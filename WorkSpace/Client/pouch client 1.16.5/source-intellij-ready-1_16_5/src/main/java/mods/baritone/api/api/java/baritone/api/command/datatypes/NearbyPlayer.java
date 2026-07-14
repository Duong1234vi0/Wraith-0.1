/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.datatypes;

import java.util.List;
import java.util.stream.Stream;
import lightning.product.a_3913_L;
import lightning.product.x_282_a;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeContext;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeFor;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.helpers.TabCompleteHelper;

public enum NearbyPlayer implements IDatatypeFor<a_3913_L>
{
    INSTANCE;


    @Override
    public a_3913_L get(IDatatypeContext ctx) throws CommandException {
        String username = ctx.getConsumer().getString();
        return NearbyPlayer.getPlayers(ctx).stream().filter(s -> s.O_1309_Q().getString().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) throws CommandException {
        return new TabCompleteHelper().append(NearbyPlayer.getPlayers(ctx).stream().map(a_3913_L::O_1309_Q).map(x_282_a::getString)).filterPrefix(ctx.getConsumer().getString()).sortAlphabetically().stream();
    }

    private static List<? extends a_3913_L> getPlayers(IDatatypeContext ctx) {
        return ctx.getBaritone().getPlayerContext().world().multiplayerClientSuggestionProvider();
    }
}


