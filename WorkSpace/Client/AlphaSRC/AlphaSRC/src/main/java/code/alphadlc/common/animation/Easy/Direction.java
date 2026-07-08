/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.common.animation.Easy;

public enum Direction {
    FORWARDS,
    BACKWARDS;


    public Direction opposite() {
        if (this == FORWARDS) {
            return BACKWARDS;
        }
        return FORWARDS;
    }
}
