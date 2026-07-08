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
 */
package code.alphadlc.features.impl.render.custommodels.models;

import code.alphadlc.features.impl.render.custommodels.CustomModel;
import net.minecraft.class_10055;
import net.minecraft.class_5603;
import net.minecraft.class_5606;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_591;

public class CrabModel
extends CustomModel {
    public CrabModel() {
        class_5609 modelData = new class_5609();
        class_5610 rootData = modelData.method_32111();
        class_5610 boneData = rootData.method_32117("bone", class_5606.method_32108().method_32101(0, 0).method_32097(-8.0f, -12.0f, -6.0f, 16.0f, 7.0f, 12.0f).method_32101(44, 0).method_32097(7.0f, -8.0f, -13.0f, 2.0f, 3.0f, 7.0f).method_32101(40, 40).method_32097(-9.0f, -8.0f, -13.0f, 2.0f, 3.0f, 7.0f).method_32101(44, 27).method_32097(-10.0f, -9.0f, -16.0f, 6.0f, 5.0f, 3.0f).method_32101(44, 19).method_32097(4.0f, -9.0f, -16.0f, 6.0f, 5.0f, 3.0f).method_32101(32, 55).method_32097(2.0f, -5.0f, -16.0f, 2.0f, 1.0f, 3.0f).method_32101(31, 19).method_32097(-4.0f, -5.0f, -16.0f, 2.0f, 1.0f, 3.0f).method_32101(0, 41).method_32097(-4.0f, -9.0f, -16.0f, 3.0f, 2.0f, 3.0f).method_32101(20, 39).method_32097(1.0f, -9.0f, -16.0f, 3.0f, 2.0f, 3.0f).method_32101(0, 35).method_32097(-6.0f, -8.0f, -9.0f, 3.0f, 3.0f, 3.0f).method_32101(22, 22).method_32097(3.0f, -8.0f, -9.0f, 3.0f, 3.0f, 3.0f), class_5603.method_32090((float)0.0f, (float)24.0f, (float)0.0f));
        boneData.method_32117("cube_r1", class_5606.method_32108().method_32101(51, 39).method_32097(-11.5f, -1.8f, 4.0f, 2.0f, 6.0f, 2.0f).method_32101(8, 51).method_32097(-11.5f, -1.8f, 0.0f, 2.0f, 6.0f, 2.0f).method_32101(24, 55).method_32097(-11.5f, -1.8f, -6.0f, 2.0f, 6.0f, 2.0f).method_32101(0, 35).method_32097(-12.5f, -5.8f, -6.0f, 4.0f, 4.0f, 12.0f).method_32101(22, 23).method_32097(3.3f, -13.6f, -6.0f, 5.0f, 4.0f, 12.0f), class_5603.method_32091((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.3491f));
        boneData.method_32117("cube_r2", class_5606.method_32108().method_32101(52, 52).method_32097(9.5f, -1.8f, 4.0f, 2.0f, 6.0f, 2.0f).method_32101(16, 55).method_32097(9.5f, -1.8f, 0.0f, 2.0f, 6.0f, 2.0f).method_32101(0, 51).method_32097(9.5f, -1.8f, -6.0f, 2.0f, 6.0f, 2.0f).method_32101(20, 39).method_32097(8.5f, -5.8f, -6.0f, 4.0f, 4.0f, 12.0f).method_32101(0, 19).method_32097(-8.3f, -13.6f, -6.0f, 5.0f, 4.0f, 12.0f), class_5603.method_32091((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)-0.3491f));
        boneData.method_32117("cube_r3", class_5606.method_32108().method_32101(0, 0).method_32097(1.3f, -18.0f, -6.7f, 3.0f, 6.0f, 3.0f), class_5603.method_32091((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.1309f));
        boneData.method_32117("cube_r4", class_5606.method_32108().method_32101(0, 19).method_32097(-4.3f, -18.0f, -6.7f, 3.0f, 6.0f, 3.0f), class_5603.method_32091((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)-0.1309f));
        this.root = rootData.method_32112(64, 64);
    }

    @Override
    public void setAngles(class_10055 state, class_591 playerModel) {
    }
}
