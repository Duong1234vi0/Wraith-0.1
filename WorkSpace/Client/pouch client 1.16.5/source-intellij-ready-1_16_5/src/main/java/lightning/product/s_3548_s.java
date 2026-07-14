/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.A_4919_q;
import lightning.product.LootContextParams;
import lightning.product.B_4088_l;
import lightning.product.K_3065_y;
import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.NonNullList;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.f_1402_I;
import lightning.product.g_2336_b;
import lightning.product.ContainerHelper;
import lightning.product.m_3054_I;
import lightning.product.p_4985_U;
import lightning.product.q_1704_m;
import lightning.product.t_3286_u;
import lightning.product.t_5_h;
import lightning.product.x_1688_C;
import lightning.product.y_4319_k;

public abstract class s_3548_s
extends y_4319_k
implements Container,
t_3286_u {
    private NonNullList<Z_1993_T> n_1700_B = NonNullList.n_1700_B(36, Z_1993_T.J_1907_R);
    private boolean J_1907_R = true;
    @Nullable
    private g_2336_b R_4764_Y;
    private long G_564_y;

    protected s_3548_s(t_5_h<?> type, b_4507_u world) {
        super(type, world);
    }

    protected s_3548_s(t_5_h<?> type, double x, double y, double z, b_4507_u world) {
        super(type, world, x, y, z);
    }

    @Override
    public void J_1907_R(P_11_z source) {
        super.J_1907_R(source);
        if (this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
            N_4263_v entity;
            K_3065_y.n_1700_B(this.O_508_d, this, (Container)this);
            if (!this.O_508_d.Y_259_p && (entity = source.s_956_w()) != null && entity.f_4016_n() == t_5_h.g_4106_L) {
                A_4919_q.n_1700_B((a_3913_L)entity, true);
            }
        }
    }

    @Override
    public boolean Q_2552_b() {
        for (Z_1993_T itemstack : this.n_1700_B) {
            if (itemstack.n_1700_B()) continue;
            return false;
        }
        return true;
    }

    @Override
    public Z_1993_T s_956_w(int index) {
        this.G_564_y((a_3913_L)null);
        return this.n_1700_B.get(index);
    }

    @Override
    public Z_1993_T n_1700_B(int index, int count) {
        this.G_564_y((a_3913_L)null);
        return ContainerHelper.n_1700_B(this.n_1700_B, index, count);
    }

    @Override
    public Z_1993_T u_2550_I(int index) {
        this.G_564_y((a_3913_L)null);
        Z_1993_T itemstack = this.n_1700_B.get(index);
        if (itemstack.n_1700_B()) {
            return Z_1993_T.J_1907_R;
        }
        this.n_1700_B.set(index, Z_1993_T.J_1907_R);
        return itemstack;
    }

    @Override
    public void J_1907_R(int index, Z_1993_T stack) {
        this.G_564_y((a_3913_L)null);
        this.n_1700_B.set(index, stack);
        if (!stack.n_1700_B() && stack.t_4043_B() > this.J_()) {
            stack.P_1922_E(this.J_());
        }
    }

    @Override
    public boolean n_1700_B(int inventorySlot, Z_1993_T itemStackIn) {
        if (inventorySlot >= 0 && inventorySlot < this.Y_259_p()) {
            this.J_1907_R(inventorySlot, itemStackIn);
            return true;
        }
        return false;
    }

    @Override
    public void J_1907_R() {
    }

    @Override
    public boolean R_4764_Y(a_3913_L player) {
        if (this.t_4219_U) {
            return false;
        }
        return !(player.G_564_y(this) > 64.0);
    }

    @Override
    @Nullable
    public N_4263_v n_1700_B(e_3591_l server) {
        this.J_1907_R = false;
        return super.n_1700_B(server);
    }

    @Override
    public void Ops() {
        if (!this.O_508_d.Y_259_p && this.J_1907_R) {
            K_3065_y.n_1700_B(this.O_508_d, this, (Container)this);
        }
        super.Ops();
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.R_4764_Y != null) {
            compound.n_1700_B("LootTable", this.R_4764_Y.toString());
            if (this.G_564_y != 0L) {
                compound.n_1700_B("LootTableSeed", this.G_564_y);
            }
        } else {
            ContainerHelper.n_1700_B(compound, this.n_1700_B);
        }
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.n_1700_B = NonNullList.n_1700_B(this.Y_259_p(), Z_1993_T.J_1907_R);
        if (compound.R_4764_Y("LootTable", 8)) {
            this.R_4764_Y = new g_2336_b(compound.M_588_G("LootTable"));
            this.G_564_y = compound.t_148_a("LootTableSeed");
        } else {
            ContainerHelper.J_1907_R(compound, this.n_1700_B);
        }
    }

    @Override
    public m_3054_I n_1700_B(a_3913_L player, x_1688_C hand) {
        player.n_1700_B(this);
        if (!player.O_508_d.Y_259_p) {
            A_4919_q.n_1700_B(player, true);
            return m_3054_I.J_1907_R;
        }
        return m_3054_I.n_1700_B;
    }

    @Override
    protected void v_4262_N() {
        float f = 0.98f;
        if (this.R_4764_Y == null) {
            int i = 15 - a_2900_S.J_1907_R(this);
            f += (float)i * 0.001f;
        }
        this.v_4262_N(this.I_4348_c().G_564_y(f, 0.0, f));
    }

    public void G_564_y(@Nullable a_3913_L player) {
        if (this.R_4764_Y != null && this.O_508_d.T_2506_i() != null) {
            p_4985_U loottable = this.O_508_d.T_2506_i().F_2624_D().n_1700_B(this.R_4764_Y);
            if (player instanceof B_4088_l) {
                U_3554_Q.T_2506_i.n_1700_B((B_4088_l)player, this.R_4764_Y);
            }
            this.R_4764_Y = null;
            q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B((e_3591_l)this.O_508_d).n_1700_B(LootContextParams.u_1723_Y, this.s_4990_V()).n_1700_B(this.G_564_y);
            if (player != null) {
                lootcontext$builder.n_1700_B(player.Module()).n_1700_B(LootContextParams.n_1700_B, player);
            }
            loottable.n_1700_B((Container)this, lootcontext$builder.n_1700_B(f_1402_I.J_1907_R));
        }
    }

    @Override
    public void C_2741_M() {
        this.G_564_y((a_3913_L)null);
        this.n_1700_B.clear();
    }

    public void n_1700_B(g_2336_b lootTableIn, long lootTableSeedIn) {
        this.R_4764_Y = lootTableIn;
        this.G_564_y = lootTableSeedIn;
    }

    @Override
    @Nullable
    public a_2900_S createMenu(int p_createMenu_1_, W_3491_f p_createMenu_2_, a_3913_L p_createMenu_3_) {
        if (this.R_4764_Y != null && p_createMenu_3_.d_2461_k()) {
            return null;
        }
        this.G_564_y(p_createMenu_2_.P_1922_E);
        return this.n_1700_B(p_createMenu_1_, p_createMenu_2_);
    }

    protected abstract a_2900_S n_1700_B(int var1, W_3491_f var2);
}



