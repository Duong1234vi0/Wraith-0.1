/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.P_2507_S;
import lightning.product.LootItemCondition;
import lightning.product.ResourceManager;
import lightning.product.ProfilerFiller;
import lightning.product.f_1402_I;
import lightning.product.g_1866_m;
import lightning.product.g_2336_b;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.Deserializers;
import lightning.product.LootItemConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class k_1471_n
extends P_2507_S {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Gson J_1907_R = Deserializers.n_1700_B().create();
    private Map<g_2336_b, LootItemCondition> R_4764_Y = ImmutableMap.of();

    public k_1471_n() {
        super(J_1907_R, "predicates");
    }

    @Nullable
    public LootItemCondition n_1700_B(g_2336_b p_227517_1_) {
        return this.R_4764_Y.get(p_227517_1_);
    }

    protected void n_1700_B(Map<g_2336_b, JsonElement> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        objectIn.forEach((p_237404_1_, p_237404_2_) -> {
            try {
                if (p_237404_2_.isJsonArray()) {
                    LootItemCondition[] ailootcondition = (LootItemCondition[])J_1907_R.fromJson(p_237404_2_, LootItemCondition[].class);
                    builder.put(p_237404_1_, (Object)new n_1700_B(ailootcondition));
                } else {
                    LootItemCondition ilootcondition = (LootItemCondition)J_1907_R.fromJson(p_237404_2_, LootItemCondition.class);
                    builder.put(p_237404_1_, (Object)ilootcondition);
                }
            }
            catch (Exception exception) {
                n_1700_B.error("Couldn't parse loot table {}", p_237404_1_, (Object)exception);
            }
        });
        ImmutableMap map = builder.build();
        g_1866_m validationtracker = new g_1866_m(f_1402_I.u_2550_I, ((Map)map)::get, p_227518_0_ -> null);
        map.forEach((p_227515_1_, p_227515_2_) -> p_227515_2_.n_1700_B(validationtracker.J_1907_R("{" + String.valueOf(p_227515_1_) + "}", (g_2336_b)p_227515_1_)));
        validationtracker.n_1700_B().forEach((p_227516_0_, p_227516_1_) -> n_1700_B.warn("Found validation problem in " + p_227516_0_ + ": " + p_227516_1_));
        this.R_4764_Y = map;
    }

    public Set<g_2336_b> J_1907_R() {
        return Collections.unmodifiableSet(this.R_4764_Y.keySet());
    }

    @Override
    protected /* synthetic */ void apply(Object object, ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        this.n_1700_B((Map)object, s_2107_a, x_2951_U);
    }

    static class n_1700_B
    implements LootItemCondition {
        private final LootItemCondition[] n_1700_B;
        private final Predicate<q_1704_m> J_1907_R;

        private n_1700_B(LootItemCondition[] p_i232164_1_) {
            this.n_1700_B = p_i232164_1_;
            this.J_1907_R = LootItemConditions.n_1700_B(p_i232164_1_);
        }

        public final boolean n_1700_B(q_1704_m p_test_1_) {
            return this.J_1907_R.test(p_test_1_);
        }

        @Override
        public void n_1700_B(g_1866_m p_225580_1_) {
            LootItemCondition.super.n_1700_B(p_225580_1_);
            for (int i = 0; i < this.n_1700_B.length; ++i) {
                this.n_1700_B[i].n_1700_B(p_225580_1_.J_1907_R(".term[" + i + "]"));
            }
        }

        @Override
        public o_3000_u J_1907_R() {
            throw new UnsupportedOperationException();
        }

        @Override
        public /* synthetic */ boolean test(Object object) {
            return this.n_1700_B((q_1704_m)object);
        }
    }
}


