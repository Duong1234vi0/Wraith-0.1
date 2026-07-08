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

public class ChinchillaModel
extends CustomModel {
    private final class_630 nose;
    private final class_630 rightEar;
    private final class_630 rightWhiskers;
    private final class_630 leftEar;
    private final class_630 leftRearFoot;
    private final class_630 rightRearFoot;
    private final class_630 rightFrontLeg;
    private final class_630 rightFrontFoot;
    private final class_630 leftFrontLeg;
    private final class_630 leftFrontFoot;
    private final class_630 chin;
    private final class_630 leftWhiskers;
    private final class_630 chinHead;

    public ChinchillaModel() {
        class_5609 modelData = new class_5609();
        class_5610 rootData = modelData.method_32111();
        rootData.method_32117("back", class_5606.method_32108().method_32101(0, 14).method_32097(-3.0f, 0.3f, 3.3f, 6.0f, 6.0f, 3.0f), class_5603.method_32091((float)0.0f, (float)18.0f, (float)-2.0f, (float)0.2443461f, (float)0.0f, (float)0.0f));
        rootData.method_32117("nose", class_5606.method_32108().method_32101(46, 24).method_32097(-2.0f, -3.0f, -3.0f, 4.0f, 4.0f, 2.0f), class_5603.method_32091((float)0.0f, (float)18.0f, (float)-3.0f, (float)0.3687912f, (float)0.0f, (float)0.0f));
        rootData.method_32117("rightEar", class_5606.method_32108().method_32101(48, 8).method_32097(0.5f, -6.0f, -0.6f, 1.0f, 4.0f, 3.0f), class_5603.method_32091((float)0.0f, (float)18.0f, (float)-3.0f, (float)0.8726646f, (float)2.094395f, (float)0.0f));
        rootData.method_32117("rightWhiskers", class_5606.method_32108().method_32101(18, 19).method_32097(-5.0f, -1.4f, -4.04f, 4.0f, 3.0f, 1.0f), class_5603.method_32091((float)0.0f, (float)18.0f, (float)-3.0f, (float)-0.0872665f, (float)0.1745329f, (float)0.0f));
        rootData.method_32117("leftEar", class_5606.method_32108().method_32101(48, 0).method_32097(0.5f, -6.0f, -2.4f, 1.0f, 4.0f, 3.0f), class_5603.method_32091((float)0.0f, (float)18.0f, (float)-3.0f, (float)-0.8726646f, (float)1.047198f, (float)0.0f));
        rootData.method_32117("upperTail", class_5606.method_32108().method_32101(14, 23).method_32097(-1.5f, 1.5f, 2.6f, 3.0f, 3.0f, 4.0f), class_5603.method_32091((float)0.0f, (float)22.2f, (float)4.066667f, (float)0.7330383f, (float)0.0f, (float)0.0f));
        rootData.method_32117("chinBody", class_5606.method_32108().method_32101(0, 0).method_32097(-3.5f, -2.0f, -1.0f, 7.0f, 7.0f, 7.0f), class_5603.method_32091((float)0.0f, (float)18.0f, (float)-2.0f, (float)-0.0872665f, (float)0.0f, (float)0.0f));
        rootData.method_32117("lowerTail", class_5606.method_32108().method_32101(0, 23).method_32097(-1.0f, -1.0f, 0.0f, 2.0f, 2.0f, 5.0f), class_5603.method_32091((float)0.0f, (float)22.2f, (float)4.066667f, (float)-0.1047198f, (float)0.0f, (float)0.0f));
        rootData.method_32117("rightRearFoot", class_5606.method_32108().method_32101(28, 9).method_32097(-1.5f, 0.0f, -3.0f, 2.0f, 1.0f, 4.0f), class_5603.method_32091((float)-3.0f, (float)23.0f, (float)3.0f, (float)0.0f, (float)0.0698132f, (float)0.0f));
        rootData.method_32117("leftRearFoot", class_5606.method_32108().method_32101(28, 0).method_32097(-0.5f, 0.0f, -3.0f, 2.0f, 1.0f, 4.0f), class_5603.method_32091((float)3.0f, (float)23.0f, (float)3.0f, (float)0.0f, (float)-0.0698132f, (float)0.0f));
        rootData.method_32117("rightFrontLeg", class_5606.method_32108().method_32101(40, 9).method_32097(-1.0f, 0.0f, -1.0f, 2.0f, 4.0f, 2.0f), class_5603.method_32091((float)-3.0f, (float)20.0f, (float)-3.0f, (float)-0.1858931f, (float)0.0f, (float)0.0f));
        rootData.method_32117("rightFrontFoot", class_5606.method_32108().method_32101(28, 14).method_32097(-1.0f, 3.0f, -2.8f, 2.0f, 1.0f, 3.0f), class_5603.method_32091((float)-3.0f, (float)20.0f, (float)-3.0f, (float)0.0f, (float)-0.0698132f, (float)0.0f));
        rootData.method_32117("leftFrontLeg", class_5606.method_32108().method_32101(40, 0).method_32097(-1.0f, 0.0f, -1.0f, 2.0f, 4.0f, 2.0f), class_5603.method_32091((float)3.0f, (float)20.0f, (float)-3.0f, (float)-0.1858931f, (float)0.0f, (float)0.0f));
        rootData.method_32117("leftFrontFoot", class_5606.method_32108().method_32101(28, 5).method_32097(-1.0f, 3.0f, -2.8f, 2.0f, 1.0f, 3.0f), class_5603.method_32091((float)3.0f, (float)20.0f, (float)-3.0f, (float)0.0f, (float)0.0698132f, (float)0.0f));
        rootData.method_32117("chin", class_5606.method_32108().method_32101(46, 18).method_32097(-2.0f, -1.3f, -4.04f, 4.0f, 3.0f, 2.0f), class_5603.method_32091((float)0.0f, (float)18.0f, (float)-3.0f, (float)-0.1115358f, (float)0.0f, (float)0.0f));
        rootData.method_32117("leftWhiskers", class_5606.method_32108().method_32101(18, 14).method_32097(1.0f, -1.4f, -4.04f, 4.0f, 3.0f, 1.0f), class_5603.method_32091((float)0.0f, (float)18.0f, (float)-3.0f, (float)-0.0872665f, (float)-0.1745329f, (float)0.0f));
        rootData.method_32117("chinHead", class_5606.method_32108().method_32101(28, 21).method_32097(-2.5f, -3.0f, -2.4f, 5.0f, 5.0f, 4.0f), class_5603.method_32090((float)0.0f, (float)18.0f, (float)-3.0f));
        this.root = rootData.method_32112(64, 32);
        this.nose = this.root.method_32086("nose");
        this.rightEar = this.root.method_32086("rightEar");
        this.rightWhiskers = this.root.method_32086("rightWhiskers");
        this.leftEar = this.root.method_32086("leftEar");
        this.leftRearFoot = this.root.method_32086("leftRearFoot");
        this.rightRearFoot = this.root.method_32086("rightRearFoot");
        this.rightFrontLeg = this.root.method_32086("rightFrontLeg");
        this.rightFrontFoot = this.root.method_32086("rightFrontFoot");
        this.leftFrontLeg = this.root.method_32086("leftFrontLeg");
        this.leftFrontFoot = this.root.method_32086("leftFrontFoot");
        this.chin = this.root.method_32086("chin");
        this.leftWhiskers = this.root.method_32086("leftWhiskers");
        this.chinHead = this.root.method_32086("chinHead");
    }

    @Override
    public void setAngles(class_10055 state, class_591 playerModel) {
        this.chinHead.field_3654 = playerModel.field_3398.field_3654;
        this.chinHead.field_3675 = playerModel.field_3398.field_3675;
        this.chin.field_3654 = this.chinHead.field_3654;
        this.chin.field_3675 = this.chinHead.field_3675;
        this.nose.field_3654 = this.chinHead.field_3654;
        this.nose.field_3675 = this.chinHead.field_3675;
        this.rightEar.field_3675 = this.chinHead.field_3675 + 2.094395f;
        this.leftEar.field_3675 = this.chinHead.field_3675 + 1.047198f;
        this.leftFrontLeg.field_3654 = playerModel.field_3397.field_3654;
        this.rightFrontLeg.field_3654 = playerModel.field_3392.field_3654;
        this.leftFrontFoot.field_3654 = playerModel.field_3397.field_3654;
        this.rightFrontFoot.field_3654 = playerModel.field_3392.field_3654;
        this.leftRearFoot.field_3654 = -playerModel.field_3397.field_3654;
        this.rightRearFoot.field_3654 = -playerModel.field_3392.field_3654;
        this.leftWhiskers.field_3675 = this.chinHead.field_3675 - 0.1745329f;
        this.leftWhiskers.field_3654 = this.chinHead.field_3654 - 0.1745329f;
        this.rightWhiskers.field_3675 = this.chinHead.field_3675 + 0.1745329f;
        this.rightWhiskers.field_3654 = this.chinHead.field_3654 + 0.1745329f;
    }
}
