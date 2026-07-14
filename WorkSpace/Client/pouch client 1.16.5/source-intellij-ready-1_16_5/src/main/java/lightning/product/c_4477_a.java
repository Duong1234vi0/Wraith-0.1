/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.util.concurrent.Executor;
import lightning.product.C_3240_x;
import lightning.product.N_1972_P;
import lightning.product.ResourceManager;
import lightning.product.X_933_l;
import lightning.product.c_4037_x;
import lightning.product.g_2336_b;
import net.optifine.Config;
import net.optifine.shaders.MultiTexID;
import net.optifine.shaders.ShadersTex;

public abstract class c_4477_a
implements AutoCloseable {
    protected int glTextureId = -1;
    protected boolean blur;
    protected boolean mipmap;
    public MultiTexID multiTex;
    private boolean blurMipmapSet;
    private boolean lastBlur;
    private boolean lastMipmap;

    public void setBlurMipmapDirect(boolean blurIn, boolean mipmapIn) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        if (!this.blurMipmapSet || this.blur != blurIn || this.mipmap != mipmapIn) {
            int j;
            int i;
            this.blurMipmapSet = true;
            this.blur = blurIn;
            this.mipmap = mipmapIn;
            if (blurIn) {
                i = mipmapIn ? 9987 : 9729;
                j = 9729;
            } else {
                int k = Config.getMipmapType();
                i = mipmapIn ? k : 9728;
                j = 9728;
            }
            X_933_l.w_1457_N(this.getGlTextureId());
            X_933_l.J_1907_R(3553, 10241, i);
            X_933_l.J_1907_R(3553, 10240, j);
        }
    }

    public int getGlTextureId() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        if (this.glTextureId == -1) {
            this.glTextureId = N_1972_P.n_1700_B();
        }
        return this.glTextureId;
    }

    public void deleteGlTexture() {
        if (!c_4037_x.J_1907_R()) {
            c_4037_x.n_1700_B(() -> {
                ShadersTex.deleteTextures(this, this.glTextureId);
                this.blurMipmapSet = false;
                if (this.glTextureId != -1) {
                    N_1972_P.n_1700_B(this.glTextureId);
                    this.glTextureId = -1;
                }
            });
        } else if (this.glTextureId != -1) {
            ShadersTex.deleteTextures(this, this.glTextureId);
            this.blurMipmapSet = false;
            N_1972_P.n_1700_B(this.glTextureId);
            this.glTextureId = -1;
        }
    }

    public abstract void loadTexture(ResourceManager var1) throws IOException;

    public void bindTexture() {
        if (!c_4037_x.R_4764_Y()) {
            c_4037_x.n_1700_B(() -> X_933_l.w_1457_N(this.getGlTextureId()));
        } else {
            X_933_l.w_1457_N(this.getGlTextureId());
        }
    }

    public void loadTexture(C_3240_x textureManagerIn, ResourceManager resourceManagerIn, g_2336_b resourceLocationIn, Executor executorIn) {
        textureManagerIn.n_1700_B(resourceLocationIn, this);
    }

    @Override
    public void close() {
    }

    public MultiTexID getMultiTexID() {
        return ShadersTex.getMultiTexID(this);
    }

    public void setBlurMipmap(boolean p_setBlurMipmap_1_, boolean p_setBlurMipmap_2_) {
        this.lastBlur = this.blur;
        this.lastMipmap = this.mipmap;
        this.setBlurMipmapDirect(p_setBlurMipmap_1_, p_setBlurMipmap_2_);
    }

    public void restoreLastBlurMipmap() {
        this.setBlurMipmapDirect(this.lastBlur, this.lastMipmap);
    }
}


