package com.example.demo.modal;

import javax.persistence.*;

@Entity
@Table(name="hktv_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="product_name")
    private String name;
    @Column(name="product_code")
    private String code;
    @Column(name="product_weight")
    private Integer weight;

    public Product() {

    }

    public Product(String name, String code, Integer weight) {
        this.name = name;
        this.code = code;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", weight=" + weight +
                '}';
    }
}
