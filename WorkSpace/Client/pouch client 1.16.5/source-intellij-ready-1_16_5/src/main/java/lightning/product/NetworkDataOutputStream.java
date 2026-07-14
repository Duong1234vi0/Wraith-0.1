/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class NetworkDataOutputStream {
    private final ByteArrayOutputStream n_1700_B;
    private final DataOutputStream J_1907_R;

    public NetworkDataOutputStream(int size) {
        this.n_1700_B = new ByteArrayOutputStream(size);
        this.J_1907_R = new DataOutputStream(this.n_1700_B);
    }

    public void n_1700_B(byte[] data) throws IOException {
        this.J_1907_R.write(data, 0, data.length);
    }

    public void n_1700_B(String data) throws IOException {
        this.J_1907_R.writeBytes(data);
        this.J_1907_R.write(0);
    }

    public void n_1700_B(int data) throws IOException {
        this.J_1907_R.write(data);
    }

    public void n_1700_B(short data) throws IOException {
        this.J_1907_R.writeShort(Short.reverseBytes(data));
    }

    public byte[] n_1700_B() {
        return this.n_1700_B.toByteArray();
    }

    public void J_1907_R() {
        this.n_1700_B.reset();
    }
}


