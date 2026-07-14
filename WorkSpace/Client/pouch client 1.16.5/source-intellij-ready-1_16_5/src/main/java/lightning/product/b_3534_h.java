/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.net.InetAddress;
import java.net.UnknownHostException;
import lightning.product.F_2904_S;
import lightning.product.ClientIntentionPacket;
import lightning.product.K_1289_S;
import lightning.product.Q_936_s;
import lightning.product.MinecraftClient;
import lightning.product.c_1633_k;
import lightning.product.ServerboundHelloPacket;
import lightning.product.d_4952_K;
import lightning.product.k_2603_m;
import lightning.product.n_633_r;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.NarrationHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class b_3534_h {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final k_2603_m J_1907_R;
    private volatile boolean R_4764_Y;
    private c_1633_k G_564_y;

    public b_3534_h(k_2603_m p_i232500_1_) {
        this.J_1907_R = p_i232500_1_;
    }

    public void n_1700_B(final q_1982_R p_244798_1_, final String p_244798_2_, final int p_244798_3_) {
        final MinecraftClient minecraft = MinecraftClient.A_4115_X();
        minecraft.R_4764_Y(true);
        NarrationHelper.n_1700_B(K_1289_S.n_1700_B("mco.connect.success", new Object[0]));
        new Thread("Realms-connect-task"){

            @Override
            public void run() {
                InetAddress inetaddress = null;
                try {
                    inetaddress = InetAddress.getByName(p_244798_2_);
                    if (b_3534_h.this.R_4764_Y) {
                        return;
                    }
                    b_3534_h.this.G_564_y = c_1633_k.n_1700_B(inetaddress, p_244798_3_, minecraft.P_4830_p.u_1723_Y());
                    if (b_3534_h.this.R_4764_Y) {
                        return;
                    }
                    b_3534_h.this.G_564_y.n_1700_B(new Q_936_s(b_3534_h.this.G_564_y, minecraft, b_3534_h.this.J_1907_R, p_209500_0_ -> {}));
                    if (b_3534_h.this.R_4764_Y) {
                        return;
                    }
                    b_3534_h.this.G_564_y.n_1700_B(new ClientIntentionPacket(p_244798_2_, p_244798_3_, d_4952_K.G_564_y));
                    if (b_3534_h.this.R_4764_Y) {
                        return;
                    }
                    b_3534_h.this.G_564_y.n_1700_B(new ServerboundHelloPacket(minecraft.z_1737_N().P_1922_E()));
                    minecraft.n_1700_B(p_244798_1_.G_564_y(p_244798_2_));
                }
                catch (UnknownHostException unknownhostexception) {
                    minecraft.z_4693_k().J_1907_R();
                    if (b_3534_h.this.R_4764_Y) {
                        return;
                    }
                    n_1700_B.error("Couldn't connect to world", (Throwable)unknownhostexception);
                    n_633_r disconnectedrealmsscreen = new n_633_r(b_3534_h.this.J_1907_R, CommonComponents.t_148_a, new F_2904_S("disconnect.genericReason", "Unknown host '" + p_244798_2_ + "'"));
                    minecraft.execute(() -> minecraft.n_1700_B(disconnectedrealmsscreen));
                }
                catch (Exception exception) {
                    minecraft.z_4693_k().J_1907_R();
                    if (b_3534_h.this.R_4764_Y) {
                        return;
                    }
                    n_1700_B.error("Couldn't connect to world", (Throwable)exception);
                    String s = exception.toString();
                    if (inetaddress != null) {
                        String s1 = String.valueOf(inetaddress) + ":" + p_244798_3_;
                        s = s.replaceAll(s1, "");
                    }
                    n_633_r disconnectedrealmsscreen1 = new n_633_r(b_3534_h.this.J_1907_R, CommonComponents.t_148_a, new F_2904_S("disconnect.genericReason", s));
                    minecraft.execute(() -> minecraft.n_1700_B(disconnectedrealmsscreen1));
                }
            }
        }.start();
    }

    public void n_1700_B() {
        this.R_4764_Y = true;
        if (this.G_564_y != null && this.G_564_y.u_1723_Y()) {
            this.G_564_y.n_1700_B(new F_2904_S("disconnect.genericReason"));
            this.G_564_y.u_2550_I();
        }
    }

    public void J_1907_R() {
        if (this.G_564_y != null) {
            if (this.G_564_y.u_1723_Y()) {
                this.G_564_y.n_1700_B();
            } else {
                this.G_564_y.u_2550_I();
            }
        }
    }
}



