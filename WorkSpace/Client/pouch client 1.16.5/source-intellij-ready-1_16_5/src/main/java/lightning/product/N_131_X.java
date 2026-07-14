/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.I_14_v;
import lightning.product.O_2332_X;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class N_131_X
extends k_2603_m {
    private static final x_282_a n_1700_B = new F_2904_S("selectWorld.allowCommands");
    private static final x_282_a J_1907_R = new F_2904_S("selectWorld.gameMode");
    private static final x_282_a R_4764_Y = new F_2904_S("lanServer.otherPlayers");
    private final k_2603_m G_564_y;
    private Button P_1922_E;
    private Button u_1723_Y;
    private String v_4262_N = "survival";
    private boolean w_1484_f;

    public N_131_X(k_2603_m lastScreenIn) {
        super(new F_2904_S("lanServer.title"));
        this.G_564_y = lastScreenIn;
    }

    @Override
    protected void init() {
        this.addButton(new Button(this.width / 2 - 155, this.height - 28, 150, 20, new F_2904_S("lanServer.start"), p_213082_1_ -> {
            this.minecraft.n_1700_B((k_2603_m)null);
            int i = O_2332_X.n_1700_B();
            F_2904_S itextcomponent = this.minecraft.n_3318_d().n_1700_B(I_14_v.n_1700_B(this.v_4262_N), this.w_1484_f, i) ? new F_2904_S("commands.publish.started", i) : new F_2904_S("commands.publish.failed");
            this.minecraft.M_588_G.R_4764_Y().n_1700_B(itextcomponent);
            this.minecraft.n_1700_B();
        }));
        this.addButton(new Button(this.width / 2 + 5, this.height - 28, 150, 20, CommonComponents.G_564_y, p_213085_1_ -> this.minecraft.n_1700_B(this.G_564_y)));
        this.u_1723_Y = this.addButton(new Button(this.width / 2 - 155, 100, 150, 20, U_2871_b.R_4764_Y, p_213084_1_ -> {
            this.v_4262_N = "spectator".equals(this.v_4262_N) ? "creative" : ("creative".equals(this.v_4262_N) ? "adventure" : ("adventure".equals(this.v_4262_N) ? "survival" : "spectator"));
            this.n_1700_B();
        }));
        this.P_1922_E = this.addButton(new Button(this.width / 2 + 5, 100, 150, 20, n_1700_B, p_213083_1_ -> {
            this.w_1484_f = !this.w_1484_f;
            this.n_1700_B();
        }));
        this.n_1700_B();
    }

    private void n_1700_B() {
        this.u_1723_Y.setMessage(new F_2904_S("options.generic_value", J_1907_R, new F_2904_S("selectWorld.gameMode." + this.v_4262_N)));
        this.P_1922_E.setMessage(CommonComponents.n_1700_B(n_1700_B, this.w_1484_f));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        N_131_X.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 50, 0xFFFFFF);
        N_131_X.drawCenteredString(matrixStack, this.font, R_4764_Y, this.width / 2, 82, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


