/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  lombok.Generated
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.GuardianRenderer;
import lightning.product.A_4115_X;
import lightning.product.WitherBossRenderer;
import lightning.product.PandaRenderer;
import lightning.product.MushroomCowRenderer;
import lightning.product.B_3871_I;
import lightning.product.SquidRenderer;
import lightning.product.C_3240_x;
import lightning.product.D_1098_v;
import lightning.product.D_434_g;
import lightning.product.D_4792_h;
import lightning.product.EvokerFangsRenderer;
import lightning.product.E_4918_z;
import lightning.product.LightningBoltRenderer;
import lightning.product.FoxRenderer;
import lightning.product.EndermiteRenderer;
import lightning.product.F_747_P;
import lightning.product.G_2271_Y;
import lightning.product.IronGolemRenderer;
import lightning.product.ChestedHorseRenderer;
import lightning.product.H_3330_w;
import lightning.product.HoglinRenderer;
import lightning.product.EndCrystalRenderer;
import lightning.product.ChickenRenderer;
import lightning.product.I_4817_s;
import lightning.product.CodRenderer;
import lightning.product.CreeperRenderer;
import lightning.product.K_4074_S;
import lightning.product.ParrotRenderer;
import lightning.product.LeashKnotRenderer;
import lightning.product.SnowGolemRenderer;
import lightning.product.L_3109_d;
import lightning.product.TippableArrowRenderer;
import lightning.product.L_3489_J;
import lightning.product.M_1336_P;
import lightning.product.M_2044_c;
import lightning.product.WitherSkullRenderer;
import lightning.product.LlamaRenderer;
import lightning.product.DolphinRenderer;
import lightning.product.MinecartRenderer;
import lightning.product.GiantMobRenderer;
import lightning.product.VindicatorRenderer;
import lightning.product.N_4263_v;
import lightning.product.O_2369_F;
import lightning.product.PolarBearRenderer;
import lightning.product.StrayRenderer;
import lightning.product.ZoglinRenderer;
import lightning.product.SpectralArrowRenderer;
import lightning.product.ReloadableResourceManager;
import lightning.product.SlimeRenderer;
import lightning.product.T_1316_M;
import lightning.product.PhantomRenderer;
import lightning.product.ShulkerBulletRenderer;
import lightning.product.PigRenderer;
import lightning.product.V_3137_a;
import lightning.product.V_4423_d;
import lightning.product.MagmaCubeRenderer;
import lightning.product.X_4340_E;
import lightning.product.ThrownTridentRenderer;
import lightning.product.Y_4083_F;
import lightning.product.ZombieVillagerRenderer;
import lightning.product.PaintingRenderer;
import lightning.product.Z_2049_e;
import lightning.product.OcelotRenderer;
import lightning.product.Z_3224_L;
import lightning.product.TntRenderer;
import lightning.product.Z_530_i;
import lightning.product.EvokerRenderer;
import lightning.product.a_3913_L;
import lightning.product.b_2971_b;
import lightning.product.b_3746_y;
import lightning.product.b_4440_Q;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.SkeletonRenderer;
import lightning.product.c_4037_x;
import lightning.product.c_4414_P;
import lightning.product.c_4467_L;
import lightning.product.FallingBlockRenderer;
import lightning.product.WolfRenderer;
import lightning.product.e_1689_x;
import lightning.product.e_2866_D;
import lightning.product.WitchRenderer;
import lightning.product.g_221_o;
import lightning.product.CowRenderer;
import lightning.product.g_2336_b;
import lightning.product.g_2561_p;
import lightning.product.h_1935_L;
import lightning.product.h_3270_j;
import lightning.product.DrownedRenderer;
import lightning.product.h_3572_K;
import lightning.product.h_4311_S;
import lightning.product.WitherSkeletonRenderer;
import lightning.product.j_4563_n;
import lightning.product.EndermanRenderer;
import lightning.product.WanderingTraderRenderer;
import lightning.product.TurtleRenderer;
import lightning.product.RavagerRenderer;
import lightning.product.RabbitRenderer;
import lightning.product.ThrownItemRenderer;
import lightning.product.HuskRenderer;
import lightning.product.m_4813_h;
import lightning.product.PillagerRenderer;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.ClientBootstrap;
import lightning.product.o_2315_w;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.SpiderRenderer;
import lightning.product.p_2951_v;
import lightning.product.IllusionerRenderer;
import lightning.product.TropicalFishRenderer;
import lightning.product.EnderDragonPart;
import lightning.product.CrashReportCategory;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.PufferfishRenderer;
import lightning.product.HorseRenderer;
import lightning.product.s_4054_j;
import lightning.product.BatRenderer;
import lightning.product.DragonFireballRenderer;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.u_588_T;
import lightning.product.BlazeRenderer;
import lightning.product.LlamaSpitRenderer;
import lightning.product.FireworkEntityRenderer;
import lightning.product.VillagerRenderer;
import lightning.product.w_3312_T;
import lightning.product.w_3785_E;
import lightning.product.ElderGuardianRenderer;
import lightning.product.SheepRenderer;
import lightning.product.CaveSpiderRenderer;
import lightning.product.UndeadHorseRenderer;
import lightning.product.SilverfishRenderer;
import lightning.product.SalmonRenderer;
import lightning.product.StriderRenderer;
import lightning.product.ArmorStandRenderer;
import lightning.product.z_883_p;
import lombok.Generated;
import mods.baritone.utils.accessor.IEntityRenderManager;
import net.optifine.Config;
import net.optifine.DynamicLights;
import net.optifine.EmissiveTextures;
import net.optifine.entity.model.CustomEntityModels;
import net.optifine.player.PlayerItemsLayer;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;

