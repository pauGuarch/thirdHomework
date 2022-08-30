package com.ironhack.crm.domain.models;

import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.exceptions.IntegerException;

import java.util.UUID;

public class Opportunity {
    private UUID id;
    private Contact decisionMaker;
    private Integer quantity;
    private OpportunityStatus status;
    private Product product;


    public Opportunity(Contact decisionMaker, Integer quantity, OpportunityStatus status, Product product) {
        setId();
        this.decisionMaker = decisionMaker;
        this.quantity = quantity;
        this.status = status;
        this.product = product;
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

    public OpportunityStatus getStatus() {
        return status;
    }

    public void setStatus(OpportunityStatus status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
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
