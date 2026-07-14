/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic.mask;

import lightning.product.K_4074_S;
import mods.baritone.api.api.java.baritone.api.schematic.mask.operator.BinaryOperatorMask;
import mods.baritone.api.api.java.baritone.api.schematic.mask.operator.NotMask;
import mods.baritone.api.api.java.baritone.api.utils.BooleanBinaryOperators;

public interface Mask {
    public boolean partOfMask(int var1, int var2, int var3, K_4074_S var4);

    public int widthX();

    public int heightY();

    public int lengthZ();

    default public Mask not() {
        return new NotMask(this);
    }

    default public Mask union(Mask other) {
        return new BinaryOperatorMask(this, other, BooleanBinaryOperators.OR);
    }

    default public Mask intersection(Mask other) {
        return new BinaryOperatorMask(this, other, BooleanBinaryOperators.AND);
    }

    default public Mask xor(Mask other) {
        return new BinaryOperatorMask(this, other, BooleanBinaryOperators.XOR);
    }
}

