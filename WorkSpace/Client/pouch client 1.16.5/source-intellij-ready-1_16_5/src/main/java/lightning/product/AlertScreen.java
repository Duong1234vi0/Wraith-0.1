/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.FormattedText;
import lightning.product.N_2445_q;
import lightning.product.V_2511_L;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class AlertScreen
extends k_2603_m {
    private final Runnable R_4764_Y;
    protected final x_282_a n_1700_B;
    private N_2445_q G_564_y = N_2445_q.n_1700_B;
    protected final x_282_a J_1907_R;
    private int P_1922_E;

    public AlertScreen(Runnable p_i48623_1_, x_282_a p_i48623_2_, x_282_a p_i48623_3_) {
        this(p_i48623_1_, p_i48623_2_, p_i48623_3_, CommonComponents.w_1484_f);
    }

    public AlertScreen(Runnable p_i232268_1_, x_282_a p_i232268_2_, x_282_a p_i232268_3_, x_282_a p_i232268_4_) {
        super(p_i232268_2_);
        this.R_4764_Y = p_i232268_1_;
        this.n_1700_B = p_i232268_3_;
        this.J_1907_R = p_i232268_4_;
    }

    @Override
    protected void init() {
        super.init();
        this.addButton(new Button(this.width / 2 - 100, this.height / 6 + 168, 200, 20, this.J_1907_R, p_212983_1_ -> this.R_4764_Y.run()));
        this.G_564_y = N_2445_q.n_1700_B(this.font, (FormattedText)this.n_1700_B, this.width - 50);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        AlertScreen.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 70, 0xFFFFFF);
        this.G_564_y.n_1700_B(matrixStack, this.width / 2, 90);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void tick() {
        super.tick();
        if (--this.P_1922_E == 0) {
            for (V_2511_L widget : this.buttons) {
                widget.active = true;
            }
        }
    }
}


