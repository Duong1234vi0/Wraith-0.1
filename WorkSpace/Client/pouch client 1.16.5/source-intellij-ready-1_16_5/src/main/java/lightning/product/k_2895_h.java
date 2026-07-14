/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.internal.Streams
 *  com.google.gson.stream.JsonReader
 *  com.mojang.datafixers.DataFixer
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  org.apache.commons.io.FileUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.mojang.datafixers.DataFixer;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lightning.product.B_4088_l;
import lightning.product.SharedConstants;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.a_3913_L;
import lightning.product.StatsCounter;
import lightning.product.g_2336_b;
import lightning.product.ClientboundAwardStatsPacket;
import lightning.product.j_3341_s;
import lightning.product.n_3832_I;
import lightning.product.o_1967_f;
import lightning.product.o_98_P;
import lightning.product.q_3277_O;
import net.minecraft.server.G_564_y;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class k_2895_h
extends StatsCounter {
    private static final Logger J_1907_R = LogManager.getLogger();
    private final G_564_y R_4764_Y;
    private final File G_564_y;
    private final Set<o_98_P<?>> P_1922_E = Sets.newHashSet();
    private int u_1723_Y = -300;

    public k_2895_h(G_564_y serverIn, File statsFileIn) {
        this.R_4764_Y = serverIn;
        this.G_564_y = statsFileIn;
        if (statsFileIn.isFile()) {
            try {
                this.n_1700_B(serverIn.M_1641_O(), FileUtils.readFileToString((File)statsFileIn));
            }
            catch (IOException ioexception) {
                J_1907_R.error("Couldn't read statistics file {}", (Object)statsFileIn, (Object)ioexception);
            }
            catch (JsonParseException jsonparseexception) {
                J_1907_R.error("Couldn't parse statistics file {}", (Object)statsFileIn, (Object)jsonparseexception);
            }
        }
    }

    public void n_1700_B() {
        try {
            FileUtils.writeStringToFile((File)this.G_564_y, (String)this.J_1907_R());
        }
        catch (IOException ioexception) {
            J_1907_R.error("Couldn't save stats", (Throwable)ioexception);
        }
    }

    @Override
    public void n_1700_B(a_3913_L playerIn, o_98_P<?> statIn, int p_150873_3_) {
        super.n_1700_B(playerIn, statIn, p_150873_3_);
        this.P_1922_E.add(statIn);
    }

    private Set<o_98_P<?>> G_564_y() {
        HashSet set = Sets.newHashSet(this.P_1922_E);
        this.P_1922_E.clear();
        return set;
    }

    public void n_1700_B(DataFixer p_199062_1_, String p_199062_2_) {
        try (JsonReader jsonreader = new JsonReader((Reader)new StringReader(p_199062_2_));){
            jsonreader.setLenient(false);
            JsonElement jsonelement = Streams.parse((JsonReader)jsonreader);
            if (jsonelement.isJsonNull()) {
                J_1907_R.error("Unable to parse Stat data from {}", (Object)this.G_564_y);
                return;
            }
            U_2912_j compoundnbt = k_2895_h.n_1700_B(jsonelement.getAsJsonObject());
            if (!compoundnbt.R_4764_Y("DataVersion", 99)) {
                compoundnbt.J_1907_R("DataVersion", 1343);
            }
            if ((compoundnbt = n_3832_I.n_1700_B(p_199062_1_, o_1967_f.v_4262_N, compoundnbt, compoundnbt.w_1484_f("DataVersion"))).R_4764_Y("stats", 10)) {
                U_2912_j compoundnbt1 = compoundnbt.M_182_A("stats");
                for (String s : compoundnbt1.G_564_y()) {
                    if (!compoundnbt1.R_4764_Y(s, 10)) continue;
                    j_3341_s.n_1700_B(V_3137_a.z_1333_t.J_1907_R(new g_2336_b(s)), p_219731_3_ -> {
                        U_2912_j compoundnbt2 = compoundnbt1.M_182_A(s);
                        for (String s1 : compoundnbt2.G_564_y()) {
                            if (compoundnbt2.R_4764_Y(s1, 99)) {
                                j_3341_s.n_1700_B(this.n_1700_B((q_3277_O)p_219731_3_, s1), p_219730_3_ -> this.n_1700_B.put(p_219730_3_, compoundnbt2.w_1484_f(s1)), () -> J_1907_R.warn("Invalid statistic in {}: Don't know what {} is", (Object)this.G_564_y, (Object)s1));
                                continue;
                            }
                            J_1907_R.warn("Invalid statistic value in {}: Don't know what {} is for key {}", (Object)this.G_564_y, (Object)compoundnbt2.R_4764_Y(s1), (Object)s1);
                        }
                    }, () -> J_1907_R.warn("Invalid statistic type in {}: Don't know what {} is", (Object)this.G_564_y, (Object)s));
                }
            }
        }
        catch (JsonParseException | IOException jsonparseexception) {
            J_1907_R.error("Unable to parse Stat data from {}", (Object)this.G_564_y, (Object)jsonparseexception);
        }
    }

    private <T> Optional<o_98_P<T>> n_1700_B(q_3277_O<T> p_219728_1_, String p_219728_2_) {
        return Optional.ofNullable(g_2336_b.J_1907_R(p_219728_2_)).flatMap(p_219728_1_.n_1700_B()::J_1907_R).map(p_219728_1_::J_1907_R);
    }

    private static U_2912_j n_1700_B(JsonObject p_199065_0_) {
        U_2912_j compoundnbt = new U_2912_j();
        for (Map.Entry entry : p_199065_0_.entrySet()) {
            JsonPrimitive jsonprimitive;
            JsonElement jsonelement = (JsonElement)entry.getValue();
            if (jsonelement.isJsonObject()) {
                compoundnbt.n_1700_B((String)entry.getKey(), k_2895_h.n_1700_B(jsonelement.getAsJsonObject()));
                continue;
            }
            if (!jsonelement.isJsonPrimitive() || !(jsonprimitive = jsonelement.getAsJsonPrimitive()).isNumber()) continue;
            compoundnbt.J_1907_R((String)entry.getKey(), jsonprimitive.getAsInt());
        }
        return compoundnbt;
    }

    protected String J_1907_R() {
        HashMap map = Maps.newHashMap();
        for (Object entry : this.n_1700_B.object2IntEntrySet()) {
            o_98_P o_98_P2 = (o_98_P)entry.getKey();
            map.computeIfAbsent(o_98_P2.G_564_y(), p_199064_0_ -> new JsonObject()).addProperty(k_2895_h.J_1907_R(o_98_P2).toString(), (Number)entry.getIntValue());
        }
        JsonObject jsonobject = new JsonObject();
        for (Map.Entry entry : map.entrySet()) {
            jsonobject.add(V_3137_a.z_1333_t.J_1907_R((q_3277_O)entry.getKey()).toString(), (JsonElement)entry.getValue());
        }
        JsonObject jsonobject1 = new JsonObject();
        jsonobject1.add("stats", (JsonElement)jsonobject);
        jsonobject1.addProperty("DataVersion", (Number)SharedConstants.n_1700_B().getWorldVersion());
        return jsonobject1.toString();
    }

    private static <T> g_2336_b J_1907_R(o_98_P<T> p_199066_0_) {
        return p_199066_0_.G_564_y().n_1700_B().J_1907_R(p_199066_0_.P_1922_E());
    }

    public void R_4764_Y() {
        this.P_1922_E.addAll((Collection<o_98_P<?>>)this.n_1700_B.keySet());
    }

    public void n_1700_B(B_4088_l player) {
        int i = this.R_4764_Y.e_1992_r();
        Object2IntOpenHashMap object2intmap = new Object2IntOpenHashMap();
        if (i - this.u_1723_Y > 300) {
            this.u_1723_Y = i;
            for (o_98_P<?> stat : this.G_564_y()) {
                object2intmap.put(stat, this.n_1700_B(stat));
            }
        }
        player.n_1700_B.n_1700_B(new ClientboundAwardStatsPacket((Object2IntMap<o_98_P<?>>)object2intmap));
    }
}


