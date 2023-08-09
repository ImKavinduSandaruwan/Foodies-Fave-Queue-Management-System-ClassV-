package com.example.classversion_20221150;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static com.example.classversion_20221150.Main.burgerInStock;  // import burger stock
import static com.example.classversion_20221150.Main.waitingList;  //import waiting list

public class FoodQueue {
    private final int length;   // length of queue object
    private final Customer[] queue;  // array of customer objects.
    private int queueIncome = 0;  // total income of the queue.

    /**
     * Implementing constructor.
     * @param length length of the array.
     */
    public FoodQueue(int length){
        this.length = length;
        queue = new Customer[this.length];
    }

    /**
     * This method is for get length of the array.
     * @return length of the array.
     */
    public int getLength() {
        return length;
    }

    /**
     * This method is for print customer object. When customer object prints this method will
     *  execute.
     * @return array in string.
     */
    @Override
    public String toString() {
        return Arrays.toString(queue);
    }

    /**
     * Method to Get value of a given index.
     * @param index index of the array.
     * @return value of the given index.
     */
    public Customer getValue(int index){
        return queue[index];
    }

    /**
     * This method is for print empty spots in the queue.
     * @param queueNumber to track current queue number.
     */
    public void emptyPositions(int queueNumber){
        System.out.print("Queue " + queueNumber + " :->  ");
        for(int i = 0; i < queue.length; i++){
            if(queue[i] == null){
                System.out.print(i+1 + ", ");
            }
        }
    }

    /**
     * This method is for add customer to the queue.
     * @param customer customer object that we want to add to the queue.
     * @param index index of the queue that we want to add customer object.
     */
    public void addCustomer(Customer customer, int index){
        queue[index] = customer;
        System.out.println(queue[index].getFirstName() + " Successfully added to the queue.");
    }

    /**
     * This method is for remove customer from the queue.
     * @param position position that we want to remove the customer object.
     * After remove customers other customer in behind shift forward by one. then add customer
     * from the waiting list.
     */
    public void removeCustomer(int position){
        try {
            if (position > queue.length || position <= 0) {
                System.out.println("Invalid Position!!");
            } else {
                System.out.println(queue[position - 1].getFirstName() + " successfully Removed.");
                queue[position - 1] = null;

                //Shift customers forward bt one.
                for(int i = position-1; i < queue.length; i++){
                    try {
                        queue[i] = queue[i + 1];
                    }catch (ArrayIndexOutOfBoundsException e){
                        queue[i] = null;
                        addCustomerFromWaitingQueue();  // add customer from waiting list.
                    }
                }
            }
        }catch(NullPointerException e){
            System.out.println("No One in that position to remove.\n");
        }
    }

    /**
     * This method is for remove served customers form the queue.
     * This will remove first element and the shift customers by one. Also add customer form
     * the waiting list
     * @param customer customer object that want to remove.
     */
    public void removeServedCustomer(Customer customer){

        if(queue[0] != null) {
            burgerInStock -= customer.getBurgerAmount();
            calculateQueueIncome(queue[0]);
        }

        queue[0] = null;

        //Shift forward by one
        for(int i = 0; i < queue.length; i++){
            try {
                queue[i] = queue[i + 1];
            }catch (ArrayIndexOutOfBoundsException e){
                queue[i] = null;
                addCustomerFromWaitingQueue();  // add customer from waiting list.
            }
        }
    }

    /**
     * This method is for save customer details to the text file.
     */
    public void saveToTextFile(){
        try {
            FileWriter myFile = new FileWriter("CustomerData.txt", true);
            for(int i = 0; i < queue.length; i++){
                if(queue[i] != null){
                    myFile.write(">>>> First name:- " + queue[i].getFirstName() +
                            ", Last name:- " + queue[i].getLastName() + ", No of burgers:- " +
                            queue[i].getBurgerAmount() + ".\n");
                }
            }
            myFile.close();
        } catch (IOException ioe){
            System.out.println(">>>>> An error occurred.");
            ioe.printStackTrace();     //https://www.w3schools.com/java/java_files.asp
        }
    }
    /**
     * This method use bubble sort method to sort customers name.
     * @param currentQueue this will check current queue number is sorting.
     */
    public void sortCustomersName(int currentQueue){
        int bottom = queue.length - 2;
        Customer temp;
        boolean isSorted = true;

        while(isSorted){
            isSorted = false;
            for(int i = 0; i <= bottom; i++){
                if(queue[i+1] != null){
                    if(queue[i].getFirstName().compareTo(queue[i+1].getFirstName()) > 0){
                        temp = queue[i];
                        queue[i] = queue[i+1];
                        queue[i+1] = temp;
                        isSorted = true;
                    }
                }
            }
            bottom--;
        }
        System.out.print("Queue " + currentQueue + ":- ");
        for(Customer customer : queue){
            if(customer != null){
                System.out.print(customer.getFirstName() + ", ");
            }
        }
        System.out.println();
    }

    /**
     * This method is for calculate income of the queue.
     * @param servedCustomer take burger amount of the served customer.
     */
    public void calculateQueueIncome(Customer servedCustomer){
        queueIncome += servedCustomer.getBurgerAmount() * 650;
    }

    /**
     * This method is for print queue income.
     * @param queueNumber to tack the current queue.
     */
    public void printQueueIncome(int queueNumber){
        System.out.println("Queue " + queueNumber + " total income : $" + queueIncome);
    }

    /**
     * This method is for add customer from waiting list.
     */
    public void addCustomerFromWaitingQueue(){
        try {
            queue[length - 1] = waitingList.getFirstElementInWaitingList();
            waitingList.remove();
        }catch (IndexOutOfBoundsException e) {
            //
        }
    }
}