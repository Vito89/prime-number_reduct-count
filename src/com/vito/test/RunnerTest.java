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
        var input = "2\r\n3\r\n5\r\n76\r\n100\r\n2001\r\n4".lines();
        var expected = "2 1\n3 1\n5 1\n23 2\n5 5\n5 6";
        assertEquals(expected, Runner.processNumbers(input.mapToInt(Integer::parseInt)));
    }

    @Test
    void processPrimeFactorsEqualsEmptyContentTest() {
        var input = new String[]{""};
        var expected = "";
        assertEquals(expected, Runner.processNumbers(stream(input).mapToInt(Integer::parseInt)));
    }

    @Test
    void processPrimeFactorsEqualsSpecialSignContentTest() {
        var input = new String[]{"4"};
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
