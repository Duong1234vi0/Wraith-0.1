package unique.core.core.manager.client;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_7923;
import unique.core.core.manager.IManager;
import unique.core.features.cmd.Command;
import unique.core.features.modules.client.ClientSettings;
import unique.core.features.modules.client.SoundFX;
import unique.core.utility.Timer;

public class SoundManager implements IManager {
   public final class_2960 KEYPRESS_SOUND = class_2960.method_60654("unique:keypress");
   public final class_2960 KEYRELEASE_SOUND = class_2960.method_60654("unique:keyrelease");
   public final class_2960 ENABLE_SOUND = class_2960.method_60654("unique:enable");
   public final class_2960 DISABLE_SOUND = class_2960.method_60654("unique:disable");
   public final class_2960 NURSULTAN_ON_SOUND = class_2960.method_60654("unique:nursultan_on");
   public final class_2960 NURSULTAN_OFF_SOUND = class_2960.method_60654("unique:nursultan_off");
   public final class_2960 NEWCODE_ON_SOUND = class_2960.method_60654("unique:newcode_on");
   public final class_2960 NEWCODE_OFF_SOUND = class_2960.method_60654("unique:newcode_off");
   public final class_2960 CATLAVAN_ON_SOUND = class_2960.method_60654("unique:catlavan_on");
   public final class_2960 CATLAVAN_OFF_SOUND = class_2960.method_60654("unique:catlavan_off");
   public final class_2960 CELESTIAL_ON_SOUND = class_2960.method_60654("unique:celestial_on");
   public final class_2960 CELESTIAL_OFF_SOUND = class_2960.method_60654("unique:celestial_off");
   public final class_2960 ORTHODOX_SOUND = class_2960.method_60654("unique:orthodox");
   public final class_2960 BOOLEAN_SOUND = class_2960.method_60654("unique:boolean");
   public final class_2960 SCROLL_SOUND = class_2960.method_60654("unique:scroll");
   public final class_2960 SWIPEIN_SOUND = class_2960.method_60654("unique:swipein");
   public final class_2960 SWIPEOUT_SOUND = class_2960.method_60654("unique:swipeout");
   public final class_2960 ALERT_SOUND = class_2960.method_60654("unique:alert");
   public final class_2960 PM_SOUND = class_2960.method_60654("unique:pmsound");
   public final class_3414 KEYPRESS_SOUNDEVENT = class_3414.method_47908(this.KEYPRESS_SOUND);
   public final class_3414 KEYRELEASE_SOUNDEVENT = class_3414.method_47908(this.KEYRELEASE_SOUND);
   public final class_3414 ENABLE_SOUNDEVENT = class_3414.method_47908(this.ENABLE_SOUND);
   public final class_3414 DISABLE_SOUNDEVENT = class_3414.method_47908(this.DISABLE_SOUND);
   public final class_3414 NURSULTAN_ON_SOUNDEVENT = class_3414.method_47908(this.NURSULTAN_ON_SOUND);
   public final class_3414 NURSULTAN_OFF_SOUNDEVENT = class_3414.method_47908(this.NURSULTAN_OFF_SOUND);
   public final class_3414 NEWCODE_ON_SOUNDEVENT = class_3414.method_47908(this.NEWCODE_ON_SOUND);
   public final class_3414 NEWCODE_OFF_SOUNDEVENT = class_3414.method_47908(this.NEWCODE_OFF_SOUND);
   public final class_3414 CATLAVAN_ON_SOUNDEVENT = class_3414.method_47908(this.CATLAVAN_ON_SOUND);
   public final class_3414 CATLAVAN_OFF_SOUNDEVENT = class_3414.method_47908(this.CATLAVAN_OFF_SOUND);
   public final class_3414 CELESTIAL_ON_SOUNDEVENT = class_3414.method_47908(this.CELESTIAL_ON_SOUND);
   public final class_3414 CELESTIAL_OFF_SOUNDEVENT = class_3414.method_47908(this.CELESTIAL_OFF_SOUND);
   public final class_3414 ORTHODOX_SOUNDEVENT = class_3414.method_47908(this.ORTHODOX_SOUND);
   public final class_3414 BOOLEAN_SOUNDEVENT = class_3414.method_47908(this.BOOLEAN_SOUND);
   public final class_3414 SCROLL_SOUNDEVENT = class_3414.method_47908(this.SCROLL_SOUND);
   public final class_3414 SWIPEIN_SOUNDEVENT = class_3414.method_47908(this.SWIPEIN_SOUND);
   public final class_3414 SWIPEOUT_SOUNDEVENT = class_3414.method_47908(this.SWIPEOUT_SOUND);
   public final class_3414 ALERT_SOUNDEVENT = class_3414.method_47908(this.ALERT_SOUND);
   public final class_3414 PM_SOUNDEVENT = class_3414.method_47908(this.PM_SOUND);
   private final Timer scrollTimer = new Timer();

