/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.IntArraySet
 *  it.unimi.dsi.fastutil.ints.IntCollection
 *  it.unimi.dsi.fastutil.ints.IntOpenHashSet
 *  it.unimi.dsi.fastutil.ints.IntSet
 *  javax.annotation.Nullable
 *  org.lwjgl.stb.STBTTFontinfo
 *  org.lwjgl.stb.STBTruetype
 *  org.lwjgl.system.MemoryStack
 *  org.lwjgl.system.MemoryUtil
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.stream.IntStream;
import javax.annotation.Nullable;
import lightning.product.e_3495_r;
import lightning.product.i_2518_W;
import lightning.product.s_3940_w;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class j_938_M
implements e_3495_r {
    private final ByteBuffer n_1700_B;
    private final STBTTFontinfo J_1907_R;
    private final float R_4764_Y;
    private final IntSet G_564_y = new IntArraySet();
    private final float P_1922_E;
    private final float u_1723_Y;
    private final float v_4262_N;
    private final float w_1484_f;

    public j_938_M(ByteBuffer p_i230051_1_, STBTTFontinfo p_i230051_2_, float p_i230051_3_, float p_i230051_4_, float p_i230051_5_, float p_i230051_6_, String p_i230051_7_) {
        this.n_1700_B = p_i230051_1_;
        this.J_1907_R = p_i230051_2_;
        this.R_4764_Y = p_i230051_4_;
        p_i230051_7_.codePoints().forEach(arg_0 -> ((IntSet)this.G_564_y).add(arg_0));
        this.P_1922_E = p_i230051_5_ * p_i230051_4_;
        this.u_1723_Y = p_i230051_6_ * p_i230051_4_;
        this.v_4262_N = STBTruetype.stbtt_ScaleForPixelHeight((STBTTFontinfo)p_i230051_2_, (float)(p_i230051_3_ * p_i230051_4_));
        try (MemoryStack memorystack = MemoryStack.stackPush();){
            IntBuffer intbuffer = memorystack.mallocInt(1);
            IntBuffer intbuffer1 = memorystack.mallocInt(1);
            IntBuffer intbuffer2 = memorystack.mallocInt(1);
            STBTruetype.stbtt_GetFontVMetrics((STBTTFontinfo)p_i230051_2_, (IntBuffer)intbuffer, (IntBuffer)intbuffer1, (IntBuffer)intbuffer2);
            this.w_1484_f = (float)intbuffer.get(0) * this.v_4262_N;
        }
    }

    @Nullable
    public n_1700_B J_1907_R(int character) {
        Object lvt_9_1_;
        if (this.G_564_y.contains(character)) {
            return null;
        }
        try (MemoryStack memorystack = MemoryStack.stackPush();){
            IntBuffer intbuffer = memorystack.mallocInt(1);
            IntBuffer intbuffer1 = memorystack.mallocInt(1);
            IntBuffer intbuffer2 = memorystack.mallocInt(1);
            IntBuffer intbuffer3 = memorystack.mallocInt(1);
            int i = STBTruetype.stbtt_FindGlyphIndex((STBTTFontinfo)this.J_1907_R, (int)character);
            if (i != 0) {
                STBTruetype.stbtt_GetGlyphBitmapBoxSubpixel((STBTTFontinfo)this.J_1907_R, (int)i, (float)this.v_4262_N, (float)this.v_4262_N, (float)this.P_1922_E, (float)this.u_1723_Y, (IntBuffer)intbuffer, (IntBuffer)intbuffer1, (IntBuffer)intbuffer2, (IntBuffer)intbuffer3);
                int k = intbuffer2.get(0) - intbuffer.get(0);
                int j = intbuffer3.get(0) - intbuffer1.get(0);
                if (k != 0 && j != 0) {
                    IntBuffer intbuffer5 = memorystack.mallocInt(1);
                    IntBuffer intbuffer4 = memorystack.mallocInt(1);
                    STBTruetype.stbtt_GetGlyphHMetrics((STBTTFontinfo)this.J_1907_R, (int)i, (IntBuffer)intbuffer5, (IntBuffer)intbuffer4);
                    n_1700_B n_1700_B2 = new n_1700_B(intbuffer.get(0), intbuffer2.get(0), -intbuffer1.get(0), -intbuffer3.get(0), (float)intbuffer5.get(0) * this.v_4262_N, (float)intbuffer4.get(0) * this.v_4262_N, i);
                    return n_1700_B2;
                }
                n_1700_B n_1700_B3 = null;
                return n_1700_B3;
            }
            lvt_9_1_ = null;
        }
        return lvt_9_1_;
    }

    @Override
    public void close() {
        this.J_1907_R.free();
        MemoryUtil.memFree((Buffer)this.n_1700_B);
    }

    @Override
    public IntSet n_1700_B() {
        return (IntSet)IntStream.range(0, 65535).filter(p_237505_1_ -> !this.G_564_y.contains(p_237505_1_)).collect(IntOpenHashSet::new, IntCollection::add, IntCollection::addAll);
    }

    @Override
    @Nullable
    public /* synthetic */ s_3940_w n_1700_B(int n) {
        return this.J_1907_R(n);
    }

    class n_1700_B
    implements s_3940_w {
        private final int J_1907_R;
        private final int R_4764_Y;
        private final float G_564_y;
        private final float P_1922_E;
        private final float u_1723_Y;
        private final int v_4262_N;

        private n_1700_B(int p_i49751_2_, int p_i49751_3_, int p_i49751_4_, int p_i49751_5_, float p_i49751_6_, float p_i49751_7_, int p_i49751_8_) {
            this.J_1907_R = p_i49751_3_ - p_i49751_2_;
            this.R_4764_Y = p_i49751_4_ - p_i49751_5_;
            this.u_1723_Y = p_i49751_6_ / j_938_M.this.R_4764_Y;
            this.G_564_y = (p_i49751_7_ + (float)p_i49751_2_ + j_938_M.this.P_1922_E) / j_938_M.this.R_4764_Y;
            this.P_1922_E = (j_938_M.this.w_1484_f - (float)p_i49751_4_ + j_938_M.this.u_1723_Y) / j_938_M.this.R_4764_Y;
            this.v_4262_N = p_i49751_8_;
        }

        @Override
        public int n_1700_B() {
            return this.J_1907_R;
        }

        @Override
        public int J_1907_R() {
            return this.R_4764_Y;
        }

        @Override
        public float R_4764_Y() {
            return j_938_M.this.R_4764_Y;
        }

        @Override
        public float getAdvance() {
            return this.u_1723_Y;
        }

        @Override
        public float P_1922_E() {
            return this.G_564_y;
        }

        @Override
        public float M_588_G() {
            return this.P_1922_E;
        }

        @Override
        public void n_1700_B(int xOffset, int yOffset) {
            i_2518_W nativeimage = new i_2518_W(i_2518_W.n_1700_B.G_564_y, this.J_1907_R, this.R_4764_Y, false);
            nativeimage.n_1700_B(j_938_M.this.J_1907_R, this.v_4262_N, this.J_1907_R, this.R_4764_Y, j_938_M.this.v_4262_N, j_938_M.this.v_4262_N, j_938_M.this.P_1922_E, j_938_M.this.u_1723_Y, 0, 0);
            nativeimage.n_1700_B(0, xOffset, yOffset, 0, 0, this.J_1907_R, this.R_4764_Y, false, true);
        }

        @Override
        public boolean G_564_y() {
            return false;
        }
    }
}

