/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class U_3005_m {
    private ServerSocket n_1700_B;
    private Socket J_1907_R;
    private PrintWriter R_4764_Y;
    private BufferedReader G_564_y;

    public U_3005_m(int port) throws IOException {
        this.n_1700_B = new ServerSocket(port);
    }

    public void n_1700_B() throws IOException {
        this.J_1907_R = this.n_1700_B.accept();
        this.R_4764_Y = new PrintWriter(this.J_1907_R.getOutputStream(), true);
        this.G_564_y = new BufferedReader(new InputStreamReader(this.J_1907_R.getInputStream()));
    }

    public PrintWriter J_1907_R() {
        return this.R_4764_Y;
    }

    public void n_1700_B(String message) {
        if (this.R_4764_Y != null) {
            this.R_4764_Y.println(message);
            this.R_4764_Y.flush();
        }
    }

    public String R_4764_Y() throws IOException {
        return this.G_564_y.readLine();
    }

    public void G_564_y() throws IOException {
        if (this.R_4764_Y != null) {
            this.R_4764_Y.close();
        }
        if (this.G_564_y != null) {
            this.G_564_y.close();
        }
        if (this.J_1907_R != null) {
            this.J_1907_R.close();
        }
    }

    public void P_1922_E() throws IOException {
        if (this.n_1700_B != null) {
            this.n_1700_B.close();
        }
    }

    public boolean u_1723_Y() {
        return this.n_1700_B == null || this.n_1700_B.isClosed();
    }

    public Socket v_4262_N() {
        return this.J_1907_R;
    }
}

