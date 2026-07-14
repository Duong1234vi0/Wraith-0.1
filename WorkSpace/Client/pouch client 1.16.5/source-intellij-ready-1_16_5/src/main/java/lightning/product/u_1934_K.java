/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Timer;
import java.util.TimerTask;
import lightning.product.AxeItem;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.L_1875_m;
import lightning.product.R_2515_i;
import lightning.product.Z_1993_T;
import lightning.product.Z_3504_M;
import lightning.product.a_408_T;
import lightning.product.MinecraftAccess;
import lightning.product.e_1174_E;
import lightning.product.g_422_i;
import lightning.product.k_2348_i;
import lightning.product.k_2610_C;
import lightning.product.p_1183_T;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.SwordItem;
import lightning.product.x_1688_C;

public class u_1934_K
implements MinecraftAccess {
    public static final int n_1700_B = 5;

    public static int n_1700_B(q_1613_l item) {
        if (c_3005_b == null || u_1934_K.c_3005_b.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || stack.J_1907_R() != item) continue;
            return i;
        }
        return -1;
    }

    public static int J_1907_R(q_1613_l input) {
        for (Z_1993_T stack : u_1934_K.c_3005_b.Y_259_p.u_55_V()) {
            if (stack.J_1907_R() != input) continue;
            return -2;
        }
        return 0;
    }

    public static int R_4764_Y(q_1613_l item) {
        for (int i = 0; i < 45; ++i) {
            if (u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != item) continue;
            return i;
        }
        return -1;
    }

    public static boolean G_564_y(q_1613_l item) {
        if (c_3005_b == null || u_1934_K.c_3005_b.Y_259_p == null) {
            return false;
        }
        for (int i = 0; i < 9; ++i) {
            if (u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).n_1700_B() || u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != item) continue;
            return true;
        }
        return false;
    }

    public static void P_1922_E(q_1613_l item) {
        int i;
        if (u_1934_K.R_4764_Y(item) == -1) {
            return;
        }
        if (u_1934_K.G_564_y(item)) {
            for (i = 0; i < 9; ++i) {
                if (u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).n_1700_B() || u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != item) continue;
                final int originalSlot = u_1934_K.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                u_1934_K.c_3005_b.Y_259_p.l_1268_F.G_564_y = i;
                new Timer().schedule(new TimerTask(){

                    @Override
                    public void run() {
                        if (MinecraftAccess.c_3005_b.Y_259_p != null && MinecraftAccess.c_3005_b.Y_259_p.n_1700_B != null) {
                            MinecraftAccess.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
                        }
                    }
                }, 40L);
                new Timer().schedule(new TimerTask(){

                    @Override
                    public void run() {
                        if (MinecraftAccess.c_3005_b.Y_259_p != null) {
                            MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.G_564_y = originalSlot;
                        }
                    }
                }, 60L);
                return;
            }
        }
        if (!u_1934_K.G_564_y(item)) {
            for (i = 0; i < 36; ++i) {
                if (u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).n_1700_B() || u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != item) continue;
                final int currentSlot = u_1934_K.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                final int swapButton = currentSlot % 8 + 1;
                int containerSlot = i < 9 ? i + 36 : i;
                u_1934_K.c_3005_b.w_1457_N.windowClick(0, containerSlot, swapButton, a_408_T.R_4764_Y, u_1934_K.c_3005_b.Y_259_p);
                u_1934_K.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(swapButton - 1));
                new Timer().schedule(new TimerTask(){

                    @Override
                    public void run() {
                        if (MinecraftAccess.c_3005_b.Y_259_p != null && MinecraftAccess.c_3005_b.Y_259_p.n_1700_B != null) {
                            MinecraftAccess.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
                        }
                    }
                }, 40L);
                final int finalContainerSlot = containerSlot;
                new Timer().schedule(new TimerTask(){

                    @Override
                    public void run() {
                        if (MinecraftAccess.c_3005_b.Y_259_p != null && MinecraftAccess.c_3005_b.Y_259_p.n_1700_B != null) {
                            MinecraftAccess.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(currentSlot));
                            MinecraftAccess.c_3005_b.w_1457_N.windowClick(0, finalContainerSlot, swapButton, a_408_T.R_4764_Y, MinecraftAccess.c_3005_b.Y_259_p);
                        }
                    }
                }, 60L);
                return;
            }
        }
    }

    public static void n_1700_B(q_1613_l item, boolean rotation) {
        if (c_3005_b == null || u_1934_K.c_3005_b.Y_259_p == null || u_1934_K.c_3005_b.w_1457_N == null) {
            return;
        }
        int sourceSlot = u_1934_K.n_1700_B(item);
        if (sourceSlot == -1) {
            return;
        }
        final int sourceContainerSlot = sourceSlot < 9 ? 36 + sourceSlot : sourceSlot;
        int offhandSlot = 45;
        try {
            u_1934_K.c_3005_b.w_1457_N.windowClick(0, sourceContainerSlot, 0, a_408_T.n_1700_B, u_1934_K.c_3005_b.Y_259_p);
            u_1934_K.c_3005_b.w_1457_N.windowClick(0, 45, 0, a_408_T.n_1700_B, u_1934_K.c_3005_b.Y_259_p);
            u_1934_K.c_3005_b.w_1457_N.windowClick(0, sourceContainerSlot, 0, a_408_T.n_1700_B, u_1934_K.c_3005_b.Y_259_p);
        }
        catch (Exception ignored) {
            return;
        }
        new Timer().schedule(new TimerTask(){

            @Override
            public void run() {
                if (!MinecraftAccess.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
                    MinecraftAccess.c_3005_b.P_4830_p.p_178_J.n_1700_B(true);
                }
            }
        }, 25L);
        new Timer().schedule(new TimerTask(){

            @Override
            public void run() {
                if (MinecraftAccess.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
                    MinecraftAccess.c_3005_b.P_4830_p.p_178_J.n_1700_B(false);
                }
            }
        }, 80L);
        new Timer().schedule(new TimerTask(){

            @Override
            public void run() {
                try {
                    MinecraftAccess.c_3005_b.w_1457_N.windowClick(0, 45, 0, a_408_T.n_1700_B, MinecraftAccess.c_3005_b.Y_259_p);
                    MinecraftAccess.c_3005_b.w_1457_N.windowClick(0, sourceContainerSlot, 0, a_408_T.n_1700_B, MinecraftAccess.c_3005_b.Y_259_p);
                    MinecraftAccess.c_3005_b.w_1457_N.windowClick(0, 45, 0, a_408_T.n_1700_B, MinecraftAccess.c_3005_b.Y_259_p);
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }, 140L);
    }

    public static int u_1723_Y(q_1613_l item) {
        if (c_3005_b == null || u_1934_K.c_3005_b.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || stack.J_1907_R() != item) continue;
            return i;
        }
        return -1;
    }

    public static void n_1700_B(int from, int to) {
        if (from == to) {
            return;
        }
        from = from < 9 ? from + 36 : from;
        u_1934_K.c_3005_b.w_1457_N.windowClick(u_1934_K.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, from, 0, a_408_T.R_4764_Y, u_1934_K.c_3005_b.Y_259_p);
        u_1934_K.c_3005_b.w_1457_N.windowClick(u_1934_K.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, to, 0, a_408_T.R_4764_Y, u_1934_K.c_3005_b.Y_259_p);
        u_1934_K.c_3005_b.w_1457_N.windowClick(u_1934_K.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, from, 0, a_408_T.R_4764_Y, u_1934_K.c_3005_b.Y_259_p);
    }

    public static void J_1907_R(int from, int to) {
        if (from == to) {
            return;
        }
        from = from < 9 ? from + 36 : from;
        u_1934_K.R_4764_Y(from, 0);
        u_1934_K.R_4764_Y(to, 0);
        u_1934_K.R_4764_Y(from, 0);
    }

    public static int n_1700_B() {
        for (int i = 0; i < 45; ++i) {
            Z_1993_T stack = u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || !(stack.J_1907_R() instanceof AxeItem)) continue;
            return i;
        }
        return -1;
    }

    public static boolean J_1907_R() {
        if (u_1934_K.c_3005_b.Y_259_p == null) {
            return false;
        }
        Z_1993_T mainHand = u_1934_K.c_3005_b.Y_259_p.A_2714_y();
        if (mainHand.n_1700_B()) {
            return false;
        }
        q_1613_l item = mainHand.J_1907_R();
        return item instanceof SwordItem || item instanceof AxeItem;
    }

    public static void R_4764_Y(int slot, int button) {
        if (u_1934_K.c_3005_b.Y_259_p == null || u_1934_K.c_3005_b.w_1457_N == null) {
            return;
        }
        u_1934_K.c_3005_b.w_1457_N.windowClick(0, slot, button, a_408_T.n_1700_B, u_1934_K.c_3005_b.Y_259_p);
    }

    public static int R_4764_Y() {
        if (u_1934_K.c_3005_b.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < u_1934_K.c_3005_b.Y_259_p.l_1268_F.n_1700_B.size(); ++i) {
            Z_1993_T itemStack = u_1934_K.c_3005_b.Y_259_p.l_1268_F.n_1700_B.get(i);
            if (itemStack.n_1700_B() || !(itemStack.J_1907_R() instanceof R_2515_i) || ((R_2515_i)itemStack.J_1907_R()).R_4764_Y() != e_1174_E.P_1922_E) continue;
            return i;
        }
        return -1;
    }

    public static void G_564_y(int fromSlot, int armorSlot) {
        if (u_1934_K.c_3005_b.Y_259_p == null || u_1934_K.c_3005_b.w_1457_N == null) {
            return;
        }
        int invFrom = fromSlot < 9 ? fromSlot + 36 : fromSlot;
        u_1934_K.c_3005_b.w_1457_N.windowClick(0, invFrom, 0, a_408_T.R_4764_Y, u_1934_K.c_3005_b.Y_259_p);
        u_1934_K.c_3005_b.w_1457_N.windowClick(0, armorSlot, 0, a_408_T.R_4764_Y, u_1934_K.c_3005_b.Y_259_p);
        u_1934_K.c_3005_b.w_1457_N.windowClick(0, invFrom, 0, a_408_T.R_4764_Y, u_1934_K.c_3005_b.Y_259_p);
    }

    public static int n_1700_B(int invSlot) {
        if (invSlot < 0) {
            return -1;
        }
        if (invSlot < 9) {
            return invSlot + 36;
        }
        if (invSlot < 36) {
            return invSlot;
        }
        if (invSlot == 36) {
            return 8;
        }
        if (invSlot == 37) {
            return 7;
        }
        if (invSlot == 38) {
            return 6;
        }
        if (invSlot == 39) {
            return 5;
        }
        if (invSlot == 40) {
            return 45;
        }
        return -1;
    }

    public static void J_1907_R(int playerInvSlot) {
        if (u_1934_K.c_3005_b.Y_259_p == null || u_1934_K.c_3005_b.w_1457_N == null) {
            return;
        }
        int from = u_1934_K.n_1700_B(playerInvSlot);
        if (from < 0) {
            return;
        }
        int wid = u_1934_K.c_3005_b.Y_259_p.H_1873_g.u_1723_Y;
        u_1934_K.c_3005_b.w_1457_N.windowClick(wid, from, 0, a_408_T.n_1700_B, u_1934_K.c_3005_b.Y_259_p);
        u_1934_K.c_3005_b.w_1457_N.windowClick(wid, 5, 0, a_408_T.n_1700_B, u_1934_K.c_3005_b.Y_259_p);
        u_1934_K.c_3005_b.w_1457_N.windowClick(wid, from, 0, a_408_T.n_1700_B, u_1934_K.c_3005_b.Y_259_p);
    }

    public static int v_4262_N(q_1613_l item) {
        if (u_1934_K.c_3005_b.Y_259_p == null) {
            return 0;
        }
        int count = 0;
        for (Z_1993_T stack : u_1934_K.c_3005_b.Y_259_p.l_1268_F.n_1700_B) {
            if (stack.n_1700_B() || !stack.J_1907_R().equals(item)) continue;
            count += stack.t_4043_B();
        }
        return count;
    }

    public static int n_1700_B(K_4074_S blockState) {
        if (u_1934_K.c_3005_b.Y_259_p == null) {
            return -1;
        }
        int bestSlot = -1;
        float bestSpeed = 1.0f;
        for (int i = 0; i < 9; ++i) {
            float speed;
            Z_1993_T stack = u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || !((speed = u_1934_K.c_3005_b.Y_259_p.n_1700_B(stack, blockState)) > bestSpeed)) continue;
            bestSpeed = speed;
            bestSlot = i;
        }
        return bestSlot;
    }

    private static int v_4262_N() {
        if (c_3005_b == null || u_1934_K.c_3005_b.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!stack.n_1700_B()) continue;
            return i < 9 ? i + 36 : i;
        }
        return -1;
    }

    public static void w_1484_f(q_1613_l item) {
        boolean isInHotbar;
        if (u_1934_K.c_3005_b.Y_259_p == null || u_1934_K.c_3005_b.w_1457_N == null) {
            return;
        }
        int slot = u_1934_K.n_1700_B(item);
        if (slot == -1) {
            return;
        }
        int currentSlot = u_1934_K.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        boolean bl = isInHotbar = slot < 9;
        if (isInHotbar && slot == currentSlot) {
            u_1934_K.w_1484_f();
        } else if (isInHotbar) {
            u_1934_K.P_1922_E(slot, currentSlot);
        } else {
            int hotbarSlot = currentSlot % 8 + 1;
            u_1934_K.c_3005_b.w_1457_N.windowClick(0, slot, hotbarSlot, a_408_T.R_4764_Y, u_1934_K.c_3005_b.Y_259_p);
            u_1934_K.P_1922_E(hotbarSlot, currentSlot);
            u_1934_K.c_3005_b.w_1457_N.windowClick(0, slot, hotbarSlot, a_408_T.R_4764_Y, u_1934_K.c_3005_b.Y_259_p);
        }
    }

    private static void w_1484_f() {
        if (u_1934_K.c_3005_b.Y_259_p == null) {
            return;
        }
        if (u_1934_K.c_3005_b.Y_259_p.Y_601_j() && !u_1934_K.c_3005_b.Y_259_p.I_1790_n()) {
            u_1934_K.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.J_1907_R));
        } else {
            u_1934_K.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
        }
    }

    private static void P_1922_E(int targetSlot, int currentSlot) {
        if (u_1934_K.c_3005_b.Y_259_p == null) {
            return;
        }
        if (u_1934_K.c_3005_b.Y_259_p.Y_601_j() && !u_1934_K.c_3005_b.Y_259_p.I_1790_n()) {
            u_1934_K.n_1700_B(targetSlot, 45);
            u_1934_K.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.J_1907_R));
            u_1934_K.n_1700_B(targetSlot, 45);
        } else {
            u_1934_K.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(targetSlot));
            u_1934_K.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
            u_1934_K.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(currentSlot));
        }
    }

    public static int G_564_y() {
        if (c_3005_b == null || u_1934_K.c_3005_b.Y_259_p == null) {
            return -1;
        }
        int best = -1;
        int max = 0;
        for (int i = 0; i < 45; ++i) {
            Z_1993_T s = u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (s.n_1700_B() || !s.J_1907_R().Y_259_p() || s.t_4043_B() <= max) continue;
            best = i;
            max = s.t_4043_B();
        }
        return best;
    }

    public static int P_1922_E() {
        if (c_3005_b == null || u_1934_K.c_3005_b.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < 45; ++i) {
            Z_1993_T stack = u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || stack.J_1907_R() != Items.N_81_X || K_4096_w.n_1700_B(stack).isEmpty()) continue;
            return i;
        }
        return -1;
    }

    public static boolean n_1700_B(Z_1993_T stack, boolean includeRegular, boolean includeSplash, boolean includeLingering, g_422_i ... effects) {
        boolean typeOk;
        if (stack == null || stack.n_1700_B()) {
            return false;
        }
        q_1613_l item = stack.J_1907_R();
        boolean bl = typeOk = includeRegular && item == Items.j_2461_G || includeSplash && item == Items.g_2492_v || includeLingering && item == Items.NetherrackBlock;
        if (!typeOk) {
            return false;
        }
        for (k_2610_C instance : L_1875_m.n_1700_B(stack)) {
            for (g_422_i effect : effects) {
                if (instance.n_1700_B() != effect) continue;
                return true;
            }
        }
        return false;
    }

    public static int n_1700_B(boolean preferHotbar, boolean includeRegular, boolean includeSplash, boolean includeLingering, g_422_i ... effects) {
        if (u_1934_K.c_3005_b.Y_259_p == null) {
            return -1;
        }
        if (preferHotbar) {
            for (int i = 0; i < 9; ++i) {
                Z_1993_T s = u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
                if (!u_1934_K.n_1700_B(s, includeRegular, includeSplash, includeLingering, effects)) continue;
                return i;
            }
        } else {
            Z_1993_T s;
            int i;
            for (i = 0; i < 9; ++i) {
                s = u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
                if (!u_1934_K.n_1700_B(s, includeRegular, includeSplash, includeLingering, effects)) continue;
                return i;
            }
            for (i = 9; i < 36; ++i) {
                s = u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
                if (!u_1934_K.n_1700_B(s, includeRegular, includeSplash, includeLingering, effects)) continue;
                return i;
            }
        }
        return -1;
    }

    public static int u_1723_Y() {
        if (u_1934_K.c_3005_b.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = u_1934_K.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!stack.n_1700_B()) continue;
            return i < 9 ? i + 36 : i;
        }
        return -1;
    }

    public static void n_1700_B(Object object, q_1613_l item) {
        int slot = u_1934_K.n_1700_B(item);
        if (slot == -1) {
            return;
        }
        if (slot < 9) {
            k_2348_i.n_1700_B(object, slot);
        } else {
            k_2348_i.J_1907_R(object, slot);
        }
    }
}



