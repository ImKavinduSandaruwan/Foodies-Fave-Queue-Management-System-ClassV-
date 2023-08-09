package com.example.classversion_20221150;

import javafx.application.Application;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
 * Any code taken from other sources is referenced within my code solution.
 * Main Method is the starting point of the execution.
 * @author Kavindu Sandaruwan 20221150 w1999503
 */
public class Main {
    public static FoodQueue[] allQueues = {new FoodQueue(2),
            new FoodQueue(3), new FoodQueue(5)};   // Array of Food Queue objects.
    public static WaitingQueue waitingList = new WaitingQueue(10);  // Waiting queue object.
    public static  int burgerInStock = 50;   // Burgers in the stock.

    public static void main(String[] args) {
        System.out.println("\tFoodies Fave Food center" + "\n" + "_".repeat(32) + "\n");
        Scanner scanner = new Scanner(System.in);  // Scanner object to take user inputs.
        boolean shouldContinue = true;   //Boolean value to control main loop.

        while(shouldContinue){
            printMainMenu();     // This is method called printMainMenu
            System.out.print("\nEnter option:- ");
            String userOption = scanner.nextLine().toUpperCase();

            if(userOption.equals("999") || userOption.equals("EXT")){
                shouldContinue = false;
                System.out.print("Thank you for using our Application.");
            }else{
                switch (userOption){    //https://docs.oracle.com/en/java/javase/17/language/switch-expressions
                    case "100", "VFQ" -> viewAllQueues();
                    case "101", "VEQ" -> viewEmptyQueues();
                    case "102", "ACQ" -> addCustomerToQueue();
                    case "103", "RCQ" -> removeCustomerFromQueue();
                    case "104", "PCQ" -> removeServedCustomer();
                    case "105", "VCS" -> viewCustomersSortedAlphabeticalOrder();
                    case "106", "SPD" -> storeProgramDataIntoFile();
                    case "107", "LPD" -> loadDataFromTxtFile();
                    case "108", "STK" -> viewRemainingBurgers();
                    case "109", "AFS" -> addBurgerToStock();
                    case "110", "IFQ" -> incomeOfEachQueue();
                    case "111", "VWL" -> viewWaitingList();
                    case "112", "GUI" -> GUI();
                    default -> {
                        System.out.println(">>>>> Invalid input. Please check the main menu" +
                                " and try again.\n");
                        continue;
                    }
                }
                System.out.println(">>>>> Press Enter key to back to the main menu:");
                scanner.nextLine();
                System.out.println("_".repeat(60) + "\n");
            }
        }
    }

    /**
     * This method is print main menu of the program
     */
    public static void printMainMenu(){
        System.out.println("""
                100 or VFQ: View all Queues.
                101 or VEQ: View all Empty Queues.
                102 or ACQ: Add customer to a Queue.
                103 or RCQ: Remove a customer from a Queue.
                104 or PCQ: Remove a served customer.
                105 or VCS: View Customers Sorted in alphabetical order.
                106 or SPD: Store Program Data into file.
                107 or LPD: Load Program Data from file.
                108 or STK: View Remaining burgers Stock.
                109 or AFS: Add burgers to Stock.
                110 or IFQ: Income of each Queue.
                111 or VWL: View Waiting List.
                112 or GUI: GUI.
                999 or EXT: Exit the Program.""");
    }

    /**
     * This method is for print all queues.
     * 0 - occupied.
     * X - not occupied.
     * This method uses nested for loops to print all the queues.
     */
    public static void viewAllQueues(){
        System.out.println("""
               \n *****************
                *    Cashiers   *
                *****************""");

        for(int i = 0; i < allQueues[2].getLength(); i++){
            for(FoodQueue queue : allQueues){
                try {
                    if (queue.getValue(i) == null) {   // Get value method returns the value of that index.
                        System.out.print("   X  ");
                    } else {
                        System.out.print("   0  ");
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.print("      ");
                }
            }
            System.out.println();
        }
    }

    /**
     * This method is for print all the empty spots in each queue.
     * This method use emptyPositions(currentQueueNumber) method to check. That method
     * is inside the food queue class.
     */
    public static void viewEmptyQueues(){
        int currentQueueNumber = 1;  // To track current queue number. Tts for printing purpose.
        for(FoodQueue queue : allQueues){
            queue.emptyPositions(currentQueueNumber);
            currentQueueNumber+=1;
            System.out.println("\n");
        }
    }

    /**
     * This method is for add new customer to the queue. If all queues are full this method
     * adds customer to the waiting list.
     */
    public static void addCustomerToQueue(){
        if(burgerInStock <= 0){
            System.out.println("Burgers out of stock.");
            addBurgerToStock();
            return;
        }
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("\nEnter Fist Name: ");
            String firstName = scanner.nextLine().toLowerCase();

            System.out.print("Enter Second Name: ");
            String secondName = scanner.nextLine().toLowerCase();

            System.out.print("How many Burgers do you need: ");
            int burgerAmount = scanner.nextInt();

            Customer customer = new Customer(firstName, secondName, burgerAmount);

            for (int i = 0; i < allQueues[2].getLength(); i++) {  //
                for (FoodQueue queue : allQueues) {
                    try {
                        if (queue.getValue(i) == null) {
                            queue.addCustomer(customer, i);
                            return;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        // Do Nothing.
                    }
                }
            }
            System.out.println("Sorry Queues are full. Now you are in the waiting list");
            waitingList.insert(customer);  // If queues are full add customer to the waiting list.
        } catch (InputMismatchException e){
            System.out.println("Characters are not allowed.\n");
            addCustomerToQueue();  // if user inputs invalid option call this method again to give another chance.
        }
    }

    /**
     * This method is for remove un served customer from given queue and spot.
     * This method use removeCustomer(position) method to remove customer. That method is in
     * food queue class.
     */
    public static void removeCustomerFromQueue(){
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Queue No:- ");
            int queueNumber = scanner.nextInt();

            System.out.print("Enter Which Position in the Queue:- ");
            int position = scanner.nextInt();

            switch (queueNumber) {
                case 1 -> allQueues[0].removeCustomer(position);  // Remove customer method.
                case 2 -> allQueues[1].removeCustomer(position);
                case 3 -> allQueues[2].removeCustomer(position);
                default -> {
                    System.out.println("Invalid queue number!\n");
                    removeCustomerFromQueue(); // if user inputs invalid option call this method again.
                }
            }
        } catch (InputMismatchException e){
            System.out.println("Characters are not allowed.\n");
        }
    }

