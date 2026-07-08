/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_377
 *  net.minecraft.class_378
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package code.alphadlc.mixins.accessor;

import java.util.Map;
import net.minecraft.class_2960;
import net.minecraft.class_377;
import net.minecraft.class_378;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_378.class})
public interface FontManagerAccessor {
    @Accessor(value="fontStorages")
    public Map<class_2960, class_377> alphadlc$getFontStorages();
}
