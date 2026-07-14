/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;
import lightning.product.FormattedText;
import lightning.product.Y_4083_F;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.x_282_a;

public interface N_2445_q {
    public static final N_2445_q n_1700_B = new N_2445_q(){

        @Override
        public int n_1700_B(g_221_o p_241863_1_, int p_241863_2_, int p_241863_3_) {
            return p_241863_3_;
        }

        @Override
        public int n_1700_B(g_221_o p_241864_1_, int p_241864_2_, int p_241864_3_, int p_241864_4_, int p_241864_5_) {
            return p_241864_3_;
        }

        @Override
        public int J_1907_R(g_221_o p_241865_1_, int p_241865_2_, int p_241865_3_, int p_241865_4_, int p_241865_5_) {
            return p_241865_3_;
        }

        @Override
        public int R_4764_Y(g_221_o p_241866_1_, int p_241866_2_, int p_241866_3_, int p_241866_4_, int p_241866_5_) {
            return p_241866_3_;
        }

        @Override
        public int n_1700_B() {
            return 0;
        }
    };

    public static N_2445_q n_1700_B(Y_4083_F p_243258_0_, FormattedText p_243258_1_, int p_243258_2_) {
        return N_2445_q.n_1700_B(p_243258_0_, (List)p_243258_0_.J_1907_R(p_243258_1_, p_243258_2_).stream().map(p_243264_1_ -> new n_1700_B((FormattedCharSequence)p_243264_1_, p_243258_0_.n_1700_B((FormattedCharSequence)p_243264_1_))).collect(ImmutableList.toImmutableList()));
    }

    public static N_2445_q n_1700_B(Y_4083_F p_243259_0_, FormattedText p_243259_1_, int p_243259_2_, int p_243259_3_) {
        return N_2445_q.n_1700_B(p_243259_0_, (List)p_243259_0_.J_1907_R(p_243259_1_, p_243259_2_).stream().limit(p_243259_3_).map(p_243263_1_ -> new n_1700_B((FormattedCharSequence)p_243263_1_, p_243259_0_.n_1700_B((FormattedCharSequence)p_243263_1_))).collect(ImmutableList.toImmutableList()));
    }

    public static N_2445_q n_1700_B(Y_4083_F p_243260_0_, x_282_a ... p_243260_1_) {
        return N_2445_q.n_1700_B(p_243260_0_, (List)Arrays.stream(p_243260_1_).map(x_282_a::u_1723_Y).map(p_243261_1_ -> new n_1700_B((FormattedCharSequence)p_243261_1_, p_243260_0_.n_1700_B((FormattedCharSequence)p_243261_1_))).collect(ImmutableList.toImmutableList()));
    }

    public static N_2445_q n_1700_B(final Y_4083_F p_243262_0_, final List<n_1700_B> p_243262_1_) {
        return p_243262_1_.isEmpty() ? n_1700_B : new N_2445_q(){

            @Override
            public int n_1700_B(g_221_o p_241863_1_, int p_241863_2_, int p_241863_3_) {
                return this.n_1700_B(p_241863_1_, p_241863_2_, p_241863_3_, 9, 0xFFFFFF);
            }

            @Override
            public int n_1700_B(g_221_o p_241864_1_, int p_241864_2_, int p_241864_3_, int p_241864_4_, int p_241864_5_) {
                int i = p_241864_3_;
                for (n_1700_B ibidirenderer$entry : p_243262_1_) {
                    p_243262_0_.n_1700_B(p_241864_1_, ibidirenderer$entry.n_1700_B, (float)(p_241864_2_ - ibidirenderer$entry.J_1907_R / 2), (float)i, p_241864_5_);
                    i += p_241864_4_;
                }
                return i;
            }

            @Override
            public int J_1907_R(g_221_o p_241865_1_, int p_241865_2_, int p_241865_3_, int p_241865_4_, int p_241865_5_) {
                int i = p_241865_3_;
                for (n_1700_B ibidirenderer$entry : p_243262_1_) {
                    p_243262_0_.n_1700_B(p_241865_1_, ibidirenderer$entry.n_1700_B, (float)p_241865_2_, (float)i, p_241865_5_);
                    i += p_241865_4_;
                }
                return i;
            }

            @Override
            public int R_4764_Y(g_221_o p_241866_1_, int p_241866_2_, int p_241866_3_, int p_241866_4_, int p_241866_5_) {
                int i = p_241866_3_;
                for (n_1700_B ibidirenderer$entry : p_243262_1_) {
                    p_243262_0_.J_1907_R(p_241866_1_, ibidirenderer$entry.n_1700_B, (float)p_241866_2_, (float)i, p_241866_5_);
                    i += p_241866_4_;
                }
                return i;
            }

            @Override
            public int n_1700_B() {
                return p_243262_1_.size();
            }
        };
    }

    public int n_1700_B(g_221_o var1, int var2, int var3);

    public int n_1700_B(g_221_o var1, int var2, int var3, int var4, int var5);

    public int J_1907_R(g_221_o var1, int var2, int var3, int var4, int var5);

    public int R_4764_Y(g_221_o var1, int var2, int var3, int var4, int var5);

    public int n_1700_B();

    public static class n_1700_B {
        private final FormattedCharSequence n_1700_B;
        private final int J_1907_R;

        private n_1700_B(FormattedCharSequence p_i242052_1_, int p_i242052_2_) {
            this.n_1700_B = p_i242052_1_;
            this.J_1907_R = p_i242052_2_;
        }
    }
}


