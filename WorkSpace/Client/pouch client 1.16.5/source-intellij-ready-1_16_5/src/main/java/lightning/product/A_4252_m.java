/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.ParticleRenderType;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
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
import lightning.product.w_2099_r;
import lightning.product.w_3785_E;

public class A_4252_m
extends TextureSheetParticle {
    private final SpriteSet n_1700_B;
    private final float J_1907_R;
    private final int Y_1740_V;
    private final float t_4043_B;
    private final float x_607_J;
    private float e_4240_b;
    private w_3785_E n_3318_d = w_3785_E.n_1700_B.v_4262_N();

    protected A_4252_m(k_4690_i world, double x, double y, double z, double scaleBase, double targetId, SpriteSet sprites) {
        super(world, x, y, z);
        this.n_1700_B = sprites;
        this.J_1907_R = (float)scaleBase;
        this.Y_1740_V = (int)targetId;
        this.Y_601_j = 20;
        this.t_4043_B = -180.0f + this.multiplayerClientSuggestionProvider.nextFloat() * 360.0f;
        this.x_607_J = -180.0f + this.multiplayerClientSuggestionProvider.nextFloat() * 360.0f;
        this.h_1847_R = false;
        this.q_2307_F = 0.0f;
        if (this.multiplayerClientSuggestionProvider.nextBoolean()) {
            this.n_1700_B(1.0f, 1.0f, 0.0f);
        } else {
            this.n_1700_B(0.0f, 1.0f, 0.0f);
        }
        this.J_1907_R(sprites);
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
            return;
        }
        if (!w_2099_r.Q_4569_t()) {
            this.s_956_w();
            return;
        }
        float opacity = w_2099_r.multiplayerClientSuggestionProvider();
        if (opacity <= 0.0f) {
            this.s_956_w();
            return;
        }
        this.J_1907_R(this.n_1700_B);
        N_4263_v target = this.R_4764_Y.J_1907_R(this.Y_1740_V);
        if (target != null) {
            this.J_1907_R(target.O_3598_v(), target.X_2960_b() + (double)target.v_165_F() * 0.5, target.l_2647_k());
        }
        this.e_4240_b += 20.0f;
        w_3785_E orbit = new w_3785_E(0.0f, 0.0f, this.e_4240_b, true);
        w_3785_E offset = new w_3785_E(this.x_607_J, this.t_4043_B, -this.x_607_J, true);
        offset.n_1700_B(orbit);
        offset.u_1723_Y();
        this.n_3318_d = offset;
        float progress = (float)this.w_1457_N / (float)this.Y_601_j;
        this.q_2307_F = u_530_F.n_1700_B((float)Math.sqrt(Math.sin(progress * (float)Math.PI)) / 1.2f, 0.0f, 1.0f) * opacity;
        this.A_4115_X = this.q_2307_F / Math.max(opacity, 0.001f) * this.J_1907_R;
    }

    @Override
    public void n_1700_B(D_4792_h buffer, h_3572_K renderInfo, float partialTicks) {
        e_2866_D cameraPos = renderInfo.J_1907_R();
        float renderX = (float)(u_530_F.G_564_y((double)partialTicks, this.G_564_y, this.v_4262_N) - cameraPos.n_1700_B());
        float renderY = (float)(u_530_F.G_564_y((double)partialTicks, this.P_1922_E, this.w_1484_f) - cameraPos.J_1907_R());
        float renderZ = (float)(u_530_F.G_564_y((double)partialTicks, this.u_1723_Y, this.t_148_a) - cameraPos.R_4764_Y());
        M_1336_P[] corners = new M_1336_P[]{new M_1336_P(-1.0f, -1.0f, 0.0f), new M_1336_P(-1.0f, 1.0f, 0.0f), new M_1336_P(1.0f, 1.0f, 0.0f), new M_1336_P(1.0f, -1.0f, 0.0f)};
        float scale = this.J_1907_R(partialTicks);
        w_3785_E quaternion = this.n_3318_d.v_4262_N();
        for (M_1336_P corner : corners) {
            corner.n_1700_B(quaternion);
            corner.n_1700_B(scale);
            corner.R_4764_Y(renderX, renderY, renderZ);
        }
        float minU = this.R_4764_Y();
        float maxU = this.G_564_y();
        float minV = this.P_1922_E();
        float maxV = this.u_1723_Y();
        int light = this.n_1700_B(partialTicks);
        buffer.pos(corners[0].n_1700_B(), corners[0].J_1907_R(), corners[0].R_4764_Y()).tex(maxU, maxV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(corners[1].n_1700_B(), corners[1].J_1907_R(), corners[1].R_4764_Y()).tex(maxU, minV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(corners[2].n_1700_B(), corners[2].J_1907_R(), corners[2].R_4764_Y()).tex(minU, minV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(corners[3].n_1700_B(), corners[3].J_1907_R(), corners[3].R_4764_Y()).tex(minU, maxV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(corners[3].n_1700_B(), corners[3].J_1907_R(), corners[3].R_4764_Y()).tex(minU, maxV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(corners[2].n_1700_B(), corners[2].J_1907_R(), corners[2].R_4764_Y()).tex(minU, minV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(corners[1].n_1700_B(), corners[1].J_1907_R(), corners[1].R_4764_Y()).tex(maxU, minV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
        buffer.pos(corners[0].n_1700_B(), corners[0].J_1907_R(), corners[0].R_4764_Y()).tex(maxU, maxV).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(light).endVertex();
    }

    @Override
    public int n_1700_B(float partialTick) {
        return 0xF000F0;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.R_4764_Y;
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet sprites) {
            this.n_1700_B = sprites;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new A_4252_m((k_4690_i)worldIn, x, y, z, xSpeed, ySpeed, this.n_1700_B);
        }
    }
}


