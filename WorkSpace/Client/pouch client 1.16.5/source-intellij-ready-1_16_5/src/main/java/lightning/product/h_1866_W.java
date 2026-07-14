/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.OptionsSubScreen;
import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.M_2935_g;
import lightning.product.Button;
import lightning.product.V_4423_d;
import lightning.product.Y_4729_x;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.t_203_B;

public class h_1866_W
extends OptionsSubScreen {
    public h_1866_W(k_2603_m parentIn, V_4423_d settingsIn) {
        super(parentIn, settingsIn, new F_2904_S("options.sounds.title"));
    }

    @Override
    protected void init() {
        int i = 0;
        this.addButton(new t_203_B(this.minecraft, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), D_38_f.n_1700_B, 310));
        i += 2;
        for (D_38_f soundcategory : D_38_f.values()) {
            if (soundcategory == D_38_f.n_1700_B) continue;
            this.addButton(new t_203_B(this.minecraft, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), soundcategory, 150));
            ++i;
        }
        int j = this.width / 2 - 75;
        int k = this.height / 6 - 12;
        this.addButton(new Y_4729_x(j, k + 24 * (++i >> 1), 150, 20, M_2935_g.SHOW_SUBTITLES, M_2935_g.SHOW_SUBTITLES.R_4764_Y(this.G_564_y), p_213105_1_ -> {
            M_2935_g.SHOW_SUBTITLES.n_1700_B(this.minecraft.P_4830_p);
            p_213105_1_.setMessage(M_2935_g.SHOW_SUBTITLES.R_4764_Y(this.minecraft.P_4830_p));
            this.minecraft.P_4830_p.J_1907_R();
        }));
        this.addButton(new Button(this.width / 2 - 100, this.height / 6 + 168, 200, 20, CommonComponents.R_4764_Y, p_213104_1_ -> this.minecraft.n_1700_B(this.R_4764_Y)));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        h_1866_W.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 15, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


