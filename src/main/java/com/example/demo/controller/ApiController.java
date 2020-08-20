package com.example.demo.controller;
import com.example.demo.modal.Location;
import com.example.demo.modal.Message;
import com.example.demo.modal.Option;
import com.example.demo.modal.Product;
import com.example.demo.respository.LocationRepository;
import com.example.demo.respository.ProductRepository;
import com.example.demo.respository.StorageRepository;
import com.example.demo.service.DataHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ApiController {

    @Autowired
    private DataHandler dataHandler;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    StorageRepository storageRepository;

    //For testing
    @GetMapping("/hello")
    public String test(){
        return "hello";
    }

    // For upload new product which is from csv
    @PostMapping("/uploadProduct")
    public Message uploadProductInfo(@RequestParam("file") MultipartFile file){
        return dataHandler.handleCSVFile(file,"product");
    }
    // For adding new location
    @PostMapping("/addNewLocation")
    public Message uploadProductInfo(@RequestBody Map<String,String> data){
        return dataHandler.addNewLocation(data.get("data"));
    }

    // For upload  new storage which is from csv
    @PostMapping("/manageProduct")
    public Message uploadStorageInfo(@RequestParam("file") MultipartFile file){
        return dataHandler.handleCSVFile(file,"storage");
    }

    //For providing the options of location and product to front-end
    @GetMapping("/provideOption")
    public Option provideOption(){
        List<Product> productList = productRepository.findAll();
        List<Location> locationList = locationRepository.findAll();
        return new Option(productList,locationList);
    }


}

