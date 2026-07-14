/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.OptionsSubScreen;
import lightning.product.E_4346_v;
import lightning.product.F_2904_S;
import lightning.product.M_2935_g;
import lightning.product.Button;
import lightning.product.V_4423_d;
import lightning.product.Y_4729_x;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;
import net.optifine.Lang;
import net.optifine.gui.GuiScreenCapeOF;

public class l_792_r
extends OptionsSubScreen {
    public l_792_r(k_2603_m parentScreenIn, V_4423_d gameSettingsIn) {
        super(parentScreenIn, gameSettingsIn, new F_2904_S("options.skinCustomisation.title"));
    }

    @Override
    protected void init() {
        int i = 0;
        for (E_4346_v playermodelpart : E_4346_v.values()) {
            this.addButton(new Button(this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), 150, 20, this.n_1700_B(playermodelpart), p_lambda$init$0_2_ -> {
                this.G_564_y.n_1700_B(playermodelpart);
                p_lambda$init$0_2_.setMessage(this.n_1700_B(playermodelpart));
            }));
            ++i;
        }
        this.addButton(new Y_4729_x(this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), 150, 20, M_2935_g.MAIN_HAND, M_2935_g.MAIN_HAND.getName(this.G_564_y), p_lambda$init$1_1_ -> {
            M_2935_g.MAIN_HAND.setValueIndex(this.G_564_y, 1);
            this.G_564_y.J_1907_R();
            p_lambda$init$1_1_.setMessage(M_2935_g.MAIN_HAND.getName(this.G_564_y));
            this.G_564_y.R_4764_Y();
        }));
        if (++i % 2 == 1) {
            ++i;
        }
        this.addButton(new Button(this.width / 2 - 100, this.height / 6 + 24 * (i >> 1), 200, 20, Lang.getComponent("of.options.skinCustomisation.ofCape"), p_lambda$init$2_1_ -> this.minecraft.n_1700_B(new GuiScreenCapeOF(this))));
        this.addButton(new Button(this.width / 2 - 100, this.height / 6 + 24 * ((i += 2) >> 1), 200, 20, CommonComponents.R_4764_Y, p_lambda$init$3_1_ -> this.minecraft.n_1700_B(this.R_4764_Y)));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        l_792_r.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private x_282_a n_1700_B(E_4346_v p_238655_1_) {
        return CommonComponents.n_1700_B(p_238655_1_.R_4764_Y(), this.G_564_y.G_564_y().contains((Object)p_238655_1_));
    }
}


