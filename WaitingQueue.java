package com.example.classversion_20221150;

import java.util.Arrays;
public class WaitingQueue {
    private final int length;  // Length of the queue
    private final Customer[] queue;   // Array of customer objects
    private int front = 0;  // To track front element.
    private int rear = -1;  // To track rear element.
    private int count= 0;  // To count how many elements in the queue.

    /**
     * This Special method called constructor is for create waiting queue object.
     * @param length This is for create waiting queue which has maximum length.
     */
    public WaitingQueue(int length) {
        this.length = length;
        queue = new Customer[length];
    }

    /**
     * This method id for add customer to the waiting queue.
     * @param customer this is customer object which need to add to the queue.
     */
    public void insert(Customer customer){
        if(count == length){
            System.out.println("Waiting Queue is full");
        }else{
            if(rear == length - 1){
                rear = -1;
            }
            queue[++rear] = customer;
            count++;
        }
    }

    /**
     * This method is for remove front customer object from the queue.
     */
    public void remove(){
        if(count != 0) {
            if (front == length) {
                front = 0;
            }
            queue[front++] = null;
            count--;
        }
    }

    /**
     * This method is for print the waiting queue whenever we need.
     */
    public void printWaitingQueue(){
        System.out.println(Arrays.toString(queue));
    }

    /**
     * This method is for get front element from the queue and return it.
     * @return front element in the queue.
     */
    public Customer getFirstElementInWaitingList(){
        return queue[front];
    }

    /**
     * This method is for get the length of the queue and return it.
     * @return length of the queue.
     */
    public int getLength() {
        return length;
    }

    /**
     * This is for get elements from the given index
     * @param index index which need to get
     * @return value of the given index.
     */
    public Customer get(int index){
        return queue[index];
    }
}