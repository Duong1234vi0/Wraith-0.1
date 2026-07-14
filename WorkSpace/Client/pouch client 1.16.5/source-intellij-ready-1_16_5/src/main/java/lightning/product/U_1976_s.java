/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.Random;
import lightning.product.References;
import lightning.product.NamedEntityFix;

public class U_1976_s
extends NamedEntityFix {
    private static final Random n_1700_B = new Random();

    public U_1976_s(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "EntityZombieVillagerTypeFix", References.M_182_A, "Zombie");
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_209656_1_) {
        if (p_209656_1_.get("IsVillager").asBoolean(false)) {
            if (!p_209656_1_.get("ZombieType").result().isPresent()) {
                int i = this.n_1700_B(p_209656_1_.get("VillagerProfession").asInt(-1));
                if (i == -1) {
                    i = this.n_1700_B(n_1700_B.nextInt(6));
                }
                p_209656_1_ = p_209656_1_.set("ZombieType", p_209656_1_.createInt(i));
            }
            p_209656_1_ = p_209656_1_.remove("IsVillager");
        }
        return p_209656_1_;
    }

    private int n_1700_B(int p_191277_1_) {
        return p_191277_1_ >= 0 && p_191277_1_ < 6 ? p_191277_1_ : -1;
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), this::n_1700_B);
    }
}


