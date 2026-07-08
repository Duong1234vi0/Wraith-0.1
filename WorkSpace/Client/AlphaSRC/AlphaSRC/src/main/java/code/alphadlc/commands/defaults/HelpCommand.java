/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2558
 *  net.minecraft.class_2558$class_2559
 *  net.minecraft.class_2561
 *  net.minecraft.class_2568
 *  net.minecraft.class_2568$class_5247
 *  net.minecraft.class_5250
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.AlphaDLC;
import code.alphadlc.commands.manager.CommandRepository;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.IBaritoneChatControl;
import code.alphadlc.utils.client.managers.api.command.ICommand;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.exception.CommandNotFoundException;
import code.alphadlc.utils.client.managers.api.command.helpers.Paginator;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_5250;

public class HelpCommand
extends Command {
    AlphaDLC main;

    protected HelpCommand(AlphaDLC main) {
        super("help");
        this.main = main;
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(1);
        CommandRepository commandRepository = this.main.getCommandRepository();
        if (!args.hasAny() || args.is(Integer.class)) {
            Paginator.paginate(args, new Paginator(commandRepository.getRegistry().descendingStream().filter(command -> !command.hiddenFromHelp()).collect(Collectors.toList())), () -> this.logDirect("\u0414\u043e\u0441\u0442\u0443\u043f\u043d\u044b\u0435 \u043a\u043e\u043c\u0430\u043d\u0434\u044b:"), command -> {
                String names = String.join((CharSequence)"/", command.getNames());
                String name = command.getNames().get(0);
                class_5250 shortDescComponent = class_2561.method_43470((String)(" - " + command.getShortDesc()));
                shortDescComponent.method_10862(shortDescComponent.method_10866().method_10977(class_124.field_1068));
                class_5250 namesComponent = class_2561.method_43470((String)names);
                namesComponent.method_10862(namesComponent.method_10866().method_10977(class_124.field_1068));
                class_5250 hoverComponent = class_2561.method_43470((String)"");
                hoverComponent.method_10862(hoverComponent.method_10866().method_10977(class_124.field_1080));
                hoverComponent.method_10852((class_2561)namesComponent);
                hoverComponent.method_27693("\n" + command.getShortDesc());
                hoverComponent.method_27693("\n\n\u041d\u0430\u0436\u043c\u0438\u0442\u0435, \u0447\u0442\u043e\u0431\u044b \u043f\u0440\u043e\u0441\u043c\u043e\u0442\u0440\u0435\u0442\u044c \u043f\u043e\u043b\u043d\u0443\u044e \u0441\u043f\u0440\u0430\u0432\u043a\u0443 \u043e \u043a\u043e\u043c\u0430\u043d\u0434\u0435");
                String clickCommand = IBaritoneChatControl.FORCE_COMMAND_PREFIX + String.format("%s %s", label, command.getNames().get(0));
                class_5250 component = class_2561.method_43470((String)name);
                component.method_10862(component.method_10866().method_10977(class_124.field_1068));
                component.method_10852((class_2561)shortDescComponent);
                component.method_10862(component.method_10866().method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)hoverComponent)).method_10958(new class_2558(class_2558.class_2559.field_11750, clickCommand)));
                return component;
            }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
        } else {
            String commandName = args.getString().toLowerCase();
            ICommand command2 = commandRepository.getCommand(commandName);
            if (command2 == null) {
                throw new CommandNotFoundException(commandName);
            }
            this.logDirect("");
            command2.getLongDesc().forEach(this::logDirect);
            this.logDirect("");
            class_5250 returnComponent = class_2561.method_43470((String)"\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e \u0431\u044b \u0432\u0435\u0440\u043d\u0443\u0442\u044c\u0441\u044f \u043e\u0431\u0440\u0430\u0442\u043d\u043e \u0432 \u043c\u0435\u043d\u044e");
            returnComponent.method_10862(returnComponent.method_10866().method_10958(new class_2558(class_2558.class_2559.field_11750, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label)));
            this.logDirect(new class_2561[]{returnComponent});
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasExactlyOne()) {
            return new TabCompleteHelper().addCommands(AlphaDLC.getInstance().getCommandRepository()).filterPrefix(args.getString()).stream();
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "\u041f\u0440\u043e\u0441\u043c\u043e\u0442\u0440 \u0432\u0441\u0435\u0445 \u0434\u043e\u0441\u0442\u0443\u043f\u043d\u044b\u0445 \u043a\u043e\u043c\u0430\u043d\u0434";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u0421 \u043f\u043e\u043c\u043e\u0449\u044c\u044e \u044d\u0442\u043e\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u044b \u043c\u043e\u0436\u043d\u043e \u043f\u0440\u043e\u0441\u043c\u043e\u0442\u0440\u0435\u0442\u044c \u043f\u043e\u0434\u0440\u043e\u0431\u043d\u0443\u044e \u0441\u043f\u0440\u0430\u0432\u043e\u0447\u043d\u0443\u044e \u0438\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u044e \u043e \u0442\u043e\u043c, \u043a\u0430\u043a \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0435\u043d\u043d\u044b\u0435 \u043a\u043e\u043c\u0430\u043d\u0434\u044b", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435:", "> help - \u041f\u0435\u0440\u0435\u0447\u0438\u0441\u043b\u044f\u0435\u0442 \u0432\u0441\u0435 \u043a\u043e\u043c\u0430\u043d\u0434\u044b \u0438 \u0438\u0445 \u043a\u0440\u0430\u0442\u043a\u0438\u0435 \u043e\u043f\u0438\u0441\u0430\u043d\u0438\u044f.", "> help <command> - \u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0435\u043d\u0438\u0435 \u0441\u043f\u0440\u0430\u0432\u043e\u0447\u043d\u043e\u0439 \u0438\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u0438 \u043f\u043e \u043a\u043e\u043d\u043a\u0440\u0435\u0442\u043d\u043e\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u0435.");
    }
}
