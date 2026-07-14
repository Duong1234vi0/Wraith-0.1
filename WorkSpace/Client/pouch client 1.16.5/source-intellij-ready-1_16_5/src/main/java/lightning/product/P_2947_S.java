/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 */
package lightning.product;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import lightning.product.V_4217_p;
import lightning.product.o_2341_D;
import lightning.product.y_4642_Y;

public class P_2947_S
extends o_2341_D {
    public P_2947_S() {
        super("p", "panic");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(ctx -> {
            y_4642_Y.n_1700_B();
            return 1;
        });
    }
}

