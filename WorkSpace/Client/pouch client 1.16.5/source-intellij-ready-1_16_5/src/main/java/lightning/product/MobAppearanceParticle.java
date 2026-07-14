/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.ParticleRenderType;
import lightning.product.M_1336_P;
import lightning.product.Z_3224_L;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_3457_g;
import lightning.product.g_221_o;
import lightning.product.h_3572_K;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.GuardianModel;
import lightning.product.u_530_F;
import lightning.product.v_3569_v;
import lightning.product.ElderGuardianRenderer;

public class MobAppearanceParticle
extends c_3457_g {
    private final v_3569_v n_1700_B = new GuardianModel();
    private final o_2576_A J_1907_R = o_2576_A.w_1484_f(ElderGuardianRenderer.n_1700_B);

    private MobAppearanceParticle(b_4507_u world, double x, double y, double z) {
        super(world, x, y, z);
        this.Y_259_p = 0.0f;
        this.Y_601_j = 30;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.P_1922_E;
    }

    @Override
    public void n_1700_B(D_4792_h buffer, h_3572_K renderInfo, float partialTicks) {
        float f = ((float)this.w_1457_N + partialTicks) / (float)this.Y_601_j;
        float f1 = 0.05f + 0.5f * u_530_F.n_1700_B(f * (float)Math.PI);
        g_221_o matrixstack = new g_221_o();
        matrixstack.n_1700_B(renderInfo.u_1723_Y());
        matrixstack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(150.0f * f - 60.0f));
        matrixstack.n_1700_B(-1.0f, -1.0f, 1.0f);
        matrixstack.n_1700_B(0.0, (double)-1.101f, 1.5);
        o_3091_w.n_1700_B irendertypebuffer$impl = MinecraftClient.A_4115_X().j_1564_a().J_1907_R();
        D_4792_h ivertexbuilder = irendertypebuffer$impl.getBuffer(this.J_1907_R);
        this.n_1700_B.render(matrixstack, ivertexbuilder, 0xF000F0, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, f1);
        irendertypebuffer$impl.J_1907_R();
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new MobAppearanceParticle(worldIn, x, y, z);
        }
    }
}



