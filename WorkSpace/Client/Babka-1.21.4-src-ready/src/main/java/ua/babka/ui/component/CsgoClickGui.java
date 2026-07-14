package ua.babka.ui.component;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.lwjgl.glfw.GLFW;
import ua.babka.Babka;
import ua.babka.core.ConfigManager;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.impl.render.Chams;
import ua.babka.module.impl.render.Tags;
import ua.babka.module.setting.BindSetting;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ColorSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.Setting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.module.setting.StringSetting;
import ua.babka.module.setting.ThemeSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.Scissor;
import ua.babka.render.font.Fonts;
import ua.babka.theme.Theme;
import ua.babka.theme.ThemeManager;
import ua.babka.util.CursorManager;
import ua.babka.util.IMinecraft;
import ua.babka.util.Instance;
import ua.babka.util.KeyStorage;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class CsgoClickGui
extends Screen
implements IMinecraft {
    private static final int GUI_W = 420;
    private static final int SIDEBAR_W = 72;
    private static final int MODULE_COLS = 2;
    private static final int CARD_W = 160;
    private static final int CARD_H = 22;
    private static final CategoryInfo[] CATS = new CategoryInfo[]{new CategoryInfo(ModuleCategory.COMBAT, "Combat", "a"), new CategoryInfo(ModuleCategory.MOVEMENT, "Movement", "b"), new CategoryInfo(ModuleCategory.RENDER, "Render", "c"), new CategoryInfo(ModuleCategory.PLAYER, "Player", "d"), new CategoryInfo(ModuleCategory.MISC, "Other", "e")};
    private static final String ICON_CONFIG = "f";
    private static final String ICON_THEMES = "g";
    private ModuleCategory selectedCategory = ModuleCategory.COMBAT;
    private boolean configTabSelected = false;
    private boolean themesTabSelected = false;
    private final Map<Module, Animation> enabledAnim = new HashMap<Module, Animation>();
    private final Map<Module, Animation> cardHoverAnim = new HashMap<Module, Animation>();
    private final Map<String, Animation> configHoverAnim = new HashMap<String, Animation>();
    private final Animation[] catHoverAnims = new Animation[CATS.length];
    private final Animation[] themeHoverAnims = new Animation[THEME_NAMES.length];
    private final Map<Module, Map<Setting, Animation>> settingVisibleAnim = new HashMap<Module, Map<Setting, Animation>>();
    private final Animation openAnimation = new Animation(Easing.CUBIC_OUT, 350L);
    private final Animation categoryAnim = new Animation(Easing.QUINTIC_OUT, 250L);
    private ModuleCategory prevCategory = ModuleCategory.COMBAT;
    private float scroll;
    private float configScroll;
    private float themesScroll;
    private String searchText = "";
    private boolean searching;
    private Module bindingModule;
    private BindSetting bindingBind;
    private SliderSetting dragging;
    private int draggingCatIndex = -1;
    private float dragCatStartY;
    private static final int CONFIG_CARD_W = 100;
    private static final int CONFIG_CARD_H = 44;
    private static final int CONFIG_COLS = 3;
    private String newConfigName = "";
    private boolean typingConfigName = false;
    private String configFeedback = "";
    private long configFeedbackUntil;
    private static final int[][] THEME_PRESETS = new int[][]{{-5618433, -15922668}, {-12276993, -16119272}, {-12255318, -16246257}, {-26300, -15069174}, {-48026, -15071220}, {-7829368, -16119286}, {-10496, -15462392}, {-12285697, -13312}, {-16711766, -47975}, {-10027077, -15061462}, {-39356, -14022118}, {-65536, -15922668}};
    private static final String[] THEME_NAMES = new String[]{"Pink", "Blue", "Зелёная", "Orange", "Blood", "Gray", "Gold", "Blue-Yellow", "Cyan-Pink", "Mint", "Sunset", "Rainbow"};
    private static final int THEME_COLS = 3;
    private static final Set<String> pinnedModuleNames = new HashSet<String>();

    private static int guiH() {
        return 32 + CATS.length * 24 + 80;
    }

    public CsgoClickGui() {
        super(Text.of((String)"CS:GO GUI"));
        int i2;
        for (Module m2 : Babka.getInstance().getModuleStorage().getModules()) {
            this.enabledAnim.put(m2, new Animation(Easing.QUINTIC_OUT, 250L));
        }
        for (i2 = 0; i2 < CATS.length; ++i2) {
            this.catHoverAnims[i2] = new Animation(Easing.QUINTIC_OUT, 200L);
        }
        for (i2 = 0; i2 < THEME_NAMES.length; ++i2) {
            this.themeHoverAnims[i2] = new Animation(Easing.QUINTIC_OUT, 200L);
        }
    }

    public Animation getOpenAnimation() {
        return this.openAnimation;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        CursorManager.reset();
        CursorManager.resetIBeam();
        CursorManager.resetClick();
        this.openAnimation.run(1.0f);
        float a2 = this.openAnimation.getValue();
        int ai2 = (int)(255.0f * a2);
        int sw = mc.getWindow().getScaledWidth();
        int sh = mc.getWindow().getScaledHeight();
        boolean showPreview = this.selectedCategory == ModuleCategory.RENDER && !this.configTabSelected && !this.themesTabSelected;
        int previewW = showPreview ? 120 : 0;
        int gx = sw / 2 - (420 + previewW) / 2;
        int gy = sh / 2 - CsgoClickGui.guiH() / 2;
        DrawUtil.drawRound((float)gx, (float)gy, (float)(420 + previewW), (float)CsgoClickGui.guiH(), 6.0f, ColorProvider.rgba(12, 10, 18, (int)(240.0f * a2)));
        DrawUtil.drawRound((float)gx, (float)gy, (float)(420 + previewW), (float)CsgoClickGui.guiH(), 6.0f, ColorProvider.rgba(255, 255, 255, (int)(8.0f * a2)));
        DrawUtil.drawRound((float)gx, (float)gy, 72.0f, (float)CsgoClickGui.guiH(), 6.0f, ColorProvider.rgba(8, 6, 14, (int)(200.0f * a2)));
        DrawUtil.drawRound((float)(gx + 72), (float)gy, 0.5f, (float)CsgoClickGui.guiH(), 0.0f, ColorProvider.rgba(255, 255, 255, (int)(10.0f * a2)));
        this.renderLogo(gx, gy, a2, ai2);
        float[] catYs = new float[CATS.length];
        float catY = gy + 38;
        for (int i2 = 0; i2 < CATS.length; ++i2) {
            catYs[i2] = catY;
            if (i2 == this.draggingCatIndex) {
                catY += 24.0f;
                continue;
            }
            this.renderCatItem(gx, catY, mouseX, mouseY, i2, a2, ai2);
            catY += 24.0f;
        }
        if (this.draggingCatIndex >= 0) {
            float dcy = MathHelper.clamp((float)(mouseY - 12), (float)(gy + 38), (float)(catY - 24.0f));
            this.renderCatItemGhost(gx, dcy, this.draggingCatIndex, a2, ai2);
        }
        DrawUtil.drawRound((float)(gx + 8), catY + 2.0f, 56.0f, 0.5f, 0.0f, ColorProvider.rgba(255, 255, 255, (int)(12.0f * a2)));
        this.renderSidebarSpecialBtn(gx, catY += 8.0f, mouseX, mouseY, a2, ai2, this.configTabSelected, ICON_CONFIG, "Конфиг");
        this.renderSidebarSpecialBtn(gx, catY += 24.0f, mouseX, mouseY, a2, ai2, this.themesTabSelected, ICON_THEMES, "Темы");
        int contentX = gx + 72 + 10;
        int contentY = gy + 38;
        int contentW = 330;
        int contentH = CsgoClickGui.guiH() - 56;
        if (this.themesTabSelected) {
            this.renderThemesTab(context, contentX, contentY, contentW, contentH, mouseX, mouseY, a2);
        } else if (this.configTabSelected) {
            this.renderConfigTab(context, contentX, contentY, contentW, contentH, mouseX, mouseY, a2);
        } else {
            this.renderModulesTab(context, contentX, contentY, contentW, contentH, mouseX, mouseY, a2);
            this.renderPlayerPreview(context, gx, gy, mouseX, mouseY, a2);
        }
        long win = mc.getWindow().getHandle();
        if (CursorManager.shouldBeHand()) {
            GLFW.glfwSetCursor((long)win, (long)GLFW.glfwCreateStandardCursor((int)221188));
        } else if (CursorManager.shouldIBeam()) {
            GLFW.glfwSetCursor((long)win, (long)GLFW.glfwCreateStandardCursor((int)221186));
        } else if (CursorManager.shouldClick()) {
            GLFW.glfwSetCursor((long)win, (long)GLFW.glfwCreateStandardCursor((int)221188));
        } else {
            GLFW.glfwSetCursor((long)win, (long)GLFW.glfwCreateStandardCursor((int)221185));
        }
    }

    private void renderLogo(int gx, int gy, float a2, int ai2) {
        String top = "BABKA";
        String sub = "CLIENT";
        float tw = Fonts.SFBOLD.get().getWidth(top, 7.5f);
        float sw = Fonts.SFREGULAR.get().getWidth(sub, 5.0f);
        float cx = (float)gx + 36.0f;
        DrawUtil.drawText(Fonts.SFBOLD.get(), top, cx - tw / 2.0f, (float)(gy + 5), ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2), 7.5f);
        DrawUtil.drawText(Fonts.SFREGULAR.get(), sub, cx - sw / 2.0f, (float)(gy + 15), ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2), 5.0f);
        DrawUtil.drawRound((float)(gx + 8), (float)(gy + 24), 56.0f, 0.5f, 0.0f, ColorProvider.rgba(255, 255, 255, (int)(10.0f * a2)));
    }

    private void renderCatItem(int gx, float catY, int mx, int my, int idx, float a2, int ai2) {
        CategoryInfo ci = CATS[idx];
        boolean sel = !this.configTabSelected && !this.themesTabSelected && ci.category == this.selectedCategory;
        boolean hov = HoverUtil.isHovered(mx, my, gx + 5, catY, 62.0, 20.0) && this.draggingCatIndex < 0;
        this.catHoverAnims[idx].run(hov);
        float hv = MathHelper.clamp((float)this.catHoverAnims[idx].getValue(), (float)0.0f, (float)1.0f);
        if (sel) {
            DrawUtil.drawRound((float)(gx + 5), catY, 62.0f, 20.0f, 4.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)(25.0f * a2)));
        } else if (hv > 0.01f) {
            DrawUtil.drawRound((float)(gx + 5), catY, 62.0f, 20.0f, 4.0f, ColorProvider.rgba(255, 255, 255, (int)(6.0f * a2 * hv)));
        }
        if (sel) {
            DrawUtil.drawRound((float)(gx + 5), catY + 3.0f, 2.0f, 14.0f, 1.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2));
        }
        int iconCol = sel ? ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2) : ColorProvider.rgba(140, 140, 150, ai2);
        int textCol = sel ? ColorProvider.rgba(240, 240, 255, ai2) : ColorProvider.rgba(130, 130, 140, ai2);
        float iconW = Fonts.ICONS_MINCED.get().getWidth(ci.icon, 8.0f);
        DrawUtil.drawText(Fonts.ICONS_MINCED.get(), ci.icon, (float)(gx + 12), catY + 6.0f, iconCol, 8.0f);
        DrawUtil.drawText(Fonts.SFREGULAR.get(), ci.name, (float)(gx + 12) + iconW + 5.0f, catY + 6.0f, textCol, 6.0f);
        if (hov) {
            CursorManager.requestHand();
        }
    }

    private void renderCatItemGhost(int gx, float catY, int idx, float a2, int ai2) {
        CategoryInfo ci = CATS[idx];
        DrawUtil.drawRound((float)(gx + 5), catY, 62.0f, 20.0f, 4.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)(35.0f * a2)));
        DrawUtil.drawRound((float)(gx + 5), catY + 3.0f, 2.0f, 14.0f, 1.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2));
        float iconW = Fonts.ICONS_MINCED.get().getWidth(ci.icon, 8.0f);
        DrawUtil.drawText(Fonts.ICONS_MINCED.get(), ci.icon, (float)(gx + 12), catY + 6.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2), 8.0f);
        DrawUtil.drawText(Fonts.SFREGULAR.get(), ci.name, (float)(gx + 12) + iconW + 5.0f, catY + 6.0f, ColorProvider.rgba(255, 255, 255, ai2), 6.0f);
    }

    private void renderSidebarSpecialBtn(int gx, float y2, int mx, int my, float a2, int ai2, boolean sel, String icon, String label) {
        boolean hov;
        boolean bl = hov = HoverUtil.isHovered(mx, my, gx + 5, y2, 62.0, 20.0) && this.draggingCatIndex < 0;
        if (sel) {
            DrawUtil.drawRound((float)(gx + 5), y2, 62.0f, 20.0f, 4.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)(25.0f * a2)));
        } else if (hov) {
            DrawUtil.drawRound((float)(gx + 5), y2, 62.0f, 20.0f, 4.0f, ColorProvider.rgba(255, 255, 255, (int)(6.0f * a2)));
        }
        if (sel) {
            DrawUtil.drawRound((float)(gx + 5), y2 + 3.0f, 2.0f, 14.0f, 1.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2));
        }
        int iconCol = sel ? ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2) : ColorProvider.rgba(140, 140, 150, ai2);
        int textCol = sel ? ColorProvider.rgba(240, 240, 255, ai2) : ColorProvider.rgba(130, 130, 140, ai2);
        float iconW = Fonts.ICONS_MINCED.get().getWidth(icon, 8.0f);
        DrawUtil.drawText(Fonts.ICONS_MINCED.get(), icon, (float)(gx + 12), y2 + 6.0f, iconCol, 8.0f);
        DrawUtil.drawText(Fonts.SFREGULAR.get(), label, (float)(gx + 12) + iconW + 5.0f, y2 + 6.0f, textCol, 6.0f);
        if (hov) {
            CursorManager.requestHand();
        }
    }

    private void renderModulesTab(DrawContext context, int contentX, int contentY, int contentW, int contentH, int mouseX, int mouseY, float a2) {
        int ai2 = (int)(255.0f * a2);
        float searchX = contentX;
        float searchY = contentY - 24;
        float searchW = 160.0f;
        float searchH = 16.0f;
        boolean searchHov = HoverUtil.isHovered(mouseX, mouseY, searchX, searchY, searchW, searchH);
        DrawUtil.drawRound(searchX, searchY, searchW, searchH, 3.0f, ColorProvider.rgba(255, 255, 255, this.searching ? (float)((int)(14.0f * a2)) : (float)((int)(8.0f * a2))));
        DrawUtil.drawText(Fonts.ICONS_MINCED.get(), "h", searchX + 4.0f, searchY + 4.0f, ColorProvider.rgba(160, 160, 170, ai2), 6.0f);
        String displayText = this.searchText.isEmpty() && !this.searching ? "Поиск..." : this.searchText;
        float iconOffset = Fonts.ICONS_MINCED.get().getWidth("h", 6.0f) + 8.0f;
        DrawUtil.drawText(Fonts.SFREGULAR.get(), displayText, searchX + iconOffset, searchY + 4.0f, this.searchText.isEmpty() && !this.searching ? ColorProvider.rgba(100, 100, 110, ai2) : ColorProvider.rgba(240, 240, 255, ai2), 6.0f);
        if (this.searching) {
            float blink = (float)((Math.sin((double)System.currentTimeMillis() * 0.004) + 1.0) / 2.0);
            float tw = Fonts.SFREGULAR.get().getWidth(this.searchText, 6.0f);
            DrawUtil.drawRound(searchX + iconOffset + tw + 1.0f, searchY + 4.0f, 1.5f, 8.0f, 0.0f, ColorProvider.rgba(255, 255, 255, (int)((float)ai2 * blink)));
        }
        if (searchHov) {
            CursorManager.requestIBeam();
        }
        if (this.searchText.isEmpty()) {
            String badge = this.getCategoryName(this.selectedCategory);
            float bw = Fonts.SFREGULAR.get().getWidth(badge, 6.0f) + 10.0f;
            float bx = (float)(contentX + contentW) - bw;
            DrawUtil.drawRound(bx, searchY, bw, searchH, 3.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)(20.0f * a2)));
            DrawUtil.drawText(Fonts.SFREGULAR.get(), badge, bx + 5.0f, searchY + 4.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2), 6.0f);
        }
        List<Module> modules = this.getFilteredModules();
        Scissor.push();
        Scissor.setFromComponentCoordinates(contentX, contentY, contentW, contentH);
        float[] colYs = new float[2];
        for (int i2 = 0; i2 < 2; ++i2) {
            colYs[i2] = (float)contentY + this.scroll;
        }
        for (Module mod : modules) {
            int bestCol = 0;
            for (int i3 = 1; i3 < 2; ++i3) {
                if (!(colYs[i3] < colYs[bestCol])) continue;
                bestCol = i3;
            }
            float cardX = contentX + bestCol * 166;
            this.renderModuleCard(context, mod, cardX, colYs[bestCol], mouseX, mouseY, a2);
            int n2 = bestCol;
            colYs[n2] = colYs[n2] + (this.getModuleTotalHeight(mod) + 5.0f);
        }
        float maxCol = (float)contentY + this.scroll;
        for (int i4 = 0; i4 < 2; ++i4) {
            maxCol = Math.max(maxCol, colYs[i4]);
        }
        float contentTotalH = maxCol - ((float)contentY + this.scroll);
        this.scroll = Math.min(0.0f, Math.max(this.scroll, (float)contentH - contentTotalH - 8.0f));
        Scissor.pop();
    }

    private String getCategoryName(ModuleCategory cat) {
        for (CategoryInfo ci : CATS) {
            if (ci.category != cat) continue;
            return ci.name;
        }
        return "";
    }

    private List<Module> getFilteredModules() {
        return Babka.getInstance().getModuleStorage().getModules().stream().filter(m2 -> this.searchText.isEmpty() ? m2.getCategory() == this.selectedCategory : m2.getName().toLowerCase().contains(this.searchText.toLowerCase())).sorted((ma, mb) -> {
            boolean eb;
            boolean pb;
            boolean pa = pinnedModuleNames.contains(ma.getName());
            if (pa != (pb = pinnedModuleNames.contains(mb.getName()))) {
                return pa ? -1 : 1;
            }
            boolean ea = ma.getSettings().isEmpty();
            if (ea != (eb = mb.getSettings().isEmpty())) {
                return ea ? 1 : -1;
            }
            return 0;
        }).collect(Collectors.toList());
    }

    private void renderConfigTab(DrawContext context, int contentX, int contentY, int contentW, int contentH, int mouseX, int mouseY, float a2) {
        boolean canSave;
        int ai2 = (int)(255.0f * a2);
        float inputX = contentX;
        float inputY = contentY - 24;
        float inputW = contentW - 40;
        float inputH = 16.0f;
        boolean inputHov = HoverUtil.isHovered(mouseX, mouseY, inputX, inputY, inputW, inputH);
        DrawUtil.drawRound(inputX, inputY, inputW, inputH, 3.0f, ColorProvider.rgba(255, 255, 255, this.typingConfigName ? (float)((int)(14.0f * a2)) : (float)((int)(8.0f * a2))));
        String inputDisplay = this.newConfigName.isEmpty() && !this.typingConfigName ? "Имя конфига..." : this.newConfigName;
        DrawUtil.drawText(Fonts.SFREGULAR.get(), inputDisplay, inputX + 5.0f, inputY + 4.0f, this.newConfigName.isEmpty() && !this.typingConfigName ? ColorProvider.rgba(100, 100, 110, ai2) : ColorProvider.rgba(240, 240, 255, ai2), 6.0f);
        if (this.typingConfigName) {
            float blink = (float)((Math.sin((double)System.currentTimeMillis() * 0.004) + 1.0) / 2.0);
            float tw = Fonts.SFREGULAR.get().getWidth(this.newConfigName, 6.0f);
            DrawUtil.drawRound(inputX + 5.0f + tw + 1.0f, inputY + 4.0f, 1.5f, 8.0f, 0.0f, ColorProvider.rgba(255, 255, 255, (int)((float)ai2 * blink)));
        }
        if (inputHov) {
            CursorManager.requestIBeam();
        }
        float saveBtnX = inputX + inputW + 6.0f;
        float saveBtnW = 32.0f;
        boolean saveHov = HoverUtil.isHovered(mouseX, mouseY, saveBtnX, inputY, saveBtnW, inputH);
        boolean bl = canSave = !this.newConfigName.isBlank();
        DrawUtil.drawRound(saveBtnX, inputY, saveBtnW, inputH, 3.0f, canSave ? ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)((float)(saveHov ? 220 : 170) * a2)) : ColorProvider.rgba(35, 33, 42, ai2));
        float slw = Fonts.SFREGULAR.get().getWidth("Save", 6.0f);
        DrawUtil.drawText(Fonts.SFREGULAR.get(), "Save", saveBtnX + saveBtnW / 2.0f - slw / 2.0f, inputY + 4.0f, ColorProvider.rgba(255, 255, 255, canSave ? (float)ai2 : (float)((int)(80.0f * a2))), 6.0f);
        if (saveHov && canSave) {
            CursorManager.requestHand();
        }
        if (!this.configFeedback.isEmpty() && System.currentTimeMillis() < this.configFeedbackUntil) {
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.configFeedback, (float)contentX, (float)(contentY - 34), ColorProvider.rgba(120, 200, 140, ai2), 5.0f);
        }
        List<ConfigManager.ConfigEntry> configs = ConfigManager.getConfigsWithDate();
        Scissor.push();
        Scissor.setFromComponentCoordinates(contentX, contentY, contentW, contentH);
        float cardY = (float)contentY + this.configScroll;
        int col = 0;
        for (ConfigManager.ConfigEntry entry : configs) {
            float cardX = contentX + col * 105;
            boolean hov = HoverUtil.isHovered(mouseX, mouseY, cardX, cardY, 100.0, 44.0);
            Animation cha = this.configHoverAnim.computeIfAbsent(entry.name(), k2 -> new Animation(Easing.QUINTIC_OUT, 200L));
            cha.run(hov);
            float chv = MathHelper.clamp((float)cha.getValue(), (float)0.0f, (float)1.0f);
            int baseConfigBg = ColorProvider.rgba(26, 24, 34, (int)(200.0f * a2));
            int hoverConfigBg = ColorProvider.rgba(45, 42, 55, (int)(220.0f * a2));
            DrawUtil.drawRound(cardX, cardY, 100.0f, 44.0f, 5.0f, ColorProvider.interpolateColor(baseConfigBg, hoverConfigBg, chv));
            DrawUtil.drawRound(cardX + 2.0f, cardY + 6.0f, 2.0f, 32.0f, 1.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)(160.0f * a2)));
            String name = entry.name();
            String shortName = name.length() > 13 ? name.substring(0, 12) + "…" : name;
            DrawUtil.drawText(Fonts.SFREGULAR.get(), shortName, cardX + 9.0f, cardY + 8.0f, ColorProvider.rgba(230, 230, 245, ai2), 6.5f);
            String dateStr = LocalDateTime.ofInstant(entry.modified(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM HH:mm"));
            DrawUtil.drawText(Fonts.SFREGULAR.get(), dateStr, cardX + 9.0f, cardY + 19.0f, ColorProvider.rgba(110, 110, 125, ai2), 5.0f);
            DrawUtil.drawText(Fonts.SFREGULAR.get(), "ЛКМ загрузить  ПКМ удалить", cardX + 9.0f, cardY + 29.0f, ColorProvider.rgba(75, 75, 90, ai2), 4.5f);
            if (hov) {
                CursorManager.requestHand();
            }
            if (++col < 3) continue;
            col = 0;
            cardY += 49.0f;
        }
        if (configs.isEmpty()) {
            DrawUtil.drawText(Fonts.SFREGULAR.get(), "Нет сохранённых конфигов", (float)contentX, cardY, ColorProvider.rgba(100, 100, 115, ai2), 5.5f);
        }
        Scissor.pop();
    }

    private void renderThemesTab(DrawContext context, int contentX, int contentY, int contentW, int contentH, int mouseX, int mouseY, float a2) {
        int ai2 = (int)(255.0f * a2);
        int themeCardH = 26;
        int gap = 5;
        int themeCardW = (contentW - 2 * gap) / 3;
        int rows = (THEME_NAMES.length - 1) / 3 + 1;
        float gridH = rows * (themeCardH + gap) - gap;
        this.themesScroll = Math.min(0.0f, Math.max(this.themesScroll, (float)contentH - gridH - 8.0f));
        DrawUtil.drawText(Fonts.SFBOLD.get(), "Выберите тему", (float)contentX, (float)(contentY - 18), ColorProvider.rgba(190, 190, 205, ai2), 6.0f);
        String currentThemeName = ThemeManager.getInstance().getCurrentTheme().name;
        Scissor.push();
        Scissor.setFromComponentCoordinates(contentX, contentY, contentW, contentH);
        float cardY = (float)contentY + this.themesScroll;
        int col = 0;
        float rowMaxH = 0.0f;
        for (int i2 = 0; i2 < THEME_NAMES.length; ++i2) {
            float cardX = contentX + col * (themeCardW + gap);
            if (cardX + (float)themeCardW > (float)(contentX + contentW) && col > 0) {
                cardY += rowMaxH + (float)gap;
                rowMaxH = 0.0f;
                col = 0;
                cardX = contentX;
            }
            rowMaxH = Math.max(rowMaxH, (float)themeCardH);
            boolean hover = HoverUtil.isHovered(mouseX, mouseY, cardX, cardY, themeCardW, themeCardH);
            boolean isActive = THEME_NAMES[i2].equals(currentThemeName) || "Rainbow".equals(THEME_NAMES[i2]) && "Радужный".equals(currentThemeName);
            int c1 = THEME_PRESETS[i2][0];
            this.themeHoverAnims[i2].run(hover);
            float thv = MathHelper.clamp((float)this.themeHoverAnims[i2].getValue(), (float)0.0f, (float)1.0f);
            int baseThemeBg = isActive ? ColorProvider.rgba(38, 35, 50, (int)(220.0f * a2)) : ColorProvider.rgba(22, 20, 30, (int)(200.0f * a2));
            int hoverThemeBg = ColorProvider.rgba(50, 47, 62, ai2);
            int bg = ColorProvider.interpolateColor(baseThemeBg, hoverThemeBg, thv);
            DrawUtil.drawRound(cardX, cardY, (float)themeCardW, (float)themeCardH, 4.0f, bg);
            if (isActive) {
                DrawUtil.drawRound(cardX, cardY, (float)themeCardW, (float)themeCardH, 4.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)(22.0f * a2)));
                DrawUtil.drawRound(cardX + 2.0f, cardY + 4.0f, 2.0f, (float)(themeCardH - 8), 1.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)(200.0f * a2)));
            }
            int color = THEME_NAMES[i2].equals("Rainbow") ? Color.HSBtoRGB((float)(System.currentTimeMillis() % 3600L) / 3600.0f, 0.85f, 1.0f) : c1;
            float ps = 10.0f;
            DrawUtil.drawRound(cardX + 5.0f, cardY + ((float)themeCardH - ps) / 2.0f, ps, ps, 3.0f, ColorProvider.setAlpha(color, ai2));
            DrawUtil.drawText(Fonts.SFREGULAR.get(), THEME_NAMES[i2], cardX + ps + 8.0f, cardY + (float)themeCardH / 2.0f - 3.0f, ColorProvider.rgba(isActive ? 255 : 210, isActive ? 255 : 210, isActive ? 255 : 210, ai2), 5.5f);
            if (hover) {
                CursorManager.requestHand();
            }
            if (++col < 3) continue;
            cardY += rowMaxH + (float)gap;
            rowMaxH = 0.0f;
            col = 0;
        }
        Scissor.pop();
    }

    private float getModuleTotalHeight(Module mod) {
        float h2 = 22.0f;
        List<Setting> settings = mod.getSettings();
        if (!settings.isEmpty()) {
            h2 += 3.0f;
            for (Setting s2 : settings) {
                Animation va = this.getSettingVisibleAnim(mod, s2);
                float vp = MathHelper.clamp((float)va.getValue(), (float)0.0f, (float)1.0f);
                if (!s2.visible.get().booleanValue() && !(vp > 0.01f)) continue;
                h2 += this.getSettingHeight(s2) * vp;
            }
        }
        return h2;
    }

    private Animation getSettingVisibleAnim(Module mod, Setting s2) {
        return this.settingVisibleAnim.computeIfAbsent(mod, k2 -> new HashMap<Setting, Animation>()).computeIfAbsent(s2, k2 -> new Animation(Easing.QUINTIC_OUT, 200L));
    }

    private float getSettingHeight(Setting s2) {
        if (s2 instanceof BooleanSetting) {
            return 14.0f;
        }
        if (s2 instanceof ModeSetting) {
            ModeSetting ms = (ModeSetting)s2;
            float avail = 142.0f;
            float row = 1.0f;
            float curX = 0.0f;
            for (String val : ms.getModes()) {
                float tw = Fonts.SFREGULAR.get().getWidth(val, 5.0f) + 7.0f;
                if (curX + tw > avail && curX > 0.0f) {
                    row += 1.0f;
                    curX = 0.0f;
                }
                curX += tw;
            }
            return 8.0f + row * 14.0f;
        }
        if (s2 instanceof ModeListSetting) {
            ModeListSetting mls = (ModeListSetting)s2;
            float avail = 142.0f;
            float row = 1.0f;
            float curX = 0.0f;
            for (BooleanSetting bs : mls.getSettings()) {
                float tw = Fonts.SFREGULAR.get().getWidth(bs.getName(), 5.0f) + 7.0f;
                if (curX + tw > avail && curX > 0.0f) {
                    row += 1.0f;
                    curX = 0.0f;
                }
                curX += tw;
            }
            return 8.0f + row * 14.0f;
        }
        if (s2 instanceof SliderSetting) {
            return 22.0f;
        }
        if (s2 instanceof BindSetting) {
            return 14.0f;
        }
        if (s2 instanceof ColorSetting) {
            return 14.0f;
        }
        if (s2 instanceof StringSetting) {
            return 18.0f;
        }
        if (s2 instanceof ThemeSetting) {
            return 8.0f;
        }
        return 11.0f;
    }

    private void renderModuleCard(DrawContext ctx, Module mod, float x2, float y2, int mx, int my, float globalAlpha) {
        this.enabledAnim.get(mod).run(mod.isEnabled());
        float ev = this.enabledAnim.get(mod).getValue();
        float a2 = globalAlpha;
        int ai2 = (int)(255.0f * a2);
        boolean hover = HoverUtil.isHovered(mx, my, x2, y2, 160.0, 22.0);
        if (hover) {
            CursorManager.requestHand();
        }
        Animation ha = this.cardHoverAnim.computeIfAbsent(mod, k2 -> new Animation(Easing.QUINTIC_OUT, 200L));
        ha.run(hover);
        float hv = MathHelper.clamp((float)ha.getValue(), (float)0.0f, (float)1.0f);
        int baseBg = ColorProvider.rgba(22, 20, 30, (int)(200.0f * a2));
        int hoverBg = ColorProvider.rgba(38, 35, 50, ai2);
        int bg = ColorProvider.interpolateColor(baseBg, hoverBg, hv);
        if (ev > 0.01f) {
            bg = ColorProvider.interpolateColor(bg, ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)(35.0f * a2 * ev)), ev * 0.55f);
        }
        DrawUtil.drawRound(x2, y2, 160.0f, 22.0f, 5.0f, bg);
        if (ev > 0.01f) {
            DrawUtil.drawRound(x2 + 2.0f, y2 + 4.0f, 2.0f, 14.0f, 1.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)(255.0f * a2 * ev)));
        }
        if (!this.searchText.isEmpty() && mod.getName().toLowerCase().contains(this.searchText.toLowerCase())) {
            DrawUtil.drawRound(x2 - 1.0f, y2 - 1.0f, 162.0f, 24.0f, 5.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)(60.0f * a2)));
        }
        int nameCol = ColorProvider.interpolateColor(ColorProvider.rgba(130, 130, 145, ai2), ColorProvider.rgba(240, 240, 255, ai2), ev);
        DrawUtil.drawText(Fonts.SFREGULAR.get(), mod.getName(), x2 + 8.0f, y2 + 7.0f, nameCol, 6.5f);
        float pinSize = 5.0f;
        float pinX = x2 + 160.0f - pinSize - 6.0f;
        float pinY = y2 + (22.0f - pinSize) / 2.0f;
        boolean pinned = pinnedModuleNames.contains(mod.getName());
        DrawUtil.drawRound(pinX, pinY, pinSize, pinSize, pinSize / 2.0f, pinned ? ColorProvider.rgba(249, 199, 79, ai2) : ColorProvider.rgba(70, 70, 80, ai2));
        if (mod.getKey() != -1) {
            String keyName = this.getKeyName(mod.getKey());
            float keyW = Fonts.SFREGULAR.get().getWidth(keyName, 5.0f);
            DrawUtil.drawRound(pinX - keyW - 10.0f, y2 + 5.0f, keyW + 6.0f, 12.0f, 2.0f, ColorProvider.rgba(35, 33, 44, ai2));
            DrawUtil.drawText(Fonts.SFREGULAR.get(), keyName, pinX - keyW - 7.0f, y2 + 7.0f, ColorProvider.rgba(110, 110, 125, ai2), 5.0f);
        }
        float settingsY = y2 + 22.0f + 3.0f;
        float totalSetH = 0.0f;
        for (Setting s2 : mod.getSettings()) {
            Animation va = this.getSettingVisibleAnim(mod, s2);
            if (!s2.visible.get().booleanValue() && !(va.getValue() > 0.01f)) continue;
            totalSetH += this.getSettingHeight(s2);
        }
        if (totalSetH > 0.5f) {
            DrawUtil.drawRound(x2, settingsY, 160.0f, totalSetH, 0.0f, ColorProvider.rgba(0, 0, 0, (int)(25.0f * a2)));
            DrawUtil.drawRound(x2 + 5.0f, settingsY, 150.0f, 0.5f, 0.0f, ColorProvider.rgba(255, 255, 255, (int)(8.0f * a2)));
            float compY = settingsY + 1.0f;
            for (Setting s3 : mod.getSettings()) {
                float sh;
                Animation va = this.getSettingVisibleAnim(mod, s3);
                va.run(s3.visible.get());
                float vp = MathHelper.clamp((float)va.getValue(), (float)0.0f, (float)1.0f);
                if (vp < 0.01f || (sh = this.getSettingHeight(s3) * vp) < 0.5f) continue;
                this.renderSetting(mod, s3, x2 + 5.0f, compY, 150.0f, sh, mx, my, a2 * vp);
                compY += sh;
            }
        }
    }

    private String truncate(String text, float maxW, float size) {
        if (Fonts.SFREGULAR.get().getWidth(text, size) <= maxW) {
            return text;
        }
        for (int i2 = text.length(); i2 > 1; --i2) {
            if (!(Fonts.SFREGULAR.get().getWidth(text.substring(0, i2) + "…", size) <= maxW)) continue;
            return text.substring(0, i2) + "…";
        }
        return "…";
    }

    private void renderSetting(Module mod, Setting setting, float x2, float y2, float w2, float h2, int mx, int my, float alpha) {
        float my2;
        Setting bs;
        int ai2 = (int)(255.0f * MathHelper.clamp((float)alpha, (float)0.0f, (float)1.0f));
        if (ai2 <= 0) {
            return;
        }
        if (setting instanceof BooleanSetting) {
            bs = (BooleanSetting)setting;
            boolean hover = HoverUtil.isHovered(mx, my, x2, y2, w2, h2);
            float cbSize = 8.0f;
            float cby = y2 + (h2 - cbSize) / 2.0f;
            int cbBg = ((BooleanSetting)bs).getValue() ? ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2) : ColorProvider.rgba(45, 43, 55, ai2);
            DrawUtil.drawRound(x2 + w2 - cbSize - 4.0f, cby, cbSize, cbSize, 2.0f, cbBg);
            if (((BooleanSetting)bs).getValue()) {
                DrawUtil.drawText(Fonts.SFBOLD.get(), "✓", x2 + w2 - cbSize - 4.0f + 1.5f, cby - 1.0f, ColorProvider.rgba(255, 255, 255, ai2), 6.0f);
            }
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.truncate(bs.getName(), w2 - cbSize - 16.0f, 5.5f), x2 + 4.0f, y2 + 2.0f, ColorProvider.rgba(200, 200, 215, ai2), 5.5f);
            if (hover) {
                CursorManager.requestHand();
            }
        }
        if (setting instanceof SliderSetting) {
            SliderSetting sl = (SliderSetting)setting;
            float slW = Math.max(w2 - 16.0f, 20.0f);
            float slX = x2 + 5.0f;
            float slY = y2 + 10.0f;
            float fill = (float)((sl.getValue() - sl.getMin()) / Math.max(sl.getMax() - sl.getMin(), 1.0));
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.truncate(sl.getName(), w2 - 50.0f, 5.5f), x2 + 4.0f, y2 + 1.0f, ColorProvider.rgba(190, 190, 205, ai2), 5.5f);
            String valStr = String.format("%." + (sl.getStep() < 1.0 ? 1 : 0) + ICON_CONFIG, sl.getValue());
            DrawUtil.drawText(Fonts.SFREGULAR.get(), valStr, x2 + w2 - Fonts.SFREGULAR.get().getWidth(valStr, 5.5f) - 4.0f, y2 + 1.0f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2), 5.5f);
            DrawUtil.drawRound(slX, slY, slW, 2.5f, 1.5f, ColorProvider.rgba(50, 48, 62, ai2));
            DrawUtil.drawRound(slX, slY, slW * fill, 2.5f, 1.5f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2));
            float thumbX = slX + slW * fill - 3.0f;
            DrawUtil.drawRound(thumbX, slY - 1.5f, 6.0f, 6.0f, 3.0f, ColorProvider.rgba(255, 255, 255, ai2));
            if (this.dragging == sl && HoverUtil.isHovered(mx, my, x2, y2, w2, h2)) {
                float pct = MathHelper.clamp((float)(((float)mx - slX) / slW), (float)0.0f, (float)1.0f);
                double val = sl.getMin() + (double)pct * (sl.getMax() - sl.getMin());
                double steps = Math.round(val / sl.getStep());
                sl.setValue(MathHelper.clamp((double)(steps * sl.getStep()), (double)sl.getMin(), (double)sl.getMax()));
            }
        }
        if (setting instanceof ModeSetting) {
            ModeSetting ms = (ModeSetting)setting;
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.truncate(ms.getName(), w2 - 10.0f, 5.5f), x2 + 4.0f, y2 + 1.0f, ColorProvider.rgba(190, 190, 205, ai2), 5.5f);
            float mx2 = x2 + 4.0f;
            my2 = y2 + 9.0f;
            for (String val : ms.getModes()) {
                float tw = Fonts.SFREGULAR.get().getWidth(val, 5.0f) + 6.0f;
                if (mx2 + tw > x2 + w2 - 4.0f && mx2 > x2 + 4.0f) {
                    mx2 = x2 + 4.0f;
                    my2 += 14.0f;
                }
                boolean sel = ms.is(val);
                boolean hov = HoverUtil.isHovered(mx, my, mx2, my2, tw, 10.0);
                DrawUtil.drawRound(mx2, my2, tw, 10.0f, 2.0f, sel ? ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2) : (hov ? ColorProvider.rgba(55, 52, 66, ai2) : ColorProvider.rgba(36, 34, 46, ai2)));
                DrawUtil.drawText(Fonts.SFREGULAR.get(), val, mx2 + 3.0f, my2 + 2.0f, ColorProvider.rgba(200, 200, 215, ai2), 5.0f);
                if (hov) {
                    CursorManager.requestHand();
                }
                mx2 += tw + 3.0f;
            }
        }
        if (setting instanceof ModeListSetting) {
            ModeListSetting mls = (ModeListSetting)setting;
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.truncate(mls.getName(), w2 - 10.0f, 5.5f), x2 + 4.0f, y2 + 1.0f, ColorProvider.rgba(190, 190, 205, ai2), 5.5f);
            float mx2 = x2 + 4.0f;
            my2 = y2 + 9.0f;
            for (BooleanSetting bs2 : mls.getSettings()) {
                float tw = Fonts.SFREGULAR.get().getWidth(bs2.getName(), 5.0f) + 6.0f;
                if (mx2 + tw > x2 + w2 - 4.0f && mx2 > x2 + 4.0f) {
                    mx2 = x2 + 4.0f;
                    my2 += 14.0f;
                }
                boolean sel = bs2.getValue();
                boolean hov = HoverUtil.isHovered(mx, my, mx2, my2, tw, 10.0);
                DrawUtil.drawRound(mx2, my2, tw, 10.0f, 2.0f, sel ? ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2) : (hov ? ColorProvider.rgba(55, 52, 66, ai2) : ColorProvider.rgba(36, 34, 46, ai2)));
                DrawUtil.drawText(Fonts.SFREGULAR.get(), bs2.getName(), mx2 + 3.0f, my2 + 2.0f, ColorProvider.rgba(200, 200, 215, ai2), 5.0f);
                if (hov) {
                    CursorManager.requestHand();
                }
                mx2 += tw + 3.0f;
            }
        }
        if (setting instanceof BindSetting) {
            boolean isBinding;
            bs = (BindSetting)setting;
            boolean bl = isBinding = this.bindingBind == bs;
            String display = isBinding ? "Нажмите клавишу" : (((BindSetting)bs).getValue() != -1 ? KeyStorage.getKey(((BindSetting)bs).getValue()) : "Нет");
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.truncate(bs.getName(), w2 - 40.0f, 5.5f), x2 + 4.0f, y2 + 2.0f, ColorProvider.rgba(200, 200, 215, ai2), 5.5f);
            float bw = Fonts.SFREGULAR.get().getWidth(display, 5.0f) + 8.0f;
            float bx = x2 + w2 - bw - 4.0f;
            DrawUtil.drawRound(bx, y2 + 2.0f, bw, 10.0f, 3.0f, isBinding ? ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2) : ColorProvider.rgba(40, 38, 52, ai2));
            DrawUtil.drawText(Fonts.SFREGULAR.get(), display, bx + bw / 2.0f - Fonts.SFREGULAR.get().getWidth(display, 5.0f) / 2.0f, y2 + 3.0f, ColorProvider.rgba(210, 210, 225, ai2), 5.0f);
            if (HoverUtil.isHovered(mx, my, bx, y2 + 2.0f, bw, 10.0)) {
                CursorManager.requestHand();
            }
        }
        if (setting instanceof ColorSetting) {
            ColorSetting cs = (ColorSetting)setting;
            DrawUtil.drawRound(x2 + w2 - 14.0f, y2 + 2.0f, 10.0f, 10.0f, 2.0f, ColorProvider.rgba(36, 34, 46, ai2));
            DrawUtil.drawRound(x2 + w2 - 13.0f, y2 + 3.0f, 8.0f, 8.0f, 2.0f, cs.getValue() | 0xFF000000);
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.truncate(cs.getName(), w2 - 28.0f, 5.5f), x2 + 4.0f, y2 + 2.0f, ColorProvider.rgba(200, 200, 215, ai2), 5.5f);
            if (HoverUtil.isHovered(mx, my, x2, y2, w2, h2)) {
                CursorManager.requestHand();
            }
        }
        if (setting instanceof StringSetting) {
            StringSetting ss = (StringSetting)setting;
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.truncate(ss.getName(), w2 - 10.0f, 5.5f), x2 + 4.0f, y2 + 1.0f, ColorProvider.rgba(190, 190, 205, ai2), 5.5f);
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.truncate(ss.getValue(), w2 - 14.0f, 5.0f), x2 + 4.0f, y2 + 9.0f, ColorProvider.rgba(120, 120, 135, ai2), 5.0f);
            DrawUtil.drawRound(x2 + 4.0f, y2 + 15.0f, w2 - 8.0f, 0.5f, 0.0f, ColorProvider.rgba(255, 255, 255, (int)(10.0f * alpha)));
        }
        if (setting instanceof ThemeSetting) {
            ThemeSetting ts = (ThemeSetting)setting;
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.truncate(ts.getName(), w2 - 10.0f, 5.5f), x2 + 4.0f, y2 + 1.0f, ColorProvider.rgba(190, 190, 205, ai2), 5.5f);
            float mx2 = x2 + 4.0f;
            float my22 = y2 + 9.0f;
            for (Theme theme : ts.getThemes()) {
                float tw = Fonts.SFREGULAR.get().getWidth(theme.name, 5.0f) + 6.0f;
                if (mx2 + tw > x2 + w2 - 4.0f && mx2 > x2 + 4.0f) {
                    mx2 = x2 + 4.0f;
                    my22 += 14.0f;
                }
                boolean sel = ts.getValue() == theme;
                boolean hov = HoverUtil.isHovered(mx, my, mx2, my22, tw, 10.0);
                DrawUtil.drawRound(mx2, my22, tw, 10.0f, 2.0f, sel ? ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2) : (hov ? ColorProvider.rgba(55, 52, 66, ai2) : ColorProvider.rgba(36, 34, 46, ai2)));
                DrawUtil.drawText(Fonts.SFREGULAR.get(), theme.name, mx2 + 3.0f, my22 + 2.0f, ColorProvider.rgba(200, 200, 215, ai2), 5.0f);
                if (hov) {
                    CursorManager.requestHand();
                }
                mx2 += tw + 3.0f;
            }
        }
    }

    public boolean mouseClicked(double mx, double my, int button) {
        if (this.bindingBind != null) {
            if (button != 0) {
                this.bindingBind.setValue(button);
            }
            this.bindingBind = null;
            return true;
        }
        this.bindingBind = null;
        if (this.bindingModule != null) {
            this.bindingModule = null;
            return true;
        }
        int sw = mc.getWindow().getScaledWidth();
        int sh = mc.getWindow().getScaledHeight();
        int previewW = this.selectedCategory == ModuleCategory.RENDER && !this.configTabSelected && !this.themesTabSelected ? 120 : 0;
        int gx = sw / 2 - (420 + previewW) / 2;
        int gy = sh / 2 - CsgoClickGui.guiH() / 2;
        float catY = gy + 38;
        for (int i2 = 0; i2 < CATS.length; ++i2) {
            if (i2 == this.draggingCatIndex) {
                catY += 24.0f;
                continue;
            }
            if (HoverUtil.isHovered(mx, my, gx + 5, catY, 62.0, 20.0)) {
                if (button == 1) {
                    this.configTabSelected = false;
                    this.themesTabSelected = false;
                    if (this.selectedCategory != CsgoClickGui.CATS[i2].category) {
                        this.prevCategory = this.selectedCategory;
                        this.selectedCategory = CsgoClickGui.CATS[i2].category;
                        this.categoryAnim.reset();
                        this.scroll = 0.0f;
                    }
                    return true;
                }
                if (button == 0) {
                    this.draggingCatIndex = i2;
                    this.dragCatStartY = (float)my;
                }
                return true;
            }
            catY += 24.0f;
        }
        if (HoverUtil.isHovered(mx, my, gx + 5, catY += 8.0f, 62.0, 20.0)) {
            this.configTabSelected = true;
            this.themesTabSelected = false;
            this.searching = false;
            this.searchText = "";
            return true;
        }
        if (HoverUtil.isHovered(mx, my, gx + 5, catY += 24.0f, 62.0, 20.0)) {
            this.themesTabSelected = true;
            this.configTabSelected = false;
            this.searching = false;
            this.searchText = "";
            return true;
        }
        int contentX = gx + 72 + 10;
        int contentY = gy + 38;
        int contentW = 330;
        if (this.configTabSelected) {
            return this.handleConfigTabClick(mx, my, button, contentX, contentY, contentW);
        }
        if (this.themesTabSelected) {
            return this.handleThemesTabClick(mx, my, button, contentX, contentY, contentW);
        }
        return this.handleModulesTabClick(mx, my, button, contentX, contentY, contentW);
    }

    private boolean handleModulesTabClick(double mx, double my, int button, int contentX, int contentY, int contentW) {
        if (HoverUtil.isHovered(mx, my, contentX, contentY - 24, 160.0, 16.0)) {
            this.searching = true;
            return true;
        }
        this.searching = false;
        List<Module> modules = this.getFilteredModules();
        float[] colYs = new float[2];
        for (int i2 = 0; i2 < 2; ++i2) {
            colYs[i2] = (float)contentY + this.scroll;
        }
        for (Module mod : modules) {
            int bestCol = 0;
            for (int i3 = 1; i3 < 2; ++i3) {
                if (!(colYs[i3] < colYs[bestCol])) continue;
                bestCol = i3;
            }
            float cardX = contentX + bestCol * 166;
            float modY = colYs[bestCol];
            float modH = this.getModuleTotalHeight(mod);
            float pinSize = 5.0f;
            float pinX = cardX + 160.0f - pinSize - 6.0f;
            if (button == 0 && HoverUtil.isHovered(mx, my, pinX - 3.0f, modY, pinSize + 6.0f, 22.0)) {
                String name = mod.getName();
                if (pinnedModuleNames.contains(name)) {
                    pinnedModuleNames.remove(name);
                } else {
                    pinnedModuleNames.add(name);
                }
                return true;
            }
            if (HoverUtil.isHovered(mx, my, cardX, modY, 160.0, 22.0)) {
                if (button == 0) {
                    mod.setEnabled(!mod.isEnabled());
                } else if (button == 2) {
                    this.bindingModule = mod;
                }
                return true;
            }
            float compY = modY + 22.0f + 3.0f;
            for (Setting s2 : mod.getSettings()) {
                Animation va = this.getSettingVisibleAnim(mod, s2);
                float vp = MathHelper.clamp((float)va.getValue(), (float)0.0f, (float)1.0f);
                if (vp < 0.01f) continue;
                float sh = this.getSettingHeight(s2) * vp;
                if (HoverUtil.isHovered(mx, my, cardX + 5.0f, compY, 150.0, sh)) {
                    this.handleSettingClick(mod, s2, mx, my, button, cardX + 5.0f, compY, 150.0f);
                    return true;
                }
                compY += sh;
            }
            int n2 = bestCol;
            colYs[n2] = colYs[n2] + (modH + 5.0f);
        }
        return true;
    }

    private boolean handleConfigTabClick(double mx, double my, int button, int contentX, int contentY, int contentW) {
        float inputX = contentX;
        float inputY = contentY - 24;
        float inputW = contentW - 40;
        float inputH = 16.0f;
        if (HoverUtil.isHovered(mx, my, inputX, inputY, inputW, inputH)) {
            this.typingConfigName = true;
            return true;
        }
        float saveBtnX = inputX + inputW + 6.0f;
        if (HoverUtil.isHovered(mx, my, saveBtnX, inputY, 32.0, inputH)) {
            this.typingConfigName = false;
            if (!this.newConfigName.isBlank()) {
                ConfigManager.save(this.newConfigName.trim());
                this.showConfigFeedback("Сохранено: " + this.newConfigName.trim());
                this.newConfigName = "";
            }
            return true;
        }
        this.typingConfigName = false;
        List<ConfigManager.ConfigEntry> configs = ConfigManager.getConfigsWithDate();
        float cardY = (float)contentY + this.configScroll;
        int col = 0;
        for (ConfigManager.ConfigEntry entry : configs) {
            float cardX = contentX + col * 105;
            if (HoverUtil.isHovered(mx, my, cardX, cardY, 100.0, 44.0)) {
                if (button == 0) {
                    ConfigManager.load(entry.name());
                    this.showConfigFeedback("Загружено: " + entry.name());
                } else if (button == 1) {
                    ConfigManager.delete(entry.name());
                    this.showConfigFeedback("Удалено: " + entry.name());
                }
                return true;
            }
            if (++col < 3) continue;
            col = 0;
            cardY += 49.0f;
        }
        return true;
    }

    private boolean handleThemesTabClick(double mx, double my, int button, int contentX, int contentY, int contentW) {
        int themeCardH = 26;
        int gap = 5;
        int themeCardW = (contentW - 2 * gap) / 3;
        float cardY = (float)contentY + this.themesScroll;
        int col = 0;
        float rowMaxH = 0.0f;
        for (int i2 = 0; i2 < THEME_NAMES.length; ++i2) {
            float cardX = contentX + col * (themeCardW + gap);
            if (cardX + (float)themeCardW > (float)(contentX + contentW) && col > 0) {
                cardY += rowMaxH + (float)gap;
                rowMaxH = 0.0f;
                col = 0;
                cardX = contentX;
            }
            rowMaxH = Math.max(rowMaxH, (float)themeCardH);
            if (HoverUtil.isHovered(mx, my, cardX, cardY, themeCardW, themeCardH) && button == 0) {
                Theme t2 = ThemeManager.getInstance().getCurrentTheme();
                t2.setColors(THEME_PRESETS[i2][0], THEME_PRESETS[i2][1]);
                t2.name = THEME_NAMES[i2];
                if ("Rainbow".equals(t2.name)) {
                    t2.name = "Радужный";
                }
                return true;
            }
            if (++col < 3) continue;
            cardY += rowMaxH + (float)gap;
            rowMaxH = 0.0f;
            col = 0;
        }
        return true;
    }

    private void showConfigFeedback(String text) {
        this.configFeedback = text;
        this.configFeedbackUntil = System.currentTimeMillis() + 2000L;
    }

    private void handleSettingClick(Module mod, Setting s2, double mx, double my, int button, float x2, float y2, float w2) {
        block11: {
            block15: {
                boolean isBindActive;
                block14: {
                    block13: {
                        block12: {
                            block10: {
                                if (!(s2 instanceof BooleanSetting)) break block10;
                                BooleanSetting bs = (BooleanSetting)s2;
                                if (button != 0) break block11;
                                bs.toggle();
                                break block11;
                            }
                            if (!(s2 instanceof ModeSetting)) break block12;
                            ModeSetting ms = (ModeSetting)s2;
                            float mx2 = x2 + 4.0f;
                            float my2 = y2 + 9.0f;
                            for (String val : ms.getModes()) {
                                float tw = Fonts.SFREGULAR.get().getWidth(val, 5.0f) + 6.0f;
                                if (mx2 + tw > x2 + w2 - 4.0f && mx2 > x2 + 4.0f) {
                                    mx2 = x2 + 4.0f;
                                    my2 += 14.0f;
                                }
                                if (HoverUtil.isHovered(mx, my, mx2, my2, tw, 10.0)) {
                                    ms.setValue(val);
                                    return;
                                }
                                mx2 += tw + 3.0f;
                            }
                            break block11;
                        }
                        if (!(s2 instanceof ModeListSetting)) break block13;
                        ModeListSetting mls = (ModeListSetting)s2;
                        float mx2 = x2 + 4.0f;
                        float my2 = y2 + 9.0f;
                        for (BooleanSetting bs : mls.getSettings()) {
                            float tw = Fonts.SFREGULAR.get().getWidth(bs.getName(), 5.0f) + 6.0f;
                            if (mx2 + tw > x2 + w2 - 4.0f && mx2 > x2 + 4.0f) {
                                mx2 = x2 + 4.0f;
                                my2 += 14.0f;
                            }
                            if (HoverUtil.isHovered(mx, my, mx2, my2, tw, 10.0)) {
                                bs.toggle();
                                return;
                            }
                            mx2 += tw + 3.0f;
                        }
                        break block11;
                    }
                    if (!(s2 instanceof SliderSetting)) break block14;
                    if (button != 0) break block11;
                    this.dragging = (SliderSetting)s2;
                    break block11;
                }
                if (!(s2 instanceof BindSetting)) break block15;
                BindSetting bs = (BindSetting)s2;
                boolean bl = isBindActive = this.bindingBind == bs;
                String bindDisplay = isBindActive ? "Нажмите клавишу" : (bs.getValue() != -1 ? KeyStorage.getKey(bs.getValue()) : "Нет");
                float bindBw = Fonts.SFREGULAR.get().getWidth(bindDisplay, 5.0f) + 8.0f;
                float bindBx = x2 + w2 - bindBw - 4.0f;
                if (!HoverUtil.isHovered(mx, my, bindBx, y2 + 2.0f, bindBw, 10.0) || button != 0) break block11;
                this.bindingBind = bs;
                break block11;
            }
            if (s2 instanceof ThemeSetting) {
                ThemeSetting ts = (ThemeSetting)s2;
                float mx2 = x2 + 4.0f;
                float my2 = y2 + 9.0f;
                for (Theme theme : ts.getThemes()) {
                    float tw = Fonts.SFREGULAR.get().getWidth(theme.name, 5.0f) + 6.0f;
                    if (mx2 + tw > x2 + w2 - 4.0f && mx2 > x2 + 4.0f) {
                        mx2 = x2 + 4.0f;
                        my2 += 14.0f;
                    }
                    if (HoverUtil.isHovered(mx, my, mx2, my2, tw, 10.0)) {
                        ts.setValue(theme);
                        return;
                    }
                    mx2 += tw + 3.0f;
                }
            }
        }
    }

    public boolean mouseReleased(double mx, double my, int button) {
        this.dragging = null;
        if (button == 0 && this.draggingCatIndex >= 0) {
            float dy = (float)(my - (double)this.dragCatStartY);
            if (Math.abs(dy) > 8.0f) {
                int targetIdx = this.draggingCatIndex;
                targetIdx = dy > 0.0f ? Math.min(CATS.length - 1, this.draggingCatIndex + (int)((dy + 8.0f) / 24.0f)) : Math.max(0, this.draggingCatIndex + (int)((dy - 8.0f) / 24.0f));
                if (targetIdx != this.draggingCatIndex) {
                    CategoryInfo dragged = CATS[this.draggingCatIndex];
                    if (targetIdx > this.draggingCatIndex) {
                        System.arraycopy(CATS, this.draggingCatIndex + 1, CATS, this.draggingCatIndex, targetIdx - this.draggingCatIndex);
                    } else {
                        System.arraycopy(CATS, targetIdx, CATS, targetIdx + 1, this.draggingCatIndex - targetIdx);
                    }
                    CsgoClickGui.CATS[targetIdx] = dragged;
                }
            } else {
                CategoryInfo ci = CATS[this.draggingCatIndex];
                this.configTabSelected = false;
                this.themesTabSelected = false;
                if (this.selectedCategory != ci.category) {
                    this.prevCategory = this.selectedCategory;
                    this.selectedCategory = ci.category;
                    this.categoryAnim.reset();
                    this.scroll = 0.0f;
                }
            }
            this.draggingCatIndex = -1;
            return true;
        }
        this.draggingCatIndex = -1;
        return super.mouseReleased(mx, my, button);
    }

    public boolean mouseScrolled(double mx, double my, double ha, double va) {
        int sh;
        int gy;
        int previewW;
        int sw = mc.getWindow().getScaledWidth();
        int gx = sw / 2 - (420 + (previewW = this.selectedCategory == ModuleCategory.RENDER && !this.configTabSelected && !this.themesTabSelected ? 120 : 0)) / 2;
        if (!HoverUtil.isHovered(mx, my, gx + 72, gy = (sh = mc.getWindow().getScaledHeight()) / 2 - CsgoClickGui.guiH() / 2, 348.0, CsgoClickGui.guiH())) {
            return true;
        }
        int contentH = CsgoClickGui.guiH() - 56;
        if (this.themesTabSelected) {
            this.themesScroll += (float)(va * 20.0);
            int themeRows = (THEME_NAMES.length - 1) / 2 + 1;
            float gridH = themeRows * 39 - 5;
            this.themesScroll = Math.min(0.0f, Math.max(this.themesScroll, (float)contentH - gridH - 8.0f));
        } else if (this.configTabSelected) {
            this.configScroll += (float)(va * 20.0);
            float gridH = (ConfigManager.getConfigsWithDate().size() + 3 - 1) / 3 * 49 - 5;
            this.configScroll = Math.min(0.0f, Math.max(this.configScroll, (float)contentH - gridH - 8.0f));
        } else {
            this.scroll += (float)(va * 20.0);
            List<Module> mods = this.getFilteredModules();
            float[] colYs = new float[2];
            for (Module mod : mods) {
                int bestCol = 0;
                for (int i2 = 1; i2 < 2; ++i2) {
                    if (!(colYs[i2] < colYs[bestCol])) continue;
                    bestCol = i2;
                }
                int n2 = bestCol;
                colYs[n2] = colYs[n2] + (this.getModuleTotalHeight(mod) + 5.0f);
            }
            float maxCol = 0.0f;
            for (int i3 = 0; i3 < 2; ++i3) {
                maxCol = Math.max(maxCol, colYs[i3]);
            }
            this.scroll = Math.min(0.0f, Math.max(this.scroll, (float)contentH - maxCol - 8.0f));
        }
        return true;
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            if (this.searching) {
                this.searching = false;
                this.searchText = "";
                return true;
            }
            if (this.typingConfigName) {
                this.typingConfigName = false;
                return true;
            }
            mc.setScreen(null);
            return true;
        }
        if (this.searching) {
            if (keyCode == 259 && !this.searchText.isEmpty()) {
                this.searchText = this.searchText.substring(0, this.searchText.length() - 1);
            }
            return true;
        }
        if (this.typingConfigName) {
            if (keyCode == 259 && !this.newConfigName.isEmpty()) {
                this.newConfigName = this.newConfigName.substring(0, this.newConfigName.length() - 1);
            } else if (keyCode == 257 && !this.newConfigName.isBlank()) {
                ConfigManager.save(this.newConfigName.trim());
                this.showConfigFeedback("Сохранено: " + this.newConfigName.trim());
                this.newConfigName = "";
                this.typingConfigName = false;
            }
            return true;
        }
        if (this.bindingBind != null) {
            if (keyCode == 261 || keyCode == 259) {
                this.bindingBind.setValue(-1);
            } else {
                this.bindingBind.setValue(keyCode);
            }
            this.bindingBind = null;
            return true;
        }
        if (this.bindingModule != null) {
            if (keyCode == 261 || keyCode == 259) {
                this.bindingModule.setKey(-1);
            } else {
                this.bindingModule.setKey(keyCode);
            }
            this.bindingModule = null;
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public boolean charTyped(char chr, int modifiers) {
        if (this.searching) {
            if (chr >= ' ' && chr <= '~') {
                this.searchText = this.searchText + chr;
            }
            return true;
        }
        if (this.typingConfigName) {
            if (chr >= ' ' && chr <= '~' && this.newConfigName.length() < 24 && "\\/:*?\"<>|".indexOf(chr) == -1) {
                this.newConfigName = this.newConfigName + chr;
            }
            return true;
        }
        return super.charTyped(chr, modifiers);
    }

    public boolean shouldPause() {
        return false;
    }
    private void renderPlayerPreview(DrawContext context, int gx, int gy, int mx, int my, float a2) {
        if (this.selectedCategory != ModuleCategory.RENDER || CsgoClickGui.mc.player == null) {
            return;
        }
        int ai2 = (int)(255.0f * a2);
        int previewW = 120;
        int px = gx + 420;
        int py = gy;
        int ph = CsgoClickGui.guiH();
        DrawUtil.drawRound((float)px, (float)py, 0.5f, (float)ph, 0.0f, ColorProvider.rgba(255, 255, 255, (int)(10.0f * a2)));
        DrawUtil.drawText(Fonts.SFREGULAR.get(), "Preview", (float)px + (float)previewW / 2.0f - Fonts.SFREGULAR.get().getWidth("Preview", 6.0f) / 2.0f, (float)(py + 12), ColorProvider.rgba(200, 200, 210, ai2), 6.0f);
        DrawUtil.drawRound((float)(px + 10), (float)(py + 28), (float)(previewW - 20), 0.5f, 0.0f, ColorProvider.rgba(255, 255, 255, (int)(12.0f * a2)));
        float scale = 80.0f;
        float entityX = (float)px + (float)previewW / 2.0f;
        float entityY = py + ph - 24;
        ClientPlayerEntity entity = CsgoClickGui.mc.player;
        float tickDelta = mc.getRenderTickCounter().getTickDelta(true);
        Chams chams = Instance.get(Chams.class);
        boolean chamsOn = chams != null && chams.isEnabled();
        Perspective savedPerspective = CsgoClickGui.mc.options.getPerspective();
        float savedBodyYaw = entity.bodyYaw;
        float savedPrevBodyYaw = entity.prevBodyYaw;
        float savedHeadYaw = entity.headYaw;
        float savedPrevHeadYaw = entity.prevHeadYaw;
        float savedYaw = entity.getYaw();
        float savedPrevYaw = entity.prevYaw;
        float savedPitch = entity.getPitch();
        float savedPrevPitch = entity.prevPitch;
        EntityPose savedPose = entity.getPose();
        try {
            CsgoClickGui.mc.options.setPerspective(Perspective.THIRD_PERSON_BACK);
            Chams.PREVIEW_MODE = true;
            entity.bodyYaw = 0.0f;
            entity.prevBodyYaw = 0.0f;
            entity.headYaw = 0.0f;
            entity.prevHeadYaw = 0.0f;
            entity.setYaw(0.0f);
            entity.prevYaw = 0.0f;
            entity.setPitch(0.0f);
            entity.prevPitch = 0.0f;
            entity.setPose(EntityPose.STANDING);
            MatrixStack matrices = new MatrixStack();
            matrices.push();
            matrices.translate((double)entityX, (double)entityY, 50.0);
            matrices.scale(-scale, scale, scale);
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0f));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-33.0f));
            DiffuseLighting.disableGuiDepthLighting();
            VertexConsumerProvider.Immediate immediate = mc.getBufferBuilders().getEntityVertexConsumers();
            mc.getEntityRenderDispatcher().render((Entity)entity, 0.0, 0.0, 0.0, tickDelta, matrices, (VertexConsumerProvider)immediate, 0xF000F0);
            immediate.draw();
            DiffuseLighting.enableGuiDepthLighting();
            matrices.pop();
            if (chamsOn) {
                chams.renderPreview((PlayerEntity)entity, tickDelta, entityX, entityY, scale);
            }
        }
        finally {
            CsgoClickGui.mc.options.setPerspective(savedPerspective);
            entity.bodyYaw = savedBodyYaw;
            entity.prevBodyYaw = savedPrevBodyYaw;
            entity.headYaw = savedHeadYaw;
            entity.prevHeadYaw = savedPrevHeadYaw;
            entity.setYaw(savedYaw);
            entity.prevYaw = savedPrevYaw;
            entity.setPitch(savedPitch);
            entity.prevPitch = savedPrevPitch;
            entity.setPose(savedPose);
            Chams.PREVIEW_MODE = false;
        }
        Tags tags = Instance.get(Tags.class);
        if (tags != null && tags.isEnabled()) {
            float tagY = entityY - scale * 1.8f - 10.0f;
            tags.renderPreview(context, (PlayerEntity)entity, (float)px + (float)previewW / 2.0f, tagY, ai2);
        }
    }

    private String getKeyName(int key) {
        if (key >= 0 && key <= 7) {
            return "M" + (key + 1);
        }
        String n2 = GLFW.glfwGetKeyName((int)key, (int)0);
        if (n2 != null) {
            return n2.toUpperCase();
        }
        if (key == 32) {
            return "SPACE";
        }
        if (key == 340) {
            return "LSHIFT";
        }
        if (key == 344) {
            return "RSHIFT";
        }
        if (key == 341) {
            return "LCTRL";
        }
        if (key == 345) {
            return "RCTRL";
        }
        return "KEY" + key;
    }

    private record CategoryInfo(ModuleCategory category, String name, String icon) {
    }
}

