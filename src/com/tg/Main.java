package com.tg;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345-678", 1000);

//      first way of creating threads

//        Thread th1 = new Thread() {
//            public void run() {
//                account.deposit(300.00);
//                account.withdraw(50.00);
//            }
//        };
//
//        Thread th2 = new Thread() {
//            public void run() {
//                account.deposit(203.75);
//                account.withdraw(100.00);
//            }
//        };


//        second way of creating threads

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(300.00);
                account.withdraw(50.00);
            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(203.75);
                account.withdraw(100.00);
            }
        });

        th1.start();
        th2.start();

    }
}