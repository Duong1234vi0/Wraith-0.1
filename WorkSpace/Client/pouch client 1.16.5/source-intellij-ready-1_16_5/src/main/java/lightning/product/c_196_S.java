/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;
import lightning.product.DefaultUncaughtExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class c_196_S
extends Thread {
    private static final AtomicInteger n_1700_B = new AtomicInteger(0);
    private static final Logger J_1907_R = LogManager.getLogger();
    private final String R_4764_Y;
    private final DatagramSocket G_564_y;
    private boolean P_1922_E = true;
    private final String u_1723_Y;

    public c_196_S(String p_i1321_1_, String p_i1321_2_) throws IOException {
        super("LanServerPinger #" + n_1700_B.incrementAndGet());
        this.R_4764_Y = p_i1321_1_;
        this.u_1723_Y = p_i1321_2_;
        this.setDaemon(true);
        this.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(J_1907_R));
        this.G_564_y = new DatagramSocket();
    }

    @Override
    public void run() {
        String s = c_196_S.n_1700_B(this.R_4764_Y, this.u_1723_Y);
        byte[] abyte = s.getBytes(StandardCharsets.UTF_8);
        while (!this.isInterrupted() && this.P_1922_E) {
            try {
                InetAddress inetaddress = InetAddress.getByName("224.0.2.60");
                DatagramPacket datagrampacket = new DatagramPacket(abyte, abyte.length, inetaddress, 4445);
                this.G_564_y.send(datagrampacket);
            }
            catch (IOException ioexception) {
                J_1907_R.warn("LanServerPinger: {}", (Object)ioexception.getMessage());
                break;
            }
            try {
                c_196_S.sleep(1500L);
            }
            catch (InterruptedException interruptedException) {}
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        this.P_1922_E = false;
    }

    public static String n_1700_B(String p_77525_0_, String p_77525_1_) {
        return "[MOTD]" + p_77525_0_ + "[/MOTD][AD]" + p_77525_1_ + "[/AD]";
    }

    public static String n_1700_B(String p_77524_0_) {
        int i = p_77524_0_.indexOf("[MOTD]");
        if (i < 0) {
            return "missing no";
        }
        int j = p_77524_0_.indexOf("[/MOTD]", i + "[MOTD]".length());
        return j < i ? "missing no" : p_77524_0_.substring(i + "[MOTD]".length(), j);
    }

    public static String J_1907_R(String p_77523_0_) {
        int i = p_77523_0_.indexOf("[/MOTD]");
        if (i < 0) {
            return null;
        }
        int j = p_77523_0_.indexOf("[/MOTD]", i + "[/MOTD]".length());
        if (j >= 0) {
            return null;
        }
        int k = p_77523_0_.indexOf("[AD]", i + "[/MOTD]".length());
        if (k < 0) {
            return null;
        }
        int l = p_77523_0_.indexOf("[/AD]", k + "[AD]".length());
        return l < k ? null : p_77523_0_.substring(k + "[AD]".length(), l);
    }
}


