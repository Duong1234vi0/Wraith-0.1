/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_591
 *  net.minecraft.class_630
 *  net.minecraft.class_630$class_628
 *  net.minecraft.class_9801
 *  org.joml.Matrix3f
 *  org.joml.Matrix3fc
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector3f
 *  org.lwjgl.opengl.GL11
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.math.projection.Projection;
import code.alphadlc.utils.theme.ThemeManager;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_591;
import net.minecraft.class_630;
import net.minecraft.class_9801;
import org.joml.Matrix3f;
import org.joml.Matrix3fc;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

public class Chams
extends Module {
    private static final Map<class_630, List<class_630.class_628>> CUBOID_CACHE = new ConcurrentHashMap<class_630, List<class_630.class_628>>();
    private static Field cuboidField;
    private final RadioSetting targets = new RadioSetting("\u0426\u0435\u043b\u0438", "\u041a\u0430\u043a\u0438\u0445 \u0438\u0433\u0440\u043e\u043a\u043e\u0432 \u043f\u043e\u0434\u0441\u0432\u0435\u0447\u0438\u0432\u0430\u0442\u044c", new String[]{"\u0412\u0441\u0435", "\u0412\u0440\u0430\u0433\u0438", "\u0414\u0440\u0443\u0437\u044c\u044f"}, "\u0412\u0441\u0435");
    private final BooleanSetting useThemeColor = new BooleanSetting("\u0426\u0432\u0435\u0442 \u0442\u0435\u043c\u044b", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0446\u0432\u0435\u0442 \u0442\u0435\u043c\u044b").setValue(true);
    private final ColorSetting color = new ColorSetting("\u0426\u0432\u0435\u0442", "\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439 \u0446\u0432\u0435\u0442 chams").value(-39836).visible(() -> !this.useThemeColor.isValue());
    private final SliderSettings alpha = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043b\u0438\u043d\u0438\u0439").setValue(160.0f).range(10, 255).step(5.0f);
    private final SliderSettings fillAlpha = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u0437\u0430\u043b\u0438\u0432\u043a\u0438", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u0437\u0430\u043b\u0438\u0432\u043a\u0438").setValue(50.0f).range(5, 200).step(5.0f);
    private final SliderSettings lineWidth = new SliderSettings("\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u043b\u0438\u043d\u0438\u0439", "\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u043b\u0438\u043d\u0438\u0439").setValue(1.5f).range(0.5f, 5.0f).step(0.5f);
    private final SliderSettings layers = new SliderSettings("\u0421\u043b\u043e\u0438", "\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0441\u043b\u043e\u0435\u0432").setValue(1.0f).range(1, 3).step(1.0f);
    private final BooleanSetting throughWalls = new BooleanSetting("\u0421\u043a\u0432\u043e\u0437\u044c \u0441\u0442\u0435\u043d\u044b", "\u0420\u0438\u0441\u043e\u0432\u0430\u0442\u044c \u0441\u043a\u0432\u043e\u0437\u044c \u0441\u0442\u0435\u043d\u044b").setValue(true);
    private final BooleanSetting wave = new BooleanSetting("\u0412\u043e\u043b\u043d\u0430 \u044f\u0440\u043a\u043e\u0441\u0442\u0438", "\u041f\u0443\u043b\u044c\u0441\u0430\u0446\u0438\u044f \u044f\u0440\u043a\u043e\u0441\u0442\u0438 \u043f\u043e \u0432\u0441\u0435\u043c\u0443 \u0442\u0435\u043b\u0443").setValue(true);
    private final SliderSettings waveSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432\u043e\u043b\u043d\u044b", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u0435\u0440\u0435\u043b\u0438\u0432\u0430\u043d\u0438\u044f \u044f\u0440\u043a\u043e\u0441\u0442\u0438").setValue(1.5f).range(0.5f, 4.0f).step(0.1f).visible(() -> this.wave.isValue());

    public Chams() {
        super("Chams", ModuleCategory.ESP);
        this.setup(this.targets, this.useThemeColor, this.color, this.alpha, this.fillAlpha, this.lineWidth, this.layers, this.throughWalls, this.wave, this.waveSpeed);
    }

    public static Chams getInstance() {
        return Instance.get(Chams.class);
    }

    public boolean shouldRender(class_1657 player) {
        if (!this.isState() || player == null) {
            return false;
        }
        if (Chams.mc.field_1724 == null || player == Chams.mc.field_1724 && Chams.mc.field_1690.method_31044().method_31034()) {
            return false;
        }
        if (!Projection.isWithinRenderDistance((class_1297)player)) {
            return false;
        }
        boolean friend = FriendUtils.isFriend((class_1297)player);
        return switch (this.targets.get()) {
            case "\u0412\u0440\u0430\u0433\u0438" -> {
                if (!friend) {
                    yield true;
                }
                yield false;
            }
            case "\u0414\u0440\u0443\u0437\u044c\u044f" -> friend;
            default -> true;
        };
    }

    public void renderPlayer(class_4587 matrices, class_1657 player, class_591 model) {
        if (!this.shouldRender(player) || model == null) {
            return;
        }
        boolean friend = FriendUtils.isFriend((class_1297)player);
        int rgb = friend ? ColorAssist.getColor(0, 220, 80, 255) : (this.useThemeColor.isValue() ? ThemeManager.primaryColor.getColor() : this.color.getColor());
        float waveBrightness = this.resolveWaveBrightness();
        this.renderModel(matrices, model, rgb, Math.max(5, (int)this.fillAlpha.getValue()), Math.max(10, (int)this.alpha.getValue()), this.lineWidth.getValue(), this.throughWalls.isValue(), Math.max(1, this.layers.getInt()), waveBrightness);
    }

    public void renderCustom(class_4587 matrices, class_591 model, int rgb, float opacity) {
        if (model == null) {
            return;
        }
        int lineAlpha = Math.max(10, (int)(opacity * 200.0f));
        int fill = Math.max(5, (int)(opacity * 55.0f));
        this.renderModel(matrices, model, rgb, fill, lineAlpha, 1.5f, true, 1, 1.0f);
    }

    private void renderModel(class_4587 matrices, class_591 model, int rgb, int fillAlphaValue, int lineAlphaValue, float width, boolean throughWalls, int layersCount, float waveBrightness) {
        class_630[] parts = new class_630[]{model.field_3398, model.field_3391, model.field_3401, model.field_27433, model.field_3392, model.field_3397};
        Matrix4f[] partMatrix = new Matrix4f[parts.length];
        Matrix3f[] partNormal = new Matrix3f[parts.length];
        boolean hasGeometry = false;
        for (int p = 0; p < parts.length; ++p) {
            List<class_630.class_628> cuboids = this.getCuboids(parts[p]);
            if (cuboids == null || cuboids.isEmpty()) continue;
            matrices.method_22903();
            parts[p].method_22703(matrices);
            class_4587.class_4665 entry = matrices.method_23760();
            partMatrix[p] = new Matrix4f((Matrix4fc)entry.method_23761());
            partNormal[p] = new Matrix3f((Matrix3fc)entry.method_23762());
            matrices.method_22909();
            hasGeometry = true;
        }
        if (!hasGeometry) {
            return;
        }
        int baseR = ColorAssist.getRed(rgb);
        int baseG = ColorAssist.getGreen(rgb);
        int baseB = ColorAssist.getBlue(rgb);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableCull();
        if (throughWalls) {
            RenderSystem.disableDepthTest();
        } else {
            RenderSystem.enableDepthTest();
            RenderSystem.depthFunc((int)515);
        }
        GL11.glEnable((int)32823);
        GL11.glPolygonOffset((float)-1.0f, (float)-1.0f);
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 fill = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        for (int i = 0; i < layersCount; ++i) {
            float expand = (float)i * 0.012f;
            int fillColor = this.scaleBrightness(ColorAssist.getColor(baseR, baseG, baseB, Math.max(5, (int)((float)fillAlphaValue / (1.0f + (float)i * 1.5f)))), waveBrightness);
            for (int p = 0; p < parts.length; ++p) {
                if (partMatrix[p] == null) continue;
                this.appendPartFill(partMatrix[p], this.getCuboids(parts[p]), fillColor, expand, fill);
            }
        }
        class_286.method_43433((class_9801)fill.method_60800());
        RenderSystem.depthMask((boolean)true);
        GL11.glPolygonOffset((float)-2.0f, (float)-2.0f);
        RenderSystem.setShader((class_10156)class_10142.field_53864);
        RenderSystem.lineWidth((float)width);
        class_287 line = class_289.method_1348().method_60827(class_293.class_5596.field_27377, class_290.field_29337);
        for (int i = 0; i < layersCount; ++i) {
            float expand = (float)i * 0.012f;
            int lineColor = this.scaleBrightness(ColorAssist.getColor(baseR, baseG, baseB, Math.max(10, (int)((float)lineAlphaValue / (1.0f + (float)i * 1.5f)))), waveBrightness);
            for (int p = 0; p < parts.length; ++p) {
                if (partMatrix[p] == null) continue;
                this.appendPartLines(partMatrix[p], partNormal[p], this.getCuboids(parts[p]), lineColor, expand, line);
            }
        }
        class_286.method_43433((class_9801)line.method_60800());
        RenderSystem.lineWidth((float)1.0f);
        GL11.glDisable((int)32823);
        GL11.glPolygonOffset((float)0.0f, (float)0.0f);
        RenderSystem.depthFunc((int)515);
        if (throughWalls) {
            RenderSystem.enableDepthTest();
        }
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

    private List<class_630.class_628> getCuboids(class_630 part) {
        if (part == null) {
            return null;
        }
        List cuboids = CUBOID_CACHE.get(part);
        if (cuboids == null && cuboidField != null) {
            try {
                cuboids = (List)cuboidField.get(part);
                if (cuboids != null) {
                    CUBOID_CACHE.put(part, cuboids);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return cuboids;
    }

    private float resolveWaveBrightness() {
        if (!this.wave.isValue()) {
            return 1.0f;
        }
        float pulse = 0.5f + 0.5f * class_3532.method_15374((float)((float)System.currentTimeMillis() / 1000.0f * this.waveSpeed.getValue() * ((float)Math.PI * 2)));
        return 0.45f + 0.55f * pulse;
    }

    private void appendPartFill(Matrix4f matrix, List<class_630.class_628> cuboids, int fillColor, float expand, class_287 buffer) {
        if (cuboids == null || cuboids.isEmpty()) {
            return;
        }
        int fa = ColorAssist.getAlpha(fillColor);
        int fr = ColorAssist.getRed(fillColor);
        int fg = ColorAssist.getGreen(fillColor);
        int fb = ColorAssist.getBlue(fillColor);
        for (class_630.class_628 cuboid : cuboids) {
            float x1 = cuboid.field_3645 / 16.0f - expand;
            float y1 = cuboid.field_3644 / 16.0f - expand;
            float z1 = cuboid.field_3643 / 16.0f - expand;
            float x2 = cuboid.field_3648 / 16.0f + expand;
            float y2 = cuboid.field_3647 / 16.0f + expand;
            float z2 = cuboid.field_3646 / 16.0f + expand;
            this.vertex(buffer, matrix, x1, y1, z1, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x2, y1, z1, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x2, y1, z2, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x1, y1, z2, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x1, y2, z1, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x1, y2, z2, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x2, y2, z2, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x2, y2, z1, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x1, y1, z1, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x1, y2, z1, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x2, y2, z1, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x2, y1, z1, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x1, y1, z2, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x2, y1, z2, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x2, y2, z2, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x1, y2, z2, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x1, y1, z1, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x1, y1, z2, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x1, y2, z2, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x1, y2, z1, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x2, y1, z1, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x2, y2, z1, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x2, y2, z2, fr, fg, fb, fa);
            this.vertex(buffer, matrix, x2, y1, z2, fr, fg, fb, fa);
        }
    }

    private void appendPartLines(Matrix4f matrix, Matrix3f normalMatrix, List<class_630.class_628> cuboids, int lineColor, float expand, class_287 buffer) {
        if (cuboids == null || cuboids.isEmpty()) {
            return;
        }
        int la = ColorAssist.getAlpha(lineColor);
        int lr = ColorAssist.getRed(lineColor);
        int lg = ColorAssist.getGreen(lineColor);
        int lb = ColorAssist.getBlue(lineColor);
        for (class_630.class_628 cuboid : cuboids) {
            float x1 = cuboid.field_3645 / 16.0f - expand;
            float y1 = cuboid.field_3644 / 16.0f - expand;
            float z1 = cuboid.field_3643 / 16.0f - expand;
            float x2 = cuboid.field_3648 / 16.0f + expand;
            float y2 = cuboid.field_3647 / 16.0f + expand;
            float z2 = cuboid.field_3646 / 16.0f + expand;
            this.edge(buffer, matrix, normalMatrix, x1, y1, z1, x2, y1, z1, lr, lg, lb, la);
            this.edge(buffer, matrix, normalMatrix, x2, y1, z1, x2, y1, z2, lr, lg, lb, la);
            this.edge(buffer, matrix, normalMatrix, x2, y1, z2, x1, y1, z2, lr, lg, lb, la);
            this.edge(buffer, matrix, normalMatrix, x1, y1, z2, x1, y1, z1, lr, lg, lb, la);
            this.edge(buffer, matrix, normalMatrix, x1, y2, z1, x2, y2, z1, lr, lg, lb, la);
            this.edge(buffer, matrix, normalMatrix, x2, y2, z1, x2, y2, z2, lr, lg, lb, la);
            this.edge(buffer, matrix, normalMatrix, x2, y2, z2, x1, y2, z2, lr, lg, lb, la);
            this.edge(buffer, matrix, normalMatrix, x1, y2, z2, x1, y2, z1, lr, lg, lb, la);
            this.edge(buffer, matrix, normalMatrix, x1, y1, z1, x1, y2, z1, lr, lg, lb, la);
            this.edge(buffer, matrix, normalMatrix, x2, y1, z1, x2, y2, z1, lr, lg, lb, la);
            this.edge(buffer, matrix, normalMatrix, x2, y1, z2, x2, y2, z2, lr, lg, lb, la);
            this.edge(buffer, matrix, normalMatrix, x1, y1, z2, x1, y2, z2, lr, lg, lb, la);
        }
    }

    private void vertex(class_287 buffer, Matrix4f matrix, float x, float y, float z, int r, int g, int b, int a) {
        buffer.method_22918(matrix, x, y, z).method_1336(r, g, b, a);
    }

    private void edge(class_287 buffer, Matrix4f matrix, Matrix3f normalMatrix, float x1, float y1, float z1, float x2, float y2, float z2, int r, int g, int b, int a) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        float dz = z2 - z1;
        float length = (float)Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (length == 0.0f) {
            return;
        }
        Vector3f normal = normalMatrix.transform(dx / length, dy / length, dz / length, new Vector3f()).normalize();
        buffer.method_22918(matrix, x1, y1, z1).method_1336(r, g, b, a).method_22914(normal.x, normal.y, normal.z);
        buffer.method_22918(matrix, x2, y2, z2).method_1336(r, g, b, a).method_22914(normal.x, normal.y, normal.z);
    }

    private int scaleBrightness(int argb, float factor) {
        int a = ColorAssist.getAlpha(argb);
        int r = Math.min(255, (int)((float)ColorAssist.getRed(argb) * factor));
        int g = Math.min(255, (int)((float)ColorAssist.getGreen(argb) * factor));
        int b = Math.min(255, (int)((float)ColorAssist.getBlue(argb) * factor));
        return ColorAssist.getColor(r, g, b, a);
    }

    static {
        try {
            for (Field field : class_630.class.getDeclaredFields()) {
                if (!List.class.isAssignableFrom(field.getType())) continue;
                field.setAccessible(true);
                cuboidField = field;
                break;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}
