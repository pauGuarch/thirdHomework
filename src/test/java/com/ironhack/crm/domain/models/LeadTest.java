package com.ironhack.crm.domain.models;
import com.ironhack.crm.exceptions.EmptyStringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeadTest {
    Lead leadTest = new Lead();

    @Test
    void setLeadTest() throws EmptyStringException {
        leadTest.setId();
        assertEquals( 1, leadTest.setId());
    }

}
