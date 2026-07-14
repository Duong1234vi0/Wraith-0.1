/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.LootContextParams;
import lightning.product.Projectile;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BaseFireBlock;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.EntityBasedExplosionDamageCalculator;
import lightning.product.ExplosionDamageCalculator;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.ProtectionEnchantment;
import lightning.product.n_1494_c;
import lightning.product.q_1704_m;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.PrimedTnt;
import lightning.product.u_530_F;

public class F_1241_B {
    private static final ExplosionDamageCalculator n_1700_B = new ExplosionDamageCalculator();
    private final boolean J_1907_R;
    private final n_1700_B R_4764_Y;
    private final Random G_564_y = new Random();
    private final b_4507_u P_1922_E;
    private final double u_1723_Y;
    private final double v_4262_N;
    private final double w_1484_f;
    @Nullable
    private final N_4263_v t_148_a;
    private final float s_956_w;
    private final P_11_z u_2550_I;
    private final ExplosionDamageCalculator M_588_G;
    private final List<c_1514_x> P_4830_p = Lists.newArrayList();
    private final Map<a_3913_L, e_2866_D> h_1847_R = Maps.newHashMap();

    public F_1241_B(b_4507_u worldIn, @Nullable N_4263_v entityIn, double x, double y, double z, float size, List<c_1514_x> affectedPositions) {
        this(worldIn, entityIn, x, y, z, size, false, lightning.product.F_1241_B$n_1700_B.R_4764_Y, affectedPositions);
    }

    public F_1241_B(b_4507_u worldIn, @Nullable N_4263_v exploderIn, double xIn, double yIn, double zIn, float sizeIn, boolean causesFireIn, n_1700_B modeIn, List<c_1514_x> affectedBlockPositionsIn) {
        this(worldIn, exploderIn, xIn, yIn, zIn, sizeIn, causesFireIn, modeIn);
        this.P_4830_p.addAll(affectedBlockPositionsIn);
    }

    public F_1241_B(b_4507_u worldIn, @Nullable N_4263_v exploderIn, double xIn, double yIn, double zIn, float sizeIn, boolean causesFireIn, n_1700_B modeIn) {
        this(worldIn, exploderIn, null, null, xIn, yIn, zIn, sizeIn, causesFireIn, modeIn);
    }

    public F_1241_B(b_4507_u world, @Nullable N_4263_v exploder, @Nullable P_11_z source, @Nullable ExplosionDamageCalculator context, double x, double y, double z, float size, boolean causesFire, n_1700_B mode) {
        this.P_1922_E = world;
        this.t_148_a = exploder;
        this.s_956_w = size;
        this.u_1723_Y = x;
        this.v_4262_N = y;
        this.w_1484_f = z;
        this.J_1907_R = causesFire;
        this.R_4764_Y = mode;
        this.u_2550_I = source == null ? P_11_z.n_1700_B(this) : source;
        this.M_588_G = context == null ? this.n_1700_B(exploder) : context;
    }

    private ExplosionDamageCalculator n_1700_B(@Nullable N_4263_v entity) {
        return entity == null ? n_1700_B : new EntityBasedExplosionDamageCalculator(entity);
    }

