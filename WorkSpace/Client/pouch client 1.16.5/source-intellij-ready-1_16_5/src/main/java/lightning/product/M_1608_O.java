/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.B_553_u;
import lightning.product.j_3341_s;
import lightning.product.NetworkDataOutputStream;
import lightning.product.x_3412_u;
import lightning.product.ServerInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class M_1608_O
extends B_553_u {
    private static final Logger G_564_y = LogManager.getLogger();
    private long P_1922_E;
    private final int u_1723_Y;
    private final int v_4262_N;
    private final int w_1484_f;
    private final String t_148_a;
    private final String s_956_w;
    private DatagramSocket u_2550_I;
    private final byte[] M_588_G = new byte[1460];
    private String P_4830_p;
    private String h_1847_R;
    private final Map<SocketAddress, n_1700_B> Q_4569_t;
    private final NetworkDataOutputStream M_182_A;
    private long t_1786_h;
    private final ServerInterface multiplayerClientSuggestionProvider;

    private M_1608_O(ServerInterface p_i241890_1_, int p_i241890_2_) {
        super("Query Listener");
        this.multiplayerClientSuggestionProvider = p_i241890_1_;
        this.u_1723_Y = p_i241890_2_;
        this.h_1847_R = p_i241890_1_.J_1907_R();
        this.v_4262_N = p_i241890_1_.R_4764_Y();
        this.t_148_a = p_i241890_1_.G_564_y();
        this.w_1484_f = p_i241890_1_.v_4262_N();
        this.s_956_w = p_i241890_1_.t_148_a();
        this.t_1786_h = 0L;
        this.P_4830_p = "0.0.0.0";
        if (!this.h_1847_R.isEmpty() && !this.P_4830_p.equals(this.h_1847_R)) {
            this.P_4830_p = this.h_1847_R;
        } else {
            this.h_1847_R = "0.0.0.0";
            try {
                InetAddress inetaddress = InetAddress.getLocalHost();
                this.P_4830_p = inetaddress.getHostAddress();
            }
            catch (UnknownHostException unknownhostexception) {
                G_564_y.warn("Unable to determine local host IP, please set server-ip in server.properties", (Throwable)unknownhostexception);
            }
        }
        this.M_182_A = new NetworkDataOutputStream(1460);
        this.Q_4569_t = Maps.newHashMap();
    }

    @Nullable
    public static M_1608_O n_1700_B(ServerInterface p_242129_0_) {
        int i = p_242129_0_.n_1700_B().w_1457_N;
        if (0 < i && 65535 >= i) {
            M_1608_O querythread = new M_1608_O(p_242129_0_, i);
            return !querythread.J_1907_R() ? null : querythread;
        }
        G_564_y.warn("Invalid query port {} found in server.properties (queries disabled)", (Object)i);
        return null;
    }

    private void n_1700_B(byte[] data, DatagramPacket requestPacket) throws IOException {
        this.u_2550_I.send(new DatagramPacket(data, data.length, requestPacket.getSocketAddress()));
    }

    private boolean n_1700_B(DatagramPacket requestPacket) throws IOException {
        byte[] abyte = requestPacket.getData();
        int i = requestPacket.getLength();
        SocketAddress socketaddress = requestPacket.getSocketAddress();
        G_564_y.debug("Packet len {} [{}]", (Object)i, (Object)socketaddress);
        if (3 <= i && -2 == abyte[0] && -3 == abyte[1]) {
            G_564_y.debug("Packet '{}' [{}]", (Object)x_3412_u.n_1700_B(abyte[2]), (Object)socketaddress);
            switch (abyte[2]) {
                case 0: {
                    if (!this.R_4764_Y(requestPacket).booleanValue()) {
                        G_564_y.debug("Invalid challenge [{}]", (Object)socketaddress);
                        return false;
                    }
                    if (15 == i) {
                        this.n_1700_B(this.J_1907_R(requestPacket), requestPacket);
                        G_564_y.debug("Rules [{}]", (Object)socketaddress);
                    } else {
                        NetworkDataOutputStream rconoutputstream = new NetworkDataOutputStream(1460);
                        rconoutputstream.n_1700_B(0);
                        rconoutputstream.n_1700_B(this.n_1700_B(requestPacket.getSocketAddress()));
                        rconoutputstream.n_1700_B(this.t_148_a);
                        rconoutputstream.n_1700_B("SMP");
                        rconoutputstream.n_1700_B(this.s_956_w);
                        rconoutputstream.n_1700_B(Integer.toString(this.multiplayerClientSuggestionProvider.u_1723_Y()));
                        rconoutputstream.n_1700_B(Integer.toString(this.w_1484_f));
                        rconoutputstream.n_1700_B((short)this.v_4262_N);
                        rconoutputstream.n_1700_B(this.P_4830_p);
                        this.n_1700_B(rconoutputstream.n_1700_B(), requestPacket);
                        G_564_y.debug("Status [{}]", (Object)socketaddress);
                    }
                }
                default: {
                    return true;
                }
                case 9: 
            }
            this.G_564_y(requestPacket);
            G_564_y.debug("Challenge [{}]", (Object)socketaddress);
            return true;
        }
        G_564_y.debug("Invalid packet [{}]", (Object)socketaddress);
        return false;
    }

    private byte[] J_1907_R(DatagramPacket requestPacket) throws IOException {
        String[] astring;
        long i = j_3341_s.J_1907_R();
        if (i < this.t_1786_h + 5000L) {
            byte[] abyte = this.M_182_A.n_1700_B();
            byte[] abyte1 = this.n_1700_B(requestPacket.getSocketAddress());
            abyte[1] = abyte1[0];
            abyte[2] = abyte1[1];
            abyte[3] = abyte1[2];
            abyte[4] = abyte1[3];
            return abyte;
        }
        this.t_1786_h = i;
        this.M_182_A.J_1907_R();
        this.M_182_A.n_1700_B(0);
        this.M_182_A.n_1700_B(this.n_1700_B(requestPacket.getSocketAddress()));
        this.M_182_A.n_1700_B("splitnum");
        this.M_182_A.n_1700_B(128);
        this.M_182_A.n_1700_B(0);
        this.M_182_A.n_1700_B("hostname");
        this.M_182_A.n_1700_B(this.t_148_a);
        this.M_182_A.n_1700_B("gametype");
        this.M_182_A.n_1700_B("SMP");
        this.M_182_A.n_1700_B("game_id");
        this.M_182_A.n_1700_B("MINECRAFT");
        this.M_182_A.n_1700_B("version");
        this.M_182_A.n_1700_B(this.multiplayerClientSuggestionProvider.P_1922_E());
        this.M_182_A.n_1700_B("plugins");
        this.M_182_A.n_1700_B(this.multiplayerClientSuggestionProvider.s_956_w());
        this.M_182_A.n_1700_B("map");
        this.M_182_A.n_1700_B(this.s_956_w);
        this.M_182_A.n_1700_B("numplayers");
        this.M_182_A.n_1700_B("" + this.multiplayerClientSuggestionProvider.u_1723_Y());
        this.M_182_A.n_1700_B("maxplayers");
        this.M_182_A.n_1700_B("" + this.w_1484_f);
        this.M_182_A.n_1700_B("hostport");
        this.M_182_A.n_1700_B("" + this.v_4262_N);
        this.M_182_A.n_1700_B("hostip");
        this.M_182_A.n_1700_B(this.P_4830_p);
        this.M_182_A.n_1700_B(0);
        this.M_182_A.n_1700_B(1);
        this.M_182_A.n_1700_B("player_");
        this.M_182_A.n_1700_B(0);
        for (String s : astring = this.multiplayerClientSuggestionProvider.w_1484_f()) {
            this.M_182_A.n_1700_B(s);
        }
        this.M_182_A.n_1700_B(0);
        return this.M_182_A.n_1700_B();
    }

    private byte[] n_1700_B(SocketAddress address) {
        return this.Q_4569_t.get(address).R_4764_Y();
    }

    private Boolean R_4764_Y(DatagramPacket requestPacket) {
        SocketAddress socketaddress = requestPacket.getSocketAddress();
        if (!this.Q_4569_t.containsKey(socketaddress)) {
            return false;
        }
        byte[] abyte = requestPacket.getData();
        return this.Q_4569_t.get(socketaddress).n_1700_B() == x_3412_u.R_4764_Y(abyte, 7, requestPacket.getLength());
    }

    private void G_564_y(DatagramPacket requestPacket) throws IOException {
        n_1700_B querythread$auth = new n_1700_B(requestPacket);
        this.Q_4569_t.put(requestPacket.getSocketAddress(), querythread$auth);
        this.n_1700_B(querythread$auth.J_1907_R(), requestPacket);
    }

    private void G_564_y() {
        long i;
        if (this.n_1700_B && (i = j_3341_s.J_1907_R()) >= this.P_1922_E + 30000L) {
            this.P_1922_E = i;
            this.Q_4569_t.values().removeIf(p_232650_2_ -> p_232650_2_.n_1700_B(i));
        }
    }

    @Override
    public void run() {
        G_564_y.info("Query running on {}:{}", (Object)this.h_1847_R, (Object)this.u_1723_Y);
        this.P_1922_E = j_3341_s.J_1907_R();
        DatagramPacket datagrampacket = new DatagramPacket(this.M_588_G, this.M_588_G.length);
        try {
            while (this.n_1700_B) {
                try {
                    this.u_2550_I.receive(datagrampacket);
                    this.G_564_y();
                    this.n_1700_B(datagrampacket);
                }
                catch (SocketTimeoutException sockettimeoutexception) {
                    this.G_564_y();
                }
                catch (PortUnreachableException sockettimeoutexception) {
                }
                catch (IOException ioexception) {
                    this.n_1700_B(ioexception);
                }
            }
        }
        finally {
            G_564_y.debug("closeSocket: {}:{}", (Object)this.h_1847_R, (Object)this.u_1723_Y);
            this.u_2550_I.close();
        }
    }

    @Override
    public boolean J_1907_R() {
        if (this.n_1700_B) {
            return true;
        }
        return !this.P_1922_E() ? false : super.J_1907_R();
    }

    private void n_1700_B(Exception exception) {
        if (this.n_1700_B) {
            G_564_y.warn("Unexpected exception", (Throwable)exception);
            if (!this.P_1922_E()) {
                G_564_y.error("Failed to recover from exception, shutting down!");
                this.n_1700_B = false;
            }
        }
    }

    private boolean P_1922_E() {
        try {
            this.u_2550_I = new DatagramSocket(this.u_1723_Y, InetAddress.getByName(this.h_1847_R));
            this.u_2550_I.setSoTimeout(500);
            return true;
        }
        catch (Exception exception) {
            G_564_y.warn("Unable to initialise query system on {}:{}", (Object)this.h_1847_R, (Object)this.u_1723_Y, (Object)exception);
            return false;
        }
    }

    static class n_1700_B {
        private final long n_1700_B = new Date().getTime();
        private final int J_1907_R;
        private final byte[] R_4764_Y;
        private final byte[] G_564_y;
        private final String P_1922_E;

        public n_1700_B(DatagramPacket p_i231427_1_) {
            byte[] abyte = p_i231427_1_.getData();
            this.R_4764_Y = new byte[4];
            this.R_4764_Y[0] = abyte[3];
            this.R_4764_Y[1] = abyte[4];
            this.R_4764_Y[2] = abyte[5];
            this.R_4764_Y[3] = abyte[6];
            this.P_1922_E = new String(this.R_4764_Y, StandardCharsets.UTF_8);
            this.J_1907_R = new Random().nextInt(0x1000000);
            this.G_564_y = String.format("\t%s%d\u0000", this.P_1922_E, this.J_1907_R).getBytes(StandardCharsets.UTF_8);
        }

        public Boolean n_1700_B(long currentTime) {
            return this.n_1700_B < currentTime;
        }

        public int n_1700_B() {
            return this.J_1907_R;
        }

        public byte[] J_1907_R() {
            return this.G_564_y;
        }

        public byte[] R_4764_Y() {
            return this.R_4764_Y;
        }
    }
}


