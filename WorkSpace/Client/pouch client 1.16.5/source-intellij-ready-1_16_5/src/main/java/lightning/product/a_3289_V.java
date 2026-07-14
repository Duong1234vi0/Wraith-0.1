/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.OptionsSubScreen;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.I_1084_e;
import lightning.product.ObjectSelectionList;
import lightning.product.M_2935_g;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.V_4423_d;
import lightning.product.Y_4729_x;
import lightning.product.b_164_E;
import lightning.product.MinecraftAccess;
import lightning.product.MinecraftClient;
import lightning.product.LanguageManager;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.o_2488_o;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class a_3289_V
extends OptionsSubScreen {
    private static final x_282_a n_1700_B = new U_2871_b("(").n_1700_B(new F_2904_S("options.languageWarning")).n_1700_B(")").n_1700_B(D_4024_W.w_1484_f);
    private n_1700_B J_1907_R;
    private final LanguageManager P_1922_E;
    private Y_4729_x u_1723_Y;
    private Button v_4262_N;

    public a_3289_V(k_2603_m screen, V_4423_d gameSettingsObj, LanguageManager manager) {
        super(screen, gameSettingsObj, new F_2904_S("options.language"));
        this.P_1922_E = manager;
    }

    @Override
    protected void init() {
        this.J_1907_R = new n_1700_B(this.minecraft);
        this.children.add(this.J_1907_R);
        this.u_1723_Y = this.addButton(new Y_4729_x(this.width / 2 - 155, this.height - 38, 150, 20, M_2935_g.FORCE_UNICODE_FONT, M_2935_g.FORCE_UNICODE_FONT.R_4764_Y(this.G_564_y), p_213037_1_ -> {
            M_2935_g.FORCE_UNICODE_FONT.n_1700_B(this.G_564_y);
            this.G_564_y.J_1907_R();
            p_213037_1_.setMessage(M_2935_g.FORCE_UNICODE_FONT.R_4764_Y(this.G_564_y));
            this.minecraft.u_2550_I();
        }));
        this.v_4262_N = this.addButton(new Button(this.width / 2 - 155 + 160, this.height - 38, 150, 20, CommonComponents.R_4764_Y, p_213036_1_ -> {
            n_1700_B.n_1700_B languagescreen$list$languageentry = (n_1700_B.n_1700_B)this.J_1907_R.getSelected();
            if (languagescreen$list$languageentry != null && !languagescreen$list$languageentry.J_1907_R.getCode().equals(this.P_1922_E.J_1907_R().getCode())) {
                this.P_1922_E.n_1700_B(languagescreen$list$languageentry.J_1907_R);
                this.G_564_y.RealmsConfirmScreen = languagescreen$list$languageentry.J_1907_R.getCode();
                MinecraftAccess.c_3005_b.e_2887_G().onResourceManagerReload(MinecraftAccess.c_3005_b.T_2506_i());
                this.v_4262_N.setMessage(CommonComponents.R_4764_Y);
                this.u_1723_Y.setMessage(M_2935_g.FORCE_UNICODE_FONT.R_4764_Y(this.G_564_y));
                this.G_564_y.J_1907_R();
            }
            this.minecraft.n_1700_B(this.R_4764_Y);
        }));
        super.init();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.J_1907_R.render(matrixStack, mouseX, mouseY, partialTicks);
        a_3289_V.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 16, 0xFFFFFF);
        a_3289_V.drawCenteredString(matrixStack, this.font, n_1700_B, this.width / 2, this.height - 56, 0x808080);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    class lightning.product.a_3289_V$n_1700_B
    extends ObjectSelectionList<n_1700_B> {
        public lightning.product.a_3289_V$n_1700_B(MinecraftClient mcIn) {
            super(mcIn, a_3289_V.this.width, a_3289_V.this.height, 32, a_3289_V.this.height - 65 + 4, 18);
            for (b_164_E language : a_3289_V.this.P_1922_E.R_4764_Y()) {
                n_1700_B languagescreen$list$languageentry = new n_1700_B(language);
                this.addEntry(languagescreen$list$languageentry);
                if (!a_3289_V.this.P_1922_E.J_1907_R().getCode().equals(language.getCode())) continue;
                this.n_1700_B(languagescreen$list$languageentry);
            }
            if (this.getSelected() != null) {
                this.centerScrollOn((n_1700_B)this.getSelected());
            }
        }

        @Override
        protected int getScrollbarPosition() {
            return super.getScrollbarPosition() + 20;
        }

        @Override
        public int getRowWidth() {
            return super.getRowWidth() + 50;
        }

        public void n_1700_B(@Nullable n_1700_B entry) {
            super.setSelected(entry);
            if (entry != null) {
                I_1084_e.J_1907_R.n_1700_B(new F_2904_S("narrator.select", entry.J_1907_R).getString());
            }
        }

        @Override
        protected void renderBackground(g_221_o p_230433_1_) {
            a_3289_V.this.renderBackground(p_230433_1_);
        }

        @Override
        protected boolean isFocused() {
            return a_3289_V.this.getListener() == this;
        }

        @Override
        public /* synthetic */ void setSelected(@Nullable o_2488_o.n_1700_B n_1700_B2) {
            this.n_1700_B((n_1700_B)n_1700_B2);
        }

        public class n_1700_B
        extends ObjectSelectionList.n_1700_B<n_1700_B> {
            private final b_164_E J_1907_R;

            public n_1700_B(b_164_E p_i50494_2_) {
                this.J_1907_R = p_i50494_2_;
            }

            @Override
            public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
                String s = this.J_1907_R.toString();
                a_3289_V.this.font.n_1700_B(p_230432_1_, s, (float)(n_1700_B.this.width / 2 - a_3289_V.this.font.J_1907_R(s) / 2), (float)(p_230432_3_ + 1), 0xFFFFFF, true);
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                if (button == 0) {
                    this.n_1700_B();
                    return true;
                }
                return false;
            }

            private void n_1700_B() {
                n_1700_B.this.n_1700_B(this);
            }
        }
    }
}



