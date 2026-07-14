/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.EXTFramebufferObject
 *  org.lwjgl.opengl.GL30
 */
package net.optifine.shaders;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import lightning.product.X_933_l;
import net.optifine.shaders.SMCLog;
import net.optifine.shaders.Shaders;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL30;

public class FixedFramebuffer {
    private String name;
    private int width;
    private int height;
    private int[] colorTextures;
    private int[] colorAttachments;
    private boolean depthFilterNearest;
    private boolean depthFilterHardware;
    private int glFramebuffer;
    private int depthTexture;
    private IntBuffer glDrawBuffers;

    public FixedFramebuffer(String name, int width, int height, int[] colorTextures, int[] colorAttachments, boolean depthFilterNearest, boolean depthFilterHardware) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.colorTextures = colorTextures;
        this.colorAttachments = colorAttachments;
        this.depthFilterNearest = depthFilterNearest;
        this.depthFilterHardware = depthFilterHardware;
    }

    public void setup() {
        int i1;
        if (this.exists()) {
            this.delete();
        }
        this.glFramebuffer = EXTFramebufferObject.glGenFramebuffersEXT();
        this.bindFramebuffer();
        GL30.glDrawBuffers((int)0);
        GL30.glReadBuffer((int)0);
        this.depthTexture = GL30.glGenTextures();
        X_933_l.w_1457_N(this.depthTexture);
        GL30.glTexParameteri((int)3553, (int)10242, (int)33071);
        GL30.glTexParameteri((int)3553, (int)10243, (int)33071);
        int i = this.depthFilterNearest ? 9728 : 9729;
        GL30.glTexParameteri((int)3553, (int)10241, (int)i);
        GL30.glTexParameteri((int)3553, (int)10240, (int)i);
        if (this.depthFilterHardware) {
            GL30.glTexParameteri((int)3553, (int)34892, (int)34894);
        }
        GL30.glTexImage2D((int)3553, (int)0, (int)6402, (int)this.width, (int)this.height, (int)0, (int)6402, (int)5126, (FloatBuffer)null);
        EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)36096, (int)3553, (int)this.depthTexture, (int)0);
        Shaders.checkGLError("FBS " + this.name + " depth");
        for (int j = 0; j < this.colorTextures.length; ++j) {
            EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)this.colorAttachments[j], (int)3553, (int)this.colorTextures[j], (int)0);
            Shaders.checkGLError("FBS " + this.name + " color");
        }
        X_933_l.w_1457_N(0);
        if (this.colorTextures.length > 0) {
            this.glDrawBuffers = BufferUtils.createIntBuffer((int)this.colorAttachments.length);
            for (int l = 0; l < this.colorAttachments.length; ++l) {
                int k = this.colorAttachments[l];
                this.glDrawBuffers.put(l, k);
            }
            GL30.glDrawBuffers((IntBuffer)this.glDrawBuffers);
            GL30.glReadBuffer((int)0);
        }
        if ((i1 = EXTFramebufferObject.glCheckFramebufferStatusEXT((int)36160)) != 36053) {
            Shaders.printChatAndLogError("[Shaders] Error creating framebuffer: " + this.name + ", status: " + i1);
        } else {
            SMCLog.info("Framebuffer created: " + this.name);
        }
    }

    public void bindFramebuffer() {
        X_933_l.w_1484_f(36160, this.glFramebuffer);
    }

    public void delete() {
        if (this.glFramebuffer != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT((int)this.glFramebuffer);
            this.glFramebuffer = 0;
        }
        if (this.depthTexture != 0) {
            X_933_l.multiplayerClientSuggestionProvider(this.depthTexture);
            this.depthTexture = 0;
        }
        this.glDrawBuffers = null;
    }

    public boolean exists() {
        return this.glFramebuffer != 0;
    }

    public String toString() {
        return this.name;
    }
}


