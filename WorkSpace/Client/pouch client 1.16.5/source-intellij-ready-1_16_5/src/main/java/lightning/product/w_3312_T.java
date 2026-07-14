/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.ItemTransforms;
import lightning.product.H_3330_w;
import lightning.product.L_3848_p;
import lightning.product.M_1336_P;
import lightning.product.ItemPhysics;
import lightning.product.S_3826_o;
import lightning.product.Z_1993_T;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.n_1494_c;
import lightning.product.ClientBootstrap;
import lightning.product.o_3091_w;
import lightning.product.q_1613_l;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class w_3312_T
extends Z_2049_e<n_1494_c> {
    private final H_3330_w n_1700_B;
    private final Random v_4262_N = new Random();

    public w_3312_T(w_2040_b renderManagerIn, H_3330_w itemRendererIn) {
        super(renderManagerIn);
        this.n_1700_B = itemRendererIn;
        this.R_4764_Y = 0.15f;
        this.G_564_y = 0.75f;
    }

    private static int n_1700_B(Z_1993_T stack) {
        int count = stack.t_4043_B();
        if (count > 48) {
            return 5;
        }
        if (count > 32) {
            return 4;
        }
        if (count > 16) {
            return 3;
        }
        if (count > 1) {
            return 2;
        }
        return 1;
    }

    @Override
    public void n_1700_B(n_1494_c entity, float entityYaw, float partialTicks, g_221_o matrixStack, o_3091_w buffer, int light) {
        float bob;
        Z_1993_T stack = entity.P_1922_E();
        if (stack.n_1700_B()) {
            return;
        }
        double distSq = this.J_1907_R.J_1907_R(entity);
        if (distSq > 4096.0) {
            this.J_1907_R(entity, entityYaw, partialTicks, matrixStack, buffer, light);
            return;
        }
        matrixStack.n_1700_B();
        this.v_4262_N.setSeed(q_1613_l.n_1700_B(stack.J_1907_R()) + stack.v_4262_N());
        S_3826_o model = this.n_1700_B.n_1700_B(stack, entity.O_508_d, null);
        boolean is3d = model.J_1907_R();
        int count = w_3312_T.n_1700_B(stack);
        ItemTransforms transforms = model.u_1723_Y();
        M_1336_P scale = transforms.n_1700_B((ItemTransforms.J_1907_R)ItemTransforms.J_1907_R.w_1484_f).G_564_y;
        boolean physics = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(ItemPhysics.class).w_1484_f();
        float f = bob = this.u_1723_Y() ? u_530_F.n_1700_B(((float)entity.w_1484_f() + partialTicks) / 10.0f + entity.n_1700_B) * 0.1f + 0.1f : 0.0f;
        if (!physics) {
            matrixStack.n_1700_B(0.0, (double)(bob + 0.25f * scale.J_1907_R()), 0.0);
            matrixStack.n_1700_B(M_1336_P.G_564_y.J_1907_R(entity.n_1700_B(partialTicks)));
        } else {
            float angle = entity.M_1641_O() ? 90.0f : entity.n_1700_B(partialTicks) * 300.0f;
            matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(angle));
        }
        if (!is3d) {
            matrixStack.n_1700_B(0.0, 0.0, (double)(-0.09375f * (float)(count - 1) * 0.5f * scale.R_4764_Y()));
        }
        boolean spread3d = is3d && this.P_1922_E();
        for (int i = 0; i < count; ++i) {
            matrixStack.n_1700_B();
            if (i > 0) {
                float dx = (this.v_4262_N.nextFloat() * 2.0f - 1.0f) * (spread3d ? 0.15f : 0.075f);
                float dy = (this.v_4262_N.nextFloat() * 2.0f - 1.0f) * (spread3d ? 0.15f : 0.075f);
                float dz = spread3d ? (this.v_4262_N.nextFloat() * 2.0f - 1.0f) * 0.15f : 0.0f;
                matrixStack.n_1700_B((double)dx, (double)dy, (double)dz);
            }
            this.n_1700_B.n_1700_B(stack, ItemTransforms.J_1907_R.w_1484_f, false, matrixStack, buffer, light, Z_3224_L.n_1700_B, model);
            matrixStack.J_1907_R();
            if (is3d) continue;
            matrixStack.n_1700_B(0.0, 0.0, (double)(0.09375f * scale.R_4764_Y()));
        }
        matrixStack.J_1907_R();
        super.n_1700_B(entity, entityYaw, partialTicks, matrixStack, buffer, light);
    }

    private void J_1907_R(n_1494_c entity, float entityYaw, float partialTicks, g_221_o matrixStack, o_3091_w buffer, int light) {
        matrixStack.n_1700_B();
        Z_1993_T stack = entity.P_1922_E();
        this.v_4262_N.setSeed(q_1613_l.n_1700_B(stack.J_1907_R()) + stack.v_4262_N());
        S_3826_o model = this.n_1700_B.n_1700_B(stack, entity.O_508_d, null);
        this.n_1700_B.n_1700_B(stack, ItemTransforms.J_1907_R.w_1484_f, false, matrixStack, buffer, light, Z_3224_L.n_1700_B, model);
        matrixStack.J_1907_R();
        super.n_1700_B(entity, entityYaw, partialTicks, matrixStack, buffer, light);
    }

    @Override
    public g_2336_b n_1700_B(n_1494_c entity) {
        return L_3848_p.n_1700_B;
    }

    public boolean P_1922_E() {
        return true;
    }

    public boolean u_1723_Y() {
        return true;
    }
}



