/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.i_601_W;
import lightning.product.q_366_O;
import lightning.product.y_2603_k;

public class y_871_Z
extends X_3546_T {
    public final q_366_O v_4262_N = new q_366_O("\u0421\u043e\u043e\u0442\u043d\u043e\u0448\u0435\u043d\u0438\u0435", "\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u043e\u0435", "4:3", "16:9", "1:1", "16:10", "\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u043e\u0435");
    public final I_686_h w_1484_f = new I_686_h("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435", 1.0f, 0.1f, 5.0f, 0.1f, () -> this.v_4262_N.J_1907_R("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u043e\u0435"));

    public y_871_Z() {
        super("AspectRatio", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f);
    }

    @Y_1740_V
    public void n_1700_B(i_601_W e) {
        float value = switch ((String)this.v_4262_N.J_1907_R()) {
            case "4:3" -> 1.3333334f;
            case "16:9" -> 1.7777778f;
            case "1:1" -> 1.0f;
            case "16:10" -> 1.6f;
            default -> ((Float)this.w_1484_f.J_1907_R()).floatValue();
        };
        e.n_1700_B(value);
    }
}

