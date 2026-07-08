/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 */
package code.alphadlc.features.impl.movement.autoMystGlider.states;

import net.minecraft.class_2338;

public class StealState {
    private final class_2338 blockPos;
    private boolean empty = false;

    public StealState(class_2338 blockPos) {
        this.blockPos = blockPos;
    }

    public class_2338 getBlockPos() {
        return this.blockPos;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
