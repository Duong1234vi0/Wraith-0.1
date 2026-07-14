/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 *  com.google.common.util.concurrent.ThreadFactoryBuilder
 *  com.mojang.datafixers.DataFixer
 *  it.unimi.dsi.fastutil.objects.Object2FloatMap
 *  it.unimi.dsi.fastutil.objects.Object2FloatMaps
 *  it.unimi.dsi.fastutil.objects.Object2FloatOpenCustomHashMap
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mojang.datafixers.DataFixer;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatMaps;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenCustomHashMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.F_2904_S;
import lightning.product.ChunkStorage;
import lightning.product.SharedConstants;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.b_2971_z;
import lightning.product.b_4507_u;
import lightning.product.f_2392_k;
import lightning.product.i_3066_Y;
import lightning.product.j_3341_s;
import lightning.product.ReportedException;
import lightning.product.s_4380_l;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class U_3339_M {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final ThreadFactory J_1907_R = new ThreadFactoryBuilder().setDaemon(true).build();
    private final ImmutableSet<f_2392_k<b_4507_u>> R_4764_Y;
    private final boolean G_564_y;
    private final b_2971_z.n_1700_B P_1922_E;
    private final Thread u_1723_Y;
    private final DataFixer v_4262_N;
    private volatile boolean w_1484_f = true;
    private volatile boolean t_148_a;
    private volatile float s_956_w;
    private volatile int u_2550_I;
    private volatile int M_588_G;
    private volatile int P_4830_p;
    private final Object2FloatMap<f_2392_k<b_4507_u>> h_1847_R = Object2FloatMaps.synchronize((Object2FloatMap)new Object2FloatOpenCustomHashMap(j_3341_s.u_2550_I()));
    private volatile x_282_a Q_4569_t = new F_2904_S("optimizeWorld.stage.counting");
    private static final Pattern M_182_A = Pattern.compile("^r\\.(-?[0-9]+)\\.(-?[0-9]+)\\.mca$");
    private final s_4380_l t_1786_h;

    public U_3339_M(b_2971_z.n_1700_B p_i231486_1_, DataFixer p_i231486_2_, ImmutableSet<f_2392_k<b_4507_u>> p_i231486_3_, boolean p_i231486_4_) {
        this.R_4764_Y = p_i231486_3_;
        this.G_564_y = p_i231486_4_;
        this.v_4262_N = p_i231486_2_;
        this.P_1922_E = p_i231486_1_;
        this.t_1786_h = new s_4380_l(new File(this.P_1922_E.n_1700_B(b_4507_u.u_1723_Y), "data"), p_i231486_2_);
        this.u_1723_Y = J_1907_R.newThread(this::t_148_a);
        this.u_1723_Y.setUncaughtExceptionHandler((p_219956_1_, p_219956_2_) -> {
            n_1700_B.error("Error upgrading world", p_219956_2_);
            this.Q_4569_t = new F_2904_S("optimizeWorld.stage.failed");
            this.t_148_a = true;
        });
        this.u_1723_Y.start();
    }

    public void n_1700_B() {
        this.w_1484_f = false;
        try {
            this.u_1723_Y.join();
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    private void t_148_a() {
        this.u_2550_I = 0;
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (f_2392_k registrykey : this.R_4764_Y) {
            List<Y_1387_d> list = this.J_1907_R(registrykey);
            builder.put((Object)registrykey, list.listIterator());
            this.u_2550_I += list.size();
        }
        if (this.u_2550_I == 0) {
            this.t_148_a = true;
        } else {
            float f1 = this.u_2550_I;
            ImmutableMap immutablemap = builder.build();
            ImmutableMap.Builder builder1 = ImmutableMap.builder();
            for (f_2392_k registrykey1 : this.R_4764_Y) {
                File file1 = this.P_1922_E.n_1700_B(registrykey1);
                builder1.put((Object)registrykey1, (Object)new ChunkStorage(new File(file1, "region"), this.v_4262_N, true));
            }
            ImmutableMap immutablemap1 = builder1.build();
            long i = j_3341_s.J_1907_R();
            this.Q_4569_t = new F_2904_S("optimizeWorld.stage.upgrading");
            while (this.w_1484_f) {
                boolean flag = false;
                float f = 0.0f;
                for (f_2392_k registrykey2 : this.R_4764_Y) {
                    ListIterator listiterator = (ListIterator)immutablemap.get((Object)registrykey2);
                    ChunkStorage chunkloader = (ChunkStorage)immutablemap1.get((Object)registrykey2);
                    if (listiterator.hasNext()) {
                        Y_1387_d chunkpos = (Y_1387_d)listiterator.next();
                        boolean flag1 = false;
                        try {
                            U_2912_j compoundnbt = chunkloader.n_1700_B(chunkpos);
                            if (compoundnbt != null) {
                                boolean flag2;
                                int j = ChunkStorage.n_1700_B(compoundnbt);
                                U_2912_j compoundnbt1 = chunkloader.n_1700_B(registrykey2, () -> this.t_1786_h, compoundnbt);
                                U_2912_j compoundnbt2 = compoundnbt1.M_182_A("Level");
                                Y_1387_d chunkpos1 = new Y_1387_d(compoundnbt2.w_1484_f("xPos"), compoundnbt2.w_1484_f("zPos"));
                                if (!chunkpos1.equals(chunkpos)) {
                                    n_1700_B.warn("Chunk {} has invalid position {}", (Object)chunkpos, (Object)chunkpos1);
                                }
                                boolean bl = flag2 = j < SharedConstants.n_1700_B().getWorldVersion();
                                if (this.G_564_y) {
                                    flag2 = flag2 || compoundnbt2.P_1922_E("Heightmaps");
                                    compoundnbt2.multiplayerClientSuggestionProvider("Heightmaps");
                                    flag2 = flag2 || compoundnbt2.P_1922_E("isLightOn");
                                    compoundnbt2.multiplayerClientSuggestionProvider("isLightOn");
                                }
                                if (flag2) {
                                    chunkloader.n_1700_B(chunkpos, compoundnbt1);
                                    flag1 = true;
                                }
                            }
                        }
                        catch (ReportedException reportedexception) {
                            Throwable throwable = reportedexception.getCause();
                            if (!(throwable instanceof IOException)) {
                                throw reportedexception;
                            }
                            n_1700_B.error("Error upgrading chunk {}", (Object)chunkpos, (Object)throwable);
                        }
                        catch (IOException ioexception1) {
                            n_1700_B.error("Error upgrading chunk {}", (Object)chunkpos, (Object)ioexception1);
                        }
                        if (flag1) {
                            ++this.M_588_G;
                        } else {
                            ++this.P_4830_p;
                        }
                        flag = true;
                    }
                    float f2 = (float)listiterator.nextIndex() / f1;
                    this.h_1847_R.put((Object)registrykey2, f2);
                    f += f2;
                }
                this.s_956_w = f;
                if (flag) continue;
                this.w_1484_f = false;
            }
            this.Q_4569_t = new F_2904_S("optimizeWorld.stage.finished");
            for (ChunkStorage chunkloader1 : immutablemap1.values()) {
                try {
                    chunkloader1.close();
                }
                catch (IOException ioexception) {
                    n_1700_B.error("Error upgrading chunk", (Throwable)ioexception);
                }
            }
            this.t_1786_h.n_1700_B();
            i = j_3341_s.J_1907_R() - i;
            n_1700_B.info("World optimizaton finished after {} ms", (Object)i);
            this.t_148_a = true;
        }
    }

    private List<Y_1387_d> J_1907_R(f_2392_k<b_4507_u> p_233532_1_) {
        File file1 = this.P_1922_E.n_1700_B(p_233532_1_);
        File file2 = new File(file1, "region");
        File[] afile = file2.listFiles((p_219954_0_, p_219954_1_) -> p_219954_1_.endsWith(".mca"));
        if (afile == null) {
            return ImmutableList.of();
        }
        ArrayList list = Lists.newArrayList();
        for (File file3 : afile) {
            Matcher matcher = M_182_A.matcher(file3.getName());
            if (!matcher.matches()) continue;
            int i = Integer.parseInt(matcher.group(1)) << 5;
            int j = Integer.parseInt(matcher.group(2)) << 5;
            try (i_3066_Y regionfile = new i_3066_Y(file3, file2, true);){
                for (int k = 0; k < 32; ++k) {
                    for (int l = 0; l < 32; ++l) {
                        Y_1387_d chunkpos = new Y_1387_d(k + i, l + j);
                        if (!regionfile.J_1907_R(chunkpos)) continue;
                        list.add(chunkpos);
                    }
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        return list;
    }

    public boolean J_1907_R() {
        return this.t_148_a;
    }

    public ImmutableSet<f_2392_k<b_4507_u>> R_4764_Y() {
        return this.R_4764_Y;
    }

    public float n_1700_B(f_2392_k<b_4507_u> p_233531_1_) {
        return this.h_1847_R.getFloat(p_233531_1_);
    }

    public float G_564_y() {
        return this.s_956_w;
    }

    public int P_1922_E() {
        return this.u_2550_I;
    }

    public int u_1723_Y() {
        return this.M_588_G;
    }

    public int v_4262_N() {
        return this.P_4830_p;
    }

    public x_282_a w_1484_f() {
        return this.Q_4569_t;
    }
}


