/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lightning.product.M_1336_P;
import lightning.product.Transformation;
import lightning.product.r_4970_d;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;
import lightning.product.ModelState;

public final class S_3779_r
extends Enum<S_3779_r>
implements ModelState {
    public static final /* enum */ S_3779_r n_1700_B = new S_3779_r(0, 0);
    public static final /* enum */ S_3779_r J_1907_R = new S_3779_r(0, 90);
    public static final /* enum */ S_3779_r R_4764_Y = new S_3779_r(0, 180);
    public static final /* enum */ S_3779_r G_564_y = new S_3779_r(0, 270);
    public static final /* enum */ S_3779_r P_1922_E = new S_3779_r(90, 0);
    public static final /* enum */ S_3779_r u_1723_Y = new S_3779_r(90, 90);
    public static final /* enum */ S_3779_r v_4262_N = new S_3779_r(90, 180);
    public static final /* enum */ S_3779_r w_1484_f = new S_3779_r(90, 270);
    public static final /* enum */ S_3779_r t_148_a = new S_3779_r(180, 0);
    public static final /* enum */ S_3779_r s_956_w = new S_3779_r(180, 90);
    public static final /* enum */ S_3779_r u_2550_I = new S_3779_r(180, 180);
    public static final /* enum */ S_3779_r M_588_G = new S_3779_r(180, 270);
    public static final /* enum */ S_3779_r P_4830_p = new S_3779_r(270, 0);
    public static final /* enum */ S_3779_r h_1847_R = new S_3779_r(270, 90);
    public static final /* enum */ S_3779_r Q_4569_t = new S_3779_r(270, 180);
    public static final /* enum */ S_3779_r M_182_A = new S_3779_r(270, 270);
    private static final Map<Integer, S_3779_r> t_1786_h;
    private final Transformation multiplayerClientSuggestionProvider;
    private final r_4970_d w_1457_N;
    private final int Y_601_j;
    private static final /* synthetic */ S_3779_r[] Y_259_p;

    public static S_3779_r[] values() {
        return (S_3779_r[])Y_259_p.clone();
    }

    public static S_3779_r valueOf(String name) {
        return Enum.valueOf(S_3779_r.class, name);
    }

    private static int J_1907_R(int x, int y) {
        return x * 360 + y;
    }

    private S_3779_r(int x, int y) {
        this.Y_601_j = S_3779_r.J_1907_R(x, y);
        w_3785_E quaternion = new w_3785_E(new M_1336_P(0.0f, 1.0f, 0.0f), -y, true);
        quaternion.n_1700_B(new w_3785_E(new M_1336_P(1.0f, 0.0f, 0.0f), -x, true));
        r_4970_d orientation = r_4970_d.n_1700_B;
        for (int i = 0; i < y; i += 90) {
            orientation = orientation.n_1700_B(r_4970_d.Y_259_p);
        }
        for (int j = 0; j < x; j += 90) {
            orientation = orientation.n_1700_B(r_4970_d.w_1457_N);
        }
        this.multiplayerClientSuggestionProvider = new Transformation(null, quaternion, null, null);
        this.w_1457_N = orientation;
    }

    @Override
    public Transformation n_1700_B() {
        return this.multiplayerClientSuggestionProvider;
    }

    public static S_3779_r n_1700_B(int x, int y) {
        return t_1786_h.get(S_3779_r.J_1907_R(u_530_F.J_1907_R(x, 360), u_530_F.J_1907_R(y, 360)));
    }

    private static /* synthetic */ S_3779_r[] R_4764_Y() {
        return new S_3779_r[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t, M_182_A};
    }

    static {
        Y_259_p = S_3779_r.R_4764_Y();
        t_1786_h = Arrays.stream(S_3779_r.values()).collect(Collectors.toMap(rotation -> rotation.Y_601_j, rotation -> rotation));
    }
}


