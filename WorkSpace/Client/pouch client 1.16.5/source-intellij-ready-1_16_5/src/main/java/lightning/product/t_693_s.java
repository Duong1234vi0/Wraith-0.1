/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.Container;
import lightning.product.ChestMenu;
import lightning.product.NonNullList;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.V_4572_l;
import lightning.product.W_3491_f;
import lightning.product.SoundEvent;
import lightning.product.X_1924_A;
import lightning.product.Z_1993_T;
import lightning.product.LidBlockEntity;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.a_4391_G;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.ContainerHelper;
import lightning.product.BaseContainerBlockEntity;
import lightning.product.p_1429_o;
import lightning.product.BlockEntityType;
import lightning.product.u_530_F;
import lightning.product.v_3445_Z;
import lightning.product.x_282_a;

public class t_693_s
extends V_4572_l
implements X_1924_A,
LidBlockEntity {
    private NonNullList<Z_1993_T> u_1723_Y = NonNullList.n_1700_B(27, Z_1993_T.J_1907_R);
    protected float n_1700_B;
    protected float J_1907_R;
    protected int R_4764_Y;
    private int v_4262_N;

    protected t_693_s(BlockEntityType<?> typeIn) {
        super(typeIn);
    }

    public t_693_s() {
        this(BlockEntityType.J_1907_R);
    }

    @Override
    public int Y_259_p() {
        return 27;
    }

    @Override
    protected x_282_a F_() {
        return new F_2904_S("container.chest");
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.u_1723_Y = NonNullList.n_1700_B(this.Y_259_p(), Z_1993_T.J_1907_R);
        if (!this.J_1907_R(nbt)) {
            ContainerHelper.J_1907_R(nbt, this.u_1723_Y);
        }
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (!this.R_4764_Y(compound)) {
            ContainerHelper.n_1700_B(compound, this.u_1723_Y);
        }
        return compound;
    }

    @Override
    public void P_1922_E() {
        int i = this.M_588_G.getX();
        int j = this.M_588_G.getY();
        int k = this.M_588_G.getZ();
        ++this.v_4262_N;
        this.R_4764_Y = t_693_s.n_1700_B(this.u_2550_I, this, this.v_4262_N, i, j, k, this.R_4764_Y);
        this.J_1907_R = this.n_1700_B;
        float f = 0.1f;
        if (this.R_4764_Y > 0 && this.n_1700_B == 0.0f) {
            this.n_1700_B(SoundEvents.d_4007_L);
        }
        if (this.R_4764_Y == 0 && this.n_1700_B > 0.0f || this.R_4764_Y > 0 && this.n_1700_B < 1.0f) {
            float f1 = this.n_1700_B;
            this.n_1700_B = this.R_4764_Y > 0 ? (this.n_1700_B += 0.1f) : (this.n_1700_B -= 0.1f);
            if (this.n_1700_B > 1.0f) {
                this.n_1700_B = 1.0f;
            }
            float f2 = 0.5f;
            if (this.n_1700_B < 0.5f && f1 >= 0.5f) {
                this.n_1700_B(SoundEvents.y_2772_m);
            }
            if (this.n_1700_B < 0.0f) {
                this.n_1700_B = 0.0f;
            }
        }
    }

    public static int n_1700_B(b_4507_u p_213977_0_, BaseContainerBlockEntity p_213977_1_, int p_213977_2_, int p_213977_3_, int p_213977_4_, int p_213977_5_, int p_213977_6_) {
        if (!p_213977_0_.Y_259_p && p_213977_6_ != 0 && (p_213977_2_ + p_213977_3_ + p_213977_4_ + p_213977_5_) % 200 == 0) {
            p_213977_6_ = t_693_s.n_1700_B(p_213977_0_, p_213977_1_, p_213977_3_, p_213977_4_, p_213977_5_);
        }
        return p_213977_6_;
    }

    public static int n_1700_B(b_4507_u p_213976_0_, BaseContainerBlockEntity p_213976_1_, int p_213976_2_, int p_213976_3_, int p_213976_4_) {
        int i = 0;
        float f = 5.0f;
        for (a_3913_L playerentity : p_213976_0_.n_1700_B(a_3913_L.class, new I_4817_s((float)p_213976_2_ - 5.0f, (float)p_213976_3_ - 5.0f, (float)p_213976_4_ - 5.0f, (float)(p_213976_2_ + 1) + 5.0f, (float)(p_213976_3_ + 1) + 5.0f, (float)(p_213976_4_ + 1) + 5.0f))) {
            Container iinventory;
            if (!(playerentity.H_1873_g instanceof ChestMenu) || (iinventory = ((ChestMenu)playerentity.H_1873_g).n_1700_B()) != p_213976_1_ && (!(iinventory instanceof a_4391_G) || !((a_4391_G)iinventory).n_1700_B(p_213976_1_))) continue;
            ++i;
        }
        return i;
    }

    private void n_1700_B(SoundEvent soundIn) {
        p_1429_o chesttype = this.e_4240_b().R_4764_Y(v_3445_Z.Q_4569_t);
        if (chesttype != p_1429_o.J_1907_R) {
            double d0 = (double)this.M_588_G.getX() + 0.5;
            double d1 = (double)this.M_588_G.getY() + 0.5;
            double d2 = (double)this.M_588_G.getZ() + 0.5;
            if (chesttype == p_1429_o.R_4764_Y) {
                b_257_Y direction = v_3445_Z.t_148_a(this.e_4240_b());
                d0 += (double)direction.t_148_a() * 0.5;
                d2 += (double)direction.u_2550_I() * 0.5;
            }
            this.u_2550_I.n_1700_B((a_3913_L)null, d0, d1, d2, soundIn, D_38_f.P_1922_E, 0.5f, this.u_2550_I.w_1457_N.nextFloat() * 0.1f + 0.9f);
        }
    }

    @Override
    public boolean a_(int id, int type) {
        if (id == 1) {
            this.R_4764_Y = type;
            return true;
        }
        return super.a_(id, type);
    }

    @Override
    public void b_(a_3913_L player) {
        if (!player.d_2461_k()) {
            if (this.R_4764_Y < 0) {
                this.R_4764_Y = 0;
            }
            ++this.R_4764_Y;
            this.v_4262_N();
        }
    }

    @Override
    public void J_1907_R(a_3913_L player) {
        if (!player.d_2461_k()) {
            --this.R_4764_Y;
            this.v_4262_N();
        }
    }

    protected void v_4262_N() {
        T_2915_h block = this.e_4240_b().J_1907_R();
        if (block instanceof v_3445_Z) {
            this.u_2550_I.n_1700_B(this.M_588_G, block, 1, this.R_4764_Y);
            this.u_2550_I.J_1907_R(this.M_588_G, block);
        }
    }

    @Override
    protected NonNullList<Z_1993_T> L_() {
        return this.u_1723_Y;
    }

    @Override
    protected void n_1700_B(NonNullList<Z_1993_T> itemsIn) {
        this.u_1723_Y = itemsIn;
    }

    @Override
    public float n_1700_B(float partialTicks) {
        return u_530_F.v_4262_N(partialTicks, this.J_1907_R, this.n_1700_B);
    }

    public static int n_1700_B(BlockGetter reader, c_1514_x posIn) {
        i_2154_H tileentity;
        K_4074_S blockstate = reader.getBlockState(posIn);
        if (blockstate.J_1907_R().G_564_y() && (tileentity = reader.getTileEntity(posIn)) instanceof t_693_s) {
            return ((t_693_s)tileentity).R_4764_Y;
        }
        return 0;
    }

    public static void n_1700_B(t_693_s chest, t_693_s otherChest) {
        NonNullList<Z_1993_T> nonnulllist = chest.L_();
        chest.n_1700_B(otherChest.L_());
        otherChest.n_1700_B(nonnulllist);
    }

    @Override
    protected a_2900_S n_1700_B(int id, W_3491_f player) {
        return ChestMenu.n_1700_B(id, player, this);
    }
}


