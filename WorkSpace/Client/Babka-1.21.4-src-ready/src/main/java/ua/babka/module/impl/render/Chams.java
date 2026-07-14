package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.client.gl.Defines;
import net.minecraft.client.gl.GlUniform;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import ua.babka.event.EventWorldRender;
import ua.babka.friend.FriendRepository;
import ua.babka.mixin.LivingEntityRendererAccessor;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;

@ModuleInformation(moduleName="Chams", moduleDesc="Подсвечивает игроков сквозь стены с волнами", moduleCategory=ModuleCategory.RENDER)
public class Chams
extends Module {
    public static final String TARGET_PLAYERS = "Игроков";
    public static final String TARGET_FRIENDS = "Друзей";
    public static final String TARGET_SELF = "Себя";
    private static final ShaderProgramKey chamsFill = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/chams/chams_fill"), VertexFormats.POSITION_TEXTURE_COLOR, Defines.EMPTY);
    private static final int DEFAULT_FILL_ALPHA = 130;
    private static final float DEFAULT_LINE_WIDTH = 0.5f;
    private static final float CLIENT_FILL_SATURATION = 1.18f;
    private static final float CLIENT_FILL_BRIGHTNESS = 1.12f;
    private static final float CLIENT_OUTLINE_SATURATION = 1.12f;
    private static final float CLIENT_OUTLINE_BRIGHTNESS = 1.08f;
    private static final float MIN_PULSE_ALPHA = 0.65f;
    private static final float PULSE_SWING = 0.35f;
    private static final int FRIEND_FILL_COLOR = new Color(85, 255, 85, 60).getRGB();
    private static final int FRIEND_OUTLINE_COLOR = new Color(100, 255, 100, 255).getRGB();
    private static final long OUTLINE_RETRY_DELAY_MS = 3000L;
    private final ModeListSetting rendering = new ModeListSetting("Отображать", new BooleanSetting("Игроков", true), new BooleanSetting("Друзей", true), new BooleanSetting("Себя", false));
    private final BooleanSetting waves = new BooleanSetting("Волны", true);
    private final SliderSetting waveSpeedX = new SliderSetting("Скорость X", 0.22, 0.0, 1.5, 0.01).setVisible(() -> this.waves.getValue());
    private final SliderSetting waveSpeedY = new SliderSetting("Скорость Y", 0.15, 0.0, 1.5, 0.01).setVisible(() -> this.waves.getValue());
    private final SliderSetting waveScale = new SliderSetting("Размер волн", 1.35, 0.2, 4.0, 0.05).setVisible(() -> this.waves.getValue());
    private final SliderSetting waveDensity = new SliderSetting("Плотность волн", 1.15, 0.5, 3.0, 0.05).setVisible(() -> this.waves.getValue());
    private final SliderSetting waveGlow = new SliderSetting("Сила волн", 1.0, 0.2, 3.0, 0.05).setVisible(() -> this.waves.getValue());
    private final BooleanSetting glow = new BooleanSetting("Свечение", true);
    private final SliderSetting glowIntensity = new SliderSetting("Сила свечения", 2.0, 1.0, 5.0, 0.1).setVisible(() -> this.glow.getValue());
    private final SliderSetting glowLayers = new SliderSetting("Слои свечения", 3.0, 1.0, 6.0, 1.0).setVisible(() -> this.glow.getValue());
    private final BooleanSetting pulse = new BooleanSetting("Пульсирование", false);
    private final SliderSetting pulseSpeed = new SliderSetting("Скорость пульсации", 2.0, 0.5, 5.0, 0.1).setVisible(() -> this.pulse.getValue());
    private final BooleanSetting hideOriginal = new BooleanSetting("Скрыть оригинал", false);
    private final BooleanSetting hideItemsAndCape = new BooleanSetting("Скрывать предметы и плащ", false);
    private final long startTime = System.currentTimeMillis();
    private boolean outlineAssistReady;
    private long nextOutlineRetryAt;
    public static boolean PREVIEW_MODE = false;

    @Override
    public void onEnable() {
        super.onEnable();
        this.outlineAssistReady = false;
        this.nextOutlineRetryAt = 0L;
        this.tryEnsureOutlineProcessor();
    }

    @Override
    public void onDisable() {
        this.outlineAssistReady = false;
        this.nextOutlineRetryAt = 0L;
        super.onDisable();
    }

    public void renderPreview(PlayerEntity player, float tickDelta, float entityX, float entityY, float scale) {
        if (!(player instanceof AbstractClientPlayerEntity)) {
            return;
        }
        AbstractClientPlayerEntity clientPlayer = (AbstractClientPlayerEntity)player;
        EntityRenderer rawRenderer = this.mc.getEntityRenderDispatcher().getRenderer((Entity)player);
        if (!(rawRenderer instanceof PlayerEntityRenderer)) {
            return;
        }
        PlayerEntityRenderer renderer = (PlayerEntityRenderer)rawRenderer;
        PlayerEntityRenderState state = renderer.createRenderState();
        renderer.updateRenderState(clientPlayer, state, tickDelta);
        PlayerEntityModel model = (PlayerEntityModel)renderer.getModel();
        model.setAngles(state);
        MatrixStack matrices = new MatrixStack();
        matrices.push();
        matrices.translate((double)entityX, (double)entityY, 50.0);
        matrices.scale(-scale, scale, scale);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0f));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-33.0f));
        float baseScale = state.baseScale;
        matrices.scale(baseScale, baseScale, baseScale);
        ((LivingEntityRendererAccessor)renderer).babka$setupTransforms((LivingEntityRenderState)state, matrices, state.bodyYaw, baseScale);
        matrices.scale(-1.0f, -1.0f, 1.0f);
        ((LivingEntityRendererAccessor)renderer).babka$scale((LivingEntityRenderState)state, matrices);
        matrices.translate(0.0f, -1.501f, 0.0f);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        int fillColor = this.resolveFillColor(player);
        int outlineColor = this.resolveOutlineColor(player);
        this.renderShaderFillModel(matrices, (BipedEntityModel<?>)model, 0.0f, fillColor);
        this.renderOutlineModel(matrices, (BipedEntityModel<?>)model, 0.0f, outlineColor);
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        matrices.pop();
    }

    @Subscribe
    public void onRenderWorld(EventWorldRender event) {
        if (!this.isEnabled() || this.mc.world == null || this.mc.player == null) {
            return;
        }
        if (this.hasOutlineAssistTargets() && !this.outlineAssistReady && System.currentTimeMillis() >= this.nextOutlineRetryAt) {
            this.tryEnsureOutlineProcessor();
        }
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        for (PlayerEntity player : this.mc.world.getPlayers()) {
            if (!this.affects(player) || player == this.mc.player && this.mc.options.getPerspective() == Perspective.FIRST_PERSON) continue;
            this.renderManualPlayer(event, player);
        }
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        RenderSystem.lineWidth((float)1.0f);
    }

    private void renderManualPlayer(EventWorldRender event, PlayerEntity player) {
        if (!(player instanceof AbstractClientPlayerEntity)) {
            return;
        }
        AbstractClientPlayerEntity clientPlayer = (AbstractClientPlayerEntity)player;
        EntityRenderer rawRenderer = this.mc.getEntityRenderDispatcher().getRenderer((Entity)player);
        if (!(rawRenderer instanceof PlayerEntityRenderer)) {
            return;
        }
        PlayerEntityRenderer renderer = (PlayerEntityRenderer)rawRenderer;
        PlayerEntityRenderState state = renderer.createRenderState();
        renderer.updateRenderState(clientPlayer, state, event.getTickDelta());
        PlayerEntityModel model = (PlayerEntityModel)renderer.getModel();
        model.setAngles(state);
        MatrixStack matrices = event.getMatrixStack();
        matrices.push();
        Vec3d cameraPos = this.mc.gameRenderer.getCamera().getPos();
        this.setupModelMatrix(matrices, state, renderer, cameraPos, player, event.getTickDelta());
        int fillColor = this.resolveFillColor(player);
        int outlineColor = this.resolveOutlineColor(player);
        this.renderShaderFillModel(matrices, (BipedEntityModel<?>)model, 0.0f, fillColor);
        this.renderOutlineModel(matrices, (BipedEntityModel<?>)model, 0.0f, outlineColor);
        matrices.pop();
    }

    private void setupModelMatrix(MatrixStack matrices, PlayerEntityRenderState state, PlayerEntityRenderer renderer, Vec3d cameraPos, PlayerEntity player, float tickDelta) {
        Vec3d pos = player.getLerpedPos(tickDelta);
        double x2 = pos.x - cameraPos.x;
        double y2 = pos.y - cameraPos.y;
        double z2 = pos.z - cameraPos.z;
        matrices.translate(x2, y2, z2);
        if (state.sleepingDirection != null) {
            float eyeOffset = state.standingEyeHeight - 0.1f;
            matrices.translate((float)(-state.sleepingDirection.getOffsetX()) * eyeOffset, 0.0f, (float)(-state.sleepingDirection.getOffsetZ()) * eyeOffset);
        }
        float baseScale = state.baseScale;
        matrices.scale(baseScale, baseScale, baseScale);
        LivingEntityRendererAccessor accessor = (LivingEntityRendererAccessor)renderer;
        accessor.babka$setupTransforms((LivingEntityRenderState)state, matrices, state.bodyYaw, baseScale);
        matrices.scale(-1.0f, -1.0f, 1.0f);
        accessor.babka$scale((LivingEntityRenderState)state, matrices);
        matrices.translate(0.0f, -1.501f, 0.0f);
    }

    private void renderShaderFillModel(MatrixStack matrices, BipedEntityModel<?> model, float expand, int color) {
        if (!this.waves.getValue()) {
            this.renderSolidFillModel(matrices, model, expand, color);
            return;
        }
        ShaderProgram shader = this.mc.getShaderLoader().getOrCreateProgram(chamsFill);
        if (shader == null) {
            return;
        }
        RenderSystem.setShader((ShaderProgramKey)chamsFill);
        this.setUniform(shader, "time", (float)(System.currentTimeMillis() - this.startTime) / 1000.0f);
        this.setUniform(shader, "speedX", this.waveSpeedX.getFloatValue());
        this.setUniform(shader, "speedY", this.waveSpeedY.getFloatValue());
        this.setUniform(shader, "scale", this.waveScale.getFloatValue());
        this.setUniform(shader, "density", this.waveDensity.getFloatValue());
        this.setUniform(shader, "glowStrength", this.waveGlow.getFloatValue());
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        ModelPart root = model.getRootPart();
        this.renderFillPart(matrices, buffer, root, model.head, -4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, expand, color);
        this.renderFillPart(matrices, buffer, root, model.body, -4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, expand, color);
        this.renderFillPart(matrices, buffer, root, model.rightArm, -3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, color);
        this.renderFillPart(matrices, buffer, root, model.leftArm, -1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, color);
        this.renderFillPart(matrices, buffer, root, model.rightLeg, -2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, color);
        this.renderFillPart(matrices, buffer, root, model.leftLeg, -2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, color);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
    }

    private void renderSolidFillModel(MatrixStack matrices, BipedEntityModel<?> model, float expand, int color) {
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        ModelPart root = model.getRootPart();
        this.renderSolidFillPart(matrices, buffer, root, model.head, -4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, expand, color);
        this.renderSolidFillPart(matrices, buffer, root, model.body, -4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, expand, color);
        this.renderSolidFillPart(matrices, buffer, root, model.rightArm, -3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, color);
        this.renderSolidFillPart(matrices, buffer, root, model.leftArm, -1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, color);
        this.renderSolidFillPart(matrices, buffer, root, model.rightLeg, -2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, color);
        this.renderSolidFillPart(matrices, buffer, root, model.leftLeg, -2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, color);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
    }

    private void renderSolidFillPart(MatrixStack baseStack, BufferBuilder buffer, ModelPart root, ModelPart part, float offX, float offY, float offZ, float width, float height, float depth, float expand, int color) {
        baseStack.push();
        root.rotate(baseStack);
        part.rotate(baseStack);
        Matrix4f matrix = baseStack.peek().getPositionMatrix();
        float scale = 0.0625f;
        float expandScale = expand * scale;
        float minX = offX * scale - expandScale;
        float minY = offY * scale - expandScale;
        float minZ = offZ * scale - expandScale;
        float maxX = (offX + width) * scale + expandScale;
        float maxY = (offY + height) * scale + expandScale;
        float maxZ = (offZ + depth) * scale + expandScale;
        this.addSolidQuad(buffer, matrix, minX, maxY, minZ, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, color);
        this.addSolidQuad(buffer, matrix, minX, minY, maxZ, minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, color);
        this.addSolidQuad(buffer, matrix, minX, minY, minZ, minX, maxY, minZ, maxX, maxY, minZ, maxX, minY, minZ, color);
        this.addSolidQuad(buffer, matrix, maxX, minY, maxZ, maxX, maxY, maxZ, minX, maxY, maxZ, minX, minY, maxZ, color);
        this.addSolidQuad(buffer, matrix, minX, minY, maxZ, minX, maxY, maxZ, minX, maxY, minZ, minX, minY, minZ, color);
        this.addSolidQuad(buffer, matrix, maxX, minY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ, maxX, minY, maxZ, color);
        baseStack.pop();
    }

    private void renderFillPart(MatrixStack baseStack, BufferBuilder buffer, ModelPart root, ModelPart part, float offX, float offY, float offZ, float width, float height, float depth, float expand, int color) {
        baseStack.push();
        root.rotate(baseStack);
        part.rotate(baseStack);
        Matrix4f matrix = baseStack.peek().getPositionMatrix();
        float scale = 0.0625f;
        float expandScale = expand * scale;
        float minX = offX * scale - expandScale;
        float minY = offY * scale - expandScale;
        float minZ = offZ * scale - expandScale;
        float maxX = (offX + width) * scale + expandScale;
        float maxY = (offY + height) * scale + expandScale;
        float maxZ = (offZ + depth) * scale + expandScale;
        this.addQuad(buffer, matrix, minX, maxY, minZ, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, color);
        this.addQuad(buffer, matrix, minX, minY, maxZ, minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, color);
        this.addQuad(buffer, matrix, minX, minY, minZ, minX, maxY, minZ, maxX, maxY, minZ, maxX, minY, minZ, color);
        this.addQuad(buffer, matrix, maxX, minY, maxZ, maxX, maxY, maxZ, minX, maxY, maxZ, minX, minY, maxZ, color);
        this.addQuad(buffer, matrix, minX, minY, maxZ, minX, maxY, maxZ, minX, maxY, minZ, minX, minY, minZ, color);
        this.addQuad(buffer, matrix, maxX, minY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ, maxX, minY, maxZ, color);
        baseStack.pop();
    }

    private void addQuad(BufferBuilder buffer, Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, int color) {
        int r2 = ColorProvider.red(color);
        int g2 = ColorProvider.green(color);
        int b2 = ColorProvider.blue(color);
        int a2 = ColorProvider.alpha(color);
        float u1 = this.waveU(x1, y1, z1);
        float v1 = this.waveV(x1, y1, z1);
        float u2 = this.waveU(x2, y2, z2);
        float v2 = this.waveV(x2, y2, z2);
        float u3 = this.waveU(x3, y3, z3);
        float v3 = this.waveV(x3, y3, z3);
        float u4 = this.waveU(x4, y4, z4);
        float v4 = this.waveV(x4, y4, z4);
        buffer.vertex(matrix, x1, y1, z1).texture(u1, v1).color(r2, g2, b2, a2);
        buffer.vertex(matrix, x2, y2, z2).texture(u2, v2).color(r2, g2, b2, a2);
        buffer.vertex(matrix, x3, y3, z3).texture(u3, v3).color(r2, g2, b2, a2);
        buffer.vertex(matrix, x4, y4, z4).texture(u4, v4).color(r2, g2, b2, a2);
    }

    private void addSolidQuad(BufferBuilder buffer, Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, int color) {
        int r2 = ColorProvider.red(color);
        int g2 = ColorProvider.green(color);
        int b2 = ColorProvider.blue(color);
        int a2 = ColorProvider.alpha(color);
        buffer.vertex(matrix, x1, y1, z1).color(r2, g2, b2, a2);
        buffer.vertex(matrix, x2, y2, z2).color(r2, g2, b2, a2);
        buffer.vertex(matrix, x3, y3, z3).color(r2, g2, b2, a2);
        buffer.vertex(matrix, x4, y4, z4).color(r2, g2, b2, a2);
    }

    private float waveU(float x2, float y2, float z2) {
        return x2 * 1.15f + z2 * 0.72f;
    }

    private float waveV(float x2, float y2, float z2) {
        return y2 * 1.05f - z2 * 0.38f + x2 * 0.18f;
    }

    private void renderOutlineModel(MatrixStack matrices, BipedEntityModel<?> model, float expand, int color) {
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        RenderSystem.lineWidth((float)0.5f);
        if (this.glow.getValue()) {
            RenderSystem.blendFuncSeparate((int)770, (int)1, (int)1, (int)0);
            int layers = Math.max(1, Math.round(this.glowLayers.getFloatValue()));
            float intensity = Math.max(1.0f, this.glowIntensity.getFloatValue());
            for (int index = layers; index >= 1; --index) {
                float layerExpand = expand + (float)index * 0.5f * intensity;
                float alphaMul = 1.0f / (float)(index + 1) * 0.7f;
                int alpha = Math.max(1, Math.min(255, Math.round((float)ColorProvider.alpha(color) * alphaMul)));
                this.drawOutlineParts(matrices, model, layerExpand, this.withAlpha(color, alpha));
            }
        }
        RenderSystem.defaultBlendFunc();
        this.drawOutlineParts(matrices, model, expand, color);
        GL11.glDisable((int)2848);
    }

    private void drawOutlineParts(MatrixStack matrices, BipedEntityModel<?> model, float expand, int color) {
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        ModelPart root = model.getRootPart();
        this.renderPartOutlineLines(matrices, buffer, root, model.head, -4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, expand, color);
        this.renderPartOutlineLines(matrices, buffer, root, model.body, -4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, expand, color);
        this.renderPartOutlineLines(matrices, buffer, root, model.rightArm, -3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, color);
        this.renderPartOutlineLines(matrices, buffer, root, model.leftArm, -1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, color);
        this.renderPartOutlineLines(matrices, buffer, root, model.rightLeg, -2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, color);
        this.renderPartOutlineLines(matrices, buffer, root, model.leftLeg, -2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, color);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
    }

    private void renderPartOutlineLines(MatrixStack baseStack, BufferBuilder buffer, ModelPart root, ModelPart part, float offX, float offY, float offZ, float width, float height, float depth, float expand, int color) {
        baseStack.push();
        root.rotate(baseStack);
        part.rotate(baseStack);
        float scale = 0.0625f;
        float expandScale = expand * scale;
        float minX = offX * scale - expandScale;
        float minY = offY * scale - expandScale;
        float minZ = offZ * scale - expandScale;
        float maxX = (offX + width) * scale + expandScale;
        float maxY = (offY + height) * scale + expandScale;
        float maxZ = (offZ + depth) * scale + expandScale;
        Matrix4f matrix = baseStack.peek().getPositionMatrix();
        this.addLine(buffer, matrix, minX, minY, minZ, maxX, minY, minZ, color);
        this.addLine(buffer, matrix, maxX, minY, minZ, maxX, minY, maxZ, color);
        this.addLine(buffer, matrix, maxX, minY, maxZ, minX, minY, maxZ, color);
        this.addLine(buffer, matrix, minX, minY, maxZ, minX, minY, minZ, color);
        this.addLine(buffer, matrix, minX, maxY, minZ, maxX, maxY, minZ, color);
        this.addLine(buffer, matrix, maxX, maxY, minZ, maxX, maxY, maxZ, color);
        this.addLine(buffer, matrix, maxX, maxY, maxZ, minX, maxY, maxZ, color);
        this.addLine(buffer, matrix, minX, maxY, maxZ, minX, maxY, minZ, color);
        this.addLine(buffer, matrix, minX, minY, minZ, minX, maxY, minZ, color);
        this.addLine(buffer, matrix, maxX, minY, minZ, maxX, maxY, minZ, color);
        this.addLine(buffer, matrix, maxX, minY, maxZ, maxX, maxY, maxZ, color);
        this.addLine(buffer, matrix, minX, minY, maxZ, minX, maxY, maxZ, color);
        baseStack.pop();
    }

    private void addLine(BufferBuilder buffer, Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2, int color) {
        int r2 = ColorProvider.red(color);
        int g2 = ColorProvider.green(color);
        int b2 = ColorProvider.blue(color);
        int a2 = ColorProvider.alpha(color);
        buffer.vertex(matrix, x1, y1, z1).color(r2, g2, b2, a2);
        buffer.vertex(matrix, x2, y2, z2).color(r2, g2, b2, a2);
    }

    private void setUniform(ShaderProgram shader, String name, float value) {
        GlUniform uniform = shader.getUniform(name);
        if (uniform != null) {
            uniform.set(value);
        }
    }

    public boolean affects(PlayerEntity player) {
        if (!this.isEnabled() || player == null || !player.isAlive()) {
            return false;
        }
        if (player == this.mc.player) {
            return this.rendering.isEnabled(TARGET_SELF) && this.mc.options.getPerspective() != Perspective.FIRST_PERSON;
        }
        if (this.isFriend(player)) {
            return this.rendering.isEnabled(TARGET_FRIENDS);
        }
        return this.rendering.isEnabled(TARGET_PLAYERS);
    }

    public boolean shouldHideBaseModel(PlayerEntity player) {
        return !PREVIEW_MODE && this.hideOriginal.getValue() && this.affects(player);
    }

    public boolean shouldHideItemsAndCape(PlayerEntity player) {
        return !PREVIEW_MODE && this.hideItemsAndCape.getValue() && this.affects(player);
    }

    public boolean shouldUseOutlineAssist(PlayerEntity player) {
        return this.affects(player);
    }

    public boolean shouldHideOutlineFramebuffer() {
        return this.isEnabled() && this.hasOutlineAssistTargets();
    }

    public int resolveFillColor(PlayerEntity player) {
        return this.applyPulse(this.baseFillColor(player));
    }

    public int resolveOutlineColor(PlayerEntity player) {
        return this.applyPulse(this.baseOutlineColor(player));
    }

    private int baseFillColor(PlayerEntity player) {
        if (this.isFriend(player)) {
            return FRIEND_FILL_COLOR;
        }
        return this.vividWithAlpha(ColorProvider.getThemeColor(), 1.18f, 1.12f, 130);
    }

    private int baseOutlineColor(PlayerEntity player) {
        if (this.isFriend(player)) {
            return FRIEND_OUTLINE_COLOR;
        }
        return this.vividWithAlpha(ColorProvider.getThemeColor(), 1.12f, 1.08f, 255);
    }

    private int applyPulse(int color) {
        if (!this.pulse.getValue()) {
            return color;
        }
        float elapsedSeconds = (float)(System.currentTimeMillis() - this.startTime) / 1000.0f;
        float pulseValue = (float)((Math.sin((double)(elapsedSeconds * this.pulseSpeed.getFloatValue()) * Math.PI) + 1.0) * 0.5);
        float alphaMul = 0.65f + 0.35f * pulseValue;
        return this.multAlpha(color, alphaMul);
    }

    private int vividWithAlpha(int color, float saturationBoost, float brightnessBoost, int alpha) {
        float[] hsb = Color.RGBtoHSB(ColorProvider.red(color), ColorProvider.green(color), ColorProvider.blue(color), null);
        float saturation = MathHelper.clamp((float)(hsb[1] * saturationBoost), (float)0.0f, (float)1.0f);
        float brightness = MathHelper.clamp((float)(Math.max(hsb[2], 0.8f) * brightnessBoost), (float)0.0f, (float)1.0f);
        int rgb = Color.HSBtoRGB(hsb[0], saturation, brightness);
        return ColorProvider.rgba(ColorProvider.red(rgb), ColorProvider.green(rgb), ColorProvider.blue(rgb), alpha);
    }

    private int withAlpha(int color, int alpha) {
        return color & 0xFFFFFF | (alpha & 0xFF) << 24;
    }

    private int multAlpha(int color, float alphaMul) {
        int a2 = Math.max(0, Math.min(255, Math.round((float)ColorProvider.alpha(color) * alphaMul)));
        return color & 0xFFFFFF | a2 << 24;
    }

    private boolean isFriend(PlayerEntity player) {
        return FriendRepository.isFriend(player.getName().getString());
    }

    private boolean hasOutlineAssistTargets() {
        if (!this.isEnabled() || this.mc.world == null || this.mc.player == null) {
            return false;
        }
        for (PlayerEntity player : this.mc.world.getPlayers()) {
            if (!this.shouldUseOutlineAssist(player)) continue;
            return true;
        }
        return false;
    }

    private boolean tryEnsureOutlineProcessor() {
        if (this.mc.worldRenderer == null) {
            this.outlineAssistReady = false;
            return false;
        }
        if (this.mc.worldRenderer.getEntityOutlinesFramebuffer() != null) {
            this.outlineAssistReady = true;
            return true;
        }
        try {
            this.mc.worldRenderer.loadEntityOutlinePostProcessor();
        }
        catch (Exception exception) {
            // empty catch block
        }
        boolean bl = this.outlineAssistReady = this.mc.worldRenderer.getEntityOutlinesFramebuffer() != null;
        if (!this.outlineAssistReady) {
            this.nextOutlineRetryAt = System.currentTimeMillis() + 3000L;
        }
        return this.outlineAssistReady;
    }
}

