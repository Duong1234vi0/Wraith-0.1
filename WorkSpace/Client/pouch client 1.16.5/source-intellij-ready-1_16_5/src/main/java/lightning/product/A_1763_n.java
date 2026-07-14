/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Maps
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.longs.LongArrayList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.StructureFeature;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.StructureFeatureIndexSavedData;
import lightning.product.b_4507_u;
import lightning.product.f_2392_k;
import lightning.product.j_3341_s;
import lightning.product.q_2896_o;
import lightning.product.s_4380_l;

public class A_1763_n {
    private static final Map<String, String> n_1700_B = j_3341_s.n_1700_B(Maps.newHashMap(), (T p_208213_0_) -> {
        p_208213_0_.put("Village", "Village");
        p_208213_0_.put("Mineshaft", "Mineshaft");
        p_208213_0_.put("Mansion", "Mansion");
        p_208213_0_.put("Igloo", "Temple");
        p_208213_0_.put("Desert_Pyramid", "Temple");
        p_208213_0_.put("Jungle_Pyramid", "Temple");
        p_208213_0_.put("Swamp_Hut", "Temple");
        p_208213_0_.put("Stronghold", "Stronghold");
        p_208213_0_.put("Monument", "Monument");
        p_208213_0_.put("Fortress", "Fortress");
        p_208213_0_.put("EndCity", "EndCity");
    });
    private static final Map<String, String> J_1907_R = j_3341_s.n_1700_B(Maps.newHashMap(), (T p_208215_0_) -> {
        p_208215_0_.put("Iglu", "Igloo");
        p_208215_0_.put("TeDP", "Desert_Pyramid");
        p_208215_0_.put("TeJP", "Jungle_Pyramid");
        p_208215_0_.put("TeSH", "Swamp_Hut");
    });
    private final boolean R_4764_Y;
    private final Map<String, Long2ObjectMap<U_2912_j>> G_564_y = Maps.newHashMap();
    private final Map<String, StructureFeatureIndexSavedData> P_1922_E = Maps.newHashMap();
    private final List<String> u_1723_Y;
    private final List<String> v_4262_N;

    public A_1763_n(@Nullable s_4380_l p_i51349_1_, List<String> p_i51349_2_, List<String> p_i51349_3_) {
        this.u_1723_Y = p_i51349_2_;
        this.v_4262_N = p_i51349_3_;
        this.n_1700_B(p_i51349_1_);
        boolean flag = false;
        for (String s : this.v_4262_N) {
            flag |= this.G_564_y.get(s) != null;
        }
        this.R_4764_Y = flag;
    }

    public void n_1700_B(long p_208216_1_) {
        for (String s : this.u_1723_Y) {
            StructureFeatureIndexSavedData structureindexessaveddata = this.P_1922_E.get(s);
            if (structureindexessaveddata == null || !structureindexessaveddata.R_4764_Y(p_208216_1_)) continue;
            structureindexessaveddata.G_564_y(p_208216_1_);
            structureindexessaveddata.R_4764_Y();
        }
    }

    public U_2912_j n_1700_B(U_2912_j p_212181_1_) {
        U_2912_j compoundnbt = p_212181_1_.M_182_A("Level");
        Y_1387_d chunkpos = new Y_1387_d(compoundnbt.w_1484_f("xPos"), compoundnbt.w_1484_f("zPos"));
        if (this.n_1700_B(chunkpos.J_1907_R, chunkpos.R_4764_Y)) {
            p_212181_1_ = this.n_1700_B(p_212181_1_, chunkpos);
        }
        U_2912_j compoundnbt1 = compoundnbt.M_182_A("Structures");
        U_2912_j compoundnbt2 = compoundnbt1.M_182_A("References");
        for (String s : this.v_4262_N) {
            StructureFeature structure = (StructureFeature)StructureFeature.n_1700_B.get((Object)s.toLowerCase(Locale.ROOT));
            if (compoundnbt2.R_4764_Y(s, 12) || structure == null) continue;
            int i = 8;
            LongArrayList longlist = new LongArrayList();
            for (int j = chunkpos.J_1907_R - 8; j <= chunkpos.J_1907_R + 8; ++j) {
                for (int k = chunkpos.R_4764_Y - 8; k <= chunkpos.R_4764_Y + 8; ++k) {
                    if (!this.n_1700_B(j, k, s)) continue;
                    longlist.add(Y_1387_d.n_1700_B(j, k));
                }
            }
            compoundnbt2.J_1907_R(s, (List<Long>)longlist);
        }
        compoundnbt1.n_1700_B("References", compoundnbt2);
        compoundnbt.n_1700_B("Structures", compoundnbt1);
        p_212181_1_.n_1700_B("Level", compoundnbt);
        return p_212181_1_;
    }

    private boolean n_1700_B(int p_208211_1_, int p_208211_2_, String p_208211_3_) {
        if (!this.R_4764_Y) {
            return false;
        }
        return this.G_564_y.get(p_208211_3_) != null && this.P_1922_E.get(n_1700_B.get(p_208211_3_)).J_1907_R(Y_1387_d.n_1700_B(p_208211_1_, p_208211_2_));
    }

