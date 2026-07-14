/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.common.io.Files
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParseException
 *  com.google.gson.internal.Streams
 *  com.google.gson.reflect.TypeToken
 *  com.google.gson.stream.JsonReader
 *  com.mojang.datafixers.DataFixer
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.JsonOps
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.A_2629_w;
import lightning.product.B_3068_A;
import lightning.product.B_4088_l;
import lightning.product.C_3304_p;
import lightning.product.F_2904_S;
import lightning.product.SharedConstants;
import lightning.product.ClientboundUpdateAdvancementsPacket;
import lightning.product.CriterionTrigger;
import lightning.product.T_4001_f;
import lightning.product.U_3554_Q;
import lightning.product.Y_408_h;
import lightning.product.ClientboundSelectAdvancementsTabPacket;
import lightning.product.g_1995_W;
import lightning.product.g_2336_b;
import lightning.product.h_1723_G;
import lightning.product.j_3341_s;
import lightning.product.ServerAdvancementManager;
import lightning.product.o_1967_f;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class S_4998_h {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Gson J_1907_R = new GsonBuilder().registerTypeAdapter(C_3304_p.class, (Object)new C_3304_p.n_1700_B()).registerTypeAdapter(g_2336_b.class, (Object)new g_2336_b.n_1700_B()).setPrettyPrinting().create();
    private static final TypeToken<Map<g_2336_b, C_3304_p>> R_4764_Y = new TypeToken<Map<g_2336_b, C_3304_p>>(){};
    private final DataFixer G_564_y;
    private final g_1995_W P_1922_E;
    private final File u_1723_Y;
    private final Map<A_2629_w, C_3304_p> v_4262_N = Maps.newLinkedHashMap();
    private final Set<A_2629_w> w_1484_f = Sets.newLinkedHashSet();
    private final Set<A_2629_w> t_148_a = Sets.newLinkedHashSet();
    private final Set<A_2629_w> s_956_w = Sets.newLinkedHashSet();
    private B_4088_l u_2550_I;
    @Nullable
    private A_2629_w M_588_G;
    private boolean P_4830_p = true;

    public S_4998_h(DataFixer dataFixer, g_1995_W playerList, ServerAdvancementManager advancementManager, File progressFile, B_4088_l player) {
        this.G_564_y = dataFixer;
        this.P_1922_E = playerList;
        this.u_1723_Y = progressFile;
        this.u_2550_I = player;
        this.G_564_y(advancementManager);
    }

    public void n_1700_B(B_4088_l player) {
        this.u_2550_I = player;
    }

    public void n_1700_B() {
        for (CriterionTrigger<?> icriteriontrigger : U_3554_Q.n_1700_B()) {
            icriteriontrigger.n_1700_B(this);
        }
    }

    public void n_1700_B(ServerAdvancementManager manager) {
        this.n_1700_B();
        this.v_4262_N.clear();
        this.w_1484_f.clear();
        this.t_148_a.clear();
        this.s_956_w.clear();
        this.P_4830_p = true;
        this.M_588_G = null;
        this.G_564_y(manager);
    }

    private void J_1907_R(ServerAdvancementManager manager) {
        for (A_2629_w advancement : manager.n_1700_B()) {
            this.R_4764_Y(advancement);
        }
    }

    private void R_4764_Y() {
        ArrayList list = Lists.newArrayList();
        for (Map.Entry<A_2629_w, C_3304_p> entry : this.v_4262_N.entrySet()) {
            if (!entry.getValue().n_1700_B()) continue;
            list.add(entry.getKey());
            this.s_956_w.add(entry.getKey());
        }
        for (A_2629_w advancement : list) {
            this.P_1922_E(advancement);
        }
    }

    private void R_4764_Y(ServerAdvancementManager manager) {
        for (A_2629_w advancement : manager.n_1700_B()) {
            if (!advancement.u_1723_Y().isEmpty()) continue;
            this.n_1700_B(advancement, "");
            advancement.G_564_y().n_1700_B(this.u_2550_I);
        }
    }

    private void G_564_y(ServerAdvancementManager manager) {
        if (this.u_1723_Y.isFile()) {
            try (JsonReader jsonreader = new JsonReader((Reader)new StringReader(Files.toString((File)this.u_1723_Y, (Charset)StandardCharsets.UTF_8)));){
                jsonreader.setLenient(false);
                Dynamic dynamic = new Dynamic((DynamicOps)JsonOps.INSTANCE, (Object)Streams.parse((JsonReader)jsonreader));
                if (!dynamic.get("DataVersion").asNumber().result().isPresent()) {
                    dynamic = dynamic.set("DataVersion", dynamic.createInt(1343));
                }
                dynamic = this.G_564_y.update(o_1967_f.t_148_a.n_1700_B(), dynamic, dynamic.get("DataVersion").asInt(0), SharedConstants.n_1700_B().getWorldVersion());
                dynamic = dynamic.remove("DataVersion");
                Map map = (Map)J_1907_R.getAdapter(R_4764_Y).fromJsonTree((JsonElement)dynamic.getValue());
                if (map == null) {
                    throw new JsonParseException("Found null for advancements");
                }
                Stream<Map.Entry> stream = map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue));
                for (Map.Entry entry : stream.collect(Collectors.toList())) {
                    A_2629_w advancement = manager.n_1700_B((g_2336_b)entry.getKey());
                    if (advancement == null) {
                        n_1700_B.warn("Ignored advancement '{}' in progress file {} - it doesn't exist anymore?", entry.getKey(), (Object)this.u_1723_Y);
                        continue;
                    }
                    this.n_1700_B(advancement, (C_3304_p)entry.getValue());
                }
            }
            catch (JsonParseException jsonparseexception) {
                n_1700_B.error("Couldn't parse player advancements in {}", (Object)this.u_1723_Y, (Object)jsonparseexception);
            }
            catch (IOException ioexception) {
                n_1700_B.error("Couldn't access player advancements in {}", (Object)this.u_1723_Y, (Object)ioexception);
            }
        }
        this.R_4764_Y(manager);
        this.R_4764_Y();
        this.J_1907_R(manager);
    }

    public void J_1907_R() {
        HashMap map = Maps.newHashMap();
        for (Map.Entry<A_2629_w, C_3304_p> entry : this.v_4262_N.entrySet()) {
            C_3304_p advancementprogress = entry.getValue();
            if (!advancementprogress.J_1907_R()) continue;
            map.put(entry.getKey().w_1484_f(), advancementprogress);
        }
        if (this.u_1723_Y.getParentFile() != null) {
            this.u_1723_Y.getParentFile().mkdirs();
        }
        JsonElement jsonelement = J_1907_R.toJsonTree((Object)map);
        jsonelement.getAsJsonObject().addProperty("DataVersion", (Number)SharedConstants.n_1700_B().getWorldVersion());
        try (FileOutputStream outputstream = new FileOutputStream(this.u_1723_Y);
             OutputStreamWriter writer = new OutputStreamWriter((OutputStream)outputstream, Charsets.UTF_8.newEncoder());){
            J_1907_R.toJson(jsonelement, (Appendable)writer);
        }
        catch (IOException ioexception) {
            n_1700_B.error("Couldn't save player advancements to {}", (Object)this.u_1723_Y, (Object)ioexception);
        }
    }

    public boolean n_1700_B(A_2629_w advancementIn, String criterionKey) {
        boolean flag = false;
        C_3304_p advancementprogress = this.J_1907_R(advancementIn);
        boolean flag1 = advancementprogress.n_1700_B();
        if (advancementprogress.n_1700_B(criterionKey)) {
            this.G_564_y(advancementIn);
            this.s_956_w.add(advancementIn);
            flag = true;
            if (!flag1 && advancementprogress.n_1700_B()) {
                advancementIn.G_564_y().n_1700_B(this.u_2550_I);
                if (advancementIn.R_4764_Y() != null && advancementIn.R_4764_Y().t_148_a() && this.u_2550_I.O_508_d.H_1990_U().J_1907_R(A_2352_Z.C_2741_M)) {
                    this.P_1922_E.n_1700_B(new F_2904_S("chat.type.advancement." + advancementIn.R_4764_Y().P_1922_E().n_1700_B(), this.u_2550_I.c_(), advancementIn.s_956_w()), Y_408_h.J_1907_R, j_3341_s.J_1907_R);
                }
            }
        }
        if (advancementprogress.n_1700_B()) {
            this.P_1922_E(advancementIn);
        }
        return flag;
    }

    public boolean J_1907_R(A_2629_w advancementIn, String criterionKey) {
        boolean flag = false;
        C_3304_p advancementprogress = this.J_1907_R(advancementIn);
        if (advancementprogress.J_1907_R(criterionKey)) {
            this.R_4764_Y(advancementIn);
            this.s_956_w.add(advancementIn);
            flag = true;
        }
        if (!advancementprogress.J_1907_R()) {
            this.P_1922_E(advancementIn);
        }
        return flag;
    }

    private void R_4764_Y(A_2629_w advancementIn) {
        C_3304_p advancementprogress = this.J_1907_R(advancementIn);
        if (!advancementprogress.n_1700_B()) {
            for (Map.Entry<String, T_4001_f> entry : advancementIn.u_1723_Y().entrySet()) {
                CriterionTrigger<h_1723_G> icriteriontrigger;
                h_1723_G icriterioninstance;
                B_3068_A criterionprogress = advancementprogress.R_4764_Y(entry.getKey());
                if (criterionprogress == null || criterionprogress.n_1700_B() || (icriterioninstance = entry.getValue().n_1700_B()) == null || (icriteriontrigger = U_3554_Q.n_1700_B(icriterioninstance.n_1700_B())) == null) continue;
                icriteriontrigger.n_1700_B(this, new CriterionTrigger.n_1700_B<h_1723_G>(icriterioninstance, advancementIn, entry.getKey()));
            }
        }
    }

    private void G_564_y(A_2629_w advancementIn) {
        C_3304_p advancementprogress = this.J_1907_R(advancementIn);
        for (Map.Entry<String, T_4001_f> entry : advancementIn.u_1723_Y().entrySet()) {
            CriterionTrigger<h_1723_G> icriteriontrigger;
            h_1723_G icriterioninstance;
            B_3068_A criterionprogress = advancementprogress.R_4764_Y(entry.getKey());
            if (criterionprogress == null || !criterionprogress.n_1700_B() && !advancementprogress.n_1700_B() || (icriterioninstance = entry.getValue().n_1700_B()) == null || (icriteriontrigger = U_3554_Q.n_1700_B(icriterioninstance.n_1700_B())) == null) continue;
            icriteriontrigger.J_1907_R(this, new CriterionTrigger.n_1700_B<h_1723_G>(icriterioninstance, advancementIn, entry.getKey()));
        }
    }

    public void J_1907_R(B_4088_l serverPlayer) {
        if (this.P_4830_p || !this.t_148_a.isEmpty() || !this.s_956_w.isEmpty()) {
            HashMap map = Maps.newHashMap();
            LinkedHashSet set = Sets.newLinkedHashSet();
            LinkedHashSet set1 = Sets.newLinkedHashSet();
            for (A_2629_w advancement : this.s_956_w) {
                if (!this.w_1484_f.contains(advancement)) continue;
                map.put(advancement.w_1484_f(), this.v_4262_N.get(advancement));
            }
            for (A_2629_w advancement1 : this.t_148_a) {
                if (this.w_1484_f.contains(advancement1)) {
                    set.add(advancement1);
                    continue;
                }
                set1.add(advancement1.w_1484_f());
            }
            if (this.P_4830_p || !map.isEmpty() || !set.isEmpty() || !set1.isEmpty()) {
                serverPlayer.n_1700_B.n_1700_B(new ClientboundUpdateAdvancementsPacket(this.P_4830_p, set, set1, map));
                this.t_148_a.clear();
                this.s_956_w.clear();
            }
        }
        this.P_4830_p = false;
    }

    public void n_1700_B(@Nullable A_2629_w advancementIn) {
        A_2629_w advancement = this.M_588_G;
        this.M_588_G = advancementIn != null && advancementIn.J_1907_R() == null && advancementIn.R_4764_Y() != null ? advancementIn : null;
        if (advancement != this.M_588_G) {
            this.u_2550_I.n_1700_B.n_1700_B(new ClientboundSelectAdvancementsTabPacket(this.M_588_G == null ? null : this.M_588_G.w_1484_f()));
        }
    }

    public C_3304_p J_1907_R(A_2629_w advancementIn) {
        C_3304_p advancementprogress = this.v_4262_N.get(advancementIn);
        if (advancementprogress == null) {
            advancementprogress = new C_3304_p();
            this.n_1700_B(advancementIn, advancementprogress);
        }
        return advancementprogress;
    }

    private void n_1700_B(A_2629_w advancementIn, C_3304_p progress) {
        progress.n_1700_B(advancementIn.u_1723_Y(), advancementIn.t_148_a());
        this.v_4262_N.put(advancementIn, progress);
    }

    private void P_1922_E(A_2629_w advancementIn) {
        boolean flag = this.u_1723_Y(advancementIn);
        boolean flag1 = this.w_1484_f.contains(advancementIn);
        if (flag && !flag1) {
            this.w_1484_f.add(advancementIn);
            this.t_148_a.add(advancementIn);
            if (this.v_4262_N.containsKey(advancementIn)) {
                this.s_956_w.add(advancementIn);
            }
        } else if (!flag && flag1) {
            this.w_1484_f.remove(advancementIn);
            this.t_148_a.add(advancementIn);
        }
        if (flag != flag1 && advancementIn.J_1907_R() != null) {
            this.P_1922_E(advancementIn.J_1907_R());
        }
        for (A_2629_w advancement : advancementIn.P_1922_E()) {
            this.P_1922_E(advancement);
        }
    }

    private boolean u_1723_Y(A_2629_w advancement) {
        for (int i = 0; advancement != null && i <= 2; advancement = advancement.J_1907_R(), ++i) {
            if (i == 0 && this.v_4262_N(advancement)) {
                return true;
            }
            if (advancement.R_4764_Y() == null) {
                return false;
            }
            C_3304_p advancementprogress = this.J_1907_R(advancement);
            if (advancementprogress.n_1700_B()) {
                return true;
            }
            if (!advancement.R_4764_Y().s_956_w()) continue;
            return false;
        }
        return false;
    }

    private boolean v_4262_N(A_2629_w advancementIn) {
        C_3304_p advancementprogress = this.J_1907_R(advancementIn);
        if (advancementprogress.n_1700_B()) {
            return true;
        }
        for (A_2629_w advancement : advancementIn.P_1922_E()) {
            if (!this.v_4262_N(advancement)) continue;
            return true;
        }
        return false;
    }
}


