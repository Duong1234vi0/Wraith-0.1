/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Queues
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import java.util.Deque;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.U_679_Y;
import lightning.product.X_4793_t;
import lightning.product.Z_1567_W;
import lightning.product.BetterMinecraft;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.g_4418_P;
import lightning.product.h_4412_P;
import lightning.product.ClientBootstrap;
import lightning.product.p_1395_w;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.y_4642_Y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class U_1085_u
extends C_2701_A {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final MinecraftClient J_1907_R;
    private final List<String> R_4764_Y = Lists.newArrayList();
    private final List<X_4793_t<x_282_a>> G_564_y = Lists.newArrayList();
    private final List<X_4793_t<FormattedCharSequence>> P_1922_E = Lists.newArrayList();
    private final Deque<x_282_a> u_1723_Y = Queues.newArrayDeque();
    private int v_4262_N;
    private boolean w_1484_f;
    private long t_148_a = 0L;
    private int s_956_w = 0;
    private String u_2550_I = "";
    private int M_588_G = 0;
    private int P_4830_p = 0;
    private int h_1847_R = 0;
    private X_4793_t<x_282_a> Q_4569_t;
    private int M_182_A = 1;
    private int t_1786_h = 1;

    public U_1085_u(MinecraftClient mcIn) {
        this.J_1907_R = mcIn;
    }

    public void n_1700_B(g_221_o p_238492_1_, int p_238492_2_) {
        int i = this.G_564_y();
        if (this.s_956_w != i) {
            this.s_956_w = i;
            this.n_1700_B();
        }
        if (!this.w_1484_f()) {
            this.u_2550_I();
            int j = this.v_4262_N();
            int k = this.P_1922_E.size();
            if (k > 0) {
                boolean flag = false;
                if (this.t_148_a()) {
                    flag = true;
                }
                double d0 = this.u_1723_Y();
                int l = u_530_F.P_1922_E((double)this.G_564_y() / d0);
                c_4037_x.v_4276_D();
                c_4037_x.R_4764_Y(2.0f, 8.0f, 0.0f);
                c_4037_x.n_1700_B(d0, d0, 1.0);
                double d1 = this.J_1907_R.P_4830_p.u_2550_I * (double)0.9f + (double)0.1f;
                double d2 = this.J_1907_R.P_4830_p.P_4830_p;
                double d3 = 9.0 * (this.J_1907_R.P_4830_p.M_588_G + 1.0);
                double d4 = -8.0 * (this.J_1907_R.P_4830_p.M_588_G + 1.0) + 4.0 * this.J_1907_R.P_4830_p.M_588_G;
                int i1 = 0;
                for (int j1 = 0; j1 + this.v_4262_N < this.P_1922_E.size() && j1 < j; ++j1) {
                    int k1;
                    X_4793_t<FormattedCharSequence> chatline = this.P_1922_E.get(j1 + this.v_4262_N);
                    if (chatline == null || (k1 = p_238492_2_ - chatline.J_1907_R()) >= 200 && !flag) continue;
                    double d5 = flag ? 1.0 : U_1085_u.J_1907_R(k1);
                    int i2 = (int)(255.0 * d5 * d1);
                    int j2 = (int)(255.0 * d5 * d2);
                    ++i1;
                    if (i2 <= 3) continue;
                    boolean k2 = false;
                    double d6 = (double)(-j1) * d3;
                    p_238492_1_.n_1700_B();
                    p_238492_1_.n_1700_B(0.0, 0.0, 50.0);
                    if (this.J_1907_R.P_4830_p.L_3570_A == 5) {
                        l = this.J_1907_R.t_148_a.n_1700_B(chatline.n_1700_B()) - 2;
                    }
                    if (this.J_1907_R.P_4830_p.L_3570_A != 3) {
                        BetterMinecraft betterMinecraft = y_4642_Y.R_4764_Y() ? null : (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
                        int maxX = !y_4642_Y.R_4764_Y() && betterMinecraft != null && betterMinecraft.w_1484_f() && betterMinecraft.Y_259_p().J_1907_R("\u0423\u043b\u0443\u0447\u0448\u0435\u043d\u043d\u044b\u0439 \u0432\u044b\u0432\u043e\u0434") != null && betterMinecraft.Y_259_p().J_1907_R("\u0423\u043b\u0443\u0447\u0448\u0435\u043d\u043d\u044b\u0439 \u0432\u044b\u0432\u043e\u0434") != false ? this.J_1907_R.t_148_a.n_1700_B(chatline.n_1700_B()) : l;
                        U_1085_u.fill(p_238492_1_, -2, (int)(d6 - d3), maxX + 2, (int)d6, j2 << 24);
                    }
                    c_4037_x.Y_601_j();
                    p_238492_1_.n_1700_B(0.0, 0.0, 50.0);
                    if (!this.J_1907_R.P_4830_p.Y_776_s) {
                        this.J_1907_R.t_148_a.J_1907_R(p_238492_1_, chatline.n_1700_B(), 0.0f, (float)((int)(d6 + d4)), 0xFFFFFF + (i2 << 24));
                    } else {
                        this.J_1907_R.t_148_a.n_1700_B(p_238492_1_, chatline.n_1700_B(), 0.0f, (float)((int)(d6 + d4)), 0xFFFFFF + (i2 << 24));
                    }
                    c_4037_x.u_2550_I();
                    c_4037_x.Y_259_p();
                    p_238492_1_.J_1907_R();
                }
                if (!this.u_1723_Y.isEmpty()) {
                    int l2 = (int)(128.0 * d1);
                    int j3 = (int)(255.0 * d2);
                    p_238492_1_.n_1700_B();
                    p_238492_1_.n_1700_B(0.0, 0.0, 50.0);
                    U_1085_u.fill(p_238492_1_, -2, 0, l + 4, 9, j3 << 24);
                    c_4037_x.Y_601_j();
                    p_238492_1_.n_1700_B(0.0, 0.0, 50.0);
                    this.J_1907_R.t_148_a.n_1700_B(p_238492_1_, new F_2904_S("chat.queue", this.u_1723_Y.size()), 0.0f, 1.0f, 0xFFFFFF + (l2 << 24));
                    p_238492_1_.J_1907_R();
                    c_4037_x.u_2550_I();
                    c_4037_x.Y_259_p();
                }
                if (flag) {
                    int i3 = 9;
                    c_4037_x.R_4764_Y(-3.0f, 0.0f, 0.0f);
                    int k3 = k * i3 + k;
                    int l3 = i1 * i3 + i1;
                    int i4 = this.v_4262_N * l3 / k;
                    int l1 = l3 * l3 / k3;
                    if (k3 != l3) {
                        int j4 = i4 > 0 ? 170 : 96;
                        int k4 = this.w_1484_f ? 0xCC3333 : 0x3333AA;
                        U_1085_u.fill(p_238492_1_, 0, -i4, 2, -i4 - l1, k4 + (j4 << 24));
                        U_1085_u.fill(p_238492_1_, 2, -i4, 1, -i4 - l1, 0xCCCCCC + (j4 << 24));
                    }
                }
                c_4037_x.d_2461_k();
            }
        }
    }

    private boolean w_1484_f() {
        return this.J_1907_R.P_4830_p.s_956_w == g_4418_P.R_4764_Y;
    }

    private static double J_1907_R(int counterIn) {
        double d0 = (double)counterIn / 200.0;
        d0 = 1.0 - d0;
        d0 *= 10.0;
        d0 = u_530_F.n_1700_B(d0, 0.0, 1.0);
        return d0 * d0;
    }

    public void n_1700_B(boolean clearSentMsgHistory) {
        this.u_1723_Y.clear();
        this.P_1922_E.clear();
        this.G_564_y.clear();
        if (clearSentMsgHistory) {
            this.R_4764_Y.clear();
        }
        this.u_2550_I = "";
        this.P_4830_p = 0;
        this.M_588_G = 0;
        this.h_1847_R = 0;
        this.Q_4569_t = null;
        this.M_182_A = 1;
        this.t_1786_h = 0;
    }

    public void n_1700_B(x_282_a chatComponent) {
        BetterMinecraft betterMinecraft;
        BetterMinecraft b_4074_q2 = betterMinecraft = y_4642_Y.R_4764_Y() ? null : (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
        if (!y_4642_Y.R_4764_Y() && betterMinecraft != null && betterMinecraft.w_1484_f() && betterMinecraft.Y_259_p().J_1907_R("\u0423\u0431\u0440\u0430\u0442\u044c \u0441\u043f\u0430\u043c") != null && betterMinecraft.Y_259_p().J_1907_R("\u0423\u0431\u0440\u0430\u0442\u044c \u0441\u043f\u0430\u043c").booleanValue()) {
            String newMessage = chatComponent.getString();
            if (this.Q_4569_t != null && this.Q_4569_t.n_1700_B().getString().contains(newMessage)) {
                ++this.M_182_A;
                MutableComponent updatedMessage = chatComponent.P_1922_E().n_1700_B(String.valueOf((Object)D_4024_W.w_1484_f) + " (x" + this.M_182_A + ")");
                this.n_1700_B(this.Q_4569_t.R_4764_Y());
                this.n_1700_B(updatedMessage, this.Q_4569_t.R_4764_Y(), this.J_1907_R.M_588_G.G_564_y(), false);
                this.Q_4569_t = new X_4793_t<MutableComponent>(this.J_1907_R.M_588_G.G_564_y(), updatedMessage, this.Q_4569_t.R_4764_Y());
                this.n_1700_B();
            } else {
                this.M_182_A = 1;
                int currentChatLineID = this.t_1786_h++;
                this.n_1700_B(chatComponent, currentChatLineID, this.J_1907_R.M_588_G.G_564_y(), false);
                this.Q_4569_t = new X_4793_t<x_282_a>(this.J_1907_R.M_588_G.G_564_y(), chatComponent, currentChatLineID);
            }
            n_1700_B.info("[CHAT] {}", (Object)chatComponent.getString().replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n"));
        } else {
            String plain = chatComponent.getString();
            if (plain.equals(this.u_2550_I)) {
                if (this.P_4830_p == 0) {
                    this.P_4830_p = ++this.h_1847_R;
                }
                chatComponent = chatComponent.P_1922_E().n_1700_B(new U_2871_b(" (" + ++this.M_588_G + ")").n_1700_B(D_4024_W.w_1484_f));
            } else {
                this.u_2550_I = plain;
                this.M_588_G = 1;
                this.P_4830_p = ++this.h_1847_R;
            }
            this.n_1700_B(chatComponent, this.P_4830_p);
        }
    }

    public void n_1700_B(x_282_a chatComponent, int chatLineId) {
        this.n_1700_B(chatComponent, chatLineId, this.J_1907_R.M_588_G.G_564_y(), false);
        n_1700_B.info("[CHAT] {}", (Object)chatComponent.getString().replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n"));
    }

    private void n_1700_B(x_282_a p_238493_1_, int p_238493_2_, int p_238493_3_, boolean p_238493_4_) {
        if (p_238493_2_ != 0) {
            this.n_1700_B(p_238493_2_);
        }
        int i = u_530_F.R_4764_Y((double)this.G_564_y() / this.u_1723_Y());
        List<FormattedCharSequence> list = p_1395_w.n_1700_B(p_238493_1_, i, this.J_1907_R.t_148_a);
        boolean flag = this.t_148_a();
        for (FormattedCharSequence ireorderingprocessor : list) {
            if (flag && this.v_4262_N > 0) {
                this.w_1484_f = true;
                this.n_1700_B(1.0);
            }
            this.P_1922_E.add(0, new X_4793_t<FormattedCharSequence>(p_238493_3_, ireorderingprocessor, p_238493_2_));
        }
        while (this.P_1922_E.size() > 100) {
            this.P_1922_E.remove(this.P_1922_E.size() - 1);
        }
        if (!p_238493_4_) {
            this.G_564_y.add(0, new X_4793_t<x_282_a>(p_238493_3_, p_238493_1_, p_238493_2_));
            while (this.G_564_y.size() > 100) {
                this.G_564_y.remove(this.G_564_y.size() - 1);
            }
        }
    }

    public void n_1700_B() {
        this.P_1922_E.clear();
        this.R_4764_Y();
        for (int i = this.G_564_y.size() - 1; i >= 0; --i) {
            X_4793_t<x_282_a> chatline = this.G_564_y.get(i);
            this.n_1700_B(chatline.n_1700_B(), chatline.R_4764_Y(), chatline.J_1907_R(), true);
        }
    }

    public List<String> J_1907_R() {
        return this.R_4764_Y;
    }

    public void n_1700_B(String message) {
        if (this.R_4764_Y.isEmpty() || !this.R_4764_Y.get(this.R_4764_Y.size() - 1).equals(message)) {
            this.R_4764_Y.add(message);
        }
    }

    public void R_4764_Y() {
        this.v_4262_N = 0;
        this.w_1484_f = false;
    }

    public void n_1700_B(double posInc) {
        this.v_4262_N = (int)((double)this.v_4262_N + posInc);
        int i = this.P_1922_E.size();
        if (this.v_4262_N > i - this.v_4262_N()) {
            this.v_4262_N = i - this.v_4262_N();
        }
        if (this.v_4262_N <= 0) {
            this.v_4262_N = 0;
            this.w_1484_f = false;
        }
    }

    public boolean n_1700_B(double p_238491_1_, double p_238491_3_) {
        if (this.t_148_a() && !this.J_1907_R.P_4830_p.RetryCallException && !this.w_1484_f() && !this.u_1723_Y.isEmpty()) {
            double d0 = p_238491_1_ - 2.0;
            double d1 = (double)this.J_1907_R.RealmsServerPing().M_182_A() - p_238491_3_ - 40.0;
            if (d0 <= (double)u_530_F.R_4764_Y((double)this.G_564_y() / this.u_1723_Y()) && d1 < 0.0 && d1 > (double)u_530_F.R_4764_Y(-9.0 * this.u_1723_Y())) {
                this.n_1700_B(this.u_1723_Y.remove());
                this.t_148_a = System.currentTimeMillis();
                return true;
            }
            return false;
        }
        return false;
    }

    @Nullable
    public Z_1567_W J_1907_R(double p_238494_1_, double p_238494_3_) {
        if (this.t_148_a() && !this.J_1907_R.P_4830_p.RetryCallException && !this.w_1484_f()) {
            double d0 = p_238494_1_ - 2.0;
            double d1 = (double)this.J_1907_R.RealmsServerPing().M_182_A() - p_238494_3_ - 40.0;
            d0 = u_530_F.R_4764_Y(d0 / this.u_1723_Y());
            d1 = u_530_F.R_4764_Y(d1 / (this.u_1723_Y() * (this.J_1907_R.P_4830_p.M_588_G + 1.0)));
            if (!(d0 < 0.0) && !(d1 < 0.0)) {
                int j;
                int i = Math.min(this.v_4262_N(), this.P_1922_E.size());
                if (d0 <= (double)u_530_F.R_4764_Y((double)this.G_564_y() / this.u_1723_Y()) && d1 < (double)(9 * i + i) && (j = (int)(d1 / 9.0 + (double)this.v_4262_N)) >= 0 && j < this.P_1922_E.size()) {
                    X_4793_t<FormattedCharSequence> chatline = this.P_1922_E.get(j);
                    return this.J_1907_R.t_148_a.J_1907_R().n_1700_B(chatline.n_1700_B(), (int)d0);
                }
                return null;
            }
            return null;
        }
        return null;
    }

    private boolean t_148_a() {
        return this.J_1907_R.Y_1740_V instanceof h_4412_P;
    }

    public void n_1700_B(int id) {
        this.P_1922_E.removeIf(p_lambda$deleteChatLine$0_1_ -> p_lambda$deleteChatLine$0_1_.R_4764_Y() == id);
        this.G_564_y.removeIf(p_lambda$deleteChatLine$1_1_ -> p_lambda$deleteChatLine$1_1_.R_4764_Y() == id);
    }

    public int G_564_y() {
        int i = U_1085_u.J_1907_R(this.J_1907_R.P_4830_p.C_2741_M);
        U_679_Y mainwindow = MinecraftClient.A_4115_X().RealmsServerPing();
        int j = (int)((double)(mainwindow.u_2550_I() - 3) / mainwindow.w_1457_N());
        return u_530_F.n_1700_B(i, 0, j);
    }

    public int P_1922_E() {
        return U_1085_u.R_4764_Y((this.t_148_a() ? this.J_1907_R.P_4830_p.q_2307_F : this.J_1907_R.P_4830_p.k_2293_S) / (this.J_1907_R.P_4830_p.M_588_G + 1.0));
    }

    public double u_1723_Y() {
        return this.J_1907_R.P_4830_p.Q_2552_b;
    }

    public static int J_1907_R(double p_194814_0_) {
        int i = 320;
        int j = 40;
        return u_530_F.R_4764_Y(p_194814_0_ * 280.0 + 40.0);
    }

    public static int R_4764_Y(double p_194816_0_) {
        int i = 180;
        int j = 20;
        return u_530_F.R_4764_Y(p_194816_0_ * 160.0 + 20.0);
    }

    public int v_4262_N() {
        return this.P_1922_E() / 9;
    }

    private long s_956_w() {
        return (long)(this.J_1907_R.P_4830_p.Z_875_P * 1000.0);
    }

    private void u_2550_I() {
        long i;
        if (!this.u_1723_Y.isEmpty() && (i = System.currentTimeMillis()) - this.t_148_a >= this.s_956_w()) {
            this.n_1700_B(this.u_1723_Y.remove());
            this.t_148_a = i;
        }
    }

    public void J_1907_R(x_282_a p_238495_1_) {
        if (this.J_1907_R.P_4830_p.Z_875_P <= 0.0) {
            this.n_1700_B(p_238495_1_);
        } else {
            long i = System.currentTimeMillis();
            if (i - this.t_148_a >= this.s_956_w()) {
                this.n_1700_B(p_238495_1_);
                this.t_148_a = i;
            } else {
                this.u_1723_Y.add(p_238495_1_);
            }
        }
    }
}



