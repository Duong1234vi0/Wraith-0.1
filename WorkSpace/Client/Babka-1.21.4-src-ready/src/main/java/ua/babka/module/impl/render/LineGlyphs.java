package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import ua.babka.event.EventTick;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;

@ModuleInformation(moduleName="LineGlyphs", moduleDesc="Трёхмерные линии в пространстве", moduleCategory=ModuleCategory.RENDER)
public class LineGlyphs
extends Module {
    private final SliderSetting count = new SliderSetting("Количество", 70.0, 10.0, 200.0, 5.0);
    private final SliderSetting maxDistance = new SliderSetting("Максимальная дистанция", 22.0, 8.0, 40.0, 1.0);
    private final BooleanSetting slow = new BooleanSetting("Медленно", false);
    private final BooleanSetting xray = new BooleanSetting("Видно сквозь стены", false);
    private final ModeSetting colorMode = new ModeSetting("Цвет", "Theme", "Theme", "Rainbow", "Single");
    private final List<Glyph> glyphs = new ArrayList<Glyph>();
    private final Random rand = new Random(93882L);

    @Override
    public void onEnable() {
        super.onEnable();
        this.glyphs.clear();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.glyphs.clear();
    }

    @Subscribe
    private void onTick(EventTick e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        this.glyphs.removeIf(Glyph::isDead);
        int cap = (int)this.count.getValue();
        int attempts = 8;
        while (attempts-- > 0 && this.glyphs.size() < cap) {
            int[] pos = this.spawnPos();
            if (this.mc.world.getBlockState(new BlockPos(pos[0], pos[1], pos[2])).isSolid()) continue;
            this.glyphs.add(new Glyph(pos, this.rand.nextInt(7, 13)));
        }
        this.glyphs.forEach(Glyph::tick);
    }

    @Subscribe
    private void onRender3D(EventWorldRender e2) {
        if (this.mc.player == null) {
            return;
        }
        this.drawAll(e2.getMatrixStack(), e2.getTickDelta());
    }

    private void drawAll(MatrixStack ms, float pt) {
        if (this.glyphs.isEmpty()) {
            return;
        }
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        RenderSystem.disableCull();
        if (this.xray.getValue()) {
            RenderSystem.disableDepthTest();
        } else {
            RenderSystem.enableDepthTest();
            RenderSystem.polygonOffset((float)-1.0f, (float)-10.0f);
            RenderSystem.enablePolygonOffset();
        }
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        Vec3d cam = this.mc.gameRenderer.getCamera().getPos();
        float maxDst = (float)this.maxDistance.getValue();
        int idx = 0;
        for (Glyph g2 : this.glyphs) {
            float lineW = this.calcLineWidth(g2, cam);
            if (lineW <= 0.01f) {
                ++idx;
                continue;
            }
            List<Vec3d> pts = g2.getPoints(pt);
            if (pts.size() < 2) {
                ++idx;
                continue;
            }
            RenderSystem.lineWidth((float)lineW);
            BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);
            Matrix4f mat = ms.peek().getPositionMatrix();
            int ci = idx;
            int step = 0;
            for (Vec3d p2 : pts) {
                float apc = g2.alpha() * (0.25f + (float)step / (float)pts.size() / 1.75f);
                Color c2 = this.stateColor(ci, apc);
                buf.vertex(mat, (float)(p2.x - cam.x), (float)(p2.y - cam.y), (float)(p2.z - cam.z)).color(c2.getRed(), c2.getGreen(), c2.getBlue(), c2.getAlpha());
                ci += 180;
                ++step;
            }
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
            RenderSystem.lineWidth((float)(lineW * 3.0f));
            BufferBuilder dots = Tessellator.getInstance().begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
            ci = idx;
            step = 0;
            for (Vec3d p3 : pts) {
                float apc = g2.alpha() * (0.25f + (float)step / (float)pts.size() / 1.75f);
                Color c3 = this.stateColor(ci, apc);
                dots.vertex(mat, (float)(p3.x - cam.x), (float)(p3.y - cam.y), (float)(p3.z - cam.z)).color(c3.getRed(), c3.getGreen(), c3.getBlue(), c3.getAlpha());
                ci += 180;
                ++step;
            }
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)dots.end());
            ++idx;
        }
        RenderSystem.lineWidth((float)1.0f);
        RenderSystem.disablePolygonOffset();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }

    private Color stateColor(int index, float alphaPC) {
        Color base = switch (this.colorMode.getValue()) {
            case "Rainbow" -> {
                float hue = ((float)System.currentTimeMillis() / 20.0f + (float)index * 2.0f) % 360.0f / 360.0f;
                yield Color.getHSBColor(hue, 1.0f, 1.0f);
            }
            case "Single" -> new Color(ColorProvider.getColorClient());
            default -> {
                float hue = ((float)System.currentTimeMillis() / 30.0f + (float)index * 3.0f) % 360.0f / 360.0f;
                yield Color.getHSBColor(hue, 0.7f, 1.0f);
            }
        };
        int a2 = Math.max(0, Math.min(255, (int)(alphaPC * 255.0f)));
        return new Color(base.getRed(), base.getGreen(), base.getBlue(), a2);
    }

    private float calcLineWidth(Glyph g2, Vec3d cam) {
        float maxDst;
        if (g2.nodes.isEmpty()) {
            return 0.0f;
        }
        int[] n0 = g2.nodes.getLast();
        Vec3d first = new Vec3d((double)n0[0], (double)n0[1], (double)n0[2]);
        double dst = cam.distanceTo(first);
        if (dst > (double)(maxDst = (float)this.maxDistance.getValue())) {
            return 0.0f;
        }
        return 1.0E-4f + 3.0f * (float)Math.max(0.0, Math.min(1.0, 1.0 - dst / (double)maxDst));
    }

    private int[] spawnPos() {
        double fov = ((Integer)this.mc.options.getFov().getValue()).intValue();
        double yaw = Math.toRadians(this.rand.nextFloat(-((float)fov) * 0.75f, (float)fov * 0.75f) - this.mc.player.getYaw());
        double dst = this.rand.nextInt(4, (int)this.maxDistance.getValue() - 2);
        int dx = (int)(-(Math.sin(yaw) * dst));
        int dy = this.rand.nextInt(-3, 10);
        int dz = (int)(Math.cos(yaw) * dst);
        Vec3d eye = this.mc.player.getEyePos();
        return new int[]{(int)eye.x + dx, (int)eye.y + dy, (int)eye.z + dz};
    }

    private class Glyph {
        final List<int[]> nodes = new ArrayList<int[]>();
        private final int maxLen;
        private int life;
        private int maxLife;
        private boolean growing = true;

        Glyph(int[] start, int maxLen) {
            this.maxLen = maxLen;
            this.life = this.maxLife = LineGlyphs.this.rand.nextInt(40, 80);
            this.nodes.add(start);
        }

        void tick() {
            if (this.growing) {
                if (this.nodes.size() < this.maxLen) {
                    int[] last = this.nodes.getLast();
                    int[] next = this.nextNode(last);
                    if (next != null) {
                        this.nodes.add(next);
                    } else {
                        this.growing = false;
                    }
                } else {
                    this.growing = false;
                }
            } else {
                if (!LineGlyphs.this.slow.getValue()) {
                    --this.life;
                } else if (LineGlyphs.this.rand.nextInt(3) == 0) {
                    --this.life;
                }
                if (!this.nodes.isEmpty()) {
                    this.nodes.removeFirst();
                }
            }
        }

        boolean isDead() {
            return !this.growing && (this.life <= 0 || this.nodes.size() <= 1);
        }

        float alpha() {
            if (this.growing) {
                float progress = (float)this.nodes.size() / (float)this.maxLen;
                return Math.min(1.0f, progress * 2.0f);
            }
            return Math.max(0.0f, (float)this.life / (float)this.maxLife);
        }

        List<Vec3d> getPoints(float pt) {
            ArrayList<Vec3d> result = new ArrayList<Vec3d>();
            for (int[] n2 : this.nodes) {
                result.add(new Vec3d((double)((float)n2[0] + 0.5f), (double)((float)n2[1] + 0.5f), (double)((float)n2[2] + 0.5f)));
            }
            return result;
        }

        private int[] nextNode(int[] from) {
            ArrayList<Integer> axes = new ArrayList<Integer>(List.of(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5)));
            Collections.shuffle(axes, LineGlyphs.this.rand);
            Iterator iterator = axes.iterator();
            while (iterator.hasNext()) {
                int[] nArray;
                int axis = (Integer)iterator.next();
                switch (axis) {
                    case 0: {
                        int[] nArray2 = new int[3];
                        nArray2[0] = 1;
                        nArray2[1] = 0;
                        nArray = nArray2;
                        nArray2[2] = 0;
                        break;
                    }
                    case 1: {
                        int[] nArray3 = new int[3];
                        nArray3[0] = -1;
                        nArray3[1] = 0;
                        nArray = nArray3;
                        nArray3[2] = 0;
                        break;
                    }
                    case 2: {
                        int[] nArray4 = new int[3];
                        nArray4[0] = 0;
                        nArray4[1] = 1;
                        nArray = nArray4;
                        nArray4[2] = 0;
                        break;
                    }
                    case 3: {
                        int[] nArray5 = new int[3];
                        nArray5[0] = 0;
                        nArray5[1] = -1;
                        nArray = nArray5;
                        nArray5[2] = 0;
                        break;
                    }
                    case 4: {
                        int[] nArray6 = new int[3];
                        nArray6[0] = 0;
                        nArray6[1] = 0;
                        nArray = nArray6;
                        nArray6[2] = 1;
                        break;
                    }
                    default: {
                        int[] nArray7 = new int[3];
                        nArray7[0] = 0;
                        nArray7[1] = 0;
                        nArray = nArray7;
                        nArray7[2] = -1;
                    }
                }
                int[] delta = nArray;
                int[] candidate = new int[]{from[0] + delta[0], from[1] + delta[1], from[2] + delta[2]};
                if (LineGlyphs.this.mc.world.getBlockState(new BlockPos(candidate[0], candidate[1], candidate[2])).isSolid()) continue;
                return candidate;
            }
            return null;
        }
    }
}

