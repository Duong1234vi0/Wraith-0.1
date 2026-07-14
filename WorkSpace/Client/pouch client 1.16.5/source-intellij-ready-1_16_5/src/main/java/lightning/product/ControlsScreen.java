/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.OptionsSubScreen;
import lightning.product.D_590_W;
import lightning.product.F_2904_S;
import lightning.product.M_2935_g;
import lightning.product.Q_4113_P;
import lightning.product.Button;
import lightning.product.V_4423_d;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.t_1480_x;
import lightning.product.MouseSettingsScreen;

public class ControlsScreen
extends OptionsSubScreen {
    public D_590_W n_1700_B;
    public long J_1907_R;
    private t_1480_x P_1922_E;
    private Button u_1723_Y;

    public ControlsScreen(k_2603_m screen, V_4423_d settings) {
        super(screen, settings, new F_2904_S("controls.title"));
    }

    @Override
    protected void init() {
        this.addButton(new Button(this.width / 2 - 155, 18, 150, 20, new F_2904_S("options.mouse_settings"), p_213126_1_ -> this.minecraft.n_1700_B(new MouseSettingsScreen(this, this.G_564_y))));
        this.addButton(M_2935_g.AUTO_JUMP.createWidget(this.G_564_y, this.width / 2 - 155 + 160, 18, 150));
        this.P_1922_E = new t_1480_x(this, this.minecraft);
        this.children.add(this.P_1922_E);
        this.u_1723_Y = this.addButton(new Button(this.width / 2 - 155, this.height - 29, 150, 20, new F_2904_S("controls.resetAll"), p_213125_1_ -> {
            for (D_590_W keybinding : this.G_564_y.RealmsDefaultUncaughtExceptionHandler) {
                keybinding.J_1907_R(keybinding.w_1484_f());
            }
            D_590_W.R_4764_Y();
        }));
        this.addButton(new Button(this.width / 2 - 155 + 160, this.height - 29, 150, 20, CommonComponents.R_4764_Y, p_213124_1_ -> this.minecraft.n_1700_B(this.R_4764_Y)));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.n_1700_B != null) {
            this.G_564_y.n_1700_B(this.n_1700_B, Q_4113_P.J_1907_R.R_4764_Y.n_1700_B(button));
            this.n_1700_B = null;
            D_590_W.R_4764_Y();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.n_1700_B != null) {
            if (keyCode == 256) {
                this.G_564_y.n_1700_B(this.n_1700_B, Q_4113_P.n_1700_B);
            } else {
                this.G_564_y.n_1700_B(this.n_1700_B, Q_4113_P.n_1700_B(keyCode, scanCode));
            }
            this.n_1700_B = null;
            this.J_1907_R = j_3341_s.J_1907_R();
            D_590_W.R_4764_Y();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.P_1922_E.render(matrixStack, mouseX, mouseY, partialTicks);
        ControlsScreen.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 8, 0xFFFFFF);
        boolean flag = false;
        for (D_590_W keybinding : this.G_564_y.RealmsDefaultUncaughtExceptionHandler) {
            if (keybinding.M_588_G()) continue;
            flag = true;
            break;
        }
        this.u_1723_Y.active = flag;
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


