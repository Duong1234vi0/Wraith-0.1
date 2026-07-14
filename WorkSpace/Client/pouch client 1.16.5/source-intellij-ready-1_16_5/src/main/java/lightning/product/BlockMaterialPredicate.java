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
import lightning.product.Material;

public class BlockMaterialPredicate
implements Predicate<K_4074_S> {
    private static final BlockMaterialPredicate n_1700_B = new BlockMaterialPredicate(Material.n_1700_B){

        @Override
        public boolean n_1700_B(@Nullable K_4074_S p_test_1_) {
            return p_test_1_ != null && p_test_1_.v_4262_N();
        }

        @Override
        public /* synthetic */ boolean test(@Nullable Object object) {
            return this.n_1700_B((K_4074_S)object);
        }
    };
    private final Material J_1907_R;

    private BlockMaterialPredicate(Material materialIn) {
        this.J_1907_R = materialIn;
    }

    public static BlockMaterialPredicate n_1700_B(Material materialIn) {
        return materialIn == Material.n_1700_B ? n_1700_B : new BlockMaterialPredicate(materialIn);
    }

    public boolean n_1700_B(@Nullable K_4074_S p_test_1_) {
        return p_test_1_ != null && p_test_1_.R_4764_Y() == this.J_1907_R;
    }

    @Override
    public /* synthetic */ boolean test(@Nullable Object object) {
        return this.n_1700_B((K_4074_S)object);
    }
}


