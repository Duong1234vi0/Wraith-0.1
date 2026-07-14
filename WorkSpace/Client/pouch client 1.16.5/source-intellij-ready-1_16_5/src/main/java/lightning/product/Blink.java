/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.D_3318_r;
import lightning.product.ServerboundInteractPacket;
import lightning.product.F_489_x;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.N_3268_u;
import lightning.product.Q_2753_H;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.l_3747_P;
import lightning.product.BooleanSetting;
import lightning.product.Packet;
import lightning.product.ModuleCategory;

public class Blink
extends Module {
    private final BooleanSetting otpravlyatPaketyNaUdarEnabled = new BooleanSetting("\u041e\u0442\u043f\u0440\u0430\u0432\u043b\u044f\u0442\u044c \u043f\u0430\u043a\u0435\u0442\u044b \u043d\u0430 \u0443\u0434\u0430\u0440", true);
    private final BooleanSetting otobrazhatPoziciyuEnabled = new BooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u043f\u043e\u0437\u0438\u0446\u0438\u044e", true);
    private final h_2367_h t_148_a = new h_2367_h("\u0426\u0432\u0435\u0442 \u0431\u043e\u043a\u0441\u0430", true, new Color(100, 150, 255, 100).getRGB(), this.otobrazhatPoziciyuEnabled::isEnabled);
    private final BooleanSetting avtomaticheskiOtpravlyatEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u043e\u0442\u043f\u0440\u0430\u0432\u043b\u044f\u0442\u044c", true);
    private final NumberSetting kolVoTikovMezhduOtpravkamiSetting = new NumberSetting("\u041a\u043e\u043b-\u0432\u043e \u0442\u0438\u043a\u043e\u0432 \u043c\u0435\u0436\u0434\u0443 \u043e\u0442\u043f\u0440\u0430\u0432\u043a\u0430\u043c\u0438", 10.0f, 1.0f, 100.0f, 1.0f, this.avtomaticheskiOtpravlyatEnabled::isEnabled);
    private final CopyOnWriteArrayList<Packet<?>> M_588_G = new CopyOnWriteArrayList();
    private e_2866_D P_4830_p = null;
    private int h_1847_R = 0;

    public Blink() {
        super("Blink", ModuleCategory.J_1907_R);
        this.addSettings(this.otpravlyatPaketyNaUdarEnabled, this.otobrazhatPoziciyuEnabled, this.t_148_a, this.avtomaticheskiOtpravlyatEnabled, this.kolVoTikovMezhduOtpravkamiSetting);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        ServerboundInteractPacket useEntityPacket;
        Packet<?> t_3138_Z2;
        if (Blink.c_3005_b.Y_259_p == null || Blink.c_3005_b.Y_601_j == null || c_3005_b.e_4240_b()) {
            return;
        }
        if (Blink.c_3005_b.Y_259_p.Z_2812_M()) {
            this.h_1847_R();
            this.R_4764_Y();
            return;
        }
        if (e.R_4764_Y() && e.G_564_y() instanceof N_3268_u) {
            this.M_588_G.add(e.G_564_y());
            e.n_1700_B(true);
            return;
        }
        if (this.otpravlyatPaketyNaUdarEnabled.isEnabled().booleanValue() && e.R_4764_Y() && (t_3138_Z2 = e.G_564_y()) instanceof ServerboundInteractPacket && (useEntityPacket = (ServerboundInteractPacket)t_3138_Z2).J_1907_R() == ServerboundInteractPacket.n_1700_B.J_1907_R && !this.M_588_G.isEmpty()) {
            this.h_1847_R();
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (Blink.c_3005_b.Y_259_p == null || Blink.c_3005_b.Y_601_j == null) {
            return;
        }
        if (Blink.c_3005_b.Y_259_p.Z_2812_M()) {
            this.h_1847_R();
            this.R_4764_Y();
            return;
        }
        if (this.avtomaticheskiOtpravlyatEnabled.isEnabled().booleanValue() && ++this.h_1847_R >= ((Float)this.kolVoTikovMezhduOtpravkamiSetting.getValue()).intValue()) {
            this.h_1847_R();
            this.h_1847_R = 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Y_1740_V
    public void n_1700_B(I_4477_R e) {
        if (!this.otobrazhatPoziciyuEnabled.isEnabled().booleanValue() || Blink.c_3005_b.Y_259_p == null || this.P_4830_p == null) {
            return;
        }
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        if (buffer.s_956_w()) {
            return;
        }
        e_2866_D view = Blink.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        c_4037_x.v_4276_D();
        try {
            c_4037_x.J_1907_R(-view.J_1907_R, -view.R_4764_Y, -view.G_564_y);
            I_4817_s box = new I_4817_s(this.P_4830_p.J_1907_R - 0.3, this.P_4830_p.R_4764_Y, this.P_4830_p.G_564_y - 0.3, this.P_4830_p.J_1907_R + 0.3, this.P_4830_p.R_4764_Y + 1.8, this.P_4830_p.G_564_y + 0.3);
            F_489_x.n_1700_B(box, (Integer)this.t_148_a.J_1907_R(), true);
        }
        finally {
            c_4037_x.d_2461_k();
        }
    }

    private void h_1847_R() {
        if (Blink.c_3005_b.Y_259_p == null || Blink.c_3005_b.Y_259_p.n_1700_B == null) {
            return;
        }
        for (Packet<?> packet : this.M_588_G) {
            Blink.c_3005_b.Y_259_p.n_1700_B.J_1907_R(packet);
        }
        this.M_588_G.clear();
        this.P_4830_p = Blink.c_3005_b.Y_259_p.s_4990_V();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (Blink.c_3005_b.Y_259_p == null) {
            this.R_4764_Y();
            return;
        }
        this.P_4830_p = Blink.c_3005_b.Y_259_p.s_4990_V();
        this.M_588_G.clear();
        this.h_1847_R = 0;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (Blink.c_3005_b.Y_259_p != null && Blink.c_3005_b.Y_259_p.n_1700_B != null) {
            for (Packet<?> packet : this.M_588_G) {
                Blink.c_3005_b.Y_259_p.n_1700_B.n_1700_B(packet);
            }
        }
        this.M_588_G.clear();
        this.P_4830_p = null;
        this.h_1847_R = 0;
    }
}



