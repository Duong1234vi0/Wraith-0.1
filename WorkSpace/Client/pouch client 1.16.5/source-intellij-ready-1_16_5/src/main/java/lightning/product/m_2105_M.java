/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.N_2445_q;
import lightning.product.Button;
import lightning.product.V_4423_d;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;

public class m_2105_M
extends k_2603_m {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/demo_background.png");
    private N_2445_q J_1907_R = N_2445_q.n_1700_B;
    private N_2445_q R_4764_Y = N_2445_q.n_1700_B;

    public m_2105_M() {
        super(new F_2904_S("demo.help.title"));
    }

    @Override
    protected void init() {
        int i = -16;
        this.addButton(new Button(this.width / 2 - 116, this.height / 2 + 62 + -16, 114, 20, new F_2904_S("demo.help.buy"), p_213019_0_ -> {
            p_213019_0_.active = false;
            j_3341_s.t_148_a().n_1700_B("http://www.minecraft.net/store?source=demo");
        }));
        this.addButton(new Button(this.width / 2 + 2, this.height / 2 + 62 + -16, 114, 20, new F_2904_S("demo.help.later"), p_213018_1_ -> {
            this.minecraft.n_1700_B((k_2603_m)null);
            this.minecraft.h_1847_R.w_1484_f();
        }));
        V_4423_d gamesettings = this.minecraft.P_4830_p;
        this.J_1907_R = N_2445_q.n_1700_B(this.font, new F_2904_S("demo.help.movementShort", gamesettings.O_508_d.u_2550_I(), gamesettings.r_715_M.u_2550_I(), gamesettings.A_1038_p.u_2550_I(), gamesettings.i_1637_u.u_2550_I()), new F_2904_S("demo.help.movementMouse"), new F_2904_S("demo.help.jump", gamesettings.Ping.u_2550_I()), new F_2904_S("demo.help.inventory", gamesettings.f_4016_n.u_2550_I()));
        this.R_4764_Y = N_2445_q.n_1700_B(this.font, (FormattedText)new F_2904_S("demo.help.fullWrapped"), 218);
    }

    @Override
    public void renderBackground(g_221_o matrixStack) {
        super.renderBackground(matrixStack);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        int i = (this.width - 248) / 2;
        int j = (this.height - 166) / 2;
        this.blit(matrixStack, i, j, 0, 0, 248, 166);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        int i = (this.width - 248) / 2 + 10;
        int j = (this.height - 166) / 2 + 8;
        this.font.J_1907_R(matrixStack, this.title, (float)i, (float)j, 0x1F1F1F);
        j = this.J_1907_R.R_4764_Y(matrixStack, i, j + 12, 12, 0x4F4F4F);
        this.R_4764_Y.R_4764_Y(matrixStack, i, j + 20, 9, 0x1F1F1F);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


