package sky.core.ui.gui.guihandlers;

import com.mojang.blaze3d.matrix.MatrixStack;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.util.Iterator;
import java.util.List;
import sky.core.modules.api.elements.Element;
import sky.core.modules.api.elements.ModuleElement;
import sky.core.modules.api.elements.impl.ColorElement;
import sky.core.modules.api.elements.impl.ColorPickerElement;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.gui.Panel;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.utils.math.MathUtil;

public class ColorPickerHandler {
   private final List panels;

   public ColorPickerHandler(List panels) {
      this.panels = panels;
   }

   public void render(MatrixStack matrixStack, int mouseX, int mouseY) {
      Iterator var4 = this.panels.iterator();

      while(true) {
         label56:
         while(true) {
            Panel panel;
            do {
               if (!var4.hasNext()) {
                  return;
               }

               panel = (Panel)var4.next();
            } while(this.isPanelHidden(panel));

            Iterator var7;
            if (panel instanceof ThemeEditor themeEditor) {
               var7 = themeEditor.getColorPickers().iterator();

               while(true) {
                  ColorElement ce;
                  ColorPickerElement picker;
                  do {
                     if (!var7.hasNext()) {
                        continue label56;
                     }

                     ce = (ColorElement)var7.next();
                     picker = ce.getColorPicker();
                  } while(!picker.isColorPickMode() && !picker.getOpenAnimation().isAlive());

                  picker.setX(ce.getX() + 110.0F);
                  picker.setY(ce.getY());
                  picker.render(matrixStack, (float)mouseX, (float)mouseY);
               }
            } else {
               var7 = panel.getModules().iterator();

               label54:
               while(var7.hasNext()) {
                  ModuleElement module = (ModuleElement)var7.next();
                  ObjectListIterator var9 = module.getElements().iterator();

                  while(true) {
                     ColorElement ce;
                     ColorPickerElement picker;
                     do {
                        Element elem;
                        do {
                           if (!var9.hasNext()) {
                              continue label54;
                           }

                           elem = (Element)var9.next();
                        } while(!(elem instanceof ColorElement));

                        ce = (ColorElement)elem;
                        picker = ce.getColorPicker();
                     } while(!picker.isColorPickMode() && !picker.getOpenAnimation().isAlive());

                     picker.setX(ce.getX() + ce.getWidth() - 7.0F);
                     picker.setY(ce.getY() - 3.0F);
                     picker.render(matrixStack, (float)mouseX, (float)mouseY);
                  }
               }
            }
         }
      }
   }