    public static float n_1700_B(e_2866_D explosionVector, N_4263_v entity) {
        I_4817_s axisalignedbb = entity.i_601_W();
        double d0 = 1.0 / ((axisalignedbb.maxX - axisalignedbb.minX) * 2.0 + 1.0);
        double d1 = 1.0 / ((axisalignedbb.maxY - axisalignedbb.minY) * 2.0 + 1.0);
        double d2 = 1.0 / ((axisalignedbb.maxZ - axisalignedbb.minZ) * 2.0 + 1.0);
        double d3 = (1.0 - Math.floor(1.0 / d0) * d0) / 2.0;
        double d4 = (1.0 - Math.floor(1.0 / d2) * d2) / 2.0;
        if (!(d0 < 0.0 || d1 < 0.0 || d2 < 0.0)) {
            int i = 0;
            int j = 0;
            float f = 0.0f;
            while (f <= 1.0f) {
                float f1 = 0.0f;
                while (f1 <= 1.0f) {
                    float f2 = 0.0f;
                    while (f2 <= 1.0f) {
                        double d7;
                        double d6;
                        double d5 = u_530_F.G_564_y((double)f, axisalignedbb.minX, axisalignedbb.maxX);
                        e_2866_D vector3d = new e_2866_D(d5 + d3, d6 = u_530_F.G_564_y((double)f1, axisalignedbb.minY, axisalignedbb.maxY), (d7 = u_530_F.G_564_y((double)f2, axisalignedbb.minZ, axisalignedbb.maxZ)) + d4);
                        if (entity.O_508_d.n_1700_B(new ClipContext(vector3d, explosionVector, ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, entity)).R_4764_Y() == HitResult.n_1700_B.n_1700_B) {
                            ++i;
                        }
                        ++j;
                        f2 = (float)((double)f2 + d2);
                    }
                    f1 = (float)((double)f1 + d1);
                }
                f = (float)((double)f + d0);
            }
            return (float)i / (float)j;
        }
        return 0.0f;
    }

    public void n_1700_B() {
        HashSet set = Sets.newHashSet();
        int i = 16;
        for (int j = 0; j < 16; ++j) {
            for (int k = 0; k < 16; ++k) {
                for (int l = 0; l < 16; ++l) {
                    if (j != 0 && j != 15 && k != 0 && k != 15 && l != 0 && l != 15) continue;
                    double d0 = (float)j / 15.0f * 2.0f - 1.0f;
                    double d1 = (float)k / 15.0f * 2.0f - 1.0f;
                    double d2 = (float)l / 15.0f * 2.0f - 1.0f;
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    d0 /= d3;
                    d1 /= d3;
                    d2 /= d3;
                    double d4 = this.u_1723_Y;
                    double d6 = this.v_4262_N;
                    double d8 = this.w_1484_f;
                    float f1 = 0.3f;
                    for (float f = this.s_956_w * (0.7f + this.P_1922_E.w_1457_N.nextFloat() * 0.6f); f > 0.0f; f -= 0.22500001f) {
                        FluidState fluidstate;
                        c_1514_x blockpos = new c_1514_x(d4, d6, d8);
                        K_4074_S blockstate = this.P_1922_E.getBlockState(blockpos);
                        Optional<Float> optional = this.M_588_G.n_1700_B(this, (BlockGetter)this.P_1922_E, blockpos, blockstate, fluidstate = this.P_1922_E.getFluidState(blockpos));
                        if (optional.isPresent()) {
                            f -= (optional.get().floatValue() + 0.3f) * 0.3f;
                        }
                        if (f > 0.0f && this.M_588_G.n_1700_B(this, (BlockGetter)this.P_1922_E, blockpos, blockstate, f)) {
                            set.add(blockpos);
                        }
                        d4 += d0 * (double)0.3f;
                        d6 += d1 * (double)0.3f;
                        d8 += d2 * (double)0.3f;
                    }
                }
            }
        }
        this.P_4830_p.addAll(set);
        float f2 = this.s_956_w * 2.0f;
        int k1 = u_530_F.R_4764_Y(this.u_1723_Y - (double)f2 - 1.0);
        int l1 = u_530_F.R_4764_Y(this.u_1723_Y + (double)f2 + 1.0);
        int i2 = u_530_F.R_4764_Y(this.v_4262_N - (double)f2 - 1.0);
        int i1 = u_530_F.R_4764_Y(this.v_4262_N + (double)f2 + 1.0);
        int j2 = u_530_F.R_4764_Y(this.w_1484_f - (double)f2 - 1.0);
        int j1 = u_530_F.R_4764_Y(this.w_1484_f + (double)f2 + 1.0);
        List<N_4263_v> list = this.P_1922_E.n_1700_B(this.t_148_a, new I_4817_s(k1, i2, j2, l1, i1, j1));
        e_2866_D vector3d = new e_2866_D(this.u_1723_Y, this.v_4262_N, this.w_1484_f);
        for (int k2 = 0; k2 < list.size(); ++k2) {
            a_3913_L playerentity;
            double d9;
            double d7;
            double d5;
            double d13;
            double d12;
            N_4263_v entity = list.get(k2);
            if (entity.l_1268_F() || !((d12 = (double)(u_530_F.n_1700_B(entity.u_1723_Y(vector3d)) / f2)) <= 1.0) || (d13 = (double)u_530_F.n_1700_B((d5 = entity.O_3598_v() - this.u_1723_Y) * d5 + (d7 = (entity instanceof PrimedTnt ? entity.X_2960_b() : entity.X_2048_Y()) - this.v_4262_N) * d7 + (d9 = entity.l_2647_k() - this.w_1484_f) * d9)) == 0.0) continue;
            d5 /= d13;
            d7 /= d13;
            d9 /= d13;
            double d14 = F_1241_B.n_1700_B(vector3d, entity);
            double d10 = (1.0 - d12) * d14;
            entity.n_1700_B(this.J_1907_R(), (float)((int)((d10 * d10 + d10) / 2.0 * 7.0 * (double)f2 + 1.0)));
            double d11 = d10;
            if (entity instanceof r_4811_B) {
                d11 = ProtectionEnchantment.n_1700_B((r_4811_B)entity, d10);
            }
            entity.v_4262_N(entity.I_4348_c().J_1907_R(d5 * d11, d7 * d11, d9 * d11));
            if (!(entity instanceof a_3913_L) || (playerentity = (a_3913_L)entity).d_2461_k() || playerentity.G_624_v() && playerentity.C_415_h.J_1907_R) continue;
            this.h_1847_R.put(playerentity, new e_2866_D(d5 * d10, d7 * d10, d9 * d10));
        }
    }