public class w_2040_b
implements IEntityRenderManager {
    private static final o_2576_A u_1723_Y = o_2576_A.M_588_G(new g_2336_b("textures/misc/shadow.png"));
    private final Map<t_5_h, Z_2049_e> v_4262_N = Maps.newHashMap();
    private final Map<String, h_4311_S> w_1484_f = Maps.newHashMap();
    private final h_4311_S t_148_a;
    private final Y_4083_F s_956_w;
    public final C_3240_x n_1700_B;
    private b_4507_u u_2550_I;
    public h_3572_K J_1907_R;
    private w_3785_E M_588_G;
    public N_4263_v R_4764_Y;
    public final V_4423_d G_564_y;
    private boolean P_4830_p = true;
    private boolean h_1847_R;
    private boolean Q_4569_t;
    public Z_2049_e P_1922_E = null;

    public <E extends N_4263_v> int n_1700_B(E entityIn, float partialTicks) {
        int i = this.n_1700_B(entityIn).J_1907_R(entityIn, partialTicks);
        if (Config.isDynamicLights()) {
            i = DynamicLights.getCombinedLight(entityIn, i);
        }
        return i;
    }

    private <T extends N_4263_v> void n_1700_B(t_5_h<T> entityTypeIn, Z_2049_e<? super T> entityRendererIn) {
        this.v_4262_N.put(entityTypeIn, entityRendererIn);
    }

    private void n_1700_B(H_3330_w itemRendererIn, ReloadableResourceManager resourceManagerIn) {
        this.n_1700_B(t_5_h.n_1700_B, new L_3109_d(this));
        this.n_1700_B(t_5_h.J_1907_R, new ArmorStandRenderer(this));
        this.n_1700_B(t_5_h.R_4764_Y, new TippableArrowRenderer(this));
        this.n_1700_B(t_5_h.G_564_y, new BatRenderer(this));
        this.n_1700_B(t_5_h.P_1922_E, new L_3489_J(this));
        this.n_1700_B(t_5_h.u_1723_Y, new BlazeRenderer(this));
        this.n_1700_B(t_5_h.v_4262_N, new m_4813_h(this));
        this.n_1700_B(t_5_h.w_1484_f, new h_1935_L(this));
        this.n_1700_B(t_5_h.t_148_a, new CaveSpiderRenderer(this));
        this.n_1700_B(t_5_h.X_933_l, new MinecartRenderer(this));
        this.n_1700_B(t_5_h.s_956_w, new ChickenRenderer(this));
        this.n_1700_B(t_5_h.u_2550_I, new CodRenderer(this));
        this.n_1700_B(t_5_h.Z_976_R, new MinecartRenderer(this));
        this.n_1700_B(t_5_h.M_588_G, new CowRenderer(this));
        this.n_1700_B(t_5_h.P_4830_p, new CreeperRenderer(this));
        this.n_1700_B(t_5_h.h_1847_R, new DolphinRenderer(this));
        this.n_1700_B(t_5_h.Q_4569_t, new ChestedHorseRenderer(this, 0.87f));
        this.n_1700_B(t_5_h.M_182_A, new DragonFireballRenderer(this));
        this.n_1700_B(t_5_h.t_1786_h, new DrownedRenderer(this));
        this.n_1700_B(t_5_h.RealmsWorldResetDto, new ThrownItemRenderer(this, itemRendererIn));
        this.n_1700_B(t_5_h.multiplayerClientSuggestionProvider, new ElderGuardianRenderer(this));
        this.n_1700_B(t_5_h.w_1457_N, new EndCrystalRenderer(this));
        this.n_1700_B(t_5_h.Y_601_j, new j_4563_n(this));
        this.n_1700_B(t_5_h.Y_259_p, new EndermanRenderer(this));
        this.n_1700_B(t_5_h.Q_2552_b, new EndermiteRenderer(this));
        this.n_1700_B(t_5_h.RegionPingResult, new ThrownItemRenderer(this, itemRendererIn));
        this.n_1700_B(t_5_h.k_2293_S, new EvokerFangsRenderer(this));
        this.n_1700_B(t_5_h.C_2741_M, new EvokerRenderer(this));
        this.n_1700_B(t_5_h.H_1083_k, new ThrownItemRenderer(this, itemRendererIn));
        this.n_1700_B(t_5_h.q_2307_F, new c_4414_P(this));
        this.n_1700_B(t_5_h.Z_875_P, new ThrownItemRenderer(this, itemRendererIn, 1.0f, true));
        this.n_1700_B(t_5_h.c_3005_b, new FallingBlockRenderer(this));
        this.n_1700_B(t_5_h.T_2506_i, new ThrownItemRenderer(this, itemRendererIn, 3.0f, true));
        this.n_1700_B(t_5_h.H_2857_Y, new FireworkEntityRenderer(this, itemRendererIn));
        this.n_1700_B(t_5_h.RealmsClientOutdatedScreen, new b_3746_y(this));
        this.n_1700_B(t_5_h.A_4115_X, new FoxRenderer(this));
        this.n_1700_B(t_5_h.H_1990_U, new MinecartRenderer(this));
        this.n_1700_B(t_5_h.Y_1740_V, new u_588_T(this));
        this.n_1700_B(t_5_h.t_4043_B, new GiantMobRenderer(this, 6.0f));
        this.n_1700_B(t_5_h.x_607_J, new GuardianRenderer(this));
        this.n_1700_B(t_5_h.e_4240_b, new HoglinRenderer(this));
        this.n_1700_B(t_5_h.N_2525_X, new MinecartRenderer(this));
        this.n_1700_B(t_5_h.n_3318_d, new HorseRenderer(this));
        this.n_1700_B(t_5_h.d_2427_y, new HuskRenderer(this));
        this.n_1700_B(t_5_h.z_1737_N, new IllusionerRenderer(this));
        this.n_1700_B(t_5_h.v_4276_D, new IronGolemRenderer(this));
        this.n_1700_B(t_5_h.d_2461_k, new w_3312_T(this, itemRendererIn));
        this.n_1700_B(t_5_h.G_624_v, new p_2951_v(this, itemRendererIn));
        this.n_1700_B(t_5_h.q_4610_l, new LeashKnotRenderer(this));
        this.n_1700_B(t_5_h.z_4693_k, new LightningBoltRenderer(this));
        this.n_1700_B(t_5_h.g_221_o, new LlamaRenderer(this));
        this.n_1700_B(t_5_h.e_2887_G, new LlamaSpitRenderer(this));
        this.n_1700_B(t_5_h.B_1668_F, new MagmaCubeRenderer(this));
        this.n_1700_B(t_5_h.g_164_R, new MinecartRenderer(this));
        this.n_1700_B(t_5_h.D_4792_h, new MushroomCowRenderer(this));
        this.n_1700_B(t_5_h.T_3594_S, new ChestedHorseRenderer(this, 0.92f));
        this.n_1700_B(t_5_h.s_2632_s, new OcelotRenderer(this));
        this.n_1700_B(t_5_h.l_1233_K, new PaintingRenderer(this));
        this.n_1700_B(t_5_h.z_1333_t, new PandaRenderer(this));
        this.n_1700_B(t_5_h.O_508_d, new ParrotRenderer(this));
        this.n_1700_B(t_5_h.r_715_M, new PhantomRenderer(this));
        this.n_1700_B(t_5_h.A_1038_p, new PigRenderer(this));
        this.n_1700_B(t_5_h.i_1637_u, new o_2315_w(this, false));
        this.n_1700_B(t_5_h.Ping, new o_2315_w(this, false));
        this.n_1700_B(t_5_h.p_178_J, new PillagerRenderer(this));
        this.n_1700_B(t_5_h.RealmsClientConfig, new PolarBearRenderer(this));
        this.n_1700_B(t_5_h.R_3908_n, new ThrownItemRenderer(this, itemRendererIn));
        this.n_1700_B(t_5_h.j_276_v, new PufferfishRenderer(this));
        this.n_1700_B(t_5_h.UploadStatus, new RabbitRenderer(this));
        this.n_1700_B(t_5_h.e_1992_r, new RavagerRenderer(this));
        this.n_1700_B(t_5_h.D_60_a, new SalmonRenderer(this));
        this.n_1700_B(t_5_h.k_3961_g, new SheepRenderer(this));
        this.n_1700_B(t_5_h.h_4320_q, new ShulkerBulletRenderer(this));
        this.n_1700_B(t_5_h.Ops, new D_434_g(this));
        this.n_1700_B(t_5_h.t_4219_U, new SilverfishRenderer(this));
        this.n_1700_B(t_5_h.PlayerInfo, new UndeadHorseRenderer(this));
        this.n_1700_B(t_5_h.V_1446_Y, new SkeletonRenderer(this));
        this.n_1700_B(t_5_h.V_1225_t, new SlimeRenderer(this));
        this.n_1700_B(t_5_h.U_1241_n, new ThrownItemRenderer(this, itemRendererIn, 0.75f, true));
        this.n_1700_B(t_5_h.dtoRealmsServerAddress, new ThrownItemRenderer(this, itemRendererIn));
        this.n_1700_B(t_5_h.q_1982_R, new SnowGolemRenderer(this));
        this.n_1700_B(t_5_h.c_4037_x, new MinecartRenderer(this));
        this.n_1700_B(t_5_h.w_612_n, new SpectralArrowRenderer(this));
        this.n_1700_B(t_5_h.RealmsServerPing, new SpiderRenderer(this));
        this.n_1700_B(t_5_h.j_1564_a, new SquidRenderer(this));
        this.n_1700_B(t_5_h.M_1641_O, new StrayRenderer(this));
        this.n_1700_B(t_5_h.g_2268_R, new c_4467_L(this));
        this.n_1700_B(t_5_h.f_4016_n, new TntRenderer(this));
        this.n_1700_B(t_5_h.F_1410_V, new LlamaRenderer(this));
        this.n_1700_B(t_5_h.ValueObject, new ThrownTridentRenderer(this));
        this.n_1700_B(t_5_h.S_4022_R, new TropicalFishRenderer(this));
        this.n_1700_B(t_5_h.l_4537_E, new TurtleRenderer(this));
        this.n_1700_B(t_5_h.F_2624_D, new M_2044_c(this));
        this.n_1700_B(t_5_h.RealmsDefaultUncaughtExceptionHandler, new VillagerRenderer(this, resourceManagerIn));
        this.n_1700_B(t_5_h.y_1700_S, new VindicatorRenderer(this));
        this.n_1700_B(t_5_h.u_744_e, new WanderingTraderRenderer(this));
        this.n_1700_B(t_5_h.RetryCallException, new WitchRenderer(this));
        this.n_1700_B(t_5_h.r_3651_U, new WitherBossRenderer(this));
        this.n_1700_B(t_5_h.RowButton, new WitherSkeletonRenderer(this));
        this.n_1700_B(t_5_h.LongRunningTask, new WitherSkullRenderer(this));
        this.n_1700_B(t_5_h.j_2266_I, new WolfRenderer(this));
        this.n_1700_B(t_5_h.S_980_j, new ZoglinRenderer(this));
        this.n_1700_B(t_5_h.RealmsScreenWithCallback, new UndeadHorseRenderer(this));
        this.n_1700_B(t_5_h.R_3077_Z, new G_2271_Y(this));
        this.n_1700_B(t_5_h.c_132_F, new o_2315_w(this, true));
        this.n_1700_B(t_5_h.M_2677_i, new ZombieVillagerRenderer(this, resourceManagerIn));
        this.n_1700_B(t_5_h.RealmsWorldOptions, new StriderRenderer(this));
    }

    public w_2040_b(C_3240_x textureManagerIn, H_3330_w itemRendererIn, ReloadableResourceManager resourceManagerIn, Y_4083_F fontRendererIn, V_4423_d gameSettingsIn) {
        this.n_1700_B = textureManagerIn;
        this.s_956_w = fontRendererIn;
        this.G_564_y = gameSettingsIn;
        this.n_1700_B(itemRendererIn, resourceManagerIn);
        this.t_148_a = new h_4311_S(this);
        this.w_1484_f.put("default", this.t_148_a);
        this.w_1484_f.put("slim", new h_4311_S(this, true));
        PlayerItemsLayer.register(this.w_1484_f);
    }

    public void n_1700_B() {
        for (t_5_h t_5_h2 : V_3137_a.g_221_o) {
            if (t_5_h2 == t_5_h.g_4106_L || this.v_4262_N.containsKey(t_5_h2)) continue;
            throw new IllegalStateException("No renderer registered for " + String.valueOf(V_3137_a.g_221_o.J_1907_R(t_5_h2)));
        }
    }

    public <T extends N_4263_v> Z_2049_e<? super T> n_1700_B(T entityIn) {
        if (entityIn instanceof X_4340_E) {
            String s = ((X_4340_E)entityIn).H_1990_U();
            h_4311_S playerrenderer = this.w_1484_f.get(s);
            return playerrenderer != null ? playerrenderer : this.t_148_a;
        }
        return this.v_4262_N.get(entityIn.f_4016_n());
    }

    public void n_1700_B(b_4507_u worldIn, h_3572_K activeRenderInfoIn, N_4263_v entityIn) {
        this.u_2550_I = worldIn;
        this.J_1907_R = activeRenderInfoIn;
        this.M_588_G = activeRenderInfoIn.u_1723_Y();
        this.R_4764_Y = entityIn;
    }

    public void n_1700_B(w_3785_E quaternionIn) {
        this.M_588_G = quaternionIn;
    }

    public void n_1700_B(boolean renderShadowIn) {
        this.P_4830_p = renderShadowIn;
    }

    public void J_1907_R(boolean debugBoundingBoxIn) {
        this.Q_4569_t = debugBoundingBoxIn;
    }

    public boolean J_1907_R() {
        return this.Q_4569_t;
    }

    public <E extends N_4263_v> boolean n_1700_B(E entityIn, E_4918_z frustumIn, double camX, double camY, double camZ) {
        Z_2049_e<E> entityrenderer = this.n_1700_B(entityIn);
        return entityrenderer.n_1700_B(entityIn, frustumIn, camX, camY, camZ);
    }

    public <E extends N_4263_v> void n_1700_B(E entity, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        if (entity.RealmsWorldResetDto == 0) {
            entity.q_1982_R = entity.O_3598_v();
            entity.dtoRealmsServerAddress = entity.X_2960_b();
            entity.w_612_n = entity.l_2647_k();
        }
        double d0 = F_747_P.n_1700_B(entity.q_1982_R, entity.O_3598_v(), (double)partialTicks);
        double d1 = F_747_P.n_1700_B(entity.dtoRealmsServerAddress, entity.X_2960_b(), (double)partialTicks);
        double d2 = F_747_P.n_1700_B(entity.w_612_n, entity.l_2647_k(), (double)partialTicks);
        float f = F_747_P.J_1907_R(entity.j_276_v, entity.p_178_J, partialTicks);
        int light = this.n_1700_B(entity, partialTicks);
        this.n_1700_B(entity, d0 - this.renderPosX(), d1 - this.renderPosY(), d2 - this.renderPosZ(), f, partialTicks, matrixStackIn, bufferIn, light);
    }

    public <E extends N_4263_v> void n_1700_B(E entity, float partialTicks, boolean p_147936_3_) {
        g_221_o matrixStack = new g_221_o();
        o_3091_w.n_1700_B bufferSource = MinecraftClient.A_4115_X().j_1564_a().J_1907_R();
        int light = this.n_1700_B(entity, partialTicks);
        this.n_1700_B(entity, partialTicks, matrixStack, bufferSource, light);
        bufferSource.J_1907_R();
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.J_1907_R(true);
    }

    public <E extends N_4263_v> void n_1700_B(E entityIn, double xIn, double yIn, double zIn, float rotationYawIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        if (this.J_1907_R != null) {
            if (entityIn instanceof a_3913_L && entityIn != MinecraftClient.A_4115_X().Y_259_p) {
                h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.t_1786_h);
                A_4115_X.n_1700_B(event);
                if (event.n_1700_B()) {
                    return;
                }
            }
            Z_2049_e<E> entityrenderer = this.n_1700_B(entityIn);
            try {
                e_2866_D vector3d = entityrenderer.n_1700_B(entityIn, partialTicks);
                double d2 = xIn + vector3d.n_1700_B();
                double d3 = yIn + vector3d.J_1907_R();
                double d0 = zIn + vector3d.R_4764_Y();
                matrixStackIn.n_1700_B();
                matrixStackIn.n_1700_B(d2, d3, d0);
                if (CustomEntityModels.isActive()) {
                    this.P_1922_E = entityrenderer;
                }
                if (EmissiveTextures.isActive()) {
                    EmissiveTextures.beginRender();
                }
                entityrenderer.n_1700_B(entityIn, rotationYawIn, partialTicks, matrixStackIn, bufferIn, packedLightIn);
                if (EmissiveTextures.isActive()) {
                    if (EmissiveTextures.hasEmissive()) {
                        EmissiveTextures.beginRenderEmissive();
                        entityrenderer.n_1700_B(entityIn, rotationYawIn, partialTicks, matrixStackIn, bufferIn, e_1689_x.n_1700_B);
                        EmissiveTextures.endRenderEmissive();
                    }
                    EmissiveTextures.endRender();
                }
                if (entityIn.O_4761_U()) {
                    this.n_1700_B(matrixStackIn, bufferIn, entityIn);
                }
                matrixStackIn.n_1700_B(-vector3d.n_1700_B(), -vector3d.J_1907_R(), -vector3d.R_4764_Y());
                if (this.G_564_y.z_4693_k && this.P_4830_p && entityrenderer.R_4764_Y > 0.0f && !entityIn.F_3572_x()) {
                    double d1;
                    float f;
                    h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.s_956_w);
                    A_4115_X.n_1700_B(event);
                    if (!event.n_1700_B() && (f = (float)((1.0 - (d1 = this.n_1700_B(entityIn.O_3598_v(), entityIn.X_2960_b(), entityIn.l_2647_k())) / 256.0) * (double)entityrenderer.G_564_y)) > 0.0f) {
                        w_2040_b.n_1700_B(matrixStackIn, bufferIn, entityIn, f, partialTicks, this.u_2550_I, entityrenderer.R_4764_Y);
                    }
                }
                if (this.Q_4569_t && !entityIn.F_3572_x() && !MinecraftClient.A_4115_X().UploadStatus()) {
                    this.n_1700_B(matrixStackIn, bufferIn.getBuffer(o_2576_A.C_2741_M()), entityIn, partialTicks);
                }
                matrixStackIn.J_1907_R();
            }
            catch (Throwable throwable1) {
                n_3236_c crashreport = n_3236_c.n_1700_B(throwable1, "Rendering entity in world");
                CrashReportCategory crashreportcategory = crashreport.n_1700_B("Entity being rendered");
                entityIn.n_1700_B(crashreportcategory);
                CrashReportCategory crashreportcategory1 = crashreport.n_1700_B("Renderer details");
                crashreportcategory1.n_1700_B("Assigned renderer", entityrenderer);
                crashreportcategory1.n_1700_B("Location", CrashReportCategory.n_1700_B(xIn, yIn, zIn));
                crashreportcategory1.n_1700_B("Rotation", Float.valueOf(rotationYawIn));
                crashreportcategory1.n_1700_B("Delta", Float.valueOf(partialTicks));
                throw new ReportedException(crashreport);
            }
        }
    }

    private void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, N_4263_v entityIn, float partialTicks) {
        if (!Shaders.isShadowPass) {
            boolean renderCustomHitBox;
            float f = entityIn.C_415_h() / 2.0f;
            s_4054_j hitBoxModule = (s_4054_j)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(s_4054_j.class);
            boolean bl = renderCustomHitBox = entityIn instanceof r_4811_B && hitBoxModule != null && hitBoxModule.h_1847_R();
            if (renderCustomHitBox) {
                float customSize = ((Float)s_4054_j.v_4262_N.J_1907_R()).floatValue();
                float modifiedWidth = entityIn.C_415_h() / 2.0f + customSize;
                z_883_p.n_1700_B(matrixStackIn, bufferIn, -modifiedWidth, 0.0, -modifiedWidth, modifiedWidth, entityIn.v_165_F(), modifiedWidth, 1.0f, 1.0f, 1.0f, 1.0f);
            } else {
                this.n_1700_B(matrixStackIn, bufferIn, entityIn, 1.0f, 1.0f, 1.0f);
            }
            boolean flag = entityIn instanceof b_2971_b;
            if (Reflector.IForgeEntity_isMultipartEntity.exists() && Reflector.IForgeEntity_getParts.exists()) {
                flag = Reflector.callBoolean(entityIn, Reflector.IForgeEntity_isMultipartEntity, new Object[0]);
            }
            if (flag) {
                EnderDragonPart[] aentity;
                double d0 = -u_530_F.G_564_y((double)partialTicks, entityIn.q_1982_R, entityIn.O_3598_v());
                double d1 = -u_530_F.G_564_y((double)partialTicks, entityIn.dtoRealmsServerAddress, entityIn.X_2960_b());
                double d2 = -u_530_F.G_564_y((double)partialTicks, entityIn.w_612_n, entityIn.l_2647_k());
                for (EnderDragonPart entity : aentity = Reflector.IForgeEntity_getParts.exists() ? (N_4263_v[])Reflector.call(entityIn, Reflector.IForgeEntity_getParts, new Object[0]) : ((b_2971_b)entityIn).Q_4569_t()) {
                    matrixStackIn.n_1700_B();
                    double d3 = d0 + u_530_F.G_564_y((double)partialTicks, entity.q_1982_R, entity.O_3598_v());
                    double d4 = d1 + u_530_F.G_564_y((double)partialTicks, entity.dtoRealmsServerAddress, entity.X_2960_b());
                    double d5 = d2 + u_530_F.G_564_y((double)partialTicks, entity.w_612_n, entity.l_2647_k());
                    matrixStackIn.n_1700_B(d3, d4, d5);
                    this.n_1700_B(matrixStackIn, bufferIn, entity, 0.25f, 1.0f, 0.0f);
                    matrixStackIn.J_1907_R();
                }
            }
            if (entityIn instanceof r_4811_B) {
                float f1 = 0.01f;
                z_883_p.n_1700_B(matrixStackIn, bufferIn, -f, entityIn.X_1313_W() - 0.01f, -f, f, entityIn.X_1313_W() + 0.01f, f, 1.0f, 0.0f, 0.0f, 1.0f);
            }
            e_2866_D vector3d = entityIn.t_148_a(partialTicks);
            D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
            bufferIn.n_1700_B(matrix4f, 0.0f, entityIn.X_1313_W(), 0.0f).color(0, 0, 255, 255).endVertex();
            bufferIn.n_1700_B(matrix4f, (float)(vector3d.J_1907_R * 2.0), (float)((double)entityIn.X_1313_W() + vector3d.R_4764_Y * 2.0), (float)(vector3d.G_564_y * 2.0)).color(0, 0, 255, 255).endVertex();
        }
    }

    private void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, N_4263_v entityIn, float red, float green, float blue) {
        I_4817_s axisalignedbb = entityIn.i_601_W().offset(-entityIn.O_3598_v(), -entityIn.X_2960_b(), -entityIn.l_2647_k());
        z_883_p.n_1700_B(matrixStackIn, bufferIn, axisalignedbb, red, green, blue, 1.0f);
    }

    private void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, N_4263_v entityIn) {
        B_3871_I textureatlassprite = g_2561_p.n_1700_B.R_4764_Y();
        B_3871_I textureatlassprite1 = g_2561_p.J_1907_R.R_4764_Y();
        matrixStackIn.n_1700_B();
        float f = entityIn.C_415_h() * 1.4f;
        matrixStackIn.n_1700_B(f, f, f);
        float f1 = 0.5f;
        float f2 = 0.0f;
        float f3 = entityIn.v_165_F() / f;
        float f4 = 0.0f;
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-this.J_1907_R.P_1922_E()));
        matrixStackIn.n_1700_B(0.0, 0.0, (double)(-0.3f + (float)((int)f3) * 0.02f));
        float f5 = 0.0f;
        int i = 0;
        D_4792_h ivertexbuilder = bufferIn.getBuffer(b_4440_Q.w_1484_f());
        if (Config.isMultiTexture()) {
            ivertexbuilder.setRenderBlocks(true);
        }
        g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
        while (f3 > 0.0f) {
            B_3871_I textureatlassprite2 = i % 2 == 0 ? textureatlassprite : textureatlassprite1;
            ivertexbuilder.setSprite(textureatlassprite2);
            float f6 = textureatlassprite2.u_1723_Y();
            float f7 = textureatlassprite2.w_1484_f();
            float f8 = textureatlassprite2.v_4262_N();
            float f9 = textureatlassprite2.t_148_a();
            if (i / 2 % 2 == 0) {
                float f10 = f8;
                f8 = f6;
                f6 = f10;
            }
            w_2040_b.n_1700_B(matrixstack$entry, ivertexbuilder, f1 - 0.0f, 0.0f - f4, f5, f8, f9);
            w_2040_b.n_1700_B(matrixstack$entry, ivertexbuilder, -f1 - 0.0f, 0.0f - f4, f5, f6, f9);
            w_2040_b.n_1700_B(matrixstack$entry, ivertexbuilder, -f1 - 0.0f, 1.4f - f4, f5, f6, f7);
            w_2040_b.n_1700_B(matrixstack$entry, ivertexbuilder, f1 - 0.0f, 1.4f - f4, f5, f8, f7);
            f3 -= 0.45f;
            f4 -= 0.45f;
            f1 *= 0.9f;
            f5 += 0.03f;
            ++i;
        }
        matrixStackIn.J_1907_R();
    }

    private static void n_1700_B(g_221_o.n_1700_B matrixEntryIn, D_4792_h bufferIn, float x, float y, float z, float texU, float texV) {
        bufferIn.n_1700_B(matrixEntryIn.n_1700_B(), x, y, z).color(255, 255, 255, 255).tex(texU, texV).overlay(0, 10).J_1907_R(240).n_1700_B(matrixEntryIn.J_1907_R(), 0.0f, 1.0f, 0.0f).endVertex();
    }

    private static void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, N_4263_v entityIn, float weightIn, float partialTicks, T_1316_M worldIn, float sizeIn) {
        if (!Config.isShaders() || !Shaders.shouldSkipDefaultShadow) {
            Z_530_i mobentity;
            float f = sizeIn;
            if (entityIn instanceof Z_530_i && (mobentity = (Z_530_i)entityIn).d_()) {
                f = sizeIn * 0.5f;
            }
            double d2 = u_530_F.G_564_y((double)partialTicks, entityIn.q_1982_R, entityIn.O_3598_v());
            double d0 = u_530_F.G_564_y((double)partialTicks, entityIn.dtoRealmsServerAddress, entityIn.X_2960_b());
            double d1 = u_530_F.G_564_y((double)partialTicks, entityIn.w_612_n, entityIn.l_2647_k());
            int i = u_530_F.R_4764_Y(d2 - (double)f);
            int j = u_530_F.R_4764_Y(d2 + (double)f);
            int k = u_530_F.R_4764_Y(d0 - (double)f);
            int l = u_530_F.R_4764_Y(d0);
            int i1 = u_530_F.R_4764_Y(d1 - (double)f);
            int j1 = u_530_F.R_4764_Y(d1 + (double)f);
            g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
            D_4792_h ivertexbuilder = bufferIn.getBuffer(u_1723_Y);
            for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(new c_1514_x(i, k, i1), new c_1514_x(j, l, j1))) {
                w_2040_b.n_1700_B(matrixstack$entry, ivertexbuilder, worldIn, blockpos, d2, d0, d1, f, weightIn);
            }
        }
    }

    private static void n_1700_B(g_221_o.n_1700_B matrixEntryIn, D_4792_h bufferIn, T_1316_M worldIn, c_1514_x blockPosIn, double xIn, double yIn, double zIn, float sizeIn, float weightIn) {
        float f;
        s_1395_c voxelshape;
        c_1514_x blockpos = blockPosIn.down();
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.w_1484_f() != O_2369_F.n_1700_B && worldIn.u_2550_I(blockPosIn) > 3 && blockstate.multiplayerClientSuggestionProvider(worldIn, blockpos) && !(voxelshape = blockstate.s_956_w(worldIn, blockPosIn.down())).J_1907_R() && (f = (float)(((double)weightIn - (yIn - (double)blockPosIn.getY()) / 2.0) * 0.5 * (double)worldIn.w_1484_f(blockPosIn))) >= 0.0f) {
            if (f > 1.0f) {
                f = 1.0f;
            }
            I_4817_s axisalignedbb = voxelshape.n_1700_B();
            double d0 = (double)blockPosIn.getX() + axisalignedbb.minX;
            double d1 = (double)blockPosIn.getX() + axisalignedbb.maxX;
            double d2 = (double)blockPosIn.getY() + axisalignedbb.minY;
            double d3 = (double)blockPosIn.getZ() + axisalignedbb.minZ;
            double d4 = (double)blockPosIn.getZ() + axisalignedbb.maxZ;
            float f1 = (float)(d0 - xIn);
            float f2 = (float)(d1 - xIn);
            float f3 = (float)(d2 - yIn);
            float f4 = (float)(d3 - zIn);
            float f5 = (float)(d4 - zIn);
            float f6 = -f1 / 2.0f / sizeIn + 0.5f;
            float f7 = -f2 / 2.0f / sizeIn + 0.5f;
            float f8 = -f4 / 2.0f / sizeIn + 0.5f;
            float f9 = -f5 / 2.0f / sizeIn + 0.5f;
            w_2040_b.n_1700_B(matrixEntryIn, bufferIn, f, f1, f3, f4, f6, f8);
            w_2040_b.n_1700_B(matrixEntryIn, bufferIn, f, f1, f3, f5, f6, f9);
            w_2040_b.n_1700_B(matrixEntryIn, bufferIn, f, f2, f3, f5, f7, f9);
            w_2040_b.n_1700_B(matrixEntryIn, bufferIn, f, f2, f3, f4, f7, f8);
        }
    }

    private static void n_1700_B(g_221_o.n_1700_B matrixEntryIn, D_4792_h bufferIn, float alphaIn, float xIn, float yIn, float zIn, float texU, float texV) {
        bufferIn.n_1700_B(matrixEntryIn.n_1700_B(), xIn, yIn, zIn).n_1700_B(1.0f, 1.0f, 1.0f, alphaIn).tex(texU, texV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(0xF000F0).n_1700_B(matrixEntryIn.J_1907_R(), 0.0f, 1.0f, 0.0f).endVertex();
    }

    public void n_1700_B(@Nullable b_4507_u worldIn) {
        this.u_2550_I = worldIn;
        if (worldIn == null) {
            this.J_1907_R = null;
        }
    }

    public double J_1907_R(N_4263_v entityIn) {
        return this.J_1907_R.J_1907_R().v_4262_N(entityIn.s_4990_V());
    }

    public double n_1700_B(double x, double y, double z) {
        return this.J_1907_R.J_1907_R().R_4764_Y(x, y, z);
    }

    public w_3785_E R_4764_Y() {
        return this.M_588_G;
    }

    public Y_4083_F G_564_y() {
        return this.s_956_w;
    }

    public Map<t_5_h, Z_2049_e> P_1922_E() {
        return this.v_4262_N;
    }

    public Map<String, h_4311_S> u_1723_Y() {
        return Collections.unmodifiableMap(this.w_1484_f);
    }

    @Override
    public double renderPosX() {
        return this.J_1907_R.J_1907_R().J_1907_R;
    }

    @Override
    public double renderPosY() {
        return this.J_1907_R.J_1907_R().R_4764_Y;
    }

    @Override
    public double renderPosZ() {
        return this.J_1907_R.J_1907_R().G_564_y;
    }

    @Generated
    public boolean v_4262_N() {
        return this.P_4830_p;
    }

    @Generated
    public boolean w_1484_f() {
        return this.h_1847_R;
    }

    @Generated
    public void R_4764_Y(boolean renderName) {
        this.h_1847_R = renderName;
    }
}



