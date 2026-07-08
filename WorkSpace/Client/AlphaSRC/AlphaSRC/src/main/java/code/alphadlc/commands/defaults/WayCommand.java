/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2338
 *  net.minecraft.class_2561
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.AlphaDLC;
import code.alphadlc.common.repository.way.Way;
import code.alphadlc.common.repository.way.WayRepository;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.IBaritoneChatControl;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.datatypes.WayDataType;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.Paginator;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2338;
import net.minecraft.class_2561;

public class WayCommand
extends Command
implements QuickImports {
    private final WayRepository wayRepository;

    protected WayCommand(AlphaDLC main) {
        super("way", "gps");
        this.wayRepository = main.getWayRepository();
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String first;
        if (!args.hasAny()) {
            this.handleListWays(label, args);
            return;
        }
        switch (first = args.peekString().toLowerCase(Locale.US)) {
            case "add": {
                args.getString();
                this.handleAddWay(args);
                break;
            }
            case "remove": {
                args.getString();
                this.handleRemoveWay(args);
                break;
            }
            case "clear": {
                args.getString();
                this.handleClearWays();
                break;
            }
            case "list": {
                args.getString();
                this.handleListWays(label, args);
                break;
            }
            default: {
                this.handleAddWayShort(args);
            }
        }
    }

    private void handleAddWayShort(IArgConsumer args) throws CommandException {
        String address;
        String first = args.peekString();
        boolean firstIsNumber = false;
        try {
            Integer.parseInt(first);
            firstIsNumber = true;
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        String string = mc.method_1562() == null ? "vanilla" : (address = mc.method_1562().method_45734() == null ? "vanilla" : WayCommand.mc.method_1562().method_45734().field_3761);
        if (firstIsNumber && args.has(3)) {
            int x = args.getAs(Integer.class);
            int y = args.getAs(Integer.class);
            int z = args.getAs(Integer.class);
            String name = "\u041c\u0435\u0442\u043a\u0430";
            this.wayRepository.upsertWay(name, new class_2338(x, y, z), address);
            this.logDirect("\u041c\u0435\u0442\u043a\u0430 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0430: " + name + ", \u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b: (" + x + ", " + y + ", " + z + "), \u0421\u0435\u0440\u0432\u0435\u0440: " + address, class_124.field_1068);
            return;
        }
        if (firstIsNumber && args.has(2)) {
            if (WayCommand.mc.field_1724 == null) {
                this.logDirect("\u0418\u0433\u0440\u043e\u043a \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d", class_124.field_1061);
                return;
            }
            int x = args.getAs(Integer.class);
            int z = args.getAs(Integer.class);
            int y = WayCommand.mc.field_1724.method_24515().method_10264();
            String name = "\u041c\u0435\u0442\u043a\u0430";
            this.wayRepository.upsertWay(name, new class_2338(x, y, z), address);
            this.logDirect("\u041c\u0435\u0442\u043a\u0430 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0430: " + name + ", \u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b: (" + x + ", " + y + ", " + z + "), \u0421\u0435\u0440\u0432\u0435\u0440: " + address, class_124.field_1068);
            return;
        }
        String name = args.getString();
        if (args.has(3)) {
            int x = args.getAs(Integer.class);
            int y = args.getAs(Integer.class);
            int z = args.getAs(Integer.class);
            this.wayRepository.upsertWay(name, new class_2338(x, y, z), address);
            this.logDirect("\u041c\u0435\u0442\u043a\u0430 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0430 " + name + ", \u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b: (" + x + ", " + y + ", " + z + "), \u0421\u0435\u0440\u0432\u0435\u0440: " + address, class_124.field_1068);
        } else if (!args.hasAny()) {
            if (WayCommand.mc.field_1724 == null) {
                this.logDirect("\u0418\u0433\u0440\u043e\u043a \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d", class_124.field_1061);
                return;
            }
            class_2338 pos = WayCommand.mc.field_1724.method_24515();
            this.wayRepository.upsertWay(name, pos, address);
            this.logDirect("\u041c\u0435\u0442\u043a\u0430 \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0430: " + name + " (" + pos.method_10263() + ", " + pos.method_10264() + ", " + pos.method_10260() + "), \u0421\u0435\u0440\u0432\u0435\u0440: " + address, class_124.field_1068);
        } else {
            args.requireMax(0);
        }
    }

    private void handleAddWay(IArgConsumer args) throws CommandException {
        if (!args.hasAny()) {
            if (WayCommand.mc.field_1724 == null) {
                this.logDirect("\u0418\u0433\u0440\u043e\u043a \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d", class_124.field_1061);
                return;
            }
            class_2338 pos = WayCommand.mc.field_1724.method_24515();
            String name = "\u041c\u0435\u0442\u043a\u0430";
            String address = mc.method_1562() == null ? "vanilla" : (mc.method_1562().method_45734() == null ? "vanilla" : WayCommand.mc.method_1562().method_45734().field_3761);
            this.wayRepository.upsertWay(name, pos, address);
            this.logDirect("\u041c\u0435\u0442\u043a\u0430 \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0430: " + name + " (" + pos.method_10263() + ", " + pos.method_10264() + ", " + pos.method_10260() + "), \u0421\u0435\u0440\u0432\u0435\u0440: " + address, class_124.field_1068);
            return;
        }
        args.requireMin(4);
        String name = args.getString();
        int x = args.getArgs().get(0).getAs(Integer.class);
        int y = args.getArgs().get(1).getAs(Integer.class);
        int z = args.getArgs().get(2).getAs(Integer.class);
        String address = mc.method_1562() == null ? "vanilla" : (mc.method_1562().method_45734() == null ? "vanilla" : WayCommand.mc.method_1562().method_45734().field_3761);
        this.wayRepository.upsertWay(name, new class_2338(x, y, z), address);
        this.logDirect("\u041c\u0435\u0442\u043a\u0430 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0430 " + name + ", \u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b: (" + x + ", " + y + ", " + z + "), \u0421\u0435\u0440\u0432\u0435\u0440: " + address, class_124.field_1068);
    }

    private void handleRemoveWay(IArgConsumer args) throws CommandException {
        args.requireMax(1);
        String name = args.getString();
        if (this.wayRepository.hasWay(name)) {
            this.wayRepository.deleteWay(name);
            this.logDirect(String.valueOf(class_124.field_1060) + "\u041c\u0435\u0442\u043a\u0430 " + String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1060) + " \u0431\u044b\u043b\u0430 \u0443\u0441\u043f\u0435\u0448\u043d\u0430 \u0443\u0434\u0430\u043b\u0435\u043d\u0430!");
        } else {
            this.logDirect("\u041c\u0435\u0442\u043a\u0430 \u0441 \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435\u043c '" + name + "' \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430!");
        }
    }

    private void handleListWays(String label, IArgConsumer args) throws CommandException {
        args.requireMax(1);
        Paginator.paginate(args, new Paginator<Way>(this.wayRepository.wayList), () -> this.logDirect("\u0421\u043f\u0438\u0441\u043e\u043a \u043c\u0435\u0442\u043e\u043a:"), way -> class_2561.method_43470((String)(String.valueOf(class_124.field_1080) + "\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435: " + String.valueOf(class_124.field_1061) + way.name())).method_10852((class_2561)class_2561.method_43470((String)(String.valueOf(class_124.field_1080) + " \u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b: " + String.valueOf(class_124.field_1068) + " (" + way.pos().method_10263() + ", " + way.pos().method_10264() + ", " + way.pos().method_10260() + ")")).method_10852((class_2561)class_2561.method_43470((String)(String.valueOf(class_124.field_1080) + " \u0421\u0435\u0440\u0432\u0435\u0440: " + String.valueOf(class_124.field_1068) + way.server())))), IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
    }

    private void handleClearWays() {
        this.wayRepository.clearList();
        this.logDirect(String.valueOf(class_124.field_1060) + "\u0412\u0441\u0435 \u043c\u0435\u0442\u043a\u0438 \u0431\u044b\u043b\u0438 \u0443\u0434\u0430\u043b\u0435\u043d\u044b.");
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (!args.hasAny()) {
            return new TabCompleteHelper().sortAlphabetically().prepend("add", "remove", "list", "clear").stream();
        }
        String first = args.peekString().toLowerCase(Locale.US);
        if (first.equals("remove")) {
            args.getString();
            if (args.hasExactlyOne()) {
                return args.tabCompleteDatatype(WayDataType.INSTANCE);
            }
            return Stream.empty();
        }
        if (first.equals("add")) {
            args.getString();
            String hint = args.has(4) ? "" : (args.has(3) ? "z" : (args.has(2) ? "y" : (args.has(1) ? "x" : "\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435")));
            return new TabCompleteHelper().sortAlphabetically().prepend(hint).stream();
        }
        if (first.equals("list") || first.equals("clear")) {
            return Stream.empty();
        }
        int left = args.getArgs().size();
        String hint = left >= 4 ? "" : (left == 3 ? "z" : (left == 2 ? "y" : (left == 1 ? "x" : "\u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435")));
        return new TabCompleteHelper().sortAlphabetically().prepend(hint).stream();
    }

    @Override
    public String getShortDesc() {
        return "\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0435\u0442 \u0441\u0442\u0430\u0432\u0438\u0442\u044c \u043c\u0435\u0442\u043a\u0438 \u0432 \u043c\u0438\u0440\u0435";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u0421 \u043f\u043e\u043c\u043e\u0449\u044c\u044e \u044d\u0442\u043e\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u044b \u043c\u043e\u0436\u043d\u043e \u0434\u043e\u0431\u0430\u0432\u043b\u044f\u0442\u044c/\u0443\u0434\u0430\u043b\u044f\u0442\u044c \u043c\u0435\u0442\u043a\u0438 \u0432 \u043c\u0438\u0440\u0435 (.way \u0438 .gps)", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435 (\u043a\u043e\u0440\u043e\u0442\u043a\u0430\u044f \u0444\u043e\u0440\u043c\u0430 \u2014 \u0431\u0435\u0437 add):", "> way <name> - \u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0435\u0442 \u043c\u0435\u0442\u043a\u0443 \u043d\u0430 \u0442\u0435\u043a\u0443\u0449\u0438\u0445 \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u0430\u0445", "> way <name> <x> <y> <z> - \u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0435\u0442 \u043c\u0435\u0442\u043a\u0443 \u043f\u043e \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u0430\u043c", "> way <x> <y> <z> - \u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0435\u0442 \u043c\u0435\u0442\u043a\u0443 \u0441 \u0430\u0432\u0442\u043e\u0438\u043c\u0435\u043d\u0435\u043c \u043f\u043e \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u0430\u043c", "> way <x> <z> - \u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0435\u0442 \u043c\u0435\u0442\u043a\u0443 \u0441 \u0430\u0432\u0442\u043e\u0438\u043c\u0435\u043d\u0435\u043c (Y = \u0442\u0435\u043a\u0443\u0449\u0438\u0439 Y)", "> way add - \u0422\u043e \u0436\u0435, \u043c\u0435\u0442\u043a\u0430 \u043d\u0430 \u0442\u0435\u043a\u0443\u0449\u0438\u0445 \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u0430\u0445 (\u0438\u043c\u044f = \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b)", "> way add <name> <x> <y> <z> - \u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0435\u0442/\u043f\u0435\u0440\u0435\u0437\u0430\u043f\u0438\u0441\u044b\u0432\u0430\u0435\u0442 \u043c\u0435\u0442\u043a\u0443", "> way remove <name> - \u0423\u0434\u0430\u043b\u044f\u0435\u0442 \u043c\u0435\u0442\u043a\u0443", "> way list - \u0421\u043f\u0438\u0441\u043e\u043a \u043c\u0435\u0442\u043e\u043a", "> way clear - \u041e\u0447\u0438\u0449\u0430\u0435\u0442 \u0432\u0441\u0435 \u043c\u0435\u0442\u043a\u0438");
    }
}
