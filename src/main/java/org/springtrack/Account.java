package org.springtrack;

public abstract class Account implements AccountMethods {
    private String accountType;
    private int accountNumber;
    private String accountHolderName;
    private double accountBalance;

    protected boolean isAccountCreated;

    public Account() {}

    @Override
    public void createAccount(String accountType, int accountNumber, String holderName, double initialDeposit) {
        if (initialDeposit > 0 && !accountType.isBlank() && accountNumber != 0 && !holderName.isBlank()) {
            this.accountType = accountType;
            this.accountNumber = accountNumber;
            this.accountHolderName = holderName;
            this.accountBalance = initialDeposit;

            System.out.println("Account Created Successfully!");
            this.isAccountCreated = true;
        } else {
            System.out.println("Error encountered, try again.");
        }

    }

    @Override
    public void depositMoney(double depositAmount) {
        if (depositAmount <= 0) {
            System.out.println("Please make sure Deposit Amount is Greater than 0");
        } else {
            accountBalance += depositAmount;
        }
    }

    @Override
    public void withdrawMoney(double withdrawAmount) {
        if (withdrawAmount <= 0) {
            System.out.println("Please make sure Withdraw Amount is Greater than 0");
        } else if (accountBalance < withdrawAmount){
            System.out.println("Insufficient Balance!");
        }
        else {
            accountBalance -= withdrawAmount;
            System.out.println("Withdrawal Successful!");
        }
    }

    @Override
    public void computeInterest() {
        double interestRateForAccountType;
        if (accountType.equalsIgnoreCase("savings")) {
            interestRateForAccountType = 0.015;
        } else {
            interestRateForAccountType = 0.020;
        }

        System.out.println("Computing Interest for Account " + accountNumber);
        double interestEarned = accountBalance * interestRateForAccountType;
        accountBalance += interestEarned;

        System.out.println("Interest earned: " + interestEarned);
        System.out.println("New Balance: " + accountBalance);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }
}
