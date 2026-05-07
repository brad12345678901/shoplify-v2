package com.shoplify.ecommerce_springboot.service.impl;

import com.shoplify.ecommerce_springboot.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileImageService implements FileService {

    @Value("${file.upload.dir}/images")
    private String uploadDir;

    public String saveFile(MultipartFile file) throws IOException {
        System.out.println("Save File HERE");
        return "";
    }

    public void deleteFile (String filename) {
        System.out.println("Delete File HERE");
    }
}
