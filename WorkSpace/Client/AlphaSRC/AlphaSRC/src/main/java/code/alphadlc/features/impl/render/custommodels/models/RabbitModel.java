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

public class RabbitModel
extends CustomModel {
    private final class_630 rabbitHead;
    private final class_630 rabbitRarm;
    private final class_630 rabbitLarm;
    private final class_630 rabbitRleg;
    private final class_630 rabbitLleg;

    public RabbitModel() {
        class_5609 modelData = new class_5609();
        class_5610 rootData = modelData.method_32111();
        class_5610 boneData = rootData.method_32117("bone", class_5606.method_32108().method_32101(28, 45).method_32097(-5.0f, -13.0f, -5.0f, 10.0f, 11.0f, 8.0f), class_5603.method_32090((float)0.0f, (float)24.0f, (float)0.0f));
        boneData.method_32117("rabbitRleg", class_5606.method_32108().method_32101(0, 0).method_32097(-2.0f, 0.0f, -2.0f, 4.0f, 2.0f, 4.0f), class_5603.method_32090((float)-3.0f, (float)-2.0f, (float)-1.0f));
        boneData.method_32117("rabbitLleg", class_5606.method_32108().method_32101(0, 0).method_32097(-2.0f, 0.0f, -2.0f, 4.0f, 2.0f, 4.0f), class_5603.method_32090((float)3.0f, (float)-2.0f, (float)-1.0f));
        boneData.method_32117("rabbitRarm", class_5606.method_32108().method_32101(0, 0).method_32097(-2.0f, 0.0f, -2.0f, 2.0f, 8.0f, 4.0f), class_5603.method_32091((float)-5.0f, (float)-13.0f, (float)-1.0f, (float)0.0f, (float)0.0f, (float)0.0873f));
        boneData.method_32117("rabbitLarm", class_5606.method_32108().method_32101(0, 0).method_32097(0.0f, 0.0f, -2.0f, 2.0f, 8.0f, 4.0f), class_5603.method_32091((float)5.0f, (float)-13.0f, (float)-1.0f, (float)0.0f, (float)0.0f, (float)-0.0873f));
        boneData.method_32117("rabbitHead", class_5606.method_32108().method_32101(0, 0).method_32097(-3.0f, 0.0f, -4.0f, 6.0f, 1.0f, 6.0f).method_32101(56, 0).method_32097(-5.0f, -9.0f, -5.0f, 2.0f, 3.0f, 2.0f).method_32101(56, 0).method_32097(3.0f, -9.0f, -5.0f, 2.0f, 3.0f, 2.0f).method_32101(0, 45).method_32097(-4.0f, -11.0f, -4.0f, 8.0f, 11.0f, 8.0f).method_32101(46, 0).method_32097(1.0f, -20.0f, 0.0f, 3.0f, 9.0f, 1.0f).method_32101(46, 0).method_32097(-4.0f, -20.0f, 0.0f, 3.0f, 9.0f, 1.0f), class_5603.method_32090((float)0.0f, (float)-14.0f, (float)-1.0f));
        this.root = rootData.method_32112(64, 64);
        class_630 bone = this.root.method_32086("bone");
        this.rabbitHead = bone.method_32086("rabbitHead");
        this.rabbitRarm = bone.method_32086("rabbitRarm");
        this.rabbitLarm = bone.method_32086("rabbitLarm");
        this.rabbitRleg = bone.method_32086("rabbitRleg");
        this.rabbitLleg = bone.method_32086("rabbitLleg");
    }

    @Override
    public void setAngles(class_10055 state, class_591 playerModel) {
        RabbitModel.copyRotation(this.rabbitHead, playerModel.field_3398);
        RabbitModel.copyRotation(this.rabbitLarm, playerModel.field_27433);
        RabbitModel.copyRotation(this.rabbitRarm, playerModel.field_3401);
        RabbitModel.copyRotation(this.rabbitRleg, playerModel.field_3392);
        RabbitModel.copyRotation(this.rabbitLleg, playerModel.field_3397);
    }
}
