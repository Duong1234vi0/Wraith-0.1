/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Random;
import lightning.product.B_4088_l;
import lightning.product.D_38_f;
import lightning.product.K_4096_w;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.N_1216_z;
import lightning.product.Stats;
import lightning.product.T_4041_i;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.V_3137_a;
import lightning.product.SoundEvents;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.DataSlot;
import lightning.product.EnchantedBookItem;
import lightning.product.Items;
import lightning.product.ContainerLevelAccess;

public class Q_1649_j
extends a_2900_S {
    private final Container G_564_y = new N_1216_z(2){

        @Override
        public void J_1907_R() {
            super.J_1907_R();
            Q_1649_j.this.n_1700_B(this);
        }
    };
    private final ContainerLevelAccess v_4262_N;
    private final Random w_1484_f = new Random();
    private final DataSlot t_148_a = DataSlot.n_1700_B();
    public final int[] n_1700_B = new int[3];
    public final int[] J_1907_R = new int[]{-1, -1, -1};
    public final int[] R_4764_Y = new int[]{-1, -1, -1};

    public Q_1649_j(int id, W_3491_f playerInventory) {
        this(id, playerInventory, ContainerLevelAccess.n_1700_B);
    }

    public Q_1649_j(int id, W_3491_f playerInventory, ContainerLevelAccess worldPosCallable) {
        super(MenuType.P_4830_p, id);
        this.v_4262_N = worldPosCallable;
        this.J_1907_R(new Slot(this, this.G_564_y, 0, 15, 47){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return true;
            }

            @Override
            public int G_564_y() {
                return 1;
            }
        });
        this.J_1907_R(new Slot(this, this.G_564_y, 1, 35, 47){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return stack.J_1907_R() == Items.W_4813_f;
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
        this.n_1700_B(DataSlot.n_1700_B(this.n_1700_B, 0));
        this.n_1700_B(DataSlot.n_1700_B(this.n_1700_B, 1));
        this.n_1700_B(DataSlot.n_1700_B(this.n_1700_B, 2));
        this.n_1700_B(this.t_148_a).n_1700_B(playerInventory.P_1922_E.h_973_D());
        this.n_1700_B(DataSlot.n_1700_B(this.J_1907_R, 0));
        this.n_1700_B(DataSlot.n_1700_B(this.J_1907_R, 1));
        this.n_1700_B(DataSlot.n_1700_B(this.J_1907_R, 2));
        this.n_1700_B(DataSlot.n_1700_B(this.R_4764_Y, 0));
        this.n_1700_B(DataSlot.n_1700_B(this.R_4764_Y, 1));
        this.n_1700_B(DataSlot.n_1700_B(this.R_4764_Y, 2));
    }

    @Override
    public void n_1700_B(Container inventoryIn) {
        if (inventoryIn == this.G_564_y) {
            Z_1993_T itemstack = inventoryIn.s_956_w(0);
            if (!itemstack.n_1700_B() && itemstack.C_2741_M()) {
                this.v_4262_N.n_1700_B((b_4507_u p_217002_2_, c_1514_x p_217002_3_) -> {
                    int j = 0;
                    for (int k = -1; k <= 1; ++k) {
                        for (int l = -1; l <= 1; ++l) {
                            if (k == 0 && l == 0 || !p_217002_2_.u_1723_Y(p_217002_3_.add(l, 0, k)) || !p_217002_2_.u_1723_Y(p_217002_3_.add(l, 1, k))) continue;
                            if (p_217002_2_.getBlockState(p_217002_3_.add(l * 2, 0, k * 2)).n_1700_B(a_3742_W.UploadTokenCache)) {
                                ++j;
                            }
                            if (p_217002_2_.getBlockState(p_217002_3_.add(l * 2, 1, k * 2)).n_1700_B(a_3742_W.UploadTokenCache)) {
                                ++j;
                            }
                            if (l == 0 || k == 0) continue;
                            if (p_217002_2_.getBlockState(p_217002_3_.add(l * 2, 0, k)).n_1700_B(a_3742_W.UploadTokenCache)) {
                                ++j;
                            }
                            if (p_217002_2_.getBlockState(p_217002_3_.add(l * 2, 1, k)).n_1700_B(a_3742_W.UploadTokenCache)) {
                                ++j;
                            }
                            if (p_217002_2_.getBlockState(p_217002_3_.add(l, 0, k * 2)).n_1700_B(a_3742_W.UploadTokenCache)) {
                                ++j;
                            }
                            if (!p_217002_2_.getBlockState(p_217002_3_.add(l, 1, k * 2)).n_1700_B(a_3742_W.UploadTokenCache)) continue;
                            ++j;
                        }
                    }
                    this.w_1484_f.setSeed(this.t_148_a.J_1907_R());
                    for (int i1 = 0; i1 < 3; ++i1) {
                        this.n_1700_B[i1] = K_4096_w.n_1700_B(this.w_1484_f, i1, j, itemstack);
                        this.J_1907_R[i1] = -1;
                        this.R_4764_Y[i1] = -1;
                        if (this.n_1700_B[i1] >= i1 + 1) continue;
                        this.n_1700_B[i1] = 0;
                    }
                    for (int j1 = 0; j1 < 3; ++j1) {
                        List<T_4041_i> list;
                        if (this.n_1700_B[j1] <= 0 || (list = this.n_1700_B(itemstack, j1, this.n_1700_B[j1])) == null || list.isEmpty()) continue;
                        T_4041_i enchantmentdata = list.get(this.w_1484_f.nextInt(list.size()));
                        this.J_1907_R[j1] = V_3137_a.z_4693_k.n_1700_B(enchantmentdata.n_1700_B);
                        this.R_4764_Y[j1] = enchantmentdata.J_1907_R;
                    }
                    this.M_588_G();
                });
            } else {
                for (int i = 0; i < 3; ++i) {
                    this.n_1700_B[i] = 0;
                    this.J_1907_R[i] = -1;
                    this.R_4764_Y[i] = -1;
                }
            }
        }
    }

    @Override
    public boolean J_1907_R(a_3913_L playerIn, int id) {
        Z_1993_T itemstack = this.G_564_y.s_956_w(0);
        Z_1993_T itemstack1 = this.G_564_y.s_956_w(1);
        int i = id + 1;
        if ((itemstack1.n_1700_B() || itemstack1.t_4043_B() < i) && !playerIn.C_415_h.G_564_y) {
            return false;
        }
        if (this.n_1700_B[id] <= 0 || itemstack.n_1700_B() || (playerIn.v_165_F < i || playerIn.v_165_F < this.n_1700_B[id]) && !playerIn.C_415_h.G_564_y) {
            return false;
        }
        this.v_4262_N.n_1700_B((b_4507_u p_217003_6_, c_1514_x p_217003_7_) -> {
            Z_1993_T itemstack2 = itemstack;
            List<T_4041_i> list = this.n_1700_B(itemstack, id, this.n_1700_B[id]);
            if (!list.isEmpty()) {
                boolean flag;
                playerIn.J_1907_R(itemstack, i);
                boolean bl = flag = itemstack.J_1907_R() == Items.K_4237_u;
                if (flag) {
                    itemstack2 = new Z_1993_T(Items.M_4472_P);
                    U_2912_j compoundnbt = itemstack.Q_4569_t();
                    if (compoundnbt != null) {
                        itemstack2.R_4764_Y(compoundnbt.v_4262_N());
                    }
                    this.G_564_y.J_1907_R(0, itemstack2);
                }
                for (int j = 0; j < list.size(); ++j) {
                    T_4041_i enchantmentdata = list.get(j);
                    if (flag) {
                        EnchantedBookItem.n_1700_B(itemstack2, enchantmentdata);
                        continue;
                    }
                    itemstack2.n_1700_B(enchantmentdata.n_1700_B, enchantmentdata.J_1907_R);
                }
                if (!playerIn.C_415_h.G_564_y) {
                    itemstack1.v_4262_N(i);
                    if (itemstack1.n_1700_B()) {
                        this.G_564_y.J_1907_R(1, Z_1993_T.J_1907_R);
                    }
                }
                playerIn.J_1907_R(Stats.Ping);
                if (playerIn instanceof B_4088_l) {
                    U_3554_Q.t_148_a.n_1700_B((B_4088_l)playerIn, itemstack2, i);
                }
                this.G_564_y.J_1907_R();
                this.t_148_a.n_1700_B(playerIn.h_973_D());
                this.n_1700_B(this.G_564_y);
                p_217003_6_.n_1700_B((a_3913_L)null, (c_1514_x)p_217003_7_, SoundEvents.h_3270_j, D_38_f.P_1922_E, 1.0f, p_217003_6_.w_1457_N.nextFloat() * 0.1f + 0.9f);
            }
        });
        return true;
    }

    private List<T_4041_i> n_1700_B(Z_1993_T stack, int enchantSlot, int level) {
        this.w_1484_f.setSeed(this.t_148_a.J_1907_R() + enchantSlot);
        List<T_4041_i> list = K_4096_w.J_1907_R(this.w_1484_f, stack, level, false);
        if (stack.J_1907_R() == Items.K_4237_u && list.size() > 1) {
            list.remove(this.w_1484_f.nextInt(list.size()));
        }
        return list;
    }

    public int n_1700_B() {
        Z_1993_T itemstack = this.G_564_y.s_956_w(1);
        return itemstack.n_1700_B() ? 0 : itemstack.t_4043_B();
    }

    public int J_1907_R() {
        return this.t_148_a.J_1907_R();
    }

    @Override
    public void J_1907_R(a_3913_L playerIn) {
        super.J_1907_R(playerIn);
        this.v_4262_N.n_1700_B((b_4507_u p_217004_2_, c_1514_x p_217004_3_) -> this.n_1700_B(playerIn, playerIn.O_508_d, this.G_564_y));
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return Q_1649_j.n_1700_B(this.v_4262_N, playerIn, a_3742_W.E_453_w);
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Slot slot = (Slot)this.P_1922_E.get(index);
        if (slot != null && slot.J_1907_R()) {
            Z_1993_T itemstack1 = slot.n_1700_B();
            itemstack = itemstack1.t_148_a();
            if (index == 0) {
                if (!this.n_1700_B(itemstack1, 2, 38, true)) {
                    return Z_1993_T.J_1907_R;
                }
            } else if (index == 1) {
                if (!this.n_1700_B(itemstack1, 2, 38, true)) {
                    return Z_1993_T.J_1907_R;
                }
            } else if (itemstack1.J_1907_R() == Items.W_4813_f) {
                if (!this.n_1700_B(itemstack1, 1, 2, true)) {
                    return Z_1993_T.J_1907_R;
                }
            } else {
                if (((Slot)this.P_1922_E.get(0)).J_1907_R() || !((Slot)this.P_1922_E.get(0)).n_1700_B(itemstack1)) {
                    return Z_1993_T.J_1907_R;
                }
                Z_1993_T itemstack2 = itemstack1.t_148_a();
                itemstack2.P_1922_E(1);
                itemstack1.v_4262_N(1);
                ((Slot)this.P_1922_E.get(0)).J_1907_R(itemstack2);
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
}


