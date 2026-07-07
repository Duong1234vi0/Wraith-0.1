package unique.core.features.modules.render;

import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_4587;
import unique.core.core.Managers;
import unique.core.core.manager.client.ShaderManager;
import unique.core.features.modules.Module;
import unique.core.injection.accesors.IGameRenderer;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.setting.impl.SettingGroup;
import unique.core.utility.render.Render3DEngine;

public class Shaders extends Module {
   private final Setting<SettingGroup> select = new Setting<>("Select", new SettingGroup(false, 0));
   private final Setting<Boolean> hands = new Setting<>("Hands", true).addToGroup(this.select);
   private final Setting<Boolean> players = new Setting<>("Players", true).addToGroup(this.select);
   private final Setting<Boolean> self = new Setting<>("Self", true, v -> this.players.getValue()).addToGroup(this.select);
   private final Setting<Boolean> friends = new Setting<>("Friends", true).addToGroup(this.select);
   private final Setting<Boolean> crystals = new Setting<>("Crystals", true).addToGroup(this.select);
   private final Setting<Boolean> creatures = new Setting<>("Creatures", false).addToGroup(this.select);
   private final Setting<Boolean> monsters = new Setting<>("Monsters", false).addToGroup(this.select);
   private final Setting<Boolean> ambients = new Setting<>("Ambients", false).addToGroup(this.select);
   private final Setting<Boolean> others = new Setting<>("Others", false).addToGroup(this.select);
   public Setting<ShaderManager.Shader> mode = new Setting<>("Mode", ShaderManager.Shader.Default);
   public Setting<ShaderManager.Shader> handsMode = new Setting<>("HandsMode", ShaderManager.Shader.Default);
   public final Setting<Integer> maxRange = new Setting<>(
      "MaxRange",
      64,
      16,
      256,
      v -> this.players.getValue()
         || this.crystals.getValue()
         || this.friends.getValue()
         || this.creatures.getValue()
         || this.monsters.getValue()
         || this.ambients.getValue()
         || this.others.getValue()
   );
   public final Setting<Float> factor = new Setting<>(
      "GradientFactor", 2.0F, 0.0F, 20.0F, v -> this.mode.is(ShaderManager.Shader.Gradient) || this.handsMode.is(ShaderManager.Shader.Gradient)
   );
   public final Setting<Float> gradient = new Setting<>(
      "Gradient", 2.0F, 0.0F, 20.0F, v -> this.mode.is(ShaderManager.Shader.Gradient) || this.handsMode.is(ShaderManager.Shader.Gradient)
   );
   public final Setting<Integer> alpha2 = new Setting<>(
      "GradientAlpha", 170, 0, 255, v -> this.mode.is(ShaderManager.Shader.Gradient) || this.handsMode.is(ShaderManager.Shader.Gradient)
   );
   public final Setting<Integer> lineWidth = new Setting<>("LineWidth", 2, 0, 500);
   public final Setting<Integer> quality = new Setting<>("Quality", 3, 0, 6);
   public final Setting<Integer> octaves = new Setting<>("SmokeOctaves", 10, 5, 30);
   public final Setting<Integer> fillAlpha = new Setting<>("FillAlpha", 170, 0, 255);
   public final Setting<Boolean> glow = new Setting<>("SmokeGlow", true);
   private final Setting<SettingGroup> colors = new Setting<>("Colors", new SettingGroup(false, 0));
   public final Setting<ColorSetting> outlineColor = new Setting<>("Outline", new ColorSetting(-2013200640)).addToGroup(this.colors);
   public final Setting<ColorSetting> outlineColor1 = new Setting<>(
         "SmokeOutline", new ColorSetting(-2013200640), v -> this.mode.is(ShaderManager.Shader.Smoke) || this.handsMode.is(ShaderManager.Shader.Smoke)
      )
      .addToGroup(this.colors);
   public final Setting<ColorSetting> outlineColor2 = new Setting<>(
         "SmokeOutline2", new ColorSetting(-2013200640), v -> this.mode.is(ShaderManager.Shader.Smoke) || this.handsMode.is(ShaderManager.Shader.Smoke)
      )
      .addToGroup(this.colors);
   public final Setting<ColorSetting> fillColor1 = new Setting<>("Fill", new ColorSetting(-2013200640)).addToGroup(this.colors);
   public final Setting<ColorSetting> fillColor2 = new Setting<>("SmokeFill", new ColorSetting(-2013200640)).addToGroup(this.colors);
   public final Setting<ColorSetting> fillColor3 = new Setting<>("SmokeFil2", new ColorSetting(-2013200640)).addToGroup(this.colors);

   public Shaders() {
      super("Shaders", Module.Category.RENDER);
   }

   public boolean shouldRender(class_1297 entity) {
      if (entity == null) {
         return false;
      } else if (mc.field_1724 == null) {
         return false;
      } else if (mc.field_1724.method_5707(entity.method_19538()) > this.maxRange.getPow2Value()) {
         return false;
      } else if (entity instanceof class_1657) {
         if (entity == mc.field_1724 && !this.self.getValue()) {
            return false;
         } else {
            return Managers.FRIEND.isFriend((class_1657)entity) ? this.friends.getValue() : this.players.getValue();
         }
      } else if (entity instanceof class_1511) {
         return this.crystals.getValue();
      } else {
         return switch (entity.method_5864().method_5891()) {
            case field_6294, field_6300 -> this.creatures.getValue();
            case field_6302 -> this.monsters.getValue();
            case field_6303, field_24460 -> this.ambients.getValue();
            default -> this.others.getValue();
         };
      }
   }

   @Override
   public void onRender3D(class_4587 matrices) {
      if (this.hands.getValue()) {
         Managers.SHADER
            .renderShader(
               () -> ((IGameRenderer)mc.field_1773)
                  .irenderHand(mc.field_1773.method_19418(), Render3DEngine.getTickDelta(), matrices.method_23760().method_23761()),
               this.handsMode.getValue()
            );
      }
   }

   @Override
   public void onDisable() {
      Managers.SHADER.reloadShaders();
   }
}
