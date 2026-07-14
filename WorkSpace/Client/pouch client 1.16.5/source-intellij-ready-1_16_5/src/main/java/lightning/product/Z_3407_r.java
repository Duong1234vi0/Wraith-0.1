/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.util.Either
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lightning.product.References;

public class Z_3407_r
extends DataFix {
    public Z_3407_r(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        return this.n_1700_B(this.getInputSchema().getTypeRaw(References.M_588_G));
    }

    private <IS> TypeRewriteRule n_1700_B(Type<IS> p_206323_1_) {
        Type type = DSL.and((Type)DSL.optional((Type)DSL.field((String)"Equipment", (Type)DSL.list(p_206323_1_))), (Type)DSL.remainderType());
        Type type1 = DSL.and((Type)DSL.optional((Type)DSL.field((String)"ArmorItems", (Type)DSL.list(p_206323_1_))), (Type)DSL.optional((Type)DSL.field((String)"HandItems", (Type)DSL.list(p_206323_1_))), (Type)DSL.remainderType());
        OpticFinder opticfinder = DSL.typeFinder((Type)type);
        OpticFinder opticfinder1 = DSL.fieldFinder((String)"Equipment", (Type)DSL.list(p_206323_1_));
        return this.fixTypeEverywhereTyped("EntityEquipmentToArmorAndHandFix", this.getInputSchema().getType(References.M_182_A), this.getOutputSchema().getType(References.M_182_A), p_207448_4_ -> {
            Either either = Either.right((Object)DSL.unit());
            Either either1 = Either.right((Object)DSL.unit());
            Dynamic dynamic = (Dynamic)p_207448_4_.getOrCreate(DSL.remainderFinder());
            Optional optional = p_207448_4_.getOptional(opticfinder1);
            if (optional.isPresent()) {
                List list = (List)optional.get();
                Object is = ((Pair)p_206323_1_.read(dynamic.emptyMap()).result().orElseThrow(() -> new IllegalStateException("Could not parse newly created empty itemstack."))).getFirst();
                if (!list.isEmpty()) {
                    either = Either.left((Object)Lists.newArrayList((Object[])new Object[]{list.get(0), is}));
                }
                if (list.size() > 1) {
                    ArrayList list1 = Lists.newArrayList((Object[])new Object[]{is, is, is, is});
                    for (int i = 1; i < Math.min(list.size(), 5); ++i) {
                        list1.set(i - 1, list.get(i));
                    }
                    either1 = Either.left((Object)list1);
                }
            }
            Dynamic dynamic2 = dynamic;
            Optional optional1 = dynamic.get("DropChances").asStreamOpt().result();
            if (optional1.isPresent()) {
                Iterator iterator = Stream.concat((Stream)optional1.get(), Stream.generate(() -> dynamic2.createInt(0))).iterator();
                float f = ((Dynamic)iterator.next()).asFloat(0.0f);
                if (!dynamic.get("HandDropChances").result().isPresent()) {
                    Dynamic dynamic1 = dynamic.createList(Stream.of(Float.valueOf(f), Float.valueOf(0.0f)).map(arg_0 -> ((Dynamic)dynamic).createFloat(arg_0)));
                    dynamic = dynamic.set("HandDropChances", dynamic1);
                }
                if (!dynamic.get("ArmorDropChances").result().isPresent()) {
                    Dynamic dynamic3 = dynamic.createList(Stream.of(Float.valueOf(((Dynamic)iterator.next()).asFloat(0.0f)), Float.valueOf(((Dynamic)iterator.next()).asFloat(0.0f)), Float.valueOf(((Dynamic)iterator.next()).asFloat(0.0f)), Float.valueOf(((Dynamic)iterator.next()).asFloat(0.0f))).map(arg_0 -> ((Dynamic)dynamic).createFloat(arg_0)));
                    dynamic = dynamic.set("ArmorDropChances", dynamic3);
                }
                dynamic = dynamic.remove("DropChances");
            }
            return p_207448_4_.set(opticfinder, type1, (Object)Pair.of((Object)either, (Object)Pair.of((Object)either1, (Object)dynamic)));
        });
    }
}


