/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10149
 *  net.minecraft.class_10156
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_265
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_5944
 *  net.minecraft.class_9801
 */
package code.alphadlc.utils.display.render.geometry;

import code.alphadlc.utils.display.atlasfont.providers.ResourceProvider;
import code.alphadlc.utils.display.render.geometry.Render3D;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_265;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_4587;
import net.minecraft.class_5944;
import net.minecraft.class_9801;

public final class ShaderBox3D {
    private static final float UV_SCALE = 4.0f;
    private static final class_10156 SHADER_SHADER = new class_10156(ResourceProvider.getShaderIdentifier("block_overlay_shader"), class_290.field_1575, class_10149.field_53930);
    private static final class_10156 PLASMA_SHADER = new class_10156(ResourceProvider.getShaderIdentifier("block_plasma"), class_290.field_1575, class_10149.field_53930);
    private static final class_10156 HEX_SHADER = new class_10156(ResourceProvider.getShaderIdentifier("block_hex"), class_290.field_1575, class_10149.field_53930);

    private ShaderBox3D() {
    }

    public static boolean drawShaderShape(class_265 shape, ShaderType shaderType, int color, float time, float opacity) {
        return ShaderBox3D.drawShaderShape(shape, null, shaderType, color, time, opacity, Render3D.lastWorldSpaceMatrix);
    }

    public static boolean drawShaderShape(class_265 shape, ShaderType shaderType, int color, float time, float opacity, class_4587.class_4665 matrixEntry) {
        return ShaderBox3D.drawShaderShape(shape, null, shaderType, color, time, opacity, matrixEntry);
    }

    public static boolean drawShaderShape(class_265 shape, class_2338 origin, ShaderType shaderType, int color, float time, float opacity, class_4587.class_4665 matrixEntry) {
        if (shape == null || shape.method_1110() || matrixEntry == null) {
            return false;
        }
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)false);
        class_5944 shader = RenderSystem.setShader((class_10156)shaderType.shaderKey);
        if (shader == null) {
            ShaderBox3D.restoreRenderState();
            return false;
        }
        if (shader.method_34582("Time") != null) {
            shader.method_34582("Time").method_1251(time);
        }
        if (shader.method_34582("Opacity") != null) {
            shader.method_34582("Opacity").method_1251(opacity);
        }
        double ox = origin != null ? (double)origin.method_10263() : 0.0;
        double oy = origin != null ? (double)origin.method_10264() : 0.0;
        double oz = origin != null ? (double)origin.method_10260() : 0.0;
        class_287 builder = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
        for (class_238 box : shape.method_1090()) {
            class_238 worldBox = box.method_989(ox, oy, oz).method_1014(0.0025);
            ShaderBox3D.addBox(builder, matrixEntry, worldBox, color);
        }
        class_286.method_43433((class_9801)builder.method_60800());
        ShaderBox3D.restoreRenderState();
        return true;
    }

    private static void restoreRenderState() {
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private static void addBox(class_287 builder, class_4587.class_4665 entry, class_238 box, int color) {
        ShaderBox3D.addQuad(builder, entry, 0, box.field_1323, box.field_1322, box.field_1321, box.field_1320, box.field_1322, box.field_1321, box.field_1320, box.field_1322, box.field_1324, box.field_1323, box.field_1322, box.field_1324, color);
        ShaderBox3D.addQuad(builder, entry, 0, box.field_1323, box.field_1325, box.field_1321, box.field_1323, box.field_1325, box.field_1324, box.field_1320, box.field_1325, box.field_1324, box.field_1320, box.field_1325, box.field_1321, color);
        ShaderBox3D.addQuad(builder, entry, 1, box.field_1323, box.field_1322, box.field_1321, box.field_1323, box.field_1325, box.field_1321, box.field_1320, box.field_1325, box.field_1321, box.field_1320, box.field_1322, box.field_1321, color);
        ShaderBox3D.addQuad(builder, entry, 1, box.field_1320, box.field_1322, box.field_1324, box.field_1320, box.field_1325, box.field_1324, box.field_1323, box.field_1325, box.field_1324, box.field_1323, box.field_1322, box.field_1324, color);
        ShaderBox3D.addQuad(builder, entry, 2, box.field_1323, box.field_1322, box.field_1324, box.field_1323, box.field_1325, box.field_1324, box.field_1323, box.field_1325, box.field_1321, box.field_1323, box.field_1322, box.field_1321, color);
        ShaderBox3D.addQuad(builder, entry, 2, box.field_1320, box.field_1322, box.field_1321, box.field_1320, box.field_1325, box.field_1321, box.field_1320, box.field_1325, box.field_1324, box.field_1320, box.field_1322, box.field_1324, color);
    }

    private static void addQuad(class_287 builder, class_4587.class_4665 entry, int plane, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double x4, double y4, double z4, int color) {
        builder.method_56824(entry, (float)x1, (float)y1, (float)z1).method_22913(ShaderBox3D.planeU(plane, x1, y1, z1), ShaderBox3D.planeV(plane, x1, y1, z1)).method_39415(color);
        builder.method_56824(entry, (float)x2, (float)y2, (float)z2).method_22913(ShaderBox3D.planeU(plane, x2, y2, z2), ShaderBox3D.planeV(plane, x2, y2, z2)).method_39415(color);
        builder.method_56824(entry, (float)x3, (float)y3, (float)z3).method_22913(ShaderBox3D.planeU(plane, x3, y3, z3), ShaderBox3D.planeV(plane, x3, y3, z3)).method_39415(color);
        builder.method_56824(entry, (float)x4, (float)y4, (float)z4).method_22913(ShaderBox3D.planeU(plane, x4, y4, z4), ShaderBox3D.planeV(plane, x4, y4, z4)).method_39415(color);
    }

    private static float planeU(int plane, double x, double y, double z) {
        if (plane == 2) {
            return (float)(z * 4.0);
        }
        return (float)(x * 4.0);
    }

    private static float planeV(int plane, double x, double y, double z) {
        if (plane == 0) {
            return (float)(z * 4.0);
        }
        return (float)(y * 4.0);
    }

    public static enum ShaderType {
        SHADER(SHADER_SHADER),
        PLASMA(PLASMA_SHADER),
        HEX(HEX_SHADER);

        private final class_10156 shaderKey;

        private ShaderType(class_10156 shaderKey) {
            this.shaderKey = shaderKey;
        }
    }
}
