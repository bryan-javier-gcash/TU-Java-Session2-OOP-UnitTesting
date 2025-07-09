package org.springtrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingsTest {
    private Savings savings;

    @BeforeEach
    void setup() {
        savings = new Savings();
    }

    @Test
    @DisplayName("Verify Account Creation")
    void shouldCreateAccountSuccessfully() {
        savings.createAccount(savings.getAccountType(), 1000, "Bryan Javier", 1000);
        boolean actual = savings.isAccountCreated;

        assertTrue(actual);
    }

    @Test
    @DisplayName("Test Deposit and Withdraw Accuracy")
    void shouldDepositAndWithdrawAccurately() {
        savings.createAccount(savings.getAccountType(), 1000, "Bryan Javier", 1000);
        savings.depositMoney(100);
        savings.withdrawMoney(75);

        double actual = savings.getAccountBalance();

        assertEquals(1025, actual);
    }

    @Test
    @DisplayName("Validate Interest Computation")
    void isInterestComputationCorrect() {
        savings.createAccount(savings.getAccountType(), 1000, "Bryan Javier", 1000);
        // interest for savings account is 0.015
        // computation is: 1000 * 0.015 = 15
        savings.computeInterest();

        // expected output, computed interest is added to account balance
        // e.g. 1000 + 15 = 1015
        assertEquals(1015 ,savings.getAccountBalance());
    }

    @Test
    @DisplayName("Deposit an Invalid Amount")
    void depositInvalidAmount() {
        savings.createAccount(savings.getAccountType(), 1000, "Bryan Javier", 1000);
        savings.depositMoney(-1);

        assertEquals(1000, savings.getAccountBalance());
    }

    @Test
    @DisplayName("Withdraw a Valid Amount")
    void withdrawValidAmount() {
        savings.createAccount(savings.getAccountType(), 1000, "Bryan Javier", 1000);
        savings.withdrawMoney(100);

        assertEquals(900, savings.getAccountBalance());
    }

    @Test
    @DisplayName("Withdraw an amount that exceeds the balance")
    void withdrawAmountExceedingBalance() {
        savings.createAccount(savings.getAccountType(), 1000, "Bryan Javier", 1000);
        savings.withdrawMoney(2000);

        assertEquals(1000, savings.getAccountBalance());
    }

    @Test
    @DisplayName("Test creation of bank accounts without deposits")
    void creationOfBankAccountWithoutDeposits () {
        boolean isAccountCreated;
        savings.createAccount(savings.getAccountType(), 1000, "Bryan Javier", 0);

        if (savings.getAccountHolderName() == null && savings.getAccountNumber() == 0) {
            isAccountCreated = false;
        } else {
            isAccountCreated = true;
        }

        assertFalse(isAccountCreated);
    }

    @Test
    @DisplayName("Print Bank Account Details")
    void printBankAccountDetails() {
        savings.createAccount(savings.getAccountType(), 1000, "Bryan Javier", 1000);
        savings.displayAccount(savings.getAccountNumber(),savings.getAccountHolderName(),savings.getAccountBalance());

    }

    @Test
    @DisplayName("Withdraw Amount is equal to Zero")
    void withdrawAmountIsEqualToZero () {
        savings.createAccount(savings.getAccountType(), 1000, "Bryan Javier", 1000);
        savings.withdrawMoney(0);

        assertEquals(1000, savings.getAccountBalance());
    }
}