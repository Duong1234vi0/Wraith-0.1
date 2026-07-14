/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_4355_q;
import lightning.product.b_4953_N;

public class ZombieAttackGoal
extends b_4953_N {
    private final F_4355_q J_1907_R;
    private int R_4764_Y;

    public ZombieAttackGoal(F_4355_q zombieIn, double speedIn, boolean longMemoryIn) {
        super(zombieIn, speedIn, longMemoryIn);
        this.J_1907_R = zombieIn;
    }

    @Override
    public void R_4764_Y() {
        super.R_4764_Y();
        this.R_4764_Y = 0;
    }

    @Override
    public void G_564_y() {
        super.G_564_y();
        this.J_1907_R.multiplayerClientSuggestionProvider(false);
    }

    @Override
    public void P_1922_E() {
        super.P_1922_E();
        ++this.R_4764_Y;
        if (this.R_4764_Y >= 5 && this.s_956_w() < this.u_2550_I() / 2) {
            this.J_1907_R.multiplayerClientSuggestionProvider(true);
        } else {
            this.J_1907_R.multiplayerClientSuggestionProvider(false);
        }
    }
}


