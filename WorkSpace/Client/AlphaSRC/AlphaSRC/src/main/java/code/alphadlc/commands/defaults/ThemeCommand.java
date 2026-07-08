/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_5250
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.AlphaDLC;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.IBaritoneChatControl;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.Paginator;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import code.alphadlc.utils.theme.ThemeManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class ThemeCommand
extends Command {
    protected ThemeCommand(AlphaDLC main) {
        super("theme");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        if (!args.hasAny()) {
            Paginator.paginate(args, new Paginator<String>(this.getThemes()), () -> this.logDirect("\u0421\u043f\u0438\u0441\u043e\u043a \u0442\u0435\u043c:"), theme -> {
                class_5250 namesComponent = class_2561.method_43470((String)theme);
                namesComponent.method_10862(namesComponent.method_10866().method_10977(class_124.field_1068));
                return namesComponent;
            }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
            return;
        }
        String arg = args.getString().toLowerCase(Locale.US);
        args.requireMax(1);
        if (arg.contains("load")) {
            String name = args.getString();
            if (ThemeManager.themeExists(name)) {
                if (ThemeManager.loadTheme(name)) {
                    this.logDirect(String.format("\u0422\u0435\u043c\u0430 %s \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u0430!", name));
                } else {
                    this.logDirect(String.format("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0435 \u0442\u0435\u043c\u044b %s!", name), class_124.field_1061);
                }
            } else {
                this.logDirect(String.format("\u0422\u0435\u043c\u0430 %s \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430!", name), class_124.field_1061);
            }
        } else if (arg.contains("save")) {
            String name = args.getString();
            if (ThemeManager.saveTheme(name)) {
                this.logDirect(String.format("\u0422\u0435\u043c\u0430 %s \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0430!", name));
            } else {
                this.logDirect(String.format("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u0438 \u0442\u0435\u043c\u044b %s!", name), class_124.field_1061);
            }
        } else if (arg.contains("remove")) {
            String name = args.getString();
            if (name.equals("default")) {
                this.logDirect("\u041d\u0435\u043b\u044c\u0437\u044f \u0443\u0434\u0430\u043b\u0438\u0442\u044c \u0442\u0435\u043c\u0443 'default'!", class_124.field_1061);
                return;
            }
            if (ThemeManager.deleteTheme(name)) {
                this.logDirect(String.format("\u0422\u0435\u043c\u0430 %s \u0443\u0434\u0430\u043b\u0435\u043d\u0430!", name));
            } else {
                this.logDirect(String.format("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0443\u0434\u0430\u043b\u0435\u043d\u0438\u0438 \u0442\u0435\u043c\u044b %s \u0438\u043b\u0438 \u0442\u0435\u043c\u0430 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430!", name), class_124.field_1061);
            }
        } else if (arg.contains("list")) {
            Paginator.paginate(args, new Paginator<String>(this.getThemes()), () -> this.logDirect("\u0421\u043f\u0438\u0441\u043e\u043a \u0442\u0435\u043c:"), theme -> {
                class_5250 namesComponent = class_2561.method_43470((String)theme);
                namesComponent.method_10862(namesComponent.method_10866().method_10977(class_124.field_1068));
                return namesComponent;
            }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
        } else {
            this.logDirect("\u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u0430\u044f \u043a\u043e\u043c\u0430\u043d\u0434\u0430! \u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439\u0442\u0435: load, save, remove, list", class_124.field_1061);
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasAny()) {
            String arg = args.getString();
            if (args.hasExactlyOne()) {
                if (arg.equalsIgnoreCase("load") || arg.equalsIgnoreCase("remove")) {
                    return this.getThemes().stream().filter(theme -> !theme.equals("default")).map(theme -> theme);
                }
                if (arg.equalsIgnoreCase("save")) {
                    return Stream.of("<\u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435>");
                }
            } else {
                return new TabCompleteHelper().sortAlphabetically().prepend("load", "save", "remove", "list").filterPrefix(arg).stream();
            }
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0435\u0442 \u0432\u0437\u0430\u0438\u043c\u043e\u0434\u0435\u0439\u0441\u0442\u0432\u043e\u0432\u0430\u0442\u044c \u0441 \u0442\u0435\u043c\u0430\u043c\u0438 \u0432 \u0447\u0438\u0442\u0435";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u0421 \u043f\u043e\u043c\u043e\u0449\u044c\u044e \u044d\u0442\u043e\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u044b \u043c\u043e\u0436\u043d\u043e \u0437\u0430\u0433\u0440\u0443\u0436\u0430\u0442\u044c/\u0441\u043e\u0445\u0440\u0430\u043d\u044f\u0442\u044c/\u0443\u0434\u0430\u043b\u044f\u0442\u044c \u0442\u0435\u043c\u044b", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435:", "> theme load <name> - \u0417\u0430\u0433\u0440\u0443\u0436\u0430\u0435\u0442 \u0442\u0435\u043c\u0443.", "> theme save <name> - \u0421\u043e\u0445\u0440\u0430\u043d\u044f\u0435\u0442 \u0442\u0435\u043c\u0443.", "> theme remove <name> - \u0423\u0434\u0430\u043b\u044f\u0435\u0442 \u0442\u0435\u043c\u0443.", "> theme list - \u0412\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0435\u0442 \u0441\u043f\u0438\u0441\u043e\u043a \u0442\u0435\u043c.");
    }

    private List<String> getThemes() {
        ArrayList<String> themes = new ArrayList<String>();
        String[] availableThemes = ThemeManager.getAvailableThemes();
        themes.add("default_dark");
        for (String theme : availableThemes) {
            if (themes.contains(theme)) continue;
            themes.add(theme);
        }
        return themes;
    }
}
