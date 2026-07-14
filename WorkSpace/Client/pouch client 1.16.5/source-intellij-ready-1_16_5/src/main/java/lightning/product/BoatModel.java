/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import lightning.product.ListModel;
import lightning.product.e_4189_z;
import lightning.product.g_1462_f;
import lightning.product.u_530_F;

public class BoatModel
extends ListModel<g_1462_f> {
    private final e_4189_z[] n_1700_B = new e_4189_z[2];
    private final e_4189_z J_1907_R;
    private final ImmutableList<e_4189_z> R_4764_Y;

    public BoatModel() {
        e_4189_z[] amodelrenderer = new e_4189_z[]{new e_4189_z(this, 0, 0).J_1907_R(128, 64), new e_4189_z(this, 0, 19).J_1907_R(128, 64), new e_4189_z(this, 0, 27).J_1907_R(128, 64), new e_4189_z(this, 0, 35).J_1907_R(128, 64), new e_4189_z(this, 0, 43).J_1907_R(128, 64)};
        int i = 32;
        int j = 6;
        int k = 20;
        int l = 4;
        int i1 = 28;
        amodelrenderer[0].n_1700_B(-14.0f, -9.0f, -3.0f, 28.0f, 16.0f, 3.0f, 0.0f);
        amodelrenderer[0].n_1700_B(0.0f, 3.0f, 1.0f);
        amodelrenderer[1].n_1700_B(-13.0f, -7.0f, -1.0f, 18.0f, 6.0f, 2.0f, 0.0f);
        amodelrenderer[1].n_1700_B(-15.0f, 4.0f, 4.0f);
        amodelrenderer[2].n_1700_B(-8.0f, -7.0f, -1.0f, 16.0f, 6.0f, 2.0f, 0.0f);
        amodelrenderer[2].n_1700_B(15.0f, 4.0f, 0.0f);
        amodelrenderer[3].n_1700_B(-14.0f, -7.0f, -1.0f, 28.0f, 6.0f, 2.0f, 0.0f);
        amodelrenderer[3].n_1700_B(0.0f, 4.0f, -9.0f);
        amodelrenderer[4].n_1700_B(-14.0f, -7.0f, -1.0f, 28.0f, 6.0f, 2.0f, 0.0f);
        amodelrenderer[4].n_1700_B(0.0f, 4.0f, 9.0f);
        amodelrenderer[0].u_1723_Y = 1.5707964f;
        amodelrenderer[1].v_4262_N = 4.712389f;
        amodelrenderer[2].v_4262_N = 1.5707964f;
        amodelrenderer[3].v_4262_N = (float)Math.PI;
        this.n_1700_B[0] = this.n_1700_B(true);
        this.n_1700_B[0].n_1700_B(3.0f, -5.0f, 9.0f);
        this.n_1700_B[1] = this.n_1700_B(false);
        this.n_1700_B[1].n_1700_B(3.0f, -5.0f, -9.0f);
        this.n_1700_B[1].v_4262_N = (float)Math.PI;
        this.n_1700_B[0].w_1484_f = 0.19634955f;
        this.n_1700_B[1].w_1484_f = 0.19634955f;
        this.J_1907_R = new e_4189_z(this, 0, 0).J_1907_R(128, 64);
        this.J_1907_R.n_1700_B(-14.0f, -9.0f, -3.0f, 28.0f, 16.0f, 3.0f, 0.0f);
        this.J_1907_R.n_1700_B(0.0f, -3.0f, 1.0f);
        this.J_1907_R.u_1723_Y = 1.5707964f;
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.addAll(Arrays.asList(amodelrenderer));
        builder.addAll(Arrays.asList(this.n_1700_B));
        this.R_4764_Y = builder.build();
    }

    @Override
    public void n_1700_B(g_1462_f entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B(entityIn, 0, limbSwing);
        this.n_1700_B(entityIn, 1, limbSwing);
    }

    public ImmutableList<e_4189_z> J_1907_R() {
        return this.R_4764_Y;
    }

    public e_4189_z R_4764_Y() {
        return this.J_1907_R;
    }

    protected e_4189_z n_1700_B(boolean p_187056_1_) {
        e_4189_z modelrenderer = new e_4189_z(this, 62, p_187056_1_ ? 0 : 20).J_1907_R(128, 64);
        int i = 20;
        int j = 7;
        int k = 6;
        float f = -5.0f;
        modelrenderer.n_1700_B(-1.0f, 0.0f, -5.0f, 2.0f, 2.0f, 18.0f);
        modelrenderer.n_1700_B(p_187056_1_ ? -1.001f : 0.001f, -3.0f, 8.0f, 1.0f, 6.0f, 7.0f);
        return modelrenderer;
    }

    protected void n_1700_B(g_1462_f p_228244_1_, int p_228244_2_, float p_228244_3_) {
        float f = p_228244_1_.n_1700_B(p_228244_2_, p_228244_3_);
        e_4189_z modelrenderer = this.n_1700_B[p_228244_2_];
        modelrenderer.u_1723_Y = (float)u_530_F.J_1907_R(-1.0471975803375244, -0.2617993950843811, (double)((u_530_F.n_1700_B(-f) + 1.0f) / 2.0f));
        modelrenderer.v_4262_N = (float)u_530_F.J_1907_R(-0.7853981852531433, 0.7853981852531433, (double)((u_530_F.n_1700_B(-f + 1.0f) + 1.0f) / 2.0f));
        if (p_228244_2_ == 1) {
            modelrenderer.v_4262_N = (float)Math.PI - modelrenderer.v_4262_N;
        }
    }

    @Override
    public /* synthetic */ Iterable n_1700_B() {
        return this.J_1907_R();
    }
}


