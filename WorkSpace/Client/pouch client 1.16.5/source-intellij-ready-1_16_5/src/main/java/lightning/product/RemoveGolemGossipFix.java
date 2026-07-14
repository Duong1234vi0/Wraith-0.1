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

public class RemoveGolemGossipFix
extends NamedEntityFix {
    public RemoveGolemGossipFix(Schema p_i241901_1_, boolean p_i241901_2_) {
        super(p_i241901_1_, p_i241901_2_, "Remove Golem Gossip Fix", References.M_182_A, "minecraft:villager");
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), RemoveGolemGossipFix::n_1700_B);
    }

    private static Dynamic<?> n_1700_B(Dynamic<?> p_242266_0_) {
        return p_242266_0_.update("Gossips", p_242267_1_ -> p_242266_0_.createList(p_242267_1_.asStream().filter(p_242268_0_ -> !p_242268_0_.get("Type").asString("").equals("golem"))));
    }
}


