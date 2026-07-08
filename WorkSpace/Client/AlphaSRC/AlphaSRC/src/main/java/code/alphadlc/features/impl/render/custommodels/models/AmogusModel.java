/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10055
 *  net.minecraft.class_5603
 *  net.minecraft.class_5606
 *  net.minecraft.class_5609
 *  net.minecraft.class_5610
 *  net.minecraft.class_591
 *  net.minecraft.class_630
 */
package code.alphadlc.features.impl.render.custommodels.models;

import code.alphadlc.features.impl.render.custommodels.CustomModel;
import net.minecraft.class_10055;
import net.minecraft.class_5603;
import net.minecraft.class_5606;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_591;
import net.minecraft.class_630;

public class AmogusModel
extends CustomModel {
    private final class_630 leftLeg;
    private final class_630 rightLeg;

    public AmogusModel() {
        class_5609 modelData = new class_5609();
        class_5610 rootData = modelData.method_32111();
        rootData.method_32117("body", class_5606.method_32108().method_32101(34, 8).method_32097(-4.0f, 6.0f, -3.0f, 8.0f, 12.0f, 6.0f).method_32101(15, 10).method_32097(-3.0f, 9.0f, 3.0f, 6.0f, 8.0f, 3.0f).method_32101(26, 0).method_32097(-3.0f, 5.0f, -3.0f, 6.0f, 1.0f, 6.0f), class_5603.method_32090((float)0.0f, (float)0.0f, (float)0.0f));
        rootData.method_32117("eye", class_5606.method_32108().method_32101(0, 10).method_32097(-3.0f, 7.0f, -4.0f, 6.0f, 4.0f, 1.0f), class_5603.method_32090((float)0.0f, (float)0.0f, (float)0.0f));
        rootData.method_32117("leftLeg", class_5606.method_32108().method_32101(0, 0).method_32097(2.9f, 0.0f, -1.5f, 3.0f, 6.0f, 3.0f), class_5603.method_32090((float)-2.0f, (float)18.0f, (float)0.0f));
        rootData.method_32117("rightLeg", class_5606.method_32108().method_32101(13, 0).method_32097(-5.9f, 0.0f, -1.5f, 3.0f, 6.0f, 3.0f), class_5603.method_32090((float)2.0f, (float)18.0f, (float)0.0f));
        this.root = rootData.method_32112(64, 64);
        this.leftLeg = this.root.method_32086("leftLeg");
        this.rightLeg = this.root.method_32086("rightLeg");
    }

    @Override
    public void setAngles(class_10055 state, class_591 playerModel) {
        AmogusModel.copyRotation(this.leftLeg, playerModel.field_3397);
        AmogusModel.copyRotation(this.rightLeg, playerModel.field_3392);
    }
}
