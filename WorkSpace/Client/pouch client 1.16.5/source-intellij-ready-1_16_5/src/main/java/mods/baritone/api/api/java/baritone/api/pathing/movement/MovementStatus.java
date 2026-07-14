/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.movement;

public enum MovementStatus {
    PREPPING(false),
    WAITING(false),
    RUNNING(false),
    SUCCESS(true),
    UNREACHABLE(true),
    FAILED(true),
    CANCELED(true);

    private final boolean complete;

    private MovementStatus(boolean complete) {
        this.complete = complete;
    }

    public final boolean isComplete() {
        return this.complete;
    }
}

