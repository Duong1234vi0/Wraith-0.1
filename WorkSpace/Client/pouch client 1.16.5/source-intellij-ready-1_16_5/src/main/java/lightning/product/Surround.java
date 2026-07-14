/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lightning.product.Step;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.BlockHitResult;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.Q_2753_H;
import lightning.product.V_3354_l;
import lightning.product.Module;
import lightning.product.Y_1740_V;
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
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.v_1669_V;
import lightning.product.Speed;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;
import org.lwjgl.opengl.GL11;

public class Surround
extends Module {
    public final ModeSetting switchMode = new ModeSetting("Switch", "Silent", "None", "Normal", "Silent");
    public final ModeSetting timingMode = new ModeSetting("Timing", "Sequential", "Vanilla", "Sequential");
    public final NumberSetting limitSetting = new NumberSetting("Limit", 4.0f, 1.0f, 20.0f, 1.0f);
    public final NumberSetting delaySetting = new NumberSetting("Delay", 0.0f, 0.0f, 20.0f, 1.0f);
    public final NumberSetting rangeSetting = new NumberSetting("Range", 5.0f, 0.0f, 12.0f, 0.5f);
    public final BooleanSetting awaitEnabled = new BooleanSetting("Await", false);
    public final BooleanSetting rotateEnabled = new BooleanSetting("Rotate", true);
    public final BooleanSetting strictdirectionEnabled = new BooleanSetting("StrictDirection", false);
    public final BooleanSetting crystaldestructionEnabled = new BooleanSetting("CrystalDestruction", true);
    public final BooleanSetting centerEnabled = new BooleanSetting("Center", false);
    public final BooleanSetting floorEnabled = new BooleanSetting("Floor", true);
    public final BooleanSetting extensionEnabled = new BooleanSetting("Extension", true);
    public final BooleanSetting whileeatingEnabled = new BooleanSetting("WhileEating", true);
    public final BooleanSetting selfdisableEnabled = new BooleanSetting("SelfDisable", false);
    public final BooleanSetting jumpdisableEnabled = new BooleanSetting("JumpDisable", true);
    public final BooleanSetting itemdisableEnabled = new BooleanSetting("ItemDisable", true);
    public final BooleanSetting steptoggleEnabled = new BooleanSetting("StepToggle", false);
    public final BooleanSetting speedtoggleEnabled = new BooleanSetting("SpeedToggle", false);
    public final BooleanSetting renderEnabled = new BooleanSetting("Render", true);
    public final h_2367_h Z_875_P = new h_2367_h("RenderColor", true, new Color(0, 255, 0, 100).getRGB(), this.renderEnabled::isEnabled);
    private Set<c_1514_x> t_4043_B = new HashSet<c_1514_x>();
    private c_1514_x x_607_J = null;
    private int e_4240_b = 0;
    private int n_3318_d = 0;
    private P_3504_Q d_2427_y;
    private boolean z_1737_N = false;
    private c_1514_x v_4276_D;
    private b_257_Y d_2461_k;
    private boolean G_624_v = false;
    private final List<c_1514_x> T_2506_i = new ArrayList<c_1514_x>();
    private final List<c_1514_x> q_4610_l = new ArrayList<c_1514_x>();
    private int z_4693_k = -1;
    private int g_221_o = -1;

    public Surround() {
        super("Surround", ModuleCategory.n_1700_B);
        this.addSettings(this.switchMode, this.timingMode, this.limitSetting, this.delaySetting, this.rangeSetting, this.awaitEnabled, this.rotateEnabled, this.strictdirectionEnabled, this.crystaldestructionEnabled, this.centerEnabled, this.floorEnabled, this.extensionEnabled, this.whileeatingEnabled, this.selfdisableEnabled, this.jumpdisableEnabled, this.itemdisableEnabled, this.steptoggleEnabled, this.speedtoggleEnabled, this.renderEnabled, this.Z_875_P);
    }

    @Override
    public void onEnable() {
        Module speed;
        Module step;
        if (Surround.c_3005_b.Y_259_p == null || Surround.c_3005_b.Y_601_j == null) {
            return;
        }
        this.x_607_J = Surround.c_3005_b.Y_259_p.b_2312_j();
        if (this.steptoggleEnabled.isEnabled().booleanValue() && (step = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Step.class)) != null && step.w_1484_f()) {
            step.R_4764_Y();
        }
        if (this.speedtoggleEnabled.isEnabled().booleanValue() && (speed = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Speed.class)) != null && speed.w_1484_f()) {
            speed.R_4764_Y();
        }
        if (this.centerEnabled.isEnabled().booleanValue()) {
            Surround.c_3005_b.Y_259_p.J_1907_R((double)this.x_607_J.getX() + 0.5, this.x_607_J.getY(), (double)this.x_607_J.getZ() + 0.5);
        }
        this.v_4276_D = null;
        this.d_2461_k = null;
        this.G_624_v = false;
        this.T_2506_i.clear();
        this.q_4610_l.clear();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.x_607_J = null;
        this.t_4043_B.clear();
        this.e_4240_b = 0;
        this.n_3318_d = 0;
        this.z_1737_N = false;
        this.d_2427_y = null;
        this.v_4276_D = null;
        this.d_2461_k = null;
        this.G_624_v = false;
        this.T_2506_i.clear();
        this.q_4610_l.clear();
        this.z_4693_k = -1;
        this.g_221_o = -1;
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        if (this.z_1737_N && this.d_2427_y != null && this.rotateEnabled.isEnabled().booleanValue()) {
            e.n_1700_B(this.d_2427_y.t_148_a);
            e.J_1907_R(this.d_2427_y.s_956_w);
            Surround.c_3005_b.Y_259_p.f_3449_S = this.d_2427_y.t_148_a;
            Surround.c_3005_b.Y_259_p.C_1162_e = this.d_2427_y.t_148_a;
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (Surround.c_3005_b.Y_259_p == null || Surround.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.jumpdisableEnabled.isEnabled().booleanValue()) {
            boolean stepOrSpeed;
            if (Surround.c_3005_b.Y_259_p.U_1241_n > 2.0f) {
                this.R_4764_Y();
                return;
            }
            Module step = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Step.class);
            Module speed = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Speed.class);
            boolean bl = stepOrSpeed = step != null && step.w_1484_f() || speed != null && speed.w_1484_f();
            if (stepOrSpeed && this.x_607_J != null && this.x_607_J.getY() != Surround.c_3005_b.Y_259_p.b_2312_j().getY()) {
                this.R_4764_Y();
                return;
            }
        }
        if (!this.whileeatingEnabled.isEnabled().booleanValue() && Surround.c_3005_b.Y_259_p.Y_601_j()) {
            return;
        }
        if (this.e_4240_b < ((Float)this.delaySetting.getValue()).intValue()) {
            ++this.e_4240_b;
            return;
        }
        if (this.switchMode.isMode("None") && !(Surround.c_3005_b.Y_259_p.A_2714_y().J_1907_R() instanceof v_1669_V)) {
            if (this.itemdisableEnabled.isEnabled().booleanValue()) {
                this.R_4764_Y();
            }
            this.t_4043_B.clear();
            return;
        }
        int slot = this.M_182_A();
        int previousSlot = Surround.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        if (slot == -1) {
            if (this.itemdisableEnabled.isEnabled().booleanValue()) {
                this.R_4764_Y();
            }
            this.t_4043_B.clear();
            return;
        }
        this.t_4043_B = this.Q_4569_t();
        if (this.rotateEnabled.isEnabled().booleanValue()) {
            this.n_1700_B(slot, previousSlot);
        } else {
            this.J_1907_R(slot, previousSlot);
        }
    }

    private void n_1700_B(int slot, int previousSlot) {
        if (this.G_624_v && this.v_4276_D != null && this.d_2461_k != null) {
            if (this.crystaldestructionEnabled.isEnabled().booleanValue()) {
                I_4817_s box = new I_4817_s(this.v_4276_D);
                for (N_4263_v entity : Surround.c_3005_b.Y_601_j.n_1700_B((N_4263_v)null, box)) {
                    if (!(entity instanceof V_3354_l)) continue;
                    Surround.c_3005_b.w_1457_N.attackEntity(Surround.c_3005_b.Y_259_p, entity);
                    Surround.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                }
            }
            this.R_4764_Y(slot, previousSlot);
            c_1514_x supportPos = this.v_4276_D.offset(this.d_2461_k);
            BlockHitResult result = new BlockHitResult(e_2866_D.n_1700_B(supportPos).J_1907_R((double)this.d_2461_k.u_1723_Y().t_148_a() * 0.5, (double)this.d_2461_k.u_1723_Y().s_956_w() * 0.5, (double)this.d_2461_k.u_1723_Y().u_2550_I() * 0.5), this.d_2461_k.u_1723_Y(), supportPos, false);
            Surround.c_3005_b.w_1457_N.func_217292_a(Surround.c_3005_b.Y_259_p, Surround.c_3005_b.Y_601_j, x_1688_C.n_1700_B, result);
            Surround.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
            this.G_564_y(slot, previousSlot);
            this.q_4610_l.add(this.v_4276_D);
            this.v_4276_D = null;
            this.d_2461_k = null;
            this.G_624_v = false;
            ++this.n_3318_d;
            this.e_4240_b = 0;
            return;
        }
        if (this.T_2506_i.isEmpty()) {
            this.q_4610_l.clear();
            this.n_3318_d = 0;
            ArrayList<c_1514_x> positions = new ArrayList<c_1514_x>();
            for (c_1514_x pos : this.t_4043_B) {
                double dist = Surround.c_3005_b.Y_259_p.v_4262_N((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
                if (dist > (double)(((Float)this.rangeSetting.getValue()).floatValue() * ((Float)this.rangeSetting.getValue()).floatValue()) || !this.n_1700_B(pos)) continue;
                positions.add(pos);
            }
            if (positions.isEmpty()) {
                if (this.selfdisableEnabled.isEnabled().booleanValue()) {
                    this.R_4764_Y();
                }
                this.z_1737_N = false;
                return;
            }
            this.T_2506_i.addAll(positions);
        }
        while (!this.T_2506_i.isEmpty()) {
            if (this.n_3318_d >= ((Float)this.limitSetting.getValue()).intValue()) {
                this.T_2506_i.clear();
                this.z_1737_N = false;
                return;
            }
            c_1514_x pos = this.T_2506_i.remove(0);
            if (!this.n_1700_B(pos)) continue;
            b_257_Y dir = this.n_1700_B(pos, this.q_4610_l);
            if (dir == null) {
                b_257_Y supportDir;
                c_1514_x support = pos.down();
                if (!this.n_1700_B(support) || (supportDir = this.n_1700_B(support, this.q_4610_l)) == null) continue;
                this.T_2506_i.add(0, pos);
                this.n_1700_B(support, supportDir);
                return;
            }
            this.n_1700_B(pos, dir);
            return;
        }
        this.z_1737_N = false;
    }

    private void n_1700_B(c_1514_x pos, b_257_Y dir) {
        this.v_4276_D = pos;
        this.d_2461_k = dir;
        this.G_624_v = true;
        c_1514_x supportPos = pos.offset(dir);
        e_2866_D hitVec = e_2866_D.n_1700_B(supportPos).J_1907_R((double)dir.u_1723_Y().t_148_a() * 0.5, (double)dir.u_1723_Y().s_956_w() * 0.5, (double)dir.u_1723_Y().u_2550_I() * 0.5);
        this.n_1700_B(hitVec.J_1907_R, hitVec.R_4764_Y, hitVec.G_564_y);
    }

    private void J_1907_R(int slot, int previousSlot) {
        this.n_3318_d = 0;
        ArrayList<c_1514_x> positions = new ArrayList<c_1514_x>();
        for (c_1514_x pos : this.t_4043_B) {
            double dist = Surround.c_3005_b.Y_259_p.v_4262_N((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
            if (dist > (double)(((Float)this.rangeSetting.getValue()).floatValue() * ((Float)this.rangeSetting.getValue()).floatValue()) || !this.n_1700_B(pos)) continue;
            positions.add(pos);
        }
        if (positions.isEmpty()) {
            if (this.selfdisableEnabled.isEnabled().booleanValue()) {
                this.R_4764_Y();
            }
            this.z_1737_N = false;
            return;
        }
        this.R_4764_Y(slot, previousSlot);
        ArrayList<c_1514_x> placedPositions = new ArrayList<c_1514_x>();
        for (c_1514_x position : positions) {
            if (this.n_3318_d >= ((Float)this.limitSetting.getValue()).intValue()) break;
            b_257_Y direction = this.n_1700_B(position, placedPositions);
            if (direction == null) {
                b_257_Y supportDirection;
                c_1514_x supportPosition = position.down();
                if (!this.n_1700_B(supportPosition) || (supportDirection = this.n_1700_B(supportPosition, placedPositions)) == null) continue;
                this.J_1907_R(supportPosition, supportDirection);
                placedPositions.add(supportPosition);
                ++this.n_3318_d;
                if (this.n_3318_d >= ((Float)this.limitSetting.getValue()).intValue()) break;
                if (this.awaitEnabled.isEnabled().booleanValue() || (direction = this.n_1700_B(position, placedPositions)) == null) continue;
            }
            this.J_1907_R(position, direction);
            placedPositions.add(position);
            ++this.n_3318_d;
        }
        this.G_564_y(slot, previousSlot);
        this.e_4240_b = 0;
        this.z_1737_N = false;
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (Surround.c_3005_b.Y_259_p == null || Surround.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.timingMode.isMode("Sequential")) {
            return;
        }
        if (!e.J_1907_R()) {
            return;
        }
        if (e.G_564_y() instanceof ClientboundAddEntityPacket) {
            ClientboundAddEntityPacket packet = (ClientboundAddEntityPacket)e.G_564_y();
            if (packet.M_588_G() != t_5_h.w_1457_N) {
                return;
            }
            V_3354_l crystal = new V_3354_l(Surround.c_3005_b.Y_601_j, packet.G_564_y(), packet.P_1922_E(), packet.u_1723_Y());
            for (c_1514_x position : this.t_4043_B) {
                I_4817_s posBox = new I_4817_s(position);
                if (!posBox.intersects(crystal.i_601_W()) || !this.t_4043_B.contains(position)) continue;
                if (this.n_3318_d > ((Float)this.limitSetting.getValue()).intValue()) {
                    return;
                }
                if (!this.whileeatingEnabled.isEnabled().booleanValue() && Surround.c_3005_b.Y_259_p.Y_601_j()) {
                    return;
                }
                int slot = this.M_182_A();
                int previousSlot = Surround.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                if (slot == -1) {
                    return;
                }
                b_257_Y direction = this.J_1907_R(position);
                if (direction == null) {
                    return;
                }
                this.R_4764_Y(slot, previousSlot);
                Surround.c_3005_b.w_1457_N.attackEntity(Surround.c_3005_b.Y_259_p, crystal);
                Surround.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                this.J_1907_R(position, direction);
                ++this.n_3318_d;
                this.G_564_y(slot, previousSlot);
                break;
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (Surround.c_3005_b.Y_259_p == null || Surround.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.renderEnabled.isEnabled().booleanValue()) {
            return;
        }
        if (this.t_4043_B.isEmpty()) {
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
        int color = (Integer)this.Z_875_P.J_1907_R();
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r buffer = tessellator.R_4764_Y();
        for (c_1514_x pos : this.t_4043_B) {
            if (!this.n_1700_B(pos)) continue;
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

    private Set<c_1514_x> Q_4569_t() {
        HashSet<c_1514_x> positions = new HashSet<c_1514_x>();
        c_1514_x playerPos = Surround.c_3005_b.Y_259_p.b_2312_j();
        c_1514_x[] offsets = new c_1514_x[]{playerPos.add(1, 0, 0), playerPos.add(-1, 0, 0), playerPos.add(0, 0, 1), playerPos.add(0, 0, -1)};
        if (this.floorEnabled.isEnabled().booleanValue()) {
            positions.add(playerPos.down());
        }
        for (c_1514_x pos : offsets) {
            positions.add(pos);
            if (!this.extensionEnabled.isEnabled().booleanValue()) continue;
            I_4817_s box = new I_4817_s(pos);
            boolean hasEntity = false;
            for (N_4263_v entity : Surround.c_3005_b.Y_601_j.n_1700_B((N_4263_v)null, box)) {
                if (!(entity instanceof r_4811_B) || entity == Surround.c_3005_b.Y_259_p) continue;
                hasEntity = true;
                break;
            }
            if (!hasEntity) continue;
            int dx = pos.getX() - playerPos.getX();
            int dz = pos.getZ() - playerPos.getZ();
            c_1514_x extended = pos.add(dx, 0, dz);
            positions.add(extended);
            if (dx != 0) {
                positions.add(extended.add(0, 0, 1));
                positions.add(extended.add(0, 0, -1));
            }
            if (dz == 0) continue;
            positions.add(extended.add(1, 0, 0));
            positions.add(extended.add(-1, 0, 0));
        }
        return positions;
    }

    private boolean n_1700_B(c_1514_x pos) {
        return Surround.c_3005_b.Y_601_j.u_1723_Y(pos) || Surround.c_3005_b.Y_601_j.getBlockState(pos).R_4764_Y().P_1922_E();
    }

    private b_257_Y J_1907_R(c_1514_x pos) {
        return this.n_1700_B(pos, new ArrayList<c_1514_x>());
    }

    private b_257_Y n_1700_B(c_1514_x pos, List<c_1514_x> placed) {
        for (b_257_Y dir : b_257_Y.values()) {
            c_1514_x supportPos = pos.offset(dir);
            if (placed.contains(supportPos)) {
                return dir;
            }
            if (Surround.c_3005_b.Y_601_j.u_1723_Y(supportPos) || !Surround.c_3005_b.Y_601_j.getBlockState(supportPos).M_588_G()) continue;
            if (this.strictdirectionEnabled.isEnabled().booleanValue()) {
                e_2866_D playerPos = Surround.c_3005_b.Y_259_p.u_2550_I(1.0f);
                e_2866_D blockCenter = e_2866_D.n_1700_B(supportPos);
                e_2866_D dirVec = new e_2866_D(dir.u_1723_Y().t_148_a(), dir.u_1723_Y().s_956_w(), dir.u_1723_Y().u_2550_I());
                if (playerPos.G_564_y(blockCenter).J_1907_R(dirVec) <= 0.0) continue;
            }
            return dir;
        }
        return null;
    }

    private void J_1907_R(c_1514_x pos, b_257_Y dir) {
        if (this.crystaldestructionEnabled.isEnabled().booleanValue()) {
            I_4817_s box = new I_4817_s(pos);
            for (N_4263_v entity : Surround.c_3005_b.Y_601_j.n_1700_B((N_4263_v)null, box)) {
                if (!(entity instanceof V_3354_l)) continue;
                Surround.c_3005_b.w_1457_N.attackEntity(Surround.c_3005_b.Y_259_p, entity);
                Surround.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
            }
        }
        if (this.rotateEnabled.isEnabled().booleanValue()) {
            this.n_1700_B((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
        }
        c_1514_x supportPos = pos.offset(dir);
        BlockHitResult result = new BlockHitResult(e_2866_D.n_1700_B(supportPos).J_1907_R((double)dir.u_1723_Y().t_148_a() * 0.5, (double)dir.u_1723_Y().s_956_w() * 0.5, (double)dir.u_1723_Y().u_2550_I() * 0.5), dir.u_1723_Y(), supportPos, false);
        Surround.c_3005_b.w_1457_N.func_217292_a(Surround.c_3005_b.Y_259_p, Surround.c_3005_b.Y_601_j, x_1688_C.n_1700_B, result);
        Surround.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
    }

    private void n_1700_B(double x, double y, double z) {
        e_2866_D eyePos = Surround.c_3005_b.Y_259_p.u_2550_I(1.0f);
        double diffX = x - eyePos.J_1907_R;
        double diffY = y - eyePos.R_4764_Y;
        double diffZ = z - eyePos.G_564_y;
        float yaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, Math.hypot(diffX, diffZ))));
        this.d_2427_y = new P_3504_Q(yaw, u_530_F.n_1700_B(pitch, -90.0f, 90.0f));
        this.z_1737_N = true;
    }

    private int M_182_A() {
        q_1613_l item;
        int i;
        for (i = 0; i < 9; ++i) {
            item = Surround.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R();
            if (item != Items.d_2545_n && item != Items.N_260_m && item != Items.TallSeagrass) continue;
            return i;
        }
        for (i = 0; i < 9; ++i) {
            item = Surround.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R();
            if (!(item instanceof v_1669_V)) continue;
            return i;
        }
        return -1;
    }

    private void R_4764_Y(int slot, int previousSlot) {
        if (slot == previousSlot) {
            return;
        }
        switch ((String)this.switchMode.getValue()) {
            case "Normal": {
                Surround.c_3005_b.Y_259_p.l_1268_F.G_564_y = slot;
                break;
            }
            case "Silent": {
                Surround.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(slot));
            }
        }
    }

    private void G_564_y(int slot, int previousSlot) {
        if (slot == previousSlot) {
            return;
        }
        if (this.switchMode.isMode("Silent")) {
            Surround.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(previousSlot));
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

    public String h_1847_R() {
        return String.valueOf(this.t_4043_B.size());
    }
}



