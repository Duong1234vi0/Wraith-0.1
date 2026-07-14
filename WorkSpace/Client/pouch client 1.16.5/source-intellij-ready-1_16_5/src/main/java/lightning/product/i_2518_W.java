/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  javax.annotation.Nullable
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.stb.STBIWriteCallback
 *  org.lwjgl.stb.STBImage
 *  org.lwjgl.stb.STBImageResize
 *  org.lwjgl.stb.STBImageWrite
 *  org.lwjgl.stb.STBTTFontinfo
 *  org.lwjgl.stb.STBTruetype
 *  org.lwjgl.system.MemoryStack
 *  org.lwjgl.system.MemoryUtil
 */
package lightning.product;

import com.google.common.base.Charsets;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Base64;
import java.util.EnumSet;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.N_1972_P;
import lightning.product.X_933_l;
import lightning.product.c_3314_E;
import lightning.product.c_4037_x;
import net.optifine.Config;
import net.optifine.util.NativeMemory;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.stb.STBIWriteCallback;
import org.lwjgl.stb.STBImage;
import org.lwjgl.stb.STBImageResize;
import org.lwjgl.stb.STBImageWrite;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public final class i_2518_W
implements AutoCloseable {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Set<StandardOpenOption> J_1907_R = EnumSet.of(StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    private final n_1700_B R_4764_Y;
    private final int G_564_y;
    private final int P_1922_E;
    private final boolean u_1723_Y;
    private long v_4262_N;
    private final long w_1484_f;

    public i_2518_W(int widthIn, int heightIn, boolean clear) {
        this(lightning.product.i_2518_W$n_1700_B.n_1700_B, widthIn, heightIn, clear);
    }

    public i_2518_W(n_1700_B pixelFormatIn, int widthIn, int heightIn, boolean initialize) {
        this.R_4764_Y = pixelFormatIn;
        this.G_564_y = widthIn;
        this.P_1922_E = heightIn;
        this.w_1484_f = (long)widthIn * (long)heightIn * (long)pixelFormatIn.n_1700_B();
        this.u_1723_Y = false;
        this.v_4262_N = initialize ? MemoryUtil.nmemCalloc((long)1L, (long)this.w_1484_f) : MemoryUtil.nmemAlloc((long)this.w_1484_f);
        this.s_956_w();
        NativeMemory.imageAllocated(this);
    }

    private i_2518_W(n_1700_B pixelFormatIn, int widthIn, int heightIn, boolean stbiPointerIn, long pointer) {
        this.R_4764_Y = pixelFormatIn;
        this.G_564_y = widthIn;
        this.P_1922_E = heightIn;
        this.u_1723_Y = stbiPointerIn;
        this.v_4262_N = pointer;
        this.w_1484_f = widthIn * heightIn * pixelFormatIn.n_1700_B();
    }

    public String toString() {
        return "NativeImage[" + String.valueOf((Object)this.R_4764_Y) + " " + this.G_564_y + "x" + this.P_1922_E + "@" + this.v_4262_N + (this.u_1723_Y ? "S" : "N") + "]";
    }

    public static i_2518_W n_1700_B(InputStream inputStreamIn) throws IOException {
        return i_2518_W.n_1700_B(lightning.product.i_2518_W$n_1700_B.n_1700_B, inputStreamIn);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static i_2518_W n_1700_B(@Nullable n_1700_B pixelFormatIn, InputStream inputStreamIn) throws IOException {
        i_2518_W nativeimage;
        ByteBuffer bytebuffer = null;
        try {
            bytebuffer = N_1972_P.n_1700_B(inputStreamIn);
            ((Buffer)bytebuffer).rewind();
            nativeimage = i_2518_W.n_1700_B(pixelFormatIn, bytebuffer);
        }
        finally {
            MemoryUtil.memFree((Buffer)bytebuffer);
            IOUtils.closeQuietly((InputStream)inputStreamIn);
        }
        return nativeimage;
    }

    public static i_2518_W n_1700_B(ByteBuffer byteBufferIn) throws IOException {
        return i_2518_W.n_1700_B(lightning.product.i_2518_W$n_1700_B.n_1700_B, byteBufferIn);
    }

    public static i_2518_W n_1700_B(@Nullable n_1700_B pixelFormatIn, ByteBuffer byteBufferIn) throws IOException {
        i_2518_W nativeimage;
        if (pixelFormatIn != null && !pixelFormatIn.t_148_a()) {
            throw new UnsupportedOperationException("Don't know how to read format " + String.valueOf((Object)pixelFormatIn));
        }
        if (MemoryUtil.memAddress((ByteBuffer)byteBufferIn) == 0L) {
            throw new IllegalArgumentException("Invalid buffer");
        }
        try (MemoryStack memorystack = MemoryStack.stackPush();){
            IntBuffer intbuffer = memorystack.mallocInt(1);
            IntBuffer intbuffer1 = memorystack.mallocInt(1);
            IntBuffer intbuffer2 = memorystack.mallocInt(1);
            ByteBuffer bytebuffer = STBImage.stbi_load_from_memory((ByteBuffer)byteBufferIn, (IntBuffer)intbuffer, (IntBuffer)intbuffer1, (IntBuffer)intbuffer2, (int)(pixelFormatIn == null ? 0 : pixelFormatIn.P_1922_E));
            if (bytebuffer == null) {
                throw new IOException("Could not load image: " + STBImage.stbi_failure_reason());
            }
            nativeimage = new i_2518_W(pixelFormatIn == null ? lightning.product.i_2518_W$n_1700_B.n_1700_B(intbuffer2.get(0)) : pixelFormatIn, intbuffer.get(0), intbuffer1.get(0), true, MemoryUtil.memAddress((ByteBuffer)bytebuffer));
            NativeMemory.imageAllocated(nativeimage);
        }
        return nativeimage;
    }

    public static void n_1700_B(boolean clamp) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        if (clamp) {
            X_933_l.J_1907_R(3553, 10242, 33071);
            X_933_l.J_1907_R(3553, 10243, 33071);
        } else {
            X_933_l.J_1907_R(3553, 10242, 10497);
            X_933_l.J_1907_R(3553, 10243, 10497);
        }
    }

    public static void n_1700_B(boolean linear, boolean mipmap) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        if (linear) {
            X_933_l.J_1907_R(3553, 10241, mipmap ? 9987 : 9729);
            X_933_l.J_1907_R(3553, 10240, 9729);
        } else {
            int i = Config.getMipmapType();
            X_933_l.J_1907_R(3553, 10241, mipmap ? i : 9728);
            X_933_l.J_1907_R(3553, 10240, 9728);
        }
    }

    private void s_956_w() {
        if (this.v_4262_N == 0L) {
            throw new IllegalStateException("Image is not allocated.");
        }
    }

    @Override
    public void close() {
        if (this.v_4262_N != 0L) {
            if (this.u_1723_Y) {
                STBImage.nstbi_image_free((long)this.v_4262_N);
            } else {
                MemoryUtil.nmemFree((long)this.v_4262_N);
            }
            NativeMemory.imageFreed(this);
        }
        this.v_4262_N = 0L;
    }

    public int n_1700_B() {
        return this.G_564_y;
    }

    public int J_1907_R() {
        return this.P_1922_E;
    }

    public n_1700_B R_4764_Y() {
        return this.R_4764_Y;
    }

    public int n_1700_B(int x, int y) {
        if (this.R_4764_Y != lightning.product.i_2518_W$n_1700_B.n_1700_B) {
            throw new IllegalArgumentException(String.format("getPixelRGBA only works on RGBA images; have %s", new Object[]{this.R_4764_Y}));
        }
        if (x >= 0 && y >= 0 && x < this.G_564_y && y < this.P_1922_E) {
            this.s_956_w();
            long i = (x + y * this.G_564_y) * 4;
            return MemoryUtil.memGetInt((long)(this.v_4262_N + i));
        }
        throw new IllegalArgumentException(String.format("(%s, %s) outside of image bounds (%s, %s)", x, y, this.G_564_y, this.P_1922_E));
    }

    public void n_1700_B(int x, int y, int value) {
        if (this.R_4764_Y != lightning.product.i_2518_W$n_1700_B.n_1700_B) {
            throw new IllegalArgumentException(String.format("getPixelRGBA only works on RGBA images; have %s", new Object[]{this.R_4764_Y}));
        }
        if (x < 0 || y < 0 || x >= this.G_564_y || y >= this.P_1922_E) {
            throw new IllegalArgumentException(String.format("(%s, %s) outside of image bounds (%s, %s)", x, y, this.G_564_y, this.P_1922_E));
        }
        this.s_956_w();
        long i = (x + y * this.G_564_y) * 4;
        MemoryUtil.memPutInt((long)(this.v_4262_N + i), (int)value);
    }

    public byte J_1907_R(int x, int y) {
        if (!this.R_4764_Y.v_4262_N()) {
            throw new IllegalArgumentException(String.format("no luminance or alpha in %s", new Object[]{this.R_4764_Y}));
        }
        if (x >= 0 && y >= 0 && x < this.G_564_y && y < this.P_1922_E) {
            int i = (x + y * this.G_564_y) * this.R_4764_Y.n_1700_B() + this.R_4764_Y.w_1484_f() / 8;
            return MemoryUtil.memGetByte((long)(this.v_4262_N + (long)i));
        }
        throw new IllegalArgumentException(String.format("(%s, %s) outside of image bounds (%s, %s)", x, y, this.G_564_y, this.P_1922_E));
    }

    @Deprecated
    public int[] G_564_y() {
        if (this.R_4764_Y != lightning.product.i_2518_W$n_1700_B.n_1700_B) {
            throw new UnsupportedOperationException("can only call makePixelArray for RGBA images.");
        }
        this.s_956_w();
        int[] aint = new int[this.n_1700_B() * this.J_1907_R()];
        for (int i = 0; i < this.J_1907_R(); ++i) {
            for (int j = 0; j < this.n_1700_B(); ++j) {
                int l1;
                int k = this.n_1700_B(j, i);
                int l = i_2518_W.n_1700_B(k);
                int i1 = i_2518_W.G_564_y(k);
                int j1 = i_2518_W.R_4764_Y(k);
                int k1 = i_2518_W.J_1907_R(k);
                aint[j + i * this.n_1700_B()] = l1 = l << 24 | k1 << 16 | j1 << 8 | i1;
            }
        }
        return aint;
    }

    public void n_1700_B(int level, int xOffset, int yOffset, boolean mipmap) {
        this.n_1700_B(level, xOffset, yOffset, 0, 0, this.G_564_y, this.P_1922_E, false, mipmap);
    }

    public void n_1700_B(int level, int xOffset, int yOffset, int unpackSkipPixels, int unpackSkipRows, int widthIn, int heightIn, boolean mipmap, boolean autoClose) {
        this.n_1700_B(level, xOffset, yOffset, unpackSkipPixels, unpackSkipRows, widthIn, heightIn, false, false, mipmap, autoClose);
    }

    public void n_1700_B(int level, int xOffset, int yOffset, int unpackSkipPixels, int unpackSkipRows, int widthIn, int heightIn, boolean blur, boolean clamp, boolean mipmap, boolean autoClose) {
        if (!c_4037_x.R_4764_Y()) {
            c_4037_x.n_1700_B(() -> this.J_1907_R(level, xOffset, yOffset, unpackSkipPixels, unpackSkipRows, widthIn, heightIn, blur, clamp, mipmap, autoClose));
        } else {
            this.J_1907_R(level, xOffset, yOffset, unpackSkipPixels, unpackSkipRows, widthIn, heightIn, blur, clamp, mipmap, autoClose);
        }
    }

    private void J_1907_R(int level, int xOffset, int yOffset, int unpackSkipPixels, int unpackSkipRows, int widthIn, int heightIn, boolean blur, boolean clamp, boolean mipmap, boolean autoClose) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        this.s_956_w();
        i_2518_W.n_1700_B(blur, mipmap);
        i_2518_W.n_1700_B(clamp);
        if (widthIn == this.n_1700_B()) {
            X_933_l.h_1847_R(3314, 0);
        } else {
            X_933_l.h_1847_R(3314, this.n_1700_B());
        }
        X_933_l.h_1847_R(3316, unpackSkipPixels);
        X_933_l.h_1847_R(3315, unpackSkipRows);
        this.R_4764_Y.R_4764_Y();
        X_933_l.n_1700_B(3553, level, xOffset, yOffset, widthIn, heightIn, this.R_4764_Y.G_564_y(), 5121, this.v_4262_N);
        if (autoClose) {
            this.close();
        }
    }

    public void n_1700_B(int level, boolean opaque) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        this.s_956_w();
        this.R_4764_Y.J_1907_R();
        X_933_l.n_1700_B(3553, level, this.R_4764_Y.G_564_y(), 5121, this.v_4262_N);
        if (opaque && this.R_4764_Y.P_1922_E()) {
            for (int i = 0; i < this.J_1907_R(); ++i) {
                for (int j = 0; j < this.n_1700_B(); ++j) {
                    this.n_1700_B(j, i, this.n_1700_B(j, i) | 255 << this.R_4764_Y.u_1723_Y());
                }
            }
        }
    }

    public void n_1700_B(File fileIn) throws IOException {
        this.n_1700_B(fileIn.toPath());
    }

    public void n_1700_B(STBTTFontinfo info, int glyphIndex, int widthIn, int heightIn, float scaleX, float scaleY, float shiftX, float shiftY, int x, int y) {
        if (x >= 0 && x + widthIn <= this.n_1700_B() && y >= 0 && y + heightIn <= this.J_1907_R()) {
            if (this.R_4764_Y.n_1700_B() != 1) {
                throw new IllegalArgumentException("Can only write fonts into 1-component images.");
            }
        } else {
            throw new IllegalArgumentException(String.format("Out of bounds: start: (%s, %s) (size: %sx%s); size: %sx%s", x, y, widthIn, heightIn, this.n_1700_B(), this.J_1907_R()));
        }
        STBTruetype.nstbtt_MakeGlyphBitmapSubpixel((long)info.address(), (long)(this.v_4262_N + (long)x + (long)(y * this.n_1700_B())), (int)widthIn, (int)heightIn, (int)this.n_1700_B(), (float)scaleX, (float)scaleY, (float)shiftX, (float)shiftY, (int)glyphIndex);
    }

    public void n_1700_B(Path pathIn) throws IOException {
        if (!this.R_4764_Y.t_148_a()) {
            throw new UnsupportedOperationException("Don't know how to write format " + String.valueOf((Object)this.R_4764_Y));
        }
        this.s_956_w();
        try (SeekableByteChannel writablebytechannel = Files.newByteChannel(pathIn, J_1907_R, new FileAttribute[0]);){
            if (!this.n_1700_B(writablebytechannel)) {
                throw new IOException("Could not write image to the PNG file \"" + String.valueOf(pathIn.toAbsolutePath()) + "\": " + STBImage.stbi_failure_reason());
            }
        }
    }

    public byte[] P_1922_E() throws IOException {
        byte[] abyte;
        try (ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
             WritableByteChannel writablebytechannel = Channels.newChannel(bytearrayoutputstream);){
            if (!this.n_1700_B(writablebytechannel)) {
                throw new IOException("Could not write image to byte array: " + STBImage.stbi_failure_reason());
            }
            abyte = bytearrayoutputstream.toByteArray();
        }
        return abyte;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean n_1700_B(WritableByteChannel channelIn) throws IOException {
        boolean flag;
        R_4764_Y nativeimage$writecallback = new R_4764_Y(channelIn);
        try {
            int i = Math.min(this.J_1907_R(), Integer.MAX_VALUE / this.n_1700_B() / this.R_4764_Y.n_1700_B());
            if (i < this.J_1907_R()) {
                n_1700_B.warn("Dropping image height from {} to {} to fit the size into 32-bit signed int", (Object)this.J_1907_R(), (Object)i);
            }
            if (STBImageWrite.nstbi_write_png_to_func((long)nativeimage$writecallback.address(), (long)0L, (int)this.n_1700_B(), (int)i, (int)this.R_4764_Y.n_1700_B(), (long)this.v_4262_N, (int)0) == 0) {
                boolean bl = false;
                return bl;
            }
            nativeimage$writecallback.n_1700_B();
            flag = true;
        }
        finally {
            nativeimage$writecallback.free();
        }
        return flag;
    }

    public void n_1700_B(i_2518_W from) {
        if (from.R_4764_Y() != this.R_4764_Y) {
            throw new UnsupportedOperationException("Image formats don't match.");
        }
        int i = this.R_4764_Y.n_1700_B();
        this.s_956_w();
        from.s_956_w();
        if (this.G_564_y == from.G_564_y) {
            MemoryUtil.memCopy((long)from.v_4262_N, (long)this.v_4262_N, (long)Math.min(this.w_1484_f, from.w_1484_f));
        } else {
            int j = Math.min(this.n_1700_B(), from.n_1700_B());
            int k = Math.min(this.J_1907_R(), from.J_1907_R());
            for (int l = 0; l < k; ++l) {
                int i1 = l * from.n_1700_B() * i;
                int j1 = l * this.n_1700_B() * i;
                MemoryUtil.memCopy((long)(from.v_4262_N + (long)i1), (long)(this.v_4262_N + (long)j1), (long)((long)j * (long)i));
            }
        }
    }

    public void n_1700_B(int x, int y, int widthIn, int heightIn, int value) {
        for (int i = y; i < y + heightIn; ++i) {
            for (int j = x; j < x + widthIn; ++j) {
                this.n_1700_B(j, i, value);
            }
        }
    }

    public void n_1700_B(int xFrom, int yFrom, int xToDelta, int yToDelta, int widthIn, int heightIn, boolean mirrorX, boolean mirrorY) {
        for (int i = 0; i < heightIn; ++i) {
            for (int j = 0; j < widthIn; ++j) {
                int k = mirrorX ? widthIn - 1 - j : j;
                int l = mirrorY ? heightIn - 1 - i : i;
                int i1 = this.n_1700_B(xFrom + j, yFrom + i);
                this.n_1700_B(xFrom + xToDelta + k, yFrom + yToDelta + l, i1);
            }
        }
    }

    public void u_1723_Y() {
        this.s_956_w();
        try (MemoryStack memorystack = MemoryStack.stackPush();){
            int i = this.R_4764_Y.n_1700_B();
            int j = this.n_1700_B() * i;
            long k = memorystack.nmalloc(j);
            for (int l = 0; l < this.J_1907_R() / 2; ++l) {
                int i1 = l * this.n_1700_B() * i;
                int j1 = (this.J_1907_R() - 1 - l) * this.n_1700_B() * i;
                MemoryUtil.memCopy((long)(this.v_4262_N + (long)i1), (long)k, (long)j);
                MemoryUtil.memCopy((long)(this.v_4262_N + (long)j1), (long)(this.v_4262_N + (long)i1), (long)j);
                MemoryUtil.memCopy((long)k, (long)(this.v_4262_N + (long)j1), (long)j);
            }
        }
    }

    public void n_1700_B(int xIn, int yIn, int widthIn, int heightIn, i_2518_W imageIn) {
        this.s_956_w();
        if (imageIn.R_4764_Y() != this.R_4764_Y) {
            throw new UnsupportedOperationException("resizeSubRectTo only works for images of the same format.");
        }
        int i = this.R_4764_Y.n_1700_B();
        STBImageResize.nstbir_resize_uint8((long)(this.v_4262_N + (long)((xIn + yIn * this.n_1700_B()) * i)), (int)widthIn, (int)heightIn, (int)(this.n_1700_B() * i), (long)imageIn.v_4262_N, (int)imageIn.n_1700_B(), (int)imageIn.J_1907_R(), (int)0, (int)i);
    }

    public void v_4262_N() {
        c_3314_E.n_1700_B(this.v_4262_N);
    }

    public static i_2518_W n_1700_B(String stringIn) throws IOException {
        i_2518_W nativeimage;
        byte[] abyte = Base64.getDecoder().decode(stringIn.replaceAll("\n", "").getBytes(Charsets.UTF_8));
        try (MemoryStack memorystack = MemoryStack.stackPush();){
            ByteBuffer bytebuffer = memorystack.malloc(abyte.length);
            bytebuffer.put(abyte);
            ((Buffer)bytebuffer).rewind();
            nativeimage = i_2518_W.n_1700_B(bytebuffer);
        }
        return nativeimage;
    }

    public static int n_1700_B(int col) {
        return col >> 24 & 0xFF;
    }

    public static int J_1907_R(int col) {
        return col >> 0 & 0xFF;
    }

    public static int R_4764_Y(int col) {
        return col >> 8 & 0xFF;
    }

    public static int G_564_y(int col) {
        return col >> 16 & 0xFF;
    }

    public static int n_1700_B(int alpha, int blue, int green, int red) {
        return (alpha & 0xFF) << 24 | (blue & 0xFF) << 16 | (green & 0xFF) << 8 | (red & 0xFF) << 0;
    }

    public IntBuffer w_1484_f() {
        if (this.R_4764_Y != lightning.product.i_2518_W$n_1700_B.n_1700_B) {
            throw new IllegalArgumentException(String.format("getBuffer only works on RGBA images; have %s", new Object[]{this.R_4764_Y}));
        }
        this.s_956_w();
        return MemoryUtil.memIntBuffer((long)this.v_4262_N, (int)((int)this.w_1484_f));
    }

    public void P_1922_E(int p_fillRGBA_1_) {
        if (this.R_4764_Y != lightning.product.i_2518_W$n_1700_B.n_1700_B) {
            throw new IllegalArgumentException(String.format("getBuffer only works on RGBA images; have %s", new Object[]{this.R_4764_Y}));
        }
        this.s_956_w();
        MemoryUtil.memSet((long)this.v_4262_N, (int)p_fillRGBA_1_, (long)this.w_1484_f);
    }

    public long t_148_a() {
        return this.w_1484_f;
    }

    public void J_1907_R(boolean p_downloadFromFramebuffer_1_) {
        this.s_956_w();
        this.R_4764_Y.J_1907_R();
        if (p_downloadFromFramebuffer_1_) {
            X_933_l.J_1907_R(3357, Float.MAX_VALUE);
        }
        X_933_l.n_1700_B(0, 0, this.G_564_y, this.P_1922_E, this.R_4764_Y.G_564_y(), 5121, this.v_4262_N);
        if (p_downloadFromFramebuffer_1_) {
            X_933_l.J_1907_R(3357, 0.0f);
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(4, 6408, true, true, true, false, true, 0, 8, 16, 255, 24, true);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(3, 6407, true, true, true, false, false, 0, 8, 16, 255, 255, true);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(2, 6410, false, false, false, true, true, 255, 255, 255, 0, 8, true);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(1, 6409, false, false, false, true, false, 0, 0, 0, 0, 255, true);
        private final int P_1922_E;
        private final int u_1723_Y;
        private final boolean v_4262_N;
        private final boolean w_1484_f;
        private final boolean t_148_a;
        private final boolean s_956_w;
        private final boolean u_2550_I;
        private final int M_588_G;
        private final int P_4830_p;
        private final int h_1847_R;
        private final int Q_4569_t;
        private final int M_182_A;
        private final boolean t_1786_h;
        private static final /* synthetic */ n_1700_B[] multiplayerClientSuggestionProvider;

        public static n_1700_B[] values() {
            return (n_1700_B[])multiplayerClientSuggestionProvider.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(int channelsIn, int glFormatIn, boolean redIn, boolean greenIn, boolean blueIn, boolean luminanceIn, boolean alphaIn, int offsetRedIn, int offsetGreenIn, int offsetBlueIn, int offsetLuminanceIn, int offsetAlphaIn, boolean standardIn) {
            this.P_1922_E = channelsIn;
            this.u_1723_Y = glFormatIn;
            this.v_4262_N = redIn;
            this.w_1484_f = greenIn;
            this.t_148_a = blueIn;
            this.s_956_w = luminanceIn;
            this.u_2550_I = alphaIn;
            this.M_588_G = offsetRedIn;
            this.P_4830_p = offsetGreenIn;
            this.h_1847_R = offsetBlueIn;
            this.Q_4569_t = offsetLuminanceIn;
            this.M_182_A = offsetAlphaIn;
            this.t_1786_h = standardIn;
        }

        public int n_1700_B() {
            return this.P_1922_E;
        }

        public void J_1907_R() {
            c_4037_x.n_1700_B(c_4037_x::J_1907_R);
            X_933_l.h_1847_R(3333, this.n_1700_B());
        }

        public void R_4764_Y() {
            c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
            X_933_l.h_1847_R(3317, this.n_1700_B());
        }

        public int G_564_y() {
            return this.u_1723_Y;
        }

        public boolean P_1922_E() {
            return this.u_2550_I;
        }

        public int u_1723_Y() {
            return this.M_182_A;
        }

        public boolean v_4262_N() {
            return this.s_956_w || this.u_2550_I;
        }

        public int w_1484_f() {
            return this.s_956_w ? this.Q_4569_t : this.M_182_A;
        }

        public boolean t_148_a() {
            return this.t_1786_h;
        }

        private static n_1700_B n_1700_B(int channelsIn) {
            switch (channelsIn) {
                case 1: {
                    return G_564_y;
                }
                case 2: {
                    return R_4764_Y;
                }
                case 3: {
                    return J_1907_R;
                }
            }
            return n_1700_B;
        }

        private static /* synthetic */ n_1700_B[] s_956_w() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            multiplayerClientSuggestionProvider = lightning.product.i_2518_W$n_1700_B.s_956_w();
        }
    }

    static class R_4764_Y
    extends STBIWriteCallback {
        private final WritableByteChannel n_1700_B;
        @Nullable
        private IOException J_1907_R;

        private R_4764_Y(WritableByteChannel byteChannelIn) {
            this.n_1700_B = byteChannelIn;
        }

        public void invoke(long p_invoke_1_, long p_invoke_3_, int p_invoke_5_) {
            ByteBuffer bytebuffer = lightning.product.i_2518_W$R_4764_Y.getData((long)p_invoke_3_, (int)p_invoke_5_);
            try {
                this.n_1700_B.write(bytebuffer);
            }
            catch (IOException ioexception) {
                this.J_1907_R = ioexception;
            }
        }

        public void n_1700_B() throws IOException {
            if (this.J_1907_R != null) {
                throw this.J_1907_R;
            }
        }
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(6408);
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(6407);
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R(6410);
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R(6409);
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R(32841);
        private final int u_1723_Y;
        private static final /* synthetic */ J_1907_R[] v_4262_N;

        public static J_1907_R[] values() {
            return (J_1907_R[])v_4262_N.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(int glFormatIn) {
            this.u_1723_Y = glFormatIn;
        }

        int n_1700_B() {
            return this.u_1723_Y;
        }

        private static /* synthetic */ J_1907_R[] J_1907_R() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            v_4262_N = lightning.product.i_2518_W$J_1907_R.J_1907_R();
        }
    }
}


