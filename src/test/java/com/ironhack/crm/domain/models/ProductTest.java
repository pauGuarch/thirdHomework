package com.ironhack.crm.domain.models;

import com.ironhack.crm.domain.enums.ProductType;
import com.ironhack.crm.exceptions.EmptyStringException;
import com.ironhack.crm.exceptions.IntegerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    @Test
    void productTypeTest() throws EmptyStringException, IntegerException {
        Product productTypeTest = new Product();
        productTypeTest.setProductType(ProductType.valueOf("Food"));
        assertEquals( "Food", productTypeTest.getProductType());
    }

    @Test
    void setProductTypeExceptionTest() throws EmptyStringException, IntegerException {
        Product productTypeTest = new Product();
        assertThrows(EmptyStringException.class, () -> productTypeTest.setProductType(ProductType.valueOf("")));
    }

}
