/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  org.lwjgl.glfw.GLFW
 */
package code.alphadlc.display.screens.clickgui.components.implement.settings;

import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.features.module.setting.implement.TextSetting;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import net.minecraft.class_332;
import org.lwjgl.glfw.GLFW;

public class TextComponent
extends AbstractSettingComponent {
    private final TextSetting setting;
    private boolean localTyping;
    public static boolean typing;
    private static final float INPUT_X = 6.0f;
    private static final float INPUT_Y = 14.0f;
    private static final float INPUT_H = 14.0f;
    private static final float INPUT_TEXT_X = 9.0f;

    public TextComponent(TextSetting setting) {
        super(setting);
        this.setting = setting;
        this.height = 32.0f;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        Object shown;
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(context.method_51448(), this.setting.getName(), this.x + 6.0f, this.y + 9.5f, ThemeManager.textColor.getColor());
        float inputW = Math.max(1.0f, this.width - 12.0f);
        rectangle.render(ShapeProperties.create(context.method_51448(), this.x + 6.0f, this.y + 14.0f, inputW, 14.0).round(3.0f).color(ColorAssist.applyOpacity(ThemeManager.offModuleColor.getColor(), 120)).build());
        String value = this.setting.getText() == null ? "" : this.setting.getText();
        Object object = shown = value.isEmpty() ? "Type text..." : value;
        if (this.localTyping && System.currentTimeMillis() % 1000L < 500L) {
            shown = (String)shown + "|";
        }
        int textColor = value.isEmpty() ? ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 120) : ThemeManager.textColor.getColor();
        Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).drawVerticallyCentered(context.method_51448(), (String)shown, this.x + 9.0f, this.y + 14.0f, 14.0f, textColor);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return false;
        }
        typing = this.localTyping = Calculate.isHovered(mouseX, mouseY, this.x + 6.0f, this.y + 14.0f, Math.max(1.0f, this.width - 12.0f), 14.0);
        return this.localTyping;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        String value;
        if (!this.localTyping) {
            return false;
        }
        String string = value = this.setting.getText() == null ? "" : this.setting.getText();
        if (keyCode == 256 || keyCode == 257 || keyCode == 335) {
            this.localTyping = false;
            typing = false;
            return true;
        }
        if (keyCode == 259 && !value.isEmpty()) {
            this.setting.setText(value.substring(0, value.length() - 1));
            return true;
        }
        if ((modifiers & 2) != 0 && keyCode == 86) {
            String clipboard = GLFW.glfwGetClipboardString((long)window.getHandle());
            if (clipboard != null) {
                int max = Math.max(0, this.setting.getMax());
                Object next = value + clipboard;
                if (max > 0 && ((String)next).length() > max) {
                    next = ((String)next).substring(0, max);
                }
                this.setting.setText((String)next);
            }
            return true;
        }
        return true;
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (!this.localTyping || Character.isISOControl(chr)) {
            return false;
        }
        String value = this.setting.getText() == null ? "" : this.setting.getText();
        int max = this.setting.getMax();
        if (max <= 0 || value.length() < max) {
            this.setting.setText(value + chr);
        }
        return true;
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        return Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
    }
}
