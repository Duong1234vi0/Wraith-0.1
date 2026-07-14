/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.CompoundList$CompoundListType
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.CompoundList;
import com.mojang.serialization.Dynamic;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class NewVillageFix
extends DataFix {
    public NewVillageFix(Schema p_i50423_1_, boolean p_i50423_2_) {
        super(p_i50423_1_, p_i50423_2_);
    }

    protected TypeRewriteRule makeRule() {
        CompoundList.CompoundListType compoundlisttype = DSL.compoundList((Type)DSL.string(), (Type)this.getInputSchema().getType(References.Y_601_j));
        OpticFinder opticfinder = compoundlisttype.finder();
        return this.n_1700_B(compoundlisttype);
    }

    private <SF> TypeRewriteRule n_1700_B(CompoundList.CompoundListType<String, SF> p_219848_1_) {
        Type type = this.getInputSchema().getType(References.R_4764_Y);
        Type type1 = this.getInputSchema().getType(References.Y_601_j);
        OpticFinder opticfinder = type.findField("Level");
        OpticFinder opticfinder1 = opticfinder.type().findField("Structures");
        OpticFinder opticfinder2 = opticfinder1.type().findField("Starts");
        OpticFinder opticfinder3 = p_219848_1_.finder();
        return TypeRewriteRule.seq((TypeRewriteRule)this.fixTypeEverywhereTyped("NewVillageFix", type, p_219841_4_ -> p_219841_4_.updateTyped(opticfinder, p_219849_3_ -> p_219849_3_.updateTyped(opticfinder1, p_219842_2_ -> p_219842_2_.updateTyped(opticfinder2, p_219850_1_ -> p_219850_1_.update(opticfinder3, p_219851_0_ -> p_219851_0_.stream().filter(p_219854_0_ -> !Objects.equals(p_219854_0_.getFirst(), "Village")).map(p_219852_0_ -> p_219852_0_.mapFirst(p_219847_0_ -> p_219847_0_.equals("New_Village") ? "Village" : p_219847_0_)).collect(Collectors.toList()))).update(DSL.remainderFinder(), p_219843_0_ -> p_219843_0_.update("References", p_219844_0_ -> {
            Optional optional = p_219844_0_.get("New_Village").result();
            return ((Dynamic)DataFixUtils.orElse(optional.map(p_219846_1_ -> p_219844_0_.remove("New_Village").set("Village", p_219846_1_)), (Object)p_219844_0_)).remove("Village");
        }))))), (TypeRewriteRule)this.fixTypeEverywhereTyped("NewVillageStartFix", type1, p_219853_0_ -> p_219853_0_.update(DSL.remainderFinder(), p_219840_0_ -> p_219840_0_.update("id", p_219845_0_ -> Objects.equals(NamespacedSchema.n_1700_B(p_219845_0_.asString("")), "minecraft:new_village") ? p_219845_0_.createString("minecraft:village") : p_219845_0_))));
    }
}


