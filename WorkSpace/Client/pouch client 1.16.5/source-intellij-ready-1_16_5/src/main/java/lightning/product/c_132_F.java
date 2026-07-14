/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.D_60_a;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.H_1883_T;
import lightning.product.RealmsLongRunningMcoTaskScreen;
import lightning.product.K_1289_S;
import lightning.product.ObjectSelectionList;
import lightning.product.M_2677_i;
import lightning.product.DownloadTask;
import lightning.product.Button;
import lightning.product.W_3464_O;
import lightning.product.c_4037_x;
import lightning.product.RealmsLongConfirmationScreen;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.RealmsScreen;
import lightning.product.o_2488_o;
import lightning.product.o_4801_T;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.NarrationHelper;
import lightning.product.u_744_e;
import lightning.product.RealmsLabel;
import lightning.product.x_282_a;
import lightning.product.z_3470_q;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class c_132_F
extends RealmsScreen {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final g_2336_b J_1907_R = new g_2336_b("realms", "textures/gui/realms/plus_icon.png");
    private static final g_2336_b R_4764_Y = new g_2336_b("realms", "textures/gui/realms/restore_icon.png");
    private static final x_282_a G_564_y = new F_2904_S("mco.backup.button.restore");
    private static final x_282_a P_1922_E = new F_2904_S("mco.backup.changes.tooltip");
    private static final x_282_a u_1723_Y = new F_2904_S("mco.configure.world.backup");
    private static final x_282_a v_4262_N = new F_2904_S("mco.backup.nobackups");
    private static int w_1484_f = -1;
    private final W_3464_O t_148_a;
    private List<D_60_a> s_956_w = Collections.emptyList();
    @Nullable
    private x_282_a u_2550_I;
    private n_1700_B M_588_G;
    private int P_4830_p = -1;
    private final int h_1847_R;
    private Button Q_4569_t;
    private Button M_182_A;
    private Button t_1786_h;
    private Boolean multiplayerClientSuggestionProvider = false;
    private final q_1982_R w_1457_N;
    private RealmsLabel Y_601_j;

    public c_132_F(W_3464_O p_i51777_1_, q_1982_R p_i51777_2_, int p_i51777_3_) {
        this.t_148_a = p_i51777_1_;
        this.w_1457_N = p_i51777_2_;
        this.h_1847_R = p_i51777_3_;
    }

    @Override
    public void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.M_588_G = new n_1700_B();
        if (w_1484_f != -1) {
            this.M_588_G.setScrollAmount(w_1484_f);
        }
        new Thread("Realms-fetch-backups"){

            @Override
            public void run() {
                p_178_J realmsclient = p_178_J.n_1700_B();
                try {
                    List<D_60_a> list = realmsclient.G_564_y((long)c_132_F.this.w_1457_N.n_1700_B).n_1700_B;
                    c_132_F.this.minecraft.execute(() -> {
                        c_132_F.this.s_956_w = list;
                        c_132_F.this.multiplayerClientSuggestionProvider = c_132_F.this.s_956_w.isEmpty();
                        c_132_F.this.M_588_G.n_1700_B();
                        for (D_60_a backup : c_132_F.this.s_956_w) {
                            c_132_F.this.M_588_G.n_1700_B(backup);
                        }
                        c_132_F.this.n_1700_B();
                    });
                }
                catch (u_744_e realmsserviceexception) {
                    n_1700_B.error("Couldn't request backups", (Throwable)realmsserviceexception);
                }
            }
        }.start();
        this.Q_4569_t = this.addButton(new Button(this.width - 135, c_132_F.G_564_y(1), 120, 20, new F_2904_S("mco.backup.button.download"), p_237758_1_ -> this.u_1723_Y()));
        this.M_182_A = this.addButton(new Button(this.width - 135, c_132_F.G_564_y(3), 120, 20, new F_2904_S("mco.backup.button.restore"), p_237754_1_ -> this.n_1700_B(this.P_4830_p)));
        this.t_1786_h = this.addButton(new Button(this.width - 135, c_132_F.G_564_y(5), 120, 20, new F_2904_S("mco.backup.changes.tooltip"), p_237752_1_ -> {
            this.minecraft.n_1700_B(new M_2677_i(this, this.s_956_w.get(this.P_4830_p)));
            this.P_4830_p = -1;
        }));
        this.addButton(new Button(this.width - 100, this.height - 35, 85, 20, CommonComponents.w_1484_f, p_237748_1_ -> this.minecraft.n_1700_B(this.t_148_a)));
        this.addListener(this.M_588_G);
        this.Y_601_j = this.addListener(new RealmsLabel(new F_2904_S("mco.configure.world.backup"), this.width / 2, 12, 0xFFFFFF));
        this.J_1907_R(this.M_588_G);
        this.J_1907_R();
        this.P_1922_E();
    }

    private void n_1700_B() {
        if (this.s_956_w.size() > 1) {
            for (int i = 0; i < this.s_956_w.size() - 1; ++i) {
                D_60_a backup = this.s_956_w.get(i);
                D_60_a backup1 = this.s_956_w.get(i + 1);
                if (backup.G_564_y.isEmpty() || backup1.G_564_y.isEmpty()) continue;
                for (String s : backup.G_564_y.keySet()) {
                    if (!s.contains("Uploaded") && backup1.G_564_y.containsKey(s)) {
                        if (backup.G_564_y.get(s).equals(backup1.G_564_y.get(s))) continue;
                        this.n_1700_B(backup, s);
                        continue;
                    }
                    this.n_1700_B(backup, s);
                }
            }
        }
    }

    private void n_1700_B(D_60_a p_224103_1_, String p_224103_2_) {
        if (p_224103_2_.contains("Uploaded")) {
            String s = DateFormat.getDateTimeInstance(3, 3).format(p_224103_1_.J_1907_R);
            p_224103_1_.P_1922_E.put(p_224103_2_, s);
            p_224103_1_.n_1700_B(true);
        } else {
            p_224103_1_.P_1922_E.put(p_224103_2_, p_224103_1_.G_564_y.get(p_224103_2_));
        }
    }

    private void J_1907_R() {
        this.M_182_A.visible = this.G_564_y();
        this.t_1786_h.visible = this.R_4764_Y();
    }

    private boolean R_4764_Y() {
        if (this.P_4830_p == -1) {
            return false;
        }
        return !this.s_956_w.get((int)this.P_4830_p).P_1922_E.isEmpty();
    }

    private boolean G_564_y() {
        if (this.P_4830_p == -1) {
            return false;
        }
        return !this.w_1457_N.s_956_w;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.n_1700_B(this.t_148_a);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void n_1700_B(int p_224104_1_) {
        if (p_224104_1_ >= 0 && p_224104_1_ < this.s_956_w.size() && !this.w_1457_N.s_956_w) {
            this.P_4830_p = p_224104_1_;
            Date date = this.s_956_w.get((int)p_224104_1_).J_1907_R;
            String s = DateFormat.getDateTimeInstance(3, 3).format(date);
            String s1 = H_1883_T.n_1700_B(date);
            F_2904_S itextcomponent = new F_2904_S("mco.configure.world.restore.question.line1", s, s1);
            F_2904_S itextcomponent1 = new F_2904_S("mco.configure.world.restore.question.line2");
            this.minecraft.n_1700_B(new RealmsLongConfirmationScreen(p_237759_1_ -> {
                if (p_237759_1_) {
                    this.w_1484_f();
                } else {
                    this.P_4830_p = -1;
                    this.minecraft.n_1700_B(this);
                }
            }, RealmsLongConfirmationScreen.n_1700_B.n_1700_B, itextcomponent, itextcomponent1, true));
        }
    }

    private void u_1723_Y() {
        F_2904_S itextcomponent = new F_2904_S("mco.configure.world.restore.download.question.line1");
        F_2904_S itextcomponent1 = new F_2904_S("mco.configure.world.restore.download.question.line2");
        this.minecraft.n_1700_B(new RealmsLongConfirmationScreen(p_237755_1_ -> {
            if (p_237755_1_) {
                this.v_4262_N();
            } else {
                this.minecraft.n_1700_B(this);
            }
        }, RealmsLongConfirmationScreen.n_1700_B.J_1907_R, itextcomponent, itextcomponent1, true));
    }

    private void v_4262_N() {
        this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(this.t_148_a.J_1907_R(), new DownloadTask(this.w_1457_N.n_1700_B, this.h_1847_R, this.w_1457_N.R_4764_Y + " (" + this.w_1457_N.t_148_a.get(this.w_1457_N.h_1847_R).n_1700_B(this.w_1457_N.h_1847_R) + ")", this)));
    }

    private void w_1484_f() {
        D_60_a backup = this.s_956_w.get(this.P_4830_p);
        this.P_4830_p = -1;
        this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(this.t_148_a.J_1907_R(), new o_4801_T(backup, this.w_1457_N.n_1700_B, this.t_148_a)));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.u_2550_I = null;
        this.renderBackground(matrixStack);
        this.M_588_G.render(matrixStack, mouseX, mouseY, partialTicks);
        this.Y_601_j.n_1700_B(this, matrixStack);
        this.font.J_1907_R(matrixStack, u_1723_Y, (float)((this.width - 150) / 2 - 90), 20.0f, 0xA0A0A0);
        if (this.multiplayerClientSuggestionProvider.booleanValue()) {
            this.font.J_1907_R(matrixStack, v_4262_N, 20.0f, (float)(this.height / 2 - 10), 0xFFFFFF);
        }
        this.Q_4569_t.active = this.multiplayerClientSuggestionProvider == false;
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.u_2550_I != null) {
            this.n_1700_B(matrixStack, this.u_2550_I, mouseX, mouseY);
        }
    }

    protected void n_1700_B(g_221_o p_237744_1_, @Nullable x_282_a p_237744_2_, int p_237744_3_, int p_237744_4_) {
        if (p_237744_2_ != null) {
            int i = p_237744_3_ + 12;
            int j = p_237744_4_ - 12;
            int k = this.font.n_1700_B((FormattedText)p_237744_2_);
            c_132_F.fillGradient(p_237744_1_, i - 3, j - 3, i + k + 3, j + 8 + 3, -1073741824, -1073741824);
            this.font.n_1700_B(p_237744_1_, p_237744_2_, (float)i, (float)j, 0xFFFFFF);
        }
    }

    class n_1700_B
    extends z_3470_q<J_1907_R> {
        public n_1700_B() {
            super(c_132_F.this.width - 150, c_132_F.this.height, 32, c_132_F.this.height - 15, 36);
        }

        public void n_1700_B(D_60_a p_223867_1_) {
            c_132_F c_132_F2 = c_132_F.this;
            Objects.requireNonNull(c_132_F2);
            this.n_1700_B(c_132_F2.new J_1907_R(p_223867_1_));
        }

        @Override
        public int getRowWidth() {
            return (int)((double)this.width * 0.93);
        }

        @Override
        public boolean isFocused() {
            return c_132_F.this.getListener() == this;
        }

        @Override
        public int getMaxPosition() {
            return this.getItemCount() * 36;
        }

        @Override
        public void renderBackground(g_221_o p_230433_1_) {
            c_132_F.this.renderBackground(p_230433_1_);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if (button != 0) {
                return false;
            }
            if (mouseX < (double)this.getScrollbarPosition() && mouseY >= (double)this.y0 && mouseY <= (double)this.y1) {
                int i = this.width / 2 - 92;
                int j = this.width;
                int k = (int)Math.floor(mouseY - (double)this.y0) - this.headerHeight + (int)this.getScrollAmount();
                int l = k / this.itemHeight;
                if (mouseX >= (double)i && mouseX <= (double)j && l >= 0 && k >= 0 && l < this.getItemCount()) {
                    this.n_1700_B(l);
                    this.n_1700_B(k, l, mouseX, mouseY, this.width);
                }
                return true;
            }
            return false;
        }

        @Override
        public int getScrollbarPosition() {
            return this.width - 5;
        }

        @Override
        public void n_1700_B(int p_231401_1_, int p_231401_2_, double p_231401_3_, double p_231401_5_, int p_231401_7_) {
            int i = this.width - 35;
            int j = p_231401_2_ * this.itemHeight + 36 - (int)this.getScrollAmount();
            int k = i + 10;
            int l = j - 3;
            if (p_231401_3_ >= (double)i && p_231401_3_ <= (double)(i + 9) && p_231401_5_ >= (double)j && p_231401_5_ <= (double)(j + 9)) {
                if (!c_132_F.this.s_956_w.get((int)p_231401_2_).P_1922_E.isEmpty()) {
                    c_132_F.this.P_4830_p = -1;
                    w_1484_f = (int)this.getScrollAmount();
                    this.minecraft.n_1700_B(new M_2677_i(c_132_F.this, c_132_F.this.s_956_w.get(p_231401_2_)));
                }
            } else if (p_231401_3_ >= (double)k && p_231401_3_ < (double)(k + 13) && p_231401_5_ >= (double)l && p_231401_5_ < (double)(l + 15)) {
                w_1484_f = (int)this.getScrollAmount();
                c_132_F.this.n_1700_B(p_231401_2_);
            }
        }

        @Override
        public void n_1700_B(int p_231400_1_) {
            this.G_564_y(p_231400_1_);
            if (p_231400_1_ != -1) {
                NarrationHelper.n_1700_B(K_1289_S.n_1700_B("narrator.select", c_132_F.this.s_956_w.get((int)p_231400_1_).J_1907_R.toString()));
            }
            this.J_1907_R(p_231400_1_);
        }

        public void J_1907_R(int p_223866_1_) {
            c_132_F.this.P_4830_p = p_223866_1_;
            c_132_F.this.J_1907_R();
        }

        public void n_1700_B(@Nullable J_1907_R entry) {
            super.setSelected(entry);
            c_132_F.this.P_4830_p = this.getEventListeners().indexOf(entry);
            c_132_F.this.J_1907_R();
        }

        @Override
        public /* synthetic */ void setSelected(@Nullable o_2488_o.n_1700_B n_1700_B2) {
            this.n_1700_B((J_1907_R)n_1700_B2);
        }
    }

    class J_1907_R
    extends ObjectSelectionList.n_1700_B<J_1907_R> {
        private final D_60_a J_1907_R;

        public J_1907_R(D_60_a p_i51657_2_) {
            this.J_1907_R = p_i51657_2_;
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            this.n_1700_B(p_230432_1_, this.J_1907_R, p_230432_4_ - 40, p_230432_3_, p_230432_7_, p_230432_8_);
        }

        private void n_1700_B(g_221_o p_237767_1_, D_60_a p_237767_2_, int p_237767_3_, int p_237767_4_, int p_237767_5_, int p_237767_6_) {
            int i = p_237767_2_.n_1700_B() ? -8388737 : 0xFFFFFF;
            c_132_F.this.font.J_1907_R(p_237767_1_, "Backup (" + H_1883_T.n_1700_B(p_237767_2_.J_1907_R) + ")", (float)(p_237767_3_ + 40), (float)(p_237767_4_ + 1), i);
            c_132_F.this.font.J_1907_R(p_237767_1_, this.n_1700_B(p_237767_2_.J_1907_R), (float)(p_237767_3_ + 40), (float)(p_237767_4_ + 12), 0x4C4C4C);
            int j = c_132_F.this.width - 175;
            int k = -3;
            int l = j - 10;
            boolean i1 = false;
            if (!c_132_F.this.w_1457_N.s_956_w) {
                this.n_1700_B(p_237767_1_, j, p_237767_4_ + -3, p_237767_5_, p_237767_6_);
            }
            if (!p_237767_2_.P_1922_E.isEmpty()) {
                this.J_1907_R(p_237767_1_, l, p_237767_4_ + 0, p_237767_5_, p_237767_6_);
            }
        }

        private String n_1700_B(Date p_223738_1_) {
            return DateFormat.getDateTimeInstance(3, 3).format(p_223738_1_);
        }

        private void n_1700_B(g_221_o p_237766_1_, int p_237766_2_, int p_237766_3_, int p_237766_4_, int p_237766_5_) {
            boolean flag = p_237766_4_ >= p_237766_2_ && p_237766_4_ <= p_237766_2_ + 12 && p_237766_5_ >= p_237766_3_ && p_237766_5_ <= p_237766_3_ + 14 && p_237766_5_ < c_132_F.this.height - 15 && p_237766_5_ > 32;
            c_132_F.this.minecraft.G_624_v().n_1700_B(R_4764_Y);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            c_4037_x.v_4276_D();
            c_4037_x.J_1907_R(0.5f, 0.5f, 0.5f);
            float f = flag ? 28.0f : 0.0f;
            C_2701_A.blit(p_237766_1_, p_237766_2_ * 2, p_237766_3_ * 2, 0.0f, f, 23, 28, 23, 56);
            c_4037_x.d_2461_k();
            if (flag) {
                c_132_F.this.u_2550_I = G_564_y;
            }
        }

        private void J_1907_R(g_221_o p_237768_1_, int p_237768_2_, int p_237768_3_, int p_237768_4_, int p_237768_5_) {
            boolean flag = p_237768_4_ >= p_237768_2_ && p_237768_4_ <= p_237768_2_ + 8 && p_237768_5_ >= p_237768_3_ && p_237768_5_ <= p_237768_3_ + 8 && p_237768_5_ < c_132_F.this.height - 15 && p_237768_5_ > 32;
            c_132_F.this.minecraft.G_624_v().n_1700_B(J_1907_R);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            c_4037_x.v_4276_D();
            c_4037_x.J_1907_R(0.5f, 0.5f, 0.5f);
            float f = flag ? 15.0f : 0.0f;
            C_2701_A.blit(p_237768_1_, p_237768_2_ * 2, p_237768_3_ * 2, 0.0f, f, 15, 15, 15, 30);
            c_4037_x.d_2461_k();
            if (flag) {
                c_132_F.this.u_2550_I = P_1922_E;
            }
        }
    }
}


