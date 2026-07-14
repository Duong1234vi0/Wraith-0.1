/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.MutableComponent;
import lightning.product.VillagerData;
import lightning.product.K_3710_b;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.a_9_q;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.MerchantOffers;
import lightning.product.u_530_F;
import lightning.product.MerchantOffer;
import lightning.product.x_282_a;
import lightning.product.z_3427_G;

public class g_904_S
extends z_3427_G<K_3710_b> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/villager2.png");
    private static final x_282_a J_1907_R = new F_2904_S("merchant.trades");
    private static final x_282_a R_4764_Y = new U_2871_b(" - ");
    private static final x_282_a G_564_y = new F_2904_S("merchant.deprecated");
    private int P_1922_E;
    private final n_1700_B[] u_1723_Y = new n_1700_B[7];
    private int v_4262_N;
    private boolean Q_2552_b;

    public g_904_S(K_3710_b p_i51080_1_, W_3491_f p_i51080_2_, x_282_a p_i51080_3_) {
        super(p_i51080_1_, p_i51080_2_, p_i51080_3_);
        this.t_148_a = 276;
        this.P_4830_p = 107;
    }

    private void n_1700_B() {
        ((K_3710_b)this.Q_4569_t).G_564_y(this.P_1922_E);
        ((K_3710_b)this.Q_4569_t).v_4262_N(this.P_1922_E);
        this.minecraft.k_2293_S().n_1700_B(new a_9_q(this.P_1922_E));
    }

    @Override
    protected void init() {
        super.init();
        int i = (this.width - this.t_148_a) / 2;
        int j = (this.height - this.s_956_w) / 2;
        int k = j + 16 + 2;
        for (int l = 0; l < 7; ++l) {
            this.u_1723_Y[l] = this.addButton(new n_1700_B(i + 5, k, l, p_214132_1_ -> {
                if (p_214132_1_ instanceof n_1700_B) {
                    this.P_1922_E = ((n_1700_B)p_214132_1_).n_1700_B() + this.v_4262_N;
                    this.n_1700_B();
                }
            }));
            k += 20;
        }
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, int x, int y) {
        int i = ((K_3710_b)this.Q_4569_t).R_4764_Y();
        if (i > 0 && i <= 5 && ((K_3710_b)this.Q_4569_t).u_1723_Y()) {
            MutableComponent itextcomponent = this.title.P_1922_E().n_1700_B(R_4764_Y).n_1700_B(new F_2904_S("merchant.level." + i));
            int j = this.font.n_1700_B((FormattedText)itextcomponent);
            int k = 49 + this.t_148_a / 2 - j / 2;
            this.font.J_1907_R(matrixStack, itextcomponent, (float)k, 6.0f, 0x404040);
        } else {
            this.font.J_1907_R(matrixStack, this.title, (float)(49 + this.t_148_a / 2 - this.font.n_1700_B((FormattedText)this.title) / 2), 6.0f, 0x404040);
        }
        this.font.J_1907_R(matrixStack, this.M_182_A.c_(), (float)this.P_4830_p, (float)this.h_1847_R, 0x404040);
        int l = this.font.n_1700_B((FormattedText)J_1907_R);
        this.font.J_1907_R(matrixStack, J_1907_R, (float)(5 - l / 2 + 48), 6.0f, 0x404040);
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        int i = (this.width - this.t_148_a) / 2;
        int j = (this.height - this.s_956_w) / 2;
        g_904_S.blit(matrixStack, i, j, this.getBlitOffset(), 0.0f, 0.0f, this.t_148_a, this.s_956_w, 256, 512);
        MerchantOffers merchantoffers = ((K_3710_b)this.Q_4569_t).P_1922_E();
        if (!merchantoffers.isEmpty()) {
            int k = this.P_1922_E;
            if (k < 0 || k >= merchantoffers.size()) {
                return;
            }
            MerchantOffer merchantoffer = (MerchantOffer)merchantoffers.get(k);
            if (merchantoffer.M_182_A()) {
                this.minecraft.G_624_v().n_1700_B(n_1700_B);
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                g_904_S.blit(matrixStack, this.multiplayerClientSuggestionProvider + 83 + 99, this.w_1457_N + 35, this.getBlitOffset(), 311.0f, 0.0f, 28, 21, 256, 512);
            }
        }
    }

    private void n_1700_B(g_221_o p_238839_1_, int p_238839_2_, int p_238839_3_, MerchantOffer p_238839_4_) {
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        int i = ((K_3710_b)this.Q_4569_t).R_4764_Y();
        int j = ((K_3710_b)this.Q_4569_t).n_1700_B();
        if (i < 5) {
            g_904_S.blit(p_238839_1_, p_238839_2_ + 136, p_238839_3_ + 16, this.getBlitOffset(), 0.0f, 186.0f, 102, 5, 256, 512);
            int k = VillagerData.J_1907_R(i);
            if (j >= k && VillagerData.G_564_y(i)) {
                int l = 100;
                float f = 100.0f / (float)(VillagerData.R_4764_Y(i) - k);
                int i1 = Math.min(u_530_F.G_564_y(f * (float)(j - k)), 100);
                g_904_S.blit(p_238839_1_, p_238839_2_ + 136, p_238839_3_ + 16, this.getBlitOffset(), 0.0f, 191.0f, i1 + 1, 5, 256, 512);
                int j1 = ((K_3710_b)this.Q_4569_t).J_1907_R();
                if (j1 > 0) {
                    int k1 = Math.min(u_530_F.G_564_y((float)j1 * f), 100 - i1);
                    g_904_S.blit(p_238839_1_, p_238839_2_ + 136 + i1 + 1, p_238839_3_ + 16 + 1, this.getBlitOffset(), 2.0f, 182.0f, k1, 3, 256, 512);
                }
            }
        }
    }

    private void n_1700_B(g_221_o p_238840_1_, int p_238840_2_, int p_238840_3_, MerchantOffers p_238840_4_) {
        int i = p_238840_4_.size() + 1 - 7;
        if (i > 1) {
            int j = 139 - (27 + (i - 1) * 139 / i);
            int k = 1 + j / i + 139 / i;
            int l = 113;
            int i1 = Math.min(113, this.v_4262_N * k);
            if (this.v_4262_N == i - 1) {
                i1 = 113;
            }
            g_904_S.blit(p_238840_1_, p_238840_2_ + 94, p_238840_3_ + 18 + i1, this.getBlitOffset(), 0.0f, 199.0f, 6, 27, 256, 512);
        } else {
            g_904_S.blit(p_238840_1_, p_238840_2_ + 94, p_238840_3_ + 18, this.getBlitOffset(), 6.0f, 199.0f, 6, 27, 256, 512);
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        MerchantOffers merchantoffers = ((K_3710_b)this.Q_4569_t).P_1922_E();
        if (!merchantoffers.isEmpty()) {
            int i = (this.width - this.t_148_a) / 2;
            int j = (this.height - this.s_956_w) / 2;
            int k = j + 16 + 1;
            int l = i + 5 + 5;
            c_4037_x.v_4276_D();
            c_4037_x.n_3318_d();
            this.minecraft.G_624_v().n_1700_B(n_1700_B);
            this.n_1700_B(matrixStack, i, j, merchantoffers);
            int i1 = 0;
            for (MerchantOffer merchantoffer : merchantoffers) {
                if (this.n_1700_B(merchantoffers.size()) && (i1 < this.v_4262_N || i1 >= 7 + this.v_4262_N)) {
                    ++i1;
                    continue;
                }
                Z_1993_T itemstack = merchantoffer.n_1700_B();
                Z_1993_T itemstack1 = merchantoffer.J_1907_R();
                Z_1993_T itemstack2 = merchantoffer.R_4764_Y();
                Z_1993_T itemstack3 = merchantoffer.G_564_y();
                this.itemRenderer.J_1907_R = 100.0f;
                int j1 = k + 2;
                this.n_1700_B(matrixStack, itemstack1, itemstack, l, j1);
                if (!itemstack2.n_1700_B()) {
                    this.itemRenderer.R_4764_Y(itemstack2, i + 5 + 35, j1);
                    this.itemRenderer.n_1700_B(this.font, itemstack2, i + 5 + 35, j1);
                }
                this.n_1700_B(matrixStack, merchantoffer, i, j1);
                this.itemRenderer.R_4764_Y(itemstack3, i + 5 + 68, j1);
                this.itemRenderer.n_1700_B(this.font, itemstack3, i + 5 + 68, j1);
                this.itemRenderer.J_1907_R = 0.0f;
                k += 20;
                ++i1;
            }
            int k1 = this.P_1922_E;
            MerchantOffer merchantoffer1 = (MerchantOffer)merchantoffers.get(k1);
            if (((K_3710_b)this.Q_4569_t).u_1723_Y()) {
                this.n_1700_B(matrixStack, i, j, merchantoffer1);
            }
            if (merchantoffer1.M_182_A() && this.n_1700_B(186, 35, 22, 21, mouseX, mouseY) && ((K_3710_b)this.Q_4569_t).G_564_y()) {
                this.renderTooltip(matrixStack, G_564_y, mouseX, mouseY);
            }
            for (n_1700_B merchantscreen$tradebutton : this.u_1723_Y) {
                if (merchantscreen$tradebutton.isHovered()) {
                    merchantscreen$tradebutton.renderToolTip(matrixStack, mouseX, mouseY);
                }
                merchantscreen$tradebutton.visible = merchantscreen$tradebutton.n_1700_B < ((K_3710_b)this.Q_4569_t).P_1922_E().size();
            }
            c_4037_x.d_2461_k();
            c_4037_x.multiplayerClientSuggestionProvider();
        }
        this.J_1907_R(matrixStack, mouseX, mouseY);
    }

    private void n_1700_B(g_221_o p_238842_1_, MerchantOffer p_238842_2_, int p_238842_3_, int p_238842_4_) {
        c_4037_x.Y_601_j();
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        if (p_238842_2_.M_182_A()) {
            g_904_S.blit(p_238842_1_, p_238842_3_ + 5 + 35 + 20, p_238842_4_ + 3, this.getBlitOffset(), 25.0f, 171.0f, 10, 9, 256, 512);
        } else {
            g_904_S.blit(p_238842_1_, p_238842_3_ + 5 + 35 + 20, p_238842_4_ + 3, this.getBlitOffset(), 15.0f, 171.0f, 10, 9, 256, 512);
        }
    }

    private void n_1700_B(g_221_o p_238841_1_, Z_1993_T p_238841_2_, Z_1993_T p_238841_3_, int p_238841_4_, int p_238841_5_) {
        this.itemRenderer.R_4764_Y(p_238841_2_, p_238841_4_, p_238841_5_);
        if (p_238841_3_.t_4043_B() == p_238841_2_.t_4043_B()) {
            this.itemRenderer.n_1700_B(this.font, p_238841_2_, p_238841_4_, p_238841_5_);
        } else {
            this.itemRenderer.n_1700_B(this.font, p_238841_3_, p_238841_4_, p_238841_5_, p_238841_3_.t_4043_B() == 1 ? "1" : null);
            this.itemRenderer.n_1700_B(this.font, p_238841_2_, p_238841_4_ + 14, p_238841_5_, p_238841_2_.t_4043_B() == 1 ? "1" : null);
            this.minecraft.G_624_v().n_1700_B(n_1700_B);
            this.setBlitOffset(this.getBlitOffset() + 300);
            g_904_S.blit(p_238841_1_, p_238841_4_ + 7, p_238841_5_ + 12, this.getBlitOffset(), 0.0f, 176.0f, 9, 2, 256, 512);
            this.setBlitOffset(this.getBlitOffset() - 300);
        }
    }

    private boolean n_1700_B(int p_214135_1_) {
        return p_214135_1_ > 7;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        int i = ((K_3710_b)this.Q_4569_t).P_1922_E().size();
        if (this.n_1700_B(i)) {
            int j = i - 7;
            this.v_4262_N = (int)((double)this.v_4262_N - delta);
            this.v_4262_N = u_530_F.n_1700_B(this.v_4262_N, 0, j);
        }
        return true;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        int i = ((K_3710_b)this.Q_4569_t).P_1922_E().size();
        if (this.Q_2552_b) {
            int j = this.w_1457_N + 18;
            int k = j + 139;
            int l = i - 7;
            float f = ((float)mouseY - (float)j - 13.5f) / ((float)(k - j) - 27.0f);
            f = f * (float)l + 0.5f;
            this.v_4262_N = u_530_F.n_1700_B((int)f, 0, l);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.Q_2552_b = false;
        int i = (this.width - this.t_148_a) / 2;
        int j = (this.height - this.s_956_w) / 2;
        if (this.n_1700_B(((K_3710_b)this.Q_4569_t).P_1922_E().size()) && mouseX > (double)(i + 94) && mouseX < (double)(i + 94 + 6) && mouseY > (double)(j + 18) && mouseY <= (double)(j + 18 + 139 + 1)) {
            this.Q_2552_b = true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    class n_1700_B
    extends Button {
        final int n_1700_B;

        public n_1700_B(int p_i50601_2_, int p_i50601_3_, int p_i50601_4_, Button.n_1700_B p_i50601_5_) {
            super(p_i50601_2_, p_i50601_3_, 89, 20, U_2871_b.R_4764_Y, p_i50601_5_);
            this.n_1700_B = p_i50601_4_;
            this.visible = false;
        }

        public int n_1700_B() {
            return this.n_1700_B;
        }

        @Override
        public void renderToolTip(g_221_o matrixStack, int mouseX, int mouseY) {
            if (this.isHovered && ((K_3710_b)g_904_S.this.Q_4569_t).P_1922_E().size() > this.n_1700_B + g_904_S.this.v_4262_N) {
                if (mouseX < this.x + 20) {
                    Z_1993_T itemstack = ((MerchantOffer)((K_3710_b)g_904_S.this.Q_4569_t).P_1922_E().get(this.n_1700_B + g_904_S.this.v_4262_N)).J_1907_R();
                    g_904_S.this.renderTooltip(matrixStack, itemstack, mouseX, mouseY);
                } else if (mouseX < this.x + 50 && mouseX > this.x + 30) {
                    Z_1993_T itemstack2 = ((MerchantOffer)((K_3710_b)g_904_S.this.Q_4569_t).P_1922_E().get(this.n_1700_B + g_904_S.this.v_4262_N)).R_4764_Y();
                    if (!itemstack2.n_1700_B()) {
                        g_904_S.this.renderTooltip(matrixStack, itemstack2, mouseX, mouseY);
                    }
                } else if (mouseX > this.x + 65) {
                    Z_1993_T itemstack1 = ((MerchantOffer)((K_3710_b)g_904_S.this.Q_4569_t).P_1922_E().get(this.n_1700_B + g_904_S.this.v_4262_N)).G_564_y();
                    g_904_S.this.renderTooltip(matrixStack, itemstack1, mouseX, mouseY);
                }
            }
        }
    }
}


