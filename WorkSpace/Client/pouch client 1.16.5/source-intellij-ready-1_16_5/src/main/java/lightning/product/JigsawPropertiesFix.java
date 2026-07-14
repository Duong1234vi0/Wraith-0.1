/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import lightning.product.References;
import lightning.product.NamedEntityFix;

public class JigsawPropertiesFix
extends NamedEntityFix {
    public JigsawPropertiesFix(Schema p_i231457_1_, boolean p_i231457_2_) {
        super(p_i231457_1_, p_i231457_2_, "JigsawPropertiesFix", References.u_2550_I, "minecraft:jigsaw");
    }

    private static Dynamic<?> n_1700_B(Dynamic<?> p_233289_0_) {
        String s = p_233289_0_.get("attachement_type").asString("minecraft:empty");
        String s1 = p_233289_0_.get("target_pool").asString("minecraft:empty");
        return p_233289_0_.set("name", p_233289_0_.createString(s)).set("target", p_233289_0_.createString(s)).remove("attachement_type").set("pool", p_233289_0_.createString(s1)).remove("target_pool");
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), JigsawPropertiesFix::n_1700_B);
    }
}


