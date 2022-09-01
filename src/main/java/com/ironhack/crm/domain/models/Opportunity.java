package com.ironhack.crm.domain.models;

import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.exceptions.IntegerException;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID uuid;
    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact decisionMaker;
    private Integer quantity;
    @Enumerated(value = EnumType.STRING)
    private OpportunityStatus status;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Opportunity() {
    }

    public Opportunity(Contact decisionMaker, Integer quantity, OpportunityStatus status, Product product, SalesRep salesRep) {
        setId();
        this.decisionMaker = decisionMaker;
        this.quantity = quantity;
        this.status = status;
        this.product = product;
        this.salesRep = salesRep;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) throws IntegerException {
        if(quantity > 0){
            this.quantity = quantity;
        }else {
            throw new IntegerException();
        }
    }

    public SalesRep getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(SalesRep salesRep) {
        this.salesRep = salesRep;
    }

    public OpportunityStatus getStatus() {
        return status;
    }

    public void setStatus(OpportunityStatus status) {
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setId() {
        this.uuid = UUID.randomUUID();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean close(OpportunityStatus status){
        if (status == OpportunityStatus.CLOSED_LOST || status == OpportunityStatus.CLOSED_WON){
            setStatus(status);
            return true;
        }else{
            return false;
        }
    }


    public boolean close(){
        return true;
    }
    public void lookUp(){

    }


}
