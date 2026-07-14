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

public class i_4614_X
extends m_2444_z {
    public i_4614_X(Schema outputSchema, boolean changesType) {
        super("EntityElderGuardianSplitFix", outputSchema, changesType);
    }

    @Override
    protected Pair<String, Dynamic<?>> n_1700_B(String name, Dynamic<?> tag) {
        return Pair.of((Object)(Objects.equals(name, "Guardian") && tag.get("Elder").asBoolean(false) ? "ElderGuardian" : name), tag);
    }
}

