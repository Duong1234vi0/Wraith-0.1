package sky.core.ui.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.text.ITextComponent;
import sky.core.SkyCore;
import sky.core.modules.Category;
import sky.core.modules.api.elements.Element;
import sky.core.modules.api.elements.ModuleElement;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.gui.additionals.Searcher;
import sky.core.ui.gui.autobuy.AutoBuyGui;
import sky.core.ui.gui.guihandlers.BindingPanelHandler;
import sky.core.ui.gui.guihandlers.ColorPickerHandler;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.Wrapper;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.math.MathUtil;
import sky.core.utils.misc.OtherUtil;
import sky.core.utils.render.font.Fonts;

public class DropDown extends Screen implements Wrapper {
   private final List panels = new ArrayList();
   private final Searcher searchField;
   private final ColorPickerHandler colorPickerManager;
   private final BindingPanelHandler bindingPanelManager;
   private boolean ctrlGPressed = false;
   private boolean themeEditorVisible;
   private boolean autoBuyVisible;
   private AnimationUtil openAnim = new AnimationUtil(0.0F, 10.0F);
   private boolean closing;

   public DropDown(ITextComponent titleIn) {
      super(titleIn);
      Category[] var2 = Category.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Category category = var2[var4];
         this.panels.add(new Panel(category));
      }

