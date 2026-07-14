/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.D_4024_W;
import lightning.product.U_2871_b;
import lightning.product.MinecraftClient;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.Settings;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.IBaritoneChatControl;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeFile;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidStateException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidTypeException;
import mods.baritone.api.api.java.baritone.api.command.helpers.Paginator;
import mods.baritone.api.api.java.baritone.api.command.helpers.TabCompleteHelper;
import mods.baritone.api.api.java.baritone.api.utils.SettingsUtil;

public class SetCommand
extends Command {
    public SetCommand(IBaritone baritone) {
        super(baritone, "set", "setting", "settings");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        boolean doingSomething;
        boolean paginate;
        String arg;
        String string = arg = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list";
        if (Arrays.asList("s", "save").contains(arg)) {
            SettingsUtil.save(Baritone.settings());
            this.logDirect("Settings saved");
            return;
        }
        if (Arrays.asList("load", "ld").contains(arg)) {
            String file = "settings.txt";
            if (args.hasAny()) {
                file = args.getString();
            }
            SettingsUtil.modifiedSettings(Baritone.settings()).forEach(Settings.Setting::reset);
            SettingsUtil.readAndApply(Baritone.settings(), file);
            this.logDirect("Settings reloaded from " + file);
            return;
        }
        boolean viewModified = Arrays.asList("m", "mod", "modified").contains(arg);
        boolean viewAll = Arrays.asList("all", "l", "list").contains(arg);
        boolean bl = paginate = viewModified || viewAll;
        if (paginate) {
            String search = args.hasAny() && args.peekAsOrNull(Integer.class) == null ? args.getString() : "";
            args.requireMax(1);
            List toPaginate = (viewModified ? SettingsUtil.modifiedSettings(Baritone.settings()) : Baritone.settings().allSettings).stream().filter(s -> !s.isJavaOnly()).filter(s -> s.getName().toLowerCase(Locale.US).contains(search.toLowerCase(Locale.US))).sorted((s1, s2) -> String.CASE_INSENSITIVE_ORDER.compare(s1.getName(), s2.getName())).collect(Collectors.toList());
            Paginator.paginate(args, new Paginator(toPaginate), () -> this.logDirect(!search.isEmpty() ? String.format("All %ssettings containing the string '%s':", viewModified ? "modified " : "", search) : String.format("All %ssettings:", viewModified ? "modified " : "")), setting -> {
                U_2871_b typeComponent = new U_2871_b(String.format(" (%s)", SettingsUtil.settingTypeToString(setting)));
                typeComponent.n_1700_B(typeComponent.n_1700_B().n_1700_B(D_4024_W.t_148_a));
                U_2871_b hoverComponent = new U_2871_b("");
                hoverComponent.n_1700_B(hoverComponent.n_1700_B().n_1700_B(D_4024_W.w_1484_f));
                hoverComponent.n_1700_B(setting.getName());
                hoverComponent.n_1700_B(String.format("\nType: %s", SettingsUtil.settingTypeToString(setting)));
                hoverComponent.n_1700_B(String.format("\n\nValue:\n%s", SettingsUtil.settingValueToString(setting)));
                hoverComponent.n_1700_B(String.format("\n\nDefault Value:\n%s", SettingsUtil.settingDefaultToString(setting)));
                String commandSuggestion = (String)Baritone.settings().prefix.value + String.format("set %s ", setting.getName());
                U_2871_b component = new U_2871_b(setting.getName());
                component.n_1700_B(component.n_1700_B().n_1700_B(D_4024_W.w_1484_f));
                component.n_1700_B(typeComponent);
                component.n_1700_B(component.n_1700_B().n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, hoverComponent)).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.G_564_y, commandSuggestion)));
                return component;
            }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + "set " + arg + " " + search);
            return;
        }
        args.requireMax(1);
        boolean resetting = arg.equalsIgnoreCase("reset");
        boolean toggling = arg.equalsIgnoreCase("toggle");
        boolean bl2 = doingSomething = resetting || toggling;
        if (resetting) {
            if (!args.hasAny()) {
                this.logDirect("Please specify 'all' as an argument to reset to confirm you'd really like to do this");
                this.logDirect("ALL settings will be reset. Use the 'set modified' or 'modified' commands to see what will be reset.");
                this.logDirect("Specify a setting name instead of 'all' to only reset one setting");
            } else if (args.peekString().equalsIgnoreCase("all")) {
                SettingsUtil.modifiedSettings(Baritone.settings()).forEach(Settings.Setting::reset);
                this.logDirect("All settings have been reset to their default values");
                SettingsUtil.save(Baritone.settings());
                return;
            }
        }
        if (toggling) {
            args.requireMin(1);
        }
        String settingName = doingSomething ? args.getString() : arg;
        Settings.Setting setting2 = Baritone.settings().allSettings.stream().filter(s -> s.getName().equalsIgnoreCase(settingName)).findFirst().orElse(null);
        if (setting2 == null) {
            throw new CommandInvalidTypeException(args.consumed(), "a valid setting");
        }
        if (setting2.isJavaOnly()) {
            throw new CommandInvalidStateException(String.format("Setting %s can only be used via the api.", setting2.getName()));
        }
        if (!doingSomething && !args.hasAny()) {
            this.logDirect(String.format("Value of setting %s:", setting2.getName()));
            this.logDirect(SettingsUtil.settingValueToString(setting2));
        } else {
            String oldValue = SettingsUtil.settingValueToString(setting2);
            if (resetting) {
                setting2.reset();
            } else if (toggling) {
                Settings.Setting asBoolSetting;
                if (setting2.getValueClass() != Boolean.class) {
                    throw new CommandInvalidTypeException(args.consumed(), "a toggleable setting", "some other setting");
                }
                Settings.Setting setting3 = asBoolSetting = setting2;
                setting3.value = (Boolean)setting3.value ^ true;
                this.logDirect(String.format("Toggled setting %s to %s", setting2.getName(), Boolean.toString((Boolean)setting2.value)));
            } else {
                String newValue = args.getString();
                try {
                    SettingsUtil.parseAndApply(Baritone.settings(), arg, newValue);
                }
                catch (Throwable t) {
                    t.printStackTrace();
                    throw new CommandInvalidTypeException(args.consumed(), "a valid value", t);
                }
            }
            if (!toggling) {
                this.logDirect(String.format("Successfully %s %s to %s", resetting ? "reset" : "set", setting2.getName(), SettingsUtil.settingValueToString(setting2)));
            }
            U_2871_b oldValueComponent = new U_2871_b(String.format("Old value: %s", oldValue));
            oldValueComponent.n_1700_B(oldValueComponent.n_1700_B().n_1700_B(D_4024_W.w_1484_f).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("Click to set the setting back to this value"))).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, IBaritoneChatControl.FORCE_COMMAND_PREFIX + String.format("set %s %s", setting2.getName(), oldValue))));
            this.logDirect(oldValueComponent);
            if (setting2.getName().equals("chatControl") && !((Boolean)setting2.value).booleanValue() && !((Boolean)Baritone.settings().chatControlAnyway.value).booleanValue() || setting2.getName().equals("chatControlAnyway") && !((Boolean)setting2.value).booleanValue() && !((Boolean)Baritone.settings().chatControl.value).booleanValue()) {
                this.logDirect("Warning: Chat commands will no longer work. If you want to revert this change, use prefix control (if enabled) or click the old value listed above.", D_4024_W.P_4830_p);
            } else if (setting2.getName().equals("prefixControl") && !((Boolean)setting2.value).booleanValue()) {
                this.logDirect("Warning: Prefixed commands will no longer work. If you want to revert this change, use chat control (if enabled) or click the old value listed above.", D_4024_W.P_4830_p);
            }
        }
        SettingsUtil.save(Baritone.settings());
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasAny()) {
            String arg = args.getString();
            if (args.hasExactlyOne() && !Arrays.asList("s", "save").contains(args.peekString().toLowerCase(Locale.US))) {
                if (arg.equalsIgnoreCase("reset")) {
                    return new TabCompleteHelper().addModifiedSettings().prepend("all").filterPrefix(args.getString()).stream();
                }
                if (arg.equalsIgnoreCase("toggle")) {
                    return new TabCompleteHelper().addToggleableSettings().filterPrefix(args.getString()).stream();
                }
                if (Arrays.asList("ld", "load").contains(arg.toLowerCase(Locale.US))) {
                    return RelativeFile.tabComplete(args, MinecraftClient.A_4115_X().M_182_A.toPath().resolve("baritone").toFile());
                }
                Settings.Setting<?> setting = Baritone.settings().byLowerName.get(arg.toLowerCase(Locale.US));
                if (setting != null) {
                    if (setting.getType() == Boolean.class) {
                        TabCompleteHelper helper = new TabCompleteHelper();
                        if (((Boolean)setting.value).booleanValue()) {
                            helper.append("true", "false");
                        } else {
                            helper.append("false", "true");
                        }
                        return helper.filterPrefix(args.getString()).stream();
                    }
                    return Stream.of(SettingsUtil.settingValueToString(setting));
                }
            } else if (!args.hasAny()) {
                return new TabCompleteHelper().addSettings().sortAlphabetically().prepend("list", "modified", "reset", "toggle", "save", "load").filterPrefix(arg).stream();
            }
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "View or change settings";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Using the set command, you can manage all of Baritone's settings. Almost every aspect is controlled by these settings - go wild!", "", "Usage:", "> set - Same as `set list`", "> set list [page] - View all settings", "> set modified [page] - View modified settings", "> set <setting> - View the current value of a setting", "> set <setting> <value> - Set the value of a setting", "> set reset all - Reset ALL SETTINGS to their defaults", "> set reset <setting> - Reset a setting to its default", "> set toggle <setting> - Toggle a boolean setting", "> set save - Save all settings (this is automatic tho)", "> set load - Load settings from settings.txt", "> set load [filename] - Load settings from another file in your minecraft/baritone");
    }
}


