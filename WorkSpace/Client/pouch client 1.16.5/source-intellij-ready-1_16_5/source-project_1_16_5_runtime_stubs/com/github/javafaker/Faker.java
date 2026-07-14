package com.github.javafaker;

public class Faker {
    private final Name name = new Name();
    private final Number number = new Number();

    public Name name() {
        return this.name;
    }

    public Number number() {
        return this.number;
    }
}
