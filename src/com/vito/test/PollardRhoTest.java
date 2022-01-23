package com.vito.test;

import com.vito.src.PollardRho;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PollardRhoTest {

    @Test
    void primeFactorsTest() {
        var expectedList = Arrays.asList(223, 1049, 3671);
        assertEquals(expectedList, PollardRho.primeFactors(858746017));

        expectedList = Arrays.asList(2, 442617971);
        assertEquals(expectedList, PollardRho.primeFactors(885235942));

        expectedList = Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 5, 5, 7, 7, 7, 283);
        assertEquals(expectedList, PollardRho.primeFactors(621241600));

        expectedList = List.of(708868387);
        assertEquals(expectedList, PollardRho.primeFactors(708868387));

        expectedList = List.of(2, 7, 41818171);
        assertEquals(expectedList, PollardRho.primeFactors(585454394));

        expectedList = List.of(55441, 8447);
        assertEquals(expectedList, PollardRho.primeFactors(468310127));

        expectedList = List.of(60317, 11909);
        assertEquals(expectedList, PollardRho.primeFactors(718315153));
    }
}
