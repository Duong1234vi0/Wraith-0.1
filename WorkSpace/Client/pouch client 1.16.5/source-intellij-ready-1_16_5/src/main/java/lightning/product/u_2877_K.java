/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Consumer;
import lightning.product.D_940_S;
import lightning.product.M_4239_y;
import lightning.product.P_4645_d;
import lightning.product.ResourceManager;
import lightning.product.S_4169_p;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.ReloadInstance;
import lightning.product.Overlay;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.i_4221_J;
import lightning.product.j_3341_s;
import lightning.product.TextureMetadataSection;
import lightning.product.u_530_F;
import lightning.product.y_4642_Y;
import net.optifine.Config;
import net.optifine.reflect.Reflector;
import net.optifine.render.GlBlendState;

public class u_2877_K
extends Overlay {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/title/mojangstudios.png");
    private static final int J_1907_R = M_4239_y.n_1700_B.n_1700_B(255, 239, 50, 61);
    private static final int R_4764_Y = J_1907_R & 0xFFFFFF;
    private final MinecraftClient G_564_y;
    private final ReloadInstance P_1922_E;
    private final Consumer<Optional<Throwable>> u_1723_Y;
    private final boolean v_4262_N;
    private float w_1484_f;
    private long t_148_a = -1L;
    private long s_956_w = -1L;
    private int u_2550_I = 0;
    private int M_588_G = R_4764_Y;
    private int P_4830_p = 0xFFFFFF;
    private int h_1847_R = 0xFFFFFF;
    private GlBlendState Q_4569_t = null;
    private boolean M_182_A = false;

    public u_2877_K(MinecraftClient p_i225928_1_, ReloadInstance p_i225928_2_, Consumer<Optional<Throwable>> p_i225928_3_, boolean p_i225928_4_) {
        this.G_564_y = p_i225928_1_;
        this.P_1922_E = p_i225928_2_;
        this.u_1723_Y = p_i225928_3_;
        this.v_4262_N = false;
    }

    public static void n_1700_B(MinecraftClient mc) {
        mc.G_624_v().n_1700_B(n_1700_B, new n_1700_B());
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        float f2;
        float f1;
        this.G_564_y.s_956_w.n_1700_B(2.0f);
        int i = this.G_564_y.RealmsServerPing().Q_4569_t();
        int j = this.G_564_y.RealmsServerPing().M_182_A();
        long k = j_3341_s.J_1907_R();
        if (this.v_4262_N && (this.P_1922_E.R_4764_Y() || this.G_564_y.Y_1740_V != null) && this.s_956_w == -1L) {
            this.s_956_w = k;
        }
        float f = this.t_148_a > -1L ? (float)(k - this.t_148_a) / 1000.0f : -1.0f;
        float f3 = f1 = this.s_956_w > -1L ? (float)(k - this.s_956_w) / 500.0f : -1.0f;
        if (f >= 1.0f) {
            this.M_182_A = true;
            if (this.G_564_y.Y_1740_V != null) {
                this.G_564_y.Y_1740_V.render(matrixStack, 0, 0, partialTicks);
            }
            f2 = 1.0f - u_530_F.n_1700_B(f - 1.0f, 0.0f, 1.0f);
        } else if (this.v_4262_N) {
            if (this.G_564_y.Y_1740_V != null && f1 < 1.0f) {
                this.G_564_y.Y_1740_V.render(matrixStack, mouseX, mouseY, partialTicks);
            }
            f2 = u_530_F.n_1700_B(f1, 0.0f, 1.0f);
        } else {
            f2 = 1.0f;
        }
        float f32 = this.P_1922_E.J_1907_R();
        this.w_1484_f = u_530_F.n_1700_B(this.w_1484_f * 0.95f + f32 * 0.050000012f, 0.0f, 1.0f);
        Reflector.ClientModLoader_renderProgressText.call(new Object[0]);
        float alphaBar = 1.0f - u_530_F.n_1700_B(f, 0.0f, 1.0f);
        if (y_4642_Y.R_4764_Y()) {
            int alpha = u_530_F.u_1723_Y(f2 * 255.0f);
            u_2877_K.fill(matrixStack, 0, 0, i, j, M_4239_y.n_1700_B.n_1700_B(alpha, 239, 50, 61));
            this.G_564_y.G_624_v().n_1700_B(n_1700_B);
            c_4037_x.Y_601_j();
            c_4037_x.R_4764_Y(32774);
            c_4037_x.J_1907_R(770, 1);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, f2);
            int s = (int)((double)i * 0.5);
            double d0 = Math.min((double)i * 0.75, (double)j) * 0.25;
            int logoX = (i - s) / 2;
            int logoY = (int)((double)j * 0.5 - d0);
            u_2877_K.blit(matrixStack, logoX, logoY, s / 2, (int)d0, 0.0f, 0.0f, 120, 60, 256, 256);
            u_2877_K.blit(matrixStack, logoX + s / 2, logoY, s / 2, (int)d0, 120.0f, 0.0f, -120, 60, 256, 256);
            u_2877_K.blit(matrixStack, logoX, logoY + (int)d0, s / 2, (int)d0, 0.0f, 60.0f, 120, -60, 256, 256);
            u_2877_K.blit(matrixStack, logoX + s / 2, logoY + (int)d0, s / 2, (int)d0, 120.0f, 60.0f, -120, -60, 256, 256);
            c_4037_x.s_2632_s();
            c_4037_x.Y_259_p();
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            int progressBarWidth = (int)((double)i * 0.5);
            int barX = (i - progressBarWidth) / 2;
            int barY = (int)((double)j * 0.8325);
            this.n_1700_B(matrixStack, barX, barY, barX + progressBarWidth, barY + 5, f2);
        } else {
            D_940_S.n_1700_B(this.G_564_y, matrixStack, i, j, mouseX, mouseY, f2, this.w_1484_f, alphaBar);
        }
        if (f >= 2.0f) {
            this.G_564_y.n_1700_B((Overlay)null);
        }
        if (this.t_148_a == -1L && this.P_1922_E.G_564_y() && (!this.v_4262_N || f1 >= 2.0f)) {
            this.t_148_a = j_3341_s.J_1907_R();
            try {
                this.P_1922_E.P_1922_E();
                this.u_1723_Y.accept(Optional.empty());
            }
            catch (Throwable throwable) {
                this.u_1723_Y.accept(Optional.of(throwable));
            }
            if (this.G_564_y.Y_1740_V != null) {
                this.G_564_y.Y_1740_V.init(this.G_564_y, this.G_564_y.RealmsServerPing().Q_4569_t(), this.G_564_y.RealmsServerPing().M_182_A());
            }
        }
        this.G_564_y.s_956_w.R_4764_Y();
    }

    private void n_1700_B(g_221_o p_238629_1_, int p_238629_2_, int p_238629_3_, int p_238629_4_, int p_238629_5_, float p_238629_6_) {
        int i = u_530_F.u_1723_Y((float)(p_238629_4_ - p_238629_2_ - 2) * this.w_1484_f);
        int j = Math.round(p_238629_6_ * 255.0f);
        if (this.M_588_G != this.u_2550_I) {
            int k = this.M_588_G >> 16 & 0xFF;
            int l = this.M_588_G >> 8 & 0xFF;
            int i1 = this.M_588_G & 0xFF;
            int j1 = M_4239_y.n_1700_B.n_1700_B(j, k, l, i1);
            u_2877_K.fill(p_238629_1_, p_238629_2_, p_238629_3_, p_238629_4_, p_238629_5_, j1);
        }
        int j2 = this.P_4830_p >> 16 & 0xFF;
        int k2 = this.P_4830_p >> 8 & 0xFF;
        int l2 = this.P_4830_p & 0xFF;
        int i3 = M_4239_y.n_1700_B.n_1700_B(j, j2, k2, l2);
        u_2877_K.fill(p_238629_1_, p_238629_2_ + 1, p_238629_3_, p_238629_4_ - 1, p_238629_3_ + 1, i3);
        u_2877_K.fill(p_238629_1_, p_238629_2_ + 1, p_238629_5_, p_238629_4_ - 1, p_238629_5_ - 1, i3);
        u_2877_K.fill(p_238629_1_, p_238629_2_, p_238629_3_, p_238629_2_ + 1, p_238629_5_, i3);
        u_2877_K.fill(p_238629_1_, p_238629_4_, p_238629_3_, p_238629_4_ - 1, p_238629_5_, i3);
        int k1 = this.h_1847_R >> 16 & 0xFF;
        int l1 = this.h_1847_R >> 8 & 0xFF;
        int i2 = this.h_1847_R & 0xFF;
        i3 = M_4239_y.n_1700_B.n_1700_B(j, k1, l1, i2);
        u_2877_K.fill(p_238629_1_, p_238629_2_ + 2, p_238629_3_ + 2, p_238629_2_ + i, p_238629_5_ - 2, i3);
    }

    @Override
    public boolean n_1700_B() {
        return true;
    }

    public void J_1907_R() {
        this.u_2550_I = 0;
        this.M_588_G = R_4764_Y;
        this.P_4830_p = 0xFFFFFF;
        this.h_1847_R = 0xFFFFFF;
    }

    private static int n_1700_B(Properties p_readColor_0_, String p_readColor_1_, int p_readColor_2_) {
        String s = p_readColor_0_.getProperty(p_readColor_1_);
        if (s == null) {
            return p_readColor_2_;
        }
        int i = u_2877_K.n_1700_B(s = s.trim(), p_readColor_2_);
        if (i < 0) {
            Config.warn("Invalid color: " + p_readColor_1_ + " = " + s);
            return i;
        }
        Config.dbg(p_readColor_1_ + " = " + s);
        return i;
    }

    private static int n_1700_B(String p_parseColor_0_, int p_parseColor_1_) {
        if (p_parseColor_0_ == null) {
            return p_parseColor_1_;
        }
        p_parseColor_0_ = p_parseColor_0_.trim();
        try {
            return Integer.parseInt(p_parseColor_0_, 16) & 0xFFFFFF;
        }
        catch (NumberFormatException numberformatexception) {
            return p_parseColor_1_;
        }
    }

    public boolean R_4764_Y() {
        return this.M_182_A;
    }

    static class n_1700_B
    extends P_4645_d {
        public n_1700_B() {
            super(n_1700_B);
        }

        @Override
        protected P_4645_d.n_1700_B n_1700_B(ResourceManager resourceManager) {
            P_4645_d.n_1700_B n_1700_B2;
            block8: {
                MinecraftClient minecraft = MinecraftClient.A_4115_X();
                S_4169_p vanillapack = minecraft.z_4693_k().n_1700_B();
                InputStream inputstream = lightning.product.u_2877_K$n_1700_B.n_1700_B(resourceManager, vanillapack);
                try {
                    n_1700_B2 = new P_4645_d.n_1700_B(new TextureMetadataSection(true, true), i_2518_W.n_1700_B(inputstream));
                    if (inputstream == null) break block8;
                }
                catch (Throwable throwable) {
                    try {
                        if (inputstream != null) {
                            try {
                                inputstream.close();
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                        }
                        throw throwable;
                    }
                    catch (IOException ioexception1) {
                        return new P_4645_d.n_1700_B(ioexception1);
                    }
                }
                inputstream.close();
            }
            return n_1700_B2;
        }

        private static InputStream n_1700_B(ResourceManager p_getLogoInputStream_0_, S_4169_p p_getLogoInputStream_1_) throws IOException {
            return p_getLogoInputStream_0_.J_1907_R(n_1700_B) ? p_getLogoInputStream_0_.n_1700_B(n_1700_B).J_1907_R() : p_getLogoInputStream_1_.getResourceStream(i_4221_J.n_1700_B, n_1700_B);
        }
    }
}



