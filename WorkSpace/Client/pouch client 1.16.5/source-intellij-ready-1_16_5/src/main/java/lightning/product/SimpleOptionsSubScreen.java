/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.OptionsSubScreen;
import lightning.product.OptionsList;
import lightning.product.I_1084_e;
import lightning.product.M_2935_g;
import lightning.product.V_2511_L;
import lightning.product.Button;
import lightning.product.V_4423_d;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public abstract class SimpleOptionsSubScreen
extends OptionsSubScreen {
    private final M_2935_g[] n_1700_B;
    @Nullable
    private V_2511_L J_1907_R;
    private OptionsList P_1922_E;

    public SimpleOptionsSubScreen(k_2603_m p_i242058_1_, V_4423_d p_i242058_2_, x_282_a p_i242058_3_, M_2935_g[] p_i242058_4_) {
        super(p_i242058_1_, p_i242058_2_, p_i242058_3_);
        this.n_1700_B = p_i242058_4_;
    }

    @Override
    protected void init() {
        this.P_1922_E = new OptionsList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        this.P_1922_E.n_1700_B(this.n_1700_B);
        this.children.add(this.P_1922_E);
        this.n_1700_B();
        this.J_1907_R = this.P_1922_E.J_1907_R(M_2935_g.NARRATOR);
        if (this.J_1907_R != null) {
            this.J_1907_R.active = I_1084_e.J_1907_R.n_1700_B();
        }
    }

    protected void n_1700_B() {
        this.addButton(new Button(this.width / 2 - 100, this.height - 27, 200, 20, CommonComponents.R_4764_Y, p_243316_1_ -> this.minecraft.n_1700_B(this.R_4764_Y)));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.P_1922_E.render(matrixStack, mouseX, mouseY, partialTicks);
        SimpleOptionsSubScreen.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        List<FormattedCharSequence> list = SimpleOptionsSubScreen.n_1700_B(this.P_1922_E, mouseX, mouseY);
        if (list != null) {
            this.renderTooltip(matrixStack, list, mouseX, mouseY);
        }
    }

    public void J_1907_R() {
        if (this.J_1907_R != null) {
            this.J_1907_R.setMessage(M_2935_g.NARRATOR.getName(this.G_564_y));
        }
    }
}


