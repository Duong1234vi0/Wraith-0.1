/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.protocol.version.ProtocolVersion
 */
package lightning.product;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.h_1015_G;
import lightning.product.l_4627_h;
import mods.viaversion.vialoadingbase.ViaLoadingBase;

public class U_3823_u
implements MinecraftAccess {
    private boolean n_1700_B = false;

    @Y_1740_V
    private void n_1700_B(l_4627_h e) {
        this.n_1700_B();
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        if (!this.n_1700_B && c_3005_b.t_4043_B() != null) {
            this.n_1700_B();
        }
    }

    private void n_1700_B() {
        if (c_3005_b.t_4043_B() == null) {
            return;
        }
        String serverIP = U_3823_u.c_3005_b.t_4043_B().J_1907_R.toLowerCase();
        if ((serverIP.contains("mc.lonygrief.me") || serverIP.contains("lonygrief")) && !this.n_1700_B) {
            try {
                ProtocolVersion targetVersion = ProtocolVersion.v1_17_1;
                if (ViaLoadingBase.getInstance().getTargetVersion() != targetVersion) {
                    ViaLoadingBase.getInstance().reload(targetVersion);
                    this.n_1700_B = true;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}


