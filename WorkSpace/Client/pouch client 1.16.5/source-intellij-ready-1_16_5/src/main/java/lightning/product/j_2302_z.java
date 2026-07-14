/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.concurrent.ThreadLocalRandom;
import lightning.product.F_3698_k;
import lightning.product.P_3504_Q;
import lightning.product.MinecraftAccess;
import lightning.product.e_2866_D;
import lightning.product.f_800_j;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public class j_2302_z
implements MinecraftAccess {
    public static void n_1700_B(r_4811_B target, P_3504_Q rotate) {
        if (target == null || rotate == null || j_2302_z.c_3005_b.Y_259_p == null) {
            return;
        }
        e_2866_D forward = target.i_4434_b();
        e_2866_D lookDirection = target.i_4434_b().G_564_y();
        e_2866_D moveDirection = forward.v_4262_N() > 0.001 ? forward.G_564_y() : lookDirection;
        double ygoldistance = lookDirection.J_1907_R(moveDirection);
        double ygol = Math.acos(u_530_F.n_1700_B(ygoldistance, -1.0, 1.0));
        double srez = Math.min(1.0, ygol / Math.toRadians(90.0)) * 0.5;
        e_2866_D correctedDirection = moveDirection.n_1700_B(lookDirection, (float)srez).G_564_y();
        double speed = forward.u_1723_Y();
        long time = System.currentTimeMillis() / 1000L;
        e_2866_D randomVec = new e_2866_D(Math.sin((double)time * 1.8) * 0.04 + ThreadLocalRandom.current().nextDouble(-0.01, 0.01), Math.sin((double)time * 2.2) * 0.03 + ThreadLocalRandom.current().nextDouble(-0.0075, 0.0075), Math.cos((double)time * 1.8) * 0.04 + ThreadLocalRandom.current().nextDouble(-0.01, 0.01));
        int lead = ((Float)F_3698_k.u_2550_I.J_1907_R()).intValue();
        e_2866_D lastPointPredicted = target.u_2550_I(c_3005_b.RealmsClientConfig()).P_1922_E(correctedDirection.n_1700_B(speed * (double)lead));
        lastPointPredicted = lastPointPredicted.P_1922_E(correctedDirection.G_564_y().n_1700_B((double)lead));
        e_2866_D lastSyka = lastPointPredicted.G_564_y(j_2302_z.c_3005_b.Y_259_p.u_2550_I(c_3005_b.RealmsClientConfig())).P_1922_E(randomVec.n_1700_B(4.0));
        float targetYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(lastSyka.G_564_y, lastSyka.J_1907_R)) - 90.0);
        float targetPitch = (float)(-Math.toDegrees(Math.atan2(lastSyka.R_4764_Y, Math.hypot(lastSyka.J_1907_R, lastSyka.G_564_y))));
        rotate.t_148_a = f_800_j.n_1700_B(targetYaw);
        rotate.s_956_w = f_800_j.n_1700_B(u_530_F.n_1700_B(targetPitch, -90.0f, 90.0f));
    }
}



