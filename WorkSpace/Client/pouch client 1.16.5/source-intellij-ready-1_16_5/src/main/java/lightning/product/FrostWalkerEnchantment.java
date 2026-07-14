/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_1310_v;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.j_123_i;
import lightning.product.r_4811_B;
import lightning.product.s_3834_w;
import lightning.product.Material;
import lightning.product.u_530_F;

public class FrostWalkerEnchantment
extends K_1310_v {
    public FrostWalkerEnchantment(K_1310_v.n_1700_B rarityIn, e_1174_E ... slots) {
        super(rarityIn, j_123_i.J_1907_R, slots);
    }

    @Override
    public int n_1700_B(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    @Override
    public int J_1907_R(int enchantmentLevel) {
        return this.n_1700_B(enchantmentLevel) + 15;
    }

    @Override
    public boolean J_1907_R() {
        return true;
    }

    @Override
    public int n_1700_B() {
        return 2;
    }

    public static void n_1700_B(r_4811_B living, b_4507_u worldIn, c_1514_x pos, int level) {
        if (living.M_1641_O()) {
            K_4074_S blockstate = a_3742_W.LeaveTracker.multiplayerClientSuggestionProvider();
            float f = Math.min(16, 2 + level);
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(pos.add(-f, -1.0, -f), pos.add(f, -1.0, f))) {
                K_4074_S blockstate2;
                if (!blockpos.withinDistance(living.s_4990_V(), (double)f)) continue;
                blockpos$mutable.n_1700_B(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                K_4074_S blockstate1 = worldIn.getBlockState(blockpos$mutable);
                if (!blockstate1.v_4262_N() || (blockstate2 = worldIn.getBlockState(blockpos)).R_4764_Y() != Material.s_956_w || blockstate2.R_4764_Y(s_3834_w.P_4830_p) != 0 || !blockstate.n_1700_B((T_1316_M)worldIn, blockpos) || !worldIn.n_1700_B(blockstate, blockpos, CollisionContext.J_1907_R())) continue;
                worldIn.J_1907_R(blockpos, blockstate);
                worldIn.u_2550_I().n_1700_B(blockpos, a_3742_W.LeaveTracker, u_530_F.n_1700_B(living.M_3508_C(), 60, 120));
            }
        }
    }

    @Override
    public boolean n_1700_B(K_1310_v ench) {
        return super.n_1700_B(ench) && ench != Enchantments.t_148_a;
    }
}



