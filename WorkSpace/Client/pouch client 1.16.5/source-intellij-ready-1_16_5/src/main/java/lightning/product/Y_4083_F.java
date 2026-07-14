/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.ibm.icu.text.ArabicShaping
 *  com.ibm.icu.text.ArabicShapingException
 *  com.ibm.icu.text.Bidi
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.StringDecomposer;
import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.F_3283_z;
import lightning.product.FormattedText;
import lightning.product.J_2133_L;
import lightning.product.TextColor;
import lightning.product.M_1336_P;
import lightning.product.FormattedCharSink;
import lightning.product.U_2871_b;
import lightning.product.Z_1567_W;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.f_1703_u;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.l_3747_P;
import lightning.product.l_4033_W;
import lightning.product.Transformation;
import lightning.product.o_3091_w;
import lightning.product.EmptyGlyph;
import lightning.product.u_530_F;
import lightning.product.v_143_j;
import lightning.product.x_282_a;
import lightning.product.y_3462_t;
import net.optifine.render.GlBlendState;
import net.optifine.util.GlyphAdvanceFixed;
import net.optifine.util.GuiPoint;

public class Y_4083_F {
    private static final M_1336_P R_4764_Y = new M_1336_P(0.0f, 0.0f, 0.03f);
    public final int n_1700_B = 9;
    public final Random J_1907_R = new Random();
    private final Function<g_2336_b, F_3283_z> G_564_y;
    private final f_1703_u P_1922_E;
    private boolean u_1723_Y = false;
    private GlBlendState v_4262_N = new GlBlendState();
    private y_3462_t w_1484_f = new GlyphAdvanceFixed(4.0f);

    public Y_4083_F(Function<g_2336_b, F_3283_z> p_i232249_1_) {
        this.G_564_y = p_i232249_1_;
        this.P_1922_E = new f_1703_u((p_lambda$new$0_1_, p_lambda$new$0_2_) -> this.n_1700_B(p_lambda$new$0_2_.u_2550_I()).n_1700_B(p_lambda$new$0_1_).n_1700_B(p_lambda$new$0_2_.J_1907_R()));
    }

    private F_3283_z n_1700_B(g_2336_b fontLocation) {
        return this.G_564_y.apply(fontLocation);
    }

    public int n_1700_B(g_221_o p_238405_1_, String p_238405_2_, float p_238405_3_, float p_238405_4_, int p_238405_5_) {
        return this.n_1700_B(p_238405_2_, p_238405_3_, p_238405_4_, p_238405_5_, p_238405_1_.R_4764_Y().n_1700_B(), true, this.n_1700_B());
    }

    public int n_1700_B(g_221_o p_238406_1_, String p_238406_2_, float p_238406_3_, float p_238406_4_, int p_238406_5_, boolean p_238406_6_) {
        c_4037_x.M_588_G();
        return this.n_1700_B(p_238406_2_, p_238406_3_, p_238406_4_, p_238406_5_, p_238406_1_.R_4764_Y().n_1700_B(), true, p_238406_6_);
    }

    public int J_1907_R(g_221_o matrixStack, String text, float x, float y, int color) {
        c_4037_x.M_588_G();
        return this.n_1700_B(text, x, y, color, matrixStack.R_4764_Y().n_1700_B(), false, this.n_1700_B());
    }

    public int n_1700_B(g_221_o p_238407_1_, FormattedCharSequence p_238407_2_, float p_238407_3_, float p_238407_4_, int p_238407_5_) {
        c_4037_x.M_588_G();
        return this.n_1700_B(p_238407_2_, p_238407_3_, p_238407_4_, p_238407_5_, p_238407_1_.R_4764_Y().n_1700_B(), true);
    }

    public int n_1700_B(g_221_o p_243246_1_, x_282_a p_243246_2_, float p_243246_3_, float p_243246_4_, int p_243246_5_) {
        c_4037_x.M_588_G();
        p_243246_2_ = this.n_1700_B(p_243246_2_);
        return this.n_1700_B(p_243246_2_.u_1723_Y(), p_243246_3_, p_243246_4_, p_243246_5_, p_243246_1_.R_4764_Y().n_1700_B(), true);
    }

    public int J_1907_R(g_221_o matrixStack, FormattedCharSequence properties, float x, float y, int color) {
        c_4037_x.M_588_G();
        return this.n_1700_B(properties, x, y, color, matrixStack.R_4764_Y().n_1700_B(), false);
    }

