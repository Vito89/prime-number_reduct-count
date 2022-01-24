package com.vito.src;

import java.util.stream.IntStream;

import static java.util.Arrays.stream;

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

    public static String processNumbers(IntStream numbers) {
        StringBuilder result = new StringBuilder();
        try {
            numbers.forEach(num -> result.append(processPrimeFactors(num)).append("\n"));
        } catch (Exception ex) {
            System.out.println("Error handle, err: " + ex.getClass() + ", msg: " + ex.getMessage());
            return "";
        }

        return result.toString().stripTrailing();
    }

    public static void main(String[] args) {
        System.out.println(processNumbers(stream(args).mapToInt(Integer::parseInt)));
    }
}
