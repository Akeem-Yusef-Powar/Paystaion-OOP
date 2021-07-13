        package edu.temple.cis.paystation;
        import java.util.Scanner;

        public class Main {

            public static void main(String[] args) throws IllegalCoinException {
                Scanner in = new Scanner(System.in);
                int option;
                PayStationImpl ps = new PayStationImpl();

                while (true) { // Paystation is available 24/7
                    System.out.println("Welcome,What would you like");
                    System.out.println("1:Deposit Coins\n" + "2:Display Balance\n" + "3:Buy Ticket\n" + "4:Cancel");

                    option = in.nextInt();

                    if (option == 1234) { // admin options not visible unless code is put in
                        System.out.println("5:Empty\n" + "6:Change Rates");
                        option = in.nextInt();
                    }

                    switch (option) {
                        case 1: {
                            System.out.println("Please coins individually in the slot below");
                            int x = in.nextInt();
                            while (x != 1) {
                                if (x == 5 || x == 10 || x == 25) {
                                    ps.addPayment(x);
                                } else {
                                    System.out.println("Invalid input, please try again");
                                }
                                System.out.println("Please deposit next coin, press 1 to go back");
                                x = in.nextInt();
                            }
                            break;
                        }
                        case 2: {
                            if (ps.readDisplay() == 6 || ps.readDisplay() == 7) { // if saturday or sunday. only altRate 2 will return these values
                                System.out.println("Today is free, please press cancel to retrieve money");
                            } else {
                                System.out.println("Currently ticket time: " + ps.readDisplay() + " Minutes");
                            }
                            break;
                        }
                        case 3: {
                            if(ps.boughtSoFar() ==0){
                            System.out.println("You have not deposited any money. Please deposit coins and try again.");
                            }else {
                                ps.buy();
                            }
                            break;
                        }
                        case 4: {
                            System.out.println("Returned Coins: " + ps.cancel());
                            break;
                        }
                        case 5: {
                            System.out.println(ps.empty());
                            break;
                        }
                        case 6: {
                            System.out.println("Change to what rate?\n" + "1:Liner 1\n" + "2:Liner 2\n" + "3:Progr essive \n" + "4:Alternate 1\n" + "5:Alternate 2");
                            option = in.nextInt();
                            while (option < 1 || option > 5) {
                                System.out.println("Invalid rate. Please chose again");
                                System.out.println("Change to what rate?\n" + "1:Liner 1\n" + "2:Liner 2\n" + "3:Progressive \n" + "4:Alternate 1\n" + "5:Alternate 2");
                                option = in.nextInt();
                            }
                            ps.changeRates(option);
                            break;
                        }
                        case 9876: { // regular users should not know how to turn off the PayStation
                            System.out.println("System shutting down");
                            System.exit(0);
                        }

                        default:
                            System.out.println("Error: invalid input, Please chose again");
                    }
                }
            }
        }
