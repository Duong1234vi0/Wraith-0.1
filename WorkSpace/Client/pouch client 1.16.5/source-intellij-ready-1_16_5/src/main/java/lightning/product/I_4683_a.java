/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_4817_s;
import lightning.product.e_2866_D;
import lightning.product.u_530_F;

public final class I_4683_a {
    private I_4683_a() {
    }

    public static float n_1700_B(e_2866_D eye, e_2866_D world) {
        e_2866_D d = world.G_564_y(eye);
        double xz = Math.sqrt(d.J_1907_R * d.J_1907_R + d.G_564_y * d.G_564_y);
        return (float)(-Math.toDegrees(Math.atan2(d.R_4764_Y, xz)));
    }

    public static e_2866_D n_1700_B(I_4817_s box, e_2866_D playerEye, float lookPitch) {
        double aimY;
        double minY = box.minY;
        double maxY = box.maxY;
        double minX = box.minX;
        double maxX = box.maxX;
        double minZ = box.minZ;
        double maxZ = box.maxZ;
        double cx = (minX + maxX) * 0.5;
        double cz = (minZ + maxZ) * 0.5;
        double h = maxY - minY;
        if (h < 1.0E-4) {
            return new e_2866_D(cx, minY, cz);
        }
        double pad = Math.max(h * 0.06, 0.06);
        e_2866_D head = new e_2866_D(cx, maxY - pad, cz);
        e_2866_D feet = new e_2866_D(cx, minY + pad, cz);
        float pHead = I_4683_a.n_1700_B(playerEye, head);
        float pFeet = I_4683_a.n_1700_B(playerEye, feet);
        if (Math.abs(pFeet - pHead) < 0.4f) {
            aimY = minY + h * 0.52;
        } else {
            float t = (lookPitch - pHead) / (pFeet - pHead);
            t = u_530_F.n_1700_B(t, 0.0f, 1.0f);
            aimY = maxY - pad + (double)t * (minY + pad - (maxY - pad));
        }
        return new e_2866_D(cx, aimY, cz);
    }

    public static e_2866_D n_1700_B(e_2866_D aimPoint, e_2866_D playerEye, I_4817_s targetBox, float targetHeight, float currentYawDegrees) {
        e_2866_D diff = aimPoint.G_564_y(playerEye);
        double hxz = Math.hypot(diff.J_1907_R, diff.G_564_y);
        double minH = 0.22;
        if (hxz >= 0.22) {
            return diff;
        }
        if (targetBox != null) {
            double cx = (targetBox.minX + targetBox.maxX) * 0.5;
            double cy = targetBox.minY + (double)targetHeight * 0.52;
            double cz = (targetBox.minZ + targetBox.maxZ) * 0.5;
            e_2866_D toCore = new e_2866_D(cx, cy, cz).G_564_y(playerEye);
            double txz = Math.hypot(toCore.J_1907_R, toCore.G_564_y);
            if (txz >= 0.22) {
                return toCore;
            }
        }
        double yRad = Math.toRadians(currentYawDegrees);
        double vx = -Math.sin(yRad) * 0.22;
        double vz = Math.cos(yRad) * 0.22;
        return new e_2866_D(vx, diff.R_4764_Y, vz);
    }

    public static float n_1700_B(e_2866_D diff) {
        return (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(diff.G_564_y, diff.J_1907_R)) - 90.0);
    }

    public static float J_1907_R(e_2866_D diff) {
        return (float)(-Math.toDegrees(Math.atan2(diff.R_4764_Y, Math.sqrt(diff.J_1907_R * diff.J_1907_R + diff.G_564_y * diff.G_564_y))));
    }
}

