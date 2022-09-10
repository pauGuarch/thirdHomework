package com.ironhack.crm.domain.models;

import com.ironhack.crm.exceptions.EmptyStringException;
import com.ironhack.crm.exceptions.IntegerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SalesTest {

    @Test
    void setSalesRepNameTest() throws EmptyStringException, IntegerException {
        SalesRep SalesRepNameTest = new SalesRep();
        SalesRepNameTest.setName("Barbara");
        assertEquals( "Barbara", SalesRepNameTest.getName());
    }

    @Test
    void setSalesRepNameExceptionTest(String barbara) throws EmptyStringException, IntegerException {
        SalesRep SalesRepName = new SalesRep();
        assertThrows(EmptyStringException.class, () -> setSalesRepNameExceptionTest("Barbara"));
    }
}
