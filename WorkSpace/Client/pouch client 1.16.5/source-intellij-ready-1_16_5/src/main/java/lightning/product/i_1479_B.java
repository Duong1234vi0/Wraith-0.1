/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class i_1479_B {
    protected Socket n_1700_B;
    protected PrintWriter J_1907_R;
    protected BufferedReader R_4764_Y;

    public i_1479_B(String host, int port) throws IOException {
        this.n_1700_B = new Socket(host, port);
        this.J_1907_R = new PrintWriter(this.n_1700_B.getOutputStream(), true);
        this.R_4764_Y = new BufferedReader(new InputStreamReader(this.n_1700_B.getInputStream()));
    }

    public void n_1700_B(String message) {
        if (this.J_1907_R != null) {
            this.J_1907_R.println(message);
            this.J_1907_R.flush();
        }
    }

    public String n_1700_B() throws IOException {
        return this.R_4764_Y.readLine();
    }

    public void J_1907_R() throws IOException {
        if (this.J_1907_R != null) {
            this.J_1907_R.close();
        }
        if (this.R_4764_Y != null) {
            this.R_4764_Y.close();
        }
        if (this.n_1700_B != null) {
            this.n_1700_B.close();
        }
    }

    public boolean R_4764_Y() {
        return this.n_1700_B == null || this.n_1700_B.isClosed();
    }

    public Socket G_564_y() {
        return this.n_1700_B;
    }
}