   public void registerSounds() {
      class_2378.method_10230(class_7923.field_41172, this.KEYPRESS_SOUND, this.KEYPRESS_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.KEYRELEASE_SOUND, this.KEYRELEASE_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.ENABLE_SOUND, this.ENABLE_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.DISABLE_SOUND, this.DISABLE_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.NURSULTAN_ON_SOUND, this.NURSULTAN_ON_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.NURSULTAN_OFF_SOUND, this.NURSULTAN_OFF_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.NEWCODE_ON_SOUND, this.NEWCODE_ON_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.NEWCODE_OFF_SOUND, this.NEWCODE_OFF_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.CATLAVAN_ON_SOUND, this.CATLAVAN_ON_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.CATLAVAN_OFF_SOUND, this.CATLAVAN_OFF_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.CELESTIAL_ON_SOUND, this.CELESTIAL_ON_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.CELESTIAL_OFF_SOUND, this.CELESTIAL_OFF_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.ORTHODOX_SOUND, this.ORTHODOX_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.BOOLEAN_SOUND, this.BOOLEAN_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.SCROLL_SOUND, this.SCROLL_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.SWIPEIN_SOUND, this.SWIPEIN_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.SWIPEOUT_SOUND, this.SWIPEOUT_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.ALERT_SOUND, this.ALERT_SOUNDEVENT);
      class_2378.method_10230(class_7923.field_41172, this.PM_SOUND, this.PM_SOUNDEVENT);
   }

   public void playHitSound(SoundFX.HitSound value) {
      switch (value) {
         case CUSTOM:
            this.playSound("hit");
            break;
         case KEYBOARD:
            this.playSound(this.KEYPRESS_SOUNDEVENT);
      }
   }

   public void playEnable() {
      switch ((SoundFX.OnOffSound)ModuleManager.soundFX.onOffSound.getValue()) {
         case Newcode:
            this.playSound(this.NEWCODE_ON_SOUNDEVENT);
            break;
         case Catlavan:
            this.playSound(this.CATLAVAN_ON_SOUNDEVENT);
            break;
         case Celestial:
            this.playSound(this.CELESTIAL_ON_SOUNDEVENT);
            break;
         case Nursultan:
            this.playSound(this.NURSULTAN_ON_SOUNDEVENT);
            break;
         case Custom:
            this.playSound("enable");
      }
   }

   public void playDisable() {
      switch ((SoundFX.OnOffSound)ModuleManager.soundFX.onOffSound.getValue()) {
         case Newcode:
            this.playSound(this.NEWCODE_OFF_SOUNDEVENT);
            break;
         case Catlavan:
            this.playSound(this.CATLAVAN_OFF_SOUNDEVENT);
            break;
         case Celestial:
            this.playSound(this.CELESTIAL_OFF_SOUNDEVENT);
            break;
         case Nursultan:
            this.playSound(this.NURSULTAN_OFF_SOUNDEVENT);
            break;
         case Custom:
            this.playSound("disable");
      }
   }

   public void playScroll() {
      if (this.scrollTimer.every(50L)) {
         if (ModuleManager.soundFX.scrollSound.getValue() == SoundFX.ScrollSound.KeyBoard) {
            this.playSound(this.KEYPRESS_SOUNDEVENT);
         } else if (ModuleManager.soundFX.scrollSound.getValue() == SoundFX.ScrollSound.Custom) {
            this.playSound("scroll");
         }
      }
   }

   public void playSound(class_3414 sound) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         mc.field_1687
            .method_8396(
               mc.field_1724, mc.field_1724.method_24515(), sound, class_3419.field_15245, ModuleManager.soundFX.volume.getValue().intValue() / 100.0F, 1.0F
            );
      }
   }

   public void playSound(String name) {
      try {
         File file = new File(ConfigManager.SOUNDS_FOLDER, name.toLowerCase() + ".ogg");
         Clip clip = AudioSystem.getClip();
         clip.open(AudioSystem.getAudioInputStream(file));
         FloatControl control = (FloatControl)clip.getControl(Type.MASTER_GAIN);
         control.setValue(
            control.getMaximum() - (control.getMaximum() - control.getMinimum()) * (1.0F - ModuleManager.soundFX.volume.getValue().intValue() / 100.0F)
         );
         clip.start();
      } catch (Exception var5) {
         Command.sendMessage(
            (ClientSettings.isRu() ? "Ошибка воспроизведения звука! Проверь " : "Error With Playing Sound! Check ")
               + new File(ConfigManager.SOUNDS_FOLDER, name.toLowerCase() + ".ogg").getAbsolutePath()
         );
      }
   }

   public void playSlider() {
      this.playSound(this.SCROLL_SOUNDEVENT);
   }

   public void playBoolean() {
      this.playSound(this.KEYRELEASE_SOUNDEVENT);
   }

   public void playSwipeIn() {
      this.playSound(this.SWIPEIN_SOUNDEVENT);
   }

   public void playSwipeOut() {
      this.playSound(this.SWIPEOUT_SOUNDEVENT);
   }

   public void playPmSound() {
      this.playSound(this.PM_SOUNDEVENT);
   }
}
