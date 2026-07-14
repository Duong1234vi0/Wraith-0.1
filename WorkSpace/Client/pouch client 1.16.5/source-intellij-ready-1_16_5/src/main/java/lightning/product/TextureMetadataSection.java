/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.TextureMetadataSectionSerializer;

public class TextureMetadataSection {
    public static final TextureMetadataSectionSerializer n_1700_B = new TextureMetadataSectionSerializer();
    private final boolean J_1907_R;
    private final boolean R_4764_Y;

    public TextureMetadataSection(boolean textureBlurIn, boolean textureClampIn) {
        this.J_1907_R = textureBlurIn;
        this.R_4764_Y = textureClampIn;
    }

    public boolean n_1700_B() {
        return this.J_1907_R;
    }

    public boolean J_1907_R() {
        return this.R_4764_Y;
    }
}


