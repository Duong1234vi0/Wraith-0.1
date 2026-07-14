/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.BlockHitResult;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.T_2915_h;
import lightning.product.V_3354_l;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.l_3747_P;
import lightning.product.m_2262_U;
import lightning.product.ClientBootstrap;
import lightning.product.p_1183_T;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.u_530_F;
import lightning.product.v_1669_V;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;
import org.lwjgl.opengl.GL11;

public class HoleFill
extends Module {
    private final ModeSetting avtoSvitchMode = new ModeSetting("\u0410\u0432\u0442\u043e-\u0441\u0432\u0438\u0442\u0447", "Silent", "None", "Normal", "Silent");
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "Smart", "Normal", "Smart");
    private final BooleanSetting pautinaEnabled = new BooleanSetting("\u041f\u0430\u0443\u0442\u0438\u043d\u0430", false);
    private final BooleanSetting asinhronnoEnabled = new BooleanSetting("\u0410\u0441\u0438\u043d\u0445\u0440\u043e\u043d\u043d\u043e", true);
    private final NumberSetting blokovTikSetting = new NumberSetting("\u0411\u043b\u043e\u043a\u043e\u0432/\u0442\u0438\u043a", 1.0f, 1.0f, 20.0f, 1.0f);
    private final NumberSetting zaderzhkaSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 0.0f, 0.0f, 20.0f, 1.0f);
    private final NumberSetting distanciyaSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 5.0f, 0.0f, 12.0f, 0.5f);
    private final NumberSetting distanciyaVragaSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0432\u0440\u0430\u0433\u0430", 8.0f, 0.0f, 16.0f, 0.5f);
    private final NumberSetting smartDistanciyaSetting = new NumberSetting("Smart \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 3.0f, 0.0f, 6.0f, 0.5f);
    private final BooleanSetting bezopasnostEnabled = new BooleanSetting("\u0411\u0435\u0437\u043e\u043f\u0430\u0441\u043d\u043e\u0441\u0442\u044c", true);
    private final NumberSetting bezopasnayaDistanciyaSetting = new NumberSetting("\u0411\u0435\u0437\u043e\u043f\u0430\u0441\u043d\u0430\u044f \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 2.0f, 0.0f, 6.0f, 0.5f);
    private final BooleanSetting rotationEnabled = new BooleanSetting("\u0420\u043e\u0442\u0430\u0446\u0438\u044f", true);
    private final BooleanSetting strogoeNapravlenieEnabled = new BooleanSetting("\u0421\u0442\u0440\u043e\u0433\u043e\u0435 \u043d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435", false);
    private final BooleanSetting lomatKristallyEnabled = new BooleanSetting("\u041b\u043e\u043c\u0430\u0442\u044c \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b", true);
    private final BooleanSetting dvoynyeDyryEnabled = new BooleanSetting("\u0414\u0432\u043e\u0439\u043d\u044b\u0435 \u0434\u044b\u0440\u044b", false);
    private final BooleanSetting proverkaDyryEnabled = new BooleanSetting("\u041f\u0440\u043e\u0432\u0435\u0440\u043a\u0430 \u0434\u044b\u0440\u044b", true);
    private final BooleanSetting priEdeEnabled = new BooleanSetting("\u041f\u0440\u0438 \u0435\u0434\u0435", true);
    private final BooleanSetting avtoVyklEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e-\u0432\u044b\u043a\u043b.", false);
    private final BooleanSetting vyklBezBlokovEnabled = new BooleanSetting("\u0412\u044b\u043a\u043b. \u0431\u0435\u0437 \u0431\u043b\u043e\u043a\u043e\u0432", true);
    private final BooleanSetting otrisovkaEnabled = new BooleanSetting("\u041e\u0442\u0440\u0438\u0441\u043e\u0432\u043a\u0430", true);
    private final h_2367_h t_4043_B = new h_2367_h("\u0426\u0432\u0435\u0442", true, new Color(255, 100, 0, 100).getRGB(), this.otrisovkaEnabled::isEnabled);
    private final ExecutorService x_607_J = Executors.newSingleThreadExecutor();
    private List<c_1514_x> e_4240_b = new ArrayList<c_1514_x>();
    private P_3504_Q n_3318_d;
    private boolean d_2427_y = false;
    private int z_1737_N = 0;
    private int v_4276_D = 0;

    public HoleFill() {
        super("HoleFill", ModuleCategory.n_1700_B);
        this.addSettings(this.avtoSvitchMode, this.rezhimMode, this.pautinaEnabled, this.asinhronnoEnabled, this.blokovTikSetting, this.zaderzhkaSetting, this.distanciyaSetting, this.distanciyaVragaSetting, this.smartDistanciyaSetting, this.bezopasnostEnabled, this.bezopasnayaDistanciyaSetting, this.rotationEnabled, this.strogoeNapravlenieEnabled, this.lomatKristallyEnabled, this.dvoynyeDyryEnabled, this.proverkaDyryEnabled, this.priEdeEnabled, this.avtoVyklEnabled, this.vyklBezBlokovEnabled, this.otrisovkaEnabled, this.t_4043_B);
    }

    @Override
    public void onDisable() {
        this.e_4240_b.clear();
        this.n_3318_d = null;
        this.d_2427_y = false;
        this.z_1737_N = 0;
        this.v_4276_D = 0;
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        if (this.d_2427_y && this.n_3318_d != null && this.rotationEnabled.isEnabled().booleanValue()) {
            e.n_1700_B(this.n_3318_d.t_148_a);
            e.J_1907_R(this.n_3318_d.s_956_w);
            HoleFill.c_3005_b.Y_259_p.f_3449_S = this.n_3318_d.t_148_a;
            HoleFill.c_3005_b.Y_259_p.C_1162_e = this.n_3318_d.t_148_a;
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (HoleFill.c_3005_b.Y_259_p == null || HoleFill.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.priEdeEnabled.isEnabled().booleanValue() && HoleFill.c_3005_b.Y_259_p.Y_601_j()) {
            return;
        }
        Runnable runnable = () -> {
            n_1700_B target;
            boolean needBlock;
            this.v_4276_D = 0;
            if (this.z_1737_N < ((Float)this.zaderzhkaSetting.getValue()).intValue()) {
                ++this.z_1737_N;
                return;
            }
            boolean bl = this.pautinaEnabled.isEnabled().booleanValue() ? HoleFill.c_3005_b.Y_259_p.A_2714_y().J_1907_R() != Items.ValueObject : (needBlock = !(HoleFill.c_3005_b.Y_259_p.A_2714_y().J_1907_R() instanceof v_1669_V));
            if (this.avtoSvitchMode.isMode("None") && needBlock) {
                if (this.vyklBezBlokovEnabled.isEnabled().booleanValue()) {
                    this.R_4764_Y();
                }
                this.e_4240_b = new ArrayList<c_1514_x>();
                return;
            }
            int slot = this.Q_4569_t();
            int previousSlot = HoleFill.c_3005_b.Y_259_p.l_1268_F.G_564_y;
            if (slot == -1) {
                if (this.vyklBezBlokovEnabled.isEnabled().booleanValue()) {
                    this.R_4764_Y();
                }
                this.e_4240_b = new ArrayList<c_1514_x>();
                return;
            }
            this.e_4240_b = this.rezhimMode.isMode("Smart") ? ((target = this.h_1847_R()) == null ? new ArrayList<c_1514_x>() : target.J_1907_R) : this.n_1700_B((a_3913_L)null);
            if (this.e_4240_b.isEmpty()) {
                if (this.avtoVyklEnabled.isEnabled().booleanValue()) {
                    this.R_4764_Y();
                }
                return;
            }
            c_3005_b.execute(() -> {
                this.n_1700_B(slot, previousSlot);
                for (c_1514_x position : this.e_4240_b) {
                    if (this.v_4276_D >= ((Float)this.blokovTikSetting.getValue()).intValue()) break;
                    b_257_Y direction = this.G_564_y(position);
                    if (direction == null) continue;
                    this.n_1700_B(position, direction);
                    ++this.v_4276_D;
                }
                this.J_1907_R(slot, previousSlot);
            });
            this.z_1737_N = 0;
        };
        if (this.asinhronnoEnabled.isEnabled().booleanValue()) {
            this.x_607_J.submit(runnable);
        } else {
            runnable.run();
        }
        this.d_2427_y = false;
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (HoleFill.c_3005_b.Y_259_p == null || HoleFill.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.otrisovkaEnabled.isEnabled().booleanValue()) {
            return;
        }
        if (this.e_4240_b.isEmpty()) {
            return;
        }
        double renderX = c_3005_b.O_508_d().renderPosX();
        double renderY = c_3005_b.O_508_d().renderPosY();
        double renderZ = c_3005_b.O_508_d().renderPosZ();
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.t_1786_h();
        c_4037_x.e_4240_b();
        c_4037_x.q_2307_F();
        int color = (Integer)this.t_4043_B.J_1907_R();
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r buffer = tessellator.R_4764_Y();
        for (c_1514_x pos : this.e_4240_b) {
            I_4817_s box = new I_4817_s(pos);
            I_4817_s renderBox = box.offset(-renderX, -renderY, -renderZ);
            buffer.n_1700_B(7, E_688_b.Y_601_j);
            this.n_1700_B(buffer, renderBox, r, g, b, a * 0.3f);
            tessellator.J_1907_R();
            c_4037_x.G_564_y(2.0f);
            GL11.glEnable((int)2848);
            buffer.n_1700_B(1, E_688_b.Y_601_j);
            this.J_1907_R(buffer, renderBox, r, g, b, a);
            tessellator.J_1907_R();
            GL11.glDisable((int)2848);
        }
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    private n_1700_B h_1847_R() {
        n_1700_B optimalTarget = null;
        for (a_3913_L a_3913_L2 : HoleFill.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider()) {
            List<c_1514_x> positions;
            if (a_3913_L2 == HoleFill.c_3005_b.Y_259_p || !a_3913_L2.RealmsLongRunningMcoTaskScreen() || a_3913_L2.g_46_E() <= 0.0f || HoleFill.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)a_3913_L2) > ((Float)this.distanciyaVragaSetting.getValue()).floatValue() || ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(a_3913_L2.y_4642_Y().getName()) || this.proverkaDyryEnabled.isEnabled().booleanValue() && this.J_1907_R(a_3913_L2) || (positions = this.n_1700_B(a_3913_L2)).isEmpty()) continue;
            if (optimalTarget == null) {
                optimalTarget = new n_1700_B(a_3913_L2, positions);
                continue;
            }
            if (!(HoleFill.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)a_3913_L2) < HoleFill.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)optimalTarget.n_1700_B))) continue;
            optimalTarget = new n_1700_B(a_3913_L2, positions);
        }
        return optimalTarget;
    }

    private List<c_1514_x> n_1700_B(a_3913_L player) {
        ArrayList<c_1514_x> positions = new ArrayList<c_1514_x>();
        c_1514_x playerPos = HoleFill.c_3005_b.Y_259_p.b_2312_j();
        int rangeInt = (int)Math.ceil(((Float)this.distanciyaSetting.getValue()).doubleValue());
        for (int x = -rangeInt; x <= rangeInt; ++x) {
            for (int y = -2; y <= 1; ++y) {
                for (int z = -rangeInt; z <= rangeInt; ++z) {
                    c_1514_x position = playerPos.add(x, y, z);
                    e_2866_D vec = e_2866_D.n_1700_B(position);
                    if (!HoleFill.c_3005_b.Y_601_j.getBlockState(position).R_4764_Y().P_1922_E() || HoleFill.c_3005_b.Y_259_p.v_4262_N(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y) > (double)(((Float)this.distanciyaSetting.getValue()).floatValue() * ((Float)this.distanciyaSetting.getValue()).floatValue()) || this.rezhimMode.isMode("Smart") && player != null && player.v_4262_N(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y) > (double)(((Float)this.smartDistanciyaSetting.getValue()).floatValue() * ((Float)this.smartDistanciyaSetting.getValue()).floatValue()) || this.bezopasnostEnabled.isEnabled().booleanValue() && !this.J_1907_R(HoleFill.c_3005_b.Y_259_p) && HoleFill.c_3005_b.Y_259_p.v_4262_N(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y) <= (double)(((Float)this.bezopasnayaDistanciyaSetting.getValue()).floatValue() * ((Float)this.bezopasnayaDistanciyaSetting.getValue()).floatValue()) || !this.n_1700_B(position) && (!this.dvoynyeDyryEnabled.isEnabled().booleanValue() || !this.J_1907_R(position)) || !this.R_4764_Y(position)) continue;
                    positions.add(position);
                }
            }
        }
        positions.sort(Comparator.comparingDouble(pos -> HoleFill.c_3005_b.Y_259_p.v_4262_N((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5)));
        return positions;
    }

    private boolean n_1700_B(c_1514_x pos) {
        if (!HoleFill.c_3005_b.Y_601_j.u_1723_Y(pos)) {
            return false;
        }
        T_2915_h below = HoleFill.c_3005_b.Y_601_j.getBlockState(pos.down()).J_1907_R();
        if (below != a_3742_W.ClientBootstrap && below != a_3742_W.Z_875_P) {
            return false;
        }
        int solidSides = 0;
        for (b_257_Y dir : b_257_Y.R_4764_Y.n_1700_B) {
            T_2915_h side = HoleFill.c_3005_b.Y_601_j.getBlockState(pos.offset(dir)).J_1907_R();
            if (side != a_3742_W.ClientBootstrap && side != a_3742_W.Z_875_P) continue;
            ++solidSides;
        }
        return solidSides >= 4;
    }

    private boolean J_1907_R(c_1514_x pos) {
        if (!HoleFill.c_3005_b.Y_601_j.u_1723_Y(pos)) {
            return false;
        }
        T_2915_h below = HoleFill.c_3005_b.Y_601_j.getBlockState(pos.down()).J_1907_R();
        if (below != a_3742_W.ClientBootstrap && below != a_3742_W.Z_875_P) {
            return false;
        }
        for (b_257_Y dir : b_257_Y.R_4764_Y.n_1700_B) {
            T_2915_h adjacentBelow;
            c_1514_x adjacent = pos.offset(dir);
            if (!HoleFill.c_3005_b.Y_601_j.u_1723_Y(adjacent) || (adjacentBelow = HoleFill.c_3005_b.Y_601_j.getBlockState(adjacent.down()).J_1907_R()) != a_3742_W.ClientBootstrap && adjacentBelow != a_3742_W.Z_875_P) continue;
            int solidSides = 0;
            for (b_257_Y checkDir : b_257_Y.R_4764_Y.n_1700_B) {
                if (checkDir == dir.u_1723_Y()) continue;
                T_2915_h side1 = HoleFill.c_3005_b.Y_601_j.getBlockState(pos.offset(checkDir)).J_1907_R();
                T_2915_h side2 = HoleFill.c_3005_b.Y_601_j.getBlockState(adjacent.offset(checkDir)).J_1907_R();
                if (side1 != a_3742_W.ClientBootstrap && side1 != a_3742_W.Z_875_P || side2 != a_3742_W.ClientBootstrap && side2 != a_3742_W.Z_875_P) continue;
                ++solidSides;
            }
            T_2915_h end1 = HoleFill.c_3005_b.Y_601_j.getBlockState(pos.offset(dir.u_1723_Y())).J_1907_R();
            T_2915_h end2 = HoleFill.c_3005_b.Y_601_j.getBlockState(adjacent.offset(dir)).J_1907_R();
            if (!(end1 != a_3742_W.ClientBootstrap && end1 != a_3742_W.Z_875_P || end2 != a_3742_W.ClientBootstrap && end2 != a_3742_W.Z_875_P)) {
                ++solidSides;
            }
            if (solidSides < 3) continue;
            return true;
        }
        return false;
    }

    private boolean J_1907_R(a_3913_L player) {
        c_1514_x pos = player.b_2312_j();
        return this.n_1700_B(pos);
    }

    private boolean R_4764_Y(c_1514_x pos) {
        return this.G_564_y(pos) != null;
    }

    private b_257_Y G_564_y(c_1514_x pos) {
        for (b_257_Y dir : b_257_Y.values()) {
            c_1514_x supportPos = pos.offset(dir);
            if (HoleFill.c_3005_b.Y_601_j.u_1723_Y(supportPos) || !HoleFill.c_3005_b.Y_601_j.getBlockState(supportPos).M_588_G()) continue;
            if (this.strogoeNapravlenieEnabled.isEnabled().booleanValue()) {
                e_2866_D playerPos = HoleFill.c_3005_b.Y_259_p.u_2550_I(1.0f);
                e_2866_D blockCenter = e_2866_D.n_1700_B(supportPos);
                e_2866_D dirVec = new e_2866_D(dir.u_1723_Y().t_148_a(), dir.u_1723_Y().s_956_w(), dir.u_1723_Y().u_2550_I());
                if (playerPos.G_564_y(blockCenter).J_1907_R(dirVec) <= 0.0) continue;
            }
            return dir;
        }
        return null;
    }

    private void n_1700_B(c_1514_x pos, b_257_Y dir) {
        if (this.lomatKristallyEnabled.isEnabled().booleanValue()) {
            I_4817_s box = new I_4817_s(pos);
            for (N_4263_v entity : HoleFill.c_3005_b.Y_601_j.n_1700_B((N_4263_v)null, box)) {
                if (!(entity instanceof V_3354_l)) continue;
                HoleFill.c_3005_b.w_1457_N.attackEntity(HoleFill.c_3005_b.Y_259_p, entity);
                HoleFill.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
            }
        }
        if (this.rotationEnabled.isEnabled().booleanValue()) {
            this.n_1700_B((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
        }
        c_1514_x supportPos = pos.offset(dir);
        BlockHitResult result = new BlockHitResult(e_2866_D.n_1700_B(supportPos).J_1907_R((double)dir.u_1723_Y().t_148_a() * 0.5, (double)dir.u_1723_Y().s_956_w() * 0.5, (double)dir.u_1723_Y().u_2550_I() * 0.5), dir.u_1723_Y(), supportPos, false);
        HoleFill.c_3005_b.w_1457_N.func_217292_a(HoleFill.c_3005_b.Y_259_p, HoleFill.c_3005_b.Y_601_j, x_1688_C.n_1700_B, result);
        HoleFill.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
    }

    private void n_1700_B(double x, double y, double z) {
        e_2866_D eyePos = HoleFill.c_3005_b.Y_259_p.u_2550_I(1.0f);
        double diffX = x - eyePos.J_1907_R;
        double diffY = y - eyePos.R_4764_Y;
        double diffZ = z - eyePos.G_564_y;
        float yaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, Math.hypot(diffX, diffZ))));
        this.n_3318_d = new P_3504_Q(yaw, u_530_F.n_1700_B(pitch, -90.0f, 90.0f));
        this.d_2427_y = true;
    }

    private int Q_4569_t() {
        if (this.pautinaEnabled.isEnabled().booleanValue()) {
            for (int i = 0; i < 9; ++i) {
                if (HoleFill.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != Items.ValueObject) continue;
                return i;
            }
        } else {
            q_1613_l item;
            int i;
            for (i = 0; i < 9; ++i) {
                item = HoleFill.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R();
                if (item != Items.d_2545_n && item != Items.N_260_m && item != Items.TallSeagrass) continue;
                return i;
            }
            for (i = 0; i < 9; ++i) {
                item = HoleFill.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R();
                if (!(item instanceof v_1669_V)) continue;
                return i;
            }
        }
        return -1;
    }

    private void n_1700_B(int slot, int previousSlot) {
        if (slot == previousSlot) {
            return;
        }
        switch ((String)this.avtoSvitchMode.getValue()) {
            case "Normal": {
                HoleFill.c_3005_b.Y_259_p.l_1268_F.G_564_y = slot;
                break;
            }
            case "Silent": {
                HoleFill.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(slot));
            }
        }
    }

    private void J_1907_R(int slot, int previousSlot) {
        if (slot == previousSlot) {
            return;
        }
        switch ((String)this.avtoSvitchMode.getValue()) {
            case "Normal": {
                break;
            }
            case "Silent": {
                HoleFill.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(previousSlot));
            }
        }
    }

    private void n_1700_B(D_3318_r buffer, I_4817_s box, float r, float g, float b, float a) {
        float x0 = (float)box.minX;
        float x1 = (float)box.maxX;
        float y0 = (float)box.minY;
        float y1 = (float)box.maxY;
        float z0 = (float)box.minZ;
        float z1 = (float)box.maxZ;
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
    }

    private void J_1907_R(D_3318_r buffer, I_4817_s box, float r, float g, float b, float a) {
        float x0 = (float)box.minX;
        float x1 = (float)box.maxX;
        float y0 = (float)box.minY;
        float y1 = (float)box.maxY;
        float z0 = (float)box.minZ;
        float z1 = (float)box.maxZ;
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
    }

    private static class n_1700_B {
        a_3913_L n_1700_B;
        List<c_1514_x> J_1907_R;

        n_1700_B(a_3913_L player, List<c_1514_x> positions) {
            this.n_1700_B = player;
            this.J_1907_R = positions;
        }
    }
}



