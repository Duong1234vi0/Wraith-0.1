/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Either
 *  it.unimi.dsi.fastutil.shorts.ShortArraySet
 *  it.unimi.dsi.fastutil.shorts.ShortSet
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.datafixers.util.Either;
import it.unimi.dsi.fastutil.shorts.ShortArraySet;
import it.unimi.dsi.fastutil.shorts.ShortSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.ChunkStatus;
import lightning.product.H_1748_a;
import lightning.product.K_4074_S;
import lightning.product.K_4719_o;
import lightning.product.ClientboundLightUpdatePacket;
import lightning.product.ClientboundBlockUpdatePacket;
import lightning.product.P_3550_Z;
import lightning.product.ClientboundSectionBlocksUpdatePacket;
import lightning.product.R_1900_x;
import lightning.product.ImposterProtoChunk;
import lightning.product.Y_1387_d;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.ChunkAccess;
import lightning.product.i_2154_H;
import lightning.product.j_3341_s;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.n_1254_X;
import lightning.product.Packet;
import lightning.product.u_530_F;
import lightning.product.y_1195_s;

public class y_3683_b {
    public static final Either<ChunkAccess, n_1700_B> n_1700_B = Either.right((Object)lightning.product.y_3683_b$n_1700_B.J_1907_R);
    public static final CompletableFuture<Either<ChunkAccess, n_1700_B>> J_1907_R = CompletableFuture.completedFuture(n_1700_B);
    public static final Either<H_1748_a, n_1700_B> R_4764_Y = Either.right((Object)lightning.product.y_3683_b$n_1700_B.J_1907_R);
    private static final CompletableFuture<Either<H_1748_a, n_1700_B>> G_564_y = CompletableFuture.completedFuture(R_4764_Y);
    private static final List<ChunkStatus> P_1922_E = ChunkStatus.n_1700_B();
    private static final G_564_y[] u_1723_Y = lightning.product.y_3683_b$G_564_y.values();
    private final AtomicReferenceArray<CompletableFuture<Either<ChunkAccess, n_1700_B>>> v_4262_N = new AtomicReferenceArray(P_1922_E.size());
    private volatile CompletableFuture<Either<H_1748_a, n_1700_B>> w_1484_f = G_564_y;
    private volatile CompletableFuture<Either<H_1748_a, n_1700_B>> t_148_a = G_564_y;
    private volatile CompletableFuture<Either<H_1748_a, n_1700_B>> s_956_w = G_564_y;
    private CompletableFuture<ChunkAccess> u_2550_I = CompletableFuture.completedFuture(null);
    private int M_588_G;
    private int P_4830_p;
    private int h_1847_R;
    private final Y_1387_d Q_4569_t;
    private boolean M_182_A;
    private final ShortSet[] t_1786_h = new ShortSet[16];
    private int multiplayerClientSuggestionProvider;
    private int w_1457_N;
    private final R_1900_x Y_601_j;
    private final J_1907_R Y_259_p;
    private final R_4764_Y Q_2552_b;
    private boolean C_2741_M;
    private boolean k_2293_S;

    public y_3683_b(Y_1387_d chunkPos, int level, R_1900_x lightManager, J_1907_R p_i50716_4_, R_4764_Y playerProvider) {
        this.Q_4569_t = chunkPos;
        this.Y_601_j = lightManager;
        this.Y_259_p = p_i50716_4_;
        this.Q_2552_b = playerProvider;
        this.P_4830_p = this.M_588_G = y_1195_s.J_1907_R + 1;
        this.h_1847_R = this.M_588_G;
        this.n_1700_B(level);
    }

    public CompletableFuture<Either<ChunkAccess, n_1700_B>> n_1700_B(ChunkStatus p_219301_1_) {
        CompletableFuture<Either<ChunkAccess, n_1700_B>> completablefuture = this.v_4262_N.get(p_219301_1_.R_4764_Y());
        return completablefuture == null ? J_1907_R : completablefuture;
    }

    public CompletableFuture<Either<ChunkAccess, n_1700_B>> J_1907_R(ChunkStatus p_225410_1_) {
        return y_3683_b.J_1907_R(this.P_4830_p).J_1907_R(p_225410_1_) ? this.n_1700_B(p_225410_1_) : J_1907_R;
    }

    public CompletableFuture<Either<H_1748_a, n_1700_B>> n_1700_B() {
        return this.t_148_a;
    }

    public CompletableFuture<Either<H_1748_a, n_1700_B>> J_1907_R() {
        return this.s_956_w;
    }

    public CompletableFuture<Either<H_1748_a, n_1700_B>> R_4764_Y() {
        return this.w_1484_f;
    }

