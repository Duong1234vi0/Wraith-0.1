/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.UUID;
import lightning.product.CommandSource;
import lightning.product.P_3504_Q;
import lightning.product.U_2871_b;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import net.minecraft.server.G_564_y;

public class K_2202_x
implements CommandSource {
    private static final U_2871_b n_1700_B = new U_2871_b("Rcon");
    private final StringBuffer J_1907_R = new StringBuffer();
    private final G_564_y R_4764_Y;

    public K_2202_x(G_564_y serverIn) {
        this.R_4764_Y = serverIn;
    }

    public void n_1700_B() {
        this.J_1907_R.setLength(0);
    }

    public String J_1907_R() {
        return this.J_1907_R.toString();
    }

    public y_2498_m R_4764_Y() {
        e_3591_l serverworld = this.R_4764_Y.x_607_J();
        return new y_2498_m(this, e_2866_D.J_1907_R(serverworld.A_1038_p()), P_3504_Q.n_1700_B, serverworld, 4, "Rcon", n_1700_B, this.R_4764_Y, null);
    }

    @Override
    public void n_1700_B(x_282_a component, UUID senderUUID) {
        this.J_1907_R.append(component.getString());
    }

    @Override
    public boolean O_508_d() {
        return true;
    }

    @Override
    public boolean r_715_M() {
        return true;
    }

    @Override
    public boolean A_1038_p() {
        return this.R_4764_Y.w_1457_N();
    }
}


