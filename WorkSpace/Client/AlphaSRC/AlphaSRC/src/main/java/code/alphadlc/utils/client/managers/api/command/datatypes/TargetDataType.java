/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.command.datatypes;

import code.alphadlc.common.repository.target.TargetRepository;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeContext;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeFor;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.stream.Stream;

public enum TargetDataType implements IDatatypeFor<String>
{
    INSTANCE;


    @Override
    public Stream<String> tabComplete(IDatatypeContext datatypeContext) throws CommandException {
        Stream<String> targets = TargetRepository.getInstance().getTargets().stream();
        String context = datatypeContext.getConsumer().peekString();
        return new TabCompleteHelper().append(targets).filterPrefix(context).sortAlphabetically().stream();
    }

    @Override
    public String get(IDatatypeContext datatypeContext) throws CommandException {
        String name = datatypeContext.getConsumer().getString();
        return TargetRepository.getInstance().getTargets().stream().filter(s -> s.equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
