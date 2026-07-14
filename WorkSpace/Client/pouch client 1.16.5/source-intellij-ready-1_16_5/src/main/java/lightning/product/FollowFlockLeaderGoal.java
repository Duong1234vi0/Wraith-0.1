/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.function.Predicate;
import lightning.product.AbstractSchoolingFish;
import lightning.product.Goal;

public class FollowFlockLeaderGoal
extends Goal {
    private final AbstractSchoolingFish n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;

    public FollowFlockLeaderGoal(AbstractSchoolingFish taskOwnerIn) {
        this.n_1700_B = taskOwnerIn;
        this.R_4764_Y = this.n_1700_B(taskOwnerIn);
    }

    protected int n_1700_B(AbstractSchoolingFish taskOwnerIn) {
        return 200 + taskOwnerIn.M_3508_C().nextInt(200) % 20;
    }

    @Override
    public boolean n_1700_B() {
        if (this.n_1700_B.f_2787_O()) {
            return false;
        }
        if (this.n_1700_B.J_3635_s()) {
            return true;
        }
        if (this.R_4764_Y > 0) {
            --this.R_4764_Y;
            return false;
        }
        this.R_4764_Y = this.n_1700_B(this.n_1700_B);
        Predicate<AbstractSchoolingFish> predicate = fish -> fish.h_973_D() || !fish.J_3635_s();
        List<AbstractSchoolingFish> list = this.n_1700_B.O_508_d.n_1700_B(this.n_1700_B.getClass(), this.n_1700_B.i_601_W().grow(8.0, 8.0, 8.0), predicate);
        AbstractSchoolingFish abstractgroupfishentity = list.stream().filter(AbstractSchoolingFish::h_973_D).findAny().orElse(this.n_1700_B);
        abstractgroupfishentity.n_1700_B(list.stream().filter(fish -> !fish.J_3635_s()));
        return this.n_1700_B.J_3635_s();
    }

    @Override
    public boolean J_1907_R() {
        return this.n_1700_B.J_3635_s() && this.n_1700_B.P_2295_B();
    }

    @Override
    public void R_4764_Y() {
        this.J_1907_R = 0;
    }

    @Override
    public void G_564_y() {
        this.n_1700_B.o_82_k();
    }

    @Override
    public void P_1922_E() {
        if (--this.J_1907_R <= 0) {
            this.J_1907_R = 10;
            this.n_1700_B.U_1697_c();
        }
    }
}


