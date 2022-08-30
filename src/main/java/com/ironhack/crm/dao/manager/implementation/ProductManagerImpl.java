package com.ironhack.crm.dao.manager.implementation;
import com.ironhack.crm.dao.manager.ProductManager;
import com.ironhack.crm.domain.models.Account;
import com.ironhack.crm.domain.models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ironhack.crm.utils.Utils.*;

public class ProductManagerImpl implements ProductManager {
    private static ProductManagerImpl productManager;
    private List<Product> products;

    private ProductManagerImpl() {
        this.products = checkProducts();
        if(products == null){
            products = new ArrayList<>();
        }
    }

    public static ProductManagerImpl getInstance() {
        if (productManager == null) {
            productManager = new ProductManagerImpl();
        }
        return productManager;
    }

    @Override
    public void createProduct(Product product) {
        products.add(product);
        try {
            writeProductJSON(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkProducts();
    }

    @Override
    public List<Product> checkProducts() {
        return this.products = readProduct();
    }

    @Override
    public List<Product> removeProduct(UUID id) {
        try {
            Product productDel = products.stream()
                    .filter(product -> product.getId().equals(id)).findFirst().get();
            products.remove(productDel);
            writeProductJSON(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkProducts();
        return products;
    }

}
