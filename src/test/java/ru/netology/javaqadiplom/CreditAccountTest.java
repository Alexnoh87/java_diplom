package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    //Пополнение при 0 балансе
    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    //Пополнение, когда на балансе есть сумма
    @Test
    public void shouldAddToPositiveBalanceThereAreFunds() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(1_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    //Пополнение отрицательной суммы
    @Test
    public void shouldAddNegativeNumberToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(-100);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    //Пополнение 0
    @Test
    public void shouldAddZeroToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    //Исключение для баланса
    @Test
    public void IllegalArgumentExceptionBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            CreditAccount account = new CreditAccount(
                    -7_000,
                    5_000,
                    15
            );
        });
    }

    //Исключение для процента
    @Test
    public void IllegalArgumentExceptionRate() {

        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            CreditAccount account = new CreditAccount(
                    2_000,
                    5_000,
                    -15
            );
        });
    }

    //Исключение для кредитного лимита
    @Test
    public void IllegalArgumentExceptionCreditLimit() {

        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            CreditAccount account = new CreditAccount(
                    2_000,
                    -5_000,
                    15
            );
        });
    }

    //Покупка при достаточном балансе на счете
    @Test
    public void shouldPayToSufficient() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(3000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    //Покупка при достаточном лимите (баланс и + кредитный лимит)
    @Test
    public void shouldPayToInsufficient() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(6000);

        Assertions.assertEquals(-1_000, account.getBalance());
    }

    //Покупка на 0
    @Test
    public void shouldPayToNullAmount() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(0);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    //Покупка на сумму выше лимита (баланс + кредитный счет)
    @Test
    public void shouldPayLimitIsExceeded() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(12_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    //Покупка на весь лимит (баланс + кредит)
    @Test
    public void shouldPayFullLimit() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(10_000);

        Assertions.assertEquals(-5_000, account.getBalance());
    }

    //Покупка на отрицательную сумму
    @Test
    public void shouldPayNegativeAmount() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(-1_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    //Расчёт процентов на отрицательный баланс
    @Test
    public void shouldYearChangeNegativeBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(1_200);

        Assertions.assertEquals(-30, account.yearChange());
    }

    //Расчёт процентов на положительный баланс
    @Test
    public void shouldYearChangePositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(800);

        Assertions.assertEquals(0, account.yearChange());
    }

    //Расчёт процентов при нулевом балансе
    @Test
    public void shouldYearChangeNullBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(1_000);

        Assertions.assertEquals(0, account.yearChange());
    }

    // Исключение IllegalArgumentException на изменение процентной ставки в некорректное значение
    @Test
    public void shouldThrowIllegalArgumentExceptionIfNegativeRateInSetter() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    5_000,
                    5_000,
                    15
            );

            account.setRate(-15);
        });
    }
}