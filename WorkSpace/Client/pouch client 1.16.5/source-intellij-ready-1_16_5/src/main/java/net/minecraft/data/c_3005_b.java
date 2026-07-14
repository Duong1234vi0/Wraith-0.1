/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Multimap
 *  com.google.common.collect.Sets
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.mojang.datafixers.util.Pair
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.datafixers.util.Pair;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lightning.product.GiftLoot;
import lightning.product.I_2176_d;
import lightning.product.I_3789_O;
import lightning.product.PiglinBarterLoot;
import lightning.product.W_4589_Q;
import lightning.product.FishingLoot;
import lightning.product.f_1402_I;
import lightning.product.f_4186_T;
import lightning.product.g_1866_m;
import lightning.product.g_2336_b;
import lightning.product.n_4365_u;
import lightning.product.o_4810_o;
import lightning.product.p_4985_U;
import net.minecraft.data.M_182_A;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.Y_259_p;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class c_3005_b
implements Y_259_p {
    private static final Logger J_1907_R = LogManager.getLogger();
    private static final Gson R_4764_Y = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final Q_4569_t G_564_y;
    private final List<Pair<Supplier<Consumer<BiConsumer<g_2336_b, p_4985_U.n_1700_B>>>, I_2176_d>> P_1922_E = ImmutableList.of((Object)Pair.of(FishingLoot::new, (Object)f_1402_I.P_1922_E), (Object)Pair.of(W_4589_Q::new, (Object)f_1402_I.J_1907_R), (Object)Pair.of(I_3789_O::new, (Object)f_1402_I.u_1723_Y), (Object)Pair.of(n_4365_u::new, (Object)f_1402_I.M_588_G), (Object)Pair.of(PiglinBarterLoot::new, (Object)f_1402_I.w_1484_f), (Object)Pair.of(GiftLoot::new, (Object)f_1402_I.v_4262_N));

    public c_3005_b(Q_4569_t dataGeneratorIn) {
        this.G_564_y = dataGeneratorIn;
    }

    @Override
    public void n_1700_B(M_182_A cache) {
        Path path = this.G_564_y.J_1907_R();
        HashMap map = Maps.newHashMap();
        this.P_1922_E.forEach(p_218438_1_ -> ((Consumer)((Supplier)p_218438_1_.getFirst()).get()).accept((p_218437_2_, p_218437_3_) -> {
            if (map.put(p_218437_2_, p_218437_3_.n_1700_B((I_2176_d)p_218438_1_.getSecond()).J_1907_R()) != null) {
                throw new IllegalStateException("Duplicate loot table " + String.valueOf(p_218437_2_));
            }
        }));
        g_1866_m validationtracker = new g_1866_m(f_1402_I.u_2550_I, p_229442_0_ -> null, map::get);
        for (g_2336_b resourcelocation : Sets.difference(o_4810_o.n_1700_B(), map.keySet())) {
            validationtracker.n_1700_B("Missing built-in table: " + String.valueOf(resourcelocation));
        }
        map.forEach((p_229439_1_, p_229439_2_) -> f_4186_T.n_1700_B(validationtracker, p_229439_1_, p_229439_2_));
        Multimap<String, String> multimap = validationtracker.n_1700_B();
        if (!multimap.isEmpty()) {
            multimap.forEach((p_229440_0_, p_229440_1_) -> J_1907_R.warn("Found validation problem in " + p_229440_0_ + ": " + p_229440_1_));
            throw new IllegalStateException("Failed to validate loot tables, see logs");
        }
        map.forEach((p_229441_2_, p_229441_3_) -> {
            Path path1 = c_3005_b.n_1700_B(path, p_229441_2_);
            try {
                Y_259_p.n_1700_B(R_4764_Y, cache, f_4186_T.n_1700_B(p_229441_3_), path1);
            }
            catch (IOException ioexception) {
                J_1907_R.error("Couldn't save loot table {}", (Object)path1, (Object)ioexception);
            }
        });
    }

    private static Path n_1700_B(Path pathIn, g_2336_b id) {
        return pathIn.resolve("data/" + id.R_4764_Y() + "/loot_tables/" + id.J_1907_R() + ".json");
    }

    @Override
    public String n_1700_B() {
        return "LootTables";
    }
}


