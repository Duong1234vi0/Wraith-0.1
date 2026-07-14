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
import lightning.product.References;

public class EntityPaintingItemFrameDirectionFix
extends DataFix {
    private static final int[][] n_1700_B = new int[][]{{0, 0, 1}, {-1, 0, 0}, {0, 0, -1}, {1, 0, 0}};

    public EntityPaintingItemFrameDirectionFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    private Dynamic<?> n_1700_B(Dynamic<?> p_209748_1_, boolean p_209748_2_, boolean p_209748_3_) {
        if ((p_209748_2_ || p_209748_3_) && !p_209748_1_.get("Facing").asNumber().result().isPresent()) {
            int i;
            if (p_209748_1_.get("Direction").asNumber().result().isPresent()) {
                i = p_209748_1_.get("Direction").asByte((byte)0) % n_1700_B.length;
                int[] aint = n_1700_B[i];
                p_209748_1_ = p_209748_1_.set("TileX", p_209748_1_.createInt(p_209748_1_.get("TileX").asInt(0) + aint[0]));
                p_209748_1_ = p_209748_1_.set("TileY", p_209748_1_.createInt(p_209748_1_.get("TileY").asInt(0) + aint[1]));
                p_209748_1_ = p_209748_1_.set("TileZ", p_209748_1_.createInt(p_209748_1_.get("TileZ").asInt(0) + aint[2]));
                p_209748_1_ = p_209748_1_.remove("Direction");
                if (p_209748_3_ && p_209748_1_.get("ItemRotation").asNumber().result().isPresent()) {
                    p_209748_1_ = p_209748_1_.set("ItemRotation", p_209748_1_.createByte((byte)(p_209748_1_.get("ItemRotation").asByte((byte)0) * 2)));
                }
            } else {
                i = p_209748_1_.get("Dir").asByte((byte)0) % n_1700_B.length;
                p_209748_1_ = p_209748_1_.remove("Dir");
            }
            p_209748_1_ = p_209748_1_.set("Facing", p_209748_1_.createByte((byte)i));
        }
        return p_209748_1_;
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getChoiceType(References.M_182_A, "Painting");
        OpticFinder opticfinder = DSL.namedChoice((String)"Painting", (Type)type);
        Type type1 = this.getInputSchema().getChoiceType(References.M_182_A, "ItemFrame");
        OpticFinder opticfinder1 = DSL.namedChoice((String)"ItemFrame", (Type)type1);
        Type type2 = this.getInputSchema().getType(References.M_182_A);
        TypeRewriteRule typerewriterule = this.fixTypeEverywhereTyped("EntityPaintingFix", type2, p_206332_3_ -> p_206332_3_.updateTyped(opticfinder, type, p_206330_1_ -> p_206330_1_.update(DSL.remainderFinder(), p_207457_1_ -> this.n_1700_B((Dynamic<?>)p_207457_1_, true, false))));
        TypeRewriteRule typerewriterule1 = this.fixTypeEverywhereTyped("EntityItemFrameFix", type2, p_206331_3_ -> p_206331_3_.updateTyped(opticfinder1, type1, p_206329_1_ -> p_206329_1_.update(DSL.remainderFinder(), p_207455_1_ -> this.n_1700_B((Dynamic<?>)p_207455_1_, false, true))));
        return TypeRewriteRule.seq((TypeRewriteRule)typerewriterule, (TypeRewriteRule)typerewriterule1);
    }
}


