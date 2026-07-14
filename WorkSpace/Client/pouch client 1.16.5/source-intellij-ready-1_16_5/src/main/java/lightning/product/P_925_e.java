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
import java.io.File;
import java.io.IOException;
import lightning.product.D_4024_W;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.ClientBootstrap;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;

public class P_925_e
extends o_2341_D {
    public P_925_e() {
        super("lua");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(x$0 -> this.n_1700_B((CommandContext<V_4217_p>)x$0));
        builder.then(P_925_e.n_1700_B("dir").executes(context -> {
            try {
                File scriptsDir = ClientBootstrap.Y_601_j().h_1847_R().n_1700_B();
                if (!scriptsDir.exists()) {
                    scriptsDir.mkdirs();
                }
                Runtime.getRuntime().exec("explorer " + scriptsDir.getAbsolutePath());
                MutableComponent successMsg = new U_2871_b("\u041f\u0430\u043f\u043a\u0430 \u0441\u043e \u0441\u043a\u0440\u0438\u043f\u0442\u0430\u043c\u0438 \u043e\u0442\u043a\u0440\u044b\u0442\u0430!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I));
                v_1900_v.n_1700_B(successMsg, new Object[0]);
            }
            catch (IOException e) {
                MutableComponent errorMsg = new U_2871_b("\u041e\u0448\u0438\u0431\u043a\u0430 \u043e\u0442\u043a\u0440\u044b\u0442\u0438\u044f \u043f\u0430\u043f\u043a\u0438!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p));
                v_1900_v.n_1700_B(errorMsg, new Object[0]);
            }
            return 1;
        }));
        builder.then(P_925_e.n_1700_B("load").then(P_925_e.n_1700_B("name", StringArgumentType.greedyString()).executes(context -> {
            Object scriptName = (String)context.getArgument("name", String.class);
            if (!((String)scriptName).toLowerCase().endsWith(".java") && !((String)scriptName).toLowerCase().endsWith(".pouch")) {
                String pouchName = (String)scriptName + ".pouch";
                String javaName = (String)scriptName + ".java";
                File scriptsDir = ClientBootstrap.Y_601_j().h_1847_R().n_1700_B();
                scriptName = new File(scriptsDir, pouchName).exists() ? pouchName : (new File(scriptsDir, javaName).exists() ? javaName : pouchName);
            }
            if (ClientBootstrap.Y_601_j().h_1847_R().n_1700_B((String)scriptName)) {
                MutableComponent successMsg = new U_2871_b("\u0421\u043a\u0440\u0438\u043f\u0442 \"" + (String)scriptName + "\" \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I));
                v_1900_v.n_1700_B(successMsg, new Object[0]);
            } else {
                MutableComponent errorMsg = new U_2871_b("\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u0437\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c \u0441\u043a\u0440\u0438\u043f\u0442 \"" + (String)scriptName + "\"! \u041f\u0440\u043e\u0432\u0435\u0440\u044c\u0442\u0435 \u043a\u043e\u043d\u0441\u043e\u043b\u044c \u0434\u043b\u044f \u0434\u0435\u0442\u0430\u043b\u0435\u0439.").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p));
                v_1900_v.n_1700_B(errorMsg, new Object[0]);
            }
            return 1;
        })));
        builder.then(P_925_e.n_1700_B("reload").then(P_925_e.n_1700_B("name", StringArgumentType.greedyString()).executes(context -> {
            Object scriptName = (String)context.getArgument("name", String.class);
            if (!((String)scriptName).toLowerCase().endsWith(".java") && !((String)scriptName).toLowerCase().endsWith(".pouch")) {
                String pouchName = (String)scriptName + ".pouch";
                String javaName = (String)scriptName + ".java";
                File scriptsDir = ClientBootstrap.Y_601_j().h_1847_R().n_1700_B();
                scriptName = new File(scriptsDir, pouchName).exists() ? pouchName : (new File(scriptsDir, javaName).exists() ? javaName : pouchName);
            }
            if (ClientBootstrap.Y_601_j().h_1847_R().J_1907_R((String)scriptName)) {
                if (ClientBootstrap.Y_601_j().h_1847_R().R_4764_Y((String)scriptName)) {
                    MutableComponent successMsg = new U_2871_b("\u0421\u043a\u0440\u0438\u043f\u0442 \"" + (String)scriptName + "\" \u043f\u0435\u0440\u0435\u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I));
                    v_1900_v.n_1700_B(successMsg, new Object[0]);
                } else {
                    errorMsg = new U_2871_b("\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043f\u0435\u0440\u0435\u0437\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c \u0441\u043a\u0440\u0438\u043f\u0442 \"" + (String)scriptName + "\"!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p));
                    v_1900_v.n_1700_B(errorMsg, new Object[0]);
                }
            } else {
                errorMsg = new U_2871_b("\u0421\u043a\u0440\u0438\u043f\u0442 \"" + (String)scriptName + "\" \u043d\u0435 \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p));
                v_1900_v.n_1700_B(errorMsg, new Object[0]);
            }
            return 1;
        })));
        builder.then(P_925_e.n_1700_B("unload").then(P_925_e.n_1700_B("name", StringArgumentType.greedyString()).executes(context -> {
            Object scriptName = (String)context.getArgument("name", String.class);
            if (!((String)scriptName).toLowerCase().endsWith(".java") && !((String)scriptName).toLowerCase().endsWith(".pouch")) {
                String pouchName = (String)scriptName + ".pouch";
                String javaName = (String)scriptName + ".java";
                File scriptsDir = ClientBootstrap.Y_601_j().h_1847_R().n_1700_B();
                scriptName = new File(scriptsDir, pouchName).exists() ? pouchName : (new File(scriptsDir, javaName).exists() ? javaName : pouchName);
            }
            if (ClientBootstrap.Y_601_j().h_1847_R().J_1907_R((String)scriptName)) {
                ClientBootstrap.Y_601_j().h_1847_R().G_564_y((String)scriptName);
                MutableComponent successMsg = new U_2871_b("\u0421\u043a\u0440\u0438\u043f\u0442 \"" + (String)scriptName + "\" \u0432\u044b\u0433\u0440\u0443\u0436\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I));
                v_1900_v.n_1700_B(successMsg, new Object[0]);
            } else {
                MutableComponent errorMsg = new U_2871_b("\u0421\u043a\u0440\u0438\u043f\u0442 \"" + (String)scriptName + "\" \u043d\u0435 \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p));
                v_1900_v.n_1700_B(errorMsg, new Object[0]);
            }
            return 1;
        })));
        builder.then(P_925_e.n_1700_B("list").executes(context -> {
            String[] scripts = ClientBootstrap.Y_601_j().h_1847_R().J_1907_R();
            if (scripts.length == 0) {
                MutableComponent emptyMsg = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0441\u043a\u0440\u0438\u043f\u0442\u043e\u0432 \u043f\u0443\u0441\u0442!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                v_1900_v.n_1700_B(emptyMsg, new Object[0]);
                return 1;
            }
            MutableComponent title = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0441\u043a\u0440\u0438\u043f\u0442\u043e\u0432:").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(title, new Object[0]);
            for (String scriptName : scripts) {
                boolean loaded = ClientBootstrap.Y_601_j().h_1847_R().J_1907_R(scriptName);
                D_4024_W color = loaded ? D_4024_W.u_2550_I : D_4024_W.M_182_A;
                String status = loaded ? " [\u0417\u0430\u0433\u0440\u0443\u0436\u0435\u043d]" : "";
                String type = scriptName.toLowerCase().endsWith(".pouch") ? " [Pouch]" : " [Java]";
                MutableComponent scriptText = new U_2871_b(scriptName + type + status).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(color));
                v_1900_v.n_1700_B(scriptText, new Object[0]);
            }
            return 1;
        }));
    }
}



