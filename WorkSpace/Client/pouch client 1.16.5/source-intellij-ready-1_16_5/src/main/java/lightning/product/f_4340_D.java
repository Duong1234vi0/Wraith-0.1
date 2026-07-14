/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.ParticleRenderType;
import lightning.product.M_1336_P;
import lightning.product.TextureSheetParticle;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.e_2866_D;
import lightning.product.h_3572_K;
import lightning.product.k_4690_i;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.u_530_F;

public class f_4340_D
extends TextureSheetParticle {
    private final SpriteSet n_1700_B;

    protected f_4340_D(k_4690_i world, double x, double y, double z, SpriteSet provider) {
        super(world, x, y, z);
        this.n_1700_B = provider;
        this.Y_601_j = 7;
        this.q_2307_F = 0.2f;
        this.A_4115_X = 0.25f;
        this.J_1907_R(provider);
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
        } else {
            this.J_1907_R(this.n_1700_B);
        }
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.R_4764_Y;
    }

    @Override
    public void n_1700_B(D_4792_h buffer, h_3572_K renderInfo, float partialTicks) {
        e_2866_D camera = renderInfo.J_1907_R();
        float x = (float)(u_530_F.G_564_y((double)partialTicks, this.G_564_y, this.v_4262_N) - camera.n_1700_B());
        float y = (float)(u_530_F.G_564_y((double)partialTicks, this.P_1922_E, this.w_1484_f) - camera.J_1907_R());
        float z = (float)(u_530_F.G_564_y((double)partialTicks, this.u_1723_Y, this.t_148_a) - camera.R_4764_Y());
        M_1336_P[] vertices = new M_1336_P[]{new M_1336_P(-1.0f, 0.0f, -1.0f), new M_1336_P(-1.0f, 0.0f, 1.0f), new M_1336_P(1.0f, 0.0f, 1.0f), new M_1336_P(1.0f, 0.0f, -1.0f)};
        float scale = this.J_1907_R(partialTicks);
        for (M_1336_P vertex : vertices) {
            vertex.n_1700_B(scale);
            vertex.R_4764_Y(x, y, z);
        }
        float minU = this.R_4764_Y();
        float maxU = this.G_564_y();
        float minV = this.P_1922_E();
        float maxV = this.u_1723_Y();
        int light = this.n_1700_B(partialTicks);
        buffer.pos(vertices[0].n_1700_B(), vertices[0].J_1907_R(), vertices[0].R_4764_Y()).tex(maxU, maxV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(vertices[1].n_1700_B(), vertices[1].J_1907_R(), vertices[1].R_4764_Y()).tex(maxU, minV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(vertices[2].n_1700_B(), vertices[2].J_1907_R(), vertices[2].R_4764_Y()).tex(minU, minV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(vertices[3].n_1700_B(), vertices[3].J_1907_R(), vertices[3].R_4764_Y()).tex(minU, maxV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet provider) {
            this.n_1700_B = provider;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            if (!(worldIn instanceof k_4690_i)) {
                return null;
            }
            k_4690_i clientWorld = (k_4690_i)worldIn;
            return new f_4340_D(clientWorld, x, y, z, this.n_1700_B);
        }
    }
}


