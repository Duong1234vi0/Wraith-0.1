package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.play.server.SEntityStatusPacket;
import net.minecraft.util.ResourceLocation;
import sky.core.events.EventAttack;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class KillSound extends Module {
   private final SliderSetting volume = new SliderSetting("Volume", 100.0F, 1.0F, 300.0F, 1.0F);
   private LivingEntity target;
   private long time;
   private Clip currentClip;
   private final Random random = new Random();

   public KillSound() {
      super("KillSound", "Plays sounds on kill", Category.Visuals);
      this.addSettings(new Setting[]{this.volume});
   }

   @EventTarget
   public void onAttack(EventAttack e) {
      if (e.getTarget() instanceof LivingEntity) {
         this.target = (LivingEntity)e.getTarget();
         this.time = System.currentTimeMillis();
      }

   }

   @EventTarget
   public void onPacket(EventPacket e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (e.getPacket() instanceof SEntityStatusPacket) {
            SEntityStatusPacket packet = (SEntityStatusPacket)e.getPacket();
            if (packet.getOpCode() == 3 && this.target != null && packet.getEntity(mc.world) == this.target && System.currentTimeMillis() - this.time <= 1000L) {
               this.onKill();
               this.target = null;
            }
         }

      }
   }

   @EventTarget
   public void onUpdate(EventUpdate e) {
      if (this.target != null && (this.target.getHealth() <= 0.0F || !this.target.isAlive())) {
         if (System.currentTimeMillis() - this.time <= 1000L) {
            this.onKill();
         }

         this.target = null;
      }

   }

   private void onKill() {
      this.stopCurrentSound();
      String[] soundFiles = new String[]{"girl_1.wav", "girl_2.wav", "girl_3.wav", "girl_4.wav", "girl_5.wav"};
      int index = this.random.nextInt(soundFiles.length);
      this.playSound("SkyCore/sounds/" + soundFiles[index]);
   }

   private void playSound(String path) {
      (new Thread(() -> {
         try {
            Clip clip = AudioSystem.getClip();
            InputStream is = mc.getResourceManager().getResource(new ResourceLocation(path)).getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);
            if (audioInputStream == null) {
               return;
            }

            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl)clip.getControl(Type.MASTER_GAIN);
            float vol = (Float)this.volume.get();
            float gain = (float)(20.0 * Math.log10((double)(vol / 100.0F)));
            gainControl.setValue(Math.max(-80.0F, Math.min(gain, 6.0F)));
            clip.start();
            this.currentClip = clip;
         } catch (Exception var9) {
         }

      })).start();
   }

   private void stopCurrentSound() {
      if (this.currentClip != null) {
         if (this.currentClip.isRunning()) {
            this.currentClip.stop();
         }

         this.currentClip.close();
         this.currentClip = null;
      }

   }
}
