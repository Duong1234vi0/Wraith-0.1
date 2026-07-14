/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.Sets;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.Optional;
import java.util.Set;
import lightning.product.References;

public class EntityHealthFix
extends DataFix {
    private static final Set<String> n_1700_B = Sets.newHashSet((Object[])new String[]{"ArmorStand", "Bat", "Blaze", "CaveSpider", "Chicken", "Cow", "Creeper", "EnderDragon", "Enderman", "Endermite", "EntityHorse", "Ghast", "Giant", "Guardian", "LavaSlime", "MushroomCow", "Ozelot", "Pig", "PigZombie", "Rabbit", "Sheep", "Shulker", "Silverfish", "Skeleton", "Slime", "SnowMan", "Spider", "Squid", "Villager", "VillagerGolem", "Witch", "WitherBoss", "Wolf", "Zombie"});

    public EntityHealthFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_209743_1_) {
        float f;
        Optional optional = p_209743_1_.get("HealF").asNumber().result();
        Optional optional1 = p_209743_1_.get("Health").asNumber().result();
        if (optional.isPresent()) {
            f = ((Number)optional.get()).floatValue();
            p_209743_1_ = p_209743_1_.remove("HealF");
        } else {
            if (!optional1.isPresent()) {
                return p_209743_1_;
            }
            f = ((Number)optional1.get()).floatValue();
        }
        return p_209743_1_.set("Health", p_209743_1_.createFloat(f));
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("EntityHealthFix", this.getInputSchema().getType(References.M_182_A), p_207449_1_ -> p_207449_1_.update(DSL.remainderFinder(), this::n_1700_B));
    }
}


