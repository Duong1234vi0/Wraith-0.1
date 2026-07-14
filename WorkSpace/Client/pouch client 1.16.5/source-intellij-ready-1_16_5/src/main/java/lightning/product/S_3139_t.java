/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lightning.product.H_1873_g;
import lightning.product.MutableComponent;
import lightning.product.S_4088_D;
import lightning.product.U_2871_b;
import lightning.product.V_1176_p;
import lightning.product.V_4217_p;
import lightning.product.W_1488_x;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftClient;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.ClientBootstrap;
import lightning.product.o_2341_D;
import lightning.product.q_3148_R;
import lightning.product.v_1900_v;

public class S_3139_t
extends o_2341_D {
    private final V_1176_p J_1907_R = new V_1176_p();

    public S_3139_t() {
        super("theme");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(ctx -> {
            this.J_1907_R();
            return 1;
        });
        builder.then(S_3139_t.n_1700_B("list").executes(ctx -> {
            this.R_4764_Y();
            return 1;
        }));
        builder.then(((LiteralArgumentBuilder)S_3139_t.n_1700_B("import").executes(ctx -> {
            this.J_1907_R((String)null);
            return 1;
        })).then(S_3139_t.n_1700_B("data", StringArgumentType.greedyString()).executes(ctx -> {
            String data = (String)ctx.getArgument("data", String.class);
            this.J_1907_R(data);
            return 1;
        })));
        builder.then(((LiteralArgumentBuilder)S_3139_t.n_1700_B("export").executes(ctx -> {
            v_1900_v.n_1700_B("\u00a7c\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439\u0442\u0435: .theme export <\u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435>", new Object[0]);
            return 1;
        })).then(S_3139_t.n_1700_B("name", StringArgumentType.greedyString()).suggests(this::n_1700_B).executes(ctx -> {
            String themeName = (String)ctx.getArgument("name", String.class);
            this.R_4764_Y(themeName);
            return 1;
        })));
    }

    private void J_1907_R() {
        v_1900_v.n_1700_B("\u00a77--- \u00a7fTheme Commands \u00a77---", new Object[0]);
        v_1900_v.n_1700_B("\u00a7e.theme list \u00a77- \u0421\u043f\u0438\u0441\u043e\u043a \u0432\u0441\u0435\u0445 \u0442\u0435\u043c", new Object[0]);
        v_1900_v.n_1700_B("\u00a7e.theme import \u00a77- \u0418\u043c\u043f\u043e\u0440\u0442 \u0442\u0435\u043c\u044b \u0438\u0437 \u0431\u0443\u0444\u0435\u0440\u0430", new Object[0]);
        v_1900_v.n_1700_B("\u00a7e.theme export <\u0438\u043c\u044f> \u00a77- \u042d\u043a\u0441\u043f\u043e\u0440\u0442 \u0442\u0435\u043c\u044b \u0432 \u0431\u0443\u0444\u0435\u0440", new Object[0]);
    }

    private void R_4764_Y() {
        List<String> themeNames = this.J_1907_R.M_588_G();
        if (themeNames.isEmpty()) {
            v_1900_v.n_1700_B("\u00a77\u0421\u043f\u0438\u0441\u043e\u043a \u043a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0445 \u0442\u0435\u043c \u043f\u0443\u0441\u0442!", new Object[0]);
            v_1900_v.n_1700_B("\u00a77\u0421\u043e\u0437\u0434\u0430\u0439\u0442\u0435 \u0442\u0435\u043c\u0443 \u0432 Theme Editor", new Object[0]);
            return;
        }
        v_1900_v.n_1700_B("\u00a77--- \u00a7f\u0421\u043f\u0438\u0441\u043e\u043a \u0442\u0435\u043c \u00a77---", new Object[0]);
        for (String themeName : themeNames) {
            V_1176_p.n_1700_B theme = this.J_1907_R.n_1700_B(themeName);
            String name = themeName;
            String creator = theme != null && theme.P_1922_E() != null ? theme.P_1922_E() : "Unknown";
            U_2871_b themeText = new U_2871_b("\u00a7f" + name + " \u00a77(\u0430\u0432\u0442\u043e\u0440: " + creator + ")");
            MutableComponent exportBtn = new U_2871_b(" \u00a7a[\u042d\u043a\u0441\u043f\u043e\u0440\u0442]").n_1700_B(Z_1567_W.n_1700_B.n_1700_B(new H_1873_g(i_2909_p.n_1700_B.R_4764_Y, ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B() + "theme export " + name)).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("\u042d\u043a\u0441\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0442\u0435\u043c\u0443 " + name))));
            v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(themeText).n_1700_B(exportBtn), new Object[0]);
        }
    }

    private void J_1907_R(String data) {
        try {
            String clipboardContent = data != null && !data.isEmpty() ? data.trim() : MinecraftClient.A_4115_X().Q_4569_t.n_1700_B().trim();
            if (clipboardContent.isEmpty()) {
                v_1900_v.n_1700_B("\u00a7c\u0412\u0441\u0442\u0430\u0432\u044c\u0442\u0435 \u0434\u0430\u043d\u043d\u044b\u0435 \u0442\u0435\u043c\u044b \u0438\u043b\u0438 \u0441\u043a\u043e\u043f\u0438\u0440\u0443\u0439\u0442\u0435 \u0432 \u0431\u0443\u0444\u0435\u0440 \u043e\u0431\u043c\u0435\u043d\u0430!", new Object[0]);
                return;
            }
            String decrypted = W_1488_x.J_1907_R(clipboardContent);
            JsonObject config = new JsonParser().parse(decrypted).getAsJsonObject();
            String presetName = config.get("presetName").getAsString();
            String creator = config.has("creator") ? config.get("creator").getAsString() : "Unknown";
            JsonArray colorsArray = config.getAsJsonArray("presetColors");
            int[] colors = new int[colorsArray.size()];
            for (int i = 0; i < colorsArray.size(); ++i) {
                colors[i] = colorsArray.get(i).getAsInt();
            }
            JsonObject elementColors = null;
            if (config.has("elementColors") && config.get("elementColors").isJsonObject()) {
                elementColors = config.getAsJsonObject("elementColors");
            }
            String cleanName = presetName.replace("\u0418\u043c\u044f: ", "");
            q_3148_R.n_1700_B preset = new q_3148_R.n_1700_B(presetName, colors, creator, true);
            this.J_1907_R.n_1700_B(cleanName, preset, creator, elementColors);
            q_3148_R.n_1700_B(presetName, colors, creator);
            q_3148_R.n_1700_B(presetName, colors);
            if (elementColors != null) {
                ClientBootstrap.Y_601_j().M_182_A().J_1907_R(elementColors);
            }
            v_1900_v.n_1700_B("\u00a7a\u0422\u0435\u043c\u0430 \"" + cleanName + "\" \u0438\u043c\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u043d\u0430 \u0438 \u043f\u0440\u0438\u043c\u0435\u043d\u0435\u043d\u0430!", new Object[0]);
        }
        catch (Exception e) {
            v_1900_v.n_1700_B("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u0438\u043c\u043f\u043e\u0440\u0442\u0430: " + e.getClass().getSimpleName(), new Object[0]);
            v_1900_v.n_1700_B("\u00a77\u0421\u043a\u043e\u043f\u0438\u0440\u0443\u0439\u0442\u0435 \u0442\u0435\u043c\u0443 \u0432 \u0431\u0443\u0444\u0435\u0440 (Ctrl+C) \u0438 \u0432\u0432\u0435\u0434\u0438\u0442\u0435 .theme import", new Object[0]);
            e.printStackTrace();
        }
    }

    private void R_4764_Y(String themeName) {
        V_1176_p.n_1700_B foundTheme = this.J_1907_R.n_1700_B(themeName);
        if (foundTheme == null) {
            v_1900_v.n_1700_B("\u00a7c\u0422\u0435\u043c\u0430 \"" + themeName + "\" \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430!", new Object[0]);
            v_1900_v.n_1700_B("\u00a77\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439\u0442\u0435 .theme list \u0434\u043b\u044f \u043f\u0440\u043e\u0441\u043c\u043e\u0442\u0440\u0430 \u0442\u0435\u043c", new Object[0]);
            return;
        }
        try {
            JsonObject config = new JsonObject();
            config.addProperty("presetName", (String)(foundTheme.R_4764_Y() != null ? foundTheme.R_4764_Y() : "\u0418\u043c\u044f: " + themeName));
            config.addProperty("creator", foundTheme.P_1922_E() != null ? foundTheme.P_1922_E() : S_4088_D.n_1700_B());
            int[] colors = foundTheme.G_564_y();
            JsonArray colorsArray = new JsonArray();
            if (colors != null) {
                for (int color : colors) {
                    colorsArray.add((Number)color);
                }
            }
            config.add("presetColors", (JsonElement)colorsArray);
            if (foundTheme.u_1723_Y() != null) {
                config.add("elementColors", (JsonElement)foundTheme.u_1723_Y());
            }
            String jsonText = new GsonBuilder().create().toJson((JsonElement)config);
            String encrypted = W_1488_x.n_1700_B(jsonText);
            MinecraftClient.A_4115_X().Q_4569_t.n_1700_B(encrypted);
            v_1900_v.n_1700_B("\u00a7a\u0422\u0435\u043c\u0430 \"" + themeName + "\" \u0441\u043a\u043e\u043f\u0438\u0440\u043e\u0432\u0430\u043d\u0430 \u0432 \u0431\u0443\u0444\u0435\u0440!", new Object[0]);
        }
        catch (Exception e) {
            v_1900_v.n_1700_B("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u044d\u043a\u0441\u043f\u043e\u0440\u0442\u0430: " + e.getMessage(), new Object[0]);
        }
    }

    private CompletableFuture<Suggestions> n_1700_B(CommandContext<V_4217_p> context, SuggestionsBuilder builder) {
        List<String> themeNames = this.J_1907_R.M_588_G();
        return V_4217_p.J_1907_R(themeNames, builder);
    }
}



