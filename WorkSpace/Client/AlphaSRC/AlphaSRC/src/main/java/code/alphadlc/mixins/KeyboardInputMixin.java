/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  net.minecraft.class_10185
 *  net.minecraft.class_3532
 *  net.minecraft.class_743
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 */
package code.alphadlc.mixins;

import code.alphadlc.events.player.InputEvent;
import code.alphadlc.mixins.InputMixin;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.features.aura.warp.TurnsConstructor;
import code.alphadlc.utils.interactions.inv.InventoryFlowManager;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_10185;
import net.minecraft.class_3532;
import net.minecraft.class_743;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={class_743.class})
public class KeyboardInputMixin
extends InputMixin {
    @ModifyExpressionValue(method={"tick"}, at={@At(value="NEW", target="(ZZZZZZZ)Lnet/minecraft/util/PlayerInput;")})
    private class_10185 tickHook(class_10185 original) {
        InputEvent event = SafeEvents.dispatch(() -> new InputEvent(original));
        if (event != null) {
            InventoryFlowManager.input(event);
        }
        return this.transformInput(event != null ? event.getInput() : original);
    }

    @Unique
    private class_10185 transformInput(class_10185 input) {
        TurnsConnection rotationController = TurnsConnection.INSTANCE;
        Turns angle = rotationController.getCurrentAngle();
        TurnsConstructor configurable = rotationController.getCurrentRotationPlan();
        if (KeyboardInputMixin.mc.field_1724 == null || angle == null || configurable == null || !configurable.isMoveCorrection() || !configurable.isFreeCorrection()) {
            return input;
        }
        float deltaYaw = KeyboardInputMixin.mc.field_1724.method_36454() - angle.getYaw();
        float z = class_743.method_40218((boolean)input.comp_3159(), (boolean)input.comp_3160());
        float x = class_743.method_40218((boolean)input.comp_3161(), (boolean)input.comp_3162());
        float newX = x * class_3532.method_15362((float)(deltaYaw * ((float)Math.PI / 180))) - z * class_3532.method_15374((float)(deltaYaw * ((float)Math.PI / 180)));
        float newZ = z * class_3532.method_15362((float)(deltaYaw * ((float)Math.PI / 180))) + x * class_3532.method_15374((float)(deltaYaw * ((float)Math.PI / 180)));
        int movementSideways = Math.round(newX);
        int movementForward = Math.round(newZ);
        return new class_10185((float)movementForward > 0.0f, (float)movementForward < 0.0f, (float)movementSideways > 0.0f, (float)movementSideways < 0.0f, input.comp_3163(), input.comp_3164(), input.comp_3165());
    }
}
