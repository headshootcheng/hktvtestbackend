package com.example.demo.controller;
import com.example.demo.modal.Message;
import com.example.demo.service.DataHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ApiController {


    @Autowired
    private DataHandler dataHandler;

    //For testing
    @GetMapping("/hello")
    public String test(){
        return "hello";
    }

    @PostMapping("/uploadProduct")
    public Message uploadProductInfo(@RequestParam("file") MultipartFile file){
        return dataHandler.handleCSVFile(file,"product");
    }

    @PostMapping("/manageProduct")
    public Message uploadStorageInfo(@RequestParam("file") MultipartFile file){
        return dataHandler.handleCSVFile(file,"storage");
    }


}

