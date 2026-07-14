/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.List$ListType
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.List;
import com.mojang.serialization.Dynamic;
import java.util.Optional;
import lightning.product.References;
import lightning.product.u_530_F;

public class VillagerRebuildLevelAndXpFix
extends DataFix {
    private static final int[] n_1700_B = new int[]{0, 10, 50, 100, 150};

    public static int n_1700_B(int p_223001_0_) {
        return n_1700_B[u_530_F.n_1700_B(p_223001_0_ - 1, 0, n_1700_B.length - 1)];
    }

    public VillagerRebuildLevelAndXpFix(Schema p_i51508_1_, boolean p_i51508_2_) {
        super(p_i51508_1_, p_i51508_2_);
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getChoiceType(References.M_182_A, "minecraft:villager");
        OpticFinder opticfinder = DSL.namedChoice((String)"minecraft:villager", (Type)type);
        OpticFinder opticfinder1 = type.findField("Offers");
        Type type1 = opticfinder1.type();
        OpticFinder opticfinder2 = type1.findField("Recipes");
        List.ListType listtype = (List.ListType)opticfinder2.type();
        OpticFinder opticfinder3 = listtype.getElement().finder();
        return this.fixTypeEverywhereTyped("Villager level and xp rebuild", this.getInputSchema().getType(References.M_182_A), p_222996_5_ -> p_222996_5_.updateTyped(opticfinder, type, p_222995_3_ -> {
            Optional optional;
            int j;
            Dynamic dynamic = (Dynamic)p_222995_3_.get(DSL.remainderFinder());
            int i = dynamic.get("VillagerData").get("level").asInt(0);
            Typed<?> typed = p_222995_3_;
            if ((i == 0 || i == 1) && (i = u_530_F.n_1700_B((j = p_222995_3_.getOptionalTyped(opticfinder1).flatMap(p_223002_1_ -> p_223002_1_.getOptionalTyped(opticfinder2)).map(p_222997_1_ -> p_222997_1_.getAllTyped(opticfinder3).size()).orElse(0).intValue()) / 2, 1, 5)) > 1) {
                typed = VillagerRebuildLevelAndXpFix.n_1700_B(p_222995_3_, i);
            }
            if (!(optional = dynamic.get("Xp").asNumber().result()).isPresent()) {
                typed = VillagerRebuildLevelAndXpFix.J_1907_R(typed, i);
            }
            return typed;
        }));
    }

    private static Typed<?> n_1700_B(Typed<?> p_223003_0_, int p_223003_1_) {
        return p_223003_0_.update(DSL.remainderFinder(), p_222998_1_ -> p_222998_1_.update("VillagerData", p_222999_1_ -> p_222999_1_.set("level", p_222999_1_.createInt(p_223003_1_))));
    }

    private static Typed<?> J_1907_R(Typed<?> p_222994_0_, int p_222994_1_) {
        int i = VillagerRebuildLevelAndXpFix.n_1700_B(p_222994_1_);
        return p_222994_0_.update(DSL.remainderFinder(), p_223000_1_ -> p_223000_1_.set("Xp", p_223000_1_.createInt(i)));
    }
}


