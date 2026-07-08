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

public class SonicModel
extends CustomModel {
    private final class_630 sonicHead;
    private final class_630 rleg;
    private final class_630 lleg;
    private final class_630 larm;
    private final class_630 rarm;

    public SonicModel() {
        class_5609 modelData = new class_5609();
        class_5610 rootData = modelData.method_32111();
        rootData.method_32117("bbMain", class_5606.method_32108().method_32101(0, 0).method_32097(-3.0f, -17.0f, -2.0f, 5.0f, 7.0f, 5.0f).method_32101(0, 22).method_32097(-3.0f, -16.0f, -3.0f, 5.0f, 5.0f, 1.0f).method_32101(0, 0).method_32097(-3.0f, -16.0f, 3.0f, 5.0f, 5.0f, 1.0f).method_32101(0, 0).method_32097(0.0f, -15.0f, 3.0f, 1.0f, 1.0f, 3.0f).method_32101(0, 0).method_32097(-3.0f, -14.0f, 4.0f, 1.0f, 1.0f, 2.0f).method_32101(0, 0).method_32097(-1.0f, -11.0f, 3.0f, 1.0f, 1.0f, 2.0f).method_32101(0, 0).method_32097(0.0f, -14.0f, 6.0f, 1.0f, 1.0f, 1.0f).method_32101(0, 0).method_32097(-3.0f, -13.0f, 6.0f, 1.0f, 1.0f, 1.0f).method_32101(0, 0).method_32097(-1.0f, -12.0f, 5.0f, 1.0f, 1.0f, 1.0f).method_32101(0, 0).method_32097(-4.0f, -16.0f, -2.0f, 1.0f, 5.0f, 5.0f).method_32101(0, 0).method_32097(2.0f, -16.0f, -2.0f, 1.0f, 5.0f, 5.0f), class_5603.method_32090((float)0.0f, (float)24.0f, (float)0.0f));
        class_5610 sonicHeadData = rootData.method_32117("sonicHead", class_5606.method_32108().method_32101(36, 3).method_32097(-3.5f, -7.0f, -3.0f, 7.0f, 7.0f, 7.0f).method_32101(60, 0).method_32097(-0.5f, -3.0f, -4.0f, 1.0f, 1.0f, 1.0f), class_5603.method_32090((float)-0.5f, (float)7.0f, (float)0.0f));
        sonicHeadData.method_32117("spike1", class_5606.method_32108().method_32101(0, 0).method_32097(-3.5f, -4.0f, 6.0f, 3.0f, 3.0f, 3.0f).method_32101(0, 0).method_32097(-3.5f, -5.0f, 4.0f, 3.0f, 3.0f, 2.0f), class_5603.method_32090((float)0.0f, (float)0.0f, (float)0.0f));
        sonicHeadData.method_32117("spike2", class_5606.method_32108().method_32101(0, 0).method_32097(0.5f, -6.0f, 6.0f, 3.0f, 3.0f, 3.0f).method_32101(0, 0).method_32097(0.5f, -7.0f, 4.0f, 3.0f, 3.0f, 2.0f), class_5603.method_32090((float)0.0f, (float)0.0f, (float)0.0f));
        sonicHeadData.method_32117("spike3", class_5606.method_32108().method_32101(0, 0).method_32097(-2.5f, -9.0f, 3.0f, 3.0f, 3.0f, 2.0f).method_32101(0, 0).method_32097(-2.5f, -10.0f, 5.0f, 3.0f, 3.0f, 3.0f), class_5603.method_32090((float)0.0f, (float)0.0f, (float)0.0f));
        rootData.method_32117("rleg", class_5606.method_32108().method_32101(53, 54).method_32097(-1.5f, 7.0f, -1.0f, 2.0f, 1.0f, 2.0f).method_32101(0, 0).method_32097(-1.0f, -1.0f, -0.5f, 1.0f, 8.0f, 1.0f).method_32101(52, 58).method_32097(-1.5f, 8.0f, -3.0f, 2.0f, 2.0f, 4.0f).method_32101(52, 58).method_32097(-2.0f, 9.0f, -2.0f, 0.0f, 1.0f, 1.0f), class_5603.method_32090((float)-3.0f, (float)14.0f, (float)0.5f));
        rootData.method_32117("lleg", class_5606.method_32108().method_32101(53, 54).method_32097(-0.5f, 7.0f, -1.0f, 2.0f, 1.0f, 2.0f).method_32101(0, 0).method_32097(0.0f, -1.0f, -0.5f, 1.0f, 8.0f, 1.0f).method_32101(52, 58).method_32097(-0.5f, 8.0f, -3.0f, 2.0f, 2.0f, 4.0f).method_32101(52, 58).method_32097(1.5f, 9.0f, -2.0f, 0.0f, 1.0f, 1.0f), class_5603.method_32090((float)2.0f, (float)14.0f, (float)0.5f));
        rootData.method_32117("larm", class_5606.method_32108().method_32101(0, 60).method_32097(-0.5f, 8.0f, -1.0f, 2.0f, 2.0f, 2.0f).method_32101(60, 17).method_32097(0.0f, 0.0f, -0.5f, 1.0f, 8.0f, 1.0f), class_5603.method_32091((float)3.0f, (float)9.0f, (float)0.5f, (float)0.0f, (float)0.0f, (float)-0.0873f));
        rootData.method_32117("rarm", class_5606.method_32108().method_32101(60, 17).method_32097(-1.0f, 0.0f, -0.5f, 1.0f, 8.0f, 1.0f).method_32101(0, 60).method_32097(-1.5f, 8.0f, -1.0f, 2.0f, 2.0f, 2.0f), class_5603.method_32091((float)-4.0f, (float)9.0f, (float)0.5f, (float)0.0f, (float)0.0f, (float)0.0873f));
        this.root = rootData.method_32112(64, 64);
        this.sonicHead = this.root.method_32086("sonicHead");
        this.rleg = this.root.method_32086("rleg");
        this.lleg = this.root.method_32086("lleg");
        this.larm = this.root.method_32086("larm");
        this.rarm = this.root.method_32086("rarm");
    }

    @Override
    public void setAngles(class_10055 state, class_591 playerModel) {
        SonicModel.copyRotation(this.sonicHead, playerModel.field_3398);
        SonicModel.copyRotation(this.larm, playerModel.field_27433, -0.0873f);
        SonicModel.copyRotation(this.rarm, playerModel.field_3401, 0.0873f);
        SonicModel.copyRotation(this.rleg, playerModel.field_3392);
        SonicModel.copyRotation(this.lleg, playerModel.field_3397);
    }
}
