/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Sets
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import lightning.product.A_2629_w;
import lightning.product.J_1617_l;
import lightning.product.J_950_c;
import lightning.product.P_4711_N;
import lightning.product.t_113_v;
import lightning.product.t_1512_m;
import net.minecraft.data.M_182_A;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.Y_259_p;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class n_1700_B
implements Y_259_p {
    private static final Logger J_1907_R = LogManager.getLogger();
    private static final Gson R_4764_Y = new GsonBuilder().setPrettyPrinting().create();
    private final Q_4569_t G_564_y;
    private final List<Consumer<Consumer<A_2629_w>>> P_1922_E = ImmutableList.of((Object)new t_113_v(), (Object)new t_1512_m(), (Object)new P_4711_N(), (Object)new J_1617_l(), (Object)new J_950_c());

    public n_1700_B(Q_4569_t generatorIn) {
        this.G_564_y = generatorIn;
    }

    @Override
    public void n_1700_B(M_182_A cache) throws IOException {
        Path path = this.G_564_y.J_1907_R();
        HashSet set = Sets.newHashSet();
        Consumer<A_2629_w> consumer = advancement -> {
            if (!set.add(advancement.w_1484_f())) {
                throw new IllegalStateException("Duplicate advancement " + String.valueOf(advancement.w_1484_f()));
            }
            Path path1 = n_1700_B.n_1700_B(path, advancement);
            try {
                Y_259_p.n_1700_B(R_4764_Y, cache, (JsonElement)advancement.n_1700_B().J_1907_R(), path1);
            }
            catch (IOException ioexception) {
                J_1907_R.error("Couldn't save advancement {}", (Object)path1, (Object)ioexception);
            }
        };
        for (Consumer<Consumer<A_2629_w>> consumer1 : this.P_1922_E) {
            consumer1.accept(consumer);
        }
    }

    private static Path n_1700_B(Path pathIn, A_2629_w advancementIn) {
        return pathIn.resolve("data/" + advancementIn.w_1484_f().R_4764_Y() + "/advancements/" + advancementIn.w_1484_f().J_1907_R() + ".json");
    }

    @Override
    public String n_1700_B() {
        return "Advancements";
    }
}