    public int J_1907_R(g_221_o p_243248_1_, x_282_a p_243248_2_, float p_243248_3_, float p_243248_4_, int p_243248_5_) {
        c_4037_x.M_588_G();
        p_243248_2_ = this.n_1700_B(p_243248_2_);
        return this.n_1700_B(p_243248_2_.u_1723_Y(), p_243248_3_, p_243248_4_, p_243248_5_, p_243248_1_.R_4764_Y().n_1700_B(), false);
    }

    private x_282_a n_1700_B(x_282_a component) {
        if (component == null) {
            return null;
        }
        String original = component.getString();
        String fixed = v_143_j.n_1700_B(original);
        if (!fixed.equals(original)) {
            return new U_2871_b(fixed);
        }
        return component;
    }

    public String n_1700_B(String text) {
        try {
            Bidi bidi = new Bidi(new ArabicShaping(8).shape(text), 127);
            bidi.setReorderingMode(0);
            return bidi.writeReordered(2);
        }
        catch (ArabicShapingException arabicshapingexception1) {
            return text;
        }
    }

    private int n_1700_B(String text, float x, float y, int color, D_1098_v matrix, boolean dropShadow, boolean p_228078_7_) {
        if (text == null) {
            return 0;
        }
        text = v_143_j.n_1700_B(text);
        o_3091_w.n_1700_B irendertypebuffer$impl = o_3091_w.n_1700_B(l_3747_P.n_1700_B().R_4764_Y());
        int i = this.n_1700_B(text, x, y, color, dropShadow, matrix, irendertypebuffer$impl, false, 0, 0xF000F0, p_228078_7_);
        irendertypebuffer$impl.J_1907_R();
        return i;
    }

    public void n_1700_B(List<String> p_renderStrings_1_, GuiPoint[] p_renderStrings_2_, int p_renderStrings_3_, D_1098_v p_renderStrings_4_, boolean p_renderStrings_5_, boolean p_renderStrings_6_) {
        o_3091_w.n_1700_B irendertypebuffer$impl = o_3091_w.n_1700_B(l_3747_P.n_1700_B().R_4764_Y());
        for (int i = 0; i < p_renderStrings_1_.size(); ++i) {
            GuiPoint guipoint;
            String s = p_renderStrings_1_.get(i);
            if (s == null || s.isEmpty() || (guipoint = p_renderStrings_2_[i]) == null) continue;
            float f = guipoint.getX();
            float f1 = guipoint.getY();
            this.n_1700_B(s, f, f1, p_renderStrings_3_, p_renderStrings_5_, p_renderStrings_4_, irendertypebuffer$impl, false, 0, 0xF000F0, p_renderStrings_6_);
        }
        irendertypebuffer$impl.J_1907_R();
    }

    private int n_1700_B(FormattedCharSequence reorderingProcessor, float x, float y, int color, D_1098_v matrix, boolean p_238415_6_) {
        o_3091_w.n_1700_B irendertypebuffer$impl = o_3091_w.n_1700_B(l_3747_P.n_1700_B().R_4764_Y());
        int i = this.n_1700_B(reorderingProcessor, x, y, color, p_238415_6_, matrix, (o_3091_w)irendertypebuffer$impl, false, 0, 0xF000F0);
        irendertypebuffer$impl.J_1907_R();
        return i;
    }

    public int n_1700_B(String text, float x, float y, int color, boolean dropShadow, D_1098_v matrix, o_3091_w buffer, boolean transparentIn, int colorBackgroundIn, int packedLight) {
        return this.n_1700_B(text, x, y, color, dropShadow, matrix, buffer, transparentIn, colorBackgroundIn, packedLight, this.n_1700_B());
    }

    public int n_1700_B(String p_238411_1_, float p_238411_2_, float p_238411_3_, int p_238411_4_, boolean p_238411_5_, D_1098_v p_238411_6_, o_3091_w p_238411_7_, boolean p_238411_8_, int p_238411_9_, int p_238411_10_, boolean p_238411_11_) {
        return this.J_1907_R(p_238411_1_, p_238411_2_, p_238411_3_, p_238411_4_, p_238411_5_, p_238411_6_, p_238411_7_, p_238411_8_, p_238411_9_, p_238411_10_, p_238411_11_);
    }

