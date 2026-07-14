/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.D_4792_h;
import lightning.product.E_3343_g;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.NumberSetting;
import lightning.product.K_1200_E;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_2049_e;
import lightning.product.a_3913_L;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.h_2739_B;
import lightning.product.h_3572_K;
import lightning.product.l_3747_P;
import lightning.product.o_2576_A;
import lightning.product.o_2840_r;
import lightning.product.o_3091_w;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;

public class KillEffect
extends Module {
    private static final g_2336_b v_4262_N = new g_2336_b("Pouch/icons/world_render/glow.png");
    private static final long w_1484_f = 2500L;
    private static final long t_148_a = 900L;
    private static final int s_956_w = 24000;
    private static final int u_2550_I = 8;
    private final NumberSetting particlesSetting = new NumberSetting("Particles", 280.0f, 80.0f, 650.0f, 10.0f);
    private final NumberSetting particleSizeSetting = new NumberSetting("Particle size", 0.115f, 0.045f, 0.22f, 0.005f);
    private final NumberSetting deathHoldSetting = new NumberSetting("Death hold", 200.0f, 0.0f, 500.0f, 10.0f);
    private final NumberSetting evaporationSetting = new NumberSetting("Evaporation", 1050.0f, 250.0f, 2400.0f, 25.0f);
    private final NumberSetting riseHeightSetting = new NumberSetting("Rise height", 1.75f, 0.35f, 4.0f, 0.05f);
    private final NumberSetting chaosSetting = new NumberSetting("Chaos", 0.75f, 0.0f, 1.75f, 0.05f);
    private final BooleanSetting throughWallsEnabled = new BooleanSetting("Through walls", false);
    private final ModeSetting colorModeMode = new ModeSetting("Color mode", "Client", "Rainbow", "Client", "Custom");
    private final BooleanSetting secondColorEnabled = new BooleanSetting("Second color", false, () -> this.colorModeMode.isMode("Custom"));
    private final h_2367_h Y_259_p = new h_2367_h("Color", true, new Color(255, 255, 255, 255).getRGB(), () -> this.colorModeMode.isMode("Custom"));
    private final h_2367_h Q_2552_b = new h_2367_h("Color 2", true, new Color(70, 70, 70, 170).getRGB(), () -> this.colorModeMode.isMode("Custom") && this.secondColorEnabled.isEnabled() != false);
    private final CopyOnWriteArrayList<P_1922_E> C_2741_M = new CopyOnWriteArrayList();
    private final List<R_4764_Y> k_2293_S = new ArrayList<R_4764_Y>();
    private final Random q_2307_F = new Random();
    private int Z_875_P = Integer.MIN_VALUE;
    private long t_4043_B;
    private e_2866_D x_607_J;
    private float e_4240_b;
    private float n_3318_d;
    private float d_2427_y;

    public KillEffect() {
        super("KillEffect", ModuleCategory.R_4764_Y);
        this.addSettings(this.particlesSetting, this.particleSizeSetting, this.throughWallsEnabled, this.deathHoldSetting, this.evaporationSetting, this.riseHeightSetting, this.chaosSetting, this.colorModeMode, this.secondColorEnabled, this.Y_259_p, this.Q_2552_b);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.Q_4569_t();
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g event) {
        if (event.J_1907_R() == E_3343_g.n_1700_B.J_1907_R) {
            this.Q_4569_t();
        }
    }

    @Y_1740_V
    public void n_1700_B(h_2739_B event) {
        if (KillEffect.c_3005_b.Y_259_p == null || KillEffect.c_3005_b.Y_601_j == null) {
            return;
        }
        N_4263_v entity = event.J_1907_R();
        if (!(entity instanceof r_4811_B) || entity == KillEffect.c_3005_b.Y_259_p) {
            return;
        }
        this.Z_875_P = entity.j_276_v();
        this.t_4043_B = System.currentTimeMillis();
        this.x_607_J = entity.s_4990_V();
        this.e_4240_b = entity.C_415_h();
        this.n_3318_d = entity.v_165_F();
        this.d_2427_y = entity.p_178_J;
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (KillEffect.c_3005_b.Y_259_p == null || KillEffect.c_3005_b.Y_601_j == null || this.Z_875_P == Integer.MIN_VALUE) {
            return;
        }
        long nowMs = System.currentTimeMillis();
        if (nowMs - this.t_4043_B > 2500L) {
            this.h_1847_R();
            return;
        }
        N_4263_v tracked = KillEffect.c_3005_b.Y_601_j.J_1907_R(this.Z_875_P);
        if (tracked == null) {
            if (this.x_607_J != null && !this.n_1700_B(this.Z_875_P, nowMs)) {
                this.k_2293_S.add(new R_4764_Y(this.Z_875_P, nowMs));
                this.n_1700_B(this.x_607_J, this.e_4240_b, this.n_3318_d, this.d_2427_y, nowMs);
            }
            this.h_1847_R();
            return;
        }
        if (!(tracked instanceof r_4811_B) || tracked == KillEffect.c_3005_b.Y_259_p) {
            this.h_1847_R();
            return;
        }
        r_4811_B living = (r_4811_B)tracked;
        if (living.g_46_E() <= 0.0f || living.O_2151_c > 0 || !tracked.RealmsLongRunningMcoTaskScreen()) {
            if (!this.n_1700_B(tracked.j_276_v(), nowMs)) {
                this.k_2293_S.add(new R_4764_Y(tracked.j_276_v(), nowMs));
                this.n_1700_B(living, nowMs);
            }
            this.h_1847_R();
        } else {
            this.x_607_J = tracked.s_4990_V();
            this.e_4240_b = tracked.C_415_h();
            this.n_3318_d = tracked.v_165_F();
            this.d_2427_y = tracked.p_178_J;
        }
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (KillEffect.c_3005_b.Y_259_p == null || KillEffect.c_3005_b.Y_601_j == null || this.C_2741_M.isEmpty()) {
            return;
        }
        long nowMs = System.currentTimeMillis();
        long holdMs = Math.max(0L, (long)Math.round(((Float)this.deathHoldSetting.getValue()).floatValue()));
        long fadeMs = Math.max(1L, (long)Math.round(((Float)this.evaporationSetting.getValue()).floatValue()));
        long maxLifeMs = holdMs + fadeMs;
        this.C_2741_M.removeIf(p -> p.n_1700_B(nowMs, maxLifeMs));
        if (this.C_2741_M.isEmpty()) {
            return;
        }
        h_3572_K camera = KillEffect.c_3005_b.s_956_w.M_588_G();
        e_2866_D camPos = camera.J_1907_R();
        c_4037_x.Y_601_j();
        c_4037_x.J_1907_R(770, 1);
        c_4037_x.q_2307_F();
        c_4037_x.J_1907_R(false);
        if (this.throughWallsEnabled.isEnabled().booleanValue()) {
            c_4037_x.t_1786_h();
        } else {
            c_4037_x.multiplayerClientSuggestionProvider();
        }
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_3005_b.G_624_v().n_1700_B(v_4262_N);
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        buffer.n_1700_B(7, E_688_b.k_2293_S);
        for (P_1922_E particle : this.C_2741_M) {
            float alpha = particle.n_1700_B(nowMs, holdMs, fadeMs);
            if (alpha <= 0.003921569f) continue;
            e_2866_D pos = particle.n_1700_B(nowMs, holdMs, fadeMs, ((Float)this.chaosSetting.getValue()).floatValue(), ((Float)this.riseHeightSetting.getValue()).floatValue());
            float size = particle.n_1700_B(nowMs, holdMs, fadeMs, ((Float)this.particleSizeSetting.getValue()).floatValue());
            int color = H_2506_c.n_1700_B(this.J_1907_R(particle.n_1700_B()), alpha);
            this.n_1700_B(buffer, pos.J_1907_R - camPos.J_1907_R, pos.R_4764_Y - camPos.R_4764_Y, pos.G_564_y - camPos.G_564_y, size * 0.5f, particle.n_1700_B(nowMs), camera, color);
        }
        buffer.u_1723_Y();
        o_2840_r.n_1700_B(buffer);
        c_4037_x.J_1907_R(true);
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.k_2293_S();
        c_4037_x.Y_259_p();
        c_4037_x.s_2632_s();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void n_1700_B(e_2866_D origin, float width, float height, float yaw, long nowMs) {
        n_1700_B model = this.n_1700_B(origin, width, height, yaw, width <= 0.7f && height > 1.4f);
        if (model.n_1700_B.isEmpty()) {
            return;
        }
        this.n_1700_B(model, nowMs);
    }

    private void n_1700_B(r_4811_B entity, long nowMs) {
        float tickDelta = c_3005_b.RealmsClientConfig();
        n_1700_B model = this.n_1700_B(entity, tickDelta);
        if (model.n_1700_B.isEmpty()) {
            model = this.n_1700_B(entity.s_4990_V(), entity.C_415_h(), entity.v_165_F(), entity.p_178_J, entity instanceof a_3913_L);
        }
        if (model.n_1700_B.isEmpty()) {
            return;
        }
        this.n_1700_B(model, nowMs);
    }

    private void n_1700_B(n_1700_B model, long nowMs) {
        int count = Math.max(1, Math.round(((Float)this.particlesSetting.getValue()).floatValue()));
        for (int i = 0; i < count; ++i) {
            e_2866_D worldPoint = model.n_1700_B(this.q_2307_F);
            e_2866_D away = worldPoint.G_564_y(model.G_564_y);
            if (away.v_4262_N() < 1.0E-5) {
                away = new e_2866_D(this.q_2307_F.nextDouble() - 0.5, this.q_2307_F.nextDouble() * 0.4, this.q_2307_F.nextDouble() - 0.5);
            }
            double chaosValue = ((Float)this.chaosSetting.getValue()).floatValue();
            e_2866_D drift = away.G_564_y().n_1700_B((0.32 + this.q_2307_F.nextDouble() * 0.58) * chaosValue).J_1907_R((this.q_2307_F.nextDouble() - 0.5) * 0.34 * chaosValue, this.q_2307_F.nextDouble() * 0.38 * chaosValue, (this.q_2307_F.nextDouble() - 0.5) * 0.34 * chaosValue);
            float sizeMul = 0.72f + this.q_2307_F.nextFloat() * 0.65f;
            this.C_2741_M.add(new P_1922_E(worldPoint, drift, nowMs, this.q_2307_F.nextInt(1440) * (this.q_2307_F.nextBoolean() ? 1 : -1), this.q_2307_F.nextFloat() * 360.0f, (this.q_2307_F.nextFloat() - 0.5f) * 210.0f, this.q_2307_F.nextFloat() * (float)Math.PI * 2.0f, sizeMul));
        }
    }

    private n_1700_B n_1700_B(r_4811_B entity, float tickDelta) {
        Z_2049_e<r_4811_B> renderer;
        if (c_3005_b.O_508_d() == null) {
            return lightning.product.KillEffect$n_1700_B.n_1700_B();
        }
        try {
            renderer = c_3005_b.O_508_d().n_1700_B(entity);
        }
        catch (Throwable ignored) {
            return lightning.product.KillEffect$n_1700_B.n_1700_B();
        }
        if (renderer == null) {
            return lightning.product.KillEffect$n_1700_B.n_1700_B();
        }
        G_564_y provider = new G_564_y(24000);
        g_221_o stack = new g_221_o();
        try {
            double x = u_530_F.G_564_y((double)tickDelta, entity.q_1982_R, entity.O_3598_v());
            double y = u_530_F.G_564_y((double)tickDelta, entity.dtoRealmsServerAddress, entity.X_2960_b());
            double z = u_530_F.G_564_y((double)tickDelta, entity.w_612_n, entity.l_2647_k());
            e_2866_D offset = renderer.n_1700_B(entity, tickDelta);
            float yaw = u_530_F.v_4262_N(tickDelta, entity.j_276_v, entity.p_178_J);
            int light = renderer.J_1907_R(entity, tickDelta);
            stack.n_1700_B();
            stack.n_1700_B(x + offset.J_1907_R, y + offset.R_4764_Y, z + offset.G_564_y);
            renderer.n_1700_B(entity, yaw, tickDelta, stack, provider, light);
            stack.J_1907_R();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return lightning.product.KillEffect$n_1700_B.n_1700_B(provider.J_1907_R());
    }

    private n_1700_B n_1700_B(e_2866_D origin, float width, float height, float yaw, boolean playerLike) {
        if (!(width > 0.0f) || !(height > 0.0f)) {
            return lightning.product.KillEffect$n_1700_B.n_1700_B();
        }
        n_1700_B model = this.J_1907_R(origin, width, height, yaw, playerLike);
        return model;
    }

    private n_1700_B J_1907_R(e_2866_D origin, float width, float height, float yaw, boolean playerLike) {
        ArrayList<e_2866_D> vertices = new ArrayList<e_2866_D>();
        if (playerLike) {
            this.n_1700_B(vertices, origin, yaw, -0.25, 1.22, -0.25, 0.25, 1.72, 0.25);
            this.n_1700_B(vertices, origin, yaw, -0.25, 0.72, -0.125, 0.25, 1.22, 0.125);
            this.n_1700_B(vertices, origin, yaw, -0.43, 0.72, -0.105, -0.25, 1.22, 0.105);
            this.n_1700_B(vertices, origin, yaw, 0.25, 0.72, -0.105, 0.43, 1.22, 0.105);
            this.n_1700_B(vertices, origin, yaw, -0.24, 0.0, -0.105, -0.02, 0.72, 0.105);
            this.n_1700_B(vertices, origin, yaw, 0.02, 0.0, -0.105, 0.24, 0.72, 0.105);
        } else {
            double half = Math.max(0.05, (double)width * 0.5);
            double h = Math.max(0.1, (double)height);
            this.J_1907_R(vertices, origin, yaw, -half, 0.0, -half, half, h, half);
        }
        return lightning.product.KillEffect$n_1700_B.n_1700_B(vertices);
    }

    private void n_1700_B(List<e_2866_D> vertices, e_2866_D origin, float yaw, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.J_1907_R(vertices, origin, yaw, minX, minY, minZ, maxX, maxY, maxZ);
    }

    private void J_1907_R(List<e_2866_D> vertices, e_2866_D origin, float yaw, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        e_2866_D nnn = this.n_1700_B(origin, yaw, minX, minY, minZ);
        e_2866_D pnn = this.n_1700_B(origin, yaw, maxX, minY, minZ);
        e_2866_D ppn = this.n_1700_B(origin, yaw, maxX, maxY, minZ);
        e_2866_D npn = this.n_1700_B(origin, yaw, minX, maxY, minZ);
        e_2866_D nnp = this.n_1700_B(origin, yaw, minX, minY, maxZ);
        e_2866_D pnp = this.n_1700_B(origin, yaw, maxX, minY, maxZ);
        e_2866_D ppp = this.n_1700_B(origin, yaw, maxX, maxY, maxZ);
        e_2866_D npp = this.n_1700_B(origin, yaw, minX, maxY, maxZ);
        vertices.add(nnn);
        vertices.add(pnn);
        vertices.add(ppn);
        vertices.add(npn);
        vertices.add(pnp);
        vertices.add(nnp);
        vertices.add(npp);
        vertices.add(ppp);
        vertices.add(nnp);
        vertices.add(nnn);
        vertices.add(npn);
        vertices.add(npp);
        vertices.add(pnn);
        vertices.add(pnp);
        vertices.add(ppp);
        vertices.add(ppn);
        vertices.add(npn);
        vertices.add(ppn);
        vertices.add(ppp);
        vertices.add(npp);
        vertices.add(nnp);
        vertices.add(pnp);
        vertices.add(pnn);
        vertices.add(nnn);
    }

    private e_2866_D n_1700_B(e_2866_D origin, float yaw, double x, double y, double z) {
        double radians = Math.toRadians(-yaw);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);
        double rx = x * cos - z * sin;
        double rz = x * sin + z * cos;
        return origin.J_1907_R(rx, y, rz);
    }

    private int J_1907_R(int seed) {
        int secondColor;
        int firstColor;
        String mode = (String)this.colorModeMode.getValue();
        if ("Rainbow".equalsIgnoreCase(mode)) {
            float hue = (float)((System.currentTimeMillis() / 12L + (long)seed) % 360L) / 360.0f;
            int rgb = Color.HSBtoRGB(hue, 1.0f, 1.0f);
            return H_2506_c.n_1700_B(rgb, 255);
        }
        if ("Client".equalsIgnoreCase(mode)) {
            int themeColor;
            firstColor = themeColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            secondColor = themeColor;
        } else {
            firstColor = (Integer)this.Y_259_p.J_1907_R();
            secondColor = this.secondColorEnabled.isEnabled() != false ? (Integer)this.Q_2552_b.J_1907_R() : (Integer)this.Y_259_p.J_1907_R();
        }
        int angle = (int)((System.currentTimeMillis() / 8L + (long)seed) % 360L);
        float progress = angle > 180 ? (float)(360 - angle) / 180.0f : (float)angle / 180.0f;
        return H_2506_c.n_1700_B(firstColor, secondColor, progress);
    }

    private void n_1700_B(D_3318_r buffer, double x, double y, double z, float halfSize, float angleDeg, h_3572_K camera, int color) {
        g_221_o ms = new g_221_o();
        ms.n_1700_B();
        ms.n_1700_B(x, y, z);
        ms.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-camera.P_1922_E()));
        ms.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(camera.G_564_y()));
        if (angleDeg != 0.0f) {
            ms.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(angleDeg));
        }
        int r = H_2506_c.n_1700_B(color);
        int g = H_2506_c.J_1907_R(color);
        int b = H_2506_c.R_4764_Y(color);
        int a = H_2506_c.G_564_y(color);
        buffer.n_1700_B(ms.R_4764_Y().n_1700_B(), -halfSize, -halfSize, 0.0f).tex(1.0f, 1.0f).color(r, g, b, a).endVertex();
        buffer.n_1700_B(ms.R_4764_Y().n_1700_B(), -halfSize, halfSize, 0.0f).tex(1.0f, 0.0f).color(r, g, b, a).endVertex();
        buffer.n_1700_B(ms.R_4764_Y().n_1700_B(), halfSize, halfSize, 0.0f).tex(0.0f, 0.0f).color(r, g, b, a).endVertex();
        buffer.n_1700_B(ms.R_4764_Y().n_1700_B(), halfSize, -halfSize, 0.0f).tex(0.0f, 1.0f).color(r, g, b, a).endVertex();
        ms.J_1907_R();
    }

    private boolean n_1700_B(int entityId, long nowMs) {
        Iterator<R_4764_Y> iterator = this.k_2293_S.iterator();
        while (iterator.hasNext()) {
            R_4764_Y stamp = iterator.next();
            if (nowMs - stamp.J_1907_R > 900L) {
                iterator.remove();
                continue;
            }
            if (stamp.n_1700_B != entityId) continue;
            return true;
        }
        return false;
    }

    private float n_1700_B(float value) {
        float t = u_530_F.n_1700_B(value, 0.0f, 1.0f);
        return t * t * (3.0f - 2.0f * t);
    }

    private float J_1907_R(float value) {
        float inv = 1.0f - u_530_F.n_1700_B(value, 0.0f, 1.0f);
        return 1.0f - inv * inv * inv;
    }

    private void h_1847_R() {
        this.Z_875_P = Integer.MIN_VALUE;
        this.t_4043_B = 0L;
        this.x_607_J = null;
        this.e_4240_b = 0.0f;
        this.n_3318_d = 0.0f;
        this.d_2427_y = 0.0f;
    }

    private void Q_4569_t() {
        this.C_2741_M.clear();
        this.k_2293_S.clear();
        this.h_1847_R();
    }

    private static double n_1700_B(e_2866_D a, e_2866_D b, e_2866_D c) {
        e_2866_D ab = b.G_564_y(a);
        e_2866_D ac = c.G_564_y(a);
        double crossX = ab.R_4764_Y * ac.G_564_y - ab.G_564_y * ac.R_4764_Y;
        double crossY = ab.G_564_y * ac.J_1907_R - ab.J_1907_R * ac.G_564_y;
        double crossZ = ab.J_1907_R * ac.R_4764_Y - ab.R_4764_Y * ac.J_1907_R;
        return Math.sqrt(crossX * crossX + crossY * crossY + crossZ * crossZ) * 0.5;
    }

    private static e_2866_D n_1700_B(e_2866_D a, e_2866_D b, e_2866_D c, Random random) {
        double v;
        double u = random.nextDouble();
        if (u + (v = random.nextDouble()) > 1.0) {
            u = 1.0 - u;
            v = 1.0 - v;
        }
        return a.P_1922_E(b.G_564_y(a).n_1700_B(u)).P_1922_E(c.G_564_y(a).n_1700_B(v));
    }

    private static boolean n_1700_B(double value) {
        return Double.isFinite(value) && Math.abs(value) < 3.0E7;
    }

    private static final class R_4764_Y {
        private final int n_1700_B;
        private final long J_1907_R;

        private R_4764_Y(int entityId, long timeMs) {
            this.n_1700_B = entityId;
            this.J_1907_R = timeMs;
        }
    }

    private final class P_1922_E {
        private final e_2866_D J_1907_R;
        private final e_2866_D R_4764_Y;
        private final long G_564_y;
        private final int P_1922_E;
        private final float u_1723_Y;
        private final float v_4262_N;
        private final float w_1484_f;
        private final float t_148_a;

        private P_1922_E(e_2866_D anchor, e_2866_D drift, long bornMs, int colorSeed, float startAngle, float spin, float phase, float sizeMul) {
            this.J_1907_R = anchor;
            this.R_4764_Y = drift;
            this.G_564_y = bornMs;
            this.P_1922_E = colorSeed;
            this.u_1723_Y = startAngle;
            this.v_4262_N = spin;
            this.w_1484_f = phase;
            this.t_148_a = sizeMul;
        }

        private boolean n_1700_B(long nowMs, long maxLifeMs) {
            return nowMs - this.G_564_y >= maxLifeMs;
        }

        private e_2866_D n_1700_B(long nowMs, long holdMs, long fadeMs, float chaosValue, float riseValue) {
            float evaporation = this.J_1907_R(nowMs, holdMs, fadeMs);
            if (evaporation <= 0.0f) {
                return this.J_1907_R;
            }
            float eased = KillEffect.this.J_1907_R(evaporation);
            double wave = Math.sin((double)(nowMs - this.G_564_y) * 0.012 + (double)this.w_1484_f) * 0.075 * (double)chaosValue * (double)evaporation;
            double sideWave = Math.cos((double)(nowMs - this.G_564_y) * 0.009 + (double)this.w_1484_f * 1.37) * 0.055 * (double)chaosValue * (double)evaporation;
            return this.J_1907_R.P_1922_E(this.R_4764_Y.n_1700_B((double)eased)).J_1907_R(wave, (double)(riseValue * eased) * (0.75 + (double)this.t_148_a * 0.35), sideWave);
        }

        private float n_1700_B(long nowMs, long holdMs, long fadeMs) {
            long ageMs = nowMs - this.G_564_y;
            float fadeIn = u_530_F.n_1700_B((float)ageMs / 80.0f, 0.0f, 1.0f);
            float evaporation = this.J_1907_R(nowMs, holdMs, fadeMs);
            if (evaporation <= 0.0f) {
                return fadeIn;
            }
            return fadeIn * (1.0f - KillEffect.this.n_1700_B(evaporation));
        }

        private float n_1700_B(long nowMs, long holdMs, long fadeMs, float baseSize) {
            float evaporation = this.J_1907_R(nowMs, holdMs, fadeMs);
            float pulse = 1.0f + u_530_F.n_1700_B((float)(nowMs - this.G_564_y) * 0.018f + this.w_1484_f) * 0.08f;
            float dissolveScale = 1.0f - KillEffect.this.n_1700_B(evaporation) * 0.45f;
            return baseSize * this.t_148_a * pulse * dissolveScale;
        }

        private float n_1700_B(long nowMs) {
            return this.u_1723_Y + (float)(nowMs - this.G_564_y) / 1000.0f * this.v_4262_N;
        }

        private float J_1907_R(long nowMs, long holdMs, long fadeMs) {
            return u_530_F.n_1700_B((float)(nowMs - this.G_564_y - holdMs) / (float)fadeMs, 0.0f, 1.0f);
        }

        private int n_1700_B() {
            return this.P_1922_E;
        }
    }

    private static final class n_1700_B {
        private final List<e_2866_D> n_1700_B;
        private final List<u_1723_Y> J_1907_R;
        private final double R_4764_Y;
        private final e_2866_D G_564_y;

        private n_1700_B(List<e_2866_D> vertices, List<u_1723_Y> surfaces, double totalArea, e_2866_D center) {
            this.n_1700_B = vertices;
            this.J_1907_R = surfaces;
            this.R_4764_Y = totalArea;
            this.G_564_y = center;
        }

        private static n_1700_B n_1700_B() {
            return new n_1700_B(new ArrayList<e_2866_D>(), new ArrayList<u_1723_Y>(), 0.0, e_2866_D.n_1700_B);
        }

        private static n_1700_B n_1700_B(List<e_2866_D> capturedVertices) {
            if (capturedVertices == null || capturedVertices.size() < 8) {
                return lightning.product.KillEffect$n_1700_B.n_1700_B();
            }
            ArrayList<e_2866_D> vertices = new ArrayList<e_2866_D>(capturedVertices);
            e_2866_D min = (e_2866_D)vertices.get(0);
            e_2866_D max = (e_2866_D)vertices.get(0);
            for (e_2866_D v : vertices) {
                min = new e_2866_D(Math.min(min.J_1907_R, v.J_1907_R), Math.min(min.R_4764_Y, v.R_4764_Y), Math.min(min.G_564_y, v.G_564_y));
                max = new e_2866_D(Math.max(max.J_1907_R, v.J_1907_R), Math.max(max.R_4764_Y, v.R_4764_Y), Math.max(max.G_564_y, v.G_564_y));
            }
            e_2866_D center = new e_2866_D((min.J_1907_R + max.J_1907_R) * 0.5, (min.R_4764_Y + max.R_4764_Y) * 0.5, (min.G_564_y + max.G_564_y) * 0.5);
            ArrayList<u_1723_Y> surfaces = new ArrayList<u_1723_Y>();
            double totalArea = 0.0;
            int i = 0;
            while (i + 3 < vertices.size()) {
                u_1723_Y surface = new u_1723_Y((e_2866_D)vertices.get(i), (e_2866_D)vertices.get(i + 1), (e_2866_D)vertices.get(i + 2), (e_2866_D)vertices.get(i + 3));
                if (!(surface.u_1723_Y <= 1.0E-7)) {
                    surfaces.add(surface);
                    totalArea += surface.u_1723_Y;
                }
                i += 4;
            }
            return new n_1700_B(vertices, surfaces, totalArea, center);
        }

        private e_2866_D n_1700_B(Random random) {
            if (!this.J_1907_R.isEmpty() && this.R_4764_Y > 1.0E-7) {
                double cursor = random.nextDouble() * this.R_4764_Y;
                for (u_1723_Y surface : this.J_1907_R) {
                    if (!((cursor -= surface.u_1723_Y) <= 0.0)) continue;
                    return surface.n_1700_B(random);
                }
                return this.J_1907_R.get(this.J_1907_R.size() - 1).n_1700_B(random);
            }
            e_2866_D vertex = this.n_1700_B.get(random.nextInt(this.n_1700_B.size()));
            return vertex.J_1907_R((random.nextDouble() - 0.5) * 0.025, (random.nextDouble() - 0.5) * 0.025, (random.nextDouble() - 0.5) * 0.025);
        }
    }

    private static final class G_564_y
    implements o_3091_w {
        private final J_1907_R n_1700_B;

        private G_564_y(int maxVertices) {
            this.n_1700_B = new J_1907_R(maxVertices);
        }

        @Override
        public D_4792_h getBuffer(o_2576_A renderType) {
            return this.n_1700_B;
        }

        private List<e_2866_D> J_1907_R() {
            return this.n_1700_B.J_1907_R();
        }
    }

    private static final class J_1907_R
    implements D_4792_h {
        private final List<e_2866_D> n_1700_B = new ArrayList<e_2866_D>();
        private final int J_1907_R;
        private double R_4764_Y;
        private double G_564_y;
        private double P_1922_E;

        private J_1907_R(int maxVertices) {
            this.J_1907_R = maxVertices;
        }

        @Override
        public D_4792_h pos(double x, double y, double z) {
            this.R_4764_Y = x;
            this.G_564_y = y;
            this.P_1922_E = z;
            return this;
        }

        @Override
        public D_4792_h color(int red, int green, int blue, int alpha) {
            return this;
        }

        @Override
        public D_4792_h tex(float u, float v) {
            return this;
        }

        @Override
        public D_4792_h overlay(int u, int v) {
            return this;
        }

        @Override
        public D_4792_h lightmap(int u, int v) {
            return this;
        }

        @Override
        public D_4792_h normal(float x, float y, float z) {
            return this;
        }

        @Override
        public void endVertex() {
            this.n_1700_B(this.R_4764_Y, this.G_564_y, this.P_1922_E);
        }

        @Override
        public D_4792_h n_1700_B(D_1098_v matrixIn, float x, float y, float z) {
            float tx = matrixIn.J_1907_R(x, y, z, 1.0f);
            float ty = matrixIn.R_4764_Y(x, y, z, 1.0f);
            float tz = matrixIn.G_564_y(x, y, z, 1.0f);
            return this.pos(tx, ty, tz);
        }

        private void n_1700_B(double x, double y, double z) {
            if (!(this.n_1700_B.size() < this.J_1907_R && KillEffect.n_1700_B(x) && KillEffect.n_1700_B(y) && KillEffect.n_1700_B(z))) {
                return;
            }
            this.n_1700_B.add(new e_2866_D(x, y, z));
        }

        private List<e_2866_D> J_1907_R() {
            return this.n_1700_B;
        }
    }

    private static final class u_1723_Y {
        private final e_2866_D n_1700_B;
        private final e_2866_D J_1907_R;
        private final e_2866_D R_4764_Y;
        private final e_2866_D G_564_y;
        private final double P_1922_E;
        private final double u_1723_Y;

        private u_1723_Y(e_2866_D a, e_2866_D b, e_2866_D c, e_2866_D d) {
            this.n_1700_B = a;
            this.J_1907_R = b;
            this.R_4764_Y = c;
            this.G_564_y = d;
            this.P_1922_E = KillEffect.n_1700_B(a, b, c);
            this.u_1723_Y = this.P_1922_E + KillEffect.n_1700_B(a, c, d);
        }

        private e_2866_D n_1700_B(Random random) {
            if (random.nextDouble() * this.u_1723_Y <= this.P_1922_E) {
                return KillEffect.n_1700_B(this.n_1700_B, this.J_1907_R, this.R_4764_Y, random);
            }
            return KillEffect.n_1700_B(this.n_1700_B, this.R_4764_Y, this.G_564_y, random);
        }
    }
}