    @Nullable
    public H_1748_a G_564_y() {
        CompletableFuture<Either<H_1748_a, n_1700_B>> completablefuture = this.n_1700_B();
        Either<H_1748_a, n_1700_B> either = completablefuture.getNow((Either<H_1748_a, n_1700_B>)((Either)null));
        return either == null ? null : either.left().orElse(null);
    }

    @Nullable
    public ChunkStatus P_1922_E() {
        for (int i = P_1922_E.size() - 1; i >= 0; --i) {
            ChunkStatus chunkstatus = P_1922_E.get(i);
            CompletableFuture<Either<ChunkAccess, n_1700_B>> completablefuture = this.n_1700_B(chunkstatus);
            if (!completablefuture.getNow(n_1700_B).left().isPresent()) continue;
            return chunkstatus;
        }
        return null;
    }

    @Nullable
    public ChunkAccess u_1723_Y() {
        for (int i = P_1922_E.size() - 1; i >= 0; --i) {
            Optional optional;
            ChunkStatus chunkstatus = P_1922_E.get(i);
            CompletableFuture<Either<ChunkAccess, n_1700_B>> completablefuture = this.n_1700_B(chunkstatus);
            if (completablefuture.isCompletedExceptionally() || !(optional = completablefuture.getNow(n_1700_B).left()).isPresent()) continue;
            return (ChunkAccess)optional.get();
        }
        return null;
    }

    public CompletableFuture<ChunkAccess> v_4262_N() {
        return this.u_2550_I;
    }

    public void n_1700_B(c_1514_x p_244386_1_) {
        H_1748_a chunk = this.G_564_y();
        if (chunk != null) {
            byte b0 = (byte)SectionPos.n_1700_B(p_244386_1_.getY());
            if (this.t_1786_h[b0] == null) {
                this.M_182_A = true;
                this.t_1786_h[b0] = new ShortArraySet();
            }
            this.t_1786_h[b0].add(SectionPos.J_1907_R(p_244386_1_));
        }
    }

    public void n_1700_B(K_4719_o type, int sectionY) {
        H_1748_a chunk = this.G_564_y();
        if (chunk != null) {
            chunk.setModified(true);
            if (type == K_4719_o.n_1700_B) {
                this.w_1457_N |= 1 << sectionY - -1;
            } else {
                this.multiplayerClientSuggestionProvider |= 1 << sectionY - -1;
            }
        }
    }

    public void n_1700_B(H_1748_a chunkIn) {
        if (this.M_182_A || this.w_1457_N != 0 || this.multiplayerClientSuggestionProvider != 0) {
            b_4507_u world = chunkIn.getWorld();
            int i = 0;
            for (int j = 0; j < this.t_1786_h.length; ++j) {
                i += this.t_1786_h[j] != null ? this.t_1786_h[j].size() : 0;
            }
            this.k_2293_S |= i >= 64;
            if (this.w_1457_N != 0 || this.multiplayerClientSuggestionProvider != 0) {
                this.n_1700_B(new ClientboundLightUpdatePacket(chunkIn.getPos(), this.Y_601_j, this.w_1457_N, this.multiplayerClientSuggestionProvider, true), !this.k_2293_S);
                this.w_1457_N = 0;
                this.multiplayerClientSuggestionProvider = 0;
            }
            for (int k = 0; k < this.t_1786_h.length; ++k) {
                ShortSet shortset = this.t_1786_h[k];
                if (shortset == null) continue;
                SectionPos sectionpos = SectionPos.n_1700_B(chunkIn.getPos(), k);
                if (shortset.size() == 1) {
                    c_1514_x blockpos = sectionpos.v_4262_N(shortset.iterator().nextShort());
                    K_4074_S blockstate = world.getBlockState(blockpos);
                    this.n_1700_B(new ClientboundBlockUpdatePacket(blockpos, blockstate), false);
                    this.n_1700_B(world, blockpos, blockstate);
                } else {
                    P_3550_Z chunksection = chunkIn.getSections()[sectionpos.getY()];
                    ClientboundSectionBlocksUpdatePacket smultiblockchangepacket = new ClientboundSectionBlocksUpdatePacket(sectionpos, shortset, chunksection, this.k_2293_S);
                    this.n_1700_B(smultiblockchangepacket, false);
                    smultiblockchangepacket.n_1700_B((c_1514_x p_244387_2_, K_4074_S p_244387_3_) -> this.n_1700_B(world, (c_1514_x)p_244387_2_, (K_4074_S)p_244387_3_));
                }
                this.t_1786_h[k] = null;
            }
            this.M_182_A = false;
        }
    }

