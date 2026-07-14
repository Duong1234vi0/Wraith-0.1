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

public class BasaltDeltasSurfaceBuilder
extends I_1748_L {
    private static final K_4074_S n_1700_B = a_3742_W.s_4990_V.multiplayerClientSuggestionProvider();
    private static final K_4074_S J_1907_R = a_3742_W.m_1964_F.multiplayerClientSuggestionProvider();
    private static final K_4074_S R_4764_Y = a_3742_W.t_4043_B.multiplayerClientSuggestionProvider();
    private static final ImmutableList<K_4074_S> G_564_y = ImmutableList.of((Object)n_1700_B, (Object)J_1907_R);
    private static final ImmutableList<K_4074_S> P_1922_E = ImmutableList.of((Object)n_1700_B);

    public BasaltDeltasSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232123_1_) {
        super(p_i232123_1_);
    }

    @Override
    protected ImmutableList<K_4074_S> n_1700_B() {
        return G_564_y;
    }

    @Override
    protected ImmutableList<K_4074_S> J_1907_R() {
        return P_1922_E;
    }

    @Override
    protected K_4074_S R_4764_Y() {
        return R_4764_Y;
    }
}


