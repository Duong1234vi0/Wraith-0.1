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
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;
import lightning.product.y_4642_Y;

public class L_3570_A
extends o_2341_D {
    public L_3570_A() {
        super("self");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(ctx -> {
            if (y_4642_Y.R_4764_Y()) {
                y_4642_Y.J_1907_R();
                v_1900_v.n_1700_B(new U_2871_b("\u0420\u0435\u0436\u0438\u043c \u043f\u0430\u043d\u0438\u043a\u0438 \u0434\u0435\u0430\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u043d. \u041a\u043b\u0438\u0435\u043d\u0442 \u0440\u0430\u0437\u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u0430\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I)), new Object[0]);
            } else {
                v_1900_v.n_1700_B(new U_2871_b("\u0420\u0435\u0436\u0438\u043c \u043f\u0430\u043d\u0438\u043a\u0438 \u043d\u0435 \u0430\u043a\u0442\u0438\u0432\u0435\u043d").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
            }
            return 1;
        });
    }
}

