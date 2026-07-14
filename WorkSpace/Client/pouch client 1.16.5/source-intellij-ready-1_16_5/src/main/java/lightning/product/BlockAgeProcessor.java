/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.StructureProcessor;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.m_2244_y;
import lightning.product.BlockTags;
import lightning.product.StructureProcessorType;
import lightning.product.w_1748_S;
import lightning.product.z_2909_G;

public class BlockAgeProcessor
extends StructureProcessor {
    public static final Codec<BlockAgeProcessor> n_1700_B = Codec.FLOAT.fieldOf("mossiness").xmap(BlockAgeProcessor::new, p_237064_0_ -> Float.valueOf(p_237064_0_.J_1907_R)).codec();
    private final float J_1907_R;

    public BlockAgeProcessor(float p_i232115_1_) {
        this.J_1907_R = p_i232115_1_;
    }

    @Override
    @Nullable
    public a_2886_t.J_1907_R n_1700_B(T_1316_M p_230386_1_, c_1514_x p_230386_2_, c_1514_x p_230386_3_, a_2886_t.J_1907_R p_230386_4_, a_2886_t.J_1907_R p_230386_5_, w_1748_S p_230386_6_) {
        Random random = p_230386_6_.J_1907_R(p_230386_5_.n_1700_B);
        K_4074_S blockstate = p_230386_5_.J_1907_R;
        c_1514_x blockpos = p_230386_5_.n_1700_B;
        K_4074_S blockstate1 = null;
        if (!(blockstate.n_1700_B(a_3742_W.f_691_R) || blockstate.n_1700_B(a_3742_W.J_1907_R) || blockstate.n_1700_B(a_3742_W.I_3457_f))) {
            if (blockstate.n_1700_B(BlockTags.Y_1740_V)) {
                blockstate1 = this.n_1700_B(random, p_230386_5_.J_1907_R);
            } else if (blockstate.n_1700_B(BlockTags.t_4043_B)) {
                blockstate1 = this.J_1907_R(random);
            } else if (blockstate.n_1700_B(BlockTags.x_607_J)) {
                blockstate1 = this.R_4764_Y(random);
            } else if (blockstate.n_1700_B(a_3742_W.ClientBootstrap)) {
                blockstate1 = this.G_564_y(random);
            }
        } else {
            blockstate1 = this.n_1700_B(random);
        }
        return blockstate1 != null ? new a_2886_t.J_1907_R(blockpos, blockstate1, p_230386_5_.R_4764_Y) : p_230386_5_;
    }

    @Nullable
    private K_4074_S n_1700_B(Random p_237065_1_) {
        if (p_237065_1_.nextFloat() >= 0.5f) {
            return null;
        }
        K_4074_S[] ablockstate = new K_4074_S[]{a_3742_W.g_1734_y.multiplayerClientSuggestionProvider(), BlockAgeProcessor.n_1700_B(p_237065_1_, a_3742_W.F_2860_q)};
        K_4074_S[] ablockstate1 = new K_4074_S[]{a_3742_W.I_4481_g.multiplayerClientSuggestionProvider(), BlockAgeProcessor.n_1700_B(p_237065_1_, a_3742_W.F_747_P)};
        return this.n_1700_B(p_237065_1_, ablockstate, ablockstate1);
    }

    @Nullable
    private K_4074_S n_1700_B(Random p_237067_1_, K_4074_S p_237067_2_) {
        b_257_Y direction = p_237067_2_.R_4764_Y(z_2909_G.P_4830_p);
        m_2244_y half = p_237067_2_.R_4764_Y(z_2909_G.h_1847_R);
        if (p_237067_1_.nextFloat() >= 0.5f) {
            return null;
        }
        K_4074_S[] ablockstate = new K_4074_S[]{a_3742_W.Jesus.multiplayerClientSuggestionProvider(), a_3742_W.Phase.multiplayerClientSuggestionProvider()};
        K_4074_S[] ablockstate1 = new K_4074_S[]{(K_4074_S)((K_4074_S)a_3742_W.F_747_P.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, direction)).n_1700_B(z_2909_G.h_1847_R, half), a_3742_W.O_1043_U.multiplayerClientSuggestionProvider()};
        return this.n_1700_B(p_237067_1_, ablockstate, ablockstate1);
    }

    @Nullable
    private K_4074_S J_1907_R(Random p_237070_1_) {
        return p_237070_1_.nextFloat() < this.J_1907_R ? a_3742_W.O_1043_U.multiplayerClientSuggestionProvider() : null;
    }

    @Nullable
    private K_4074_S R_4764_Y(Random p_237071_1_) {
        return p_237071_1_.nextFloat() < this.J_1907_R ? a_3742_W.t_4562_T.multiplayerClientSuggestionProvider() : null;
    }

    @Nullable
    private K_4074_S G_564_y(Random p_237072_1_) {
        return p_237072_1_.nextFloat() < 0.15f ? a_3742_W.MinMaxBounds.multiplayerClientSuggestionProvider() : null;
    }

    private static K_4074_S n_1700_B(Random p_237066_0_, T_2915_h p_237066_1_) {
        return (K_4074_S)((K_4074_S)p_237066_1_.multiplayerClientSuggestionProvider().n_1700_B(z_2909_G.P_4830_p, b_257_Y.R_4764_Y.n_1700_B.n_1700_B(p_237066_0_))).n_1700_B(z_2909_G.h_1847_R, m_2244_y.values()[p_237066_0_.nextInt(m_2244_y.values().length)]);
    }

    private K_4074_S n_1700_B(Random p_237069_1_, K_4074_S[] p_237069_2_, K_4074_S[] p_237069_3_) {
        return p_237069_1_.nextFloat() < this.J_1907_R ? BlockAgeProcessor.n_1700_B(p_237069_1_, p_237069_3_) : BlockAgeProcessor.n_1700_B(p_237069_1_, p_237069_2_);
    }

    private static K_4074_S n_1700_B(Random p_237068_0_, K_4074_S[] p_237068_1_) {
        return p_237068_1_[p_237068_0_.nextInt(p_237068_1_.length)];
    }

    @Override
    protected StructureProcessorType<?> n_1700_B() {
        return StructureProcessorType.v_4262_N;
    }
}



