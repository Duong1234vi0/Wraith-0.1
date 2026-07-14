package com.github.javafaker;

import java.util.concurrent.ThreadLocalRandom;

public class Name {
    private static final String[] PREFIXES = {"Player", "Steve", "Alex", "Miner", "Builder", "Crafter"};

    public String username() {
        String prefix = PREFIXES[ThreadLocalRandom.current().nextInt(PREFIXES.length)];
        int suffix = ThreadLocalRandom.current().nextInt(100, 1000);
        return prefix + suffix;
    }
}
