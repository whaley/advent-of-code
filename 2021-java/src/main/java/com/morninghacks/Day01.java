package com.morninghacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day01 {
    public static int depthIncreases(int[] depths) {
        int count = 0;
        for (int i = 0; i + 1 < depths.length; i++) {
            if (depths[i] < depths[i+1]) {
                count++;
            }
        }
        return count;
    }

    static List<String> readInputFile(String resourcePath) throws IOException {
        try (var stream = Day01.class.getResourceAsStream(resourcePath)) {
            return new BufferedReader(new InputStreamReader(stream)).lines().toList();
        }
    }

    public static void main(String[] args) throws Exception{
        var lines = readInputFile("/day01.txt");
        var depths = lines.stream().mapToInt(Integer::valueOf).toArray();
        System.out.println(depthIncreases(depths));
    }
}
