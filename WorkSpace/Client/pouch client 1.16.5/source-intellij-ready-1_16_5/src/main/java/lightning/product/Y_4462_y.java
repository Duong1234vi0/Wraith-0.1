/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.LeavesBlock;
import lightning.product.T_2915_h;
import lightning.product.W_3371_U;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.g_1941_L;
import lightning.product.PathfinderMob;
import lightning.product.BlockTags;
import lightning.product.u_530_F;

public class Y_4462_y
extends g_1941_L {
    public Y_4462_y(PathfinderMob creature, double speed) {
        super(creature, speed);
    }

    @Override
    @Nullable
    protected e_2866_D v_4262_N() {
        e_2866_D vector3d = null;
        if (this.n_1700_B.RowButton()) {
            vector3d = W_3371_U.J_1907_R(this.n_1700_B, 15, 15);
        }
        if (this.n_1700_B.M_3508_C().nextFloat() >= this.w_1484_f) {
            vector3d = this.s_956_w();
        }
        return vector3d == null ? super.v_4262_N() : vector3d;
    }

    @Nullable
    private e_2866_D s_956_w() {
        c_1514_x blockpos = this.n_1700_B.b_2312_j();
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        c_1514_x.n_1700_B blockpos$mutable1 = new c_1514_x.n_1700_B();
        for (c_1514_x blockpos1 : c_1514_x.getAllInBoxMutable(u_530_F.R_4764_Y(this.n_1700_B.O_3598_v() - 3.0), u_530_F.R_4764_Y(this.n_1700_B.X_2960_b() - 6.0), u_530_F.R_4764_Y(this.n_1700_B.l_2647_k() - 3.0), u_530_F.R_4764_Y(this.n_1700_B.O_3598_v() + 3.0), u_530_F.R_4764_Y(this.n_1700_B.X_2960_b() + 6.0), u_530_F.R_4764_Y(this.n_1700_B.l_2647_k() + 3.0))) {
            T_2915_h block;
            boolean flag;
            if (blockpos.equals(blockpos1) || !(flag = (block = this.n_1700_B.O_508_d.getBlockState(blockpos$mutable1.n_1700_B(blockpos1, b_257_Y.n_1700_B)).J_1907_R()) instanceof LeavesBlock || block.n_1700_B(BlockTags.w_1457_N)) || !this.n_1700_B.O_508_d.u_1723_Y(blockpos1) || !this.n_1700_B.O_508_d.u_1723_Y(blockpos$mutable.n_1700_B(blockpos1, b_257_Y.J_1907_R))) continue;
            return e_2866_D.R_4764_Y(blockpos1);
        }
        return null;
    }
}


