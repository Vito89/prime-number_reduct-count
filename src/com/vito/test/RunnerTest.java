package com.vito.test;

import com.vito.src.Runner;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RunnerTest {

    @Test
    void processPrimeFactorsEqualsExContentTest() {
        var input = "2\n3\n5\n76\n100\n2001\n4".lines().collect(Collectors.toList());
        var expected = "2 1\n3 1\n5 1\n23 2\n5 5\n5 6".lines().collect(Collectors.toList());
        IntStream.range(0, input.size() - 1).forEach(idx ->
            assertEquals(expected.get(idx), Runner.processNumber(Integer.parseInt(input.get(idx))))
        );
    }

    @Test
    void processPrimeFactorsEqualsEmptyContentTest() {
        var input = -1;
        var expected = "";
        assertEquals(expected, Runner.processNumber(input));
    }

    @Test
    void processPrimeFactorsEqualsSpecialSignContentTest() {
        var input = 4;
        var expected = "";
        assertEquals(expected, Runner.processNumber(input));
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
