/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockHitResult;
import lightning.product.BlockPlaceContext;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.x_1688_C;

public class DirectionalPlaceContext
extends BlockPlaceContext {
    private final b_257_Y J_1907_R;

    public DirectionalPlaceContext(b_4507_u worldIn, c_1514_x pos, b_257_Y lookDirectionIn, Z_1993_T stackIn, b_257_Y against) {
        super(worldIn, null, x_1688_C.n_1700_B, stackIn, new BlockHitResult(e_2866_D.R_4764_Y(pos), against, pos, false));
        this.J_1907_R = lookDirectionIn;
    }

    @Override
    public c_1514_x getPos() {
        return this.func_242401_i().n_1700_B();
    }

    @Override
    public boolean n_1700_B() {
        return this.getWorld().getBlockState(this.func_242401_i().n_1700_B()).n_1700_B(this);
    }

    @Override
    public boolean J_1907_R() {
        return this.n_1700_B();
    }

    @Override
    public b_257_Y R_4764_Y() {
        return b_257_Y.n_1700_B;
    }

    @Override
    public b_257_Y[] G_564_y() {
        switch (this.J_1907_R) {
            default: {
                return new b_257_Y[]{b_257_Y.n_1700_B, b_257_Y.R_4764_Y, b_257_Y.u_1723_Y, b_257_Y.G_564_y, b_257_Y.P_1922_E, b_257_Y.J_1907_R};
            }
            case J_1907_R: {
                return new b_257_Y[]{b_257_Y.n_1700_B, b_257_Y.J_1907_R, b_257_Y.R_4764_Y, b_257_Y.u_1723_Y, b_257_Y.G_564_y, b_257_Y.P_1922_E};
            }
            case R_4764_Y: {
                return new b_257_Y[]{b_257_Y.n_1700_B, b_257_Y.R_4764_Y, b_257_Y.u_1723_Y, b_257_Y.P_1922_E, b_257_Y.J_1907_R, b_257_Y.G_564_y};
            }
            case G_564_y: {
                return new b_257_Y[]{b_257_Y.n_1700_B, b_257_Y.G_564_y, b_257_Y.u_1723_Y, b_257_Y.P_1922_E, b_257_Y.J_1907_R, b_257_Y.R_4764_Y};
            }
            case P_1922_E: {
                return new b_257_Y[]{b_257_Y.n_1700_B, b_257_Y.P_1922_E, b_257_Y.G_564_y, b_257_Y.J_1907_R, b_257_Y.R_4764_Y, b_257_Y.u_1723_Y};
            }
            case u_1723_Y: 
        }
        return new b_257_Y[]{b_257_Y.n_1700_B, b_257_Y.u_1723_Y, b_257_Y.G_564_y, b_257_Y.J_1907_R, b_257_Y.R_4764_Y, b_257_Y.P_1922_E};
    }

    @Override
    public b_257_Y getPlacementHorizontalFacing() {
        return this.J_1907_R.h_1847_R() == b_257_Y.n_1700_B.J_1907_R ? b_257_Y.R_4764_Y : this.J_1907_R;
    }

    @Override
    public boolean hasSecondaryUseForPlayer() {
        return false;
    }

    @Override
    public float getPlacementYaw() {
        return this.J_1907_R.G_564_y() * 90;
    }
}


