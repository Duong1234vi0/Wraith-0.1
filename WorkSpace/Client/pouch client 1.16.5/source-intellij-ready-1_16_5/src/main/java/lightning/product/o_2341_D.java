/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Arrays;
import java.util.List;
import lightning.product.D_4024_W;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.v_1900_v;

public abstract class o_2341_D {
    public List<String> n_1700_B;

    public o_2341_D(String ... command) {
        this.n_1700_B = Arrays.asList(command);
    }

    public abstract void n_1700_B(LiteralArgumentBuilder<V_4217_p> var1);

    public static <T> RequiredArgumentBuilder<V_4217_p, T> n_1700_B(String name, ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument((String)name, type);
    }

    public static LiteralArgumentBuilder<V_4217_p> n_1700_B(String name) {
        return LiteralArgumentBuilder.literal((String)name);
    }

    public void n_1700_B(CommandDispatcher<V_4217_p> dispatcher) {
        for (String name : this.n_1700_B) {
            LiteralArgumentBuilder builder = LiteralArgumentBuilder.literal((String)name);
            this.n_1700_B((LiteralArgumentBuilder<V_4217_p>)builder);
            dispatcher.register(builder);
        }
    }

    public String n_1700_B() {
        return this.n_1700_B.get(0);
    }

    protected int n_1700_B(CommandContext<V_4217_p> ctx) {
        String input = ctx.getInput();
        MutableComponent errorMsg = new U_2871_b("\u041d\u0435\u0432\u0435\u0440\u043d\u043e \u0443\u043a\u0430\u0437\u0430\u043d\u044b \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u044b - \"" + input + "\"").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p));
        v_1900_v.n_1700_B(errorMsg, new Object[0]);
        MutableComponent helpMsg = new U_2871_b("\u0414\u043b\u044f \u043f\u043e\u043c\u043e\u0449\u0438 - .help").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p));
        v_1900_v.n_1700_B(helpMsg, new Object[0]);
        return 1;
    }
}


