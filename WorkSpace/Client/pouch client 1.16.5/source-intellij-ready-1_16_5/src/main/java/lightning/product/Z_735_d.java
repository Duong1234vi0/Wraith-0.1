/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 */
package lightning.product;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import lightning.product.D_4024_W;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.LogoutSpots;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;

public class Z_735_d
extends o_2341_D {
    public Z_735_d() {
        super("logout");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(ctx -> {
            v_1900_v.n_1700_B(new U_2871_b(".logout dir - \u043e\u0442\u043a\u0440\u044b\u0442\u044c \u043f\u0430\u043f\u043a\u0443 \u0441 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u044f\u043c\u0438").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
            return 1;
        });
        builder.then(Z_735_d.n_1700_B("dir").executes(ctx -> {
            LogoutSpots.h_1847_R();
            v_1900_v.n_1700_B(new U_2871_b("\u041f\u0430\u043f\u043a\u0430 LogoutSpots \u043e\u0442\u043a\u0440\u044b\u0442\u0430!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I)), new Object[0]);
            return 1;
        }));
    }
}


