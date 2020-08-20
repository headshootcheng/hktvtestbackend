package com.example.demo.service;

import com.example.demo.modal.*;
import com.example.demo.respository.LocationRepository;
import com.example.demo.respository.ProductRepository;
import com.example.demo.respository.StorageRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class DataHandler {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    StorageRepository storageRepository;


    public Message addNewLocation (String newLocationName){
        List<Location> locationList = locationRepository.findByName(newLocationName);
        if(!locationList.isEmpty()){
            return new Message(0,"This location is already existed");
        }
        else{
            locationRepository.save(new Location(newLocationName));
            return  new Message(1,"Added to database");
        }
    }

    public Message handleCSVFile(MultipartFile file, String type){
        if (file.isEmpty()) {
            return new Message(0,"The file is empty!!!!");
        } else {
            try {
                //read the file uploaded from client side
                Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

                CSVReader csvReader = new CSVReader(reader);

                List<String[]> records = csvReader.readAll();

                if(type == "product"){
                    productDataHandler(records);
                }

                if(type == "storage"){
                    storageDataHandler(records);
                }

                reader.close();
                return new Message(1,"Upload Success");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return new Message(0,"Unknown Error!!!");
            }
        }
    }

    public Message handleTransit(TransitRequest transitRequest){
        Integer qty = transitRequest.getQty();
        String productId = transitRequest.getProductId();
        String fromlocation = transitRequest.getFromLocation();
        String toLocation = transitRequest.getToLocation();
        if(inStorage(productId,fromlocation)){
            // check whether the qty is reasonable or not
            if(qtyOverLimit(qty,productId,fromlocation)){
                return new Message(0,"Qty is over the limit!!!");
            }
            transitAction(qty,productId,fromlocation,toLocation);
            return new Message(1,"Transit success!!!");
        }else {
            return new Message(0,"This data doesn't exist!!!");
        }
    }



    private void productDataHandler(List<String[]> records){
        for (String[] record : records) {
            //Check whether the product code is existed in database  or not as it is a unique/primary key for the product
            List<Product> productList = productRepository.findByCode(record[1]);

            if(productList.isEmpty()) {
                //store the data into database
                productRepository.save(new Product(record[0], record[1], Integer.parseInt(record[2])));
            }
        }
    }

    private void storageDataHandler(List<String[]> records){
        for (String[] record : records) {
            // To make sure the Qty must be positive number
            if (Integer.parseInt(record[1]) > 0) {
                // To make sure that location name and product code are existed in database location and product
                // In order to save the useful data from csv
                List<Product> productList = productRepository.findByCode(record[0]);
                List<Location> locationList = locationRepository.findByName(record[2]);

                if (!productList.isEmpty() && !locationList.isEmpty()) {

                    if (!inStorage(record[0],record[2])) {
                        //store the data into database if the data is not existed in database
                        storageRepository.save(new Storage(record[0], Integer.parseInt(record[1]), record[2]));
                    } else {
                        //update the data into database if the data is existed in database
                        List<Storage> storageList = storageRepository.findByCodeAndLocation(record[0], record[2]);
                        Integer initialQty = storageList.get(0).getQty();
                        Integer addQty = Integer.parseInt(record[1]);
                        AddStorageNumber(initialQty, addQty, storageList.get(0));
                    }
                }
            }
        }
    }
    private boolean inStorage( String productId, String location){
        List<Storage> storageList = storageRepository.findByCodeAndLocation(productId,location);
        if(storageList.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    private boolean qtyOverLimit(Integer qty,String productId, String location){
        List<Storage> storageList = storageRepository.findByCodeAndLocation(productId,location);
        if(qty>storageList.get(0).getQty()){
            return true;
        }
        else{
            return false;
        }
    }

    private void AddStorageNumber(Integer initialQty, Integer addQty, Storage existStorage){
        existStorage.setQty(initialQty+addQty);
        storageRepository.save(existStorage);
    }

    private void transitAction(Integer qty, String productId, String fromLocation, String toLocation){
        Storage initialStorage = storageRepository.findByCodeAndLocation(productId,fromLocation).get(0);
        Integer initialQty = initialStorage.getQty();
        // remove the qty from original location
        initialStorage.setQty(initialQty-qty);
        storageRepository.save(initialStorage);

        //Check whether the product is already existed in destination or not
        if(inStorage(productId,toLocation)){
            // Add the qty to destination
            Storage destinationStorage = storageRepository.findByCodeAndLocation(productId,toLocation).get(0);
            Integer initialDestinationQty = destinationStorage.getQty();
            AddStorageNumber(initialDestinationQty,qty,destinationStorage);
        }
        else{
            //Create new data to Storage database
            storageRepository.save(new Storage(productId,qty,toLocation));
        }
    }

}
