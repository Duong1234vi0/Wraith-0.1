/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2IntFunction
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import java.util.Calendar;
import lightning.product.D_4792_h;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.N_4989_q;
import lightning.product.DoubleBlockCombiner;
import lightning.product.T_2910_P;
import lightning.product.T_2915_h;
import lightning.product.LidBlockEntity;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4440_Q;
import lightning.product.b_4507_u;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.i_2154_H;
import lightning.product.AbstractChestBlock;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.p_1429_o;
import lightning.product.v_3445_Z;

public class ChestRenderer<T extends i_2154_H>
extends l_1802_R<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z w_1484_f;
    private final e_4189_z t_148_a;
    private final e_4189_z s_956_w;
    private boolean u_2550_I;

    public ChestRenderer(f_2689_h rendererDispatcherIn) {
        super(rendererDispatcherIn);
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
            this.u_2550_I = true;
        }
        this.J_1907_R = new e_4189_z(64, 64, 0, 19);
        this.J_1907_R.n_1700_B(1.0f, 0.0f, 1.0f, 14.0f, 10.0f, 14.0f, 0.0f);
        this.n_1700_B = new e_4189_z(64, 64, 0, 0);
        this.n_1700_B.n_1700_B(1.0f, 0.0f, 0.0f, 14.0f, 5.0f, 14.0f, 0.0f);
        this.n_1700_B.G_564_y = 9.0f;
        this.n_1700_B.P_1922_E = 1.0f;
        this.R_4764_Y = new e_4189_z(64, 64, 0, 0);
        this.R_4764_Y.n_1700_B(7.0f, -1.0f, 15.0f, 2.0f, 4.0f, 1.0f, 0.0f);
        this.R_4764_Y.G_564_y = 8.0f;
        this.P_1922_E = new e_4189_z(64, 64, 0, 19);
        this.P_1922_E.n_1700_B(1.0f, 0.0f, 1.0f, 15.0f, 10.0f, 14.0f, 0.0f);
        this.G_564_y = new e_4189_z(64, 64, 0, 0);
        this.G_564_y.n_1700_B(1.0f, 0.0f, 0.0f, 15.0f, 5.0f, 14.0f, 0.0f);
        this.G_564_y.G_564_y = 9.0f;
        this.G_564_y.P_1922_E = 1.0f;
        this.u_1723_Y = new e_4189_z(64, 64, 0, 0);
        this.u_1723_Y.n_1700_B(15.0f, -1.0f, 15.0f, 1.0f, 4.0f, 1.0f, 0.0f);
        this.u_1723_Y.G_564_y = 8.0f;
        this.t_148_a = new e_4189_z(64, 64, 0, 19);
        this.t_148_a.n_1700_B(0.0f, 0.0f, 1.0f, 15.0f, 10.0f, 14.0f, 0.0f);
        this.w_1484_f = new e_4189_z(64, 64, 0, 0);
        this.w_1484_f.n_1700_B(0.0f, 0.0f, 0.0f, 15.0f, 5.0f, 14.0f, 0.0f);
        this.w_1484_f.G_564_y = 9.0f;
        this.w_1484_f.P_1922_E = 1.0f;
        this.s_956_w = new e_4189_z(64, 64, 0, 0);
        this.s_956_w.n_1700_B(0.0f, -1.0f, 15.0f, 1.0f, 4.0f, 1.0f, 0.0f);
        this.s_956_w.G_564_y = 8.0f;
    }

    @Override
    public void n_1700_B(T tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        b_4507_u world = ((i_2154_H)tileEntityIn).c_3005_b();
        boolean flag = world != null;
        K_4074_S blockstate = flag ? ((i_2154_H)tileEntityIn).e_4240_b() : (K_4074_S)a_3742_W.L_1362_X.multiplayerClientSuggestionProvider().n_1700_B(v_3445_Z.h_1847_R, b_257_Y.G_564_y);
        p_1429_o chesttype = blockstate.J_1907_R(v_3445_Z.Q_4569_t) ? blockstate.R_4764_Y(v_3445_Z.Q_4569_t) : p_1429_o.n_1700_B;
        T_2915_h block = blockstate.J_1907_R();
        if (block instanceof AbstractChestBlock) {
            AbstractChestBlock abstractchestblock = (AbstractChestBlock)block;
            boolean flag1 = chesttype != p_1429_o.n_1700_B;
            matrixStackIn.n_1700_B();
            float f = blockstate.R_4764_Y(v_3445_Z.h_1847_R).Q_4569_t();
            matrixStackIn.n_1700_B(0.5, 0.5, 0.5);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-f));
            matrixStackIn.n_1700_B(-0.5, -0.5, -0.5);
            DoubleBlockCombiner.J_1907_R<Object> icallbackwrapper = flag ? abstractchestblock.n_1700_B(blockstate, world, ((i_2154_H)tileEntityIn).x_607_J(), true) : DoubleBlockCombiner.n_1700_B::J_1907_R;
            float f1 = icallbackwrapper.apply(v_3445_Z.n_1700_B((LidBlockEntity)tileEntityIn)).get(partialTicks);
            f1 = 1.0f - f1;
            f1 = 1.0f - f1 * f1 * f1;
            int i = ((Int2IntFunction)icallbackwrapper.apply(new N_4989_q())).applyAsInt(combinedLightIn);
            T_2910_P rendermaterial = b_4440_Q.n_1700_B(tileEntityIn, chesttype, this.u_2550_I);
            D_4792_h ivertexbuilder = rendermaterial.n_1700_B(bufferIn, o_2576_A::R_4764_Y);
            if (flag1) {
                if (chesttype == p_1429_o.J_1907_R) {
                    this.n_1700_B(matrixStackIn, ivertexbuilder, this.w_1484_f, this.s_956_w, this.t_148_a, f1, i, combinedOverlayIn);
                } else {
                    this.n_1700_B(matrixStackIn, ivertexbuilder, this.G_564_y, this.u_1723_Y, this.P_1922_E, f1, i, combinedOverlayIn);
                }
            } else {
                this.n_1700_B(matrixStackIn, ivertexbuilder, this.n_1700_B, this.R_4764_Y, this.J_1907_R, f1, i, combinedOverlayIn);
            }
            matrixStackIn.J_1907_R();
        }
    }

    private void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, e_4189_z chestLid, e_4189_z chestLatch, e_4189_z chestBottom, float lidAngle, int combinedLightIn, int combinedOverlayIn) {
        chestLatch.u_1723_Y = chestLid.u_1723_Y = -(lidAngle * 1.5707964f);
        chestLid.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        chestLatch.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        chestBottom.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
    }
}


