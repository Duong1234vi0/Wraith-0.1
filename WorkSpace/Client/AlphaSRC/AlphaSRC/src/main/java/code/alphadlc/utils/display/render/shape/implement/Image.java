/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_2960
 *  net.minecraft.class_4587
 *  net.minecraft.class_7833
 */
package code.alphadlc.utils.display.render.shape.implement;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.shape.Shape;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_7833;

public class Image
implements Shape,
QuickImports {
    private String texture;

    @Override
    public void render(ShapeProperties shape) {
        class_4587 matrix = shape.getMatrix();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture((int)0, (class_2960)class_2960.method_60654((String)this.texture));
        float width = shape.getWidth();
        float x = shape.getX() + width;
        float y = shape.getY();
        matrix.method_22903();
        matrix.method_46416(x, y, 0.0f);
        matrix.method_22907(class_7833.field_40718.rotationDegrees(90.0f));
        matrix.method_46416(-x, -y, 0.0f);
        drawEngine.quad(matrix.method_23760().method_23761(), x, y, shape.getHeight(), width, shape.getColor().x);
        matrix.method_22909();
        RenderSystem.disableBlend();
    }

    public Image setTexture(String texture) {
        this.texture = texture;
        return this;
    }
}
