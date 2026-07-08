/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.command.datatypes;

import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeContext;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.stream.Stream;

public interface IDatatype
extends QuickImports {
    public Stream<String> tabComplete(IDatatypeContext var1) throws CommandException;
}
