/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2IntMap
 *  it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lightning.product.J_270_s;
import lightning.product.U_2912_j;
import lightning.product.PaletteResize;
import lightning.product.b_2585_i;
import lightning.product.LinearPalette;
import lightning.product.e_32_n;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.Palette;
import lightning.product.q_2896_o;
import lightning.product.CrashReportCategory;
import lightning.product.u_530_F;
import lightning.product.w_424_u;
import mods.baritone.utils.accessor.IPalettedContainer;

public class M_4466_T<T>
implements PaletteResize<T>,
IPalettedContainer {
    private final Palette<T> J_1907_R;
    private final PaletteResize<T> R_4764_Y = (p_205517_0_, p_205517_1_) -> 0;
    private final w_424_u<T> G_564_y;
    private final Function<U_2912_j, T> P_1922_E;
    private final Function<T, U_2912_j> u_1723_Y;
    private final T v_4262_N;
    protected J_270_s n_1700_B;
    private Palette<T> w_1484_f;
    private int t_148_a;
    private final ReentrantLock s_956_w = new ReentrantLock();

    public void n_1700_B() {
        if (this.s_956_w.isLocked() && !this.s_956_w.isHeldByCurrentThread()) {
            String s = Thread.getAllStackTraces().keySet().stream().filter(Objects::nonNull).map(p_210458_0_ -> p_210458_0_.getName() + ": \n\tat " + Arrays.stream(p_210458_0_.getStackTrace()).map(Object::toString).collect(Collectors.joining("\n\tat "))).collect(Collectors.joining("\n"));
            n_3236_c crashreport = new n_3236_c("Writing into PalettedContainer from multiple threads", new IllegalStateException());
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Thread dumps");
            crashreportcategory.n_1700_B("Thread dumps", s);
            throw new ReportedException(crashreport);
        }
        this.s_956_w.lock();
    }

    public void J_1907_R() {
        this.s_956_w.unlock();
    }

    public M_4466_T(Palette<T> globalPaletteIn, w_424_u<T> registryIn, Function<U_2912_j, T> deserializerIn, Function<T, U_2912_j> serializerIn, T defaultStateIn) {
        this.J_1907_R = globalPaletteIn;
        this.G_564_y = registryIn;
        this.P_1922_E = deserializerIn;
        this.u_1723_Y = serializerIn;
        this.v_4262_N = defaultStateIn;
        this.J_1907_R(4);
    }

    private static int J_1907_R(int x, int y, int z) {
        return y << 8 | z << 4 | x;
    }

    private void J_1907_R(int bitsIn) {
        if (bitsIn != this.t_148_a) {
            this.t_148_a = bitsIn;
            if (this.t_148_a <= 4) {
                this.t_148_a = 4;
                this.w_1484_f = new LinearPalette<T>(this.G_564_y, this.t_148_a, this, this.P_1922_E);
            } else if (this.t_148_a < 9) {
                this.w_1484_f = new e_32_n<T>(this.G_564_y, this.t_148_a, this, this.P_1922_E, this.u_1723_Y);
            } else {
                this.w_1484_f = this.J_1907_R;
                this.t_148_a = u_530_F.P_1922_E(this.G_564_y.n_1700_B());
            }
            this.w_1484_f.n_1700_B(this.v_4262_N);
            this.n_1700_B = new J_270_s(this.t_148_a, 4096);
        }
    }

    @Override
    public int onResize(int p_onResize_1_, T p_onResize_2_) {
        this.n_1700_B();
        J_270_s bitarray = this.n_1700_B;
        Palette<T> ipalette = this.w_1484_f;
        this.J_1907_R(p_onResize_1_);
        for (int i = 0; i < bitarray.J_1907_R(); ++i) {
            T t = ipalette.n_1700_B(bitarray.n_1700_B(i));
            if (t == null) continue;
            this.J_1907_R(i, t);
        }
        int j = this.w_1484_f.n_1700_B(p_onResize_2_);
        this.J_1907_R();
        return j;
    }

    public T n_1700_B(int x, int y, int z, T state) {
        this.n_1700_B();
        T t = this.n_1700_B(M_4466_T.J_1907_R(x, y, z), state);
        this.J_1907_R();
        return t;
    }

    public T J_1907_R(int x, int y, int z, T state) {
        return this.n_1700_B(M_4466_T.J_1907_R(x, y, z), state);
    }

    protected T n_1700_B(int index, T state) {
        int i = this.w_1484_f.n_1700_B(state);
        int j = this.n_1700_B.n_1700_B(index, i);
        T t = this.w_1484_f.n_1700_B(j);
        return t == null ? this.v_4262_N : t;
    }

    protected void J_1907_R(int index, T state) {
        int i = this.w_1484_f.n_1700_B(state);
        this.n_1700_B.J_1907_R(index, i);
    }

    public T n_1700_B(int x, int y, int z) {
        return this.n_1700_B(M_4466_T.J_1907_R(x, y, z));
    }

    protected T n_1700_B(int index) {
        T t = this.w_1484_f.n_1700_B(this.n_1700_B.n_1700_B(index));
        return t == null ? this.v_4262_N : t;
    }

    public void n_1700_B(b_2585_i buf) {
        this.n_1700_B();
        byte i = buf.readByte();
        if (this.t_148_a != i) {
            this.J_1907_R(i);
        }
        this.w_1484_f.n_1700_B(buf);
        buf.J_1907_R(this.n_1700_B.n_1700_B());
        this.J_1907_R();
    }

    public void J_1907_R(b_2585_i buf) {
        this.n_1700_B();
        buf.writeByte(this.t_148_a);
        this.w_1484_f.J_1907_R(buf);
        buf.n_1700_B(this.n_1700_B.n_1700_B());
        this.J_1907_R();
    }

    public void n_1700_B(q_2896_o paletteNbt, long[] data) {
        this.n_1700_B();
        int i = Math.max(4, u_530_F.P_1922_E(paletteNbt.size()));
        if (i != this.t_148_a) {
            this.J_1907_R(i);
        }
        this.w_1484_f.n_1700_B(paletteNbt);
        int j = data.length * 64 / 4096;
        if (this.w_1484_f == this.J_1907_R) {
            e_32_n<T> ipalette = new e_32_n<T>(this.G_564_y, i, this.R_4764_Y, this.P_1922_E, this.u_1723_Y);
            ipalette.n_1700_B(paletteNbt);
            J_270_s bitarray = new J_270_s(i, 4096, data);
            for (int k = 0; k < 4096; ++k) {
                this.n_1700_B.J_1907_R(k, this.J_1907_R.n_1700_B(ipalette.n_1700_B(bitarray.n_1700_B(k))));
            }
        } else if (j == this.t_148_a) {
            System.arraycopy(data, 0, this.n_1700_B.n_1700_B(), 0, data.length);
        } else {
            J_270_s bitarray1 = new J_270_s(j, 4096, data);
            for (int l = 0; l < 4096; ++l) {
                this.n_1700_B.J_1907_R(l, bitarray1.n_1700_B(l));
            }
        }
        this.J_1907_R();
    }

    public void n_1700_B(U_2912_j compound, String paletteName, String paletteDataName) {
        this.n_1700_B();
        e_32_n<T> hashmappalette = new e_32_n<T>(this.G_564_y, this.t_148_a, this.R_4764_Y, this.P_1922_E, this.u_1723_Y);
        T t = this.v_4262_N;
        int i = hashmappalette.n_1700_B(this.v_4262_N);
        int[] aint = new int[4096];
        for (int j = 0; j < 4096; ++j) {
            T t1 = this.n_1700_B(j);
            if (t1 != t) {
                t = t1;
                i = hashmappalette.n_1700_B(t1);
            }
            aint[j] = i;
        }
        q_2896_o listnbt = new q_2896_o();
        hashmappalette.J_1907_R(listnbt);
        compound.n_1700_B(paletteName, listnbt);
        int l = Math.max(4, u_530_F.P_1922_E(listnbt.size()));
        J_270_s bitarray = new J_270_s(l, 4096);
        for (int k = 0; k < aint.length; ++k) {
            bitarray.J_1907_R(k, aint[k]);
        }
        compound.n_1700_B(paletteDataName, bitarray.n_1700_B());
        this.J_1907_R();
    }

    public int R_4764_Y() {
        return 1 + this.w_1484_f.n_1700_B() + b_2585_i.n_1700_B(this.n_1700_B.J_1907_R()) + this.n_1700_B.n_1700_B().length * 8;
    }

    public boolean n_1700_B(Predicate<T> p_235963_1_) {
        return this.w_1484_f.n_1700_B(p_235963_1_);
    }

    public void n_1700_B(n_1700_B<T> countConsumerIn) {
        Int2IntOpenHashMap int2intmap = new Int2IntOpenHashMap();
        this.n_1700_B.n_1700_B(arg_0 -> M_4466_T.n_1700_B((Int2IntMap)int2intmap, arg_0));
        int2intmap.int2IntEntrySet().forEach(p_225499_2_ -> countConsumerIn.accept(this.w_1484_f.n_1700_B(p_225499_2_.getIntKey()), p_225499_2_.getIntValue()));
    }

    @Override
    public Palette<T> getPalette() {
        return this.w_1484_f;
    }

    @Override
    public J_270_s getStorage() {
        return this.n_1700_B;
    }

    private static /* synthetic */ void n_1700_B(Int2IntMap int2intmap, int p_225498_1_) {
        int2intmap.put(p_225498_1_, int2intmap.get(p_225498_1_) + 1);
    }

    @FunctionalInterface
    public static interface n_1700_B<T> {
        public void accept(T var1, int var2);
    }
}


