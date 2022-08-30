package com.ironhack.crm.domain.models;

import com.ironhack.crm.domain.enums.ProductType;
import com.ironhack.crm.exceptions.EmptyStringException;

import java.util.UUID;

public class Product {

    UUID id;
    private ProductType productType;

    public Product(ProductType productType) {
        setId();
        this.productType = productType;
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }


    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
