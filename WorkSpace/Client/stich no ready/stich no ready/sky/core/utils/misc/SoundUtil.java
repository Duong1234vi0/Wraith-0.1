package sky.core.utils.misc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.FloatControl.Type;
import sky.core.SkyCore;
import sky.core.modules.impl.miscellaneous.ToggleSounds;
import sky.core.utils.Wrapper;

public class SoundUtil implements Wrapper {
   public static void playSound(String location) {
      if (SkyCore.getInstance().getModuleManager().getModule(ToggleSounds.class).isEnabled()) {
         (new Thread(() -> {
            try {
               InputStream resource = SoundUtil.class.getResourceAsStream("/assets/minecraft/SkyCore/sounds/" + location + ".wav");
               if (resource == null) {
                  return;
               }

               AudioInputStream in = AudioSystem.getAudioInputStream(new BufferedInputStream(resource));

               try {
                  AudioFormat baseFormat = in.getFormat();
                  AudioFormat decodedFormat = new AudioFormat(Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
                  AudioInputStream din = AudioSystem.getAudioInputStream(decodedFormat, in);

                  try {
                     Clip clip = AudioSystem.getClip();
                     clip.open(din);
                     setVolume(clip, (double)(Float)ToggleSounds.volume.get() / 100.0);
                     clip.start();
                  } catch (Throwable var10) {
                     if (din != null) {
                        try {
                           din.close();
                        } catch (Throwable var9) {
                           var10.addSuppressed(var9);
                        }
                     }

                     throw var10;
                  }

                  if (din != null) {
                     din.close();
                  }
               } catch (Throwable var11) {
                  if (in != null) {
                     try {
                        in.close();
                     } catch (Throwable var8) {
                        var11.addSuppressed(var8);
                     }
                  }

                  throw var11;
               }

               if (in != null) {
                  in.close();
               }
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException var12) {
               var12.printStackTrace();
            }

         })).start();
      }
   }

   private static void setVolume(Clip clip, double volume) {
      try {
         if (clip.isControlSupported(Type.MASTER_GAIN)) {
            FloatControl volumeControl = (FloatControl)clip.getControl(Type.MASTER_GAIN);
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            volume = Math.max(0.0, Math.min(1.0, volume));
            if (volume == 0.0) {
               volumeControl.setValue(min);
            } else {
               float dB = (float)(Math.log(volume) / Math.log(10.0) * 20.0);
               volumeControl.setValue(Math.max(min, Math.min(max, dB)));
            }
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }
}
