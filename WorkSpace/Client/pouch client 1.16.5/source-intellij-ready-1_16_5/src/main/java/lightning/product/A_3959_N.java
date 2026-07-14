/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.C_1162_e;
import lightning.product.C_3538_G;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.J_2011_a;
import lightning.product.K_1289_S;
import lightning.product.ObjectSelectionList;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.o_2488_o;
import lightning.product.CommonComponents;
import lightning.product.NarrationHelper;
import lightning.product.RealmsLabel;
import lightning.product.w_728_N;
import lightning.product.x_282_a;
import lightning.product.z_3470_q;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A_3959_N
extends RealmsScreen {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final x_282_a J_1907_R = new F_2904_S("selectWorld.world");
    private static final x_282_a R_4764_Y = new F_2904_S("selectWorld.conversion");
    private static final x_282_a G_564_y = new F_2904_S("mco.upload.hardcore").n_1700_B(D_4024_W.P_1922_E);
    private static final x_282_a P_1922_E = new F_2904_S("selectWorld.cheats");
    private static final DateFormat u_1723_Y = new SimpleDateFormat();
    private final C_3538_G v_4262_N;
    private final long w_1484_f;
    private final int t_148_a;
    private Button s_956_w;
    private List<J_2011_a> u_2550_I = Lists.newArrayList();
    private int M_588_G = -1;
    private J_1907_R P_4830_p;
    private RealmsLabel h_1847_R;
    private RealmsLabel Q_4569_t;
    private RealmsLabel M_182_A;
    private final Runnable t_1786_h;

    public A_3959_N(long p_i232219_1_, int p_i232219_3_, C_3538_G p_i232219_4_, Runnable p_i232219_5_) {
        this.v_4262_N = p_i232219_4_;
        this.w_1484_f = p_i232219_1_;
        this.t_148_a = p_i232219_3_;
        this.t_1786_h = p_i232219_5_;
    }

    private void n_1700_B() throws Exception {
        this.u_2550_I = this.minecraft.t_148_a().n_1700_B().stream().sorted((p_237970_0_, p_237970_1_) -> {
            if (p_237970_0_.P_1922_E() < p_237970_1_.P_1922_E()) {
                return 1;
            }
            return p_237970_0_.P_1922_E() > p_237970_1_.P_1922_E() ? -1 : p_237970_0_.n_1700_B().compareTo(p_237970_1_.n_1700_B());
        }).collect(Collectors.toList());
        for (J_2011_a worldsummary : this.u_2550_I) {
            this.P_4830_p.n_1700_B(worldsummary);
        }
    }

    @Override
    public void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.P_4830_p = new J_1907_R();
        try {
            this.n_1700_B();
        }
        catch (Exception exception) {
            n_1700_B.error("Couldn't load level list", (Throwable)exception);
            this.minecraft.n_1700_B(new w_728_N(new U_2871_b("Unable to load worlds"), x_282_a.J_1907_R(exception.getMessage()), this.v_4262_N));
            return;
        }
        this.addListener(this.P_4830_p);
        this.s_956_w = this.addButton(new Button(this.width / 2 - 154, this.height - 32, 153, 20, new F_2904_S("mco.upload.button.name"), p_237976_1_ -> this.J_1907_R()));
        this.s_956_w.active = this.M_588_G >= 0 && this.M_588_G < this.u_2550_I.size();
        this.addButton(new Button(this.width / 2 + 6, this.height - 32, 153, 20, CommonComponents.w_1484_f, p_237973_1_ -> this.minecraft.n_1700_B(this.v_4262_N)));
        this.h_1847_R = this.addListener(new RealmsLabel(new F_2904_S("mco.upload.select.world.title"), this.width / 2, 13, 0xFFFFFF));
        this.Q_4569_t = this.addListener(new RealmsLabel(new F_2904_S("mco.upload.select.world.subtitle"), this.width / 2, A_3959_N.G_564_y(-1), 0xA0A0A0));
        this.M_182_A = this.u_2550_I.isEmpty() ? this.addListener(new RealmsLabel(new F_2904_S("mco.upload.select.world.none"), this.width / 2, this.height / 2 - 20, 0xFFFFFF)) : null;
        this.P_1922_E();
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    private void J_1907_R() {
        if (this.M_588_G != -1 && !this.u_2550_I.get(this.M_588_G).v_4262_N()) {
            J_2011_a worldsummary = this.u_2550_I.get(this.M_588_G);
            this.minecraft.n_1700_B(new C_1162_e(this.w_1484_f, this.t_148_a, this.v_4262_N, worldsummary, this.t_1786_h));
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.P_4830_p.render(matrixStack, mouseX, mouseY, partialTicks);
        this.h_1847_R.n_1700_B(this, matrixStack);
        this.Q_4569_t.n_1700_B(this, matrixStack);
        if (this.M_182_A != null) {
            this.M_182_A.n_1700_B(this, matrixStack);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.n_1700_B(this.v_4262_N);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private static x_282_a n_1700_B(J_2011_a p_237977_0_) {
        return p_237977_0_.u_1723_Y().R_4764_Y();
    }

    private static String J_1907_R(J_2011_a p_237979_0_) {
        return u_1723_Y.format(new Date(p_237979_0_.P_1922_E()));
    }

    class J_1907_R
    extends z_3470_q<n_1700_B> {
        public J_1907_R() {
            super(A_3959_N.this.width, A_3959_N.this.height, A_3959_N.G_564_y(0), A_3959_N.this.height - 40, 36);
        }

        public void n_1700_B(J_2011_a p_237986_1_) {
            A_3959_N a_3959_N = A_3959_N.this;
            Objects.requireNonNull(a_3959_N);
            this.n_1700_B(a_3959_N.new n_1700_B(p_237986_1_));
        }

        @Override
        public int getMaxPosition() {
            return A_3959_N.this.u_2550_I.size() * 36;
        }

        @Override
        public boolean isFocused() {
            return A_3959_N.this.getListener() == this;
        }

        @Override
        public void renderBackground(g_221_o p_230433_1_) {
            A_3959_N.this.renderBackground(p_230433_1_);
        }

        @Override
        public void n_1700_B(int p_231400_1_) {
            this.G_564_y(p_231400_1_);
            if (p_231400_1_ != -1) {
                J_2011_a worldsummary = A_3959_N.this.u_2550_I.get(p_231400_1_);
                String s = K_1289_S.n_1700_B("narrator.select.list.position", p_231400_1_ + 1, A_3959_N.this.u_2550_I.size());
                String s1 = NarrationHelper.J_1907_R(Arrays.asList(worldsummary.J_1907_R(), A_3959_N.J_1907_R(worldsummary), A_3959_N.n_1700_B(worldsummary).getString(), s));
                NarrationHelper.n_1700_B(K_1289_S.n_1700_B("narrator.select", s1));
            }
        }

        public void n_1700_B(@Nullable n_1700_B entry) {
            super.setSelected(entry);
            A_3959_N.this.M_588_G = this.getEventListeners().indexOf(entry);
            A_3959_N.this.s_956_w.active = A_3959_N.this.M_588_G >= 0 && A_3959_N.this.M_588_G < this.getItemCount() && !A_3959_N.this.u_2550_I.get(A_3959_N.this.M_588_G).v_4262_N();
        }

        @Override
        public /* synthetic */ void setSelected(@Nullable o_2488_o.n_1700_B n_1700_B2) {
            this.n_1700_B((n_1700_B)n_1700_B2);
        }
    }

    class n_1700_B
    extends ObjectSelectionList.n_1700_B<n_1700_B> {
        private final J_2011_a J_1907_R;
        private final String R_4764_Y;
        private final String G_564_y;
        private final x_282_a P_1922_E;

        public n_1700_B(J_2011_a p_i232220_2_) {
            this.J_1907_R = p_i232220_2_;
            this.R_4764_Y = p_i232220_2_.J_1907_R();
            this.G_564_y = p_i232220_2_.n_1700_B() + " (" + A_3959_N.J_1907_R(p_i232220_2_) + ")";
            if (p_i232220_2_.G_564_y()) {
                this.P_1922_E = R_4764_Y;
            } else {
                x_282_a itextcomponent = p_i232220_2_.v_4262_N() ? G_564_y : A_3959_N.n_1700_B(p_i232220_2_);
                if (p_i232220_2_.w_1484_f()) {
                    itextcomponent = itextcomponent.P_1922_E().n_1700_B(", ").n_1700_B(P_1922_E);
                }
                this.P_1922_E = itextcomponent;
            }
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            this.n_1700_B(p_230432_1_, this.J_1907_R, p_230432_2_, p_230432_4_, p_230432_3_);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            A_3959_N.this.P_4830_p.n_1700_B(A_3959_N.this.u_2550_I.indexOf(this.J_1907_R));
            return true;
        }

        protected void n_1700_B(g_221_o p_237985_1_, J_2011_a p_237985_2_, int p_237985_3_, int p_237985_4_, int p_237985_5_) {
            Object s = this.R_4764_Y.isEmpty() ? String.valueOf(J_1907_R) + " " + (p_237985_3_ + 1) : this.R_4764_Y;
            A_3959_N.this.font.J_1907_R(p_237985_1_, (String)s, (float)(p_237985_4_ + 2), (float)(p_237985_5_ + 1), 0xFFFFFF);
            A_3959_N.this.font.J_1907_R(p_237985_1_, this.G_564_y, (float)(p_237985_4_ + 2), (float)(p_237985_5_ + 12), 0x808080);
            A_3959_N.this.font.J_1907_R(p_237985_1_, this.P_1922_E, (float)(p_237985_4_ + 2), (float)(p_237985_5_ + 12 + 10), 0x808080);
        }
    }
}


