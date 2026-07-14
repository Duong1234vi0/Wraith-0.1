/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lightning.product.D_4792_h;
import lightning.product.N_4263_v;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.n_4915_r;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.s_1395_c;
import lightning.product.z_883_p;

public class CollisionBoxRenderer
implements n_4915_r.n_1700_B {
    private final MinecraftClient n_1700_B;
    private double J_1907_R = Double.MIN_VALUE;
    private List<s_1395_c> R_4764_Y = Collections.emptyList();

    public CollisionBoxRenderer(MinecraftClient minecraftIn) {
        this.n_1700_B = minecraftIn;
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        double d0 = j_3341_s.R_4764_Y();
        if (d0 - this.J_1907_R > 1.0E8) {
            this.J_1907_R = d0;
            N_4263_v entity = this.n_1700_B.s_956_w.M_588_G().v_4262_N();
            this.R_4764_Y = entity.O_508_d.R_4764_Y(entity, entity.i_601_W().grow(6.0), p_239370_0_ -> true).collect(Collectors.toList());
        }
        D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.C_2741_M());
        for (s_1395_c voxelshape : this.R_4764_Y) {
            z_883_p.n_1700_B(matrixStackIn, ivertexbuilder, voxelshape, -camX, -camY, -camZ, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}



