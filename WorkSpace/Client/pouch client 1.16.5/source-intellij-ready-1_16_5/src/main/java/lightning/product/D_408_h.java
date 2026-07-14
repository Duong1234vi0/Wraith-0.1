/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Set;
import java.util.function.UnaryOperator;
import javax.annotation.Nullable;
import lightning.product.B_4977_Y;
import lightning.product.MutableComponent;
import lightning.product.I_2011_f;
import lightning.product.LootItemFunctions;
import lightning.product.N_4263_v;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.Z_1993_T;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D_408_h
extends T_3951_H {
    private static final Logger J_1907_R = LogManager.getLogger();
    private final x_282_a R_4764_Y;
    @Nullable
    private final q_1704_m.J_1907_R G_564_y;

    private D_408_h(LootItemCondition[] p_i51218_1_, @Nullable x_282_a p_i51218_2_, @Nullable q_1704_m.J_1907_R p_i51218_3_) {
        super(p_i51218_1_);
        this.R_4764_Y = p_i51218_2_;
        this.G_564_y = p_i51218_3_;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.s_956_w;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return this.G_564_y != null ? ImmutableSet.of(this.G_564_y.n_1700_B()) : ImmutableSet.of();
    }

    public static UnaryOperator<x_282_a> n_1700_B(q_1704_m p_215936_0_, @Nullable q_1704_m.J_1907_R p_215936_1_) {
        N_4263_v entity;
        if (p_215936_1_ != null && (entity = p_215936_0_.J_1907_R(p_215936_1_.n_1700_B())) != null) {
            y_2498_m commandsource = entity.A_3244_K().J_1907_R(2);
            return p_215937_2_ -> {
                try {
                    return ComponentUtils.n_1700_B(commandsource, p_215937_2_, entity, 0);
                }
                catch (CommandSyntaxException commandsyntaxexception) {
                    J_1907_R.warn("Failed to resolve text component", (Throwable)commandsyntaxexception);
                    return p_215937_2_;
                }
            };
        }
        return p_215938_0_ -> p_215938_0_;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        if (this.R_4764_Y != null) {
            stack.n_1700_B((x_282_a)D_408_h.n_1700_B(context, this.G_564_y).apply(this.R_4764_Y));
        }
        return stack;
    }

    public static class n_1700_B
    extends T_3951_H.J_1907_R<D_408_h> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, D_408_h p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            if (p_230424_2_.R_4764_Y != null) {
                p_230424_1_.add("name", x_282_a.n_1700_B.J_1907_R(p_230424_2_.R_4764_Y));
            }
            if (p_230424_2_.G_564_y != null) {
                p_230424_1_.add("entity", p_230424_3_.serialize((Object)p_230424_2_.G_564_y));
            }
        }

        public D_408_h J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            MutableComponent itextcomponent = x_282_a.n_1700_B.n_1700_B(object.get("name"));
            q_1704_m.J_1907_R lootcontext$entitytarget = i_4431_W.n_1700_B(object, "entity", null, deserializationContext, q_1704_m.J_1907_R.class);
            return new D_408_h(conditionsIn, itextcomponent, lootcontext$entitytarget);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


