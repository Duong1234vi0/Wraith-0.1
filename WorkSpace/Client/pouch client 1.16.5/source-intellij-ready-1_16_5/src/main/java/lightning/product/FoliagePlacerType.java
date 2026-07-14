/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.SpruceFoliagePlacer;
import lightning.product.MegaPineFoliagePlacer;
import lightning.product.FancyFoliagePlacer;
import lightning.product.DarkOakFoliagePlacer;
import lightning.product.BushFoliagePlacer;
import lightning.product.V_3137_a;
import lightning.product.BlobFoliagePlacer;
import lightning.product.PineFoliagePlacer;
import lightning.product.FoliagePlacer;
import lightning.product.MegaJungleFoliagePlacer;
import lightning.product.AcaciaFoliagePlacer;

public class FoliagePlacerType<P extends FoliagePlacer> {
    public static final FoliagePlacerType<BlobFoliagePlacer> n_1700_B = FoliagePlacerType.n_1700_B("blob_foliage_placer", BlobFoliagePlacer.n_1700_B);
    public static final FoliagePlacerType<SpruceFoliagePlacer> J_1907_R = FoliagePlacerType.n_1700_B("spruce_foliage_placer", SpruceFoliagePlacer.n_1700_B);
    public static final FoliagePlacerType<PineFoliagePlacer> R_4764_Y = FoliagePlacerType.n_1700_B("pine_foliage_placer", PineFoliagePlacer.n_1700_B);
    public static final FoliagePlacerType<AcaciaFoliagePlacer> G_564_y = FoliagePlacerType.n_1700_B("acacia_foliage_placer", AcaciaFoliagePlacer.n_1700_B);
    public static final FoliagePlacerType<BushFoliagePlacer> P_1922_E = FoliagePlacerType.n_1700_B("bush_foliage_placer", BushFoliagePlacer.R_4764_Y);
    public static final FoliagePlacerType<FancyFoliagePlacer> u_1723_Y = FoliagePlacerType.n_1700_B("fancy_foliage_placer", FancyFoliagePlacer.R_4764_Y);
    public static final FoliagePlacerType<MegaJungleFoliagePlacer> v_4262_N = FoliagePlacerType.n_1700_B("jungle_foliage_placer", MegaJungleFoliagePlacer.n_1700_B);
    public static final FoliagePlacerType<MegaPineFoliagePlacer> w_1484_f = FoliagePlacerType.n_1700_B("mega_pine_foliage_placer", MegaPineFoliagePlacer.n_1700_B);
    public static final FoliagePlacerType<DarkOakFoliagePlacer> t_148_a = FoliagePlacerType.n_1700_B("dark_oak_foliage_placer", DarkOakFoliagePlacer.n_1700_B);
    private final Codec<P> s_956_w;

    private static <P extends FoliagePlacer> FoliagePlacerType<P> n_1700_B(String p_236773_0_, Codec<P> p_236773_1_) {
        return V_3137_a.n_1700_B(V_3137_a.LongRunningTask, p_236773_0_, new FoliagePlacerType<P>(p_236773_1_));
    }

    private FoliagePlacerType(Codec<P> p_i232036_1_) {
        this.s_956_w = p_i232036_1_;
    }

    public Codec<P> n_1700_B() {
        return this.s_956_w;
    }
}


