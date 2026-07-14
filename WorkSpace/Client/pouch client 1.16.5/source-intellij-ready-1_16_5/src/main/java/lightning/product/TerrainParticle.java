/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.ParticleRenderType;
import lightning.product.K_4074_S;
import lightning.product.TextureSheetParticle;
import lightning.product.X_426_i;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.z_883_p;

public class TerrainParticle
extends TextureSheetParticle {
    private final K_4074_S n_1700_B;
    private c_1514_x J_1907_R;
    private final float Y_1740_V;
    private final float t_4043_B;

    public TerrainParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, K_4074_S state) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.n_1700_B = state;
        this.n_1700_B(MinecraftClient.A_4115_X().z_1333_t().J_1907_R().n_1700_B(state));
        this.Y_259_p = 1.0f;
        this.Q_2552_b = 0.6f;
        this.C_2741_M = 0.6f;
        this.k_2293_S = 0.6f;
        this.A_4115_X /= 2.0f;
        this.Y_1740_V = this.multiplayerClientSuggestionProvider.nextFloat() * 3.0f;
        this.t_4043_B = this.multiplayerClientSuggestionProvider.nextFloat() * 3.0f;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.n_1700_B;
    }

    public TerrainParticle n_1700_B(c_1514_x pos) {
        this.J_1907_R = pos;
        if (this.n_1700_B.n_1700_B(a_3742_W.t_148_a)) {
            return this;
        }
        this.J_1907_R(pos);
        return this;
    }

    public TerrainParticle v_4262_N() {
        this.J_1907_R = new c_1514_x(this.v_4262_N, this.w_1484_f, this.t_148_a);
        if (this.n_1700_B.n_1700_B(a_3742_W.t_148_a)) {
            return this;
        }
        this.J_1907_R(this.J_1907_R);
        return this;
    }

    protected void J_1907_R(@Nullable c_1514_x pos) {
        int i = MinecraftClient.A_4115_X().j_276_v().n_1700_B(this.n_1700_B, this.R_4764_Y, pos, 0);
        this.Q_2552_b *= (float)(i >> 16 & 0xFF) / 255.0f;
        this.C_2741_M *= (float)(i >> 8 & 0xFF) / 255.0f;
        this.k_2293_S *= (float)(i & 0xFF) / 255.0f;
    }

    @Override
    protected float R_4764_Y() {
        return this.H_2857_Y.n_1700_B((double)((this.Y_1740_V + 1.0f) / 4.0f * 16.0f));
    }

    @Override
    protected float G_564_y() {
        return this.H_2857_Y.n_1700_B((double)(this.Y_1740_V / 4.0f * 16.0f));
    }

    @Override
    protected float P_1922_E() {
        return this.H_2857_Y.J_1907_R((double)(this.t_4043_B / 4.0f * 16.0f));
    }

    @Override
    protected float u_1723_Y() {
        return this.H_2857_Y.J_1907_R((double)((this.t_4043_B + 1.0f) / 4.0f * 16.0f));
    }

    @Override
    public int n_1700_B(float partialTick) {
        int i = super.n_1700_B(partialTick);
        int j = 0;
        if (this.R_4764_Y.M_588_G(this.J_1907_R)) {
            j = z_883_p.n_1700_B(this.R_4764_Y, this.J_1907_R);
        }
        return i == 0 ? j : i;
    }

    public static class n_1700_B
    implements ParticleProvider<X_426_i> {
        @Override
        public c_3457_g n_1700_B(X_426_i typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            K_4074_S blockstate = typeIn.n_1700_B();
            return !blockstate.v_4262_N() && !blockstate.n_1700_B(a_3742_W.O_2151_c) ? new TerrainParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, blockstate).v_4262_N() : null;
        }
    }
}