   public boolean handleMouseClicked(float mouseX, float mouseY, int button) {
      boolean isColorPickerOpen = false;
      ColorPickerElement activeEyedropperPicker = null;
      Iterator var6 = this.panels.iterator();

      while(true) {
         while(true) {
            Panel panel;
            ThemeEditor themeEditor;
            Iterator var9;
            ModuleElement module;
            ObjectListIterator var11;
            Element elem;
            ColorElement ce;
            ColorPickerElement picker;
            ColorElement ce;
            ColorPickerElement picker;
            do {
               if (!var6.hasNext()) {
                  if (activeEyedropperPicker != null) {
                     activeEyedropperPicker.mouseClicked(mouseX, mouseY);
                     return true;
                  }

                  var6 = this.panels.iterator();

                  while(true) {
                     label204:
                     while(true) {
                        do {
                           if (!var6.hasNext()) {
                              if (isColorPickerOpen && (button == 1 || button == 2)) {
                                 this.closeAllPickers();
                                 return true;
                              }

                              if (isColorPickerOpen && button == 0) {
                                 this.closeAllPickers();
                                 return true;
                              }

                              return false;
                           }

                           panel = (Panel)var6.next();
                        } while(this.isPanelHidden(panel));

                        if (panel instanceof ThemeEditor) {
                           themeEditor = (ThemeEditor)panel;
                           var9 = themeEditor.getColorPickers().iterator();

                           float effectiveY;
                           float scissorTop;
                           float scissorBottom;
                           do {
                              do {
                                 do {
                                    do {
                                       if (!var9.hasNext()) {
                                          continue label204;
                                       }

                                       ce = (ColorElement)var9.next();
                                       picker = ce.getColorPicker();
                                       if (picker.isColorPickMode()) {
                                          effectiveY = 93.0F + (picker.getSetting().alphaBar ? 0.0F : -7.0F);
                                          scissorTop = 100.0F + (picker.getSetting().alphaBar ? 10.0F : -0.0F);
                                          if (MathUtil.isHovered(mouseX, mouseY, picker.getX() + 7.0F, picker.getY(), effectiveY, scissorTop)) {
                                             picker.mouseClicked(mouseX, mouseY);
                                             return true;
                                          }
                                       }

                                       effectiveY = ce.getY();
                                       scissorTop = themeEditor.getPickersScissorTop();
                                       scissorBottom = themeEditor.getPickersScissorBottom();
                                    } while(!ce.isVisible());
                                 } while(button != 0);
                              } while(themeEditor.getCurrentPreset() != null && !themeEditor.getCurrentPreset().isCustom());
                           } while(!MathUtil.isHovered(mouseX, mouseY, ce.getX() + 2.5F, ce.getY() - 1.5F, ce.getWidth() - 5.5F, ce.getHeight()) || !(effectiveY < scissorBottom) || !(effectiveY + ce.getHeight() > scissorTop));

                           Iterator var30 = themeEditor.getColorPickers().iterator();

                           while(var30.hasNext()) {
                              ColorElement otherCe = (ColorElement)var30.next();
                              if (otherCe != ce && otherCe.getColorPicker().isColorPickMode()) {
                                 otherCe.getColorPicker().onClose();
                              }
                           }

                           picker.setColorPickMode(!picker.isColorPickMode());
                           return true;
                        } else {
                           var9 = panel.getModules().iterator();

                           while(var9.hasNext()) {
                              module = (ModuleElement)var9.next();
                              var11 = module.getElements().iterator();

                              while(var11.hasNext()) {
                                 elem = (Element)var11.next();
                                 if (elem instanceof ColorElement) {
                                    ce = (ColorElement)elem;
                                    picker = ce.getColorPicker();
                                    float scissorTop;
                                    float scissorBottom;
                                    if (picker.isColorPickMode()) {
                                       scissorTop = 93.0F + (picker.getSetting().alphaBar ? 0.0F : -7.0F);
                                       scissorBottom = 100.0F + (picker.getSetting().alphaBar ? 10.0F : -0.0F);
                                       if (MathUtil.isHovered(mouseX, mouseY, picker.getX() + 7.0F, picker.getY() - 2.0F, scissorTop, scissorBottom + 1.0F)) {
                                          picker.mouseClicked(mouseX, mouseY);
                                          return true;
                                       }
                                    }

                                    scissorTop = panel.getY() + 28.0F;
                                    scissorBottom = panel.getY() + 28.0F + panel.getHeight() - 35.0F;
                                    float effectiveY = ce.getY();
                                    if (ce.isVisible() && button == 0 && module.isOpen() && MathUtil.isHovered(mouseX, mouseY, ce.getX(), ce.getY() - 1.0F, ce.getWidth(), ce.getHeight() - 7.0F) && effectiveY >= scissorTop && effectiveY + ce.getHeight() <= scissorBottom) {
                                       Iterator var18 = this.panels.iterator();

                                       while(true) {
                                          Panel otherPanel;
                                          do {
                                             if (!var18.hasNext()) {
                                                picker.setColorPickMode(!picker.isColorPickMode());
                                                return true;
                                             }

                                             otherPanel = (Panel)var18.next();
                                          } while(this.isPanelHidden(otherPanel));

                                          Iterator var20 = otherPanel.getModules().iterator();

                                          while(var20.hasNext()) {
                                             ModuleElement otherModule = (ModuleElement)var20.next();
                                             ObjectListIterator var22 = otherModule.getElements().iterator();

                                             while(var22.hasNext()) {
                                                Element otherElem = (Element)var22.next();
                                                if (otherElem instanceof ColorElement) {
                                                   ColorElement otherCe = (ColorElement)otherElem;
                                                   if (otherCe != ce && otherCe.getColorPicker().isColorPickMode()) {
                                                      otherCe.getColorPicker().onClose();
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               panel = (Panel)var6.next();
            } while(this.isPanelHidden(panel));

            if (panel instanceof ThemeEditor themeEditor) {
               var9 = themeEditor.getColorPickers().iterator();

               while(var9.hasNext()) {
                  ce = (ColorElement)var9.next();
                  picker = ce.getColorPicker();
                  if (picker.isColorPickMode()) {
                     isColorPickerOpen = true;
                     if (picker.isEyeDropperActive()) {
                        activeEyedropperPicker = picker;
                     }
                  }
               }
            } else {
               var9 = panel.getModules().iterator();

               while(var9.hasNext()) {
                  module = (ModuleElement)var9.next();
                  var11 = module.getElements().iterator();

                  while(var11.hasNext()) {
                     elem = (Element)var11.next();
                     if (elem instanceof ColorElement ce) {
                        picker = ce.getColorPicker();
                        if (picker.isColorPickMode()) {
                           isColorPickerOpen = true;
                           if (picker.isEyeDropperActive()) {
                              activeEyedropperPicker = picker;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void closeAllPickers() {
      Iterator var1 = this.panels.iterator();

      while(true) {
         while(true) {
            Panel panel;
            do {
               if (!var1.hasNext()) {
                  return;
               }

               panel = (Panel)var1.next();
            } while(this.isPanelHidden(panel));

            Iterator var4;
            if (panel instanceof ThemeEditor themeEditor) {
               var4 = themeEditor.getColorPickers().iterator();

               while(var4.hasNext()) {
                  ColorElement ce = (ColorElement)var4.next();
                  ColorPickerElement picker = ce.getColorPicker();
                  if (picker.isColorPickMode()) {
                     picker.onClose();
                  }
               }
            } else {
               var4 = panel.getModules().iterator();

               while(var4.hasNext()) {
                  ModuleElement module = (ModuleElement)var4.next();
                  ObjectListIterator var6 = module.getElements().iterator();

                  while(var6.hasNext()) {
                     Element elem = (Element)var6.next();
                     if (elem instanceof ColorElement ce) {
                        ColorPickerElement picker = ce.getColorPicker();
                        if (picker.isColorPickMode()) {
                           picker.onClose();
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public boolean hasOpenColorPicker(Panel panel) {
      Iterator var3;
      if (panel instanceof ThemeEditor themeEditor) {
         var3 = themeEditor.getColorPickers().iterator();

         while(var3.hasNext()) {
            ColorElement ce = (ColorElement)var3.next();
            if (ce.getColorPicker().isColorPickMode()) {
               return true;
            }
         }
      } else {
         var3 = panel.getModules().iterator();

         while(var3.hasNext()) {
            ModuleElement module = (ModuleElement)var3.next();
            ObjectListIterator var5 = module.getElements().iterator();

            while(var5.hasNext()) {
               Element elem = (Element)var5.next();
               if (elem instanceof ColorElement ce) {
                  if (ce.getColorPicker().isColorPickMode()) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private boolean isPanelHidden(Panel panel) {
      return panel instanceof ThemeEditor && !(Boolean)Interface.themeeditor.get();
   }
}
