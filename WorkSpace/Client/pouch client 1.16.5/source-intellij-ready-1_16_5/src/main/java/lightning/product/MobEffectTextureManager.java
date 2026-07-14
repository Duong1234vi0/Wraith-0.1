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
import lightning.product.g_422_i;

public class MobEffectTextureManager
extends C_377_T {
    public MobEffectTextureManager(C_3240_x textureManagerIn) {
        super(textureManagerIn, new g_2336_b("textures/atlas/mob_effects.png"), "mob_effect");
    }

    @Override
    protected Stream<g_2336_b> J_1907_R() {
        return V_3137_a.T_2506_i.G_564_y().stream();
    }

    public B_3871_I n_1700_B(g_422_i effectIn) {
        return this.n_1700_B(V_3137_a.T_2506_i.J_1907_R(effectIn));
    }
}


