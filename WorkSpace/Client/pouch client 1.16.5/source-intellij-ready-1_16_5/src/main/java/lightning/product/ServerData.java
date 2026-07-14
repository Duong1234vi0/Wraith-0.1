/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.SharedConstants;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.x_282_a;

public class ServerData {
    public String n_1700_B;
    public String J_1907_R;
    public x_282_a R_4764_Y;
    public x_282_a G_564_y;
    public long P_1922_E;
    public int u_1723_Y = SharedConstants.n_1700_B().getProtocolVersion();
    public x_282_a v_4262_N = new U_2871_b(SharedConstants.n_1700_B().getName());
    public boolean w_1484_f;
    public List<x_282_a> t_148_a = Collections.emptyList();
    private n_1700_B s_956_w = lightning.product.ServerData$n_1700_B.R_4764_Y;
    @Nullable
    private String u_2550_I;
    private boolean M_588_G;

    public ServerData(String name, String ip, boolean isLan) {
        this.n_1700_B = name;
        this.J_1907_R = ip;
        this.M_588_G = isLan;
    }

    public U_2912_j n_1700_B() {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("name", this.n_1700_B);
        compoundnbt.n_1700_B("ip", this.J_1907_R);
        if (this.u_2550_I != null) {
            compoundnbt.n_1700_B("icon", this.u_2550_I);
        }
        if (this.s_956_w == lightning.product.ServerData$n_1700_B.n_1700_B) {
            compoundnbt.n_1700_B("acceptTextures", true);
        } else if (this.s_956_w == lightning.product.ServerData$n_1700_B.J_1907_R) {
            compoundnbt.n_1700_B("acceptTextures", false);
        }
        return compoundnbt;
    }

    public n_1700_B J_1907_R() {
        return this.s_956_w;
    }

    public void n_1700_B(n_1700_B mode) {
        this.s_956_w = mode;
    }

    public static ServerData n_1700_B(U_2912_j nbtCompound) {
        ServerData serverdata = new ServerData(nbtCompound.M_588_G("name"), nbtCompound.M_588_G("ip"), false);
        if (nbtCompound.R_4764_Y("icon", 8)) {
            serverdata.n_1700_B(nbtCompound.M_588_G("icon"));
        }
        if (nbtCompound.R_4764_Y("acceptTextures", 1)) {
            if (nbtCompound.t_1786_h("acceptTextures")) {
                serverdata.n_1700_B(lightning.product.ServerData$n_1700_B.n_1700_B);
            } else {
                serverdata.n_1700_B(lightning.product.ServerData$n_1700_B.J_1907_R);
            }
        } else {
            serverdata.n_1700_B(lightning.product.ServerData$n_1700_B.R_4764_Y);
        }
        return serverdata;
    }

    @Nullable
    public String R_4764_Y() {
        return this.u_2550_I;
    }

    public void n_1700_B(@Nullable String icon) {
        this.u_2550_I = icon;
    }

    public boolean G_564_y() {
        return this.M_588_G;
    }

    public void n_1700_B(ServerData serverDataIn) {
        this.J_1907_R = serverDataIn.J_1907_R;
        this.n_1700_B = serverDataIn.n_1700_B;
        this.n_1700_B(serverDataIn.J_1907_R());
        this.u_2550_I = serverDataIn.u_2550_I;
        this.M_588_G = serverDataIn.M_588_G;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("enabled");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("disabled");
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("prompt");
        private final x_282_a G_564_y;
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String name) {
            this.G_564_y = new F_2904_S("addServer.resourcePack." + name);
        }

        public x_282_a n_1700_B() {
            return this.G_564_y;
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            P_1922_E = lightning.product.ServerData$n_1700_B.J_1907_R();
        }
    }
}


