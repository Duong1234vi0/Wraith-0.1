/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.utils;

import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.BaseFireBlock;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.s_1395_c;

public final class VecUtils {
    private VecUtils() {
    }

    public static e_2866_D calculateBlockCenter(b_4507_u world, c_1514_x pos) {
        K_4074_S b = world.getBlockState(pos);
        s_1395_c shape = b.u_2550_I(world, pos);
        if (shape.J_1907_R()) {
            return VecUtils.getBlockPosCenter(pos);
        }
        double xDiff = (shape.J_1907_R(b_257_Y.n_1700_B.n_1700_B) + shape.R_4764_Y(b_257_Y.n_1700_B.n_1700_B)) / 2.0;
        double yDiff = (shape.J_1907_R(b_257_Y.n_1700_B.J_1907_R) + shape.R_4764_Y(b_257_Y.n_1700_B.J_1907_R)) / 2.0;
        double zDiff = (shape.J_1907_R(b_257_Y.n_1700_B.R_4764_Y) + shape.R_4764_Y(b_257_Y.n_1700_B.R_4764_Y)) / 2.0;
        if (Double.isNaN(xDiff) || Double.isNaN(yDiff) || Double.isNaN(zDiff)) {
            throw new IllegalStateException(String.valueOf(b) + " " + String.valueOf(pos) + " " + String.valueOf(shape));
        }
        if (b.J_1907_R() instanceof BaseFireBlock) {
            yDiff = 0.0;
        }
        return new e_2866_D((double)pos.getX() + xDiff, (double)pos.getY() + yDiff, (double)pos.getZ() + zDiff);
    }

    public static e_2866_D getBlockPosCenter(c_1514_x pos) {
        return new e_2866_D((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
    }

    public static double distanceToCenter(c_1514_x pos, double x, double y, double z) {
        double xdiff = (double)pos.getX() + 0.5 - x;
        double ydiff = (double)pos.getY() + 0.5 - y;
        double zdiff = (double)pos.getZ() + 0.5 - z;
        return Math.sqrt(xdiff * xdiff + ydiff * ydiff + zdiff * zdiff);
    }

    public static double entityDistanceToCenter(N_4263_v entity, c_1514_x pos) {
        return VecUtils.distanceToCenter(pos, entity.s_4990_V().J_1907_R, entity.s_4990_V().R_4764_Y, entity.s_4990_V().G_564_y);
    }

    public static double entityFlatDistanceToCenter(N_4263_v entity, c_1514_x pos) {
        return VecUtils.distanceToCenter(pos, entity.s_4990_V().J_1907_R, (double)pos.getY() + 0.5, entity.s_4990_V().G_564_y);
    }
}


