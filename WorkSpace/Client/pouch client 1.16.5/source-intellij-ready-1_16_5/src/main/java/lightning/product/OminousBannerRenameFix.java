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
 *  com.mojang.datafixers.util.Pair
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
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.Objects;
import java.util.Optional;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class OminousBannerRenameFix
extends DataFix {
    public OminousBannerRenameFix(Schema p_i50433_1_, boolean p_i50433_2_) {
        super(p_i50433_1_, p_i50433_2_);
    }

    private Dynamic<?> n_1700_B(Dynamic<?> p_219818_1_) {
        Optional optional = p_219818_1_.get("display").result();
        if (optional.isPresent()) {
            Dynamic dynamic = (Dynamic)optional.get();
            Optional optional1 = dynamic.get("Name").asString().result();
            if (optional1.isPresent()) {
                String s = (String)optional1.get();
                s = s.replace("\"translate\":\"block.minecraft.illager_banner\"", "\"translate\":\"block.minecraft.ominous_banner\"");
                dynamic = dynamic.set("Name", dynamic.createString(s));
            }
            return p_219818_1_.set("display", dynamic);
        }
        return p_219818_1_;
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.M_588_G);
        OpticFinder opticfinder = DSL.fieldFinder((String)"id", (Type)DSL.named((String)References.multiplayerClientSuggestionProvider.typeName(), NamespacedSchema.n_1700_B()));
        OpticFinder opticfinder1 = type.findField("tag");
        return this.fixTypeEverywhereTyped("OminousBannerRenameFix", type, p_219819_3_ -> {
            Optional optional1;
            Optional optional = p_219819_3_.getOptional(opticfinder);
            if (optional.isPresent() && Objects.equals(((Pair)optional.get()).getSecond(), "minecraft:white_banner") && (optional1 = p_219819_3_.getOptionalTyped(opticfinder1)).isPresent()) {
                Typed typed = (Typed)optional1.get();
                Dynamic dynamic = (Dynamic)typed.get(DSL.remainderFinder());
                return p_219819_3_.set(opticfinder1, typed.set(DSL.remainderFinder(), this.n_1700_B(dynamic)));
            }
            return p_219819_3_;
        });
    }
}


