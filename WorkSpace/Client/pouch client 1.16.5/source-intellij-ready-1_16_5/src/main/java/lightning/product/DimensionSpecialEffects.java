/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
 *  it.unimi.dsi.fastutil.objects.Object2ObjectMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import javax.annotation.Nullable;
import lightning.product.Z_3903_F;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.u_530_F;

public abstract class DimensionSpecialEffects {
    private static final Object2ObjectMap<g_2336_b, DimensionSpecialEffects> n_1700_B = (Object2ObjectMap)j_3341_s.n_1700_B(new Object2ObjectArrayMap(), p_239214_0_ -> {
        G_564_y dimensionrenderinfo$overworld = new G_564_y();
        p_239214_0_.defaultReturnValue((Object)dimensionrenderinfo$overworld);
        p_239214_0_.put((Object)Z_3903_F.n_1700_B, (Object)dimensionrenderinfo$overworld);
        p_239214_0_.put((Object)Z_3903_F.J_1907_R, (Object)new R_4764_Y());
        p_239214_0_.put((Object)Z_3903_F.R_4764_Y, (Object)new n_1700_B());
    });
    private final float[] J_1907_R = new float[4];
    private final float R_4764_Y;
    private final boolean G_564_y;
    private final J_1907_R P_1922_E;
    private final boolean u_1723_Y;
    private final boolean v_4262_N;

    public DimensionSpecialEffects(float p_i241259_1_, boolean p_i241259_2_, J_1907_R p_i241259_3_, boolean p_i241259_4_, boolean p_i241259_5_) {
        this.R_4764_Y = p_i241259_1_;
        this.G_564_y = p_i241259_2_;
        this.P_1922_E = p_i241259_3_;
        this.u_1723_Y = p_i241259_4_;
        this.v_4262_N = p_i241259_5_;
    }

    public static DimensionSpecialEffects n_1700_B(Z_3903_F p_243495_0_) {
        return (DimensionSpecialEffects)n_1700_B.get((Object)p_243495_0_.M_182_A());
    }

    @Nullable
    public float[] n_1700_B(float p_230492_1_, float p_230492_2_) {
        float f = 0.4f;
        float f1 = u_530_F.J_1907_R(p_230492_1_ * ((float)Math.PI * 2)) - 0.0f;
        float f2 = -0.0f;
        if (f1 >= -0.4f && f1 <= 0.4f) {
            float f3 = (f1 - -0.0f) / 0.4f * 0.5f + 0.5f;
            float f4 = 1.0f - (1.0f - u_530_F.n_1700_B(f3 * (float)Math.PI)) * 0.99f;
            f4 *= f4;
            this.J_1907_R[0] = f3 * 0.3f + 0.7f;
            this.J_1907_R[1] = f3 * f3 * 0.7f + 0.2f;
            this.J_1907_R[2] = f3 * f3 * 0.0f + 0.2f;
            this.J_1907_R[3] = f4;
            return this.J_1907_R;
        }
        return null;
    }

    public float n_1700_B() {
        return this.R_4764_Y;
    }

    public boolean J_1907_R() {
        return this.G_564_y;
    }

    public abstract e_2866_D n_1700_B(e_2866_D var1, float var2);

    public abstract boolean n_1700_B(int var1, int var2);

    public J_1907_R R_4764_Y() {
        return this.P_1922_E;
    }

    public boolean G_564_y() {
        return this.u_1723_Y;
    }

    public boolean P_1922_E() {
        return this.v_4262_N;
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] G_564_y;

        public static J_1907_R[] values() {
            return (J_1907_R[])G_564_y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.DimensionSpecialEffects$J_1907_R.n_1700_B();
        }
    }

    public static class G_564_y
    extends DimensionSpecialEffects {
        public G_564_y() {
            super(128.0f, true, lightning.product.DimensionSpecialEffects$J_1907_R.J_1907_R, false, false);
        }

        @Override
        public e_2866_D n_1700_B(e_2866_D p_230494_1_, float p_230494_2_) {
            return p_230494_1_.G_564_y(p_230494_2_ * 0.94f + 0.06f, p_230494_2_ * 0.94f + 0.06f, p_230494_2_ * 0.91f + 0.09f);
        }

        @Override
        public boolean n_1700_B(int p_230493_1_, int p_230493_2_) {
            return false;
        }
    }

    public static class R_4764_Y
    extends DimensionSpecialEffects {
        public R_4764_Y() {
            super(Float.NaN, true, lightning.product.DimensionSpecialEffects$J_1907_R.n_1700_B, false, true);
        }

        @Override
        public e_2866_D n_1700_B(e_2866_D p_230494_1_, float p_230494_2_) {
            return p_230494_1_;
        }

        @Override
        public boolean n_1700_B(int p_230493_1_, int p_230493_2_) {
            return true;
        }
    }

    public static class n_1700_B
    extends DimensionSpecialEffects {
        public n_1700_B() {
            super(Float.NaN, false, lightning.product.DimensionSpecialEffects$J_1907_R.R_4764_Y, true, false);
        }

        @Override
        public e_2866_D n_1700_B(e_2866_D p_230494_1_, float p_230494_2_) {
            return p_230494_1_.n_1700_B((double)0.15f);
        }

        @Override
        public boolean n_1700_B(int p_230493_1_, int p_230493_2_) {
            return false;
        }

        @Override
        @Nullable
        public float[] n_1700_B(float p_230492_1_, float p_230492_2_) {
            return null;
        }
    }
}