    private void n_1700_B(b_4507_u p_244385_1_, c_1514_x p_244385_2_, K_4074_S p_244385_3_) {
        if (p_244385_3_.J_1907_R().G_564_y()) {
            this.n_1700_B(p_244385_1_, p_244385_2_);
        }
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x posIn) {
        ClientboundBlockEntityDataPacket supdatetileentitypacket;
        i_2154_H tileentity = worldIn.getTileEntity(posIn);
        if (tileentity != null && (supdatetileentitypacket = tileentity.G_()) != null) {
            this.n_1700_B(supdatetileentitypacket, false);
        }
    }

    private void n_1700_B(Packet<?> packetIn, boolean boundaryOnly) {
        this.Q_2552_b.n_1700_B(this.Q_4569_t, boundaryOnly).forEach(p_219304_1_ -> p_219304_1_.n_1700_B.n_1700_B(packetIn));
    }

    public CompletableFuture<Either<ChunkAccess, n_1700_B>> n_1700_B(ChunkStatus p_219276_1_, y_1195_s p_219276_2_) {
        Either<ChunkAccess, n_1700_B> either;
        int i = p_219276_1_.R_4764_Y();
        CompletableFuture<Either<ChunkAccess, n_1700_B>> completablefuture = this.v_4262_N.get(i);
        if (completablefuture != null && ((either = completablefuture.getNow((Either<ChunkAccess, n_1700_B>)((Either)null))) == null || either.left().isPresent())) {
            return completablefuture;
        }
        if (y_3683_b.J_1907_R(this.P_4830_p).J_1907_R(p_219276_1_)) {
            CompletableFuture<Either<ChunkAccess, n_1700_B>> completablefuture1 = p_219276_2_.n_1700_B(this, p_219276_1_);
            this.n_1700_B(completablefuture1);
            this.v_4262_N.set(i, completablefuture1);
            return completablefuture1;
        }
        return completablefuture == null ? J_1907_R : completablefuture;
    }

    private void n_1700_B(CompletableFuture<? extends Either<? extends ChunkAccess, n_1700_B>> eitherChunk) {
        this.u_2550_I = this.u_2550_I.thenCombine(eitherChunk, (p_219295_0_, p_219295_1_) -> (ChunkAccess)p_219295_1_.map(p_219283_0_ -> p_219283_0_, p_219288_1_ -> p_219295_0_));
    }

    public G_564_y w_1484_f() {
        return y_3683_b.R_4764_Y(this.P_4830_p);
    }

    public Y_1387_d t_148_a() {
        return this.Q_4569_t;
    }

    public int s_956_w() {
        return this.P_4830_p;
    }

    public int u_2550_I() {
        return this.h_1847_R;
    }

    private void G_564_y(int p_219275_1_) {
        this.h_1847_R = p_219275_1_;
    }

    public void n_1700_B(int level) {
        this.P_4830_p = level;
    }

