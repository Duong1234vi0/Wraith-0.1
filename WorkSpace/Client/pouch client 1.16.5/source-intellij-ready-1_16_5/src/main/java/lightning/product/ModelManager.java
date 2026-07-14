/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.C_3240_x;
import lightning.product.K_4074_S;
import lightning.product.L_3848_p;
import lightning.product.AtlasSet;
import lightning.product.ResourceManager;
import lightning.product.S_3826_o;
import lightning.product.ProfilerFiller;
import lightning.product.FluidState;
import lightning.product.d_1062_x;
import lightning.product.g_2336_b;
import lightning.product.g_2561_p;
import lightning.product.k_4467_X;
import lightning.product.SimplePreparableReloadListener;
import lightning.product.BlockModelShaper;

public class ModelManager
extends SimplePreparableReloadListener<g_2561_p>
implements AutoCloseable {
    private Map<g_2336_b, S_3826_o> n_1700_B;
    @Nullable
    private AtlasSet J_1907_R;
    private final BlockModelShaper R_4764_Y;
    private final C_3240_x G_564_y;
    private final k_4467_X P_1922_E;
    private int u_1723_Y;
    private S_3826_o v_4262_N;
    private Object2IntMap<K_4074_S> w_1484_f;

    public ModelManager(C_3240_x textureManagerIn, k_4467_X blockColorsIn, int maxMipmapLevelIn) {
        this.G_564_y = textureManagerIn;
        this.P_1922_E = blockColorsIn;
        this.u_1723_Y = maxMipmapLevelIn;
        this.R_4764_Y = new BlockModelShaper(this);
    }

    public S_3826_o n_1700_B(d_1062_x modelLocation) {
        return this.n_1700_B.getOrDefault(modelLocation, this.v_4262_N);
    }

    public S_3826_o J_1907_R() {
        return this.v_4262_N;
    }

    public BlockModelShaper R_4764_Y() {
        return this.R_4764_Y;
    }

    protected g_2561_p n_1700_B(ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        profilerIn.n_1700_B();
        g_2561_p modelbakery = new g_2561_p(resourceManagerIn, this.P_1922_E, profilerIn, this.u_1723_Y);
        profilerIn.J_1907_R();
        return modelbakery;
    }

    protected void n_1700_B(g_2561_p objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        profilerIn.n_1700_B();
        profilerIn.n_1700_B("upload");
        if (this.J_1907_R != null) {
            this.J_1907_R.close();
        }
        this.J_1907_R = objectIn.n_1700_B(this.G_564_y, profilerIn);
        this.n_1700_B = objectIn.n_1700_B();
        this.w_1484_f = objectIn.J_1907_R();
        this.v_4262_N = this.n_1700_B.get(g_2561_p.M_588_G);
        profilerIn.J_1907_R("cache");
        this.R_4764_Y.J_1907_R();
        profilerIn.R_4764_Y();
        profilerIn.J_1907_R();
    }

    public boolean n_1700_B(K_4074_S oldState, K_4074_S newState) {
        int j;
        if (oldState == newState) {
            return false;
        }
        int i = this.w_1484_f.getInt((Object)oldState);
        if (i != -1 && i == (j = this.w_1484_f.getInt((Object)newState))) {
            FluidState fluidstate1;
            FluidState fluidstate = oldState.P_4830_p();
            return fluidstate != (fluidstate1 = newState.P_4830_p());
        }
        return true;
    }

    public L_3848_p n_1700_B(g_2336_b locationIn) {
        return this.J_1907_R.n_1700_B(locationIn);
    }

    @Override
    public void close() {
        if (this.J_1907_R != null) {
            this.J_1907_R.close();
        }
    }

    public void n_1700_B(int levelIn) {
        this.u_1723_Y = levelIn;
    }

    @Override
    protected /* synthetic */ void apply(Object object, ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        this.n_1700_B((g_2561_p)object, s_2107_a, x_2951_U);
    }

    @Override
    protected /* synthetic */ Object prepare(ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        return this.n_1700_B(s_2107_a, x_2951_U);
    }
}


