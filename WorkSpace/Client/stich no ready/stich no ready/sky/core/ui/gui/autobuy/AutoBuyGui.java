package sky.core.ui.gui.autobuy;

import com.adl.nativeprotect.Native;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ItemSetting;
import sky.core.modules.api.constructors.impl.StringSetting;
import sky.core.modules.api.elements.impl.BooleanElement;
import sky.core.modules.api.elements.impl.ItemElement;
import sky.core.modules.api.elements.impl.StringElement;
import sky.core.modules.impl.player.AutoBuy;
import sky.core.ui.gui.Panel;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.Wrapper;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.math.MathUtil;
import sky.core.utils.math.NumberUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.ScissorUtil;
import sky.core.utils.render.font.Fonts;

public class AutoBuyGui extends Panel implements Wrapper {
   private final List stringElements = new ArrayList();
   private final List itemElements = new ArrayList();
   private final List priceElements = new ArrayList();
   private final List sellPercentElements = new ArrayList();
   private final List autoSetupElements = new ArrayList();
   private final AnimationUtil listScrollAnimation;
   private float listScroll;
   private float animatedListScroll;
   private float listMaxHeight;
   private int selectedIndex;
   private String lastMode;

   public AutoBuyGui() {
      this.listScrollAnimation = new AnimationUtil(0.0F, 10.0F, Easings.SINE_OUT);
      this.selectedIndex = -1;
      this.lastMode = "";
      this.width = 420.0F;
      this.height = 300.0F;
      this.centerPanel();
      this.stringElements.add(new StringElement(new StringSetting("Search...")));
   }

   private void centerPanel() {
      this.x = ((float)mc.getMainWindow().getScaledWidth() - this.width) / 2.0F;
      this.y = ((float)mc.getMainWindow().getScaledHeight() - this.height) / 2.0F;
   }

