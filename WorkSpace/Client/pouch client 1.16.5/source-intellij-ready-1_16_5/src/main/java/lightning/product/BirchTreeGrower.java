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

public class BirchTreeGrower
extends AbstractTreeGrower {
    @Override
    @Nullable
    protected ConfiguredFeature<TreeConfiguration, ?> n_1700_B(Random randomIn, boolean largeHive) {
        return largeHive ? Features.n_3197_X : Features.U_1341_G;
    }
}


