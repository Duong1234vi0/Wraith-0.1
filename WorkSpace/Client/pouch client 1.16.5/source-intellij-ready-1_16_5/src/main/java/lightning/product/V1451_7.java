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
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class V1451_7
extends NamespacedSchema {
    public V1451_7(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    public void registerTypes(Schema p_registerTypes_1_, Map<String, Supplier<TypeTemplate>> p_registerTypes_2_, Map<String, Supplier<TypeTemplate>> p_registerTypes_3_) {
        super.registerTypes(p_registerTypes_1_, p_registerTypes_2_, p_registerTypes_3_);
        p_registerTypes_1_.registerType(false, References.Y_601_j, () -> DSL.optionalFields((String)"Children", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"CA", (TypeTemplate)References.P_4830_p.in(p_registerTypes_1_), (String)"CB", (TypeTemplate)References.P_4830_p.in(p_registerTypes_1_), (String)"CC", (TypeTemplate)References.P_4830_p.in(p_registerTypes_1_), (String)"CD", (TypeTemplate)References.P_4830_p.in(p_registerTypes_1_)))));
    }
}


