/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.K_4040_w;
import lightning.product.V_3137_a;
import lightning.product.e_3109_Q;
import lightning.product.EmptyPoolElement;
import lightning.product.StructurePoolElement;
import lightning.product.p_3713_U;
import lightning.product.z_4596_H;

public interface StructurePoolElementType<P extends StructurePoolElement> {
    public static final StructurePoolElementType<e_3109_Q> n_1700_B = StructurePoolElementType.n_1700_B("single_pool_element", e_3109_Q.J_1907_R);
    public static final StructurePoolElementType<K_4040_w> J_1907_R = StructurePoolElementType.n_1700_B("list_pool_element", K_4040_w.n_1700_B);
    public static final StructurePoolElementType<z_4596_H> R_4764_Y = StructurePoolElementType.n_1700_B("feature_pool_element", z_4596_H.n_1700_B);
    public static final StructurePoolElementType<EmptyPoolElement> G_564_y = StructurePoolElementType.n_1700_B("empty_pool_element", EmptyPoolElement.n_1700_B);
    public static final StructurePoolElementType<p_3713_U> P_1922_E = StructurePoolElementType.n_1700_B("legacy_single_pool_element", p_3713_U.n_1700_B);

    public Codec<P> codec();

    public static <P extends StructurePoolElement> StructurePoolElementType<P> n_1700_B(String p_236851_0_, Codec<P> p_236851_1_) {
        return V_3137_a.n_1700_B(V_3137_a.g_4106_L, p_236851_0_, () -> p_236851_1_);
    }
}


