/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.A_2352_Z;
import lightning.product.PatrollingMonster;
import lightning.product.CustomSpawner;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.V_3157_k;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.k_594_Q;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_743_i;
import lightning.product.z_2963_s;

public class L_4413_M
implements CustomSpawner {
    private int n_1700_B;

    @Override
    public int n_1700_B(e_3591_l p_230253_1_, boolean p_230253_2_, boolean p_230253_3_) {
        if (!p_230253_2_) {
            return 0;
        }
        if (!p_230253_1_.H_1990_U().J_1907_R(A_2352_Z.Y_1740_V)) {
            return 0;
        }
        Random random = p_230253_1_.w_1457_N;
        --this.n_1700_B;
        if (this.n_1700_B > 0) {
            return 0;
        }
        this.n_1700_B += 12000 + random.nextInt(1200);
        long i = p_230253_1_.Z_976_R() / 24000L;
        if (i >= 5L && p_230253_1_.q_4610_l()) {
            if (random.nextInt(5) != 0) {
                return 0;
            }
            int j = p_230253_1_.multiplayerClientSuggestionProvider().size();
            if (j < 1) {
                return 0;
            }
            a_3913_L playerentity = p_230253_1_.multiplayerClientSuggestionProvider().get(random.nextInt(j));
            if (playerentity.d_2461_k()) {
                return 0;
            }
            if (p_230253_1_.R_4764_Y(playerentity.b_2312_j(), 2)) {
                return 0;
            }
            int k = (24 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
            int l = (24 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
            c_1514_x.n_1700_B blockpos$mutable = playerentity.b_2312_j().toMutable().J_1907_R(k, 0, l);
            if (!p_230253_1_.n_1700_B(blockpos$mutable.getX() - 10, blockpos$mutable.getY() - 10, blockpos$mutable.getZ() - 10, blockpos$mutable.getX() + 10, blockpos$mutable.getY() + 10, blockpos$mutable.getZ() + 10)) {
                return 0;
            }
            k_594_Q biome = p_230253_1_.P_1922_E(blockpos$mutable);
            k_594_Q.R_4764_Y biome$category = biome.Y_601_j();
            if (biome$category == k_594_Q.R_4764_Y.M_182_A) {
                return 0;
            }
            int i1 = 0;
            int j1 = (int)Math.ceil(p_230253_1_.J_1907_R(blockpos$mutable).J_1907_R()) + 1;
            for (int k1 = 0; k1 < j1; ++k1) {
                ++i1;
                blockpos$mutable.setY(p_230253_1_.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, (c_1514_x)blockpos$mutable).getY());
                if (k1 == 0) {
                    if (!this.n_1700_B(p_230253_1_, blockpos$mutable, random, true)) {
                        break;
                    }
                } else {
                    this.n_1700_B(p_230253_1_, blockpos$mutable, random, false);
                }
                blockpos$mutable.setX(blockpos$mutable.getX() + random.nextInt(5) - random.nextInt(5));
                blockpos$mutable.setZ(blockpos$mutable.getZ() + random.nextInt(5) - random.nextInt(5));
            }
            return i1;
        }
        return 0;
    }

    private boolean n_1700_B(e_3591_l worldIn, c_1514_x p_222695_2_, Random random, boolean p_222695_4_) {
        K_4074_S blockstate = worldIn.getBlockState(p_222695_2_);
        if (!u_743_i.n_1700_B((BlockGetter)worldIn, p_222695_2_, blockstate, blockstate.P_4830_p(), t_5_h.p_178_J)) {
            return false;
        }
        if (!PatrollingMonster.J_1907_R(t_5_h.p_178_J, (LevelAccessor)worldIn, a_3160_D.M_182_A, p_222695_2_, random)) {
            return false;
        }
        PatrollingMonster patrollerentity = t_5_h.p_178_J.n_1700_B(worldIn);
        if (patrollerentity != null) {
            if (p_222695_4_) {
                patrollerentity.Y_259_p(true);
                patrollerentity.D_3612_q();
            }
            patrollerentity.J_1907_R(p_222695_2_.getX(), p_222695_2_.getY(), p_222695_2_.getZ());
            patrollerentity.n_1700_B(worldIn, worldIn.J_1907_R(p_222695_2_), a_3160_D.M_182_A, (V_3157_k)null, null);
            worldIn.n_1700_B((N_4263_v)patrollerentity);
            return true;
        }
        return false;
    }
}


