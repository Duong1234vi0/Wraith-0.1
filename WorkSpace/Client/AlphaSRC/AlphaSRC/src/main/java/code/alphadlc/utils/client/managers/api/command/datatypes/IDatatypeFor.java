/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.command.datatypes;

import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatype;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeContext;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;

public interface IDatatypeFor<T>
extends IDatatype {
    public T get(IDatatypeContext var1) throws CommandException;
}
