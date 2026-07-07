package unique.core.gui.clickui.impl;

import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import unique.core.core.Managers;
import unique.core.gui.clickui.AbstractElement;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.setting.impl.SettingGroup;
import unique.core.utility.render.TextureStorage;
import unique.core.utility.render.animation.AnimationUtility;

public class ParentElement extends AbstractElement {
   private final Setting<SettingGroup> parentSetting;
   private float animation;

   public ParentElement(Setting setting) {
      super(setting);
      this.parentSetting = setting;
   }

   @Override
   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      super.render(context, mouseX, mouseY, delta);
      class_4587 matrixStack = context.method_51448();
      float tx = this.x + this.width - 11.0F;
      float ty = this.y + 7.5F;
      this.animation = AnimationUtility.fast(this.animation, this.getParentSetting().getValue().isExtended() ? 0.0F : 1.0F, 15.0F);
      matrixStack.method_22903();
      matrixStack.method_46416(tx, ty, 0.0F);
      matrixStack.method_22907(class_7833.field_40718.rotationDegrees(-180.0F * this.animation));
      matrixStack.method_46416(-tx, -ty, 0.0F);
      matrixStack.method_46416(this.x + this.width - 14.0F, this.y + 4.5F, 0.0F);
      context.method_25290(TextureStorage.guiArrow, 0, 0, 0.0F, 0.0F, 6, 6, 6, 6);
      matrixStack.method_46416(-(this.x + this.width - 14.0F), -(this.y + 4.5F), 0.0F);
      matrixStack.method_22909();
      FontRenderers.sf_medium_mini
         .drawString(
            matrixStack,
            this.setting.getName(),
            this.x + 6.0F + 6 * this.getParentSetting().getValue().getHierarchy(),
            this.y + this.height / 2.0F - 1.0F,
            new Color(-1).getRGB()
         );
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int button) {
      if (this.hovered) {
         this.getParentSetting().getValue().setExtended(!this.getParentSetting().getValue().isExtended());
         if (this.getParentSetting().getValue().isExtended()) {
            Managers.SOUND.playSwipeIn();
         } else {
            Managers.SOUND.playSwipeOut();
         }
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public Setting<SettingGroup> getParentSetting() {
      return this.parentSetting;
   }
}