    public void n_1700_B(boolean spawnParticles) {
        boolean flag;
        if (this.P_1922_E.Y_259_p) {
            this.P_1922_E.n_1700_B(this.u_1723_Y, this.v_4262_N, this.w_1484_f, SoundEvents.S_2828_i, D_38_f.P_1922_E, 4.0f, (1.0f + (this.P_1922_E.w_1457_N.nextFloat() - this.P_1922_E.w_1457_N.nextFloat()) * 0.2f) * 0.7f, false);
        }
        boolean bl = flag = this.R_4764_Y != lightning.product.F_1241_B$n_1700_B.n_1700_B;
        if (spawnParticles) {
            if (!(this.s_956_w < 2.0f) && flag) {
                this.P_1922_E.n_1700_B(ParticleTypes.Q_2552_b, this.u_1723_Y, this.v_4262_N, this.w_1484_f, 1.0, 0.0, 0.0);
            } else {
                this.P_1922_E.n_1700_B(ParticleTypes.C_2741_M, this.u_1723_Y, this.v_4262_N, this.w_1484_f, 1.0, 0.0, 0.0);
            }
        }
        if (flag) {
            ObjectArrayList objectarraylist = new ObjectArrayList();
            Collections.shuffle(this.P_4830_p, this.P_1922_E.w_1457_N);
            for (c_1514_x blockpos : this.P_4830_p) {
                K_4074_S blockstate = this.P_1922_E.getBlockState(blockpos);
                T_2915_h block = blockstate.J_1907_R();
                if (blockstate.v_4262_N()) continue;
                c_1514_x blockpos1 = blockpos.toImmutable();
                this.P_1922_E.D_4792_h().n_1700_B("explosion_blocks");
                if (block.n_1700_B(this) && this.P_1922_E instanceof e_3591_l) {
                    i_2154_H tileentity = block.G_564_y() ? this.P_1922_E.getTileEntity(blockpos) : null;
                    q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B((e_3591_l)this.P_1922_E).n_1700_B(this.P_1922_E.w_1457_N).n_1700_B(LootContextParams.u_1723_Y, e_2866_D.n_1700_B(blockpos)).n_1700_B(LootContextParams.t_148_a, Z_1993_T.J_1907_R).J_1907_R(LootContextParams.w_1484_f, tileentity).J_1907_R(LootContextParams.n_1700_B, this.t_148_a);
                    if (this.R_4764_Y == lightning.product.F_1241_B$n_1700_B.R_4764_Y) {
                        lootcontext$builder.n_1700_B(LootContextParams.s_956_w, Float.valueOf(this.s_956_w));
                    }
                    blockstate.n_1700_B(lootcontext$builder).forEach(stack -> F_1241_B.n_1700_B((ObjectArrayList<Pair<Z_1993_T, c_1514_x>>)objectarraylist, stack, blockpos1));
                }
                this.P_1922_E.n_1700_B(blockpos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 3);
                block.n_1700_B(this.P_1922_E, blockpos, this);
                this.P_1922_E.D_4792_h().R_4764_Y();
            }
            for (Pair pair : objectarraylist) {
                T_2915_h.n_1700_B(this.P_1922_E, (c_1514_x)pair.getSecond(), (Z_1993_T)pair.getFirst());
            }
        }
        if (this.J_1907_R) {
            for (c_1514_x blockpos2 : this.P_4830_p) {
                if (this.G_564_y.nextInt(3) != 0 || !this.P_1922_E.getBlockState(blockpos2).v_4262_N() || !this.P_1922_E.getBlockState(blockpos2.down()).t_148_a(this.P_1922_E, blockpos2.down())) continue;
                this.P_1922_E.J_1907_R(blockpos2, BaseFireBlock.n_1700_B(this.P_1922_E, blockpos2));
            }
        }
    }

