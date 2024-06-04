package edu.school21.numbers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    NumberWorker nw;
    @BeforeEach
    public void init() {
        nw = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 1013, 43, 13, 2})
     void isPrimeForPrimes(int number) {
        Assertions.assertTrue(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {22, 46, 1057, 844, 423})
    void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, -300, 0, -10, -800})
    void isPrimeForIncorrectPrimes(int number) {
        boolean thrown = false;
        try {
            nw.isPrime(number);
        } catch (IllegalNumberException e) {
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", delimiter = ',')
    void digitSumTest(int number, int expected) {
        Assertions.assertEquals(expected, nw.digitsSum(number));
    }
}
