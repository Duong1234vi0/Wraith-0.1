/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.WalkTarget;
import lightning.product.S_50_d;
import lightning.product.Z_530_i;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.BlockTags;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class v_2575_G
extends Behavior<Z_530_i> {
    private final float n_1700_B;
    @Nullable
    private c_1514_x R_4764_Y;
    private int G_564_y;
    private int P_1922_E;
    private int u_1723_Y;

    public v_2575_G(float speed) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.C_2741_M, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R)));
        this.n_1700_B = speed;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, Z_530_i owner) {
        return owner.d_() && this.J_1907_R(worldIn, owner);
    }

    protected void n_1700_B(e_3591_l worldIn, Z_530_i entityIn, long gameTimeIn) {
        super.G_564_y(worldIn, entityIn, gameTimeIn);
        this.n_1700_B(entityIn).ifPresent(bedPos -> {
            this.R_4764_Y = bedPos;
            this.G_564_y = 100;
            this.P_1922_E = 3 + worldIn.w_1457_N.nextInt(4);
            this.u_1723_Y = 0;
            this.n_1700_B(entityIn, (c_1514_x)bedPos);
        });
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, Z_530_i entityIn, long gameTimeIn) {
        super.J_1907_R(worldIn, entityIn, gameTimeIn);
        this.R_4764_Y = null;
        this.G_564_y = 0;
        this.P_1922_E = 0;
        this.u_1723_Y = 0;
    }

    protected boolean R_4764_Y(e_3591_l worldIn, Z_530_i entityIn, long gameTimeIn) {
        return entityIn.d_() && this.R_4764_Y != null && this.n_1700_B(worldIn, this.R_4764_Y) && !this.P_1922_E(worldIn, entityIn) && !this.u_1723_Y(worldIn, entityIn);
    }

    @Override
    protected boolean n_1700_B(long gameTime) {
        return false;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, Z_530_i owner, long gameTime) {
        if (!this.R_4764_Y(worldIn, owner)) {
            --this.G_564_y;
        } else if (this.u_1723_Y > 0) {
            --this.u_1723_Y;
        } else if (this.G_564_y(worldIn, owner)) {
            owner.t_4043_B().n_1700_B();
            --this.P_1922_E;
            this.u_1723_Y = 5;
        }
    }

    private void n_1700_B(Z_530_i mob, c_1514_x pos) {
        mob.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(pos, this.n_1700_B, 0));
    }

    private boolean J_1907_R(e_3591_l world, Z_530_i mob) {
        return this.R_4764_Y(world, mob) || this.n_1700_B(mob).isPresent();
    }

    private boolean R_4764_Y(e_3591_l world, Z_530_i mob) {
        c_1514_x blockpos = mob.b_2312_j();
        c_1514_x blockpos1 = blockpos.down();
        return this.n_1700_B(world, blockpos) || this.n_1700_B(world, blockpos1);
    }

    private boolean G_564_y(e_3591_l world, Z_530_i mob) {
        return this.n_1700_B(world, mob.b_2312_j());
    }

    @Override
    private boolean n_1700_B(e_3591_l world, c_1514_x pos) {
        return world.getBlockState(pos).n_1700_B(BlockTags.d_2461_k);
    }

    private Optional<c_1514_x> n_1700_B(Z_530_i p_220463_1_) {
        return p_220463_1_.y_1945_D().R_4764_Y(MemoryModuleType.C_2741_M);
    }

    private boolean P_1922_E(e_3591_l world, Z_530_i mob) {
        return !this.R_4764_Y(world, mob) && this.G_564_y <= 0;
    }

    private boolean u_1723_Y(e_3591_l world, Z_530_i mob) {
        return this.R_4764_Y(world, mob) && this.P_1922_E <= 0;
    }

    @Override
    protected /* synthetic */ boolean n_1700_B(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        return this.R_4764_Y(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void R_4764_Y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.G_564_y(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }
}


