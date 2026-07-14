package sky.core.modules.impl.player;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Hand;
import org.apache.commons.lang3.RandomStringUtils;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.player.MoveUtil;

public class AntiAFK extends Module {
   private final MultiBooleanSetting actions = new MultiBooleanSetting("Действия", new BooleanSetting[]{new BooleanSetting("Прыжок", false), new BooleanSetting("Команда", true), new BooleanSetting("Качание рукой", false)});
   private final TimeUtil timer = new TimeUtil();

   public AntiAFK() {
      super("AntiAFK", "Предотвращает кик сервером за AFK", Category.Player);
      this.addSettings(new Setting[]{this.actions});
   }

   public void onDisable() {
      this.timer.reset();
      super.onDisable();
   }

   @EventTarget
   @Native
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (MoveUtil.isMoving()) {
            this.timer.reset();
         } else {
            if (this.timer.hasReached(20000.0)) {
               if (this.actions.is("Прыжок")) {
                  var10000 = mc;
                  if (Minecraft.player.isOnGround()) {
                     var10000 = mc;
                     Minecraft.player.jump();
                  }
               }

               if (this.actions.is("Команда")) {
                  var10000 = mc;
                  Minecraft.player.sendChatMessage("/" + RandomStringUtils.randomAlphabetic(5));
               }

               if (this.actions.is("Качание рукой")) {
                  var10000 = mc;
                  Minecraft.player.swingArm(Hand.MAIN_HAND);
               }

               this.timer.reset();
            }

         }
      }
   }
}
