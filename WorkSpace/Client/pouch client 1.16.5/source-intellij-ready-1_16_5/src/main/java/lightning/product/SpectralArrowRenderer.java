/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ArrowRenderer;
import lightning.product.SpectralArrow;
import lightning.product.g_2336_b;
import lightning.product.w_2040_b;

public class SpectralArrowRenderer
extends ArrowRenderer<SpectralArrow> {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/entity/projectiles/spectral_arrow.png");

    public SpectralArrowRenderer(w_2040_b manager) {
        super(manager);
    }

    @Override
    public g_2336_b n_1700_B(SpectralArrow entity) {
        return n_1700_B;
    }
}


