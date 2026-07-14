/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.Removals;
import lightning.product.I_4817_s;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.a_3913_L;
import lightning.product.MinecraftAccess;
import lightning.product.e_2866_D;
import lightning.product.i_601_W;
import lightning.product.ClientBootstrap;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;
import org.joml.Vector2f;

public class v_2826_q
implements MinecraftAccess {
    public static Vector2f n_1700_B(e_2866_D vec) {
        return v_2826_q.n_1700_B(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y);
    }

    public static Vector2f n_1700_B(double x, double y, double z) {
        e_2866_D camera_pos = v_2826_q.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        w_3785_E cameraRotation = c_3005_b.O_508_d().R_4764_Y().v_4262_N();
        cameraRotation.P_1922_E();
        M_1336_P result3f = new M_1336_P((float)(camera_pos.J_1907_R - x), (float)(camera_pos.R_4764_Y - y), (float)(camera_pos.G_564_y - z));
        result3f.n_1700_B(cameraRotation);
        N_4263_v renderViewEntity = c_3005_b.g_2268_R();
        if (renderViewEntity instanceof a_3913_L) {
            a_3913_L playerentity = (a_3913_L)renderViewEntity;
            if (!ClientBootstrap.Y_601_j().J_1907_R().P_1922_E.w_1484_f() || !Removals.v_4262_N.J_1907_R("\u0422\u0440\u044f\u0441\u043a\u0430 \u043a\u0430\u043c\u0435\u0440\u044b").booleanValue()) {
                v_2826_q.J_1907_R(playerentity, result3f);
            }
            if (v_2826_q.c_3005_b.P_4830_p.T_3594_S) {
                v_2826_q.n_1700_B(playerentity, result3f);
            }
        }
        double fov = v_2826_q.c_3005_b.s_956_w.n_1700_B(v_2826_q.c_3005_b.O_508_d().J_1907_R, c_3005_b.RealmsClientConfig(), true);
        float aspectRatio = v_2826_q.n_1700_B();
        return v_2826_q.n_1700_B(result3f, fov, aspectRatio);
    }

    private static void n_1700_B(a_3913_L playerentity, M_1336_P result3f) {
        float walked = playerentity.PlayerInfo;
        float f = walked - playerentity.V_1446_Y;
        float f1 = -(walked + f * c_3005_b.RealmsClientConfig());
        float f2 = u_530_F.v_4262_N(c_3005_b.RealmsClientConfig(), playerentity.X_290_I, playerentity.O_1795_e);
        w_3785_E quaternion = new w_3785_E(M_1336_P.J_1907_R, Math.abs(u_530_F.J_1907_R(f1 * (float)Math.PI - 0.2f) * f2) * 5.0f, true);
        quaternion.P_1922_E();
        result3f.n_1700_B(quaternion);
        w_3785_E quaternion1 = new w_3785_E(M_1336_P.u_1723_Y, u_530_F.n_1700_B(f1 * (float)Math.PI) * f2 * 3.0f, true);
        quaternion1.P_1922_E();
        result3f.n_1700_B(quaternion1);
        M_1336_P bobTranslation = new M_1336_P(u_530_F.n_1700_B(f1 * (float)Math.PI) * f2 * 0.5f, -Math.abs(u_530_F.J_1907_R(f1 * (float)Math.PI) * f2), 0.0f);
        bobTranslation.P_1922_E(-bobTranslation.J_1907_R());
        result3f.n_1700_B(bobTranslation);
    }

    private static void J_1907_R(a_3913_L playerentity, M_1336_P result3f) {
        w_3785_E quaternion1;
        float partialTicks = c_3005_b.RealmsClientConfig();
        float f = (float)playerentity.RealmsLongRunningMcoTaskScreen - partialTicks;
        if (playerentity.Z_2812_M()) {
            float f1 = Math.min((float)playerentity.O_2151_c + partialTicks, 20.0f);
            quaternion1 = new w_3785_E(M_1336_P.u_1723_Y, 40.0f - 8000.0f / (f1 + 200.0f), true);
            quaternion1.P_1922_E();
            result3f.n_1700_B(quaternion1);
        }
        if (f < 0.0f) {
            return;
        }
        f /= (float)playerentity.i_2993_w;
        f = u_530_F.n_1700_B(f * f * f * f * (float)Math.PI);
        float f2 = playerentity.RealmsParentalConsentScreen;
        quaternion1 = new w_3785_E(M_1336_P.G_564_y, -f2, true);
        quaternion1.P_1922_E();
        result3f.n_1700_B(quaternion1);
        w_3785_E quaternion2 = new w_3785_E(M_1336_P.u_1723_Y, -f * 14.0f, true);
        quaternion2.P_1922_E();
        result3f.n_1700_B(quaternion2);
        w_3785_E quaternion3 = new w_3785_E(M_1336_P.u_1723_Y, f2, true);
        quaternion3.P_1922_E();
        result3f.n_1700_B(quaternion3);
    }

    private static float n_1700_B() {
        float nativeAspectRatio = (float)c_3005_b.RealmsServerPing().u_2550_I() / (float)c_3005_b.RealmsServerPing().M_588_G();
        i_601_W event = new i_601_W(nativeAspectRatio);
        A_4115_X.n_1700_B(event);
        float effectiveAspect = event.n_1700_B() ? nativeAspectRatio : event.J_1907_R();
        return effectiveAspect / nativeAspectRatio;
    }

    private static Vector2f n_1700_B(M_1336_P result3f, double fov, float aspectRatio) {
        float halfHeight = (float)H_2857_Y.M_182_A() / 2.0f;
        float scaleFactor = halfHeight / (result3f.R_4764_Y() * (float)Math.tan(Math.toRadians(fov / 2.0)));
        if (result3f.R_4764_Y() < 0.0f) {
            return new Vector2f(-result3f.n_1700_B() * scaleFactor / aspectRatio + (float)c_3005_b.RealmsServerPing().Q_4569_t() / 2.0f, (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f - result3f.J_1907_R() * scaleFactor);
        }
        return new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    public static I_4817_s n_1700_B(N_4263_v entity, e_2866_D vec) {
        e_2866_D size = new e_2866_D(entity.i_601_W().maxX - entity.i_601_W().minX, entity.i_601_W().maxY - entity.i_601_W().minY, entity.i_601_W().maxZ - entity.i_601_W().minZ);
        return new I_4817_s(vec.J_1907_R - size.J_1907_R / 2.0, vec.R_4764_Y, vec.G_564_y - size.G_564_y / 2.0, vec.J_1907_R + size.J_1907_R / 2.0, vec.R_4764_Y + size.R_4764_Y + (double)(0.2f - (entity.q_2307_F() && !v_2826_q.c_3005_b.Y_259_p.C_415_h.J_1907_R ? 0.1f : 0.0f)), vec.G_564_y + size.G_564_y / 2.0);
    }

    public static e_2866_D[] n_1700_B(I_4817_s AABB) {
        return new e_2866_D[]{new e_2866_D(AABB.minX, AABB.minY, AABB.minZ), new e_2866_D(AABB.minX, AABB.minY, AABB.maxZ), new e_2866_D(AABB.minX, AABB.maxY, AABB.minZ), new e_2866_D(AABB.minX, AABB.maxY, AABB.maxZ), new e_2866_D(AABB.maxX, AABB.minY, AABB.minZ), new e_2866_D(AABB.maxX, AABB.minY, AABB.maxZ), new e_2866_D(AABB.maxX, AABB.maxY, AABB.minZ), new e_2866_D(AABB.maxX, AABB.maxY, AABB.maxZ)};
    }

    public static boolean n_1700_B(N_4263_v entity) {
        if (c_3005_b.g_2268_R() == null) {
            return false;
        }
        return v_2826_q.c_3005_b.u_1723_Y.v_4276_D().isBoundingBoxInFrustum(entity.i_601_W()) || entity.RowButton;
    }

    public static float n_1700_B(e_2866_D worldPos, float baseSize) {
        e_2866_D camera_pos = v_2826_q.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        w_3785_E cameraRotation = c_3005_b.O_508_d().R_4764_Y().v_4262_N();
        cameraRotation.P_1922_E();
        M_1336_P vecToTarget = new M_1336_P((float)(camera_pos.J_1907_R - worldPos.J_1907_R), (float)(camera_pos.R_4764_Y - worldPos.R_4764_Y), (float)(camera_pos.G_564_y - worldPos.G_564_y));
        vecToTarget.n_1700_B(cameraRotation);
        float depth = vecToTarget.R_4764_Y();
        if (depth >= 0.0f) {
            return 0.0f;
        }
        double fov = v_2826_q.c_3005_b.s_956_w.n_1700_B(v_2826_q.c_3005_b.O_508_d().J_1907_R, c_3005_b.RealmsClientConfig(), true);
        float scaleFactor = (float)c_3005_b.RealmsServerPing().M_182_A() / (2.0f * Math.abs(depth) * (float)Math.tan(Math.toRadians(fov / 2.0)));
        return baseSize * scaleFactor * 0.01f;
    }
}



