package com.thinkingrodent.blog.blog_app_apis.controllers;

import com.thinkingrodent.blog.blog_app_apis.payloads.ApiResponse;
import com.thinkingrodent.blog.blog_app_apis.payloads.FileResponse;
import com.thinkingrodent.blog.blog_app_apis.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
//@RequestMapping("/file")
public class FileController {
//    @Autowired
//    private FileService fileService;
//
//    @Value("${project.image}")
//    private String path;
//
//    @PostMapping("/upload")
//    public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile image){
//        String fileName= null;
//        try {
//            fileName = this.fileService.uploadImage(path,image);
//        } catch (IOException e) {
//            e.printStackTrace();
//            FileResponse fileResponse=new FileResponse(fileName,"Image not uploaded due to error!");
//            return new ResponseEntity<>(fileResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
////        return new ResponseEntity<FileResponse>(new FileResponse(fileName,"Image uploaded!"));
//        FileResponse fileResponse=new FileResponse(fileName,"Image uploaded!");
//        return new ResponseEntity<>(fileResponse, HttpStatus.OK);
//    }

}
