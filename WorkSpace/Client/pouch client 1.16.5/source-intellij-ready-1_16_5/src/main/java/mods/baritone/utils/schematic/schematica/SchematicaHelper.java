/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.schematic.schematica;

import java.util.Optional;
import lightning.product.Tuple;
import lightning.product.c_1514_x;
import mods.baritone.api.api.java.baritone.api.schematic.IStaticSchematic;
import mods.baritone.lunatrius.core.util.math.MBlockPos;
import mods.baritone.lunatrius.schematica.Schematica;
import mods.baritone.lunatrius.schematica.client.world.SchematicWorld;
import mods.baritone.lunatrius.schematica.proxy.ClientProxy;
import mods.baritone.utils.schematic.schematica.SchematicAdapter;

public final class SchematicaHelper
extends Enum<SchematicaHelper> {
    private static final /* synthetic */ SchematicaHelper[] $VALUES;

    public static SchematicaHelper[] values() {
        return (SchematicaHelper[])$VALUES.clone();
    }

    public static SchematicaHelper valueOf(String name) {
        return Enum.valueOf(SchematicaHelper.class, name);
    }

    public static boolean isSchematicaPresent() {
        try {
            Class.forName(Schematica.class.getName());
            return true;
        }
        catch (ClassNotFoundException | NoClassDefFoundError ex) {
            return false;
        }
    }

    public static Optional<Tuple<IStaticSchematic, c_1514_x>> getOpenSchematic() {
        return Optional.ofNullable(ClientProxy.schematic).map(world -> new Tuple<SchematicAdapter, MBlockPos>(new SchematicAdapter((SchematicWorld)world), world.position));
    }

    private static /* synthetic */ SchematicaHelper[] $values() {
        return new SchematicaHelper[0];
    }

    static {
        $VALUES = SchematicaHelper.$values();
    }
}


