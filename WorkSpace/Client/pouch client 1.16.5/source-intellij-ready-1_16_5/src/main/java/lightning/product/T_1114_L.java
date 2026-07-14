/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.N_1972_P;
import lightning.product.ResourceManager;
import lightning.product.c_4037_x;
import lightning.product.c_4477_a;
import lightning.product.i_2518_W;
import net.optifine.Config;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class T_1114_L
extends c_4477_a {
    private static final Logger n_1700_B = LogManager.getLogger();
    @Nullable
    private i_2518_W J_1907_R;

    public T_1114_L(i_2518_W nativeImageIn) {
        this.J_1907_R = nativeImageIn;
        if (!c_4037_x.J_1907_R()) {
            c_4037_x.n_1700_B(() -> {
                N_1972_P.n_1700_B(this.getGlTextureId(), this.J_1907_R.n_1700_B(), this.J_1907_R.J_1907_R());
                this.n_1700_B();
                if (Config.isShaders()) {
                    ShadersTex.initDynamicTextureNS(this);
                }
            });
        } else {
            N_1972_P.n_1700_B(this.getGlTextureId(), this.J_1907_R.n_1700_B(), this.J_1907_R.J_1907_R());
            this.n_1700_B();
            if (Config.isShaders()) {
                ShadersTex.initDynamicTextureNS(this);
            }
        }
    }

    public T_1114_L(int widthIn, int heightIn, boolean clearIn) {
        c_4037_x.n_1700_B(c_4037_x::P_1922_E);
        this.J_1907_R = new i_2518_W(widthIn, heightIn, clearIn);
        N_1972_P.n_1700_B(this.getGlTextureId(), this.J_1907_R.n_1700_B(), this.J_1907_R.J_1907_R());
        if (Config.isShaders()) {
            ShadersTex.initDynamicTextureNS(this);
        }
    }

    @Override
    public void loadTexture(ResourceManager manager) {
    }

    public void n_1700_B() {
        if (this.J_1907_R != null) {
            this.bindTexture();
            this.J_1907_R.n_1700_B(0, 0, 0, false);
        } else {
            n_1700_B.warn("Trying to upload disposed texture {}", (Object)this.getGlTextureId());
        }
    }

    @Nullable
    public i_2518_W J_1907_R() {
        return this.J_1907_R;
    }

    public void n_1700_B(i_2518_W nativeImageIn) {
        if (this.J_1907_R != null) {
            this.J_1907_R.close();
        }
        this.J_1907_R = nativeImageIn;
    }

    @Override
    public void close() {
        if (this.J_1907_R != null) {
            this.J_1907_R.close();
            this.deleteGlTexture();
            this.J_1907_R = null;
        }
    }
}


