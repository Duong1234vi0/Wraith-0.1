/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.List;
import lightning.product.FormattedText;
import lightning.product.N_2445_q;
import lightning.product.Button;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;

public class o_3756_j
extends k_2603_m {
    private final FormattedText n_1700_B;
    private final ImmutableList<n_1700_B> J_1907_R;
    private N_2445_q R_4764_Y = N_2445_q.n_1700_B;
    private int G_564_y;
    private int P_1922_E;

    protected o_3756_j(x_282_a title, List<FormattedText> warnings, ImmutableList<n_1700_B> options) {
        super(title);
        this.n_1700_B = FormattedText.n_1700_B(warnings);
        this.J_1907_R = options;
    }

    @Override
    public String getNarrationMessage() {
        return super.getNarrationMessage() + ". " + this.n_1700_B.getString();
    }

    @Override
    public void init(MinecraftClient minecraft, int width, int height) {
        super.init(minecraft, width, height);
        for (n_1700_B gpuwarningscreen$option : this.J_1907_R) {
            this.P_1922_E = Math.max(this.P_1922_E, 20 + this.font.n_1700_B((FormattedText)gpuwarningscreen$option.n_1700_B) + 20);
        }
        int l = 5 + this.P_1922_E + 5;
        int i1 = l * this.J_1907_R.size();
        this.R_4764_Y = N_2445_q.n_1700_B(this.font, this.n_1700_B, i1);
        int i = this.R_4764_Y.n_1700_B() * 9;
        this.G_564_y = (int)((double)height / 2.0 - (double)i / 2.0);
        int j = this.G_564_y + i + 18;
        int k = (int)((double)width / 2.0 - (double)i1 / 2.0);
        for (n_1700_B gpuwarningscreen$option1 : this.J_1907_R) {
            this.addButton(new Button(k, j, this.P_1922_E, 20, gpuwarningscreen$option1.n_1700_B, gpuwarningscreen$option1.J_1907_R));
            k += l;
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderDirtBackground(0);
        o_3756_j.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, this.G_564_y - 18, -1);
        this.R_4764_Y.n_1700_B(matrixStack, this.width / 2, this.G_564_y);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    public static final class n_1700_B {
        private final x_282_a n_1700_B;
        private final Button.n_1700_B J_1907_R;

        public n_1700_B(x_282_a p_i241251_1_, Button.n_1700_B p_i241251_2_) {
            this.n_1700_B = p_i241251_1_;
            this.J_1907_R = p_i241251_2_;
        }
    }
}



