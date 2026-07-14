/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.exceptions.AuthenticationException
 *  com.mojang.authlib.exceptions.AuthenticationUnavailableException
 *  com.mojang.authlib.exceptions.InsufficientPrivilegesException
 *  com.mojang.authlib.exceptions.InvalidCredentialsException
 *  com.mojang.authlib.minecraft.MinecraftSessionService
 *  io.netty.util.concurrent.Future
 *  io.netty.util.concurrent.GenericFutureListener
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InsufficientPrivilegesException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.math.BigInteger;
import java.security.PublicKey;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import lightning.product.F_2904_S;
import lightning.product.ClientboundHelloPacket;
import lightning.product.M_182_A;
import lightning.product.ClientboundGameProfilePacket;
import lightning.product.O_2332_X;
import lightning.product.ServerboundCustomQueryPacket;
import lightning.product.V_173_d;
import lightning.product.Y_2605_X;
import lightning.product.a_3942_s;
import lightning.product.a_4411_f;
import lightning.product.MinecraftClient;
import lightning.product.c_1633_k;
import lightning.product.ServerboundKeyPacket;
import lightning.product.ClientboundCustomQueryPacket;
import lightning.product.d_4952_K;
import lightning.product.ClientLoginPacketListener;
import lightning.product.RealmsScreen;
import lightning.product.i_4972_c;
import lightning.product.k_2603_m;
import lightning.product.n_633_r;
import lightning.product.CommonComponents;
import lightning.product.t_1786_h;
import lightning.product.Crypt;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Q_4569_t
implements ClientLoginPacketListener {
    public static final Logger n_1700_B = LogManager.getLogger();
    public final MinecraftClient J_1907_R;
    @Nullable
    public final k_2603_m R_4764_Y;
    public final Consumer<x_282_a> G_564_y;
    public final t_1786_h P_1922_E;
    private final c_1633_k w_1484_f;
    public GameProfile u_1723_Y;

    public Q_4569_t(t_1786_h networkManagerIn, MinecraftClient mcIn, @Nullable k_2603_m previousScreen, Consumer<x_282_a> statusMessageConsumerIn) {
        this.P_1922_E = networkManagerIn;
        this.J_1907_R = mcIn;
        this.R_4764_Y = previousScreen;
        this.G_564_y = statusMessageConsumerIn;
        this.w_1484_f = new c_1633_k(a_3942_s.J_1907_R);
    }

    @Override
    public void n_1700_B(ClientboundHelloPacket packetIn) {
        ServerboundKeyPacket cencryptionresponsepacket;
        Cipher cipher1;
        Cipher cipher;
        String s;
        try {
            SecretKey secretkey = Crypt.n_1700_B();
            PublicKey publickey = packetIn.R_4764_Y();
            s = new BigInteger(Crypt.n_1700_B(packetIn.J_1907_R(), publickey, secretkey)).toString(16);
            cipher = Crypt.n_1700_B(2, secretkey);
            cipher1 = Crypt.n_1700_B(1, secretkey);
            cencryptionresponsepacket = new ServerboundKeyPacket(secretkey, publickey, packetIn.G_564_y());
        }
        catch (Y_2605_X var8) {
            throw new IllegalStateException("Protocol error", var8);
        }
        this.G_564_y.accept(new F_2904_S("connect.authorizing"));
        O_2332_X.n_1700_B.submit(() -> {
            x_282_a itextcomponent = this.n_1700_B(s);
            if (itextcomponent != null) {
                if (this.J_1907_R.t_4043_B() == null || !this.J_1907_R.t_4043_B().G_564_y()) {
                    this.P_1922_E.n_1700_B(itextcomponent);
                    return;
                }
                n_1700_B.warn(itextcomponent.getString());
            }
            this.G_564_y.accept(new F_2904_S("connect.encrypting"));
            this.P_1922_E.n_1700_B(cencryptionresponsepacket, (GenericFutureListener<? extends Future<? super Void>>)((GenericFutureListener)p_244776_3_ -> this.P_1922_E.n_1700_B(cipher, cipher1)));
        });
    }

    @Nullable
    private x_282_a n_1700_B(String serverHash) {
        try {
            this.n_1700_B().joinServer(this.J_1907_R.z_1737_N().P_1922_E(), this.J_1907_R.z_1737_N().G_564_y(), serverHash);
            return null;
        }
        catch (AuthenticationUnavailableException authenticationunavailableexception) {
            return new F_2904_S("disconnect.loginFailedInfo", new F_2904_S("disconnect.loginFailedInfo.serversUnavailable"));
        }
        catch (InvalidCredentialsException invalidcredentialsexception) {
            return new F_2904_S("disconnect.loginFailedInfo", new F_2904_S("disconnect.loginFailedInfo.invalidSession"));
        }
        catch (InsufficientPrivilegesException insufficientprivilegesexception) {
            return new F_2904_S("disconnect.loginFailedInfo", new F_2904_S("disconnect.loginFailedInfo.insufficientPrivileges"));
        }
        catch (AuthenticationException authenticationexception) {
            return new F_2904_S("disconnect.loginFailedInfo", authenticationexception.getMessage());
        }
    }

    private MinecraftSessionService n_1700_B() {
        return this.J_1907_R.N_2525_X();
    }

    @Override
    public void n_1700_B(ClientboundGameProfilePacket packetIn) {
        this.G_564_y.accept(new F_2904_S("connect.joining"));
        GameProfile receivedProfile = packetIn.J_1907_R();
        if (receivedProfile == null || receivedProfile.getId() == null || receivedProfile.getName() == null || receivedProfile.getName().isEmpty()) {
            n_1700_B.error("Received invalid GameProfile from server: {}", (Object)receivedProfile);
            if (this.P_1922_E.n_1700_B() != null) {
                receivedProfile = this.P_1922_E.n_1700_B();
                n_1700_B.info("Using local GameProfile: {}", (Object)receivedProfile);
            }
        }
        this.u_1723_Y = receivedProfile;
        this.P_1922_E.n_1700_B(d_4952_K.J_1907_R);
        this.P_1922_E.n_1700_B(new M_182_A(this.J_1907_R, this.R_4764_Y, this.P_1922_E, this.u_1723_Y));
    }

    @Override
    public void onDisconnect(x_282_a reason) {
        if (this.R_4764_Y != null && this.R_4764_Y instanceof RealmsScreen) {
            this.J_1907_R.n_1700_B(new n_633_r(this.R_4764_Y, CommonComponents.t_148_a, reason));
        } else {
            assert (this.R_4764_Y != null);
            this.J_1907_R.n_1700_B(new a_4411_f(this.R_4764_Y, CommonComponents.t_148_a, reason));
        }
    }

    @Override
    public c_1633_k getNetworkManager() {
        return this.w_1484_f;
    }

    @Override
    public t_1786_h getBotNetwork() {
        return this.P_1922_E;
    }

    @Override
    public void n_1700_B(V_173_d packetIn) {
        this.P_1922_E.n_1700_B(packetIn.J_1907_R());
    }

    @Override
    public void n_1700_B(i_4972_c packetIn) {
        if (!this.P_1922_E.P_1922_E()) {
            this.P_1922_E.n_1700_B(packetIn.J_1907_R());
        }
    }

    @Override
    public void n_1700_B(ClientboundCustomQueryPacket packetIn) {
        this.G_564_y.accept(new F_2904_S("connect.negotiating"));
        this.P_1922_E.n_1700_B(new ServerboundCustomQueryPacket(packetIn.J_1907_R(), null));
    }
}



