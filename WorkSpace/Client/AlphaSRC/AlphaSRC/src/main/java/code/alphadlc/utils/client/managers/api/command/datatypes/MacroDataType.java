/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.command.datatypes;

import code.alphadlc.AlphaDLC;
import code.alphadlc.common.repository.macro.Macro;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeContext;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeFor;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.List;
import java.util.stream.Stream;

public enum MacroDataType implements IDatatypeFor<Macro>
{
    INSTANCE;


    @Override
    public Stream<String> tabComplete(IDatatypeContext datatypeContext) throws CommandException {
        Stream<String> macros = this.getMacro().stream().map(Macro::name);
        String context = datatypeContext.getConsumer().getString();
        return new TabCompleteHelper().append(macros).filterPrefix(context).sortAlphabetically().stream();
    }

    @Override
    public Macro get(IDatatypeContext datatypeContext) throws CommandException {
        String username = datatypeContext.getConsumer().getString();
        return this.getMacro().stream().filter(s -> s.name().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    private List<? extends Macro> getMacro() {
        return AlphaDLC.getInstance().getMacroRepository().macroList;
    }
}
