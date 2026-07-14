package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector2f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.ChestMinecartEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;
import ua.babka.event.EventHUD;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.ProjectionUtil;
import ua.babka.render.font.Fonts;
import ua.babka.render.font.MsdfFont;

@ModuleInformation(moduleName="LootTracker", moduleDesc="Показывает залутанные спавнеры и вагонетки", moduleCategory=ModuleCategory.RENDER)
public class LootTracker
extends Module {
    private static final float TAG_BOX_HEIGHT = 12.5f;
    private static final float TAG_PADDING = 5.0f;
    private static final float TAG_HUD_RADIUS = 1.1f;
    private static final int TAG_HUD_ALPHA = 204;
    private static final float FONT_SIZE = 7.0f;
    private final BooleanSetting showSpawners = new BooleanSetting("Спавнеры", true);
    private final BooleanSetting showMinecarts = new BooleanSetting("Вагонетки", true);
    private final SliderSetting maxDistance = new SliderSetting("Макс. дистанция", 64.0, 16.0, 128.0, 1.0);
    private final List<LootSource> cachedSources = new ArrayList<LootSource>();
    private long lastCacheUpdate = 0L;
    private static final long CACHE_UPDATE_INTERVAL = 500L;

    @Override
    public void onDisable() {
        this.cachedSources.clear();
        super.onDisable();
    }

    @Subscribe
    public void onRender3D(EventWorldRender event) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastCacheUpdate > 500L) {
            this.updateCache();
            this.lastCacheUpdate = now;
        }
        float maxDistSq = this.maxDistance.getFloatValue() * this.maxDistance.getFloatValue();
        for (LootSource source : this.cachedSources) {
            if (this.mc.player.squaredDistanceTo((double)source.pos.getX(), (double)source.pos.getY(), (double)source.pos.getZ()) > (double)maxDistSq) {
                source.screenPos = null;
                continue;
            }
            Vector2f pos = ProjectionUtil.project((double)source.pos.getX() + 0.5, (double)source.pos.getY() + 1.5, (double)source.pos.getZ() + 0.5);
            if (pos == null || pos.getX() == Float.MAX_VALUE || pos.getY() == Float.MAX_VALUE) {
                source.screenPos = null;
                continue;
            }
            source.screenPos = pos;
        }
    }

    @Subscribe
    public void onRender2D(EventHUD event) {
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        MsdfFont font = Fonts.SFMEDIUM.get();
        if (font == null) {
            return;
        }
        MatrixStack matrices = event.getDrawContext().getMatrices();
        for (LootSource source : this.cachedSources) {
            if (source.screenPos == null) continue;
            this.drawLootTag(matrices, font, source.screenPos.getX(), source.screenPos.getY(), source);
        }
    }

    private void updateCache() {
        this.cachedSources.clear();
        if (this.showSpawners.getValue()) {
            int renderDistance = (Integer)this.mc.options.getViewDistance().getValue();
            ChunkPos playerChunk = this.mc.player.getChunkPos();
            for (int cx = -renderDistance; cx <= renderDistance; ++cx) {
                for (int cz = -renderDistance; cz <= renderDistance; ++cz) {
                    WorldChunk chunk = this.mc.world.getChunk(playerChunk.x + cx, playerChunk.z + cz);
                    if (chunk == null) continue;
                    for (BlockEntity blockEntity : chunk.getBlockEntities().values()) {
                        MobSpawnerBlockEntity spawner;
                        if (!(blockEntity instanceof MobSpawnerBlockEntity) || !this.hasSingleChestNearby((spawner = (MobSpawnerBlockEntity)blockEntity).getPos())) continue;
                        int delay = this.getSpawnerDelay(spawner);
                        boolean isLooted = delay > 0 && delay != 20 || this.isAreaExplored(spawner.getPos());
                        this.cachedSources.add(new LootSource(spawner.getPos(), LootType.SPAWNER, isLooted));
                    }
                }
            }
        }
        if (this.showMinecarts.getValue()) {
            for (Entity entity : this.mc.world.getEntities()) {
                if (!(entity instanceof ChestMinecartEntity)) continue;
                ChestMinecartEntity minecart = (ChestMinecartEntity)entity;
                boolean isLooted = this.isAreaExplored(minecart.getBlockPos());
                this.cachedSources.add(new LootSource(minecart.getBlockPos(), LootType.MINECART, isLooted));
            }
        }
    }

    private void drawLootTag(MatrixStack matrices, MsdfFont font, float x2, float y2, LootSource source) {
        String typeText = source.type == LootType.SPAWNER ? "Спавнер" : "Вагонетка";
        String statusText = source.isLooted ? " [Залутано]" : " [Не залутано]";
        float typeWidth = font.getWidth(typeText, 7.0f);
        float statusWidth = font.getWidth(statusText, 7.0f);
        float totalWidth = typeWidth + statusWidth;
        float boxWidth = totalWidth + 10.0f;
        float boxHeight = 12.5f;
        float tagX = x2 - boxWidth / 2.0f;
        float tagY = y2 - boxHeight / 2.0f;
        this.drawDefaultTagPanel(matrices, tagX, tagY, boxWidth, boxHeight);
        float textX = tagX + 5.0f;
        float textY = tagY + (boxHeight - 7.0f) / 2.0f;
        DrawUtil.drawText(font, typeText, textX, textY, -1, 7.0f);
        int statusColor = source.isLooted ? -43691 : -11141291;
        DrawUtil.drawText(font, statusText, textX += typeWidth, textY, statusColor, 7.0f);
    }

    private void drawDefaultTagPanel(MatrixStack matrices, float x2, float y2, float width, float height) {
        int themeColor = ColorProvider.getColorClient();
        int topColor = ColorProvider.rgba(50, 50, 50, 204.0f);
        int bottomColor = ColorProvider.setAlpha(ColorProvider.brighter(themeColor, 0.85f), 204);
        DrawUtil.drawRound(x2, y2, width, height, 1.1f, topColor, bottomColor);
    }

    private boolean isAreaExplored(BlockPos pos) {
        int radius = 20;
        int airCount = 0;
        int checkCount = 0;
        for (int x2 = -radius; x2 <= radius; x2 += 4) {
            for (int y2 = -radius; y2 <= radius; y2 += 4) {
                for (int z2 = -radius; z2 <= radius; z2 += 4) {
                    BlockPos checkPos = pos.add(x2, y2, z2);
                    BlockState state = this.mc.world.getBlockState(checkPos);
                    ++checkCount;
                    if (!state.isOf(Blocks.AIR) && !state.isOf(Blocks.CAVE_AIR)) continue;
                    ++airCount;
                }
            }
        }
        return (double)airCount > (double)checkCount * 0.3;
    }

    private int getSpawnerDelay(MobSpawnerBlockEntity spawner) {
        try {
            NbtCompound tag = spawner.createNbtWithIdentifyingData((RegistryWrapper.WrapperLookup)this.mc.world.getRegistryManager());
            return tag.contains("Delay") ? (int)tag.getShort("Delay") : 20;
        }
        catch (Exception e2) {
            return 20;
        }
    }

    private boolean hasSingleChestNearby(BlockPos spawnerPos) {
        int radius = 3;
        for (int x2 = -radius; x2 <= radius; ++x2) {
            for (int y2 = -radius; y2 <= radius; ++y2) {
                for (int z2 = -radius; z2 <= radius; ++z2) {
                    BlockPos checkPos = spawnerPos.add(x2, y2, z2);
                    BlockState state = this.mc.world.getBlockState(checkPos);
                    if (!(state.getBlock() instanceof ChestBlock) || state.get((Property)ChestBlock.CHEST_TYPE) != ChestType.SINGLE) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private static class LootSource {
        final BlockPos pos;
        final LootType type;
        final boolean isLooted;
        Vector2f screenPos;

        LootSource(BlockPos pos, LootType type, boolean isLooted) {
            this.pos = pos;
            this.type = type;
            this.isLooted = isLooted;
        }
    }

    private static enum LootType {
        SPAWNER,
        MINECART;

    }
}

