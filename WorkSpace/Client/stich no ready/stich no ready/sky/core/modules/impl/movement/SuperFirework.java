package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import sky.core.events.EventFireworkRocket;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class SuperFirework extends Module {
   private final SliderSetting yaw05 = new SliderSetting("Yaw 0-5°", 1.63F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting yaw510 = new SliderSetting("Yaw 5-10°", 1.62F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting yaw1015 = new SliderSetting("Yaw 10-15°", 1.69F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting yaw1520 = new SliderSetting("Yaw 15-20°", 1.76F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting yaw2025 = new SliderSetting("Yaw 20-25°", 1.77F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting yaw2530 = new SliderSetting("Yaw 25-30°", 1.83F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting yaw3035 = new SliderSetting("Yaw 30-35°", 1.92F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting yaw3540 = new SliderSetting("Yaw 35-40°", 1.96F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting yaw4045 = new SliderSetting("Yaw 40-45°", 1.94F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting pitch05 = new SliderSetting("Pitch 0-5°", 1.62F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting pitch510 = new SliderSetting("Pitch 5-10°", 1.63F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting pitch1015 = new SliderSetting("Pitch 10-15°", 1.63F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting pitch1520 = new SliderSetting("Pitch 15-20°", 1.66F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting pitch2025 = new SliderSetting("Pitch 20-25°", 1.8F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting pitch2530 = new SliderSetting("Pitch 25-30°", 1.84F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting pitch3035 = new SliderSetting("Pitch 30-35°", 1.97F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting pitch3540 = new SliderSetting("Pitch 35-40°", 1.99F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting pitch4045 = new SliderSetting("Pitch 40-45°", 1.99F, 1.5F, 2.5F, 0.01F);
   private final SliderSetting[] yawSettings;
   private final SliderSetting[] pitchSettings;

   public SuperFirework() {
      super("Super Firework", "Увеличивает скорость и дальность полета при использовании фейерверков на элитре", Category.Movement);
      this.yawSettings = new SliderSetting[]{this.yaw05, this.yaw510, this.yaw1015, this.yaw1520, this.yaw2025, this.yaw2530, this.yaw3035, this.yaw3540, this.yaw4045};
      this.pitchSettings = new SliderSetting[]{this.pitch05, this.pitch510, this.pitch1015, this.pitch1520, this.pitch2025, this.pitch2530, this.pitch3035, this.pitch3540, this.pitch4045};
      this.addSettings(new Setting[]{this.yaw05, this.yaw510, this.yaw1015, this.yaw1520, this.yaw2025, this.yaw2530, this.yaw3035, this.yaw3540, this.yaw4045, this.pitch05, this.pitch510, this.pitch1015, this.pitch1520, this.pitch2025, this.pitch2530, this.pitch3035, this.pitch3540, this.pitch4045});
   }

   @EventTarget
   public void onEvent(EventFireworkRocket event) {
      Minecraft var10001 = mc;
      float convertedYaw = this.convertAngleToRange(MathHelper.wrapDegrees(Minecraft.player.rotationYaw));
      var10001 = mc;
      float convertedPitch = this.convertAngleToRange(Math.abs(Minecraft.player.rotationPitch));
      float xzSpeed = this.getSpeedForYaw(convertedYaw);
      float ySpeed = this.getSpeedForPitch(convertedPitch);
      if (ySpeed > xzSpeed) {
         xzSpeed = ySpeed;
      }

      event.setSpeed(xzSpeed);
      event.setYSpeed(ySpeed);
   }

   private float getSpeedForYaw(float yaw) {
      int index = (int)(yaw / 5.0F);
      if (index >= this.yawSettings.length) {
         index = this.yawSettings.length - 1;
      }

      if (index < 0) {
         index = 0;
      }

      return (Float)this.yawSettings[index].get();
   }

   private float getSpeedForPitch(float pitch) {
      int index = (int)(pitch / 5.0F);
      if (index >= this.pitchSettings.length) {
         index = this.pitchSettings.length - 1;
      }

      if (index < 0) {
         index = 0;
      }

      return (Float)this.pitchSettings[index].get();
   }

   private float convertAngleToRange(float angle) {
      float absAngle = Math.abs(angle);
      if (absAngle > 90.0F) {
         absAngle = 180.0F - absAngle;
      }

      if (absAngle > 45.0F) {
         absAngle = 90.0F - absAngle;
      }

      return absAngle;
   }
}
