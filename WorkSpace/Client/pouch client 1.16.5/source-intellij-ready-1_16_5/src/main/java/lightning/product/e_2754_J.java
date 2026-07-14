/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectList
 *  it.unimi.dsi.fastutil.objects.ObjectListIterator
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntSupplier;
import javax.annotation.Nullable;
import lightning.product.K_4719_o;
import lightning.product.P_3550_Z;
import lightning.product.R_1900_x;
import lightning.product.DataLayer;
import lightning.product.U_3758_m;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.ChunkAccess;
import lightning.product.f_2197_c;
import lightning.product.j_3341_s;
import lightning.product.ProcessorHandle;
import lightning.product.LightChunkGetter;
import lightning.product.y_1195_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class e_2754_J
extends R_1900_x
implements AutoCloseable {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final U_3758_m<Runnable> J_1907_R;
    private final ObjectList<Pair<n_1700_B, Runnable>> R_4764_Y = new ObjectArrayList();
    private final y_1195_s G_564_y;
    private final ProcessorHandle<f_2197_c.n_1700_B<Runnable>> P_1922_E;
    private volatile int u_1723_Y = 5;
    private final AtomicBoolean v_4262_N = new AtomicBoolean();

    public e_2754_J(LightChunkGetter provider, y_1195_s chunkManagerIn, boolean hasSkyLight, U_3758_m<Runnable> p_i50701_4_, ProcessorHandle<f_2197_c.n_1700_B<Runnable>> p_i50701_5_) {
        super(provider, true, hasSkyLight);
        this.G_564_y = chunkManagerIn;
        this.P_1922_E = p_i50701_5_;
        this.J_1907_R = p_i50701_4_;
    }

    @Override
    public void close() {
    }

    @Override
    public int n_1700_B(int toUpdateCount, boolean updateSkyLight, boolean updateBlockLight) {
        throw j_3341_s.R_4764_Y(new UnsupportedOperationException("Ran authomatically on a different thread!"));
    }

    @Override
    public void n_1700_B(c_1514_x blockPosIn, int p_215573_2_) {
        throw j_3341_s.R_4764_Y(new UnsupportedOperationException("Ran authomatically on a different thread!"));
    }

    @Override
    public void n_1700_B(c_1514_x blockPosIn) {
        c_1514_x blockpos = blockPosIn.toImmutable();
        this.n_1700_B(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4, lightning.product.e_2754_J$n_1700_B.J_1907_R, j_3341_s.n_1700_B(() -> super.n_1700_B(blockpos), () -> "checkBlock " + String.valueOf(blockpos)));
    }

    protected void n_1700_B(Y_1387_d p_215581_1_) {
        this.n_1700_B(p_215581_1_.J_1907_R, p_215581_1_.R_4764_Y, () -> 0, lightning.product.e_2754_J$n_1700_B.n_1700_B, j_3341_s.n_1700_B(() -> {
            super.J_1907_R(p_215581_1_, false);
            super.n_1700_B(p_215581_1_, false);
            for (int i = -1; i < 17; ++i) {
                super.n_1700_B(K_4719_o.J_1907_R, SectionPos.n_1700_B(p_215581_1_, i), null, true);
                super.n_1700_B(K_4719_o.n_1700_B, SectionPos.n_1700_B(p_215581_1_, i), null, true);
            }
            for (int j = 0; j < 16; ++j) {
                super.n_1700_B(SectionPos.n_1700_B(p_215581_1_, j), true);
            }
        }, () -> "updateChunkStatus " + String.valueOf(p_215581_1_) + " true"));
    }

    @Override
    public void n_1700_B(SectionPos pos, boolean isEmpty) {
        this.n_1700_B(pos.n_1700_B(), pos.R_4764_Y(), () -> 0, lightning.product.e_2754_J$n_1700_B.n_1700_B, j_3341_s.n_1700_B(() -> super.n_1700_B(pos, isEmpty), () -> "updateSectionStatus " + String.valueOf(pos) + " " + isEmpty));
    }

    @Override
    public void n_1700_B(Y_1387_d p_215571_1_, boolean p_215571_2_) {
        this.n_1700_B(p_215571_1_.J_1907_R, p_215571_1_.R_4764_Y, lightning.product.e_2754_J$n_1700_B.n_1700_B, j_3341_s.n_1700_B(() -> super.n_1700_B(p_215571_1_, p_215571_2_), () -> "enableLight " + String.valueOf(p_215571_1_) + " " + p_215571_2_));
    }

    @Override
    public void n_1700_B(K_4719_o type, SectionPos pos, @Nullable DataLayer array, boolean p_215574_4_) {
        this.n_1700_B(pos.n_1700_B(), pos.R_4764_Y(), () -> 0, lightning.product.e_2754_J$n_1700_B.n_1700_B, j_3341_s.n_1700_B(() -> super.n_1700_B(type, pos, array, p_215574_4_), () -> "queueData " + String.valueOf(pos)));
    }

    private void n_1700_B(int chunkX, int chunkZ, n_1700_B p_215586_3_, Runnable p_215586_4_) {
        this.n_1700_B(chunkX, chunkZ, this.G_564_y.R_4764_Y(Y_1387_d.n_1700_B(chunkX, chunkZ)), p_215586_3_, p_215586_4_);
    }

    private void n_1700_B(int chunkX, int chunkZ, IntSupplier p_215600_3_, n_1700_B p_215600_4_, Runnable p_215600_5_) {
        this.P_1922_E.n_1700_B(f_2197_c.n_1700_B(() -> {
            this.R_4764_Y.add((Object)Pair.of((Object)((Object)p_215600_4_), (Object)p_215600_5_));
            if (this.R_4764_Y.size() >= this.u_1723_Y) {
                this.R_4764_Y();
            }
        }, Y_1387_d.n_1700_B(chunkX, chunkZ), p_215600_3_));
    }

    @Override
    public void J_1907_R(Y_1387_d pos, boolean retain) {
        this.n_1700_B(pos.J_1907_R, pos.R_4764_Y, () -> 0, lightning.product.e_2754_J$n_1700_B.n_1700_B, j_3341_s.n_1700_B(() -> super.J_1907_R(pos, retain), () -> "retainData " + String.valueOf(pos)));
    }

    public CompletableFuture<ChunkAccess> n_1700_B(ChunkAccess p_215593_1_, boolean p_215593_2_) {
        Y_1387_d chunkpos = p_215593_1_.getPos();
        p_215593_1_.setLight(false);
        this.n_1700_B(chunkpos.J_1907_R, chunkpos.R_4764_Y, lightning.product.e_2754_J$n_1700_B.n_1700_B, j_3341_s.n_1700_B(() -> {
            P_3550_Z[] achunksection = p_215593_1_.getSections();
            for (int i = 0; i < 16; ++i) {
                P_3550_Z chunksection = achunksection[i];
                if (P_3550_Z.n_1700_B(chunksection)) continue;
                super.n_1700_B(SectionPos.n_1700_B(chunkpos, i), false);
            }
            super.n_1700_B(chunkpos, true);
            if (!p_215593_2_) {
                p_215593_1_.getLightSources().forEach(p_215579_2_ -> super.n_1700_B((c_1514_x)p_215579_2_, p_215593_1_.R_4764_Y((c_1514_x)p_215579_2_)));
            }
            this.G_564_y.G_564_y(chunkpos);
        }, () -> "lightChunk " + String.valueOf(chunkpos) + " " + p_215593_2_));
        return CompletableFuture.supplyAsync(() -> {
            p_215593_1_.setLight(true);
            super.J_1907_R(chunkpos, false);
            return p_215593_1_;
        }, p_215597_2_ -> this.n_1700_B(chunkpos.J_1907_R, chunkpos.R_4764_Y, lightning.product.e_2754_J$n_1700_B.J_1907_R, p_215597_2_));
    }

    public void J_1907_R() {
        if ((!this.R_4764_Y.isEmpty() || super.n_1700_B()) && this.v_4262_N.compareAndSet(false, true)) {
            this.J_1907_R.n_1700_B(() -> {
                this.R_4764_Y();
                this.v_4262_N.set(false);
            });
        }
    }

    private void R_4764_Y() {
        int j;
        int i = Math.min(this.R_4764_Y.size(), this.u_1723_Y);
        ObjectListIterator objectlistiterator = this.R_4764_Y.iterator();
        for (j = 0; objectlistiterator.hasNext() && j < i; ++j) {
            Pair pair = (Pair)objectlistiterator.next();
            if (pair.getFirst() != lightning.product.e_2754_J$n_1700_B.n_1700_B) continue;
            ((Runnable)pair.getSecond()).run();
        }
        objectlistiterator.back(j);
        super.n_1700_B(Integer.MAX_VALUE, true, true);
        for (int k = 0; objectlistiterator.hasNext() && k < i; ++k) {
            Pair pair1 = (Pair)objectlistiterator.next();
            if (pair1.getFirst() == lightning.product.e_2754_J$n_1700_B.J_1907_R) {
                ((Runnable)pair1.getSecond()).run();
            }
            objectlistiterator.remove();
        }
    }

    public void n_1700_B(int p_215598_1_) {
        this.u_1723_Y = p_215598_1_;
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.e_2754_J$n_1700_B.n_1700_B();
        }
    }
}


