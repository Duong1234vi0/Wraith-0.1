package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import sky.core.SkyCore;
import sky.core.events.EventCrosshair;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.impl.movement.FreeCamera;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;

public class Crosshair extends Module {
   private final SliderSetting gap = new SliderSetting("Зазор", 2.0F, 0.0F, 6.0F, 0.5F);
   private final SliderSetting length = new SliderSetting("Длина", 3.0F, 2.0F, 5.0F, 0.5F);
   private final SliderSetting thickness = new SliderSetting("Толщина", 2.0F, 1.0F, 6.0F, 1.0F);
   private final BooleanSetting changeColorOnHover = new BooleanSetting("Менять цвет при наведении", false);
   private final BooleanSetting dynamicCrosshair = new BooleanSetting("Динамический разрыв", false);
   private final BooleanSetting showOutline = new BooleanSetting("Обводка", true);
   private final BooleanSetting showDot = new BooleanSetting("Точка в центре", true);

   public Crosshair() {
      super("Crosshair", "Позволяет кастомизировать прицел", Category.Visuals);
      this.addSettings(new Setting[]{this.gap, this.length, this.thickness, this.changeColorOnHover, this.dynamicCrosshair, this.showOutline, this.showDot});
   }

   @EventTarget
   public void onRender2D(EventCrosshair e) {
      if (mc.gameSettings.getPointOfView().firstPerson() || SkyCore.getInstance().getModuleManager().getModule(FreeCamera.class).isEnabled()) {
         float cx = (float)mc.getMainWindow().getScaledWidth() / 2.0F;
         float cy = (float)mc.getMainWindow().getScaledHeight() / 2.0F;
         Minecraft var10001 = mc;
         float cooldown = 1.0F - Minecraft.player.getCooledAttackStrength(e.getPartialTicks());
         this.drawCustomCrosshair(e.getStack(), cx, cy, cooldown);
         e.setCancelled(true);
      }

   }

   private void drawCustomCrosshair(MatrixStack stack, float centerX, float centerY, float cooldown) {
      float gapValue = (Float)this.gap.get();
      float thicknessValue = (Float)this.thickness.get();
      float lengthValue = (Float)this.length.get();
      float actualGap = (Boolean)this.dynamicCrosshair.get() ? gapValue + 8.0F * cooldown : gapValue;
      float outline = 1.0F;
      int color = (Boolean)this.changeColorOnHover.get() && mc.pointedEntity != null ? ColorUtil.getColor(255, 64, 64) : -1;
      if ((Boolean)this.showOutline.get()) {
         RenderUtil.drawMinecraftRectangle(stack, centerX + actualGap - outline / 2.0F, centerY - thicknessValue / 2.0F - outline / 2.0F, lengthValue + outline, thicknessValue + outline, ColorUtil.getColor(0, 0, 0));
         RenderUtil.drawMinecraftRectangle(stack, centerX - actualGap - lengthValue - outline / 2.0F, centerY - thicknessValue / 2.0F - outline / 2.0F, lengthValue + outline, thicknessValue + outline, ColorUtil.getColor(0, 0, 0));
         RenderUtil.drawMinecraftRectangle(stack, centerX - thicknessValue / 2.0F - outline / 2.0F, centerY - actualGap - lengthValue - outline / 2.0F, thicknessValue + outline, lengthValue + outline, ColorUtil.getColor(0, 0, 0));
         RenderUtil.drawMinecraftRectangle(stack, centerX - thicknessValue / 2.0F - outline / 2.0F, centerY + actualGap - outline / 2.0F, thicknessValue + outline, lengthValue + outline, ColorUtil.getColor(0, 0, 0));
         RenderUtil.drawMinecraftRectangle(stack, centerX + actualGap, centerY - thicknessValue / 2.0F, lengthValue, thicknessValue, color);
         RenderUtil.drawMinecraftRectangle(stack, centerX - actualGap - lengthValue, centerY - thicknessValue / 2.0F, lengthValue, thicknessValue, color);
         RenderUtil.drawMinecraftRectangle(stack, centerX - thicknessValue / 2.0F, centerY - actualGap - lengthValue, thicknessValue, lengthValue, color);
         RenderUtil.drawMinecraftRectangle(stack, centerX - thicknessValue / 2.0F, centerY + actualGap, thicknessValue, lengthValue, color);
      } else {
         RenderUtil.drawMinecraftRectangle(stack, centerX + actualGap, centerY - thicknessValue / 2.0F, lengthValue, thicknessValue, color);
         RenderUtil.drawMinecraftRectangle(stack, centerX - actualGap - lengthValue, centerY - thicknessValue / 2.0F, lengthValue, thicknessValue, color);
         RenderUtil.drawMinecraftRectangle(stack, centerX - thicknessValue / 2.0F, centerY - actualGap - lengthValue, thicknessValue, lengthValue, color);
         RenderUtil.drawMinecraftRectangle(stack, centerX - thicknessValue / 2.0F, centerY + actualGap, thicknessValue, lengthValue, color);
      }

      if ((Boolean)this.showDot.get() && actualGap > 0.0F) {
         float dx = centerX - thicknessValue / 2.0F;
         float dy = centerY - thicknessValue / 2.0F;
         if ((Boolean)this.showOutline.get()) {
            RenderUtil.drawMinecraftRectangle(stack, dx - outline / 2.0F, dy - outline / 2.0F, thicknessValue + outline, thicknessValue + outline, ColorUtil.getColor(0, 0, 0));
            RenderUtil.drawMinecraftRectangle(stack, dx, dy, thicknessValue, thicknessValue, color);
         } else {
            RenderUtil.drawMinecraftRectangle(stack, dx, dy, thicknessValue, thicknessValue, color);
         }
      }

   }
}
