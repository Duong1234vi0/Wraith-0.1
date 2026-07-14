/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.ParticleRenderType;
import lightning.product.N_4263_v;
import lightning.product.RenderBuffers;
import lightning.product.c_3005_b;
import lightning.product.c_3457_g;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.h_3572_K;
import lightning.product.k_4690_i;
import lightning.product.n_1494_c;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.shaders.Program;
import net.optifine.shaders.Shaders;

public class I_438_q
extends c_3457_g {
    private final RenderBuffers n_1700_B;
    private final N_4263_v J_1907_R;
    private final N_4263_v H_2857_Y;
    private int A_4115_X;
    private final w_2040_b Y_1740_V;

    public I_438_q(w_2040_b entityRenderManager, RenderBuffers buffers, k_4690_i world, N_4263_v item, N_4263_v target) {
        this(entityRenderManager, buffers, world, item, target, item.I_4348_c());
    }

    public I_438_q(w_2040_b entityRenderManager, RenderBuffers buffers, c_3005_b world, N_4263_v item, N_4263_v target) {
        this(entityRenderManager, buffers, world, item, target, item.I_4348_c());
    }

    private I_438_q(w_2040_b entityRenderManager, RenderBuffers buffers, k_4690_i world, N_4263_v item, N_4263_v target, e_2866_D motionVector) {
        super(world, item.O_3598_v(), item.X_2960_b(), item.l_2647_k(), motionVector.J_1907_R, motionVector.R_4764_Y, motionVector.G_564_y);
        this.n_1700_B = buffers;
        this.J_1907_R = this.n_1700_B(item);
        this.H_2857_Y = target;
        this.Y_1740_V = entityRenderManager;
    }

    private I_438_q(w_2040_b entityRenderManager, RenderBuffers buffers, c_3005_b world, N_4263_v item, N_4263_v target, e_2866_D motionVector) {
        super(world, item.O_3598_v(), item.X_2960_b(), item.l_2647_k(), motionVector.J_1907_R, motionVector.R_4764_Y, motionVector.G_564_y);
        this.n_1700_B = buffers;
        this.J_1907_R = this.n_1700_B(item);
        this.H_2857_Y = target;
        this.Y_1740_V = entityRenderManager;
    }

    private N_4263_v n_1700_B(N_4263_v entity) {
        return !(entity instanceof n_1494_c) ? entity : ((n_1494_c)entity).w_1457_N();
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.P_1922_E;
    }

    @Override
    public void n_1700_B(D_4792_h buffer, h_3572_K renderInfo, float partialTicks) {
        Program program = null;
        if (Config.isShaders()) {
            program = Shaders.activeProgram;
            Shaders.nextEntity(this.J_1907_R);
        }
        float f = ((float)this.A_4115_X + partialTicks) / 3.0f;
        f *= f;
        double d0 = u_530_F.G_564_y((double)partialTicks, this.H_2857_Y.q_1982_R, this.H_2857_Y.O_3598_v());
        double d1 = u_530_F.G_564_y((double)partialTicks, this.H_2857_Y.dtoRealmsServerAddress, this.H_2857_Y.X_2960_b()) + 0.5;
        double d2 = u_530_F.G_564_y((double)partialTicks, this.H_2857_Y.w_612_n, this.H_2857_Y.l_2647_k());
        double d3 = u_530_F.G_564_y((double)f, this.J_1907_R.O_3598_v(), d0);
        double d4 = u_530_F.G_564_y((double)f, this.J_1907_R.X_2960_b(), d1);
        double d5 = u_530_F.G_564_y((double)f, this.J_1907_R.l_2647_k(), d2);
        o_3091_w.n_1700_B irendertypebuffer$impl = this.n_1700_B.J_1907_R();
        e_2866_D vector3d = renderInfo.J_1907_R();
        this.Y_1740_V.n_1700_B(this.J_1907_R, d3 - vector3d.n_1700_B(), d4 - vector3d.J_1907_R(), d5 - vector3d.R_4764_Y(), this.J_1907_R.p_178_J, partialTicks, new g_221_o(), irendertypebuffer$impl, this.Y_1740_V.n_1700_B(this.J_1907_R, partialTicks));
        irendertypebuffer$impl.J_1907_R();
        if (Config.isShaders()) {
            Shaders.setEntityId(null);
            Shaders.useProgram(program);
        }
    }

    @Override
    public void n_1700_B() {
        ++this.A_4115_X;
        if (this.A_4115_X == 3) {
            this.s_956_w();
        }
    }
}


