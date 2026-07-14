/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.util.concurrent.locks.ReentrantLock;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.RealmsLongRunningMcoTaskScreen;
import lightning.product.Q_201_j;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.Z_1567_W;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import lightning.product.r_715_M;
import lightning.product.u_744_e;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class J_739_q
extends RealmsScreen {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final x_282_a J_1907_R = new F_2904_S("mco.terms.title");
    private static final x_282_a R_4764_Y = new F_2904_S("mco.terms.sentence.1");
    private static final x_282_a G_564_y = new U_2871_b(" ").n_1700_B(new F_2904_S("mco.terms.sentence.2").J_1907_R(Z_1567_W.n_1700_B.R_4764_Y(true)));
    private final k_2603_m P_1922_E;
    private final r_715_M u_1723_Y;
    private final q_1982_R v_4262_N;
    private boolean w_1484_f;
    private final String t_148_a = "https://aka.ms/MinecraftRealmsTerms";

    public J_739_q(k_2603_m p_i232225_1_, r_715_M p_i232225_2_, q_1982_R p_i232225_3_) {
        this.P_1922_E = p_i232225_1_;
        this.u_1723_Y = p_i232225_2_;
        this.v_4262_N = p_i232225_3_;
    }

    @Override
    public void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        int i = this.width / 4 - 2;
        this.addButton(new Button(this.width / 4, J_739_q.G_564_y(12), i, 20, new F_2904_S("mco.terms.buttons.agree"), p_238078_1_ -> this.n_1700_B()));
        this.addButton(new Button(this.width / 2 + 4, J_739_q.G_564_y(12), i, 20, new F_2904_S("mco.terms.buttons.disagree"), p_238077_1_ -> this.minecraft.n_1700_B(this.P_1922_E)));
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.n_1700_B(this.P_1922_E);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void n_1700_B() {
        p_178_J realmsclient = p_178_J.n_1700_B();
        try {
            realmsclient.M_588_G();
            this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(this.P_1922_E, new Q_201_j(this.u_1723_Y, this.P_1922_E, this.v_4262_N, new ReentrantLock())));
        }
        catch (u_744_e realmsserviceexception) {
            n_1700_B.error("Couldn't agree to TOS");
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.w_1484_f) {
            this.minecraft.Q_4569_t.n_1700_B("https://aka.ms/MinecraftRealmsTerms");
            j_3341_s.t_148_a().n_1700_B("https://aka.ms/MinecraftRealmsTerms");
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public String getNarrationMessage() {
        return super.getNarrationMessage() + ". " + R_4764_Y.getString() + " " + G_564_y.getString();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        J_739_q.drawCenteredString(matrixStack, this.font, J_1907_R, this.width / 2, 17, 0xFFFFFF);
        this.font.J_1907_R(matrixStack, R_4764_Y, (float)(this.width / 2 - 120), (float)J_739_q.G_564_y(5), 0xFFFFFF);
        int i = this.font.n_1700_B((FormattedText)R_4764_Y);
        int j = this.width / 2 - 121 + i;
        int k = J_739_q.G_564_y(5);
        int l = j + this.font.n_1700_B((FormattedText)G_564_y) + 1;
        int i1 = k + 1 + 9;
        this.w_1484_f = j <= mouseX && mouseX <= l && k <= mouseY && mouseY <= i1;
        this.font.J_1907_R(matrixStack, G_564_y, (float)(this.width / 2 - 120 + i), (float)J_739_q.G_564_y(5), this.w_1484_f ? 7107012 : 0x3366BB);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


