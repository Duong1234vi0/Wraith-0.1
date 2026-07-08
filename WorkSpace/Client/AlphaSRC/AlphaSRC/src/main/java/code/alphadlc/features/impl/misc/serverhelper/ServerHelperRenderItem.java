/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 */
package code.alphadlc.features.impl.misc.serverhelper;

import net.minecraft.class_1792;

public class ServerHelperRenderItem {
    private final String fullName;
    private final String shortName;
    private final String status;
    private final class_1792 icon;
    private int bindKey = -1;
    private int tint = -1;

    public ServerHelperRenderItem(String fullName, String shortName, String status, class_1792 icon) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.status = status;
        this.icon = icon;
    }

    public ServerHelperRenderItem withBind(int bindKey) {
        this.bindKey = bindKey;
        return this;
    }

    public ServerHelperRenderItem withTint(int tint) {
        this.tint = tint;
        return this;
    }

    public String fullName() {
        return this.fullName;
    }

    public String shortName() {
        return this.shortName;
    }

    public String status() {
        return this.status;
    }

    public class_1792 icon() {
        return this.icon;
    }

    public int bindKey() {
        return this.bindKey;
    }

    public int tint() {
        return this.tint;
    }

    public boolean hasTint() {
        return this.tint != -1;
    }
}
