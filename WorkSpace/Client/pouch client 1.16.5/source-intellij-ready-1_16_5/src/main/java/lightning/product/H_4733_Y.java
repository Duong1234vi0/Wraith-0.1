/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.DataLayer;
import lightning.product.U_2912_j;
import lightning.product.OldDataLayer;
import lightning.product.V_3137_a;
import lightning.product.Y_1387_d;
import lightning.product.c_1108_W;
import lightning.product.BiomeSource;
import lightning.product.q_2896_o;
import lightning.product.r_4097_j;

public class H_4733_Y {
    public static n_1700_B n_1700_B(U_2912_j nbt) {
        int i = nbt.w_1484_f("xPos");
        int j = nbt.w_1484_f("zPos");
        n_1700_B chunkloaderutil$anvilconverterdata = new n_1700_B(i, j);
        chunkloaderutil$anvilconverterdata.v_4262_N = nbt.P_4830_p("Blocks");
        chunkloaderutil$anvilconverterdata.u_1723_Y = new OldDataLayer(nbt.P_4830_p("Data"), 7);
        chunkloaderutil$anvilconverterdata.P_1922_E = new OldDataLayer(nbt.P_4830_p("SkyLight"), 7);
        chunkloaderutil$anvilconverterdata.G_564_y = new OldDataLayer(nbt.P_4830_p("BlockLight"), 7);
        chunkloaderutil$anvilconverterdata.R_4764_Y = nbt.P_4830_p("HeightMap");
        chunkloaderutil$anvilconverterdata.J_1907_R = nbt.t_1786_h("TerrainPopulated");
        chunkloaderutil$anvilconverterdata.w_1484_f = nbt.G_564_y("Entities", 10);
        chunkloaderutil$anvilconverterdata.t_148_a = nbt.G_564_y("TileEntities", 10);
        chunkloaderutil$anvilconverterdata.s_956_w = nbt.G_564_y("TileTicks", 10);
        try {
            chunkloaderutil$anvilconverterdata.n_1700_B = nbt.t_148_a("LastUpdate");
        }
        catch (ClassCastException classcastexception) {
            chunkloaderutil$anvilconverterdata.n_1700_B = nbt.w_1484_f("LastUpdate");
        }
        return chunkloaderutil$anvilconverterdata;
    }

    public static void n_1700_B(r_4097_j.J_1907_R p_242708_0_, n_1700_B p_242708_1_, U_2912_j p_242708_2_, BiomeSource p_242708_3_) {
        p_242708_2_.J_1907_R("xPos", p_242708_1_.u_2550_I);
        p_242708_2_.J_1907_R("zPos", p_242708_1_.M_588_G);
        p_242708_2_.n_1700_B("LastUpdate", p_242708_1_.n_1700_B);
        int[] aint = new int[p_242708_1_.R_4764_Y.length];
        for (int i = 0; i < p_242708_1_.R_4764_Y.length; ++i) {
            aint[i] = p_242708_1_.R_4764_Y[i];
        }
        p_242708_2_.n_1700_B("HeightMap", aint);
        p_242708_2_.n_1700_B("TerrainPopulated", p_242708_1_.J_1907_R);
        q_2896_o listnbt = new q_2896_o();
        for (int j = 0; j < 8; ++j) {
            boolean flag = true;
            for (int k = 0; k < 16 && flag; ++k) {
                block3: for (int l = 0; l < 16 && flag; ++l) {
                    for (int i1 = 0; i1 < 16; ++i1) {
                        int j1 = k << 11 | i1 << 7 | l + (j << 4);
                        byte k1 = p_242708_1_.v_4262_N[j1];
                        if (k1 == 0) continue;
                        flag = false;
                        continue block3;
                    }
                }
            }
            if (flag) continue;
            byte[] abyte = new byte[4096];
            DataLayer nibblearray = new DataLayer();
            DataLayer nibblearray1 = new DataLayer();
            DataLayer nibblearray2 = new DataLayer();
            for (int l2 = 0; l2 < 16; ++l2) {
                for (int l1 = 0; l1 < 16; ++l1) {
                    for (int i2 = 0; i2 < 16; ++i2) {
                        int j2 = l2 << 11 | i2 << 7 | l1 + (j << 4);
                        byte k2 = p_242708_1_.v_4262_N[j2];
                        abyte[l1 << 8 | i2 << 4 | l2] = (byte)(k2 & 0xFF);
                        nibblearray.n_1700_B(l2, l1, i2, p_242708_1_.u_1723_Y.n_1700_B(l2, l1 + (j << 4), i2));
                        nibblearray1.n_1700_B(l2, l1, i2, p_242708_1_.P_1922_E.n_1700_B(l2, l1 + (j << 4), i2));
                        nibblearray2.n_1700_B(l2, l1, i2, p_242708_1_.G_564_y.n_1700_B(l2, l1 + (j << 4), i2));
                    }
                }
            }
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("Y", (byte)(j & 0xFF));
            compoundnbt.n_1700_B("Blocks", abyte);
            compoundnbt.n_1700_B("Data", nibblearray.n_1700_B());
            compoundnbt.n_1700_B("SkyLight", nibblearray1.n_1700_B());
            compoundnbt.n_1700_B("BlockLight", nibblearray2.n_1700_B());
            listnbt.add(compoundnbt);
        }
        p_242708_2_.n_1700_B("Sections", listnbt);
        p_242708_2_.n_1700_B("Biomes", new c_1108_W(p_242708_0_.J_1907_R(V_3137_a.PlayerInfo), new Y_1387_d(p_242708_1_.u_2550_I, p_242708_1_.M_588_G), p_242708_3_).n_1700_B());
        p_242708_2_.n_1700_B("Entities", p_242708_1_.w_1484_f);
        p_242708_2_.n_1700_B("TileEntities", p_242708_1_.t_148_a);
        if (p_242708_1_.s_956_w != null) {
            p_242708_2_.n_1700_B("TileTicks", p_242708_1_.s_956_w);
        }
        p_242708_2_.n_1700_B("convertedFromAlphaFormat", true);
    }

    public static class n_1700_B {
        public long n_1700_B;
        public boolean J_1907_R;
        public byte[] R_4764_Y;
        public OldDataLayer G_564_y;
        public OldDataLayer P_1922_E;
        public OldDataLayer u_1723_Y;
        public byte[] v_4262_N;
        public q_2896_o w_1484_f;
        public q_2896_o t_148_a;
        public q_2896_o s_956_w;
        public final int u_2550_I;
        public final int M_588_G;

        public n_1700_B(int xIn, int zIn) {
            this.u_2550_I = xIn;
            this.M_588_G = zIn;
        }
    }
}


