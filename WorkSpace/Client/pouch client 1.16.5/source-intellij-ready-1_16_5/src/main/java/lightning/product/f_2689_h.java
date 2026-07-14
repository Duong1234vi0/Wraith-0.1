/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.C_3240_x;
import lightning.product.D_3318_r;
import lightning.product.TheEndGatewayRenderer;
import lightning.product.HitResult;
import lightning.product.StructureBlockRenderer;
import lightning.product.O_1806_w;
import lightning.product.BellRenderer;
import lightning.product.R_4531_p;
import lightning.product.ConduitRenderer;
import lightning.product.T_2978_m;
import lightning.product.Position;
import lightning.product.ChestRenderer;
import lightning.product.W_2396_q;
import lightning.product.PistonHeadRenderer;
import lightning.product.Y_4083_F;
import lightning.product.Z_3224_L;
import lightning.product.b_4507_u;
import lightning.product.e_1689_x;
import lightning.product.e_2866_D;
import lightning.product.CampfireRenderer;
import lightning.product.g_221_o;
import lightning.product.h_3572_K;
import lightning.product.i_2154_H;
import lightning.product.BedRenderer;
import lightning.product.l_1802_R;
import lightning.product.ShulkerBoxRenderer;
import lightning.product.SpawnerRenderer;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.o_3091_w;
import lightning.product.ShulkerModel;
import lightning.product.CrashReportCategory;
import lightning.product.BlockEntityType;
import lightning.product.EnchantTableRenderer;
import lightning.product.LecternRenderer;
import lightning.product.BannerRenderer;
import lightning.product.z_883_p;
import net.optifine.EmissiveTextures;

public class f_2689_h {
    public final Map<BlockEntityType<?>, l_1802_R<?>> n_1700_B = Maps.newHashMap();
    public static final f_2689_h J_1907_R = new f_2689_h();
    private final D_3318_r w_1484_f = new D_3318_r(256);
    private Y_4083_F t_148_a;
    public C_3240_x R_4764_Y;
    public b_4507_u G_564_y;
    public h_3572_K P_1922_E;
    public HitResult u_1723_Y;
    public static i_2154_H v_4262_N;

    private f_2689_h() {
        this.J_1907_R(BlockEntityType.w_1484_f, new O_1806_w(this));
        this.J_1907_R(BlockEntityType.t_148_a, new SpawnerRenderer(this));
        this.J_1907_R(BlockEntityType.s_956_w, new PistonHeadRenderer(this));
        this.J_1907_R(BlockEntityType.J_1907_R, new ChestRenderer(this));
        this.J_1907_R(BlockEntityType.G_564_y, new ChestRenderer(this));
        this.J_1907_R(BlockEntityType.R_4764_Y, new ChestRenderer(this));
        this.J_1907_R(BlockEntityType.M_588_G, new EnchantTableRenderer(this));
        this.J_1907_R(BlockEntityType.A_4115_X, new LecternRenderer(this));
        this.J_1907_R(BlockEntityType.P_4830_p, new T_2978_m(this));
        this.J_1907_R(BlockEntityType.Y_259_p, new TheEndGatewayRenderer(this));
        this.J_1907_R(BlockEntityType.h_1847_R, new R_4531_p(this));
        this.J_1907_R(BlockEntityType.Q_4569_t, new W_2396_q(this));
        this.J_1907_R(BlockEntityType.w_1457_N, new BannerRenderer(this));
        this.J_1907_R(BlockEntityType.Y_601_j, new StructureBlockRenderer(this));
        this.J_1907_R(BlockEntityType.C_2741_M, new ShulkerBoxRenderer(new ShulkerModel(), this));
        this.J_1907_R(BlockEntityType.k_2293_S, new BedRenderer(this));
        this.J_1907_R(BlockEntityType.q_2307_F, new ConduitRenderer(this));
        this.J_1907_R(BlockEntityType.Y_1740_V, new BellRenderer(this));
        this.J_1907_R(BlockEntityType.x_607_J, new CampfireRenderer(this));
    }

    private <E extends i_2154_H> void J_1907_R(BlockEntityType<E> typeIn, l_1802_R<E> rendererIn) {
        this.n_1700_B.put(typeIn, rendererIn);
    }

