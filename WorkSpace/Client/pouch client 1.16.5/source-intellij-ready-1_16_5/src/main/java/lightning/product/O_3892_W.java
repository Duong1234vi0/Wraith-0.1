/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.N_2445_q;
import lightning.product.Button;
import lightning.product.Checkbox;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class O_3892_W
extends k_2603_m {
    @Nullable
    private final k_2603_m J_1907_R;
    protected final n_1700_B n_1700_B;
    private final x_282_a R_4764_Y;
    private final boolean G_564_y;
    private N_2445_q P_1922_E = N_2445_q.n_1700_B;
    private Checkbox u_1723_Y;

    public O_3892_W(@Nullable k_2603_m p_i51122_1_, n_1700_B p_i51122_2_, x_282_a p_i51122_3_, x_282_a p_i51122_4_, boolean p_i51122_5_) {
        super(p_i51122_3_);
        this.J_1907_R = p_i51122_1_;
        this.n_1700_B = p_i51122_2_;
        this.R_4764_Y = p_i51122_4_;
        this.G_564_y = p_i51122_5_;
    }

    @Override
    protected void init() {
        super.init();
        this.P_1922_E = N_2445_q.n_1700_B(this.font, (FormattedText)this.R_4764_Y, this.width - 50);
        int i = (this.P_1922_E.n_1700_B() + 1) * 9;
        this.addButton(new Button(this.width / 2 - 155, 100 + i, 150, 20, new F_2904_S("selectWorld.backupJoinConfirmButton"), p_212993_1_ -> this.n_1700_B.proceed(true, this.u_1723_Y.n_1700_B())));
        this.addButton(new Button(this.width / 2 - 155 + 160, 100 + i, 150, 20, new F_2904_S("selectWorld.backupJoinSkipButton"), p_212992_1_ -> this.n_1700_B.proceed(false, this.u_1723_Y.n_1700_B())));
        this.addButton(new Button(this.width / 2 - 155 + 80, 124 + i, 150, 20, CommonComponents.G_564_y, p_212991_1_ -> this.minecraft.n_1700_B(this.J_1907_R)));
        this.u_1723_Y = new Checkbox(this.width / 2 - 155 + 80, 76 + i, 150, 20, new F_2904_S("selectWorld.backupEraseCache"), false);
        if (this.G_564_y) {
            this.addButton(this.u_1723_Y);
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        O_3892_W.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 50, 0xFFFFFF);
        this.P_1922_E.n_1700_B(matrixStack, this.width / 2, 70);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.n_1700_B(this.J_1907_R);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public static interface n_1700_B {
        public void proceed(boolean var1, boolean var2);
    }
}


