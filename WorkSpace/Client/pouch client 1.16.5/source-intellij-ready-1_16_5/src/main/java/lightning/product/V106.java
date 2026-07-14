/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.templates.TypeTemplate
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.Map;
import java.util.function.Supplier;
import lightning.product.References;

public class V106
extends Schema {
    public V106(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    public void registerTypes(Schema p_registerTypes_1_, Map<String, Supplier<TypeTemplate>> p_registerTypes_2_, Map<String, Supplier<TypeTemplate>> p_registerTypes_3_) {
        super.registerTypes(p_registerTypes_1_, p_registerTypes_2_, p_registerTypes_3_);
        p_registerTypes_1_.registerType(true, References.w_1457_N, () -> DSL.optionalFields((String)"SpawnPotentials", (TypeTemplate)DSL.list((TypeTemplate)DSL.fields((String)"Entity", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_))), (String)"SpawnData", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_)));
    }
}


