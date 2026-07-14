/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lightning.product.D_4024_W;
import lightning.product.H_1873_g;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Module;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftAccess;
import lightning.product.ModuleManager;
import lightning.product.c_973_a;
import lightning.product.Setting;
import lightning.product.i_2909_p;
import lightning.product.j_1654_T;
import lightning.product.ClientBootstrap;
import lightning.product.o_2341_D;
import lightning.product.BooleanSetting;
import lightning.product.KeyBindSetting;
import lightning.product.v_1900_v;

public class t_1446_I
extends o_2341_D
implements MinecraftAccess {
    public t_1446_I() {
        super("bind");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(x$0 -> this.n_1700_B((CommandContext<V_4217_p>)x$0));
        builder.then(t_1446_I.n_1700_B("add").then(t_1446_I.n_1700_B("args", StringArgumentType.greedyString()).suggests(this::J_1907_R).executes(this::J_1907_R)));
        builder.then(t_1446_I.n_1700_B("remove").then(t_1446_I.n_1700_B("module", StringArgumentType.greedyString()).suggests(this::n_1700_B).executes(this::R_4764_Y)));
        builder.then(t_1446_I.n_1700_B("list").executes(this::G_564_y));
        builder.then(t_1446_I.n_1700_B("clear").executes(this::P_1922_E));
    }

    private int J_1907_R(CommandContext<V_4217_p> context) {
        Module.n_1700_B mode;
        String rest = ((String)context.getArgument("args", String.class)).trim();
        if (rest.isEmpty()) {
            return this.n_1700_B(context);
        }
        String[] tokens = rest.split("\\s+");
        if (tokens.length < 3) {
            return this.n_1700_B(context);
        }
        String modeToken = tokens[tokens.length - 1];
        String keyToken = tokens[tokens.length - 2];
        String moduleName = String.join((CharSequence)" ", Arrays.asList(tokens).subList(0, tokens.length - 2));
        if (modeToken.equalsIgnoreCase("hold")) {
            mode = Module.n_1700_B.J_1907_R;
        } else if (modeToken.equalsIgnoreCase("toggle")) {
            mode = Module.n_1700_B.n_1700_B;
        } else {
            return this.n_1700_B(context);
        }
        j_1654_T key = j_1654_T.n_1700_B(keyToken);
        if (key == null) {
            return this.n_1700_B(context);
        }
        ModuleManager mm = ClientBootstrap.Y_601_j().J_1907_R();
        Module module = this.n_1700_B(mm, moduleName);
        if (module == null) {
            return this.n_1700_B(context);
        }
        module.n_1700_B(key.J_1907_R());
        module.n_1700_B(mode);
        MutableComponent msg1 = new U_2871_b("\u0423\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d \u0431\u0438\u043d\u0434 ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
        MutableComponent msg2 = new U_2871_b(j_1654_T.n_1700_B(module.v_4262_N())).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
        MutableComponent msg3 = new U_2871_b(" \u043d\u0430 \u043c\u043e\u0434\u0443\u043b\u044c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
        MutableComponent msg4 = new U_2871_b(module.G_564_y()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
        MutableComponent msg5 = new U_2871_b("'!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
        v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(msg1).n_1700_B(msg2).n_1700_B(msg3).n_1700_B(msg4).n_1700_B(msg5), new Object[0]);
        return 1;
    }

    private int R_4764_Y(CommandContext<V_4217_p> context) {
        String moduleName = (String)context.getArgument("module", String.class);
        ModuleManager mm = ClientBootstrap.Y_601_j().J_1907_R();
        Module module = this.n_1700_B(mm, moduleName);
        if (module == null) {
            return this.n_1700_B(context);
        }
        module.n_1700_B(-100);
        MutableComponent msg1 = new U_2871_b("\u041c\u043e\u0434\u0443\u043b\u044c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
        MutableComponent msg2 = new U_2871_b(module.G_564_y()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
        MutableComponent msg3 = new U_2871_b("' \u0442\u0435\u043f\u0435\u0440\u044c \u043d\u0435 \u0438\u043c\u0435\u0435\u0442 \u0431\u0438\u043d\u0434\u0430!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
        v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(msg1).n_1700_B(msg2).n_1700_B(msg3), new Object[0]);
        return 1;
    }

    private int G_564_y(CommandContext<V_4217_p> context) {
        MutableComponent keyVal;
        MutableComponent keyLabel;
        MutableComponent settingName;
        MutableComponent separator;
        MutableComponent moduleName;
        String key;
        ModuleManager mm = ClientBootstrap.Y_601_j().J_1907_R();
        List boundModules = mm.u_1723_Y().stream().filter(m -> m.v_4262_N() != -100).collect(Collectors.toList());
        ArrayList<AbstractMap.SimpleEntry<Module, BooleanSetting>> boundBooleans = new ArrayList<AbstractMap.SimpleEntry<Module, BooleanSetting>>();
        ArrayList<AbstractMap.SimpleEntry<Module, KeyBindSetting>> boundBindSettings = new ArrayList<AbstractMap.SimpleEntry<Module, KeyBindSetting>>();
        for (Module x_3546_T : mm.u_1723_Y()) {
            for (Setting<?> setting : x_3546_T.u_2550_I()) {
                KeyBindSetting bindSetting;
                BooleanSetting bs;
                if (setting instanceof BooleanSetting && (bs = (BooleanSetting)setting).u_2550_I() != -100) {
                    boundBooleans.add(new AbstractMap.SimpleEntry<Module, BooleanSetting>(x_3546_T, bs));
                    continue;
                }
                if (!(setting instanceof KeyBindSetting) || (Integer)(bindSetting = (KeyBindSetting)setting).J_1907_R() == -1) continue;
                boundBindSettings.add(new AbstractMap.SimpleEntry<Module, KeyBindSetting>(x_3546_T, bindSetting));
            }
        }
        if (boundModules.isEmpty() && boundBooleans.isEmpty() && boundBindSettings.isEmpty()) {
            v_1900_v.n_1700_B(new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0431\u0438\u043d\u0434o\u0432 \u043f\u0443\u0441\u0442!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
            return 1;
        }
        if (!boundModules.isEmpty()) {
            v_1900_v.n_1700_B(new U_2871_b("--- \u0411\u0438\u043d\u0434\u044b \u043c\u043e\u0434\u0443\u043b\u0435\u0439 ---").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.v_4262_N)), new Object[0]);
            for (Module x_3546_T : boundModules) {
                String key2 = j_1654_T.n_1700_B(x_3546_T.v_4262_N());
                MutableComponent name = new U_2871_b(x_3546_T.G_564_y()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent keyLabel2 = new U_2871_b(" \u043a\u043b\u0430\u0432\u0438\u0448\u0430: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent keyVal2 = new U_2871_b(key2).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                String removeCmd = ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B() + this.n_1700_B() + " remove " + this.J_1907_R(x_3546_T.G_564_y());
                MutableComponent removeBtn = new U_2871_b(" [\u0423\u0434\u0430\u043b\u0438\u0442\u044c]").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p).n_1700_B(new H_1873_g(i_2909_p.n_1700_B.R_4764_Y, removeCmd)).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("\u041a\u043b\u0438\u043a \u0434\u043b\u044f \u0443\u0434\u0430\u043b\u0435\u043d\u0438\u044f \u0431\u0438\u043d\u0434\u0430").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)))));
                v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(name).n_1700_B(keyLabel2).n_1700_B(keyVal2).n_1700_B(removeBtn), new Object[0]);
            }
        }
        if (!boundBooleans.isEmpty()) {
            v_1900_v.n_1700_B(new U_2871_b("--- \u0411\u0438\u043d\u0434\u044b \u043d\u0430 \u0447\u0435\u043a\u0431\u043e\u043a\u0441\u044b ---").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.v_4262_N)), new Object[0]);
            for (AbstractMap.SimpleEntry simpleEntry : boundBooleans) {
                Module m2 = (Module)simpleEntry.getKey();
                BooleanSetting bs = (BooleanSetting)simpleEntry.getValue();
                key = j_1654_T.n_1700_B(bs.u_2550_I());
                moduleName = new U_2871_b(m2.G_564_y()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                separator = new U_2871_b(" > ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                settingName = new U_2871_b(bs.n_1700_B()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                keyLabel = new U_2871_b(" \u043a\u043b\u0430\u0432\u0438\u0448\u0430: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                keyVal = new U_2871_b(key).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(moduleName).n_1700_B(separator).n_1700_B(settingName).n_1700_B(keyLabel).n_1700_B(keyVal), new Object[0]);
            }
        }
        if (!boundBindSettings.isEmpty()) {
            v_1900_v.n_1700_B(new U_2871_b("--- \u0411\u0438\u043d\u0434\u044b \u043d\u0430 \u043a\u043d\u043e\u043f\u043a\u0438 ---").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.v_4262_N)), new Object[0]);
            for (AbstractMap.SimpleEntry simpleEntry : boundBindSettings) {
                Module m2 = (Module)simpleEntry.getKey();
                KeyBindSetting bindSetting = (KeyBindSetting)simpleEntry.getValue();
                key = j_1654_T.n_1700_B((Integer)bindSetting.J_1907_R());
                moduleName = new U_2871_b(m2.G_564_y()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                separator = new U_2871_b(" > ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                settingName = new U_2871_b(bindSetting.n_1700_B()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                keyLabel = new U_2871_b(" \u043a\u043b\u0430\u0432\u0438\u0448\u0430: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                keyVal = new U_2871_b(key).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(moduleName).n_1700_B(separator).n_1700_B(settingName).n_1700_B(keyLabel).n_1700_B(keyVal), new Object[0]);
            }
        }
        return 1;
    }

    private int P_1922_E(CommandContext<V_4217_p> context) {
        boolean anyBound = ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y().stream().anyMatch(m -> m.v_4262_N() != -100);
        if (!anyBound) {
            MutableComponent emptyMsg = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0431\u0438\u043d\u0434o\u0432 \u043f\u0443\u0441\u0442!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(emptyMsg, new Object[0]);
            return 1;
        }
        ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y().forEach(m -> m.n_1700_B(-100));
        MutableComponent clearedMsg = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0411\u0438\u043d\u0434\u043e\u0432 \u043e\u0447\u0438\u0449\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
        v_1900_v.n_1700_B(clearedMsg, new Object[0]);
        return 1;
    }

    private CompletableFuture<Suggestions> n_1700_B(CommandContext<V_4217_p> ctx, SuggestionsBuilder builder) {
        List<String> names = ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y().stream().map(Module::G_564_y).map(this::J_1907_R).collect(Collectors.toList());
        return V_4217_p.J_1907_R(names, builder);
    }

    private CompletableFuture<Suggestions> J_1907_R(CommandContext<V_4217_p> ctx, SuggestionsBuilder builder) {
        String[] parts;
        String input = builder.getInput();
        String lower = input.toLowerCase(Locale.ROOT);
        int addPos = lower.indexOf(".bind add ");
        String afterAdd = addPos == -1 ? "" : input.substring(addPos + ".bind add ".length());
        boolean hasTrailingSpace = afterAdd.endsWith(" ");
        String trimmed = afterAdd.trim();
        String[] stringArray = parts = trimmed.isEmpty() ? new String[]{} : trimmed.split("\\s+");
        int replaceStart = parts.length == 0 ? input.length() : (hasTrailingSpace ? input.length() : input.length() - parts[parts.length - 1].length());
        SuggestionsBuilder sb = new SuggestionsBuilder(input, replaceStart);
        if (parts.length == 0 || parts.length == 1 && !hasTrailingSpace) {
            String prefix = parts.length == 0 ? "" : this.J_1907_R(parts[0]);
            List names = ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y().stream().map(Module::G_564_y).map(this::J_1907_R).filter(n -> n.toLowerCase(Locale.ROOT).startsWith(prefix.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
            for (String n2 : names) {
                sb.suggest(n2);
            }
            return sb.buildFuture();
        }
        if (parts.length == 1 || parts.length == 2 && !hasTrailingSpace) {
            String keyPrefix = parts.length == 2 ? parts[1].toUpperCase(Locale.ROOT) : "";
            for (j_1654_T key : j_1654_T.values()) {
                if (!key.name().startsWith(keyPrefix)) continue;
                sb.suggest(key.name());
            }
            return sb.buildFuture();
        }
        if (parts.length == 2 || !hasTrailingSpace) {
            String modePrefix;
            String string = modePrefix = parts.length >= 3 ? parts[2].toLowerCase(Locale.ROOT) : "";
            if ("toggle".startsWith(modePrefix)) {
                sb.suggest("toggle");
            }
            if ("hold".startsWith(modePrefix)) {
                sb.suggest("hold");
            }
            return sb.buildFuture();
        }
        return sb.buildFuture();
    }

    private Module n_1700_B(ModuleManager mm, String userInputName) {
        Module direct = mm.n_1700_B(userInputName).orElse(null);
        if (direct != null) {
            return direct;
        }
        String normalized = this.J_1907_R(userInputName);
        for (Module m : mm.u_1723_Y()) {
            if (!this.J_1907_R(m.G_564_y()).equalsIgnoreCase(normalized)) continue;
            return m;
        }
        return null;
    }

    private String J_1907_R(String name) {
        return name.replaceAll("\\s+", "");
    }
}



