package com.example.demo.modal;

import javax.persistence.*;

@Entity
@Table(name = "hktv_storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="storage_id")
    private int id;
    @Column(name = "product_code")
    private String code;
    @Column(name = "product_qty")
    private Integer qty;
    @Column(name = "location_name")
    private String location;

    public Storage() {
    }

    public Storage(String code, Integer qty, String location) {
        this.code = code;
        this.qty = qty;
        this.location = location;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", qty=" + qty +
                ", location='" + location + '\'' +
                '}';
    }

}
