package com.ironhack.crm.domain.models;
import com.ironhack.crm.exceptions.EmptyStringException;
import com.ironhack.crm.exceptions.IntegerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account accountTest = new Account();

    @Test
    void setIndustryWithValueTest() throws EmptyStringException {
      accountTest.setIndustry("Tech");
      assertEquals("Tech", accountTest.getIndustry());
    }

    @Test
    void setIndustryWithNoValueTest() {
        assertThrows(EmptyStringException.class, () -> accountTest.setIndustry(""));
    }

    @Test
    void setEmployeeCountTest() throws IntegerException {
        accountTest.setEmployeeCount(12);
        assertEquals(12, accountTest.getEmployeeCount());
    }

    @Test
    void setEmployeeCountExceptionTest() throws IntegerException {
       assertThrows(IntegerException.class, () -> accountTest.setEmployeeCount(-1));
    }

    @Test
    void setCityTest() throws EmptyStringException {
        accountTest.setCity("Tetuan");
        assertEquals("Tetuan", accountTest.getCity());
    }

    @Test
    void setCityIsNotValidTest() throws EmptyStringException {
        assertThrows(EmptyStringException.class, () -> accountTest.setCity(""));
    }

    @Test
    void setCountryTest() throws EmptyStringException {
        accountTest.setCountry("México");
        assertEquals("México", accountTest.getCountry());
    }
    @Test
    void setCountryIsNotValidTest() throws EmptyStringException {
        assertThrows(EmptyStringException.class, () -> accountTest.setCountry(""));
    }
}