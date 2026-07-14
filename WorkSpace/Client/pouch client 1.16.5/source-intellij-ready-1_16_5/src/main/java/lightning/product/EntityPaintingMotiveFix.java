/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import lightning.product.References;
import lightning.product.g_2336_b;
import lightning.product.NamedEntityFix;

public class EntityPaintingMotiveFix
extends NamedEntityFix {
    private static final Map<String, String> n_1700_B = (Map)DataFixUtils.make((Object)Maps.newHashMap(), p_201153_0_ -> {
        p_201153_0_.put("donkeykong", "donkey_kong");
        p_201153_0_.put("burningskull", "burning_skull");
        p_201153_0_.put("skullandroses", "skull_and_roses");
    });

    public EntityPaintingMotiveFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "EntityPaintingMotiveFix", References.M_182_A, "minecraft:painting");
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_209652_1_) {
        Optional optional = p_209652_1_.get("Motive").asString().result();
        if (optional.isPresent()) {
            String s = ((String)optional.get()).toLowerCase(Locale.ROOT);
            return p_209652_1_.set("Motive", p_209652_1_.createString(new g_2336_b(n_1700_B.getOrDefault(s, s)).toString()));
        }
        return p_209652_1_;
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), this::n_1700_B);
    }
}


