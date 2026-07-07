package unique.core.features.modules.client;

import meteordevelopment.orbit.EventHandler;
import org.jetbrains.annotations.NotNull;
import unique.core.core.Managers;
import unique.core.events.impl.EventAttack;
import unique.core.events.impl.EventDeath;
import unique.core.features.modules.Module;
import unique.core.features.modules.combat.Aura;
import unique.core.setting.Setting;

public final class SoundFX extends Module {
   public final Setting<Integer> volume = new Setting<>("Volume", 100, 0, 100);
   public final Setting<SoundFX.OnOffSound> onOffSound = new Setting<>("On/Off Sound", SoundFX.OnOffSound.Celestial);
   public final Setting<SoundFX.HitSound> hitSound = new Setting<>("HitSound", SoundFX.HitSound.OFF);
   public final Setting<SoundFX.KillSound> killSound = new Setting<>("KillSound", SoundFX.KillSound.OFF);
   public final Setting<SoundFX.ScrollSound> scrollSound = new Setting<>("ScrollSound", SoundFX.ScrollSound.KeyBoard);

   public SoundFX() {
      super("SoundFX", Module.Category.CLIENT);
   }

   @EventHandler
   public void onAttack(@NotNull EventAttack event) {
      if (!event.isPre()) {
         Managers.SOUND.playHitSound(this.hitSound.getValue());
      }
   }

   @EventHandler
   public void onDeath(EventDeath e) {
      if (Aura.target != null && Aura.target == e.getPlayer() && this.killSound.is(SoundFX.KillSound.Custom)) {
         Managers.SOUND.playSound("kill");
      }
   }

   public static enum HitSound {
      CUSTOM,
      OFF,
      KEYBOARD;
   }

   public static enum KillSound {
      Custom,
      OFF;
   }

   public static enum OnOffSound {
      OFF,
      Newcode,
      Catlavan,
      Celestial,
      Nursultan,
      Custom;
   }

   public static enum ScrollSound {
      Custom,
      OFF,
      KeyBoard;
   }
}
