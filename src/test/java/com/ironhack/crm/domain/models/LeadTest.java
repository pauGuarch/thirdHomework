package com.ironhack.crm.domain.models;
import com.ironhack.crm.exceptions.EmptyStringException;
import com.ironhack.crm.exceptions.IntegerException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeadTest {

    @Test
    void setNameTest() throws EmptyStringException, IntegerException {
        Lead leadTest = new Lead();
        leadTest.setName("Aline");
        assertEquals( "Aline", leadTest.getName());
    }

    @Test
    void setNameExceptionTest() throws EmptyStringException, IntegerException {
        Lead leadTest = new Lead();
        assertThrows(EmptyStringException.class, () -> leadTest.setName(""));
    }

    @Test
    void setCompanyNameTest() throws EmptyStringException, IntegerException {
        Lead leadTest = new Lead();
        leadTest.setCompanyName("Casa");
        assertEquals( "Casa", leadTest.getCompanyName());
    }

    @Test
    void setCompanyNameExceptionTest() throws EmptyStringException, IntegerException {
        Lead leadTest = new Lead();
        assertThrows(EmptyStringException.class, () -> leadTest.setCompanyName(""));
    }

    @Test
    void setEmailTest() throws EmptyStringException, IntegerException {
        Lead leadTest = new Lead();
        leadTest.setEmail("aline@casa.com");
        assertEquals( "aline@casa.com", leadTest.getEmail());
    }

    @Test
    void setEmailExceptionTest() throws EmptyStringException, IntegerException {
        Lead leadTest = new Lead();
        assertThrows(EmptyStringException.class, () -> leadTest.setEmail(""));
    }

    @Test
    void setPhoneNumberTest() throws EmptyStringException, IntegerException {
        Lead leadTest = new Lead();
        leadTest.setPhoneNumber("615320418");
        assertEquals( "615320418", leadTest.getPhoneNumber());
    }

    @Test
    void setPhoneNumberExceptionTest() throws EmptyStringException, IntegerException {
        Lead leadTest = new Lead();
        assertThrows(EmptyStringException.class, () -> leadTest.setPhoneNumber(""));
    }
}
