/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders;

import lightning.product.N_1972_P;
import lightning.product.c_4477_a;
import net.optifine.shaders.ICustomTexture;

public class CustomTexture
implements ICustomTexture {
    private int textureUnit = -1;
    private String path = null;
    private c_4477_a texture = null;

    public CustomTexture(int textureUnit, String path, c_4477_a texture) {
        this.textureUnit = textureUnit;
        this.path = path;
        this.texture = texture;
    }

    @Override
    public int getTextureUnit() {
        return this.textureUnit;
    }

    public String getPath() {
        return this.path;
    }

    public c_4477_a getTexture() {
        return this.texture;
    }

    @Override
    public int getTextureId() {
        return this.texture.getGlTextureId();
    }

    @Override
    public void deleteTexture() {
        N_1972_P.n_1700_B(this.texture.getGlTextureId());
    }

    public String toString() {
        return "textureUnit: " + this.textureUnit + ", path: " + this.path + ", glTextureId: " + this.getTextureId();
    }
}

