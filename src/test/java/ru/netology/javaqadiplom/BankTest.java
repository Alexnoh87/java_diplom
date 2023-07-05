package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {

    //Перевод суммы с кредитного счёта на сберегательный счёт
    @Test
    public void shouldTransferFromCreditAccountToSavingAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                5_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                2_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(credit, saving, 2_000);

        Assertions.assertEquals(3_000, credit.getBalance());
        Assertions.assertEquals(4_000, saving.getBalance());
    }

    //Перевод суммы в рамках баланса со сберегательного счёта на кредитный счёт
    @Test
    public void shouldTransferFromSavingAccountToCreditAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                5_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                3_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(saving, credit, 1_000);

        Assertions.assertEquals(6_000, credit.getBalance());
        Assertions.assertEquals(2_000, saving.getBalance());
    }

    //Перевод всей суммы с кредитного счета, включая средства кредита
    @Test
    public void shouldTransferMaxAmountFromCreditAccountToSavingAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                5_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                2_000,
                1_000,
                15_000,
                10
        );

        trans.transfer(credit, saving, 10_000);

        Assertions.assertEquals(-5_000, credit.getBalance());
        Assertions.assertEquals(12_000, saving.getBalance());
    }

    //Перевод всей суммы со сберегательного счета на кредитный
    @Test
    public void shouldTransferMaxAmountFromSavingAccountToCreditAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                5_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                6_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(saving, credit, 5_000);

        Assertions.assertEquals(10_000, credit.getBalance());
        Assertions.assertEquals(1_000, saving.getBalance());
    }

    //Перевод 0 со сберегательного счета на кредитный
    @Test
    public void shouldTransferNullAmountFromSavingAccountToCreditAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                5_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                6_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(saving, credit, 0);

        Assertions.assertEquals(5_000, credit.getBalance());
        Assertions.assertEquals(6_000, saving.getBalance());
    }

    //Перевод 0 с кредитного счета на сберегательный
    @Test
    public void shouldTransferNullAmountFromCreditAccountToSavingAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                5_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                6_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(credit, saving, 0);

        Assertions.assertEquals(5_000, credit.getBalance());
        Assertions.assertEquals(6_000, saving.getBalance());
    }

    //Перевод с кредитного на кредитный (сам на себя)
    @Test
    public void shouldTransferFromCreditAccountToCreditAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                5_000,
                5_000,
                15
        );

        trans.transfer(credit, credit, 4_000);

        Assertions.assertEquals(5_000, credit.getBalance());
    }

    //Перевод со сберегательного на сберегательный (сам на себя)
    @Test
    public void shouldTransferFromSavingAccountToSavingAccount() {
        Bank trans = new Bank();

        SavingAccount saving = new SavingAccount(
                6_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(saving, saving, 4_000);

        Assertions.assertEquals(6_000, saving.getBalance());
    }

    //Перевод отрицательной суммы с кредитного на сберегательный счет
    @Test
    public void shouldTransferNegativeAmountFromCreditAccountToSavingAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                5_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                2_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(credit, saving, -2_000);

        Assertions.assertEquals(5_000, credit.getBalance());
        Assertions.assertEquals(2_000, saving.getBalance());
    }

    //Перевод отрицательной суммы со сберегательного на кредитный счет
    @Test
    public void shouldTransferNegativeAmountFromSavingAccountToCreditAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                6_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                3_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(saving, credit, -2_000);

        Assertions.assertEquals(6_000, credit.getBalance());
        Assertions.assertEquals(3_000, saving.getBalance());
    }

    //Перевод суммы, превышающий баланс, со сберегательного на кредитный счет
    @Test
    public void shouldTransferExceedingAmountFromSavingAccountToCreditAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                6_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                3_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(saving, credit, 4_000);

        Assertions.assertEquals(6_000, credit.getBalance());
        Assertions.assertEquals(3_000, saving.getBalance());
    }

    //Перевод суммы, превышающий баланс, с кредитного на сберегательный счет (в рамках всего лимита)
    @Test
    public void shouldTransferExceedingAmountWithoutCreditFromCreditAccountToSavingAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                5_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                3_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(credit, saving, 6_000);

        Assertions.assertEquals(-1_000, credit.getBalance());
        Assertions.assertEquals(9_000, saving.getBalance());
    }

    //Перевод суммы, превышающий общий баланс, с кредитного на сберегательный счет
    @Test
    public void shouldTransferExceedingAmountFromCreditAccountToSavingAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                6_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                3_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(credit, saving, 14_000);

        Assertions.assertEquals(6_000, credit.getBalance());
        Assertions.assertEquals(3_000, saving.getBalance());
    }

    //Перевод с кредитного счёта на сберегательный счёт (превышает макс баланс)
    @Test
    public void shouldTransferFromCreditAccountToSavingAccountMaxBalance() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                5_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                6_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(credit, saving, 5_000);

        Assertions.assertEquals(5_000, credit.getBalance());
        Assertions.assertEquals(6_000, saving.getBalance());
    }

    //Перевод со сберегательного на кредитный счёт (остается меньше мин баланса)
    @Test
    public void shouldTransferFromSavingAccountMinBalanceToCreditAccount() {
        Bank trans = new Bank();

        CreditAccount credit = new CreditAccount(
                5_000,
                5_000,
                15
        );

        SavingAccount saving = new SavingAccount(
                4_000,
                1_000,
                10_000,
                10
        );

        trans.transfer(saving, credit, 3_500);

        Assertions.assertEquals(5_000, credit.getBalance());
        Assertions.assertEquals(4_000, saving.getBalance());
    }
}