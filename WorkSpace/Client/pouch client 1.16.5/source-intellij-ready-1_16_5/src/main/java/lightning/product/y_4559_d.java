/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lightning.product.c_196_S;
import lightning.product.DefaultUncaughtExceptionHandler;
import lightning.product.LanServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class y_4559_d {
    private static final AtomicInteger n_1700_B = new AtomicInteger(0);
    private static final Logger J_1907_R = LogManager.getLogger();

    public static class J_1907_R {
        private final List<LanServer> n_1700_B = Lists.newArrayList();
        private boolean J_1907_R;

        public synchronized boolean n_1700_B() {
            return this.J_1907_R;
        }

        public synchronized void J_1907_R() {
            this.J_1907_R = false;
        }

        public synchronized List<LanServer> R_4764_Y() {
            return Collections.unmodifiableList(this.n_1700_B);
        }

        public synchronized void n_1700_B(String pingResponse, InetAddress ipAddress) {
            String s = c_196_S.n_1700_B(pingResponse);
            Object s1 = c_196_S.J_1907_R(pingResponse);
            if (s1 != null) {
                s1 = ipAddress.getHostAddress() + ":" + (String)s1;
                boolean flag = false;
                for (LanServer lanserverinfo : this.n_1700_B) {
                    if (!lanserverinfo.J_1907_R().equals(s1)) continue;
                    lanserverinfo.R_4764_Y();
                    flag = true;
                    break;
                }
                if (!flag) {
                    this.n_1700_B.add(new LanServer(s, (String)s1));
                    this.J_1907_R = true;
                }
            }
        }
    }

    public static class n_1700_B
    extends Thread {
        private final J_1907_R n_1700_B;
        private final InetAddress J_1907_R;
        private final MulticastSocket R_4764_Y;

        public n_1700_B(J_1907_R list) throws IOException {
            super("LanServerDetector #" + n_1700_B.incrementAndGet());
            this.n_1700_B = list;
            this.setDaemon(true);
            this.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(J_1907_R));
            this.R_4764_Y = new MulticastSocket(4445);
            this.J_1907_R = InetAddress.getByName("224.0.2.60");
            this.R_4764_Y.setSoTimeout(5000);
            this.R_4764_Y.joinGroup(this.J_1907_R);
        }

        @Override
        public void run() {
            byte[] abyte = new byte[1024];
            while (!this.isInterrupted()) {
                DatagramPacket datagrampacket = new DatagramPacket(abyte, abyte.length);
                try {
                    this.R_4764_Y.receive(datagrampacket);
                }
                catch (SocketTimeoutException sockettimeoutexception) {
                    continue;
                }
                catch (IOException ioexception1) {
                    J_1907_R.error("Couldn't ping server", (Throwable)ioexception1);
                    break;
                }
                String s = new String(datagrampacket.getData(), datagrampacket.getOffset(), datagrampacket.getLength(), StandardCharsets.UTF_8);
                J_1907_R.debug("{}: {}", (Object)datagrampacket.getAddress(), (Object)s);
                this.n_1700_B.n_1700_B(s, datagrampacket.getAddress());
            }
            try {
                this.R_4764_Y.leaveGroup(this.J_1907_R);
            }
            catch (IOException iOException) {
                // empty catch block
            }
            this.R_4764_Y.close();
        }
    }
}


