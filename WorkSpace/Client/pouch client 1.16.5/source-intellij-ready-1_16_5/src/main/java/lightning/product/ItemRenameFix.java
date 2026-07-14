/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import java.util.Objects;
import java.util.function.Function;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public abstract class ItemRenameFix
extends DataFix {
    private final String n_1700_B;

    public ItemRenameFix(Schema outputSchema, String name) {
        super(outputSchema, false);
        this.n_1700_B = name;
    }

    public TypeRewriteRule makeRule() {
        Type type = DSL.named((String)References.multiplayerClientSuggestionProvider.typeName(), NamespacedSchema.n_1700_B());
        if (!Objects.equals(this.getInputSchema().getType(References.multiplayerClientSuggestionProvider), type)) {
            throw new IllegalStateException("item name type is not what was expected.");
        }
        return this.fixTypeEverywhere(this.n_1700_B, type, p_211012_1_ -> p_206354_1_ -> p_206354_1_.mapSecond(this::n_1700_B));
    }

    protected abstract String n_1700_B(String var1);

    public static DataFix n_1700_B(Schema p_207476_0_, String p_207476_1_, final Function<String, String> p_207476_2_) {
        return new ItemRenameFix(p_207476_0_, p_207476_1_){

            @Override
            protected String n_1700_B(String p_206355_1_) {
                return (String)p_207476_2_.apply(p_206355_1_);
            }
        };
    }
}


