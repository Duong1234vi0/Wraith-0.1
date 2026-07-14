/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.AbstractMegaTreeGrower;
import lightning.product.TreeConfiguration;
import lightning.product.Features;
import lightning.product.ConfiguredFeature;

public class SpruceTreeGrower
extends AbstractMegaTreeGrower {
    @Override
    @Nullable
    protected ConfiguredFeature<TreeConfiguration, ?> n_1700_B(Random randomIn, boolean largeHive) {
        return Features.o_2341_D;
    }

    @Override
    @Nullable
    protected ConfiguredFeature<TreeConfiguration, ?> n_1700_B(Random rand) {
        return rand.nextBoolean() ? Features.L_1362_X : Features.P_5000_x;
    }
}


