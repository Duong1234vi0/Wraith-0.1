package ua.babka.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.event.FireworkEvent;
import ua.babka.mixin.IFireworkRocketEntityAccessor;

@Mixin(value={FireworkRocketEntity.class})
public abstract class FireworkRocketEntityMixin {
    @Shadow
    public LivingEntity shooter;
    @Unique
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    @ModifyConstant(method={"tick"}, constant={@Constant(doubleValue=1.5)})
    private double replaceSpeed(double original) {
        FireworkEvent event = new FireworkEvent(this.shooter, (float)original);
        event.post();
        return event.getSpeed();
    }

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    private void onTick(CallbackInfo ci) {
        Vec3d playerPos;
        FireworkRocketEntity self;
        Vec3d fireworkPos;
        double distance;
        if (FireworkRocketEntityMixin.mc.player != null && FireworkRocketEntityMixin.mc.world != null && FireworkRocketEntityMixin.mc.player.isGliding() && this.shooter != FireworkRocketEntityMixin.mc.player && !this.hasActiveOwnFirework() && (distance = (fireworkPos = (self = (FireworkRocketEntity)this).getPos()).distanceTo(playerPos = FireworkRocketEntityMixin.mc.player.getPos())) < 5.0) {
            Vec3d fireworkVel = self.getVelocity();
            if (fireworkVel.lengthSquared() < 0.01) {
                return;
            }
            Vec3d toPlayer = playerPos.subtract(fireworkPos).normalize();
            double dot = fireworkVel.normalize().dotProduct(toPlayer);
            if (dot > 0.4) {
                Vec3d fireworkDir = fireworkVel.normalize();
                Vec3d right = new Vec3d(fireworkDir.x, 0.0, -fireworkDir.z).normalize();
                double dotRight = toPlayer.dotProduct(right);
                if (dotRight < 0.0) {
                    right = right.multiply(-1.0);
                }
                double urgency = 1.0 - distance / 5.0;
                double strength = 0.25 * urgency;
                double upward = 0.1 * urgency;
                Vec3d currentVel = FireworkRocketEntityMixin.mc.player.getVelocity();
                Vec3d dodge = right.multiply(strength).add(0.0, upward, 0.0);
                FireworkRocketEntityMixin.mc.player.setVelocity(currentVel.add(dodge));
            }
        }
    }

    @Unique
    private boolean hasActiveOwnFirework() {
        for (Entity entity : FireworkRocketEntityMixin.mc.world.getEntities()) {
            FireworkRocketEntity firework;
            IFireworkRocketEntityAccessor accessor;
            if (!(entity instanceof FireworkRocketEntity) || (accessor = (IFireworkRocketEntityAccessor)(firework = (FireworkRocketEntity)entity)).getShooter() != FireworkRocketEntityMixin.mc.player) continue;
            return true;
        }
        return false;
    }
}

