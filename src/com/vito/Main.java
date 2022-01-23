package com.vito;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.lang.System.out;

public class Main {

    private static final AtomicInteger ai = new AtomicInteger(0); // TODO rm

    public static void process(int number) {
        var primeNumbers = PollardRho.primeFactors(number);
        if (primeNumbers.size() == 1) {
            out.println(number + " 1");
        } else {
            out.println("idx: " + ai.getAndIncrement() + " numb:" + number + ", result: " + primeNumbers);
        }
    }

    public static void main(String[] args) {
        var intStream = IntStream.generate(() -> abs(ThreadLocalRandom.current().nextInt()))
            .filter(p -> p < 1000000000)
            .limit(19000);

        var start = LocalDateTime.now();
        out.println("Starting at: " + start);
        intStream.forEach(Main::process);
        out.println("Finished in:" + Duration.between(start, LocalDateTime.now()).getNano() + " nano");
    }
}
