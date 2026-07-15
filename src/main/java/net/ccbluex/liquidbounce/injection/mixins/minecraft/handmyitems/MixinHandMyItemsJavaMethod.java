package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import java.lang.reflect.Method;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.annotation.Safe;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "org.luaj.vm2.lib.jse.JavaMethod", remap = false)
public abstract class MixinHandMyItemsJavaMethod {

    @Shadow
    @Final
    private Method method;

    @Inject(method = "invokeMethod", at = @At("RETURN"), cancellable = true)
    private void hmi$rejectUnsafeInvocation(Object instance, Varargs arguments, CallbackInfoReturnable<LuaValue> cir) {
        String name = this.method.getName();
        if (!this.method.isAnnotationPresent(Safe.class) && !name.equals("getOrDefault") && !name.equals("put")) {
            cir.setReturnValue(LuaValue.NIL);
        }
    }
}
