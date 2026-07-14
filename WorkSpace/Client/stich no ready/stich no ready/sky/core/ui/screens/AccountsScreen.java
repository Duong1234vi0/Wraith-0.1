package sky.core.ui.screens;

import com.adl.nativeprotect.Native;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.glfw.GLFW;
import sky.core.utils.Wrapper;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.net.MicrosoftLogin;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.ScissorUtil;
import sky.core.utils.render.font.Fonts;

public class AccountsScreen extends Screen {
   private boolean showAddDialog = false;
   private boolean showClearDialog = false;
   private float closeIconX;
   private float closeIconY;
   private final float closeIconWidth = 8.0F;
   private final float closeIconHeight = 8.0F;
   private String inputText = "";
   private String addDialogInputText = "";
   private boolean inputFocused = false;
   private boolean addDialogInputFocused = false;
   private boolean isTextSelected = false;
   private boolean isAddDialogTextSelected = false;
   private long cursorBlinkStart = 0L;
   private final Map accountNames = new LinkedHashMap();
   private final Map skinCache = new HashMap();
   private final Map xaveIconClicked = new HashMap();
   private String selectedAccount = null;
   private static final ResourceLocation DEFAULT_SKIN = new ResourceLocation("minecraft:textures/entity/steve.png");
   private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
   private static final int MAX_INPUT_LENGTH = 16;
   private float scroll = 0.0F;
   private float animatedScroll = 0.0F;
   private float maxHeight = 0.0F;
   private final AnimationUtil scrollAnimation;
   private static float savedScroll = 0.0F;
   private final sky.core.utils.managers.impl.AccountManager accountManager;

   public AccountsScreen() {
      super(new StringTextComponent("Аккаунты"));
      this.scrollAnimation = new AnimationUtil(0.0F, 7.0F, Easings.QUINT_OUT);
      this.accountManager = new sky.core.utils.managers.impl.AccountManager();
      this.loadFromConfig();
      this.scroll = savedScroll;
      this.animatedScroll = savedScroll;
      this.scrollAnimation.setValue(savedScroll);
   }

   @Native
   private void scrollToBottom() {
      int accountCount = this.accountNames.size();
      int rows = (int)Math.ceil((double)accountCount / 2.0);
      float rectHeight = 64.0F;
      float rowPadding = 10.0F;
      float localMaxHeight = (float)rows * (rectHeight + rowPadding);
      float contentHeight = 222.0F;
      float bottom = Math.min(0.0F, -localMaxHeight + contentHeight);
      this.scroll = MathHelper.clamp(bottom, -localMaxHeight + contentHeight, 0.0F);
      savedScroll = this.scroll;
   }

   private void loadFromConfig() {
      if (this.accountManager.getData() == null) {
         this.accountManager.init();
      }

      this.accountNames.clear();
      this.accountNames.putAll(this.accountManager.getAccountNames());
      this.xaveIconClicked.clear();
      this.xaveIconClicked.putAll(this.accountManager.getFavoriteAccounts());
      this.selectedAccount = this.accountManager.getSelectedAccount();
      this.updateAccountOrder();
   }

   private void saveToConfig() {
      Iterator var1 = this.accountNames.entrySet().iterator();

      Map.Entry entry;
      while(var1.hasNext()) {
         entry = (Map.Entry)var1.next();
         if (!this.accountManager.getAccountNames().containsKey(entry.getKey())) {
            this.accountManager.addAccount((String)entry.getKey());
         }
      }

      var1 = this.xaveIconClicked.entrySet().iterator();

      while(var1.hasNext()) {
         entry = (Map.Entry)var1.next();
         this.accountManager.setFavorite((String)entry.getKey(), (Boolean)entry.getValue());
      }

      if (this.selectedAccount != null) {
         this.accountManager.selectAccount(this.selectedAccount);
      } else {
         this.accountManager.selectAccount((String)null);
      }

   }

   @Native
   private void updateAccountOrder() {
      LinkedHashMap sortedAccounts = new LinkedHashMap();
      this.accountNames.entrySet().stream().filter((entry) -> {
         return (Boolean)this.xaveIconClicked.getOrDefault(entry.getKey(), false);
      }).forEach((entry) -> {
         sortedAccounts.put((String)entry.getKey(), (Long)entry.getValue());
      });
      this.accountNames.entrySet().stream().filter((entry) -> {
         return !(Boolean)this.xaveIconClicked.getOrDefault(entry.getKey(), false);
      }).forEach((entry) -> {
         sortedAccounts.put((String)entry.getKey(), (Long)entry.getValue());
      });
      this.accountNames.clear();
      this.accountNames.putAll(sortedAccounts);
      this.saveToConfig();
   }

