/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.M_1336_P;
import lightning.product.b_4507_u;
import lightning.product.c_3457_g;
import lightning.product.e_2866_D;
import lightning.product.h_3572_K;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;

public abstract class SingleQuadParticle
extends c_3457_g {
    protected float A_4115_X;

    protected SingleQuadParticle(b_4507_u world, double x, double y, double z) {
        super(world, x, y, z);
        this.A_4115_X = 0.1f * (this.multiplayerClientSuggestionProvider.nextFloat() * 0.5f + 0.5f) * 2.0f;
    }

    protected SingleQuadParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.A_4115_X = 0.1f * (this.multiplayerClientSuggestionProvider.nextFloat() * 0.5f + 0.5f) * 2.0f;
    }

    @Override
    public void n_1700_B(D_4792_h buffer, h_3572_K renderInfo, float partialTicks) {
        w_3785_E quaternion;
        e_2866_D vector3d = renderInfo.J_1907_R();
        float f = (float)(u_530_F.G_564_y((double)partialTicks, this.G_564_y, this.v_4262_N) - vector3d.n_1700_B());
        float f1 = (float)(u_530_F.G_564_y((double)partialTicks, this.P_1922_E, this.w_1484_f) - vector3d.J_1907_R());
        float f2 = (float)(u_530_F.G_564_y((double)partialTicks, this.u_1723_Y, this.t_148_a) - vector3d.R_4764_Y());
        if (this.Z_875_P == 0.0f) {
            quaternion = renderInfo.u_1723_Y();
        } else {
            quaternion = new w_3785_E(renderInfo.u_1723_Y());
            float f3 = u_530_F.v_4262_N(partialTicks, this.c_3005_b, this.Z_875_P);
            quaternion.n_1700_B(M_1336_P.u_1723_Y.J_1907_R(f3));
        }
        M_1336_P vector3f1 = new M_1336_P(-1.0f, -1.0f, 0.0f);
        vector3f1.n_1700_B(quaternion);
        M_1336_P[] avector3f = new M_1336_P[]{new M_1336_P(-1.0f, -1.0f, 0.0f), new M_1336_P(-1.0f, 1.0f, 0.0f), new M_1336_P(1.0f, 1.0f, 0.0f), new M_1336_P(1.0f, -1.0f, 0.0f)};
        float f4 = this.J_1907_R(partialTicks);
        for (int i = 0; i < 4; ++i) {
            M_1336_P vector3f = avector3f[i];
            vector3f.n_1700_B(quaternion);
            vector3f.n_1700_B(f4);
            vector3f.R_4764_Y(f, f1, f2);
        }
        float f7 = this.R_4764_Y();
        float f8 = this.G_564_y();
        float f5 = this.P_1922_E();
        float f6 = this.u_1723_Y();
        int j = this.n_1700_B(partialTicks);
        buffer.pos(avector3f[0].n_1700_B(), avector3f[0].J_1907_R(), avector3f[0].R_4764_Y()).tex(f8, f6).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(j).endVertex();
        buffer.pos(avector3f[1].n_1700_B(), avector3f[1].J_1907_R(), avector3f[1].R_4764_Y()).tex(f8, f5).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(j).endVertex();
        buffer.pos(avector3f[2].n_1700_B(), avector3f[2].J_1907_R(), avector3f[2].R_4764_Y()).tex(f7, f5).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(j).endVertex();
        buffer.pos(avector3f[3].n_1700_B(), avector3f[3].J_1907_R(), avector3f[3].R_4764_Y()).tex(f7, f6).n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F).J_1907_R(j).endVertex();
    }

    public float J_1907_R(float scaleFactor) {
        return this.A_4115_X;
    }

    @Override
    public c_3457_g G_564_y(float scale) {
        this.A_4115_X *= scale;
        return super.G_564_y(scale);
    }

    protected abstract float R_4764_Y();

    protected abstract float G_564_y();

    protected abstract float P_1922_E();

    protected abstract float u_1723_Y();
}


