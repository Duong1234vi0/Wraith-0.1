package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LightType;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class FullBright extends Module {
   private static FullBright instance;
   public ModeSetting mode = new ModeSetting("Режим", "Гамма", new String[]{"Гамма", "Зелье"});
   public BooleanSetting adaptiveBrightness = new BooleanSetting("Адаптивная яркость", false, () -> {
      return this.mode.is("Гамма");
   });
   public SliderSetting gammaValue = new SliderSetting("Яркость", 8.0F, 0.5F, 20.0F, 0.1F, () -> {
      return !(Boolean)this.adaptiveBrightness.get() && this.mode.is("Гамма");
   });
   private double savedGamma;
   private float renderGamma;

   public FullBright() {
      super("Full Bright", "Освещает темные места в мире", Category.Visuals);
      this.addSettings(new Setting[]{this.mode, this.adaptiveBrightness, this.gammaValue});
      instance = this;
   }

   public static float getGammaForRendering(float vanillaGamma) {
      return instance != null && instance.isEnabled() && instance.mode.is("Гамма") ? instance.renderGamma : vanillaGamma;
   }

   public void onEnable() {
      if (mc.gameSettings != null) {
         this.savedGamma = mc.gameSettings.gamma;
         this.renderGamma = (float)this.savedGamma;
      }

      super.onEnable();
   }

   @EventTarget
   public void onEvent(EventUpdate event) {
      Minecraft var10000;
      if (this.mode.is("Гамма")) {
         if ((Boolean)this.adaptiveBrightness.get()) {
            var10000 = mc;
            BlockPos p = Minecraft.player.getPosition();
            int lvl = Math.max(mc.world.getLightFor(LightType.BLOCK, p), mc.world.getLightFor(LightType.SKY, p));
            double t = 1.2 + Math.pow(1.0 - (double)lvl / 15.0, 2.0) * 10.8;
            float target = MathHelper.clamp((float)t, 1.2F, 12.0F);
            this.renderGamma += (target - this.renderGamma) * 0.3F;
         } else {
            this.renderGamma = (Float)this.gammaValue.get() / 5.0F;
         }
      }

      if (this.mode.is("Зелье")) {
         var10000 = mc;
         Minecraft.player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 1337, 1));
      } else {
         var10000 = mc;
         Minecraft.player.removePotionEffect(Effects.NIGHT_VISION);
      }

   }

   public void onDisable() {
      if (mc.gameSettings != null) {
         mc.gameSettings.gamma = this.savedGamma;
      }

      this.renderGamma = 0.0F;
      Minecraft var10000 = mc;
      Minecraft.player.removeActivePotionEffect((new EffectInstance(Effects.NIGHT_VISION)).getPotion());
      super.onDisable();
   }
}
