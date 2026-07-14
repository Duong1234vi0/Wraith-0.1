/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.booleans.BooleanConsumer
 */
package lightning.product;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import lightning.product.FormattedText;
import lightning.product.N_2445_q;
import lightning.product.V_2511_L;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class q_3418_t
extends k_2603_m {
    private final x_282_a G_564_y;
    private N_2445_q P_1922_E = N_2445_q.n_1700_B;
    protected x_282_a n_1700_B;
    protected x_282_a J_1907_R;
    private int u_1723_Y;
    protected final BooleanConsumer R_4764_Y;

    public q_3418_t(BooleanConsumer _callbackFunction, x_282_a _title, x_282_a _messageLine2) {
        this(_callbackFunction, _title, _messageLine2, CommonComponents.P_1922_E, CommonComponents.u_1723_Y);
    }

    public q_3418_t(BooleanConsumer p_i232270_1_, x_282_a p_i232270_2_, x_282_a p_i232270_3_, x_282_a p_i232270_4_, x_282_a p_i232270_5_) {
        super(p_i232270_2_);
        this.R_4764_Y = p_i232270_1_;
        this.G_564_y = p_i232270_3_;
        this.n_1700_B = p_i232270_4_;
        this.J_1907_R = p_i232270_5_;
    }

    @Override
    public String getNarrationMessage() {
        return super.getNarrationMessage() + ". " + this.G_564_y.getString();
    }

    @Override
    protected void init() {
        super.init();
        this.addButton(new Button(this.width / 2 - 155, this.height / 6 + 96, 150, 20, this.n_1700_B, p_213002_1_ -> this.R_4764_Y.accept(true)));
        this.addButton(new Button(this.width / 2 - 155 + 160, this.height / 6 + 96, 150, 20, this.J_1907_R, p_213001_1_ -> this.R_4764_Y.accept(false)));
        this.P_1922_E = N_2445_q.n_1700_B(this.font, (FormattedText)this.G_564_y, this.width - 50);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        q_3418_t.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 70, 0xFFFFFF);
        this.P_1922_E.n_1700_B(matrixStack, this.width / 2, 90);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    public void n_1700_B(int ticksUntilEnableIn) {
        this.u_1723_Y = ticksUntilEnableIn;
        for (V_2511_L widget : this.buttons) {
            widget.active = false;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (--this.u_1723_Y == 0) {
            for (V_2511_L widget : this.buttons) {
                widget.active = true;
            }
        }
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.R_4764_Y.accept(false);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}


