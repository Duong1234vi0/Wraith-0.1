/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.ColumnPlacer;
import lightning.product.V_3137_a;
import lightning.product.X_3306_T;
import lightning.product.k_3886_Y;
import lightning.product.BlockPlacer;

public class BlockPlacerType<P extends BlockPlacer> {
    public static final BlockPlacerType<X_3306_T> n_1700_B = BlockPlacerType.n_1700_B("simple_block_placer", X_3306_T.J_1907_R);
    public static final BlockPlacerType<k_3886_Y> J_1907_R = BlockPlacerType.n_1700_B("double_plant_placer", k_3886_Y.J_1907_R);
    public static final BlockPlacerType<ColumnPlacer> R_4764_Y = BlockPlacerType.n_1700_B("column_placer", ColumnPlacer.J_1907_R);
    private final Codec<P> G_564_y;

    private static <P extends BlockPlacer> BlockPlacerType<P> n_1700_B(String name, Codec<P> codec) {
        return V_3137_a.n_1700_B(V_3137_a.RowButton, name, new BlockPlacerType<P>(codec));
    }

    private BlockPlacerType(Codec<P> codec) {
        this.G_564_y = codec;
    }

    public Codec<P> n_1700_B() {
        return this.G_564_y;
    }
}


