/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.TreeConfiguration;
import lightning.product.Features;
import lightning.product.AbstractTreeGrower;
import lightning.product.ConfiguredFeature;

public class OakTreeGrower
extends AbstractTreeGrower {
    @Override
    @Nullable
    protected ConfiguredFeature<TreeConfiguration, ?> n_1700_B(Random randomIn, boolean largeHive) {
        if (randomIn.nextInt(10) == 0) {
            return largeHive ? Features.w_2705_t : Features.t_1446_I;
        }
        return largeHive ? Features.P_925_e : Features.TextRenderingUtils;
    }
}