   @Native
   private void initItemSettings() {
      this.itemElements.clear();
      this.autoSetupElements.clear();
      this.priceElements.clear();
      this.sellPercentElements.clear();
      Iterator var1 = ItemList.getItems().iterator();

      while(var1.hasNext()) {
         ItemSetting setting = (ItemSetting)var1.next();
         this.itemElements.add(new ItemElement(setting));
         this.autoSetupElements.add(new BooleanElement(new BooleanSetting("Auto Resell", setting.isSellEnabled())));
         this.priceElements.add(new StringElement(new StringSetting("0")));
         this.sellPercentElements.add(new StringElement(new StringSetting(String.valueOf(setting.getSellPercent()))));
      }

      if (!this.itemElements.isEmpty()) {
         ((ItemElement)this.itemElements.get(0)).setSelected(true);
         this.selectedIndex = 0;
      }

      this.listScroll = 0.0F;
   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      String currentMode = (String)AutoBuy.instance.mode.get();
      if (!currentMode.equals(this.lastMode)) {
         this.initItemSettings();
         this.centerPanel();
         this.lastMode = currentMode;
      }

      this.listScrollAnimation.update(this.listScroll);
      this.animatedListScroll = this.listScrollAnimation.getValue();
      RenderUtil.drawBlurredRoundedRectangle(this.x, this.y, this.width, this.height, 12.0F, ThemeEditor.getColor(ThemeSettings.MAIN), alpha);
      RenderUtil.drawOutlineRectangle(this.x, this.y, this.width, this.height, 12.0F, ThemeEditor.getColor(ThemeSettings.OUTLINE), ThemeEditor.getAlpha(ThemeSettings.OUTLINE) * alpha);
      boolean hC = MathUtil.isHovered(mouseX, mouseY, this.x + this.width - 22.0F, this.y + 8.0F, 14.0F, 14.0F);
      Fonts.sf_medium[18].drawString(stack, "q", (double)(this.x + this.width - 20.0F), (double)(this.y + 10.0F), hC ? ColorUtil.gradientInterface(0) : 1627389951);
      Fonts.sf_bold[24].drawString(stack, "AutoBuy", (double)(this.x + 15.0F), (double)(this.y + 15.0F), -1);
      if (currentMode.equalsIgnoreCase("Холиворлд")) {
         Fonts.sf_medium[14].drawString(stack, "HOLYWORLD", (double)(this.x + 70.0F), (double)(this.y + 19.0F), -13395457);
      } else {
         float sw = Fonts.sf_medium[14].getWidth("SPOOKY");
         Fonts.sf_medium[14].drawString(stack, "SPOOKY", (double)(this.x + 70.0F), (double)(this.y + 19.0F), -45747);
         Fonts.sf_medium[14].drawString(stack, "TIME", (double)(this.x + 70.0F + sw), (double)(this.y + 19.0F), -1);
      }

      StringElement search = (StringElement)this.stringElements.get(0);
      search.setX(this.x + 10.0F);
      search.setY(this.y + 45.0F);
      search.setWidth(this.width / 2.2F);
      search.render(stack, mouseX, mouseY, alpha);
      float itemBaseY = this.y + 75.0F;
      float vH = this.height - 95.0F;
      float iS = 20.0F;
      List filtered = this.getFilteredItems();
      this.listMaxHeight = (float)filtered.size() * iS;
      this.listScroll = MathHelper.clamp(this.listScroll, Math.min(0.0F, -this.listMaxHeight + vH), 0.0F);
      ScissorUtil.start((double)(this.x + 10.0F), (double)itemBaseY, (double)(this.width / 2.2F + 10.0F), (double)vH);

      for(int i = 0; i < filtered.size(); ++i) {
         ItemElement elem = (ItemElement)filtered.get(i);
         elem.setX(this.x + 15.0F);
         elem.setY(itemBaseY + (float)i * iS + this.animatedListScroll);
         elem.setWidth(this.width / 2.2F - 10.0F);
         elem.render(stack, mouseX, mouseY, alpha);
      }

      ScissorUtil.end();
      float rX = this.x + this.width / 2.2F + 25.0F;
      float rW = this.width - this.width / 2.2F - 40.0F;
      if (this.selectedIndex != -1 && this.selectedIndex < this.itemElements.size()) {
         ItemSetting sel = ((ItemElement)this.itemElements.get(this.selectedIndex)).getSetting();
         this.renderScaledItem(stack, sel.getItemStack(), rX + rW / 2.0F - 20.0F, this.y + 55.0F, 2.5F);
         String n = sel.getName();
         if (n.length() > 16) {
            n = n.substring(0, 14) + "..";
         }

         Fonts.sf_bold[18].drawString(stack, n, (double)(rX + (rW - Fonts.sf_bold[18].getWidth(n)) / 2.0F), (double)(this.y + 105.0F), -1);
         BooleanElement toggle = (BooleanElement)this.autoSetupElements.get(this.selectedIndex);
         toggle.setX(rX);
         toggle.setY(this.y + 125.0F);
         toggle.setWidth(rW);
         toggle.render(stack, mouseX, mouseY, alpha);
         sel.setSellEnabled((Boolean)toggle.getSetting().get());
         StringElement pElem;
         if (sel.isSellEnabled()) {
            Fonts.sf_medium[12].drawString(stack, "Наценка %:", (double)(rX + 6.0F), (double)(this.y + 152.0F), -1862270977);
            pElem = (StringElement)this.sellPercentElements.get(this.selectedIndex);
            pElem.setX(rX + 65.0F);
            pElem.setY(this.y + 143.0F);
            pElem.setWidth(rW - 65.0F);
            pElem.render(stack, mouseX, mouseY, alpha);
            if (pElem.isFocused) {
               try {
                  sel.setSellPercent(Integer.parseInt(pElem.getInputText().replaceAll("[^0-9]", "")));
               } catch (Exception var19) {
               }
            } else {
               pElem.setInputText(String.valueOf(sel.getSellPercent()));
            }
         }

         Fonts.sf_medium[12].drawString(stack, "Цена за 1:", (double)(rX + 6.0F), (double)(this.y + 177.0F), -1862270977);
         pElem = (StringElement)this.priceElements.get(this.selectedIndex);
         pElem.setX(rX + 65.0F);
         pElem.setY(this.y + 168.0F);
         pElem.setWidth(rW - 65.0F);
         pElem.render(stack, mouseX, mouseY, alpha);
         if (!pElem.isFocused) {
            pElem.setInputText(NumberUtil.formatThousands(String.valueOf(sel.getMaxPrice())));
         } else {
            sel.setMaxPriceFromString(pElem.getInputText());
         }

         float sY = this.y + 205.0F;
         RenderUtil.drawOutlineRectangle(rX, sY, rW, 45.0F, 6.0F, 285212671, 1.0F);
         Fonts.sf_bold[11].drawString(stack, "Stats", (double)(rX + 6.0F), (double)(sY + 6.0F), ColorUtil.gradientInterface(0));
         Fonts.sf_regular[11].drawString(stack, "Active: " + ItemList.getItems().stream().filter(Setting::get).count(), (double)(rX + 6.0F), (double)(sY + 18.0F), -1778384897);
         Fonts.sf_regular[11].drawString(stack, "Total: " + ItemList.getItems().size(), (double)(rX + 6.0F), (double)(sY + 28.0F), -1778384897);
      }

      float bW = rW / 2.0F - 3.0F;
      float bH = 18.0F;
      float bY = this.y + this.height - 30.0F;
      int acc = ColorUtil.gradientInterface(0);
      if (this.drawBtn(stack, "ENABLE", rX, bY, bW, bH, mouseX, mouseY, acc)) {
         ItemList.getItems().forEach((ix) -> {
            ix.set(true);
         });
      }

      if (this.drawBtn(stack, "DISABLE", rX + bW + 6.0F, bY, bW, bH, mouseX, mouseY, acc)) {
         ItemList.getItems().forEach((ix) -> {
            ix.set(false);
         });
      }

   }

