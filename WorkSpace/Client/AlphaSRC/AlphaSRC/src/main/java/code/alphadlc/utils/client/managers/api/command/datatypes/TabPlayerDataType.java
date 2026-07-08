/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_268
 */
package code.alphadlc.utils.client.managers.api.command.datatypes;

import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeContext;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeFor;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Collection;
import java.util.stream.Stream;
import net.minecraft.class_268;

public enum TabPlayerDataType implements IDatatypeFor<class_268>
{
    INSTANCE;


    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) throws CommandException {
        return new TabCompleteHelper().append(this.getTeam().stream().map(class_268::method_1204).map(Object::toString).map(s -> s.replaceAll("[\\[\\]]", ""))).filterPrefix(ctx.getConsumer().getString()).sortAlphabetically().stream();
    }

    @Override
    public class_268 get(IDatatypeContext datatypeContext) throws CommandException {
        String username = datatypeContext.getConsumer().getString();
        return this.getTeam().stream().filter(s -> s.method_1197().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    public Collection<class_268> getTeam() {
        return TabPlayerDataType.mc.field_1687.method_8428().method_1159();
    }
}
