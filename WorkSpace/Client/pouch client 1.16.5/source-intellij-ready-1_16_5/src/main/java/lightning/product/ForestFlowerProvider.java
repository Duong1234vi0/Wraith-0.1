/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.BlockStateProvider;
import lightning.product.BlockStateProviderType;
import lightning.product.k_594_Q;
import lightning.product.u_530_F;

public class ForestFlowerProvider
extends BlockStateProvider {
    public static final Codec<ForestFlowerProvider> n_1700_B;
    private static final K_4074_S[] G_564_y;
    public static final ForestFlowerProvider R_4764_Y;

    @Override
    protected BlockStateProviderType<?> n_1700_B() {
        return BlockStateProviderType.G_564_y;
    }

    @Override
    public K_4074_S n_1700_B(Random randomIn, c_1514_x blockPosIn) {
        double d0 = u_530_F.n_1700_B((1.0 + k_594_Q.u_1723_Y.n_1700_B((double)blockPosIn.getX() / 48.0, (double)blockPosIn.getZ() / 48.0, false)) / 2.0, 0.0, 0.9999);
        return G_564_y[(int)(d0 * (double)G_564_y.length)];
    }

    static {
        G_564_y = new K_4074_S[]{a_3742_W.s_1671_u.multiplayerClientSuggestionProvider(), a_3742_W.RealmsResetNormalWorldScreen.multiplayerClientSuggestionProvider(), a_3742_W.A_3959_N.multiplayerClientSuggestionProvider(), a_3742_W.G_424_k.multiplayerClientSuggestionProvider(), a_3742_W.RealmsSettingsScreen.multiplayerClientSuggestionProvider(), a_3742_W.f_1043_S.multiplayerClientSuggestionProvider(), a_3742_W.F_4247_a.multiplayerClientSuggestionProvider(), a_3742_W.J_739_q.multiplayerClientSuggestionProvider(), a_3742_W.C_1162_e.multiplayerClientSuggestionProvider(), a_3742_W.D_4361_a.multiplayerClientSuggestionProvider(), a_3742_W.u_55_V.multiplayerClientSuggestionProvider()};
        R_4764_Y = new ForestFlowerProvider();
        n_1700_B = Codec.unit(() -> R_4764_Y);
    }
}