   private boolean drawBtn(MatrixStack s, String t, float x, float y, float w, float h, float mx, float my, int c) {
      boolean hov = MathUtil.isHovered(mx, my, x, y, w, h);
      Fonts.sf_medium[11].drawString(s, t, (double)(x + (w - Fonts.sf_medium[11].getWidth(t)) / 2.0F), (double)(y + 4.0F), hov ? -1 : -1862270977);
      return hov && GLFW.glfwGetMouseButton(mc.getMainWindow().getHandle(), 0) == 1;
   }

   private void renderScaledItem(MatrixStack stack, ItemStack itemStack, float x, float y, float scale) {
      RenderSystem.pushMatrix();
      RenderSystem.translatef(x, y, 0.0F);
      RenderSystem.scalef(scale, scale, 1.0F);
      mc.getItemRenderer().renderItemAndEffectIntoGUI(itemStack, 0, 0);
      RenderSystem.popMatrix();
   }

   @Native
   public void mouseClicked(float mouseX, float mouseY, int button) {
      float rX = this.x + this.width / 2.2F + 25.0F;
      float bW = (this.width - this.width / 2.2F - 40.0F) / 2.0F - 3.0F;
      float bY = this.y + this.height - 30.0F;
      this.stringElements.forEach((e) -> {
         e.mouseClicked(mouseX, mouseY, button);
      });
      if (MathUtil.isHovered(mouseX, mouseY, this.x, this.y + 75.0F, this.width / 2.2F + 10.0F, this.height - 95.0F)) {
         Iterator var7 = this.getFilteredItems().iterator();

         while(var7.hasNext()) {
            ItemElement elem = (ItemElement)var7.next();
            if (MathUtil.isHovered(mouseX, mouseY, elem.getX(), elem.getY(), elem.getWidth(), 18.0F)) {
               elem.mouseClicked(mouseX, mouseY, button);
               if (button == 0) {
                  this.itemElements.forEach((e) -> {
                     e.setSelected(false);
                  });
                  elem.setSelected(true);
                  this.selectedIndex = this.itemElements.indexOf(elem);
               }

               return;
            }
         }
      }

      if (this.selectedIndex != -1) {
         ((BooleanElement)this.autoSetupElements.get(this.selectedIndex)).mouseClicked(mouseX, mouseY, button);
         ((StringElement)this.priceElements.get(this.selectedIndex)).mouseClicked(mouseX, mouseY, button);
         ((StringElement)this.sellPercentElements.get(this.selectedIndex)).mouseClicked(mouseX, mouseY, button);
      }

   }

   public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
      if (MathUtil.isHovered((float)mouseX, (float)mouseY, this.x, this.y + 75.0F, this.width / 2.2F, this.height - 95.0F)) {
         this.listScroll += (float)(delta * 20.0);
         return true;
      } else {
         return false;
      }
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
      this.stringElements.forEach((e) -> {
         e.keyPressed(keyCode, scanCode, modifiers);
      });
      if (this.selectedIndex != -1) {
         ((StringElement)this.priceElements.get(this.selectedIndex)).keyPressed(keyCode, scanCode, modifiers);
         ((StringElement)this.sellPercentElements.get(this.selectedIndex)).keyPressed(keyCode, scanCode, modifiers);
      }

   }

   public void charTyped(char codePoint, int modifiers) {
      this.stringElements.forEach((e) -> {
         e.charTyped(codePoint, modifiers);
      });
      if (this.selectedIndex != -1) {
         if (((StringElement)this.priceElements.get(this.selectedIndex)).isFocused && Character.isDigit(codePoint)) {
            ((StringElement)this.priceElements.get(this.selectedIndex)).charTyped(codePoint, modifiers);
         }

         if (((StringElement)this.sellPercentElements.get(this.selectedIndex)).isFocused && Character.isDigit(codePoint)) {
            ((StringElement)this.sellPercentElements.get(this.selectedIndex)).charTyped(codePoint, modifiers);
         }
      }

   }

   private List getFilteredItems() {
      String f = ((StringElement)this.stringElements.get(0)).getInputText().trim().toLowerCase();
      List res = new ArrayList();
      Iterator var3 = this.itemElements.iterator();

      while(true) {
         ItemElement e;
         while(true) {
            if (!var3.hasNext()) {
               return res;
            }

            e = (ItemElement)var3.next();
            String n = e.getSetting().getName().toLowerCase();
            if (f.equals("!вкл")) {
               if ((Boolean)e.getSetting().get()) {
                  break;
               }
            } else if (f.equals("!выкл")) {
               if (!(Boolean)e.getSetting().get()) {
                  break;
               }
            } else if (f.isEmpty() || n.contains(f)) {
               break;
            }
         }

         res.add(e);
      }
   }
}
