/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class E_3520_U
implements Packet<ClientGamePacketListener> {
    private String n_1700_B;
    private String J_1907_R;

    public E_3520_U() {
    }

    public E_3520_U(String urlIn, String hashIn) {
        this.n_1700_B = urlIn;
        this.J_1907_R = hashIn;
        if (hashIn.length() > 40) {
            throw new IllegalArgumentException("Hash is too long (max 40, was " + hashIn.length() + ")");
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_1922_E(Short.MAX_VALUE);
        this.J_1907_R = buf.P_1922_E(40);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public String J_1907_R() {
        return this.n_1700_B;
    }

    public String R_4764_Y() {
        return this.J_1907_R;
    }
}


