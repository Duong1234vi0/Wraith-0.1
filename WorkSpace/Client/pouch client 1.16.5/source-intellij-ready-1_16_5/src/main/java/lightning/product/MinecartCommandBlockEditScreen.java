/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ServerboundSetCommandMinecartPacket;
import lightning.product.d_742_e;
import lightning.product.r_2555_q;
import lightning.product.z_2326_J;

public class MinecartCommandBlockEditScreen
extends r_2555_q {
    private final d_742_e v_4262_N;

    public MinecartCommandBlockEditScreen(d_742_e p_i46595_1_) {
        this.v_4262_N = p_i46595_1_;
    }

    @Override
    public d_742_e n_1700_B() {
        return this.v_4262_N;
    }

    @Override
    int J_1907_R() {
        return 150;
    }

    @Override
    protected void init() {
        super.init();
        this.u_1723_Y = this.n_1700_B().s_956_w();
        this.R_4764_Y();
        this.n_1700_B.setText(this.n_1700_B().w_1484_f());
    }

    @Override
    protected void n_1700_B(d_742_e commandBlockLogicIn) {
        if (commandBlockLogicIn instanceof z_2326_J.n_1700_B) {
            z_2326_J.n_1700_B commandblockminecartentity$minecartcommandlogic = (z_2326_J.n_1700_B)commandBlockLogicIn;
            this.minecraft.k_2293_S().n_1700_B(new ServerboundSetCommandMinecartPacket(commandblockminecartentity$minecartcommandlogic.G_564_y().j_276_v(), this.n_1700_B.getText(), commandBlockLogicIn.s_956_w()));
        }
    }
}


