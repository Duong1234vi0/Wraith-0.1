/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.datatypes;

import java.util.Locale;
import java.util.stream.Stream;
import lightning.product.b_257_Y;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeContext;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeFor;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.helpers.TabCompleteHelper;

public enum ForAxis implements IDatatypeFor<b_257_Y.n_1700_B>
{
    INSTANCE;


    @Override
    public b_257_Y.n_1700_B get(IDatatypeContext ctx) throws CommandException {
        return b_257_Y.n_1700_B.valueOf(ctx.getConsumer().getString().toUpperCase(Locale.US));
    }

    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) throws CommandException {
        return new TabCompleteHelper().append(Stream.of(b_257_Y.n_1700_B.values()).map(b_257_Y.n_1700_B::n_1700_B).map(String::toLowerCase)).filterPrefix(ctx.getConsumer().getString()).stream();
    }
}

