/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.NonNullList;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.ContainerListener;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.DataSlot;
import lightning.product.i_2154_H;
import lightning.product.ContainerData;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.CrashReportCategory;
import lightning.product.u_530_F;
import lightning.product.ContainerLevelAccess;

public abstract class a_2900_S {
    private final NonNullList<Z_1993_T> n_1700_B = NonNullList.n_1700_B();
    public final List<Slot> P_1922_E = Lists.newArrayList();
    private final List<DataSlot> J_1907_R = Lists.newArrayList();
    @Nullable
    private final MenuType<?> R_4764_Y;
    public final int u_1723_Y;
    private short G_564_y;
    private int v_4262_N = -1;
    private int w_1484_f;
    private final Set<Slot> t_148_a = Sets.newHashSet();
    private final List<ContainerListener> s_956_w = Lists.newArrayList();
    private final Set<a_3913_L> u_2550_I = Sets.newHashSet();

    protected a_2900_S(@Nullable MenuType<?> type, int id) {
        this.R_4764_Y = type;
        this.u_1723_Y = id;
    }

    protected static boolean n_1700_B(ContainerLevelAccess worldPos, a_3913_L playerIn, T_2915_h targetBlock) {
        return worldPos.n_1700_B((b_4507_u p_216960_2_, c_1514_x p_216960_3_) -> !p_216960_2_.getBlockState((c_1514_x)p_216960_3_).n_1700_B(targetBlock) ? false : playerIn.v_4262_N((double)p_216960_3_.getX() + 0.5, (double)p_216960_3_.getY() + 0.5, (double)p_216960_3_.getZ() + 0.5) <= 64.0, true);
    }

    public MenuType<?> s_956_w() {
        if (this.R_4764_Y == null) {
            throw new UnsupportedOperationException("Unable to construct this menu by type");
        }
        return this.R_4764_Y;
    }

    protected static void n_1700_B(Container inventoryIn, int minSize) {
        int i = inventoryIn.Y_259_p();
        if (i < minSize) {
            throw new IllegalArgumentException("Container size " + i + " is smaller than expected " + minSize);
        }
    }

    protected static void n_1700_B(ContainerData intArrayIn, int minSize) {
        int i = intArrayIn.n_1700_B();
        if (i < minSize) {
            throw new IllegalArgumentException("Container data count " + i + " is smaller than expected " + minSize);
        }
    }

    protected Slot J_1907_R(Slot slotIn) {
        slotIn.G_564_y = this.P_1922_E.size();
        this.P_1922_E.add(slotIn);
        this.n_1700_B.add(Z_1993_T.J_1907_R);
        return slotIn;
    }

    protected DataSlot n_1700_B(DataSlot intIn) {
        this.J_1907_R.add(intIn);
        return intIn;
    }

    protected void n_1700_B(ContainerData arrayIn) {
        for (int i = 0; i < arrayIn.n_1700_B(); ++i) {
            this.n_1700_B(DataSlot.n_1700_B(arrayIn, i));
        }
    }

    public void n_1700_B(ContainerListener listener) {
        if (!this.s_956_w.contains(listener)) {
            this.s_956_w.add(listener);
            listener.n_1700_B(this, this.u_2550_I());
            this.M_588_G();
        }
    }

    public void J_1907_R(ContainerListener listener) {
        this.s_956_w.remove(listener);
    }

    public NonNullList<Z_1993_T> u_2550_I() {
        NonNullList<Z_1993_T> nonnulllist = NonNullList.n_1700_B();
        for (int i = 0; i < this.P_1922_E.size(); ++i) {
            nonnulllist.add(this.P_1922_E.get(i).n_1700_B());
        }
        return nonnulllist;
    }

    public void M_588_G() {
        for (int i = 0; i < this.P_1922_E.size(); ++i) {
            Z_1993_T itemstack = this.P_1922_E.get(i).n_1700_B();
            Z_1993_T itemstack1 = this.n_1700_B.get(i);
            if (Z_1993_T.J_1907_R(itemstack1, itemstack)) continue;
            Z_1993_T itemstack2 = itemstack.t_148_a();
            this.n_1700_B.set(i, itemstack2);
            for (ContainerListener icontainerlistener : this.s_956_w) {
                icontainerlistener.n_1700_B(this, i, itemstack2);
            }
        }
        for (int j = 0; j < this.J_1907_R.size(); ++j) {
            DataSlot intreferenceholder = this.J_1907_R.get(j);
            if (!intreferenceholder.R_4764_Y()) continue;
            for (ContainerListener icontainerlistener1 : this.s_956_w) {
                icontainerlistener1.n_1700_B(this, j, intreferenceholder.J_1907_R());
            }
        }
    }

