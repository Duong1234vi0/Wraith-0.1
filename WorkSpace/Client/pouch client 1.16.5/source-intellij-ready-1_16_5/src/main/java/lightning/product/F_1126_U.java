/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 *  it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.ints.IntList
 *  it.unimi.dsi.fastutil.ints.IntListIterator
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.A_1434_p;
import lightning.product.References;
import lightning.product.t_252_P;
import lightning.product.BlockStateData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class F_1126_U
extends DataFix {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final BitSet J_1907_R = new BitSet(256);
    private static final BitSet R_4764_Y = new BitSet(256);
    private static final Dynamic<?> G_564_y = BlockStateData.J_1907_R("{Name:'minecraft:pumpkin'}");
    private static final Dynamic<?> P_1922_E = BlockStateData.J_1907_R("{Name:'minecraft:podzol',Properties:{snowy:'true'}}");
    private static final Dynamic<?> u_1723_Y = BlockStateData.J_1907_R("{Name:'minecraft:grass_block',Properties:{snowy:'true'}}");
    private static final Dynamic<?> v_4262_N = BlockStateData.J_1907_R("{Name:'minecraft:mycelium',Properties:{snowy:'true'}}");
    private static final Dynamic<?> w_1484_f = BlockStateData.J_1907_R("{Name:'minecraft:sunflower',Properties:{half:'upper'}}");
    private static final Dynamic<?> t_148_a = BlockStateData.J_1907_R("{Name:'minecraft:lilac',Properties:{half:'upper'}}");
    private static final Dynamic<?> s_956_w = BlockStateData.J_1907_R("{Name:'minecraft:tall_grass',Properties:{half:'upper'}}");
    private static final Dynamic<?> u_2550_I = BlockStateData.J_1907_R("{Name:'minecraft:large_fern',Properties:{half:'upper'}}");
    private static final Dynamic<?> M_588_G = BlockStateData.J_1907_R("{Name:'minecraft:rose_bush',Properties:{half:'upper'}}");
    private static final Dynamic<?> P_4830_p = BlockStateData.J_1907_R("{Name:'minecraft:peony',Properties:{half:'upper'}}");
    private static final Map<String, Dynamic<?>> h_1847_R = (Map)DataFixUtils.make((Object)Maps.newHashMap(), p_209306_0_ -> {
        p_209306_0_.put("minecraft:air0", BlockStateData.J_1907_R("{Name:'minecraft:flower_pot'}"));
        p_209306_0_.put("minecraft:red_flower0", BlockStateData.J_1907_R("{Name:'minecraft:potted_poppy'}"));
        p_209306_0_.put("minecraft:red_flower1", BlockStateData.J_1907_R("{Name:'minecraft:potted_blue_orchid'}"));
        p_209306_0_.put("minecraft:red_flower2", BlockStateData.J_1907_R("{Name:'minecraft:potted_allium'}"));
        p_209306_0_.put("minecraft:red_flower3", BlockStateData.J_1907_R("{Name:'minecraft:potted_azure_bluet'}"));
        p_209306_0_.put("minecraft:red_flower4", BlockStateData.J_1907_R("{Name:'minecraft:potted_red_tulip'}"));
        p_209306_0_.put("minecraft:red_flower5", BlockStateData.J_1907_R("{Name:'minecraft:potted_orange_tulip'}"));
        p_209306_0_.put("minecraft:red_flower6", BlockStateData.J_1907_R("{Name:'minecraft:potted_white_tulip'}"));
        p_209306_0_.put("minecraft:red_flower7", BlockStateData.J_1907_R("{Name:'minecraft:potted_pink_tulip'}"));
        p_209306_0_.put("minecraft:red_flower8", BlockStateData.J_1907_R("{Name:'minecraft:potted_oxeye_daisy'}"));
        p_209306_0_.put("minecraft:yellow_flower0", BlockStateData.J_1907_R("{Name:'minecraft:potted_dandelion'}"));
        p_209306_0_.put("minecraft:sapling0", BlockStateData.J_1907_R("{Name:'minecraft:potted_oak_sapling'}"));
        p_209306_0_.put("minecraft:sapling1", BlockStateData.J_1907_R("{Name:'minecraft:potted_spruce_sapling'}"));
        p_209306_0_.put("minecraft:sapling2", BlockStateData.J_1907_R("{Name:'minecraft:potted_birch_sapling'}"));
        p_209306_0_.put("minecraft:sapling3", BlockStateData.J_1907_R("{Name:'minecraft:potted_jungle_sapling'}"));
        p_209306_0_.put("minecraft:sapling4", BlockStateData.J_1907_R("{Name:'minecraft:potted_acacia_sapling'}"));
        p_209306_0_.put("minecraft:sapling5", BlockStateData.J_1907_R("{Name:'minecraft:potted_dark_oak_sapling'}"));
        p_209306_0_.put("minecraft:red_mushroom0", BlockStateData.J_1907_R("{Name:'minecraft:potted_red_mushroom'}"));
        p_209306_0_.put("minecraft:brown_mushroom0", BlockStateData.J_1907_R("{Name:'minecraft:potted_brown_mushroom'}"));
        p_209306_0_.put("minecraft:deadbush0", BlockStateData.J_1907_R("{Name:'minecraft:potted_dead_bush'}"));
        p_209306_0_.put("minecraft:tallgrass2", BlockStateData.J_1907_R("{Name:'minecraft:potted_fern'}"));
        p_209306_0_.put("minecraft:cactus0", BlockStateData.J_1907_R(2240));
    });
    private static final Map<String, Dynamic<?>> Q_4569_t = (Map)DataFixUtils.make((Object)Maps.newHashMap(), p_209308_0_ -> {
        F_1126_U.n_1700_B(p_209308_0_, 0, "skeleton", "skull");
        F_1126_U.n_1700_B(p_209308_0_, 1, "wither_skeleton", "skull");
        F_1126_U.n_1700_B(p_209308_0_, 2, "zombie", "head");
        F_1126_U.n_1700_B(p_209308_0_, 3, "player", "head");
        F_1126_U.n_1700_B(p_209308_0_, 4, "creeper", "head");
        F_1126_U.n_1700_B(p_209308_0_, 5, "dragon", "head");
    });
    private static final Map<String, Dynamic<?>> M_182_A = (Map)DataFixUtils.make((Object)Maps.newHashMap(), p_209298_0_ -> {
        F_1126_U.n_1700_B(p_209298_0_, "oak_door", 1024);
        F_1126_U.n_1700_B(p_209298_0_, "iron_door", 1136);
        F_1126_U.n_1700_B(p_209298_0_, "spruce_door", 3088);
        F_1126_U.n_1700_B(p_209298_0_, "birch_door", 3104);
        F_1126_U.n_1700_B(p_209298_0_, "jungle_door", 3120);
        F_1126_U.n_1700_B(p_209298_0_, "acacia_door", 3136);
        F_1126_U.n_1700_B(p_209298_0_, "dark_oak_door", 3152);
    });
    private static final Map<String, Dynamic<?>> t_1786_h = (Map)DataFixUtils.make((Object)Maps.newHashMap(), p_209302_0_ -> {
        for (int i = 0; i < 26; ++i) {
            p_209302_0_.put("true" + i, BlockStateData.J_1907_R("{Name:'minecraft:note_block',Properties:{powered:'true',note:'" + i + "'}}"));
            p_209302_0_.put("false" + i, BlockStateData.J_1907_R("{Name:'minecraft:note_block',Properties:{powered:'false',note:'" + i + "'}}"));
        }
    });
    private static final Int2ObjectMap<String> multiplayerClientSuggestionProvider = (Int2ObjectMap)DataFixUtils.make((Object)new Int2ObjectOpenHashMap(), p_209296_0_ -> {
        p_209296_0_.put(0, (Object)"white");
        p_209296_0_.put(1, (Object)"orange");
        p_209296_0_.put(2, (Object)"magenta");
        p_209296_0_.put(3, (Object)"light_blue");
        p_209296_0_.put(4, (Object)"yellow");
        p_209296_0_.put(5, (Object)"lime");
        p_209296_0_.put(6, (Object)"pink");
        p_209296_0_.put(7, (Object)"gray");
        p_209296_0_.put(8, (Object)"light_gray");
        p_209296_0_.put(9, (Object)"cyan");
        p_209296_0_.put(10, (Object)"purple");
        p_209296_0_.put(11, (Object)"blue");
        p_209296_0_.put(12, (Object)"brown");
        p_209296_0_.put(13, (Object)"green");
        p_209296_0_.put(14, (Object)"red");
        p_209296_0_.put(15, (Object)"black");
    });
    private static final Map<String, Dynamic<?>> w_1457_N = (Map)DataFixUtils.make((Object)Maps.newHashMap(), p_209304_0_ -> {
        for (Int2ObjectMap.Entry entry : multiplayerClientSuggestionProvider.int2ObjectEntrySet()) {
            if (Objects.equals(entry.getValue(), "red")) continue;
            F_1126_U.n_1700_B(p_209304_0_, entry.getIntKey(), (String)entry.getValue());
        }
    });
    private static final Map<String, Dynamic<?>> Y_601_j = (Map)DataFixUtils.make((Object)Maps.newHashMap(), p_209299_0_ -> {
        for (Int2ObjectMap.Entry entry : multiplayerClientSuggestionProvider.int2ObjectEntrySet()) {
            if (Objects.equals(entry.getValue(), "white")) continue;
            F_1126_U.J_1907_R(p_209299_0_, 15 - entry.getIntKey(), (String)entry.getValue());
        }
    });
    private static final Dynamic<?> Y_259_p = BlockStateData.J_1907_R(0);

    public F_1126_U(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    private static void n_1700_B(Map<String, Dynamic<?>> p_209300_0_, int p_209300_1_, String p_209300_2_, String p_209300_3_) {
        p_209300_0_.put(p_209300_1_ + "north", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209300_2_ + "_wall_" + p_209300_3_ + "',Properties:{facing:'north'}}"));
        p_209300_0_.put(p_209300_1_ + "east", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209300_2_ + "_wall_" + p_209300_3_ + "',Properties:{facing:'east'}}"));
        p_209300_0_.put(p_209300_1_ + "south", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209300_2_ + "_wall_" + p_209300_3_ + "',Properties:{facing:'south'}}"));
        p_209300_0_.put(p_209300_1_ + "west", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209300_2_ + "_wall_" + p_209300_3_ + "',Properties:{facing:'west'}}"));
        for (int i = 0; i < 16; ++i) {
            p_209300_0_.put("" + p_209300_1_ + i, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209300_2_ + "_" + p_209300_3_ + "',Properties:{rotation:'" + i + "'}}"));
        }
    }

    private static void n_1700_B(Map<String, Dynamic<?>> p_209301_0_, String p_209301_1_, int p_209301_2_) {
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastlowerleftfalsefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'east',half:'lower',hinge:'left',open:'false',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastlowerleftfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'east',half:'lower',hinge:'left',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastlowerlefttruefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'east',half:'lower',hinge:'left',open:'true',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastlowerlefttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'east',half:'lower',hinge:'left',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastlowerrightfalsefalse", BlockStateData.J_1907_R(p_209301_2_));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastlowerrightfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'east',half:'lower',hinge:'right',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastlowerrighttruefalse", BlockStateData.J_1907_R(p_209301_2_ + 4));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastlowerrighttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'east',half:'lower',hinge:'right',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastupperleftfalsefalse", BlockStateData.J_1907_R(p_209301_2_ + 8));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastupperleftfalsetrue", BlockStateData.J_1907_R(p_209301_2_ + 10));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastupperlefttruefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'east',half:'upper',hinge:'left',open:'true',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastupperlefttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'east',half:'upper',hinge:'left',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastupperrightfalsefalse", BlockStateData.J_1907_R(p_209301_2_ + 9));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastupperrightfalsetrue", BlockStateData.J_1907_R(p_209301_2_ + 11));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastupperrighttruefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'east',half:'upper',hinge:'right',open:'true',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "eastupperrighttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'east',half:'upper',hinge:'right',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northlowerleftfalsefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'lower',hinge:'left',open:'false',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northlowerleftfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'lower',hinge:'left',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northlowerlefttruefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'lower',hinge:'left',open:'true',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northlowerlefttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'lower',hinge:'left',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northlowerrightfalsefalse", BlockStateData.J_1907_R(p_209301_2_ + 3));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northlowerrightfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'lower',hinge:'right',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northlowerrighttruefalse", BlockStateData.J_1907_R(p_209301_2_ + 7));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northlowerrighttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'lower',hinge:'right',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northupperleftfalsefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'upper',hinge:'left',open:'false',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northupperleftfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'upper',hinge:'left',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northupperlefttruefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'upper',hinge:'left',open:'true',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northupperlefttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'upper',hinge:'left',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northupperrightfalsefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'upper',hinge:'right',open:'false',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northupperrightfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'upper',hinge:'right',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northupperrighttruefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'upper',hinge:'right',open:'true',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "northupperrighttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'north',half:'upper',hinge:'right',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southlowerleftfalsefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'lower',hinge:'left',open:'false',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southlowerleftfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'lower',hinge:'left',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southlowerlefttruefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'lower',hinge:'left',open:'true',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southlowerlefttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'lower',hinge:'left',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southlowerrightfalsefalse", BlockStateData.J_1907_R(p_209301_2_ + 1));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southlowerrightfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'lower',hinge:'right',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southlowerrighttruefalse", BlockStateData.J_1907_R(p_209301_2_ + 5));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southlowerrighttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'lower',hinge:'right',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southupperleftfalsefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'upper',hinge:'left',open:'false',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southupperleftfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'upper',hinge:'left',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southupperlefttruefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'upper',hinge:'left',open:'true',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southupperlefttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'upper',hinge:'left',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southupperrightfalsefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'upper',hinge:'right',open:'false',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southupperrightfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'upper',hinge:'right',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southupperrighttruefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'upper',hinge:'right',open:'true',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "southupperrighttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'south',half:'upper',hinge:'right',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westlowerleftfalsefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'lower',hinge:'left',open:'false',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westlowerleftfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'lower',hinge:'left',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westlowerlefttruefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'lower',hinge:'left',open:'true',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westlowerlefttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'lower',hinge:'left',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westlowerrightfalsefalse", BlockStateData.J_1907_R(p_209301_2_ + 2));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westlowerrightfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'lower',hinge:'right',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westlowerrighttruefalse", BlockStateData.J_1907_R(p_209301_2_ + 6));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westlowerrighttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'lower',hinge:'right',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westupperleftfalsefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'upper',hinge:'left',open:'false',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westupperleftfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'upper',hinge:'left',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westupperlefttruefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'upper',hinge:'left',open:'true',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westupperlefttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'upper',hinge:'left',open:'true',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westupperrightfalsefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'upper',hinge:'right',open:'false',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westupperrightfalsetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'upper',hinge:'right',open:'false',powered:'true'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westupperrighttruefalse", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'upper',hinge:'right',open:'true',powered:'false'}}"));
        p_209301_0_.put("minecraft:" + p_209301_1_ + "westupperrighttruetrue", BlockStateData.J_1907_R("{Name:'minecraft:" + p_209301_1_ + "',Properties:{facing:'west',half:'upper',hinge:'right',open:'true',powered:'true'}}"));
    }

    private static void n_1700_B(Map<String, Dynamic<?>> p_209307_0_, int p_209307_1_, String p_209307_2_) {
        p_209307_0_.put("southfalsefoot" + p_209307_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209307_2_ + "_bed',Properties:{facing:'south',occupied:'false',part:'foot'}}"));
        p_209307_0_.put("westfalsefoot" + p_209307_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209307_2_ + "_bed',Properties:{facing:'west',occupied:'false',part:'foot'}}"));
        p_209307_0_.put("northfalsefoot" + p_209307_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209307_2_ + "_bed',Properties:{facing:'north',occupied:'false',part:'foot'}}"));
        p_209307_0_.put("eastfalsefoot" + p_209307_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209307_2_ + "_bed',Properties:{facing:'east',occupied:'false',part:'foot'}}"));
        p_209307_0_.put("southfalsehead" + p_209307_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209307_2_ + "_bed',Properties:{facing:'south',occupied:'false',part:'head'}}"));
        p_209307_0_.put("westfalsehead" + p_209307_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209307_2_ + "_bed',Properties:{facing:'west',occupied:'false',part:'head'}}"));
        p_209307_0_.put("northfalsehead" + p_209307_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209307_2_ + "_bed',Properties:{facing:'north',occupied:'false',part:'head'}}"));
        p_209307_0_.put("eastfalsehead" + p_209307_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209307_2_ + "_bed',Properties:{facing:'east',occupied:'false',part:'head'}}"));
        p_209307_0_.put("southtruehead" + p_209307_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209307_2_ + "_bed',Properties:{facing:'south',occupied:'true',part:'head'}}"));
        p_209307_0_.put("westtruehead" + p_209307_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209307_2_ + "_bed',Properties:{facing:'west',occupied:'true',part:'head'}}"));
        p_209307_0_.put("northtruehead" + p_209307_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209307_2_ + "_bed',Properties:{facing:'north',occupied:'true',part:'head'}}"));
        p_209307_0_.put("easttruehead" + p_209307_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209307_2_ + "_bed',Properties:{facing:'east',occupied:'true',part:'head'}}"));
    }

    private static void J_1907_R(Map<String, Dynamic<?>> p_209297_0_, int p_209297_1_, String p_209297_2_) {
        for (int i = 0; i < 16; ++i) {
            p_209297_0_.put(i + "_" + p_209297_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209297_2_ + "_banner',Properties:{rotation:'" + i + "'}}"));
        }
        p_209297_0_.put("north_" + p_209297_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209297_2_ + "_wall_banner',Properties:{facing:'north'}}"));
        p_209297_0_.put("south_" + p_209297_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209297_2_ + "_wall_banner',Properties:{facing:'south'}}"));
        p_209297_0_.put("west_" + p_209297_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209297_2_ + "_wall_banner',Properties:{facing:'west'}}"));
        p_209297_0_.put("east_" + p_209297_1_, BlockStateData.J_1907_R("{Name:'minecraft:" + p_209297_2_ + "_wall_banner',Properties:{facing:'east'}}"));
    }

    public static String n_1700_B(Dynamic<?> p_209726_0_) {
        return p_209726_0_.get("Name").asString("");
    }

    public static String n_1700_B(Dynamic<?> p_209719_0_, String p_209719_1_) {
        return p_209719_0_.get("Properties").get(p_209719_1_).asString("");
    }

    public static int n_1700_B(t_252_P<Dynamic<?>> p_209724_0_, Dynamic<?> p_209724_1_) {
        int i = p_209724_0_.n_1700_B(p_209724_1_);
        if (i == -1) {
            i = p_209724_0_.J_1907_R(p_209724_1_);
        }
        return i;
    }

    private Dynamic<?> J_1907_R(Dynamic<?> p_209712_1_) {
        Optional optional = p_209712_1_.get("Level").result();
        return optional.isPresent() && ((Dynamic)optional.get()).get("Sections").asStreamOpt().result().isPresent() ? p_209712_1_.set("Level", new G_564_y((Dynamic)optional.get()).n_1700_B()) : p_209712_1_;
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.R_4764_Y);
        Type type1 = this.getOutputSchema().getType(References.R_4764_Y);
        return this.writeFixAndRead("ChunkPalettedStorageFix", type, type1, this::J_1907_R);
    }

    public static int n_1700_B(boolean p_210957_0_, boolean p_210957_1_, boolean p_210957_2_, boolean p_210957_3_) {
        int i = 0;
        if (p_210957_2_) {
            i = p_210957_1_ ? (i |= 2) : (p_210957_0_ ? (i |= 0x80) : (i |= 1));
        } else if (p_210957_3_) {
            i = p_210957_0_ ? (i |= 0x20) : (p_210957_1_ ? (i |= 8) : (i |= 0x10));
        } else if (p_210957_1_) {
            i |= 4;
        } else if (p_210957_0_) {
            i |= 0x40;
        }
        return i;
    }

    static {
        R_4764_Y.set(2);
        R_4764_Y.set(3);
        R_4764_Y.set(110);
        R_4764_Y.set(140);
        R_4764_Y.set(144);
        R_4764_Y.set(25);
        R_4764_Y.set(86);
        R_4764_Y.set(26);
        R_4764_Y.set(176);
        R_4764_Y.set(177);
        R_4764_Y.set(175);
        R_4764_Y.set(64);
        R_4764_Y.set(71);
        R_4764_Y.set(193);
        R_4764_Y.set(194);
        R_4764_Y.set(195);
        R_4764_Y.set(196);
        R_4764_Y.set(197);
        J_1907_R.set(54);
        J_1907_R.set(146);
        J_1907_R.set(25);
        J_1907_R.set(26);
        J_1907_R.set(51);
        J_1907_R.set(53);
        J_1907_R.set(67);
        J_1907_R.set(108);
        J_1907_R.set(109);
        J_1907_R.set(114);
        J_1907_R.set(128);
        J_1907_R.set(134);
        J_1907_R.set(135);
        J_1907_R.set(136);
        J_1907_R.set(156);
        J_1907_R.set(163);
        J_1907_R.set(164);
        J_1907_R.set(180);
        J_1907_R.set(203);
        J_1907_R.set(55);
        J_1907_R.set(85);
        J_1907_R.set(113);
        J_1907_R.set(188);
        J_1907_R.set(189);
        J_1907_R.set(190);
        J_1907_R.set(191);
        J_1907_R.set(192);
        J_1907_R.set(93);
        J_1907_R.set(94);
        J_1907_R.set(101);
        J_1907_R.set(102);
        J_1907_R.set(160);
        J_1907_R.set(106);
        J_1907_R.set(107);
        J_1907_R.set(183);
        J_1907_R.set(184);
        J_1907_R.set(185);
        J_1907_R.set(186);
        J_1907_R.set(187);
        J_1907_R.set(132);
        J_1907_R.set(139);
        J_1907_R.set(199);
    }

    static final class G_564_y {
        private int n_1700_B;
        private final R_4764_Y[] J_1907_R = new R_4764_Y[16];
        private final Dynamic<?> R_4764_Y;
        private final int G_564_y;
        private final int P_1922_E;
        private final Int2ObjectMap<Dynamic<?>> u_1723_Y = new Int2ObjectLinkedOpenHashMap(16);

        public G_564_y(Dynamic<?> p_i231449_1_) {
            this.R_4764_Y = p_i231449_1_;
            this.G_564_y = p_i231449_1_.get("xPos").asInt(0) << 4;
            this.P_1922_E = p_i231449_1_.get("zPos").asInt(0) << 4;
            p_i231449_1_.get("TileEntities").asStreamOpt().result().ifPresent(p_210061_1_ -> p_210061_1_.forEach(p_233150_1_ -> {
                int j4;
                int l3 = p_233150_1_.get("x").asInt(0) - this.G_564_y & 0xF;
                int i4 = p_233150_1_.get("y").asInt(0);
                int k4 = i4 << 8 | (j4 = p_233150_1_.get("z").asInt(0) - this.P_1922_E & 0xF) << 4 | l3;
                if (this.u_1723_Y.put(k4, p_233150_1_) != null) {
                    n_1700_B.warn("In chunk: {}x{} found a duplicate block entity at position: [{}, {}, {}]", (Object)this.G_564_y, (Object)this.P_1922_E, (Object)l3, (Object)i4, (Object)j4);
                }
            }));
            boolean flag = p_i231449_1_.get("convertedFromAlphaFormat").asBoolean(false);
            p_i231449_1_.get("Sections").asStreamOpt().result().ifPresent(p_210062_1_ -> p_210062_1_.forEach(p_210065_1_ -> {
                R_4764_Y chunkpaletteformat$section1 = new R_4764_Y((Dynamic<?>)p_210065_1_);
                this.n_1700_B = chunkpaletteformat$section1.J_1907_R(this.n_1700_B);
                this.J_1907_R[chunkpaletteformat$section1.n_1700_B] = chunkpaletteformat$section1;
            }));
            for (R_4764_Y chunkpaletteformat$section : this.J_1907_R) {
                if (chunkpaletteformat$section == null) continue;
                block14: for (Map.Entry entry : chunkpaletteformat$section.u_1723_Y.entrySet()) {
                    int i = chunkpaletteformat$section.n_1700_B << 12;
                    switch ((Integer)entry.getKey()) {
                        case 2: {
                            IntListIterator intListIterator = ((IntList)entry.getValue()).iterator();
                            while (intListIterator.hasNext()) {
                                String s12;
                                int i3 = (Integer)intListIterator.next();
                                Dynamic<?> dynamic11 = this.n_1700_B(i3 |= i);
                                if (!"minecraft:grass_block".equals(F_1126_U.n_1700_B(dynamic11)) || !"minecraft:snow".equals(s12 = F_1126_U.n_1700_B(this.n_1700_B(lightning.product.F_1126_U$G_564_y.n_1700_B(i3, lightning.product.F_1126_U$n_1700_B.J_1907_R)))) && !"minecraft:snow_layer".equals(s12)) continue;
                                this.n_1700_B(i3, u_1723_Y);
                            }
                            continue block14;
                        }
                        case 3: {
                            IntListIterator intListIterator = ((IntList)entry.getValue()).iterator();
                            while (intListIterator.hasNext()) {
                                String s11;
                                int l2 = (Integer)intListIterator.next();
                                Dynamic<?> dynamic10 = this.n_1700_B(l2 |= i);
                                if (!"minecraft:podzol".equals(F_1126_U.n_1700_B(dynamic10)) || !"minecraft:snow".equals(s11 = F_1126_U.n_1700_B(this.n_1700_B(lightning.product.F_1126_U$G_564_y.n_1700_B(l2, lightning.product.F_1126_U$n_1700_B.J_1907_R)))) && !"minecraft:snow_layer".equals(s11)) continue;
                                this.n_1700_B(l2, P_1922_E);
                            }
                            continue block14;
                        }
                        case 25: {
                            IntListIterator intListIterator = ((IntList)entry.getValue()).iterator();
                            while (intListIterator.hasNext()) {
                                int k2 = (Integer)intListIterator.next();
                                Dynamic<?> dynamic9 = this.R_4764_Y(k2 |= i);
                                if (dynamic9 == null) continue;
                                String s10 = Boolean.toString(dynamic9.get("powered").asBoolean(false)) + (byte)Math.min(Math.max(dynamic9.get("note").asInt(0), 0), 24);
                                this.n_1700_B(k2, t_1786_h.getOrDefault(s10, t_1786_h.get("false0")));
                            }
                            continue block14;
                        }
                        case 26: {
                            IntListIterator intListIterator = ((IntList)entry.getValue()).iterator();
                            while (intListIterator.hasNext()) {
                                String s16;
                                int k3;
                                int j2 = (Integer)intListIterator.next();
                                Dynamic<?> dynamic8 = this.J_1907_R(j2 |= i);
                                Dynamic<?> dynamic14 = this.n_1700_B(j2);
                                if (dynamic8 == null || (k3 = dynamic8.get("color").asInt(0)) == 14 || k3 < 0 || k3 >= 16 || !w_1457_N.containsKey(s16 = F_1126_U.n_1700_B(dynamic14, "facing") + F_1126_U.n_1700_B(dynamic14, "occupied") + F_1126_U.n_1700_B(dynamic14, "part") + k3)) continue;
                                this.n_1700_B(j2, w_1457_N.get(s16));
                            }
                            continue block14;
                        }
                        case 64: 
                        case 71: 
                        case 193: 
                        case 194: 
                        case 195: 
                        case 196: 
                        case 197: {
                            IntListIterator intListIterator = ((IntList)entry.getValue()).iterator();
                            while (intListIterator.hasNext()) {
                                Dynamic<?> dynamic13;
                                int i2 = (Integer)intListIterator.next();
                                Dynamic<?> dynamic7 = this.n_1700_B(i2 |= i);
                                if (!F_1126_U.n_1700_B(dynamic7).endsWith("_door") || !"lower".equals(F_1126_U.n_1700_B(dynamic13 = this.n_1700_B(i2), "half"))) continue;
                                int j3 = lightning.product.F_1126_U$G_564_y.n_1700_B(i2, lightning.product.F_1126_U$n_1700_B.J_1907_R);
                                Dynamic<?> dynamic15 = this.n_1700_B(j3);
                                String s1 = F_1126_U.n_1700_B(dynamic13);
                                if (!s1.equals(F_1126_U.n_1700_B(dynamic15))) continue;
                                String s2 = F_1126_U.n_1700_B(dynamic13, "facing");
                                String s3 = F_1126_U.n_1700_B(dynamic13, "open");
                                String s4 = flag ? "left" : F_1126_U.n_1700_B(dynamic15, "hinge");
                                String s5 = flag ? "false" : F_1126_U.n_1700_B(dynamic15, "powered");
                                this.n_1700_B(i2, M_182_A.get(s1 + s2 + "lower" + s4 + s3 + s5));
                                this.n_1700_B(j3, M_182_A.get(s1 + s2 + "upper" + s4 + s3 + s5));
                            }
                            continue block14;
                        }
                        case 86: {
                            IntListIterator intListIterator = ((IntList)entry.getValue()).iterator();
                            while (intListIterator.hasNext()) {
                                String s9;
                                int l1 = (Integer)intListIterator.next();
                                Dynamic<?> dynamic6 = this.n_1700_B(l1 |= i);
                                if (!"minecraft:carved_pumpkin".equals(F_1126_U.n_1700_B(dynamic6)) || !"minecraft:grass_block".equals(s9 = F_1126_U.n_1700_B(this.n_1700_B(lightning.product.F_1126_U$G_564_y.n_1700_B(l1, lightning.product.F_1126_U$n_1700_B.n_1700_B)))) && !"minecraft:dirt".equals(s9)) continue;
                                this.n_1700_B(l1, G_564_y);
                            }
                            continue block14;
                        }
                        case 110: {
                            IntListIterator intListIterator = ((IntList)entry.getValue()).iterator();
                            while (intListIterator.hasNext()) {
                                String s8;
                                int k1 = (Integer)intListIterator.next();
                                Dynamic<?> dynamic5 = this.n_1700_B(k1 |= i);
                                if (!"minecraft:mycelium".equals(F_1126_U.n_1700_B(dynamic5)) || !"minecraft:snow".equals(s8 = F_1126_U.n_1700_B(this.n_1700_B(lightning.product.F_1126_U$G_564_y.n_1700_B(k1, lightning.product.F_1126_U$n_1700_B.J_1907_R)))) && !"minecraft:snow_layer".equals(s8)) continue;
                                this.n_1700_B(k1, v_4262_N);
                            }
                            continue block14;
                        }
                        case 140: {
                            IntListIterator intListIterator = ((IntList)entry.getValue()).iterator();
                            while (intListIterator.hasNext()) {
                                int j1 = (Integer)intListIterator.next();
                                Dynamic<?> dynamic4 = this.R_4764_Y(j1 |= i);
                                if (dynamic4 == null) continue;
                                String s7 = dynamic4.get("Item").asString("") + dynamic4.get("Data").asInt(0);
                                this.n_1700_B(j1, h_1847_R.getOrDefault(s7, h_1847_R.get("minecraft:air0")));
                            }
                            continue block14;
                        }
                        case 144: {
                            IntListIterator intListIterator = ((IntList)entry.getValue()).iterator();
                            while (intListIterator.hasNext()) {
                                int i1 = (Integer)intListIterator.next();
                                Dynamic<?> dynamic3 = this.J_1907_R(i1 |= i);
                                if (dynamic3 == null) continue;
                                String s6 = String.valueOf(dynamic3.get("SkullType").asInt(0));
                                String s14 = F_1126_U.n_1700_B(this.n_1700_B(i1), "facing");
                                String s15 = !"up".equals(s14) && !"down".equals(s14) ? s6 + s14 : s6 + String.valueOf(dynamic3.get("Rot").asInt(0));
                                dynamic3.remove("SkullType");
                                dynamic3.remove("facing");
                                dynamic3.remove("Rot");
                                this.n_1700_B(i1, Q_4569_t.getOrDefault(s15, Q_4569_t.get("0north")));
                            }
                            continue block14;
                        }
                        case 175: {
                            IntListIterator intListIterator = ((IntList)entry.getValue()).iterator();
                            while (intListIterator.hasNext()) {
                                int l = (Integer)intListIterator.next();
                                Dynamic<?> dynamic2 = this.n_1700_B(l |= i);
                                if (!"upper".equals(F_1126_U.n_1700_B(dynamic2, "half"))) continue;
                                Dynamic<?> dynamic12 = this.n_1700_B(lightning.product.F_1126_U$G_564_y.n_1700_B(l, lightning.product.F_1126_U$n_1700_B.n_1700_B));
                                String s13 = F_1126_U.n_1700_B(dynamic12);
                                if ("minecraft:sunflower".equals(s13)) {
                                    this.n_1700_B(l, w_1484_f);
                                    continue;
                                }
                                if ("minecraft:lilac".equals(s13)) {
                                    this.n_1700_B(l, t_148_a);
                                    continue;
                                }
                                if ("minecraft:tall_grass".equals(s13)) {
                                    this.n_1700_B(l, s_956_w);
                                    continue;
                                }
                                if ("minecraft:large_fern".equals(s13)) {
                                    this.n_1700_B(l, u_2550_I);
                                    continue;
                                }
                                if ("minecraft:rose_bush".equals(s13)) {
                                    this.n_1700_B(l, M_588_G);
                                    continue;
                                }
                                if (!"minecraft:peony".equals(s13)) continue;
                                this.n_1700_B(l, P_4830_p);
                            }
                            continue block14;
                        }
                        case 176: 
                        case 177: {
                            IntListIterator intListIterator = ((IntList)entry.getValue()).iterator();
                            while (intListIterator.hasNext()) {
                                String s;
                                int k;
                                int j = (Integer)intListIterator.next();
                                Dynamic<?> dynamic = this.J_1907_R(j |= i);
                                Dynamic<?> dynamic1 = this.n_1700_B(j);
                                if (dynamic == null || (k = dynamic.get("Base").asInt(0)) == 15 || k < 0 || k >= 16 || !Y_601_j.containsKey(s = F_1126_U.n_1700_B(dynamic1, (Integer)entry.getKey() == 176 ? "rotation" : "facing") + "_" + k)) continue;
                                this.n_1700_B(j, Y_601_j.get(s));
                            }
                            break;
                        }
                    }
                }
            }
        }

        @Nullable
        private Dynamic<?> J_1907_R(int p_210066_1_) {
            return (Dynamic)this.u_1723_Y.get(p_210066_1_);
        }

        @Nullable
        private Dynamic<?> R_4764_Y(int p_210059_1_) {
            return (Dynamic)this.u_1723_Y.remove(p_210059_1_);
        }

        public static int n_1700_B(int p_199223_0_, n_1700_B p_199223_1_) {
            switch (p_199223_1_.J_1907_R().ordinal()) {
                case 0: {
                    int i = (p_199223_0_ & 0xF) + p_199223_1_.n_1700_B().n_1700_B();
                    return i >= 0 && i <= 15 ? p_199223_0_ & 0xFFFFFFF0 | i : -1;
                }
                case 1: {
                    int j = (p_199223_0_ >> 8) + p_199223_1_.n_1700_B().n_1700_B();
                    return j >= 0 && j <= 255 ? p_199223_0_ & 0xFF | j << 8 : -1;
                }
                case 2: {
                    int k = (p_199223_0_ >> 4 & 0xF) + p_199223_1_.n_1700_B().n_1700_B();
                    return k >= 0 && k <= 15 ? p_199223_0_ & 0xFFFFFF0F | k << 4 : -1;
                }
            }
            return -1;
        }

        private void n_1700_B(int p_210060_1_, Dynamic<?> p_210060_2_) {
            R_4764_Y chunkpaletteformat$section;
            if (p_210060_1_ >= 0 && p_210060_1_ <= 65535 && (chunkpaletteformat$section = this.G_564_y(p_210060_1_)) != null) {
                chunkpaletteformat$section.n_1700_B(p_210060_1_ & 0xFFF, p_210060_2_);
            }
        }

        @Nullable
        private R_4764_Y G_564_y(int p_199221_1_) {
            int i = p_199221_1_ >> 12;
            return i < this.J_1907_R.length ? this.J_1907_R[i] : null;
        }

        public Dynamic<?> n_1700_B(int p_210064_1_) {
            if (p_210064_1_ >= 0 && p_210064_1_ <= 65535) {
                R_4764_Y chunkpaletteformat$section = this.G_564_y(p_210064_1_);
                return chunkpaletteformat$section == null ? Y_259_p : chunkpaletteformat$section.n_1700_B(p_210064_1_ & 0xFFF);
            }
            return Y_259_p;
        }

        public Dynamic<?> n_1700_B() {
            Dynamic dynamic = this.R_4764_Y;
            dynamic = this.u_1723_Y.isEmpty() ? dynamic.remove("TileEntities") : dynamic.set("TileEntities", dynamic.createList(this.u_1723_Y.values().stream()));
            Dynamic dynamic1 = dynamic.emptyMap();
            ArrayList list = Lists.newArrayList();
            for (R_4764_Y chunkpaletteformat$section : this.J_1907_R) {
                if (chunkpaletteformat$section == null) continue;
                list.add(chunkpaletteformat$section.n_1700_B());
                dynamic1 = dynamic1.set(String.valueOf(chunkpaletteformat$section.n_1700_B), dynamic1.createIntList(Arrays.stream(chunkpaletteformat$section.v_4262_N.toIntArray())));
            }
            Dynamic dynamic2 = dynamic.emptyMap();
            dynamic2 = dynamic2.set("Sides", dynamic2.createByte((byte)this.n_1700_B));
            dynamic2 = dynamic2.set("Indices", dynamic1);
            return dynamic.set("UpgradeData", dynamic2).set("Sections", dynamic2.createList(list.stream()));
        }
    }

    static class R_4764_Y {
        private final t_252_P<Dynamic<?>> J_1907_R = new t_252_P(32);
        private final List<Dynamic<?>> R_4764_Y;
        private final Dynamic<?> G_564_y;
        private final boolean P_1922_E;
        private final Int2ObjectMap<IntList> u_1723_Y = new Int2ObjectLinkedOpenHashMap();
        private final IntList v_4262_N = new IntArrayList();
        public final int n_1700_B;
        private final Set<Dynamic<?>> w_1484_f = Sets.newIdentityHashSet();
        private final int[] t_148_a = new int[4096];

        public R_4764_Y(Dynamic<?> p_i231448_1_) {
            this.R_4764_Y = Lists.newArrayList();
            this.G_564_y = p_i231448_1_;
            this.n_1700_B = p_i231448_1_.get("Y").asInt(0);
            this.P_1922_E = p_i231448_1_.get("Blocks").result().isPresent();
        }

        public Dynamic<?> n_1700_B(int p_210056_1_) {
            if (p_210056_1_ >= 0 && p_210056_1_ <= 4095) {
                Dynamic<?> dynamic = this.J_1907_R.n_1700_B(this.t_148_a[p_210056_1_]);
                return dynamic == null ? Y_259_p : dynamic;
            }
            return Y_259_p;
        }

        public void n_1700_B(int p_210053_1_, Dynamic<?> p_210053_2_) {
            if (this.w_1484_f.add(p_210053_2_)) {
                this.R_4764_Y.add("%%FILTER_ME%%".equals(F_1126_U.n_1700_B(p_210053_2_)) ? Y_259_p : p_210053_2_);
            }
            this.t_148_a[p_210053_1_] = F_1126_U.n_1700_B(this.J_1907_R, p_210053_2_);
        }

        public int J_1907_R(int p_199207_1_) {
            if (!this.P_1922_E) {
                return p_199207_1_;
            }
            ByteBuffer bytebuffer = (ByteBuffer)this.G_564_y.get("Blocks").asByteBufferOpt().result().get();
            J_1907_R chunkpaletteformat$nibblearray = this.G_564_y.get("Data").asByteBufferOpt().map(p_210055_0_ -> new J_1907_R(DataFixUtils.toArray((ByteBuffer)p_210055_0_))).result().orElseGet(J_1907_R::new);
            J_1907_R chunkpaletteformat$nibblearray1 = this.G_564_y.get("Add").asByteBufferOpt().map(p_210052_0_ -> new J_1907_R(DataFixUtils.toArray((ByteBuffer)p_210052_0_))).result().orElseGet(J_1907_R::new);
            this.w_1484_f.add(Y_259_p);
            F_1126_U.n_1700_B(this.J_1907_R, Y_259_p);
            this.R_4764_Y.add(Y_259_p);
            for (int i = 0; i < 4096; ++i) {
                int j = i & 0xF;
                int k = i >> 8 & 0xF;
                int l = i >> 4 & 0xF;
                int i1 = chunkpaletteformat$nibblearray1.n_1700_B(j, k, l) << 12 | (bytebuffer.get(i) & 0xFF) << 4 | chunkpaletteformat$nibblearray.n_1700_B(j, k, l);
                if (R_4764_Y.get(i1 >> 4)) {
                    this.n_1700_B(i1 >> 4, i);
                }
                if (J_1907_R.get(i1 >> 4)) {
                    int j1 = F_1126_U.n_1700_B(j == 0, j == 15, l == 0, l == 15);
                    if (j1 == 0) {
                        this.v_4262_N.add(i);
                    } else {
                        p_199207_1_ |= j1;
                    }
                }
                this.n_1700_B(i, BlockStateData.J_1907_R(i1));
            }
            return p_199207_1_;
        }

        private void n_1700_B(int p_199205_1_, int p_199205_2_) {
            IntList intlist = (IntList)this.u_1723_Y.get(p_199205_1_);
            if (intlist == null) {
                intlist = new IntArrayList();
                this.u_1723_Y.put(p_199205_1_, (Object)intlist);
            }
            intlist.add(p_199205_2_);
        }

        public Dynamic<?> n_1700_B() {
            Dynamic dynamic = this.G_564_y;
            if (!this.P_1922_E) {
                return dynamic;
            }
            dynamic = dynamic.set("Palette", dynamic.createList(this.R_4764_Y.stream()));
            int i = Math.max(4, DataFixUtils.ceillog2((int)this.w_1484_f.size()));
            A_1434_p arbitrarybitlengthintarray = new A_1434_p(i, 4096);
            for (int j = 0; j < this.t_148_a.length; ++j) {
                arbitrarybitlengthintarray.n_1700_B(j, this.t_148_a[j]);
            }
            dynamic = dynamic.set("BlockStates", dynamic.createLongList(Arrays.stream(arbitrarybitlengthintarray.n_1700_B())));
            dynamic = dynamic.remove("Blocks");
            dynamic = dynamic.remove("Data");
            return dynamic.remove("Add");
        }
    }

    static class J_1907_R {
        private final byte[] n_1700_B;

        public J_1907_R() {
            this.n_1700_B = new byte[2048];
        }

        public J_1907_R(byte[] p_i49577_1_) {
            this.n_1700_B = p_i49577_1_;
            if (p_i49577_1_.length != 2048) {
                throw new IllegalArgumentException("ChunkNibbleArrays should be 2048 bytes not: " + p_i49577_1_.length);
            }
        }

        public int n_1700_B(int p_210932_1_, int p_210932_2_, int p_210932_3_) {
            int i = this.J_1907_R(p_210932_2_ << 8 | p_210932_3_ << 4 | p_210932_1_);
            return this.n_1700_B(p_210932_2_ << 8 | p_210932_3_ << 4 | p_210932_1_) ? this.n_1700_B[i] & 0xF : this.n_1700_B[i] >> 4 & 0xF;
        }

        private boolean n_1700_B(int p_210933_1_) {
            return (p_210933_1_ & 1) == 0;
        }

        private int J_1907_R(int p_210934_1_) {
            return p_210934_1_ >> 1;
        }
    }

    public static final class lightning.product.F_1126_U$n_1700_B
    extends Enum<lightning.product.F_1126_U$n_1700_B> {
        public static final /* enum */ lightning.product.F_1126_U$n_1700_B n_1700_B = new lightning.product.F_1126_U$n_1700_B(J_1907_R.J_1907_R, n_1700_B.J_1907_R);
        public static final /* enum */ lightning.product.F_1126_U$n_1700_B J_1907_R = new lightning.product.F_1126_U$n_1700_B(J_1907_R.n_1700_B, n_1700_B.J_1907_R);
        public static final /* enum */ lightning.product.F_1126_U$n_1700_B R_4764_Y = new lightning.product.F_1126_U$n_1700_B(J_1907_R.J_1907_R, n_1700_B.R_4764_Y);
        public static final /* enum */ lightning.product.F_1126_U$n_1700_B G_564_y = new lightning.product.F_1126_U$n_1700_B(J_1907_R.n_1700_B, n_1700_B.R_4764_Y);
        public static final /* enum */ lightning.product.F_1126_U$n_1700_B P_1922_E = new lightning.product.F_1126_U$n_1700_B(J_1907_R.J_1907_R, n_1700_B.n_1700_B);
        public static final /* enum */ lightning.product.F_1126_U$n_1700_B u_1723_Y = new lightning.product.F_1126_U$n_1700_B(J_1907_R.n_1700_B, n_1700_B.n_1700_B);
        private final n_1700_B v_4262_N;
        private final J_1907_R w_1484_f;
        private static final /* synthetic */ lightning.product.F_1126_U$n_1700_B[] t_148_a;

        public static lightning.product.F_1126_U$n_1700_B[] values() {
            return (lightning.product.F_1126_U$n_1700_B[])t_148_a.clone();
        }

        public static lightning.product.F_1126_U$n_1700_B valueOf(String name) {
            return Enum.valueOf(lightning.product.F_1126_U$n_1700_B.class, name);
        }

        private lightning.product.F_1126_U$n_1700_B(J_1907_R axisIn, n_1700_B directionIn) {
            this.v_4262_N = directionIn;
            this.w_1484_f = axisIn;
        }

        public J_1907_R n_1700_B() {
            return this.w_1484_f;
        }

        public n_1700_B J_1907_R() {
            return this.v_4262_N;
        }

        private static /* synthetic */ lightning.product.F_1126_U$n_1700_B[] R_4764_Y() {
            return new lightning.product.F_1126_U$n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            t_148_a = lightning.product.F_1126_U$n_1700_B.R_4764_Y();
        }

        public static final class n_1700_B
        extends Enum<n_1700_B> {
            public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
            public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
            public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
            private static final /* synthetic */ n_1700_B[] G_564_y;

            public static n_1700_B[] values() {
                return (n_1700_B[])G_564_y.clone();
            }

            public static n_1700_B valueOf(String name) {
                return Enum.valueOf(n_1700_B.class, name);
            }

            private static /* synthetic */ n_1700_B[] n_1700_B() {
                return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
            }

            static {
                G_564_y = lightning.product.F_1126_U$n_1700_B$n_1700_B.n_1700_B();
            }
        }

        public static final class J_1907_R
        extends Enum<J_1907_R> {
            public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(1);
            public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(-1);
            private final int R_4764_Y;
            private static final /* synthetic */ J_1907_R[] G_564_y;

            public static J_1907_R[] values() {
                return (J_1907_R[])G_564_y.clone();
            }

            public static J_1907_R valueOf(String name) {
                return Enum.valueOf(J_1907_R.class, name);
            }

            private J_1907_R(int p_i49694_3_) {
                this.R_4764_Y = p_i49694_3_;
            }

            public int n_1700_B() {
                return this.R_4764_Y;
            }

            private static /* synthetic */ J_1907_R[] J_1907_R() {
                return new J_1907_R[]{n_1700_B, J_1907_R};
            }

            static {
                G_564_y = lightning.product.F_1126_U$n_1700_B$J_1907_R.J_1907_R();
            }
        }
    }
}


