/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.datatypes;

import java.util.regex.Pattern;
import java.util.stream.Stream;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeContext;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeFor;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.helpers.TabCompleteHelper;

public enum BlockById implements IDatatypeFor<T_2915_h>
{
    INSTANCE;

    private static Pattern PATTERN;

    @Override
    public T_2915_h get(IDatatypeContext ctx) throws CommandException {
        g_2336_b id = new g_2336_b(ctx.getConsumer().getString());
        T_2915_h block = V_3137_a.q_4610_l.J_1907_R(id).orElse(null);
        if (block == null) {
            throw new IllegalArgumentException("no block found by that id");
        }
        return block;
    }

    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) throws CommandException {
        String arg = ctx.getConsumer().getString();
        if (!PATTERN.matcher(arg).matches()) {
            return Stream.empty();
        }
        return new TabCompleteHelper().append(V_3137_a.q_4610_l.G_564_y().stream().map(Object::toString)).filterPrefixNamespaced(arg).sortAlphabetically().stream();
    }

    static {
        PATTERN = Pattern.compile("(?:[a-z0-9_.-]+:)?[a-z0-9/_.-]*");
    }
}

