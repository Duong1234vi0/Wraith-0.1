/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.z_2311_U;

public final class N_4890_q {
    private final List<float[]> n_1700_B = new ArrayList<float[]>();
    private final List<float[]> J_1907_R = new ArrayList<float[]>();
    private final List<J_1907_R> R_4764_Y = new ArrayList<J_1907_R>();
    private float[] G_564_y;
    private boolean P_1922_E;

    public void n_1700_B() {
        this.n_1700_B.clear();
        this.J_1907_R.clear();
        this.R_4764_Y.clear();
        this.G_564_y = null;
        this.P_1922_E = false;
    }

    public void J_1907_R() {
        this.R_4764_Y.clear();
        this.G_564_y = null;
    }

    public List<float[]> R_4764_Y() {
        return this.n_1700_B;
    }

    public List<float[]> G_564_y() {
        return this.J_1907_R;
    }

    public int P_1922_E() {
        return this.n_1700_B.size();
    }

    public void n_1700_B(boolean down) {
        this.P_1922_E = down;
    }

    public boolean u_1723_Y() {
        return this.P_1922_E;
    }

    public void n_1700_B(boolean recording, boolean neuroMode, boolean autoProfile, r_4811_B target, long snapshotSeq, boolean attackKeyDown, boolean playerPresent) {
        if (recording && neuroMode && autoProfile && target != null && playerPresent && attackKeyDown && !this.P_1922_E) {
            this.R_4764_Y.add(new J_1907_R(snapshotSeq));
        }
        this.P_1922_E = attackKeyDown;
    }

    public void n_1700_B(boolean recording, boolean neuroMode, boolean autoProfile, long snapshotSeq) {
        if (recording && neuroMode && !autoProfile) {
            this.R_4764_Y.add(new J_1907_R(snapshotSeq));
        }
    }

    public void n_1700_B(boolean recording, z_2311_U head, long snapshotSeq, r_4811_B target, n_1700_B aimErrors) {
        if (!recording) {
            return;
        }
        if (this.G_564_y != null && head.J_1907_R() >= 2) {
            float op;
            float oy = u_530_F.v_4262_N(head.n_1700_B(0) - head.n_1700_B(1));
            if (N_4890_q.n_1700_B(oy, op = head.J_1907_R(0) - head.J_1907_R(1), target, head.n_1700_B(0), head.J_1907_R(0), aimErrors)) {
                this.n_1700_B.add(this.G_564_y);
                this.J_1907_R.add(new float[]{oy, op});
            }
            this.G_564_y = null;
        }
        if (target != null && head.J_1907_R() >= 8 && head.R_4764_Y()) {
            this.G_564_y = head.G_564_y();
        }
        Iterator<J_1907_R> it = this.R_4764_Y.iterator();
        while (it.hasNext()) {
            float op;
            J_1907_R p = it.next();
            if (!p.R_4764_Y && head.J_1907_R() >= 8 && snapshotSeq == p.n_1700_B + 3L) {
                p.J_1907_R = head.G_564_y();
                p.R_4764_Y = true;
            }
            if (!p.R_4764_Y || snapshotSeq != p.n_1700_B + 4L || head.J_1907_R() < 2) continue;
            float oy = u_530_F.v_4262_N(head.n_1700_B(0) - head.n_1700_B(1));
            if (N_4890_q.n_1700_B(oy, op = head.J_1907_R(0) - head.J_1907_R(1), target, head.n_1700_B(0), head.J_1907_R(0), aimErrors)) {
                this.n_1700_B.add(p.J_1907_R);
                this.J_1907_R.add(new float[]{oy, op});
            }
            it.remove();
        }
    }

    private static boolean n_1700_B(float oy, float op, r_4811_B target, float historyYaw0, float historyPitch0, n_1700_B aimErrors) {
        float movement = Math.abs(oy) + Math.abs(op);
        if (movement > 0.03f) {
            return true;
        }
        if (target == null || aimErrors == null) {
            return false;
        }
        return aimErrors.aimErrorSumDegrees(target, historyYaw0, historyPitch0) > 2.2f;
    }

    private static final class J_1907_R {
        final long n_1700_B;
        float[] J_1907_R;
        boolean R_4764_Y;

        J_1907_R(long attackSeq) {
            this.n_1700_B = attackSeq;
        }
    }

    public static interface n_1700_B {
        public float aimErrorSumDegrees(r_4811_B var1, float var2, float var3);
    }
}

