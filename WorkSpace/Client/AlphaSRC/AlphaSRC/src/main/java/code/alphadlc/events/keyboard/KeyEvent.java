/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3675$class_307
 *  net.minecraft.class_437
 */
package code.alphadlc.events.keyboard;

import code.alphadlc.utils.client.bind.BindCodes;
import code.alphadlc.utils.client.managers.event.events.Event;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_3675;
import net.minecraft.class_437;

public record KeyEvent(class_437 screen, class_3675.class_307 type, int key, int action) implements Event,
QuickImports
{
    public boolean isKeyDown(int key) {
        return this.matchesBind(key, KeyEvent.mc.field_1755 == null);
    }

    public boolean isKeyDown(int key, boolean screen) {
        return this.matchesBind(key, screen);
    }

    public boolean matchesBind(int bindKey, boolean allowScreen) {
        if (bindKey == 998 || bindKey == 999) {
            return false;
        }
        if (this.action != 1 || !allowScreen) {
            return false;
        }
        if (BindCodes.isMouse(bindKey)) {
            return this.type == class_3675.class_307.field_1672 && this.key == bindKey;
        }
        return this.type == class_3675.class_307.field_1668 && this.key == bindKey;
    }

    public boolean isKeyReleased(int key) {
        return this.isKeyReleased(key, KeyEvent.mc.field_1755 == null);
    }

    public boolean isKeyReleased(int key, boolean screen) {
        return this.key == key && this.action == 0 && screen;
    }
}
