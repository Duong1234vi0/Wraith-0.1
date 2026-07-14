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
import lightning.product.BoneMealItem;
import lightning.product.CropBlock;
import lightning.product.WalkTarget;
import lightning.product.K_4074_S;
import lightning.product.L_2225_p;
import lightning.product.N_1216_z;
import lightning.product.S_50_d;
import lightning.product.T_2915_h;
import lightning.product.Z_148_A;
import lightning.product.Z_1993_T;
import lightning.product.c_1514_x;
import lightning.product.e_1174_E;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class I_2956_L
extends Behavior<L_2225_p> {
    private long n_1700_B;
    private long R_4764_Y;
    private int G_564_y;
    private Optional<c_1514_x> P_1922_E = Optional.empty();

    public I_2956_L() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R)));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        if (owner.RealmsWorldResetDto % 10 == 0 && (this.R_4764_Y == 0L || this.R_4764_Y + 160L <= (long)owner.RealmsWorldResetDto)) {
            if (owner.J_3635_s().n_1700_B(Items.r_1970_q) <= 0) {
                return false;
            }
            this.P_1922_E = this.J_1907_R(worldIn, owner);
            return this.P_1922_E.isPresent();
        }
        return false;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        return this.G_564_y < 80 && this.P_1922_E.isPresent();
    }

    private Optional<c_1514_x> J_1907_R(e_3591_l world, L_2225_p villager) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        Optional<c_1514_x> optional = Optional.empty();
        int i = 0;
        for (int j = -1; j <= 1; ++j) {
            for (int k = -1; k <= 1; ++k) {
                for (int l = -1; l <= 1; ++l) {
                    blockpos$mutable.n_1700_B(villager.b_2312_j(), j, k, l);
                    if (!this.n_1700_B(blockpos$mutable, world) || world.w_1457_N.nextInt(++i) != 0) continue;
                    optional = Optional.of(blockpos$mutable.toImmutable());
                }
            }
        }
        return optional;
    }

    private boolean n_1700_B(c_1514_x pos, e_3591_l world) {
        K_4074_S blockstate = world.getBlockState(pos);
        T_2915_h block = blockstate.J_1907_R();
        return block instanceof CropBlock && !((CropBlock)block).t_148_a(blockstate);
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        this.n_1700_B(entityIn);
        entityIn.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.r_1970_q));
        this.n_1700_B = gameTimeIn;
        this.G_564_y = 0;
    }

    private void n_1700_B(L_2225_p villager) {
        this.P_1922_E.ifPresent(pos -> {
            Z_148_A blockposwrapper = new Z_148_A((c_1514_x)pos);
            villager.y_1945_D().n_1700_B(MemoryModuleType.h_1847_R, blockposwrapper);
            villager.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(blockposwrapper, 0.5f, 1));
        });
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        entityIn.n_1700_B(e_1174_E.n_1700_B, Z_1993_T.J_1907_R);
        this.R_4764_Y = entityIn.RealmsWorldResetDto;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, L_2225_p owner, long gameTime) {
        c_1514_x blockpos = this.P_1922_E.get();
        if (gameTime >= this.n_1700_B && blockpos.withinDistance(owner.s_4990_V(), 1.0)) {
            Z_1993_T itemstack = Z_1993_T.J_1907_R;
            N_1216_z inventory = owner.J_3635_s();
            int i = inventory.Y_259_p();
            for (int j = 0; j < i; ++j) {
                Z_1993_T itemstack1 = inventory.s_956_w(j);
                if (itemstack1.J_1907_R() != Items.r_1970_q) continue;
                itemstack = itemstack1;
                break;
            }
            if (!itemstack.n_1700_B() && BoneMealItem.n_1700_B(itemstack, worldIn, blockpos)) {
                worldIn.R_4764_Y(2005, blockpos, 0);
                this.P_1922_E = this.J_1907_R(worldIn, owner);
                this.n_1700_B(owner);
                this.n_1700_B = gameTime + 40L;
            }
            ++this.G_564_y;
        }
    }

    @Override
    protected /* synthetic */ void J_1907_R(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.R_4764_Y(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void R_4764_Y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.G_564_y(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.J_1907_R(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