    public int n_1700_B(x_282_a p_243247_1_, float p_243247_2_, float p_243247_3_, int p_243247_4_, boolean p_243247_5_, D_1098_v p_243247_6_, o_3091_w p_243247_7_, boolean p_243247_8_, int p_243247_9_, int p_243247_10_) {
        return this.n_1700_B(p_243247_1_.u_1723_Y(), p_243247_2_, p_243247_3_, p_243247_4_, p_243247_5_, p_243247_6_, p_243247_7_, p_243247_8_, p_243247_9_, p_243247_10_);
    }

    public int n_1700_B(FormattedCharSequence processor, float x, float y, int color, boolean dropShadow, D_1098_v matrix, o_3091_w buffer, boolean transparent, int colorBackground, int packedLight) {
        return this.J_1907_R(processor, x, y, color, dropShadow, matrix, buffer, transparent, colorBackground, packedLight);
    }

    private static int n_1700_B(int p_238403_0_) {
        return (p_238403_0_ & 0xFC000000) == 0 ? p_238403_0_ | 0xFF000000 : p_238403_0_;
    }

    private int J_1907_R(String text, float x, float y, int color, boolean p_238423_5_, D_1098_v matrix, o_3091_w buffer, boolean transparent, int p_238423_9_, int p_238423_10_, boolean p_238423_11_) {
        if (p_238423_11_) {
            text = this.n_1700_B(text);
        }
        color = Y_4083_F.n_1700_B(color);
        D_1098_v matrix4f = matrix.u_1723_Y();
        if (p_238423_5_) {
            this.J_1907_R(text, x, y, color, true, matrix, buffer, transparent, p_238423_9_, p_238423_10_);
            matrix4f.n_1700_B(R_4764_Y);
        }
        x = this.J_1907_R(text, x, y, color, false, matrix4f, buffer, transparent, p_238423_9_, p_238423_10_);
        return (int)x + (p_238423_5_ ? 1 : 0);
    }

    private int J_1907_R(FormattedCharSequence p_238424_1_, float x, float y, int color, boolean p_238424_5_, D_1098_v matrix, o_3091_w buffer, boolean p_238424_8_, int p_238424_9_, int p_238424_10_) {
        color = Y_4083_F.n_1700_B(color);
        D_1098_v matrix4f = matrix.u_1723_Y();
        if (p_238424_5_) {
            this.R_4764_Y(p_238424_1_, x, y, color, true, matrix, buffer, p_238424_8_, p_238424_9_, p_238424_10_);
            matrix4f.n_1700_B(R_4764_Y);
        }
        x = this.R_4764_Y(p_238424_1_, x, y, color, false, matrix4f, buffer, p_238424_8_, p_238424_9_, p_238424_10_);
        return (int)x + (p_238424_5_ ? 1 : 0);
    }

    private float J_1907_R(String text, float x, float y, int color, boolean isShadow, D_1098_v matrix, o_3091_w buffer, boolean isTransparent, int colorBackgroundIn, int packedLight) {
        n_1700_B fontrenderer$characterrenderer = new n_1700_B(buffer, x, y, color, isShadow, matrix, isTransparent, packedLight);
        StringDecomposer.R_4764_Y(text, Z_1567_W.n_1700_B, fontrenderer$characterrenderer);
        return fontrenderer$characterrenderer.n_1700_B(colorBackgroundIn, x);
    }

    private float R_4764_Y(FormattedCharSequence p_238426_1_, float p_238426_2_, float p_238426_3_, int p_238426_4_, boolean p_238426_5_, D_1098_v p_238426_6_, o_3091_w p_238426_7_, boolean p_238426_8_, int p_238426_9_, int p_238426_10_) {
        n_1700_B fontrenderer$characterrenderer = new n_1700_B(p_238426_7_, p_238426_2_, p_238426_3_, p_238426_4_, p_238426_5_, p_238426_6_, p_238426_8_, p_238426_10_);
        p_238426_1_.accept(fontrenderer$characterrenderer);
        return fontrenderer$characterrenderer.n_1700_B(p_238426_9_, p_238426_2_);
    }

    private void n_1700_B(J_2133_L glyphIn, boolean boldIn, boolean italicIn, float boldOffsetIn, float xIn, float yIn, D_1098_v matrix, D_4792_h bufferIn, float redIn, float greenIn, float blueIn, float alphaIn, int packedLight) {
        glyphIn.n_1700_B(italicIn, xIn, yIn, matrix, bufferIn, redIn, greenIn, blueIn, alphaIn, packedLight);
        if (boldIn) {
            glyphIn.n_1700_B(italicIn, xIn + boldOffsetIn, yIn, matrix, bufferIn, redIn, greenIn, blueIn, alphaIn, packedLight);
        }
    }

