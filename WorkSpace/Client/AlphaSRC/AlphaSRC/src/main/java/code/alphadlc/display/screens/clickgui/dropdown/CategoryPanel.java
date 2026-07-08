/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  org.lwjgl.glfw.GLFW
 */
package code.alphadlc.display.screens.clickgui.dropdown;

import code.alphadlc.AlphaDLC;
import code.alphadlc.display.screens.clickgui.components.implement.other.ToggleSwitchRenderer;
import code.alphadlc.display.screens.clickgui.components.implement.window.AbstractWindow;
import code.alphadlc.display.screens.clickgui.components.implement.window.WindowManager;
import code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.color.ColorWindow;
import code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.group.GroupWindow;
import code.alphadlc.display.screens.clickgui.dropdown.DropDownScreen;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.Setting;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ButtonSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.GroupSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.features.module.setting.implement.TextSetting;
import code.alphadlc.utils.animation.util.Animation;
import code.alphadlc.utils.client.bind.BindCaptureHelper;
import code.alphadlc.utils.client.bind.BindCodes;
import code.alphadlc.utils.client.chat.StringHelper;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.BackgroundIcons;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import org.lwjgl.glfw.GLFW;

public class CategoryPanel
implements QuickImports {
    private static final float HEADER_HEIGHT = 24.0f;
    private static final float HEADER_SEPARATOR = 4.0f;
    private static final float MODULE_HEIGHT = 20.0f;
    private static final float CUSTOM_SECTION_GAP = 12.0f;
    private static final long NO_SETTINGS_FLASH_MS = 350L;
    private static final int NO_SETTINGS_TEXT_COLOR = -45734;
    private final DropDownScreen screen;
    private final ModuleCategory category;
    private final int panelIndex;
    private final List<ModuleComponentData> moduleComponents = new ArrayList<ModuleComponentData>();
    private float x;
    private float y;
    private float animatedX;
    private float animatedY;
    private final float width;
    private final float height;
    private float modulesScroll;
    private float modulesScrollTarget;
    private float modulesMaxScroll;
    private float settingsScroll;
    private float settingsScrollTarget;
    private float settingsMaxScroll;
    private float swapping;
    private int panelMouseX;
    private int panelMouseY;
    private ModuleComponentData lastSelected;
    private ModuleComponentData selectedModuleComponent;
    private TextSetting focusedText;
    private SliderSettings draggingSlider;
    private final Map<SliderSettings, Float> sliderAnimations = new HashMap<SliderSettings, Float>();
    private final Map<SliderSettings, Float> sliderDragAnimations = new HashMap<SliderSettings, Float>();
    private final Map<Module, Float> moduleSearchVisibility = new HashMap<Module, Float>();
    private final Map<String, Float> optionHoverAnim = new HashMap<String, Float>();
    private final Map<String, Float> optionActiveAnim = new HashMap<String, Float>();
    private final Map<String, Animation> toggleAnimations = new HashMap<String, Animation>();
    private float panelOpenAnim = 1.0f;
    private float prevAlpha = 1.0f;
    private long lastRenderAtMs;
    private String activeThemeSlider;
    private float activeThemeSliderX;
    private float activeThemeSliderWidth;
    private static boolean sharedSearchFocused;
    private static String sharedSearchText;
    private static float sharedSearchAnim;
    private static String sharedSearchSuggestion;
    private long lastSearchTypeSoundAt;

    public CategoryPanel(DropDownScreen screen, ModuleCategory category, int panelIndex, float x, float y, float width, float height) {
        this.screen = screen;
        this.category = category;
        this.panelIndex = panelIndex;
        this.x = x;
        this.y = y;
        this.animatedX = x;
        this.animatedY = y;
        this.width = width;
        this.height = height;
        this.onInit();
    }

    private void onInit() {
        this.moduleComponents.clear();
        ArrayList<Module> modules = new ArrayList<Module>(this.screen.getModulesForCategory(this.category));
        modules.sort(Comparator.comparing(Module::getName, String.CASE_INSENSITIVE_ORDER));
        for (Module module : modules) {
            this.moduleComponents.add(new ModuleComponentData(module));
        }
        if (this.isSearchHostPanel()) {
            sharedSearchFocused = false;
            sharedSearchText = "";
            sharedSearchSuggestion = "";
            sharedSearchAnim = 0.0f;
        }
    }

    public ModuleCategory getCategory() {
        return this.category;
    }

    public float getX() {
        return this.animatedX;
    }

    public float getY() {
        return this.animatedY;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void snapPosition(float x, float y) {
        this.x = x;
        this.y = y;
        this.animatedX = x;
        this.animatedY = y;
        this.onInit();
    }

    public boolean isHeaderHovered(double mouseX, double mouseY) {
        return Calculate.isHovered(mouseX, mouseY, this.animatedX, this.animatedY, this.width, 24.0);
    }

    public void render(class_332 context, int mouseX, int mouseY, float delta, float alpha) {
        class_4587 matrices = context.method_51448();
        this.panelMouseX = mouseX;
        this.panelMouseY = mouseY;
        long now = System.currentTimeMillis();
        if (now - this.lastRenderAtMs > 260L) {
            this.panelOpenAnim = 0.0f;
        }
        this.lastRenderAtMs = now;
        if (this.prevAlpha < 0.08f && alpha > 0.08f) {
            this.panelOpenAnim = 0.0f;
        }
        this.prevAlpha = alpha;
        this.panelOpenAnim = class_3532.method_16439((float)0.18f, (float)this.panelOpenAnim, (float)1.0f);
        this.animatedX = class_3532.method_16439((float)0.18f, (float)this.animatedX, (float)this.x);
        this.animatedY = class_3532.method_16439((float)0.18f, (float)this.animatedY, (float)(this.y + (1.0f - alpha) * 8.0f));
        this.modulesScroll = class_3532.method_16439((float)0.2f, (float)this.modulesScroll, (float)this.modulesScrollTarget);
        this.settingsScroll = class_3532.method_16439((float)0.2f, (float)this.settingsScroll, (float)this.settingsScrollTarget);
        this.swapping = class_3532.method_16439((float)0.2f, (float)this.swapping, (float)(this.selectedModuleComponent != null ? 1.0f : 0.0f));
        float swapEased = 1.0f - (float)Math.pow(1.0f - this.swapping, 3.0);
        float delay = Math.min(0.24f, (float)this.panelIndex * 0.045f);
        float reveal = class_3532.method_15363((float)((alpha - delay) / Math.max(0.001f, 1.0f - delay)), (float)0.0f, (float)1.0f);
        float openEase = 1.0f - (float)Math.pow(1.0f - reveal, 3.0);
        float closeEase = (float)Math.pow(reveal, 0.85f);
        float appear = class_3532.method_15363((float)(openEase * closeEase * this.panelOpenAnim), (float)0.0f, (float)1.0f);
        float scale = 0.9f + 0.1f * appear;
        float slideY = (1.0f - appear) * 16.0f;
        float slideX = (1.0f - appear) * (this.panelIndex % 2 == 0 ? -5.0f : 5.0f);
        matrices.method_22903();
        float pivotX = this.animatedX + this.width / 2.0f;
        float pivotY = this.animatedY + this.height / 2.0f;
        matrices.method_46416(pivotX + slideX, pivotY + slideY, 0.0f);
        matrices.method_22905(scale, scale, 1.0f);
        matrices.method_46416(-pivotX, -pivotY, 0.0f);
        this.drawPanelBackground(context);
        this.drawHeader(context);
        if (this.selectedModuleComponent != null) {
            this.lastSelected = this.selectedModuleComponent;
        }
        if (this.swapping != 1.0f) {
            this.renderModulesPage(context, this.animatedX - this.width * swapEased, mouseX, mouseY);
        }
        if (this.swapping != 0.0f && this.lastSelected != null) {
            this.renderSettingsPage(context, this.animatedX + this.width * (1.0f - swapEased), this.lastSelected);
        }
        if (this.draggingSlider != null) {
            this.updateSlider(mouseX, this.draggingSlider);
        }
        if (this.activeThemeSlider != null && this.category == ModuleCategory.CUSTOMIZATION) {
            this.updateCustomizationSlider(mouseX);
        }
        matrices.method_22909();
    }

    private void drawPanelBackground(class_332 context) {
        class_4587 matrices = context.method_51448();
        float round = 11.0f;
        if (ThemeManager.isLiquidGlassTheme()) {
            int bgMain = ColorAssist.applyOpacity(ThemeManager.BackgroundGui.getColor(), 116);
            int bgAlt = ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), 104);
            int outline = ColorAssist.applyOpacity(-1, 52);
            rectangle.render(ShapeProperties.create(matrices, this.animatedX, this.animatedY, this.width, this.height).round(round).thickness(0.18f).outlineColor(outline).color(bgMain, bgAlt, bgAlt, bgMain).build());
            rectangle.render(ShapeProperties.create(matrices, this.animatedX + 1.0f, this.animatedY + 1.0f, this.width - 2.0f, 7.0).round(6.0f).color(ColorAssist.applyOpacity(-1, 24)).build());
        } else {
            rectangle.render(ShapeProperties.create(matrices, this.animatedX, this.animatedY, this.width, this.height).round(round).color(ColorAssist.applyOpacity(ThemeManager.BackgroundGui.getColor(), 185)).build());
        }
    }

    private void drawHeader(class_332 context) {
        class_4587 matrices = context.method_51448();
        Fonts.getSize(14, Fonts.Type.SuisseIntlMedium).drawString(matrices, this.screen.getCategoryLabel(this.category), this.animatedX + 10.0f, this.animatedY + 11.0f, ThemeManager.textColor.getColor());
        if (this.category == ModuleCategory.ESP) {
            float iconX = this.animatedX + this.width - 16.0f - 9.0f;
            float iconY = this.animatedY + 11.0f;
            BackgroundIcons.drawEspCategory(context, BackgroundIcons.ESP, iconX, iconY, ThemeManager.textColor.getColor());
        } else {
            Fonts.getSize(15, Fonts.Type.ALPHADLC_ICONS).drawString(matrices, this.screen.getCategoryIcon(this.category), this.animatedX + this.width - 16.0f, this.animatedY + 10.0f, ColorAssist.getClientColor());
        }
        rectangle.render(ShapeProperties.create(matrices, this.animatedX, this.animatedY + 24.0f, this.width, 4.0).color(ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 8)).build());
    }

    private void renderModulesPage(class_332 context, float pageX, int mouseX, int mouseY) {
        class_4587 matrices = context.method_51448();
        float contentY = this.animatedY + 24.0f + 4.0f;
        float contentH = this.height - 24.0f - 4.0f - 0.5f;
        float clipX = this.animatedX + 3.0f;
        float clipY = contentY + 3.0f;
        float clipW = this.width - 6.0f;
        float clipH = contentH - 6.0f;
        AlphaDLC.getInstance().getScissorManager().push(matrices.method_23760().method_23761(), clipX, clipY, clipW, clipH);
        if (this.category == ModuleCategory.CUSTOMIZATION) {
            float totalHeight = this.renderCustomization(context, pageX + 4.0f, contentY + this.modulesScroll + 2.0f, this.width - 8.0f);
            this.modulesMaxScroll = Math.max(0.0f, totalHeight - contentH + 8.0f);
            this.renderVerticalScrollIndicator(context, this.animatedX + this.width - 3.0f, contentY + 4.0f, contentH - 8.0f, this.modulesMaxScroll, this.modulesScroll);
        } else {
            if (this.isSearchHostPanel()) {
                sharedSearchAnim = class_3532.method_16439((float)0.22f, (float)sharedSearchAnim, (float)(sharedSearchFocused || !sharedSearchText.isBlank() ? 1.0f : 0.0f));
                this.updateSearchSuggestion();
                float searchW = 140.0f;
                float searchX = ((float)window.getScaledWidth() - searchW) / 2.0f;
                float searchY = this.animatedY + this.height + 6.0f - 10.0f * sharedSearchAnim;
                if (sharedSearchAnim > 0.01f) {
                    int bg = ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), (int)(170.0f * sharedSearchAnim));
                    rectangle.render(ShapeProperties.create(matrices, searchX, searchY, searchW, 12.0).round(5.0f).color(bg).build());
                    String shown = sharedSearchText;
                    int baseColor = ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(230.0f * sharedSearchAnim));
                    Fonts.getSize(10, Fonts.Type.SuisseIntlMedium).drawString(matrices, shown, searchX + 6.0f, searchY + 8.2f, baseColor);
                    if (!sharedSearchSuggestion.isBlank() && sharedSearchSuggestion.length() > shown.length()) {
                        String suffix = sharedSearchSuggestion.substring(shown.length());
                        float offset = Fonts.getSize(10, Fonts.Type.SuisseIntlMedium).getStringWidth(shown);
                        Fonts.getSize(10, Fonts.Type.SuisseIntlMedium).drawString(matrices, suffix, searchX + 6.0f + offset, searchY + 8.2f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(110.0f * sharedSearchAnim)));
                    } else if (shown.isBlank()) {
                        Fonts.getSize(10, Fonts.Type.SuisseIntlMedium).drawString(matrices, "Search...", searchX + 6.0f, searchY + 8.2f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(96.0f * sharedSearchAnim)));
                    }
                    if (sharedSearchFocused && System.currentTimeMillis() % 1000L < 500L) {
                        float cursorX = searchX + 6.0f + Fonts.getSize(10, Fonts.Type.SuisseIntlMedium).getStringWidth(shown);
                        rectangle.render(ShapeProperties.create(matrices, cursorX + 1.5f, searchY + 3.3f, 0.8f, 6.0).round(1.0f).color(ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(210.0f * sharedSearchAnim))).build());
                    }
                } else {
                    Fonts.getSize(11, Fonts.Type.SuisseIntlMedium).drawCenteredString(matrices, "CTRL + C TO SEARCH", (float)window.getScaledWidth() / 2.0f, searchY + 8.0f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 110));
                }
            }
            float rowY = contentY + this.modulesScroll - 1.0f;
            float total = 0.0f;
            for (ModuleComponentData component : this.moduleComponents) {
                float visibility = this.getSearchVisibility(component.module);
                float rowHeight = 20.0f * visibility;
                if (rowHeight < 0.6f) continue;
                this.renderModuleRow(context, component, pageX, rowY, mouseX, mouseY, visibility);
                rowY += rowHeight;
                total += rowHeight;
            }
            this.modulesMaxScroll = Math.max(0.0f, total - contentH + 8.0f);
            this.renderVerticalScrollIndicator(context, this.animatedX + this.width - 3.0f, contentY + 4.0f, contentH - 8.0f, this.modulesMaxScroll, this.modulesScroll);
        }
        AlphaDLC.getInstance().getScissorManager().pop();
    }

    private void renderModuleRow(class_332 context, ModuleComponentData component, float pageX, float rowY, int mouseX, int mouseY, float visibility) {
        int color;
        float accentAnim;
        class_4587 matrices = context.method_51448();
        Module module = component.module;
        long now = System.currentTimeMillis();
        float shake = 0.0f;
        if (component.shakeUntilMs > now) {
            float remain = (float)(component.shakeUntilMs - now) / 220.0f;
            shake = (float)Math.sin((float)now * 0.09f) * (1.8f * remain);
        }
        float rowHeight = 20.0f * visibility;
        boolean rowHovered = Calculate.isHovered(mouseX, mouseY, pageX, rowY, this.width, rowHeight);
        component.hoverAnim = class_3532.method_16439((float)0.22f, (float)component.hoverAnim, (float)(rowHovered ? 1.0f : 0.0f));
        component.enableAnim = class_3532.method_16439((float)0.22f, (float)component.enableAnim, (float)(module.isState() ? 1.0f : 0.0f));
        boolean selected = component == this.selectedModuleComponent || component == this.lastSelected;
        float eased = 1.0f - (float)Math.pow(1.0f - class_3532.method_15363((float)visibility, (float)0.0f, (float)1.0f), 2.0);
        float slideX = (1.0f - eased) * 8.0f;
        int rowBg = ColorAssist.applyOpacity(ThemeManager.BackgroundGui.getColor(), (int)(58.0f + component.hoverAnim * 28.0f + (selected ? 24.0f : 0.0f)));
        rowBg = ColorAssist.applyOpacity(rowBg, (int)(255.0f * eased));
        rectangle.render(ShapeProperties.create(matrices, pageX + 2.0f + slideX, rowY + 1.4f, this.width - 4.0f, Math.max(1.0f, rowHeight - 2.2f)).round(4.0f).color(rowBg).build());
        float f = accentAnim = selected ? 1.0f : 0.0f;
        if (accentAnim > 0.01f) {
            rectangle.render(ShapeProperties.create(matrices, pageX + 4.0f + slideX, rowY + 4.0f, 1.5, Math.max(1.0f, rowHeight - 8.0f)).round(1.2f).color(ColorAssist.applyOpacity(ColorAssist.getClientColor(), (int)((70.0f + accentAnim * 165.0f) * eased))).build());
        }
        String text = module.getVisibleName();
        int disabledText = -7434605;
        int enabledBase = this.getEnabledModuleTextColor();
        int n = color = module.isState() ? enabledBase : disabledText;
        if (component.noSettingsFlashUntilMs > now) {
            float flash = class_3532.method_15363((float)((float)(component.noSettingsFlashUntilMs - now) / 350.0f), (float)0.0f, (float)1.0f);
            color = this.blendArgb(color, -45734, flash);
        }
        color = ColorAssist.applyOpacity(color, (int)(255.0f * eased));
        Fonts.getSize(12, Fonts.Type.SuisseIntlMedium).drawString(matrices, text, pageX + 10.0f + shake + slideX, rowY + 10.6f, color);
        String bindText = StringHelper.getBindName(module.getKey());
        if (!(bindText == null || bindText.isBlank() || "Unknown".equalsIgnoreCase(bindText) || "None".equalsIgnoreCase(bindText))) {
            float bindW = Fonts.getSize(11, Fonts.Type.SuisseIntlMedium).getStringWidth(bindText);
            Fonts.getSize(11, Fonts.Type.SuisseIntlMedium).drawString(matrices, bindText, pageX + this.width - bindW - 30.0f + slideX, rowY + 10.4f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(145.0f * eased)));
        }
        float checkX = pageX + this.width - 15.0f - component.enableAnim * 2.0f;
        float checkY = rowY + 8.3f;
        if (component.enableAnim > 0.01f) {
            Fonts.getSize(12, Fonts.Type.ALPHADLC_ICONS).drawString(matrices, "k", checkX, checkY, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)((26.0f + 229.0f * component.enableAnim) * eased)));
        }
        rectangle.render(ShapeProperties.create(matrices, pageX, rowY + rowHeight - 0.5f, this.width, 0.5).color(ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(6.0f * eased))).build());
    }

    private void renderSettingsPage(class_332 context, float pageX, ModuleComponentData component) {
        class_4587 matrices = context.method_51448();
        float topY = this.animatedY + 24.0f + 4.0f;
        Fonts.getSize(12, Fonts.Type.ALPHADLC_ICONS).drawString(matrices, "q", pageX + 8.0f, topY + 10.2f, ThemeManager.textColor.getColor());
        Fonts.getSize(16, Fonts.Type.SuisseIntlMedium).drawString(matrices, component.module.getName(), pageX + 18.0f, topY + 10.5f, ThemeManager.textColor.getColor());
        rectangle.render(ShapeProperties.create(matrices, pageX, topY + 20.0f - 0.5f, this.width, 0.5).color(ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 14)).build());
        float contentY = topY + 20.0f + 4.0f;
        float contentH = this.height - 48.0f - 4.0f - 0.5f;
        AlphaDLC.getInstance().getScissorManager().push(matrices.method_23760().method_23761(), this.animatedX, contentY, this.width, contentH);
        float settingY = contentY + this.settingsScroll;
        float total = 0.0f;
        for (Setting setting : component.module.settings()) {
            if (!setting.isVisible()) continue;
            float next = this.renderSetting(context, setting, pageX + 2.0f, settingY, this.width - 4.0f);
            total += next - settingY + 2.0f;
            settingY = next;
        }
        this.settingsMaxScroll = Math.max(0.0f, total - contentH + 8.0f);
        AlphaDLC.getInstance().getScissorManager().pop();
    }

    private float renderSetting(class_332 context, Setting setting, float sx, float sy, float sw) {
        if (setting instanceof BooleanSetting) {
            BooleanSetting bool = (BooleanSetting)setting;
            return this.renderBoolean(context, bool, sx, sy, sw);
        }
        if (setting instanceof SliderSettings) {
            SliderSettings slider = (SliderSettings)setting;
            return this.renderSlider(context, slider, sx, sy, sw);
        }
        if (setting instanceof SelectSetting) {
            SelectSetting select = (SelectSetting)setting;
            return this.renderSelect(context, select.getName(), select.getSelected(), select.getList(), sx, sy, sw);
        }
        if (setting instanceof RadioSetting) {
            RadioSetting radio = (RadioSetting)setting;
            return this.renderSelect(context, radio.getName(), radio.get(), List.of(radio.getOptions()), sx, sy, sw);
        }
        if (setting instanceof MultiSelectSetting) {
            MultiSelectSetting multi = (MultiSelectSetting)setting;
            return this.renderMultiSelect(context, multi, sx, sy, sw);
        }
        if (setting instanceof BindSetting) {
            BindSetting bind = (BindSetting)setting;
            return this.renderBind(context, bind, sx, sy, sw);
        }
        if (setting instanceof TextSetting) {
            TextSetting text = (TextSetting)setting;
            return this.renderText(context, text, sx, sy, sw);
        }
        if (setting instanceof ColorSetting) {
            ColorSetting color = (ColorSetting)setting;
            return this.renderColor(context, color, sx, sy, sw);
        }
        if (setting instanceof ButtonSetting) {
            ButtonSetting button = (ButtonSetting)setting;
            return this.renderButton(context, button, sx, sy, sw);
        }
        if (setting instanceof GroupSetting) {
            GroupSetting group = (GroupSetting)setting;
            return this.renderGroup(context, group, sx, sy, sw);
        }
        return sy + 21.0f;
    }

    private float renderBoolean(class_332 context, BooleanSetting setting, float sx, float sy, float sw) {
        class_4587 matrices = context.method_51448();
        FontRenderer labelFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        ToggleSwitchRenderer.drawLabel(labelFont, matrices, setting.getName(), sx + 7.0f, sy, 21.0f, ThemeManager.textColor.getColor());
        float toggleX = sx + sw - 22.0f - 7.0f;
        float toggleY = ToggleSwitchRenderer.alignedControlY(labelFont, setting.getName(), sy, 21.0f, 10.0f);
        ToggleSwitchRenderer.renderDefault(context, toggleX, toggleY, setting.isValue(), setting);
        return sy + 21.0f;
    }

    private float renderSlider(class_332 context, SliderSettings setting, float sx, float sy, float sw) {
        class_4587 matrices = context.method_51448();
        float currentValue = this.sliderAnimations.getOrDefault(setting, Float.valueOf(setting.getValue())).floatValue();
        currentValue = class_3532.method_16439((float)0.3f, (float)currentValue, (float)setting.getValue());
        this.sliderAnimations.put(setting, Float.valueOf(currentValue));
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, setting.getName(), sx + 7.0f, sy + 7.5f, ThemeManager.textColor.getColor());
        String value = this.formatSlider(setting);
        float valueW = Fonts.getSize(10, Fonts.Type.ROCKSTAR_SEMIBOLD).getStringWidth(value);
        Fonts.getSize(10, Fonts.Type.ROCKSTAR_SEMIBOLD).drawString(matrices, value, sx + sw - valueW - 7.0f, sy + 8.0f, ThemeManager.textColor.getColor());
        float barX = sx + 7.0f;
        float barY = sy + 17.2f;
        float barW = sw - 14.0f;
        float barH = 4.2f;
        float percent = class_3532.method_15363((float)((currentValue - setting.getMin()) / (setting.getMax() - setting.getMin())), (float)0.0f, (float)1.0f);
        float dragAnim = this.sliderDragAnimations.getOrDefault(setting, Float.valueOf(0.0f)).floatValue();
        dragAnim = class_3532.method_16439((float)0.26f, (float)dragAnim, (float)(this.draggingSlider == setting ? 1.0f : 0.0f));
        this.sliderDragAnimations.put(setting, Float.valueOf(dragAnim));
        rectangle.render(ShapeProperties.create(matrices, barX, barY, barW, barH).round(2.1f).color(ColorAssist.applyOpacity(ThemeManager.offModuleColor.getColor(), ThemeManager.isLiquidGlassTheme() ? 58 : 96)).build());
        rectangle.render(ShapeProperties.create(matrices, barX, barY, barW * percent, barH).round(2.1f).color(ColorAssist.getClientColor(), ColorAssist.getClientColor2(), ColorAssist.getClientColor(), ColorAssist.getClientColor2()).build());
        float knobCenterX = barX + barW * percent;
        if (ThemeManager.isLiquidGlassTheme()) {
            float knobW = class_3532.method_16439((float)dragAnim, (float)9.0f, (float)13.0f);
            float knobH = class_3532.method_16439((float)dragAnim, (float)6.0f, (float)9.0f);
            rectangle.render(ShapeProperties.create(matrices, knobCenterX - knobW / 2.0f, barY - (knobH - barH) / 2.0f, knobW, knobH).round(knobH / 2.0f).color(ColorAssist.applyOpacity(-16777216, (int)(72.0f + dragAnim * 46.0f))).build());
        } else {
            float knobW = class_3532.method_16439((float)dragAnim, (float)8.0f, (float)11.0f);
            float knobH = class_3532.method_16439((float)dragAnim, (float)8.0f, (float)6.8f);
            float knobY = barY + (barH - knobH) / 2.0f;
            rectangle.render(ShapeProperties.create(matrices, knobCenterX - knobW / 2.0f, knobY, knobW, knobH).round(knobH / 2.0f).color(ColorAssist.applyOpacity(-16777216, 64)).build());
            rectangle.render(ShapeProperties.create(matrices, knobCenterX - knobW / 2.0f, knobY, knobW, knobH).round(knobH / 2.0f).color(ThemeManager.textColor.getColor()).build());
        }
        return sy + 28.0f;
    }

    private float renderSelect(class_332 context, String name, String selected, List<String> values, float sx, float sy, float sw) {
        class_4587 matrices = context.method_51448();
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, name, sx + 7.0f, sy + 10.3f, ThemeManager.textColor.getColor());
        rectangle.render(ShapeProperties.create(matrices, sx + 6.0f, sy + 17.0f, sw - 12.0f, 8.0f + (float)values.size() * 12.0f).round(6.0f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), 77)).build());
        float offset = 0.0f;
        for (String value : values) {
            float itemY = sy + 24.0f + offset;
            boolean hovered = Calculate.isHovered(this.panelMouseX, this.panelMouseY, sx + 6.0f, sy + 20.0f + offset, sw - 12.0f, 12.0);
            boolean active = value.equals(selected);
            String key = "single:" + name + ":" + value;
            float hoverAnim = this.animateOption(this.optionHoverAnim, key, hovered ? 1.0f : 0.0f);
            float activeAnim = this.animateOption(this.optionActiveAnim, key, active ? 1.0f : 0.0f);
            int textColor = ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(191.0f + 64.0f * hoverAnim + 64.0f * activeAnim));
            Fonts.getSize(10, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, value, sx + 14.0f, itemY + 0.5f, textColor);
            if (activeAnim > 0.01f) {
                Fonts.getSize(11, Fonts.Type.ALPHADLC_ICONS).drawString(matrices, "k", sx + sw - 15.0f - activeAnim * 2.0f, itemY + 0.3f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(26.0f + 229.0f * activeAnim)));
            }
            offset += 12.0f;
        }
        return sy + 24.0f + (float)values.size() * 12.0f + 2.0f;
    }

    private float renderMultiSelect(class_332 context, MultiSelectSetting setting, float sx, float sy, float sw) {
        class_4587 matrices = context.method_51448();
        String name = setting.getName() + " " + setting.getSelected().size() + "/" + setting.getList().size();
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, name, sx + 7.0f, sy + 10.3f, ThemeManager.textColor.getColor());
        rectangle.render(ShapeProperties.create(matrices, sx + 6.0f, sy + 17.0f, sw - 12.0f, 8.0f + (float)setting.getList().size() * 12.0f).round(6.0f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), 77)).build());
        float offset = 0.0f;
        for (String value : setting.getList()) {
            boolean active = setting.getSelected().contains(value);
            boolean hovered = Calculate.isHovered(this.panelMouseX, this.panelMouseY, sx + 6.0f, sy + 20.0f + offset, sw - 12.0f, 12.0);
            String key = "multi:" + setting.getName() + ":" + value;
            float hoverAnim = this.animateOption(this.optionHoverAnim, key, hovered ? 1.0f : 0.0f);
            float activeAnim = this.animateOption(this.optionActiveAnim, key, active ? 1.0f : 0.0f);
            float itemY = sy + 24.0f + offset;
            int textColor = ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(191.0f + 64.0f * hoverAnim + 64.0f * activeAnim));
            Fonts.getSize(10, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, value, sx + 14.0f, itemY + 0.5f, textColor);
            if (activeAnim > 0.01f) {
                Fonts.getSize(11, Fonts.Type.ALPHADLC_ICONS).drawString(matrices, "k", sx + sw - 15.0f - activeAnim * 2.0f, itemY + 0.3f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(26.0f + 229.0f * activeAnim)));
            }
            offset += 12.0f;
        }
        return sy + 24.0f + (float)setting.getList().size() * 12.0f + 2.0f;
    }

    private float renderBind(class_332 context, BindSetting setting, float sx, float sy, float sw) {
        class_4587 matrices = context.method_51448();
        FontRenderer labelFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        ToggleSwitchRenderer.drawLabel(labelFont, matrices, setting.getName(), sx + 7.0f, sy, 21.0f, ThemeManager.textColor.getColor());
        boolean listening = BindCaptureHelper.isCapturing(setting);
        String key = listening ? "..." : BindCodes.getDisplayName(setting.getKey());
        FontRenderer pillFont = Fonts.getSize(12, Fonts.Type.ROCKSTAR_SEMIBOLD);
        float pillW = ToggleSwitchRenderer.bindPillWidth(key);
        float pillH = 14.0f;
        float pillX = sx + sw - pillW - 7.0f;
        float pillY = ToggleSwitchRenderer.bindPillTrackY(labelFont, setting.getName(), sy, 21.0f);
        long pulse = System.currentTimeMillis();
        int pulseAlpha = listening ? (int)(120.0 + 80.0 * Math.abs(Math.sin((double)pulse / 220.0))) : 0;
        int outlineColor = listening ? ColorAssist.applyOpacity(ColorAssist.getClientColor(), pulseAlpha + 80) : ColorAssist.applyOpacity(ColorAssist.getOutline(), 130);
        int fillColor = listening ? ColorAssist.applyOpacity(ColorAssist.getClientColor(), pulseAlpha) : ColorAssist.applyOpacity(ThemeManager.offModuleColor.getColor(), 120);
        rectangle.render(ShapeProperties.create(matrices, pillX, pillY, pillW, pillH).round(5.0f).thickness(listening ? 1.2f : 0.7f).outlineColor(outlineColor).color(fillColor).build());
        ToggleSwitchRenderer.drawPillTextCentered(pillFont, matrices, key, pillX, pillY, pillW, pillH, listening ? ColorAssist.getClientColor() : ThemeManager.textColor.getColor());
        return sy + 21.0f;
    }

    private float renderText(class_332 context, TextSetting setting, float sx, float sy, float sw) {
        Object value;
        class_4587 matrices = context.method_51448();
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, setting.getName(), sx + 7.0f, sy + 10.3f, ThemeManager.textColor.getColor());
        rectangle.render(ShapeProperties.create(matrices, sx + 7.0f, sy + 16.0f, sw - 14.0f, 10.0).round(3.0f).color(ColorAssist.applyOpacity(ThemeManager.offModuleColor.getColor(), 120)).build());
        Object object = value = setting.getText() == null ? "" : setting.getText();
        if (this.focusedText == setting && System.currentTimeMillis() % 1000L < 500L) {
            value = (String)value + "|";
        }
        String shown = ((String)value).isEmpty() ? "Type text..." : value;
        Fonts.getSize(10, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, shown, sx + 10.0f, sy + 22.8f, ((String)value).isEmpty() ? ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 120) : ThemeManager.textColor.getColor());
        return sy + 32.0f;
    }

    private float renderColor(class_332 context, ColorSetting setting, float sx, float sy, float sw) {
        class_4587 matrices = context.method_51448();
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, setting.getName(), sx + 7.0f, sy + 10.3f, ThemeManager.textColor.getColor());
        rectangle.render(ShapeProperties.create(matrices, sx + sw - 18.0f, sy + 5.0f, 9.0, 9.0).round(3.0f).color(setting.getColor()).build());
        return sy + 21.0f;
    }

    private float renderButton(class_332 context, ButtonSetting setting, float sx, float sy, float sw) {
        class_4587 matrices = context.method_51448();
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawCenteredString(matrices, setting.getName(), sx + sw / 2.0f, sy + 10.3f, ThemeManager.textColor.getColor());
        return sy + 26.0f;
    }

    private float renderGroup(class_332 context, GroupSetting setting, float sx, float sy, float sw) {
        class_4587 matrices = context.method_51448();
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, setting.getName(), sx + 7.0f, sy + 10.3f, ThemeManager.textColor.getColor());
        rectangle.render(ShapeProperties.create(matrices, sx + sw - 27.0f, sy + 5.0f, 8.0, 8.0).round(2.0f).color(ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 40)).build());
        rectangle.render(ShapeProperties.create(matrices, sx + sw - 15.0f, sy + 5.0f, 8.0, 8.0).round(2.0f).color(setting.isValue() ? ColorAssist.getClientColor() : ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 35)).build());
        return sy + 21.0f;
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!Calculate.isHovered(mouseX, mouseY, this.animatedX, this.animatedY, this.width, this.height)) {
            this.focusedText = null;
            if (button == 0) {
                sharedSearchFocused = false;
            }
            if (BindCaptureHelper.isCapturing()) {
                return BindCaptureHelper.handleMouse(button);
            }
            return false;
        }
        if (BindCaptureHelper.isCapturing() && this.selectedModuleComponent == null) {
            return BindCaptureHelper.handleMouse(button);
        }
        if (this.selectedModuleComponent == null) {
            if (this.category == ModuleCategory.CUSTOMIZATION) {
                return this.handleCustomizationClick(mouseX, mouseY, button);
            }
            if (button == 0 && this.isSearchHostPanel()) {
                float searchW = 140.0f;
                float searchX = ((float)window.getScaledWidth() - searchW) / 2.0f;
                float searchY = this.animatedY + this.height + 6.0f - 10.0f * sharedSearchAnim;
                if (sharedSearchAnim > 0.01f && Calculate.isHovered(mouseX, mouseY, searchX, searchY, searchW, 12.0)) {
                    sharedSearchFocused = true;
                    return true;
                }
                if (sharedSearchAnim <= 0.01f && Calculate.isHovered(mouseX, mouseY, this.animatedX, searchY - 2.0f, this.width, 14.0)) {
                    sharedSearchFocused = true;
                    return true;
                }
                sharedSearchFocused = false;
            }
            float contentY = this.animatedY + 24.0f + 4.0f;
            float rowY = contentY + this.modulesScroll - 1.0f;
            for (ModuleComponentData component : this.moduleComponents) {
                float visibility = this.getSearchVisibility(component.module);
                float rowHeight = 20.0f * visibility;
                if (rowHeight < 0.6f) continue;
                if (Calculate.isHovered(mouseX, mouseY, this.animatedX, rowY, this.width, rowHeight) && visibility > 0.82f) {
                    if (button == 0) {
                        component.module.switchState();
                        return true;
                    }
                    if (button == 1) {
                        this.openModuleSettings(component);
                        return true;
                    }
                    if (button == 2) {
                        BindCaptureHelper.toggle(component.module);
                        return true;
                    }
                }
                rowY += rowHeight;
            }
            return false;
        }
        float pageX = this.animatedX + this.width * (1.0f - this.swapping);
        float topY = this.animatedY + 24.0f + 4.0f;
        if (button == 0 && Calculate.isHovered(mouseX, mouseY, pageX, topY, this.width, 20.0)) {
            this.selectedModuleComponent = null;
            this.settingsScroll = 0.0f;
            this.settingsScrollTarget = 0.0f;
            BindCaptureHelper.cancel();
            return true;
        }
        if (BindCaptureHelper.isCapturing()) {
            float sy = topY + 20.0f + 4.0f + this.settingsScroll;
            for (Setting setting : this.selectedModuleComponent.module.settings()) {
                if (!setting.isVisible()) continue;
                if (setting instanceof BindSetting) {
                    BindSetting bind = (BindSetting)setting;
                    if (button == 0 && this.isBindSettingHovered(bind, pageX + 2.0f, sy, this.width - 4.0f, mouseX, mouseY)) {
                        BindCaptureHelper.toggle(bind);
                        return true;
                    }
                }
                sy = this.getNextSettingY(setting, sy);
            }
            return BindCaptureHelper.handleMouse(button);
        }
        float sy = topY + 20.0f + 4.0f + this.settingsScroll;
        for (Setting setting : this.selectedModuleComponent.module.settings()) {
            if (!setting.isVisible()) continue;
            float handled = this.handleSettingClick(setting, pageX + 2.0f, sy, this.width - 4.0f, mouseX, mouseY, button);
            if (handled != sy) {
                return true;
            }
            sy = this.getNextSettingY(setting, sy);
        }
        this.focusedText = null;
        return false;
    }

    private float handleSettingClick(Setting setting, float sx, float sy, float sw, double mouseX, double mouseY, int button) {
        float oy;
        if (setting instanceof BooleanSetting) {
            BooleanSetting bool = (BooleanSetting)setting;
            if (button == 0 && Calculate.isHovered(mouseX, mouseY, sx, sy, sw, 19.0)) {
                bool.setValue(!bool.isValue());
                return sy + 1.0f;
            }
        }
        if (setting instanceof SliderSettings) {
            SliderSettings slider = (SliderSettings)setting;
            if (button == 0 && Calculate.isHovered(mouseX, mouseY, sx, sy, sw, 24.0)) {
                this.draggingSlider = slider;
                this.updateSlider(mouseX, slider);
                return sy + 1.0f;
            }
        }
        if (setting instanceof SelectSetting) {
            SelectSetting select = (SelectSetting)setting;
            if (button == 0) {
                oy = sy + 16.0f;
                for (String value : select.getList()) {
                    if (Calculate.isHovered(mouseX, mouseY, sx + 6.0f, oy + 4.0f, sw - 12.0f, 12.0)) {
                        select.setSelected(value);
                        return sy + 1.0f;
                    }
                    oy += 12.0f;
                }
            }
        }
        if (setting instanceof RadioSetting) {
            RadioSetting radio = (RadioSetting)setting;
            if (button == 0) {
                oy = sy + 16.0f;
                for (String value : radio.getOptions()) {
                    if (Calculate.isHovered(mouseX, mouseY, sx + 6.0f, oy + 4.0f, sw - 12.0f, 12.0)) {
                        radio.set(value);
                        return sy + 1.0f;
                    }
                    oy += 12.0f;
                }
            }
        }
        if (setting instanceof MultiSelectSetting) {
            MultiSelectSetting multi = (MultiSelectSetting)setting;
            if (button == 0) {
                oy = sy + 16.0f;
                for (String value : multi.getList()) {
                    if (Calculate.isHovered(mouseX, mouseY, sx + 6.0f, oy + 4.0f, sw - 12.0f, 12.0)) {
                        if (multi.getSelected().contains(value)) {
                            multi.getSelected().remove(value);
                        } else {
                            multi.getSelected().add(value);
                        }
                        return sy + 1.0f;
                    }
                    oy += 12.0f;
                }
            }
        }
        if (setting instanceof BindSetting) {
            BindSetting bind = (BindSetting)setting;
            if (button == 0 && this.isBindSettingHovered(bind, sx, sy, sw, mouseX, mouseY)) {
                BindCaptureHelper.toggle(bind);
                return sy + 1.0f;
            }
        }
        if (setting instanceof TextSetting) {
            TextSetting text = (TextSetting)setting;
            if (button == 0 && Calculate.isHovered(mouseX, mouseY, sx + 7.0f, sy + 16.0f, sw - 14.0f, 10.0)) {
                this.focusedText = text;
                return sy + 1.0f;
            }
        }
        if (setting instanceof ColorSetting) {
            ColorSetting color = (ColorSetting)setting;
            if (button == 0 && Calculate.isHovered(mouseX, mouseY, sx + sw - 18.0f, sy + 5.0f, 9.0, 9.0)) {
                this.openColorWindow(color, (int)mouseX, (int)mouseY);
                return sy + 1.0f;
            }
        }
        if (setting instanceof ButtonSetting) {
            ButtonSetting buttonSetting = (ButtonSetting)setting;
            if (button == 0 && Calculate.isHovered(mouseX, mouseY, sx, sy, sw, 24.0)) {
                if (buttonSetting.getRunnable() != null) {
                    buttonSetting.getRunnable().run();
                }
                return sy + 1.0f;
            }
        }
        if (setting instanceof GroupSetting) {
            GroupSetting group = (GroupSetting)setting;
            if (Calculate.isHovered(mouseX, mouseY, sx, sy, sw, 19.0)) {
                if (button == 0) {
                    if (Calculate.isHovered(mouseX, mouseY, sx + sw - 27.0f, sy + 5.0f, 8.0, 8.0)) {
                        this.openGroupWindow(group, (int)mouseX, (int)mouseY);
                    } else {
                        group.setValue(!group.isValue());
                    }
                } else if (button == 1) {
                    this.openGroupWindow(group, (int)mouseX, (int)mouseY);
                }
                return sy + 1.0f;
            }
        }
        return sy;
    }

    private boolean isBindSettingHovered(BindSetting bind, float sx, float sy, float sw, double mouseX, double mouseY) {
        FontRenderer labelFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        boolean listening = BindCaptureHelper.isCapturing(bind);
        String key = listening ? "..." : BindCodes.getDisplayName(bind.getKey());
        float pillW = ToggleSwitchRenderer.bindPillWidth(key);
        float pillH = 14.0f;
        float pillX = sx + sw - pillW - 7.0f;
        float pillY = ToggleSwitchRenderer.bindPillTrackY(labelFont, bind.getName(), sy, 21.0f);
        return Calculate.isHovered(mouseX, mouseY, pillX, pillY, pillW, pillH) || Calculate.isHovered(mouseX, mouseY, sx, sy, sw, 21.0);
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        this.draggingSlider = null;
        this.activeThemeSlider = null;
        this.activeThemeSliderX = 0.0f;
        this.activeThemeSliderWidth = 0.0f;
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (!Calculate.isHovered(mouseX, mouseY, this.animatedX, this.animatedY, this.width, this.height)) {
            return false;
        }
        if (BindCaptureHelper.isCapturing()) {
            return BindCaptureHelper.handleScroll(amount);
        }
        if (this.selectedModuleComponent != null) {
            this.settingsScrollTarget = class_3532.method_15363((float)(this.settingsScrollTarget + (float)amount * 14.0f), (float)(-this.settingsMaxScroll), (float)0.0f);
        } else {
            this.modulesScrollTarget = class_3532.method_15363((float)(this.modulesScrollTarget + (float)amount * 14.0f), (float)(-this.modulesMaxScroll), (float)0.0f);
        }
        return true;
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (BindCaptureHelper.isCapturing()) {
            return BindCaptureHelper.handleKey(keyCode, scanCode);
        }
        if (this.isSearchHostPanel() && this.selectedModuleComponent == null && this.category != ModuleCategory.CUSTOMIZATION) {
            if ((modifiers & 2) != 0 && keyCode == 67) {
                sharedSearchFocused = true;
                return true;
            }
            if (sharedSearchFocused) {
                if (keyCode == 256) {
                    sharedSearchFocused = false;
                    sharedSearchText = "";
                    sharedSearchSuggestion = "";
                    return true;
                }
                if (keyCode == 257 || keyCode == 335) {
                    ModuleComponentData suggested = this.findSuggestedComponent();
                    if (suggested != null) {
                        this.openModuleSettings(suggested);
                    }
                    sharedSearchFocused = false;
                    return true;
                }
                if (keyCode == 258 && !sharedSearchSuggestion.isBlank()) {
                    sharedSearchText = sharedSearchSuggestion;
                    this.playSearchTypeSound();
                    return true;
                }
                if (keyCode == 259 && !sharedSearchText.isEmpty()) {
                    sharedSearchText = sharedSearchText.substring(0, sharedSearchText.length() - 1);
                    this.playSearchTypeSound();
                    return true;
                }
                if ((modifiers & 2) != 0 && keyCode == 86) {
                    String clipboard = GLFW.glfwGetClipboardString((long)window.getHandle());
                    if (clipboard != null && !clipboard.isBlank()) {
                        String next = sharedSearchText + clipboard.replace("\n", "").replace("\r", "");
                        sharedSearchText = next.length() > 32 ? next.substring(0, 32) : next;
                        this.playSearchTypeSound();
                    }
                    return true;
                }
            }
        }
        if (this.focusedText != null) {
            String value;
            String string = value = this.focusedText.getText() == null ? "" : this.focusedText.getText();
            if (keyCode == 256 || keyCode == 257 || keyCode == 335) {
                this.focusedText = null;
                return true;
            }
            if (keyCode == 259 && !value.isEmpty()) {
                this.focusedText.setText(value.substring(0, value.length() - 1));
                return true;
            }
            if ((modifiers & 2) != 0 && keyCode == 86) {
                String clipboard = GLFW.glfwGetClipboardString((long)window.getHandle());
                if (clipboard != null) {
                    int max = Math.max(0, this.focusedText.getMax());
                    Object next = value + clipboard;
                    if (max > 0 && ((String)next).length() > max) {
                        next = ((String)next).substring(0, max);
                    }
                    this.focusedText.setText((String)next);
                }
                return true;
            }
            return true;
        }
        return false;
    }

    public boolean charTyped(char chr) {
        if (this.isSearchHostPanel() && sharedSearchFocused && this.selectedModuleComponent == null && this.category != ModuleCategory.CUSTOMIZATION && !Character.isISOControl(chr) && sharedSearchText.length() < 32) {
            sharedSearchText = sharedSearchText + chr;
            this.playSearchTypeSound();
            return true;
        }
        if (this.focusedText == null || Character.isISOControl(chr)) {
            return false;
        }
        String value = this.focusedText.getText() == null ? "" : this.focusedText.getText();
        int max = this.focusedText.getMax();
        if (max <= 0 || value.length() < max) {
            this.focusedText.setText(value + chr);
        }
        return true;
    }

    private void updateSlider(double mouseX, SliderSettings setting) {
        Module module;
        if (this.isLiquidGlassSlider(setting)) {
            this.updateThemeSlider(mouseX, setting);
            return;
        }
        Module module2 = this.selectedModuleComponent != null ? this.selectedModuleComponent.module : (module = this.lastSelected != null ? this.lastSelected.module : null);
        if (module == null) {
            return;
        }
        float pageX = this.animatedX + this.width * (1.0f - this.swapping);
        float sy = this.animatedY + 24.0f + 4.0f + 20.0f + 4.0f + this.settingsScroll;
        for (Setting current : module.settings()) {
            if (!current.isVisible()) continue;
            if (current == setting) {
                float barX = pageX + 2.0f + 7.0f;
                float barW = this.width - 4.0f - 14.0f;
                float percent = class_3532.method_15363((float)((float)((mouseX - (double)barX) / (double)barW)), (float)0.0f, (float)1.0f);
                float raw = setting.getMin() + (setting.getMax() - setting.getMin()) * percent;
                float step = setting.getStep() <= 0.0f ? 0.01f : setting.getStep();
                raw = (float)Math.round(raw / step) * step;
                setting.setValue(class_3532.method_15363((float)raw, (float)setting.getMin(), (float)setting.getMax()));
                return;
            }
            sy = this.getNextSettingY(current, sy);
        }
    }

    private float getNextSettingY(Setting setting, float currentY) {
        if (setting instanceof BooleanSetting) {
            return currentY + 21.0f;
        }
        if (setting instanceof SliderSettings) {
            return currentY + 28.0f;
        }
        if (setting instanceof SelectSetting) {
            SelectSetting select = (SelectSetting)setting;
            return currentY + 24.0f + (float)select.getList().size() * 12.0f + 2.0f;
        }
        if (setting instanceof RadioSetting) {
            RadioSetting radio = (RadioSetting)setting;
            return currentY + 24.0f + (float)radio.getOptions().length * 12.0f + 2.0f;
        }
        if (setting instanceof MultiSelectSetting) {
            MultiSelectSetting multi = (MultiSelectSetting)setting;
            return currentY + 24.0f + (float)multi.getList().size() * 12.0f + 2.0f;
        }
        if (setting instanceof TextSetting) {
            return currentY + 32.0f;
        }
        if (setting instanceof ButtonSetting) {
            return currentY + 26.0f;
        }
        return currentY + 21.0f;
    }

    private boolean isLiquidGlassSlider(SliderSettings setting) {
        return setting == ThemeManager.liquidGlassGlobalAlphaMinSetting || setting == ThemeManager.liquidGlassBaseAlphaSetting || setting == ThemeManager.liquidGlassFresnelAlphaSetting || setting == ThemeManager.liquidGlassFresnelMixSetting || setting == ThemeManager.liquidGlassSmoothnessSetting || setting == ThemeManager.liquidGlassDistortStrengthSetting || setting == ThemeManager.liquidGlassCornerSmoothnessSetting;
    }

    private String formatSlider(SliderSettings setting) {
        if (setting.isInteger()) {
            return String.valueOf((int)setting.getValue());
        }
        float step = setting.getStep() <= 0.0f ? 0.01f : setting.getStep();
        int precision = 0;
        String stepText = String.valueOf(step);
        int dot = stepText.indexOf(46);
        if (dot >= 0) {
            for (precision = stepText.length() - dot - 1; precision > 0 && stepText.endsWith("0"); --precision) {
                stepText = stepText.substring(0, stepText.length() - 1);
            }
        }
        String format = "%." + Math.max(1, precision) + "f";
        return String.format(Locale.US, format, Float.valueOf(setting.getValue())).replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    private void openColorWindow(ColorSetting setting, int mouseX, int mouseY) {
        WindowManager manager = this.screen.getLocalWindowManager();
        AbstractWindow existing = null;
        for (AbstractWindow window : manager.getWindows()) {
            ColorWindow colorWindow;
            if (!(window instanceof ColorWindow) || (colorWindow = (ColorWindow)window).getSetting() != setting) continue;
            existing = window;
            break;
        }
        if (existing != null) {
            existing.startCloseAnimation();
            return;
        }
        manager.add(new ColorWindow(setting).position(mouseX - 90, mouseY - 10).size(100.0f, 155.0f).draggable(true));
    }

    private void openGroupWindow(GroupSetting setting, int mouseX, int mouseY) {
        WindowManager manager = this.screen.getLocalWindowManager();
        AbstractWindow existing = null;
        for (AbstractWindow window : manager.getWindows()) {
            GroupWindow groupWindow;
            if (!(window instanceof GroupWindow) || (groupWindow = (GroupWindow)window).getSetting() != setting) continue;
            existing = window;
            break;
        }
        if (existing != null) {
            existing.startCloseAnimation();
            return;
        }
        manager.add(new GroupWindow(setting).position(mouseX + 4, mouseY).size(137.0f, 23.0f).draggable(false));
    }

    private boolean searchCheck(Module module) {
        String search = sharedSearchText.toLowerCase(Locale.ROOT);
        if (search.isBlank()) {
            return false;
        }
        return !module.getVisibleName().toLowerCase(Locale.ROOT).contains(search) && !module.getName().toLowerCase(Locale.ROOT).contains(search) && !module.getName().replace(" ", "").toLowerCase(Locale.ROOT).contains(search);
    }

    private void renderVerticalScrollIndicator(class_332 context, float x, float y, float h, float maxScroll, float scroll) {
        if (maxScroll <= 0.5f) {
            return;
        }
        class_4587 matrices = context.method_51448();
        rectangle.render(ShapeProperties.create(matrices, x, y, 1.5, h).round(1.0f).color(ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 22)).build());
        float ratio = class_3532.method_15363((float)(h / (h + maxScroll)), (float)0.18f, (float)1.0f);
        float thumbH = h * ratio;
        float travel = Math.max(0.0f, h - thumbH);
        float progress = class_3532.method_15363((float)(-scroll / maxScroll), (float)0.0f, (float)1.0f);
        float thumbY = y + travel * progress;
        rectangle.render(ShapeProperties.create(matrices, x, thumbY, 1.5, thumbH).round(1.0f).color(ColorAssist.applyOpacity(ColorAssist.getClientColor(), 150)).build());
    }

    private void updateSearchSuggestion() {
        sharedSearchSuggestion = "";
        if (sharedSearchText.isBlank()) {
            return;
        }
        String q = sharedSearchText.toLowerCase(Locale.ROOT);
        for (ModuleComponentData component : this.moduleComponents) {
            String name = component.module.getVisibleName();
            if (name == null || !name.toLowerCase(Locale.ROOT).startsWith(q)) continue;
            sharedSearchSuggestion = name;
            return;
        }
    }

    private boolean isSearchHostPanel() {
        return this.panelIndex == 0;
    }

    private void playSearchTypeSound() {
        long now = System.currentTimeMillis();
        if (now - this.lastSearchTypeSoundAt < 24L) {
            return;
        }
        this.lastSearchTypeSoundAt = now;
        SoundManager.playSound(SoundManager.SETTING_INTERACT);
    }

    private ModuleComponentData findSuggestedComponent() {
        String q;
        String string = q = sharedSearchText == null ? "" : sharedSearchText.trim().toLowerCase(Locale.ROOT);
        if (!sharedSearchSuggestion.isBlank()) {
            for (ModuleComponentData component : this.moduleComponents) {
                if (!sharedSearchSuggestion.equalsIgnoreCase(component.module.getVisibleName()) && !sharedSearchSuggestion.equalsIgnoreCase(component.module.getName())) continue;
                return component;
            }
        }
        if (q.isBlank()) {
            return this.moduleComponents.isEmpty() ? null : this.moduleComponents.get(0);
        }
        for (ModuleComponentData component : this.moduleComponents) {
            String visible = component.module.getVisibleName().toLowerCase(Locale.ROOT);
            String raw = component.module.getName().toLowerCase(Locale.ROOT);
            if (!visible.contains(q) && !raw.contains(q) && !raw.replace(" ", "").contains(q)) continue;
            return component;
        }
        return null;
    }

    private float getSearchVisibility(Module module) {
        boolean hiddenBySearch = this.searchCheck(module);
        float target = hiddenBySearch ? 0.0f : 1.0f;
        float current = this.moduleSearchVisibility.getOrDefault(module, Float.valueOf(target)).floatValue();
        float next = class_3532.method_16439((float)0.2f, (float)current, (float)target);
        this.moduleSearchVisibility.put(module, Float.valueOf(next));
        return next;
    }

    private float renderCustomization(class_332 context, float sx, float sy, float sw) {
        class_4587 matrices = context.method_51448();
        float startY = sy;
        float sectionGap = 8.0f;
        float sectionPad = 5.0f;
        float rowH = 14.0f;
        float rowStep = 16.0f;
        String[] themes = ThemeManager.getAvailableThemes();
        float themesBlockH = sectionPad * 2.0f + 11.0f + (float)themes.length * rowStep;
        rectangle.render(ShapeProperties.create(matrices, sx, sy, sw, themesBlockH).round(6.0f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), 68)).build());
        Fonts.getSize(10, Fonts.Type.SuisseIntlMedium).drawString(matrices, "Themes", sx + 6.0f, sy + 9.4f, ThemeManager.textColor.getColor());
        float rowY = sy + sectionPad + 11.0f;
        for (String themeName : themes) {
            boolean active = themeName.equalsIgnoreCase(ThemeManager.getCurrentThemeName());
            rectangle.render(ShapeProperties.create(matrices, sx + 4.0f, rowY, sw - 8.0f, rowH).round(4.5f).color(active ? ColorAssist.applyOpacity(ColorAssist.getClientColor(), 96) : ColorAssist.applyOpacity(ThemeManager.BackgroundGui.getColor(), 76)).build());
            Fonts.getSize(10, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, this.beautifyThemeName(themeName), sx + 10.0f, rowY + 8.5f, ThemeManager.textColor.getColor());
            rowY += rowStep;
        }
        ColorSetting[] colors = new ColorSetting[]{ThemeManager.primaryColor, ThemeManager.secondaryColor, ThemeManager.BackgroundGui, ThemeManager.offModuleColor, ThemeManager.BackgroundSettings, ThemeManager.textColor};
        String[] colorNames = new String[]{"Primary", "Secondary", "GUI", "Modules", "Settings", "Text"};
        float colorsBlockH = sectionPad * 2.0f + 11.0f + (float)colors.length * rowStep;
        rectangle.render(ShapeProperties.create(matrices, sx, sy += themesBlockH + sectionGap, sw, colorsBlockH).round(6.0f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), 68)).build());
        Fonts.getSize(10, Fonts.Type.SuisseIntlMedium).drawString(matrices, "Colors", sx + 6.0f, sy + 9.4f, ThemeManager.textColor.getColor());
        rowY = sy + sectionPad + 11.0f;
        for (int i = 0; i < colors.length; ++i) {
            rowY = this.renderThemeColorRow(context, colorNames[i], colors[i], sx + 4.0f, rowY, sw - 8.0f);
        }
        sy += colorsBlockH + sectionGap;
        if (ThemeManager.isLiquidGlassTheme()) {
            float lgHeadH = sectionPad * 2.0f + 11.0f + rowStep;
            float slidersH = 196.0f;
            float resetH = rowStep;
            float lgBlockH = lgHeadH + slidersH + resetH;
            rectangle.render(ShapeProperties.create(matrices, sx, sy, sw, lgBlockH).round(6.0f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), 68)).build());
            Fonts.getSize(10, Fonts.Type.SuisseIntlMedium).drawString(matrices, "Liquid Glass", sx + 6.0f, sy + 9.4f, ThemeManager.textColor.getColor());
            String modeText = ThemeManager.isLiquidGlassCustomMode() ? "Mode: Custom" : "Mode: Default";
            rectangle.render(ShapeProperties.create(matrices, sx + 4.0f, sy + sectionPad + 11.0f, sw - 8.0f, rowH).round(4.5f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundGui.getColor(), 84)).build());
            Fonts.getSize(10, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, modeText, sx + 10.0f, sy + sectionPad + 11.0f + 8.5f, ThemeManager.textColor.getColor());
            sy += lgHeadH;
            sy = this.renderSlider(context, ThemeManager.liquidGlassGlobalAlphaMinSetting, sx + 4.0f, sy, sw - 8.0f);
            sy = this.renderSlider(context, ThemeManager.liquidGlassBaseAlphaSetting, sx + 4.0f, sy, sw - 8.0f);
            sy = this.renderSlider(context, ThemeManager.liquidGlassFresnelAlphaSetting, sx + 4.0f, sy, sw - 8.0f);
            sy = this.renderSlider(context, ThemeManager.liquidGlassFresnelMixSetting, sx + 4.0f, sy, sw - 8.0f);
            sy = this.renderSlider(context, ThemeManager.liquidGlassSmoothnessSetting, sx + 4.0f, sy, sw - 8.0f);
            sy = this.renderSlider(context, ThemeManager.liquidGlassDistortStrengthSetting, sx + 4.0f, sy, sw - 8.0f);
            sy = this.renderSlider(context, ThemeManager.liquidGlassCornerSmoothnessSetting, sx + 4.0f, sy, sw - 8.0f);
            rectangle.render(ShapeProperties.create(matrices, sx + 4.0f, sy, sw - 8.0f, rowH).round(4.5f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundGui.getColor(), 86)).build());
            Fonts.getSize(10, Fonts.Type.ROCKSTAR_MEDIUM).drawCenteredString(matrices, "Reset Liquid Glass", sx + sw / 2.0f, sy + 8.7f, ThemeManager.textColor.getColor());
            sy += rowStep;
        }
        return sy - startY;
    }

    private float renderThemeColorRow(class_332 context, String name, ColorSetting setting, float sx, float sy, float sw) {
        class_4587 matrices = context.method_51448();
        rectangle.render(ShapeProperties.create(matrices, sx, sy, sw, 14.0).round(4.0f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), 72)).build());
        Fonts.getSize(10, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, name, sx + 7.0f, sy + 8.7f, ThemeManager.textColor.getColor());
        rectangle.render(ShapeProperties.create(matrices, sx + sw - 17.0f, sy + 3.0f, 8.0, 8.0).round(3.0f).color(setting.getColor()).build());
        return sy + 16.0f;
    }

    private void openModuleSettings(ModuleComponentData component) {
        if (component.module.settings().isEmpty()) {
            long now = System.currentTimeMillis();
            component.shakeUntilMs = now + 220L;
            component.noSettingsFlashUntilMs = now + 350L;
            SoundManager.playSound(SoundManager.CRITICAL);
            return;
        }
        this.selectedModuleComponent = component;
        this.lastSelected = component;
        this.settingsScroll = 0.0f;
        this.settingsScrollTarget = 0.0f;
    }

    private boolean handleCustomizationClick(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return false;
        }
        float sx = this.animatedX + 4.0f;
        float sy = this.animatedY + 24.0f + 4.0f + this.modulesScroll + 2.0f;
        float sw = this.width - 8.0f;
        float sectionGap = 8.0f;
        float sectionPad = 5.0f;
        float rowH = 14.0f;
        float rowStep = 16.0f;
        String[] themes = ThemeManager.getAvailableThemes();
        float themesBlockH = sectionPad * 2.0f + 11.0f + (float)themes.length * rowStep;
        float rowY = sy + sectionPad + 11.0f;
        for (String themeName : themes) {
            if (Calculate.isHovered(mouseX, mouseY, sx + 4.0f, rowY, sw - 8.0f, rowH)) {
                ThemeManager.loadTheme(themeName);
                return true;
            }
            rowY += rowStep;
        }
        ColorSetting[] colors = new ColorSetting[]{ThemeManager.primaryColor, ThemeManager.secondaryColor, ThemeManager.BackgroundGui, ThemeManager.offModuleColor, ThemeManager.BackgroundSettings, ThemeManager.textColor};
        float colorsBlockH = sectionPad * 2.0f + 11.0f + (float)colors.length * rowStep;
        rowY = (sy += themesBlockH + sectionGap) + sectionPad + 11.0f;
        for (ColorSetting colorSetting : colors) {
            if (Calculate.isHovered(mouseX, mouseY, sx + sw - 21.0f, rowY + 3.0f, 8.0, 8.0)) {
                this.openColorWindow(colorSetting, (int)mouseX, (int)mouseY);
                return true;
            }
            rowY += rowStep;
        }
        sy += colorsBlockH + sectionGap;
        if (ThemeManager.isLiquidGlassTheme()) {
            float lgHeadH = sectionPad * 2.0f + 11.0f + rowStep;
            float modeY = sy + sectionPad + 11.0f;
            if (Calculate.isHovered(mouseX, mouseY, sx + 4.0f, modeY, sw - 8.0f, rowH)) {
                ThemeManager.setLiquidGlassCustomMode(!ThemeManager.isLiquidGlassCustomMode());
                return true;
            }
            if (this.handleThemeSliderClick(ThemeManager.liquidGlassGlobalAlphaMinSetting, sx + 4.0f, sy += lgHeadH, sw - 8.0f, mouseX, mouseY)) {
                return true;
            }
            if (this.handleThemeSliderClick(ThemeManager.liquidGlassBaseAlphaSetting, sx + 4.0f, sy += 28.0f, sw - 8.0f, mouseX, mouseY)) {
                return true;
            }
            if (this.handleThemeSliderClick(ThemeManager.liquidGlassFresnelAlphaSetting, sx + 4.0f, sy += 28.0f, sw - 8.0f, mouseX, mouseY)) {
                return true;
            }
            if (this.handleThemeSliderClick(ThemeManager.liquidGlassFresnelMixSetting, sx + 4.0f, sy += 28.0f, sw - 8.0f, mouseX, mouseY)) {
                return true;
            }
            if (this.handleThemeSliderClick(ThemeManager.liquidGlassSmoothnessSetting, sx + 4.0f, sy += 28.0f, sw - 8.0f, mouseX, mouseY)) {
                return true;
            }
            if (this.handleThemeSliderClick(ThemeManager.liquidGlassDistortStrengthSetting, sx + 4.0f, sy += 28.0f, sw - 8.0f, mouseX, mouseY)) {
                return true;
            }
            if (this.handleThemeSliderClick(ThemeManager.liquidGlassCornerSmoothnessSetting, sx + 4.0f, sy += 28.0f, sw - 8.0f, mouseX, mouseY)) {
                return true;
            }
            if (Calculate.isHovered(mouseX, mouseY, sx + 4.0f, sy += 28.0f, sw - 8.0f, rowH)) {
                ThemeManager.resetLiquidGlassDefaults();
                return true;
            }
        }
        return false;
    }

    private boolean handleThemeSliderClick(SliderSettings setting, float sx, float sy, float sw, double mouseX, double mouseY) {
        if (!Calculate.isHovered(mouseX, mouseY, sx, sy, sw, 24.0)) {
            return false;
        }
        this.draggingSlider = setting;
        this.activeThemeSlider = setting.getName();
        this.activeThemeSliderX = sx + 7.0f;
        this.activeThemeSliderWidth = sw - 14.0f;
        this.updateThemeSlider(mouseX, setting);
        return true;
    }

    private void updateCustomizationSlider(double mouseX) {
        if (this.activeThemeSlider == null) {
            return;
        }
        if (this.draggingSlider != null && this.isLiquidGlassSlider(this.draggingSlider)) {
            this.updateThemeSlider(mouseX, this.draggingSlider);
        }
    }

    private void updateThemeSlider(double mouseX, SliderSettings setting) {
        if (this.activeThemeSliderWidth <= 0.0f) {
            return;
        }
        float percent = class_3532.method_15363((float)((float)((mouseX - (double)this.activeThemeSliderX) / (double)this.activeThemeSliderWidth)), (float)0.0f, (float)1.0f);
        float raw = setting.getMin() + (setting.getMax() - setting.getMin()) * percent;
        float step = setting.getStep() <= 0.0f ? 0.01f : setting.getStep();
        raw = (float)Math.round(raw / step) * step;
        setting.setValue(class_3532.method_15363((float)raw, (float)setting.getMin(), (float)setting.getMax()));
    }

    private String beautifyThemeName(String themeName) {
        return switch (themeName.toLowerCase(Locale.ROOT)) {
            case "default_dark" -> "Dark";
            case "default_light" -> "Light";
            case "default_liquidglass" -> "Liquid Glass";
            default -> themeName.replace('_', ' ');
        };
    }

    private int getEnabledModuleTextColor() {
        int b;
        int g;
        int accent = ColorAssist.getClientColor();
        int r = ColorAssist.getRed(accent);
        float luminance = (0.2126f * (float)r + 0.7152f * (float)(g = ColorAssist.getGreen(accent)) + 0.0722f * (float)(b = ColorAssist.getBlue(accent))) / 255.0f;
        if (luminance > 0.92f) {
            return -16777216;
        }
        if (luminance < 0.1f) {
            return -1;
        }
        return accent;
    }

    private float animateOption(Map<String, Float> store, String key, float target) {
        float current = store.getOrDefault(key, Float.valueOf(0.0f)).floatValue();
        float next = class_3532.method_16439((float)0.24f, (float)current, (float)target);
        store.put(key, Float.valueOf(next));
        return next;
    }

    private int blendArgb(int from, int to, float progress) {
        float t = class_3532.method_15363((float)progress, (float)0.0f, (float)1.0f);
        int a = Math.round((float)(from >>> 24 & 0xFF) + (float)((to >>> 24 & 0xFF) - (from >>> 24 & 0xFF)) * t);
        int r = Math.round((float)(from >>> 16 & 0xFF) + (float)((to >>> 16 & 0xFF) - (from >>> 16 & 0xFF)) * t);
        int g = Math.round((float)(from >>> 8 & 0xFF) + (float)((to >>> 8 & 0xFF) - (from >>> 8 & 0xFF)) * t);
        int b = Math.round((float)(from & 0xFF) + (float)((to & 0xFF) - (from & 0xFF)) * t);
        return a << 24 | r << 16 | g << 8 | b;
    }

    static {
        sharedSearchText = "";
        sharedSearchSuggestion = "";
    }

    private static class ModuleComponentData {
        private final Module module;
        private float enableAnim;
        private float hoverAnim;
        private long shakeUntilMs;
        private long noSettingsFlashUntilMs;

        private ModuleComponentData(Module module) {
            this.module = module;
            this.enableAnim = module.isState() ? 1.0f : 0.0f;
            this.hoverAnim = 0.0f;
            this.shakeUntilMs = 0L;
            this.noSettingsFlashUntilMs = 0L;
        }
    }
}
