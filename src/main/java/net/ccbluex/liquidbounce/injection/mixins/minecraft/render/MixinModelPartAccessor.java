package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(ModelPart.class)
public interface MixinModelPartAccessor {

    @Accessor("cubes")
    List<ModelPart.Cube> liquid_bounce$getCubes();

}
