/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.BlockGetter;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.l_3747_P;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;
import lightning.product.s_1395_c;

public class SolidFaceRenderer
implements n_4915_r.n_1700_B {
    private final MinecraftClient n_1700_B;

    public SolidFaceRenderer(MinecraftClient minecraftIn) {
        this.n_1700_B = minecraftIn;
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        b_4507_u iblockreader = this.n_1700_B.Y_259_p.O_508_d;
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.G_564_y(2.0f);
        c_4037_x.e_4240_b();
        c_4037_x.J_1907_R(false);
        c_1514_x blockpos = new c_1514_x(camX, camY, camZ);
        for (c_1514_x blockpos1 : c_1514_x.getAllInBoxMutable(blockpos.add(-6, -6, -6), blockpos.add(6, 6, 6))) {
            K_4074_S blockstate = iblockreader.getBlockState(blockpos1);
            if (blockstate.n_1700_B(a_3742_W.n_1700_B)) continue;
            s_1395_c voxelshape = blockstate.s_956_w(iblockreader, blockpos1);
            for (I_4817_s axisalignedbb : voxelshape.G_564_y()) {
                I_4817_s axisalignedbb1 = axisalignedbb.offset(blockpos1).grow(0.002).offset(-camX, -camY, -camZ);
                double d0 = axisalignedbb1.minX;
                double d1 = axisalignedbb1.minY;
                double d2 = axisalignedbb1.minZ;
                double d3 = axisalignedbb1.maxX;
                double d4 = axisalignedbb1.maxY;
                double d5 = axisalignedbb1.maxZ;
                float f = 1.0f;
                float f1 = 0.0f;
                float f2 = 0.0f;
                float f3 = 0.5f;
                if (blockstate.G_564_y((BlockGetter)iblockreader, blockpos1, b_257_Y.P_1922_E)) {
                    l_3747_P tessellator = l_3747_P.n_1700_B();
                    D_3318_r bufferbuilder = tessellator.R_4764_Y();
                    bufferbuilder.n_1700_B(5, E_688_b.Y_601_j);
                    bufferbuilder.pos(d0, d1, d2).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder.pos(d0, d1, d5).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder.pos(d0, d4, d2).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder.pos(d0, d4, d5).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    tessellator.J_1907_R();
                }
                if (blockstate.G_564_y((BlockGetter)iblockreader, blockpos1, b_257_Y.G_564_y)) {
                    l_3747_P tessellator1 = l_3747_P.n_1700_B();
                    D_3318_r bufferbuilder1 = tessellator1.R_4764_Y();
                    bufferbuilder1.n_1700_B(5, E_688_b.Y_601_j);
                    bufferbuilder1.pos(d0, d4, d5).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder1.pos(d0, d1, d5).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder1.pos(d3, d4, d5).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder1.pos(d3, d1, d5).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    tessellator1.J_1907_R();
                }
                if (blockstate.G_564_y((BlockGetter)iblockreader, blockpos1, b_257_Y.u_1723_Y)) {
                    l_3747_P tessellator2 = l_3747_P.n_1700_B();
                    D_3318_r bufferbuilder2 = tessellator2.R_4764_Y();
                    bufferbuilder2.n_1700_B(5, E_688_b.Y_601_j);
                    bufferbuilder2.pos(d3, d1, d5).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder2.pos(d3, d1, d2).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder2.pos(d3, d4, d5).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder2.pos(d3, d4, d2).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    tessellator2.J_1907_R();
                }
                if (blockstate.G_564_y((BlockGetter)iblockreader, blockpos1, b_257_Y.R_4764_Y)) {
                    l_3747_P tessellator3 = l_3747_P.n_1700_B();
                    D_3318_r bufferbuilder3 = tessellator3.R_4764_Y();
                    bufferbuilder3.n_1700_B(5, E_688_b.Y_601_j);
                    bufferbuilder3.pos(d3, d4, d2).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder3.pos(d3, d1, d2).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder3.pos(d0, d4, d2).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder3.pos(d0, d1, d2).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    tessellator3.J_1907_R();
                }
                if (blockstate.G_564_y((BlockGetter)iblockreader, blockpos1, b_257_Y.n_1700_B)) {
                    l_3747_P tessellator4 = l_3747_P.n_1700_B();
                    D_3318_r bufferbuilder4 = tessellator4.R_4764_Y();
                    bufferbuilder4.n_1700_B(5, E_688_b.Y_601_j);
                    bufferbuilder4.pos(d0, d1, d2).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder4.pos(d3, d1, d2).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder4.pos(d0, d1, d5).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder4.pos(d3, d1, d5).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    tessellator4.J_1907_R();
                }
                if (!blockstate.G_564_y((BlockGetter)iblockreader, blockpos1, b_257_Y.J_1907_R)) continue;
                l_3747_P tessellator5 = l_3747_P.n_1700_B();
                D_3318_r bufferbuilder5 = tessellator5.R_4764_Y();
                bufferbuilder5.n_1700_B(5, E_688_b.Y_601_j);
                bufferbuilder5.pos(d0, d4, d2).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                bufferbuilder5.pos(d0, d4, d5).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                bufferbuilder5.pos(d3, d4, d2).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                bufferbuilder5.pos(d3, d4, d5).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                tessellator5.J_1907_R();
            }
        }
        c_4037_x.J_1907_R(true);
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
    }
}



