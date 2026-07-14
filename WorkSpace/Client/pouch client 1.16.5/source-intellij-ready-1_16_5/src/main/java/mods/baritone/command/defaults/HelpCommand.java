/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.D_4024_W;
import lightning.product.U_2871_b;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.IBaritoneChatControl;
import mods.baritone.api.api.java.baritone.api.command.ICommand;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandNotFoundException;
import mods.baritone.api.api.java.baritone.api.command.helpers.Paginator;
import mods.baritone.api.api.java.baritone.api.command.helpers.TabCompleteHelper;

public class HelpCommand
extends Command {
    public HelpCommand(IBaritone baritone) {
        super(baritone, "help", "?");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(1);
        if (!args.hasAny() || args.is(Integer.class)) {
            Paginator.paginate(args, new Paginator(this.baritone.getCommandManager().getRegistry().descendingStream().filter(command -> !command.hiddenFromHelp()).collect(Collectors.toList())), () -> this.logDirect("All Baritone commands (clickable):"), command -> {
                String names = String.join((CharSequence)"/", command.getNames());
                String name = command.getNames().get(0);
                U_2871_b shortDescComponent = new U_2871_b(" - " + command.getShortDesc());
                shortDescComponent.n_1700_B(shortDescComponent.n_1700_B().n_1700_B(D_4024_W.t_148_a));
                U_2871_b namesComponent = new U_2871_b(names);
                namesComponent.n_1700_B(namesComponent.n_1700_B().n_1700_B(D_4024_W.M_182_A));
                U_2871_b hoverComponent = new U_2871_b("");
                hoverComponent.n_1700_B(hoverComponent.n_1700_B().n_1700_B(D_4024_W.w_1484_f));
                hoverComponent.n_1700_B(namesComponent);
                hoverComponent.n_1700_B("\n" + command.getShortDesc());
                hoverComponent.n_1700_B("\n\nClick to view full help");
                String clickCommand = IBaritoneChatControl.FORCE_COMMAND_PREFIX + String.format("%s %s", label, command.getNames().get(0));
                U_2871_b component = new U_2871_b(name);
                component.n_1700_B(component.n_1700_B().n_1700_B(D_4024_W.w_1484_f));
                component.n_1700_B(shortDescComponent);
                component.n_1700_B(component.n_1700_B().n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, hoverComponent)).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, clickCommand)));
                return component;
            }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
        } else {
            String commandName = args.getString().toLowerCase();
            ICommand command2 = this.baritone.getCommandManager().getCommand(commandName);
            if (command2 == null) {
                throw new CommandNotFoundException(commandName);
            }
            this.logDirect(String.format("%s - %s", String.join((CharSequence)" / ", command2.getNames()), command2.getShortDesc()));
            this.logDirect("");
            command2.getLongDesc().forEach(this::logDirect);
            this.logDirect("");
            U_2871_b returnComponent = new U_2871_b("Click to return to the help menu");
            returnComponent.n_1700_B(returnComponent.n_1700_B().n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label)));
            this.logDirect(returnComponent);
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasExactlyOne()) {
            return new TabCompleteHelper().addCommands(this.baritone.getCommandManager()).filterPrefix(args.getString()).stream();
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "View all commands or help on specific ones";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Using this command, you can view detailed help information on how to use certain commands of Baritone.", "", "Usage:", "> help - Lists all commands and their short descriptions.", "> help <command> - Displays help information on a specific command.");
    }
}

