package ua.babka.ui.component;

import net.minecraft.client.gui.DrawContext;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.font.Fonts;
import ua.babka.util.CursorManager;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class SearchField {
    private float x;
    private float y;
    private float width;
    private float height;
    public String text = "";
    private boolean isFocused;
    private final String placeholder;
    private final Animation focusAnim = new Animation(Easing.QUINTIC_OUT, 250L);
    private long lastInteraction;
    private static final float ICON_BOX_W = 17.0f;
    private static final float RADIUS = 4.0f;

    public SearchField(String placeholder) {
        this.placeholder = placeholder;
    }

    public void setBounds(float x2, float y2, float width, float height) {
        this.x = x2;
        this.y = y2;
        this.width = width;
        this.height = height;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float partialTicks) {
        boolean hovered = HoverUtil.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
        if (hovered) {
            CursorManager.requestIBeam();
        }
        this.focusAnim.run(this.isFocused);
        float fv = this.focusAnim.getValue();
        int themeC = ColorProvider.getThemeColor();
        DrawUtil.drawRoundBlur(this.x, this.y, this.width, this.height, 4.0f, ColorProvider.rgba(75, 75, 75, 255.0f), 20.0f);
        DrawUtil.drawRound(this.x, this.y, this.width, this.height, 4.0f, ColorProvider.setAlpha(ColorProvider.getColorWindowBg(), 30));
        if (fv > 0.01f) {
            DrawUtil.drawRound(this.x - 0.5f, this.y - 0.5f, this.width + 1.0f, this.height + 1.0f, 4.5f, ColorProvider.setAlpha(themeC, (int)(60.0f * fv)));
        }
        DrawUtil.drawRound(this.x, this.y, 17.0f, this.height, 4.0f, ColorProvider.interpolateColor(ColorProvider.setAlpha(ColorProvider.getColorHeaderBg(), 80), ColorProvider.setAlpha(themeC, (int)(80.0f * fv)), fv));
        float iconW = Fonts.ICONS_MINCED.get().getWidth("l", 10.0f);
        float iconX = this.x + (17.0f - iconW) / 2.0f + 1.0f;
        float iconY = this.y + this.height / 2.0f - 4.5f;
        DrawUtil.drawText(Fonts.ICONS_MINCED.get(), "l", iconX, iconY, ColorProvider.interpolateColor(ColorProvider.setAlpha(ColorProvider.getColorIcons(), 200), ColorProvider.setAlpha(themeC, 255), fv), 10.0f);
        float textX = this.x + 17.0f + 4.0f;
        float textY = this.y + this.height / 2.0f - 3.5f;
        if (!this.isFocused && this.text.isEmpty()) {
            int phAlpha = (int)(180.0f * (1.0f - fv * 0.3f));
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.placeholder, textX, textY, ColorProvider.rgba(120, 120, 130, phAlpha), 7.0f);
        } else if (!this.text.isEmpty()) {
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.text, textX, textY, ColorProvider.setAlpha(ColorProvider.getColorText(), 255), 7.0f);
            if (this.isFocused) {
                float tw = Fonts.SFREGULAR.get().getWidth(this.text, 7.0f);
                long t2 = System.currentTimeMillis() - this.lastInteraction;
                float cursorAlpha = t2 < 500L ? 1.0f : 0.3f + 0.7f * (float)(Math.sin((double)(t2 - 500L) * 0.0035) * 0.5 + 0.5);
                DrawUtil.drawText(Fonts.SFREGULAR.get(), "|", textX + tw + 1.0f, textY, ColorProvider.setAlpha(themeC, (int)(255.0f * cursorAlpha)), 7.0f);
            }
        } else if (this.isFocused) {
            float tw = Fonts.SFREGULAR.get().getWidth(this.text, 7.0f);
            long t3 = System.currentTimeMillis() - this.lastInteraction;
            float cursorAlpha = t3 < 500L ? 1.0f : 0.3f + 0.7f * (float)(Math.sin((double)(t3 - 500L) * 0.0035) * 0.5 + 0.5);
            DrawUtil.drawText(Fonts.SFREGULAR.get(), "|", textX + tw + 1.0f, textY, ColorProvider.setAlpha(themeC, (int)(255.0f * cursorAlpha)), 7.0f);
        }
    }

    public void charTyped(char codePoint, int modifiers) {
        if (this.isFocused && this.text.length() < 15) {
            this.text = this.text + codePoint;
            this.lastInteraction = System.currentTimeMillis();
        }
    }

    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.isFocused) {
            if (keyCode == 259 && !this.text.isEmpty()) {
                this.text = this.text.substring(0, this.text.length() - 1);
                this.lastInteraction = System.currentTimeMillis();
            }
            if (keyCode == 257 || keyCode == 256) {
                this.isFocused = false;
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        boolean was = this.isFocused;
        this.isFocused = HoverUtil.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
        if (this.isFocused) {
            this.lastInteraction = System.currentTimeMillis();
        }
    }

    public boolean isEmpty() {
        return this.text.isEmpty();
    }

    public void clearFocus() {
        this.isFocused = false;
    }
}

