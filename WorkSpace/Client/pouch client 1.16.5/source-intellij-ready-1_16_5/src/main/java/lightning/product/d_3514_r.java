/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.I_1084_e;
import lightning.product.ServerboundSetJigsawBlockPacket;
import lightning.product.O_694_j;
import lightning.product.U_2871_b;
import lightning.product.V_182_a;
import lightning.product.Button;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.JigsawBlock;
import lightning.product.JigsawBlockEntity;
import lightning.product.CommonComponents;
import lightning.product.AbstractSliderButton;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class d_3514_r
extends k_2603_m {
    private static final x_282_a n_1700_B = new F_2904_S("jigsaw_block.joint_label");
    private static final x_282_a J_1907_R = new F_2904_S("jigsaw_block.pool");
    private static final x_282_a R_4764_Y = new F_2904_S("jigsaw_block.name");
    private static final x_282_a G_564_y = new F_2904_S("jigsaw_block.target");
    private static final x_282_a P_1922_E = new F_2904_S("jigsaw_block.final_state");
    private final JigsawBlockEntity u_1723_Y;
    private O_694_j v_4262_N;
    private O_694_j w_1484_f;
    private O_694_j t_148_a;
    private O_694_j s_956_w;
    private int u_2550_I;
    private boolean M_588_G = true;
    private Button P_4830_p;
    private Button h_1847_R;
    private JigsawBlockEntity.n_1700_B Q_4569_t;

    public d_3514_r(JigsawBlockEntity p_i51083_1_) {
        super(I_1084_e.n_1700_B);
        this.u_1723_Y = p_i51083_1_;
    }

    @Override
    public void tick() {
        this.v_4262_N.tick();
        this.w_1484_f.tick();
        this.t_148_a.tick();
        this.s_956_w.tick();
    }

    private void n_1700_B() {
        this.R_4764_Y();
        this.minecraft.n_1700_B((k_2603_m)null);
    }

    private void J_1907_R() {
        this.minecraft.n_1700_B((k_2603_m)null);
    }

    private void R_4764_Y() {
        this.minecraft.k_2293_S().n_1700_B(new ServerboundSetJigsawBlockPacket(this.u_1723_Y.x_607_J(), new g_2336_b(this.v_4262_N.getText()), new g_2336_b(this.w_1484_f.getText()), new g_2336_b(this.t_148_a.getText()), this.s_956_w.getText(), this.Q_4569_t));
    }

    private void G_564_y() {
        this.minecraft.k_2293_S().n_1700_B(new V_182_a(this.u_1723_Y.x_607_J(), this.u_2550_I, this.M_588_G));
    }

    @Override
    public void closeScreen() {
        this.J_1907_R();
    }

    @Override
    protected void init() {
        boolean flag;
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.t_148_a = new O_694_j(this.font, this.width / 2 - 152, 20, 300, 20, new F_2904_S("jigsaw_block.pool"));
        this.t_148_a.setMaxStringLength(128);
        this.t_148_a.setText(this.u_1723_Y.w_1484_f().toString());
        this.t_148_a.setResponder(p_238833_1_ -> this.P_1922_E());
        this.children.add(this.t_148_a);
        this.v_4262_N = new O_694_j(this.font, this.width / 2 - 152, 55, 300, 20, new F_2904_S("jigsaw_block.name"));
        this.v_4262_N.setMaxStringLength(128);
        this.v_4262_N.setText(this.u_1723_Y.P_1922_E().toString());
        this.v_4262_N.setResponder(p_238830_1_ -> this.P_1922_E());
        this.children.add(this.v_4262_N);
        this.w_1484_f = new O_694_j(this.font, this.width / 2 - 152, 90, 300, 20, new F_2904_S("jigsaw_block.target"));
        this.w_1484_f.setMaxStringLength(128);
        this.w_1484_f.setText(this.u_1723_Y.v_4262_N().toString());
        this.w_1484_f.setResponder(p_214254_1_ -> this.P_1922_E());
        this.children.add(this.w_1484_f);
        this.s_956_w = new O_694_j(this.font, this.width / 2 - 152, 125, 300, 20, new F_2904_S("jigsaw_block.final_state"));
        this.s_956_w.setMaxStringLength(256);
        this.s_956_w.setText(this.u_1723_Y.s_956_w());
        this.children.add(this.s_956_w);
        this.Q_4569_t = this.u_1723_Y.u_2550_I();
        int i = this.font.n_1700_B((FormattedText)n_1700_B) + 10;
        this.P_4830_p = this.addButton(new Button(this.width / 2 - 152 + i, 150, 300 - i, 20, this.u_1723_Y(), p_238834_1_ -> {
            JigsawBlockEntity.n_1700_B[] ajigsawtileentity$orientationtype = JigsawBlockEntity.n_1700_B.values();
            int j = (this.Q_4569_t.ordinal() + 1) % ajigsawtileentity$orientationtype.length;
            this.Q_4569_t = ajigsawtileentity$orientationtype[j];
            p_238834_1_.setMessage(this.u_1723_Y());
        }));
        this.P_4830_p.active = flag = JigsawBlock.w_1484_f(this.u_1723_Y.e_4240_b()).h_1847_R().R_4764_Y();
        this.P_4830_p.visible = flag;
        this.addButton(new AbstractSliderButton(this.width / 2 - 154, 180, 100, 20, U_2871_b.R_4764_Y, 0.0){
            {
                this.func_230979_b_();
            }

            @Override
            protected void func_230979_b_() {
                this.setMessage(new F_2904_S("jigsaw_block.levels", d_3514_r.this.u_2550_I));
            }

            @Override
            protected void func_230972_a_() {
                d_3514_r.this.u_2550_I = u_530_F.R_4764_Y(u_530_F.J_1907_R(0.0, 7.0, this.sliderValue));
            }
        });
        this.addButton(new Button(this.width / 2 - 50, 180, 100, 20, new F_2904_S("jigsaw_block.keep_jigsaws"), p_238832_1_ -> {
            this.M_588_G = !this.M_588_G;
            p_238832_1_.queueNarration(250);
        }){

            @Override
            public x_282_a getMessage() {
                return CommonComponents.n_1700_B(super.getMessage(), d_3514_r.this.M_588_G);
            }
        });
        this.addButton(new Button(this.width / 2 + 54, 180, 100, 20, new F_2904_S("jigsaw_block.generate"), p_238831_1_ -> {
            this.n_1700_B();
            this.G_564_y();
        }));
        this.h_1847_R = this.addButton(new Button(this.width / 2 - 4 - 150, 210, 150, 20, CommonComponents.R_4764_Y, p_238828_1_ -> this.n_1700_B()));
        this.addButton(new Button(this.width / 2 + 4, 210, 150, 20, CommonComponents.G_564_y, p_238825_1_ -> this.J_1907_R()));
        this.n_1700_B(this.t_148_a);
        this.P_1922_E();
    }

    private void P_1922_E() {
        this.h_1847_R.active = g_2336_b.R_4764_Y(this.v_4262_N.getText()) && g_2336_b.R_4764_Y(this.w_1484_f.getText()) && g_2336_b.R_4764_Y(this.t_148_a.getText());
    }

    @Override
    public void resize(MinecraftClient minecraft, int width, int height) {
        String s = this.v_4262_N.getText();
        String s1 = this.w_1484_f.getText();
        String s2 = this.t_148_a.getText();
        String s3 = this.s_956_w.getText();
        int i = this.u_2550_I;
        JigsawBlockEntity.n_1700_B jigsawtileentity$orientationtype = this.Q_4569_t;
        this.init(minecraft, width, height);
        this.v_4262_N.setText(s);
        this.w_1484_f.setText(s1);
        this.t_148_a.setText(s2);
        this.s_956_w.setText(s3);
        this.u_2550_I = i;
        this.Q_4569_t = jigsawtileentity$orientationtype;
        this.P_4830_p.setMessage(this.u_1723_Y());
    }

    private x_282_a u_1723_Y() {
        return new F_2904_S("jigsaw_block.joint." + this.Q_4569_t.n_1700_B());
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (!this.h_1847_R.active || keyCode != 257 && keyCode != 335) {
            return false;
        }
        this.n_1700_B();
        return true;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        d_3514_r.drawString(matrixStack, this.font, J_1907_R, this.width / 2 - 153, 10, 0xA0A0A0);
        this.t_148_a.render(matrixStack, mouseX, mouseY, partialTicks);
        d_3514_r.drawString(matrixStack, this.font, R_4764_Y, this.width / 2 - 153, 45, 0xA0A0A0);
        this.v_4262_N.render(matrixStack, mouseX, mouseY, partialTicks);
        d_3514_r.drawString(matrixStack, this.font, G_564_y, this.width / 2 - 153, 80, 0xA0A0A0);
        this.w_1484_f.render(matrixStack, mouseX, mouseY, partialTicks);
        d_3514_r.drawString(matrixStack, this.font, P_1922_E, this.width / 2 - 153, 115, 0xA0A0A0);
        this.s_956_w.render(matrixStack, mouseX, mouseY, partialTicks);
        if (JigsawBlock.w_1484_f(this.u_1723_Y.e_4240_b()).h_1847_R().R_4764_Y()) {
            d_3514_r.drawString(matrixStack, this.font, n_1700_B, this.width / 2 - 153, 156, 0xFFFFFF);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}



