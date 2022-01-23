package com.vito.src;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.lang.System.out;
import static java.util.Arrays.stream;

public class Runner {

    private static final AtomicInteger ai = new AtomicInteger(0); // TODO rm

    public static void processPrimeFactors(int number) {
        var primeNumbers = PollardRho.primeFactors(number);
        if (primeNumbers.size() == 1) {
            out.println(number + " 1");
        } else {
            out.println("idx: " + ai.getAndIncrement() + " number:" + number + ", result: " + primeNumbers);
        }
    }

    public static String processNumbers(IntStream numbers) {
        numbers.forEach(Runner::processPrimeFactors);

        return null;
    }

    public static void main(String[] args) {
        System.out.println(processNumbers(stream(args).mapToInt(Integer::parseInt)));
    }
}
