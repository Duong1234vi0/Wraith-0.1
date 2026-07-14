/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import lightning.product.a_2587_Z;

public class D_3097_e {
    private static final byte[] n_1700_B = new byte[]{0, -1, -1, 0, -2, -2, -2, -2, -3, -3, -3, -3, 18, 52, 86, 120};
    private static final byte J_1907_R = 1;
    private static final byte R_4764_Y = 28;
    private static final byte G_564_y = 5;
    private static final byte P_1922_E = 6;
    private static final byte u_1723_Y = 7;
    private static final byte v_4262_N = 8;
    private static final byte w_1484_f = 9;
    private static final byte t_148_a = 16;
    private static final byte s_956_w = 19;
    private static final byte u_2550_I = 21;
    private static final int M_588_G = 11;
    private static final int[] P_4830_p = new int[]{1400, 1200, 1000, 576};
    private final long h_1847_R = new Random().nextLong();
    private DatagramSocket Q_4569_t;
    private InetAddress M_182_A;
    private int t_1786_h;
    private int multiplayerClientSuggestionProvider = 1400;
    private boolean w_1457_N = false;
    private Consumer<String> Y_601_j;

    public CompletableFuture<a_2587_Z> n_1700_B(String host, int port, int timeoutMs) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return this.J_1907_R(host, port, timeoutMs);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public a_2587_Z J_1907_R(String host, int port, int timeoutMs) throws Exception {
        DatagramSocket pingSocket = new DatagramSocket();
        pingSocket.setSoTimeout(timeoutMs);
        try {
            InetAddress address = InetAddress.getByName(host);
            byte[] pingPacket = this.G_564_y();
            DatagramPacket sendPacket = new DatagramPacket(pingPacket, pingPacket.length, address, port);
            pingSocket.send(sendPacket);
            byte[] receiveBuffer = new byte[2048];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            pingSocket.receive(receivePacket);
            a_2587_Z a_2587_Z2 = this.n_1700_B(receivePacket.getData(), receivePacket.getLength());
            return a_2587_Z2;
        }
        finally {
            if (!pingSocket.isClosed()) {
                pingSocket.close();
            }
        }
    }

    private byte[] G_564_y() {
        ByteBuffer buffer = ByteBuffer.allocate(33);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put((byte)1);
        buffer.putLong(System.currentTimeMillis());
        buffer.put(n_1700_B);
        buffer.putLong(this.h_1847_R);
        return buffer.array();
    }

    private a_2587_Z n_1700_B(byte[] data, int length) throws Exception {
        if (length < 35) {
            throw new Exception("Pong packet too short");
        }
        ByteBuffer buffer = ByteBuffer.wrap(data, 0, length);
        buffer.order(ByteOrder.BIG_ENDIAN);
        byte packetId = buffer.get();
        if (packetId != 28) {
            throw new Exception("Invalid packet ID: " + packetId);
        }
        long pingTime = buffer.getLong();
        long serverGuid = buffer.getLong();
        buffer.position(buffer.position() + 16);
        int stringLength = buffer.getShort() & 0xFFFF;
        if (stringLength <= 0 || stringLength > buffer.remaining()) {
            throw new Exception("Invalid server ID length: " + stringLength);
        }
        byte[] stringBytes = new byte[stringLength];
        buffer.get(stringBytes);
        String serverIdString = new String(stringBytes, StandardCharsets.UTF_8).replace("\u0000", "");
        return this.n_1700_B(serverIdString, serverGuid);
    }

    private a_2587_Z n_1700_B(String serverIdString, long serverGuid) {
        String[] parts = serverIdString.split(";", -1);
        a_2587_Z info = new a_2587_Z();
        info.u_1723_Y(serverIdString);
        info.n_1700_B(serverGuid);
        if (parts.length >= 1) {
            info.n_1700_B(parts[0]);
        }
        if (parts.length >= 2) {
            info.J_1907_R(parts[1]);
        }
        if (parts.length >= 3) {
            info.n_1700_B(this.n_1700_B(parts[2], 0));
        }
        if (parts.length >= 4) {
            info.R_4764_Y(parts[3]);
        }
        if (parts.length >= 5) {
            info.J_1907_R(this.n_1700_B(parts[4], 0));
        }
        if (parts.length >= 6) {
            info.R_4764_Y(this.n_1700_B(parts[5], 0));
        }
        if (parts.length >= 8) {
            info.G_564_y(parts[7]);
        }
        if (parts.length >= 9) {
            info.P_1922_E(parts[8]);
        }
        return info;
    }

