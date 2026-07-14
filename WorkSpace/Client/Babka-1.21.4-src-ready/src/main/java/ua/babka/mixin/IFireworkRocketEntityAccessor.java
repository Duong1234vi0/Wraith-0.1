package ua.babka.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={FireworkRocketEntity.class})
public interface IFireworkRocketEntityAccessor {
    @Accessor(value="shooter")
    public LivingEntity getShooter();
}

