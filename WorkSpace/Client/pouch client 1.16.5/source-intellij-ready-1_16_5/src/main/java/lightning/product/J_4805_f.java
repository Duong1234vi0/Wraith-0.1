/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.openal.AL
 *  org.lwjgl.openal.AL10
 *  org.lwjgl.openal.ALC
 *  org.lwjgl.openal.ALC10
 *  org.lwjgl.openal.ALCCapabilities
 *  org.lwjgl.openal.ALCapabilities
 *  org.lwjgl.system.MemoryStack
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.A_1630_i;
import lightning.product.N_3519_E;
import lightning.product.o_3492_Q;
import lightning.product.u_530_F;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;
import org.lwjgl.system.MemoryStack;

public class J_4805_f {
    private static final Logger n_1700_B = LogManager.getLogger();
    private long J_1907_R;
    private long R_4764_Y;
    private static final J_1907_R G_564_y = new J_1907_R(){

        @Override
        @Nullable
        public A_1630_i n_1700_B() {
            return null;
        }

        @Override
        public boolean n_1700_B(A_1630_i source) {
            return false;
        }

        @Override
        public void J_1907_R() {
        }

        @Override
        public int R_4764_Y() {
            return 0;
        }

        @Override
        public int G_564_y() {
            return 0;
        }
    };
    private J_1907_R P_1922_E = G_564_y;
    private J_1907_R u_1723_Y = G_564_y;
    private final o_3492_Q v_4262_N = new o_3492_Q();

    public void n_1700_B() {
        this.J_1907_R = J_4805_f.u_1723_Y();
        ALCCapabilities alccapabilities = ALC.createCapabilities((long)this.J_1907_R);
        if (N_3519_E.n_1700_B(this.J_1907_R, "Get capabilities")) {
            throw new IllegalStateException("Failed to get OpenAL capabilities");
        }
        if (!alccapabilities.OpenALC11) {
            throw new IllegalStateException("OpenAL 1.1 not supported");
        }
        this.R_4764_Y = ALC10.alcCreateContext((long)this.J_1907_R, (IntBuffer)null);
        ALC10.alcMakeContextCurrent((long)this.R_4764_Y);
        int i = this.P_1922_E();
        int j = u_530_F.n_1700_B((int)u_530_F.R_4764_Y((float)i), 2, 8);
        int k = u_530_F.n_1700_B(i - j, 8, 255);
        this.P_1922_E = new n_1700_B(k);
        this.u_1723_Y = new n_1700_B(j);
        ALCapabilities alcapabilities = AL.createCapabilities((ALCCapabilities)alccapabilities);
        N_3519_E.n_1700_B("Initialization");
        if (!alcapabilities.AL_EXT_source_distance_model) {
            throw new IllegalStateException("AL_EXT_source_distance_model is not supported");
        }
        AL10.alEnable((int)512);
        if (!alcapabilities.AL_EXT_LINEAR_DISTANCE) {
            throw new IllegalStateException("AL_EXT_LINEAR_DISTANCE is not supported");
        }
        N_3519_E.n_1700_B("Enable per-source distance models");
        n_1700_B.info("OpenAL initialized.");
    }

    private int P_1922_E() {
        int i1;
        try (MemoryStack memorystack = MemoryStack.stackPush();){
            int l;
            int k;
            int i = ALC10.alcGetInteger((long)this.J_1907_R, (int)4098);
            if (N_3519_E.n_1700_B(this.J_1907_R, "Get attributes size")) {
                throw new IllegalStateException("Failed to get OpenAL attributes");
            }
            IntBuffer intbuffer = memorystack.mallocInt(i);
            ALC10.alcGetIntegerv((long)this.J_1907_R, (int)4099, (IntBuffer)intbuffer);
            if (N_3519_E.n_1700_B(this.J_1907_R, "Get attributes")) {
                throw new IllegalStateException("Failed to get OpenAL attributes");
            }
            int j = 0;
            do {
                if (j >= i) {
                    int n = 30;
                    return n;
                }
                if ((k = intbuffer.get(j++)) == 0) {
                    int n = 30;
                    return n;
                }
                l = intbuffer.get(j++);
            } while (k != 4112);
            i1 = l;
        }
        return i1;
    }

