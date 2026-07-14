/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import lightning.product.A_2352_Z;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.y_2498_m;

public class i_4673_G {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        final LiteralArgumentBuilder literalargumentbuilder = (LiteralArgumentBuilder)Q_2241_p.n_1700_B("gamerule").requires(p_198491_0_ -> p_198491_0_.n_1700_B(2));
        A_2352_Z.n_1700_B(new A_2352_Z.G_564_y(){

            @Override
            public <T extends A_2352_Z.w_1484_f<T>> void R_4764_Y(A_2352_Z.u_1723_Y<T> key, A_2352_Z.v_4262_N<T> type) {
                literalargumentbuilder.then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B(key.n_1700_B()).executes(p_223483_1_ -> i_4673_G.n_1700_B((y_2498_m)p_223483_1_.getSource(), key))).then(type.n_1700_B("value").executes(p_223482_1_ -> i_4673_G.n_1700_B((CommandContext<y_2498_m>)p_223482_1_, key))));
            }
        });
        dispatcher.register(literalargumentbuilder);
    }

    private static <T extends A_2352_Z.w_1484_f<T>> int n_1700_B(CommandContext<y_2498_m> p_223485_0_, A_2352_Z.u_1723_Y<T> p_223485_1_) {
        y_2498_m commandsource = (y_2498_m)p_223485_0_.getSource();
        T t = commandsource.w_1457_N().y_1700_S().n_1700_B(p_223485_1_);
        ((A_2352_Z.w_1484_f)t).J_1907_R(p_223485_0_, "value");
        commandsource.n_1700_B(new F_2904_S("commands.gamerule.set", p_223485_1_.n_1700_B(), ((A_2352_Z.w_1484_f)t).toString()), true);
        return ((A_2352_Z.w_1484_f)t).R_4764_Y();
    }

    private static <T extends A_2352_Z.w_1484_f<T>> int n_1700_B(y_2498_m p_223486_0_, A_2352_Z.u_1723_Y<T> p_223486_1_) {
        T t = p_223486_0_.w_1457_N().y_1700_S().n_1700_B(p_223486_1_);
        p_223486_0_.n_1700_B(new F_2904_S("commands.gamerule.query", p_223486_1_.n_1700_B(), ((A_2352_Z.w_1484_f)t).toString()), false);
        return ((A_2352_Z.w_1484_f)t).R_4764_Y();
    }
}

