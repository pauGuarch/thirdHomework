package com.ironhack.crm.dao.manager.implementation;
import com.ironhack.crm.dao.manager.ContactManager;
import com.ironhack.crm.dao.repositories.ContactRepository;
import com.ironhack.crm.domain.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ironhack.crm.utils.Utils.readContacts;
import static com.ironhack.crm.utils.Utils.writeContactsJSON;

@Controller
public class ContactManagerImpl implements ContactManager {
    @Autowired
    private ContactRepository contactRepository;
    private List<Contact> contacts;


    @Override
    public void createNewContact(Contact contact) {
        contactRepository.save(contact);
        checkContacts();
    }

    @Override
    public List<Contact> checkContacts() {
        return contactRepository.findAll();
    }
}
