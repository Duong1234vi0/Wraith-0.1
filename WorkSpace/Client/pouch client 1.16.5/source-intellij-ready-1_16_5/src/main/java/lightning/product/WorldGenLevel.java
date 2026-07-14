/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.stream.Stream;
import lightning.product.StructureFeature;
import lightning.product.SectionPos;
import lightning.product.ServerLevelAccessor;
import lightning.product.StructureStart;

public interface WorldGenLevel
extends ServerLevelAccessor {
    public long n_1700_B();

    public Stream<? extends StructureStart<?>> n_1700_B(SectionPos var1, StructureFeature<?> var2);
}