    /**
     * This method is for remove served customers form the queues. This will loop through all the
     * queues and then call removeServedCustomer() method to remove that customer. removeServedCustomer
     * method is inside food queue method.
     */
    public static void removeServedCustomer(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter queue number: ");
        int queueNumber = scanner.nextInt();

        switch (queueNumber) {
            case 1 -> allQueues[0].removeServedCustomer(allQueues[0].getValue(0));
            case 2 -> allQueues[1].removeServedCustomer(allQueues[1].getValue(0));
            case 3 -> allQueues[2].removeServedCustomer(allQueues[2].getValue(0));
            default -> System.out.println("Invalid queue number.\n");
        }

        System.out.println("""
                Successfully removed served customers.
                The customers behind shift forward by one,
                who was on the waiting list joined the queue.
                """);
    }

    /**
     * This method is for sort customer names in alphabetical order. It will loop through
     * all queues then call sortCustomer method which is inside food queue.
     */
    public static void viewCustomersSortedAlphabeticalOrder(){
        int currentQueueNumber = 1;  // This for track the current queue number.
        for(FoodQueue queue : allQueues){
            queue.sortCustomersName(currentQueueNumber);
            currentQueueNumber+=1;
        }
        //https://www.geeksforgeeks.org/difference-between-for-loop-and-enhanced-for-loop-in-java/
    }

    /**
     * This method is for store data into text file. This will loop through queues
     * then call saveToTextFile method inorder to do that.
     */
    public static void storeProgramDataIntoFile(){
        for(FoodQueue queue : allQueues){
            queue.saveToTextFile();
        }
        System.out.println("Successfully Saved to Txt file.");
        //https://www.geeksforgeeks.org/difference-between-for-loop-and-enhanced-for-loop-in-java/
        //https://www.w3schools.com/java/java_files.asp
    }

    /**
     * This method is for load data from text file.
     */
    public static void loadDataFromTxtFile(){
        try {
            File myFile = new File("CustomerData.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(">>>> An error occurred.");
            e.printStackTrace();
            //https://www.w3schools.com/java/java_files.asp
        }
    }

    /**
     * This method is for display remaining burger amount in the stock.
     */
    public static void viewRemainingBurgers(){
        System.out.println("There are " + burgerInStock + " burgers left in the stock.");
    }

    /**
     * This method is for add burgers to the stock.
     */
    public static void addBurgerToStock(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many burgers do you need to add:- ");
        try {
            burgerInStock += scanner.nextInt();  //increase burger stock by given number.
        }catch (InputMismatchException e){
            System.out.println("Invalid burger amount detected. Please try again\n");
            addBurgerToStock();
        }
    }

    /**
     * This method is for calculate income of each queue.
     * This will loop through queues and then call printQueueIncome method to calculate
     * the income and the print all the information.
     */
    public static void incomeOfEachQueue(){
        int currentQueue = 1;
        for(var queue : allQueues){
            queue.printQueueIncome(currentQueue);
            currentQueue+=1;
        }
    }

    /**
     * This method is for print waiting list. This is an additional function.
     */
    public static void viewWaitingList(){
        waitingList.printWaitingQueue();
        System.out.println();
    }

    /**
     * This method is responsible for handle all the GUI part.
     * This method calls MyApplication class to activate java fx GUI.
     * This method can be executed only one time.
     */
    public static void GUI(){
        try {
            Application.launch(MyApplication.class);
        }catch (IllegalStateException e){
            System.out.println("Sorry only one time can be operated.\n");
        }
    }
}