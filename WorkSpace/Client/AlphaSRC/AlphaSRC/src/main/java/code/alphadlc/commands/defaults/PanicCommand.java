/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.AlphaDLC;
import code.alphadlc.features.impl.misc.Panic;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_124;

public class PanicCommand
extends Command {
    public PanicCommand() {
        super("panic");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        Panic panicModule = AlphaDLC.getInstance().getModuleRepository().modules().stream().filter(module -> module instanceof Panic).map(module -> (Panic)module).findFirst().orElse(null);
        if (panicModule != null) {
            panicModule.setState(true);
            this.logDirect(String.valueOf(class_124.field_1060) + "Panic mode activated! Client is now hidden.");
        } else {
            this.logDirect(String.valueOf(class_124.field_1061) + "Panic module not found!");
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "\u0410\u043a\u0442\u0438\u0432\u0438\u0440\u0443\u0435\u0442 \u0440\u0435\u0436\u0438\u043c \u043f\u0430\u043d\u0438\u043a\u0438 - \u043f\u043e\u043b\u043d\u043e\u0441\u0442\u044c\u044e \u0441\u043a\u0440\u044b\u0432\u0430\u0435\u0442 \u043a\u043b\u0438\u0435\u043d\u0442";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u041a\u043e\u043c\u0430\u043d\u0434\u0430 \u0430\u043a\u0442\u0438\u0432\u0438\u0440\u0443\u0435\u0442 \u0440\u0435\u0436\u0438\u043c \u043f\u0430\u043d\u0438\u043a\u0438, \u043a\u043e\u0442\u043e\u0440\u044b\u0439 \u043f\u043e\u043b\u043d\u043e\u0441\u0442\u044c\u044e \u0441\u043a\u0440\u044b\u0432\u0430\u0435\u0442 \u043a\u043b\u0438\u0435\u043d\u0442.", "", "\u041f\u0440\u0438 \u0430\u043a\u0442\u0438\u0432\u0430\u0446\u0438\u0438:", "- \u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0435\u0442\u0441\u044f \u043a\u043e\u0434 \u0432\u043e\u0441\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u044f (6 \u0446\u0438\u0444\u0440)", "- \u0418\u0434\u0435\u0442 \u043e\u0431\u0440\u0430\u0442\u043d\u044b\u0439 \u043e\u0442\u0441\u0447\u0435\u0442 5-4-3-2-1", "- \u0412\u0441\u0435 \u043c\u043e\u0434\u0443\u043b\u0438 \u043e\u0442\u043a\u043b\u044e\u0447\u0430\u044e\u0442\u0441\u044f", "- \u0427\u0430\u0442 \u043e\u0447\u0438\u0449\u0430\u0435\u0442\u0441\u044f", "- \u041a\u043b\u0438\u0435\u043d\u0442 \u043f\u043e\u043b\u043d\u043e\u0441\u0442\u044c\u044e \u0441\u043a\u0440\u044b\u0432\u0430\u0435\u0442\u0441\u044f", "", "\u0414\u043b\u044f \u0432\u043e\u0441\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u044f \u0432\u0432\u0435\u0434\u0438\u0442\u0435 \u043a\u043e\u0434 \u0432 \u0447\u0430\u0442.", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435:", "> panic - \u0410\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0440\u0435\u0436\u0438\u043c \u043f\u0430\u043d\u0438\u043a\u0438");
    }
}
