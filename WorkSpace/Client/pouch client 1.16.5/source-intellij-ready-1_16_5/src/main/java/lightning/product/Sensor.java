/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import java.util.Set;
import lightning.product.TargetingConditions;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public abstract class Sensor<E extends r_4811_B> {
    private static final Random n_1700_B = new Random();
    private static final TargetingConditions J_1907_R = new TargetingConditions().n_1700_B(16.0).J_1907_R().G_564_y();
    private static final TargetingConditions R_4764_Y = new TargetingConditions().n_1700_B(16.0).J_1907_R().G_564_y().P_1922_E();
    private final int G_564_y;
    private long P_1922_E;

    public Sensor(int interval) {
        this.G_564_y = interval;
        this.P_1922_E = n_1700_B.nextInt(interval);
    }

    public Sensor() {
        this(20);
    }

    public final void J_1907_R(e_3591_l worldIn, E entityIn) {
        if (--this.P_1922_E <= 0L) {
            this.P_1922_E = this.G_564_y;
            this.n_1700_B(worldIn, entityIn);
        }
    }

    protected abstract void n_1700_B(e_3591_l var1, E var2);

    public abstract Set<MemoryModuleType<?>> n_1700_B();

    protected static boolean n_1700_B(r_4811_B livingEntity, r_4811_B target) {
        return livingEntity.y_1945_D().J_1907_R(MemoryModuleType.Q_4569_t, target) ? R_4764_Y.n_1700_B(livingEntity, target) : J_1907_R.n_1700_B(livingEntity, target);
    }
}


