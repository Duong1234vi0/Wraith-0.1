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

public class V100
extends Schema {
    public V100(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    protected static TypeTemplate n_1700_B(Schema schema) {
        return DSL.optionalFields((String)"ArmorItems", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(schema)), (String)"HandItems", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(schema)));
    }

    protected static void n_1700_B(Schema schema, Map<String, Supplier<TypeTemplate>> map, String name) {
        schema.register(map, name, () -> V100.n_1700_B(schema));
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        Map map = super.registerEntities(p_registerEntities_1_);
        V100.n_1700_B(p_registerEntities_1_, map, "ArmorStand");
        V100.n_1700_B(p_registerEntities_1_, map, "Creeper");
        V100.n_1700_B(p_registerEntities_1_, map, "Skeleton");
        V100.n_1700_B(p_registerEntities_1_, map, "Spider");
        V100.n_1700_B(p_registerEntities_1_, map, "Giant");
        V100.n_1700_B(p_registerEntities_1_, map, "Zombie");
        V100.n_1700_B(p_registerEntities_1_, map, "Slime");
        V100.n_1700_B(p_registerEntities_1_, map, "Ghast");
        V100.n_1700_B(p_registerEntities_1_, map, "PigZombie");
        p_registerEntities_1_.register(map, "Enderman", p_206609_1_ -> DSL.optionalFields((String)"carried", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        V100.n_1700_B(p_registerEntities_1_, map, "CaveSpider");
        V100.n_1700_B(p_registerEntities_1_, map, "Silverfish");
        V100.n_1700_B(p_registerEntities_1_, map, "Blaze");
        V100.n_1700_B(p_registerEntities_1_, map, "LavaSlime");
        V100.n_1700_B(p_registerEntities_1_, map, "EnderDragon");
        V100.n_1700_B(p_registerEntities_1_, map, "WitherBoss");
        V100.n_1700_B(p_registerEntities_1_, map, "Bat");
        V100.n_1700_B(p_registerEntities_1_, map, "Witch");
        V100.n_1700_B(p_registerEntities_1_, map, "Endermite");
        V100.n_1700_B(p_registerEntities_1_, map, "Guardian");
        V100.n_1700_B(p_registerEntities_1_, map, "Pig");
        V100.n_1700_B(p_registerEntities_1_, map, "Sheep");
        V100.n_1700_B(p_registerEntities_1_, map, "Cow");
        V100.n_1700_B(p_registerEntities_1_, map, "Chicken");
        V100.n_1700_B(p_registerEntities_1_, map, "Squid");
        V100.n_1700_B(p_registerEntities_1_, map, "Wolf");
        V100.n_1700_B(p_registerEntities_1_, map, "MushroomCow");
        V100.n_1700_B(p_registerEntities_1_, map, "SnowMan");
        V100.n_1700_B(p_registerEntities_1_, map, "Ozelot");
        V100.n_1700_B(p_registerEntities_1_, map, "VillagerGolem");
        p_registerEntities_1_.register(map, "EntityHorse", p_206612_1_ -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"ArmorItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        V100.n_1700_B(p_registerEntities_1_, map, "Rabbit");
        p_registerEntities_1_.register(map, "Villager", p_206608_1_ -> DSL.optionalFields((String)"Inventory", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"Offers", (TypeTemplate)DSL.optionalFields((String)"Recipes", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"buy", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"buyB", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"sell", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)))), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        V100.n_1700_B(p_registerEntities_1_, map, "Shulker");
        p_registerEntities_1_.registerSimple(map, "AreaEffectCloud");
        p_registerEntities_1_.registerSimple(map, "ShulkerBullet");
        return map;
    }

    public void registerTypes(Schema p_registerTypes_1_, Map<String, Supplier<TypeTemplate>> p_registerTypes_2_, Map<String, Supplier<TypeTemplate>> p_registerTypes_3_) {
        super.registerTypes(p_registerTypes_1_, p_registerTypes_2_, p_registerTypes_3_);
        p_registerTypes_1_.registerType(false, References.u_1723_Y, () -> DSL.optionalFields((String)"entities", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"nbt", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_))), (String)"blocks", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"nbt", (TypeTemplate)References.u_2550_I.in(p_registerTypes_1_))), (String)"palette", (TypeTemplate)DSL.list((TypeTemplate)References.P_4830_p.in(p_registerTypes_1_))));
        p_registerTypes_1_.registerType(false, References.P_4830_p, DSL::remainder);
    }
}


