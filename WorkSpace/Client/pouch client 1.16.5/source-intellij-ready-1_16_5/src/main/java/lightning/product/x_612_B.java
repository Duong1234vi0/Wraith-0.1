/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 */
package lightning.product;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import lightning.product.D_4024_W;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;

public class x_612_B
extends o_2341_D {
    public static String J_1907_R = null;

    public x_612_B() {
        super("autocontract", "act");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(ctx -> {
            String current = J_1907_R;
            MutableComponent msg = new U_2871_b("\u0422\u0435\u043a\u0443\u0449\u0430\u044f \u0446\u0435\u043b\u044c: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)).n_1700_B(new U_2871_b(current == null ? "\u043d\u0435 \u0437\u0430\u0434\u0430\u043d\u043e" : current).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)));
            v_1900_v.n_1700_B(msg, new Object[0]);
            return 1;
        });
        builder.then(x_612_B.n_1700_B("nickname", StringArgumentType.greedyString()).executes(ctx -> {
            String nickname;
            J_1907_R = nickname = (String)ctx.getArgument("nickname", String.class);
            MutableComponent msg = new U_2871_b("\u0426\u0435\u043b\u044c \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0430: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)).n_1700_B(new U_2871_b(nickname).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)));
            v_1900_v.n_1700_B(msg, new Object[0]);
            return 1;
        }));
    }
}


