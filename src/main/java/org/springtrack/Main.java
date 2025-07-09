package org.springtrack;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static boolean isAccountNumberExisting(ArrayList<Account> bankAccounts,int accountNumber){
        boolean isExisting = false;
        for (Account account: bankAccounts){
            if (account.getAccountNumber() == accountNumber) {
                isExisting = true;
                break;
            }
        }
        return isExisting;
    }

    public static void main(String[] args){
        Scanner inputReader = new Scanner(System.in);
        ArrayList<Account> bankAccounts = new ArrayList<>();
        int choice = 0;

        while(choice != 6) {
            System.out.println("=== Welcome to MyBank ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Compute Interest");
            System.out.println("5. Display Account(s)");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");
            choice = inputReader.nextInt();
            int accountNumber;

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Type (Savings/Checking): ");
                    String accountType = inputReader.next();
                    System.out.print("Enter Account Number: ");
                    accountNumber = inputReader.nextInt();

                    // skip next line for input to avoid input mismatch
                    inputReader.nextLine();

                    if (isAccountNumberExisting(bankAccounts, accountNumber)) {
                        System.out.println("Account Number Already Exists! Please Try Again.");
                        // add new line
                        System.out.println();
                        break;
                    }

                    System.out.print("Enter Holder Name: ");

                    String holderName = inputReader.nextLine();
                    System.out.print("Initial Deposit: ");
                    double initialDeposit = inputReader.nextDouble();

                    if (accountType.equalsIgnoreCase("savings")) {
                        Savings savingsAccount = new Savings();
                        savingsAccount.createAccount(
                                savingsAccount.getAccountType(),
                                accountNumber,
                                holderName,
                                initialDeposit
                        );
                        bankAccounts.add(savingsAccount);

                    } else if (accountType.equalsIgnoreCase("checking")){
                        Checking checkingAccount = new Checking();
                        checkingAccount.createAccount(
                                checkingAccount.getAccountType(),
                                accountNumber,
                                holderName,
                                initialDeposit
                        );
                        bankAccounts.add(checkingAccount);

                    } else {
                        System.out.println("Invalid Input, try again.");
                    }

                    // add new line
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    accountNumber = inputReader.nextInt();
                    System.out.print("Deposit Amount: ");
                    double depositAmount = inputReader.nextDouble();
                    boolean isMoneyDeposited = false;

                    for (Account account: bankAccounts) {
                        if (account.getAccountNumber() == accountNumber) {
                            account.depositMoney(depositAmount);
                            isMoneyDeposited = true;
                        }
                    }

                    if (isMoneyDeposited) {
                        System.out.println("Deposit Successful!");
                    } else {
                        System.out.println("Deposit Unsuccessful, try again.");
                    }

                    // add new line
                    System.out.println();
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    accountNumber = inputReader.nextInt();
                    System.out.print("Withdraw Amount: ");
                    double withdrawAmount = inputReader.nextDouble();

                    for (Account account: bankAccounts) {
                        if (account.getAccountNumber() == accountNumber) {
                            account.withdrawMoney(withdrawAmount);
                        }
                    }
                    // add new line
                    System.out.println();
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    accountNumber = inputReader.nextInt();

                    for (Account account: bankAccounts) {
                        if (account.getAccountNumber() == accountNumber) {
                            account.computeInterest();
                        }
                    }
                    // add new line
                    System.out.println();

                    break;

                case 5:
                    for (Account account : bankAccounts) {
                        account.displayAccount(
                                account.getAccountNumber(),
                                account.getAccountHolderName(),
                                account.getAccountBalance()
                        );
                    }

                    // add new line
                    System.out.println();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Wrong choice, try again.");
                    //add new line
                    System.out.println();
                    break;
            }
        }


    }
}