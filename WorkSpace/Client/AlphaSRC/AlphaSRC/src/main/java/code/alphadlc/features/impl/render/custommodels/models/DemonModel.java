/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10055
 *  net.minecraft.class_5603
 *  net.minecraft.class_5605
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
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_591;
import net.minecraft.class_630;

public class DemonModel
extends CustomModel {
    private final class_630 head;
    private final class_630 leftArm;
    private final class_630 rightArm;
    private final class_630 leftLeg;
    private final class_630 rightLeg;

    public DemonModel() {
        class_5609 modelData = new class_5609();
        class_5610 rootData = modelData.method_32111();
        class_5610 headData = rootData.method_32117("head", class_5606.method_32108().method_32101(0, 0).method_32098(-4.0f, -4.0f, -3.0f, 8.0f, 8.0f, 8.0f, new class_5605(0.3f)), class_5603.method_32090((float)0.0f, (float)-6.0f, (float)-1.0f));
        headData.method_32117("leftHorn", class_5606.method_32108().method_32101(32, 8).method_32098(13.4346f, -5.2071f, 2.7071f, 6.0f, 2.0f, 2.0f, new class_5605(0.1f)).method_32101(0, 0).method_32098(17.4346f, -10.4071f, 2.7071f, 2.0f, 5.0f, 2.0f, new class_5605(0.1f)), class_5603.method_32091((float)-8.0f, (float)8.0f, (float)0.0f, (float)-0.3927f, (float)0.3927f, (float)-0.5236f));
        headData.method_32117("rightHorn", class_5606.method_32108().method_32101(32, 8).method_32098(-19.4346f, -5.2071f, 2.7071f, 6.0f, 2.0f, 2.0f, new class_5605(0.1f)).method_32101(0, 0).method_32098(-19.4346f, -10.4071f, 2.7071f, 2.0f, 5.0f, 2.0f, new class_5605(0.1f)), class_5603.method_32091((float)8.0f, (float)8.0f, (float)0.0f, (float)-0.3927f, (float)-0.3927f, (float)0.5236f));
        class_5610 bodyData = rootData.method_32117("body", class_5606.method_32108().method_32101(0, 16).method_32097(-4.5f, -1.7028f, 1.4696f, 8.0f, 12.0f, 4.0f), class_5603.method_32091((float)0.5f, (float)-0.1f, (float)-3.5f, (float)0.1745f, (float)0.0f, (float)0.0f));
        bodyData.method_32117("leftWing", class_5606.method_32108().method_32101(40, 12).method_32097(-7.0072f, -0.5972f, 0.7515f, 12.0f, 13.0f, 0.0f), class_5603.method_32091((float)8.25f, (float)-2.0f, (float)10.0f, (float)0.0873f, (float)-0.829f, (float)0.1745f));
        bodyData.method_32117("rightWing", class_5606.method_32108().method_32101(40, 12).method_32097(-4.9928f, -0.5972f, 0.7515f, 12.0f, 13.0f, 0.0f), class_5603.method_32091((float)-9.25f, (float)-2.0f, (float)10.0f, (float)0.0873f, (float)0.829f, (float)-0.1745f));
        rootData.method_32117("leftArm", class_5606.method_32108().method_32101(24, 16).method_32097(-1.1f, -1.05f, 0.0f, 4.0f, 14.0f, 4.0f), class_5603.method_32091((float)5.4f, (float)-1.25f, (float)-2.0f, (float)0.0f, (float)0.0f, (float)-0.2182f));
        rootData.method_32117("rightArm", class_5606.method_32108().method_32101(24, 16).method_32097(-2.9f, -1.05f, 0.0f, 4.0f, 14.0f, 4.0f), class_5603.method_32091((float)-5.4f, (float)-1.25f, (float)-2.0f, (float)0.0f, (float)0.0f, (float)0.2182f));
        class_5610 leftLegData = rootData.method_32117("leftLeg", class_5606.method_32108().method_32101(48, 22).method_32097(-3.25f, -2.25f, -1.0f, 4.0f, 9.0f, 4.0f), class_5603.method_32090((float)3.0f, (float)10.0f, (float)0.0f));
        class_5610 leftLeg1 = leftLegData.method_32117("leftLeg1", class_5606.method_32108().method_32101(34, 34).method_32097(0.95f, 4.6f, 8.0511f, 3.0f, 5.0f, 3.0f), class_5603.method_32091((float)-1.7f, (float)-0.1f, (float)-3.55f, (float)-0.5236f, (float)0.0f, (float)0.0f));
        leftLeg1.method_32117("bone2", class_5606.method_32108().method_32101(26, 0).method_32097(-0.7f, -1.15f, 9.3f, 4.0f, 2.0f, 4.0f).method_32101(40, 0).method_32097(-0.7f, -1.15f, 7.3f, 4.0f, 2.0f, 2.0f), class_5603.method_32091((float)1.4f, (float)15.0f, (float)0.25f, (float)0.5236f, (float)0.0f, (float)0.0f));
        class_5610 bone3 = leftLeg1.method_32117("bone3", class_5606.method_32108(), class_5603.method_32091((float)-1.0f, (float)0.0f, (float)-2.0f, (float)0.0f, (float)-0.0873f, (float)-0.2618f));
        bone3.method_32117("bone7", class_5606.method_32108().method_32101(16, 34).method_32097(-0.7911f, -10.1159f, 8.0029f, 4.0f, 4.0f, 5.0f).method_32101(0, 32).method_32097(-0.7911f, -15.1159f, 4.0029f, 4.0f, 9.0f, 4.0f), class_5603.method_32090((float)1.9f, (float)12.0f, (float)0.25f));
        class_5610 rightLegData = rootData.method_32117("rightLeg", class_5606.method_32108().method_32101(48, 22).method_32097(-0.75f, -2.25f, -1.0f, 4.0f, 9.0f, 4.0f), class_5603.method_32090((float)-3.0f, (float)10.0f, (float)0.0f));
        class_5610 rightLeg3 = rightLegData.method_32117("rightLeg3", class_5606.method_32108().method_32101(34, 34).method_32097(-3.95f, 4.6f, 8.0511f, 3.0f, 5.0f, 3.0f), class_5603.method_32091((float)1.7f, (float)-0.1f, (float)-3.55f, (float)-0.5236f, (float)0.0f, (float)0.0f));
        rightLeg3.method_32117("bone4", class_5606.method_32108().method_32101(26, 0).method_32097(-3.3f, -1.15f, 9.3f, 4.0f, 2.0f, 4.0f).method_32101(40, 0).method_32097(-3.3f, -1.15f, 7.3f, 4.0f, 2.0f, 2.0f), class_5603.method_32091((float)-1.4f, (float)15.0f, (float)0.25f, (float)0.5236f, (float)0.0f, (float)0.0f));
        class_5610 bone5 = rightLeg3.method_32117("bone5", class_5606.method_32108(), class_5603.method_32091((float)1.0f, (float)0.0f, (float)-2.0f, (float)0.0f, (float)0.0873f, (float)0.2618f));
        bone5.method_32117("bone6", class_5606.method_32108().method_32101(16, 34).method_32097(-3.2089f, -10.1159f, 8.0029f, 4.0f, 4.0f, 5.0f).method_32101(0, 32).method_32097(-3.2089f, -15.1159f, 4.0029f, 4.0f, 9.0f, 4.0f), class_5603.method_32090((float)-1.9f, (float)12.0f, (float)0.25f));
        this.root = rootData.method_32112(64, 64);
        this.head = this.root.method_32086("head");
        this.leftArm = this.root.method_32086("leftArm");
        this.rightArm = this.root.method_32086("rightArm");
        this.leftLeg = this.root.method_32086("leftLeg");
        this.rightLeg = this.root.method_32086("rightLeg");
    }

    @Override
    public void setAngles(class_10055 state, class_591 playerModel) {
        DemonModel.copyRotation(this.head, playerModel.field_3398);
        DemonModel.copyRotation(this.leftArm, playerModel.field_27433, -0.2182f);
        DemonModel.copyRotation(this.rightArm, playerModel.field_3401, 0.2182f);
        DemonModel.copyRotation(this.leftLeg, playerModel.field_3397);
        DemonModel.copyRotation(this.rightLeg, playerModel.field_3392);
    }
}
