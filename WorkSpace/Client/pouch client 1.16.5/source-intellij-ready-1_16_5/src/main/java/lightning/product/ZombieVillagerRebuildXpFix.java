/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import java.util.Optional;
import lightning.product.References;
import lightning.product.VillagerRebuildLevelAndXpFix;
import lightning.product.NamedEntityFix;

public class ZombieVillagerRebuildXpFix
extends NamedEntityFix {
    public ZombieVillagerRebuildXpFix(Schema p_i51507_1_, boolean p_i51507_2_) {
        super(p_i51507_1_, p_i51507_2_, "Zombie Villager XP rebuild", References.M_182_A, "minecraft:zombie_villager");
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), p_222993_0_ -> {
            Optional optional = p_222993_0_.get("Xp").asNumber().result();
            if (!optional.isPresent()) {
                int i = p_222993_0_.get("VillagerData").get("level").asInt(1);
                return p_222993_0_.set("Xp", p_222993_0_.createInt(VillagerRebuildLevelAndXpFix.n_1700_B(i)));
            }
            return p_222993_0_;
        });
    }
}


