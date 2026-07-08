/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  org.lwjgl.glfw.GLFW
 */
package code.alphadlc.display.screens.clickgui.components.implement.customization;

import code.alphadlc.AlphaDLC;
import code.alphadlc.display.screens.clickgui.MenuScreen;
import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.display.screens.clickgui.components.implement.customization.ThemeDisplayComponent;
import code.alphadlc.utils.animation.AnimationHelper;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.scissor.ScissorAssist;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import org.lwjgl.glfw.GLFW;

public class ThemeInputComponent
extends AbstractComponent
implements MenuScreen.ClickGuiInput,
MenuScreen.ClearableClickGuiInput {
    private boolean typing = false;
    private boolean dragging;
    private int cursorPosition = 0;
    private int selectionStart = -1;
    private int selectionEnd = -1;
    private long lastClickTime = 0L;
    private float xOffset = 0.0f;
    private String text = "";
    private Runnable onTextChanged;
    private long squareClickTime = 0L;
    private final ThemeDisplayComponent themeDisplay = new ThemeDisplayComponent();

    public void setText(String text) {
        this.text = text;
        this.cursorPosition = 0;
        this.clearSelection();
    }

    public void setOnTextChanged(Runnable callback) {
        this.onTextChanged = callback;
    }

    @Override
    public void setTyping(boolean typing) {
        this.typing = typing;
    }

    @Override
    public boolean isTyping() {
        return this.typing;
    }

    private void notifyTextChanged() {
        if (this.onTextChanged != null) {
            this.onTextChanged.run();
        }
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        boolean focused;
        int end;
        int start;
        class_4587 matrix = context.method_51448();
        FontRenderer font = Fonts.getSize(12);
        this.updateXOffset(font, this.cursorPosition);
        this.width = 100.0f;
        this.height = 21.0f;
        rectangle.render(ShapeProperties.create(matrix, this.x, this.y, this.width - 16.0f, this.height).round(6.0f).softness(1.0f).thickness(2.0f).outlineColor(new Color(47, 47, 50, 255).getRGB()).color(ThemeManager.offModuleColor.getColor()).build());
        float squareSize = 20.0f;
        float squareX = this.x + this.width - squareSize - 5.0f;
        float squareY = this.y + (this.height - squareSize) / 2.0f;
        long currentTime = System.currentTimeMillis();
        boolean isClicked = currentTime - this.squareClickTime < 200L;
        int[] colors = isClicked ? new int[]{-1, -1, -1, 5} : new int[]{ColorAssist.getClientColor(), ColorAssist.getClientColor2(), ColorAssist.getClientColor(), ColorAssist.getClientColor2()};
        String buttonAnimationKey = "theme_save_button_" + System.identityHashCode(10);
        float buttonScale = AnimationHelper.getAnimationValue(buttonAnimationKey, 1.0f);
        rectangle.render(ShapeProperties.create(matrix, squareX + 12.0f, squareY, squareSize * buttonScale, squareSize * buttonScale).round(4.0f).color(colors[0], colors[1], colors[2], colors[3]).build());
        float iconX = squareX + (squareSize - Fonts.getSize(18, Fonts.Type.ALPHADLC_ICONS).getStringWidth("y")) / 2.0f;
        float iconY = squareY + (squareSize - Fonts.getSize(18, Fonts.Type.ALPHADLC_ICONS).getStringHeight("y")) / 2.0f;
        Fonts.getSize(18, Fonts.Type.ALPHADLC_ICONS).drawString(context.method_51448(), "y", (double)iconX + 12.3, (double)iconY + 7.6, ThemeManager.textColor.getColor());
        String displayText = this.text.equalsIgnoreCase("") && !this.typing ? "New Theme..." : this.text;
        ScissorAssist scissor = AlphaDLC.getInstance().getScissorManager();
        scissor.push(matrix.method_23760().method_23761(), this.x - 1.0f, this.y, this.width - 25.0f, this.height);
        if (this.typing && this.selectionStart != -1 && this.selectionEnd != -1 && this.selectionStart != this.selectionEnd && (start = Math.max(0, Math.min(this.getStartOfSelection(), this.text.length()))) < (end = Math.max(0, Math.min(this.getEndOfSelection(), this.text.length())))) {
            float selectionXStart = this.x + 4.0f - this.xOffset + font.getStringWidth(this.text.substring(0, start));
            float selectionXEnd = this.x + 4.0f - this.xOffset + font.getStringWidth(this.text.substring(0, end));
            float selectionWidth = selectionXEnd - selectionXStart;
            rectangle.render(ShapeProperties.create(matrix, selectionXStart, this.y + this.height / 2.0f - 4.0f, selectionWidth, 8.0).color(-11172376).build());
        }
        float textX = this.x + 5.0f;
        Fonts.getSize(15, Fonts.Type.SuisseIntlSemiBold).drawString(context.method_51448(), displayText, textX, this.y + this.height / 2.0f - 1.0f, this.typing ? ThemeManager.textColor.getColor() : new Color(137, 137, 140, 255).getRGB());
        scissor.pop();
        long cursorTime = System.currentTimeMillis();
        boolean bl = focused = this.typing && cursorTime % 1000L < 500L;
        if (focused && (this.selectionStart == -1 || this.selectionStart == this.selectionEnd)) {
            FontRenderer textFont = Fonts.getSize(15, Fonts.Type.SuisseIntlSemiBold);
            int safeCursorPosition = Math.min(this.cursorPosition, this.text.length());
            float cursorX = textFont.getStringWidth(this.text.substring(0, safeCursorPosition));
            float leftCursorX = this.x + 5.0f + cursorX;
            rectangle.render(ShapeProperties.create(matrix, leftCursorX, this.y + this.height / 2.0f - 3.5f, 0.5, 7.0).color(ThemeManager.textColor.getColor()).build());
        }
        if (this.dragging && GLFW.glfwGetMouseButton((long)window.getHandle(), (int)0) == 1) {
            this.cursorPosition = this.getCursorIndexAt(mouseX);
            if (this.selectionStart == -1) {
                this.selectionStart = this.cursorPosition + 1;
            }
            this.selectionEnd = this.cursorPosition;
        } else if (this.dragging) {
            this.dragging = false;
        }
        this.themeDisplay.x = this.x;
        this.themeDisplay.y = this.y - 11.0f;
        this.themeDisplay.width = this.width;
        this.themeDisplay.height = 20.0f;
        this.themeDisplay.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.themeDisplay.x = this.x;
        this.themeDisplay.y = this.y - 11.0f;
        this.themeDisplay.width = this.width;
        this.themeDisplay.height = 20.0f;
        if (this.themeDisplay.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        float squareSize = 20.0f;
        float squareX = this.x + this.width - squareSize - 12.0f;
        float squareY = this.y + (this.height - squareSize) / 2.0f;
        if (Calculate.isHovered(mouseX, mouseY, squareX + 12.0f, squareY, squareSize, squareSize) && button == 0) {
            String buttonAnimationKey = "theme_save_button_" + System.identityHashCode(this);
            AnimationHelper.startAnimation(buttonAnimationKey, 1.0f, 1.2f, 100L, AnimationHelper.EasingType.EASE_OUT);
            this.squareClickTime = System.currentTimeMillis();
            this.saveTheme();
            return true;
        }
        if (Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width - 25.0f, this.height) && button == 0) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.lastClickTime < 250L) {
                this.selectionStart = 0;
                this.selectionEnd = this.text.length();
            } else {
                MenuScreen.activateClickGuiInput(this);
                this.dragging = true;
                this.lastClickTime = currentTime;
                this.selectionStart = this.cursorPosition = this.getCursorIndexAt(mouseX);
                this.selectionEnd = this.cursorPosition;
            }
            return true;
        }
        this.typing = false;
        this.clearSelection();
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.dragging = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (this.typing) {
            float maxWidth;
            String testText = this.text.substring(0, this.cursorPosition) + chr + this.text.substring(this.cursorPosition);
            float testWidth = Fonts.getSize(15, Fonts.Type.SuisseIntlSemiBold).getStringWidth(testText);
            if (testWidth <= (maxWidth = this.width - 30.0f)) {
                this.deleteSelectedText();
                this.text = this.text.substring(0, this.cursorPosition) + chr + this.text.substring(this.cursorPosition);
                ++this.cursorPosition;
                this.clearSelection();
                this.notifyTextChanged();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.typing) {
            if (class_437.method_25441()) {
                switch (keyCode) {
                    case 65: {
                        this.selectAllText();
                        break;
                    }
                    case 86: {
                        this.pasteFromClipboard();
                        break;
                    }
                    case 67: {
                        this.copyToClipboard();
                    }
                }
            } else {
                switch (keyCode) {
                    case 257: 
                    case 259: {
                        this.handleTextModification(keyCode);
                        break;
                    }
                    case 262: 
                    case 263: {
                        this.moveCursor(keyCode);
                    }
                }
            }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void pasteFromClipboard() {
        String clipboardText = GLFW.glfwGetClipboardString((long)window.getHandle());
        if (clipboardText != null) {
            this.replaceText(this.cursorPosition, this.cursorPosition, clipboardText);
        }
    }

    private void copyToClipboard() {
        if (this.hasSelection()) {
            GLFW.glfwSetClipboardString((long)window.getHandle(), (CharSequence)this.getSelectedText());
        }
    }

    private void selectAllText() {
        this.selectionStart = 0;
        this.selectionEnd = this.text.length();
    }

    private void handleTextModification(int keyCode) {
        if (keyCode == 259) {
            if (this.hasSelection()) {
                this.replaceText(this.getStartOfSelection(), this.getEndOfSelection(), "");
            } else if (this.cursorPosition > 0) {
                this.replaceText(this.cursorPosition - 1, this.cursorPosition, "");
            }
        } else if (keyCode == 257) {
            this.typing = false;
        }
    }

    private void moveCursor(int keyCode) {
        if (keyCode == 263 && this.cursorPosition > 0) {
            --this.cursorPosition;
        } else if (keyCode == 262 && this.cursorPosition < this.text.length()) {
            ++this.cursorPosition;
        }
        this.updateSelectionAfterCursorMove();
    }

    private void updateSelectionAfterCursorMove() {
        if (class_437.method_25442()) {
            if (this.selectionStart == -1) {
                this.selectionStart = this.cursorPosition;
            }
            this.selectionEnd = this.cursorPosition;
        } else {
            this.clearSelection();
        }
    }

    private void replaceText(int start, int end, String replacement) {
        if (start < 0) {
            start = 0;
        }
        if (end > this.text.length()) {
            end = this.text.length();
        }
        if (start > end) {
            start = end;
        }
        this.text = this.text.substring(0, start) + replacement + this.text.substring(end);
        this.cursorPosition = start + replacement.length();
        this.clearSelection();
        this.notifyTextChanged();
    }

    private boolean hasSelection() {
        return this.selectionStart != -1 && this.selectionEnd != -1 && this.selectionStart != this.selectionEnd;
    }

    private String getSelectedText() {
        return this.text.substring(this.getStartOfSelection(), this.getEndOfSelection());
    }

    private int getStartOfSelection() {
        return Math.min(this.selectionStart, this.selectionEnd);
    }

    private int getEndOfSelection() {
        return Math.max(this.selectionStart, this.selectionEnd);
    }

    private void clearSelection() {
        this.selectionStart = -1;
        this.selectionEnd = -1;
    }

    private int getCursorIndexAt(double mouseX) {
        float charWidth;
        int position;
        FontRenderer font = Fonts.getSize(15, Fonts.Type.SuisseIntlSemiBold);
        float textStartX = this.x + 5.0f;
        float relativeX = (float)mouseX - textStartX;
        for (position = 0; position < this.text.length() && !((charWidth = font.getStringWidth(this.text.substring(0, position + 1))) > relativeX); ++position) {
        }
        return position;
    }

    private void updateXOffset(FontRenderer font, int cursorPosition) {
        this.xOffset = 0.0f;
    }

    private void deleteSelectedText() {
        if (this.hasSelection()) {
            this.replaceText(this.getStartOfSelection(), this.getEndOfSelection(), "");
        }
    }

    private void saveTheme() {
        if (this.text.isEmpty()) {
            System.out.println("ThemeInputComponent: Cannot save theme - name is empty");
            return;
        }
        try {
            ThemeManager.saveTheme(this.text);
            this.text = "";
            this.cursorPosition = 0;
            this.clearSelection();
        }
        catch (Exception e) {
            System.out.println("ThemeInputComponent: Error saving theme: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void clearText() {
        this.text = "";
        this.cursorPosition = 0;
        this.clearSelection();
    }

    public String getText() {
        return this.text;
    }
}
