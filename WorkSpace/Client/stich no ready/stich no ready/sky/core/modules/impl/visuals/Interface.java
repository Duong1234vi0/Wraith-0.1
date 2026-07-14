package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import net.minecraft.client.gui.screen.ChatScreen;
import sky.core.SkyCore;
import sky.core.events.EventRender2D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ButtonSetting;
import sky.core.modules.api.constructors.impl.ColorSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.ui.Interface.elements.impl.ArmorRender;
import sky.core.ui.Interface.elements.impl.ArrayListRender;
import sky.core.ui.Interface.elements.impl.InformationRender;
import sky.core.ui.Interface.elements.impl.KeybindsRender;
import sky.core.ui.Interface.elements.impl.MusicRender;
import sky.core.ui.Interface.elements.impl.NotificationRender;
import sky.core.ui.Interface.elements.impl.PotionsRender;
import sky.core.ui.Interface.elements.impl.StaffListRender;
import sky.core.ui.Interface.elements.impl.TargetHudRender;
import sky.core.ui.Interface.elements.impl.TotemCounterRender;
import sky.core.ui.Interface.elements.impl.WatermarkRender;
import sky.core.utils.managers.impl.dragmanager.Dragging;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;

public class Interface extends Module {
   public static ButtonSetting themeeditor = new ButtonSetting("Темы", false, "Открыть темы", "Закрыть темы");
   public static ModeSetting hudStyle = new ModeSetting("Свап HUD", "Old", new String[]{"Old", "New"});
   public static ModeSetting newHudMode = new ModeSetting("Режим худа", "Обычный", Interface::isNewHud, new String[]{"Обычный", "Блюр", "LiquidGlass"});
   public static ModeSetting roundingMode = new ModeSetting("Скругление", "Обычное", new String[]{"Обычное", "iOS"});
   public static MultiBooleanSetting elements = new MultiBooleanSetting("Выбор", new BooleanSetting[]{new BooleanSetting("Броня", true), new BooleanSetting("Счетчик Тотемов", true), new BooleanSetting("Информация о цели", true), new BooleanSetting("Логотип", true), new BooleanSetting("Информация", true), new BooleanSetting("Музыка", true), new BooleanSetting("Администрация онлайн", true), new BooleanSetting("Уведомления", true), new BooleanSetting("Зелья", true), new BooleanSetting("Горячие клавиши", true), new BooleanSetting("Хотбар", true), new BooleanSetting("Arraylist", true)});
   public static ColorSetting colorSetting = new ColorSetting("Цвет", true, ColorUtil.hex("#8A98FFFF"));
   TargetHudRender targetHudRender;
   KeybindsRender keybindsRender;
   WatermarkRender watermarkRender;
   ArmorRender armorRender;
   TotemCounterRender totemCounterRender;
   NotificationRender notificationRender;
   PotionsRender potionsRender;
   StaffListRender staffListRender;
   InformationRender informationRender;
   MusicRender musicRender;
   ArrayListRender arrayListRender;
   private Dragging targetHudDrag;
   private Dragging keybindsDrag;
   private Dragging potionsDrag;
   private Dragging staffDrag;
   private Dragging watermarkDrag;
   private Dragging informationDrag;
   private Dragging musicDrag;
   private Dragging notificationDrag;
   private Dragging arrayListDrag;

   public static boolean isNewHud() {
      return hudStyle.is("New");
   }

   public static boolean isOldHud() {
      return hudStyle.is("Old");
   }

   public static boolean isIosRounding() {
      return roundingMode.is("iOS");
   }

   public static boolean isNewHudBlur() {
      return isNewHud() && newHudMode.is("Блюр");
   }

   private static boolean isElementEnabled(String name) {
      Boolean value = elements.is(name);
      return value == null || value;
   }

