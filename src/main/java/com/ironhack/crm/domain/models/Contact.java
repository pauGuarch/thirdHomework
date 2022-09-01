package com.ironhack.crm.domain.models;

import com.ironhack.crm.exceptions.EmptyStringException;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID uuid;
    private String name;
    private String email;
    private String phoneNumber;
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRepOpportunity;

    public Contact() {
    }

    public Contact(String name, String email, String phoneNumber, String companyName) {
        setId();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId() {
        this.uuid = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws EmptyStringException {
        if(!name.isEmpty()){
            this.name = name;
        }else {
            throw new EmptyStringException();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmptyStringException {
        if(!email.isEmpty()){
            this.email = email;
        }else {
            throw new EmptyStringException();
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws EmptyStringException {
        if(!phoneNumber.isEmpty()){
            this.phoneNumber = phoneNumber;
        }else {
            throw new EmptyStringException();
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) throws EmptyStringException {
        if(!companyName.isEmpty()){
            this.companyName = companyName;
        }else {
            throw new EmptyStringException();
        }
    }

    @Override
    public String toString() {
        return "Id{" +
                "name='" + name + '\'' +
                ", phoneNumber ='" + '\'' +
                ", email=" +
                ", companyName=" +
                '}';
    }

}
