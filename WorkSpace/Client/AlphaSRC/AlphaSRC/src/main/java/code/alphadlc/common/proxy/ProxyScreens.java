/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_437
 */
package code.alphadlc.common.proxy;

import code.alphadlc.common.proxy.GuiProxy;
import net.minecraft.class_310;
import net.minecraft.class_437;

public final class ProxyScreens {
    private ProxyScreens() {
    }

    public static void open(class_437 parent) {
        try {
            class_310.method_1551().method_1507((class_437)new GuiProxy(parent));
        }
        catch (Throwable error) {
            System.err.println("Failed to open proxy screen");
            error.printStackTrace();
        }
    }
}
