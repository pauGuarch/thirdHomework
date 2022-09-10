    package com.ironhack.crm.controller;

    import com.ironhack.crm.dao.manager.implementation.AccountManagerImpl;
    import com.ironhack.crm.domain.classes.CRM;
    import com.ironhack.crm.utils.Utils;
    import com.ironhack.crm.utils.UtilsUserInputs;
    import com.ironhack.crm.view.CRMView;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;

    import java.io.IOException;
    import java.util.Scanner;

    @Component
    public class CRMController {
        private CRMView crmView;
        @Autowired
        private CRM crm;
        private Long count;

        public void runCRM() {
            initializeCRM();
            runWindowsHandler();
        }

        private void initializeCRM() {
            crmView = new CRMView();
        }


        private void runWindowsHandler() {
            String option = showMenu("menu-options");
            while(!option.equals("EXIT")) {
                option = showMenu(option);
            }
            exitCRM();
        }

        private void exitCRM() {
            System.exit(0);
        }
        private  String showMenu(String menu) {
            String option = "";
            Integer salesRepId;
            try {
                crmView.showMenu(menu);
                    switch (menu) {
                        case "menu-options":
                            System.out.println("Please introduce a valid command:");
                            String key = new Scanner(System.in).nextLine();
                            while (!key.equals("new lead") && !key.equals("lookup lead") && !key.equals("show leads") && !key.equals("convert")
                                    && !key.equals("show opportunities") && !key.equals("close-won")&& !key.equals("new salesrep")
                                    && !key.equals("show salesreps") && !key.equals("close-lost")  && !key.equals("report close-won by salesrep")
                                    && !key.equals("report close-lost by salesrep")  && !key.equals("report open by salesrep") &&
                                    !key.equals("mean employee count") && !key.equals("max employee count") && !key.equals("min employee count")
                                    && !key.equals("EXIT") && !key.equals("BACK")) {
                                System.out.println("Please insert a valid command:");
                                key = new Scanner(System.in).nextLine();
                            }
                            switch (key.toLowerCase()){
                                case "new salesrep":
                                    System.out.println("\nYou are about to create a new SalesRep, read carefully the instructions.\n");
                                    crm.createNewSalesRep(UtilsUserInputs.getUserSalesRepInput());
                                    System.out.println("\n!! SalesRep created successfully !!\n");
                                    option = "menu-options";
                                    break;
                                case "show salesreps":
                                    Utils.showSalesReps(crm.checkSalesReps());
                                    option = "menu-options";
                                    break;
                                case "new lead":
                                    System.out.println("\nYou are about to create a new Lead, read carefully the instructions.\n");
                                    crm.createNewLead(UtilsUserInputs.getUserLeadInput(crm.lookUpSalesRep(UtilsUserInputs.getGetSalesRepId())));
                                    System.out.println("\n!! Lead created successfully !!\n");
                                    option = "menu-options";
                                    break;

                                case "lookup lead":
                                    Utils.showLead(crm.lookUpLead(Integer.parseInt(UtilsUserInputs.getLeadIdInput())));
                                    option = "menu-options";
                                    break;
                                case "show leads":
                                    Utils.showLeads(crm.checkLeads());
                                    option = "menu-options";
                                    break;
                                case "convert":
                                    if (UtilsUserInputs.getNewAccount()) {
                                        try {
                                            crm.convertLeadToOpportunity(crm.lookUpLead(Integer.parseInt(UtilsUserInputs.getLeadIdInput())),
                                                    UtilsUserInputs.createProduct(), UtilsUserInputs.getProductQuantityInput(),
                                                    UtilsUserInputs.getAccountIndustryInput(), UtilsUserInputs.getEmployeesNumberInput(),
                                                    UtilsUserInputs.getAccountCityInput(), UtilsUserInputs.getAccountCountryInput());
                                        }catch (RuntimeException e){
                                            System.out.println("DATA-ERROR(The lead Id that you introduced was not found)");
                                        }
                                    }else{
                                        crm.convertLeadToOpportunity(UtilsUserInputs.getLeadIdInput());
                                    }
                                    option = "menu-options";
                                break;
                                case "show opportunities":
                                    Utils.showOpportunities(crm.checkOpportunities());
                                    option = "menu-options";
                                break;
                                case "close-lost":
                                    crm.editOpportunityStatus(UtilsUserInputs.getOpportunityIdInput(), 3);
                                    option = "menu-options";
                                break;
                                case "close-won":
                                    crm.editOpportunityStatus(UtilsUserInputs.getOpportunityIdInput(), 2);
                                    option = "menu-options";
                                break;
                                case "report close-won by salesrep":
                                    salesRepId = UtilsUserInputs.getGetSalesRepId();
                                    Utils.showSalesRepsAndStatus(crm.lookUpSalesRep(salesRepId),1 ,crm.countByStatusAndSalesRep(salesRepId, 1));
                                    option = "menu-options";
                                break;
                                case "report close-lost by salesrep":
                                    salesRepId = UtilsUserInputs.getGetSalesRepId();
                                    Utils.showSalesRepsAndStatus(crm.lookUpSalesRep(salesRepId),2 ,crm.countByStatusAndSalesRep(salesRepId, 2));
                                    option = "menu-options";
                                break;
                                case "report open by salesrep":
                                    salesRepId = UtilsUserInputs.getGetSalesRepId();
                                    Utils.showSalesRepsAndStatus(crm.lookUpSalesRep(salesRepId),0 ,crm.countByStatusAndSalesRep(salesRepId, 0));
                                    option = "menu-options";
                                    break;
                                    case "mean employee count":
                                        count = crm.getAccountsEmployeesMean();
                                        System.out.println("\nThe mean of employees in all the accounts is: ");
                                        System.out.println(count);
                                    option = "menu-options";
                                break;
                                case "max employee count":
                                        count = crm.getAccountsEmployeesMax();
                                        System.out.println("\nThe maximum of employees in all the accounts is: ");
                                        System.out.println(count);
                                    option = "menu-options";
                                break;
                                case "min employee count":
                                        count = crm.getAccountsEmployeesMin();
                                        System.out.println("\nThe minimum of employees in all the accounts is: ");
                                        System.out.println(count);
                                    option = "menu-options";
                                break;

                            default:
                                option = key;
                            }
                            clearConsole();
                            break;


                }
            } catch (IOException e) {
                exitCRM();
                e.printStackTrace();
            }
            return option;
        }

        private void clearConsole() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
