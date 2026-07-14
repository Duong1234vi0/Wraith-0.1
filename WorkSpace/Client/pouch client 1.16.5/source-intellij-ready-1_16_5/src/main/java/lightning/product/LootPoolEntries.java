/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_2242_n;
import lightning.product.Serializer;
import lightning.product.EmptyLootItem;
import lightning.product.R_2836_Y;
import lightning.product.V_3137_a;
import lightning.product.W_60_G;
import lightning.product.c_2687_J;
import lightning.product.d_1292_N;
import lightning.product.d_614_w;
import lightning.product.g_2336_b;
import lightning.product.SequentialEntry;
import lightning.product.EntryGroup;
import lightning.product.GsonAdapterFactory;
import lightning.product.u_1373_N;
import lightning.product.v_2678_c;

public class LootPoolEntries {
    public static final d_614_w n_1700_B = LootPoolEntries.n_1700_B("empty", new EmptyLootItem.n_1700_B());
    public static final d_614_w J_1907_R = LootPoolEntries.n_1700_B("item", new R_2836_Y.n_1700_B());
    public static final d_614_w R_4764_Y = LootPoolEntries.n_1700_B("loot_table", new c_2687_J.n_1700_B());
    public static final d_614_w G_564_y = LootPoolEntries.n_1700_B("dynamic", new v_2678_c.n_1700_B());
    public static final d_614_w P_1922_E = LootPoolEntries.n_1700_B("tag", new W_60_G.n_1700_B());
    public static final d_614_w u_1723_Y = LootPoolEntries.n_1700_B("alternatives", D_2242_n.n_1700_B(d_1292_N::new));
    public static final d_614_w v_4262_N = LootPoolEntries.n_1700_B("sequence", D_2242_n.n_1700_B(SequentialEntry::new));
    public static final d_614_w w_1484_f = LootPoolEntries.n_1700_B("group", D_2242_n.n_1700_B(EntryGroup::new));

    private static d_614_w n_1700_B(String name, Serializer<? extends u_1373_N> serializer) {
        return V_3137_a.n_1700_B(V_3137_a.f_4016_n, new g_2336_b(name), new d_614_w(serializer));
    }

    public static Object n_1700_B() {
        return GsonAdapterFactory.n_1700_B(V_3137_a.f_4016_n, "entry", "type", u_1373_N::n_1700_B).n_1700_B();
    }
}


