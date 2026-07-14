/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.FluidTags;
import lightning.product.BlockGetter;
import lightning.product.I_4817_s;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;

public class WaterDebugRenderer
implements n_4915_r.n_1700_B {
    private final MinecraftClient n_1700_B;

    public WaterDebugRenderer(MinecraftClient minecraftIn) {
        this.n_1700_B = minecraftIn;
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        c_1514_x blockpos = this.n_1700_B.Y_259_p.b_2312_j();
        b_4507_u iworldreader = this.n_1700_B.Y_259_p.O_508_d;
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.G_564_y(0.0f, 1.0f, 0.0f, 0.75f);
        c_4037_x.e_4240_b();
        c_4037_x.G_564_y(6.0f);
        for (c_1514_x blockpos1 : c_1514_x.getAllInBoxMutable(blockpos.add(-10, -10, -10), blockpos.add(10, 10, 10))) {
            FluidState fluidstate = iworldreader.getFluidState(blockpos1);
            if (!fluidstate.n_1700_B(FluidTags.J_1907_R)) continue;
            double d0 = (float)blockpos1.getY() + fluidstate.n_1700_B((BlockGetter)iworldreader, blockpos1);
            n_4915_r.n_1700_B(new I_4817_s((float)blockpos1.getX() + 0.01f, (float)blockpos1.getY() + 0.01f, (float)blockpos1.getZ() + 0.01f, (float)blockpos1.getX() + 0.99f, d0, (float)blockpos1.getZ() + 0.99f).offset(-camX, -camY, -camZ), 1.0f, 1.0f, 1.0f, 0.2f);
        }
        for (c_1514_x blockpos2 : c_1514_x.getAllInBoxMutable(blockpos.add(-10, -10, -10), blockpos.add(10, 10, 10))) {
            FluidState fluidstate1 = iworldreader.getFluidState(blockpos2);
            if (!fluidstate1.n_1700_B(FluidTags.J_1907_R)) continue;
            n_4915_r.n_1700_B(String.valueOf(fluidstate1.P_1922_E()), (double)blockpos2.getX() + 0.5, (double)((float)blockpos2.getY() + fluidstate1.n_1700_B((BlockGetter)iworldreader, blockpos2)), (double)blockpos2.getZ() + 0.5, -16777216);
        }
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
    }
}



