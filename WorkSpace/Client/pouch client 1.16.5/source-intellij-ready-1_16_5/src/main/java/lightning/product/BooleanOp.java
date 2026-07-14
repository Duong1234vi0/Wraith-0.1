/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public interface BooleanOp {
    public static final BooleanOp n_1700_B = (p_223272_0_, p_223272_1_) -> false;
    public static final BooleanOp J_1907_R = (p_223271_0_, p_223271_1_) -> !p_223271_0_ && !p_223271_1_;
    public static final BooleanOp R_4764_Y = (p_223270_0_, p_223270_1_) -> p_223270_1_ && !p_223270_0_;
    public static final BooleanOp G_564_y = (p_223269_0_, p_223269_1_) -> !p_223269_0_;
    public static final BooleanOp P_1922_E = (p_223268_0_, p_223268_1_) -> p_223268_0_ && !p_223268_1_;
    public static final BooleanOp u_1723_Y = (p_223267_0_, p_223267_1_) -> !p_223267_1_;
    public static final BooleanOp v_4262_N = (p_223266_0_, p_223266_1_) -> p_223266_0_ != p_223266_1_;
    public static final BooleanOp w_1484_f = (p_223265_0_, p_223265_1_) -> !p_223265_0_ || !p_223265_1_;
    public static final BooleanOp t_148_a = (p_223264_0_, p_223264_1_) -> p_223264_0_ && p_223264_1_;
    public static final BooleanOp s_956_w = (p_223263_0_, p_223263_1_) -> p_223263_0_ == p_223263_1_;
    public static final BooleanOp u_2550_I = (p_223262_0_, p_223262_1_) -> p_223262_1_;
    public static final BooleanOp M_588_G = (p_223261_0_, p_223261_1_) -> !p_223261_0_ || p_223261_1_;
    public static final BooleanOp P_4830_p = (p_223260_0_, p_223260_1_) -> p_223260_0_;
    public static final BooleanOp h_1847_R = (p_223259_0_, p_223259_1_) -> p_223259_0_ || !p_223259_1_;
    public static final BooleanOp Q_4569_t = (p_223258_0_, p_223258_1_) -> p_223258_0_ || p_223258_1_;
    public static final BooleanOp M_182_A = (p_223257_0_, p_223257_1_) -> true;

    public boolean apply(boolean var1, boolean var2);
}


