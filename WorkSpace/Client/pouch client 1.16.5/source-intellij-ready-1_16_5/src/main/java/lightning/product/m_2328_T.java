/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Optional;
import lightning.product.References;

public class m_2328_T
extends DataFix {
    private static final Int2ObjectMap<String> n_1700_B = (Int2ObjectMap)DataFixUtils.make((Object)new Int2ObjectOpenHashMap(), p_208043_0_ -> {
        p_208043_0_.put(0, (Object)"minecraft:protection");
        p_208043_0_.put(1, (Object)"minecraft:fire_protection");
        p_208043_0_.put(2, (Object)"minecraft:feather_falling");
        p_208043_0_.put(3, (Object)"minecraft:blast_protection");
        p_208043_0_.put(4, (Object)"minecraft:projectile_protection");
        p_208043_0_.put(5, (Object)"minecraft:respiration");
        p_208043_0_.put(6, (Object)"minecraft:aqua_affinity");
        p_208043_0_.put(7, (Object)"minecraft:thorns");
        p_208043_0_.put(8, (Object)"minecraft:depth_strider");
        p_208043_0_.put(9, (Object)"minecraft:frost_walker");
        p_208043_0_.put(10, (Object)"minecraft:binding_curse");
        p_208043_0_.put(16, (Object)"minecraft:sharpness");
        p_208043_0_.put(17, (Object)"minecraft:smite");
        p_208043_0_.put(18, (Object)"minecraft:bane_of_arthropods");
        p_208043_0_.put(19, (Object)"minecraft:knockback");
        p_208043_0_.put(20, (Object)"minecraft:fire_aspect");
        p_208043_0_.put(21, (Object)"minecraft:looting");
        p_208043_0_.put(22, (Object)"minecraft:sweeping");
        p_208043_0_.put(32, (Object)"minecraft:efficiency");
        p_208043_0_.put(33, (Object)"minecraft:silk_touch");
        p_208043_0_.put(34, (Object)"minecraft:unbreaking");
        p_208043_0_.put(35, (Object)"minecraft:fortune");
        p_208043_0_.put(48, (Object)"minecraft:power");
        p_208043_0_.put(49, (Object)"minecraft:punch");
        p_208043_0_.put(50, (Object)"minecraft:flame");
        p_208043_0_.put(51, (Object)"minecraft:infinity");
        p_208043_0_.put(61, (Object)"minecraft:luck_of_the_sea");
        p_208043_0_.put(62, (Object)"minecraft:lure");
        p_208043_0_.put(65, (Object)"minecraft:loyalty");
        p_208043_0_.put(66, (Object)"minecraft:impaling");
        p_208043_0_.put(67, (Object)"minecraft:riptide");
        p_208043_0_.put(68, (Object)"minecraft:channeling");
        p_208043_0_.put(70, (Object)"minecraft:mending");
        p_208043_0_.put(71, (Object)"minecraft:vanishing_curse");
    });

    public m_2328_T(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.M_588_G);
        OpticFinder opticfinder = type.findField("tag");
        return this.fixTypeEverywhereTyped("ItemStackEnchantmentFix", type, p_208045_2_ -> p_208045_2_.updateTyped(opticfinder, p_208046_1_ -> p_208046_1_.update(DSL.remainderFinder(), this::n_1700_B)));
    }

    private Dynamic<?> n_1700_B(Dynamic<?> p_209627_1_) {
        Optional optional = p_209627_1_.get("ench").asStreamOpt().map(p_209626_0_ -> p_209626_0_.map(p_209628_0_ -> p_209628_0_.set("id", p_209628_0_.createString((String)n_1700_B.getOrDefault(p_209628_0_.get("id").asInt(0), (Object)"null"))))).map(arg_0 -> p_209627_1_.createList(arg_0)).result();
        if (optional.isPresent()) {
            p_209627_1_ = p_209627_1_.remove("ench").set("Enchantments", (Dynamic)optional.get());
        }
        return p_209627_1_.update("StoredEnchantments", p_209625_0_ -> (Dynamic)DataFixUtils.orElse((Optional)p_209625_0_.asStreamOpt().map(p_209623_0_ -> p_209623_0_.map(p_209624_0_ -> p_209624_0_.set("id", p_209624_0_.createString((String)n_1700_B.getOrDefault(p_209624_0_.get("id").asInt(0), (Object)"null"))))).map(arg_0 -> ((Dynamic)p_209625_0_).createList(arg_0)).result(), (Object)p_209625_0_));
    }
}


