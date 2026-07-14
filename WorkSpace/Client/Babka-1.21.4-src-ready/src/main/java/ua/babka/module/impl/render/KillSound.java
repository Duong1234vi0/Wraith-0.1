package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import ua.babka.event.EventAttack;
import ua.babka.event.EventPacket;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.SliderSetting;

@ModuleInformation(moduleName="KillSound", moduleDesc="Звук при убийстве", moduleCategory=ModuleCategory.RENDER)
public class KillSound
extends Module {
    private final SliderSetting volume = new SliderSetting("Громкость", 100.0, 1.0, 300.0, 1.0);
    private LivingEntity target;
    private long time;
    private Clip currentClip;
    private final Random random = new Random();

    @Subscribe
    private void onAttack(EventAttack e2) {
        Entity entity = e2.getEntity();
        if (entity instanceof PlayerEntity) {
            PlayerEntity living = (PlayerEntity)entity;
            this.target = living;
            this.time = System.currentTimeMillis();
        }
    }

    @Subscribe
    private void onPacket(EventPacket e2) {
        EntityStatusS2CPacket packet;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        Packet<?> packet2 = e2.getPacket();
        if (packet2 instanceof EntityStatusS2CPacket && (packet = (EntityStatusS2CPacket)packet2).getStatus() == 3 && this.target != null && packet.getEntity((World)this.mc.world) == this.target && System.currentTimeMillis() - this.time <= 1000L) {
            this.onKill();
            this.target = null;
        }
    }

    @Subscribe
    private void onTick(EventTick e2) {
        if (this.target != null && (this.target.getHealth() <= 0.0f || !this.target.isAlive())) {
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
        this.playSound("killsound/" + soundFiles[index]);
    }

    private void playSound(String path) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                Optional opt = this.mc.getResourceManager().getResource(Identifier.of((String)"babka", (String)path));
                if (opt.isEmpty()) {
                    return;
                }
                InputStream is = ((Resource)opt.get()).getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);
                if (audioInputStream == null) {
                    return;
                }
                clip.open(audioInputStream);
                FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                float vol = this.volume.getFloatValue();
                float gain = (float)(20.0 * Math.log10(vol / 100.0f));
                gainControl.setValue(Math.max(-80.0f, Math.min(gain, 6.0f)));
                clip.start();
                this.currentClip = clip;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }).start();
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

