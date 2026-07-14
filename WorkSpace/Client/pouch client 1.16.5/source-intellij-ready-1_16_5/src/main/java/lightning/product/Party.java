/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import java.util.Optional;
import lightning.product.D_1410_T;
import lightning.product.D_686_b;
import lightning.product.G_1539_D;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.H_2506_c;
import lightning.product.HitResult;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.Module;
import lightning.product.Z_3822_q;
import lightning.product.a_3913_L;
import lightning.product.b_3528_u;
import lightning.product.MinecraftClient;
import lightning.product.e_2866_D;
import lightning.product.l_3370_o;
import lightning.product.KeyBindSetting;
import lightning.product.u_530_F;
import lightning.product.v_2826_q;
import lightning.product.ModuleCategory;
import org.joml.Vector2f;

public class Party
extends Module {
    private static final String w_1484_f = "M";
    private static final String t_148_a = "\u041e\u043f\u0430\u0441\u043d\u043e\u0441\u0442\u044c";
    private static final int s_956_w = 22;
    private static final double u_2550_I = 75.0;
    public final KeyBindSetting metkaPoVzglyaduKeyBind = new KeyBindSetting("\u041c\u0435\u0442\u043a\u0430 \u043f\u043e \u0432\u0437\u0433\u043b\u044f\u0434\u0443");

    public Party() {
        super("Party", ModuleCategory.P_1922_E);
        this.addSettings(this.metkaPoVzglyaduKeyBind);
    }

    public static void h_1847_R() {
        double pz;
        double py;
        double px;
        MinecraftClient mc = MinecraftClient.A_4115_X();
        if (mc.Y_259_p == null || mc.Y_601_j == null || mc.Y_1740_V != null) {
            return;
        }
        if (!G_1539_D.n_1700_B.P_1922_E() || !G_1539_D.v_4262_N()) {
            return;
        }
        String room = G_1539_D.u_1723_Y();
        if (room == null || !G_1539_D.J_1907_R(room)) {
            return;
        }
        String selfMc = mc.Y_259_p.y_4642_Y().getName();
        if (selfMc == null || selfMc.isEmpty()) {
            return;
        }
        n_1700_B pick = Party.n_1700_B(mc, 75.0);
        String targetMc = null;
        if (pick.n_1700_B != null) {
            N_4263_v n_4263_v = pick.n_1700_B;
            if (n_4263_v instanceof a_3913_L) {
                a_3913_L pl = (a_3913_L)n_4263_v;
                targetMc = pl.y_4642_Y().getName();
            }
            e_2866_D p = pick.n_1700_B.s_4990_V();
            double hh = pick.n_1700_B.i_601_W().getYSize();
            px = p.J_1907_R;
            py = p.R_4764_Y + hh + 0.2;
            pz = p.G_564_y;
        } else if (pick.J_1907_R != null) {
            px = pick.J_1907_R.J_1907_R;
            py = pick.J_1907_R.R_4764_Y + 0.12;
            pz = pick.J_1907_R.G_564_y;
        } else {
            return;
        }
        G_1539_D.n_1700_B.n_1700_B(room, selfMc, targetMc, px, py, pz);
    }

    public static void n_1700_B(b_3528_u event) {
        MinecraftClient mc = MinecraftClient.A_4115_X();
        if (mc.Y_259_p == null || mc.Y_601_j == null) {
            return;
        }
        double nowSec = (double)System.currentTimeMillis() / 1000.0;
        Z_3822_q iconFont = l_3370_o.u_1723_Y[22];
        Z_3822_q textFont = l_3370_o.J_1907_R[12];
        String g = w_1484_f;
        int rgb = (Integer)D_1410_T.w_1484_f.J_1907_R();
        int iconColor = H_2506_c.n_1700_B(rgb, Math.min(1.0f, (float)H_2506_c.G_564_y(rgb) / 255.0f));
        int textColor = H_2506_c.n_1700_B(255, 255, 255, 230);
        for (G_1539_D.J_1907_R m : G_1539_D.t_148_a()) {
            e_2866_D world;
            if (m.v_4262_N <= nowSec || (world = Party.n_1700_B(mc, m, event.R_4764_Y(), nowSec)) == null) continue;
            Vector2f sp = v_2826_q.n_1700_B(world);
            if (sp.x == Float.MAX_VALUE || sp.y == Float.MAX_VALUE) continue;
            float iw = iconFont.n_1700_B(g);
            float ih = iconFont.h_1847_R();
            float ix = sp.x - iw / 2.0f;
            float iy = sp.y - ih / 2.0f;
            iconFont.n_1700_B(event.J_1907_R(), g, (double)ix, (double)iy, iconColor);
            float tw = textFont.n_1700_B(t_148_a);
            float tx = sp.x - tw / 2.0f;
            float ty = iy + ih + 3.0f;
            textFont.n_1700_B(event.J_1907_R(), t_148_a, (double)tx, (double)ty, textColor);
        }
    }

    private static e_2866_D n_1700_B(MinecraftClient mc, G_1539_D.J_1907_R m, float partialTicks, double nowSec) {
        if (m.v_4262_N <= nowSec) {
            return null;
        }
        if (m.R_4764_Y != null && !m.R_4764_Y.isEmpty()) {
            for (G_1539_D.n_1700_B rm : G_1539_D.w_1484_f()) {
                if (rm.n_1700_B == null || !rm.n_1700_B.equalsIgnoreCase(m.R_4764_Y) || !rm.J_1907_R) continue;
                return new e_2866_D(rm.R_4764_Y, rm.G_564_y + 2.2, rm.P_1922_E);
            }
            a_3913_L pl = Party.n_1700_B(mc, m.R_4764_Y);
            if (pl != null && pl.RealmsLongRunningMcoTaskScreen()) {
                double x = u_530_F.G_564_y((double)partialTicks, pl.q_1982_R, pl.O_3598_v());
                double y = u_530_F.G_564_y((double)partialTicks, pl.dtoRealmsServerAddress, pl.X_2960_b());
                double z = u_530_F.G_564_y((double)partialTicks, pl.w_612_n, pl.l_2647_k());
                double hh = pl.i_601_W().getYSize();
                return new e_2866_D(x, y + hh + 0.2, z);
            }
        }
        return new e_2866_D(m.G_564_y, m.P_1922_E, m.u_1723_Y);
    }

    private static a_3913_L n_1700_B(MinecraftClient mc, String name) {
        if (name == null || mc.Y_601_j == null) {
            return null;
        }
        for (a_3913_L a_3913_L2 : mc.Y_601_j.multiplayerClientSuggestionProvider()) {
            if (a_3913_L2 == null || a_3913_L2.y_4642_Y() == null || !name.equalsIgnoreCase(a_3913_L2.y_4642_Y().getName())) continue;
            return a_3913_L2;
        }
        return null;
    }

    private static n_1700_B n_1700_B(MinecraftClient mc, double maxDist) {
        float pt = mc.RealmsClientConfig();
        e_2866_D start = mc.Y_259_p.u_2550_I(pt);
        e_2866_D look = mc.Y_259_p.t_148_a(pt).G_564_y();
        e_2866_D end = start.P_1922_E(look.n_1700_B(maxDist));
        N_4263_v bestEnt = null;
        double bestEntDistSq = Double.MAX_VALUE;
        I_4817_s sweep = new I_4817_s(start, end).grow(1.0);
        for (N_4263_v entity : mc.Y_601_j.J_1907_R((N_4263_v)mc.Y_259_p, sweep, e -> Party.n_1700_B(mc, e))) {
            double d;
            Optional<e_2866_D> hit = entity.i_601_W().grow(0.12).rayTrace(start, end);
            if (!hit.isPresent() || !((d = start.v_4262_N(hit.get())) < bestEntDistSq)) continue;
            bestEntDistSq = d;
            bestEnt = entity;
        }
        ClipContext ctx = new ClipContext(start, end, ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, mc.Y_259_p);
        BlockHitResult blockHit = mc.Y_601_j.n_1700_B(ctx);
        e_2866_D blockVec = null;
        double blockDistSq = Double.MAX_VALUE;
        if (blockHit.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            blockVec = blockHit.P_1922_E();
            blockDistSq = start.v_4262_N(blockVec);
        }
        if (bestEnt != null && bestEntDistSq <= blockDistSq + 0.05) {
            return new n_1700_B(bestEnt, null);
        }
        if (blockVec != null) {
            return new n_1700_B(null, blockVec.J_1907_R(0.0, 0.08, 0.0));
        }
        return new n_1700_B(null, end);
    }

    private static boolean n_1700_B(MinecraftClient mc, N_4263_v e) {
        if (e == null || !e.RealmsLongRunningMcoTaskScreen() || e == mc.Y_259_p) {
            return false;
        }
        if (e instanceof D_686_b) {
            return false;
        }
        return e.C_290_v();
    }

    private static final class n_1700_B {
        final N_4263_v n_1700_B;
        final e_2866_D J_1907_R;

        n_1700_B(N_4263_v entity, e_2866_D worldPos) {
            this.n_1700_B = entity;
            this.J_1907_R = worldPos;
        }
    }
}



