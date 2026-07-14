package sky.core.modules.impl.visuals;

import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class TargetESP extends Module {
   private final ModeSetting mode = new ModeSetting("Режим", "Кольцо", new String[]{"Не отображать", "Кольцо", "Ромб", "Ромб-2", "Кристаллы", "Кристалики", "Кружочек", "Призраки", "Души", "Души 2", "Преследующие призраки", "Свинки"});
   private final BooleanSetting throughWalls = new BooleanSetting("Через стены", false, () -> {
      return this.mode.is("Кружочек");
   });
   private final BooleanSetting damageRed = new BooleanSetting("Краснение", true, () -> {
      return this.mode.is("Кружочек");
   });
   private final SliderSetting ghostCount = new SliderSetting("Количество", 3.0F, 2.0F, 5.0F, 1.0F, () -> {
      return this.mode.is("Преследующие призраки");
   });
   private final SliderSetting ghostLifeTime = new SliderSetting("Время жизни", 350.0F, 150.0F, 500.0F, 25.0F, () -> {
      return this.mode.is("Преследующие призраки");
   });
   private final SliderSetting strengthXZ = new SliderSetting("Скорость XZ", 2000.0F, 1000.0F, 5000.0F, 100.0F, () -> {
      return this.mode.is("Преследующие призраки");
   });
   private final SliderSetting strengthY = new SliderSetting("Скорость Y", 1700.0F, 1000.0F, 5000.0F, 100.0F, () -> {
      return this.mode.is("Преследующие призраки");
   });
   private final SliderSetting pigSpeed = new SliderSetting("Скорость свинок", 1.5F, 0.5F, 3.0F, 0.1F, () -> {
      return this.mode.is("Свинки");
   });
   private final SliderSetting crystalSize = new SliderSetting("Размер", 2.0F, 0.5F, 3.0F, 0.1F, () -> {
      return this.mode.is("Кристаллы") || this.mode.is("Кристалики") || this.mode.is("Кристалы");
   });

   public TargetESP() {
      super("TargetESP", "ESP цели из Attack Aura", Category.Visuals);
      this.addSettings(new Setting[]{this.mode, this.throughWalls, this.damageRed, this.ghostCount, this.ghostLifeTime, this.strengthXZ, this.strengthY, this.pigSpeed, this.crystalSize});
   }

   public ModeSetting getMode() {
      return this.mode;
   }

   public BooleanSetting getThroughWalls() {
      return this.throughWalls;
   }

   public BooleanSetting getDamageRed() {
      return this.damageRed;
   }

   public SliderSetting getGhostCount() {
      return this.ghostCount;
   }

   public SliderSetting getGhostLifeTime() {
      return this.ghostLifeTime;
   }

   public SliderSetting getStrengthXZ() {
      return this.strengthXZ;
   }

   public SliderSetting getStrengthY() {
      return this.strengthY;
   }

   public SliderSetting getPigSpeed() {
      return this.pigSpeed;
   }

   public SliderSetting getCrystalSize() {
      return this.crystalSize;
   }
}
