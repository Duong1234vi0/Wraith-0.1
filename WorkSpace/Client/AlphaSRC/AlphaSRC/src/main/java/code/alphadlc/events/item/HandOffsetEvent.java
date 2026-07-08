/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1306
 *  net.minecraft.class_1799
 *  net.minecraft.class_4587
 *  net.minecraft.class_742
 */
package code.alphadlc.events.item;

import code.alphadlc.utils.client.managers.event.events.Event;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_742;

public class HandOffsetEvent
implements Event {
    private class_4587 matrices;
    private class_1799 stack;
    private class_1268 hand;
    private class_742 player;
    private float swingProgress;
    private float equipProgress;
    private float tickDelta;
    private class_1306 arm;

    public HandOffsetEvent(class_4587 matrices, class_1799 stack, class_1268 hand, class_742 player, float swingProgress, float equipProgress, float tickDelta, class_1306 arm) {
        this.matrices = matrices;
        this.stack = stack;
        this.hand = hand;
        this.player = player;
        this.swingProgress = swingProgress;
        this.equipProgress = equipProgress;
        this.tickDelta = tickDelta;
        this.arm = arm;
    }

    public class_4587 getMatrices() {
        return this.matrices;
    }

    public class_1799 getStack() {
        return this.stack;
    }

    public class_1268 getHand() {
        return this.hand;
    }

    public class_742 getPlayer() {
        return this.player;
    }

    public float getSwingProgress() {
        return this.swingProgress;
    }

    public float getEquipProgress() {
        return this.equipProgress;
    }

    public float getTickDelta() {
        return this.tickDelta;
    }

    public class_1306 getArm() {
        return this.arm;
    }

    public void setMatrices(class_4587 matrices) {
        this.matrices = matrices;
    }

    public void setStack(class_1799 stack) {
        this.stack = stack;
    }

    public void setHand(class_1268 hand) {
        this.hand = hand;
    }

    public void setPlayer(class_742 player) {
        this.player = player;
    }

    public void setSwingProgress(float swingProgress) {
        this.swingProgress = swingProgress;
    }

    public void setEquipProgress(float equipProgress) {
        this.equipProgress = equipProgress;
    }

    public void setTickDelta(float tickDelta) {
        this.tickDelta = tickDelta;
    }

    public void setArm(class_1306 arm) {
        this.arm = arm;
    }
}
