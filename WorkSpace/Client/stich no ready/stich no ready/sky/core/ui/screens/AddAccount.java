package sky.core.ui.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class AddAccount extends Screen {
   private TextFieldWidget fieldWidget;
   private Button loginButton;
   private Button randomButton;
   private int textWidth = 200;
   private int buttonHeight = 20;
   private int randomButtonWidth = 50;
   private int loginButtonWidth = 150;
   String currentUsername = Minecraft.getInstance().getSession().getUsername();
   AccountManager accountManagement = new AccountManager();

   public AddAccount() {
      super(new StringTextComponent("Add New Account"));
   }

   public void init(Minecraft minecraft, int width, int height) {
      super.init(minecraft, width, height);
      int textFieldX = this.width / 2 - this.textWidth / 2;
      int textFieldY = this.height / 2 - 60;
      int buttonY = textFieldY + 25;
      this.fieldWidget = new TextFieldWidget(this.font, textFieldX, textFieldY, this.textWidth, 20, new StringTextComponent("Account")) {
         public boolean charTyped(char codePoint, int modifiers) {
            return !Character.toString(codePoint).matches("[a-zA-Z0-9_]") && codePoint != '\b' ? false : super.charTyped(codePoint, modifiers);
         }
      };
      this.fieldWidget.setMaxStringLength(16);
      this.addButton(this.fieldWidget);
      this.loginButton = new Button(textFieldX, buttonY, this.loginButtonWidth, this.buttonHeight, new StringTextComponent("Login"), (button) -> {
         String username = this.fieldWidget.getText().trim();
         if (!username.isEmpty()) {
            this.accountManagement.addAccount(username);
            Minecraft.getInstance().displayGuiScreen(new AccountManager());
         }

      });
      this.addButton(this.loginButton);
      this.randomButton = new Button(textFieldX + this.loginButtonWidth, buttonY, this.randomButtonWidth, this.buttonHeight, new StringTextComponent("Random"), (button) -> {
         Random random = new Random();
         String randomNick = "SkyCore" + (100 + random.nextInt(899));
         this.fieldWidget.setText(randomNick);
      });
      this.addButton(this.randomButton);
      Button cancelButton = new Button(textFieldX, buttonY + 25, this.textWidth, this.buttonHeight, new StringTextComponent("Отмена"), (button) -> {
         Minecraft.getInstance().displayGuiScreen(new AccountManager());
      });
      this.addButton(cancelButton);
   }

   public void tick() {
      super.tick();
      this.fieldWidget.tick();
      this.loginButton.active = this.fieldWidget.getText().trim().length() >= 3;
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      if (this.fieldWidget.isFocused() && Screen.hasControlDown() && keyCode == 86) {
         String clipboard = Minecraft.getInstance().keyboardListener.getClipboardString();
         if (clipboard != null) {
            clipboard = clipboard.replaceAll("[^a-zA-Z0-9_]", "");
            this.fieldWidget.writeText(clipboard);
         }

         return true;
      } else {
         return super.keyPressed(keyCode, scanCode, modifiers);
      }
   }

   public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
      this.renderDirtBackground(0);
      super.render(matrixStack, mouseX, mouseY, partialTicks);
      this.fieldWidget.render(matrixStack, mouseX, mouseY, partialTicks);
      this.loginButton.render(matrixStack, mouseX, mouseY, partialTicks);
      this.randomButton.render(matrixStack, mouseX, mouseY, partialTicks);
      if (this.fieldWidget.getText().isEmpty()) {
         drawString(matrixStack, this.font, "Enter name...", this.width / 2 - this.textWidth / 2, this.height / 2 - 75, 13421772);
      }

      if (!this.fieldWidget.getText().isEmpty()) {
         drawString(matrixStack, this.font, "Enter name", this.width / 2 - this.textWidth / 2, this.height / 2 - 75, 13421772);
      }

      if (this.mouseOverButton(this.loginButton, mouseX, mouseY)) {
         if (!this.loginButton.active) {
            this.renderTooltip(matrixStack, new StringTextComponent("Enter at least 3 characters"), mouseX, mouseY);
         } else {
            this.renderTooltip(matrixStack, new StringTextComponent("Click to login"), mouseX, mouseY);
         }
      }

      if (this.mouseOverButton(this.randomButton, mouseX, mouseY)) {
         this.renderTooltip(matrixStack, new StringTextComponent("Click to generate a random username"), mouseX, mouseY);
      }

      drawCenteredString(matrixStack, this.font, "Login account info", this.width / 2, 10, 16777215);
   }

   private boolean mouseOverButton(Button button, int mouseX, int mouseY) {
      int buttonX1 = button.x;
      int buttonY1 = button.y;
      int buttonX2 = button.x + button.getWidth();
      int buttonY2 = button.y + button.getHeightRealms();
      return mouseX >= buttonX1 && mouseY >= buttonY1 && mouseX < buttonX2 && mouseY < buttonY2;
   }
}
