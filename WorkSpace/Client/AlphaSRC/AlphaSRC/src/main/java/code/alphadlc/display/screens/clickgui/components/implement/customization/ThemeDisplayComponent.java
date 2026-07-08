/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.customization;

import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.render.shape.implement.Rectangle;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class ThemeDisplayComponent
extends AbstractComponent {
    private final Rectangle rectangle = new Rectangle();
    private final List<ThemeButton> themeButtons = new ArrayList<ThemeButton>();
    private float buttonSize = 16.0f;
    private float buttonSpacing = -2.0f;

    public ThemeDisplayComponent() {
        this.updateThemeButtons();
    }

    private void updateThemeButtons() {
        String[] availableThemes;
        this.themeButtons.clear();
        this.themeButtons.add(new ThemeButton("default_dark", true));
        for (String themeName : availableThemes = ThemeManager.getAvailableThemes()) {
            if (themeName.equals("default_dark") || themeName.equals("default_light") || themeName.equals("default_liquidglass")) continue;
            this.themeButtons.add(new ThemeButton(themeName, false));
        }
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrix = context.method_51448();
        this.updateThemeButtons();
        ThemeButton hoveredButton = null;
        float currentX = this.x + 5.0f;
        for (ThemeButton button : this.themeButtons) {
            button.x = currentX;
            button.y = this.y;
            button.size = this.buttonSize;
            this.renderThemeButton(context, matrix, button, mouseX, mouseY);
            if (Calculate.isHovered(mouseX + 6, mouseY + 4, button.x, button.y, button.size, button.size - 5.0f)) {
                hoveredButton = button;
            }
            currentX += this.buttonSize + this.buttonSpacing;
        }
        if (hoveredButton != null) {
            this.renderTooltip(matrix, hoveredButton);
        }
    }

    private void renderThemeButton(class_332 context, class_4587 matrix, ThemeButton button, int mouseX, int mouseY) {
        String icon = "l";
        float iconX = button.x + (button.size - Fonts.getSize(16, Fonts.Type.ALPHADLC_ICONS).getStringWidth(icon)) / 2.0f;
        float iconY = button.y + (button.size - Fonts.getSize(16, Fonts.Type.ALPHADLC_ICONS).getStringHeight(icon)) / 2.0f;
        int[] themeColors = this.getThemeColors(button.themeName);
        Fonts.getSize(16, Fonts.Type.ALPHADLC_ICONS).drawHalfSplitString(matrix, icon, iconX - 6.0f, iconY, themeColors[0], themeColors[1]);
    }

    private void renderTooltip(class_4587 matrix, ThemeButton button) {
        FontRenderer font = Fonts.getSize(11, Fonts.Type.SuisseIntlSemiBold);
        int paddingX = 3;
        float paddingY = 0.75f;
        float width = font.getStringWidth(button.themeName);
        float height = (float)font.getFont().getSize() / 1.5f;
        float tooltipWidth = width + (float)(paddingX * 2);
        float iconOffset = -6.0f;
        float posX = button.x + button.size / 2.0f - tooltipWidth / 2.0f + iconOffset;
        float posY = button.y - height - paddingY * 2.0f - 4.0f;
        this.rectangle.render(ShapeProperties.create(matrix, posX, posY, width + (float)(paddingX * 2), height + paddingY * 2.0f).round(1.0f).outlineColor(new Color(33, 33, 33, 150).getRGB()).color(ColorAssist.HALF_BLACK).build());
        font.drawString(matrix, button.themeName, posX + (float)paddingX, posY + 3.5f, -1);
    }

    private int[] getThemeColors(String themeName) {
        try {
            File themeDir = ThemeManager.getThemeDir();
            File themeFile = new File(themeDir, themeName + ".json");
            if (!themeFile.exists()) {
                System.out.println("[ThemeDisplayComponent] Theme file not found: " + themeFile.getAbsolutePath());
                return new int[]{-1, -1};
            }
            String content = Files.readString(themeFile.toPath());
            JsonObject theme = JsonParser.parseString((String)content).getAsJsonObject();
            if (theme.has("colors")) {
                JsonObject colors = theme.getAsJsonObject("colors");
                int primaryColor = colors.has("\u041f\u0435\u0440\u0432\u044b\u0439 \u0446\u0432\u0435\u0442") ? colors.get("\u041f\u0435\u0440\u0432\u044b\u0439 \u0446\u0432\u0435\u0442").getAsInt() : -1;
                int secondaryColor = colors.has("\u0412\u0442\u043e\u0440\u043e\u0439 \u0446\u0432\u0435\u0442") ? colors.get("\u0412\u0442\u043e\u0440\u043e\u0439 \u0446\u0432\u0435\u0442").getAsInt() : -1;
                return new int[]{primaryColor, secondaryColor};
            }
            return new int[]{-1, -1};
        }
        catch (Exception e) {
            System.out.println("Error loading theme '" + themeName + "': " + e.getMessage());
            e.printStackTrace();
            return new int[]{-1, -1};
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (ThemeButton themeButton : this.themeButtons) {
            if (!Calculate.isHovered(mouseX + 6.0, mouseY + 4.0, themeButton.x, themeButton.y, themeButton.size, themeButton.size - 5.0f)) continue;
            if (button == 0) {
                this.loadTheme(themeButton.themeName);
                return true;
            }
            if (button != 1 || themeButton.isDefault) continue;
            this.deleteTheme(themeButton.themeName);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void loadTheme(String themeName) {
        try {
            ThemeManager.loadTheme(themeName);
        }
        catch (Exception e) {
            System.out.println("Error loading theme: " + e.getMessage());
        }
    }

    private void deleteTheme(String themeName) {
        try {
            ThemeManager.deleteTheme(themeName);
        }
        catch (Exception e) {
            System.out.println("Error deleting theme: " + e.getMessage());
        }
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        for (ThemeButton button : this.themeButtons) {
            if (!Calculate.isHovered(mouseX, mouseY, button.x, button.y, button.size, button.size)) continue;
            return true;
        }
        return false;
    }

    private static class ThemeButton {
        public String themeName;
        public boolean isDefault;
        public float x;
        public float y;
        public float size;

        public ThemeButton(String themeName, boolean isDefault) {
            this.themeName = themeName;
            this.isDefault = isDefault;
        }
    }
}
