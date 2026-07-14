/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Objects;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import lightning.product.ToggleSounds;
import lightning.product.MinecraftAccess;
import lightning.product.ClientBootstrap;

public class p_863_D
implements MinecraftAccess {
    public static void n_1700_B(String location) {
        if (ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(ToggleSounds.class).w_1484_f() && p_863_D.c_3005_b.Y_259_p.O_508_d != null) {
            try (AudioInputStream in = AudioSystem.getAudioInputStream(new BufferedInputStream(Objects.requireNonNull(p_863_D.class.getResourceAsStream("/assets/minecraft/Pouch/sounds/" + location + ".wav"))));){
                AudioFormat baseFormat = in.getFormat();
                AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
                try (AudioInputStream din = AudioSystem.getAudioInputStream(decodedFormat, in);){
                    Clip clip = AudioSystem.getClip();
                    clip.open(din);
                    p_863_D.n_1700_B(clip, ((Float)ToggleSounds.w_1484_f.J_1907_R()).floatValue() / 100.0f);
                    clip.start();
                }
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }

    private static void n_1700_B(Clip clip, double volume) {
        if (volume < 0.0) {
            volume = 0.0;
        }
        if (volume > 1.0) {
            volume = 1.0;
        }
        FloatControl volumeControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        if (volume == 0.0) {
            volumeControl.setValue(min);
            return;
        }
        float dB = (float)(20.0 * Math.log10(volume));
        if (dB < min) {
            dB = min;
        }
        if (dB > max) {
            dB = max;
        }
        volumeControl.setValue(dB);
    }
}


