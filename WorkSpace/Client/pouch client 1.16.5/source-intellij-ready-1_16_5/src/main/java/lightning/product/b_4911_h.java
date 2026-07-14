/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DataFixer
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.OptionalDynamic
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.OptionalDynamic;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.SharedConstants;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.Tag;
import lightning.product.b_4507_u;
import lightning.product.SectionPos;
import lightning.product.d_1030_n;
import lightning.product.j_3341_s;
import lightning.product.l_4118_l;
import lightning.product.o_1967_f;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class b_4911_h<R>
implements AutoCloseable {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final d_1030_n J_1907_R;
    private final Long2ObjectMap<Optional<R>> R_4764_Y = new Long2ObjectOpenHashMap();
    private final LongLinkedOpenHashSet G_564_y = new LongLinkedOpenHashSet();
    private final Function<Runnable, Codec<R>> P_1922_E;
    private final Function<Runnable, R> u_1723_Y;
    private final DataFixer v_4262_N;
    private final o_1967_f w_1484_f;

    public b_4911_h(File p_i231897_1_, Function<Runnable, Codec<R>> p_i231897_2_, Function<Runnable, R> p_i231897_3_, DataFixer p_i231897_4_, o_1967_f p_i231897_5_, boolean p_i231897_6_) {
        this.P_1922_E = p_i231897_2_;
        this.u_1723_Y = p_i231897_3_;
        this.v_4262_N = p_i231897_4_;
        this.w_1484_f = p_i231897_5_;
        this.J_1907_R = new d_1030_n(p_i231897_1_, p_i231897_6_, p_i231897_1_.getName());
    }

    protected void n_1700_B(BooleanSupplier p_219115_1_) {
        while (!this.G_564_y.isEmpty() && p_219115_1_.getAsBoolean()) {
            Y_1387_d chunkpos = SectionPos.n_1700_B(this.G_564_y.firstLong()).M_588_G();
            this.G_564_y(chunkpos);
        }
    }

    @Nullable
    protected Optional<R> R_4764_Y(long p_219106_1_) {
        return (Optional)this.R_4764_Y.get(p_219106_1_);
    }

    protected Optional<R> G_564_y(long p_219113_1_) {
        SectionPos sectionpos = SectionPos.n_1700_B(p_219113_1_);
        if (this.J_1907_R(sectionpos)) {
            return Optional.empty();
        }
        Optional<R> optional = this.R_4764_Y(p_219113_1_);
        if (optional != null) {
            return optional;
        }
        this.J_1907_R(sectionpos.M_588_G());
        optional = this.R_4764_Y(p_219113_1_);
        if (optional == null) {
            throw j_3341_s.R_4764_Y(new IllegalStateException());
        }
        return optional;
    }

    protected boolean J_1907_R(SectionPos p_219114_1_) {
        return b_4507_u.G_564_y(SectionPos.R_4764_Y(p_219114_1_.J_1907_R()));
    }

    protected R P_1922_E(long p_235995_1_) {
        Optional<R> optional = this.G_564_y(p_235995_1_);
        if (optional.isPresent()) {
            return optional.get();
        }
        R r = this.u_1723_Y.apply(() -> this.n_1700_B(p_235995_1_));
        this.R_4764_Y.put(p_235995_1_, Optional.of(r));
        return r;
    }

    private void J_1907_R(Y_1387_d p_219107_1_) {
        this.n_1700_B(p_219107_1_, l_4118_l.n_1700_B, this.R_4764_Y(p_219107_1_));
    }

    @Nullable
    private U_2912_j R_4764_Y(Y_1387_d p_223138_1_) {
        try {
            return this.J_1907_R.n_1700_B(p_223138_1_);
        }
        catch (IOException ioexception) {
            n_1700_B.error("Error reading chunk {} data from disk", (Object)p_223138_1_, (Object)ioexception);
            return null;
        }
    }

    private <T> void n_1700_B(Y_1387_d p_235992_1_, DynamicOps<T> p_235992_2_, @Nullable T p_235992_3_) {
        if (p_235992_3_ == null) {
            for (int i = 0; i < 16; ++i) {
                this.R_4764_Y.put(SectionPos.n_1700_B(p_235992_1_, i).P_4830_p(), Optional.empty());
            }
        } else {
            int k;
            Dynamic dynamic1 = new Dynamic(p_235992_2_, p_235992_3_);
            int j = b_4911_h.n_1700_B(dynamic1);
            boolean flag = j != (k = SharedConstants.n_1700_B().getWorldVersion());
            Dynamic dynamic = this.v_4262_N.update(this.w_1484_f.n_1700_B(), dynamic1, j, k);
            OptionalDynamic optionaldynamic = dynamic.get("Sections");
            for (int l = 0; l < 16; ++l) {
                long i1 = SectionPos.n_1700_B(p_235992_1_, l).P_4830_p();
                Optional optional = optionaldynamic.get(Integer.toString(l)).result().flatMap(p_235989_3_ -> this.P_1922_E.apply(() -> this.n_1700_B(i1)).parse(p_235989_3_).resultOrPartial(arg_0 -> ((Logger)n_1700_B).error(arg_0)));
                this.R_4764_Y.put(i1, optional);
                optional.ifPresent(p_235990_4_ -> {
                    this.J_1907_R(i1);
                    if (flag) {
                        this.n_1700_B(i1);
                    }
                });
            }
        }
    }

    private void G_564_y(Y_1387_d p_219117_1_) {
        Dynamic<Tag> dynamic = this.n_1700_B(p_219117_1_, l_4118_l.n_1700_B);
        Tag inbt = (Tag)dynamic.getValue();
        if (inbt instanceof U_2912_j) {
            this.J_1907_R.n_1700_B(p_219117_1_, (U_2912_j)inbt);
        } else {
            n_1700_B.error("Expected compound tag, got {}", (Object)inbt);
        }
    }

    private <T> Dynamic<T> n_1700_B(Y_1387_d p_235991_1_, DynamicOps<T> p_235991_2_) {
        HashMap map = Maps.newHashMap();
        for (int i = 0; i < 16; ++i) {
            long j = SectionPos.n_1700_B(p_235991_1_, i).P_4830_p();
            this.G_564_y.remove(j);
            Optional optional = (Optional)this.R_4764_Y.get(j);
            if (optional == null || !optional.isPresent()) continue;
            DataResult dataresult = this.P_1922_E.apply(() -> this.n_1700_B(j)).encodeStart(p_235991_2_, optional.get());
            String s = Integer.toString(i);
            dataresult.resultOrPartial(arg_0 -> ((Logger)n_1700_B).error(arg_0)).ifPresent(p_235994_3_ -> map.put(p_235991_2_.createString(s), p_235994_3_));
        }
        return new Dynamic(p_235991_2_, p_235991_2_.createMap((Map)ImmutableMap.of((Object)p_235991_2_.createString("Sections"), (Object)p_235991_2_.createMap((Map)map), (Object)p_235991_2_.createString("DataVersion"), (Object)p_235991_2_.createInt(SharedConstants.n_1700_B().getWorldVersion()))));
    }

    protected void J_1907_R(long p_219111_1_) {
    }

    protected void n_1700_B(long sectionPosIn) {
        Optional optional = (Optional)this.R_4764_Y.get(sectionPosIn);
        if (optional != null && optional.isPresent()) {
            this.G_564_y.add(sectionPosIn);
        } else {
            n_1700_B.warn("No data for position: {}", (Object)SectionPos.n_1700_B(sectionPosIn));
        }
    }

    private static int n_1700_B(Dynamic<?> p_235993_0_) {
        return p_235993_0_.get("DataVersion").asInt(1945);
    }

    public void n_1700_B(Y_1387_d p_219112_1_) {
        if (!this.G_564_y.isEmpty()) {
            for (int i = 0; i < 16; ++i) {
                long j = SectionPos.n_1700_B(p_219112_1_, i).P_4830_p();
                if (!this.G_564_y.contains(j)) continue;
                this.G_564_y(p_219112_1_);
                return;
            }
        }
    }

    @Override
    public void close() throws IOException {
        this.J_1907_R.close();
    }
}


