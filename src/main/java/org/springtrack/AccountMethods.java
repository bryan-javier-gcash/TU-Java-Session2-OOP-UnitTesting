package org.springtrack;

public interface AccountMethods {
    void createAccount(String accountType, int accountNumber, String holderName, double initialDeposit);
    void depositMoney(double depositAmount);
    void withdrawMoney(double withdrawAmount);
    void computeInterest();
    default void displayAccount(int accountNumber, String holderName, double accountBalance) {
        // add new line
        System.out.println();
        System.out.println("---Account Information---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: " + accountBalance);
    }
}
