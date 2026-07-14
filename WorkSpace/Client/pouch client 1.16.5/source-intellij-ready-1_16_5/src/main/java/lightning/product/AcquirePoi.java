/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lightning.product.DebugPackets;
import lightning.product.F_427_K;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.b_1722_e;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.PathfinderMob;
import lightning.product.q_2232_A;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class AcquirePoi
extends Behavior<PathfinderMob> {
    private final q_2232_A n_1700_B;
    private final MemoryModuleType<F_427_K> R_4764_Y;
    private final boolean G_564_y;
    private final Optional<Byte> P_1922_E;
    private long u_1723_Y;
    private final Long2ObjectMap<n_1700_B> v_4262_N = new Long2ObjectOpenHashMap();

    public AcquirePoi(q_2232_A p_i241906_1_, MemoryModuleType<F_427_K> p_i241906_2_, MemoryModuleType<F_427_K> p_i241906_3_, boolean p_i241906_4_, Optional<Byte> p_i241906_5_) {
        super((Map<MemoryModuleType<?>, S_50_d>)AcquirePoi.n_1700_B(p_i241906_2_, p_i241906_3_));
        this.n_1700_B = p_i241906_1_;
        this.R_4764_Y = p_i241906_3_;
        this.G_564_y = p_i241906_4_;
        this.P_1922_E = p_i241906_5_;
    }

    public AcquirePoi(q_2232_A p_i241907_1_, MemoryModuleType<F_427_K> p_i241907_2_, boolean p_i241907_3_, Optional<Byte> p_i241907_4_) {
        this(p_i241907_1_, p_i241907_2_, p_i241907_2_, p_i241907_3_, p_i241907_4_);
    }

    private static ImmutableMap<MemoryModuleType<?>, S_50_d> n_1700_B(MemoryModuleType<F_427_K> p_233841_0_, MemoryModuleType<F_427_K> p_233841_1_) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        builder.put(p_233841_0_, (Object)S_50_d.J_1907_R);
        if (p_233841_1_ != p_233841_0_) {
            builder.put(p_233841_1_, (Object)S_50_d.J_1907_R);
        }
        return builder.build();
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, PathfinderMob owner) {
        if (this.G_564_y && owner.d_()) {
            return false;
        }
        if (this.u_1723_Y == 0L) {
            this.u_1723_Y = owner.O_508_d.X_933_l() + (long)worldIn.w_1457_N.nextInt(20);
            return false;
        }
        return worldIn.X_933_l() >= this.u_1723_Y;
    }

    protected void n_1700_B(e_3591_l worldIn, PathfinderMob entityIn, long gameTimeIn) {
        this.u_1723_Y = gameTimeIn + 20L + (long)worldIn.e_4240_b().nextInt(20);
        b_4946_z pointofinterestmanager = worldIn.p_178_J();
        this.v_4262_N.long2ObjectEntrySet().removeIf(p_241362_2_ -> !((n_1700_B)p_241362_2_.getValue()).J_1907_R(gameTimeIn));
        Predicate<c_1514_x> predicate = p_220603_3_ -> {
            n_1700_B gatherpoitask$retrymarker = (n_1700_B)this.v_4262_N.get(p_220603_3_.toLong());
            if (gatherpoitask$retrymarker == null) {
                return true;
            }
            if (!gatherpoitask$retrymarker.R_4764_Y(gameTimeIn)) {
                return false;
            }
            gatherpoitask$retrymarker.n_1700_B(gameTimeIn);
            return true;
        };
        Set<c_1514_x> set = pointofinterestmanager.J_1907_R(this.n_1700_B.J_1907_R(), predicate, entityIn.b_2312_j(), 48, b_4946_z.J_1907_R.n_1700_B).limit(5L).collect(Collectors.toSet());
        b_1722_e path = entityIn.e_4240_b().n_1700_B(set, this.n_1700_B.R_4764_Y());
        if (path != null && path.s_956_w()) {
            c_1514_x blockpos1 = path.P_4830_p();
            pointofinterestmanager.R_4764_Y(blockpos1).ifPresent(p_225441_5_ -> {
                pointofinterestmanager.n_1700_B(this.n_1700_B.J_1907_R(), p_225442_1_ -> p_225442_1_.equals(blockpos1), blockpos1, 1);
                entityIn.y_1945_D().n_1700_B(this.R_4764_Y, F_427_K.n_1700_B(worldIn.g_2268_R(), blockpos1));
                this.P_1922_E.ifPresent(p_242291_2_ -> worldIn.n_1700_B((N_4263_v)entityIn, (byte)p_242291_2_));
                this.v_4262_N.clear();
                DebugPackets.R_4764_Y(worldIn, blockpos1);
            });
        } else {
            for (c_1514_x blockpos : set) {
                this.v_4262_N.computeIfAbsent(blockpos.toLong(), p_241363_3_ -> new n_1700_B(entityIn.O_508_d.w_1457_N, gameTimeIn));
            }
        }
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (PathfinderMob)r_4811_B2, l);
    }

    static class n_1700_B {
        private final Random n_1700_B;
        private long J_1907_R;
        private long R_4764_Y;
        private int G_564_y;

        n_1700_B(Random p_i241233_1_, long p_i241233_2_) {
            this.n_1700_B = p_i241233_1_;
            this.n_1700_B(p_i241233_2_);
        }

        public void n_1700_B(long p_241370_1_) {
            this.J_1907_R = p_241370_1_;
            int i = this.G_564_y + this.n_1700_B.nextInt(40) + 40;
            this.G_564_y = Math.min(i, 400);
            this.R_4764_Y = p_241370_1_ + (long)this.G_564_y;
        }

        public boolean J_1907_R(long p_241371_1_) {
            return p_241371_1_ - this.J_1907_R < 400L;
        }

        public boolean R_4764_Y(long p_241372_1_) {
            return p_241372_1_ >= this.R_4764_Y;
        }

        public String toString() {
            return "RetryMarker{, previousAttemptAt=" + this.J_1907_R + ", nextScheduledAttemptAt=" + this.R_4764_Y + ", currentDelay=" + this.G_564_y + "}";
        }
    }
}


