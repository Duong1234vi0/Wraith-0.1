/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Random;
import lightning.product.D_1098_v;
import lightning.product.D_4024_W;
import lightning.product.D_4792_h;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.K_1310_v;
import lightning.product.M_1336_P;
import lightning.product.Q_1649_j;
import lightning.product.U_2871_b;
import lightning.product.W_3265_k;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.Z_3224_L;
import lightning.product.a_3913_L;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.BookModel;
import lightning.product.l_3747_P;
import lightning.product.o_3091_w;
import lightning.product.EnchantmentNames;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.z_3427_G;

public class t_1279_j
extends z_3427_G<Q_1649_j> {
    private static final g_2336_b Q_2552_b = new g_2336_b("textures/gui/container/enchanting_table.png");
    private static final g_2336_b C_2741_M = new g_2336_b("textures/entity/enchanting_table_book.png");
    private static final BookModel k_2293_S = new BookModel();
    private final Random q_2307_F = new Random();
    public int n_1700_B;
    public float J_1907_R;
    public float R_4764_Y;
    public float G_564_y;
    public float P_1922_E;
    public float u_1723_Y;
    public float v_4262_N;
    private Z_1993_T Z_875_P = Z_1993_T.J_1907_R;

    public t_1279_j(Q_1649_j container, W_3491_f playerInventory, x_282_a textComponent) {
        super(container, playerInventory, textComponent);
    }

    @Override
    public void tick() {
        super.tick();
        this.n_1700_B();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int i = (this.width - this.t_148_a) / 2;
        int j = (this.height - this.s_956_w) / 2;
        for (int k = 0; k < 3; ++k) {
            double d0 = mouseX - (double)(i + 60);
            double d1 = mouseY - (double)(j + 14 + 19 * k);
            if (!(d0 >= 0.0) || !(d1 >= 0.0) || !(d0 < 108.0) || !(d1 < 19.0) || !((Q_1649_j)this.Q_4569_t).J_1907_R((a_3913_L)this.minecraft.Y_259_p, k)) continue;
            this.minecraft.w_1457_N.sendEnchantPacket(((Q_1649_j)this.Q_4569_t).u_1723_Y, k);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        W_3265_k.R_4764_Y();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(Q_2552_b);
        int i = (this.width - this.t_148_a) / 2;
        int j = (this.height - this.s_956_w) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.s_956_w);
        c_4037_x.u_2550_I(5889);
        c_4037_x.v_4276_D();
        c_4037_x.z_1737_N();
        int k = (int)this.minecraft.RealmsServerPing().w_1457_N();
        c_4037_x.R_4764_Y((this.width - 320) / 2 * k, (this.height - 240) / 2 * k, 320 * k, 240 * k);
        c_4037_x.R_4764_Y(-0.34f, 0.23f, 0.0f);
        c_4037_x.n_1700_B(D_1098_v.n_1700_B(90.0, 1.3333334f, 9.0f, 80.0f));
        c_4037_x.u_2550_I(5888);
        matrixStack.n_1700_B();
        g_221_o.n_1700_B matrixstack$entry = matrixStack.R_4764_Y();
        matrixstack$entry.n_1700_B().n_1700_B();
        matrixstack$entry.J_1907_R().R_4764_Y();
        matrixStack.n_1700_B(0.0, (double)3.3f, 1984.0);
        float f = 5.0f;
        matrixStack.n_1700_B(5.0f, 5.0f, 5.0f);
        matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f));
        matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(20.0f));
        float f1 = u_530_F.v_4262_N(partialTicks, this.v_4262_N, this.u_1723_Y);
        matrixStack.n_1700_B((double)((1.0f - f1) * 0.2f), (double)((1.0f - f1) * 0.1f), (double)((1.0f - f1) * 0.25f));
        float f2 = -(1.0f - f1) * 90.0f - 90.0f;
        matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f2));
        matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(180.0f));
        float f3 = u_530_F.v_4262_N(partialTicks, this.R_4764_Y, this.J_1907_R) + 0.25f;
        float f4 = u_530_F.v_4262_N(partialTicks, this.R_4764_Y, this.J_1907_R) + 0.75f;
        f3 = (f3 - (float)u_530_F.J_1907_R((double)f3)) * 1.6f - 0.3f;
        f4 = (f4 - (float)u_530_F.J_1907_R((double)f4)) * 1.6f - 0.3f;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        if (f4 < 0.0f) {
            f4 = 0.0f;
        }
        if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        if (f4 > 1.0f) {
            f4 = 1.0f;
        }
        c_4037_x.n_3318_d();
        k_2293_S.n_1700_B(0.0f, f3, f4, f1);
        o_3091_w.n_1700_B irendertypebuffer$impl = o_3091_w.n_1700_B(l_3747_P.n_1700_B().R_4764_Y());
        D_4792_h ivertexbuilder = irendertypebuffer$impl.getBuffer(k_2293_S.getRenderType(C_2741_M));
        k_2293_S.render(matrixStack, ivertexbuilder, 0xF000F0, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        irendertypebuffer$impl.J_1907_R();
        matrixStack.J_1907_R();
        c_4037_x.u_2550_I(5889);
        c_4037_x.R_4764_Y(0, 0, this.minecraft.RealmsServerPing().u_2550_I(), this.minecraft.RealmsServerPing().M_588_G());
        c_4037_x.d_2461_k();
        c_4037_x.u_2550_I(5888);
        W_3265_k.G_564_y();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        EnchantmentNames.n_1700_B().n_1700_B(((Q_1649_j)this.Q_4569_t).J_1907_R());
        int l = ((Q_1649_j)this.Q_4569_t).n_1700_B();
        for (int i1 = 0; i1 < 3; ++i1) {
            int j1 = i + 60;
            int k1 = j1 + 20;
            this.setBlitOffset(0);
            this.minecraft.G_624_v().n_1700_B(Q_2552_b);
            int l1 = ((Q_1649_j)this.Q_4569_t).n_1700_B[i1];
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            if (l1 == 0) {
                this.blit(matrixStack, j1, j + 14 + 19 * i1, 0, 185, 108, 19);
                continue;
            }
            String s = "" + l1;
            int i2 = 86 - this.font.J_1907_R(s);
            FormattedText itextproperties = EnchantmentNames.n_1700_B().n_1700_B(this.font, i2);
            int j2 = 6839882;
            if (!(l >= i1 + 1 && this.minecraft.Y_259_p.v_165_F >= l1 || this.minecraft.Y_259_p.C_415_h.G_564_y)) {
                this.blit(matrixStack, j1, j + 14 + 19 * i1, 0, 185, 108, 19);
                this.blit(matrixStack, j1 + 1, j + 15 + 19 * i1, 16 * i1, 239, 16, 16);
                this.font.n_1700_B(itextproperties, k1, j + 16 + 19 * i1, i2, (j2 & 0xFEFEFE) >> 1);
                j2 = 4226832;
            } else {
                int k2 = x - (i + 60);
                int l2 = y - (j + 14 + 19 * i1);
                if (k2 >= 0 && l2 >= 0 && k2 < 108 && l2 < 19) {
                    this.blit(matrixStack, j1, j + 14 + 19 * i1, 0, 204, 108, 19);
                    j2 = 0xFFFF80;
                } else {
                    this.blit(matrixStack, j1, j + 14 + 19 * i1, 0, 166, 108, 19);
                }
                this.blit(matrixStack, j1 + 1, j + 15 + 19 * i1, 16 * i1, 223, 16, 16);
                this.font.n_1700_B(itextproperties, k1, j + 16 + 19 * i1, i2, j2);
                j2 = 8453920;
            }
            this.font.n_1700_B(matrixStack, s, (float)(k1 + 86 - this.font.J_1907_R(s)), (float)(j + 16 + 19 * i1 + 7), j2);
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        partialTicks = this.minecraft.RealmsClientConfig();
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.J_1907_R(matrixStack, mouseX, mouseY);
        boolean flag = this.minecraft.Y_259_p.C_415_h.G_564_y;
        int i = ((Q_1649_j)this.Q_4569_t).n_1700_B();
        for (int j = 0; j < 3; ++j) {
            int k = ((Q_1649_j)this.Q_4569_t).n_1700_B[j];
            K_1310_v enchantment = K_1310_v.R_4764_Y(((Q_1649_j)this.Q_4569_t).J_1907_R[j]);
            int l = ((Q_1649_j)this.Q_4569_t).R_4764_Y[j];
            int i1 = j + 1;
            if (!this.n_1700_B(60, 14 + 19 * j, 108, 17, mouseX, mouseY) || k <= 0 || l < 0 || enchantment == null) continue;
            ArrayList list = Lists.newArrayList();
            list.add(new F_2904_S("container.enchant.clue", enchantment.G_564_y(l)).n_1700_B(D_4024_W.M_182_A));
            if (!flag) {
                list.add(U_2871_b.R_4764_Y);
                if (this.minecraft.Y_259_p.v_165_F < k) {
                    list.add(new F_2904_S("container.enchant.level.requirement", ((Q_1649_j)this.Q_4569_t).n_1700_B[j]).n_1700_B(D_4024_W.P_4830_p));
                } else {
                    F_2904_S iformattabletextcomponent = i1 == 1 ? new F_2904_S("container.enchant.lapis.one") : new F_2904_S("container.enchant.lapis.many", i1);
                    list.add(iformattabletextcomponent.n_1700_B(i >= i1 ? D_4024_W.w_1484_f : D_4024_W.P_4830_p));
                    F_2904_S iformattabletextcomponent1 = i1 == 1 ? new F_2904_S("container.enchant.level.one") : new F_2904_S("container.enchant.level.many", i1);
                    list.add(iformattabletextcomponent1.n_1700_B(D_4024_W.w_1484_f));
                }
            }
            this.func_243308_b(matrixStack, list, mouseX, mouseY);
            break;
        }
    }

    public void n_1700_B() {
        Z_1993_T itemstack = ((Q_1649_j)this.Q_4569_t).n_1700_B(0).n_1700_B();
        if (!Z_1993_T.J_1907_R(itemstack, this.Z_875_P)) {
            this.Z_875_P = itemstack;
            do {
                this.G_564_y += (float)(this.q_2307_F.nextInt(4) - this.q_2307_F.nextInt(4));
            } while (this.J_1907_R <= this.G_564_y + 1.0f && this.J_1907_R >= this.G_564_y - 1.0f);
        }
        ++this.n_1700_B;
        this.R_4764_Y = this.J_1907_R;
        this.v_4262_N = this.u_1723_Y;
        boolean flag = false;
        for (int i = 0; i < 3; ++i) {
            if (((Q_1649_j)this.Q_4569_t).n_1700_B[i] == 0) continue;
            flag = true;
        }
        this.u_1723_Y = flag ? (this.u_1723_Y += 0.2f) : (this.u_1723_Y -= 0.2f);
        this.u_1723_Y = u_530_F.n_1700_B(this.u_1723_Y, 0.0f, 1.0f);
        float f1 = (this.G_564_y - this.J_1907_R) * 0.4f;
        float f = 0.2f;
        f1 = u_530_F.n_1700_B(f1, -0.2f, 0.2f);
        this.P_1922_E += (f1 - this.P_1922_E) * 0.9f;
        this.J_1907_R += this.P_1922_E;
    }
}


