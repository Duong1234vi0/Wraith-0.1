/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  io.netty.handler.codec.DecoderException
 *  io.netty.handler.codec.EncoderException
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.ObjectUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.k_594_Q;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.CrashReportCategory;
import lightning.product.EntityDataSerializer;
import net.optifine.util.BiomeUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class C_4114_x {
    private static final Logger R_4764_Y = LogManager.getLogger();
    private static final Map<Class<? extends N_4263_v>, Integer> G_564_y = Maps.newHashMap();
    private final N_4263_v P_1922_E;
    private final Map<Integer, n_1700_B<?>> u_1723_Y = Maps.newHashMap();
    private final ReadWriteLock v_4262_N = new ReentrantReadWriteLock();
    private boolean w_1484_f = true;
    private boolean t_148_a;
    public k_594_Q n_1700_B = BiomeUtils.PLAINS;
    public c_1514_x J_1907_R = c_1514_x.ZERO;

    public C_4114_x(N_4263_v entityIn) {
        this.P_1922_E = entityIn;
    }

    public static <T> h_256_u<T> n_1700_B(Class<? extends N_4263_v> clazz, EntityDataSerializer<T> serializer) {
        int j;
        if (R_4764_Y.isDebugEnabled()) {
            try {
                Class<?> oclass = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
                if (!oclass.equals(clazz)) {
                    R_4764_Y.debug("defineId called for: {} from {}", clazz, oclass, (Object)new RuntimeException());
                }
            }
            catch (ClassNotFoundException oclass) {
                // empty catch block
            }
        }
        if (G_564_y.containsKey(clazz)) {
            j = G_564_y.get(clazz) + 1;
        } else {
            int i = 0;
            Class<? extends N_4263_v> oclass1 = clazz;
            while (oclass1 != N_4263_v.class) {
                if (!G_564_y.containsKey(oclass1 = oclass1.getSuperclass())) continue;
                i = G_564_y.get(oclass1) + 1;
                break;
            }
            j = i;
        }
        if (j > 254) {
            throw new IllegalArgumentException("Data value id is too big with " + j + "! (Max is 254)");
        }
        G_564_y.put(clazz, j);
        return serializer.n_1700_B(j);
    }

    public <T> void n_1700_B(h_256_u<T> key, T value) {
        int i = key.n_1700_B();
        if (i > 254) {
            throw new IllegalArgumentException("Data value id is too big with " + i + "! (Max is 254)");
        }
        if (this.u_1723_Y.containsKey(i)) {
            throw new IllegalArgumentException("Duplicate id value for " + i + "!");
        }
        if (EntityDataSerializers.J_1907_R(key.J_1907_R()) < 0) {
            throw new IllegalArgumentException("Unregistered serializer " + String.valueOf(key.J_1907_R()) + " for " + i + "!");
        }
        this.R_4764_Y(key, value);
    }

    private <T> void R_4764_Y(h_256_u<T> key, T value) {
        n_1700_B<T> dataentry = new n_1700_B<T>(key, value);
        this.v_4262_N.writeLock().lock();
        this.u_1723_Y.put(key.n_1700_B(), dataentry);
        this.w_1484_f = false;
        this.v_4262_N.writeLock().unlock();
    }

    private <T> n_1700_B<T> J_1907_R(h_256_u<T> key) {
        n_1700_B<?> dataentry;
        this.v_4262_N.readLock().lock();
        try {
            dataentry = this.u_1723_Y.get(key.n_1700_B());
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Getting synched entity data");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Synched entity data");
            crashreportcategory.n_1700_B("Data ID", key);
            throw new ReportedException(crashreport);
        }
        finally {
            this.v_4262_N.readLock().unlock();
        }
        return dataentry;
    }

    public <T> T n_1700_B(h_256_u<T> key) {
        return this.J_1907_R(key).J_1907_R();
    }

    public <T> void J_1907_R(h_256_u<T> key, T value) {
        n_1700_B<T> dataentry = this.J_1907_R(key);
        if (ObjectUtils.notEqual(value, dataentry.J_1907_R())) {
            dataentry.n_1700_B(value);
            this.P_1922_E.n_1700_B(key);
            dataentry.n_1700_B(true);
            this.t_148_a = true;
        }
    }

    public boolean n_1700_B() {
        return this.t_148_a;
    }

    public static void n_1700_B(List<n_1700_B<?>> entriesIn, b_2585_i buf) throws IOException {
        if (entriesIn != null) {
            int j = entriesIn.size();
            for (int i = 0; i < j; ++i) {
                C_4114_x.n_1700_B(buf, entriesIn.get(i));
            }
        }
        buf.writeByte(255);
    }

    @Nullable
    public List<n_1700_B<?>> J_1907_R() {
        ArrayList list = null;
        if (this.t_148_a) {
            this.v_4262_N.readLock().lock();
            for (n_1700_B<?> dataentry : this.u_1723_Y.values()) {
                if (!dataentry.R_4764_Y()) continue;
                dataentry.n_1700_B(false);
                if (list == null) {
                    list = Lists.newArrayList();
                }
                list.add(dataentry.G_564_y());
            }
            this.v_4262_N.readLock().unlock();
        }
        this.t_148_a = false;
        return list;
    }

    @Nullable
    public List<n_1700_B<?>> R_4764_Y() {
        ArrayList list = null;
        this.v_4262_N.readLock().lock();
        for (n_1700_B<?> dataentry : this.u_1723_Y.values()) {
            if (list == null) {
                list = Lists.newArrayList();
            }
            list.add(dataentry.G_564_y());
        }
        this.v_4262_N.readLock().unlock();
        return list;
    }

    private static <T> void n_1700_B(b_2585_i buf, n_1700_B<T> entry) throws IOException {
        h_256_u<T> dataparameter = entry.n_1700_B();
        int i = EntityDataSerializers.J_1907_R(dataparameter.J_1907_R());
        if (i < 0) {
            throw new EncoderException("Unknown serializer type " + String.valueOf(dataparameter.J_1907_R()));
        }
        buf.writeByte(dataparameter.n_1700_B());
        buf.G_564_y(i);
        dataparameter.J_1907_R().n_1700_B(buf, entry.J_1907_R());
    }

    @Nullable
    public static List<n_1700_B<?>> n_1700_B(b_2585_i buf) throws IOException {
        short i;
        ArrayList list = null;
        while ((i = buf.readUnsignedByte()) != 255) {
            int j;
            EntityDataSerializer<?> idataserializer;
            if (list == null) {
                list = Lists.newArrayList();
            }
            if ((idataserializer = EntityDataSerializers.n_1700_B(j = buf.u_1723_Y())) == null) {
                throw new DecoderException("Unknown serializer type " + j);
            }
            list.add(C_4114_x.n_1700_B(buf, i, idataserializer));
        }
        return list;
    }

    private static <T> n_1700_B<T> n_1700_B(b_2585_i bufferIn, int idIn, EntityDataSerializer<T> serializerIn) {
        return new n_1700_B<T>(serializerIn.n_1700_B(idIn), serializerIn.J_1907_R(bufferIn));
    }

    public void n_1700_B(List<n_1700_B<?>> entriesIn) {
        this.v_4262_N.writeLock().lock();
        for (n_1700_B<?> dataentry : entriesIn) {
            n_1700_B<?> dataentry1 = this.u_1723_Y.get(dataentry.n_1700_B().n_1700_B());
            if (dataentry1 == null) continue;
            this.n_1700_B(dataentry1, dataentry);
            this.P_1922_E.n_1700_B(dataentry.n_1700_B());
        }
        this.v_4262_N.writeLock().unlock();
        this.t_148_a = true;
    }

    private <T> void n_1700_B(n_1700_B<T> target, n_1700_B<?> source) {
        if (!Objects.equals(source.n_1700_B.J_1907_R(), target.n_1700_B.J_1907_R())) {
            throw new IllegalStateException(String.format("Invalid entity data item type for field %d on entity %s: old=%s(%s), new=%s(%s)", target.n_1700_B.n_1700_B(), this.P_1922_E, target.J_1907_R, target.J_1907_R.getClass(), source.J_1907_R, source.J_1907_R.getClass()));
        }
        target.n_1700_B(source.J_1907_R());
    }

    public boolean G_564_y() {
        return this.w_1484_f;
    }

    public void P_1922_E() {
        this.t_148_a = false;
        this.v_4262_N.readLock().lock();
        for (n_1700_B<?> dataentry : this.u_1723_Y.values()) {
            dataentry.n_1700_B(false);
        }
        this.v_4262_N.readLock().unlock();
    }

    public static class n_1700_B<T> {
        private final h_256_u<T> n_1700_B;
        private T J_1907_R;
        private boolean R_4764_Y;

        public n_1700_B(h_256_u<T> keyIn, T valueIn) {
            this.n_1700_B = keyIn;
            this.J_1907_R = valueIn;
            this.R_4764_Y = true;
        }

        public h_256_u<T> n_1700_B() {
            return this.n_1700_B;
        }

        public void n_1700_B(T valueIn) {
            this.J_1907_R = valueIn;
        }

        public T J_1907_R() {
            return this.J_1907_R;
        }

        public boolean R_4764_Y() {
            return this.R_4764_Y;
        }

        public void n_1700_B(boolean dirtyIn) {
            this.R_4764_Y = dirtyIn;
        }

        public n_1700_B<T> G_564_y() {
            return new n_1700_B<T>(this.n_1700_B, this.n_1700_B.J_1907_R().n_1700_B(this.J_1907_R));
        }
    }
}


