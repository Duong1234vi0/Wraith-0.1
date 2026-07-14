package ua.babka.command.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import ua.babka.Babka;
import ua.babka.baritone.IBaritoneChatControl;
import ua.babka.command.CommandException;
import ua.babka.command.CommandNotEnoughArgumentsException;
import ua.babka.command.Paginator;
import ua.babka.command.TabCompleteHelper;
import ua.babka.command.argument.IArgConsumer;
import ua.babka.command.impl.Command;
import ua.babka.core.ConfigManager;
import ua.babka.module.Module;

public class CfgCommand
extends Command {
    public CfgCommand() {
        super("cfg");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String action;
        switch (action = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list") {
            case "save": {
                this.handleSave(args);
                break;
            }
            case "load": {
                this.handleLoad(args);
                break;
            }
            case "list": {
                this.handleList(args, label);
                break;
            }
            case "clear": {
                List<String> configs = ConfigManager.getConfigs();
                for (String name : configs) {
                    Path file = Paths.get("BabkaCleint/configs", new String[0]).resolve(name + ".json");
                    if (!Files.exists(file, new LinkOption[0])) continue;
                    try {
                        Files.delete(file);
                    }
                    catch (IOException e2) {
                        this.logDirect(String.valueOf(Formatting.GRAY) + "Ошибка при удалении файла.");
                        e2.printStackTrace();
                    }
                }
                this.logDirect("Список конфигов очищен", Formatting.GRAY);
                break;
            }
            case "dir": {
                try {
                    File dir = new File("BabkaCleint/configs/");
                    if (!dir.exists()) {
                        this.logDirect(String.valueOf(Formatting.GRAY) + "Зач папку удалил?");
                        dir.mkdirs();
                    } else {
                        this.logDirect(String.valueOf(Formatting.GRAY) + "Открываю папку с конфигами...");
                    }
                    Runtime.getRuntime().exec("explorer " + dir.getAbsolutePath());
                }
                catch (IOException e3) {
                    this.logDirect(String.valueOf(Formatting.GRAY) + "Ошибка при открытии папки: " + String.valueOf(Formatting.WHITE) + e3.getMessage());
                }
                break;
            }
            case "reset": {
                this.handleReset();
                break;
            }
            case "remove": {
                this.handleRemove(args);
                break;
            }
            default: {
                this.logDirect("Неизвестная подкоманда. Используй load/save/remove/list/dir/reset.", Formatting.GRAY);
            }
        }
    }

    private void handleSave(IArgConsumer args) throws CommandException {
        args.requireExactly(1);
        String name = args.getString();
        ConfigManager.save(name);
        this.logDirect(String.valueOf(Formatting.GRAY) + "Конфиг с именем " + String.valueOf(Formatting.WHITE) + name + String.valueOf(Formatting.GRAY) + " успешно сохранён");
    }

    private void handleLoad(IArgConsumer args) throws CommandException {
        args.requireExactly(1);
        String name = args.getString();
        if (!ConfigManager.getConfigs().contains(name)) {
            this.logDirect(String.valueOf(Formatting.GRAY) + "Конфиг с таким именем не найден");
            return;
        }
        ConfigManager.load(name);
        this.logDirect(String.valueOf(Formatting.GRAY) + "Конфиг с именем " + String.valueOf(Formatting.WHITE) + name + String.valueOf(Formatting.GRAY) + " успешно загружен");
    }

    private void handleList(IArgConsumer args, String label) throws CommandException {
        args.requireMax(1);
        List<String> configs = ConfigManager.getConfigs();
        this.logDirect("Список конфигов:", Formatting.GRAY);
        Paginator.paginate(args, new Paginator<String>(configs), name -> {
            MutableText nameText = Text.literal((String)(String.valueOf(Formatting.GRAY) + "- " + String.valueOf(Formatting.WHITE) + name + " "));
            MutableText loadText = Text.literal((String)(String.valueOf(Formatting.GREEN) + "[Загрузить]")).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, IBaritoneChatControl.FORCE_COMMAND_PREFIX + "cfg load " + name)).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)Text.literal((String)"Click to load config"))));
            MutableText deleteText = Text.literal((String)(String.valueOf(Formatting.RED) + " [Удалить]")).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, IBaritoneChatControl.FORCE_COMMAND_PREFIX + "cfg remove " + name)).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)Text.literal((String)"Click to delete config"))));
            return nameText.copy().append((Text)loadText).append((Text)deleteText);
        }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
    }

    private void handleReset() {
        if (ConfigManager.exists("default")) {
            ConfigManager.load("default");
        } else {
            for (Module module : Babka.getInstance().getModuleStorage().getModules()) {
                module.setEnabled(false);
                module.setKey(-1);
            }
        }
        this.logDirect("Все настройки и бинды сброшены.", Formatting.GRAY);
    }

    private void handleRemove(IArgConsumer args) throws CommandException {
        args.requireExactly(1);
        String name = args.getString();
        Path file = Paths.get("BabkaCleint/configs", new String[0]).resolve(name + ".json");
        if (Files.exists(file, new LinkOption[0])) {
            try {
                Files.delete(file);
                this.logDirect(String.valueOf(Formatting.GRAY) + "Конфиг " + String.valueOf(Formatting.WHITE) + name + String.valueOf(Formatting.GRAY) + " успешно удалён");
            }
            catch (IOException e2) {
                this.logDirect(String.valueOf(Formatting.GRAY) + "Ошибка при удалении файла.");
                e2.printStackTrace();
            }
        } else {
            this.logDirect(String.valueOf(Formatting.GRAY) + "Конфиг не найден");
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasAny() && args.hasExactlyOne()) {
            return new TabCompleteHelper().sortAlphabetically().prepend("load", "save", "remove", "list", "clear", "dir", "reset").filterPrefix(args.getString()).stream();
        }
        if (args.hasAny()) {
            String arg = args.getString();
            if (args.hasExactlyOne() && (arg.equalsIgnoreCase("load") || arg.equalsIgnoreCase("remove"))) {
                return ConfigManager.getConfigs().stream().filter(cfg -> {
                    try {
                        return cfg.startsWith(args.peekString());
                    }
                    catch (CommandNotEnoughArgumentsException e2) {
                        throw new RuntimeException(e2);
                    }
                }).sorted();
            }
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Управление конфигами";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Команда для управления конфигурациями клиента.", "", "Использование:", "> cfg save <name> - Сохраняет текущую конфигурацию.", "> cfg load <name> - Загружает конфигурацию.", "> cfg list - Показывает все доступные конфиги.", "> cfg remove <name> - Удаляет конфиг по имени.", "> cfg reset - Сбрасывает все настройки, бинды и макросы.");
    }
}

