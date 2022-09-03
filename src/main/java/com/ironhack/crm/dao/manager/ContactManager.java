package com.ironhack.crm.dao.manager;
import com.ironhack.crm.domain.models.Contact;
import java.util.List;
import java.util.UUID;

public interface ContactManager {
    void createNewContact(Contact contact);
    List<Contact> checkContacts();
}
