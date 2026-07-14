/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.R_2450_T;
import lightning.product.y_2498_m;
import net.minecraft.server.G_564_y;

public class DifficultyCommand {
    private static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(p_208823_0_ -> new F_2904_S("commands.difficulty.failure", p_208823_0_));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        LiteralArgumentBuilder<y_2498_m> literalargumentbuilder = Q_2241_p.n_1700_B("difficulty");
        for (R_2450_T difficulty : R_2450_T.values()) {
            literalargumentbuilder.then(Q_2241_p.n_1700_B(difficulty.R_4764_Y()).executes(p_198347_1_ -> DifficultyCommand.n_1700_B((y_2498_m)p_198347_1_.getSource(), difficulty)));
        }
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)literalargumentbuilder.requires(p_198348_0_ -> p_198348_0_.n_1700_B(2))).executes(p_198346_0_ -> {
            R_2450_T difficulty1 = ((y_2498_m)p_198346_0_.getSource()).h_1847_R().x_607_J();
            ((y_2498_m)p_198346_0_.getSource()).n_1700_B(new F_2904_S("commands.difficulty.query", difficulty1.J_1907_R()), false);
            return difficulty1.n_1700_B();
        }));
    }

    public static int n_1700_B(y_2498_m source, R_2450_T difficulty) throws CommandSyntaxException {
        G_564_y minecraftserver = source.w_1457_N();
        if (minecraftserver.c_132_F().u_2550_I() == difficulty) {
            throw n_1700_B.create((Object)difficulty.R_4764_Y());
        }
        minecraftserver.n_1700_B(difficulty, true);
        source.n_1700_B(new F_2904_S("commands.difficulty.success", difficulty.J_1907_R()), true);
        return 0;
    }
}


