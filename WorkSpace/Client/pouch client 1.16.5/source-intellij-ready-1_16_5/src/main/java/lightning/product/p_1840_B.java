/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import lightning.product.A_2178_U;
import lightning.product.LootContextParams;
import lightning.product.B_4977_Y;
import lightning.product.I_2011_f;
import lightning.product.LootItemFunctions;
import lightning.product.K_1178_t;
import lightning.product.N_4263_v;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.U_2912_j;
import lightning.product.Tag;
import lightning.product.Z_1993_T;
import lightning.product.h_2396_v;
import lightning.product.i_2154_H;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;
import lightning.product.q_2896_o;

public class p_1840_B
extends T_3951_H {
    private final P_1922_E J_1907_R;
    private final List<R_4764_Y> R_4764_Y;
    private static final Function<N_4263_v, Tag> G_564_y = h_2396_v::J_1907_R;
    private static final Function<i_2154_H, Tag> P_1922_E = p_215882_0_ -> p_215882_0_.n_1700_B(new U_2912_j());

    private p_1840_B(LootItemCondition[] p_i51240_1_, P_1922_E p_i51240_2_, List<R_4764_Y> p_i51240_3_) {
        super(p_i51240_1_);
        this.J_1907_R = p_i51240_2_;
        this.R_4764_Y = ImmutableList.copyOf(p_i51240_3_);
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.Y_259_p;
    }

    private static K_1178_t.v_4262_N n_1700_B(String p_215880_0_) {
        try {
            return new K_1178_t().n_1700_B(new StringReader(p_215880_0_));
        }
        catch (CommandSyntaxException commandsyntaxexception) {
            throw new IllegalArgumentException("Failed to parse path " + p_215880_0_, commandsyntaxexception);
        }
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(this.J_1907_R.u_1723_Y);
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        Tag inbt = this.J_1907_R.v_4262_N.apply(context);
        if (inbt != null) {
            this.R_4764_Y.forEach(p_215885_2_ -> p_215885_2_.n_1700_B(stack::M_182_A, inbt));
        }
        return stack;
    }

    public static J_1907_R n_1700_B(P_1922_E source) {
        return new J_1907_R(source);
    }

    public static final class P_1922_E
    extends Enum<P_1922_E> {
        public static final /* enum */ P_1922_E n_1700_B = new P_1922_E("this", LootContextParams.n_1700_B, G_564_y);
        public static final /* enum */ P_1922_E J_1907_R = new P_1922_E("killer", LootContextParams.G_564_y, G_564_y);
        public static final /* enum */ P_1922_E R_4764_Y = new P_1922_E("killer_player", LootContextParams.J_1907_R, G_564_y);
        public static final /* enum */ P_1922_E G_564_y = new P_1922_E("block_entity", LootContextParams.w_1484_f, P_1922_E);
        public final String P_1922_E;
        public final I_2011_f<?> u_1723_Y;
        public final Function<q_1704_m, Tag> v_4262_N;
        private static final /* synthetic */ P_1922_E[] w_1484_f;

        public static P_1922_E[] values() {
            return (P_1922_E[])w_1484_f.clone();
        }

        public static P_1922_E valueOf(String name) {
            return Enum.valueOf(P_1922_E.class, name);
        }

        private <T> P_1922_E(String p_i50672_3_, I_2011_f<T> p_i50672_4_, Function<? super T, Tag> p_i50672_5_) {
            this.P_1922_E = p_i50672_3_;
            this.u_1723_Y = p_i50672_4_;
            this.v_4262_N = p_216222_2_ -> {
                Object t = p_216222_2_.J_1907_R(p_i50672_4_);
                return t != null ? (Tag)p_i50672_5_.apply((Object)t) : null;
            };
        }

        public static P_1922_E n_1700_B(String p_216223_0_) {
            for (P_1922_E copynbt$source : lightning.product.p_1840_B$P_1922_E.values()) {
                if (!copynbt$source.P_1922_E.equals(p_216223_0_)) continue;
                return copynbt$source;
            }
            throw new IllegalArgumentException("Invalid tag source " + p_216223_0_);
        }

        private static /* synthetic */ P_1922_E[] n_1700_B() {
            return new P_1922_E[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            w_1484_f = lightning.product.p_1840_B$P_1922_E.n_1700_B();
        }
    }

    public static class J_1907_R
    extends T_3951_H.n_1700_B<J_1907_R> {
        private final P_1922_E n_1700_B;
        private final List<R_4764_Y> J_1907_R = Lists.newArrayList();

        private J_1907_R(P_1922_E p_i50675_1_) {
            this.n_1700_B = p_i50675_1_;
        }

        public J_1907_R n_1700_B(String sourcePath, String targetPath, n_1700_B copyAction) {
            this.J_1907_R.add(new R_4764_Y(sourcePath, targetPath, copyAction));
            return this;
        }

        public J_1907_R n_1700_B(String sourcePath, String targetPath) {
            return this.n_1700_B(sourcePath, targetPath, lightning.product.p_1840_B$n_1700_B.n_1700_B);
        }

        protected J_1907_R P_1922_E() {
            return this;
        }

        @Override
        public A_2178_U u_1723_Y() {
            return new p_1840_B(this.R_4764_Y(), this.n_1700_B, this.J_1907_R);
        }

        @Override
        protected /* synthetic */ T_3951_H.n_1700_B J_1907_R() {
            return this.P_1922_E();
        }
    }

    static class R_4764_Y {
        private final String n_1700_B;
        private final K_1178_t.v_4262_N J_1907_R;
        private final String R_4764_Y;
        private final K_1178_t.v_4262_N G_564_y;
        private final n_1700_B P_1922_E;

        private R_4764_Y(String p_i50673_1_, String p_i50673_2_, n_1700_B p_i50673_3_) {
            this.n_1700_B = p_i50673_1_;
            this.J_1907_R = p_1840_B.n_1700_B(p_i50673_1_);
            this.R_4764_Y = p_i50673_2_;
            this.G_564_y = p_1840_B.n_1700_B(p_i50673_2_);
            this.P_1922_E = p_i50673_3_;
        }

        public void n_1700_B(Supplier<Tag> p_216216_1_, Tag p_216216_2_) {
            try {
                List<Tag> list = this.J_1907_R.n_1700_B(p_216216_2_);
                if (!list.isEmpty()) {
                    this.P_1922_E.n_1700_B(p_216216_1_.get(), this.G_564_y, list);
                }
            }
            catch (CommandSyntaxException commandSyntaxException) {
                // empty catch block
            }
        }

        public JsonObject n_1700_B() {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("source", this.n_1700_B);
            jsonobject.addProperty("target", this.R_4764_Y);
            jsonobject.addProperty("op", this.P_1922_E.G_564_y);
            return jsonobject;
        }

        public static R_4764_Y n_1700_B(JsonObject p_216215_0_) {
            String s = i_4431_W.u_1723_Y(p_216215_0_, "source");
            String s1 = i_4431_W.u_1723_Y(p_216215_0_, "target");
            n_1700_B copynbt$action = lightning.product.p_1840_B$n_1700_B.n_1700_B(i_4431_W.u_1723_Y(p_216215_0_, "op"));
            return new R_4764_Y(s, s1, copynbt$action);
        }
    }

    public static class G_564_y
    extends T_3951_H.J_1907_R<p_1840_B> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, p_1840_B p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.addProperty("source", p_230424_2_.J_1907_R.P_1922_E);
            JsonArray jsonarray = new JsonArray();
            p_230424_2_.R_4764_Y.stream().map(R_4764_Y::n_1700_B).forEach(arg_0 -> ((JsonArray)jsonarray).add(arg_0));
            p_230424_1_.add("ops", (JsonElement)jsonarray);
        }

        public p_1840_B J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            P_1922_E copynbt$source = lightning.product.p_1840_B$P_1922_E.n_1700_B(i_4431_W.u_1723_Y(object, "source"));
            ArrayList list = Lists.newArrayList();
            for (JsonElement jsonelement : i_4431_W.P_4830_p(object, "ops")) {
                JsonObject jsonobject = i_4431_W.w_1484_f(jsonelement, "op");
                list.add(lightning.product.p_1840_B$R_4764_Y.n_1700_B(jsonobject));
            }
            return new p_1840_B(conditionsIn, copynbt$source, list);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }

    public static abstract sealed class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("replace"){

            @Override
            public void n_1700_B(Tag p_216227_1_, K_1178_t.v_4262_N p_216227_2_, List<Tag> p_216227_3_) throws CommandSyntaxException {
                p_216227_2_.J_1907_R(p_216227_1_, ((Tag)Iterables.getLast(p_216227_3_))::R_4764_Y);
            }
        };
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("append"){

            @Override
            public void n_1700_B(Tag p_216227_1_, K_1178_t.v_4262_N p_216227_2_, List<Tag> p_216227_3_) throws CommandSyntaxException {
                List<Tag> list = p_216227_2_.n_1700_B(p_216227_1_, q_2896_o::new);
                list.forEach(p_216232_1_ -> {
                    if (p_216232_1_ instanceof q_2896_o) {
                        p_216227_3_.forEach(p_216231_1_ -> ((q_2896_o)p_216232_1_).add(p_216231_1_.R_4764_Y()));
                    }
                });
            }
        };
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("merge"){

            @Override
            public void n_1700_B(Tag p_216227_1_, K_1178_t.v_4262_N p_216227_2_, List<Tag> p_216227_3_) throws CommandSyntaxException {
                List<Tag> list = p_216227_2_.n_1700_B(p_216227_1_, U_2912_j::new);
                list.forEach(p_216234_1_ -> {
                    if (p_216234_1_ instanceof U_2912_j) {
                        p_216227_3_.forEach(p_216233_1_ -> {
                            if (p_216233_1_ instanceof U_2912_j) {
                                ((U_2912_j)p_216234_1_).n_1700_B((U_2912_j)p_216233_1_);
                            }
                        });
                    }
                });
            }
        };
        private final String G_564_y;
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        public abstract void n_1700_B(Tag var1, K_1178_t.v_4262_N var2, List<Tag> var3) throws CommandSyntaxException;

        private n_1700_B(String p_i50670_3_) {
            this.G_564_y = p_i50670_3_;
        }

        public static n_1700_B n_1700_B(String p_216229_0_) {
            for (n_1700_B copynbt$action : lightning.product.p_1840_B$n_1700_B.values()) {
                if (!copynbt$action.G_564_y.equals(p_216229_0_)) continue;
                return copynbt$action;
            }
            throw new IllegalArgumentException("Invalid merge strategy" + p_216229_0_);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            P_1922_E = lightning.product.p_1840_B$n_1700_B.n_1700_B();
        }
    }
}


