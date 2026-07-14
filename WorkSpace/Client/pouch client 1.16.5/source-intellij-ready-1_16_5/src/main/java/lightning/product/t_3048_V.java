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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import lightning.product.F_2904_S;
import lightning.product.I_1084_e;
import lightning.product.ClientIntentionPacket;
import lightning.product.Q_936_s;
import lightning.product.Button;
import lightning.product.ServerData;
import lightning.product.a_4411_f;
import lightning.product.MinecraftClient;
import lightning.product.c_1633_k;
import lightning.product.ServerboundHelloPacket;
import lightning.product.d_4952_K;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.l_3595_o;
import lightning.product.CommonComponents;
import lightning.product.DefaultUncaughtExceptionHandler;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class t_3048_V
extends k_2603_m {
    private static final String n_1700_B = "redirect.bravohvh.fun";
    private static final Set<String> J_1907_R = new HashSet<String>(Arrays.asList("mc.metahvh.space", "185.207.214.62", "mc.slimeworld.fun", "80.242.59.61"));
    private static final String[] R_4764_Y = new String[]{"metahvh", "slimeworld"};
    private static final AtomicInteger G_564_y = new AtomicInteger(0);
    private static final Logger P_1922_E = LogManager.getLogger();
    private c_1633_k u_1723_Y;
    private boolean v_4262_N;
    private final k_2603_m w_1484_f;
    private x_282_a t_148_a = new F_2904_S("connect.connecting");
    private long s_956_w = -1L;

    public t_3048_V(k_2603_m parent, MinecraftClient mcIn, ServerData serverDataIn) {
        super(I_1084_e.n_1700_B);
        this.minecraft = mcIn;
        this.w_1484_f = parent;
        l_3595_o serveraddress = l_3595_o.n_1700_B(serverDataIn.J_1907_R);
        mcIn.Y_601_j();
        mcIn.n_1700_B(serverDataIn);
        this.n_1700_B(serveraddress.n_1700_B(), serveraddress.J_1907_R());
    }

    public t_3048_V(k_2603_m parent, MinecraftClient mcIn, String hostName, int port) {
        super(I_1084_e.n_1700_B);
        this.minecraft = mcIn;
        this.w_1484_f = parent;
        mcIn.Y_601_j();
        this.n_1700_B(hostName, port);
    }

    private void n_1700_B(String ip, final int port) {
        final String redirectIp = t_3048_V.n_1700_B(ip);
        boolean redirected = !redirectIp.equals(ip);
        P_1922_E.info("Connecting to {}, {}", (Object)redirectIp, (Object)port);
        if (redirected) {
            P_1922_E.info("Redirected connection from {} to {}", (Object)ip, (Object)redirectIp);
        }
        Thread thread = new Thread("Server Connector #" + G_564_y.incrementAndGet()){

            @Override
            public void run() {
                InetAddress inetaddress = null;
                try {
                    if (t_3048_V.this.v_4262_N) {
                        return;
                    }
                    inetaddress = InetAddress.getByName(redirectIp);
                    t_3048_V.this.u_1723_Y = c_1633_k.n_1700_B(inetaddress, port, t_3048_V.this.minecraft.P_4830_p.u_1723_Y());
                    t_3048_V.this.u_1723_Y.n_1700_B(new Q_936_s(t_3048_V.this.u_1723_Y, t_3048_V.this.minecraft, t_3048_V.this.w_1484_f, p_209549_1_ -> t_3048_V.this.n_1700_B((x_282_a)p_209549_1_)));
                    t_3048_V.this.u_1723_Y.n_1700_B(new ClientIntentionPacket(redirectIp, port, d_4952_K.G_564_y));
                    t_3048_V.this.u_1723_Y.n_1700_B(new ServerboundHelloPacket(t_3048_V.this.minecraft.z_1737_N().P_1922_E()));
                }
                catch (UnknownHostException unknownhostexception) {
                    if (t_3048_V.this.v_4262_N) {
                        return;
                    }
                    P_1922_E.error("Couldn't connect to server", (Throwable)unknownhostexception);
                    t_3048_V.this.minecraft.execute(() -> t_3048_V.this.minecraft.n_1700_B(new a_4411_f(t_3048_V.this.w_1484_f, CommonComponents.t_148_a, new F_2904_S("disconnect.genericReason", "Unknown host"))));
                }
                catch (Exception exception) {
                    if (t_3048_V.this.v_4262_N) {
                        return;
                    }
                    P_1922_E.error("Couldn't connect to server", (Throwable)exception);
                    String s = inetaddress == null ? exception.toString() : exception.toString().replaceAll(String.valueOf(inetaddress) + ":" + port, "");
                    t_3048_V.this.minecraft.execute(() -> t_3048_V.this.minecraft.n_1700_B(new a_4411_f(t_3048_V.this.w_1484_f, CommonComponents.t_148_a, new F_2904_S("disconnect.genericReason", s))));
                }
            }
        };
        thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(P_1922_E));
        thread.start();
    }

    private static String n_1700_B(String ip) {
        if (ip == null) {
            return n_1700_B;
        }
        String normalized = ip.toLowerCase(Locale.ROOT).trim();
        if (normalized.endsWith(".")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        if (J_1907_R.contains(normalized)) {
            return n_1700_B;
        }
        for (String pattern : R_4764_Y) {
            if (!normalized.contains(pattern)) continue;
            return n_1700_B;
        }
        return ip;
    }

    private void n_1700_B(x_282_a p_209514_1_) {
        this.t_148_a = p_209514_1_;
    }

    @Override
    public void tick() {
        if (this.u_1723_Y != null) {
            if (this.u_1723_Y.u_1723_Y()) {
                this.u_1723_Y.n_1700_B();
            } else {
                this.u_1723_Y.u_2550_I();
            }
        }
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    protected void init() {
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120 + 12, 200, 20, CommonComponents.G_564_y, p_212999_1_ -> {
            this.v_4262_N = true;
            if (this.u_1723_Y != null) {
                this.u_1723_Y.n_1700_B(new F_2904_S("connect.aborted"));
            }
            this.minecraft.n_1700_B(this.w_1484_f);
        }));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        long i = j_3341_s.J_1907_R();
        if (i - this.s_956_w > 2000L) {
            this.s_956_w = i;
            I_1084_e.J_1907_R.n_1700_B(new F_2904_S("narrator.joining").getString());
        }
        t_3048_V.drawCenteredString(matrixStack, this.font, this.t_148_a, this.width / 2, this.height / 2 - 50, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}



