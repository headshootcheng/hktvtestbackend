package com.example.demo.modal;

import java.util.List;

public class Option {
    private List<Product> productList;
    private List<Location> locationList;

    public Option() {
    }

    public Option(List<Product> productList, List<Location> locationList) {
        this.productList = productList;
        this.locationList = locationList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    @Override
    public String toString() {
        return "Option{" +
                "productList=" + productList +
                ", locationList=" + locationList +
                '}';
    }

}
