/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime;

public enum SwordAttacks {
    RTL,
    LTR,
    FWD;

    private static final SwordAttacks[] vals;

    public SwordAttacks next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    static {
        vals = SwordAttacks.values();
    }
}
