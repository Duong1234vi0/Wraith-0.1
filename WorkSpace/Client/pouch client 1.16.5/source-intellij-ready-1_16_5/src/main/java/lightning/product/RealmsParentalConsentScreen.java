/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.N_2445_q;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.NarrationHelper;
import lightning.product.x_282_a;

public class RealmsParentalConsentScreen
extends RealmsScreen {
    private static final x_282_a n_1700_B = new F_2904_S("mco.account.privacyinfo");
    private final k_2603_m J_1907_R;
    private N_2445_q R_4764_Y = N_2445_q.n_1700_B;

    public RealmsParentalConsentScreen(k_2603_m p_i232210_1_) {
        this.J_1907_R = p_i232210_1_;
    }

    @Override
    public void init() {
        NarrationHelper.n_1700_B(n_1700_B.getString());
        F_2904_S itextcomponent = new F_2904_S("mco.account.update");
        x_282_a itextcomponent1 = CommonComponents.w_1484_f;
        int i = Math.max(this.font.n_1700_B((FormattedText)itextcomponent), this.font.n_1700_B((FormattedText)itextcomponent1)) + 30;
        F_2904_S itextcomponent2 = new F_2904_S("mco.account.privacy.info");
        int j = (int)((double)this.font.n_1700_B((FormattedText)itextcomponent2) * 1.2);
        this.addButton(new Button(this.width / 2 - j / 2, RealmsParentalConsentScreen.G_564_y(11), j, 20, itextcomponent2, p_237862_0_ -> j_3341_s.t_148_a().n_1700_B("https://aka.ms/MinecraftGDPR")));
        this.addButton(new Button(this.width / 2 - (i + 5), RealmsParentalConsentScreen.G_564_y(13), i, 20, itextcomponent, p_237861_0_ -> j_3341_s.t_148_a().n_1700_B("https://aka.ms/UpdateMojangAccount")));
        this.addButton(new Button(this.width / 2 + 5, RealmsParentalConsentScreen.G_564_y(13), i, 20, itextcomponent1, p_237860_1_ -> this.minecraft.n_1700_B(this.J_1907_R)));
        this.R_4764_Y = N_2445_q.n_1700_B(this.font, (FormattedText)n_1700_B, (int)Math.round((double)this.width * 0.9));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.R_4764_Y.n_1700_B(matrixStack, this.width / 2, 15, 15, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


