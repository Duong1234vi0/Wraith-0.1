/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.ArgumentBuilder
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import lightning.product.LootContextParams;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.K_4074_S;
import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.ReplaceItemCommand;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.X_4512_s;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.f_1402_I;
import lightning.product.f_4186_T;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.i_4556_r;
import lightning.product.ResourceLocationArgument;
import lightning.product.BlockPosArgument;
import lightning.product.n_1494_c;
import lightning.product.p_4985_U;
import lightning.product.q_1704_m;
import lightning.product.r_4811_B;
import lightning.product.u_1579_Y;
import lightning.product.y_2498_m;
import lightning.product.ItemArgument;

public class h_1834_T {
    public static final SuggestionProvider<y_2498_m> n_1700_B = (p_218873_0_, p_218873_1_) -> {
        f_4186_T loottablemanager = ((y_2498_m)p_218873_0_.getSource()).w_1457_N().F_2624_D();
        return V_4217_p.n_1700_B(loottablemanager.J_1907_R(), p_218873_1_);
    };
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_218896_0_ -> new F_2904_S("commands.drop.no_held_items", p_218896_0_));
    private static final DynamicCommandExceptionType R_4764_Y = new DynamicCommandExceptionType(p_218889_0_ -> new F_2904_S("commands.drop.no_loot_table", p_218889_0_));

    public static void n_1700_B(CommandDispatcher<y_2498_m> p_218886_0_) {
        p_218886_0_.register(h_1834_T.n_1700_B((LiteralArgumentBuilder)Q_2241_p.n_1700_B("loot").requires(p_218903_0_ -> p_218903_0_.n_1700_B(2)), (ArgumentBuilder<y_2498_m, ?> p_218880_0_, R_4764_Y p_218880_1_) -> p_218880_0_.then(Q_2241_p.n_1700_B("fish").then(Q_2241_p.n_1700_B("loot_table", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("pos", BlockPosArgument.n_1700_B()).executes(p_218899_1_ -> h_1834_T.n_1700_B((CommandContext<y_2498_m>)p_218899_1_, ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_218899_1_, "loot_table"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218899_1_, "pos"), Z_1993_T.J_1907_R, p_218880_1_))).then(Q_2241_p.n_1700_B("tool", ItemArgument.n_1700_B()).executes(p_218874_1_ -> h_1834_T.n_1700_B((CommandContext<y_2498_m>)p_218874_1_, ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_218874_1_, "loot_table"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218874_1_, "pos"), ItemArgument.n_1700_B(p_218874_1_, "tool").n_1700_B(1, false), p_218880_1_)))).then(Q_2241_p.n_1700_B("mainhand").executes(p_218892_1_ -> h_1834_T.n_1700_B((CommandContext<y_2498_m>)p_218892_1_, ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_218892_1_, "loot_table"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218892_1_, "pos"), h_1834_T.n_1700_B((y_2498_m)p_218892_1_.getSource(), e_1174_E.n_1700_B), p_218880_1_)))).then(Q_2241_p.n_1700_B("offhand").executes(p_218898_1_ -> h_1834_T.n_1700_B((CommandContext<y_2498_m>)p_218898_1_, ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_218898_1_, "loot_table"), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218898_1_, "pos"), h_1834_T.n_1700_B((y_2498_m)p_218898_1_.getSource(), e_1174_E.J_1907_R), p_218880_1_)))))).then(Q_2241_p.n_1700_B("loot").then(Q_2241_p.n_1700_B("loot_table", ResourceLocationArgument.n_1700_B()).suggests(n_1700_B).executes(p_218861_1_ -> h_1834_T.n_1700_B((CommandContext<y_2498_m>)p_218861_1_, ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_218861_1_, "loot_table"), p_218880_1_)))).then(Q_2241_p.n_1700_B("kill").then(Q_2241_p.n_1700_B("target", i_4556_r.n_1700_B()).executes(p_218891_1_ -> h_1834_T.n_1700_B((CommandContext<y_2498_m>)p_218891_1_, i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_218891_1_, "target"), p_218880_1_)))).then(Q_2241_p.n_1700_B("mine").then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("pos", BlockPosArgument.n_1700_B()).executes(p_218897_1_ -> h_1834_T.n_1700_B((CommandContext<y_2498_m>)p_218897_1_, BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218897_1_, "pos"), Z_1993_T.J_1907_R, p_218880_1_))).then(Q_2241_p.n_1700_B("tool", ItemArgument.n_1700_B()).executes(p_218878_1_ -> h_1834_T.n_1700_B((CommandContext<y_2498_m>)p_218878_1_, BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218878_1_, "pos"), ItemArgument.n_1700_B(p_218878_1_, "tool").n_1700_B(1, false), p_218880_1_)))).then(Q_2241_p.n_1700_B("mainhand").executes(p_218895_1_ -> h_1834_T.n_1700_B((CommandContext<y_2498_m>)p_218895_1_, BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218895_1_, "pos"), h_1834_T.n_1700_B((y_2498_m)p_218895_1_.getSource(), e_1174_E.n_1700_B), p_218880_1_)))).then(Q_2241_p.n_1700_B("offhand").executes(p_218888_1_ -> h_1834_T.n_1700_B((CommandContext<y_2498_m>)p_218888_1_, BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218888_1_, "pos"), h_1834_T.n_1700_B((y_2498_m)p_218888_1_.getSource(), e_1174_E.J_1907_R), p_218880_1_)))))));
    }

    private static <T extends ArgumentBuilder<y_2498_m, T>> T n_1700_B(T p_218868_0_, n_1700_B p_218868_1_) {
        return (T)p_218868_0_.then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("replace").then(Q_2241_p.n_1700_B("entity").then(Q_2241_p.n_1700_B("entities", i_4556_r.J_1907_R()).then(p_218868_1_.construct((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B("slot", X_4512_s.n_1700_B()), (p_218866_0_, p_218866_1_, p_218866_2_) -> h_1834_T.n_1700_B(i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_218866_0_, "entities"), X_4512_s.n_1700_B((CommandContext<y_2498_m>)p_218866_0_, "slot"), p_218866_1_.size(), p_218866_1_, p_218866_2_)).then(p_218868_1_.construct((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B("count", IntegerArgumentType.integer((int)0)), (p_218884_0_, p_218884_1_, p_218884_2_) -> h_1834_T.n_1700_B(i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_218884_0_, "entities"), X_4512_s.n_1700_B((CommandContext<y_2498_m>)p_218884_0_, "slot"), IntegerArgumentType.getInteger((CommandContext)p_218884_0_, (String)"count"), p_218884_1_, p_218884_2_))))))).then(Q_2241_p.n_1700_B("block").then(Q_2241_p.n_1700_B("targetPos", BlockPosArgument.n_1700_B()).then(p_218868_1_.construct((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B("slot", X_4512_s.n_1700_B()), (p_218864_0_, p_218864_1_, p_218864_2_) -> h_1834_T.n_1700_B((y_2498_m)p_218864_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218864_0_, "targetPos"), X_4512_s.n_1700_B((CommandContext<y_2498_m>)p_218864_0_, "slot"), p_218864_1_.size(), p_218864_1_, p_218864_2_)).then(p_218868_1_.construct((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B("count", IntegerArgumentType.integer((int)0)), (p_218870_0_, p_218870_1_, p_218870_2_) -> h_1834_T.n_1700_B((y_2498_m)p_218870_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218870_0_, "targetPos"), IntegerArgumentType.getInteger((CommandContext)p_218870_0_, (String)"slot"), IntegerArgumentType.getInteger((CommandContext)p_218870_0_, (String)"count"), p_218870_1_, p_218870_2_))))))).then(Q_2241_p.n_1700_B("insert").then(p_218868_1_.construct((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B("targetPos", BlockPosArgument.n_1700_B()), (p_218885_0_, p_218885_1_, p_218885_2_) -> h_1834_T.n_1700_B((y_2498_m)p_218885_0_.getSource(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_218885_0_, "targetPos"), (List<Z_1993_T>)p_218885_1_, p_218885_2_)))).then(Q_2241_p.n_1700_B("give").then(p_218868_1_.construct((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B("players", i_4556_r.G_564_y()), (p_218867_0_, p_218867_1_, p_218867_2_) -> h_1834_T.n_1700_B(i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_218867_0_, "players"), (List<Z_1993_T>)p_218867_1_, p_218867_2_)))).then(Q_2241_p.n_1700_B("spawn").then(p_218868_1_.construct((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B("targetPos", u_1579_Y.n_1700_B()), (p_218877_0_, p_218877_1_, p_218877_2_) -> h_1834_T.n_1700_B((y_2498_m)p_218877_0_.getSource(), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_218877_0_, "targetPos"), (List<Z_1993_T>)p_218877_1_, p_218877_2_))));
    }

    private static Container n_1700_B(y_2498_m p_218862_0_, c_1514_x p_218862_1_) throws CommandSyntaxException {
        i_2154_H tileentity = p_218862_0_.h_1847_R().getTileEntity(p_218862_1_);
        if (!(tileentity instanceof Container)) {
            throw ReplaceItemCommand.n_1700_B.create();
        }
        return (Container)((Object)tileentity);
    }

    private static int n_1700_B(y_2498_m p_218900_0_, c_1514_x p_218900_1_, List<Z_1993_T> p_218900_2_, J_1907_R p_218900_3_) throws CommandSyntaxException {
        Container iinventory = h_1834_T.n_1700_B(p_218900_0_, p_218900_1_);
        ArrayList list = Lists.newArrayListWithCapacity((int)p_218900_2_.size());
        for (Z_1993_T itemstack : p_218900_2_) {
            if (!h_1834_T.n_1700_B(iinventory, itemstack.t_148_a())) continue;
            iinventory.J_1907_R();
            list.add(itemstack);
        }
        p_218900_3_.accept(list);
        return list.size();
    }

    private static boolean n_1700_B(Container p_218890_0_, Z_1993_T p_218890_1_) {
        boolean flag = false;
        for (int i = 0; i < p_218890_0_.Y_259_p() && !p_218890_1_.n_1700_B(); ++i) {
            Z_1993_T itemstack = p_218890_0_.s_956_w(i);
            if (!p_218890_0_.a_(i, p_218890_1_)) continue;
            if (itemstack.n_1700_B()) {
                p_218890_0_.J_1907_R(i, p_218890_1_);
                flag = true;
                break;
            }
            if (!h_1834_T.n_1700_B(itemstack, p_218890_1_)) continue;
            int j = p_218890_1_.R_4764_Y() - itemstack.t_4043_B();
            int k = Math.min(p_218890_1_.t_4043_B(), j);
            p_218890_1_.v_4262_N(k);
            itemstack.u_1723_Y(k);
            flag = true;
        }
        return flag;
    }

    private static int n_1700_B(y_2498_m p_218894_0_, c_1514_x p_218894_1_, int p_218894_2_, int p_218894_3_, List<Z_1993_T> p_218894_4_, J_1907_R p_218894_5_) throws CommandSyntaxException {
        Container iinventory = h_1834_T.n_1700_B(p_218894_0_, p_218894_1_);
        int i = iinventory.Y_259_p();
        if (p_218894_2_ >= 0 && p_218894_2_ < i) {
            ArrayList list = Lists.newArrayListWithCapacity((int)p_218894_4_.size());
            for (int j = 0; j < p_218894_3_; ++j) {
                Z_1993_T itemstack;
                int k = p_218894_2_ + j;
                Z_1993_T z_1993_T = itemstack = j < p_218894_4_.size() ? p_218894_4_.get(j) : Z_1993_T.J_1907_R;
                if (!iinventory.a_(k, itemstack)) continue;
                iinventory.J_1907_R(k, itemstack);
                list.add(itemstack);
            }
            p_218894_5_.accept(list);
            return list.size();
        }
        throw ReplaceItemCommand.J_1907_R.create((Object)p_218894_2_);
    }

    private static boolean n_1700_B(Z_1993_T p_218883_0_, Z_1993_T p_218883_1_) {
        return p_218883_0_.J_1907_R() == p_218883_1_.J_1907_R() && p_218883_0_.v_4262_N() == p_218883_1_.v_4262_N() && p_218883_0_.t_4043_B() <= p_218883_0_.R_4764_Y() && Objects.equals(p_218883_0_.Q_4569_t(), p_218883_1_.Q_4569_t());
    }

    private static int n_1700_B(Collection<B_4088_l> p_218859_0_, List<Z_1993_T> p_218859_1_, J_1907_R p_218859_2_) throws CommandSyntaxException {
        ArrayList list = Lists.newArrayListWithCapacity((int)p_218859_1_.size());
        for (Z_1993_T itemstack : p_218859_1_) {
            for (B_4088_l serverplayerentity : p_218859_0_) {
                if (!serverplayerentity.l_1268_F.P_1922_E(itemstack.t_148_a())) continue;
                list.add(itemstack);
            }
        }
        p_218859_2_.accept(list);
        return list.size();
    }

    private static void n_1700_B(N_4263_v p_218901_0_, List<Z_1993_T> p_218901_1_, int p_218901_2_, int p_218901_3_, List<Z_1993_T> p_218901_4_) {
        for (int i = 0; i < p_218901_3_; ++i) {
            Z_1993_T itemstack;
            Z_1993_T z_1993_T = itemstack = i < p_218901_1_.size() ? p_218901_1_.get(i) : Z_1993_T.J_1907_R;
            if (!p_218901_0_.n_1700_B(p_218901_2_ + i, itemstack.t_148_a())) continue;
            p_218901_4_.add(itemstack);
        }
    }

    private static int n_1700_B(Collection<? extends N_4263_v> p_218865_0_, int p_218865_1_, int p_218865_2_, List<Z_1993_T> p_218865_3_, J_1907_R p_218865_4_) throws CommandSyntaxException {
        ArrayList list = Lists.newArrayListWithCapacity((int)p_218865_3_.size());
        for (N_4263_v n_4263_v : p_218865_0_) {
            if (n_4263_v instanceof B_4088_l) {
                B_4088_l serverplayerentity = (B_4088_l)n_4263_v;
                serverplayerentity.o_1800_r.M_588_G();
                h_1834_T.n_1700_B(n_4263_v, p_218865_3_, p_218865_1_, p_218865_2_, list);
                serverplayerentity.o_1800_r.M_588_G();
                continue;
            }
            h_1834_T.n_1700_B(n_4263_v, p_218865_3_, p_218865_1_, p_218865_2_, list);
        }
        p_218865_4_.accept(list);
        return list.size();
    }

    private static int n_1700_B(y_2498_m p_218881_0_, e_2866_D p_218881_1_, List<Z_1993_T> p_218881_2_, J_1907_R p_218881_3_) throws CommandSyntaxException {
        e_3591_l serverworld = p_218881_0_.h_1847_R();
        p_218881_2_.forEach(p_218882_2_ -> {
            n_1494_c itementity = new n_1494_c(serverworld, p_218881_1_.J_1907_R, p_218881_1_.R_4764_Y, p_218881_1_.G_564_y, p_218882_2_.t_148_a());
            itementity.t_148_a();
            serverworld.a_(itementity);
        });
        p_218881_3_.accept(p_218881_2_);
        return p_218881_2_.size();
    }

    private static void n_1700_B(y_2498_m p_218875_0_, List<Z_1993_T> p_218875_1_) {
        if (p_218875_1_.size() == 1) {
            Z_1993_T itemstack = p_218875_1_.get(0);
            p_218875_0_.n_1700_B(new F_2904_S("commands.drop.success.single", itemstack.t_4043_B(), itemstack.A_4115_X()), false);
        } else {
            p_218875_0_.n_1700_B(new F_2904_S("commands.drop.success.multiple", p_218875_1_.size()), false);
        }
    }

    private static void n_1700_B(y_2498_m p_218860_0_, List<Z_1993_T> p_218860_1_, g_2336_b p_218860_2_) {
        if (p_218860_1_.size() == 1) {
            Z_1993_T itemstack = p_218860_1_.get(0);
            p_218860_0_.n_1700_B(new F_2904_S("commands.drop.success.single_with_table", itemstack.t_4043_B(), itemstack.A_4115_X(), p_218860_2_), false);
        } else {
            p_218860_0_.n_1700_B(new F_2904_S("commands.drop.success.multiple_with_table", p_218860_1_.size(), p_218860_2_), false);
        }
    }

    private static Z_1993_T n_1700_B(y_2498_m p_218872_0_, e_1174_E p_218872_1_) throws CommandSyntaxException {
        N_4263_v entity = p_218872_0_.M_182_A();
        if (entity instanceof r_4811_B) {
            return ((r_4811_B)entity).J_1907_R(p_218872_1_);
        }
        throw J_1907_R.create((Object)entity.c_());
    }

    private static int n_1700_B(CommandContext<y_2498_m> p_218879_0_, c_1514_x p_218879_1_, Z_1993_T p_218879_2_, R_4764_Y p_218879_3_) throws CommandSyntaxException {
        y_2498_m commandsource = (y_2498_m)p_218879_0_.getSource();
        e_3591_l serverworld = commandsource.h_1847_R();
        K_4074_S blockstate = serverworld.getBlockState(p_218879_1_);
        i_2154_H tileentity = serverworld.getTileEntity(p_218879_1_);
        q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B(serverworld).n_1700_B(LootContextParams.u_1723_Y, e_2866_D.n_1700_B(p_218879_1_)).n_1700_B(LootContextParams.v_4262_N, blockstate).J_1907_R(LootContextParams.w_1484_f, tileentity).J_1907_R(LootContextParams.n_1700_B, commandsource.Q_4569_t()).n_1700_B(LootContextParams.t_148_a, p_218879_2_);
        List<Z_1993_T> list = blockstate.n_1700_B(lootcontext$builder);
        return p_218879_3_.accept(p_218879_0_, list, p_218893_2_ -> h_1834_T.n_1700_B(commandsource, (List<Z_1993_T>)p_218893_2_, blockstate.J_1907_R().P_1922_E()));
    }

    private static int n_1700_B(CommandContext<y_2498_m> p_218869_0_, N_4263_v p_218869_1_, R_4764_Y p_218869_2_) throws CommandSyntaxException {
        if (!(p_218869_1_ instanceof r_4811_B)) {
            throw R_4764_Y.create((Object)p_218869_1_.c_());
        }
        g_2336_b resourcelocation = ((r_4811_B)p_218869_1_).q_3401_q();
        y_2498_m commandsource = (y_2498_m)p_218869_0_.getSource();
        q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B(commandsource.h_1847_R());
        N_4263_v entity = commandsource.Q_4569_t();
        if (entity instanceof a_3913_L) {
            lootcontext$builder.n_1700_B(LootContextParams.J_1907_R, (a_3913_L)entity);
        }
        lootcontext$builder.n_1700_B(LootContextParams.R_4764_Y, P_11_z.Q_4569_t);
        lootcontext$builder.J_1907_R(LootContextParams.P_1922_E, entity);
        lootcontext$builder.J_1907_R(LootContextParams.G_564_y, entity);
        lootcontext$builder.n_1700_B(LootContextParams.n_1700_B, p_218869_1_);
        lootcontext$builder.n_1700_B(LootContextParams.u_1723_Y, commandsource.P_4830_p());
        p_4985_U loottable = commandsource.w_1457_N().F_2624_D().n_1700_B(resourcelocation);
        List<Z_1993_T> list = loottable.n_1700_B(lootcontext$builder.n_1700_B(f_1402_I.u_1723_Y));
        return p_218869_2_.accept(p_218869_0_, list, p_218863_2_ -> h_1834_T.n_1700_B(commandsource, (List<Z_1993_T>)p_218863_2_, resourcelocation));
    }

    private static int n_1700_B(CommandContext<y_2498_m> p_218887_0_, g_2336_b p_218887_1_, R_4764_Y p_218887_2_) throws CommandSyntaxException {
        y_2498_m commandsource = (y_2498_m)p_218887_0_.getSource();
        q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B(commandsource.h_1847_R()).J_1907_R(LootContextParams.n_1700_B, commandsource.Q_4569_t()).n_1700_B(LootContextParams.u_1723_Y, commandsource.P_4830_p());
        return h_1834_T.n_1700_B(p_218887_0_, p_218887_1_, lootcontext$builder.n_1700_B(f_1402_I.J_1907_R), p_218887_2_);
    }

    private static int n_1700_B(CommandContext<y_2498_m> p_218876_0_, g_2336_b p_218876_1_, c_1514_x p_218876_2_, Z_1993_T p_218876_3_, R_4764_Y p_218876_4_) throws CommandSyntaxException {
        y_2498_m commandsource = (y_2498_m)p_218876_0_.getSource();
        q_1704_m lootcontext = new q_1704_m.n_1700_B(commandsource.h_1847_R()).n_1700_B(LootContextParams.u_1723_Y, e_2866_D.n_1700_B(p_218876_2_)).n_1700_B(LootContextParams.t_148_a, p_218876_3_).J_1907_R(LootContextParams.n_1700_B, commandsource.Q_4569_t()).n_1700_B(f_1402_I.P_1922_E);
        return h_1834_T.n_1700_B(p_218876_0_, p_218876_1_, lootcontext, p_218876_4_);
    }

    private static int n_1700_B(CommandContext<y_2498_m> p_218871_0_, g_2336_b p_218871_1_, q_1704_m p_218871_2_, R_4764_Y p_218871_3_) throws CommandSyntaxException {
        y_2498_m commandsource = (y_2498_m)p_218871_0_.getSource();
        p_4985_U loottable = commandsource.w_1457_N().F_2624_D().n_1700_B(p_218871_1_);
        List<Z_1993_T> list = loottable.n_1700_B(p_218871_2_);
        return p_218871_3_.accept(p_218871_0_, list, p_218902_1_ -> h_1834_T.n_1700_B(commandsource, p_218902_1_));
    }

    @FunctionalInterface
    static interface n_1700_B {
        public ArgumentBuilder<y_2498_m, ?> construct(ArgumentBuilder<y_2498_m, ?> var1, R_4764_Y var2);
    }

    @FunctionalInterface
    static interface R_4764_Y {
        public int accept(CommandContext<y_2498_m> var1, List<Z_1993_T> var2, J_1907_R var3) throws CommandSyntaxException;
    }

    @FunctionalInterface
    static interface J_1907_R {
        public void accept(List<Z_1993_T> var1) throws CommandSyntaxException;
    }
}


