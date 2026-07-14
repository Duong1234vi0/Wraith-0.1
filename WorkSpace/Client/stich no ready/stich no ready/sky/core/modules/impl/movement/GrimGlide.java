package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.UseAction;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.SkyCore;
import sky.core.events.EventMotion;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.ModuleManager;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.utils.StopWatch;
import sky.core.utils.math.MathUtil;

public class GrimGlide extends Module {
   private static final float MOTION_RANDOM_MIN = 1.001F;
   private static final float MOTION_RANDOM_MAX = 1.0021F;
   private static final double MOTION_Y_BOOST = 0.00600000075995922;
   private static final long POSITION_BURST_MS = 30L;
   private static final int MOTION_BURST_TICKS = 30;
   private final ModeSetting mode = new ModeSetting("Режим", "Обычный", new String[]{"Обычный", "Рывки"});
   private long lastTickTime = 0L;
   private int ticksTwo = 0;
   private final StopWatch glideTicks = new StopWatch();

   public GrimGlide() {
      super("Glide Fly", "Флай без фейерверков (Grim)", Category.Movement);
      this.addSettings(new Setting[]{this.mode});
   }

   public static boolean isGlideFlyActive() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.isElytraFlying()) {
            ModuleManager manager = SkyCore.getInstance().getModuleManager();
            if (manager == null) {
               return false;
            }

            GrimGlide grimGlide = (GrimGlide)manager.getModule(GrimGlide.class);
            if (grimGlide != null && grimGlide.isEnabled()) {
               return true;
            }

            Flight flight = (Flight)manager.getModule(Flight.class);
            return flight != null && flight.isEnabled() && flight.isGlideFlyMode();
         }
      }

      return false;
   }

   private static void blockEating() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.isHandActive()) {
            var10000 = mc;
            if (isConsumableUse(Minecraft.player.getActiveItemStack().getUseAction())) {
               Minecraft var10001 = mc;
               mc.playerController.onStoppedUsingItem(Minecraft.player);
            }
         }

         mc.gameSettings.keyBindUseItem.setPressed(false);
      }
   }

   private static boolean isConsumableUse(UseAction action) {
      return action == UseAction.EAT || action == UseAction.DRINK;
   }

   @EventTarget
   public void onBlockEat(EventUpdate event) {
      if (isGlideFlyActive()) {
         blockEating();
      }
   }

   private double getGlideForward() {
      Minecraft var10000 = mc;
      return Minecraft.player.ticksExisted % 2 == 0 ? 0.087 : 0.09;
   }

   private void applyGlideMotion(double dx, double dz) {
      Minecraft var10000 = mc;
      ClientPlayerEntity var5 = Minecraft.player;
      double var10001 = dx * (double)MathUtil.randomtest(1.001F, 1.0021F);
      Minecraft var10002 = mc;
      var5.setMotion(var10001, Minecraft.player.getMotion().y + 0.00600000075995922, dz * (double)MathUtil.randomtest(1.001F, 1.0021F));
   }

   private double[] getGlideDelta(float yaw) {
      double forward = this.getGlideForward();
      return new double[]{-Math.sin(Math.toRadians((double)yaw)) * forward, Math.cos(Math.toRadians((double)yaw)) * forward};
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      if (this.mode.is("Обычный")) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && mc.world != null) {
            var10000 = mc;
            if (Minecraft.player.isElytraFlying()) {
               ++this.ticksTwo;
               var10000 = mc;
               Vector3d pos = Minecraft.player.getPositionVec();
               var10000 = mc;
               float yaw = Minecraft.player.rotationYaw;
               double[] delta = this.getGlideDelta(yaw);
               double dx = delta[0];
               double dz = delta[1];
               if (System.currentTimeMillis() - this.lastTickTime >= 30L) {
                  var10000 = mc;
                  Minecraft.player.setPosition(pos.getX() + dx, pos.getY(), pos.getZ() + dz);
                  this.lastTickTime = System.currentTimeMillis();
               }

               if (this.ticksTwo % 30 == 0) {
                  this.applyGlideMotion(dx, dz);
               }

               return;
            }
         }
      }

   }

   @EventTarget
   public void onMotion(EventMotion event) {
      if (this.mode.is("Рывки")) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            if (Minecraft.player.isElytraFlying()) {
               ++this.ticksTwo;
               var10000 = mc;
               Vector3d pos = Minecraft.player.getPositionVec();
               var10000 = mc;
               float yaw = Minecraft.player.rotationYaw;
               double[] delta = this.getGlideDelta(yaw);
               double dx = delta[0];
               double dz = delta[1];
               float multX = MathUtil.randomtest(1.001F, 1.0021F);
               float multZ = MathUtil.randomtest(1.001F, 1.0021F);
               var10000 = mc;
               double var10001 = dx * (double)multX;
               Minecraft var10002 = mc;
               Minecraft.player.setVelocity(var10001, Minecraft.player.getMotion().y, dz * (double)multZ);
               if (this.glideTicks.finished(30.0)) {
                  var10000 = mc;
                  Minecraft.player.setPosition(pos.getX() + dx, pos.getY(), pos.getZ() + dz);
                  this.glideTicks.reset();
               }

               var10000 = mc;
               var10001 = dx * (double)multX;
               var10002 = mc;
               Minecraft.player.setVelocity(var10001, Minecraft.player.getMotion().y + 0.00600000075995922, dz * (double)multZ);
               event.setX(event.getX() + dx);
               event.setZ(event.getZ() + dz);
               return;
            }
         }
      }

   }

   public void onEnable() {
      super.onEnable();
      this.ticksTwo = 0;
      this.lastTickTime = System.currentTimeMillis();
      this.glideTicks.reset();
   }

   public void onDisable() {
      this.ticksTwo = 0;
      blockEating();
      super.onDisable();
   }
}
