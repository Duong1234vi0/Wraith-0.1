package ua.babka.command.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import ua.babka.Babka;
import ua.babka.baritone.IBaritoneChatControl;
import ua.babka.baritone.KeyDataType;
import ua.babka.command.CommandException;
import ua.babka.command.Paginator;
import ua.babka.command.TabCompleteHelper;
import ua.babka.command.argument.IArgConsumer;
import ua.babka.command.argument.ICommandArgument;
import ua.babka.command.impl.Command;
import ua.babka.module.Module;
import ua.babka.module.ModuleStorage;
import ua.babka.util.KeyStorage;

public class BindCommand
extends Command {
    private final ModuleStorage moduleStorage;

    public BindCommand(Babka onetap) {
        super("bind");
        this.moduleStorage = onetap.getModuleStorage();
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String action;
        switch (action = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list") {
            case "add": {
                this.handleAddBind(label, args);
                break;
            }
            case "remove": {
                this.handleRemoveBind(args);
                break;
            }
            case "list": {
                this.handleListBinds(args, label);
                break;
            }
            case "clear": {
                this.handleClearBinds(args);
                break;
            }
            default: {
                this.logDirect("Неизвестная подкоманда. Используй add/remove/list/clear.", Formatting.GRAY);
            }
        }
    }

    private void handleAddBind(String label, IArgConsumer args) throws CommandException {
        args.requireMin(2);
        String first = args.getString();
        Object module = this.moduleStorage.get(first);
        if (module == null) {
            StringBuilder nameBuilder = new StringBuilder(first);
            while (args.has(2)) {
                nameBuilder.append(" ").append(args.getString());
                module = this.moduleStorage.get(nameBuilder.toString());
                if (module == null) continue;
            }
        }
        if (module == null) {
            this.logDirect(String.valueOf(Formatting.GRAY) + "Модуль с названием " + String.valueOf(Formatting.WHITE) + first + String.valueOf(Formatting.GRAY) + " не найден");
            return;
        }
        int key = (Integer)((Map.Entry)args.getDatatypeFor(KeyDataType.INSTANCE)).getValue();
        ((Module)module).setKey(key);
        this.logDirect(String.valueOf(Formatting.GRAY) + "Модуль " + String.valueOf(Formatting.WHITE) + ((Module)module).getName() + String.valueOf(Formatting.GRAY) + " успешно привязан к клавише " + String.valueOf(Formatting.WHITE) + KeyStorage.getKey(key).toUpperCase());
    }

    private void handleRemoveBind(IArgConsumer args) throws CommandException {
        args.requireMin(1);
        String moduleName = args.getString();
        Object module = this.moduleStorage.get(moduleName);
        if (module == null) {
            this.logDirect(String.valueOf(Formatting.GRAY) + "Модуль с названием " + String.valueOf(Formatting.WHITE) + moduleName + String.valueOf(Formatting.GRAY) + " не найден");
            return;
        }
        ((Module)module).setKey(-1);
        this.logDirect(String.valueOf(Formatting.GRAY) + "Модуль " + String.valueOf(Formatting.WHITE) + ((Module)module).getName() + String.valueOf(Formatting.GRAY) + " больше не имеет привязку к клавише");
    }

    private void handleListBinds(IArgConsumer args, String label) throws CommandException {
        args.requireMax(1);
        List boundModules = this.moduleStorage.getModules().stream().filter(module -> module.getKey() != -1).collect(Collectors.toList());
        Paginator.paginate(args, new Paginator(boundModules), () -> this.logDirect("Привязанные модули:"), module -> {
            int key = module.getKey();
            String keyName = KeyStorage.getKey(key).toUpperCase();
            return Text.literal((String)(String.valueOf(Formatting.GRAY) + module.getName() + ": " + String.valueOf(Formatting.WHITE) + keyName));
        }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
    }

    private void handleClearBinds(IArgConsumer args) throws CommandException {
        args.requireMax(1);
        for (Module m2 : this.moduleStorage.getModules()) {
            if (m2.getName().equals("Click Gui")) continue;
            m2.setKey(-1);
        }
        this.logDirect(String.valueOf(Formatting.GRAY) + "Все модули успешно отвязаны и больше не имеют привязку к клавише");
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        LinkedList<ICommandArgument> raw = args.getArgs();
        int size = raw.size();
        if (size <= 1) {
            String prefix = size == 1 ? args.peekString(0) : "";
            return new TabCompleteHelper().prepend("add", "remove", "list", "clear").filterPrefix(prefix).sortAlphabetically().stream();
        }
        String action = args.peekString(0).toLowerCase(Locale.ROOT);
        if (action.equals("add") && size == 2) {
            String modulePrefix = args.peekString(1);
            return this.moduleStorage.getModules().stream().map(Module::getName).filter(n2 -> n2.toLowerCase(Locale.ROOT).startsWith(modulePrefix.toLowerCase(Locale.ROOT))).sorted().distinct();
        }
        if (action.equals("add") && size == 3) {
            String keyPrefix = args.peekString(2);
            return KeyStorage.keyMap.keySet().stream().filter(k2 -> k2.toLowerCase(Locale.ROOT).startsWith(keyPrefix.toLowerCase(Locale.ROOT))).sorted().distinct();
        }
        if (action.equals("remove") && size == 2) {
            String modulePrefix = args.peekString(1);
            return this.moduleStorage.getModules().stream().filter(m2 -> m2.getKey() != -1).map(Module::getName).filter(n2 -> n2.toLowerCase(Locale.ROOT).startsWith(modulePrefix.toLowerCase(Locale.ROOT))).sorted().distinct();
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Управление привязкой модуля к клавише";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Команда для установки и удаления привязки модуля к клавише", "", "Использование:", "> bind add <модуль> <клавиша> — привязать модуль к клавише", "> bind remove <модуль> — отвязать модуль от клавиши", "> bind list — показать все привязанные модули", "> bind clear — отвязать все модули от своих клавиш");
    }
}

