package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import sky.core.SkyCore;
import sky.core.events.EventFireworkRocket;
import sky.core.events.EventRender2D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.font.Fonts;

public class ElytraBooster extends Module {
   private final BooleanSetting boost = new BooleanSetting("Ускорение", true);
   private final ModeSetting mode = new ModeSetting("Режим ускорения", "ReallyWorld", () -> {
      return (Boolean)this.boost.get();
   }, new String[]{"ReallyWorld", "Bravo", "BravoGrief", "Кастомный", "HolyWorld", "Ultra"});
   public final MultiBooleanSetting render = new MultiBooleanSetting("Отображать", () -> {
      return this.mode.is("Кастомный");
   }, new BooleanSetting[]{new BooleanSetting("Углы", false)});
   private final BooleanSetting individualY = new BooleanSetting("Индивидуальный Y", false, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный");
   });
   private final SliderSetting xz0_5 = new SliderSetting("XZ 0-5°", 1.52F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный");
   });
   private final SliderSetting xz5_10 = new SliderSetting("XZ 5-10°", 1.53F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный");
   });
   private final SliderSetting xz10_15 = new SliderSetting("XZ 10-15°", 1.54F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный");
   });
   private final SliderSetting xz15_20 = new SliderSetting("XZ 15-20°", 1.55F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный");
   });
   private final SliderSetting xz20_25 = new SliderSetting("XZ 20-25°", 1.56F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный");
   });
   private final SliderSetting xz25_30 = new SliderSetting("XZ 25-30°", 1.57F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный");
   });
   private final SliderSetting xz30_35 = new SliderSetting("XZ 30-35°", 1.58F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный");
   });
   private final SliderSetting xz35_40 = new SliderSetting("XZ 35-40°", 1.59F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный");
   });
   private final SliderSetting xz40_45 = new SliderSetting("XZ 40-45°", 1.6F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный");
   });
   private final SliderSetting y0_5 = new SliderSetting("Y 0-5°", 1.51F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный") && !(Boolean)this.individualY.get();
   });
   private final SliderSetting y5_10 = new SliderSetting("Y 5-10°", 1.52F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный") && !(Boolean)this.individualY.get();
   });
   private final SliderSetting y10_15 = new SliderSetting("Y 10-15°", 1.53F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный") && !(Boolean)this.individualY.get();
   });
   private final SliderSetting y15_20 = new SliderSetting("Y 15-20°", 1.54F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный") && !(Boolean)this.individualY.get();
   });
   private final SliderSetting y20_25 = new SliderSetting("Y 20-25°", 1.55F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный") && !(Boolean)this.individualY.get();
   });
   private final SliderSetting y25_30 = new SliderSetting("Y 25-30°", 1.56F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный") && !(Boolean)this.individualY.get();
   });
   private final SliderSetting y30_35 = new SliderSetting("Y 30-35°", 1.57F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный") && !(Boolean)this.individualY.get();
   });
   private final SliderSetting y35_40 = new SliderSetting("Y 35-40°", 1.58F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный") && !(Boolean)this.individualY.get();
   });
   private final SliderSetting y40_45 = new SliderSetting("Y 40-45°", 1.59F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный") && !(Boolean)this.individualY.get();
   });
   private final SliderSetting y45_50 = new SliderSetting("Y 45-50°", 1.6F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный") && !(Boolean)this.individualY.get();
   });
   private final SliderSetting y50_55 = new SliderSetting("Y 50-55°", 1.61F, 1.5F, 3.0F, 0.01F, () -> {
      return (Boolean)this.boost.get() && this.mode.is("Кастомный") && !(Boolean)this.individualY.get();
   });
   private final SliderSetting[] individualYSettings = new SliderSetting[56];
   private final SliderSetting[] xzSliders;
   private final SliderSetting[] yGroupSliders;
   private static final float[] BRAVO_GRIEF_XZ = new float[]{1.67F, 1.68F, 1.69F, 1.72F, 1.72F, 1.73F, 1.74F, 1.75F, 1.76F};
   private static final float[] BRAVO_GRIEF_Y = new float[]{1.72F, 1.72F, 1.72F, 1.72F, 1.72F, 1.73F, 1.74F, 1.76F, 1.8F};
   private static final float[] REALLY_WORLD_XZ = new float[]{1.6F, 1.63F, 1.66F, 1.71F, 1.73F, 1.81F, 1.81F, 1.81F, 1.81F};
   private static final float[] REALLY_WORLD_Y = new float[]{1.6F, 1.62F, 1.6F, 1.62F, 1.64F, 1.68F, 1.75F, 1.9F, 2.16F};
   private static final float[] HOLYWORLD_XZ = new float[]{1.65F, 1.68F, 1.72F, 1.76F, 1.8F, 1.84F, 1.88F, 1.92F, 1.96F};
   private static final float[] HOLYWORLD_Y = new float[]{1.62F, 1.65F, 1.68F, 1.72F, 1.76F, 1.8F, 1.84F, 1.88F, 1.92F};
   private static final float[] ULTRA_XZ = new float[]{1.82F, 1.85F, 1.89F, 1.93F, 1.97F, 2.01F, 2.05F, 2.09F, 2.13F};
   private static final float[] ULTRA_Y = new float[]{1.8F, 1.83F, 1.87F, 1.91F, 1.95F, 1.99F, 2.03F, 2.07F, 2.11F};

   public ElytraBooster() {
      super("Super Firework", "Ускоряет полёт на элитрах", Category.Movement);
      this.xzSliders = new SliderSetting[]{this.xz0_5, this.xz5_10, this.xz10_15, this.xz15_20, this.xz20_25, this.xz25_30, this.xz30_35, this.xz35_40, this.xz40_45};
      this.yGroupSliders = new SliderSetting[]{this.y0_5, this.y5_10, this.y10_15, this.y15_20, this.y20_25, this.y25_30, this.y30_35, this.y35_40, this.y40_45, this.y45_50, this.y50_55};

      for(int i = 0; i <= 55; ++i) {
         float defaultValue = 1.5F + (float)i / 55.0F * 1.5F;
         this.individualYSettings[i] = new SliderSetting("Y " + i + "°", defaultValue, 1.5F, 3.0F, 0.01F, () -> {
            return (Boolean)this.boost.get() && this.mode.is("Кастомный") && (Boolean)this.individualY.get();
         });
      }

      this.addSettings(new Setting[]{this.boost, this.mode, this.individualY});
      this.addSettings(this.xzSliders);
      this.addSettings(this.yGroupSliders);
      this.addSettings(this.individualYSettings);
      this.addSettings(new Setting[]{this.render});
   }

   private void addSettings(SliderSetting[] settings) {
      SliderSetting[] var2 = settings;
      int var3 = settings.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         SliderSetting setting = var2[var4];
         this.addSettings(new Setting[]{setting});
      }

   }

   @EventTarget
   public void onFirework(EventFireworkRocket event) {
      if ((Boolean)this.boost.get()) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            AttackAura aura = SkyCore.getInstance().getModuleManager().attackAura;
            if (aura == null) {
               return;
            }

            var10000 = mc;
            float yaw = MathHelper.wrapDegrees(Minecraft.player.rotationYaw);
            var10000 = mc;
            float pitch = Minecraft.player.rotationPitch;
            if (aura.isEnabled() && AttackAura.rotate != null) {
               yaw = MathHelper.wrapDegrees(AttackAura.rotate.x);
               pitch = AttackAura.rotate.y;
            }

            float speedXZ;
            float speedY;
            if (this.mode.is("HolyWorld")) {
               speedXZ = this.getHolyWorldXZ(yaw);
               speedY = this.getHolyWorldY(pitch);
               if (speedY > speedXZ) {
                  speedXZ = speedY;
               }

               event.setSpeed(speedXZ);
               event.setYSpeed(speedY);
            } else if (this.mode.is("Ultra")) {
               speedXZ = this.getUltraXZ(yaw);
               speedY = this.getUltraY(pitch);
               if (speedY > speedXZ) {
                  speedXZ = speedY;
               }

               event.setSpeed(speedXZ);
               event.setYSpeed(speedY);
            } else if (this.mode.is("Кастомный")) {
               speedXZ = this.getCustomSpeed(yaw, this.xzSliders);
               if ((Boolean)this.individualY.get()) {
                  speedY = this.getIndividualYSpeed(pitch);
               } else {
                  speedY = this.getCustomSpeed(pitch, this.yGroupSliders);
               }

               if (speedY > speedXZ) {
                  speedXZ = speedY;
               }

               event.setSpeed(speedXZ);
               event.setYSpeed(speedY);
            } else if (this.mode.is("Bravo")) {
               event.setSpeed(this.getBravoSpeedXZ(pitch, yaw));
               event.setYSpeed(this.getBravoSpeedY(pitch));
            } else if (this.mode.is("BravoGrief")) {
               event.setSpeed(getTableSpeed(BRAVO_GRIEF_XZ, yaw, 9));
               event.setYSpeed(getTableSpeed(BRAVO_GRIEF_Y, pitch, 9));
            } else if (this.mode.is("ReallyWorld")) {
               speedXZ = getTableSpeed(REALLY_WORLD_XZ, yaw, 9);
               speedY = getTableSpeed(REALLY_WORLD_Y, pitch, 9);
               if (speedY > speedXZ) {
                  speedXZ = speedY;
               }

               event.setSpeed(speedXZ);
               event.setYSpeed(speedY);
            }

            return;
         }
      }

   }

   private float getHolyWorldXZ(float yaw) {
      return getTableSpeed(HOLYWORLD_XZ, yaw, 9);
   }

   private float getHolyWorldY(float pitch) {
      return getTableSpeed(HOLYWORLD_Y, pitch, 9);
   }

   private float getUltraXZ(float yaw) {
      return getTableSpeed(ULTRA_XZ, yaw, 9);
   }

   private float getUltraY(float pitch) {
      return getTableSpeed(ULTRA_Y, pitch, 9);
   }

   private float getIndividualYSpeed(float pitch) {
      float absPitch = this.convertAngleForIndividual(pitch);
      int degree = Math.round(absPitch);
      if (degree < 0) {
         degree = 0;
      }

      if (degree > 55) {
         degree = 55;
      }

      return (Float)this.individualYSettings[degree].get();
   }

   private float convertAngleForIndividual(float angle) {
      float absAngle = Math.abs(angle);
      if (absAngle > 90.0F) {
         absAngle = 180.0F - absAngle;
      }

      return absAngle;
   }

   private float getCustomSpeed(float angle, SliderSetting[] sliders) {
      float converted = this.convertAngle(angle);
      int index = (int)(converted / 5.0F);
      if (index >= sliders.length) {
         index = sliders.length - 1;
      }

      if (index < 0) {
         index = 0;
      }

      return (Float)sliders[index].get();
   }

   private float convertAngle(float angle) {
      float absAngle = Math.abs(angle);
      if (absAngle > 90.0F) {
         absAngle = 180.0F - absAngle;
      }

      if (absAngle > 45.0F) {
         absAngle = 90.0F - absAngle;
      }

      return absAngle;
   }

   private static float getTableSpeed(float[] table, float angle, int tableLength) {
      int index = (int)(convertAngleStatic(angle) / 5.0F);
      if (index >= tableLength) {
         index = tableLength - 1;
      }

      if (index < 0) {
         index = 0;
      }

      return table[index];
   }

   private static float convertAngleStatic(float angle) {
      float absAngle = Math.abs(angle);
      if (absAngle > 90.0F) {
         absAngle = 180.0F - absAngle;
      }

      if (absAngle > 45.0F) {
         absAngle = 90.0F - absAngle;
      }

      return absAngle;
   }

   private float getBravoSpeedXZ(float pitch, float yaw) {
      float absPitch = Math.abs(pitch);
      float absYaw = Math.abs(MathHelper.wrapDegrees(yaw) % 90.0F);
      float speed = absPitch >= 38.0F && absPitch <= 52.0F ? 2.0F : (absPitch >= 32.0F && absPitch <= 58.0F ? 1.96F : (absPitch >= 28.0F && absPitch <= 62.0F ? 1.95F : (absYaw >= 29.0F && absYaw <= 61.0F || absPitch >= 29.0F && absPitch <= 61.0F ? 1.963F : (absYaw >= 28.0F && absYaw <= 60.0F || absPitch >= 28.0F && absPitch <= 60.0F ? 1.954F : (absYaw >= 26.0F && absYaw <= 64.0F || absPitch >= 26.0F && absPitch <= 64.0F ? 1.874F : (absYaw >= 24.0F && absYaw <= 66.0F || absPitch >= 24.0F && absPitch <= 66.0F ? 1.75F : (absYaw >= 15.0F && absYaw <= 75.0F || absPitch >= 15.0F && absPitch <= 75.0F ? 1.75F : (absYaw >= 13.0F && absYaw <= 77.0F || absPitch >= 13.0F && absPitch <= 77.0F ? 1.75F : (absYaw >= 12.0F && absYaw <= 78.0F || absPitch >= 12.0F && absPitch <= 78.0F ? 1.75F : (absYaw >= 8.0F && absYaw <= 82.0F || absPitch >= 11.0F && absPitch <= 79.0F ? 1.75F : ((!(absYaw >= 5.0F) || !(absYaw <= 85.0F)) && (!(absPitch >= 8.0F) || !(absPitch <= 82.0F)) ? (!(absYaw <= 90.0F) && !(absPitch <= 90.0F) ? 1.66F : 1.67F) : 1.67F)))))))))));
      return pitch > 15.0F ? speed - 0.068F : speed;
   }

   private float getBravoSpeedY(float pitch) {
      float absPitch = Math.abs(pitch);
      if (absPitch >= 37.0F && absPitch <= 38.0F) {
         return 2.03F;
      } else if (absPitch >= 25.0F && absPitch <= 30.0F) {
         return 2.0F;
      } else if (absPitch >= 35.0F && absPitch <= 45.0F) {
         return 1.99F;
      } else if (absPitch >= 40.0F && absPitch <= 50.0F) {
         return 1.97F;
      } else if (absPitch >= 50.0F && absPitch <= 60.0F) {
         return 1.96F;
      } else if (absPitch >= 51.0F && absPitch <= 61.0F) {
         return 1.85F;
      } else {
         return absPitch >= 52.0F && absPitch <= 65.0F ? 1.8F : 1.59F;
      }
   }

   @EventTarget
   private void onRender(EventRender2D event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null && !mc.gameSettings.showDebugInfo) {
         if (this.mode.is("Кастомный")) {
            int screenCenterX = mc.getMainWindow().getScaledWidth() / 2;
            if (Boolean.TRUE.equals(this.render.is("Углы"))) {
               var10000 = mc;
               String pitch = String.valueOf((int)Minecraft.player.getPitch(event.getPartialTicks()));
               String pitchText = "Pitch: " + pitch;
               float pitchWidth = Fonts.sf_medium[16].getWidth(pitchText);
               float x = (float)screenCenterX - pitchWidth / 2.0F;
               float y = (float)mc.getMainWindow().getScaledHeight() / 2.0F + 12.0F;
               Fonts.sf_medium[16].drawString(event.getStack(), pitchText, (double)x, (double)(y + 13.0F), ColorUtil.getColor(255, 255, 255, 255));
            }

         }
      }
   }
}
