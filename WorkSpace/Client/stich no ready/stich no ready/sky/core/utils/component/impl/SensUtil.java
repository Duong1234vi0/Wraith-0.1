package sky.core.utils.component.impl;

import sky.core.SkyCore;
import sky.core.utils.Wrapper;

public final class SensUtil implements Wrapper {
   public static float getSens(float rotation) {
      return getDeltaMouse(rotation) * getGCDValue();
   }

   public static float getGCDValue() {
      boolean testas = SkyCore.getInstance().getModuleManager().getAttackAura().componentMode.is("Funtime");
      double multiplier = testas ? 0.015 : 0.15;
      return (float)((double)getGCD() * multiplier);
   }

   public static float getGCD() {
      double mouseSensitivity = mc.gameSettings.mouseSensitivity;
      return (float)(Math.pow(mouseSensitivity * 0.6000000238418579 + 0.20000000298023224, 3.0) * 8.0);
   }

   public static float getDeltaMouse(float delta) {
      return (float)Math.round(delta / getGCDValue());
   }

   private SensUtil() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
