/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.Sets;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.Optional;
import java.util.Set;
import lightning.product.References;
import lightning.product.z_2197_Y;

public class a_1330_H
extends z_2197_Y {
    private static final Set<String> R_4764_Y = Sets.newHashSet();
    private static final Set<String> G_564_y = Sets.newHashSet();
    private static final Set<String> P_1922_E = Sets.newHashSet();
    private static final Set<String> u_1723_Y = Sets.newHashSet();
    private static final Set<String> v_4262_N = Sets.newHashSet();
    private static final Set<String> w_1484_f = Sets.newHashSet();

    public a_1330_H(Schema p_i231452_1_) {
        super(p_i231452_1_, References.M_182_A);
    }

    protected TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("EntityUUIDFixes", this.getInputSchema().getType(this.J_1907_R), p_233210_1_ -> {
            p_233210_1_ = p_233210_1_.update(DSL.remainderFinder(), a_1330_H::R_4764_Y);
            for (String s : R_4764_Y) {
                p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, s, a_1330_H::M_588_G);
            }
            for (String s1 : G_564_y) {
                p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, s1, a_1330_H::M_588_G);
            }
            for (String s2 : P_1922_E) {
                p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, s2, a_1330_H::P_4830_p);
            }
            for (String s3 : u_1723_Y) {
                p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, s3, a_1330_H::h_1847_R);
            }
            for (String s4 : v_4262_N) {
                p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, s4, a_1330_H::J_1907_R);
            }
            for (String s5 : w_1484_f) {
                p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, s5, a_1330_H::Q_4569_t);
            }
            p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, "minecraft:bee", a_1330_H::u_2550_I);
            p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, "minecraft:zombified_piglin", a_1330_H::u_2550_I);
            p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, "minecraft:fox", a_1330_H::s_956_w);
            p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, "minecraft:item", a_1330_H::t_148_a);
            p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, "minecraft:shulker_bullet", a_1330_H::w_1484_f);
            p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, "minecraft:area_effect_cloud", a_1330_H::v_4262_N);
            p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, "minecraft:zombie_villager", a_1330_H::u_1723_Y);
            p_233210_1_ = this.n_1700_B((Typed<?>)p_233210_1_, "minecraft:evoker_fangs", a_1330_H::P_1922_E);
            return this.n_1700_B((Typed<?>)p_233210_1_, "minecraft:piglin", a_1330_H::G_564_y);
        });
    }

    private static Dynamic<?> G_564_y(Dynamic<?> p_233216_0_) {
        return p_233216_0_.update("Brain", p_233235_0_ -> p_233235_0_.update("memories", p_233236_0_ -> p_233236_0_.update("minecraft:angry_at", p_233237_0_ -> a_1330_H.n_1700_B(p_233237_0_, "value", "value").orElseGet(() -> {
            n_1700_B.warn("angry_at has no value.");
            return p_233237_0_;
        }))));
    }

    private static Dynamic<?> P_1922_E(Dynamic<?> p_233218_0_) {
        return a_1330_H.R_4764_Y(p_233218_0_, "OwnerUUID", "Owner").orElse(p_233218_0_);
    }

    private static Dynamic<?> u_1723_Y(Dynamic<?> p_233220_0_) {
        return a_1330_H.R_4764_Y(p_233220_0_, "ConversionPlayer", "ConversionPlayer").orElse(p_233220_0_);
    }

    private static Dynamic<?> v_4262_N(Dynamic<?> p_233221_0_) {
        return a_1330_H.R_4764_Y(p_233221_0_, "OwnerUUID", "Owner").orElse(p_233221_0_);
    }

    private static Dynamic<?> w_1484_f(Dynamic<?> p_233222_0_) {
        p_233222_0_ = a_1330_H.J_1907_R(p_233222_0_, "Owner", "Owner").orElse(p_233222_0_);
        return a_1330_H.J_1907_R(p_233222_0_, "Target", "Target").orElse(p_233222_0_);
    }

    private static Dynamic<?> t_148_a(Dynamic<?> p_233223_0_) {
        p_233223_0_ = a_1330_H.J_1907_R(p_233223_0_, "Owner", "Owner").orElse(p_233223_0_);
        return a_1330_H.J_1907_R(p_233223_0_, "Thrower", "Thrower").orElse(p_233223_0_);
    }

    private static Dynamic<?> s_956_w(Dynamic<?> p_233224_0_) {
        Optional<Dynamic> optional = p_233224_0_.get("TrustedUUIDs").result().map(p_233219_1_ -> p_233224_0_.createList(p_233219_1_.asStream().map(p_233233_0_ -> a_1330_H.n_1700_B(p_233233_0_).orElseGet(() -> {
            n_1700_B.warn("Trusted contained invalid data.");
            return p_233233_0_;
        }))));
        return (Dynamic)DataFixUtils.orElse(optional.map(p_233217_1_ -> p_233224_0_.remove("TrustedUUIDs").set("Trusted", p_233217_1_)), p_233224_0_);
    }

    private static Dynamic<?> u_2550_I(Dynamic<?> p_233225_0_) {
        return a_1330_H.n_1700_B(p_233225_0_, "HurtBy", "HurtBy").orElse(p_233225_0_);
    }

    private static Dynamic<?> M_588_G(Dynamic<?> p_233226_0_) {
        Dynamic<?> dynamic = a_1330_H.P_4830_p(p_233226_0_);
        return a_1330_H.n_1700_B(dynamic, "OwnerUUID", "Owner").orElse(dynamic);
    }

    private static Dynamic<?> P_4830_p(Dynamic<?> p_233227_0_) {
        Dynamic<?> dynamic = a_1330_H.h_1847_R(p_233227_0_);
        return a_1330_H.R_4764_Y(dynamic, "LoveCause", "LoveCause").orElse(dynamic);
    }

    private static Dynamic<?> h_1847_R(Dynamic<?> p_233228_0_) {
        return a_1330_H.J_1907_R(p_233228_0_).update("Leash", p_233232_0_ -> a_1330_H.R_4764_Y(p_233232_0_, "UUID", "UUID").orElse((Dynamic<?>)p_233232_0_));
    }

    public static Dynamic<?> J_1907_R(Dynamic<?> p_233212_0_) {
        return p_233212_0_.update("Attributes", p_233213_1_ -> p_233212_0_.createList(p_233213_1_.asStream().map(p_233230_0_ -> p_233230_0_.update("Modifiers", p_233215_1_ -> p_233230_0_.createList(p_233215_1_.asStream().map(p_233231_0_ -> a_1330_H.R_4764_Y(p_233231_0_, "UUID", "UUID").orElse((Dynamic<?>)p_233231_0_)))))));
    }

    private static Dynamic<?> Q_4569_t(Dynamic<?> p_233229_0_) {
        return (Dynamic)DataFixUtils.orElse(p_233229_0_.get("OwnerUUID").result().map(p_233211_1_ -> p_233229_0_.remove("OwnerUUID").set("Owner", p_233211_1_)), p_233229_0_);
    }

    public static Dynamic<?> R_4764_Y(Dynamic<?> p_233214_0_) {
        return a_1330_H.R_4764_Y(p_233214_0_, "UUID", "UUID").orElse(p_233214_0_);
    }

    static {
        R_4764_Y.add("minecraft:donkey");
        R_4764_Y.add("minecraft:horse");
        R_4764_Y.add("minecraft:llama");
        R_4764_Y.add("minecraft:mule");
        R_4764_Y.add("minecraft:skeleton_horse");
        R_4764_Y.add("minecraft:trader_llama");
        R_4764_Y.add("minecraft:zombie_horse");
        G_564_y.add("minecraft:cat");
        G_564_y.add("minecraft:parrot");
        G_564_y.add("minecraft:wolf");
        P_1922_E.add("minecraft:bee");
        P_1922_E.add("minecraft:chicken");
        P_1922_E.add("minecraft:cow");
        P_1922_E.add("minecraft:fox");
        P_1922_E.add("minecraft:mooshroom");
        P_1922_E.add("minecraft:ocelot");
        P_1922_E.add("minecraft:panda");
        P_1922_E.add("minecraft:pig");
        P_1922_E.add("minecraft:polar_bear");
        P_1922_E.add("minecraft:rabbit");
        P_1922_E.add("minecraft:sheep");
        P_1922_E.add("minecraft:turtle");
        P_1922_E.add("minecraft:hoglin");
        u_1723_Y.add("minecraft:bat");
        u_1723_Y.add("minecraft:blaze");
        u_1723_Y.add("minecraft:cave_spider");
        u_1723_Y.add("minecraft:cod");
        u_1723_Y.add("minecraft:creeper");
        u_1723_Y.add("minecraft:dolphin");
        u_1723_Y.add("minecraft:drowned");
        u_1723_Y.add("minecraft:elder_guardian");
        u_1723_Y.add("minecraft:ender_dragon");
        u_1723_Y.add("minecraft:enderman");
        u_1723_Y.add("minecraft:endermite");
        u_1723_Y.add("minecraft:evoker");
        u_1723_Y.add("minecraft:ghast");
        u_1723_Y.add("minecraft:giant");
        u_1723_Y.add("minecraft:guardian");
        u_1723_Y.add("minecraft:husk");
        u_1723_Y.add("minecraft:illusioner");
        u_1723_Y.add("minecraft:magma_cube");
        u_1723_Y.add("minecraft:pufferfish");
        u_1723_Y.add("minecraft:zombified_piglin");
        u_1723_Y.add("minecraft:salmon");
        u_1723_Y.add("minecraft:shulker");
        u_1723_Y.add("minecraft:silverfish");
        u_1723_Y.add("minecraft:skeleton");
        u_1723_Y.add("minecraft:slime");
        u_1723_Y.add("minecraft:snow_golem");
        u_1723_Y.add("minecraft:spider");
        u_1723_Y.add("minecraft:squid");
        u_1723_Y.add("minecraft:stray");
        u_1723_Y.add("minecraft:tropical_fish");
        u_1723_Y.add("minecraft:vex");
        u_1723_Y.add("minecraft:villager");
        u_1723_Y.add("minecraft:iron_golem");
        u_1723_Y.add("minecraft:vindicator");
        u_1723_Y.add("minecraft:pillager");
        u_1723_Y.add("minecraft:wandering_trader");
        u_1723_Y.add("minecraft:witch");
        u_1723_Y.add("minecraft:wither");
        u_1723_Y.add("minecraft:wither_skeleton");
        u_1723_Y.add("minecraft:zombie");
        u_1723_Y.add("minecraft:zombie_villager");
        u_1723_Y.add("minecraft:phantom");
        u_1723_Y.add("minecraft:ravager");
        u_1723_Y.add("minecraft:piglin");
        v_4262_N.add("minecraft:armor_stand");
        w_1484_f.add("minecraft:arrow");
        w_1484_f.add("minecraft:dragon_fireball");
        w_1484_f.add("minecraft:firework_rocket");
        w_1484_f.add("minecraft:fireball");
        w_1484_f.add("minecraft:llama_spit");
        w_1484_f.add("minecraft:small_fireball");
        w_1484_f.add("minecraft:snowball");
        w_1484_f.add("minecraft:spectral_arrow");
        w_1484_f.add("minecraft:egg");
        w_1484_f.add("minecraft:ender_pearl");
        w_1484_f.add("minecraft:experience_bottle");
        w_1484_f.add("minecraft:potion");
        w_1484_f.add("minecraft:trident");
        w_1484_f.add("minecraft:wither_skull");
    }
}