    private int n_1700_B(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void n_1700_B(Consumer<String> logger) {
        this.Y_601_j = logger;
    }

    private void n_1700_B(String message) {
        if (this.Y_601_j != null) {
            this.Y_601_j.accept(message);
        }
        System.out.println("[RakNet] " + message);
    }

    public boolean R_4764_Y(String host, int port, int timeoutMs) throws Exception {
        this.Q_4569_t = new DatagramSocket();
        this.Q_4569_t.setSoTimeout(timeoutMs);
        this.M_182_A = InetAddress.getByName(host);
        this.t_1786_h = port;
        try {
            this.n_1700_B("\u041f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u043a " + host + ":" + port + "...");
            this.n_1700_B("\u041e\u0442\u043f\u0440\u0430\u0432\u043a\u0430 Open Connection Request 1...");
            if (!this.P_1922_E()) {
                this.n_1700_B("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430: \u0441\u0435\u0440\u0432\u0435\u0440 \u043d\u0435 \u043e\u0442\u0432\u0435\u0442\u0438\u043b \u043d\u0430 Request 1");
                return false;
            }
            this.n_1700_B("\u00a7a\u041f\u043e\u043b\u0443\u0447\u0435\u043d Open Connection Reply 1 (MTU: " + this.multiplayerClientSuggestionProvider + ")");
            this.n_1700_B("\u041e\u0442\u043f\u0440\u0430\u0432\u043a\u0430 Open Connection Request 2...");
            if (!this.u_1723_Y()) {
                this.n_1700_B("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430: \u0441\u0435\u0440\u0432\u0435\u0440 \u043d\u0435 \u043e\u0442\u0432\u0435\u0442\u0438\u043b \u043d\u0430 Request 2");
                return false;
            }
            this.n_1700_B("\u00a7a\u041f\u043e\u043b\u0443\u0447\u0435\u043d Open Connection Reply 2");
            this.n_1700_B("\u041e\u0442\u043f\u0440\u0430\u0432\u043a\u0430 Connection Request...");
            if (!this.w_1484_f()) {
                this.n_1700_B("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430: Connection Request \u043d\u0435 \u043f\u0440\u0438\u043d\u044f\u0442");
                return false;
            }
            this.n_1700_B("\u00a7a\u041f\u043e\u043b\u0443\u0447\u0435\u043d Connection Request Accepted!");
            this.w_1457_N = true;
            this.n_1700_B("\u00a7a=== RakNet \u0441\u043e\u0435\u0434\u0438\u043d\u0435\u043d\u0438\u0435 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u043e! ===");
            this.n_1700_B("\u00a7e\u041d\u043e \u044d\u0442\u043e \u0442\u043e\u043b\u044c\u043a\u043e \u0442\u0440\u0430\u043d\u0441\u043f\u043e\u0440\u0442\u043d\u044b\u0439 \u0443\u0440\u043e\u0432\u0435\u043d\u044c.");
            this.n_1700_B("\u00a7e\u0414\u043b\u044f \u0438\u0433\u0440\u044b \u043d\u0443\u0436\u0435\u043d Bedrock Login \u0441 Xbox \u0430\u0432\u0442\u043e\u0440\u0438\u0437\u0430\u0446\u0438\u0435\u0439.");
            return true;
        }
        catch (SocketTimeoutException e) {
            this.n_1700_B("\u00a7c\u0422\u0430\u0439\u043c\u0430\u0443\u0442 \u0441\u043e\u0435\u0434\u0438\u043d\u0435\u043d\u0438\u044f");
            return false;
        }
        catch (Exception e) {
            this.n_1700_B("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430: " + e.getMessage());
            throw e;
        }
    }

    private boolean P_1922_E() throws Exception {
        for (int mtu : P_4830_p) {
            byte[] packet = this.n_1700_B(mtu);
            DatagramPacket sendPacket = new DatagramPacket(packet, packet.length, this.M_182_A, this.t_1786_h);
            this.Q_4569_t.send(sendPacket);
            try {
                byte[] receiveBuffer = new byte[2048];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                this.Q_4569_t.receive(receivePacket);
                if (receiveBuffer[0] != 6) continue;
                ByteBuffer buffer = ByteBuffer.wrap(receiveBuffer, 0, receivePacket.getLength());
                buffer.order(ByteOrder.BIG_ENDIAN);
                buffer.get();
                buffer.position(buffer.position() + 16);
                buffer.getLong();
                buffer.get();
                this.multiplayerClientSuggestionProvider = buffer.getShort() & 0xFFFF;
                return true;
            }
            catch (SocketTimeoutException e) {
                // empty catch block
            }
        }
        return false;
    }

    private byte[] n_1700_B(int mtu) {
        ByteBuffer buffer = ByteBuffer.allocate(mtu);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put((byte)5);
        buffer.put(n_1700_B);
        buffer.put((byte)11);
        while (buffer.position() < mtu - 28) {
            buffer.put((byte)0);
        }
        byte[] result = new byte[buffer.position()];
        buffer.flip();
        buffer.get(result);
        return result;
    }

    private boolean u_1723_Y() throws Exception {
        byte[] packet = this.v_4262_N();
        DatagramPacket sendPacket = new DatagramPacket(packet, packet.length, this.M_182_A, this.t_1786_h);
        this.Q_4569_t.send(sendPacket);
        byte[] receiveBuffer = new byte[2048];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        this.Q_4569_t.receive(receivePacket);
        return receiveBuffer[0] == 8;
    }

    private byte[] v_4262_N() {
        byte[] addressBytes;
        ByteBuffer buffer = ByteBuffer.allocate(34);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put((byte)7);
        buffer.put(n_1700_B);
        buffer.put((byte)4);
        for (byte b : addressBytes = this.M_182_A.getAddress()) {
            buffer.put((byte)(~b & 0xFF));
        }
        buffer.putShort((short)this.t_1786_h);
        buffer.putShort((short)this.multiplayerClientSuggestionProvider);
        buffer.putLong(this.h_1847_R);
        byte[] result = new byte[buffer.position()];
        buffer.flip();
        buffer.get(result);
        return result;
    }

    private boolean w_1484_f() throws Exception {
        byte[] packet = this.t_148_a();
        byte[] framedPacket = this.J_1907_R(packet, 0);
        DatagramPacket sendPacket = new DatagramPacket(framedPacket, framedPacket.length, this.M_182_A, this.t_1786_h);
        this.Q_4569_t.send(sendPacket);
        byte[] receiveBuffer = new byte[2048];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        for (int i = 0; i < 10; ++i) {
            try {
                this.Q_4569_t.receive(receivePacket);
                byte[] data = receivePacket.getData();
                if (!this.n_1700_B(data, receivePacket.getLength(), (byte)16)) continue;
                return true;
            }
            catch (SocketTimeoutException e) {
                break;
            }
        }
        return false;
    }

    private byte[] t_148_a() {
        ByteBuffer buffer = ByteBuffer.allocate(18);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put((byte)9);
        buffer.putLong(this.h_1847_R);
        buffer.putLong(System.currentTimeMillis());
        buffer.put((byte)0);
        byte[] result = new byte[buffer.position()];
        buffer.flip();
        buffer.get(result);
        return result;
    }

    private byte[] J_1907_R(byte[] payload, int sequenceNumber) {
        ByteBuffer buffer = ByteBuffer.allocate(payload.length + 10);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put((byte)-124);
        buffer.put((byte)(sequenceNumber & 0xFF));
        buffer.put((byte)(sequenceNumber >> 8 & 0xFF));
        buffer.put((byte)(sequenceNumber >> 16 & 0xFF));
        buffer.put((byte)96);
        buffer.putShort((short)(payload.length * 8));
        buffer.put((byte)0);
        buffer.put((byte)0);
        buffer.put((byte)0);
        buffer.put(payload);
        byte[] result = new byte[buffer.position()];
        buffer.flip();
        buffer.get(result);
        return result;
    }

    private boolean n_1700_B(byte[] data, int length, byte packetId) {
        for (int i = 0; i < length; ++i) {
            if (data[i] != packetId) continue;
            return true;
        }
        return false;
    }

    public boolean n_1700_B() {
        return this.w_1457_N;
    }

    public void J_1907_R() {
        if (this.Q_4569_t != null && this.w_1457_N) {
            try {
                byte[] packet = new byte[]{21};
                byte[] framedPacket = this.J_1907_R(packet, 1);
                DatagramPacket sendPacket = new DatagramPacket(framedPacket, framedPacket.length, this.M_182_A, this.t_1786_h);
                this.Q_4569_t.send(sendPacket);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.w_1457_N = false;
        this.R_4764_Y();
    }

    public void R_4764_Y() {
        if (this.Q_4569_t != null && !this.Q_4569_t.isClosed()) {
            this.Q_4569_t.close();
        }
    }
}


