/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.lwjgl.openal.AL10
 */
package lightning.product;

import java.nio.ByteBuffer;
import java.util.OptionalInt;
import javax.annotation.Nullable;
import javax.sound.sampled.AudioFormat;
import lightning.product.N_3519_E;
import org.lwjgl.openal.AL10;

public class m_4536_S {
    @Nullable
    private ByteBuffer n_1700_B;
    private final AudioFormat J_1907_R;
    private boolean R_4764_Y;
    private int G_564_y;

    public m_4536_S(ByteBuffer buffer, AudioFormat format) {
        this.n_1700_B = buffer;
        this.J_1907_R = format;
    }

    OptionalInt n_1700_B() {
        if (!this.R_4764_Y) {
            if (this.n_1700_B == null) {
                return OptionalInt.empty();
            }
            int i = N_3519_E.n_1700_B(this.J_1907_R);
            int[] aint = new int[1];
            AL10.alGenBuffers((int[])aint);
            if (N_3519_E.n_1700_B("Creating buffer")) {
                return OptionalInt.empty();
            }
            AL10.alBufferData((int)aint[0], (int)i, (ByteBuffer)this.n_1700_B, (int)((int)this.J_1907_R.getSampleRate()));
            if (N_3519_E.n_1700_B("Assigning buffer data")) {
                return OptionalInt.empty();
            }
            this.G_564_y = aint[0];
            this.R_4764_Y = true;
            this.n_1700_B = null;
        }
        return OptionalInt.of(this.G_564_y);
    }

    public void J_1907_R() {
        if (this.R_4764_Y) {
            AL10.alDeleteBuffers((int[])new int[]{this.G_564_y});
            if (N_3519_E.n_1700_B("Deleting stream buffers")) {
                return;
            }
        }
        this.R_4764_Y = false;
    }

    public OptionalInt R_4764_Y() {
        OptionalInt optionalint = this.n_1700_B();
        this.R_4764_Y = false;
        return optionalint;
    }
}

