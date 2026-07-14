/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;

public class BlockPredicate
implements Predicate<K_4074_S> {
    private final T_2915_h n_1700_B;

    public BlockPredicate(T_2915_h blockType) {
        this.n_1700_B = blockType;
    }

    public static BlockPredicate n_1700_B(T_2915_h blockType) {
        return new BlockPredicate(blockType);
    }

    public boolean n_1700_B(@Nullable K_4074_S p_test_1_) {
        return p_test_1_ != null && p_test_1_.n_1700_B(this.n_1700_B);
    }

    @Override
    public /* synthetic */ boolean test(@Nullable Object object) {
        return this.n_1700_B((K_4074_S)object);
    }
}