    public int J_1907_R(String text) {
        return u_530_F.u_1723_Y(this.P_1922_E.n_1700_B(text));
    }

    public int n_1700_B(FormattedText properties) {
        return u_530_F.u_1723_Y(this.P_1922_E.n_1700_B(properties));
    }

    public int n_1700_B(FormattedCharSequence processor) {
        return u_530_F.u_1723_Y(this.P_1922_E.n_1700_B(processor));
    }

    public String n_1700_B(String p_238413_1_, int p_238413_2_, boolean p_238413_3_) {
        return p_238413_3_ ? this.P_1922_E.R_4764_Y(p_238413_1_, p_238413_2_, Z_1567_W.n_1700_B) : this.P_1922_E.J_1907_R(p_238413_1_, p_238413_2_, Z_1567_W.n_1700_B);
    }

    public String n_1700_B(String p_238412_1_, int p_238412_2_) {
        return this.P_1922_E.J_1907_R(p_238412_1_, p_238412_2_, Z_1567_W.n_1700_B);
    }

    public FormattedText n_1700_B(FormattedText properties, int maxLength) {
        return this.P_1922_E.n_1700_B(properties, maxLength, Z_1567_W.n_1700_B);
    }

    public void n_1700_B(FormattedText text, int x, int y, int maxLength, int color) {
        D_1098_v matrix4f = Transformation.n_1700_B().R_4764_Y();
        for (FormattedCharSequence ireorderingprocessor : this.J_1907_R(text, maxLength)) {
            this.n_1700_B(ireorderingprocessor, x, (float)y, color, matrix4f, false);
            y += 9;
        }
    }

    public int J_1907_R(String str, int maxLength) {
        return 9 * this.P_1922_E.G_564_y(str, maxLength, Z_1567_W.n_1700_B).size();
    }

    public List<FormattedCharSequence> J_1907_R(FormattedText p_238425_1_, int p_238425_2_) {
        return l_4033_W.R_4764_Y().n_1700_B(this.P_1922_E.J_1907_R(p_238425_1_, p_238425_2_, Z_1567_W.n_1700_B));
    }

    public boolean n_1700_B() {
        return l_4033_W.R_4764_Y().n_1700_B();
    }

    public f_1703_u J_1907_R() {
        return this.P_1922_E;
    }

