/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_243
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.animation.util.Animation;
import code.alphadlc.utils.animation.util.Easings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_9801;
import org.joml.Matrix4f;

public class LineGlyphs
extends Module {
    static final int SPAWN_ATTEMPTS_PER_TICK = 4;
    private final List<Glyph> glyphs = new ArrayList<Glyph>();
    private final Random random = new Random(93882L);
    private final SliderSettings count = new SliderSettings("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e", "\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0433\u043b\u0438\u0444\u043e\u0432").setValue(70.0f).range(10.0f, 200.0f).step(5.0f);
    private final BooleanSetting slow = new BooleanSetting("\u041c\u0435\u0434\u043b\u0435\u043d\u043d\u0430\u044f \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u041c\u0435\u0434\u043b\u0435\u043d\u043d\u0435\u0435 \u0440\u0438\u0441\u0443\u044e\u0442\u0441\u044f \u0441\u0435\u0433\u043c\u0435\u043d\u0442\u044b \u0433\u043b\u0438\u0444\u043e\u0432");
    private final SliderSettings lineThickness = new SliderSettings("\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u043b\u0438\u043d\u0438\u0439", "\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u043b\u0438\u043d\u0438\u0439 \u0433\u043b\u0438\u0444\u043e\u0432").setValue(2.0f).range(0.5f, 5.0f).step(0.1f);
    private final SliderSettings opacity = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u0433\u043b\u0438\u0444\u043e\u0432").setValue(1.0f).range(0.1f, 1.0f).step(0.05f);
    private final SliderSettings spawnRadius = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441 \u0441\u043f\u0430\u0432\u043d\u0430", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043f\u043e\u044f\u0432\u043b\u0435\u043d\u0438\u044f \u0433\u043b\u0438\u0444\u043e\u0432 \u043e\u0442 \u0438\u0433\u0440\u043e\u043a\u0430").setValue(16.0f).range(4.0f, 48.0f).step(1.0f);
    private final SelectSetting colorMode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c \u0446\u0432\u0435\u0442\u0430", "\u0420\u0435\u0436\u0438\u043c \u043e\u043a\u0440\u0430\u0441\u043a\u0438 \u0433\u043b\u0438\u0444\u043e\u0432").value("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439", "\u0420\u0430\u0434\u0443\u0436\u043d\u044b\u0439", "\u041a\u0430\u0441\u0442\u043e\u043c").selected("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439");
    private final ColorSetting customColor = new ColorSetting("\u0426\u0432\u0435\u0442", "\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439 \u0446\u0432\u0435\u0442 \u0433\u043b\u0438\u0444\u043e\u0432").value(ColorAssist.getClientColor()).visible(() -> this.colorMode.isSelected("\u041a\u0430\u0441\u0442\u043e\u043c"));

    public LineGlyphs() {
        super("LineGlyphs", "Line Glyphs", ModuleCategory.RENDER);
        this.setup(this.count, this.slow, this.lineThickness, this.opacity, this.spawnRadius, this.colorMode, this.customColor);
    }

    public static LineGlyphs getInstance() {
        return Instance.get(LineGlyphs.class);
    }

    @Override
    public void activate() {
        super.activate();
        this.glyphs.clear();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.glyphs.clear();
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (!this.isState() || LineGlyphs.mc.field_1724 == null || LineGlyphs.mc.field_1687 == null) {
            return;
        }
        this.glyphs.removeIf(Glyph::isDead);
        int cap = this.count.getInt();
        int attempts = 4;
        while (attempts-- > 0 && this.glyphs.size() < cap) {
            this.glyphs.add(new Glyph(this.spawnPos(), this.random.nextInt(7, 13)));
        }
        this.glyphs.forEach(Glyph::tick);
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent event) {
        if (!this.isState() || LineGlyphs.mc.field_1724 == null || this.glyphs.isEmpty()) {
            return;
        }
        this.drawAll(event.getStack(), event.getPartialTicks());
    }

    private void drawAll(class_4587 matrix, float partialTicks) {
        class_243 cameraPos = LineGlyphs.mc.field_1773.method_19418().method_19326();
        Matrix4f matrix4f = matrix.method_23760().method_23761();
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        float baseOpacity = this.opacity.getValue();
        float halfWidth = this.lineThickness.getValue() * 0.04f;
        float cullRadius = this.spawnRadius.getValue() * 1.75f;
        double cullDistSq = cullRadius * cullRadius;
        int glyphIndex = 0;
        boolean hasLines = false;
        for (Glyph glyph : this.glyphs) {
            double dz;
            double dy;
            if (glyph.nodes.isEmpty()) {
                ++glyphIndex;
                continue;
            }
            int[] first = glyph.nodes.get(0);
            double dx = (double)first[0] - cameraPos.field_1352;
            if (dx * dx + (dy = (double)first[1] - cameraPos.field_1351) * dy + (dz = (double)first[2] - cameraPos.field_1350) * dz > cullDistSq) {
                ++glyphIndex;
                continue;
            }
            if (glyph.appendSegments(buffer, matrix4f, partialTicks, glyphIndex, baseOpacity, halfWidth)) {
                hasLines = true;
            }
            ++glyphIndex;
        }
        if (!hasLines) {
            return;
        }
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }

    private static void appendThickSegment(class_287 buffer, Matrix4f matrix, double x1, double y1, double z1, double x2, double y2, double z2, float halfWidth, int color) {
        double pz;
        double py;
        double plen;
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (len < 1.0E-6) {
            return;
        }
        double px = (dy /= len) * 0.0 - (dz /= len) * 1.0;
        if ((plen = Math.sqrt(px * px + (py = dz * 0.0 - (dx /= len) * 0.0) * py + (pz = dx * 1.0 - dy * 0.0) * pz)) < 1.0E-6) {
            px = dy * 0.0 - dz * 0.0;
            py = dz * 1.0 - dx * 0.0;
            pz = dx * 0.0 - dy * 1.0;
            plen = Math.sqrt(px * px + py * py + pz * pz);
        }
        px = px / plen * (double)halfWidth;
        py = py / plen * (double)halfWidth;
        pz = pz / plen * (double)halfWidth;
        float fx1 = (float)x1;
        float fy1 = (float)y1;
        float fz1 = (float)z1;
        float fx2 = (float)x2;
        float fy2 = (float)y2;
        float fz2 = (float)z2;
        float fpx = (float)px;
        float fpy = (float)py;
        float fpz = (float)pz;
        buffer.method_22918(matrix, fx1 + fpx, fy1 + fpy, fz1 + fpz).method_39415(color);
        buffer.method_22918(matrix, fx1 - fpx, fy1 - fpy, fz1 - fpz).method_39415(color);
        buffer.method_22918(matrix, fx2 - fpx, fy2 - fpy, fz2 - fpz).method_39415(color);
        buffer.method_22918(matrix, fx2 + fpx, fy2 + fpy, fz2 + fpz).method_39415(color);
    }

    private int glyphColor(int index, float alpha) {
        int rgb = switch (this.colorMode.getSelected()) {
            case "\u0420\u0430\u0434\u0443\u0436\u043d\u044b\u0439" -> ColorAssist.rainbow(20, index, 1.0f, 1.0f, 1.0f);
            case "\u041a\u0430\u0441\u0442\u043e\u043c" -> this.customColor.getColor();
            default -> ColorAssist.fade(index);
        };
        return ColorAssist.multAlpha(rgb, alpha);
    }

    private int[] spawnPos() {
        double fov = ((Integer)LineGlyphs.mc.field_1690.method_41808().method_41753()).intValue();
        double yaw = Math.toRadians(this.random.nextInt((int)((double)LineGlyphs.mc.field_1724.method_36454() - fov * 0.75), (int)((double)LineGlyphs.mc.field_1724.method_36454() + fov * 0.75)));
        float radius = this.spawnRadius.getValue();
        float minDistance = Math.max(4.0f, radius * 0.25f);
        double distance = (double)minDistance + this.random.nextDouble() * (double)(radius - minDistance);
        int dx = (int)(-(Math.sin(yaw) * distance));
        int dy = this.random.nextInt(0, 12);
        int dz = (int)(Math.cos(yaw) * distance);
        class_243 eye = LineGlyphs.mc.field_1724.method_33571();
        return new int[]{(int)eye.field_1352 + dx, (int)eye.field_1351 + dy, (int)eye.field_1350 + dz};
    }

    private int[] randomDirection() {
        return new int[]{this.random.nextInt(0, 4) * 90, this.random.nextInt(-1, 2) * 90};
    }

    private int[] nextDirection(int[] previous) {
        int nextB = switch (previous[1]) {
            case 0 -> {
                if (this.random.nextBoolean()) {
                    yield 90;
                }
                yield -90;
            }
            case 90 -> {
                if (this.random.nextBoolean()) {
                    yield 0;
                }
                yield -90;
            }
            default -> this.random.nextBoolean() ? 0 : 90;
        };
        int nextA = (previous[0] + 90 * (1 + this.random.nextInt(3))) % 360;
        if (nextA == previous[0]) {
            nextA = (nextA + 90) % 360;
        }
        return new int[]{nextA, nextB};
    }

    private int[] step(int[] position, int[] direction, int radius) {
        double yaw = Math.toRadians(direction[0]);
        double pitch = Math.toRadians(direction[1]);
        double horizontalRadius = radius;
        int deltaY = (int)(Math.sin(pitch) * horizontalRadius);
        if (pitch != 0.0) {
            horizontalRadius = 0.0;
        }
        int deltaX = (int)(-(Math.sin(yaw) * horizontalRadius));
        int deltaZ = (int)(Math.cos(yaw) * horizontalRadius);
        return new int[]{position[0] + deltaX, position[1] + deltaY, position[2] + deltaZ};
    }

    private class Glyph {
        final List<int[]> nodes = new ArrayList<int[]>();
        final double[] pointScratch = new double[3];
        final double[] prevScratch = new double[3];
        int[] direction;
        int stepsLeft;
        int ticksLeft;
        int lastSet;
        boolean dying;
        final Animation animation = new Animation();

        Glyph(int[] spawn, int steps) {
            this.nodes.add(spawn);
            this.direction = LineGlyphs.this.randomDirection();
            this.stepsLeft = steps;
            this.animation.set(0.0);
            this.animation.run(1.0, 0.6, Easings.SINE_OUT);
        }

        void tick() {
            this.animation.update();
            if (this.stepsLeft == 0) {
                if (!this.dying) {
                    this.dying = true;
                    this.animation.run(0.0, 0.4, Easings.SINE_IN);
                }
                return;
            }
            if (this.ticksLeft > 0) {
                this.ticksLeft -= LineGlyphs.this.slow.isValue() ? 1 : 2;
                if (this.ticksLeft < 0) {
                    this.ticksLeft = 0;
                }
                return;
            }
            this.direction = LineGlyphs.this.nextDirection(this.direction);
            this.lastSet = this.ticksLeft = LineGlyphs.this.random.nextInt(0, 3);
            this.nodes.add(LineGlyphs.this.step(this.nodes.get(this.nodes.size() - 1), this.direction, Math.max(1, this.ticksLeft)));
            --this.stepsLeft;
        }

        float alpha() {
            return this.animation.get();
        }

        boolean isDead() {
            return this.stepsLeft == 0 && this.animation.get() < 0.01f && this.animation.isFinished();
        }

        boolean appendSegments(class_287 buffer, Matrix4f matrix4f, float partialTicks, int colorIndex, float baseOpacity, float halfWidth) {
            int size = this.nodes.size();
            if (size < 2) {
                return false;
            }
            boolean wrote = false;
            this.getNodeCoord(0, partialTicks, this.prevScratch);
            for (int i = 1; i < size; ++i) {
                this.getNodeCoord(i, partialTicks, this.pointScratch);
                float alpha = this.alpha() * baseOpacity * (0.25f + (float)i / (float)size / 1.75f);
                int color = LineGlyphs.this.glyphColor(colorIndex + i * 180, alpha);
                LineGlyphs.appendThickSegment(buffer, matrix4f, this.prevScratch[0], this.prevScratch[1], this.prevScratch[2], this.pointScratch[0], this.pointScratch[1], this.pointScratch[2], halfWidth, color);
                this.prevScratch[0] = this.pointScratch[0];
                this.prevScratch[1] = this.pointScratch[1];
                this.prevScratch[2] = this.pointScratch[2];
                wrote = true;
            }
            return wrote;
        }

        void getNodeCoord(int index, float partialTicks, double[] out) {
            int[] node = this.nodes.get(index);
            double x = node[0];
            double y = node[1];
            double z = node[2];
            if (index == this.nodes.size() - 1 && this.nodes.size() >= 2) {
                int[] previous = this.nodes.get(index - 1);
                float advance = this.lastSet > 0 ? Math.max(0.0f, Math.min(1.0f, 1.0f - (float)this.ticksLeft / (float)this.lastSet)) : 1.0f;
                x = class_3532.method_16436((double)advance, (double)previous[0], (double)x);
                y = class_3532.method_16436((double)advance, (double)previous[1], (double)y);
                z = class_3532.method_16436((double)advance, (double)previous[2], (double)z);
            }
            out[0] = x;
            out[1] = y;
            out[2] = z;
        }
    }
}
