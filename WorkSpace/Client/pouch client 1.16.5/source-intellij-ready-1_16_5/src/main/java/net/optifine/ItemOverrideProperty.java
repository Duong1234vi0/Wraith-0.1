/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.Arrays;
import lightning.product.O_2592_x;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.k_4690_i;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.ItemPropertyFunction;
import net.optifine.Config;

public class ItemOverrideProperty {
    private g_2336_b location;
    private float[] values;

    public ItemOverrideProperty(g_2336_b location, float[] values) {
        this.location = location;
        this.values = (float[])values.clone();
        Arrays.sort(this.values);
    }

    public Integer getValueIndex(Z_1993_T stack, k_4690_i world, r_4811_B entity) {
        q_1613_l item = stack.J_1907_R();
        ItemPropertyFunction iitempropertygetter = O_2592_x.n_1700_B(item, this.location);
        if (iitempropertygetter == null) {
            return null;
        }
        float f = iitempropertygetter.call(stack, world, entity);
        int i = Arrays.binarySearch(this.values, f);
        return i;
    }

    public g_2336_b getLocation() {
        return this.location;
    }

    public float[] getValues() {
        return this.values;
    }

    public String toString() {
        return "location: " + String.valueOf(this.location) + ", values: [" + Config.arrayToString(this.values) + "]";
    }
}


