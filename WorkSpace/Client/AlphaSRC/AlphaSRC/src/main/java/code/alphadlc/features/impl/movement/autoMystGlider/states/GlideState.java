/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10185
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_243
 */
package code.alphadlc.features.impl.movement.autoMystGlider.states;

import code.alphadlc.features.impl.misc.creeperFarm.WalkDirectionUtil;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_10185;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_243;

public record GlideState(class_243 vec3d) implements QuickImports
{
    public boolean done() {
        if (GlideState.mc.field_1724 == null) {
            return false;
        }
        class_243 curPos = GlideState.mc.field_1724.method_19538();
        class_243 delta = this.vec3d.method_1020(curPos);
        return delta.method_37268() < 3.0;
    }

    public boolean horizontalGlide() {
        if (GlideState.mc.field_1724 == null) {
            return false;
        }
        class_243 curPos = GlideState.mc.field_1724.method_19538();
        class_243 delta = this.vec3d.method_1020(curPos);
        return !this.verticalGlide() && delta.method_37268() > 3.0;
    }

    public boolean verticalGlide() {
        if (GlideState.mc.field_1724 == null) {
            return false;
        }
        class_243 curPos = GlideState.mc.field_1724.method_19538();
        class_243 delta = this.vec3d.method_1020(curPos);
        return delta.method_10214() > -130.0 && !this.done();
    }

    public boolean solidBlockInRadius(int radius) {
        class_2338 blockPos = class_2338.method_49638((class_2374)GlideState.mc.field_1724.method_19538());
        for (int i = 0; i < radius; ++i) {
            class_2338 blockPos1 = blockPos.method_33096(blockPos.method_10074().method_10264() - i);
            if (GlideState.mc.field_1724.method_37908().method_8320(blockPos1).method_26215()) continue;
            return true;
        }
        return false;
    }

    public boolean shouldLand() {
        if (GlideState.mc.field_1724 == null || GlideState.mc.field_1687 == null) {
            return false;
        }
        return !this.solidBlockInRadius(2);
    }

    public boolean fullyDone() {
        return this.vec3d.method_10214() - GlideState.mc.field_1724.method_19538().method_10214() > 3.0;
    }

    public class_10185 selectInput() {
        return WalkDirectionUtil.fromVec3d(this.vec3d.method_1020(GlideState.mc.field_1724.method_19538()), GlideState.mc.field_1724.method_36454());
    }
}
