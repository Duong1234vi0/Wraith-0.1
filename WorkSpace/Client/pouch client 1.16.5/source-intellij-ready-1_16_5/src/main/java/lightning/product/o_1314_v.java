/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.exceptions.AuthenticationUnavailableException
 *  io.netty.util.concurrent.Future
 *  io.netty.util.concurrent.GenericFutureListener
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.Validate
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.ServerLoginPacketListener;
import lightning.product.ClientboundHelloPacket;
import lightning.product.ClientboundGameProfilePacket;
import lightning.product.ServerboundCustomQueryPacket;
import lightning.product.V_173_d;
import lightning.product.Y_2605_X;
import lightning.product.a_3913_L;
import lightning.product.c_1633_k;
import lightning.product.ServerboundKeyPacket;
import lightning.product.ServerboundHelloPacket;
import lightning.product.i_4972_c;
import lightning.product.DefaultUncaughtExceptionHandler;
import lightning.product.t_1786_h;
import lightning.product.Crypt;
import lightning.product.x_282_a;
import net.minecraft.server.G_564_y;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class o_1314_v
implements ServerLoginPacketListener {
    private static final AtomicInteger J_1907_R = new AtomicInteger(0);
    private static final Logger R_4764_Y = LogManager.getLogger();
    private static final Random G_564_y = new Random();
    private final byte[] P_1922_E = new byte[4];
    private final G_564_y u_1723_Y;
    public final c_1633_k n_1700_B;
    private n_1700_B v_4262_N = lightning.product.o_1314_v$n_1700_B.n_1700_B;
    private int w_1484_f;
    private GameProfile t_148_a;
    private final String s_956_w = "";
    private SecretKey u_2550_I;
    private B_4088_l M_588_G;

    public o_1314_v(G_564_y serverIn, c_1633_k networkManagerIn) {
        this.u_1723_Y = serverIn;
        this.n_1700_B = networkManagerIn;
        G_564_y.nextBytes(this.P_1922_E);
    }

    public void n_1700_B() {
        B_4088_l serverplayerentity;
        if (this.v_4262_N == lightning.product.o_1314_v$n_1700_B.P_1922_E) {
            this.J_1907_R();
        } else if (this.v_4262_N == lightning.product.o_1314_v$n_1700_B.u_1723_Y && (serverplayerentity = this.u_1723_Y.p_178_J().n_1700_B(this.t_148_a.getId())) == null) {
            this.v_4262_N = lightning.product.o_1314_v$n_1700_B.P_1922_E;
            this.u_1723_Y.p_178_J().n_1700_B(this.n_1700_B, this.M_588_G);
            this.M_588_G = null;
        }
        if (this.w_1484_f++ == 600) {
            this.n_1700_B(new F_2904_S("multiplayer.disconnect.slow_login"));
        }
    }

    @Override
    public c_1633_k getNetworkManager() {
        return this.n_1700_B;
    }

    @Override
    public t_1786_h getBotNetwork() {
        return null;
    }

    public void n_1700_B(x_282_a reason) {
        try {
            R_4764_Y.info("Disconnecting {}: {}", (Object)this.R_4764_Y(), (Object)reason.getString());
            this.n_1700_B.n_1700_B(new V_173_d(reason));
            this.n_1700_B.n_1700_B(reason);
        }
        catch (Exception exception) {
            R_4764_Y.error("Error whilst disconnecting player", (Throwable)exception);
        }
    }

    public void J_1907_R() {
        x_282_a itextcomponent;
        if (!this.t_148_a.isComplete()) {
            this.t_148_a = this.n_1700_B(this.t_148_a);
        }
        if ((itextcomponent = this.u_1723_Y.p_178_J().n_1700_B(this.n_1700_B.R_4764_Y(), this.t_148_a)) != null) {
            this.n_1700_B(itextcomponent);
        } else {
            this.v_4262_N = lightning.product.o_1314_v$n_1700_B.v_4262_N;
            if (this.u_1723_Y.RealmsServerPing() >= 0 && !this.n_1700_B.G_564_y()) {
                this.n_1700_B.n_1700_B(new i_4972_c(this.u_1723_Y.RealmsServerPing()), (GenericFutureListener<? extends Future<? super Void>>)((GenericFutureListener)p_210149_1_ -> this.n_1700_B.n_1700_B(this.u_1723_Y.RealmsServerPing())));
            }
            this.n_1700_B.n_1700_B(new ClientboundGameProfilePacket(this.t_148_a));
            B_4088_l serverplayerentity = this.u_1723_Y.p_178_J().n_1700_B(this.t_148_a.getId());
            if (serverplayerentity != null) {
                this.v_4262_N = lightning.product.o_1314_v$n_1700_B.u_1723_Y;
                this.M_588_G = this.u_1723_Y.p_178_J().P_1922_E(this.t_148_a);
            } else {
                this.u_1723_Y.p_178_J().n_1700_B(this.n_1700_B, this.u_1723_Y.p_178_J().P_1922_E(this.t_148_a));
            }
        }
    }

    @Override
    public void onDisconnect(x_282_a reason) {
        R_4764_Y.info("{} lost connection: {}", (Object)this.R_4764_Y(), (Object)reason.getString());
    }

    public String R_4764_Y() {
        return this.t_148_a != null ? String.valueOf(this.t_148_a) + " (" + String.valueOf(this.n_1700_B.R_4764_Y()) + ")" : String.valueOf(this.n_1700_B.R_4764_Y());
    }

    @Override
    public void n_1700_B(ServerboundHelloPacket packetIn) {
        Validate.validState((this.v_4262_N == lightning.product.o_1314_v$n_1700_B.n_1700_B ? 1 : 0) != 0, (String)"Unexpected hello packet", (Object[])new Object[0]);
        this.t_148_a = packetIn.J_1907_R();
        if (this.u_1723_Y.Z_976_R() && !this.n_1700_B.G_564_y()) {
            this.v_4262_N = lightning.product.o_1314_v$n_1700_B.J_1907_R;
            this.n_1700_B.n_1700_B(new ClientboundHelloPacket("", this.u_1723_Y.v_4276_D().getPublic().getEncoded(), this.P_1922_E));
        } else {
            this.v_4262_N = lightning.product.o_1314_v$n_1700_B.P_1922_E;
        }
    }

    @Override
    public void n_1700_B(ServerboundKeyPacket packetIn) {
        String s;
        Validate.validState((this.v_4262_N == lightning.product.o_1314_v$n_1700_B.J_1907_R ? 1 : 0) != 0, (String)"Unexpected key packet", (Object[])new Object[0]);
        PrivateKey privatekey = this.u_1723_Y.v_4276_D().getPrivate();
        try {
            if (!Arrays.equals(this.P_1922_E, packetIn.J_1907_R(privatekey))) {
                throw new IllegalStateException("Protocol error");
            }
            this.u_2550_I = packetIn.n_1700_B(privatekey);
            Cipher cipher = Crypt.n_1700_B(2, this.u_2550_I);
            Cipher cipher1 = Crypt.n_1700_B(1, this.u_2550_I);
            s = new BigInteger(Crypt.n_1700_B("", this.u_1723_Y.v_4276_D().getPublic(), this.u_2550_I)).toString(16);
            this.v_4262_N = lightning.product.o_1314_v$n_1700_B.R_4764_Y;
            this.n_1700_B.n_1700_B(cipher, cipher1);
        }
        catch (Y_2605_X cryptexception) {
            throw new IllegalStateException("Protocol error", cryptexception);
        }
        Thread thread = new Thread("User Authenticator #" + J_1907_R.incrementAndGet()){

            @Override
            public void run() {
                GameProfile gameprofile = o_1314_v.this.t_148_a;
                try {
                    o_1314_v.this.t_148_a = o_1314_v.this.u_1723_Y.V_1446_Y().hasJoinedServer(new GameProfile((UUID)null, gameprofile.getName()), s, this.n_1700_B());
                    if (o_1314_v.this.t_148_a != null) {
                        R_4764_Y.info("UUID of player {} is {}", (Object)o_1314_v.this.t_148_a.getName(), (Object)o_1314_v.this.t_148_a.getId());
                        o_1314_v.this.v_4262_N = lightning.product.o_1314_v$n_1700_B.P_1922_E;
                    } else if (o_1314_v.this.u_1723_Y.T_2506_i()) {
                        R_4764_Y.warn("Failed to verify username but will let them in anyway!");
                        o_1314_v.this.t_148_a = o_1314_v.this.n_1700_B(gameprofile);
                        o_1314_v.this.v_4262_N = lightning.product.o_1314_v$n_1700_B.P_1922_E;
                    } else {
                        o_1314_v.this.n_1700_B(new F_2904_S("multiplayer.disconnect.unverified_username"));
                        R_4764_Y.error("Username '{}' tried to join with an invalid session", (Object)gameprofile.getName());
                    }
                }
                catch (AuthenticationUnavailableException authenticationunavailableexception) {
                    if (o_1314_v.this.u_1723_Y.T_2506_i()) {
                        R_4764_Y.warn("Authentication servers are down but will let them in anyway!");
                        o_1314_v.this.t_148_a = o_1314_v.this.n_1700_B(gameprofile);
                        o_1314_v.this.v_4262_N = lightning.product.o_1314_v$n_1700_B.P_1922_E;
                    }
                    o_1314_v.this.n_1700_B(new F_2904_S("multiplayer.disconnect.authservers_down"));
                    R_4764_Y.error("Couldn't verify username because servers are unavailable");
                }
            }

            @Nullable
            private InetAddress n_1700_B() {
                SocketAddress socketaddress = o_1314_v.this.n_1700_B.R_4764_Y();
                return o_1314_v.this.u_1723_Y.H_1990_U() && socketaddress instanceof InetSocketAddress ? ((InetSocketAddress)socketaddress).getAddress() : null;
            }
        };
        thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(R_4764_Y));
        thread.start();
    }

    @Override
    public void n_1700_B(ServerboundCustomQueryPacket p_209526_1_) {
        this.n_1700_B(new F_2904_S("multiplayer.disconnect.unexpected_query_response"));
    }

    protected GameProfile n_1700_B(GameProfile original) {
        UUID uuid = a_3913_L.u_1723_Y(original.getName());
        return new GameProfile(uuid, original.getName());
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B();
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] w_1484_f;

        public static n_1700_B[] values() {
            return (n_1700_B[])w_1484_f.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            w_1484_f = lightning.product.o_1314_v$n_1700_B.n_1700_B();
        }
    }
}


