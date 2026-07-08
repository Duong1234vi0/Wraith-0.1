/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.window;

import code.alphadlc.features.impl.misc.Panic;

public class WindowTitleAnimation {
    private static WindowTitleAnimation INSTANCE;
    private static final String TITLE = "AlphaDLC Free 3.0 https://t.me/Alpha_DLC";
    private static final String PANIC_TITLE = "Minecraft 1.21.4";

    private WindowTitleAnimation() {
    }

    public static WindowTitleAnimation getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WindowTitleAnimation();
        }
        return INSTANCE;
    }

    public void updateTitle() {
    }

    public String getCurrentTitle() {
        if (Panic.isPanicMode()) {
            return PANIC_TITLE;
        }
        return TITLE;
    }
}
