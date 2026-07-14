/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.EXTFramebufferObject
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL30
 */
package net.optifine.shaders;

import java.awt.Dimension;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lightning.product.X_933_l;
import lightning.product.Z_2491_A;
import net.optifine.shaders.DrawBuffers;
import net.optifine.shaders.FixedFramebuffer;
import net.optifine.shaders.FlipTextures;
import net.optifine.shaders.GlState;
import net.optifine.shaders.SMCLog;
import net.optifine.shaders.Shaders;
import net.optifine.util.ArrayUtils;
import net.optifine.util.CompoundIntKey;
import net.optifine.util.CompoundKey;
import net.optifine.util.DynamicDimension;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class ShadersFramebuffer {
    private String name;
    private int width;
    private int height;
    private int usedColorBuffers;
    private int usedDepthBuffers;
    private int maxDrawBuffers;
    private boolean[] depthFilterNearest;
    private boolean[] depthFilterHardware;
    private boolean[] colorFilterNearest;
    private DynamicDimension[] colorBufferSizes;
    private int[] buffersFormat;
    private int[] colorTextureUnits;
    private int[] depthTextureUnits;
    private int[] colorImageUnits;
    private int glFramebuffer;
    private FlipTextures colorTexturesFlip;
    private IntBuffer depthTextures;
    private final DrawBuffers drawBuffers;
    private DrawBuffers activeDrawBuffers;
    private int[] drawColorTextures;
    private int[] drawColorTexturesMap;
    private boolean[] dirtyColorTextures;
    private Map<CompoundKey, FixedFramebuffer> fixedFramebuffers = new HashMap<CompoundKey, FixedFramebuffer>();

    public ShadersFramebuffer(String name, int width, int height, int usedColorBuffers, int usedDepthBuffers, int maxDrawBuffers, boolean[] depthFilterNearest, boolean[] depthFilterHardware, boolean[] colorFilterNearest, DynamicDimension[] colorBufferSizes, int[] buffersFormat, int[] colorTextureUnits, int[] depthTextureUnits, int[] colorImageUnits, DrawBuffers drawBuffers) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.usedColorBuffers = usedColorBuffers;
        this.usedDepthBuffers = usedDepthBuffers;
        this.maxDrawBuffers = maxDrawBuffers;
        this.depthFilterNearest = depthFilterNearest;
        this.depthFilterHardware = depthFilterHardware;
        this.colorFilterNearest = colorFilterNearest;
        this.colorBufferSizes = colorBufferSizes;
        this.buffersFormat = buffersFormat;
        this.colorTextureUnits = colorTextureUnits;
        this.depthTextureUnits = depthTextureUnits;
        this.colorImageUnits = colorImageUnits;
        this.drawBuffers = drawBuffers;
    }

    public void setup() {
        int j1;
        if (this.exists()) {
            this.delete();
        }
        this.colorTexturesFlip = new FlipTextures(this.name + "ColorTexturesFlip", this.usedColorBuffers);
        this.depthTextures = BufferUtils.createIntBuffer((int)this.usedDepthBuffers);
        this.drawColorTextures = new int[this.usedColorBuffers];
        this.drawColorTexturesMap = new int[this.usedColorBuffers];
        this.dirtyColorTextures = new boolean[this.maxDrawBuffers];
        Arrays.fill(this.drawColorTextures, 0);
        Arrays.fill(this.drawColorTexturesMap, -1);
        Arrays.fill(this.dirtyColorTextures, false);
        for (int i = 0; i < this.drawBuffers.limit(); ++i) {
            this.drawBuffers.put(i, 36064 + i);
        }
        this.glFramebuffer = EXTFramebufferObject.glGenFramebuffersEXT();
        this.bindFramebuffer();
        GL30.glDrawBuffers((int)0);
        GL30.glReadBuffer((int)0);
        GL30.glGenTextures((IntBuffer)((IntBuffer)((Buffer)this.depthTextures).clear().limit(this.usedDepthBuffers)));
        this.colorTexturesFlip.clear().limit(this.usedColorBuffers).genTextures();
        ((Buffer)this.depthTextures).position(0);
        this.colorTexturesFlip.position(0);
        for (int k = 0; k < this.usedDepthBuffers; ++k) {
            X_933_l.w_1457_N(this.depthTextures.get(k));
            GL30.glTexParameteri((int)3553, (int)10242, (int)33071);
            GL30.glTexParameteri((int)3553, (int)10243, (int)33071);
            int j = this.depthFilterNearest[k] ? 9728 : 9729;
            GL30.glTexParameteri((int)3553, (int)10241, (int)j);
            GL30.glTexParameteri((int)3553, (int)10240, (int)j);
            if (this.depthFilterHardware[k]) {
                GL30.glTexParameteri((int)3553, (int)34892, (int)34894);
            }
            GL30.glTexImage2D((int)3553, (int)0, (int)6402, (int)this.width, (int)this.height, (int)0, (int)6402, (int)5126, (FloatBuffer)null);
        }
        this.setFramebufferTexture2D(36160, 36096, 3553, this.depthTextures.get(0), 0);
        Shaders.checkGLError("FBS " + this.name + " depth");
        for (int l = 0; l < this.usedColorBuffers; ++l) {
            X_933_l.w_1457_N(this.colorTexturesFlip.getA(l));
            GL30.glTexParameteri((int)3553, (int)10242, (int)33071);
            GL30.glTexParameteri((int)3553, (int)10243, (int)33071);
            int k1 = this.colorFilterNearest[l] ? 9728 : 9729;
            GL30.glTexParameteri((int)3553, (int)10241, (int)k1);
            GL30.glTexParameteri((int)3553, (int)10240, (int)k1);
            Dimension dimension = this.colorBufferSizes[l] != null ? this.colorBufferSizes[l].getDimension(this.width, this.height) : new Dimension(this.width, this.height);
            GL30.glTexImage2D((int)3553, (int)0, (int)this.buffersFormat[l], (int)dimension.width, (int)dimension.height, (int)0, (int)Shaders.getPixelFormat(this.buffersFormat[l]), (int)33639, (ByteBuffer)null);
            this.setFramebufferTexture2D(36160, 36064 + l, 3553, this.colorTexturesFlip.getA(l), 0);
            Shaders.checkGLError("FBS " + this.name + " colorA");
        }
        for (int i1 = 0; i1 < this.usedColorBuffers; ++i1) {
            X_933_l.w_1457_N(this.colorTexturesFlip.getB(i1));
            GL30.glTexParameteri((int)3553, (int)10242, (int)33071);
            GL30.glTexParameteri((int)3553, (int)10243, (int)33071);
            int l1 = this.colorFilterNearest[i1] ? 9728 : 9729;
            GL30.glTexParameteri((int)3553, (int)10241, (int)l1);
            GL30.glTexParameteri((int)3553, (int)10240, (int)l1);
            Dimension dimension1 = this.colorBufferSizes[i1] != null ? this.colorBufferSizes[i1].getDimension(this.width, this.height) : new Dimension(this.width, this.height);
            GL30.glTexImage2D((int)3553, (int)0, (int)this.buffersFormat[i1], (int)dimension1.width, (int)dimension1.height, (int)0, (int)Shaders.getPixelFormat(this.buffersFormat[i1]), (int)33639, (ByteBuffer)null);
            Shaders.checkGLError("FBS " + this.name + " colorB");
        }
        X_933_l.w_1457_N(0);
        if (this.usedColorBuffers > 0) {
            this.setDrawBuffers(this.drawBuffers);
            GL30.glReadBuffer((int)0);
        }
        if ((j1 = EXTFramebufferObject.glCheckFramebufferStatusEXT((int)36160)) != 36053) {
            Shaders.printChatAndLogError("[Shaders] Error creating framebuffer: " + this.name + ", status: " + j1);
        } else {
            SMCLog.info("Framebuffer created: " + this.name);
        }
    }

    public void delete() {
        if (this.glFramebuffer != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT((int)this.glFramebuffer);
            this.glFramebuffer = 0;
        }
        if (this.colorTexturesFlip != null) {
            this.colorTexturesFlip.deleteTextures();
            this.colorTexturesFlip = null;
        }
        if (this.depthTextures != null) {
            X_933_l.J_1907_R(this.depthTextures);
            this.depthTextures = null;
        }
        this.drawBuffers.position(0).fill(0);
        for (FixedFramebuffer fixedframebuffer : this.fixedFramebuffers.values()) {
            fixedframebuffer.delete();
        }
        this.fixedFramebuffers.clear();
    }

    public String getName() {
        return this.name;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getGlFramebuffer() {
        return this.glFramebuffer;
    }

    public boolean exists() {
        return this.glFramebuffer != 0;
    }

    public void bindFramebuffer() {
        GlState.bindFramebuffer(this);
    }

    public void setColorTextures(boolean main) {
        for (int i = 0; i < this.usedColorBuffers; ++i) {
            this.setFramebufferTexture2D(36160, 36064 + i, 3553, this.colorTexturesFlip.get(main, i), 0);
        }
    }

    public void setDepthTexture() {
        this.setFramebufferTexture2D(36160, 36096, 3553, this.depthTextures.get(0), 0);
    }

    public void setColorBuffersFiltering(int minFilter, int magFilter) {
        X_933_l.t_1786_h(33984);
        for (int i = 0; i < this.usedColorBuffers; ++i) {
            X_933_l.w_1457_N(this.colorTexturesFlip.getA(i));
            GL11.glTexParameteri((int)3553, (int)10241, (int)minFilter);
            GL11.glTexParameteri((int)3553, (int)10240, (int)magFilter);
            X_933_l.w_1457_N(this.colorTexturesFlip.getB(i));
            GL11.glTexParameteri((int)3553, (int)10241, (int)minFilter);
            GL11.glTexParameteri((int)3553, (int)10240, (int)magFilter);
        }
        X_933_l.w_1457_N(0);
    }

    public void setFramebufferTexture2D(int target, int attachment, int texTarget, int texture, int level) {
        int i = attachment - 36064;
        if (this.isColorBufferIndex(i)) {
            if (this.colorBufferSizes[i] != null) {
                if (this.isColorExtendedIndex(i)) {
                    return;
                }
                texture = 0;
            }
            this.drawColorTextures[i] = texture;
            if (i >= this.maxDrawBuffers) {
                int j = this.drawColorTexturesMap[i];
                if (!this.isDrawBufferIndex(j)) {
                    return;
                }
                attachment = 36064 + j;
            }
        }
        this.bindFramebuffer();
        EXTFramebufferObject.glFramebufferTexture2DEXT((int)target, (int)attachment, (int)texTarget, (int)texture, (int)level);
    }

    public boolean isColorBufferIndex(int index) {
        return index >= 0 && index < this.usedColorBuffers;
    }

    public boolean isColorExtendedIndex(int index) {
        return index >= this.maxDrawBuffers && index < this.usedColorBuffers;
    }

    public boolean isDrawBufferIndex(int index) {
        return index >= 0 && index < this.maxDrawBuffers;
    }

    private void setDrawColorTexturesMap(int[] newColorTexturesMap) {
        this.bindFramebuffer();
        for (int i = 0; i < this.maxDrawBuffers; ++i) {
            if (!this.dirtyColorTextures[i]) continue;
            int j = this.drawColorTextures[i];
            EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)(36064 + i), (int)3553, (int)j, (int)0);
            this.dirtyColorTextures[i] = false;
        }
        this.drawColorTexturesMap = newColorTexturesMap;
        for (int l = this.maxDrawBuffers; l < this.drawColorTexturesMap.length; ++l) {
            int i1 = this.drawColorTexturesMap[l];
            if (i1 < 0) continue;
            int k = this.drawColorTextures[l];
            EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)(36064 + i1), (int)3553, (int)k, (int)0);
            this.dirtyColorTextures[i1] = true;
        }
    }

    public void setDrawBuffers(DrawBuffers drawBuffersIn) {
        if (drawBuffersIn == null) {
            drawBuffersIn = Shaders.drawBuffersNone;
        }
        this.setDrawColorTexturesMap(drawBuffersIn.getAttachmentMappings());
        this.activeDrawBuffers = drawBuffersIn;
        this.bindFramebuffer();
        GL30.glDrawBuffers((IntBuffer)drawBuffersIn.getGlDrawBuffers());
        Shaders.checkGLError("setDrawBuffers");
    }

    public void setDrawBuffers() {
        this.setDrawBuffers(this.drawBuffers);
    }

    public DrawBuffers getDrawBuffers() {
        return this.activeDrawBuffers;
    }

    public void bindDepthTextures(int[] depthTextureImageUnits) {
        for (int i = 0; i < this.usedDepthBuffers; ++i) {
            X_933_l.t_1786_h(33984 + depthTextureImageUnits[i]);
            X_933_l.w_1457_N(this.depthTextures.get(i));
        }
        X_933_l.t_1786_h(33984);
    }

    public void bindColorTextures(int startColorBuffer) {
        for (int i = startColorBuffer; i < this.usedColorBuffers; ++i) {
            X_933_l.t_1786_h(33984 + this.colorTextureUnits[i]);
            X_933_l.w_1457_N(this.colorTexturesFlip.getA(i));
            this.bindColorImage(i, true);
        }
    }

    public void bindColorImages(boolean main) {
        if (this.colorImageUnits != null) {
            for (int i = 0; i < this.usedColorBuffers; ++i) {
                this.bindColorImage(i, main);
            }
        }
    }

    public void bindColorImage(int index, boolean main) {
        if (this.colorImageUnits != null) {
            if (index >= 0 && index < this.colorImageUnits.length) {
                int i = Shaders.getImageFormat(this.buffersFormat[index]);
                X_933_l.n_1700_B(this.colorImageUnits[index], this.colorTexturesFlip.get(main, index), 0, false, 0, 35002, i);
            }
            X_933_l.t_1786_h(33984);
        }
    }

    public void generateDepthMipmaps(boolean[] depthMipmapEnabled) {
        for (int i = 0; i < this.usedDepthBuffers; ++i) {
            if (!depthMipmapEnabled[i]) continue;
            X_933_l.t_1786_h(33984 + this.depthTextureUnits[i]);
            X_933_l.w_1457_N(this.depthTextures.get(i));
            GL30.glGenerateMipmap((int)3553);
            GL30.glTexParameteri((int)3553, (int)10241, (int)(this.depthFilterNearest[i] ? 9984 : 9987));
        }
        X_933_l.t_1786_h(33984);
    }

    public void generateColorMipmaps(boolean main, boolean[] colorMipmapEnabled) {
        for (int i = 0; i < this.usedColorBuffers; ++i) {
            if (!colorMipmapEnabled[i]) continue;
            X_933_l.t_1786_h(33984 + this.colorTextureUnits[i]);
            X_933_l.w_1457_N(this.colorTexturesFlip.get(main, i));
            GL30.glGenerateMipmap((int)3553);
            GL30.glTexParameteri((int)3553, (int)10241, (int)(this.colorFilterNearest[i] ? 9984 : 9987));
        }
        X_933_l.t_1786_h(33984);
    }

    public void genCompositeMipmap(int compositeMipmapSetting) {
        if (Shaders.hasGlGenMipmap) {
            for (int i = 0; i < this.usedColorBuffers; ++i) {
                if ((compositeMipmapSetting & 1 << i) == 0) continue;
                X_933_l.t_1786_h(33984 + this.colorTextureUnits[i]);
                GL30.glTexParameteri((int)3553, (int)10241, (int)9987);
                GL30.glGenerateMipmap((int)3553);
            }
            X_933_l.t_1786_h(33984);
        }
    }

    public void flipColorTextures(boolean[] toggleColorTextures) {
        for (int i = 0; i < this.colorTexturesFlip.limit(); ++i) {
            if (!toggleColorTextures[i]) continue;
            this.flipColorTexture(i);
        }
    }

    public void flipColorTexture(int index) {
        this.colorTexturesFlip.flip(index);
        X_933_l.t_1786_h(33984 + this.colorTextureUnits[index]);
        X_933_l.w_1457_N(this.colorTexturesFlip.getA(index));
        this.bindColorImage(index, true);
        this.setFramebufferTexture2D(36160, 36064 + index, 3553, this.colorTexturesFlip.getB(index), 0);
        X_933_l.t_1786_h(33984);
    }

    public void clearColorBuffers(boolean[] buffersClear, Z_2491_A[] clearColors) {
        for (int i = 0; i < this.usedColorBuffers; ++i) {
            if (!buffersClear[i]) continue;
            Z_2491_A vector4f = clearColors[i];
            if (vector4f != null) {
                GL30.glClearColor((float)vector4f.n_1700_B(), (float)vector4f.J_1907_R(), (float)vector4f.R_4764_Y(), (float)vector4f.G_564_y());
            }
            if (this.colorBufferSizes[i] != null) {
                if (this.colorTexturesFlip.isChanged(i)) {
                    this.clearColorBufferFixedSize(i, false);
                }
                this.clearColorBufferFixedSize(i, true);
                continue;
            }
            if (this.colorTexturesFlip.isChanged(i)) {
                this.setFramebufferTexture2D(36160, 36064 + i, 3553, this.colorTexturesFlip.getB(i), 0);
                this.setDrawBuffers(Shaders.drawBuffersColorAtt[i]);
                GL30.glClear((int)16384);
                this.setFramebufferTexture2D(36160, 36064 + i, 3553, this.colorTexturesFlip.getA(i), 0);
            }
            this.setDrawBuffers(Shaders.drawBuffersColorAtt[i]);
            GL30.glClear((int)16384);
        }
    }

    private void clearColorBufferFixedSize(int i, boolean main) {
        Dimension dimension = this.colorBufferSizes[i].getDimension(this.width, this.height);
        if (dimension != null) {
            FixedFramebuffer fixedframebuffer = this.getFixedFramebuffer(dimension.width, dimension.height, Shaders.drawBuffersColorAtt[i], main);
            fixedframebuffer.bindFramebuffer();
            GL30.glClear((int)16384);
        }
    }

    public void clearDepthBuffer(Z_2491_A col) {
        this.setFramebufferTexture2D(36160, 36096, 3553, this.depthTextures.get(0), 0);
        GL30.glClearColor((float)col.n_1700_B(), (float)col.J_1907_R(), (float)col.R_4764_Y(), (float)col.G_564_y());
        GL30.glClear((int)256);
    }

    public String toString() {
        return this.name + ", width: " + this.width + ", height: " + this.height + ", usedColorBuffers: " + this.usedColorBuffers + ", usedDepthBuffers: " + this.usedDepthBuffers + ", maxDrawBuffers: " + this.maxDrawBuffers;
    }

    public FixedFramebuffer getFixedFramebuffer(int width, int height, DrawBuffers dbs, boolean main) {
        IntBuffer intbuffer = dbs.getGlDrawBuffers();
        int i = dbs.limit();
        int[] aint = new int[i];
        int[] aint1 = new int[i];
        for (int j = 0; j < aint.length; ++j) {
            int k = dbs.get(j);
            int l = k - 36064;
            if (!this.isColorBufferIndex(l)) continue;
            aint[j] = this.colorTexturesFlip.get(main, l);
            aint1[j] = intbuffer.get(j);
        }
        CompoundKey compoundkey = new CompoundKey(new CompoundIntKey(aint), new CompoundIntKey(aint1));
        FixedFramebuffer fixedframebuffer = this.fixedFramebuffers.get(compoundkey);
        if (fixedframebuffer == null) {
            String s = this.name + ", [" + ArrayUtils.arrayToString(aint) + "], [" + ArrayUtils.arrayToString(aint1) + "]";
            fixedframebuffer = new FixedFramebuffer(s, width, height, aint, aint1, this.depthFilterNearest[0], this.depthFilterHardware[0]);
            fixedframebuffer.setup();
            this.fixedFramebuffers.put(compoundkey, fixedframebuffer);
        }
        return fixedframebuffer;
    }
}

