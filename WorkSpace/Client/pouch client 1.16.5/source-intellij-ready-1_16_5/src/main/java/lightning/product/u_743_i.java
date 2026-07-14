/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntMaps
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.D_4011_s;
import lightning.product.BlockGetter;
import lightning.product.F_4023_g;
import lightning.product.StructureFeature;
import lightning.product.PotentialCalculator;
import lightning.product.H_1748_a;
import lightning.product.J_3017_d;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_1316_M;
import lightning.product.V_3137_a;
import lightning.product.V_3157_k;
import lightning.product.MobSpawnSettings;
import lightning.product.Y_1387_d;
import lightning.product.Z_530_i;
import lightning.product.Z_749_F;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.WeighedRandom;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.ServerLevelAccessor;
import lightning.product.ChunkAccess;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.k_594_Q;
import lightning.product.BlockTags;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;
import lightning.product.z_3539_x;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class u_743_i {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final int J_1907_R = (int)Math.pow(17.0, 2.0);
    private static final Z_749_F[] R_4764_Y = (Z_749_F[])Stream.of(Z_749_F.values()).filter(p_234965_0_ -> p_234965_0_ != Z_749_F.u_1723_Y).toArray(Z_749_F[]::new);

    public static n_1700_B n_1700_B(int p_234964_0_, Iterable<N_4263_v> p_234964_1_, R_4764_Y p_234964_2_) {
        PotentialCalculator mobdensitytracker = new PotentialCalculator();
        Object2IntOpenHashMap object2intopenhashmap = new Object2IntOpenHashMap();
        Iterator<N_4263_v> iterator = p_234964_1_.iterator();
        while (iterator.hasNext()) {
            Z_530_i mobentity;
            N_4263_v entity = iterator.next();
            if (entity instanceof Z_530_i && ((mobentity = (Z_530_i)entity).s_2632_s() || mobentity.e_2887_G())) continue;
            N_4263_v entity_f = entity;
            Z_749_F entityclassification = entity.f_4016_n().P_1922_E();
            if (entityclassification == Z_749_F.u_1723_Y) continue;
            c_1514_x blockpos = entity.b_2312_j();
            long i = Y_1387_d.n_1700_B(blockpos.getX() >> 4, blockpos.getZ() >> 4);
            p_234964_2_.query(i, p_234971_5_ -> {
                MobSpawnSettings.J_1907_R mobspawninfo$spawncosts = u_743_i.n_1700_B(blockpos, (ChunkAccess)p_234971_5_).J_1907_R().n_1700_B(entity_f.f_4016_n());
                if (mobspawninfo$spawncosts != null) {
                    mobdensitytracker.n_1700_B(entity_f.b_2312_j(), mobspawninfo$spawncosts.J_1907_R());
                }
                object2intopenhashmap.addTo((Object)entityclassification, 1);
            });
        }
        return new n_1700_B(p_234964_0_, (Object2IntOpenHashMap<Z_749_F>)object2intopenhashmap, mobdensitytracker);
    }

    private static k_594_Q n_1700_B(c_1514_x p_234980_0_, ChunkAccess p_234980_1_) {
        return D_4011_s.n_1700_B.n_1700_B(0L, p_234980_0_.getX(), p_234980_0_.getY(), p_234980_0_.getZ(), p_234980_1_.getBiomes());
    }

    public static void n_1700_B(e_3591_l p_234979_0_, H_1748_a p_234979_1_, n_1700_B p_234979_2_, boolean p_234979_3_, boolean p_234979_4_, boolean p_234979_5_) {
        p_234979_0_.D_4792_h().n_1700_B("spawner");
        for (Z_749_F entityclassification : R_4764_Y) {
            if (!p_234979_3_ && entityclassification.G_564_y() || !p_234979_4_ && !entityclassification.G_564_y() || !p_234979_5_ && entityclassification.P_1922_E() || !p_234979_2_.n_1700_B(entityclassification)) continue;
            u_743_i.n_1700_B(entityclassification, p_234979_0_, p_234979_1_, (t_5_h<?> p_234969_1_, c_1514_x p_234969_2_, ChunkAccess p_234969_3_) -> p_234979_2_.n_1700_B(p_234969_1_, p_234969_2_, p_234969_3_), (Z_530_i p_234970_1_, ChunkAccess p_234970_2_) -> p_234979_2_.n_1700_B(p_234970_1_, p_234970_2_));
        }
        p_234979_0_.D_4792_h().R_4764_Y();
    }

    public static void n_1700_B(Z_749_F p_234967_0_, e_3591_l p_234967_1_, H_1748_a p_234967_2_, J_1907_R p_234967_3_, G_564_y p_234967_4_) {
        c_1514_x blockpos = u_743_i.n_1700_B((b_4507_u)p_234967_1_, p_234967_2_);
        if (blockpos.getY() >= 1) {
            u_743_i.n_1700_B(p_234967_0_, p_234967_1_, p_234967_2_, blockpos, p_234967_3_, p_234967_4_);
        }
    }

    public static void n_1700_B(Z_749_F p_234966_0_, e_3591_l p_234966_1_, ChunkAccess p_234966_2_, c_1514_x p_234966_3_, J_1907_R p_234966_4_, G_564_y p_234966_5_) {
        J_3017_d structuremanager = p_234966_1_.R_4764_Y();
        z_1753_f chunkgenerator = p_234966_1_.Y_259_p().t_148_a();
        int i = p_234966_3_.getY();
        K_4074_S blockstate = p_234966_2_.getBlockState(p_234966_3_);
        if (!blockstate.v_4262_N(p_234966_2_, p_234966_3_)) {
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            int j = 0;
            block0: for (int k = 0; k < 3; ++k) {
                int l = p_234966_3_.getX();
                int i1 = p_234966_3_.getZ();
                int j1 = 6;
                MobSpawnSettings.R_4764_Y mobspawninfo$spawners = null;
                V_3157_k ilivingentitydata = null;
                int k1 = u_530_F.u_1723_Y(p_234966_1_.w_1457_N.nextFloat() * 4.0f);
                int l1 = 0;
                for (int i2 = 0; i2 < k1; ++i2) {
                    double d2;
                    blockpos$mutable.n_1700_B(l += p_234966_1_.w_1457_N.nextInt(6) - p_234966_1_.w_1457_N.nextInt(6), i, i1 += p_234966_1_.w_1457_N.nextInt(6) - p_234966_1_.w_1457_N.nextInt(6));
                    double d0 = (double)l + 0.5;
                    double d1 = (double)i1 + 0.5;
                    a_3913_L playerentity = p_234966_1_.n_1700_B(d0, (double)i, d1, -1.0, false);
                    if (playerentity == null || !u_743_i.n_1700_B(p_234966_1_, p_234966_2_, blockpos$mutable, d2 = playerentity.v_4262_N(d0, i, d1))) continue;
                    if (mobspawninfo$spawners == null) {
                        mobspawninfo$spawners = u_743_i.n_1700_B(p_234966_1_, structuremanager, chunkgenerator, p_234966_0_, p_234966_1_.w_1457_N, (c_1514_x)blockpos$mutable);
                        if (mobspawninfo$spawners == null) continue block0;
                        k1 = mobspawninfo$spawners.G_564_y + p_234966_1_.w_1457_N.nextInt(1 + mobspawninfo$spawners.P_1922_E - mobspawninfo$spawners.G_564_y);
                    }
                    if (!u_743_i.n_1700_B(p_234966_1_, p_234966_0_, structuremanager, chunkgenerator, mobspawninfo$spawners, blockpos$mutable, d2) || !p_234966_4_.test(mobspawninfo$spawners.J_1907_R, blockpos$mutable, p_234966_2_)) continue;
                    Z_530_i mobentity = u_743_i.n_1700_B(p_234966_1_, mobspawninfo$spawners.J_1907_R);
                    if (mobentity == null) {
                        return;
                    }
                    mobentity.J_1907_R(d0, i, d1, p_234966_1_.w_1457_N.nextFloat() * 360.0f, 0.0f);
                    if (!u_743_i.n_1700_B(p_234966_1_, mobentity, d2)) continue;
                    ilivingentitydata = mobentity.n_1700_B(p_234966_1_, p_234966_1_.J_1907_R(mobentity.b_2312_j()), a_3160_D.n_1700_B, ilivingentitydata, null);
                    ++l1;
                    p_234966_1_.n_1700_B((N_4263_v)mobentity);
                    p_234966_5_.run(mobentity, p_234966_2_);
                    if (++j >= mobentity.c_4037_x()) {
                        return;
                    }
                    if (mobentity.t_1786_h(l1)) continue block0;
                }
            }
        }
    }

    private static boolean n_1700_B(e_3591_l p_234978_0_, ChunkAccess p_234978_1_, c_1514_x.n_1700_B p_234978_2_, double p_234978_3_) {
        if (p_234978_3_ <= 576.0) {
            return false;
        }
        if (p_234978_0_.A_1038_p().withinDistance(new e_2866_D((double)p_234978_2_.getX() + 0.5, p_234978_2_.getY(), (double)p_234978_2_.getZ() + 0.5), 24.0)) {
            return false;
        }
        Y_1387_d chunkpos = new Y_1387_d(p_234978_2_);
        return Objects.equals(chunkpos, p_234978_1_.getPos()) || p_234978_0_.Y_259_p().n_1700_B(chunkpos);
    }

    private static boolean n_1700_B(e_3591_l p_234975_0_, Z_749_F p_234975_1_, J_3017_d p_234975_2_, z_1753_f p_234975_3_, MobSpawnSettings.R_4764_Y p_234975_4_, c_1514_x.n_1700_B p_234975_5_, double p_234975_6_) {
        t_5_h<?> entitytype = p_234975_4_.J_1907_R;
        if (entitytype.P_1922_E() == Z_749_F.u_1723_Y) {
            return false;
        }
        if (!entitytype.G_564_y() && p_234975_6_ > (double)(entitytype.P_1922_E().u_1723_Y() * entitytype.P_1922_E().u_1723_Y())) {
            return false;
        }
        if (entitytype.J_1907_R() && u_743_i.n_1700_B(p_234975_0_, p_234975_2_, p_234975_3_, p_234975_1_, p_234975_4_, (c_1514_x)p_234975_5_)) {
            F_4023_g.R_4764_Y entityspawnplacementregistry$placementtype = F_4023_g.n_1700_B(entitytype);
            if (!u_743_i.n_1700_B(entityspawnplacementregistry$placementtype, p_234975_0_, (c_1514_x)p_234975_5_, entitytype)) {
                return false;
            }
            if (!F_4023_g.n_1700_B(entitytype, p_234975_0_, a_3160_D.n_1700_B, p_234975_5_, p_234975_0_.w_1457_N)) {
                return false;
            }
            return p_234975_0_.J_1907_R(entitytype.n_1700_B((double)p_234975_5_.getX() + 0.5, p_234975_5_.getY(), (double)p_234975_5_.getZ() + 0.5));
        }
        return false;
    }

    @Nullable
    private static Z_530_i n_1700_B(e_3591_l p_234973_0_, t_5_h<?> p_234973_1_) {
        try {
            Object entity = p_234973_1_.n_1700_B(p_234973_0_);
            if (!(entity instanceof Z_530_i)) {
                throw new IllegalStateException("Trying to spawn a non-mob: " + String.valueOf(V_3137_a.g_221_o.J_1907_R(p_234973_1_)));
            }
            return (Z_530_i)entity;
        }
        catch (Exception exception) {
            n_1700_B.warn("Failed to create mob", (Throwable)exception);
            return null;
        }
    }

    private static boolean n_1700_B(e_3591_l p_234974_0_, Z_530_i p_234974_1_, double p_234974_2_) {
        if (p_234974_2_ > (double)(p_234974_1_.f_4016_n().P_1922_E().u_1723_Y() * p_234974_1_.f_4016_n().P_1922_E().u_1723_Y()) && p_234974_1_.w_1484_f(p_234974_2_)) {
            return false;
        }
        return p_234974_1_.n_1700_B((LevelAccessor)p_234974_0_, a_3160_D.n_1700_B) && p_234974_1_.n_1700_B((T_1316_M)p_234974_0_);
    }

    @Nullable
    private static MobSpawnSettings.R_4764_Y n_1700_B(e_3591_l p_234977_0_, J_3017_d p_234977_1_, z_1753_f p_234977_2_, Z_749_F p_234977_3_, Random p_234977_4_, c_1514_x p_234977_5_) {
        k_594_Q biome = p_234977_0_.P_1922_E(p_234977_5_);
        if (p_234977_3_ == Z_749_F.P_1922_E && biome.Y_601_j() == k_594_Q.R_4764_Y.h_1847_R && p_234977_4_.nextFloat() < 0.98f) {
            return null;
        }
        List<MobSpawnSettings.R_4764_Y> list = u_743_i.n_1700_B(p_234977_0_, p_234977_1_, p_234977_2_, p_234977_3_, p_234977_5_, biome);
        return list.isEmpty() ? null : WeighedRandom.n_1700_B(p_234977_4_, list);
    }

    private static boolean n_1700_B(e_3591_l p_234976_0_, J_3017_d p_234976_1_, z_1753_f p_234976_2_, Z_749_F p_234976_3_, MobSpawnSettings.R_4764_Y p_234976_4_, c_1514_x p_234976_5_) {
        return u_743_i.n_1700_B(p_234976_0_, p_234976_1_, p_234976_2_, p_234976_3_, p_234976_5_, (k_594_Q)null).contains(p_234976_4_);
    }

    private static List<MobSpawnSettings.R_4764_Y> n_1700_B(e_3591_l p_241463_0_, J_3017_d p_241463_1_, z_1753_f p_241463_2_, Z_749_F p_241463_3_, c_1514_x p_241463_4_, @Nullable k_594_Q p_241463_5_) {
        return p_241463_3_ == Z_749_F.n_1700_B && p_241463_0_.getBlockState(p_241463_4_.down()).J_1907_R() == a_3742_W.h_1015_G && p_241463_1_.n_1700_B(p_241463_4_, false, StructureFeature.h_1847_R).P_1922_E() ? StructureFeature.h_1847_R.R_4764_Y() : p_241463_2_.n_1700_B(p_241463_5_ != null ? p_241463_5_ : p_241463_0_.P_1922_E(p_241463_4_), p_241463_1_, p_241463_3_, p_241463_4_);
    }

    private static c_1514_x n_1700_B(b_4507_u worldIn, H_1748_a p_222262_1_) {
        Y_1387_d chunkpos = p_222262_1_.getPos();
        int i = chunkpos.J_1907_R() + worldIn.w_1457_N.nextInt(16);
        int j = chunkpos.R_4764_Y() + worldIn.w_1457_N.nextInt(16);
        int k = p_222262_1_.getTopBlockY(z_2963_s.n_1700_B.J_1907_R, i, j) + 1;
        int l = worldIn.w_1457_N.nextInt(k + 1);
        return new c_1514_x(i, l, j);
    }

    public static boolean n_1700_B(BlockGetter p_234968_0_, c_1514_x p_234968_1_, K_4074_S p_234968_2_, FluidState p_234968_3_, t_5_h<?> p_234968_4_) {
        if (p_234968_2_.multiplayerClientSuggestionProvider(p_234968_0_, p_234968_1_)) {
            return false;
        }
        if (p_234968_2_.t_148_a()) {
            return false;
        }
        if (!p_234968_3_.R_4764_Y()) {
            return false;
        }
        if (p_234968_2_.n_1700_B(BlockTags.dtoRealmsServerAddress)) {
            return false;
        }
        return !p_234968_4_.n_1700_B(p_234968_2_);
    }

    public static boolean n_1700_B(F_4023_g.R_4764_Y placeType, T_1316_M worldIn, c_1514_x pos, @Nullable t_5_h<?> entityTypeIn) {
        if (placeType == F_4023_g.R_4764_Y.R_4764_Y) {
            return true;
        }
        if (entityTypeIn != null && worldIn.H_2857_Y().n_1700_B(pos)) {
            K_4074_S blockstate = worldIn.getBlockState(pos);
            FluidState fluidstate = worldIn.getFluidState(pos);
            c_1514_x blockpos = pos.up();
            c_1514_x blockpos1 = pos.down();
            switch (placeType) {
                case J_1907_R: {
                    return fluidstate.n_1700_B(FluidTags.J_1907_R) && worldIn.getFluidState(blockpos1).n_1700_B(FluidTags.J_1907_R) && !worldIn.getBlockState(blockpos).v_4262_N(worldIn, blockpos);
                }
                case G_564_y: {
                    return fluidstate.n_1700_B(FluidTags.R_4764_Y);
                }
            }
            K_4074_S blockstate1 = worldIn.getBlockState(blockpos1);
            if (!blockstate1.n_1700_B((BlockGetter)worldIn, blockpos1, entityTypeIn)) {
                return false;
            }
            return u_743_i.n_1700_B(worldIn, pos, blockstate, fluidstate, entityTypeIn) && u_743_i.n_1700_B(worldIn, blockpos, worldIn.getBlockState(blockpos), worldIn.getFluidState(blockpos), entityTypeIn);
        }
        return false;
    }

    public static void n_1700_B(ServerLevelAccessor worldIn, k_594_Q biomeIn, int centerX, int centerZ, Random diameterX) {
        MobSpawnSettings mobspawninfo = biomeIn.J_1907_R();
        List<MobSpawnSettings.R_4764_Y> list = mobspawninfo.n_1700_B(Z_749_F.J_1907_R);
        if (!list.isEmpty()) {
            int i = centerX << 4;
            int j = centerZ << 4;
            while (diameterX.nextFloat() < mobspawninfo.n_1700_B()) {
                MobSpawnSettings.R_4764_Y mobspawninfo$spawners = WeighedRandom.n_1700_B(diameterX, list);
                int k = mobspawninfo$spawners.G_564_y + diameterX.nextInt(1 + mobspawninfo$spawners.P_1922_E - mobspawninfo$spawners.G_564_y);
                V_3157_k ilivingentitydata = null;
                int l = i + diameterX.nextInt(16);
                int i1 = j + diameterX.nextInt(16);
                int j1 = l;
                int k1 = i1;
                for (int l1 = 0; l1 < k; ++l1) {
                    boolean flag = false;
                    for (int i2 = 0; !flag && i2 < 4; ++i2) {
                        c_1514_x blockpos = u_743_i.n_1700_B(worldIn, mobspawninfo$spawners.J_1907_R, l, i1);
                        if (mobspawninfo$spawners.J_1907_R.J_1907_R() && u_743_i.n_1700_B(F_4023_g.n_1700_B(mobspawninfo$spawners.J_1907_R), worldIn, blockpos, mobspawninfo$spawners.J_1907_R)) {
                            Z_530_i mobentity;
                            Object entity;
                            float f = mobspawninfo$spawners.J_1907_R.t_148_a();
                            double d0 = u_530_F.n_1700_B((double)l, (double)i + (double)f, (double)i + 16.0 - (double)f);
                            double d1 = u_530_F.n_1700_B((double)i1, (double)j + (double)f, (double)j + 16.0 - (double)f);
                            if (!worldIn.J_1907_R(mobspawninfo$spawners.J_1907_R.n_1700_B(d0, blockpos.getY(), d1)) || !F_4023_g.n_1700_B(mobspawninfo$spawners.J_1907_R, worldIn, a_3160_D.J_1907_R, new c_1514_x(d0, (double)blockpos.getY(), d1), worldIn.e_4240_b())) continue;
                            try {
                                entity = mobspawninfo$spawners.J_1907_R.n_1700_B(worldIn.J_1907_R());
                            }
                            catch (Exception exception) {
                                n_1700_B.warn("Failed to create mob", (Throwable)exception);
                                continue;
                            }
                            ((N_4263_v)entity).J_1907_R(d0, blockpos.getY(), d1, diameterX.nextFloat() * 360.0f, 0.0f);
                            if (entity instanceof Z_530_i && (mobentity = (Z_530_i)entity).n_1700_B(worldIn, a_3160_D.J_1907_R) && mobentity.n_1700_B(worldIn)) {
                                ilivingentitydata = mobentity.n_1700_B(worldIn, worldIn.J_1907_R(mobentity.b_2312_j()), a_3160_D.J_1907_R, ilivingentitydata, null);
                                worldIn.n_1700_B(mobentity);
                                flag = true;
                            }
                        }
                        l += diameterX.nextInt(5) - diameterX.nextInt(5);
                        i1 += diameterX.nextInt(5) - diameterX.nextInt(5);
                        while (l < i || l >= i + 16 || i1 < j || i1 >= j + 16) {
                            l = j1 + diameterX.nextInt(5) - diameterX.nextInt(5);
                            i1 = k1 + diameterX.nextInt(5) - diameterX.nextInt(5);
                        }
                    }
                }
            }
        }
    }

    private static c_1514_x n_1700_B(T_1316_M worldIn, t_5_h<?> p_208498_1_, int x, int z) {
        z_3539_x blockpos;
        int i = worldIn.n_1700_B(F_4023_g.J_1907_R(p_208498_1_), x, z);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B(x, i, z);
        if (worldIn.G_624_v().R_4764_Y()) {
            do {
                blockpos$mutable.n_1700_B(b_257_Y.n_1700_B);
            } while (!worldIn.getBlockState(blockpos$mutable).v_4262_N());
            do {
                blockpos$mutable.n_1700_B(b_257_Y.n_1700_B);
            } while (worldIn.getBlockState(blockpos$mutable).v_4262_N() && blockpos$mutable.getY() > 0);
        }
        if (F_4023_g.n_1700_B(p_208498_1_) == F_4023_g.R_4764_Y.n_1700_B && worldIn.getBlockState((c_1514_x)(blockpos = blockpos$mutable.down())).n_1700_B((BlockGetter)worldIn, (c_1514_x)blockpos, t_3546_P.n_1700_B)) {
            return blockpos;
        }
        return blockpos$mutable.toImmutable();
    }

    public static class n_1700_B {
        private final int n_1700_B;
        private final Object2IntOpenHashMap<Z_749_F> J_1907_R;
        private final PotentialCalculator R_4764_Y;
        private final Object2IntMap<Z_749_F> G_564_y;
        @Nullable
        private c_1514_x P_1922_E;
        @Nullable
        private t_5_h<?> u_1723_Y;
        private double v_4262_N;

        private n_1700_B(int p_i231621_1_, Object2IntOpenHashMap<Z_749_F> p_i231621_2_, PotentialCalculator p_i231621_3_) {
            this.n_1700_B = p_i231621_1_;
            this.J_1907_R = p_i231621_2_;
            this.R_4764_Y = p_i231621_3_;
            this.G_564_y = Object2IntMaps.unmodifiable(p_i231621_2_);
        }

        private boolean n_1700_B(t_5_h<?> p_234989_1_, c_1514_x p_234989_2_, ChunkAccess p_234989_3_) {
            double d0;
            this.P_1922_E = p_234989_2_;
            this.u_1723_Y = p_234989_1_;
            MobSpawnSettings.J_1907_R mobspawninfo$spawncosts = u_743_i.n_1700_B(p_234989_2_, p_234989_3_).J_1907_R().n_1700_B(p_234989_1_);
            if (mobspawninfo$spawncosts == null) {
                this.v_4262_N = 0.0;
                return true;
            }
            this.v_4262_N = d0 = mobspawninfo$spawncosts.J_1907_R();
            double d1 = this.R_4764_Y.J_1907_R(p_234989_2_, d0);
            return d1 <= mobspawninfo$spawncosts.n_1700_B();
        }

        private void n_1700_B(Z_530_i p_234990_1_, ChunkAccess p_234990_2_) {
            MobSpawnSettings.J_1907_R mobspawninfo$spawncosts;
            t_5_h<?> entitytype = p_234990_1_.f_4016_n();
            c_1514_x blockpos = p_234990_1_.b_2312_j();
            double d0 = blockpos.equals(this.P_1922_E) && entitytype == this.u_1723_Y ? this.v_4262_N : ((mobspawninfo$spawncosts = u_743_i.n_1700_B(blockpos, p_234990_2_).J_1907_R().n_1700_B(entitytype)) != null ? mobspawninfo$spawncosts.J_1907_R() : 0.0);
            this.R_4764_Y.n_1700_B(blockpos, d0);
            this.J_1907_R.addTo((Object)entitytype.P_1922_E(), 1);
        }

        public int n_1700_B() {
            return this.n_1700_B;
        }

        public Object2IntMap<Z_749_F> J_1907_R() {
            return this.G_564_y;
        }

        private boolean n_1700_B(Z_749_F p_234991_1_) {
            int i = p_234991_1_.R_4764_Y() * this.n_1700_B / J_1907_R;
            return this.J_1907_R.getInt((Object)p_234991_1_) < i;
        }
    }

    @FunctionalInterface
    public static interface R_4764_Y {
        public void query(long var1, Consumer<H_1748_a> var3);
    }

    @FunctionalInterface
    public static interface J_1907_R {
        public boolean test(t_5_h<?> var1, c_1514_x var2, ChunkAccess var3);
    }

    @FunctionalInterface
    public static interface G_564_y {
        public void run(Z_530_i var1, ChunkAccess var2);
    }
}


