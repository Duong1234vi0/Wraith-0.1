/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.schematic.litematica;

import java.io.File;
import lightning.product.K_4074_S;
import lightning.product.W_2163_m;
import lightning.product.q_4099_E;
import lightning.product.z_3539_x;
import mods.baritone.litematica.Litematica;
import mods.baritone.litematica.data.DataManager;
import mods.baritone.utils.schematic.format.defaults.LitematicaSchematic;

public final class LitematicaHelper {
    public static boolean isLitematicaPresent() {
        try {
            Class.forName(Litematica.class.getName());
            return true;
        }
        catch (ClassNotFoundException | NoClassDefFoundError ex) {
            return false;
        }
    }

    public static boolean hasLoadedSchematic() {
        return DataManager.getSchematicPlacementManager().getAllSchematicsPlacements().size() > 0;
    }

    public static String getName(int i) {
        return DataManager.getSchematicPlacementManager().getAllSchematicsPlacements().get(i).getName();
    }

    public static z_3539_x getOrigin(int i) {
        return DataManager.getSchematicPlacementManager().getAllSchematicsPlacements().get(i).getOrigin();
    }

    public static File getSchematicFile(int i) {
        return DataManager.getSchematicPlacementManager().getAllSchematicsPlacements().get(i).getSchematicFile();
    }

    public static W_2163_m getRotation(int i) {
        return DataManager.getSchematicPlacementManager().getAllSchematicsPlacements().get(i).getRotation();
    }

    public static q_4099_E getMirror(int i) {
        return DataManager.getSchematicPlacementManager().getAllSchematicsPlacements().get(i).getMirror();
    }

    public static z_3539_x getCorrectedOrigin(LitematicaSchematic schematic, int i) {
        int x = LitematicaHelper.getOrigin(i).getX();
        int y = LitematicaHelper.getOrigin(i).getY();
        int z = LitematicaHelper.getOrigin(i).getZ();
        int mx = schematic.getOffsetMinCorner().getX();
        int my = schematic.getOffsetMinCorner().getY();
        int mz = schematic.getOffsetMinCorner().getZ();
        int sx = (schematic.getX() - 1) * -1;
        int sz = (schematic.getZ() - 1) * -1;
        q_4099_E mirror = LitematicaHelper.getMirror(i);
        W_2163_m rotation = LitematicaHelper.getRotation(i);
        return switch (mirror) {
            case q_4099_E.R_4764_Y, q_4099_E.J_1907_R -> {
                switch ((mirror.ordinal() * 2 + rotation.ordinal()) % 4) {
                    case 1: {
                        yield new z_3539_x(x + (sz - mz), y + my, z + (sx - mx));
                    }
                    case 2: {
                        yield new z_3539_x(x + mx, y + my, z + (sz - mz));
                    }
                    case 3: {
                        yield new z_3539_x(x + mz, y + my, z + mx);
                    }
                }
                yield new z_3539_x(x + (sx - mx), y + my, z + mz);
            }
            default -> {
                switch (rotation) {
                    case J_1907_R: {
                        yield new z_3539_x(x + (sz - mz), y + my, z + mx);
                    }
                    case R_4764_Y: {
                        yield new z_3539_x(x + (sx - mx), y + my, z + (sz - mz));
                    }
                    case G_564_y: {
                        yield new z_3539_x(x + mz, y + my, z + (sx - mx));
                    }
                }
                yield new z_3539_x(x + mx, y + my, z + mz);
            }
        };
    }

    public static z_3539_x doMirroring(z_3539_x in, int sizeX, int sizeZ, q_4099_E mirror) {
        int xOut = in.getX();
        int zOut = in.getZ();
        if (mirror == q_4099_E.J_1907_R) {
            zOut = sizeZ - in.getZ();
        } else if (mirror == q_4099_E.R_4764_Y) {
            xOut = sizeX - in.getX();
        }
        return new z_3539_x(xOut, in.getY(), zOut);
    }

    public static z_3539_x rotate(z_3539_x in, int sizeX, int sizeZ) {
        return new z_3539_x(sizeX - (sizeX - sizeZ) - in.getZ(), in.getY(), in.getX());
    }

    public static LitematicaSchematic blackMagicFuckery(LitematicaSchematic schemIn, int i) {
        LitematicaSchematic tempSchem = schemIn.getCopy(LitematicaHelper.getRotation(i).ordinal() % 2 == 1);
        for (int yCounter = 0; yCounter < schemIn.getY(); ++yCounter) {
            for (int zCounter = 0; zCounter < schemIn.getZ(); ++zCounter) {
                for (int xCounter = 0; xCounter < schemIn.getX(); ++xCounter) {
                    z_3539_x xyzHolder = new z_3539_x(xCounter, yCounter, zCounter);
                    xyzHolder = LitematicaHelper.doMirroring(xyzHolder, schemIn.getX() - 1, schemIn.getZ() - 1, LitematicaHelper.getMirror(i));
                    for (int turns = 0; turns < LitematicaHelper.getRotation(i).ordinal(); ++turns) {
                        xyzHolder = turns % 2 == 0 ? LitematicaHelper.rotate(xyzHolder, schemIn.getX() - 1, schemIn.getZ() - 1) : LitematicaHelper.rotate(xyzHolder, schemIn.getZ() - 1, schemIn.getX() - 1);
                    }
                    K_4074_S state = schemIn.getDirect(xCounter, yCounter, zCounter);
                    try {
                        state = state.n_1700_B(LitematicaHelper.getMirror(i)).n_1700_B(LitematicaHelper.getRotation(i));
                    }
                    catch (NullPointerException nullPointerException) {
                        // empty catch block
                    }
                    tempSchem.setDirect(xyzHolder.getX(), xyzHolder.getY(), xyzHolder.getZ(), state);
                }
            }
        }
        return tempSchem;
    }
}

