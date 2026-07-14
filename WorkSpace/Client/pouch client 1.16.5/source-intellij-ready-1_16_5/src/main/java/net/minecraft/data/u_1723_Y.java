/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonPrimitive
 */
package net.minecraft.data;

import com.google.gson.JsonPrimitive;
import lightning.product.g_2336_b;
import net.minecraft.data.G_564_y;

public class u_1723_Y {
    public static final G_564_y<n_1700_B> n_1700_B = new G_564_y<n_1700_B>("x", p_240207_0_ -> new JsonPrimitive((Number)p_240207_0_.P_1922_E));
    public static final G_564_y<n_1700_B> J_1907_R = new G_564_y<n_1700_B>("y", p_240205_0_ -> new JsonPrimitive((Number)p_240205_0_.P_1922_E));
    public static final G_564_y<g_2336_b> R_4764_Y = new G_564_y<g_2336_b>("model", p_240206_0_ -> new JsonPrimitive(p_240206_0_.toString()));
    public static final G_564_y<Boolean> G_564_y = new G_564_y<Boolean>("uvlock", JsonPrimitive::new);
    public static final G_564_y<Integer> P_1922_E = new G_564_y<Integer>("weight", JsonPrimitive::new);

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(0);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(90);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(180);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(270);
        private final int P_1922_E;
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(int p_i232542_3_) {
            this.P_1922_E = p_i232542_3_;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            u_1723_Y = net.minecraft.data.u_1723_Y$n_1700_B.n_1700_B();
        }
    }
}

