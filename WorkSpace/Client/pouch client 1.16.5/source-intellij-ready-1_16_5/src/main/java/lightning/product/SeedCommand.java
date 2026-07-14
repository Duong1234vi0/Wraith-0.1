/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.Q_2241_p;
import lightning.product.U_2871_b;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.ComponentUtils;
import lightning.product.y_2498_m;

public class SeedCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> p_241067_0_, boolean p_241067_1_) {
        p_241067_0_.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("seed").requires(p_198673_1_ -> !p_241067_1_ || p_198673_1_.n_1700_B(2))).executes(p_198672_0_ -> {
            long i = ((y_2498_m)p_198672_0_.getSource()).h_1847_R().n_1700_B();
            MutableComponent itextcomponent = ComponentUtils.n_1700_B(new U_2871_b(String.valueOf(i)).n_1700_B(p_211752_2_ -> p_211752_2_.n_1700_B(D_4024_W.u_2550_I).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.u_1723_Y, String.valueOf(i))).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new F_2904_S("chat.copy.click"))).n_1700_B(String.valueOf(i))));
            ((y_2498_m)p_198672_0_.getSource()).n_1700_B(new F_2904_S("commands.seed.success", itextcomponent), false);
            return (int)i;
        }));
    }
}


