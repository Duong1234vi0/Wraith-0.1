/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.display.screens.clickgui.components.implement.window;

import code.alphadlc.display.screens.clickgui.components.implement.window.AbstractWindow;
import code.alphadlc.display.screens.clickgui.components.implement.window.WindowManager;
import code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.BindPickerWindow;
import code.alphadlc.display.screens.clickgui.dropdown.DropDownScreen;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.setting.implement.BindSetting;

public final class BindWindowOpener {
    private BindWindowOpener() {
    }

    public static void openBindSetting(DropDownScreen screen, BindSetting setting, int mouseX, int mouseY) {
        WindowManager manager = screen.getLocalWindowManager();
        for (AbstractWindow window : manager.getWindows()) {
            BindPickerWindow picker;
            if (!(window instanceof BindPickerWindow) || (picker = (BindPickerWindow)window).getTarget() != setting) continue;
            window.startCloseAnimation();
            return;
        }
        float popupW = 210.0f;
        float popupH = 78.0f;
        float popupX = (float)mouseX - popupW / 2.0f;
        float popupY = (float)mouseY - popupH - 8.0f;
        manager.add(new BindPickerWindow(setting).position(popupX, popupY).size(popupW, popupH).draggable(true));
    }

    public static void openModuleBind(DropDownScreen screen, Module module, int mouseX, int mouseY) {
        WindowManager manager = screen.getLocalWindowManager();
        for (AbstractWindow window : manager.getWindows()) {
            BindPickerWindow picker;
            if (!(window instanceof BindPickerWindow) || (picker = (BindPickerWindow)window).getTarget() != module) continue;
            window.startCloseAnimation();
            return;
        }
        float popupW = 210.0f;
        float popupH = 78.0f;
        float popupX = (float)mouseX - popupW / 2.0f;
        float popupY = (float)mouseY - popupH - 8.0f;
        manager.add(new BindPickerWindow(module).position(popupX, popupY).size(popupW, popupH).draggable(true));
    }
}
