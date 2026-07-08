/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  org.lwjgl.glfw.GLFW
 */
package code.alphadlc.display.screens.clickgui.dropdown;

import code.alphadlc.AlphaDLC;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.Decelerate;
import code.alphadlc.display.screens.clickgui.components.implement.window.AbstractWindow;
import code.alphadlc.display.screens.clickgui.components.implement.window.WindowManager;
import code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.color.ColorWindow;
import code.alphadlc.display.screens.clickgui.dropdown.CategoryPanel;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.utils.client.bind.BindCaptureHelper;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import org.lwjgl.glfw.GLFW;

public class DropDownScreen
extends class_437
implements QuickImports {
    private static DropDownScreen instance;
    private static final float PANEL_WIDTH = 115.0f;
    private static final float PANEL_HEIGHT = 240.0f;
    private static final float PANEL_SPACING = 10.0f;
    private final List<CategoryPanel> panels = new ArrayList<CategoryPanel>();
    private final Decelerate menuAnimation = (Decelerate)new Decelerate().setMs(300).setValue(1.0);
    private final WindowManager localWindowManager = new WindowManager();
    private boolean closing;
    private boolean searchFocused;
    private String searchText = "";
    private float guiScale = 1.0f;
    private float autoScale = 1.0f;
    private boolean themesOpen;
    private float themesAnim;
    private String activeGlassSlider;

    public static DropDownScreen getInstance() {
        if (instance == null) {
            instance = new DropDownScreen();
        }
        return instance;
    }

    private DropDownScreen() {
        super((class_2561)class_2561.method_43470((String)"DropDown"));
    }

    public void openGui() {
        this.closing = false;
        this.menuAnimation.reset();
        this.menuAnimation.setDirection(Direction.FORWARDS);
        this.searchFocused = false;
        this.searchText = "";
        this.guiScale = 1.0f;
        this.autoScale = 1.0f;
        this.themesOpen = false;
        this.themesAnim = 0.0f;
        this.activeGlassSlider = null;
        if (mc != null) {
            mc.method_1507((class_437)this);
        }
    }

    protected void method_25426() {
        super.method_25426();
        this.closing = false;
        this.searchFocused = false;
        this.searchText = "";
        this.themesOpen = false;
        this.themesAnim = 0.0f;
        this.activeGlassSlider = null;
        this.localWindowManager.getWindows().clear();
        this.rebuildPanels();
    }

    private void rebuildPanels() {
        this.panels.clear();
        List<ModuleCategory> categories = this.getDropdownCategories();
        float totalWidth = (float)categories.size() * 115.0f + (float)Math.max(0, categories.size() - 1) * 10.0f;
        float widthScale = (float)this.field_22789 * 0.92f / Math.max(totalWidth, 1.0f);
        float heightScale = (float)this.field_22790 * 0.78f / Math.max(310.0f, 1.0f);
        this.autoScale = class_3532.method_15363((float)Math.min(1.0f, Math.min(widthScale, heightScale)), (float)0.65f, (float)1.0f);
        float startX = ((float)this.field_22789 - totalWidth) / 2.0f;
        float y = ((float)this.field_22790 - 240.0f) / 2.0f;
        for (int i = 0; i < categories.size(); ++i) {
            float x = startX + (float)i * 125.0f;
            CategoryPanel panel = new CategoryPanel(this, categories.get(i), i, x, y, 115.0f, 240.0f);
            panel.snapPosition(x, y);
            this.panels.add(panel);
        }
    }

    public void method_25393() {
        super.method_25393();
        if (this.menuAnimation.isFinished(Direction.BACKWARDS) && mc != null) {
            mc.method_1507(null);
        }
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.menuAnimation.setDirection(this.closing ? Direction.BACKWARDS : Direction.FORWARDS);
        float anim = this.menuAnimation.getOutput().floatValue();
        class_4587 matrices = context.method_51448();
        int scaledMouseX = (int)this.toGuiX(mouseX);
        int scaledMouseY = (int)this.toGuiY(mouseY);
        if (ThemeManager.isLiquidGlassTheme()) {
            KawaseBlur.INSTANCE.render(matrices, 1, 0.0f);
        }
        if (!ThemeManager.isLiquidGlassTheme()) {
            rectangle.render(ShapeProperties.create(matrices, 0.0, 0.0, this.field_22789, this.field_22790).color(ColorAssist.applyOpacity(-16777216, (int)(90.0f * anim))).build());
        }
        float centerX = (float)this.field_22789 / 2.0f;
        float centerY = (float)this.field_22790 / 2.0f;
        float scale = class_3532.method_15363((float)(this.autoScale * this.guiScale), (float)0.65f, (float)1.75f);
        matrices.method_22903();
        matrices.method_46416(centerX, centerY, 0.0f);
        matrices.method_22905(scale, scale, 1.0f);
        matrices.method_46416(-centerX, -centerY, 0.0f);
        for (CategoryPanel panel : this.panels) {
            panel.render(context, scaledMouseX, scaledMouseY, delta, anim);
        }
        this.themesAnim = 0.0f;
        this.localWindowManager.render(context, scaledMouseX, scaledMouseY, delta);
        matrices.method_22909();
    }

    private void renderSearch(class_332 context, float anim) {
        Object text;
        class_4587 matrices = context.method_51448();
        float barW = 100.0f;
        float barH = 20.0f;
        float x = (float)this.field_22789 / 2.0f - barW / 2.0f;
        float y = (float)this.field_22790 - 40.0f;
        if (ThemeManager.isLiquidGlassTheme()) {
            int bgColor = ThemeManager.BackgroundGui.getColor();
            int rgb = bgColor & 0xFFFFFF;
            rectangle.render(ShapeProperties.create(matrices, x, y, barW, barH).round(6.0f).color(ColorAssist.applyOpacity(rgb, this.searchFocused ? 85 : 60)).build());
        } else {
            rectangle.render(ShapeProperties.create(matrices, x, y, barW, barH).round(6.0f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundGui.getColor(), this.searchFocused ? 210 : 170)).build());
        }
        Object object = text = this.searchText.isEmpty() ? "Search..." : this.searchText;
        if (this.searchFocused && System.currentTimeMillis() % 1000L < 500L) {
            text = (String)text + "|";
        }
        int c = this.searchText.isEmpty() ? ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 90) : ThemeManager.textColor.getColor();
        Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, (String)text, x + 8.0f, y + 11.0f, ColorAssist.applyOpacity(c, (int)(255.0f * anim)));
    }

    private void renderThemesButton(class_332 context, int mouseX, int mouseY, float anim) {
        class_4587 matrices = context.method_51448();
        float bw = 74.0f;
        float bh = 20.0f;
        float bx = (float)this.field_22789 - bw - 14.0f;
        float by = (float)this.field_22790 - bh - 12.0f;
        boolean hover = Calculate.isHovered(mouseX, mouseY, bx, by, bw, bh);
        int bg = ColorAssist.applyOpacity(ThemeManager.BackgroundGui.getColor(), hover ? 220 : 185);
        rectangle.render(ShapeProperties.create(matrices, bx, by, bw, bh).round(6.0f).color(bg).build());
        Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).drawCenteredString(matrices, "Themes", bx + bw / 2.0f, by + 11.4f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(255.0f * anim)));
    }

    private void renderThemesDrawer(class_332 context, int mouseX, int mouseY) {
        if (this.themesAnim < 0.01f) {
            return;
        }
        class_4587 matrices = context.method_51448();
        float dw = 260.0f;
        float dh = 334.0f;
        float dx = (float)this.field_22789 - dw - 14.0f;
        float dyClosed = (float)this.field_22790 + 8.0f;
        float dyOpen = (float)this.field_22790 - dh - 38.0f;
        float dy = class_3532.method_16439((float)this.themesAnim, (float)dyClosed, (float)dyOpen);
        rectangle.render(ShapeProperties.create(matrices, dx, dy, dw, dh).round(8.0f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundGui.getColor(), 226)).build());
        Fonts.getSize(17, Fonts.Type.SuisseIntlMedium).drawString(matrices, "Themes", dx + 12.0f, dy + 17.0f, ThemeManager.textColor.getColor());
        Fonts.getSize(11, Fonts.Type.SuisseIntlMedium).drawString(matrices, this.prettifyTheme(ThemeManager.getCurrentThemeName()), dx + 12.0f, dy + 31.0f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 150));
        String[] themes = ThemeManager.getAvailableThemes();
        float cardW = (dw - 30.0f) / 2.0f;
        float cardH = 34.0f;
        float cardY = dy + 44.0f;
        for (int i = 0; i < themes.length && i < 4; ++i) {
            float cardX = dx + 10.0f + (float)(i % 2) * (cardW + 10.0f);
            float currentY = cardY + (float)(i / 2) * (cardH + 8.0f);
            this.renderThemePresetCard(context, themes[i], cardX, currentY, cardW, cardH);
        }
        float y = dy + 126.0f;
        Fonts.getSize(13, Fonts.Type.SuisseIntlMedium).drawString(matrices, "Colors", dx + 12.0f, y, ThemeManager.textColor.getColor());
        y += 12.0f;
        y = this.renderThemeColorChip(context, "Primary", ThemeManager.primaryColor, dx, y, dw);
        y = this.renderThemeColorChip(context, "Secondary", ThemeManager.secondaryColor, dx, y, dw);
        y = this.renderThemeColorChip(context, "GUI", ThemeManager.BackgroundGui, dx, y, dw);
        y = this.renderThemeColorChip(context, "Modules", ThemeManager.offModuleColor, dx, y, dw);
        y = this.renderThemeColorChip(context, "Settings", ThemeManager.BackgroundSettings, dx, y, dw);
        y = this.renderThemeColorChip(context, "Text", ThemeManager.textColor, dx, y, dw);
        if (ThemeManager.isLiquidGlassTheme()) {
            Fonts.getSize(13, Fonts.Type.SuisseIntlMedium).drawString(matrices, "Liquid Glass", dx + 12.0f, y += 6.0f, ThemeManager.textColor.getColor());
            y += 12.0f;
            y = this.renderToggleRow(context, "Mode", ThemeManager.isLiquidGlassCustomMode() ? "Custom" : "Standard", dx, y, dw);
            if (ThemeManager.isLiquidGlassCustomMode()) {
                y = this.renderGlassSlider(context, "Alpha", "alpha", ThemeManager.getLiquidGlassBaseAlpha(), 0.5f, 1.0f, dx, y, dw, mouseX);
                y = this.renderGlassSlider(context, "Distort", "distort", ThemeManager.getLiquidGlassDistortStrength(), 0.0f, 0.06f, dx, y, dw, mouseX);
                y = this.renderGlassSlider(context, "Smooth", "smooth", ThemeManager.getLiquidGlassSmoothness(), 0.1f, 1.0f, dx, y, dw, mouseX);
            }
        }
    }

    private void renderThemePresetCard(class_332 context, String themeName, float x, float y, float w, float h) {
        class_4587 matrices = context.method_51448();
        boolean active = themeName.equalsIgnoreCase(ThemeManager.getCurrentThemeName());
        int base = active ? ColorAssist.applyOpacity(ColorAssist.getClientColor(), 120) : ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), 130);
        rectangle.render(ShapeProperties.create(matrices, x, y, w, h).round(6.0f).color(base).build());
        if (active) {
            rectangle.render(ShapeProperties.create(matrices, x + 6.0f, y + h - 6.0f, w - 12.0f, 1.6f).round(1.0f).color(ColorAssist.getClientColor(), ColorAssist.getClientColor2(), ColorAssist.getClientColor2(), ColorAssist.getClientColor()).build());
        }
        int[] preview = this.getThemePreview(themeName);
        rectangle.render(ShapeProperties.create(matrices, x + 7.0f, y + 7.0f, 14.0, 14.0).round(4.0f).color(preview[0], preview[1], preview[1], preview[0]).build());
        Fonts.getSize(11, Fonts.Type.SuisseIntlMedium).drawString(matrices, this.prettifyTheme(themeName), x + 26.0f, y + 17.5f, ThemeManager.textColor.getColor());
    }

    private float renderThemeColorChip(class_332 context, String name, ColorSetting setting, float dx, float y, float dw) {
        class_4587 matrices = context.method_51448();
        rectangle.render(ShapeProperties.create(matrices, dx + 10.0f, y, dw - 20.0f, 17.0).round(5.0f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), 90)).build());
        Fonts.getSize(11, Fonts.Type.SuisseIntlMedium).drawString(matrices, name, dx + 17.0f, y + 10.6f, ThemeManager.textColor.getColor());
        rectangle.render(ShapeProperties.create(matrices, dx + dw - 29.0f, y + 4.0f, 10.0, 10.0).round(4.0f).color(setting.getColor()).build());
        return y + 19.0f;
    }

    private float renderToggleRow(class_332 context, String label, String value, float dx, float y, float dw) {
        class_4587 matrices = context.method_51448();
        rectangle.render(ShapeProperties.create(matrices, dx + 10.0f, y, dw - 20.0f, 17.0).round(5.0f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), 95)).build());
        Fonts.getSize(11, Fonts.Type.SuisseIntlMedium).drawString(matrices, label, dx + 17.0f, y + 10.6f, ThemeManager.textColor.getColor());
        float w = Fonts.getSize(11, Fonts.Type.SuisseIntlMedium).getStringWidth(value);
        Fonts.getSize(11, Fonts.Type.SuisseIntlMedium).drawString(matrices, value, dx + dw - w - 17.0f, y + 10.6f, ThemeManager.textColor.getColor());
        return y + 20.0f;
    }

    private float renderGlassSlider(class_332 context, String label, String id, float value, float min, float max, float dx, float y, float dw, int mouseX) {
        class_4587 matrices = context.method_51448();
        Fonts.getSize(11, Fonts.Type.SuisseIntlMedium).drawString(matrices, label, dx + 17.0f, y + 9.0f, ThemeManager.textColor.getColor());
        float barX = dx + 82.0f;
        float barY = y + 7.0f;
        float barW = dw - 102.0f;
        float pct = class_3532.method_15363((float)((value - min) / (max - min)), (float)0.0f, (float)1.0f);
        rectangle.render(ShapeProperties.create(matrices, barX, barY, barW, 3.0).round(2.0f).color(ColorAssist.applyOpacity(ThemeManager.offModuleColor.getColor(), 130)).build());
        rectangle.render(ShapeProperties.create(matrices, barX, barY, barW * pct, 3.0).round(2.0f).color(ColorAssist.getClientColor()).build());
        if (id.equals(this.activeGlassSlider)) {
            float np = class_3532.method_15363((float)(((float)mouseX - barX) / barW), (float)0.0f, (float)1.0f);
            float nv = min + (max - min) * np;
            this.applyGlassSlider(id, nv);
        }
        return y + 16.0f;
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        double sy;
        double sx = this.toGuiX(mouseX);
        if (this.localWindowManager.mouseClicked(sx, sy = this.toGuiY(mouseY), button)) {
            return true;
        }
        this.searchFocused = false;
        for (CategoryPanel panel : this.panels) {
            if (!panel.mouseClicked(sx, sy, button)) continue;
            return true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    private boolean handleThemesClick(double sx, double sy, int button) {
        if (this.themesAnim < 0.05f) {
            return false;
        }
        float dw = 260.0f;
        float dx = (float)this.field_22789 - dw - 14.0f;
        float dyClosed = (float)this.field_22790 + 8.0f;
        float dh = 334.0f;
        float dyOpen = (float)this.field_22790 - dh - 38.0f;
        float dy = class_3532.method_16439((float)this.themesAnim, (float)dyClosed, (float)dyOpen);
        if (!Calculate.isHovered(sx, sy, dx, dy, dw, dh)) {
            return false;
        }
        if (button != 0) {
            return true;
        }
        String[] themes = ThemeManager.getAvailableThemes();
        float cardW = (dw - 30.0f) / 2.0f;
        float cardH = 34.0f;
        float cardY = dy + 44.0f;
        for (int i = 0; i < themes.length && i < 4; ++i) {
            float cardX = dx + 10.0f + (float)(i % 2) * (cardW + 10.0f);
            float currentY = cardY + (float)(i / 2) * (cardH + 8.0f);
            if (!Calculate.isHovered(sx, sy, cardX, currentY, cardW, cardH)) continue;
            ThemeManager.loadTheme(themes[i]);
            return true;
        }
        float colorsY = dy + 138.0f;
        if (Calculate.isHovered(sx, sy, dx + dw - 29.0f, colorsY + 4.0f, 10.0, 10.0)) {
            this.openThemeColorWindow(ThemeManager.primaryColor, (int)sx, (int)sy);
            return true;
        }
        if (Calculate.isHovered(sx, sy, dx + dw - 29.0f, colorsY + 23.0f, 10.0, 10.0)) {
            this.openThemeColorWindow(ThemeManager.secondaryColor, (int)sx, (int)sy);
            return true;
        }
        if (Calculate.isHovered(sx, sy, dx + dw - 29.0f, colorsY + 42.0f, 10.0, 10.0)) {
            this.openThemeColorWindow(ThemeManager.BackgroundGui, (int)sx, (int)sy);
            return true;
        }
        if (Calculate.isHovered(sx, sy, dx + dw - 29.0f, colorsY + 61.0f, 10.0, 10.0)) {
            this.openThemeColorWindow(ThemeManager.offModuleColor, (int)sx, (int)sy);
            return true;
        }
        if (Calculate.isHovered(sx, sy, dx + dw - 29.0f, colorsY + 80.0f, 10.0, 10.0)) {
            this.openThemeColorWindow(ThemeManager.BackgroundSettings, (int)sx, (int)sy);
            return true;
        }
        if (Calculate.isHovered(sx, sy, dx + dw - 29.0f, colorsY + 99.0f, 10.0, 10.0)) {
            this.openThemeColorWindow(ThemeManager.textColor, (int)sx, (int)sy);
            return true;
        }
        if (ThemeManager.isLiquidGlassTheme()) {
            float toggleY = dy + 267.0f;
            if (Calculate.isHovered(sx, sy, dx + 10.0f, toggleY, dw - 20.0f, 17.0)) {
                ThemeManager.setLiquidGlassCustomMode(!ThemeManager.isLiquidGlassCustomMode());
                return true;
            }
            if (ThemeManager.isLiquidGlassCustomMode()) {
                if (Calculate.isHovered(sx, sy, dx + 82.0f, toggleY + 20.0f + 3.0f, dw - 102.0f, 10.0)) {
                    this.activeGlassSlider = "alpha";
                    return true;
                }
                if (Calculate.isHovered(sx, sy, dx + 82.0f, toggleY + 36.0f + 3.0f, dw - 102.0f, 10.0)) {
                    this.activeGlassSlider = "distort";
                    return true;
                }
                if (Calculate.isHovered(sx, sy, dx + 82.0f, toggleY + 52.0f + 3.0f, dw - 102.0f, 10.0)) {
                    this.activeGlassSlider = "smooth";
                    return true;
                }
            }
        }
        return true;
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        double sx = this.toGuiX(mouseX);
        double sy = this.toGuiY(mouseY);
        for (CategoryPanel panel : this.panels) {
            panel.mouseReleased(sx, sy, button);
        }
        this.localWindowManager.mouseReleased(sx, sy, button);
        this.activeGlassSlider = null;
        return super.method_25406(mouseX, mouseY, button);
    }

    public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.activeGlassSlider != null && button == 0) {
            return true;
        }
        return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
    }

    public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        double sy;
        boolean ctrlDown;
        long handle = mc.method_22683().method_4490();
        boolean bl = ctrlDown = GLFW.glfwGetKey((long)handle, (int)341) == 1 || GLFW.glfwGetKey((long)handle, (int)345) == 1;
        if (ctrlDown) {
            this.guiScale = class_3532.method_15363((float)(this.guiScale + (float)verticalAmount * 0.1f), (float)0.65f, (float)1.75f);
            return true;
        }
        double sx = this.toGuiX(mouseX);
        if (this.localWindowManager.mouseScrolled(sx, sy = this.toGuiY(mouseY), verticalAmount)) {
            return true;
        }
        for (CategoryPanel panel : this.panels) {
            if (!panel.mouseScrolled(sx, sy, verticalAmount)) continue;
            return true;
        }
        return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (this.localWindowManager.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        for (CategoryPanel panel : this.panels) {
            if (!panel.keyPressed(keyCode, scanCode, modifiers)) continue;
            return true;
        }
        if (keyCode == 256) {
            if (BindCaptureHelper.isCapturing()) {
                return BindCaptureHelper.handleKey(keyCode, scanCode);
            }
            if (!this.localWindowManager.getWindows().isEmpty()) {
                this.localWindowManager.getWindows().forEach(window -> window.startCloseAnimation());
                return true;
            }
            if (this.searchFocused) {
                this.searchFocused = false;
                this.searchText = "";
                return true;
            }
            this.closing = true;
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    public boolean method_25400(char chr, int modifiers) {
        if (this.localWindowManager.charTyped(chr, modifiers)) {
            return true;
        }
        for (CategoryPanel panel : this.panels) {
            if (!panel.charTyped(chr)) continue;
            return true;
        }
        return super.method_25400(chr, modifiers);
    }

    public boolean method_25422() {
        return false;
    }

    private double toGuiX(double screenX) {
        float scale = class_3532.method_15363((float)(this.autoScale * this.guiScale), (float)0.65f, (float)1.75f);
        return (screenX - (double)this.field_22789 / 2.0) / (double)scale + (double)this.field_22789 / 2.0;
    }

    private double toGuiY(double screenY) {
        float scale = class_3532.method_15363((float)(this.autoScale * this.guiScale), (float)0.65f, (float)1.75f);
        return (screenY - (double)this.field_22790 / 2.0) / (double)scale + (double)this.field_22790 / 2.0;
    }

    public WindowManager getLocalWindowManager() {
        return this.localWindowManager;
    }

    public String getSearchText() {
        return "";
    }

    public List<Module> getModulesForCategory(ModuleCategory category) {
        ArrayList<Module> result = new ArrayList<Module>();
        if (AlphaDLC.getInstance() == null || AlphaDLC.getInstance().getModuleRepository() == null) {
            return result;
        }
        for (Module module : AlphaDLC.getInstance().getModuleRepository().modules()) {
            ModuleCategory moduleCategory = module.getCategory();
            if (category == ModuleCategory.RENDER) {
                if (moduleCategory != ModuleCategory.RENDER && moduleCategory != ModuleCategory.ESP) continue;
                result.add(module);
                continue;
            }
            if (moduleCategory != category) continue;
            result.add(module);
        }
        return result;
    }

    private List<ModuleCategory> getDropdownCategories() {
        ArrayList<ModuleCategory> result = new ArrayList<ModuleCategory>();
        for (ModuleCategory category : ModuleCategory.values()) {
            if (category == ModuleCategory.ESP || category == ModuleCategory.CONFIGS) continue;
            result.add(category);
        }
        return result;
    }

    public String getCategoryIcon(ModuleCategory category) {
        return switch (category) {
            default -> throw new MatchException(null, null);
            case ModuleCategory.COMBAT -> "b";
            case ModuleCategory.MOVEMENT -> "d";
            case ModuleCategory.RENDER -> "e";
            case ModuleCategory.PLAYER -> "c";
            case ModuleCategory.MISC -> "f";
            case ModuleCategory.CUSTOMIZATION -> "g";
            case ModuleCategory.CONFIGS -> "h";
            case ModuleCategory.ESP -> "i";
        };
    }

    public String getCategoryLabel(ModuleCategory category) {
        return switch (category) {
            case ModuleCategory.CUSTOMIZATION -> "Themes";
            case ModuleCategory.RENDER -> "Render";
            default -> category.getReadableName();
        };
    }

    private String prettifyTheme(String themeName) {
        if (themeName == null || themeName.isBlank()) {
            return "Theme";
        }
        return switch (themeName.toLowerCase(Locale.ROOT)) {
            case "default_dark" -> "Dark";
            case "default_light" -> "Light";
            case "default_liquidglass" -> "Liquid Glass";
            default -> themeName.replace('_', ' ');
        };
    }

    private int[] getThemePreview(String themeName) {
        int[] nArray;
        switch (themeName.toLowerCase(Locale.ROOT)) {
            case "default_light": {
                int[] nArray2 = new int[2];
                nArray2[0] = -1;
                nArray = nArray2;
                nArray2[1] = -1447447;
                break;
            }
            case "default_liquidglass": {
                int[] nArray3 = new int[2];
                nArray3[0] = 0x33FFFFFF;
                nArray = nArray3;
                nArray3[1] = -1711276033;
                break;
            }
            case "default_dark": {
                int[] nArray4 = new int[2];
                nArray4[0] = -15658732;
                nArray = nArray4;
                nArray4[1] = -14671837;
                break;
            }
            default: {
                int[] nArray5 = new int[2];
                nArray5[0] = ThemeManager.primaryColor.getColor();
                nArray = nArray5;
                nArray5[1] = ThemeManager.secondaryColor.getColor();
            }
        }
        return nArray;
    }

    private void applyGlassSlider(String id, float value) {
        switch (id) {
            case "alpha": {
                ThemeManager.setLiquidGlassBaseAlpha(value);
                break;
            }
            case "distort": {
                ThemeManager.setLiquidGlassDistortStrength(value);
                break;
            }
            case "smooth": {
                ThemeManager.setLiquidGlassSmoothness(value);
            }
        }
    }

    private void openThemeColorWindow(ColorSetting setting, int mouseX, int mouseY) {
        AbstractWindow existing = null;
        for (AbstractWindow window : this.localWindowManager.getWindows()) {
            ColorWindow colorWindow;
            if (!(window instanceof ColorWindow) || (colorWindow = (ColorWindow)window).getSetting() != setting) continue;
            existing = window;
            break;
        }
        if (existing != null) {
            existing.startCloseAnimation();
            return;
        }
        this.localWindowManager.add(new ColorWindow(setting).position(mouseX - 90, mouseY - 10).size(100.0f, 155.0f).draggable(true));
    }
}
