/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.StringUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.util.Map;
import lightning.product.K_1310_v;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.MenuType;
import lightning.product.R_1120_N;
import lightning.product.U_2871_b;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.DataSlot;
import lightning.product.EnchantedBookItem;
import lightning.product.BlockTags;
import lightning.product.Items;
import lightning.product.t_2321_d;
import lightning.product.ContainerLevelAccess;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnvilMenu
extends R_1120_N {
    private static final Logger v_4262_N = LogManager.getLogger();
    private int w_1484_f;
    private String t_148_a;
    private final DataSlot s_956_w = DataSlot.n_1700_B();

    public AnvilMenu(int id, W_3491_f playerInventory) {
        this(id, playerInventory, ContainerLevelAccess.n_1700_B);
    }

    public AnvilMenu(int id, W_3491_f playerInventory, ContainerLevelAccess worldPosCallable) {
        super(MenuType.w_1484_f, id, playerInventory, worldPosCallable);
        this.n_1700_B(this.s_956_w);
    }

    @Override
    protected boolean n_1700_B(K_4074_S p_230302_1_) {
        return p_230302_1_.n_1700_B(BlockTags.e_4240_b);
    }

    @Override
    protected boolean n_1700_B(a_3913_L p_230303_1_, boolean p_230303_2_) {
        return (p_230303_1_.C_415_h.G_564_y || p_230303_1_.v_165_F >= this.s_956_w.J_1907_R()) && this.s_956_w.J_1907_R() > 0;
    }

    @Override
    protected Z_1993_T n_1700_B(a_3913_L p_230301_1_, Z_1993_T p_230301_2_) {
        if (!p_230301_1_.C_415_h.G_564_y) {
            p_230301_1_.w_1457_N(-this.s_956_w.J_1907_R());
        }
        this.J_1907_R.J_1907_R(0, Z_1993_T.J_1907_R);
        if (this.w_1484_f > 0) {
            Z_1993_T itemstack = this.J_1907_R.s_956_w(1);
            if (!itemstack.n_1700_B() && itemstack.t_4043_B() > this.w_1484_f) {
                itemstack.v_4262_N(this.w_1484_f);
                this.J_1907_R.J_1907_R(1, itemstack);
            } else {
                this.J_1907_R.J_1907_R(1, Z_1993_T.J_1907_R);
            }
        } else {
            this.J_1907_R.J_1907_R(1, Z_1993_T.J_1907_R);
        }
        this.s_956_w.n_1700_B(0);
        this.R_4764_Y.n_1700_B((b_4507_u p_234633_1_, c_1514_x p_234633_2_) -> {
            K_4074_S blockstate = p_234633_1_.getBlockState((c_1514_x)p_234633_2_);
            if (!p_230301_1_.C_415_h.G_564_y && blockstate.n_1700_B(BlockTags.e_4240_b) && p_230301_1_.M_3508_C().nextFloat() < 0.12f) {
                K_4074_S blockstate1 = t_2321_d.w_1484_f(blockstate);
                if (blockstate1 == null) {
                    p_234633_1_.n_1700_B((c_1514_x)p_234633_2_, false);
                    p_234633_1_.R_4764_Y(1029, (c_1514_x)p_234633_2_, 0);
                } else {
                    p_234633_1_.n_1700_B((c_1514_x)p_234633_2_, blockstate1, 2);
                    p_234633_1_.R_4764_Y(1030, (c_1514_x)p_234633_2_, 0);
                }
            } else {
                p_234633_1_.R_4764_Y(1030, (c_1514_x)p_234633_2_, 0);
            }
        });
        return p_230301_2_;
    }

    @Override
    public void n_1700_B() {
        Z_1993_T itemstack = this.J_1907_R.s_956_w(0);
        this.s_956_w.n_1700_B(1);
        int i = 0;
        int j = 0;
        int k = 0;
        if (itemstack.n_1700_B()) {
            this.n_1700_B.J_1907_R(0, Z_1993_T.J_1907_R);
            this.s_956_w.n_1700_B(0);
        } else {
            Z_1993_T itemstack1 = itemstack.t_148_a();
            Z_1993_T itemstack2 = this.J_1907_R.s_956_w(1);
            Map<K_1310_v, Integer> map = K_4096_w.n_1700_B(itemstack1);
            j = j + itemstack.H_2857_Y() + (itemstack2.n_1700_B() ? 0 : itemstack2.H_2857_Y());
            this.w_1484_f = 0;
            if (!itemstack2.n_1700_B()) {
                boolean flag;
                boolean bl = flag = itemstack2.J_1907_R() == Items.M_4472_P && !EnchantedBookItem.G_564_y(itemstack2).isEmpty();
                if (itemstack1.P_1922_E() && itemstack1.J_1907_R().n_1700_B(itemstack, itemstack2)) {
                    int i3;
                    int l2 = Math.min(itemstack1.v_4262_N(), itemstack1.w_1484_f() / 4);
                    if (l2 <= 0) {
                        this.n_1700_B.J_1907_R(0, Z_1993_T.J_1907_R);
                        this.s_956_w.n_1700_B(0);
                        return;
                    }
                    for (i3 = 0; l2 > 0 && i3 < itemstack2.t_4043_B(); ++i3) {
                        int j3 = itemstack1.v_4262_N() - l2;
                        itemstack1.J_1907_R(j3);
                        ++i;
                        l2 = Math.min(itemstack1.v_4262_N(), itemstack1.w_1484_f() / 4);
                    }
                    this.w_1484_f = i3;
                } else {
                    if (!(flag || itemstack1.J_1907_R() == itemstack2.J_1907_R() && itemstack1.P_1922_E())) {
                        this.n_1700_B.J_1907_R(0, Z_1993_T.J_1907_R);
                        this.s_956_w.n_1700_B(0);
                        return;
                    }
                    if (itemstack1.P_1922_E() && !flag) {
                        int l = itemstack.w_1484_f() - itemstack.v_4262_N();
                        int i1 = itemstack2.w_1484_f() - itemstack2.v_4262_N();
                        int j1 = i1 + itemstack1.w_1484_f() * 12 / 100;
                        int k1 = l + j1;
                        int l1 = itemstack1.w_1484_f() - k1;
                        if (l1 < 0) {
                            l1 = 0;
                        }
                        if (l1 < itemstack1.v_4262_N()) {
                            itemstack1.J_1907_R(l1);
                            i += 2;
                        }
                    }
                    Map<K_1310_v, Integer> map1 = K_4096_w.n_1700_B(itemstack2);
                    boolean flag2 = false;
                    boolean flag3 = false;
                    for (K_1310_v enchantment1 : map1.keySet()) {
                        int j2;
                        if (enchantment1 == null) continue;
                        int i2 = map.getOrDefault(enchantment1, 0);
                        j2 = i2 == (j2 = map1.get(enchantment1).intValue()) ? j2 + 1 : Math.max(j2, i2);
                        boolean flag1 = enchantment1.n_1700_B(itemstack);
                        if (this.G_564_y.C_415_h.G_564_y || itemstack.J_1907_R() == Items.M_4472_P) {
                            flag1 = true;
                        }
                        for (K_1310_v enchantment : map.keySet()) {
                            if (enchantment == enchantment1 || enchantment1.J_1907_R(enchantment)) continue;
                            flag1 = false;
                            ++i;
                        }
                        if (!flag1) {
                            flag3 = true;
                            continue;
                        }
                        flag2 = true;
                        if (j2 > enchantment1.n_1700_B()) {
                            j2 = enchantment1.n_1700_B();
                        }
                        map.put(enchantment1, j2);
                        int k3 = 0;
                        switch (enchantment1.G_564_y()) {
                            case n_1700_B: {
                                k3 = 1;
                                break;
                            }
                            case J_1907_R: {
                                k3 = 2;
                                break;
                            }
                            case R_4764_Y: {
                                k3 = 4;
                                break;
                            }
                            case G_564_y: {
                                k3 = 8;
                            }
                        }
                        if (flag) {
                            k3 = Math.max(1, k3 / 2);
                        }
                        i += k3 * j2;
                        if (itemstack.t_4043_B() <= 1) continue;
                        i = 40;
                    }
                    if (flag3 && !flag2) {
                        this.n_1700_B.J_1907_R(0, Z_1993_T.J_1907_R);
                        this.s_956_w.n_1700_B(0);
                        return;
                    }
                }
            }
            if (StringUtils.isBlank((CharSequence)this.t_148_a)) {
                if (itemstack.Y_601_j()) {
                    k = 1;
                    i += k;
                    itemstack1.w_1457_N();
                }
            } else if (!this.t_148_a.equals(itemstack.multiplayerClientSuggestionProvider().getString())) {
                k = 1;
                i += k;
                itemstack1.n_1700_B(new U_2871_b(this.t_148_a));
            }
            this.s_956_w.n_1700_B(j + i);
            if (i <= 0) {
                itemstack1 = Z_1993_T.J_1907_R;
            }
            if (k == i && k > 0 && this.s_956_w.J_1907_R() >= 40) {
                this.s_956_w.n_1700_B(39);
            }
            if (this.s_956_w.J_1907_R() >= 40 && !this.G_564_y.C_415_h.G_564_y) {
                itemstack1 = Z_1993_T.J_1907_R;
            }
            if (!itemstack1.n_1700_B()) {
                int k2 = itemstack1.H_2857_Y();
                if (!itemstack2.n_1700_B() && k2 < itemstack2.H_2857_Y()) {
                    k2 = itemstack2.H_2857_Y();
                }
                if (k != i || k == 0) {
                    k2 = AnvilMenu.G_564_y(k2);
                }
                itemstack1.R_4764_Y(k2);
                K_4096_w.n_1700_B(map, itemstack1);
            }
            this.n_1700_B.J_1907_R(0, itemstack1);
            this.M_588_G();
        }
    }

    public static int G_564_y(int oldRepairCost) {
        return oldRepairCost * 2 + 1;
    }

    public void n_1700_B(String newName) {
        this.t_148_a = newName;
        if (this.n_1700_B(2).J_1907_R()) {
            Z_1993_T itemstack = this.n_1700_B(2).n_1700_B();
            if (StringUtils.isBlank((CharSequence)newName)) {
                itemstack.w_1457_N();
            } else {
                itemstack.n_1700_B(new U_2871_b(this.t_148_a));
            }
        }
        this.n_1700_B();
    }

    public int J_1907_R() {
        return this.s_956_w.J_1907_R();
    }
}