      this.panels.add(new ThemeEditor());
      this.panels.add(new AutoBuyGui());
      this.searchField = new Searcher(0.0F, 0.0F, 125.0F, 24.0F);
      this.colorPickerManager = new ColorPickerHandler(this.panels);
      this.bindingPanelManager = new BindingPanelHandler();
   }

   protected void init() {
      super.init();
      this.closing = false;
      this.openAnim.setValue(0.0F);
   }

   public void tick() {
      this.updateMovementKeysWhileOpen();
      super.tick();
   }

   public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
      this.openAnim.update(this.closing ? 0.0F : 1.0F);
      Vector2f mousevec = OtherUtil.getMouse(mouseX, mouseY);
      mouseX = (int)mousevec.getX();
      mouseY = (int)mousevec.getY();
      mc.gameRenderer.setupOverlayRendering(2.0F);
      this.themeEditorVisible = (Boolean)Interface.themeeditor.get();
      Iterator var6 = this.panels.iterator();

      while(var6.hasNext()) {
         Panel panel = (Panel)var6.next();
         if (!this.isPanelHidden(panel)) {
            this.setPanelPosition(panel, mc.getMainWindow().getScaledWidth(), mc.getMainWindow().getScaledHeight(), (float)((Category.values().length - 1) * 132), 125.0F, -0.9F, 295.0F);
            panel.render(matrixStack, (float)mouseX, (float)mouseY, this.openAnim.getValue());
         }
      }

      this.searchField.setX(((float)mc.getMainWindow().getScaledWidth() - this.searchField.getWidth()) / 2.0F);
      this.searchField.setY((float)mc.getMainWindow().getScaledHeight() / 2.0F + 158.0F);
      this.searchField.render(matrixStack, this.openAnim.getValue());
      this.updatePanelModules();
      this.renderModuleDescriptions(matrixStack, mouseX, mouseY);
      this.colorPickerManager.render(matrixStack, mouseX, mouseY);
      this.bindingPanelManager.render(matrixStack, mouseX, mouseY);
      mc.gameRenderer.setupOverlayRendering();
      if (this.closing && this.openAnim.isDone()) {
         this.closeScreen();
      }

   }

   private void updateMovementKeysWhileOpen() {
      KeyBinding[] keys = new KeyBinding[]{mc.gameSettings.keyBindForward, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindJump};
      KeyBinding[] var2 = keys;
      int var3 = keys.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         KeyBinding keyBinding = var2[var4];
         int keyCode = keyBinding.getDefault().getKeyCode();
         boolean isKeyPressed = keyCode >= 0 && InputMappings.isKeyDown(mc.getMainWindow().getHandle(), keyCode);
         keyBinding.setPressed(isKeyPressed);
      }

   }

   private boolean isPanelHidden(Panel panel) {
      return panel instanceof ThemeEditor && !this.themeEditorVisible || panel instanceof AutoBuyGui && !this.autoBuyVisible;
   }

   private void setPanelPosition(Panel panel, int windowWidth, int windowHeight, float width, float panelWidth, float offset, float panelHeight) {
      float baseY = (float)windowHeight / 2.0F - panelHeight / 2.0F + 0.5F;
      if (panel instanceof ThemeEditor) {
         panel.setX(10.0F);
         panel.setY(baseY);
      } else if (panel instanceof AutoBuyGui) {
         panel.setX((float)windowWidth - panel.getWidth() - 10.0F);
         panel.setY(baseY);
      } else {
         int count = Category.values().length;
         float totalWidth = (float)count * panelWidth + (float)(count - 1) * offset;
         float startX = (float)windowWidth / 2.0F - totalWidth / 2.0F;
         panel.setX(startX + (float)panel.getCategory().ordinal() * (panelWidth + offset));
         panel.setY(baseY);
      }
   }

   private void renderModuleDescriptions(MatrixStack matrixStack, int mouseX, int mouseY) {
      Iterator var4 = this.panels.iterator();

      while(true) {
         Panel panel;
         float visibleTop;
         float visibleBottom;
         do {
            do {
               do {
                  do {
                     if (!var4.hasNext()) {
                        return;
                     }

                     panel = (Panel)var4.next();
                  } while(panel instanceof ThemeEditor);
               } while(panel instanceof AutoBuyGui);

               visibleTop = panel.getY() + 28.0F;
               visibleBottom = visibleTop + panel.getHeight() - 28.0F;
            } while((float)mouseY < visibleTop);
         } while((float)mouseY > visibleBottom);

         Iterator var8 = panel.getModules().iterator();

         while(var8.hasNext()) {
            ModuleElement element = (ModuleElement)var8.next();
            if (MathUtil.isHovered((float)mouseX, (float)mouseY, element.getX(), element.getY(), element.getWidth(), element.getHeight())) {
               float elementTop = element.getY();
               float elementBottom = elementTop + element.getHeight();
               if (elementBottom > visibleTop && elementTop < visibleBottom) {
                  String description = element.getModule().getDescription();
                  if (description != null && !description.isEmpty()) {
                     float descWidth = Fonts.sf_semibold[20].getWidth(description);
                     float tooltipX = ((float)mc.getMainWindow().getScaledWidth() - descWidth) / 2.0F;
                     float tooltipY = 80.0F;
                     Fonts.sf_semibold[20].drawString(matrixStack, description, (double)tooltipX, (double)(tooltipY - 50.0F), ThemeEditor.getColor(ThemeSettings.TOOLTIP));
                  }

                  return;
               }
            }
         }
      }
   }

   private void updatePanelModules() {
      String searchText = this.searchField.getText().toLowerCase();
      Iterator var2 = this.panels.iterator();

      while(var2.hasNext()) {
         Panel panel = (Panel)var2.next();
         if (!(panel instanceof ThemeEditor)) {
            if (searchText.isEmpty()) {
               panel.setModules(new ArrayList(panel.getAllModules()));
            } else {
               List filteredModules = (List)panel.getAllModules().stream().filter((module) -> {
                  return module.getModule().getName().toLowerCase().contains(searchText);
               }).collect(Collectors.toList());
               panel.setModules(filteredModules);
            }
         }
      }

   }

   public boolean isPauseScreen() {
      return false;
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
      Vector2f mousevec = OtherUtil.getMouse((int)mouseX, (int)mouseY);
      mouseX = (double)mousevec.getX();
      mouseY = (double)mousevec.getY();
      Iterator var8 = this.panels.iterator();

      while(true) {
         Panel panel;
         do {
            if (!var8.hasNext()) {
               return super.mouseScrolled(mouseX, mouseY, delta);
            }

            panel = (Panel)var8.next();
         } while(panel instanceof ThemeEditor && !this.themeEditorVisible);

         if (MathUtil.isHovered((float)mouseX, (float)mouseY, panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight())) {
            boolean scrolled = panel.mouseScrolled(mouseX, mouseY, delta);
            if (scrolled) {
               if (this.colorPickerManager.hasOpenColorPicker(panel)) {
                  this.colorPickerManager.closeAllPickers();
               }

               if (this.bindingPanelManager.isActive()) {
                  this.bindingPanelManager.close();
               }

               return true;
            }
         }
      }
   }

   public boolean charTyped(char codePoint, int modifiers) {
      Iterator var3 = this.panels.iterator();

      while(true) {
         Panel panel;
         do {
            if (!var3.hasNext()) {
               if (this.searchField.isFocused()) {
                  this.searchField.charTyped(codePoint);
                  return true;
               }

               return super.charTyped(codePoint, modifiers);
            }

            panel = (Panel)var3.next();
         } while(panel instanceof ThemeEditor && !this.themeEditorVisible);

         panel.charTyped(codePoint, modifiers);
      }
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      if (this.searchField.isFocused()) {
         this.searchField.keyPressed(keyCode, scanCode, modifiers);
         return true;
      } else if (this.bindingPanelManager.keyPressed(keyCode)) {
         return true;
      } else {
         Iterator var4 = this.panels.iterator();

         while(true) {
            Panel panel;
            do {
               if (!var4.hasNext()) {
                  if (keyCode == 71 && (modifiers & 2) != 0) {
                     this.ctrlGPressed = true;
                     return true;
                  }

                  if (keyCode == 256) {
                     this.closing = true;
                     this.searchField.setText("");
                     return true;
                  }

                  return super.keyPressed(keyCode, scanCode, modifiers);
               }

               panel = (Panel)var4.next();
            } while(panel instanceof ThemeEditor && !this.themeEditorVisible);

            panel.keyPressed(keyCode, scanCode, modifiers);
         }
      }
   }

   public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
      if (keyCode == 71 || keyCode == 341 || keyCode == 345) {
         this.ctrlGPressed = false;
      }

      return super.keyReleased(keyCode, scanCode, modifiers);
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      Vector2f mousevec = OtherUtil.getMouse((int)mouseX, (int)mouseY);
      mouseX = (double)mousevec.getX();
      mouseY = (double)mousevec.getY();
      if (this.colorPickerManager.handleMouseClicked((float)mouseX, (float)mouseY, button)) {
         return true;
      } else if (this.bindingPanelManager.isActive() && this.bindingPanelManager.mouseClicked((float)mouseX, (float)mouseY, button)) {
         return true;
      } else {
         ModuleElement hovered;
         if (button == 2) {
            hovered = this.findHoveredModuleHeaderElement((int)mouseX, (int)mouseY);
            if (hovered != null) {
               this.bindingPanelManager.openForModule(hovered);
               return true;
            }
         }

         Iterator var12 = this.panels.iterator();

         Panel panel;
         while(var12.hasNext()) {
            panel = (Panel)var12.next();
            if (panel instanceof ThemeEditor) {
               ThemeEditor themeEditor = (ThemeEditor)panel;
               if (this.themeEditorVisible && MathUtil.isHovered((float)mouseX, (float)mouseY, themeEditor.getX(), themeEditor.getY(), themeEditor.getWidth(), themeEditor.getHeight())) {
                  themeEditor.mouseClicked((float)mouseX, (float)mouseY, button);
                  return true;
               }
            }
         }

         if (this.searchField.mouseClicked((float)mouseX, (float)mouseY)) {
            return true;
         } else {
            if (button == 1) {
               hovered = this.findHoveredModuleElement((int)mouseX, (int)mouseY);
               if (hovered != null && hovered.getElements().stream().anyMatch(Element::isVisible)) {
                  Iterator var13 = this.panels.iterator();

                  label75:
                  while(true) {
                     Panel panel;
                     do {
                        do {
                           if (!var13.hasNext()) {
                              break label75;
                           }

                           panel = (Panel)var13.next();
                        } while(this.isPanelHidden(panel));
                     } while(panel instanceof ThemeEditor);

                     Iterator var10 = panel.getModules().iterator();

                     while(var10.hasNext()) {
                        ModuleElement element = (ModuleElement)var10.next();
                        if (element != hovered) {
                           element.setOpen(false);
                        }
                     }
                  }
               }
            }

            var12 = this.panels.iterator();

            while(var12.hasNext()) {
               panel = (Panel)var12.next();
               if (!this.isPanelHidden(panel)) {
                  panel.mouseClicked((float)mouseX, (float)mouseY, button);
               }
            }

            return super.mouseClicked(mouseX, mouseY, button);
         }
      }
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      Vector2f mousevec = OtherUtil.getMouse((int)mouseX, (int)mouseY);
      mouseX = (double)((int)mousevec.getX());
      mouseY = (double)((int)mousevec.getY());
      Iterator var7 = this.panels.iterator();

      while(var7.hasNext()) {
         Panel panel = (Panel)var7.next();
         panel.mouseReleased((float)mouseX, (float)mouseY, button);
      }

      return super.mouseReleased(mouseX, mouseY, button);
   }

   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
      Vector2f mousevec = OtherUtil.getMouse((int)mouseX, (int)mouseY);
      mouseX = (double)((int)mousevec.getX());
      mouseY = (double)((int)mousevec.getY());
      Iterator var11 = this.panels.iterator();

      Panel panel;
      do {
         if (!var11.hasNext()) {
            return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
         }

         panel = (Panel)var11.next();
      } while(this.isPanelHidden(panel) || !MathUtil.isHovered((float)mouseX, (float)mouseY, panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight()) || !panel.mouseDragged(mouseX, mouseY, button, dragX, dragY));

      return true;
   }

   public void onClose() {
      SkyCore.getInstance().getConfigManager().saveConfig("default");
      this.colorPickerManager.closeAllPickers();
      this.bindingPanelManager.close();
      this.searchField.setText("");
      Iterator var1 = this.panels.iterator();

      while(var1.hasNext()) {
         Panel panel = (Panel)var1.next();
         if (panel instanceof ThemeEditor) {
            ((ThemeEditor)panel).clearContextMenu();
         }
      }

      super.onClose();
   }

   private ModuleElement findHoveredModuleElement(int mouseX, int mouseY) {
      Iterator var3 = this.panels.iterator();

      while(true) {
         Panel panel;
         float visibleTop;
         float visibleBottom;
         do {
            do {
               do {
                  do {
                     if (!var3.hasNext()) {
                        return null;
                     }

                     panel = (Panel)var3.next();
                  } while(this.isPanelHidden(panel));
               } while(panel instanceof ThemeEditor);

               visibleTop = panel.getY() + 28.0F;
               visibleBottom = visibleTop + panel.getHeight() - 35.0F;
            } while((float)mouseY < visibleTop);
         } while((float)mouseY > visibleBottom);

         Iterator var7 = panel.getModules().iterator();

         while(var7.hasNext()) {
            ModuleElement element = (ModuleElement)var7.next();
            if (MathUtil.isHovered((float)mouseX, (float)mouseY, element.getX(), element.getY(), element.getWidth(), element.getHeight())) {
               float elementTop = element.getY();
               float elementBottom = elementTop + element.getHeight();
               if (elementBottom > visibleTop && elementTop < visibleBottom) {
                  return element;
               }
            }
         }
      }
   }

   private ModuleElement findHoveredModuleHeaderElement(int mouseX, int mouseY) {
      Iterator var3 = this.panels.iterator();

      while(true) {
         Panel panel;
         float visibleTop;
         float visibleBottom;
         do {
            do {
               do {
                  do {
                     if (!var3.hasNext()) {
                        return null;
                     }

                     panel = (Panel)var3.next();
                  } while(this.isPanelHidden(panel));
               } while(panel instanceof ThemeEditor);

               visibleTop = panel.getY() + 28.0F;
               visibleBottom = visibleTop + panel.getHeight() - 35.0F;
            } while((float)mouseY < visibleTop);
         } while((float)mouseY > visibleBottom);

         Iterator var7 = panel.getModules().iterator();

         while(var7.hasNext()) {
            ModuleElement element = (ModuleElement)var7.next();
            if (MathUtil.isHovered((float)mouseX, (float)mouseY, element.getX(), element.getY(), element.getWidth(), 15.0F)) {
               float elementTop = element.getY();
               float elementBottom = elementTop + element.getHeight();
               if (elementBottom > visibleTop && elementTop < visibleBottom) {
                  return element;
               }
            }
         }
      }
   }

   public BindingPanelHandler getBindingPanelManager() {
      return this.bindingPanelManager;
   }
}
