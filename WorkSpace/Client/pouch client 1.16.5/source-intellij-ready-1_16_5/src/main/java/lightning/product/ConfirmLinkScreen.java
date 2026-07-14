/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.booleans.BooleanConsumer
 */
package lightning.product;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import lightning.product.F_2904_S;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.CommonComponents;
import lightning.product.q_3418_t;
import lightning.product.x_282_a;

public class ConfirmLinkScreen
extends q_3418_t {
    private final x_282_a G_564_y;
    private final x_282_a P_1922_E;
    private final String u_1723_Y;
    private final boolean v_4262_N;

    public ConfirmLinkScreen(BooleanConsumer p_i51121_1_, String p_i51121_2_, boolean p_i51121_3_) {
        super(p_i51121_1_, new F_2904_S(p_i51121_3_ ? "chat.link.confirmTrusted" : "chat.link.confirm"), new U_2871_b(p_i51121_2_));
        this.n_1700_B = p_i51121_3_ ? new F_2904_S("chat.link.open") : CommonComponents.P_1922_E;
        this.J_1907_R = p_i51121_3_ ? CommonComponents.G_564_y : CommonComponents.u_1723_Y;
        this.P_1922_E = new F_2904_S("chat.copy");
        this.G_564_y = new F_2904_S("chat.link.warning");
        this.v_4262_N = !p_i51121_3_;
        this.u_1723_Y = p_i51121_2_;
    }

    @Override
    protected void init() {
        super.init();
        this.buttons.clear();
        this.children.clear();
        this.addButton(new Button(this.width / 2 - 50 - 105, this.height / 6 + 96, 100, 20, this.n_1700_B, p_213006_1_ -> this.R_4764_Y.accept(true)));
        this.addButton(new Button(this.width / 2 - 50, this.height / 6 + 96, 100, 20, this.P_1922_E, p_213005_1_ -> {
            this.n_1700_B();
            this.R_4764_Y.accept(false);
        }));
        this.addButton(new Button(this.width / 2 - 50 + 105, this.height / 6 + 96, 100, 20, this.J_1907_R, p_213004_1_ -> this.R_4764_Y.accept(false)));
    }

    public void n_1700_B() {
        this.minecraft.Q_4569_t.n_1700_B(this.u_1723_Y);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.v_4262_N) {
            ConfirmLinkScreen.drawCenteredString(matrixStack, this.font, this.G_564_y, this.width / 2, 110, 0xFFCCCC);
        }
    }
}


