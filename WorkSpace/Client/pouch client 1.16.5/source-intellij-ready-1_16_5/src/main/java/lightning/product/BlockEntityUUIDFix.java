/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import lightning.product.References;
import lightning.product.z_2197_Y;

public class BlockEntityUUIDFix
extends z_2197_Y {
    public BlockEntityUUIDFix(Schema p_i231447_1_) {
        super(p_i231447_1_, References.u_2550_I);
    }

    protected TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("BlockEntityUUIDFix", this.getInputSchema().getType(this.J_1907_R), p_233113_1_ -> {
            p_233113_1_ = this.n_1700_B((Typed<?>)p_233113_1_, "minecraft:conduit", this::R_4764_Y);
            return this.n_1700_B((Typed<?>)p_233113_1_, "minecraft:skull", this::J_1907_R);
        });
    }

    private Dynamic<?> J_1907_R(Dynamic<?> p_233115_1_) {
        return p_233115_1_.get("Owner").get().map(p_233117_0_ -> BlockEntityUUIDFix.n_1700_B(p_233117_0_, "Id", "Id").orElse((Dynamic<?>)p_233117_0_)).map(p_233114_1_ -> p_233115_1_.remove("Owner").set("SkullOwner", p_233114_1_)).result().orElse(p_233115_1_);
    }

    private Dynamic<?> R_4764_Y(Dynamic<?> p_233116_1_) {
        return BlockEntityUUIDFix.J_1907_R(p_233116_1_, "target_uuid", "Target").orElse(p_233116_1_);
    }
}


