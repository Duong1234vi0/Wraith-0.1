/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.D_1098_v;
import lightning.product.SolidFaceRenderer;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.H_2333_J;
import lightning.product.CollisionBoxRenderer;
import lightning.product.StructureRenderer;
import lightning.product.I_4817_s;
import lightning.product.RaidDebugRenderer;
import lightning.product.WaterDebugRenderer;
import lightning.product.N_4263_v;
import lightning.product.VillageSectionsDebugRenderer;
import lightning.product.LightDebugRenderer;
import lightning.product.S_3601_T;
import lightning.product.GoalSelectorDebugRenderer;
import lightning.product.Y_158_B;
import lightning.product.Y_3383_J;
import lightning.product.Y_4083_F;
import lightning.product.a_3494_m;
import lightning.product.NeighborsUpdateRenderer;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.e_2896_q;
import lightning.product.f_1591_A;
import lightning.product.GameTestDebugRenderer;
import lightning.product.g_221_o;
import lightning.product.h_3572_K;
import lightning.product.WorldGenAttemptRenderer;
import lightning.product.HeightMapRenderer;
import lightning.product.l_3747_P;
import lightning.product.Transformation;
import lightning.product.o_3091_w;
import lightning.product.EntityHitResult;
import lightning.product.z_883_p;

public class n_4915_r {
    public final Y_3383_J n_1700_B = new Y_3383_J();
    public final n_1700_B J_1907_R;
    public final n_1700_B R_4764_Y;
    public final n_1700_B G_564_y;
    public final n_1700_B P_1922_E;
    public final n_1700_B u_1723_Y;
    public final e_2896_q v_4262_N;
    public final StructureRenderer w_1484_f;
    public final n_1700_B t_148_a;
    public final n_1700_B s_956_w;
    public final n_1700_B u_2550_I;
    public final n_1700_B M_588_G;
    public final Y_158_B P_4830_p;
    public final VillageSectionsDebugRenderer h_1847_R;
    public final S_3601_T Q_4569_t;
    public final RaidDebugRenderer M_182_A;
    public final GoalSelectorDebugRenderer t_1786_h;
    public final GameTestDebugRenderer multiplayerClientSuggestionProvider;
    private boolean w_1457_N;

    public n_4915_r(MinecraftClient clientIn) {
        this.J_1907_R = new WaterDebugRenderer(clientIn);
        this.R_4764_Y = new f_1591_A(clientIn);
        this.G_564_y = new HeightMapRenderer(clientIn);
        this.P_1922_E = new CollisionBoxRenderer(clientIn);
        this.u_1723_Y = new NeighborsUpdateRenderer(clientIn);
        this.v_4262_N = new e_2896_q();
        this.w_1484_f = new StructureRenderer(clientIn);
        this.t_148_a = new LightDebugRenderer(clientIn);
        this.s_956_w = new WorldGenAttemptRenderer();
        this.u_2550_I = new SolidFaceRenderer(clientIn);
        this.M_588_G = new a_3494_m(clientIn);
        this.P_4830_p = new Y_158_B(clientIn);
        this.h_1847_R = new VillageSectionsDebugRenderer();
        this.Q_4569_t = new S_3601_T(clientIn);
        this.M_182_A = new RaidDebugRenderer(clientIn);
        this.t_1786_h = new GoalSelectorDebugRenderer(clientIn);
        this.multiplayerClientSuggestionProvider = new GameTestDebugRenderer();
    }

    public void n_1700_B() {
        this.n_1700_B.n_1700_B();
        this.J_1907_R.n_1700_B();
        this.R_4764_Y.n_1700_B();
        this.G_564_y.n_1700_B();
        this.P_1922_E.n_1700_B();
        this.u_1723_Y.n_1700_B();
        this.v_4262_N.n_1700_B();
        this.w_1484_f.n_1700_B();
        this.t_148_a.n_1700_B();
        this.s_956_w.n_1700_B();
        this.u_2550_I.n_1700_B();
        this.M_588_G.n_1700_B();
        this.P_4830_p.n_1700_B();
        this.h_1847_R.n_1700_B();
        this.Q_4569_t.n_1700_B();
        this.M_182_A.n_1700_B();
        this.t_1786_h.n_1700_B();
        this.multiplayerClientSuggestionProvider.n_1700_B();
    }

