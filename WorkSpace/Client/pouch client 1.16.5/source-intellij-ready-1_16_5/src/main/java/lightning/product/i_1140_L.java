/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.B_477_D;
import lightning.product.PageButton;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.MutableComponent;
import lightning.product.I_1084_e;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.Button;
import lightning.product.Z_1567_W;
import lightning.product.Z_1993_T;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.i_2909_p;
import lightning.product.k_2603_m;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.CommonComponents;
import lightning.product.Items;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class i_1140_L
extends k_2603_m {
    public static final n_1700_B n_1700_B = new n_1700_B(){

        @Override
        public int n_1700_B() {
            return 0;
        }

        @Override
        public FormattedText n_1700_B(int p_230456_1_) {
            return FormattedText.J_1907_R;
        }
    };
    public static final g_2336_b J_1907_R = new g_2336_b("textures/gui/book.png");
    private n_1700_B R_4764_Y;
    private int G_564_y;
    private List<FormattedCharSequence> P_1922_E = Collections.emptyList();
    private int u_1723_Y = -1;
    private x_282_a v_4262_N = U_2871_b.R_4764_Y;
    private PageButton w_1484_f;
    private PageButton t_148_a;
    private final boolean s_956_w;

    public i_1140_L(n_1700_B bookInfoIn) {
        this(bookInfoIn, true);
    }

    public i_1140_L() {
        this(n_1700_B, false);
    }

    private i_1140_L(n_1700_B bookInfoIn, boolean pageTurnSoundsIn) {
        super(I_1084_e.n_1700_B);
        this.R_4764_Y = bookInfoIn;
        this.s_956_w = pageTurnSoundsIn;
    }

    public void n_1700_B(n_1700_B p_214155_1_) {
        this.R_4764_Y = p_214155_1_;
        this.G_564_y = u_530_F.n_1700_B(this.G_564_y, 0, p_214155_1_.n_1700_B());
        this.J_1907_R();
        this.u_1723_Y = -1;
    }

    public boolean J_1907_R(int pageNum) {
        int i = u_530_F.n_1700_B(pageNum, 0, this.R_4764_Y.n_1700_B() - 1);
        if (i != this.G_564_y) {
            this.G_564_y = i;
            this.J_1907_R();
            this.u_1723_Y = -1;
            return true;
        }
        return false;
    }

    protected boolean n_1700_B(int pageNum) {
        return this.J_1907_R(pageNum);
    }

    @Override
    protected void init() {
        this.R_4764_Y();
        this.u_1723_Y();
    }

    protected void R_4764_Y() {
        this.addButton(new Button(this.width / 2 - 100, 196, 200, 20, CommonComponents.R_4764_Y, p_214161_1_ -> this.minecraft.n_1700_B((k_2603_m)null)));
    }

    protected void u_1723_Y() {
        int i = (this.width - 192) / 2;
        int j = 2;
        this.w_1484_f = this.addButton(new PageButton(i + 116, 159, true, p_214159_1_ -> this.P_1922_E(), this.s_956_w));
        this.t_148_a = this.addButton(new PageButton(i + 43, 159, false, p_214158_1_ -> this.G_564_y(), this.s_956_w));
        this.J_1907_R();
    }

    private int n_1700_B() {
        return this.R_4764_Y.n_1700_B();
    }

    protected void G_564_y() {
        if (this.G_564_y > 0) {
            --this.G_564_y;
        }
        this.J_1907_R();
    }

    protected void P_1922_E() {
        if (this.G_564_y < this.n_1700_B() - 1) {
            ++this.G_564_y;
        }
        this.J_1907_R();
    }

    private void J_1907_R() {
        this.w_1484_f.visible = this.G_564_y < this.n_1700_B() - 1;
        this.t_148_a.visible = this.G_564_y > 0;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        switch (keyCode) {
            case 266: {
                this.t_148_a.onPress();
                return true;
            }
            case 267: {
                this.w_1484_f.onPress();
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(J_1907_R);
        int i = (this.width - 192) / 2;
        int j = 2;
        this.blit(matrixStack, i, 2, 0, 0, 192, 192);
        if (this.u_1723_Y != this.G_564_y) {
            FormattedText itextproperties = this.R_4764_Y.J_1907_R(this.G_564_y);
            this.P_1922_E = this.font.J_1907_R(itextproperties, 114);
            this.v_4262_N = new F_2904_S("book.pageIndicator", this.G_564_y + 1, Math.max(this.n_1700_B(), 1));
        }
        this.u_1723_Y = this.G_564_y;
        int i1 = this.font.n_1700_B((FormattedText)this.v_4262_N);
        this.font.J_1907_R(matrixStack, this.v_4262_N, (float)(i - i1 + 192 - 44), 18.0f, 0);
        int k = Math.min(14, this.P_1922_E.size());
        for (int l = 0; l < k; ++l) {
            FormattedCharSequence ireorderingprocessor = this.P_1922_E.get(l);
            this.font.J_1907_R(matrixStack, ireorderingprocessor, (float)(i + 36), (float)(32 + l * 9), 0);
        }
        Z_1567_W style = this.R_4764_Y(mouseX, mouseY);
        if (style != null) {
            this.renderComponentHoverEffect(matrixStack, style, mouseX, mouseY);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        Z_1567_W style;
        if (button == 0 && (style = this.R_4764_Y(mouseX, mouseY)) != null && this.handleComponentClicked(style)) {
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean handleComponentClicked(Z_1567_W style) {
        i_2909_p clickevent = style.w_1484_f();
        if (clickevent == null) {
            return false;
        }
        if (clickevent.n_1700_B() == i_2909_p.n_1700_B.P_1922_E) {
            String s = clickevent.J_1907_R();
            try {
                int i = Integer.parseInt(s) - 1;
                return this.n_1700_B(i);
            }
            catch (Exception exception) {
                return false;
            }
        }
        boolean flag = super.handleComponentClicked(style);
        if (flag && clickevent.n_1700_B() == i_2909_p.n_1700_B.R_4764_Y) {
            this.minecraft.n_1700_B((k_2603_m)null);
        }
        return flag;
    }

    @Nullable
    public Z_1567_W R_4764_Y(double p_238805_1_, double p_238805_3_) {
        if (this.P_1922_E.isEmpty()) {
            return null;
        }
        int i = u_530_F.R_4764_Y(p_238805_1_ - (double)((this.width - 192) / 2) - 36.0);
        int j = u_530_F.R_4764_Y(p_238805_3_ - 2.0 - 30.0);
        if (i >= 0 && j >= 0) {
            int k = Math.min(14, this.P_1922_E.size());
            if (i <= 114 && j < 9 * k + k) {
                int l = j / 9;
                if (l >= 0 && l < this.P_1922_E.size()) {
                    FormattedCharSequence ireorderingprocessor = this.P_1922_E.get(l);
                    return this.minecraft.t_148_a.J_1907_R().n_1700_B(ireorderingprocessor, i);
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public static List<String> n_1700_B(U_2912_j p_214157_0_) {
        q_2896_o listnbt = p_214157_0_.G_564_y("pages", 8).G_564_y();
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < listnbt.size(); ++i) {
            builder.add((Object)listnbt.t_148_a(i));
        }
        return builder.build();
    }

    public static interface n_1700_B {
        public int n_1700_B();

        public FormattedText n_1700_B(int var1);

        default public FormattedText J_1907_R(int p_238806_1_) {
            return p_238806_1_ >= 0 && p_238806_1_ < this.n_1700_B() ? this.n_1700_B(p_238806_1_) : FormattedText.J_1907_R;
        }

        public static n_1700_B n_1700_B(Z_1993_T p_216917_0_) {
            q_1613_l item = p_216917_0_.J_1907_R();
            if (item == Items.CryingObsidianBlock) {
                return new R_4764_Y(p_216917_0_);
            }
            return item == Items.CropBlock ? new J_1907_R(p_216917_0_) : n_1700_B;
        }
    }

    public static class R_4764_Y
    implements n_1700_B {
        private final List<String> n_1700_B;

        public R_4764_Y(Z_1993_T p_i50616_1_) {
            this.n_1700_B = lightning.product.i_1140_L$R_4764_Y.J_1907_R(p_i50616_1_);
        }

        private static List<String> J_1907_R(Z_1993_T stack) {
            U_2912_j compoundnbt = stack.Q_4569_t();
            return compoundnbt != null && B_477_D.n_1700_B(compoundnbt) ? i_1140_L.n_1700_B(compoundnbt) : ImmutableList.of((Object)x_282_a.n_1700_B.n_1700_B(new F_2904_S("book.invalid.tag").n_1700_B(D_4024_W.P_1922_E)));
        }

        @Override
        public int n_1700_B() {
            return this.n_1700_B.size();
        }

        @Override
        public FormattedText n_1700_B(int p_230456_1_) {
            String s = this.n_1700_B.get(p_230456_1_);
            try {
                MutableComponent itextproperties = x_282_a.n_1700_B.n_1700_B(s);
                if (itextproperties != null) {
                    return itextproperties;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            return FormattedText.R_4764_Y(s);
        }
    }

    public static class J_1907_R
    implements n_1700_B {
        private final List<String> n_1700_B;

        public J_1907_R(Z_1993_T p_i50617_1_) {
            this.n_1700_B = lightning.product.i_1140_L$J_1907_R.J_1907_R(p_i50617_1_);
        }

        private static List<String> J_1907_R(Z_1993_T p_216919_0_) {
            U_2912_j compoundnbt = p_216919_0_.Q_4569_t();
            return compoundnbt != null ? i_1140_L.n_1700_B(compoundnbt) : ImmutableList.of();
        }

        @Override
        public int n_1700_B() {
            return this.n_1700_B.size();
        }

        @Override
        public FormattedText n_1700_B(int p_230456_1_) {
            return FormattedText.R_4764_Y(this.n_1700_B.get(p_230456_1_));
        }
    }
}


