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
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lightning.product.D_4024_W;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.ServerData;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftAccess;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.ClientBootstrap;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;
import lightning.product.y_2447_C;

public class I_1407_m
extends o_2341_D
implements ArgumentType<String>,
MinecraftAccess {
    public I_1407_m() {
        super("waypoint", "way");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(x$0 -> this.n_1700_B((CommandContext<V_4217_p>)x$0));
        builder.then(I_1407_m.n_1700_B("add").then(((RequiredArgumentBuilder)I_1407_m.n_1700_B("name", this).executes(ctx -> {
            String name = (String)ctx.getArgument("name", String.class);
            if (!this.J_1907_R(name)) {
                return this.n_1700_B((CommandContext<V_4217_p>)ctx);
            }
            if (I_1407_m.c_3005_b.Y_259_p == null) {
                return this.n_1700_B((CommandContext<V_4217_p>)ctx);
            }
            double px = I_1407_m.c_3005_b.Y_259_p.O_3598_v();
            double py = I_1407_m.c_3005_b.Y_259_p.X_2960_b();
            double pz = I_1407_m.c_3005_b.Y_259_p.l_2647_k();
            String serverKey = this.R_4764_Y();
            this.J_1907_R().n_1700_B(serverKey, name, px, py, pz);
            MutableComponent prefix = new U_2871_b("\u0422\u043e\u0447\u043a\u0430 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            MutableComponent nameText = new U_2871_b(name).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
            MutableComponent suffix = new U_2871_b("' \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0430!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(prefix).n_1700_B(nameText).n_1700_B(suffix), new Object[0]);
            return 1;
        })).then(I_1407_m.n_1700_B("x", new n_1700_B()).then(I_1407_m.n_1700_B("y", new n_1700_B()).then(I_1407_m.n_1700_B("z", new n_1700_B()).executes(ctx -> {
            String name = (String)ctx.getArgument("name", String.class);
            double x = (Double)ctx.getArgument("x", Double.class);
            double y = (Double)ctx.getArgument("y", Double.class);
            double z = (Double)ctx.getArgument("z", Double.class);
            String serverKey = this.R_4764_Y();
            this.J_1907_R().n_1700_B(serverKey, name, x, y, z);
            MutableComponent prefix = new U_2871_b("\u0422\u043e\u0447\u043a\u0430 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            MutableComponent nameText = new U_2871_b(name).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
            MutableComponent suffix = new U_2871_b("' \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0430!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(prefix).n_1700_B(nameText).n_1700_B(suffix), new Object[0]);
            return 1;
        }))))));
        builder.then(I_1407_m.n_1700_B("remove").then(I_1407_m.n_1700_B("name", this).suggests(this::n_1700_B).executes(ctx -> {
            String name = (String)ctx.getArgument("name", String.class);
            String serverKey = this.R_4764_Y();
            boolean removed = this.J_1907_R().n_1700_B(serverKey, name);
            if (removed) {
                MutableComponent prefix = new U_2871_b("\u0422\u043e\u0447\u043a\u0430 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent nameText = new U_2871_b(name).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent suffix = new U_2871_b("' \u0443\u0434\u0430\u043b\u0435\u043d\u0430!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(prefix).n_1700_B(nameText).n_1700_B(suffix), new Object[0]);
            }
            return 1;
        })));
        builder.then(I_1407_m.n_1700_B("clear").executes(ctx -> {
            String serverKey = this.R_4764_Y();
            this.J_1907_R().J_1907_R(serverKey);
            v_1900_v.n_1700_B(new U_2871_b("\u0412\u0441\u0435 \u0442\u043e\u0447\u043a\u0438 \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0443\u0434\u0430\u043b\u0435\u043d\u044b!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
            return 1;
        }));
        builder.then(I_1407_m.n_1700_B("list").executes(ctx -> {
            String serverKey = this.R_4764_Y();
            List<y_2447_C.n_1700_B> list = this.J_1907_R().n_1700_B(serverKey);
            if (list.isEmpty()) {
                v_1900_v.n_1700_B(new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0442\u043e\u0447\u0435\u043a \u043f\u0443\u0441\u0442!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
                return 1;
            }
            for (y_2447_C.n_1700_B wp : list) {
                v_1900_v.n_1700_B(new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0442\u043e\u0447\u0435\u043a:").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
                MutableComponent name = new U_2871_b(wp.n_1700_B()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent xLabel = new U_2871_b(" {x: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent xVal = new U_2871_b(String.format("%.1f", wp.J_1907_R()).replace(",", ".")).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent yLabel = new U_2871_b(", y: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent yVal = new U_2871_b(String.format("%.1f", wp.R_4764_Y()).replace(",", ".")).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent zLabel = new U_2871_b(", z: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent zVal = new U_2871_b(String.format("%.1f", wp.G_564_y()).replace(",", ".")).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent closeBrace = new U_2871_b("}").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent ipLabel = new U_2871_b(" {ip: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent ipVal = new U_2871_b(serverKey).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent ipClose = new U_2871_b("} ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                String removeCmd = ".way remove " + wp.n_1700_B();
                MutableComponent removeBtn = new U_2871_b("[\u0423\u0434\u0430\u043b\u0438\u0442\u044c]").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, removeCmd)).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("\u041a\u043b\u0438\u043a \u0434\u043b\u044f \u0443\u0434\u0430\u043b\u0435\u043d\u0438\u044f \u0442\u043e\u0447\u043a\u0438").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)))));
                v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(name).n_1700_B(xLabel).n_1700_B(xVal).n_1700_B(yLabel).n_1700_B(yVal).n_1700_B(zLabel).n_1700_B(zVal).n_1700_B(closeBrace).n_1700_B(ipLabel).n_1700_B(ipVal).n_1700_B(ipClose).n_1700_B(new U_2871_b(" ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f))).n_1700_B(removeBtn), new Object[0]);
            }
            v_1900_v.n_1700_B(new U_2871_b("\u0412\u0441\u0435\u0433\u043e: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)).n_1700_B(new U_2871_b(String.valueOf(list.size())).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A))), new Object[0]);
            return 1;
        }));
    }

    public String n_1700_B(StringReader reader) throws CommandSyntaxException {
        return reader.readString();
    }

    private boolean J_1907_R(String name) {
        if (name == null) {
            return false;
        }
        String s = name.trim();
        if (s.length() > 8) {
            s = s.substring(0, 8);
        }
        return s.matches("^[A-Za-z0-9]{3,8}$");
    }

    private y_2447_C J_1907_R() {
        return ClientBootstrap.Y_601_j().s_956_w();
    }

    private String R_4764_Y() {
        ServerData data = c_3005_b.t_4043_B();
        return data != null ? data.J_1907_R : "singleplayer";
    }

    private CompletableFuture<Suggestions> n_1700_B(CommandContext<V_4217_p> ctx, SuggestionsBuilder builder) {
        String serverKey = this.R_4764_Y();
        List<String> names = this.J_1907_R().n_1700_B(serverKey).stream().map(y_2447_C.n_1700_B::n_1700_B).collect(Collectors.toList());
        return V_4217_p.J_1907_R(names, builder);
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



