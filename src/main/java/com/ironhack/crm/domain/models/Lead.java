package com.ironhack.crm.domain.models;

import com.ironhack.crm.exceptions.EmptyStringException;
import com.ironhack.crm.exceptions.IntegerException;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Leadd")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID uuid;
    private String name;
    private String companyName;
    private String email;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;

    public Lead() {
    }

    public Lead(String name, String companyName, String email, String phoneNumber, SalesRep salesRep) {
        setId();
        this.name = name;
        this.companyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salesRep = salesRep;
    }

    public Integer getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setId(){this.uuid = UUID.randomUUID();}

    public String getName() {
        return name;
    }

    public void setName(String name) throws IntegerException, EmptyStringException {
        if(!name.isEmpty()){
            this.name = name;
        }else {
            throw new EmptyStringException();
        }
    }

    public SalesRep getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(SalesRep salesRep) {
        this.salesRep = salesRep;
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
}
