package com.kkk.spring.test.TestWithSpringGradle.utility;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimpleStaticUtility {
    private void StaticUtils() {}

    public static List<Integer> range(int start, int end) {
        return IntStream.range(start, end)
                .boxed()
                .collect(Collectors.toList());
    }

    public static String name() {
        return "Baeldung";
    }
}
