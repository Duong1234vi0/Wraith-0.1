/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10149
 *  net.minecraft.class_10156
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_4587
 *  net.minecraft.class_5944
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.joml.Vector3f
 */
package code.alphadlc.utils.display.render;

import code.alphadlc.utils.display.atlasfont.providers.ResourceProvider;
import code.alphadlc.utils.display.interfaces.QuickImports;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_4587;
import net.minecraft.class_5944;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class HudShine
implements QuickImports {
    private static final float SWEEP_MS = 2200.0f;
    private static final float PAUSE_MS = 1100.0f;
    private static final class_10156 SHINE_SHADER = new class_10156(ResourceProvider.getShaderIdentifier("hud_shine"), class_290.field_1592, class_10149.field_53930);

    private HudShine() {
    }

    public static void render(class_4587 matrices, float x, float y, float w, float h, float round) {
        HudShine.render(matrices, x, y, w, h, round, 1.0f, 1.0f, 0.42f);
    }

    public static void render(class_4587 matrices, float x, float y, float w, float h, float round, float sizeScale, float speedScale, float brightness) {
        if (w <= 2.0f || h <= 2.0f) {
            return;
        }
        double cycle = 3300.0;
        double now = (double)System.currentTimeMillis() * (double)speedScale;
        double t = now % cycle;
        if (t > 2200.0) {
            return;
        }
        Matrix4f matrix = matrices.method_23760().method_23761();
        float scale = (float)window.getScaleFactor();
        Vector3f pos = matrix.transformPosition(x, y, 0.0f, new Vector3f()).mul(scale);
        Vector3f sz = matrix.getScale(new Vector3f()).mul(scale);
        float scaledWidth = w * sz.x;
        float scaledHeight = h * sz.y;
        float locationX = pos.x;
        float locationY = (float)window.getHeight() - scaledHeight - pos.y;
        float radius = Math.min(round, Math.min(w, h) * 0.5f) * sz.y;
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        class_5944 shader = RenderSystem.setShader((class_10156)SHINE_SHADER);
        shader.method_35785("size").method_1255(scaledWidth, scaledHeight);
        shader.method_35785("location").method_1255(locationX, locationY);
        shader.method_35785("radius").method_35657(radius, radius, radius, radius);
        shader.method_35785("beamWidth").method_1251(0.2f * sizeScale);
        shader.method_35785("brightness").method_1251(brightness);
        if (shader.method_34582("Time") != null) {
            shader.method_34582("Time").method_1251((float)(now % (4.0 * cycle) / 1000.0));
        }
        float m = 8.0f;
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1592);
        buffer.method_22918(matrix, x - m, y - m, 0.0f);
        buffer.method_22918(matrix, x - m, y + h + m, 0.0f);
        buffer.method_22918(matrix, x + w + m, y + h + m, 0.0f);
        buffer.method_22918(matrix, x + w + m, y - m, 0.0f);
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.defaultBlendFunc();
    }
}
