package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    //-------ТЕСТЫ ИНИЦИАЛИЗАЦИИ НОВОГО СБЕРЕГАТЕЛЬНОГО СЧЕТА------------------

    // Негативный тест: если минимальный баланс больше максимального,
    // должно вызываться исключение IllegalArgumentException
    @Test
    public void shouldThrowIllegalArgumentExceptionIfMinMoreThanMax() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    500,
                    5
            );
        });
    }

    // Негативный тест: если начальный баланс < 0,
    // должно вызываться исключение IllegalArgumentException
    @Test
    public void shouldThrowIllegalArgumentExceptionIfInitBalanceIsNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    -100,
                    1_000,
                    5_000,
                    5
            );
        });
    }

    // Негативный тест: если ставка < 0,
    // должно вызываться исключение IllegalArgumentException
    @Test
    public void shouldThrowIllegalArgumentExceptionIfRateIsNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    5_000,
                    -5
            );
        });
    }

    // Негативный тест: если нач.баланс < минимального,
    // должно вызываться исключение IllegalArgumentException
    @Test
    public void shouldThrowIllegalArgumentExceptionIfInitBalanceLessMinimum() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    200,
                    1_000,
                    5_000,
                    5
            );
        });
    }

    // Негативный тест: если нач.баланс > максимального,
    // должно вызываться исключение IllegalArgumentException
    @Test
    public void shouldThrowIllegalArgumentExceptionIfInitBalanceMoreMaximum() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    20_000,
                    1_000,
                    5_000,
                    5
            );
        });
    }

//    // Позитивный тест: со всеми валидными значениями
//    @Test
//    public void shouldInitSavingAccountIfValid() {
//        SavingAccount account = new SavingAccount(
//                2_000,
//                1_000,
//                10_000,
//                5
//        );
//        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
//    }


    //-------ТЕСТЫ ОПЛАТЫ СО СБЕРЕГАТЕЛЬНОГО СЧЕТА------------------

    // Негативный тест: оплата значением < 0,
    // должно вернуть false
    @Test
    public void shouldNotPayIfAmountIsNegativeAndGetFalse() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.pay(-100));
    }

    // Негативный тест: оплата значением < 0,
    // не должен поменяться баланс счета
    @Test
    public void shouldNotPayIfAmountIsNegativeAndNotChangeBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.pay(-100);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    // Негативный тест: оплата значением = 0,
    // должно вернуть false
    @Test
    public void shouldNotPayIfAmountIsZeroAndGetFalse() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.pay(0));
    }

    // Негативный тест: оплата значением = 0,
    // не должен поменяться баланс счета
    @Test
    public void shouldNotPayIfAmountIsZeroAndNotChangeBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.pay(0);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    // Негативный тест: оплата значением < баланса,
    // должно вернуть false
    @Test
    public void shouldNotPayIfAmountIsLessThenBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.pay(2_500));
    }

    // Негативный тест: оплата значением, когда остаток баланса станет < минимального баланса,
    // должно вернуть false
    @Test
    public void shouldNotPayIfBalanceBecomesLessThenMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.pay(1_500));
    }

    // Позитивный тест: оплата по правилам, минимум не достигнут,
    // должно вернуть true
    @Test
    public void shouldPayIfValidAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertTrue(account.pay(500));
    }

    // Позитивный тест: оплата по правилам, достигается минимум,
    // должно вернуть true
    @Test
    public void shouldPayIfValidAmountTillMin() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertTrue(account.pay(1_000));
    }


    //-------ТЕСТЫ ПОПОЛНЕНИЯ СБЕРЕГАТЕЛЬНОГО СЧЕТА------------------

    // Негативный тест: пополнение значением < 0,
    // должно вернуть false
    @Test
    public void shouldNotAddIfAmountIsNegativeAndGetFalse() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.add(-100));
    }

    // Негативный тест: пополнение значением < 0,
    // не должен поменяться баланс счета
    @Test
    public void shouldNotAddIfAmountIsNegativeAndNotChangeBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.add(-100);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    // Негативный тест: пополнение значением = 0,
    // должно вернуть false
    @Test
    public void shouldNotAddIfAmountIsZeroAndGetFalse() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.add(0));
    }

    // Негативный тест: пополнение значением = 0,
    // не должен поменяться баланс счета
    @Test
    public void shouldNotAddIfAmountIsZeroAndNotChangeBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.add(0);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    // Негативный тест: пополнение значением, когда остаток баланса станет > максимального баланса,
    // должно вернуть false
    @Test
    public void shouldNotAddIfBalanceBecomesMoreThenMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.add(8_500));
    }

    // Позитивный тест: пополнение по правилам, максимум не достигается
    // должно вернуть true
    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);
        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }


    // Позитивный тест: пополнение по правилам, максимум достигается
    // должно вернуть true
    @Test
    public void shouldAddToMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);
        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    //------- ТЕСТЫ РАСЧЕТА ПРОЦЕНТОВ ПО СТАВКЕ -----------------

    // Позитивный тест: расчет процентов при балансе = 0
    // должен вернуть 0 при ненулевой ставке
    @Test
    public void shouldGetZeroYearChangeIfZeroBalanceAndNonZeroRate() {
        SavingAccount account = new SavingAccount(
                0,
                0,
                10_000,
                5
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    // Позитивный тест: расчет процентов при балансе > 0
    // должен вернуть 0 при нулевой ставке
    @Test
    public void shouldGetZeroYearChangeIfNonZeroBalanceAndZeroRate() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                0
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    // Позитивный тест: расчет процентов при балансе > 0 (>1)
    // должен вернуть > 0 при ненулевой ставке
    @Test
    public void shouldGetNonZeroYearChangeIfNonZeroBalanceAndNonZeroRate() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                10
        );

        Assertions.assertEquals(200, account.yearChange());
    }

    // Позитивный тест: расчет процентов при балансе = 1
    // должен вернуть 0 при ненулевой ставке
    @Test
    public void shouldGetZeroYearChangeIfBalanceIsOneAndNonZeroRate() {
        SavingAccount account = new SavingAccount(
                1,
                0,
                10_000,
                10
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    //------- ТЕСТЫ СЕТТЕРА ДЛЯ СТАВКИ -----------------

    // Негативный тест: установка отрицательной ставки
    // должно вызываться исключение IllegalArgumentException
    @Test
    public void shouldThrowIllegalArgumentExceptionIfNegativeRateInSetter() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    5_000,
                    5
            );
            account.setRate(-10);
        });

    }

}
