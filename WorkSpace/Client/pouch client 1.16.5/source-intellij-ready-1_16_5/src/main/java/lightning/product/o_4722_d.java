/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Optional;
import lightning.product.N_4263_v;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.PositionTracker;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class o_4722_d
implements PositionTracker {
    private final N_4263_v n_1700_B;
    private final boolean J_1907_R;

    public o_4722_d(N_4263_v entity, boolean eyePos) {
        this.n_1700_B = entity;
        this.J_1907_R = eyePos;
    }

    @Override
    public e_2866_D n_1700_B() {
        return this.J_1907_R ? this.n_1700_B.s_4990_V().J_1907_R(0.0, this.n_1700_B.X_1313_W(), 0.0) : this.n_1700_B.s_4990_V();
    }

    @Override
    public c_1514_x J_1907_R() {
        return this.n_1700_B.b_2312_j();
    }

    @Override
    public boolean n_1700_B(r_4811_B entity) {
        if (!(this.n_1700_B instanceof r_4811_B)) {
            return true;
        }
        Optional<List<r_4811_B>> optional = entity.y_1945_D().R_4764_Y(MemoryModuleType.w_1484_f);
        return this.n_1700_B.RealmsLongRunningMcoTaskScreen() && optional.isPresent() && optional.get().contains(this.n_1700_B);
    }

    public String toString() {
        return "EntityTracker for " + String.valueOf(this.n_1700_B);
    }
}


