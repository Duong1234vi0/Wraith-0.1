/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.sound.sampled.AudioFormat;

public interface AudioStream
extends Closeable {
    public AudioFormat n_1700_B();

    public ByteBuffer n_1700_B(int var1) throws IOException;
}


