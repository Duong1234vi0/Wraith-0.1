package ua.babka.command.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Stream;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import ua.babka.Babka;
import ua.babka.baritone.IBaritoneChatControl;
import ua.babka.command.CommandException;
import ua.babka.command.Paginator;
import ua.babka.command.TabCompleteHelper;
import ua.babka.command.argument.IArgConsumer;
import ua.babka.command.impl.Command;
import ua.babka.server.Staff;
import ua.babka.server.StaffManager;

public class StaffCommand
extends Command {
    private final StaffManager staffManager;

    public StaffCommand(Babka onetap) {
        super("staff");
        this.staffManager = onetap.getStaffManager();
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String action;
        switch (action = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list") {
            case "add": {
                this.handleAddStaff(args);
                break;
            }
            case "remove": {
                this.handleRemoveStaff(args);
                break;
            }
            case "list": {
                this.handleListStaff(args, label);
                break;
            }
            case "clear": {
                this.handleClearStaff(args);
                break;
            }
            default: {
                this.logDirect("Неизвестная подкоманда. Используй add/remove/list/clear.", Formatting.GRAY);
            }
        }
    }

    private void handleAddStaff(IArgConsumer args) throws CommandException {
        args.requireMin(1);
        String name = args.getString();
        if (StaffManager.isStaff(name)) {
            this.logDirect("Этот игрок уже в списке модераторов", Formatting.GRAY);
            return;
        }
        StaffManager.addStaff(new Staff(name));
        this.logDirect("Игрок с именем " + String.valueOf(Formatting.WHITE) + name + String.valueOf(Formatting.GRAY) + " успешно добавлен в список модераторов");
    }

    private void handleRemoveStaff(IArgConsumer args) throws CommandException {
        args.requireMin(1);
        String name = args.getString();
        if (!StaffManager.isStaff(name)) {
            this.logDirect("Такой игрок в списке модераторов не найден", Formatting.GRAY);
            return;
        }
        StaffManager.removeStaff(name);
        this.logDirect("Игрок с именем " + String.valueOf(Formatting.WHITE) + name + String.valueOf(Formatting.GRAY) + " успешно удален из списка модераторов");
    }

    private void handleListStaff(IArgConsumer args, String label) throws CommandException {
        args.requireMax(1);
        Set<Staff> staffSet = StaffManager.getStaffList();
        List<Staff> sorted = staffSet.stream().sorted(Comparator.comparing(s2 -> s2.name.toLowerCase())).toList();
        Paginator.paginate(args, new Paginator<Staff>(sorted), () -> this.logDirect("Список модераторов:", Formatting.GRAY), staff -> {
            MutableText nameText = Text.literal((String)(String.valueOf(Formatting.GRAY) + "- " + String.valueOf(Formatting.WHITE) + staff.name));
            MutableText deleteText = Text.literal((String)(String.valueOf(Formatting.RED) + " [Удалить]")).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, IBaritoneChatControl.FORCE_COMMAND_PREFIX + "staff remove " + staff.name)).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)Text.literal((String)"Click to delete staff"))));
            return nameText.copy().append((Text)deleteText);
        }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
    }

    private void handleClearStaff(IArgConsumer args) throws CommandException {
        args.requireMax(1);
        StaffManager.clearStaff();
        this.logDirect("Список модераторов очищен", Formatting.GRAY);
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasAny() && args.hasExactlyOne()) {
            return new TabCompleteHelper().prepend("add", "remove", "list", "clear").filterPrefix(args.getString()).sortAlphabetically().stream();
        }
        if (args.hasAny()) {
            String action = args.peekString(0).toLowerCase(Locale.ROOT);
            if (action.equals("remove") && args.hasExactly(2)) {
                String prefix = args.peekString(1).toLowerCase(Locale.ROOT);
                return StaffManager.getStaffList().stream().map(staff -> staff.name).filter(name -> name.toLowerCase(Locale.ROOT).startsWith(prefix)).sorted().distinct();
            }
            if (action.equals("add") && args.hasExactly(2)) {
                String prefix = args.peekString(1).toLowerCase(Locale.ROOT);
                return MinecraftClient.getInstance().getNetworkHandler().getPlayerList().stream().map(info -> info.getProfile().getName()).filter(name -> name.toLowerCase(Locale.ROOT).startsWith(prefix)).sorted().distinct();
            }
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Управление списком модераторов";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Эта команда позволяет управлять списком модераторов.", "", "Использование:", "> staff add <name> - Добавляет игрока в список модераторов.", "> staff remove <name> - Удаляет игрока из списка модераторов.", "> staff list - Показывает список модераторов.", "> staff clear - очищает весь список модераторов.");
    }
}

