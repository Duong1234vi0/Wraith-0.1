package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import ua.babka.event.EventAttack;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;

@ModuleInformation(moduleName="Cubes", moduleDesc="Объёмные кубы в мире", moduleCategory=ModuleCategory.RENDER)
public class Cubes
extends Module {
    private static final Identifier GLOW_TEX = Identifier.of((String)"mre", (String)"images/bloom.png");
    private static final float SPAWN_RADIUS = 12.0f;
    private static final float PARTICLE_SIZE = 0.18f;
    private static final float PARTICLE_SPEED = 0.25f;
    private static final float GLOW_INTENSITY = 1.7f;
    private static final float MAX_RENDER_DISTANCE_SQ = 900.0f;
    private static final byte[][] CUBE_EDGES = new byte[][]{{-1, -1, -1, 1, -1, -1}, {1, -1, -1, 1, -1, 1}, {1, -1, 1, -1, -1, 1}, {-1, -1, 1, -1, -1, -1}, {-1, 1, -1, 1, 1, -1}, {1, 1, -1, 1, 1, 1}, {1, 1, 1, -1, 1, 1}, {-1, 1, 1, -1, 1, -1}, {-1, -1, -1, -1, 1, -1}, {1, -1, -1, 1, 1, -1}, {1, -1, 1, 1, 1, 1}, {-1, -1, 1, -1, 1, 1}};
    private static final byte[][] TRIANGLE_EDGES = new byte[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 2}, {2, 3}, {3, 4}, {4, 1}};
    private static final float[] GLOW_SCALES = new float[]{10.0f, 6.0f, 3.5f};
    private static final float[] GLOW_ALPHA_SCALES = new float[]{0.06f, 0.14f, 0.25f};
    private final ModeSetting animation = new ModeSetting("Анимация", "Разлет", "Разлет", "Падение");
    private final ModeSetting shape = new ModeSetting("Форма", "Кубы", "Кубы", "Треугольники");
    private final SliderSetting count = new SliderSetting("Количество", 30.0, 5.0, 100.0, 1.0);
    private final SliderSetting size = new SliderSetting("Размер", 1.0, 0.1, 3.0, 0.1);
    private final SliderSetting speed = new SliderSetting("Скорость", 1.0, 0.1, 5.0, 0.1);
    private final List<CubeParticle> cubes = new ArrayList<CubeParticle>();
    private final List<CubeParticle> visibleCubes = new ArrayList<CubeParticle>();
    private final Random random = new Random();
    private float cr;
    private float cg;
    private float cb;
    private int updateCounter = 0;

    @Override
    public void onEnable() {
        this.cubes.clear();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.cubes.clear();
        super.onDisable();
    }

    @Subscribe
    public void onAttack(EventAttack event) {
        if (this.mc.gameRenderer != null && this.mc.gameRenderer.getCamera() != null) {
            this.applyHitImpulseFromCrosshair(this.mc.gameRenderer.getCamera());
        }
    }

    @Subscribe
    public void onWorldRender(EventWorldRender event) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        ++this.updateCounter;
        if (this.updateCounter % 2 == 0) {
            this.updateCubes();
        }
        this.renderCubes(event);
    }

    private void applyHitImpulseFromCrosshair(Camera camera) {
        if (this.cubes.isEmpty() || camera == null) {
            return;
        }
        Vec3d origin = camera.getPos();
        float yaw = (float)Math.toRadians(camera.getYaw());
        float pitch = (float)Math.toRadians(camera.getPitch());
        double dirX = -MathHelper.sin((float)yaw) * MathHelper.cos((float)pitch);
        double dirY = -MathHelper.sin((float)pitch);
        double dirZ = MathHelper.cos((float)yaw) * MathHelper.cos((float)pitch);
        CubeParticle best = null;
        double bestT = Double.MAX_VALUE;
        for (CubeParticle p2 : this.cubes) {
            double cz;
            double cy;
            double cx;
            double distSq;
            double opX = p2.x - origin.x;
            double opY = p2.y - origin.y;
            double opZ = p2.z - origin.z;
            double t2 = opX * dirX + opY * dirY + opZ * dirZ;
            if (t2 < 0.0 || t2 > 128.0 || (distSq = (p2.x - (cx = origin.x + dirX * t2)) * (p2.x - cx) + (p2.y - (cy = origin.y + dirY * t2)) * (p2.y - cy) + (p2.z - (cz = origin.z + dirZ * t2)) * (p2.z - cz)) > 1.32 || t2 >= bestT) continue;
            bestT = t2;
            best = p2;
        }
        if (best != null) {
            double force = 0.08 * this.speed.getValue();
            best.vx += (float)(dirX * force);
            best.vy += (float)(dirY * force + 0.02);
            best.vz += (float)(dirZ * force);
        }
    }

    private void updateCubes() {
        int target = (int)this.count.getValue();
        int currentSize = this.cubes.size();
        if (currentSize < target) {
            int toAdd = Math.min(target - currentSize, 5);
            for (int i2 = 0; i2 < toAdd; ++i2) {
                this.cubes.add(this.spawnCube());
            }
        } else if (currentSize > target) {
            this.cubes.subList(target, currentSize).clear();
        }
        float spd = 0.25f * (float)this.speed.getValue();
        boolean falling = this.animation.is("Падение");
        Vec3d playerPos = this.mc.player.getPos();
        double maxRSq = 900.0;
        for (int i3 = this.cubes.size() - 1; i3 >= 0; --i3) {
            CubeParticle p2 = this.cubes.get(i3);
            if (falling) {
                p2.wobblePhase += 0.06f * spd;
                p2.x += (double)(p2.vx * spd) + Math.sin(p2.wobblePhase + p2.wobbleOffset) * (double)0.0024f * (double)spd;
                p2.y += (double)(p2.vy * spd);
                p2.z += (double)(p2.vz * spd) + Math.cos(p2.wobblePhase * 0.8f + p2.wobbleOffset) * (double)0.002f * (double)spd;
                p2.vy = Math.max(p2.vy - 8.0E-5f * spd, -0.032f);
                p2.rotX += p2.rotSpeedX * 0.2f * spd;
                p2.rotY += p2.rotSpeedY * 0.2f * spd;
                p2.rotZ += p2.rotSpeedZ * 0.2f * spd;
            } else {
                p2.x += (double)(p2.vx * spd);
                p2.y += (double)(p2.vy * spd);
                p2.z += (double)(p2.vz * spd);
                p2.rotX += p2.rotSpeedX * spd;
                p2.rotY += p2.rotSpeedY * spd;
                p2.rotZ += p2.rotSpeedZ * spd;
                p2.vx *= 0.995f;
                p2.vy *= 0.995f;
                p2.vz *= 0.995f;
            }
            --p2.life;
            double dx = p2.x - playerPos.x;
            double dy = p2.y - playerPos.y;
            double dz = p2.z - playerPos.z;
            double distSq = dx * dx + dy * dy + dz * dz;
            if (p2.life > 0 && !(distSq > maxRSq) && (!falling || !(p2.y < playerPos.y - 2.5))) continue;
            this.cubes.remove(i3);
            this.cubes.add(this.spawnCube());
        }
    }

    private void renderCubes(EventWorldRender e2) {
        if (this.mc.player == null) {
            return;
        }
        MatrixStack ms = e2.getMatrixStack();
        Vec3d cam = this.mc.gameRenderer.getCamera().getPos();
        Camera camera = this.mc.gameRenderer.getCamera();
        float s2 = 0.18f * (float)this.size.getValue();
        int baseRGB = ColorProvider.getThemeColor();
        this.cr = (float)ColorProvider.red(baseRGB) / 255.0f;
        this.cg = (float)ColorProvider.green(baseRGB) / 255.0f;
        this.cb = (float)ColorProvider.blue(baseRGB) / 255.0f;
        this.visibleCubes.clear();
        float yaw = (float)Math.toRadians(camera.getYaw());
        float pitch = (float)Math.toRadians(camera.getPitch());
        double lookX = -MathHelper.sin((float)yaw) * MathHelper.cos((float)pitch);
        double lookY = -MathHelper.sin((float)pitch);
        double lookZ = MathHelper.cos((float)yaw) * MathHelper.cos((float)pitch);
        for (CubeParticle p2 : this.cubes) {
            double dx = p2.x - cam.x;
            double dy = p2.y - cam.y;
            double dz = p2.z - cam.z;
            if (dx * dx + dy * dy + dz * dz > 900.0 || dx * lookX + dy * lookY + dz * lookZ < -1.0) continue;
            p2.renderAlpha = this.getAlpha(p2);
            if (p2.renderAlpha < 0.01f) continue;
            this.visibleCubes.add(p2);
        }
        if (this.visibleCubes.isEmpty()) {
            return;
        }
        RenderSystem.enableBlend();
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.blendFuncSeparate((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE, (GlStateManager.SrcFactor)GlStateManager.SrcFactor.ZERO, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)GLOW_TEX);
        this.drawGlowBatch(ms, camera, cam, s2);
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        boolean isCubes = this.shape.is("Кубы");
        boolean isTriangles = this.shape.is("Треугольники");
        if (isCubes) {
            this.drawCubeFacesBatch(ms, cam, s2);
        }
        if (isTriangles) {
            this.drawTriangleFacesBatch(ms, cam, s2);
        }
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        if (isCubes) {
            this.drawCubeDashedEdgesBatch(ms, cam, s2);
        } else if (isTriangles) {
            this.drawTriangleDashedEdgesBatch(ms, cam, s2);
        }
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    private void drawGlowBatch(MatrixStack ms, Camera camera, Vec3d cam, float s2) {
        BufferBuilder builder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        for (CubeParticle p2 : this.visibleCubes) {
            float alpha = p2.renderAlpha;
            ms.push();
            ms.translate(p2.x - cam.x, p2.y - cam.y, p2.z - cam.z);
            ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camera.getYaw()));
            ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
            Matrix4f matrix = ms.peek().getPositionMatrix();
            for (int i2 = 0; i2 < 3; ++i2) {
                float scale = s2 * GLOW_SCALES[i2] * 1.7f;
                float a2 = alpha * GLOW_ALPHA_SCALES[i2] * 1.7f;
                float hs = scale * 0.5f;
                builder.vertex(matrix, -hs, hs, 0.0f).texture(0.0f, 1.0f).color(this.cr, this.cg, this.cb, a2);
                builder.vertex(matrix, hs, hs, 0.0f).texture(1.0f, 1.0f).color(this.cr, this.cg, this.cb, a2);
                builder.vertex(matrix, hs, -hs, 0.0f).texture(1.0f, 0.0f).color(this.cr, this.cg, this.cb, a2);
                builder.vertex(matrix, -hs, -hs, 0.0f).texture(0.0f, 0.0f).color(this.cr, this.cg, this.cb, a2);
            }
            ms.pop();
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)builder.end());
    }

    private void drawCubeFacesBatch(MatrixStack ms, Vec3d cam, float s2) {
        if (!this.hasFaceRenderableParticles()) {
            return;
        }
        BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        for (CubeParticle p2 : this.visibleCubes) {
            float alpha = p2.renderAlpha * 0.4f;
            if (alpha < 0.01f) continue;
            ms.push();
            ms.translate(p2.x - cam.x, p2.y - cam.y, p2.z - cam.z);
            ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(p2.rotX));
            ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(p2.rotY));
            ms.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(p2.rotZ));
            this.appendCubeFaces(buf, ms.peek().getPositionMatrix(), s2, alpha);
            ms.pop();
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
    }

    private void drawTriangleFacesBatch(MatrixStack ms, Vec3d cam, float s2) {
        if (!this.hasFaceRenderableParticles()) {
            return;
        }
        BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR);
        for (CubeParticle p2 : this.visibleCubes) {
            float alpha = p2.renderAlpha * 0.4f;
            if (alpha < 0.01f) continue;
            ms.push();
            ms.translate(p2.x - cam.x, p2.y - cam.y, p2.z - cam.z);
            ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(p2.rotX));
            ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(p2.rotY));
            ms.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(p2.rotZ));
            this.appendTriangleFaces(buf, ms.peek().getPositionMatrix(), s2, alpha);
            ms.pop();
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
    }

    private boolean hasFaceRenderableParticles() {
        for (CubeParticle p2 : this.visibleCubes) {
            if (!(p2.renderAlpha >= 0.025f)) continue;
            return true;
        }
        return false;
    }

    private void appendCubeFaces(BufferBuilder b2, Matrix4f m2, float s2, float a2) {
        b2.vertex(m2, -s2, -s2, s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, s2, -s2, s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, s2, s2, s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -s2, s2, s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, s2, -s2, -s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -s2, -s2, -s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -s2, s2, -s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, s2, s2, -s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -s2, s2, s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, s2, s2, s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, s2, s2, -s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -s2, s2, -s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -s2, -s2, -s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, s2, -s2, -s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, s2, -s2, s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -s2, -s2, s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, s2, -s2, s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, s2, -s2, -s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, s2, s2, -s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, s2, s2, s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -s2, -s2, -s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -s2, -s2, s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -s2, s2, s2).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -s2, s2, -s2).color(this.cr, this.cg, this.cb, a2);
    }

    private void appendTriangleFaces(BufferBuilder b2, Matrix4f m2, float s2, float a2) {
        float top = s2;
        float bottom = -s2;
        float hb = s2 * 0.866f;
        b2.vertex(m2, 0.0f, top, 0.0f).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -hb, bottom, hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, hb, bottom, hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, 0.0f, top, 0.0f).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, hb, bottom, hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, hb, bottom, -hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, 0.0f, top, 0.0f).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, hb, bottom, -hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -hb, bottom, -hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, 0.0f, top, 0.0f).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -hb, bottom, -hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -hb, bottom, hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -hb, bottom, hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, hb, bottom, hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, hb, bottom, -hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -hb, bottom, hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, hb, bottom, -hb).color(this.cr, this.cg, this.cb, a2);
        b2.vertex(m2, -hb, bottom, -hb).color(this.cr, this.cg, this.cb, a2);
    }

    private void drawCubeDashedEdgesBatch(MatrixStack ms, Vec3d cam, float s2) {
        BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        int lines = 0;
        for (CubeParticle p2 : this.visibleCubes) {
            ms.push();
            ms.translate(p2.x - cam.x, p2.y - cam.y, p2.z - cam.z);
            ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(p2.rotX));
            ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(p2.rotY));
            ms.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(p2.rotZ));
            lines += this.appendCubeDashedEdges(buf, ms.peek().getPositionMatrix(), s2, p2.renderAlpha);
            ms.pop();
        }
        if (lines > 0) {
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
        }
    }

    private void drawTriangleDashedEdgesBatch(MatrixStack ms, Vec3d cam, float s2) {
        BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        int lines = 0;
        for (CubeParticle p2 : this.visibleCubes) {
            ms.push();
            ms.translate(p2.x - cam.x, p2.y - cam.y, p2.z - cam.z);
            ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(p2.rotX));
            ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(p2.rotY));
            ms.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(p2.rotZ));
            lines += this.appendTriangleDashedEdges(buf, ms.peek().getPositionMatrix(), s2, p2.renderAlpha);
            ms.pop();
        }
        if (lines > 0) {
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
        }
    }

    private int appendCubeDashedEdges(BufferBuilder buf, Matrix4f mat, float s2, float alpha) {
        int color = Cubes.colorToInt(Math.min(1.0f, this.cr * 1.5f), Math.min(1.0f, this.cg * 1.5f), Math.min(1.0f, this.cb * 1.5f), alpha);
        float dashLen = s2 * 0.3f;
        float gapLen = s2 * 0.25f;
        int lines = 0;
        for (byte[] edge : CUBE_EDGES) {
            float x2 = (float)edge[3] * s2;
            float x1 = (float)edge[0] * s2;
            float dx = x2 - x1;
            float y2 = (float)edge[4] * s2;
            float y1 = (float)edge[1] * s2;
            float dy = y2 - y1;
            float z2 = (float)edge[5] * s2;
            float z1 = (float)edge[2] * s2;
            float dz = z2 - z1;
            float len = MathHelper.sqrt((float)(dx * dx + dy * dy + dz * dz));
            if (len < 0.001f) continue;
            float nx = dx / len;
            float ny = dy / len;
            float nz = dz / len;
            float pos = 0.0f;
            boolean drawing = true;
            while (pos < len) {
                float segLen = drawing ? dashLen : gapLen;
                float end = Math.min(pos + segLen, len);
                if (drawing) {
                    buf.vertex(mat, x1 + nx * pos, y1 + ny * pos, z1 + nz * pos).color(color);
                    buf.vertex(mat, x1 + nx * end, y1 + ny * end, z1 + nz * end).color(color);
                    ++lines;
                }
                pos = end;
                drawing = !drawing;
            }
        }
        return lines;
    }

    private int appendTriangleDashedEdges(BufferBuilder buf, Matrix4f mat, float s2, float alpha) {
        int color = Cubes.colorToInt(Math.min(1.0f, this.cr * 1.5f), Math.min(1.0f, this.cg * 1.5f), Math.min(1.0f, this.cb * 1.5f), alpha);
        float dashLen = s2 * 0.3f;
        float gapLen = s2 * 0.25f;
        float top = s2;
        float bottom = -s2;
        float hb = s2 * 0.866f;
        int lines = 0;
        for (byte[] edge : TRIANGLE_EDGES) {
            float z2;
            float dz;
            float y2;
            float dy;
            float x1 = this.trianglePointX(edge[0], hb);
            float y1 = edge[0] == 0 ? top : bottom;
            float z1 = this.trianglePointZ(edge[0], hb);
            float x2 = this.trianglePointX(edge[1], hb);
            float dx = x2 - x1;
            float len = MathHelper.sqrt((float)(dx * dx + (dy = (y2 = edge[1] == 0 ? top : bottom) - y1) * dy + (dz = (z2 = this.trianglePointZ(edge[1], hb)) - z1) * dz));
            if (len < 0.001f) continue;
            float nx = dx / len;
            float ny = dy / len;
            float nz = dz / len;
            float pos = 0.0f;
            boolean drawing = true;
            while (pos < len) {
                float segLen = drawing ? dashLen : gapLen;
                float end = Math.min(pos + segLen, len);
                if (drawing) {
                    buf.vertex(mat, x1 + nx * pos, y1 + ny * pos, z1 + nz * pos).color(color);
                    buf.vertex(mat, x1 + nx * end, y1 + ny * end, z1 + nz * end).color(color);
                    ++lines;
                }
                pos = end;
                drawing = !drawing;
            }
        }
        return lines;
    }

    private CubeParticle spawnCube() {
        float vz;
        float vy;
        float vx;
        boolean falling = this.animation.is("Падение");
        float r2 = 12.0f;
        int life = falling ? 260 + this.random.nextInt(220) : 420 + this.random.nextInt(420);
        float speedMult = (float)this.speed.getValue();
        double x2 = this.mc.player.getX() + (this.random.nextDouble() * 2.0 - 1.0) * (double)r2;
        double y2 = falling ? this.mc.player.getY() + 4.0 + this.random.nextDouble() * ((double)r2 * 0.55) : this.mc.player.getY() + 2.0 + this.random.nextDouble() * ((double)r2 * 0.8);
        double z2 = this.mc.player.getZ() + (this.random.nextDouble() * 2.0 - 1.0) * (double)r2;
        if (falling) {
            vx = (this.random.nextFloat() - 0.5f) * 0.008f * speedMult;
            vy = (-0.012f - this.random.nextFloat() * 0.012f) * speedMult;
            vz = (this.random.nextFloat() - 0.5f) * 0.008f * speedMult;
        } else {
            float yaw = this.random.nextFloat() * 360.0f;
            float vel = (0.01f + this.random.nextFloat() * 0.02f) * speedMult;
            vx = -MathHelper.sin((float)((float)Math.toRadians(yaw))) * vel;
            vz = MathHelper.cos((float)((float)Math.toRadians(yaw))) * vel;
            vy = (this.random.nextFloat() - 0.5f) * 0.01f * speedMult;
        }
        return new CubeParticle(x2, y2, z2, vx, vy, vz, this.random.nextFloat() * 360.0f, this.random.nextFloat() * 360.0f, this.random.nextFloat() * 360.0f, (this.random.nextFloat() - 0.5f) * 1.5f, (this.random.nextFloat() - 0.5f) * 1.5f, (this.random.nextFloat() - 0.5f) * 1.5f, life, (float)(this.random.nextDouble() * Math.PI * 2.0), this.random.nextFloat() * 10.0f);
    }

    private float getAlpha(CubeParticle p2) {
        float lifePct = MathHelper.clamp((float)((float)p2.life / (float)p2.maxLife), (float)0.0f, (float)1.0f);
        float fadeIn = Math.min(1.0f, (float)(p2.maxLife - p2.life) / 20.0f);
        return lifePct * fadeIn;
    }

    private float trianglePointX(int index, float hb) {
        return switch (index) {
            case 1, 4 -> -hb;
            case 2, 3 -> hb;
            default -> 0.0f;
        };
    }

    private float trianglePointZ(int index, float hb) {
        return switch (index) {
            case 1, 2 -> hb;
            case 3, 4 -> -hb;
            default -> 0.0f;
        };
    }

    private static int colorToInt(float r2, float g2, float b2, float a2) {
        return (int)(a2 * 255.0f) << 24 | (int)(r2 * 255.0f) << 16 | (int)(g2 * 255.0f) << 8 | (int)(b2 * 255.0f);
    }

    private static class CubeParticle {
        double x;
        double y;
        double z;
        float vx;
        float vy;
        float vz;
        float rotX;
        float rotY;
        float rotZ;
        float rotSpeedX;
        float rotSpeedY;
        float rotSpeedZ;
        float wobblePhase;
        float wobbleOffset;
        float renderAlpha;
        int life;
        int maxLife;

        CubeParticle(double x2, double y2, double z2, float vx, float vy, float vz, float rotX, float rotY, float rotZ, float rotSpeedX, float rotSpeedY, float rotSpeedZ, int life, float wobblePhase, float wobbleOffset) {
            this.x = x2;
            this.y = y2;
            this.z = z2;
            this.vx = vx;
            this.vy = vy;
            this.vz = vz;
            this.rotX = rotX;
            this.rotY = rotY;
            this.rotZ = rotZ;
            this.rotSpeedX = rotSpeedX;
            this.rotSpeedY = rotSpeedY;
            this.rotSpeedZ = rotSpeedZ;
            this.life = this.maxLife = life;
            this.wobblePhase = wobblePhase;
            this.wobbleOffset = wobbleOffset;
        }
    }
}

