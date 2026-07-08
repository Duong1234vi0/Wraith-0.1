/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1068
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1429
 *  net.minecraft.class_1541
 *  net.minecraft.class_1542
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_238
 *  net.minecraft.class_2561
 *  net.minecraft.class_2583
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 *  net.minecraft.class_5250
 *  net.minecraft.class_5348
 *  net.minecraft.class_640
 *  net.minecraft.class_7417
 *  net.minecraft.class_742
 *  net.minecraft.class_8828$class_2585
 *  org.joml.Vector4d
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.display.screens.clickgui.MenuScreen;
import code.alphadlc.emotions.ClientBadgeRenderer;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.events.render.WorldLoadEvent;
import code.alphadlc.features.impl.combat.AntiBot;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.render.shape.implement.Rectangle;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.math.projection.Projection;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.class_1068;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1541;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_238;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_5250;
import net.minecraft.class_5348;
import net.minecraft.class_640;
import net.minecraft.class_7417;
import net.minecraft.class_742;
import net.minecraft.class_8828;
import org.joml.Vector4d;

public class NameTags
extends Module {
    private static final float TAG_PADDING_X = 2.0f;
    private static final float TAG_PADDING_TOP = 1.0f;
    private static final float TAG_PADDING_BOTTOM = 0.5f;
    private static final float TAG_BG_ROUND = 1.0f;
    private static final int TAG_BG_COLOR = new Color(27, 27, 30, 150).getRGB();
    private static final float ARMOR_SLOT_SIZE = 14.0f;
    private static final float ARMOR_SLOT_GAP = 1.0f;
    private static final float ARMOR_ITEM_SCALE = 0.65f;
    private static final float HAND_TAG_GAP = 2.0f;
    private static final int KAWASE_BLUR_STEPS = 3;
    private static final float KAWASE_BLUR_OFFSET = 1.0f;
    private static boolean blurCapturedThisFrame;
    private final List<class_1657> players = new ArrayList<class_1657>();
    private final Map<UUID, class_5250> playerTextCache = new HashMap<UUID, class_5250>();
    private final Map<UUID, class_2960> playerSkinCache = new HashMap<UUID, class_2960>();
    private final ArrayList<class_1799> armorScratch = new ArrayList(6);
    private final ArrayList<PlayerTagLayout> playerLayouts = new ArrayList(64);
    private final ArrayList<GenericTagLayout> genericLayouts = new ArrayList(128);
    private final Vector4d vecScratch = new Vector4d();
    private class_238 entitySearchBox = new class_238(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    public static MultiSelectSetting entityType;
    static MultiSelectSetting playerSetting;
    static SelectSetting fontMode;
    static BooleanSetting blurBackground;
    private static final Map<String, PrefixInfo> DONAT_PREFIXES;

    public static NameTags getInstance() {
        return Instance.get(NameTags.class);
    }

    public NameTags() {
        super("NameTags", "NameTags", ModuleCategory.ESP);
        this.setup(entityType, playerSetting, fontMode, blurBackground);
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        this.players.clear();
        this.playerTextCache.clear();
        this.playerSkinCache.clear();
    }

    @EventHandler
    public void onTick(TickEvent e) {
        this.players.clear();
        this.playerTextCache.clear();
        this.playerSkinCache.clear();
        if (NameTags.mc.field_1687 == null) {
            return;
        }
        for (class_1657 player : NameTags.mc.field_1687.method_18456()) {
            if (player == NameTags.mc.field_1724 || player.method_5797() != null && player.method_5797().getString().startsWith("Ghost_")) continue;
            this.players.add(player);
            boolean friend = FriendUtils.isFriend((class_1297)player);
            this.playerTextCache.put(player.method_5667(), this.buildPlayerText(player, friend));
            this.cachePlayerSkin(player);
        }
    }

    private void cachePlayerSkin(class_1657 player) {
        class_640 entry;
        class_2960 skin = null;
        if (player instanceof class_742) {
            class_742 clientPlayer = (class_742)player;
            skin = clientPlayer.method_52814().comp_1626();
        }
        if (skin == null && mc.method_1562() != null && (entry = mc.method_1562().method_2871(player.method_5667())) != null && entry.method_52810() != null) {
            skin = entry.method_52810().comp_1626();
        }
        if (skin == null) {
            skin = class_1068.method_4648((UUID)player.method_5667()).comp_1626();
        }
        this.playerSkinCache.put(player.method_5667(), skin);
    }

    @EventHandler
    public void onDraw(DrawEvent e) {
        if (e == null || e.getDrawContext() == null || NameTags.mc.field_1687 == null || NameTags.mc.field_1724 == null) {
            return;
        }
        if (NameTags.mc.field_1755 instanceof MenuScreen) {
            return;
        }
        class_332 context = e.getDrawContext();
        class_4587 matrix = context.method_51448();
        boolean useCustomFont = fontMode.isSelected("\u041d\u043e\u0432\u044b\u0439");
        FontRenderer font = useCustomFont ? Fonts.getSize(13, Fonts.Type.SuisseIntlSemiBold) : null;
        FontRenderer bigFont = useCustomFont ? Fonts.getSize(15, Fonts.Type.SuisseIntlMedium) : null;
        boolean showPlayers = entityType.isSelected("\u0418\u0433\u0440\u043e\u043a\u043e\u0432");
        boolean showFriends = entityType.isSelected("\u0414\u0440\u0443\u0437\u044c\u044f");
        boolean showMobs = entityType.isSelected("\u041c\u043e\u0431\u043e\u0432");
        boolean showAnimals = entityType.isSelected("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445");
        boolean showItems = entityType.isSelected("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b");
        boolean showTnt = entityType.isSelected("\u0414\u0438\u043d\u0430\u043c\u0438\u0442");
        boolean showArmor = playerSetting.isSelected("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b");
        boolean showHands = playerSetting.isSelected("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0432 \u0440\u0443\u043a\u0430\u0445");
        boolean aresMine = Network.isAresMine();
        boolean useBlur = blurBackground.isValue();
        if (!(showPlayers || showFriends || showMobs || showAnimals || showItems || showTnt)) {
            return;
        }
        blurCapturedThisFrame = false;
        RenderSystem.disableDepthTest();
        this.playerLayouts.clear();
        this.genericLayouts.clear();
        double renderDistance = Math.sqrt(Projection.getMaxRenderDistanceSq());
        this.entitySearchBox = NameTags.mc.field_1724.method_5829().method_1014(renderDistance);
        if (showPlayers || showFriends) {
            this.collectPlayerLayouts(showPlayers, showFriends, showArmor, showHands, useCustomFont, font, aresMine);
        }
        if (showMobs || showAnimals || showItems || showTnt) {
            this.collectWorldLayouts(showMobs, showAnimals, showItems, showTnt, showArmor, showHands, useCustomFont, font, bigFont);
        }
        if (!useBlur) {
            Rectangle.beginBatch();
        }
        for (PlayerTagLayout playerTagLayout : this.playerLayouts) {
            this.queuePlayerBackgrounds(matrix, playerTagLayout, showArmor, showHands, useCustomFont, font, aresMine);
        }
        for (GenericTagLayout genericTagLayout : this.genericLayouts) {
            this.queueGenericBackgrounds(matrix, genericTagLayout, showArmor, showHands, useCustomFont, font);
        }
        if (!useBlur) {
            Rectangle.endBatch();
        }
        RenderSystem.enableBlend();
        for (PlayerTagLayout playerTagLayout : this.playerLayouts) {
            this.drawPlayerContent(context, matrix, playerTagLayout, useCustomFont, font, aresMine);
        }
        for (GenericTagLayout genericTagLayout : this.genericLayouts) {
            this.drawGenericContent(context, matrix, genericTagLayout, useCustomFont, font, bigFont, showArmor, showHands);
        }
        RenderSystem.enableDepthTest();
    }

    private void collectPlayerLayouts(boolean showPlayers, boolean showFriends, boolean showArmor, boolean showHands, boolean useCustomFont, FontRenderer font, boolean aresMine) {
        for (class_1657 player : this.players) {
            if (player == null || player.method_5797() != null && player.method_5797().getString().startsWith("Ghost_")) continue;
            boolean friend = FriendUtils.isFriend((class_1297)player);
            if (!showPlayers && (!showFriends || !friend) || !Projection.isWithinRenderDistance((class_1297)player) || !Projection.canSee(player.method_5829()) || !Projection.fillVector4D((class_1297)player, this.vecScratch)) continue;
            class_5250 text = this.playerTextCache.get(player.method_5667());
            if (text == null) {
                text = this.buildPlayerText(player, friend);
            }
            PlayerTagLayout layout = new PlayerTagLayout();
            layout.player = player;
            layout.friend = friend;
            layout.text = text;
            layout.centerX = Projection.centerX(this.vecScratch);
            layout.topY = this.vecScratch.y - 2.0;
            layout.vecY = this.vecScratch.y;
            layout.vecW = this.vecScratch.w;
            layout.skin = this.playerSkinCache.get(player.method_5667());
            layout.badgeWidth = this.getClientBadgeExtraWidth(player, aresMine ? 8.0f : 6.5f, 2.0f);
            layout.aresMine = aresMine;
            this.computePlayerTagMetrics(layout, useCustomFont, font, aresMine);
            if (showHands && useCustomFont && font != null) {
                this.computeHandLayouts(layout, font);
            }
            this.playerLayouts.add(layout);
        }
    }

    private void collectWorldLayouts(boolean showMobs, boolean showAnimals, boolean showItems, boolean showTnt, boolean showArmor, boolean showHands, boolean useCustomFont, FontRenderer font, FontRenderer bigFont) {
        if (showMobs) {
            for (class_1308 mob : NameTags.mc.field_1687.method_8390(class_1308.class, this.entitySearchBox, entity -> !(entity instanceof class_1429))) {
                this.collectLivingLayout((class_1309)mob, showArmor, showHands, useCustomFont, font);
            }
        }
        if (showAnimals) {
            for (Object animal : NameTags.mc.field_1687.method_8390(class_1429.class, this.entitySearchBox, entity -> true)) {
                this.collectLivingLayout((class_1309)animal, showArmor, showHands, useCustomFont, font);
            }
        }
        if (showItems) {
            List items = NameTags.mc.field_1687.method_8390(class_1542.class, this.entitySearchBox, entity -> true);
            items.sort(Comparator.comparing(ent -> {
                class_1799 stack = ent.method_6983();
                return stack.method_7964().method_10851().toString().equals("empty");
            }));
            for (class_1542 item : items) {
                if (!Projection.isWithinRenderDistance((class_1297)item) || !Projection.canSee(item.method_5829()) || !Projection.fillVector4D((class_1297)item, this.vecScratch)) continue;
                class_1799 stack = item.method_6983();
                class_2561 text = stack.method_7964();
                if (stack.method_7947() > 1) {
                    text = text.method_27661().method_27693(String.valueOf(class_124.field_1070) + " [" + String.valueOf(class_124.field_1061) + stack.method_7947() + String.valueOf(class_124.field_1080) + "x" + String.valueOf(class_124.field_1070) + "]");
                }
                GenericTagLayout layout = new GenericTagLayout();
                layout.type = GenericTagLayout.Type.ITEM;
                layout.text = text;
                layout.centerX = Projection.centerX(this.vecScratch);
                layout.topY = this.vecScratch.y;
                layout.vecY = this.vecScratch.y;
                layout.vecW = this.vecScratch.w;
                layout.useBigFont = text.method_10851().toString().equals("empty");
                this.computeSimpleTagMetrics(layout, useCustomFont, layout.useBigFont ? bigFont : font);
                this.genericLayouts.add(layout);
            }
        }
        if (showTnt) {
            for (class_1541 tnt : NameTags.mc.field_1687.method_8390(class_1541.class, this.entitySearchBox, entity -> true)) {
                if (!Projection.isWithinRenderDistance((class_1297)tnt) || !Projection.canSee(tnt.method_5829()) || !Projection.fillVector4D((class_1297)tnt, this.vecScratch)) continue;
                GenericTagLayout layout = new GenericTagLayout();
                layout.type = GenericTagLayout.Type.TNT;
                layout.text = tnt.method_55423();
                layout.centerX = Projection.centerX(this.vecScratch);
                layout.topY = this.vecScratch.y;
                layout.vecY = this.vecScratch.y;
                layout.vecW = this.vecScratch.w;
                this.computeSimpleTagMetrics(layout, useCustomFont, font);
                this.genericLayouts.add(layout);
            }
        }
    }

    private void collectLivingLayout(class_1309 living, boolean showArmor, boolean showHands, boolean useCustomFont, FontRenderer font) {
        if (!Projection.isWithinRenderDistance((class_1297)living) || !Projection.canSee(living.method_5829())) {
            return;
        }
        if (!Projection.fillVector4D((class_1297)living, this.vecScratch)) {
            return;
        }
        GenericTagLayout layout = new GenericTagLayout();
        layout.type = GenericTagLayout.Type.LIVING;
        layout.living = living;
        layout.text = this.buildLivingText(living);
        layout.centerX = Projection.centerX(this.vecScratch);
        layout.topY = this.vecScratch.y - 2.0;
        layout.vecY = this.vecScratch.y;
        layout.vecW = this.vecScratch.w;
        this.computeSimpleTagMetrics(layout, useCustomFont, font);
        if (showHands && useCustomFont && font != null) {
            this.computeHandLayouts(layout, font);
        }
        this.genericLayouts.add(layout);
    }

    private void computePlayerTagMetrics(PlayerTagLayout layout, boolean useCustomFont, FontRenderer font, boolean aresMine) {
        if (aresMine) {
            float width = NameTags.mc.field_1772.method_27525((class_5348)layout.text);
            Objects.requireNonNull(NameTags.mc.field_1772);
            float textHeight = 9.0f;
            float headSize = 10.0f;
            float badgeSize = 8.0f;
            layout.totalWidth = width + headSize + 4.0f + layout.badgeWidth;
            layout.boxHeight = NameTags.tagBoxHeight(textHeight);
            layout.contentHeight = Math.max(headSize, textHeight);
            layout.headSize = headSize;
            layout.badgeSize = badgeSize;
            layout.textHeight = textHeight;
            layout.posX = (float)(layout.centerX - (double)(layout.totalWidth / 2.0f));
            layout.boxTop = (float)(layout.topY - (double)layout.boxHeight);
            layout.vanilla = true;
            return;
        }
        float headSize = 8.0f;
        float badgeSize = 6.5f;
        layout.headSize = headSize;
        layout.badgeSize = badgeSize;
        if (useCustomFont && font != null) {
            layout.textHeight = NameTags.visualTextHeight(font, (class_2561)layout.text);
            layout.totalWidth = font.getStringWidth((class_2561)layout.text) + headSize + 4.0f + layout.badgeWidth;
            layout.contentHeight = Math.max(headSize, layout.textHeight);
            layout.boxHeight = NameTags.tagBoxHeight(layout.contentHeight);
            layout.posX = (float)(layout.centerX - (double)(layout.totalWidth / 2.0f));
            layout.boxTop = (float)(layout.topY - (double)layout.boxHeight);
            layout.vanilla = false;
        } else {
            Objects.requireNonNull(NameTags.mc.field_1772);
            layout.textHeight = 9.0f;
            layout.totalWidth = (float)NameTags.mc.field_1772.method_27525((class_5348)layout.text) + headSize + 4.0f + layout.badgeWidth;
            layout.contentHeight = Math.max(headSize, layout.textHeight);
            layout.boxHeight = NameTags.tagBoxHeight(layout.contentHeight);
            layout.posX = (float)(layout.centerX - (double)(layout.totalWidth / 2.0f));
            layout.boxTop = (float)(layout.topY - (double)layout.boxHeight);
            layout.vanilla = true;
        }
    }

    private void computeSimpleTagMetrics(GenericTagLayout layout, boolean useCustomFont, FontRenderer font) {
        if (useCustomFont && font != null) {
            layout.textHeight = NameTags.visualTextHeight(font, layout.text);
            layout.totalWidth = font.getStringWidth(layout.text);
            layout.boxHeight = NameTags.tagBoxHeight(layout.textHeight);
            layout.posX = (float)(layout.centerX - (double)(layout.totalWidth / 2.0f));
            layout.boxTop = (float)(layout.topY - (double)layout.boxHeight);
            layout.vanilla = false;
        } else {
            Objects.requireNonNull(NameTags.mc.field_1772);
            layout.textHeight = 9.0f;
            layout.totalWidth = NameTags.mc.field_1772.method_27525((class_5348)layout.text);
            layout.boxHeight = NameTags.tagBoxHeight(layout.textHeight);
            layout.posX = (float)(layout.centerX - (double)(layout.totalWidth / 2.0f));
            layout.boxTop = (float)(layout.topY - (double)layout.boxHeight);
            layout.vanilla = true;
        }
    }

    private void computeHandLayouts(PlayerTagLayout layout, FontRenderer font) {
        layout.handTags = new ArrayList<HandTagLayout>(2);
        float posY = (float)layout.vecW + 2.0f;
        for (class_1799 stack : layout.player.method_5877()) {
            if (stack.method_7960()) continue;
            class_5250 text = class_2561.method_43473().method_10852(stack.method_7964());
            if (stack.method_7947() > 1) {
                text.method_27693(String.valueOf(class_124.field_1070) + " [" + String.valueOf(class_124.field_1061) + stack.method_7947() + String.valueOf(class_124.field_1080) + "x" + String.valueOf(class_124.field_1070) + "]");
            }
            float textHeight = NameTags.visualTextHeight(font, (class_2561)text);
            float width = font.getStringWidth((class_2561)text);
            float boxHeight = NameTags.tagBoxHeight(textHeight);
            HandTagLayout hand = new HandTagLayout();
            hand.text = text;
            hand.textHeight = textHeight;
            hand.totalWidth = width;
            hand.boxHeight = boxHeight;
            hand.centerX = layout.centerX;
            hand.posX = (float)(hand.centerX - (double)(width / 2.0f));
            hand.boxTop = posY;
            layout.handTags.add(hand);
            posY += boxHeight + 2.0f;
        }
    }

    private void computeHandLayouts(GenericTagLayout layout, FontRenderer font) {
        layout.handTags = new ArrayList<HandTagLayout>(2);
        float posY = (float)layout.vecW + 2.0f;
        for (class_1799 stack : layout.living.method_5877()) {
            if (stack.method_7960()) continue;
            class_5250 text = class_2561.method_43473().method_10852(stack.method_7964());
            if (stack.method_7947() > 1) {
                text.method_27693(String.valueOf(class_124.field_1070) + " [" + String.valueOf(class_124.field_1061) + stack.method_7947() + String.valueOf(class_124.field_1080) + "x" + String.valueOf(class_124.field_1070) + "]");
            }
            float textHeight = NameTags.visualTextHeight(font, (class_2561)text);
            float width = font.getStringWidth((class_2561)text);
            float boxHeight = NameTags.tagBoxHeight(textHeight);
            HandTagLayout hand = new HandTagLayout();
            hand.text = text;
            hand.textHeight = textHeight;
            hand.totalWidth = width;
            hand.boxHeight = boxHeight;
            hand.centerX = layout.centerX;
            hand.posX = (float)(hand.centerX - (double)(width / 2.0f));
            hand.boxTop = posY;
            layout.handTags.add(hand);
            posY += boxHeight + 2.0f;
        }
    }

    private void queuePlayerBackgrounds(class_4587 matrix, PlayerTagLayout layout, boolean showArmor, boolean showHands, boolean useCustomFont, FontRenderer font, boolean aresMine) {
        if (showArmor) {
            this.queueArmorBackground(matrix, layout.centerX, layout.vecY, (class_1309)layout.player);
        }
        if (showHands && useCustomFont && font != null && layout.handTags != null) {
            for (HandTagLayout hand : layout.handTags) {
                this.renderTagBackground(matrix, hand.posX - 2.0f, hand.boxTop, hand.totalWidth + 4.0f, hand.boxHeight);
            }
        }
        if (aresMine) {
            this.renderTagBackground(matrix, layout.posX - 2.0f, layout.boxTop, layout.totalWidth + 4.0f, layout.boxHeight);
        } else {
            this.renderTagBackground(matrix, layout.posX - 2.0f, layout.boxTop, layout.totalWidth + 4.0f, layout.boxHeight);
        }
    }

    private void queueGenericBackgrounds(class_4587 matrix, GenericTagLayout layout, boolean showArmor, boolean showHands, boolean useCustomFont, FontRenderer font) {
        if (layout.type == GenericTagLayout.Type.LIVING && showArmor) {
            this.queueArmorBackground(matrix, layout.centerX, layout.vecY, layout.living);
        }
        if (layout.type == GenericTagLayout.Type.LIVING && showHands && useCustomFont && font != null && layout.handTags != null) {
            for (HandTagLayout hand : layout.handTags) {
                this.renderTagBackground(matrix, hand.posX - 2.0f, hand.boxTop, hand.totalWidth + 4.0f, hand.boxHeight);
            }
        }
        this.renderTagBackground(matrix, layout.posX - 2.0f, layout.boxTop, layout.totalWidth + 4.0f, layout.boxHeight);
    }

    private void queueArmorBackground(class_4587 matrix, double centerX, double vecY, class_1309 entity) {
        this.armorScratch.clear();
        for (class_1799 stack : entity.method_56675()) {
            if (stack.method_7960()) continue;
            this.armorScratch.add(stack);
        }
        if (this.armorScratch.isEmpty()) {
            return;
        }
        float step = 15.0f;
        float totalWidth = (float)this.armorScratch.size() * step - 1.0f;
        float posX = (float)(centerX - (double)(totalWidth / 2.0f));
        float posY = (float)(vecY - 8.666666984558105 - 15.0);
        for (int i = 0; i < this.armorScratch.size(); ++i) {
            this.renderTagBackground(matrix, posX + (float)i * step, posY, 14.0f, 14.0f);
        }
    }

    private void drawPlayerContent(class_332 context, class_4587 matrix, PlayerTagLayout layout, boolean useCustomFont, FontRenderer font, boolean aresMine) {
        if (playerSetting.isSelected("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b")) {
            this.drawArmorItems(context, layout.centerX, layout.vecY, (class_1309)layout.player);
        }
        if (aresMine) {
            if (layout.skin != null) {
                float headY = NameTags.tagContentY(layout.boxTop, layout.contentHeight, layout.headSize);
                Render2D.drawTexture(context, layout.skin, layout.posX + 1.0f, headY, layout.headSize, layout.headSize / 2.0f, 8, 8, 64, ColorAssist.rgba(255, 255, 255, 255), ColorAssist.rgba(255, 255, 255, 255));
            }
            float badgeY = NameTags.tagContentY(layout.boxTop, layout.contentHeight, layout.badgeSize);
            this.drawClientBadge(context, layout.player, layout.posX + layout.headSize + 2.0f, badgeY, layout.badgeSize);
            float textY = NameTags.tagContentY(layout.boxTop, layout.contentHeight, layout.textHeight);
            context.method_51439(NameTags.mc.field_1772, (class_2561)layout.text, (int)(layout.posX + layout.headSize + 4.0f + layout.badgeWidth), (int)textY, ColorAssist.getColor(255), false);
            return;
        }
        if (layout.skin != null) {
            float headY = NameTags.tagContentY(layout.boxTop, layout.contentHeight, layout.headSize);
            Render2D.drawTexture(context, layout.skin, layout.posX + 1.0f, headY, layout.headSize, layout.headSize / 2.0f, 8, 8, 64, ColorAssist.rgba(255, 255, 255, 255), ColorAssist.rgba(255, 255, 255, 255));
        }
        float badgeY = NameTags.tagContentY(layout.boxTop, layout.contentHeight, layout.badgeSize);
        this.drawClientBadge(context, layout.player, layout.posX + layout.headSize + 2.0f, badgeY, layout.badgeSize);
        if (useCustomFont && font != null) {
            font.drawText(matrix, (class_2561)layout.text, layout.posX + layout.headSize + 4.0f + layout.badgeWidth, NameTags.customFontTextY(font, layout.boxTop, layout.boxHeight, (class_2561)layout.text));
            if (layout.handTags != null) {
                for (HandTagLayout hand : layout.handTags) {
                    font.drawText(matrix, (class_2561)hand.text, hand.posX, NameTags.customFontTextY(font, hand.boxTop, hand.boxHeight, (class_2561)hand.text));
                }
            }
        } else {
            float textY = NameTags.tagContentY(layout.boxTop, layout.contentHeight, layout.textHeight);
            context.method_51439(NameTags.mc.field_1772, (class_2561)layout.text, (int)(layout.posX + layout.headSize + 4.0f + layout.badgeWidth), (int)textY, 0xFFFFFF, true);
        }
    }

    private void drawGenericContent(class_332 context, class_4587 matrix, GenericTagLayout layout, boolean useCustomFont, FontRenderer font, FontRenderer bigFont, boolean showArmor, boolean showHands) {
        FontRenderer activeFont;
        if (layout.type == GenericTagLayout.Type.LIVING && showArmor) {
            this.drawArmorItems(context, layout.centerX, layout.vecY, layout.living);
        }
        if (layout.type == GenericTagLayout.Type.LIVING && showHands && useCustomFont && font != null && layout.handTags != null) {
            for (HandTagLayout hand : layout.handTags) {
                font.drawText(matrix, (class_2561)hand.text, hand.posX, NameTags.customFontTextY(font, hand.boxTop, hand.boxHeight, (class_2561)hand.text));
            }
        }
        FontRenderer fontRenderer = activeFont = layout.useBigFont && bigFont != null ? bigFont : font;
        if (useCustomFont && activeFont != null) {
            activeFont.drawText(matrix, layout.text, layout.posX, NameTags.customFontTextY(activeFont, layout.boxTop, layout.boxHeight, layout.text));
        } else {
            context.method_51439(NameTags.mc.field_1772, layout.text, (int)layout.posX, (int)NameTags.tagContentY(layout.boxTop, layout.textHeight, layout.textHeight), 0xFFFFFF, true);
        }
    }

    private void drawArmorItems(class_332 context, double centerX, double vecY, class_1309 entity) {
        this.armorScratch.clear();
        for (class_1799 stack : entity.method_56675()) {
            if (stack.method_7960()) continue;
            this.armorScratch.add(stack);
        }
        if (this.armorScratch.isEmpty()) {
            return;
        }
        float step = 15.0f;
        float totalWidth = (float)this.armorScratch.size() * step - 1.0f;
        float posX = (float)(centerX - (double)(totalWidth / 2.0f));
        float posY = (float)(vecY - 8.666666984558105 - 15.0);
        float itemVisualSize = 10.4f;
        float itemOffset = (14.0f - itemVisualSize) / 2.0f - 1.0f;
        for (int i = 0; i < this.armorScratch.size(); ++i) {
            float itemX = posX + (float)i * step + itemOffset;
            float itemY = posY + itemOffset;
            Render2D.defaultDrawStack(context, this.armorScratch.get(i), itemX, itemY, false, true, 0.65f);
        }
    }

    public void renderPreview(class_332 context, class_4587 matrix, class_1657 player, Vector4d vec) {
        if (context == null || matrix == null || player == null || vec == null) {
            return;
        }
        if (!(entityType.isSelected("\u0418\u0433\u0440\u043e\u043a\u043e\u0432") || entityType.isSelected("\u0414\u0440\u0443\u0437\u044c\u044f") && FriendUtils.isFriend((class_1297)player))) {
            return;
        }
        boolean useCustomFont = fontMode.isSelected("\u041d\u043e\u0432\u044b\u0439");
        FontRenderer font = useCustomFont ? Fonts.getSize(13, Fonts.Type.SuisseIntlSemiBold) : null;
        blurCapturedThisFrame = false;
        PlayerTagLayout layout = new PlayerTagLayout();
        layout.player = player;
        layout.friend = FriendUtils.isFriend((class_1297)player);
        layout.text = this.buildPlayerText(player, layout.friend);
        layout.centerX = Projection.centerX(vec);
        layout.topY = vec.y - 2.0;
        layout.vecY = vec.y;
        layout.vecW = vec.w;
        layout.skin = this.playerSkinCache.getOrDefault(player.method_5667(), class_1068.method_4648((UUID)player.method_5667()).comp_1626());
        layout.badgeWidth = this.getClientBadgeExtraWidth(player, 6.5f, 2.0f);
        layout.aresMine = false;
        this.computePlayerTagMetrics(layout, useCustomFont, font, false);
        if (playerSetting.isSelected("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0432 \u0440\u0443\u043a\u0430\u0445") && useCustomFont && font != null) {
            this.computeHandLayouts(layout, font);
        }
        if (!blurBackground.isValue()) {
            Rectangle.beginBatch();
        }
        this.queuePlayerBackgrounds(matrix, layout, playerSetting.isSelected("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b"), playerSetting.isSelected("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0432 \u0440\u0443\u043a\u0430\u0445"), useCustomFont, font, false);
        if (!blurBackground.isValue()) {
            Rectangle.endBatch();
        }
        this.drawPlayerContent(context, matrix, layout, useCustomFont, font, false);
    }

    @Override
    public void deactivate() {
        this.playerTextCache.clear();
        this.playerSkinCache.clear();
        super.deactivate();
    }

    private static float tagBoxHeight(float contentHeight) {
        return contentHeight + 1.0f + 0.5f;
    }

    private static float tagContentY(float boxTop, float contentHeight, float elementHeight) {
        return boxTop + 1.0f + (contentHeight - elementHeight) / 2.0f;
    }

    private static float visualTextHeight(FontRenderer font, class_2561 text) {
        return font.getStringHeight(text);
    }

    private static float customFontTextY(FontRenderer font, float boxTop, float boxHeight, class_2561 text) {
        return font.getCenteredY(boxTop, boxHeight, text.getString());
    }

    private void renderTagBackground(class_4587 matrix, float x, float y, float width, float height) {
        if (blurBackground.isValue()) {
            if (!blurCapturedThisFrame) {
                KawaseBlur.INSTANCE.render(matrix, 3, 1.0f);
                blurCapturedThisFrame = true;
            }
            Render2D.rectangleWithMask(matrix.method_23760().method_23761(), x, y, width, height, 1.0f, 0, KawaseBlur.INSTANCE.getBlurTexture());
            rectangle.render(ShapeProperties.create(matrix, x, y, width, height).round(1.0f).color(TAG_BG_COLOR).build());
            return;
        }
        rectangle.render(ShapeProperties.create(matrix, x, y, width, height).round(1.0f).outlineColor(new Color(33, 33, 33, 0).getRGB()).color(TAG_BG_COLOR).build());
    }

    private float getClientBadgeExtraWidth(class_1657 player, float badgeSize, float badgeGap) {
        return ClientBadgeRenderer.hasCompatibleClient(player) ? badgeSize + badgeGap : 0.0f;
    }

    private void drawClientBadge(class_332 context, class_1657 player, float x, float y, float size) {
        if (ClientBadgeRenderer.hasCompatibleClient(player)) {
            ClientBadgeRenderer.drawRoundedBadge(context, x, y, size);
        }
    }

    private class_5250 buildPlayerText(class_1657 player, boolean friend) {
        float health = PlayerInteractionHelper.getHealth((class_1309)player);
        class_5250 text = class_2561.method_43473();
        if (friend) {
            text.method_27693("[" + String.valueOf(class_124.field_1060) + "F" + String.valueOf(class_124.field_1070) + "] ");
        }
        if (AntiBot.isBot(player)) {
            text.method_27693("[" + String.valueOf(class_124.field_1079) + "BOT" + String.valueOf(class_124.field_1070) + "] ");
        }
        class_2561 displayName = NameTags.convertDisplayName(player.method_5476());
        text.method_10852((class_2561)(friend ? NameTags.colorizeText(displayName, ColorAssist.getFriendColor()) : displayName));
        if (health > 0.0f) {
            text.method_27693(String.valueOf(class_124.field_1070) + " [" + String.valueOf(class_124.field_1061) + PlayerInteractionHelper.getHealthString(health) + String.valueOf(class_124.field_1070) + "]");
        }
        return text;
    }

    private class_5250 buildLivingText(class_1309 entity) {
        float health = PlayerInteractionHelper.getHealth(entity);
        class_5250 text = class_2561.method_43473();
        text.method_10852(entity.method_5476());
        if (health > 0.0f) {
            text.method_27693(String.valueOf(class_124.field_1070) + " [" + String.valueOf(class_124.field_1061) + PlayerInteractionHelper.getHealthString(entity) + String.valueOf(class_124.field_1070) + "]");
        }
        return text;
    }

    private static class_5250 colorizeText(class_2561 text, int rgb) {
        class_5250 result = class_2561.method_43473();
        text.method_27658((style, content) -> {
            if (!content.isEmpty()) {
                result.method_10852((class_2561)class_2561.method_43470((String)content).method_27694(ignored -> style.method_36139(rgb)));
            }
            return Optional.empty();
        }, class_2583.field_24360);
        return result;
    }

    private static class_2561 convertDisplayName(class_2561 original) {
        class_5250 result = class_2561.method_43473();
        NameTags.processTextComponent(original, result);
        return result;
    }

    private static void processTextComponent(class_2561 component, class_5250 result) {
        String content = "";
        class_7417 class_74172 = component.method_10851();
        if (class_74172 instanceof class_8828.class_2585) {
            class_8828.class_2585 literal = (class_8828.class_2585)class_74172;
            content = literal.comp_737();
        }
        if (!content.isEmpty()) {
            boolean foundPrefix = false;
            for (Map.Entry entry : DONAT_PREFIXES.entrySet()) {
                String key = (String)entry.getKey();
                int keyIndex = content.indexOf(key);
                if (keyIndex < 0) continue;
                PrefixInfo info = (PrefixInfo)entry.getValue();
                String prefixText = String.valueOf(info.color()) + info.name();
                int afterKey = keyIndex + key.length();
                boolean needsSpace = afterKey >= content.length() || content.charAt(afterKey) != ' ';
                String before = content.substring(0, keyIndex);
                String after = afterKey < content.length() ? content.substring(afterKey) : "";
                String replaced = before + prefixText + (needsSpace ? " " : "") + after;
                result.method_10852((class_2561)class_2561.method_43470((String)replaced));
                foundPrefix = true;
                break;
            }
            if (!foundPrefix) {
                result.method_10852((class_2561)class_2561.method_43470((String)content).method_10862(component.method_10866()));
            }
        }
        for (class_2561 sibling : component.method_10855()) {
            NameTags.processTextComponent(sibling, result);
        }
    }

    static {
        entityType = new MultiSelectSetting("\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c", "\u0421\u0443\u0449\u043d\u043e\u0441\u0442\u0438, \u043a\u043e\u0442\u043e\u0440\u044b\u0435 \u0431\u0443\u0434\u0443\u0442 \u043e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c\u0441\u044f").value("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", "\u041c\u043e\u0431\u043e\u0432", "\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445", "\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", "\u0414\u0438\u043d\u0430\u043c\u0438\u0442", "\u0414\u0440\u0443\u0437\u044c\u044f").selected("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", "\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b");
        playerSetting = new MultiSelectSetting("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438", "\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438 \u0434\u043b\u044f \u0438\u0433\u0440\u043e\u043a\u043e\u0432").value("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", "\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0432 \u0440\u0443\u043a\u0430\u0445").selected("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", "\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0432 \u0440\u0443\u043a\u0430\u0445").visible(() -> entityType.isSelected("\u0418\u0433\u0440\u043e\u043a\u043e\u0432") || entityType.isSelected("\u041c\u043e\u0431\u043e\u0432") || entityType.isSelected("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445") || entityType.isSelected("\u0414\u0440\u0443\u0437\u044c\u044f"));
        fontMode = new SelectSetting("\u0428\u0440\u0438\u0444\u0442", "\u0412\u044b\u0431\u043e\u0440 \u0448\u0440\u0438\u0444\u0442\u0430 \u0434\u043b\u044f \u043d\u0435\u0439\u043c\u0442\u0435\u0433\u043e\u0432").value("\u041d\u043e\u0432\u044b\u0439", "\u0421\u0442\u0430\u043d\u0434\u0430\u0440\u0442\u043d\u044b\u0439").selected("\u041d\u043e\u0432\u044b\u0439");
        blurBackground = new BooleanSetting("\u0411\u043b\u044e\u0440", "\u0420\u0430\u0437\u043c\u044b\u0442\u044b\u0439 \u0444\u043e\u043d \u043d\u0435\u0439\u043c\u0442\u0435\u0433\u043e\u0432").setValue(false);
        DONAT_PREFIXES = new HashMap<String, PrefixInfo>();
        DONAT_PREFIXES.put("\ua500", new PrefixInfo("PLAYER", class_124.field_1080));
        DONAT_PREFIXES.put("\ua504", new PrefixInfo("HERO", class_124.field_1078));
        DONAT_PREFIXES.put("\ua508", new PrefixInfo("TITAN", class_124.field_1054));
        DONAT_PREFIXES.put("\ua512", new PrefixInfo("AVENGER", class_124.field_1060));
        DONAT_PREFIXES.put("\ua516", new PrefixInfo("OVERLORD", class_124.field_1075));
        DONAT_PREFIXES.put("\ua520", new PrefixInfo("MAGISTER", class_124.field_1065));
        DONAT_PREFIXES.put("\ua524", new PrefixInfo("IMPERATOR", class_124.field_1061));
        DONAT_PREFIXES.put("\ua528", new PrefixInfo("DRAGON", class_124.field_1076));
        DONAT_PREFIXES.put("\ua532", new PrefixInfo("BULL", class_124.field_1064));
        DONAT_PREFIXES.put("\ua552", new PrefixInfo("RABBIT", class_124.field_1068));
        DONAT_PREFIXES.put("\ua536", new PrefixInfo("TIGER", class_124.field_1065));
        DONAT_PREFIXES.put("\ua544", new PrefixInfo("DRACULA", class_124.field_1061));
        DONAT_PREFIXES.put("\ua556", new PrefixInfo("BUNNY", class_124.field_1068));
        DONAT_PREFIXES.put("\ua540", new PrefixInfo("HYDRA", class_124.field_1077));
        DONAT_PREFIXES.put("\ua548", new PrefixInfo("COBRA", class_124.field_1060));
        DONAT_PREFIXES.put("\ua545", new PrefixInfo("VAMPIRE", class_124.field_1061));
        DONAT_PREFIXES.put("\ua501", new PrefixInfo("MEDIA", class_124.field_1078));
        DONAT_PREFIXES.put("\ua505", new PrefixInfo("YT", class_124.field_1061));
        DONAT_PREFIXES.put("\ua560", new PrefixInfo("D.HELPER", class_124.field_1060));
        DONAT_PREFIXES.put("\ua509", new PrefixInfo("HELPER", class_124.field_1054));
        DONAT_PREFIXES.put("\ua513", new PrefixInfo("ML.MODER", class_124.field_1054));
        DONAT_PREFIXES.put("\ua517", new PrefixInfo("MODER", class_124.field_1054));
        DONAT_PREFIXES.put("\ua521", new PrefixInfo("MODER+", class_124.field_1054));
        DONAT_PREFIXES.put("\ua525", new PrefixInfo("ST.MODER", class_124.field_1054));
        DONAT_PREFIXES.put("\ua529", new PrefixInfo("GL.MODER", class_124.field_1065));
        DONAT_PREFIXES.put("\ua533", new PrefixInfo("ML.ADMIN", class_124.field_1061));
        DONAT_PREFIXES.put("\ua537", new PrefixInfo("ADMIN", class_124.field_1079));
    }

    private static final class PlayerTagLayout {
        class_1657 player;
        boolean friend;
        class_5250 text;
        class_2960 skin;
        double centerX;
        double topY;
        double vecY;
        double vecW;
        float posX;
        float boxTop;
        float boxHeight;
        float totalWidth;
        float contentHeight;
        float textHeight;
        float headSize;
        float badgeSize;
        float badgeWidth;
        boolean aresMine;
        boolean vanilla;
        List<HandTagLayout> handTags;

        private PlayerTagLayout() {
        }
    }

    private static final class GenericTagLayout {
        Type type;
        class_1309 living;
        class_2561 text;
        double centerX;
        double topY;
        double vecY;
        double vecW;
        float posX;
        float boxTop;
        float boxHeight;
        float totalWidth;
        float textHeight;
        boolean vanilla;
        boolean useBigFont;
        List<HandTagLayout> handTags;

        private GenericTagLayout() {
        }

        static enum Type {
            LIVING,
            ITEM,
            TNT;

        }
    }

    private static final class HandTagLayout {
        class_5250 text;
        double centerX;
        float posX;
        float boxTop;
        float boxHeight;
        float totalWidth;
        float textHeight;

        private HandTagLayout() {
        }
    }

    private record PrefixInfo(String name, class_124 color) {
    }
}
