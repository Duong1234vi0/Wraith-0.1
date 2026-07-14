/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.OptionalDynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.OptionalDynamic;
import java.util.Arrays;
import java.util.function.Function;
import lightning.product.References;

public class o_3085_e
extends DataFix {
    public o_3085_e(Schema p_i231450_1_) {
        super(p_i231450_1_, false);
    }

    protected TypeRewriteRule makeRule() {
        Schema schema = this.getInputSchema();
        return this.fixTypeEverywhereTyped("EntityProjectileOwner", schema.getType(References.M_182_A), this::n_1700_B);
    }

    private Typed<?> n_1700_B(Typed<?> p_233183_1_) {
        p_233183_1_ = this.n_1700_B(p_233183_1_, "minecraft:egg", this::G_564_y);
        p_233183_1_ = this.n_1700_B(p_233183_1_, "minecraft:ender_pearl", this::G_564_y);
        p_233183_1_ = this.n_1700_B(p_233183_1_, "minecraft:experience_bottle", this::G_564_y);
        p_233183_1_ = this.n_1700_B(p_233183_1_, "minecraft:snowball", this::G_564_y);
        p_233183_1_ = this.n_1700_B(p_233183_1_, "minecraft:potion", this::G_564_y);
        p_233183_1_ = this.n_1700_B(p_233183_1_, "minecraft:potion", this::R_4764_Y);
        p_233183_1_ = this.n_1700_B(p_233183_1_, "minecraft:llama_spit", this::J_1907_R);
        p_233183_1_ = this.n_1700_B(p_233183_1_, "minecraft:arrow", this::n_1700_B);
        p_233183_1_ = this.n_1700_B(p_233183_1_, "minecraft:spectral_arrow", this::n_1700_B);
        return this.n_1700_B(p_233183_1_, "minecraft:trident", this::n_1700_B);
    }

    private Dynamic<?> n_1700_B(Dynamic<?> p_233185_1_) {
        long i = p_233185_1_.get("OwnerUUIDMost").asLong(0L);
        long j = p_233185_1_.get("OwnerUUIDLeast").asLong(0L);
        return this.n_1700_B(p_233185_1_, i, j).remove("OwnerUUIDMost").remove("OwnerUUIDLeast");
    }

    private Dynamic<?> J_1907_R(Dynamic<?> p_233188_1_) {
        OptionalDynamic optionaldynamic = p_233188_1_.get("Owner");
        long i = optionaldynamic.get("OwnerUUIDMost").asLong(0L);
        long j = optionaldynamic.get("OwnerUUIDLeast").asLong(0L);
        return this.n_1700_B(p_233188_1_, i, j).remove("Owner");
    }

    private Dynamic<?> R_4764_Y(Dynamic<?> p_233189_1_) {
        OptionalDynamic optionaldynamic = p_233189_1_.get("Potion");
        return p_233189_1_.set("Item", optionaldynamic.orElseEmptyMap()).remove("Potion");
    }

    private Dynamic<?> G_564_y(Dynamic<?> p_233190_1_) {
        String s = "owner";
        OptionalDynamic optionaldynamic = p_233190_1_.get("owner");
        long i = optionaldynamic.get("M").asLong(0L);
        long j = optionaldynamic.get("L").asLong(0L);
        return this.n_1700_B(p_233190_1_, i, j).remove("owner");
    }

    private Dynamic<?> n_1700_B(Dynamic<?> p_233186_1_, long p_233186_2_, long p_233186_4_) {
        String s = "OwnerUUID";
        return p_233186_2_ != 0L && p_233186_4_ != 0L ? p_233186_1_.set("OwnerUUID", p_233186_1_.createIntList(Arrays.stream(o_3085_e.n_1700_B(p_233186_2_, p_233186_4_)))) : p_233186_1_;
    }

    private static int[] n_1700_B(long p_233182_0_, long p_233182_2_) {
        return new int[]{(int)(p_233182_0_ >> 32), (int)p_233182_0_, (int)(p_233182_2_ >> 32), (int)p_233182_2_};
    }

    private Typed<?> n_1700_B(Typed<?> p_233184_1_, String p_233184_2_, Function<Dynamic<?>, Dynamic<?>> p_233184_3_) {
        Type type = this.getInputSchema().getChoiceType(References.M_182_A, p_233184_2_);
        Type type1 = this.getOutputSchema().getChoiceType(References.M_182_A, p_233184_2_);
        return p_233184_1_.updateTyped(DSL.namedChoice((String)p_233184_2_, (Type)type), type1, p_233187_1_ -> p_233187_1_.update(DSL.remainderFinder(), p_233184_3_));
    }
}


