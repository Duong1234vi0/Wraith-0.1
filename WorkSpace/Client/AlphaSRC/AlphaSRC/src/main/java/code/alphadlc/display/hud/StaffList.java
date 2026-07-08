/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10042
 *  net.minecraft.class_1068
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1934
 *  net.minecraft.class_2561
 *  net.minecraft.class_268
 *  net.minecraft.class_269
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_640
 *  net.minecraft.class_897
 *  net.minecraft.class_922
 */
package code.alphadlc.display.hud;

import code.alphadlc.common.animation.Animation;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.Decelerate;
import code.alphadlc.common.animation.implement.EaseOut;
import code.alphadlc.common.repository.staff.StaffRepository;
import code.alphadlc.display.hud.HudListPanel;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.BackgroundIcons;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.theme.ThemeManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import net.minecraft.class_10042;
import net.minecraft.class_1068;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1934;
import net.minecraft.class_2561;
import net.minecraft.class_268;
import net.minecraft.class_269;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_640;
import net.minecraft.class_897;
import net.minecraft.class_922;
import org.jetbrains.annotations.Nullable;

public class StaffList
extends AbstractDraggable {
    private static final Pattern NAME_PATTERN = Pattern.compile("^\\w{3,16}$");
    private static final Pattern PREFIX_MATCHES = Pattern.compile(".*(mod|\u043c\u043e\u0434|adm|\u0430\u0434\u043c|help|\u0445\u0435\u043b\u043f|curat|\u043a\u0443\u0440\u0430\u0442|own|\u043e\u0432\u043d|dev|supp|\u0441\u0430\u043f\u043f|yt|\u044e\u0442|\u0441\u043e\u0442\u0440\u0443\u0434).*", 2);
    private final List<StaffRow> staffRows = new ArrayList<StaffRow>();
    private final List<StaffRow> renderSnapshot = new ArrayList<StaffRow>();
    private final Map<String, StaffRow> rowByName = new HashMap<String, StaffRow>();
    private final Set<String> addedPlayers = new HashSet<String>();
    private final FontRenderer fontMedium = Fonts.getSize(13, Fonts.Type.SuisseIntlMedium);
    private final FontRenderer fontHeader = Fonts.getSize(16, Fonts.Type.DEFAULT);
    private final FontRenderer fontStatus = Fonts.getSize(13, Fonts.Type.DEFAULT);
    private final FontRenderer fontName = Fonts.getSize(14, Fonts.Type.DEFAULT);

    public StaffList() {
        super("StaffList", 115, 40, 100, 23, true);
        this.scaleAnimation = new EaseOut().setValue(1.0).setMs(100);
    }

    @Override
    public boolean visible() {
        Hud.getInstance();
        if (!Hud.interfaceSettings.isSelected("StaffList") || !Hud.getInstance().state) {
            return false;
        }
        return !this.staffRows.isEmpty() || PlayerInteractionHelper.isChat(StaffList.mc.field_1755);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            FontRenderer iconFont = Fonts.getSize(15, Fonts.Type.ALPHADLC_ICONS);
            float pX = (float)(this.getX() + this.getWidth()) - iconFont.getStringWidth("p") - 8.0f;
            float pY = (float)this.getY() + 9.5f;
            float pWidth = iconFont.getStringWidth("p");
            float pHeight = iconFont.getStringHeight("p");
            if (mouseX >= (double)pX && mouseX <= (double)(pX + pWidth) && mouseY >= (double)pY && mouseY <= (double)(pY + pHeight)) {
                Hud.getInstance();
                ArrayList<String> selected = new ArrayList<String>(Hud.interfaceSettings.getSelected());
                selected.remove("StaffList");
                Hud.getInstance();
                Hud.interfaceSettings.setSelected(selected);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean hasStaff() {
        return !this.staffRows.isEmpty();
    }

    @Override
    public void tick() {
        this.staffRows.removeIf(row -> row.anim.isFinished(Direction.BACKWARDS));
        this.updateStaffPlayers();
    }

    @Override
    public void drawDraggable(class_332 context) {
        boolean showExample;
        class_4587 matrix = context.method_51448();
        this.renderSnapshot.clear();
        this.renderSnapshot.addAll(this.staffRows);
        float posX = this.getX();
        float posY = this.getY();
        float maxElementsWidth = HudListPanel.headerMinWidth(this.fontHeader, "StaffList");
        float headerHeight = 21.0f;
        float rowHeight = 11.0f;
        boolean hasVisibleRows = false;
        for (StaffRow row : this.renderSnapshot) {
            if (row.anim.getOutput().floatValue() <= 0.0f) continue;
            hasVisibleRows = true;
            float elementsWidth = HudListPanel.rowWidth(this.fontName, row.player.name, 0.0f, this.fontStatus, row.player.status.label);
            maxElementsWidth = Math.max(maxElementsWidth, elementsWidth);
        }
        boolean bl = showExample = this.staffRows.isEmpty() && PlayerInteractionHelper.isChat(StaffList.mc.field_1755);
        if (showExample) {
            hasVisibleRows = true;
            float elementsWidth = HudListPanel.rowWidth(this.fontName, "Admin", 0.0f, this.fontStatus, "[ON]");
            maxElementsWidth = Math.max(maxElementsWidth, elementsWidth);
        }
        float totalContentHeight = 0.0f;
        for (StaffRow row : this.renderSnapshot) {
            float animValue = row.anim.getOutput().floatValue();
            if (!(animValue > 0.0f)) continue;
            totalContentHeight += rowHeight * animValue;
        }
        if (showExample) {
            totalContentHeight += rowHeight;
        }
        float totalHeight = HudListPanel.panelHeight(totalContentHeight);
        float width = maxElementsWidth;
        Hud.getInstance();
        if (Hud.blur.isValue() && !KawaseBlur.INSTANCE.fbos.isEmpty()) {
            Render2D.rectangleWithMask(matrix.method_23760().method_23761(), posX, posY, width, totalHeight, 6.0f, 0, KawaseBlur.INSTANCE.fbos.getFirst().method_30277());
        }
        rectangle.render(ShapeProperties.create(matrix, posX, posY, width, totalHeight).round(6.0f).softness(2.5f).color(new Color(0, 0, 0, 210).getRGB()).build());
        float headerTextY = HudListPanel.headerTextY(this.fontHeader, posY, "StaffList");
        this.fontHeader.drawString(matrix, "StaffList", posX + 7.0f, headerTextY, ThemeManager.textColor.getColor());
        float iconBoxW = this.fontStatus.getStringWidth("[ON]") + 6.0f;
        float iconCenterX = posX + width - 6.5f - iconBoxW / 2.0f;
        BackgroundIcons.drawHudHeaderIcon(context, BackgroundIcons.STAFFLIST, this.fontHeader, "StaffList", headerTextY, iconCenterX, ColorAssist.getHudColor());
        posY = HudListPanel.contentStartY(posY);
        if (showExample) {
            this.drawStaffRow(context, matrix, posX, posY, width, rowHeight, 1.0f, "Admin", StaffStatus.ONLINE, null, true);
            posY += rowHeight;
        } else if (hasVisibleRows) {
            for (StaffRow row : this.renderSnapshot) {
                float animValue = row.anim.getOutput().floatValue();
                if (animValue <= 0.0f) continue;
                this.drawStaffRow(context, matrix, posX, posY, width, rowHeight, animValue, row.player.name, row.player.status, row.player.uuid, false);
                posY += rowHeight * animValue;
            }
        }
        this.setWidth((int)width);
        this.setHeight((int)totalHeight);
    }

    private void drawStaffRow(class_332 context, class_4587 matrix, float posX, float posY, float width, float rowHeight, float animValue, String name, StaffStatus status, @Nullable UUID uuid, boolean example) {
        class_2960 faceTexture;
        int alpha = (int)(255.0f * animValue);
        boolean isLightTheme = ThemeManager.BackgroundGui.getColor() == Color.WHITE.getRGB() || (ThemeManager.BackgroundGui.getColor() >> 16 & 0xFF) > 200;
        Color textColor = isLightTheme ? new Color(0, 0, 0, alpha) : new Color(255, 255, 255, alpha);
        int statusColor = ColorAssist.replAlpha(StaffList.getStatusColor(status), alpha);
        matrix.method_22903();
        matrix.method_46416(posX + width / 2.0f, posY + rowHeight * animValue / 2.0f, 0.0f);
        matrix.method_22905(animValue, animValue, animValue);
        matrix.method_46416(-(posX + width / 2.0f), -(posY + rowHeight * animValue / 2.0f), 0.0f);
        float headSize = 9.0f;
        float headRound = 2.5f;
        float headX = posX + 7.0f;
        float headY = HudListPanel.rowIconY(posY, headSize);
        class_2960 class_29602 = faceTexture = status == StaffStatus.VANISHED ? null : this.resolveFaceTexture(name, uuid);
        if (faceTexture != null) {
            this.drawStaffHead(context, headX, headY, headSize, headRound, faceTexture, animValue);
        } else {
            rectangle.render(ShapeProperties.create(matrix, headX, headY, headSize, headSize).round(headRound).color(ColorAssist.rgba(70, 70, 74, alpha)).build());
        }
        rectangle.render(ShapeProperties.create(matrix, posX + 18.6f, HudListPanel.rowDotY(posY), 2.0, 2.0).round(1.0f).color(new Color(87, 87, 90, alpha).getRGB()).build());
        float rowTextY = HudListPanel.rowIconAlignedTextY(this.fontName, posY, headSize, name);
        this.fontName.drawString(matrix, name, Math.round(posX + 23.5f), Math.round(rowTextY), textColor.getRGB());
        String statusText = status.label;
        float statusWidth = this.fontStatus.getStringWidth(statusText);
        float statusBoxWidth = statusWidth + 6.0f;
        float statusBoxX = posX + width - 6.5f - statusBoxWidth;
        float statusBoxHeight = 8.0f;
        float statusBoxY = HudListPanel.rowBadgeY(posY, statusBoxHeight);
        rectangle.render(ShapeProperties.create(matrix, statusBoxX, statusBoxY, statusBoxWidth, statusBoxHeight * animValue).round(2.0f).thickness(1.0f).outlineColor(new Color(33, 33, 33, alpha).getRGB()).color(new Color(0, 0, 0, (int)(100.0f * animValue)).getRGB()).build());
        this.fontStatus.drawString(matrix, statusText, Math.round(statusBoxX + statusBoxWidth / 2.0f - statusWidth / 2.0f), Math.round(HudListPanel.rowBadgeTextY(this.fontStatus, posY, statusBoxHeight, statusText)), example ? ThemeManager.textColor.getColor() : statusColor);
        matrix.method_22909();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void drawStaffHead(class_332 context, float headX, float headY, float headSize, float headRound, class_2960 texture, float animation) {
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)class_3532.method_15363((float)animation, (float)0.0f, (float)1.0f));
        try {
            Render2D.drawTexture(context, texture, headX, headY, headSize, headRound, 8, 8, 64, ColorAssist.getRect(1.0f), -1);
        }
        finally {
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderSystem.defaultBlendFunc();
        }
    }

    @Nullable
    private class_2960 resolveFaceTexture(String playerName, @Nullable UUID uuid) {
        class_2960 texture;
        class_922 renderer;
        class_10042 state;
        class_897 baseRenderer;
        class_1657 player;
        class_310 client = class_310.method_1551();
        if (client == null || client.field_1687 == null) {
            return StaffList.resolveSkinTexture(playerName, uuid);
        }
        if (uuid != null && (player = client.field_1687.method_18470(uuid)) != null && (baseRenderer = client.method_1561().method_3953((class_1297)player)) instanceof class_922 && (state = (class_10042)(renderer = (class_922)baseRenderer).method_62425((class_1297)player, client.method_61966().method_60637(false))) != null && (texture = renderer.method_3885(state)) != null) {
            return texture;
        }
        return StaffList.resolveSkinTexture(playerName, uuid);
    }

    private void updateStaffPlayers() {
        if (PlayerInteractionHelper.nullCheck() || mc.method_1562() == null || StaffList.mc.field_1687 == null) {
            this.markAllRowsRemoved();
            return;
        }
        HashMap<String, class_640> nameToEntry = new HashMap<String, class_640>();
        for (class_640 entry2 : mc.method_1562().method_2880()) {
            if (entry2.method_2966() == null || entry2.method_2966().getName() == null) continue;
            nameToEntry.put(entry2.method_2966().getName().toLowerCase(Locale.ROOT), entry2);
        }
        HashSet<String> detectedNames = new HashSet<String>();
        this.addedPlayers.clear();
        String ourName = StaffList.mc.field_1724.method_5477().getString();
        class_269 scoreboard = StaffList.mc.field_1687.method_8428();
        for (class_268 team : scoreboard.method_1159()) {
            class_2561 prefixComponent = team.method_1144();
            String prefix = prefixComponent.getString();
            String cleanPrefixLower = StaffList.repairString(prefix).toLowerCase(Locale.ROOT);
            for (String member : team.method_1204()) {
                boolean isVanished;
                if (member == null || member.equals(ourName) || this.addedPlayers.contains(member) || !NAME_PATTERN.matcher(member).matches()) continue;
                class_640 entry3 = (class_640)nameToEntry.get(member.toLowerCase(Locale.ROOT));
                boolean bl = isVanished = entry3 == null;
                if (isVanished ? prefix.isEmpty() : !PREFIX_MATCHES.matcher(cleanPrefixLower).matches() && !StaffRepository.isStaff(member)) continue;
                UUID uuid = entry3 != null ? entry3.method_2966().getId() : null;
                StaffPlayer player = new StaffPlayer(member, prefixComponent, uuid);
                detectedNames.add(member.toLowerCase(Locale.ROOT));
                StaffRow existing = this.rowByName.get(member.toLowerCase(Locale.ROOT));
                if (existing == null) {
                    StaffRow row2 = new StaffRow(player, new Decelerate().setMs(150).setValue(1.0));
                    this.rowByName.put(member.toLowerCase(Locale.ROOT), row2);
                    this.staffRows.add(row2);
                } else {
                    existing.player = player;
                    existing.anim.setDirection(Direction.FORWARDS);
                }
                this.addedPlayers.add(member);
            }
        }
        this.staffRows.sort(Comparator.comparing(row -> row.player.name, String.CASE_INSENSITIVE_ORDER));
        for (StaffRow row3 : this.staffRows) {
            if (detectedNames.contains(row3.player.name.toLowerCase(Locale.ROOT))) continue;
            row3.anim.setDirection(Direction.BACKWARDS);
        }
        this.rowByName.entrySet().removeIf(entry -> {
            StaffRow row = (StaffRow)entry.getValue();
            if (row.anim.isFinished(Direction.BACKWARDS)) {
                this.staffRows.remove(row);
                return true;
            }
            return false;
        });
    }

    private void markAllRowsRemoved() {
        for (StaffRow row : this.staffRows) {
            row.anim.setDirection(Direction.BACKWARDS);
        }
    }

    private static String repairString(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(input.length());
        for (char c : input.toCharArray()) {
            if (c >= '\uff01' && c <= '\uff5e') {
                sb.append((char)(c - 65248));
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    @Nullable
    private static class_2960 resolveSkinTexture(String playerName, @Nullable UUID uuid) {
        class_2960 texture;
        class_310 client = class_310.method_1551();
        if (client == null) {
            return null;
        }
        class_640 entry = StaffList.findPlayerListEntry(client, uuid, playerName);
        if (entry != null && entry.method_52810() != null && (texture = entry.method_52810().comp_1626()) != null) {
            return texture;
        }
        if (entry != null && entry.method_2966() != null) {
            return client.method_1582().method_52862(entry.method_2966()).comp_1626();
        }
        UUID resolvedUuid = uuid != null ? uuid : UUID.nameUUIDFromBytes(("OfflinePlayer:" + playerName).getBytes(StandardCharsets.UTF_8));
        return class_1068.method_4648((UUID)resolvedUuid).comp_1626();
    }

    @Nullable
    private static class_640 findPlayerListEntry(class_310 client, @Nullable UUID uuid, String playerName) {
        class_640 byUuid;
        if (client.method_1562() == null) {
            return null;
        }
        if (uuid != null && (byUuid = client.method_1562().method_2871(uuid)) != null) {
            return byUuid;
        }
        for (class_640 entry : client.method_1562().method_2880()) {
            if (entry.method_2966() == null || entry.method_2966().getName() == null || !entry.method_2966().getName().equalsIgnoreCase(playerName)) continue;
            return entry;
        }
        return null;
    }

    private static int getStatusColor(StaffStatus status) {
        return switch (status.ordinal()) {
            default -> throw new MatchException(null, null);
            case 1 -> Color.ORANGE.getRGB();
            case 2 -> Color.YELLOW.getRGB();
            case 3 -> Color.RED.getRGB();
            case 0 -> Color.GREEN.getRGB();
        };
    }

    private static final class StaffRow {
        private StaffPlayer player;
        private final Animation anim;

        private StaffRow(StaffPlayer player, Animation anim) {
            this.player = player;
            this.anim = anim;
        }
    }

    private static final class StaffPlayer {
        private final String name;
        private final StaffStatus status;
        @Nullable
        private final UUID uuid;

        private StaffPlayer(String name, class_2561 prefix, @Nullable UUID uuid) {
            this.name = name;
            this.uuid = uuid;
            this.status = this.resolveStatus(uuid);
        }

        private StaffStatus resolveStatus(@Nullable UUID uuid) {
            class_310 client = class_310.method_1551();
            if (client == null || client.field_1687 == null || client.method_1562() == null) {
                return StaffStatus.VANISHED;
            }
            class_640 entry = StaffList.findPlayerListEntry(client, uuid, this.name);
            if (entry == null) {
                return StaffStatus.VANISHED;
            }
            if (entry.method_2958() == class_1934.field_9219) {
                return StaffStatus.SPEC;
            }
            if (client.field_1687.method_18470(entry.method_2966().getId()) != null) {
                return StaffStatus.NEAR;
            }
            return StaffStatus.ONLINE;
        }
    }

    private static enum StaffStatus {
        ONLINE("[ON]"),
        NEAR("[N]"),
        SPEC("[GM3]"),
        VANISHED("[V]");

        private final String label;

        private StaffStatus(String label) {
            this.label = label;
        }
    }
}
