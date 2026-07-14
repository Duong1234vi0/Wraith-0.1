/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SingleQuadParticle;
import lightning.product.B_3871_I;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;

public abstract class TextureSheetParticle
extends SingleQuadParticle {
    protected B_3871_I H_2857_Y;

    protected TextureSheetParticle(b_4507_u world, double x, double y, double z) {
        super(world, x, y, z);
    }

    protected TextureSheetParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
    }

    protected void n_1700_B(B_3871_I sprite) {
        this.H_2857_Y = sprite;
    }

    @Override
    protected float R_4764_Y() {
        return this.H_2857_Y.u_1723_Y();
    }

    @Override
    protected float G_564_y() {
        return this.H_2857_Y.v_4262_N();
    }

    @Override
    protected float P_1922_E() {
        return this.H_2857_Y.w_1484_f();
    }

    @Override
    protected float u_1723_Y() {
        return this.H_2857_Y.t_148_a();
    }

    public void n_1700_B(SpriteSet sprite) {
        this.n_1700_B(sprite.n_1700_B(this.multiplayerClientSuggestionProvider));
    }

    public void J_1907_R(SpriteSet sprite) {
        this.n_1700_B(sprite.n_1700_B(this.w_1457_N, this.Y_601_j));
    }
}


