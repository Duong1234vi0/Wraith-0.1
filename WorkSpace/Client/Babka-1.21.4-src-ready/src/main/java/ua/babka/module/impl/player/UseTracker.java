package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.network.packet.s2c.play.ItemPickupAnimationS2CPacket;
import net.minecraft.text.TextColor;
import net.minecraft.world.World;
import ua.babka.event.EventPacket;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.player.StreamerMode;
import ua.babka.module.impl.render.Interface;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.StringSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.font.Fonts;
import ua.babka.util.Instance;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;
import ua.babka.util.player.InventoryUtil;

@ModuleInformation(moduleName="UseTracker", moduleDesc="Отслеживает использование предметов", moduleCategory=ModuleCategory.MISC)
public class UseTracker
extends Module {
    private final ModeSetting mode = new ModeSetting("Режим", "Чат", "Чат", "Вебхук");
    private final StringSetting webhook = (StringSetting)new StringSetting("Webhook URL", "").setVisible(() -> this.mode.is("Вебхук"));
    private final List<PickupLog> logs = new CopyOnWriteArrayList<PickupLog>();
    private static final String[] ALLOWED_PICKUP_KEYWORDS = new String[]{"незерит", "набор", "шар", "талисман", "зелье", "арбалет", "элитры", "фейерверк", "яблоко", "солнечн", "трезубец"};

    public UseTracker() {
        HudRenderCallback.EVENT.register(this::onRenderHUD);
    }

    @Override
    public void onDisable() {
        this.logs.clear();
        super.onDisable();
    }

    private String getProtectedName(PlayerEntity player) {
        boolean isStreamerModetEnabled;
        String originalName = player.getName().getString();
        StreamerMode streamerMode = Instance.get(StreamerMode.class);
        boolean bl = isStreamerModetEnabled = streamerMode != null && streamerMode.isEnabled();
        if (isStreamerModetEnabled) {
            boolean isMe = player.equals((Object)this.mc.player);
            boolean isFriend = FriendRepository.isFriend(player.getNameForScoreboard());
            if (isMe || isFriend) {
                return "Protected";
            }
        }
        return originalName;
    }

    private void sendWebhook(String playerName, String action, String itemName) {
        if (this.mode.is("Вебхук") && !this.webhook.getValue().isEmpty()) {
            String message = playerName + " " + action + " " + itemName;
            InventoryUtil.sendDiscordWebhook(this.webhook.getValue(), message);
        }
    }

    @Subscribe
    public void onPacketReceive(EventPacket event) {
        Entity itemEntity;
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        Packet<?> packet = event.getPacket();
        if (packet instanceof ItemPickupAnimationS2CPacket) {
            ItemPickupAnimationS2CPacket packet2 = (ItemPickupAnimationS2CPacket)packet;
            itemEntity = this.mc.world.getEntityById(packet2.getEntityId());
            Entity collectorEntity = this.mc.world.getEntityById(packet2.getCollectorEntityId());
            if (itemEntity instanceof ItemEntity) {
                ItemEntity item = (ItemEntity)itemEntity;
                if (collectorEntity instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity)collectorEntity;
                    if (player == this.mc.player) {
                        return;
                    }
                    ItemStack stack = item.getStack().copy();
                    String rawName = stack.getName().getString();
                    String cleanName = rawName.replaceAll("(?i)§[0-9a-fk-orx]", "");
                    String itemName = cleanName.toLowerCase(Locale.ROOT);
                    boolean shouldLog = false;
                    for (String keyword : ALLOWED_PICKUP_KEYWORDS) {
                        if (!itemName.contains(keyword)) continue;
                        shouldLog = true;
                        break;
                    }
                    if (shouldLog) {
                        stack.setCount(packet2.getStackAmount());
                        String playerName = this.getProtectedName(player);
                        this.logs.add(new PickupLog(playerName, stack, 3000L, "Подобрал:"));
                        this.sendWebhook(playerName, "подобрал", cleanName);
                    }
                }
            }
        }
        if ((itemEntity = event.getPacket()) instanceof EntityStatusS2CPacket) {
            Entity entity;
            EntityStatusS2CPacket statusPacket = (EntityStatusS2CPacket)itemEntity;
            if (statusPacket.getStatus() == 9) {
                entity = statusPacket.getEntity((World)this.mc.world);
                if (entity instanceof PlayerEntity) {
                    player = (PlayerEntity)entity;
                    if (player == this.mc.player) {
                        return;
                    }
                    ItemStack usedStack = player.getMainHandStack();
                    if (usedStack.isEmpty() || !usedStack.contains(DataComponentTypes.FOOD) && usedStack.getItem() != Items.POTION) {
                        usedStack = player.getOffHandStack();
                    }
                    if (!usedStack.isEmpty()) {
                        String playerName = this.getProtectedName(player);
                        String itemName = usedStack.getName().getString();
                        this.logs.add(new PickupLog(playerName, usedStack.copy(), 3000L, "Использовал:"));
                        this.sendWebhook(playerName, "использовал", itemName);
                    }
                }
            } else if (statusPacket.getStatus() == 35 && (entity = statusPacket.getEntity((World)this.mc.world)) instanceof PlayerEntity) {
                player = (PlayerEntity)entity;
                if (player == this.mc.player) {
                    return;
                }
                String playerName = this.getProtectedName(player);
                ItemStack totemStack = Items.TOTEM_OF_UNDYING.getDefaultStack();
                this.logs.add(new PickupLog(playerName, totemStack, 3000L, "Потерял:"));
                this.sendWebhook(playerName, "потерял", "Тотем бессмертия");
            }
        }
    }

    private void onRenderHUD(DrawContext context, RenderTickCounter tickCounter) {
        float startY;
        if (this.logs.isEmpty() || !this.isEnabled()) {
            return;
        }
        if (this.mode.is("Вебхук")) {
            return;
        }
        int screenWidth = this.mc.getWindow().getScaledWidth();
        int screenHeight = this.mc.getWindow().getScaledHeight();
        float currentY = startY = (float)screenHeight / 2.0f + 20.0f;
        Interface hudModule = Instance.get(Interface.class);
        for (PickupLog log : this.logs) {
            log.update();
            float animValue = log.animation.getValue();
            if (log.isRemoving && animValue <= 0.01f) {
                this.logs.remove(log);
                continue;
            }
            this.renderLog(context, log, screenWidth, currentY, animValue, hudModule);
            currentY += 16.0f * animValue;
        }
    }

    private void renderLog(DrawContext context, PickupLog log, int screenWidth, float y2, float animValue, Interface hud) {
        String actionText = log.playerName + " " + log.actionText;
        String itemName = log.stack.getName().getString();
        float fontSize = 6.5f;
        float actionWidth = Fonts.SFMEDIUM.get().getWidth(actionText, fontSize);
        float itemWidth = Fonts.SFMEDIUM.get().getWidth(itemName, fontSize);
        float gap = 4.5f;
        float height = 13.0f;
        float totalWidth = 20.0f + actionWidth + gap + itemWidth + 5.0f;
        float x2 = ((float)screenWidth - totalWidth) / 2.0f;
        int alphaInt = (int)(255.0f * Math.max(0.0f, Math.min(1.0f, animValue)));
        context.getMatrices().push();
        context.getMatrices().translate(x2 + totalWidth / 2.0f, y2 + height / 2.0f, 0.0f);
        context.getMatrices().scale(animValue, animValue, 1.0f);
        context.getMatrices().translate(-(x2 + totalWidth / 2.0f), -(y2 + height / 2.0f), 0.0f);
        if (hud != null && hud.isEnabled()) {
            hud.drawBackground(x2, y2, totalWidth, height, 3.0f, alphaInt);
        } else {
            DrawUtil.drawRound(x2, y2, totalWidth, height, 3.0f, ColorProvider.rgba(25, 25, 25, (int)(150.0f * animValue)));
        }
        context.getMatrices().push();
        float iconScale = 0.6f;
        context.getMatrices().translate(x2 + 3.5f, y2 + 1.5f, 0.0f);
        context.getMatrices().scale(iconScale, iconScale, 1.0f);
        context.drawItem(log.stack, 0, 0);
        context.getMatrices().pop();
        DrawUtil.drawRound(x2 + 16.0f, y2 + 2.0f, 0.5f, height - 4.0f, 0.0f, ColorProvider.rgba(125, 125, 125, alphaInt));
        int actionColor = ColorProvider.rgba(255, 255, 255, alphaInt);
        int itemColorRgb = 0xFFAA00;
        TextColor styleColor = log.stack.getName().getStyle().getColor();
        if (styleColor != null) {
            itemColorRgb = styleColor.getRgb();
        }
        int itemColor = ColorProvider.rgba(itemColorRgb >> 16 & 0xFF, itemColorRgb >> 8 & 0xFF, itemColorRgb & 0xFF, alphaInt);
        float textX = x2 + 20.0f;
        float textY = y2 + 2.75f;
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), actionText, textX, textY, actionColor, fontSize);
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), itemName, textX + actionWidth + gap, textY, itemColor, fontSize);
        context.getMatrices().pop();
    }

    private static class PickupLog {
        public final String playerName;
        public final ItemStack stack;
        public final String actionText;
        private final long startTime;
        private final long maxLifeTime;
        public boolean isRemoving = false;
        public final Animation animation;

        public PickupLog(String playerName, ItemStack stack, long maxLifeTime, String actionText) {
            this.playerName = playerName;
            this.stack = stack;
            this.maxLifeTime = maxLifeTime;
            this.actionText = actionText;
            this.startTime = System.currentTimeMillis();
            this.animation = new Animation(Easing.BACK_OUT, 300L);
        }

        public void update() {
            long timeAlive = System.currentTimeMillis() - this.startTime;
            if (timeAlive > this.maxLifeTime && !this.isRemoving) {
                this.isRemoving = true;
            }
            this.animation.run(this.isRemoving ? 0.0f : 1.0f);
        }
    }
}

