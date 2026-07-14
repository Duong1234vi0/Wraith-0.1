package ua.babka.command.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import ua.babka.Babka;
import ua.babka.baritone.IBaritoneChatControl;
import ua.babka.command.CommandException;
import ua.babka.command.CommandNotFoundException;
import ua.babka.command.CommandRepository;
import ua.babka.command.Paginator;
import ua.babka.command.TabCompleteHelper;
import ua.babka.command.argument.IArgConsumer;
import ua.babka.command.impl.Command;
import ua.babka.command.impl.ICommand;

public class HelpCommand
extends Command {
    Babka onetap;

    protected HelpCommand(Babka onetap) {
        super("help");
        this.onetap = onetap;
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(1);
        CommandRepository commandRepository = this.onetap.getCommandRepository();
        if (!args.hasAny() || args.is(Integer.class)) {
            Paginator.paginate(args, new Paginator(commandRepository.getRegistry().descendingStream().filter(command -> !command.hiddenFromHelp()).collect(Collectors.toList())), () -> this.logDirect("Доступные команды в чите:"), command -> {
                String names = String.join((CharSequence)"/", command.getNames());
                String name = command.getNames().get(0);
                MutableText shortDescComponent = Text.literal((String)(String.valueOf(Formatting.DARK_GRAY) + " - " + String.valueOf(Formatting.GRAY) + command.getShortDesc()));
                shortDescComponent.setStyle(shortDescComponent.getStyle().withColor(Formatting.GRAY));
                MutableText namesComponent = Text.literal((String)names);
                namesComponent.setStyle(namesComponent.getStyle().withColor(Formatting.WHITE));
                MutableText hoverComponent = Text.literal((String)"");
                hoverComponent.setStyle(hoverComponent.getStyle().withColor(Formatting.GRAY));
                hoverComponent.append((Text)namesComponent);
                hoverComponent.append("\n" + command.getShortDesc());
                hoverComponent.append("\n\nНажмите, чтобы просмотреть полную справку о команде");
                String clickCommand = IBaritoneChatControl.FORCE_COMMAND_PREFIX + String.format("%s %s", label, command.getNames().get(0));
                MutableText component = Text.literal((String)name);
                component.setStyle(component.getStyle().withColor(Formatting.GRAY));
                component.append((Text)shortDescComponent);
                component.setStyle(component.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)hoverComponent)).withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, clickCommand)));
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
            MutableText returnComponent = Text.literal((String)"Нажмите что бы вернуться обратно в меню");
            returnComponent.setStyle(returnComponent.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label)));
            this.logDirect(new Text[]{returnComponent});
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasExactlyOne()) {
            return new TabCompleteHelper().addCommands(Babka.getInstance().getCommandRepository()).filterPrefix(args.getString()).stream();
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Просмотр всех доступных команд";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("> help - Перечисляет все команды и их краткие описания.", "> help <command> - Отображение справочной информации по конкретной команде.");
    }
}

