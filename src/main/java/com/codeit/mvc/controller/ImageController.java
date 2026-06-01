package com.codeit.mvc.controller;

import com.codeit.mvc.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;

@RestController
@RequestMapping("/image")
@Slf4j
@RequiredArgsConstructor
public class ImageController {

    private final FileService fileService;

    @GetMapping("/{fileName}")
    public void getImage(@PathVariable String fileName) {

    }

}
