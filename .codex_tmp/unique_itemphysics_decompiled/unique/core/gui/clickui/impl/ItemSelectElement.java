package unique.core.gui.clickui.impl;

import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import unique.core.core.Managers;
import unique.core.core.manager.IManager;
import unique.core.gui.clickui.AbstractElement;
import unique.core.gui.font.FontRenderers;
import unique.core.gui.windows.WindowsScreen;
import unique.core.gui.windows.impl.ItemSelectWindow;
import unique.core.setting.Setting;
import unique.core.setting.impl.ItemSelectSetting;

public class ItemSelectElement extends AbstractElement {
   private final Setting<ItemSelectSetting> setting;

   public ItemSelectElement(Setting setting) {
      super(setting);
      this.setting = setting;
   }

   @Override
   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      super.render(context, mouseX, mouseY, delta);
      class_4587 matrixStack = context.method_51448();
      FontRenderers.icons.drawString(matrixStack, "H", this.x + this.width - 14.0F, this.y + 6.0F, new Color(-1250068, true).getRGB());
      FontRenderers.sf_medium_mini.drawString(matrixStack, this.setting.getName(), this.x + 6.0F, this.y + this.height / 2.0F - 1.0F, new Color(-1).getRGB());
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int button) {
      if (this.hovered) {
         IManager.mc.method_1507(new WindowsScreen(new ItemSelectWindow(this.getItemSetting())));
         Managers.SOUND.playSwipeIn();
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public Setting<ItemSelectSetting> getItemSetting() {
      return this.setting;
   }
}
