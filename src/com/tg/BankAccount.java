package com.tg;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private String accountNumber;
    private Lock lock;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.lock = new ReentrantLock(); // don't forget to create the lock instance in the constructor
    }

    public void deposit(double amount) {
        boolean status = false; // local variables are already threadsafe
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) { // waiting for the lock for 1 second
                try {
                    balance += amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock in 1 second");
            }
        } catch (InterruptedException e) {
        }
        System.out.println("Deposit status = " + status);
    }

    public void withdraw(double amount) {
        boolean status = false; // local variables are already threadsafe
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) { // waiting for the lock for 1 second
                try {
                    balance -= amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock in 1 second");
            }
        } catch (InterruptedException e) {
        }
        System.out.println("Withdraw status = " + status);
    }

    public String getAccountNumber() {
        return accountNumber; // no need to synchronize
    }

    public void printAccountNumber() {
        System.out.println("Account number = " + accountNumber); // no need to synchronize
    }
}
