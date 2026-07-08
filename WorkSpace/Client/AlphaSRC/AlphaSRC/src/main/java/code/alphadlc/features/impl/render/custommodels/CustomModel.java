/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10055
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_591
 *  net.minecraft.class_630
 */
package code.alphadlc.features.impl.render.custommodels;

import net.minecraft.class_10055;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_591;
import net.minecraft.class_630;

public abstract class CustomModel {
    protected class_630 root;

    public abstract void setAngles(class_10055 var1, class_591 var2);

    public void render(class_4587 matrices, class_4588 vertices, int light, int overlay) {
        this.root.method_22698(matrices, vertices, light, overlay);
    }

    protected static void copyRotation(class_630 target, class_630 source) {
        CustomModel.copyRotation(target, source, 0.0f);
    }

    protected static void copyRotation(class_630 target, class_630 source, float rollOffset) {
        target.field_3654 = source.field_3654;
        target.field_3675 = source.field_3675;
        target.field_3674 = source.field_3674 + rollOffset;
    }
}
