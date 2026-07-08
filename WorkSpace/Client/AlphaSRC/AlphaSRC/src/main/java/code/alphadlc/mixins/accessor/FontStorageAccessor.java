/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_377
 *  net.minecraft.class_380
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package code.alphadlc.mixins.accessor;

import java.util.List;
import net.minecraft.class_377;
import net.minecraft.class_380;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_377.class})
public interface FontStorageAccessor {
    @Accessor(value="glyphAtlases")
    public List<class_380> alphadlc$getGlyphAtlases();
}
