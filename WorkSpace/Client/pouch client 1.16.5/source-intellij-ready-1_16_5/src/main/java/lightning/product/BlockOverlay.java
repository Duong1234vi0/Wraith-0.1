/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.awt.Color;
import lightning.product.E_688_b;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.H_2506_c;
import lightning.product.HitResult;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.K_1200_E;
import lightning.product.CollisionContext;
import lightning.product.Module;
import lightning.product.X_933_l;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.Ambience;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;
import lightning.product.AttackAura;
import lightning.product.s_1395_c;
import lightning.product.s_4405_m;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;
import org.lwjgl.opengl.GL11;

public class BlockOverlay
extends Module
implements MinecraftAccess {
    private static final float v_4262_N = 15.0f;
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "Default", "Default", "Dotted", "Animate", "\u041d\u0435\u0431\u043e Plasma", "\u041d\u0435\u0431\u043e Balatro");
    private final ModeSetting cvetaMode = new ModeSetting("\u0426\u0432\u0435\u0442\u0430", "\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441", "\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441", "\u0421\u0432\u043e\u0439");
    private final BooleanSetting animaciyaPerehodaEnabled = new BooleanSetting("\u0410\u043d\u0438\u043c\u0430\u0446\u0438\u044f \u043f\u0435\u0440\u0435\u0445\u043e\u0434\u0430", true);
    private final h_2367_h u_2550_I = new h_2367_h("\u0426\u0432\u0435\u0442 \u043b\u0438\u043d\u0438\u0439", true, new Color(150, 155, 175, 255).getRGB(), () -> (this.rezhimMode.isMode("Animate") || this.rezhimMode.isMode("\u041d\u0435\u0431\u043e Plasma") || this.rezhimMode.isMode("\u041d\u0435\u0431\u043e Balatro")) && this.cvetaMode.isMode("\u0421\u0432\u043e\u0439"));
    private final h_2367_h M_588_G = new h_2367_h("\u0426\u0432\u0435\u0442 \u0437\u0430\u043b\u0438\u0432\u043a\u0438", true, new Color(197, 197, 197, 90).getRGB(), () -> this.cvetaMode.isMode("\u0421\u0432\u043e\u0439"));
    private final h_2367_h P_4830_p = new h_2367_h("\u0426\u0432\u0435\u0442 \u0440\u0430\u043c\u043a\u0438", true, new Color(255, 255, 255, 180).getRGB(), () -> this.cvetaMode.isMode("\u0421\u0432\u043e\u0439"));
    private c_1514_x h_1847_R;
    private I_4817_s Q_4569_t;
    private I_4817_s M_182_A;
    private I_4817_s t_1786_h;
    private final Animation multiplayerClientSuggestionProvider = new Animation(0.0f, 15.0f, Easing.u_2550_I);

    public BlockOverlay() {
        super("BlockOverlay", ModuleCategory.R_4764_Y);
        this.addSettings(this.rezhimMode, this.cvetaMode, this.animaciyaPerehodaEnabled, this.u_2550_I, this.M_588_G, this.P_4830_p);
    }

    private int h_1847_R() {
        return q_3148_R.n_1700_B(K_1200_E.J_1907_R);
    }

    private int Q_4569_t() {
        return this.cvetaMode.isMode("\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441") ? this.h_1847_R() : ((Integer)this.u_2550_I.J_1907_R()).intValue();
    }

    private int M_182_A() {
        if (!this.cvetaMode.isMode("\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441")) {
            return (Integer)this.M_588_G.J_1907_R();
        }
        int t = this.h_1847_R();
        int a = H_2506_c.G_564_y((Integer)this.M_588_G.J_1907_R());
        if (a < 55) {
            a = 85;
        }
        return H_2506_c.n_1700_B(t, a);
    }

    private int t_1786_h() {
        if (!this.cvetaMode.isMode("\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441")) {
            return (Integer)this.P_4830_p.J_1907_R();
        }
        int t = this.h_1847_R();
        int a = H_2506_c.G_564_y(t);
        if (a < 100) {
            return H_2506_c.n_1700_B(t, 200);
        }
        return t;
    }

    private void multiplayerClientSuggestionProvider() {
        this.h_1847_R = null;
        this.Q_4569_t = null;
        this.M_182_A = null;
        this.t_1786_h = null;
        this.multiplayerClientSuggestionProvider.J_1907_R(0.0f);
    }

    private I_4817_s n_1700_B(I_4817_s from, I_4817_s to, float t) {
        return new I_4817_s(u_530_F.G_564_y((double)t, from.minX, to.minX), u_530_F.G_564_y((double)t, from.minY, to.minY), u_530_F.G_564_y((double)t, from.minZ, to.minZ), u_530_F.G_564_y((double)t, from.maxX, to.maxX), u_530_F.G_564_y((double)t, from.maxY, to.maxY), u_530_F.G_564_y((double)t, from.maxZ, to.maxZ));
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        I_4817_s box;
        if (BlockOverlay.c_3005_b.Y_259_p == null || BlockOverlay.c_3005_b.Y_601_j == null) {
            return;
        }
        AttackAura attackAura = (AttackAura)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AttackAura.class);
        if (attackAura != null && attackAura.w_1484_f() && attackAura.h_1847_R() != null) {
            this.multiplayerClientSuggestionProvider();
            return;
        }
        HitResult hit = BlockOverlay.c_3005_b.Z_875_P;
        if (hit == null || hit.R_4764_Y() != HitResult.n_1700_B.J_1907_R) {
            this.multiplayerClientSuggestionProvider();
            return;
        }
        c_1514_x pos = ((BlockHitResult)hit).n_1700_B();
        s_1395_c shape = BlockOverlay.c_3005_b.Y_601_j.getBlockState(pos).n_1700_B((BlockGetter)BlockOverlay.c_3005_b.Y_601_j, pos, CollisionContext.n_1700_B(BlockOverlay.c_3005_b.Y_259_p));
        if (shape.J_1907_R()) {
            this.multiplayerClientSuggestionProvider();
            return;
        }
        I_4817_s targetBox = shape.n_1700_B().offset(pos);
        if (this.h_1847_R == null || !pos.equals(this.h_1847_R)) {
            this.h_1847_R = pos;
            this.Q_4569_t = this.t_1786_h != null ? this.t_1786_h : targetBox;
            this.M_182_A = targetBox;
            this.multiplayerClientSuggestionProvider.J_1907_R(0.0f);
        } else {
            this.M_182_A = targetBox;
        }
        if (!this.animaciyaPerehodaEnabled.isEnabled().booleanValue()) {
            this.multiplayerClientSuggestionProvider.J_1907_R(1.0f);
            this.t_1786_h = targetBox;
            box = targetBox;
        } else {
            this.multiplayerClientSuggestionProvider.n_1700_B(1.0f);
            float t = u_530_F.n_1700_B(this.multiplayerClientSuggestionProvider.n_1700_B(), 0.0f, 1.0f);
            this.t_1786_h = this.Q_4569_t == null || this.M_182_A == null ? targetBox : this.n_1700_B(this.Q_4569_t, this.M_182_A, t);
            box = this.t_1786_h != null ? this.t_1786_h : targetBox;
        }
        c_4037_x.v_4276_D();
        c_4037_x.n_1700_B(new g_221_o().R_4764_Y().n_1700_B());
        c_4037_x.J_1907_R(-c_3005_b.O_508_d().renderPosX(), -c_3005_b.O_508_d().renderPosY(), -c_3005_b.O_508_d().renderPosZ());
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.t_1786_h();
        if (this.rezhimMode.isMode("Animate")) {
            this.n_1700_B(pos, box);
        } else if (this.rezhimMode.isMode("\u041d\u0435\u0431\u043e Plasma") || this.rezhimMode.isMode("\u041d\u0435\u0431\u043e Balatro")) {
            this.addSettings(box, this.rezhimMode.isMode("\u041d\u0435\u0431\u043e Balatro"), event.J_1907_R());
        } else if (this.rezhimMode.isMode("Dotted")) {
            c_4037_x.e_4240_b();
            this.n_1700_B(box, this.M_182_A());
            this.R_4764_Y(box, this.t_1786_h());
        } else {
            c_4037_x.e_4240_b();
            this.n_1700_B(box, this.M_182_A());
            this.J_1907_R(box, this.t_1786_h());
        }
        c_4037_x.x_607_J();
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.J_1907_R(c_3005_b.O_508_d().renderPosX(), c_3005_b.O_508_d().renderPosY(), c_3005_b.O_508_d().renderPosZ());
        c_4037_x.d_2461_k();
    }

    private void n_1700_B(c_1514_x pos, I_4817_s box) {
        float[] quad;
        float minX = (float)box.minX;
        float minY = (float)box.minY;
        float minZ = (float)box.minZ;
        float maxX = (float)box.maxX;
        float maxY = (float)box.maxY;
        float maxZ = (float)box.maxZ;
        boolean useWave = s_4405_m.Y_601_j.n_1700_B();
        int lc = this.Q_4569_t();
        float cr = (float)H_2506_c.n_1700_B(lc) / 255.0f;
        float cg = (float)H_2506_c.J_1907_R(lc) / 255.0f;
        float cb = (float)H_2506_c.R_4764_Y(lc) / 255.0f;
        c_4037_x.s_2632_s();
        for (b_257_Y face : b_257_Y.values()) {
            quad = this.n_1700_B(face, minX, minY, minZ, maxX, maxY, maxZ);
            this.n_1700_B(quad, this.M_182_A());
        }
        if (useWave) {
            c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.P_1922_E);
            s_4405_m.Y_601_j.J_1907_R();
            s_4405_m.Y_601_j.J_1907_R("u_time", (float)((double)System.nanoTime() / 1.0E9));
            for (b_257_Y face : b_257_Y.values()) {
                quad = this.n_1700_B(face, minX, minY, minZ, maxX, maxY, maxZ);
                float seed = (float)(pos.getX() * 73856093 ^ pos.getY() * 19349663 ^ pos.getZ() * 83492791 ^ face.ordinal() * 15485863) * 1.0E-5f;
                s_4405_m.Y_601_j.J_1907_R("u_seed", seed);
                s_4405_m.Y_601_j.J_1907_R("u_color", cr, cg, cb);
                this.n_1700_B(quad);
            }
            s_4405_m.Y_601_j.R_4764_Y();
        }
        c_4037_x.s_2632_s();
        this.J_1907_R(box, this.t_1786_h());
    }

    private void n_1700_B(I_4817_s box, boolean balatro, float partialTicks) {
        s_4405_m shader;
        s_4405_m s_4405_m2 = shader = balatro ? s_4405_m.g_221_o : s_4405_m.z_4693_k;
        if (!shader.n_1700_B() || BlockOverlay.c_3005_b.Y_601_j == null) {
            c_4037_x.e_4240_b();
            this.n_1700_B(box, this.M_182_A());
            this.J_1907_R(box, this.t_1786_h());
            return;
        }
        int lc = this.Q_4569_t();
        float tr = (float)H_2506_c.n_1700_B(lc) / 255.0f;
        float tg = (float)H_2506_c.J_1907_R(lc) / 255.0f;
        float tb = (float)H_2506_c.R_4764_Y(lc) / 255.0f;
        float alpha = (float)H_2506_c.G_564_y(this.M_182_A()) / 255.0f;
        float time = ((float)BlockOverlay.c_3005_b.Y_601_j.X_933_l() + partialTicks) * ((Float)Ambience.Y_601_j.J_1907_R()).floatValue();
        shader.J_1907_R();
        shader.n_1700_B("u_Color", tr, tg, tb, 1.0f);
        shader.n_1700_B("u_Scale", ((Float)Ambience.w_1457_N.J_1907_R()).floatValue());
        shader.J_1907_R("u_Time", time * 0.08f);
        shader.J_1907_R("u_Alpha", alpha);
        c_4037_x.e_4240_b();
        c_4037_x.q_2307_F();
        this.n_1700_B(box);
        shader.R_4764_Y();
        c_4037_x.k_2293_S();
        this.J_1907_R(box, this.t_1786_h());
    }

    private void n_1700_B(float x, float y, float z, double cx, double cy, double cz) {
        float dx = (float)((double)x - cx);
        float dy = (float)((double)y - cy);
        float dz = (float)((double)z - cz);
        float len = u_530_F.R_4764_Y(dx * dx + dy * dy + dz * dz);
        if (len > 1.0E-5f) {
            dx /= len;
            dy /= len;
            dz /= len;
        } else {
            dx = 0.0f;
            dy = 1.0f;
            dz = 0.0f;
        }
        int cr = u_530_F.n_1700_B((int)(dx * 127.5f + 127.5f), 0, 255);
        int cg = u_530_F.n_1700_B((int)(dy * 127.5f + 127.5f), 0, 255);
        int cb = u_530_F.n_1700_B((int)(dz * 127.5f + 127.5f), 0, 255);
        A_4115_X.pos(x, y, z).color(cr, cg, cb, 255).endVertex();
    }

    private void n_1700_B(I_4817_s box) {
        double cx = (box.minX + box.maxX) * 0.5;
        double cy = (box.minY + box.maxY) * 0.5;
        double cz = (box.minZ + box.maxZ) * 0.5;
        float minX = (float)box.minX;
        float minY = (float)box.minY;
        float minZ = (float)box.minZ;
        float maxX = (float)box.maxX;
        float maxY = (float)box.maxY;
        float maxZ = (float)box.maxZ;
        A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
        for (b_257_Y face : b_257_Y.values()) {
            float[] q = this.n_1700_B(face, minX, minY, minZ, maxX, maxY, maxZ);
            this.n_1700_B(q[0], q[1], q[2], cx, cy, cz);
            this.n_1700_B(q[3], q[4], q[5], cx, cy, cz);
            this.n_1700_B(q[6], q[7], q[8], cx, cy, cz);
            this.n_1700_B(q[9], q[10], q[11], cx, cy, cz);
        }
        Y_1740_V.J_1907_R();
    }

    private float[] n_1700_B(b_257_Y face, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        switch (face) {
            case n_1700_B: {
                return new float[]{minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, minX, minY, maxZ};
            }
            case J_1907_R: {
                return new float[]{minX, maxY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ, minX, maxY, maxZ};
            }
            case R_4764_Y: {
                return new float[]{maxX, minY, minZ, minX, minY, minZ, minX, maxY, minZ, maxX, maxY, minZ};
            }
            case G_564_y: {
                return new float[]{minX, minY, maxZ, maxX, minY, maxZ, maxX, maxY, maxZ, minX, maxY, maxZ};
            }
            case P_1922_E: {
                return new float[]{minX, minY, maxZ, minX, minY, minZ, minX, maxY, minZ, minX, maxY, maxZ};
            }
            case u_1723_Y: {
                return new float[]{maxX, minY, minZ, maxX, minY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ};
            }
        }
        return new float[12];
    }

    private void n_1700_B(I_4817_s box, int color) {
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        int top = H_2506_c.J_1907_R(color, 10);
        int bot = H_2506_c.J_1907_R(color, 0.5f);
        float tr = (float)H_2506_c.n_1700_B(top) / 255.0f;
        float tg = (float)H_2506_c.J_1907_R(top) / 255.0f;
        float tb = (float)H_2506_c.R_4764_Y(top) / 255.0f;
        float br = (float)H_2506_c.n_1700_B(bot) / 255.0f;
        float bg = (float)H_2506_c.J_1907_R(bot) / 255.0f;
        float bb = (float)H_2506_c.R_4764_Y(bot) / 255.0f;
        float x0 = (float)box.minX;
        float x1 = (float)box.maxX;
        float y0 = (float)box.minY;
        float y1 = (float)box.maxY;
        float z0 = (float)box.minZ;
        float z1 = (float)box.maxZ;
        c_4037_x.q_2307_F();
        A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
        A_4115_X.pos(x0, y0, z0).n_1700_B(br, bg, bb, a).endVertex();
        A_4115_X.pos(x1, y0, z0).n_1700_B(br, bg, bb, a).endVertex();
        A_4115_X.pos(x1, y0, z1).n_1700_B(br, bg, bb, a).endVertex();
        A_4115_X.pos(x0, y0, z1).n_1700_B(br, bg, bb, a).endVertex();
        A_4115_X.pos(x0, y1, z0).n_1700_B(tr, tg, tb, a).endVertex();
        A_4115_X.pos(x0, y1, z1).n_1700_B(tr, tg, tb, a).endVertex();
        A_4115_X.pos(x1, y1, z1).n_1700_B(tr, tg, tb, a).endVertex();
        A_4115_X.pos(x1, y1, z0).n_1700_B(tr, tg, tb, a).endVertex();
        A_4115_X.pos(x0, y0, z0).n_1700_B(br, bg, bb, a).endVertex();
        A_4115_X.pos(x0, y1, z0).n_1700_B(tr, tg, tb, a).endVertex();
        A_4115_X.pos(x1, y1, z0).n_1700_B(tr, tg, tb, a).endVertex();
        A_4115_X.pos(x1, y0, z0).n_1700_B(br, bg, bb, a).endVertex();
        A_4115_X.pos(x0, y0, z1).n_1700_B(br, bg, bb, a).endVertex();
        A_4115_X.pos(x1, y0, z1).n_1700_B(br, bg, bb, a).endVertex();
        A_4115_X.pos(x1, y1, z1).n_1700_B(tr, tg, tb, a).endVertex();
        A_4115_X.pos(x0, y1, z1).n_1700_B(tr, tg, tb, a).endVertex();
        A_4115_X.pos(x0, y0, z0).n_1700_B(br, bg, bb, a).endVertex();
        A_4115_X.pos(x0, y0, z1).n_1700_B(br, bg, bb, a).endVertex();
        A_4115_X.pos(x0, y1, z1).n_1700_B(tr, tg, tb, a).endVertex();
        A_4115_X.pos(x0, y1, z0).n_1700_B(tr, tg, tb, a).endVertex();
        A_4115_X.pos(x1, y0, z0).n_1700_B(br, bg, bb, a).endVertex();
        A_4115_X.pos(x1, y1, z0).n_1700_B(tr, tg, tb, a).endVertex();
        A_4115_X.pos(x1, y1, z1).n_1700_B(tr, tg, tb, a).endVertex();
        A_4115_X.pos(x1, y0, z1).n_1700_B(br, bg, bb, a).endVertex();
        Y_1740_V.J_1907_R();
        c_4037_x.k_2293_S();
    }

    private void J_1907_R(I_4817_s box, int color) {
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        float x0 = (float)box.minX;
        float x1 = (float)box.maxX;
        float y0 = (float)box.minY;
        float y1 = (float)box.maxY;
        float z0 = (float)box.minZ;
        float z1 = (float)box.maxZ;
        c_4037_x.v_4276_D();
        c_4037_x.e_4240_b();
        c_4037_x.G_564_y(2.0f);
        GL11.glEnable((int)2848);
        A_4115_X.n_1700_B(1, E_688_b.Y_601_j);
        this.n_1700_B(x0, y0, z0, x1, y0, z0, r, g, b, a);
        this.n_1700_B(x1, y0, z0, x1, y0, z1, r, g, b, a);
        this.n_1700_B(x1, y0, z1, x0, y0, z1, r, g, b, a);
        this.n_1700_B(x0, y0, z1, x0, y0, z0, r, g, b, a);
        this.n_1700_B(x0, y1, z0, x1, y1, z0, r, g, b, a);
        this.n_1700_B(x1, y1, z0, x1, y1, z1, r, g, b, a);
        this.n_1700_B(x1, y1, z1, x0, y1, z1, r, g, b, a);
        this.n_1700_B(x0, y1, z1, x0, y1, z0, r, g, b, a);
        this.n_1700_B(x0, y0, z0, x0, y1, z0, r, g, b, a);
        this.n_1700_B(x1, y0, z0, x1, y1, z0, r, g, b, a);
        this.n_1700_B(x1, y0, z1, x1, y1, z1, r, g, b, a);
        this.n_1700_B(x0, y0, z1, x0, y1, z1, r, g, b, a);
        Y_1740_V.J_1907_R();
        GL11.glDisable((int)2848);
        c_4037_x.G_564_y(1.0f);
        c_4037_x.x_607_J();
        c_4037_x.d_2461_k();
    }

    private void n_1700_B(float x1, float y1, float z1, float x2, float y2, float z2, float r, float g, float b, float a) {
        A_4115_X.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.pos(x2, y2, z2).n_1700_B(r, g, b, a).endVertex();
    }

    private void R_4764_Y(I_4817_s box, int color) {
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        float x0 = (float)box.minX;
        float x1 = (float)box.maxX;
        float y0 = (float)box.minY;
        float y1 = (float)box.maxY;
        float z0 = (float)box.minZ;
        float z1 = (float)box.maxZ;
        float segSize = 0.15f;
        int segX = Math.max(1, Math.round((x1 - x0) / segSize));
        int segY = Math.max(1, Math.round((y1 - y0) / segSize));
        int segZ = Math.max(1, Math.round((z1 - z0) / segSize));
        c_4037_x.v_4276_D();
        c_4037_x.e_4240_b();
        c_4037_x.q_2307_F();
        c_4037_x.G_564_y(2.0f);
        GL11.glEnable((int)2848);
        this.n_1700_B(x0, y0, z0, x1, y0, z0, r, g, b, a, segX);
        this.n_1700_B(x1, y0, z0, x1, y0, z1, r, g, b, a, segZ);
        this.n_1700_B(x1, y0, z1, x0, y0, z1, r, g, b, a, segX);
        this.n_1700_B(x0, y0, z1, x0, y0, z0, r, g, b, a, segZ);
        this.n_1700_B(x0, y1, z0, x1, y1, z0, r, g, b, a, segX);
        this.n_1700_B(x1, y1, z0, x1, y1, z1, r, g, b, a, segZ);
        this.n_1700_B(x1, y1, z1, x0, y1, z1, r, g, b, a, segX);
        this.n_1700_B(x0, y1, z1, x0, y1, z0, r, g, b, a, segZ);
        this.n_1700_B(x0, y0, z0, x0, y1, z0, r, g, b, a, segY);
        this.n_1700_B(x1, y0, z0, x1, y1, z0, r, g, b, a, segY);
        this.n_1700_B(x1, y0, z1, x1, y1, z1, r, g, b, a, segY);
        this.n_1700_B(x0, y0, z1, x0, y1, z1, r, g, b, a, segY);
        GL11.glDisable((int)2848);
        c_4037_x.G_564_y(1.0f);
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
        c_4037_x.d_2461_k();
    }

    private void n_1700_B(float x1, float y1, float z1, float x2, float y2, float z2, float r, float g, float b, float a, int segments) {
        A_4115_X.n_1700_B(1, E_688_b.Y_601_j);
        if (segments == 1) {
            A_4115_X.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
            A_4115_X.pos(x2, y2, z2).n_1700_B(r, g, b, a).endVertex();
            Y_1740_V.J_1907_R();
            return;
        }
        float segLen = 1.0f / (float)segments;
        float dashLen = segLen * 0.6f;
        for (int i = 0; i < segments; ++i) {
            float t1;
            float t0;
            if (i == 0) {
                t0 = 0.0f;
                t1 = dashLen;
            } else if (i == segments - 1) {
                t0 = 1.0f - dashLen;
                t1 = 1.0f;
            } else {
                float c = ((float)i + 0.5f) * segLen;
                t0 = c - dashLen / 2.0f;
                t1 = c + dashLen / 2.0f;
            }
            A_4115_X.pos(x1 + (x2 - x1) * t0, y1 + (y2 - y1) * t0, z1 + (z2 - z1) * t0).n_1700_B(r, g, b, a).endVertex();
            A_4115_X.pos(x1 + (x2 - x1) * t1, y1 + (y2 - y1) * t1, z1 + (z2 - z1) * t1).n_1700_B(r, g, b, a).endVertex();
        }
        Y_1740_V.J_1907_R();
    }

    private void n_1700_B(float[] q) {
        c_4037_x.e_4240_b();
        c_4037_x.q_2307_F();
        A_4115_X.n_1700_B(7, E_688_b.Q_2552_b);
        A_4115_X.pos(q[0], q[1], q[2]).tex(0.0f, 0.0f).endVertex();
        A_4115_X.pos(q[3], q[4], q[5]).tex(1.0f, 0.0f).endVertex();
        A_4115_X.pos(q[6], q[7], q[8]).tex(1.0f, 1.0f).endVertex();
        A_4115_X.pos(q[9], q[10], q[11]).tex(0.0f, 1.0f).endVertex();
        Y_1740_V.J_1907_R();
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
    }

    private void n_1700_B(float[] q, int color) {
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        c_4037_x.e_4240_b();
        c_4037_x.q_2307_F();
        A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
        A_4115_X.pos(q[0], q[1], q[2]).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.pos(q[3], q[4], q[5]).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.pos(q[6], q[7], q[8]).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.pos(q[9], q[10], q[11]).n_1700_B(r, g, b, a).endVertex();
        Y_1740_V.J_1907_R();
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
    }
}



