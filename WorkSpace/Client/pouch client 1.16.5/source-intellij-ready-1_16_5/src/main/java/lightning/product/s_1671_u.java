/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.util.Objects;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.J_4256_G;
import lightning.product.K_1289_S;
import lightning.product.ObjectSelectionList;
import lightning.product.PlayerInfo;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.RealmsConfirmScreen;
import lightning.product.W_3464_O;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.RealmsScreen;
import lightning.product.Ops;
import lightning.product.l_3747_P;
import lightning.product.o_2488_o;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.NarrationHelper;
import lightning.product.u_744_e;
import lightning.product.RealmsLabel;
import lightning.product.x_282_a;
import lightning.product.y_2772_m;
import lightning.product.z_3470_q;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class s_1671_u
extends RealmsScreen {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final g_2336_b J_1907_R = new g_2336_b("realms", "textures/gui/realms/op_icon.png");
    private static final g_2336_b R_4764_Y = new g_2336_b("realms", "textures/gui/realms/user_icon.png");
    private static final g_2336_b G_564_y = new g_2336_b("realms", "textures/gui/realms/cross_player_icon.png");
    private static final g_2336_b P_1922_E = new g_2336_b("minecraft", "textures/gui/options_background.png");
    private static final x_282_a u_1723_Y = new F_2904_S("mco.configure.world.invites.normal.tooltip");
    private static final x_282_a v_4262_N = new F_2904_S("mco.configure.world.invites.ops.tooltip");
    private static final x_282_a w_1484_f = new F_2904_S("mco.configure.world.invites.remove.tooltip");
    private static final x_282_a t_148_a = new F_2904_S("mco.configure.world.invited");
    private x_282_a s_956_w;
    private final W_3464_O u_2550_I;
    private final q_1982_R M_588_G;
    private R_4764_Y P_4830_p;
    private int h_1847_R;
    private int Q_4569_t;
    private int M_182_A;
    private Button t_1786_h;
    private Button multiplayerClientSuggestionProvider;
    private int w_1457_N = -1;
    private String Y_601_j;
    private int Y_259_p = -1;
    private boolean Q_2552_b;
    private RealmsLabel C_2741_M;
    private n_1700_B k_2293_S = lightning.product.s_1671_u$n_1700_B.R_4764_Y;

    public s_1671_u(W_3464_O p_i51760_1_, q_1982_R p_i51760_2_) {
        this.u_2550_I = p_i51760_1_;
        this.M_588_G = p_i51760_2_;
    }

    @Override
    public void init() {
        this.h_1847_R = this.width / 2 - 160;
        this.Q_4569_t = 150;
        this.M_182_A = this.width / 2 + 12;
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.P_4830_p = new R_4764_Y();
        this.P_4830_p.setLeftPos(this.h_1847_R);
        this.addListener(this.P_4830_p);
        for (PlayerInfo playerinfo : this.M_588_G.w_1484_f) {
            this.P_4830_p.n_1700_B(playerinfo);
        }
        this.addButton(new Button(this.M_182_A, s_1671_u.G_564_y(1), this.Q_4569_t + 10, 20, new F_2904_S("mco.configure.world.buttons.invite"), p_237924_1_ -> this.minecraft.n_1700_B(new J_4256_G(this.u_2550_I, this, this.M_588_G))));
        this.t_1786_h = this.addButton(new Button(this.M_182_A, s_1671_u.G_564_y(7), this.Q_4569_t + 10, 20, new F_2904_S("mco.configure.world.invites.remove.tooltip"), p_237918_1_ -> this.w_1484_f(this.Y_259_p)));
        this.multiplayerClientSuggestionProvider = this.addButton(new Button(this.M_182_A, s_1671_u.G_564_y(9), this.Q_4569_t + 10, 20, new F_2904_S("mco.configure.world.invites.ops.tooltip"), p_237912_1_ -> {
            if (this.M_588_G.w_1484_f.get(this.Y_259_p).R_4764_Y()) {
                this.v_4262_N(this.Y_259_p);
            } else {
                this.u_1723_Y(this.Y_259_p);
            }
        }));
        this.addButton(new Button(this.M_182_A + this.Q_4569_t / 2 + 2, s_1671_u.G_564_y(12), this.Q_4569_t / 2 + 10 - 2, 20, CommonComponents.w_1484_f, p_237907_1_ -> this.J_1907_R()));
        this.C_2741_M = this.addListener(new RealmsLabel(new F_2904_S("mco.configure.world.players.title"), this.width / 2, 17, 0xFFFFFF));
        this.P_1922_E();
        this.n_1700_B();
    }

    private void n_1700_B() {
        this.t_1786_h.visible = this.P_1922_E(this.Y_259_p);
        this.multiplayerClientSuggestionProvider.visible = this.P_1922_E(this.Y_259_p);
    }

    private boolean P_1922_E(int p_224296_1_) {
        return p_224296_1_ != -1;
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.J_1907_R();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void J_1907_R() {
        if (this.Q_2552_b) {
            this.minecraft.n_1700_B(this.u_2550_I.J_1907_R());
        } else {
            this.minecraft.n_1700_B(this.u_2550_I);
        }
    }

    private void u_1723_Y(int p_224289_1_) {
        this.n_1700_B();
        p_178_J realmsclient = p_178_J.n_1700_B();
        String s = this.M_588_G.w_1484_f.get(p_224289_1_).J_1907_R();
        try {
            this.n_1700_B(realmsclient.P_1922_E(this.M_588_G.n_1700_B, s));
        }
        catch (u_744_e realmsserviceexception) {
            n_1700_B.error("Couldn't op the user");
        }
    }

    private void v_4262_N(int p_224279_1_) {
        this.n_1700_B();
        p_178_J realmsclient = p_178_J.n_1700_B();
        String s = this.M_588_G.w_1484_f.get(p_224279_1_).J_1907_R();
        try {
            this.n_1700_B(realmsclient.u_1723_Y(this.M_588_G.n_1700_B, s));
        }
        catch (u_744_e realmsserviceexception) {
            n_1700_B.error("Couldn't deop the user");
        }
    }

    private void n_1700_B(Ops p_224283_1_) {
        for (PlayerInfo playerinfo : this.M_588_G.w_1484_f) {
            playerinfo.n_1700_B(p_224283_1_.n_1700_B.contains(playerinfo.n_1700_B()));
        }
    }

    private void w_1484_f(int p_224274_1_) {
        this.n_1700_B();
        if (p_224274_1_ >= 0 && p_224274_1_ < this.M_588_G.w_1484_f.size()) {
            PlayerInfo playerinfo = this.M_588_G.w_1484_f.get(p_224274_1_);
            this.Y_601_j = playerinfo.J_1907_R();
            this.w_1457_N = p_224274_1_;
            RealmsConfirmScreen realmsconfirmscreen = new RealmsConfirmScreen(p_237919_1_ -> {
                if (p_237919_1_) {
                    p_178_J realmsclient = p_178_J.n_1700_B();
                    try {
                        realmsclient.n_1700_B(this.M_588_G.n_1700_B, this.Y_601_j);
                    }
                    catch (u_744_e realmsserviceexception) {
                        n_1700_B.error("Couldn't uninvite user");
                    }
                    this.t_148_a(this.w_1457_N);
                    this.Y_259_p = -1;
                    this.n_1700_B();
                }
                this.Q_2552_b = true;
                this.minecraft.n_1700_B(this);
            }, new U_2871_b("Question"), new F_2904_S("mco.configure.world.uninvite.question").n_1700_B(" '").n_1700_B(playerinfo.n_1700_B()).n_1700_B("' ?"));
            this.minecraft.n_1700_B(realmsconfirmscreen);
        }
    }

    private void t_148_a(int p_224292_1_) {
        this.M_588_G.w_1484_f.remove(p_224292_1_);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.s_956_w = null;
        this.k_2293_S = lightning.product.s_1671_u$n_1700_B.R_4764_Y;
        this.renderBackground(matrixStack);
        if (this.P_4830_p != null) {
            this.P_4830_p.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        int i = s_1671_u.G_564_y(12) + 20;
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        this.minecraft.G_624_v().n_1700_B(P_1922_E);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        float f = 32.0f;
        bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
        bufferbuilder.pos(0.0, this.height, 0.0).tex(0.0f, (float)(this.height - i) / 32.0f + 0.0f).color(64, 64, 64, 255).endVertex();
        bufferbuilder.pos(this.width, this.height, 0.0).tex((float)this.width / 32.0f, (float)(this.height - i) / 32.0f + 0.0f).color(64, 64, 64, 255).endVertex();
        bufferbuilder.pos(this.width, i, 0.0).tex((float)this.width / 32.0f, 0.0f).color(64, 64, 64, 255).endVertex();
        bufferbuilder.pos(0.0, i, 0.0).tex(0.0f, 0.0f).color(64, 64, 64, 255).endVertex();
        tessellator.J_1907_R();
        this.C_2741_M.n_1700_B(this, matrixStack);
        if (this.M_588_G != null && this.M_588_G.w_1484_f != null) {
            this.font.J_1907_R(matrixStack, new U_2871_b("").n_1700_B(t_148_a).n_1700_B(" (").n_1700_B(Integer.toString(this.M_588_G.w_1484_f.size())).n_1700_B(")"), (float)this.h_1847_R, (float)s_1671_u.G_564_y(0), 0xA0A0A0);
        } else {
            this.font.J_1907_R(matrixStack, t_148_a, (float)this.h_1847_R, (float)s_1671_u.G_564_y(0), 0xA0A0A0);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.M_588_G != null) {
            this.n_1700_B(matrixStack, this.s_956_w, mouseX, mouseY);
        }
    }

    protected void n_1700_B(g_221_o p_237903_1_, @Nullable x_282_a p_237903_2_, int p_237903_3_, int p_237903_4_) {
        if (p_237903_2_ != null) {
            int i = p_237903_3_ + 12;
            int j = p_237903_4_ - 12;
            int k = this.font.n_1700_B((FormattedText)p_237903_2_);
            s_1671_u.fillGradient(p_237903_1_, i - 3, j - 3, i + k + 3, j + 8 + 3, -1073741824, -1073741824);
            this.font.n_1700_B(p_237903_1_, p_237903_2_, (float)i, (float)j, 0xFFFFFF);
        }
    }

    private void n_1700_B(g_221_o p_237914_1_, int p_237914_2_, int p_237914_3_, int p_237914_4_, int p_237914_5_) {
        boolean flag = p_237914_4_ >= p_237914_2_ && p_237914_4_ <= p_237914_2_ + 9 && p_237914_5_ >= p_237914_3_ && p_237914_5_ <= p_237914_3_ + 9 && p_237914_5_ < s_1671_u.G_564_y(12) + 20 && p_237914_5_ > s_1671_u.G_564_y(1);
        this.minecraft.G_624_v().n_1700_B(G_564_y);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        float f = flag ? 7.0f : 0.0f;
        C_2701_A.blit(p_237914_1_, p_237914_2_, p_237914_3_, 0.0f, f, 8, 7, 8, 14);
        if (flag) {
            this.s_956_w = w_1484_f;
            this.k_2293_S = lightning.product.s_1671_u$n_1700_B.J_1907_R;
        }
    }

    private void J_1907_R(g_221_o p_237921_1_, int p_237921_2_, int p_237921_3_, int p_237921_4_, int p_237921_5_) {
        boolean flag = p_237921_4_ >= p_237921_2_ && p_237921_4_ <= p_237921_2_ + 9 && p_237921_5_ >= p_237921_3_ && p_237921_5_ <= p_237921_3_ + 9 && p_237921_5_ < s_1671_u.G_564_y(12) + 20 && p_237921_5_ > s_1671_u.G_564_y(1);
        this.minecraft.G_624_v().n_1700_B(J_1907_R);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        float f = flag ? 8.0f : 0.0f;
        C_2701_A.blit(p_237921_1_, p_237921_2_, p_237921_3_, 0.0f, f, 8, 8, 8, 16);
        if (flag) {
            this.s_956_w = v_4262_N;
            this.k_2293_S = lightning.product.s_1671_u$n_1700_B.n_1700_B;
        }
    }

    private void R_4764_Y(g_221_o p_237925_1_, int p_237925_2_, int p_237925_3_, int p_237925_4_, int p_237925_5_) {
        boolean flag = p_237925_4_ >= p_237925_2_ && p_237925_4_ <= p_237925_2_ + 9 && p_237925_5_ >= p_237925_3_ && p_237925_5_ <= p_237925_3_ + 9 && p_237925_5_ < s_1671_u.G_564_y(12) + 20 && p_237925_5_ > s_1671_u.G_564_y(1);
        this.minecraft.G_624_v().n_1700_B(R_4764_Y);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        float f = flag ? 8.0f : 0.0f;
        C_2701_A.blit(p_237925_1_, p_237925_2_, p_237925_3_, 0.0f, f, 8, 8, 8, 16);
        if (flag) {
            this.s_956_w = u_1723_Y;
            this.k_2293_S = lightning.product.s_1671_u$n_1700_B.n_1700_B;
        }
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.s_1671_u$n_1700_B.n_1700_B();
        }
    }

    class R_4764_Y
    extends z_3470_q<J_1907_R> {
        public R_4764_Y() {
            super(s_1671_u.this.Q_4569_t + 10, s_1671_u.G_564_y(12) + 20, s_1671_u.G_564_y(1), s_1671_u.G_564_y(12) + 20, 13);
        }

        public void n_1700_B(PlayerInfo p_223870_1_) {
            s_1671_u s_1671_u2 = s_1671_u.this;
            Objects.requireNonNull(s_1671_u2);
            this.n_1700_B(s_1671_u2.new J_1907_R(p_223870_1_));
        }

        @Override
        public int getRowWidth() {
            return (int)((double)this.width * 1.0);
        }

        @Override
        public boolean isFocused() {
            return s_1671_u.this.getListener() == this;
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if (button == 0 && mouseX < (double)this.getScrollbarPosition() && mouseY >= (double)this.y0 && mouseY <= (double)this.y1) {
                int i = s_1671_u.this.h_1847_R;
                int j = s_1671_u.this.h_1847_R + s_1671_u.this.Q_4569_t;
                int k = (int)Math.floor(mouseY - (double)this.y0) - this.headerHeight + (int)this.getScrollAmount() - 4;
                int l = k / this.itemHeight;
                if (mouseX >= (double)i && mouseX <= (double)j && l >= 0 && k >= 0 && l < this.getItemCount()) {
                    this.n_1700_B(l);
                    this.n_1700_B(k, l, mouseX, mouseY, this.width);
                }
                return true;
            }
            return super.mouseClicked(mouseX, mouseY, button);
        }

        @Override
        public void n_1700_B(int p_231401_1_, int p_231401_2_, double p_231401_3_, double p_231401_5_, int p_231401_7_) {
            if (p_231401_2_ >= 0 && p_231401_2_ <= s_1671_u.this.M_588_G.w_1484_f.size() && s_1671_u.this.k_2293_S != lightning.product.s_1671_u$n_1700_B.R_4764_Y) {
                if (s_1671_u.this.k_2293_S == lightning.product.s_1671_u$n_1700_B.n_1700_B) {
                    if (s_1671_u.this.M_588_G.w_1484_f.get(p_231401_2_).R_4764_Y()) {
                        s_1671_u.this.v_4262_N(p_231401_2_);
                    } else {
                        s_1671_u.this.u_1723_Y(p_231401_2_);
                    }
                } else if (s_1671_u.this.k_2293_S == lightning.product.s_1671_u$n_1700_B.J_1907_R) {
                    s_1671_u.this.w_1484_f(p_231401_2_);
                }
            }
        }

        @Override
        public void n_1700_B(int p_231400_1_) {
            this.G_564_y(p_231400_1_);
            if (p_231400_1_ != -1) {
                NarrationHelper.n_1700_B(K_1289_S.n_1700_B("narrator.select", s_1671_u.this.M_588_G.w_1484_f.get(p_231400_1_).n_1700_B()));
            }
            this.J_1907_R(p_231400_1_);
        }

        public void J_1907_R(int p_223869_1_) {
            s_1671_u.this.Y_259_p = p_223869_1_;
            s_1671_u.this.n_1700_B();
        }

        public void n_1700_B(@Nullable J_1907_R entry) {
            super.setSelected(entry);
            s_1671_u.this.Y_259_p = this.getEventListeners().indexOf(entry);
            s_1671_u.this.n_1700_B();
        }

        @Override
        public void renderBackground(g_221_o p_230433_1_) {
            s_1671_u.this.renderBackground(p_230433_1_);
        }

        @Override
        public int getScrollbarPosition() {
            return s_1671_u.this.h_1847_R + this.width - 5;
        }

        @Override
        public int getMaxPosition() {
            return this.getItemCount() * 13;
        }

        @Override
        public /* synthetic */ void setSelected(@Nullable o_2488_o.n_1700_B n_1700_B2) {
            this.n_1700_B((J_1907_R)n_1700_B2);
        }
    }

    class J_1907_R
    extends ObjectSelectionList.n_1700_B<J_1907_R> {
        private final PlayerInfo J_1907_R;

        public J_1907_R(PlayerInfo p_i51614_2_) {
            this.J_1907_R = p_i51614_2_;
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            this.n_1700_B(p_230432_1_, this.J_1907_R, p_230432_4_, p_230432_3_, p_230432_7_, p_230432_8_);
        }

        private void n_1700_B(g_221_o p_237932_1_, PlayerInfo p_237932_2_, int p_237932_3_, int p_237932_4_, int p_237932_5_, int p_237932_6_) {
            int i = !p_237932_2_.G_564_y() ? 0xA0A0A0 : (p_237932_2_.P_1922_E() ? 0x7FFF7F : 0xFFFFFF);
            s_1671_u.this.font.J_1907_R(p_237932_1_, p_237932_2_.n_1700_B(), (float)(s_1671_u.this.h_1847_R + 3 + 12), (float)(p_237932_4_ + 1), i);
            if (p_237932_2_.R_4764_Y()) {
                s_1671_u.this.J_1907_R(p_237932_1_, s_1671_u.this.h_1847_R + s_1671_u.this.Q_4569_t - 10, p_237932_4_ + 1, p_237932_5_, p_237932_6_);
            } else {
                s_1671_u.this.R_4764_Y(p_237932_1_, s_1671_u.this.h_1847_R + s_1671_u.this.Q_4569_t - 10, p_237932_4_ + 1, p_237932_5_, p_237932_6_);
            }
            s_1671_u.this.n_1700_B(p_237932_1_, s_1671_u.this.h_1847_R + s_1671_u.this.Q_4569_t - 22, p_237932_4_ + 2, p_237932_5_, p_237932_6_);
            y_2772_m.n_1700_B(p_237932_2_.J_1907_R(), () -> {
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                C_2701_A.blit(p_237932_1_, s_1671_u.this.h_1847_R + 2 + 2, p_237932_4_ + 1, 8, 8, 8.0f, 8.0f, 8, 8, 64, 64);
                C_2701_A.blit(p_237932_1_, s_1671_u.this.h_1847_R + 2 + 2, p_237932_4_ + 1, 8, 8, 40.0f, 8.0f, 8, 8, 64, 64);
            });
        }
    }
}


