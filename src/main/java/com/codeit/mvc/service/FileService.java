package com.codeit.mvc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class FileService {

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(
            // 이미지
            ".jpg", ".jpeg", ".png", ".gif", ".webp", ".bmp", ".svg",
            // 문서
            ".pdf", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx",
            // 텍스트
            ".txt", ".md", ".csv", ".json"
    );

    private final Path uploadPath;

    public FileService(@Value("${blog.file-directory}") String uploadDir) {
        this.uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(uploadPath);
            log.info("Upload directory created: {}", uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("업로드 디렉토리 생성 실패: " + uploadPath, e );
        }

    }

    public String saveFile(MultipartFile file) {
        //cleanPath = null체크 및 정규화 (상대경로 등을 정리)
        String originFileName = StringUtils.cleanPath(
                file.getOriginalFilename() == null ? "unknown" : file.getOriginalFilename()
        );

        int dotIndex = originFileName.lastIndexOf(" . ");
        String extension = (dotIndex >=0)  ? originFileName.substring(dotIndex).toLowerCase(): "";

        // 확장자 검증
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new IllegalArgumentException("Not allowed file type: " + extension);
        };

        // 랜덤 문자열 UUID를 이용해서 생성 후 확장자를 붙여서 저장 할 파일명 새롭게 생성.
        String savedFileName = UUID.randomUUID().toString().replace("-", "") + extension;

        // 위에 준비한 업로드패스와 새로 생성한 파일명을 이어 붙인 패스 객체 생성
        Path targetPath = uploadPath.resolve(savedFileName).normalize();

        try {
            file.transferTo(targetPath);
            log.info("File uploaded: {} (origin: {}. size: {} bytes", savedFileName, originFileName, file.getSize());
            return savedFileName; // 파일 저장이 완료되면 새로운 파일명을 리턴. -> DB 저장.
        } catch (IOException e) {
            throw new RuntimeException("File save fail : {} " + originFileName, e);
        }


    }

}
