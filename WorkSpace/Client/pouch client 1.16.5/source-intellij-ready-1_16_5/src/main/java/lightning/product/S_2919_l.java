/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.I_1084_e;
import lightning.product.N_2445_q;
import lightning.product.Button;
import lightning.product.Checkbox;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.q_3131_N;
import lightning.product.x_282_a;

public class S_2919_l
extends k_2603_m {
    private final k_2603_m n_1700_B;
    private static final x_282_a J_1907_R = new F_2904_S("multiplayerWarning.header").n_1700_B(D_4024_W.multiplayerClientSuggestionProvider);
    private static final x_282_a R_4764_Y = new F_2904_S("multiplayerWarning.message");
    private static final x_282_a G_564_y = new F_2904_S("multiplayerWarning.check");
    private static final x_282_a P_1922_E = J_1907_R.P_1922_E().n_1700_B("\n").n_1700_B(R_4764_Y);
    private Checkbox u_1723_Y;
    private N_2445_q v_4262_N = N_2445_q.n_1700_B;

    public S_2919_l(k_2603_m p_i230052_1_) {
        super(I_1084_e.n_1700_B);
        this.n_1700_B = p_i230052_1_;
    }

    @Override
    protected void init() {
        super.init();
        this.v_4262_N = N_2445_q.n_1700_B(this.font, (FormattedText)R_4764_Y, this.width - 50);
        int i = (this.v_4262_N.n_1700_B() + 1) * 9 * 2;
        this.addButton(new Button(this.width / 2 - 155, 100 + i, 150, 20, CommonComponents.v_4262_N, p_230165_1_ -> {
            if (this.u_1723_Y.n_1700_B()) {
                this.minecraft.P_4830_p.l_1233_K = true;
                this.minecraft.P_4830_p.J_1907_R();
            }
            this.minecraft.n_1700_B(new q_3131_N(this.n_1700_B));
        }));
        this.addButton(new Button(this.width / 2 - 155 + 160, 100 + i, 150, 20, CommonComponents.w_1484_f, p_230164_1_ -> this.minecraft.n_1700_B(this.n_1700_B)));
        this.u_1723_Y = new Checkbox(this.width / 2 - 155 + 80, 76 + i, 150, 20, G_564_y, false);
        this.addButton(this.u_1723_Y);
    }

    @Override
    public String getNarrationMessage() {
        return P_1922_E.getString();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderDirtBackground(0);
        S_2919_l.drawString(matrixStack, this.font, J_1907_R, 25, 30, 0xFFFFFF);
        this.v_4262_N.J_1907_R(matrixStack, 25, 70, 18, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


