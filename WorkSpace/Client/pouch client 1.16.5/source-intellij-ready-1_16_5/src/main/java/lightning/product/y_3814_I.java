/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.util.Map;
import lightning.product.X_2241_P;

public class y_3814_I {
    private final int n_1700_B;
    private final int J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;
    private final X_2241_P.n_1700_B P_1922_E;

    public y_3814_I(int sourceX, int sourceGroundY, int sourceZ, int deltaY, X_2241_P.n_1700_B destProjection) {
        this.n_1700_B = sourceX;
        this.J_1907_R = sourceGroundY;
        this.R_4764_Y = sourceZ;
        this.G_564_y = deltaY;
        this.P_1922_E = destProjection;
    }

    public int n_1700_B() {
        return this.n_1700_B;
    }

    public int J_1907_R() {
        return this.J_1907_R;
    }

    public int R_4764_Y() {
        return this.R_4764_Y;
    }

    public <T> Dynamic<T> n_1700_B(DynamicOps<T> p_236820_1_) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        builder.put(p_236820_1_.createString("source_x"), p_236820_1_.createInt(this.n_1700_B)).put(p_236820_1_.createString("source_ground_y"), p_236820_1_.createInt(this.J_1907_R)).put(p_236820_1_.createString("source_z"), p_236820_1_.createInt(this.R_4764_Y)).put(p_236820_1_.createString("delta_y"), p_236820_1_.createInt(this.G_564_y)).put(p_236820_1_.createString("dest_proj"), p_236820_1_.createString(this.P_1922_E.J_1907_R()));
        return new Dynamic(p_236820_1_, p_236820_1_.createMap((Map)builder.build()));
    }

    public static <T> y_3814_I n_1700_B(Dynamic<T> p_236819_0_) {
        return new y_3814_I(p_236819_0_.get("source_x").asInt(0), p_236819_0_.get("source_ground_y").asInt(0), p_236819_0_.get("source_z").asInt(0), p_236819_0_.get("delta_y").asInt(0), X_2241_P.n_1700_B.n_1700_B(p_236819_0_.get("dest_proj").asString("")));
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            y_3814_I jigsawjunction = (y_3814_I)p_equals_1_;
            if (this.n_1700_B != jigsawjunction.n_1700_B) {
                return false;
            }
            if (this.R_4764_Y != jigsawjunction.R_4764_Y) {
                return false;
            }
            if (this.G_564_y != jigsawjunction.G_564_y) {
                return false;
            }
            return this.P_1922_E == jigsawjunction.P_1922_E;
        }
        return false;
    }

    public int hashCode() {
        int i = this.n_1700_B;
        i = 31 * i + this.J_1907_R;
        i = 31 * i + this.R_4764_Y;
        i = 31 * i + this.G_564_y;
        return 31 * i + this.P_1922_E.hashCode();
    }

    public String toString() {
        return "JigsawJunction{sourceX=" + this.n_1700_B + ", sourceGroundY=" + this.J_1907_R + ", sourceZ=" + this.R_4764_Y + ", deltaY=" + this.G_564_y + ", destProjection=" + String.valueOf(this.P_1922_E) + "}";
    }
}

