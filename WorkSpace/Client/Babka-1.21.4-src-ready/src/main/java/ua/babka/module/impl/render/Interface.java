package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.GameMode;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import ua.babka.Babka;
import ua.babka.event.EventHUD;
import ua.babka.event.EventPacket;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.combat.AttackAura;
import ua.babka.module.impl.player.StreamerMode;
import ua.babka.module.setting.BindSetting;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.Setting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.ColorUtils;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoveringUtils;
import ua.babka.render.ReplaceUtil;
import ua.babka.render.Scissor;
import ua.babka.render.builder.Builder;
import ua.babka.render.builder.BuiltBorder;
import ua.babka.render.builder.BuiltGlow;
import ua.babka.render.builder.BuiltRectangle;
import ua.babka.render.builder.BuiltShine;
import ua.babka.render.builder.BuiltTexture;
import ua.babka.render.builder.QuadColorState;
import ua.babka.render.builder.QuadRadiusState;
import ua.babka.render.builder.SizeState;
import ua.babka.render.font.Fonts;
import ua.babka.render.font.MsdfFont;
import ua.babka.render.shader.StencilUtil;
import ua.babka.server.Server;
import ua.babka.server.StaffManager;
import ua.babka.ui.drag.DragManager;
import ua.babka.ui.drag.Draggable;
import ua.babka.ui.hud.NotificationsElement;
import ua.babka.util.Instance;
import ua.babka.util.KeyStorage;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;
import ua.babka.util.math.Counter;
import ua.babka.util.math.MathUtil;

