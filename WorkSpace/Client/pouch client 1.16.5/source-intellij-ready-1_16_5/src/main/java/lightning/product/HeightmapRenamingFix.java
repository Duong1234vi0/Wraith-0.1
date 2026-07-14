/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import java.util.Optional;
import lightning.product.References;

public class HeightmapRenamingFix
extends DataFix {
    public HeightmapRenamingFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.R_4764_Y);
        OpticFinder opticfinder = type.findField("Level");
        return this.fixTypeEverywhereTyped("HeightmapRenamingFix", type, p_207306_2_ -> p_207306_2_.updateTyped(opticfinder, p_207307_1_ -> p_207307_1_.update(DSL.remainderFinder(), this::n_1700_B)));
    }

    private Dynamic<?> n_1700_B(Dynamic<?> p_209766_1_) {
        Optional optional4;
        Optional optional3;
        Optional optional2;
        Optional optional = p_209766_1_.get("Heightmaps").result();
        if (!optional.isPresent()) {
            return p_209766_1_;
        }
        Dynamic dynamic = (Dynamic)optional.get();
        Optional optional1 = dynamic.get("LIQUID").result();
        if (optional1.isPresent()) {
            dynamic = dynamic.remove("LIQUID");
            dynamic = dynamic.set("WORLD_SURFACE_WG", (Dynamic)optional1.get());
        }
        if ((optional2 = dynamic.get("SOLID").result()).isPresent()) {
            dynamic = dynamic.remove("SOLID");
            dynamic = dynamic.set("OCEAN_FLOOR_WG", (Dynamic)optional2.get());
            dynamic = dynamic.set("OCEAN_FLOOR", (Dynamic)optional2.get());
        }
        if ((optional3 = dynamic.get("LIGHT").result()).isPresent()) {
            dynamic = dynamic.remove("LIGHT");
            dynamic = dynamic.set("LIGHT_BLOCKING", (Dynamic)optional3.get());
        }
        if ((optional4 = dynamic.get("RAIN").result()).isPresent()) {
            dynamic = dynamic.remove("RAIN");
            dynamic = dynamic.set("MOTION_BLOCKING", (Dynamic)optional4.get());
            dynamic = dynamic.set("MOTION_BLOCKING_NO_LEAVES", (Dynamic)optional4.get());
        }
        return p_209766_1_.set("Heightmaps", dynamic);
    }
}


