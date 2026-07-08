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

public class FredBearModel
extends CustomModel {
    private final class_630 fredHead;
    private final class_630 armRight;
    private final class_630 armLeft;
    private final class_630 legRight;
    private final class_630 legLeft;

    public FredBearModel() {
        class_5609 modelData = new class_5609();
        class_5610 rootData = modelData.method_32111();
        class_5610 fredBodyData = rootData.method_32117("fredBody", class_5606.method_32108().method_32101(0, 0).method_32097(-1.0f, -14.0f, -1.0f, 2.0f, 24.0f, 2.0f), class_5603.method_32090((float)0.0f, (float)-9.0f, (float)0.0f));
        fredBodyData.method_32117("torso", class_5606.method_32108().method_32101(8, 0).method_32097(-6.0f, -9.0f, -4.0f, 12.0f, 18.0f, 8.0f), class_5603.method_32091((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0175f, (float)0.0f, (float)0.0f));
        class_5610 armRightData = fredBodyData.method_32117("armRight", class_5606.method_32108().method_32101(48, 0).method_32097(-1.0f, 0.0f, -1.0f, 2.0f, 10.0f, 2.0f), class_5603.method_32091((float)-6.5f, (float)-8.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.2618f));
        armRightData.method_32117("armRightpad", class_5606.method_32108().method_32101(70, 10).method_32097(-2.5f, 0.0f, -2.5f, 5.0f, 9.0f, 5.0f), class_5603.method_32090((float)0.0f, (float)0.5f, (float)0.0f));
        class_5610 armRight2 = armRightData.method_32117("armRight2", class_5606.method_32108().method_32101(90, 20).method_32097(-1.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f), class_5603.method_32091((float)0.0f, (float)9.6f, (float)0.0f, (float)-0.1745f, (float)0.0f, (float)0.0f));
        armRight2.method_32117("armRightpad2", class_5606.method_32108().method_32101(0, 26).method_32097(-2.5f, 0.0f, -2.5f, 5.0f, 7.0f, 5.0f), class_5603.method_32090((float)0.0f, (float)0.5f, (float)0.0f));
        armRight2.method_32117("handRight", class_5606.method_32108().method_32101(20, 26).method_32097(-2.0f, 0.0f, -2.5f, 4.0f, 4.0f, 5.0f), class_5603.method_32091((float)0.0f, (float)8.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)-0.0524f));
        class_5610 armLeftData = fredBodyData.method_32117("armLeft", class_5606.method_32108().method_32101(62, 10).method_32097(-1.0f, 0.0f, -1.0f, 2.0f, 10.0f, 2.0f), class_5603.method_32091((float)6.5f, (float)-8.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)-0.2618f));
        armLeftData.method_32117("armLeftpad", class_5606.method_32108().method_32101(38, 54).method_32097(-2.5f, 0.0f, -2.5f, 5.0f, 9.0f, 5.0f), class_5603.method_32090((float)0.0f, (float)0.5f, (float)0.0f));
        class_5610 armLeft2 = armLeftData.method_32117("armLeft2", class_5606.method_32108().method_32101(90, 48).method_32097(-1.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f), class_5603.method_32091((float)0.0f, (float)9.6f, (float)0.0f, (float)-0.1745f, (float)0.0f, (float)0.0f));
        armLeft2.method_32117("armLeftpad2", class_5606.method_32108().method_32101(0, 58).method_32097(-2.5f, 0.0f, -2.5f, 5.0f, 7.0f, 5.0f), class_5603.method_32090((float)0.0f, (float)0.5f, (float)0.0f));
        armLeft2.method_32117("handLeft", class_5606.method_32108().method_32101(58, 56).method_32097(-1.0f, 0.0f, -2.5f, 4.0f, 4.0f, 5.0f), class_5603.method_32091((float)0.0f, (float)8.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0524f));
        fredBodyData.method_32117("crotch", class_5606.method_32108().method_32101(56, 0).method_32097(-5.5f, 0.0f, -3.5f, 11.0f, 3.0f, 7.0f), class_5603.method_32090((float)0.0f, (float)9.5f, (float)0.0f));
        class_5610 legRightData = fredBodyData.method_32117("legRight", class_5606.method_32108().method_32101(90, 8).method_32097(-1.0f, 0.0f, -1.0f, 2.0f, 10.0f, 2.0f), class_5603.method_32090((float)-3.3f, (float)12.5f, (float)0.0f));
        legRightData.method_32117("legRightpad", class_5606.method_32108().method_32101(73, 33).method_32097(-3.0f, 0.0f, -3.0f, 6.0f, 9.0f, 6.0f), class_5603.method_32090((float)0.0f, (float)0.5f, (float)0.0f));
        class_5610 legRight2 = legRightData.method_32117("legRight2", class_5606.method_32108().method_32101(20, 35).method_32097(-1.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f), class_5603.method_32091((float)0.0f, (float)9.6f, (float)0.0f, (float)0.0349f, (float)0.0f, (float)0.0f));
        legRight2.method_32117("legRightpad2", class_5606.method_32108().method_32101(0, 39).method_32097(-2.5f, 0.0f, -3.0f, 5.0f, 7.0f, 6.0f), class_5603.method_32090((float)0.0f, (float)0.5f, (float)0.0f));
        legRight2.method_32117("footRight", class_5606.method_32108().method_32101(22, 39).method_32097(-2.5f, 0.0f, -6.0f, 5.0f, 3.0f, 8.0f), class_5603.method_32091((float)0.0f, (float)8.0f, (float)0.0f, (float)-0.0349f, (float)0.0f, (float)0.0f));
        class_5610 legLeftData = fredBodyData.method_32117("legLeft", class_5606.method_32108().method_32101(54, 10).method_32097(-1.0f, 0.0f, -1.0f, 2.0f, 10.0f, 2.0f), class_5603.method_32090((float)3.3f, (float)12.5f, (float)0.0f));
        legLeftData.method_32117("legLeftpad", class_5606.method_32108().method_32101(48, 39).method_32097(-3.0f, 0.0f, -3.0f, 6.0f, 9.0f, 6.0f), class_5603.method_32090((float)0.0f, (float)0.5f, (float)0.0f));
        class_5610 legLeft2 = legLeftData.method_32117("legLeft2", class_5606.method_32108().method_32101(72, 48).method_32097(-1.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f), class_5603.method_32091((float)0.0f, (float)9.6f, (float)0.0f, (float)0.0349f, (float)0.0f, (float)0.0f));
        legLeft2.method_32117("legLeftpad2", class_5606.method_32108().method_32101(16, 50).method_32097(-2.5f, 0.0f, -3.0f, 5.0f, 7.0f, 6.0f), class_5603.method_32090((float)0.0f, (float)0.5f, (float)0.0f));
        legLeft2.method_32117("footLeft", class_5606.method_32108().method_32101(72, 50).method_32097(-2.5f, 0.0f, -6.0f, 5.0f, 3.0f, 8.0f), class_5603.method_32091((float)0.0f, (float)8.0f, (float)0.0f, (float)-0.0349f, (float)0.0f, (float)0.0f));
        class_5610 fredHeadData = fredBodyData.method_32117("fredHead", class_5606.method_32108().method_32101(39, 22).method_32097(-5.5f, -8.0f, -4.5f, 11.0f, 8.0f, 9.0f), class_5603.method_32090((float)0.0f, (float)-13.0f, (float)-0.5f));
        fredHeadData.method_32117("jaw", class_5606.method_32108().method_32101(49, 65).method_32097(-5.0f, 0.0f, -4.5f, 10.0f, 3.0f, 9.0f), class_5603.method_32091((float)0.0f, (float)0.5f, (float)0.0f, (float)0.0873f, (float)0.0f, (float)0.0f));
        fredHeadData.method_32117("frednose", class_5606.method_32108().method_32101(17, 67).method_32097(-4.0f, -2.0f, -3.0f, 8.0f, 4.0f, 3.0f), class_5603.method_32090((float)0.0f, (float)-2.0f, (float)-4.5f));
        class_5610 earRight = fredHeadData.method_32117("earRight", class_5606.method_32108().method_32101(8, 0).method_32097(-1.0f, -3.0f, -0.5f, 2.0f, 3.0f, 1.0f), class_5603.method_32091((float)-4.5f, (float)-5.5f, (float)0.0f, (float)0.0524f, (float)0.0f, (float)-1.0472f));
        earRight.method_32117("earRightpad", class_5606.method_32108().method_32101(85, 0).method_32097(-2.0f, -5.0f, -1.0f, 4.0f, 4.0f, 2.0f), class_5603.method_32090((float)0.0f, (float)-1.0f, (float)0.0f));
        class_5610 earLeft = fredHeadData.method_32117("earLeft", class_5606.method_32108().method_32101(40, 0).method_32097(-1.0f, -3.0f, -0.5f, 2.0f, 3.0f, 1.0f), class_5603.method_32091((float)4.5f, (float)-5.5f, (float)0.0f, (float)0.0524f, (float)0.0f, (float)1.0472f));
        earLeft.method_32117("earRightpad_1", class_5606.method_32108().method_32101(40, 39).method_32097(-2.0f, -5.0f, -1.0f, 4.0f, 4.0f, 2.0f), class_5603.method_32090((float)0.0f, (float)-1.0f, (float)0.0f));
        class_5610 hat = fredHeadData.method_32117("hat", class_5606.method_32108().method_32101(70, 24).method_32097(-3.0f, -0.5f, -3.0f, 6.0f, 1.0f, 6.0f), class_5603.method_32091((float)0.0f, (float)-8.4f, (float)0.0f, (float)-0.0175f, (float)0.0f, (float)0.0f));
        hat.method_32117("hat2", class_5606.method_32108().method_32101(78, 61).method_32097(-2.0f, -4.0f, -2.0f, 4.0f, 4.0f, 4.0f), class_5603.method_32091((float)0.0f, (float)0.1f, (float)0.0f, (float)-0.0175f, (float)0.0f, (float)0.0f));
        this.root = rootData.method_32112(100, 80);
        class_630 fredBody = this.root.method_32086("fredBody");
        this.armRight = fredBody.method_32086("armRight");
        this.armLeft = fredBody.method_32086("armLeft");
        this.legRight = fredBody.method_32086("legRight");
        this.legLeft = fredBody.method_32086("legLeft");
        this.fredHead = fredBody.method_32086("fredHead");
    }

    @Override
    public void setAngles(class_10055 state, class_591 playerModel) {
        FredBearModel.copyRotation(this.fredHead, playerModel.field_3398);
        FredBearModel.copyRotation(this.armLeft, playerModel.field_27433, -0.2618f);
        FredBearModel.copyRotation(this.armRight, playerModel.field_3401, 0.2618f);
        FredBearModel.copyRotation(this.legRight, playerModel.field_3392);
        FredBearModel.copyRotation(this.legLeft, playerModel.field_3397);
    }
}
