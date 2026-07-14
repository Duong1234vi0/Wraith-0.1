/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.Objects;
import lightning.product.m_2444_z;

public class EntitySkeletonSplitFix
extends m_2444_z {
    public EntitySkeletonSplitFix(Schema outputSchema, boolean changesType) {
        super("EntitySkeletonSplitFix", outputSchema, changesType);
    }

    @Override
    protected Pair<String, Dynamic<?>> n_1700_B(String name, Dynamic<?> tag) {
        if (Objects.equals(name, "Skeleton")) {
            int i = tag.get("SkeletonType").asInt(0);
            if (i == 1) {
                name = "WitherSkeleton";
            } else if (i == 2) {
                name = "Stray";
            }
        }
        return Pair.of((Object)name, tag);
    }
}


