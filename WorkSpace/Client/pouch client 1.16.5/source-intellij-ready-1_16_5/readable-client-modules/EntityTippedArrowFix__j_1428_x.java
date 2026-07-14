/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.schemas.Schema
 */
package lightning.product;

import com.mojang.datafixers.schemas.Schema;
import java.util.Objects;
import lightning.product.Z_3827_g;

public class j_1428_x
extends Z_3827_g {
    public j_1428_x(Schema outputSchema, boolean changesType) {
        super("EntityTippedArrowFix", outputSchema, changesType);
    }

    @Override
    protected String n_1700_B(String name) {
        return Objects.equals(name, "TippedArrow") ? "Arrow" : name;
    }
}

