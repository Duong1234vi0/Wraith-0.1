/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_7923
 */
package code.alphadlc.utils.client.managers.api.command.datatypes;

import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeContext;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeFor;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.class_1299;
import net.minecraft.class_7923;

public enum EntityDataType implements IDatatypeFor<class_1299<?>>
{
    INSTANCE;


    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) throws CommandException {
        Stream<String> ways = class_7923.field_41177.method_10220().map(s -> s.method_5897().getString().replace(" ", "_"));
        String context = ctx.getConsumer().getString();
        return new TabCompleteHelper().append(ways).filterPrefix(context).sortAlphabetically().stream();
    }

    @Override
    public class_1299<?> get(IDatatypeContext datatypeContext) throws CommandException {
        return this.findEntity(datatypeContext.getConsumer().getString()).orElse(null);
    }

    public Optional<class_1299<?>> findEntity(String text) {
        return class_7923.field_41177.method_10220().filter(s -> s.method_5897().getString().replace(" ", "_").equalsIgnoreCase(text)).findFirst();
    }
}
