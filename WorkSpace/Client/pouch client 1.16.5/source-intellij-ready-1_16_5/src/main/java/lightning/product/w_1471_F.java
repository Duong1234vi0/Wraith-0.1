/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.J_2538_C;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.N_1216_z;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.DyeItem;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_933_M;
import lightning.product.DataSlot;
import lightning.product.g_536_a;
import lightning.product.q_2896_o;
import lightning.product.ContainerLevelAccess;
import lightning.product.x_2414_j;

public class w_1471_F
extends a_2900_S {
    private final ContainerLevelAccess n_1700_B;
    private final DataSlot J_1907_R = DataSlot.n_1700_B();
    private Runnable R_4764_Y = () -> {};
    private final Slot G_564_y;
    private final Slot v_4262_N;
    private final Slot w_1484_f;
    private final Slot t_148_a;
    private long s_956_w;
    private final Container u_2550_I = new N_1216_z(3){

        @Override
        public void J_1907_R() {
            super.J_1907_R();
            w_1471_F.this.n_1700_B(this);
            w_1471_F.this.R_4764_Y.run();
        }
    };
    private final Container M_588_G = new N_1216_z(1){

        @Override
        public void J_1907_R() {
            super.J_1907_R();
            w_1471_F.this.R_4764_Y.run();
        }
    };

    public w_1471_F(int id, W_3491_f playerInventory) {
        this(id, playerInventory, ContainerLevelAccess.n_1700_B);
    }

    public w_1471_F(int id, W_3491_f playerInventory, final ContainerLevelAccess worldCallable) {
        super(MenuType.multiplayerClientSuggestionProvider, id);
        this.n_1700_B = worldCallable;
        this.G_564_y = this.J_1907_R(new Slot(this, this.u_2550_I, 0, 13, 26){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return stack.J_1907_R() instanceof x_2414_j;
            }
        });
        this.v_4262_N = this.J_1907_R(new Slot(this, this.u_2550_I, 1, 33, 26){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return stack.J_1907_R() instanceof DyeItem;
            }
        });
        this.w_1484_f = this.J_1907_R(new Slot(this, this.u_2550_I, 2, 23, 45){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return stack.J_1907_R() instanceof g_536_a;
            }
        });
        this.t_148_a = this.J_1907_R(new Slot(this.M_588_G, 0, 143, 58){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return false;
            }

            @Override
            public Z_1993_T n_1700_B(a_3913_L thePlayer, Z_1993_T stack) {
                w_1471_F.this.G_564_y.n_1700_B(1);
                w_1471_F.this.v_4262_N.n_1700_B(1);
                if (!w_1471_F.this.G_564_y.J_1907_R() || !w_1471_F.this.v_4262_N.J_1907_R()) {
                    w_1471_F.this.J_1907_R.n_1700_B(0);
                }
                worldCallable.n_1700_B((b_4507_u p_216951_1_, c_1514_x p_216951_2_) -> {
                    long l = p_216951_1_.X_933_l();
                    if (w_1471_F.this.s_956_w != l) {
                        p_216951_1_.n_1700_B((a_3913_L)null, (c_1514_x)p_216951_2_, SoundEvents.c_1788_D, D_38_f.P_1922_E, 1.0f, 1.0f);
                        w_1471_F.this.s_956_w = l;
                    }
                });
                return super.n_1700_B(thePlayer, stack);
            }
        });
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.J_1907_R(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.J_1907_R(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
        this.n_1700_B(this.J_1907_R);
    }

    public int n_1700_B() {
        return this.J_1907_R.J_1907_R();
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return w_1471_F.n_1700_B(this.n_1700_B, playerIn, a_3742_W.m_1628_s);
    }

    @Override
    public boolean J_1907_R(a_3913_L playerIn, int id) {
        if (id > 0 && id <= J_2538_C.e_2887_G) {
            this.J_1907_R.n_1700_B(id);
            this.u_1723_Y();
            return true;
        }
        return false;
    }

    @Override
    public void n_1700_B(Container inventoryIn) {
        Z_1993_T itemstack = this.G_564_y.n_1700_B();
        Z_1993_T itemstack1 = this.v_4262_N.n_1700_B();
        Z_1993_T itemstack2 = this.w_1484_f.n_1700_B();
        Z_1993_T itemstack3 = this.t_148_a.n_1700_B();
        if (itemstack3.n_1700_B() || !itemstack.n_1700_B() && !itemstack1.n_1700_B() && this.J_1907_R.J_1907_R() > 0 && (this.J_1907_R.J_1907_R() < J_2538_C.z_4693_k - J_2538_C.g_221_o || !itemstack2.n_1700_B())) {
            if (!itemstack2.n_1700_B() && itemstack2.J_1907_R() instanceof g_536_a) {
                boolean flag;
                U_2912_j compoundnbt = itemstack.n_1700_B("BlockEntityTag");
                boolean bl = flag = compoundnbt.R_4764_Y("Patterns", 9) && !itemstack.n_1700_B() && compoundnbt.G_564_y("Patterns", 10).size() >= 6;
                if (flag) {
                    this.J_1907_R.n_1700_B(0);
                } else {
                    this.J_1907_R.n_1700_B(((g_536_a)itemstack2.J_1907_R()).R_4764_Y().ordinal());
                }
            }
        } else {
            this.t_148_a.J_1907_R(Z_1993_T.J_1907_R);
            this.J_1907_R.n_1700_B(0);
        }
        this.u_1723_Y();
        this.M_588_G();
    }

    public void n_1700_B(Runnable p_217020_1_) {
        this.R_4764_Y = p_217020_1_;
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Slot slot = (Slot)this.P_1922_E.get(index);
        if (slot != null && slot.J_1907_R()) {
            Z_1993_T itemstack1 = slot.n_1700_B();
            itemstack = itemstack1.t_148_a();
            if (index == this.t_148_a.G_564_y) {
                if (!this.n_1700_B(itemstack1, 4, 40, true)) {
                    return Z_1993_T.J_1907_R;
                }
                slot.n_1700_B(itemstack1, itemstack);
            } else if (index != this.v_4262_N.G_564_y && index != this.G_564_y.G_564_y && index != this.w_1484_f.G_564_y ? (itemstack1.J_1907_R() instanceof x_2414_j ? !this.n_1700_B(itemstack1, this.G_564_y.G_564_y, this.G_564_y.G_564_y + 1, false) : (itemstack1.J_1907_R() instanceof DyeItem ? !this.n_1700_B(itemstack1, this.v_4262_N.G_564_y, this.v_4262_N.G_564_y + 1, false) : (itemstack1.J_1907_R() instanceof g_536_a ? !this.n_1700_B(itemstack1, this.w_1484_f.G_564_y, this.w_1484_f.G_564_y + 1, false) : (index >= 4 && index < 31 ? !this.n_1700_B(itemstack1, 31, 40, false) : index >= 31 && index < 40 && !this.n_1700_B(itemstack1, 4, 31, false))))) : !this.n_1700_B(itemstack1, 4, 40, false)) {
                return Z_1993_T.J_1907_R;
            }
            if (itemstack1.n_1700_B()) {
                slot.J_1907_R(Z_1993_T.J_1907_R);
            } else {
                slot.R_4764_Y();
            }
            if (itemstack1.t_4043_B() == itemstack.t_4043_B()) {
                return Z_1993_T.J_1907_R;
            }
            slot.n_1700_B(playerIn, itemstack1);
        }
        return itemstack;
    }

    @Override
    public void J_1907_R(a_3913_L playerIn) {
        super.J_1907_R(playerIn);
        this.n_1700_B.n_1700_B((b_4507_u p_217028_2_, c_1514_x p_217028_3_) -> this.n_1700_B(playerIn, playerIn.O_508_d, this.u_2550_I));
    }

    private void u_1723_Y() {
        if (this.J_1907_R.J_1907_R() > 0) {
            Z_1993_T itemstack = this.G_564_y.n_1700_B();
            Z_1993_T itemstack1 = this.v_4262_N.n_1700_B();
            Z_1993_T itemstack2 = Z_1993_T.J_1907_R;
            if (!itemstack.n_1700_B() && !itemstack1.n_1700_B()) {
                q_2896_o listnbt;
                itemstack2 = itemstack.t_148_a();
                itemstack2.P_1922_E(1);
                J_2538_C bannerpattern = J_2538_C.values()[this.J_1907_R.J_1907_R()];
                e_933_M dyecolor = ((DyeItem)itemstack1.J_1907_R()).R_4764_Y();
                U_2912_j compoundnbt = itemstack2.n_1700_B("BlockEntityTag");
                if (compoundnbt.R_4764_Y("Patterns", 9)) {
                    listnbt = compoundnbt.G_564_y("Patterns", 10);
                } else {
                    listnbt = new q_2896_o();
                    compoundnbt.n_1700_B("Patterns", listnbt);
                }
                U_2912_j compoundnbt1 = new U_2912_j();
                compoundnbt1.n_1700_B("Pattern", bannerpattern.J_1907_R());
                compoundnbt1.J_1907_R("Color", dyecolor.J_1907_R());
                listnbt.add(compoundnbt1);
            }
            if (!Z_1993_T.J_1907_R(itemstack2, this.t_148_a.n_1700_B())) {
                this.t_148_a.J_1907_R(itemstack2);
            }
        }
    }

    public Slot J_1907_R() {
        return this.G_564_y;
    }

    public Slot R_4764_Y() {
        return this.v_4262_N;
    }

    public Slot G_564_y() {
        return this.w_1484_f;
    }

    public Slot P_1922_E() {
        return this.t_148_a;
    }
}


