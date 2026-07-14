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

public class V_2455_Z
extends m_2444_z {
    public V_2455_Z(Schema outputSchema, boolean changesType) {
        super("EntityZombieSplitFix", outputSchema, changesType);
    }

    @Override
    protected Pair<String, Dynamic<?>> n_1700_B(String name, Dynamic<?> tag) {
        if (Objects.equals("Zombie", name)) {
            String s = "Zombie";
            int i = tag.get("ZombieType").asInt(0);
            switch (i) {
                default: {
                    break;
                }
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: {
                    s = "ZombieVillager";
                    tag = tag.set("Profession", tag.createInt(i - 1));
                    break;
                }
                case 6: {
                    s = "Husk";
                }
            }
            tag = tag.remove("ZombieType");
            return Pair.of((Object)s, (Object)tag);
        }
        return Pair.of((Object)name, tag);
    }
}

