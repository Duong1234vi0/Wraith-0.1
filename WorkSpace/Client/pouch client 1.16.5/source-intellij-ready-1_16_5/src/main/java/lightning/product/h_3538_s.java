/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.ServerboundSetStructureBlockPacket;
import lightning.product.M_3212_T;
import lightning.product.O_694_j;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.W_2163_m;
import lightning.product.a_3742_W;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.j_2644_e;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.q_4099_E;
import lightning.product.x_282_a;

public class h_3538_s
extends k_2603_m {
    private static final x_282_a n_1700_B = new F_2904_S("structure_block.structure_name");
    private static final x_282_a J_1907_R = new F_2904_S("structure_block.position");
    private static final x_282_a R_4764_Y = new F_2904_S("structure_block.size");
    private static final x_282_a G_564_y = new F_2904_S("structure_block.integrity");
    private static final x_282_a P_1922_E = new F_2904_S("structure_block.custom_data");
    private static final x_282_a u_1723_Y = new F_2904_S("structure_block.include_entities");
    private static final x_282_a v_4262_N = new F_2904_S("structure_block.detect_size");
    private static final x_282_a w_1484_f = new F_2904_S("structure_block.show_air");
    private static final x_282_a t_148_a = new F_2904_S("structure_block.show_boundingbox");
    private final j_2644_e s_956_w;
    private q_4099_E u_2550_I = q_4099_E.n_1700_B;
    private W_2163_m M_588_G = W_2163_m.n_1700_B;
    private M_3212_T P_4830_p = M_3212_T.G_564_y;
    private boolean h_1847_R;
    private boolean Q_4569_t;
    private boolean M_182_A;
    private O_694_j t_1786_h;
    private O_694_j multiplayerClientSuggestionProvider;
    private O_694_j w_1457_N;
    private O_694_j Y_601_j;
    private O_694_j Y_259_p;
    private O_694_j Q_2552_b;
    private O_694_j C_2741_M;
    private O_694_j k_2293_S;
    private O_694_j q_2307_F;
    private O_694_j Z_875_P;
    private Button c_3005_b;
    private Button H_2857_Y;
    private Button A_4115_X;
    private Button Y_1740_V;
    private Button t_4043_B;
    private Button x_607_J;
    private Button e_4240_b;
    private Button n_3318_d;
    private Button d_2427_y;
    private Button z_1737_N;
    private Button v_4276_D;
    private Button d_2461_k;
    private Button G_624_v;
    private Button T_2506_i;
    private final DecimalFormat q_4610_l = new DecimalFormat("0.0###");

    public h_3538_s(j_2644_e p_i47142_1_) {
        super(new F_2904_S(a_3742_W.l_14_c.P_4830_p()));
        this.s_956_w = p_i47142_1_;
        this.q_4610_l.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT));
    }

    @Override
    public void tick() {
        this.t_1786_h.tick();
        this.multiplayerClientSuggestionProvider.tick();
        this.w_1457_N.tick();
        this.Y_601_j.tick();
        this.Y_259_p.tick();
        this.Q_2552_b.tick();
        this.C_2741_M.tick();
        this.k_2293_S.tick();
        this.q_2307_F.tick();
        this.Z_875_P.tick();
    }

    private void n_1700_B() {
        if (this.n_1700_B(j_2644_e.n_1700_B.n_1700_B)) {
            this.minecraft.n_1700_B((k_2603_m)null);
        }
    }

    private void J_1907_R() {
        this.s_956_w.n_1700_B(this.u_2550_I);
        this.s_956_w.n_1700_B(this.M_588_G);
        this.s_956_w.n_1700_B(this.P_4830_p);
        this.s_956_w.n_1700_B(this.h_1847_R);
        this.s_956_w.G_564_y(this.Q_4569_t);
        this.s_956_w.P_1922_E(this.M_182_A);
        this.minecraft.n_1700_B((k_2603_m)null);
    }

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.c_3005_b = this.addButton(new Button(this.width / 2 - 4 - 150, 210, 150, 20, CommonComponents.R_4764_Y, p_214274_1_ -> this.n_1700_B()));
        this.H_2857_Y = this.addButton(new Button(this.width / 2 + 4, 210, 150, 20, CommonComponents.G_564_y, p_214275_1_ -> this.J_1907_R()));
        this.A_4115_X = this.addButton(new Button(this.width / 2 + 4 + 100, 185, 50, 20, new F_2904_S("structure_block.button.save"), p_214276_1_ -> {
            if (this.s_956_w.Q_4569_t() == M_3212_T.n_1700_B) {
                this.n_1700_B(j_2644_e.n_1700_B.J_1907_R);
                this.minecraft.n_1700_B((k_2603_m)null);
            }
        }));
        this.Y_1740_V = this.addButton(new Button(this.width / 2 + 4 + 100, 185, 50, 20, new F_2904_S("structure_block.button.load"), p_214277_1_ -> {
            if (this.s_956_w.Q_4569_t() == M_3212_T.J_1907_R) {
                this.n_1700_B(j_2644_e.n_1700_B.R_4764_Y);
                this.minecraft.n_1700_B((k_2603_m)null);
            }
        }));
        this.d_2427_y = this.addButton(new Button(this.width / 2 - 4 - 150, 185, 50, 20, new U_2871_b("MODE"), p_214280_1_ -> {
            this.s_956_w.t_1786_h();
            this.w_1484_f();
        }));
        this.z_1737_N = this.addButton(new Button(this.width / 2 + 4 + 100, 120, 50, 20, new F_2904_S("structure_block.button.detect_size"), p_214278_1_ -> {
            if (this.s_956_w.Q_4569_t() == M_3212_T.n_1700_B) {
                this.n_1700_B(j_2644_e.n_1700_B.G_564_y);
                this.minecraft.n_1700_B((k_2603_m)null);
            }
        }));
        this.v_4276_D = this.addButton(new Button(this.width / 2 + 4 + 100, 160, 50, 20, new U_2871_b("ENTITIES"), p_214282_1_ -> {
            this.s_956_w.n_1700_B(!this.s_956_w.multiplayerClientSuggestionProvider());
            this.R_4764_Y();
        }));
        this.d_2461_k = this.addButton(new Button(this.width / 2 - 20, 185, 40, 20, new U_2871_b("MIRROR"), p_214281_1_ -> {
            switch (this.s_956_w.M_588_G()) {
                case n_1700_B: {
                    this.s_956_w.n_1700_B(q_4099_E.J_1907_R);
                    break;
                }
                case J_1907_R: {
                    this.s_956_w.n_1700_B(q_4099_E.R_4764_Y);
                    break;
                }
                case R_4764_Y: {
                    this.s_956_w.n_1700_B(q_4099_E.n_1700_B);
                }
            }
            this.u_1723_Y();
        }));
        this.G_624_v = this.addButton(new Button(this.width / 2 + 4 + 100, 80, 50, 20, new U_2871_b("SHOWAIR"), p_214269_1_ -> {
            this.s_956_w.G_564_y(!this.s_956_w.Z_875_P());
            this.G_564_y();
        }));
        this.T_2506_i = this.addButton(new Button(this.width / 2 + 4 + 100, 80, 50, 20, new U_2871_b("SHOWBB"), p_214270_1_ -> {
            this.s_956_w.P_1922_E(!this.s_956_w.H_2857_Y());
            this.P_1922_E();
        }));
        this.t_4043_B = this.addButton(new Button(this.width / 2 - 1 - 40 - 1 - 40 - 20, 185, 40, 20, new U_2871_b("0"), p_214268_1_ -> {
            this.s_956_w.n_1700_B(W_2163_m.n_1700_B);
            this.v_4262_N();
        }));
        this.x_607_J = this.addButton(new Button(this.width / 2 - 1 - 40 - 20, 185, 40, 20, new U_2871_b("90"), p_214273_1_ -> {
            this.s_956_w.n_1700_B(W_2163_m.J_1907_R);
            this.v_4262_N();
        }));
        this.e_4240_b = this.addButton(new Button(this.width / 2 + 1 + 20, 185, 40, 20, new U_2871_b("180"), p_214272_1_ -> {
            this.s_956_w.n_1700_B(W_2163_m.R_4764_Y);
            this.v_4262_N();
        }));
        this.n_3318_d = this.addButton(new Button(this.width / 2 + 1 + 40 + 1 + 20, 185, 40, 20, new U_2871_b("270"), p_214271_1_ -> {
            this.s_956_w.n_1700_B(W_2163_m.G_564_y);
            this.v_4262_N();
        }));
        this.t_1786_h = new O_694_j(this.font, this.width / 2 - 152, 40, 300, 20, (x_282_a)new F_2904_S("structure_block.structure_name")){

            @Override
            public boolean charTyped(char codePoint, int modifiers) {
                return !h_3538_s.this.isValidCharacterForName(this.getText(), codePoint, this.getCursorPosition()) ? false : super.charTyped(codePoint, modifiers);
            }
        };
        this.t_1786_h.setMaxStringLength(64);
        this.t_1786_h.setText(this.s_956_w.P_1922_E());
        this.children.add(this.t_1786_h);
        c_1514_x blockpos = this.s_956_w.s_956_w();
        this.multiplayerClientSuggestionProvider = new O_694_j(this.font, this.width / 2 - 152, 80, 80, 20, new F_2904_S("structure_block.position.x"));
        this.multiplayerClientSuggestionProvider.setMaxStringLength(15);
        this.multiplayerClientSuggestionProvider.setText(Integer.toString(blockpos.getX()));
        this.children.add(this.multiplayerClientSuggestionProvider);
        this.w_1457_N = new O_694_j(this.font, this.width / 2 - 72, 80, 80, 20, new F_2904_S("structure_block.position.y"));
        this.w_1457_N.setMaxStringLength(15);
        this.w_1457_N.setText(Integer.toString(blockpos.getY()));
        this.children.add(this.w_1457_N);
        this.Y_601_j = new O_694_j(this.font, this.width / 2 + 8, 80, 80, 20, new F_2904_S("structure_block.position.z"));
        this.Y_601_j.setMaxStringLength(15);
        this.Y_601_j.setText(Integer.toString(blockpos.getZ()));
        this.children.add(this.Y_601_j);
        c_1514_x blockpos1 = this.s_956_w.u_2550_I();
        this.Y_259_p = new O_694_j(this.font, this.width / 2 - 152, 120, 80, 20, new F_2904_S("structure_block.size.x"));
        this.Y_259_p.setMaxStringLength(15);
        this.Y_259_p.setText(Integer.toString(blockpos1.getX()));
        this.children.add(this.Y_259_p);
        this.Q_2552_b = new O_694_j(this.font, this.width / 2 - 72, 120, 80, 20, new F_2904_S("structure_block.size.y"));
        this.Q_2552_b.setMaxStringLength(15);
        this.Q_2552_b.setText(Integer.toString(blockpos1.getY()));
        this.children.add(this.Q_2552_b);
        this.C_2741_M = new O_694_j(this.font, this.width / 2 + 8, 120, 80, 20, new F_2904_S("structure_block.size.z"));
        this.C_2741_M.setMaxStringLength(15);
        this.C_2741_M.setText(Integer.toString(blockpos1.getZ()));
        this.children.add(this.C_2741_M);
        this.k_2293_S = new O_694_j(this.font, this.width / 2 - 152, 120, 80, 20, new F_2904_S("structure_block.integrity.integrity"));
        this.k_2293_S.setMaxStringLength(15);
        this.k_2293_S.setText(this.q_4610_l.format(this.s_956_w.w_1457_N()));
        this.children.add(this.k_2293_S);
        this.q_2307_F = new O_694_j(this.font, this.width / 2 - 72, 120, 80, 20, new F_2904_S("structure_block.integrity.seed"));
        this.q_2307_F.setMaxStringLength(31);
        this.q_2307_F.setText(Long.toString(this.s_956_w.Y_601_j()));
        this.children.add(this.q_2307_F);
        this.Z_875_P = new O_694_j(this.font, this.width / 2 - 152, 120, 240, 20, new F_2904_S("structure_block.custom_data"));
        this.Z_875_P.setMaxStringLength(128);
        this.Z_875_P.setText(this.s_956_w.h_1847_R());
        this.children.add(this.Z_875_P);
        this.u_2550_I = this.s_956_w.M_588_G();
        this.u_1723_Y();
        this.M_588_G = this.s_956_w.P_4830_p();
        this.v_4262_N();
        this.P_4830_p = this.s_956_w.Q_4569_t();
        this.w_1484_f();
        this.h_1847_R = this.s_956_w.multiplayerClientSuggestionProvider();
        this.R_4764_Y();
        this.Q_4569_t = this.s_956_w.Z_875_P();
        this.G_564_y();
        this.M_182_A = this.s_956_w.H_2857_Y();
        this.P_1922_E();
        this.n_1700_B(this.t_1786_h);
    }

    @Override
    public void resize(MinecraftClient minecraft, int width, int height) {
        String s = this.t_1786_h.getText();
        String s1 = this.multiplayerClientSuggestionProvider.getText();
        String s2 = this.w_1457_N.getText();
        String s3 = this.Y_601_j.getText();
        String s4 = this.Y_259_p.getText();
        String s5 = this.Q_2552_b.getText();
        String s6 = this.C_2741_M.getText();
        String s7 = this.k_2293_S.getText();
        String s8 = this.q_2307_F.getText();
        String s9 = this.Z_875_P.getText();
        this.init(minecraft, width, height);
        this.t_1786_h.setText(s);
        this.multiplayerClientSuggestionProvider.setText(s1);
        this.w_1457_N.setText(s2);
        this.Y_601_j.setText(s3);
        this.Y_259_p.setText(s4);
        this.Q_2552_b.setText(s5);
        this.C_2741_M.setText(s6);
        this.k_2293_S.setText(s7);
        this.q_2307_F.setText(s8);
        this.Z_875_P.setText(s9);
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    private void R_4764_Y() {
        this.v_4276_D.setMessage(CommonComponents.n_1700_B(!this.s_956_w.multiplayerClientSuggestionProvider()));
    }

    private void G_564_y() {
        this.G_624_v.setMessage(CommonComponents.n_1700_B(this.s_956_w.Z_875_P()));
    }

    private void P_1922_E() {
        this.T_2506_i.setMessage(CommonComponents.n_1700_B(this.s_956_w.H_2857_Y()));
    }

    private void u_1723_Y() {
        q_4099_E mirror = this.s_956_w.M_588_G();
        switch (mirror) {
            case n_1700_B: {
                this.d_2461_k.setMessage(new U_2871_b("|"));
                break;
            }
            case J_1907_R: {
                this.d_2461_k.setMessage(new U_2871_b("< >"));
                break;
            }
            case R_4764_Y: {
                this.d_2461_k.setMessage(new U_2871_b("^ v"));
            }
        }
    }

    private void v_4262_N() {
        this.t_4043_B.active = true;
        this.x_607_J.active = true;
        this.e_4240_b.active = true;
        this.n_3318_d.active = true;
        switch (this.s_956_w.P_4830_p()) {
            case n_1700_B: {
                this.t_4043_B.active = false;
                break;
            }
            case R_4764_Y: {
                this.e_4240_b.active = false;
                break;
            }
            case G_564_y: {
                this.n_3318_d.active = false;
                break;
            }
            case J_1907_R: {
                this.x_607_J.active = false;
            }
        }
    }

    private void w_1484_f() {
        this.t_1786_h.setVisible(false);
        this.multiplayerClientSuggestionProvider.setVisible(false);
        this.w_1457_N.setVisible(false);
        this.Y_601_j.setVisible(false);
        this.Y_259_p.setVisible(false);
        this.Q_2552_b.setVisible(false);
        this.C_2741_M.setVisible(false);
        this.k_2293_S.setVisible(false);
        this.q_2307_F.setVisible(false);
        this.Z_875_P.setVisible(false);
        this.A_4115_X.visible = false;
        this.Y_1740_V.visible = false;
        this.z_1737_N.visible = false;
        this.v_4276_D.visible = false;
        this.d_2461_k.visible = false;
        this.t_4043_B.visible = false;
        this.x_607_J.visible = false;
        this.e_4240_b.visible = false;
        this.n_3318_d.visible = false;
        this.G_624_v.visible = false;
        this.T_2506_i.visible = false;
        switch (this.s_956_w.Q_4569_t()) {
            case n_1700_B: {
                this.t_1786_h.setVisible(true);
                this.multiplayerClientSuggestionProvider.setVisible(true);
                this.w_1457_N.setVisible(true);
                this.Y_601_j.setVisible(true);
                this.Y_259_p.setVisible(true);
                this.Q_2552_b.setVisible(true);
                this.C_2741_M.setVisible(true);
                this.A_4115_X.visible = true;
                this.z_1737_N.visible = true;
                this.v_4276_D.visible = true;
                this.G_624_v.visible = true;
                break;
            }
            case J_1907_R: {
                this.t_1786_h.setVisible(true);
                this.multiplayerClientSuggestionProvider.setVisible(true);
                this.w_1457_N.setVisible(true);
                this.Y_601_j.setVisible(true);
                this.k_2293_S.setVisible(true);
                this.q_2307_F.setVisible(true);
                this.Y_1740_V.visible = true;
                this.v_4276_D.visible = true;
                this.d_2461_k.visible = true;
                this.t_4043_B.visible = true;
                this.x_607_J.visible = true;
                this.e_4240_b.visible = true;
                this.n_3318_d.visible = true;
                this.T_2506_i.visible = true;
                this.v_4262_N();
                break;
            }
            case R_4764_Y: {
                this.t_1786_h.setVisible(true);
                break;
            }
            case G_564_y: {
                this.Z_875_P.setVisible(true);
            }
        }
        this.d_2427_y.setMessage(new F_2904_S("structure_block.mode." + this.s_956_w.Q_4569_t().n_1700_B()));
    }

    private boolean n_1700_B(j_2644_e.n_1700_B p_210143_1_) {
        c_1514_x blockpos = new c_1514_x(this.R_4764_Y(this.multiplayerClientSuggestionProvider.getText()), this.R_4764_Y(this.w_1457_N.getText()), this.R_4764_Y(this.Y_601_j.getText()));
        c_1514_x blockpos1 = new c_1514_x(this.R_4764_Y(this.Y_259_p.getText()), this.R_4764_Y(this.Q_2552_b.getText()), this.R_4764_Y(this.C_2741_M.getText()));
        float f = this.J_1907_R(this.k_2293_S.getText());
        long i = this.n_1700_B(this.q_2307_F.getText());
        this.minecraft.k_2293_S().n_1700_B(new ServerboundSetStructureBlockPacket(this.s_956_w.x_607_J(), p_210143_1_, this.s_956_w.Q_4569_t(), this.t_1786_h.getText(), blockpos, blockpos1, this.s_956_w.M_588_G(), this.s_956_w.P_4830_p(), this.Z_875_P.getText(), this.s_956_w.multiplayerClientSuggestionProvider(), this.s_956_w.Z_875_P(), this.s_956_w.H_2857_Y(), f, i));
        return true;
    }

    private long n_1700_B(String p_189821_1_) {
        try {
            return Long.valueOf(p_189821_1_);
        }
        catch (NumberFormatException numberformatexception) {
            return 0L;
        }
    }

    private float J_1907_R(String p_189819_1_) {
        try {
            return Float.valueOf(p_189819_1_).floatValue();
        }
        catch (NumberFormatException numberformatexception) {
            return 1.0f;
        }
    }

    private int R_4764_Y(String p_189817_1_) {
        try {
            return Integer.parseInt(p_189817_1_);
        }
        catch (NumberFormatException numberformatexception) {
            return 0;
        }
    }

    @Override
    public void closeScreen() {
        this.J_1907_R();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (keyCode != 257 && keyCode != 335) {
            return false;
        }
        this.n_1700_B();
        return true;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        M_3212_T structuremode = this.s_956_w.Q_4569_t();
        h_3538_s.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 10, 0xFFFFFF);
        if (structuremode != M_3212_T.G_564_y) {
            h_3538_s.drawString(matrixStack, this.font, n_1700_B, this.width / 2 - 153, 30, 0xA0A0A0);
            this.t_1786_h.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        if (structuremode == M_3212_T.J_1907_R || structuremode == M_3212_T.n_1700_B) {
            h_3538_s.drawString(matrixStack, this.font, J_1907_R, this.width / 2 - 153, 70, 0xA0A0A0);
            this.multiplayerClientSuggestionProvider.render(matrixStack, mouseX, mouseY, partialTicks);
            this.w_1457_N.render(matrixStack, mouseX, mouseY, partialTicks);
            this.Y_601_j.render(matrixStack, mouseX, mouseY, partialTicks);
            h_3538_s.drawString(matrixStack, this.font, u_1723_Y, this.width / 2 + 154 - this.font.n_1700_B((FormattedText)u_1723_Y), 150, 0xA0A0A0);
        }
        if (structuremode == M_3212_T.n_1700_B) {
            h_3538_s.drawString(matrixStack, this.font, R_4764_Y, this.width / 2 - 153, 110, 0xA0A0A0);
            this.Y_259_p.render(matrixStack, mouseX, mouseY, partialTicks);
            this.Q_2552_b.render(matrixStack, mouseX, mouseY, partialTicks);
            this.C_2741_M.render(matrixStack, mouseX, mouseY, partialTicks);
            h_3538_s.drawString(matrixStack, this.font, v_4262_N, this.width / 2 + 154 - this.font.n_1700_B((FormattedText)v_4262_N), 110, 0xA0A0A0);
            h_3538_s.drawString(matrixStack, this.font, w_1484_f, this.width / 2 + 154 - this.font.n_1700_B((FormattedText)w_1484_f), 70, 0xA0A0A0);
        }
        if (structuremode == M_3212_T.J_1907_R) {
            h_3538_s.drawString(matrixStack, this.font, G_564_y, this.width / 2 - 153, 110, 0xA0A0A0);
            this.k_2293_S.render(matrixStack, mouseX, mouseY, partialTicks);
            this.q_2307_F.render(matrixStack, mouseX, mouseY, partialTicks);
            h_3538_s.drawString(matrixStack, this.font, t_148_a, this.width / 2 + 154 - this.font.n_1700_B((FormattedText)t_148_a), 70, 0xA0A0A0);
        }
        if (structuremode == M_3212_T.G_564_y) {
            h_3538_s.drawString(matrixStack, this.font, P_1922_E, this.width / 2 - 153, 110, 0xA0A0A0);
            this.Z_875_P.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        h_3538_s.drawString(matrixStack, this.font, structuremode.J_1907_R(), this.width / 2 - 153, 174, 0xA0A0A0);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}



