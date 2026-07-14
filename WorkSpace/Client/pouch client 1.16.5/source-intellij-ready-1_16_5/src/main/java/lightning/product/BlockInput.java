/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.n_3832_I;
import lightning.product.BlockInWorld;
import lightning.product.v_3760_Q;

public class BlockInput
implements Predicate<BlockInWorld> {
    private final K_4074_S n_1700_B;
    private final Set<v_3760_Q<?>> J_1907_R;
    @Nullable
    private final U_2912_j R_4764_Y;

    public BlockInput(K_4074_S stateIn, Set<v_3760_Q<?>> propertiesIn, @Nullable U_2912_j nbtIn) {
        this.n_1700_B = stateIn;
        this.J_1907_R = propertiesIn;
        this.R_4764_Y = nbtIn;
    }

    public K_4074_S n_1700_B() {
        return this.n_1700_B;
    }

    public boolean n_1700_B(BlockInWorld p_test_1_) {
        K_4074_S blockstate = p_test_1_.n_1700_B();
        if (!blockstate.n_1700_B(this.n_1700_B.J_1907_R())) {
            return false;
        }
        for (v_3760_Q<?> property : this.J_1907_R) {
            if (blockstate.R_4764_Y(property) == this.n_1700_B.R_4764_Y(property)) continue;
            return false;
        }
        if (this.R_4764_Y == null) {
            return true;
        }
        i_2154_H tileentity = p_test_1_.J_1907_R();
        return tileentity != null && n_3832_I.n_1700_B(this.R_4764_Y, tileentity.n_1700_B(new U_2912_j()), true);
    }

    public boolean n_1700_B(e_3591_l worldIn, c_1514_x pos, int flags) {
        i_2154_H tileentity;
        K_4074_S blockstate = T_2915_h.J_1907_R(this.n_1700_B, worldIn, pos);
        if (blockstate.v_4262_N()) {
            blockstate = this.n_1700_B;
        }
        if (!worldIn.n_1700_B(pos, blockstate, flags)) {
            return false;
        }
        if (this.R_4764_Y != null && (tileentity = worldIn.getTileEntity(pos)) != null) {
            U_2912_j compoundnbt = this.R_4764_Y.v_4262_N();
            compoundnbt.J_1907_R("x", pos.getX());
            compoundnbt.J_1907_R("y", pos.getY());
            compoundnbt.J_1907_R("z", pos.getZ());
            tileentity.n_1700_B(blockstate, compoundnbt);
        }
        return true;
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((BlockInWorld)object);
    }
}


