package sky.core.ui.screens;

import com.adl.nativeprotect.Native;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.util.text.StringTextComponent;

public class AccountManager extends Screen {
   private long lastClickTime = 0L;
   private static final long DOUBLE_CLICK_INTERVAL = 250L;
   private boolean isScrolling = false;
   private int lastMouseY = 0;
   private int scrollOffset = 0;
   private final int maxListHeight = 200;
   private Button loginButton;
   private Button deleteButton;
   private static List filteredAccounts = new ArrayList();
   private static final List accounts = new ArrayList();
   private TextFieldWidget fieldWidget;
   private Account selectedAccount = null;
   private static TextFieldWidget searchField;
   private final int accountListStartY = 45;
   private final int accountListEntryHeight = 20;
   private int selectedAccountColor = 16777215;
   private static final ResourceLocation STEVE_SKIN = new ResourceLocation("textures/entity/steve.png");
   private static final File LAST_ACCOUNT_FILE = new File("last_account.txt");

   public AccountManager() {
      super(new StringTextComponent("Account Manager"));
      this.loadAccounts();
      this.readLastAlt();
   }

   @Native
   public void init() {
      searchField = new TextFieldWidget(this.font, this.width / 2 - 100, 20, 200, 20, new StringTextComponent(""));
      searchField.setResponder(this::filterAccounts);
      this.children.add(searchField);
      int buttonWidth = 100;
      int buttonHeight = 20;
      int startY = this.height - 35;
      this.loginButton = (Button)this.addButton(new Button(this.width / 2 - (buttonWidth + 5) * 2, startY, buttonWidth, buttonHeight, new StringTextComponent("Login"), (button) -> {
         this.loginSelectedAccount();
      }));
      this.deleteButton = (Button)this.addButton(new Button(this.width / 2 - (buttonWidth + 5), startY, buttonWidth, buttonHeight, new StringTextComponent("Delete"), (button) -> {
         this.deleteSelectedAccount();
      }));
      this.addButton(new Button(this.width / 2, startY, buttonWidth, buttonHeight, new StringTextComponent("Add Account"), (button) -> {
         Minecraft.getInstance().displayGuiScreen(new AddAccount());
      }));
      this.addButton(new Button(this.width / 2 + buttonWidth + 5, startY, buttonWidth, buttonHeight, new StringTextComponent("Отмена"), (button) -> {
         Minecraft.getInstance().displayGuiScreen(new MainMenuScreen());
      }));
      this.fieldWidget = new TextFieldWidget(this.font, this.width / 2 - 150, 100, 300, 20, new StringTextComponent("Username"));
      this.fieldWidget.setVisible(false);
      this.fieldWidget.setMaxStringLength(16);
      this.children.add(this.fieldWidget);
      filteredAccounts = new ArrayList(accounts);
   }

   public void addAccount(String username) {
      if (this.isValidUsername(username)) {
         Iterator var2 = accounts.iterator();

         Account account;
         do {
            if (!var2.hasNext()) {
               accounts.add(new Account(username));
               this.saveAccounts();
               return;
            }

            account = (Account)var2.next();
         } while(!account.username.equalsIgnoreCase(username));

      }
   }