    private static long u_1723_Y() {
        for (int i = 0; i < 3; ++i) {
            long j = ALC10.alcOpenDevice((ByteBuffer)null);
            if (j == 0L || N_3519_E.n_1700_B(j, "Open device")) continue;
            return j;
        }
        throw new IllegalStateException("Failed to open OpenAL device");
    }

    public void J_1907_R() {
        this.P_1922_E.J_1907_R();
        this.u_1723_Y.J_1907_R();
        ALC10.alcDestroyContext((long)this.R_4764_Y);
        if (this.J_1907_R != 0L) {
            ALC10.alcCloseDevice((long)this.J_1907_R);
        }
    }

    public o_3492_Q R_4764_Y() {
        return this.v_4262_N;
    }

    @Nullable
    public A_1630_i n_1700_B(R_4764_Y soundMode) {
        return (soundMode == lightning.product.J_4805_f$R_4764_Y.J_1907_R ? this.u_1723_Y : this.P_1922_E).n_1700_B();
    }

    public void n_1700_B(A_1630_i source) {
        if (!this.P_1922_E.n_1700_B(source) && !this.u_1723_Y.n_1700_B(source)) {
            throw new IllegalStateException("Tried to release unknown channel");
        }
    }

    public String G_564_y() {
        return String.format("Sounds: %d/%d + %d/%d", this.P_1922_E.G_564_y(), this.P_1922_E.R_4764_Y(), this.u_1723_Y.G_564_y(), this.u_1723_Y.R_4764_Y());
    }

    static interface J_1907_R {
        @Nullable
        public A_1630_i n_1700_B();

        public boolean n_1700_B(A_1630_i var1);

        public void J_1907_R();

        public int R_4764_Y();

        public int G_564_y();
    }

    static class n_1700_B
    implements J_1907_R {
        private final int n_1700_B;
        private final Set<A_1630_i> J_1907_R = Sets.newIdentityHashSet();

        public n_1700_B(int maxSoundSources) {
            this.n_1700_B = maxSoundSources;
        }

        @Override
        @Nullable
        public A_1630_i n_1700_B() {
            if (this.J_1907_R.size() >= this.n_1700_B) {
                n_1700_B.warn("Maximum sound pool size {} reached", (Object)this.n_1700_B);
                return null;
            }
            A_1630_i soundsource = A_1630_i.n_1700_B();
            if (soundsource != null) {
                this.J_1907_R.add(soundsource);
            }
            return soundsource;
        }

        @Override
        public boolean n_1700_B(A_1630_i source) {
            if (!this.J_1907_R.remove(source)) {
                return false;
            }
            source.J_1907_R();
            return true;
        }

        @Override
        public void J_1907_R() {
            this.J_1907_R.forEach(A_1630_i::J_1907_R);
            this.J_1907_R.clear();
        }

        @Override
        public int R_4764_Y() {
            return this.n_1700_B;
        }

        @Override
        public int G_564_y() {
            return this.J_1907_R.size();
        }
    }

    public static final class R_4764_Y
    extends Enum<R_4764_Y> {
        public static final /* enum */ R_4764_Y n_1700_B = new R_4764_Y();
        public static final /* enum */ R_4764_Y J_1907_R = new R_4764_Y();
        private static final /* synthetic */ R_4764_Y[] R_4764_Y;

        public static R_4764_Y[] values() {
            return (R_4764_Y[])R_4764_Y.clone();
        }

        public static R_4764_Y valueOf(String name) {
            return Enum.valueOf(R_4764_Y.class, name);
        }

        private static /* synthetic */ R_4764_Y[] n_1700_B() {
            return new R_4764_Y[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.J_4805_f$R_4764_Y.n_1700_B();
        }
    }
}

