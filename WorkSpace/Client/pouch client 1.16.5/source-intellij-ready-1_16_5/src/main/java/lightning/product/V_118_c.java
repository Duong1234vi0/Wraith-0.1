/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import lightning.product.D_4024_W;
import lightning.product.MutableComponent;
import lightning.product.N_3268_u;
import lightning.product.T_2915_h;
import lightning.product.T_3952_j;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_408_T;
import lightning.product.MinecraftAccess;
import lightning.product.c_1514_x;
import lightning.product.o_2341_D;
import lightning.product.Items;
import lightning.product.v_1900_v;

public class V_118_c
extends o_2341_D
implements MinecraftAccess {
    public V_118_c() {
        super("vclip");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(x$0 -> this.n_1700_B((CommandContext<V_4217_p>)x$0));
        builder.then(V_118_c.n_1700_B("value", new n_1700_B()).executes(context -> {
            if (V_118_c.c_3005_b.Y_259_p == null || V_118_c.c_3005_b.Y_601_j == null) {
                v_1900_v.n_1700_B(new U_2871_b("\u0418\u0433\u0440\u043e\u043a \u0438\u043b\u0438 \u043c\u0438\u0440 \u043d\u0435 \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u044b!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)), new Object[0]);
                return 1;
            }
            double value = (Double)context.getArgument("value", Double.class);
            if (value != 0.0) {
                this.n_1700_B(value);
            } else {
                v_1900_v.n_1700_B(new U_2871_b("\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u0432\u044b\u043f\u043e\u043b\u043d\u0438\u0442\u044c \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u044e.").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)), new Object[0]);
            }
            return 1;
        }));
        builder.then(V_118_c.n_1700_B("up").executes(context -> {
            if (V_118_c.c_3005_b.Y_259_p == null || V_118_c.c_3005_b.Y_601_j == null) {
                v_1900_v.n_1700_B(new U_2871_b("\u0418\u0433\u0440\u043e\u043a \u0438\u043b\u0438 \u043c\u0438\u0440 \u043d\u0435 \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u044b!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)), new Object[0]);
                return 1;
            }
            double blocks = this.n_1700_B(true, 100);
            if (blocks == 0.0) {
                v_1900_v.n_1700_B(new U_2871_b("\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043d\u0430\u0439\u0442\u0438 \u0441\u0432\u043e\u0431\u043e\u0434\u043d\u043e\u0435 \u043f\u0440\u043e\u0441\u0442\u0440\u0430\u043d\u0441\u0442\u0432\u043e \u0441\u0432\u0435\u0440\u0445\u0443!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)), new Object[0]);
                return 1;
            }
            this.n_1700_B(blocks);
            return 1;
        }));
        builder.then(V_118_c.n_1700_B("down").executes(context -> {
            if (V_118_c.c_3005_b.Y_259_p == null || V_118_c.c_3005_b.Y_601_j == null) {
                v_1900_v.n_1700_B(new U_2871_b("\u0418\u0433\u0440\u043e\u043a \u0438\u043b\u0438 \u043c\u0438\u0440 \u043d\u0435 \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u044b!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)), new Object[0]);
                return 1;
            }
            double blocks = this.n_1700_B(false, 100);
            if (blocks == 0.0) {
                v_1900_v.n_1700_B(new U_2871_b("\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043d\u0430\u0439\u0442\u0438 \u0441\u0432\u043e\u0431\u043e\u0434\u043d\u043e\u0435 \u043f\u0440\u043e\u0441\u0442\u0440\u0430\u043d\u0441\u0442\u0432\u043e \u0441\u043d\u0438\u0437\u0443!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)), new Object[0]);
                return 1;
            }
            this.n_1700_B(blocks);
            return 1;
        }));
        builder.then(V_118_c.n_1700_B("bd").executes(context -> {
            if (V_118_c.c_3005_b.Y_259_p == null || V_118_c.c_3005_b.Y_601_j == null) {
                v_1900_v.n_1700_B(new U_2871_b("\u0418\u0433\u0440\u043e\u043a \u0438\u043b\u0438 \u043c\u0438\u0440 \u043d\u0435 \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u044b!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)), new Object[0]);
                return 1;
            }
            int playerX = (int)Math.floor(V_118_c.c_3005_b.Y_259_p.O_3598_v());
            int playerZ = (int)Math.floor(V_118_c.c_3005_b.Y_259_p.l_2647_k());
            int startY = (int)Math.floor(V_118_c.c_3005_b.Y_259_p.X_2960_b());
            double newY = -1.0;
            for (int y = Math.min(startY, 5); y >= 0; --y) {
                c_1514_x checkPos = new c_1514_x(playerX, y, playerZ);
                if (V_118_c.c_3005_b.Y_601_j.getBlockState(checkPos).J_1907_R() != a_3742_W.Z_875_P) continue;
                newY = y - 25;
                break;
            }
            double blocks = newY - V_118_c.c_3005_b.Y_259_p.X_2960_b();
            this.n_1700_B(blocks);
            MutableComponent message = new U_2871_b("\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u043d \u043f\u043e\u0434 \u0431\u0435\u0434\u0440\u043e\u043a (Y: " + String.format("%.2f", newY) + ")").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I));
            v_1900_v.n_1700_B(message, new Object[0]);
            return 1;
        }));
    }

    private T_2915_h n_1700_B(c_1514_x pos) {
        return V_118_c.c_3005_b.Y_601_j.getBlockState(pos).J_1907_R();
    }

    private boolean J_1907_R(c_1514_x pos) {
        return V_118_c.c_3005_b.Y_601_j.getBlockState(pos).v_4262_N() || !V_118_c.c_3005_b.Y_601_j.getBlockState(pos).R_4764_Y().R_4764_Y();
    }

    private boolean R_4764_Y(c_1514_x pos) {
        return V_118_c.c_3005_b.Y_601_j.getBlockState(pos).R_4764_Y().R_4764_Y();
    }

    private double n_1700_B(boolean up, int maximum) {
        c_1514_x pos = V_118_c.c_3005_b.Y_259_p.b_2312_j();
        if (up) {
            for (int i = maximum; i >= 2; --i) {
                c_1514_x feetPos = pos.add(0, i, 0);
                c_1514_x headPos = pos.add(0, i + 1, 0);
                c_1514_x floorPos = pos.add(0, i - 1, 0);
                if (!this.J_1907_R(feetPos) || !this.J_1907_R(headPos) || !this.R_4764_Y(floorPos)) continue;
                return i;
            }
        } else {
            for (int i = -maximum; i <= -2; ++i) {
                c_1514_x feetPos = pos.add(0, i, 0);
                c_1514_x headPos = pos.add(0, i + 1, 0);
                c_1514_x floorPos = pos.add(0, i - 1, 0);
                if (this.n_1700_B(floorPos) == a_3742_W.Z_875_P || !this.J_1907_R(feetPos) || !this.J_1907_R(headPos) || !this.R_4764_Y(floorPos)) continue;
                return i;
            }
        }
        return 0.0;
    }

    private void n_1700_B(double blocks) {
        boolean elytraEquipped;
        if (blocks == 0.0) {
            return;
        }
        int elytraSlot = this.J_1907_R();
        boolean hasElytra = elytraSlot != -1;
        boolean bl = elytraEquipped = elytraSlot == -2;
        if (hasElytra && !elytraEquipped) {
            V_118_c.c_3005_b.w_1457_N.windowClick(0, elytraSlot, 0, a_408_T.n_1700_B, V_118_c.c_3005_b.Y_259_p);
            V_118_c.c_3005_b.w_1457_N.windowClick(0, 6, 0, a_408_T.n_1700_B, V_118_c.c_3005_b.Y_259_p);
        }
        V_118_c.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u(false));
        V_118_c.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u(false));
        if (hasElytra || elytraEquipped) {
            V_118_c.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new T_3952_j(V_118_c.c_3005_b.Y_259_p, T_3952_j.n_1700_B.t_148_a));
        }
        double newY = V_118_c.c_3005_b.Y_259_p.X_2960_b() + blocks;
        V_118_c.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u.n_1700_B(V_118_c.c_3005_b.Y_259_p.O_3598_v(), newY, V_118_c.c_3005_b.Y_259_p.l_2647_k(), false));
        if (hasElytra || elytraEquipped) {
            V_118_c.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new T_3952_j(V_118_c.c_3005_b.Y_259_p, T_3952_j.n_1700_B.t_148_a));
        }
        V_118_c.c_3005_b.Y_259_p.J_1907_R(V_118_c.c_3005_b.Y_259_p.O_3598_v(), newY, V_118_c.c_3005_b.Y_259_p.l_2647_k());
        if (hasElytra && !elytraEquipped) {
            V_118_c.c_3005_b.w_1457_N.windowClick(0, 6, 0, a_408_T.n_1700_B, V_118_c.c_3005_b.Y_259_p);
            V_118_c.c_3005_b.w_1457_N.windowClick(0, elytraSlot, 0, a_408_T.n_1700_B, V_118_c.c_3005_b.Y_259_p);
        }
        String blockUnit = Math.abs(blocks) > 1.0 ? "\u0431\u043b\u043e\u043a\u043e\u0432" : "\u0431\u043b\u043e\u043a";
        MutableComponent message = new U_2871_b(String.format("\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u043d \u043d\u0430 %.1f %s \u043f\u043e \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u0438", blocks, blockUnit)).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I));
        v_1900_v.n_1700_B(message, new Object[0]);
    }

    private int J_1907_R() {
        for (Z_1993_T stack : V_118_c.c_3005_b.Y_259_p.u_55_V()) {
            if (stack.J_1907_R() != Items.NyliumBlock) continue;
            return -2;
        }
        int slot = -1;
        for (int i = 0; i < 36; ++i) {
            Z_1993_T s = V_118_c.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (s.J_1907_R() != Items.NyliumBlock) continue;
            slot = i;
            break;
        }
        if (slot != -1 && slot < 9) {
            slot += 36;
        }
        return slot;
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
                throw new DynamicCommandExceptionType(value -> new U_2871_b("\u041d\u0435\u0432\u0435\u0440\u043d\u044b\u0439 \u0444\u043e\u0440\u043c\u0430\u0442 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f: " + String.valueOf(value))).create((Object)reader.getString());
            }
        }

        public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
            return this.n_1700_B(stringReader);
        }
    }
}



