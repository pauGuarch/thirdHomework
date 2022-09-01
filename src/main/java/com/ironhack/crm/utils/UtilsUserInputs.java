package com.ironhack.crm.utils;

import com.ironhack.crm.domain.enums.ProductType;
import com.ironhack.crm.domain.models.Lead;
import com.ironhack.crm.domain.models.Product;
import com.ironhack.crm.domain.models.SalesRep;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsUserInputs {

    public static String getAccountCountryInput() {
        Scanner input = new Scanner(System.in);
        String accountCountry = "";
        boolean isValidCountry = false;
        while (!isValidCountry) {
            System.out.print("Please input the Country for the account that you want to create: ");
            try {
                accountCountry = input.nextLine();
                isValidCountry = true;
            } catch (InputMismatchException a) {

            }
        }
        System.out.println("\nOpportunity created successfully\n");
        return accountCountry;
    }

    public static String getAccountCityInput() {
        Scanner input = new Scanner(System.in);
        String accountCity = "";
        boolean isValidCity = false;
        while (!isValidCity) {
            System.out.print("Please input the city for the account that you want to create: ");
            try {
                accountCity = input.nextLine();
                isValidCity = true;
            } catch (InputMismatchException a) {

            }
        }
        return accountCity;
    }

    public static String getAccountIndustryInput() {
        Scanner input = new Scanner(System.in);
        String accountIndustry = "";
        boolean isValidName = false;
        while (!isValidName) {
            System.out.print("Please input the industry for the account that you want to create: ");
            try {
                accountIndustry = input.nextLine();
                isValidName = true;
            } catch (InputMismatchException a) {

            }
        }
        return accountIndustry;
    }

    public static int getEmployeesNumberInput(){
        Scanner input = new Scanner(System.in);
        boolean isValidQuantity = false;
        int productQuantity = 0;
        while(!isValidQuantity) {
            System.out.print("Please input the number of employees of the account: ");
            try {
                productQuantity = Integer.parseInt(input.nextLine());
                if (productQuantity >0 && productQuantity<1000000) {
                    isValidQuantity = true;
                }
            } catch (NumberFormatException num) {

            }
        }
        return productQuantity;
    }

    public static int getProductQuantityInput(){
        Scanner input = new Scanner(System.in);
        boolean isValidQuantity = false;
        int productQuantity = 0;
        while(!isValidQuantity) {
            System.out.print("Please input the product quantity for the Opportunity ");
            try {
                productQuantity = Integer.parseInt(input.nextLine());
                if (productQuantity >0) {
                    isValidQuantity = true;
                }
            } catch (NumberFormatException num) {

            }
        }
        return productQuantity;
    }

    public static Product createProduct(){
        Scanner input = new Scanner(System.in);
        String productName = null;
        int productTypeIndex = 0;
        boolean isValidEnum = false;
        while(!isValidEnum) {
            if(!isValidEnum) {
                System.out.println("\nYou are about to create a new Opportunity please read carefully all the steps\n");
                System.out.print("Please input the number of product type you want to choose : 1-BOX, 2-FLATBED, 3-HYBRID : ");
                try {
                    productTypeIndex = Integer.parseInt(input.nextLine());
                    if (productTypeIndex >= 1 && productTypeIndex <= 3) {
                        isValidEnum = true;
                    }
                } catch (NumberFormatException num) {

                }
            }
        }
        ProductType productType = ProductType.values()[productTypeIndex-1];
        return new Product(productType);
    }

    public static String getLeadIdInput(){
        System.out.print("\nPlease input the lead's UUID that you want to search: ");
        Scanner input = new Scanner(System.in);
        String someUUID = input.nextLine();
        while(!isUUID(someUUID)){
            System.out.print("Please input the lead's UUID that you want to search: ");
            someUUID = input.nextLine();
        }
        return someUUID;
    }

    public static UUID getGetSalesRepId(){
        System.out.print("\nPlease input the SalesRep UUID that you want to insert in this Lead: ");
        Scanner input = new Scanner(System.in);
        String someUUID = input.nextLine();
        while(!isUUID(someUUID)){
            System.out.print("Please input the lead's UUID that you want to insert in this Lead ");
            someUUID = input.nextLine();
        }
        return UUID.fromString(someUUID);
    }

    public static Lead getUserLeadInput(SalesRep salesRep){
        return new Lead(getLeadNameInput(), getCompanyNameInput(), getLeadEmailInput(), getLeadPhoneNumberInput(), salesRep);
    }


    public static boolean validateEmail(String email){
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean validate = false;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()){
            validate=true;
        }
        return validate;
    }
    static boolean isUUID(String string) {
        try {
            UUID.fromString(string);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static String getLeadNameInput(){
        boolean isName = false;
        String name = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Please type the lead's full name in this format 'First name, Middle name and Last name': ");
        while (!isName) {
            name = input.nextLine();
            isName = (name.trim().split("\\s+").length == 3);
            if (!isName) {
                System.out.print("\nMake sure to follow the format 'FirstName MiddleName  LastName' : ");
            }
        }
        return name;
    }

    private static String getCompanyNameInput(){
        String companyName = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Please type the lead's company name: ");
        companyName = input.nextLine();
        return companyName;
    }

    private static String getLeadEmailInput(){
        boolean isEmail = false;
        String email = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Please type lead's email: ");
        while (!isEmail) {
            email = input.nextLine();
            isEmail = validateEmail(email);
            if (!isEmail) {
                System.out.print("\nPlease make sure to follow the correct format (name@domain.example): ");
            }
        }
        return email;
    }

    private static String getLeadPhoneNumberInput(){
        boolean isPhoneNumber = false;
        String phoneNumber = "";
        Scanner input = new Scanner(System.in);
        while (!isPhoneNumber) {
            System.out.print("Please type lead's phone number: ");
            phoneNumber = input.nextLine();
            if (phoneNumber.isEmpty()==false) isPhoneNumber = true;
            if (!isPhoneNumber) {
                System.out.print("\nPlease make sure to only type numbers: ");
            }
        }
        return phoneNumber;
    }

    public static String getOpportunityIdInput() {
        System.out.print("Please type the Opportunity UUID that you want to change the status: ");
        Scanner input = new Scanner(System.in);
        String someUUID = input.nextLine();
        while (!isUUID(someUUID)) {
            System.out.print("Please type the Opportunity UUID that you want to change the status: ");
                someUUID = input.nextLine();
        }
        return someUUID;
    }

    // Made by Pau

    public static int getOpportunityStatus(){
        Scanner input = new Scanner(System.in);
        boolean isValidEnum = false;
        int opportunityStatusIndex = 0;
        while(!isValidEnum) {
            System.out.print("Please input the number for the status type : \n 1-OPEN, 2-CLOSED_WON, 3-CLOSED_LOST :\n");
            try {
                opportunityStatusIndex = Integer.parseInt(input.nextLine());
                if (opportunityStatusIndex >= 1 && opportunityStatusIndex <= 3) {
                    isValidEnum = true;
                }
            } catch (NumberFormatException num) {

            }
        }
        return opportunityStatusIndex;
    }



}