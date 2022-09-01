    package com.ironhack.crm.controller;

    import com.ironhack.crm.domain.classes.CRM;
    import com.ironhack.crm.utils.Utils;
    import com.ironhack.crm.utils.UtilsUserInputs;
    import com.ironhack.crm.view.CRMView;
    import org.apache.tomcat.util.security.Escape;

    import java.io.IOException;
    import java.util.Scanner;
    import java.util.UUID;

    import static com.ironhack.crm.utils.UtilsUserInputs.validateEmail;

    public class CRMController {
        private static CRMView crmView;
        private static CRM crm;

        public static void runCRM() {
            initializeCRM();
            runWindowsHandler();
        }

        private static void initializeCRM() {
            crm = new CRM();
            crmView = new CRMView();
        }


        private static void runWindowsHandler() {
            String option = showMenu("menu-options");
            while(!option.equals("EXIT")) {
                option = showMenu(option);
            }
            exitCRM();
        }

        private static void exitCRM() {
            System.exit(0);
        }

        private static String showMenu(String menu) {
            String option = "";
            try {
                crmView.showMenu(menu);
                    switch (menu) {
                        case "menu-options":
                            System.out.println("Please introduce a valid command:");
                            String key = new Scanner(System.in).nextLine();
                            while (!key.equals("new lead") && !key.equals("lookup lead") && !key.equals("show leads") && !key.equals("convert")
                                    && !key.equals("show opportunities") && !key.equals("close-won")&& !key.equals("new salesrep") && !key.equals("close-lost") && !key.equals("EXIT") && !key.equals("BACK")) {
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
                                case "new lead":
                                    System.out.println("\nYou are about to create a new Lead, read carefully the instructions.\n");
                                    crm.createNewLead(UtilsUserInputs.getUserLeadInput(crm.lookUpSalesRep(UtilsUserInputs.getGetSalesRepId())));
                                    System.out.println("\n!! Lead created successfully !!\n");
                                    option = "menu-options";
                                    break;

                                case "lookup lead":
                                    Utils.showLead(crm.lookUpLead(UUID.fromString(UtilsUserInputs.getLeadIdInput())));
                                    option = "menu-options";
                                    break;
                                case "show leads":
                                    Utils.showLeads(crm.checkLeads());
                                    option = "menu-options";
                                    break;
                                case "convert":
                                    crm.convertLeadToOpportunity(UtilsUserInputs.getLeadIdInput(),
                                            UtilsUserInputs.createProduct(), UtilsUserInputs.getProductQuantityInput(),
                                            UtilsUserInputs.getAccountIndustryInput(), UtilsUserInputs.getEmployeesNumberInput(),
                                            UtilsUserInputs.getAccountCityInput(), UtilsUserInputs.getAccountCountryInput(), crm.lookUpSalesRep(UtilsUserInputs.getGetSalesRepId()));

                                    option = "menu-options";
                                break;
                                case "show opportunities":
                                    Utils.showOpportunities(crm.checkOpportunities());
                                    option = "menu-options";
                                break;
                                case "close-lost":
                                    // Made by Pau
                                    crm.editOpportunityStatus(UtilsUserInputs.getOpportunityIdInput(), 3);
                                    option = "menu-options";
                                break;case "close-won":
                                    // Made by Pau
                                    crm.editOpportunityStatus(UtilsUserInputs.getOpportunityIdInput(), 2);
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

        private static void clearConsole() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
