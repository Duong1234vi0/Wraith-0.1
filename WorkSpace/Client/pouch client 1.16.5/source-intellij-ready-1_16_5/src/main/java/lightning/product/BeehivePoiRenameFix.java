/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.schemas.Schema
 */
package lightning.product;

import com.mojang.datafixers.schemas.Schema;
import lightning.product.PoiTypeRename;

public class BeehivePoiRenameFix
extends PoiTypeRename {
    public BeehivePoiRenameFix(Schema p_i225700_1_) {
        super(p_i225700_1_, false);
    }

    @Override
    protected String n_1700_B(String p_225501_1_) {
        return p_225501_1_.equals("minecraft:bee_hive") ? "minecraft:beehive" : p_225501_1_;
    }
}


