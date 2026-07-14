/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic.mask;

import lightning.product.K_4074_S;
import mods.baritone.api.api.java.baritone.api.schematic.mask.Mask;
import mods.baritone.api.api.java.baritone.api.schematic.mask.PreComputedMask;
import mods.baritone.api.api.java.baritone.api.schematic.mask.operator.BinaryOperatorMask;
import mods.baritone.api.api.java.baritone.api.schematic.mask.operator.NotMask;
import mods.baritone.api.api.java.baritone.api.utils.BooleanBinaryOperators;

public interface StaticMask
extends Mask {
    public boolean partOfMask(int var1, int var2, int var3);

    @Override
    default public boolean partOfMask(int x, int y, int z, K_4074_S currentState) {
        return this.partOfMask(x, y, z);
    }

    @Override
    default public StaticMask not() {
        return new NotMask.Static(this);
    }

    default public StaticMask union(StaticMask other) {
        return new BinaryOperatorMask.Static(this, other, BooleanBinaryOperators.OR);
    }

    default public StaticMask intersection(StaticMask other) {
        return new BinaryOperatorMask.Static(this, other, BooleanBinaryOperators.AND);
    }

    default public StaticMask xor(StaticMask other) {
        return new BinaryOperatorMask.Static(this, other, BooleanBinaryOperators.XOR);
    }

    default public StaticMask compute() {
        return new PreComputedMask(this);
    }
}

