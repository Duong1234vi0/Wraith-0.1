/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.D_38_f;
import lightning.product.F_997_G;
import lightning.product.I_408_V;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.S_3458_C;
import lightning.product.SoundEvents;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.Shearable;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.BlockTags;
import lightning.product.r_4811_B;
import lightning.product.v_1577_d;
import lightning.product.OptionalDispenseItemBehavior;
import lightning.product.BlockSource;

public class p_1553_v
extends OptionalDispenseItemBehavior {
    @Override
    protected Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
        e_3591_l world = source.v_4262_N();
        if (!world.v_4276_D()) {
            c_1514_x blockpos = source.G_564_y().offset(source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p));
            this.n_1700_B(p_1553_v.n_1700_B(world, blockpos) || p_1553_v.J_1907_R(world, blockpos));
            if (this.J_1907_R() && stack.n_1700_B(1, world.e_4240_b(), (B_4088_l)null)) {
                stack.P_1922_E(0);
            }
        }
        return stack;
    }

    private static boolean n_1700_B(e_3591_l world, c_1514_x pos) {
        int i;
        K_4074_S blockstate = world.getBlockState(pos);
        if (blockstate.n_1700_B(BlockTags.Ping) && (i = blockstate.R_4764_Y(v_1577_d.h_1847_R).intValue()) >= 5) {
            world.n_1700_B((a_3913_L)null, pos, SoundEvents.RegionPingResult, D_38_f.P_1922_E, 1.0f, 1.0f);
            v_1577_d.n_1700_B(world, pos);
            ((v_1577_d)blockstate.J_1907_R()).n_1700_B((b_4507_u)world, blockstate, pos, (a_3913_L)null, F_997_G.J_1907_R.J_1907_R);
            return true;
        }
        return false;
    }

    private static boolean J_1907_R(e_3591_l world, c_1514_x pos) {
        for (r_4811_B r_4811_B2 : world.n_1700_B(r_4811_B.class, new I_4817_s(pos), I_408_V.v_4262_N)) {
            Shearable ishearable;
            if (!(r_4811_B2 instanceof Shearable) || !(ishearable = (Shearable)((Object)r_4811_B2)).n_1700_B()) continue;
            ishearable.n_1700_B(D_38_f.P_1922_E);
            return true;
        }
        return false;
    }
}


