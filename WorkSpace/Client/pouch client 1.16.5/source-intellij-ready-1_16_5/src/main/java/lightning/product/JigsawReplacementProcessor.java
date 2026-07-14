/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.StructureProcessor;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.f_71_T;
import lightning.product.StructureProcessorType;
import lightning.product.w_1748_S;

public class JigsawReplacementProcessor
extends StructureProcessor {
    public static final Codec<JigsawReplacementProcessor> n_1700_B;
    public static final JigsawReplacementProcessor J_1907_R;

    private JigsawReplacementProcessor() {
    }

    @Override
    @Nullable
    public a_2886_t.J_1907_R n_1700_B(T_1316_M p_230386_1_, c_1514_x p_230386_2_, c_1514_x p_230386_3_, a_2886_t.J_1907_R p_230386_4_, a_2886_t.J_1907_R p_230386_5_, w_1748_S p_230386_6_) {
        K_4074_S blockstate = p_230386_5_.J_1907_R;
        if (blockstate.n_1700_B(a_3742_W.q_2034_t)) {
            String s = p_230386_5_.R_4764_Y.M_588_G("final_state");
            f_71_T blockstateparser = new f_71_T(new StringReader(s), false);
            try {
                blockstateparser.n_1700_B(true);
            }
            catch (CommandSyntaxException commandsyntaxexception) {
                throw new RuntimeException(commandsyntaxexception);
            }
            return blockstateparser.J_1907_R().n_1700_B(a_3742_W.PearlLogger) ? null : new a_2886_t.J_1907_R(p_230386_5_.n_1700_B, blockstateparser.J_1907_R(), null);
        }
        return p_230386_5_;
    }

    @Override
    protected StructureProcessorType<?> n_1700_B() {
        return StructureProcessorType.G_564_y;
    }

    static {
        J_1907_R = new JigsawReplacementProcessor();
        n_1700_B = Codec.unit(() -> J_1907_R);
    }
}



