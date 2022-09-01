package com.ironhack.crm.domain.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sales_rep")
public class SalesRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID uuid;
    private String name;

    @OneToMany(mappedBy = "salesRep")
    private List<Lead> leadList;

    @OneToMany(mappedBy = "salesRep")
    private List<Opportunity> opportunityList;




    public SalesRep() {
    }

    public SalesRep(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }


    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
