package ua.babka.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ua.babka.module.impl.player.FreeCamera;
import ua.babka.util.Instance;

@Mixin(value={PlayerEntity.class})
public abstract class PlayerEntityMixin {
    @Redirect(method={"tick"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/player/PlayerEntity;isSpectator()Z"))
    private boolean redirectNoClip(PlayerEntity instance) {
        return instance.isSpectator() || Instance.get(FreeCamera.class).isEnabled();
    }
}

