/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 */
package code.alphadlc.features.impl.movement.autoMystGlider.states;

import code.alphadlc.features.impl.movement.autoMystGlider.handers.StealHandler;
import code.alphadlc.features.impl.movement.autoMystGlider.states.GlidePhase;
import code.alphadlc.features.impl.movement.autoMystGlider.states.GlideState;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.math.time.StopWatch;
import net.minecraft.class_2338;
import net.minecraft.class_2374;

public class ConnectionState
implements QuickImports {
    private final GlideState glideState;
    private final StealHandler stealHandler;
    private boolean connected = true;
    private int anarchy = -1;
    private long leaveTime;
    private long remainingTime;
    private long phaseStartTime = System.currentTimeMillis();
    private GlidePhase phase = GlidePhase.FLYING;
    private StopWatch timer = new StopWatch();

    public ConnectionState(GlideState glideState, StealHandler stealHandler) {
        this.glideState = glideState;
        this.stealHandler = stealHandler;
    }

    public boolean isReconnected() {
        return this.phase == GlidePhase.LOOTING;
    }

    public void transitionTo(GlidePhase newPhase) {
        this.phase = newPhase;
        this.phaseStartTime = System.currentTimeMillis();
    }

    public void hub(GlidePhase nextPhase) {
        if (this.timer.finished(1000.0)) {
            this.timer.reset();
            this.connected = false;
            this.leaveTime = System.currentTimeMillis();
            mc.method_1562().method_45731("hub");
            this.transitionTo(nextPhase);
        }
    }

    public void joinAnarchy(int num, GlidePhase nextPhase) {
        if (this.timer.finished(1000.0)) {
            this.timer.reset();
            this.connected = true;
            this.anarchy = num;
            mc.method_1562().method_45731("an" + num);
            this.transitionTo(nextPhase);
        }
    }

    public void reload() {
        this.connected = true;
        this.leaveTime = -1L;
        this.remainingTime = -1L;
        this.phase = GlidePhase.FLYING;
        this.phaseStartTime = System.currentTimeMillis();
        this.timer.reset();
    }

    public boolean successfully() {
        return ConnectionState.mc.field_1687.method_8320(class_2338.method_49638((class_2374)ConnectionState.mc.field_1724.method_19538())).method_51367();
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void setAnarchy(int anarchy) {
        this.anarchy = anarchy;
    }

    public void setLeaveTime(long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public void setRemainingTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setPhaseStartTime(long phaseStartTime) {
        this.phaseStartTime = phaseStartTime;
    }

    public void setPhase(GlidePhase phase) {
        this.phase = phase;
    }

    public void setTimer(StopWatch timer) {
        this.timer = timer;
    }

    public GlideState getGlideState() {
        return this.glideState;
    }

    public StealHandler getStealHandler() {
        return this.stealHandler;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public int getAnarchy() {
        return this.anarchy;
    }

    public long getLeaveTime() {
        return this.leaveTime;
    }

    public long getRemainingTime() {
        return this.remainingTime;
    }

    public long getPhaseStartTime() {
        return this.phaseStartTime;
    }

    public GlidePhase getPhase() {
        return this.phase;
    }

    public StopWatch getTimer() {
        return this.timer;
    }
}
