package com.tg;

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
        lock.lock();
        try {
            balance += amount;
        } finally { // we want to guarantee that we call the unlock() method
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            balance -= amount;
        } finally { // we want to guarantee that we call the unlock() method
            lock.unlock();
        }
    }

    public String getAccountNumber() {
        return accountNumber; // no need to synchronize
    }

    public void printAccountNumber() {
        System.out.println("Account number = " + accountNumber); // no need to synchronize
    }
}
