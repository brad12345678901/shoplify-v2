package com.shoplify.ecommerce_springboot.service.impl;

import com.shoplify.ecommerce_springboot.entity.Product;
import com.shoplify.ecommerce_springboot.entity.ProductImage;
import com.shoplify.ecommerce_springboot.service.FileService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

@Service
public class ProductFileImageService implements FileService<ProductImage, Product> {

    @Value("${file.upload.dir}/images")
    private String uploadDir;

    private final String[] fileTypes = {"image/jpeg", "image/png"};

    @Override
    public ProductImage saveFile(Product product, MultipartFile file) throws IOException {
        String completePath = uploadDir +"/"+ product.getId();
        System.out.println(completePath);
        Path imageFolder = Paths.get(completePath).toAbsolutePath().normalize();
        Files.createDirectories(imageFolder);

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        Path targetPath = imageFolder.resolve(fileName);

        if (!Arrays.asList(fileTypes).contains(file.getContentType()))
            throw new FileUploadException("File Type is not an acceptable product image");
        System.out.println(targetPath);
        file.transferTo(targetPath);

        String ImageURL = "upload/images/" + product.getId() + "/" + fileName;

        return new ProductImage(
                fileName,
                file.getContentType(),
                file.getSize(),
                ImageURL,
                product
        );
    }

    @Override
    public void deleteFile (String filename, String subfolder) {
        System.out.println("Delete File HERE");
    }
}