    class n_1700_B
    implements FormattedCharSink {
        final o_3091_w n_1700_B;
        private final boolean R_4764_Y;
        private final float G_564_y;
        private final float P_1922_E;
        private final float u_1723_Y;
        private final float v_4262_N;
        private final float w_1484_f;
        private final D_1098_v t_148_a;
        private final boolean s_956_w;
        private final int u_2550_I;
        private float M_588_G;
        private float P_4830_p;
        @Nullable
        private List<J_2133_L.n_1700_B> h_1847_R;
        private Z_1567_W Q_4569_t;
        private F_3283_z M_182_A;

        private void n_1700_B(J_2133_L.n_1700_B p_238442_1_) {
            if (this.h_1847_R == null) {
                this.h_1847_R = Lists.newArrayList();
            }
            this.h_1847_R.add(p_238442_1_);
        }

        public n_1700_B(o_3091_w p_i232250_2_, float p_i232250_3_, float p_i232250_4_, int p_i232250_5_, boolean p_i232250_6_, D_1098_v p_i232250_7_, boolean p_i232250_8_, int p_i232250_9_) {
            this.n_1700_B = p_i232250_2_;
            this.M_588_G = p_i232250_3_;
            this.P_4830_p = p_i232250_4_;
            this.R_4764_Y = p_i232250_6_;
            this.G_564_y = p_i232250_6_ ? 0.25f : 1.0f;
            this.P_1922_E = (float)(p_i232250_5_ >> 16 & 0xFF) / 255.0f * this.G_564_y;
            this.u_1723_Y = (float)(p_i232250_5_ >> 8 & 0xFF) / 255.0f * this.G_564_y;
            this.v_4262_N = (float)(p_i232250_5_ & 0xFF) / 255.0f * this.G_564_y;
            this.w_1484_f = (float)(p_i232250_5_ >> 24 & 0xFF) / 255.0f;
            this.t_148_a = p_i232250_7_.J_1907_R() ? J_2133_L.n_1700_B : p_i232250_7_;
            this.s_956_w = p_i232250_8_;
            this.u_2550_I = p_i232250_9_;
        }

        @Override
        public boolean accept(int p_accept_1_, Z_1567_W p_accept_2_, int p_accept_3_) {
            float f7;
            float f3;
            float f2;
            float f1;
            F_3283_z font = this.n_1700_B(p_accept_2_);
            y_3462_t iglyph = font.n_1700_B(p_accept_3_);
            J_2133_L texturedglyph = p_accept_2_.u_1723_Y() && p_accept_3_ != 32 ? font.n_1700_B(iglyph) : font.J_1907_R(p_accept_3_);
            boolean flag = p_accept_2_.J_1907_R();
            float f = this.w_1484_f;
            TextColor color = p_accept_2_.n_1700_B();
            if (color != null) {
                int i = color.n_1700_B();
                f1 = (float)(i >> 16 & 0xFF) / 255.0f * this.G_564_y;
                f2 = (float)(i >> 8 & 0xFF) / 255.0f * this.G_564_y;
                f3 = (float)(i & 0xFF) / 255.0f * this.G_564_y;
            } else {
                f1 = this.P_1922_E;
                f2 = this.u_1723_Y;
                f3 = this.v_4262_N;
            }
            if (!(texturedglyph instanceof EmptyGlyph)) {
                float f5 = flag ? iglyph.u_1723_Y() : 0.0f;
                float f4 = this.R_4764_Y ? iglyph.v_4262_N() : 0.0f;
                D_4792_h ivertexbuilder = this.n_1700_B.getBuffer(texturedglyph.n_1700_B(this.s_956_w));
                Y_4083_F.this.n_1700_B(texturedglyph, flag, p_accept_2_.R_4764_Y(), f5, this.M_588_G + f4, this.P_4830_p + f4, this.t_148_a, ivertexbuilder, f1, f2, f3, f, this.u_2550_I);
            }
            float f6 = iglyph.n_1700_B(flag);
            float f4 = f7 = this.R_4764_Y ? 1.0f : 0.0f;
            if (p_accept_2_.G_564_y()) {
                this.n_1700_B(new J_2133_L.n_1700_B(this.M_588_G + f7 - 1.0f, this.P_4830_p + f7 + 4.5f, this.M_588_G + f7 + f6, this.P_4830_p + f7 + 4.5f - 1.0f, 0.01f, f1, f2, f3, f));
            }
            if (p_accept_2_.P_1922_E()) {
                this.n_1700_B(new J_2133_L.n_1700_B(this.M_588_G + f7 - 1.0f, this.P_4830_p + f7 + 9.0f, this.M_588_G + f7 + f6, this.P_4830_p + f7 + 9.0f - 1.0f, 0.01f, f1, f2, f3, f));
            }
            this.M_588_G += f6;
            return true;
        }

        public float n_1700_B(int p_238441_1_, float p_238441_2_) {
            if (p_238441_1_ != 0) {
                float f = (float)(p_238441_1_ >> 24 & 0xFF) / 255.0f;
                float f1 = (float)(p_238441_1_ >> 16 & 0xFF) / 255.0f;
                float f2 = (float)(p_238441_1_ >> 8 & 0xFF) / 255.0f;
                float f3 = (float)(p_238441_1_ & 0xFF) / 255.0f;
                this.n_1700_B(new J_2133_L.n_1700_B(p_238441_2_ - 1.0f, this.P_4830_p + 9.0f, this.M_588_G + 1.0f, this.P_4830_p - 1.0f, 0.01f, f1, f2, f3, f));
            }
            if (this.h_1847_R != null) {
                J_2133_L texturedglyph = Y_4083_F.this.n_1700_B(Z_1567_W.J_1907_R).n_1700_B();
                D_4792_h ivertexbuilder = this.n_1700_B.getBuffer(texturedglyph.n_1700_B(this.s_956_w));
                for (J_2133_L.n_1700_B texturedglyph$effect : this.h_1847_R) {
                    texturedglyph.n_1700_B(texturedglyph$effect, this.t_148_a, ivertexbuilder, this.u_2550_I);
                }
            }
            return this.M_588_G;
        }

        private F_3283_z n_1700_B(Z_1567_W p_getFont_1_) {
            if (p_getFont_1_ == this.Q_4569_t) {
                return this.M_182_A;
            }
            this.Q_4569_t = p_getFont_1_;
            this.M_182_A = Y_4083_F.this.n_1700_B(p_getFont_1_.u_2550_I());
            return this.M_182_A;
        }
    }
}


