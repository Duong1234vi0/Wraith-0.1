/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_340
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.misc.Panic;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_340;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={class_340.class})
public abstract class DebugHudMixin {
    @ModifyVariable(method={"getLeftText"}, at=@At(value="RETURN"), ordinal=0)
    private List<String> modifyLeftText(List<String> list) {
        if (!list.isEmpty()) {
            if (Panic.isPanicMode()) {
                list.set(0, "Minecraft 1.21.4");
            } else {
                String fps = class_310.method_1551().field_1770.split(" ")[0];
                list.set(0, "AlphaDLC Recode // " + fps + "FPS");
            }
        }
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (!list.get(i).startsWith("SC:")) continue;
            index = i;
            break;
        }
        if (index != -1) {
            list.subList(index, list.size()).clear();
        }
        return list;
    }

    @ModifyVariable(method={"getRightText"}, at=@At(value="RETURN"), ordinal=0)
    private List<String> modifyRightText(List<String> list) {
        list.removeIf(s -> s.contains("Targeted Block") || s.contains("Targeted Fluid") || s.contains("Targeted Entity") || s.startsWith("minecraft:") || s.startsWith("#minecraft:"));
        return list;
    }

    @Redirect(method={"drawText"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V"))
    private void redirectFill(class_332 instance, int x1, int y1, int x2, int y2, int color) {
        int newColor = -939524096;
        instance.method_25294(x1, y1, x2, y2, newColor);
    }

    @Redirect(method={"getLeftText"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;getYaw()F"))
    private float redirectYaw(class_1297 entity) {
        if (entity == null) {
            return 0.0f;
        }
        return TurnsConnection.INSTANCE.getRotation().getYaw();
    }

    @Redirect(method={"getLeftText"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;getPitch()F"))
    private float redirectPitch(class_1297 entity) {
        if (entity == null) {
            return 0.0f;
        }
        return TurnsConnection.INSTANCE.getRotation().getPitch();
    }
}
