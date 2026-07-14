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

public class EntityCatSplitFix
extends m_2444_z {
    public EntityCatSplitFix(Schema p_i50428_1_, boolean p_i50428_2_) {
        super("EntityCatSplitFix", p_i50428_1_, p_i50428_2_);
    }

    @Override
    protected Pair<String, Dynamic<?>> n_1700_B(String name, Dynamic<?> tag) {
        if (Objects.equals("minecraft:ocelot", name)) {
            int i = tag.get("CatType").asInt(0);
            if (i == 0) {
                String s = tag.get("Owner").asString("");
                String s1 = tag.get("OwnerUUID").asString("");
                if (s.length() > 0 || s1.length() > 0) {
                    tag.set("Trusting", tag.createBoolean(true));
                }
            } else if (i > 0 && i < 4) {
                tag = tag.set("CatType", tag.createInt(i));
                tag = tag.set("OwnerUUID", tag.createString(tag.get("OwnerUUID").asString("")));
                return Pair.of((Object)"minecraft:cat", (Object)tag);
            }
        }
        return Pair.of((Object)name, tag);
    }
}


