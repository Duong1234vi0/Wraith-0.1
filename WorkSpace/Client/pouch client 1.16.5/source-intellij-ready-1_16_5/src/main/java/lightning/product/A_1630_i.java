/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.openal.AL10
 */
package lightning.product;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import javax.sound.sampled.AudioFormat;
import lightning.product.N_3519_E;
import lightning.product.AudioStream;
import lightning.product.e_2866_D;
import lightning.product.m_4536_S;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.openal.AL10;

public class A_1630_i {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final int J_1907_R;
    private final AtomicBoolean R_4764_Y = new AtomicBoolean(true);
    private int G_564_y = 16384;
    @Nullable
    private AudioStream P_1922_E;

    @Nullable
    static A_1630_i n_1700_B() {
        int[] aint = new int[1];
        AL10.alGenSources((int[])aint);
        return N_3519_E.n_1700_B("Allocate new source") ? null : new A_1630_i(aint[0]);
    }

    private A_1630_i(int id) {
        this.J_1907_R = id;
    }

    public void J_1907_R() {
        if (this.R_4764_Y.compareAndSet(true, false)) {
            AL10.alSourceStop((int)this.J_1907_R);
            N_3519_E.n_1700_B("Stop");
            if (this.P_1922_E != null) {
                try {
                    this.P_1922_E.close();
                }
                catch (IOException ioexception) {
                    n_1700_B.error("Failed to close audio stream", (Throwable)ioexception);
                }
                this.u_2550_I();
                this.P_1922_E = null;
            }
            AL10.alDeleteSources((int[])new int[]{this.J_1907_R});
            N_3519_E.n_1700_B("Cleanup");
        }
    }

    public void R_4764_Y() {
        AL10.alSourcePlay((int)this.J_1907_R);
    }

    private int s_956_w() {
        return !this.R_4764_Y.get() ? 4116 : AL10.alGetSourcei((int)this.J_1907_R, (int)4112);
    }

    public void G_564_y() {
        if (this.s_956_w() == 4114) {
            AL10.alSourcePause((int)this.J_1907_R);
        }
    }

    public void P_1922_E() {
        if (this.s_956_w() == 4115) {
            AL10.alSourcePlay((int)this.J_1907_R);
        }
    }

    public void u_1723_Y() {
        if (this.R_4764_Y.get()) {
            AL10.alSourceStop((int)this.J_1907_R);
            N_3519_E.n_1700_B("Stop");
        }
    }

    public boolean v_4262_N() {
        return this.s_956_w() == 4116;
    }

    public void n_1700_B(e_2866_D source) {
        AL10.alSourcefv((int)this.J_1907_R, (int)4100, (float[])new float[]{(float)source.J_1907_R, (float)source.R_4764_Y, (float)source.G_564_y});
    }

    public void n_1700_B(float pitch) {
        AL10.alSourcef((int)this.J_1907_R, (int)4099, (float)pitch);
    }

    public void n_1700_B(boolean looping) {
        AL10.alSourcei((int)this.J_1907_R, (int)4103, (int)(looping ? 1 : 0));
    }

    public void J_1907_R(float volume) {
        AL10.alSourcef((int)this.J_1907_R, (int)4106, (float)volume);
    }

    public void w_1484_f() {
        AL10.alSourcei((int)this.J_1907_R, (int)53248, (int)0);
    }

    public void R_4764_Y(float linearAttenuation) {
        AL10.alSourcei((int)this.J_1907_R, (int)53248, (int)53251);
        AL10.alSourcef((int)this.J_1907_R, (int)4131, (float)linearAttenuation);
        AL10.alSourcef((int)this.J_1907_R, (int)4129, (float)1.0f);
        AL10.alSourcef((int)this.J_1907_R, (int)4128, (float)0.0f);
    }

    public void J_1907_R(boolean relative) {
        AL10.alSourcei((int)this.J_1907_R, (int)514, (int)(relative ? 1 : 0));
    }

    public void n_1700_B(m_4536_S buffer) {
        buffer.n_1700_B().ifPresent(bufferID -> AL10.alSourcei((int)this.J_1907_R, (int)4105, (int)bufferID));
    }

    public void n_1700_B(AudioStream audioStream) {
        this.P_1922_E = audioStream;
        AudioFormat audioformat = audioStream.n_1700_B();
        this.G_564_y = A_1630_i.n_1700_B(audioformat, 1);
        this.n_1700_B(4);
    }

    private static int n_1700_B(AudioFormat audioFormat, int sampleAmount) {
        return (int)((float)(sampleAmount * audioFormat.getSampleSizeInBits()) / 8.0f * (float)audioFormat.getChannels() * audioFormat.getSampleRate());
    }

    private void n_1700_B(int readCount) {
        if (this.P_1922_E != null) {
            try {
                for (int i = 0; i < readCount; ++i) {
                    ByteBuffer bytebuffer = this.P_1922_E.n_1700_B(this.G_564_y);
                    if (bytebuffer == null) continue;
                    new m_4536_S(bytebuffer, this.P_1922_E.n_1700_B()).R_4764_Y().ifPresent(bufferID -> AL10.alSourceQueueBuffers((int)this.J_1907_R, (int[])new int[]{bufferID}));
                }
            }
            catch (IOException ioexception) {
                n_1700_B.error("Failed to read from audio stream", (Throwable)ioexception);
            }
        }
    }

    public void t_148_a() {
        if (this.P_1922_E != null) {
            int i = this.u_2550_I();
            this.n_1700_B(i);
        }
    }

    private int u_2550_I() {
        int i = AL10.alGetSourcei((int)this.J_1907_R, (int)4118);
        if (i > 0) {
            int[] aint = new int[i];
            AL10.alSourceUnqueueBuffers((int)this.J_1907_R, (int[])aint);
            N_3519_E.n_1700_B("Unqueue buffers");
            AL10.alDeleteBuffers((int[])aint);
            N_3519_E.n_1700_B("Remove processed buffers");
        }
        return i;
    }
}


