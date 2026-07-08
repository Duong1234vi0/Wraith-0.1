/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package code.alphadlc.mixins;

import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_746.class})
public interface ClientPlayerEntityAccessor {
    @Accessor(value="lastSprinting")
    public boolean getLastSprinting();

    @Accessor(value="lastSprinting")
    public void setLastSprinting(boolean var1);
}
