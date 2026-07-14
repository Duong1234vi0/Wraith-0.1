/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Y_444_s;
import lightning.product.g_2336_b;
import lightning.product.Weighted;

public class O_728_b
implements Weighted<O_728_b> {
    private final g_2336_b n_1700_B;
    private final float J_1907_R;
    private final float R_4764_Y;
    private final int G_564_y;
    private final n_1700_B P_1922_E;
    private final boolean u_1723_Y;
    private final boolean v_4262_N;
    private final int w_1484_f;

    public O_728_b(String nameIn, float volumeIn, float pitchIn, int weightIn, n_1700_B typeIn, boolean streamingIn, boolean preloadIn, int attenuationDistanceIn) {
        this.n_1700_B = new g_2336_b(nameIn);
        this.J_1907_R = volumeIn;
        this.R_4764_Y = pitchIn;
        this.G_564_y = weightIn;
        this.P_1922_E = typeIn;
        this.u_1723_Y = streamingIn;
        this.v_4262_N = preloadIn;
        this.w_1484_f = attenuationDistanceIn;
    }

    public g_2336_b R_4764_Y() {
        return this.n_1700_B;
    }

    public g_2336_b G_564_y() {
        return new g_2336_b(this.n_1700_B.R_4764_Y(), "sounds/" + this.n_1700_B.J_1907_R() + ".ogg");
    }

    public float P_1922_E() {
        return this.J_1907_R;
    }

    public float u_1723_Y() {
        return this.R_4764_Y;
    }

    @Override
    public int n_1700_B() {
        return this.G_564_y;
    }

    public O_728_b v_4262_N() {
        return this;
    }

    @Override
    public void n_1700_B(Y_444_s engine) {
        if (this.v_4262_N) {
            engine.n_1700_B(this);
        }
    }

    public n_1700_B w_1484_f() {
        return this.P_1922_E;
    }

    public boolean t_148_a() {
        return this.u_1723_Y;
    }

    public boolean s_956_w() {
        return this.v_4262_N;
    }

    public int u_2550_I() {
        return this.w_1484_f;
    }

    public String toString() {
        return "Sound[" + String.valueOf(this.n_1700_B) + "]";
    }

    @Override
    public /* synthetic */ Object J_1907_R() {
        return this.v_4262_N();
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("file");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("event");
        private final String R_4764_Y;
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String nameIn) {
            this.R_4764_Y = nameIn;
        }

        public static n_1700_B n_1700_B(String nameIn) {
            for (n_1700_B sound$type : lightning.product.O_728_b$n_1700_B.values()) {
                if (!sound$type.R_4764_Y.equals(nameIn)) continue;
                return sound$type;
            }
            return null;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            G_564_y = lightning.product.O_728_b$n_1700_B.n_1700_B();
        }
    }
}


