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
import lightning.product.j_3341_s;
import lightning.product.k_594_Q;

public class V_1684_a
extends BlockStateProvider {
    public static final Codec<V_1684_a> n_1700_B;
    public static final V_1684_a R_4764_Y;
    private static final K_4074_S[] G_564_y;
    private static final K_4074_S[] P_1922_E;

    @Override
    protected BlockStateProviderType<?> n_1700_B() {
        return BlockStateProviderType.R_4764_Y;
    }

    @Override
    public K_4074_S n_1700_B(Random randomIn, c_1514_x blockPosIn) {
        double d0 = k_594_Q.u_1723_Y.n_1700_B((double)blockPosIn.getX() / 200.0, (double)blockPosIn.getZ() / 200.0, false);
        if (d0 < -0.8) {
            return j_3341_s.n_1700_B(G_564_y, randomIn);
        }
        return randomIn.nextInt(3) > 0 ? j_3341_s.n_1700_B(P_1922_E, randomIn) : a_3742_W.s_1671_u.multiplayerClientSuggestionProvider();
    }

    static {
        R_4764_Y = new V_1684_a();
        G_564_y = new K_4074_S[]{a_3742_W.f_1043_S.multiplayerClientSuggestionProvider(), a_3742_W.RealmsSettingsScreen.multiplayerClientSuggestionProvider(), a_3742_W.J_739_q.multiplayerClientSuggestionProvider(), a_3742_W.F_4247_a.multiplayerClientSuggestionProvider()};
        P_1922_E = new K_4074_S[]{a_3742_W.RealmsResetNormalWorldScreen.multiplayerClientSuggestionProvider(), a_3742_W.G_424_k.multiplayerClientSuggestionProvider(), a_3742_W.C_1162_e.multiplayerClientSuggestionProvider(), a_3742_W.D_4361_a.multiplayerClientSuggestionProvider()};
        n_1700_B = Codec.unit(() -> R_4764_Y);
    }
}


