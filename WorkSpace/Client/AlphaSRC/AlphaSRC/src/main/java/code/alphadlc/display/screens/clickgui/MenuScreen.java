/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1068
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4050
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_745
 *  net.minecraft.class_898
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector4d
 *  org.lwjgl.glfw.GLFW
 */
package code.alphadlc.display.screens.clickgui;

import code.alphadlc.AlphaDLC;
import code.alphadlc.common.animation.Easy.Direction;
import code.alphadlc.common.animation.Easy.EaseBackIn;
import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.display.screens.clickgui.components.implement.customization.CustomizationComponent;
import code.alphadlc.display.screens.clickgui.components.implement.module.ModuleComponent;
import code.alphadlc.display.screens.clickgui.components.implement.module.ModuleDescriptions;
import code.alphadlc.display.screens.clickgui.components.implement.other.ToggleSwitchRenderer;
import code.alphadlc.display.screens.clickgui.components.implement.panel.PanelManager;
import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.BindComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.TextComponent;
import code.alphadlc.features.impl.misc.Panic;
import code.alphadlc.features.impl.render.ClickGui;
import code.alphadlc.features.impl.render.Esp;
import code.alphadlc.features.impl.render.EspSkeleton;
import code.alphadlc.features.impl.render.NameTags;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.Setting;
import code.alphadlc.features.module.setting.SettingComponentAdder;
import code.alphadlc.utils.client.bind.BindCaptureHelper;
import code.alphadlc.utils.client.bind.BindCodes;
import code.alphadlc.utils.client.chat.StringHelper;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.gif.GifTextureRenderer;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.BackgroundIcons;
import code.alphadlc.utils.display.render.HudShine;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.render.shape.implement.Rectangle;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import net.minecraft.class_1068;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4050;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_745;
import net.minecraft.class_898;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector4d;
import org.lwjgl.glfw.GLFW;

