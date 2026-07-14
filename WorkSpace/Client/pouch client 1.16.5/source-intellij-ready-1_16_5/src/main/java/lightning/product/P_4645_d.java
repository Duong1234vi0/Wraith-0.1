/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.io.Closeable;
import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.N_1972_P;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.c_4037_x;
import lightning.product.c_4477_a;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.TextureMetadataSection;
import net.optifine.Config;
import net.optifine.EmissiveTextures;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class P_4645_d
extends c_4477_a {
    private static final Logger n_1700_B = LogManager.getLogger();
    protected final g_2336_b R_4764_Y;
    private ResourceManager J_1907_R;
    public g_2336_b G_564_y;
    public boolean P_1922_E;

    public P_4645_d(g_2336_b textureResourceLocation) {
        this.R_4764_Y = textureResourceLocation;
    }

    @Override
    public void loadTexture(ResourceManager manager) throws IOException {
        boolean flag1;
        boolean flag;
        this.J_1907_R = manager;
        n_1700_B simpletexture$texturedata = this.n_1700_B(manager);
        simpletexture$texturedata.R_4764_Y();
        TextureMetadataSection texturemetadatasection = simpletexture$texturedata.n_1700_B();
        if (texturemetadatasection != null) {
            flag = texturemetadatasection.n_1700_B();
            flag1 = texturemetadatasection.J_1907_R();
        } else {
            flag = false;
            flag1 = false;
        }
        i_2518_W nativeimage = simpletexture$texturedata.J_1907_R();
        if (!c_4037_x.R_4764_Y()) {
            c_4037_x.n_1700_B(() -> this.n_1700_B(nativeimage, flag, flag1));
        } else {
            this.n_1700_B(nativeimage, flag, flag1);
        }
    }

    private void n_1700_B(i_2518_W imageIn, boolean blurIn, boolean clampIn) {
        N_1972_P.n_1700_B(this.getGlTextureId(), 0, imageIn.n_1700_B(), imageIn.J_1907_R());
        imageIn.n_1700_B(0, 0, 0, 0, 0, imageIn.n_1700_B(), imageIn.J_1907_R(), blurIn, clampIn, false, true);
        if (Config.isShaders()) {
            ShadersTex.loadSimpleTextureNS(this.getGlTextureId(), imageIn, blurIn, clampIn, this.J_1907_R, this.R_4764_Y, this.getMultiTexID());
        }
        if (EmissiveTextures.isActive()) {
            EmissiveTextures.loadTexture(this.R_4764_Y, this);
        }
    }

    protected n_1700_B n_1700_B(ResourceManager resourceManager) {
        return lightning.product.P_4645_d$n_1700_B.n_1700_B(resourceManager, this.R_4764_Y);
    }

    public static class n_1700_B
    implements Closeable {
        @Nullable
        private final TextureMetadataSection n_1700_B;
        @Nullable
        private final i_2518_W J_1907_R;
        @Nullable
        private final IOException R_4764_Y;

        public n_1700_B(IOException exceptionIn) {
            this.R_4764_Y = exceptionIn;
            this.n_1700_B = null;
            this.J_1907_R = null;
        }

        public n_1700_B(@Nullable TextureMetadataSection metadataIn, i_2518_W imageIn) {
            this.R_4764_Y = null;
            this.n_1700_B = metadataIn;
            this.J_1907_R = imageIn;
        }

        public static n_1700_B n_1700_B(ResourceManager resourceManagerIn, g_2336_b locationIn) {
            n_1700_B n_1700_B2;
            block10: {
                Resource iresource = resourceManagerIn.n_1700_B(locationIn);
                try {
                    i_2518_W nativeimage = i_2518_W.n_1700_B(iresource.J_1907_R());
                    TextureMetadataSection texturemetadatasection = null;
                    try {
                        texturemetadatasection = iresource.n_1700_B(TextureMetadataSection.n_1700_B);
                    }
                    catch (RuntimeException runtimeexception) {
                        n_1700_B.warn("Failed reading metadata of: {}", (Object)locationIn, (Object)runtimeexception);
                    }
                    n_1700_B2 = new n_1700_B(texturemetadatasection, nativeimage);
                    if (iresource == null) break block10;
                }
                catch (Throwable throwable) {
                    try {
                        if (iresource != null) {
                            try {
                                iresource.close();
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                        }
                        throw throwable;
                    }
                    catch (IOException ioexception1) {
                        return new n_1700_B(ioexception1);
                    }
                }
                iresource.close();
            }
            return n_1700_B2;
        }

        @Nullable
        public TextureMetadataSection n_1700_B() {
            return this.n_1700_B;
        }

        public i_2518_W J_1907_R() throws IOException {
            if (this.R_4764_Y != null) {
                throw this.R_4764_Y;
            }
            return this.J_1907_R;
        }

        @Override
        public void close() {
            if (this.J_1907_R != null) {
                this.J_1907_R.close();
            }
        }

        public void R_4764_Y() throws IOException {
            if (this.R_4764_Y != null) {
                throw this.R_4764_Y;
            }
        }
    }
}


