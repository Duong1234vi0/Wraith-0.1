/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import lightning.product.DragonChargePlayerPhase;
import lightning.product.DragonDeathPhase;
import lightning.product.DragonSittingScanningPhase;
import lightning.product.b_2971_b;
import lightning.product.b_732_O;
import lightning.product.d_218_Y;
import lightning.product.k_608_m;
import lightning.product.DragonLandingApproachPhase;
import lightning.product.DragonSittingFlamingPhase;
import lightning.product.DragonPhaseInstance;
import lightning.product.DragonHoverPhase;
import lightning.product.DragonSittingAttackingPhase;
import lightning.product.DragonLandingPhase;

public class Z_1164_j<T extends DragonPhaseInstance> {
    private static Z_1164_j<?>[] M_588_G = new Z_1164_j[0];
    public static final Z_1164_j<b_732_O> n_1700_B = Z_1164_j.n_1700_B(b_732_O.class, "HoldingPattern");
    public static final Z_1164_j<k_608_m> J_1907_R = Z_1164_j.n_1700_B(k_608_m.class, "StrafePlayer");
    public static final Z_1164_j<DragonLandingApproachPhase> R_4764_Y = Z_1164_j.n_1700_B(DragonLandingApproachPhase.class, "LandingApproach");
    public static final Z_1164_j<DragonLandingPhase> G_564_y = Z_1164_j.n_1700_B(DragonLandingPhase.class, "Landing");
    public static final Z_1164_j<d_218_Y> P_1922_E = Z_1164_j.n_1700_B(d_218_Y.class, "Takeoff");
    public static final Z_1164_j<DragonSittingFlamingPhase> u_1723_Y = Z_1164_j.n_1700_B(DragonSittingFlamingPhase.class, "SittingFlaming");
    public static final Z_1164_j<DragonSittingScanningPhase> v_4262_N = Z_1164_j.n_1700_B(DragonSittingScanningPhase.class, "SittingScanning");
    public static final Z_1164_j<DragonSittingAttackingPhase> w_1484_f = Z_1164_j.n_1700_B(DragonSittingAttackingPhase.class, "SittingAttacking");
    public static final Z_1164_j<DragonChargePlayerPhase> t_148_a = Z_1164_j.n_1700_B(DragonChargePlayerPhase.class, "ChargingPlayer");
    public static final Z_1164_j<DragonDeathPhase> s_956_w = Z_1164_j.n_1700_B(DragonDeathPhase.class, "Dying");
    public static final Z_1164_j<DragonHoverPhase> u_2550_I = Z_1164_j.n_1700_B(DragonHoverPhase.class, "Hover");
    private final Class<? extends DragonPhaseInstance> P_4830_p;
    private final int h_1847_R;
    private final String Q_4569_t;

    private Z_1164_j(int idIn, Class<? extends DragonPhaseInstance> clazzIn, String nameIn) {
        this.h_1847_R = idIn;
        this.P_4830_p = clazzIn;
        this.Q_4569_t = nameIn;
    }

    public DragonPhaseInstance n_1700_B(b_2971_b dragon) {
        try {
            Constructor<DragonPhaseInstance> constructor = this.n_1700_B();
            return constructor.newInstance(dragon);
        }
        catch (Exception exception) {
            throw new Error(exception);
        }
    }

    protected Constructor<? extends DragonPhaseInstance> n_1700_B() throws NoSuchMethodException {
        return this.P_4830_p.getConstructor(b_2971_b.class);
    }

    public int J_1907_R() {
        return this.h_1847_R;
    }

    public String toString() {
        return this.Q_4569_t + " (#" + this.h_1847_R + ")";
    }

    public static Z_1164_j<?> n_1700_B(int idIn) {
        return idIn >= 0 && idIn < M_588_G.length ? M_588_G[idIn] : n_1700_B;
    }

    public static int R_4764_Y() {
        return M_588_G.length;
    }

    private static <T extends DragonPhaseInstance> Z_1164_j<T> n_1700_B(Class<T> phaseIn, String nameIn) {
        Z_1164_j<T> phasetype = new Z_1164_j<T>(M_588_G.length, phaseIn, nameIn);
        M_588_G = Arrays.copyOf(M_588_G, M_588_G.length + 1);
        Z_1164_j.M_588_G[phasetype.J_1907_R()] = phasetype;
        return phasetype;
    }
}


