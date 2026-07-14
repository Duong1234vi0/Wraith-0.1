/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.LootContextParams;
import lightning.product.B_4088_l;
import lightning.product.BlockGetter;
import lightning.product.Container;
import lightning.product.NonNullList;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.f_1402_I;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.ContainerHelper;
import lightning.product.BaseContainerBlockEntity;
import lightning.product.p_4985_U;
import lightning.product.q_1704_m;
import lightning.product.BlockEntityType;

public abstract class V_4572_l
extends BaseContainerBlockEntity {
    @Nullable
    protected g_2336_b G_564_y;
    protected long P_1922_E;

    protected V_4572_l(BlockEntityType<?> typeIn) {
        super(typeIn);
    }

    public static void n_1700_B(BlockGetter reader, Random rand, c_1514_x p_195479_2_, g_2336_b lootTableIn) {
        i_2154_H tileentity = reader.getTileEntity(p_195479_2_);
        if (tileentity instanceof V_4572_l) {
            ((V_4572_l)tileentity).n_1700_B(lootTableIn, rand.nextLong());
        }
    }

    protected boolean J_1907_R(U_2912_j compound) {
        if (compound.R_4764_Y("LootTable", 8)) {
            this.G_564_y = new g_2336_b(compound.M_588_G("LootTable"));
            this.P_1922_E = compound.t_148_a("LootTableSeed");
            return true;
        }
        return false;
    }

    protected boolean R_4764_Y(U_2912_j compound) {
        if (this.G_564_y == null) {
            return false;
        }
        compound.n_1700_B("LootTable", this.G_564_y.toString());
        if (this.P_1922_E != 0L) {
            compound.n_1700_B("LootTableSeed", this.P_1922_E);
        }
        return true;
    }

    public void G_564_y(@Nullable a_3913_L player) {
        if (this.G_564_y != null && this.u_2550_I.T_2506_i() != null) {
            p_4985_U loottable = this.u_2550_I.T_2506_i().F_2624_D().n_1700_B(this.G_564_y);
            if (player instanceof B_4088_l) {
                U_3554_Q.T_2506_i.n_1700_B((B_4088_l)player, this.G_564_y);
            }
            this.G_564_y = null;
            q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B((e_3591_l)this.u_2550_I).n_1700_B(LootContextParams.u_1723_Y, e_2866_D.n_1700_B(this.M_588_G)).n_1700_B(this.P_1922_E);
            if (player != null) {
                lootcontext$builder.n_1700_B(player.Module()).n_1700_B(LootContextParams.n_1700_B, player);
            }
            loottable.n_1700_B((Container)this, lootcontext$builder.n_1700_B(f_1402_I.J_1907_R));
        }
    }

    public void n_1700_B(g_2336_b lootTableIn, long seedIn) {
        this.G_564_y = lootTableIn;
        this.P_1922_E = seedIn;
    }

    @Override
    public boolean Q_2552_b() {
        this.G_564_y(null);
        return this.L_().stream().allMatch(Z_1993_T::n_1700_B);
    }

    @Override
    public Z_1993_T s_956_w(int index) {
        this.G_564_y(null);
        return this.L_().get(index);
    }

    @Override
    public Z_1993_T n_1700_B(int index, int count) {
        this.G_564_y(null);
        Z_1993_T itemstack = ContainerHelper.n_1700_B(this.L_(), index, count);
        if (!itemstack.n_1700_B()) {
            this.J_1907_R();
        }
        return itemstack;
    }

    @Override
    public Z_1993_T u_2550_I(int index) {
        this.G_564_y(null);
        return ContainerHelper.n_1700_B(this.L_(), index);
    }

    @Override
    public void J_1907_R(int index, Z_1993_T stack) {
        this.G_564_y(null);
        this.L_().set(index, stack);
        if (stack.t_4043_B() > this.J_()) {
            stack.P_1922_E(this.J_());
        }
        this.J_1907_R();
    }

    @Override
    public boolean R_4764_Y(a_3913_L player) {
        if (this.u_2550_I.getTileEntity(this.M_588_G) != this) {
            return false;
        }
        return !(player.v_4262_N((double)this.M_588_G.getX() + 0.5, (double)this.M_588_G.getY() + 0.5, (double)this.M_588_G.getZ() + 0.5) > 64.0);
    }

    @Override
    public void C_2741_M() {
        this.L_().clear();
    }

    protected abstract NonNullList<Z_1993_T> L_();

    protected abstract void n_1700_B(NonNullList<Z_1993_T> var1);

    @Override
    public boolean P_1922_E(a_3913_L p_213904_1_) {
        return super.P_1922_E(p_213904_1_) && (this.G_564_y == null || !p_213904_1_.d_2461_k());
    }

    @Override
    @Nullable
    public a_2900_S createMenu(int p_createMenu_1_, W_3491_f p_createMenu_2_, a_3913_L p_createMenu_3_) {
        if (this.P_1922_E(p_createMenu_3_)) {
            this.G_564_y(p_createMenu_2_.P_1922_E);
            return this.n_1700_B(p_createMenu_1_, p_createMenu_2_);
        }
        return null;
    }
}



