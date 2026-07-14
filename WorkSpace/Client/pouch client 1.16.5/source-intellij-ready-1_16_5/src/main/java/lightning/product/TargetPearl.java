/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.List;
import lightning.product.NumberSetting;
import lightning.product.N_3268_u;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.Z_3504_M;
import lightning.product.a_178_J;
import lightning.product.a_408_T;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.ClientBootstrap;
import lightning.product.p_1183_T;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.q_3115_L;
import lightning.product.Items;
import lightning.product.AttackAura;
import lightning.product.r_4811_B;
import lightning.product.u_1934_K;
import lightning.product.u_530_F;
import lightning.product.u_925_K;
import lightning.product.w_2989_N;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;
import lombok.Generated;

public class TargetPearl
extends Module {
    private final BooleanSetting tolkoZaTargetomEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0437\u0430 \u0442\u0430\u0440\u0433\u0435\u0442\u043e\u043c", false);
    private final NumberSetting minDistanciyaSetting = new NumberSetting("\u041c\u0438\u043d \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 10.0f, 8.0f, 32.0f, 1.0f);
    private final V_4557_X s_956_w = new V_4557_X();
    private w_2989_N u_2550_I = null;
    private e_2866_D M_588_G = null;
    private long P_4830_p = 0L;
    private long h_1847_R = 0L;
    private long Q_4569_t = 0L;
    private boolean M_182_A = false;
    public P_3504_Q v_4262_N = null;
    private N_4263_v t_1786_h = null;
    private long multiplayerClientSuggestionProvider = 0L;
    private static final long w_1457_N = 10000L;
    private static final int Y_601_j = 70;
    private static final int Y_259_p = 2000;
    private static final int Q_2552_b = 160;
    private static final double C_2741_M = 1.5;
    private static final double k_2293_S = 2.0;

    public TargetPearl() {
        super("TargetPearl", ModuleCategory.n_1700_B);
        this.addSettings(this.minDistanciyaSetting);
    }

    public boolean h_1847_R() {
        return this.w_1484_f() && this.M_182_A && this.v_4262_N != null;
    }

    @Y_1740_V
    public void n_1700_B(a_178_J e) {
        if (TargetPearl.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.Q_4569_t > 0L && System.currentTimeMillis() >= this.Q_4569_t) {
            this.M_182_A = false;
            this.v_4262_N = null;
            this.Q_4569_t = 0L;
        }
        if (this.h_1847_R()) {
            u_925_K.n_1700_B(e, this.v_4262_N.t_148_a);
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (TargetPearl.c_3005_b.Y_259_p == null || TargetPearl.c_3005_b.Y_601_j == null || TargetPearl.c_3005_b.Y_259_p.k_578_l()) {
            return;
        }
        this.c_3005_b();
    }

    private void c_3005_b() {
        if (System.currentTimeMillis() - this.h_1847_R < 2000L) {
            return;
        }
        if (!this.H_2857_Y()) {
            return;
        }
        this.A_4115_X();
        if (this.M_588_G == null) {
            return;
        }
        float[] rot = this.J_1907_R(this.M_588_G);
        if (rot == null && (rot = this.R_4764_Y(this.M_588_G)) == null) {
            return;
        }
        e_2866_D predicted = this.n_1700_B(rot[0], rot[1]);
        if (predicted != null) {
            double maxAllowedError;
            double distToTarget = this.M_588_G.u_1723_Y(predicted);
            e_2866_D eye = TargetPearl.c_3005_b.Y_259_p.u_2550_I(1.0f);
            double distToCached = eye.u_1723_Y(this.M_588_G);
            double heightDiff = this.M_588_G.R_4764_Y - eye.R_4764_Y;
            boolean isHighTarget = heightDiff > 5.0;
            double d = maxAllowedError = distToCached > 60.0 ? 3.0 : 2.25;
            if (isHighTarget) {
                maxAllowedError = 4.5;
            }
            if (this.n_1700_B(eye, this.M_588_G)) {
                double d2 = maxAllowedError = isHighTarget ? 6.0 : 3.75;
            }
            if (distToTarget > maxAllowedError && (rot = this.R_4764_Y(this.M_588_G)) == null) {
                return;
            }
        }
        this.M_182_A = true;
        this.v_4262_N = new P_3504_Q(rot[0], rot[1]);
        this.n_1700_B(rot[0], rot[1], TargetPearl.c_3005_b.Y_259_p.M_1641_O());
        if (!TargetPearl.c_3005_b.Y_259_p.p_1458_L().n_1700_B(Items.v_2746_S) && u_1934_K.n_1700_B(Items.v_2746_S) != -1) {
            if (q_3115_L.n_1700_B("funtime") || q_3115_L.n_1700_B("spooky")) {
                int hbSlot = this.n_1700_B(Items.v_2746_S, true);
                int invSlot = this.n_1700_B(Items.v_2746_S, false);
                int originalSlot = TargetPearl.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                if (hbSlot != -1) {
                    TargetPearl.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(hbSlot));
                    TargetPearl.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
                    TargetPearl.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(originalSlot));
                } else if (invSlot != -1) {
                    TargetPearl.c_3005_b.w_1457_N.pickItem(invSlot);
                    TargetPearl.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
                }
                this.n_1700_B(x_1688_C.n_1700_B);
            } else {
                this.n_1700_B(Items.v_2746_S);
            }
            this.s_956_w.n_1700_B();
            this.h_1847_R = System.currentTimeMillis();
        }
        this.M_182_A = false;
        this.v_4262_N = null;
        this.Q_4569_t = 0L;
    }

    private boolean H_2857_Y() {
        return !TargetPearl.c_3005_b.Y_259_p.p_1458_L().n_1700_B(Items.v_2746_S);
    }

    private void n_1700_B(q_1613_l item) {
        if (TargetPearl.c_3005_b.Y_259_p == null) {
            return;
        }
        int slot = u_1934_K.n_1700_B(item);
        if (slot == -1) {
            return;
        }
        int current = TargetPearl.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        if (slot < 9) {
            TargetPearl.c_3005_b.Y_259_p.l_1268_F.G_564_y = slot;
            TargetPearl.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(slot));
            TargetPearl.c_3005_b.w_1457_N.processRightClick(TargetPearl.c_3005_b.Y_259_p, TargetPearl.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
            TargetPearl.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(current));
            TargetPearl.c_3005_b.Y_259_p.l_1268_F.G_564_y = current;
        } else {
            TargetPearl.c_3005_b.w_1457_N.windowClick(TargetPearl.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, slot, current, a_408_T.R_4764_Y, TargetPearl.c_3005_b.Y_259_p);
            TargetPearl.c_3005_b.w_1457_N.processRightClick(TargetPearl.c_3005_b.Y_259_p, TargetPearl.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
            TargetPearl.c_3005_b.w_1457_N.windowClick(TargetPearl.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, slot, current, a_408_T.R_4764_Y, TargetPearl.c_3005_b.Y_259_p);
        }
    }

    private void A_4115_X() {
        long now = System.currentTimeMillis();
        if (now - this.P_4830_p < 70L) {
            return;
        }
        this.P_4830_p = now;
        this.M_588_G = null;
        this.u_2550_I = this.Y_1740_V();
        if (this.u_2550_I != null && this.u_2550_I.RealmsLongRunningMcoTaskScreen()) {
            c_1514_x pos;
            this.M_588_G = this.n_1700_B(this.u_2550_I);
            if (this.M_588_G != null && TargetPearl.c_3005_b.Y_601_j.getBlockState((pos = new c_1514_x(this.M_588_G)).down()).u_2550_I(TargetPearl.c_3005_b.Y_601_j, pos.down()).J_1907_R() && TargetPearl.c_3005_b.Y_601_j.getBlockState(pos.down(2)).u_2550_I(TargetPearl.c_3005_b.Y_601_j, pos.down(2)).J_1907_R()) {
                this.M_588_G = null;
            }
        }
    }

    private w_2989_N Y_1740_V() {
        List<N_4263_v> entities = TargetPearl.c_3005_b.Y_601_j.n_1700_B((N_4263_v)TargetPearl.c_3005_b.Y_259_p, TargetPearl.c_3005_b.Y_259_p.i_601_W().grow(130.0));
        w_2989_N best = null;
        double bestDist = Double.MAX_VALUE;
        double minDist = ((Float)this.minDistanciyaSetting.getValue()).doubleValue();
        N_4263_v targetToUse = null;
        if (this.tolkoZaTargetomEnabled.isEnabled().booleanValue()) {
            AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().J_1907_R();
            r_4811_B currentAuraTarget = aura != null ? aura.h_1847_R() : null;
            long currentTime = System.currentTimeMillis();
            if (currentAuraTarget != null && ((N_4263_v)currentAuraTarget).RealmsLongRunningMcoTaskScreen()) {
                this.t_1786_h = currentAuraTarget;
                this.multiplayerClientSuggestionProvider = currentTime;
                targetToUse = currentAuraTarget;
            } else if (this.t_1786_h != null) {
                if (!this.t_1786_h.RealmsLongRunningMcoTaskScreen() || this.t_1786_h.t_4219_U) {
                    this.t_1786_h = null;
                    this.multiplayerClientSuggestionProvider = 0L;
                } else {
                    long timeSinceRemembered = currentTime - this.multiplayerClientSuggestionProvider;
                    if (timeSinceRemembered < 10000L) {
                        targetToUse = this.t_1786_h;
                    } else {
                        this.t_1786_h = null;
                        this.multiplayerClientSuggestionProvider = 0L;
                    }
                }
            }
        }
        for (N_4263_v e : entities) {
            double dist;
            e_2866_D landing;
            if (!(e instanceof w_2989_N) || !e.RealmsLongRunningMcoTaskScreen()) continue;
            w_2989_N pearl = (w_2989_N)e;
            if (this.tolkoZaTargetomEnabled.isEnabled().booleanValue() && (targetToUse == null || pearl.Y_601_j() == null || !pearl.Y_601_j().equals(targetToUse)) || (landing = this.n_1700_B(pearl)) == null || !((dist = TargetPearl.c_3005_b.Y_259_p.s_4990_V().u_1723_Y(landing)) >= minDist) || !(dist <= 120.0) || !(dist < bestDist)) continue;
            best = pearl;
            bestDist = dist;
        }
        return best;
    }

    private e_2866_D n_1700_B(w_2989_N pearl) {
        e_2866_D pos = pearl.s_4990_V();
        e_2866_D vel = pearl.I_4348_c();
        for (int i = 0; i < 160; ++i) {
            e_2866_D next = pos.P_1922_E(vel);
            vel = vel.n_1700_B(0.99).n_1700_B(0.0, 0.03, 0.0);
            if (next.R_4764_Y <= 0.0) {
                return this.n_1700_B(next);
            }
            c_1514_x bp = new c_1514_x(next);
            if (!TargetPearl.c_3005_b.Y_601_j.getBlockState(bp).u_2550_I(TargetPearl.c_3005_b.Y_601_j, bp).J_1907_R()) {
                return this.n_1700_B(next);
            }
            pos = next;
        }
        return null;
    }

    private e_2866_D n_1700_B(e_2866_D vec) {
        return new e_2866_D((double)u_530_F.R_4764_Y(vec.J_1907_R) + 0.5, u_530_F.R_4764_Y(vec.R_4764_Y), (double)u_530_F.R_4764_Y(vec.G_564_y) + 0.5);
    }

    private boolean n_1700_B(e_2866_D start, e_2866_D end) {
        e_2866_D direction = end.G_564_y(start);
        double dist = direction.u_1723_Y();
        e_2866_D normalized = direction.G_564_y();
        int steps = (int)(dist / 0.5) + 1;
        for (int i = 1; i < steps; ++i) {
            e_2866_D checkPos = start.P_1922_E(normalized.n_1700_B((double)i * 0.5));
            c_1514_x bp = new c_1514_x(checkPos);
            if (TargetPearl.c_3005_b.Y_601_j.getBlockState(bp).u_2550_I(TargetPearl.c_3005_b.Y_601_j, bp).J_1907_R()) continue;
            return true;
        }
        return false;
    }

    private float[] J_1907_R(e_2866_D target) {
        float step;
        float minPitch;
        e_2866_D eye = TargetPearl.c_3005_b.Y_259_p.u_2550_I(1.0f);
        double dx = target.J_1907_R - eye.J_1907_R;
        double dz = target.G_564_y - eye.G_564_y;
        float yaw = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0f;
        double dist = eye.u_1723_Y(target);
        double heightDiff = target.R_4764_Y - eye.R_4764_Y;
        boolean isHighTarget = heightDiff > 5.0;
        float maxPitch = isHighTarget ? 89.0f : 85.0f;
        float f = minPitch = dist > 60.0 ? -50.0f : -30.0f;
        if (isHighTarget) {
            minPitch = -80.0f;
        }
        float f2 = step = dist > 60.0 ? 0.5f : 0.42f;
        if (isHighTarget) {
            step = 0.3f;
        }
        float bestPitch = 0.0f;
        int bestTicks = Integer.MAX_VALUE;
        double bestError = Double.MAX_VALUE;
        double maxError = isHighTarget ? 4.0 : 2.0;
        for (float pitch = maxPitch; pitch >= minPitch; pitch -= step) {
            n_1700_B res = this.n_1700_B(yaw, pitch, target);
            if (res == null || !(res.n_1700_B <= maxError) || res.J_1907_R >= bestTicks && (res.J_1907_R != bestTicks || !(res.n_1700_B < bestError))) continue;
            bestTicks = res.J_1907_R;
            bestPitch = pitch;
            bestError = res.n_1700_B;
        }
        if (bestTicks != Integer.MAX_VALUE) {
            return new float[]{yaw, u_530_F.n_1700_B(bestPitch, -90.0f, 90.0f)};
        }
        return null;
    }

    private float[] R_4764_Y(e_2866_D target) {
        double error;
        e_2866_D landing;
        float pitch;
        double baseMaxError;
        float yawStep;
        float yawRange;
        float step;
        float minPitch;
        e_2866_D eye = TargetPearl.c_3005_b.Y_259_p.u_2550_I(1.0f);
        double dx = target.J_1907_R - eye.J_1907_R;
        double dz = target.G_564_y - eye.G_564_y;
        float baseYaw = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0f;
        double dist = eye.u_1723_Y(target);
        double heightDiff = target.R_4764_Y - eye.R_4764_Y;
        boolean isHighTarget = heightDiff > 5.0;
        float maxPitch = isHighTarget ? 89.0f : 85.0f;
        float f = minPitch = dist > 60.0 ? -70.0f : -50.0f;
        if (isHighTarget) {
            minPitch = -85.0f;
        }
        float f2 = step = dist > 60.0 ? 1.2f : 1.5f;
        if (isHighTarget) {
            step = 1.0f;
        }
        float f3 = yawRange = dist > 60.0 ? 30.0f : 20.0f;
        if (isHighTarget) {
            yawRange = 40.0f;
        }
        float f4 = yawStep = dist > 60.0 ? 3.0f : 2.5f;
        if (isHighTarget) {
            yawStep = 2.0f;
        }
        double d = baseMaxError = dist > 60.0 ? 3.0 : 2.25;
        if (isHighTarget) {
            baseMaxError = 4.5;
        }
        for (pitch = maxPitch; pitch >= minPitch; pitch -= step) {
            double error2;
            e_2866_D landing2 = this.n_1700_B(baseYaw, pitch);
            if (landing2 == null || !((error2 = target.u_1723_Y(landing2)) <= baseMaxError)) continue;
            return new float[]{baseYaw, u_530_F.n_1700_B(pitch, -90.0f, 90.0f)};
        }
        for (float yawOffset = -yawRange; yawOffset <= yawRange; yawOffset += yawStep) {
            if (yawOffset == 0.0f) continue;
            float yaw = baseYaw + yawOffset;
            for (float pitch2 = maxPitch; pitch2 >= minPitch; pitch2 -= step) {
                landing = this.n_1700_B(yaw, pitch2);
                if (landing == null || !((error = target.u_1723_Y(landing)) <= baseMaxError)) continue;
                return new float[]{yaw, u_530_F.n_1700_B(pitch2, -90.0f, 90.0f)};
            }
        }
        if (isHighTarget) {
            for (pitch = 89.0f; pitch >= 75.0f; pitch -= 1.5f) {
                for (float yawOffset = -yawRange; yawOffset <= yawRange; yawOffset += yawStep) {
                    float yaw = baseYaw + yawOffset;
                    landing = this.n_1700_B(yaw, pitch);
                    if (landing == null || !((error = target.u_1723_Y(landing)) <= baseMaxError * 1.5)) continue;
                    return new float[]{yaw, u_530_F.n_1700_B(pitch, -90.0f, 90.0f)};
                }
            }
        }
        for (pitch = maxPitch; pitch >= 70.0f; pitch -= 2.0f) {
            for (float yawOffset = -yawRange; yawOffset <= yawRange; yawOffset += yawStep * 2.0f) {
                double maxError;
                float yaw = baseYaw + yawOffset;
                landing = this.n_1700_B(yaw, pitch);
                if (landing == null) continue;
                error = target.u_1723_Y(landing);
                double d2 = maxError = isHighTarget ? 6.0 : 3.75;
                if (!(error <= maxError)) continue;
                return new float[]{yaw, u_530_F.n_1700_B(pitch, -90.0f, 90.0f)};
            }
        }
        return null;
    }

    private n_1700_B n_1700_B(float yaw, float pitch, e_2866_D target) {
        e_2866_D pos = this.J_1907_R(yaw, pitch);
        e_2866_D motion = this.R_4764_Y(yaw, pitch);
        for (int tick = 0; tick < 160; ++tick) {
            pos = pos.P_1922_E(motion);
            motion = motion.n_1700_B(0.99).n_1700_B(0.0, 0.03, 0.0);
            if (pos.R_4764_Y <= 0.0) {
                return new n_1700_B(this.n_1700_B(pos).u_1723_Y(target), tick + 1);
            }
            c_1514_x bp = new c_1514_x(pos);
            if (TargetPearl.c_3005_b.Y_601_j.getBlockState(bp).u_2550_I(TargetPearl.c_3005_b.Y_601_j, bp).J_1907_R()) continue;
            return new n_1700_B(this.n_1700_B(pos).u_1723_Y(target), tick + 1);
        }
        return null;
    }

    private e_2866_D n_1700_B(float yaw, float pitch) {
        e_2866_D pos = this.J_1907_R(yaw, pitch);
        e_2866_D motion = this.R_4764_Y(yaw, pitch);
        for (int i = 0; i < 160; ++i) {
            pos = pos.P_1922_E(motion);
            motion = motion.n_1700_B(0.99).n_1700_B(0.0, 0.03, 0.0);
            if (pos.R_4764_Y <= 0.0) {
                return this.n_1700_B(pos);
            }
            c_1514_x bp = new c_1514_x(pos);
            if (TargetPearl.c_3005_b.Y_601_j.getBlockState(bp).u_2550_I(TargetPearl.c_3005_b.Y_601_j, bp).J_1907_R()) continue;
            return this.n_1700_B(pos);
        }
        return null;
    }

    private e_2866_D J_1907_R(float yaw, float pitch) {
        float yr = (float)Math.toRadians(yaw);
        double x = TargetPearl.c_3005_b.Y_259_p.O_3598_v() - (double)u_530_F.J_1907_R(yr) * 0.16;
        double y = TargetPearl.c_3005_b.Y_259_p.X_2960_b() + (double)TargetPearl.c_3005_b.Y_259_p.X_1313_W() - 0.1;
        double z = TargetPearl.c_3005_b.Y_259_p.l_2647_k() - (double)u_530_F.n_1700_B(yr) * 0.16;
        return new e_2866_D(x, y, z);
    }

    private e_2866_D R_4764_Y(float yaw, float pitch) {
        double v = 1.5;
        float yr = (float)Math.toRadians(yaw);
        float pr = (float)Math.toRadians(pitch);
        double vx = (double)(-u_530_F.n_1700_B(yr) * u_530_F.J_1907_R(pr)) * v;
        double vy = (double)(-u_530_F.n_1700_B(pr)) * v;
        double vz = (double)(u_530_F.J_1907_R(yr) * u_530_F.J_1907_R(pr)) * v;
        double playerVy = TargetPearl.c_3005_b.Y_259_p.I_4348_c().R_4764_Y;
        return new e_2866_D(vx, vy += playerVy, vz);
    }

    private void n_1700_B(float yaw, float pitch, boolean onGround) {
        if (c_3005_b.k_2293_S() != null) {
            c_3005_b.k_2293_S().n_1700_B(new N_3268_u.R_4764_Y(yaw, pitch, onGround));
        }
    }

    private int n_1700_B(q_1613_l item, boolean hotbarOnly) {
        for (int i = hotbarOnly ? 0 : 9; i < (hotbarOnly ? 9 : 36); ++i) {
            Z_1993_T stack = TargetPearl.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || stack.J_1907_R() != item) continue;
            return i;
        }
        return -1;
    }

    private void n_1700_B(x_1688_C hand) {
        if (c_3005_b.k_2293_S() != null) {
            c_3005_b.k_2293_S().n_1700_B(new Z_3504_M(hand));
            TargetPearl.c_3005_b.Y_259_p.n_1700_B(hand);
        }
    }

    @Override
    public void onDisable() {
        this.M_182_A = false;
        this.u_2550_I = null;
        this.M_588_G = null;
        this.v_4262_N = null;
        this.Q_4569_t = 0L;
        this.s_956_w.n_1700_B();
        this.t_1786_h = null;
        this.multiplayerClientSuggestionProvider = 0L;
        super.onDisable();
    }

    @Generated
    public BooleanSetting Q_4569_t() {
        return this.tolkoZaTargetomEnabled;
    }

    @Generated
    public NumberSetting M_182_A() {
        return this.minDistanciyaSetting;
    }

    @Generated
    public V_4557_X t_1786_h() {
        return this.s_956_w;
    }

    @Generated
    public w_2989_N multiplayerClientSuggestionProvider() {
        return this.u_2550_I;
    }

    @Generated
    public e_2866_D w_1457_N() {
        return this.M_588_G;
    }

    @Generated
    public long Y_601_j() {
        return this.P_4830_p;
    }

    @Generated
    public long Y_259_p() {
        return this.h_1847_R;
    }

    @Generated
    public long Q_2552_b() {
        return this.Q_4569_t;
    }

    @Generated
    public boolean C_2741_M() {
        return this.M_182_A;
    }

    @Generated
    public P_3504_Q k_2293_S() {
        return this.v_4262_N;
    }

    @Generated
    public N_4263_v q_2307_F() {
        return this.t_1786_h;
    }

    @Generated
    public long Z_875_P() {
        return this.multiplayerClientSuggestionProvider;
    }

    private static class n_1700_B {
        final double n_1700_B;
        final int J_1907_R;

        n_1700_B(double error, int ticks) {
            this.n_1700_B = error;
            this.J_1907_R = ticks;
        }
    }
}



