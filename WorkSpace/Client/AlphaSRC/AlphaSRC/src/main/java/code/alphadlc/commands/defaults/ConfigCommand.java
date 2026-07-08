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
import code.alphadlc.main.client.ClientInfoProvider;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.IBaritoneChatControl;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.datatypes.ConfigFileDataType;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.Paginator;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import code.alphadlc.utils.client.managers.file.FileController;
import code.alphadlc.utils.client.managers.file.FileRepository;
import code.alphadlc.utils.client.managers.file.exception.FileProcessingException;
import code.alphadlc.utils.config.ConfigManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class ConfigCommand
extends Command {
    private final FileController fileController;
    private final ClientInfoProvider clientInfoProvider;

    protected ConfigCommand(AlphaDLC main) {
        super("config", "cfg");
        this.fileController = main.getFileController();
        this.clientInfoProvider = main.getClientInfoProvider();
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String name;
        String arg = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list";
        args.requireMax(1);
        if (arg.contains("load")) {
            name = args.getString();
            if (new File(this.clientInfoProvider.configsDir(), name + ".json").exists()) {
                try {
                    FileRepository fileRepository = new FileRepository();
                    fileRepository.setup(AlphaDLC.getInstance());
                    FileController fileController = new FileController(fileRepository.getClientFiles(), this.clientInfoProvider.filesDir(), this.clientInfoProvider.configsDir());
                    fileController.loadFile(name + ".json");
                    ChatMessage.brandmessage(String.format("\u041a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f %s \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u0430!", name));
                }
                catch (FileProcessingException e) {
                    this.logDirect(String.format("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0435 \u043a\u043e\u043d\u0444\u0438\u0433\u0430! \u0414\u0435\u0442\u0430\u043b\u0438: %s", e.getCause().getMessage()), class_124.field_1061);
                }
            } else {
                ChatMessage.brandmessage(String.format("\u041a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f %s \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430!", name));
            }
        }
        if (arg.contains("save")) {
            name = args.getString();
            try {
                this.fileController.saveFile(name + ".json");
                ChatMessage.brandmessage(String.format("\u041a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f %s \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0430!", name));
                System.out.println("loaded");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.printf("error %s%n", e.getCause().getMessage());
                this.logDirect(String.format("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u0438 \u043a\u043e\u043d\u0444\u0438\u0433\u0430! \u0414\u0435\u0442\u0430\u043b\u0438: %s", e.getCause().getMessage()), class_124.field_1061);
            }
        }
        if (arg.contains("list")) {
            Paginator.paginate(args, new Paginator<String>(this.getConfigs()), () -> this.logDirect("\u0421\u043f\u0438\u0441\u043e\u043a \u043a\u043e\u043d\u0444\u0438\u0433\u043e\u0432:"), config -> {
                class_5250 namesComponent = class_2561.method_43470((String)config);
                namesComponent.method_10862(namesComponent.method_10866().method_10977(class_124.field_1068));
                return namesComponent;
            }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
        }
        if (arg.contains("dir")) {
            try {
                Runtime.getRuntime().exec("explorer " + this.clientInfoProvider.configsDir().getAbsolutePath());
            }
            catch (IOException e) {
                ChatMessage.brandmessage("\u041f\u0430\u043f\u043a\u0430 \u0441 \u043a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f\u043c\u0438 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430!" + e.getMessage());
            }
        }
        if (arg.contains("reset")) {
            if (ConfigManager.resetConfig()) {
                ChatMessage.brandmessage("\u0412\u0441\u0435 \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438 \u0441\u0431\u0440\u043e\u0448\u0435\u043d\u044b \u043a \u0434\u0435\u0444\u043e\u043b\u0442\u043d\u044b\u043c \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f\u043c!");
            } else {
                this.logDirect("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u0431\u0440\u043e\u0441\u0435 \u043a\u043e\u043d\u0444\u0438\u0433\u0430!", class_124.field_1061);
            }
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasAny()) {
            String arg = args.getString();
            if (args.hasExactlyOne()) {
                if (arg.equalsIgnoreCase("load")) {
                    return args.tabCompleteDatatype(ConfigFileDataType.INSTANCE);
                }
                if (arg.equalsIgnoreCase("save")) {
                    return args.tabCompleteDatatype(ConfigFileDataType.INSTANCE);
                }
            } else {
                return new TabCompleteHelper().sortAlphabetically().prepend("load", "save", "list", "dir", "reset").filterPrefix(arg).stream();
            }
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0435\u0442 \u0432\u0437\u0430\u0438\u043c\u043e\u0434\u0435\u0439\u0441\u0442\u0432\u043e\u0432\u0430\u0442\u044c \u0441 \u043a\u043e\u043d\u0444\u0438\u0433\u0430\u043c\u0438 \u0432 \u0447\u0438\u0442\u0435";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u0421 \u043f\u043e\u043c\u043e\u0449\u044c\u044e \u044d\u0442\u043e\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u044b \u043c\u043e\u0436\u043d\u043e \u0437\u0430\u0433\u0440\u0443\u0436\u0430\u0442\u044c/\u0441\u043e\u0445\u0440\u0430\u043d\u044f\u0442\u044c \u043a\u043e\u043d\u0444\u0438\u0433\u0438", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435:", "> config load <name> - \u0417\u0430\u0433\u0440\u0443\u0436\u0430\u0435\u0442 \u043a\u043e\u043d\u0444\u0438\u0433.", "> config save <name> - \u0421\u043e\u0445\u0440\u0430\u043d\u044f\u0435\u0442 \u043a\u043e\u043d\u0444\u0438\u0433.", "> config list - \u0412\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0435\u0442 \u0441\u043f\u0438\u0441\u043e\u043a \u043a\u043e\u043d\u0444\u0438\u0433\u043e\u0432", "> config dir - \u041e\u0442\u043a\u0440\u044b\u0432\u0430\u0435\u0442 \u043f\u0430\u043f\u043a\u0443 \u0441 \u043a\u043e\u043d\u0444\u0438\u0433\u0430\u043c\u0438.", "> config reset - \u0421\u0431\u0440\u0430\u0441\u044b\u0432\u0430\u0435\u0442 \u0432\u0441\u0435 \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438 \u043a \u0434\u0435\u0444\u043e\u043b\u0442\u043d\u044b\u043c \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f\u043c.");
    }

    public List<String> getConfigs() {
        ArrayList<String> configs = new ArrayList<String>();
        File[] configFiles = AlphaDLC.getInstance().getClientInfoProvider().configsDir().listFiles();
        if (configFiles != null) {
            for (File configFile : configFiles) {
                if (!configFile.isFile() || !configFile.getName().endsWith(".json")) continue;
                String configName = configFile.getName().replace(".json", "");
                configs.add(configName);
            }
        }
        return configs;
    }
}
