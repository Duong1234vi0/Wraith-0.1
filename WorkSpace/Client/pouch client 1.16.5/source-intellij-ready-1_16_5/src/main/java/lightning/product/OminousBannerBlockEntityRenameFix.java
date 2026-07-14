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
import java.util.Optional;
import lightning.product.References;
import lightning.product.NamedEntityFix;

public class OminousBannerBlockEntityRenameFix
extends NamedEntityFix {
    public OminousBannerBlockEntityRenameFix(Schema p_i51509_1_, boolean p_i51509_2_) {
        super(p_i51509_1_, p_i51509_2_, "OminousBannerBlockEntityRenameFix", References.u_2550_I, "minecraft:banner");
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), this::n_1700_B);
    }

    private Dynamic<?> n_1700_B(Dynamic<?> p_222992_1_) {
        Optional optional = p_222992_1_.get("CustomName").asString().result();
        if (optional.isPresent()) {
            String s = (String)optional.get();
            s = s.replace("\"translate\":\"block.minecraft.illager_banner\"", "\"translate\":\"block.minecraft.ominous_banner\"");
            return p_222992_1_.set("CustomName", p_222992_1_.createString(s));
        }
        return p_222992_1_;
    }
}


