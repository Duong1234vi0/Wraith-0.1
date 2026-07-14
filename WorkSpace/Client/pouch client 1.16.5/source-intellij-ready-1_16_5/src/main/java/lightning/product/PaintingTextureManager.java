/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.stream.Stream;
import lightning.product.B_3871_I;
import lightning.product.C_3240_x;
import lightning.product.C_377_T;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.Motive;

public class PaintingTextureManager
extends C_377_T {
    private static final g_2336_b n_1700_B = new g_2336_b("back");

    public PaintingTextureManager(C_3240_x textureManagerIn) {
        super(textureManagerIn, new g_2336_b("textures/atlas/paintings.png"), "painting");
    }

    @Override
    protected Stream<g_2336_b> J_1907_R() {
        return Stream.concat(V_3137_a.Z_976_R.G_564_y().stream(), Stream.of(n_1700_B));
    }

    public B_3871_I n_1700_B(Motive paintingTypeIn) {
        return this.n_1700_B(V_3137_a.Z_976_R.J_1907_R(paintingTypeIn));
    }

    public B_3871_I R_4764_Y() {
        return this.n_1700_B(n_1700_B);
    }
}


