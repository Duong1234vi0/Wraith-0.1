/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Function;
import lightning.product.MovementTutorialStepInstance;
import lightning.product.F_1864_A;
import lightning.product.W_1671_y;
import lightning.product.PunchTreeTutorialStepInstance;
import lightning.product.j_744_k_0;
import lightning.product.OpenInventoryTutorialStep;
import lightning.product.TutorialStepInstance;
import lightning.product.x_3584_Y;

public final class t_4467_k
extends Enum<t_4467_k> {
    public static final /* enum */ t_4467_k n_1700_B = new t_4467_k("movement", MovementTutorialStepInstance::new);
    public static final /* enum */ t_4467_k J_1907_R = new t_4467_k("find_tree", j_744_k_0::new);
    public static final /* enum */ t_4467_k R_4764_Y = new t_4467_k("punch_tree", PunchTreeTutorialStepInstance::new);
    public static final /* enum */ t_4467_k G_564_y = new t_4467_k("open_inventory", OpenInventoryTutorialStep::new);
    public static final /* enum */ t_4467_k P_1922_E = new t_4467_k("craft_planks", x_3584_Y::new);
    public static final /* enum */ t_4467_k u_1723_Y = new t_4467_k("none", F_1864_A::new);
    private final String v_4262_N;
    private final Function<W_1671_y, ? extends TutorialStepInstance> w_1484_f;
    private static final /* synthetic */ t_4467_k[] t_148_a;

    public static t_4467_k[] values() {
        return (t_4467_k[])t_148_a.clone();
    }

    public static t_4467_k valueOf(String name) {
        return Enum.valueOf(t_4467_k.class, name);
    }

    private <T extends TutorialStepInstance> t_4467_k(String nameIn, Function<W_1671_y, T> constructor) {
        this.v_4262_N = nameIn;
        this.w_1484_f = constructor;
    }

    public TutorialStepInstance n_1700_B(W_1671_y tutorial) {
        return this.w_1484_f.apply(tutorial);
    }

    public String n_1700_B() {
        return this.v_4262_N;
    }

    public static t_4467_k n_1700_B(String name) {
        for (t_4467_k tutorialsteps : t_4467_k.values()) {
            if (!tutorialsteps.v_4262_N.equals(name)) continue;
            return tutorialsteps;
        }
        return u_1723_Y;
    }

    private static /* synthetic */ t_4467_k[] J_1907_R() {
        return new t_4467_k[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
    }

    static {
        t_148_a = t_4467_k.J_1907_R();
    }
}