public class MenuScreen
extends class_437
implements QuickImports {
    private static final class_2960 WAIT_GIF_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"gifs/wait.gif");
    private static MenuScreen INSTANCE;
    private static final List<ClickGuiInput> activeClickGuiInputs;
    private final List<AbstractComponent> components = new ArrayList<AbstractComponent>();
    private final PanelManager panelManager = new PanelManager();
    private final GifTextureRenderer waitGifRenderer = new GifTextureRenderer(WAIT_GIF_TEXTURE);
    public final EaseBackIn animation = new EaseBackIn(200, 1.0, 1.5f);
    public ModuleCategory category = ModuleCategory.COMBAT;
    public int x;
    public int y;
    public int width;
    public int height;
    private final Random random = new Random();
    private static final int MAX_SNOWFLAKES = 300;
    private static final float SPAWN_RATE = 1.0f;
    private boolean resizeModeActive = false;
    private long lastClickTime = 0L;
    private static final long DOUBLE_CLICK_DELAY_MS = 300L;
    private float scaleFactor = 1.0f;
    private static final float GUI_WIDTH = 560.0f;
    private static final float GUI_HEIGHT = 320.0f;
    private static final float SIDEBAR_WIDTH = 150.0f;
    private static final float HEADER_HEIGHT = 28.0f;
    private static final float MODULE_HEIGHT = 20.0f;
    private static final float ESP_SECTION_HEADER_HEIGHT = 20.0f;
    private static final float ESP_SECTION_GAP = 8.0f;
    private static final float ESP_SETTINGS_TOP_PAD = 6.0f;
    private static final float PADDING = 10.0f;
    private static final float CONTENT_GAP = 12.0f;
    private static final float COLUMN_WIDTH = 130.0f;
    private static final float COLUMN_SPACING = 2.0f;
    private static final int MAX_VISIBLE_MODULES = 9;
    private Map<ModuleCategory, CategoryColumn> categoryColumns = new LinkedHashMap<ModuleCategory, CategoryColumn>();
    private boolean initialized = false;
    private String hoveredModuleName = null;
    private Module bindingModule = null;
    private int firstBindKey = -1;
    private Module selectedModule = null;
    private Module hoveredModule = null;
    private boolean tooltipVisible = false;
    private float tooltipX = 0.0f;
    private float tooltipY = 0.0f;
    private float tooltipW = 0.0f;
    private float tooltipH = 0.0f;
    private String searchText = "";
    private boolean searchFieldFocused = false;
    private float searchTextScroll = 0.0f;
    private int searchCursor = 0;
    private int searchSelectionStart = -1;
    private int searchSelectionEnd = -1;
    private static final int SEARCH_MAX_LENGTH = 40;
    private static final float SEARCH_TEXT_PADDING = 8.0f;
    private float searchSuggestionsAnim = 0.0f;
    private int searchSuggestionIndex = -1;
    private final List<String> searchSuggestions = new ArrayList<String>();
    private float searchSuggestionsX = 0.0f;
    private float searchSuggestionsY = 0.0f;
    private float searchSuggestionsW = 0.0f;
    private float searchSuggestionsH = 0.0f;
    private float espPreviewX = 0.0f;
    private float espPreviewY = 0.0f;
    private float espPreviewW = 0.0f;
    private float espPreviewH = 0.0f;
    private boolean espPreviewDragging = false;
    private float espPreviewYaw = 180.0f;
    private float espPreviewPitch = 15.0f;
    private float espDragStartX = 0.0f;
    private float espDragStartY = 0.0f;
    private float espDragStartYaw = 180.0f;
    private float espDragStartPitch = 15.0f;
    private class_1309 espPreviewEntity = null;
    private final UUID espPreviewUuid = UUID.fromString("7e6b0ad2-6d6b-4b8d-9b6c-1b1c0b1c0b1c");
    private final String espPreviewName = "Enemy";
    private final Map<Module, List<AbstractSettingComponent>> espSettingsCache = new HashMap<Module, List<AbstractSettingComponent>>();
    private final Map<Module, Integer> espSettingsCacheVersion = new HashMap<Module, Integer>();
    private final EspSectionLayout espSectionLayout = new EspSectionLayout();
    private final EspSectionLayout skeletonSectionLayout = new EspSectionLayout();
    private final EspSectionLayout nameTagsSectionLayout = new EspSectionLayout();
    private boolean espSettingsLayoutReady;
    private Map<Module, List<AbstractSettingComponent>> expandedModules = new HashMap<Module, List<AbstractSettingComponent>>();
    private Map<Module, Float> expandAnimations = new HashMap<Module, Float>();
    private float settingsScrollOffset = 0.0f;
    private float settingsTargetScroll = 0.0f;
    private float settingsMaxScroll = 0.0f;
    private float settingsPanelAnim = 0.0f;
    private ModuleCategory lastCategory = ModuleCategory.COMBAT;
    private float categoryFade = 1.0f;
    private float categorySlideOffset = 0.0f;
    private float categorySlideDirection = 1.0f;
    private final Map<ModuleCategory, Float> categorySelectAnimations = new HashMap<ModuleCategory, Float>();
    private Map<Module, Float> moduleEnabledAnimations = new HashMap<Module, Float>();
    private final Map<Module, Float> moduleHoverAnimations = new HashMap<Module, Float>();
    private final Map<Module, Float> moduleSelectAnimations = new HashMap<Module, Float>();
    private final Map<Module, Long> moduleNoSettingsShakeUntil = new HashMap<Module, Long>();
    private float sidebarSearchFocusAnim = 0.0f;
    private Map<ModuleCategory, Float> columnSlideAnimations = new HashMap<ModuleCategory, Float>();
    private Map<ModuleCategory, Long> columnSlideDelays = new HashMap<ModuleCategory, Long>();
    private long openTime = 0L;
    private CustomizationComponent customizationComponent = new CustomizationComponent();
    private SettingComponentAdder settingComponentAdder = new SettingComponentAdder();
    private int lastMouseX = 0;
    private int lastMouseY = 0;
    private String currentHoveredModule = null;
    private ModuleComponent currentHoveredComponent = null;
    private float lastModuleX = 0.0f;
    private float lastModuleY = 0.0f;
    private float lastModuleWidth = 0.0f;
    private Module displayedSettingsModule = null;
    private Module settingsPanelClosingModule = null;
    private static final float SETTINGS_PANEL_SLIDE = 72.0f;
    private Module settingsMorphModule = null;
    private float settingsMorphOriginX = 0.0f;
    private float settingsMorphOriginY = 0.0f;
    private float settingsMorphOriginW = 0.0f;
    private float settingsMorphOriginH = 20.0f;
    private boolean blurMaskFailed = false;
    private String lastSearchText = "";
    private ModuleFilterKey cachedModuleFilterKey = null;
    private List<Module> cachedFilteredModules = List.of();
    private static final float MODULE_LIST_CLIP_PAD = 2.0f;

    public static MenuScreen getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MenuScreen();
        }
        return INSTANCE;
    }

    public static void activateClickGuiInput(ClickGuiInput input) {
        for (ClickGuiInput activeInput : activeClickGuiInputs) {
            if (activeInput == input || !activeInput.isTyping()) continue;
            activeInput.setTyping(false);
        }
        input.setTyping(true);
        if (!activeClickGuiInputs.contains(input)) {
            activeClickGuiInputs.add(input);
        }
    }

    public static void clearAllClickGuiInputs() {
        for (ClickGuiInput input : activeClickGuiInputs) {
            input.setTyping(false);
            if (!(input instanceof ClearableClickGuiInput)) continue;
            ClearableClickGuiInput clearable = (ClearableClickGuiInput)input;
            clearable.clearText();
        }
        activeClickGuiInputs.clear();
    }

    public static boolean hasActiveClickGuiInputs() {
        return activeClickGuiInputs.stream().anyMatch(ClickGuiInput::isTyping);
    }

    public void initialize() {
        if (this.initialized) {
            return;
        }
        if (this.category == ModuleCategory.CONFIGS) {
            this.category = ModuleCategory.COMBAT;
        }
        List<ModuleCategory> visibleCategories = this.getVisibleCategories();
        this.categoryColumns.clear();
        for (ModuleCategory category : visibleCategories) {
            this.categoryColumns.put(category, new CategoryColumn(category, 0.0f, 0.0f));
        }
        this.initialized = true;
    }

    public MenuScreen() {
        super(class_2561.method_30163((String)"MenuScreen"));
    }

    public void method_25393() {
        super.method_25393();
        if (this.animation.finished(Direction.BACKWARDS)) {
            TextComponent.typing = false;
            MenuScreen.clearAllClickGuiInputs();
            if (this.field_22787 != null) {
                this.field_22787.method_1507(null);
            }
        }
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        if (!this.initialized) {
            this.initialize();
        }
        if (ThemeManager.isLiquidGlassTheme()) {
            KawaseBlur.INSTANCE.render(context.method_51448(), 1, 0.0f);
        }
        this.updateColumnPositions();
        this.lastMouseX = mouseX;
        this.lastMouseY = mouseY;
        rectangle.render(ShapeProperties.create(context.method_51448(), 0.0, 0.0, window.getScaledWidth(), window.getScaledHeight()).color(Calculate.applyOpacity(-16777216, 100.0f * this.getScaleAnimation())).build());
        float centerX = (float)window.getScaledWidth() / 2.0f;
        float centerY = (float)window.getScaledHeight() / 2.0f;
        float scale = this.getScaleAnimation() * this.scaleFactor;
        double transformedMouseX = ((float)mouseX - centerX) / scale + centerX;
        double transformedMouseY = ((float)mouseY - centerY) / scale + centerY;
        context.method_51448().method_22903();
        context.method_51448().method_46416(centerX, centerY, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_51448().method_46416(-centerX, -centerY, 0.0f);
        Calculate.setAlpha(this.getScaleAnimation(), () -> this.renderSidebarLayout(context, (float)transformedMouseX, (float)transformedMouseY, delta));
        this.drawModuleTooltip(context);
        context.method_51448().method_22909();
        if (this.bindingModule != null) {
            rectangle.render(ShapeProperties.create(context.method_51448(), 0.0, 0.0, window.getScaledWidth(), window.getScaledHeight()).color(Calculate.applyOpacity(-16777216, (int)(128.0f * this.getScaleAnimation()))).build());
            this.renderBindingPopup(context, this.getScaleAnimation());
        }
    }

    private void updateColumnPositions() {
        List<ModuleCategory> visibleCategories = this.getVisibleCategories();
        float totalWidth = (float)visibleCategories.size() * 130.0f + (float)(visibleCategories.size() - 1) * 2.0f;
        float startX = ((float)window.getScaledWidth() - totalWidth) / 2.0f;
        float startY = (float)(window.getScaledHeight() - 300) / 2.0f;
        if (this.categoryColumns.isEmpty()) {
            for (int i = 0; i < visibleCategories.size(); ++i) {
                float x = startX + (float)i * 132.0f;
                this.categoryColumns.put(visibleCategories.get(i), new CategoryColumn(visibleCategories.get(i), x, startY));
            }
        } else {
            this.categoryColumns.keySet().removeIf(cat -> !visibleCategories.contains(cat));
            for (ModuleCategory category : visibleCategories) {
                if (this.categoryColumns.containsKey((Object)category)) continue;
                this.categoryColumns.put(category, new CategoryColumn(category, 0.0f, startY));
            }
            int i = 0;
            for (ModuleCategory category : visibleCategories) {
                CategoryColumn column = this.categoryColumns.get((Object)category);
                if (column == null) continue;
                column.x = startX + (float)i * 132.0f;
                column.y = startY;
                ++i;
            }
        }
    }

    private void renderColumns(class_332 context, float mouseX, float mouseY, float delta) {
        class_4587 matrices = context.method_51448();
        float progress = this.getScaleAnimation();
        this.hoveredModuleName = null;
        for (CategoryColumn column : this.categoryColumns.values()) {
            if (!this.getVisibleCategories().contains((Object)column.category)) continue;
            this.renderColumn(context, column, mouseX, mouseY, progress);
        }
        this.renderSearchField(context, mouseX, mouseY, progress);
        windowManager.render(context, (int)mouseX, (int)mouseY, delta);
    }

    private void renderSidebarLayout(class_332 context, float mouseX, float mouseY, float delta) {
        class_4587 matrices = context.method_51448();
        GuiLayout layout = this.getLayout();
        int baseColor = ThemeManager.BackgroundGui.getColor();
        int bodyColor = Calculate.applyOpacity(baseColor, 230.0f);
        int sidebarColor = Calculate.applyOpacity(baseColor, 205.0f);
        ClickGui clickGui = ClickGui.getInstance();
        if (ThemeManager.isLiquidGlassTheme() || clickGui != null && clickGui.shouldRenderBackgroundBlur()) {
            this.drawBlurMaskSafe(matrices, layout.x, layout.y, layout.width, layout.height, 16.0f);
        }
        rectangle.render(ShapeProperties.create(matrices, layout.x, layout.y, layout.width, layout.height).round(8.0f).color(bodyColor).build());
        rectangle.render(ShapeProperties.create(matrices, layout.x, layout.y, layout.sidebarWidth, layout.height).round(8.0f).color(sidebarColor).build());
        rectangle.render(ShapeProperties.create(matrices, layout.x, layout.y, layout.sidebarWidth, layout.height).round(8.0f).color(Calculate.applyOpacity(-16777216, 35.0f)).build());
        this.renderSidebar(context, layout, mouseX, mouseY);
        this.renderContent(context, layout, mouseX, mouseY, delta);
        windowManager.render(context, (int)mouseX, (int)mouseY, delta);
        if (clickGui == null || clickGui.shouldRenderShine()) {
            float shineSize = clickGui != null ? clickGui.getShineSize() : 1.0f;
            float shineSpeed = clickGui != null ? clickGui.getShineSpeed() : 1.0f;
            float shineBrightness = clickGui != null ? clickGui.getShineBrightness() : 0.42f;
            HudShine.render(matrices, layout.x, layout.y, layout.width, layout.height, 8.0f, shineSize, shineSpeed, shineBrightness);
        }
    }

    private void renderSidebar(class_332 context, GuiLayout layout, float mouseX, float mouseY) {
        class_4587 matrices = context.method_51448();
        List<ModuleCategory> categories = this.getVisibleCategories();
        float brandCenterX = layout.x + layout.sidebarWidth / 2.0f;
        Fonts.getSize(24, Fonts.Type.ROCKSTAR_MEDIUM).drawCenteredBoth(matrices, "AlphaDLC", brandCenterX, layout.y + 12.0f, 26.0f, ThemeManager.textColor.getColor());
        this.renderSearchField(context, layout, mouseX, mouseY);
        float itemY = layout.sidebarSearchY + layout.sidebarSearchHeight + 12.0f;
        float itemHeight = 22.0f;
        float iconX = layout.x + 16.0f;
        float textX = layout.x + 34.0f;
        for (ModuleCategory category : categories) {
            boolean selected = category == this.category;
            boolean hovered = Calculate.isHovered(mouseX, mouseY, layout.x + 8.0f, itemY, layout.sidebarWidth - 16.0f, itemHeight);
            float anim = this.categorySelectAnimations.getOrDefault((Object)category, Float.valueOf(0.0f)).floatValue();
            float target = selected ? 1.0f : 0.0f;
            anim = Calculate.interpolateSmooth(6.0, anim, target);
            this.categorySelectAnimations.put(category, Float.valueOf(anim));
            float mix = Math.min(1.0f, anim + (hovered ? 0.35f : 0.0f));
            if (selected || hovered) {
                int bg = Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 70 + (int)(mix * 110.0f));
                rectangle.render(ShapeProperties.create(matrices, layout.x + 8.0f, itemY, layout.sidebarWidth - 16.0f, itemHeight).round(5.0f).color(bg).build());
                if (selected) {
                    rectangle.render(ShapeProperties.create(matrices, layout.x + 10.0f, itemY + 3.0f, 2.0, itemHeight - 6.0f).round(2.0f).color(ColorAssist.getClientColor()).build());
                }
            }
            int iconColor = selected ? ThemeManager.textColor.getColor() : Calculate.applyOpacity(ThemeManager.textColor.getColor(), 140.0f);
            int textColor = selected ? ThemeManager.textColor.getColor() : Calculate.applyOpacity(ThemeManager.textColor.getColor(), 130.0f);
            String label = this.getCategoryLabel(category);
            this.drawCategorySidebarIcon(context, category, iconX, itemY, itemHeight, iconColor);
            Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM).drawVerticallyCentered(matrices, label, textX, itemY, itemHeight, textColor);
            itemY += itemHeight + 4.0f;
        }
        float cardWidth = layout.sidebarWidth - 16.0f;
        float cardHeight = 28.0f;
        float cardX = layout.x + 8.0f;
        float cardY = layout.y + layout.height - cardHeight - 8.0f;
        rectangle.render(ShapeProperties.create(matrices, cardX, cardY, cardWidth, cardHeight).round(6.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 120.0f)).build());
        float avatarSize = 16.0f;
        float avatarX = cardX + 6.0f;
        float avatarY = cardY + (cardHeight - avatarSize) / 2.0f;
        class_2960 skinTexture = null;
        if (MenuScreen.mc.field_1724 != null) {
            skinTexture = MenuScreen.mc.field_1724.method_52814().comp_1626();
        }
        if (skinTexture == null) {
            skinTexture = class_1068.method_4648((UUID)UUID.randomUUID()).comp_1626();
        }
        Render2D.drawTexture(context, skinTexture, avatarX, avatarY, avatarSize, 8.0f, 8, 8, 64, Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 120.0f), -1);
        String username = MenuScreen.mc.field_1724 != null ? MenuScreen.mc.field_1724.method_5477().getString() : "Player";
        float nameX = avatarX + avatarSize + 6.0f;
        float nameY = cardY + 3.0f;
        Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, username, nameX, nameY + 6.0f, ThemeManager.textColor.getColor());
        Fonts.getSize(10, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, "FREE", nameX, nameY + 14.0f, Calculate.applyOpacity(ThemeManager.textColor.getColor(), 120.0f));
    }

    private void renderContent(class_332 context, GuiLayout layout, float mouseX, float mouseY, float delta) {
        Runnable contentRenderer;
        this.renderHeaderBar(context, layout, mouseX, mouseY);
        if (this.category != this.lastCategory) {
            int previousIndex = this.getCategoryOrderIndex(this.lastCategory);
            int currentIndex = this.getCategoryOrderIndex(this.category);
            this.categorySlideDirection = currentIndex >= previousIndex ? 1.0f : -1.0f;
            this.categorySlideOffset = 16.0f;
            this.categoryFade = 0.0f;
            this.lastCategory = this.category;
            this.tooltipVisible = false;
            this.hoveredModule = null;
            this.espSettingsLayoutReady = false;
        }
        this.categoryFade = Calculate.interpolateSmooth(6.0, this.categoryFade, 1.0f);
        this.categorySlideOffset = Calculate.interpolateSmooth(7.0, this.categorySlideOffset, 0.0f);
        if (this.category == ModuleCategory.CUSTOMIZATION) {
            this.tooltipVisible = false;
            this.hoveredModule = null;
            contentRenderer = () -> this.renderCustomizationContent(context, layout, mouseX, mouseY);
        } else {
            contentRenderer = this.category == ModuleCategory.ESP ? () -> this.renderEspContent(context, layout, mouseX, mouseY, delta) : () -> {
                this.renderModulesContent(context, layout, mouseX, mouseY);
                this.renderSettingsPanel(context, layout, mouseX, mouseY);
            };
        }
        class_4587 matrices = context.method_51448();
        matrices.method_22903();
        matrices.method_46416(this.categorySlideDirection * this.categorySlideOffset, 0.0f, 0.0f);
        float clipX = layout.contentX + 10.0f;
        float clipY = layout.moduleListY;
        float clipW = Math.max(0.0f, layout.contentWidth - 20.0f);
        float clipH = layout.moduleListHeight;
        AlphaDLC.getInstance().getScissorManager().push(matrices.method_23760().method_23761(), clipX, clipY - 2.0f, clipW, clipH + 4.0f);
        Calculate.setAlpha(this.categoryFade, contentRenderer);
        AlphaDLC.getInstance().getScissorManager().pop();
        matrices.method_22909();
    }

    private void renderHeaderBar(class_332 context, GuiLayout layout, float mouseX, float mouseY) {
        float iconDrawY;
        float iconTextH;
        class_4587 matrices = context.method_51448();
        rectangle.render(ShapeProperties.create(matrices, layout.headerX, layout.headerY, layout.headerWidth, layout.headerHeight).round(6.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 210.0f)).build());
        String title = this.getCategoryLabel(this.category);
        String icon = this.getCategoryIcon(this.category);
        FontRenderer iconFont = Fonts.getSize(16, Fonts.Type.ALPHADLC_ICONS);
        FontRenderer titleFont = Fonts.getSize(15, Fonts.Type.ROCKSTAR_MEDIUM);
        float iconSize = this.getCategoryIconWidth(this.category, iconFont, icon);
        float textWidth = titleFont.getStringWidth(title);
        float badgeWidth = iconSize + textWidth + 18.0f;
        float badgeX = layout.headerX + 10.0f;
        float badgeH = layout.headerHeight - 6.0f;
        float badgeY = layout.headerY + (layout.headerHeight - badgeH) / 2.0f;
        rectangle.render(ShapeProperties.create(matrices, badgeX, badgeY, badgeWidth, badgeH).round(6.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 140.0f)).build());
        float f = iconTextH = this.category == ModuleCategory.ESP ? 9.0f : Math.min(iconFont.getStringHeight(icon), badgeH);
        if (this.category == ModuleCategory.ESP) {
            iconDrawY = badgeY + (badgeH - 9.0f) / 2.0f;
            BackgroundIcons.drawEspCategory(context, BackgroundIcons.ESP, badgeX + 6.0f, iconDrawY, ThemeManager.textColor.getColor());
        } else {
            iconDrawY = badgeY + (badgeH - iconTextH) / 2.0f;
            iconFont.drawString(matrices, icon, badgeX + 6.0f, iconDrawY, ThemeManager.textColor.getColor());
        }
        float titleDrawY = this.category == ModuleCategory.ESP ? iconDrawY + (9.0f - titleFont.getStringHeight(title)) / 2.0f : titleFont.getCenteredY(badgeY, badgeH, title);
        titleFont.drawString(matrices, title, badgeX + 6.0f + iconSize + 6.0f, titleDrawY, ThemeManager.textColor.getColor());
        if (this.category != ModuleCategory.CUSTOMIZATION) {
            List<Module> categoryModules = AlphaDLC.getInstance().getModuleRepository().modules().stream().filter(m -> m.getCategory() == this.category).toList();
            long enabled = categoryModules.stream().filter(Module::isState).count();
            String countText = enabled + "/" + categoryModules.size();
            FontRenderer countFont = Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM);
            float countWidth = countFont.getStringWidth(countText) + 10.0f;
            float countX = layout.headerX + layout.headerWidth - countWidth - 10.0f;
            rectangle.render(ShapeProperties.create(matrices, countX, badgeY, countWidth, badgeH).round(6.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 180.0f)).build());
            float countDrawY = this.category == ModuleCategory.ESP ? iconDrawY + (9.0f - countFont.getStringHeight(countText)) / 2.0f : countFont.getCenteredY(badgeY, badgeH, countText);
            countFont.drawString(matrices, countText, countX + 5.0f, countDrawY, Calculate.applyOpacity(ThemeManager.textColor.getColor(), 150.0f));
        }
    }

    private void renderModulesContent(class_332 context, GuiLayout layout, float mouseX, float mouseY) {
        class_4587 matrices = context.method_51448();
        CategoryColumn column = this.categoryColumns.getOrDefault((Object)this.category, new CategoryColumn(this.category, 0.0f, 0.0f));
        if (!this.searchText.equals(this.lastSearchText)) {
            this.lastSearchText = this.searchText;
            column.scrollOffset = 0.0f;
            column.targetScrollOffset = 0.0f;
        }
        column.updateScroll();
        this.updateExpandAnimations();
        this.hoveredModule = null;
        this.tooltipVisible = false;
        rectangle.render(ShapeProperties.create(matrices, layout.moduleListX, layout.moduleListY, layout.moduleListWidth, layout.moduleListHeight).round(8.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 200.0f)).build());
        rectangle.render(ShapeProperties.create(matrices, layout.moduleListX, layout.moduleListY, layout.moduleListWidth, layout.moduleListHeight).round(8.0f).color(Calculate.applyOpacity(-16777216, 25.0f)).build());
        List<Module> categoryModules = this.getFilteredModuleList(this.category, true);
        float visibleModulesHeight = layout.moduleListHeight;
        float totalModulesHeight = (float)categoryModules.size() * 22.0f;
        AlphaDLC.getInstance().getScissorManager().push(matrices.method_23760().method_23761(), layout.moduleListX, layout.moduleListY - 2.0f, layout.moduleListWidth, visibleModulesHeight + 4.0f);
        float moduleY = layout.moduleListY - column.scrollOffset;
        for (Module module : categoryModules) {
            boolean moduleVisible;
            float currentModuleY = moduleY;
            boolean bl = moduleVisible = currentModuleY + 20.0f > layout.moduleListY && currentModuleY < layout.moduleListY + visibleModulesHeight;
            if (moduleVisible) {
                this.renderModule(context, module, layout.moduleListX, currentModuleY, layout.moduleListWidth, mouseX, mouseY, 1.0f, layout.moduleListY, layout.moduleListY + visibleModulesHeight);
                if (Calculate.isHovered(mouseX, mouseY, layout.moduleListX, currentModuleY, layout.moduleListWidth, 20.0) && mouseY >= layout.moduleListY && mouseY <= layout.moduleListY + visibleModulesHeight) {
                    this.hoveredModuleName = module.getName();
                    this.hoveredModule = module;
                }
            }
            moduleY += 22.0f;
        }
        AlphaDLC.getInstance().getScissorManager().pop();
        if (totalModulesHeight > visibleModulesHeight) {
            float scrollbarWidth = 2.0f;
            float scrollbarHeight = visibleModulesHeight / totalModulesHeight * visibleModulesHeight;
            float scrollbarY = layout.moduleListY + column.scrollOffset / totalModulesHeight * visibleModulesHeight;
            rectangle.render(ShapeProperties.create(matrices, layout.moduleListX + layout.moduleListWidth - scrollbarWidth, layout.moduleListY, scrollbarWidth, visibleModulesHeight).round(1.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 60.0f)).build());
            rectangle.render(ShapeProperties.create(matrices, layout.moduleListX + layout.moduleListWidth - scrollbarWidth, scrollbarY, scrollbarWidth, scrollbarHeight).round(1.0f).color(ColorAssist.getClientColor(), ColorAssist.getClientColor2(), ColorAssist.getClientColor(), ColorAssist.getClientColor2()).build());
        }
        column.maxScroll = Math.max(0.0f, totalModulesHeight - visibleModulesHeight);
        if (column.targetScrollOffset > column.maxScroll) {
            column.targetScrollOffset = column.maxScroll;
        }
        if (column.scrollOffset > column.maxScroll) {
            column.scrollOffset = column.maxScroll;
        }
        this.categoryColumns.put(this.category, column);
        this.updateModuleTooltip(layout, mouseX, mouseY);
    }

    private void updateExpandAnimations() {
        for (Module module2 : this.expandedModules.keySet()) {
            float currentProgress = this.expandAnimations.getOrDefault(module2, Float.valueOf(0.0f)).floatValue();
            if (!(currentProgress < 1.0f)) continue;
            float speed = 0.08f;
            currentProgress += speed;
            currentProgress = Math.min(1.0f, currentProgress);
            this.expandAnimations.put(module2, Float.valueOf(currentProgress));
        }
        this.expandAnimations.keySet().removeIf(module -> {
            if (!this.expandedModules.containsKey(module)) {
                float currentProgress = this.expandAnimations.getOrDefault(module, Float.valueOf(0.0f)).floatValue();
                if (currentProgress > 0.0f) {
                    float speed = 0.12f;
                    currentProgress -= speed;
                    currentProgress = Math.max(0.0f, currentProgress);
                    this.expandAnimations.put((Module)module, Float.valueOf(currentProgress));
                    return currentProgress <= 0.0f;
                }
                return true;
            }
            return false;
        });
    }

    private void renderCustomizationContent(class_332 context, GuiLayout layout, float mouseX, float mouseY) {
        class_4587 matrices = context.method_51448();
        rectangle.render(ShapeProperties.create(matrices, layout.moduleListX, layout.moduleListY, layout.moduleListWidth, layout.moduleListHeight).round(8.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 200.0f)).build());
        rectangle.render(ShapeProperties.create(matrices, layout.moduleListX, layout.moduleListY, layout.moduleListWidth, layout.moduleListHeight).round(8.0f).color(Calculate.applyOpacity(-16777216, 25.0f)).build());
        this.customizationComponent.x = layout.moduleListX + 10.0f;
        this.customizationComponent.y = layout.moduleListY + 10.0f;
        this.customizationComponent.width = layout.moduleListWidth - 20.0f;
        this.customizationComponent.height = layout.moduleListHeight - 20.0f;
        this.customizationComponent.render(context, (int)mouseX, (int)mouseY, 0.0f);
    }

    private void renderSettingsPanel(class_332 context, GuiLayout layout, float mouseX, float mouseY) {
        boolean wantsOpen;
        class_4587 matrices = context.method_51448();
        boolean bl = wantsOpen = this.selectedModule != null && this.expandedModules.containsKey(this.selectedModule);
        if (!wantsOpen) {
            this.displayedSettingsModule = null;
            this.settingsPanelAnim = 0.0f;
            this.renderWaitGifPlaceholder(context, layout);
            return;
        }
        this.displayedSettingsModule = this.selectedModule;
        this.settingsPanelAnim = Calculate.interpolateSmooth(8.0, this.settingsPanelAnim, 1.0f);
        if (this.displayedSettingsModule == null) {
            this.renderWaitGifPlaceholder(context, layout);
            return;
        }
        float eased = 1.0f - (float)Math.pow(1.0f - this.settingsPanelAnim, 3.0);
        float slideOffset = (1.0f - eased) * 72.0f;
        float panelX = layout.settingsX + slideOffset;
        float panelY = layout.settingsY;
        float panelW = layout.settingsWidth;
        float panelH = layout.settingsHeight;
        float panelAlpha = 0.55f + eased * 0.45f;
        Calculate.setAlpha(panelAlpha, () -> {
            rectangle.render(ShapeProperties.create(matrices, panelX, panelY, panelW, panelH).round(8.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 210.0f)).build());
            rectangle.render(ShapeProperties.create(matrices, panelX, panelY, panelW, panelH).round(8.0f).color(Calculate.applyOpacity(-16777216, 24.0f)).build());
        });
        if (this.settingsPanelAnim < 0.01f) {
            return;
        }
        List<AbstractSettingComponent> settingComponents = this.expandedModules.get(this.displayedSettingsModule);
        if (settingComponents == null || settingComponents.isEmpty()) {
            return;
        }
        float contentAlpha = class_3532.method_15363((float)(eased * this.settingsPanelAnim), (float)0.0f, (float)1.0f);
        this.settingsScrollOffset = Calculate.interpolateSmooth(6.0, this.settingsScrollOffset, this.settingsTargetScroll);
        float titleBarH = 20.0f;
        float contentX = panelX + 10.0f;
        float headerY = panelY + 10.0f;
        float scrollAreaY = headerY + titleBarH;
        float contentWidth = panelW - 20.0f;
        float scrollAreaHeight = panelH - 20.0f - titleBarH;
        if (contentWidth <= 10.0f || scrollAreaHeight <= 10.0f) {
            return;
        }
        String title = this.displayedSettingsModule.getVisibleName();
        Calculate.setAlpha(contentAlpha, () -> Fonts.getSize(16, Fonts.Type.ROCKSTAR_MEDIUM).drawVerticallyCentered(matrices, title, contentX, headerY, titleBarH, ThemeManager.textColor.getColor()));
        float clipInset = 2.0f;
        AlphaDLC.getInstance().getScissorManager().push(matrices.method_23760().method_23761(), panelX + clipInset, scrollAreaY, panelW - clipInset * 2.0f, scrollAreaHeight);
        float settingY = scrollAreaY - this.settingsScrollOffset;
        float totalHeight = 0.0f;
        for (AbstractSettingComponent component : settingComponents) {
            if (!component.getSetting().isVisible()) continue;
            component.prepareLayout(contentWidth);
            component.x = contentX;
            component.y = settingY;
            Calculate.setAlpha(contentAlpha, () -> component.render(context, (int)mouseX, (int)mouseY, 0.0f));
            settingY += component.height + 4.0f;
            totalHeight += component.height + 4.0f;
        }
        AlphaDLC.getInstance().getScissorManager().pop();
        this.settingsMaxScroll = Math.max(0.0f, totalHeight - scrollAreaHeight);
    }

    private void renderWaitGifPlaceholder(class_332 context, GuiLayout layout) {
        class_4587 matrices = context.method_51448();
        rectangle.render(ShapeProperties.create(matrices, layout.settingsX, layout.settingsY, layout.settingsWidth, layout.settingsHeight).round(8.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 210.0f)).build());
        rectangle.render(ShapeProperties.create(matrices, layout.settingsX, layout.settingsY, layout.settingsWidth, layout.settingsHeight).round(8.0f).color(Calculate.applyOpacity(-16777216, 24.0f)).build());
        float gifSize = Math.min(layout.settingsWidth - 40.0f, layout.settingsHeight - 40.0f);
        gifSize = Math.max(64.0f, Math.min(gifSize, 160.0f));
        float gifX = layout.settingsX + (layout.settingsWidth - gifSize) / 2.0f;
        float gifY = layout.settingsY + (layout.settingsHeight - gifSize) / 2.0f - 8.0f;
        RenderSystem.enableBlend();
        this.waitGifRenderer.render(context, gifX, gifY, gifSize, gifSize);
        RenderSystem.disableBlend();
        String hint = "Open module settings";
        float hintX = layout.settingsX + (layout.settingsWidth - Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).getStringWidth(hint)) / 2.0f;
        float hintY = gifY + gifSize + 10.0f;
        Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, hint, hintX, hintY, Calculate.applyOpacity(ThemeManager.textColor.getColor(), 140.0f));
    }

    private void renderEspContent(class_332 context, GuiLayout layout, float mouseX, float mouseY, float delta) {
        this.hoveredModule = null;
        this.tooltipVisible = false;
        this.renderEspPreviewPanel(context, layout, mouseX, mouseY, delta);
        this.renderEspSettingsPanel(context, layout, mouseX, mouseY);
    }

    private void renderEspPreviewPanel(class_332 context, GuiLayout layout, float mouseX, float mouseY, float delta) {
        class_4587 matrices = context.method_51448();
        rectangle.render(ShapeProperties.create(matrices, layout.moduleListX, layout.moduleListY, layout.moduleListWidth, layout.moduleListHeight).round(8.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 200.0f)).build());
        rectangle.render(ShapeProperties.create(matrices, layout.moduleListX, layout.moduleListY, layout.moduleListWidth, layout.moduleListHeight).round(8.0f).color(Calculate.applyOpacity(-16777216, 25.0f)).build());
        Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, "Preview", layout.moduleListX + 10.0f, layout.moduleListY + 10.0f - 2.0f, Calculate.applyOpacity(ThemeManager.textColor.getColor(), 170.0f));
        float innerX = layout.moduleListX + 10.0f;
        float innerY = layout.moduleListY + 10.0f + 10.0f;
        float innerW = layout.moduleListWidth - 20.0f;
        float innerH = layout.moduleListHeight - 20.0f - 10.0f;
        rectangle.render(ShapeProperties.create(matrices, innerX, innerY, innerW, innerH).round(7.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 150.0f)).build());
        rectangle.render(ShapeProperties.create(matrices, innerX, innerY, innerW, innerH).round(7.0f).color(Calculate.applyOpacity(-16119278, 232.0f)).build());
        this.espPreviewX = innerX;
        this.espPreviewY = innerY;
        this.espPreviewW = innerW;
        this.espPreviewH = innerH;
        boolean hovered = Calculate.isHovered(mouseX, mouseY, innerX, innerY, innerW, innerH);
        int hintColor = Calculate.applyOpacity(ThemeManager.textColor.getColor(), hovered ? 170.0f : 110.0f);
        Fonts.getSize(10, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, "Drag to rotate", innerX + 6.0f, innerY + 6.0f, hintColor);
        if (MenuScreen.mc.field_1724 != null) {
            class_1309 previewEntity = this.getEspPreviewEntity();
            if (previewEntity != null) {
                float entityHeight = Math.max(1.0f, previewEntity.method_17682());
                float entityWidth = Math.max(0.6f, previewEntity.method_17681());
                float sizeByHeight = (innerH - 16.0f) / entityHeight;
                float sizeByWidth = (innerW - 20.0f) / (entityWidth * 2.0f);
                float size = Math.max(8.0f, Math.min(sizeByHeight, sizeByWidth) * 0.62f);
                float entityX = innerX + innerW / 2.0f;
                float entityY = innerY + innerH / 2.0f + entityHeight * size * 0.42f + 6.0f;
                Render2D.onRender(context);
                Rectangle.flushPending();
                AlphaDLC.getInstance().getScissorManager().push(matrices.method_23760().method_23761(), innerX, innerY, innerW, innerH);
                this.drawEntityPreview(context, entityX, entityY, size, this.espPreviewYaw, this.espPreviewPitch, previewEntity);
                this.renderEspPreviewDecorations(context, innerX, innerY, innerW, innerH, entityX, entityY, size);
                AlphaDLC.getInstance().getScissorManager().pop();
            }
        } else {
            Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, "No player", innerX + (innerW - Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).getStringWidth("No player")) / 2.0f, innerY + innerH / 2.0f, Calculate.applyOpacity(ThemeManager.textColor.getColor(), 120.0f));
        }
    }

    private void renderEspSettingsPanel(class_332 context, GuiLayout layout, float mouseX, float mouseY) {
        this.espSettingsLayoutReady = false;
        class_4587 matrices = context.method_51448();
        this.settingsPanelAnim = Calculate.interpolateSmooth(6.0, this.settingsPanelAnim, 1.0f);
        float panelY = layout.settingsY + (1.0f - this.settingsPanelAnim) * 6.0f;
        Calculate.setAlpha(0.6f + this.settingsPanelAnim * 0.4f, () -> {
            rectangle.render(ShapeProperties.create(matrices, layout.settingsX, panelY, layout.settingsWidth, layout.settingsHeight).round(8.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 210.0f)).build());
            rectangle.render(ShapeProperties.create(matrices, layout.settingsX, panelY, layout.settingsWidth, layout.settingsHeight).round(8.0f).color(Calculate.applyOpacity(-16777216, 22.0f)).build());
        });
        this.settingsScrollOffset = Calculate.interpolateSmooth(6.0, this.settingsScrollOffset, this.settingsTargetScroll);
        float clipInset = 2.0f;
        float clipX = layout.settingsX + clipInset;
        float clipY = panelY + clipInset;
        float clipW = Math.max(0.0f, layout.settingsWidth - clipInset * 2.0f);
        float clipH = Math.max(0.0f, layout.settingsHeight - clipInset * 2.0f);
        AlphaDLC.getInstance().getScissorManager().push(matrices.method_23760().method_23761(), clipX, clipY, clipW, clipH);
        float contentX = layout.settingsX + 10.0f;
        float contentY = panelY + 10.0f;
        float contentWidth = layout.settingsWidth - 20.0f;
        float contentHeight = layout.settingsHeight - 20.0f;
        float sectionY = contentY - this.settingsScrollOffset;
        Module espModule = this.getEspModule();
        Module skeletonModule = this.getEspSkeletonModule();
        Module nameTagsModule = this.getNameTagsModule();
        sectionY = this.renderEspSection(context, contentX, sectionY, contentWidth, "ESP", espModule, this.espSectionLayout, mouseX, mouseY);
        sectionY += 8.0f;
        sectionY = this.renderEspSection(context, contentX, sectionY, contentWidth, "Skeleton", skeletonModule, this.skeletonSectionLayout, mouseX, mouseY);
        sectionY += 8.0f;
        sectionY = this.renderEspSection(context, contentX, sectionY, contentWidth, "NameTags", nameTagsModule, this.nameTagsSectionLayout, mouseX, mouseY);
        AlphaDLC.getInstance().getScissorManager().pop();
        float totalHeight = sectionY - (contentY - this.settingsScrollOffset);
        this.settingsMaxScroll = Math.max(0.0f, totalHeight - contentHeight);
        this.espSettingsLayoutReady = true;
    }

    private float renderEspSection(class_332 context, float x, float y, float width, String title, Module module, EspSectionLayout layout, float mouseX, float mouseY) {
        class_4587 matrices = context.method_51448();
        float headerHeight = 20.0f;
        layout.sectionStartY = y;
        layout.headerX = x;
        layout.headerY = y;
        layout.headerW = width;
        layout.headerH = headerHeight;
        layout.sectionTitle = title;
        rectangle.render(ShapeProperties.create(matrices, x, y, width, headerHeight).round(6.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 160.0f)).build());
        rectangle.render(ShapeProperties.create(matrices, x, y, width, headerHeight).round(6.0f).color(Calculate.applyOpacity(-16777216, 18.0f)).build());
        FontRenderer headerFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        ToggleSwitchRenderer.drawLabel(headerFont, matrices, title, x + 6.0f, y, headerHeight, ThemeManager.textColor.getColor());
        float toggleX = ToggleSwitchRenderer.resolveHeaderX(x, width);
        float toggleY = ToggleSwitchRenderer.alignedControlY(headerFont, title, y, headerHeight, 10.0f);
        layout.toggleX = toggleX;
        layout.toggleY = toggleY;
        layout.toggleW = 22.0f;
        layout.toggleH = 10.0f;
        if (module != null) {
            this.layoutEspSectionBindPill(layout, module);
            this.renderEspSectionBindPill(context, layout, module, mouseX, mouseY);
            ToggleSwitchRenderer.renderHeader(context, toggleX, toggleY, module.isState(), module);
        }
        float settingY = y + headerHeight + 6.0f;
        if (module == null) {
            Fonts.getSize(11, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, "Module not found", x + 6.0f, settingY + 4.0f, Calculate.applyOpacity(ThemeManager.textColor.getColor(), 120.0f));
            settingY += 12.0f;
        } else {
            List<AbstractSettingComponent> components = this.getEspSettingsComponents(module);
            for (AbstractSettingComponent component : components) {
                if (!component.getSetting().isVisible()) continue;
                component.prepareLayout(width);
                component.x = x;
                component.y = settingY;
                component.render(context, (int)mouseX, (int)mouseY, 0.0f);
                settingY += component.height + 4.0f;
            }
        }
        layout.sectionEndY = settingY + 8.0f;
        return layout.sectionEndY;
    }

    private void layoutEspSectionBindPill(EspSectionLayout layout, Module module) {
        if (module == null) {
            layout.bindPillW = 0.0f;
            return;
        }
        FontRenderer headerFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        boolean listening = BindCaptureHelper.isCapturing(module);
        String keyText = listening ? "..." : BindCodes.getDisplayName(module.getKey());
        layout.bindPillW = ToggleSwitchRenderer.bindPillWidth(keyText);
        layout.bindPillH = 14.0f;
        layout.bindPillX = layout.toggleX - layout.bindPillW - 4.0f;
        layout.bindPillY = ToggleSwitchRenderer.bindPillTrackY(headerFont, layout.sectionTitle, layout.headerY, layout.headerH);
    }

    private void renderEspSectionBindPill(class_332 context, EspSectionLayout layout, Module module, float mouseX, float mouseY) {
        int outlineColor;
        int pulseAlpha;
        if (module == null || layout.bindPillW <= 0.0f) {
            return;
        }
        class_4587 matrices = context.method_51448();
        boolean listening = BindCaptureHelper.isCapturing(module);
        String keyText = listening ? "..." : BindCodes.getDisplayName(module.getKey());
        FontRenderer pillFont = Fonts.getSize(12, Fonts.Type.ROCKSTAR_SEMIBOLD);
        boolean hovered = Calculate.isHovered(mouseX, mouseY, layout.bindPillX, layout.bindPillY, layout.bindPillW, layout.bindPillH);
        long pulse = System.currentTimeMillis();
        int n = pulseAlpha = listening ? (int)(120.0 + 80.0 * Math.abs(Math.sin((double)pulse / 220.0))) : 0;
        int n2 = listening ? ColorAssist.applyOpacity(ColorAssist.getClientColor(), pulseAlpha + 80) : (outlineColor = ColorAssist.applyOpacity(hovered ? ColorAssist.getClientColor() : ColorAssist.getOutline(), hovered ? 200 : 130));
        int fillColor = listening ? ColorAssist.applyOpacity(ColorAssist.getClientColor(), pulseAlpha) : ColorAssist.applyOpacity(ThemeManager.offModuleColor.getColor(), hovered ? 165 : 120);
        rectangle.render(ShapeProperties.create(matrices, layout.bindPillX, layout.bindPillY, layout.bindPillW, layout.bindPillH).round(5.0f).thickness(listening ? 1.2f : (hovered ? 1.0f : 0.7f)).outlineColor(outlineColor).color(fillColor).build());
        ToggleSwitchRenderer.drawPillTextCentered(pillFont, matrices, keyText, layout.bindPillX, layout.bindPillY, layout.bindPillW, layout.bindPillH, listening ? ColorAssist.getClientColor() : ThemeManager.textColor.getColor());
    }

    private boolean tryClickEspSectionBind(double mouseX, double mouseY, int button, EspSectionLayout layout, Module module) {
        if (module == null || layout.sectionStartY < 0.0f) {
            return false;
        }
        this.layoutEspSectionBindPill(layout, module);
        if (BindCaptureHelper.isCapturing(module)) {
            if (button == 0 && Calculate.isHovered(mouseX, mouseY, layout.bindPillX, layout.bindPillY, layout.bindPillW, layout.bindPillH)) {
                BindCaptureHelper.cancel();
                return true;
            }
            return BindCaptureHelper.handleMouse(button);
        }
        if (button == 0 && Calculate.isHovered(mouseX, mouseY, layout.bindPillX, layout.bindPillY, layout.bindPillW, layout.bindPillH)) {
            BindCaptureHelper.toggle(module);
            return true;
        }
        if (button == 2 && Calculate.isHovered(mouseX, mouseY, layout.headerX, layout.headerY, layout.headerW, layout.headerH) && !ToggleSwitchRenderer.isHeaderHovered(mouseX, mouseY, layout.toggleX, layout.toggleY)) {
            BindCaptureHelper.toggle(module);
            return true;
        }
        return false;
    }

    private boolean isEspModuleBindTarget(Module module) {
        if (module == null || !BindCaptureHelper.isCapturing()) {
            return false;
        }
        Object target = BindCaptureHelper.getTarget();
        return target == module;
    }

    private List<AbstractSettingComponent> getEspSettingsComponents(Module module) {
        if (module == null) {
            return Collections.emptyList();
        }
        int version = module.settings().size();
        Integer cachedVersion = this.espSettingsCacheVersion.get(module);
        if (cachedVersion == null || cachedVersion != version) {
            ArrayList<AbstractSettingComponent> components = new ArrayList<AbstractSettingComponent>();
            this.settingComponentAdder.addSettingComponent(module.settings(), components, module);
            this.espSettingsCache.put(module, components);
            this.espSettingsCacheVersion.put(module, version);
        }
        return this.espSettingsCache.get(module);
    }

    private void drawEntityPreview(class_332 context, float x, float y, float size, float yaw, float pitch, class_1309 entity) {
        float wrappedYaw;
        class_4587 matrices = context.method_51448();
        matrices.method_22903();
        matrices.method_22904((double)x, (double)y, 1050.0);
        matrices.method_22905(1.0f, 1.0f, -1.0f);
        matrices.method_22905(size, size, size);
        float pitchRad = (float)Math.toRadians(pitch);
        Quaternionf cameraRotation = new Quaternionf().rotateX(-pitchRad);
        Quaternionf entityRotation = new Quaternionf().rotateZ((float)Math.PI).mul((Quaternionfc)cameraRotation);
        matrices.method_22907(entityRotation);
        float prevBodyYaw = entity.field_6283;
        float prevYaw = entity.method_36454();
        float prevPitch = entity.method_36455();
        float prevHeadYaw = entity.field_6241;
        float prevPrevHeadYaw = entity.field_6259;
        float prevPrevBodyYaw = entity.field_6220;
        float prevPrevYaw = entity.field_5982;
        float prevPrevPitch = entity.field_6004;
        float bodyYaw = wrappedYaw = class_3532.method_15393((float)yaw);
        float modelPitch = -pitch * 0.9f;
        entity.field_6283 = bodyYaw;
        entity.field_6220 = bodyYaw;
        entity.method_36456(bodyYaw);
        entity.field_5982 = bodyYaw;
        entity.method_36457(modelPitch);
        entity.field_6004 = modelPitch;
        entity.field_6241 = bodyYaw;
        entity.field_6259 = bodyYaw;
        class_898 dispatcher = mc.method_1561();
        dispatcher.method_24196(new Quaternionf((Quaternionfc)cameraRotation).conjugate());
        dispatcher.method_3948(false);
        RenderSystem.enableDepthTest();
        class_4597.class_4598 vertexConsumers = mc.method_22940().method_23000();
        dispatcher.method_62424((class_1297)entity, 0.0, 0.0, 0.0, 0.0f, matrices, (class_4597)vertexConsumers, 0xF000F0);
        if (vertexConsumers instanceof class_4597.class_4598) {
            class_4597.class_4598 immediate = vertexConsumers;
            immediate.method_22993();
        } else {
            context.method_51452();
        }
        dispatcher.method_24196(new Quaternionf());
        dispatcher.method_3948(true);
        RenderSystem.disableDepthTest();
        entity.field_6283 = prevBodyYaw;
        entity.field_6220 = prevPrevBodyYaw;
        entity.method_36456(prevYaw);
        entity.field_5982 = prevPrevYaw;
        entity.method_36457(prevPitch);
        entity.field_6004 = prevPrevPitch;
        entity.field_6241 = prevHeadYaw;
        entity.field_6259 = prevPrevHeadYaw;
        matrices.method_22909();
    }

    private void renderEspPreviewDecorations(class_332 context, float innerX, float innerY, float innerW, float innerH, float entityX, float entityY, float size) {
        NameTags nameTagsInstance;
        Module nameTagsModule;
        Esp espInstance;
        class_4587 matrices = context.method_51448();
        class_1309 previewEntity = this.getEspPreviewEntity();
        float boxW = previewEntity != null ? previewEntity.method_17681() * size : size * 0.8f;
        float boxH = previewEntity != null ? previewEntity.method_17682() * size : size * 1.8f;
        boxW = Math.min(boxW, innerW - 12.0f);
        boxH = Math.min(boxH, innerH - 12.0f);
        float bottom = Math.min(entityY, innerY + innerH - 6.0f);
        float top = Math.max(innerY + 6.0f, bottom - boxH);
        Vector4d vec = new Vector4d((double)(entityX - boxW / 2.0f), (double)top, (double)(entityX + boxW / 2.0f), (double)bottom);
        Module espModule = this.getEspModule();
        if (espModule instanceof Esp && (espInstance = (Esp)espModule).isState()) {
            RenderSystem.disableDepthTest();
            Calculate.setAlpha(1.0f, () -> {
                espInstance.renderPreviewBox(matrices, vec, false, this.espPreviewYaw, this.espPreviewPitch);
                if (previewEntity != null) {
                    espInstance.renderPreviewPotions(matrices, previewEntity, vec);
                }
                Render2D.onRender(context);
            });
        }
        if ((nameTagsModule = this.getNameTagsModule()) instanceof NameTags && (nameTagsInstance = (NameTags)nameTagsModule).isState()) {
            RenderSystem.disableDepthTest();
            if (previewEntity instanceof class_1657) {
                class_1657 playerEntity = (class_1657)previewEntity;
                Vector4d nameTagVec = new Vector4d(vec.x, vec.y - 5.0, vec.z, vec.w);
                Calculate.setAlpha(1.0f, () -> nameTagsInstance.renderPreview(context, matrices, playerEntity, nameTagVec));
            }
        }
    }

    private class_1309 getEspPreviewEntity() {
        if (MenuScreen.mc.field_1687 == null) {
            return null;
        }
        if (!(this.espPreviewEntity instanceof class_745) || this.espPreviewEntity.method_37908() != MenuScreen.mc.field_1687) {
            GameProfile profile = new GameProfile(this.espPreviewUuid, "Enemy");
            this.espPreviewEntity = new class_745(MenuScreen.mc.field_1687, profile);
        }
        this.espPreviewEntity.method_18380(class_4050.field_18076);
        this.espPreviewEntity.method_6033(20.0f);
        this.espPreviewEntity.method_6073(0.0f);
        this.espPreviewEntity.method_6012();
        this.espPreviewEntity.method_6092(new class_1293(class_1294.field_5904, 3560, 1));
        this.espPreviewEntity.method_6092(new class_1293(class_1294.field_5910, 3560, 1));
        this.espPreviewEntity.method_6092(new class_1293(class_1294.field_5924, 3560, 1));
        for (class_1304 slot : class_1304.values()) {
            this.espPreviewEntity.method_5673(slot, class_1799.field_8037);
        }
        this.espPreviewEntity.method_5673(class_1304.field_6169, new class_1799((class_1935)class_1802.field_8805));
        this.espPreviewEntity.method_5673(class_1304.field_6174, new class_1799((class_1935)class_1802.field_22028));
        this.espPreviewEntity.method_5673(class_1304.field_6172, new class_1799((class_1935)class_1802.field_8396));
        this.espPreviewEntity.method_5673(class_1304.field_6166, new class_1799((class_1935)class_1802.field_8370));
        this.espPreviewEntity.method_5673(class_1304.field_6173, new class_1799((class_1935)class_1802.field_22022));
        this.espPreviewEntity.method_5673(class_1304.field_6171, new class_1799((class_1935)class_1802.field_8288));
        return this.espPreviewEntity;
    }

    private Module getEspModule() {
        Esp module = Esp.getInstance();
        if (module != null) {
            return module;
        }
        return this.getModuleByName("Esp");
    }

    private Module getEspSkeletonModule() {
        EspSkeleton module = EspSkeleton.getInstance();
        if (module != null) {
            return module;
        }
        return this.getModuleByName("EspSkeleton");
    }

    private Module getNameTagsModule() {
        NameTags module = NameTags.getInstance();
        if (module != null) {
            return module;
        }
        return this.getModuleByName("NameTags");
    }

    private float getEspSettingsPanelY(GuiLayout layout) {
        return layout.settingsY + (1.0f - this.settingsPanelAnim) * 6.0f;
    }

    private float getEspSettingsContentY(GuiLayout layout) {
        return this.getEspSettingsPanelY(layout) + 10.0f - this.settingsScrollOffset;
    }

    private boolean isEspSettingsPanelHovered(double mouseX, double mouseY, GuiLayout layout) {
        float panelY = this.getEspSettingsPanelY(layout);
        return Calculate.isHovered(mouseX, mouseY, layout.settingsX, panelY, layout.settingsWidth, layout.settingsHeight);
    }

    private void syncEspSettingsClickLayout(GuiLayout layout) {
        if (this.espSettingsLayoutReady) {
            return;
        }
        float contentX = layout.settingsX + 10.0f;
        float contentWidth = layout.settingsWidth - 20.0f;
        float sectionY = this.getEspSettingsContentY(layout);
        this.espSectionLayout.sectionTitle = "ESP";
        this.espSectionLayout.sectionStartY = sectionY;
        this.espSectionLayout.sectionEndY = this.measureEspSectionEnd(sectionY, contentWidth, this.getEspModule());
        this.espSectionLayout.headerX = contentX;
        this.espSectionLayout.headerY = sectionY;
        this.espSectionLayout.headerW = contentWidth;
        this.espSectionLayout.headerH = 20.0f;
        FontRenderer espHeaderFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        this.espSectionLayout.toggleX = ToggleSwitchRenderer.resolveHeaderX(contentX, contentWidth);
        this.espSectionLayout.toggleY = ToggleSwitchRenderer.alignedControlY(espHeaderFont, "ESP", sectionY, 20.0f, 10.0f);
        this.espSectionLayout.toggleW = 22.0f;
        this.espSectionLayout.toggleH = 10.0f;
        this.layoutEspSectionBindPill(this.espSectionLayout, this.getEspModule());
        float skeletonStart = this.espSectionLayout.sectionEndY + 8.0f;
        this.skeletonSectionLayout.sectionTitle = "Skeleton";
        this.skeletonSectionLayout.sectionStartY = skeletonStart;
        this.skeletonSectionLayout.sectionEndY = this.measureEspSectionEnd(skeletonStart, contentWidth, this.getEspSkeletonModule());
        this.skeletonSectionLayout.headerX = contentX;
        this.skeletonSectionLayout.headerY = skeletonStart;
        this.skeletonSectionLayout.headerW = contentWidth;
        this.skeletonSectionLayout.headerH = 20.0f;
        this.skeletonSectionLayout.toggleX = ToggleSwitchRenderer.resolveHeaderX(contentX, contentWidth);
        this.skeletonSectionLayout.toggleY = ToggleSwitchRenderer.alignedControlY(espHeaderFont, "Skeleton", skeletonStart, 20.0f, 10.0f);
        this.skeletonSectionLayout.toggleW = 22.0f;
        this.skeletonSectionLayout.toggleH = 10.0f;
        this.layoutEspSectionBindPill(this.skeletonSectionLayout, this.getEspSkeletonModule());
        float nameTagsStart = this.skeletonSectionLayout.sectionEndY + 8.0f;
        this.nameTagsSectionLayout.sectionTitle = "NameTags";
        this.nameTagsSectionLayout.sectionStartY = nameTagsStart;
        this.nameTagsSectionLayout.sectionEndY = this.measureEspSectionEnd(nameTagsStart, contentWidth, this.getNameTagsModule());
        this.nameTagsSectionLayout.headerX = contentX;
        this.nameTagsSectionLayout.headerY = nameTagsStart;
        this.nameTagsSectionLayout.headerW = contentWidth;
        this.nameTagsSectionLayout.headerH = 20.0f;
        this.nameTagsSectionLayout.toggleX = ToggleSwitchRenderer.resolveHeaderX(contentX, contentWidth);
        this.nameTagsSectionLayout.toggleY = ToggleSwitchRenderer.alignedControlY(espHeaderFont, "NameTags", nameTagsStart, 20.0f, 10.0f);
        this.nameTagsSectionLayout.toggleW = 22.0f;
        this.nameTagsSectionLayout.toggleH = 10.0f;
        this.layoutEspSectionBindPill(this.nameTagsSectionLayout, this.getNameTagsModule());
        this.layoutEspSectionComponents(this.espSectionLayout.sectionStartY, contentX, contentWidth, this.getEspModule());
        this.layoutEspSectionComponents(this.skeletonSectionLayout.sectionStartY, contentX, contentWidth, this.getEspSkeletonModule());
        this.layoutEspSectionComponents(this.nameTagsSectionLayout.sectionStartY, contentX, contentWidth, this.getNameTagsModule());
    }

    private float measureEspSectionEnd(float sectionStartY, float contentWidth, Module module) {
        float settingY = sectionStartY + 20.0f + 6.0f;
        if (module == null) {
            return settingY + 12.0f;
        }
        for (AbstractSettingComponent component : this.getEspSettingsComponents(module)) {
            if (!component.getSetting().isVisible()) continue;
            component.prepareLayout(contentWidth);
            settingY += component.height + 4.0f;
        }
        return settingY + 8.0f;
    }

    private void layoutEspSectionComponents(float sectionStartY, float contentX, float contentWidth, Module module) {
        if (module == null) {
            return;
        }
        float settingY = sectionStartY + 20.0f + 6.0f;
        for (AbstractSettingComponent component : this.getEspSettingsComponents(module)) {
            if (!component.getSetting().isVisible()) continue;
            component.prepareLayout(contentWidth);
            component.x = contentX;
            component.y = settingY;
            settingY += component.height + 4.0f;
        }
    }

    private boolean tryClickEspSectionToggle(double mouseX, double mouseY, int button, EspSectionLayout layout, Module module) {
        if (module == null || button != 0 || layout.sectionStartY < 0.0f) {
            return false;
        }
        if (ToggleSwitchRenderer.isHeaderHovered(mouseX, mouseY, layout.toggleX, layout.toggleY)) {
            module.switchState();
            SoundManager.playSound(SoundManager.SETTING_INTERACT);
            return true;
        }
        return false;
    }

    private boolean handleEspSettingsClick(double mouseX, double mouseY, int button, GuiLayout layout) {
        this.syncEspSettingsClickLayout(layout);
        Module espModule = this.getEspModule();
        Module skeletonModule = this.getEspSkeletonModule();
        Module nameTagsModule = this.getNameTagsModule();
        if (BindCaptureHelper.isCapturing() && (this.isEspModuleBindTarget(espModule) || this.isEspModuleBindTarget(skeletonModule) || this.isEspModuleBindTarget(nameTagsModule))) {
            if (this.tryClickEspSectionBind(mouseX, mouseY, button, this.nameTagsSectionLayout, nameTagsModule)) {
                return true;
            }
            if (this.tryClickEspSectionBind(mouseX, mouseY, button, this.skeletonSectionLayout, skeletonModule)) {
                return true;
            }
            if (this.tryClickEspSectionBind(mouseX, mouseY, button, this.espSectionLayout, espModule)) {
                return true;
            }
            if (this.handleEspSectionComponentClickFromLayout(mouseX, mouseY, button, nameTagsModule)) {
                return true;
            }
            if (this.handleEspSectionComponentClickFromLayout(mouseX, mouseY, button, skeletonModule)) {
                return true;
            }
            if (this.handleEspSectionComponentClickFromLayout(mouseX, mouseY, button, espModule)) {
                return true;
            }
            if (BindCaptureHelper.handleMouse(button)) {
                return true;
            }
            return this.isEspSettingsPanelHovered(mouseX, mouseY, layout);
        }
        if (this.tryClickEspSectionBind(mouseX, mouseY, button, this.nameTagsSectionLayout, nameTagsModule)) {
            return true;
        }
        if (this.tryClickEspSectionBind(mouseX, mouseY, button, this.skeletonSectionLayout, skeletonModule)) {
            return true;
        }
        if (this.tryClickEspSectionBind(mouseX, mouseY, button, this.espSectionLayout, espModule)) {
            return true;
        }
        if (this.tryClickEspSectionToggle(mouseX, mouseY, button, this.nameTagsSectionLayout, nameTagsModule)) {
            return true;
        }
        if (this.tryClickEspSectionToggle(mouseX, mouseY, button, this.skeletonSectionLayout, skeletonModule)) {
            return true;
        }
        if (this.tryClickEspSectionToggle(mouseX, mouseY, button, this.espSectionLayout, espModule)) {
            return true;
        }
        if (this.handleEspSectionComponentClickFromLayout(mouseX, mouseY, button, this.getNameTagsModule())) {
            SoundManager.playSound(SoundManager.SETTING_INTERACT);
            return true;
        }
        if (this.handleEspSectionComponentClickFromLayout(mouseX, mouseY, button, this.getEspSkeletonModule())) {
            SoundManager.playSound(SoundManager.SETTING_INTERACT);
            return true;
        }
        if (this.handleEspSectionComponentClickFromLayout(mouseX, mouseY, button, this.getEspModule())) {
            SoundManager.playSound(SoundManager.SETTING_INTERACT);
            return true;
        }
        return false;
    }

    private boolean handleEspSectionComponentClickFromLayout(double mouseX, double mouseY, int button, Module module) {
        if (module == null) {
            return false;
        }
        for (AbstractSettingComponent component : this.getEspSettingsComponents(module)) {
            if (!component.getSetting().isVisible() || !component.mouseClicked(mouseX, mouseY, button)) continue;
            return true;
        }
        return false;
    }

    private boolean handleEspSectionComponentClick(double mouseX, double mouseY, int button, float sectionStartY, float contentX, float contentWidth, Module module) {
        this.layoutEspSectionComponents(sectionStartY, contentX, contentWidth, module);
        return this.handleEspSectionComponentClickFromLayout(mouseX, mouseY, button, module);
    }

    private void handleEspSectionComponentReleaseFromLayout(double mouseX, double mouseY, int button, Module module) {
        if (module == null) {
            return;
        }
        for (AbstractSettingComponent component : this.getEspSettingsComponents(module)) {
            if (!component.getSetting().isVisible()) continue;
            component.mouseReleased(mouseX, mouseY, button);
        }
    }

    private boolean handleEspSectionComponentDragFromLayout(double mouseX, double mouseY, int button, double deltaX, double deltaY, Module module) {
        if (module == null) {
            return false;
        }
        for (AbstractSettingComponent component : this.getEspSettingsComponents(module)) {
            if (!component.getSetting().isVisible() || !component.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)) continue;
            return true;
        }
        return false;
    }

    private void handleEspSectionComponentRelease(double mouseX, double mouseY, int button, float sectionStartY, float contentX, float contentWidth, Module module) {
        if (module == null) {
            return;
        }
        float settingY = sectionStartY + 20.0f + 6.0f;
        for (AbstractSettingComponent component : this.getEspSettingsComponents(module)) {
            if (!component.getSetting().isVisible()) continue;
            component.prepareLayout(contentWidth);
            component.x = contentX;
            component.y = settingY;
            component.mouseReleased(mouseX, mouseY, button);
            settingY += component.height + 4.0f;
        }
    }

    private boolean handleEspSectionComponentDrag(double mouseX, double mouseY, int button, double deltaX, double deltaY, float sectionStartY, float contentX, float contentWidth, Module module) {
        if (module == null) {
            return false;
        }
        float settingY = sectionStartY + 20.0f + 6.0f;
        for (AbstractSettingComponent component : this.getEspSettingsComponents(module)) {
            if (!component.getSetting().isVisible()) continue;
            component.prepareLayout(contentWidth);
            component.x = contentX;
            component.y = settingY;
            if (component.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)) {
                return true;
            }
            settingY += component.height + 4.0f;
        }
        return false;
    }

    private Module getModuleByName(String name) {
        return AlphaDLC.getInstance().getModuleRepository().modules().stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    private static String safeModuleDescription(Module module) {
        if (module == null) {
            return null;
        }
        try {
            return ModuleDescriptions.tryGetDescription(module);
        }
        catch (Throwable ignored) {
            return null;
        }
    }

    private static boolean isMissingModuleDescription(String description) {
        try {
            return ModuleDescriptions.isMissingDescription(description);
        }
        catch (Throwable ignored) {
            return true;
        }
    }

    private void updateModuleTooltip(GuiLayout layout, float mouseX, float mouseY) {
        ClickGui clickGui = ClickGui.getInstance();
        if (clickGui != null && !clickGui.shouldShowModuleDescriptions()) {
            this.tooltipVisible = false;
            return;
        }
        if (this.hoveredModule == null) {
            this.tooltipVisible = false;
            return;
        }
        String description = MenuScreen.safeModuleDescription(this.hoveredModule);
        if (MenuScreen.isMissingModuleDescription(description)) {
            this.tooltipVisible = false;
            return;
        }
        String wrapped = StringHelper.wrap(description, 160, 11);
        String[] lines = wrapped.split("\n");
        float maxWidth = 0.0f;
        for (String line : lines) {
            maxWidth = Math.max(maxWidth, Fonts.getSize(11, Fonts.Type.ROCKSTAR_MEDIUM).getStringWidth(line));
        }
        float padding = 6.0f;
        float width = maxWidth + padding * 2.0f;
        float height = (float)lines.length * (Fonts.getSize(11, Fonts.Type.ROCKSTAR_MEDIUM).getStringHeight("A") + 2.0f) + padding * 2.0f;
        float x = mouseX + 10.0f;
        float y = mouseY + 10.0f;
        float screenW = window.getScaledWidth();
        float screenH = window.getScaledHeight();
        float listRight = layout.moduleListX + layout.moduleListWidth - 4.0f;
        if (x + width > listRight) {
            x = mouseX - width - 10.0f;
        }
        if (x + width > listRight) {
            x = listRight - width;
        }
        if (x < layout.moduleListX + 4.0f) {
            x = layout.moduleListX + 4.0f;
        }
        if (x + width > screenW) {
            x = screenW - width - 4.0f;
        }
        if (y + height > screenH) {
            y = mouseY - height - 10.0f;
        }
        this.tooltipX = x;
        this.tooltipY = y;
        this.tooltipW = width;
        this.tooltipH = height;
        this.tooltipVisible = true;
    }

    private void drawModuleTooltip(class_332 context) {
        if (!this.tooltipVisible || this.hoveredModule == null) {
            return;
        }
        String description = MenuScreen.safeModuleDescription(this.hoveredModule);
        if (MenuScreen.isMissingModuleDescription(description)) {
            return;
        }
        String wrapped = StringHelper.wrap(description, 160, 11);
        String[] lines = wrapped.split("\n");
        float padding = 6.0f;
        class_4587 matrices = context.method_51448();
        matrices.method_22903();
        matrices.method_46416(0.0f, 0.0f, 500.0f);
        rectangle.render(ShapeProperties.create(matrices, this.tooltipX, this.tooltipY, this.tooltipW, this.tooltipH).round(6.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 220.0f)).build());
        rectangle.render(ShapeProperties.create(matrices, this.tooltipX, this.tooltipY, this.tooltipW, this.tooltipH).round(6.0f).color(Calculate.applyOpacity(-16777216, 40.0f)).build());
        float textY = this.tooltipY + padding;
        for (String line : lines) {
            Fonts.getSize(11, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, line, this.tooltipX + padding, textY + 6.0f, ThemeManager.textColor.getColor());
            textY += Fonts.getSize(11, Fonts.Type.ROCKSTAR_MEDIUM).getStringHeight("A") + 2.0f;
        }
        matrices.method_22909();
    }

    private void renderSearchField(class_332 context, GuiLayout layout, float mouseX, float mouseY) {
        class_4587 matrices = context.method_51448();
        this.sidebarSearchFocusAnim = Calculate.interpolateSmooth(6.0, this.sidebarSearchFocusAnim, this.searchFieldFocused ? 1.0f : 0.0f);
        boolean hovered = Calculate.isHovered(mouseX, mouseY, layout.sidebarSearchX, layout.sidebarSearchY, layout.sidebarSearchWidth, layout.sidebarSearchHeight);
        int bgAlpha = ThemeManager.isLiquidGlassTheme() ? 90 + (int)(this.sidebarSearchFocusAnim * 30.0f) + (hovered ? 10 : 0) : 180 + (int)(this.sidebarSearchFocusAnim * 25.0f) + (hovered ? 8 : 0);
        int bgColor = ThemeManager.BackgroundGui.getColor();
        if (ThemeManager.isLiquidGlassTheme()) {
            bgColor &= 0xFFFFFF;
        }
        float boxX = layout.sidebarSearchX;
        float boxY = layout.sidebarSearchY;
        float boxW = layout.sidebarSearchWidth;
        float boxH = layout.sidebarSearchHeight;
        rectangle.render(ShapeProperties.create(matrices, boxX, boxY, boxW, boxH).round(6.0f).color(ColorAssist.applyOpacity(bgColor, bgAlpha)).build());
        rectangle.render(ShapeProperties.create(matrices, boxX + 1.0f, boxY + 1.0f, boxW - 2.0f, boxH - 2.0f).round(5.0f).color(Calculate.applyOpacity(ColorAssist.getClientColor(), (int)(this.sidebarSearchFocusAnim * 24.0f))).build());
        FontRenderer searchFont = Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM);
        float textX = boxX + 8.0f;
        float textAreaW = boxW - 16.0f;
        boolean hasQuery = !this.searchText.isEmpty();
        String displayText = hasQuery ? this.searchText : "\u041f\u043e\u0438\u0441\u043a";
        int searchTextColor = hasQuery ? ThemeManager.textColor.getColor() : Calculate.applyOpacity(ThemeManager.textColor.getColor(), 80.0f);
        float textY = searchFont.getCenteredY(boxY, boxH, displayText);
        float textH = searchFont.getStringHeight(displayText);
        if (hasQuery) {
            float textW = searchFont.getStringWidth(this.searchText);
            if (textW > textAreaW) {
                float cursorOffset = searchFont.getStringWidth(this.searchText.substring(0, this.searchCursor));
                float targetScroll = Math.max(0.0f, cursorOffset - textAreaW + 6.0f);
                this.searchTextScroll = Math.min(targetScroll, textW - textAreaW);
            } else {
                this.searchTextScroll = 0.0f;
            }
        } else {
            this.searchTextScroll = 0.0f;
        }
        float drawX = textX - this.searchTextScroll;
        AlphaDLC.getInstance().getScissorManager().push(matrices.method_23760().method_23761(), boxX + 1.0f, boxY + 1.0f, boxW - 2.0f, boxH - 2.0f);
        if (this.searchFieldFocused && this.hasSearchSelection()) {
            int selStart = this.getSearchSelectionStart();
            int selEnd = this.getSearchSelectionEnd();
            float selX1 = drawX + searchFont.getStringWidth(this.searchText.substring(0, selStart));
            float selX2 = drawX + searchFont.getStringWidth(this.searchText.substring(0, selEnd));
            float selW = Math.max(0.0f, selX2 - selX1);
            if (selW > 0.0f) {
                rectangle.render(ShapeProperties.create(matrices, selX1, textY, selW, textH).round(2.0f).color(ColorAssist.applyOpacity(ColorAssist.getClientColor(), 120)).build());
            }
        }
        searchFont.drawString(matrices, displayText, drawX, textY, searchTextColor);
        if (this.searchFieldFocused && !this.hasSearchSelection() && System.currentTimeMillis() % 1000L < 500L) {
            this.syncSearchCursor();
            String beforeCursor = this.searchText.substring(0, this.searchCursor);
            float cursorX = drawX + searchFont.getStringWidth(beforeCursor);
            float cursorH = Math.min(boxH - 6.0f, textH + 1.0f);
            float cursorY = boxY + (boxH - cursorH) / 2.0f;
            rectangle.render(ShapeProperties.create(matrices, cursorX + 1.0f, cursorY, 1.0, cursorH).color(ThemeManager.textColor.getColor()).build());
        }
        AlphaDLC.getInstance().getScissorManager().pop();
        this.searchSuggestionsAnim = 0.0f;
        this.searchSuggestionsH = 0.0f;
        this.searchSuggestionsW = 0.0f;
        this.searchSuggestionsY = 0.0f;
        this.searchSuggestionsX = 0.0f;
    }

    private GuiLayout getLayout() {
        float screenWidth = window.getScaledWidth();
        float screenHeight = window.getScaledHeight();
        float width = Math.min(560.0f, screenWidth - 40.0f);
        float height = Math.min(320.0f, screenHeight - 40.0f);
        float x = (screenWidth - width) / 2.0f;
        float y = (screenHeight - height) / 2.0f;
        float contentX = x + 150.0f + 12.0f;
        float contentY = y + 12.0f;
        float contentWidth = width - 150.0f - 24.0f;
        float contentHeight = height - 24.0f;
        float headerX = contentX;
        float headerY = contentY;
        float headerWidth = contentWidth;
        float headerHeight = 28.0f;
        float sidebarSearchWidth = 130.0f;
        float sidebarSearchHeight = 26.0f;
        float sidebarSearchX = x + 10.0f;
        float sidebarSearchY = y + 40.0f;
        float innerContentWidth = contentWidth - 30.0f;
        float moduleListWidth = Math.max(0.0f, innerContentWidth * 0.52f);
        float moduleListX = contentX + 10.0f;
        float moduleListY = headerY + headerHeight + 10.0f;
        float moduleListHeight = contentHeight - headerHeight - 20.0f;
        float settingsX = moduleListX + moduleListWidth + 10.0f;
        float settingsY = moduleListY;
        float settingsWidth = Math.max(0.0f, innerContentWidth - moduleListWidth);
        float settingsHeight = moduleListHeight;
        return new GuiLayout(x, y, width, height, 150.0f, contentX, contentY, contentWidth, contentHeight, headerX, headerY, headerWidth, headerHeight, moduleListX, moduleListY, moduleListWidth, moduleListHeight, settingsX, settingsY, settingsWidth, settingsHeight, sidebarSearchX, sidebarSearchY, sidebarSearchWidth, sidebarSearchHeight);
    }

    private List<Module> getFilteredModuleList(ModuleCategory cat, boolean crossCategorySearch) {
        String query = this.searchText == null ? "" : this.searchText;
        ModuleFilterKey key = new ModuleFilterKey(query, cat, crossCategorySearch);
        if (key.equals(this.cachedModuleFilterKey)) {
            return this.cachedFilteredModules;
        }
        this.cachedModuleFilterKey = key;
        String lower = query.toLowerCase(Locale.ROOT);
        this.cachedFilteredModules = AlphaDLC.getInstance().getModuleRepository().modules().stream().filter(m -> {
            if (query.isEmpty()) {
                return m.getCategory() == cat;
            }
            if (!crossCategorySearch && m.getCategory() != cat) {
                return false;
            }
            return m.getVisibleName().toLowerCase(Locale.ROOT).contains(lower);
        }).sorted(Comparator.comparing(Module::getVisibleName, String.CASE_INSENSITIVE_ORDER)).toList();
        return this.cachedFilteredModules;
    }

    private void updateSearchSuggestions() {
        String q;
        this.searchSuggestions.clear();
        String string = q = this.searchText == null ? "" : this.searchText.trim().toLowerCase(Locale.ROOT);
        if (q.isEmpty()) {
            this.searchSuggestionIndex = -1;
            return;
        }
        AlphaDLC.getInstance().getModuleRepository().modules().stream().map(Module::getVisibleName).filter(Objects::nonNull).distinct().sorted(String.CASE_INSENSITIVE_ORDER).filter(name -> {
            String low = name.toLowerCase(Locale.ROOT);
            return low.contains(q) || low.replace(" ", "").contains(q.replace(" ", ""));
        }).limit(6L).forEach(this.searchSuggestions::add);
        if (this.searchSuggestions.isEmpty()) {
            this.searchSuggestionIndex = -1;
        } else if (this.searchSuggestionIndex < 0 || this.searchSuggestionIndex >= this.searchSuggestions.size()) {
            this.searchSuggestionIndex = 0;
        }
    }

    private void applySearchSuggestion(int index) {
        if (index < 0 || index >= this.searchSuggestions.size()) {
            return;
        }
        this.searchText = this.searchSuggestions.get(index);
        this.searchCursor = this.searchText.length();
        this.clearSearchSelection();
        this.searchSuggestionIndex = index;
    }

    private void syncSearchCursor() {
        this.searchCursor = class_3532.method_15340((int)this.searchCursor, (int)0, (int)this.searchText.length());
    }

    private void clearSearchSelection() {
        this.searchSelectionStart = -1;
        this.searchSelectionEnd = -1;
    }

    private boolean hasSearchSelection() {
        return this.searchSelectionStart != -1 && this.searchSelectionEnd != -1 && this.searchSelectionStart != this.searchSelectionEnd;
    }

    private int getSearchSelectionStart() {
        return Math.min(this.searchSelectionStart, this.searchSelectionEnd);
    }

    private int getSearchSelectionEnd() {
        return Math.max(this.searchSelectionStart, this.searchSelectionEnd);
    }

    private String getSearchSelectedText() {
        return this.searchText.substring(this.getSearchSelectionStart(), this.getSearchSelectionEnd());
    }

    private void deleteSearchSelection() {
        if (!this.hasSearchSelection()) {
            return;
        }
        int start = this.getSearchSelectionStart();
        int end = this.getSearchSelectionEnd();
        this.searchText = this.searchText.substring(0, start) + this.searchText.substring(end);
        this.searchCursor = start;
        this.clearSearchSelection();
    }

    private void replaceSearchText(int start, int end, String replacement) {
        Object next;
        if (start < 0) {
            start = 0;
        }
        if (end > this.searchText.length()) {
            end = this.searchText.length();
        }
        if (start > end) {
            start = end;
        }
        if (((String)(next = this.searchText.substring(0, start) + replacement + this.searchText.substring(end))).length() > 40) {
            next = ((String)next).substring(0, 40);
        }
        this.searchText = next;
        this.searchCursor = Math.min(start + replacement.length(), this.searchText.length());
        this.clearSearchSelection();
        this.searchSuggestionIndex = 0;
    }

    private void selectAllSearchText() {
        this.searchSelectionStart = 0;
        this.searchSelectionEnd = this.searchText.length();
        this.searchCursor = this.searchText.length();
    }

    private int getSearchCursorIndexAt(float mouseX, float textStartX) {
        FontRenderer font = Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM);
        float relativeX = mouseX - textStartX;
        for (int i = 0; i <= this.searchText.length(); ++i) {
            if (!(font.getStringWidth(this.searchText.substring(0, i)) > relativeX)) continue;
            return i;
        }
        return this.searchText.length();
    }

    private void clearSearchField() {
        this.searchText = "";
        this.searchTextScroll = 0.0f;
        this.searchCursor = 0;
        this.clearSearchSelection();
        this.searchSuggestions.clear();
        this.searchSuggestionIndex = -1;
    }

    private boolean handleSearchKeyPressed(int keyCode) {
        this.updateSearchSuggestions();
        if (class_437.method_25441()) {
            switch (keyCode) {
                case 65: {
                    this.selectAllSearchText();
                    return true;
                }
                case 67: {
                    if (this.hasSearchSelection()) {
                        GLFW.glfwSetClipboardString((long)window.getHandle(), (CharSequence)this.getSearchSelectedText());
                    }
                    return true;
                }
                case 88: {
                    if (this.hasSearchSelection()) {
                        GLFW.glfwSetClipboardString((long)window.getHandle(), (CharSequence)this.getSearchSelectedText());
                        this.deleteSearchSelection();
                    }
                    return true;
                }
                case 86: {
                    String clipboard = GLFW.glfwGetClipboardString((long)window.getHandle());
                    if (clipboard != null) {
                        this.deleteSearchSelection();
                        this.replaceSearchText(this.searchCursor, this.searchCursor, clipboard);
                    }
                    return true;
                }
            }
        }
        switch (keyCode) {
            case 264: {
                if (!this.searchSuggestions.isEmpty()) {
                    this.searchSuggestionIndex = (this.searchSuggestionIndex + 1) % this.searchSuggestions.size();
                }
                return true;
            }
            case 265: {
                if (!this.searchSuggestions.isEmpty()) {
                    this.searchSuggestionIndex = (this.searchSuggestionIndex - 1 + this.searchSuggestions.size()) % this.searchSuggestions.size();
                }
                return true;
            }
            case 258: {
                if (!this.searchSuggestions.isEmpty()) {
                    this.applySearchSuggestion(this.searchSuggestionIndex < 0 ? 0 : this.searchSuggestionIndex);
                }
                return true;
            }
            case 259: {
                if (this.hasSearchSelection()) {
                    this.deleteSearchSelection();
                } else if (this.searchCursor > 0) {
                    this.searchText = this.searchText.substring(0, this.searchCursor - 1) + this.searchText.substring(this.searchCursor);
                    --this.searchCursor;
                }
                return true;
            }
            case 261: {
                if (this.hasSearchSelection()) {
                    this.deleteSearchSelection();
                } else if (this.searchCursor < this.searchText.length()) {
                    this.searchText = this.searchText.substring(0, this.searchCursor) + this.searchText.substring(this.searchCursor + 1);
                }
                return true;
            }
            case 263: {
                if (this.searchCursor > 0) {
                    --this.searchCursor;
                }
                if (class_437.method_25442()) {
                    if (this.searchSelectionStart == -1) {
                        this.searchSelectionStart = this.searchCursor + 1;
                    }
                    this.searchSelectionEnd = this.searchCursor;
                } else {
                    this.clearSearchSelection();
                }
                return true;
            }
            case 262: {
                if (this.searchCursor < this.searchText.length()) {
                    ++this.searchCursor;
                }
                if (class_437.method_25442()) {
                    if (this.searchSelectionStart == -1) {
                        this.searchSelectionStart = this.searchCursor - 1;
                    }
                    this.searchSelectionEnd = this.searchCursor;
                } else {
                    this.clearSearchSelection();
                }
                return true;
            }
            case 268: {
                this.searchCursor = 0;
                if (class_437.method_25442()) {
                    this.searchSelectionEnd = this.searchCursor;
                    if (this.searchSelectionStart == -1) {
                        this.searchSelectionStart = this.searchText.length();
                    }
                } else {
                    this.clearSearchSelection();
                }
                return true;
            }
            case 269: {
                this.searchCursor = this.searchText.length();
                if (class_437.method_25442()) {
                    this.searchSelectionEnd = this.searchCursor;
                    if (this.searchSelectionStart == -1) {
                        this.searchSelectionStart = 0;
                    }
                } else {
                    this.clearSearchSelection();
                }
                return true;
            }
            case 256: {
                this.searchFieldFocused = false;
                this.clearSearchField();
                return true;
            }
            case 257: {
                if (!this.searchSuggestions.isEmpty()) {
                    this.applySearchSuggestion(this.searchSuggestionIndex < 0 ? 0 : this.searchSuggestionIndex);
                }
                return true;
            }
        }
        return true;
    }

    private void renderColumn(class_332 context, CategoryColumn column, float mouseX, float mouseY, float progress) {
        float easedProgress;
        class_4587 matrices = context.method_51448();
        float x = column.x;
        float y = column.y;
        column.updateScroll();
        float slideProgress = this.columnSlideAnimations.getOrDefault((Object)column.category, Float.valueOf(0.0f)).floatValue();
        long delay = this.columnSlideDelays.getOrDefault((Object)column.category, 0L);
        long timeSinceOpen = System.currentTimeMillis() - this.openTime;
        if (timeSinceOpen >= delay && slideProgress < 1.0f) {
            slideProgress = Math.min(1.0f, slideProgress + 0.06f);
            this.columnSlideAnimations.put(column.category, Float.valueOf(slideProgress));
        }
        if (slideProgress < 1.0f) {
            float c1 = 1.70158f;
            float c3 = c1 + 1.0f;
            easedProgress = (float)(1.0 + (double)c3 * Math.pow(slideProgress - 1.0f, 3.0) + (double)c1 * Math.pow(slideProgress - 1.0f, 2.0));
        } else {
            easedProgress = 1.0f;
        }
        float slideOffset = (1.0f - easedProgress) * 150.0f;
        float finalY = y + slideOffset;
        float alpha = (float)Math.pow(easedProgress, 0.5);
        matrices.method_22903();
        float finalX = x;
        Calculate.setAlpha(alpha, () -> this.renderColumnContent(context, column, finalX, finalY, mouseX, mouseY, progress));
        matrices.method_22909();
        for (Module module2 : this.expandedModules.keySet()) {
            float currentProgress = this.expandAnimations.getOrDefault(module2, Float.valueOf(0.0f)).floatValue();
            if (!(currentProgress < 1.0f)) continue;
            float speed = 0.08f;
            currentProgress += speed;
            currentProgress = Math.min(1.0f, currentProgress);
            this.expandAnimations.put(module2, Float.valueOf(currentProgress));
        }
        this.expandAnimations.keySet().removeIf(module -> {
            if (!this.expandedModules.containsKey(module)) {
                float currentProgress = this.expandAnimations.get(module).floatValue();
                if (currentProgress > 0.0f) {
                    float speed = 0.12f;
                    currentProgress -= speed;
                    currentProgress = Math.max(0.0f, currentProgress);
                    this.expandAnimations.put((Module)module, Float.valueOf(currentProgress));
                    return currentProgress <= 0.0f;
                }
                return true;
            }
            return false;
        });
    }

    private void renderColumnContent(class_332 context, CategoryColumn column, float x, float y, float mouseX, float mouseY, float progress) {
        class_4587 matrices = context.method_51448();
        if (column.category == ModuleCategory.CUSTOMIZATION) {
            this.renderCustomizationColumn(context, column, x, y, mouseX, mouseY, progress);
            return;
        }
        List<Module> categoryModules = this.getFilteredModuleList(column.category, false);
        float maxVisibleHeight = 198.0f;
        float totalModulesHeight = 0.0f;
        for (Module module : categoryModules) {
            totalModulesHeight += 22.0f;
            if (!this.expandedModules.containsKey(module)) continue;
            List<AbstractSettingComponent> settingComponents = this.expandedModules.get(module);
            float expandProgress = this.expandAnimations.getOrDefault(module, Float.valueOf(0.0f)).floatValue();
            float easedProgress = (float)(1.0 - Math.pow(1.0f - expandProgress, 3.0));
            for (AbstractSettingComponent component : settingComponents) {
                totalModulesHeight += (component.height + 2.0f) * easedProgress;
            }
        }
        float visibleModulesHeight = maxVisibleHeight;
        if (ThemeManager.isLiquidGlassTheme()) {
            Render2D.rectangleWithMask(matrices.method_23760().method_23761(), x, y, 130.0f, 28.0f, 16.0f, 0, KawaseBlur.INSTANCE.getBlurTexture());
        }
        rectangle.render(ShapeProperties.create(matrices, x, y, 130.0, 28.0).round(4.0f).color(ThemeManager.BackgroundGui.getColor()).build());
        String categoryIcon = this.getCategoryIcon(column.category);
        String categoryName = column.category.getReadableName();
        float iconX = x + 10.0f;
        float iconY = y + (28.0f - Fonts.getSize(18, Fonts.Type.ALPHADLC_ICONS).getStringHeight(categoryIcon)) / 2.0f;
        float iconWidth = this.getCategoryColumnIconWidth(column.category, categoryIcon);
        float textX = iconX + iconWidth + 4.0f;
        float textY = y + (28.0f - Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).getStringHeight(categoryName)) / 2.0f;
        this.drawCategoryColumnIcon(context, column.category, categoryIcon, iconX, iconY, ColorAssist.getClientColor());
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, categoryName, textX, textY, ThemeManager.textColor.getColor());
        float panelY = y + 28.0f + 4.0f;
        float panelHeight = visibleModulesHeight + 20.0f;
        if (ThemeManager.isLiquidGlassTheme()) {
            Render2D.rectangleWithMask(matrices.method_23760().method_23761(), x, panelY, 130.0f, panelHeight, 16.0f, 0, KawaseBlur.INSTANCE.getBlurTexture());
        }
        rectangle.render(ShapeProperties.create(matrices, x, panelY, 130.0, panelHeight).round(4.0f).color(ThemeManager.BackgroundGui.getColor()).build());
        float contentY = panelY + 10.0f;
        float contentX = x + 10.0f;
        float contentWidth = 110.0f;
        AlphaDLC.getInstance().getScissorManager().push(matrices.method_23760().method_23761(), contentX, contentY, contentWidth, visibleModulesHeight);
        float moduleY = contentY - column.scrollOffset;
        for (Module module : categoryModules) {
            boolean moduleVisible;
            float currentModuleY = moduleY;
            boolean bl = moduleVisible = currentModuleY + 20.0f > contentY && currentModuleY < contentY + visibleModulesHeight;
            if (moduleVisible) {
                this.renderModule(context, module, contentX, currentModuleY, contentWidth, mouseX, mouseY, progress, contentY, contentY + visibleModulesHeight);
                if (Calculate.isHovered(mouseX, mouseY, contentX, currentModuleY, contentWidth, 20.0)) {
                    this.hoveredModuleName = module.getName();
                }
            }
            moduleY += 22.0f;
            if (!this.expandedModules.containsKey(module)) continue;
            List<AbstractSettingComponent> settingComponents = this.expandedModules.get(module);
            float expandProgress = this.expandAnimations.getOrDefault(module, Float.valueOf(0.0f)).floatValue();
            float easedProgress = (float)(1.0 - Math.pow(1.0f - expandProgress, 3.0));
            for (AbstractSettingComponent component : settingComponents) {
                boolean settingVisible;
                float settingHeight = (component.height + 2.0f) * easedProgress;
                boolean bl2 = settingVisible = moduleY < contentY + visibleModulesHeight && moduleY + settingHeight > contentY;
                if (settingVisible && easedProgress > 0.01f) {
                    component.x = contentX;
                    component.y = moduleY;
                    component.width = contentWidth;
                    matrices.method_22903();
                    Calculate.setAlpha(easedProgress, () -> component.render(context, (int)mouseX, (int)mouseY, 0.0f));
                    matrices.method_22909();
                }
                moduleY += settingHeight;
            }
        }
        AlphaDLC.getInstance().getScissorManager().pop();
        if (totalModulesHeight > visibleModulesHeight) {
            float scrollbarWidth = 2.0f;
            float scrollbarHeight = visibleModulesHeight / totalModulesHeight * visibleModulesHeight;
            float scrollbarY = contentY + column.scrollOffset / totalModulesHeight * visibleModulesHeight;
            rectangle.render(ShapeProperties.create(matrices, x + 130.0f - 10.0f - scrollbarWidth, contentY, scrollbarWidth, visibleModulesHeight).round(1.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), 30.0f)).build());
            rectangle.render(ShapeProperties.create(matrices, x + 130.0f - 10.0f - scrollbarWidth, scrollbarY, scrollbarWidth, scrollbarHeight).round(1.0f).color(ColorAssist.getClientColor(), ColorAssist.getClientColor2(), ColorAssist.getClientColor(), ColorAssist.getClientColor2()).build());
        }
        column.maxScroll = Math.max(0.0f, totalModulesHeight - visibleModulesHeight);
    }

    private void renderCustomizationColumn(class_332 context, CategoryColumn column, float x, float y, float mouseX, float mouseY, float progress) {
        class_4587 matrices = context.method_51448();
        if (ThemeManager.isLiquidGlassTheme()) {
            Render2D.rectangleWithMask(matrices.method_23760().method_23761(), x, y, 130.0f, 28.0f, 16.0f, 0, KawaseBlur.INSTANCE.getBlurTexture());
        }
        rectangle.render(ShapeProperties.create(matrices, x, y, 130.0, 28.0).round(4.0f).color(ThemeManager.BackgroundGui.getColor()).build());
        String categoryIcon = this.getCategoryIcon(column.category);
        String categoryName = column.category.getReadableName();
        float iconX = x + 10.0f;
        float iconY = y + (28.0f - Fonts.getSize(18, Fonts.Type.ALPHADLC_ICONS).getStringHeight(categoryIcon)) / 2.0f;
        float iconWidth = this.getCategoryColumnIconWidth(column.category, categoryIcon);
        float textX = iconX + iconWidth + 4.0f;
        float textY = y + (28.0f - Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).getStringHeight(categoryName)) / 2.0f;
        this.drawCategoryColumnIcon(context, column.category, categoryIcon, iconX, iconY, ColorAssist.getClientColor());
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, categoryName, textX, textY, ThemeManager.textColor.getColor());
        float panelY = y + 28.0f + 4.0f;
        float maxVisibleHeight = 198.0f;
        float panelHeight = maxVisibleHeight + 20.0f;
        if (ThemeManager.isLiquidGlassTheme()) {
            Render2D.rectangleWithMask(matrices.method_23760().method_23761(), x, panelY, 130.0f, panelHeight, 16.0f, 0, KawaseBlur.INSTANCE.getBlurTexture());
        }
        rectangle.render(ShapeProperties.create(matrices, x, panelY, 130.0, panelHeight).round(4.0f).color(ThemeManager.BackgroundGui.getColor()).build());
        float contentY = panelY + 10.0f;
        float contentX = x + 10.0f;
        float contentWidth = 110.0f;
        this.customizationComponent.x = contentX;
        this.customizationComponent.y = contentY;
        this.customizationComponent.width = contentWidth;
        this.customizationComponent.render(context, (int)mouseX, (int)mouseY, 0.0f);
    }

    private void renderModuleSettings(class_332 context, Module module, float x, float y, float width, float mouseX, float mouseY, float progress) {
    }

    private String getSettingValue(Setting setting) {
        return null;
    }

    private void renderModuleLegacy(class_332 context, Module module, float x, float y, float width, float mouseX, float mouseY, float progress) {
        class_4587 matrices = context.method_51448();
        boolean hovered = Calculate.isHovered(mouseX, mouseY, x, y, width, 20.0);
        boolean enabled = module.isState();
        boolean expanded = this.expandedModules.containsKey(module);
        float enabledProgress = this.getModuleEnabledAnimation(module, enabled);
        if (enabled || enabledProgress > 0.01f) {
            float alpha = 0.3f + enabledProgress * 0.5f;
            if (expanded) {
                alpha = Math.min(1.0f, alpha + 0.1f);
            }
            rectangle.render(ShapeProperties.create(matrices, x, y, width, 20.0).round(4.0f).color(ColorAssist.getClientColor(alpha), ColorAssist.getClientColor(alpha), ColorAssist.getClientColor2(alpha), ColorAssist.getClientColor2(alpha)).build());
        } else {
            int bgColor = ThemeManager.BackgroundGui.getColor();
            if (hovered) {
                bgColor = Calculate.applyOpacity(bgColor, 150.0f);
            }
            if (expanded) {
                bgColor = Calculate.applyOpacity(bgColor, 80.0f);
            }
            rectangle.render(ShapeProperties.create(matrices, x, y, width, 20.0).round(4.0f).color(Calculate.applyOpacity(bgColor, 50.0f)).build());
        }
        String moduleName = module.getVisibleName();
        float textX = x + 10.0f;
        FontRenderer moduleFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        float textY = moduleFont.getCenteredY(y, 20.0f, moduleName);
        int moduleTextColor = enabled ? ThemeManager.textColor.getColor() : Calculate.applyOpacity(ThemeManager.textColor.getColor(), 70.0f);
        moduleFont.drawString(matrices, moduleName, textX, textY, moduleTextColor);
        float rightX = x + width - 10.0f;
    }

    private void renderModule(class_332 context, Module module, float x, float y, float width, float mouseX, float mouseY, float progress, float clipTop, float clipBottom) {
        int moduleTextColor;
        class_4587 matrices = context.method_51448();
        long now = System.currentTimeMillis();
        long shakeUntil = this.moduleNoSettingsShakeUntil.getOrDefault(module, 0L);
        float shakeX = 0.0f;
        float shakeColorMix = 0.0f;
        if (shakeUntil > now) {
            float remain = (float)(shakeUntil - now) / 220.0f;
            shakeX = (float)Math.sin((float)now * 0.09f) * (2.0f * remain);
            shakeColorMix = remain;
        }
        boolean hovered = Calculate.isHovered(mouseX, mouseY, x, y, width, 20.0) && mouseY >= clipTop && mouseY <= clipBottom;
        boolean enabled = module.isState();
        boolean selected = module == this.selectedModule;
        float enabledAnim = this.getModuleEnabledAnimation(module, enabled);
        float hoverAnim = this.moduleHoverAnimations.getOrDefault(module, Float.valueOf(0.0f)).floatValue();
        hoverAnim = Calculate.interpolateSmooth(7.0, hoverAnim, hovered ? 1.0f : 0.0f);
        this.moduleHoverAnimations.put(module, Float.valueOf(hoverAnim));
        float selectedAnim = this.moduleSelectAnimations.getOrDefault(module, Float.valueOf(selected ? 1.0f : 0.0f)).floatValue();
        selectedAnim = Calculate.interpolateSmooth(7.0, selectedAnim, selected ? 1.0f : 0.0f);
        this.moduleSelectAnimations.put(module, Float.valueOf(selectedAnim));
        int base = ThemeManager.BackgroundGui.getColor();
        int bg = Calculate.applyOpacity(base, (int)(80.0f + hoverAnim * 34.0f + selectedAnim * 36.0f));
        rectangle.render(ShapeProperties.create(matrices, x, y, width, 20.0).round(6.0f).color(bg).build());
        float accentAnim = enabledAnim;
        if (accentAnim > 0.002f) {
            float accentH = 14.0f * (0.55f + 0.45f * accentAnim);
            float accentY = y + 10.0f - accentH / 2.0f;
            rectangle.render(ShapeProperties.create(matrices, x + 2.0f, accentY, 1.5, accentH).round(1.2f).color(Calculate.applyOpacity(ColorAssist.getClientColor(), (int)(accentAnim * 255.0f))).build());
        }
        String moduleName = module.getVisibleName();
        float textX = x + 10.0f;
        FontRenderer moduleFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        float textY = moduleFont.getCenteredY(y, 20.0f, moduleName);
        int n = moduleTextColor = enabled ? ThemeManager.textColor.getColor() : Calculate.applyOpacity(ThemeManager.textColor.getColor(), (int)(90.0f + hoverAnim * 25.0f));
        if (shakeColorMix > 0.001f) {
            moduleTextColor = ColorAssist.lerp(shakeColorMix, moduleTextColor, ColorAssist.red);
        }
        moduleFont.drawString(matrices, moduleName, textX + shakeX, textY, moduleTextColor);
        float rightX = x + width - 6.0f;
        float dotSize = 3.6f + enabledAnim * 1.2f;
        float dotX = rightX - dotSize;
        float dotY = y + 10.0f - dotSize / 2.0f;
        int dotColor = enabled ? Calculate.applyOpacity(ColorAssist.getClientColor(), (int)(180.0f + enabledAnim * 75.0f)) : Calculate.applyOpacity(ThemeManager.textColor.getColor(), (int)(60.0f + hoverAnim * 50.0f));
        rectangle.render(ShapeProperties.create(matrices, dotX, dotY, dotSize, dotSize).round(4.0f).color(dotColor).build());
        rightX = dotX - 6.0f;
        if (module == this.selectedModule) {
            this.lastModuleX = x;
            this.lastModuleY = y;
            this.lastModuleWidth = width;
        }
    }

    private void triggerNoSettingsFeedback(Module module) {
        this.moduleNoSettingsShakeUntil.put(module, System.currentTimeMillis() + 220L);
        SoundManager.playSound(SoundManager.CRITICAL);
    }

    private void renderSearchField(class_332 context, float mouseX, float mouseY, float progress) {
        class_4587 matrices = context.method_51448();
        List<ModuleCategory> visibleCategories = this.getVisibleCategories();
        float totalWidth = (float)visibleCategories.size() * 130.0f + (float)(visibleCategories.size() - 1) * 2.0f;
        float startX = ((float)window.getScaledWidth() - totalWidth) / 2.0f;
        float startY = (float)(window.getScaledHeight() - 300) / 2.0f;
        float searchWidth = 150.0f;
        float searchHeight = 20.0f;
        float searchX = ((float)window.getScaledWidth() - searchWidth) / 2.0f;
        float searchY = startY + 28.0f + 4.0f + 198.0f + 20.0f + 20.0f;
        if (ThemeManager.isLiquidGlassTheme()) {
            Render2D.rectangleWithMask(matrices.method_23760().method_23761(), searchX - 4.0f, searchY - 2.0f, searchWidth + 8.0f, searchHeight + 4.0f, 16.0f, 0, KawaseBlur.INSTANCE.getBlurTexture());
        }
        rectangle.render(ShapeProperties.create(matrices, searchX - 4.0f, searchY - 2.0f, searchWidth + 8.0f, searchHeight + 4.0f).round(4.0f).color(ThemeManager.BackgroundGui.getColor()).build());
        String displayText = this.searchText.isEmpty() ? "\u041f\u043e\u0438\u0441\u043a \u043c\u043e\u0434\u0443\u043b\u0435\u0439..." : this.searchText;
        int searchTextColor = this.searchText.isEmpty() ? Calculate.applyOpacity(ThemeManager.textColor.getColor(), 50.0f) : ThemeManager.textColor.getColor();
        FontRenderer searchFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        float textY = searchFont.getCenteredY(searchY, searchHeight, displayText);
        searchFont.drawString(matrices, displayText, searchX, textY, searchTextColor);
        if (this.searchFieldFocused && !this.searchText.isEmpty()) {
            float cursorX = searchX + Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).getStringWidth(this.searchText);
            float cursorH = 11.0f;
            float cursorY = searchY + (searchHeight - cursorH) / 2.0f;
            if (System.currentTimeMillis() % 1000L < 500L) {
                rectangle.render(ShapeProperties.create(matrices, cursorX + 2.0f, cursorY, 1.0, cursorH).color(ThemeManager.textColor.getColor()).build());
            }
        }
    }

    private void renderBindingPopup(class_332 context, float progress) {
        class_4587 matrices = context.method_51448();
        String text = this.firstBindKey == -1 ? "\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u043a\u043b\u0430\u0432\u0438\u0448\u0443, \u043a\u043d\u043e\u043f\u043a\u0443 \u043c\u044b\u0448\u0438 \u0438\u043b\u0438 \u043a\u043e\u043b\u0451\u0441\u0438\u043a\u043e" : "\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0432\u0442\u043e\u0440\u0443\u044e \u043a\u043b\u0430\u0432\u0438\u0448\u0443 (Enter \u2014 \u043e\u0434\u043d\u0430 \u043a\u043b\u0430\u0432\u0438\u0448\u0430)";
        String moduleName = this.bindingModule.getVisibleName();
        Object currentBind = "";
        if (this.firstBindKey != -1) {
            currentBind = BindCodes.getDisplayName(this.firstBindKey) + " + ...";
        }
        float padding = 14.0f;
        float textWidth = Math.max(Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM).getStringWidth(text), Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM).getStringWidth(moduleName));
        if (!((String)currentBind).isEmpty()) {
            textWidth = Math.max(textWidth, Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM).getStringWidth((String)currentBind));
        }
        float popupWidth = textWidth + padding * 2.0f + 14.0f;
        float popupHeight = ((String)currentBind).isEmpty() ? 66.0f : 80.0f;
        float centerX = (float)window.getScaledWidth() / 2.0f;
        float centerY = (float)window.getScaledHeight() / 2.0f;
        float scale = 0.92f + progress * 0.08f;
        float popupX = centerX - popupWidth / 2.0f;
        float popupY = centerY - popupHeight / 2.0f;
        ClickGui clickGui = ClickGui.getInstance();
        if (ThemeManager.isLiquidGlassTheme() || clickGui != null && clickGui.shouldRenderBackgroundBlur()) {
            this.drawBlurMaskSafe(matrices, popupX, popupY, popupWidth, popupHeight, 16.0f);
        }
        matrices.method_22903();
        matrices.method_46416(centerX, centerY, 0.0f);
        matrices.method_22905(scale, scale, 1.0f);
        matrices.method_46416(-centerX, -centerY, 0.0f);
        rectangle.render(ShapeProperties.create(matrices, popupX, popupY, popupWidth, popupHeight).round(8.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), (int)(180.0f + progress * 55.0f))).build());
        rectangle.render(ShapeProperties.create(matrices, popupX, popupY, popupWidth, popupHeight).round(8.0f).color(Calculate.applyOpacity(-16777216, (int)(26.0f + progress * 25.0f))).build());
        rectangle.render(ShapeProperties.create(matrices, popupX, popupY, popupWidth, 2.0).round(2.0f).color(Calculate.applyOpacity(ColorAssist.getClientColor(), (int)(140.0f + progress * 110.0f))).build());
        float textY = popupY + padding - 1.0f;
        Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, text, popupX + (popupWidth - Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM).getStringWidth(text)) / 2.0f, textY + 6.0f, Calculate.applyOpacity(ThemeManager.textColor.getColor(), (int)(180.0f + progress * 75.0f)));
        if (!((String)currentBind).isEmpty()) {
            Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, (String)currentBind, popupX + (popupWidth - Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM).getStringWidth((String)currentBind)) / 2.0f, textY + 22.0f, Calculate.applyOpacity(ColorAssist.getClientColor(), (int)(200.0f + progress * 55.0f)));
        }
        float pillW = Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM).getStringWidth(moduleName) + 16.0f;
        float pillH = 16.0f;
        float pillX = popupX + (popupWidth - pillW) / 2.0f;
        float pillY = popupY + popupHeight - padding - pillH + 1.0f;
        rectangle.render(ShapeProperties.create(matrices, pillX, pillY, pillW, pillH).round(6.0f).color(Calculate.applyOpacity(ColorAssist.getClientColor(), (int)(70.0f + progress * 85.0f))).build());
        Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM).drawCenteredBoth(matrices, moduleName, pillX + pillW / 2.0f, pillY, pillH, ThemeManager.textColor.getColor());
        matrices.method_22909();
    }

    private List<ModuleCategory> getVisibleCategories() {
        ArrayList<ModuleCategory> visibleCategories = new ArrayList<ModuleCategory>();
        ClickGui clickGui = ClickGui.getInstance();
        for (ModuleCategory cat : ModuleCategory.values()) {
            if (cat == ModuleCategory.CONFIGS || cat == ModuleCategory.CUSTOMIZATION && clickGui != null && !clickGui.shouldRenderCustomization()) continue;
            visibleCategories.add(cat);
        }
        return visibleCategories;
    }

    private int getCategoryOrderIndex(ModuleCategory targetCategory) {
        List<ModuleCategory> categories = this.getVisibleCategories();
        int index = categories.indexOf((Object)targetCategory);
        return index >= 0 ? index : 0;
    }

    private void drawBlurMaskSafe(class_4587 matrices, float x, float y, float w, float h, float round) {
        if (this.blurMaskFailed) {
            return;
        }
        try {
            Render2D.rectangleWithMask(matrices.method_23760().method_23761(), x, y, w, h, round, 0, KawaseBlur.INSTANCE.getBlurTexture());
        }
        catch (Throwable ignored) {
            this.blurMaskFailed = true;
        }
    }

    private float getCategoryIconWidth(ModuleCategory category, FontRenderer iconFont, String icon) {
        if (category == ModuleCategory.ESP) {
            return 9.0f;
        }
        return iconFont.getStringWidth(icon);
    }

    private float getCategoryColumnIconWidth(ModuleCategory category, String icon) {
        if (category == ModuleCategory.ESP) {
            return 9.0f;
        }
        return Fonts.getSize(18, Fonts.Type.ALPHADLC_ICONS).getStringWidth(icon);
    }

    private void drawCategorySidebarIcon(class_332 context, ModuleCategory category, float iconX, float itemY, float itemHeight, int iconColor) {
        if (category == ModuleCategory.ESP) {
            float iconY = itemY + (itemHeight - 9.0f) / 2.0f;
            BackgroundIcons.drawEspCategory(context, BackgroundIcons.ESP, iconX, iconY, iconColor);
            return;
        }
        Fonts.getSize(16, Fonts.Type.ALPHADLC_ICONS).drawVerticallyCentered(context.method_51448(), this.getCategoryIcon(category), iconX, itemY, itemHeight, iconColor);
    }

    private void drawCategoryColumnIcon(class_332 context, ModuleCategory category, String icon, float iconX, float iconY, int color) {
        if (category == ModuleCategory.ESP) {
            BackgroundIcons.drawEspCategory(context, BackgroundIcons.ESP, iconX, iconY + 6.0f, color);
            return;
        }
        Fonts.getSize(18, Fonts.Type.ALPHADLC_ICONS).drawString(context.method_51448(), icon, iconX, iconY + 6.0f, color);
    }

    private String getCategoryIcon(ModuleCategory category) {
        return switch (category) {
            default -> throw new MatchException(null, null);
            case ModuleCategory.COMBAT -> "b";
            case ModuleCategory.MOVEMENT -> "d";
            case ModuleCategory.RENDER -> "e";
            case ModuleCategory.ESP -> "i";
            case ModuleCategory.PLAYER -> "c";
            case ModuleCategory.MISC -> "f";
            case ModuleCategory.CUSTOMIZATION -> "g";
            case ModuleCategory.CONFIGS -> "h";
        };
    }

    private String getCategoryLabel(ModuleCategory category) {
        if (category == ModuleCategory.CUSTOMIZATION) {
            return "Themes";
        }
        return category.getReadableName();
    }

    private String getKeyName(int keyCode) {
        return BindCodes.getDisplayName(keyCode);
    }

    private boolean handleModuleBindInput(int keyCode) {
        if (this.bindingModule == null) {
            return false;
        }
        if (keyCode == 256) {
            this.bindingModule.setKey(-1);
            this.bindingModule.setSecondKey(-1);
            this.firstBindKey = -1;
            this.bindingModule = null;
            return true;
        }
        if (keyCode == 261 || keyCode == 259) {
            this.bindingModule.setKey(-1);
            this.bindingModule.setSecondKey(-1);
            this.firstBindKey = -1;
            this.bindingModule = null;
            return true;
        }
        if (keyCode == 257 && this.firstBindKey != -1) {
            this.bindingModule.setKey(this.firstBindKey);
            this.bindingModule.setSecondKey(-1);
            this.firstBindKey = -1;
            this.bindingModule = null;
            return true;
        }
        if (!BindCodes.canBind(keyCode)) {
            return true;
        }
        if (this.firstBindKey == -1) {
            this.firstBindKey = keyCode;
            return true;
        }
        this.bindingModule.setKey(this.firstBindKey);
        this.bindingModule.setSecondKey(keyCode);
        this.firstBindKey = -1;
        this.bindingModule = null;
        return true;
    }

    private String getBindText(Module module) {
        int keyCode = module.getKey();
        if (keyCode == -1 || keyCode == 0 || keyCode == -1) {
            return null;
        }
        String firstKey = this.getKeyName(keyCode);
        int secondKey = module.getSecondKey();
        if (secondKey != -1 && secondKey != -1) {
            String secondKeyName = this.getKeyName(secondKey);
            return firstKey + " + " + secondKeyName;
        }
        return firstKey;
    }

    public void openGui() {
        if (Panic.isPanicMode()) {
            return;
        }
        if (!this.initialized) {
            this.initialize();
        }
        this.animation.setDirection(Direction.FORWARDS);
        this.openTime = System.currentTimeMillis();
        SoundManager.playSound(SoundManager.OPEN_GUI);
        this.columnSlideAnimations.clear();
        this.columnSlideDelays.clear();
        int index = 0;
        for (ModuleCategory category : this.categoryColumns.keySet()) {
            this.columnSlideAnimations.put(category, Float.valueOf(0.0f));
            this.columnSlideDelays.put(category, (long)index * 80L);
            ++index;
        }
        mc.method_1507((class_437)this);
    }

    public float getScaleAnimation() {
        float progress = (float)this.animation.getOutput();
        if (this.animation.getDirection() == Direction.FORWARDS && progress > 0.01f) {
            float c1 = 1.70158f;
            float c3 = c1 + 1.0f;
            float eased = (float)(1.0 + (double)c3 * Math.pow(progress - 1.0f, 3.0) + (double)c1 * Math.pow(progress - 1.0f, 2.0));
            return Math.min(eased, 1.15f);
        }
        if (this.animation.getDirection() == Direction.BACKWARDS) {
            return (float)Math.pow(progress, 3.0);
        }
        return progress;
    }

    private float getModuleEnabledAnimation(Module module, boolean enabled) {
        float currentProgress = this.moduleEnabledAnimations.getOrDefault(module, Float.valueOf(enabled ? 1.0f : 0.0f)).floatValue();
        float targetProgress = enabled ? 1.0f : 0.0f;
        if (Math.abs((currentProgress = Calculate.interpolateSmooth(7.0, currentProgress, targetProgress)) - targetProgress) < 0.005f) {
            currentProgress = targetProgress;
        }
        this.moduleEnabledAnimations.put(module, Float.valueOf(currentProgress));
        return currentProgress;
    }

    private void startSettingsMorph(Module module, float rowX, float rowY, float rowWidth) {
        if (module == null) {
            return;
        }
        this.settingsMorphModule = module;
        this.settingsMorphOriginX = rowX;
        this.settingsMorphOriginY = rowY;
        this.settingsMorphOriginW = rowWidth;
        this.settingsMorphOriginH = 20.0f;
    }

    private void openModuleSettings(Module module, float rowX, float rowY, float rowWidth) {
        if (module == null || module.settings().isEmpty()) {
            return;
        }
        if (this.selectedModule != null && this.selectedModule != module) {
            this.expandedModules.remove(this.selectedModule);
        }
        if (this.settingsPanelClosingModule != null) {
            this.expandedModules.remove(this.settingsPanelClosingModule);
            this.settingsPanelClosingModule = null;
        }
        this.selectedModule = module;
        this.displayedSettingsModule = module;
        this.startSettingsMorph(module, rowX, rowY, rowWidth);
        this.settingsPanelAnim = 0.0f;
        this.settingsScrollOffset = 0.0f;
        this.settingsTargetScroll = 0.0f;
        if (!this.expandedModules.containsKey(module)) {
            ArrayList<AbstractSettingComponent> settingComponents = new ArrayList<AbstractSettingComponent>();
            this.settingComponentAdder.addSettingComponent(module.settings(), settingComponents, module);
            this.expandedModules.put(module, settingComponents);
        }
        this.expandAnimations.put(module, Float.valueOf(1.0f));
    }

    private void closeModuleSettings(Module module) {
        if (module == null) {
            return;
        }
        this.expandAnimations.remove(module);
        this.expandedModules.remove(module);
        if (this.selectedModule == module) {
            this.selectedModule = null;
        }
        if (this.displayedSettingsModule == module) {
            this.displayedSettingsModule = null;
        }
        this.settingsPanelClosingModule = null;
        this.settingsPanelAnim = 0.0f;
        this.settingsMorphModule = null;
    }

    private void clearSettingsPanelState() {
        this.selectedModule = null;
        this.displayedSettingsModule = null;
        this.settingsPanelClosingModule = null;
        this.settingsPanelAnim = 0.0f;
        this.settingsMorphModule = null;
        this.settingsScrollOffset = 0.0f;
        this.settingsTargetScroll = 0.0f;
        this.expandedModules.clear();
        this.expandAnimations.clear();
    }

    private boolean isSettingsPanelInteractive() {
        return this.selectedModule != null && this.expandedModules.containsKey(this.selectedModule) && this.settingsPanelAnim > 0.88f;
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        float centerX = (float)window.getScaledWidth() / 2.0f;
        float centerY = (float)window.getScaledHeight() / 2.0f;
        float scale = this.getScaleAnimation() * this.scaleFactor;
        double transformedX = (mouseX - (double)centerX) / (double)scale + (double)centerX;
        double transformedY = (mouseY - (double)centerY) / (double)scale + (double)centerY;
        if (this.bindingModule != null) {
            this.handleModuleBindInput(button);
            return true;
        }
        if (BindCaptureHelper.isCapturing()) {
            if (this.dispatchBindCaptureMouse(transformedX, transformedY, button)) {
                return true;
            }
            return true;
        }
        if (windowManager.mouseClicked(transformedX, transformedY, button)) {
            return true;
        }
        GuiLayout layout = this.getLayout();
        if (this.tooltipVisible && this.hoveredModule != null && Calculate.isHovered(transformedX, transformedY, this.tooltipX, this.tooltipY, this.tooltipW, this.tooltipH)) {
            if (!this.hoveredModule.settings().isEmpty()) {
                this.openModuleSettings(this.hoveredModule, layout.moduleListX, layout.moduleListY, layout.moduleListWidth);
                SoundManager.playSound(SoundManager.CATEGORY_CLICK);
            }
            return true;
        }
        boolean insideGui = Calculate.isHovered(transformedX, transformedY, layout.x, layout.y, layout.width, layout.height);
        if (insideGui) {
            float rowH;
            float pad;
            int clickedIndex;
            if (Calculate.isHovered(transformedX, transformedY, layout.x, layout.y, layout.sidebarWidth, layout.height)) {
                float itemY = layout.sidebarSearchY + layout.sidebarSearchHeight + 12.0f;
                float itemHeight = 22.0f;
                for (ModuleCategory moduleCategory : this.getVisibleCategories()) {
                    if (Calculate.isHovered(transformedX, transformedY, layout.x + 8.0f, itemY, layout.sidebarWidth - 16.0f, itemHeight)) {
                        this.category = moduleCategory;
                        this.searchFieldFocused = false;
                        this.clearSettingsPanelState();
                        this.espPreviewDragging = false;
                        SoundManager.playSound(SoundManager.CATEGORY_CLICK);
                        return true;
                    }
                    itemY += itemHeight + 4.0f;
                }
            }
            if (this.searchSuggestionsAnim > 0.05f && Calculate.isHovered(transformedX, transformedY, this.searchSuggestionsX, this.searchSuggestionsY, this.searchSuggestionsW, this.searchSuggestionsH) && (clickedIndex = (int)((transformedY - (double)this.searchSuggestionsY - (double)(pad = 4.0f)) / (double)(rowH = 16.0f))) >= 0 && clickedIndex < this.searchSuggestions.size()) {
                this.applySearchSuggestion(clickedIndex);
                this.searchFieldFocused = true;
                return true;
            }
            if (Calculate.isHovered(transformedX, transformedY, layout.sidebarSearchX, layout.sidebarSearchY, layout.sidebarSearchWidth, layout.sidebarSearchHeight)) {
                this.searchFieldFocused = true;
                this.searchCursor = this.getSearchCursorIndexAt((float)transformedX, layout.sidebarSearchX + 8.0f - this.searchTextScroll);
                this.clearSearchSelection();
                return true;
            }
            this.searchFieldFocused = false;
            if (this.category == ModuleCategory.CUSTOMIZATION) {
                this.customizationComponent.x = layout.moduleListX + 10.0f;
                this.customizationComponent.y = layout.moduleListY + 10.0f;
                this.customizationComponent.width = layout.moduleListWidth - 20.0f;
                if (this.customizationComponent.mouseClicked(transformedX, transformedY, button)) {
                    SoundManager.playSound(SoundManager.SETTING_INTERACT);
                    return true;
                }
                return true;
            }
            if (this.category == ModuleCategory.ESP) {
                if (button == 0 && Calculate.isHovered(transformedX, transformedY, this.espPreviewX, this.espPreviewY, this.espPreviewW, this.espPreviewH)) {
                    this.espPreviewDragging = true;
                    this.espDragStartX = (float)transformedX;
                    this.espDragStartY = (float)transformedY;
                    this.espDragStartYaw = this.espPreviewYaw;
                    this.espDragStartPitch = this.espPreviewPitch;
                    return true;
                }
                if (this.isEspSettingsPanelHovered(transformedX, transformedY, layout)) {
                    if (this.handleEspSettingsClick(transformedX, transformedY, button, layout)) {
                        return true;
                    }
                    return true;
                }
                return true;
            }
            CategoryColumn column = this.categoryColumns.getOrDefault((Object)this.category, new CategoryColumn(this.category, 0.0f, 0.0f));
            List<Module> categoryModules = this.getFilteredModuleList(this.category, true);
            if (Calculate.isHovered(transformedX, transformedY, layout.moduleListX, layout.moduleListY, layout.moduleListWidth, layout.moduleListHeight)) {
                float moduleY = layout.moduleListY - column.scrollOffset;
                for (Module module : categoryModules) {
                    if (moduleY >= layout.moduleListY - 20.0f && moduleY < layout.moduleListY + layout.moduleListHeight && Calculate.isHovered(transformedX, transformedY, layout.moduleListX, moduleY, layout.moduleListWidth, 20.0)) {
                        if (button == 0) {
                            module.switchState();
                            if (this.expandedModules.containsKey(module)) {
                                this.selectedModule = module;
                            }
                            return true;
                        }
                        if (button == 1) {
                            if (!module.settings().isEmpty()) {
                                if (this.expandedModules.containsKey(module)) {
                                    this.closeModuleSettings(module);
                                    SoundManager.playSound(SoundManager.CLOSE_GUI);
                                } else {
                                    this.openModuleSettings(module, layout.moduleListX, moduleY, layout.moduleListWidth);
                                    SoundManager.playSound(SoundManager.OPEN_GUI);
                                }
                            } else {
                                this.triggerNoSettingsFeedback(module);
                            }
                            return true;
                        }
                        if (button == 2) {
                            this.bindingModule = module;
                            this.selectedModule = module;
                            return true;
                        }
                    }
                    moduleY += 22.0f;
                }
            }
            if (Calculate.isHovered(transformedX, transformedY, layout.settingsX, layout.settingsY, layout.settingsWidth, layout.settingsHeight)) {
                if (this.isSettingsPanelInteractive()) {
                    List<AbstractSettingComponent> settingComponents = this.expandedModules.get(this.selectedModule);
                    float f = layout.settingsX + 10.0f;
                    float contentY = layout.settingsY + 10.0f + 18.0f - this.settingsScrollOffset;
                    float contentWidth = layout.settingsWidth - 20.0f;
                    for (AbstractSettingComponent component : settingComponents) {
                        if (!component.getSetting().isVisible()) continue;
                        component.prepareLayout(contentWidth);
                        component.x = f;
                        component.y = contentY;
                        if (component.mouseClicked(transformedX, transformedY, button)) {
                            SoundManager.playSound(SoundManager.SETTING_INTERACT);
                            return true;
                        }
                        contentY += component.height + 4.0f;
                    }
                }
                return true;
            }
            return true;
        }
        return false;
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        double transformedY;
        float centerX = (float)window.getScaledWidth() / 2.0f;
        float centerY = (float)window.getScaledHeight() / 2.0f;
        float scale = this.getScaleAnimation() * this.scaleFactor;
        double transformedX = (mouseX - (double)centerX) / (double)scale + (double)centerX;
        if (windowManager.mouseReleased(transformedX, transformedY = (mouseY - (double)centerY) / (double)scale + (double)centerY, button)) {
            return true;
        }
        if (this.category == ModuleCategory.ESP && button == 0) {
            this.espPreviewDragging = false;
        }
        GuiLayout layout = this.getLayout();
        if (Calculate.isHovered(transformedX, transformedY, layout.x, layout.y, layout.width, layout.height)) {
            if (this.category == ModuleCategory.CUSTOMIZATION) {
                this.customizationComponent.mouseReleased(transformedX, transformedY, button);
                return true;
            }
            if (this.category == ModuleCategory.ESP) {
                this.espPreviewDragging = false;
                this.syncEspSettingsClickLayout(layout);
                this.handleEspSectionComponentReleaseFromLayout(transformedX, transformedY, button, this.getNameTagsModule());
                this.handleEspSectionComponentReleaseFromLayout(transformedX, transformedY, button, this.getEspSkeletonModule());
                this.handleEspSectionComponentReleaseFromLayout(transformedX, transformedY, button, this.getEspModule());
                return true;
            }
            if (this.selectedModule != null && this.expandedModules.containsKey(this.selectedModule)) {
                List<AbstractSettingComponent> settingComponents = this.expandedModules.get(this.selectedModule);
                float contentX = layout.settingsX + 10.0f;
                float contentY = layout.settingsY + 10.0f + 18.0f - this.settingsScrollOffset;
                float contentWidth = layout.settingsWidth - 20.0f;
                for (AbstractSettingComponent component : settingComponents) {
                    if (!component.getSetting().isVisible()) continue;
                    component.prepareLayout(contentWidth);
                    component.x = contentX;
                    component.y = contentY;
                    component.mouseReleased(transformedX, transformedY, button);
                    contentY += component.height + 4.0f;
                }
            }
            return true;
        }
        return false;
    }

    public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        double transformedDeltaY;
        double transformedDeltaX;
        double transformedY;
        float centerX = (float)window.getScaledWidth() / 2.0f;
        float centerY = (float)window.getScaledHeight() / 2.0f;
        float scale = this.getScaleAnimation() * this.scaleFactor;
        double transformedX = (mouseX - (double)centerX) / (double)scale + (double)centerX;
        if (windowManager.mouseDragged(transformedX, transformedY = (mouseY - (double)centerY) / (double)scale + (double)centerY, button, transformedDeltaX = deltaX / (double)scale, transformedDeltaY = deltaY / (double)scale)) {
            return true;
        }
        GuiLayout layout = this.getLayout();
        if (Calculate.isHovered(transformedX, transformedY, layout.x, layout.y, layout.width, layout.height)) {
            if (this.category == ModuleCategory.CUSTOMIZATION) {
                return this.customizationComponent.mouseDragged(transformedX, transformedY, button, transformedDeltaX, transformedDeltaY);
            }
            if (this.category == ModuleCategory.ESP) {
                if (this.espPreviewDragging) {
                    float yaw = this.espDragStartYaw - (float)(transformedX - (double)this.espDragStartX) * 1.2f;
                    this.espPreviewYaw = (yaw % 360.0f + 360.0f) % 360.0f;
                    this.espPreviewPitch = class_3532.method_15363((float)(this.espDragStartPitch + (float)(transformedY - (double)this.espDragStartY) * 0.8f), (float)-60.0f, (float)60.0f);
                    return true;
                }
                this.syncEspSettingsClickLayout(layout);
                if (this.handleEspSectionComponentDragFromLayout(transformedX, transformedY, button, transformedDeltaX, transformedDeltaY, this.getNameTagsModule())) {
                    return true;
                }
                if (this.handleEspSectionComponentDragFromLayout(transformedX, transformedY, button, transformedDeltaX, transformedDeltaY, this.getEspSkeletonModule())) {
                    return true;
                }
                if (this.handleEspSectionComponentDragFromLayout(transformedX, transformedY, button, transformedDeltaX, transformedDeltaY, this.getEspModule())) {
                    return true;
                }
                return true;
            }
            if (this.selectedModule != null && this.expandedModules.containsKey(this.selectedModule)) {
                List<AbstractSettingComponent> settingComponents = this.expandedModules.get(this.selectedModule);
                float contentX = layout.settingsX + 10.0f;
                float contentY = layout.settingsY + 10.0f + 18.0f - this.settingsScrollOffset;
                float contentWidth = layout.settingsWidth - 20.0f;
                for (AbstractSettingComponent component : settingComponents) {
                    if (!component.getSetting().isVisible()) continue;
                    component.prepareLayout(contentWidth);
                    component.x = contentX;
                    component.y = contentY;
                    if (component.mouseDragged(transformedX, transformedY, button, transformedDeltaX, transformedDeltaY)) {
                        return true;
                    }
                    contentY += component.height + 4.0f;
                }
            }
            return true;
        }
        return false;
    }

    public boolean method_25401(double mouseX, double mouseY, double horizontal, double vertical) {
        double transformedY;
        if (this.bindingModule != null && vertical != 0.0) {
            this.handleModuleBindInput(vertical > 0.0 ? 998 : 999);
            return true;
        }
        if (BindCaptureHelper.isCapturing() && vertical != 0.0) {
            return BindCaptureHelper.handleScroll(vertical);
        }
        if (this.resizeModeActive) {
            float[] levels = new float[]{0.5f, 0.7f, 0.85f, 1.0f, 1.3333f, 1.7f};
            int currentLevel = 3;
            for (int i = 0; i < levels.length; ++i) {
                if (!(Math.abs(this.scaleFactor - levels[i]) < 0.05f)) continue;
                currentLevel = i;
                break;
            }
            int newLevel = currentLevel + (vertical > 0.0 ? 1 : -1);
            if ((newLevel = Math.max(0, Math.min(5, newLevel))) != currentLevel) {
                this.scaleFactor = levels[newLevel];
                return true;
            }
        }
        float centerX = (float)window.getScaledWidth() / 2.0f;
        float centerY = (float)window.getScaledHeight() / 2.0f;
        float scale = this.getScaleAnimation() * this.scaleFactor;
        double transformedX = (mouseX - (double)centerX) / (double)scale + (double)centerX;
        if (windowManager.mouseScrolled(transformedX, transformedY = (mouseY - (double)centerY) / (double)scale + (double)centerY, vertical)) {
            return true;
        }
        GuiLayout layout = this.getLayout();
        if (Calculate.isHovered(transformedX, transformedY, layout.x, layout.y, layout.width, layout.height)) {
            if (this.category == ModuleCategory.CUSTOMIZATION) {
                return this.customizationComponent.mouseScrolled(transformedX, transformedY, vertical);
            }
            if (this.category == ModuleCategory.ESP) {
                if (this.isEspSettingsPanelHovered(transformedX, transformedY, layout)) {
                    this.settingsTargetScroll -= (float)vertical * 20.0f;
                    this.settingsTargetScroll = class_3532.method_15363((float)this.settingsTargetScroll, (float)0.0f, (float)this.settingsMaxScroll);
                }
                return true;
            }
            if (Calculate.isHovered(transformedX, transformedY, layout.moduleListX, layout.moduleListY, layout.moduleListWidth, layout.moduleListHeight)) {
                CategoryColumn column = this.categoryColumns.getOrDefault((Object)this.category, new CategoryColumn(this.category, 0.0f, 0.0f));
                column.targetScrollOffset -= (float)vertical * 20.0f;
                column.targetScrollOffset = class_3532.method_15363((float)column.targetScrollOffset, (float)0.0f, (float)column.maxScroll);
                this.categoryColumns.put(this.category, column);
                return true;
            }
            if (Calculate.isHovered(transformedX, transformedY, layout.settingsX, layout.settingsY, layout.settingsWidth, layout.settingsHeight)) {
                this.settingsTargetScroll -= (float)vertical * 20.0f;
                this.settingsTargetScroll = class_3532.method_15363((float)this.settingsTargetScroll, (float)0.0f, (float)this.settingsMaxScroll);
                return true;
            }
            return true;
        }
        return false;
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (this.bindingModule != null) {
            return this.handleModuleBindInput(keyCode);
        }
        if (BindCaptureHelper.isCapturing()) {
            return BindCaptureHelper.handleKey(keyCode, scanCode);
        }
        if (this.customizationComponent.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        for (List<AbstractSettingComponent> settingComponents : this.expandedModules.values()) {
            for (AbstractSettingComponent component : settingComponents) {
                if (!component.keyPressed(keyCode, scanCode, modifiers)) continue;
                return true;
            }
        }
        if (this.category == ModuleCategory.ESP) {
            Module espModule = this.getEspModule();
            Module skeletonModule = this.getEspSkeletonModule();
            Module nameTagsModule = this.getNameTagsModule();
            if (espModule != null) {
                for (AbstractSettingComponent component : this.getEspSettingsComponents(espModule)) {
                    if (!component.keyPressed(keyCode, scanCode, modifiers)) continue;
                    return true;
                }
            }
            if (skeletonModule != null) {
                for (AbstractSettingComponent component : this.getEspSettingsComponents(skeletonModule)) {
                    if (!component.keyPressed(keyCode, scanCode, modifiers)) continue;
                    return true;
                }
            }
            if (nameTagsModule != null) {
                for (AbstractSettingComponent component : this.getEspSettingsComponents(nameTagsModule)) {
                    if (!component.keyPressed(keyCode, scanCode, modifiers)) continue;
                    return true;
                }
            }
        }
        if (this.searchFieldFocused) {
            return this.handleSearchKeyPressed(keyCode);
        }
        if (keyCode == 256 && this.method_25422() && !BindCaptureHelper.isCapturing() && this.bindingModule == null) {
            this.method_25419();
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    private boolean dispatchBindCaptureMouse(double mouseX, double mouseY, int button) {
        GuiLayout layout;
        if (this.category == ModuleCategory.ESP) {
            layout = this.getLayout();
            this.syncEspSettingsClickLayout(layout);
            Module espModule = this.getEspModule();
            Module skeletonModule = this.getEspSkeletonModule();
            Module nameTagsModule = this.getNameTagsModule();
            if (this.tryClickEspSectionBind(mouseX, mouseY, button, this.nameTagsSectionLayout, nameTagsModule)) {
                return true;
            }
            if (this.tryClickEspSectionBind(mouseX, mouseY, button, this.skeletonSectionLayout, skeletonModule)) {
                return true;
            }
            if (this.tryClickEspSectionBind(mouseX, mouseY, button, this.espSectionLayout, espModule)) {
                return true;
            }
            if (this.handleEspSectionComponentClickFromLayout(mouseX, mouseY, button, nameTagsModule)) {
                return true;
            }
            if (this.handleEspSectionComponentClickFromLayout(mouseX, mouseY, button, skeletonModule)) {
                return true;
            }
            if (this.handleEspSectionComponentClickFromLayout(mouseX, mouseY, button, espModule)) {
                return true;
            }
        }
        if (this.selectedModule != null && this.expandedModules.containsKey(this.selectedModule)) {
            layout = this.getLayout();
            float contentX = layout.settingsX + 10.0f;
            float contentY = layout.settingsY + 10.0f + 18.0f - this.settingsScrollOffset;
            float contentWidth = layout.settingsWidth - 20.0f;
            for (AbstractSettingComponent component : this.expandedModules.get(this.selectedModule)) {
                BindComponent bindComponent;
                if (!component.getSetting().isVisible()) continue;
                component.prepareLayout(contentWidth);
                component.x = contentX;
                component.y = contentY;
                if (component instanceof BindComponent && (bindComponent = (BindComponent)component).mouseClicked(mouseX, mouseY, button)) {
                    return true;
                }
                contentY += component.height + 4.0f;
            }
        }
        return BindCaptureHelper.handleMouse(button);
    }

    public boolean method_25400(char chr, int modifiers) {
        if (this.customizationComponent.charTyped(chr, modifiers)) {
            return true;
        }
        for (List<AbstractSettingComponent> settingComponents : this.expandedModules.values()) {
            for (AbstractSettingComponent component : settingComponents) {
                if (!component.charTyped(chr, modifiers)) continue;
                return true;
            }
        }
        if (this.category == ModuleCategory.ESP) {
            Module espModule = this.getEspModule();
            Module skeletonModule = this.getEspSkeletonModule();
            Module nameTagsModule = this.getNameTagsModule();
            if (espModule != null) {
                for (AbstractSettingComponent component : this.getEspSettingsComponents(espModule)) {
                    if (!component.charTyped(chr, modifiers)) continue;
                    return true;
                }
            }
            if (skeletonModule != null) {
                for (AbstractSettingComponent component : this.getEspSettingsComponents(skeletonModule)) {
                    if (!component.charTyped(chr, modifiers)) continue;
                    return true;
                }
            }
            if (nameTagsModule != null) {
                for (AbstractSettingComponent component : this.getEspSettingsComponents(nameTagsModule)) {
                    if (!component.charTyped(chr, modifiers)) continue;
                    return true;
                }
            }
        }
        if (this.searchFieldFocused) {
            if (!Character.isISOControl(chr)) {
                this.deleteSearchSelection();
                if (this.searchText.length() < 40) {
                    this.searchText = this.searchText.substring(0, this.searchCursor) + chr + this.searchText.substring(this.searchCursor);
                    ++this.searchCursor;
                    this.searchSuggestionIndex = 0;
                }
                return true;
            }
            return true;
        }
        return super.method_25400(chr, modifiers);
    }

    public boolean method_25421() {
        return false;
    }

    public void method_25419() {
        this.animation.setDirection(Direction.BACKWARDS);
        SoundManager.playSound(SoundManager.CLOSE_GUI);
    }

    public void setCategory(ModuleCategory category) {
        this.category = category;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setResizeModeActive(boolean resizeModeActive) {
        this.resizeModeActive = resizeModeActive;
    }

    public void setLastClickTime(long lastClickTime) {
        this.lastClickTime = lastClickTime;
    }

    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public void setCategoryColumns(Map<ModuleCategory, CategoryColumn> categoryColumns) {
        this.categoryColumns = categoryColumns;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public void setHoveredModuleName(String hoveredModuleName) {
        this.hoveredModuleName = hoveredModuleName;
    }

    public void setBindingModule(Module bindingModule) {
        this.bindingModule = bindingModule;
    }

    public void setFirstBindKey(int firstBindKey) {
        this.firstBindKey = firstBindKey;
    }

    public void setSelectedModule(Module selectedModule) {
        this.selectedModule = selectedModule;
    }

    public void setHoveredModule(Module hoveredModule) {
        this.hoveredModule = hoveredModule;
    }

    public void setTooltipVisible(boolean tooltipVisible) {
        this.tooltipVisible = tooltipVisible;
    }

    public void setTooltipX(float tooltipX) {
        this.tooltipX = tooltipX;
    }

    public void setTooltipY(float tooltipY) {
        this.tooltipY = tooltipY;
    }

    public void setTooltipW(float tooltipW) {
        this.tooltipW = tooltipW;
    }

    public void setTooltipH(float tooltipH) {
        this.tooltipH = tooltipH;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setSearchFieldFocused(boolean searchFieldFocused) {
        this.searchFieldFocused = searchFieldFocused;
    }

    public void setSearchTextScroll(float searchTextScroll) {
        this.searchTextScroll = searchTextScroll;
    }

    public void setSearchCursor(int searchCursor) {
        this.searchCursor = searchCursor;
    }

    public void setSearchSelectionStart(int searchSelectionStart) {
        this.searchSelectionStart = searchSelectionStart;
    }

    public void setSearchSelectionEnd(int searchSelectionEnd) {
        this.searchSelectionEnd = searchSelectionEnd;
    }

    public void setSearchSuggestionsAnim(float searchSuggestionsAnim) {
        this.searchSuggestionsAnim = searchSuggestionsAnim;
    }

    public void setSearchSuggestionIndex(int searchSuggestionIndex) {
        this.searchSuggestionIndex = searchSuggestionIndex;
    }

    public void setSearchSuggestionsX(float searchSuggestionsX) {
        this.searchSuggestionsX = searchSuggestionsX;
    }

    public void setSearchSuggestionsY(float searchSuggestionsY) {
        this.searchSuggestionsY = searchSuggestionsY;
    }

    public void setSearchSuggestionsW(float searchSuggestionsW) {
        this.searchSuggestionsW = searchSuggestionsW;
    }

    public void setSearchSuggestionsH(float searchSuggestionsH) {
        this.searchSuggestionsH = searchSuggestionsH;
    }

    public void setEspPreviewX(float espPreviewX) {
        this.espPreviewX = espPreviewX;
    }

    public void setEspPreviewY(float espPreviewY) {
        this.espPreviewY = espPreviewY;
    }

    public void setEspPreviewW(float espPreviewW) {
        this.espPreviewW = espPreviewW;
    }

    public void setEspPreviewH(float espPreviewH) {
        this.espPreviewH = espPreviewH;
    }

    public void setEspPreviewDragging(boolean espPreviewDragging) {
        this.espPreviewDragging = espPreviewDragging;
    }

    public void setEspPreviewYaw(float espPreviewYaw) {
        this.espPreviewYaw = espPreviewYaw;
    }

    public void setEspPreviewPitch(float espPreviewPitch) {
        this.espPreviewPitch = espPreviewPitch;
    }

    public void setEspDragStartX(float espDragStartX) {
        this.espDragStartX = espDragStartX;
    }

    public void setEspDragStartY(float espDragStartY) {
        this.espDragStartY = espDragStartY;
    }

    public void setEspDragStartYaw(float espDragStartYaw) {
        this.espDragStartYaw = espDragStartYaw;
    }

    public void setEspDragStartPitch(float espDragStartPitch) {
        this.espDragStartPitch = espDragStartPitch;
    }

    public void setEspPreviewEntity(class_1309 espPreviewEntity) {
        this.espPreviewEntity = espPreviewEntity;
    }

    public void setEspSettingsLayoutReady(boolean espSettingsLayoutReady) {
        this.espSettingsLayoutReady = espSettingsLayoutReady;
    }

    public void setExpandedModules(Map<Module, List<AbstractSettingComponent>> expandedModules) {
        this.expandedModules = expandedModules;
    }

    public void setExpandAnimations(Map<Module, Float> expandAnimations) {
        this.expandAnimations = expandAnimations;
    }

    public void setSettingsScrollOffset(float settingsScrollOffset) {
        this.settingsScrollOffset = settingsScrollOffset;
    }

    public void setSettingsTargetScroll(float settingsTargetScroll) {
        this.settingsTargetScroll = settingsTargetScroll;
    }

    public void setSettingsMaxScroll(float settingsMaxScroll) {
        this.settingsMaxScroll = settingsMaxScroll;
    }

    public void setSettingsPanelAnim(float settingsPanelAnim) {
        this.settingsPanelAnim = settingsPanelAnim;
    }

    public void setLastCategory(ModuleCategory lastCategory) {
        this.lastCategory = lastCategory;
    }

    public void setCategoryFade(float categoryFade) {
        this.categoryFade = categoryFade;
    }

    public void setCategorySlideOffset(float categorySlideOffset) {
        this.categorySlideOffset = categorySlideOffset;
    }

    public void setCategorySlideDirection(float categorySlideDirection) {
        this.categorySlideDirection = categorySlideDirection;
    }

    public void setModuleEnabledAnimations(Map<Module, Float> moduleEnabledAnimations) {
        this.moduleEnabledAnimations = moduleEnabledAnimations;
    }

    public void setSidebarSearchFocusAnim(float sidebarSearchFocusAnim) {
        this.sidebarSearchFocusAnim = sidebarSearchFocusAnim;
    }

    public void setColumnSlideAnimations(Map<ModuleCategory, Float> columnSlideAnimations) {
        this.columnSlideAnimations = columnSlideAnimations;
    }

    public void setColumnSlideDelays(Map<ModuleCategory, Long> columnSlideDelays) {
        this.columnSlideDelays = columnSlideDelays;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public void setCustomizationComponent(CustomizationComponent customizationComponent) {
        this.customizationComponent = customizationComponent;
    }

    public void setSettingComponentAdder(SettingComponentAdder settingComponentAdder) {
        this.settingComponentAdder = settingComponentAdder;
    }

    public void setLastMouseX(int lastMouseX) {
        this.lastMouseX = lastMouseX;
    }

    public void setLastMouseY(int lastMouseY) {
        this.lastMouseY = lastMouseY;
    }

    public void setCurrentHoveredModule(String currentHoveredModule) {
        this.currentHoveredModule = currentHoveredModule;
    }

    public void setCurrentHoveredComponent(ModuleComponent currentHoveredComponent) {
        this.currentHoveredComponent = currentHoveredComponent;
    }

    public void setLastModuleX(float lastModuleX) {
        this.lastModuleX = lastModuleX;
    }

    public void setLastModuleY(float lastModuleY) {
        this.lastModuleY = lastModuleY;
    }

    public void setLastModuleWidth(float lastModuleWidth) {
        this.lastModuleWidth = lastModuleWidth;
    }

    public void setDisplayedSettingsModule(Module displayedSettingsModule) {
        this.displayedSettingsModule = displayedSettingsModule;
    }

    public void setSettingsPanelClosingModule(Module settingsPanelClosingModule) {
        this.settingsPanelClosingModule = settingsPanelClosingModule;
    }

    public void setSettingsMorphModule(Module settingsMorphModule) {
        this.settingsMorphModule = settingsMorphModule;
    }

    public void setSettingsMorphOriginX(float settingsMorphOriginX) {
        this.settingsMorphOriginX = settingsMorphOriginX;
    }

    public void setSettingsMorphOriginY(float settingsMorphOriginY) {
        this.settingsMorphOriginY = settingsMorphOriginY;
    }

    public void setSettingsMorphOriginW(float settingsMorphOriginW) {
        this.settingsMorphOriginW = settingsMorphOriginW;
    }

    public void setSettingsMorphOriginH(float settingsMorphOriginH) {
        this.settingsMorphOriginH = settingsMorphOriginH;
    }

    public void setBlurMaskFailed(boolean blurMaskFailed) {
        this.blurMaskFailed = blurMaskFailed;
    }

    public void setLastSearchText(String lastSearchText) {
        this.lastSearchText = lastSearchText;
    }

    public void setCachedModuleFilterKey(ModuleFilterKey cachedModuleFilterKey) {
        this.cachedModuleFilterKey = cachedModuleFilterKey;
    }

    public void setCachedFilteredModules(List<Module> cachedFilteredModules) {
        this.cachedFilteredModules = cachedFilteredModules;
    }

    public List<AbstractComponent> getComponents() {
        return this.components;
    }

    public PanelManager getPanelManager() {
        return this.panelManager;
    }

    public GifTextureRenderer getWaitGifRenderer() {
        return this.waitGifRenderer;
    }

    public EaseBackIn getAnimation() {
        return this.animation;
    }

    public ModuleCategory getCategory() {
        return this.category;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Random getRandom() {
        return this.random;
    }

    public boolean isResizeModeActive() {
        return this.resizeModeActive;
    }

    public long getLastClickTime() {
        return this.lastClickTime;
    }

    public float getScaleFactor() {
        return this.scaleFactor;
    }

    public Map<ModuleCategory, CategoryColumn> getCategoryColumns() {
        return this.categoryColumns;
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public String getHoveredModuleName() {
        return this.hoveredModuleName;
    }

    public Module getBindingModule() {
        return this.bindingModule;
    }

    public int getFirstBindKey() {
        return this.firstBindKey;
    }

    public Module getSelectedModule() {
        return this.selectedModule;
    }

    public Module getHoveredModule() {
        return this.hoveredModule;
    }

    public boolean isTooltipVisible() {
        return this.tooltipVisible;
    }

    public float getTooltipX() {
        return this.tooltipX;
    }

    public float getTooltipY() {
        return this.tooltipY;
    }

    public float getTooltipW() {
        return this.tooltipW;
    }

    public float getTooltipH() {
        return this.tooltipH;
    }

    public String getSearchText() {
        return this.searchText;
    }

    public boolean isSearchFieldFocused() {
        return this.searchFieldFocused;
    }

    public float getSearchTextScroll() {
        return this.searchTextScroll;
    }

    public int getSearchCursor() {
        return this.searchCursor;
    }

    public float getSearchSuggestionsAnim() {
        return this.searchSuggestionsAnim;
    }

    public int getSearchSuggestionIndex() {
        return this.searchSuggestionIndex;
    }

    public List<String> getSearchSuggestions() {
        return this.searchSuggestions;
    }

    public float getSearchSuggestionsX() {
        return this.searchSuggestionsX;
    }

    public float getSearchSuggestionsY() {
        return this.searchSuggestionsY;
    }

    public float getSearchSuggestionsW() {
        return this.searchSuggestionsW;
    }

    public float getSearchSuggestionsH() {
        return this.searchSuggestionsH;
    }

    public float getEspPreviewX() {
        return this.espPreviewX;
    }

    public float getEspPreviewY() {
        return this.espPreviewY;
    }

    public float getEspPreviewW() {
        return this.espPreviewW;
    }

    public float getEspPreviewH() {
        return this.espPreviewH;
    }

    public boolean isEspPreviewDragging() {
        return this.espPreviewDragging;
    }

    public float getEspPreviewYaw() {
        return this.espPreviewYaw;
    }

    public float getEspPreviewPitch() {
        return this.espPreviewPitch;
    }

    public float getEspDragStartX() {
        return this.espDragStartX;
    }

    public float getEspDragStartY() {
        return this.espDragStartY;
    }

    public float getEspDragStartYaw() {
        return this.espDragStartYaw;
    }

    public float getEspDragStartPitch() {
        return this.espDragStartPitch;
    }

    public UUID getEspPreviewUuid() {
        return this.espPreviewUuid;
    }

    public String getEspPreviewName() {
        return this.espPreviewName;
    }

    public Map<Module, List<AbstractSettingComponent>> getEspSettingsCache() {
        return this.espSettingsCache;
    }

    public Map<Module, Integer> getEspSettingsCacheVersion() {
        return this.espSettingsCacheVersion;
    }

    public EspSectionLayout getEspSectionLayout() {
        return this.espSectionLayout;
    }

    public EspSectionLayout getSkeletonSectionLayout() {
        return this.skeletonSectionLayout;
    }

    public EspSectionLayout getNameTagsSectionLayout() {
        return this.nameTagsSectionLayout;
    }

    public boolean isEspSettingsLayoutReady() {
        return this.espSettingsLayoutReady;
    }

    public Map<Module, List<AbstractSettingComponent>> getExpandedModules() {
        return this.expandedModules;
    }

    public Map<Module, Float> getExpandAnimations() {
        return this.expandAnimations;
    }

    public float getSettingsScrollOffset() {
        return this.settingsScrollOffset;
    }

    public float getSettingsTargetScroll() {
        return this.settingsTargetScroll;
    }

    public float getSettingsMaxScroll() {
        return this.settingsMaxScroll;
    }

    public float getSettingsPanelAnim() {
        return this.settingsPanelAnim;
    }

    public ModuleCategory getLastCategory() {
        return this.lastCategory;
    }

    public float getCategoryFade() {
        return this.categoryFade;
    }

    public float getCategorySlideOffset() {
        return this.categorySlideOffset;
    }

    public float getCategorySlideDirection() {
        return this.categorySlideDirection;
    }

    public Map<ModuleCategory, Float> getCategorySelectAnimations() {
        return this.categorySelectAnimations;
    }

    public Map<Module, Float> getModuleEnabledAnimations() {
        return this.moduleEnabledAnimations;
    }

    public Map<Module, Float> getModuleHoverAnimations() {
        return this.moduleHoverAnimations;
    }

    public Map<Module, Float> getModuleSelectAnimations() {
        return this.moduleSelectAnimations;
    }

    public Map<Module, Long> getModuleNoSettingsShakeUntil() {
        return this.moduleNoSettingsShakeUntil;
    }

    public float getSidebarSearchFocusAnim() {
        return this.sidebarSearchFocusAnim;
    }

    public Map<ModuleCategory, Float> getColumnSlideAnimations() {
        return this.columnSlideAnimations;
    }

    public Map<ModuleCategory, Long> getColumnSlideDelays() {
        return this.columnSlideDelays;
    }

    public long getOpenTime() {
        return this.openTime;
    }

    public CustomizationComponent getCustomizationComponent() {
        return this.customizationComponent;
    }

    public SettingComponentAdder getSettingComponentAdder() {
        return this.settingComponentAdder;
    }

    public int getLastMouseX() {
        return this.lastMouseX;
    }

    public int getLastMouseY() {
        return this.lastMouseY;
    }

    public String getCurrentHoveredModule() {
        return this.currentHoveredModule;
    }

    public ModuleComponent getCurrentHoveredComponent() {
        return this.currentHoveredComponent;
    }

    public float getLastModuleX() {
        return this.lastModuleX;
    }

    public float getLastModuleY() {
        return this.lastModuleY;
    }

    public float getLastModuleWidth() {
        return this.lastModuleWidth;
    }

    public Module getDisplayedSettingsModule() {
        return this.displayedSettingsModule;
    }

    public Module getSettingsPanelClosingModule() {
        return this.settingsPanelClosingModule;
    }

    public Module getSettingsMorphModule() {
        return this.settingsMorphModule;
    }

    public float getSettingsMorphOriginX() {
        return this.settingsMorphOriginX;
    }

    public float getSettingsMorphOriginY() {
        return this.settingsMorphOriginY;
    }

    public float getSettingsMorphOriginW() {
        return this.settingsMorphOriginW;
    }

    public float getSettingsMorphOriginH() {
        return this.settingsMorphOriginH;
    }

    public boolean isBlurMaskFailed() {
        return this.blurMaskFailed;
    }

    public String getLastSearchText() {
        return this.lastSearchText;
    }

    public ModuleFilterKey getCachedModuleFilterKey() {
        return this.cachedModuleFilterKey;
    }

    public List<Module> getCachedFilteredModules() {
        return this.cachedFilteredModules;
    }

    static {
        activeClickGuiInputs = new ArrayList<ClickGuiInput>();
    }

    public static interface ClickGuiInput {
        public void setTyping(boolean var1);

        public boolean isTyping();
    }

    public static interface ClearableClickGuiInput
    extends ClickGuiInput {
        public void clearText();
    }

    private static class CategoryColumn {
        ModuleCategory category;
        float x;
        float y;
        float scrollOffset = 0.0f;
        float targetScrollOffset = 0.0f;
        float maxScroll = 0.0f;
        EaseBackIn scrollAnimation = new EaseBackIn(200, 1.0, 1.5f);

        CategoryColumn(ModuleCategory category, float x, float y) {
            this.category = category;
            this.x = x;
            this.y = y;
        }

        void updateScroll() {
            this.scrollAnimation.setDirection(Direction.FORWARDS);
            this.scrollOffset = Math.abs(this.scrollOffset - this.targetScrollOffset) > 0.1f ? Calculate.interpolateSmooth(5.0, this.scrollOffset, this.targetScrollOffset) : this.targetScrollOffset;
        }
    }

    private static class EspSectionLayout {
        float sectionStartY = -1.0f;
        float sectionEndY = -1.0f;
        float headerX;
        float headerY;
        float headerW;
        float headerH;
        float toggleX;
        float toggleY;
        float toggleW;
        float toggleH;
        float bindPillX;
        float bindPillY;
        float bindPillW;
        float bindPillH;
        String sectionTitle = "";

        private EspSectionLayout() {
        }
    }

    private record ModuleFilterKey(String query, ModuleCategory category, boolean crossCategorySearch) {
    }

    private static class GuiLayout {
        final float x;
        final float y;
        final float width;
        final float height;
        final float sidebarWidth;
        final float contentX;
        final float contentY;
        final float contentWidth;
        final float contentHeight;
        final float headerX;
        final float headerY;
        final float headerWidth;
        final float headerHeight;
        final float moduleListX;
        final float moduleListY;
        final float moduleListWidth;
        final float moduleListHeight;
        final float settingsX;
        final float settingsY;
        final float settingsWidth;
        final float settingsHeight;
        final float sidebarSearchX;
        final float sidebarSearchY;
        final float sidebarSearchWidth;
        final float sidebarSearchHeight;

        GuiLayout(float x, float y, float width, float height, float sidebarWidth, float contentX, float contentY, float contentWidth, float contentHeight, float headerX, float headerY, float headerWidth, float headerHeight, float moduleListX, float moduleListY, float moduleListWidth, float moduleListHeight, float settingsX, float settingsY, float settingsWidth, float settingsHeight, float sidebarSearchX, float sidebarSearchY, float sidebarSearchWidth, float sidebarSearchHeight) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.sidebarWidth = sidebarWidth;
            this.contentX = contentX;
            this.contentY = contentY;
            this.contentWidth = contentWidth;
            this.contentHeight = contentHeight;
            this.headerX = headerX;
            this.headerY = headerY;
            this.headerWidth = headerWidth;
            this.headerHeight = headerHeight;
            this.moduleListX = moduleListX;
            this.moduleListY = moduleListY;
            this.moduleListWidth = moduleListWidth;
            this.moduleListHeight = moduleListHeight;
            this.settingsX = settingsX;
            this.settingsY = settingsY;
            this.settingsWidth = settingsWidth;
            this.settingsHeight = settingsHeight;
            this.sidebarSearchX = sidebarSearchX;
            this.sidebarSearchY = sidebarSearchY;
            this.sidebarSearchWidth = sidebarSearchWidth;
            this.sidebarSearchHeight = sidebarSearchHeight;
        }
    }
}
