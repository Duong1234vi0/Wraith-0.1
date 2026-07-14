package sky.core.ui.altmanager;

import com.adl.nativeprotect.Native;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Random;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class AddWindow extends Screen {
   private static final String[] RANDOM_NAMES = new String[]{"leoaer", "rixando", "lucarde", "hengiro", "bevunho", "thionme", "andmurgo", "guivuson", "reilherpe", "ferano", "Alemurer", "cloonca", "Stepanh", "vanosik", "madboss", "wh1tolox", "roflPen", "Gentelman"};
   private final NewAltManager parent;
   private String inputText = "";
   private boolean fieldFocused = false;
   private boolean cursorVisible = true;
   private long lastCursorBlink = System.currentTimeMillis();
   private float createHover = 0.0F;
   private long createHoverTime = -1L;
   private float genHover = 0.0F;
   private long genHoverTime = -1L;

   public AddWindow(NewAltManager parent) {
      super(new StringTextComponent(""));
      this.parent = parent;
   }

   @Native
   public void tick() {
      super.tick();
   }

   @Native
   protected void init() {
      super.init();
      this.fieldFocused = true;
   }

   private void doCreate() {
      String text = this.inputText.trim();
      if (text.isEmpty()) {
         String var10000 = RANDOM_NAMES[(new Random()).nextInt(RANDOM_NAMES.length)];
         text = var10000 + (new Random()).nextInt(1500);
      }

      this.parent.addAccount(text);
      this.minecraft.displayGuiScreen(this.parent);
   }

   private void doGenerate() {
      String var10001 = RANDOM_NAMES[(new Random()).nextInt(RANDOM_NAMES.length)];
      this.inputText = var10001 + (new Random()).nextInt(1500);
   }

   @Native
   public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
      GlStateManager.disableDepthTest();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/mainmenu/black_background.png"), 0.0F, 0.0F, (float)this.width, (float)this.height, ColorUtil.applyOpacity(-1, 80));
      float mw = 200.0F;
      float mh = 95.0F;
      float mx = ((float)this.width - mw) / 2.0F;
      float my = ((float)this.height - mh) / 2.0F;
      float pad = 12.0F;
      float gap = 8.0F;
      float radius = 7.0F;
      RenderUtil.drawRoundedRectangle(mx, my, mw, mh, radius, ColorUtil.getColor(21, 20, 24, 255));
      float cy = my + pad;
      int gray85 = ColorUtil.getColor(103, 106, 114, 216);
      int white = ColorUtil.getColor(255, 255, 255, 255);
      float titleH = Fonts.sf_regular[18].getHeight();
      Fonts.sf_regular[18].drawString(ms, "Добавление аккаунта", (double)((float)Math.round(mx + pad)), (double)((float)Math.round(cy)), gray85);
      cy += titleH + gap;
      float fieldH = 21.0F;
      float fieldW = mw - pad * 2.0F;
      RenderUtil.drawBlurredRoundedRectangle(mx + pad, cy, fieldW, fieldH, 5.0F, ColorUtil.getColor(103, 106, 114, 255), 0.1F);
      RenderUtil.drawOutlineRectangle(mx + pad, cy, fieldW, fieldH, 5.0F, ColorUtil.getColor(103, 106, 114, 255), 38.25F);
      long now = System.currentTimeMillis();
      if (now - this.lastCursorBlink > 500L) {
         this.cursorVisible = !this.cursorVisible;
         this.lastCursorBlink = now;
      }

      String displayText = this.inputText.isEmpty() ? "" : this.inputText;
      float textY = cy + (fieldH - Fonts.sf_regular[18].getHeight()) / 2.0F;
      float btnW;
      if (this.inputText.isEmpty() && !this.fieldFocused) {
         Fonts.sf_regular[18].drawString(ms, "Введите никнейм", (double)((float)Math.round(mx + pad + 8.0F)), (double)((float)Math.round(textY)), gray85);
      } else {
         Fonts.sf_regular[18].drawString(ms, displayText, (double)((float)Math.round(mx + pad + 8.0F)), (double)((float)Math.round(textY)), white);
         if (this.cursorVisible) {
            btnW = mx + pad + 8.0F + Fonts.sf_regular[18].getWidth(displayText) + 1.0F;
            fill(ms, (int)btnW, (int)textY, (int)btnW + 1, (int)(textY + Fonts.sf_regular[18].getHeight()), -1);
         }
      }

      cy += fieldH + gap;
      btnW = (fieldW - gap) / 2.0F;
      float btnH = 21.0F;
      long nowC = System.currentTimeMillis();
      if (this.createHoverTime < 0L) {
         this.createHoverTime = nowC;
      }

      float dC = Math.min((float)(nowC - this.createHoverTime) / 1000.0F, 0.05F);
      this.createHoverTime = nowC;
      boolean onCreate = (float)mouseX >= mx + pad && (float)mouseX <= mx + pad + btnW && (float)mouseY >= cy && (float)mouseY <= cy + btnH;
      this.createHover = onCreate ? Math.min(this.createHover + dC * 10.0F, 1.0F) : Math.max(this.createHover - dC * 10.0F, 0.0F);
      RenderUtil.drawBlurredRoundedRectangle(mx + pad, cy, btnW, btnH, 5.0F, ColorUtil.getColor(154, 192, 255, 255), 0.7F + 0.15F * this.createHover);
      RenderUtil.drawOutlineRectangle(mx + pad, cy, btnW, btnH, 5.0F, ColorUtil.getColor(103, 106, 114, 255), 38.25F);
      float createTxtW = Fonts.sf_regular[18].getWidth("Создать");
      Fonts.sf_regular[18].drawString(ms, "Создать", (double)((float)Math.round(mx + pad + (btnW - createTxtW) / 2.0F)), (double)((float)Math.round(cy + (btnH - Fonts.sf_regular[18].getHeight()) / 2.0F)), ColorUtil.getColor(16, 17, 18, 255));
      float genX = mx + pad + btnW + gap;
      long nowG = System.currentTimeMillis();
      if (this.genHoverTime < 0L) {
         this.genHoverTime = nowG;
      }

      float dG = Math.min((float)(nowG - this.genHoverTime) / 1000.0F, 0.05F);
      this.genHoverTime = nowG;
      boolean onGen = (float)mouseX >= genX && (float)mouseX <= genX + btnW && (float)mouseY >= cy && (float)mouseY <= cy + btnH;
      this.genHover = onGen ? Math.min(this.genHover + dG * 10.0F, 1.0F) : Math.max(this.genHover - dG * 10.0F, 0.0F);
      int genBgC = (int)(10.3F + 39.7F * this.genHover);
      RenderUtil.drawBlurredRoundedRectangle(genX, cy, btnW, btnH, 5.0F, ColorUtil.getColor(103, 106, 114, 255), 0.1F + 0.05F * this.genHover);
      RenderUtil.drawOutlineRectangle(genX, cy, btnW, btnH, 5.0F, ColorUtil.getColor(103, 106, 114, 255), 38.25F);
      float genTxtW = Fonts.sf_regular[18].getWidth("Сгенерировать");
      Fonts.sf_regular[18].drawString(ms, "Сгенерировать", (double)((float)Math.round(genX + (btnW - genTxtW) / 2.0F)), (double)((float)Math.round(cy + (btnH - Fonts.sf_regular[18].getHeight()) / 2.0F)), gray85);
      super.render(ms, mouseX, mouseY, partialTicks);
   }

   @Native
   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (button != 0) {
         return super.mouseClicked(mouseX, mouseY, button);
      } else {
         float mw = 200.0F;
         float mh = 95.0F;
         float mx = ((float)this.width - mw) / 2.0F;
         float my = ((float)this.height - mh) / 2.0F;
         float pad = 12.0F;
         float gap = 8.0F;
         float fieldH = 21.0F;
         float btnH = 21.0F;
         float titleH = Fonts.sf_regular[18].getHeight();
         float fieldW = mw - pad * 2.0F;
         float btnW = (fieldW - gap) / 2.0F;
         float fieldY = my + pad + titleH + gap;
         float btnY = fieldY + fieldH + gap;
         if (mouseX >= (double)(mx + pad) && mouseX <= (double)(mx + pad + btnW) && mouseY >= (double)btnY && mouseY <= (double)(btnY + btnH)) {
            this.doCreate();
            return true;
         } else {
            float genX = mx + pad + btnW + gap;
            if (mouseX >= (double)genX && mouseX <= (double)(genX + btnW) && mouseY >= (double)btnY && mouseY <= (double)(btnY + btnH)) {
               this.doGenerate();
               return true;
            } else if (mouseX >= (double)(mx + pad) && mouseX <= (double)(mx + mw - pad) && mouseY >= (double)fieldY && mouseY <= (double)(fieldY + fieldH)) {
               this.fieldFocused = true;
               return true;
            } else if (!(mouseX < (double)mx) && !(mouseX > (double)(mx + mw)) && !(mouseY < (double)my) && !(mouseY > (double)(my + mh))) {
               this.fieldFocused = false;
               return super.mouseClicked(mouseX, mouseY, button);
            } else {
               this.minecraft.displayGuiScreen(this.parent);
               return true;
            }
         }
      }
   }

   @Native
   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      if (keyCode == 256) {
         this.minecraft.displayGuiScreen(this.parent);
         return true;
      } else if (keyCode != 257 && keyCode != 335) {
         if (keyCode == 259 && !this.inputText.isEmpty()) {
            this.inputText = this.inputText.substring(0, this.inputText.length() - 1);
            return true;
         } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
         }
      } else {
         this.doCreate();
         return true;
      }
   }

   @Native
   public boolean charTyped(char c, int modifiers) {
      if (this.inputText.length() < 32) {
         this.inputText = this.inputText + c;
         return true;
      } else {
         return false;
      }
   }
}
