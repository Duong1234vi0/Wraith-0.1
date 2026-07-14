/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.datatypes;

import java.util.stream.Stream;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.t_5_h;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeContext;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeFor;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.helpers.TabCompleteHelper;

public enum EntityClassById implements IDatatypeFor<t_5_h>
{
    INSTANCE;


    @Override
    public t_5_h get(IDatatypeContext ctx) throws CommandException {
        g_2336_b id = new g_2336_b(ctx.getConsumer().getString());
        t_5_h entity = V_3137_a.g_221_o.J_1907_R(id).orElse(null);
        if (entity == null) {
            throw new IllegalArgumentException("no entity found by that id");
        }
        return entity;
    }

    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) throws CommandException {
        return new TabCompleteHelper().append(V_3137_a.g_221_o.u_1723_Y().map(Object::toString)).filterPrefixNamespaced(ctx.getConsumer().getString()).sortAlphabetically().stream();
    }
}

