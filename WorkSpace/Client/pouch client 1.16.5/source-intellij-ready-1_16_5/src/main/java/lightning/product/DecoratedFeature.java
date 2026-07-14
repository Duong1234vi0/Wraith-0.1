/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  org.apache.commons.lang3.mutable.MutableBoolean
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.DecorationContext;
import lightning.product.WorldGenLevel;
import lightning.product.V_3137_a;
import lightning.product.DecoratedFeatureConfiguration;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.z_1753_f;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class DecoratedFeature
extends Feature<DecoratedFeatureConfiguration> {
    public DecoratedFeature(Codec<DecoratedFeatureConfiguration> p_i231943_1_) {
        super(p_i231943_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, DecoratedFeatureConfiguration p_241855_5_) {
        MutableBoolean mutableboolean = new MutableBoolean();
        p_241855_5_.R_4764_Y.n_1700_B(new DecorationContext(p_241855_1_, p_241855_2_), p_241855_3_, p_241855_4_).forEach(p_242772_5_ -> {
            if (p_241855_5_.J_1907_R.get().n_1700_B(p_241855_1_, p_241855_2_, p_241855_3_, (c_1514_x)p_242772_5_)) {
                mutableboolean.setTrue();
            }
        });
        return mutableboolean.isTrue();
    }

    public String toString() {
        return String.format("< %s [%s] >", this.getClass().getSimpleName(), V_3137_a.RealmsServerPing.J_1907_R(this));
    }
}


