/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.LongSet
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.StructureFeature;
import lightning.product.StructureStart;

public interface FeatureAccess {
    @Nullable
    public StructureStart<?> func_230342_a_(StructureFeature<?> var1);

    public void func_230344_a_(StructureFeature<?> var1, StructureStart<?> var2);

    public LongSet func_230346_b_(StructureFeature<?> var1);

    public void func_230343_a_(StructureFeature<?> var1, long var2);

    public Map<StructureFeature<?>, LongSet> getStructureReferences();

    public void setStructureReferences(Map<StructureFeature<?>, LongSet> var1);
}