   @Native
   public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
      this.renderBackground(matrixStack);
      super.render(matrixStack, mouseX, mouseY, partialTicks);
      this.loginButton.active = this.selectedAccount != null;
      this.deleteButton.active = this.selectedAccount != null;
      String currentUsername = Minecraft.getInstance().getSession().getUsername();
      String screenTitle = "Account Management (" + currentUsername + ")";
      this.font.drawString(matrixStack, screenTitle, (float)this.width / 2.0F - (float)this.font.getStringWidth(screenTitle) / 2.0F, 8.0F, 16777215);
      int listWidth = 300;
      int listX = this.width / 2 - listWidth / 2;
      int listY = 45;
      int visibleAccounts = 10;
      int startIndex = this.scrollOffset / 20;
      int endIndex = Math.min(filteredAccounts.size(), startIndex + visibleAccounts);
      int maxRenderY = this.height - 35 - 20;
      fill(matrixStack, listX, listY, listX + listWidth, listY + 200, Integer.MIN_VALUE);
      if (!filteredAccounts.isEmpty()) {
         for(int i = startIndex; i < endIndex; ++i) {
            int entryY = listY + (i - startIndex) * 20 - this.scrollOffset % 20;
            if (entryY + 20 <= maxRenderY) {
               Account account = (Account)filteredAccounts.get(i);
               String accountName = account.username;
               if (this.selectedAccount != null && accountName.equals(this.selectedAccount.username)) {
                  fill(matrixStack, listX, entryY, listX + listWidth, entryY + 20, (new Color(124, 124, 125, 255)).getRGB());
                  fill(matrixStack, listX + 1, entryY + 1, listX + listWidth - 1, entryY + 20 - 1, (new Color(0, 0, 0, 255)).getRGB());
               }

               Minecraft.getInstance().getTextureManager().bindTexture(STEVE_SKIN);
               GlStateManager.enableBlend();
               drawScaledCustomSizeModalRect((float)(listX + 2), (float)(entryY + 2), 8.0F, 8.0F, 8.0F, 8.0F, 16.0F, 16.0F, 64.0F, 64.0F);
               GlStateManager.disableBlend();
               int textColor = accountName.equals(currentUsername) ? '\uff00' : 16777215;
               drawString(matrixStack, this.font, accountName, listX + 21, entryY + 6, textColor);
            }

            int searchFieldX;
            if (filteredAccounts.size() > visibleAccounts) {
               int scrollBarHeight = 200 * visibleAccounts / filteredAccounts.size();
               searchFieldX = listY + this.scrollOffset * (200 - scrollBarHeight) / (filteredAccounts.size() * 20 - 200);
               fill(matrixStack, listX + listWidth + 2, searchFieldX, listX + listWidth + 6, searchFieldX + scrollBarHeight, -1);
            }

            String searchFieldText = "Find Account...";
            searchFieldX = this.width / 2 - this.font.getStringWidth(searchFieldText) / 2 - 60;
            int searchFieldY = 26;
            if (!searchField.isFocused()) {
               this.font.drawString(matrixStack, searchFieldText, (float)searchFieldX, (float)searchFieldY, 13421772);
            }

            int scrollBarHeight = '鱀' / (filteredAccounts.size() * 20);
            if (scrollBarHeight < 200) {
               fill(matrixStack, listX + listWidth + 2, listY, listX + listWidth + 6, listY + 200, -16777216);
               fill(matrixStack, listX + listWidth + 2, listY + this.scrollOffset * 200 / (filteredAccounts.size() * 20), listX + listWidth + 6, listY + this.scrollOffset * 200 / (filteredAccounts.size() * 20) + scrollBarHeight, -1);
            }

            if (mouseX >= listX && mouseX < listX + listWidth && mouseY >= entryY && mouseY < entryY + 20) {
               fill(matrixStack, listX + 2, entryY + 2, listX + 18, entryY + 18, (new Color(0, 0, 0, 100)).getRGB());
            }
         }
      } else {
         String noAccountsText = "Accounts not found!";
         this.font.drawString(matrixStack, noAccountsText, (float)this.width / 2.0F - (float)this.font.getStringWidth(noAccountsText) / 2.0F, (float)(listY + 20), 16777215);
      }

