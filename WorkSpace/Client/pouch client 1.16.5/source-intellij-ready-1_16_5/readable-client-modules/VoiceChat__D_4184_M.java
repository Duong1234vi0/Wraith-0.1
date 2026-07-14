/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.H_1491_c;
import lightning.product.H_2506_c;
import lightning.product.U_2871_b;
import lightning.product.U_3758_B;
import lightning.product.X_3546_T;
import lightning.product.v_1900_v;
import lightning.product.y_2603_k;
import lombok.Generated;
import mods.voicechat.gui.VoiceChatScreen;
import mods.voicechat.gui.onboarding.OnboardingManager;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;

public class D_4184_M
extends X_3546_T {
    public H_1491_c v_4262_N = new H_1491_c("\u041e\u0442\u043a\u0440\u044b\u0442\u044c \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438", () -> c_3005_b.n_1700_B(new VoiceChatScreen()));
    public H_1491_c w_1484_f = new H_1491_c("\u041e\u0442\u043a\u0440\u044b\u0442\u044c \u0433\u0430\u0439\u0434", () -> OnboardingManager.startOnboarding(null));

    public D_4184_M() {
        super("VoiceChat", y_2603_k.P_1922_E);
        this.n_1700_B(this.w_1484_f, this.v_4262_N);
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
        v_1900_v.n_1700_B(new U_2871_b("\u041f\u0435\u0440\u0435\u0437\u0430\u0439\u0434\u0438\u0442\u0435 \u0432 \u043c\u0438\u0440, \u0434\u043b\u044f \u0440\u0430\u0431\u043e\u0442\u044b Voice Chat").n_1700_B(D_4024_W.u_2550_I, D_4024_W.N_4405_n), new Object[0]);
        U_3758_B.n_1700_B("G", "\u041f\u0435\u0440\u0435\u0437\u0430\u0439\u0434\u0438\u0442\u0435 \u0432 \u043c\u0438\u0440, \u0434\u043b\u044f \u0440\u0430\u0431\u043e\u0442\u044b Voice Chat", H_2506_c.n_1700_B("#54FB54FF"));
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        ClientVoicechat client = ClientManager.getClient();
        if (client != null) {
            client.close();
        }
        v_1900_v.n_1700_B(new U_2871_b("\u041f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u0440\u0430\u0437\u043e\u0440\u0432\u0430\u043d\u043e").n_1700_B(D_4024_W.P_4830_p, D_4024_W.N_4405_n), new Object[0]);
        U_3758_B.n_1700_B("M", "\u041f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u0440\u0430\u0437\u043e\u0440\u0432\u0430\u043d\u043e", H_2506_c.n_1700_B("#FB5454FF"));
    }

    @Generated
    public H_1491_c h_1847_R() {
        return this.v_4262_N;
    }

    @Generated
    public H_1491_c Q_4569_t() {
        return this.w_1484_f;
    }
}

