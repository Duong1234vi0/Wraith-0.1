/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.ChunkStatus;
import lightning.product.BlockGetter;
import lightning.product.N_3869_i;
import lightning.product.MoveToBlockGoal;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.PathfinderMob;
import lightning.product.Items;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;

public class RemoveBlockGoal
extends MoveToBlockGoal {
    private final T_2915_h v_4262_N;
    private final Z_530_i w_1484_f;
    private int t_148_a;

    public RemoveBlockGoal(T_2915_h blockIn, PathfinderMob creature, double speed, int yMax) {
        super(creature, speed, 24, yMax);
        this.v_4262_N = blockIn;
        this.w_1484_f = creature;
    }

    @Override
    public boolean n_1700_B() {
        if (!this.w_1484_f.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
            return false;
        }
        if (this.R_4764_Y > 0) {
            --this.R_4764_Y;
            return false;
        }
        if (this.h_1847_R()) {
            this.R_4764_Y = 20;
            return true;
        }
        this.R_4764_Y = this.n_1700_B(this.n_1700_B);
        return false;
    }

    private boolean h_1847_R() {
        return this.P_1922_E != null && this.n_1700_B((T_1316_M)this.n_1700_B.O_508_d, this.P_1922_E) ? true : this.P_4830_p();
    }

    @Override
    public void G_564_y() {
        super.G_564_y();
        this.w_1484_f.U_1241_n = 1.0f;
    }

    @Override
    public void R_4764_Y() {
        super.R_4764_Y();
        this.t_148_a = 0;
    }

    public void n_1700_B(LevelAccessor worldIn, c_1514_x pos) {
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x pos) {
    }

    @Override
    public void P_1922_E() {
        super.P_1922_E();
        b_4507_u world = this.w_1484_f.O_508_d;
        c_1514_x blockpos = this.w_1484_f.b_2312_j();
        c_1514_x blockpos1 = this.n_1700_B(blockpos, world);
        Random random = this.w_1484_f.M_3508_C();
        if (this.M_588_G() && blockpos1 != null) {
            if (this.t_148_a > 0) {
                e_2866_D vector3d = this.w_1484_f.I_4348_c();
                this.w_1484_f.h_1847_R(vector3d.J_1907_R, 0.3, vector3d.G_564_y);
                if (!world.Y_259_p) {
                    double d0 = 0.08;
                    ((e_3591_l)world).n_1700_B(new N_3869_i(ParticleTypes.d_2427_y, new Z_1993_T(Items.s_4405_m)), (double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.7, (double)blockpos1.getZ() + 0.5, 3, ((double)random.nextFloat() - 0.5) * 0.08, ((double)random.nextFloat() - 0.5) * 0.08, ((double)random.nextFloat() - 0.5) * 0.08, 0.15f);
                }
            }
            if (this.t_148_a % 2 == 0) {
                e_2866_D vector3d1 = this.w_1484_f.I_4348_c();
                this.w_1484_f.h_1847_R(vector3d1.J_1907_R, -0.3, vector3d1.G_564_y);
                if (this.t_148_a % 6 == 0) {
                    this.n_1700_B((LevelAccessor)world, this.P_1922_E);
                }
            }
            if (this.t_148_a > 60) {
                world.n_1700_B(blockpos1, false);
                if (!world.Y_259_p) {
                    for (int i = 0; i < 20; ++i) {
                        double d3 = random.nextGaussian() * 0.02;
                        double d1 = random.nextGaussian() * 0.02;
                        double d2 = random.nextGaussian() * 0.02;
                        ((e_3591_l)world).n_1700_B(ParticleTypes.z_4693_k, (double)blockpos1.getX() + 0.5, blockpos1.getY(), (double)blockpos1.getZ() + 0.5, 1, d3, d1, d2, 0.15f);
                    }
                    this.n_1700_B(world, blockpos1);
                }
            }
            ++this.t_148_a;
        }
    }

    @Nullable
    private c_1514_x n_1700_B(c_1514_x pos, BlockGetter worldIn) {
        c_1514_x[] ablockpos;
        if (worldIn.getBlockState(pos).n_1700_B(this.v_4262_N)) {
            return pos;
        }
        for (c_1514_x blockpos : ablockpos = new c_1514_x[]{pos.down(), pos.west(), pos.east(), pos.north(), pos.south(), pos.down().down()}) {
            if (!worldIn.getBlockState(blockpos).n_1700_B(this.v_4262_N)) continue;
            return blockpos;
        }
        return null;
    }

    @Override
    protected boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
        ChunkAccess ichunk = worldIn.n_1700_B(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.P_4830_p, false);
        if (ichunk == null) {
            return false;
        }
        return ichunk.getBlockState(pos).n_1700_B(this.v_4262_N) && ichunk.getBlockState(pos.up()).v_4262_N() && ichunk.getBlockState(pos.up(2)).v_4262_N();
    }
}


