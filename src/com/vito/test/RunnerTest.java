package com.vito.test;

import com.vito.src.Runner;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static java.util.Arrays.stream;
import static org.junit.jupiter.api.Assertions.*;

class RunnerTest {

    @Test
    void processPrimeFactorsEqualsExContentTest() {
        var input = """
            2
            3
            5
            76
            100
            2001
            4""".split("\n");

        var expected = """
            2 1
            3 1
            5 1
            23 2
            5 5
            5 6
            """;

        assertEquals(expected, Runner.processNumbers(stream(input).mapToInt(Integer::parseInt)));
    }

    @Test
    void processPrimeFactorsEqualsEmptyContentTest() {
        var input = "".split("\n");
        var expected = "";
        assertEquals(expected, Runner.processNumbers(stream(input).mapToInt(Integer::parseInt)));
    }

    @Test
    void processPrimeFactorsEqualsSpecialSignContentTest() {
        var input = "4".split("\n");
        var expected = "";
        assertEquals(expected, Runner.processNumbers(stream(input).mapToInt(Integer::parseInt)));
    }

    @Test
    void twentyThousandElements_processPrimeFactors_Demo() {
        var intStream = IntStream.generate(() -> abs(ThreadLocalRandom.current().nextInt()))
            .filter(p -> p < 1000000000)
            .limit(20000);

        var start = LocalDateTime.now();
        out.println("Starting at: " + start);
        intStream.forEach(Runner::processPrimeFactors);
        out.println("Finished in:" + Duration.between(start, LocalDateTime.now()).getNano() + " nano");
    }
}
