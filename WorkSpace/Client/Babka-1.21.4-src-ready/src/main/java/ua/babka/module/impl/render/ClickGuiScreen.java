package ua.babka.module.impl.render;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import ua.babka.module.ModuleCategory;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.font.SkycoreIcons;
import ua.babka.theme.Theme;
import ua.babka.theme.ThemeManager;
import ua.babka.ui.component.Panel;
import ua.babka.ui.component.SearchField;
import ua.babka.ui.component.ThemeManagerWindow;
import ua.babka.util.CursorManager;
import ua.babka.util.IMinecraft;
import ua.babka.util.animation.Easing;

public class ClickGuiScreen
extends Screen
implements IMinecraft {
    private final List<Panel> panels = new ArrayList<Panel>();
    private final SearchField searchField = new SearchField("Search...");
    private final ThemeManagerWindow themeManager;

    public ClickGuiScreen() {
        super(Text.of((String)"Avalora Frame"));
        SkycoreIcons.load();
        for (ModuleCategory category : ModuleCategory.values()) {
            this.panels.add(new Panel(category, this));
        }
        this.themeManager = new ThemeManagerWindow(this);
    }

    public void init() {
        for (Panel panel : this.panels) {
            panel.resetSlide();
        }
        this.themeManager.resetSlide();
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        CursorManager.reset();
        CursorManager.resetIBeam();
        CursorManager.resetClick();
        int windowWidth = mc.getWindow().getScaledWidth();
        int windowHeight = mc.getWindow().getScaledHeight();
        float panelWidth = 150.0f;
        float spacing = 5.0f;
        float panelHeight = 260.0f;
        float panelTotalWidth = (float)this.panels.size() * (panelWidth + spacing) - spacing;
        float startX = ((float)windowWidth - panelTotalWidth) / 2.0f;
        float panelY = ((float)windowHeight - panelHeight) / 2.0f - 20.0f;
        for (int i2 = 0; i2 < this.panels.size(); ++i2) {
            Panel panel = this.panels.get(i2);
            panel.getAnimationAlpha().setDuration(650L);
            panel.getAnimationAlpha().run(1.0f);
            panel.getAnimationAlpha().setEasing(Easing.QUINTIC_OUT);
            panel.setX(startX + (float)i2 * (panelWidth + spacing));
            panel.setY(panelY);
            panel.setWidth(panelWidth);
            panel.setHeight(panelHeight);
            panel.render(context.getMatrices(), mouseX, mouseY, delta);
        }
        float searchW = 140.0f;
        float searchH = 18.0f;
        float searchX = startX + (panelTotalWidth - searchW) / 2.0f;
        float searchY = panelY + panelHeight + 13.9f;
        this.searchField.setBounds(searchX, searchY, searchW, searchH);
        this.searchField.render(context, mouseX, mouseY, delta);
        int[][] presets = ThemeManagerWindow.getPresets();
        float sq = 22.0f;
        float gap = 4.0f;
        float pd = 8.0f;
        int cols = 4;
        int rows = (int)Math.ceil((float)presets.length / (float)cols);
        float palW = (float)cols * (sq + gap) - gap + pd * 2.0f;
        float palH = (float)rows * (sq + gap) - gap + pd * 2.0f;
        float palX = 10.0f;
        float palY = (float)windowHeight - palH - 10.0f;
        DrawUtil.drawRound(palX, palY, palW, palH, 5.0f, ColorProvider.rgba(0, 0, 0, 200.0f));
        for (int i3 = 0; i3 < presets.length; ++i3) {
            int col = i3 % cols;
            float sx = palX + pd + (float)col * (sq + gap);
            int row = i3 / cols;
            float sy = palY + pd + (float)row * (sq + gap);
            boolean hov = HoverUtil.isHovered(mouseX, mouseY, sx, sy, sq, sq);
            if (hov) {
                CursorManager.requestHand();
            }
            DrawUtil.drawRound(sx, sy, sq, sq, 4.0f, ColorProvider.setAlpha(presets[i3][0], 255));
        }
        long window = mc.getWindow().getHandle();
        if (CursorManager.shouldBeHand()) {
            GLFW.glfwSetCursor((long)window, (long)GLFW.glfwCreateStandardCursor((int)221188));
        } else if (CursorManager.shouldIBeam()) {
            GLFW.glfwSetCursor((long)window, (long)GLFW.glfwCreateStandardCursor((int)221186));
        } else if (CursorManager.shouldClick()) {
            GLFW.glfwSetCursor((long)window, (long)GLFW.glfwCreateStandardCursor((int)221188));
        } else {
            GLFW.glfwSetCursor((long)window, (long)GLFW.glfwCreateStandardCursor((int)221185));
        }
    }

    private void handleThemeClick(double mouseX, double mouseY) {
        int windowHeight = mc.getWindow().getScaledHeight();
        int[][] presets = ThemeManagerWindow.getPresets();
        float sq = 22.0f;
        float gap = 4.0f;
        float pd = 8.0f;
        int cols = 4;
        int rows = (int)Math.ceil((float)presets.length / (float)cols);
        float palW = (float)cols * (sq + gap) - gap + pd * 2.0f;
        float palH = (float)rows * (sq + gap) - gap + pd * 2.0f;
        float palX = 10.0f;
        float palY = (float)windowHeight - palH - 10.0f;
        for (int i2 = 0; i2 < presets.length; ++i2) {
            int col = i2 % cols;
            float sx = palX + pd + (float)col * (sq + gap);
            int row = i2 / cols;
            float sy = palY + pd + (float)row * (sq + gap);
            if (!HoverUtil.isHovered(mouseX, mouseY, sx, sy, sq, sq)) continue;
            int[] cur = ThemeManagerWindow.buildDefaultColors(presets[i2][0], presets[i2][1]);
            this.themeManager.setCurrentColors(cur);
            this.themeManager.applyColors();
            Theme t2 = ThemeManager.getInstance().getCurrentTheme();
            t2.name = "Тема";
            this.themeManager.save();
            return;
        }
    }

    public boolean searchCheck(String text) {
        return !this.searchField.isEmpty() && !text.replaceAll(" ", "").toLowerCase().contains(this.searchField.text.replaceAll(" ", "").toLowerCase());
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.searchField.mouseClicked(mouseX, mouseY, button);
        if (button == 0) {
            this.handleThemeClick(mouseX, mouseY);
        }
        this.themeManager.mouseClicked(mouseX, mouseY, button);
        if (this.searchField.isEmpty()) {
            for (Panel panel : this.panels) {
                if (!HoverUtil.isHovered(mouseX, mouseY, panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight())) continue;
                panel.mouseClicked(mouseX, mouseY, button);
            }
        } else {
            for (Panel panel : this.panels) {
                panel.mouseClicked(mouseX, mouseY, button);
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Panel panel : this.panels) {
            panel.mouseReleased(mouseX, mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        this.themeManager.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
        for (Panel panel : this.panels) {
            panel.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.searchField.text = "";
            this.searchField.clearFocus();
            this.themeManager.startCloseAnimation();
            long window = mc.getWindow().getHandle();
            GLFW.glfwSetCursor((long)window, (long)GLFW.glfwCreateStandardCursor((int)221185));
            for (Panel panel : this.panels) {
                panel.startCloseAnimation();
            }
        }
        this.searchField.keyPressed(keyCode, scanCode, modifiers);
        for (Panel panel : this.panels) {
            panel.keyPressed(keyCode, scanCode, modifiers);
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public boolean charTyped(char chr, int modifiers) {
        for (Panel panel : this.panels) {
            panel.charTyped(chr, modifiers);
        }
        this.searchField.charTyped(chr, modifiers);
        return true;
    }

    public boolean shouldPause() {
        return false;
    }

    public List<Panel> getPanels() {
        return this.panels;
    }

    public SearchField getSearchField() {
        return this.searchField;
    }

    public ThemeManagerWindow getThemeManager() {
        return this.themeManager;
    }
}

