/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.util.concurrent.RateLimiter
 *  it.unimi.dsi.fastutil.booleans.BooleanConsumer
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import lightning.product.A_1038_p;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.F_1410_V;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.H_1974_E;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.c_4037_x;
import lightning.product.RealmsLongConfirmationScreen;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.l_3747_P;
import lightning.product.CommonComponents;
import lightning.product.NarrationHelper;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class C_290_v
extends RealmsScreen {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final ReentrantLock J_1907_R = new ReentrantLock();
    private final k_2603_m R_4764_Y;
    private final F_1410_V G_564_y;
    private final x_282_a P_1922_E;
    private final RateLimiter u_1723_Y;
    private Button v_4262_N;
    private final String w_1484_f;
    private final n_1700_B t_148_a;
    private volatile x_282_a s_956_w;
    private volatile x_282_a u_2550_I = new F_2904_S("mco.download.preparing");
    private volatile String M_588_G;
    private volatile boolean P_4830_p;
    private volatile boolean h_1847_R = true;
    private volatile boolean Q_4569_t;
    private volatile boolean M_182_A;
    private Long t_1786_h;
    private Long multiplayerClientSuggestionProvider;
    private long w_1457_N;
    private int Y_601_j;
    private static final String[] Y_259_p = new String[]{"", ".", ". .", ". . ."};
    private int Q_2552_b;
    private boolean C_2741_M;
    private final BooleanConsumer k_2293_S;

    public C_290_v(k_2603_m p_i232203_1_, F_1410_V p_i232203_2_, String p_i232203_3_, BooleanConsumer p_i232203_4_) {
        this.k_2293_S = p_i232203_4_;
        this.R_4764_Y = p_i232203_1_;
        this.w_1484_f = p_i232203_3_;
        this.G_564_y = p_i232203_2_;
        this.t_148_a = new n_1700_B(this);
        this.P_1922_E = new F_2904_S("mco.download.title");
        this.u_1723_Y = RateLimiter.create((double)0.1f);
    }

    @Override
    public void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.v_4262_N = this.addButton(new Button(this.width / 2 - 100, this.height - 42, 200, 20, CommonComponents.G_564_y, p_237834_1_ -> {
            this.P_4830_p = true;
            this.J_1907_R();
        }));
        this.n_1700_B();
    }

    private void n_1700_B() {
        if (!this.Q_4569_t) {
            if (!this.C_2741_M && this.n_1700_B(this.G_564_y.n_1700_B) >= 0x140000000L) {
                F_2904_S itextcomponent = new F_2904_S("mco.download.confirmation.line1", H_1974_E.J_1907_R(0x140000000L));
                F_2904_S itextcomponent1 = new F_2904_S("mco.download.confirmation.line2");
                this.minecraft.n_1700_B(new RealmsLongConfirmationScreen(p_237837_1_ -> {
                    this.C_2741_M = true;
                    this.minecraft.n_1700_B(this);
                    this.R_4764_Y();
                }, RealmsLongConfirmationScreen.n_1700_B.n_1700_B, itextcomponent, itextcomponent1, false));
            } else {
                this.R_4764_Y();
            }
        }
    }

    private long n_1700_B(String p_224152_1_) {
        A_1038_p filedownload = new A_1038_p();
        return filedownload.n_1700_B(p_224152_1_);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.Y_601_j;
        if (this.u_2550_I != null && this.u_1723_Y.tryAcquire(1)) {
            ArrayList list = Lists.newArrayList();
            list.add(this.P_1922_E);
            list.add(this.u_2550_I);
            if (this.M_588_G != null) {
                list.add(new U_2871_b(this.M_588_G + "%"));
                list.add(new U_2871_b(H_1974_E.J_1907_R(this.w_1457_N) + "/s"));
            }
            if (this.s_956_w != null) {
                list.add(this.s_956_w);
            }
            String s = list.stream().map(x_282_a::getString).collect(Collectors.joining("\n"));
            NarrationHelper.n_1700_B(s);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.P_4830_p = true;
            this.J_1907_R();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void J_1907_R() {
        if (this.Q_4569_t && this.k_2293_S != null && this.s_956_w == null) {
            this.k_2293_S.accept(true);
        }
        this.minecraft.n_1700_B(this.R_4764_Y);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        C_290_v.drawCenteredString(matrixStack, this.font, this.P_1922_E, this.width / 2, 20, 0xFFFFFF);
        C_290_v.drawCenteredString(matrixStack, this.font, this.u_2550_I, this.width / 2, 50, 0xFFFFFF);
        if (this.h_1847_R) {
            this.n_1700_B(matrixStack);
        }
        if (this.t_148_a.n_1700_B != 0L && !this.P_4830_p) {
            this.J_1907_R(matrixStack);
            this.R_4764_Y(matrixStack);
        }
        if (this.s_956_w != null) {
            C_290_v.drawCenteredString(matrixStack, this.font, this.s_956_w, this.width / 2, 110, 0xFF0000);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void n_1700_B(g_221_o p_237835_1_) {
        int i = this.font.n_1700_B((FormattedText)this.u_2550_I);
        if (this.Y_601_j % 10 == 0) {
            ++this.Q_2552_b;
        }
        this.font.J_1907_R(p_237835_1_, Y_259_p[this.Q_2552_b % Y_259_p.length], (float)(this.width / 2 + i / 2 + 5), 50.0f, 0xFFFFFF);
    }

    private void J_1907_R(g_221_o p_237836_1_) {
        double d0 = Math.min((double)this.t_148_a.n_1700_B / (double)this.t_148_a.J_1907_R, 1.0);
        this.M_588_G = String.format(Locale.ROOT, "%.1f", d0 * 100.0);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_4037_x.e_4240_b();
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
        double d1 = this.width / 2 - 100;
        double d2 = 0.5;
        bufferbuilder.pos(d1 - 0.5, 95.5, 0.0).color(217, 210, 210, 255).endVertex();
        bufferbuilder.pos(d1 + 200.0 * d0 + 0.5, 95.5, 0.0).color(217, 210, 210, 255).endVertex();
        bufferbuilder.pos(d1 + 200.0 * d0 + 0.5, 79.5, 0.0).color(217, 210, 210, 255).endVertex();
        bufferbuilder.pos(d1 - 0.5, 79.5, 0.0).color(217, 210, 210, 255).endVertex();
        bufferbuilder.pos(d1, 95.0, 0.0).color(128, 128, 128, 255).endVertex();
        bufferbuilder.pos(d1 + 200.0 * d0, 95.0, 0.0).color(128, 128, 128, 255).endVertex();
        bufferbuilder.pos(d1 + 200.0 * d0, 80.0, 0.0).color(128, 128, 128, 255).endVertex();
        bufferbuilder.pos(d1, 80.0, 0.0).color(128, 128, 128, 255).endVertex();
        tessellator.J_1907_R();
        c_4037_x.x_607_J();
        C_290_v.drawCenteredString(p_237836_1_, this.font, this.M_588_G + " %", this.width / 2, 84, 0xFFFFFF);
    }

    private void R_4764_Y(g_221_o p_237838_1_) {
        if (this.Y_601_j % 20 == 0) {
            if (this.t_1786_h != null) {
                long i = j_3341_s.J_1907_R() - this.multiplayerClientSuggestionProvider;
                if (i == 0L) {
                    i = 1L;
                }
                this.w_1457_N = 1000L * (this.t_148_a.n_1700_B - this.t_1786_h) / i;
                this.n_1700_B(p_237838_1_, this.w_1457_N);
            }
            this.t_1786_h = this.t_148_a.n_1700_B;
            this.multiplayerClientSuggestionProvider = j_3341_s.J_1907_R();
        } else {
            this.n_1700_B(p_237838_1_, this.w_1457_N);
        }
    }

    private void n_1700_B(g_221_o p_237833_1_, long p_237833_2_) {
        if (p_237833_2_ > 0L) {
            int i = this.font.J_1907_R(this.M_588_G);
            String s = "(" + H_1974_E.J_1907_R(p_237833_2_) + "/s)";
            this.font.J_1907_R(p_237833_1_, s, (float)(this.width / 2 + i / 2 + 15), 84.0f, 0xFFFFFF);
        }
    }

    private void R_4764_Y() {
        new Thread(() -> {
            try {
                if (J_1907_R.tryLock(1L, TimeUnit.SECONDS)) {
                    if (this.P_4830_p) {
                        this.G_564_y();
                        return;
                    }
                    this.u_2550_I = new F_2904_S("mco.download.downloading", this.w_1484_f);
                    A_1038_p filedownload = new A_1038_p();
                    filedownload.n_1700_B(this.G_564_y.n_1700_B);
                    filedownload.n_1700_B(this.G_564_y, this.w_1484_f, this.t_148_a, this.minecraft.t_148_a());
                    while (!filedownload.J_1907_R()) {
                        if (filedownload.R_4764_Y()) {
                            filedownload.n_1700_B();
                            this.s_956_w = new F_2904_S("mco.download.failed");
                            this.v_4262_N.setMessage(CommonComponents.R_4764_Y);
                            return;
                        }
                        if (filedownload.G_564_y()) {
                            if (!this.M_182_A) {
                                this.u_2550_I = new F_2904_S("mco.download.extracting");
                            }
                            this.M_182_A = true;
                        }
                        if (this.P_4830_p) {
                            filedownload.n_1700_B();
                            this.G_564_y();
                            return;
                        }
                        try {
                            Thread.sleep(500L);
                        }
                        catch (InterruptedException interruptedexception) {
                            n_1700_B.error("Failed to check Realms backup download status");
                        }
                    }
                    this.Q_4569_t = true;
                    this.u_2550_I = new F_2904_S("mco.download.done");
                    this.v_4262_N.setMessage(CommonComponents.R_4764_Y);
                    return;
                }
                this.u_2550_I = new F_2904_S("mco.download.failed");
            }
            catch (InterruptedException interruptedexception1) {
                n_1700_B.error("Could not acquire upload lock");
                return;
            }
            catch (Exception exception) {
                this.s_956_w = new F_2904_S("mco.download.failed");
                exception.printStackTrace();
                return;
            }
            finally {
                if (!J_1907_R.isHeldByCurrentThread()) {
                    return;
                }
                J_1907_R.unlock();
                this.h_1847_R = false;
                this.Q_4569_t = true;
            }
        }).start();
    }

    private void G_564_y() {
        this.u_2550_I = new F_2904_S("mco.download.cancelled");
    }

    public class n_1700_B {
        public volatile long n_1700_B;
        public volatile long J_1907_R;

        public n_1700_B(C_290_v this$0) {
        }
    }
}


