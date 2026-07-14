/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lightning.product.RegionPingResult;
import lightning.product.j_3341_s;

public class Ping {
    public static List<RegionPingResult> n_1700_B(n_1700_B ... p_224867_0_) {
        for (n_1700_B ping$region : p_224867_0_) {
            Ping.n_1700_B(ping$region.s_956_w);
        }
        ArrayList list = Lists.newArrayList();
        for (n_1700_B ping$region1 : p_224867_0_) {
            list.add(new RegionPingResult(ping$region1.t_148_a, Ping.n_1700_B(ping$region1.s_956_w)));
        }
        list.sort(Comparator.comparingInt(RegionPingResult::n_1700_B));
        return list;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static int n_1700_B(String p_224868_0_) {
        int i = 700;
        long j = 0L;
        Socket socket = null;
        for (int k = 0; k < 5; ++k) {
            try {
                InetSocketAddress socketaddress = new InetSocketAddress(p_224868_0_, 80);
                socket = new Socket();
                long l = Ping.J_1907_R();
                socket.connect(socketaddress, 700);
                j += Ping.J_1907_R() - l;
                Ping.n_1700_B(socket);
                continue;
            }
            catch (Exception exception) {
                j += 700L;
                continue;
            }
            finally {
                Ping.n_1700_B(socket);
            }
        }
        return (int)((double)j / 5.0);
    }

    private static void n_1700_B(Socket p_224866_0_) {
        try {
            if (p_224866_0_ != null) {
                p_224866_0_.close();
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private static long J_1907_R() {
        return j_3341_s.J_1907_R();
    }

    public static List<RegionPingResult> n_1700_B() {
        return Ping.n_1700_B(n_1700_B.values());
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("us-east-1", "ec2.us-east-1.amazonaws.com");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("us-west-2", "ec2.us-west-2.amazonaws.com");
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("us-west-1", "ec2.us-west-1.amazonaws.com");
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B("eu-west-1", "ec2.eu-west-1.amazonaws.com");
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B("ap-southeast-1", "ec2.ap-southeast-1.amazonaws.com");
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B("ap-southeast-2", "ec2.ap-southeast-2.amazonaws.com");
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B("ap-northeast-1", "ec2.ap-northeast-1.amazonaws.com");
        public static final /* enum */ n_1700_B w_1484_f = new n_1700_B("sa-east-1", "ec2.sa-east-1.amazonaws.com");
        private final String t_148_a;
        private final String s_956_w;
        private static final /* synthetic */ n_1700_B[] u_2550_I;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_2550_I.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String p_i51602_3_, String p_i51602_4_) {
            this.t_148_a = p_i51602_3_;
            this.s_956_w = p_i51602_4_;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f};
        }

        static {
            u_2550_I = lightning.product.Ping$n_1700_B.n_1700_B();
        }
    }
}


