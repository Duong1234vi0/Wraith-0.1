/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockAndTintGetter;
import lightning.product.c_1514_x;
import lightning.product.k_594_Q;
import lightning.product.ColorResolver;

public class BiomeColors {
    public static final ColorResolver n_1700_B = k_594_Q::n_1700_B;
    public static final ColorResolver J_1907_R = (biome, x, z) -> biome.v_4262_N();
    public static final ColorResolver R_4764_Y = (biome, x, z) -> biome.P_4830_p();

    private static int n_1700_B(BlockAndTintGetter worldIn, c_1514_x blockPosIn, ColorResolver colorResolverIn) {
        return worldIn.getBlockColor(blockPosIn, colorResolverIn);
    }

    public static int n_1700_B(BlockAndTintGetter worldIn, c_1514_x blockPosIn) {
        return BiomeColors.n_1700_B(worldIn, blockPosIn, n_1700_B);
    }

    public static int J_1907_R(BlockAndTintGetter worldIn, c_1514_x blockPosIn) {
        return BiomeColors.n_1700_B(worldIn, blockPosIn, J_1907_R);
    }

    public static int R_4764_Y(BlockAndTintGetter worldIn, c_1514_x blockPosIn) {
        return BiomeColors.n_1700_B(worldIn, blockPosIn, R_4764_Y);
    }
}


