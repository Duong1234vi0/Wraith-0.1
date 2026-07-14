/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Map;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public abstract class Behavior<E extends r_4811_B> {
    protected final Map<MemoryModuleType<?>, S_50_d> J_1907_R;
    private n_1700_B n_1700_B = lightning.product.Behavior$n_1700_B.n_1700_B;
    private long R_4764_Y;
    private final int G_564_y;
    private final int P_1922_E;

    public Behavior(Map<MemoryModuleType<?>, S_50_d> requiredMemoryStateIn) {
        this(requiredMemoryStateIn, 60);
    }

    public Behavior(Map<MemoryModuleType<?>, S_50_d> requiredMemoryStateIn, int duration) {
        this(requiredMemoryStateIn, duration, duration);
    }

    public Behavior(Map<MemoryModuleType<?>, S_50_d> requiredMemoryStateIn, int durationMinIn, int durationMaxIn) {
        this.G_564_y = durationMinIn;
        this.P_1922_E = durationMaxIn;
        this.J_1907_R = requiredMemoryStateIn;
    }

    public n_1700_B n_1700_B() {
        return this.n_1700_B;
    }

    public final boolean P_1922_E(e_3591_l worldIn, E owner, long gameTime) {
        if (this.n_1700_B(owner) && this.n_1700_B(worldIn, owner)) {
            this.n_1700_B = lightning.product.Behavior$n_1700_B.J_1907_R;
            int i = this.G_564_y + worldIn.e_4240_b().nextInt(this.P_1922_E + 1 - this.G_564_y);
            this.R_4764_Y = gameTime + (long)i;
            this.G_564_y(worldIn, owner, gameTime);
            return true;
        }
        return false;
    }

    protected void G_564_y(e_3591_l worldIn, E entityIn, long gameTimeIn) {
    }

    public final void u_1723_Y(e_3591_l worldIn, E entityIn, long gameTime) {
        if (!this.n_1700_B(gameTime) && this.n_1700_B(worldIn, entityIn, gameTime)) {
            this.R_4764_Y(worldIn, entityIn, gameTime);
        } else {
            this.v_4262_N(worldIn, entityIn, gameTime);
        }
    }

    protected void R_4764_Y(e_3591_l worldIn, E owner, long gameTime) {
    }

    public final void v_4262_N(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        this.n_1700_B = lightning.product.Behavior$n_1700_B.n_1700_B;
        this.J_1907_R(worldIn, entityIn, gameTimeIn);
    }

    protected void J_1907_R(e_3591_l worldIn, E entityIn, long gameTimeIn) {
    }

    protected boolean n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        return false;
    }

    protected boolean n_1700_B(long gameTime) {
        return gameTime > this.R_4764_Y;
    }

    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        return true;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }

    private boolean n_1700_B(E owner) {
        for (Map.Entry<MemoryModuleType<?>, S_50_d> entry : this.J_1907_R.entrySet()) {
            MemoryModuleType<?> memorymoduletype = entry.getKey();
            S_50_d memorymodulestatus = entry.getValue();
            if (((r_4811_B)owner).y_1945_D().n_1700_B(memorymoduletype, memorymodulestatus)) continue;
            return false;
        }
        return true;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.Behavior$n_1700_B.n_1700_B();
        }
    }
}


