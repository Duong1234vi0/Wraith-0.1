/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.utils;

import mods.baritone.api.api.java.baritone.api.utils.BooleanBinaryOperator;

public enum BooleanBinaryOperators implements BooleanBinaryOperator
{
    OR((a, b) -> a || b),
    AND((a, b) -> a && b),
    XOR((a, b) -> a ^ b);

    private final BooleanBinaryOperator op;

    private BooleanBinaryOperators(BooleanBinaryOperator op) {
        this.op = op;
    }

    @Override
    public boolean applyAsBoolean(boolean a, boolean b) {
        return this.op.applyAsBoolean(a, b);
    }
}

