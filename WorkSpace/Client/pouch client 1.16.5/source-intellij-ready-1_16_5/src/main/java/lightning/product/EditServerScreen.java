/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.booleans.BooleanConsumer
 */
package lightning.product;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.net.IDN;
import java.util.function.Predicate;
import lightning.product.F_2904_S;
import lightning.product.H_1468_N;
import lightning.product.O_694_j;
import lightning.product.Button;
import lightning.product.ServerData;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class EditServerScreen
extends k_2603_m {
    private static final x_282_a n_1700_B = new F_2904_S("addServer.enterName");
    private static final x_282_a J_1907_R = new F_2904_S("addServer.enterIp");
    private Button R_4764_Y;
    private final BooleanConsumer G_564_y;
    private final ServerData P_1922_E;
    private O_694_j u_1723_Y;
    private O_694_j v_4262_N;
    private Button w_1484_f;
    private final k_2603_m t_148_a;
    private final Predicate<String> s_956_w = p_210141_0_ -> {
        if (H_1468_N.J_1907_R(p_210141_0_)) {
            return true;
        }
        String[] astring = p_210141_0_.split(":");
        if (astring.length == 0) {
            return true;
        }
        try {
            String s = IDN.toASCII(astring[0]);
            return true;
        }
        catch (IllegalArgumentException illegalargumentexception) {
            return false;
        }
    };

    public EditServerScreen(k_2603_m p_i225927_1_, BooleanConsumer p_i225927_2_, ServerData p_i225927_3_) {
        super(new F_2904_S("addServer.title"));
        this.t_148_a = p_i225927_1_;
        this.G_564_y = p_i225927_2_;
        this.P_1922_E = p_i225927_3_;
    }

    @Override
    public void tick() {
        this.v_4262_N.tick();
        this.u_1723_Y.tick();
    }

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.v_4262_N = new O_694_j(this.font, this.width / 2 - 100, 66, 200, 20, new F_2904_S("addServer.enterName"));
        this.v_4262_N.setFocused2(true);
        this.v_4262_N.setText(this.P_1922_E.n_1700_B);
        this.v_4262_N.setResponder(this::n_1700_B);
        this.children.add(this.v_4262_N);
        this.u_1723_Y = new O_694_j(this.font, this.width / 2 - 100, 106, 200, 20, new F_2904_S("addServer.enterIp"));
        this.u_1723_Y.setMaxStringLength(128);
        this.u_1723_Y.setText(this.P_1922_E.J_1907_R);
        this.u_1723_Y.setValidator(this.s_956_w);
        this.u_1723_Y.setResponder(this::n_1700_B);
        this.children.add(this.u_1723_Y);
        this.w_1484_f = this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 72, 200, 20, EditServerScreen.n_1700_B(this.P_1922_E.J_1907_R()), p_213031_1_ -> {
            this.P_1922_E.n_1700_B(ServerData.n_1700_B.values()[(this.P_1922_E.J_1907_R().ordinal() + 1) % ServerData.n_1700_B.values().length]);
            this.w_1484_f.setMessage(EditServerScreen.n_1700_B(this.P_1922_E.J_1907_R()));
        }));
        this.R_4764_Y = this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 96 + 18, 200, 20, new F_2904_S("addServer.add"), p_213030_1_ -> this.n_1700_B()));
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120 + 18, 200, 20, CommonComponents.G_564_y, p_213029_1_ -> this.G_564_y.accept(false)));
        this.J_1907_R();
    }

    private static x_282_a n_1700_B(ServerData.n_1700_B p_238624_0_) {
        return new F_2904_S("addServer.resourcePack").n_1700_B(": ").n_1700_B(p_238624_0_.n_1700_B());
    }

    @Override
    public void resize(MinecraftClient minecraft, int width, int height) {
        String s = this.u_1723_Y.getText();
        String s1 = this.v_4262_N.getText();
        this.init(minecraft, width, height);
        this.u_1723_Y.setText(s);
        this.v_4262_N.setText(s1);
    }

    private void n_1700_B(String p_213028_1_) {
        this.J_1907_R();
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    private void n_1700_B() {
        this.P_1922_E.n_1700_B = this.v_4262_N.getText();
        this.P_1922_E.J_1907_R = this.u_1723_Y.getText();
        this.G_564_y.accept(true);
    }

    @Override
    public void closeScreen() {
        this.J_1907_R();
        this.minecraft.n_1700_B(this.t_148_a);
    }

    private void J_1907_R() {
        String s = this.u_1723_Y.getText();
        boolean flag = !s.isEmpty() && s.split(":").length > 0 && s.indexOf(32) == -1;
        this.R_4764_Y.active = flag && !this.v_4262_N.getText().isEmpty();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        EditServerScreen.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 17, 0xFFFFFF);
        EditServerScreen.drawString(matrixStack, this.font, n_1700_B, this.width / 2 - 100, 53, 0xA0A0A0);
        EditServerScreen.drawString(matrixStack, this.font, J_1907_R, this.width / 2 - 100, 94, 0xA0A0A0);
        this.v_4262_N.render(matrixStack, mouseX, mouseY, partialTicks);
        this.u_1723_Y.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}



