/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.HashBiMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.LootContextParams;
import lightning.product.I_2176_d;
import lightning.product.g_2336_b;

public class f_1402_I {
    private static final BiMap<g_2336_b, I_2176_d> P_4830_p = HashBiMap.create();
    public static final I_2176_d n_1700_B = f_1402_I.n_1700_B("empty", p_216249_0_ -> {});
    public static final I_2176_d J_1907_R = f_1402_I.n_1700_B("chest", p_216259_0_ -> p_216259_0_.n_1700_B(LootContextParams.u_1723_Y).J_1907_R(LootContextParams.n_1700_B));
    public static final I_2176_d R_4764_Y = f_1402_I.n_1700_B("command", p_216250_0_ -> p_216250_0_.n_1700_B(LootContextParams.u_1723_Y).J_1907_R(LootContextParams.n_1700_B));
    public static final I_2176_d G_564_y = f_1402_I.n_1700_B("selector", p_216254_0_ -> p_216254_0_.n_1700_B(LootContextParams.u_1723_Y).n_1700_B(LootContextParams.n_1700_B));
    public static final I_2176_d P_1922_E = f_1402_I.n_1700_B("fishing", p_216258_0_ -> p_216258_0_.n_1700_B(LootContextParams.u_1723_Y).n_1700_B(LootContextParams.t_148_a).J_1907_R(LootContextParams.n_1700_B));
    public static final I_2176_d u_1723_Y = f_1402_I.n_1700_B("entity", p_216251_0_ -> p_216251_0_.n_1700_B(LootContextParams.n_1700_B).n_1700_B(LootContextParams.u_1723_Y).n_1700_B(LootContextParams.R_4764_Y).J_1907_R(LootContextParams.G_564_y).J_1907_R(LootContextParams.P_1922_E).J_1907_R(LootContextParams.J_1907_R));
    public static final I_2176_d v_4262_N = f_1402_I.n_1700_B("gift", p_216255_0_ -> p_216255_0_.n_1700_B(LootContextParams.u_1723_Y).n_1700_B(LootContextParams.n_1700_B));
    public static final I_2176_d w_1484_f = f_1402_I.n_1700_B("barter", p_216252_0_ -> p_216252_0_.n_1700_B(LootContextParams.n_1700_B));
    public static final I_2176_d t_148_a = f_1402_I.n_1700_B("advancement_reward", p_227560_0_ -> p_227560_0_.n_1700_B(LootContextParams.n_1700_B).n_1700_B(LootContextParams.u_1723_Y));
    public static final I_2176_d s_956_w = f_1402_I.n_1700_B("advancement_entity", p_227559_0_ -> p_227559_0_.n_1700_B(LootContextParams.n_1700_B).n_1700_B(LootContextParams.u_1723_Y));
    public static final I_2176_d u_2550_I = f_1402_I.n_1700_B("generic", p_237456_0_ -> p_237456_0_.n_1700_B(LootContextParams.n_1700_B).n_1700_B(LootContextParams.J_1907_R).n_1700_B(LootContextParams.R_4764_Y).n_1700_B(LootContextParams.G_564_y).n_1700_B(LootContextParams.P_1922_E).n_1700_B(LootContextParams.u_1723_Y).n_1700_B(LootContextParams.v_4262_N).n_1700_B(LootContextParams.w_1484_f).n_1700_B(LootContextParams.t_148_a).n_1700_B(LootContextParams.s_956_w));
    public static final I_2176_d M_588_G = f_1402_I.n_1700_B("block", p_237455_0_ -> p_237455_0_.n_1700_B(LootContextParams.v_4262_N).n_1700_B(LootContextParams.u_1723_Y).n_1700_B(LootContextParams.t_148_a).J_1907_R(LootContextParams.n_1700_B).J_1907_R(LootContextParams.w_1484_f).J_1907_R(LootContextParams.s_956_w));

    private static I_2176_d n_1700_B(String registryName, Consumer<I_2176_d.n_1700_B> p_216253_1_) {
        I_2176_d.n_1700_B lootparameterset$builder = new I_2176_d.n_1700_B();
        p_216253_1_.accept(lootparameterset$builder);
        I_2176_d lootparameterset = lootparameterset$builder.n_1700_B();
        g_2336_b resourcelocation = new g_2336_b(registryName);
        I_2176_d lootparameterset1 = (I_2176_d)P_4830_p.put((Object)resourcelocation, (Object)lootparameterset);
        if (lootparameterset1 != null) {
            throw new IllegalStateException("Loot table parameter set " + String.valueOf(resourcelocation) + " is already registered");
        }
        return lootparameterset;
    }

    @Nullable
    public static I_2176_d n_1700_B(g_2336_b registryName) {
        return (I_2176_d)P_4830_p.get((Object)registryName);
    }

    @Nullable
    public static g_2336_b n_1700_B(I_2176_d p_216257_0_) {
        return (g_2336_b)P_4830_p.inverse().get((Object)p_216257_0_);
    }
}


