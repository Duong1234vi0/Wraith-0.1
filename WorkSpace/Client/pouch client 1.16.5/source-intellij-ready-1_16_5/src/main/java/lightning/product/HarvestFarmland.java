/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.D_38_f;
import lightning.product.CropBlock;
import lightning.product.WalkTarget;
import lightning.product.K_4074_S;
import lightning.product.L_2225_p;
import lightning.product.N_1216_z;
import lightning.product.VillagerProfession;
import lightning.product.S_50_d;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.Z_148_A;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;
import lightning.product.y_2012_u;

public class HarvestFarmland
extends Behavior<L_2225_p> {
    @Nullable
    private c_1514_x n_1700_B;
    private long R_4764_Y;
    private int G_564_y;
    private final List<c_1514_x> P_1922_E = Lists.newArrayList();

    public HarvestFarmland() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.u_1723_Y, (Object)((Object)S_50_d.n_1700_B)));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        if (!worldIn.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
            return false;
        }
        if (owner.c_2086_l().J_1907_R() != VillagerProfession.u_1723_Y) {
            return false;
        }
        c_1514_x.n_1700_B blockpos$mutable = owner.b_2312_j().toMutable();
        this.P_1922_E.clear();
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                for (int k = -1; k <= 1; ++k) {
                    blockpos$mutable.n_1700_B(owner.O_3598_v() + (double)i, owner.X_2960_b() + (double)j, owner.l_2647_k() + (double)k);
                    if (!this.n_1700_B(blockpos$mutable, worldIn)) continue;
                    this.P_1922_E.add(new c_1514_x(blockpos$mutable));
                }
            }
        }
        this.n_1700_B = this.n_1700_B(worldIn);
        return this.n_1700_B != null;
    }

    @Nullable
    private c_1514_x n_1700_B(e_3591_l serverWorldIn) {
        return this.P_1922_E.isEmpty() ? null : this.P_1922_E.get(serverWorldIn.e_4240_b().nextInt(this.P_1922_E.size()));
    }

    private boolean n_1700_B(c_1514_x pos, e_3591_l serverWorldIn) {
        K_4074_S blockstate = serverWorldIn.getBlockState(pos);
        T_2915_h block = blockstate.J_1907_R();
        T_2915_h block1 = serverWorldIn.getBlockState(pos.down()).J_1907_R();
        return block instanceof CropBlock && ((CropBlock)block).t_148_a(blockstate) || blockstate.v_4262_N() && block1 instanceof y_2012_u;
    }

    protected void n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        if (gameTimeIn > this.R_4764_Y && this.n_1700_B != null) {
            entityIn.y_1945_D().n_1700_B(MemoryModuleType.h_1847_R, new Z_148_A(this.n_1700_B));
            entityIn.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(new Z_148_A(this.n_1700_B), 0.5f, 1));
        }
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.h_1847_R);
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.P_4830_p);
        this.G_564_y = 0;
        this.R_4764_Y = gameTimeIn + 40L;
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, L_2225_p owner, long gameTime) {
        if (this.n_1700_B == null || this.n_1700_B.withinDistance(owner.s_4990_V(), 1.0)) {
            if (this.n_1700_B != null && gameTime > this.R_4764_Y) {
                K_4074_S blockstate = worldIn.getBlockState(this.n_1700_B);
                T_2915_h block = blockstate.J_1907_R();
                T_2915_h block1 = worldIn.getBlockState(this.n_1700_B.down()).J_1907_R();
                if (block instanceof CropBlock && ((CropBlock)block).t_148_a(blockstate)) {
                    worldIn.n_1700_B(this.n_1700_B, true, owner);
                }
                if (blockstate.v_4262_N() && block1 instanceof y_2012_u && owner.A_1306_N()) {
                    N_1216_z inventory = owner.J_3635_s();
                    for (int i = 0; i < inventory.Y_259_p(); ++i) {
                        Z_1993_T itemstack = inventory.s_956_w(i);
                        boolean flag = false;
                        if (!itemstack.n_1700_B()) {
                            if (itemstack.J_1907_R() == Items.G_4691_Q) {
                                worldIn.n_1700_B(this.n_1700_B, a_3742_W.l_4088_R.multiplayerClientSuggestionProvider(), 3);
                                flag = true;
                            } else if (itemstack.J_1907_R() == Items.l_683_e) {
                                worldIn.n_1700_B(this.n_1700_B, a_3742_W.U_1697_c.multiplayerClientSuggestionProvider(), 3);
                                flag = true;
                            } else if (itemstack.J_1907_R() == Items.BaseCoralWallFanBlock) {
                                worldIn.n_1700_B(this.n_1700_B, a_3742_W.P_2295_B.multiplayerClientSuggestionProvider(), 3);
                                flag = true;
                            } else if (itemstack.J_1907_R() == Items.MushroomBlock) {
                                worldIn.n_1700_B(this.n_1700_B, a_3742_W.FreeCam.multiplayerClientSuggestionProvider(), 3);
                                flag = true;
                            }
                        }
                        if (!flag) continue;
                        worldIn.n_1700_B((a_3913_L)null, (double)this.n_1700_B.getX(), (double)this.n_1700_B.getY(), (double)this.n_1700_B.getZ(), SoundEvents.h_2739_B, D_38_f.P_1922_E, 1.0f, 1.0f);
                        itemstack.v_4262_N(1);
                        if (!itemstack.n_1700_B()) break;
                        inventory.J_1907_R(i, Z_1993_T.J_1907_R);
                        break;
                    }
                }
                if (block instanceof CropBlock && !((CropBlock)block).t_148_a(blockstate)) {
                    this.P_1922_E.remove(this.n_1700_B);
                    this.n_1700_B = this.n_1700_B(worldIn);
                    if (this.n_1700_B != null) {
                        this.R_4764_Y = gameTime + 20L;
                        owner.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(new Z_148_A(this.n_1700_B), 0.5f, 1));
                        owner.y_1945_D().n_1700_B(MemoryModuleType.h_1847_R, new Z_148_A(this.n_1700_B));
                    }
                }
            }
            ++this.G_564_y;
        }
    }

    protected boolean G_564_y(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        return this.G_564_y < 200;
    }

    @Override
    protected /* synthetic */ boolean n_1700_B(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        return this.G_564_y(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}



