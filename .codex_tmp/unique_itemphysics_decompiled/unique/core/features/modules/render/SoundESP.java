package unique.core.features.modules.render;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_243;
import net.minecraft.class_332;
import org.joml.Vector4d;
import unique.core.features.modules.Module;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public class SoundESP extends Module {
   private final Setting<Float> scale = new Setting<>("Scale", 1.0F, 0.1F, 10.0F);
   private final Setting<ColorSetting> fillColorA = new Setting<>("Color", new ColorSetting(Integer.MIN_VALUE));
   private List<SoundESP.Sound> sounds = new ArrayList<>();

   public SoundESP() {
      super("SoundESP", Module.Category.RENDER);
   }

   public void add(double x, double y, double z, String name) {
      this.sounds.add(new SoundESP.Sound(x, y, z, name.replace("minecraft.block.", "").replace("minecraft.entity", "").replace(".", " ")));
   }

   @Override
   public void onRender2D(class_332 context) {
      for (SoundESP.Sound s : Lists.newArrayList(this.sounds)) {
         class_243 vector = new class_243(s.x, s.y, s.z);
         Vector4d position = null;
         vector = Render3DEngine.worldSpaceToScreenSpace(vector);
         if (vector.field_1350 > 0.0 && vector.field_1350 < 1.0) {
            position = new Vector4d(vector.field_1352, vector.field_1351, vector.field_1350, 0.0);
            position.x = Math.min(vector.field_1352, position.x);
            position.y = Math.min(vector.field_1351, position.y);
            position.z = Math.max(vector.field_1352, position.z);
         }

         if (position != null) {
            double posX = position.x;
            double posY = position.y;
            double endPosX = position.z;
            float diff = (float)(endPosX - posX) / 2.0F;
            float textWidth = FontRenderers.sf_bold.getStringWidth(s.name) * 1.0F;
            float tagX = (float)((posX + diff - textWidth / 2.0F) * 1.0);
            float alpha = (float)(1.0 - Math.pow(1.0F - s.ticks / 60.0F, 3.0));
            context.method_51448().method_22903();
            context.method_51448().method_46416(tagX - 2.0F + (textWidth + 4.0F) / 2.0F, (float)(posY - 13.0) + 6.5F, 0.0F);
            context.method_51448().method_22905(this.scale.getValue(), this.scale.getValue(), 1.0F);
            context.method_51448().method_46416(-(tagX - 2.0F + (textWidth + 4.0F) / 2.0F), -((float)(posY - 13.0 + 6.5)), 0.0F);
            Render2DEngine.drawRect(
               context.method_51448(),
               tagX - 2.0F,
               (float)(posY - 13.0),
               textWidth + 4.0F,
               11.0F,
               this.fillColorA.getValue().withAlpha((int)(this.fillColorA.getValue().getAlpha() * alpha)).getColorObject()
            );
            FontRenderers.sf_bold.drawString(context.method_51448(), s.name, tagX, (float)posY - 10.0F, Render2DEngine.applyOpacity(-1, alpha));
            context.method_51448().method_22909();
         }
      }
   }

   @Override
   public void onUpdate() {
      this.sounds.removeIf(SoundESP.Sound::shouldRemove);
   }

   private class Sound {
      double x;
      double y;
      double z;
      String name;
      int ticks;

      public Sound(double x, double y, double z, String name) {
         this.x = x;
         this.y = y;
         this.z = z;
         this.name = name;
         this.ticks = 60;
      }

      public boolean shouldRemove() {
         return this.ticks-- <= 0;
      }
   }
}
