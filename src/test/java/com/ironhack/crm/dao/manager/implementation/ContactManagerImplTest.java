package com.ironhack.crm.dao.manager.implementation;

import com.ironhack.crm.domain.models.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagerImplTest {
    private Contact contact1;
    private Contact contact2;

    private ContactManagerImpl contactManager;

    @BeforeEach
    void setUp() {
        contact1 = new Contact("Manolo", "manolo@gmail", "212512525", "RamonerCompany");
        contact2 = new Contact("Rubi", "rubi@gmail", "212512525", "RamonerCompany");
    }


    @Test
    void testCreateNewContact() {
        contactManager.createNewContact(contact1);
        Contact testContact = contactManager.checkContacts().stream()
                .filter(contact -> contact.getUuid().equals(contact1.getUuid())).findFirst().get();
        assertEquals("Manolo", testContact.getName());
        contactManager.deleteContact(contact1.getUuid());
    }


    @Test
    void testCheckContacts() {
        int contactListSize = contactManager.checkContacts().size();
        contactManager.createNewContact(contact1);
        contactManager.createNewContact(contact2);
        List<Contact> contactList = contactManager.checkContacts();
        assertEquals(contactList.size(), contactListSize + 2);
        contactManager.deleteContact(contact1.getUuid());
        contactManager.deleteContact(contact2.getUuid());
    }


}