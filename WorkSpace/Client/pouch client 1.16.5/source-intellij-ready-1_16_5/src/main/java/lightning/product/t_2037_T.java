/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.stream.IntStream;
import lightning.product.A_4313_D;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.D_4792_h;
import lightning.product.E_688_b;
import lightning.product.F_2904_S;
import lightning.product.K_4074_S;
import lightning.product.O_1806_w;
import lightning.product.StandingSignBlock;
import lightning.product.T_2910_P;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.W_2853_p;
import lightning.product.W_3265_k;
import lightning.product.X_933_l;
import lightning.product.Z_3224_L;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.l_3747_P;
import lightning.product.o_2840_r;
import lightning.product.o_3091_w;
import lightning.product.CommonComponents;
import lightning.product.ServerboundSignUpdatePacket;
import lightning.product.w_2043_E;
import lightning.product.x_282_a;

public class t_2037_T
extends k_2603_m {
    private final O_1806_w.n_1700_B n_1700_B = new O_1806_w.n_1700_B();
    private final A_4313_D J_1907_R;
    private int R_4764_Y;
    private int G_564_y;
    private w_2043_E P_1922_E;
    private final String[] u_1723_Y = (String[])IntStream.range(0, 4).mapToObj(teSign::n_1700_B).map(x_282_a::getString).toArray(String[]::new);

    public t_2037_T(A_4313_D teSign) {
        super(new F_2904_S("sign.edit"));
        this.J_1907_R = teSign;
    }

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120, 200, 20, CommonComponents.R_4764_Y, p_238847_1_ -> this.n_1700_B()));
        this.J_1907_R.n_1700_B(false);
        this.P_1922_E = new w_2043_E(() -> this.u_1723_Y[this.G_564_y], p_238850_1_ -> {
            this.u_1723_Y[this.G_564_y] = p_238850_1_;
            this.J_1907_R.n_1700_B(this.G_564_y, new U_2871_b((String)p_238850_1_));
        }, w_2043_E.n_1700_B(this.minecraft), w_2043_E.R_4764_Y(this.minecraft), p_238848_1_ -> this.minecraft.t_148_a.J_1907_R((String)p_238848_1_) <= 90);
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
        W_2853_p clientplaynethandler = this.minecraft.k_2293_S();
        if (clientplaynethandler != null) {
            clientplaynethandler.n_1700_B(new ServerboundSignUpdatePacket(this.J_1907_R.x_607_J(), this.u_1723_Y[0], this.u_1723_Y[1], this.u_1723_Y[2], this.u_1723_Y[3]));
        }
        this.J_1907_R.n_1700_B(true);
    }

    @Override
    public void tick() {
        ++this.R_4764_Y;
        if (!this.J_1907_R.z_1737_N().n_1700_B(this.J_1907_R.e_4240_b().J_1907_R())) {
            this.n_1700_B();
        }
    }

    private void n_1700_B() {
        this.J_1907_R.J_1907_R();
        this.minecraft.n_1700_B((k_2603_m)null);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        this.P_1922_E.n_1700_B(codePoint);
        return true;
    }

    @Override
    public void closeScreen() {
        this.n_1700_B();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 265) {
            this.G_564_y = this.G_564_y - 1 & 3;
            this.P_1922_E.P_1922_E();
            return true;
        }
        if (keyCode != 264 && keyCode != 257 && keyCode != 335) {
            return this.P_1922_E.n_1700_B(keyCode) ? true : super.keyPressed(keyCode, scanCode, modifiers);
        }
        this.G_564_y = this.G_564_y + 1 & 3;
        this.P_1922_E.P_1922_E();
        return true;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        W_3265_k.R_4764_Y();
        this.renderBackground(matrixStack);
        t_2037_T.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 40, 0xFFFFFF);
        matrixStack.n_1700_B();
        matrixStack.n_1700_B((double)(this.width / 2), 0.0, 50.0);
        float f = 93.75f;
        matrixStack.n_1700_B(93.75f, -93.75f, 93.75f);
        matrixStack.n_1700_B(0.0, -1.3125, 0.0);
        K_4074_S blockstate = this.J_1907_R.e_4240_b();
        boolean flag = blockstate.J_1907_R() instanceof StandingSignBlock;
        if (!flag) {
            matrixStack.n_1700_B(0.0, -0.3125, 0.0);
        }
        boolean flag1 = this.R_4764_Y / 6 % 2 == 0;
        float f1 = 0.6666667f;
        matrixStack.n_1700_B();
        matrixStack.n_1700_B(0.6666667f, -0.6666667f, -0.6666667f);
        o_3091_w.n_1700_B irendertypebuffer$impl = this.minecraft.j_1564_a().J_1907_R();
        T_2910_P rendermaterial = O_1806_w.n_1700_B(blockstate.J_1907_R());
        D_4792_h ivertexbuilder = rendermaterial.n_1700_B(irendertypebuffer$impl, this.n_1700_B::getRenderType);
        this.n_1700_B.n_1700_B.n_1700_B(matrixStack, ivertexbuilder, 0xF000F0, Z_3224_L.n_1700_B);
        if (flag) {
            this.n_1700_B.J_1907_R.n_1700_B(matrixStack, ivertexbuilder, 0xF000F0, Z_3224_L.n_1700_B);
        }
        matrixStack.J_1907_R();
        float f2 = 0.010416667f;
        matrixStack.n_1700_B(0.0, 0.3333333432674408, 0.046666666865348816);
        matrixStack.n_1700_B(0.010416667f, -0.010416667f, 0.010416667f);
        int i = this.J_1907_R.w_1484_f().v_4262_N();
        int j = this.P_1922_E.u_1723_Y();
        int k = this.P_1922_E.v_4262_N();
        int l = this.G_564_y * 10 - this.u_1723_Y.length * 5;
        D_1098_v matrix4f = matrixStack.R_4764_Y().n_1700_B();
        for (int i1 = 0; i1 < this.u_1723_Y.length; ++i1) {
            String s = this.u_1723_Y[i1];
            if (s == null) continue;
            if (this.font.n_1700_B()) {
                s = this.font.n_1700_B(s);
            }
            float f3 = -this.minecraft.t_148_a.J_1907_R(s) / 2;
            this.minecraft.t_148_a.n_1700_B(s, f3, i1 * 10 - this.u_1723_Y.length * 5, i, false, matrix4f, irendertypebuffer$impl, false, 0, 0xF000F0, false);
            if (i1 != this.G_564_y || j < 0 || !flag1) continue;
            int j1 = this.minecraft.t_148_a.J_1907_R(s.substring(0, Math.max(Math.min(j, s.length()), 0)));
            int k1 = j1 - this.minecraft.t_148_a.J_1907_R(s) / 2;
            if (j < s.length()) continue;
            this.minecraft.t_148_a.n_1700_B("_", k1, l, i, false, matrix4f, irendertypebuffer$impl, false, 0, 0xF000F0, false);
        }
        irendertypebuffer$impl.J_1907_R();
        for (int i3 = 0; i3 < this.u_1723_Y.length; ++i3) {
            String s1 = this.u_1723_Y[i3];
            if (s1 == null || i3 != this.G_564_y || j < 0) continue;
            int j3 = this.minecraft.t_148_a.J_1907_R(s1.substring(0, Math.max(Math.min(j, s1.length()), 0)));
            int k3 = j3 - this.minecraft.t_148_a.J_1907_R(s1) / 2;
            if (flag1 && j < s1.length()) {
                t_2037_T.fill(matrixStack, k3, l - 1, k3 + 1, l + 9, 0xFF000000 | i);
            }
            if (k == j) continue;
            int l3 = Math.min(j, k);
            int l1 = Math.max(j, k);
            int i2 = this.minecraft.t_148_a.J_1907_R(s1.substring(0, l3)) - this.minecraft.t_148_a.J_1907_R(s1) / 2;
            int j2 = this.minecraft.t_148_a.J_1907_R(s1.substring(0, l1)) - this.minecraft.t_148_a.J_1907_R(s1) / 2;
            int k2 = Math.min(i2, j2);
            int l2 = Math.max(i2, j2);
            l_3747_P tessellator = l_3747_P.n_1700_B();
            D_3318_r bufferbuilder = tessellator.R_4764_Y();
            c_4037_x.e_4240_b();
            c_4037_x.Y_1740_V();
            c_4037_x.n_1700_B(X_933_l.h_1847_R.h_1847_R);
            bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
            bufferbuilder.n_1700_B(matrix4f, (float)k2, (float)(l + 9), 0.0f).color(0, 0, 255, 255).endVertex();
            bufferbuilder.n_1700_B(matrix4f, (float)l2, (float)(l + 9), 0.0f).color(0, 0, 255, 255).endVertex();
            bufferbuilder.n_1700_B(matrix4f, (float)l2, (float)l, 0.0f).color(0, 0, 255, 255).endVertex();
            bufferbuilder.n_1700_B(matrix4f, (float)k2, (float)l, 0.0f).color(0, 0, 255, 255).endVertex();
            bufferbuilder.u_1723_Y();
            o_2840_r.n_1700_B(bufferbuilder);
            c_4037_x.t_4043_B();
            c_4037_x.x_607_J();
        }
        matrixStack.J_1907_R();
        W_3265_k.G_564_y();
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


