/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.MinecraftAccess;
import lightning.product.MinecraftClient;
import lightning.product.e_2866_D;
import lightning.product.j_2129_E;
import lightning.product.u_530_F;

public class F_747_P
implements MinecraftAccess {
    public static double n_1700_B(double start, double end, double factor) {
        return start + (end - start) * factor;
    }

    public static float n_1700_B(float start, float end, double factor) {
        return (float)((double)start + (double)(end - start) * factor);
    }

    public static int n_1700_B(int start, int end, double factor) {
        return (int)((double)start + (double)(end - start) * factor);
    }

    public static float n_1700_B(float x, float sigma) {
        double PI = Math.PI;
        double output = 1.0 / Math.sqrt(2.0 * PI * (double)(sigma * sigma));
        return (float)(output * Math.exp((double)(-(x * x)) / (2.0 * (double)(sigma * sigma))));
    }

    public static float J_1907_R(float min, float max) {
        return F_747_P.G_564_y(min, max);
    }

    public static e_2866_D n_1700_B(e_2866_D end, e_2866_D start, float multiple) {
        return new e_2866_D(F_747_P.n_1700_B(end.n_1700_B(), start.n_1700_B(), (double)multiple), F_747_P.n_1700_B(end.J_1907_R(), start.J_1907_R(), (double)multiple), F_747_P.n_1700_B(end.R_4764_Y(), start.R_4764_Y(), (double)multiple));
    }

    public static float n_1700_B(float start, float end, float amount) {
        float a = u_530_F.n_1700_B(amount, 0.0f, 1.0f);
        float d = u_530_F.v_4262_N(end - start);
        if (Math.abs(d) < 0.5f) {
            return end;
        }
        return u_530_F.v_4262_N(start + d * a);
    }

    public static float J_1907_R(float current, float old, float scale) {
        return old + (current - old) * scale;
    }

    public static float R_4764_Y(float end, float start, float multiple) {
        return (float)((double)end + (double)(start - end) * u_530_F.n_1700_B(F_747_P.n_1700_B() * (double)multiple, 0.0, 1.0));
    }

    public static double J_1907_R(double end, double start, double multiple) {
        return end + (start - end) * u_530_F.n_1700_B(F_747_P.n_1700_B() * multiple, 0.0, 1.0);
    }

    public static boolean n_1700_B(float mouseX, float mouseY, float x, float y, float width, float height) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    public static P_3504_Q n_1700_B(N_4263_v target) {
        e_2866_D vector3d = target.s_4990_V().G_564_y(MinecraftClient.A_4115_X().Y_259_p.s_4990_V());
        double magnitude = Math.hypot(vector3d.J_1907_R, vector3d.G_564_y);
        return new P_3504_Q((float)Math.toDegrees(Math.atan2(vector3d.G_564_y, vector3d.J_1907_R)) - 90.0f, (float)(-Math.toDegrees(Math.atan2(vector3d.R_4764_Y, magnitude))));
    }

    public static double n_1700_B(double num, double increment) {
        double v = (double)Math.round(num / increment) * increment;
        BigDecimal bd = new BigDecimal(v);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static float R_4764_Y(float num, float increment) {
        float v = (float)Math.round(num / increment) * increment;
        BigDecimal bd = BigDecimal.valueOf(v);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    public static float G_564_y(float end, float start, float multiple) {
        return (1.0f - u_530_F.n_1700_B((float)(F_747_P.n_1700_B() * (double)multiple), 0.0f, 1.0f)) * end + u_530_F.n_1700_B((float)(F_747_P.n_1700_B() * (double)multiple), 0.0f, 1.0f) * start;
    }

    public static e_2866_D J_1907_R(e_2866_D end, e_2866_D start, float multiple) {
        return new e_2866_D(F_747_P.G_564_y((float)end.n_1700_B(), (float)start.n_1700_B(), multiple), F_747_P.G_564_y((float)end.J_1907_R(), (float)start.J_1907_R(), multiple), F_747_P.G_564_y((float)end.R_4764_Y(), (float)start.R_4764_Y(), multiple));
    }

    public static double J_1907_R(double value, double steps) {
        double roundedValue = (double)Math.round(value / steps) * steps;
        return (double)Math.round(roundedValue * 100.0) / 100.0;
    }

    public static float G_564_y(float min, float max) {
        return (float)(Math.random() * (double)(max - min) + (double)min);
    }

    public static double n_1700_B() {
        return j_2129_E.J_1907_R() > 5 ? (double)(1.0f / (float)j_2129_E.J_1907_R()) : (double)0.016f;
    }

    public static e_2866_D n_1700_B(N_4263_v entity, float partialTicks) {
        double posX = e_2866_D.P_1922_E(entity.q_1982_R, entity.O_3598_v(), partialTicks);
        double posY = e_2866_D.P_1922_E(entity.dtoRealmsServerAddress, entity.X_2960_b(), partialTicks);
        double posZ = e_2866_D.P_1922_E(entity.w_612_n, entity.l_2647_k(), partialTicks);
        return new e_2866_D(posX, posY, posZ);
    }

    public static double n_1700_B(N_4263_v entity, int decimal) {
        double x = entity.O_3598_v() - entity.r_715_M;
        double y = entity.X_2960_b() - entity.A_1038_p;
        double z = entity.l_2647_k() - entity.i_1637_u;
        double speed = Math.sqrt(x * x + y * y + z * z) * 20.0;
        return F_747_P.R_4764_Y(speed, (double)decimal);
    }

    public static double R_4764_Y(double num, double increment) {
        double v = (double)Math.round(num / increment) * increment;
        BigDecimal bd = new BigDecimal(v);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double J_1907_R(N_4263_v entity) {
        double dx = entity.O_3598_v() - entity.r_715_M;
        double dy = entity.X_2960_b() - entity.A_1038_p;
        double dz = entity.l_2647_k() - entity.i_1637_u;
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        return distance * 20.0;
    }

    public static double R_4764_Y(N_4263_v target) {
        double baseSpeed = 1.5;
        if (target == null) {
            return 1.5;
        }
        double targetBps = F_747_P.J_1907_R(target);
        double speedFactor = 0.00342;
        double bonusSpeed = targetBps * 0.00342;
        return 1.5 + bonusSpeed;
    }
}



