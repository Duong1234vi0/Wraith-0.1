package dev.client.modules.impl.render;

import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.settings.Setting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.util.animations.Animation;
import dev.client.util.animations.Direction;
import dev.client.util.animations.impl.EaseBackIn;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import ru.metaculture.sdk.Compile;

@Environment(EnvType.CLIENT)
public class Wings extends Module implements ITickable {
   private final ModeSetting mode = ((ModeSetting)(new ModeSetting()).name("Mode")).value("Type 1").modes("Type 1", "Type 2", "Type 3");
   public static final Animation animationWings = new EaseBackIn(550, (double)0.5F, 0.001F);

   public Wings() {
      super(new ModuleInfo("Wings", Category.RENDER, "Добавляет игроку крылья за спиной"));
      this.addSetting(new Setting[]{this.mode});
   }

   @Compile
   public void onTick(TickEvent event) {
      if (animationWings.finished(Direction.FORWARDS)) {
         animationWings.setDirection(Direction.BACKWARDS);
      } else if (animationWings.finished(Direction.BACKWARDS)) {
         animationWings.setDirection(Direction.FORWARDS);
      }

   }

   public Identifier getMode() {
      String var10001 = this.mode.getValue().toLowerCase();
      return Identifier.of("wild", "images/wings/" + var10001.replace(" ", "") + ".png");
   }
}
