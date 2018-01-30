package com.cyh.optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

/**
 * Created by yanhuche on 3/31/2017.
 */
public class OptionalTest01 {

    @Test
    public void test() {
        Optional<String> optional = Optional.of("bam");
        assertTrue(optional.isPresent());
        assertEquals(optional.get(), "bam");
        assertEquals(optional.orElse("fallback"), "bam");
        optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"
    }



}
