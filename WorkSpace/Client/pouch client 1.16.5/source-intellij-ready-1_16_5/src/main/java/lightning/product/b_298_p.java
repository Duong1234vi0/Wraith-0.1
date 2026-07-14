/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.collect.EvictingQueue
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Queues
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Charsets;
import com.google.common.collect.EvictingQueue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.LambdaMetafactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.A_2204_Z;
import lightning.product.A_4115_X;
import lightning.product.A_4252_m;
import lightning.product.FluidTags;
import lightning.product.B_3871_I;
import lightning.product.FlameParticle;
import lightning.product.C_3240_x;
import lightning.product.D_3318_r;
import lightning.product.DragonBreathParticle;
import lightning.product.E_4918_z;
import lightning.product.SuspendedParticle;
import lightning.product.ParticleRenderType;
import lightning.product.F_3565_Q;
import lightning.product.BlockHitResult;
import lightning.product.LavaParticle;
import lightning.product.I_4817_s;
import lightning.product.SpellParticle;
import lightning.product.CritParticle;
import lightning.product.K_1668_X;
import lightning.product.K_2336_H;
import lightning.product.K_4074_S;
import lightning.product.SoulParticle;
import lightning.product.L_3848_p;
import lightning.product.L_3884_T;
import lightning.product.M_3637_E;
import lightning.product.PreparableReloadListener;
import lightning.product.N_4263_v;
import lightning.product.Resource;
import lightning.product.FallingDustParticle;
import lightning.product.O_2369_F;
import lightning.product.DripParticle;
import lightning.product.ReversePortalParticle;
import lightning.product.P_3394_O;
import lightning.product.Q_2974_D;
import lightning.product.R_137_M;
import lightning.product.HugeExplosionSeedParticle;
import lightning.product.R_4912_F;
import lightning.product.ResourceManager;
import lightning.product.S_315_z;
import lightning.product.ParticleOptions;
import lightning.product.T_2915_h;
import lightning.product.U_3921_G;
import lightning.product.EnchantmentTableParticle;
import lightning.product.V_3080_W;
import lightning.product.V_3137_a;
import lightning.product.X_1567_o;
import lightning.product.ProfilerFiller;
import lightning.product.CampfireSmokeParticle;
import lightning.product.particleCritParticle2;
import lightning.product.a_794_m;
import lightning.product.b_257_Y;
import lightning.product.b_296_o;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.SpriteSet;
import lightning.product.FluidState;
import lightning.product.c_3005_b;
import lightning.product.c_3457_g;
import lightning.product.c_4037_x;
import lightning.product.d_1468_P;
import lightning.product.d_4807_l;
import lightning.product.e_1689_x;
import lightning.product.SquidInkParticle;
import lightning.product.e_87_p;
import lightning.product.f_4340_D;
import lightning.product.TerrainParticle;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.PlayerCloudParticle;
import lightning.product.h_1915_h;
import lightning.product.h_3270_j;
import lightning.product.h_3572_K;
import lightning.product.i_4431_W;
import lightning.product.EndRodParticle;
import lightning.product.SuspendedTownParticle;
import lightning.product.TrackingEmitter;
import lightning.product.MobAppearanceParticle;
import lightning.product.BreakingItemParticle;
import lightning.product.l_3747_P;
import lightning.product.ParticleProvider;
import lightning.product.m_229_F;
import lightning.product.n_2564_g;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.n_421_x;
import lightning.product.o_3091_w;
import lightning.product.o_977_F;
import lightning.product.p_1976_q;
import lightning.product.q_4242_z;
import lightning.product.PortalParticle;
import lightning.product.CrashReportCategory;
import lightning.product.r_4013_A;
import lightning.product.s_1395_c;
import lightning.product.s_2736_T;
import lightning.product.ParticleTypes;
import lightning.product.BarrierParticle;
import lightning.product.u_530_F;
import lightning.product.ParticleType;
import lightning.product.particleCritParticle;
import lightning.product.x_3506_b;
import lightning.product.x_4101_Q;
import lightning.product.y_4802_V;
import net.optifine.Config;
import net.optifine.reflect.Reflector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class b_298_p
implements PreparableReloadListener {
    private static final List<ParticleRenderType> J_1907_R = ImmutableList.of((Object)ParticleRenderType.n_1700_B, (Object)ParticleRenderType.J_1907_R, (Object)ParticleRenderType.G_564_y, (Object)ParticleRenderType.R_4764_Y, (Object)ParticleRenderType.P_1922_E);
    private static final Logger R_4764_Y = LogManager.getLogger();
    protected b_4507_u n_1700_B;
    private final Map<ParticleRenderType, Queue<c_3457_g>> G_564_y = Maps.newIdentityHashMap();
    private final Queue<TrackingEmitter> P_1922_E = Queues.newArrayDeque();
    private final C_3240_x u_1723_Y;
    private final Random v_4262_N = new Random();
    private final Map<g_2336_b, ParticleProvider<?>> w_1484_f = new HashMap();
    private final Queue<c_3457_g> t_148_a = Queues.newArrayDeque();
    private final Map<g_2336_b, n_1700_B> s_956_w = Maps.newHashMap();
    private final L_3848_p u_2550_I = new L_3848_p(L_3848_p.J_1907_R);

    public b_298_p(b_4507_u world, C_3240_x textureManager) {
        textureManager.n_1700_B(this.u_2550_I.R_4764_Y(), this.u_2550_I);
        this.n_1700_B = world;
        this.u_1723_Y = textureManager;
        this.P_1922_E();
    }

    private void P_1922_E() {
        m_229_F.n_1700_B();
        n_421_x.n_1700_B();
        this.n_1700_B(ParticleTypes.n_1700_B, SpellParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.J_1907_R, CritParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.R_4764_Y, new BarrierParticle.n_1700_B());
        this.n_1700_B(ParticleTypes.G_564_y, new TerrainParticle.n_1700_B());
        this.n_1700_B(ParticleTypes.P_1922_E, Q_2974_D.n_1700_B::new);
        this.n_1700_B(ParticleTypes.l_1233_K, d_1468_P.n_1700_B::new);
        this.n_1700_B(ParticleTypes.D_4792_h, y_4802_V.n_1700_B::new);
        this.n_1700_B(ParticleTypes.r_715_M, CampfireSmokeParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.A_1038_p, CampfireSmokeParticle.J_1907_R::new);
        this.n_1700_B(ParticleTypes.u_1723_Y, PlayerCloudParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.x_607_J, SuspendedTownParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.v_4262_N, particleCritParticle.J_1907_R::new);
        this.n_1700_B(ParticleTypes.s_2632_s, b_296_o.n_1700_B::new);
        this.n_1700_B(ParticleTypes.w_1484_f, particleCritParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.t_148_a, DragonBreathParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.O_508_d, SuspendedTownParticle.J_1907_R::new);
        this.n_1700_B(ParticleTypes.s_956_w, DripParticle.G_564_y::new);
        this.n_1700_B(ParticleTypes.u_2550_I, DripParticle.t_148_a::new);
        this.n_1700_B(ParticleTypes.M_588_G, DripParticle.t_1786_h::new);
        this.n_1700_B(ParticleTypes.P_4830_p, DripParticle.u_1723_Y::new);
        this.n_1700_B(ParticleTypes.h_1847_R, DripParticle.h_1847_R::new);
        this.n_1700_B(ParticleTypes.Q_4569_t, L_3884_T.n_1700_B::new);
        this.n_1700_B(ParticleTypes.M_182_A, SpellParticle.J_1907_R::new);
        this.n_1700_B(ParticleTypes.t_1786_h, new MobAppearanceParticle.n_1700_B());
        this.n_1700_B(ParticleTypes.multiplayerClientSuggestionProvider, particleCritParticle.R_4764_Y::new);
        this.n_1700_B(ParticleTypes.w_1457_N, EnchantmentTableParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.Y_601_j, EndRodParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.Y_259_p, SpellParticle.G_564_y::new);
        this.n_1700_B(ParticleTypes.Q_2552_b, new HugeExplosionSeedParticle.n_1700_B());
        this.n_1700_B(ParticleTypes.C_2741_M, r_4013_A.n_1700_B::new);
        this.n_1700_B(ParticleTypes.k_2293_S, FallingDustParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.q_2307_F, R_137_M.G_564_y::new);
        this.n_1700_B(ParticleTypes.Z_875_P, M_3637_E.n_1700_B::new);
        this.n_1700_B(ParticleTypes.c_3005_b, FlameParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.A_4115_X, SoulParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.H_2857_Y, FlameParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.Y_1740_V, R_137_M.J_1907_R::new);
        this.n_1700_B(ParticleTypes.t_4043_B, SuspendedTownParticle.G_564_y::new);
        this.n_1700_B(ParticleTypes.e_4240_b, CritParticle.J_1907_R::new);
        this.n_1700_B(ParticleTypes.n_3318_d, SpellParticle.R_4764_Y::new);
        this.n_1700_B(ParticleTypes.d_2427_y, new BreakingItemParticle.n_1700_B());
        this.n_1700_B(ParticleTypes.z_1737_N, new BreakingItemParticle.J_1907_R());
        this.n_1700_B(ParticleTypes.v_4276_D, new BreakingItemParticle.R_4764_Y());
        this.n_1700_B(ParticleTypes.d_2461_k, x_3506_b.n_1700_B::new);
        this.n_1700_B(ParticleTypes.G_624_v, LavaParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.T_2506_i, SuspendedTownParticle.R_4764_Y::new);
        this.n_1700_B(ParticleTypes.z_1333_t, EnchantmentTableParticle.J_1907_R::new);
        this.n_1700_B(ParticleTypes.q_4610_l, particleCritParticle2.n_1700_B::new);
        this.n_1700_B(ParticleTypes.z_4693_k, n_2564_g.n_1700_B::new);
        this.n_1700_B(ParticleTypes.g_221_o, PortalParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.e_2887_G, U_3921_G.n_1700_B::new);
        this.n_1700_B(ParticleTypes.B_1668_F, P_3394_O.n_1700_B::new);
        this.n_1700_B(ParticleTypes.g_164_R, PlayerCloudParticle.J_1907_R::new);
        this.n_1700_B(ParticleTypes.X_933_l, s_2736_T.n_1700_B::new);
        this.n_1700_B(ParticleTypes.H_1990_U, q_4242_z.n_1700_B::new);
        this.n_1700_B(ParticleTypes.N_2525_X, V_3080_W.n_1700_B::new);
        this.n_1700_B(ParticleTypes.Z_976_R, SquidInkParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.c_4037_x, SuspendedParticle.J_1907_R::new);
        this.n_1700_B(ParticleTypes.g_2268_R, d_4807_l.n_1700_B::new);
        this.n_1700_B(ParticleTypes.T_3594_S, SpellParticle.P_1922_E::new);
        this.n_1700_B(ParticleTypes.i_1637_u, DripParticle.J_1907_R::new);
        this.n_1700_B(ParticleTypes.Ping, DripParticle.v_4262_N::new);
        this.n_1700_B(ParticleTypes.p_178_J, DripParticle.M_182_A::new);
        this.n_1700_B(ParticleTypes.RealmsClientConfig, DripParticle.u_2550_I::new);
        this.n_1700_B(ParticleTypes.f_4016_n, K_1668_X.n_1700_B::new);
        this.n_1700_B(ParticleTypes.j_276_v, SuspendedParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.UploadStatus, SuspendedParticle.R_4764_Y::new);
        this.n_1700_B(ParticleTypes.e_1992_r, DripParticle.P_1922_E::new);
        this.n_1700_B(ParticleTypes.D_60_a, DripParticle.P_4830_p::new);
        this.n_1700_B(ParticleTypes.k_3961_g, DripParticle.multiplayerClientSuggestionProvider::new);
        this.n_1700_B(ParticleTypes.Ops, ReversePortalParticle.n_1700_B::new);
        this.n_1700_B(ParticleTypes.h_4320_q, X_1567_o.n_1700_B::new);
        this.n_1700_B(m_229_F.J_1907_R, f_4340_D.n_1700_B::new);
        this.n_1700_B(m_229_F.R_4764_Y, new A_2204_Z.n_1700_B());
        this.n_1700_B(m_229_F.G_564_y, S_315_z.n_1700_B::new);
        this.n_1700_B(m_229_F.P_1922_E, R_4912_F.n_1700_B::new);
        this.n_1700_B(m_229_F.u_1723_Y, o_977_F.n_1700_B::new);
        this.n_1700_B(n_421_x.J_1907_R, A_4252_m.n_1700_B::new);
        this.n_1700_B(n_421_x.R_4764_Y, a_794_m.n_1700_B::new);
        this.n_1700_B(n_421_x.G_564_y, K_2336_H.n_1700_B::new);
        this.n_1700_B(n_421_x.P_1922_E, e_87_p.n_1700_B::new);
        this.n_1700_B(n_421_x.u_1723_Y, p_1976_q.n_1700_B::new);
    }

    private <T extends ParticleOptions> void n_1700_B(ParticleType<T> particleTypeIn, ParticleProvider<T> particleFactoryIn) {
        this.w_1484_f.put(V_3137_a.g_164_R.J_1907_R(particleTypeIn), particleFactoryIn);
    }

    private <T extends ParticleOptions> void n_1700_B(ParticleType<T> particleTypeIn, J_1907_R<T> particleMetaFactoryIn) {
        n_1700_B particlemanager$animatedspriteimpl = new n_1700_B(this);
        this.s_956_w.put(V_3137_a.g_164_R.J_1907_R(particleTypeIn), particlemanager$animatedspriteimpl);
        this.w_1484_f.put(V_3137_a.g_164_R.J_1907_R(particleTypeIn), particleMetaFactoryIn.create(particlemanager$animatedspriteimpl));
    }

    @Override
    public CompletableFuture<Void> reload(PreparableReloadListener.n_1700_B stage, ResourceManager resourceManager, ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler, Executor backgroundExecutor, Executor gameExecutor) {
        ConcurrentMap map = Maps.newConcurrentMap();
        CompletableFuture[] completablefuture = (CompletableFuture[])V_3137_a.g_164_R.G_564_y().stream().map(p_lambda$reload$1_4_ -> CompletableFuture.runAsync(() -> this.n_1700_B(resourceManager, (g_2336_b)p_lambda$reload$1_4_, map), backgroundExecutor)).toArray(CompletableFuture[]::new);
        return ((CompletableFuture)((CompletableFuture)CompletableFuture.allOf(completablefuture).thenApplyAsync(p_lambda$reload$3_4_ -> {
            preparationsProfiler.n_1700_B();
            preparationsProfiler.n_1700_B("stitching");
            L_3848_p.n_1700_B atlastexture$sheetdata = this.u_2550_I.n_1700_B(resourceManager, map.values().stream().flatMap(Collection::stream), preparationsProfiler, 0);
            preparationsProfiler.R_4764_Y();
            preparationsProfiler.J_1907_R();
            return atlastexture$sheetdata;
        }, backgroundExecutor)).thenCompose(stage::markCompleteAwaitingOthers)).thenAcceptAsync(p_lambda$reload$5_3_ -> {
            this.G_564_y.clear();
            reloadProfiler.n_1700_B();
            reloadProfiler.n_1700_B("upload");
            this.u_2550_I.n_1700_B((L_3848_p.n_1700_B)p_lambda$reload$5_3_);
            reloadProfiler.J_1907_R("bindSpriteSets");
            B_3871_I textureatlassprite = this.u_2550_I.J_1907_R(F_3565_Q.n_1700_B());
            map.forEach((p_lambda$null$4_2_, p_lambda$null$4_3_) -> {
                ImmutableList immutablelist = p_lambda$null$4_3_.isEmpty() ? ImmutableList.of((Object)textureatlassprite) : (ImmutableList)p_lambda$null$4_3_.stream().map(this.u_2550_I::J_1907_R).collect(ImmutableList.toImmutableList());
                this.s_956_w.get(p_lambda$null$4_2_).n_1700_B((List<B_3871_I>)immutablelist);
            });
            reloadProfiler.R_4764_Y();
            reloadProfiler.J_1907_R();
        }, gameExecutor);
    }

    public void J_1907_R() {
        this.u_2550_I.J_1907_R();
    }

    private void n_1700_B(ResourceManager manager, g_2336_b particleId, Map<g_2336_b, List<g_2336_b>> textures) {
        block17: {
            g_2336_b resourcelocation = new g_2336_b(particleId.R_4764_Y(), "particles/" + particleId.J_1907_R() + ".json");
            try (Resource iresource = manager.n_1700_B(resourcelocation);
                 InputStreamReader reader = new InputStreamReader(iresource.J_1907_R(), Charsets.UTF_8);){
                x_4101_Q texturesparticle = x_4101_Q.n_1700_B(i_4431_W.n_1700_B(reader));
                List<g_2336_b> list = texturesparticle.n_1700_B();
                boolean flag = this.s_956_w.containsKey(particleId);
                if (list == null) {
                    if (flag) {
                        throw new IllegalStateException("Missing texture list for particle " + String.valueOf(particleId));
                    }
                } else {
                    if (!flag) {
                        throw new IllegalStateException("Redundant texture list for particle " + String.valueOf(particleId));
                    }
                    textures.put(particleId, list.stream().map(p_lambda$loadTextureLists$6_0_ -> new g_2336_b(p_lambda$loadTextureLists$6_0_.R_4764_Y(), "particle/" + p_lambda$loadTextureLists$6_0_.J_1907_R())).collect(Collectors.toList()));
                }
            }
            catch (IOException ioexception1) {
                boolean hasSpriteSet = this.s_956_w.containsKey(particleId);
                if (!hasSpriteSet) break block17;
                R_4764_Y.warn("Failed to load description for particle {} (falling back to missing sprite).", (Object)particleId, (Object)ioexception1);
                textures.put(particleId, Collections.emptyList());
            }
        }
    }

    public void n_1700_B(N_4263_v entityIn, ParticleOptions particleData) {
        this.P_1922_E.add(new TrackingEmitter(this.n_1700_B, entityIn, particleData));
    }

    public void n_1700_B(N_4263_v entityIn, ParticleOptions dataIn, int lifetimeIn) {
        this.P_1922_E.add(new TrackingEmitter(this.n_1700_B, entityIn, dataIn, lifetimeIn));
    }

    @Nullable
    public c_3457_g n_1700_B(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        c_3457_g particle;
        h_3270_j event;
        if (particleData.G_564_y() == ParticleTypes.Q_2552_b || particleData.G_564_y() == ParticleTypes.C_2741_M || particleData.G_564_y() == ParticleTypes.z_4693_k) {
            event = new h_3270_j(h_3270_j.n_1700_B.q_2307_F);
            A_4115_X.n_1700_B(event);
            if (event.n_1700_B()) {
                return null;
            }
        }
        if (particleData.G_564_y() == ParticleTypes.B_1668_F || particleData.G_564_y() == ParticleTypes.d_2461_k || particleData.G_564_y() == ParticleTypes.r_715_M || particleData.G_564_y() == ParticleTypes.A_1038_p) {
            event = new h_3270_j(h_3270_j.n_1700_B.u_2550_I);
            A_4115_X.n_1700_B(event);
            if (event.n_1700_B()) {
                return null;
            }
        }
        if ((particle = this.J_1907_R(particleData, x, y, z, xSpeed, ySpeed, zSpeed)) != null) {
            this.n_1700_B(particle);
            return particle;
        }
        return null;
    }

    @Nullable
    private <T extends ParticleOptions> c_3457_g J_1907_R(T particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        ParticleProvider<?> iparticlefactory = this.w_1484_f.get(V_3137_a.g_164_R.J_1907_R(particleData.G_564_y()));
        return iparticlefactory == null ? null : iparticlefactory.n_1700_B(particleData, this.n_1700_B, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    public void n_1700_B(c_3457_g effect) {
        if (effect != null && (!(effect instanceof R_137_M.R_4764_Y) || Config.isFireworkParticles())) {
            this.t_148_a.add(effect);
        }
    }

    public void R_4764_Y() {
        this.G_564_y.forEach((p_lambda$tick$7_1_, p_lambda$tick$7_2_) -> {
            this.n_1700_B.D_4792_h().n_1700_B(p_lambda$tick$7_1_.toString());
            this.n_1700_B((Collection<c_3457_g>)p_lambda$tick$7_2_);
            this.n_1700_B.D_4792_h().R_4764_Y();
        });
        if (!this.P_1922_E.isEmpty()) {
            ArrayList list = Lists.newArrayList();
            for (TrackingEmitter emitterparticle : this.P_1922_E) {
                emitterparticle.n_1700_B();
                if (emitterparticle.M_588_G()) continue;
                list.add(emitterparticle);
            }
            this.P_1922_E.removeAll(list);
        }
        if (!this.t_148_a.isEmpty()) {
            c_3457_g particle;
            while ((particle = this.t_148_a.poll()) != null) {
                Queue queue = this.G_564_y.computeIfAbsent(particle.J_1907_R(), p_lambda$tick$8_0_ -> EvictingQueue.create((int)16384));
                if (particle instanceof BarrierParticle && this.n_1700_B(particle, (Queue<c_3457_g>)queue)) continue;
                queue.add(particle);
            }
        }
    }

    private void n_1700_B(Collection<c_3457_g> particlesIn) {
        if (!particlesIn.isEmpty()) {
            long i = System.currentTimeMillis();
            int j = particlesIn.size();
            Iterator<c_3457_g> iterator = particlesIn.iterator();
            while (iterator.hasNext()) {
                c_3457_g particle = iterator.next();
                this.J_1907_R(particle);
                if (!particle.M_588_G()) {
                    iterator.remove();
                }
                --j;
                if (System.currentTimeMillis() <= i + 20L) continue;
                break;
            }
            if (j > 0) {
                Iterator<c_3457_g> iterator1 = particlesIn.iterator();
                for (int k = j; iterator1.hasNext() && k > 0; --k) {
                    c_3457_g particle1 = iterator1.next();
                    particle1.s_956_w();
                    iterator1.remove();
                }
            }
        }
    }

    private void J_1907_R(c_3457_g particle) {
        try {
            MinecraftClient mc = MinecraftClient.A_4115_X();
            N_4263_v viewEntity = mc.g_2268_R();
            if (viewEntity == null) {
                viewEntity = mc.Y_259_p;
            }
            if (viewEntity != null) {
                double dz;
                double dy;
                double dx = particle.v_4262_N - viewEntity.O_3598_v();
                double distSq = dx * dx + (dy = particle.w_1484_f - viewEntity.X_2960_b()) * dy + (dz = particle.t_148_a - viewEntity.l_2647_k()) * dz;
                if (distSq > 1024.0 && (mc.Y_601_j.X_933_l() & 1L) == 0L) {
                    return;
                }
                if (distSq > 2304.0 && (mc.Y_601_j.X_933_l() & 3L) != 0L) {
                    return;
                }
            }
            particle.n_1700_B();
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Ticking Particle");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Particle being ticked");
            crashreportcategory.n_1700_B("Particle", particle::toString);
            crashreportcategory.n_1700_B("Particle Type", (h_1915_h)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, toString(), ()Ljava/lang/String;)((ParticleRenderType)particle.J_1907_R()));
            throw new ReportedException(crashreport);
        }
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w.n_1700_B bufferIn, e_1689_x lightTextureIn, h_3572_K activeRenderInfoIn, float partialTicks) {
        this.n_1700_B(matrixStackIn, bufferIn, lightTextureIn, activeRenderInfoIn, partialTicks, null);
    }

    public void n_1700_B(g_221_o p_renderParticles_1_, o_3091_w.n_1700_B p_renderParticles_2_, e_1689_x p_renderParticles_3_, h_3572_K p_renderParticles_4_, float p_renderParticles_5_, E_4918_z p_renderParticles_6_) {
        p_renderParticles_3_.R_4764_Y();
        Runnable runnable = () -> {
            c_4037_x.M_588_G();
            c_4037_x.l_1233_K();
            c_4037_x.multiplayerClientSuggestionProvider();
            c_4037_x.Q_2552_b();
            if (Reflector.ForgeHooksClient.exists()) {
                c_4037_x.P_1922_E(33986);
                c_4037_x.x_607_J();
                c_4037_x.P_1922_E(33984);
            }
        };
        FluidState fluidstate = p_renderParticles_4_.s_956_w();
        boolean flag = fluidstate.n_1700_B(FluidTags.J_1907_R);
        c_4037_x.v_4276_D();
        c_4037_x.n_1700_B(p_renderParticles_1_.R_4764_Y().n_1700_B());
        Collection<ParticleRenderType> collection = J_1907_R;
        if (Reflector.ForgeHooksClient.exists()) {
            collection = this.G_564_y.keySet();
        }
        for (ParticleRenderType iparticlerendertype : collection) {
            if (iparticlerendertype == ParticleRenderType.u_1723_Y) continue;
            runnable.run();
            Iterable iterable = this.G_564_y.get(iparticlerendertype);
            if (iterable == null) continue;
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            l_3747_P tessellator = l_3747_P.n_1700_B();
            D_3318_r bufferbuilder = tessellator.R_4764_Y();
            iparticlerendertype.n_1700_B(bufferbuilder, this.u_1723_Y);
            double camX = p_renderParticles_4_.J_1907_R().J_1907_R;
            double camY = p_renderParticles_4_.J_1907_R().R_4764_Y;
            double camZ = p_renderParticles_4_.J_1907_R().G_564_y;
            for (c_3457_g particle : iterable) {
                double dx = particle.v_4262_N - camX;
                double dy = particle.w_1484_f - camY;
                double dz = particle.t_148_a - camZ;
                double distSq = dx * dx + dy * dy + dz * dz;
                if (distSq > 2304.0 || p_renderParticles_6_ != null && particle.h_1847_R() && !p_renderParticles_6_.isBoundingBoxInFrustum(particle.P_4830_p())) continue;
                try {
                    if (!flag && particle instanceof SuspendedParticle && particle.s_956_w == 0.0 && particle.u_2550_I == 0.0 && particle.M_588_G == 0.0) continue;
                    particle.n_1700_B(bufferbuilder, p_renderParticles_4_, p_renderParticles_5_);
                }
                catch (Throwable throwable) {
                    n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Rendering Particle");
                    CrashReportCategory crashreportcategory = crashreport.n_1700_B("Particle being rendered");
                    crashreportcategory.n_1700_B("Particle", particle::toString);
                    crashreportcategory.n_1700_B("Particle Type", (h_1915_h)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, toString(), ()Ljava/lang/String;)((ParticleRenderType)iparticlerendertype));
                    throw new ReportedException(crashreport);
                }
            }
            iparticlerendertype.n_1700_B(tessellator);
        }
        c_4037_x.d_2461_k();
        c_4037_x.J_1907_R(true);
        c_4037_x.J_1907_R(515);
        c_4037_x.Y_259_p();
        c_4037_x.l_1233_K();
        p_renderParticles_3_.J_1907_R();
        c_4037_x.C_2741_M();
        c_4037_x.multiplayerClientSuggestionProvider();
    }

    public void n_1700_B(@Nullable b_4507_u worldIn) {
        this.n_1700_B = worldIn;
        this.G_564_y.clear();
        this.P_1922_E.clear();
    }

    public void n_1700_B(@Nullable c_3005_b worldIn) {
        this.n_1700_B = worldIn;
        this.G_564_y.clear();
        this.P_1922_E.clear();
    }

    public void n_1700_B(c_1514_x pos, K_4074_S state) {
        boolean flag;
        if (Reflector.IForgeBlockState_addDestroyEffects.exists() && Reflector.IForgeBlockState_isAir2.exists()) {
            T_2915_h block = state.J_1907_R();
            flag = !Reflector.callBoolean(state, Reflector.IForgeBlockState_isAir2, this.n_1700_B, pos) && !Reflector.callBoolean(state, Reflector.IForgeBlockState_addDestroyEffects, this.n_1700_B, pos, this);
        } else {
            boolean bl = flag = !state.v_4262_N();
        }
        if (flag) {
            s_1395_c voxelshape = state.s_956_w(this.n_1700_B, pos);
            double d0 = 0.25;
            voxelshape.J_1907_R((double p_lambda$addBlockDestroyEffects$10_3_, double p_lambda$addBlockDestroyEffects$10_5_, double p_lambda$addBlockDestroyEffects$10_7_, double p_lambda$addBlockDestroyEffects$10_9_, double p_lambda$addBlockDestroyEffects$10_11_, double p_lambda$addBlockDestroyEffects$10_13_) -> {
                double d1 = Math.min(1.0, p_lambda$addBlockDestroyEffects$10_9_ - p_lambda$addBlockDestroyEffects$10_3_);
                double d2 = Math.min(1.0, p_lambda$addBlockDestroyEffects$10_11_ - p_lambda$addBlockDestroyEffects$10_5_);
                double d3 = Math.min(1.0, p_lambda$addBlockDestroyEffects$10_13_ - p_lambda$addBlockDestroyEffects$10_7_);
                int i = Math.max(2, u_530_F.P_1922_E(d1 / 0.25));
                int j = Math.max(2, u_530_F.P_1922_E(d2 / 0.25));
                int k = Math.max(2, u_530_F.P_1922_E(d3 / 0.25));
                for (int l = 0; l < i; ++l) {
                    for (int i1 = 0; i1 < j; ++i1) {
                        for (int j1 = 0; j1 < k; ++j1) {
                            double d4 = ((double)l + 0.5) / (double)i;
                            double d5 = ((double)i1 + 0.5) / (double)j;
                            double d6 = ((double)j1 + 0.5) / (double)k;
                            double d7 = d4 * d1 + p_lambda$addBlockDestroyEffects$10_3_;
                            double d8 = d5 * d2 + p_lambda$addBlockDestroyEffects$10_5_;
                            double d9 = d6 * d3 + p_lambda$addBlockDestroyEffects$10_7_;
                            this.n_1700_B(new TerrainParticle(this.n_1700_B, (double)pos.getX() + d7, (double)pos.getY() + d8, (double)pos.getZ() + d9, d4 - 0.5, d5 - 0.5, d6 - 0.5, state).n_1700_B(pos));
                        }
                    }
                }
            });
        }
    }

    public void n_1700_B(c_1514_x pos, b_257_Y side) {
        K_4074_S blockstate = this.n_1700_B.getBlockState(pos);
        if (blockstate.w_1484_f() != O_2369_F.n_1700_B) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            float f = 0.1f;
            I_4817_s axisalignedbb = blockstate.s_956_w(this.n_1700_B, pos).n_1700_B();
            double d0 = (double)i + this.v_4262_N.nextDouble() * (axisalignedbb.maxX - axisalignedbb.minX - (double)0.2f) + (double)0.1f + axisalignedbb.minX;
            double d1 = (double)j + this.v_4262_N.nextDouble() * (axisalignedbb.maxY - axisalignedbb.minY - (double)0.2f) + (double)0.1f + axisalignedbb.minY;
            double d2 = (double)k + this.v_4262_N.nextDouble() * (axisalignedbb.maxZ - axisalignedbb.minZ - (double)0.2f) + (double)0.1f + axisalignedbb.minZ;
            if (side == b_257_Y.n_1700_B) {
                d1 = (double)j + axisalignedbb.minY - (double)0.1f;
            }
            if (side == b_257_Y.J_1907_R) {
                d1 = (double)j + axisalignedbb.maxY + (double)0.1f;
            }
            if (side == b_257_Y.R_4764_Y) {
                d2 = (double)k + axisalignedbb.minZ - (double)0.1f;
            }
            if (side == b_257_Y.G_564_y) {
                d2 = (double)k + axisalignedbb.maxZ + (double)0.1f;
            }
            if (side == b_257_Y.P_1922_E) {
                d0 = (double)i + axisalignedbb.minX - (double)0.1f;
            }
            if (side == b_257_Y.u_1723_Y) {
                d0 = (double)i + axisalignedbb.maxX + (double)0.1f;
            }
            this.n_1700_B(new TerrainParticle(this.n_1700_B, d0, d1, d2, 0.0, 0.0, 0.0, blockstate).n_1700_B(pos).R_4764_Y(0.2f).G_564_y(0.6f));
        }
    }

    public String G_564_y() {
        return String.valueOf(this.G_564_y.values().stream().mapToInt(Collection::size).sum());
    }

    private boolean n_1700_B(c_3457_g p_reuseBarrierParticle_1_, Queue<c_3457_g> p_reuseBarrierParticle_2_) {
        for (c_3457_g particle : p_reuseBarrierParticle_2_) {
            if (!(particle instanceof BarrierParticle) || p_reuseBarrierParticle_1_.G_564_y != particle.G_564_y || p_reuseBarrierParticle_1_.P_1922_E != particle.P_1922_E || p_reuseBarrierParticle_1_.u_1723_Y != particle.u_1723_Y) continue;
            particle.w_1457_N = 0;
            return true;
        }
        return false;
    }

    public void n_1700_B(c_1514_x p_addBlockHitEffects_1_, BlockHitResult p_addBlockHitEffects_2_) {
        boolean flag;
        K_4074_S blockstate = this.n_1700_B.getBlockState(p_addBlockHitEffects_1_);
        if (blockstate != null && !(flag = Reflector.callBoolean(blockstate, Reflector.IForgeBlockState_addHitEffects, this.n_1700_B, p_addBlockHitEffects_2_, this))) {
            b_257_Y direction = p_addBlockHitEffects_2_.J_1907_R();
            this.n_1700_B(p_addBlockHitEffects_1_, direction);
        }
    }

    @FunctionalInterface
    static interface J_1907_R<T extends ParticleOptions> {
        public ParticleProvider<T> create(SpriteSet var1);
    }

    class n_1700_B
    implements SpriteSet {
        private List<B_3871_I> n_1700_B;

        private n_1700_B(b_298_p this$0) {
        }

        @Override
        public B_3871_I n_1700_B(int particleAge, int particleMaxAge) {
            return this.n_1700_B.get(particleAge * (this.n_1700_B.size() - 1) / particleMaxAge);
        }

        @Override
        public B_3871_I n_1700_B(Random rand) {
            return this.n_1700_B.get(rand.nextInt(this.n_1700_B.size()));
        }

        public void n_1700_B(List<B_3871_I> sprites) {
            this.n_1700_B = ImmutableList.copyOf(sprites);
        }
    }
}



