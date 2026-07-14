package ua.babka.command.exception;

import java.util.List;
import net.minecraft.util.Formatting;
import ua.babka.command.argument.ICommandArgument;
import ua.babka.command.impl.ICommand;
import ua.babka.util.QuickLogger;

public interface ICommandException
extends QuickLogger {
    public String getMessage();

    default public void handle(ICommand command, List<ICommandArgument> args) {
        this.logDirect(this.getMessage(), Formatting.RED);
    }
}