    public boolean J_1907_R(a_3913_L playerIn, int id) {
        return false;
    }

    public Slot n_1700_B(int slotId) {
        return this.P_1922_E.get(slotId);
    }

    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Slot slot = this.P_1922_E.get(index);
        return slot != null ? slot.n_1700_B() : Z_1993_T.J_1907_R;
    }

    public Z_1993_T n_1700_B(int slotId, int dragType, a_408_T clickTypeIn, a_3913_L player) {
        try {
            return this.J_1907_R(slotId, dragType, clickTypeIn, player);
        }
        catch (Exception exception) {
            n_3236_c crashreport = n_3236_c.n_1700_B(exception, "Container click");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Click info");
            crashreportcategory.n_1700_B("Menu Type", () -> this.R_4764_Y != null ? V_3137_a.T_3594_S.J_1907_R(this.R_4764_Y).toString() : "<no type>");
            crashreportcategory.n_1700_B("Menu Class", () -> this.getClass().getCanonicalName());
            crashreportcategory.n_1700_B("Slot Count", this.P_1922_E.size());
            crashreportcategory.n_1700_B("Slot", slotId);
            crashreportcategory.n_1700_B("Button", dragType);
            crashreportcategory.n_1700_B("Type", (Object)clickTypeIn);
            throw new ReportedException(crashreport);
        }
    }

    private Z_1993_T J_1907_R(int p_241440_1_, int p_241440_2_, a_408_T p_241440_3_, a_3913_L p_241440_4_) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        W_3491_f playerinventory = p_241440_4_.l_1268_F;
        if (p_241440_3_ == a_408_T.u_1723_Y) {
            int i1 = this.w_1484_f;
            this.w_1484_f = a_2900_S.R_4764_Y(p_241440_2_);
            if ((i1 != 1 || this.w_1484_f != 2) && i1 != this.w_1484_f) {
                this.P_4830_p();
            } else if (playerinventory.s_956_w().n_1700_B()) {
                this.P_4830_p();
            } else if (this.w_1484_f == 0) {
                this.v_4262_N = a_2900_S.J_1907_R(p_241440_2_);
                if (a_2900_S.n_1700_B(this.v_4262_N, p_241440_4_)) {
                    this.w_1484_f = 1;
                    this.t_148_a.clear();
                } else {
                    this.P_4830_p();
                }
            } else if (this.w_1484_f == 1) {
                Slot slot7 = this.P_1922_E.get(p_241440_1_);
                Z_1993_T itemstack12 = playerinventory.s_956_w();
                if (slot7 != null && a_2900_S.n_1700_B(slot7, itemstack12, true) && slot7.n_1700_B(itemstack12) && (this.v_4262_N == 2 || itemstack12.t_4043_B() > this.t_148_a.size()) && this.n_1700_B(slot7)) {
                    this.t_148_a.add(slot7);
                }
            } else if (this.w_1484_f == 2) {
                if (!this.t_148_a.isEmpty()) {
                    Z_1993_T itemstack10 = playerinventory.s_956_w().t_148_a();
                    int k1 = playerinventory.s_956_w().t_4043_B();
                    for (Slot slot8 : this.t_148_a) {
                        Z_1993_T itemstack13 = playerinventory.s_956_w();
                        if (slot8 == null || !a_2900_S.n_1700_B(slot8, itemstack13, true) || !slot8.n_1700_B(itemstack13) || this.v_4262_N != 2 && itemstack13.t_4043_B() < this.t_148_a.size() || !this.n_1700_B(slot8)) continue;
                        Z_1993_T itemstack14 = itemstack10.t_148_a();
                        int j3 = slot8.J_1907_R() ? slot8.n_1700_B().t_4043_B() : 0;
                        a_2900_S.n_1700_B(this.t_148_a, this.v_4262_N, itemstack14, j3);
                        int k3 = Math.min(itemstack14.R_4764_Y(), slot8.R_4764_Y(itemstack14));
                        if (itemstack14.t_4043_B() > k3) {
                            itemstack14.P_1922_E(k3);
                        }
                        k1 -= itemstack14.t_4043_B() - j3;
                        slot8.J_1907_R(itemstack14);
                    }
                    itemstack10.P_1922_E(k1);
                    playerinventory.v_4262_N(itemstack10);
                }
                this.P_4830_p();
            } else {
                this.P_4830_p();
            }
        } else if (this.w_1484_f != 0) {
            this.P_4830_p();
        } else if (!(p_241440_3_ != a_408_T.n_1700_B && p_241440_3_ != a_408_T.J_1907_R || p_241440_2_ != 0 && p_241440_2_ != 1)) {
            if (p_241440_1_ == -999) {
                if (!playerinventory.s_956_w().n_1700_B()) {
                    if (p_241440_2_ == 0) {
                        p_241440_4_.n_1700_B(playerinventory.s_956_w(), true);
                        playerinventory.v_4262_N(Z_1993_T.J_1907_R);
                    }
                    if (p_241440_2_ == 1) {
                        p_241440_4_.n_1700_B(playerinventory.s_956_w().n_1700_B(1), true);
                    }
                }
            } else if (p_241440_3_ == a_408_T.J_1907_R) {
                if (p_241440_1_ < 0) {
                    return Z_1993_T.J_1907_R;
                }
                Slot slot5 = this.P_1922_E.get(p_241440_1_);
                if (slot5 == null || !slot5.n_1700_B(p_241440_4_)) {
                    return Z_1993_T.J_1907_R;
                }
                Z_1993_T itemstack8 = this.n_1700_B(p_241440_4_, p_241440_1_);
                while (!itemstack8.n_1700_B() && Z_1993_T.R_4764_Y(slot5.n_1700_B(), itemstack8)) {
                    itemstack = itemstack8.t_148_a();
                    itemstack8 = this.n_1700_B(p_241440_4_, p_241440_1_);
                }
            } else {
                if (p_241440_1_ < 0) {
                    return Z_1993_T.J_1907_R;
                }
                Slot slot6 = this.P_1922_E.get(p_241440_1_);
                if (slot6 != null) {
                    Z_1993_T itemstack9 = slot6.n_1700_B();
                    Z_1993_T itemstack11 = playerinventory.s_956_w();
                    if (!itemstack9.n_1700_B()) {
                        itemstack = itemstack9.t_148_a();
                    }
                    if (itemstack9.n_1700_B()) {
                        if (!itemstack11.n_1700_B() && slot6.n_1700_B(itemstack11)) {
                            int j2;
                            int n = j2 = p_241440_2_ == 0 ? itemstack11.t_4043_B() : 1;
                            if (j2 > slot6.R_4764_Y(itemstack11)) {
                                j2 = slot6.R_4764_Y(itemstack11);
                            }
                            slot6.J_1907_R(itemstack11.n_1700_B(j2));
                        }
                    } else if (slot6.n_1700_B(p_241440_4_)) {
                        int i3;
                        if (itemstack11.n_1700_B()) {
                            if (itemstack9.n_1700_B()) {
                                slot6.J_1907_R(Z_1993_T.J_1907_R);
                                playerinventory.v_4262_N(Z_1993_T.J_1907_R);
                            } else {
                                int k2 = p_241440_2_ == 0 ? itemstack9.t_4043_B() : (itemstack9.t_4043_B() + 1) / 2;
                                playerinventory.v_4262_N(slot6.n_1700_B(k2));
                                if (itemstack9.n_1700_B()) {
                                    slot6.J_1907_R(Z_1993_T.J_1907_R);
                                }
                                slot6.n_1700_B(p_241440_4_, playerinventory.s_956_w());
                            }
                        } else if (slot6.n_1700_B(itemstack11)) {
                            if (a_2900_S.n_1700_B(itemstack9, itemstack11)) {
                                int l2;
                                int n = l2 = p_241440_2_ == 0 ? itemstack11.t_4043_B() : 1;
                                if (l2 > slot6.R_4764_Y(itemstack11) - itemstack9.t_4043_B()) {
                                    l2 = slot6.R_4764_Y(itemstack11) - itemstack9.t_4043_B();
                                }
                                if (l2 > itemstack11.R_4764_Y() - itemstack9.t_4043_B()) {
                                    l2 = itemstack11.R_4764_Y() - itemstack9.t_4043_B();
                                }
                                itemstack11.v_4262_N(l2);
                                itemstack9.u_1723_Y(l2);
                            } else if (itemstack11.t_4043_B() <= slot6.R_4764_Y(itemstack11)) {
                                slot6.J_1907_R(itemstack11);
                                playerinventory.v_4262_N(itemstack9);
                            }
                        } else if (itemstack11.R_4764_Y() > 1 && a_2900_S.n_1700_B(itemstack9, itemstack11) && !itemstack9.n_1700_B() && (i3 = itemstack9.t_4043_B()) + itemstack11.t_4043_B() <= itemstack11.R_4764_Y()) {
                            itemstack11.u_1723_Y(i3);
                            itemstack9 = slot6.n_1700_B(i3);
                            if (itemstack9.n_1700_B()) {
                                slot6.J_1907_R(Z_1993_T.J_1907_R);
                            }
                            slot6.n_1700_B(p_241440_4_, playerinventory.s_956_w());
                        }
                    }
                    slot6.R_4764_Y();
                }
            }
        } else if (p_241440_3_ == a_408_T.R_4764_Y) {
            Slot slot = this.P_1922_E.get(p_241440_1_);
            Z_1993_T itemstack1 = playerinventory.s_956_w(p_241440_2_);
            Z_1993_T itemstack2 = slot.n_1700_B();
            if (!itemstack1.n_1700_B() || !itemstack2.n_1700_B()) {
                if (itemstack1.n_1700_B()) {
                    if (slot.n_1700_B(p_241440_4_)) {
                        playerinventory.J_1907_R(p_241440_2_, itemstack2);
                        slot.J_1907_R(itemstack2.t_4043_B());
                        slot.J_1907_R(Z_1993_T.J_1907_R);
                        slot.n_1700_B(p_241440_4_, itemstack2);
                    }
                } else if (itemstack2.n_1700_B()) {
                    if (slot.n_1700_B(itemstack1)) {
                        int i = slot.R_4764_Y(itemstack1);
                        if (itemstack1.t_4043_B() > i) {
                            slot.J_1907_R(itemstack1.n_1700_B(i));
                        } else {
                            slot.J_1907_R(itemstack1);
                            playerinventory.J_1907_R(p_241440_2_, Z_1993_T.J_1907_R);
                        }
                    }
                } else if (slot.n_1700_B(p_241440_4_) && slot.n_1700_B(itemstack1)) {
                    int l1 = slot.R_4764_Y(itemstack1);
                    if (itemstack1.t_4043_B() > l1) {
                        slot.J_1907_R(itemstack1.n_1700_B(l1));
                        slot.n_1700_B(p_241440_4_, itemstack2);
                        if (!playerinventory.P_1922_E(itemstack2)) {
                            p_241440_4_.n_1700_B(itemstack2, true);
                        }
                    } else {
                        slot.J_1907_R(itemstack1);
                        playerinventory.J_1907_R(p_241440_2_, itemstack2);
                        slot.n_1700_B(p_241440_4_, itemstack2);
                    }
                }
            }
        } else if (p_241440_3_ == a_408_T.G_564_y && p_241440_4_.C_415_h.G_564_y && playerinventory.s_956_w().n_1700_B() && p_241440_1_ >= 0) {
            Slot slot4 = this.P_1922_E.get(p_241440_1_);
            if (slot4 != null && slot4.J_1907_R()) {
                Z_1993_T itemstack7 = slot4.n_1700_B().t_148_a();
                itemstack7.P_1922_E(itemstack7.R_4764_Y());
                playerinventory.v_4262_N(itemstack7);
            }
        } else if (p_241440_3_ == a_408_T.P_1922_E && playerinventory.s_956_w().n_1700_B() && p_241440_1_ >= 0) {
            Slot slot3 = this.P_1922_E.get(p_241440_1_);
            if (slot3 != null && slot3.J_1907_R() && slot3.n_1700_B(p_241440_4_)) {
                Z_1993_T itemstack6 = slot3.n_1700_B(p_241440_2_ == 0 ? 1 : slot3.n_1700_B().t_4043_B());
                slot3.n_1700_B(p_241440_4_, itemstack6);
                p_241440_4_.n_1700_B(itemstack6, true);
            }
        } else if (p_241440_3_ == a_408_T.v_4262_N && p_241440_1_ >= 0) {
            Slot slot2 = this.P_1922_E.get(p_241440_1_);
            Z_1993_T itemstack5 = playerinventory.s_956_w();
            if (!(itemstack5.n_1700_B() || slot2 != null && slot2.J_1907_R() && slot2.n_1700_B(p_241440_4_))) {
                int j1 = p_241440_2_ == 0 ? 0 : this.P_1922_E.size() - 1;
                int i2 = p_241440_2_ == 0 ? 1 : -1;
                for (int j = 0; j < 2; ++j) {
                    for (int k = j1; k >= 0 && k < this.P_1922_E.size() && itemstack5.t_4043_B() < itemstack5.R_4764_Y(); k += i2) {
                        Slot slot1 = this.P_1922_E.get(k);
                        if (!slot1.J_1907_R() || !a_2900_S.n_1700_B(slot1, itemstack5, true) || !slot1.n_1700_B(p_241440_4_) || !this.n_1700_B(itemstack5, slot1)) continue;
                        Z_1993_T itemstack3 = slot1.n_1700_B();
                        if (j == 0 && itemstack3.t_4043_B() == itemstack3.R_4764_Y()) continue;
                        int l = Math.min(itemstack5.R_4764_Y() - itemstack5.t_4043_B(), itemstack3.t_4043_B());
                        Z_1993_T itemstack4 = slot1.n_1700_B(l);
                        itemstack5.u_1723_Y(l);
                        if (itemstack4.n_1700_B()) {
                            slot1.J_1907_R(Z_1993_T.J_1907_R);
                        }
                        slot1.n_1700_B(p_241440_4_, itemstack4);
                    }
                }
            }
            this.M_588_G();
        }
        return itemstack;
    }

    public static boolean n_1700_B(Z_1993_T stack1, Z_1993_T stack2) {
        return stack1.J_1907_R() == stack2.J_1907_R() && Z_1993_T.n_1700_B(stack1, stack2);
    }

    public boolean n_1700_B(Z_1993_T stack, Slot slotIn) {
        return true;
    }

    public void J_1907_R(a_3913_L playerIn) {
        W_3491_f playerinventory = playerIn.l_1268_F;
        if (!playerinventory.s_956_w().n_1700_B()) {
            playerIn.n_1700_B(playerinventory.s_956_w(), false);
            playerinventory.v_4262_N(Z_1993_T.J_1907_R);
        }
    }

    protected void n_1700_B(a_3913_L playerIn, b_4507_u worldIn, Container inventoryIn) {
        if (!playerIn.RealmsLongRunningMcoTaskScreen() || playerIn instanceof B_4088_l && ((B_4088_l)playerIn).C_2741_M()) {
            for (int j = 0; j < inventoryIn.Y_259_p(); ++j) {
                playerIn.n_1700_B(inventoryIn.u_2550_I(j), false);
            }
        } else {
            for (int i = 0; i < inventoryIn.Y_259_p(); ++i) {
                playerIn.l_1268_F.n_1700_B(worldIn, inventoryIn.u_2550_I(i));
            }
        }
    }

    public void n_1700_B(Container inventoryIn) {
        this.M_588_G();
    }

    public void n_1700_B(int slotID, Z_1993_T stack) {
        this.n_1700_B(slotID).J_1907_R(stack);
    }

    public void n_1700_B(List<Z_1993_T> p_190896_1_) {
        for (int i = 0; i < p_190896_1_.size(); ++i) {
            this.n_1700_B(i).J_1907_R(p_190896_1_.get(i));
        }
    }

    public void n_1700_B(int id, int data) {
        this.J_1907_R.get(id).n_1700_B(data);
    }

    public short n_1700_B(W_3491_f invPlayer) {
        this.G_564_y = (short)(this.G_564_y + 1);
        return this.G_564_y;
    }

    public boolean R_4764_Y(a_3913_L player) {
        return !this.u_2550_I.contains(player);
    }

    public void J_1907_R(a_3913_L player, boolean canCraft) {
        if (canCraft) {
            this.u_2550_I.remove(player);
        } else {
            this.u_2550_I.add(player);
        }
    }

    public abstract boolean n_1700_B(a_3913_L var1);

    protected boolean n_1700_B(Z_1993_T stack, int startIndex, int endIndex, boolean reverseDirection) {
        boolean flag = false;
        int i = startIndex;
        if (reverseDirection) {
            i = endIndex - 1;
        }
        if (stack.G_564_y()) {
            while (!stack.n_1700_B() && !(!reverseDirection ? i >= endIndex : i < startIndex)) {
                Slot slot = this.P_1922_E.get(i);
                Z_1993_T itemstack = slot.n_1700_B();
                if (!itemstack.n_1700_B() && a_2900_S.n_1700_B(stack, itemstack)) {
                    int j = itemstack.t_4043_B() + stack.t_4043_B();
                    if (j <= stack.R_4764_Y()) {
                        stack.P_1922_E(0);
                        itemstack.P_1922_E(j);
                        slot.R_4764_Y();
                        flag = true;
                    } else if (itemstack.t_4043_B() < stack.R_4764_Y()) {
                        stack.v_4262_N(stack.R_4764_Y() - itemstack.t_4043_B());
                        itemstack.P_1922_E(stack.R_4764_Y());
                        slot.R_4764_Y();
                        flag = true;
                    }
                }
                if (reverseDirection) {
                    --i;
                    continue;
                }
                ++i;
            }
        }
        if (!stack.n_1700_B()) {
            i = reverseDirection ? endIndex - 1 : startIndex;
            while (!(!reverseDirection ? i >= endIndex : i < startIndex)) {
                Slot slot1 = this.P_1922_E.get(i);
                Z_1993_T itemstack1 = slot1.n_1700_B();
                if (itemstack1.n_1700_B() && slot1.n_1700_B(stack)) {
                    if (stack.t_4043_B() > slot1.G_564_y()) {
                        slot1.J_1907_R(stack.n_1700_B(slot1.G_564_y()));
                    } else {
                        slot1.J_1907_R(stack.n_1700_B(stack.t_4043_B()));
                    }
                    slot1.R_4764_Y();
                    flag = true;
                    break;
                }
                if (reverseDirection) {
                    --i;
                    continue;
                }
                ++i;
            }
        }
        return flag;
    }

    public static int J_1907_R(int eventButton) {
        return eventButton >> 2 & 3;
    }

    public static int R_4764_Y(int clickedButton) {
        return clickedButton & 3;
    }

    public static int R_4764_Y(int p_94534_0_, int p_94534_1_) {
        return p_94534_0_ & 3 | (p_94534_1_ & 3) << 2;
    }

    public static boolean n_1700_B(int dragModeIn, a_3913_L player) {
        if (dragModeIn == 0) {
            return true;
        }
        if (dragModeIn == 1) {
            return true;
        }
        return dragModeIn == 2 && player.C_415_h.G_564_y;
    }

    protected void P_4830_p() {
        this.w_1484_f = 0;
        this.t_148_a.clear();
    }

    public static boolean n_1700_B(@Nullable Slot slotIn, Z_1993_T stack, boolean stackSizeMatters) {
        boolean flag;
        boolean bl = flag = slotIn == null || !slotIn.J_1907_R();
        if (!flag && stack.n_1700_B(slotIn.n_1700_B()) && Z_1993_T.n_1700_B(slotIn.n_1700_B(), stack)) {
            return slotIn.n_1700_B().t_4043_B() + (stackSizeMatters ? 0 : stack.t_4043_B()) <= stack.R_4764_Y();
        }
        return flag;
    }

    public static void n_1700_B(Set<Slot> dragSlotsIn, int dragModeIn, Z_1993_T stack, int slotStackSize) {
        switch (dragModeIn) {
            case 0: {
                stack.P_1922_E(u_530_F.G_564_y((float)stack.t_4043_B() / (float)dragSlotsIn.size()));
                break;
            }
            case 1: {
                stack.P_1922_E(1);
                break;
            }
            case 2: {
                stack.P_1922_E(stack.J_1907_R().u_2550_I());
            }
        }
        stack.u_1723_Y(slotStackSize);
    }

    public boolean n_1700_B(Slot slotIn) {
        return true;
    }

    public static int n_1700_B(@Nullable i_2154_H te) {
        return te instanceof Container ? a_2900_S.J_1907_R((Container)((Object)te)) : 0;
    }

    public static int J_1907_R(@Nullable Container inv) {
        if (inv == null) {
            return 0;
        }
        int i = 0;
        float f = 0.0f;
        for (int j = 0; j < inv.Y_259_p(); ++j) {
            Z_1993_T itemstack = inv.s_956_w(j);
            if (itemstack.n_1700_B()) continue;
            f += (float)itemstack.t_4043_B() / (float)Math.min(inv.J_(), itemstack.R_4764_Y());
            ++i;
        }
        return u_530_F.G_564_y((f /= (float)inv.Y_259_p()) * 14.0f) + (i > 0 ? 1 : 0);
    }
}


