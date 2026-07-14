/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;
import lightning.product.U_2912_j;
import lightning.product.ServerData;
import lightning.product.MinecraftClient;
import lightning.product.j_3341_s;
import lightning.product.q_2896_o;
import lightning.product.r_1827_u;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class m_3545_A {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final MinecraftClient J_1907_R;
    private final List<ServerData> R_4764_Y = Lists.newArrayList();

    public m_3545_A(MinecraftClient mcIn) {
        this.J_1907_R = mcIn;
        this.n_1700_B();
    }

    public void n_1700_B() {
        try {
            this.R_4764_Y.clear();
            U_2912_j compoundnbt = r_1827_u.J_1907_R(new File(this.J_1907_R.M_182_A, "servers.dat"));
            if (compoundnbt == null) {
                return;
            }
            q_2896_o listnbt = compoundnbt.G_564_y("servers", 10);
            for (int i = 0; i < listnbt.size(); ++i) {
                this.R_4764_Y.add(ServerData.n_1700_B(listnbt.n_1700_B(i)));
            }
        }
        catch (Exception exception) {
            n_1700_B.error("Couldn't load server list", (Throwable)exception);
        }
    }

    public void J_1907_R() {
        try {
            q_2896_o listnbt = new q_2896_o();
            for (ServerData serverdata : this.R_4764_Y) {
                listnbt.add(serverdata.n_1700_B());
            }
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("servers", listnbt);
            File file3 = File.createTempFile("servers", ".dat", this.J_1907_R.M_182_A);
            r_1827_u.J_1907_R(compoundnbt, file3);
            File file1 = new File(this.J_1907_R.M_182_A, "servers.dat_old");
            File file2 = new File(this.J_1907_R.M_182_A, "servers.dat");
            j_3341_s.n_1700_B(file2, file3, file1);
        }
        catch (Exception exception) {
            n_1700_B.error("Couldn't save server list", (Throwable)exception);
        }
    }

    public ServerData n_1700_B(int index) {
        return this.R_4764_Y.get(index);
    }

    public void n_1700_B(ServerData p_217506_1_) {
        this.R_4764_Y.remove(p_217506_1_);
    }

    public void J_1907_R(ServerData server) {
        this.R_4764_Y.add(server);
    }

    public int R_4764_Y() {
        return this.R_4764_Y.size();
    }

    public void n_1700_B(int pos1, int pos2) {
        if (pos1 < 0 || pos1 >= this.R_4764_Y.size() || pos2 < 0 || pos2 >= this.R_4764_Y.size()) {
            return;
        }
        ServerData serverdata = this.n_1700_B(pos1);
        this.R_4764_Y.set(pos1, this.n_1700_B(pos2));
        this.R_4764_Y.set(pos2, serverdata);
        this.J_1907_R();
    }

    public void n_1700_B(int index, ServerData server) {
        this.R_4764_Y.set(index, server);
    }

    public static void R_4764_Y(ServerData server) {
        m_3545_A serverlist = new m_3545_A(MinecraftClient.A_4115_X());
        serverlist.n_1700_B();
        for (int i = 0; i < serverlist.R_4764_Y(); ++i) {
            ServerData serverdata = serverlist.n_1700_B(i);
            if (!serverdata.n_1700_B.equals(server.n_1700_B) || !serverdata.J_1907_R.equals(server.J_1907_R)) continue;
            serverlist.n_1700_B(i, server);
            break;
        }
        serverlist.J_1907_R();
    }
}



