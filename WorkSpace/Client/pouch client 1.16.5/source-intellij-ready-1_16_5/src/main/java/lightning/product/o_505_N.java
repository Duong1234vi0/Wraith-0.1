/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.U_2912_j;
import lightning.product.CompoundTagArgument;
import lightning.product.V_3157_k;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.h_4126_t;
import lightning.product.EntitySummonArgument;
import lightning.product.t_5_h;
import lightning.product.u_1579_Y;
import lightning.product.y_2498_m;

public class o_505_N {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.summon.failed"));
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.summon.failed.uuid"));
    private static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.summon.invalidPosition"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("summon").requires(p_198740_0_ -> p_198740_0_.n_1700_B(2))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("entity", EntitySummonArgument.n_1700_B()).suggests(h_4126_t.P_1922_E).executes(p_198738_0_ -> o_505_N.n_1700_B((y_2498_m)p_198738_0_.getSource(), EntitySummonArgument.n_1700_B((CommandContext<y_2498_m>)p_198738_0_, "entity"), ((y_2498_m)p_198738_0_.getSource()).P_4830_p(), new U_2912_j(), true))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("pos", u_1579_Y.n_1700_B()).executes(p_198735_0_ -> o_505_N.n_1700_B((y_2498_m)p_198735_0_.getSource(), EntitySummonArgument.n_1700_B((CommandContext<y_2498_m>)p_198735_0_, "entity"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198735_0_, "pos"), new U_2912_j(), true))).then(Q_2241_p.n_1700_B("nbt", CompoundTagArgument.n_1700_B()).executes(p_198739_0_ -> o_505_N.n_1700_B((y_2498_m)p_198739_0_.getSource(), EntitySummonArgument.n_1700_B((CommandContext<y_2498_m>)p_198739_0_, "entity"), u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198739_0_, "pos"), CompoundTagArgument.n_1700_B(p_198739_0_, "nbt"), false))))));
    }

    private static int n_1700_B(y_2498_m source, g_2336_b type, e_2866_D pos, U_2912_j nbt, boolean randomizeProperties) throws CommandSyntaxException {
        c_1514_x blockpos = new c_1514_x(pos);
        if (!b_4507_u.h_1847_R(blockpos)) {
            throw R_4764_Y.create();
        }
        U_2912_j compoundnbt = nbt.v_4262_N();
        compoundnbt.n_1700_B("id", type.toString());
        e_3591_l serverworld = source.h_1847_R();
        N_4263_v entity = t_5_h.n_1700_B(compoundnbt, serverworld, p_218914_1_ -> {
            p_218914_1_.J_1907_R(pos.J_1907_R, pos.R_4764_Y, pos.G_564_y, p_218914_1_.p_178_J, p_218914_1_.f_4016_n);
            return p_218914_1_;
        });
        if (entity == null) {
            throw n_1700_B.create();
        }
        if (randomizeProperties && entity instanceof Z_530_i) {
            ((Z_530_i)entity).n_1700_B(source.h_1847_R(), source.h_1847_R().J_1907_R(entity.b_2312_j()), a_3160_D.h_1847_R, (V_3157_k)null, null);
        }
        if (!serverworld.t_148_a(entity)) {
            throw J_1907_R.create();
        }
        source.n_1700_B(new F_2904_S("commands.summon.success", entity.c_()), true);
        return 1;
    }
}


