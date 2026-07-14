/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.HopperScreen;
import lightning.product.ContainerScreen;
import lightning.product.GrindstoneScreen;
import lightning.product.CraftingScreen;
import lightning.product.K_1816_v;
import lightning.product.FurnaceScreen;
import lightning.product.MenuType;
import lightning.product.N_4498_h;
import lightning.product.BrewingStandScreen;
import lightning.product.T_3046_C;
import lightning.product.SmokerScreen;
import lightning.product.SmithingScreen;
import lightning.product.V_3137_a;
import lightning.product.W_3491_f;
import lightning.product.DispenserScreen;
import lightning.product.a_2900_S;
import lightning.product.Bots;
import lightning.product.MinecraftClient;
import lightning.product.ShulkerBoxScreen;
import lightning.product.g_904_S;
import lightning.product.k_2603_m;
import lightning.product.StonecutterScreen;
import lightning.product.ClientBootstrap;
import lightning.product.AnvilScreen;
import lightning.product.t_1279_j;
import lightning.product.v_1406_g;
import lightning.product.BlastFurnaceScreen;
import lightning.product.x_282_a;
import lightning.product.CartographyTableScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D_4704_b {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Map<MenuType<?>, n_1700_B<?, ?>> J_1907_R = Maps.newHashMap();

    public static <T extends a_2900_S> void n_1700_B(@Nullable MenuType<T> type, MinecraftClient mc, int windowId, x_282_a title) {
        if (type == null) {
            n_1700_B.warn("Trying to open invalid screen with name: {}", (Object)title.getString());
        } else {
            n_1700_B<T, ?> iscreenfactory = D_4704_b.n_1700_B(type);
            if (iscreenfactory == null) {
                n_1700_B.warn("Failed to create screen for menu type: {}", (Object)V_3137_a.T_3594_S.J_1907_R(type));
            } else {
                iscreenfactory.n_1700_B(title, type, mc, windowId);
            }
        }
    }

    public static <T extends a_2900_S> void n_1700_B(@Nullable MenuType<T> type, MinecraftClient mc, int windowId, x_282_a title, lightning.product.n_1700_B bot) {
        if (type == null) {
            n_1700_B.warn("Trying to open invalid screen with name: {}", (Object)title.getString());
        } else {
            n_1700_B<T, ?> iscreenfactory = D_4704_b.n_1700_B(type);
            if (iscreenfactory == null) {
                n_1700_B.warn("Failed to create screen for menu type: {}", (Object)V_3137_a.T_3594_S.J_1907_R(type));
            } else {
                iscreenfactory.n_1700_B(title, type, mc, windowId, bot);
            }
        }
    }

    @Nullable
    private static <T extends a_2900_S> n_1700_B<T, ?> n_1700_B(MenuType<T> type) {
        return J_1907_R.get(type);
    }

    private static <M extends a_2900_S, U extends k_2603_m> void n_1700_B(MenuType<? extends M> type, n_1700_B<M, U> factory) {
        n_1700_B<M, U> iscreenfactory = J_1907_R.put(type, factory);
        if (iscreenfactory != null) {
            throw new IllegalStateException("Duplicate registration for " + String.valueOf(V_3137_a.T_3594_S.J_1907_R(type)));
        }
    }

    public static boolean n_1700_B() {
        boolean flag = false;
        for (MenuType m_1029_C : V_3137_a.T_3594_S) {
            if (J_1907_R.containsKey(m_1029_C)) continue;
            n_1700_B.debug("Menu {} has no matching screen", (Object)V_3137_a.T_3594_S.J_1907_R(m_1029_C));
            flag = true;
        }
        return flag;
    }

    static {
        D_4704_b.n_1700_B(MenuType.n_1700_B, ContainerScreen::new);
        D_4704_b.n_1700_B(MenuType.J_1907_R, ContainerScreen::new);
        D_4704_b.n_1700_B(MenuType.R_4764_Y, ContainerScreen::new);
        D_4704_b.n_1700_B(MenuType.G_564_y, ContainerScreen::new);
        D_4704_b.n_1700_B(MenuType.P_1922_E, ContainerScreen::new);
        D_4704_b.n_1700_B(MenuType.u_1723_Y, ContainerScreen::new);
        D_4704_b.n_1700_B(MenuType.v_4262_N, DispenserScreen::new);
        D_4704_b.n_1700_B(MenuType.w_1484_f, AnvilScreen::new);
        D_4704_b.n_1700_B(MenuType.t_148_a, v_1406_g::new);
        D_4704_b.n_1700_B(MenuType.s_956_w, BlastFurnaceScreen::new);
        D_4704_b.n_1700_B(MenuType.u_2550_I, BrewingStandScreen::new);
        D_4704_b.n_1700_B(MenuType.M_588_G, CraftingScreen::new);
        D_4704_b.n_1700_B(MenuType.P_4830_p, t_1279_j::new);
        D_4704_b.n_1700_B(MenuType.h_1847_R, FurnaceScreen::new);
        D_4704_b.n_1700_B(MenuType.Q_4569_t, GrindstoneScreen::new);
        D_4704_b.n_1700_B(MenuType.M_182_A, HopperScreen::new);
        D_4704_b.n_1700_B(MenuType.t_1786_h, K_1816_v::new);
        D_4704_b.n_1700_B(MenuType.multiplayerClientSuggestionProvider, T_3046_C::new);
        D_4704_b.n_1700_B(MenuType.w_1457_N, g_904_S::new);
        D_4704_b.n_1700_B(MenuType.Y_601_j, ShulkerBoxScreen::new);
        D_4704_b.n_1700_B(MenuType.Y_259_p, SmithingScreen::new);
        D_4704_b.n_1700_B(MenuType.Q_2552_b, SmokerScreen::new);
        D_4704_b.n_1700_B(MenuType.C_2741_M, CartographyTableScreen::new);
        D_4704_b.n_1700_B(MenuType.k_2293_S, StonecutterScreen::new);
    }

    static interface n_1700_B<T extends a_2900_S, U extends k_2603_m> {
        default public void n_1700_B(x_282_a title, MenuType<T> type, MinecraftClient mc, int windowId) {
            U u = this.create(type.n_1700_B(windowId, mc.Y_259_p.l_1268_F), mc.Y_259_p.l_1268_F, title);
            mc.Y_259_p.H_1873_g = ((N_4498_h)u).n_();
            mc.n_1700_B((k_2603_m)u);
        }

        default public void n_1700_B(x_282_a title, MenuType<T> type, MinecraftClient mc, int windowId, lightning.product.n_1700_B bot) {
            U u = this.create(type.n_1700_B(windowId, bot.P_1922_E.Q_2552_b.l_1268_F), bot.P_1922_E.Q_2552_b.l_1268_F, title);
            bot.P_1922_E.Q_2552_b.H_1873_g = ((N_4498_h)u).n_();
            Bots botsModule = (Bots)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Bots.class);
            if (botsModule == null || !botsModule.w_1484_f() || botsModule.h_1847_R.t_148_a().booleanValue()) {
                mc.n_1700_B((k_2603_m)u);
            }
        }

        public U create(T var1, W_3491_f var2, x_282_a var3);
    }
}



