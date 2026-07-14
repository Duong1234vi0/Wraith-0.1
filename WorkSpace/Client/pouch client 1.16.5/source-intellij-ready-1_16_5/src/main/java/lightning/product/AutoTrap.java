/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.F_1446_q;
import lightning.product.BlockHitResult;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.P_3504_Q;
import lightning.product.P_4526_H;
import lightning.product.Q_1939_l;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.i_4434_b;
import lightning.product.k_2603_m;
import lightning.product.l_3747_P;
import lightning.product.m_2262_U;
import lightning.product.ClientBootstrap;
import lightning.product.p_1183_T;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.KeyBindSetting;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.r_4790_y;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;
import org.lwjgl.opengl.GL11;

public class AutoTrap
extends Module {
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "\u041e\u0434\u0438\u043d\u043e\u0447\u043d\u044b\u0439", "\u041e\u0434\u0438\u043d\u043e\u0447\u043d\u044b\u0439", "\u041c\u0443\u043b\u044c\u0442\u0438");
    private final ModeSetting blocksMode = new ModeSetting("Blocks", "Cobweb", "Cobweb", "Obsidian", "Both");
    private final NumberSetting distanciyaSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 4.0f, 1.0f, 8.0f, 0.5f);
    private final BooleanSetting prygatDlyaVerhaEnabled = new BooleanSetting("\u041f\u0440\u044b\u0433\u0430\u0442\u044c \u0434\u043b\u044f \u0432\u0435\u0440\u0445\u0430", true, () -> this.blocksMode.isMode("Obsidian") || this.blocksMode.isMode("Both"));
    private final BooleanSetting podstavlyatObsidianEsliNetBlokaEnabled = new BooleanSetting("\u041f\u043e\u0434\u0441\u0442\u0430\u0432\u043b\u044f\u0442\u044c \u043e\u0431\u0441\u0438\u0434\u0438\u0430\u043d \u0435\u0441\u043b\u0438 \u043d\u0435\u0442 \u0431\u043b\u043e\u043a\u0430", true, () -> this.blocksMode.isMode("Cobweb") || this.blocksMode.isMode("Both"));
    private final BooleanSetting rabotatIzInventaryaEnabled = new BooleanSetting("\u0420\u0430\u0431\u043e\u0442\u0430\u0442\u044c \u0438\u0437 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044f", false);
    private final KeyBindSetting knopkaUstanovkiKeyBind = new KeyBindSetting("\u041a\u043d\u043e\u043f\u043a\u0430 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043a\u0438");
    private boolean h_1847_R = false;
    private final List<c_1514_x> Q_4569_t = new ArrayList<c_1514_x>();
    private final List<c_1514_x> M_182_A = new ArrayList<c_1514_x>();
    private final List<c_1514_x> t_1786_h = new ArrayList<c_1514_x>();
    private final List<c_1514_x> multiplayerClientSuggestionProvider = new ArrayList<c_1514_x>();
    private int w_1457_N = 0;
    private c_1514_x Y_601_j;
    private b_257_Y Y_259_p;
    private boolean Q_2552_b;
    private boolean C_2741_M = false;
    private boolean k_2293_S = false;
    private P_3504_Q q_2307_F;

    public AutoTrap() {
        super("AutoTrap", ModuleCategory.n_1700_B);
        this.addSettings(this.rezhimMode, this.blocksMode, this.distanciyaSetting, this.prygatDlyaVerhaEnabled, this.podstavlyatObsidianEsliNetBlokaEnabled, this.rabotatIzInventaryaEnabled, this.knopkaUstanovkiKeyBind);
    }

    @Override
    public void onDisable() {
        this.h_1847_R();
        this.h_1847_R = false;
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (AutoTrap.c_3005_b.Y_259_p == null || AutoTrap.c_3005_b.Y_1740_V != null) {
            return;
        }
        if ((Integer)this.knopkaUstanovkiKeyBind.getKey() != -1 && e.n_1700_B() == ((Integer)this.knopkaUstanovkiKeyBind.getKey()).intValue()) {
            this.h_1847_R = e.J_1907_R();
            if (!this.h_1847_R) {
                this.h_1847_R();
            }
        }
    }

    @Y_1740_V
    private void n_1700_B(m_2262_U e) {
        if (this.q_2307_F != null && this.C_2741_M) {
            e.n_1700_B(this.q_2307_F.t_148_a);
            e.J_1907_R(this.q_2307_F.s_956_w);
            AutoTrap.c_3005_b.Y_259_p.f_3449_S = this.q_2307_F.t_148_a;
            AutoTrap.c_3005_b.Y_259_p.C_1162_e = this.q_2307_F.t_148_a;
            AutoTrap.c_3005_b.Y_259_p.u_55_V = this.q_2307_F.s_956_w;
        }
    }

    private boolean n_1700_B(c_1514_x pos) {
        double eyeY = AutoTrap.c_3005_b.Y_259_p.X_2960_b() + (double)AutoTrap.c_3005_b.Y_259_p.X_1313_W();
        return (double)pos.getY() >= eyeY;
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (AutoTrap.c_3005_b.Y_259_p == null || AutoTrap.c_3005_b.Y_601_j == null) {
            this.h_1847_R();
            return;
        }
        if (!this.h_1847_R) {
            this.h_1847_R();
            return;
        }
        if (!this.Q_2552_b()) {
            this.h_1847_R();
            return;
        }
        if (this.C_2741_M && this.Y_601_j != null && this.Y_259_p != null) {
            if (this.k_2293_S) {
                if (!AutoTrap.c_3005_b.Y_259_p.M_1641_O() && AutoTrap.c_3005_b.Y_259_p.I_4348_c().R_4764_Y > 0.0) {
                    this.J_1907_R(this.Y_601_j, this.Y_259_p, this.Q_2552_b);
                    this.M_182_A.add(this.Y_601_j);
                    this.Y_601_j = null;
                    this.Y_259_p = null;
                    this.C_2741_M = false;
                    this.k_2293_S = false;
                    ++this.w_1457_N;
                } else if (AutoTrap.c_3005_b.Y_259_p.M_1641_O()) {
                    AutoTrap.c_3005_b.Y_259_p.e_837_t();
                }
                return;
            }
            this.J_1907_R(this.Y_601_j, this.Y_259_p, this.Q_2552_b);
            this.M_182_A.add(this.Y_601_j);
            this.Y_601_j = null;
            this.Y_259_p = null;
            this.C_2741_M = false;
            ++this.w_1457_N;
            return;
        }
        List<a_3913_L> targets = this.Q_4569_t();
        if (targets.isEmpty()) {
            this.h_1847_R();
            return;
        }
        if (this.Q_4569_t.isEmpty() || this.w_1457_N >= this.Q_4569_t.size()) {
            this.Q_4569_t.clear();
            this.M_182_A.clear();
            this.w_1457_N = 0;
            for (a_3913_L target : targets) {
                for (c_1514_x pos : this.n_1700_B(target)) {
                    if (this.Q_4569_t.contains(pos)) continue;
                    this.Q_4569_t.add(pos);
                }
            }
        }
        this.J_1907_R(targets);
        while (this.w_1457_N < this.Q_4569_t.size()) {
            c_1514_x pos = this.Q_4569_t.get(this.w_1457_N);
            if (!this.R_4764_Y(pos)) {
                ++this.w_1457_N;
                continue;
            }
            boolean isCobwebPos = this.n_1700_B(pos, targets);
            boolean cobweb = isCobwebPos && (this.blocksMode.isMode("Cobweb") || this.blocksMode.isMode("Both")) && this.multiplayerClientSuggestionProvider();
            b_257_Y dir = this.J_1907_R(pos);
            if (dir == null) {
                b_257_Y belowDir;
                c_1514_x below;
                boolean allowUnderObsidian;
                boolean bl = allowUnderObsidian = !cobweb || this.podstavlyatObsidianEsliNetBlokaEnabled.isEnabled() != false;
                if (allowUnderObsidian && this.R_4764_Y(below = pos.down()) && !this.M_182_A.contains(below) && (belowDir = this.J_1907_R(below)) != null) {
                    this.n_1700_B(below, belowDir, false);
                    this.Q_4569_t.add(this.w_1457_N, below);
                    return;
                }
                ++this.w_1457_N;
                continue;
            }
            this.k_2293_S = this.prygatDlyaVerhaEnabled.isEnabled() != false && !cobweb && this.n_1700_B(pos);
            this.n_1700_B(pos, dir, cobweb);
            if (this.k_2293_S && AutoTrap.c_3005_b.Y_259_p.M_1641_O()) {
                AutoTrap.c_3005_b.Y_259_p.e_837_t();
            }
            return;
        }
        this.Q_4569_t.clear();
        this.w_1457_N = 0;
        this.q_2307_F = null;
        this.t_1786_h.clear();
        this.multiplayerClientSuggestionProvider.clear();
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (AutoTrap.c_3005_b.Y_259_p == null || AutoTrap.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.h_1847_R) {
            return;
        }
        if (this.t_1786_h.isEmpty() && this.multiplayerClientSuggestionProvider.isEmpty()) {
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
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r buffer = tessellator.R_4764_Y();
        this.n_1700_B(this.t_1786_h, renderX, renderY, renderZ, -1606360833, tessellator, buffer);
        this.n_1700_B(this.multiplayerClientSuggestionProvider, renderX, renderY, renderZ, -1601949441, tessellator, buffer);
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    private void n_1700_B(c_1514_x pos, b_257_Y dir, boolean cobweb) {
        this.Y_601_j = pos;
        this.Y_259_p = dir;
        this.Q_2552_b = cobweb;
        this.C_2741_M = true;
        c_1514_x supportPos = pos.offset(dir);
        e_2866_D hitVec = e_2866_D.n_1700_B(supportPos).J_1907_R((double)dir.u_1723_Y().t_148_a() * 0.5, (double)dir.u_1723_Y().s_956_w() * 0.5, (double)dir.u_1723_Y().u_2550_I() * 0.5);
        this.n_1700_B(hitVec);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void J_1907_R(c_1514_x pos, b_257_Y dir, boolean cobweb) {
        int originalSlot = AutoTrap.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        int targetSlot = -1;
        boolean didInventorySwap = false;
        int invIndex = -1;
        int swapHotbar = -1;
        if (cobweb) {
            hotbarSlot = this.M_182_A();
            if (hotbarSlot != -1) {
                targetSlot = hotbarSlot;
            } else {
                if (!this.rabotatIzInventaryaEnabled.isEnabled().booleanValue()) return;
                invIndex = this.t_1786_h();
                if (invIndex == -1) {
                    return;
                }
                swapHotbar = this.J_1907_R(originalSlot);
                this.n_1700_B(invIndex, swapHotbar);
                targetSlot = swapHotbar;
                didInventorySwap = true;
            }
        } else {
            hotbarSlot = this.w_1457_N();
            if (hotbarSlot != -1) {
                targetSlot = hotbarSlot;
            } else {
                if (!this.rabotatIzInventaryaEnabled.isEnabled().booleanValue()) return;
                invIndex = this.Y_601_j();
                if (invIndex == -1) {
                    return;
                }
                swapHotbar = this.J_1907_R(originalSlot);
                this.n_1700_B(invIndex, swapHotbar);
                targetSlot = swapHotbar;
                didInventorySwap = true;
            }
        }
        if (targetSlot != originalSlot) {
            AutoTrap.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(targetSlot));
        }
        c_1514_x supportPos = pos.offset(dir);
        BlockHitResult result = new BlockHitResult(e_2866_D.n_1700_B(supportPos).J_1907_R((double)dir.u_1723_Y().t_148_a() * 0.5, (double)dir.u_1723_Y().s_956_w() * 0.5, (double)dir.u_1723_Y().u_2550_I() * 0.5), dir.u_1723_Y(), supportPos, false);
        AutoTrap.c_3005_b.w_1457_N.func_217292_a(AutoTrap.c_3005_b.Y_259_p, AutoTrap.c_3005_b.Y_601_j, x_1688_C.n_1700_B, result);
        AutoTrap.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
        if (targetSlot != originalSlot) {
            AutoTrap.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(originalSlot));
        }
        if (!didInventorySwap) return;
        this.n_1700_B(invIndex, swapHotbar);
    }

    private int J_1907_R(int currentSlot) {
        return (currentSlot + 1) % 9;
    }

    private void n_1700_B(int inventoryIndex, int hotbarSlot) {
        c_3005_b.n_1700_B(new Q_1939_l(AutoTrap.c_3005_b.Y_259_p));
        int containerSlot = inventoryIndex < 9 ? inventoryIndex + 36 : inventoryIndex;
        AutoTrap.c_3005_b.w_1457_N.windowClick(AutoTrap.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, containerSlot, hotbarSlot, a_408_T.R_4764_Y, AutoTrap.c_3005_b.Y_259_p);
        AutoTrap.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(AutoTrap.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
        c_3005_b.n_1700_B((k_2603_m)null);
    }

    private void n_1700_B(e_2866_D target) {
        e_2866_D eyePos = AutoTrap.c_3005_b.Y_259_p.u_2550_I(1.0f);
        double diffX = target.J_1907_R - eyePos.J_1907_R;
        double diffY = target.R_4764_Y - eyePos.R_4764_Y;
        double diffZ = target.G_564_y - eyePos.G_564_y;
        float yaw = u_530_F.v_4262_N((float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f);
        float pitch = u_530_F.n_1700_B((float)(-Math.toDegrees(Math.atan2(diffY, Math.hypot(diffX, diffZ)))), -90.0f, 90.0f);
        float gcd = this.C_2741_M();
        if (this.q_2307_F != null) {
            yaw -= (yaw - this.q_2307_F.t_148_a) % gcd;
            pitch -= (pitch - this.q_2307_F.s_956_w) % gcd;
        }
        this.q_2307_F = new P_3504_Q(yaw, pitch);
        r_4790_y.n_1700_B(new F_1446_q(yaw, pitch), 120.0f, 120.0f, 0, 6);
    }

    private b_257_Y J_1907_R(c_1514_x pos) {
        for (c_1514_x placed : this.M_182_A) {
            for (b_257_Y dir : b_257_Y.values()) {
                if (!pos.offset(dir).equals(placed)) continue;
                return dir;
            }
        }
        for (b_257_Y dir : b_257_Y.values()) {
            c_1514_x neighbor = pos.offset(dir);
            if (AutoTrap.c_3005_b.Y_601_j.u_1723_Y(neighbor) || !AutoTrap.c_3005_b.Y_601_j.getBlockState(neighbor).M_588_G() && AutoTrap.c_3005_b.Y_601_j.getBlockState(neighbor).J_1907_R() != a_3742_W.y_1700_S) continue;
            return dir;
        }
        return null;
    }

    private boolean R_4764_Y(c_1514_x pos) {
        return AutoTrap.c_3005_b.Y_601_j.getBlockState(pos).v_4262_N() || AutoTrap.c_3005_b.Y_601_j.getBlockState(pos).R_4764_Y().P_1922_E();
    }

    private List<c_1514_x> n_1700_B(r_4811_B target) {
        c_1514_x feet = new c_1514_x(target.s_4990_V());
        c_1514_x head = feet.up();
        c_1514_x top = head.up();
        ArrayList<c_1514_x> queue = new ArrayList<c_1514_x>();
        if (this.blocksMode.isMode("Cobweb") || this.blocksMode.isMode("Both")) {
            if (this.R_4764_Y(feet)) {
                queue.add(feet);
            }
            if (this.R_4764_Y(head)) {
                queue.add(head);
            }
        }
        if (this.blocksMode.isMode("Obsidian") || this.blocksMode.isMode("Both")) {
            c_1514_x aboveTop = top.up();
            ArrayList<c_1514_x> obsidianPositions = new ArrayList<c_1514_x>();
            obsidianPositions.add(feet.north());
            obsidianPositions.add(feet.south());
            obsidianPositions.add(feet.east());
            obsidianPositions.add(feet.west());
            obsidianPositions.add(head.north());
            obsidianPositions.add(head.south());
            obsidianPositions.add(head.east());
            obsidianPositions.add(head.west());
            obsidianPositions.add(top);
            obsidianPositions.add(aboveTop);
            for (c_1514_x pos : obsidianPositions) {
                if (!this.R_4764_Y(pos) || queue.contains(pos)) continue;
                queue.add(pos);
            }
        }
        return queue;
    }

    private boolean n_1700_B(c_1514_x pos, List<a_3913_L> targets) {
        for (a_3913_L target : targets) {
            c_1514_x feet = new c_1514_x(target.s_4990_V());
            if (!pos.equals(feet) && !pos.equals(feet.up())) continue;
            return true;
        }
        return false;
    }

    private void J_1907_R(List<a_3913_L> targets) {
        this.t_1786_h.clear();
        this.multiplayerClientSuggestionProvider.clear();
        boolean canPlaceCobweb = (this.blocksMode.isMode("Cobweb") || this.blocksMode.isMode("Both")) && this.multiplayerClientSuggestionProvider();
        boolean canPlaceObsidian = (this.blocksMode.isMode("Obsidian") || this.blocksMode.isMode("Both")) && this.Y_259_p();
        for (int i = this.w_1457_N; i < this.Q_4569_t.size(); ++i) {
            boolean willUseCobweb;
            c_1514_x pos = this.Q_4569_t.get(i);
            if (!this.R_4764_Y(pos)) continue;
            boolean cobwebTarget = this.n_1700_B(pos, targets);
            boolean bl = willUseCobweb = cobwebTarget && canPlaceCobweb;
            if (willUseCobweb) {
                if (this.t_1786_h.contains(pos)) continue;
                this.t_1786_h.add(pos);
                continue;
            }
            if (!canPlaceObsidian || this.multiplayerClientSuggestionProvider.contains(pos)) continue;
            this.multiplayerClientSuggestionProvider.add(pos);
        }
        if (this.C_2741_M && this.Y_601_j != null) {
            if (this.Q_2552_b) {
                if (!this.t_1786_h.contains(this.Y_601_j)) {
                    this.t_1786_h.add(this.Y_601_j);
                }
            } else if (!this.multiplayerClientSuggestionProvider.contains(this.Y_601_j)) {
                this.multiplayerClientSuggestionProvider.add(this.Y_601_j);
            }
        }
    }

    private void n_1700_B(List<c_1514_x> queue, double renderX, double renderY, double renderZ, int color, l_3747_P tessellator, D_3318_r buffer) {
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        for (c_1514_x pos : queue) {
            I_4817_s box = new I_4817_s(pos).offset(-renderX, -renderY, -renderZ);
            buffer.n_1700_B(7, E_688_b.Y_601_j);
            this.n_1700_B(buffer, box, r, g, b, a * 0.25f);
            tessellator.J_1907_R();
            c_4037_x.G_564_y(2.0f);
            GL11.glEnable((int)2848);
            buffer.n_1700_B(1, E_688_b.Y_601_j);
            this.J_1907_R(buffer, box, r, g, b, a);
            tessellator.J_1907_R();
            GL11.glDisable((int)2848);
        }
    }

    private void h_1847_R() {
        this.q_2307_F = null;
        this.C_2741_M = false;
        this.k_2293_S = false;
        this.Y_601_j = null;
        this.Y_259_p = null;
        this.Q_4569_t.clear();
        this.M_182_A.clear();
        this.t_1786_h.clear();
        this.multiplayerClientSuggestionProvider.clear();
        this.w_1457_N = 0;
    }

    private List<a_3913_L> Q_4569_t() {
        e_2866_D playerPos = AutoTrap.c_3005_b.Y_259_p.s_4990_V();
        double radius = ((Float)this.distanciyaSetting.getValue()).floatValue();
        I_4817_s searchBox = new I_4817_s(playerPos.J_1907_R - radius, playerPos.R_4764_Y - radius, playerPos.G_564_y - radius, playerPos.J_1907_R + radius, playerPos.R_4764_Y + radius, playerPos.G_564_y + radius);
        List<a_3913_L> all = AutoTrap.c_3005_b.Y_601_j.n_1700_B(a_3913_L.class, searchBox, (? super T entity) -> entity.RealmsLongRunningMcoTaskScreen() && entity != AutoTrap.c_3005_b.Y_259_p && !ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(entity.O_1309_Q().getString()));
        all.sort(Comparator.comparingDouble(e -> e.s_4990_V().v_4262_N(playerPos)));
        if (this.rezhimMode.isMode("\u041e\u0434\u0438\u043d\u043e\u0447\u043d\u044b\u0439")) {
            return all.isEmpty() ? List.of() : List.of(all.get(0));
        }
        return all;
    }

    private boolean n_1700_B(q_1613_l item) {
        return item == Items.ValueObject;
    }

    private boolean J_1907_R(q_1613_l item) {
        return item == Items.d_2545_n || item == Items.TallSeagrass;
    }

    private int M_182_A() {
        for (int i = 0; i < 9; ++i) {
            if (!this.n_1700_B(AutoTrap.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R())) continue;
            return i;
        }
        return -1;
    }

    private int t_1786_h() {
        for (int i = 9; i < 36; ++i) {
            if (!this.n_1700_B(AutoTrap.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R())) continue;
            return i;
        }
        return -1;
    }

    private boolean multiplayerClientSuggestionProvider() {
        return this.M_182_A() != -1 || this.t_1786_h() != -1;
    }

    private int w_1457_N() {
        for (int i = 0; i < 9; ++i) {
            if (!this.J_1907_R(AutoTrap.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R())) continue;
            return i;
        }
        return -1;
    }

    private int Y_601_j() {
        for (int i = 9; i < 36; ++i) {
            if (!this.J_1907_R(AutoTrap.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R())) continue;
            return i;
        }
        return -1;
    }

    private boolean Y_259_p() {
        return this.w_1457_N() != -1 || this.Y_601_j() != -1;
    }

    private boolean Q_2552_b() {
        if (this.blocksMode.isMode("Cobweb")) {
            return this.multiplayerClientSuggestionProvider();
        }
        if (this.blocksMode.isMode("Obsidian")) {
            return this.Y_259_p();
        }
        return this.multiplayerClientSuggestionProvider() || this.Y_259_p();
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

    private float C_2741_M() {
        float sensitivity = (float)(AutoTrap.c_3005_b.P_4830_p.n_1700_B * (double)0.6f + (double)0.2f);
        float gcd = sensitivity * sensitivity * sensitivity * 8.0f;
        return gcd * 0.15f;
    }
}



