package com.example.classversion_20221150;

public class Customer {
    private final String firstName;  // To store customer first name.
    private final String lastName;  // To store customer last name.
    private final int burgerAmount;  // To store burger amount.

    /**
     * Implementing constructor.
     * @param firstName customer first name.
     * @param lastName  customer last name.
     * @param burgerAmount burger amount.
     */
    public Customer(String firstName, String lastName, int burgerAmount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.burgerAmount = burgerAmount;
    }

    /**
     * This is for get first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This is for get last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This is for get burger amount
     * @return burger amount
     */
    public int getBurgerAmount() {
        return burgerAmount;
    }

    /**
     * This method is for print customer object. When customer object prints this method will
     * execute.
     * @return customer first name.
     */
    @Override
    public String toString() {
        return firstName;
    }
}
