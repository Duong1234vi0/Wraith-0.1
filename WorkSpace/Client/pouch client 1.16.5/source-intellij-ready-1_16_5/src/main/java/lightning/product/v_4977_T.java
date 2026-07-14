/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.PointerBuffer
 *  org.lwjgl.stb.STBVorbis
 *  org.lwjgl.stb.STBVorbisAlloc
 *  org.lwjgl.stb.STBVorbisInfo
 *  org.lwjgl.system.MemoryStack
 *  org.lwjgl.system.MemoryUtil
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import javax.sound.sampled.AudioFormat;
import lightning.product.AudioStream;
import lightning.product.u_530_F;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.stb.STBVorbis;
import org.lwjgl.stb.STBVorbisAlloc;
import org.lwjgl.stb.STBVorbisInfo;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class v_4977_T
implements AudioStream {
    private long n_1700_B;
    private final AudioFormat J_1907_R;
    private final InputStream R_4764_Y;
    private ByteBuffer G_564_y = MemoryUtil.memAlloc((int)8192);

    public v_4977_T(InputStream oggInputStream) throws IOException {
        this.R_4764_Y = oggInputStream;
        ((Buffer)this.G_564_y).limit(0);
        try (MemoryStack memorystack = MemoryStack.stackPush();){
            IntBuffer intbuffer = memorystack.mallocInt(1);
            IntBuffer intbuffer1 = memorystack.mallocInt(1);
            while (this.n_1700_B == 0L) {
                if (!this.R_4764_Y()) {
                    throw new IOException("Failed to find Ogg header");
                }
                int i = this.G_564_y.position();
                ((Buffer)this.G_564_y).position(0);
                this.n_1700_B = STBVorbis.stb_vorbis_open_pushdata((ByteBuffer)this.G_564_y, (IntBuffer)intbuffer, (IntBuffer)intbuffer1, (STBVorbisAlloc)null);
                ((Buffer)this.G_564_y).position(i);
                int j = intbuffer1.get(0);
                if (j == 1) {
                    this.G_564_y();
                    continue;
                }
                if (j == 0) continue;
                throw new IOException("Failed to read Ogg file " + j);
            }
            ((Buffer)this.G_564_y).position(this.G_564_y.position() + intbuffer.get(0));
            STBVorbisInfo stbvorbisinfo = STBVorbisInfo.mallocStack((MemoryStack)memorystack);
            STBVorbis.stb_vorbis_get_info((long)this.n_1700_B, (STBVorbisInfo)stbvorbisinfo);
            this.J_1907_R = new AudioFormat(stbvorbisinfo.sample_rate(), 16, stbvorbisinfo.channels(), true, false);
        }
    }

    private boolean R_4764_Y() throws IOException {
        int i = this.G_564_y.limit();
        int j = this.G_564_y.capacity() - i;
        if (j == 0) {
            return true;
        }
        byte[] abyte = new byte[j];
        int k = this.R_4764_Y.read(abyte);
        if (k == -1) {
            return false;
        }
        int l = this.G_564_y.position();
        ((Buffer)this.G_564_y).limit(i + k);
        ((Buffer)this.G_564_y).position(i);
        this.G_564_y.put(abyte, 0, k);
        ((Buffer)this.G_564_y).position(l);
        return true;
    }

    private void G_564_y() {
        boolean flag1;
        boolean flag = this.G_564_y.position() == 0;
        boolean bl = flag1 = this.G_564_y.position() == this.G_564_y.limit();
        if (flag1 && !flag) {
            ((Buffer)this.G_564_y).position(0);
            ((Buffer)this.G_564_y).limit(0);
        } else {
            ByteBuffer bytebuffer = MemoryUtil.memAlloc((int)(flag ? 2 * this.G_564_y.capacity() : this.G_564_y.capacity()));
            bytebuffer.put(this.G_564_y);
            MemoryUtil.memFree((Buffer)this.G_564_y);
            ((Buffer)bytebuffer).flip();
            this.G_564_y = bytebuffer;
        }
    }

    private boolean n_1700_B(n_1700_B oggAudioBuffer) throws IOException {
        if (this.n_1700_B == 0L) {
            return false;
        }
        try (MemoryStack memorystack = MemoryStack.stackPush();){
            PointerBuffer pointerbuffer = memorystack.mallocPointer(1);
            IntBuffer intbuffer = memorystack.mallocInt(1);
            IntBuffer intbuffer1 = memorystack.mallocInt(1);
            while (true) {
                int i = STBVorbis.stb_vorbis_decode_frame_pushdata((long)this.n_1700_B, (ByteBuffer)this.G_564_y, (IntBuffer)intbuffer, (PointerBuffer)pointerbuffer, (IntBuffer)intbuffer1);
                ((Buffer)this.G_564_y).position(this.G_564_y.position() + i);
                int j = STBVorbis.stb_vorbis_get_error((long)this.n_1700_B);
                if (j == 1) {
                    this.G_564_y();
                    if (this.R_4764_Y()) continue;
                    boolean bl = false;
                    return bl;
                }
                if (j != 0) {
                    throw new IOException("Failed to read Ogg file " + j);
                }
                int k = intbuffer1.get(0);
                if (k == 0) continue;
                int l = intbuffer.get(0);
                PointerBuffer pointerbuffer1 = pointerbuffer.getPointerBuffer(l);
                if (l != 1) {
                    if (l == 2) {
                        this.n_1700_B(pointerbuffer1.getFloatBuffer(0, k), pointerbuffer1.getFloatBuffer(1, k), oggAudioBuffer);
                        boolean bl = true;
                        return bl;
                    }
                    throw new IllegalStateException("Invalid number of channels: " + l);
                }
                this.n_1700_B(pointerbuffer1.getFloatBuffer(0, k), oggAudioBuffer);
                boolean bl = true;
                return bl;
            }
        }
    }

    private void n_1700_B(FloatBuffer floatBuffer, n_1700_B oggAudioBuffer) {
        while (floatBuffer.hasRemaining()) {
            oggAudioBuffer.n_1700_B(floatBuffer.get());
        }
    }

    private void n_1700_B(FloatBuffer soundChannel1, FloatBuffer soundChannel2, n_1700_B oggAudioBuffer) {
        while (soundChannel1.hasRemaining() && soundChannel2.hasRemaining()) {
            oggAudioBuffer.n_1700_B(soundChannel1.get());
            oggAudioBuffer.n_1700_B(soundChannel2.get());
        }
    }

    @Override
    public void close() throws IOException {
        if (this.n_1700_B != 0L) {
            STBVorbis.stb_vorbis_close((long)this.n_1700_B);
            this.n_1700_B = 0L;
        }
        MemoryUtil.memFree((Buffer)this.G_564_y);
        this.R_4764_Y.close();
    }

    @Override
    public AudioFormat n_1700_B() {
        return this.J_1907_R;
    }

    @Override
    public ByteBuffer n_1700_B(int size) throws IOException {
        n_1700_B oggaudiostream$buffer = new n_1700_B(size + 8192);
        while (this.n_1700_B(oggaudiostream$buffer) && oggaudiostream$buffer.R_4764_Y < size) {
        }
        return oggaudiostream$buffer.n_1700_B();
    }

    public ByteBuffer J_1907_R() throws IOException {
        n_1700_B oggaudiostream$buffer = new n_1700_B(16384);
        while (this.n_1700_B(oggaudiostream$buffer)) {
        }
        return oggaudiostream$buffer.n_1700_B();
    }

    static class n_1700_B {
        private final List<ByteBuffer> n_1700_B = Lists.newArrayList();
        private final int J_1907_R;
        private int R_4764_Y;
        private ByteBuffer G_564_y;

        public n_1700_B(int capacity) {
            this.J_1907_R = capacity + 1 & 0xFFFFFFFE;
            this.J_1907_R();
        }

        private void J_1907_R() {
            this.G_564_y = BufferUtils.createByteBuffer((int)this.J_1907_R);
        }

        public void n_1700_B(float floatValue) {
            if (this.G_564_y.remaining() == 0) {
                ((Buffer)this.G_564_y).flip();
                this.n_1700_B.add(this.G_564_y);
                this.J_1907_R();
            }
            int i = u_530_F.n_1700_B((int)(floatValue * 32767.5f - 0.5f), Short.MIN_VALUE, Short.MAX_VALUE);
            this.G_564_y.putShort((short)i);
            this.R_4764_Y += 2;
        }

        public ByteBuffer n_1700_B() {
            ((Buffer)this.G_564_y).flip();
            if (this.n_1700_B.isEmpty()) {
                return this.G_564_y;
            }
            ByteBuffer bytebuffer = BufferUtils.createByteBuffer((int)this.R_4764_Y);
            this.n_1700_B.forEach(bytebuffer::put);
            bytebuffer.put(this.G_564_y);
            ((Buffer)bytebuffer).flip();
            return bytebuffer;
        }
    }
}


