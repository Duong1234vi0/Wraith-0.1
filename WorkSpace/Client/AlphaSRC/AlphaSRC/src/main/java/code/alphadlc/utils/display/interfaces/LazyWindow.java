/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1041
 *  net.minecraft.class_310
 */
package code.alphadlc.utils.display.interfaces;

import net.minecraft.class_1041;
import net.minecraft.class_310;

public final class LazyWindow {
    private class_1041 delegate() {
        class_310 client = class_310.method_1551();
        return client != null ? client.method_22683() : null;
    }

    public int getScaledWidth() {
        class_1041 window = this.delegate();
        return window != null ? window.method_4486() : 0;
    }

    public int getScaledHeight() {
        class_1041 window = this.delegate();
        return window != null ? window.method_4502() : 0;
    }

    public int getWidth() {
        class_1041 window = this.delegate();
        return window != null ? window.method_4480() : 0;
    }

    public int getHeight() {
        class_1041 window = this.delegate();
        return window != null ? window.method_4507() : 0;
    }

    public long getHandle() {
        class_1041 window = this.delegate();
        return window != null ? window.method_4490() : 0L;
    }

    public double getScaleFactor() {
        class_1041 window = this.delegate();
        return window != null ? window.method_4495() : 1.0;
    }

    public boolean isFullscreen() {
        class_1041 window = this.delegate();
        return window != null && window.method_4498();
    }

    public void toggleFullscreen() {
        class_1041 window = this.delegate();
        if (window != null) {
            window.method_4500();
        }
    }

    public void setVsync(boolean enabled) {
        class_1041 window = this.delegate();
        if (window != null) {
            window.method_4497(enabled);
        }
    }
}
