/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.templates.Hook$HookFunction
 *  com.mojang.datafixers.types.templates.TypeTemplate
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.Hook;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.Map;
import java.util.function.Supplier;
import lightning.product.References;
import lightning.product.R_4769_o;

public class V102
extends Schema {
    public V102(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    public void registerTypes(Schema p_registerTypes_1_, Map<String, Supplier<TypeTemplate>> p_registerTypes_2_, Map<String, Supplier<TypeTemplate>> p_registerTypes_3_) {
        super.registerTypes(p_registerTypes_1_, p_registerTypes_2_, p_registerTypes_3_);
        p_registerTypes_1_.registerType(true, References.M_588_G, () -> DSL.hook((TypeTemplate)DSL.optionalFields((String)"id", (TypeTemplate)References.multiplayerClientSuggestionProvider.in(p_registerTypes_1_), (String)"tag", (TypeTemplate)DSL.optionalFields((String)"EntityTag", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_), (String)"BlockEntityTag", (TypeTemplate)References.u_2550_I.in(p_registerTypes_1_), (String)"CanDestroy", (TypeTemplate)DSL.list((TypeTemplate)References.t_1786_h.in(p_registerTypes_1_)), (String)"CanPlaceOn", (TypeTemplate)DSL.list((TypeTemplate)References.t_1786_h.in(p_registerTypes_1_)))), (Hook.HookFunction)R_4769_o.n_1700_B, (Hook.HookFunction)Hook.HookFunction.IDENTITY));
    }
}