    @Nullable
    public <E extends i_2154_H> l_1802_R<E> n_1700_B(E tileEntityIn) {
        return this.n_1700_B.get(tileEntityIn.z_1737_N());
    }

    public void n_1700_B(b_4507_u worldIn, C_3240_x textureManagerIn, Y_4083_F fontRendererIn, h_3572_K activeRenderInfoIn, HitResult rayTraceResultIn) {
        if (this.G_564_y != worldIn) {
            this.n_1700_B(worldIn);
        }
        this.R_4764_Y = textureManagerIn;
        this.P_1922_E = activeRenderInfoIn;
        this.t_148_a = fontRendererIn;
        this.u_1723_Y = rayTraceResultIn;
    }

    public <E extends i_2154_H> void n_1700_B(E tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn) {
        l_1802_R tileentityrenderer;
        if (e_2866_D.n_1700_B(tileEntityIn.x_607_J()).n_1700_B((Position)this.P_1922_E.J_1907_R(), tileEntityIn.t_148_a()) && (tileentityrenderer = this.n_1700_B(tileEntityIn)) != null && tileEntityIn.t_4043_B() && tileEntityIn.z_1737_N().n_1700_B(tileEntityIn.e_4240_b().J_1907_R())) {
            f_2689_h.n_1700_B(tileEntityIn, () -> f_2689_h.n_1700_B(tileentityrenderer, tileEntityIn, partialTicks, matrixStackIn, bufferIn));
        }
    }

    private static <T extends i_2154_H> void n_1700_B(l_1802_R<T> rendererIn, T tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn) {
        b_4507_u world = tileEntityIn.c_3005_b();
        int i = world != null ? z_883_p.n_1700_B(world, tileEntityIn.x_607_J()) : 0xF000F0;
        v_4262_N = tileEntityIn;
        if (EmissiveTextures.isActive()) {
            EmissiveTextures.beginRender();
        }
        rendererIn.n_1700_B(tileEntityIn, partialTicks, matrixStackIn, bufferIn, i, Z_3224_L.n_1700_B);
        if (EmissiveTextures.isActive()) {
            if (EmissiveTextures.hasEmissive()) {
                EmissiveTextures.beginRenderEmissive();
                rendererIn.n_1700_B(tileEntityIn, partialTicks, matrixStackIn, bufferIn, e_1689_x.n_1700_B, Z_3224_L.n_1700_B);
                EmissiveTextures.endRenderEmissive();
            }
            EmissiveTextures.endRender();
        }
        v_4262_N = null;
    }

    public <E extends i_2154_H> boolean n_1700_B(E tileEntityIn, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        l_1802_R tileentityrenderer = this.n_1700_B(tileEntityIn);
        if (tileentityrenderer == null) {
            return true;
        }
        f_2689_h.n_1700_B(tileEntityIn, () -> {
            v_4262_N = tileEntityIn;
            tileentityrenderer.n_1700_B(tileEntityIn, 0.0f, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
            v_4262_N = null;
        });
        return false;
    }

    private static void n_1700_B(i_2154_H tileEntityIn, Runnable runnableIn) {
        try {
            runnableIn.run();
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Rendering Block Entity");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Block Entity Details");
            tileEntityIn.n_1700_B(crashreportcategory);
            throw new ReportedException(crashreport);
        }
    }

    public void n_1700_B(@Nullable b_4507_u worldIn) {
        this.G_564_y = worldIn;
        if (worldIn == null) {
            this.P_1922_E = null;
        }
    }

    public Y_4083_F n_1700_B() {
        return this.t_148_a;
    }

    public l_1802_R n_1700_B(BlockEntityType p_getRenderer_1_) {
        return this.n_1700_B.get(p_getRenderer_1_);
    }

    public synchronized <T extends i_2154_H> void n_1700_B(BlockEntityType<T> p_setSpecialRendererInternal_1_, l_1802_R<? super T> p_setSpecialRendererInternal_2_) {
        this.n_1700_B.put(p_setSpecialRendererInternal_1_, p_setSpecialRendererInternal_2_);
    }
}


