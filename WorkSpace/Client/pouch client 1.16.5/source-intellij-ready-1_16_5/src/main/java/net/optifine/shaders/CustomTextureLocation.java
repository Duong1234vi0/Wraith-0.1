/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders;

import lightning.product.C_3240_x;
import lightning.product.P_4645_d;
import lightning.product.MinecraftClient;
import lightning.product.c_4477_a;
import lightning.product.g_2336_b;
import net.optifine.shaders.ICustomTexture;
import net.optifine.shaders.MultiTexID;

public class CustomTextureLocation
implements ICustomTexture {
    private int textureUnit = -1;
    private g_2336_b location;
    private int variant = 0;
    private c_4477_a texture;
    public static final int VARIANT_BASE = 0;
    public static final int VARIANT_NORMAL = 1;
    public static final int VARIANT_SPECULAR = 2;

    public CustomTextureLocation(int textureUnit, g_2336_b location, int variant) {
        this.textureUnit = textureUnit;
        this.location = location;
        this.variant = variant;
    }

    public c_4477_a getTexture() {
        if (this.texture == null) {
            C_3240_x texturemanager = MinecraftClient.A_4115_X().G_624_v();
            this.texture = texturemanager.J_1907_R(this.location);
            if (this.texture == null) {
                this.texture = new P_4645_d(this.location);
                texturemanager.n_1700_B(this.location, this.texture);
                this.texture = texturemanager.J_1907_R(this.location);
            }
        }
        return this.texture;
    }

    public void reloadTexture() {
        this.texture = null;
    }

    @Override
    public int getTextureId() {
        MultiTexID multitexid;
        c_4477_a texture = this.getTexture();
        if (this.variant != 0 && texture instanceof c_4477_a && (multitexid = texture.multiTex) != null) {
            if (this.variant == 1) {
                return multitexid.norm;
            }
            if (this.variant == 2) {
                return multitexid.spec;
            }
        }
        return texture.getGlTextureId();
    }

    @Override
    public int getTextureUnit() {
        return this.textureUnit;
    }

    @Override
    public void deleteTexture() {
    }

    public String toString() {
        return "textureUnit: " + this.textureUnit + ", location: " + String.valueOf(this.location) + ", glTextureId: " + String.valueOf(this.texture != null ? Integer.valueOf(this.texture.getGlTextureId()) : "");
    }
}


