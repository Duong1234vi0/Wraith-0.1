package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import sky.core.events.EventNoRender;
import sky.core.events.EventSound;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class Removals extends Module {
   public static MultiBooleanSetting mode = new MultiBooleanSetting("Применять на", new BooleanSetting[]{new BooleanSetting("Тряска камеры", true), new BooleanSetting("Скорборд", true), new BooleanSetting("Удочка на экране", true), new BooleanSetting("Босс-бар", true), new BooleanSetting("Частицы разрушения", true), new BooleanSetting("Дождь", true), new BooleanSetting("Камера клип", true), new BooleanSetting("Тени", true), new BooleanSetting("Дым", true), new BooleanSetting("Снесение тотема", true), new BooleanSetting("Виньетка", true), new BooleanSetting("Стрелы в игроке", true), new BooleanSetting("Голограммы", true), new BooleanSetting("Эффект здоровья", true), new BooleanSetting("Трава", true), new BooleanSetting("Плохие эффекты", true), new BooleanSetting("Свечение игроков", true), new BooleanSetting("Игроки", false), new BooleanSetting("Размытие под водой", true), new BooleanSetting("Огонь", true), new BooleanSetting("Тайтлы", true)});
   public static MultiBooleanSetting sound = new MultiBooleanSetting("Уменьшить звук", new BooleanSetting[]{new BooleanSetting("Трезубец", false), new BooleanSetting("Появление визера", false), new BooleanSetting("Открытие энд-портала", false), new BooleanSetting("Музыкальные пластинки", false), new BooleanSetting("Битье пузырьков опыта", false)});
   public static SliderSetting volume = new SliderSetting("Громкость", 0.5F, 0.0F, 1.0F, 0.05F, () -> {
      return sound.is("Трезубец") || sound.is("Появление визера") || sound.is("Открытие энд-портала") || sound.is("Музыкальные пластинки") || sound.is("Битье пузырьков опыта");
   });
   private boolean lastGrass = false;

   public Removals() {
      super("Removals", "Увеличивает FPS, убирая лишние визуальные эффекты", Category.Visuals);
      this.addSettings(new Setting[]{mode});
   }

   @EventTarget
   private void onEvent(EventNoRender event) {
      boolean var10000;
      switch (event.norenderType) {
         case fire:
            var10000 = mode.is("Огонь");
            break;
         case bossbar:
            var10000 = mode.is("Босс-бар");
            break;
         case scoreboard:
            var10000 = mode.is("Скорборд");
            break;
         case title:
            var10000 = mode.is("Тайтлы");
            break;
         case totem:
            var10000 = mode.is("Снесение тотема");
            break;
         case hurttime:
            var10000 = mode.is("Тряска камеры");
            break;
         case cameraclip:
            var10000 = mode.is("Камера клип");
            break;
         case fishingrod:
            var10000 = mode.is("Удочка на экране");
            break;
         case destroyparticles:
            var10000 = mode.is("Частицы разрушения");
            break;
         case rain:
            var10000 = mode.is("Дождь");
            break;
         case shadows:
            var10000 = mode.is("Тени");
            break;
         case glowing:
            var10000 = mode.is("Свечение игроков");
            break;
         case smoke:
            var10000 = mode.is("Дым");
            break;
         case vignette:
            var10000 = mode.is("Виньетка");
            break;
         case arrows:
            var10000 = mode.is("Стрелы в игроке");
            break;
         case holograms:
            var10000 = mode.is("Голограммы");
            break;
         case grass:
            var10000 = mode.is("Трава");
            break;
         case health:
            var10000 = mode.is("Эффект здоровья");
            break;
         case players:
            var10000 = mode.is("Игроки");
            break;
         case bad_effects:
            var10000 = mode.is("Плохие эффекты");
            break;
         case underwater_blur:
            var10000 = mode.is("Размытие под водой");
            break;
         case container_background:
            var10000 = mode.is("Фон контейнера");
            break;
         case tab:
            var10000 = mode.is("Нанесение таба");
            break;
         case lava:
            var10000 = mode.is("Видимость лавы");
            break;
         default:
            throw new IncompatibleClassChangeError();
      }

      boolean cancel = var10000;
      if (cancel) {
         event.setCancelled(true);
      }

   }

   public void onDisable() {
      super.onDisable();
      if (mc.world != null) {
         mc.worldRenderer.loadRenderers();
      }

   }

   public void onEnable() {
      super.onEnable();
      if (mc.world != null) {
         mc.worldRenderer.loadRenderers();
      }

   }

   @EventTarget
   private void onUpdate(EventUpdate event) {
      boolean grass = mode.is("Трава");
      if (grass != this.lastGrass) {
         this.lastGrass = grass;
         if (mc.world != null) {
            mc.worldRenderer.loadRenderers();
         }
      }

   }

   @EventTarget
   public void onSound(EventSound event) {
      String name = event.getISound().getSoundLocation().toString();
      boolean found = sound.is("Битье пузырьков опыта") && name.contains("minecraft:entity.experience_bottle.throw") || name.contains("minecraft:entity.experience_orb.pickup");
      if (sound.is("Трезубец") && name.contains("minecraft:item.trident.throw")) {
         found = true;
      }

      if (sound.is("Появление визера") && name.contains("minecraft:entity.wither.spawn")) {
         found = true;
      }

      if (sound.is("Открытие энд-портала") && name.contains("minecraft:block.end_portal.spawn")) {
         found = true;
      }

      if (sound.is("Музыкальные пластинки") && name.contains("minecraft:item.record.play")) {
         found = true;
      }

      if (found) {
         event.setFactor((Float)volume.get());
      }

   }
}
