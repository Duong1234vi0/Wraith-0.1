/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import lightning.product.D_4024_W;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.ClientBootstrap;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;

public class F_518_D
extends o_2341_D {
    public F_518_D() {
        super("prefix");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(x$0 -> this.n_1700_B((CommandContext<V_4217_p>)x$0));
        builder.then(F_518_D.n_1700_B("value", StringArgumentType.greedyString()).executes(ctx -> {
            String newPrefix = (String)ctx.getArgument("value", String.class);
            ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B(newPrefix);
            MutableComponent msg = new U_2871_b("\u041f\u0440\u0435\u0444\u0438\u043a\u0441 \u0434\u043b\u044f \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u044f \u043a\u043e\u043c\u0430\u043d\u0434 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d \u043d\u0430 '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)).n_1700_B(new U_2871_b(newPrefix).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A))).n_1700_B(new U_2871_b("'").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
            v_1900_v.n_1700_B(msg, new Object[0]);
            return 1;
        }));
    }
}



