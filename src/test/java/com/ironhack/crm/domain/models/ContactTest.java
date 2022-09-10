package com.ironhack.crm.domain.models;
import com.ironhack.crm.exceptions.EmptyStringException;
import com.ironhack.crm.exceptions.IntegerException;
import org.junit.jupiter.api.Test;
import javax.lang.model.element.Name;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactTest {

    @Test
    static void setIdTest() throws EmptyStringException, IntegerException {
    Contact contactTest = new Contact();
    ContactTest.setIdTest();
    assertEquals( "", contactTest.getUuid());
    }
    @Test
    void setNameTest() throws EmptyStringException, IntegerException {
       Contact nameTest = new Contact();
       nameTest.setName("Aline");
       assertEquals("Aline", nameTest.getName());
    }

    @Test
    void setNameExceptionTest() throws EmptyStringException, IntegerException {
        Contact nameTest = new Contact();
        EmptyStringException emptyStringException = assertThrows(EmptyStringException.class, () -> nameTest.setName(""));
    }
    @Test
    void setEmailTest() throws EmptyStringException, IntegerException {
        Contact emailTest = new Contact();
        emailTest.setEmail("aline@casa.com");
        assertEquals( "aline@casa.com", emailTest.getEmail());
    }

    @Test
    void setEmailExceptionTest() throws EmptyStringException, IntegerException {
        Contact emailTest = new Contact();
        assertThrows(EmptyStringException.class, () -> emailTest.setEmail(""));
    }

    @Test
    void setPhoneNumberTest() throws EmptyStringException, IntegerException {
       Contact phoneNumberTest = new Contact();
       phoneNumberTest.setPhoneNumber("615320418");
       assertEquals( "615320418", phoneNumberTest.getPhoneNumber());
    }

    @Test
    void setPhoneNumberExceptionTest() throws EmptyStringException, IntegerException {
        Contact phoneNumberTest = new Contact();
        assertThrows(EmptyStringException.class, () -> phoneNumberTest.setPhoneNumber(""));
    }

    @Test
    void setCompanyNameTest() throws EmptyStringException, IntegerException {
        Contact companyNameTest = new Contact();
        companyNameTest.setCompanyName("Casa");
        assertEquals( "Casa", companyNameTest.getCompanyName());
    }

    @Test
    void setCompanyNameExceptionTest() throws EmptyStringException, IntegerException {
        Contact companyNameTest = new Contact();
        assertThrows(EmptyStringException.class, () -> companyNameTest.setCompanyName(""));
    }
}
