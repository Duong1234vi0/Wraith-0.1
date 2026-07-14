/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import lightning.product.D_4024_W;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftAccess;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;

public class O_1309_Q
extends o_2341_D
implements ArgumentType<String>,
MinecraftAccess {
    public static boolean J_1907_R = false;
    public static double R_4764_Y = 0.0;
    public static double G_564_y = 0.0;

    public O_1309_Q() {
        super("gps");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(x$0 -> this.n_1700_B((CommandContext<V_4217_p>)x$0));
        builder.then(((RequiredArgumentBuilder)O_1309_Q.n_1700_B("x", new n_1700_B()).executes(context -> {
            String input = context.getInput();
            return this.n_1700_B((CommandContext<V_4217_p>)context);
        })).then(O_1309_Q.n_1700_B("z", new n_1700_B()).executes(context -> {
            double newX = (Double)context.getArgument("x", Double.class);
            double newZ = (Double)context.getArgument("z", Double.class);
            J_1907_R = true;
            R_4764_Y = newX;
            G_564_y = newZ;
            return 1;
        })));
        builder.then(O_1309_Q.n_1700_B("off").executes(context -> {
            J_1907_R = false;
            R_4764_Y = 0.0;
            G_564_y = 0.0;
            return 1;
        }));
        builder.then(O_1309_Q.n_1700_B("info").executes(context -> {
            if (!J_1907_R) {
                MutableComponent message = new U_2871_b("\u0421\u0435\u0439\u0447\u0430\u0441 GPS \u043e\u0442\u043a\u043b\u044e\u0447\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                v_1900_v.n_1700_B(message, new Object[0]);
            } else {
                MutableComponent prefix = new U_2871_b("\u0418\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u044f \u043e \u0442\u0435\u043a\u0443\u0449\u0435\u043c GPS: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent xLabel = new U_2871_b("x: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent xValue = new U_2871_b(String.format("%.1f", R_4764_Y).replace(",", ".")).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent zLabel = new U_2871_b(" z: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent zValue = new U_2871_b(String.format("%.1f", G_564_y).replace(",", ".")).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(prefix).n_1700_B(xLabel).n_1700_B(xValue).n_1700_B(zLabel).n_1700_B(zValue), new Object[0]);
            }
            return 1;
        }));
    }

    public String n_1700_B(StringReader reader) throws CommandSyntaxException {
        return reader.readString();
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }

    private static class n_1700_B
    implements ArgumentType<Double> {
        private n_1700_B() {
        }

        public Double n_1700_B(StringReader reader) throws CommandSyntaxException {
            try {
                return reader.readDouble();
            }
            catch (CommandSyntaxException e) {
                throw new DynamicCommandExceptionType(value -> new U_2871_b("\u041d\u0435\u0432\u0435\u0440\u043d\u044b\u0439 \u0444\u043e\u0440\u043c\u0430\u0442 \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b: " + String.valueOf(value))).create((Object)reader.getString());
            }
        }

        public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
            return this.n_1700_B(stringReader);
        }
    }
}



