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
import com.mojang.serialization.Dynamic;
import java.util.Optional;
import java.util.stream.Stream;
import lightning.product.References;
import lightning.product.U_2871_b;
import lightning.product.x_282_a;

public class ItemLoreFix
extends DataFix {
    public ItemLoreFix(Schema p_i50426_1_, boolean p_i50426_2_) {
        super(p_i50426_1_, p_i50426_2_);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.M_588_G);
        OpticFinder opticfinder = type.findField("tag");
        return this.fixTypeEverywhereTyped("Item Lore componentize", type, p_219832_1_ -> p_219832_1_.updateTyped(opticfinder, p_219836_0_ -> p_219836_0_.update(DSL.remainderFinder(), p_219835_0_ -> p_219835_0_.update("display", p_219833_0_ -> p_219833_0_.update("Lore", p_219834_0_ -> (Dynamic)DataFixUtils.orElse((Optional)p_219834_0_.asStreamOpt().map(ItemLoreFix::n_1700_B).map(arg_0 -> ((Dynamic)p_219834_0_).createList(arg_0)).result(), (Object)p_219834_0_))))));
    }

    private static <T> Stream<Dynamic<T>> n_1700_B(Stream<Dynamic<T>> p_219830_0_) {
        return p_219830_0_.map(p_219831_0_ -> (Dynamic)DataFixUtils.orElse((Optional)p_219831_0_.asString().map(ItemLoreFix::n_1700_B).map(arg_0 -> ((Dynamic)p_219831_0_).createString(arg_0)).result(), (Object)p_219831_0_));
    }

    private static String n_1700_B(String p_219837_0_) {
        return x_282_a.n_1700_B.n_1700_B(new U_2871_b(p_219837_0_));
    }
}


