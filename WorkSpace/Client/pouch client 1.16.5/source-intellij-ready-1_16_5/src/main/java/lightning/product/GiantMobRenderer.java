/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4315_z;
import lightning.product.Giant;
import lightning.product.S_4174_n;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.n_1658_l;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;
import lightning.product.x_4904_Z;

public class GiantMobRenderer
extends r_1334_c<Giant, n_1658_l<Giant>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/zombie/zombie.png");
    private final float t_1786_h;

    public GiantMobRenderer(w_2040_b renderManagerIn, float scaleIn) {
        super(renderManagerIn, new S_4174_n(), 0.5f * scaleIn);
        this.t_1786_h = scaleIn;
        this.n_1700_B(new x_4904_Z<Giant, n_1658_l<Giant>>(this));
        this.n_1700_B(new B_4315_z<Giant, n_1658_l<Giant>, S_4174_n>(this, new S_4174_n(0.5f, true), new S_4174_n(1.0f, true)));
    }

    @Override
    protected void n_1700_B(Giant entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        matrixStackIn.n_1700_B(this.t_1786_h, this.t_1786_h, this.t_1786_h);
    }

    @Override
    public g_2336_b n_1700_B(Giant entity) {
        return n_1700_B;
    }
}