    protected void n_1700_B(y_1195_s chunkManagerIn) {
        ChunkStatus chunkstatus = y_3683_b.J_1907_R(this.M_588_G);
        ChunkStatus chunkstatus1 = y_3683_b.J_1907_R(this.P_4830_p);
        boolean flag = this.M_588_G <= y_1195_s.J_1907_R;
        boolean flag1 = this.P_4830_p <= y_1195_s.J_1907_R;
        G_564_y chunkholder$locationtype = y_3683_b.R_4764_Y(this.M_588_G);
        G_564_y chunkholder$locationtype1 = y_3683_b.R_4764_Y(this.P_4830_p);
        if (flag) {
            int i;
            Either either = Either.right((Object)new n_1700_B(){

                public String toString() {
                    return "Unloaded ticket level " + y_3683_b.this.Q_4569_t.toString();
                }
            });
            int n = i = flag1 ? chunkstatus1.R_4764_Y() + 1 : 0;
            while (i <= chunkstatus.R_4764_Y()) {
                CompletableFuture<Either<ChunkAccess, n_1700_B>> completablefuture = this.v_4262_N.get(i);
                if (completablefuture != null) {
                    completablefuture.complete((Either<ChunkAccess, n_1700_B>)either);
                } else {
                    this.v_4262_N.set(i, CompletableFuture.completedFuture(either));
                }
                ++i;
            }
        }
        boolean flag5 = chunkholder$locationtype.n_1700_B(lightning.product.y_3683_b$G_564_y.J_1907_R);
        boolean flag6 = chunkholder$locationtype1.n_1700_B(lightning.product.y_3683_b$G_564_y.J_1907_R);
        this.C_2741_M |= flag6;
        if (!flag5 && flag6) {
            this.w_1484_f = chunkManagerIn.J_1907_R(this);
            this.n_1700_B(this.w_1484_f);
        }
        if (flag5 && !flag6) {
            CompletableFuture<Either<H_1748_a, n_1700_B>> completablefuture1 = this.w_1484_f;
            this.w_1484_f = G_564_y;
            this.n_1700_B((CompletableFuture<? extends Either<? extends ChunkAccess, n_1700_B>>)completablefuture1.thenApply(p_222982_1_ -> p_222982_1_.ifLeft(chunkManagerIn::n_1700_B)));
        }
        boolean flag7 = chunkholder$locationtype.n_1700_B(lightning.product.y_3683_b$G_564_y.R_4764_Y);
        boolean flag2 = chunkholder$locationtype1.n_1700_B(lightning.product.y_3683_b$G_564_y.R_4764_Y);
        if (!flag7 && flag2) {
            this.t_148_a = chunkManagerIn.n_1700_B(this);
            this.n_1700_B(this.t_148_a);
        }
        if (flag7 && !flag2) {
            this.t_148_a.complete(R_4764_Y);
            this.t_148_a = G_564_y;
        }
        boolean flag3 = chunkholder$locationtype.n_1700_B(lightning.product.y_3683_b$G_564_y.G_564_y);
        boolean flag4 = chunkholder$locationtype1.n_1700_B(lightning.product.y_3683_b$G_564_y.G_564_y);
        if (!flag3 && flag4) {
            if (this.s_956_w != G_564_y) {
                throw j_3341_s.R_4764_Y(new IllegalStateException());
            }
            this.s_956_w = chunkManagerIn.R_4764_Y(this.Q_4569_t);
            this.n_1700_B(this.s_956_w);
        }
        if (flag3 && !flag4) {
            this.s_956_w.complete(R_4764_Y);
            this.s_956_w = G_564_y;
        }
        this.Y_259_p.n_1700_B(this.Q_4569_t, this::u_2550_I, this.P_4830_p, this::G_564_y);
        this.M_588_G = this.P_4830_p;
    }

    public static ChunkStatus J_1907_R(int level) {
        return level < 33 ? ChunkStatus.P_4830_p : ChunkStatus.n_1700_B(level - 33);
    }

    public static G_564_y R_4764_Y(int level) {
        return u_1723_Y[u_530_F.n_1700_B(33 - level + 1, 0, u_1723_Y.length - 1)];
    }

    public boolean M_588_G() {
        return this.C_2741_M;
    }

    public void P_4830_p() {
        this.C_2741_M = y_3683_b.R_4764_Y(this.P_4830_p).n_1700_B(lightning.product.y_3683_b$G_564_y.J_1907_R);
    }

    public void n_1700_B(ImposterProtoChunk p_219294_1_) {
        for (int i = 0; i < this.v_4262_N.length(); ++i) {
            Optional optional;
            CompletableFuture<Either<ChunkAccess, n_1700_B>> completablefuture = this.v_4262_N.get(i);
            if (completablefuture == null || !(optional = completablefuture.getNow(n_1700_B).left()).isPresent() || !(optional.get() instanceof n_1254_X)) continue;
            this.v_4262_N.set(i, CompletableFuture.completedFuture(Either.left((Object)p_219294_1_)));
        }
        this.n_1700_B(CompletableFuture.completedFuture(Either.left((Object)p_219294_1_.w_1484_f())));
    }

    public static interface J_1907_R {
        public void n_1700_B(Y_1387_d var1, IntSupplier var2, int var3, IntConsumer var4);
    }

    public static interface R_4764_Y {
        public Stream<B_4088_l> n_1700_B(Y_1387_d var1, boolean var2);
    }

    public static final class G_564_y
    extends Enum<G_564_y> {
        public static final /* enum */ G_564_y n_1700_B = new G_564_y();
        public static final /* enum */ G_564_y J_1907_R = new G_564_y();
        public static final /* enum */ G_564_y R_4764_Y = new G_564_y();
        public static final /* enum */ G_564_y G_564_y = new G_564_y();
        private static final /* synthetic */ G_564_y[] P_1922_E;

        public static G_564_y[] values() {
            return (G_564_y[])P_1922_E.clone();
        }

        public static G_564_y valueOf(String name) {
            return Enum.valueOf(G_564_y.class, name);
        }

        public boolean n_1700_B(G_564_y type) {
            return this.ordinal() >= type.ordinal();
        }

        private static /* synthetic */ G_564_y[] n_1700_B() {
            return new G_564_y[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.y_3683_b$G_564_y.n_1700_B();
        }
    }

    public static interface n_1700_B {
        public static final n_1700_B J_1907_R = new n_1700_B(){

            public String toString() {
                return "UNLOADED";
            }
        };
    }
}


