package com.mybanksystem.exceptions;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(double amount) {
        super(String.format("You can not deposit %.2f$. After the bank fee's you would have negative balance in your account.", amount));
    }
    public InsufficientFundsException(double balance, double amount) {
        super(String.format("Your current balance is: %.2f\nCan not withdraw %.2f$", balance, amount));
    }

    public InsufficientFundsException() {
        super("Can not transfer 0.00$");
    }

}
