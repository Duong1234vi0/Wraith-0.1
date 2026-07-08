/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 */
package code.alphadlc.utils.client.managers.api.command.datatypes;

import code.alphadlc.AlphaDLC;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeContext;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeFor;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.class_1299;

public enum EntityESPDataType implements IDatatypeFor<class_1299<?>>
{
    INSTANCE;


    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) throws CommandException {
        Stream<String> ways = this.getEntities().map(s -> s.method_5897().getString().replace(" ", "_"));
        String context = ctx.getConsumer().getString();
        return new TabCompleteHelper().append(ways).filterPrefix(context).sortAlphabetically().stream();
    }

    @Override
    public class_1299<?> get(IDatatypeContext datatypeContext) throws CommandException {
        return this.findEntity(datatypeContext.getConsumer().getString()).orElse(null);
    }

    public Optional<class_1299<?>> findEntity(String text) {
        return this.getEntities().filter(s -> s.method_5897().getString().replace(" ", "_").equalsIgnoreCase(text)).findFirst();
    }

    private Stream<class_1299<?>> getEntities() {
        return AlphaDLC.getInstance().getBoxESPRepository().entities.keySet().stream();
    }
}
