/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.stb.STBIEOFCallback
 *  org.lwjgl.stb.STBIEOFCallbackI
 *  org.lwjgl.stb.STBIIOCallbacks
 *  org.lwjgl.stb.STBIReadCallback
 *  org.lwjgl.stb.STBIReadCallbackI
 *  org.lwjgl.stb.STBISkipCallback
 *  org.lwjgl.stb.STBISkipCallbackI
 *  org.lwjgl.stb.STBImage
 *  org.lwjgl.system.MemoryStack
 *  org.lwjgl.system.MemoryUtil
 */
package lightning.product;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import org.lwjgl.stb.STBIEOFCallback;
import org.lwjgl.stb.STBIEOFCallbackI;
import org.lwjgl.stb.STBIIOCallbacks;
import org.lwjgl.stb.STBIReadCallback;
import org.lwjgl.stb.STBIReadCallbackI;
import org.lwjgl.stb.STBISkipCallback;
import org.lwjgl.stb.STBISkipCallbackI;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class g_1477_d {
    public final int n_1700_B;
    public final int J_1907_R;
    private static final Object R_4764_Y = new Object();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public g_1477_d(String p_i51172_1_, InputStream p_i51172_2_) throws IOException {
        Object object = R_4764_Y;
        synchronized (object) {
            try (MemoryStack memorystack = MemoryStack.stackPush();
                 n_1700_B pngsizeinfo$reader = g_1477_d.n_1700_B(p_i51172_2_);
                 STBIReadCallback stbireadcallback = STBIReadCallback.create(pngsizeinfo$reader::n_1700_B);
                 STBISkipCallback stbiskipcallback = STBISkipCallback.create(pngsizeinfo$reader::n_1700_B);
                 STBIEOFCallback stbieofcallback = STBIEOFCallback.create(pngsizeinfo$reader::n_1700_B);){
                STBIIOCallbacks stbiiocallbacks = STBIIOCallbacks.mallocStack((MemoryStack)memorystack);
                stbiiocallbacks.read((STBIReadCallbackI)stbireadcallback);
                stbiiocallbacks.skip((STBISkipCallbackI)stbiskipcallback);
                stbiiocallbacks.eof((STBIEOFCallbackI)stbieofcallback);
                IntBuffer intbuffer = memorystack.mallocInt(1);
                IntBuffer intbuffer1 = memorystack.mallocInt(1);
                IntBuffer intbuffer2 = memorystack.mallocInt(1);
                if (!STBImage.stbi_info_from_callbacks((STBIIOCallbacks)stbiiocallbacks, (long)0L, (IntBuffer)intbuffer, (IntBuffer)intbuffer1, (IntBuffer)intbuffer2)) {
                    throw new IOException("Could not read info from the PNG file " + p_i51172_1_ + " " + STBImage.stbi_failure_reason());
                }
                this.n_1700_B = intbuffer.get(0);
                this.J_1907_R = intbuffer1.get(0);
            }
        }
    }

    public String toString() {
        return this.n_1700_B + " x " + this.J_1907_R;
    }

    private static n_1700_B n_1700_B(InputStream p_195695_0_) {
        return p_195695_0_ instanceof FileInputStream ? new R_4764_Y(((FileInputStream)p_195695_0_).getChannel()) : new J_1907_R(Channels.newChannel(p_195695_0_));
    }

    static abstract class n_1700_B
    implements AutoCloseable {
        protected boolean n_1700_B;

        private n_1700_B() {
        }

        int n_1700_B(long p_195682_1_, long p_195682_3_, int p_195682_5_) {
            try {
                return this.J_1907_R(p_195682_3_, p_195682_5_);
            }
            catch (IOException ioexception) {
                this.n_1700_B = true;
                return 0;
            }
        }

        void n_1700_B(long p_195686_1_, int p_195686_3_) {
            try {
                this.n_1700_B(p_195686_3_);
            }
            catch (IOException ioexception) {
                this.n_1700_B = true;
            }
        }

        int n_1700_B(long p_195685_1_) {
            return this.n_1700_B ? 1 : 0;
        }

        protected abstract int J_1907_R(long var1, int var3) throws IOException;

        protected abstract void n_1700_B(int var1) throws IOException;

        @Override
        public abstract void close() throws IOException;
    }

    static class R_4764_Y
    extends n_1700_B {
        private final SeekableByteChannel J_1907_R;

        private R_4764_Y(SeekableByteChannel p_i48134_1_) {
            this.J_1907_R = p_i48134_1_;
        }

        @Override
        public int J_1907_R(long p_195683_1_, int p_195683_3_) throws IOException {
            ByteBuffer bytebuffer = MemoryUtil.memByteBuffer((long)p_195683_1_, (int)p_195683_3_);
            return this.J_1907_R.read(bytebuffer);
        }

        @Override
        public void n_1700_B(int p_195684_1_) throws IOException {
            this.J_1907_R.position(this.J_1907_R.position() + (long)p_195684_1_);
        }

        @Override
        public int n_1700_B(long p_195685_1_) {
            return super.n_1700_B(p_195685_1_) != 0 && this.J_1907_R.isOpen() ? 1 : 0;
        }

        @Override
        public void close() throws IOException {
            this.J_1907_R.close();
        }
    }

    static class J_1907_R
    extends n_1700_B {
        private final ReadableByteChannel J_1907_R;
        private long R_4764_Y = MemoryUtil.nmemAlloc((long)128L);
        private int G_564_y = 128;
        private int P_1922_E;
        private int u_1723_Y;

        private J_1907_R(ReadableByteChannel p_i48136_1_) {
            this.J_1907_R = p_i48136_1_;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void J_1907_R(int p_195688_1_) throws IOException {
            ByteBuffer bytebuffer = MemoryUtil.memByteBuffer((long)this.R_4764_Y, (int)this.G_564_y);
            if (p_195688_1_ + this.u_1723_Y > this.G_564_y) {
                this.G_564_y = p_195688_1_ + this.u_1723_Y;
                bytebuffer = MemoryUtil.memRealloc((ByteBuffer)bytebuffer, (int)this.G_564_y);
                this.R_4764_Y = MemoryUtil.memAddress((ByteBuffer)bytebuffer);
            }
            ((Buffer)bytebuffer).position(this.P_1922_E);
            while (p_195688_1_ + this.u_1723_Y > this.P_1922_E) {
                try {
                    int i = this.J_1907_R.read(bytebuffer);
                    if (i != -1) continue;
                    break;
                }
                finally {
                    this.P_1922_E = bytebuffer.position();
                }
            }
        }

        @Override
        public int J_1907_R(long p_195683_1_, int p_195683_3_) throws IOException {
            this.J_1907_R(p_195683_3_);
            if (p_195683_3_ + this.u_1723_Y > this.P_1922_E) {
                p_195683_3_ = this.P_1922_E - this.u_1723_Y;
            }
            MemoryUtil.memCopy((long)(this.R_4764_Y + (long)this.u_1723_Y), (long)p_195683_1_, (long)p_195683_3_);
            this.u_1723_Y += p_195683_3_;
            return p_195683_3_;
        }

        @Override
        public void n_1700_B(int p_195684_1_) throws IOException {
            if (p_195684_1_ > 0) {
                this.J_1907_R(p_195684_1_);
                if (p_195684_1_ + this.u_1723_Y > this.P_1922_E) {
                    throw new EOFException("Can't skip past the EOF.");
                }
            }
            if (this.u_1723_Y + p_195684_1_ < 0) {
                throw new IOException("Can't seek before the beginning: " + (this.u_1723_Y + p_195684_1_));
            }
            this.u_1723_Y += p_195684_1_;
        }

        @Override
        public void close() throws IOException {
            MemoryUtil.nmemFree((long)this.R_4764_Y);
            this.J_1907_R.close();
        }
    }
}

