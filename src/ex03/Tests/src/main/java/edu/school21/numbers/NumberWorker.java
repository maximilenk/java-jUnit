package edu.school21.numbers;

public class NumberWorker
{
    public int digitsSum(int number) {
        if (number < 0) {
            number *= -1;
        }
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    public boolean isPrime(int number) {
        if (number <= 1) {
            throw new IllegalNumberException("wrong number!");
        }
        boolean isPrime = true;
        if (number != 2 && number != 3) {
            if (number % 2 == 0) {
                isPrime = false;
            } else {
                for (int i = 3; i <= (int)Math.sqrt(number); i += 2) {
                    if (number % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }
        }
        return isPrime;

    }

}
