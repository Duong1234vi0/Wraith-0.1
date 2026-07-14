/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import java.util.Collection;
import lightning.product.F_2904_S;
import lightning.product.ServerFunctionManager;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.c_853_z;
import lightning.product.r_3448_Z;
import lightning.product.y_2498_m;

public class FunctionCommand {
    public static final SuggestionProvider<y_2498_m> n_1700_B = (p_198477_0_, p_198477_1_) -> {
        ServerFunctionManager functionmanager = ((y_2498_m)p_198477_0_.getSource()).w_1457_N().RealmsWorldResetDto();
        V_4217_p.n_1700_B(functionmanager.u_1723_Y(), p_198477_1_, "#");
        return V_4217_p.n_1700_B(functionmanager.P_1922_E(), p_198477_1_);
    };

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("function").requires(p_198480_0_ -> p_198480_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("name", c_853_z.n_1700_B()).suggests(n_1700_B).executes(p_198479_0_ -> FunctionCommand.n_1700_B((y_2498_m)p_198479_0_.getSource(), c_853_z.n_1700_B((CommandContext<y_2498_m>)p_198479_0_, "name")))));
    }

    private static int n_1700_B(y_2498_m source, Collection<r_3448_Z> functions) {
        int i = 0;
        for (r_3448_Z functionobject : functions) {
            i += source.w_1457_N().RealmsWorldResetDto().n_1700_B(functionobject, source.s_956_w().R_4764_Y(2));
        }
        if (functions.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.function.success.single", i, functions.iterator().next().n_1700_B()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.function.success.multiple", i, functions.size()), true);
        }
        return i;
    }
}


