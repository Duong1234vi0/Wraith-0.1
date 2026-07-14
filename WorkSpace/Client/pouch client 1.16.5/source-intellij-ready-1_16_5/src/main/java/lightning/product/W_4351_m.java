/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import lightning.product.A_2629_w;
import lightning.product.LootContextParams;
import lightning.product.B_3068_A;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.I_14_v;
import lightning.product.J_2545_z;
import lightning.product.LootItemCondition;
import lightning.product.S_4998_h;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.Objective;
import lightning.product.Z_1993_T;
import lightning.product.e_3591_l;
import lightning.product.f_1402_I;
import lightning.product.g_2336_b;
import lightning.product.ServerScoreboard;
import lightning.product.ServerAdvancementManager;
import lightning.product.n_3832_I;
import lightning.product.o_3050_h;
import lightning.product.EntityTypeTags;
import lightning.product.q_1704_m;
import lightning.product.r_4318_c;
import lightning.product.r_4811_B;
import lightning.product.MinMaxBounds;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.v_4839_y;
import lightning.product.x_282_a;
import lightning.product.WrappedMinMaxBounds;

public class W_4351_m {
    private static final Map<String, J_1907_R> t_148_a = Maps.newHashMap();
    public static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(p_208752_0_ -> new F_2904_S("argument.entity.options.unknown", p_208752_0_));
    public static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208726_0_ -> new F_2904_S("argument.entity.options.inapplicable", p_208726_0_));
    public static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("argument.entity.options.distance.negative"));
    public static final SimpleCommandExceptionType G_564_y = new SimpleCommandExceptionType((Message)new F_2904_S("argument.entity.options.level.negative"));
    public static final SimpleCommandExceptionType P_1922_E = new SimpleCommandExceptionType((Message)new F_2904_S("argument.entity.options.limit.toosmall"));
    public static final DynamicCommandExceptionType u_1723_Y = new DynamicCommandExceptionType(p_208749_0_ -> new F_2904_S("argument.entity.options.sort.irreversible", p_208749_0_));
    public static final DynamicCommandExceptionType v_4262_N = new DynamicCommandExceptionType(p_208740_0_ -> new F_2904_S("argument.entity.options.mode.invalid", p_208740_0_));
    public static final DynamicCommandExceptionType w_1484_f = new DynamicCommandExceptionType(p_208758_0_ -> new F_2904_S("argument.entity.options.type.invalid", p_208758_0_));

    private static void n_1700_B(String id, n_1700_B handler, Predicate<J_2545_z> p_202024_2_, x_282_a tooltip) {
        t_148_a.put(id, new J_1907_R(handler, p_202024_2_, tooltip));
    }

    public static void n_1700_B() {
        if (t_148_a.isEmpty()) {
            W_4351_m.n_1700_B("name", p_197440_0_ -> {
                int i = p_197440_0_.v_4262_N().getCursor();
                boolean flag = p_197440_0_.P_1922_E();
                String s = p_197440_0_.v_4262_N().readString();
                if (p_197440_0_.Q_2552_b() && !flag) {
                    p_197440_0_.v_4262_N().setCursor(i);
                    throw J_1907_R.createWithContext((ImmutableStringReader)p_197440_0_.v_4262_N(), (Object)"name");
                }
                if (flag) {
                    p_197440_0_.R_4764_Y(true);
                } else {
                    p_197440_0_.J_1907_R(true);
                }
                p_197440_0_.n_1700_B(p_197446_2_ -> p_197446_2_.O_1309_Q().getString().equals(s) != flag);
            }, p_202016_0_ -> !p_202016_0_.Y_259_p(), new F_2904_S("argument.entity.options.name.description"));
            W_4351_m.n_1700_B("distance", p_197439_0_ -> {
                int i = p_197439_0_.v_4262_N().getCursor();
                MinMaxBounds.n_1700_B minmaxbounds$floatbound = MinMaxBounds.n_1700_B.n_1700_B(p_197439_0_.v_4262_N());
                if (minmaxbounds$floatbound.n_1700_B() != null && ((Float)minmaxbounds$floatbound.n_1700_B()).floatValue() < 0.0f || minmaxbounds$floatbound.J_1907_R() != null && ((Float)minmaxbounds$floatbound.J_1907_R()).floatValue() < 0.0f) {
                    p_197439_0_.v_4262_N().setCursor(i);
                    throw R_4764_Y.createWithContext((ImmutableStringReader)p_197439_0_.v_4262_N());
                }
                p_197439_0_.n_1700_B(minmaxbounds$floatbound);
                p_197439_0_.w_1484_f();
            }, p_202020_0_ -> p_202020_0_.t_148_a().R_4764_Y(), new F_2904_S("argument.entity.options.distance.description"));
            W_4351_m.n_1700_B("level", p_197438_0_ -> {
                int i = p_197438_0_.v_4262_N().getCursor();
                MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(p_197438_0_.v_4262_N());
                if (minmaxbounds$intbound.n_1700_B() != null && (Integer)minmaxbounds$intbound.n_1700_B() < 0 || minmaxbounds$intbound.J_1907_R() != null && (Integer)minmaxbounds$intbound.J_1907_R() < 0) {
                    p_197438_0_.v_4262_N().setCursor(i);
                    throw G_564_y.createWithContext((ImmutableStringReader)p_197438_0_.v_4262_N());
                }
                p_197438_0_.n_1700_B(minmaxbounds$intbound);
                p_197438_0_.n_1700_B(false);
            }, p_202019_0_ -> p_202019_0_.s_956_w().R_4764_Y(), new F_2904_S("argument.entity.options.level.description"));
            W_4351_m.n_1700_B("x", p_197437_0_ -> {
                p_197437_0_.w_1484_f();
                p_197437_0_.n_1700_B(p_197437_0_.v_4262_N().readDouble());
            }, p_202022_0_ -> p_202022_0_.P_4830_p() == null, new F_2904_S("argument.entity.options.x.description"));
            W_4351_m.n_1700_B("y", p_197442_0_ -> {
                p_197442_0_.w_1484_f();
                p_197442_0_.J_1907_R(p_197442_0_.v_4262_N().readDouble());
            }, p_202021_0_ -> p_202021_0_.h_1847_R() == null, new F_2904_S("argument.entity.options.y.description"));
            W_4351_m.n_1700_B("z", p_197464_0_ -> {
                p_197464_0_.w_1484_f();
                p_197464_0_.R_4764_Y(p_197464_0_.v_4262_N().readDouble());
            }, p_202029_0_ -> p_202029_0_.Q_4569_t() == null, new F_2904_S("argument.entity.options.z.description"));
            W_4351_m.n_1700_B("dx", p_197460_0_ -> {
                p_197460_0_.w_1484_f();
                p_197460_0_.G_564_y(p_197460_0_.v_4262_N().readDouble());
            }, p_202027_0_ -> p_202027_0_.M_182_A() == null, new F_2904_S("argument.entity.options.dx.description"));
            W_4351_m.n_1700_B("dy", p_197463_0_ -> {
                p_197463_0_.w_1484_f();
                p_197463_0_.P_1922_E(p_197463_0_.v_4262_N().readDouble());
            }, p_202026_0_ -> p_202026_0_.t_1786_h() == null, new F_2904_S("argument.entity.options.dy.description"));
            W_4351_m.n_1700_B("dz", p_197458_0_ -> {
                p_197458_0_.w_1484_f();
                p_197458_0_.u_1723_Y(p_197458_0_.v_4262_N().readDouble());
            }, p_202030_0_ -> p_202030_0_.multiplayerClientSuggestionProvider() == null, new F_2904_S("argument.entity.options.dz.description"));
            W_4351_m.n_1700_B("x_rotation", p_197462_0_ -> p_197462_0_.n_1700_B(WrappedMinMaxBounds.n_1700_B(p_197462_0_.v_4262_N(), true, u_530_F::v_4262_N)), p_202028_0_ -> p_202028_0_.u_2550_I() == WrappedMinMaxBounds.n_1700_B, new F_2904_S("argument.entity.options.x_rotation.description"));
            W_4351_m.n_1700_B("y_rotation", p_197461_0_ -> p_197461_0_.J_1907_R(WrappedMinMaxBounds.n_1700_B(p_197461_0_.v_4262_N(), true, u_530_F::v_4262_N)), p_202036_0_ -> p_202036_0_.M_588_G() == WrappedMinMaxBounds.n_1700_B, new F_2904_S("argument.entity.options.y_rotation.description"));
            W_4351_m.n_1700_B("limit", p_197456_0_ -> {
                int i = p_197456_0_.v_4262_N().getCursor();
                int j = p_197456_0_.v_4262_N().readInt();
                if (j < 1) {
                    p_197456_0_.v_4262_N().setCursor(i);
                    throw P_1922_E.createWithContext((ImmutableStringReader)p_197456_0_.v_4262_N());
                }
                p_197456_0_.n_1700_B(j);
                p_197456_0_.G_564_y(true);
            }, p_202035_0_ -> !p_202035_0_.Y_601_j() && !p_202035_0_.C_2741_M(), new F_2904_S("argument.entity.options.limit.description"));
            W_4351_m.n_1700_B("sort", p_197455_0_ -> {
                int i = p_197455_0_.v_4262_N().getCursor();
                String s = p_197455_0_.v_4262_N().readUnquotedString();
                p_197455_0_.n_1700_B((p_202056_0_, p_202056_1_) -> V_4217_p.J_1907_R(Arrays.asList("nearest", "furthest", "random", "arbitrary"), p_202056_0_));
                int b0 = -1;
                switch (s.hashCode()) {
                    case -938285885: {
                        if (!s.equals("random")) break;
                        b0 = 2;
                        break;
                    }
                    case 1510793967: {
                        if (!s.equals("furthest")) break;
                        b0 = 1;
                        break;
                    }
                    case 1780188658: {
                        if (!s.equals("arbitrary")) break;
                        b0 = 3;
                        break;
                    }
                    case 1825779806: {
                        if (!s.equals("nearest")) break;
                        b0 = 0;
                    }
                }
                p_197455_0_.n_1700_B(switch (b0) {
                    case 0 -> J_2545_z.w_1484_f;
                    case 1 -> J_2545_z.t_148_a;
                    case 2 -> J_2545_z.s_956_w;
                    case 3 -> J_2545_z.v_4262_N;
                    default -> {
                        p_197455_0_.v_4262_N().setCursor(i);
                        throw u_1723_Y.createWithContext((ImmutableStringReader)p_197455_0_.v_4262_N(), (Object)s);
                    }
                });
                p_197455_0_.P_1922_E(true);
            }, p_202043_0_ -> !p_202043_0_.Y_601_j() && !p_202043_0_.k_2293_S(), new F_2904_S("argument.entity.options.sort.description"));
            W_4351_m.n_1700_B("gamemode", p_197452_0_ -> {
                p_197452_0_.n_1700_B((p_202018_1_, p_202018_2_) -> {
                    String s1 = p_202018_1_.getRemaining().toLowerCase(Locale.ROOT);
                    boolean flag1 = !p_197452_0_.Z_875_P();
                    boolean flag2 = true;
                    if (!s1.isEmpty()) {
                        if (s1.charAt(0) == '!') {
                            flag1 = false;
                            s1 = s1.substring(1);
                        } else {
                            flag2 = false;
                        }
                    }
                    for (I_14_v gametype1 : I_14_v.values()) {
                        if (gametype1 == I_14_v.n_1700_B || !gametype1.J_1907_R().toLowerCase(Locale.ROOT).startsWith(s1)) continue;
                        if (flag2) {
                            p_202018_1_.suggest("!" + gametype1.J_1907_R());
                        }
                        if (!flag1) continue;
                        p_202018_1_.suggest(gametype1.J_1907_R());
                    }
                    return p_202018_1_.buildFuture();
                });
                int i = p_197452_0_.v_4262_N().getCursor();
                boolean flag = p_197452_0_.P_1922_E();
                if (p_197452_0_.Z_875_P() && !flag) {
                    p_197452_0_.v_4262_N().setCursor(i);
                    throw J_1907_R.createWithContext((ImmutableStringReader)p_197452_0_.v_4262_N(), (Object)"gamemode");
                }
                String s = p_197452_0_.v_4262_N().readUnquotedString();
                I_14_v gametype = I_14_v.n_1700_B(s, I_14_v.n_1700_B);
                if (gametype == I_14_v.n_1700_B) {
                    p_197452_0_.v_4262_N().setCursor(i);
                    throw v_4262_N.createWithContext((ImmutableStringReader)p_197452_0_.v_4262_N(), (Object)s);
                }
                p_197452_0_.n_1700_B(false);
                p_197452_0_.n_1700_B(p_202055_2_ -> {
                    if (!(p_202055_2_ instanceof B_4088_l)) {
                        return false;
                    }
                    I_14_v gametype1 = ((B_4088_l)p_202055_2_).R_4764_Y.J_1907_R();
                    return flag ? gametype1 != gametype : gametype1 == gametype;
                });
                if (flag) {
                    p_197452_0_.v_4262_N(true);
                } else {
                    p_197452_0_.u_1723_Y(true);
                }
            }, p_202048_0_ -> !p_202048_0_.q_2307_F(), new F_2904_S("argument.entity.options.gamemode.description"));
            W_4351_m.n_1700_B("team", p_197449_0_ -> {
                boolean flag = p_197449_0_.P_1922_E();
                String s = p_197449_0_.v_4262_N().readUnquotedString();
                p_197449_0_.n_1700_B(p_197454_2_ -> {
                    if (!(p_197454_2_ instanceof r_4811_B)) {
                        return false;
                    }
                    o_3050_h team = p_197454_2_.L_1362_X();
                    String s1 = team == null ? "" : team.n_1700_B();
                    return s1.equals(s) != flag;
                });
                if (flag) {
                    p_197449_0_.t_148_a(true);
                } else {
                    p_197449_0_.w_1484_f(true);
                }
            }, p_202038_0_ -> !p_202038_0_.c_3005_b(), new F_2904_S("argument.entity.options.team.description"));
            W_4351_m.n_1700_B("type", p_197447_0_ -> {
                p_197447_0_.n_1700_B((p_202052_1_, p_202052_2_) -> {
                    V_4217_p.n_1700_B(V_3137_a.g_221_o.G_564_y(), p_202052_1_, String.valueOf('!'));
                    V_4217_p.n_1700_B(EntityTypeTags.n_1700_B().J_1907_R(), p_202052_1_, "!#");
                    if (!p_197447_0_.Y_1740_V()) {
                        V_4217_p.n_1700_B(V_3137_a.g_221_o.G_564_y(), p_202052_1_);
                        V_4217_p.n_1700_B(EntityTypeTags.n_1700_B().J_1907_R(), p_202052_1_, String.valueOf('#'));
                    }
                    return p_202052_1_.buildFuture();
                });
                int i = p_197447_0_.v_4262_N().getCursor();
                boolean flag = p_197447_0_.P_1922_E();
                if (p_197447_0_.Y_1740_V() && !flag) {
                    p_197447_0_.v_4262_N().setCursor(i);
                    throw J_1907_R.createWithContext((ImmutableStringReader)p_197447_0_.v_4262_N(), (Object)"type");
                }
                if (flag) {
                    p_197447_0_.H_2857_Y();
                }
                if (p_197447_0_.u_1723_Y()) {
                    g_2336_b resourcelocation = g_2336_b.n_1700_B(p_197447_0_.v_4262_N());
                    p_197447_0_.n_1700_B(p_239573_2_ -> p_239573_2_.f_1574_f().F_1410_V().G_564_y().J_1907_R(resourcelocation).n_1700_B(p_239573_2_.f_4016_n()) != flag);
                } else {
                    g_2336_b resourcelocation1 = g_2336_b.n_1700_B(p_197447_0_.v_4262_N());
                    t_5_h<?> entitytype = V_3137_a.g_221_o.J_1907_R(resourcelocation1).orElseThrow(() -> {
                        p_197447_0_.v_4262_N().setCursor(i);
                        return w_1484_f.createWithContext((ImmutableStringReader)p_197447_0_.v_4262_N(), (Object)resourcelocation1.toString());
                    });
                    if (Objects.equals(t_5_h.g_4106_L, entitytype) && !flag) {
                        p_197447_0_.n_1700_B(false);
                    }
                    p_197447_0_.n_1700_B(p_202057_2_ -> Objects.equals(entitytype, p_202057_2_.f_4016_n()) != flag);
                    if (!flag) {
                        p_197447_0_.n_1700_B(entitytype);
                    }
                }
            }, p_202047_0_ -> !p_202047_0_.A_4115_X(), new F_2904_S("argument.entity.options.type.description"));
            W_4351_m.n_1700_B("tag", p_197448_0_ -> {
                boolean flag = p_197448_0_.P_1922_E();
                String s = p_197448_0_.v_4262_N().readUnquotedString();
                p_197448_0_.n_1700_B(p_197466_2_ -> {
                    if ("".equals(s)) {
                        return p_197466_2_.UploadStatus().isEmpty() != flag;
                    }
                    return p_197466_2_.UploadStatus().contains(s) != flag;
                });
            }, p_202041_0_ -> true, new F_2904_S("argument.entity.options.tag.description"));
            W_4351_m.n_1700_B("nbt", p_197450_0_ -> {
                boolean flag = p_197450_0_.P_1922_E();
                U_2912_j compoundnbt = new r_4318_c(p_197450_0_.v_4262_N()).u_1723_Y();
                p_197450_0_.n_1700_B(p_197443_2_ -> {
                    Z_1993_T itemstack;
                    U_2912_j compoundnbt1 = p_197443_2_.P_1922_E(new U_2912_j());
                    if (p_197443_2_ instanceof B_4088_l && !(itemstack = ((B_4088_l)p_197443_2_).l_1268_F.R_4764_Y()).n_1700_B()) {
                        compoundnbt1.n_1700_B("SelectedItem", itemstack.J_1907_R(new U_2912_j()));
                    }
                    return n_3832_I.n_1700_B(compoundnbt, compoundnbt1, true) != flag;
                });
            }, p_202046_0_ -> true, new F_2904_S("argument.entity.options.nbt.description"));
            W_4351_m.n_1700_B("scores", p_197457_0_ -> {
                StringReader stringreader = p_197457_0_.v_4262_N();
                HashMap map = Maps.newHashMap();
                stringreader.expect('{');
                stringreader.skipWhitespace();
                while (stringreader.canRead() && stringreader.peek() != '}') {
                    stringreader.skipWhitespace();
                    String s = stringreader.readUnquotedString();
                    stringreader.skipWhitespace();
                    stringreader.expect('=');
                    stringreader.skipWhitespace();
                    MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(stringreader);
                    map.put(s, minmaxbounds$intbound);
                    stringreader.skipWhitespace();
                    if (!stringreader.canRead() || stringreader.peek() != ',') continue;
                    stringreader.skip();
                }
                stringreader.expect('}');
                if (!map.isEmpty()) {
                    p_197457_0_.n_1700_B(p_197465_1_ -> {
                        ServerScoreboard scoreboard = p_197465_1_.f_1574_f().S_4022_R();
                        String s1 = p_197465_1_.L_3570_A();
                        for (Map.Entry entry : map.entrySet()) {
                            Objective scoreobjective = scoreboard.R_4764_Y((String)entry.getKey());
                            if (scoreobjective == null) {
                                return false;
                            }
                            if (!scoreboard.n_1700_B(s1, scoreobjective)) {
                                return false;
                            }
                            v_4839_y score = scoreboard.J_1907_R(s1, scoreobjective);
                            int i = score.J_1907_R();
                            if (((MinMaxBounds.G_564_y)entry.getValue()).R_4764_Y(i)) continue;
                            return false;
                        }
                        return true;
                    });
                }
                p_197457_0_.s_956_w(true);
            }, p_202033_0_ -> !p_202033_0_.t_4043_B(), new F_2904_S("argument.entity.options.scores.description"));
            W_4351_m.n_1700_B("advancements", p_197453_0_ -> {
                StringReader stringreader = p_197453_0_.v_4262_N();
                HashMap map = Maps.newHashMap();
                stringreader.expect('{');
                stringreader.skipWhitespace();
                while (stringreader.canRead() && stringreader.peek() != '}') {
                    stringreader.skipWhitespace();
                    g_2336_b resourcelocation = g_2336_b.n_1700_B(stringreader);
                    stringreader.skipWhitespace();
                    stringreader.expect('=');
                    stringreader.skipWhitespace();
                    if (stringreader.canRead() && stringreader.peek() == '{') {
                        HashMap map1 = Maps.newHashMap();
                        stringreader.skipWhitespace();
                        stringreader.expect('{');
                        stringreader.skipWhitespace();
                        while (stringreader.canRead() && stringreader.peek() != '}') {
                            stringreader.skipWhitespace();
                            String s = stringreader.readUnquotedString();
                            stringreader.skipWhitespace();
                            stringreader.expect('=');
                            stringreader.skipWhitespace();
                            boolean flag1 = stringreader.readBoolean();
                            map1.put(s, p_197444_1_ -> p_197444_1_.n_1700_B() == flag1);
                            stringreader.skipWhitespace();
                            if (!stringreader.canRead() || stringreader.peek() != ',') continue;
                            stringreader.skip();
                        }
                        stringreader.skipWhitespace();
                        stringreader.expect('}');
                        stringreader.skipWhitespace();
                        map.put(resourcelocation, p_197435_1_ -> {
                            for (Map.Entry entry : map1.entrySet()) {
                                B_3068_A criterionprogress = p_197435_1_.R_4764_Y((String)entry.getKey());
                                if (criterionprogress != null && ((Predicate)entry.getValue()).test(criterionprogress)) continue;
                                return false;
                            }
                            return true;
                        });
                    } else {
                        boolean flag = stringreader.readBoolean();
                        map.put(resourcelocation, p_197451_1_ -> p_197451_1_.n_1700_B() == flag);
                    }
                    stringreader.skipWhitespace();
                    if (!stringreader.canRead() || stringreader.peek() != ',') continue;
                    stringreader.skip();
                }
                stringreader.expect('}');
                if (!map.isEmpty()) {
                    p_197453_0_.n_1700_B(p_197441_1_ -> {
                        if (!(p_197441_1_ instanceof B_4088_l)) {
                            return false;
                        }
                        B_4088_l serverplayerentity = (B_4088_l)p_197441_1_;
                        S_4998_h playeradvancements = serverplayerentity.g_164_R();
                        ServerAdvancementManager advancementmanager = serverplayerentity.f_1574_f().RealmsWorldOptions();
                        for (Map.Entry entry : map.entrySet()) {
                            A_2629_w advancement = advancementmanager.n_1700_B((g_2336_b)entry.getKey());
                            if (advancement != null && ((Predicate)entry.getValue()).test(playeradvancements.J_1907_R(advancement))) continue;
                            return false;
                        }
                        return true;
                    });
                    p_197453_0_.n_1700_B(false);
                }
                p_197453_0_.u_2550_I(true);
            }, p_202032_0_ -> !p_202032_0_.x_607_J(), new F_2904_S("argument.entity.options.advancements.description"));
            W_4351_m.n_1700_B("predicate", p_229367_0_ -> {
                boolean flag = p_229367_0_.P_1922_E();
                g_2336_b resourcelocation = g_2336_b.n_1700_B(p_229367_0_.v_4262_N());
                p_229367_0_.n_1700_B(p_229366_2_ -> {
                    if (!(p_229366_2_.O_508_d instanceof e_3591_l)) {
                        return false;
                    }
                    e_3591_l serverworld = (e_3591_l)p_229366_2_.O_508_d;
                    LootItemCondition ilootcondition = serverworld.T_2506_i().RealmsDefaultUncaughtExceptionHandler().n_1700_B(resourcelocation);
                    if (ilootcondition == null) {
                        return false;
                    }
                    q_1704_m lootcontext = new q_1704_m.n_1700_B(serverworld).n_1700_B(LootContextParams.n_1700_B, p_229366_2_).n_1700_B(LootContextParams.u_1723_Y, p_229366_2_.s_4990_V()).n_1700_B(f_1402_I.G_564_y);
                    return flag ^ ilootcondition.test(lootcontext);
                });
            }, p_229365_0_ -> true, new F_2904_S("argument.entity.options.predicate.description"));
        }
    }

    public static n_1700_B n_1700_B(J_2545_z parser, String id, int cursor) throws CommandSyntaxException {
        J_1907_R entityoptions$optionhandler = t_148_a.get(id);
        if (entityoptions$optionhandler != null) {
            if (entityoptions$optionhandler.J_1907_R.test(parser)) {
                return entityoptions$optionhandler.n_1700_B;
            }
            throw J_1907_R.createWithContext((ImmutableStringReader)parser.v_4262_N(), (Object)id);
        }
        parser.v_4262_N().setCursor(cursor);
        throw n_1700_B.createWithContext((ImmutableStringReader)parser.v_4262_N(), (Object)id);
    }

    public static void n_1700_B(J_2545_z parser, SuggestionsBuilder builder) {
        String s = builder.getRemaining().toLowerCase(Locale.ROOT);
        for (Map.Entry<String, J_1907_R> entry : t_148_a.entrySet()) {
            if (!entry.getValue().J_1907_R.test(parser) || !entry.getKey().toLowerCase(Locale.ROOT).startsWith(s)) continue;
            builder.suggest(entry.getKey() + "=", (Message)entry.getValue().R_4764_Y);
        }
    }

    static class J_1907_R {
        public final n_1700_B n_1700_B;
        public final Predicate<J_2545_z> J_1907_R;
        public final x_282_a R_4764_Y;

        private J_1907_R(n_1700_B handlerIn, Predicate<J_2545_z> p_i48717_2_, x_282_a tooltipIn) {
            this.n_1700_B = handlerIn;
            this.J_1907_R = p_i48717_2_;
            this.R_4764_Y = tooltipIn;
        }
    }

    public static interface n_1700_B {
        public void handle(J_2545_z var1) throws CommandSyntaxException;
    }
}


