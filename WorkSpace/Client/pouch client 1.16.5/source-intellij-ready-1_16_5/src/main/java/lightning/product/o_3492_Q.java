/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.openal.AL10
 */
package lightning.product;

import lightning.product.M_1336_P;
import lightning.product.e_2866_D;
import org.lwjgl.openal.AL10;

public class o_3492_Q {
    private float n_1700_B = 1.0f;
    private e_2866_D J_1907_R = e_2866_D.n_1700_B;

    public void n_1700_B(e_2866_D pos) {
        this.J_1907_R = pos;
        AL10.alListener3f((int)4100, (float)((float)pos.J_1907_R), (float)((float)pos.R_4764_Y), (float)((float)pos.G_564_y));
    }

    public e_2866_D n_1700_B() {
        return this.J_1907_R;
    }

    public void n_1700_B(M_1336_P clientViewVector, M_1336_P viewVectorRaised) {
        AL10.alListenerfv((int)4111, (float[])new float[]{clientViewVector.n_1700_B(), clientViewVector.J_1907_R(), clientViewVector.R_4764_Y(), viewVectorRaised.n_1700_B(), viewVectorRaised.J_1907_R(), viewVectorRaised.R_4764_Y()});
    }

    public void n_1700_B(float gainIn) {
        AL10.alListenerf((int)4106, (float)gainIn);
        this.n_1700_B = gainIn;
    }

    public float J_1907_R() {
        return this.n_1700_B;
    }

    public void R_4764_Y() {
        this.n_1700_B(e_2866_D.n_1700_B);
        this.n_1700_B(M_1336_P.P_1922_E, M_1336_P.G_564_y);
    }
}

