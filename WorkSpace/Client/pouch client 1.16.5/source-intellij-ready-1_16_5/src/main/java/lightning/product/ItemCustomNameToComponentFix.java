/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import java.util.Optional;
import lightning.product.F_2904_S;
import lightning.product.References;
import lightning.product.U_2871_b;
import lightning.product.x_282_a;

public class ItemCustomNameToComponentFix
extends DataFix {
    public ItemCustomNameToComponentFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    private Dynamic<?> n_1700_B(Dynamic<?> p_209621_1_) {
        Optional optional = p_209621_1_.get("display").result();
        if (optional.isPresent()) {
            Dynamic dynamic = (Dynamic)optional.get();
            Optional optional1 = dynamic.get("Name").asString().result();
            if (optional1.isPresent()) {
                dynamic = dynamic.set("Name", dynamic.createString(x_282_a.n_1700_B.n_1700_B(new U_2871_b((String)optional1.get()))));
            } else {
                Optional optional2 = dynamic.get("LocName").asString().result();
                if (optional2.isPresent()) {
                    dynamic = dynamic.set("Name", dynamic.createString(x_282_a.n_1700_B.n_1700_B(new F_2904_S((String)optional2.get()))));
                    dynamic = dynamic.remove("LocName");
                }
            }
            return p_209621_1_.set("display", dynamic);
        }
        return p_209621_1_;
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.M_588_G);
        OpticFinder opticfinder = type.findField("tag");
        return this.fixTypeEverywhereTyped("ItemCustomNameToComponentFix", type, p_207467_2_ -> p_207467_2_.updateTyped(opticfinder, p_207469_1_ -> p_207469_1_.update(DSL.remainderFinder(), this::n_1700_B)));
    }
}


