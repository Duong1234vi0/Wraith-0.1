/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.concurrent.Immutable
 */
package lightning.product;

import javax.annotation.concurrent.Immutable;
import lightning.product.R_2450_T;
import lightning.product.u_530_F;

@Immutable
public class DifficultyInstance {
    private final R_2450_T n_1700_B;
    private final float J_1907_R;

    public DifficultyInstance(R_2450_T worldDifficulty, long worldTime, long chunkInhabitedTime, float moonPhaseFactor) {
        this.n_1700_B = worldDifficulty;
        this.J_1907_R = this.n_1700_B(worldDifficulty, worldTime, chunkInhabitedTime, moonPhaseFactor);
    }

    public R_2450_T n_1700_B() {
        return this.n_1700_B;
    }

    public float J_1907_R() {
        return this.J_1907_R;
    }

    public boolean n_1700_B(float difficulty) {
        return this.J_1907_R > difficulty;
    }

    public float R_4764_Y() {
        if (this.J_1907_R < 2.0f) {
            return 0.0f;
        }
        return this.J_1907_R > 4.0f ? 1.0f : (this.J_1907_R - 2.0f) / 2.0f;
    }

    private float n_1700_B(R_2450_T difficulty, long worldTime, long chunkInhabitedTime, float moonPhaseFactor) {
        if (difficulty == R_2450_T.n_1700_B) {
            return 0.0f;
        }
        boolean flag = difficulty == R_2450_T.G_564_y;
        float f = 0.75f;
        float f1 = u_530_F.n_1700_B(((float)worldTime + -72000.0f) / 1440000.0f, 0.0f, 1.0f) * 0.25f;
        f += f1;
        float f2 = 0.0f;
        f2 += u_530_F.n_1700_B((float)chunkInhabitedTime / 3600000.0f, 0.0f, 1.0f) * (flag ? 1.0f : 0.75f);
        f2 += u_530_F.n_1700_B(moonPhaseFactor * 0.25f, 0.0f, f1);
        if (difficulty == R_2450_T.J_1907_R) {
            f2 *= 0.5f;
        }
        return (float)difficulty.n_1700_B() * (f += f2);
    }
}


