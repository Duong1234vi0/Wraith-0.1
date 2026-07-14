/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.i_4556_r;
import lightning.product.y_2498_m;

public class KillCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("kill").requires(p_198521_0_ -> p_198521_0_.n_1700_B(2))).executes(p_198520_0_ -> KillCommand.n_1700_B((y_2498_m)p_198520_0_.getSource(), (Collection<? extends N_4263_v>)ImmutableList.of((Object)((y_2498_m)p_198520_0_.getSource()).M_182_A())))).then(Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).executes(p_229810_0_ -> KillCommand.n_1700_B((y_2498_m)p_229810_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_229810_0_, "targets")))));
    }

    private static int n_1700_B(y_2498_m source, Collection<? extends N_4263_v> targets) {
        for (N_4263_v n_4263_v : targets) {
            n_4263_v.e_1992_r();
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.kill.success.single", targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.kill.success.multiple", targets.size()), true);
        }
        return targets.size();
    }
}


