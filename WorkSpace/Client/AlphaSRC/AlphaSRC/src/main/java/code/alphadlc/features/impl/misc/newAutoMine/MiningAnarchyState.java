/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.misc.newAutoMine;

import code.alphadlc.features.impl.player.autoMine.api.MineType;

public class MiningAnarchyState {
    private final int anarchy;
    private final MineType mineType;
    private final long startTime;
    private final int time;

    public MiningAnarchyState(int anarchy, MineType mineType, int time) {
        this.anarchy = anarchy;
        this.mineType = mineType;
        this.startTime = System.currentTimeMillis();
        this.time = time;
    }

    public MiningAnarchyState(String anarchy, MineType mineType, int time) {
        this(Integer.parseInt(anarchy.replaceAll("\\D", "")), mineType, time);
    }

    public long getRemainingTime() {
        return (long)this.time - (System.currentTimeMillis() - this.startTime) / 1000L;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof MiningAnarchyState)) return false;
        MiningAnarchyState state = (MiningAnarchyState)obj;
        if (state.anarchy != this.anarchy) return false;
        return true;
    }

    public int getAnarchy() {
        return this.anarchy;
    }

    public MineType getMineType() {
        return this.mineType;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public int getTime() {
        return this.time;
    }
}
