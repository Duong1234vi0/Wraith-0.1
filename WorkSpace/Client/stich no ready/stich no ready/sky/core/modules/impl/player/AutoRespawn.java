package sky.core.modules.impl.player;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import sky.core.SkyCore;
import sky.core.events.EventDeath;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.impl.combat.AttackAura;

public class AutoRespawn extends Module {
   private final BooleanSetting disableAura = new BooleanSetting("Отключать AttackAura", true);

   public AutoRespawn() {
      super("AutoRespawn", "Автоматически возраждает вас после смерти", Category.Player);
      this.addSettings(new Setting[]{this.disableAura});
   }

   @EventTarget
   @Native
   public void onUpdate(EventDeath event) {
      if ((Boolean)this.disableAura.get() && SkyCore.getInstance().getModuleManager().getModule(AttackAura.class).isEnabled()) {
         SkyCore.getInstance().getModuleManager().getModule(AttackAura.class).toggle();
      }

      Minecraft var10000 = mc;
      Minecraft.player.respawnPlayer();
      mc.displayGuiScreen((Screen)null);
   }
}
