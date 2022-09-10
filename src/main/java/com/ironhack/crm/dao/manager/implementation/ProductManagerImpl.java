package com.ironhack.crm.dao.manager.implementation;
import com.ironhack.crm.dao.manager.ProductManager;
import com.ironhack.crm.dao.repositories.ProductRepository;
import com.ironhack.crm.domain.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ironhack.crm.utils.Utils.*;

@Controller
public class ProductManagerImpl implements ProductManager {
    @Autowired
    private ProductRepository productRepository;
    private List<Product> products;

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> checkProducts() {
        return productRepository.findAll();
    }

}
