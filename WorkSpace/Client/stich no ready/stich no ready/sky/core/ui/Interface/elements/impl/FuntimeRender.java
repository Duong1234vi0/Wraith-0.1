package sky.core.ui.Interface.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.vector.Vector4f;
import sky.core.SkyCore;
import sky.core.events.EventRender2D;
import sky.core.modules.Module;
import sky.core.modules.impl.miscellaneous.FunTimeHelper;
import sky.core.ui.Interface.elements.ElementRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.client.KeyStorage;
import sky.core.utils.managers.impl.dragmanager.Dragging;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class FuntimeRender implements ElementRender {
   private final Dragging dragging;

   public FuntimeRender(Dragging dragging) {
      this.dragging = dragging;
   }

   public void render(EventRender2D.Post event) {
      float posX = this.dragging.getX();
      float posY = this.dragging.getY();
      MatrixStack ms = event.getStack();
      this.renderKeybinds(ms, posX, posY);
   }

   private void renderKeybinds(MatrixStack ms, float posX, float posY) {
      Module funTimeHelper = SkyCore.getInstance().getModuleManager().getModule(FunTimeHelper.class);
      if (funTimeHelper != null && funTimeHelper instanceof FunTimeHelper helper) {
         String[][] binds = new String[][]{{"Дезорент", KeyStorage.getKey((Integer)helper.useDezor.get())}, {"Трапка", KeyStorage.getKey((Integer)helper.useTrap.get())}, {"Снежок", KeyStorage.getKey((Integer)helper.useSnow.get())}, {"Явная Пыль", KeyStorage.getKey((Integer)helper.usePil.get())}, {"Пласт", KeyStorage.getKey((Integer)helper.usePlast.get())}, {"Божья Аура", KeyStorage.getKey((Integer)helper.useAura.get())}, {"Смерч", KeyStorage.getKey((Integer)helper.useSmerch.get())}};
         float currentX = posX;
         String[][] var8 = binds;
         int var9 = binds.length;

         for(int var10 = 0; var10 < var9; ++var10) {
            String[] bind = var8[var10];
            String name = bind[0];
            String key = this.formatKey(bind[1]);
            if (!key.equals("None")) {
               ItemStack var10000;
               switch (name) {
                  case "Дезорент":
                     var10000 = Items.ENDER_EYE.getDefaultInstance();
                     break;
                  case "Трапка":
                     var10000 = Items.NETHERITE_SCRAP.getDefaultInstance();
                     break;
                  case "Снежок":
                     var10000 = Items.SNOWBALL.getDefaultInstance();
                     break;
                  case "Явная Пыль":
                     var10000 = Items.SUGAR.getDefaultInstance();
                     break;
                  case "Пласт":
                     var10000 = Items.DRIED_KELP.getDefaultInstance();
                     break;
                  case "Божья Аура":
                     var10000 = Items.PHANTOM_MEMBRANE.getDefaultInstance();
                     break;
                  case "Смерч":
                     var10000 = Items.FIRE_CHARGE.getDefaultInstance();
                     break;
                  default:
                     var10000 = Items.BARRIER.getDefaultInstance();
               }

               ItemStack icon = var10000;
               float nameWidth = Fonts.sf_medium[14].getWidth(name);
               float keyWidth = Fonts.sf_medium[14].getWidth(key);
               float maxTextWidth = Math.max(nameWidth, keyWidth);
               float rectWidth = 20.0F + maxTextWidth + 6.0F;
               int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
               int darkBgColor = ColorUtil.darken(logoColor, 0.1F);
               RenderUtil.drawRoundedRectangle(currentX - 0.5F, posY - 0.5F, rectWidth + 1.0F, 17.0F, new Vector4f(4.0F, 4.0F, 4.0F, 4.0F), ColorUtil.applyOpacity(darkBgColor, 90));
               RenderUtil.drawRoundedRectangle(currentX, posY, rectWidth, 16.0F, new Vector4f(3.5F, 3.5F, 3.5F, 3.5F), ColorUtil.applyOpacity(darkBgColor, 190));
               float iconBgSize = 13.0F;
               float iconBgX = currentX + 3.0F - 1.0F;
               float iconBgY = posY + 8.0F - iconBgSize / 2.0F;
               RenderUtil.drawRoundedRectangle(iconBgX, iconBgY, iconBgSize, iconBgSize, new Vector4f(3.0F, 3.0F, 3.0F, 3.0F), ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), 60));
               float iconX = currentX + 3.0F - 1.7F;
               float iconY = posY + 6.4F - 5.5F;
               RenderUtil.drawStack(icon, iconX, iconY, 0.85F);
               float textX = currentX + 3.0F + 11.0F + 3.0F + 1.0F;
               Fonts.sf_medium[14].drawString(ms, name, (double)textX, (double)(posY + 3.5F), -1);
               Fonts.sf_medium[11].drawString(ms, key, (double)textX, (double)(posY + 16.0F - 5.5F), -1);
               currentX += rectWidth + 4.0F;
            }
         }

         this.dragging.setWidth(Math.max(0.0F, currentX - posX - 4.0F));
         this.dragging.setHeight(16.0F);
      }

   }

   private void drawShadow(float x, float y, float width, float height, int blur, int color) {
      RenderUtil.drawRoundedRectangle(x - (float)blur, y - (float)blur, width + (float)(blur * 2), height + (float)(blur * 2), new Vector4f(4.0F, 4.0F, 4.0F, 4.0F), color);
   }

   private String formatKey(String var1) {
      // $FF: Couldn't be decompiled
   }
}
