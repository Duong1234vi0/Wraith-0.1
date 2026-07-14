/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Either
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import java.util.function.Supplier;
import lightning.product.GravityProcessor;
import lightning.product.JigsawReplacementProcessor;
import lightning.product.BlockAgeProcessor;
import lightning.product.NopProcessor;
import lightning.product.V_3137_a;
import lightning.product.StructureProcessor;
import lightning.product.d_862_x;
import lightning.product.RuleProcessor;
import lightning.product.n_2313_d;
import lightning.product.n_4684_C;
import lightning.product.r_3979_x_0;
import lightning.product.r_4719_P;
import lightning.product.LavaSubmergedBlockProcessor;

public interface StructureProcessorType<P extends StructureProcessor> {
    public static final StructureProcessorType<r_4719_P> n_1700_B = StructureProcessorType.n_1700_B("block_ignore", r_4719_P.n_1700_B);
    public static final StructureProcessorType<d_862_x> J_1907_R = StructureProcessorType.n_1700_B("block_rot", d_862_x.n_1700_B);
    public static final StructureProcessorType<GravityProcessor> R_4764_Y = StructureProcessorType.n_1700_B("gravity", GravityProcessor.n_1700_B);
    public static final StructureProcessorType<JigsawReplacementProcessor> G_564_y = StructureProcessorType.n_1700_B("jigsaw_replacement", JigsawReplacementProcessor.n_1700_B);
    public static final StructureProcessorType<RuleProcessor> P_1922_E = StructureProcessorType.n_1700_B("rule", RuleProcessor.n_1700_B);
    public static final StructureProcessorType<NopProcessor> u_1723_Y = StructureProcessorType.n_1700_B("nop", NopProcessor.n_1700_B);
    public static final StructureProcessorType<BlockAgeProcessor> v_4262_N = StructureProcessorType.n_1700_B("block_age", BlockAgeProcessor.n_1700_B);
    public static final StructureProcessorType<n_2313_d> w_1484_f = StructureProcessorType.n_1700_B("blackstone_replace", n_2313_d.n_1700_B);
    public static final StructureProcessorType<LavaSubmergedBlockProcessor> t_148_a = StructureProcessorType.n_1700_B("lava_submerged_block", LavaSubmergedBlockProcessor.n_1700_B);
    public static final Codec<StructureProcessor> s_956_w = V_3137_a.c_132_F.dispatch("processor_type", StructureProcessor::n_1700_B, StructureProcessorType::codec);
    public static final Codec<r_3979_x_0> u_2550_I = s_956_w.listOf().xmap(r_3979_x_0::new, r_3979_x_0::n_1700_B);
    public static final Codec<r_3979_x_0> M_588_G = Codec.either((Codec)u_2550_I.fieldOf("processors").codec(), u_2550_I).xmap(p_242923_0_ -> (r_3979_x_0)p_242923_0_.map(p_242926_0_ -> p_242926_0_, p_242925_0_ -> p_242925_0_), Either::left);
    public static final Codec<Supplier<r_3979_x_0>> P_4830_p = n_4684_C.n_1700_B(V_3137_a.t_4219_U, M_588_G);

    public Codec<P> codec();

    public static <P extends StructureProcessor> StructureProcessorType<P> n_1700_B(String p_237139_0_, Codec<P> p_237139_1_) {
        return V_3137_a.n_1700_B(V_3137_a.c_132_F, p_237139_0_, () -> p_237139_1_);
    }
}


