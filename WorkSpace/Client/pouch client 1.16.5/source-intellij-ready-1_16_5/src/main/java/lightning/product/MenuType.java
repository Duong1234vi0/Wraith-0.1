/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_512_t;
import lightning.product.F_4082_i;
import lightning.product.K_3710_b;
import lightning.product.CraftingMenu;
import lightning.product.ChestMenu;
import lightning.product.Q_1649_j;
import lightning.product.R_4599_y;
import lightning.product.V_3137_a;
import lightning.product.W_3491_f;
import lightning.product.W_4989_Q;
import lightning.product.BrewingStandMenu;
import lightning.product.AnvilMenu;
import lightning.product.a_2900_S;
import lightning.product.a_669_v;
import lightning.product.BeaconMenu;
import lightning.product.g_4614_N;
import lightning.product.SmithingMenu;
import lightning.product.i_3895_t;
import lightning.product.w_1471_F;
import lightning.product.x_353_w;
import lightning.product.x_940_l;
import lightning.product.z_1477_l;

public class MenuType<T extends a_2900_S> {
    public static final MenuType<ChestMenu> n_1700_B = MenuType.n_1700_B("generic_9x1", ChestMenu::n_1700_B);
    public static final MenuType<ChestMenu> J_1907_R = MenuType.n_1700_B("generic_9x2", ChestMenu::J_1907_R);
    public static final MenuType<ChestMenu> R_4764_Y = MenuType.n_1700_B("generic_9x3", ChestMenu::R_4764_Y);
    public static final MenuType<ChestMenu> G_564_y = MenuType.n_1700_B("generic_9x4", ChestMenu::G_564_y);
    public static final MenuType<ChestMenu> P_1922_E = MenuType.n_1700_B("generic_9x5", ChestMenu::P_1922_E);
    public static final MenuType<ChestMenu> u_1723_Y = MenuType.n_1700_B("generic_9x6", ChestMenu::u_1723_Y);
    public static final MenuType<x_940_l> v_4262_N = MenuType.n_1700_B("generic_3x3", x_940_l::new);
    public static final MenuType<AnvilMenu> w_1484_f = MenuType.n_1700_B("anvil", AnvilMenu::new);
    public static final MenuType<BeaconMenu> t_148_a = MenuType.n_1700_B("beacon", BeaconMenu::new);
    public static final MenuType<W_4989_Q> s_956_w = MenuType.n_1700_B("blast_furnace", W_4989_Q::new);
    public static final MenuType<BrewingStandMenu> u_2550_I = MenuType.n_1700_B("brewing_stand", BrewingStandMenu::new);
    public static final MenuType<CraftingMenu> M_588_G = MenuType.n_1700_B("crafting", CraftingMenu::new);
    public static final MenuType<Q_1649_j> P_4830_p = MenuType.n_1700_B("enchantment", Q_1649_j::new);
    public static final MenuType<g_4614_N> h_1847_R = MenuType.n_1700_B("furnace", g_4614_N::new);
    public static final MenuType<F_4082_i> Q_4569_t = MenuType.n_1700_B("grindstone", F_4082_i::new);
    public static final MenuType<z_1477_l> M_182_A = MenuType.n_1700_B("hopper", z_1477_l::new);
    public static final MenuType<a_669_v> t_1786_h = MenuType.n_1700_B("lectern", (p_221504_0_, p_221504_1_) -> new a_669_v(p_221504_0_));
    public static final MenuType<w_1471_F> multiplayerClientSuggestionProvider = MenuType.n_1700_B("loom", w_1471_F::new);
    public static final MenuType<K_3710_b> w_1457_N = MenuType.n_1700_B("merchant", K_3710_b::new);
    public static final MenuType<x_353_w> Y_601_j = MenuType.n_1700_B("shulker_box", x_353_w::new);
    public static final MenuType<SmithingMenu> Y_259_p = MenuType.n_1700_B("smithing", SmithingMenu::new);
    public static final MenuType<B_512_t> Q_2552_b = MenuType.n_1700_B("smoker", B_512_t::new);
    public static final MenuType<i_3895_t> C_2741_M = MenuType.n_1700_B("cartography_table", i_3895_t::new);
    public static final MenuType<R_4599_y> k_2293_S = MenuType.n_1700_B("stonecutter", R_4599_y::new);
    private final n_1700_B<T> q_2307_F;

    private static <T extends a_2900_S> MenuType<T> n_1700_B(String key, n_1700_B<T> factory) {
        return V_3137_a.n_1700_B(V_3137_a.T_3594_S, key, new MenuType<T>(factory));
    }

    private MenuType(n_1700_B<T> factory) {
        this.q_2307_F = factory;
    }

    public T n_1700_B(int windowId, W_3491_f player) {
        return this.q_2307_F.create(windowId, player);
    }

    static interface n_1700_B<T extends a_2900_S> {
        public T create(int var1, W_3491_f var2);
    }
}


