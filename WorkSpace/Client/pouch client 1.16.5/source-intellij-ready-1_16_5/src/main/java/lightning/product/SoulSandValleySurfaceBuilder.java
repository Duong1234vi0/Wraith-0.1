/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import lightning.product.I_1748_L;
import lightning.product.K_4074_S;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.a_3742_W;

public class SoulSandValleySurfaceBuilder
extends I_1748_L {
    private static final K_4074_S n_1700_B = a_3742_W.C_415_h.multiplayerClientSuggestionProvider();
    private static final K_4074_S J_1907_R = a_3742_W.v_165_F.multiplayerClientSuggestionProvider();
    private static final K_4074_S R_4764_Y = a_3742_W.t_4043_B.multiplayerClientSuggestionProvider();
    private static final ImmutableList<K_4074_S> G_564_y = ImmutableList.of((Object)n_1700_B, (Object)J_1907_R);

    public SoulSandValleySurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232135_1_) {
        super(p_i232135_1_);
    }

    @Override
    protected ImmutableList<K_4074_S> n_1700_B() {
        return G_564_y;
    }

    @Override
    protected ImmutableList<K_4074_S> J_1907_R() {
        return G_564_y;
    }

    @Override
    protected K_4074_S R_4764_Y() {
        return R_4764_Y;
    }
}


