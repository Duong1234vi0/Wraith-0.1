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

public class V1022
extends Schema {
    public V1022(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    public void registerTypes(Schema p_registerTypes_1_, Map<String, Supplier<TypeTemplate>> p_registerTypes_2_, Map<String, Supplier<TypeTemplate>> p_registerTypes_3_) {
        super.registerTypes(p_registerTypes_1_, p_registerTypes_2_, p_registerTypes_3_);
        p_registerTypes_1_.registerType(false, References.C_2741_M, () -> DSL.constType(NamespacedSchema.n_1700_B()));
        p_registerTypes_1_.registerType(false, References.J_1907_R, () -> DSL.optionalFields((String)"RootVehicle", (TypeTemplate)DSL.optionalFields((String)"Entity", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_)), (String)"Inventory", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerTypes_1_)), (String)"EnderItems", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerTypes_1_)), (TypeTemplate)DSL.optionalFields((String)"ShoulderEntityLeft", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_), (String)"ShoulderEntityRight", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_), (String)"recipeBook", (TypeTemplate)DSL.optionalFields((String)"recipes", (TypeTemplate)DSL.list((TypeTemplate)References.C_2741_M.in(p_registerTypes_1_)), (String)"toBeDisplayed", (TypeTemplate)DSL.list((TypeTemplate)References.C_2741_M.in(p_registerTypes_1_))))));
        p_registerTypes_1_.registerType(false, References.G_564_y, () -> DSL.compoundList((TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerTypes_1_))));
    }
}


