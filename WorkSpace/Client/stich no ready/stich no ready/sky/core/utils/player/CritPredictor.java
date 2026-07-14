package sky.core.utils.player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;

public class CritPredictor {
   private final Minecraft mc = Minecraft.getInstance();
   private final PlayerEntity player;
   private double simY;
   private double simMotionY;
   private boolean simOnGround;
   private float simFallDistance;

   public CritPredictor(PlayerEntity p) {
      this.player = p;
      this.simY = p.getPosY();
      this.simMotionY = p.getMotion().y;
      this.simOnGround = p.isOnGround();
      this.simFallDistance = p.fallDistance;
   }

   public void simulateTicks(int ticks) {
      for(int i = 0; i < ticks; ++i) {
         this.tickVerticalPhysics();
      }

   }

   private void tickVerticalPhysics() {
      double gravity = -0.08;
      double drag = 0.98;
      if (!this.player.abilities.isFlying) {
         this.simMotionY += gravity;
      }

      this.simY += this.simMotionY;
      BlockPos pos = new BlockPos(this.player.getPosX(), this.simY - 0.1, this.player.getPosZ());
      boolean ground = !this.player.world.isAirBlock(pos);
      if (ground) {
         this.simOnGround = true;
         this.simMotionY = 0.0;
         this.simFallDistance = 0.0F;
      } else {
         this.simOnGround = false;
         if (this.simMotionY < 0.0) {
            this.simFallDistance = (float)((double)this.simFallDistance + -this.simMotionY);
         }
      }

      this.simMotionY *= drag;
   }

   public boolean shouldCritical() {
      boolean debuffed = this.player.isPotionActive(Effects.LEVITATION) || this.player.isPotionActive(Effects.BLINDNESS) || this.player.isPotionActive(Effects.SLOW_FALLING);
      boolean inLiquid = this.player.areEyesInFluid(FluidTags.WATER) || this.player.areEyesInFluid(FluidTags.LAVA);
      boolean flying = this.player.abilities.isFlying || this.player.isElytraFlying();
      boolean climbing = this.player.isOnLadder();
      boolean cantJump = this.player.isPassenger();
      Minecraft var10000 = this.mc;
      boolean inWeb = Minecraft.player.isInWeb;
      return !debuffed && !inLiquid && !flying && !climbing && !cantJump && !inWeb;
   }

   public boolean willBeAbleToCrit(boolean onlyCrits, boolean critModuleEnabled, boolean canCritSetting) {
      boolean canDefaultCrit = this.simFallDistance > 0.0F && canCritSetting || critModuleEnabled && !this.simOnGround;
      if (!onlyCrits) {
         return true;
      } else {
         return this.shouldCritical() && canDefaultCrit;
      }
   }

   public boolean willBeFalling() {
      return !this.simOnGround && this.simMotionY < 0.0;
   }

   public float getPredictedFallDistance() {
      return this.simFallDistance;
   }
}
