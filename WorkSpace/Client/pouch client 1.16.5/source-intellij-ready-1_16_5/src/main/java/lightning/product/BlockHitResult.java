/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.HitResult;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;

public class BlockHitResult
extends HitResult {
    private final b_257_Y J_1907_R;
    private final c_1514_x R_4764_Y;
    private final boolean G_564_y;
    private final boolean P_1922_E;

    public static BlockHitResult n_1700_B(e_2866_D hitVec, b_257_Y faceIn, c_1514_x posIn) {
        return new BlockHitResult(true, hitVec, faceIn, posIn, false);
    }

    public BlockHitResult(e_2866_D hitVec, b_257_Y faceIn, c_1514_x posIn, boolean isInside) {
        this(false, hitVec, faceIn, posIn, isInside);
    }

    private BlockHitResult(boolean isMissIn, e_2866_D hitVec, b_257_Y faceIn, c_1514_x posIn, boolean isInside) {
        super(hitVec);
        this.G_564_y = isMissIn;
        this.J_1907_R = faceIn;
        this.R_4764_Y = posIn;
        this.P_1922_E = isInside;
    }

    public BlockHitResult n_1700_B(b_257_Y newFace) {
        return new BlockHitResult(this.G_564_y, this.n_1700_B, newFace, this.R_4764_Y, this.P_1922_E);
    }

    public BlockHitResult n_1700_B(c_1514_x pos) {
        return new BlockHitResult(this.G_564_y, this.n_1700_B, this.J_1907_R, pos, this.P_1922_E);
    }

    public c_1514_x n_1700_B() {
        return this.R_4764_Y;
    }

    public b_257_Y J_1907_R() {
        return this.J_1907_R;
    }

    @Override
    public HitResult.n_1700_B R_4764_Y() {
        return this.G_564_y ? HitResult.n_1700_B.n_1700_B : HitResult.n_1700_B.J_1907_R;
    }

    public boolean G_564_y() {
        return this.P_1922_E;
    }
}


