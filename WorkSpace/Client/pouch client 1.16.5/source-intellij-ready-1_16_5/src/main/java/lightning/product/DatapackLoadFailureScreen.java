/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.N_2445_q;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;

public class DatapackLoadFailureScreen
extends k_2603_m {
    private N_2445_q n_1700_B = N_2445_q.n_1700_B;
    private final Runnable J_1907_R;

    public DatapackLoadFailureScreen(Runnable p_i232276_1_) {
        super(new F_2904_S("datapackFailure.title"));
        this.J_1907_R = p_i232276_1_;
    }

    @Override
    protected void init() {
        super.init();
        this.n_1700_B = N_2445_q.n_1700_B(this.font, (FormattedText)this.getTitle(), this.width - 50);
        this.addButton(new Button(this.width / 2 - 155, this.height / 6 + 96, 150, 20, new F_2904_S("datapackFailure.safeMode"), p_238622_1_ -> this.J_1907_R.run()));
        this.addButton(new Button(this.width / 2 - 155 + 160, this.height / 6 + 96, 150, 20, new F_2904_S("gui.toTitle"), p_238621_1_ -> this.minecraft.n_1700_B((k_2603_m)null)));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.n_1700_B.n_1700_B(matrixStack, this.width / 2, 70);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}