@ModuleInformation(moduleName="Interface", moduleDesc="Настройки интерфейса", moduleCategory=ModuleCategory.RENDER)
public class Interface
extends Module {
    private static final Identifier TARGET_HUD_GLOW_TEXTURE = Identifier.of((String)"mre", (String)"images/glow.png");
    private static final Identifier WATERMARK_LOGO_TEXTURE = Identifier.of((String)"mre", (String)"images/logo.png");
    private final ModeSetting hudStyle = new ModeSetting("Стиль HUD", "Babka", "Babka", "Babka2", "Nursultan", "Celestial");
    private final ModeListSetting elements = new ModeListSetting("Элементы", new BooleanSetting("Ватермарка", true), new BooleanSetting("Активный таргет", true), new BooleanSetting("Таргет худ от темы", false), new BooleanSetting("Активные модераторы", true), new BooleanSetting("Бафы", true), new BooleanSetting("Счетчик тотемов", true), new BooleanSetting("Нотификации", true), new BooleanSetting("ArmorHUD", true), new BooleanSetting("Бинды ServerHelper", true));
    private final BooleanSetting blurEnabled = new BooleanSetting("Блюр", true);
    private final ModeListSetting sbBindsInfo = new ModeListSetting("Бинды для показа", new BooleanSetting("Анти Полет", true), new BooleanSetting("Тёмный Пульс", true), new BooleanSetting("Шар Хаоса", true), new BooleanSetting("Ловушки", true));
    private final SliderSetting backgroundIntensity = new SliderSetting("Интенсивность фона", 1.0, 0.05f, 1.0, 0.01f);
    private final SliderSetting headerIntensity = new SliderSetting("Интенсивность заголовков", 1.0, 0.05f, 1.0, 0.01f);
    private final SliderSetting itemIntensity = new SliderSetting("Интенсивность элементов", 0.2f, 0.05f, 1.0, 0.01f);
    private final SliderSetting lowHpAlertThreshold = new SliderSetting("Порог ХП оповещения", 8.0, 1.0, 20.0, 0.5);
    private final SliderSetting babka2Transparency = new SliderSetting("Прозрачность Babka2", 0.0, 0.0, 100.0, 1.0).setVisible(() -> this.hudStyle.is("Babka2"));
    private static final SoundEvent SPEK_SOUND = SoundEvent.of((Identifier)Identifier.of((String)"mre", (String)"spek"));
    private float animFps;
    private final Draggable watermarkDrag = DragManager.installDrag(this, "HotKeys", 4.0f, 4.0f);
    private final Draggable keyBindsDrag = DragManager.installDrag(this, "HotKeys", 100.0f, 50.0f);
    private final Draggable staffListDrag = DragManager.installDrag(this, "StaffList", 200.0f, 50.0f);
    private final Draggable potionsDrag = DragManager.installDrag(this, "Potions", 300.0f, 50.0f);
    private final Draggable targetHUDDrag = DragManager.installDrag(this, "TargetHUD", 130.0f, 130.0f);
    private final Draggable totemCounterDrag = DragManager.installDrag(this, "TotemCounter", 200.0f, 200.0f);
    private final Draggable armorHudDrag = DragManager.installDrag(this, "ArmorHUD", 4.0f, 200.0f);
    private final Draggable sbBindsDrag = DragManager.installDrag(this, "ServerBinds", 4.0f, 230.0f);
    private final Draggable afCooldownDrag = DragManager.installDrag(this, "AfCooldown", 200.0f, 230.0f);
    private final Draggable notificationsDrag = DragManager.installDrag(this, "Notifications", 4.0f, 300.0f);
    private boolean showFps = true;
    private boolean showTps = true;
    private boolean showServer = true;
    private boolean showCoords = true;
    private boolean showBps = true;
    private boolean contextMenuOpen;
    private float contextMenuX;
    private float contextMenuY;
    private Draggable contextMenuElement;
    private Draggable pendingContextMenuElement;
    private float pendingContextMenuX;
    private float pendingContextMenuY;
    private final Animation contextMenuAnimation = new Animation(Easing.SINE_IN_OUT, 200L);
    private final Animation fpsToggleAnimation = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation tpsToggleAnimation = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation serverToggleAnimation = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation coordsToggleAnimation = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation bpsToggleAnimation = new Animation(Easing.SINE_IN_OUT, 150L);
    private boolean keyBindsTransparentBg;
    private boolean staffListTransparentBg;
    private boolean potionsTransparentBg;
    private boolean totemCounterTransparentBg;
    private boolean armorHudTransparentBg;
    private boolean sbBindsTransparentBg;
    private boolean afCooldownTransparentBg;
    private boolean watermarkTransparentBg;
    private boolean targetHudTransparentBg;
    private boolean notificationsTransparentBg;
    private boolean showTargetArmor = true;
    private boolean showTargetGapples = true;
    private boolean hpBarUseTheme = true;
    private final Animation kbTranspAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation slTranspAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation potTranspAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation tcTranspAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation ahTranspAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation sbTranspAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation afTranspAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation targetArmorAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation targetGapplesAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation hpBarThemeAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation wmTranspAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation thTranspAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private final Animation notifTranspAnim = new Animation(Easing.SINE_IN_OUT, 150L);
    private static final Map<String, Item> SH_ICONS = Map.of("Анти Полет", Items.FIREWORK_STAR, "Тёмный Пульс", Items.FIREWORK_STAR, "Шар Хаоса", Items.PLAYER_HEAD, "Ловушки", Items.HEART_OF_THE_SEA);
    private final Animation sbBindsWidthAnimation = new Animation(Easing.QUAD_OUT, 400L);
    private long lastAntiFlyUse;
    private static final long ANTI_FLY_COOLDOWN = 40000L;
    private boolean antiFlyKeyWasDown;
    public final NotificationsElement notifications = new NotificationsElement();
    private final Map<String, Long> spekSuspects = new ConcurrentHashMap<String, Long>();
    private final Animation animation = new Animation(Easing.EXPO_OUT, 300L);
    private final Animation armorAnim = new Animation(Easing.EXPO_OUT, 300L);
    private final Animation hpAnimation = new Animation(Easing.EXPO_OUT, 600L);
    private final Animation outdatedHpAnimation = new Animation(Easing.EXPO_OUT, 1200L);
    private final Animation absorptionAnimation = new Animation(Easing.EXPO_OUT, 300L);
    private final Animation absorptionTrailAnimation = new Animation(Easing.EXPO_OUT, 1200L);
    private Entity lastTarget;
    private float lastHealthPercent;
    private float lastAbsorptionPercent;
    private float trailHealthPercent;
    private float lastHpPercent = -1.0f;
    private float lastHpRaw = -1.0f;
    private final Animation widthAnim = new Animation(Easing.EXPO_OUT, 200L);
    private final Animation xLine = new Animation(Easing.EXPO_OUT, 170L);
    private final Animation alpha = new Animation(Easing.EXPO_OUT, 200L);
    private final Animation alphaKB2 = new Animation(Easing.EXPO_OUT, 200L);
    private final Animation widthAnimKB2 = new Animation(Easing.EXPO_OUT, 200L);
    private final Animation keybindsEmptyAnim = new Animation(Easing.EXPO_OUT, 233L);
    private final Animation potionsEmptyAnim = new Animation(Easing.EXPO_OUT, 233L);
    private final Animation lowHpAlertAnimation = new Animation(Easing.EXPO_OUT, 300L);
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final List<Staff> staffPlayers = new ArrayList<Staff>();
    private final Pattern namePattern = Pattern.compile("^\\w{3,16}$");
    private final Pattern prefixMatches = Pattern.compile(".*(ꔷ|ꔳ|ꔩ|ꔥ|ꔡ|ꔗ|ꔓ|\\bmod\\b|\\badm\\b|\\bhelp\\b|\\bwne\\b|модер|хелп|помощ|админ|владел|отриц|\\btaf\\b|\\bcurat\\b|куратор|\\bdev\\b|разраб|\\bsupp\\b|саппорт|\\byt\\b|\\[yt\\]|ютуб|стажер|сотрудник).*");
    private final Animation widthAnim2 = new Animation(Easing.EXPO_OUT, 200L);
    private final Animation alpha2 = new Animation(Easing.EXPO_OUT, 200L);
    private final Animation staffEmptyAnim = new Animation(Easing.EXPO_OUT, 233L);
    private final Animation widthAnim3 = new Animation(Easing.EXPO_OUT, 200L);
    private final Animation xLine2 = new Animation(Easing.EXPO_OUT, 170L);
    private final Animation alpha3 = new Animation(Easing.EXPO_OUT, 200L);
    private final List<PotionItem> potionItems = new CopyOnWriteArrayList<PotionItem>();
    private final List<HeadParticle> headParticles = new ArrayList<HeadParticle>();

    public float getBackgroundIntensity() {
        return this.backgroundIntensity.getFloatValue();
    }

    private int applyBabka2Alpha(int alpha) {
        if (!this.hudStyle.is("Babka2")) {
            return alpha;
        }
        float t2 = this.babka2Transparency.getFloatValue() / 100.0f;
        float mult = 1.0f - t2 * 0.7f;
        return MathHelper.clamp((int)((int)((float)alpha * mult)), (int)0, (int)255);
    }

    private float applyBabka2Alpha(float alpha) {
        if (!this.hudStyle.is("Babka2")) {
            return alpha;
        }
        float t2 = this.babka2Transparency.getFloatValue() / 100.0f;
        float mult = 1.0f - t2 * 0.7f;
        return MathHelper.clamp((float)(alpha * mult), (float)0.0f, (float)1.0f);
    }

    private boolean canBlur(int alpha) {
        return this.blurEnabled.getValue() && alpha > 0;
    }

    public void drawHeaderBackground(float x2, float y2, float w2, float h2, float radius, int alpha) {
        float intensity = this.backgroundIntensity.getFloatValue();
        int color = ColorProvider.rgba(25, 25, 25, (int)((float)alpha * intensity));
        if (this.canBlur(alpha)) {
            DrawUtil.drawRoundBlur(x2, y2, w2, h2, radius, ColorProvider.rgba(200, 200, 200, alpha), 12.0f);
        }
        DrawUtil.drawRound(x2, y2, w2, h2, radius, color);
    }

    private void drawItemBackground(float x2, float y2, float w2, float h2, float radius, int alpha) {
        float intensity = this.backgroundIntensity.getFloatValue();
        int color = ColorProvider.rgba(25, 25, 25, (int)((float)alpha * intensity));
        if (this.canBlur(alpha)) {
            DrawUtil.drawRoundBlur(x2, y2, w2, h2, radius, ColorProvider.rgba(200, 200, 200, alpha), 12.0f);
        }
        DrawUtil.drawRound(x2, y2, w2, h2, radius, color);
    }

    public void drawBackground(float x2, float y2, float w2, float h2, float radius, int alpha) {
        int color = ColorProvider.rgba(25, 25, 25, (int)((float)alpha * this.backgroundIntensity.getFloatValue()));
        if (this.canBlur(alpha)) {
            DrawUtil.drawRoundBlur(x2, y2, w2, h2, radius, ColorProvider.rgba(200, 200, 200, alpha), 12.0f);
        }
        DrawUtil.drawRound(x2, y2, w2, h2, radius, color);
    }

    public void drawBlurBg(float x2, float y2, float w2, float h2, float radius, int alpha) {
        if (this.canBlur(alpha)) {
            DrawUtil.drawRoundBlur(x2, y2, w2, h2, radius, ColorProvider.rgba(200, 200, 200, alpha), 12.0f);
        }
    }

    public void drawBackground(float x2, float y2, float w2, float h2, float tl, float tr, float bl, float br, int alpha) {
        Vector4f radii = new Vector4f(tl, tr, bl, br);
        int color = ColorProvider.rgba(25, 25, 25, (int)((float)alpha * this.backgroundIntensity.getFloatValue()));
        if (this.canBlur(alpha)) {
            DrawUtil.drawRoundBlur(x2, y2, w2, h2, radii, ColorProvider.rgba(200, 200, 200, alpha), 12.0f);
        }
        DrawUtil.drawRound(x2, y2, w2, h2, radii, color);
    }

    public void drawBlurBg(float x2, float y2, float w2, float h2, float tl, float tr, float bl, float br, int alpha) {
        Vector4f radii = new Vector4f(tl, tr, bl, br);
        if (this.canBlur(alpha)) {
            DrawUtil.drawRoundBlur(x2, y2, w2, h2, radii, ColorProvider.rgba(200, 200, 200, alpha), 12.0f);
        }
    }

    private void drawShine(float x2, float y2, float w2, float h2, float radius, int alpha) {
        this.drawShine(x2, y2, w2, h2, radius, radius, radius, radius, alpha);
    }

    private void drawShine(float x2, float y2, float w2, float h2, float tl, float tr, float bl, float br, int alpha) {
        float t2 = (float)((double)(System.currentTimeMillis() % 2800L) / 2800.0);
        int shineColor = ColorProvider.rgba(255, 255, 255, (int)((float)alpha * 0.18f));
        ((BuiltShine)Builder.shine().size(new SizeState(w2, h2)).radius(new QuadRadiusState(tl, tr, br, bl)).color(new QuadColorState(shineColor)).smoothness(1.5f).time(t2).shineWidth(0.06f).build()).render(new Matrix4f(), x2, y2, 0.0f);
    }

    private void drawCelestialGlow(Matrix4f matrix, float x2, float y2, float w2, float h2, float radius, float anim) {
        int t1 = ColorProvider.getThemeColor();
        int t2 = ColorProvider.getThemeColorTwo();
        float glow = 7.0f;
        int a2 = (int)(110.0f * anim);
        int[] c2 = ColorProvider.getOrbitalRect(t1, t2, 300.0, a2);
        ((BuiltGlow)Builder.glow().size(new SizeState(w2 + glow * 2.0f - 6.0f, h2 + glow * 2.0f - 6.0f)).radius(new QuadRadiusState(radius)).color(new QuadColorState(c2[0], c2[1], c2[2], c2[3])).glowRadius(glow).softness(0.0f).intensity(2.0f).additive(true).build()).render(matrix, x2 - glow + 3.0f, y2 - glow + 3.0f, 0.0f);
    }

    @Subscribe
    public void onEventHUD(EventHUD e2) {
        if (this.mc.player == null || this.mc.options.hudHidden || this.mc.getDebugHud().shouldShowDebugHud()) {
            return;
        }
        if (this.elements.isEnabled("Нотификации")) {
            this.notifications.render(e2.getDrawContext(), this.notificationsDrag.getX(), this.notificationsDrag.getY(), this.notificationsTransparentBg);
            if (this.notifications.getDragWidth() > 0.0f) {
                this.notificationsDrag.setWidth(this.notifications.getDragWidth());
                this.notificationsDrag.setHeight(this.notifications.getDragHeight());
            }
        }
        if (this.elements.isEnabled("Счетчик тотемов")) {
            this.renderTotemCounter(e2.getDrawContext());
        }
        if (this.elements.isEnabled("Ватермарка")) {
            this.renderWatermark(e2.getDrawContext());
        }
        if (this.elements.isEnabled("Активный таргет")) {
            this.renderTargetHUD(e2.getDrawContext());
        }
        this.renderKeyBinds(e2.getDrawContext());
        if (this.elements.isEnabled("Активные модераторы")) {
            this.renderStaffList(e2.getDrawContext());
        }
        if (this.elements.isEnabled("Бафы")) {
            this.renderPotions(e2.getDrawContext());
        }
        if (this.elements.isEnabled("ArmorHUD")) {
            this.renderArmorHud(e2.getDrawContext());
        }
        if (this.elements.isEnabled("Бинды ServerHelper")) {
            this.renderServerBinds(e2.getDrawContext());
            this.renderAntiFlyCooldown(e2.getDrawContext());
        }
        if (!(this.mc.currentScreen instanceof ChatScreen)) {
            this.contextMenuOpen = false;
            this.pendingContextMenuElement = null;
        }
    }

    @Subscribe
    private void onUpdate(EventTick e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        long now = System.currentTimeMillis();
        this.spekSuspects.entrySet().removeIf(entry -> now - (Long)entry.getValue() > 30000L);
        for (AbstractClientPlayerEntity p2 : this.mc.world.getPlayers()) {
            if (p2 == this.mc.player || !(this.mc.player.distanceTo((Entity)p2) < 50.0f)) continue;
            this.spekSuspects.put(p2.getName().getString(), now);
        }
        AttackAura ka = Instance.get(AttackAura.class);
        if (ka != null && ka.isEnabled() && ka.getTarget() != null) {
            this.spekSuspects.put(ka.getTarget().getName().getString(), now);
        }
        if (this.elements.isEnabled("Активные модераторы")) {
            this.update();
        }
        if (this.elements.isEnabled("Бафы")) {
            this.updatePotions();
        }
        if (this.elements.isEnabled("Бинды ServerHelper")) {
            if (this.mc.currentScreen != null) {
                this.antiFlyKeyWasDown = false;
            } else {
                Module serverHelper = null;
                for (Module m2 : Babka.getInstance().getModuleStorage().getModules()) {
                    if (!m2.getName().equals("ServerHelper")) continue;
                    serverHelper = m2;
                    break;
                }
                if (serverHelper != null && serverHelper.isEnabled()) {
                    for (Setting s2 : serverHelper.getSettings()) {
                        long elapsed;
                        boolean down;
                        if (!(s2 instanceof BindSetting)) continue;
                        BindSetting bs = (BindSetting)s2;
                        if (!s2.getName().equals("Анти Полет") || bs.getValue() == -1) continue;
                        boolean bl = down = GLFW.glfwGetKey((long)this.mc.getWindow().getHandle(), (int)bs.getValue()) == 1;
                        if (down && !this.antiFlyKeyWasDown && (elapsed = System.currentTimeMillis() - this.lastAntiFlyUse) >= 40000L) {
                            this.lastAntiFlyUse = System.currentTimeMillis();
                        }
                        this.antiFlyKeyWasDown = down;
                        break;
                    }
                }
            }
        }
    }

    @Subscribe
    private void onPacket(EventPacket e2) {
        if (this.mc.player == null) {
            return;
        }
        Packet<?> packet = e2.getPacket();
        if (packet instanceof GameMessageS2CPacket) {
            long elapsed;
            boolean isTrigger;
            GameMessageS2CPacket packet2 = (GameMessageS2CPacket)packet;
            String rawContent = packet2.comp_763().getString();
            String msgLower = rawContent.toLowerCase();
            boolean bl = isTrigger = msgLower.contains("спек") || msgLower.contains("spec") || msgLower.contains("spek") || msgLower.contains("report") || msgLower.contains("фаст");
            if (rawContent.contains("Анти Полет") && rawContent.contains("Перезарядка") && (elapsed = System.currentTimeMillis() - this.lastAntiFlyUse) < 1000L) {
                this.lastAntiFlyUse = 0L;
            }
            if (isTrigger) {
                for (String suspect : this.spekSuspects.keySet()) {
                    if (!rawContent.contains(suspect)) continue;
                    this.playSpekSound();
                    break;
                }
            }
        }
    }

    private void playSpekSound() {
        if (this.mc.getSoundManager() != null) {
            this.mc.getSoundManager().play((SoundInstance)PositionedSoundInstance.master((SoundEvent)SPEK_SOUND, (float)1.0f));
        }
    }

    private void drawBabkaGlow(Matrix4f matrix, float x2, float y2, float w2, float h2, float radius, float anim) {
        int a2 = (int)(110.0f * anim);
        int[] c2 = ColorProvider.getOrbitalRect(ColorProvider.getColorClient(), ColorProvider.getColorClient(), 300.0, a2);
        ((BuiltGlow)Builder.glow().size(new SizeState(w2 + 8.0f, h2 + 8.0f)).radius(new QuadRadiusState(radius)).color(new QuadColorState(c2[0], c2[1], c2[2], c2[3])).glowRadius(7.0f).softness(0.0f).intensity(2.0f).additive(true).build()).render(matrix, x2 - 4.0f, y2 - 4.0f, 0.0f);
    }

    public int getThemeTint(int alpha) {
        int themeColor = ColorProvider.getColorClient();
        return ColorProvider.setAlpha(themeColor, (int)(100.0f * ((float)alpha / 255.0f) * this.backgroundIntensity.getFloatValue()));
    }

    private void renderWatermarkCelestial(DrawContext context) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        Counter.updateFPS();
        StreamerMode streamerMode = Instance.get(StreamerMode.class);
        String userText = streamerMode != null && streamerMode.isEnabled() ? "Пользователь" : this.mc.player.getNameForScoreboard();
        String fpsText = Counter.getCurrentFPS() + "fps";
        String tpsText = String.format(Locale.US, "%.1ftps", Float.valueOf(Babka.getInstance().getTpsGetter().getTPS()));
        String timeText = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        String fullText = "Babka | " + userText + " | " + fpsText + " | " + tpsText + " | " + timeText;
        float fontSize = 7.5f;
        float x2 = this.watermarkDrag.getX();
        float y2 = this.watermarkDrag.getY();
        float height = 14.0f;
        float width = Fonts.SFBOLD.get().getWidth(fullText, fontSize) + 12.0f;
        int t1 = ColorProvider.getThemeColor();
        int t2 = ColorProvider.getThemeColorTwo();
        int[] glow = ColorProvider.getOrbitalRect(t1, t2, 300.0, 110);
        Matrix4f m2 = context.getMatrices().peek().getPositionMatrix();
        this.drawCelestialGlow(m2, x2, y2, width, height, 4.0f, 1.0f);
        DrawUtil.drawRound(x2 - 0.5f, y2 - 0.5f, width + 1.0f, height + 1.0f, 4.0f, glow[0], glow[1], glow[2], glow[3]);
        DrawUtil.drawRound(x2, y2, width, height, 4.0f, ColorProvider.rgba(14, 10, 6, 255.0f));
        float textY = y2 + (height - fontSize) / 2.0f;
        DrawUtil.drawText(Fonts.SFBOLD.get(), fullText, x2 + 3.0f, textY, ColorProvider.rgba(255, 255, 255, 255.0f), fontSize);
        this.watermarkDrag.setWidth(width);
        this.watermarkDrag.setHeight(height);
    }

    private void renderWatermark(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        if (this.hudStyle.is("Celestial")) {
            this.renderWatermarkCelestial(context);
        } else if (this.hudStyle.is("Nursultan")) {
            this.renderWatermarkNursultan(context);
        } else if (this.hudStyle.is("Babka2")) {
            this.renderWatermarkBabka2(context);
        } else {
            this.renderWatermarkBabka(context);
        }
    }

    private void renderWatermarkBabka(DrawContext context) {
        String ch;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        Counter.updateFPS();
        StreamerMode streamerMode = Instance.get(StreamerMode.class);
        String userText = streamerMode != null && streamerMode.isEnabled() ? "Пользователь" : this.mc.player.getNameForScoreboard();
        String clientName = "Babka";
        String username = "[" + userText + "]";
        String fpsText = Counter.getCurrentFPS() + "fps";
        String tpsText = String.format(Locale.US, "%.1ftps", Float.valueOf(Babka.getInstance().getTpsGetter().getTPS()));
        String serverText = "singleplayer";
        if (this.mc.getCurrentServerEntry() != null && this.mc.getCurrentServerEntry().address != null) {
            serverText = this.mc.getCurrentServerEntry().address;
        }
        String coordsText = (int)this.mc.player.getX() + ", " + (int)this.mc.player.getY() + ", " + (int)this.mc.player.getZ();
        double dX = this.mc.player.getX() - this.mc.player.prevX;
        double dY = this.mc.player.getY() - this.mc.player.prevY;
        double dZ = this.mc.player.getZ() - this.mc.player.prevZ;
        String bpsText = String.format(Locale.US, "%.1fbps", Math.hypot(Math.hypot(dX, dZ), dY) * 20.0);
        String dot = " • ";
        String fpsIcon = "X";
        String tpsIcon = "$";
        String serverIcon = "Q";
        String coordsIcon = "F";
        String bpsIcon = "@";
        boolean showFps = this.showFps;
        boolean showTps = this.showTps;
        boolean showServer = this.showServer;
        boolean showCoords = this.showCoords;
        boolean showBps = this.showBps;
        float x2 = this.watermarkDrag.getX();
        float y2 = this.watermarkDrag.getY();
        float height = 13.5f;
        float gap = 2.0f;
        float headSize = 9.5f;
        float fontSize = 6.7f;
        float titleSize = 7.1f;
        float iconSize = 7.0f;
        int white = ColorProvider.rgba(245, 245, 245, 255.0f);
        int gray = ColorProvider.rgba(165, 165, 165, 255.0f);
        int userColor = ColorProvider.rgba(185, 185, 185, 255.0f);
        int dotColor = ColorProvider.rgba(185, 140, 90, 255.0f);
        int iconColor = ColorProvider.getColorIcons();
        float clientW = Fonts.SFBOLD.get().getWidth(clientName, titleSize);
        float usernameW = Fonts.SFMEDIUM.get().getWidth(username, fontSize);
        float fpsW = showFps ? Fonts.SFMEDIUM.get().getWidth(fpsText, fontSize) : 0.0f;
        float tpsW = showTps ? Fonts.SFMEDIUM.get().getWidth(tpsText, fontSize) : 0.0f;
        float serverW = showServer ? Fonts.SFMEDIUM.get().getWidth(serverText, fontSize) : 0.0f;
        float dotW = Fonts.SFMEDIUM.get().getWidth(dot, fontSize);
        float fpsIconW = showFps ? Fonts.ICONS_NURIK.get().getWidth(fpsIcon, iconSize) : 0.0f;
        float tpsIconW = showTps ? Fonts.ICONS_NURIK.get().getWidth(tpsIcon, iconSize) : 0.0f;
        float serverIconW = showServer ? Fonts.ICONS_NURIK.get().getWidth(serverIcon, iconSize) : 0.0f;
        float coordsW = showCoords ? Fonts.SFMEDIUM.get().getWidth(coordsText, fontSize) : 0.0f;
        float bpsW = showBps ? Fonts.SFMEDIUM.get().getWidth(bpsText, fontSize) : 0.0f;
        float coordsIconW = showCoords ? Fonts.ICONS_NURIK.get().getWidth(coordsIcon, iconSize) : 0.0f;
        float bpsIconW = showBps ? Fonts.ICONS_NURIK.get().getWidth(bpsIcon, iconSize) : 0.0f;
        float row1Width = 6.0f + clientW + dotW + headSize + 3.0f + usernameW;
        if (showFps) {
            row1Width += dotW + fpsIconW + 3.0f + fpsW;
        }
        if (showTps) {
            row1Width += dotW + tpsIconW + 3.0f + tpsW;
        }
        if (showServer) {
            row1Width += dotW + serverIconW + 3.0f + serverW;
        }
        row1Width += 6.0f;
        float row2Width = 6.0f;
        if (showCoords) {
            row2Width += coordsIconW + 3.0f + coordsW;
        }
        if (showCoords && showBps) {
            row2Width += dotW;
        }
        if (showBps) {
            row2Width += bpsIconW + 3.0f + bpsW;
        }
        row2Width += 6.0f;
        int bg = ColorProvider.rgba(24, 24, 24, 205.0f);
        int outline = ColorProvider.rgba(60, 60, 60, 70.0f);
        float y22 = y2 + height + gap;
        if (this.watermarkTransparentBg) {
            DrawUtil.drawRoundBlur(x2, y2, row1Width, height, 3.0f, ColorProvider.rgba(120, 120, 120, 85.0f), 10.0f);
            DrawUtil.drawRoundBlur(x2, y22, row2Width, height, 3.0f, ColorProvider.rgba(120, 120, 120, 85.0f), 10.0f);
        } else {
            if (this.canBlur(255)) {
                DrawUtil.drawRoundBlur(x2, y2, row1Width, height, 3.0f, ColorProvider.rgba(120, 120, 120, 85.0f), 10.0f);
            }
            DrawUtil.drawRound(x2, y2, row1Width, height, 3.0f, bg);
            DrawUtil.drawRound(x2, y2, row1Width, height, 3.0f, outline);
            if (this.canBlur(255)) {
                DrawUtil.drawRoundBlur(x2, y22, row2Width, height, 3.0f, ColorProvider.rgba(120, 120, 120, 85.0f), 10.0f);
            }
            DrawUtil.drawRound(x2, y22, row2Width, height, 3.0f, bg);
            DrawUtil.drawRound(x2, y22, row2Width, height, 3.0f, outline);
        }
        float currentX = x2 + 6.0f;
        float textY = y2 + 3.2f;
        int themeColor = ColorProvider.getColorClient();
        int themeDark = ColorProvider.rgba((int)((float)(themeColor >> 16 & 0xFF) * 0.55f), (int)((float)(themeColor >> 8 & 0xFF) * 0.55f), (int)((float)(themeColor & 0xFF) * 0.55f), 255.0f);
        for (int i2 = 0; i2 < clientName.length(); ++i2) {
            ch = String.valueOf(clientName.charAt(i2));
            int charColor = this.colorLerp(themeColor, themeDark, 3.0f, (float)i2 * 0.35f);
            DrawUtil.drawText(Fonts.SFBOLD.get(), ch, currentX, textY - 0.15f, charColor, titleSize);
            currentX += Fonts.SFBOLD.get().getWidth(ch, titleSize);
        }
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), dot, currentX, textY, dotColor, fontSize);
        currentX += dotW;
        ch = this.mc.player;
        if (ch instanceof AbstractClientPlayerEntity) {
            String clientPlayer = ch;
            try {
                int texId = this.mc.getTextureManager().getTexture(clientPlayer.getSkinTextures().comp_1626()).getGlId();
                ((BuiltTexture)Builder.texture().size(new SizeState(headSize, headSize)).radius(new QuadRadiusState(2.0f)).color(new QuadColorState(-1)).texture(0.125f, 0.125f, 0.125f, 0.125f, texId).smoothness(1.0f).build()).render(context.getMatrices().peek().getPositionMatrix(), currentX, y2 + 2.0f);
            }
            catch (Exception ignored) {
                DrawUtil.drawText(Fonts.ICONS_NURIK.get(), "W", currentX, y2 + 2.8f, white, 7.5f);
            }
        }
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), username, currentX += headSize + 3.0f, textY, userColor, fontSize);
        currentX += usernameW;
        if (showFps) {
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), dot, currentX, textY, dotColor, fontSize);
            DrawUtil.drawText(Fonts.ICONS_NURIK.get(), fpsIcon, currentX += dotW, y2 + 4.0f, iconColor, iconSize);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), fpsText, currentX += fpsIconW + 3.0f, textY, white, fontSize);
            currentX += fpsW;
        }
        if (showTps) {
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), dot, currentX, textY, dotColor, fontSize);
            DrawUtil.drawText(Fonts.ICONS_NURIK.get(), tpsIcon, currentX += dotW, y2 + 4.0f, iconColor, iconSize);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), tpsText, currentX += tpsIconW + 3.0f, textY, white, fontSize);
            currentX += tpsW;
        }
        if (showServer) {
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), dot, currentX, textY, dotColor, fontSize);
            DrawUtil.drawText(Fonts.ICONS_NURIK.get(), serverIcon, currentX += dotW, y2 + 4.0f, iconColor, iconSize);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), serverText, currentX += serverIconW + 3.0f, textY, gray, fontSize);
        }
        float cx2 = x2 + 6.0f;
        float ty2 = y22 + 3.2f;
        if (showCoords) {
            DrawUtil.drawText(Fonts.ICONS_NURIK.get(), coordsIcon, cx2, y22 + 4.0f, iconColor, iconSize);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), coordsText, cx2 += coordsIconW + 3.0f, ty2, white, fontSize);
            cx2 += coordsW;
            if (showBps) {
                DrawUtil.drawText(Fonts.SFMEDIUM.get(), dot, cx2, ty2, dotColor, fontSize);
                cx2 += dotW;
            }
        }
        if (showBps) {
            DrawUtil.drawText(Fonts.ICONS_NURIK.get(), bpsIcon, cx2, y22 + 4.0f, iconColor, iconSize);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), bpsText, cx2 += bpsIconW + 3.0f, ty2, white, fontSize);
        }
        this.watermarkDrag.setWidth(Math.max(row1Width, row2Width));
        this.watermarkDrag.setHeight(height * 2.0f + gap);
    }

    private void renderWatermarkBabka2(DrawContext context) {
        String ch;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        Counter.updateFPS();
        StreamerMode streamerMode = Instance.get(StreamerMode.class);
        String userText = streamerMode != null && streamerMode.isEnabled() ? "Пользователь" : this.mc.player.getNameForScoreboard();
        String clientName = "Бабка";
        String username = userText;
        String fpsText = Counter.getCurrentFPS() + " fps";
        String fpsIcon = "U";
        String tpsText = String.format(Locale.US, "%.1f tps", Float.valueOf(Babka.getInstance().getTpsGetter().getTPS()));
        String tpsIcon = "$";
        String serverText = "singleplayer";
        if (this.mc.getCurrentServerEntry() != null && this.mc.getCurrentServerEntry().address != null) {
            serverText = this.mc.getCurrentServerEntry().address;
        }
        String serverIcon = "Q";
        String coordsText = (int)this.mc.player.getX() + " " + (int)this.mc.player.getY() + " " + (int)this.mc.player.getZ();
        String coordsIcon = "F";
        double dX = this.mc.player.getX() - this.mc.player.prevX;
        double dY = this.mc.player.getY() - this.mc.player.prevY;
        double dZ = this.mc.player.getZ() - this.mc.player.prevZ;
        String bpsText = String.format(Locale.US, "%.1f bps", Math.hypot(Math.hypot(dX, dZ), dY) * 20.0);
        String bpsIcon = "@";
        String dot = " ";
        float x2 = this.watermarkDrag.getX();
        float y2 = this.watermarkDrag.getY();
        float height = 16.0f;
        float radius = 0.0f;
        float padL = 10.0f;
        float padR = 10.0f;
        float headSize = 9.5f;
        float fontSize = 6.75f;
        float titleSize = 7.1f;
        float iconSize = 7.0f;
        float sepGap = 6.0f;
        int wmAlpha = MathHelper.clamp((int)this.applyBabka2Alpha(255), (int)0, (int)255);
        float alphaMul = (float)wmAlpha / 255.0f;
        int themeAccent = ColorProvider.getThemeColor();
        int accentR = ColorProvider.red(themeAccent);
        int accentG = ColorProvider.green(themeAccent);
        int accentB = ColorProvider.blue(themeAccent);
        int accent = ColorProvider.rgba(accentR, accentG, accentB, wmAlpha);
        int accentDark = ColorProvider.rgba((int)((float)accentR * 0.68f), (int)((float)accentG * 0.68f), (int)((float)accentB * 0.68f), wmAlpha);
        int textMain = ColorProvider.rgba(230, 230, 240, wmAlpha);
        int textSoft = ColorProvider.rgba(180, 170, 255, wmAlpha);
        int textGray = ColorProvider.rgba(165, 165, 165, wmAlpha);
        float clientW = Fonts.SFBOLD.get().getWidth(clientName, titleSize);
        float usernameW = Fonts.SFMEDIUM.get().getWidth(username, fontSize);
        float fpsW = this.showFps ? Fonts.SFMEDIUM.get().getWidth(fpsText, fontSize) : 0.0f;
        float fpsIconW = this.showFps ? Fonts.ICONS_NURIK.get().getWidth(fpsIcon, iconSize) : 0.0f;
        float tpsW = this.showTps ? Fonts.SFMEDIUM.get().getWidth(tpsText, fontSize) : 0.0f;
        float tpsIconW = this.showTps ? Fonts.ICONS_NURIK.get().getWidth(tpsIcon, iconSize) : 0.0f;
        float serverW = this.showServer ? Fonts.SFMEDIUM.get().getWidth(serverText, fontSize) : 0.0f;
        float serverIconW = this.showServer ? Fonts.ICONS_NURIK.get().getWidth(serverIcon, iconSize) : 0.0f;
        float coordsW = this.showCoords ? Fonts.SFMEDIUM.get().getWidth(coordsText, fontSize) : 0.0f;
        float coordsIconW = this.showCoords ? Fonts.ICONS_NURIK.get().getWidth(coordsIcon, iconSize) : 0.0f;
        float bpsW = this.showBps ? Fonts.SFMEDIUM.get().getWidth(bpsText, fontSize) : 0.0f;
        float bpsIconW = this.showBps ? Fonts.ICONS_NURIK.get().getWidth(bpsIcon, iconSize) : 0.0f;
        float rowWidth = padL + clientW + 8.0f + headSize + 3.0f + usernameW + 8.0f;
        if (this.showFps) {
            rowWidth += fpsIconW + 3.0f + fpsW;
        }
        if (this.showTps) {
            rowWidth += sepGap + tpsIconW + 3.0f + tpsW;
        }
        if (this.showServer) {
            rowWidth += sepGap + serverIconW + 3.0f + serverW;
        }
        if (this.showCoords) {
            rowWidth += sepGap + coordsIconW + 3.0f + coordsW;
        }
        if (this.showBps) {
            rowWidth += sepGap + bpsIconW + 3.0f + bpsW;
        }
        rowWidth += padR;
        int bgAlpha = MathHelper.clamp((int)((int)(235.0f * alphaMul)), (int)0, (int)255);
        if (this.watermarkTransparentBg) {
            int blurA = MathHelper.clamp((int)((int)(60.0f * alphaMul)), (int)0, (int)255);
            DrawUtil.drawRoundBlur(x2, y2, rowWidth, height, 0.0f, ColorProvider.rgba(120, 120, 120, blurA), 10.0f);
        } else {
            DrawUtil.drawRound(x2, y2, rowWidth, height, radius, ColorProvider.rgba(18, 14, 36, bgAlpha));
            DrawUtil.drawRound(x2, y2, rowWidth, height, radius, ColorProvider.rgba(accentR, accentG, accentB, (int)(18.0f * alphaMul)));
            float sepY = y2 + height - 1.0f;
            context.fill((int)(x2 + padL - 1.0f), (int)sepY, (int)(x2 + rowWidth - padR + 1.0f), (int)(sepY + 1.0f), ColorProvider.rgba(accentR, accentG, accentB, (int)(40.0f * alphaMul)));
        }
        float currentX = x2 + padL;
        float textY = y2 + height / 2.0f - 3.6f;
        for (int i2 = 0; i2 < clientName.length(); ++i2) {
            ch = String.valueOf(clientName.charAt(i2));
            int charColor = this.colorLerp(accent, accentDark, 3.0f, (float)i2 * 0.35f);
            DrawUtil.drawText(Fonts.SFBOLD.get(), ch, currentX, textY - 0.15f, charColor, titleSize);
            currentX += Fonts.SFBOLD.get().getWidth(ch, titleSize);
        }
        currentX += 8.0f;
        try {
            ch = this.mc.player;
            if (ch instanceof AbstractClientPlayerEntity) {
                String clientPlayer = ch;
                int texId = this.mc.getTextureManager().getTexture(clientPlayer.getSkinTextures().comp_1626()).getGlId();
                ((BuiltTexture)Builder.texture().size(new SizeState(headSize, headSize)).radius(new QuadRadiusState(2.0f)).color(new QuadColorState(ColorProvider.setAlpha(-1, wmAlpha))).texture(0.125f, 0.125f, 0.125f, 0.125f, texId).smoothness(1.0f).build()).render(context.getMatrices().peek().getPositionMatrix(), currentX, y2 + (height - headSize) / 2.0f);
            } else {
                DrawUtil.drawText(Fonts.ICONS_NURIK.get(), "P", currentX, y2 + height / 2.0f - 3.8f, textMain, 7.5f);
            }
        }
        catch (Exception e2) {
            DrawUtil.drawText(Fonts.ICONS_NURIK.get(), "P", currentX, y2 + height / 2.0f - 3.8f, textMain, 7.5f);
        }
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), username, currentX += headSize + 3.0f, textY, textMain, fontSize);
        currentX += usernameW + 8.0f;
        if (this.showFps) {
            DrawUtil.drawText(Fonts.ICONS_NURIK.get(), fpsIcon, currentX, textY, accent, iconSize);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), fpsText, currentX += fpsIconW + 3.0f, textY, textSoft, fontSize);
            currentX += fpsW;
        }
        if (this.showTps) {
            DrawUtil.drawText(Fonts.ICONS_NURIK.get(), tpsIcon, currentX += sepGap, textY, accent, iconSize);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), tpsText, currentX += tpsIconW + 3.0f, textY, textSoft, fontSize);
            currentX += tpsW;
        }
        if (this.showServer) {
            DrawUtil.drawText(Fonts.ICONS_NURIK.get(), serverIcon, currentX += sepGap, textY, accent, iconSize);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), serverText, currentX += serverIconW + 3.0f, textY, textGray, fontSize);
            currentX += serverW;
        }
        if (this.showCoords) {
            DrawUtil.drawText(Fonts.ICONS_NURIK.get(), coordsIcon, currentX += sepGap, textY, accent, iconSize);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), coordsText, currentX += coordsIconW + 3.0f, textY, textSoft, fontSize);
            currentX += coordsW;
        }
        if (this.showBps) {
            DrawUtil.drawText(Fonts.ICONS_NURIK.get(), bpsIcon, currentX += sepGap, textY, accent, iconSize);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), bpsText, currentX += bpsIconW + 3.0f, textY, textSoft, fontSize);
        }
        this.watermarkDrag.setWidth(rowWidth);
        this.watermarkDrag.setHeight(height);
    }

    private float drawTopRow(DrawContext context, float x2, float y2, float height) {
        MsdfFont iconFont = Fonts.ICONS_NURIK.get();
        MsdfFont textFont = Fonts.SFBOLD.get();
        float iconSize = this.scaled(6.8f);
        float textSize = this.scaled(6.1f);
        float pad = this.scaled(3.3f);
        float innerGap = this.scaled(2.0f);
        float sepGap = this.scaled(2.5f);
        String username = this.mc.getSession().getUsername();
        String fps = this.getFpsText();
        String time = LocalTime.now().format(this.timeFormatter);
        float w2 = pad * 2.0f + iconFont.getWidth("W", iconSize) + innerGap + textFont.getWidth(username, textSize) + sepGap + textFont.getWidth("|", textSize) + sepGap + iconFont.getWidth("X", iconSize) + innerGap + textFont.getWidth(fps, textSize) + sepGap + textFont.getWidth("|", textSize) + sepGap + iconFont.getWidth("V", iconSize) + innerGap + textFont.getWidth(time, textSize);
        this.drawBackground(x2, y2, w2, height, this.scaled(3.0f), 255);
        float textY = y2 + (height - textSize) / 2.0f;
        float iconY = y2 + (height - iconSize) / 2.0f;
        float offset = x2 + pad;
        int themeColor = ColorProvider.getColorClient();
        int whiteColor = -1;
        DrawUtil.drawText(iconFont, "W", offset, iconY, themeColor, iconSize);
        DrawUtil.drawText(textFont, username, offset += iconFont.getWidth("W", iconSize) + innerGap, textY, whiteColor, textSize);
        offset += textFont.getWidth(username, textSize) + sepGap;
        offset = this.drawSep(context, offset, textY, textSize) + sepGap;
        DrawUtil.drawText(iconFont, "X", offset, iconY, themeColor, iconSize);
        DrawUtil.drawText(textFont, fps, offset += iconFont.getWidth("X", iconSize) + innerGap, textY, whiteColor, textSize);
        offset += textFont.getWidth(fps, textSize) + sepGap;
        offset = this.drawSep(context, offset, textY, textSize) + sepGap;
        DrawUtil.drawText(iconFont, "V", offset, iconY, themeColor, iconSize);
        DrawUtil.drawText(textFont, time, offset += iconFont.getWidth("V", iconSize) + innerGap, textY, whiteColor, textSize);
        return w2;
    }

    private float drawInfoPill(DrawContext context, float x2, float y2, float height, float radius, String icon, String value) {
        MsdfFont iconFont = Fonts.ICONS_NURIK.get();
        MsdfFont textFont = Fonts.SFBOLD.get();
        float iconSize = this.scaled(6.8f);
        float textSize = this.scaled(6.1f);
        float pad = this.scaled(3.3f);
        float innerGap = this.scaled(2.0f);
        float sepW = textFont.getWidth("|", textSize);
        float w2 = pad * 2.0f + iconFont.getWidth(icon, iconSize) + innerGap + sepW + innerGap + textFont.getWidth(value, textSize);
        this.drawBackground(x2, y2, w2, height, radius, 255);
        float textY = y2 + (height - textSize) / 2.0f;
        float iconY = y2 + (height - iconSize) / 2.0f;
        float offset = x2 + pad;
        int themeColor = ColorProvider.getColorClient();
        int whiteColor = -1;
        DrawUtil.drawText(iconFont, icon, offset, iconY, themeColor, iconSize);
        this.drawSep(context, offset += iconFont.getWidth(icon, iconSize) + innerGap, textY, textSize);
        DrawUtil.drawText(textFont, value, offset += sepW + innerGap, textY, whiteColor, textSize);
        return w2;
    }

    private void drawIconBox(DrawContext context, float x2, float y2, float w2, float h2, float radius, String icon) {
        MsdfFont iconFont = Fonts.ICONS_NURIK.get();
        float iconSize = this.scaled(7.2f);
        int themeColor = ColorProvider.getColorClient();
        this.drawBackground(x2, y2, w2, h2, radius, 255);
        float iconX = x2 + (w2 - iconFont.getWidth(icon, iconSize)) / 2.0f;
        DrawUtil.drawText(iconFont, icon, iconX, y2 + (h2 - iconSize) / 2.0f, themeColor, iconSize);
    }

    private float drawSep(DrawContext context, float x2, float y2, float size) {
        int sepColor = ColorProvider.rgba(220, 220, 220, 115.0f);
        DrawUtil.drawText(Fonts.SFBOLD.get(), "|", x2, y2, sepColor, size);
        return x2 + Fonts.SFBOLD.get().getWidth("|", size);
    }

    private float scaled(float v2) {
        return v2 * 1.2f;
    }

    private String getFpsText() {
        this.animFps += ((float)this.mc.getCurrentFps() - this.animFps) * 0.2f;
        return (int)this.animFps + " Fps";
    }

    private String getPingText() {
        int ping = 0;
        if (this.mc.getNetworkHandler() != null) {
            for (PlayerListEntry entry : this.mc.getNetworkHandler().getPlayerList()) {
                if (!entry.getProfile().getId().equals(this.mc.player.getUuid())) continue;
                ping = entry.getLatency();
                break;
            }
        }
        return ping + " Ms";
    }

    private String getTpsText() {
        return String.format(Locale.US, "%.1f Ticks", Float.valueOf(Babka.getInstance().getTpsGetter().getTPS()));
    }

    private String getBpsText() {
        double dX = this.mc.player.getX() - this.mc.player.prevX;
        double dZ = this.mc.player.getZ() - this.mc.player.prevZ;
        return String.format(Locale.US, "%.1f Bps", Math.hypot(dX, dZ) * 20.0);
    }

    private void renderWatermarkNursultan(DrawContext context) {
        Counter.updateFPS();
        StreamerMode streamerMode = Instance.get(StreamerMode.class);
        String userText = streamerMode != null && streamerMode.isEnabled() ? "babka.xyz" : this.mc.player.getNameForScoreboard();
        String fpsValue = this.getFpsText();
        String timeText = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String pingText = Server.getPing((PlayerEntity)this.mc.player) + " Ping";
        String tpsText = String.format("%.1f Ticks", Float.valueOf(Babka.getInstance().getTpsGetter().getTPS()));
        String bpsText = String.format("%.1f Bps", MathUtil.getBps((Entity)this.mc.player));
        String serverText = "singleplayer";
        if (this.mc.getCurrentServerEntry() != null && this.mc.getCurrentServerEntry().address != null) {
            serverText = this.mc.getCurrentServerEntry().address;
        }
        float x2 = this.watermarkDrag.getX();
        float y2 = this.watermarkDrag.getY();
        float startX = x2;
        float height = this.scaled(13.5f);
        float gap = this.scaled(1.56f);
        float radius = this.scaled(3.0f);
        int primaryColor = ColorProvider.getColorClient();
        int secondaryColor = ColorProvider.getColorIcons();
        int whiteColor = -1;
        float iconSize = this.scaled(6.8f);
        float textSize = this.scaled(6.1f);
        float boxIconSize = this.scaled(7.2f);
        float padding = this.scaled(3.3f);
        float innerGap = this.scaled(2.0f);
        float separatorGap = this.scaled(2.5f);
        MsdfFont iconFont = Fonts.ICONS_NURIK.get();
        MsdfFont textFont = Fonts.SFBOLD.get();
        float topBoxWidth = this.scaled(14.0f);
        if (this.watermarkTransparentBg) {
            this.drawBlurBg(x2, y2, topBoxWidth, height, radius, 255);
        } else {
            this.drawBackground(x2, y2, topBoxWidth, height, radius, 255);
        }
        DrawUtil.drawText(iconFont, "P", x2 + (topBoxWidth - iconFont.getWidth("P", boxIconSize)) / 2.0f - this.scaled(0.55f), y2 + (height - boxIconSize) / 2.0f, primaryColor, boxIconSize);
        float topRowX = x2 + topBoxWidth + gap;
        String sep = "|";
        float sepW = textFont.getWidth(sep, textSize);
        float usernameW = textFont.getWidth(userText, textSize);
        float timeW = textFont.getWidth(timeText, textSize);
        float fpsW = this.showFps ? textFont.getWidth(fpsValue, textSize) : 0.0f;
        float serverW = this.showServer ? textFont.getWidth(serverText, textSize) : 0.0f;
        float topRowWidth = padding * 2.0f + iconFont.getWidth("W", iconSize) + innerGap + usernameW + separatorGap + sepW + separatorGap + (this.showFps ? iconFont.getWidth("X", iconSize) + innerGap + fpsW + separatorGap + sepW + separatorGap : 0.0f) + (this.showServer ? iconFont.getWidth("Q", iconSize) + innerGap + serverW + separatorGap + sepW + separatorGap : 0.0f) + iconFont.getWidth("V", iconSize) + innerGap + timeW;
        if (this.watermarkTransparentBg) {
            this.drawBlurBg(topRowX, y2, topRowWidth, height, radius, 255);
        } else {
            this.drawBackground(topRowX, y2, topRowWidth, height, radius, 255);
        }
        float textY = y2 + (height - textSize) / 2.0f;
        float iconY = y2 + (height - iconSize) / 2.0f;
        float offset = topRowX + padding;
        DrawUtil.drawText(iconFont, "W", offset, iconY, primaryColor, iconSize);
        DrawUtil.drawText(textFont, userText, offset += iconFont.getWidth("W", iconSize) + innerGap, textY, whiteColor, textSize);
        DrawUtil.drawText(textFont, sep, offset += usernameW + separatorGap, textY, ColorProvider.rgba(220, 220, 220, 115.0f), textSize);
        offset += sepW + separatorGap;
        if (this.showFps) {
            DrawUtil.drawText(iconFont, "X", offset, iconY, primaryColor, iconSize);
            DrawUtil.drawText(textFont, fpsValue, offset += iconFont.getWidth("X", iconSize) + innerGap, textY, whiteColor, textSize);
            DrawUtil.drawText(textFont, sep, offset += fpsW + separatorGap, textY, ColorProvider.rgba(220, 220, 220, 115.0f), textSize);
            offset += sepW + separatorGap;
        }
        if (this.showServer) {
            DrawUtil.drawText(iconFont, "Q", offset, iconY, primaryColor, iconSize);
            DrawUtil.drawText(textFont, serverText, offset += iconFont.getWidth("Q", iconSize) + innerGap, textY, whiteColor, textSize);
            DrawUtil.drawText(textFont, sep, offset += serverW + separatorGap, textY, ColorProvider.rgba(220, 220, 220, 115.0f), textSize);
            offset += sepW + separatorGap;
        }
        DrawUtil.drawText(iconFont, "V", offset, iconY, primaryColor, iconSize);
        DrawUtil.drawText(textFont, timeText, offset += iconFont.getWidth("V", iconSize) + innerGap, textY, whiteColor, textSize);
        float topTotalWidth = topRowX + topRowWidth - startX;
        x2 = startX;
        y2 += height + gap;
        float bottomBoxWidth = this.scaled(13.2f);
        if (this.watermarkTransparentBg) {
            this.drawBlurBg(x2, y2, bottomBoxWidth, height, radius, 255);
        } else {
            this.drawBackground(x2, y2, bottomBoxWidth, height, radius, 255);
        }
        DrawUtil.drawText(iconFont, "U", x2 + (bottomBoxWidth - iconFont.getWidth("U", boxIconSize)) / 2.0f - this.scaled(0.55f), y2 + (height - boxIconSize) / 2.0f, primaryColor, boxIconSize);
        float bottomRowX = x2 + bottomBoxWidth + gap;
        float pingW = textFont.getWidth(pingText, textSize);
        float tpsW = this.showTps ? textFont.getWidth(tpsText, textSize) : 0.0f;
        float bpsW = this.showBps ? textFont.getWidth(bpsText, textSize) : 0.0f;
        float pillInnerWidth = iconFont.getWidth("Q", iconSize) + innerGap + sepW + innerGap;
        float pillPadding = padding * 2.0f;
        float pillGap = gap;
        float wPing = pillPadding + pillInnerWidth + pingW;
        if (this.watermarkTransparentBg) {
            this.drawBlurBg(bottomRowX, y2, wPing, height, radius, 255);
        } else {
            this.drawBackground(bottomRowX, y2, wPing, height, radius, 255);
        }
        float pillY = y2 + (height - textSize) / 2.0f;
        DrawUtil.drawText(iconFont, "Q", bottomRowX + padding, y2 + (height - iconSize) / 2.0f, primaryColor, iconSize);
        float pillOff = bottomRowX + padding + iconFont.getWidth("Q", iconSize) + innerGap;
        DrawUtil.drawText(textFont, sep, pillOff, pillY, ColorProvider.rgba(220, 220, 220, 115.0f), textSize);
        DrawUtil.drawText(textFont, pingText, pillOff + sepW + innerGap, pillY, whiteColor, textSize);
        float currentBottomX = bottomRowX + wPing;
        if (this.showTps) {
            float wTps = pillPadding + pillInnerWidth + tpsW;
            float tpsX = currentBottomX + pillGap;
            if (this.watermarkTransparentBg) {
                this.drawBlurBg(tpsX, y2, wTps, height, radius, 255);
            } else {
                this.drawBackground(tpsX, y2, wTps, height, radius, 255);
            }
            DrawUtil.drawText(iconFont, "$", tpsX + padding, y2 + (height - iconSize) / 2.0f, primaryColor, iconSize);
            pillOff = tpsX + padding + iconFont.getWidth("$", iconSize) + innerGap;
            DrawUtil.drawText(textFont, sep, pillOff, pillY, ColorProvider.rgba(220, 220, 220, 115.0f), textSize);
            DrawUtil.drawText(textFont, tpsText, pillOff + sepW + innerGap, pillY, whiteColor, textSize);
            currentBottomX = tpsX + wTps;
        }
        if (this.showBps) {
            float wBps = pillPadding + pillInnerWidth + bpsW;
            float bpsX = currentBottomX + pillGap;
            if (this.watermarkTransparentBg) {
                this.drawBlurBg(bpsX, y2, wBps, height, radius, 255);
            } else {
                this.drawBackground(bpsX, y2, wBps, height, radius, 255);
            }
            DrawUtil.drawText(iconFont, "@", bpsX + padding, y2 + (height - iconSize) / 2.0f, secondaryColor, iconSize);
            pillOff = bpsX + padding + iconFont.getWidth("@", iconSize) + innerGap;
            DrawUtil.drawText(textFont, sep, pillOff, pillY, ColorProvider.rgba(220, 220, 220, 115.0f), textSize);
            DrawUtil.drawText(textFont, bpsText, pillOff + sepW + innerGap, pillY, whiteColor, textSize);
            currentBottomX = bpsX + wBps;
        }
        float bottomTotalWidth = currentBottomX - startX;
        this.watermarkDrag.setWidth(Math.max(topTotalWidth, bottomTotalWidth));
        this.watermarkDrag.setHeight(height * 2.0f + gap);
    }

    private void renderKeyBindsCelestial(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        boolean chatOpen = this.mc.currentScreen instanceof ChatScreen;
        record BindEntry(String label, int keyCode, Animation anim) {
        }
        ArrayList<BindEntry> entries = new ArrayList<BindEntry>();
        for (Module m2 : Babka.getInstance().getModuleStorage().getModules()) {
            if (m2.getKey() != -1 && m2.getAnimation().getValue() > 0.01f) {
                entries.add(new BindEntry(m2.getName(), m2.getKey(), m2.getAnimation()));
            }
            for (Setting s2 : m2.getSettings()) {
                String keyName;
                BooleanSetting bs;
                if (!s2.visible.get().booleanValue() || !(s2 instanceof BooleanSetting) || (bs = (BooleanSetting)s2).getKey() == -1 || !bs.getValue() || (keyName = KeyStorage.getKey(bs.getKey())) == null || keyName.isBlank()) continue;
                entries.add(new BindEntry(s2.getName(), bs.getKey(), bs.getAnimation()));
            }
        }
        boolean showPlaceholder = chatOpen && entries.isEmpty();
        this.keybindsEmptyAnim.run(showPlaceholder ? 1.0f : 0.0f);
        this.alpha.run(entries.isEmpty() && !chatOpen ? 0.0f : 1.0f);
        float globalAlpha = this.alpha.getValue();
        if (globalAlpha <= 0.05f) {
            return;
        }
        int aInt = MathHelper.clamp((int)((int)(255.0f * globalAlpha)), (int)0, (int)255);
        float emptyAnimVal = MathHelper.clamp((float)this.keybindsEmptyAnim.getValue(), (float)0.0f, (float)1.0f);
        String placeholderText = "No active binds";
        float fontSize = 7.5f;
        float headerH = 14.0f;
        float rowH = 9.5f;
        float targetWidth = 70.0f;
        for (BindEntry e3 : entries) {
            String keyText = "[" + KeyStorage.getKey(e3.keyCode()) + "]";
            float rowWidth = Fonts.SFBOLD.get().getWidth(e3.label(), 7.5f) + Fonts.SFBOLD.get().getWidth(keyText, 7.5f) + 20.0f;
            targetWidth = Math.max(targetWidth, rowWidth);
        }
        if (emptyAnimVal > 0.001f) {
            targetWidth = Math.max(targetWidth, Fonts.SFBOLD.get().getWidth("No active binds", 7.5f) + 14.0f);
        }
        this.widthAnim.run(targetWidth);
        float curW = Math.max(70.0f, this.widthAnim.getValue());
        float rowsHeight = (float)entries.stream().mapToDouble(e2 -> 9.5f * MathHelper.clamp((float)e2.anim().getValue(), (float)0.0f, (float)1.0f)).sum();
        float totalH = 14.0f + rowsHeight + ((rowsHeight += 9.5f * emptyAnimVal) > 0.0f ? 3.0f : 1.0f);
        float x2 = this.keyBindsDrag.getX();
        float y2 = this.keyBindsDrag.getY();
        int t1 = ColorProvider.getThemeColor();
        int t2 = ColorProvider.getThemeColorTwo();
        int[] orbital = ColorProvider.getOrbitalRect(t1, t2, 300.0, aInt);
        int[] glow = ColorProvider.getOrbitalRect(t1, t2, 300.0, (int)(110.0f * globalAlpha));
        Matrix4f m2 = context.getMatrices().peek().getPositionMatrix();
        this.drawCelestialGlow(m2, x2, y2, curW, totalH, 4.0f, globalAlpha);
        DrawUtil.drawRound(x2 - 0.5f, y2 - 0.5f, curW + 1.0f, totalH + 1.0f, 4.0f, glow[0], glow[1], glow[2], glow[3]);
        DrawUtil.drawRound(x2, y2, curW, totalH, 4.0f, ColorProvider.rgba(14, 10, 6, aInt));
        ((BuiltRectangle)Builder.rectangle().size(new SizeState(curW + 0.5f, 14.0f)).radius(new QuadRadiusState(4.0f, 0.0f, 0.0f, 4.0f)).color(new QuadColorState(orbital[0], orbital[1], orbital[2], orbital[3])).build()).render(context.getMatrices().peek().getPositionMatrix(), x2, y2);
        float headerTextX = x2 + (curW - Fonts.SFBOLD.get().getWidth("Keybinds", 10.0f)) / 2.0f;
        DrawUtil.drawText(Fonts.SFBOLD.get(), "Keybinds", headerTextX, y2 + 1.0f, ColorProvider.rgba(255, 255, 255, aInt), 10.0f);
        float curY = y2 + 14.0f + 1.0f;
        for (BindEntry e4 : entries) {
            float rowAnim = MathHelper.clamp((float)e4.anim().getValue(), (float)0.0f, (float)1.0f);
            if (rowAnim <= 0.001f) continue;
            float itemHeight = 9.0f * rowAnim;
            int itemAlpha = MathHelper.clamp((int)((int)((float)aInt * rowAnim)), (int)0, (int)255);
            if (itemAlpha >= 4) {
                float textY = curY + itemHeight / 2.0f - 3.75f - 1.0f;
                String key = "[" + KeyStorage.getKey(e4.keyCode()) + "]";
                DrawUtil.drawText(Fonts.SFBOLD.get(), e4.label(), x2 + 5.0f, textY, ColorProvider.rgba(233, 233, 233, itemAlpha), 7.5f);
                float keyX = x2 + curW - Fonts.SFBOLD.get().getWidth(key, 7.5f) - 5.0f;
                DrawUtil.drawText(Fonts.SFBOLD.get(), key, keyX, textY, ColorProvider.rgba(200, 200, 200, itemAlpha), 7.5f);
            }
            curY += itemHeight;
        }
        if (emptyAnimVal > 0.001f) {
            float itemHeight = 9.5f * emptyAnimVal;
            int itemAlpha = MathHelper.clamp((int)((int)((float)aInt * emptyAnimVal)), (int)0, (int)255);
            if (itemAlpha >= 4) {
                float textY = curY + itemHeight / 2.0f - 3.75f;
                float textX = x2 + (curW - Fonts.SFBOLD.get().getWidth("No active binds", 7.5f)) / 2.0f;
                DrawUtil.drawText(Fonts.SFBOLD.get(), "No active binds", textX, textY, ColorProvider.rgba(255, 205, 70, itemAlpha), 7.5f);
            }
            curY += itemHeight;
        }
        this.keyBindsDrag.setWidth(curW);
        this.keyBindsDrag.setHeight(totalH);
    }

    private void renderKeyBinds(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        if (this.hudStyle.is("Celestial")) {
            this.renderKeyBindsCelestial(context);
        } else if (this.hudStyle.is("Nursultan")) {
            this.renderKeyBindsClassic(context);
        } else if (this.hudStyle.is("Babka2")) {
            this.renderKeyBindsBabka2(context);
        } else {
            this.renderKeyBindsBabka(context);
        }
    }

    private void renderKeyBindsBabka(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        boolean chatOpen = this.mc.currentScreen instanceof ChatScreen;
        record BindEntry4(String label, int keyCode, Animation anim) {
        }
        ArrayList<BindEntry4> entries = new ArrayList<BindEntry4>();
        for (Module m2 : Babka.getInstance().getModuleStorage().getModules()) {
            if (m2.getKey() != -1 && m2.getAnimation().getValue() > 0.01f) {
                entries.add(new BindEntry4(m2.getName(), m2.getKey(), m2.getAnimation()));
            }
            for (Setting s2 : m2.getSettings()) {
                String keyName;
                BooleanSetting bs;
                if (!s2.visible.get().booleanValue() || !(s2 instanceof BooleanSetting) || (bs = (BooleanSetting)s2).getKey() == -1 || !bs.getValue() || (keyName = KeyStorage.getKey(bs.getKey())) == null || keyName.isBlank()) continue;
                entries.add(new BindEntry4(s2.getName(), bs.getKey(), bs.getAnimation()));
            }
        }
        this.alpha.run(entries.isEmpty() && !chatOpen ? 0.0f : 1.0f);
        float globalAlpha = this.alpha.getValue();
        if (globalAlpha <= 0.05f) {
            return;
        }
        int aInt = MathHelper.clamp((int)((int)(255.0f * globalAlpha)), (int)0, (int)255);
        float headerHeight = 14.5f;
        float rowHeight = 11.0f;
        float rowSpacing = 1.5f;
        float x2 = this.keyBindsDrag.getX();
        float y2 = this.keyBindsDrag.getY();
        float maxWidth = 65.0f;
        for (BindEntry4 e2 : entries) {
            String bind = "[" + KeyStorage.getKey(e2.keyCode()) + "]";
            float rowWidth = 8.0f + Fonts.SFMEDIUM.get().getWidth(e2.label(), 6.75f) + Fonts.SFMEDIUM.get().getWidth(bind, 6.75f) + 18.0f;
            if (!(rowWidth > maxWidth)) continue;
            maxWidth = rowWidth;
        }
        this.widthAnim.run(maxWidth);
        float width = this.widthAnim.getValue();
        float totalHeight = headerHeight;
        for (BindEntry4 e3 : entries) {
            totalHeight += (rowHeight + rowSpacing) * MathHelper.clamp((float)e3.anim().getValue(), (float)0.0f, (float)1.0f);
        }
        int bgColor = ColorProvider.rgba(25, 25, 25, (int)(190.0f * globalAlpha));
        int blurColor = ColorProvider.rgba(200, 200, 200, aInt);
        int textColor = ColorProvider.rgba(235, 235, 235, aInt);
        int iconColor = ColorProvider.setAlpha(ColorProvider.getColorClient(), aInt);
        if (this.keyBindsTransparentBg) {
            DrawUtil.drawRoundBlur(x2, y2, width, totalHeight, 3.0f, blurColor, 12.0f);
        } else {
            if (this.blurEnabled.getValue()) {
                DrawUtil.drawRoundBlur(x2, y2, width, totalHeight, 3.0f, blurColor, 12.0f);
            }
            DrawUtil.drawRound(x2, y2, width, totalHeight, 3.0f, bgColor);
            DrawUtil.drawRound(x2, y2, width, headerHeight, new Vector4f(3.0f, 3.0f, 0.0f, 0.0f), ColorProvider.rgba(35, 35, 35, (int)(120.0f * globalAlpha)));
        }
        DrawUtil.drawText(Fonts.ICONS_NURIK.get(), "C", x2 + 4.0f, y2 + 4.0f, iconColor, 8.0f);
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), "Hotkeys", x2 + 14.5f, y2 + 3.55f, textColor, 7.5f);
        float currentY = y2 + headerHeight + 1.0f;
        for (BindEntry4 e4 : entries) {
            float rowAnim = MathHelper.clamp((float)e4.anim().getValue(), (float)0.0f, (float)1.0f);
            if (rowAnim <= 0.001f) continue;
            float itemHeight = rowHeight * rowAnim;
            int itemAlpha = MathHelper.clamp((int)((int)((float)aInt * rowAnim)), (int)0, (int)255);
            String bind = "[" + KeyStorage.getKey(e4.keyCode()) + "]";
            float textY = currentY + itemHeight / 2.0f - 3.9f;
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), e4.label(), x2 + 4.5f, textY, ColorProvider.rgba(235, 235, 235, itemAlpha), 6.75f);
            float bindX = x2 + width - Fonts.SFMEDIUM.get().getWidth(bind, 6.75f) - 5.0f;
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), bind, bindX, textY, ColorProvider.rgba(205, 205, 205, itemAlpha), 6.75f);
            currentY += itemHeight + rowSpacing * rowAnim;
        }
        this.keyBindsDrag.setWidth(width);
        this.keyBindsDrag.setHeight(totalHeight);
    }

    private void renderKeyBindsBabka2(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        boolean chatOpen = this.mc.currentScreen instanceof ChatScreen;
        record BindEntry2(String label, int keyCode, Animation anim, ModuleCategory category) {
        }
        ArrayList<BindEntry2> activeModules = new ArrayList<BindEntry2>();
        for (Module m2 : Babka.getInstance().getModuleStorage().getModules()) {
            if (m2.getKey() != -1 && m2.getAnimation().getValue() > 0.01f) {
                activeModules.add(new BindEntry2(m2.getName(), m2.getKey(), m2.getAnimation(), m2.getCategory()));
            }
            for (Setting s2 : m2.getSettings()) {
                String keyName;
                BooleanSetting bs;
                if (!s2.visible.get().booleanValue() || !(s2 instanceof BooleanSetting) || (bs = (BooleanSetting)s2).getKey() == -1 || !bs.getValue() || (keyName = KeyStorage.getKey(bs.getKey())) == null || keyName.isBlank()) continue;
                activeModules.add(new BindEntry2(s2.getName(), bs.getKey(), bs.getAnimation(), m2.getCategory()));
            }
        }
        this.alphaKB2.run(activeModules.isEmpty() && !chatOpen ? 0.0f : 1.0f);
        float globalAlpha = this.alphaKB2.getValue();
        globalAlpha = this.applyBabka2Alpha(globalAlpha);
        if (globalAlpha <= 0.05f) {
            return;
        }
        int aInt = MathHelper.clamp((int)((int)(255.0f * globalAlpha)), (int)0, (int)255);
        float rowH = 14.0f;
        float hdrH = 16.0f;
        float padL = 10.0f;
        float padR = 10.0f;
        float pillW = 3.0f;
        float x2 = this.keyBindsDrag.getX();
        float y2 = this.keyBindsDrag.getY();
        int themeAccent = ColorProvider.getThemeColor();
        int accentR = ColorProvider.red(themeAccent);
        int accentG = ColorProvider.green(themeAccent);
        int accentB = ColorProvider.blue(themeAccent);
        float headerTextW = Fonts.SFMEDIUM.get().getWidth("KeyBinds", 7.0f);
        float minW = padL + headerTextW + padR;
        for (BindEntry2 e2 : activeModules) {
            String bind = "[" + KeyStorage.getKey(e2.keyCode()) + "]";
            float bindW = Fonts.SFMEDIUM.get().getWidth(bind, 6.5f);
            String catIcon = this.getKBCatIcon(e2.category());
            float catIconW = Fonts.ICONS_MINCED.get().getWidth(catIcon, 7.0f) + 5.0f;
            float rw = padL + pillW + 5.0f + catIconW + Fonts.SFMEDIUM.get().getWidth(e2.label(), 6.75f) + 10.0f + bindW + padR;
            if (!(rw > minW)) continue;
            minW = rw;
        }
        this.widthAnimKB2.run(minW);
        float width = this.widthAnimKB2.getValue();
        float totalH = hdrH;
        for (BindEntry2 e3 : activeModules) {
            totalH += rowH * MathHelper.clamp((float)e3.anim().getValue(), (float)0.0f, (float)1.0f);
        }
        totalH += 6.0f;
        int bgAlpha = MathHelper.clamp((int)((int)(235.0f * globalAlpha)), (int)0, (int)255);
        if (this.keyBindsTransparentBg) {
            int blurA = MathHelper.clamp((int)((int)(60.0f * globalAlpha)), (int)0, (int)255);
            DrawUtil.drawRoundBlur(x2, y2, width, totalH, 0.0f, ColorProvider.rgba(120, 120, 120, blurA), 10.0f);
        } else {
            DrawUtil.drawRound(x2, y2, width, totalH, 0.0f, ColorProvider.rgba(18, 14, 36, bgAlpha));
            int hdrAccA = MathHelper.clamp((int)((int)(80.0f * globalAlpha)), (int)0, (int)255);
            DrawUtil.drawRound(x2, y2, width, hdrH, 0.0f, ColorProvider.rgba(accentR, accentG, accentB, hdrAccA));
        }
        float headerCenterX = x2 + width / 2.0f - headerTextW / 2.0f;
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), "HOTKEYS", headerCenterX, y2 + 3.6f, ColorProvider.rgba(Math.min(255, accentR + 60), Math.min(255, accentG + 60), Math.min(255, accentB + 60), aInt), 7.0f);
        float sepY = y2 + hdrH - 0.5f;
        context.fill((int)(x2 + padL - 1.0f), (int)sepY, (int)(x2 + width - padR + 1.0f), (int)(sepY + 0.5f), ColorProvider.rgba(accentR, accentG, accentB, (int)(40.0f * globalAlpha)));
        float curY = y2 + hdrH + 3.0f;
        for (BindEntry2 e4 : activeModules) {
            float rowAnim = MathHelper.clamp((float)e4.anim().getValue(), (float)0.0f, (float)1.0f);
            if (rowAnim <= 0.001f) continue;
            float itemH = rowH * rowAnim;
            int itemAlpha = MathHelper.clamp((int)((int)((float)aInt * rowAnim)), (int)0, (int)255);
            int pillColor = this.getKBCategoryColor(e4.category());
            int pr = pillColor >> 16 & 0xFF;
            int pg = pillColor >> 8 & 0xFF;
            int pb = pillColor & 0xFF;
            float pillX = x2 + padL;
            float pillY = curY + (itemH - 12.0f) / 2.0f;
            DrawUtil.drawRound(pillX, pillY, pillW, 12.0f, 1.5f, ColorProvider.rgba(pr, pg, pb, (int)((float)itemAlpha * 0.8f)));
            String catIcon = this.getKBCatIcon(e4.category());
            float catIconW = Fonts.ICONS_MINCED.get().getWidth(catIcon, 6.75f);
            float textY = curY + itemH / 2.0f - 3.6f;
            DrawUtil.drawText(Fonts.ICONS_MINCED.get(), catIcon, pillX + pillW + 5.0f, textY, ColorProvider.rgba(pr, pg, pb, (int)((float)itemAlpha * 0.9f)), 6.75f);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), e4.label(), pillX + pillW + 5.0f + catIconW + 3.0f, textY, ColorProvider.rgba(230, 230, 240, itemAlpha), 6.75f);
            String bind = "[" + KeyStorage.getKey(e4.keyCode()) + "]";
            float bindW = Fonts.SFMEDIUM.get().getWidth(bind, 6.5f);
            float bindX = x2 + width - bindW - padR;
            DrawUtil.drawRound(bindX - 3.0f, curY + (itemH - 11.0f) / 2.0f, bindW + 6.0f, 11.0f, 3.0f, ColorProvider.rgba(accentR, accentG, accentB, (int)(40.0f * globalAlpha * rowAnim)));
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), bind, bindX, textY, ColorProvider.rgba(180, 170, 255, itemAlpha), 6.5f);
            curY += itemH;
        }
        this.keyBindsDrag.setWidth(width);
        this.keyBindsDrag.setHeight(totalH);
    }

    private String getKBCatIcon(ModuleCategory cat) {
        return switch (cat) {
            default -> throw new MatchException(null, null);
            case ModuleCategory.COMBAT -> "s";
            case ModuleCategory.MOVEMENT -> "q";
            case ModuleCategory.RENDER -> "r";
            case ModuleCategory.PLAYER -> "p";
            case ModuleCategory.MISC -> "m";
        };
    }

    private int getKBCategoryColor(ModuleCategory cat) {
        return switch (cat) {
            default -> throw new MatchException(null, null);
            case ModuleCategory.COMBAT -> 14703708;
            case ModuleCategory.MOVEMENT -> 5163936;
            case ModuleCategory.RENDER -> 5152224;
            case ModuleCategory.PLAYER -> 14723406;
            case ModuleCategory.MISC -> 10518216;
        };
    }

    private void renderKeyBindsClassic(DrawContext context) {
        float globalAlpha;
        boolean isFound;
        if (this.mc.player == null) {
            return;
        }
        float posX = this.keyBindsDrag.getX();
        float posY = this.keyBindsDrag.getY();
        float defaultWidth = 55.0f;
        float height = this.scaled(13.5f);
        float gap = this.scaled(1.56f);
        ArrayList<BindEntry3> entries = new ArrayList<BindEntry3>();
        for (Module module : Babka.getInstance().getModuleStorage().getModules()) {
            if (module.getKey() != -1 && (double)module.getAnimation().getValue() > 0.001) {
                entries.add(new BindEntry3(module.getName(), KeyStorage.getKey(module.getKey()), module.getAnimation().getValue(), module.getCategory()));
            }
            for (Setting setting : module.getSettings()) {
                BooleanSetting bs;
                if (!(setting instanceof BooleanSetting) || (bs = (BooleanSetting)setting).getKey() == -1 || !bs.getValue()) continue;
                String label = module.getName() + " > " + bs.getName();
                entries.add(new BindEntry3(label, KeyStorage.getKey(bs.getKey()), 1.0, module.getCategory()));
            }
        }
        boolean bl = isFound = !entries.isEmpty();
        if (isFound) {
            this.alpha.run(1.0f);
        }
        if (!isFound && !(this.mc.currentScreen instanceof ChatScreen)) {
            this.alpha.run(0.0f);
        }
        if (this.mc.currentScreen instanceof ChatScreen) {
            this.alpha.run(1.0f);
        }
        if ((globalAlpha = this.alpha.getValue()) <= 0.05f) {
            return;
        }
        int headerAlpha = (int)Math.min(255.0f, Math.max(0.0f, 255.0f * globalAlpha));
        int iconColor = ColorProvider.getColorClient();
        if (!this.keyBindsTransparentBg) {
            this.drawBackground(posX, posY, this.widthAnim.getValue(), height, this.scaled(3.0f), headerAlpha);
        }
        DrawUtil.drawText(Fonts.ICONS_NURIK.get(), "C", posX + this.scaled(3.3f), posY + (height - this.scaled(6.8f)) / 2.0f, ColorProvider.setAlpha(iconColor, headerAlpha), this.scaled(6.8f));
        DrawUtil.drawText(Fonts.SFBOLD.get(), "Hotkeys", posX + this.scaled(15.0f), posY + (height - this.scaled(7.1f)) / 2.0f, ColorProvider.rgba(255, 255, 255, headerAlpha), this.scaled(6.1f));
        posY += height + gap;
        float bindWidth = 0.0f;
        float itemFontSize = this.scaled(6.1f);
        for (BindEntry3 entry : entries) {
            float localBindWidth = Fonts.SFBOLD.get().getWidth(entry.bind(), itemFontSize);
            if (!(localBindWidth > bindWidth)) continue;
            bindWidth = localBindWidth;
        }
        this.xLine.run(bindWidth);
        for (BindEntry3 entry : entries) {
            float bindX;
            float animVal = (float)Math.min(1.0, Math.max(0.0, entry.animValue()));
            float itemHeight = this.scaled(11.0f);
            height += itemHeight;
            int itemAlpha = (int)(255.0f * globalAlpha);
            if ((itemAlpha = Math.min(255, Math.max(0, itemAlpha))) < 5) {
                posY += itemHeight;
                continue;
            }
            String bind = entry.bind();
            String label = entry.label();
            float elementsWidth = Fonts.SFBOLD.get().getWidth(label, itemFontSize) + Fonts.SFBOLD.get().getWidth(bind, itemFontSize) + this.scaled(10.0f);
            float textYOffset = (itemHeight - itemFontSize) / 2.0f;
            if (!this.keyBindsTransparentBg) {
                this.drawBackground(posX, posY, this.widthAnim.getValue(), itemHeight, this.scaled(3.0f), itemAlpha);
            }
            int lettersShown = (int)(animVal * (float)(label.length() + 1));
            float charX = posX + this.scaled(3.3f);
            for (int i2 = 0; i2 < label.length(); ++i2) {
                int charAlpha;
                String ch = String.valueOf(label.charAt(i2));
                if (i2 < lettersShown) {
                    charAlpha = itemAlpha;
                } else if (i2 == lettersShown) {
                    float fraction = animVal * (float)(label.length() + 1) - (float)lettersShown;
                    charAlpha = (int)((float)itemAlpha * fraction);
                } else {
                    charAlpha = 0;
                }
                if (charAlpha > 0) {
                    DrawUtil.drawText(Fonts.SFBOLD.get(), ch, charX, posY + textYOffset, ColorProvider.rgba(255, 255, 255, charAlpha), itemFontSize);
                }
                charX += Fonts.SFBOLD.get().getWidth(ch, itemFontSize);
            }
            float bindAnimVal = Math.max(0.0f, (animVal * (float)(label.length() + bind.length() + 2) - (float)label.length()) / (float)(bind.length() + 1));
            int bindLettersShown = (int)(bindAnimVal * (float)(bind.length() + 1));
            float bindCharX = bindX = posX + this.widthAnim.getValue() - this.xLine.getValue() - this.scaled(3.3f);
            for (int i3 = 0; i3 < bind.length(); ++i3) {
                int charAlpha;
                String ch = String.valueOf(bind.charAt(i3));
                if (i3 < bindLettersShown) {
                    charAlpha = itemAlpha;
                } else if (i3 == bindLettersShown) {
                    float fraction = bindAnimVal * (float)(bind.length() + 1) - (float)bindLettersShown;
                    charAlpha = (int)((float)itemAlpha * fraction);
                } else {
                    charAlpha = 0;
                }
                if (charAlpha > 0) {
                    DrawUtil.drawText(Fonts.SFBOLD.get(), ch, bindCharX, posY + textYOffset, ColorProvider.rgba(255, 255, 255, charAlpha), itemFontSize);
                }
                bindCharX += Fonts.SFBOLD.get().getWidth(ch, itemFontSize);
            }
            if (elementsWidth > defaultWidth) {
                defaultWidth = elementsWidth;
            }
            posY += itemHeight;
        }
        this.widthAnim.run(defaultWidth);
        this.keyBindsDrag.setWidth(this.widthAnim.getValue());
        this.keyBindsDrag.setHeight(height);
    }

    private void renderStaffListCelestial(DrawContext context) {
        boolean chatOpen = this.mc.currentScreen instanceof ChatScreen;
        for (Staff staff : this.staffPlayers) {
            staff.animation.run(staff.isOnServer ? 1.0f : 0.0f);
        }
        List<Staff> activeStaff = this.staffPlayers.stream().filter(s2 -> s2.animation.getValue() > 0.01f).toList();
        boolean showPlaceholder = chatOpen && activeStaff.isEmpty();
        this.staffEmptyAnim.run(showPlaceholder ? 1.0f : 0.0f);
        this.alpha2.run(activeStaff.isEmpty() && !chatOpen ? 0.0f : 1.0f);
        float globalAlpha = this.alpha2.getValue();
        if (globalAlpha <= 0.05f) {
            return;
        }
        int aInt = MathHelper.clamp((int)((int)(255.0f * globalAlpha)), (int)0, (int)255);
        float emptyAnimVal = MathHelper.clamp((float)this.staffEmptyAnim.getValue(), (float)0.0f, (float)1.0f);
        String headerText = "Staff Online";
        String placeholderText = "No active staff";
        float fontSize = 7.5f;
        float headerH = 14.0f;
        float rowH = 9.5f;
        float padL = 5.0f;
        float padR = 5.0f;
        float targetWidth = 70.0f;
        for (Staff staff : activeStaff) {
            float prefixW = Fonts.SFBOLD.get().getWidth(staff.prefix.getString(), 7.5f);
            float nameW = Fonts.SFBOLD.get().getWidth(" " + staff.name, 7.5f);
            float rowWidth = 5.0f + prefixW + nameW + 14.0f + 5.0f;
            targetWidth = Math.max(targetWidth, rowWidth);
        }
        if (emptyAnimVal > 0.001f) {
            targetWidth = Math.max(targetWidth, Fonts.SFBOLD.get().getWidth("No active staff", 7.5f) + 14.0f);
        }
        this.widthAnim2.run(targetWidth);
        float curW = Math.max(70.0f, this.widthAnim2.getValue());
        float rowsHeight = (float)activeStaff.stream().mapToDouble(s2 -> 9.5f * MathHelper.clamp((float)s2.animation.getValue(), (float)0.0f, (float)1.0f)).sum();
        float totalH = 14.0f + rowsHeight + ((rowsHeight += 9.5f * emptyAnimVal) > 0.0f ? 3.0f : 1.0f);
        float x2 = this.staffListDrag.getX();
        float y2 = this.staffListDrag.getY();
        int t1 = ColorProvider.getThemeColor();
        int t2 = ColorProvider.getThemeColorTwo();
        int[] orbital = ColorProvider.getOrbitalRect(t1, t2, 300.0, aInt);
        int[] glow = ColorProvider.getOrbitalRect(t1, t2, 300.0, (int)(110.0f * globalAlpha));
        Matrix4f m2 = context.getMatrices().peek().getPositionMatrix();
        this.drawCelestialGlow(m2, x2, y2, curW, totalH, 4.0f, globalAlpha);
        DrawUtil.drawRound(x2 - 0.5f, y2 - 0.5f, curW + 1.0f, totalH + 1.0f, 4.0f, glow[0], glow[1], glow[2], glow[3]);
        DrawUtil.drawRound(x2, y2, curW, totalH, 4.0f, ColorProvider.rgba(14, 10, 6, aInt));
        ((BuiltRectangle)Builder.rectangle().size(new SizeState(curW + 0.5f, 14.0f)).radius(new QuadRadiusState(4.0f, 0.0f, 0.0f, 4.0f)).color(new QuadColorState(orbital[0], orbital[1], orbital[2], orbital[3])).build()).render(context.getMatrices().peek().getPositionMatrix(), x2, y2);
        float headerTextX = x2 + (curW - Fonts.SFBOLD.get().getWidth("Staff Online", 10.0f)) / 2.0f;
        DrawUtil.drawText(Fonts.SFBOLD.get(), "Staff Online", headerTextX, y2 + 1.0f, ColorProvider.rgba(255, 255, 255, aInt), 10.0f);
        float curY = y2 + 14.0f + 1.0f;
        for (Staff staff : activeStaff) {
            float rowAnim = MathHelper.clamp((float)staff.animation.getValue(), (float)0.0f, (float)1.0f);
            if (rowAnim <= 0.001f) continue;
            float itemH = 9.5f * rowAnim;
            int itemA = MathHelper.clamp((int)((int)((float)aInt * rowAnim)), (int)0, (int)255);
            if (itemA >= 4) {
                boolean inNear;
                float textY = curY + itemH / 2.0f - 3.75f - 1.0f;
                DrawUtil.drawText(Fonts.SFBOLD.get(), staff.prefix.getString(), x2 + 5.0f, textY, 7, (float)itemA);
                float prefixW = Fonts.SFBOLD.get().getWidth(staff.prefix.getString(), 7.5f);
                DrawUtil.drawText(Fonts.SFBOLD.get(), " " + staff.name, x2 + 5.0f + prefixW, textY, ColorProvider.rgba(220, 220, 220, itemA), 7.5f);
                boolean bl = inNear = this.mc.world != null && this.mc.world.getPlayers().stream().anyMatch(p2 -> p2.getName().getString().equals(staff.name));
                int statusColor = staff.status == Status.VANISHED || staff.isSpec ? ColorProvider.rgba(255, 50, 50, itemA) : (inNear ? ColorProvider.rgba(255, 215, 0, itemA) : ColorProvider.rgba(50, 255, 50, itemA));
                float r2 = 2.5f;
                float cx = x2 + curW - 5.0f - r2 * 2.0f;
                float cy = curY + itemH / 2.0f - r2;
                DrawUtil.drawRound(cx, cy, r2 * 2.0f, r2 * 2.0f, r2, statusColor);
            }
            curY += itemH;
        }
        if (emptyAnimVal > 0.001f) {
            float itemH = 9.5f * emptyAnimVal;
            int itemA = MathHelper.clamp((int)((int)((float)aInt * emptyAnimVal)), (int)0, (int)255);
            if (itemA >= 4) {
                float textY = curY + itemH / 2.0f - 3.75f - 1.0f;
                float textX = x2 + (curW - Fonts.SFBOLD.get().getWidth("No active staff", 7.5f)) / 2.0f;
                DrawUtil.drawText(Fonts.SFBOLD.get(), "No active staff", textX, textY, ColorProvider.rgba(255, 205, 70, itemA), 7.5f);
            }
            curY += itemH;
        }
        this.staffListDrag.setWidth(curW);
        this.staffListDrag.setHeight(totalH);
    }

    private void renderStaffList(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        if (this.hudStyle.is("Celestial")) {
            this.renderStaffListCelestial(context);
        } else if (this.hudStyle.is("Nursultan")) {
            this.renderStaffListClassic(context);
        } else if (this.hudStyle.is("Babka2")) {
            this.renderStaffListBabka2(context);
        } else {
            this.renderStaffListBabka(context);
        }
    }

    private void renderStaffListBabka(DrawContext context) {
        boolean isFound;
        float posX = this.staffListDrag.getX();
        float posY = this.staffListDrag.getY();
        boolean bl = isFound = !this.staffPlayers.isEmpty();
        if (isFound) {
            this.alpha2.run(1.0f);
        } else if (!(this.mc.currentScreen instanceof ChatScreen)) {
            this.alpha2.run(0.0f);
        } else {
            this.alpha2.run(1.0f);
        }
        float globalAlpha = this.alpha2.getValue();
        if (globalAlpha <= 0.05f) {
            return;
        }
        int aInt = MathHelper.clamp((int)((int)(255.0f * globalAlpha)), (int)0, (int)255);
        float headerHeight = 14.5f;
        float rowHeight = 11.0f;
        float rowSpacing = 1.5f;
        float maxWidth = 65.0f;
        for (Staff staff : this.staffPlayers) {
            float rowWidth = 8.0f + Fonts.SFMEDIUM.get().getWidth(staff.name, 6.75f) + Fonts.SFMEDIUM.get().getWidth(staff.status.string, 6.75f) + 18.0f;
            if (!(rowWidth > maxWidth)) continue;
            maxWidth = rowWidth;
        }
        this.widthAnim2.run(maxWidth);
        float width = this.widthAnim2.getValue();
        float totalHeight = 14.5f;
        for (Staff staff : this.staffPlayers) {
            staff.animation.run(staff.isOnServer ? 1.0f : 0.0f);
            float animVal = MathHelper.clamp((float)staff.animation.getValue(), (float)0.0f, (float)1.0f);
            totalHeight += 12.5f * animVal;
        }
        int bgColor = ColorProvider.rgba(25, 25, 25, (int)(190.0f * globalAlpha));
        int blurColor = ColorProvider.rgba(200, 200, 200, aInt);
        int textColor = ColorProvider.rgba(235, 235, 235, aInt);
        int iconColor = ColorProvider.setAlpha(ColorProvider.getColorClient(), aInt);
        if (this.staffListTransparentBg) {
            DrawUtil.drawRoundBlur(posX, posY, width, totalHeight, 3.0f, blurColor, 12.0f);
        } else {
            if (this.blurEnabled.getValue()) {
                DrawUtil.drawRoundBlur(posX, posY, width, totalHeight, 3.0f, blurColor, 12.0f);
            }
            DrawUtil.drawRound(posX, posY, width, totalHeight, 3.0f, bgColor);
            DrawUtil.drawRound(posX, posY, width, 14.5f, new Vector4f(3.0f, 3.0f, 0.0f, 0.0f), ColorProvider.rgba(35, 35, 35, (int)(120.0f * globalAlpha)));
        }
        DrawUtil.drawText(Fonts.ICONS_NURIK.get(), "O", posX + 4.0f, posY + 4.0f, iconColor, 8.0f);
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), "Staff Online", posX + 13.5f, posY + 3.55f, textColor, 7.5f);
        float currentY = posY + 14.5f + 1.0f;
        for (Staff staff : this.staffPlayers) {
            float animVal = MathHelper.clamp((float)staff.animation.getValue(), (float)0.0f, (float)1.0f);
            if (animVal <= 0.001f) continue;
            float itemHeight = 11.0f * animVal;
            int itemAlpha = MathHelper.clamp((int)((int)((float)aInt * animVal)), (int)0, (int)255);
            float textY = currentY + itemHeight / 2.0f - 3.9f;
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), staff.name, posX + 4.5f, textY, ColorProvider.rgba(235, 235, 235, itemAlpha), 6.75f);
            String statusStr = "[" + staff.status.string + "]";
            float statusX = posX + width - Fonts.SFMEDIUM.get().getWidth(statusStr, 6.75f) - 5.0f;
            int statusColor = staff.status == Status.NONE ? ColorProvider.rgba(100, 220, 100, itemAlpha) : ColorProvider.rgba(220, 100, 100, itemAlpha);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), statusStr, statusX, textY, statusColor, 6.75f);
            currentY += itemHeight + 1.5f * animVal;
        }
        this.staffListDrag.setWidth(width);
        this.staffListDrag.setHeight(totalHeight);
    }

    private void renderStaffListBabka2(DrawContext context) {
        boolean chatOpen = this.mc.currentScreen instanceof ChatScreen;
        boolean isFound = !this.staffPlayers.isEmpty();
        this.alpha2.run(!isFound && !chatOpen ? 0.0f : 1.0f);
        float globalAlpha = this.alpha2.getValue();
        globalAlpha = this.applyBabka2Alpha(globalAlpha);
        if (globalAlpha <= 0.05f) {
            return;
        }
        int aInt = MathHelper.clamp((int)((int)(255.0f * globalAlpha)), (int)0, (int)255);
        float rowH = 14.0f;
        float hdrH = 16.0f;
        float padL = 10.0f;
        float padR = 10.0f;
        float pillW = 3.0f;
        float x2 = this.staffListDrag.getX();
        float y2 = this.staffListDrag.getY();
        int themeAccent = ColorProvider.getThemeColor();
        int accentR = ColorProvider.red(themeAccent);
        int accentG = ColorProvider.green(themeAccent);
        int accentB = ColorProvider.blue(themeAccent);
        float headerTextW = Fonts.SFMEDIUM.get().getWidth("STAFF", 7.0f);
        float minW = padL + headerTextW + padR;
        for (Staff staff : this.staffPlayers) {
            Object label;
            Object object = label = staff.prefix != null ? staff.prefix.getString() : staff.name;
            float headW = 13.0f;
            float labelW = Fonts.SFMEDIUM.get().getWidth((String)label, 6.75f);
            float rw = padL + pillW + 5.0f + headW + labelW + 10.0f + 5.0f + padR;
            if (!(rw > minW)) continue;
            minW = rw;
        }
        this.widthAnim2.run(minW);
        float width = this.widthAnim2.getValue();
        float totalH = hdrH;
        for (Staff staff : this.staffPlayers) {
            totalH += rowH * MathHelper.clamp((float)staff.animation.getValue(), (float)0.0f, (float)1.0f);
        }
        totalH += 6.0f;
        int bgAlpha = MathHelper.clamp((int)((int)(235.0f * globalAlpha)), (int)0, (int)255);
        if (this.staffListTransparentBg) {
            int blurA = MathHelper.clamp((int)((int)(60.0f * globalAlpha)), (int)0, (int)255);
            DrawUtil.drawRoundBlur(x2, y2, width, totalH, 0.0f, ColorProvider.rgba(120, 120, 120, blurA), 10.0f);
        } else {
            DrawUtil.drawRound(x2, y2, width, totalH, 0.0f, ColorProvider.rgba(18, 14, 36, bgAlpha));
            int hdrAccA = MathHelper.clamp((int)((int)(80.0f * globalAlpha)), (int)0, (int)255);
            DrawUtil.drawRound(x2, y2, width, hdrH, 0.0f, ColorProvider.rgba(accentR, accentG, accentB, hdrAccA));
        }
        float headerCenterX = x2 + width / 2.0f - headerTextW / 2.0f;
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), "STAFF", headerCenterX, y2 + 3.6f, ColorProvider.rgba(Math.min(255, accentR + 60), Math.min(255, accentG + 60), Math.min(255, accentB + 60), aInt), 7.0f);
        float sepY = y2 + hdrH - 0.5f;
        context.fill((int)(x2 + padL - 1.0f), (int)sepY, (int)(x2 + width - padR + 1.0f), (int)(sepY + 0.5f), ColorProvider.rgba(accentR, accentG, accentB, (int)(40.0f * globalAlpha)));
        float curY = y2 + hdrH + 3.0f;
        for (Staff staff : this.staffPlayers) {
            staff.animation.run(staff.isOnServer ? 1.0f : 0.0f);
            float rowAnim = MathHelper.clamp((float)staff.animation.getValue(), (float)0.0f, (float)1.0f);
            if (rowAnim <= 0.001f) continue;
            float itemH = rowH * rowAnim;
            int itemAlpha = MathHelper.clamp((int)((int)((float)aInt * rowAnim)), (int)0, (int)255);
            int pillColor = staff.status == Status.NONE ? 5163936 : 14703708;
            int pr = pillColor >> 16 & 0xFF;
            int pg = pillColor >> 8 & 0xFF;
            int pb = pillColor & 0xFF;
            float pillX = x2 + padL;
            float pillY = curY + (itemH - 12.0f) / 2.0f;
            DrawUtil.drawRound(pillX, pillY, pillW, 12.0f, 1.5f, ColorProvider.rgba(pr, pg, pb, (int)((float)itemAlpha * 0.8f)));
            float textY = curY + itemH / 2.0f - 3.6f;
            float headSize = 8.0f;
            float headX = pillX + pillW + 5.0f;
            float headY = curY + itemH / 2.0f - headSize / 2.0f;
            PlayerListEntry entry = this.mc.getNetworkHandler() != null ? this.mc.getNetworkHandler().getPlayerListEntry(staff.name) : null;
            Identifier skinTex = entry != null ? entry.getSkinTextures().comp_1626() : DefaultSkinHelper.getTexture();
            int texId = this.mc.getTextureManager().getTexture(skinTex).getGlId();
            ((BuiltTexture)Builder.texture().size(new SizeState(headSize, headSize)).radius(new QuadRadiusState(2.0f)).color(new QuadColorState(ColorProvider.setAlpha(-1, itemAlpha))).texture(0.125f, 0.125f, 0.125f, 0.125f, texId).smoothness(1.0f).build()).render(context.getMatrices().peek().getPositionMatrix(), headX, headY);
            String label = staff.prefix != null ? staff.prefix.getString() : staff.name;
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), label, headX + headSize + 5.0f, textY, ColorProvider.rgba(230, 230, 240, itemAlpha), 6.75f);
            float dotSize = 5.0f;
            float dotX = x2 + width - dotSize - padR;
            float dotY = curY + itemH / 2.0f - dotSize / 2.0f;
            int dotColor = staff.status == Status.NONE ? ColorProvider.rgba(78, 203, 160, itemAlpha) : ColorProvider.rgba(224, 92, 92, itemAlpha);
            DrawUtil.drawRound(dotX - 3.0f, dotY - 3.0f, dotSize + 6.0f, dotSize + 6.0f, 3.0f, ColorProvider.rgba(accentR, accentG, accentB, (int)(40.0f * globalAlpha * rowAnim)));
            DrawUtil.drawRound(dotX, dotY, dotSize, dotSize, 2.5f, dotColor);
            curY += itemH;
        }
        this.staffListDrag.setWidth(width);
        this.staffListDrag.setHeight(totalH);
    }

    private void renderStaffListClassic(DrawContext context) {
        float globalAlpha;
        float posX = this.staffListDrag.getX();
        float posY = this.staffListDrag.getY();
        float defaultWidth = 64.0f;
        float height = this.scaled(13.5f);
        float gap = this.scaled(1.56f);
        boolean isFound = false;
        if (!this.staffPlayers.isEmpty()) {
            this.alpha2.run(1.0f);
            isFound = true;
        }
        if (!isFound && !(this.mc.currentScreen instanceof ChatScreen)) {
            this.alpha2.run(0.0f);
        }
        if (this.mc.currentScreen instanceof ChatScreen) {
            this.alpha2.run(1.0f);
        }
        if ((globalAlpha = this.alpha2.getValue()) <= 0.05f) {
            return;
        }
        int headerAlpha = (int)Math.min(255.0f, Math.max(0.0f, 255.0f * globalAlpha));
        int iconColor = ColorProvider.getColorClient();
        float fontSize = this.scaled(6.1f);
        if (!this.staffListTransparentBg) {
            this.drawBackground(posX, posY, this.widthAnim2.getValue(), height, this.scaled(3.0f), headerAlpha);
        }
        DrawUtil.drawText(Fonts.ICONS_NURIK.get(), "O", posX + this.scaled(3.3f), posY + (height - this.scaled(6.8f)) / 2.0f, ColorProvider.setAlpha(iconColor, headerAlpha), this.scaled(6.8f));
        DrawUtil.drawText(Fonts.SFBOLD.get(), "Staff Online", posX + this.scaled(15.0f), posY + (height - fontSize) / 3.0f, ColorProvider.rgba(255, 255, 255, headerAlpha), fontSize);
        posY += height + gap;
        float bindWidth = 0.0f;
        float headOffset = this.scaled(10.0f);
        for (Staff staff : this.staffPlayers) {
            staff.animation.run(staff.isOnServer ? 1.0f : 0.0f);
            float localBindWidth = headOffset + Fonts.SFBOLD.get().getWidth(staff.prefix, fontSize) + Fonts.SFBOLD.get().getWidth(staff.status.string, fontSize);
            if (!(localBindWidth > bindWidth)) continue;
            bindWidth = localBindWidth;
        }
        for (Staff staff : this.staffPlayers) {
            float animVal = staff.animation.getValue();
            if (animVal <= 0.001f) continue;
            float heightFactor = Math.min(1.0f, animVal);
            float itemHeight = this.scaled(10.0f) * heightFactor;
            height += itemHeight;
            float alphaFactor = Math.min(1.0f, Math.max(0.0f, animVal));
            int itemAlpha = (int)(255.0f * alphaFactor * globalAlpha);
            if ((itemAlpha = Math.min(255, Math.max(0, itemAlpha))) < 5) {
                posY += itemHeight;
                continue;
            }
            String name = staff.name;
            Text prefix = staff.prefix;
            float elementsWidth = headOffset + Fonts.SFBOLD.get().getWidth(prefix, fontSize) + this.scaled(10.0f);
            float textYOffset = (itemHeight - fontSize) / 2.0f;
            if (!this.staffListTransparentBg) {
                this.drawBackground(posX, posY, this.widthAnim2.getValue(), itemHeight, this.scaled(3.0f), itemAlpha);
            }
            float headSize = this.scaled(7.5f);
            float headX = posX + this.scaled(2.5f);
            float headY = posY + (itemHeight - headSize) / 2.0f;
            PlayerListEntry playerEntry = this.mc.getNetworkHandler().getPlayerListEntry(name);
            Identifier skinTexture = playerEntry != null ? playerEntry.getSkinTextures().comp_1626() : DefaultSkinHelper.getTexture();
            int textureId = this.mc.getTextureManager().getTexture(skinTexture).getGlId();
            BuiltTexture headBuilt = (BuiltTexture)Builder.texture().size(new SizeState(headSize, headSize)).radius(new QuadRadiusState(this.scaled(2.0f))).color(new QuadColorState(ColorProvider.setAlpha(-1, itemAlpha))).texture(0.125f, 0.125f, 0.125f, 0.125f, textureId).smoothness(1.0f).build();
            headBuilt.render(context.getMatrices().peek().getPositionMatrix(), headX, headY);
            DrawUtil.drawText(Fonts.SFBOLD.get(), prefix, posX + this.scaled(2.0f) + headOffset, posY + textYOffset, fontSize, itemAlpha);
            DrawUtil.drawRound(posX + this.widthAnim2.getValue() - this.scaled(7.0f), posY + (itemHeight - this.scaled(4.5f)) / 2.0f, this.scaled(4.5f), this.scaled(4.5f), this.scaled(2.0f), staff.status == Status.NONE ? ColorProvider.rgba(32, 255, 32, itemAlpha) : ColorProvider.rgba(255, 32, 32, itemAlpha));
            if (elementsWidth > defaultWidth) {
                defaultWidth = elementsWidth;
            }
            posY += itemHeight;
        }
        this.widthAnim2.run(defaultWidth);
        this.staffListDrag.setWidth(this.widthAnim2.getValue());
        this.staffListDrag.setHeight(height);
    }

    private void renderPotionsCelestial(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        boolean chatOpen = this.mc.currentScreen instanceof ChatScreen;
        this.potionItems.sort(Comparator.comparing(pi -> pi.name));
        ArrayList<PotionItem> visible = new ArrayList<PotionItem>();
        for (PotionItem item : this.potionItems) {
            item.animation.run(item.active ? 1.0f : 0.0f);
            if (!(item.animation.getValue() > 0.01f)) continue;
            visible.add(item);
        }
        boolean showPlaceholder = chatOpen && visible.isEmpty();
        this.potionsEmptyAnim.run(showPlaceholder ? 1.0f : 0.0f);
        this.alpha3.run(visible.isEmpty() && !chatOpen ? 0.0f : 1.0f);
        float globalAlpha = this.alpha3.getValue();
        if (globalAlpha <= 0.05f) {
            return;
        }
        int aInt = MathHelper.clamp((int)((int)(255.0f * globalAlpha)), (int)0, (int)255);
        float emptyAnimVal = MathHelper.clamp((float)this.potionsEmptyAnim.getValue(), (float)0.0f, (float)1.0f);
        String headerText = "Potions";
        String placeholderText = "No active effects";
        float fontSize = 7.5f;
        float headerH = 14.0f;
        float rowH = 9.5f;
        float padL = 5.0f;
        float padR = 5.0f;
        float targetWidth = 70.0f;
        for (PotionItem item : visible) {
            int totalSec = Math.max(0, item.durationTicks / 20);
            int minutes = totalSec / 60;
            int sec = totalSec % 60;
            String time = String.format("%d:%02d", minutes, sec);
            int lvl = item.amplifier + 1;
            String lvlText = "   " + lvl;
            float nameW = Fonts.SFBOLD.get().getWidth(item.name, 7.5f);
            float lvlW = Fonts.SFBOLD.get().getWidth(lvlText, 7.5f);
            float timeW = Fonts.SFBOLD.get().getWidth(time, 7.5f);
            float rowW = 5.0f + nameW + lvlW + 10.0f + timeW + 5.0f;
            targetWidth = Math.max(targetWidth, rowW);
        }
        if (emptyAnimVal > 0.001f) {
            targetWidth = Math.max(targetWidth, Fonts.SFBOLD.get().getWidth("No active effects", 7.5f) + 14.0f);
        }
        this.widthAnim3.run(targetWidth);
        float curW = Math.max(70.0f, this.widthAnim3.getValue());
        float rowsHeight = 0.0f;
        for (PotionItem item : visible) {
            rowsHeight += 9.5f * MathHelper.clamp((float)item.animation.getValue(), (float)0.0f, (float)1.0f);
        }
        float totalH = 14.0f + rowsHeight + ((rowsHeight += 9.5f * emptyAnimVal) > 0.0f ? 3.0f : 1.0f);
        float x2 = this.potionsDrag.getX();
        float y2 = this.potionsDrag.getY();
        int t1 = ColorProvider.getThemeColor();
        int t2 = ColorProvider.getThemeColorTwo();
        int[] orbital = ColorProvider.getOrbitalRect(t1, t2, 300.0, aInt);
        int[] glow = ColorProvider.getOrbitalRect(t1, t2, 300.0, (int)(110.0f * globalAlpha));
        Matrix4f m2 = context.getMatrices().peek().getPositionMatrix();
        this.drawCelestialGlow(m2, x2, y2, curW, totalH, 4.0f, globalAlpha);
        DrawUtil.drawRound(x2 - 0.5f, y2 - 0.5f, curW + 1.0f, totalH + 1.0f, 4.0f, glow[0], glow[1], glow[2], glow[3]);
        DrawUtil.drawRound(x2, y2, curW, totalH, 4.0f, ColorProvider.rgba(14, 10, 6, aInt));
        ((BuiltRectangle)Builder.rectangle().size(new SizeState(curW + 0.5f, 14.0f)).radius(new QuadRadiusState(4.0f, 0.0f, 0.0f, 4.0f)).color(new QuadColorState(orbital[0], orbital[1], orbital[2], orbital[3])).build()).render(context.getMatrices().peek().getPositionMatrix(), x2, y2);
        float headerTextX = x2 + (curW - Fonts.SFBOLD.get().getWidth("Potions", 10.0f)) / 2.0f;
        DrawUtil.drawText(Fonts.SFBOLD.get(), "Potions", headerTextX, y2 + 1.0f, ColorProvider.rgba(255, 255, 255, aInt), 10.0f);
        float curY = y2 + 14.0f + 1.0f;
        for (PotionItem item : visible) {
            float rowAnim = MathHelper.clamp((float)item.animation.getValue(), (float)0.0f, (float)1.0f);
            if (rowAnim <= 0.001f) continue;
            float itemH = 9.5f * rowAnim;
            int itemA = MathHelper.clamp((int)((int)((float)aInt * rowAnim)), (int)0, (int)255);
            if (itemA >= 4) {
                int lvlColor;
                int totalSec = Math.max(0, item.durationTicks / 20);
                int minutes = totalSec / 60;
                int sec = totalSec % 60;
                String time = String.format("%d:%02d", minutes, sec);
                int lvl = item.amplifier + 1;
                String lvlText = "   " + lvl;
                float timeW = Fonts.SFBOLD.get().getWidth(time, 7.5f);
                float timeX = x2 + curW - timeW - 5.0f;
                float leftX = x2 + 5.0f;
                float textY = curY + itemH / 2.0f - 3.75f - 1.0f;
                float clipW = Math.max(0.0f, timeX - 6.0f - leftX);
                Scissor.push();
                Scissor.setFromComponentCoordinates(leftX, curY, clipW, itemH);
                DrawUtil.drawText(Fonts.SFBOLD.get(), item.name, leftX, textY, ColorProvider.rgba(233, 233, 233, itemA), 7.5f);
                float nameW = Fonts.SFBOLD.get().getWidth(item.name, 7.5f);
                int n2 = lvlColor = lvl >= 2 ? ColorProvider.rgba(192, 100, 106, itemA) : ColorProvider.rgba(200, 200, 200, itemA);
                if (lvl > 1) {
                    DrawUtil.drawText(Fonts.SFBOLD.get(), lvlText, leftX + nameW, textY, lvlColor, 7.5f);
                }
                Scissor.unset();
                Scissor.pop();
                DrawUtil.drawText(Fonts.SFBOLD.get(), time, timeX, textY, ColorProvider.rgba(200, 200, 200, itemA), 7.5f);
            }
            curY += itemH;
        }
        if (emptyAnimVal > 0.001f) {
            float itemH = 9.5f * emptyAnimVal;
            int itemA = MathHelper.clamp((int)((int)((float)aInt * emptyAnimVal)), (int)0, (int)255);
            if (itemA >= 4) {
                float textY = curY + itemH / 2.0f - 3.75f;
                float textX = x2 + (curW - Fonts.SFBOLD.get().getWidth("No active effects", 7.5f)) / 2.0f;
                DrawUtil.drawText(Fonts.SFBOLD.get(), "No active effects", textX, textY, ColorProvider.rgba(255, 205, 70, itemA), 7.5f);
            }
            curY += itemH;
        }
        this.potionsDrag.setWidth(curW);
        this.potionsDrag.setHeight(totalH);
    }

    private void renderPotions(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        if (this.hudStyle.is("Celestial")) {
            this.renderPotionsCelestial(context);
        } else if (this.hudStyle.is("Nursultan")) {
            this.renderPotionsClassic(context);
        } else if (this.hudStyle.is("Babka2")) {
            this.renderPotionsBabka2(context);
        } else {
            this.renderPotionsBabka(context);
        }
    }

    private void renderPotionsBabka(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        this.potionItems.sort(Comparator.comparing(pi -> pi.name));
        ArrayList<PotionItem> visible = new ArrayList<PotionItem>();
        for (PotionItem item : this.potionItems) {
            item.animation.run(item.active ? 1.0f : 0.0f);
            if (!(item.animation.getValue() > 0.01f)) continue;
            visible.add(item);
        }
        this.alpha3.run(visible.isEmpty() ? 0.0f : 1.0f);
        float globalAlpha = this.alpha3.getValue();
        if (globalAlpha <= 0.05f) {
            return;
        }
        int aInt = MathHelper.clamp((int)((int)(255.0f * globalAlpha)), (int)0, (int)255);
        float headerHeight = 14.5f;
        float rowHeight = 11.0f;
        float rowSpacing = 1.5f;
        float x2 = this.potionsDrag.getX();
        float y2 = this.potionsDrag.getY();
        float maxWidth = 68.0f;
        for (PotionItem item : visible) {
            float rowWidth;
            int totalSec = Math.max(0, item.durationTicks / 20);
            String time = String.format("%02d:%02d", totalSec / 60, totalSec % 60);
            Object effectName = item.name;
            if (item.amplifier > 0) {
                effectName = (String)effectName + " " + (item.amplifier + 1);
            }
            if (!((rowWidth = 8.0f + Fonts.SFMEDIUM.get().getWidth((String)effectName, 6.75f) + Fonts.SFMEDIUM.get().getWidth(time, 6.75f) + 18.0f) > maxWidth)) continue;
            maxWidth = rowWidth;
        }
        this.widthAnim3.run(maxWidth);
        float width = this.widthAnim3.getValue();
        float totalHeight = headerHeight;
        for (PotionItem item : visible) {
            totalHeight += (rowHeight + rowSpacing) * MathHelper.clamp((float)item.animation.getValue(), (float)0.0f, (float)1.0f);
        }
        int bgColor = ColorProvider.rgba(25, 25, 25, (int)(190.0f * globalAlpha));
        int blurColor = ColorProvider.rgba(200, 200, 200, aInt);
        int textColor = ColorProvider.rgba(235, 235, 235, aInt);
        int iconColor = ColorProvider.setAlpha(ColorProvider.getColorClient(), aInt);
        if (this.potionsTransparentBg) {
            DrawUtil.drawRoundBlur(x2, y2, width, totalHeight, 3.0f, blurColor, 12.0f);
        } else {
            if (this.blurEnabled.getValue()) {
                DrawUtil.drawRoundBlur(x2, y2, width, totalHeight, 3.0f, blurColor, 12.0f);
            }
            DrawUtil.drawRound(x2, y2, width, totalHeight, 3.0f, bgColor);
            DrawUtil.drawRound(x2, y2, width, headerHeight, new Vector4f(3.0f, 3.0f, 0.0f, 0.0f), ColorProvider.rgba(35, 35, 35, (int)(120.0f * globalAlpha)));
        }
        DrawUtil.drawText(Fonts.ICONS_NURIK.get(), "E", x2 + 4.0f, y2 + 4.0f, iconColor, 8.0f);
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), "Potions", x2 + 13.5f, y2 + 3.55f, textColor, 7.5f);
        float currentY = y2 + headerHeight + 1.0f;
        for (PotionItem item : visible) {
            float rowAnim = MathHelper.clamp((float)item.animation.getValue(), (float)0.0f, (float)1.0f);
            if (rowAnim <= 0.001f) continue;
            float itemHeight = rowHeight * rowAnim;
            int itemAlpha = MathHelper.clamp((int)((int)((float)aInt * rowAnim)), (int)0, (int)255);
            int totalSec = Math.max(0, item.durationTicks / 20);
            String time = String.format("%02d:%02d", totalSec / 60, totalSec % 60);
            Object effectName = item.name;
            if (item.amplifier > 0) {
                effectName = (String)effectName + " " + (item.amplifier + 1);
            }
            float textY = currentY + itemHeight / 2.0f - 3.9f;
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), (String)effectName, x2 + 4.5f, textY, ColorProvider.rgba(235, 235, 235, itemAlpha), 6.75f);
            float timeX = x2 + width - Fonts.SFMEDIUM.get().getWidth(time, 6.75f) - 5.0f;
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), time, timeX, textY, ColorProvider.rgba(205, 205, 205, itemAlpha), 6.75f);
            currentY += itemHeight + rowSpacing * rowAnim;
        }
        this.potionsDrag.setWidth(width);
        this.potionsDrag.setHeight(totalHeight);
    }

    private void renderPotionsBabka2(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        boolean chatOpen = this.mc.currentScreen instanceof ChatScreen;
        this.potionItems.sort(Comparator.comparing(pi -> pi.name));
        for (PotionItem item : this.potionItems) {
            item.animation.run(item.active ? 1.0f : 0.0f);
        }
        boolean isFound = this.potionItems.stream().anyMatch(p2 -> p2.animation.getValue() > 0.001f);
        this.alpha3.run(!isFound && !chatOpen ? 0.0f : 1.0f);
        float globalAlpha = this.alpha3.getValue();
        globalAlpha = this.applyBabka2Alpha(globalAlpha);
        if (globalAlpha <= 0.05f) {
            return;
        }
        int aInt = MathHelper.clamp((int)((int)(255.0f * globalAlpha)), (int)0, (int)255);
        float rowH = 14.0f;
        float hdrH = 16.0f;
        float padL = 10.0f;
        float padR = 10.0f;
        float pillW = 3.0f;
        float x2 = this.potionsDrag.getX();
        float y2 = this.potionsDrag.getY();
        int themeAccent = ColorProvider.getThemeColor();
        int accentR = ColorProvider.red(themeAccent);
        int accentG = ColorProvider.green(themeAccent);
        int accentB = ColorProvider.blue(themeAccent);
        float headerTextW = Fonts.SFMEDIUM.get().getWidth("POTIONS", 7.0f);
        float minW = padL + headerTextW + padR;
        for (PotionItem item : this.potionItems) {
            String name;
            if (item.animation.getValue() <= 0.001f) continue;
            int sec = item.durationTicks / 20;
            String dur = String.format("%d:%02d", sec / 60, sec % 60);
            float durW = Fonts.SFMEDIUM.get().getWidth(dur, 6.5f);
            String string = name = item.amplifier >= 1 ? item.name + " " + (item.amplifier + 1) : item.name;
            float nameW = Fonts.SFMEDIUM.get().getWidth(name, 6.75f);
            float rw = padL + pillW + 5.0f + nameW + 10.0f + durW + padR;
            if (!(rw > minW)) continue;
            minW = rw;
        }
        this.widthAnim3.run(minW);
        float width = this.widthAnim3.getValue();
        float totalH = hdrH;
        for (PotionItem item : this.potionItems) {
            totalH += rowH * MathHelper.clamp((float)item.animation.getValue(), (float)0.0f, (float)1.0f);
        }
        totalH += 6.0f;
        int bgAlpha = MathHelper.clamp((int)((int)(235.0f * globalAlpha)), (int)0, (int)255);
        if (this.potionsTransparentBg) {
            int blurA = MathHelper.clamp((int)((int)(60.0f * globalAlpha)), (int)0, (int)255);
            DrawUtil.drawRoundBlur(x2, y2, width, totalH, 0.0f, ColorProvider.rgba(120, 120, 120, blurA), 10.0f);
        } else {
            DrawUtil.drawRound(x2, y2, width, totalH, 0.0f, ColorProvider.rgba(18, 14, 36, bgAlpha));
            int hdrAccA = MathHelper.clamp((int)((int)(80.0f * globalAlpha)), (int)0, (int)255);
            DrawUtil.drawRound(x2, y2, width, hdrH, 0.0f, ColorProvider.rgba(accentR, accentG, accentB, hdrAccA));
        }
        float headerCenterX = x2 + width / 2.0f - headerTextW / 2.0f;
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), "POTIONS", headerCenterX, y2 + 3.6f, ColorProvider.rgba(Math.min(255, accentR + 60), Math.min(255, accentG + 60), Math.min(255, accentB + 60), aInt), 7.0f);
        float sepY = y2 + hdrH - 0.5f;
        context.fill((int)(x2 + padL - 1.0f), (int)sepY, (int)(x2 + width - padR + 1.0f), (int)(sepY + 0.5f), ColorProvider.rgba(accentR, accentG, accentB, (int)(40.0f * globalAlpha)));
        float curY = y2 + hdrH + 3.0f;
        for (PotionItem item : this.potionItems) {
            float rowAnim = MathHelper.clamp((float)item.animation.getValue(), (float)0.0f, (float)1.0f);
            if (rowAnim <= 0.001f) continue;
            float itemH = rowH * rowAnim;
            int itemAlpha = MathHelper.clamp((int)((int)((float)aInt * rowAnim)), (int)0, (int)255);
            int pillColor = this.getPotionPillColor(item);
            int pr = pillColor >> 16 & 0xFF;
            int pg = pillColor >> 8 & 0xFF;
            int pb = pillColor & 0xFF;
            float pillX = x2 + padL;
            float pillY = curY + (itemH - 12.0f) / 2.0f;
            DrawUtil.drawRound(pillX, pillY, pillW, 12.0f, 1.5f, ColorProvider.rgba(pr, pg, pb, (int)((float)itemAlpha * 0.8f)));
            float textY = curY + itemH / 2.0f - 3.6f;
            String name = item.amplifier >= 1 ? item.name + " " + (item.amplifier + 1) : item.name;
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), name, pillX + pillW + 5.0f, textY, ColorProvider.rgba(230, 230, 240, itemAlpha), 6.75f);
            int sec = item.durationTicks / 20;
            String dur = String.format("%d:%02d", sec / 60, sec % 60);
            float durW = Fonts.SFMEDIUM.get().getWidth(dur, 6.5f);
            float durX = x2 + width - durW - padR;
            DrawUtil.drawRound(durX - 3.0f, curY + (itemH - 11.0f) / 2.0f, durW + 6.0f, 11.0f, 3.0f, ColorProvider.rgba(accentR, accentG, accentB, (int)(40.0f * globalAlpha * rowAnim)));
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), dur, durX, textY, ColorProvider.rgba(180, 170, 255, itemAlpha), 6.5f);
            curY += itemH;
        }
        this.potionsDrag.setWidth(width);
        this.potionsDrag.setHeight(totalH);
    }

    private int getPotionPillColor(PotionItem item) {
        boolean harmful;
        StatusEffect effect = (StatusEffect)item.effect.comp_349();
        StatusEffectCategory cat = effect.getCategory();
        boolean beneficial = cat == StatusEffectCategory.BENEFICIAL;
        boolean bl = harmful = cat == StatusEffectCategory.HARMFUL;
        if (beneficial) {
            return 5163936;
        }
        if (harmful) {
            return 14703708;
        }
        return 10518216;
    }

    private void renderPotionsClassic(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        float posX = this.potionsDrag.getX();
        float posY = this.potionsDrag.getY();
        float fontSize = this.scaled(6.1f);
        float iconSize = this.scaled(6.8f);
        float headerIconW = Fonts.ICONS_NURIK.get().getWidth("E", iconSize);
        float headerTextW = Fonts.SFBOLD.get().getWidth("Active Potions", fontSize);
        float defaultWidth = headerIconW + headerTextW + this.scaled(15.0f);
        float height = this.scaled(13.5f);
        float gap = this.scaled(1.56f);
        this.potionItems.sort(Comparator.comparing(pi -> pi.name));
        boolean isFound = false;
        for (PotionItem item : this.potionItems) {
            int secondsLeft = item.durationTicks / 20;
            float target = item.active ? 1.0f : 0.0f;
            item.animation.run(target);
            if (item.animation.getValue() > 0.001f) {
                isFound = true;
            }
            float nameW = Fonts.SFBOLD.get().getWidth(item.name, fontSize);
            float ampW = item.amplifier >= 1 ? Fonts.SFBOLD.get().getWidth(" " + (item.amplifier + 1), fontSize) : 0.0f;
            Object[] objectArray = new Object[]{secondsLeft / 60, secondsLeft % 60};
            float timeW = Fonts.SFBOLD.get().getWidth(String.format("%d:%02d", objectArray), fontSize);
            float moduleWidth = nameW + ampW + timeW + this.scaled(20.0f);
            if (!(moduleWidth > defaultWidth)) continue;
            defaultWidth = moduleWidth;
        }
        if (!isFound && !(this.mc.currentScreen instanceof ChatScreen)) {
            this.alpha3.run(0.0f);
        } else {
            this.alpha3.run(1.0f);
        }
        float globalAlpha = this.alpha3.getValue();
        if (globalAlpha <= 0.05f) {
            return;
        }
        int headerAlpha = (int)(255.0f * globalAlpha);
        int iconColor = ColorProvider.getColorClient();
        this.widthAnim3.run(defaultWidth);
        float currentWidth = Math.max(20.0f, this.widthAnim3.getValue());
        if (!this.potionsTransparentBg) {
            this.drawBackground(posX, posY, currentWidth, height, this.scaled(3.0f), headerAlpha);
        }
        DrawUtil.drawText(Fonts.ICONS_NURIK.get(), "E", posX + this.scaled(3.3f), posY + (height - iconSize) / 2.0f, ColorProvider.setAlpha(iconColor, headerAlpha), iconSize);
        DrawUtil.drawText(Fonts.SFBOLD.get(), "Active Potions", posX + this.scaled(14.0f), posY + (height - fontSize) / 3.0f, ColorProvider.rgba(255, 255, 255, headerAlpha), fontSize);
        posY += height + gap;
        this.xLine2.run(this.scaled(10.0f));
        for (PotionItem item : this.potionItems) {
            boolean lowTime;
            float animVal = item.animation.getValue();
            if (animVal <= 0.001f) continue;
            float itemHeight = this.scaled(10.0f) * animVal;
            height += itemHeight;
            int baseAlpha = (int)(255.0f * animVal * globalAlpha);
            if (baseAlpha < 5) {
                posY += itemHeight;
                continue;
            }
            int secondsLeft = item.durationTicks / 20;
            String timeStr = String.format("%d:%02d", secondsLeft / 60, secondsLeft % 60);
            boolean isNegative = ((StatusEffect)item.effect.comp_349()).getCategory() == StatusEffectCategory.HARMFUL;
            boolean bl = lowTime = secondsLeft <= 5 && secondsLeft > 0;
            float blinkFactor = lowTime ? (System.currentTimeMillis() % 700L < 350L ? 1.0f : 0.32f) : 1.0f;
            int itemAlpha = (int)((float)baseAlpha * blinkFactor);
            float textYOffset = (itemHeight - fontSize) / 2.0f;
            if (!this.potionsTransparentBg) {
                this.drawBackground(posX, posY, currentWidth, itemHeight, this.scaled(3.0f), baseAlpha);
            }
            int nameColor = isNegative ? ColorProvider.rgba(255, 85, 85, itemAlpha) : ColorProvider.rgba(255, 255, 255, itemAlpha);
            float nameX = posX + this.scaled(3.3f);
            DrawUtil.drawText(Fonts.SFBOLD.get(), item.name, nameX, posY + textYOffset, nameColor, fontSize);
            float nameW = Fonts.SFBOLD.get().getWidth(item.name, fontSize);
            if (item.amplifier >= 1) {
                DrawUtil.drawText(Fonts.SFBOLD.get(), " " + (item.amplifier + 1), nameX + nameW, posY + textYOffset, ColorProvider.rgba(211, 211, 211, itemAlpha), fontSize);
            }
            float separatorX = posX + currentWidth - this.xLine2.getValue() - this.scaled(3.3f);
            DrawUtil.drawRound(separatorX, posY + this.scaled(1.5f), this.scaled(0.6f), itemHeight - this.scaled(3.0f), 0.0f, ColorProvider.rgba(88, 88, 88, baseAlpha));
            float timeWidth = Fonts.SFBOLD.get().getWidth(timeStr, fontSize);
            DrawUtil.drawText(Fonts.SFBOLD.get(), timeStr, separatorX - timeWidth, posY + textYOffset, ColorProvider.rgba(255, 255, 255, itemAlpha), fontSize);
            Sprite sprite = this.mc.getStatusEffectSpriteManager().getSprite(item.effect);
            if (sprite != null) {
                float spriteAlpha = (float)itemAlpha / 255.0f;
                RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)spriteAlpha);
                float spriteSize = this.scaled(7.5f);
                float spriteX = separatorX + this.scaled(2.5f);
                float spriteY = posY + (itemHeight - spriteSize) / 2.0f;
                context.drawSpriteStretched(RenderLayer::getGuiTextured, sprite, (int)spriteX, (int)spriteY, (int)spriteSize, (int)spriteSize, itemAlpha << 24 | 0xFFFFFF);
                RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
            posY += itemHeight;
        }
        this.potionsDrag.setWidth(currentWidth);
        this.potionsDrag.setHeight(height);
    }

    private void renderTotemCounter(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        if (this.hudStyle.is("Nursultan")) {
            this.renderTotemCounterNursultan(context);
        } else if (this.hudStyle.is("Babka2")) {
            this.renderTotemCounterBabka2(context);
        } else {
            this.renderTotemCounterBabka(context);
        }
    }

    private void renderTotemCounterNursultan(DrawContext context) {
        float posX = this.totemCounterDrag.getX();
        float posY = this.totemCounterDrag.getY();
        int totemCount = 0;
        for (int i2 = 0; i2 < this.mc.player.getInventory().size(); ++i2) {
            if (this.mc.player.getInventory().getStack(i2).getItem() != Items.TOTEM_OF_UNDYING) continue;
            totemCount += this.mc.player.getInventory().getStack(i2).getCount();
        }
        String text = totemCount + "x";
        float height = this.scaled(13.5f);
        float fontSize = this.scaled(6.1f);
        float itemSize = height - this.scaled(3.0f);
        float width = itemSize + this.scaled(2.0f) + Fonts.SFBOLD.get().getWidth(text, fontSize) + this.scaled(6.0f);
        int alpha = 255;
        this.drawBackground(posX, posY, width, height, this.scaled(3.0f), alpha);
        context.getMatrices().push();
        context.getMatrices().translate(posX + this.scaled(1.5f), posY + (height - itemSize) / 2.0f, 0.0f);
        float itemScale = itemSize / 16.0f;
        context.getMatrices().scale(itemScale, itemScale, 1.0f);
        context.drawItem(new ItemStack((ItemConvertible)Items.TOTEM_OF_UNDYING), 0, 0);
        context.getMatrices().pop();
        DrawUtil.drawText(Fonts.SFBOLD.get(), text, posX + itemSize + this.scaled(3.0f), posY + (height - fontSize) / 2.0f, ColorProvider.rgba(255, 255, 255, alpha), fontSize);
        this.totemCounterDrag.setWidth(width);
        this.totemCounterDrag.setHeight(height);
    }

    private void renderTotemCounterBabka(DrawContext context) {
        int totemCount = 0;
        for (int i2 = 0; i2 < this.mc.player.getInventory().size(); ++i2) {
            ItemStack stack = this.mc.player.getInventory().getStack(i2);
            if (stack.getItem() != Items.TOTEM_OF_UNDYING) continue;
            totemCount += stack.getCount();
        }
        String text = totemCount + "x";
        float screenW = this.mc.getWindow().getScaledWidth();
        float screenH = this.mc.getWindow().getScaledHeight();
        float hotbarY = screenH - 22.0f - 1.0f;
        float hotbarRight = screenW / 2.0f + 91.0f;
        float x2 = hotbarRight + 2.0f;
        float y2 = hotbarY;
        float slotW = 22.0f;
        float slotH = 22.0f;
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        context.drawTexture(RenderLayer::getGuiTextured, Identifier.of((String)"minecraft", (String)"textures/gui/sprites/hud/hotbar.png"), (int)x2, (int)y2, 0.0f, 0.0f, (int)slotW, (int)slotH, 182, 22);
        context.drawItem(new ItemStack((ItemConvertible)Items.TOTEM_OF_UNDYING), (int)(x2 + 3.0f), (int)(y2 + 3.0f));
        float textW = Fonts.SFMEDIUM.get().getWidth(text, 6.0f);
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), text, x2 + slotW - textW - 1.0f, y2 + slotH - 8.0f, ColorProvider.rgba(255, 255, 255, 255.0f), 6.0f);
        this.totemCounterDrag.setWidth(slotW);
        this.totemCounterDrag.setHeight(slotH);
    }

    private void renderTotemCounterBabka2(DrawContext context) {
        float posX = this.totemCounterDrag.getX();
        float posY = this.totemCounterDrag.getY();
        int totemCount = 0;
        for (int i2 = 0; i2 < this.mc.player.getInventory().size(); ++i2) {
            if (this.mc.player.getInventory().getStack(i2).getItem() != Items.TOTEM_OF_UNDYING) continue;
            totemCount += this.mc.player.getInventory().getStack(i2).getCount();
        }
        String text = totemCount + "x";
        float width = Fonts.SFMEDIUM.get().getWidth(text, 9.0f) + 18.0f;
        float height = 14.0f;
        context.getMatrices().push();
        context.getMatrices().translate(posX + 2.0f, posY + 2.0f, 0.0f);
        context.getMatrices().scale(0.79f, 0.79f, 0.79f);
        context.drawItem(new ItemStack((ItemConvertible)Items.TOTEM_OF_UNDYING), 0, 0);
        context.getMatrices().pop();
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), text, posX + 15.0f, posY + 4.5f, -1, 9.0f);
        this.totemCounterDrag.setWidth(width);
        this.totemCounterDrag.setHeight(height);
    }

    private void renderTargetHUDCelestial(DrawContext context) {
        AttackAura attackAura = Instance.get(AttackAura.class);
        boolean chatOpen = this.mc.currentScreen instanceof ChatScreen;
        LivingEntity target = null;
        if (attackAura != null && attackAura.isEnabled() && attackAura.getTarget() != null && attackAura.getTarget().isAlive()) {
            target = attackAura.getTarget();
        } else {
            LivingEntity living;
            Entity entity = this.mc.targetedEntity;
            if (entity instanceof LivingEntity && (living = (LivingEntity)entity).isAlive()) {
                target = living;
            } else if (chatOpen) {
                target = this.mc.player;
            }
        }
        if (target != null) {
            this.lastTarget = target;
            this.animation.run(1.0f);
        } else {
            this.animation.run(0.0f);
        }
        if (this.animation.getValue() <= 0.05f || this.lastTarget == null || !(this.lastTarget instanceof LivingEntity)) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity)this.lastTarget;
        AbstractClientPlayerEntity playerEntity = this.lastTarget instanceof AbstractClientPlayerEntity ? (AbstractClientPlayerEntity)this.lastTarget : null;
        float anim = this.animation.getValue();
        int alphaInt = (int)(255.0f * anim);
        float width = 100.0f;
        float height = 37.0f;
        float x2 = this.targetHUDDrag.getX();
        float y2 = this.targetHUDDrag.getY();
        Matrix4f m2 = context.getMatrices().peek().getPositionMatrix();
        this.drawCelestialGlow(m2, x2, y2, width, height, 5.0f, anim);
        int theme1 = ColorProvider.getThemeColor();
        int theme2 = ColorProvider.getThemeColorTwo();
        int[] glow = ColorProvider.getOrbitalRect(theme1, theme2, 300.0, (int)(110.0f * anim));
        DrawUtil.drawRound(x2 - 0.5f, y2 - 0.5f, width + 1.0f, height + 1.0f, 5.0f, glow[0], glow[1], glow[2], glow[3]);
        DrawUtil.drawRound(x2, y2, width, height, 5.0f, ColorProvider.rgba(14, 10, 6, alphaInt));
        float headSize = 30.0f;
        float headX = x2 + 2.5f;
        float headY = y2 + 3.75f;
        int headColor = ColorProvider.rgba(255, (int)(255.0f * (1.0f - (float)livingEntity.hurtTime / 10.0f)), (int)(255.0f * (1.0f - (float)livingEntity.hurtTime / 10.0f)), alphaInt);
        if (playerEntity != null) {
            try {
                int texId = this.mc.getTextureManager().getTexture(playerEntity.getSkinTextures().comp_1626()).getGlId();
                ((BuiltTexture)Builder.texture().size(new SizeState(headSize, headSize)).radius(new QuadRadiusState(3.0f)).color(new QuadColorState(headColor)).texture(0.125f, 0.125f, 0.125f, 0.125f, texId).smoothness(1.0f).build()).render(context.getMatrices().peek().getPositionMatrix(), headX, headY);
            }
            catch (Exception texId) {}
        } else {
            SpawnEggItem spawnEgg = SpawnEggItem.forEntity((EntityType)livingEntity.getType());
            if (spawnEgg != null) {
                context.getMatrices().push();
                context.getMatrices().translate((double)(headX + headSize / 2.0f), (double)(headY + headSize / 2.0f), 50.0);
                float animatedScale = headSize / 16.0f * anim;
                context.getMatrices().scale(animatedScale, animatedScale, 1.0f);
                context.getMatrices().translate(-8.0, -8.0, 0.0);
                context.drawItem(new ItemStack((ItemConvertible)spawnEgg), 0, 0);
                context.getMatrices().pop();
            } else {
                DrawUtil.drawRound(headX, headY, headSize, headSize, 3.0f, ColorProvider.rgba(40, 40, 40, alphaInt));
                DrawUtil.drawText(Fonts.ICONS_NURIK.get(), "N", headX + 1.5f, headY + 8.0f, ColorProvider.rgba(255, 255, 255, alphaInt), 24.0f);
            }
        }
        float textX = headX + headSize + 2.0f;
        StreamerMode nameProtect = Instance.get(StreamerMode.class);
        String name = nameProtect.isEnabled() ? nameProtect.getCustomName(livingEntity.getName().getString()) : livingEntity.getName().getString();
        DrawUtil.drawText(Fonts.SFBOLD.get(), name, textX, y2 + 7.0f, ColorProvider.rgba(230, 230, 230, alphaInt), 9.0f, 0.3f, 0.7f, width);
        float currentHp = Math.max(0.0f, livingEntity.getHealth());
        float absHp = Math.max(0.0f, livingEntity.getAbsorptionAmount());
        float maxHealth = Math.max(1.0f, livingEntity.getMaxHealth());
        float barX = textX - 1.0f;
        float barY = y2 + 25.0f;
        float barHeight = 7.5f;
        float barWidth = width - headSize - 10.0f;
        this.hpAnimation.run(barWidth * MathHelper.clamp((float)(currentHp / maxHealth), (float)0.0f, (float)1.0f));
        float hpWNow = this.hpAnimation.getValue();
        float animatedHp = barWidth > 0.0f ? hpWNow / barWidth * maxHealth : currentHp;
        String hpText = String.format(Locale.US, "HP: %.1f", Float.valueOf(animatedHp));
        float fontSize = 6.5f;
        float hpY = y2 + 15.5f;
        DrawUtil.drawText(Fonts.SFBOLD.get(), hpText, textX + 0.5f, hpY, ColorProvider.rgba(230, 230, 230, alphaInt), fontSize);
        if (absHp > 0.05f) {
            String absText = String.format(Locale.US, "%.1f AB", Float.valueOf(absHp));
            float hpW = Fonts.SFBOLD.get().getWidth(hpText, fontSize);
            float plusW = Fonts.SFBOLD.get().getWidth("  + ", fontSize);
            DrawUtil.drawText(Fonts.SFBOLD.get(), "  + ", textX + hpW, hpY, ColorProvider.rgba(160, 160, 160, alphaInt), fontSize);
            DrawUtil.drawText(Fonts.SFBOLD.get(), absText, textX + hpW + plusW, hpY, ColorProvider.rgba(255, 205, 70, alphaInt), fontSize);
        }
        DrawUtil.drawRound(barX, barY, barWidth, barHeight, 2.0f, ColorProvider.rgba(45, 45, 45, (int)(255.0f * anim)));
        if (hpWNow > 0.5f) {
            int[] colors = ColorProvider.getOrbitalRect(theme1, theme2, 300.0, alphaInt);
            float rR = hpWNow >= barWidth - 1.0f || hpWNow <= barHeight ? 2.0f : 0.0f;
            ((BuiltRectangle)Builder.rectangle().size(new SizeState(hpWNow, barHeight)).radius(new QuadRadiusState(2.0f, 2.0f, rR, rR)).color(new QuadColorState(colors[0], colors[1], colors[2], colors[3])).build()).render(context.getMatrices().peek().getPositionMatrix(), barX, barY);
        }
        this.targetHUDDrag.setWidth(width);
        this.targetHUDDrag.setHeight(height);
    }

    private void renderTargetHUD(DrawContext context) {
        if (this.hudStyle.is("Celestial")) {
            this.renderTargetHUDCelestial(context);
        } else if (this.hudStyle.is("Nursultan")) {
            this.renderTargetHUDClassic(context);
        } else if (this.hudStyle.is("Babka2")) {
            this.renderTargetHUDBabka2(context);
        } else {
            this.renderTargetHUDBabka(context);
        }
    }

    private void renderTargetHUDBabka(DrawContext context) {
        float absorption2;
        boolean showStatus;
        AbstractClientPlayerEntity p2;
        AttackAura attackAura = Instance.get(AttackAura.class);
        boolean chatOpen = this.mc.currentScreen instanceof ChatScreen;
        ClientPlayerEntity target = null;
        if (attackAura != null && attackAura.isEnabled() && attackAura.getTarget() != null && attackAura.getTarget().isAlive()) {
            target = attackAura.getTarget();
        } else if (chatOpen) {
            target = this.mc.player;
        }
        if (target != null) {
            this.lastTarget = target;
            this.animation.run(1.0f);
        } else {
            this.animation.run(0.0f);
        }
        if (this.animation.getValue() <= 0.05f || this.lastTarget == null || !(this.lastTarget instanceof LivingEntity)) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity)this.lastTarget;
        Entity entity = this.lastTarget;
        AbstractClientPlayerEntity playerEntity = entity instanceof AbstractClientPlayerEntity ? (p2 = (AbstractClientPlayerEntity)entity) : null;
        float anim = this.animation.getValue();
        int alphaInt = (int)(255.0f * anim);
        float x2 = this.targetHUDDrag.getX();
        float y2 = this.targetHUDDrag.getY();
        float width = 96.0f;
        float height = 32.0f;
        if (this.targetHudTransparentBg) {
            DrawUtil.drawRoundBlur(x2, y2, width, height, 4.0f, ColorProvider.rgba(120, 120, 120, (int)(55.0f * anim)), 10.0f);
        } else {
            DrawUtil.drawRound(x2, y2, width, height, 4.0f, ColorProvider.rgba(12, 12, 12, (int)(220.0f * anim)));
            DrawUtil.drawRound(x2, y2, width, height, 4.0f, ColorProvider.rgba(55, 55, 55, (int)(45.0f * anim)));
        }
        float headSize = 22.0f;
        float headX = x2 + 4.0f;
        float headY = y2 + 4.0f;
        float hurtPercent = (float)livingEntity.hurtTime / 10.0f;
        int headColor = ColorProvider.rgba(255, (int)(255.0f * (1.0f - hurtPercent)), (int)(255.0f * (1.0f - hurtPercent)), alphaInt);
        if (playerEntity != null) {
            try {
                int texId = this.mc.getTextureManager().getTexture(playerEntity.getSkinTextures().comp_1626()).getGlId();
                ((BuiltTexture)Builder.texture().size(new SizeState(headSize, headSize)).radius(new QuadRadiusState(3.0f)).color(new QuadColorState(headColor)).texture(0.125f, 0.125f, 0.125f, 0.125f, texId).smoothness(1.0f).build()).render(context.getMatrices().peek().getPositionMatrix(), headX, headY);
            }
            catch (Exception texId) {
                // empty catch block
            }
        }
        float textX = headX + headSize + 4.0f;
        StreamerMode streamerMode = Instance.get(StreamerMode.class);
        String name = streamerMode != null && streamerMode.isEnabled() ? streamerMode.getCustomName(livingEntity.getName().getString()) : livingEntity.getName().getString();
        float nameMaxWidth = width - (textX - x2) - 3.0f;
        DrawUtil.drawScrollingText(Fonts.SFMEDIUM.get(), name, textX, y2 + 4.2f, ColorProvider.rgba(235, 235, 235, alphaInt), 6.9f, nameMaxWidth, "th_name_" + System.identityHashCode(livingEntity));
        float currentHp = Math.max(0.0f, Server.getHealth(livingEntity, false));
        float maxHealth = Math.max(1.0f, livingEntity.getMaxHealth());
        String hpText = String.format(Locale.US, "hp %.1f", Float.valueOf(currentHp));
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), hpText, textX, y2 + 12.8f, ColorProvider.rgba(185, 185, 185, alphaInt), 6.0f);
        boolean bl = showStatus = this.mc.player != null && livingEntity != this.mc.player;
        if (showStatus) {
            float playerHp = Server.getHealth((LivingEntity)this.mc.player, false);
            String status = currentHp < playerHp ? "< win" : "< lose";
            int statusColor = currentHp < playerHp ? ColorProvider.rgba(80, 220, 80, alphaInt) : ColorProvider.rgba(220, 80, 80, alphaInt);
            float statusOffset = Fonts.SFMEDIUM.get().getWidth(hpText, 6.0f) + 4.0f;
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), status, textX + statusOffset, y2 + 12.8f, statusColor, 6.0f);
        }
        if (this.showTargetGapples && (absorption2 = livingEntity.getAbsorptionAmount()) > 0.0f) {
            String absText = String.format(Locale.US, "(+%.1f)", Float.valueOf(absorption2));
            float absOffset = Fonts.SFMEDIUM.get().getWidth(hpText, 6.0f) + 2.0f;
            if (showStatus) {
                float playerHp;
                absOffset += Fonts.SFMEDIUM.get().getWidth(currentHp < (playerHp = Server.getHealth((LivingEntity)this.mc.player, false)) ? "< win" : "< lose", 6.0f) + 4.0f;
            }
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), absText, textX + absOffset + 2.0f, y2 + 12.8f, ColorProvider.rgba(255, 215, 0, alphaInt), 6.0f);
        }
        float hpPercent = MathHelper.clamp((float)(currentHp / maxHealth), (float)0.0f, (float)1.0f);
        this.hpAnimation.run(hpPercent);
        float barX = textX - 1.0f;
        float barY = y2 + 22.0f;
        float barWidth = width - (textX - x2) - 5.0f;
        float barHeight = 4.0f;
        float hpWNow = barWidth * this.hpAnimation.getValue();
        DrawUtil.drawRound(barX, barY, barWidth, barHeight, 0.8f, ColorProvider.rgba(35, 35, 35, alphaInt));
        if (hpWNow > 0.5f) {
            int hpColor = this.hpBarUseTheme ? ColorProvider.getColorClient() : ColorProvider.rgba(80, 220, 80, 255.0f);
            DrawUtil.drawRound(barX, barY, hpWNow, barHeight, 0.8f, ColorProvider.setAlpha(hpColor, alphaInt));
        }
        if (this.showTargetGapples) {
            float absPercent = MathHelper.clamp((float)(livingEntity.getAbsorptionAmount() / maxHealth), (float)0.0f, (float)1.0f);
            this.absorptionAnimation.run(barWidth * absPercent);
            float abWNow = this.absorptionAnimation.getValue();
            if (abWNow > 0.5f) {
                DrawUtil.drawRound(barX - 0.25f, barY, abWNow, barHeight, 0.8f, ColorProvider.rgba(140, 120, 0, (int)(200.0f * anim)), ColorProvider.rgba(140, 120, 0, (int)(200.0f * anim)), ColorProvider.rgba(255, 215, 0, (int)(255.0f * anim)), ColorProvider.rgba(255, 215, 0, (int)(255.0f * anim)));
            }
        }
        if (this.showTargetArmor && playerEntity != null) {
            float armorY = barY + barHeight + 2.0f;
            float armorX = textX - 1.0f;
            ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();
            for (ItemStack stack : playerEntity.getInventory().armor) {
                armorItems.add(stack);
            }
            Collections.reverse(armorItems);
            float armorSize = 9.0f;
            for (ItemStack stack : armorItems) {
                if (!stack.isEmpty()) {
                    context.getMatrices().push();
                    context.getMatrices().translate(armorX, armorY, 150.0f);
                    context.getMatrices().scale(armorSize / 16.0f, armorSize / 16.0f, 1.0f);
                    context.drawItem(stack, 0, 0);
                    context.getMatrices().pop();
                }
                armorX += armorSize + 0.5f;
            }
            height = 42.0f;
        }
        this.targetHUDDrag.setWidth(width);
        this.targetHUDDrag.setHeight(height);
    }

    private void renderTargetHUDClassic(DrawContext context) {
        float armorAlpha;
        Object name;
        AttackAura killAura = Instance.get(AttackAura.class);
        boolean chatOpen = this.mc.currentScreen instanceof ChatScreen;
        ClientPlayerEntity target = null;
        if (killAura != null && killAura.isEnabled() && killAura.getTarget() != null && killAura.getTarget().isAlive()) {
            target = killAura.getTarget();
        } else if (chatOpen) {
            target = this.mc.player;
        }
        if (target != null) {
            this.lastTarget = target;
            this.animation.run(1.0f);
            this.armorAnim.run(1.0f);
        } else {
            this.animation.run(0.0f);
            this.armorAnim.run(0.0f);
        }
        if (this.animation.getValue() <= 0.05f || this.lastTarget == null || !(this.lastTarget instanceof LivingEntity)) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity)this.lastTarget;
        AbstractClientPlayerEntity playerEntity = this.lastTarget instanceof AbstractClientPlayerEntity ? (AbstractClientPlayerEntity)this.lastTarget : null;
        float anim = this.animation.getValue();
        int alphaInt = (int)(255.0f * anim);
        float WIDTH = this.scaled(85.0f);
        float HEIGHT = this.scaled(32.0f);
        float x2 = this.targetHUDDrag.getX();
        float y2 = this.targetHUDDrag.getY();
        float iconColor = ColorProvider.getColorClient();
        if (this.targetHudTransparentBg) {
            this.drawBlurBg(x2, y2, WIDTH, HEIGHT, this.scaled(3.0f), alphaInt);
        } else {
            this.drawHeaderBackground(x2, y2, WIDTH, HEIGHT, this.scaled(3.0f), alphaInt);
        }
        float headSize = this.scaled(26.0f);
        float headX = x2 + this.scaled(2.5f);
        float headY = y2 + (HEIGHT - headSize) / 2.0f;
        float hurtPercent = (float)livingEntity.hurtTime / 10.0f;
        int headR = 255;
        int headG = (int)(255.0f * (1.0f - hurtPercent));
        int headB = (int)(255.0f * (1.0f - hurtPercent));
        int headColor = ColorProvider.rgba(headR, headG, headB, alphaInt);
        if (playerEntity != null) {
            try {
                Identifier skin = playerEntity.getSkinTextures().comp_1626();
                int texId = this.mc.getTextureManager().getTexture(skin).getGlId();
                BuiltTexture headTexture = (BuiltTexture)Builder.texture().size(new SizeState(headSize, headSize)).radius(new QuadRadiusState(this.scaled(3.0f))).color(new QuadColorState(headColor)).texture(0.125f, 0.125f, 0.125f, 0.125f, texId).smoothness(1.0f).build();
                headTexture.render(context.getMatrices().peek().getPositionMatrix(), headX, headY);
            }
            catch (Exception skin) {}
        } else {
            DrawUtil.drawRound(headX, headY, headSize, headSize, this.scaled(3.0f), ColorProvider.rgba(headR, headG, headB, (int)((float)alphaInt * 0.85f)));
        }
        float textX = x2 + headSize + this.scaled(6.0f);
        float fontSize = this.scaled(6.8f);
        float smallFontSize = this.scaled(5.8f);
        StreamerMode streamerMode = Instance.get(StreamerMode.class);
        Object object = name = streamerMode != null && streamerMode.isEnabled() ? streamerMode.getCustomName(livingEntity.getName().getString()) : livingEntity.getName().getString();
        if (((String)name).length() > 14) {
            name = ((String)name).substring(0, 14) + "..";
        }
        Scissor.push();
        Scissor.setFromComponentCoordinates(textX, y2, WIDTH - headSize - this.scaled(10.0f), HEIGHT);
        DrawUtil.drawText(Fonts.SFBOLD.get(), (String)name, textX, y2 + this.scaled(4.0f), ColorProvider.rgba(255, 255, 255, alphaInt), fontSize);
        Scissor.unset();
        Scissor.pop();
        float currentHp = Server.getHealth(livingEntity, false);
        if (Float.isNaN(currentHp) || currentHp < 0.0f) {
            currentHp = 0.0f;
        }
        String hpText = String.format(Locale.US, "HP: %.1f", Float.valueOf(currentHp));
        DrawUtil.drawText(Fonts.SFBOLD.get(), hpText, textX, y2 + this.scaled(13.5f), ColorProvider.rgba(255, 255, 255, alphaInt), smallFontSize);
        float absorption = Math.max(0.0f, livingEntity.getAbsorptionAmount());
        if (absorption > 0.0f) {
            String absText = String.format(Locale.US, "(+%.1f)", Float.valueOf(absorption));
            float hpW = Fonts.SFBOLD.get().getWidth(hpText, smallFontSize);
            DrawUtil.drawText(Fonts.SFBOLD.get(), absText, textX + hpW + this.scaled(3.0f), y2 + this.scaled(13.5f), ColorProvider.rgba(255, 215, 0, alphaInt), smallFontSize);
        }
        float barX = textX;
        float barY = y2 + HEIGHT - this.scaled(7.0f);
        float barWidth = WIDTH - headSize - this.scaled(10.0f);
        float barHeight = this.scaled(4.5f);
        float maxHealth = Math.max(1.0f, livingEntity.getMaxHealth());
        float hpPercent = MathHelper.clamp((float)(currentHp / maxHealth), (float)0.0f, (float)1.0f);
        this.hpAnimation.run(hpPercent);
        this.outdatedHpAnimation.run(hpPercent);
        this.lastHpPercent = hpPercent;
        float hpWNow = barWidth * this.hpAnimation.getValue();
        float hpWOld = barWidth * this.outdatedHpAnimation.getValue();
        Color barBase = this.targetHudHealthColor(currentHp, maxHealth);
        int br = barBase.getRed();
        int bg = barBase.getGreen();
        int bb2 = barBase.getBlue();
        int backColor = ColorProvider.rgba((int)((float)br * 0.45f), (int)((float)bg * 0.45f), (int)((float)bb2 * 0.45f), (int)(120.0f * anim));
        DrawUtil.drawRound(barX, barY, barWidth, barHeight, this.scaled(1.0f), backColor);
        if (hpWOld > hpWNow + 0.5f) {
            int ghostColor = ColorProvider.rgba(br, bg, bb2, (int)(110.0f * anim));
            DrawUtil.drawRound(barX, barY, hpWOld, barHeight, this.scaled(1.0f), ghostColor);
        }
        if (hpWNow > 0.5f) {
            int hpLeft = ColorProvider.rgba((int)((float)br * 0.35f), (int)((float)bg * 0.35f), (int)((float)bb2 * 0.35f), alphaInt);
            int hpRight = ColorProvider.rgba(br, bg, bb2, alphaInt);
            DrawUtil.drawRound(barX, barY, hpWNow, barHeight, this.scaled(1.0f), hpLeft, hpLeft, hpRight, hpRight);
        }
        float absPercent = MathHelper.clamp((float)(absorption / maxHealth), (float)0.0f, (float)1.0f);
        this.absorptionAnimation.run(barWidth * absPercent);
        this.absorptionTrailAnimation.run(barWidth * absPercent);
        float abWNow = this.absorptionAnimation.getValue();
        float abWOld = this.absorptionTrailAnimation.getValue();
        if (abWNow > 0.5f) {
            int absLeft = ColorProvider.rgba(140, 120, 0, (int)(200.0f * anim));
            int absRight = ColorProvider.rgba(255, 215, 0, (int)(255.0f * anim));
            if (abWOld > abWNow + 0.5f) {
                DrawUtil.drawRound(barX - 0.25f, barY, abWOld, barHeight, this.scaled(1.0f), ColorProvider.rgba(200, 170, 0, (int)(190.0f * anim)), ColorProvider.rgba(200, 170, 0, (int)(190.0f * anim)), ColorProvider.rgba(255, 215, 0, (int)(190.0f * anim)), ColorProvider.rgba(255, 215, 0, (int)(190.0f * anim)));
            }
            DrawUtil.drawRound(barX - 0.25f, barY, abWNow, barHeight, this.scaled(1.0f), absLeft, absLeft, absRight, absRight);
        }
        if ((armorAlpha = this.armorAnim.getValue()) > 0.05f) {
            ArrayList<ItemStack> items = new ArrayList<ItemStack>();
            items.add(livingEntity.getMainHandStack());
            for (ItemStack stack : livingEntity.getArmorItems()) {
                items.add(stack);
            }
            items.add(livingEntity.getOffHandStack());
            Collections.reverse(items);
            float itemScale = 0.65f;
            float slotSize = 16.0f * itemScale;
            float itemX = x2 + WIDTH - slotSize * 6.0f - this.scaled(3.0f);
            float itemY = y2 - slotSize - this.scaled(1.0f);
            context.getMatrices().push();
            context.getMatrices().translate(0.0f, 0.0f, 100.0f);
            TextRenderer textRenderer = this.mc.textRenderer;
            for (ItemStack stack : items) {
                if (stack.isEmpty()) {
                    itemX += slotSize;
                    continue;
                }
                context.getMatrices().push();
                context.getMatrices().translate(itemX, itemY, 0.0f);
                context.getMatrices().scale(armorAlpha * itemScale, armorAlpha * itemScale, 1.0f);
                context.drawItem(stack, 0, 0);
                context.drawStackOverlay(textRenderer, stack, 0, 0);
                context.getMatrices().pop();
                itemX += slotSize;
            }
            context.getMatrices().pop();
        }
        this.targetHUDDrag.setWidth(WIDTH);
        this.targetHUDDrag.setHeight(HEIGHT);
    }

    private void renderTargetHUDBabka2(DrawContext context) {
        float armorAlpha;
        int hpLeft;
        int hpRight;
        AttackAura attackAura = Instance.get(AttackAura.class);
        ClientPlayerEntity target = null;
        if (attackAura.isEnabled() && attackAura.getTarget() != null && attackAura.getTarget().isAlive()) {
            target = attackAura.getTarget();
        } else if (this.mc.currentScreen instanceof ChatScreen) {
            target = this.mc.player;
        }
        if (target != null) {
            this.lastTarget = target;
            this.animation.run(1.0f);
            this.armorAnim.run(1.0f);
        } else {
            this.animation.run(0.0f);
            this.armorAnim.run(0.0f);
        }
        float animAlpha = this.animation.getValue();
        animAlpha = this.applyBabka2Alpha(animAlpha);
        if (animAlpha <= 0.05f || this.lastTarget == null || !(this.lastTarget instanceof LivingEntity)) {
            return;
        }
        int aInt = MathHelper.clamp((int)((int)(255.0f * animAlpha)), (int)0, (int)255);
        LivingEntity livingEntity = (LivingEntity)this.lastTarget;
        float x2 = this.targetHUDDrag.getX();
        float y2 = this.targetHUDDrag.getY();
        float width = 108.0f;
        float height = 38.0f;
        float panelRadius = 0.0f;
        float padL = 10.0f;
        float padR = 10.0f;
        int themeAccent = ColorProvider.getThemeColor();
        int accentR = ColorProvider.red(themeAccent);
        int accentG = ColorProvider.green(themeAccent);
        int accentB = ColorProvider.blue(themeAccent);
        int bgAlpha = MathHelper.clamp((int)((int)(235.0f * animAlpha)), (int)0, (int)255);
        if (this.targetHudTransparentBg) {
            DrawUtil.drawRoundBlur(x2, y2, width, height, panelRadius, ColorProvider.rgba(120, 120, 120, (int)(55.0f * animAlpha)), 10.0f);
        } else {
            DrawUtil.drawRound(x2, y2, width, height, panelRadius, ColorProvider.rgba(18, 14, 36, bgAlpha));
        }
        float headSize = 26.0f;
        float headX = x2 + 8.0f;
        float headY = y2 + (height - headSize) / 2.0f;
        float headRadius = headSize / 2.0f;
        context.draw();
        StencilUtil.push();
        DrawUtil.drawRound(headX, headY, headSize, headSize, headRadius, -1);
        StencilUtil.read(1);
        float currentAnimScale = this.armorAnim.getValue();
        float entityScale = headSize / 1.3f * currentAnimScale;
        if (entityScale > 0.1f) {
            float elytra;
            float entityX = headX + headSize / 2.0f;
            float entityY = headY + headSize + 15.0f * currentAnimScale;
            float f2 = elytra = livingEntity.isGliding() ? -10.0f : 0.0f;
            if (livingEntity.isGliding()) {
                entityY -= 20.0f * currentAnimScale;
            }
            this.drawEntity(entityX - elytra, entityY + elytra, entityScale, -33.0f, 0.0f, livingEntity);
        }
        context.draw();
        StencilUtil.pop();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        DiffuseLighting.disableGuiDepthLighting();
        ((BuiltBorder)Builder.border().size(new SizeState(headSize + 1.5f, headSize + 1.5f)).radius(new QuadRadiusState(headRadius)).color(new QuadColorState(ColorProvider.rgba(accentR, accentG, accentB, (int)(140.0f * animAlpha)))).thickness(1.0f).smoothness(1.0f, 0.5f).build()).render(context.getMatrices().peek().getPositionMatrix(), headX - 0.75f, headY - 0.75f);
        float textX = headX + headSize + 8.0f;
        float textY = y2 + 6.5f;
        float rightLimit = x2 + width - padR;
        StreamerMode streamerMode = Instance.get(StreamerMode.class);
        String name = streamerMode.isEnabled() ? streamerMode.getCustomName(livingEntity.getName().getString()) : livingEntity.getName().getString();
        float nameMaxW = rightLimit - textX;
        DrawUtil.drawScrollingText(Fonts.SFMEDIUM.get(), name, textX, textY, ColorProvider.rgba(230, 230, 240, aInt), 7.25f, nameMaxW, "th2_name_" + System.identityHashCode(livingEntity));
        float currentHp = Math.max(0.0f, Server.getHealth(livingEntity, false));
        float absorptionHP = Math.max(0.0f, livingEntity.getAbsorptionAmount());
        float maxHealth = Math.max(1.0f, livingEntity.getMaxHealth());
        String hpText = String.format(Locale.US, "%.1f", Float.valueOf(currentHp));
        String absorpText = String.format(Locale.US, "%.1f", Float.valueOf(absorptionHP));
        int hpCol = this.getHealthColor(currentHp, maxHealth);
        float hpY = y2 + height / 2.0f + 2.0f;
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), hpText, textX, hpY, ColorProvider.setAlpha(hpCol, aInt), 6.5f);
        float hpTextW = Fonts.SFMEDIUM.get().getWidth(hpText, 6.5f);
        if (absorptionHP > 0.0f) {
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), "+" + absorpText, textX + hpTextW + 4.0f, hpY, ColorProvider.rgba(239, 159, 39, aInt), 6.5f);
        }
        float barX = textX;
        float barY = y2 + height - 8.0f;
        float barWidth = rightLimit - textX;
        float barHeight = 3.5f;
        DrawUtil.drawRound(barX, barY, barWidth, barHeight, 1.5f, ColorProvider.rgba(40, 36, 60, aInt));
        float hpPercent = MathHelper.clamp((float)(currentHp / maxHealth), (float)0.0f, (float)1.0f);
        float absorptionPercent = MathHelper.clamp((float)(absorptionHP / maxHealth), (float)0.0f, (float)1.0f);
        this.lastHealthPercent += (hpPercent - this.lastHealthPercent) * 0.25f;
        this.lastAbsorptionPercent += (absorptionPercent - this.lastAbsorptionPercent) * 0.15f;
        this.trailHealthPercent += (this.lastHealthPercent - this.trailHealthPercent) * 0.008f;
        float hpWidth = barWidth * this.lastHealthPercent;
        float trailWidth = barWidth * this.trailHealthPercent;
        float absWidth = barWidth * this.lastAbsorptionPercent;
        if (this.elements.isEnabled("Таргет худ от темы")) {
            hpRight = ColorProvider.rgba(accentR, accentG, accentB, aInt);
            hpLeft = ColorProvider.rgba(accentR / 2, accentG / 2, accentB / 2, aInt);
        } else {
            int r2 = hpCol >> 16 & 0xFF;
            int g2 = hpCol >> 8 & 0xFF;
            int b2 = hpCol & 0xFF;
            hpLeft = ColorProvider.rgba(r2 / 2, g2 / 2, b2 / 2, aInt);
            hpRight = ColorProvider.rgba(r2, g2, b2, aInt);
        }
        if (trailWidth > hpWidth) {
            DrawUtil.drawRound(barX, barY, trailWidth, barHeight, 1.5f, ColorProvider.rgba(accentR, accentG, accentB, (int)(80.0f * animAlpha)));
        }
        if (hpWidth > 0.0f) {
            DrawUtil.drawRound(barX, barY, hpWidth, barHeight, 1.5f, hpLeft, hpLeft, hpRight, hpRight);
        }
        if (absWidth > 0.0f) {
            int absBase = ColorProvider.rgba(239, 159, 39, aInt);
            int absLeft = ColorProvider.rgba(120, 80, 20, aInt);
            DrawUtil.drawRound(barX, barY, absWidth, barHeight, 1.5f, absLeft, absLeft, absBase, absBase);
        }
        if ((armorAlpha = this.armorAnim.getValue()) > 0.05f) {
            ArrayList<ItemStack> items = new ArrayList<ItemStack>();
            items.add(livingEntity.getEquippedStack(EquipmentSlot.HEAD));
            items.add(livingEntity.getEquippedStack(EquipmentSlot.CHEST));
            items.add(livingEntity.getEquippedStack(EquipmentSlot.LEGS));
            items.add(livingEntity.getEquippedStack(EquipmentSlot.FEET));
            items.add(livingEntity.getMainHandStack());
            items.add(livingEntity.getOffHandStack());
            items.removeIf(ItemStack::isEmpty);
            if (!items.isEmpty()) {
                float itemScale = 0.7f;
                float slotSize = 14.0f * itemScale;
                float padding = 2.0f;
                float totalW = (float)items.size() * slotSize + (float)(items.size() - 1) * padding;
                float itemX = x2 + (width - totalW) / 2.0f;
                float itemY = y2 - slotSize - 2.0f;
                context.getMatrices().push();
                context.getMatrices().translate(0.0f, 0.0f, 100.0f);
                for (ItemStack stack : items) {
                    context.getMatrices().push();
                    context.getMatrices().translate(itemX, itemY, 0.0f);
                    context.getMatrices().scale(armorAlpha * itemScale, armorAlpha * itemScale, 1.0f);
                    context.drawItem(stack, 0, 0);
                    context.drawStackOverlay(this.mc.textRenderer, stack, 0, 0);
                    context.getMatrices().pop();
                    itemX += slotSize + padding;
                }
                context.getMatrices().pop();
            }
        }
        this.targetHUDDrag.setWidth(width);
        this.targetHUDDrag.setHeight(height);
    }

    private int getHealthColor(float health, float maxHealth) {
        float pct = health / maxHealth;
        int r2 = pct > 0.5f ? (int)((1.0f - pct) * 2.0f * 255.0f) : 255;
        int g2 = pct > 0.5f ? 255 : (int)(pct * 2.0f * 255.0f);
        return r2 << 16 | g2 << 8 | 0x55;
    }

    private void renderArmorHud(DrawContext context) {
        this.renderArmorHudBabka2(context);
    }

    private void renderArmorHudBabka2(DrawContext context) {
        if (this.mc.player == null) {
            return;
        }
        float x2 = this.armorHudDrag.getX();
        float y2 = this.armorHudDrag.getY();
        float itemScale = 1.0f;
        float slotSize = 16.0f * itemScale + 3.0f;
        ArrayList<ItemStack> armor = new ArrayList<ItemStack>();
        for (ItemStack stack : this.mc.player.getInventory().armor) {
            armor.add(stack);
        }
        Collections.reverse(armor);
        float itemX = x2;
        float itemY = y2;
        float totalW = 0.0f;
        context.getMatrices().push();
        for (ItemStack stack : armor) {
            if (!stack.isEmpty()) {
                context.getMatrices().push();
                context.getMatrices().translate(itemX, itemY, 0.0f);
                context.getMatrices().scale(itemScale, itemScale, 1.0f);
                context.drawItem(stack, 0, 0);
                context.getMatrices().pop();
                if (stack.getMaxDamage() > 0) {
                    float dur = (float)(stack.getMaxDamage() - stack.getDamage()) / (float)stack.getMaxDamage();
                    float barW = 14.0f * itemScale;
                    float barH = 2.0f;
                    float barX = itemX;
                    float barY = itemY + 14.0f * itemScale;
                    DrawUtil.drawRound(barX, barY, barW, barH, 0.5f, ColorProvider.rgba(0, 0, 0, 80.0f));
                    if (dur < 1.0f) {
                        DrawUtil.drawRound(barX, barY, barW * dur, barH, 0.5f, this.getDurColor(stack));
                    }
                }
            }
            totalW += slotSize;
            itemX += slotSize;
        }
        context.getMatrices().pop();
        this.armorHudDrag.setWidth(totalW);
        this.armorHudDrag.setHeight(16.0f * itemScale);
    }

    private int getDurColor(ItemStack stack) {
        float max = stack.getMaxDamage();
        float cur = max - (float)stack.getDamage();
        float pct = cur / max;
        if (pct > 0.6f) {
            return ColorProvider.rgba(50, 255, 50, 200.0f);
        }
        if (pct > 0.25f) {
            return ColorProvider.rgba(255, 255, 50, 200.0f);
        }
        return ColorProvider.rgba(255, 50, 50, 200.0f);
    }

    private void renderServerBinds(DrawContext context) {
        boolean cdActive;
        if (this.mc.player == null) {
            return;
        }
        Module serverHelper = this.findModule("ServerHelper");
        Module cordsDropper = this.findModule("Cords Dropper");
        boolean shActive = serverHelper != null && serverHelper.isEnabled();
        boolean bl = cdActive = cordsDropper != null && cordsDropper.isEnabled();
        if (!shActive && !cdActive) {
            this.sbBindsWidthAnimation.run(0.0f);
            this.sbBindsDrag.setWidth(0.0f);
            this.sbBindsDrag.setHeight(0.0f);
            return;
        }
        ArrayList<SbBind> binds = new ArrayList<SbBind>();
        if (shActive) {
            this.collectBinds(serverHelper, binds, true);
        }
        if (cdActive) {
            this.collectBinds(cordsDropper, binds, false);
        }
        if (binds.isEmpty()) {
            this.sbBindsWidthAnimation.run(0.0f);
            this.sbBindsDrag.setWidth(0.0f);
            this.sbBindsDrag.setHeight(0.0f);
            return;
        }
        this.drawCompactBindsPanel(context, binds);
    }

    private Module findModule(String name) {
        for (Module m2 : Babka.getInstance().getModuleStorage().getModules()) {
            if (!m2.getName().equals(name)) continue;
            return m2;
        }
        return null;
    }

    private void collectBinds(Module module, List<SbBind> binds, boolean isServerHelper) {
        for (Setting s2 : module.getSettings()) {
            BindSetting bs;
            if (isServerHelper && !s2.visible.get().booleanValue() || !(s2 instanceof BindSetting) || (bs = (BindSetting)s2).getValue() == -1 || isServerHelper && !this.sbBindsInfo.isEnabled(s2.getName())) continue;
            Item iconItem = isServerHelper ? SH_ICONS.get(s2.getName()) : Items.COMPASS;
            ItemStack icon = iconItem != null ? new ItemStack((ItemConvertible)iconItem) : null;
            binds.add(new SbBind(s2.getName(), bs.getValue(), icon));
        }
    }

    private void drawCompactBindsPanel(DrawContext context, List<SbBind> binds) {
        MatrixStack matrices = context.getMatrices();
        float x2 = this.sbBindsDrag.getX();
        float y2 = this.sbBindsDrag.getY();
        float height = 19.0f;
        float itemSize = 9.8f;
        float itemScale = 0.61f;
        float fontGap = 2.8f;
        float cellGap = 5.0f;
        float sidePadding = 6.0f;
        float fontSize = 6.5f;
        float width = sidePadding * 2.0f;
        for (int i2 = 0; i2 < binds.size(); ++i2) {
            String keyName = KeyStorage.getKey(binds.get(i2).getKey());
            if (keyName == null || keyName.isBlank()) {
                keyName = "?";
            }
            width += itemSize + fontGap + Fonts.SFMEDIUM.get().getWidth(keyName, fontSize);
            if (i2 >= binds.size() - 1) continue;
            width += cellGap;
        }
        this.sbBindsWidthAnimation.run(width);
        float animatedWidth = this.sbBindsWidthAnimation.getValue();
        DrawUtil.drawRound(x2, y2, animatedWidth, height, 3.0f, ColorProvider.rgba(13, 13, 13, 210.0f));
        DrawUtil.drawRound(x2, y2, 2.5f, height, 2.5f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), 200));
        float offsetX = x2 + sidePadding;
        float itemY = y2 + (height - itemSize) * 0.5f;
        float textY = y2 + 6.5f;
        for (int i3 = 0; i3 < binds.size(); ++i3) {
            SbBind bind = binds.get(i3);
            String keyName = KeyStorage.getKey(bind.getKey());
            if (keyName == null || keyName.isBlank()) {
                keyName = "?";
            }
            if (bind.getIcon() != null) {
                this.drawItemIcon(context, bind.getIcon(), offsetX, itemY, itemScale);
            }
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), keyName, offsetX + itemSize + fontGap, textY, ColorProvider.rgba(255, 255, 255, 240.0f), fontSize);
            float tw = Fonts.SFMEDIUM.get().getWidth(keyName, fontSize);
            offsetX += itemSize + fontGap + tw;
            if (i3 >= binds.size() - 1) continue;
            offsetX += cellGap;
        }
        this.sbBindsDrag.setWidth(animatedWidth);
        this.sbBindsDrag.setHeight(height);
    }

    private void drawItemIcon(DrawContext context, ItemStack stack, float x2, float y2, float scale) {
        MatrixStack matrices = context.getMatrices();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        matrices.push();
        matrices.translate(x2, y2, 0.0f);
        matrices.scale(scale, scale, 1.0f);
        context.drawItem(stack, 0, 0);
        matrices.pop();
    }

    private int getThemeColor() {
        return ColorProvider.getThemeColor();
    }

    private void renderAntiFlyCooldown(DrawContext context) {
        long elapsed = System.currentTimeMillis() - this.lastAntiFlyUse;
        if (elapsed > 40000L) {
            return;
        }
        float remaining = (float)(40000L - elapsed) / 1000.0f;
        float PAD = 5.0f;
        float CELL_W = 32.0f;
        float CELL_H = 38.0f;
        float FONT = 7.0f;
        float x2 = this.afCooldownDrag.getX();
        float y2 = this.afCooldownDrag.getY();
        DrawUtil.drawRound(x2, y2, 42.0f, 48.0f, 5.0f, ColorProvider.rgba(13, 13, 13, 220.0f));
        float cx = x2 + 5.0f;
        float cellY = y2 + 5.0f;
        DrawUtil.drawRound(cx, cellY, 32.0f, 38.0f, 4.0f, ColorProvider.rgba(255, 255, 255, 10.0f));
        ItemStack icon = new ItemStack((ItemConvertible)Items.FIREWORK_STAR);
        float iconX = cx + 8.0f;
        float iconY = cellY + 4.0f;
        context.getMatrices().push();
        context.getMatrices().translate(iconX, iconY, 150.0f);
        context.drawItem(icon, 0, 0);
        context.getMatrices().pop();
        String timeStr = String.format("%.1f", Float.valueOf(remaining));
        float tw = Fonts.SFMEDIUM.get().getWidth(timeStr, 7.0f);
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), timeStr, cx + (32.0f - tw) / 2.0f, cellY + 38.0f - 7.0f - 3.0f, ColorProvider.rgba(255, 200, 50, 255.0f), 7.0f);
        this.afCooldownDrag.setWidth(42.0f);
        this.afCooldownDrag.setHeight(48.0f);
    }

    public void update() {
        for (Staff staff2 : this.staffPlayers) {
            staff2.isOnServer = false;
        }
        for (PlayerListEntry playerListEntry : this.mc.getNetworkHandler().getPlayerList()) {
            int endIndex;
            String fullString;
            int nickIndex;
            Status status;
            boolean notSelf;
            String name = playerListEntry.getProfile().getName().replaceAll("[\\[\\]]", "");
            PlayerListEntry info = MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(name);
            boolean vanish = info == null;
            boolean isGM3 = info != null && info.getGameMode() == GameMode.SPECTATOR;
            boolean matchesPrefix = this.prefixMatches.matcher(playerListEntry.getDisplayName() != null ? playerListEntry.getDisplayName().getString().toLowerCase(Locale.ROOT) : "").matches();
            boolean isValidName = this.namePattern.matcher(name).matches();
            boolean bl = notSelf = !name.equals(MinecraftClient.getInstance().player.getName().getString());
            if (!(isValidName && notSelf && matchesPrefix || isValidName && notSelf && vanish) && !StaffManager.isStaff(name)) continue;
            if (StaffManager.isStaff(name)) {
                String[] names = new String[]{"auction", "exp_smith", "shop_balls", "shop_grief", "free", "shop_kits", "siege", "rwplus", "bossfight", "guide", "shop_smith", "shop_spawners", "colliseum", "battlepass", "buyer", "huckster", "buff_brewer", "killer", "shop_mage"};
                boolean contains = false;
                if (MinecraftClient.getInstance().getCurrentServerEntry() != null && MinecraftClient.getInstance().getCurrentServerEntry().address != null && (MinecraftClient.getInstance().getCurrentServerEntry().address.contains("mc.rwdonat.pw") || MinecraftClient.getInstance().getCurrentServerEntry().address.contains("mc.cakeworld.pw"))) {
                    for (String n2 : names) {
                        if (!name.contains(n2)) continue;
                        contains = true;
                        break;
                    }
                }
                if (contains) continue;
            }
            Optional<Staff> existingStaff = this.staffPlayers.stream().filter(s2 -> s2.name.equals(name)).findFirst();
            Status status2 = status = vanish || isGM3 ? Status.VANISHED : Status.NONE;
            if (existingStaff.isPresent()) {
                Staff s3 = existingStaff.get();
                s3.isOnServer = true;
                s3.status = status;
                continue;
            }
            String[] names = new String[]{"auction", "exp_smith", "shop_balls", "shop_grief", "free", "shop_kits", "siege", "rwplus", "bossfight", "guide", "shop_smith", "shop_spawners", "colliseum", "battlepass", "buyer", "huckster", "buff_brewer", "killer", "shop_mage"};
            boolean contains = false;
            if (MinecraftClient.getInstance().getCurrentServerEntry() != null && MinecraftClient.getInstance().getCurrentServerEntry().address != null && (MinecraftClient.getInstance().getCurrentServerEntry().address.contains("mc.rwdonat.pw") || MinecraftClient.getInstance().getCurrentServerEntry().address.contains("mc.cakeworld.pw"))) {
                for (String n3 : names) {
                    if (!name.contains(n3)) continue;
                    contains = true;
                }
            }
            if (contains) continue;
            Text originalPrefix = playerListEntry.getDisplayName();
            Text prefix = originalPrefix;
            if (prefix != null && (nickIndex = (fullString = (prefix = ReplaceUtil.replaceSymbols(prefix)).getString()).indexOf(name)) != -1 && (endIndex = nickIndex + name.length()) < fullString.length()) {
                MutableText newText = Text.empty();
                int currentLength = 0;
                MutableText baseCopy = prefix.copy();
                baseCopy.getSiblings().clear();
                String mainContent = baseCopy.getString();
                if (!mainContent.isEmpty() && currentLength < endIndex) {
                    int takeLength = Math.min(mainContent.length(), endIndex - currentLength);
                    newText.append((Text)Text.literal((String)mainContent.substring(0, takeLength)).setStyle(prefix.getStyle()));
                    currentLength += takeLength;
                }
                for (Text sibling : prefix.getSiblings()) {
                    if (currentLength >= endIndex) break;
                    MutableText siblingCopy = sibling.copy();
                    siblingCopy.getSiblings().clear();
                    String siblingContent = siblingCopy.getString();
                    int takeLength = Math.min(siblingContent.length(), endIndex - currentLength);
                    if (takeLength <= 0) continue;
                    newText.append((Text)Text.literal((String)siblingContent.substring(0, takeLength)).setStyle(sibling.getStyle()));
                    currentLength += takeLength;
                }
                prefix = newText;
            }
            Staff staff3 = new Staff(prefix == null ? Text.of((String)playerListEntry.getProfile().getName()) : prefix, name, vanish || isGM3, status);
            staff3.isOnServer = true;
            this.staffPlayers.add(staff3);
        }
        this.staffPlayers.removeIf(staff -> !staff.isOnServer && staff.animation.getValue() == 0.0f);
    }

    private void updatePotions() {
        Map<String, StatusEffectInstance> currentEffects = this.mc.player.getStatusEffects().stream().collect(Collectors.toMap(e2 -> Text.translatable((String)e2.getTranslationKey()).getString() + ":" + e2.getAmplifier(), e2 -> e2, (e1, e2) -> e1));
        this.potionItems.forEach(item -> {
            String key = item.name + ":" + item.amplifier;
            StatusEffectInstance effect = (StatusEffectInstance)currentEffects.get(key);
            if (effect != null) {
                item.durationTicks = effect.getDuration();
                if (!item.active) {
                    item.animation.setValue(1.0f);
                }
                item.active = true;
                currentEffects.remove(key);
            } else {
                item.active = false;
            }
        });
        currentEffects.forEach((key, effect) -> this.potionItems.add(new PotionItem(Text.translatable((String)effect.getTranslationKey()).getString(), effect.getAmplifier(), effect.getDuration(), (RegistryEntry<StatusEffect>)effect.getEffectType())));
        this.potionItems.removeIf(item -> !item.active && item.animation.getValue() == 0.0f);
    }

    public int getPing(PlayerEntity entity) {
        PlayerListEntry list = this.mc.getNetworkHandler().getPlayerListEntry(entity.getUuid());
        return list != null ? list.getLatency() : 0;
    }

    private void drawHeaderText(String text, float x2, float y2, float size, int alpha) {
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), text, x2, y2, ColorProvider.rgba(255, 255, 255, alpha), size);
    }

    private int colorLerp(int start, int end, float speed, float offset) {
        long t2 = System.currentTimeMillis();
        double ph = (double)t2 * ((double)speed / 1000.0) + (double)offset;
        float p2 = (float)(Math.sin(ph) * 0.5 + 0.5);
        int sr = start >> 16 & 0xFF;
        int sg = start >> 8 & 0xFF;
        int sb = start & 0xFF;
        int er = end >> 16 & 0xFF;
        int eg = end >> 8 & 0xFF;
        int eb = end & 0xFF;
        int r2 = (int)((float)sr * (1.0f - p2) + (float)er * p2);
        int g2 = (int)((float)sg * (1.0f - p2) + (float)eg * p2);
        int b2 = (int)((float)sb * (1.0f - p2) + (float)eb * p2);
        return 0xFF000000 | r2 << 16 | g2 << 8 | b2;
    }

    private Color targetHudHealthColor(float hp, float maxHp) {
        float ratio = MathHelper.clamp((float)(hp / maxHp), (float)0.0f, (float)1.0f);
        Color cMax = new Color(44, 246, 53);
        Color c56 = new Color(160, 228, 69);
        Color c38 = new Color(222, 191, 79);
        Color c32 = new Color(233, 150, 87);
        Color c11 = new Color(255, 125, 98);
        if (ratio >= 0.56f) {
            return this.targetHudLerpColor(cMax, c56, (1.0f - ratio) / 0.44f);
        }
        if (ratio >= 0.38f) {
            return this.targetHudLerpColor(c56, c38, (0.56f - ratio) / 0.18f);
        }
        if (ratio >= 0.32f) {
            return this.targetHudLerpColor(c38, c32, (0.38f - ratio) / 0.060000002f);
        }
        if (ratio >= 0.11f) {
            return this.targetHudLerpColor(c32, c11, (0.32f - ratio) / 0.21f);
        }
        return c11;
    }

    private Color targetHudLerpColor(Color a2, Color b2, float t2) {
        t2 = MathHelper.clamp((float)t2, (float)0.0f, (float)1.0f);
        return new Color((int)((float)a2.getRed() + t2 * (float)(b2.getRed() - a2.getRed())), (int)((float)a2.getGreen() + t2 * (float)(b2.getGreen() - a2.getGreen())), (int)((float)a2.getBlue() + t2 * (float)(b2.getBlue() - a2.getBlue())));
    }

    private Color lerpColor(Color a2, Color b2, float t2) {
        return new Color((int)((float)a2.getRed() + t2 * (float)(b2.getRed() - a2.getRed())), (int)((float)a2.getGreen() + t2 * (float)(b2.getGreen() - a2.getGreen())), (int)((float)a2.getBlue() + t2 * (float)(b2.getBlue() - a2.getBlue())));
    }

    public void drawEntity(float x2, float y2, float scale, float yawAngle, float pitchAngle, LivingEntity entity) {
        MatrixStack matrices = new MatrixStack();
        matrices.push();
        matrices.translate((double)x2, (double)y2, 50.0);
        matrices.scale(-scale, scale, scale);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0f));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yawAngle));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(pitchAngle));
        float bodyYaw = entity.bodyYaw;
        float prevBodyYaw = entity.prevBodyYaw;
        float headYaw = entity.headYaw;
        float prevHeadYaw = entity.prevHeadYaw;
        float yaw = entity.getYaw();
        float prevYaw = entity.prevYaw;
        float pitch = entity.getPitch();
        float prevPitch = entity.prevPitch;
        entity.bodyYaw = 0.0f;
        entity.prevBodyYaw = 0.0f;
        entity.headYaw = 0.0f;
        entity.prevHeadYaw = 0.0f;
        entity.setYaw(0.0f);
        entity.prevYaw = 0.0f;
        entity.setPitch(0.0f);
        entity.prevPitch = 0.0f;
        DiffuseLighting.disableGuiDepthLighting();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        float tickDelta = this.mc.getRenderTickCounter().getTickDelta(true);
        this.mc.getEntityRenderDispatcher().render((Entity)entity, 0.0, 0.0, 0.0, tickDelta, matrices, (VertexConsumerProvider)immediate, 0xF000F0);
        immediate.draw();
        DiffuseLighting.enableGuiDepthLighting();
        entity.bodyYaw = bodyYaw;
        entity.prevBodyYaw = prevBodyYaw;
        entity.headYaw = headYaw;
        entity.prevHeadYaw = prevHeadYaw;
        entity.setYaw(yaw);
        entity.prevYaw = prevYaw;
        entity.setPitch(pitch);
        entity.prevPitch = prevPitch;
        matrices.pop();
    }

    private boolean isHudElementHovered(Draggable d2, double mx, double my) {
        float w2 = d2.getWidth();
        float h2 = d2.getHeight();
        if (w2 <= 1.0f || h2 <= 1.0f) {
            return false;
        }
        return HoveringUtils.isHovered(mx, my, d2.getX(), d2.getY(), w2, h2);
    }

    private boolean isHudElementEnabled(Draggable d2) {
        return d2 != null && d2.getWidth() > 1.0f && d2.getHeight() > 1.0f;
    }

    private Draggable getHoveredHudElement(double mx, double my) {
        if (this.isHudElementEnabled(this.watermarkDrag) && this.isHudElementHovered(this.watermarkDrag, mx, my)) {
            return this.watermarkDrag;
        }
        if (this.isHudElementEnabled(this.keyBindsDrag) && this.isHudElementHovered(this.keyBindsDrag, mx, my)) {
            return this.keyBindsDrag;
        }
        if (this.isHudElementEnabled(this.staffListDrag) && this.isHudElementHovered(this.staffListDrag, mx, my)) {
            return this.staffListDrag;
        }
        if (this.isHudElementEnabled(this.potionsDrag) && this.isHudElementHovered(this.potionsDrag, mx, my)) {
            return this.potionsDrag;
        }
        if (this.isHudElementEnabled(this.targetHUDDrag) && this.isHudElementHovered(this.targetHUDDrag, mx, my)) {
            return this.targetHUDDrag;
        }
        if (this.isHudElementEnabled(this.totemCounterDrag) && this.isHudElementHovered(this.totemCounterDrag, mx, my)) {
            return this.totemCounterDrag;
        }
        if (this.isHudElementEnabled(this.armorHudDrag) && this.isHudElementHovered(this.armorHudDrag, mx, my)) {
            return this.armorHudDrag;
        }
        if (this.isHudElementEnabled(this.sbBindsDrag) && this.isHudElementHovered(this.sbBindsDrag, mx, my)) {
            return this.sbBindsDrag;
        }
        if (this.isHudElementEnabled(this.afCooldownDrag) && this.isHudElementHovered(this.afCooldownDrag, mx, my)) {
            return this.afCooldownDrag;
        }
        if (this.isHudElementEnabled(this.notificationsDrag) && this.isHudElementHovered(this.notificationsDrag, mx, my)) {
            return this.notificationsDrag;
        }
        return null;
    }

    private float getTargetHudMenuWidth() {
        return 100.0f;
    }

    private float getMenuHeightForElement(Draggable element) {
        if (element == this.watermarkDrag) {
            return this.hudStyle.is("Nursultan") ? 59.0f : 69.0f;
        }
        if (element == this.targetHUDDrag) {
            return 49.0f;
        }
        return 25.0f;
    }

    private void clampTargetHudMenuToWindow(float w2, float h2) {
        if (this.mc == null || this.mc.getWindow() == null) {
            return;
        }
        float maxX = Math.max(2.0f, (float)this.mc.getWindow().getScaledWidth() - w2 - 2.0f);
        float maxY = Math.max(2.0f, (float)this.mc.getWindow().getScaledHeight() - h2 - 2.0f);
        this.contextMenuX = MathHelper.clamp((float)this.contextMenuX, (float)2.0f, (float)maxX);
        this.contextMenuY = MathHelper.clamp((float)this.contextMenuY, (float)2.0f, (float)maxY);
    }

    public boolean handleHudContextClick(double mouseX, double mouseY, int button) {
        Draggable hovered = this.getHoveredHudElement(mouseX, mouseY);
        if (button == 1 && hovered != null) {
            if (this.contextMenuOpen && this.contextMenuElement == hovered) {
                this.contextMenuOpen = false;
                this.pendingContextMenuElement = null;
            } else if (this.contextMenuOpen && this.contextMenuElement != null && this.contextMenuElement != hovered) {
                this.pendingContextMenuElement = hovered;
                float menuWidth = this.getTargetHudMenuWidth();
                float menuHeight = this.getMenuHeightForElement(hovered);
                this.pendingContextMenuX = hovered.getX() + hovered.getWidth() + 4.0f;
                this.pendingContextMenuY = hovered.getY() + 1.5f;
                float saveX = this.contextMenuX;
                float saveY = this.contextMenuY;
                this.contextMenuX = this.pendingContextMenuX;
                this.contextMenuY = this.pendingContextMenuY;
                this.clampTargetHudMenuToWindow(menuWidth, menuHeight);
                this.pendingContextMenuX = this.contextMenuX;
                this.pendingContextMenuY = this.contextMenuY;
                this.contextMenuX = saveX;
                this.contextMenuY = saveY;
                this.contextMenuOpen = false;
            } else {
                this.contextMenuElement = hovered;
                this.pendingContextMenuElement = null;
                this.contextMenuOpen = true;
                float menuWidth = this.getTargetHudMenuWidth();
                float menuHeight = this.getMenuHeightForElement(hovered);
                this.contextMenuX = hovered.getX() + hovered.getWidth() + 4.0f;
                this.contextMenuY = hovered.getY() + 1.5f;
                this.clampTargetHudMenuToWindow(menuWidth, menuHeight);
            }
            return true;
        }
        if (!this.contextMenuOpen || this.contextMenuElement == null) {
            return false;
        }
        float menuWidth = this.getTargetHudMenuWidth();
        float menuHeight = this.getMenuHeightForElement(this.contextMenuElement);
        this.clampTargetHudMenuToWindow(menuWidth, menuHeight);
        if (this.contextMenuElement == this.watermarkDrag) {
            float baseY = this.contextMenuY + 4.5f;
            float toggleX = this.contextMenuX + menuWidth - 21.0f;
            if (button == 0 && HoveringUtils.isHovered(mouseX, mouseY, toggleX, baseY, 16.0, 9.0)) {
                this.showFps = !this.showFps;
                return true;
            }
            if (button == 0 && HoveringUtils.isHovered(mouseX, mouseY, toggleX, baseY + 10.0f, 16.0, 9.0)) {
                this.showTps = !this.showTps;
                return true;
            }
            if (button == 0 && HoveringUtils.isHovered(mouseX, mouseY, toggleX, baseY + 20.0f, 16.0, 9.0)) {
                this.showServer = !this.showServer;
                return true;
            }
            if (!this.hudStyle.is("Nursultan") && button == 0 && HoveringUtils.isHovered(mouseX, mouseY, toggleX, baseY + 30.0f, 16.0, 9.0)) {
                this.showCoords = !this.showCoords;
                return true;
            }
            if (button == 0 && HoveringUtils.isHovered(mouseX, mouseY, toggleX, baseY + 40.0f, 16.0, 9.0)) {
                this.showBps = !this.showBps;
                return true;
            }
            if (button == 0 && HoveringUtils.isHovered(mouseX, mouseY, toggleX, baseY + 50.0f, 16.0, 9.0)) {
                this.watermarkTransparentBg = !this.watermarkTransparentBg;
                return true;
            }
            return true;
        }
        if (this.contextMenuElement == this.targetHUDDrag) {
            float baseY = this.contextMenuY + 4.5f;
            float toggleX = this.contextMenuX + menuWidth - 21.0f;
            if (button == 0 && HoveringUtils.isHovered(mouseX, mouseY, toggleX, baseY, 16.0, 9.0)) {
                this.showTargetArmor = !this.showTargetArmor;
                return true;
            }
            if (button == 0 && HoveringUtils.isHovered(mouseX, mouseY, toggleX, baseY + 10.0f, 16.0, 9.0)) {
                this.showTargetGapples = !this.showTargetGapples;
                return true;
            }
            if (button == 0 && HoveringUtils.isHovered(mouseX, mouseY, toggleX, baseY + 20.0f, 16.0, 9.0)) {
                this.hpBarUseTheme = !this.hpBarUseTheme;
                return true;
            }
            if (button == 0 && HoveringUtils.isHovered(mouseX, mouseY, toggleX, baseY + 30.0f, 16.0, 9.0)) {
                this.targetHudTransparentBg = !this.targetHudTransparentBg;
                return true;
            }
            return true;
        }
        if (button == 0 && this.contextMenuElement == this.keyBindsDrag) {
            this.keyBindsTransparentBg = !this.keyBindsTransparentBg;
            return true;
        }
        if (button == 0 && this.contextMenuElement == this.staffListDrag) {
            this.staffListTransparentBg = !this.staffListTransparentBg;
            return true;
        }
        if (button == 0 && this.contextMenuElement == this.potionsDrag) {
            this.potionsTransparentBg = !this.potionsTransparentBg;
            return true;
        }
        if (button == 0 && this.contextMenuElement == this.totemCounterDrag) {
            this.totemCounterTransparentBg = !this.totemCounterTransparentBg;
            return true;
        }
        if (button == 0 && this.contextMenuElement == this.armorHudDrag) {
            this.armorHudTransparentBg = !this.armorHudTransparentBg;
            return true;
        }
        if (button == 0 && this.contextMenuElement == this.sbBindsDrag) {
            this.sbBindsTransparentBg = !this.sbBindsTransparentBg;
            return true;
        }
        if (button == 0 && this.contextMenuElement == this.afCooldownDrag) {
            this.afCooldownTransparentBg = !this.afCooldownTransparentBg;
            return true;
        }
        if (button == 0 && this.contextMenuElement == this.notificationsDrag) {
            this.notificationsTransparentBg = !this.notificationsTransparentBg;
            return true;
        }
        boolean menuHovered = HoveringUtils.isHovered(mouseX, mouseY, this.contextMenuX, this.contextMenuY, menuWidth, menuHeight);
        if (button == 0 && !menuHovered && hovered == this.contextMenuElement) {
            this.contextMenuOpen = false;
            this.pendingContextMenuElement = null;
            return false;
        }
        if ((button == 0 || button == 1) && menuHovered) {
            return true;
        }
        if (hovered != this.contextMenuElement) {
            this.contextMenuOpen = false;
            this.pendingContextMenuElement = null;
        }
        return false;
    }

    public boolean isContextMenuActive() {
        return this.contextMenuOpen;
    }

    public void renderHudContextMenu(DrawContext context, int mouseX, int mouseY) {
        if (this.contextMenuElement != null && !this.isHudElementEnabled(this.contextMenuElement)) {
            this.contextMenuOpen = false;
            this.contextMenuElement = null;
            this.pendingContextMenuElement = null;
        }
        this.contextMenuAnimation.animateTo(this.contextMenuOpen ? 1.0f : 0.0f);
        this.contextMenuAnimation.run();
        float menuProgress = MathHelper.clamp((float)this.contextMenuAnimation.getValue(), (float)0.0f, (float)1.0f);
        if (!this.contextMenuOpen && menuProgress <= 0.01f) {
            if (this.pendingContextMenuElement != null) {
                this.contextMenuElement = this.pendingContextMenuElement;
                this.pendingContextMenuElement = null;
                this.contextMenuX = this.pendingContextMenuX;
                this.contextMenuY = this.pendingContextMenuY;
                this.contextMenuOpen = true;
            } else {
                this.contextMenuElement = null;
            }
        }
        if (!this.contextMenuOpen && menuProgress <= 0.01f || this.contextMenuElement == null) {
            return;
        }
        float menuWidth = this.getTargetHudMenuWidth();
        float menuHeight = this.getMenuHeightForElement(this.contextMenuElement);
        this.clampTargetHudMenuToWindow(menuWidth, menuHeight);
        float x2 = this.contextMenuX;
        float y2 = this.contextMenuY;
        int themeColor = this.getThemeColor();
        float contentProgress = MathHelper.clamp((float)((menuProgress - 0.06f) / 0.94f), (float)0.0f, (float)1.0f);
        int textAlpha = (int)(contentProgress * 255.0f);
        int safeAlpha = MathHelper.clamp((int)textAlpha, (int)0, (int)255);
        int borderColor = ColorUtils.applyAlpha(ColorUtils.darken(themeColor, 0.15f), menuProgress);
        int gradientColor = ColorUtils.applyAlpha(ColorUtils.darken(themeColor, 0.05f), menuProgress);
        DrawUtil.drawRound(x2 - 0.5f, y2 - 0.5f, menuWidth + 1.0f, menuHeight + 1.0f, 3.0f, borderColor);
        DrawUtil.drawRound(x2, y2, menuWidth, menuHeight, 3.0f, ColorUtils.applyAlpha(ColorProvider.rgba(50, 50, 50, 255.0f), menuProgress), gradientColor);
        if (contentProgress <= 0.02f) {
            return;
        }
        float toggleX = x2 + menuWidth - 21.0f;
        float baseY = y2 + 3.5f;
        float labelX = x2 + 5.0f;
        if (this.contextMenuElement == this.watermarkDrag) {
            this.drawHudToggle("FPS", labelX, toggleX, baseY, this.showFps, this.fpsToggleAnimation, themeColor, contentProgress, safeAlpha);
            this.drawHudToggle("TPS", labelX, toggleX, baseY + 10.0f, this.showTps, this.tpsToggleAnimation, themeColor, contentProgress, safeAlpha);
            this.drawHudToggle("Сервер", labelX, toggleX, baseY + 20.0f, this.showServer, this.serverToggleAnimation, themeColor, contentProgress, safeAlpha);
            if (!this.hudStyle.is("Nursultan")) {
                this.drawHudToggle("Координаты", labelX, toggleX, baseY + 30.0f, this.showCoords, this.coordsToggleAnimation, themeColor, contentProgress, safeAlpha);
            }
            this.drawHudToggle("BPS", labelX, toggleX, baseY + 40.0f, this.showBps, this.bpsToggleAnimation, themeColor, contentProgress, safeAlpha);
            this.drawHudToggle("Прозрачный фон", labelX, toggleX, baseY + 50.0f, this.watermarkTransparentBg, this.wmTranspAnim, themeColor, contentProgress, safeAlpha);
            return;
        }
        if (this.contextMenuElement == this.targetHUDDrag) {
            this.drawHudToggle("Броня", labelX, toggleX, baseY, this.showTargetArmor, this.targetArmorAnim, themeColor, contentProgress, safeAlpha);
            this.drawHudToggle("Золотые Сердца", labelX, toggleX, baseY + 10.0f, this.showTargetGapples, this.targetGapplesAnim, themeColor, contentProgress, safeAlpha);
            this.drawHudToggle("Цвет ХП от темы", labelX, toggleX, baseY + 20.0f, this.hpBarUseTheme, this.hpBarThemeAnim, themeColor, contentProgress, safeAlpha);
            this.drawHudToggle("Прозрачный фон", labelX, toggleX, baseY + 30.0f, this.targetHudTransparentBg, this.thTranspAnim, themeColor, contentProgress, safeAlpha);
            return;
        }
        boolean isTransparent = false;
        Animation bgAnim = null;
        if (this.contextMenuElement == this.keyBindsDrag) {
            isTransparent = this.keyBindsTransparentBg;
            bgAnim = this.kbTranspAnim;
        } else if (this.contextMenuElement == this.staffListDrag) {
            isTransparent = this.staffListTransparentBg;
            bgAnim = this.slTranspAnim;
        } else if (this.contextMenuElement == this.potionsDrag) {
            isTransparent = this.potionsTransparentBg;
            bgAnim = this.potTranspAnim;
        } else if (this.contextMenuElement == this.totemCounterDrag) {
            isTransparent = this.totemCounterTransparentBg;
            bgAnim = this.tcTranspAnim;
        } else if (this.contextMenuElement == this.armorHudDrag) {
            isTransparent = this.armorHudTransparentBg;
            bgAnim = this.ahTranspAnim;
        } else if (this.contextMenuElement == this.sbBindsDrag) {
            isTransparent = this.sbBindsTransparentBg;
            bgAnim = this.sbTranspAnim;
        } else if (this.contextMenuElement == this.afCooldownDrag) {
            isTransparent = this.afCooldownTransparentBg;
            bgAnim = this.afTranspAnim;
        } else if (this.contextMenuElement == this.notificationsDrag) {
            isTransparent = this.notificationsTransparentBg;
            bgAnim = this.notifTranspAnim;
        }
        if (bgAnim != null) {
            this.drawHudToggle("Прозрачный фон", labelX, toggleX, baseY, isTransparent, bgAnim, themeColor, contentProgress, safeAlpha);
        }
    }

    private void drawHudToggle(String label, float labelX, float toggleX, float toggleY, boolean enabled, Animation anim, int themeColor, float contentProgress, int textAlpha) {
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), label, labelX, toggleY, ColorUtils.applyAlpha(ColorProvider.rgba(255, 255, 255, 255.0f), textAlpha), 6.75f);
        anim.animateTo(enabled ? 1.0f : 0.0f);
        anim.run();
        float progress = anim.getValue();
        int offColor = ColorUtils.darken(themeColor, 0.05f);
        int toggleColor = ColorUtils.gradient(offColor, themeColor, progress);
        int togglColor2 = ColorUtils.applyAlpha(ColorUtils.darken(toggleColor, 0.65f), contentProgress);
        DrawUtil.drawRound(toggleX, toggleY, 16.0f, 9.0f, 3.0f, ColorUtils.applyAlpha(toggleColor, contentProgress), togglColor2);
        float circleR = 2.8f;
        float circleD = circleR * 2.0f;
        float pad = 1.8f;
        float travel = 16.0f - circleD - pad * 2.0f;
        float cx = toggleX + pad + progress * travel;
        float cy = toggleY + (9.0f - circleD) / 2.0f;
        DrawUtil.drawRound(cx, cy, circleD, circleD, circleR, ColorUtils.applyAlpha(ColorProvider.rgba(255, 255, 255, 255.0f), textAlpha));
    }

    private record BindEntry3(String label, String bind, double animValue, ModuleCategory category) {
    }

    public static class Staff {
        Text prefix;
        public String name;
        boolean isSpec;
        Status status;
        boolean isOnServer;
        Animation animation;
        long mills;

        public Staff(Text prefix, String name, boolean isSpec, Status status) {
            this.prefix = prefix;
            this.name = name;
            this.isSpec = isSpec;
            this.status = status;
            this.animation = new Animation(Easing.EXPO_OUT, 233L);
            this.mills = System.currentTimeMillis();
        }
    }

    public static enum Status {
        NONE("", -1),
        VANISHED("SPEC", ColorProvider.rgba(229, 0, 63, 255.0f));

        public final String string;
        public final int color;

        private Status(String string2, int color) {
            this.string = string2;
            this.color = color;
        }
    }

    private static class PotionItem {
        String name;
        int amplifier;
        int durationTicks;
        boolean active;
        RegistryEntry<StatusEffect> effect;
        Animation animation = new Animation(Easing.EXPO_OUT, 233L);

        PotionItem(String name, int amplifier, int durationTicks, RegistryEntry<StatusEffect> effect) {
            this.name = name;
            this.amplifier = amplifier;
            this.durationTicks = durationTicks;
            this.effect = effect;
            this.active = true;
        }
    }

    private static class SbBind {
        String name;
        int key;
        ItemStack icon;

        SbBind(String name, int key, ItemStack icon) {
            this.name = name;
            this.key = key;
            this.icon = icon;
        }

        public int getKey() {
            return this.key;
        }

        public ItemStack getIcon() {
            return this.icon;
        }
    }

    private static class HeadParticle {
        float x;
        float y;
        float vx;
        float vy;
        float size;
        long spawnTime;
        int color;

        HeadParticle(float startX, float startY, int color) {
            this.x = startX;
            this.y = startY;
            double angle = Math.random() * Math.PI * 2.0;
            double speed = Math.random() * 0.4 + 0.1;
            this.vx = (float)(Math.cos(angle) * speed);
            this.vy = (float)(Math.sin(angle) * speed);
            this.size = (float)(Math.random() * 8.0 + 2.0);
            this.spawnTime = System.currentTimeMillis();
            this.color = color;
        }

        void update() {
            this.x += this.vx;
            this.y += this.vy;
        }

        float getAlpha() {
            long elapsed = System.currentTimeMillis() - this.spawnTime;
            if (elapsed >= 2000L) {
                return 0.0f;
            }
            return 1.0f - (float)elapsed / 2000.0f;
        }
    }
}

