/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_638$class_5271
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.WorldTweaks;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_638.class_5271.class})
public class ClientWorldPropertiesMixin
implements QuickImports {
    @Shadow
    private long field_24439;

    @Inject(method={"setTimeOfDay"}, at={@At(value="HEAD")}, cancellable=true)
    public void setTimeOfDayHook(long timeOfDay, CallbackInfo ci) {
        WorldTweaks tweaks = WorldTweaks.getInstance();
        if (tweaks != null && tweaks.state && tweaks.modeSetting.isSelected("\u0412\u0440\u0435\u043c\u044f")) {
            this.field_24439 = (long)tweaks.timeSetting.getInt() * 1000L;
            ci.cancel();
        }
    }
}
