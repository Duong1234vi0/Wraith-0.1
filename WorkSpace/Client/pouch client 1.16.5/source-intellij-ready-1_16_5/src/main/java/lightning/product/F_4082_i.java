/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Map;
import java.util.stream.Collectors;
import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.N_1216_z;
import lightning.product.W_3491_f;
import lightning.product.ResultContainer;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.AnvilMenu;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.n_4637_L;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.ContainerLevelAccess;

public class F_4082_i
extends a_2900_S {
    private final Container n_1700_B = new ResultContainer();
    private final Container J_1907_R = new N_1216_z(2){

        @Override
        public void J_1907_R() {
            super.J_1907_R();
            F_4082_i.this.n_1700_B(this);
        }
    };
    private final ContainerLevelAccess R_4764_Y;

    public F_4082_i(int p_i50080_1_, W_3491_f playerInventoryIn) {
        this(p_i50080_1_, playerInventoryIn, ContainerLevelAccess.n_1700_B);
    }

    public F_4082_i(int windowIdIn, W_3491_f p_i50081_2_, final ContainerLevelAccess worldPosCallableIn) {
        super(MenuType.Q_4569_t, windowIdIn);
        this.R_4764_Y = worldPosCallableIn;
        this.J_1907_R(new Slot(this, this.J_1907_R, 0, 49, 19){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return stack.P_1922_E() || stack.J_1907_R() == Items.M_4472_P || stack.k_2293_S();
            }
        });
        this.J_1907_R(new Slot(this, this.J_1907_R, 1, 49, 40){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return stack.P_1922_E() || stack.J_1907_R() == Items.M_4472_P || stack.k_2293_S();
            }
        });
        this.J_1907_R(new Slot(this.n_1700_B, 2, 129, 34){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return false;
            }

            @Override
            public Z_1993_T n_1700_B(a_3913_L thePlayer, Z_1993_T stack) {
                worldPosCallableIn.n_1700_B((b_4507_u p_216944_1_, c_1514_x p_216944_2_) -> {
                    int i1;
                    for (int l = this.n_1700_B((b_4507_u)p_216944_1_); l > 0; l -= i1) {
                        i1 = n_4637_L.n_1700_B(l);
                        p_216944_1_.a_(new n_4637_L((b_4507_u)p_216944_1_, p_216944_2_.getX(), (double)p_216944_2_.getY() + 0.5, (double)p_216944_2_.getZ() + 0.5, i1));
                    }
                    p_216944_1_.R_4764_Y(1042, (c_1514_x)p_216944_2_, 0);
                });
                F_4082_i.this.J_1907_R.J_1907_R(0, Z_1993_T.J_1907_R);
                F_4082_i.this.J_1907_R.J_1907_R(1, Z_1993_T.J_1907_R);
                return stack;
            }

            private int n_1700_B(b_4507_u worldIn) {
                int l = 0;
                l += this.P_1922_E(F_4082_i.this.J_1907_R.s_956_w(0));
                if ((l += this.P_1922_E(F_4082_i.this.J_1907_R.s_956_w(1))) > 0) {
                    int i1 = (int)Math.ceil((double)l / 2.0);
                    return i1 + worldIn.w_1457_N.nextInt(i1);
                }
                return 0;
            }

            private int P_1922_E(Z_1993_T stack) {
                int l = 0;
                Map<K_1310_v, Integer> map = K_4096_w.n_1700_B(stack);
                for (Map.Entry<K_1310_v, Integer> entry : map.entrySet()) {
                    K_1310_v enchantment = entry.getKey();
                    Integer integer = entry.getValue();
                    if (enchantment.R_4764_Y()) continue;
                    l += enchantment.n_1700_B(integer);
                }
                return l;
            }
        });
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.J_1907_R(new Slot(p_i50081_2_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.J_1907_R(new Slot(p_i50081_2_, k, 8 + k * 18, 142));
        }
    }

    @Override
    public void n_1700_B(Container inventoryIn) {
        super.n_1700_B(inventoryIn);
        if (inventoryIn == this.J_1907_R) {
            this.n_1700_B();
        }
    }

    private void n_1700_B() {
        boolean flag1;
        Z_1993_T itemstack = this.J_1907_R.s_956_w(0);
        Z_1993_T itemstack1 = this.J_1907_R.s_956_w(1);
        boolean flag = !itemstack.n_1700_B() || !itemstack1.n_1700_B();
        boolean bl = flag1 = !itemstack.n_1700_B() && !itemstack1.n_1700_B();
        if (!flag) {
            this.n_1700_B.J_1907_R(0, Z_1993_T.J_1907_R);
        } else {
            Z_1993_T itemstack2;
            int i;
            boolean flag2;
            boolean bl2 = flag2 = !itemstack.n_1700_B() && itemstack.J_1907_R() != Items.M_4472_P && !itemstack.k_2293_S() || !itemstack1.n_1700_B() && itemstack1.J_1907_R() != Items.M_4472_P && !itemstack1.k_2293_S();
            if (itemstack.t_4043_B() > 1 || itemstack1.t_4043_B() > 1 || !flag1 && flag2) {
                this.n_1700_B.J_1907_R(0, Z_1993_T.J_1907_R);
                this.M_588_G();
                return;
            }
            int j = 1;
            if (flag1) {
                if (itemstack.J_1907_R() != itemstack1.J_1907_R()) {
                    this.n_1700_B.J_1907_R(0, Z_1993_T.J_1907_R);
                    this.M_588_G();
                    return;
                }
                q_1613_l item = itemstack.J_1907_R();
                int k = item.M_588_G() - itemstack.v_4262_N();
                int l = item.M_588_G() - itemstack1.v_4262_N();
                int i1 = k + l + item.M_588_G() * 5 / 100;
                i = Math.max(item.M_588_G() - i1, 0);
                itemstack2 = this.J_1907_R(itemstack, itemstack1);
                if (!itemstack2.P_1922_E()) {
                    if (!Z_1993_T.J_1907_R(itemstack, itemstack1)) {
                        this.n_1700_B.J_1907_R(0, Z_1993_T.J_1907_R);
                        this.M_588_G();
                        return;
                    }
                    j = 2;
                }
            } else {
                boolean flag3 = !itemstack.n_1700_B();
                i = flag3 ? itemstack.v_4262_N() : itemstack1.v_4262_N();
                itemstack2 = flag3 ? itemstack : itemstack1;
            }
            this.n_1700_B.J_1907_R(0, this.n_1700_B(itemstack2, i, j));
        }
        this.M_588_G();
    }

    private Z_1993_T J_1907_R(Z_1993_T copyTo, Z_1993_T copyFrom) {
        Z_1993_T itemstack = copyTo.t_148_a();
        Map<K_1310_v, Integer> map = K_4096_w.n_1700_B(copyFrom);
        for (Map.Entry<K_1310_v, Integer> entry : map.entrySet()) {
            K_1310_v enchantment = entry.getKey();
            if (enchantment.R_4764_Y() && K_4096_w.n_1700_B(enchantment, itemstack) != 0) continue;
            itemstack.n_1700_B(enchantment, entry.getValue());
        }
        return itemstack;
    }

    private Z_1993_T n_1700_B(Z_1993_T stack, int damage, int count) {
        Z_1993_T itemstack = stack.t_148_a();
        itemstack.R_4764_Y("Enchantments");
        itemstack.R_4764_Y("StoredEnchantments");
        if (damage > 0) {
            itemstack.J_1907_R(damage);
        } else {
            itemstack.R_4764_Y("Damage");
        }
        itemstack.P_1922_E(count);
        Map<K_1310_v, Integer> map = K_4096_w.n_1700_B(stack).entrySet().stream().filter(p_217012_0_ -> ((K_1310_v)p_217012_0_.getKey()).R_4764_Y()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        K_4096_w.n_1700_B(map, itemstack);
        itemstack.R_4764_Y(0);
        if (itemstack.J_1907_R() == Items.M_4472_P && map.size() == 0) {
            itemstack = new Z_1993_T(Items.K_4237_u);
            if (stack.Y_601_j()) {
                itemstack.n_1700_B(stack.multiplayerClientSuggestionProvider());
            }
        }
        for (int i = 0; i < map.size(); ++i) {
            itemstack.R_4764_Y(AnvilMenu.G_564_y(itemstack.H_2857_Y()));
        }
        return itemstack;
    }

    @Override
    public void J_1907_R(a_3913_L playerIn) {
        super.J_1907_R(playerIn);
        this.R_4764_Y.n_1700_B((b_4507_u p_217009_2_, c_1514_x p_217009_3_) -> this.n_1700_B(playerIn, (b_4507_u)p_217009_2_, this.J_1907_R));
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return F_4082_i.n_1700_B(this.R_4764_Y, playerIn, a_3742_W.v_2826_q);
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Slot slot = (Slot)this.P_1922_E.get(index);
        if (slot != null && slot.J_1907_R()) {
            Z_1993_T itemstack1 = slot.n_1700_B();
            itemstack = itemstack1.t_148_a();
            Z_1993_T itemstack2 = this.J_1907_R.s_956_w(0);
            Z_1993_T itemstack3 = this.J_1907_R.s_956_w(1);
            if (index == 2) {
                if (!this.n_1700_B(itemstack1, 3, 39, true)) {
                    return Z_1993_T.J_1907_R;
                }
                slot.n_1700_B(itemstack1, itemstack);
            } else if (index != 0 && index != 1 ? (!itemstack2.n_1700_B() && !itemstack3.n_1700_B() ? (index >= 3 && index < 30 ? !this.n_1700_B(itemstack1, 30, 39, false) : index >= 30 && index < 39 && !this.n_1700_B(itemstack1, 3, 30, false)) : !this.n_1700_B(itemstack1, 0, 2, false)) : !this.n_1700_B(itemstack1, 3, 39, false)) {
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
}


