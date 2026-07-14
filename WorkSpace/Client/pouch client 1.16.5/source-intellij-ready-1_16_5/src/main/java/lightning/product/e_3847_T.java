/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Map;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.StructureFeature;
import lightning.product.MutableComponent;
import lightning.product.Q_2241_p;
import lightning.product.c_1514_x;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.u_530_F;
import lightning.product.ComponentUtils;
import lightning.product.y_2498_m;

public class e_3847_T {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.locate.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        LiteralArgumentBuilder literalargumentbuilder = (LiteralArgumentBuilder)Q_2241_p.n_1700_B("locate").requires(p_198533_0_ -> p_198533_0_.n_1700_B(2));
        for (Map.Entry entry : StructureFeature.n_1700_B.entrySet()) {
            literalargumentbuilder = (LiteralArgumentBuilder)literalargumentbuilder.then(Q_2241_p.n_1700_B((String)entry.getKey()).executes(p_241056_1_ -> e_3847_T.n_1700_B((y_2498_m)p_241056_1_.getSource(), (StructureFeature)entry.getValue())));
        }
        dispatcher.register(literalargumentbuilder);
    }

    private static int n_1700_B(y_2498_m p_241053_0_, StructureFeature<?> p_241053_1_) throws CommandSyntaxException {
        c_1514_x blockpos = new c_1514_x(p_241053_0_.P_4830_p());
        c_1514_x blockpos1 = p_241053_0_.h_1847_R().n_1700_B(p_241053_1_, blockpos, 100, false);
        if (blockpos1 == null) {
            throw n_1700_B.create();
        }
        return e_3847_T.n_1700_B(p_241053_0_, p_241053_1_.v_4262_N(), blockpos, blockpos1, "commands.locate.success");
    }

    public static int n_1700_B(y_2498_m p_241054_0_, String p_241054_1_, c_1514_x p_241054_2_, c_1514_x p_241054_3_, String p_241054_4_) {
        int i = u_530_F.G_564_y(e_3847_T.n_1700_B(p_241054_2_.getX(), p_241054_2_.getZ(), p_241054_3_.getX(), p_241054_3_.getZ()));
        MutableComponent itextcomponent = ComponentUtils.n_1700_B(new F_2904_S("chat.coordinates", p_241054_3_.getX(), "~", p_241054_3_.getZ())).n_1700_B(p_241055_1_ -> p_241055_1_.n_1700_B(D_4024_W.u_2550_I).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.G_564_y, "/tp @s " + p_241054_3_.getX() + " ~ " + p_241054_3_.getZ())).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new F_2904_S("chat.coordinates.tooltip"))));
        p_241054_0_.n_1700_B(new F_2904_S(p_241054_4_, p_241054_1_, itextcomponent, i), false);
        return i;
    }

    private static float n_1700_B(int x1, int z1, int x2, int z2) {
        int i = x2 - x1;
        int j = z2 - z1;
        return u_530_F.R_4764_Y((float)(i * i + j * j));
    }
}


