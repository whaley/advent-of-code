package com.morninghacks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

public class Day01Test {

    @Test
    void depthIncreases() {
        var depths = new int[]{199, 200, 208, 210, 200, 207, 240, 269, 260, 263};
        assertEquals(7, Day01.depthIncreases(depths));
    }
}