   @Native
   public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
      int baseScale = (int)Wrapper.mc.getMainWindow().getGuiScaleFactor();
      Wrapper.mc.gameRenderer.setupOverlayRendering(2.0F);
      float factor = (float)baseScale / 2.0F;
      int scaledMouseX = (int)((float)mouseX * factor);
      int scaledMouseY = (int)((float)mouseY * factor);
      this.renderBackgrounds(scaledMouseX, scaledMouseY);
      this.renderSearchBar(matrixStack, scaledMouseX, scaledMouseY);
      this.renderMainPanel(matrixStack, scaledMouseX, scaledMouseY);
      this.renderButtons(matrixStack, scaledMouseX, scaledMouseY);
      this.renderAddDialog(matrixStack, scaledMouseX, scaledMouseY);
      this.renderClearDialog(matrixStack, scaledMouseX, scaledMouseY);
      this.renderMicrosoft(matrixStack, scaledMouseX, scaledMouseY);
      super.render(matrixStack, scaledMouseX, scaledMouseY, partialTicks);
      Wrapper.mc.gameRenderer.setupOverlayRendering();
   }

   @Native
   private void renderBackgrounds(int mouseX, int mouseY) {
      int windowWidth = Wrapper.mc.getMainWindow().getScaledWidth();
      int windowHeight = Wrapper.mc.getMainWindow().getScaledHeight();
      int renderWidth = (int)((float)windowWidth * 1.05F);
      int renderHeight = (int)((float)windowHeight * 1.05F);
      float normMouseX = (float)mouseX / (float)windowWidth * 2.0F - 1.0F;
      float normMouseY = (float)mouseY / (float)windowHeight * 2.0F - 1.0F;
      float maxOffsetX = (float)(renderWidth - windowWidth) / 2.0F;
      float maxOffsetY = (float)(renderHeight - windowHeight) / 2.0F;
      float offsetX = MathHelper.clamp(normMouseX * 3.0F, -maxOffsetX, maxOffsetX);
      float offsetY = MathHelper.clamp(normMouseY * 3.0F, -maxOffsetY, maxOffsetY);
      RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/mainmenu/background.png"), offsetX - (float)(renderWidth - windowWidth) / 2.0F, offsetY - (float)(renderHeight - windowHeight) / 2.0F, (float)renderWidth, (float)renderHeight, -1);
      float parallax = 2.0F;
      float offsetXBlack = MathHelper.clamp(normMouseX * parallax, -maxOffsetX, maxOffsetX);
      float offsetYBlack = MathHelper.clamp(normMouseY * parallax, -maxOffsetY, maxOffsetY);
      RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/mainmenu/black_background.png"), offsetXBlack - (float)(renderWidth - windowWidth) / 2.0F, offsetYBlack - (float)(renderHeight - windowHeight) / 2.0F, (float)renderWidth, (float)renderHeight, ColorUtil.applyOpacity(-1, 200));
      float offsetXBlue = MathHelper.clamp(normMouseX * parallax, -maxOffsetX, maxOffsetX);
      float offsetYBlue = MathHelper.clamp(normMouseY * parallax, -maxOffsetY, maxOffsetY);
      RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/mainmenu/blue_background.png"), offsetXBlue - (float)(renderWidth - windowWidth) / 2.0F, offsetYBlue - (float)(renderHeight - windowHeight) / 2.0F, (float)renderWidth, (float)renderHeight, -1);
   }

   @Native
   private void renderSearchBar(MatrixStack matrixStack, int mouseX, int mouseY) {
      float centerSearcherX = (float)(Wrapper.mc.getMainWindow().getScaledWidth() - 172) / 2.0F;
      float searcherY = 4.0F;
      float searcherWidth = 172.0F;
      float searcherHeight = 24.0F;
      boolean searcherHovered = (float)mouseX >= centerSearcherX && (float)mouseX <= centerSearcherX + searcherWidth && (float)mouseY >= searcherY && (float)mouseY <= searcherY + searcherHeight;
      RenderUtil.drawRoundedRectangle(centerSearcherX, searcherY, searcherWidth, searcherHeight, 4.0F, searcherHovered ? ColorUtil.getColor(33, 33, 33) : ColorUtil.getColor(22, 22, 22));
      String display = this.inputText.isEmpty() && !this.inputFocused ? "Search" : this.inputText;
      int textColor = this.inputText.isEmpty() && !this.inputFocused ? ColorUtil.getColor(120, 120, 120, 255) : -1;
      Fonts.sf_regular[17].drawString(matrixStack, display, (double)(centerSearcherX + 5.0F), (double)(searcherY + (searcherHeight - Fonts.sf_regular[17].getHeight()) / 2.0F), textColor);
      if (this.inputFocused) {
         long time = System.currentTimeMillis();
         if (this.cursorBlinkStart == 0L) {
            this.cursorBlinkStart = time;
         }

         boolean showCursor = (time - this.cursorBlinkStart) % 1000L < 500L;
         if (showCursor) {
            float textWidth = Fonts.sf_regular[17].getWidth(this.inputText);
            RenderUtil.drawMinecraftRectangle(matrixStack, centerSearcherX + 5.0F + textWidth, searcherY + searcherHeight - 8.0F, 5.0F, 0.5F, -1);
         }
      } else {
         this.isTextSelected = false;
      }

   }

   @Native
   private void renderMainPanel(MatrixStack matrixStack, int mouseX, int mouseY) {
      float windowWidth = (float)Wrapper.mc.getMainWindow().getScaledWidth();
      float windowHeight = (float)Wrapper.mc.getMainWindow().getScaledHeight();
      float centerX = (windowWidth - 380.0F) / 2.0F;
      float centerY = (windowHeight - 232.0F) / 2.0F;
      RenderUtil.drawRoundedRectangleGradient(centerX, centerY + 35.0F, 380.0F, 232.0F, 8.0F, ColorUtil.getColor(9, 9, 9), ColorUtil.getColor(8, 8, 8), ColorUtil.getColor(31, 31, 31), ColorUtil.getColor(12, 12, 12), 1.0F);
      Fonts.sf_semibold[28].drawString(matrixStack, "Аккаунты", (double)(centerX + 190.0F - Fonts.sf_semibold[28].getWidth("Аккаунты") / 2.0F), (double)centerY, -1);
      String message = this.selectedAccount == null ? "Нажмите на любой, для входа в аккаунт" : "Нажмите на любой, для входа в аккаунт (Текущий: " + this.selectedAccount + ")";
      Fonts.sf_regular[18].drawString(matrixStack, message, (double)(centerX + 190.0F - Fonts.sf_regular[18].getWidth(message) / 2.0F), (double)(centerY + Fonts.sf_semibold[28].getHeight() + 9.0F), ColorUtil.getColor(255, 255, 255, 120));
      this.scrollAnimation.setSpeed(1.2F);
      this.scrollAnimation.update(this.scroll);
      this.animatedScroll = this.scrollAnimation.getValue();
      List filteredAccounts = new ArrayList();
      Iterator var10 = this.accountNames.entrySet().iterator();

      while(true) {
         Map.Entry entry;
         do {
            if (!var10.hasNext()) {
               int accountCount = filteredAccounts.size();
               int rows = (int)Math.ceil((double)accountCount / 2.0);
               float rectHeight = 64.0F;
               float rowPadding = 10.0F;
               this.maxHeight = (float)rows * (rectHeight + rowPadding);
               float contentHeight = 222.0F;
               if (this.maxHeight > contentHeight) {
                  this.scroll = MathHelper.clamp(this.scroll, -this.maxHeight + contentHeight, 0.0F);
                  this.animatedScroll = MathHelper.clamp(this.animatedScroll, -this.maxHeight + contentHeight, 0.0F);
               } else {
                  this.scroll = this.animatedScroll = 0.0F;
               }

               ScissorUtil.start((double)centerX, (double)(centerY + 35.0F), 380.0, 232.0);
               float startX = centerX + 10.0F;
               float startY = centerY + 45.0F;
               int row = 0;
               int col = 0;
               Iterator var19 = filteredAccounts.iterator();

               while(var19.hasNext()) {
                  Map.Entry entry = (Map.Entry)var19.next();
                  String name = (String)entry.getKey();
                  long timestamp = (Long)entry.getValue();
                  float rectWidth = 170.0F;
                  float rectPadding = 20.0F;
                  float x = startX + (float)col * (rectWidth + rectPadding);
                  float y = startY + (float)row * (64.0F + rectPadding - 10.0F) + this.animatedScroll;
                  String date = this.dateFormat.format(new Date(timestamp));
                  RenderUtil.drawRoundedRectangle(x, y, rectWidth, 64.0F, 10.0F, ColorUtil.getColor(22, 22, 22));
                  RenderUtil.drawRoundedRectangle(x + 124.0F, y, 46.0F, 18.0F, new Vector4f(0.0F, 7.0F, 7.0F, 0.0F), ColorUtil.getColor(37, 138, 238));
                  float xmarkIconX = x + 132.5F;
                  float xmarkIconY = y + 5.5F;
                  float xmarkIconWidth = 7.0F;
                  float xmarkIconHeight = 7.0F;
                  boolean xmarkHovered = (double)mouseX >= (double)xmarkIconX && (double)mouseX <= (double)(xmarkIconX + xmarkIconWidth) && (double)mouseY >= (double)xmarkIconY && (double)mouseY <= (double)(xmarkIconY + xmarkIconHeight);
                  int xmarkColor = xmarkHovered ? ColorUtil.getColor(134, 201, 244) : -1;
                  RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/alts/close.png"), xmarkIconX, xmarkIconY, xmarkIconWidth, xmarkIconHeight, xmarkColor);
                  float copyIconX = x + 144.0F;
                  float copyIconY = y + 5.0F;
                  float copyIconWidth = 8.0F;
                  float copyIconHeight = 8.0F;
                  boolean copyHovered = (double)mouseX >= (double)copyIconX && (double)mouseX <= (double)(copyIconX + copyIconWidth) && (double)mouseY >= (double)copyIconY && (double)mouseY <= (double)(copyIconY + copyIconHeight);
                  int copyColor = copyHovered ? ColorUtil.getColor(134, 201, 244) : -1;
                  RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/alts/copy.png"), copyIconX, copyIconY, copyIconWidth, copyIconHeight, copyColor);
                  float xaveIconX = x + 156.0F;
                  float xaveIconY = y + 5.0F;
                  float xaveIconWidth = 9.0F;
                  float xaveIconHeight = 9.0F;
                  boolean xaveIconHovered = (double)mouseX >= (double)xaveIconX && (double)mouseX <= (double)(xaveIconX + xaveIconWidth) && (double)mouseY >= (double)(xaveIconY - 3.0F) && (double)mouseY <= (double)(xaveIconY - 3.0F + xaveIconHeight);
                  boolean isClicked = (Boolean)this.xaveIconClicked.getOrDefault(name, false);
                  int xaveIconColor = isClicked ? ColorUtil.getColor(255, 255, 0) : (xaveIconHovered ? ColorUtil.getColor(134, 201, 244) : -1);
                  RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/alts/star.png"), xaveIconX + 1.0F, xaveIconY, 7.5F, 7.5F, xaveIconColor);
                  RenderUtil.drawRoundedHead(this.getSkinForName(name), (LivingEntity)null, x + 8.0F, y + 8.0F, 48.0F, 48.0F, 10.0F, 1.0F);
                  Fonts.sf_regular[16].drawString(matrixStack, name, (double)(x + 61.0F), (double)(y + 23.0F), ColorUtil.getColor(255, 255, 255, 200));
                  Fonts.sf_regular[14].drawString(matrixStack, "Дата создания", (double)(x + 61.0F), (double)(y + 32.0F), ColorUtil.getColor(255, 255, 255, 120));
                  Fonts.sf_regular[14].drawString(matrixStack, date, (double)(x + 61.0F), (double)(y + 40.0F), ColorUtil.getColor(255, 255, 255, 120));
                  ++col;
                  if (x + rectWidth + rectPadding > centerX + 380.0F - 10.0F) {
                     col = 0;
                     ++row;
                  }
               }

               this.maxHeight = (float)((int)Math.ceil((double)filteredAccounts.size() / 2.0) * 74);
               ScissorUtil.end();
               return;
            }

            entry = (Map.Entry)var10.next();
         } while(!this.showAddDialog && !this.inputText.isEmpty() && !((String)entry.getKey()).toLowerCase().contains(this.inputText.toLowerCase()));

         filteredAccounts.add(entry);
      }
   }

   @Native
   private void renderButtons(MatrixStack matrixStack, int mouseX, int mouseY) {
      float centerX = (float)(Wrapper.mc.getMainWindow().getScaledWidth() - 380) / 2.0F;
      float centerY = (float)(Wrapper.mc.getMainWindow().getScaledHeight() - 230) / 2.0F;
      float spacing = 13.0F;
      float buttonWidth = 84.0F;
      float buttonHeight = 26.0F;
      float totalWidth = buttonWidth * 4.0F + spacing * 3.0F;
      float baseX = centerX + (380.0F - totalWidth) / 2.0F;
      float y = centerY + 35.0F + 230.0F + 10.0F;
      String[] labels = new String[]{"Добавить", "Очистить", "Рандомный", "Закрыть"};

      for(int i = 0; i < 4; ++i) {
         float x = baseX + (float)i * (buttonWidth + spacing);
         boolean hovered = (float)mouseX >= x && (float)mouseX <= x + buttonWidth && (float)mouseY >= y && (float)mouseY <= y + buttonHeight;
         int textColor = hovered ? ColorUtil.getColor(255, 255, 255, 200) : ColorUtil.getColor(255, 255, 255, 120);
         RenderUtil.drawRoundedRectangleGradient(x, y, buttonWidth, buttonHeight, 6.0F, ColorUtil.getColor(9, 9, 9), ColorUtil.getColor(8, 8, 8), ColorUtil.getColor(31, 31, 31), ColorUtil.getColor(12, 12, 12), 1.0F);
         Fonts.sf_regular[26].drawString(matrixStack, labels[i], (double)(x + (buttonWidth - Fonts.sf_regular[26].getWidth(labels[i])) / 2.0F), (double)(y + (buttonHeight - Fonts.sf_regular[26].getHeight()) / 2.0F - 1.5F), textColor);
      }

   }

   @Native
   private void renderAddDialog(MatrixStack matrixStack, int mouseX, int mouseY) {
      if (this.showAddDialog) {
         float dialogWidth = 180.0F;
         float dialogHeight = 90.0F;
         float dialogX = ((float)Wrapper.mc.getMainWindow().getScaledWidth() - dialogWidth) / 2.0F;
         float dialogY = ((float)Wrapper.mc.getMainWindow().getScaledHeight() - dialogHeight) / 2.0F;
         RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/mainmenu/black_background.png"), 0.0F, 0.0F, (float)Wrapper.mc.getMainWindow().getScaledWidth(), (float)Wrapper.mc.getMainWindow().getScaledHeight(), ColorUtil.applyOpacity(-1, 240));
         RenderUtil.drawRoundedRectangleGradient(dialogX, dialogY + 35.0F, dialogWidth, dialogHeight, 8.0F, ColorUtil.getColor(11, 11, 11), ColorUtil.getColor(10, 10, 10), ColorUtil.getColor(31, 31, 32), ColorUtil.getColor(12, 12, 12), 1.0F);
         Fonts.sf_medium[21].drawString(matrixStack, "Напишите желаемый ник", (double)(dialogX + (dialogWidth - Fonts.sf_medium[21].getWidth("Напишите желаемый ник")) / 2.0F), (double)(dialogY + 49.5F), -1);
         float inputX = dialogX + 4.0F;
         float inputY = dialogY + 66.5F;
         float inputWidth = 172.0F;
         float inputHeight = 24.0F;
         boolean inputHovered = (float)mouseX >= inputX && (float)mouseX <= inputX + inputWidth && (float)mouseY >= inputY && (float)mouseY <= inputY + inputHeight;
         RenderUtil.drawRoundedRectangle(inputX, inputY, inputWidth, inputHeight, 4.0F, inputHovered ? ColorUtil.getColor(33, 33, 33) : ColorUtil.getColor(22, 22, 22));
         String display = this.addDialogInputText.isEmpty() && !this.addDialogInputFocused ? "Никнейм" : this.addDialogInputText;
         int textColor = this.addDialogInputText.isEmpty() && !this.addDialogInputFocused ? ColorUtil.getColor(255, 255, 255, 120) : -1;
         Fonts.sf_regular[17].drawString(matrixStack, display, (double)(inputX + 5.0F), (double)(inputY + 9.5F), textColor);
         float textWidth;
         if (this.addDialogInputFocused) {
            long time = System.currentTimeMillis();
            if (this.cursorBlinkStart == 0L) {
               this.cursorBlinkStart = time;
            }

            boolean showCursor = (time - this.cursorBlinkStart) % 1000L < 500L;
            if (showCursor) {
               textWidth = Fonts.sf_regular[17].getWidth(this.addDialogInputText);
               RenderUtil.drawMinecraftRectangle(matrixStack, inputX + 5.0F + textWidth, inputY + 15.0F, 5.0F, 0.5F, -1);
            }
         } else {
            this.isAddDialogTextSelected = false;
         }

         this.closeIconX = dialogX + dialogWidth - 8.0F - 7.0F;
         this.closeIconY = dialogY + 41.0F;
         RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/alts/close.png"), this.closeIconX, this.closeIconY, 8.0F, 8.0F, -1);
         float buttonWidth = 120.0F;
         float buttonHeight = 22.0F;
         float buttonX = dialogX + (dialogWidth - buttonWidth) / 2.0F;
         textWidth = dialogY + 97.5F;
         boolean buttonHovered = (float)mouseX >= buttonX && (float)mouseX <= buttonX + buttonWidth && (float)mouseY >= textWidth && (float)mouseY <= textWidth + buttonHeight;
         RenderUtil.drawRoundedRectangleGradient(buttonX, textWidth, buttonWidth, buttonHeight, 5.0F, ColorUtil.getColor(8, 8, 8), ColorUtil.getColor(7, 7, 7), ColorUtil.getColor(29, 29, 29), ColorUtil.getColor(10, 10, 10), 1.0F);
         String text = "Добавить";
         textColor = buttonHovered ? ColorUtil.getColor(255, 255, 255, 200) : ColorUtil.getColor(255, 255, 255, 120);
         float textWidth = Fonts.sf_medium[24].getWidth(text);
         float textHeight = Fonts.sf_medium[24].getHeight();
         Fonts.sf_medium[24].drawString(matrixStack, text, (double)(buttonX + (buttonWidth - textWidth) / 2.0F), (double)(textWidth + (buttonHeight - textHeight) / 2.0F - 2.0F), textColor);
         if (buttonHovered && GLFW.glfwGetMouseButton(Wrapper.mc.getMainWindow().getHandle(), 0) == 1 && !this.addDialogInputText.trim().isEmpty()) {
            String accountName = this.addDialogInputText.trim();
            this.accountNames.put(accountName, System.currentTimeMillis());
            this.accountManager.addAccount(accountName);
            this.updateAccountOrder();
            this.scrollToBottom();
            this.addDialogInputText = "";
            this.addDialogInputFocused = false;
            this.isAddDialogTextSelected = false;
            this.showAddDialog = false;
         }

      }
   }

   @Native
   private void renderMicrosoft(MatrixStack matrixStack, int mouseX, int mouseY) {
      float iconX = 4.0F;
      float iconY = 4.0F;
      float iconW = 32.0F;
      float iconH = 32.0F;
      boolean hovered = (float)mouseX >= iconX && (float)mouseX <= iconX + iconW && (float)mouseY >= iconY && (float)mouseY <= iconY + iconH;
      int color = hovered ? ColorUtil.getColor(134, 201, 244) : -1;
      RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/alts/microsoft.png"), iconX, iconY, iconW, iconH, color);
   }

   @Native
   private void renderClearDialog(MatrixStack matrixStack, int mouseX, int mouseY) {
      if (this.showClearDialog) {
         float dialogWidth = 180.0F;
         float dialogHeight = 60.0F;
         float dialogX = ((float)Wrapper.mc.getMainWindow().getScaledWidth() - dialogWidth) / 2.0F;
         float dialogY = ((float)Wrapper.mc.getMainWindow().getScaledHeight() - dialogHeight) / 2.0F + 20.0F;
         RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/mainmenu/black_background.png"), 0.0F, 0.0F, (float)Wrapper.mc.getMainWindow().getScaledWidth(), (float)Wrapper.mc.getMainWindow().getScaledHeight(), ColorUtil.applyOpacity(-1, 240));
         RenderUtil.drawRoundedRectangleGradient(dialogX, dialogY, dialogWidth, dialogHeight, 8.0F, ColorUtil.getColor(11, 11, 11), ColorUtil.getColor(10, 10, 10), ColorUtil.getColor(31, 31, 31), ColorUtil.getColor(12, 12, 12), 1.0F);
         String text = "Подтвердить";
         Fonts.sf_medium[22].drawString(matrixStack, text, (double)(dialogX + (dialogWidth - Fonts.sf_medium[22].getWidth(text)) / 2.0F), (double)(dialogY + 14.5F), -1);
         this.closeIconX = dialogX + dialogWidth - 8.0F - 7.0F;
         this.closeIconY = dialogY + 6.0F;
         RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/alts/close.png"), this.closeIconX, this.closeIconY, 8.0F, 8.0F, -1);
         float buttonWidth = 60.0F;
         float buttonHeight = 23.0F;
         float spacing = 20.0F;
         float buttonY = dialogY + dialogHeight - buttonHeight - 4.0F;
         float yesButtonX = dialogX + (dialogWidth - buttonWidth * 2.0F - spacing) / 2.0F;
         float noButtonX = yesButtonX + buttonWidth + spacing;
         boolean yesHovered = (float)mouseX >= yesButtonX && (float)mouseX <= yesButtonX + buttonWidth && (float)mouseY >= buttonY && (float)mouseY <= buttonY + buttonHeight;
         boolean noHovered = (float)mouseX >= noButtonX && (float)mouseX <= noButtonX + buttonWidth && (float)mouseY >= buttonY && (float)mouseY <= buttonY + buttonHeight;
         RenderUtil.drawRoundedRectangleGradient(yesButtonX, buttonY, buttonWidth, buttonHeight, 5.0F, ColorUtil.getColor(8, 8, 8), ColorUtil.getColor(7, 7, 7), ColorUtil.getColor(29, 29, 29), ColorUtil.getColor(10, 10, 10), 1.0F);
         Fonts.sf_medium[24].drawString(matrixStack, "Ок", (double)(yesButtonX + (buttonWidth - Fonts.sf_medium[24].getWidth("Ок")) / 2.0F), (double)(buttonY + (buttonHeight - Fonts.sf_medium[24].getHeight()) / 2.0F - 1.5F), yesHovered ? ColorUtil.getColor(255, 255, 255, 200) : ColorUtil.getColor(255, 255, 255, 120));
         RenderUtil.drawRoundedRectangleGradient(noButtonX, buttonY, buttonWidth, buttonHeight, 5.0F, ColorUtil.getColor(8, 8, 8), ColorUtil.getColor(7, 7, 7), ColorUtil.getColor(29, 29, 29), ColorUtil.getColor(10, 10, 10), 1.0F);
         Fonts.sf_medium[24].drawString(matrixStack, "Отмена", (double)(noButtonX + (buttonWidth - Fonts.sf_medium[24].getWidth("Отмена")) / 2.0F), (double)(buttonY + (buttonHeight - Fonts.sf_medium[24].getHeight()) / 2.0F - 1.5F), noHovered ? ColorUtil.getColor(255, 255, 255, 200) : ColorUtil.getColor(255, 255, 255, 120));
      }
   }

   private ResourceLocation getSkinForName(String name) {
      if (this.skinCache.containsKey(name)) {
         return (ResourceLocation)this.skinCache.get(name);
      } else {
         try {
            ResourceLocation location = AbstractClientPlayerEntity.getLocationSkin(name);
            AbstractClientPlayerEntity.getDownloadImageSkin(location, name);
            this.skinCache.put(name, location);
            return location;
         } catch (Exception var3) {
            this.skinCache.put(name, DEFAULT_SKIN);
            return DEFAULT_SKIN;
         }
      }
   }

   @Native
   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      int baseScale = (int)Wrapper.mc.getMainWindow().getGuiScaleFactor();
      double factor = (double)baseScale / 2.0;
      double sx = mouseX * factor;
      double sy = mouseY * factor;
      if (button != 0) {
         return super.mouseClicked(sx, sy, button);
      } else {
         double w2 = (double)Wrapper.mc.getMainWindow().getFramebufferWidth() / 2.0;
         double h2 = (double)Wrapper.mc.getMainWindow().getFramebufferHeight() / 2.0;
         double searchX = (w2 - 172.0) / 2.0;
         double searchY = 4.0;
         double searchW = 172.0;
         double searchH = 24.0;
         if (!this.showAddDialog && !this.showClearDialog) {
            if (sx >= searchX && sx <= searchX + searchW && sy >= searchY && sy <= searchY + searchH) {
               this.inputFocused = true;
               this.addDialogInputFocused = false;
               this.cursorBlinkStart = System.currentTimeMillis();
               this.isTextSelected = false;
               return true;
            }

            this.inputFocused = false;
            this.isTextSelected = false;
         }

         double centerX2;
         double centerY2;
         double dialogY;
         double inputX;
         double inputY;
         double spacing;
         if (this.showClearDialog) {
            centerX2 = 180.0;
            centerY2 = 60.0;
            dialogY = (w2 - centerX2) / 2.0;
            inputX = (h2 - centerY2) / 2.0 + 20.0;
            if (sx >= (double)this.closeIconX * factor && sx <= (double)this.closeIconX * factor + 8.0 * factor && sy >= (double)this.closeIconY * factor && sy <= (double)this.closeIconY * factor + 8.0 * factor) {
               this.showClearDialog = false;
               return true;
            } else {
               inputY = 60.0;
               double buttonHeight = 23.0;
               double spacing = 20.0;
               double buttonY = inputX + centerY2 - buttonHeight - 4.0;
               double yesButtonX = dialogY + (centerX2 - inputY * 2.0 - spacing) / 2.0;
               spacing = yesButtonX + inputY + spacing;
               if (sx >= yesButtonX && sx <= yesButtonX + inputY && sy >= buttonY && sy <= buttonY + buttonHeight) {
                  List accountList = new ArrayList(this.accountNames.keySet());
                  Iterator var46 = accountList.iterator();

                  while(var46.hasNext()) {
                     String accountName = (String)var46.next();
                     this.accountManager.removeAccount(accountName);
                  }

                  this.accountNames.clear();
                  this.skinCache.clear();
                  this.xaveIconClicked.clear();
                  this.selectedAccount = null;
                  this.showClearDialog = false;
                  return true;
               } else if (sx >= spacing && sx <= spacing + inputY && sy >= buttonY && sy <= buttonY + buttonHeight) {
                  this.showClearDialog = false;
                  return true;
               } else {
                  return true;
               }
            }
         } else if (this.showAddDialog) {
            if (sx >= (double)this.closeIconX * factor && sx <= (double)this.closeIconX * factor + 8.0 * factor && sy >= (double)this.closeIconY * factor && sy <= (double)this.closeIconY * factor + 8.0 * factor) {
               this.showAddDialog = false;
               this.addDialogInputText = "";
               this.addDialogInputFocused = false;
               this.isAddDialogTextSelected = false;
               return true;
            } else {
               centerX2 = 180.0;
               centerY2 = (w2 - centerX2) / 2.0;
               dialogY = (h2 - 90.0) / 2.0;
               inputX = centerY2 + 4.0;
               inputY = dialogY + 66.5;
               if (sx >= inputX && sx <= inputX + 172.0 && sy >= inputY && sy <= inputY + 24.0) {
                  this.addDialogInputFocused = true;
                  this.inputFocused = false;
                  this.cursorBlinkStart = System.currentTimeMillis();
                  this.isAddDialogTextSelected = false;
                  return true;
               } else {
                  this.addDialogInputFocused = false;
                  this.isAddDialogTextSelected = false;
                  return true;
               }
            }
         } else {
            centerX2 = (w2 - 380.0) / 2.0;
            centerY2 = (h2 - 230.0) / 2.0;
            dialogY = centerY2 + 35.0;
            inputX = 380.0;
            inputY = 232.0;
            boolean isInScissorArea = sx >= centerX2 && sx <= centerX2 + inputX && sy >= dialogY && sy <= dialogY + inputY;
            double startX2 = centerX2 + 10.0;
            double startY2 = centerY2 + 45.0;
            int row = 0;
            int col = 0;
            Iterator iterator = this.accountNames.entrySet().iterator();

            double buttonWidth;
            double buttonHeight;
            double x;
            double rectHeight;
            double yButtons;
            double msIconX;
            double msIconY;
            double msIconW;
            double msIconH;
            double x2;
            double x3;
            double x4;
            while(iterator.hasNext()) {
               Map.Entry entry = (Map.Entry)iterator.next();
               String name = (String)entry.getKey();
               buttonWidth = 170.0;
               buttonHeight = 20.0;
               x = startX2 + (double)col * (buttonWidth + buttonHeight);
               rectHeight = 64.0;
               yButtons = startY2 + (double)row * (rectHeight + (buttonHeight - 10.0)) + (double)this.animatedScroll;
               if (isInScissorArea) {
                  msIconX = x + 132.5;
                  msIconY = yButtons + 5.5;
                  msIconW = 7.0;
                  msIconH = 7.0;
                  if (sx >= msIconX && sx <= msIconX + msIconW && sy >= msIconY && sy <= msIconY + msIconH) {
                     iterator.remove();
                     this.skinCache.remove(name);
                     this.xaveIconClicked.remove(name);
                     this.accountManager.removeAccount(name);
                     if (Objects.equals(this.selectedAccount, name)) {
                        this.selectedAccount = null;
                     }

                     this.updateAccountOrder();
                     return true;
                  }

                  x2 = x + 144.0;
                  x3 = yButtons + 5.0;
                  x4 = 8.0;
                  double copyIconHeight = 8.0;
                  if (sx >= x2 && sx <= x2 + x4 && sy >= x3 && sy <= x3 + copyIconHeight) {
                     Wrapper.mc.keyboardListener.setClipboardString(name);
                     return true;
                  }

                  double favX = x + 156.0;
                  double favY = yButtons + 7.0;
                  double favW = 9.0;
                  double favH = 9.0;
                  if (sx >= favX && sx <= favX + favW && sy >= favY - 3.0 && sy <= favY - 3.0 + favH) {
                     boolean newFavoriteState = !(Boolean)this.xaveIconClicked.getOrDefault(name, false);
                     this.xaveIconClicked.put(name, newFavoriteState);
                     this.accountManager.setFavorite(name, newFavoriteState);
                     this.updateAccountOrder();
                     return true;
                  }

                  if (sx >= x && sx <= x + buttonWidth && sy >= yButtons && sy <= yButtons + rectHeight) {
                     this.selectedAccount = name;
                     Wrapper.mc.session.setUsername(this.selectedAccount);
                     this.accountManager.selectAccount(this.selectedAccount);
                     return true;
                  }
               }

               ++col;
               if (x + buttonWidth + buttonHeight > centerX2 + 380.0 - 10.0) {
                  col = 0;
                  ++row;
               }
            }

            spacing = 13.0;
            buttonWidth = 84.0;
            buttonHeight = 26.0;
            x = buttonWidth * 4.0 + spacing * 3.0;
            rectHeight = centerX2 + (380.0 - x) / 2.0;
            yButtons = centerY2 + 35.0 + 230.0 + 10.0;
            if (sx >= rectHeight && sx <= rectHeight + buttonWidth && sy >= yButtons && sy <= yButtons + buttonHeight) {
               this.showAddDialog = true;
               this.addDialogInputFocused = true;
               this.cursorBlinkStart = System.currentTimeMillis();
               this.addDialogInputText = "";
               this.isAddDialogTextSelected = false;
               return true;
            } else {
               msIconX = 4.0;
               msIconY = 4.0;
               msIconW = 32.0;
               msIconH = 32.0;
               if (sx >= msIconX && sx <= msIconX + msIconW && sy >= msIconY && sy <= msIconY + msIconH) {
                  MicrosoftLogin.getRefreshToken((refreshToken) -> {
                     if (refreshToken != null) {
                        MicrosoftLogin.LoginData data = MicrosoftLogin.login(refreshToken);
                        if (data.isGood()) {
                           Wrapper.mc.execute(() -> {
                              if (data.username != null && !data.username.isEmpty()) {
                                 if (!this.accountNames.containsKey(data.username)) {
                                    this.accountNames.put(data.username, System.currentTimeMillis());
                                    this.accountManager.addAccount(data.username);
                                    this.updateAccountOrder();
                                 }

                                 try {
                                    Wrapper.mc.session = new Session(data.username, data.uuid, data.mcToken, "mojang");
                                    this.selectedAccount = data.username;
                                    this.accountManager.selectAccount(data.username);
                                 } catch (Exception var3) {
                                    var3.printStackTrace();
                                 }
                              }

                           });
                        }

                     }
                  });
                  return true;
               } else {
                  x2 = rectHeight + buttonWidth + spacing;
                  if (sx >= x2 && sx <= x2 + buttonWidth && sy >= yButtons && sy <= yButtons + buttonHeight) {
                     if (!this.accountNames.isEmpty()) {
                        this.showClearDialog = true;
                     }

                     return true;
                  } else {
                     x3 = rectHeight + 2.0 * (buttonWidth + spacing);
                     if (sx >= x3 && sx <= x3 + buttonWidth && sy >= yButtons && sy <= yButtons + buttonHeight) {
                        return true;
                     } else {
                        x4 = rectHeight + 3.0 * (buttonWidth + spacing);
                        if (sx >= x4 && sx <= x4 + buttonWidth && sy >= yButtons && sy <= yButtons + buttonHeight) {
                           savedScroll = this.scroll;
                           Wrapper.mc.displayGuiScreen(new MainMenuScreen());
                           return true;
                        } else {
                           return super.mouseClicked(sx, sy, button);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
      if (this.showAddDialog) {
         return super.mouseScrolled(mouseX, mouseY, delta);
      } else {
         float windowWidth = (float)Wrapper.mc.getMainWindow().getScaledWidth();
         float windowHeight = (float)Wrapper.mc.getMainWindow().getScaledHeight();
         float centerX = (windowWidth - 380.0F) / 2.0F;
         float centerY = (windowHeight - 232.0F) / 2.0F;
         boolean hovered = mouseX >= (double)centerX && mouseX <= (double)(centerX + 380.0F) && mouseY >= (double)(centerY + 35.0F) && mouseY <= (double)(centerY + 35.0F + 232.0F);
         if (hovered) {
            float contentHeight = 222.0F;
            boolean canScroll = this.maxHeight > contentHeight;
            float previousScroll = this.scroll;
            this.scroll += (float)(delta * 60.0);
            this.scroll = MathHelper.clamp(this.scroll, -this.maxHeight + contentHeight, 0.0F);
            return canScroll && this.scroll != previousScroll;
         } else {
            return false;
         }
      }
   }

   public boolean charTyped(char codePoint, int modifiers) {
      if (!Character.toString(codePoint).matches("[a-zA-Z0-9_]")) {
         return true;
      } else if (this.showAddDialog && this.addDialogInputFocused) {
         if (this.isAddDialogTextSelected) {
            this.addDialogInputText = String.valueOf(codePoint);
            this.isAddDialogTextSelected = false;
         } else if (this.addDialogInputText.length() < 16) {
            this.addDialogInputText = this.addDialogInputText + codePoint;
         }

         return true;
      } else if (this.inputFocused && !this.showClearDialog) {
         if (this.isTextSelected) {
            this.inputText = String.valueOf(codePoint);
            this.isTextSelected = false;
         } else if (this.inputText.length() < 16) {
            this.inputText = this.inputText + codePoint;
         }

         return true;
      } else {
         return super.charTyped(codePoint, modifiers);
      }
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      float windowWidth = (float)Wrapper.mc.getMainWindow().getScaledWidth();
      float windowHeight = (float)Wrapper.mc.getMainWindow().getScaledHeight();
      float centerX = (windowWidth - 380.0F) / 2.0F;
      float centerY = (windowHeight - 232.0F) / 2.0F;
      double mouseX = Wrapper.mc.mouseHelper.getMouseX() * (double)Wrapper.mc.getMainWindow().getScaledWidth() / (double)Wrapper.mc.getMainWindow().getWidth();
      double mouseY = Wrapper.mc.mouseHelper.getMouseY() * (double)Wrapper.mc.getMainWindow().getScaledHeight() / (double)Wrapper.mc.getMainWindow().getHeight();
      boolean panelHovered = mouseX >= (double)centerX && mouseX <= (double)(centerX + 380.0F) && mouseY >= (double)(centerY + 35.0F) && mouseY <= (double)(centerY + 35.0F + 232.0F);
      String clipboardText;
      if (panelHovered && keyCode == 86 && (modifiers & 2) != 0 && !this.showAddDialog && !this.showClearDialog) {
         clipboardText = Wrapper.mc.keyboardListener.getClipboardString();
         if (!clipboardText.isEmpty()) {
            String trimmedText = clipboardText.trim();
            trimmedText = trimmedText.substring(0, Math.min(trimmedText.length(), 16));
            if (!trimmedText.isEmpty() && !this.accountNames.containsKey(trimmedText)) {
               this.accountNames.put(trimmedText, System.currentTimeMillis());
               this.accountManager.addAccount(trimmedText);
               this.updateAccountOrder();
               this.scrollToBottom();
            }
         }

         return true;
      } else {
         int remainingChars;
         String var10001;
         if (this.showAddDialog && this.addDialogInputFocused) {
            if (keyCode == 259) {
               if (this.isAddDialogTextSelected) {
                  this.addDialogInputText = "";
                  this.isAddDialogTextSelected = false;
               } else if (!this.addDialogInputText.isEmpty()) {
                  this.addDialogInputText = this.addDialogInputText.substring(0, this.addDialogInputText.length() - 1);
               }

               return true;
            }

            if (keyCode == 257 && !this.addDialogInputText.trim().isEmpty()) {
               clipboardText = this.addDialogInputText.trim();
               this.accountNames.put(clipboardText, System.currentTimeMillis());
               this.accountManager.addAccount(clipboardText);
               this.updateAccountOrder();
               this.addDialogInputText = "";
               this.addDialogInputFocused = false;
               this.isAddDialogTextSelected = false;
               this.showAddDialog = false;
               return true;
            }

            if (keyCode == 65 && (modifiers & 2) != 0) {
               if (!this.addDialogInputText.isEmpty()) {
                  this.isAddDialogTextSelected = true;
               }

               return true;
            }

            if (keyCode == 67 && (modifiers & 2) != 0) {
               if (this.isAddDialogTextSelected && !this.addDialogInputText.isEmpty()) {
                  Wrapper.mc.keyboardListener.setClipboardString(this.addDialogInputText);
               }

               return true;
            }

            if (keyCode == 86 && (modifiers & 2) != 0) {
               clipboardText = Wrapper.mc.keyboardListener.getClipboardString();
               if (!clipboardText.isEmpty()) {
                  clipboardText = clipboardText.substring(0, Math.min(clipboardText.length(), 16));
                  if (this.isAddDialogTextSelected) {
                     this.addDialogInputText = clipboardText;
                     this.isAddDialogTextSelected = false;
                  } else {
                     remainingChars = 16 - this.addDialogInputText.length();
                     if (remainingChars > 0) {
                        var10001 = this.addDialogInputText;
                        this.addDialogInputText = var10001 + clipboardText.substring(0, Math.min(clipboardText.length(), remainingChars));
                     }
                  }
               }

               return true;
            }
         } else if (this.inputFocused && !this.showClearDialog) {
            if (keyCode == 259) {
               if (this.isTextSelected) {
                  this.inputText = "";
                  this.isTextSelected = false;
               } else if (!this.inputText.isEmpty()) {
                  this.inputText = this.inputText.substring(0, this.inputText.length() - 1);
               }

               return true;
            }

            if (keyCode == 65 && (modifiers & 2) != 0) {
               if (!this.inputText.isEmpty()) {
                  this.isTextSelected = true;
               }

               return true;
            }

            if (keyCode == 67 && (modifiers & 2) != 0) {
               if (this.isTextSelected && !this.inputText.isEmpty()) {
                  Wrapper.mc.keyboardListener.setClipboardString(this.inputText);
               }

               return true;
            }

            if (keyCode == 86 && (modifiers & 2) != 0) {
               clipboardText = Wrapper.mc.keyboardListener.getClipboardString();
               if (!clipboardText.isEmpty()) {
                  clipboardText = clipboardText.substring(0, Math.min(clipboardText.length(), 16));
                  if (this.isTextSelected) {
                     this.inputText = clipboardText;
                     this.isTextSelected = false;
                  } else {
                     remainingChars = 16 - this.inputText.length();
                     if (remainingChars > 0) {
                        var10001 = this.inputText;
                        this.inputText = var10001 + clipboardText.substring(0, Math.min(clipboardText.length(), remainingChars));
                     }
                  }
               }

               return true;
            }

            if (keyCode == 256) {
               this.inputText = "";
               this.inputFocused = false;
               this.isTextSelected = false;
               return true;
            }
         }

         if (keyCode == 256) {
            if (this.showClearDialog) {
               this.showClearDialog = false;
               return true;
            } else if (this.showAddDialog) {
               this.showAddDialog = false;
               this.addDialogInputText = "";
               this.addDialogInputFocused = false;
               this.isAddDialogTextSelected = false;
               return true;
            } else {
               Wrapper.mc.displayGuiScreen(new MainMenuScreen());
               return true;
            }
         } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
         }
      }
   }
}
