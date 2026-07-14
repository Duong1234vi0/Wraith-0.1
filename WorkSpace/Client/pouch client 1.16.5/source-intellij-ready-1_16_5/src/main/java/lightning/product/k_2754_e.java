/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.List$ListType
 *  com.mojang.datafixers.types.templates.TaggedChoice$TaggedChoiceType
 *  com.mojang.serialization.Dynamic
 *  it.unimi.dsi.fastutil.ints.IntOpenHashSet
 *  it.unimi.dsi.fastutil.ints.IntSet
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.List;
import com.mojang.datafixers.types.templates.TaggedChoice;
import com.mojang.serialization.Dynamic;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.References;
import lightning.product.c_1839_V;
import lightning.product.h_3335_F;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class k_2754_e
extends DataFix {
    private static final Logger n_1700_B = LogManager.getLogger();

    public k_2754_e(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getOutputSchema().getType(References.R_4764_Y);
        Type type1 = type.findFieldType("Level");
        Type type2 = type1.findFieldType("TileEntities");
        if (!(type2 instanceof List.ListType)) {
            throw new IllegalStateException("Tile entity type is not a list type.");
        }
        List.ListType listtype = (List.ListType)type2;
        OpticFinder opticfinder = DSL.fieldFinder((String)"TileEntities", (Type)listtype);
        Type type3 = this.getInputSchema().getType(References.R_4764_Y);
        OpticFinder opticfinder1 = type3.findField("Level");
        OpticFinder opticfinder2 = opticfinder1.type().findField("Sections");
        Type type4 = opticfinder2.type();
        if (!(type4 instanceof List.ListType)) {
            throw new IllegalStateException("Expecting sections to be a list.");
        }
        Type type5 = ((List.ListType)type4).getElement();
        OpticFinder opticfinder3 = DSL.typeFinder((Type)type5);
        return TypeRewriteRule.seq((TypeRewriteRule)new h_3335_F(this.getOutputSchema(), "AddTrappedChestFix", References.u_2550_I).makeRule(), (TypeRewriteRule)this.fixTypeEverywhereTyped("Trapped Chest fix", type3, p_212533_5_ -> p_212533_5_.updateTyped(opticfinder1, p_212531_4_ -> {
            Optional optional = p_212531_4_.getOptionalTyped(opticfinder2);
            if (!optional.isPresent()) {
                return p_212531_4_;
            }
            List list = ((Typed)optional.get()).getAllTyped(opticfinder3);
            IntOpenHashSet intset = new IntOpenHashSet();
            for (Typed typed : list) {
                n_1700_B trappedchesttileentitysplit$section = new n_1700_B(typed, this.getInputSchema());
                if (trappedchesttileentitysplit$section.J_1907_R()) continue;
                for (int i = 0; i < 4096; ++i) {
                    int j = trappedchesttileentitysplit$section.R_4764_Y(i);
                    if (!trappedchesttileentitysplit$section.n_1700_B(j)) continue;
                    intset.add(trappedchesttileentitysplit$section.R_4764_Y() << 12 | i);
                }
            }
            Dynamic dynamic = (Dynamic)p_212531_4_.get(DSL.remainderFinder());
            int k = dynamic.get("xPos").asInt(0);
            int l = dynamic.get("zPos").asInt(0);
            TaggedChoice.TaggedChoiceType taggedchoicetype = this.getInputSchema().findChoiceType(References.u_2550_I);
            return p_212531_4_.updateTyped(opticfinder, arg_0 -> k_2754_e.n_1700_B(taggedchoicetype, k, l, (IntSet)intset, arg_0));
        })));
    }

    private static /* synthetic */ Typed n_1700_B(TaggedChoice.TaggedChoiceType taggedchoicetype, int k, int l, IntSet intset, Typed p_212532_4_) {
        return p_212532_4_.updateTyped(taggedchoicetype.finder(), p_212530_4_ -> {
            int k1;
            int j1;
            Dynamic dynamic1 = (Dynamic)p_212530_4_.getOrCreate(DSL.remainderFinder());
            int i1 = dynamic1.get("x").asInt(0) - (k << 4);
            return intset.contains(c_1839_V.n_1700_B(i1, j1 = dynamic1.get("y").asInt(0), k1 = dynamic1.get("z").asInt(0) - (l << 4))) ? p_212530_4_.update(taggedchoicetype.finder(), p_212534_0_ -> p_212534_0_.mapFirst(p_212535_0_ -> {
                if (!Objects.equals(p_212535_0_, "minecraft:chest")) {
                    n_1700_B.warn("Block Entity was expected to be a chest");
                }
                return "minecraft:trapped_chest";
            })) : p_212530_4_;
        });
    }

    public static final class n_1700_B
    extends c_1839_V.J_1907_R {
        @Nullable
        private IntSet P_1922_E;

        public n_1700_B(Typed<?> p_i49831_1_, Schema p_i49831_2_) {
            super(p_i49831_1_, p_i49831_2_);
        }

        @Override
        protected boolean n_1700_B() {
            this.P_1922_E = new IntOpenHashSet();
            for (int i = 0; i < this.J_1907_R.size(); ++i) {
                Dynamic dynamic = (Dynamic)this.J_1907_R.get(i);
                String s = dynamic.get("Name").asString("");
                if (!Objects.equals(s, "minecraft:trapped_chest")) continue;
                this.P_1922_E.add(i);
            }
            return this.P_1922_E.isEmpty();
        }

        public boolean n_1700_B(int p_212511_1_) {
            return this.P_1922_E.contains(p_212511_1_);
        }
    }
}


