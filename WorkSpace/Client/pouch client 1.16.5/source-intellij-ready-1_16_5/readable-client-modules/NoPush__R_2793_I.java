/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  java.lang.MatchException
 */
package lightning.product;

import lightning.product.I_685_r;
import lightning.product.N_4463_r;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.p_1977_n;
import lightning.product.y_2603_k;

public class R_2793_I
extends X_3546_T {
    public N_4463_r v_4262_N = new N_4463_r("\u0422\u0438\u043f", new p_1977_n("\u0418\u0433\u0440\u043e\u043a\u0438", true), new p_1977_n("\u0411\u043b\u043e\u043a\u0438", true), new p_1977_n("\u0412\u043e\u0434\u0430", true), new p_1977_n("\u0423\u0434\u043e\u0447\u043a\u0438", true), new p_1977_n("\u041b\u043e\u0434\u043a\u0438", false), new p_1977_n("\u0412\u0430\u0433\u043e\u043d\u0435\u0442\u043a\u0438", false), new p_1977_n("\u041f\u0443\u0437\u044b\u0440\u044c\u043a\u043e\u0432\u044b\u0435 \u043a\u043e\u043b\u043e\u043d\u043d\u044b", false));

    public R_2793_I() {
        super("NoPush", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(I_685_r e) {
        boolean cancel;
        switch (e.n_1700_B) {
            default: {
                throw new MatchException(null, null);
            }
            case n_1700_B: {
                boolean bl = this.v_4262_N.J_1907_R("\u0411\u043b\u043e\u043a\u0438");
                break;
            }
            case J_1907_R: {
                boolean bl = this.v_4262_N.J_1907_R("\u0412\u043e\u0434\u0430");
                break;
            }
            case R_4764_Y: {
                boolean bl = this.v_4262_N.J_1907_R("\u0418\u0433\u0440\u043e\u043a\u0438");
                break;
            }
            case G_564_y: {
                boolean bl = this.v_4262_N.J_1907_R("\u0423\u0434\u043e\u0447\u043a\u0438");
                break;
            }
            case P_1922_E: {
                boolean bl = this.v_4262_N.J_1907_R("\u041b\u043e\u0434\u043a\u0438");
                break;
            }
            case u_1723_Y: {
                boolean bl = this.v_4262_N.J_1907_R("\u0412\u0430\u0433\u043e\u043d\u0435\u0442\u043a\u0438");
                break;
            }
            case v_4262_N: {
                boolean bl = cancel = this.v_4262_N.J_1907_R("\u041f\u0443\u0437\u044b\u0440\u044c\u043a\u043e\u0432\u044b\u0435 \u043a\u043e\u043b\u043e\u043d\u043d\u044b").booleanValue();
            }
        }
        if (cancel) {
            e.n_1700_B(true);
        }
    }
}