    private static void n_1700_B(ObjectArrayList<Pair<Z_1993_T, c_1514_x>> dropPositionArray, Z_1993_T stack, c_1514_x pos) {
        int i = dropPositionArray.size();
        for (int j = 0; j < i; ++j) {
            Pair pair = (Pair)dropPositionArray.get(j);
            Z_1993_T itemstack = (Z_1993_T)pair.getFirst();
            if (!n_1494_c.n_1700_B(itemstack, stack)) continue;
            Z_1993_T itemstack1 = n_1494_c.n_1700_B(itemstack, stack, 16);
            dropPositionArray.set(j, (Object)Pair.of((Object)itemstack1, (Object)((c_1514_x)pair.getSecond())));
            if (!stack.n_1700_B()) continue;
            return;
        }
        dropPositionArray.add((Object)Pair.of((Object)stack, (Object)pos));
    }

    public P_11_z J_1907_R() {
        return this.u_2550_I;
    }

    public Map<a_3913_L, e_2866_D> R_4764_Y() {
        return this.h_1847_R;
    }

    @Nullable
    public r_4811_B G_564_y() {
        N_4263_v entity;
        if (this.t_148_a == null) {
            return null;
        }
        if (this.t_148_a instanceof PrimedTnt) {
            return ((PrimedTnt)this.t_148_a).P_1922_E();
        }
        if (this.t_148_a instanceof r_4811_B) {
            return (r_4811_B)this.t_148_a;
        }
        if (this.t_148_a instanceof Projectile && (entity = ((Projectile)this.t_148_a).Y_601_j()) instanceof r_4811_B) {
            return (r_4811_B)entity;
        }
        return null;
    }

    public void P_1922_E() {
        this.P_4830_p.clear();
    }

    public List<c_1514_x> u_1723_Y() {
        return this.P_4830_p;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.F_1241_B$n_1700_B.n_1700_B();
        }
    }
}


