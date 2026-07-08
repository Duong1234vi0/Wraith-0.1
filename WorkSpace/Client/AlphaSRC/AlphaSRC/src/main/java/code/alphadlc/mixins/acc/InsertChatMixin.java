/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_303
 *  net.minecraft.class_338
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package code.alphadlc.mixins.acc;

import net.minecraft.class_303;
import net.minecraft.class_338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_338.class})
public interface InsertChatMixin {
    @Invoker(value="addMessage")
    public void invokeAddMessage(class_303 var1);

    @Invoker(value="addVisibleMessage")
    public void invokeAddVisibleMessage(class_303 var1);
}
