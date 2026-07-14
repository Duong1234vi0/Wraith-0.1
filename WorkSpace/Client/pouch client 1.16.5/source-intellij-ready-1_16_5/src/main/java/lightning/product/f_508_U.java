/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lightning.product.F_3698_k;
import lightning.product.I_4817_s;
import lightning.product.P_3504_Q;
import lightning.product.MinecraftAccess;
import lightning.product.e_2866_D;
import lightning.product.ElytraResolver;
import lightning.product.ClientBootstrap;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lombok.Generated;

public class f_508_U
implements MinecraftAccess {
    private final SecureRandom J_1907_R = new SecureRandom();
    private final float R_4764_Y = (float)Math.toDegrees(1.0);
    private static e_2866_D G_564_y;
    private static e_2866_D P_1922_E;
    public static boolean n_1700_B;

    private float n_1700_B(double d) {
        return (float)(d * (double)this.R_4764_Y);
    }

    private P_3504_Q n_1700_B(e_2866_D vector3d) {
        e_2866_D vector3d2 = vector3d.G_564_y(f_508_U.c_3005_b.Y_259_p.u_2550_I(1.0f));
        return new P_3504_Q(u_530_F.v_4262_N(this.n_1700_B(Math.atan2(vector3d2.G_564_y, vector3d2.J_1907_R)) - 90.0f), -this.n_1700_B(Math.atan2(vector3d2.R_4764_Y, Math.hypot(vector3d2.J_1907_R, vector3d2.G_564_y))));
    }

    private P_3504_Q n_1700_B(P_3504_Q vector2f, e_2866_D vector3d) {
        P_3504_Q vector2f2 = this.n_1700_B(vector3d);
        return new P_3504_Q(u_530_F.v_4262_N(vector2f2.t_148_a - vector2f.t_148_a), vector2f2.s_956_w - vector2f.s_956_w);
    }

    private float n_1700_B(float f, float f2) {
        return f + (this.J_1907_R.nextFloat() * f2 * 2.0f - f2);
    }

    private P_3504_Q n_1700_B(P_3504_Q vector2f, P_3504_Q vector2f2, float sex) {
        double d2 = (double)sex * 0.6 + 0.2;
        double d3 = d2 * d2 * d2 * 8.0 * 0.15;
        float f = vector2f.t_148_a -= (float)((double)(vector2f.t_148_a - vector2f2.t_148_a) % d3);
        float f2 = vector2f.s_956_w -= (float)((double)(vector2f.s_956_w - vector2f2.s_956_w) % d3);
        return new P_3504_Q(f, f2);
    }

    public void n_1700_B(r_4811_B target, P_3504_Q vector2f, float f, double lastSpeed, double prevSpeed) {
        e_2866_D pos;
        e_2866_D serverPos = new e_2866_D(target.V_118_c, target.I_1407_m, target.o_2767_H);
        e_2866_D serverDelta = new e_2866_D(target.V_118_c - target.d_2545_n, target.I_1407_m - target.x_92_N, target.o_2767_H - target.i_601_W);
        F_3698_k elytraTarget = ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y();
        boolean isSpeed = lastSpeed >= 20.0 || lastSpeed != prevSpeed && lastSpeed == 0.0;
        boolean overtakeEnabled = elytraTarget != null && elytraTarget.h_1847_R();
        boolean antiAir = overtakeEnabled && f_508_U.c_3005_b.Y_259_p != null && f_508_U.c_3005_b.Y_259_p.k_578_l() && isSpeed;
        ElytraResolver resolver = ClientBootstrap.Y_601_j().J_1907_R().P_1922_E();
        if (f_508_U.c_3005_b.Y_259_p.k_578_l()) {
            if (!antiAir && resolver != null && resolver.w_1484_f()) {
                e_2866_D retreatPoint;
                double currentDistance = f_508_U.c_3005_b.Y_259_p.u_2550_I(1.0f).u_1723_Y(serverPos);
                resolver.n_1700_B(target, currentDistance);
                pos = resolver.h_1847_R() ? serverPos.J_1907_R(0.0, (double)target.v_165_F() * 0.4, 0.0) : ((retreatPoint = resolver.k_2293_S()) != null ? retreatPoint : serverPos.J_1907_R(0.0, 5.0, 0.0));
                n_1700_B = ((String)resolver.Y_601_j().J_1907_R()).equals("\u0412\u0432\u0435\u0440\u0445") && resolver.Q_4569_t();
            } else if (overtakeEnabled) {
                float predictionTicks = 0.3f + ((Float)F_3698_k.u_2550_I.J_1907_R()).floatValue();
                e_2866_D velocity = serverDelta.G_564_y();
                e_2866_D predictedPos = serverPos.P_1922_E(velocity.n_1700_B((double)predictionTicks));
                pos = predictedPos.J_1907_R(0.0, (double)target.v_165_F() * 0.4, 0.0);
                double distToTarget = f_508_U.c_3005_b.Y_259_p.u_2550_I(1.0f).u_1723_Y(serverPos);
                n_1700_B = distToTarget < 2.0 && f_508_U.c_3005_b.Y_259_p.I_4348_c().u_1723_Y() > 1.5;
            } else {
                pos = serverPos.J_1907_R(0.0, (double)target.v_165_F() * 0.4, 0.0);
                n_1700_B = false;
            }
        } else {
            pos = this.n_1700_B(target, vector2f, f);
            n_1700_B = false;
        }
        P_1922_E = pos;
        if (f_508_U.c_3005_b.Y_259_p.k_578_l()) {
            P_3504_Q targetRotation = this.J_1907_R(pos);
            float yawDiff = u_530_F.v_4262_N(targetRotation.t_148_a - vector2f.t_148_a);
            float pitchDiff = targetRotation.s_956_w - vector2f.s_956_w;
            float rotSpeed = !antiAir && resolver != null && resolver.w_1484_f() && resolver.Q_4569_t() ? 0.8f : 1.0f;
            vector2f.t_148_a += yawDiff * rotSpeed;
            vector2f.s_956_w = u_530_F.n_1700_B(vector2f.s_956_w + pitchDiff * rotSpeed, -90.0f, 90.0f);
            P_3504_Q gcd = this.n_1700_B(vector2f, vector2f, (float)f_508_U.c_3005_b.P_4830_p.n_1700_B);
            vector2f.t_148_a = gcd.t_148_a;
            vector2f.s_956_w = u_530_F.n_1700_B(gcd.s_956_w, -90.0f, 90.0f);
        } else {
            P_3504_Q vector2f2 = this.n_1700_B(vector2f, pos);
            float f2 = u_530_F.n_1700_B(vector2f2.t_148_a, -180.0f, 180.0f);
            float f3 = u_530_F.n_1700_B(vector2f2.s_956_w, -90.0f, 90.0f);
            vector2f2.t_148_a = u_530_F.v_4262_N(1.0f, vector2f.t_148_a, vector2f.t_148_a + f2);
            vector2f2.s_956_w = u_530_F.v_4262_N(1.0f, vector2f.s_956_w, vector2f.s_956_w + f3);
            vector2f2.t_148_a = this.n_1700_B(vector2f2.t_148_a, 0.4f);
            vector2f2.s_956_w = this.n_1700_B(vector2f2.s_956_w, 0.4f);
            P_3504_Q vector2f3 = this.n_1700_B(vector2f2, vector2f, (float)f_508_U.c_3005_b.P_4830_p.n_1700_B);
            vector2f.t_148_a = vector2f3.t_148_a;
            vector2f.s_956_w = u_530_F.n_1700_B(vector2f3.s_956_w, -90.0f, 90.0f);
        }
    }

    private P_3504_Q J_1907_R(e_2866_D targetPos) {
        e_2866_D eyePos = f_508_U.c_3005_b.Y_259_p.u_2550_I(1.0f);
        double diffX = targetPos.J_1907_R - eyePos.J_1907_R;
        double diffY = targetPos.R_4764_Y - eyePos.R_4764_Y;
        double diffZ = targetPos.G_564_y - eyePos.G_564_y;
        double horizontalDist = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, horizontalDist)));
        return new P_3504_Q(u_530_F.v_4262_N(yaw), u_530_F.n_1700_B(pitch, -90.0f, 90.0f));
    }

    private void n_1700_B(List<e_2866_D> list, e_2866_D vector3d, float f) {
        e_2866_D vector3d2 = f_508_U.c_3005_b.Y_259_p.u_2550_I(1.0f);
        if (vector3d2.v_4262_N(vector3d) < (double)f) {
            list.add(vector3d);
        }
    }

    public e_2866_D n_1700_B(r_4811_B ajn2, P_3504_Q vector2f, float f) {
        ArrayList<e_2866_D> arrayList = new ArrayList<e_2866_D>();
        I_4817_s aABB = ajn2.i_601_W().shrink(0.15f);
        e_2866_D vector3d = f_508_U.c_3005_b.Y_259_p.u_2550_I(1.0f);
        int n = 20;
        int n2 = 10;
        int n3 = 10;
        double d = aABB.getYSize() / (double)n;
        double d2 = aABB.getXSize() / (double)n2;
        double d3 = aABB.getZSize() / (double)n3;
        for (int i = 0; i <= n; ++i) {
            e_2866_D vector3d2;
            e_2866_D vector3d3;
            double d4;
            int n4;
            double d5 = aABB.minY + (double)i * d;
            for (n4 = 0; n4 < n2; ++n4) {
                d4 = aABB.minX + (double)n4 * d2;
                vector3d3 = new e_2866_D(d4, d5, aABB.minZ);
                vector3d2 = new e_2866_D(d4, d5, aABB.maxZ);
                if (vector3d.v_4262_N(vector3d3) < vector3d.v_4262_N(vector3d2)) {
                    this.n_1700_B(arrayList, vector3d3, f);
                    continue;
                }
                this.n_1700_B(arrayList, vector3d2, f);
            }
            for (n4 = 0; n4 < n3; ++n4) {
                d4 = aABB.minZ + (double)n4 * d3;
                vector3d3 = new e_2866_D(aABB.maxX, d5, d4);
                vector3d2 = new e_2866_D(aABB.minX, d5, d4);
                if (vector3d.v_4262_N(vector3d3) < vector3d.v_4262_N(vector3d2)) {
                    this.n_1700_B(arrayList, vector3d3, f);
                    continue;
                }
                this.n_1700_B(arrayList, vector3d2, f);
            }
        }
        return this.n_1700_B(arrayList, vector2f, ajn2.s_4990_V());
    }

    public double n_1700_B(double d, double d2, double d3, double d4) {
        double d5 = d2 + d * (d3 - d2);
        if (Math.abs(d3 - d2) < d4) {
            d5 = d2;
        }
        return d5;
    }

    private e_2866_D n_1700_B(List<e_2866_D> list, P_3504_Q vector2f, e_2866_D vector3d2) {
        list.sort(Comparator.comparing(vector3d -> {
            P_3504_Q vector2f2 = this.n_1700_B(vector2f, (e_2866_D)vector3d);
            return Math.hypot(vector2f2.t_148_a, vector2f2.s_956_w);
        }));
        if (list.isEmpty()) {
            return vector3d2;
        }
        return list.get(0);
    }

    @Generated
    public static e_2866_D n_1700_B() {
        return G_564_y;
    }

    @Generated
    public static e_2866_D J_1907_R() {
        return P_1922_E;
    }

    @Generated
    public static boolean R_4764_Y() {
        return n_1700_B;
    }
}


