package unique.core.gui.clickui;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_3675;
import net.minecraft.class_437;
import org.lwjgl.glfw.GLFW;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.hud.HudElement;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClickGui;
import unique.core.features.modules.client.ClientSettings;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.font.FontRenderers;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.animation.AnimationUtility;
import unique.core.utility.render.animation.EaseOutBack;

public class ClickGUI extends class_437 {
   public static List<AbstractCategory> windows;
   public static boolean anyHovered;
   private boolean firstOpen;
   private float scrollY;
   private float closeAnimation;
   private float prevYaw;
   private float prevPitch;
   private float closeDirectionX;
   private float closeDirectionY;
   public static boolean close = false;
   public static boolean imageDirection;
   public static String currentDescription = "";
   public EaseOutBack imageAnimation = new EaseOutBack(6);
   private static ClickGUI INSTANCE = new ClickGUI();

   public ClickGUI() {
      super(class_2561.method_30163("NewClickGUI"));
      windows = Lists.newArrayList();
      this.firstOpen = true;
      this.setInstance();
   }

   public static ClickGUI getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new ClickGUI();
      }

      imageDirection = true;
      return INSTANCE;
   }

   public static ClickGUI getClickGui() {
      windows.forEach(AbstractCategory::init);
      return getInstance();
   }

   private void setInstance() {
      INSTANCE = this;
   }

   protected void method_25426() {
      if (this.firstOpen) {
         float offset = 0.0F;
         int windowHeight = 18;
         int halfWidth = Module.mc.method_22683().method_4486() / 2;
         int halfWidthCats = (int)((Module.Category.values().size() - 1.0F) / 2.0F * (ModuleManager.clickGui.moduleWidth.getValue().intValue() + 4.0F));

         for (Module.Category category : Managers.MODULE.getCategories()) {
            if (category != Module.Category.HUD) {
               Category window = new Category(
                  category, Managers.MODULE.getModulesByCategory(category), halfWidth - halfWidthCats + offset, 20.0F, 100.0F, windowHeight
               );
               window.setOpen(true);
               windows.add(window);
               offset += ModuleManager.clickGui.moduleWidth.getValue() + 2;
               if (offset > Module.mc.method_22683().method_4486()) {
                  offset = 0.0F;
               }
            }
         }

         this.firstOpen = false;
      } else if (windows.getFirst().getX() < 0.0F || windows.getFirst().getY() < 0.0F) {
         float offset = 0.0F;
         int halfWidth = Module.mc.method_22683().method_4486() / 2;
         int halfWidthCats = (int)(3.0F * (ModuleManager.clickGui.moduleWidth.getValue().intValue() + 4.0F));

         for (AbstractCategory w : windows) {
            w.setX(halfWidth - halfWidthCats + offset);
            w.setY(20.0F);
            offset += ModuleManager.clickGui.moduleWidth.getValue() + 2;
            if (offset > Module.mc.method_22683().method_4486()) {
               offset = 0.0F;
            }
         }
      }

      windows.forEach(AbstractCategory::init);
   }

   public boolean method_25421() {
      return false;
   }

   public void method_25393() {
      windows.forEach(AbstractCategory::tick);
      this.imageAnimation.update(imageDirection);
      if (close) {
         if (Module.mc.field_1724 != null) {
            if (Module.mc.field_1724.method_36455() > this.prevPitch) {
               this.closeDirectionY = (this.prevPitch - Module.mc.field_1724.method_36455()) * 300.0F;
            }

            if (Module.mc.field_1724.method_36455() < this.prevPitch) {
               this.closeDirectionY = (this.prevPitch - Module.mc.field_1724.method_36455()) * 300.0F;
            }

            if (Module.mc.field_1724.method_36454() > this.prevYaw) {
               this.closeDirectionX = (this.prevYaw - Module.mc.field_1724.method_36454()) * 300.0F;
            }

            if (Module.mc.field_1724.method_36454() < this.prevYaw) {
               this.closeDirectionX = (this.prevYaw - Module.mc.field_1724.method_36454()) * 300.0F;
            }
         }

         if (this.closeDirectionX < 1.0F && this.closeDirectionY < 1.0F && this.closeAnimation > 2.0F) {
            this.closeDirectionY = -3000.0F;
         }

         this.closeAnimation++;
         if (this.closeAnimation > 6.0F) {
            close = false;
            windows.forEach(AbstractCategory::restorePos);
            this.method_25419();
         }
      }
   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      if (ModuleManager.clickGui.blur.getValue()) {
         this.method_57734(delta);
      }

      anyHovered = false;
      ClickGui.Image image = ModuleManager.clickGui.image.getValue();
      if (image != ClickGui.Image.None) {
         RenderSystem.setShaderTexture(0, image.file);
         RenderSystem.setShaderTexture(0, image.file);
         RenderSystem.texParameter(3553, 10241, 9728);
         RenderSystem.texParameter(3553, 10240, 9728);
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         Render2DEngine.renderTexture(
            context.method_51448(),
            Module.mc.method_22683().method_4486() - image.fileWidth * this.imageAnimation.getAnimationd(),
            Module.mc.method_22683().method_4502() - image.fileHeight,
            image.fileWidth,
            image.fileHeight,
            0.0F,
            0.0F,
            image.fileWidth,
            image.fileHeight,
            image.fileWidth,
            image.fileHeight
         );
      }

      if (this.closeAnimation <= 6.0F) {
         windows.forEach(w -> {
            w.setX(w.getX() + this.closeDirectionX * AnimationUtility.deltaTime());
            w.setY(w.getY() + this.closeDirectionY * AnimationUtility.deltaTime());
         });
      }

      if (Module.fullNullCheck()) {
         this.method_25420(context, mouseX, mouseY, delta);
      }

      if (ModuleManager.clickGui.scrollMode.getValue() == ClickGui.scrollModeEn.Old) {
         for (AbstractCategory window : windows) {
            if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 264)) {
               window.setY(window.getY() + 2.0F);
            }

            if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 265)) {
               window.setY(window.getY() - 2.0F);
            }

            if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 262)) {
               window.setX(window.getX() + 2.0F);
            }

            if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 263)) {
               window.setX(window.getX() - 2.0F);
            }

            if (this.scrollY != 0.0F) {
               window.setY(window.getY() + this.scrollY);
            }
         }
      } else {
         for (AbstractCategory window : windows) {
            if (this.scrollY != 0.0F) {
               window.setModuleOffset(this.scrollY, mouseX, mouseY);
            }
         }
      }

      this.scrollY = 0.0F;
      windows.forEach(w -> w.render(context, mouseX, mouseY, delta));
      if (!Objects.equals(currentDescription, "") && ModuleManager.clickGui.descriptions.getValue()) {
         Render2DEngine.drawHudBase(
            context.method_51448(), mouseX + 7, mouseY + 5, FontRenderers.sf_medium.getStringWidth(currentDescription) + 6.0F, 11.0F, 1.0F, false
         );
         FontRenderers.sf_medium.drawString(context.method_51448(), currentDescription, mouseX + 10, mouseY + 8, HudEditor.getColor(0).getRGB());
         currentDescription = "";
      }

      if (ModuleManager.clickGui.tips.getValue() && !close) {
         FontRenderers.sf_medium
            .drawString(
               context.method_51448(),
               ClientSettings.isRu()
                  ? "Щелкните левой кнопкой мыши, чтобы включить модуль.\nЩелкните правой кнопкой мыши, чтобы открыть настройки модуля.\nЩелкните колёсиком мыши, чтобы привязать модуль\nCtrl + F, чтобы начать поиск\nПерекиньте конфиг в окошко майна, чтобы загрузить его\nShift + Left Mouse Click, чтобы изменить отображение модуля в Array list\nЩелкните колёсиком мыши по слайдеру, чтобы ввести значение с клавиатуры.\nDelete + Left Mouse Click по модулю, чтобы сбросить его настройки"
                  : "Left Mouse Click To Enable Module\nRight Mouse Click To Open Module Settings\nMiddle Mouse Click To Bind Module\nCtrl + F To Start Searching\nDrag n Drop Config There To Load\nShift + Left Mouse Click To Change Module Visibility In Array list\nMiddle Mouse Click On Slider To Enter Value From Keyboard\nDelete + Left Mouse Click On Module To Reset",
               5.0,
               Module.mc.method_22683().method_4502() - 80,
               HudEditor.getColor(0).getRGB()
            );
      }

      if (!HudElement.anyHovered && !anyHovered && GLFW.glfwGetPlatform() != 393219) {
         GLFW.glfwSetCursor(Module.mc.method_22683().method_4490(), GLFW.glfwCreateStandardCursor(221185));
      }
   }

   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
      this.scrollY += (int)(verticalAmount * 5.0);
      return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      windows.forEach(w -> {
         w.mouseClicked((int)mouseX, (int)mouseY, button);
         windows.forEach(w1 -> {
            if (w.dragging && w != w1) {
               w1.dragging = false;
            }
         });
      });
      return super.method_25402(mouseX, mouseY, button);
   }

   public boolean method_25406(double mouseX, double mouseY, int button) {
      windows.forEach(w -> w.mouseReleased((int)mouseX, (int)mouseY, button));
      return super.method_25406(mouseX, mouseY, button);
   }

   public boolean method_25400(char key, int modifier) {
      windows.forEach(w -> w.charTyped(key, modifier));
      return true;
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      windows.forEach(w -> w.keyTyped(keyCode));
      if (keyCode == 256) {
         if (Module.mc.field_1724 == null || !ModuleManager.clickGui.closeAnimation.getValue()) {
            imageDirection = false;
            this.imageAnimation.reset();
            super.method_25404(keyCode, scanCode, modifiers);
            return true;
         } else if (close) {
            return true;
         } else {
            imageDirection = false;
            windows.forEach(AbstractCategory::savePos);
            this.closeDirectionX = 0.0F;
            this.closeDirectionY = 0.0F;
            close = true;
            Module.mc.field_1729.method_1612();
            this.closeAnimation = 0.0F;
            if (Module.mc.field_1724 != null) {
               this.prevYaw = Module.mc.field_1724.method_36454();
               this.prevPitch = Module.mc.field_1724.method_36455();
            }

            return true;
         }
      } else {
         return false;
      }
   }
}
