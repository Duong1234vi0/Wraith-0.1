/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.I_1084_e;
import lightning.product.O_694_j;
import lightning.product.S_499_t;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.MinecraftClient;
import lightning.product.d_742_e;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public abstract class r_2555_q
extends k_2603_m {
    private static final x_282_a v_4262_N = new F_2904_S("advMode.setCommand");
    private static final x_282_a w_1484_f = new F_2904_S("advMode.command");
    private static final x_282_a t_148_a = new F_2904_S("advMode.previousOutput");
    protected O_694_j n_1700_B;
    protected O_694_j J_1907_R;
    protected Button R_4764_Y;
    protected Button G_564_y;
    protected Button P_1922_E;
    protected boolean u_1723_Y;
    private S_499_t s_956_w;

    public r_2555_q() {
        super(I_1084_e.n_1700_B);
    }

    @Override
    public void tick() {
        this.n_1700_B.tick();
    }

    abstract d_742_e n_1700_B();

    abstract int J_1907_R();

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.R_4764_Y = this.addButton(new Button(this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, CommonComponents.R_4764_Y, p_214187_1_ -> this.G_564_y()));
        this.G_564_y = this.addButton(new Button(this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, CommonComponents.G_564_y, p_214186_1_ -> this.closeScreen()));
        this.P_1922_E = this.addButton(new Button(this.width / 2 + 150 - 20, this.J_1907_R(), 20, 20, new U_2871_b("O"), p_214184_1_ -> {
            d_742_e commandblocklogic;
            commandblocklogic.n_1700_B(!(commandblocklogic = this.n_1700_B()).s_956_w());
            this.R_4764_Y();
        }));
        this.n_1700_B = new O_694_j(this.font, this.width / 2 - 150, 50, 300, 20, (x_282_a)new F_2904_S("advMode.command")){

            @Override
            protected MutableComponent getNarrationMessage() {
                return super.getNarrationMessage().n_1700_B(r_2555_q.this.s_956_w.J_1907_R());
            }
        };
        this.n_1700_B.setMaxStringLength(32500);
        this.n_1700_B.setResponder(this::n_1700_B);
        this.children.add(this.n_1700_B);
        this.J_1907_R = new O_694_j(this.font, this.width / 2 - 150, this.J_1907_R(), 276, 20, new F_2904_S("advMode.previousOutput"));
        this.J_1907_R.setMaxStringLength(32500);
        this.J_1907_R.setEnabled(false);
        this.J_1907_R.setText("-");
        this.children.add(this.J_1907_R);
        this.n_1700_B(this.n_1700_B);
        this.n_1700_B.setFocused2(true);
        this.s_956_w = new S_499_t(this.minecraft, this, this.n_1700_B, this.font, true, true, 0, 7, false, Integer.MIN_VALUE);
        this.s_956_w.n_1700_B(true);
        this.s_956_w.n_1700_B();
    }

    @Override
    public void resize(MinecraftClient minecraft, int width, int height) {
        String s = this.n_1700_B.getText();
        this.init(minecraft, width, height);
        this.n_1700_B.setText(s);
        this.s_956_w.n_1700_B();
    }

    protected void R_4764_Y() {
        if (this.n_1700_B().s_956_w()) {
            this.P_1922_E.setMessage(new U_2871_b("O"));
            this.J_1907_R.setText(this.n_1700_B().v_4262_N().getString());
        } else {
            this.P_1922_E.setMessage(new U_2871_b("X"));
            this.J_1907_R.setText("-");
        }
    }

    protected void G_564_y() {
        d_742_e commandblocklogic = this.n_1700_B();
        this.n_1700_B(commandblocklogic);
        if (!commandblocklogic.s_956_w()) {
            commandblocklogic.J_1907_R((x_282_a)null);
        }
        this.minecraft.n_1700_B((k_2603_m)null);
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    protected abstract void n_1700_B(d_742_e var1);

    @Override
    public void closeScreen() {
        this.n_1700_B().n_1700_B(this.u_1723_Y);
        this.minecraft.n_1700_B((k_2603_m)null);
    }

    private void n_1700_B(String p_214185_1_) {
        this.s_956_w.n_1700_B();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.s_956_w.n_1700_B(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (keyCode != 257 && keyCode != 335) {
            return false;
        }
        this.G_564_y();
        return true;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        return this.s_956_w.n_1700_B(delta) ? true : super.mouseScrolled(mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return this.s_956_w.n_1700_B(mouseX, mouseY, button) ? true : super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        r_2555_q.drawCenteredString(matrixStack, this.font, v_4262_N, this.width / 2, 20, 0xFFFFFF);
        r_2555_q.drawString(matrixStack, this.font, w_1484_f, this.width / 2 - 150, 40, 0xA0A0A0);
        this.n_1700_B.render(matrixStack, mouseX, mouseY, partialTicks);
        int i = 75;
        if (!this.J_1907_R.getText().isEmpty()) {
            r_2555_q.drawString(matrixStack, this.font, t_148_a, this.width / 2 - 150, (i += 46 + this.J_1907_R() - 135) + 4, 0xA0A0A0);
            this.J_1907_R.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.s_956_w.n_1700_B(matrixStack, mouseX, mouseY);
    }
}



