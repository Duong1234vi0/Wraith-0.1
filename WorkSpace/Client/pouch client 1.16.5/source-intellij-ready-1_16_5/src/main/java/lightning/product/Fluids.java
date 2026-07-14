/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.LavaFluid;
import lightning.product.U_4243_e;
import lightning.product.V_3137_a;
import lightning.product.FluidState;
import lightning.product.EmptyFluid;
import lightning.product.WaterFluid;
import lightning.product.Fluid;

public class Fluids {
    public static final Fluid n_1700_B = Fluids.n_1700_B("empty", new EmptyFluid());
    public static final U_4243_e J_1907_R = Fluids.n_1700_B("flowing_water", new WaterFluid.n_1700_B());
    public static final U_4243_e R_4764_Y = Fluids.n_1700_B("water", new WaterFluid.J_1907_R());
    public static final U_4243_e G_564_y = Fluids.n_1700_B("flowing_lava", new LavaFluid.n_1700_B());
    public static final U_4243_e P_1922_E = Fluids.n_1700_B("lava", new LavaFluid.J_1907_R());

    private static <T extends Fluid> T n_1700_B(String key, T fluid) {
        return (T)V_3137_a.n_1700_B(V_3137_a.G_624_v, key, fluid);
    }

    static {
        for (Fluid fluid : V_3137_a.G_624_v) {
            for (FluidState fluidstate : fluid.v_4262_N().n_1700_B()) {
                Fluid.R_4764_Y.J_1907_R(fluidstate);
            }
        }
    }
}