   @EventTarget
   private void onRender(EventRender2D.Post e) {
      if (this.isEnabled() && !mc.gameSettings.showDebugInfo) {
         RenderUtil.beginInterfaceRounding();

         try {
            if (this.isEnabled() && mc.currentScreen instanceof ChatScreen && !mc.gameSettings.showDebugInfo) {
               double windowW = (double)mc.getMainWindow().getWidth();
               double windowH = (double)mc.getMainWindow().getHeight();
               float mouseX = (float)(mc.mouseHelper.getMouseX() * (double)mc.getMainWindow().getScaledWidth() / windowW);
               float mouseY = (float)(mc.mouseHelper.getMouseY() * (double)mc.getMainWindow().getScaledHeight() / windowH);
               Dragging.prepareHudDragFrame(mouseX, mouseY);
            } else if (this.isEnabled()) {
               Dragging.tickAllSmoothing();
            }

            Iterator var16 = SkyCore.getInstance().getDraggingManager().getRenderOrder().iterator();

            while(var16.hasNext()) {
               Dragging draggable = (Dragging)var16.next();
               String name = draggable.getName();
               if (isElementEnabled(name)) {
                  draggable.pushRenderTransform();

                  try {
                     renderHudElement(() -> {
                        switch (name) {
                           case "Информация о цели":
                              this.targetHudRender.render(e);
                              break;
                           case "Горячие клавиши":
                              this.keybindsRender.render(e);
                              break;
                           case "Зелья":
                              this.potionsRender.render(e);
                              break;
                           case "Администрация онлайн":
                              this.staffListRender.render(e);
                              break;
                           case "Музыка":
                              this.musicRender.render(e);
                        }

                     });
                  } finally {
                     draggable.popRenderTransform();
                  }
               }
            }

            if (isElementEnabled("Arraylist")) {
               renderWithDragTransform(this.arrayListDrag, () -> {
                  this.arrayListRender.render(e);
               });
            }

            if (isElementEnabled("Счетчик Тотемов")) {
               renderHudElement(() -> {
                  this.totemCounterRender.render(e);
               });
            }

            if (isElementEnabled("Броня")) {
               renderHudElement(() -> {
                  this.armorRender.render(e);
               });
            }

            if (isElementEnabled("Логотип")) {
               renderWithDragTransform(this.watermarkDrag, () -> {
                  this.watermarkRender.render(e);
               });
            }

            if (isElementEnabled("Информация")) {
               renderWithDragTransform(this.informationDrag, () -> {
                  this.informationRender.render(e);
               });
            }

            if (isElementEnabled("Уведомления")) {
               renderWithDragTransform(this.notificationDrag, () -> {
                  this.notificationRender.render(e);
               });
            }
         } finally {
            RenderUtil.endInterfaceRounding();
         }

      }
   }

   private static void renderWithDragTransform(Dragging drag, Runnable render) {
      if (drag == null) {
         renderHudElement(render);
      } else {
         drag.pushRenderTransform();

         try {
            renderHudElement(render);
         } finally {
            drag.popRenderTransform();
         }

      }
   }

   private static void renderHudElement(Runnable render) {
      RenderUtil.beginHudLiquidGlass();

      try {
         render.run();
      } finally {
         RenderUtil.endHudLiquidGlass();
      }

   }

   public Interface() {
      super("Interface", "Визуальный интерфейс клиента", Category.Visuals);
      this.targetHudDrag = new Dragging("Информация о цели", 4.0F, 100.0F, elements);
      this.keybindsDrag = new Dragging("Горячие клавиши", 297.0F, 100.0F, elements);
      this.potionsDrag = new Dragging("Зелья", 357.0F, 100.0F, elements);
      this.staffDrag = new Dragging("Администрация онлайн", 447.0F, 100.0F, elements);
      this.watermarkDrag = new Dragging("Логотип", 4.0F, 4.0F, elements, true);
      this.informationDrag = new Dragging("Информация", 4.0F, 26.0F, elements, true);
      this.musicDrag = new Dragging("Музыка", 4.0F, 50.0F, elements, true);
      this.notificationDrag = new Dragging("Уведомления", (float)mc.getMainWindow().getScaledWidth() / 2.0F, (float)mc.getMainWindow().getScaledHeight() / 2.0F + 21.0F, elements, false);
      this.arrayListDrag = new Dragging("Arraylist", 4.0F, 8.0F, elements, true);
      this.targetHudRender = new TargetHudRender(this.targetHudDrag);
      this.keybindsRender = new KeybindsRender(this.keybindsDrag);
      this.armorRender = new ArmorRender();
      this.totemCounterRender = new TotemCounterRender();
      this.watermarkRender = new WatermarkRender(this.watermarkDrag);
      this.informationRender = new InformationRender(this.informationDrag);
      this.musicRender = new MusicRender(this.musicDrag);
      this.notificationRender = new NotificationRender(this.notificationDrag);
      this.potionsRender = new PotionsRender(this.potionsDrag);
      this.staffListRender = new StaffListRender(this.staffDrag);
      this.arrayListRender = new ArrayListRender(this.arrayListDrag);
      this.keybindsDrag.addSettings(KeybindsRender.alphabg);
      this.staffDrag.addSettings(StaffListRender.skins, StaffListRender.alphabg);
      this.potionsDrag.addSettings(PotionsRender.badeffects, PotionsRender.alphabg);
      this.notificationDrag.addSettings(NotificationRender.alphabg, NotificationRender.shield, NotificationRender.spec, NotificationRender.warps, NotificationRender.module, NotificationRender.lowstrength, NotificationRender.effects);
      this.targetHudDrag.addSettings(TargetHudRender.hpbar, TargetHudRender.goldhealth, TargetHudRender.alphabg, TargetHudRender.particles2, TargetHudRender.ontarget, TargetHudRender.armor);
      this.watermarkDrag.addSettings(WatermarkRender.alphabg);
      this.informationDrag.addSettings(InformationRender.fps, InformationRender.bps, InformationRender.coords);
      this.arrayListDrag.addSettings(ArrayListRender.showCombat, ArrayListRender.showMovement, ArrayListRender.showVisuals, ArrayListRender.showPlayer, ArrayListRender.showMisc);
      this.addSettings(new Setting[]{elements, themeeditor, hudStyle, newHudMode, roundingMode});
   }

   public static ColorSetting getColorSetting() {
      return colorSetting;
   }
}