    public boolean J_1907_R() {
        this.w_1457_N = !this.w_1457_N;
        return this.w_1457_N;
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w.n_1700_B bufferIn, double camX, double camY, double camZ) {
        if (this.w_1457_N && !MinecraftClient.A_4115_X().UploadStatus()) {
            this.R_4764_Y.n_1700_B(matrixStackIn, bufferIn, camX, camY, camZ);
        }
        this.multiplayerClientSuggestionProvider.n_1700_B(matrixStackIn, bufferIn, camX, camY, camZ);
    }

    public static Optional<N_4263_v> n_1700_B(@Nullable N_4263_v entityIn, int distance) {
        int i;
        Predicate<N_4263_v> predicate;
        I_4817_s axisalignedbb;
        e_2866_D vector3d1;
        e_2866_D vector3d2;
        if (entityIn == null) {
            return Optional.empty();
        }
        e_2866_D vector3d = entityIn.u_2550_I(1.0f);
        EntityHitResult entityraytraceresult = H_2333_J.n_1700_B(entityIn, vector3d, vector3d2 = vector3d.P_1922_E(vector3d1 = entityIn.t_148_a(1.0f).n_1700_B((double)distance)), axisalignedbb = entityIn.i_601_W().expand(vector3d1).grow(1.0), predicate = p_217727_0_ -> !p_217727_0_.d_2461_k() && p_217727_0_.C_290_v(), i = distance * distance);
        if (entityraytraceresult == null) {
            return Optional.empty();
        }
        return vector3d.v_4262_N(entityraytraceresult.P_1922_E()) > (double)i ? Optional.empty() : Optional.of(entityraytraceresult.n_1700_B());
    }

    public static void n_1700_B(c_1514_x p_217735_0_, c_1514_x p_217735_1_, float p_217735_2_, float p_217735_3_, float p_217735_4_, float p_217735_5_) {
        h_3572_K activerenderinfo = MinecraftClient.A_4115_X().s_956_w.M_588_G();
        if (activerenderinfo.w_1484_f()) {
            e_2866_D vector3d = activerenderinfo.J_1907_R().P_1922_E();
            I_4817_s axisalignedbb = new I_4817_s(p_217735_0_, p_217735_1_).offset(vector3d);
            n_4915_r.n_1700_B(axisalignedbb, p_217735_2_, p_217735_3_, p_217735_4_, p_217735_5_);
        }
    }

    public static void n_1700_B(c_1514_x p_217736_0_, float p_217736_1_, float p_217736_2_, float p_217736_3_, float p_217736_4_, float p_217736_5_) {
        h_3572_K activerenderinfo = MinecraftClient.A_4115_X().s_956_w.M_588_G();
        if (activerenderinfo.w_1484_f()) {
            e_2866_D vector3d = activerenderinfo.J_1907_R().P_1922_E();
            I_4817_s axisalignedbb = new I_4817_s(p_217736_0_).offset(vector3d).grow(p_217736_1_);
            n_4915_r.n_1700_B(axisalignedbb, p_217736_2_, p_217736_3_, p_217736_4_, p_217736_5_);
        }
    }

    public static void n_1700_B(I_4817_s p_217730_0_, float p_217730_1_, float p_217730_2_, float p_217730_3_, float p_217730_4_) {
        n_4915_r.n_1700_B(p_217730_0_.minX, p_217730_0_.minY, p_217730_0_.minZ, p_217730_0_.maxX, p_217730_0_.maxY, p_217730_0_.maxZ, p_217730_1_, p_217730_2_, p_217730_3_, p_217730_4_);
    }

