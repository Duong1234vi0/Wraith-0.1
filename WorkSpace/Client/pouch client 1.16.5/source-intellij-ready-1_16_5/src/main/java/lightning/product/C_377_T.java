/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.stream.Stream;
import lightning.product.B_3871_I;
import lightning.product.C_3240_x;
import lightning.product.L_3848_p;
import lightning.product.ResourceManager;
import lightning.product.ProfilerFiller;
import lightning.product.g_2336_b;
import lightning.product.SimplePreparableReloadListener;

public abstract class C_377_T
extends SimplePreparableReloadListener<L_3848_p.n_1700_B>
implements AutoCloseable {
    private final L_3848_p n_1700_B;
    private final String J_1907_R;

    public C_377_T(C_3240_x textureManagerIn, g_2336_b atlasTextureLocation, String prefixIn) {
        this.J_1907_R = prefixIn;
        this.n_1700_B = new L_3848_p(atlasTextureLocation);
        textureManagerIn.n_1700_B(this.n_1700_B.R_4764_Y(), this.n_1700_B);
    }

    protected abstract Stream<g_2336_b> J_1907_R();

    protected B_3871_I n_1700_B(g_2336_b locationIn) {
        return this.n_1700_B.J_1907_R(this.J_1907_R(locationIn));
    }

    private g_2336_b J_1907_R(g_2336_b locationIn) {
        return new g_2336_b(locationIn.R_4764_Y(), this.J_1907_R + "/" + locationIn.J_1907_R());
    }

    protected L_3848_p.n_1700_B n_1700_B(ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        profilerIn.n_1700_B();
        profilerIn.n_1700_B("stitching");
        L_3848_p.n_1700_B atlastexture$sheetdata = this.n_1700_B.n_1700_B(resourceManagerIn, this.J_1907_R().map(this::J_1907_R), profilerIn, 0);
        profilerIn.R_4764_Y();
        profilerIn.J_1907_R();
        return atlastexture$sheetdata;
    }

    protected void n_1700_B(L_3848_p.n_1700_B objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        profilerIn.n_1700_B();
        profilerIn.n_1700_B("upload");
        this.n_1700_B.n_1700_B(objectIn);
        profilerIn.R_4764_Y();
        profilerIn.J_1907_R();
    }

    @Override
    public void close() {
        this.n_1700_B.J_1907_R();
    }

    @Override
    protected /* synthetic */ void apply(Object object, ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        this.n_1700_B((L_3848_p.n_1700_B)object, s_2107_a, x_2951_U);
    }

    @Override
    protected /* synthetic */ Object prepare(ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        return this.n_1700_B(s_2107_a, x_2951_U);
    }
}


