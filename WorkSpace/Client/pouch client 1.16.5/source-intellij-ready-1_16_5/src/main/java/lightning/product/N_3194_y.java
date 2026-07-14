/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.Map;
import lightning.product.References;
import lightning.product.NamedEntityFix;

public class N_3194_y
extends NamedEntityFix {
    public N_3194_y(Schema p_i231460_1_, String p_i231460_2_) {
        super(p_i231460_1_, false, "Memory expiry data fix (" + p_i231460_2_ + ")", References.M_182_A, p_i231460_2_);
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), this::n_1700_B);
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_233326_1_) {
        return p_233326_1_.update("Brain", this::J_1907_R);
    }

    private Dynamic<?> J_1907_R(Dynamic<?> p_233327_1_) {
        return p_233327_1_.update("memories", this::R_4764_Y);
    }

    private Dynamic<?> R_4764_Y(Dynamic<?> p_233328_1_) {
        return p_233328_1_.updateMapValues(this::n_1700_B);
    }

    private Pair<Dynamic<?>, Dynamic<?>> n_1700_B(Pair<Dynamic<?>, Dynamic<?>> p_233325_1_) {
        return p_233325_1_.mapSecond(this::G_564_y);
    }

    private Dynamic<?> G_564_y(Dynamic<?> p_233329_1_) {
        return p_233329_1_.createMap((Map)ImmutableMap.of((Object)p_233329_1_.createString("value"), p_233329_1_));
    }
}


