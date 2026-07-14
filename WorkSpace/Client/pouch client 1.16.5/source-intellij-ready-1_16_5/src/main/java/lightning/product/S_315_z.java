/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import lightning.product.FluidTags;
import lightning.product.D_4792_h;
import lightning.product.ParticleRenderType;
import lightning.product.BiomeColors;
import lightning.product.M_1336_P;
import lightning.product.TextureSheetParticle;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.e_2866_D;
import lightning.product.h_3572_K;
import lightning.product.k_4690_i;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.u_530_F;

public class S_315_z
extends TextureSheetParticle {
    protected final SpriteSet n_1700_B;
    private final float J_1907_R;
    private final float Y_1740_V;

    protected S_315_z(k_4690_i world, double x, double y, double z, float width, float height, SpriteSet provider) {
        super(world, x, y, z);
        this.n_1700_B = provider;
        this.J_1907_R = width;
        this.Y_1740_V = height;
        this.Y_601_j = 18;
        this.Y_259_p = 0.0f;
        this.J_1907_R(provider);
        Color color = new Color(BiomeColors.R_4764_Y(world, new c_1514_x(x, y, z)));
        this.Q_2552_b = (float)color.getRed() / 255.0f;
        this.C_2741_M = (float)color.getGreen() / 255.0f;
        this.k_2293_S = (float)color.getBlue() / 255.0f;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.R_4764_Y;
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
        this.J_1907_R(this.n_1700_B);
        if (!this.R_4764_Y.getFluidState(new c_1514_x(this.v_4262_N, this.w_1484_f, this.t_148_a)).n_1700_B(FluidTags.J_1907_R)) {
            this.s_956_w();
        }
    }

    @Override
    public void n_1700_B(D_4792_h buffer, h_3572_K renderInfo, float partialTicks) {
        e_2866_D camera = renderInfo.J_1907_R();
        float x = (float)(u_530_F.G_564_y((double)partialTicks, this.G_564_y, this.v_4262_N) - camera.n_1700_B());
        float y = (float)(u_530_F.G_564_y((double)partialTicks, this.P_1922_E, this.w_1484_f) - camera.J_1907_R());
        float z = (float)(u_530_F.G_564_y((double)partialTicks, this.u_1723_Y, this.t_148_a) - camera.R_4764_Y());
        M_1336_P[] vertices = new M_1336_P[]{new M_1336_P(-1.0f, 0.0f, -1.0f), new M_1336_P(-1.0f, 0.0f, 1.0f), new M_1336_P(1.0f, 0.0f, 1.0f), new M_1336_P(1.0f, 0.0f, -1.0f)};
        float ageDelta = u_530_F.v_4262_N(partialTicks, (float)this.w_1457_N - 1.0f, this.w_1457_N);
        float progress = ageDelta / (float)this.Y_601_j;
        float scale = this.J_1907_R * (0.8f + 0.2f * progress);
        for (M_1336_P vertex : vertices) {
            vertex.n_1700_B(scale);
            vertex.R_4764_Y(x, y, z);
        }
        float unit = 2.0f / (float)this.H_2857_Y.G_564_y;
        float minU = this.R_4764_Y() + unit;
        float maxU = this.G_564_y() - unit;
        float minV = this.P_1922_E();
        float maxV = this.u_1723_Y();
        int light = this.n_1700_B(partialTicks);
        this.n_1700_B(buffer, vertices, 0, 1, minU, maxU, minV, maxV, light);
        this.n_1700_B(buffer, vertices, 1, 2, minU, maxU, minV, maxV, light);
        this.n_1700_B(buffer, vertices, 2, 3, minU, maxU, minV, maxV, light);
        this.n_1700_B(buffer, vertices, 3, 0, minU, maxU, minV, maxV, light);
    }

    private void n_1700_B(D_4792_h buffer, M_1336_P[] vertices, int a, int b, float minU, float maxU, float minV, float maxV, int light) {
        buffer.pos(vertices[a].n_1700_B(), vertices[a].J_1907_R(), vertices[a].R_4764_Y()).tex(minU, maxV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(vertices[b].n_1700_B(), vertices[b].J_1907_R(), vertices[b].R_4764_Y()).tex(maxU, maxV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(vertices[b].n_1700_B(), vertices[b].J_1907_R() + this.Y_1740_V, vertices[b].R_4764_Y()).tex(maxU, minV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(vertices[a].n_1700_B(), vertices[a].J_1907_R() + this.Y_1740_V, vertices[a].R_4764_Y()).tex(minU, minV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(vertices[b].n_1700_B(), vertices[b].J_1907_R(), vertices[b].R_4764_Y()).tex(maxU, maxV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(vertices[a].n_1700_B(), vertices[a].J_1907_R(), vertices[a].R_4764_Y()).tex(minU, maxV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(vertices[a].n_1700_B(), vertices[a].J_1907_R() + this.Y_1740_V, vertices[a].R_4764_Y()).tex(minU, minV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(vertices[b].n_1700_B(), vertices[b].J_1907_R() + this.Y_1740_V, vertices[b].R_4764_Y()).tex(maxU, minV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
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
            return new S_315_z(clientWorld, x, y, z, (float)xSpeed, (float)ySpeed, this.n_1700_B);
        }
    }
}


