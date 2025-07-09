package org.springtrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingTest {
    Checking checking;

    @BeforeEach
    void setup() {
        checking = new Checking();
    }

    @Test
    @DisplayName("Verify Account Creation")
    void shouldCreateAccountSuccessfully() {
        checking.createAccount(checking.getAccountType(), 1000, "Bryan Javier", 1000);
        boolean actual = checking.isAccountCreated;

        assertTrue(actual);
    }

    @Test
    @DisplayName("Test Deposit and Withdraw Accuracy")
    void shouldDepositAndWithdrawAccurately() {
        checking.createAccount(checking.getAccountType(), 1000, "Bryan Javier", 1000);
        checking.depositMoney(100);
        checking.withdrawMoney(75);

        double actual = checking.getAccountBalance();

        assertEquals(1025, actual);
    }

    @Test
    @DisplayName("Validate Interest Computation")
    void isInterestComputationCorrect() {
        checking.createAccount(checking.getAccountType(), 1000, "Bryan Javier", 1000);
        // interest for savings account is 0.020
        // computation is: 1000 * 0.015 = 15
        checking.computeInterest();

        // expected output, computed interest is added to account balance
        // e.g. 1000 + 15 = 1015
        assertEquals(1020 ,checking.getAccountBalance());
    }

    @Test
    @DisplayName("Deposit an Invalid Amount")
    void depositInvalidAmount() {
        checking.createAccount(checking.getAccountType(), 1000, "Bryan Javier", 1000);
        checking.depositMoney(-1);

        assertEquals(1000, checking.getAccountBalance());
    }

    @Test
    @DisplayName("Withdraw a Valid Amount")
    void withdrawValidAmount() {
        checking.createAccount(checking.getAccountType(), 1000, "Bryan Javier", 1000);
        checking.withdrawMoney(100);

        assertEquals(900, checking.getAccountBalance());
    }

    @Test
    @DisplayName("Withdraw an amount that exceeds the balance")
    void withdrawAmountExceedingBalance() {
        checking.createAccount(checking.getAccountType(), 1000, "Bryan Javier", 1000);
        checking.withdrawMoney(2000);

        assertEquals(1000, checking.getAccountBalance());
    }

    @Test
    @DisplayName("Test creation of bank accounts without deposits")
    void creationOfBankAccountWithoutDeposits () {
        boolean isAccountCreated;
        checking.createAccount(checking.getAccountType(), 1000, "Bryan Javier", 0);

        if (checking.getAccountHolderName() == null && checking.getAccountNumber() == 0) {
            isAccountCreated = false;
        } else {
            isAccountCreated = true;
        }

        assertFalse(isAccountCreated);
    }

    @Test
    @DisplayName("Print Bank Account Details")
    void printBankAccountDetails() {
        checking.createAccount(checking.getAccountType(), 1000, "Bryan Javier", 1000);
        checking.displayAccount(checking.getAccountNumber(),checking.getAccountHolderName(),checking.getAccountBalance());

    }

    @Test
    @DisplayName("Initialize Account Constructor")
    void accountConstructorInitializeTest() {
        Account testAccount = new Savings();
    }

    @Test
    @DisplayName("Withdraw Amount is equal to Zero")
    void withdrawAmountIsEqualToZero () {
        checking.createAccount(checking.getAccountType(), 1000, "Bryan Javier", 1000);
        checking.withdrawMoney(0);

        assertEquals(1000, checking.getAccountBalance());
    }

}