/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.MapCodec
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import java.util.concurrent.atomic.AtomicInteger;
import lightning.product.BlockGetter;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;
import lightning.product.q_4293_E;
import lightning.product.v_3760_Q;
import net.minecraftforge.common.extensions.IForgeBlockState;
import net.optifine.Config;
import net.optifine.util.BlockUtils;

public class K_4074_S
extends q_4293_E.n_1700_B
implements IForgeBlockState {
    public static final Codec<K_4074_S> J_1907_R = K_4074_S.n_1700_B(V_3137_a.q_4610_l, T_2915_h::multiplayerClientSuggestionProvider).stable();
    private int P_1922_E = -1;
    private int u_1723_Y = -1;
    private g_2336_b v_4262_N;
    private int w_1484_f = -1;
    private static final AtomicInteger t_148_a = new AtomicInteger(0);

    public int multiplayerClientSuggestionProvider() {
        if (this.P_1922_E < 0) {
            this.P_1922_E = V_3137_a.q_4610_l.n_1700_B(this.J_1907_R());
        }
        return this.P_1922_E;
    }

    public int w_1457_N() {
        if (this.u_1723_Y < 0) {
            this.u_1723_Y = BlockUtils.getMetadata(this);
            if (this.u_1723_Y < 0) {
                Config.warn("Metadata not found, block: " + String.valueOf(this.Y_601_j()));
                this.u_1723_Y = 0;
            }
        }
        return this.u_1723_Y;
    }

    public g_2336_b Y_601_j() {
        if (this.v_4262_N == null) {
            this.v_4262_N = V_3137_a.q_4610_l.J_1907_R(this.J_1907_R());
        }
        return this.v_4262_N;
    }

    public int Y_259_p() {
        if (this.w_1484_f < 0) {
            this.w_1484_f = t_148_a.incrementAndGet();
        }
        return this.w_1484_f;
    }

    public int w_1457_N(BlockGetter p_getLightValue_1_, c_1514_x p_getLightValue_2_) {
        return this.u_1723_Y();
    }

    public boolean Q_2552_b() {
        return this.n_1700_B != null && this.n_1700_B.n_1700_B;
    }

    public boolean C_2741_M() {
        return this.n_1700_B != null && this.n_1700_B.G_564_y;
    }

    public K_4074_S(T_2915_h block, ImmutableMap<v_3760_Q<?>, Comparable<?>> propertiesToValueMap, MapCodec<K_4074_S> codec) {
        super(block, propertiesToValueMap, codec);
    }

    @Override
    protected K_4074_S M_182_A() {
        return this;
    }
}


