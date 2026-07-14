/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.BoolArgumentType
 *  com.mojang.brigadier.arguments.FloatArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.Dynamic4CommandExceptionType
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic4CommandExceptionType;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.Q_2241_p;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Vec2Argument;
import lightning.product.i_4556_r;
import lightning.product.o_3050_h;
import lightning.product.Material;
import lightning.product.u_530_F;
import lightning.product.y_2498_m;

public class M_3778_G {
    private static final Dynamic4CommandExceptionType n_1700_B = new Dynamic4CommandExceptionType((p_208910_0_, p_208910_1_, p_208910_2_, p_208910_3_) -> new F_2904_S("commands.spreadplayers.failed.teams", p_208910_0_, p_208910_1_, p_208910_2_, p_208910_3_));
    private static final Dynamic4CommandExceptionType J_1907_R = new Dynamic4CommandExceptionType((p_208912_0_, p_208912_1_, p_208912_2_, p_208912_3_) -> new F_2904_S("commands.spreadplayers.failed.entities", p_208912_0_, p_208912_1_, p_208912_2_, p_208912_3_));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("spreadplayers").requires(p_198721_0_ -> p_198721_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("center", Vec2Argument.n_1700_B()).then(Q_2241_p.n_1700_B("spreadDistance", FloatArgumentType.floatArg((float)0.0f)).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("maxRange", FloatArgumentType.floatArg((float)1.0f)).then(Q_2241_p.n_1700_B("respectTeams", BoolArgumentType.bool()).then(Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).executes(p_198718_0_ -> M_3778_G.n_1700_B((y_2498_m)p_198718_0_.getSource(), Vec2Argument.n_1700_B((CommandContext<y_2498_m>)p_198718_0_, "center"), FloatArgumentType.getFloat((CommandContext)p_198718_0_, (String)"spreadDistance"), FloatArgumentType.getFloat((CommandContext)p_198718_0_, (String)"maxRange"), 256, BoolArgumentType.getBool((CommandContext)p_198718_0_, (String)"respectTeams"), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198718_0_, "targets")))))).then(Q_2241_p.n_1700_B("under").then(Q_2241_p.n_1700_B("maxHeight", IntegerArgumentType.integer((int)0)).then(Q_2241_p.n_1700_B("respectTeams", BoolArgumentType.bool()).then(Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).executes(p_241069_0_ -> M_3778_G.n_1700_B((y_2498_m)p_241069_0_.getSource(), Vec2Argument.n_1700_B((CommandContext<y_2498_m>)p_241069_0_, "center"), FloatArgumentType.getFloat((CommandContext)p_241069_0_, (String)"spreadDistance"), FloatArgumentType.getFloat((CommandContext)p_241069_0_, (String)"maxRange"), IntegerArgumentType.getInteger((CommandContext)p_241069_0_, (String)"maxHeight"), BoolArgumentType.getBool((CommandContext)p_241069_0_, (String)"respectTeams"), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_241069_0_, "targets")))))))))));
    }

    private static int n_1700_B(y_2498_m p_241070_0_, P_3504_Q p_241070_1_, float p_241070_2_, float p_241070_3_, int p_241070_4_, boolean p_241070_5_, Collection<? extends N_4263_v> p_241070_6_) throws CommandSyntaxException {
        Random random = new Random();
        double d0 = p_241070_1_.t_148_a - p_241070_3_;
        double d1 = p_241070_1_.s_956_w - p_241070_3_;
        double d2 = p_241070_1_.t_148_a + p_241070_3_;
        double d3 = p_241070_1_.s_956_w + p_241070_3_;
        n_1700_B[] aspreadplayerscommand$position = M_3778_G.n_1700_B(random, p_241070_5_ ? M_3778_G.n_1700_B(p_241070_6_) : p_241070_6_.size(), d0, d1, d2, d3);
        M_3778_G.n_1700_B(p_241070_1_, p_241070_2_, p_241070_0_.h_1847_R(), random, d0, d1, d2, d3, p_241070_4_, aspreadplayerscommand$position, p_241070_5_);
        double d4 = M_3778_G.n_1700_B(p_241070_6_, p_241070_0_.h_1847_R(), aspreadplayerscommand$position, p_241070_4_, p_241070_5_);
        p_241070_0_.n_1700_B(new F_2904_S("commands.spreadplayers.success." + (p_241070_5_ ? "teams" : "entities"), aspreadplayerscommand$position.length, Float.valueOf(p_241070_1_.t_148_a), Float.valueOf(p_241070_1_.s_956_w), String.format(Locale.ROOT, "%.2f", d4)), true);
        return aspreadplayerscommand$position.length;
    }

    private static int n_1700_B(Collection<? extends N_4263_v> entities) {
        HashSet set = Sets.newHashSet();
        for (N_4263_v n_4263_v : entities) {
            if (n_4263_v instanceof a_3913_L) {
                set.add(n_4263_v.L_1362_X());
                continue;
            }
            set.add(null);
        }
        return set.size();
    }

    private static void n_1700_B(P_3504_Q p_241071_0_, double p_241071_1_, e_3591_l p_241071_3_, Random p_241071_4_, double p_241071_5_, double p_241071_7_, double p_241071_9_, double p_241071_11_, int p_241071_13_, n_1700_B[] p_241071_14_, boolean p_241071_15_) throws CommandSyntaxException {
        int i;
        boolean flag = true;
        double d0 = 3.4028234663852886E38;
        for (i = 0; i < 10000 && flag; ++i) {
            flag = false;
            d0 = 3.4028234663852886E38;
            for (int j = 0; j < p_241071_14_.length; ++j) {
                n_1700_B spreadplayerscommand$position = p_241071_14_[j];
                int k = 0;
                n_1700_B spreadplayerscommand$position1 = new n_1700_B();
                for (int l = 0; l < p_241071_14_.length; ++l) {
                    if (j == l) continue;
                    n_1700_B spreadplayerscommand$position2 = p_241071_14_[l];
                    double d1 = spreadplayerscommand$position.n_1700_B(spreadplayerscommand$position2);
                    d0 = Math.min(d1, d0);
                    if (!(d1 < p_241071_1_)) continue;
                    ++k;
                    spreadplayerscommand$position1.n_1700_B += spreadplayerscommand$position2.n_1700_B - spreadplayerscommand$position.n_1700_B;
                    spreadplayerscommand$position1.J_1907_R += spreadplayerscommand$position2.J_1907_R - spreadplayerscommand$position.J_1907_R;
                }
                if (k > 0) {
                    spreadplayerscommand$position1.n_1700_B /= (double)k;
                    spreadplayerscommand$position1.J_1907_R /= (double)k;
                    double d2 = spreadplayerscommand$position1.J_1907_R();
                    if (d2 > 0.0) {
                        spreadplayerscommand$position1.n_1700_B();
                        spreadplayerscommand$position.J_1907_R(spreadplayerscommand$position1);
                    } else {
                        spreadplayerscommand$position.n_1700_B(p_241071_4_, p_241071_5_, p_241071_7_, p_241071_9_, p_241071_11_);
                    }
                    flag = true;
                }
                if (!spreadplayerscommand$position.n_1700_B(p_241071_5_, p_241071_7_, p_241071_9_, p_241071_11_)) continue;
                flag = true;
            }
            if (flag) continue;
            for (n_1700_B spreadplayerscommand$position3 : p_241071_14_) {
                if (spreadplayerscommand$position3.J_1907_R(p_241071_3_, p_241071_13_)) continue;
                spreadplayerscommand$position3.n_1700_B(p_241071_4_, p_241071_5_, p_241071_7_, p_241071_9_, p_241071_11_);
                flag = true;
            }
        }
        if (d0 == 3.4028234663852886E38) {
            d0 = 0.0;
        }
        if (i >= 10000) {
            if (p_241071_15_) {
                throw n_1700_B.create((Object)p_241071_14_.length, (Object)Float.valueOf(p_241071_0_.t_148_a), (Object)Float.valueOf(p_241071_0_.s_956_w), (Object)String.format(Locale.ROOT, "%.2f", d0));
            }
            throw J_1907_R.create((Object)p_241071_14_.length, (Object)Float.valueOf(p_241071_0_.t_148_a), (Object)Float.valueOf(p_241071_0_.s_956_w), (Object)String.format(Locale.ROOT, "%.2f", d0));
        }
    }

    private static double n_1700_B(Collection<? extends N_4263_v> p_241072_0_, e_3591_l p_241072_1_, n_1700_B[] p_241072_2_, int p_241072_3_, boolean p_241072_4_) {
        double d0 = 0.0;
        int i = 0;
        HashMap map = Maps.newHashMap();
        for (N_4263_v n_4263_v : p_241072_0_) {
            n_1700_B spreadplayerscommand$position;
            if (p_241072_4_) {
                o_3050_h team;
                o_3050_h o_3050_h2 = team = n_4263_v instanceof a_3913_L ? n_4263_v.L_1362_X() : null;
                if (!map.containsKey(team)) {
                    map.put(team, p_241072_2_[i++]);
                }
                spreadplayerscommand$position = (n_1700_B)map.get(team);
            } else {
                spreadplayerscommand$position = p_241072_2_[i++];
            }
            n_4263_v.M_588_G((double)u_530_F.R_4764_Y(spreadplayerscommand$position.n_1700_B) + 0.5, spreadplayerscommand$position.n_1700_B(p_241072_1_, p_241072_3_), (double)u_530_F.R_4764_Y(spreadplayerscommand$position.J_1907_R) + 0.5);
            double d2 = Double.MAX_VALUE;
            for (n_1700_B spreadplayerscommand$position1 : p_241072_2_) {
                if (spreadplayerscommand$position == spreadplayerscommand$position1) continue;
                double d1 = spreadplayerscommand$position.n_1700_B(spreadplayerscommand$position1);
                d2 = Math.min(d1, d2);
            }
            d0 += d2;
        }
        return p_241072_0_.size() < 2 ? 0.0 : d0 / (double)p_241072_0_.size();
    }

    private static n_1700_B[] n_1700_B(Random random, int count, double minX, double minZ, double maxX, double maxZ) {
        n_1700_B[] aspreadplayerscommand$position = new n_1700_B[count];
        for (int i = 0; i < aspreadplayerscommand$position.length; ++i) {
            n_1700_B spreadplayerscommand$position = new n_1700_B();
            spreadplayerscommand$position.n_1700_B(random, minX, minZ, maxX, maxZ);
            aspreadplayerscommand$position[i] = spreadplayerscommand$position;
        }
        return aspreadplayerscommand$position;
    }

    static class n_1700_B {
        private double n_1700_B;
        private double J_1907_R;

        n_1700_B() {
        }

        double n_1700_B(n_1700_B other) {
            double d0 = this.n_1700_B - other.n_1700_B;
            double d1 = this.J_1907_R - other.J_1907_R;
            return Math.sqrt(d0 * d0 + d1 * d1);
        }

        void n_1700_B() {
            double d0 = this.J_1907_R();
            this.n_1700_B /= d0;
            this.J_1907_R /= d0;
        }

        float J_1907_R() {
            return u_530_F.n_1700_B(this.n_1700_B * this.n_1700_B + this.J_1907_R * this.J_1907_R);
        }

        public void J_1907_R(n_1700_B other) {
            this.n_1700_B -= other.n_1700_B;
            this.J_1907_R -= other.J_1907_R;
        }

        public boolean n_1700_B(double minX, double minZ, double maxX, double maxZ) {
            boolean flag = false;
            if (this.n_1700_B < minX) {
                this.n_1700_B = minX;
                flag = true;
            } else if (this.n_1700_B > maxX) {
                this.n_1700_B = maxX;
                flag = true;
            }
            if (this.J_1907_R < minZ) {
                this.J_1907_R = minZ;
                flag = true;
            } else if (this.J_1907_R > maxZ) {
                this.J_1907_R = maxZ;
                flag = true;
            }
            return flag;
        }

        public int n_1700_B(BlockGetter worldIn, int p_198710_2_) {
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B(this.n_1700_B, (double)(p_198710_2_ + 1), this.J_1907_R);
            boolean flag = worldIn.getBlockState(blockpos$mutable).v_4262_N();
            blockpos$mutable.n_1700_B(b_257_Y.n_1700_B);
            boolean flag1 = worldIn.getBlockState(blockpos$mutable).v_4262_N();
            while (blockpos$mutable.getY() > 0) {
                blockpos$mutable.n_1700_B(b_257_Y.n_1700_B);
                boolean flag2 = worldIn.getBlockState(blockpos$mutable).v_4262_N();
                if (!flag2 && flag1 && flag) {
                    return blockpos$mutable.getY() + 1;
                }
                flag = flag1;
                flag1 = flag2;
            }
            return p_198710_2_ + 1;
        }

        public boolean J_1907_R(BlockGetter p_241074_1_, int p_241074_2_) {
            c_1514_x blockpos = new c_1514_x(this.n_1700_B, (double)(this.n_1700_B(p_241074_1_, p_241074_2_) - 1), this.J_1907_R);
            K_4074_S blockstate = p_241074_1_.getBlockState(blockpos);
            Material material = blockstate.R_4764_Y();
            return blockpos.getY() < p_241074_2_ && !material.n_1700_B() && material != Material.h_1847_R;
        }

        public void n_1700_B(Random random, double minX, double minZ, double maxX, double maZx) {
            this.n_1700_B = u_530_F.n_1700_B(random, minX, maxX);
            this.J_1907_R = u_530_F.n_1700_B(random, minZ, maZx);
        }
    }
}