    public static void n_1700_B(double p_217733_0_, double p_217733_2_, double p_217733_4_, double p_217733_6_, double p_217733_8_, double p_217733_10_, float p_217733_12_, float p_217733_13_, float p_217733_14_, float p_217733_15_) {
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(5, E_688_b.Y_601_j);
        z_883_p.n_1700_B(bufferbuilder, p_217733_0_, p_217733_2_, p_217733_4_, p_217733_6_, p_217733_8_, p_217733_10_, p_217733_12_, p_217733_13_, p_217733_14_, p_217733_15_);
        tessellator.J_1907_R();
    }

    public static void n_1700_B(String p_217731_0_, int p_217731_1_, int p_217731_2_, int p_217731_3_, int p_217731_4_) {
        n_4915_r.n_1700_B(p_217731_0_, (double)p_217731_1_ + 0.5, (double)p_217731_2_ + 0.5, (double)p_217731_3_ + 0.5, p_217731_4_);
    }

    public static void n_1700_B(String p_217732_0_, double p_217732_1_, double p_217732_3_, double p_217732_5_, int p_217732_7_) {
        n_4915_r.n_1700_B(p_217732_0_, p_217732_1_, p_217732_3_, p_217732_5_, p_217732_7_, 0.02f);
    }

    public static void n_1700_B(String p_217729_0_, double p_217729_1_, double p_217729_3_, double p_217729_5_, int p_217729_7_, float p_217729_8_) {
        n_4915_r.n_1700_B(p_217729_0_, p_217729_1_, p_217729_3_, p_217729_5_, p_217729_7_, p_217729_8_, true, 0.0f, false);
    }

    public static void n_1700_B(String textIn, double p_217734_1_, double p_217734_3_, double p_217734_5_, int colorIn, float p_217734_8_, boolean p_217734_9_, float p_217734_10_, boolean p_217734_11_) {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        h_3572_K activerenderinfo = minecraft.s_956_w.M_588_G();
        if (activerenderinfo.w_1484_f() && minecraft.O_508_d().G_564_y != null) {
            Y_4083_F fontrenderer = minecraft.t_148_a;
            double d0 = activerenderinfo.J_1907_R().J_1907_R;
            double d1 = activerenderinfo.J_1907_R().R_4764_Y;
            double d2 = activerenderinfo.J_1907_R().G_564_y;
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y((float)(p_217734_1_ - d0), (float)(p_217734_3_ - d1) + 0.07f, (float)(p_217734_5_ - d2));
            c_4037_x.n_1700_B(0.0f, 1.0f, 0.0f);
            c_4037_x.n_1700_B(new D_1098_v(activerenderinfo.u_1723_Y()));
            c_4037_x.J_1907_R(p_217734_8_, -p_217734_8_, p_217734_8_);
            c_4037_x.x_607_J();
            if (p_217734_11_) {
                c_4037_x.t_1786_h();
            } else {
                c_4037_x.multiplayerClientSuggestionProvider();
            }
            c_4037_x.J_1907_R(true);
            c_4037_x.J_1907_R(-1.0f, 1.0f, 1.0f);
            float f = p_217734_9_ ? (float)(-fontrenderer.J_1907_R(textIn)) / 2.0f : 0.0f;
            c_4037_x.M_588_G();
            o_3091_w.n_1700_B irendertypebuffer$impl = o_3091_w.n_1700_B(l_3747_P.n_1700_B().R_4764_Y());
            fontrenderer.n_1700_B(textIn, f -= p_217734_10_ / p_217734_8_, 0.0f, colorIn, false, Transformation.n_1700_B().R_4764_Y(), (o_3091_w)irendertypebuffer$impl, p_217734_11_, 0, 0xF000F0);
            irendertypebuffer$impl.J_1907_R();
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            c_4037_x.multiplayerClientSuggestionProvider();
            c_4037_x.d_2461_k();
        }
    }

    public static interface n_1700_B {
        public void n_1700_B(g_221_o var1, o_3091_w var2, double var3, double var5, double var7);

        default public void n_1700_B() {
        }
    }
}



