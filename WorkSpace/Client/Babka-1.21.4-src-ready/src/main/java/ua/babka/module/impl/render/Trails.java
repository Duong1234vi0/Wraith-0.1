package ua.babka.module.impl.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;

@ModuleInformation(moduleName="Trails", moduleDesc="Хвост позади сущностей", moduleCategory=ModuleCategory.RENDER)
public class Trails
extends Module {
    private final SliderSetting duration = new SliderSetting("Длительность", 1000.0, 500.0, 2000.0, 100.0);
    private final SliderSetting lineWidth = new SliderSetting("Толщина", 2.0, 0.5, 5.0, 0.1f);
    private final SliderSetting opacity = new SliderSetting("Непрозрачность", 100.0, 0.0, 255.0, 5.0);
    private final BooleanSetting firstPerson = new BooleanSetting("От первого лица", false);
    private final BooleanSetting targetSelf = new BooleanSetting("Себе", true);
    private final BooleanSetting targetFriends = new BooleanSetting("На друзьях", true);
    private final BooleanSetting targetPlayers = new BooleanSetting("На игроках", true);
    private final BooleanSetting targetMobs = new BooleanSetting("На мобах", false);
    private final Map<Integer, List<Point>> entityPoints = new HashMap<Integer, List<Point>>();
    private final Map<Integer, Vec3d> lastPositions = new HashMap<Integer, Vec3d>();
    private final Map<Integer, Long> lastPointTimes = new HashMap<Integer, Long>();
    private final Map<Integer, EntityType> entityTypes = new HashMap<Integer, EntityType>();

    public Trails() {
        WorldRenderEvents.LAST.register(this::onRender);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.clearData();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.clearData();
    }

    private void clearData() {
        this.entityPoints.clear();
        this.lastPositions.clear();
        this.lastPointTimes.clear();
        this.entityTypes.clear();
    }

    private void onRender(WorldRenderContext context) {
        List<Point> points;
        if (!this.isEnabled()) {
            return;
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null || !mc.player.isAlive()) {
            if (!this.entityPoints.isEmpty()) {
                this.clearData();
            }
            return;
        }
        float tickDelta = context.tickCounter().getTickDelta(true);
        boolean selfVisible = this.firstPerson.getValue() || !mc.options.getPerspective().isFirstPerson();
        long currentTime = System.currentTimeMillis();
        for (Entity entity : mc.world.getEntities()) {
            if (entity == null || !entity.isAlive() || !(entity instanceof LivingEntity)) continue;
            boolean isTarget = false;
            EntityType entityType = null;
            if (entity == mc.player) {
                isTarget = this.targetSelf.getValue() && selfVisible;
                entityType = EntityType.SELF;
            } else if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity)entity;
                boolean isFriend = FriendRepository.isFriend(player.getNameForScoreboard());
                if (isFriend) {
                    isTarget = this.targetFriends.getValue();
                    entityType = EntityType.FRIEND;
                } else {
                    isTarget = this.targetPlayers.getValue();
                    entityType = EntityType.PLAYER;
                }
            } else if (entity instanceof MobEntity) {
                isTarget = this.targetMobs.getValue();
                entityType = EntityType.MOB;
            }
            if (!isTarget) {
                this.removeEntityData(entity.getId());
                continue;
            }
            this.entityTypes.put(entity.getId(), entityType);
            Vec3d currentPos = entity.getLerpedPos(tickDelta);
            long lastTime = this.lastPointTimes.getOrDefault(entity.getId(), 0L);
            boolean shouldAddPoint = currentTime - lastTime >= 15L;
            if (!shouldAddPoint) continue;
            Vec3d lastPos = this.lastPositions.get(entity.getId());
            if (lastPos != null) {
                double distanceMoved = lastPos.distanceTo(currentPos);
                boolean bl = shouldAddPoint = distanceMoved > 0.01 || !this.entityPoints.containsKey(entity.getId()) || this.entityPoints.get(entity.getId()).isEmpty();
            }
            if (!shouldAddPoint) continue;
            List points2 = this.entityPoints.computeIfAbsent(entity.getId(), k2 -> new ArrayList());
            points2.add(0, new Point(currentPos, currentTime));
            this.lastPointTimes.put(entity.getId(), currentTime);
            this.lastPositions.put(entity.getId(), currentPos);
        }
        Iterator<Map.Entry<Integer, List<Point>>> iterator = this.entityPoints.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Point>> entry = iterator.next();
            Entity entity = mc.world.getEntityById(entry.getKey().intValue());
            if (entity == null || !entity.isAlive()) {
                iterator.remove();
                this.removeEntityData(entry.getKey());
                continue;
            }
            points = entry.getValue();
            points.removeIf(point -> (float)(currentTime - point.timestamp) > (float)this.duration.getValue());
            if (!points.isEmpty()) continue;
            iterator.remove();
            this.removeEntityData(entry.getKey());
        }
        for (Map.Entry<Integer, List<Point>> entry : this.entityPoints.entrySet()) {
            Entity entity;
            points = entry.getValue();
            if (points.size() < 2 || (entity = mc.world.getEntityById(entry.getKey().intValue())) == null || !entity.isAlive()) continue;
            EntityType entityType = this.entityTypes.get(entry.getKey());
            this.renderTrail(points, context, currentTime, entity.getHeight(), entityType);
        }
    }

    private void renderTrail(List<Point> points, WorldRenderContext context, long currentTime, float entityHeight, EntityType entityType) {
        MatrixStack matrixStack = context.matrixStack();
        Vec3d camPos = context.camera().getPos();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        Tessellator tessellator = Tessellator.getInstance();
        matrixStack.push();
        matrixStack.translate(-camPos.x, -camPos.y, -camPos.z);
        Matrix4f matrix = matrixStack.peek().getPositionMatrix();
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
        int index = 0;
        for (Point point : points) {
            float lifeRatio = Math.min(1.0f, (float)((double)(currentTime - point.timestamp) / this.duration.getValue()));
            float alpha = (float)(this.opacity.getValue() / 255.0 * (double)(1.0f - this.easeOutQuad(lifeRatio)));
            int color = this.getColorForEntity(entityType, index);
            float r2 = (float)(color >> 16 & 0xFF) / 255.0f;
            float g2 = (float)(color >> 8 & 0xFF) / 255.0f;
            float b2 = (float)(color & 0xFF) / 255.0f;
            buffer.vertex(matrix, (float)point.pos.x, (float)point.pos.y, (float)point.pos.z).color(r2, g2, b2, alpha);
            buffer.vertex(matrix, (float)point.pos.x, (float)(point.pos.y + (double)entityHeight - 0.1), (float)point.pos.z).color(r2, g2, b2, alpha);
            ++index;
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
        RenderSystem.lineWidth((float)((float)this.lineWidth.getValue()));
        BufferBuilder bufferLineBottom = tessellator.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);
        index = 0;
        for (Point point : points) {
            float lifeRatio = Math.min(1.0f, (float)((double)(currentTime - point.timestamp) / this.duration.getValue()));
            float alpha = (float)(this.opacity.getValue() / 255.0 * (double)(1.0f - this.easeOutQuad(lifeRatio)));
            int color = this.getColorForEntity(entityType, index);
            float r3 = (float)(color >> 16 & 0xFF) / 255.0f;
            float g3 = (float)(color >> 8 & 0xFF) / 255.0f;
            float b3 = (float)(color & 0xFF) / 255.0f;
            bufferLineBottom.vertex(matrix, (float)point.pos.x, (float)point.pos.y, (float)point.pos.z).color(r3, g3, b3, alpha);
            ++index;
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)bufferLineBottom.end());
        BufferBuilder bufferBuilder = tessellator.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);
        index = 0;
        for (Point point : points) {
            float lifeRatio = Math.min(1.0f, (float)((double)(currentTime - point.timestamp) / this.duration.getValue()));
            float alpha = (float)(this.opacity.getValue() / 255.0 * (double)(1.0f - this.easeOutQuad(lifeRatio)));
            int color = this.getColorForEntity(entityType, index);
            float r4 = (float)(color >> 16 & 0xFF) / 255.0f;
            float g4 = (float)(color >> 8 & 0xFF) / 255.0f;
            float b4 = (float)(color & 0xFF) / 255.0f;
            bufferBuilder.vertex(matrix, (float)point.pos.x, (float)(point.pos.y + (double)entityHeight - 0.1), (float)point.pos.z).color(r4, g4, b4, alpha);
            ++index;
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)bufferBuilder.end());
        matrixStack.pop();
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private int getColorForEntity(EntityType entityType, int index) {
        int clientColor = ColorProvider.getColorClient();
        if (entityType == null) {
            return clientColor;
        }
        switch (entityType.ordinal()) {
            case 2: {
                return new Color(255, 85, 85).getRGB();
            }
            case 3: {
                return new Color(255, 170, 0).getRGB();
            }
        }
        return clientColor;
    }

    private float easeOutQuad(float x2) {
        return 1.0f - (1.0f - x2) * (1.0f - x2);
    }

    private void removeEntityData(int entityId) {
        this.lastPositions.remove(entityId);
        this.lastPointTimes.remove(entityId);
        this.entityTypes.remove(entityId);
    }

    private static enum EntityType {
        SELF,
        FRIEND,
        PLAYER,
        MOB;

    }

    public static class Point {
        public final Vec3d pos;
        public final long timestamp;

        public Point(Vec3d pos, long timestamp) {
            this.pos = pos;
            this.timestamp = timestamp;
        }
    }
}

