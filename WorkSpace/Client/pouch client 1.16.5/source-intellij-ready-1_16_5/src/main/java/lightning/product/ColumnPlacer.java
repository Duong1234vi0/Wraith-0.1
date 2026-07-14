/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Random;
import lightning.product.BlockPlacerType;
import lightning.product.K_4074_S;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.LevelAccessor;
import lightning.product.BlockPlacer;

public class ColumnPlacer
extends BlockPlacer {
    public static final Codec<ColumnPlacer> J_1907_R = RecordCodecBuilder.create(builder -> builder.group((App)Codec.INT.fieldOf("min_size").forGetter(placer -> placer.R_4764_Y), (App)Codec.INT.fieldOf("extra_size").forGetter(placer -> placer.G_564_y)).apply((Applicative)builder, ColumnPlacer::new));
    private final int R_4764_Y;
    private final int G_564_y;

    public ColumnPlacer(int minSize, int extraSize) {
        this.R_4764_Y = minSize;
        this.G_564_y = extraSize;
    }

    @Override
    protected BlockPlacerType<?> n_1700_B() {
        return BlockPlacerType.R_4764_Y;
    }

    @Override
    public void n_1700_B(LevelAccessor world, c_1514_x pos, K_4074_S state, Random random) {
        c_1514_x.n_1700_B blockpos$mutable = pos.toMutable();
        int i = this.R_4764_Y + random.nextInt(random.nextInt(this.G_564_y + 1) + 1);
        for (int j = 0; j < i; ++j) {
            world.n_1700_B((c_1514_x)blockpos$mutable, state, 2);
            blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
        }
    }
}


