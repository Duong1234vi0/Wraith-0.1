/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.common.repository.target.TargetRepository;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.IBaritoneChatControl;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.datatypes.TabPlayerDataType;
import code.alphadlc.utils.client.managers.api.command.datatypes.TargetDataType;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.Paginator;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import code.alphadlc.utils.client.managers.api.command.manager.ICommandManager;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2561;

public class TargetCommand
extends Command
implements QuickImports {
    private final TargetRepository repository = TargetRepository.getInstance();

    public TargetCommand() {
        super("target");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String action;
        if (!args.hasAny()) {
            this.handleListTargets(label, args);
            return;
        }
        switch (action = args.peekString().toLowerCase(Locale.US)) {
            case "add": {
                args.get();
                this.handleAddTarget(args);
                break;
            }
            case "remove": {
                args.get();
                this.handleRemoveTarget(args);
                break;
            }
            case "clear": {
                args.get();
                this.handleClearTargets(args);
                break;
            }
            case "list": {
                args.get();
                this.handleListTargets(label, args);
                break;
            }
            default: {
                this.handleAddTarget(args);
            }
        }
    }

    private void handleAddTarget(IArgConsumer args) throws CommandException {
        args.requireMin(1);
        String name = args.getString();
        if (!this.repository.isTarget(name)) {
            this.repository.addTarget(name);
            this.logDirect("\u0418\u0433\u0440\u043e\u043a " + String.valueOf(class_124.field_1060) + name + String.valueOf(class_124.field_1080) + " \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d \u0432 \u043f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442\u043d\u044b\u0439 \u0441\u043f\u0438\u0441\u043e\u043a.", class_124.field_1080);
        } else {
            this.logDirect("\u0418\u0433\u0440\u043e\u043a " + String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1080) + " \u0443\u0436\u0435 \u0432 \u043f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442\u043d\u043e\u043c \u0441\u043f\u0438\u0441\u043a\u0435.", class_124.field_1061);
        }
    }

    private void handleRemoveTarget(IArgConsumer args) throws CommandException {
        args.requireMin(1);
        String name = args.getString();
        if (this.repository.isTarget(name)) {
            this.repository.removeTarget(name);
            this.logDirect("\u0418\u0433\u0440\u043e\u043a " + String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1080) + " \u0443\u0434\u0430\u043b\u0435\u043d \u0438\u0437 \u043f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442\u043d\u043e\u0433\u043e \u0441\u043f\u0438\u0441\u043a\u0430.", class_124.field_1080);
        } else {
            this.logDirect("\u0418\u0433\u0440\u043e\u043a " + String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1080) + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u043f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442\u043d\u043e\u043c \u0441\u043f\u0438\u0441\u043a\u0435.", class_124.field_1061);
        }
    }

    private void handleClearTargets(IArgConsumer args) throws CommandException {
        args.requireMax(0);
        this.repository.clearTargets();
        this.logDirect("\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442\u043d\u044b\u0439 \u0441\u043f\u0438\u0441\u043e\u043a \u0446\u0435\u043b\u0435\u0439 \u043e\u0447\u0438\u0449\u0435\u043d.", class_124.field_1060);
    }

    private void handleListTargets(String label, IArgConsumer args) throws CommandException {
        args.requireMax(1);
        Paginator.paginate(args, new Paginator<String>(this.repository.getTargets()), () -> this.logDirect("\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442\u043d\u044b\u0439 \u0441\u043f\u0438\u0441\u043e\u043a \u0446\u0435\u043b\u0435\u0439:"), targetName -> class_2561.method_43470((String)(String.valueOf(class_124.field_1080) + "- " + String.valueOf(class_124.field_1068) + targetName)), IBaritoneChatControl.FORCE_COMMAND_PREFIX + label + " list");
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasExactlyOne()) {
            return new TabCompleteHelper().prepend("add", "remove", "list", "clear").filterPrefix(args.peekString()).addCommands((ICommandManager)mc.method_1562().method_2875()).stream();
        }
        if (args.hasAtMost(2) && args.peekString(0).equalsIgnoreCase("add")) {
            return args.tabCompleteDatatype(TabPlayerDataType.INSTANCE);
        }
        if (args.hasAtMost(2) && args.peekString(0).equalsIgnoreCase("remove")) {
            return args.tabCompleteDatatype(TargetDataType.INSTANCE);
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "\u0423\u043f\u0440\u0430\u0432\u043b\u044f\u0435\u0442 \u043f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442\u043d\u044b\u043c\u0438 \u0446\u0435\u043b\u044f\u043c\u0438 \u0434\u043b\u044f Aura.";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0435\u0442 \u0438\u0433\u0440\u043e\u043a\u043e\u0432 \u0432 \u043f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442\u043d\u044b\u0439 \u0441\u043f\u0438\u0441\u043e\u043a \u0434\u043b\u044f \u043c\u043e\u0434\u0443\u043b\u044f Aura.", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435:", "> target <\u043d\u0438\u043a> - \u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u0438\u0433\u0440\u043e\u043a\u0430 \u0432 \u043f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442.", "> target add <\u043d\u0438\u043a> - \u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u0438\u0433\u0440\u043e\u043a\u0430 \u0432 \u043f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442.", "> target remove <\u043d\u0438\u043a> - \u0423\u0434\u0430\u043b\u0438\u0442\u044c \u0438\u0433\u0440\u043e\u043a\u0430 \u0438\u0437 \u043f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442\u0430.", "> target list - \u041f\u043e\u043a\u0430\u0437\u0430\u0442\u044c \u0441\u043f\u0438\u0441\u043e\u043a \u043f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442\u043d\u044b\u0445 \u0438\u0433\u0440\u043e\u043a\u043e\u0432.", "> target clear - \u041e\u0447\u0438\u0441\u0442\u0438\u0442\u044c \u0441\u043f\u0438\u0441\u043e\u043a.");
    }
}
