/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.color.component;

import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.render.shape.implement.Rectangle;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class RGBInputComponent
extends AbstractComponent {
    private final ColorSetting setting;
    private final Rectangle rectangle = new Rectangle();
    private final NumberInputField redField;
    private final NumberInputField greenField;
    private final NumberInputField blueField;
    private final NumberInputField alphaField;

    public RGBInputComponent(ColorSetting setting) {
        this.setting = setting;
        this.redField = new NumberInputField(0, 255);
        this.greenField = new NumberInputField(0, 255);
        this.blueField = new NumberInputField(0, 255);
        this.alphaField = new NumberInputField(0, 255);
        this.updateFieldsFromColor();
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrix = context.method_51448();
        if (!(this.redField.isTyping() || this.greenField.isTyping() || this.blueField.isTyping() || this.alphaField.isTyping())) {
            this.updateFieldsFromColor();
        }
        float fieldY = this.y + 90.0f;
        float fieldWidth = 20.0f;
        float fieldHeight = 10.0f;
        float fieldSpacing = 2.0f;
        this.renderInputField(context, matrix, this.redField, this.x + 6.0f, fieldY, fieldWidth, fieldHeight);
        this.renderInputField(context, matrix, this.greenField, this.x + 6.0f + fieldWidth + fieldSpacing, fieldY, fieldWidth, fieldHeight);
        this.renderInputField(context, matrix, this.blueField, this.x + 6.0f + (fieldWidth + fieldSpacing) * 2.0f, fieldY, fieldWidth, fieldHeight);
        this.renderInputField(context, matrix, this.alphaField, this.x + 6.0f + (fieldWidth + fieldSpacing) * 3.0f, fieldY, fieldWidth, fieldHeight);
    }

    private void renderInputField(class_332 context, class_4587 matrix, NumberInputField field, float x, float y, float width, float height) {
        this.rectangle.render(ShapeProperties.create(matrix, x, y, width, height).round(2.0f).color(ThemeManager.BackgroundSettings.getColor()).thickness(1.0f).outlineColor(field.isTyping() ? new Color(255, 101, 57, 255).getRGB() : new Color(47, 47, 50, 255).getRGB()).build());
        String displayText = field.isTyping() ? field.getText() : String.valueOf(field.getValue());
        float textWidth = Fonts.getSize(11, Fonts.Type.SuisseIntlSemiBold).getStringWidth(displayText);
        float textX = x + (width - textWidth) / 2.0f;
        float textY = y + (height - Fonts.getSize(11, Fonts.Type.SuisseIntlSemiBold).getStringHeight(displayText)) / 2.0f;
        Fonts.getSize(11, Fonts.Type.SuisseIntlSemiBold).drawString(matrix, displayText, textX, (double)textY + 5.5, ThemeManager.textColor.getColor());
        if (field.isTyping() && System.currentTimeMillis() % 1000L < 500L) {
            float cursorX = textX + textWidth;
            this.rectangle.render(ShapeProperties.create(matrix, cursorX, y + 1.0f, 1.0, height - 2.0f).color(-1).build());
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            float fieldY = this.y + 90.0f;
            float fieldWidth = 20.0f;
            float fieldHeight = 10.0f;
            float fieldSpacing = 2.0f;
            if (Calculate.isHovered(mouseX, mouseY, this.x + 6.0f, fieldY, fieldWidth, fieldHeight)) {
                this.redField.setTyping(true);
                this.greenField.setTyping(false);
                this.blueField.setTyping(false);
                this.alphaField.setTyping(false);
                SoundManager.playSound(SoundManager.SETTING_INTERACT);
                return true;
            }
            if (Calculate.isHovered(mouseX, mouseY, this.x + 6.0f + fieldWidth + fieldSpacing, fieldY, fieldWidth, fieldHeight)) {
                this.redField.setTyping(false);
                this.greenField.setTyping(true);
                this.blueField.setTyping(false);
                this.alphaField.setTyping(false);
                SoundManager.playSound(SoundManager.SETTING_INTERACT);
                return true;
            }
            if (Calculate.isHovered(mouseX, mouseY, this.x + 6.0f + (fieldWidth + fieldSpacing) * 2.0f, fieldY, fieldWidth, fieldHeight)) {
                this.redField.setTyping(false);
                this.greenField.setTyping(false);
                this.blueField.setTyping(true);
                this.alphaField.setTyping(false);
                SoundManager.playSound(SoundManager.SETTING_INTERACT);
                return true;
            }
            if (Calculate.isHovered(mouseX, mouseY, this.x + 6.0f + (fieldWidth + fieldSpacing) * 3.0f, fieldY, fieldWidth, fieldHeight)) {
                this.redField.setTyping(false);
                this.greenField.setTyping(false);
                this.blueField.setTyping(false);
                this.alphaField.setTyping(true);
                SoundManager.playSound(SoundManager.SETTING_INTERACT);
                return true;
            }
            this.redField.setTyping(false);
            this.greenField.setTyping(false);
            this.blueField.setTyping(false);
            this.alphaField.setTyping(false);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (this.redField.isTyping()) {
            return this.redField.charTyped(chr, modifiers);
        }
        if (this.greenField.isTyping()) {
            return this.greenField.charTyped(chr, modifiers);
        }
        if (this.blueField.isTyping()) {
            return this.blueField.charTyped(chr, modifiers);
        }
        if (this.alphaField.isTyping()) {
            return this.alphaField.charTyped(chr, modifiers);
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.redField.isTyping()) {
            if (this.redField.keyPressed(keyCode, scanCode, modifiers)) {
                this.updateColorFromFields();
                return true;
            }
        } else if (this.greenField.isTyping()) {
            if (this.greenField.keyPressed(keyCode, scanCode, modifiers)) {
                this.updateColorFromFields();
                return true;
            }
        } else if (this.blueField.isTyping()) {
            if (this.blueField.keyPressed(keyCode, scanCode, modifiers)) {
                this.updateColorFromFields();
                return true;
            }
        } else if (this.alphaField.isTyping() && this.alphaField.keyPressed(keyCode, scanCode, modifiers)) {
            this.updateColorFromFields();
            return true;
        }
        return false;
    }

    private void updateColorFromFields() {
        int r = this.redField.getValue();
        int g = this.greenField.getValue();
        int b = this.blueField.getValue();
        int a = this.alphaField.getValue();
        Color newColor = new Color(r, g, b, a);
        this.setting.setColor(newColor.getRGB());
    }

    private void updateFieldsFromColor() {
        Color currentColor = new Color(this.setting.getColor(), true);
        this.redField.setValue(currentColor.getRed());
        this.greenField.setValue(currentColor.getGreen());
        this.blueField.setValue(currentColor.getBlue());
        this.alphaField.setValue(currentColor.getAlpha());
    }

    private static class NumberInputField {
        private final int minValue;
        private final int maxValue;
        private int value;
        private String text = "";
        private boolean typing = false;

        public NumberInputField(int minValue, int maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.value = minValue;
        }

        public boolean isTyping() {
            return this.typing;
        }

        public void setTyping(boolean typing) {
            this.typing = typing;
            if (!typing) {
                try {
                    int newValue = Integer.parseInt(this.text.isEmpty() ? "0" : this.text);
                    this.value = Math.max(this.minValue, Math.min(this.maxValue, newValue));
                    this.text = String.valueOf(this.value);
                }
                catch (NumberFormatException e) {
                    this.text = String.valueOf(this.value);
                }
            } else {
                this.text = String.valueOf(this.value);
            }
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = Math.max(this.minValue, Math.min(this.maxValue, value));
            this.text = String.valueOf(this.value);
        }

        public String getText() {
            return this.text;
        }

        public boolean charTyped(char chr, int modifiers) {
            if (!this.typing) {
                return false;
            }
            if (chr >= '0' && chr <= '9') {
                String newText = this.text + chr;
                try {
                    int newValue = Integer.parseInt(newText);
                    if (newValue <= this.maxValue) {
                        this.text = newText;
                        return true;
                    }
                }
                catch (NumberFormatException numberFormatException) {
                    // empty catch block
                }
            }
            return false;
        }

        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            if (!this.typing) {
                return false;
            }
            switch (keyCode) {
                case 259: {
                    if (this.text.isEmpty()) break;
                    this.text = this.text.substring(0, this.text.length() - 1);
                    return true;
                }
                case 257: {
                    this.typing = false;
                    try {
                        int newValue = Integer.parseInt(this.text.isEmpty() ? "0" : this.text);
                        this.value = Math.max(this.minValue, Math.min(this.maxValue, newValue));
                        this.text = String.valueOf(this.value);
                    }
                    catch (NumberFormatException e) {
                        this.text = String.valueOf(this.value);
                    }
                    return true;
                }
                case 256: {
                    this.typing = false;
                    this.text = String.valueOf(this.value);
                    return true;
                }
            }
            return false;
        }
    }
}
