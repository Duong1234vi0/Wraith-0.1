package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector2f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector4f;
import ua.babka.Babka;
import ua.babka.event.EventHUD;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.player.StreamerMode;
import ua.babka.module.impl.render.Interface;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.ProjectionUtil;
import ua.babka.render.ReplaceUtil;
import ua.babka.render.builder.Builder;
import ua.babka.render.builder.BuiltTexture;
import ua.babka.render.builder.QuadColorState;
import ua.babka.render.builder.QuadRadiusState;
import ua.babka.render.builder.SizeState;
import ua.babka.render.font.Fonts;
import ua.babka.render.font.MsdfFont;
import ua.babka.render.model.IRenderer;
import ua.babka.server.Server;

@ModuleInformation(moduleName="Tags", moduleDesc="Имена и теги над головами", moduleCategory=ModuleCategory.RENDER)
public class Tags
extends Module {
    private final ModeListSetting entityTypes = new ModeListSetting("На кого работать", new BooleanSetting("Игроки", true), new BooleanSetting("Голые игроки", true), new BooleanSetting("Предметы", true), new BooleanSetting("Мобы", false), new BooleanSetting("Друзья", true));
    private final Map<UUID, Text> normalizedNames = new ConcurrentHashMap<UUID, Text>();
    private int clearCacheTicker = 0;
    private final Map<UUID, Float> itemTagWidths = new ConcurrentHashMap<UUID, Float>();
    private static final float SLOT_SIZE = 14.0f;
    private static final float SLOT_GAP = 1.5f;
    private static final float SLOT_RADIUS = 2.0f;
    private static final float ITEM_SIZE = 11.0f;

    public static Text normalizeSmallCaps(Text text) {
        String[] from = new String[]{"ᴀ", "ʙ", "ᴄ", "ᴅ", "ᴇ", "ꜰ", "ɢ", "ʜ", "ɪ", "ᴊ", "ᴋ", "ʟ", "ᴍ", "ɴ", "ᴏ", "ᴘ", "ǫ", "ʀ", "ꜱ", "ᴛ", "ᴜ", "ᴠ", "ᴡ", "x", "ʏ", "ᴢ", "◆", "┃ "};
        String[] to = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "●", ""};
        for (int i2 = 0; i2 < from.length; ++i2) {
            text = ReplaceUtil.replace(text, from[i2], to[i2]);
        }
        return text;
    }

    public static Text processName(PlayerEntity entity) {
        String s2;
        Text name = entity.getDisplayName();
        MinecraftClient mc = MinecraftClient.getInstance();
        if (Babka.getInstance().getModuleStorage().get(StreamerMode.class).isEnabled() && mc.player != null) {
            String scoreName = entity.getNameForScoreboard();
            String myName = mc.player.getNameForScoreboard();
            String customName = Babka.getInstance().getModuleStorage().get(StreamerMode.class).getCustomName();
            if (scoreName.equals(myName) || entity.getUuid().equals(mc.player.getUuid())) {
                name = ReplaceUtil.replace(name, myName, customName);
            } else if (Babka.getInstance().getModuleStorage().get(StreamerMode.class).hideFriends.getValue() && FriendRepository.isFriend(scoreName)) {
                name = ReplaceUtil.replace(name, scoreName, customName);
            }
        }
        if ((s2 = name.getString()).contains("ꔲ")) {
            name = ReplaceUtil.replace(name, "ꔲ", "§5BULL");
        }
        if (s2.contains("ꕓ")) {
            name = ReplaceUtil.replace(name, "ꕓ", "§8GHOST");
        }
        if (s2.contains("ꔨ")) {
            name = ReplaceUtil.replace(name, "ꔨ", "§dDRAGON");
        }
        if (s2.contains("ꔂ")) {
            name = ReplaceUtil.replace(name, "ꔂ", "§9D.MODER");
        }
        if (s2.contains("ꔦ")) {
            name = ReplaceUtil.replace(name, "ꔦ", "§9D.ML.ADMIN");
        }
        if (s2.contains("ꕀ")) {
            name = ReplaceUtil.replace(name, "ꕀ", "§2HYDRA");
        }
        if (s2.contains("ꕖ")) {
            name = ReplaceUtil.replace(name, "ꕖ", "§7BUNNY");
        }
        if (s2.contains("ꕒ")) {
            name = ReplaceUtil.replace(name, "ꕒ", "§fRABBIT");
        }
        if (s2.contains("ꕈ")) {
            name = ReplaceUtil.replace(name, "ꕈ", "§aCOBRA");
        }
        if (s2.contains("ꔶ")) {
            name = ReplaceUtil.replace(name, "ꔶ", "§6TIGER");
        }
        if (s2.contains("ꕠ")) {
            name = ReplaceUtil.replace(name, "ꕠ", "§eD.HELPER");
        }
        if (s2.contains("ꔉ")) {
            name = ReplaceUtil.replace(name, "ꔉ", "§eHELPER");
        }
        if (s2.contains("ꔆ")) {
            name = ReplaceUtil.replace(name, "ꔆ", "§7D.MODER");
        }
        if (s2.contains("ꕄ")) {
            name = ReplaceUtil.replace(name, "ꕄ", "§4DRACULA");
        }
        if (s2.contains("ꔰ")) {
            name = ReplaceUtil.replace(name, "ꔰ", "§7D.ML.ADMIN");
        }
        if (s2.contains("ꔐ")) {
            name = ReplaceUtil.replace(name, "ꔐ", "§1D.GL.MODER");
        }
        if (s2.contains("ꔔ")) {
            name = ReplaceUtil.replace(name, "ꔔ", "§7D.GL.MODER");
        }
        if (s2.contains("ꔢ")) {
            name = ReplaceUtil.replace(name, "ꔢ", "§7D.ST.MODER");
        }
        if (s2.contains("ꕡ")) {
            name = ReplaceUtil.replace(name, "ꕡ", "§6ST.HELPER");
        }
        if (s2.contains("ꕅ")) {
            name = ReplaceUtil.replace(name, "ꕅ", "§5MEDIA+");
        }
        if (s2.contains("ꔗ")) {
            name = ReplaceUtil.replace(name, "ꔗ", "§9MODER");
        }
        if (s2.contains("ꕗ")) {
            name = ReplaceUtil.replace(name, "ꕗ", "§4D.ADMIN");
        }
        if (s2.contains("ꔘ")) {
            name = ReplaceUtil.replace(name, "ꔘ", "§9D.ST.MODER");
        }
        if (s2.contains("ꔳ")) {
            name = ReplaceUtil.replace(name, "ꔳ", "§bML.ADMIN");
        }
        if (s2.contains("ꔁ")) {
            name = ReplaceUtil.replace(name, "ꔁ", "§5MEDIA");
        }
        if (s2.contains("ꔅ")) {
            name = ReplaceUtil.replace(name, "ꔅ", "§cYT");
        }
        if (s2.contains("ꕁ")) {
            name = ReplaceUtil.replace(name, "ꕁ", "§6LEGENDA");
        }
        return Tags.normalizeSmallCaps(name);
    }

    private Text getNormalizedName(PlayerEntity entity) {
        return this.normalizedNames.computeIfAbsent(entity.getUuid(), uuid -> Tags.processName(entity));
    }

    private boolean isNaked(PlayerEntity entity) {
        return entity.getEquippedStack(EquipmentSlot.HEAD).isEmpty() && entity.getEquippedStack(EquipmentSlot.CHEST).isEmpty() && entity.getEquippedStack(EquipmentSlot.LEGS).isEmpty() && entity.getEquippedStack(EquipmentSlot.FEET).isEmpty();
    }

    public void renderPreview(DrawContext context, PlayerEntity entity, float cx, float topY, int alpha) {
        if (!(entity instanceof AbstractClientPlayerEntity)) {
            return;
        }
        AbstractClientPlayerEntity clientPlayer = (AbstractClientPlayerEntity)entity;
        MsdfFont font = Fonts.SFMEDIUM.get();
        Text name = this.getNormalizedName(entity);
        String nameStr = name.getString();
        float paddingX = 4.0f;
        float headIconSize = 11.0f;
        float headIconPad = 2.5f;
        float textWidth = font.getWidth(nameStr, 7.5f);
        float totalWidth = textWidth + paddingX * 2.0f + headIconSize + headIconPad;
        float tagHeight = 13.5f;
        float bgX = cx - totalWidth / 2.0f;
        List<ItemStack> visibleSlots = this.collectNonEmptySlots(entity);
        float tagY = topY;
        if (!visibleSlots.isEmpty()) {
            int count = visibleSlots.size();
            float rowWidth = (float)count * 14.0f + (float)(count - 1) * 1.5f;
            float armorStartX = cx - rowWidth / 2.0f;
            float armorY = tagY - 14.0f - 2.0f;
            for (int i2 = 0; i2 < count; ++i2) {
                float slotX = armorStartX + (float)i2 * 15.5f;
                DrawUtil.drawRound(slotX, armorY, 14.0f, 14.0f, new Vector4f(2.0f, 2.0f, 2.0f, 2.0f), ColorProvider.rgba(28, 28, 28, 200.0f));
                ItemStack stack = visibleSlots.get(i2);
                float iconOffset = 1.5f;
                MatrixStack matrices = context.getMatrices();
                matrices.push();
                matrices.translate(slotX + iconOffset, armorY + iconOffset, 0.0f);
                matrices.scale(0.6875f, 0.6875f, 1.0f);
                context.drawItem(stack, 0, 0);
                context.drawStackOverlay(this.mc.textRenderer, stack, 0, 0);
                matrices.pop();
            }
        }
        DrawUtil.drawRound(bgX, tagY, totalWidth, tagHeight, new Vector4f(0.4f, 0.4f, 0.4f, 0.4f), ColorProvider.rgba(0, 0, 0, (int)(125.0f * Math.min(1.0f, (float)alpha / 255.0f))));
        float headX = bgX + 1.0f;
        float headY = tagY + (tagHeight - headIconSize) / 2.0f;
        AbstractTexture skinTex = this.mc.getTextureManager().getTexture(clientPlayer.getSkinTextures().comp_1626());
        int texId = skinTex.getGlId();
        ((BuiltTexture)Builder.texture().size(new SizeState(headIconSize, headIconSize)).radius(new QuadRadiusState(1.0f)).color(new QuadColorState(-1)).texture(0.125f, 0.125f, 0.125f, 0.125f, texId).build()).render(IRenderer.DEFAULT_MATRIX, headX, headY, 0.0f);
        DrawUtil.drawText(font, name, bgX + paddingX + 0.5f + headIconSize + headIconPad, tagY + 3.0f, 7.5f, ColorProvider.rgba(255, 255, 255, alpha));
    }

    @Subscribe
    private void onRender(EventHUD e2) {
        if (this.clearCacheTicker++ > 100) {
            this.normalizedNames.clear();
            this.clearCacheTicker = 0;
        }
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        MsdfFont font = Fonts.SFMEDIUM.get();
        float tickDelta = e2.getRenderTickCounter().getTickDelta(true);
        boolean showPlayers = this.entityTypes.isEnabled("Игроки");
        boolean showNaked = this.entityTypes.isEnabled("Голые игроки");
        boolean showMobs = this.entityTypes.isEnabled("Мобы");
        boolean showFriends = this.entityTypes.isEnabled("Друзья");
        if (showPlayers || showNaked || showFriends) {
            this.renderPlayerTags(font, tickDelta, e2, showPlayers, showNaked, showFriends);
        }
        if (this.entityTypes.isEnabled("Предметы")) {
            this.renderItemTags(font, tickDelta, e2);
        }
        if (showMobs) {
            this.renderMobTags(font, tickDelta, e2);
        }
    }

    private List<ItemStack> collectNonEmptySlots(PlayerEntity entity) {
        ItemStack[] allSlots = new ItemStack[]{entity.getMainHandStack(), entity.getEquippedStack(EquipmentSlot.HEAD), entity.getEquippedStack(EquipmentSlot.CHEST), entity.getEquippedStack(EquipmentSlot.LEGS), entity.getEquippedStack(EquipmentSlot.FEET), entity.getOffHandStack()};
        ArrayList<ItemStack> result = new ArrayList<ItemStack>();
        for (ItemStack stack : allSlots) {
            if (stack.isEmpty()) continue;
            result.add(stack);
        }
        return result;
    }

    private void renderPlayerTags(MsdfFont font, float tickDelta, EventHUD e2, boolean showPlayers, boolean showNaked, boolean showFriends) {
        List worldPlayers = this.mc.world.getPlayers();
        for (PlayerEntity entity : worldPlayers) {
            Interface iface;
            MutableText name;
            double ez;
            double ey;
            double ex;
            Vector2f pos;
            if (entity == this.mc.player && !this.mc.getEntityRenderDispatcher().camera.isThirdPerson()) continue;
            boolean naked = this.isNaked(entity);
            boolean isFriend = FriendRepository.isFriend(entity.getNameForScoreboard());
            boolean show = false;
            if (showPlayers) {
                show = true;
            }
            if (showNaked && naked) {
                show = true;
            }
            if (showFriends && isFriend) {
                show = true;
            }
            if (!show || !showPlayers && !showNaked && showFriends && !isFriend || (pos = ProjectionUtil.project(ex = MathHelper.lerp((double)tickDelta, (double)entity.lastRenderX, (double)entity.getX()), ey = MathHelper.lerp((double)tickDelta, (double)entity.lastRenderY, (double)entity.getY()) + (double)entity.getHeight() + 0.5, ez = MathHelper.lerp((double)tickDelta, (double)entity.lastRenderZ, (double)entity.getZ()))).getX() == Float.MAX_VALUE || pos.getY() == Float.MAX_VALUE) continue;
            float screenYOffset = 5.7f;
            float posY = pos.getY() - 5.7f;
            float centerX = pos.getX();
            boolean friendHighlight = isFriend && showFriends;
            int slotBgColor = friendHighlight ? ColorProvider.rgba(0, 130, 0, 200.0f) : ColorProvider.rgba(28, 28, 28, 210.0f);
            int tagBgColor = friendHighlight ? ColorProvider.rgba(0, 130, 0, 150.0f) : ColorProvider.rgba(0, 0, 0, 125.0f);
            int blurOverlayColor = friendHighlight ? ColorProvider.rgba(0, 130, 0, 38.0f) : ColorProvider.rgba(25, 25, 25, 38.0f);
            int blurOverlayTagColor = friendHighlight ? ColorProvider.rgba(0, 130, 0, 25.0f) : ColorProvider.rgba(25, 25, 25, 25.0f);
            Text baseName = this.getNormalizedName(entity);
            int currentHp = (int)Server.getHealth((LivingEntity)entity, false);
            int hpColor = 0xFF5555;
            if (friendHighlight) {
                name = Text.literal((String)baseName.getString()).setStyle(Style.EMPTY.withColor(Formatting.WHITE));
                name.append((Text)Text.literal((String)" ♥").setStyle(Style.EMPTY.withColor(Formatting.WHITE)));
            } else {
                name = baseName.copy();
            }
            ItemStack offHandStack = entity.getOffHandStack();
            if (offHandStack.getItem() == Items.PLAYER_HEAD) {
                name.append((Text)Text.literal((String)" [").setStyle(Style.EMPTY.withColor(Formatting.GRAY))).append(offHandStack.getName()).append((Text)Text.literal((String)"]").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
            }
            name.append((Text)Text.literal((String)" [").setStyle(Style.EMPTY.withColor(Formatting.GRAY))).append((Text)Text.literal((String)String.valueOf(currentHp)).setStyle(Style.EMPTY.withColor(hpColor))).append((Text)Text.literal((String)"]").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
            float paddingX = 4.0f;
            float headIconSize = 11.0f;
            float headIconPad = 2.5f;
            float textWidth = font.getWidth(name.getString(), 7.5f);
            float totalWidth = textWidth + paddingX * 2.0f + headIconSize + headIconPad;
            float tagHeight = 13.5f;
            float tagY = posY + 3.0f;
            float bgX = centerX - totalWidth / 2.0f;
            List<ItemStack> visibleSlots = this.collectNonEmptySlots(entity);
            if (!visibleSlots.isEmpty()) {
                int count = visibleSlots.size();
                float rowWidth = (float)count * 14.0f + (float)(count - 1) * 1.5f;
                float armorStartX = centerX - rowWidth / 2.0f;
                float armorY = tagY - 14.0f - 2.0f;
                Vector4f slotRadii = new Vector4f(2.0f, 2.0f, 2.0f, 2.0f);
                MatrixStack matrices = e2.getDrawContext().getMatrices();
                for (int i2 = 0; i2 < count; ++i2) {
                    float slotX = armorStartX + (float)i2 * 15.5f;
                    DrawUtil.drawRound(slotX, armorY, 14.0f, 14.0f, slotRadii, slotBgColor);
                    ItemStack stack = visibleSlots.get(i2);
                    float iconOffset = 1.5f;
                    matrices.push();
                    matrices.translate(slotX + iconOffset, armorY + iconOffset, 0.0f);
                    matrices.scale(0.6875f, 0.6875f, 1.0f);
                    e2.getDrawContext().drawItem(stack, 0, 0);
                    e2.getDrawContext().drawStackOverlay(this.mc.textRenderer, stack, 0, 0);
                    matrices.pop();
                }
            }
            if ((iface = Babka.getInstance().getModuleStorage().get(Interface.class)) != null) {
                Vector4f radii = new Vector4f(0.4f, 0.4f, 0.4f, 0.4f);
                DrawUtil.drawRound(bgX, tagY, totalWidth, tagHeight, radii, tagBgColor);
            }
            float headX = bgX + 1.0f;
            float headY = tagY + (tagHeight - headIconSize) / 2.0f;
            if (entity instanceof AbstractClientPlayerEntity) {
                AbstractClientPlayerEntity clientPlayer = (AbstractClientPlayerEntity)entity;
                AbstractTexture skinTex = this.mc.getTextureManager().getTexture(clientPlayer.getSkinTextures().comp_1626());
                int texId = skinTex.getGlId();
                ((BuiltTexture)Builder.texture().size(new SizeState(headIconSize, headIconSize)).radius(new QuadRadiusState(1.0f)).color(new QuadColorState(-1)).texture(0.125f, 0.125f, 0.125f, 0.125f, texId).build()).render(IRenderer.DEFAULT_MATRIX, headX, headY, 0.0f);
            }
            DrawUtil.drawText(font, (Text)name, bgX + paddingX + 0.5f + headIconSize + headIconPad, posY + 5.5f, 7.5f, 220);
        }
    }

    private void renderMobTags(MsdfFont font, float tickDelta, EventHUD e2) {
        for (Entity entity : this.mc.world.getEntities()) {
            double z2;
            double y2;
            if (!(entity instanceof MobEntity)) continue;
            MobEntity mob = (MobEntity)entity;
            double x2 = MathHelper.lerp((double)tickDelta, (double)entity.lastRenderX, (double)entity.getX());
            Vector2f pos = ProjectionUtil.project(x2, y2 = MathHelper.lerp((double)tickDelta, (double)entity.lastRenderY, (double)entity.getY()) + (double)entity.getHeight() + 0.5, z2 = MathHelper.lerp((double)tickDelta, (double)entity.lastRenderZ, (double)entity.getZ()));
            if (pos.getX() == Float.MAX_VALUE || pos.getY() == Float.MAX_VALUE) continue;
            float screenYOffset = 5.7f;
            float posY = pos.getY() - 5.7f;
            String mobName = entity.getName().getString();
            int currentHp = (int)mob.getHealth();
            int hpColor = 0xFF5555;
            MutableText nameText = Text.literal((String)mobName).copy();
            nameText.append((Text)Text.literal((String)" [").setStyle(Style.EMPTY.withColor(Formatting.GRAY))).append((Text)Text.literal((String)String.valueOf(currentHp)).setStyle(Style.EMPTY.withColor(hpColor))).append((Text)Text.literal((String)"]").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
            float paddingX = 4.0f;
            float textWidth = font.getWidth(nameText.getString(), 7.0f);
            float totalWidth = textWidth + paddingX * 2.0f;
            float tagHeight = 11.5f;
            float tagY = posY + 3.0f;
            float bgX = pos.getX() - totalWidth / 2.0f;
            Vector4f radii = new Vector4f(0.4f, 0.4f, 0.4f, 0.4f);
            DrawUtil.drawRound(bgX, tagY, totalWidth, tagHeight, radii, ColorProvider.rgba(0, 0, 0, 125.0f));
            DrawUtil.drawText(font, (Text)nameText, bgX + paddingX, posY + 5.0f, 7.0f, 220);
        }
    }

    private void renderItemTags(MsdfFont font, float tickDelta, EventHUD e2) {
        for (Entity entity : this.mc.world.getEntities()) {
            ItemStack stack;
            double z2;
            double y2;
            if (!(entity instanceof ItemEntity)) continue;
            ItemEntity itemEntity = (ItemEntity)entity;
            double x2 = MathHelper.lerp((double)tickDelta, (double)entity.lastRenderX, (double)entity.getX());
            Vector2f pos = ProjectionUtil.project(x2, y2 = MathHelper.lerp((double)tickDelta, (double)entity.lastRenderY, (double)entity.getY()) + (double)entity.getHeight() + 0.5, z2 = MathHelper.lerp((double)tickDelta, (double)entity.lastRenderZ, (double)entity.getZ()));
            if (pos.getX() == Float.MAX_VALUE || pos.getY() == Float.MAX_VALUE || (stack = itemEntity.getStack()).isEmpty()) continue;
            int rarityOrdinal = stack.getRarity().ordinal();
            Formatting rarityColor = switch (rarityOrdinal) {
                case 0 -> Formatting.WHITE;
                case 1 -> Formatting.YELLOW;
                case 2 -> Formatting.AQUA;
                case 3 -> Formatting.LIGHT_PURPLE;
                default -> Formatting.WHITE;
            };
            String itemName = stack.getName().getString();
            MutableText nameText = Text.literal((String)itemName).setStyle(Style.EMPTY.withColor(rarityColor));
            if (!stack.getName().getSiblings().isEmpty()) {
                nameText = stack.getName();
            }
            MutableText countComponent = stack.getCount() > 1 ? Text.literal((String)" [").setStyle(Style.EMPTY.withColor(Formatting.GRAY)).append((Text)Text.literal((String)String.valueOf(stack.getCount())).setStyle(Style.EMPTY.withColor(Formatting.RED))).append((Text)Text.literal((String)"]").setStyle(Style.EMPTY.withColor(Formatting.GRAY))) : Text.empty();
            MutableText textComponent = nameText.copy().append((Text)countComponent);
            Text normalized = Tags.normalizeSmallCaps((Text)textComponent);
            float itemIconSize = 10.0f;
            float padLeft = itemIconSize + 4.0f;
            float paddingX = 3.0f;
            float textWidth = font.getWidth(normalized.getString(), 7.0f);
            float targetWidth = textWidth + paddingX * 2.0f + padLeft;
            float animatedWidth = this.itemTagWidths.compute(itemEntity.getUuid(), (uuid, prev) -> {
                if (prev == null) {
                    return Float.valueOf(targetWidth);
                }
                return Float.valueOf(prev.floatValue() + (targetWidth - prev.floatValue()) * 0.15f);
            }).floatValue();
            float bgX = pos.getX() - animatedWidth / 2.0f;
            float tagY = pos.getY() - 1.5f;
            float tagH = 11.5f;
            Vector4f radii = new Vector4f(0.4f, 0.4f, 0.4f, 0.4f);
            DrawUtil.drawRound(bgX, tagY, animatedWidth - 0.5f, tagH, radii, ColorProvider.rgba(0, 0, 0, 125.0f));
            float iconX = bgX + paddingX;
            float iconY = tagY + (tagH - itemIconSize) / 2.0f;
            MatrixStack matrices = e2.getDrawContext().getMatrices();
            matrices.push();
            matrices.translate(iconX, iconY, 0.0f);
            matrices.scale(itemIconSize / 16.0f, itemIconSize / 16.0f, 1.0f);
            e2.getDrawContext().drawItem(stack, 0, 0);
            e2.getDrawContext().drawStackOverlay(this.mc.textRenderer, stack, 0, 0);
            matrices.pop();
            DrawUtil.drawText(font, normalized, bgX + paddingX + padLeft, pos.getY() + 0.5f, 7.0f, 220);
        }
    }
}

