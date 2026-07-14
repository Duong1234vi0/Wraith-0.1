/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import lightning.product.ServerLoginPacketListener;
import lightning.product.Y_2605_X;
import lightning.product.b_2585_i;
import lightning.product.Packet;
import lightning.product.Crypt;

public class ServerboundKeyPacket
implements Packet<ServerLoginPacketListener> {
    private byte[] n_1700_B = new byte[0];
    private byte[] J_1907_R = new byte[0];

    public ServerboundKeyPacket() {
    }

    public ServerboundKeyPacket(SecretKey secret, PublicKey key, byte[] verifyToken) throws Y_2605_X {
        this.n_1700_B = Crypt.n_1700_B(key, secret.getEncoded());
        this.J_1907_R = Crypt.n_1700_B(key, verifyToken);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.n_1700_B();
        this.J_1907_R = buf.n_1700_B();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ServerLoginPacketListener handler) {
        handler.n_1700_B(this);
    }

    public SecretKey n_1700_B(PrivateKey key) throws Y_2605_X {
        return Crypt.n_1700_B(key, this.n_1700_B);
    }

    public byte[] J_1907_R(PrivateKey key) throws Y_2605_X {
        return Crypt.J_1907_R(key, this.J_1907_R);
    }
}