    private boolean n_1700_B(int p_208209_1_, int p_208209_2_) {
        if (!this.R_4764_Y) {
            return false;
        }
        for (String s : this.v_4262_N) {
            if (this.G_564_y.get(s) == null || !this.P_1922_E.get(n_1700_B.get(s)).R_4764_Y(Y_1387_d.n_1700_B(p_208209_1_, p_208209_2_))) continue;
            return true;
        }
        return false;
    }

    private U_2912_j n_1700_B(U_2912_j p_212182_1_, Y_1387_d p_212182_2_) {
        U_2912_j compoundnbt = p_212182_1_.M_182_A("Level");
        U_2912_j compoundnbt1 = compoundnbt.M_182_A("Structures");
        U_2912_j compoundnbt2 = compoundnbt1.M_182_A("Starts");
        for (String s : this.v_4262_N) {
            U_2912_j compoundnbt3;
            Long2ObjectMap<U_2912_j> long2objectmap = this.G_564_y.get(s);
            if (long2objectmap == null) continue;
            long i = p_212182_2_.n_1700_B();
            if (!this.P_1922_E.get(n_1700_B.get(s)).R_4764_Y(i) || (compoundnbt3 = (U_2912_j)long2objectmap.get(i)) == null) continue;
            compoundnbt2.n_1700_B(s, compoundnbt3);
        }
        compoundnbt1.n_1700_B("Starts", compoundnbt2);
        compoundnbt.n_1700_B("Structures", compoundnbt1);
        p_212182_1_.n_1700_B("Level", compoundnbt);
        return p_212182_1_;
    }

    private void n_1700_B(@Nullable s_4380_l p_212184_1_) {
        if (p_212184_1_ != null) {
            for (String s : this.u_1723_Y) {
                U_2912_j compoundnbt = new U_2912_j();
                try {
                    compoundnbt = p_212184_1_.n_1700_B(s, 1493).M_182_A("data").M_182_A("Features");
                    if (compoundnbt.u_1723_Y()) {
                        continue;
                    }
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                for (String s1 : compoundnbt.G_564_y()) {
                    String s3;
                    String s4;
                    U_2912_j compoundnbt1 = compoundnbt.M_182_A(s1);
                    long i = Y_1387_d.n_1700_B(compoundnbt1.w_1484_f("ChunkX"), compoundnbt1.w_1484_f("ChunkZ"));
                    q_2896_o listnbt = compoundnbt1.G_564_y("Children", 10);
                    if (!listnbt.isEmpty() && (s4 = J_1907_R.get(s3 = listnbt.n_1700_B(0).M_588_G("id"))) != null) {
                        compoundnbt1.n_1700_B("id", s4);
                    }
                    String s6 = compoundnbt1.M_588_G("id");
                    this.G_564_y.computeIfAbsent(s6, p_208208_0_ -> new Long2ObjectOpenHashMap()).put(i, (Object)compoundnbt1);
                }
                String s5 = s + "_index";
                StructureFeatureIndexSavedData structureindexessaveddata = p_212184_1_.n_1700_B(() -> new StructureFeatureIndexSavedData(s5), s5);
                if (!structureindexessaveddata.n_1700_B().isEmpty()) {
                    this.P_1922_E.put(s, structureindexessaveddata);
                    continue;
                }
                StructureFeatureIndexSavedData structureindexessaveddata1 = new StructureFeatureIndexSavedData(s5);
                this.P_1922_E.put(s, structureindexessaveddata1);
                for (String s2 : compoundnbt.G_564_y()) {
                    U_2912_j compoundnbt2 = compoundnbt.M_182_A(s2);
                    structureindexessaveddata1.n_1700_B(Y_1387_d.n_1700_B(compoundnbt2.w_1484_f("ChunkX"), compoundnbt2.w_1484_f("ChunkZ")));
                }
                structureindexessaveddata1.R_4764_Y();
            }
        }
    }

    public static A_1763_n n_1700_B(f_2392_k<b_4507_u> p_236992_0_, @Nullable s_4380_l p_236992_1_) {
        if (p_236992_0_ == b_4507_u.u_1723_Y) {
            return new A_1763_n(p_236992_1_, (List<String>)ImmutableList.of((Object)"Monument", (Object)"Stronghold", (Object)"Village", (Object)"Mineshaft", (Object)"Temple", (Object)"Mansion"), (List<String>)ImmutableList.of((Object)"Village", (Object)"Mineshaft", (Object)"Mansion", (Object)"Igloo", (Object)"Desert_Pyramid", (Object)"Jungle_Pyramid", (Object)"Swamp_Hut", (Object)"Stronghold", (Object)"Monument"));
        }
        if (p_236992_0_ == b_4507_u.v_4262_N) {
            ImmutableList list1 = ImmutableList.of((Object)"Fortress");
            return new A_1763_n(p_236992_1_, (List<String>)list1, (List<String>)list1);
        }
        if (p_236992_0_ == b_4507_u.w_1484_f) {
            ImmutableList list = ImmutableList.of((Object)"EndCity");
            return new A_1763_n(p_236992_1_, (List<String>)list, (List<String>)list);
        }
        throw new RuntimeException(String.format("Unknown dimension type : %s", p_236992_0_));
    }
}


