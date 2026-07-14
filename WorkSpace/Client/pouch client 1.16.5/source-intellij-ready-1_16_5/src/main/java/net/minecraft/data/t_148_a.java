/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.q_1613_l;
import net.minecraft.data.A_4115_X;
import net.minecraft.data.M_182_A;
import net.minecraft.data.Q_2552_b;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.Y_259_p;
import net.minecraft.data.q_2307_F;
import net.minecraft.data.v_4262_N;
import net.minecraft.data.w_1484_f;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class t_148_a
implements Y_259_p {
    private static final Logger J_1907_R = LogManager.getLogger();
    private static final Gson R_4764_Y = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final Q_4569_t G_564_y;

    public t_148_a(Q_4569_t p_i232520_1_) {
        this.G_564_y = p_i232520_1_;
    }

    @Override
    public void n_1700_B(M_182_A cache) {
        Path path = this.G_564_y.J_1907_R();
        HashMap map = Maps.newHashMap();
        Consumer<Q_2552_b> consumer = p_240085_1_ -> {
            T_2915_h block = p_240085_1_.n_1700_B();
            Q_2552_b ifinishedblockstate = map.put(block, p_240085_1_);
            if (ifinishedblockstate != null) {
                throw new IllegalStateException("Duplicate blockstate definition for " + String.valueOf(block));
            }
        };
        HashMap map1 = Maps.newHashMap();
        HashSet set = Sets.newHashSet();
        BiConsumer<g_2336_b, Supplier<JsonElement>> biconsumer = (p_240086_1_, p_240086_2_) -> {
            Supplier supplier = map1.put(p_240086_1_, p_240086_2_);
            if (supplier != null) {
                throw new IllegalStateException("Duplicate model definition for " + String.valueOf(p_240086_1_));
            }
        };
        Consumer<q_1613_l> consumer1 = set::add;
        new v_4262_N(consumer, biconsumer, consumer1).n_1700_B();
        new q_2307_F(biconsumer).n_1700_B();
        List list = V_3137_a.q_4610_l.u_1723_Y().filter(p_240084_1_ -> !map.containsKey(p_240084_1_)).collect(Collectors.toList());
        if (!list.isEmpty()) {
            throw new IllegalStateException("Missing blockstate definitions for: " + String.valueOf(list));
        }
        V_3137_a.q_4610_l.forEach(p_240087_2_ -> {
            q_1613_l item = q_1613_l.P_1922_E.get(p_240087_2_);
            if (item != null) {
                if (set.contains(item)) {
                    return;
                }
                g_2336_b resourcelocation = A_4115_X.n_1700_B(item);
                if (!map1.containsKey(resourcelocation)) {
                    map1.put(resourcelocation, new w_1484_f(A_4115_X.n_1700_B(p_240087_2_)));
                }
            }
        });
        this.n_1700_B(cache, path, map, t_148_a::n_1700_B);
        this.n_1700_B(cache, path, map1, t_148_a::n_1700_B);
    }

    private <T> void n_1700_B(M_182_A p_240081_1_, Path p_240081_2_, Map<T, ? extends Supplier<JsonElement>> p_240081_3_, BiFunction<Path, T, Path> p_240081_4_) {
        p_240081_3_.forEach((p_240088_3_, p_240088_4_) -> {
            Path path = (Path)p_240081_4_.apply(p_240081_2_, p_240088_3_);
            try {
                Y_259_p.n_1700_B(R_4764_Y, p_240081_1_, (JsonElement)p_240088_4_.get(), path);
            }
            catch (Exception exception) {
                J_1907_R.error("Couldn't save {}", (Object)path, (Object)exception);
            }
        });
    }

    private static Path n_1700_B(Path p_240082_0_, T_2915_h p_240082_1_) {
        g_2336_b resourcelocation = V_3137_a.q_4610_l.J_1907_R(p_240082_1_);
        return p_240082_0_.resolve("assets/" + resourcelocation.R_4764_Y() + "/blockstates/" + resourcelocation.J_1907_R() + ".json");
    }

    private static Path n_1700_B(Path p_240083_0_, g_2336_b p_240083_1_) {
        return p_240083_0_.resolve("assets/" + p_240083_1_.R_4764_Y() + "/models/" + p_240083_1_.J_1907_R() + ".json");
    }

    @Override
    public String n_1700_B() {
        return "Block State Definitions";
    }
}

