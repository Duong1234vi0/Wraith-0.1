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
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.e_3847_T;
import lightning.product.g_2336_b;
import lightning.product.h_4126_t;
import lightning.product.ResourceLocationArgument;
import lightning.product.k_594_Q;
import lightning.product.y_2498_m;

public class LocateBiomeCommand {
    public static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(p_241052_0_ -> new F_2904_S("commands.locatebiome.invalid", p_241052_0_));
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_241050_0_ -> new F_2904_S("commands.locatebiome.notFound", p_241050_0_));

    public static void n_1700_B(CommandDispatcher<y_2498_m> p_241046_0_) {
        p_241046_0_.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("locatebiome").requires(p_241048_0_ -> p_241048_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("biome", ResourceLocationArgument.n_1700_B()).suggests(h_4126_t.G_564_y).executes(p_241047_0_ -> LocateBiomeCommand.n_1700_B((y_2498_m)p_241047_0_.getSource(), (g_2336_b)p_241047_0_.getArgument("biome", g_2336_b.class)))));
    }

    private static int n_1700_B(y_2498_m p_241049_0_, g_2336_b p_241049_1_) throws CommandSyntaxException {
        k_594_Q biome = (k_594_Q)p_241049_0_.w_1457_N().g_4106_L().J_1907_R(V_3137_a.PlayerInfo).J_1907_R(p_241049_1_).orElseThrow(() -> n_1700_B.create((Object)p_241049_1_));
        c_1514_x blockpos = new c_1514_x(p_241049_0_.P_4830_p());
        c_1514_x blockpos1 = p_241049_0_.h_1847_R().n_1700_B(biome, blockpos, 6400, 8);
        String s = p_241049_1_.toString();
        if (blockpos1 == null) {
            throw J_1907_R.create((Object)s);
        }
        return e_3847_T.n_1700_B(p_241049_0_, s, blockpos, blockpos1, "commands.locatebiome.success");
    }
}


