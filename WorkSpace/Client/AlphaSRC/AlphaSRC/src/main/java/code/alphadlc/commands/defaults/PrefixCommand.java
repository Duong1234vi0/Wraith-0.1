/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.commands.CommandDispatcher;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;

public class PrefixCommand
extends Command
implements QuickImports {
    protected PrefixCommand() {
        super("prefix");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String arg;
        String string = arg = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list";
        if (arg.equals("set")) {
            args.requireMin(1);
            CommandDispatcher.prefix = args.getString();
            this.logDirect("\u0423\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d \u043f\u0440\u0435\u0444\u0438\u043a\u0441 '" + String.valueOf(class_124.field_1061) + CommandDispatcher.prefix + String.valueOf(class_124.field_1080) + "'", class_124.field_1080);
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasAny()) {
            String arg = args.getString();
            if (arg.equalsIgnoreCase("set")) {
                return new TabCompleteHelper().sortAlphabetically().prepend("name").stream();
            }
            return new TabCompleteHelper().sortAlphabetically().prepend("set").filterPrefix(arg).stream();
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0435\u0442 \u043c\u0435\u043d\u044f\u0442\u044c \u043f\u0440\u0435\u0444\u0438\u043a\u0441 \u043a\u043e\u043c\u0430\u043d\u0434 \u0432 \u043c\u043e\u0434\u0435";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u0421 \u043f\u043e\u043c\u043e\u0449\u044c\u044e \u044d\u0442\u043e\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u044b \u043c\u043e\u0436\u043d\u043e \u0438\u0437\u043c\u0435\u043d\u0438\u0442\u044c \u043f\u0440\u0435\u0444\u0438\u043a\u0441 \u043a\u043e\u043c\u0430\u043d\u0434 \u0432 \u043c\u043e\u0434\u0435", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435:", "> prefix set <name> - \u0443\u0441\u0442\u0430\u043d\u0430\u0432\u043b\u0438\u0432\u0430\u0435\u0442 \u043f\u0440\u0435\u0444\u0438\u043a\u0441 \u043a\u043e\u043c\u0430\u043d\u0434");
    }
}
