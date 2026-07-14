/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import lightning.product.References;

public class IglooMetadataRemovalFix
extends DataFix {
    public IglooMetadataRemovalFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.Y_601_j);
        Type type1 = this.getOutputSchema().getType(References.Y_601_j);
        return this.writeFixAndRead("IglooMetadataRemovalFix", type, type1, IglooMetadataRemovalFix::n_1700_B);
    }

    private static <T> Dynamic<T> n_1700_B(Dynamic<T> p_211926_0_) {
        boolean flag = p_211926_0_.get("Children").asStreamOpt().map(p_211928_0_ -> p_211928_0_.allMatch(IglooMetadataRemovalFix::R_4764_Y)).result().orElse(false);
        return flag ? p_211926_0_.set("id", p_211926_0_.createString("Igloo")).remove("Children") : p_211926_0_.update("Children", IglooMetadataRemovalFix::J_1907_R);
    }

    private static <T> Dynamic<T> J_1907_R(Dynamic<T> p_211929_0_) {
        return p_211929_0_.asStreamOpt().map(p_211925_0_ -> p_211925_0_.filter(p_211927_0_ -> !IglooMetadataRemovalFix.R_4764_Y(p_211927_0_))).map(arg_0 -> p_211929_0_.createList(arg_0)).result().orElse(p_211929_0_);
    }

    private static boolean R_4764_Y(Dynamic<?> p_211930_0_) {
        return p_211930_0_.get("id").asString("").equals("Iglu");
    }
}


