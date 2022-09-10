package com.ironhack.crm.domain.models;
import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.exceptions.EmptyStringException;
import com.ironhack.crm.exceptions.IntegerException;
import org.junit.jupiter.api.Test;
import static com.ironhack.crm.domain.enums.OpportunityStatus.CLOSED_LOST;
import static com.ironhack.crm.domain.enums.OpportunityStatus.CLOSED_WON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OpportunityTest {

    @Test
    void setQuantityTest() throws EmptyStringException, IntegerException {
        Opportunity opportunityTest = new Opportunity();
        opportunityTest.setQuantity();
        assertEquals( 135, opportunityTest.getQuantity());
    }

    @Test
    void setQuantityExceptionTest() throws EmptyStringException, IntegerException {
        Opportunity opportunityTest = new Opportunity();
        assertThrows(EmptyStringException.class, opportunityTest::setQuantity);
    }

    @Test
    void setOpportunityStatus() throws EmptyStringException, IntegerException {
        Opportunity opportunityTest = new Opportunity();
        opportunityTest.setStatus(CLOSED_WON);
        assertEquals( CLOSED_WON, opportunityTest.getStatus());
    }

    @Test
    void setOpportunityStatusTest() throws EmptyStringException, IntegerException {
        Opportunity opportunityTest = new Opportunity();
        assertThrows(EmptyStringException.class, () -> opportunityTest.setStatus(CLOSED_WON));
    }
}
