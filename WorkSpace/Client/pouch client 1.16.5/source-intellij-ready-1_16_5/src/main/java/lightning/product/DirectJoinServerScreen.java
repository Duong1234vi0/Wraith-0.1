/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.booleans.BooleanConsumer
 */
package lightning.product;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import lightning.product.F_2904_S;
import lightning.product.O_694_j;
import lightning.product.Button;
import lightning.product.ServerData;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class DirectJoinServerScreen
extends k_2603_m {
    private static final x_282_a n_1700_B = new F_2904_S("addServer.enterIp");
    private Button J_1907_R;
    private final ServerData R_4764_Y;
    private O_694_j G_564_y;
    private final BooleanConsumer P_1922_E;
    private final k_2603_m u_1723_Y;

    public DirectJoinServerScreen(k_2603_m previousScreen, BooleanConsumer p_i225926_2_, ServerData serverData) {
        super(new F_2904_S("selectServer.direct"));
        this.u_1723_Y = previousScreen;
        this.R_4764_Y = serverData;
        this.P_1922_E = p_i225926_2_;
    }

    @Override
    public void tick() {
        this.G_564_y.tick();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.getListener() != this.G_564_y || keyCode != 257 && keyCode != 335) {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
        this.n_1700_B();
        return true;
    }

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.J_1907_R = this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 96 + 12, 200, 20, new F_2904_S("selectServer.select"), p_213026_1_ -> this.n_1700_B()));
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120 + 12, 200, 20, CommonComponents.G_564_y, p_213025_1_ -> this.P_1922_E.accept(false)));
        this.G_564_y = new O_694_j(this.font, this.width / 2 - 100, 116, 200, 20, new F_2904_S("addServer.enterIp"));
        this.G_564_y.setMaxStringLength(128);
        this.G_564_y.setFocused2(true);
        this.G_564_y.setText(this.minecraft.P_4830_p.j_2266_I);
        this.G_564_y.setResponder(p_213024_1_ -> this.J_1907_R());
        this.children.add(this.G_564_y);
        this.n_1700_B(this.G_564_y);
        this.J_1907_R();
    }

    @Override
    public void resize(MinecraftClient minecraft, int width, int height) {
        String s = this.G_564_y.getText();
        this.init(minecraft, width, height);
        this.G_564_y.setText(s);
    }

    private void n_1700_B() {
        this.R_4764_Y.J_1907_R = this.G_564_y.getText();
        this.P_1922_E.accept(true);
    }

    @Override
    public void closeScreen() {
        this.minecraft.n_1700_B(this.u_1723_Y);
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
        this.minecraft.P_4830_p.j_2266_I = this.G_564_y.getText();
        this.minecraft.P_4830_p.J_1907_R();
    }

    private void J_1907_R() {
        String s = this.G_564_y.getText();
        this.J_1907_R.active = !s.isEmpty() && s.split(":").length > 0 && s.indexOf(32) == -1;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        DirectJoinServerScreen.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        DirectJoinServerScreen.drawString(matrixStack, this.font, n_1700_B, this.width / 2 - 100, 100, 0xA0A0A0);
        this.G_564_y.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}



