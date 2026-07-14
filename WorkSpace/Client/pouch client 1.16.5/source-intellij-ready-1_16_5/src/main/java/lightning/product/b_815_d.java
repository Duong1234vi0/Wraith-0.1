/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.sound.sampled.AudioFormat;
import lightning.product.AudioStream;

public class b_815_d
implements AudioStream {
    private final n_1700_B n_1700_B;
    private AudioStream J_1907_R;
    private final BufferedInputStream R_4764_Y;

    public b_815_d(n_1700_B wrapperFactoryOGG, InputStream inputStream) throws IOException {
        this.n_1700_B = wrapperFactoryOGG;
        this.R_4764_Y = new BufferedInputStream(inputStream);
        this.R_4764_Y.mark(Integer.MAX_VALUE);
        this.J_1907_R = wrapperFactoryOGG.create(new J_1907_R(this.R_4764_Y));
    }

    @Override
    public AudioFormat n_1700_B() {
        return this.J_1907_R.n_1700_B();
    }

    @Override
    public ByteBuffer n_1700_B(int size) throws IOException {
        ByteBuffer bytebuffer = this.J_1907_R.n_1700_B(size);
        if (!bytebuffer.hasRemaining()) {
            this.J_1907_R.close();
            this.R_4764_Y.reset();
            this.J_1907_R = this.n_1700_B.create(new J_1907_R(this.R_4764_Y));
            bytebuffer = this.J_1907_R.n_1700_B(size);
        }
        return bytebuffer;
    }

    @Override
    public void close() throws IOException {
        this.J_1907_R.close();
        this.R_4764_Y.close();
    }

    @FunctionalInterface
    public static interface n_1700_B {
        public AudioStream create(InputStream var1) throws IOException;
    }

    static class J_1907_R
    extends FilterInputStream {
        private J_1907_R(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public void close() {
        }
    }
}


