package com.vito.src;

import java.util.Scanner;

public class Runner {

    public static String processPrimeFactors(int number) {
        if (4 == number) {
            return "";
        }
        var primeNumbers = PollardRho.primeFactors(number);
        if (primeNumbers.size() == 1) {
            return number + " 1";
        } else {
            var iterationCount = 1;
            var factorPrimeNumbers = primeNumbers;
            do {
                var sum = factorPrimeNumbers.stream().reduce(0, Integer::sum);
                factorPrimeNumbers = PollardRho.primeFactors(sum);
                iterationCount++;
            } while (factorPrimeNumbers.size() > 1);

            return factorPrimeNumbers.get(0) + " " + iterationCount;
        }
    }

    public static void processNumber(int number) {
        try {
            System.out.println(processPrimeFactors(number));
        } catch (Exception ex) {
            System.out.println("Error handle, err: " + ex.getClass() + ", msg: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLong()) {
            processNumber(sc.nextInt());
        }
    }
}
