package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.time.LocalTime;
import net.minecraft.network.play.server.SUpdateTimePacket;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ColorSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class Ambience extends Module {
   public static ModeSetting skyMode = new ModeSetting("Небо", "Обычная", new String[]{"Обычная", "Плазма", "Лето"});
   public static ModeSetting summerMode = new ModeSetting("Режим лета", "Дневной", () -> {
      return skyMode.is("Лето");
   }, new String[]{"Дневной", "Ночной"});
   public static ModeSetting timeMode = new ModeSetting("Время", "Не менять", () -> {
      return !skyMode.is("Лето");
   }, new String[]{"Рассвет", "Утро", "День", "Вечер", "Заход солнца", "Ночь", "Время из реальной жизни", "Не менять"});
   public static ModeSetting fogMode = new ModeSetting("Туман", "Ничего не делать", new String[]{"Ничего не делать", "Очистить", "Переопределить"});
   public static ColorSetting fogColor = new ColorSetting("Цвет тумана", false, -1, () -> {
      return fogMode.is("Переопределить");
   });
   public static SliderSetting fogEnd = new SliderSetting("Конец тумана", 1.0F, 0.1F, 1.5F, 0.1F, () -> {
      return fogMode.is("Переопределить");
   });
   public static SliderSetting fogStart;
   public static SliderSetting skySpeed;
   public static SliderSetting skyScale;
   public static SliderSetting skyOutline;
   public static SliderSetting skyGlow;
   public static SliderSetting skyFill;
   public static SliderSetting skyAlpha;

   public Ambience() {
      super("Ambience", "Позволяет изменить атмосферу игры", Category.Visuals);
      this.addSettings(new Setting[]{timeMode, fogMode, fogColor, fogStart, fogEnd, skyMode, summerMode, skySpeed, skyScale, skyOutline, skyGlow, skyFill, skyAlpha});
   }

   @EventTarget
   public void onUpdate(EventPacket event) {
      if (event.getPacket() instanceof SUpdateTimePacket && (!timeMode.is("Не менять") || skyMode.is("Лето"))) {
         event.setCancelled(true);
      }

   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      if (skyMode.is("Лето")) {
         mc.world.setDayTime(18000L);
      } else {
         if (!((String)timeMode.get()).equals("Не менять")) {
            long time;
            if (((String)timeMode.get()).equals("Время из реальной жизни")) {
               time = this.getRealWorldTime();
            } else {
               long var10000;
               switch ((String)timeMode.get()) {
                  case "Рассвет":
                     var10000 = 23000L;
                     break;
                  case "Утро":
                     var10000 = 1000L;
                     break;
                  case "День":
                     var10000 = 6000L;
                     break;
                  case "Вечер":
                     var10000 = 12000L;
                     break;
                  case "Заход солнца":
                     var10000 = 13000L;
                     break;
                  case "Ночь":
                     var10000 = 18000L;
                     break;
                  default:
                     var10000 = mc.world.getDayTime();
               }

               time = var10000;
            }

            mc.world.setDayTime(time);
         }

      }
   }

   private long getRealWorldTime() {
      LocalTime now = LocalTime.now();
      int hours = now.getHour();
      int minutes = now.getMinute();
      int seconds = now.getSecond();
      int totalSeconds = hours * 3600 + minutes * 60 + seconds;
      int offsetSeconds = (totalSeconds - 21600) % 86400;
      if (offsetSeconds < 0) {
         offsetSeconds += 86400;
      }

      return (long)((double)offsetSeconds / 86400.0 * 24000.0);
   }

   static {
      fogStart = new SliderSetting("Начало тумана", 0.5F, 0.1F, 1.5F, 0.1F, () -> {
         return fogMode.is("Переопределить");
      }, fogEnd);
      skySpeed = new SliderSetting("Скорость шейдера", 1.2F, 0.1F, 5.0F, 0.1F, () -> {
         return skyMode.is("Плазма");
      });
      skyScale = new SliderSetting("Масштаб шейдера", 1.0F, 0.2F, 4.0F, 0.1F, () -> {
         return skyMode.is("Плазма") || skyMode.is("Лето");
      });
      skyOutline = new SliderSetting("Контур шейдера", 0.1F, 0.01F, 1.0F, 0.01F, () -> {
         return skyMode.is("Плазма");
      });
      skyGlow = new SliderSetting("Глоу шейдера", 10.0F, 0.0F, 30.0F, 0.5F, () -> {
         return skyMode.is("Плазма");
      });
      skyFill = new SliderSetting("Заливка шейдера", 0.6F, 0.0F, 2.0F, 0.05F, () -> {
         return skyMode.is("Плазма");
      });
      skyAlpha = new SliderSetting("Альфа шейдера", 1.0F, 0.0F, 2.0F, 0.05F, () -> {
         return skyMode.is("Плазма");
      });
   }
}
