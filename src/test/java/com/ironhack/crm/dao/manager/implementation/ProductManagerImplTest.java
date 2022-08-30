package com.ironhack.crm.dao.manager.implementation;

import com.ironhack.crm.domain.enums.ProductType;
import com.ironhack.crm.domain.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerImplTest {
    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;
    private Product product5;
    private Product product6;

    private ProductManagerImpl productManager;

    @BeforeEach
    void setUp() {
        productManager = ProductManagerImpl.getInstance();
        product1 = new Product(ProductType.HYBRID);
        product2 = new Product(ProductType.BOX);
    }

    @Test
    void testCreateAndCheckProducts() {
        productManager.createProduct(product1);
        Product productTest = productManager.checkProducts().stream()
                .filter(product -> product.getId().equals(product1.getId())).findFirst().get();
        assertEquals(ProductType.HYBRID, productTest.getProductType());
        productManager.removeProduct(product1.getId());
    }
}