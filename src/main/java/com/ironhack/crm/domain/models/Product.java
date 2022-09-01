package com.ironhack.crm.domain.models;

import com.ironhack.crm.domain.enums.ProductType;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID uuid;
    //TODO change name to type
    private String name;
    @Column(name = "type")
    private ProductType productType;

    public Product() {
    }

    public Product(ProductType productType) {
        setId();
        this.productType = productType;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setId() {
        this.uuid = UUID.randomUUID();
    }


    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