      searchField.render(matrixStack, mouseX, mouseY, partialTicks);
      if (this.fieldWidget.getVisible()) {
         this.fieldWidget.render(matrixStack, mouseX, mouseY, partialTicks);
      }

   }

   @Native
   public static void drawScaledCustomSizeModalRect(float x, float y, float u, float v, float uWidth, float vHeight, float width, float height, float tileWidth, float tileHeight) {
      float f = 1.0F / tileWidth;
      float f1 = 1.0F / tileHeight;
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
      bufferbuilder.pos((double)x, (double)(y + height), 0.0).tex(u * f, (v + vHeight) * f1).endVertex();
      bufferbuilder.pos((double)(x + width), (double)(y + height), 0.0).tex((u + uWidth) * f, (v + vHeight) * f1).endVertex();
      bufferbuilder.pos((double)(x + width), (double)y, 0.0).tex((u + uWidth) * f, v * f1).endVertex();
      bufferbuilder.pos((double)x, (double)y, 0.0).tex(u * f, v * f1).endVertex();
      tessellator.draw();
   }

   @Native
   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      int listWidth = 300;
      int listX = this.width / 2 - listWidth / 2;
      int visibleAccounts = 10;
      int startIndex = this.scrollOffset / 20;
      int endIndex = Math.min(filteredAccounts.size(), startIndex + visibleAccounts);
      int maxRenderY = this.height - 35 - 20;
      if (button == 0) {
         long currentTime = System.currentTimeMillis();

         for(int i = startIndex; i < endIndex; ++i) {
            int entryY = 45 + (i - startIndex) * 20 - this.scrollOffset % 20;
            if (entryY + 20 <= maxRenderY && mouseX >= (double)listX && mouseX < (double)(listX + listWidth) && mouseY >= (double)entryY && mouseY < (double)(entryY + 20)) {
               this.selectedAccount = (Account)filteredAccounts.get(i);
               this.selectedAccountColor = 16776960;
               if (currentTime - this.lastClickTime < 250L) {
                  this.loginSelectedAccount();
               }

               this.lastClickTime = currentTime;
               return true;
            }
         }
      }

      return super.mouseClicked(mouseX, mouseY, button);
   }

   @Native
   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      if (this.isScrolling) {
         this.isScrolling = false;
         return true;
      } else {
         return super.mouseReleased(mouseX, mouseY, button);
      }
   }

   public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      if (this.isScrolling) {
         int deltaYInt = (int)mouseY - this.lastMouseY;
         this.scrollOffset -= deltaYInt;
         this.scrollOffset = Math.max(0, Math.min(this.scrollOffset, filteredAccounts.size() * 20 - 200));
         this.lastMouseY = (int)mouseY;
         return true;
      } else {
         return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
      }
   }

   private void filterAccounts(String query) {
      if (query.isEmpty()) {
         filteredAccounts = new ArrayList(accounts);
      } else {
         filteredAccounts.clear();
         Iterator var2 = accounts.iterator();

         while(var2.hasNext()) {
            Account account = (Account)var2.next();
            if (account.username.toLowerCase().contains(query.toLowerCase())) {
               filteredAccounts.add(account);
            }
         }
      }

   }

   private void loginSelectedAccount() {
      if (this.selectedAccount != null) {
         Minecraft.getInstance().session = new Session(this.selectedAccount.username, "", "", "mojang");
         this.selectedAccountColor = 65280;
      }

   }

   public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
      if (this.isMouseOverScreen(mouseX, mouseY)) {
         this.scrollOffset -= (int)delta * 20;
         int maxScroll = Math.max(0, filteredAccounts.size() * 20 - 200);
         this.scrollOffset = Math.max(0, Math.min(this.scrollOffset, maxScroll));
         return true;
      } else {
         return super.mouseScrolled(mouseX, mouseY, delta);
      }
   }

   private boolean isMouseOverScreen(double mouseX, double mouseY) {
      int listWidth = 300;
      int listX = this.width / 2 - listWidth / 2;
      int listY = 45;
      return mouseX >= (double)listX && mouseX < (double)(listX + listWidth) && mouseY >= (double)listY && mouseY < (double)(listY + 200);
   }

   private void deleteSelectedAccount() {
      if (this.selectedAccount != null) {
         accounts.remove(this.selectedAccount);
         this.saveAccounts();
         this.selectedAccount = null;
      }

   }

   private boolean isValidUsername(String username) {
      return username.length() >= 3 && username.length() <= 16 ? username.matches("[a-zA-Z0-9_]+") : false;
   }

   private void saveAccounts() {
      try {
         PrintWriter out = new PrintWriter("accounts.txt");

         try {
            Set uniqueUsernames = new HashSet();
            Iterator var3 = accounts.iterator();

            while(var3.hasNext()) {
               Account account = (Account)var3.next();
               if (uniqueUsernames.add(account.username.toLowerCase()) && this.isValidUsername(account.username)) {
                  out.println(account.username);
               }
            }
         } catch (Throwable var6) {
            try {
               out.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }

            throw var6;
         }

         out.close();
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }

   private void saveLastAccount() {
      if (accounts != null && !accounts.isEmpty()) {
         Account lastAccount = (Account)accounts.get(accounts.size() - 1);
         if (this.isValidUsername(lastAccount.username)) {
            try {
               PrintWriter out = new PrintWriter("last_account.txt");

               try {
                  out.println(lastAccount.username);
               } catch (Throwable var6) {
                  try {
                     out.close();
                  } catch (Throwable var5) {
                     var6.addSuppressed(var5);
                  }

                  throw var6;
               }

               out.close();
            } catch (Exception var7) {
               var7.printStackTrace();
            }

         }
      }
   }

   public void readLastAlt() {
      try {
         BufferedReader reader = Files.newBufferedReader(LAST_ACCOUNT_FILE.toPath(), StandardCharsets.UTF_8);

         try {
            String last;
            String line;
            for(last = null; (line = reader.readLine()) != null; last = line.trim()) {
            }

            if (last != null) {
               Minecraft.getInstance().session = new Session(last, "", "", "mojang");
            }
         } catch (Throwable var5) {
            if (reader != null) {
               try {
                  reader.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }
            }

            throw var5;
         }

         if (reader != null) {
            reader.close();
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   private void loadAccounts() {
      File file = new File("accounts.txt");
      if (file.exists()) {
         try {
            Scanner scanner = new Scanner(file);

            try {
               accounts.clear();
               Set uniqueUsernames = new HashSet();

               while(scanner.hasNextLine()) {
                  String username = scanner.nextLine();
                  if (uniqueUsernames.add(username.toLowerCase()) && this.isValidUsername(username)) {
                     accounts.add(new Account(username));
                  }
               }
            } catch (Throwable var6) {
               try {
                  scanner.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }

               throw var6;
            }

            scanner.close();
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }

      if (searchField != null) {
         this.filterAccounts(searchField.getText());
      }

   }

   public void onClose() {
      this.saveAccounts();
      super.onClose();
   }

   private static class Account {
      String username;

      Account(String username) {
         this.username = username;
      }
   }
}
