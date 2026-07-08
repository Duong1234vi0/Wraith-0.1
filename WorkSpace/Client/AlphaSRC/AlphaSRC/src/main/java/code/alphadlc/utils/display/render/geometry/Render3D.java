/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10017
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_265
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_3532
 *  net.minecraft.class_3545
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_7833
 *  net.minecraft.class_897
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.joml.Vector3f
 *  org.joml.Vector3fc
 *  org.joml.Vector4i
 *  org.lwjgl.opengl.GL11
 */
package code.alphadlc.utils.display.render.geometry;

import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.impl.render.TargetESP;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.math.projection.Projection;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.class_10017;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_3545;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_7833;
import net.minecraft.class_897;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4i;
import org.lwjgl.opengl.GL11;

public final class Render3D
implements QuickImports {
    private static final Map<class_265, class_3545<List<class_238>, List<Line>>> SHAPE_OUTLINES = new HashMap<class_265, class_3545<List<class_238>, List<Line>>>();
    private static final Map<class_265, List<class_238>> SHAPE_BOXES = new HashMap<class_265, List<class_238>>();
    public static final List<Texture> TEXTURE_DEPTH = new ArrayList<Texture>();
    public static final List<Texture> TEXTURE = new ArrayList<Texture>();
    public static final List<Line> LINE_DEPTH = new ArrayList<Line>();
    public static final List<Line> LINE = new ArrayList<Line>();
    public static final List<Quad> QUAD_DEPTH = new ArrayList<Quad>();
    public static final List<Quad> QUAD = new ArrayList<Quad>();
    public static Matrix4f lastProjMat = new Matrix4f();
    public static class_4587.class_4665 lastWorldSpaceMatrix = new class_4587().method_23760();
    private static final class_2960 captureId = class_2960.method_60654((String)"textures/capture1.png");
    private static final class_2960 bloom = class_2960.method_60654((String)"textures/bloom.png");
    private static final class_2960 snow = class_2960.method_60654((String)"textures/show1.png");
    public static final List<Crystal> crystalList = new ArrayList<Crystal>();
    private static float espValue = 1.0f;
    private static float espSpeed = 1.0f;
    private static float prevEspValue;
    private static float circleStep;
    private static boolean flipSpeed;

    public static void drawEntity(class_1297 entity, class_243 pos, float yaw, int alpha, class_4587 matrices, float tickDelta) {
        if (!(entity instanceof class_1309)) {
            return;
        }
        class_1309 livingEntity = (class_1309)entity;
        matrices.method_22903();
        matrices.method_22904(pos.field_1352, pos.field_1351, pos.field_1350);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(yaw));
        matrices.method_22905(1.0f, 1.0f, 1.0f);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)((float)alpha / 255.0f));
        class_897 renderer = mc.method_1561().method_3953(entity);
        if (renderer != null) {
            int light = renderer.method_24088((class_1297)livingEntity, tickDelta);
            class_4597.class_4598 vertexConsumers = mc.method_22940().method_23000();
            class_10017 renderState = renderer.method_62425((class_1297)livingEntity, tickDelta);
            if (renderState != null) {
                renderer.method_3936(renderState, matrices, (class_4597)vertexConsumers, light);
            }
            vertexConsumers.method_22993();
        }
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.disableBlend();
        matrices.method_22909();
    }

    public static void onWorldRender(WorldRenderEvent e) {
        class_287 buffer;
        Set widths;
        Set identifiers;
        if (!TEXTURE.isEmpty()) {
            identifiers = TEXTURE.stream().map(texture -> texture.id).collect(Collectors.toCollection(LinkedHashSet::new));
            RenderSystem.enableBlend();
            RenderSystem.disableDepthTest();
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_CONSTANT_ALPHA);
            identifiers.forEach(id -> {
                RenderSystem.setShaderTexture((int)0, (class_2960)id);
                RenderSystem.setShader((class_10156)class_10142.field_53880);
                class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
                TEXTURE.stream().filter(texture -> texture.id.equals(id)).forEach(tex -> Render3D.quadTexture(tex.entry, buffer, tex.x, tex.y, tex.width, tex.height, tex.color));
                class_286.method_43433((class_9801)buffer.method_60800());
            });
            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
            TEXTURE.clear();
        }
        if (!TEXTURE_DEPTH.isEmpty()) {
            identifiers = TEXTURE_DEPTH.stream().map(texture -> texture.id).collect(Collectors.toCollection(LinkedHashSet::new));
            RenderSystem.enableBlend();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask((boolean)false);
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_CONSTANT_ALPHA);
            identifiers.forEach(id -> {
                RenderSystem.setShaderTexture((int)0, (class_2960)id);
                RenderSystem.setShader((class_10156)class_10142.field_53880);
                class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
                TEXTURE_DEPTH.stream().filter(texture -> texture.id.equals(id)).forEach(tex -> Render3D.quadTexture(tex.entry, buffer, tex.x, tex.y, tex.width, tex.height, tex.color));
                class_286.method_43433((class_9801)buffer.method_60800());
            });
            RenderSystem.depthMask((boolean)true);
            RenderSystem.disableBlend();
            TEXTURE_DEPTH.clear();
        }
        if (!LINE.isEmpty()) {
            GL11.glEnable((int)2881);
            widths = LINE.stream().map(line -> Float.valueOf(line.width)).collect(Collectors.toCollection(LinkedHashSet::new));
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_CONSTANT_ALPHA);
            RenderSystem.setShader((class_10156)class_10142.field_53864);
            widths.forEach(width -> {
                RenderSystem.lineWidth((float)width.floatValue());
                class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27377, class_290.field_29337);
                LINE.stream().filter(line -> line.width == width.floatValue()).forEach(line -> Render3D.vertexLine(line.entry, (class_4588)buffer, line.start.method_46409(), line.end.method_46409(), line.colorStart, line.colorEnd));
                class_286.method_43433((class_9801)buffer.method_60800());
            });
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            LINE.clear();
            GL11.glDisable((int)2881);
        }
        if (!QUAD.isEmpty()) {
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_CONSTANT_ALPHA);
            RenderSystem.setShader((class_10156)class_10142.field_53876);
            buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
            QUAD.forEach(quad -> Render3D.vertexQuad(quad.entry, (class_4588)buffer, quad.x, quad.y, quad.w, quad.z, quad.color));
            class_286.method_43433((class_9801)buffer.method_60800());
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            QUAD.clear();
        }
        if (!LINE_DEPTH.isEmpty()) {
            GL11.glEnable((int)2881);
            widths = LINE_DEPTH.stream().map(line -> Float.valueOf(line.width)).collect(Collectors.toCollection(LinkedHashSet::new));
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask((boolean)false);
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_CONSTANT_ALPHA);
            RenderSystem.setShader((class_10156)class_10142.field_53864);
            widths.forEach(width -> {
                RenderSystem.lineWidth((float)width.floatValue());
                class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27377, class_290.field_29337);
                LINE_DEPTH.stream().filter(line -> line.width == width.floatValue()).forEach(line -> Render3D.vertexLine(line.entry, (class_4588)buffer, line.start.method_46409(), line.end.method_46409(), line.colorStart, line.colorEnd));
                class_286.method_43433((class_9801)buffer.method_60800());
            });
            RenderSystem.depthMask((boolean)true);
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            LINE_DEPTH.clear();
            GL11.glDisable((int)2881);
        }
        if (!QUAD_DEPTH.isEmpty()) {
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_CONSTANT_ALPHA);
            RenderSystem.setShader((class_10156)class_10142.field_53876);
            buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
            QUAD_DEPTH.forEach(quad -> Render3D.vertexQuad(quad.entry, (class_4588)buffer, quad.x, quad.y, quad.w, quad.z, quad.color));
            class_286.method_43433((class_9801)buffer.method_60800());
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            QUAD_DEPTH.clear();
        }
    }

    public static void drawShape(class_2338 blockPos, class_265 voxelShape, int color, float width) {
        Render3D.drawShape(blockPos, voxelShape, color, width, true, false);
    }

    public static void drawShape(class_2338 blockPos, class_265 voxelShape, int color, float width, boolean fill, boolean depth) {
        if (!SHAPE_BOXES.containsKey(voxelShape)) {
            SHAPE_BOXES.put(voxelShape, voxelShape.method_1090());
        }
        for (class_238 box : SHAPE_BOXES.get(voxelShape)) {
            class_238 offsetBox = box.method_996(blockPos);
            if (!Projection.canSee(offsetBox)) continue;
            Render3D.drawBox(offsetBox, color, width, true, fill, depth);
        }
    }

    public static void drawShapeAlternative(class_2338 blockPos, class_265 voxelShape, int color, float width, boolean fill, boolean depth) {
        Render3D.drawShapeAlternative(blockPos, voxelShape, color, width, fill, true, depth);
    }

    public static void drawShapeAlternative(class_2338 blockPos, class_265 voxelShape, int color, float width, boolean fill, boolean lines, boolean depth) {
        if (!Projection.canSee(new class_238(blockPos))) {
            return;
        }
        if (!SHAPE_OUTLINES.containsKey(voxelShape)) {
            ArrayList edgeLines = new ArrayList();
            voxelShape.method_1104((minX, minY, minZ, maxX, maxY, maxZ) -> edgeLines.add(new Line(null, new class_243(minX, minY, minZ), new class_243(maxX, maxY, maxZ), 0, 0, 0.0f)));
            SHAPE_OUTLINES.put(voxelShape, (class_3545<List<class_238>, List<Line>>)new class_3545((Object)voxelShape.method_1090(), edgeLines));
        }
        class_243 vec3d = class_243.method_24954((class_2382)blockPos);
        class_3545<List<class_238>, List<Line>> pair = SHAPE_OUTLINES.get(voxelShape);
        if (fill) {
            ((List)pair.method_15442()).forEach(box -> Render3D.drawBox(box.method_997(vec3d), color, width, false, true, depth));
        }
        if (lines) {
            ((List)pair.method_15441()).forEach(line -> Render3D.drawLine(line.start.method_1019(vec3d), line.end.method_1019(vec3d), color, width, depth));
        }
    }

    public static void drawBox(class_238 box, int color, float width) {
        Render3D.drawBox(box, color, width, true, true, false);
    }

    public static void drawBox(class_238 box, int color, float width, boolean line, boolean fill, boolean depth) {
        Render3D.drawBox(null, box, color, width, line, fill, depth);
    }

    public static void drawBox(class_4587.class_4665 entry, class_238 box, int color, float width, boolean line, boolean fill, boolean depth) {
        box = box.method_1014(0.001);
        double x1 = box.field_1323;
        double y1 = box.field_1322;
        double z1 = box.field_1321;
        double x2 = box.field_1320;
        double y2 = box.field_1325;
        double z2 = box.field_1324;
        if (fill) {
            int fillColor = ColorAssist.multAlpha(color, 0.1f);
            Render3D.drawQuad(entry, new class_243(x1, y1, z1), new class_243(x2, y1, z1), new class_243(x2, y1, z2), new class_243(x1, y1, z2), fillColor, depth);
            Render3D.drawQuad(entry, new class_243(x1, y1, z1), new class_243(x1, y2, z1), new class_243(x2, y2, z1), new class_243(x2, y1, z1), fillColor, depth);
            Render3D.drawQuad(entry, new class_243(x2, y1, z1), new class_243(x2, y2, z1), new class_243(x2, y2, z2), new class_243(x2, y1, z2), fillColor, depth);
            Render3D.drawQuad(entry, new class_243(x1, y1, z2), new class_243(x2, y1, z2), new class_243(x2, y2, z2), new class_243(x1, y2, z2), fillColor, depth);
            Render3D.drawQuad(entry, new class_243(x1, y1, z1), new class_243(x1, y1, z2), new class_243(x1, y2, z2), new class_243(x1, y2, z1), fillColor, depth);
            Render3D.drawQuad(entry, new class_243(x1, y2, z1), new class_243(x1, y2, z2), new class_243(x2, y2, z2), new class_243(x2, y2, z1), fillColor, depth);
        }
        if (line) {
            Render3D.drawLine(entry, x1, y1, z1, x2, y1, z1, color, width, depth);
            Render3D.drawLine(entry, x2, y1, z1, x2, y1, z2, color, width, depth);
            Render3D.drawLine(entry, x2, y1, z2, x1, y1, z2, color, width, depth);
            Render3D.drawLine(entry, x1, y1, z2, x1, y1, z1, color, width, depth);
            Render3D.drawLine(entry, x1, y1, z2, x1, y2, z2, color, width, depth);
            Render3D.drawLine(entry, x1, y1, z1, x1, y2, z1, color, width, depth);
            Render3D.drawLine(entry, x2, y1, z2, x2, y2, z2, color, width, depth);
            Render3D.drawLine(entry, x2, y1, z1, x2, y2, z1, color, width, depth);
            Render3D.drawLine(entry, x1, y2, z1, x2, y2, z1, color, width, depth);
            Render3D.drawLine(entry, x2, y2, z1, x2, y2, z2, color, width, depth);
            Render3D.drawLine(entry, x2, y2, z2, x1, y2, z2, color, width, depth);
            Render3D.drawLine(entry, x1, y2, z2, x1, y2, z1, color, width, depth);
        }
    }

    public static void vertexLine(class_4587 matrices, class_4588 buffer, class_243 start, class_243 end, int startColor, int endColor) {
        Render3D.vertexLine(matrices.method_23760(), buffer, start.method_46409(), end.method_46409(), startColor, endColor);
    }

    public static void vertexLine(class_4587.class_4665 entry, class_4588 buffer, Vector3f start, Vector3f end, int startColor, int endColor) {
        if (entry == null) {
            entry = lastWorldSpaceMatrix;
        }
        Vector3f vec = Render3D.getNormal(start, end);
        buffer.method_61032(entry, start).method_39415(startColor).method_61959(entry, vec);
        buffer.method_61032(entry, end).method_39415(endColor).method_61959(entry, vec);
    }

    public static void vertexQuad(class_4587.class_4665 entry, class_4588 buffer, class_243 vec1, class_243 vec2, class_243 vec3, class_243 vec4, int color) {
        Render3D.vertexQuad(entry, buffer, vec1.method_46409(), vec2.method_46409(), vec3.method_46409(), vec4.method_46409(), color);
    }

    public static void vertexQuad(class_4587.class_4665 entry, class_4588 buffer, Vector3f vec1, Vector3f vec2, Vector3f vec3, Vector3f vec4, int color) {
        if (entry == null) {
            entry = lastWorldSpaceMatrix;
        }
        buffer.method_61032(entry, vec1).method_39415(color);
        buffer.method_61032(entry, vec2).method_39415(color);
        buffer.method_61032(entry, vec3).method_39415(color);
        buffer.method_61032(entry, vec4).method_39415(color);
    }

    public static void quadTexture(class_4587.class_4665 entry, class_287 buffer, float x, float y, float width, float height, Vector4i color) {
        buffer.method_56824(entry, x, y + height, 0.0f).method_22913(0.0f, 0.0f).method_39415(color.x);
        buffer.method_56824(entry, x + width, y + height, 0.0f).method_22913(0.0f, 1.0f).method_39415(color.y);
        buffer.method_56824(entry, x + width, y, 0.0f).method_22913(1.0f, 1.0f).method_39415(color.w);
        buffer.method_56824(entry, x, y, 0.0f).method_22913(1.0f, 0.0f).method_39415(color.z);
    }

    public static Vector3f getNormal(Vector3f start, Vector3f end) {
        Vector3f normal = new Vector3f((Vector3fc)start).sub((Vector3fc)end);
        float sqrt = class_3532.method_15355((float)normal.lengthSquared());
        return normal.div(sqrt);
    }

    public static void drawCube(class_1309 lastTarget, float anim, float red) {
        float size = red - anim - 0.17f;
        class_4184 camera = Render3D.mc.method_1561().field_4686;
        class_243 vec = Calculate.interpolate((class_1297)lastTarget).method_1020(camera.method_19326());
        class_4587 matrix = new class_4587();
        matrix.method_22903();
        matrix.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        matrix.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0f));
        matrix.method_22904(vec.field_1352, vec.field_1351 + lastTarget.method_5829().method_17940() / 2.0, vec.field_1350);
        matrix.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrix.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        matrix.method_22907(class_7833.field_40718.rotationDegrees(Calculate.interpolate(prevEspValue, espValue)));
        class_4587.class_4665 entry = matrix.method_23760().method_56822();
        int baseColor = TargetESP.getInstance().redOnHit.isValue() && red > 0.0f ? ColorAssist.red : TargetESP.getInstance().getColor();
        int finalColor = ColorAssist.setAlpha(baseColor, (int)((float)ColorAssist.getAlpha(baseColor) * anim));
        Render3D.drawTexture(entry, class_2960.method_60654((String)"textures/capture2.png"), -size / 2.0f, -size / 2.0f, size, size, new Vector4i(finalColor, finalColor, finalColor, finalColor), false);
        matrix.method_22909();
    }

    public static void drawTextureCube(class_1309 lastTarget, float anim, float red, class_2960 id, boolean glow) {
        float size = red - anim - 0.17f;
        class_4184 camera = Render3D.mc.method_1561().field_4686;
        class_243 vec = Calculate.interpolate((class_1297)lastTarget).method_1020(camera.method_19326());
        class_4587 matrix = new class_4587();
        matrix.method_22903();
        matrix.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        matrix.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0f));
        matrix.method_22904(vec.field_1352, vec.field_1351 + lastTarget.method_5829().method_17940() / 2.0, vec.field_1350);
        matrix.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrix.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        matrix.method_22907(class_7833.field_40718.rotationDegrees(Calculate.interpolate(prevEspValue, espValue)));
        class_4587.class_4665 entry = matrix.method_23760().method_56822();
        int baseColor = TargetESP.getInstance().redOnHit.isValue() && red > 0.0f ? ColorAssist.red : TargetESP.getInstance().getColor();
        int finalColor = ColorAssist.setAlpha(baseColor, (int)((float)ColorAssist.getAlpha(baseColor) * anim));
        if (glow) {
            float glowSize = size * 1.7f;
            int glowColor = ColorAssist.setAlpha(baseColor, (int)((float)ColorAssist.getAlpha(baseColor) * anim * 0.45f));
            Render3D.drawTexture(entry, bloom, -glowSize / 2.0f, -glowSize / 2.0f, glowSize, glowSize, new Vector4i(glowColor, glowColor, glowColor, glowColor), false);
            int solidColor = ColorAssist.setAlpha(0xFFFFFF, (int)(255.0f * anim));
            Render3D.drawTexture(entry, id, -size / 2.0f, -size / 2.0f, size, size, new Vector4i(solidColor, solidColor, solidColor, solidColor), false);
            Render3D.drawTexture(entry, id, -size / 2.0f, -size / 2.0f, size, size, new Vector4i(solidColor, solidColor, solidColor, solidColor), false);
        } else {
            Render3D.drawTexture(entry, id, -size / 2.0f, -size / 2.0f, size, size, new Vector4i(finalColor, finalColor, finalColor, finalColor), false);
        }
        matrix.method_22909();
    }

    public static void drawTargetEspCubes(class_1309 lastTarget, float anim, float red) {
        Render3D.drawOrbitingCubes(lastTarget, anim, red);
    }

    private static void drawOrbitingCubes(class_1309 lastTarget, float anim, float red) {
        if (lastTarget == null) {
            return;
        }
        class_4184 camera = Render3D.mc.field_1773.method_19418();
        class_243 targetPos = Calculate.interpolate((class_1297)lastTarget);
        long time = System.currentTimeMillis();
        int baseColor = TargetESP.getInstance().redOnHit.isValue() && red > 0.0f ? ColorAssist.red : TargetESP.getInstance().getColor();
        int fillColor = ColorAssist.setAlpha(baseColor, (int)(58.0f * anim));
        int lineColor = ColorAssist.setAlpha(baseColor, (int)(205.0f * anim));
        int glowColor = ColorAssist.setAlpha(baseColor, (int)(135.0f * anim));
        int count = 24;
        double radius = 0.4 + (double)(lastTarget.method_17681() / 2.0f) + (double)0.35f - (double)(0.35f * anim);
        double heightRange = lastTarget.method_17682();
        for (int i = 0; i < count; ++i) {
            double r1 = Math.sin((double)i * 132.12 + 4.12);
            double r3 = Math.sin((double)i * 789.34 + 9.87);
            double angleOffset = Math.PI * 2 / (double)count * (double)i;
            double angle = (double)time / 6000.0 * (Math.PI * 2) + angleOffset;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;
            double ySpeed = 1.0 + r1 * 0.2;
            double yPhase = angleOffset + r3 * 2.0;
            double yOffset = Math.sin((double)time / 9000.0 * (Math.PI * 2) * ySpeed + yPhase) * 0.45 + 0.55;
            double y = yOffset * heightRange;
            double renderX = targetPos.field_1352 + x;
            double renderY = targetPos.field_1351 + y;
            double renderZ = targetPos.field_1350 + z;
            class_4587 cubeStack = new class_4587();
            cubeStack.method_34425(lastWorldSpaceMatrix.method_23761());
            cubeStack.method_22904(renderX, renderY, renderZ);
            float pulse = 1.0f + 0.15f * (float)Math.sin((double)time / 400.0 + (double)i * 1.5);
            float cubeSize = 0.15f * pulse * (0.85f + 0.15f * anim);
            double hurtFactor = (double)red * (0.5 + 0.5 * Math.sin((double)i * 123.45));
            if (hurtFactor > 0.05) {
                cubeSize *= (float)(1.0 - hurtFactor * 0.2);
                double pushOut = hurtFactor * 0.4;
                cubeStack.method_22904(Math.cos(angle) * pushOut, 0.0, Math.sin(angle) * pushOut);
            }
            float selfRotSpeed = 12000.0f + (float)r3 * 2000.0f;
            float selfRot = (float)(time % (long)Math.abs(selfRotSpeed)) / Math.abs(selfRotSpeed) * 360.0f;
            if (i % 3 == 0) {
                cubeStack.method_22907(class_7833.field_40716.rotationDegrees(selfRot));
                cubeStack.method_22907(class_7833.field_40714.rotationDegrees(selfRot));
            } else if (i % 3 == 1) {
                cubeStack.method_22907(class_7833.field_40718.rotationDegrees(selfRot));
                cubeStack.method_22907(class_7833.field_40716.rotationDegrees(selfRot));
            } else {
                cubeStack.method_22907(class_7833.field_40714.rotationDegrees(selfRot));
                cubeStack.method_22907(class_7833.field_40718.rotationDegrees(selfRot));
            }
            class_4587.class_4665 cubeEntry = cubeStack.method_23760().method_56822();
            Render3D.drawCubeBox(cubeEntry, cubeSize, fillColor, lineColor, false);
            Render3D.drawCubeBox(cubeEntry, cubeSize * 0.92f, ColorAssist.multAlpha(fillColor, 0.28f), ColorAssist.multAlpha(lineColor, 0.7f), false);
            class_4587 glowStack = new class_4587();
            glowStack.method_34425(lastWorldSpaceMatrix.method_23761());
            glowStack.method_22904(renderX, renderY, renderZ);
            glowStack.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
            glowStack.method_22907(class_7833.field_40714.rotationDegrees(-camera.method_19329()));
            class_4587.class_4665 glowEntry = glowStack.method_23760().method_56822();
            float glowSize = cubeSize * 3.9f;
            Render3D.drawTexture(glowEntry, bloom, -glowSize / 2.0f, -glowSize / 2.0f, glowSize, glowSize, new Vector4i(glowColor, glowColor, glowColor, glowColor), false);
        }
    }

    private static void drawCubeBox(class_4587.class_4665 entry, float size, int fillColor, int lineColor, boolean depth) {
        float halfSize = size / 2.0f;
        class_243 p000 = new class_243((double)(-halfSize), (double)(-halfSize), (double)(-halfSize));
        class_243 p001 = new class_243((double)(-halfSize), (double)(-halfSize), (double)halfSize);
        class_243 p010 = new class_243((double)(-halfSize), (double)halfSize, (double)(-halfSize));
        class_243 p011 = new class_243((double)(-halfSize), (double)halfSize, (double)halfSize);
        class_243 p100 = new class_243((double)halfSize, (double)(-halfSize), (double)(-halfSize));
        class_243 p101 = new class_243((double)halfSize, (double)(-halfSize), (double)halfSize);
        class_243 p110 = new class_243((double)halfSize, (double)halfSize, (double)(-halfSize));
        class_243 p111 = new class_243((double)halfSize, (double)halfSize, (double)halfSize);
        Render3D.drawCubeFace(entry, p010, p011, p111, p110, fillColor, depth);
        Render3D.drawCubeFace(entry, p000, p100, p101, p001, fillColor, depth);
        Render3D.drawCubeFace(entry, p001, p101, p111, p011, fillColor, depth);
        Render3D.drawCubeFace(entry, p000, p010, p110, p100, fillColor, depth);
        Render3D.drawCubeFace(entry, p000, p001, p011, p010, fillColor, depth);
        Render3D.drawCubeFace(entry, p100, p110, p111, p101, fillColor, depth);
        float lineWidth = depth ? 1.2f : 1.9f;
        Render3D.drawLine(entry, p000, p001, lineColor, lineColor, lineWidth, depth);
        Render3D.drawLine(entry, p001, p011, lineColor, lineColor, lineWidth, depth);
        Render3D.drawLine(entry, p011, p010, lineColor, lineColor, lineWidth, depth);
        Render3D.drawLine(entry, p010, p000, lineColor, lineColor, lineWidth, depth);
        Render3D.drawLine(entry, p100, p101, lineColor, lineColor, lineWidth, depth);
        Render3D.drawLine(entry, p101, p111, lineColor, lineColor, lineWidth, depth);
        Render3D.drawLine(entry, p111, p110, lineColor, lineColor, lineWidth, depth);
        Render3D.drawLine(entry, p110, p100, lineColor, lineColor, lineWidth, depth);
        Render3D.drawLine(entry, p000, p100, lineColor, lineColor, lineWidth, depth);
        Render3D.drawLine(entry, p001, p101, lineColor, lineColor, lineWidth, depth);
        Render3D.drawLine(entry, p010, p110, lineColor, lineColor, lineWidth, depth);
        Render3D.drawLine(entry, p011, p111, lineColor, lineColor, lineWidth, depth);
    }

    private static void drawCubeFace(class_4587.class_4665 entry, class_243 a, class_243 b, class_243 c, class_243 d, int color, boolean depth) {
        Render3D.drawQuad(entry, a, b, c, d, color, depth);
        Render3D.drawQuad(entry, d, c, b, a, color, depth);
    }

    public static void drawCircle(class_4587 matrix, class_1309 lastTarget, float anim, float red) {
        double cs = Calculate.interpolate((double)circleStep - 0.17, (double)circleStep);
        class_243 target = Calculate.interpolate((class_1297)lastTarget);
        boolean canSee = Objects.requireNonNull(Render3D.mc.field_1724).method_6057((class_1297)lastTarget);
        GL11.glEnable((int)2881);
        if (canSee) {
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask((boolean)false);
        } else {
            RenderSystem.disableDepthTest();
        }
        RenderSystem.enableBlend();
        RenderSystem.disableCull();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_CONSTANT_ALPHA);
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27380, class_290.field_1576);
        int size = 64;
        for (int i = 0; i <= size; ++i) {
            float width = lastTarget.method_17681();
            float height = lastTarget.method_17682();
            double yAnim = Calculate.absSinAnimation(cs) * (double)height;
            double yAnim2 = Calculate.absSinAnimation(cs - 0.45) * (double)height;
            class_243 cosSin = Calculate.cosSin(i, size, width);
            class_243 nextCosSin = Calculate.cosSin(i + 1, size, width);
            int baseColor = TargetESP.getInstance().redOnHit.isValue() && red > 0.0f ? ColorAssist.red : TargetESP.getInstance().getColor();
            int color = ColorAssist.multRed(baseColor, 1.0f + red * 10.0f);
            class_243 start = target.method_1031(cosSin.field_1352, cosSin.field_1351 + yAnim, cosSin.field_1350);
            class_243 end = target.method_1031(cosSin.field_1352, cosSin.field_1351 + yAnim2, cosSin.field_1350);
            Render3D.vertexLine(matrix, (class_4588)buffer, start, end, ColorAssist.multAlpha(color, 0.76f * anim), ColorAssist.multAlpha(color, 0.0f));
            Render3D.drawLine(target.method_1031(cosSin.field_1352, cosSin.field_1351 + yAnim, cosSin.field_1350), target.method_1031(nextCosSin.field_1352, nextCosSin.field_1351 + yAnim, nextCosSin.field_1350), ColorAssist.multAlpha(color, anim), 2.0f, canSee);
        }
        class_286.method_43433((class_9801)buffer.method_60800());
        if (canSee) {
            RenderSystem.depthMask((boolean)true);
            RenderSystem.disableDepthTest();
        } else {
            RenderSystem.enableDepthTest();
        }
        GL11.glDisable((int)2881);
    }

    public static void drawGhosts(class_1309 lastTarget, float anim, float red, float speed) {
        class_4184 camera = Render3D.mc.method_1561().field_4686;
        float pitch = camera.method_19329();
        float yaw = camera.method_19330();
        class_243 vec = Calculate.interpolate((class_1297)lastTarget).method_1020(camera.method_19326());
        double iAge = Calculate.interpolate(Render3D.mc.field_1724.field_6012 - 1, Render3D.mc.field_1724.field_6012);
        double radius = 0.45 + (double)lastTarget.method_17681() * 0.4;
        float maxWidth = 0.5f;
        float minWidth = maxWidth * 0.28f;
        double trailStep = 0.13;
        int length = 92;
        float angleStep = 0.18f;
        int ghostCount = 4;
        float entityHeight = lastTarget.method_17682();
        float scaleMul = 0.82f * (0.8f + speed * 0.15f);
        int trailColor = TargetESP.getInstance().redOnHit.isValue() && red > 0.0f ? ColorAssist.red : TargetESP.getInstance().getColor();
        int colorR = ColorAssist.getRed(trailColor);
        int colorG = ColorAssist.getGreen(trailColor);
        int colorB = ColorAssist.getBlue(trailColor);
        int baseAlpha = ColorAssist.getAlpha(trailColor);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.setShaderTexture((int)0, (class_2960)bloom);
        RenderSystem.setShader((class_10156)class_10142.field_53880);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
        class_4587 matrices = new class_4587();
        for (int ghost = 0; ghost < ghostCount; ++ghost) {
            double angleOffset = Math.PI * 2 / (double)ghostCount * (double)ghost;
            for (int i = length - 1; i >= 0; --i) {
                if ((float)i > (float)length * 0.62f && (i & 1) == 1) continue;
                double angle = (double)angleStep * (iAge * (double)speed - (double)i * trailStep) + angleOffset;
                double xOffset = Math.sin(angle) * radius;
                double zOffset = Math.cos(angle) * radius;
                double yWave = Math.sin(angle * 0.65 + (double)ghost * 1.5707963267948966);
                double yOffset = (yWave + 1.0) * 0.5 * (double)entityHeight;
                float progress = 1.0f - (float)i / (float)length;
                float currentWidth = minWidth + (maxWidth - minWidth) * progress * progress;
                float scale = currentWidth * scaleMul;
                float alphaFactor = class_3532.method_15363((float)(progress * anim), (float)0.0f, (float)1.0f);
                int finalAlpha = (int)((float)baseAlpha * alphaFactor);
                if (finalAlpha <= 2) continue;
                int color = ColorAssist.getColor(colorR, colorG, colorB, finalAlpha);
                matrices.method_22903();
                matrices.method_22907(class_7833.field_40714.rotationDegrees(pitch));
                matrices.method_22907(class_7833.field_40716.rotationDegrees(yaw + 180.0f));
                matrices.method_22904(vec.field_1352 + xOffset, vec.field_1351 + yOffset, vec.field_1350 + zOffset);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(-yaw));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(pitch));
                float half = scale * 0.5f;
                Render3D.quadTexture(matrices.method_23760(), buffer, -half, -half, scale, scale, new Vector4i(color, color, color, color));
                matrices.method_22909();
            }
        }
        class_9801 built = buffer.method_60794();
        if (built != null && built.method_60822().comp_750() > 0) {
            class_286.method_43433((class_9801)built);
        }
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    public static void updateTargetEsp() {
        prevEspValue = espValue;
        espValue += espSpeed;
        if (espSpeed > 25.0f) {
            flipSpeed = true;
        }
        if (espSpeed < -25.0f) {
            flipSpeed = false;
        }
        espSpeed = flipSpeed ? espSpeed - 0.5f : espSpeed + 0.5f;
        circleStep += 0.15f;
    }

    public static void drawLine(class_4587.class_4665 entry, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, int color, float width, boolean depth) {
        Render3D.drawLine(entry, new class_243(minX, minY, minZ), new class_243(maxX, maxY, maxZ), color, color, width, depth);
    }

    public static void drawLine(class_243 start, class_243 end, int color, float width, boolean depth) {
        Render3D.drawLine(null, start, end, color, color, width, depth);
    }

    public static void drawLine(class_4587.class_4665 entry, class_243 start, class_243 end, int colorStart, int colorEnd, float width, boolean depth) {
        Line line = new Line(entry, start, end, colorStart, colorEnd, width);
        if (depth) {
            LINE_DEPTH.add(line);
        } else {
            LINE.add(line);
        }
    }

    public static void drawCircleQuad(class_287 buffer, class_4587.class_4665 entry, class_243 center, double radius, int color, int segments) {
        buffer.method_56824(entry, (float)center.field_1352, (float)center.field_1351, (float)center.field_1350).method_39415(color);
        for (int i = 0; i <= segments; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)segments;
            double dx = Math.cos(angle) * radius;
            double dz = Math.sin(angle) * radius;
            buffer.method_56824(entry, (float)(center.field_1352 + dx), (float)center.field_1351, (float)(center.field_1350 + dz)).method_39415(color);
        }
    }

    public static void drawQuad(class_243 x, class_243 y, class_243 w, class_243 z, int color, boolean depth) {
        Render3D.drawQuad(null, x, y, w, z, color, depth);
    }

    public static void drawQuad(class_4587.class_4665 entry, class_243 x, class_243 y, class_243 w, class_243 z, int color, boolean depth) {
        Quad quad = new Quad(entry, x, y, w, z, color);
        if (depth) {
            QUAD_DEPTH.add(quad);
        } else {
            QUAD.add(quad);
        }
    }

    public static void drawTexture(class_4587.class_4665 entry, class_2960 id, float x, float y, float width, float height, Vector4i color, boolean depth) {
        Texture texture = new Texture(entry, id, x, y, width, height, color);
        if (depth) {
            TEXTURE_DEPTH.add(texture);
        } else {
            TEXTURE.add(texture);
        }
    }

    private Render3D() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void setLastProjMat(Matrix4f lastProjMat) {
        Render3D.lastProjMat = lastProjMat;
    }

    public static void setLastWorldSpaceMatrix(class_4587.class_4665 lastWorldSpaceMatrix) {
        Render3D.lastWorldSpaceMatrix = lastWorldSpaceMatrix;
    }

    public record Line(class_4587.class_4665 entry, class_243 start, class_243 end, int colorStart, int colorEnd, float width) {
    }

    public record Quad(class_4587.class_4665 entry, class_243 x, class_243 y, class_243 w, class_243 z, int color) {
    }

    public record Texture(class_4587.class_4665 entry, class_2960 id, float x, float y, float width, float height, Vector4i color) {
    }

    private static class Crystal {
        private final class_1297 entity;
        private final class_243 position;
        private final class_243 rotation;
        private final float size;
        private final float rotationSpeed;

        public Crystal(class_1297 entity, class_243 position, class_243 rotation) {
            this.entity = entity;
            this.position = position;
            this.rotation = rotation;
            this.size = 0.09f;
            this.rotationSpeed = 0.5f + (float)(Math.random() * 1.5);
        }

        public void render(class_4587 ms) {
            ms.method_22903();
            ms.method_22904(this.position.field_1352, this.position.field_1351, this.position.field_1350);
            float pulsation = 1.0f + (float)(Math.sin((double)System.currentTimeMillis() / 500.0) * (double)0.1f);
            ms.method_22905(pulsation, pulsation, pulsation);
            float selfRotation = (float)(System.currentTimeMillis() % 36000L) / 100.0f * this.rotationSpeed;
            ms.method_22907(class_7833.field_40714.rotationDegrees((float)this.rotation.field_1352));
            ms.method_22907(class_7833.field_40716.rotationDegrees((float)this.rotation.field_1351 + selfRotation));
            ms.method_22907(class_7833.field_40718.rotationDegrees((float)this.rotation.field_1350));
            RenderSystem.disableCull();
            RenderSystem.enableBlend();
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShader((class_10156)class_10142.field_53876);
            int baseColor = ColorAssist.fade(90);
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
            this.drawCrystal(ms, baseColor, 0.2f, true);
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
            this.drawCrystal(ms, baseColor, 0.3f, true);
            this.drawCrystal(ms, baseColor, 0.8f, false);
            RenderSystem.disableBlend();
            RenderSystem.enableCull();
            ms.method_22909();
        }

        private void drawCrystal(class_4587 ms, int baseColor, float alpha, boolean filled) {
            class_243 v2;
            class_243 v1;
            int i;
            class_287 bufferBuilder = class_289.method_1348().method_60827(filled ? class_293.class_5596.field_27379 : class_293.class_5596.field_29344, class_290.field_1576);
            float s = this.size;
            float h_prism = this.size * 1.0f;
            float h_pyramid = this.size * 1.5f;
            int numSides = 8;
            ArrayList<class_243> topVertices = new ArrayList<class_243>();
            ArrayList<class_243> bottomVertices = new ArrayList<class_243>();
            for (int i2 = 0; i2 < numSides; ++i2) {
                float angle = (float)(Math.PI * 2 * (double)i2 / (double)numSides);
                float x = (float)((double)s * Math.cos(angle));
                float z = (float)((double)s * Math.sin(angle));
                topVertices.add(new class_243((double)x, (double)(h_prism / 2.0f), (double)z));
                bottomVertices.add(new class_243((double)x, (double)(-h_prism / 2.0f), (double)z));
            }
            class_243 vTop = new class_243(0.0, (double)(h_prism / 2.0f + h_pyramid), 0.0);
            class_243 vBottom = new class_243(0.0, (double)(-h_prism / 2.0f - h_pyramid), 0.0);
            int finalColor = ColorAssist.setAlpha(baseColor, (int)(alpha * 255.0f));
            for (i = 0; i < numSides; ++i) {
                v1 = (class_243)bottomVertices.get(i);
                v2 = (class_243)bottomVertices.get((i + 1) % numSides);
                class_243 v3 = (class_243)topVertices.get((i + 1) % numSides);
                class_243 v4 = (class_243)topVertices.get(i);
                this.drawQuad(ms, bufferBuilder, v1, v2, v3, v4, finalColor, filled);
            }
            for (i = 0; i < numSides; ++i) {
                v1 = (class_243)topVertices.get(i);
                v2 = (class_243)topVertices.get((i + 1) % numSides);
                this.drawTriangle(ms, bufferBuilder, vTop, v1, v2, finalColor, filled);
            }
            for (i = 0; i < numSides; ++i) {
                v1 = (class_243)bottomVertices.get(i);
                v2 = (class_243)bottomVertices.get((i + 1) % numSides);
                this.drawTriangle(ms, bufferBuilder, vBottom, v2, v1, finalColor, filled);
            }
            class_286.method_43433((class_9801)bufferBuilder.method_60800());
        }

        private void drawTriangle(class_4587 ms, class_287 bb, class_243 v1, class_243 v2, class_243 v3, int color, boolean filled) {
            if (filled) {
                bb.method_22918(ms.method_23760().method_23761(), (float)v1.field_1352, (float)v1.field_1351, (float)v1.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v2.field_1352, (float)v2.field_1351, (float)v2.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v3.field_1352, (float)v3.field_1351, (float)v3.field_1350).method_39415(color);
            } else {
                bb.method_22918(ms.method_23760().method_23761(), (float)v1.field_1352, (float)v1.field_1351, (float)v1.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v2.field_1352, (float)v2.field_1351, (float)v2.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v2.field_1352, (float)v2.field_1351, (float)v2.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v3.field_1352, (float)v3.field_1351, (float)v3.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v3.field_1352, (float)v3.field_1351, (float)v3.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v1.field_1352, (float)v1.field_1351, (float)v1.field_1350).method_39415(color);
            }
        }

        private void drawQuad(class_4587 ms, class_287 bb, class_243 v1, class_243 v2, class_243 v3, class_243 v4, int color, boolean filled) {
            if (filled) {
                this.drawTriangle(ms, bb, v1, v2, v3, color, true);
                this.drawTriangle(ms, bb, v1, v3, v4, color, true);
            } else {
                bb.method_22918(ms.method_23760().method_23761(), (float)v1.field_1352, (float)v1.field_1351, (float)v1.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v2.field_1352, (float)v2.field_1351, (float)v2.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v2.field_1352, (float)v2.field_1351, (float)v2.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v3.field_1352, (float)v3.field_1351, (float)v3.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v3.field_1352, (float)v3.field_1351, (float)v3.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v4.field_1352, (float)v4.field_1351, (float)v4.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v4.field_1352, (float)v4.field_1351, (float)v4.field_1350).method_39415(color);
                bb.method_22918(ms.method_23760().method_23761(), (float)v1.field_1352, (float)v1.field_1351, (float)v1.field_1350).method_39415(color);
            }
        }
    }
}
