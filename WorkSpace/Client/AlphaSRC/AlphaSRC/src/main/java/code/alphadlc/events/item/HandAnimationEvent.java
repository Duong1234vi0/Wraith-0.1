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

import code.alphadlc.utils.client.managers.event.events.callables.EventCancellable;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_742;

public class HandAnimationEvent
extends EventCancellable {
    private class_4587 matrices;
    private class_1268 hand;
    private float swingProgress;
    private class_742 player;
    private class_1799 stack;
    private class_1306 arm;
    private float equipProgress;
    private float tickDelta;

    public HandAnimationEvent(class_4587 matrices, class_1268 hand, float swingProgress, class_742 player, class_1799 stack, class_1306 arm, float equipProgress, float tickDelta) {
        this.matrices = matrices;
        this.hand = hand;
        this.swingProgress = swingProgress;
        this.player = player;
        this.stack = stack;
        this.arm = arm;
        this.equipProgress = equipProgress;
        this.tickDelta = tickDelta;
    }

    public class_4587 getMatrices() {
        return this.matrices;
    }

    public class_1268 getHand() {
        return this.hand;
    }

    public float getSwingProgress() {
        return this.swingProgress;
    }

    public class_742 getPlayer() {
        return this.player;
    }

    public class_1799 getStack() {
        return this.stack;
    }

    public class_1306 getArm() {
        return this.arm;
    }

    public float getEquipProgress() {
        return this.equipProgress;
    }

    public float getTickDelta() {
        return this.tickDelta;
    }

    public void setMatrices(class_4587 matrices) {
        this.matrices = matrices;
    }

    public void setHand(class_1268 hand) {
        this.hand = hand;
    }

    public void setSwingProgress(float swingProgress) {
        this.swingProgress = swingProgress;
    }

    public void setPlayer(class_742 player) {
        this.player = player;
    }

    public void setStack(class_1799 stack) {
        this.stack = stack;
    }

    public void setArm(class_1306 arm) {
        this.arm = arm;
    }

    public void setEquipProgress(float equipProgress) {
        this.equipProgress = equipProgress;
    }

    public void setTickDelta(float tickDelta) {
        this.tickDelta = tickDelta;
    }
}
