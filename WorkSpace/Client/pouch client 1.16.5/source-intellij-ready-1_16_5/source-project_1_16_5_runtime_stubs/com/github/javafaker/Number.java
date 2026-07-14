package com.github.javafaker;

import java.util.concurrent.ThreadLocalRandom;

public class Number {
    public String digits(int count) {
        StringBuilder builder = new StringBuilder(Math.max(count, 0));
        for (int i = 0; i < count; i++) {
            builder.append(ThreadLocalRandom.current().nextInt(10));
        }
        return builder.toString();
    }
}
