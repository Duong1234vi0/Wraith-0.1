package unique.core.gui.windows.impl;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_332;
import net.minecraft.class_3544;
import net.minecraft.class_3675;
import unique.core.core.Managers;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.clickui.ClickGUI;
import unique.core.gui.clickui.impl.SliderElement;
import unique.core.gui.font.FontRenderers;
import unique.core.gui.windows.WindowBase;
import unique.core.setting.Setting;
import unique.core.setting.impl.PositionSetting;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.TextureStorage;

public class FriendsWindow extends WindowBase {
   private static FriendsWindow instance;
   private ArrayList<FriendsWindow.FriendPlate> friendPlates = new ArrayList<>();
   private int listeningId = -1;
   private String search = "Search";
   private String addName = "Name";

   public FriendsWindow(float x, float y, float width, float height, Setting<PositionSetting> position) {
      super(x, y, width, height, "Friends", position, TextureStorage.playerIcon);
      this.refresh();
   }

   public static FriendsWindow get(float x, float y, Setting<PositionSetting> position) {
      if (instance == null) {
         instance = new FriendsWindow(x, y, 200.0F, 180.0F, position);
      }

      instance.refresh();
      return instance;
   }

   @Override
   public void render(class_332 context, int mouseX, int mouseY) {
      super.render(context, mouseX, mouseY);
      Color color = new Color(-986500301, true);
      Color color2 = new Color(-983868581, true);
      Color hoveredColor = new Color(-985052855, true);
      int textColor = new Color(12434877).getRGB();
      boolean hover1 = Render2DEngine.isHovered(mouseX, mouseY, this.getX() + this.getWidth() - 90.0F, this.getY() + 3.0F, 70.0, 10.0);
      Render2DEngine.drawRectWithOutline(
         context.method_51448(), this.getX() + this.getWidth() - 90.0F, this.getY() + 3.0F, 70.0F, 10.0F, hover1 ? hoveredColor : color, color2
      );
      FontRenderers.sf_medium_mini
         .drawString(context.method_51448(), this.search, this.getX() + this.getWidth() - 86.0F, this.getY() + 7.0F, new Color(14013909).getRGB());
      if (this.friendPlates.isEmpty()) {
         FontRenderers.sf_medium
            .drawCenteredString(
               context.method_51448(),
               ClientSettings.isRu() ? "Тут пока пусто" : "It's empty here yet",
               this.getX() + this.getWidth() / 2.0F,
               this.getY() + this.getHeight() / 2.0F,
               new Color(12434877).getRGB()
            );
      }

      String blink2 = System.currentTimeMillis() / 240L % 2L == 0L ? "" : "l";
      boolean hover2 = Render2DEngine.isHovered(mouseX, mouseY, this.getX() + 11.0F, this.getY() + 19.0F, this.getWidth() - 28.0F, 11.0);
      Render2DEngine.drawRectWithOutline(
         context.method_51448(), this.getX() + 11.0F, this.getY() + 19.0F, this.getWidth() - 28.0F, 11.0F, hover2 ? hoveredColor : color, color2
      );
      FontRenderers.sf_medium
         .drawString(
            context.method_51448(),
            this.addName + (this.listeningId == -3 ? blink2 : ""),
            this.getX() + 13.0F,
            this.getY() + 23.0F,
            new Color(12434877).getRGB()
         );
      boolean hover5 = Render2DEngine.isHovered(mouseX, mouseY, this.getX() + this.getWidth() - 15.0F, this.getY() + 19.0F, 11.0, 11.0);
      Render2DEngine.drawRectWithOutline(
         context.method_51448(), this.getX() + this.getWidth() - 15.0F, this.getY() + 19.0F, 11.0F, 11.0F, hover5 ? hoveredColor : color, color2
      );
      FontRenderers.categories.drawString(context.method_51448(), "+", this.getX() + this.getWidth() - 12.0F, this.getY() + 23.0F, -1);
      Render2DEngine.horizontalGradient(
         context.method_51448(),
         this.getX() + 2.0F,
         this.getY() + 33.0F,
         this.getX() + 2.0F + this.getWidth() / 2.0F - 2.0F,
         this.getY() + 33.5F,
         Render2DEngine.injectAlpha(HudEditor.textColor.getValue().getColorObject(), 0),
         HudEditor.textColor.getValue().getColorObject()
      );
      Render2DEngine.horizontalGradient(
         context.method_51448(),
         this.getX() + 2.0F + this.getWidth() / 2.0F - 2.0F,
         this.getY() + 33.0F,
         this.getX() + 2.0F + this.getWidth() - 4.0F,
         this.getY() + 33.5F,
         HudEditor.textColor.getValue().getColorObject(),
         Render2DEngine.injectAlpha(HudEditor.textColor.getValue().getColorObject(), 0)
      );
      Render2DEngine.addWindow(
         context.method_51448(), this.getX(), this.getY() + 38.0F, this.getX() + this.getWidth(), this.getY() + this.getHeight() - 1.0F, 1.0
      );
      hover2 = (boolean)0;

      for (FriendsWindow.FriendPlate friendPlate : this.friendPlates) {
         hover2++;
         if (!((int)(friendPlate.offset + this.getY() + 25.0F) + this.getScrollOffset() > this.getY() + this.getHeight())
            && !(friendPlate.offset + this.getScrollOffset() + this.getY() + 10.0F < this.getY())) {
            boolean online = Module.mc.field_1724 != null
                  && Module.mc.field_1724.field_3944.method_2880().stream().map(p -> p.method_2966().getName()).toList().contains(friendPlate.name())
               || Managers.TELEMETRY.getOnlinePlayers().contains(friendPlate.name());
            Render2DEngine.drawRectWithOutline(
               context.method_51448(),
               this.getX() + 11.0F,
               friendPlate.offset + this.getY() + 36.0F + this.getScrollOffset(),
               this.getWidth() - 28.0F,
               11.0F,
               color,
               color2
            );
            FontRenderers.sf_medium
               .drawString(
                  context.method_51448(),
                  friendPlate.name() + (online ? class_124.field_1063 + " l " + class_124.field_1060 + "Online" : ""),
                  this.getX() + 13.0F,
                  friendPlate.offset + this.getY() + 40.0F + this.getScrollOffset(),
                  textColor
               );
            boolean hover5x = Render2DEngine.isHovered(
               mouseX, mouseY, this.getX() + this.getWidth() - 15.0F, friendPlate.offset + this.getY() + 36.0F + this.getScrollOffset(), 11.0, 11.0
            );
            Render2DEngine.drawRectWithOutline(
               context.method_51448(),
               this.getX() + this.getWidth() - 15.0F,
               friendPlate.offset + this.getY() + 36.0F + this.getScrollOffset(),
               11.0F,
               11.0F,
               hover5x ? hoveredColor : color,
               color2
            );
            FontRenderers.icons
               .drawString(
                  context.method_51448(), "w", this.getX() + this.getWidth() - 15.0F, friendPlate.offset + this.getY() + 40.0F + this.getScrollOffset(), -1
               );
            FontRenderers.sf_medium_mini
               .drawString(
                  context.method_51448(), hover2 + ".", this.getX() + 3.0F, friendPlate.offset + this.getY() + 41.0F + this.getScrollOffset(), textColor
               );
         }
      }

      this.setMaxElementsHeight(this.friendPlates.size() * 20);
      Render2DEngine.popWindow();
   }

