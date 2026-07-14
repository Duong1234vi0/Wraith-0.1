package ua.babka.module.impl.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import ua.babka.Babka;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.combat.AttackAura;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;
import ua.babka.util.math.MathUtil;

@ModuleInformation(moduleName="Target ESP", moduleDesc="Подсветка текущей цели", moduleCategory=ModuleCategory.RENDER)
public class TargetESP
extends Module {
    private static final Identifier GLOW_TEXTURE = Identifier.of((String)"mre", (String)"images/bloom.png");
    private static final Identifier BLOOM_TEXTURE = Identifier.of((String)"mre", (String)"images/bloom.png");
    private static final Identifier SKULL_0 = Identifier.of((String)"mre", (String)"images/targetesp/skull_state_0.png");
    private static final Identifier SKULL_1 = Identifier.of((String)"mre", (String)"images/targetesp/skull_state_1.png");
    private static final Identifier SKULL_2 = Identifier.of((String)"mre", (String)"images/targetesp/skull_state_2.png");
    private static final Identifier SKULL_GLOW = Identifier.of((String)"mre", (String)"images/targetesp/glow.png");
    private static final float[] SCALE_CACHE = new float[101];
    private static final File CUSTOM_DIR;
    private static final Map<String, Integer> customTextures;
    private static List<String> cachedFilenames;
    private final ModeSetting mode = new ModeSetting("Режим", "Crystals", "Crystals", "Crystals 1", "Cubes", "Circle", "Ghosts2", "Celestial", "Skull", "Soul 2", "Soul Test 3", "Soul Test", "Crystal 3DD", "Души", "Души 2");
    private final SliderSetting customSpeed = new SliderSetting("Скорость вращения", 0.5, 2.0, 5.0, 0.1f).setVisible(() -> this.mode.getValue().startsWith("Custom:"));
    private final SliderSetting CircleSpeed = new SliderSetting("Скорость кольца", 0.01f, 0.05f, 0.3f, 0.01f).setVisible(() -> this.mode.is("Circle"));
    private final BooleanSetting CircleRainbow = new BooleanSetting("Радуга кольца", true).setVisible(() -> this.mode.is("Circle"));
    private final BooleanSetting CircleDamageRed = new BooleanSetting("Красный при уроне", true).setVisible(() -> this.mode.is("Circle"));
    private final BooleanSetting CircleThroughWalls = new BooleanSetting("Через стены", false).setVisible(() -> this.mode.is("Circle"));
    private final SliderSetting soulsSpeed = new SliderSetting("Скорость душ", 1.0, 5.0, 20.0, 0.5).setVisible(() -> this.mode.is("Души"));
    private final Animation animation = new Animation(Easing.EXPO_OUT, 500L);
    private Entity lastTarget = null;
    private boolean registered = false;
    private long lastCircleUpdateTime = 0L;
    private double CircleStep = 0.0;
    private float CircleAnimProgress = 0.0f;
    private long lastDamageTime = 0L;
    private float damageFlashIntensity = 0.0f;
    private static final long DAMAGE_FLASH_DURATION = 500L;
    private final ArrayList<Particle> particles = new ArrayList();
    private static final int PARTICLES_PER_SPAWN = 1;
    private static final float SPAWN_INTERVAL = 0.017f;
    private float spawnAccumulator = 0.0f;
    private long lastCubeTime = 0L;
    private final Animation crystalsAnim = new Animation(Easing.EXPO_OUT, 350L);
    private Entity crystalsLastTarget = null;
    private final Animation animation2 = new Animation(Easing.CUBIC_OUT, 250L);
    private long javelinTimestamp4 = System.currentTimeMillis();
    private long javelinTimestamp5 = System.nanoTime();
    private float javelinValue23 = 0.0f;
    private static final int ORBIT_PARTICLE_COUNT = 3;
    private static final float ORBIT_BASE_RADIUS = 0.4f;
    private static final float ORBIT_BASE_MUL = 0.1f;
    private static final float ORBIT_SPEED = 15.0f;
    private static final int ORBIT_TRAIL_LENGTH = 40;
    private final Vec3d[] orbitPositions = new Vec3d[3];
    private final Vec3d[] orbitMotions = new Vec3d[3];
    private final List<Vec3d>[] orbitTrails = new ArrayList[3];
    private float movingAngle = 0.0f;
    private long lastOrbitTime = 0L;
    private final Animation orbitShrinkAnim = new Animation(Easing.CUBIC_OUT, 300L);
    private float crystalMoving = 0.0f;
    private final LinkedList<Vec3d>[] ghostTrails = new LinkedList[]{new LinkedList(), new LinkedList(), new LinkedList(), new LinkedList(), new LinkedList()};
    private final Animation CelestialAnimation = new Animation(Easing.EXPO_OUT, 400L);
    private final Animation skullAnimation = new Animation(Easing.EXPO_OUT, 450L);
    private Entity skullLastTarget = null;
    private final Animation hurtAnimation = new Animation(Easing.EXPO_OUT, 200L);
    private float chainRotationAngle = 0.0f;
    private float prevChainRotationAngle = 0.0f;
    private final Animation soulsAnim = new Animation(Easing.EXPO_OUT, 400L);
    private float soulsPlavnost = 0.0f;
    private Entity soulsLastTarget = null;
    private final Animation souls2Anim = new Animation(Easing.EXPO_OUT, 400L);
    private Entity souls2LastTarget = null;
    private final WorldRenderEvents.Last listener = context -> this.onRenderWorldLast(context.matrixStack(), context.camera(), context.tickCounter().getTickDelta(true));

    @Override
    public void onEnable() {
        if (!this.registered) {
            WorldRenderEvents.LAST.register((Object)this.listener);
            this.registered = true;
        }
        TargetESP.refreshCustomModes(this.mode);
        for (int i2 = 0; i2 < 3; ++i2) {
            if (this.orbitTrails[i2] != null) continue;
            this.orbitTrails[i2] = new ArrayList<Vec3d>();
        }
        super.onEnable();
    }

    private void onRenderWorldLast(MatrixStack matrices, Camera camera, float tickDelta) {
        block86: {
            String modeVal;
            float partialTicks;
            block84: {
                String modeVal2;
                block85: {
                    if (!this.isEnabled()) {
                        return;
                    }
                    LivingEntity target = Babka.getInstance().getModuleStorage().get(AttackAura.class).getTarget();
                    if (target != null && target != this.mc.player && !(target instanceof ArmorStandEntity)) {
                        if (this.lastTarget != target) {
                            for (int i2 = 0; i2 < 3; ++i2) {
                                this.orbitPositions[i2] = null;
                                this.orbitMotions[i2] = Vec3d.ZERO;
                                if (this.orbitTrails[i2] == null) continue;
                                this.orbitTrails[i2].clear();
                            }
                        }
                        this.lastTarget = target;
                        this.animation.run(1.0f);
                        this.animation2.run(1.0f);
                        this.CelestialAnimation.run(1.0f);
                        this.skullAnimation.run(1.0f);
                    } else {
                        this.animation.run(0.0f);
                        this.animation2.run(0.0f);
                        this.CelestialAnimation.run(0.0f);
                        this.skullAnimation.run(0.0f);
                        if (this.animation.getValue() == 0.0f) {
                            this.lastTarget = null;
                        }
                    }
                    partialTicks = tickDelta;
                    if (target != null && this.lastTarget == target && target instanceof LivingEntity) {
                        LivingEntity living = target;
                        this.hurtAnimation.run(living.hurtTime > 0 ? 1.0f : 0.0f);
                    } else {
                        this.hurtAnimation.run(0.0f);
                    }
                    if (target == null || this.lastTarget == null) break block84;
                    modeVal2 = this.mode.getValue();
                    if (!modeVal2.startsWith("Custom:")) break block85;
                    this.skullLastTarget = this.lastTarget;
                    this.skullAnimation.run(1.0f);
                    float customAnim = this.skullAnimation.getValue();
                    if (customAnim > 0.001f) {
                        this.renderCustom(matrices, camera, (LivingEntity)this.skullLastTarget, customAnim, partialTicks);
                    }
                    break block86;
                }
                switch (modeVal2) {
                    case "Crystals": {
                        this.crystalsLastTarget = this.lastTarget;
                        this.crystalsAnim.run(1.0f);
                        float anim = this.crystalsAnim.getValue();
                        if (anim > 0.001f) {
                            this.renderCrystals(matrices, camera, (LivingEntity)this.crystalsLastTarget, false, anim, partialTicks);
                            break;
                        }
                        break block86;
                    }
                    case "Crystals 1": {
                        this.crystalsLastTarget = this.lastTarget;
                        this.crystalsAnim.run(1.0f);
                        float anim = this.crystalsAnim.getValue();
                        if (anim > 0.001f) {
                            this.renderCrystals(matrices, camera, (LivingEntity)this.crystalsLastTarget, true, anim, partialTicks);
                            break;
                        }
                        break block86;
                    }
                    case "Cubes": {
                        this.renderCubes(matrices, camera, (LivingEntity)this.lastTarget, partialTicks);
                        break;
                    }
                    case "Circle": {
                        this.CircleAnimProgress = Math.min(1.0f, this.CircleAnimProgress + 0.1f);
                        Entity entity = this.lastTarget;
                        if (entity instanceof LivingEntity) {
                            LivingEntity living = (LivingEntity)entity;
                            if (living.hurtTime > 0) {
                                this.lastDamageTime = System.currentTimeMillis();
                            }
                        }
                        long now = System.currentTimeMillis();
                        if (this.lastCircleUpdateTime == 0L) {
                            this.lastCircleUpdateTime = now;
                        }
                        float dt = Math.min((float)(now - this.lastCircleUpdateTime) / 1000.0f, 0.1f);
                        this.lastCircleUpdateTime = now;
                        this.CircleStep += (double)(this.CircleSpeed.getFloatValue() * dt) * 60.0;
                        this.renderCircle(matrices, camera, (LivingEntity)this.lastTarget, partialTicks, this.CircleAnimProgress);
                        break;
                    }
                    case "Ghosts2": {
                        this.drawGhosts2(matrices, camera, partialTicks);
                        break;
                    }
                    case "Celestial": {
                        this.renderCelestial(matrices, camera, partialTicks);
                        break;
                    }
                    case "Skull": {
                        this.skullLastTarget = this.lastTarget;
                        this.skullAnimation.run(1.0f);
                        float skullAnim = this.skullAnimation.getValue();
                        if (skullAnim > 0.001f) {
                            this.renderSkull(matrices, camera, (LivingEntity)this.skullLastTarget, skullAnim, partialTicks);
                            break;
                        }
                        break block86;
                    }
                    case "Soul 2": {
                        this.renderNursultan(matrices, camera, partialTicks);
                        break;
                    }
                    case "Soul Test 3": {
                        this.drawCircle(matrices, camera, partialTicks);
                        break;
                    }
                    case "Soul Test": {
                        this.drawGhostOrbits(matrices, camera, partialTicks);
                        break;
                    }
                    case "Crystal 3DD": {
                        this.renderJavelinCrystals(matrices, camera, partialTicks);
                        break;
                    }
                    case "Души": {
                        this.soulsLastTarget = this.lastTarget;
                        this.soulsAnim.run(1.0f);
                        float sAnim = this.soulsAnim.getValue();
                        if (sAnim > 0.001f) {
                            this.renderSouls(matrices, camera, (LivingEntity)this.soulsLastTarget, sAnim, partialTicks);
                            break;
                        }
                        break block86;
                    }
                    case "Души 2": {
                        this.souls2LastTarget = this.lastTarget;
                        this.souls2Anim.run(1.0f);
                        float s2Anim = this.souls2Anim.getValue();
                        if (!(s2Anim > 0.001f)) break;
                        this.renderSouls2(matrices, camera, (LivingEntity)this.souls2LastTarget, s2Anim, partialTicks);
                    }
                }
                break block86;
            }
            this.lastCubeTime = 0L;
            this.spawnAccumulator = 0.0f;
            float anim2Val = this.animation2.getValue();
            if (anim2Val > 0.001f && this.lastTarget != null) {
                String jvMode;
                switch (jvMode = this.mode.getValue()) {
                    case "Soul 2": {
                        this.renderNursultan(matrices, camera, partialTicks);
                        break;
                    }
                    case "Soul Test 3": {
                        this.drawCircle(matrices, camera, partialTicks);
                        break;
                    }
                    case "Soul Test": {
                        this.drawGhostOrbits(matrices, camera, partialTicks);
                        break;
                    }
                    case "Crystal 3DD": {
                        this.renderJavelinCrystals(matrices, camera, partialTicks);
                    }
                }
            }
            if (this.mode.is("Crystals") || this.mode.is("Crystals 1")) {
                this.crystalsAnim.run(0.0f);
                float anim = this.crystalsAnim.getValue();
                if (anim > 0.001f && this.crystalsLastTarget != null) {
                    this.renderCrystals(matrices, camera, (LivingEntity)this.crystalsLastTarget, this.mode.is("Crystals 1"), anim, partialTicks);
                } else if (anim <= 0.001f) {
                    this.crystalsLastTarget = null;
                }
            }
            if (this.mode.is("Circle")) {
                this.CircleAnimProgress = Math.max(0.0f, this.CircleAnimProgress - 0.05f);
                if (this.CircleAnimProgress > 0.0f && this.lastTarget != null) {
                    long now = System.currentTimeMillis();
                    if (this.lastCircleUpdateTime == 0L) {
                        this.lastCircleUpdateTime = now;
                    }
                    float dt = Math.min((float)(now - this.lastCircleUpdateTime) / 1000.0f, 0.1f);
                    this.lastCircleUpdateTime = now;
                    this.CircleStep += (double)(this.CircleSpeed.getFloatValue() * dt) * 60.0;
                    this.renderCircle(matrices, camera, (LivingEntity)this.lastTarget, partialTicks, this.CircleAnimProgress);
                }
            } else {
                this.lastCircleUpdateTime = 0L;
            }
            if (this.mode.is("Cubes") && !this.particles.isEmpty()) {
                this.renderCubes(matrices, camera, null, partialTicks);
            }
            if (this.mode.is("Ghosts2")) {
                for (LinkedList<Vec3d> trail : this.ghostTrails) {
                    trail.clear();
                }
            }
            if ((modeVal = this.mode.getValue()).equals("Skull") || modeVal.startsWith("Custom:")) {
                this.skullAnimation.run(0.0f);
                float skullAnim = this.skullAnimation.getValue();
                if (skullAnim > 0.001f && this.skullLastTarget != null) {
                    if (modeVal.equals("Skull")) {
                        this.renderSkull(matrices, camera, (LivingEntity)this.skullLastTarget, skullAnim, partialTicks);
                    } else {
                        this.renderCustom(matrices, camera, (LivingEntity)this.skullLastTarget, skullAnim, partialTicks);
                    }
                } else if (skullAnim <= 0.001f) {
                    this.skullLastTarget = null;
                }
            }
            if (this.mode.is("Души")) {
                this.soulsAnim.run(0.0f);
                float sAnim = this.soulsAnim.getValue();
                if (sAnim > 0.001f && this.soulsLastTarget != null) {
                    this.renderSouls(matrices, camera, (LivingEntity)this.soulsLastTarget, sAnim, partialTicks);
                } else if (sAnim <= 0.001f) {
                    this.soulsLastTarget = null;
                }
            }
            if (this.mode.is("Души 2")) {
                this.souls2Anim.run(0.0f);
                float s2Anim = this.souls2Anim.getValue();
                if (s2Anim > 0.001f && this.souls2LastTarget != null) {
                    this.renderSouls2(matrices, camera, (LivingEntity)this.souls2LastTarget, s2Anim, partialTicks);
                } else if (s2Anim <= 0.001f) {
                    this.souls2LastTarget = null;
                }
            }
        }
    }

    private void renderSkull(MatrixStack matrices, Camera camera, LivingEntity target, float alphaPC, float partialTicks) {
        float skullB;
        float skullG;
        float skullR;
        int hurtTicks;
        boolean isHurt;
        float hurtShake;
        float maxHP;
        if (target == null) {
            return;
        }
        Vec3d camPos = camera.getPos();
        double tx = MathHelper.lerp((double)partialTicks, (double)target.lastRenderX, (double)target.getX()) - camPos.x;
        double ty = MathHelper.lerp((double)partialTicks, (double)target.lastRenderY, (double)target.getY()) - camPos.y + (double)(target.getHeight() / 2.0f);
        double tz = MathHelper.lerp((double)partialTicks, (double)target.lastRenderZ, (double)target.getZ()) - camPos.z;
        float currentHP = target.getHealth();
        float hpPercent = MathHelper.clamp((float)(currentHP / (maxHP = target.getMaxHealth())), (float)0.0f, (float)1.0f);
        float lowHpShake = (1.0f - hpPercent) * 0.02f;
        float totalShake = lowHpShake + (hurtShake = (isHurt = (hurtTicks = target.hurtTime) > 0) ? (float)hurtTicks / 8.0f * 0.04f : 0.0f);
        if (totalShake > 0.001f) {
            tx += (Math.random() - 0.5) * (double)totalShake;
            ty += (Math.random() - 0.5) * (double)totalShake;
            tz += (Math.random() - 0.5) * (double)totalShake;
        }
        Identifier skullTexture = hpPercent > 0.6f ? SKULL_0 : (hpPercent > 0.3f ? SKULL_1 : SKULL_2);
        int baseColor = ColorProvider.getColorClient();
        float baseR = ColorProvider.red(baseColor);
        float baseG = ColorProvider.green(baseColor);
        float baseB = ColorProvider.blue(baseColor);
        if (isHurt) {
            float hurtPC = (float)Math.abs(Math.sin((double)hurtTicks * 0.3141592653589793));
            skullR = MathHelper.lerp((float)hurtPC, (float)(baseR / 255.0f), (float)1.0f);
            skullG = MathHelper.lerp((float)hurtPC, (float)(baseG / 255.0f), (float)0.19607843f);
            skullB = MathHelper.lerp((float)hurtPC, (float)(baseB / 255.0f), (float)0.19607843f);
        } else if (hpPercent < 0.3f) {
            float mixPC = (1.0f - hpPercent / 0.3f) * 0.4f;
            skullR = MathHelper.lerp((float)mixPC, (float)(baseR / 255.0f), (float)1.0f);
            skullG = MathHelper.lerp((float)mixPC, (float)(baseG / 255.0f), (float)0.19607843f);
            skullB = MathHelper.lerp((float)mixPC, (float)(baseB / 255.0f), (float)0.19607843f);
        } else {
            skullR = baseR / 255.0f;
            skullG = baseG / 255.0f;
            skullB = baseB / 255.0f;
        }
        int skullAlpha = (int)(alphaPC * 255.0f);
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        matrices.push();
        matrices.translate(tx, ty, tz);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camera.getYaw()));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0f));
        float skullSize = 0.6f;
        RenderSystem.setShaderTexture((int)0, (Identifier)skullTexture);
        BufferBuilder skullBuf = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        Matrix4f mat = matrices.peek().getPositionMatrix();
        int sr = (int)(skullR * 255.0f);
        int sg = (int)(skullG * 255.0f);
        int sb = (int)(skullB * 255.0f);
        int sa = skullAlpha;
        skullBuf.vertex(mat, -skullSize, -skullSize, 0.0f).texture(0.0f, 0.0f).color(sr, sg, sb, sa);
        skullBuf.vertex(mat, skullSize, -skullSize, 0.0f).texture(1.0f, 0.0f).color(sr, sg, sb, sa);
        skullBuf.vertex(mat, skullSize, skullSize, 0.0f).texture(1.0f, 1.0f).color(sr, sg, sb, sa);
        skullBuf.vertex(mat, -skullSize, skullSize, 0.0f).texture(0.0f, 1.0f).color(sr, sg, sb, sa);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)skullBuf.end());
        float glowSize = skullSize * (1.5f + (1.0f - hpPercent) * 0.5f);
        float glowAlphaF = alphaPC * (100.0f + (1.0f - hpPercent) * 50.0f) / 255.0f;
        int ga = Math.max(0, Math.min(255, (int)(glowAlphaF * 255.0f)));
        RenderSystem.setShaderTexture((int)0, (Identifier)SKULL_GLOW);
        BufferBuilder glowBuf = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        glowBuf.vertex(mat, -glowSize, -glowSize, 0.0f).texture(0.0f, 0.0f).color(sr, sg, sb, ga);
        glowBuf.vertex(mat, glowSize, -glowSize, 0.0f).texture(1.0f, 0.0f).color(sr, sg, sb, ga);
        glowBuf.vertex(mat, glowSize, glowSize, 0.0f).texture(1.0f, 1.0f).color(sr, sg, sb, ga);
        glowBuf.vertex(mat, -glowSize, glowSize, 0.0f).texture(0.0f, 1.0f).color(sr, sg, sb, ga);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)glowBuf.end());
        matrices.pop();
        this.restoreRenderState();
    }

    private void renderCelestial(MatrixStack matrices, Camera camera, float partialTicks) {
        float aPC = this.CelestialAnimation.getValue();
        if (aPC <= 0.01f || this.lastTarget == null || !(this.lastTarget instanceof LivingEntity)) {
            return;
        }
        LivingEntity target = (LivingEntity)this.lastTarget;
        Vec3d camPos = camera.getPos();
        double bx = MathHelper.lerp((double)partialTicks, (double)target.lastRenderX, (double)target.getX()) - camPos.x;
        double by = MathHelper.lerp((double)partialTicks, (double)target.lastRenderY, (double)target.getY()) - camPos.y;
        double bz = MathHelper.lerp((double)partialTicks, (double)target.lastRenderZ, (double)target.getZ()) - camPos.z;
        double t2 = (double)System.currentTimeMillis() / 250.0;
        double tv = (double)System.currentTimeMillis() / 400.0;
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)BLOOM_TEXTURE);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        float radius = 0.65f;
        for (int k2 = 0; k2 < 4; ++k2) {
            for (int j2 = 0; j2 < 20; ++j2) {
                float kf = (float)j2 / 20.0f;
                float ease = 1.0f - kf * 0.6f;
                double tj = t2 - (double)j2 * 0.06;
                double tvj = tv - (double)j2 * 0.06;
                double cyc = (Math.sin(tvj) + 1.0) * 0.5;
                double baseAngle = Math.toRadians((double)k2 * 90.0 + tj * 55.0 % 360.0);
                double offX = Math.cos(baseAngle) * (double)radius;
                double offZ = Math.sin(baseAngle) * (double)radius;
                double offY = k2 % 2 == 0 ? 0.15 + 1.65 * cyc : 1.75 - 1.65 * cyc;
                float dynSize = 0.9f * ease;
                matrices.push();
                matrices.translate(bx + offX, by + offY, bz + offZ);
                matrices.scale(aPC * dynSize * 0.8f, aPC * dynSize * 0.8f, aPC * dynSize * 0.8f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camera.getYaw()));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
                Matrix4f mat = matrices.peek().getPositionMatrix();
                int color = ColorProvider.getColorClient();
                int r2 = ColorProvider.red(color);
                int g2 = ColorProvider.green(color);
                int b2 = ColorProvider.blue(color);
                int alpha = (int)(255.0f * aPC);
                buffer.vertex(mat, -0.5f, 0.5f, 0.0f).texture(0.0f, 1.0f).color(r2, g2, b2, alpha);
                buffer.vertex(mat, 0.5f, 0.5f, 0.0f).texture(1.0f, 1.0f).color(r2, g2, b2, alpha);
                buffer.vertex(mat, 0.5f, -0.5f, 0.0f).texture(1.0f, 0.0f).color(r2, g2, b2, alpha);
                buffer.vertex(mat, -0.5f, -0.5f, 0.0f).texture(0.0f, 0.0f).color(r2, g2, b2, alpha);
                matrices.pop();
            }
            radius *= -1.0f;
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
        this.restoreRenderState();
    }

    private void drawGhosts2(MatrixStack matrices, Camera camera, float tickDelta) {
        float tProgress = this.animation.getValue();
        if (tProgress <= 0.01f) {
            for (LinkedList<Vec3d> trail : this.ghostTrails) {
                trail.clear();
            }
            return;
        }
        double targetX = this.interpolate(this.lastTarget.getX(), this.lastTarget.lastRenderX, tickDelta);
        double targetY = this.interpolate(this.lastTarget.getY(), this.lastTarget.lastRenderY, tickDelta);
        double targetZ = this.interpolate(this.lastTarget.getZ(), this.lastTarget.lastRenderZ, tickDelta);
        Vec3d camPos = camera.getPos();
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)BLOOM_TEXTURE);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        int c1 = ColorProvider.getColorClient();
        long timeMs = System.currentTimeMillis();
        for (int i2 = 0; i2 < 5; ++i2) {
            double speed = (double)timeMs * 0.005;
            double offset = (double)i2 * 1.2566370614359172;
            double radius = (double)this.lastTarget.getWidth() + 0.15;
            double tx = Math.sin(speed + offset) * radius;
            double tz = Math.cos(speed + offset) * radius;
            double verticalDir = i2 % 2 == 0 ? 1.0 : -1.0;
            double yOscillation = Math.sin((speed + offset) * 0.8) * verticalDir;
            double ty = yOscillation * ((double)this.lastTarget.getHeight() * 0.55) + (double)this.lastTarget.getHeight() * 0.5;
            Vec3d relativePos = new Vec3d(tx, ty, tz);
            this.ghostTrails[i2].addFirst(relativePos);
            if (this.ghostTrails[i2].size() > 35) {
                this.ghostTrails[i2].removeLast();
            }
            matrices.push();
            int segmentIdx = 0;
            for (Vec3d relPos : this.ghostTrails[i2]) {
                float fade = 1.0f - (float)segmentIdx / (float)this.ghostTrails[i2].size();
                float renderX = (float)(targetX + relPos.x - camPos.x);
                float renderY = (float)(targetY + relPos.y - camPos.y);
                float renderZ = (float)(targetZ + relPos.z - camPos.z);
                float baseScale = segmentIdx == 0 ? 0.34f : 0.21f;
                float trailScale = baseScale * fade * tProgress;
                int alpha = (int)(255.0 * Math.pow(fade, 1.8) * (double)tProgress);
                BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
                this.drawQuad(buffer, matrices, camera, renderX, renderY, renderZ, trailScale, ColorProvider.setAlpha(c1, alpha));
                BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
                if (segmentIdx == 0) {
                    BufferBuilder coreBuffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
                    float coreScale = 0.2f * tProgress;
                    this.drawQuad(coreBuffer, matrices, camera, renderX, renderY, renderZ, coreScale, ColorProvider.setAlpha(-1, (int)(255.0f * tProgress)));
                    BufferRenderer.drawWithGlobalProgram((BuiltBuffer)coreBuffer.end());
                }
                ++segmentIdx;
            }
            matrices.pop();
        }
        this.restoreRenderState();
    }

    private double interpolate(double current, double prev, float delta) {
        return prev + (current - prev) * (double)delta;
    }

    private void drawQuad(BufferBuilder buffer, MatrixStack ms, Camera camera, float x2, float y2, float z2, float scale, int color) {
        ms.push();
        ms.translate(x2, y2, z2);
        ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camera.getYaw()));
        ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
        ms.scale(scale, scale, scale);
        Matrix4f m2 = ms.peek().getPositionMatrix();
        int r2 = ColorProvider.red(color);
        int g2 = ColorProvider.green(color);
        int b2 = ColorProvider.blue(color);
        int a2 = ColorProvider.alpha(color);
        buffer.vertex(m2, -0.5f, 0.5f, 0.0f).texture(0.0f, 1.0f).color(r2, g2, b2, a2);
        buffer.vertex(m2, 0.5f, 0.5f, 0.0f).texture(1.0f, 1.0f).color(r2, g2, b2, a2);
        buffer.vertex(m2, 0.5f, -0.5f, 0.0f).texture(1.0f, 0.0f).color(r2, g2, b2, a2);
        buffer.vertex(m2, -0.5f, -0.5f, 0.0f).texture(0.0f, 0.0f).color(r2, g2, b2, a2);
        ms.pop();
    }

    private void renderCircle(MatrixStack matrices, Camera camera, LivingEntity target, float partialTicks, float animProgress) {
        boolean canSee;
        if (animProgress <= 0.0f || target == null) {
            return;
        }
        Vec3d camPos = camera.getPos();
        float camYaw = camera.getYaw();
        float camPitch = camera.getPitch();
        double tx = MathHelper.lerp((double)partialTicks, (double)target.lastRenderX, (double)target.getX());
        double ty = MathHelper.lerp((double)partialTicks, (double)target.lastRenderY, (double)target.getY());
        double tz = MathHelper.lerp((double)partialTicks, (double)target.lastRenderZ, (double)target.getZ());
        double rx = tx - camPos.x;
        double ry = ty - camPos.y;
        double rz = tz - camPos.z;
        float entityWidth = target.getWidth() * 0.9f;
        float entityHeight = target.getHeight();
        float eased = TargetESP.easeOutCubic(animProgress);
        boolean bl = canSee = this.mc.player != null && this.mc.player.canSee((Entity)target);
        if (!this.CircleThroughWalls.getValue() && !canSee) {
            return;
        }
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)GLOW_TEXTURE);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        BufferBuilder builder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        boolean hasCircle = false;
        double golovkaY = this.absSin(this.CircleStep) * (double)entityHeight;
        float spriteSize = 0.12f;
        int totalPoints = 72;
        long now = System.currentTimeMillis();
        for (int i2 = 0; i2 < totalPoints; ++i2) {
            double angle = Math.PI * 2 * (double)i2 / (double)totalPoints;
            float xOff = (float)(Math.cos(angle) * (double)entityWidth);
            float zOff = (float)(Math.sin(angle) * (double)entityWidth);
            int baseColor = this.getCircleColor(i2 * (360 / totalPoints), now);
            float cr = (float)ColorProvider.red(baseColor) / 255.0f;
            float cg = (float)ColorProvider.green(baseColor) / 255.0f;
            float cb = (float)ColorProvider.blue(baseColor) / 255.0f;
            float ss = eased * spriteSize;
            if (ss < 0.01f) continue;
            this.putBloomQuad(builder, matrices, rx + (double)xOff, ry + golovkaY, rz + (double)zOff, ss * 4.0f, cr, cg, cb, eased * 0.15f, camYaw, camPitch);
            this.putBloomQuad(builder, matrices, rx + (double)xOff, ry + golovkaY, rz + (double)zOff, ss * 2.0f, cr, cg, cb, eased * 0.35f, camYaw, camPitch);
            this.putBloomQuad(builder, matrices, rx + (double)xOff, ry + golovkaY, rz + (double)zOff, ss * 0.8f, Math.min(1.0f, cr * 1.5f), Math.min(1.0f, cg * 1.5f), Math.min(1.0f, cb * 1.5f), eased, camYaw, camPitch);
            hasCircle = true;
        }
        if (hasCircle) {
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)builder.end());
        }
        this.restoreRenderState();
    }

    private double absSin(double input) {
        return Math.abs(1.0 + Math.sin(input)) / 2.0;
    }

    private int getCircleColor(int offsetAngle, long now) {
        int color;
        if (this.CircleRainbow.getValue()) {
            float timeFactor = (float)(now % 3000L) / 3000.0f;
            int hue = (int)(timeFactor * 360.0f) + offsetAngle;
            color = Color.HSBtoRGB((float)(hue % 360) / 360.0f, 0.7f, 1.0f);
            color = 0xFF000000 | color & 0xFFFFFF;
        } else {
            color = ColorProvider.getColorClient();
            if (ColorProvider.alpha(color) == 0) {
                color = 0xFF000000 | color & 0xFFFFFF;
            }
        }
        return this.applyDamageFlash(color);
    }

    private int applyDamageFlash(int color) {
        if (!this.CircleDamageRed.getValue()) {
            return color;
        }
        long timeSince = System.currentTimeMillis() - this.lastDamageTime;
        float target = 0.0f;
        if (timeSince < 500L) {
            target = 1.0f - TargetESP.easeOutCubic((float)timeSince / 500.0f);
        }
        this.damageFlashIntensity = MathHelper.lerp((float)0.1f, (float)this.damageFlashIntensity, (float)target);
        if (this.damageFlashIntensity < 0.05f) {
            return color;
        }
        int a2 = ColorProvider.alpha(color);
        int r2 = ColorProvider.red(color);
        int g2 = ColorProvider.green(color);
        int b2 = ColorProvider.blue(color);
        return a2 << 24 | MathHelper.lerp((float)this.damageFlashIntensity, (int)r2, (int)255) << 16 | MathHelper.lerp((float)this.damageFlashIntensity, (int)g2, (int)50) << 8 | MathHelper.lerp((float)this.damageFlashIntensity, (int)b2, (int)50);
    }

    private void renderCrystals(MatrixStack matrices, Camera camera, LivingEntity target, boolean sharp, float anim, float partialTicks) {
        if (target == null || this.mc.player == null) {
            return;
        }
        float eased = TargetESP.easeOutCubic(anim);
        float time = ((float)this.mc.player.age + partialTicks) * 6.0f;
        Vec3d camPos = camera.getPos();
        double tx = MathHelper.lerp((double)partialTicks, (double)target.lastRenderX, (double)target.getX());
        double ty = MathHelper.lerp((double)partialTicks, (double)target.lastRenderY, (double)target.getY());
        double tz = MathHelper.lerp((double)partialTicks, (double)target.lastRenderZ, (double)target.getZ());
        float camYaw = camera.getYaw();
        float camPitch = camera.getPitch();
        float entityHeight = target.getHeight();
        float halfWidth = target.getWidth() * 0.5f;
        int baseColor = ColorProvider.getColorClient();
        float r2 = Math.min(1.0f, (float)ColorProvider.red(baseColor) / 255.0f * 1.3f);
        float g2 = Math.min(1.0f, (float)ColorProvider.green(baseColor) / 255.0f * 1.3f);
        float b2 = Math.min(1.0f, (float)ColorProvider.blue(baseColor) / 255.0f * 1.3f);
        matrices.push();
        matrices.translate(tx - camPos.x, ty - camPos.y, tz - camPos.z);
        int crystalCount = 18;
        float pelvisY = entityHeight * 0.35f;
        float torsoY = entityHeight * 0.55f;
        float neckY = entityHeight * 0.74f;
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        RenderSystem.enableBlend();
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR);
        boolean hasCrystals = false;
        for (int i2 = 0; i2 < crystalCount; ++i2) {
            float s1 = (float)Math.sin((float)i2 * 1.7f + 0.3f) * 0.5f + 0.5f;
            float s2 = (float)Math.cos((float)i2 * 2.3f + 0.7f) * 0.5f + 0.5f;
            float s3 = (float)Math.sin((float)i2 * 3.1f + 1.1f) * 0.5f + 0.5f;
            float angle = time + (float)i2 * (360.0f / (float)crystalCount) + s1 * 12.0f;
            float radius = halfWidth + 0.25f + s3 * 0.15f;
            float cx = radius * (float)Math.cos(Math.toRadians(angle));
            float cz = radius * (float)Math.sin(Math.toRadians(angle));
            float cy = s2 * entityHeight;
            float scale = 0.18f * eased;
            if (scale < 0.001f) continue;
            float lookY = this.getCrystalLookY(cy, entityHeight, pelvisY, torsoY, neckY);
            float dx = -cx;
            float dy = lookY - cy;
            float dz = -cz;
            float yaw = (float)Math.toDegrees(Math.atan2(dz, dx));
            float pitch = (float)Math.toDegrees(Math.atan2(dy, (float)Math.sqrt(dx * dx + dz * dz)));
            this.drawCrystalShape(buf, matrices, cx, cy, cz, scale, yaw, pitch, (int)(r2 * 255.0f), (int)(g2 * 255.0f), (int)(b2 * 255.0f), (int)(200.0f * anim), sharp);
            hasCrystals = true;
        }
        if (hasCrystals) {
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
        }
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)GLOW_TEXTURE);
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        BufferBuilder glowBuf = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        boolean hasGlow = false;
        for (int i3 = 0; i3 < crystalCount; ++i3) {
            float s1 = (float)Math.sin((float)i3 * 1.7f + 0.3f) * 0.5f + 0.5f;
            float s2 = (float)Math.cos((float)i3 * 2.3f + 0.7f) * 0.5f + 0.5f;
            float s3 = (float)Math.sin((float)i3 * 3.1f + 1.1f) * 0.5f + 0.5f;
            float angle = time + (float)i3 * (360.0f / (float)crystalCount) + s1 * 12.0f;
            float radius = halfWidth + 0.25f + s3 * 0.15f;
            float cx = radius * (float)Math.cos(Math.toRadians(angle));
            float cz = radius * (float)Math.sin(Math.toRadians(angle));
            float cy = s2 * entityHeight;
            float scale = 0.18f * eased;
            if (!(anim * 0.15f > 0.001f) || !(scale > 1.0E-4f)) continue;
            this.putBloomQuad(glowBuf, matrices, cx, cy, cz, scale * 5.5f, r2, g2, b2, anim * 0.15f, camYaw, camPitch);
            this.putBloomQuad(glowBuf, matrices, cx, cy, cz, scale * 3.5f, r2, g2, b2, anim * 0.25f, camYaw, camPitch);
            hasGlow = true;
        }
        if (hasGlow) {
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)glowBuf.end());
        }
        matrices.pop();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    private float getCrystalLookY(float cy, float h2, float pelvis, float torso, float neck) {
        float n2 = cy / h2;
        if (n2 < 0.33f) {
            return pelvis;
        }
        if (n2 < 0.6f) {
            return torso;
        }
        return neck;
    }

    private void drawCrystalShape(BufferBuilder buf, MatrixStack ms, float x2, float y2, float z2, float scale, float yaw, float pitch, int r2, int g2, int b2, int a2, boolean sharp) {
        ms.push();
        ms.translate(x2, y2, z2);
        ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-yaw));
        ms.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(pitch));
        ms.scale(scale, scale, scale);
        Matrix4f m2 = ms.peek().getPositionMatrix();
        float w2 = sharp ? 0.35f : 0.7f;
        float h2 = sharp ? 1.2f : 1.0f;
        this.tri(buf, m2, h2, 0.0f, 0.0f, 0.0f, w2, 0.0f, 0.0f, 0.0f, w2, r2, g2, b2, a2);
        this.tri(buf, m2, h2, 0.0f, 0.0f, 0.0f, 0.0f, w2, 0.0f, -w2, 0.0f, r2, g2, b2, a2);
        this.tri(buf, m2, h2, 0.0f, 0.0f, 0.0f, -w2, 0.0f, 0.0f, 0.0f, -w2, r2, g2, b2, a2);
        this.tri(buf, m2, h2, 0.0f, 0.0f, 0.0f, 0.0f, -w2, 0.0f, w2, 0.0f, r2, g2, b2, a2);
        this.tri(buf, m2, -h2, 0.0f, 0.0f, 0.0f, w2, 0.0f, 0.0f, 0.0f, w2, r2, g2, b2, a2);
        this.tri(buf, m2, -h2, 0.0f, 0.0f, 0.0f, 0.0f, w2, 0.0f, -w2, 0.0f, r2, g2, b2, a2);
        this.tri(buf, m2, -h2, 0.0f, 0.0f, 0.0f, -w2, 0.0f, 0.0f, 0.0f, -w2, r2, g2, b2, a2);
        this.tri(buf, m2, -h2, 0.0f, 0.0f, 0.0f, 0.0f, -w2, 0.0f, w2, 0.0f, r2, g2, b2, a2);
        ms.pop();
    }

    private void tri(BufferBuilder buf, Matrix4f m2, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, int r2, int g2, int b2, int a2) {
        buf.vertex(m2, x1, y1, z1).color(r2, g2, b2, a2);
        buf.vertex(m2, x2, y2, z2).color(r2, g2, b2, a2);
        buf.vertex(m2, x3, y3, z3).color(r2, g2, b2, a2);
    }

    private void renderCubes(MatrixStack matrices, Camera camera, LivingEntity target, float partialTicks) {
        long now = System.currentTimeMillis();
        if (this.lastCubeTime == 0L) {
            this.lastCubeTime = now;
        }
        float dt = Math.min((float)(now - this.lastCubeTime) / 1000.0f, 0.1f);
        this.lastCubeTime = now;
        if (target != null) {
            this.spawnAccumulator += dt;
            while (this.spawnAccumulator >= 0.017f) {
                this.spawnAccumulator -= 0.017f;
                for (int i2 = 0; i2 < 1; ++i2) {
                    double rand = MathUtil.random(0.0f, 360.0f);
                    double px = Math.cos(Math.toRadians(rand)) * 0.7;
                    double py = MathUtil.random(0.04, 0.2);
                    double pz = Math.sin(Math.toRadians(rand)) * 0.7;
                    this.particles.add(new Particle(target, px, py, pz));
                }
            }
        }
        float camYaw = camera.getYaw();
        float camPitch = camera.getPitch();
        Iterator<Particle> it = this.particles.iterator();
        ArrayList<Particle> toRender = new ArrayList<Particle>();
        while (it.hasNext()) {
            Particle p2 = it.next();
            p2.update(dt);
            if (now - p2.time > 1000L) {
                it.remove();
                continue;
            }
            toRender.add(p2);
        }
        int color = ColorProvider.getColorClient();
        for (Particle p3 : toRender) {
            p3.renderCube(matrices, camera, color, partialTicks);
        }
        if (!toRender.isEmpty()) {
            RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
            RenderSystem.setShaderTexture((int)0, (Identifier)GLOW_TEXTURE);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask((boolean)false);
            BufferBuilder builder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
            boolean hasBloom = false;
            for (Particle p4 : toRender) {
                if (!p4.renderBloom(builder, matrices, camera, color, camYaw, camPitch, partialTicks)) continue;
                hasBloom = true;
            }
            if (hasBloom) {
                BufferRenderer.drawWithGlobalProgram((BuiltBuffer)builder.end());
            }
            RenderSystem.depthMask((boolean)true);
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableBlend();
            RenderSystem.enableCull();
        }
    }

    private void putBloomQuad(BufferBuilder builder, MatrixStack ms, double x2, double y2, double z2, float scale, float r2, float g2, float b2, float a2, float camYaw, float camPitch) {
        if (a2 <= 0.001f || scale <= 1.0E-4f) {
            return;
        }
        ms.push();
        ms.translate(x2, y2, z2);
        ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camYaw));
        ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camPitch));
        ms.scale(scale, scale, scale);
        Matrix4f m2 = ms.peek().getPositionMatrix();
        int ri = (int)(r2 * 255.0f);
        int gi = (int)(g2 * 255.0f);
        int bi = (int)(b2 * 255.0f);
        int ai2 = (int)(a2 * 255.0f);
        builder.vertex(m2, -0.5f, 0.5f, 0.0f).texture(0.0f, 1.0f).color(ri, gi, bi, ai2);
        builder.vertex(m2, 0.5f, 0.5f, 0.0f).texture(1.0f, 1.0f).color(ri, gi, bi, ai2);
        builder.vertex(m2, 0.5f, -0.5f, 0.0f).texture(1.0f, 0.0f).color(ri, gi, bi, ai2);
        builder.vertex(m2, -0.5f, -0.5f, 0.0f).texture(0.0f, 0.0f).color(ri, gi, bi, ai2);
        ms.pop();
    }

    private static float easeOutCubic(float t2) {
        float u2 = 1.0f - t2;
        return 1.0f - u2 * u2 * u2;
    }

    private void restoreRenderState() {
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void renderNursultan(MatrixStack matrices, Camera camera, float tickDelta) {
        float anim2Val = this.animation2.getValue();
        if (anim2Val <= 0.01f || this.lastTarget == null) {
            return;
        }
        Vec3d camPos = camera.getPos();
        double x2 = this.interpolate(this.lastTarget.getX(), this.lastTarget.lastRenderX, tickDelta) - camPos.x;
        double y2 = this.interpolate(this.lastTarget.getY(), this.lastTarget.lastRenderY, tickDelta) - camPos.y + (double)this.lastTarget.getHeight() / 2.0;
        double z2 = this.interpolate(this.lastTarget.getZ(), this.lastTarget.lastRenderZ, tickDelta) - camPos.z;
        float time = (float)(System.currentTimeMillis() - this.javelinTimestamp4) / 1100.0f;
        float rotSpd = 360.0f;
        float rotation = time * rotSpd;
        float radius = 0.5f;
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)GLOW_TEXTURE);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate((int)770, (int)1, (int)0, (int)1);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        for (int layer = 0; layer < 4; ++layer) {
            float layerOffset = (float)(layer - 1) * 0.4f;
            float prevSize = -1.0f;
            for (float i2 = 0.0f; i2 < 130.0f; i2 += 1.0f) {
                float angle = rotation + i2 + layerOffset * 360.0f;
                double radians = Math.toRadians(-angle);
                double yOffset = Math.sin(radians + 2.0) * (double)layerOffset;
                float size = radius * (i2 / 140.0f);
                float finalSize = prevSize >= 0.0f ? (prevSize + size) / 2.0f : size;
                prevSize = size;
                float alpha = MathHelper.clamp((float)(finalSize *= anim2Val), (float)0.0f, (float)1.0f);
                int baseColor = ColorProvider.getColorClient();
                int a2 = Math.min(255, (int)(600.0 * (double)anim2Val * (double)alpha));
                int color = ColorProvider.setAlpha(baseColor, a2);
                int r2 = ColorProvider.red(color);
                int g2 = ColorProvider.green(color);
                int b2 = ColorProvider.blue(color);
                int colAlpha = ColorProvider.alpha(color);
                matrices.push();
                matrices.translate(x2, y2 + yOffset, z2);
                matrices.multiply(camera.getRotation());
                float halfSize = finalSize / 2.0f;
                double cosAngle = Math.cos(radians) * (double)radius - (double)halfSize;
                double sinAngle = Math.sin(radians) * (double)radius - (double)halfSize;
                buffer.vertex(matrices.peek().getPositionMatrix(), (float)cosAngle, -halfSize, (float)sinAngle).texture(0.0f, 1.0f).color(r2, g2, b2, colAlpha);
                buffer.vertex(matrices.peek().getPositionMatrix(), (float)(cosAngle + (double)finalSize), -halfSize, (float)sinAngle).texture(1.0f, 1.0f).color(r2, g2, b2, colAlpha);
                buffer.vertex(matrices.peek().getPositionMatrix(), (float)(cosAngle + (double)finalSize), halfSize, (float)sinAngle).texture(1.0f, 0.0f).color(r2, g2, b2, colAlpha);
                buffer.vertex(matrices.peek().getPositionMatrix(), (float)cosAngle, halfSize, (float)sinAngle).texture(0.0f, 0.0f).color(r2, g2, b2, colAlpha);
                matrices.pop();
            }
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
        this.restoreRenderState();
    }

    private void drawCircle(MatrixStack matrices, Camera camera, float tickDelta) {
        float anim2Val = this.animation2.getValue();
        if (anim2Val <= 0.01f || this.lastTarget == null) {
            return;
        }
        Vec3d camPos = camera.getPos();
        double x2 = this.interpolate(this.lastTarget.getX(), this.lastTarget.lastRenderX, tickDelta) - camPos.x;
        double y2 = this.interpolate(this.lastTarget.getY(), this.lastTarget.lastRenderY, tickDelta) - camPos.y;
        double z2 = this.interpolate(this.lastTarget.getZ(), this.lastTarget.lastRenderZ, tickDelta) - camPos.z;
        float height = this.lastTarget.getHeight();
        int period = 1500;
        double time = System.currentTimeMillis() % (long)period;
        boolean ascending = time > (double)(period / 2);
        float progress = (float)(time / (double)((float)period / 2.0f));
        progress = ascending ? (progress -= 1.0f) : 1.0f - progress;
        progress = (double)progress < 0.5 ? 2.0f * progress * progress : (float)(1.0 - Math.pow(-2.0f * progress + 2.0f, 2.0) / 2.0);
        double yOffset = height / 2.0f * ((double)progress > 0.5 ? 1.0f - progress : progress) * (float)(ascending ? -1 : 1);
        matrices.push();
        matrices.translate(x2, y2 + (double)(height * progress) + yOffset, z2);
        float hurtTime = 0.0f;
        Entity entity = this.lastTarget;
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity;
            hurtTime = ((float)living.hurtTime - (living.hurtTime != 0 ? tickDelta : 0.0f)) / 10.0f;
        }
        long timeMs = (long)((float)(System.currentTimeMillis() - this.javelinTimestamp4) / 2.5f);
        long nanoTime = System.nanoTime();
        float deltaTime = (float)(nanoTime - this.javelinTimestamp5) / 2000000.0f;
        this.javelinTimestamp5 = nanoTime;
        this.javelinValue23 += hurtTime * deltaTime;
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)GLOW_TEXTURE);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate((int)770, (int)1, (int)0, (int)1);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        for (int layer = 0; layer < 4; ++layer) {
            for (int i2 = 0; i2 < 15; ++i2) {
                matrices.push();
                float particleProgress = (float)i2 / 14.0f;
                float size = (0.5f * (1.0f - particleProgress) + 0.5f * particleProgress) * anim2Val;
                float angle = 0.2f * ((float)timeMs + this.javelinValue23 - (float)i2 * 3.5f) / 15.0f;
                boolean firstHalf = particleProgress < 0.5f;
                float wave = firstHalf ? particleProgress * 2.0f : (1.0f - particleProgress) * 2.0f;
                double amplitude = Math.sin((double)wave * Math.PI) * 2.0;
                Random random = new Random((long)i2 * 12345L);
                double offsetX = (random.nextDouble() - 0.5) * amplitude;
                double offsetY = (random.nextDouble() - 0.5) * amplitude;
                double offsetZ = (random.nextDouble() - 0.5) * amplitude;
                double animOffsetX = offsetX * (double)anim2Val - offsetX;
                double animOffsetY = offsetY * (double)anim2Val - offsetY;
                double animOffsetZ = offsetZ * (double)anim2Val - offsetZ;
                double radius = 0.7;
                switch (layer) {
                    case 0: {
                        matrices.translate(Math.cos(angle) * radius + animOffsetX, animOffsetY, Math.sin(angle) * radius + animOffsetZ);
                        break;
                    }
                    case 1: {
                        matrices.translate(-Math.sin(angle) * radius + animOffsetX, animOffsetY, Math.cos(angle) * radius + animOffsetZ);
                        break;
                    }
                    case 2: {
                        matrices.translate(-Math.cos(angle) * radius + animOffsetX, animOffsetY, -Math.sin(angle) * radius + animOffsetZ);
                        break;
                    }
                    case 3: {
                        matrices.translate(Math.sin(angle) * radius + animOffsetX, animOffsetY, -Math.cos(angle) * radius + animOffsetZ);
                    }
                }
                float particleSize = size * 0.5f;
                int baseColor = ColorProvider.getColorClient();
                int a2 = Math.min(255, (int)(400.0f * anim2Val));
                int color = ColorProvider.setAlpha(baseColor, a2);
                int r2 = ColorProvider.red(color);
                int g2 = ColorProvider.green(color);
                int b2 = ColorProvider.blue(color);
                int colAlpha = ColorProvider.alpha(color);
                matrices.multiply(camera.getRotation());
                buffer.vertex(matrices.peek().getPositionMatrix(), -particleSize, -particleSize, 0.0f).texture(1.0f, 1.0f).color(r2, g2, b2, colAlpha);
                buffer.vertex(matrices.peek().getPositionMatrix(), particleSize, -particleSize, 0.0f).texture(0.0f, 1.0f).color(r2, g2, b2, colAlpha);
                buffer.vertex(matrices.peek().getPositionMatrix(), particleSize, particleSize, 0.0f).texture(0.0f, 0.0f).color(r2, g2, b2, colAlpha);
                buffer.vertex(matrices.peek().getPositionMatrix(), -particleSize, particleSize, 0.0f).texture(1.0f, 0.0f).color(r2, g2, b2, colAlpha);
                matrices.pop();
            }
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
        this.restoreRenderState();
        matrices.pop();
    }

    private void drawGhostOrbits(MatrixStack matrices, Camera camera, float tickDelta) {
        if (this.lastTarget == null) {
            return;
        }
        Vec3d camPos = camera.getPos();
        double tx = this.interpolate(this.lastTarget.getX(), this.lastTarget.lastRenderX, tickDelta);
        double ty = this.interpolate(this.lastTarget.getY(), this.lastTarget.lastRenderY, tickDelta);
        double tz = this.interpolate(this.lastTarget.getZ(), this.lastTarget.lastRenderZ, tickDelta);
        Vec3d targetCenter = new Vec3d(tx, ty + (double)this.lastTarget.getHeight() / 2.0, tz);
        long now = System.currentTimeMillis();
        if (this.lastOrbitTime == 0L) {
            this.lastOrbitTime = now;
        }
        float dtMs = now - this.lastOrbitTime;
        this.lastOrbitTime = now;
        this.movingAngle += 20.0f * dtMs / 16.667f * 0.27272728f;
        boolean isHurt = false;
        Entity entity = this.lastTarget;
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity;
            isHurt = living.hurtTime > 7;
        }
        this.orbitShrinkAnim.run(isHurt ? 1.0f : 0.0f);
        float shrinkValue = this.orbitShrinkAnim.getValue();
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)GLOW_TEXTURE);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((int)770, (int)1);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        int baseThemeColor = ColorProvider.getColorClient();
        float anim2Val = this.animation2.getValue();
        for (int i2 = 0; i2 < 3; ++i2) {
            float angleOffset = (float)i2 * 360.0f / 3.0f;
            float currentAngle = this.movingAngle + angleOffset;
            double radian = Math.toRadians(currentAngle);
            float orbitRadius = 0.4f - shrinkValue * 0.4f;
            float ox = (float)Math.sin(radian) * orbitRadius;
            float oz = (float)Math.cos(radian) * orbitRadius;
            double oy = 0.3 * Math.sin(Math.toRadians(this.movingAngle / ((float)i2 + 1.0f)));
            Vec3d targetGhostPos = targetCenter.add((double)ox, oy, (double)oz);
            if (this.orbitPositions[i2] == null || this.orbitPositions[i2].distanceTo(targetGhostPos) > 10.0) {
                this.orbitPositions[i2] = targetGhostPos;
                this.orbitMotions[i2] = Vec3d.ZERO;
            }
            float mul = 0.1f * (float)Math.max(this.mc.getCurrentFps(), 10) / 10.0f;
            Vec3d diff = targetGhostPos.subtract(this.orbitPositions[i2]);
            this.orbitMotions[i2] = diff.multiply((double)mul, (double)mul, (double)mul);
            this.orbitPositions[i2] = this.orbitPositions[i2].add(this.orbitMotions[i2]);
            if (this.orbitTrails[i2].isEmpty() || this.orbitTrails[i2].get(0).distanceTo(this.orbitPositions[i2]) > 0.01) {
                this.orbitTrails[i2].add(0, this.orbitPositions[i2]);
                while (this.orbitTrails[i2].size() > 40) {
                    this.orbitTrails[i2].remove(this.orbitTrails[i2].size() - 1);
                }
            }
            for (int j2 = 0; j2 < this.orbitTrails[i2].size(); ++j2) {
                Vec3d p2 = this.orbitTrails[i2].get(j2);
                float offset = 1.0f - (float)j2 / 40.0f;
                matrices.push();
                matrices.translate(p2.x - camPos.x, p2.y - camPos.y, p2.z - camPos.z);
                matrices.multiply(camera.getRotation());
                Matrix4f matrix = matrices.peek().getPositionMatrix();
                float opacity = (float)Math.pow(offset, 1.8) * anim2Val * 0.7f;
                int a2 = Math.min(255, (int)(opacity * 255.0f));
                int color = ColorProvider.setAlpha(baseThemeColor, a2);
                int r2 = ColorProvider.red(color);
                int g2 = ColorProvider.green(color);
                int b2 = ColorProvider.blue(color);
                int colAlpha = ColorProvider.alpha(color);
                float scale = SCALE_CACHE[Math.min((int)(offset * 100.0f), 100)] * 0.8f;
                buffer.vertex(matrix, -scale, scale, 0.0f).texture(0.0f, 1.0f).color(r2, g2, b2, colAlpha);
                buffer.vertex(matrix, scale, scale, 0.0f).texture(1.0f, 1.0f).color(r2, g2, b2, colAlpha);
                buffer.vertex(matrix, scale, -scale, 0.0f).texture(1.0f, 0.0f).color(r2, g2, b2, colAlpha);
                buffer.vertex(matrix, -scale, -scale, 0.0f).texture(0.0f, 0.0f).color(r2, g2, b2, colAlpha);
                matrices.pop();
            }
            if (this.orbitTrails[i2].isEmpty()) continue;
            Vec3d head = this.orbitTrails[i2].get(0);
            matrices.push();
            matrices.translate(head.x - camPos.x, head.y - camPos.y, head.z - camPos.z);
            matrices.multiply(camera.getRotation());
            Matrix4f matrix = matrices.peek().getPositionMatrix();
            float headScale = 0.35f * anim2Val;
            int ha = Math.min(255, (int)(120.0f * anim2Val));
            int headColor = ColorProvider.setAlpha(baseThemeColor, ha);
            int hr = ColorProvider.red(headColor);
            int hg = ColorProvider.green(headColor);
            int hb = ColorProvider.blue(headColor);
            int hAlpha = ColorProvider.alpha(headColor);
            buffer.vertex(matrix, -headScale, headScale, 0.0f).texture(0.0f, 1.0f).color(hr, hg, hb, hAlpha);
            buffer.vertex(matrix, headScale, headScale, 0.0f).texture(1.0f, 1.0f).color(hr, hg, hb, hAlpha);
            buffer.vertex(matrix, headScale, -headScale, 0.0f).texture(1.0f, 0.0f).color(hr, hg, hb, hAlpha);
            buffer.vertex(matrix, -headScale, -headScale, 0.0f).texture(0.0f, 0.0f).color(hr, hg, hb, hAlpha);
            matrices.pop();
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
        this.restoreRenderState();
    }

    private void renderJavelinCrystals(MatrixStack matrices, Camera camera, float tickDelta) {
        float anim2Val = this.animation2.getValue();
        if (anim2Val <= 0.01f || this.lastTarget == null || this.mc.player == null) {
            return;
        }
        Vec3d camPos = camera.getPos();
        double tx = this.interpolate(this.lastTarget.getX(), this.lastTarget.lastRenderX, tickDelta);
        double ty = this.interpolate(this.lastTarget.getY(), this.lastTarget.lastRenderY, tickDelta);
        double tz = this.interpolate(this.lastTarget.getZ(), this.lastTarget.lastRenderZ, tickDelta);
        this.crystalMoving += 1.0f;
        float entityHeight = this.lastTarget.getHeight();
        float entityWidth = this.lastTarget.getWidth();
        float width = entityWidth * 1.5f;
        int baseColor = ColorProvider.getColorClient();
        int cr = ColorProvider.red(baseColor);
        int cg = ColorProvider.green(baseColor);
        int cb = ColorProvider.blue(baseColor);
        matrices.push();
        matrices.translate(tx - camPos.x, ty - camPos.y, tz - camPos.z);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((int)770, (int)771);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        BufferBuilder crystalBuffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR);
        int crystalAlpha = Math.min(255, (int)(anim2Val * 255.0f));
        int cTop = crystalAlpha << 24 | Math.min(255, cr + 60) << 16 | Math.min(255, cg + 60) << 8 | Math.min(255, cb + 60);
        int cSide1 = crystalAlpha << 24 | Math.min(255, cr + 30) << 16 | Math.min(255, cg + 30) << 8 | Math.min(255, cb + 30);
        int cSide2 = crystalAlpha << 24 | cr << 16 | cg << 8 | cb;
        int cBot = crystalAlpha << 24 | Math.max(0, cr - 30) << 16 | Math.max(0, cg - 30) << 8 | Math.max(0, cb - 30);
        float cw = 0.075f;
        float ch = 0.2f;
        for (int i2 = 0; i2 < 360; i2 += 19) {
            int fc;
            int next;
            int j2;
            float val = 1.2f - 0.5f * anim2Val;
            float angleDeg = (float)i2 + this.crystalMoving * 0.3f;
            float angleRad = (float)Math.toRadians(angleDeg);
            float sin = (float)(Math.sin(angleRad) * (double)width * (double)val);
            float cos = (float)(Math.cos(angleRad) * (double)width * (double)val);
            float heightPrc = (float)i2 / 20.0f * 0.6180339f % 1.0f;
            float crystalY = entityHeight * heightPrc;
            matrices.push();
            matrices.translate(sin, crystalY, cos);
            Vector3f dir = new Vector3f(-sin, 0.0f, -cos).normalize();
            Quaternionf rotation = new Quaternionf().rotationTo((Vector3fc)new Vector3f(0.0f, 1.0f, 0.0f), (Vector3fc)dir);
            matrices.multiply(rotation);
            Matrix4f matrix = matrices.peek().getPositionMatrix();
            float[] ex = new float[]{cw, 0.0f, -cw, 0.0f};
            float[] ez = new float[]{0.0f, cw, 0.0f, -cw};
            for (j2 = 0; j2 < 4; ++j2) {
                next = (j2 + 1) % 4;
                fc = j2 % 2 == 0 ? cTop : cSide1;
                crystalBuffer.vertex(matrix, 0.0f, ch, 0.0f).color(fc);
                crystalBuffer.vertex(matrix, ex[j2], 0.0f, ez[j2]).color(fc);
                crystalBuffer.vertex(matrix, ex[next], 0.0f, ez[next]).color(fc);
            }
            for (j2 = 0; j2 < 4; ++j2) {
                next = (j2 + 1) % 4;
                fc = j2 % 2 == 0 ? cBot : cSide2;
                crystalBuffer.vertex(matrix, 0.0f, -ch, 0.0f).color(fc);
                crystalBuffer.vertex(matrix, ex[next], 0.0f, ez[next]).color(fc);
                crystalBuffer.vertex(matrix, ex[j2], 0.0f, ez[j2]).color(fc);
            }
            matrices.pop();
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)crystalBuffer.end());
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)GLOW_TEXTURE);
        RenderSystem.blendFunc((int)770, (int)1);
        BufferBuilder glowBuffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        for (int i3 = 0; i3 < 360; i3 += 19) {
            float val = 1.2f - 0.5f * anim2Val;
            float angleDeg = (float)i3 + this.crystalMoving * 0.3f;
            float angleRad = (float)Math.toRadians(angleDeg);
            float sin = (float)(Math.sin(angleRad) * (double)width * (double)val);
            float cos = (float)(Math.cos(angleRad) * (double)width * (double)val);
            float heightPrc = (float)i3 / 20.0f * 0.6180339f % 1.0f;
            float crystalY = entityHeight * heightPrc;
            matrices.push();
            matrices.translate(sin, crystalY, cos);
            matrices.multiply(camera.getRotation());
            Matrix4f matrix = matrices.peek().getPositionMatrix();
            float glowSize = 0.15f * anim2Val;
            int glowAlpha = (int)(anim2Val * 100.0f);
            int glowColor = glowAlpha << 24 | cr << 16 | cg << 8 | cb;
            int gr = ColorProvider.red(glowColor);
            int gg = ColorProvider.green(glowColor);
            int gb = ColorProvider.blue(glowColor);
            int ga = ColorProvider.alpha(glowColor);
            glowBuffer.vertex(matrix, -glowSize, glowSize, 0.0f).texture(0.0f, 1.0f).color(gr, gg, gb, ga);
            glowBuffer.vertex(matrix, glowSize, glowSize, 0.0f).texture(1.0f, 1.0f).color(gr, gg, gb, ga);
            glowBuffer.vertex(matrix, glowSize, -glowSize, 0.0f).texture(1.0f, 0.0f).color(gr, gg, gb, ga);
            glowBuffer.vertex(matrix, -glowSize, -glowSize, 0.0f).texture(0.0f, 0.0f).color(gr, gg, gb, ga);
            matrices.pop();
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)glowBuffer.end());
        matrices.pop();
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    private void renderSouls(MatrixStack matrices, Camera camera, LivingEntity target, float anim, float tickDelta) {
        if (target == null) {
            return;
        }
        Vec3d camPos = camera.getPos();
        double x2 = MathHelper.lerp((double)tickDelta, (double)target.lastRenderX, (double)target.getX()) - camPos.x;
        double y2 = MathHelper.lerp((double)tickDelta, (double)target.lastRenderY, (double)target.getY()) - camPos.y;
        double z2 = MathHelper.lerp((double)tickDelta, (double)target.lastRenderZ, (double)target.getZ()) - camPos.z;
        this.soulsPlavnost = (float)(System.currentTimeMillis() % 100000L) / (float)this.soulsSpeed.getValue();
        float width = target.getWidth() * 1.5f;
        int color = ColorProvider.getColorClient();
        float alphaPC = (float)ColorProvider.alpha(color) * anim / 255.0f;
        matrices.push();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)BLOOM_TEXTURE);
        BufferBuilder builder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        int r2 = ColorProvider.red(color);
        int g2 = ColorProvider.green(color);
        int b2 = ColorProvider.blue(color);
        int step = 2;
        int wormTick = 0;
        int wormCD = 0;
        for (int i2 = 0; i2 < 360; i2 += step) {
            float size = 0.13f + 0.005f * (float)wormTick;
            float bigSize = 0.7f + 0.005f * (float)wormTick;
            if (wormCD > 0) {
                wormCD -= step;
                continue;
            }
            if ((wormTick += step) > 50) {
                wormCD = 100;
                wormTick = 0;
                continue;
            }
            float val = Math.max(0.5f, 1.2f - 0.5f * anim);
            float angleRad = (float)Math.toRadians((float)i2 + this.soulsPlavnost);
            float sin = (float)(Math.sin(angleRad) * (double)width * (double)val);
            float cos = (float)(Math.cos(angleRad) * (double)width * (double)val);
            float waveY = (float)Math.sin(Math.toRadians((float)i2 / 2.0f + this.soulsPlavnost / 5.0f));
            matrices.push();
            matrices.translate(x2 + (double)sin, y2 + (double)(target.getHeight() / 1.5f) + (double)(target.getHeight() / 3.0f * waveY), z2 + (double)cos);
            matrices.multiply(camera.getRotation());
            float fa = alphaPC * 0.05f;
            Matrix4f mat = matrices.peek().getPositionMatrix();
            builder.vertex(mat, -bigSize / 2.0f, bigSize / 2.0f, 0.0f).texture(0.0f, 1.0f).color(r2, g2, b2, (int)(fa * 255.0f));
            builder.vertex(mat, bigSize / 2.0f, bigSize / 2.0f, 0.0f).texture(1.0f, 1.0f).color(r2, g2, b2, (int)(fa * 255.0f));
            builder.vertex(mat, bigSize / 2.0f, -bigSize / 2.0f, 0.0f).texture(1.0f, 0.0f).color(r2, g2, b2, (int)(fa * 255.0f));
            builder.vertex(mat, -bigSize / 2.0f, -bigSize / 2.0f, 0.0f).texture(0.0f, 0.0f).color(r2, g2, b2, (int)(fa * 255.0f));
            float ca = alphaPC;
            builder.vertex(mat, -size / 2.0f, size / 2.0f, 0.0f).texture(0.0f, 1.0f).color(r2, g2, b2, (int)(ca * 255.0f));
            builder.vertex(mat, size / 2.0f, size / 2.0f, 0.0f).texture(1.0f, 1.0f).color(r2, g2, b2, (int)(ca * 255.0f));
            builder.vertex(mat, size / 2.0f, -size / 2.0f, 0.0f).texture(1.0f, 0.0f).color(r2, g2, b2, (int)(ca * 255.0f));
            builder.vertex(mat, -size / 2.0f, -size / 2.0f, 0.0f).texture(0.0f, 0.0f).color(r2, g2, b2, (int)(ca * 255.0f));
            matrices.pop();
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)builder.end());
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
        matrices.pop();
    }

    private void renderSouls2(MatrixStack matrices, Camera camera, LivingEntity target, float anim, float tickDelta) {
        if (target == null) {
            return;
        }
        Vec3d camPos = camera.getPos();
        double bx = MathHelper.lerp((double)tickDelta, (double)target.lastRenderX, (double)target.getX()) - camPos.x;
        double by = MathHelper.lerp((double)tickDelta, (double)target.lastRenderY, (double)target.getY()) - camPos.y + (double)target.getHeight() / 2.0;
        double bz = MathHelper.lerp((double)tickDelta, (double)target.lastRenderZ, (double)target.getZ()) - camPos.z;
        int baseColor = ColorProvider.getColorClient();
        long time = System.currentTimeMillis();
        matrices.push();
        RenderSystem.enableBlend();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)BLOOM_TEXTURE);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        for (int i2 = 0; i2 < 20; ++i2) {
            float trailFactor = 1.0f - (float)i2 / 20.0f * 0.7f;
            double angle = 0.15 * ((double)time - (double)i2 * 10.0) / 25.0;
            this.drawSouls2Particle(matrices, buffer, camera, bx, by, bz, angle, 1.0, 1.0, -1.0, 0.285f * trailFactor, anim, baseColor);
            this.drawSouls2Particle(matrices, buffer, camera, bx, by, bz, angle, -1.0, 1.0, -1.0, 0.285f * trailFactor, anim, baseColor);
            this.drawSouls2Particle(matrices, buffer, camera, bx, by, bz, angle, -1.0, -1.0, 1.0, 0.285f * trailFactor, anim, baseColor);
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableBlend();
        matrices.pop();
    }

    private void drawSouls2Particle(MatrixStack ms, BufferBuilder buffer, Camera camera, double bx, double by, double bz, double angle, double sMult, double cMult, double cZMult, float size, float anim, int baseColor) {
        double fz;
        double fy;
        double fx;
        double s2 = Math.sin(angle) * 0.7;
        double c2 = Math.cos(angle) * 0.7;
        if (sMult == 1.0 && cMult == 1.0 && cZMult == -1.0) {
            fx = s2;
            fy = c2;
            fz = -c2;
        } else if (sMult == -1.0 && cMult == 1.0 && cZMult == -1.0) {
            fx = -s2;
            fy = s2;
            fz = -c2;
        } else {
            fx = -s2;
            fy = -s2;
            fz = c2;
        }
        ms.push();
        ms.translate(bx + fx, by + fy, bz + fz);
        ms.multiply(camera.getRotation());
        Matrix4f mat = ms.peek().getPositionMatrix();
        int r2 = ColorProvider.red(baseColor);
        int g2 = ColorProvider.green(baseColor);
        int bb2 = ColorProvider.blue(baseColor);
        int alpha = (int)(255.0f * anim);
        float glowSize = size * 2.5f;
        int glowAlpha = (int)(255.0f * anim * 0.3f);
        buffer.vertex(mat, -glowSize / 2.0f, glowSize / 2.0f, 0.0f).texture(0.0f, 1.0f).color(r2, g2, bb2, glowAlpha);
        buffer.vertex(mat, glowSize / 2.0f, glowSize / 2.0f, 0.0f).texture(1.0f, 1.0f).color(r2, g2, bb2, glowAlpha);
        buffer.vertex(mat, glowSize / 2.0f, -glowSize / 2.0f, 0.0f).texture(1.0f, 0.0f).color(r2, g2, bb2, glowAlpha);
        buffer.vertex(mat, -glowSize / 2.0f, -glowSize / 2.0f, 0.0f).texture(0.0f, 0.0f).color(r2, g2, bb2, glowAlpha);
        buffer.vertex(mat, -size / 2.0f, size / 2.0f, 0.0f).texture(0.0f, 1.0f).color(r2, g2, bb2, alpha);
        buffer.vertex(mat, size / 2.0f, size / 2.0f, 0.0f).texture(1.0f, 1.0f).color(r2, g2, bb2, alpha);
        buffer.vertex(mat, size / 2.0f, -size / 2.0f, 0.0f).texture(1.0f, 0.0f).color(r2, g2, bb2, alpha);
        buffer.vertex(mat, -size / 2.0f, -size / 2.0f, 0.0f).texture(0.0f, 0.0f).color(r2, g2, bb2, alpha);
        ms.pop();
    }

    public static void refreshCustomModes(ModeSetting modeSetting) {
        if (!CUSTOM_DIR.exists()) {
            CUSTOM_DIR.mkdirs();
        }
        File[] files = CUSTOM_DIR.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        ArrayList<String> newModes = new ArrayList<String>(Arrays.asList("Crystals", "Crystals 1", "Cubes", "Circle", "Ghosts2", "Celestial", "Skull", "Soul 2", "Soul Test 3", "Soul Test", "Crystal 3DD", "Души", "Души 2"));
        cachedFilenames = new ArrayList<String>();
        if (files != null) {
            for (File f2 : files) {
                String name2 = f2.getName();
                name2 = name2.substring(0, name2.lastIndexOf(46));
                cachedFilenames.add(name2);
                newModes.add("Custom:" + name2);
            }
        }
        modeSetting.setModes(newModes);
    }

    public static void loadCustomTexture(String fileName) {
        File file;
        if (fileName == null) {
            return;
        }
        Identifier id = Identifier.of((String)"babka", (String)("targetesp_" + fileName.toLowerCase()));
        MinecraftClient mc = MinecraftClient.getInstance();
        if (customTextures.containsKey(fileName)) {
            mc.getTextureManager().destroyTexture(id);
            customTextures.remove(fileName);
        }
        if (!(file = new File(CUSTOM_DIR, fileName + ".png")).exists()) {
            return;
        }
        try (FileInputStream fis = new FileInputStream(file);){
            NativeImage image = NativeImage.read((InputStream)fis);
            NativeImageBackedTexture tex = new NativeImageBackedTexture(image);
            mc.getTextureManager().registerTexture(id, (AbstractTexture)tex);
            customTextures.put(fileName, tex.getGlId());
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static int getCustomTexId(String fileName) {
        return customTextures.getOrDefault(fileName, -1);
    }

    public static void openCustomFolder() {
        if (!CUSTOM_DIR.exists()) {
            CUSTOM_DIR.mkdirs();
        }
        try {
            Runtime.getRuntime().exec("explorer " + CUSTOM_DIR.getAbsolutePath());
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static List<String> getCachedFilenames() {
        return cachedFilenames;
    }

    private void renderCustom(MatrixStack matrices, Camera camera, LivingEntity target, float alphaPC, float partialTicks) {
        String modeVal = this.mode.getValue();
        if (!modeVal.startsWith("Custom:")) {
            return;
        }
        String fileName = modeVal.substring("Custom:".length());
        if (fileName.isEmpty()) {
            return;
        }
        int texId = TargetESP.getCustomTexId(fileName);
        if (texId == -1) {
            TargetESP.loadCustomTexture(fileName);
            texId = TargetESP.getCustomTexId(fileName);
            if (texId == -1) {
                return;
            }
        }
        Vec3d camPos = camera.getPos();
        double tx = MathHelper.lerp((double)partialTicks, (double)target.lastRenderX, (double)target.getX()) - camPos.x;
        double ty = MathHelper.lerp((double)partialTicks, (double)target.lastRenderY, (double)target.getY()) - camPos.y + (double)(target.getHeight() / 2.0f);
        double tz = MathHelper.lerp((double)partialTicks, (double)target.lastRenderZ, (double)target.getZ()) - camPos.z;
        float time = (float)(System.currentTimeMillis() % 360000L) / 1000.0f * this.customSpeed.getFloatValue() * 60.0f;
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        matrices.push();
        matrices.translate(tx, ty, tz);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camera.getYaw()));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(time));
        float size = 0.6f;
        Identifier texIdObj = Identifier.of((String)"babka", (String)("targetesp_" + fileName.toLowerCase()));
        RenderSystem.setShaderTexture((int)0, (Identifier)texIdObj);
        BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        Matrix4f mat = matrices.peek().getPositionMatrix();
        int a2 = (int)(alphaPC * 255.0f);
        buf.vertex(mat, -size, -size, 0.0f).texture(0.0f, 0.0f).color(255, 255, 255, a2);
        buf.vertex(mat, size, -size, 0.0f).texture(1.0f, 0.0f).color(255, 255, 255, a2);
        buf.vertex(mat, size, size, 0.0f).texture(1.0f, 1.0f).color(255, 255, 255, a2);
        buf.vertex(mat, -size, size, 0.0f).texture(0.0f, 1.0f).color(255, 255, 255, a2);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
        matrices.pop();
        this.restoreRenderState();
    }

    static {
        for (int k2 = 0; k2 <= 100; ++k2) {
            TargetESP.SCALE_CACHE[k2] = Math.max(0.28f * ((float)k2 / 100.0f), 0.15f);
        }
        CUSTOM_DIR = new File("BabkaCleint/targetesp");
        customTextures = new HashMap<String, Integer>();
    }

    public static class Particle {
        double x;
        double y;
        double z;
        long time;
        LivingEntity entity;

        public Particle(LivingEntity entity, double x2, double y2, double z2) {
            this.entity = entity;
            this.x = x2;
            this.y = y2;
            this.z = z2;
            this.time = System.currentTimeMillis();
        }

        public void update(float dt) {
            this.y += MathUtil.random(0.01, 0.04) * (double)(dt * 60.0f);
        }

        public void renderCube(MatrixStack ms, Camera camera, int colorInt, float partialTicks) {
            float alpha;
            if (this.entity == null) {
                return;
            }
            double alive = System.currentTimeMillis() - this.time;
            float life = Math.min(1.0f, (float)alive / 1000.0f);
            float f2 = life > 0.8f ? 1.0f - (life - 0.8f) * 5.0f : (alpha = alive < 200.0 ? (float)alive / 200.0f : 1.0f);
            if (alpha <= 0.001f) {
                return;
            }
            Vec3d cam = camera.getPos();
            double ex = MathHelper.lerp((double)partialTicks, (double)this.entity.lastRenderX, (double)this.entity.getX());
            double ey = MathHelper.lerp((double)partialTicks, (double)this.entity.lastRenderY, (double)this.entity.getY());
            double ez = MathHelper.lerp((double)partialTicks, (double)this.entity.lastRenderZ, (double)this.entity.getZ());
            float scale = 0.12f;
            int color = ColorProvider.setAlpha(colorInt, (int)(alpha * 255.0f));
            ms.push();
            ms.translate(ex - cam.x + this.x, ey - cam.y + this.y, ez - cam.z + this.z);
            double rot = alive / 10.0;
            ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees((float)rot));
            ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)rot));
            ms.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float)rot));
            ms.scale(scale, scale, scale);
            RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
            RenderSystem.enableBlend();
            RenderSystem.enableDepthTest();
            RenderSystem.disableCull();
            RenderSystem.depthMask((boolean)false);
            RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
            this.drawBox(ms, color);
            RenderSystem.depthMask((boolean)true);
            RenderSystem.disableBlend();
            RenderSystem.enableCull();
            ms.pop();
        }

        public boolean renderBloom(BufferBuilder builder, MatrixStack ms, Camera camera, int colorInt, float camYaw, float camPitch, float partialTicks) {
            float alpha;
            if (this.entity == null) {
                return false;
            }
            double alive = System.currentTimeMillis() - this.time;
            float life = Math.min(1.0f, (float)alive / 1000.0f);
            float f2 = life > 0.8f ? 1.0f - (life - 0.8f) * 5.0f : (alpha = alive < 200.0 ? (float)alive / 200.0f : 1.0f);
            if (alpha <= 0.001f) {
                return false;
            }
            float r2 = (float)ColorProvider.red(colorInt) / 255.0f;
            float g2 = (float)ColorProvider.green(colorInt) / 255.0f;
            float b2 = (float)ColorProvider.blue(colorInt) / 255.0f;
            Vec3d cam = camera.getPos();
            double ex = MathHelper.lerp((double)partialTicks, (double)this.entity.lastRenderX, (double)this.entity.getX());
            double ey = MathHelper.lerp((double)partialTicks, (double)this.entity.lastRenderY, (double)this.entity.getY());
            double ez = MathHelper.lerp((double)partialTicks, (double)this.entity.lastRenderZ, (double)this.entity.getZ());
            ms.push();
            ms.translate(ex - cam.x + this.x, ey - cam.y + this.y, ez - cam.z + this.z);
            ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camYaw));
            ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camPitch));
            ms.scale(0.9f, 0.9f, 0.9f);
            Matrix4f m2 = ms.peek().getPositionMatrix();
            int ai2 = (int)(alpha * 0.12f * 255.0f);
            int ri = (int)(r2 * 255.0f);
            int gi = (int)(g2 * 255.0f);
            int bi = (int)(b2 * 255.0f);
            builder.vertex(m2, -0.5f, 0.5f, 0.0f).texture(0.0f, 1.0f).color(ri, gi, bi, ai2);
            builder.vertex(m2, 0.5f, 0.5f, 0.0f).texture(1.0f, 1.0f).color(ri, gi, bi, ai2);
            builder.vertex(m2, 0.5f, -0.5f, 0.0f).texture(1.0f, 0.0f).color(ri, gi, bi, ai2);
            builder.vertex(m2, -0.5f, -0.5f, 0.0f).texture(0.0f, 0.0f).color(ri, gi, bi, ai2);
            ms.pop();
            return true;
        }

        private void drawBox(MatrixStack ms, int color) {
            float min = -0.5f;
            float max = 0.5f;
            float r2 = (float)ColorProvider.red(color) / 255.0f;
            float g2 = (float)ColorProvider.green(color) / 255.0f;
            float b2 = (float)ColorProvider.blue(color) / 255.0f;
            float a2 = (float)ColorProvider.alpha(color) / 255.0f;
            float lineA = a2 / 4.0f;
            float fillA = a2 / 12.0f;
            BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
            Matrix4f m2 = ms.peek().getPositionMatrix();
            this.addLine(buf, m2, min, min, min, max, min, min, r2, g2, b2, lineA);
            this.addLine(buf, m2, min, max, min, max, max, min, r2, g2, b2, lineA);
            this.addLine(buf, m2, min, min, max, max, min, max, r2, g2, b2, lineA);
            this.addLine(buf, m2, min, max, max, max, max, max, r2, g2, b2, lineA);
            this.addLine(buf, m2, min, min, min, min, max, min, r2, g2, b2, lineA);
            this.addLine(buf, m2, max, min, min, max, max, min, r2, g2, b2, lineA);
            this.addLine(buf, m2, min, min, max, min, max, max, r2, g2, b2, lineA);
            this.addLine(buf, m2, max, min, max, max, max, max, r2, g2, b2, lineA);
            this.addLine(buf, m2, min, min, min, min, min, max, r2, g2, b2, lineA);
            this.addLine(buf, m2, max, min, min, max, min, max, r2, g2, b2, lineA);
            this.addLine(buf, m2, min, max, min, min, max, max, r2, g2, b2, lineA);
            this.addLine(buf, m2, max, max, min, max, max, max, r2, g2, b2, lineA);
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
            BufferBuilder fb = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
            this.addFace(fb, m2, min, min, min, max, min, max, r2, g2, b2, fillA);
            this.addFace(fb, m2, min, max, min, max, max, max, r2, g2, b2, fillA);
            this.addFace(fb, m2, min, min, min, max, max, min, r2, g2, b2, fillA);
            this.addFace(fb, m2, min, min, max, max, max, max, r2, g2, b2, fillA);
            this.addFace(fb, m2, min, min, min, min, max, max, r2, g2, b2, fillA);
            this.addFace(fb, m2, max, min, min, max, max, max, r2, g2, b2, fillA);
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)fb.end());
        }

        private void addLine(BufferBuilder buf, Matrix4f m2, float x1, float y1, float z1, float x2, float y2, float z2, float r2, float g2, float b2, float a2) {
            buf.vertex(m2, x1, y1, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y2, z2).color(r2, g2, b2, a2);
        }

        private void addFace(BufferBuilder buf, Matrix4f m2, float x1, float y1, float z1, float x2, float y2, float z2, float r2, float g2, float b2, float a2) {
            buf.vertex(m2, x1, y1, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y1, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y1, z2).color(r2, g2, b2, a2);
            buf.vertex(m2, x1, y1, z2).color(r2, g2, b2, a2);
            buf.vertex(m2, x1, y2, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x1, y2, z2).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y2, z2).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y2, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x1, y1, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x1, y2, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y2, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y1, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x1, y1, z2).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y1, z2).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y2, z2).color(r2, g2, b2, a2);
            buf.vertex(m2, x1, y2, z2).color(r2, g2, b2, a2);
            buf.vertex(m2, x1, y1, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x1, y1, z2).color(r2, g2, b2, a2);
            buf.vertex(m2, x1, y2, z2).color(r2, g2, b2, a2);
            buf.vertex(m2, x1, y2, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y1, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y2, z1).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y2, z2).color(r2, g2, b2, a2);
            buf.vertex(m2, x2, y1, z2).color(r2, g2, b2, a2);
        }
    }
}

