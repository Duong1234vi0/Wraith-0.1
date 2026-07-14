/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lightning.product.N_4263_v;
import lightning.product.V_3137_a;
import lightning.product.a_3913_L;
import lightning.product.g_2336_b;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import mods.baritone.KeepName;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.datatypes.EntityClassById;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeFor;
import mods.baritone.api.api.java.baritone.api.command.datatypes.NearbyPlayer;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandErrorMessageException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.helpers.TabCompleteHelper;

public class FollowCommand
extends Command {
    public FollowCommand(IBaritone baritone) {
        super(baritone, "follow");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        FollowGroup group;
        args.requireMin(1);
        ArrayList<N_4263_v> entities = new ArrayList<N_4263_v>();
        ArrayList<t_5_h> classes = new ArrayList<t_5_h>();
        if (args.hasExactlyOne()) {
            group = args.getEnum(FollowGroup.class);
            this.baritone.getFollowProcess().follow(group.filter);
        } else {
            args.requireMin(2);
            group = null;
            FollowList list = args.getEnum(FollowList.class);
            while (args.hasAny()) {
                Object gotten = args.getDatatypeFor(list.datatype);
                if (gotten instanceof t_5_h) {
                    classes.add((t_5_h)gotten);
                    continue;
                }
                if (gotten == null) continue;
                entities.add((N_4263_v)gotten);
            }
            this.baritone.getFollowProcess().follow(classes.isEmpty() ? entities::contains : e -> classes.stream().anyMatch(c -> e.f_4016_n().equals(c)));
        }
        if (group != null) {
            this.logDirect(String.format("Following all %s", group.name().toLowerCase(Locale.US)));
        } else if (classes.isEmpty()) {
            if (entities.isEmpty()) {
                throw new NoEntitiesException();
            }
            this.logDirect("Following these entities:");
            entities.stream().map(N_4263_v::toString).forEach(this::logDirect);
        } else {
            this.logDirect("Following these types of entities:");
            classes.stream().map(V_3137_a.g_221_o::J_1907_R).map(Objects::requireNonNull).map(g_2336_b::toString).forEach(this::logDirect);
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        IDatatypeFor followType;
        if (args.hasExactlyOne()) {
            return new TabCompleteHelper().append(FollowGroup.class).append(FollowList.class).filterPrefix(args.getString()).stream();
        }
        try {
            followType = args.getEnum(FollowList.class).datatype;
        }
        catch (NullPointerException e) {
            return Stream.empty();
        }
        while (args.has(2)) {
            if (args.peekDatatypeOrNull(followType) == null) {
                return Stream.empty();
            }
            args.get();
        }
        return args.tabCompleteDatatype(followType);
    }

    @Override
    public String getShortDesc() {
        return "Follow entity things";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The follow command tells Baritone to follow certain kinds of entities.", "", "Usage:", "> follow entities - Follows all entities.", "> follow entity <entity1> <entity2> <...> - Follow certain entities (for example 'skeleton', 'horse' etc.)", "> follow players - Follow players", "> follow player <username1> <username2> <...> - Follow certain players");
    }

    @KeepName
    private static enum FollowGroup {
        ENTITIES(r_4811_B.class::isInstance),
        PLAYERS(a_3913_L.class::isInstance);

        final Predicate<N_4263_v> filter;

        private FollowGroup(Predicate<N_4263_v> filter) {
            this.filter = filter;
        }
    }

    @KeepName
    private static enum FollowList {
        ENTITY(EntityClassById.INSTANCE),
        PLAYER(NearbyPlayer.INSTANCE);

        final IDatatypeFor datatype;

        private FollowList(IDatatypeFor datatype) {
            this.datatype = datatype;
        }
    }

    public static class NoEntitiesException
    extends CommandErrorMessageException {
        protected NoEntitiesException() {
            super("No valid entities in range!");
        }
    }
}

