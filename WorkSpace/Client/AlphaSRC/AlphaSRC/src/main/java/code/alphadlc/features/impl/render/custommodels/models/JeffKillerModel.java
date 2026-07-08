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

public class JeffKillerModel
extends CustomModel {
    private final class_630 head;
    private final class_630 leftArm;
    private final class_630 rightLeg;
    private final class_630 leftLeg;

    public JeffKillerModel() {
        class_5609 modelData = new class_5609();
        class_5610 rootData = modelData.method_32111();
        rootData.method_32117("head", class_5606.method_32108().method_32101(0, 0).method_32097(-5.0f, -9.75f, -5.0f, 10.0f, 10.0f, 8.0f), class_5603.method_32090((float)0.0f, (float)1.0f, (float)-3.0f));
        rootData.method_32117("body", class_5606.method_32108().method_32101(0, 18).method_32097(-4.0f, -23.1486f, 0.5266f, 8.0f, 14.0f, 4.0f), class_5603.method_32091((float)0.0f, (float)24.0f, (float)0.0f, (float)0.2618f, (float)0.0f, (float)0.0f));
        rootData.method_32117("rightArm", class_5606.method_32108().method_32101(36, 0).method_32097(-7.0f, -4.5f, -23.25f, 3.0f, 12.0f, 3.0f).method_32101(16, 36).method_32097(-6.0f, 5.75f, -25.25f, 1.0f, 2.0f, 5.0f).method_32101(31, 15).method_32097(-6.0f, 5.75f, -30.25f, 1.0f, 2.0f, 5.0f).method_32101(0, 0).method_32097(-6.0f, 8.75f, -28.25f, 1.0f, 1.0f, 3.0f).method_32101(24, 18).method_32097(-6.0f, 7.75f, -29.25f, 1.0f, 1.0f, 5.0f), class_5603.method_32091((float)0.0f, (float)24.0f, (float)0.0f, (float)-1.309f, (float)0.0f, (float)0.0f));
        rootData.method_32117("leftArm", class_5606.method_32108().method_32101(37, 37).method_32097(0.0f, -1.75f, -1.5f, 3.0f, 12.0f, 3.0f), class_5603.method_32090((float)4.0f, (float)3.0f, (float)-3.0f));
        rootData.method_32117("rightLeg", class_5606.method_32108().method_32101(0, 36).method_32097(-2.0f, 0.0f, -2.0f, 4.0f, 10.0f, 4.0f), class_5603.method_32090((float)-2.0f, (float)14.0f, (float)0.0f));
        rootData.method_32117("leftLeg", class_5606.method_32108().method_32101(24, 24).method_32097(-2.0f, 0.0f, -2.0f, 4.0f, 10.0f, 4.0f), class_5603.method_32090((float)2.0f, (float)14.0f, (float)0.0f));
        this.root = rootData.method_32112(64, 64);
        this.head = this.root.method_32086("head");
        this.leftArm = this.root.method_32086("leftArm");
        this.rightLeg = this.root.method_32086("rightLeg");
        this.leftLeg = this.root.method_32086("leftLeg");
    }

    @Override
    public void setAngles(class_10055 state, class_591 playerModel) {
        JeffKillerModel.copyRotation(this.head, playerModel.field_3398);
        JeffKillerModel.copyRotation(this.leftArm, playerModel.field_27433);
        JeffKillerModel.copyRotation(this.rightLeg, playerModel.field_3392);
        JeffKillerModel.copyRotation(this.leftLeg, playerModel.field_3397);
    }
}
