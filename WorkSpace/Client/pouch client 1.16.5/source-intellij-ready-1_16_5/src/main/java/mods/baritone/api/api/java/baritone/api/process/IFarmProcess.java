/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.process;

import lightning.product.c_1514_x;
import mods.baritone.api.api.java.baritone.api.process.IBaritoneProcess;

public interface IFarmProcess
extends IBaritoneProcess {
    public void farm(int var1, c_1514_x var2);

    default public void farm() {
        this.farm(0, null);
    }

    default public void farm(int range) {
        this.farm(range, null);
    }
}

