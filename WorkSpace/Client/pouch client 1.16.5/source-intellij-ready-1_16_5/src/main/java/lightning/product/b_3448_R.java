/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import lightning.product.I_408_V;
import lightning.product.S_50_d;
import lightning.product.Z_530_i;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class b_3448_R<E extends Z_530_i>
extends Behavior<E> {
    private final Predicate<r_4811_B> n_1700_B;

    public b_3448_R(Predicate<r_4811_B> p_i231539_1_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.Q_4569_t, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.Y_1740_V, (Object)((Object)S_50_d.R_4764_Y)));
        this.n_1700_B = p_i231539_1_;
    }

    public b_3448_R() {
        this((r_4811_B p_233984_0_) -> false);
    }

    protected void n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        if (b_3448_R.n_1700_B(entityIn)) {
            this.G_564_y(entityIn);
        } else if (this.R_4764_Y(entityIn)) {
            this.G_564_y(entityIn);
        } else if (this.n_1700_B(entityIn)) {
            this.G_564_y(entityIn);
        } else if (!I_408_V.u_1723_Y.test(this.J_1907_R(entityIn))) {
            this.G_564_y(entityIn);
        } else if (this.n_1700_B.test(this.J_1907_R(entityIn))) {
            this.G_564_y(entityIn);
        }
    }

    private boolean n_1700_B(E p_233983_1_) {
        return this.J_1907_R(p_233983_1_).O_508_d != ((Z_530_i)p_233983_1_).O_508_d;
    }

    private r_4811_B J_1907_R(E p_233985_1_) {
        return ((r_4811_B)p_233985_1_).y_1945_D().R_4764_Y(MemoryModuleType.Q_4569_t).get();
    }

    private static <E extends r_4811_B> boolean n_1700_B(E p_233982_0_) {
        Optional<Long> optional = p_233982_0_.y_1945_D().R_4764_Y(MemoryModuleType.Y_1740_V);
        return optional.isPresent() && p_233982_0_.O_508_d.X_933_l() - optional.get() > 200L;
    }

    private boolean R_4764_Y(E p_233986_1_) {
        Optional<r_4811_B> optional = ((r_4811_B)p_233986_1_).y_1945_D().R_4764_Y(MemoryModuleType.Q_4569_t);
        return optional.isPresent() && !optional.get().RealmsLongRunningMcoTaskScreen();
    }

    private void G_564_y(E p_233987_1_) {
        ((r_4811_B)p_233987_1_).y_1945_D().J_1907_R(MemoryModuleType.Q_4569_t);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }
}


