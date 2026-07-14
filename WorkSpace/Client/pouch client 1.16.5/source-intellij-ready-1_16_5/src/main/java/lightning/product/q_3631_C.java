/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lightning.product.D_3718_K;
import lightning.product.F_877_l;
import lightning.product.H_4733_Y;
import lightning.product.H_4757_Q;
import lightning.product.ResourceManager;
import lightning.product.OverworldBiomeSource;
import lightning.product.biomeBiomes;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Y_1387_d;
import lightning.product.Tag;
import lightning.product.b_2971_z;
import lightning.product.b_4507_u;
import lightning.product.DataPackConfig;
import lightning.product.i_3066_Y;
import lightning.product.ProgressListener;
import lightning.product.k_594_Q;
import lightning.product.l_4118_l;
import lightning.product.WorldData;
import lightning.product.WritableRegistry;
import lightning.product.BiomeSource;
import lightning.product.r_1827_u;
import lightning.product.r_4097_j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class q_3631_C {
    private static final Logger n_1700_B = LogManager.getLogger();

    static boolean n_1700_B(b_2971_z.n_1700_B levelSave, ProgressListener progress) {
        progress.n_1700_B(0);
        ArrayList list = Lists.newArrayList();
        ArrayList list1 = Lists.newArrayList();
        ArrayList list2 = Lists.newArrayList();
        File file1 = levelSave.n_1700_B(b_4507_u.u_1723_Y);
        File file2 = levelSave.n_1700_B(b_4507_u.v_4262_N);
        File file3 = levelSave.n_1700_B(b_4507_u.w_1484_f);
        n_1700_B.info("Scanning folders...");
        q_3631_C.n_1700_B(file1, list);
        if (file2.exists()) {
            q_3631_C.n_1700_B(file2, list1);
        }
        if (file3.exists()) {
            q_3631_C.n_1700_B(file3, list2);
        }
        int i = list.size() + list1.size() + list2.size();
        n_1700_B.info("Total conversion count is {}", (Object)i);
        r_4097_j.J_1907_R dynamicregistries$impl = r_4097_j.J_1907_R();
        F_877_l<Tag> worldsettingsimport = F_877_l.n_1700_B(l_4118_l.n_1700_B, ResourceManager.n_1700_B.n_1700_B, dynamicregistries$impl);
        WorldData iserverconfiguration = levelSave.n_1700_B(worldsettingsimport, DataPackConfig.n_1700_B);
        long j = iserverconfiguration != null ? iserverconfiguration.e_4240_b().n_1700_B() : 0L;
        WritableRegistry<k_594_Q> registry = dynamicregistries$impl.J_1907_R(V_3137_a.PlayerInfo);
        BiomeSource biomeprovider = iserverconfiguration != null && iserverconfiguration.e_4240_b().w_1484_f() ? new D_3718_K(registry.R_4764_Y(biomeBiomes.J_1907_R)) : new OverworldBiomeSource(j, false, false, registry);
        q_3631_C.n_1700_B(dynamicregistries$impl, new File(file1, "region"), list, biomeprovider, 0, i, progress);
        q_3631_C.n_1700_B(dynamicregistries$impl, new File(file2, "region"), list1, (BiomeSource)new D_3718_K(registry.R_4764_Y(biomeBiomes.t_148_a)), list.size(), i, progress);
        q_3631_C.n_1700_B(dynamicregistries$impl, new File(file3, "region"), list2, (BiomeSource)new D_3718_K(registry.R_4764_Y(biomeBiomes.s_956_w)), list.size() + list1.size(), i, progress);
        q_3631_C.n_1700_B(levelSave);
        levelSave.n_1700_B(dynamicregistries$impl, iserverconfiguration);
        return true;
    }

    private static void n_1700_B(b_2971_z.n_1700_B levelSave) {
        File file1 = levelSave.n_1700_B(H_4757_Q.P_1922_E).toFile();
        if (!file1.exists()) {
            n_1700_B.warn("Unable to create level.dat_mcr backup");
        } else {
            File file2 = new File(file1.getParent(), "level.dat_mcr");
            if (!file1.renameTo(file2)) {
                n_1700_B.warn("Unable to create level.dat_mcr backup");
            }
        }
    }

    private static void n_1700_B(r_4097_j.J_1907_R p_242983_0_, File p_242983_1_, Iterable<File> p_242983_2_, BiomeSource p_242983_3_, int p_242983_4_, int p_242983_5_, ProgressListener p_242983_6_) {
        for (File file1 : p_242983_2_) {
            q_3631_C.n_1700_B(p_242983_0_, p_242983_1_, file1, p_242983_3_, p_242983_4_, p_242983_5_, p_242983_6_);
            int i = (int)Math.round(100.0 * (double)(++p_242983_4_) / (double)p_242983_5_);
            p_242983_6_.n_1700_B(i);
        }
    }

    private static void n_1700_B(r_4097_j.J_1907_R p_242982_0_, File p_242982_1_, File p_242982_2_, BiomeSource p_242982_3_, int p_242982_4_, int p_242982_5_, ProgressListener p_242982_6_) {
        String s = p_242982_2_.getName();
        try (i_3066_Y regionfile = new i_3066_Y(p_242982_2_, p_242982_1_, true);
             i_3066_Y regionfile1 = new i_3066_Y(new File(p_242982_1_, s.substring(0, s.length() - ".mcr".length()) + ".mca"), p_242982_1_, true);){
            for (int i = 0; i < 32; ++i) {
                for (int j = 0; j < 32; ++j) {
                    U_2912_j compoundnbt;
                    Y_1387_d chunkpos = new Y_1387_d(i, j);
                    if (!regionfile.G_564_y(chunkpos) || regionfile1.G_564_y(chunkpos)) continue;
                    try (DataInputStream datainputstream = regionfile.n_1700_B(chunkpos);){
                        if (datainputstream == null) {
                            n_1700_B.warn("Failed to fetch input stream for chunk {}", (Object)chunkpos);
                            continue;
                        }
                        compoundnbt = r_1827_u.n_1700_B(datainputstream);
                    }
                    catch (IOException ioexception) {
                        n_1700_B.warn("Failed to read data for chunk {}", (Object)chunkpos, (Object)ioexception);
                        continue;
                    }
                    U_2912_j compoundnbt3 = compoundnbt.M_182_A("Level");
                    H_4733_Y.n_1700_B chunkloaderutil$anvilconverterdata = H_4733_Y.n_1700_B(compoundnbt3);
                    U_2912_j compoundnbt1 = new U_2912_j();
                    U_2912_j compoundnbt2 = new U_2912_j();
                    compoundnbt1.n_1700_B("Level", compoundnbt2);
                    H_4733_Y.n_1700_B(p_242982_0_, chunkloaderutil$anvilconverterdata, compoundnbt2, p_242982_3_);
                    try (DataOutputStream dataoutputstream = regionfile1.R_4764_Y(chunkpos);){
                        r_1827_u.n_1700_B(compoundnbt1, (DataOutput)dataoutputstream);
                        continue;
                    }
                }
                int k = (int)Math.round(100.0 * (double)(p_242982_4_ * 1024) / (double)(p_242982_5_ * 1024));
                int l = (int)Math.round(100.0 * (double)((i + 1) * 32 + p_242982_4_ * 1024) / (double)(p_242982_5_ * 1024));
                if (l <= k) continue;
                p_242982_6_.n_1700_B(l);
            }
        }
        catch (IOException ioexception1) {
            n_1700_B.error("Failed to upgrade region file {}", (Object)p_242982_2_, (Object)ioexception1);
        }
    }

    private static void n_1700_B(File saveFolder, Collection<File> files) {
        File file1 = new File(saveFolder, "region");
        File[] afile = file1.listFiles((region, fileName) -> fileName.endsWith(".mcr"));
        if (afile != null) {
            Collections.addAll(files, afile);
        }
    }
}


