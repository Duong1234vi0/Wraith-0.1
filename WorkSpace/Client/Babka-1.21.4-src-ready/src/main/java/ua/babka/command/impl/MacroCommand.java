package ua.babka.command.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import ua.babka.macro.Macro;
import ua.babka.macro.MacroRepository;
import ua.babka.util.KeyStorage;

public class MacroCommand
extends Command {
    private final MacroRepository macroRepository;

    public MacroCommand(Babka onetap) {
        super("macro");
        this.macroRepository = onetap.getMacroRepository();
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String action;
        switch (action = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list") {
            case "add": {
                this.handleAddMacro(args);
                break;
            }
            case "remove": {
                this.handleRemoveMacro(args);
                break;
            }
            case "list": {
                this.handleListMacros(args, label);
                break;
            }
            case "clear": {
                this.handleClearMacros(args);
                break;
            }
            default: {
                this.logDirect("Используй: add / remove / list / clear", Formatting.GRAY);
            }
        }
    }

    private void handleAddMacro(IArgConsumer args) throws CommandException {
        args.requireMin(2);
        int key = (Integer)((Map.Entry)args.getDatatypeFor(KeyDataType.INSTANCE)).getValue();
        String command = args.rawRest();
        this.macroRepository.addMacro(command, key);
        this.logDirect(String.valueOf(Formatting.GRAY) + "Макрос добавлен: " + String.valueOf(Formatting.WHITE) + KeyStorage.getKey(key).toLowerCase() + String.valueOf(Formatting.GRAY) + " → " + String.valueOf(Formatting.WHITE) + command);
    }

    private void handleRemoveMacro(IArgConsumer args) throws CommandException {
        args.requireMin(1);
        int key = (Integer)((Map.Entry)args.getDatatypeFor(KeyDataType.INSTANCE)).getValue();
        if (this.macroRepository.removeByKey(key)) {
            this.logDirect(String.valueOf(Formatting.GRAY) + "Макрос на клавише " + String.valueOf(Formatting.WHITE) + KeyStorage.getKey(key).toLowerCase() + String.valueOf(Formatting.GRAY) + " удалён");
        } else {
            this.logDirect("Макрос на этой клавише не найден", Formatting.GRAY);
        }
    }

    private void handleListMacros(IArgConsumer args, String label) throws CommandException {
        Paginator.paginate(args, new Paginator<Macro>(this.macroRepository.getMacroList()), () -> this.logDirect("Список макросов:"), macro -> Text.literal((String)(String.valueOf(Formatting.GRAY) + " " + KeyStorage.getKey(macro.key()).toLowerCase() + String.valueOf(Formatting.DARK_GRAY) + " → " + String.valueOf(Formatting.WHITE) + macro.message())), IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
    }

    private void handleClearMacros(IArgConsumer args) {
        this.macroRepository.clearList();
        this.logDirect("Все макросы очищены", Formatting.GRAY);
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        String action;
        LinkedList<ICommandArgument> raw = args.getArgs();
        int size = raw.size();
        if (args.hasExactlyOne()) {
            return new TabCompleteHelper().prepend("add", "remove", "list", "clear").filterPrefix(args.getString()).sortAlphabetically().stream();
        }
        if (args.hasAny() && ((action = args.getString()).equalsIgnoreCase("add") || action.equalsIgnoreCase("remove")) && size == 2) {
            return args.tabCompleteDatatype(KeyDataType.INSTANCE);
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Макросы по клавишам";
    }

    @Override
    public List<String> getLongDesc() {
        return List.of("Использование:", ".macro add <key> <command>", ".macro remove <key>", ".macro list", ".macro clear");
    }
}

