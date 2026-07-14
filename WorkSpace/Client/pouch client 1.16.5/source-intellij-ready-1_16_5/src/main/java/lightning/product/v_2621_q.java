/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.Animal;
import lightning.product.N_4263_v;
import lightning.product.Goal;

public class v_2621_q
extends Goal {
    private final Animal n_1700_B;
    private Animal J_1907_R;
    private final double R_4764_Y;
    private int G_564_y;

    public v_2621_q(Animal animal, double speed) {
        this.n_1700_B = animal;
        this.R_4764_Y = speed;
    }

    @Override
    public boolean n_1700_B() {
        if (this.n_1700_B.x_() >= 0) {
            return false;
        }
        List<?> list = this.n_1700_B.O_508_d.n_1700_B(this.n_1700_B.getClass(), this.n_1700_B.i_601_W().grow(8.0, 4.0, 8.0));
        Animal animalentity = null;
        double d0 = Double.MAX_VALUE;
        for (Animal animalentity1 : list) {
            double d1;
            if (animalentity1.x_() < 0 || (d1 = this.n_1700_B.G_564_y((N_4263_v)animalentity1)) > d0) continue;
            d0 = d1;
            animalentity = animalentity1;
        }
        if (animalentity == null) {
            return false;
        }
        if (d0 < 9.0) {
            return false;
        }
        this.J_1907_R = animalentity;
        return true;
    }

    @Override
    public boolean J_1907_R() {
        if (this.n_1700_B.x_() >= 0) {
            return false;
        }
        if (!this.J_1907_R.RealmsLongRunningMcoTaskScreen()) {
            return false;
        }
        double d0 = this.n_1700_B.G_564_y((N_4263_v)this.J_1907_R);
        return !(d0 < 9.0) && !(d0 > 256.0);
    }

    @Override
    public void R_4764_Y() {
        this.G_564_y = 0;
    }

    @Override
    public void G_564_y() {
        this.J_1907_R = null;
    }

    @Override
    public void P_1922_E() {
        if (--this.G_564_y <= 0) {
            this.G_564_y = 10;
            this.n_1700_B.e_4240_b().n_1700_B((N_4263_v)this.J_1907_R, this.R_4764_Y);
        }
    }
}


