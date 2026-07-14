/*
 * Decompiled with CFR 0.152.
 */
package mods.cape;

import lightning.product.X_4340_E;
import lightning.product.u_530_F;
import mods.cape.Cape;
import mods.cape.StickSimulation;
import mods.cape.Vector2;
import mods.cape.Vector3;

public interface CapeHolder {
    public StickSimulation getSimulation();

    default public void updateSimulation(X_4340_E abstractClientPlayer, int partCount) {
        StickSimulation simulation = this.getSimulation();
        if (simulation == null) {
            return;
        }
        boolean dirty = simulation.init(partCount);
        if (dirty) {
            simulation.applyMovement(new Vector3(1.0f, 1.0f, 0.0f));
            for (int i = 0; i < 5; ++i) {
                this.simulate(abstractClientPlayer);
            }
        }
    }

    default public void simulate(X_4340_E abstractClientPlayer) {
        StickSimulation simulation = this.getSimulation();
        if (simulation == null || simulation.empty()) {
            return;
        }
        double d = abstractClientPlayer.r_2478_U - abstractClientPlayer.O_3598_v();
        double m = abstractClientPlayer.A_3244_K - abstractClientPlayer.l_2647_k();
        float n = abstractClientPlayer.D_4361_a + abstractClientPlayer.C_1162_e - abstractClientPlayer.D_4361_a;
        double o = u_530_F.n_1700_B(n * ((float)Math.PI / 180));
        double p = -u_530_F.J_1907_R(n * ((float)Math.PI / 180));
        float heightMul = Cape.config.heightMultiplier;
        float straveMul = Cape.config.straveMultiplier;
        if (abstractClientPlayer.z_1737_N()) {
            heightMul *= 2.0f;
        }
        double fallHack = u_530_F.n_1700_B((abstractClientPlayer.A_1038_p - abstractClientPlayer.X_2960_b()) * 10.0, 0.0, 1.0);
        if (abstractClientPlayer.z_1737_N()) {
            simulation.setGravity((float)Cape.config.gravity / 10.0f);
        } else {
            simulation.setGravity(Cape.config.gravity);
        }
        Vector3 gravity = new Vector3(0.0f, -1.0f, 0.0f);
        Vector2 strave = new Vector2((float)(abstractClientPlayer.O_3598_v() - abstractClientPlayer.r_715_M), (float)(abstractClientPlayer.l_2647_k() - abstractClientPlayer.i_1637_u));
        strave.rotateDegrees(-abstractClientPlayer.p_178_J);
        double changeX = d * o + m * p + fallHack + (double)(abstractClientPlayer.Z_875_P() && !simulation.isSneaking() ? 3 : 0);
        double changeY = (abstractClientPlayer.X_2960_b() - abstractClientPlayer.A_1038_p) * (double)heightMul + (double)(abstractClientPlayer.Z_875_P() && !simulation.isSneaking() ? 1 : 0);
        double changeZ = -strave.x * straveMul;
        simulation.setSneaking(abstractClientPlayer.Z_875_P());
        Vector3 change = new Vector3((float)changeX, (float)changeY, (float)changeZ);
        if (abstractClientPlayer.x_612_B()) {
            float rotation = abstractClientPlayer.f_4016_n;
            gravity.rotateDegrees(rotation += 90.0f);
            change.rotateDegrees(rotation);
        }
        simulation.setGravityDirection(gravity);
        simulation.applyMovement(change);
        simulation.simulate();
    }
}

