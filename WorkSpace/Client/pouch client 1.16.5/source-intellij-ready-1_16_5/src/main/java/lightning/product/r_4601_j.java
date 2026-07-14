/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_1446_q;
import lightning.product.P_2272_O;
import lightning.product.MinecraftAccess;
import lightning.product.e_2866_D;
import lightning.product.ClientBootstrap;
import lightning.product.r_4790_y;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public class r_4601_j {
    private P_2272_O n_1700_B;
    private String J_1907_R = null;
    private r_4811_B R_4764_Y;
    private float G_564_y;
    private float P_1922_E;
    private float u_1723_Y;
    private float v_4262_N;
    private float w_1484_f = 1.0f;
    private float t_148_a = Float.MAX_VALUE;
    private int s_956_w = 0;

    private P_2272_O P_1922_E() {
        if (this.n_1700_B == null) {
            this.n_1700_B = ClientBootstrap.Y_601_j().P_4830_p();
        }
        return this.n_1700_B;
    }

    public void n_1700_B() {
        this.u_1723_Y();
        if (MinecraftAccess.c_3005_b.Y_259_p != null) {
            this.G_564_y = MinecraftAccess.c_3005_b.Y_259_p.p_178_J;
            this.P_1922_E = MinecraftAccess.c_3005_b.Y_259_p.f_4016_n;
        }
    }

    private void u_1723_Y() {
        P_2272_O mgr;
        this.u_1723_Y = 0.0f;
        this.v_4262_N = 0.0f;
        this.w_1484_f = 1.0f;
        this.t_148_a = Float.MAX_VALUE;
        this.s_956_w = 0;
        this.R_4764_Y = null;
        if (this.J_1907_R != null && (mgr = this.P_1922_E()) != null) {
            mgr.P_1922_E(this.J_1907_R);
        }
    }

    public void n_1700_B(r_4811_B target) {
        P_2272_O mgr = this.P_1922_E();
        if (mgr == null) {
            return;
        }
        if (target != this.R_4764_Y) {
            this.R_4764_Y = target;
            this.u_1723_Y = 0.0f;
            this.v_4262_N = 0.0f;
            this.w_1484_f = 1.0f;
            this.t_148_a = Float.MAX_VALUE;
            this.s_956_w = 0;
            this.G_564_y = MinecraftAccess.c_3005_b.Y_259_p.p_178_J;
            this.P_1922_E = MinecraftAccess.c_3005_b.Y_259_p.f_4016_n;
            if (this.J_1907_R != null) {
                mgr.P_1922_E(this.J_1907_R);
            }
        }
        if (mgr.G_564_y()) {
            this.R_4764_Y(target);
            return;
        }
        if (this.J_1907_R == null || !mgr.u_1723_Y(this.J_1907_R)) {
            return;
        }
        this.G_564_y(target);
    }

    private float[] J_1907_R(r_4811_B target) {
        e_2866_D eye = MinecraftAccess.c_3005_b.Y_259_p.u_2550_I(MinecraftAccess.c_3005_b.RealmsClientConfig());
        e_2866_D center = target.s_4990_V().J_1907_R(0.0, (double)target.v_165_F() * 0.5, 0.0);
        e_2866_D d = center.G_564_y(eye);
        float yaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(d.G_564_y, d.J_1907_R)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(d.R_4764_Y, Math.hypot(d.J_1907_R, d.G_564_y))));
        return new float[]{yaw, pitch};
    }

    private void R_4764_Y(r_4811_B target) {
        float yaw = MinecraftAccess.c_3005_b.Y_259_p.p_178_J;
        float pitch = MinecraftAccess.c_3005_b.Y_259_p.f_4016_n;
        float deltaYaw = u_530_F.v_4262_N(yaw - this.G_564_y);
        float deltaPitch = pitch - this.P_1922_E;
        float[] base = this.J_1907_R(target);
        float offsetYaw = u_530_F.v_4262_N(yaw - base[0]);
        float offsetPitch = pitch - base[1];
        float[] inputs = this.n_1700_B(target, base, yaw, pitch);
        float[] outputs = new float[]{offsetYaw / 60.0f, offsetPitch / 30.0f};
        this.P_1922_E().n_1700_B(inputs, outputs);
        this.u_1723_Y = deltaYaw;
        this.v_4262_N = deltaPitch;
        this.G_564_y = yaw;
        this.P_1922_E = pitch;
    }

    private void G_564_y(r_4811_B target) {
        float angDist;
        float[] base = this.J_1907_R(target);
        float[] inputs = this.n_1700_B(target, base, this.G_564_y, this.P_1922_E);
        float[] output = this.P_1922_E().n_1700_B(this.J_1907_R, inputs);
        if (output == null) {
            return;
        }
        float offsetYaw = output[0] * 60.0f;
        float offsetPitch = output[1] * 30.0f;
        if (Float.isNaN(offsetYaw) || Float.isInfinite(offsetYaw)) {
            offsetYaw = 0.0f;
        }
        if (Float.isNaN(offsetPitch) || Float.isInfinite(offsetPitch)) {
            offsetPitch = 0.0f;
        }
        if ((angDist = (float)Math.hypot(u_530_F.v_4262_N(base[0] - this.G_564_y), base[1] - this.P_1922_E)) > 5.0f && angDist >= this.t_148_a - 0.3f) {
            ++this.s_956_w;
            if (this.s_956_w > 5) {
                this.w_1484_f = Math.max(this.w_1484_f - 0.05f, 0.3f);
            }
        } else {
            this.s_956_w = Math.max(this.s_956_w - 1, 0);
            this.w_1484_f = Math.min(this.w_1484_f + 0.05f, 1.0f);
        }
        this.t_148_a = angDist;
        float newYaw = base[0] + (offsetYaw *= this.w_1484_f);
        float newPitch = u_530_F.n_1700_B(base[1] + (offsetPitch *= this.w_1484_f), -89.0f, 89.0f);
        this.u_1723_Y = u_530_F.v_4262_N(newYaw - this.G_564_y);
        this.v_4262_N = newPitch - this.P_1922_E;
        this.G_564_y = newYaw;
        this.P_1922_E = newPitch;
        r_4790_y.n_1700_B(new F_1446_q(newYaw, newPitch), 180.0f, 180.0f, 0, 6);
    }

    private float[] n_1700_B(r_4811_B target, float[] base, float curYaw, float curPitch) {
        e_2866_D eye = MinecraftAccess.c_3005_b.Y_259_p.u_2550_I(MinecraftAccess.c_3005_b.RealmsClientConfig());
        e_2866_D head = target.s_4990_V().J_1907_R(0.0, (double)target.v_165_F() - 0.1, 0.0);
        e_2866_D feet = target.s_4990_V().J_1907_R(0.0, 0.1, 0.0);
        e_2866_D toHead = head.G_564_y(eye);
        float headPitch = (float)(-Math.toDegrees(Math.atan2(toHead.R_4764_Y, Math.hypot(toHead.J_1907_R, toHead.G_564_y))));
        e_2866_D toFeet = feet.G_564_y(eye);
        float feetPitch = (float)(-Math.toDegrees(Math.atan2(toFeet.R_4764_Y, Math.hypot(toFeet.J_1907_R, toFeet.G_564_y))));
        float dist = MinecraftAccess.c_3005_b.Y_259_p.R_4764_Y(target);
        float curOffsetYaw = u_530_F.v_4262_N(curYaw - base[0]);
        float curOffsetPitch = curPitch - base[1];
        float angDist = (float)Math.hypot(curOffsetYaw, curOffsetPitch);
        return new float[]{(headPitch - base[1]) / 90.0f, (feetPitch - base[1]) / 90.0f, Math.min(dist / 6.0f, 1.0f), this.u_1723_Y / 30.0f, this.v_4262_N / 15.0f, Math.min(angDist / 180.0f, 1.0f), curOffsetYaw / 60.0f, curOffsetPitch / 30.0f};
    }

    public void n_1700_B(String name) {
        this.J_1907_R = name;
        this.u_1723_Y();
    }

    public void J_1907_R() {
        this.J_1907_R = null;
        this.u_1723_Y();
    }

    public String R_4764_Y() {
        return this.J_1907_R;
    }

    public boolean G_564_y() {
        return this.J_1907_R != null;
    }
}



