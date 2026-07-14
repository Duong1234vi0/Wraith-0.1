/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonParseException
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.google.gson.JsonParseException;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import java.util.Optional;
import lightning.product.References;
import lightning.product.U_2871_b;
import lightning.product.i_4431_W;
import lightning.product.BlockEntitySignTextStrictJsonFix;
import lightning.product.x_282_a;
import org.apache.commons.lang3.StringUtils;

public class ItemWrittenBookPagesStrictJsonFix
extends DataFix {
    public ItemWrittenBookPagesStrictJsonFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_209633_1_) {
        return p_209633_1_.update("pages", p_212821_1_ -> (Dynamic)DataFixUtils.orElse((Optional)p_212821_1_.asStreamOpt().map(p_209630_0_ -> p_209630_0_.map(p_209631_0_ -> {
            if (!p_209631_0_.asString().result().isPresent()) {
                return p_209631_0_;
            }
            String s = p_209631_0_.asString("");
            x_282_a itextcomponent = null;
            if (!"null".equals(s) && !StringUtils.isEmpty((CharSequence)s)) {
                if (s.charAt(0) == '\"' && s.charAt(s.length() - 1) == '\"' || s.charAt(0) == '{' && s.charAt(s.length() - 1) == '}') {
                    try {
                        itextcomponent = i_4431_W.n_1700_B(BlockEntitySignTextStrictJsonFix.n_1700_B, s, x_282_a.class, true);
                        if (itextcomponent == null) {
                            itextcomponent = U_2871_b.R_4764_Y;
                        }
                    }
                    catch (JsonParseException jsonParseException) {
                        // empty catch block
                    }
                    if (itextcomponent == null) {
                        try {
                            itextcomponent = x_282_a.n_1700_B.n_1700_B(s);
                        }
                        catch (JsonParseException jsonParseException) {
                            // empty catch block
                        }
                    }
                    if (itextcomponent == null) {
                        try {
                            itextcomponent = x_282_a.n_1700_B.J_1907_R(s);
                        }
                        catch (JsonParseException jsonParseException) {
                            // empty catch block
                        }
                    }
                    if (itextcomponent == null) {
                        itextcomponent = new U_2871_b(s);
                    }
                } else {
                    itextcomponent = new U_2871_b(s);
                }
            } else {
                itextcomponent = U_2871_b.R_4764_Y;
            }
            return p_209631_0_.createString(x_282_a.n_1700_B.n_1700_B(itextcomponent));
        })).map(arg_0 -> ((Dynamic)p_209633_1_).createList(arg_0)).result(), (Object)p_209633_1_.emptyList()));
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.M_588_G);
        OpticFinder opticfinder = type.findField("tag");
        return this.fixTypeEverywhereTyped("ItemWrittenBookPagesStrictJsonFix", type, p_207415_2_ -> p_207415_2_.updateTyped(opticfinder, p_207417_1_ -> p_207417_1_.update(DSL.remainderFinder(), this::n_1700_B)));
    }
}


