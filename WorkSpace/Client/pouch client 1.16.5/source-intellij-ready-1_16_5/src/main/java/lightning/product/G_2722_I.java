/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.C_4998_y;
import lightning.product.Clearable;
import lightning.product.K_3065_y;
import lightning.product.K_4074_S;
import lightning.product.N_1216_z;
import lightning.product.NonNullList;
import lightning.product.S_3924_b;
import lightning.product.U_2912_j;
import lightning.product.X_1924_A;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.RecipeType;
import lightning.product.i_2154_H;
import lightning.product.ContainerHelper;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.BlockEntityType;
import lightning.product.ParticleTypes;
import lightning.product.u_530_F;

public class G_2722_I
extends i_2154_H
implements Clearable,
X_1924_A {
    private final NonNullList<Z_1993_T> n_1700_B = NonNullList.n_1700_B(4, Z_1993_T.J_1907_R);
    private final int[] J_1907_R = new int[4];
    private final int[] R_4764_Y = new int[4];

    public G_2722_I() {
        super(BlockEntityType.x_607_J);
    }

    @Override
    public void P_1922_E() {
        boolean flag = this.e_4240_b().R_4764_Y(C_4998_y.h_1847_R);
        boolean flag1 = this.u_2550_I.Y_259_p;
        if (flag1) {
            if (flag) {
                this.s_956_w();
            }
        } else if (flag) {
            this.w_1484_f();
        } else {
            for (int i = 0; i < this.n_1700_B.size(); ++i) {
                if (this.J_1907_R[i] <= 0) continue;
                this.J_1907_R[i] = u_530_F.n_1700_B(this.J_1907_R[i] - 2, 0, this.R_4764_Y[i]);
            }
        }
    }

    private void w_1484_f() {
        for (int i = 0; i < this.n_1700_B.size(); ++i) {
            Z_1993_T itemstack = this.n_1700_B.get(i);
            if (itemstack.n_1700_B()) continue;
            int n = i;
            this.J_1907_R[n] = this.J_1907_R[n] + 1;
            if (this.J_1907_R[i] < this.R_4764_Y[i]) continue;
            N_1216_z iinventory = new N_1216_z(itemstack);
            Z_1993_T itemstack1 = this.u_2550_I.s_956_w().n_1700_B(RecipeType.P_1922_E, iinventory, this.u_2550_I).map(campfireRecipe -> campfireRecipe.n_1700_B(iinventory)).orElse(itemstack);
            c_1514_x blockpos = this.x_607_J();
            K_3065_y.n_1700_B(this.u_2550_I, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), itemstack1);
            this.n_1700_B.set(i, Z_1993_T.J_1907_R);
            this.u_2550_I();
        }
    }

    private void s_956_w() {
        b_4507_u world = this.c_3005_b();
        if (world != null) {
            c_1514_x blockpos = this.x_607_J();
            Random random = world.w_1457_N;
            if (random.nextFloat() < 0.11f) {
                for (int i = 0; i < random.nextInt(2) + 2; ++i) {
                    C_4998_y.n_1700_B(world, blockpos, this.e_4240_b().R_4764_Y(C_4998_y.Q_4569_t), false);
                }
            }
            int l = this.e_4240_b().R_4764_Y(C_4998_y.t_1786_h).G_564_y();
            for (int j = 0; j < this.n_1700_B.size(); ++j) {
                if (this.n_1700_B.get(j).n_1700_B() || !(random.nextFloat() < 0.2f)) continue;
                b_257_Y direction = b_257_Y.J_1907_R(Math.floorMod(j + l, 4));
                float f = 0.3125f;
                double d0 = (double)blockpos.getX() + 0.5 - (double)((float)direction.t_148_a() * 0.3125f) + (double)((float)direction.v_4262_N().t_148_a() * 0.3125f);
                double d1 = (double)blockpos.getY() + 0.5;
                double d2 = (double)blockpos.getZ() + 0.5 - (double)((float)direction.u_2550_I() * 0.3125f) + (double)((float)direction.v_4262_N().u_2550_I() * 0.3125f);
                for (int k = 0; k < 4; ++k) {
                    world.n_1700_B(ParticleTypes.B_1668_F, d0, d1, d2, 0.0, 5.0E-4, 0.0);
                }
            }
        }
    }

    public NonNullList<Z_1993_T> n_1700_B() {
        return this.n_1700_B;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.n_1700_B.clear();
        ContainerHelper.J_1907_R(nbt, this.n_1700_B);
        if (nbt.R_4764_Y("CookingTimes", 11)) {
            int[] aint = nbt.h_1847_R("CookingTimes");
            System.arraycopy(aint, 0, this.J_1907_R, 0, Math.min(this.R_4764_Y.length, aint.length));
        }
        if (nbt.R_4764_Y("CookingTotalTimes", 11)) {
            int[] aint1 = nbt.h_1847_R("CookingTotalTimes");
            System.arraycopy(aint1, 0, this.R_4764_Y, 0, Math.min(this.R_4764_Y.length, aint1.length));
        }
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        this.J_1907_R(compound);
        compound.n_1700_B("CookingTimes", this.J_1907_R);
        compound.n_1700_B("CookingTotalTimes", this.R_4764_Y);
        return compound;
    }

    private U_2912_j J_1907_R(U_2912_j compound) {
        super.n_1700_B(compound);
        ContainerHelper.n_1700_B(compound, this.n_1700_B, true);
        return compound;
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket G_() {
        return new ClientboundBlockEntityDataPacket(this.M_588_G, 13, this.H_());
    }

    @Override
    public U_2912_j H_() {
        return this.J_1907_R(new U_2912_j());
    }

    public Optional<S_3924_b> n_1700_B(Z_1993_T itemStackIn) {
        return this.n_1700_B.stream().noneMatch(Z_1993_T::n_1700_B) ? Optional.empty() : this.u_2550_I.s_956_w().n_1700_B(RecipeType.P_1922_E, new N_1216_z(itemStackIn), this.u_2550_I);
    }

    public boolean n_1700_B(Z_1993_T itemStackIn, int cookTime) {
        for (int i = 0; i < this.n_1700_B.size(); ++i) {
            Z_1993_T itemstack = this.n_1700_B.get(i);
            if (!itemstack.n_1700_B()) continue;
            this.R_4764_Y[i] = cookTime;
            this.J_1907_R[i] = 0;
            this.n_1700_B.set(i, itemStackIn.n_1700_B(1));
            this.u_2550_I();
            return true;
        }
        return false;
    }

    private void u_2550_I() {
        this.J_1907_R();
        this.c_3005_b().n_1700_B(this.x_607_J(), this.e_4240_b(), this.e_4240_b(), 3);
    }

    @Override
    public void C_2741_M() {
        this.n_1700_B.clear();
    }

    public void v_4262_N() {
        if (this.u_2550_I != null) {
            if (!this.u_2550_I.Y_259_p) {
                K_3065_y.n_1700_B(this.u_2550_I, this.x_607_J(), this.n_1700_B());
            }
            this.u_2550_I();
        }
    }
}


