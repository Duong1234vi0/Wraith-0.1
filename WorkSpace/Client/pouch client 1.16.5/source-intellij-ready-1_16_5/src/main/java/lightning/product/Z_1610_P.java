/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import lightning.product.B_553_u;
import lightning.product.x_3412_u;
import lightning.product.ServerInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Z_1610_P
extends B_553_u {
    private static final Logger G_564_y = LogManager.getLogger();
    private boolean P_1922_E;
    private final Socket u_1723_Y;
    private final byte[] v_4262_N = new byte[1460];
    private final String w_1484_f;
    private final ServerInterface t_148_a;

    Z_1610_P(ServerInterface p_i50687_1_, String p_i50687_2_, Socket p_i50687_3_) {
        super("RCON Client " + String.valueOf(p_i50687_3_.getInetAddress()));
        this.t_148_a = p_i50687_1_;
        this.u_1723_Y = p_i50687_3_;
        try {
            this.u_1723_Y.setSoTimeout(0);
        }
        catch (Exception exception) {
            this.n_1700_B = false;
        }
        this.w_1484_f = p_i50687_2_;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        try {
            block14: while (true) {
                if (!this.n_1700_B) {
                    return;
                }
                BufferedInputStream bufferedinputstream = new BufferedInputStream(this.u_1723_Y.getInputStream());
                int i = bufferedinputstream.read(this.v_4262_N, 0, 1460);
                if (10 > i) break;
                int j = 0;
                int k = x_3412_u.J_1907_R(this.v_4262_N, 0, i);
                if (k != i - 4) {
                    return;
                }
                int l = x_3412_u.J_1907_R(this.v_4262_N, j += 4, i);
                int i1 = x_3412_u.n_1700_B(this.v_4262_N, j += 4);
                j += 4;
                switch (i1) {
                    case 2: {
                        if (this.P_1922_E) {
                            String s1 = x_3412_u.n_1700_B(this.v_4262_N, j, i);
                            try {
                                this.n_1700_B(l, this.t_148_a.n_1700_B(s1));
                            }
                            catch (Exception exception) {
                                this.n_1700_B(l, "Error executing: " + s1 + " (" + exception.getMessage() + ")");
                            }
                            continue block14;
                        }
                        this.G_564_y();
                        continue block14;
                    }
                    case 3: {
                        String s = x_3412_u.n_1700_B(this.v_4262_N, j, i);
                        int j1 = j + s.length();
                        if (!s.isEmpty() && s.equals(this.w_1484_f)) {
                            this.P_1922_E = true;
                            this.n_1700_B(l, 2, "");
                            continue block14;
                        }
                        this.P_1922_E = false;
                        this.G_564_y();
                        continue block14;
                    }
                }
                this.n_1700_B(l, String.format("Unknown request %s", Integer.toHexString(i1)));
            }
            return;
        }
        catch (IOException ioexception) {
            return;
        }
        catch (Exception exception1) {
            G_564_y.error("Exception whilst parsing RCON input", (Throwable)exception1);
            return;
        }
        finally {
            this.P_1922_E();
            G_564_y.info("Thread {} shutting down", (Object)this.J_1907_R);
            this.n_1700_B = false;
        }
    }

    private void n_1700_B(int p_72654_1_, int p_72654_2_, String message) throws IOException {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(1248);
        DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
        byte[] abyte = message.getBytes(StandardCharsets.UTF_8);
        dataoutputstream.writeInt(Integer.reverseBytes(abyte.length + 10));
        dataoutputstream.writeInt(Integer.reverseBytes(p_72654_1_));
        dataoutputstream.writeInt(Integer.reverseBytes(p_72654_2_));
        dataoutputstream.write(abyte);
        dataoutputstream.write(0);
        dataoutputstream.write(0);
        this.u_1723_Y.getOutputStream().write(bytearrayoutputstream.toByteArray());
    }

    private void G_564_y() throws IOException {
        this.n_1700_B(-1, 2, "");
    }

    private void n_1700_B(int p_72655_1_, String p_72655_2_) throws IOException {
        int j;
        int i = p_72655_2_.length();
        do {
            j = 4096 <= i ? 4096 : i;
            this.n_1700_B(p_72655_1_, 0, p_72655_2_.substring(0, j));
        } while (0 != (i = (p_72655_2_ = p_72655_2_.substring(j)).length()));
    }

    @Override
    public void n_1700_B() {
        this.n_1700_B = false;
        this.P_1922_E();
        super.n_1700_B();
    }

    private void P_1922_E() {
        try {
            this.u_1723_Y.close();
        }
        catch (IOException ioexception) {
            G_564_y.warn("Failed to close socket", (Throwable)ioexception);
        }
    }
}


