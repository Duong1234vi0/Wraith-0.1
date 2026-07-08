/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 */
package code.alphadlc.display.screens.clickgui.components.implement.window;

import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.display.screens.clickgui.components.implement.window.AbstractWindow;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_332;

public class WindowManager
extends AbstractComponent {
    private final List<AbstractWindow> windows = new ArrayList<AbstractWindow>();

    public void add(AbstractWindow window) {
        this.windows.add(window);
    }

    public void delete(AbstractWindow window) {
        window.startCloseAnimation();
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        ArrayList toRemove = new ArrayList();
        this.windows.forEach(window -> {
            window.render(context, mouseX, mouseY, delta);
            if (window.isCloseAnimationFinished()) {
                toRemove.add(window);
            }
        });
        this.windows.removeAll(toRemove);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean clickedInsideWindow = false;
        ArrayList<AbstractWindow> windowsCopy = new ArrayList<AbstractWindow>(this.windows);
        for (int i = windowsCopy.size() - 1; i >= 0; --i) {
            AbstractWindow window = (AbstractWindow)windowsCopy.get(i);
            if (!window.isHovered(mouseX, mouseY) && !this.isHover(mouseX, mouseY)) continue;
            clickedInsideWindow = true;
            window.mouseClicked(mouseX, mouseY, button);
            break;
        }
        if (!clickedInsideWindow) {
            for (AbstractWindow window : this.windows) {
                window.startCloseAnimation();
            }
            return false;
        }
        return clickedInsideWindow;
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        this.windows.forEach(window -> window.isHovered(mouseX, mouseY));
        for (AbstractWindow window2 : this.windows) {
            if (!window2.isHover(mouseX, mouseY)) continue;
            return true;
        }
        return super.isHover(mouseX, mouseY);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        this.windows.forEach(window -> window.charTyped(chr, modifiers));
        return super.charTyped(chr, modifiers);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        for (AbstractWindow window : this.windows) {
            if (!window.mouseScrolled(mouseX, mouseY, amount)) continue;
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        this.windows.forEach(window -> window.keyPressed(keyCode, scanCode, modifiers));
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.windows.forEach(window -> window.mouseReleased(mouseX, mouseY, button));
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public List<AbstractWindow> getWindows() {
        return this.windows;
    }
}
