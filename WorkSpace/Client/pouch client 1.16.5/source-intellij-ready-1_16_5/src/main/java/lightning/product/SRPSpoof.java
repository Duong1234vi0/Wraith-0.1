/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_3520_U;
import lightning.product.ServerboundResourcePackPacket;
import lightning.product.Q_2753_H;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.ModuleCategory;

public class SRPSpoof
extends Module {
    public SRPSpoof() {
        super("SRPSpoof", ModuleCategory.P_1922_E);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (e.G_564_y() instanceof E_3520_U && e.J_1907_R()) {
            SRPSpoof.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new ServerboundResourcePackPacket(ServerboundResourcePackPacket.n_1700_B.G_564_y));
            SRPSpoof.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new ServerboundResourcePackPacket(ServerboundResourcePackPacket.n_1700_B.n_1700_B));
            e.n_1700_B(true);
        }
    }
}



