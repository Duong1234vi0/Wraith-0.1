/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_329
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package code.alphadlc.mixins.acc;

import net.minecraft.class_2960;
import net.minecraft.class_329;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_329.class})
public interface InGameHudAccessor {
    @Accessor(value="FOOD_FULL_TEXTURE")
    public class_2960 getFoodFullTexture();

    @Accessor(value="FOOD_HALF_TEXTURE")
    public class_2960 getFoodHalfTexture();

    @Accessor(value="FOOD_EMPTY_TEXTURE")
    public class_2960 getFoodEmptyTexture();

    @Accessor(value="FOOD_FULL_HUNGER_TEXTURE")
    public class_2960 getFoodFullHungerTexture();

    @Accessor(value="FOOD_HALF_HUNGER_TEXTURE")
    public class_2960 getFoodHalfHungerTexture();

    @Accessor(value="FOOD_EMPTY_HUNGER_TEXTURE")
    public class_2960 getFoodEmptyHungerTexture();
}
