/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.process;

import java.io.File;
import java.util.List;
import lightning.product.K_4074_S;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.z_3539_x;
import mods.baritone.api.api.java.baritone.api.process.IBaritoneProcess;
import mods.baritone.api.api.java.baritone.api.schematic.ISchematic;

public interface IBuilderProcess
extends IBaritoneProcess {
    public void build(String var1, ISchematic var2, z_3539_x var3);

    public boolean build(String var1, File var2, z_3539_x var3);

    @Deprecated
    default public boolean build(String schematicFile, c_1514_x origin) {
        File file = new File(new File(MinecraftClient.A_4115_X().M_182_A, "schematics"), schematicFile);
        return this.build(schematicFile, file, (z_3539_x)origin);
    }

    public void buildOpenSchematic();

    public void buildOpenLitematic(int var1);

    public void pause();

    public boolean isPaused();

    public void resume();

    public void clearArea(c_1514_x var1, c_1514_x var2);

    public List<K_4074_S> getApproxPlaceable();
}


