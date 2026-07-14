/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.H_1883_T;
import lightning.product.K_1289_S;
import lightning.product.ObjectSelectionList;
import lightning.product.Button;
import lightning.product.RowButton;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.RealmsScreen;
import lightning.product.h_4320_q;
import lightning.product.k_2603_m;
import lightning.product.o_2488_o;
import lightning.product.p_178_J;
import lightning.product.CommonComponents;
import lightning.product.r_715_M;
import lightning.product.NarrationHelper;
import lightning.product.u_744_e;
import lightning.product.RealmsLabel;
import lightning.product.x_282_a;
import lightning.product.y_2772_m;
import lightning.product.z_3470_q;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class O_2151_c
extends RealmsScreen {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final g_2336_b J_1907_R = new g_2336_b("realms", "textures/gui/realms/accept_icon.png");
    private static final g_2336_b R_4764_Y = new g_2336_b("realms", "textures/gui/realms/reject_icon.png");
    private static final x_282_a G_564_y = new F_2904_S("mco.invites.nopending");
    private static final x_282_a P_1922_E = new F_2904_S("mco.invites.button.accept");
    private static final x_282_a u_1723_Y = new F_2904_S("mco.invites.button.reject");
    private final k_2603_m v_4262_N;
    @Nullable
    private x_282_a w_1484_f;
    private boolean t_148_a;
    private J_1907_R s_956_w;
    private RealmsLabel u_2550_I;
    private int M_588_G = -1;
    private Button P_4830_p;
    private Button h_1847_R;

    public O_2151_c(k_2603_m p_i232211_1_) {
        this.v_4262_N = p_i232211_1_;
    }

    @Override
    public void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.s_956_w = new J_1907_R();
        new Thread("Realms-pending-invitations-fetcher"){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void run() {
                p_178_J realmsclient = p_178_J.n_1700_B();
                try {
                    List<h_4320_q> list = realmsclient.u_2550_I().n_1700_B;
                    List list1 = list.stream().map(p_225146_1_ -> {
                        O_2151_c o_2151_c = O_2151_c.this;
                        Objects.requireNonNull(o_2151_c);
                        return o_2151_c.new n_1700_B((h_4320_q)p_225146_1_);
                    }).collect(Collectors.toList());
                    O_2151_c.this.minecraft.execute(() -> O_2151_c.this.s_956_w.replaceEntries(list1));
                }
                catch (u_744_e realmsserviceexception) {
                    n_1700_B.error("Couldn't list invites");
                }
                finally {
                    O_2151_c.this.t_148_a = true;
                }
            }
        }.start();
        this.addListener(this.s_956_w);
        this.P_4830_p = this.addButton(new Button(this.width / 2 - 174, this.height - 32, 100, 20, new F_2904_S("mco.invites.button.accept"), p_237878_1_ -> {
            this.R_4764_Y(this.M_588_G);
            this.M_588_G = -1;
            this.n_1700_B();
        }));
        this.addButton(new Button(this.width / 2 - 50, this.height - 32, 100, 20, CommonComponents.R_4764_Y, p_237875_1_ -> this.minecraft.n_1700_B(new r_715_M(this.v_4262_N))));
        this.h_1847_R = this.addButton(new Button(this.width / 2 + 74, this.height - 32, 100, 20, new F_2904_S("mco.invites.button.reject"), p_237871_1_ -> {
            this.J_1907_R(this.M_588_G);
            this.M_588_G = -1;
            this.n_1700_B();
        }));
        this.u_2550_I = new RealmsLabel(new F_2904_S("mco.invites.title"), this.width / 2, 12, 0xFFFFFF);
        this.addListener(this.u_2550_I);
        this.P_1922_E();
        this.n_1700_B();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.n_1700_B(new r_715_M(this.v_4262_N));
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void n_1700_B(int p_224318_1_) {
        this.s_956_w.J_1907_R(p_224318_1_);
    }

    private void J_1907_R(final int p_224321_1_) {
        if (p_224321_1_ < this.s_956_w.getItemCount()) {
            new Thread("Realms-reject-invitation"){

                @Override
                public void run() {
                    try {
                        p_178_J realmsclient = p_178_J.n_1700_B();
                        realmsclient.J_1907_R(((n_1700_B)O_2151_c.this.s_956_w.getEventListeners().get((int)p_224321_1_)).J_1907_R.n_1700_B);
                        O_2151_c.this.minecraft.execute(() -> O_2151_c.this.n_1700_B(p_224321_1_));
                    }
                    catch (u_744_e realmsserviceexception) {
                        n_1700_B.error("Couldn't reject invite");
                    }
                }
            }.start();
        }
    }

    private void R_4764_Y(final int p_224329_1_) {
        if (p_224329_1_ < this.s_956_w.getItemCount()) {
            new Thread("Realms-accept-invitation"){

                @Override
                public void run() {
                    try {
                        p_178_J realmsclient = p_178_J.n_1700_B();
                        realmsclient.n_1700_B(((n_1700_B)O_2151_c.this.s_956_w.getEventListeners().get((int)p_224329_1_)).J_1907_R.n_1700_B);
                        O_2151_c.this.minecraft.execute(() -> O_2151_c.this.n_1700_B(p_224329_1_));
                    }
                    catch (u_744_e realmsserviceexception) {
                        n_1700_B.error("Couldn't accept invite");
                    }
                }
            }.start();
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.w_1484_f = null;
        this.renderBackground(matrixStack);
        this.s_956_w.render(matrixStack, mouseX, mouseY, partialTicks);
        this.u_2550_I.n_1700_B(this, matrixStack);
        if (this.w_1484_f != null) {
            this.n_1700_B(matrixStack, this.w_1484_f, mouseX, mouseY);
        }
        if (this.s_956_w.getItemCount() == 0 && this.t_148_a) {
            O_2151_c.drawCenteredString(matrixStack, this.font, G_564_y, this.width / 2, this.height / 2 - 20, 0xFFFFFF);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    protected void n_1700_B(g_221_o p_237866_1_, @Nullable x_282_a p_237866_2_, int p_237866_3_, int p_237866_4_) {
        if (p_237866_2_ != null) {
            int i = p_237866_3_ + 12;
            int j = p_237866_4_ - 12;
            int k = this.font.n_1700_B((FormattedText)p_237866_2_);
            O_2151_c.fillGradient(p_237866_1_, i - 3, j - 3, i + k + 3, j + 8 + 3, -1073741824, -1073741824);
            this.font.n_1700_B(p_237866_1_, p_237866_2_, (float)i, (float)j, 0xFFFFFF);
        }
    }

    private void n_1700_B() {
        this.P_4830_p.visible = this.P_1922_E(this.M_588_G);
        this.h_1847_R.visible = this.P_1922_E(this.M_588_G);
    }

    private boolean P_1922_E(int p_224316_1_) {
        return p_224316_1_ != -1;
    }

    class J_1907_R
    extends z_3470_q<n_1700_B> {
        public J_1907_R() {
            super(O_2151_c.this.width, O_2151_c.this.height, 32, O_2151_c.this.height - 40, 36);
        }

        public void J_1907_R(int p_223872_1_) {
            this.remove(p_223872_1_);
        }

        @Override
        public int getMaxPosition() {
            return this.getItemCount() * 36;
        }

        @Override
        public int getRowWidth() {
            return 260;
        }

        @Override
        public boolean isFocused() {
            return O_2151_c.this.getListener() == this;
        }

        @Override
        public void renderBackground(g_221_o p_230433_1_) {
            O_2151_c.this.renderBackground(p_230433_1_);
        }

        @Override
        public void n_1700_B(int p_231400_1_) {
            this.G_564_y(p_231400_1_);
            if (p_231400_1_ != -1) {
                List list = O_2151_c.this.s_956_w.getEventListeners();
                h_4320_q pendinginvite = ((n_1700_B)list.get((int)p_231400_1_)).J_1907_R;
                String s = K_1289_S.n_1700_B("narrator.select.list.position", p_231400_1_ + 1, list.size());
                String s1 = NarrationHelper.J_1907_R(Arrays.asList(pendinginvite.J_1907_R, pendinginvite.R_4764_Y, H_1883_T.n_1700_B(pendinginvite.P_1922_E), s));
                NarrationHelper.n_1700_B(K_1289_S.n_1700_B("narrator.select", s1));
            }
            this.R_4764_Y(p_231400_1_);
        }

        public void R_4764_Y(int p_223873_1_) {
            O_2151_c.this.M_588_G = p_223873_1_;
            O_2151_c.this.n_1700_B();
        }

        public void n_1700_B(@Nullable n_1700_B entry) {
            super.setSelected(entry);
            O_2151_c.this.M_588_G = this.getEventListeners().indexOf(entry);
            O_2151_c.this.n_1700_B();
        }

        @Override
        public /* synthetic */ void setSelected(@Nullable o_2488_o.n_1700_B n_1700_B2) {
            this.n_1700_B((n_1700_B)n_1700_B2);
        }
    }

    class lightning.product.O_2151_c$n_1700_B
    extends ObjectSelectionList.n_1700_B<lightning.product.O_2151_c$n_1700_B> {
        private final h_4320_q J_1907_R;
        private final List<RowButton> R_4764_Y;

        lightning.product.O_2151_c$n_1700_B(h_4320_q p_i51623_2_) {
            this.J_1907_R = p_i51623_2_;
            this.R_4764_Y = Arrays.asList(new n_1700_B(), new J_1907_R());
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            this.n_1700_B(p_230432_1_, this.J_1907_R, p_230432_4_, p_230432_3_, p_230432_7_, p_230432_8_);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            RowButton.n_1700_B(O_2151_c.this.s_956_w, this, this.R_4764_Y, button, mouseX, mouseY);
            return true;
        }

        private void n_1700_B(g_221_o p_237893_1_, h_4320_q p_237893_2_, int p_237893_3_, int p_237893_4_, int p_237893_5_, int p_237893_6_) {
            O_2151_c.this.font.J_1907_R(p_237893_1_, p_237893_2_.J_1907_R, (float)(p_237893_3_ + 38), (float)(p_237893_4_ + 1), 0xFFFFFF);
            O_2151_c.this.font.J_1907_R(p_237893_1_, p_237893_2_.R_4764_Y, (float)(p_237893_3_ + 38), (float)(p_237893_4_ + 12), 0x6C6C6C);
            O_2151_c.this.font.J_1907_R(p_237893_1_, H_1883_T.n_1700_B(p_237893_2_.P_1922_E), (float)(p_237893_3_ + 38), (float)(p_237893_4_ + 24), 0x6C6C6C);
            RowButton.n_1700_B(p_237893_1_, this.R_4764_Y, O_2151_c.this.s_956_w, p_237893_3_, p_237893_4_, p_237893_5_, p_237893_6_);
            y_2772_m.n_1700_B(p_237893_2_.G_564_y, () -> {
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                C_2701_A.blit(p_237893_1_, p_237893_3_, p_237893_4_, 32, 32, 8.0f, 8.0f, 8, 8, 64, 64);
                C_2701_A.blit(p_237893_1_, p_237893_3_, p_237893_4_, 32, 32, 40.0f, 8.0f, 8, 8, 64, 64);
            });
        }

        class n_1700_B
        extends RowButton {
            n_1700_B() {
                super(15, 15, 215, 5);
            }

            @Override
            protected void n_1700_B(g_221_o p_230435_1_, int p_230435_2_, int p_230435_3_, boolean p_230435_4_) {
                O_2151_c.this.minecraft.G_624_v().n_1700_B(O_2151_c.J_1907_R);
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                float f = p_230435_4_ ? 19.0f : 0.0f;
                C_2701_A.blit(p_230435_1_, p_230435_2_, p_230435_3_, f, 0.0f, 18, 18, 37, 18);
                if (p_230435_4_) {
                    O_2151_c.this.w_1484_f = P_1922_E;
                }
            }

            @Override
            public void n_1700_B(int p_225121_1_) {
                O_2151_c.this.R_4764_Y(p_225121_1_);
            }
        }

        class J_1907_R
        extends RowButton {
            J_1907_R() {
                super(15, 15, 235, 5);
            }

            @Override
            protected void n_1700_B(g_221_o p_230435_1_, int p_230435_2_, int p_230435_3_, boolean p_230435_4_) {
                O_2151_c.this.minecraft.G_624_v().n_1700_B(O_2151_c.R_4764_Y);
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                float f = p_230435_4_ ? 19.0f : 0.0f;
                C_2701_A.blit(p_230435_1_, p_230435_2_, p_230435_3_, f, 0.0f, 18, 18, 37, 18);
                if (p_230435_4_) {
                    O_2151_c.this.w_1484_f = u_1723_Y;
                }
            }

            @Override
            public void n_1700_B(int p_225121_1_) {
                O_2151_c.this.J_1907_R(p_225121_1_);
            }
        }
    }
}


