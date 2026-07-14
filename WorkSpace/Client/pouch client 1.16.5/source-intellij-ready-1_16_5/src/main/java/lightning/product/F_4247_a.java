/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import lightning.product.F_2904_S;
import lightning.product.H_1083_k;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.RealmsLongConfirmationScreen;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.NarrationHelper;
import lightning.product.u_744_e;
import lightning.product.w_728_N;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class F_4247_a
extends RealmsScreen {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final x_282_a J_1907_R = new F_2904_S("mco.configure.world.subscription.title");
    private static final x_282_a R_4764_Y = new F_2904_S("mco.configure.world.subscription.start");
    private static final x_282_a G_564_y = new F_2904_S("mco.configure.world.subscription.timeleft");
    private static final x_282_a P_1922_E = new F_2904_S("mco.configure.world.subscription.recurring.daysleft");
    private static final x_282_a u_1723_Y = new F_2904_S("mco.configure.world.subscription.expired");
    private static final x_282_a v_4262_N = new F_2904_S("mco.configure.world.subscription.less_than_a_day");
    private static final x_282_a w_1484_f = new F_2904_S("mco.configure.world.subscription.month");
    private static final x_282_a t_148_a = new F_2904_S("mco.configure.world.subscription.months");
    private static final x_282_a s_956_w = new F_2904_S("mco.configure.world.subscription.day");
    private static final x_282_a u_2550_I = new F_2904_S("mco.configure.world.subscription.days");
    private final k_2603_m M_588_G;
    private final q_1982_R P_4830_p;
    private final k_2603_m h_1847_R;
    private x_282_a Q_4569_t;
    private String M_182_A;
    private H_1083_k.n_1700_B t_1786_h;

    public F_4247_a(k_2603_m p_i232223_1_, q_1982_R p_i232223_2_, k_2603_m p_i232223_3_) {
        this.M_588_G = p_i232223_1_;
        this.P_4830_p = p_i232223_2_;
        this.h_1847_R = p_i232223_3_;
    }

    @Override
    public void init() {
        this.n_1700_B(this.P_4830_p.n_1700_B);
        NarrationHelper.n_1700_B(J_1907_R.getString(), R_4764_Y.getString(), this.M_182_A, G_564_y.getString(), this.Q_4569_t.getString());
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.addButton(new Button(this.width / 2 - 100, F_4247_a.G_564_y(6), 200, 20, new F_2904_S("mco.configure.world.subscription.extend"), p_238073_1_ -> {
            String s = "https://aka.ms/ExtendJavaRealms?subscriptionId=" + this.P_4830_p.J_1907_R + "&profileId=" + this.minecraft.z_1737_N().J_1907_R();
            this.minecraft.Q_4569_t.n_1700_B(s);
            j_3341_s.t_148_a().n_1700_B(s);
        }));
        this.addButton(new Button(this.width / 2 - 100, F_4247_a.G_564_y(12), 200, 20, CommonComponents.w_1484_f, p_238071_1_ -> this.minecraft.n_1700_B(this.M_588_G)));
        if (this.P_4830_p.s_956_w) {
            this.addButton(new Button(this.width / 2 - 100, F_4247_a.G_564_y(10), 200, 20, new F_2904_S("mco.configure.world.delete.button"), p_238069_1_ -> {
                F_2904_S itextcomponent = new F_2904_S("mco.configure.world.delete.question.line1");
                F_2904_S itextcomponent1 = new F_2904_S("mco.configure.world.delete.question.line2");
                this.minecraft.n_1700_B(new RealmsLongConfirmationScreen(this::n_1700_B, RealmsLongConfirmationScreen.n_1700_B.n_1700_B, itextcomponent, itextcomponent1, true));
            }));
        }
    }

    private void n_1700_B(boolean p_238074_1_) {
        if (p_238074_1_) {
            new Thread("Realms-delete-realm"){

                @Override
                public void run() {
                    try {
                        p_178_J realmsclient = p_178_J.n_1700_B();
                        realmsclient.w_1484_f(F_4247_a.this.P_4830_p.n_1700_B);
                    }
                    catch (u_744_e realmsserviceexception) {
                        n_1700_B.error("Couldn't delete world");
                        n_1700_B.error((Object)realmsserviceexception);
                    }
                    F_4247_a.this.minecraft.execute(() -> F_4247_a.this.minecraft.n_1700_B(F_4247_a.this.h_1847_R));
                }
            }.start();
        }
        this.minecraft.n_1700_B(this);
    }

    private void n_1700_B(long p_224573_1_) {
        p_178_J realmsclient = p_178_J.n_1700_B();
        try {
            H_1083_k subscription = realmsclient.v_4262_N(p_224573_1_);
            this.Q_4569_t = this.n_1700_B(subscription.J_1907_R);
            this.M_182_A = F_4247_a.J_1907_R(subscription.n_1700_B);
            this.t_1786_h = subscription.R_4764_Y;
        }
        catch (u_744_e realmsserviceexception) {
            n_1700_B.error("Couldn't get subscription");
            this.minecraft.n_1700_B(new w_728_N(realmsserviceexception, this.M_588_G));
        }
    }

    private static String J_1907_R(long p_224574_0_) {
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());
        calendar.setTimeInMillis(p_224574_0_);
        return DateFormat.getDateTimeInstance().format(calendar.getTime());
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.n_1700_B(this.M_588_G);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        int i = this.width / 2 - 100;
        F_4247_a.drawCenteredString(matrixStack, this.font, J_1907_R, this.width / 2, 17, 0xFFFFFF);
        this.font.J_1907_R(matrixStack, R_4764_Y, (float)i, (float)F_4247_a.G_564_y(0), 0xA0A0A0);
        this.font.J_1907_R(matrixStack, this.M_182_A, (float)i, (float)F_4247_a.G_564_y(1), 0xFFFFFF);
        if (this.t_1786_h == H_1083_k.n_1700_B.n_1700_B) {
            this.font.J_1907_R(matrixStack, G_564_y, (float)i, (float)F_4247_a.G_564_y(3), 0xA0A0A0);
        } else if (this.t_1786_h == H_1083_k.n_1700_B.J_1907_R) {
            this.font.J_1907_R(matrixStack, P_1922_E, (float)i, (float)F_4247_a.G_564_y(3), 0xA0A0A0);
        }
        this.font.J_1907_R(matrixStack, this.Q_4569_t, (float)i, (float)F_4247_a.G_564_y(4), 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private x_282_a n_1700_B(int p_224576_1_) {
        if (p_224576_1_ < 0 && this.P_4830_p.s_956_w) {
            return u_1723_Y;
        }
        if (p_224576_1_ <= 1) {
            return v_4262_N;
        }
        int i = p_224576_1_ / 30;
        int j = p_224576_1_ % 30;
        U_2871_b iformattabletextcomponent = new U_2871_b("");
        if (i > 0) {
            iformattabletextcomponent.n_1700_B(Integer.toString(i)).n_1700_B(" ");
            if (i == 1) {
                iformattabletextcomponent.n_1700_B(w_1484_f);
            } else {
                iformattabletextcomponent.n_1700_B(t_148_a);
            }
        }
        if (j > 0) {
            if (i > 0) {
                iformattabletextcomponent.n_1700_B(", ");
            }
            iformattabletextcomponent.n_1700_B(Integer.toString(j)).n_1700_B(" ");
            if (j == 1) {
                iformattabletextcomponent.n_1700_B(s_956_w);
            } else {
                iformattabletextcomponent.n_1700_B(u_2550_I);
            }
        }
        return iformattabletextcomponent;
    }
}


