/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lightning.product.E_3343_g;
import lightning.product.E_4612_l;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.NumberSetting;
import lightning.product.K_1200_E;
import lightning.product.N_4263_v;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.m_2262_U;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;
import lightning.product.r_4811_B;
import lightning.product.t_1920_R;
import lightning.product.u_530_F;
import lightning.product.v_2826_q;
import lightning.product.ModuleCategory;
import org.lwjgl.opengl.GL11;

public class Trails
extends Module {
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "Old", "Old", "New");
    private final MultiBooleanSetting otobrazhatOptions = new MultiBooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c", new BooleanSetting("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", false), new BooleanSetting("\u0414\u0440\u0443\u0437\u0435\u0439", true), new BooleanSetting("\u0421\u0435\u0431\u044f", true), new BooleanSetting("\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u044b\u0445", false));
    private final NumberSetting dlinaSetting = new NumberSetting("\u0414\u043b\u0438\u043d\u0430", 2.5f, 2.0f, 4.0f, 0.5f, () -> ((String)this.rezhimMode.getValue()).equals("Old"));
    private final NumberSetting razmerChasticSetting = new NumberSetting("\u0420\u0430\u0437\u043c\u0435\u0440 \u0447\u0430\u0441\u0442\u0438\u0446", 0.15f, 0.05f, 0.5f, 0.01f, () -> ((String)this.rezhimMode.getValue()).equals("New"));
    private final NumberSetting razmerSvecheniyaSetting = new NumberSetting("\u0420\u0430\u0437\u043c\u0435\u0440 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f", 0.25f, 0.1f, 1.0f, 0.05f, () -> ((String)this.rezhimMode.getValue()).equals("New"));
    private final NumberSetting vremyaZhizniSetting = new NumberSetting("\u0412\u0440\u0435\u043c\u044f \u0436\u0438\u0437\u043d\u0438", 1500.0f, 500.0f, 5000.0f, 100.0f, () -> ((String)this.rezhimMode.getValue()).equals("New"));
    private final Map<Integer, List<G_564_y>> P_4830_p = new HashMap<Integer, List<G_564_y>>();
    private final List<J_1907_R> h_1847_R = new ArrayList<J_1907_R>();
    private final g_2336_b Q_4569_t = new g_2336_b("Pouch/hats/firefly.png");
    private static final int M_182_A = 500;

    public Trails() {
        super("Trails", ModuleCategory.R_4764_Y);
        this.addSettings(this.rezhimMode, this.otobrazhatOptions, this.dlinaSetting, this.razmerChasticSetting, this.razmerSvecheniyaSetting, this.vremyaZhizniSetting);
    }

    @Override
    public void onEnable() {
        this.P_4830_p.clear();
        this.h_1847_R.clear();
        super.onEnable();
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U event) {
        if (Trails.c_3005_b.Y_259_p == null || !((String)this.rezhimMode.getValue()).equals("New")) {
            return;
        }
        if (!this.otobrazhatOptions.isOptionEnabled("\u0421\u0435\u0431\u044f").booleanValue()) {
            return;
        }
        if (this.n_1700_B(Trails.c_3005_b.Y_259_p)) {
            this.J_1907_R(Trails.c_3005_b.Y_259_p);
        }
        this.h_1847_R();
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R e) {
        if (Trails.c_3005_b.Y_601_j == null) {
            return;
        }
        if (((String)this.rezhimMode.getValue()).equals("Old")) {
            this.J_1907_R(e);
        } else {
            this.R_4764_Y(e);
        }
    }

    private void J_1907_R(I_4477_R e) {
        c_4037_x.v_4276_D();
        c_4037_x.w_1484_f(7425);
        c_4037_x.Y_601_j();
        c_4037_x.q_2307_F();
        c_4037_x.J_1907_R(770, 771);
        c_4037_x.u_2550_I();
        c_4037_x.J_1907_R(false);
        c_4037_x.e_4240_b();
        double camX = Trails.c_3005_b.O_508_d().J_1907_R.J_1907_R().J_1907_R;
        double camY = Trails.c_3005_b.O_508_d().J_1907_R.J_1907_R().R_4764_Y;
        double camZ = Trails.c_3005_b.O_508_d().J_1907_R.J_1907_R().G_564_y;
        c_4037_x.J_1907_R(-camX, -camY, -camZ);
        float partialTicks = e.J_1907_R();
        float sizeValue = ((Float)this.dlinaSetting.getValue()).floatValue() * 100.0f;
        A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
        for (N_4263_v entity : Trails.c_3005_b.Y_601_j.J_1907_R()) {
            boolean isFirstPerson;
            if (!this.n_1700_B(entity) || !(entity instanceof r_4811_B)) continue;
            r_4811_B living = (r_4811_B)entity;
            double posX = living.q_1982_R + (living.O_3598_v() - living.q_1982_R) * (double)partialTicks;
            double posY = living.dtoRealmsServerAddress + (living.X_2960_b() - living.dtoRealmsServerAddress) * (double)partialTicks + 0.05 - (living.k_578_l() ? 0.15 : 0.0);
            double posZ = living.w_612_n + (living.l_2647_k() - living.w_612_n) * (double)partialTicks;
            boolean bl = isFirstPerson = living == Trails.c_3005_b.Y_259_p && Trails.c_3005_b.P_4830_p.P_4830_p() == t_1920_R.n_1700_B;
            if (!isFirstPerson) {
                List list = this.P_4830_p.computeIfAbsent(living.j_276_v(), id -> new ArrayList());
                list.add(new G_564_y(posX, posY, posZ));
            }
            float height = living.v_165_F() * (living.q_2307_F() ? 0.8f : 1.0f);
            float heightOffset = height * 0.01f;
            List<G_564_y> list = this.P_4830_p.get(living.j_276_v());
            if (list == null) continue;
            G_564_y prevTrail = null;
            boolean isFirst = true;
            int prevColor = 0;
            Iterator<G_564_y> it = list.iterator();
            while (it.hasNext()) {
                G_564_y trail = it.next();
                if ((float)(System.currentTimeMillis() - trail.P_1922_E) > sizeValue) {
                    it.remove();
                    continue;
                }
                trail.G_564_y = u_530_F.n_1700_B((float)(System.currentTimeMillis() - trail.P_1922_E) / sizeValue, 0.0f, 1.0f) * 255.0f;
                int color = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.J_1907_R), (int)((float)(255 * (255 - Math.round(trail.G_564_y))) / 220.0f));
                if (isFirst) {
                    A_4115_X.pos(trail.n_1700_B, trail.J_1907_R, trail.R_4764_Y).n_1700_B(color).endVertex();
                    A_4115_X.pos(trail.n_1700_B, trail.J_1907_R + (double)height, trail.R_4764_Y).n_1700_B(color).endVertex();
                } else {
                    A_4115_X.pos(prevTrail.n_1700_B, prevTrail.J_1907_R, prevTrail.R_4764_Y).n_1700_B(prevColor).endVertex();
                    A_4115_X.pos(prevTrail.n_1700_B, prevTrail.J_1907_R + (double)height, prevTrail.R_4764_Y).n_1700_B(prevColor).endVertex();
                }
                A_4115_X.pos(trail.n_1700_B, trail.J_1907_R + (double)height, trail.R_4764_Y).n_1700_B(color).endVertex();
                A_4115_X.pos(trail.n_1700_B, trail.J_1907_R, trail.R_4764_Y).n_1700_B(color).endVertex();
                if (isFirst) {
                    A_4115_X.pos(trail.n_1700_B, trail.J_1907_R, trail.R_4764_Y).n_1700_B(color).endVertex();
                    A_4115_X.pos(trail.n_1700_B, trail.J_1907_R + (double)heightOffset, trail.R_4764_Y).n_1700_B(color).endVertex();
                } else {
                    A_4115_X.pos(prevTrail.n_1700_B, prevTrail.J_1907_R, prevTrail.R_4764_Y).n_1700_B(prevColor).endVertex();
                    A_4115_X.pos(prevTrail.n_1700_B, prevTrail.J_1907_R + (double)heightOffset, prevTrail.R_4764_Y).n_1700_B(prevColor).endVertex();
                }
                A_4115_X.pos(trail.n_1700_B, trail.J_1907_R + (double)heightOffset, trail.R_4764_Y).n_1700_B(color).endVertex();
                A_4115_X.pos(trail.n_1700_B, trail.J_1907_R, trail.R_4764_Y).n_1700_B(color).endVertex();
                if (isFirst) {
                    A_4115_X.pos(trail.n_1700_B, trail.J_1907_R + (double)height, trail.R_4764_Y).n_1700_B(color).endVertex();
                    A_4115_X.pos(trail.n_1700_B, trail.J_1907_R + (double)height - (double)heightOffset, trail.R_4764_Y).n_1700_B(color).endVertex();
                } else {
                    A_4115_X.pos(prevTrail.n_1700_B, prevTrail.J_1907_R + (double)height, prevTrail.R_4764_Y).n_1700_B(prevColor).endVertex();
                    A_4115_X.pos(prevTrail.n_1700_B, prevTrail.J_1907_R + (double)height - (double)heightOffset, prevTrail.R_4764_Y).n_1700_B(prevColor).endVertex();
                }
                A_4115_X.pos(trail.n_1700_B, trail.J_1907_R + (double)height - (double)heightOffset, trail.R_4764_Y).n_1700_B(color).endVertex();
                A_4115_X.pos(trail.n_1700_B, trail.J_1907_R + (double)height, trail.R_4764_Y).n_1700_B(color).endVertex();
                isFirst = false;
                prevTrail = trail;
                prevColor = color;
            }
            if (!list.isEmpty()) continue;
            this.P_4830_p.remove(living.j_276_v());
        }
        Y_1740_V.J_1907_R();
        c_4037_x.M_588_G();
        c_4037_x.k_2293_S();
        c_4037_x.J_1907_R(true);
        c_4037_x.Y_259_p();
        c_4037_x.x_607_J();
        c_4037_x.w_1484_f(7424);
        c_4037_x.d_2461_k();
        c_4037_x.G_624_v();
    }

    private void R_4764_Y(I_4477_R e) {
        if (this.h_1847_R.isEmpty()) {
            return;
        }
        this.Q_4569_t();
        this.M_182_A();
        this.t_1786_h();
        this.multiplayerClientSuggestionProvider();
    }

    private boolean n_1700_B(a_3913_L player) {
        return player.q_1982_R != player.O_3598_v() || player.dtoRealmsServerAddress != player.X_2960_b() || player.w_612_n != player.l_2647_k();
    }

    private void J_1907_R(a_3913_L player) {
        if (this.h_1847_R.size() >= 500) {
            return;
        }
        double distance = -(player.C_415_h() / 2.0f);
        double yawRad = Math.toRadians(player.C_1162_e);
        double xOffset = -Math.sin(yawRad) * distance;
        double zOffset = Math.cos(yawRad) * distance;
        e_2866_D position = new e_2866_D(player.O_3598_v() + xOffset, player.X_2960_b() + (double)(player.v_165_F() * 0.4f), player.l_2647_k() + zOffset);
        e_2866_D velocity = new e_2866_D(player.I_4348_c().J_1907_R, 0.0, player.I_4348_c().G_564_y).G_564_y(1.5 + Math.random(), 1.5 + Math.random(), 1.5 + Math.random());
        this.h_1847_R.add(new J_1907_R(position, velocity, this.h_1847_R.size(), ((Float)this.vremyaZhizniSetting.getValue()).intValue()));
    }

    private void h_1847_R() {
        if (Trails.c_3005_b.Y_259_p == null) {
            return;
        }
        long lifeTimeMs = ((Float)this.vremyaZhizniSetting.getValue()).intValue();
        this.h_1847_R.removeIf(particle -> particle.G_564_y.n_1700_B(lifeTimeMs));
        this.h_1847_R.removeIf(particle -> particle.n_1700_B.u_1723_Y(Trails.c_3005_b.Y_259_p.s_4990_V()) >= 100.0);
    }

    private void Q_4569_t() {
        c_4037_x.v_4276_D();
        c_4037_x.x_607_J();
        c_4037_x.Y_601_j();
        c_4037_x.J_1907_R(770, 771);
        c_4037_x.u_2550_I();
        c_4037_x.J_1907_R(false);
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.q_2307_F();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glShadeModel((int)7425);
        double camX = Trails.c_3005_b.O_508_d().J_1907_R.J_1907_R().J_1907_R;
        double camY = Trails.c_3005_b.O_508_d().J_1907_R.J_1907_R().R_4764_Y;
        double camZ = Trails.c_3005_b.O_508_d().J_1907_R.J_1907_R().G_564_y;
        c_4037_x.J_1907_R(-camX, -camY, -camZ);
    }

    private void M_182_A() {
        float ticks = c_3005_b.RealmsClientConfig();
        for (int i = 0; i < (int)ticks; ++i) {
            for (J_1907_R particle : this.h_1847_R) {
                particle.n_1700_B();
            }
        }
    }

    private void t_1786_h() {
        int index = 0;
        for (J_1907_R particle : this.h_1847_R) {
            this.n_1700_B(particle);
            int baseColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            int alpha = (int)particle.P_1922_E.n_1700_B();
            int color = H_2506_c.n_1700_B(baseColor, alpha);
            if (index > 0) {
                J_1907_R prevParticle = this.h_1847_R.get(index - 1);
                this.n_1700_B(prevParticle, particle);
            }
            this.n_1700_B(particle, color);
            ++index;
        }
    }

    private void n_1700_B(J_1907_R particle) {
        if ((int)particle.P_1922_E.n_1700_B() != 128 && !particle.G_564_y.n_1700_B(250L)) {
            particle.P_1922_E.n_1700_B(128.0);
        }
        float fadeTime = ((Float)this.vremyaZhizniSetting.getValue()).floatValue() - 250.0f;
        if ((int)particle.P_1922_E.n_1700_B() != 0 && particle.G_564_y.n_1700_B((long)fadeTime)) {
            particle.P_1922_E.n_1700_B(0.0);
        }
    }

    private void n_1700_B(J_1907_R prevParticle, J_1907_R currentParticle) {
        float smooth = 0.1f;
        double newX = this.n_1700_B(prevParticle.n_1700_B.J_1907_R, currentParticle.n_1700_B.J_1907_R, smooth);
        double newY = this.n_1700_B(prevParticle.n_1700_B.R_4764_Y, currentParticle.n_1700_B.R_4764_Y, smooth);
        double newZ = this.n_1700_B(prevParticle.n_1700_B.G_564_y, currentParticle.n_1700_B.G_564_y, smooth);
        prevParticle.n_1700_B = new e_2866_D(newX, newY, newZ);
    }

    private double n_1700_B(double a, double b, double t) {
        return a + (b - a) * t;
    }

    private void n_1700_B(J_1907_R particle, int color) {
        c_4037_x.v_4276_D();
        c_4037_x.J_1907_R(particle.n_1700_B.J_1907_R, particle.n_1700_B.R_4764_Y, particle.n_1700_B.G_564_y);
        float yaw = -Trails.c_3005_b.O_508_d().J_1907_R.P_1922_E();
        float pitch = Trails.c_3005_b.O_508_d().J_1907_R.G_564_y();
        c_4037_x.R_4764_Y(yaw, 0.0f, 1.0f, 0.0f);
        c_4037_x.R_4764_Y(pitch, 1.0f, 0.0f, 0.0f);
        c_4037_x.R_4764_Y(0.0f, ((Float)this.razmerChasticSetting.getValue()).floatValue() / 2.0f, 0.0f);
        float pSize = ((Float)this.razmerChasticSetting.getValue()).floatValue();
        float dynamicBloom = ((Float)this.razmerSvecheniyaSetting.getValue()).floatValue();
        if (!this.h_1847_R.isEmpty()) {
            double dist = particle.n_1700_B.u_1723_Y(this.h_1847_R.get((int)0).n_1700_B);
            dynamicBloom = (float)u_530_F.n_1700_B(dist * 0.5, 0.25, (double)((Float)this.razmerSvecheniyaSetting.getValue()).floatValue());
        }
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        c_3005_b.G_624_v().n_1700_B(this.Q_4569_t);
        GL11.glBlendFunc((int)770, (int)1);
        A_4115_X.n_1700_B(7, E_688_b.k_2293_S);
        A_4115_X.pos(-dynamicBloom, -dynamicBloom, 0.0).tex(0.0f, 0.0f).n_1700_B(r, g, b, a * 0.5f).endVertex();
        A_4115_X.pos(-dynamicBloom, dynamicBloom, 0.0).tex(0.0f, 1.0f).n_1700_B(r, g, b, a * 0.5f).endVertex();
        A_4115_X.pos(dynamicBloom, dynamicBloom, 0.0).tex(1.0f, 1.0f).n_1700_B(r, g, b, a * 0.5f).endVertex();
        A_4115_X.pos(dynamicBloom, -dynamicBloom, 0.0).tex(1.0f, 0.0f).n_1700_B(r, g, b, a * 0.5f).endVertex();
        Y_1740_V.J_1907_R();
        A_4115_X.n_1700_B(7, E_688_b.k_2293_S);
        A_4115_X.pos(-pSize, -pSize, 0.0).tex(0.0f, 0.0f).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.pos(-pSize, pSize, 0.0).tex(0.0f, 1.0f).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.pos(pSize, pSize, 0.0).tex(1.0f, 1.0f).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.pos(pSize, -pSize, 0.0).tex(1.0f, 0.0f).n_1700_B(r, g, b, a).endVertex();
        Y_1740_V.J_1907_R();
        GL11.glBlendFunc((int)770, (int)771);
        c_4037_x.d_2461_k();
    }

    private void multiplayerClientSuggestionProvider() {
        c_4037_x.s_2632_s();
        c_4037_x.Y_259_p();
        c_4037_x.G_624_v();
        GL11.glShadeModel((int)7424);
        c_4037_x.k_2293_S();
        c_4037_x.J_1907_R(true);
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.M_588_G();
        c_4037_x.x_607_J();
        c_4037_x.d_2461_k();
    }

    private boolean n_1700_B(N_4263_v entity) {
        if (!entity.RealmsLongRunningMcoTaskScreen() || !v_2826_q.n_1700_B(entity)) {
            return false;
        }
        if (entity.F_3572_x() && !this.otobrazhatOptions.isOptionEnabled("\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u044b\u0445").booleanValue()) {
            return false;
        }
        if (entity instanceof a_3913_L) {
            if (entity == Trails.c_3005_b.Y_259_p) {
                return E_4612_l.J_1907_R(entity, this.otobrazhatOptions);
            }
            return E_4612_l.n_1700_B(entity, this.otobrazhatOptions, false);
        }
        return false;
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g e) {
        this.P_4830_p.clear();
        this.h_1847_R.clear();
    }

    @Override
    public void onDisable() {
        this.P_4830_p.clear();
        this.h_1847_R.clear();
        super.onDisable();
    }

    public static final class G_564_y {
        final double n_1700_B;
        final double J_1907_R;
        final double R_4764_Y;
        float G_564_y;
        final long P_1922_E;

        public G_564_y(double x, double y, double z) {
            this.n_1700_B = x;
            this.J_1907_R = y;
            this.R_4764_Y = z;
            this.P_1922_E = System.currentTimeMillis();
        }
    }

    public static final class J_1907_R {
        public e_2866_D n_1700_B;
        public e_2866_D J_1907_R;
        public final int R_4764_Y;
        public final R_4764_Y G_564_y;
        public final n_1700_B P_1922_E;

        public J_1907_R(e_2866_D position, e_2866_D velocity, int index, long lifeTime) {
            this.n_1700_B = position;
            this.J_1907_R = velocity;
            this.R_4764_Y = index;
            this.G_564_y = new R_4764_Y();
            this.P_1922_E = new n_1700_B(0.0);
        }

        public void onEnable() {
            this.n_1700_B = this.n_1700_B.P_1922_E(this.J_1907_R.n_1700_B(0.01));
            this.J_1907_R = this.J_1907_R.n_1700_B(0.98);
        }
    }

    public static final class n_1700_B {
        private double n_1700_B;
        private double J_1907_R;
        private long R_4764_Y;
        private static final double G_564_y = 0.15;

        public n_1700_B(double value) {
            this.n_1700_B = value;
            this.J_1907_R = value;
            this.R_4764_Y = System.currentTimeMillis();
        }

        public void n_1700_B(double target) {
            this.J_1907_R = target;
        }

        public double n_1700_B() {
            long now = System.currentTimeMillis();
            double delta = (double)(now - this.R_4764_Y) / 16.0;
            this.R_4764_Y = now;
            this.n_1700_B += (this.J_1907_R - this.n_1700_B) * 0.15 * delta;
            return this.n_1700_B;
        }
    }

    public static final class R_4764_Y {
        private long n_1700_B = System.currentTimeMillis();

        public boolean n_1700_B(long time) {
            return System.currentTimeMillis() - this.n_1700_B >= time;
        }

        public void onEnable() {
            this.n_1700_B = System.currentTimeMillis();
        }
    }
}



