/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.O_922_L;
import lightning.product.GenericDirtMessageScreen;
import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.Button;
import lightning.product.Z_1567_W;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.i_2909_p;
import lightning.product.k_2603_m;
import lightning.product.k_596_g;
import lightning.product.q_3418_t;
import lightning.product.x_282_a;
import lightning.product.y_4642_Y;

public class E_1407_D
extends k_2603_m {
    private int n_1700_B;
    private final x_282_a J_1907_R;
    private final boolean R_4764_Y;
    private x_282_a G_564_y;

    public E_1407_D(@Nullable x_282_a textComponent, boolean isHardcoreMode) {
        super(new F_2904_S(isHardcoreMode ? "deathScreen.title.hardcore" : "deathScreen.title"));
        this.J_1907_R = textComponent;
        this.R_4764_Y = isHardcoreMode;
    }

    @Override
    protected void init() {
        this.n_1700_B = 0;
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 72, 200, 20, this.R_4764_Y ? new F_2904_S("deathScreen.spectate") : new F_2904_S("deathScreen.respawn"), p_213021_1_ -> {
            this.minecraft.Y_259_p.G_564_y();
            this.minecraft.n_1700_B((k_2603_m)null);
        }));
        Button button = this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 96, 200, 20, new F_2904_S("deathScreen.titleScreen"), p_213020_1_ -> {
            if (this.R_4764_Y) {
                this.n_1700_B();
            } else {
                q_3418_t confirmscreen = new q_3418_t(this::n_1700_B, new F_2904_S("deathScreen.quit.confirm"), U_2871_b.R_4764_Y, new F_2904_S("deathScreen.titleScreen"), new F_2904_S("deathScreen.respawn"));
                this.minecraft.n_1700_B(confirmscreen);
                confirmscreen.n_1700_B(20);
            }
        }));
        if (!this.R_4764_Y && this.minecraft.z_1737_N() == null) {
            button.active = false;
        }
        for (V_2511_L widget : this.buttons) {
            widget.active = false;
        }
        this.G_564_y = new F_2904_S("deathScreen.score").n_1700_B(": ").n_1700_B(new U_2871_b(Integer.toString(this.minecraft.Y_259_p.r_4414_L())).n_1700_B(D_4024_W.Q_4569_t));
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    private void n_1700_B(boolean p_213022_1_) {
        if (p_213022_1_) {
            this.n_1700_B();
        } else {
            this.minecraft.Y_259_p.G_564_y();
            this.minecraft.n_1700_B((k_2603_m)null);
        }
    }

    private void n_1700_B() {
        if (this.minecraft.Y_601_j != null) {
            this.minecraft.Y_601_j.w_1484_f();
        }
        this.minecraft.J_1907_R(new GenericDirtMessageScreen(new F_2904_S("menu.savingLevel")));
        this.minecraft.n_1700_B(y_4642_Y.R_4764_Y() ? new k_596_g(true) : new O_922_L());
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        E_1407_D.fillGradient(matrixStack, 0, 0, this.width, this.height, 0x60500000, -1602211792);
        c_4037_x.v_4276_D();
        c_4037_x.J_1907_R(2.0f, 2.0f, 2.0f);
        E_1407_D.drawCenteredString(matrixStack, this.font, this.title, this.width / 2 / 2, 30, 0xFFFFFF);
        c_4037_x.d_2461_k();
        if (this.J_1907_R != null) {
            E_1407_D.drawCenteredString(matrixStack, this.font, this.J_1907_R, this.width / 2, 85, 0xFFFFFF);
        }
        E_1407_D.drawCenteredString(matrixStack, this.font, this.G_564_y, this.width / 2, 100, 0xFFFFFF);
        if (this.J_1907_R != null && mouseY > 85 && mouseY < 94) {
            Z_1567_W style = this.n_1700_B(mouseX);
            this.renderComponentHoverEffect(matrixStack, style, mouseX, mouseY);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Nullable
    private Z_1567_W n_1700_B(int p_238623_1_) {
        if (this.J_1907_R == null) {
            return null;
        }
        int i = this.minecraft.t_148_a.n_1700_B((FormattedText)this.J_1907_R);
        int j = this.width / 2 - i / 2;
        int k = this.width / 2 + i / 2;
        return p_238623_1_ >= j && p_238623_1_ <= k ? this.minecraft.t_148_a.J_1907_R().n_1700_B(this.J_1907_R, p_238623_1_ - j) : null;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        Z_1567_W style;
        if (this.J_1907_R != null && mouseY > 85.0 && mouseY < 94.0 && (style = this.n_1700_B((int)mouseX)) != null && style.w_1484_f() != null && style.w_1484_f().n_1700_B() == i_2909_p.n_1700_B.n_1700_B) {
            this.handleComponentClicked(style);
            return false;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        ++this.n_1700_B;
        if (this.n_1700_B == 20) {
            for (V_2511_L widget : this.buttons) {
                widget.active = true;
            }
        }
    }
}


