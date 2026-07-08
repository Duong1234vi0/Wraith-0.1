/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10042
 *  net.minecraft.class_3887
 *  net.minecraft.class_583
 *  net.minecraft.class_922
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package code.alphadlc.mixins;

import net.minecraft.class_10042;
import net.minecraft.class_3887;
import net.minecraft.class_583;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_922.class})
public interface LivingEntityRendererAccessor<S extends class_10042, M extends class_583<? super S>> {
    @Invoker(value="addFeature")
    public boolean callAddFeature(class_3887<S, M> var1);

    @Accessor(value="model")
    public M alphadlc$getModel();
}