   @Override
   public void mouseClicked(double mouseX, double mouseY, int button) {
      super.mouseClicked(mouseX, mouseY, button);
      if (Render2DEngine.isHovered(mouseX, mouseY, this.getX() + this.getWidth() - 90.0F, this.getY() + 3.0F, 70.0, 10.0)) {
         this.listeningId = -2;
         this.search = "";
      }

      if (Render2DEngine.isHovered(mouseX, mouseY, this.getX() + this.getWidth() - 15.0F, this.getY() + 3.0F, 10.0, 10.0)) {
         Module.mc.method_1507(ClickGUI.getClickGui());
      }

      boolean hoveringName = Render2DEngine.isHovered(mouseX, mouseY, this.getX() + 11.0F, this.getY() + 19.0F, this.getWidth() - 28.0F, 11.0);
      boolean hoveringAdd = Render2DEngine.isHovered(mouseX, mouseY, this.getX() + this.getWidth() - 15.0F, this.getY() + 19.0F, 11.0, 11.0);
      if (hoveringName) {
         this.addName = "";
         this.listeningId = -3;
      }

      if (hoveringAdd && !this.addName.isEmpty()) {
         Managers.FRIEND.addFriend(this.addName);
         this.addName = "";
         this.refresh();
      }

      for (FriendsWindow.FriendPlate friendPlate : Lists.newArrayList(this.friendPlates)) {
         if (!((int)(friendPlate.offset + this.getY() + 50.0F) + this.getScrollOffset() > this.getY() + this.getHeight())) {
            boolean hoveringRemove = Render2DEngine.isHovered(
               mouseX, mouseY, this.getX() + this.getWidth() - 15.0F, friendPlate.offset + this.getY() + 36.0F + this.getScrollOffset(), 11.0, 11.0
            );
            if (hoveringRemove) {
               Managers.FRIEND.removeFriend(friendPlate.name());
               this.refresh();
            }
         }
      }
   }

   @Override
   public void keyPressed(int keyCode, int scanCode, int modifiers) {
      if (keyCode != 70
         || !class_3675.method_15987(Module.mc.method_22683().method_4490(), 341) && !class_3675.method_15987(Module.mc.method_22683().method_4490(), 345)) {
         if (this.listeningId != -1) {
            switch (keyCode) {
               case 32:
                  if (this.listeningId == -2) {
                     this.search = this.search + " ";
                  }
                  break;
               case 256:
                  if (this.listeningId == -2) {
                     this.search = "Search";
                  }

                  this.listeningId = -1;
                  this.refresh();
                  break;
               case 257:
                  if (!this.addName.isEmpty()) {
                     Managers.FRIEND.addFriend(this.addName);
                  }

                  if (this.listeningId != -2) {
                     this.listeningId = -1;
                  }

                  this.addName = "Name";
                  this.refresh();
                  break;
               case 259:
                  if (this.listeningId == -2) {
                     this.search = SliderElement.removeLastChar(this.search);
                     this.refresh();
                     if (Objects.equals(this.search, "")) {
                        this.listeningId = -1;
                        this.search = "Search";
                     }

                     return;
                  }

                  if (this.listeningId == -3) {
                     this.addName = SliderElement.removeLastChar(this.addName);
                  }
            }
         }
      } else {
         this.listeningId = -2;
      }
   }

   @Override
   public void charTyped(char key, int keyCode) {
      if (class_3544.method_57175(key) && this.listeningId != -1) {
         if (this.listeningId == -2) {
            this.search = this.search + key;
         }

         if (this.listeningId == -3) {
            this.addName = this.addName + key;
         }

         this.refresh();
      }
   }

   private void refresh() {
      this.resetScroll();
      this.friendPlates.clear();
      int id1 = 0;

      for (String f : Managers.FRIEND.getFriends()) {
         if (this.search.equals("Search") || this.search.isEmpty() || f.contains(this.search)) {
            this.friendPlates.add(new FriendsWindow.FriendPlate(id1, (float)(id1 * 20 + 8), f));
            id1++;
         }
      }
   }

   private record FriendPlate(int id, float offset, String name) {
   }
}
