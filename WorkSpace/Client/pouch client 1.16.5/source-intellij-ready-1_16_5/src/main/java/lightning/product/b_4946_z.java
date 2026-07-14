/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFixer
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.longs.Long2ByteMap
 *  it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 *  it.unimi.dsi.fastutil.longs.LongSet
 */
package lightning.product;

import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.longs.Long2ByteMap;
import it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lightning.product.ChunkStatus;
import lightning.product.SectionTracker;
import lightning.product.K_4074_S;
import lightning.product.P_3550_Z;
import lightning.product.T_1316_M;
import lightning.product.Y_1387_d;
import lightning.product.b_4911_h;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.j_3341_s;
import lightning.product.o_1967_f;
import lightning.product.o_4054_p;
import lightning.product.q_2232_A;
import lightning.product.y_2339_p;

public class b_4946_z
extends b_4911_h<o_4054_p> {
    private final n_1700_B n_1700_B;
    private final LongSet J_1907_R = new LongOpenHashSet();

    public b_4946_z(File folder, DataFixer fixer, boolean sync) {
        super(folder, o_4054_p::n_1700_B, o_4054_p::new, fixer, o_1967_f.s_956_w, sync);
        this.n_1700_B = new n_1700_B();
    }

    public void n_1700_B(c_1514_x pos, q_2232_A poiType) {
        ((o_4054_p)this.P_1922_E(SectionPos.n_1700_B(pos).P_4830_p())).n_1700_B(pos, poiType);
    }

    public void n_1700_B(c_1514_x pos) {
        ((o_4054_p)this.P_1922_E(SectionPos.n_1700_B(pos).P_4830_p())).n_1700_B(pos);
    }

    public long n_1700_B(Predicate<q_2232_A> p_219145_1_, c_1514_x pos, int distance, J_1907_R status) {
        return this.R_4764_Y(p_219145_1_, pos, distance, status).count();
    }

    public boolean n_1700_B(q_2232_A type, c_1514_x pos) {
        Optional<q_2232_A> optional = ((o_4054_p)this.P_1922_E(SectionPos.n_1700_B(pos).P_4830_p())).R_4764_Y(pos);
        return optional.isPresent() && optional.get().equals(type);
    }

    public Stream<y_2339_p> J_1907_R(Predicate<q_2232_A> typePredicate, c_1514_x pos, int distance, J_1907_R status) {
        int i = Math.floorDiv(distance, 16) + 1;
        return Y_1387_d.n_1700_B(new Y_1387_d(pos), i).flatMap(chunkPos -> this.n_1700_B(typePredicate, (Y_1387_d)chunkPos, status)).filter(poi -> {
            c_1514_x blockpos = poi.P_1922_E();
            return Math.abs(blockpos.getX() - pos.getX()) <= distance && Math.abs(blockpos.getZ() - pos.getZ()) <= distance;
        });
    }

    public Stream<y_2339_p> R_4764_Y(Predicate<q_2232_A> typePredicate, c_1514_x pos, int distance, J_1907_R status) {
        int i = distance * distance;
        return this.J_1907_R(typePredicate, pos, distance, status).filter(p_226349_2_ -> p_226349_2_.P_1922_E().distanceSq(pos) <= (double)i);
    }

    public Stream<y_2339_p> n_1700_B(Predicate<q_2232_A> p_219137_1_, Y_1387_d posChunk, J_1907_R status) {
        return IntStream.range(0, 16).boxed().map(y -> this.G_564_y(SectionPos.n_1700_B(posChunk, (int)y).P_4830_p())).filter(Optional::isPresent).flatMap(data -> ((o_4054_p)data.get()).n_1700_B(p_219137_1_, status));
    }

    public Stream<c_1514_x> n_1700_B(Predicate<q_2232_A> typePredicate, Predicate<c_1514_x> posPredicate, c_1514_x pos, int distance, J_1907_R status) {
        return this.R_4764_Y(typePredicate, pos, distance, status).map(y_2339_p::P_1922_E).filter(posPredicate);
    }

    public Stream<c_1514_x> J_1907_R(Predicate<q_2232_A> p_242324_1_, Predicate<c_1514_x> posPredicate, c_1514_x p_242324_3_, int distance, J_1907_R status) {
        return this.n_1700_B(p_242324_1_, posPredicate, p_242324_3_, distance, status).sorted(Comparator.comparingDouble(pos -> pos.distanceSq(p_242324_3_)));
    }

    public Optional<c_1514_x> R_4764_Y(Predicate<q_2232_A> typePredicate, Predicate<c_1514_x> posPredicate, c_1514_x pos, int distance, J_1907_R status) {
        return this.n_1700_B(typePredicate, posPredicate, pos, distance, status).findFirst();
    }

    public Optional<c_1514_x> G_564_y(Predicate<q_2232_A> typePredicate, c_1514_x pos, int distance, J_1907_R status) {
        return this.R_4764_Y(typePredicate, pos, distance, status).map(y_2339_p::P_1922_E).min(Comparator.comparingDouble(pos2 -> pos2.distanceSq(pos)));
    }

    public Optional<c_1514_x> n_1700_B(Predicate<q_2232_A> typePredicate, Predicate<c_1514_x> posPredicate, c_1514_x pos, int distance) {
        return this.R_4764_Y(typePredicate, pos, distance, lightning.product.b_4946_z$J_1907_R.n_1700_B).filter(p_219129_1_ -> posPredicate.test(p_219129_1_.P_1922_E())).findFirst().map(p_219152_0_ -> {
            p_219152_0_.n_1700_B();
            return p_219152_0_.P_1922_E();
        });
    }

    public Optional<c_1514_x> n_1700_B(Predicate<q_2232_A> typePredicate, Predicate<c_1514_x> posPredicate, J_1907_R status, c_1514_x pos, int distance, Random rand) {
        List list = this.R_4764_Y(typePredicate, pos, distance, status).collect(Collectors.toList());
        Collections.shuffle(list, rand);
        return list.stream().filter(p_234143_1_ -> posPredicate.test(p_234143_1_.P_1922_E())).findFirst().map(y_2339_p::P_1922_E);
    }

    public boolean J_1907_R(c_1514_x pos) {
        return ((o_4054_p)this.P_1922_E(SectionPos.n_1700_B(pos).P_4830_p())).J_1907_R(pos);
    }

    public boolean n_1700_B(c_1514_x pos, Predicate<q_2232_A> p_219138_2_) {
        return this.G_564_y(SectionPos.n_1700_B(pos).P_4830_p()).map(data -> data.n_1700_B(pos, p_219138_2_)).orElse(false);
    }

    public Optional<q_2232_A> R_4764_Y(c_1514_x pos) {
        o_4054_p pointofinterestdata = (o_4054_p)this.P_1922_E(SectionPos.n_1700_B(pos).P_4830_p());
        return pointofinterestdata.R_4764_Y(pos);
    }

    public int n_1700_B(SectionPos sectionPos) {
        this.n_1700_B.n_1700_B();
        return this.n_1700_B.R_4764_Y(sectionPos.P_4830_p());
    }

    private boolean u_1723_Y(long p_219154_1_) {
        Optional optional = this.R_4764_Y(p_219154_1_);
        return optional == null ? false : optional.map(data -> data.n_1700_B(q_2232_A.J_1907_R, lightning.product.b_4946_z$J_1907_R.J_1907_R).count() > 0L).orElse(false);
    }

    @Override
    public void n_1700_B(BooleanSupplier p_219115_1_) {
        super.n_1700_B(p_219115_1_);
        this.n_1700_B.n_1700_B();
    }

    @Override
    protected void n_1700_B(long sectionPosIn) {
        super.n_1700_B(sectionPosIn);
        this.n_1700_B.J_1907_R(sectionPosIn, this.n_1700_B.J_1907_R(sectionPosIn), false);
    }

    @Override
    protected void J_1907_R(long p_219111_1_) {
        this.n_1700_B.J_1907_R(p_219111_1_, this.n_1700_B.J_1907_R(p_219111_1_), false);
    }

    public void n_1700_B(Y_1387_d pos, P_3550_Z section) {
        SectionPos sectionpos = SectionPos.n_1700_B(pos, section.v_4262_N() >> 4);
        j_3341_s.n_1700_B(this.G_564_y(sectionpos.P_4830_p()), (T data) -> data.n_1700_B((BiConsumer<c_1514_x, q_2232_A> p_234145_3_) -> {
            if (b_4946_z.n_1700_B(section)) {
                this.n_1700_B(section, sectionpos, (BiConsumer<c_1514_x, q_2232_A>)p_234145_3_);
            }
        }), () -> {
            if (b_4946_z.n_1700_B(section)) {
                o_4054_p pointofinterestdata = (o_4054_p)this.P_1922_E(sectionpos.P_4830_p());
                this.n_1700_B(section, sectionpos, pointofinterestdata::n_1700_B);
            }
        });
    }

    private static boolean n_1700_B(P_3550_Z section) {
        return section.n_1700_B(q_2232_A.k_2293_S::contains);
    }

    private void n_1700_B(P_3550_Z section, SectionPos sectionPos, BiConsumer<c_1514_x, q_2232_A> posToTypeConsumer) {
        sectionPos.h_1847_R().forEach(pos -> {
            K_4074_S blockstate = section.n_1700_B(SectionPos.J_1907_R(pos.getX()), SectionPos.J_1907_R(pos.getY()), SectionPos.J_1907_R(pos.getZ()));
            q_2232_A.n_1700_B(blockstate).ifPresent(type -> posToTypeConsumer.accept((c_1514_x)pos, (q_2232_A)type));
        });
    }

    public void n_1700_B(T_1316_M worldReader, c_1514_x pos, int coordinateOffset) {
        SectionPos.J_1907_R(new Y_1387_d(pos), Math.floorDiv(coordinateOffset, 16)).map(sectionPos -> Pair.of((Object)sectionPos, this.G_564_y(sectionPos.P_4830_p()))).filter(p_234146_0_ -> ((Optional)p_234146_0_.getSecond()).map(o_4054_p::n_1700_B).orElse(false) == false).map(p_234140_0_ -> ((SectionPos)p_234140_0_.getFirst()).M_588_G()).filter(chunkPos -> this.J_1907_R.add(chunkPos.n_1700_B())).forEach(chunkPos -> worldReader.n_1700_B(chunkPos.J_1907_R, chunkPos.R_4764_Y, ChunkStatus.n_1700_B));
    }

    final class n_1700_B
    extends SectionTracker {
        private final Long2ByteMap J_1907_R;

        protected n_1700_B() {
            super(7, 16, 256);
            this.J_1907_R = new Long2ByteOpenHashMap();
            this.J_1907_R.defaultReturnValue((byte)7);
        }

        @Override
        protected int J_1907_R(long pos) {
            return b_4946_z.this.u_1723_Y(pos) ? 0 : 7;
        }

        @Override
        protected int R_4764_Y(long sectionPosIn) {
            return this.J_1907_R.get(sectionPosIn);
        }

        @Override
        protected void n_1700_B(long sectionPosIn, int level) {
            if (level > 6) {
                this.J_1907_R.remove(sectionPosIn);
            } else {
                this.J_1907_R.put(sectionPosIn, (byte)level);
            }
        }

        public void n_1700_B() {
            super.n_1700_B(Integer.MAX_VALUE);
        }
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(y_2339_p::R_4764_Y);
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(y_2339_p::G_564_y);
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R(poi -> true);
        private final Predicate<? super y_2339_p> G_564_y;
        private static final /* synthetic */ J_1907_R[] P_1922_E;

        public static J_1907_R[] values() {
            return (J_1907_R[])P_1922_E.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(Predicate<? super y_2339_p> test) {
            this.G_564_y = test;
        }

        public Predicate<? super y_2339_p> n_1700_B() {
            return this.G_564_y;
        }

        private static /* synthetic */ J_1907_R[] J_1907_R() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            P_1922_E = lightning.product.b_4946_z$J_1907_R.J_1907_R();
        }
    }
}


