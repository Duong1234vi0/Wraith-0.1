/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.F_2904_S;
import lightning.product.O_694_j;
import lightning.product.Button;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.o_1792_J;
import lightning.product.CommonComponents;
import lightning.product.u_970_b;

public class R_4398_I
extends k_2603_m {
    protected final k_2603_m n_1700_B;
    private List<FormattedCharSequence> R_4764_Y;
    private Button G_564_y;
    private Button P_1922_E;
    private Button u_1723_Y;
    private Button v_4262_N;
    protected O_694_j J_1907_R;
    private u_970_b w_1484_f;

    public R_4398_I(k_2603_m screenIn) {
        super(new F_2904_S("selectWorld.title"));
        this.n_1700_B = screenIn;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        return super.mouseScrolled(mouseX, mouseY, delta);
    }

    @Override
    public void tick() {
        this.J_1907_R.tick();
    }

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.J_1907_R = new O_694_j(this.font, this.width / 2 - 100, 22, 200, 20, this.J_1907_R, new F_2904_S("selectWorld.search"));
        this.J_1907_R.setResponder(p_214329_1_ -> this.w_1484_f.n_1700_B(() -> p_214329_1_, false));
        this.w_1484_f = new u_970_b(this, this.minecraft, this.width, this.height, 48, this.height - 64, 36, () -> this.J_1907_R.getText(), this.w_1484_f);
        this.children.add(this.J_1907_R);
        this.children.add(this.w_1484_f);
        this.P_1922_E = this.addButton(new Button(this.width / 2 - 154, this.height - 52, 150, 20, new F_2904_S("selectWorld.select"), p_214325_1_ -> this.w_1484_f.n_1700_B().ifPresent(u_970_b.n_1700_B::n_1700_B)));
        this.addButton(new Button(this.width / 2 + 4, this.height - 52, 150, 20, new F_2904_S("selectWorld.create"), p_214326_1_ -> this.minecraft.n_1700_B(o_1792_J.n_1700_B(this))));
        this.u_1723_Y = this.addButton(new Button(this.width / 2 - 154, this.height - 28, 72, 20, new F_2904_S("selectWorld.edit"), p_214323_1_ -> this.w_1484_f.n_1700_B().ifPresent(u_970_b.n_1700_B::R_4764_Y)));
        this.G_564_y = this.addButton(new Button(this.width / 2 - 76, this.height - 28, 72, 20, new F_2904_S("selectWorld.delete"), p_214330_1_ -> this.w_1484_f.n_1700_B().ifPresent(u_970_b.n_1700_B::J_1907_R)));
        this.v_4262_N = this.addButton(new Button(this.width / 2 + 4, this.height - 28, 72, 20, new F_2904_S("selectWorld.recreate"), p_214328_1_ -> this.w_1484_f.n_1700_B().ifPresent(u_970_b.n_1700_B::G_564_y)));
        this.addButton(new Button(this.width / 2 + 82, this.height - 28, 72, 20, CommonComponents.G_564_y, p_214327_1_ -> this.minecraft.n_1700_B(this.n_1700_B)));
        this.n_1700_B(false);
        this.n_1700_B(this.J_1907_R);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(keyCode, scanCode, modifiers) ? true : this.J_1907_R.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void closeScreen() {
        this.minecraft.n_1700_B(this.n_1700_B);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        return this.J_1907_R.charTyped(codePoint, modifiers);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.R_4764_Y = null;
        this.w_1484_f.render(matrixStack, mouseX, mouseY, partialTicks);
        this.J_1907_R.render(matrixStack, mouseX, mouseY, partialTicks);
        R_4398_I.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 8, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.R_4764_Y != null) {
            this.renderTooltip(matrixStack, this.R_4764_Y, mouseX, mouseY);
        }
    }

    public void n_1700_B(List<FormattedCharSequence> p_239026_1_) {
        this.R_4764_Y = p_239026_1_;
    }

    public void n_1700_B(boolean p_214324_1_) {
        this.P_1922_E.active = p_214324_1_;
        this.G_564_y.active = p_214324_1_;
        this.u_1723_Y.active = p_214324_1_;
        this.v_4262_N.active = p_214324_1_;
    }

    @Override
    public void onClose() {
        if (this.w_1484_f != null) {
            this.w_1484_f.getEventListeners().forEach(u_970_b.n_1700_B::close);
        }
    }
}


