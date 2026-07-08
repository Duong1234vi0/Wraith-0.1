/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.joml.Vector4i
 */
package code.alphadlc.utils.display.render.draw;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.draw.DrawEngine;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_4587;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Vector4i;

public class DrawEngineImpl
implements DrawEngine,
QuickImports {
    @Override
    public void quad(Matrix4f matrix4f, class_287 buffer, float x, float y, float width, float height) {
        buffer.method_22918(matrix4f, x, y, 0.0f);
        buffer.method_22918(matrix4f, x, y + height, 0.0f);
        buffer.method_22918(matrix4f, x + width, y + height, 0.0f);
        buffer.method_22918(matrix4f, x + width, y, 0.0f);
    }

    @Override
    public void quad(Matrix4f matrix4f, class_287 buffer, float x, float y, float width, float height, int color) {
        buffer.method_22918(matrix4f, x, y, 0.0f).method_39415(color);
        buffer.method_22918(matrix4f, x, y + height, 0.0f).method_39415(color);
        buffer.method_22918(matrix4f, x + width, y + height, 0.0f).method_39415(color);
        buffer.method_22918(matrix4f, x + width, y, 0.0f).method_39415(color);
    }

    @Override
    public void quad(Matrix4f matrix4f, float x, float y, float width, float height, int color) {
        RenderSystem.setShader((class_10156)class_10142.field_53880);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
        buffer.method_22918(matrix4f, x, y + height, 0.0f).method_22913(0.0f, 0.0f).method_39415(color);
        buffer.method_22918(matrix4f, x + width, y + height, 0.0f).method_22913(0.0f, 1.0f).method_39415(color);
        buffer.method_22918(matrix4f, x + width, y, 0.0f).method_22913(1.0f, 1.0f).method_39415(color);
        buffer.method_22918(matrix4f, x, y, 0.0f).method_22913(1.0f, 0.0f).method_39415(color);
        class_286.method_43433((class_9801)buffer.method_60800());
    }

    @Override
    public void quadTexture(class_4587.class_4665 entry, class_287 buffer, float x, float y, float width, float height, Vector4i color) {
        buffer.method_56824(entry, x, y + height, 0.0f).method_22913(0.0f, 0.0f).method_39415(color.x);
        buffer.method_56824(entry, x + width, y + height, 0.0f).method_22913(0.0f, 1.0f).method_39415(color.y);
        buffer.method_56824(entry, x + width, y, 0.0f).method_22913(1.0f, 1.0f).method_39415(color.w);
        buffer.method_56824(entry, x, y, 0.0f).method_22913(1.0f, 0.0f).method_39415(color.z);
    }
}
