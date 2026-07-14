package ua.babka.mixin;

import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.util.PlayerInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.event.MoveInputEvent;

@Mixin(value={KeyboardInput.class})
public abstract class KeyboardInputMixin
extends Input {
    @Inject(method={"tick"}, at={@At(value="TAIL")})
    private void onTick(CallbackInfo ci) {
        float forward = this.movementForward;
        float strafe = this.movementSideways;
        boolean jump = ((KeyboardInput)this).playerInput.comp_3163();
        boolean sneak = ((KeyboardInput)this).playerInput.sneak();
        MoveInputEvent event = new MoveInputEvent(forward, strafe, jump, sneak, 0.3);
        event.post();
        if (event.isCancelled()) {
            this.movementForward = 0.0f;
            this.movementSideways = 0.0f;
            return;
        }
        this.movementForward = event.getForward();
        this.movementSideways = event.getStrafe();
        ((KeyboardInput)this).playerInput = new PlayerInput(event.getForward() > 0.0f, event.getForward() < 0.0f, event.getStrafe() > 0.0f, event.getStrafe() < 0.0f, event.isJump(), event.isSneaking(), ((KeyboardInput)this).playerInput.comp_3165());
    }
}

