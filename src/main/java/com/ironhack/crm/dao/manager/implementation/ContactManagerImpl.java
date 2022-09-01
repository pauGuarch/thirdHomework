package com.ironhack.crm.dao.manager.implementation;
import com.ironhack.crm.dao.manager.ContactManager;
import com.ironhack.crm.domain.models.Contact;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ironhack.crm.utils.Utils.readContacts;
import static com.ironhack.crm.utils.Utils.writeContactsJSON;

public class ContactManagerImpl implements ContactManager {
    private static ContactManagerImpl contactManager;
    private List<Contact> contacts;

    private ContactManagerImpl() {
        contacts = checkContacts();
        if (contacts == null){
            contacts = new ArrayList<>();
        }
    }

    public static ContactManagerImpl getInstance() {
        if (contactManager == null) {
            contactManager = new ContactManagerImpl();
        }
        return contactManager;
    }
    @Override
    public void createNewContact(Contact contact) {
        contacts.add(contact);
        try {
            writeContactsJSON(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkContacts();
    }
    @Override
    public List<Contact> deleteContact(UUID contactId) {
        try {
            Contact contactDel = contacts.stream()
                    .filter(contact -> contact.getUuid().equals(contactId)).findFirst().get();
            contacts.remove(contactDel);
            writeContactsJSON(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkContacts();
        return contacts;
    }

    @Override
    public List<Contact> checkContacts() {
        return this.contacts = readContacts();
    }
}
