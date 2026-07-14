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
import java.util.Optional;
import java.util.function.Function;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public abstract class G_671_s
extends DataFix {
    private final String n_1700_B;

    public G_671_s(Schema outputSchema, String name) {
        super(outputSchema, false);
        this.n_1700_B = name;
    }

    public TypeRewriteRule makeRule() {
        Type type1;
        Type type = this.getInputSchema().getType(References.t_1786_h);
        if (!Objects.equals(type, type1 = DSL.named((String)References.t_1786_h.typeName(), NamespacedSchema.n_1700_B()))) {
            throw new IllegalStateException("block type is not what was expected.");
        }
        TypeRewriteRule typerewriterule = this.fixTypeEverywhere(this.n_1700_B + " for block", type1, p_209705_1_ -> p_206308_1_ -> p_206308_1_.mapSecond(this::n_1700_B));
        TypeRewriteRule typerewriterule1 = this.fixTypeEverywhereTyped(this.n_1700_B + " for block_state", this.getInputSchema().getType(References.P_4830_p), p_209704_1_ -> p_209704_1_.update(DSL.remainderFinder(), p_207439_1_ -> {
            Optional optional = p_207439_1_.get("Name").asString().result();
            return optional.isPresent() ? p_207439_1_.set("Name", p_207439_1_.createString(this.n_1700_B((String)optional.get()))) : p_207439_1_;
        }));
        return TypeRewriteRule.seq((TypeRewriteRule)typerewriterule, (TypeRewriteRule)typerewriterule1);
    }

    protected abstract String n_1700_B(String var1);

    public static DataFix n_1700_B(Schema p_207437_0_, String p_207437_1_, final Function<String, String> p_207437_2_) {
        return new G_671_s(p_207437_0_, p_207437_1_){

            @Override
            protected String n_1700_B(String p_206309_1_) {
                return (String)p_207437_2_.apply(p_206309_1_);
            }
        };
    }
}


