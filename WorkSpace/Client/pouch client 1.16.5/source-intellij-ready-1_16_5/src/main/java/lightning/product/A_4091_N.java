/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Arrays;
import javax.annotation.Nullable;
import lightning.product.E_414_E;
import lightning.product.F_2904_S;
import lightning.product.K_3065_y;
import lightning.product.K_4074_S;
import lightning.product.NonNullList;
import lightning.product.U_2912_j;
import lightning.product.W_3491_f;
import lightning.product.X_1924_A;
import lightning.product.BrewingStandMenu;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.ContainerHelper;
import lightning.product.ContainerData;
import lightning.product.BaseContainerBlockEntity;
import lightning.product.WorldlyContainer;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.BlockEntityType;
import lightning.product.x_282_a;
import lightning.product.BrewingStandBlock;

public class A_4091_N
extends BaseContainerBlockEntity
implements X_1924_A,
WorldlyContainer {
    private static final int[] J_1907_R = new int[]{3};
    private static final int[] R_4764_Y = new int[]{0, 1, 2, 3};
    private static final int[] G_564_y = new int[]{0, 1, 2, 4};
    private NonNullList<Z_1993_T> P_1922_E = NonNullList.n_1700_B(5, Z_1993_T.J_1907_R);
    private int u_1723_Y;
    private boolean[] v_4262_N;
    private q_1613_l w_1484_f;
    private int t_148_a;
    protected final ContainerData n_1700_B = new ContainerData(){

        @Override
        public int n_1700_B(int index) {
            switch (index) {
                case 0: {
                    return A_4091_N.this.u_1723_Y;
                }
                case 1: {
                    return A_4091_N.this.t_148_a;
                }
            }
            return 0;
        }

        @Override
        public void n_1700_B(int index, int value) {
            switch (index) {
                case 0: {
                    A_4091_N.this.u_1723_Y = value;
                    break;
                }
                case 1: {
                    A_4091_N.this.t_148_a = value;
                }
            }
        }

        @Override
        public int n_1700_B() {
            return 2;
        }
    };

    public A_4091_N() {
        super(BlockEntityType.u_2550_I);
    }

    @Override
    protected x_282_a F_() {
        return new F_2904_S("container.brewing");
    }

    @Override
    public int Y_259_p() {
        return this.P_1922_E.size();
    }

    @Override
    public boolean Q_2552_b() {
        for (Z_1993_T itemstack : this.P_1922_E) {
            if (itemstack.n_1700_B()) continue;
            return false;
        }
        return true;
    }

    @Override
    public void P_1922_E() {
        boolean[] aboolean;
        Z_1993_T itemstack = this.P_1922_E.get(4);
        if (this.t_148_a <= 0 && itemstack.J_1907_R() == Items.C_3528_u) {
            this.t_148_a = 20;
            itemstack.v_4262_N(1);
            this.J_1907_R();
        }
        boolean flag = this.w_1484_f();
        boolean flag1 = this.u_1723_Y > 0;
        Z_1993_T itemstack1 = this.P_1922_E.get(3);
        if (flag1) {
            boolean flag2;
            --this.u_1723_Y;
            boolean bl = flag2 = this.u_1723_Y == 0;
            if (flag2 && flag) {
                this.s_956_w();
                this.J_1907_R();
            } else if (!flag) {
                this.u_1723_Y = 0;
                this.J_1907_R();
            } else if (this.w_1484_f != itemstack1.J_1907_R()) {
                this.u_1723_Y = 0;
                this.J_1907_R();
            }
        } else if (flag && this.t_148_a > 0) {
            --this.t_148_a;
            this.u_1723_Y = 400;
            this.w_1484_f = itemstack1.J_1907_R();
            this.J_1907_R();
        }
        if (!this.u_2550_I.Y_259_p && !Arrays.equals(aboolean = this.v_4262_N(), this.v_4262_N)) {
            this.v_4262_N = aboolean;
            K_4074_S blockstate = this.u_2550_I.getBlockState(this.x_607_J());
            if (!(blockstate.J_1907_R() instanceof BrewingStandBlock)) {
                return;
            }
            for (int i = 0; i < BrewingStandBlock.P_4830_p.length; ++i) {
                blockstate = (K_4074_S)blockstate.n_1700_B(BrewingStandBlock.P_4830_p[i], aboolean[i]);
            }
            this.u_2550_I.n_1700_B(this.M_588_G, blockstate, 2);
        }
    }

    public boolean[] v_4262_N() {
        boolean[] aboolean = new boolean[3];
        for (int i = 0; i < 3; ++i) {
            if (this.P_1922_E.get(i).n_1700_B()) continue;
            aboolean[i] = true;
        }
        return aboolean;
    }

    private boolean w_1484_f() {
        Z_1993_T itemstack = this.P_1922_E.get(3);
        if (itemstack.n_1700_B()) {
            return false;
        }
        if (!E_414_E.n_1700_B(itemstack)) {
            return false;
        }
        for (int i = 0; i < 3; ++i) {
            Z_1993_T itemstack1 = this.P_1922_E.get(i);
            if (itemstack1.n_1700_B() || !E_414_E.n_1700_B(itemstack1, itemstack)) continue;
            return true;
        }
        return false;
    }

    private void s_956_w() {
        Z_1993_T itemstack = this.P_1922_E.get(3);
        for (int i = 0; i < 3; ++i) {
            this.P_1922_E.set(i, E_414_E.G_564_y(itemstack, this.P_1922_E.get(i)));
        }
        itemstack.v_4262_N(1);
        c_1514_x blockpos = this.x_607_J();
        if (itemstack.J_1907_R().multiplayerClientSuggestionProvider()) {
            Z_1993_T itemstack1 = new Z_1993_T(itemstack.J_1907_R().t_1786_h());
            if (itemstack.n_1700_B()) {
                itemstack = itemstack1;
            } else if (!this.u_2550_I.Y_259_p) {
                K_3065_y.n_1700_B(this.u_2550_I, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), itemstack1);
            }
        }
        this.P_1922_E.set(3, itemstack);
        this.u_2550_I.R_4764_Y(1035, blockpos, 0);
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.P_1922_E = NonNullList.n_1700_B(this.Y_259_p(), Z_1993_T.J_1907_R);
        ContainerHelper.J_1907_R(nbt, this.P_1922_E);
        this.u_1723_Y = nbt.v_4262_N("BrewTime");
        this.t_148_a = nbt.u_1723_Y("Fuel");
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("BrewTime", (short)this.u_1723_Y);
        ContainerHelper.n_1700_B(compound, this.P_1922_E);
        compound.n_1700_B("Fuel", (byte)this.t_148_a);
        return compound;
    }

    @Override
    public Z_1993_T s_956_w(int index) {
        return index >= 0 && index < this.P_1922_E.size() ? this.P_1922_E.get(index) : Z_1993_T.J_1907_R;
    }

    @Override
    public Z_1993_T n_1700_B(int index, int count) {
        return ContainerHelper.n_1700_B(this.P_1922_E, index, count);
    }

    @Override
    public Z_1993_T u_2550_I(int index) {
        return ContainerHelper.n_1700_B(this.P_1922_E, index);
    }

    @Override
    public void J_1907_R(int index, Z_1993_T stack) {
        if (index >= 0 && index < this.P_1922_E.size()) {
            this.P_1922_E.set(index, stack);
        }
    }

    @Override
    public boolean R_4764_Y(a_3913_L player) {
        if (this.u_2550_I.getTileEntity(this.M_588_G) != this) {
            return false;
        }
        return !(player.v_4262_N((double)this.M_588_G.getX() + 0.5, (double)this.M_588_G.getY() + 0.5, (double)this.M_588_G.getZ() + 0.5) > 64.0);
    }

    @Override
    public boolean a_(int index, Z_1993_T stack) {
        if (index == 3) {
            return E_414_E.n_1700_B(stack);
        }
        q_1613_l item = stack.J_1907_R();
        if (index == 4) {
            return item == Items.C_3528_u;
        }
        return (item == Items.j_2461_G || item == Items.g_2492_v || item == Items.NetherrackBlock || item == Items.Y_3588_g) && this.s_956_w(index).n_1700_B();
    }

    @Override
    public int[] n_1700_B(b_257_Y side) {
        if (side == b_257_Y.J_1907_R) {
            return J_1907_R;
        }
        return side == b_257_Y.n_1700_B ? R_4764_Y : G_564_y;
    }

    @Override
    public boolean n_1700_B(int index, Z_1993_T itemStackIn, @Nullable b_257_Y direction) {
        return this.a_(index, itemStackIn);
    }

    @Override
    public boolean J_1907_R(int index, Z_1993_T stack, b_257_Y direction) {
        if (index == 3) {
            return stack.J_1907_R() == Items.Y_3588_g;
        }
        return true;
    }

    @Override
    public void C_2741_M() {
        this.P_1922_E.clear();
    }

    @Override
    protected a_2900_S n_1700_B(int id, W_3491_f player) {
        return new BrewingStandMenu(id, player, this, this.n_1700_B);
    }
}


