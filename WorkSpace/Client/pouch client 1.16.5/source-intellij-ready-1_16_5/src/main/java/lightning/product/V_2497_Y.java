/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.Map;
import java.util.Optional;
import lightning.product.References;
import lightning.product.NamedEntityFix;

public class V_2497_Y
extends NamedEntityFix {
    public V_2497_Y(Schema p_i50420_1_, String p_i50420_2_) {
        super(p_i50420_1_, false, "Villager profession data fix (" + p_i50420_2_ + ")", References.M_182_A, p_i50420_2_);
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        Dynamic dynamic = (Dynamic)p_207419_1_.get(DSL.remainderFinder());
        return p_207419_1_.set(DSL.remainderFinder(), (Object)dynamic.remove("Profession").remove("Career").remove("CareerLevel").set("VillagerData", dynamic.createMap((Map)ImmutableMap.of((Object)dynamic.createString("type"), (Object)dynamic.createString("minecraft:plains"), (Object)dynamic.createString("profession"), (Object)dynamic.createString(V_2497_Y.n_1700_B(dynamic.get("Profession").asInt(0), dynamic.get("Career").asInt(0))), (Object)dynamic.createString("level"), (Object)((Dynamic)DataFixUtils.orElse((Optional)dynamic.get("CareerLevel").result(), (Object)dynamic.createInt(1)))))));
    }

    private static String n_1700_B(int p_219811_0_, int p_219811_1_) {
        if (p_219811_0_ == 0) {
            if (p_219811_1_ == 2) {
                return "minecraft:fisherman";
            }
            if (p_219811_1_ == 3) {
                return "minecraft:shepherd";
            }
            return p_219811_1_ == 4 ? "minecraft:fletcher" : "minecraft:farmer";
        }
        if (p_219811_0_ == 1) {
            return p_219811_1_ == 2 ? "minecraft:cartographer" : "minecraft:librarian";
        }
        if (p_219811_0_ == 2) {
            return "minecraft:cleric";
        }
        if (p_219811_0_ == 3) {
            if (p_219811_1_ == 2) {
                return "minecraft:weaponsmith";
            }
            return p_219811_1_ == 3 ? "minecraft:toolsmith" : "minecraft:armorer";
        }
        if (p_219811_0_ == 4) {
            return p_219811_1_ == 2 ? "minecraft:leatherworker" : "minecraft:butcher";
        }
        return p_219811_0_ == 5 ? "minecraft:nitwit" : "minecraft:none";
    }
}


