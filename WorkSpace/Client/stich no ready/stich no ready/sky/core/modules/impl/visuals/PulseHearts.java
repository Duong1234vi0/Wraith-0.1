package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import sky.core.SkyCore;
import sky.core.events.EventRender2D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.Wrapper;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;

public class PulseHearts extends Module implements Wrapper {
   private final SliderSetting hpThreshold = new SliderSetting("Порог ХП", 20.0F, 1.0F, 40.0F, 1.0F);
   private final AnimationUtil heartAnimation;
   private long lastAnimationTime;
   private boolean fadingIn;
   private float currentAlpha;

   public PulseHearts() {
      super("PulseHearts", "Делает сердца в хотбаре моргающими при низком ХП", Category.Visuals);
      this.heartAnimation = new AnimationUtil(1.0F, 8.0F, Easings.SINE_IN_OUT);
      this.lastAnimationTime = 0L;
      this.fadingIn = true;
      this.currentAlpha = 1.0F;
      this.addSettings(new Setting[]{this.hpThreshold});
   }

   @EventTarget
   public void onRender2D(EventRender2D.Post e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && !mc.gameSettings.showDebugInfo) {
         var10000 = mc;
         PlayerEntity player = Minecraft.player;
         float currentHealth = player.getHealth();
         float threshold = (Float)this.hpThreshold.get();
         if (currentHealth <= threshold) {
            float healthRatio = currentHealth / threshold;
            float blinkSpeed = 200.0F + 800.0F * healthRatio;
            long currentTime = System.currentTimeMillis();
            if ((float)(currentTime - this.lastAnimationTime) >= blinkSpeed) {
               this.fadingIn = !this.fadingIn;
               this.lastAnimationTime = currentTime;
            }

            float targetAlpha = this.fadingIn ? 1.0F : 0.3F;
            this.heartAnimation.update(targetAlpha);
            this.currentAlpha = this.heartAnimation.getValue();
         } else {
            this.currentAlpha = 1.0F;
         }

      }
   }

   public static float getHeartAlpha() {
      try {
         Module module = SkyCore.getInstance().getModuleManager().getModule(PulseHearts.class);
         if (module instanceof PulseHearts && module.isEnabled()) {
            return ((PulseHearts)module).currentAlpha;
         }
      } catch (Exception var1) {
      }

      return 1.0F;
   }
}
