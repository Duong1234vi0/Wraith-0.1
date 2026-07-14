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
import ua.babka.baritone.IBaritoneChatControl;
import ua.babka.command.CommandException;
import ua.babka.command.Paginator;
import ua.babka.command.TabCompleteHelper;
import ua.babka.command.argument.IArgConsumer;
import ua.babka.command.impl.Command;
import ua.babka.module.impl.combat.AttackAura;
import ua.babka.util.Instance;

public class TargetCommand
extends Command {
    public TargetCommand() {
        super("target");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String action;
        switch (action = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list") {
            case "add": {
                this.handleAdd(args);
                break;
            }
            case "remove": {
                this.handleRemove(args);
                break;
            }
            case "list": {
                this.handleList(args, label);
                break;
            }
            case "clear": {
                this.handleClear(args);
                break;
            }
            default: {
                this.logDirect("Неизвестная подкоманда. Используй add/remove/list/clear.", Formatting.GRAY);
            }
        }
    }

    private AttackAura getAttackAura() {
        return Instance.get(AttackAura.class);
    }

    private void handleAdd(IArgConsumer args) throws CommandException {
        args.requireMin(1);
        String name = args.getString();
        AttackAura aura = this.getAttackAura();
        if (aura == null) {
            this.logDirect("KillAura не найден", Formatting.GRAY);
            return;
        }
        if (aura.priorityTargets.contains(name)) {
            this.logDirect("Этот игрок уже в списке приоритетных целей", Formatting.GRAY);
            return;
        }
        aura.priorityTargets.add(name);
        this.logDirect(String.valueOf(Formatting.GRAY) + "Игрок " + String.valueOf(Formatting.WHITE) + name + String.valueOf(Formatting.GRAY) + " добавлен в приоритетные цели");
    }

    private void handleRemove(IArgConsumer args) throws CommandException {
        args.requireMin(1);
        String name = args.getString();
        AttackAura aura = this.getAttackAura();
        if (aura == null) {
            this.logDirect("KillAura не найден", Formatting.GRAY);
            return;
        }
        if (!aura.priorityTargets.remove(name)) {
            this.logDirect("Такой игрок не найден в списке", Formatting.GRAY);
            return;
        }
        this.logDirect("Игрок " + String.valueOf(Formatting.WHITE) + name + String.valueOf(Formatting.GRAY) + " удален из приоритетных целей");
    }

    private void handleList(IArgConsumer args, String label) throws CommandException {
        args.requireMax(1);
        AttackAura aura = this.getAttackAura();
        if (aura == null) {
            this.logDirect("KillAura не найден", Formatting.GRAY);
            return;
        }
        List<String> targets = aura.priorityTargets;
        Paginator.paginate(args, new Paginator<String>(targets), () -> this.logDirect("Приоритетные цели:", Formatting.GRAY), name -> {
            MutableText nameText = Text.literal((String)(String.valueOf(Formatting.GRAY) + "- " + String.valueOf(Formatting.WHITE) + name));
            MutableText deleteText = Text.literal((String)(String.valueOf(Formatting.RED) + " [Удалить]")).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, IBaritoneChatControl.FORCE_COMMAND_PREFIX + "target remove " + name)).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)Text.literal((String)"Click to remove target"))));
            return nameText.copy().append((Text)deleteText);
        }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
    }

    private void handleClear(IArgConsumer args) throws CommandException {
        args.requireMax(1);
        AttackAura aura = this.getAttackAura();
        if (aura == null) {
            this.logDirect("KillAura не найден", Formatting.GRAY);
            return;
        }
        aura.priorityTargets.clear();
        this.logDirect("Список приоритетных целей очищен", Formatting.GRAY);
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasAny() && args.hasExactlyOne()) {
            return new TabCompleteHelper().prepend("add", "remove", "list", "clear").filterPrefix(args.getString()).sortAlphabetically().stream();
        }
        if (args.hasAny()) {
            String action = args.peekString(0).toLowerCase(Locale.ROOT);
            AttackAura aura = this.getAttackAura();
            if (action.equals("remove") && args.hasExactly(2) && aura != null) {
                String prefix = args.peekString(1).toLowerCase(Locale.ROOT);
                return aura.priorityTargets.stream().filter(name -> name.toLowerCase(Locale.ROOT).startsWith(prefix)).sorted().distinct();
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
        return "Управление приоритетными целями для KillAura";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Эта команда позволяет управлять списком приоритетных целей для KillAura.", "", "Использование:", "> target add <name> - Добавляет игрока в приоритетные цели.", "> target remove <name> - Удаляет игрока из приоритетных целей.", "> target list - Показывает список всех приоритетных целей.", "> target clear - Удаляет всех из списка.");
    }
}

