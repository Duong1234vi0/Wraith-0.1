/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.B_553_u;
import lightning.product.DedicatedServerProperties;
import lightning.product.Z_1610_P;
import lightning.product.ServerInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RconThread
extends B_553_u {
    private static final Logger G_564_y = LogManager.getLogger();
    private final ServerSocket P_1922_E;
    private final String u_1723_Y;
    private final List<Z_1610_P> v_4262_N = Lists.newArrayList();
    private final ServerInterface w_1484_f;

    private RconThread(ServerInterface p_i241891_1_, ServerSocket p_i241891_2_, String p_i241891_3_) {
        super("RCON Listener");
        this.w_1484_f = p_i241891_1_;
        this.P_1922_E = p_i241891_2_;
        this.u_1723_Y = p_i241891_3_;
    }

    private void G_564_y() {
        this.v_4262_N.removeIf(p_232654_0_ -> !p_232654_0_.R_4764_Y());
    }

    @Override
    public void run() {
        try {
            while (this.n_1700_B) {
                try {
                    Socket socket = this.P_1922_E.accept();
                    Z_1610_P clientthread = new Z_1610_P(this.w_1484_f, this.u_1723_Y, socket);
                    clientthread.J_1907_R();
                    this.v_4262_N.add(clientthread);
                    this.G_564_y();
                }
                catch (SocketTimeoutException sockettimeoutexception) {
                    this.G_564_y();
                }
                catch (IOException ioexception) {
                    if (!this.n_1700_B) continue;
                    G_564_y.info("IO exception: ", (Throwable)ioexception);
                }
            }
        }
        finally {
            this.n_1700_B(this.P_1922_E);
        }
    }

    @Nullable
    public static RconThread n_1700_B(ServerInterface p_242130_0_) {
        int i;
        DedicatedServerProperties serverproperties = p_242130_0_.n_1700_B();
        String s = p_242130_0_.J_1907_R();
        if (s.isEmpty()) {
            s = "0.0.0.0";
        }
        if (0 < (i = serverproperties.Y_259_p) && 65535 >= i) {
            String s1 = serverproperties.Q_2552_b;
            if (s1.isEmpty()) {
                G_564_y.warn("No rcon password set in server.properties, rcon disabled!");
                return null;
            }
            try {
                ServerSocket serversocket = new ServerSocket(i, 0, InetAddress.getByName(s));
                serversocket.setSoTimeout(500);
                RconThread mainthread = new RconThread(p_242130_0_, serversocket, s1);
                if (!mainthread.J_1907_R()) {
                    return null;
                }
                G_564_y.info("RCON running on {}:{}", (Object)s, (Object)i);
                return mainthread;
            }
            catch (IOException ioexception) {
                G_564_y.warn("Unable to initialise RCON on {}:{}", (Object)s, (Object)i, (Object)ioexception);
                return null;
            }
        }
        G_564_y.warn("Invalid rcon port {} found in server.properties, rcon disabled!", (Object)i);
        return null;
    }

    @Override
    public void n_1700_B() {
        this.n_1700_B = false;
        this.n_1700_B(this.P_1922_E);
        super.n_1700_B();
        for (Z_1610_P clientthread : this.v_4262_N) {
            if (!clientthread.R_4764_Y()) continue;
            clientthread.n_1700_B();
        }
        this.v_4262_N.clear();
    }

    private void n_1700_B(ServerSocket p_232655_1_) {
        G_564_y.debug("closeSocket: {}", (Object)p_232655_1_);
        try {
            p_232655_1_.close();
        }
        catch (IOException ioexception) {
            G_564_y.warn("Failed to close socket", (Throwable)ioexception);
        }
    }
}


