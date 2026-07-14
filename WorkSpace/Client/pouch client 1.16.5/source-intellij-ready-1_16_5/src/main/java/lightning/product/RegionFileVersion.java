/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterInputStream;
import javax.annotation.Nullable;

public class RegionFileVersion {
    private static final Int2ObjectMap<RegionFileVersion> G_564_y = new Int2ObjectOpenHashMap();
    public static final RegionFileVersion n_1700_B = RegionFileVersion.n_1700_B(new RegionFileVersion(1, GZIPInputStream::new, GZIPOutputStream::new));
    public static final RegionFileVersion J_1907_R = RegionFileVersion.n_1700_B(new RegionFileVersion(2, InflaterInputStream::new, DeflaterOutputStream::new));
    public static final RegionFileVersion R_4764_Y = RegionFileVersion.n_1700_B(new RegionFileVersion(3, p_227171_0_ -> p_227171_0_, p_227172_0_ -> p_227172_0_));
    private final int P_1922_E;
    private final n_1700_B<InputStream> u_1723_Y;
    private final n_1700_B<OutputStream> v_4262_N;

    private RegionFileVersion(int p_i225787_1_, n_1700_B<InputStream> p_i225787_2_, n_1700_B<OutputStream> p_i225787_3_) {
        this.P_1922_E = p_i225787_1_;
        this.u_1723_Y = p_i225787_2_;
        this.v_4262_N = p_i225787_3_;
    }

    private static RegionFileVersion n_1700_B(RegionFileVersion p_227167_0_) {
        G_564_y.put(p_227167_0_.P_1922_E, (Object)p_227167_0_);
        return p_227167_0_;
    }

    @Nullable
    public static RegionFileVersion n_1700_B(int p_227166_0_) {
        return (RegionFileVersion)G_564_y.get(p_227166_0_);
    }

    public static boolean J_1907_R(int p_227170_0_) {
        return G_564_y.containsKey(p_227170_0_);
    }

    public int n_1700_B() {
        return this.P_1922_E;
    }

    public OutputStream n_1700_B(OutputStream p_227169_1_) throws IOException {
        return this.v_4262_N.wrap(p_227169_1_);
    }

    public InputStream n_1700_B(InputStream p_227168_1_) throws IOException {
        return this.u_1723_Y.wrap(p_227168_1_);
    }

    @FunctionalInterface
    static interface n_1700_B<O> {
        public O wrap(O var1) throws IOException;
    }
}


