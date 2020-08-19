package com.example.demo.controller;

import com.example.demo.modal.Message;
import com.example.demo.modal.Product;
import com.example.demo.respository.ProductRepository;
import com.opencsv.CSVReader;
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
    ProductRepository productRepository;

    @GetMapping("/hello")
    public String test(){
        return "hello";
    }

    @PostMapping("/uploadProduct")
    public Message uploadProductInfo(@RequestParam("file") MultipartFile file){
        // validate file
        if (file.isEmpty()) {
            return new Message(0,"The file is empty!!!!");
        } else {

            try {
                //read the file uploaded from client side
                Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

                CSVReader csvReader = new CSVReader(reader);

                List<String[]> records = csvReader.readAll();


                for (String[] record : records) {

                    //Check whether the product code is existed in database  or not as it is a unique/primary key for the product
                    List<Product> productList = productRepository.findByCode(record[1]);

                    if(productList.isEmpty()) {
                        //store the data into database
                        productRepository.save(new Product(record[0], record[1], Integer.parseInt(record[2])));
                    }
                }
                reader.close();
                return new Message(1,"Upload Success");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return new Message(0,"Unknown Error!!!");
            }
        }
    }
}

