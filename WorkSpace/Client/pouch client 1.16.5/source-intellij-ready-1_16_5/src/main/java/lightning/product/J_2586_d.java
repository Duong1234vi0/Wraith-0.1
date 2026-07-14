/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lightning.product.A_2629_w;
import lightning.product.B_4088_l;
import lightning.product.C_3304_p;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.V_3164_a;
import lightning.product.V_4217_p;
import lightning.product.i_4556_r;
import lightning.product.ResourceLocationArgument;
import lightning.product.y_2498_m;

public class J_2586_d {
    private static final SuggestionProvider<y_2498_m> n_1700_B = (p_198206_0_, p_198206_1_) -> {
        Collection<A_2629_w> collection = ((y_2498_m)p_198206_0_.getSource()).w_1457_N().RealmsWorldOptions().n_1700_B();
        return V_4217_p.n_1700_B(collection.stream().map(A_2629_w::w_1484_f), p_198206_1_);
    };

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("advancement").requires(p_198205_0_ -> p_198205_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("grant").then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).then(Q_2241_p.n_1700_B("only").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("advancement", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).executes(p_198202_0_ -> J_2586_d.n_1700_B((y_2498_m)p_198202_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198202_0_, "targets"), lightning.product.J_2586_d$n_1700_B.n_1700_B, J_2586_d.n_1700_B(ResourceLocationArgument.n_1700_B((CommandContext<y_2498_m>)p_198202_0_, "advancement"), J_1907_R.n_1700_B)))).then(Q_2241_p.n_1700_B("criterion", StringArgumentType.greedyString()).suggests((p_198209_0_, p_198209_1_) -> V_4217_p.J_1907_R(ResourceLocationArgument.n_1700_B((CommandContext<y_2498_m>)p_198209_0_, "advancement").u_1723_Y().keySet(), p_198209_1_)).executes(p_198212_0_ -> J_2586_d.n_1700_B((y_2498_m)p_198212_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198212_0_, "targets"), lightning.product.J_2586_d$n_1700_B.n_1700_B, ResourceLocationArgument.n_1700_B((CommandContext<y_2498_m>)p_198212_0_, "advancement"), StringArgumentType.getString((CommandContext)p_198212_0_, (String)"criterion"))))))).then(Q_2241_p.n_1700_B("from").then(Q_2241_p.n_1700_B("advancement", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).executes(p_198215_0_ -> J_2586_d.n_1700_B((y_2498_m)p_198215_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198215_0_, "targets"), lightning.product.J_2586_d$n_1700_B.n_1700_B, J_2586_d.n_1700_B(ResourceLocationArgument.n_1700_B((CommandContext<y_2498_m>)p_198215_0_, "advancement"), J_1907_R.R_4764_Y)))))).then(Q_2241_p.n_1700_B("until").then(Q_2241_p.n_1700_B("advancement", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).executes(p_198204_0_ -> J_2586_d.n_1700_B((y_2498_m)p_198204_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198204_0_, "targets"), lightning.product.J_2586_d$n_1700_B.n_1700_B, J_2586_d.n_1700_B(ResourceLocationArgument.n_1700_B((CommandContext<y_2498_m>)p_198204_0_, "advancement"), J_1907_R.G_564_y)))))).then(Q_2241_p.n_1700_B("through").then(Q_2241_p.n_1700_B("advancement", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).executes(p_198211_0_ -> J_2586_d.n_1700_B((y_2498_m)p_198211_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198211_0_, "targets"), lightning.product.J_2586_d$n_1700_B.n_1700_B, J_2586_d.n_1700_B(ResourceLocationArgument.n_1700_B((CommandContext<y_2498_m>)p_198211_0_, "advancement"), J_1907_R.J_1907_R)))))).then(Q_2241_p.n_1700_B("everything").executes(p_198217_0_ -> J_2586_d.n_1700_B((y_2498_m)p_198217_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198217_0_, "targets"), lightning.product.J_2586_d$n_1700_B.n_1700_B, ((y_2498_m)p_198217_0_.getSource()).w_1457_N().RealmsWorldOptions().n_1700_B())))))).then(Q_2241_p.n_1700_B("revoke").then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).then(Q_2241_p.n_1700_B("only").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("advancement", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).executes(p_198198_0_ -> J_2586_d.n_1700_B((y_2498_m)p_198198_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198198_0_, "targets"), lightning.product.J_2586_d$n_1700_B.J_1907_R, J_2586_d.n_1700_B(ResourceLocationArgument.n_1700_B((CommandContext<y_2498_m>)p_198198_0_, "advancement"), J_1907_R.n_1700_B)))).then(Q_2241_p.n_1700_B("criterion", StringArgumentType.greedyString()).suggests((p_198210_0_, p_198210_1_) -> V_4217_p.J_1907_R(ResourceLocationArgument.n_1700_B((CommandContext<y_2498_m>)p_198210_0_, "advancement").u_1723_Y().keySet(), p_198210_1_)).executes(p_198200_0_ -> J_2586_d.n_1700_B((y_2498_m)p_198200_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198200_0_, "targets"), lightning.product.J_2586_d$n_1700_B.J_1907_R, ResourceLocationArgument.n_1700_B((CommandContext<y_2498_m>)p_198200_0_, "advancement"), StringArgumentType.getString((CommandContext)p_198200_0_, (String)"criterion"))))))).then(Q_2241_p.n_1700_B("from").then(Q_2241_p.n_1700_B("advancement", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).executes(p_198208_0_ -> J_2586_d.n_1700_B((y_2498_m)p_198208_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198208_0_, "targets"), lightning.product.J_2586_d$n_1700_B.J_1907_R, J_2586_d.n_1700_B(ResourceLocationArgument.n_1700_B((CommandContext<y_2498_m>)p_198208_0_, "advancement"), J_1907_R.R_4764_Y)))))).then(Q_2241_p.n_1700_B("until").then(Q_2241_p.n_1700_B("advancement", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).executes(p_198201_0_ -> J_2586_d.n_1700_B((y_2498_m)p_198201_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198201_0_, "targets"), lightning.product.J_2586_d$n_1700_B.J_1907_R, J_2586_d.n_1700_B(ResourceLocationArgument.n_1700_B((CommandContext<y_2498_m>)p_198201_0_, "advancement"), J_1907_R.G_564_y)))))).then(Q_2241_p.n_1700_B("through").then(Q_2241_p.n_1700_B("advancement", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).executes(p_198197_0_ -> J_2586_d.n_1700_B((y_2498_m)p_198197_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198197_0_, "targets"), lightning.product.J_2586_d$n_1700_B.J_1907_R, J_2586_d.n_1700_B(ResourceLocationArgument.n_1700_B((CommandContext<y_2498_m>)p_198197_0_, "advancement"), J_1907_R.J_1907_R)))))).then(Q_2241_p.n_1700_B("everything").executes(p_198213_0_ -> J_2586_d.n_1700_B((y_2498_m)p_198213_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198213_0_, "targets"), lightning.product.J_2586_d$n_1700_B.J_1907_R, ((y_2498_m)p_198213_0_.getSource()).w_1457_N().RealmsWorldOptions().n_1700_B()))))));
    }

    private static int n_1700_B(y_2498_m source, Collection<B_4088_l> targets, n_1700_B action, Collection<A_2629_w> advancements) {
        int i = 0;
        for (B_4088_l serverplayerentity : targets) {
            i += action.n_1700_B(serverplayerentity, advancements);
        }
        if (i == 0) {
            if (advancements.size() == 1) {
                if (targets.size() == 1) {
                    throw new V_3164_a(new F_2904_S(action.n_1700_B() + ".one.to.one.failure", advancements.iterator().next().s_956_w(), targets.iterator().next().c_()));
                }
                throw new V_3164_a(new F_2904_S(action.n_1700_B() + ".one.to.many.failure", advancements.iterator().next().s_956_w(), targets.size()));
            }
            if (targets.size() == 1) {
                throw new V_3164_a(new F_2904_S(action.n_1700_B() + ".many.to.one.failure", advancements.size(), targets.iterator().next().c_()));
            }
            throw new V_3164_a(new F_2904_S(action.n_1700_B() + ".many.to.many.failure", advancements.size(), targets.size()));
        }
        if (advancements.size() == 1) {
            if (targets.size() == 1) {
                source.n_1700_B(new F_2904_S(action.n_1700_B() + ".one.to.one.success", advancements.iterator().next().s_956_w(), targets.iterator().next().c_()), true);
            } else {
                source.n_1700_B(new F_2904_S(action.n_1700_B() + ".one.to.many.success", advancements.iterator().next().s_956_w(), targets.size()), true);
            }
        } else if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S(action.n_1700_B() + ".many.to.one.success", advancements.size(), targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S(action.n_1700_B() + ".many.to.many.success", advancements.size(), targets.size()), true);
        }
        return i;
    }

    private static int n_1700_B(y_2498_m source, Collection<B_4088_l> targets, n_1700_B action, A_2629_w advancementIn, String criterionName) {
        int i = 0;
        if (!advancementIn.u_1723_Y().containsKey(criterionName)) {
            throw new V_3164_a(new F_2904_S("commands.advancement.criterionNotFound", advancementIn.s_956_w(), criterionName));
        }
        for (B_4088_l serverplayerentity : targets) {
            if (!action.n_1700_B(serverplayerentity, advancementIn, criterionName)) continue;
            ++i;
        }
        if (i == 0) {
            if (targets.size() == 1) {
                throw new V_3164_a(new F_2904_S(action.n_1700_B() + ".criterion.to.one.failure", criterionName, advancementIn.s_956_w(), targets.iterator().next().c_()));
            }
            throw new V_3164_a(new F_2904_S(action.n_1700_B() + ".criterion.to.many.failure", criterionName, advancementIn.s_956_w(), targets.size()));
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S(action.n_1700_B() + ".criterion.to.one.success", criterionName, advancementIn.s_956_w(), targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S(action.n_1700_B() + ".criterion.to.many.success", criterionName, advancementIn.s_956_w(), targets.size()), true);
        }
        return i;
    }

    private static List<A_2629_w> n_1700_B(A_2629_w advancementIn, J_1907_R mode) {
        ArrayList list = Lists.newArrayList();
        if (mode.u_1723_Y) {
            for (A_2629_w advancement = advancementIn.J_1907_R(); advancement != null; advancement = advancement.J_1907_R()) {
                list.add(advancement);
            }
        }
        list.add(advancementIn);
        if (mode.v_4262_N) {
            J_2586_d.n_1700_B(advancementIn, list);
        }
        return list;
    }

    private static void n_1700_B(A_2629_w advancementIn, List<A_2629_w> list) {
        for (A_2629_w advancement : advancementIn.P_1922_E()) {
            list.add(advancement);
            J_2586_d.n_1700_B(advancement, list);
        }
    }

    static abstract sealed class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("grant"){

            @Override
            protected boolean n_1700_B(B_4088_l player, A_2629_w advancementIn) {
                C_3304_p advancementprogress = player.g_164_R().J_1907_R(advancementIn);
                if (advancementprogress.n_1700_B()) {
                    return false;
                }
                for (String s : advancementprogress.P_1922_E()) {
                    player.g_164_R().n_1700_B(advancementIn, s);
                }
                return true;
            }

            @Override
            protected boolean n_1700_B(B_4088_l player, A_2629_w advancementIn, String criterionName) {
                return player.g_164_R().n_1700_B(advancementIn, criterionName);
            }
        };
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("revoke"){

            @Override
            protected boolean n_1700_B(B_4088_l player, A_2629_w advancementIn) {
                C_3304_p advancementprogress = player.g_164_R().J_1907_R(advancementIn);
                if (!advancementprogress.J_1907_R()) {
                    return false;
                }
                for (String s : advancementprogress.u_1723_Y()) {
                    player.g_164_R().J_1907_R(advancementIn, s);
                }
                return true;
            }

            @Override
            protected boolean n_1700_B(B_4088_l player, A_2629_w advancementIn, String criterionName) {
                return player.g_164_R().J_1907_R(advancementIn, criterionName);
            }
        };
        private final String R_4764_Y;
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String name) {
            this.R_4764_Y = "commands.advancement." + name;
        }

        public int n_1700_B(B_4088_l player, Iterable<A_2629_w> advancements) {
            int i = 0;
            for (A_2629_w advancement : advancements) {
                if (!this.n_1700_B(player, advancement)) continue;
                ++i;
            }
            return i;
        }

        protected abstract boolean n_1700_B(B_4088_l var1, A_2629_w var2);

        protected abstract boolean n_1700_B(B_4088_l var1, A_2629_w var2, String var3);

        protected String n_1700_B() {
            return this.R_4764_Y;
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            G_564_y = lightning.product.J_2586_d$n_1700_B.J_1907_R();
        }
    }

    static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(false, false);
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(true, true);
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R(false, true);
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R(true, false);
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R(true, true);
        private final boolean u_1723_Y;
        private final boolean v_4262_N;
        private static final /* synthetic */ J_1907_R[] w_1484_f;

        public static J_1907_R[] values() {
            return (J_1907_R[])w_1484_f.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(boolean includesParentsIn, boolean includesChildrenIn) {
            this.u_1723_Y = includesParentsIn;
            this.v_4262_N = includesChildrenIn;
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            w_1484_f = lightning.product.J_2586_d$J_1907_R.n_1700_B();
        }
    }
}


