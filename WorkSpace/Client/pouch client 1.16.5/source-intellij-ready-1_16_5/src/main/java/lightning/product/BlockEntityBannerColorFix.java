/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.Optional;
import lightning.product.References;
import lightning.product.NamedEntityFix;

public class BlockEntityBannerColorFix
extends NamedEntityFix {
    public BlockEntityBannerColorFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "BlockEntityBannerColorFix", References.u_2550_I, "minecraft:banner");
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_209643_1_) {
        p_209643_1_ = p_209643_1_.update("Base", p_209642_0_ -> p_209642_0_.createInt(15 - p_209642_0_.asInt(0)));
        return p_209643_1_.update("Patterns", p_209641_0_ -> (Dynamic)DataFixUtils.orElse((Optional)p_209641_0_.asStreamOpt().map(p_209639_0_ -> p_209639_0_.map(p_210561_0_ -> p_210561_0_.update("Color", p_209644_0_ -> p_209644_0_.createInt(15 - p_209644_0_.asInt(0))))).map(arg_0 -> ((Dynamic)p_209641_0_).createList(arg_0)).result(), (Object)p_209641_0_));
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), this::n_1700_B);
    }
}


