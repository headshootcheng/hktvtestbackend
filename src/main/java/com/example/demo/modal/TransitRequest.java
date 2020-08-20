package com.example.demo.modal;

public class TransitRequest {
    private String productId;
    private String fromLocation;
    private String toLocation;
    private Integer qty;

    public TransitRequest() {
    }

    public TransitRequest(String productId, String fromLocation, String toLocation, Integer qty) {
        this.productId = productId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.qty = qty;
    }

    public String getProductId() {
        return productId;
    }

    public String getFromLocation() {
        return fromLocation;
    }


    public String getToLocation() {
        return toLocation;
    }


    public Integer getQty() {
        return qty;
    }

    @Override
    public String toString() {
        return "TransitRequest{" +
                "productId='" + productId + '\'' +
                ", fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\'' +
                ", qty=" + qty +
                '}';
    }
}
