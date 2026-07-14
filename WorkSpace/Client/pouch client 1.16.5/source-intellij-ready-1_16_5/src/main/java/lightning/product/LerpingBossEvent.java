/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BossEvent;
import lightning.product.j_3341_s;
import lightning.product.m_1761_s;
import lightning.product.u_530_F;

public class LerpingBossEvent
extends BossEvent {
    protected float n_1700_B;
    protected long J_1907_R;

    public LerpingBossEvent(m_1761_s packetIn) {
        super(packetIn.J_1907_R(), packetIn.G_564_y(), packetIn.u_1723_Y(), packetIn.v_4262_N());
        this.n_1700_B = packetIn.P_1922_E();
        this.G_564_y = packetIn.P_1922_E();
        this.J_1907_R = j_3341_s.J_1907_R();
        this.n_1700_B(packetIn.w_1484_f());
        this.J_1907_R(packetIn.t_148_a());
        this.R_4764_Y(packetIn.s_956_w());
    }

    @Override
    public void n_1700_B(float percentIn) {
        this.G_564_y = this.n_1700_B();
        this.n_1700_B = percentIn;
        this.J_1907_R = j_3341_s.J_1907_R();
    }

    @Override
    public float n_1700_B() {
        long i = j_3341_s.J_1907_R() - this.J_1907_R;
        float f = u_530_F.n_1700_B((float)i / 100.0f, 0.0f, 1.0f);
        return u_530_F.v_4262_N(f, this.G_564_y, this.n_1700_B);
    }

    public void n_1700_B(m_1761_s packetIn) {
        switch (packetIn.R_4764_Y()) {
            case G_564_y: {
                this.n_1700_B(packetIn.G_564_y());
                break;
            }
            case R_4764_Y: {
                this.n_1700_B(packetIn.P_1922_E());
                break;
            }
            case P_1922_E: {
                this.n_1700_B(packetIn.u_1723_Y());
                this.n_1700_B(packetIn.v_4262_N());
                break;
            }
            case u_1723_Y: {
                this.n_1700_B(packetIn.w_1484_f());
                this.J_1907_R(packetIn.t_148_a());
            }
        }
    }
}


