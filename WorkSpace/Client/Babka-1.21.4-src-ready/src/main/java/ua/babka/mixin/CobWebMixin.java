package ua.babka.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CobwebBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.event.EventCobweb;

@Mixin(value={CobwebBlock.class})
public class CobWebMixin {
    @Inject(method={"onEntityCollision"}, at={@At(value="HEAD")}, cancellable=true)
    private void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || entity != client.player) {
            return;
        }
        EventCobweb event = new EventCobweb();
        event.post();
    }
}

