package unique.core.features.modules.client;

import baritone.api.BaritoneAPI;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_2960;
import unique.core.UniqueCore;
import unique.core.events.impl.EventSetting;
import unique.core.features.modules.Module;
import unique.core.gui.clickui.ClickGUI;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.setting.impl.BooleanSettingGroup;

public class ClickGui extends Module {
   public final Setting<ClickGui.Gradient> gradientMode = new Setting<>("Gradient", ClickGui.Gradient.LeftToRight);
   public final Setting<ClickGui.TextSide> textSide = new Setting<>("TextSide", ClickGui.TextSide.Left);
   public final Setting<ClickGui.scrollModeEn> scrollMode = new Setting<>("ScrollMode", ClickGui.scrollModeEn.Old);
   public final Setting<Integer> catHeight = new Setting<>("CategoryHeight", 300, 100, 720, v -> this.scrollMode.is(ClickGui.scrollModeEn.New));
   public final Setting<Boolean> descriptions = new Setting<>("Descriptions", true);
   public final Setting<Boolean> blur = new Setting<>("Blur", true);
   public final Setting<Boolean> tips = new Setting<>("Tips", true);
   public final Setting<Boolean> pauseBaritone = new Setting<>("PauseBaritone", false);
   public final Setting<ClickGui.Image> image = new Setting<>("Image", ClickGui.Image.None);
   public final Setting<Integer> moduleWidth = new Setting<>("ModuleWidth", 100, 50, 200);
   public final Setting<Integer> moduleHeight = new Setting<>("ModuleHeight", 14, 8, 25);
   public final Setting<Integer> settingFontScale = new Setting<>("SettingFontScale", 12, 6, 24);
   public final Setting<Integer> modulesFontScale = new Setting<>("ModulesFontScale", 14, 6, 24);
   public static final Setting<BooleanSettingGroup> gear = new Setting<>("Gear", new BooleanSettingGroup(true));
   public final Setting<Integer> gearScale = new Setting<>("GearScale", 60, 6, 300).addToGroup(gear);
   public static Setting<Float> gearDuration = new Setting<>("GearDuration", 0.5F, 0.1F, 2.0F).addToGroup(gear);
   public static Setting<Integer> gearStop = new Setting<>("GearStop", 25, 10, 45).addToGroup(gear);
   public final Setting<Boolean> closeAnimation = new Setting<>("CloseAnimation", true);

   public ClickGui() {
      super("ClickGui", Module.Category.CLIENT);
   }

   @Override
   public void onEnable() {
      if (this.pauseBaritone.getValue() && !fullNullCheck() && UniqueCore.baritone) {
         BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("pause");
      }

      this.setGui();
   }

   @Override
   public void onDisable() {
      if (this.pauseBaritone.getValue() && !fullNullCheck() && UniqueCore.baritone) {
         BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("resume");
      }
   }

   public void setGui() {
      mc.method_1507(ClickGUI.getClickGui());
   }

   @Override
   public void onUpdate() {
      if (!(mc.field_1755 instanceof ClickGUI)) {
         this.disable();
      }
   }

   @EventHandler
   public void onSetting(EventSetting e) {
      try {
         if (e.getSetting() == this.settingFontScale) {
            FontRenderers.sf_medium_mini = FontRenderers.create(this.settingFontScale.getValue().intValue(), "sf_medium");
         }

         if (e.getSetting() == this.modulesFontScale) {
            FontRenderers.sf_medium_modules = FontRenderers.create(this.modulesFontScale.getValue().intValue(), "sf_medium");
         }

         if (e.getSetting() == this.image) {
            ClickGUI.getClickGui().imageAnimation.reset();
         }
      } catch (Exception var3) {
      }
   }

   public static enum Gradient {
      UpsideDown,
      LeftToRight,
      both;
   }

   public static enum Image {
      None("", 0, 0, new int[]{0, 0}, 0);

      public final int fileWidth;
      public final int fileHeight;
      public final class_2960 file;
      public final int[] pos;
      public final int size;

      private Image(String file, int fileWidth, int fileHeight, int[] pos, int size) {
         this.fileHeight = fileHeight;
         this.fileWidth = fileWidth;
         this.file = class_2960.method_60655("unique", "textures/gui/images/" + file);
         this.pos = pos;
         this.size = size;
      }
   }

   public static enum TextSide {
      Left,
      Center;
   }

   public static enum colorModeEn {
      Static,
      Sky,
      LightRainbow,
      Rainbow,
      Fade,
      DoubleColor,
      Analogous;
   }

   public static enum scrollModeEn {
      New,
      Old;
   }
}
