/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.JsonOps
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import lightning.product.BuiltinRegistries;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.k_594_Q;
import net.minecraft.data.M_182_A;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.Y_259_p;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class J_1907_R
implements Y_259_p {
    private static final Logger J_1907_R = LogManager.getLogger();
    private static final Gson R_4764_Y = new GsonBuilder().setPrettyPrinting().create();
    private final Q_4569_t G_564_y;

    public J_1907_R(Q_4569_t generator) {
        this.G_564_y = generator;
    }

    @Override
    public void n_1700_B(M_182_A cache) {
        Path path = this.G_564_y.J_1907_R();
        for (Map.Entry<f_2392_k<k_594_Q>, k_594_Q> entry : BuiltinRegistries.t_148_a.P_1922_E()) {
            Path path1 = net.minecraft.data.J_1907_R.n_1700_B(path, entry.getKey().n_1700_B());
            k_594_Q biome = entry.getValue();
            Function function = JsonOps.INSTANCE.withEncoder(k_594_Q.G_564_y);
            try {
                Optional optional = ((DataResult)function.apply(() -> biome)).result();
                if (optional.isPresent()) {
                    Y_259_p.n_1700_B(R_4764_Y, cache, (JsonElement)optional.get(), path1);
                    continue;
                }
                J_1907_R.error("Couldn't serialize biome {}", (Object)path1);
            }
            catch (IOException ioexception) {
                J_1907_R.error("Couldn't save biome {}", (Object)path1, (Object)ioexception);
            }
        }
    }

    private static Path n_1700_B(Path path, g_2336_b biomeLocation) {
        return path.resolve("reports/biomes/" + biomeLocation.J_1907_R() + ".json");
    }

    @Override
    public String n_1700_B() {
        return "Biomes";
    }
}


