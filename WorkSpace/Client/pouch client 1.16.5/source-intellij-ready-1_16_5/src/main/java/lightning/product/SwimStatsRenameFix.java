/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 */
package lightning.product;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class SwimStatsRenameFix
extends DataFix {
    public SwimStatsRenameFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getOutputSchema().getType(References.v_4262_N);
        Type type1 = this.getInputSchema().getType(References.v_4262_N);
        OpticFinder opticfinder = type1.findField("stats");
        OpticFinder opticfinder1 = opticfinder.type().findField("minecraft:custom");
        OpticFinder opticfinder2 = NamespacedSchema.n_1700_B().finder();
        return this.fixTypeEverywhereTyped("SwimStatsRenameFix", type1, type, p_211690_3_ -> p_211690_3_.updateTyped(opticfinder, p_211692_2_ -> p_211692_2_.updateTyped(opticfinder1, p_211691_1_ -> p_211691_1_.update(opticfinder2, p_211693_0_ -> {
            if (p_211693_0_.equals("minecraft:swim_one_cm")) {
                return "minecraft:walk_on_water_one_cm";
            }
            return p_211693_0_.equals("minecraft:dive_one_cm") ? "minecraft:walk_under_water_one_cm" : p_211693_0_;
        }))));
    }
}


