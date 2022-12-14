package com.ironhack.crm.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.domain.models.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Utils {
    public static void lookUpOpportunity(Opportunity opportunity){
        System.out.println(opportunity.toString());
    }

    //Returns the number introduced by the customer within the command. If command is not valid returns 0.
    public static int validateConversionCommand(String command){
        int id = 0;
        try {
            if(command.substring(0, 8).toLowerCase().equals("convert ")){
                String leadId = command.substring(8, command.length());
                try{
                    id = Integer.parseInt(leadId);
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                    id =0;
                }
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("The command you entered is not valid");
        }finally {
            return id;
        }

    }

    public static boolean validateNewAccountCommand(String command){
        boolean isCommand= false;
        try {
            if(command.substring(0, 3).equalsIgnoreCase("new")){
               isCommand = true;
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("");
        }
        return isCommand;
    }

    public static boolean validateSelectAccountCommand(String command){
        boolean isCommand= false;
        try {
            if(command.equalsIgnoreCase("select")){
               isCommand = true;
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("error sselect");
        }
        return isCommand;
    }

    //Returns the number introduced by the customer within the command. If command is not valid returns 0.
    public static int validateLookUpOpportunityCmd(String command){
        int id=0;
        try{
            if(command.substring(0, 19).toLowerCase().equals("lookup opportunity ")){
                String leadId = command.substring(19, command.length());
                try{
                    id = Integer.parseInt(leadId);
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                    id = 0;
                }
            }else{
                id = 0;
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("The command you entered is not valid");
        }finally {
            return id;
        }
    }


    public static List<Opportunity> readOpportunities() {
        List<Opportunity> opportunities = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get("./src/main/resources/data/opportunity.json"));
            opportunities = new Gson().fromJson(reader, new TypeToken<List<Opportunity>>() {}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return opportunities;
    }

    public static List<Contact> readContacts() {
        List<Contact> contacts = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get("./src/main/resources/data/contact.json"));
            contacts = new Gson().fromJson(reader, new TypeToken<List<Contact>>() {}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contacts;
    }
    public static List<Account> readAccounts() {
        List<Account> accounts = null;
        try {
            //Reader reader = new FileReader(new ClassPathResource("data/account.json").getFile());
            Reader reader = Files.newBufferedReader(Paths.get("./src/main/resources/data/account.json"));
            accounts = new Gson().fromJson(reader, new TypeToken<List<Account>>() {}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return accounts;
    }
    public static List<Product> readProduct() {
        List<Product> products = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get("./src/main/resources/data/product.json"));
            products = new Gson().fromJson(reader, new TypeToken<List<Product>>() {}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public static List<Lead> readLeads() {
        List<Lead> leads = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get("./src/main/resources/data/lead.json"));
            leads = new Gson().fromJson(reader, new TypeToken<List<Lead>>() {}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return leads;
    }
    
    public static void writeAccountsJSON(List<Account> accountList) throws IOException {
        try {
            Writer writer = new FileWriter("./src/main/resources/data/account.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(accountList, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void writeContactsJSON(List<Contact> contactList) throws IOException {
        try {
            Writer writer = new FileWriter("./src/main/resources/data/contact.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(contactList, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void writeLeadsJSON(List<Lead> leadList) throws IOException {
        try {
            Writer writer = new FileWriter("./src/main/resources/data/lead.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(leadList, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void writeOpportunityJSON(List<Opportunity> opportunityList) throws IOException {
        try {
            Writer writer = new FileWriter("./src/main/resources/data/opportunity.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(opportunityList, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void writeProductJSON(List<Product> productList) throws IOException {
        try {
            Writer writer = new FileWriter("./src/main/resources/data/product.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(productList, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void writeSalesRepJSON(List<SalesRep> salesReps) throws IOException {

    }

    public static void showContacts(List<Contact> contacts) {

        StringBuilder contact = new StringBuilder();
        String id = "ID";
        String name = "NAME";
        String email = "EMAIL";
        String phoneNumber = "PHONE NUMBER";
        String companyName = "COMPANY NAME";

        System.out.format("%s%43s%31s%24s%32s \n"+"-".repeat(142)+"\n", id, name, email, phoneNumber, companyName);

        for (int i = 0; i < contacts.size(); i++) {
            System.out.format("%-42s %-28s %-30s %-25s %-12s \n", contacts.get(i).getUuid(), contacts.get(i).getName(), contacts.get(i).getEmail(), contacts.get(i).getPhoneNumber(), contacts.get(i).getCompanyName());
        }

    }

    public static void showOpportunities(List<Opportunity> opportunities) {

        StringBuilder opportunity = new StringBuilder();
        String id = "ID";
        String decisionMaker = "DECISION MAKER";
        String quantity = "QUANTITY";
        String status = "STATUS";
        String product = "PRODUCT";
        System.out.format("%s%55s%31s%24s%32s \n"+"-".repeat(142)+"\n", id, decisionMaker, quantity, status, product);

        for (int i = 0; i < opportunities.size(); i++) {
            System.out.format("%-42s %-28s %-30s %-25s %-12s \n", opportunities.get(i).getUuid(), opportunities.get(i).getDecisionMaker().getName(), opportunities.get(i).getQuantity(), opportunities.get(i).getStatus(), opportunities.get(i).getProduct());
        }
    }

    //TODO ponerlo bonito
    public static void showSalesReps(List<SalesRep> salesRepList) {
        StringBuilder salesReps = new StringBuilder();
        String id = "ID";
        String name = "NAME";
        System.out.format("%-43s %-24s \n"+"-".repeat(50)+"\n", id, name);
        //System.out.println("ID   -   UUID    -    Name");
        //for (SalesRep salesRep : salesRepList) {
            //System.out.println(salesRep.getId() + "     " + salesRep.getUuid()+ "     " + salesRep.getName());

        for (int i = 0; i < salesRepList.size(); i++) {

            System.out.format("%-42s %-30s \n", salesRepList.get(i).getId(), salesRepList.get(i).getName());

        }
    }

    public static void showProducts(List<Product> products) {
        StringBuilder product = new StringBuilder();
        String id = "ID";
        String productName = "PRODUCT NAME";
        String productType = "PRODUCT TYPE";

        System.out.format("%-43s %-31s %-24s \n"+"-".repeat(142)+"\n", id, productName, productType);

        for (int i = 0; i < products.size(); i++) {

            System.out.format("%-42s %-28s %-30s \n", products.get(i).getId(), products.get(i).getProductType(), products.get(i).getName());

        }
    }

    public static void showLeads(List<Lead> leads){
        StringBuilder lead = new StringBuilder();
        String id = "ID";
        String name = "NAME";
        String company = "COMPANY";
        String email = "EMAIL";
        String phoneNumber = "PHONENUMBER";
        System.out.format("%s%43s%31s%24s%32s \n"+"-".repeat(142)+"\n", id,
                name, company, email, phoneNumber);
        for (int i = 0; i < leads.size(); i++) {
            System.out.format("%-40s %-27s %-25s %-25s %-12s \n", leads.get(i).getId().toString(), leads.get(i).getName(),
                    leads.get(i).getCompanyName(), leads.get(i).getEmail(), leads.get(i).getPhoneNumber());
        }
    }

    public static void showLead(Lead lead){
        String id = "ID";
        String name = "NAME";
        String company = "COMPANY";
        String email = "EMAIL";
        String phoneNumber = "PHONENUMBER";
        System.out.format("%s%43s%31s%24s%32s \n"+"-".repeat(142)+"\n", id,
                name, company, email, phoneNumber);
        System.out.format("%-40s %-27s %-25s %-25s %-12s \n", lead.getUuid().toString(), lead.getName(),
                    lead.getCompanyName(), lead.getEmail(), lead.getPhoneNumber());
    }

    public static void showSalesRepsAndStatus(SalesRep salesRep, int status, Long count) {
        System.out.println("\nThe SalesRep " + salesRep.getName() + " have " + count + " Opportunities with the status " + OpportunityStatus.values()[status] + "\n");
    }

    public static void showOpportunitiesByTheProduct(Long countOpportunities, String productName) {
        System.out.println("\n The product " + productName + " have " + countOpportunities + " opportunities \n");
    }

    public static void showClosedWonByTheProduct(Long countClosedWon, String productName) {
    System.out.println("\n The product " + productName + " have " + countClosedWon + " opportunities with CLOSE_WON status");
    }

    public static void showClosedLostByTheProduct(Long countClosedLost, String productName) {
        System.out.println("\n The product " + productName + " have " + countClosedLost + " opportunities with CLOSE_LOST status");
    }

    public static void showOpenByTheProduct(Long countOpen, String productName) {
        System.out.println("\n The product " + productName + " have " + countOpen + " opportunities with OPEN status");
    }

    public static void showQuantityCount(Long quantity, String request) {
        System.out.println("\nThe " + request + " quantity of products is " + quantity + "\n");
    }

    /*
    public static void showSalesRepsByOpportunity(Long opportunities, int status) {
        System.out.println("These are all the Opportunities with the status " + OpportunityStatus.values()[status] + " Assigned to the SalesRep: " + salesrep.getName());
        System.out.println(" SalesRep " + getSalesRepId);
    }*/


}
