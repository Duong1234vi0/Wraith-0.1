package ua.babka.command.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
import ua.babka.friend.Friend;
import ua.babka.friend.FriendRepository;

public class FriendCommand
extends Command {
    public FriendCommand(Babka onetap) {
        super("friend");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String action;
        switch (action = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list") {
            case "add": {
                this.handleAddFriend(args);
                break;
            }
            case "remove": {
                this.handleRemoveFriend(args);
                break;
            }
            case "list": {
                this.handleListFriends(args, label);
                break;
            }
            case "clear": {
                this.handleClearFriends(args);
                break;
            }
            default: {
                this.logDirect("Неизвестная подкоманда. Используй add/remove/list/clear.", Formatting.GRAY);
            }
        }
    }

    private void handleAddFriend(IArgConsumer args) throws CommandException {
        args.requireMin(1);
        String name = args.getString();
        if (FriendRepository.isFriend(name)) {
            this.logDirect("Этот игрок уже в списке друзей", Formatting.GRAY);
            return;
        }
        FriendRepository.addFriend(name);
        this.logDirect(String.valueOf(Formatting.GRAY) + "Игрок с именем " + String.valueOf(Formatting.WHITE) + name + String.valueOf(Formatting.GRAY) + " успешно добавлен в друзья");
    }

    private void handleRemoveFriend(IArgConsumer args) throws CommandException {
        args.requireMin(1);
        String name = args.getString();
        if (!FriendRepository.isFriend(name)) {
            this.logDirect("Такой друг не найден", Formatting.GRAY);
            return;
        }
        FriendRepository.removeFriend(name);
        this.logDirect("Игрок с именем " + String.valueOf(Formatting.WHITE) + name + String.valueOf(Formatting.GRAY) + " успешно удален из друзей");
    }

    private void handleListFriends(IArgConsumer args, String label) throws CommandException {
        args.requireMax(1);
        List<Friend> friends = FriendRepository.getFriends();
        Paginator.paginate(args, new Paginator<Friend>(friends), () -> this.logDirect("Список друзей:", Formatting.GRAY), friend -> {
            MutableText nameText = Text.literal((String)(String.valueOf(Formatting.GRAY) + "- " + String.valueOf(Formatting.WHITE) + friend.name()));
            MutableText deleteText = Text.literal((String)(String.valueOf(Formatting.RED) + " [Удалить]")).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, IBaritoneChatControl.FORCE_COMMAND_PREFIX + "friend remove " + friend.name())).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)Text.literal((String)"Click to delete friend"))));
            return nameText.copy().append((Text)deleteText);
        }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
    }

    private void handleClearFriends(IArgConsumer args) throws CommandException {
        args.requireMax(1);
        FriendRepository.clear();
        this.logDirect("Список друзей очищен", Formatting.GRAY);
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
                return FriendRepository.getFriends().stream().map(Friend::name).filter(name -> name.toLowerCase(Locale.ROOT).startsWith(prefix)).sorted().distinct();
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
        return "Управление друзьями";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Эта команда позволяет управлять списком друзей.", "", "Использование:", "> friend add <name> - Добавляет игрока в друзья.", "> friend remove <name> - Удаляет игрока из друзей.", "> friend list - Показывает список всех друзей.", "> friend clear - Удаляет всех друзей.");
    }
}

