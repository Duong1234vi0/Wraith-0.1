/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lightning.product.E_4612_l;
import lightning.product.E_4925_L;
import lightning.product.F_1446_q;
import lightning.product.F_489_x;
import lightning.product.TridentItem;
import lightning.product.NumberSetting;
import lightning.product.N_4263_v;
import lightning.product.MultiBooleanSetting;
import lightning.product.BowItem;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1630_j;
import lightning.product.a_1344_X;
import lightning.product.a_3913_L;
import lightning.product.b_3528_u;
import lightning.product.d_2169_p;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.h_384_L;
import lightning.product.i_2572_h;
import lightning.product.i_4434_b;
import lightning.product.ClientBootstrap;
import lightning.product.p_1183_T;
import lightning.product.BooleanSetting;
import lightning.product.p_863_D;
import lightning.product.q_1613_l;
import lightning.product.KeyBindSetting;
import lightning.product.Items;
import lightning.product.r_4790_y;
import lightning.product.r_4811_B;
import lightning.product.u_1934_K;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;

public class ItemRelease
extends Module {
    private final MultiBooleanSetting predmetyOptions = new MultiBooleanSetting("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", new BooleanSetting("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446", false), new BooleanSetting("\u041b\u0443\u043a", false), new BooleanSetting("\u0410\u0440\u0431\u0430\u043b\u0435\u0442", false));
    private final NumberSetting silaVystrelaSetting = new NumberSetting("\u0421\u0438\u043b\u0430 \u0432\u044b\u0441\u0442\u0440\u0435\u043b\u0430", 3.0f, 1.0f, 20.0f, 0.5f, () -> this.predmetyOptions.isOptionEnabled("\u041b\u0443\u043a"));
    private final BooleanSetting zvukPriPopadaniiEnabled = new BooleanSetting("\u0417\u0432\u0443\u043a \u043f\u0440\u0438 \u043f\u043e\u043f\u0430\u0434\u0430\u043d\u0438\u0438", false);
    private final KeyBindSetting knopkaBroskaTrezubcaKeyBind = new KeyBindSetting("\u041a\u043d\u043e\u043f\u043a\u0430 \u0431\u0440\u043e\u0441\u043a\u0430 \u0442\u0440\u0435\u0437\u0443\u0431\u0446\u0430", () -> this.predmetyOptions.isOptionEnabled("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446"));
    private final NumberSetting vremyaZaryadkiTrezubcaSetting = new NumberSetting("\u0412\u0440\u0435\u043c\u044f \u0437\u0430\u0440\u044f\u0434\u043a\u0438 \u0442\u0440\u0435\u0437\u0443\u0431\u0446\u0430", 10.0f, 10.0f, 20.0f, 1.0f, () -> this.predmetyOptions.isOptionEnabled("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446"));
    private final BooleanSetting aimAssistEnabled = new BooleanSetting("\u0410\u0438\u043c-\u0430\u0441\u0441\u0438\u0441\u0442", false);
    private final NumberSetting dalnostAimAssistaSetting = new NumberSetting("\u0414\u0430\u043b\u044c\u043d\u043e\u0441\u0442\u044c \u0430\u0438\u043c-\u0430\u0441\u0441\u0438\u0441\u0442\u0430", 50.0f, 10.0f, 100.0f, 1.0f, () -> this.aimAssistEnabled.isEnabled());
    private final NumberSetting radiusFovSetting = new NumberSetting("\u0420\u0430\u0434\u0438\u0443\u0441 FOV", 60.0f, 10.0f, 180.0f, 1.0f, () -> this.aimAssistEnabled.isEnabled());
    private final BooleanSetting otrisovyvatFovEnabled = new BooleanSetting("\u041e\u0442\u0440\u0438\u0441\u043e\u0432\u044b\u0432\u0430\u0442\u044c FOV", false, () -> this.aimAssistEnabled.isEnabled());
    private final BooleanSetting prediktCeliEnabled = new BooleanSetting("\u041f\u0440\u0435\u0434\u0438\u043a\u0442 \u0446\u0435\u043b\u0438", false, () -> this.aimAssistEnabled.isEnabled());
    private final BooleanSetting targetitBlizhayshuyuCelEnabled = new BooleanSetting("\u0422\u0430\u0440\u0433\u0435\u0442\u0438\u0442\u044c \u0431\u043b\u0438\u0436\u0430\u0439\u0448\u0443\u044e \u0446\u0435\u043b\u044c", false, () -> this.aimAssistEnabled.isEnabled());
    private final BooleanSetting neStrelyatEsliTargetZaStenoyEnabled = new BooleanSetting("\u041d\u0435 \u0441\u0442\u0440\u0435\u043b\u044f\u0442\u044c \u0435\u0441\u043b\u0438 \u0442\u0430\u0440\u0433\u0435\u0442 \u0437\u0430 \u0441\u0442\u0435\u043d\u043e\u0439", false, () -> this.aimAssistEnabled.isEnabled());
    private final MultiBooleanSetting celiAimAssistaOptions = new MultiBooleanSetting("\u0426\u0435\u043b\u0438 \u0430\u0438\u043c-\u0430\u0441\u0441\u0438\u0441\u0442\u0430", () -> this.aimAssistEnabled.isEnabled(), new BooleanSetting("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", true), new BooleanSetting("\u0414\u0440\u0443\u0437\u0435\u0439", false), new BooleanSetting("\u041c\u043e\u0431\u043e\u0432", false), new BooleanSetting("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445", false), new BooleanSetting("\u0416\u0438\u0442\u0435\u043b\u0435\u0439", false));
    private final Set<Integer> Y_601_j = new HashSet<Integer>();
    private final Set<Integer> Y_259_p = new HashSet<Integer>();
    private final V_4557_X Q_2552_b = new V_4557_X();
    private boolean C_2741_M;
    private int k_2293_S = -1;
    private int q_2307_F = -1;
    private int Z_875_P = 0;
    private int t_4043_B = 0;

    public ItemRelease() {
        super("ItemRelease", ModuleCategory.G_564_y);
        this.addSettings(this.predmetyOptions, this.silaVystrelaSetting, this.zvukPriPopadaniiEnabled, this.knopkaBroskaTrezubcaKeyBind, this.vremyaZaryadkiTrezubcaSetting, this.aimAssistEnabled, this.dalnostAimAssistaSetting, this.radiusFovSetting, this.otrisovyvatFovEnabled, this.prediktCeliEnabled, this.targetitBlizhayshuyuCelEnabled, this.neStrelyatEsliTargetZaStenoyEnabled, this.celiAimAssistaOptions);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (this.predmetyOptions.isOptionEnabled("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue() && ((Integer)this.knopkaBroskaTrezubcaKeyBind.getKey()).intValue() == e.n_1700_B() && e.J_1907_R() && !ItemRelease.c_3005_b.Y_259_p.p_1458_L().n_1700_B(Items.P_2605_j)) {
            int slot = u_1934_K.n_1700_B(Items.P_2605_j);
            if (slot != -1 && this.t_4043_B == 0) {
                this.C_2741_M = true;
                this.k_2293_S = slot;
                this.q_2307_F = ItemRelease.c_3005_b.Y_259_p.l_1268_F.G_564_y;
            }
        } else if (this.predmetyOptions.isOptionEnabled("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue() && ((Integer)this.knopkaBroskaTrezubcaKeyBind.getKey()).intValue() == e.n_1700_B() && !e.J_1907_R() && this.t_4043_B == 2) {
            ItemRelease.c_3005_b.w_1457_N.onStoppedUsingItem(ItemRelease.c_3005_b.Y_259_p);
            this.t_4043_B = 3;
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        r_4811_B target;
        if (this.predmetyOptions.isOptionEnabled("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue() && this.C_2741_M) {
            switch (this.t_4043_B) {
                case 0: {
                    if (this.k_2293_S < 9) {
                        ItemRelease.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.k_2293_S));
                        ItemRelease.c_3005_b.w_1457_N.syncCurrentPlayItem();
                    } else {
                        ItemRelease.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new i_2572_h(this.k_2293_S));
                    }
                    this.t_4043_B = 1;
                    break;
                }
                case 1: {
                    if (this.k_2293_S < 9) {
                        ItemRelease.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.k_2293_S;
                        ItemRelease.c_3005_b.w_1457_N.syncCurrentPlayItem();
                    }
                    ItemRelease.c_3005_b.w_1457_N.processRightClick(ItemRelease.c_3005_b.Y_259_p, ItemRelease.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
                    this.Z_875_P = 0;
                    this.t_4043_B = 2;
                    break;
                }
                case 2: {
                    ++this.Z_875_P;
                    if (this.Z_875_P < ((Float)this.vremyaZaryadkiTrezubcaSetting.getValue()).intValue()) break;
                    if (ItemRelease.c_3005_b.Y_259_p.Y_601_j()) {
                        ItemRelease.c_3005_b.w_1457_N.onStoppedUsingItem(ItemRelease.c_3005_b.Y_259_p);
                    }
                    this.t_4043_B = 3;
                    break;
                }
                case 3: {
                    if (this.k_2293_S < 9) {
                        ItemRelease.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.q_2307_F));
                        ItemRelease.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.q_2307_F;
                        ItemRelease.c_3005_b.w_1457_N.syncCurrentPlayItem();
                    } else {
                        ItemRelease.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new i_2572_h(this.k_2293_S));
                    }
                    this.multiplayerClientSuggestionProvider();
                }
            }
        }
        if (this.predmetyOptions.isOptionEnabled("\u041b\u0443\u043a").booleanValue() && ItemRelease.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof BowItem && ItemRelease.c_3005_b.Y_259_p.Y_601_j() && (float)ItemRelease.c_3005_b.Y_259_p.g_1031_K() >= ((Float)this.silaVystrelaSetting.getValue()).floatValue()) {
            if (this.M_182_A()) {
                return;
            }
            ItemRelease.c_3005_b.w_1457_N.onStoppedUsingItem(ItemRelease.c_3005_b.Y_259_p);
        }
        if (this.predmetyOptions.isOptionEnabled("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue() && this.t_4043_B == 0 && ItemRelease.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof TridentItem && ItemRelease.c_3005_b.Y_259_p.Y_601_j() && ItemRelease.c_3005_b.Y_259_p.g_1031_K() >= 10) {
            if (this.M_182_A()) {
                return;
            }
            ItemRelease.c_3005_b.w_1457_N.onStoppedUsingItem(ItemRelease.c_3005_b.Y_259_p);
        }
        if (this.predmetyOptions.isOptionEnabled("\u0410\u0440\u0431\u0430\u043b\u0435\u0442").booleanValue() && ItemRelease.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof Z_1630_j && ItemRelease.c_3005_b.Y_259_p.Y_601_j() && ItemRelease.c_3005_b.Y_259_p.g_1031_K() >= Z_1630_j.v_4262_N(ItemRelease.c_3005_b.Y_259_p.l_1268_F.R_4764_Y())) {
            if (this.M_182_A()) {
                return;
            }
            ItemRelease.c_3005_b.w_1457_N.onStoppedUsingItem(ItemRelease.c_3005_b.Y_259_p);
        }
        if (this.zvukPriPopadaniiEnabled.isEnabled().booleanValue() && ItemRelease.c_3005_b.Y_601_j != null) {
            this.h_1847_R();
        }
        if (this.aimAssistEnabled.isEnabled().booleanValue() && ItemRelease.c_3005_b.Y_259_p != null && ItemRelease.c_3005_b.Y_601_j != null && (ItemRelease.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof BowItem || ItemRelease.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof TridentItem || ItemRelease.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof Z_1630_j) && ItemRelease.c_3005_b.Y_259_p.Y_601_j() && (target = this.t_1786_h()) != null) {
            this.R_4764_Y(target);
        }
    }

    private void h_1847_R() {
        if (ItemRelease.c_3005_b.Y_259_p == null || ItemRelease.c_3005_b.Y_601_j == null) {
            return;
        }
        for (N_4263_v entity : ItemRelease.c_3005_b.Y_601_j.J_1907_R()) {
            E_4925_L trident;
            N_4263_v shooter;
            if (entity instanceof h_384_L) {
                h_384_L arrow = (h_384_L)entity;
                shooter = arrow.Y_601_j();
                if (shooter != ItemRelease.c_3005_b.Y_259_p || this.Y_601_j.contains(arrow.j_276_v())) continue;
                this.Y_601_j.add(arrow.j_276_v());
                continue;
            }
            if (!(entity instanceof E_4925_L) || (shooter = (trident = (E_4925_L)entity).Y_601_j()) != ItemRelease.c_3005_b.Y_259_p || this.Y_601_j.contains(trident.j_276_v())) continue;
            this.Y_601_j.add(trident.j_276_v());
        }
        this.Y_601_j.removeIf(projectileId -> {
            N_4263_v projectile = ItemRelease.c_3005_b.Y_601_j.J_1907_R((int)projectileId);
            if (projectile == null || !projectile.RealmsLongRunningMcoTaskScreen()) {
                return true;
            }
            for (N_4263_v entity : ItemRelease.c_3005_b.Y_601_j.J_1907_R()) {
                double speed;
                if (!(entity instanceof r_4811_B)) continue;
                r_4811_B living = (r_4811_B)entity;
                if (entity == ItemRelease.c_3005_b.Y_259_p || this.Y_259_p.contains(projectileId) || !projectile.i_601_W().intersects(entity.i_601_W()) || !((speed = projectile.I_4348_c().v_4262_N()) < 0.01) && living.RealmsLongRunningMcoTaskScreen <= 0) continue;
                this.Q_4569_t();
                this.Y_259_p.add((Integer)projectileId);
                return true;
            }
            double speed = projectile.I_4348_c().v_4262_N();
            return speed < 0.001;
        });
        this.Y_259_p.removeIf(id -> ItemRelease.c_3005_b.Y_601_j.J_1907_R((int)id) == null);
    }

    private void Q_4569_t() {
        if (this.Q_2552_b.J_1907_R(100L)) {
            p_863_D.n_1700_B("toggle");
            this.Q_2552_b.n_1700_B();
        }
    }

    private boolean M_182_A() {
        if (!this.aimAssistEnabled.isEnabled().booleanValue() || !this.neStrelyatEsliTargetZaStenoyEnabled.isEnabled().booleanValue() || ItemRelease.c_3005_b.Y_259_p == null) {
            return false;
        }
        r_4811_B target = this.t_1786_h();
        return target != null && !ItemRelease.c_3005_b.Y_259_p.c_3005_b(target);
    }

    private r_4811_B t_1786_h() {
        if (ItemRelease.c_3005_b.Y_259_p == null || ItemRelease.c_3005_b.Y_601_j == null) {
            return null;
        }
        try {
            List entities = StreamSupport.stream(ItemRelease.c_3005_b.Y_601_j.J_1907_R().spliterator(), false).collect(Collectors.toList());
            Comparator<r_4811_B> comparator = this.targetitBlizhayshuyuCelEnabled.isEnabled() != false ? Comparator.comparingDouble(entity -> entity.R_4764_Y(ItemRelease.c_3005_b.Y_259_p)) : Comparator.comparingDouble(this::n_1700_B);
            return entities.stream().filter(e -> e instanceof r_4811_B).map(e -> (r_4811_B)e).filter(this::J_1907_R).sorted(comparator).findFirst().orElse(null);
        }
        catch (Exception e2) {
            return null;
        }
    }

    private double n_1700_B(r_4811_B entity) {
        e_2866_D eyePos = ItemRelease.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D toTarget = entity.i_601_W().getCenter().G_564_y(eyePos).G_564_y();
        float realYaw = (d_2169_p.n_1700_B() ? d_2169_p.J_1907_R() : ItemRelease.c_3005_b.Y_259_p.p_178_J) * (float)Math.PI / 180.0f;
        float realPitch = (d_2169_p.n_1700_B() ? d_2169_p.R_4764_Y() : ItemRelease.c_3005_b.Y_259_p.f_4016_n) * (float)Math.PI / 180.0f;
        e_2866_D lookVec = new e_2866_D(-Math.sin(realYaw) * Math.cos(realPitch), -Math.sin(realPitch), Math.cos(realYaw) * Math.cos(realPitch)).G_564_y();
        double dot = toTarget.J_1907_R(lookVec);
        return Math.acos(u_530_F.n_1700_B(dot, -1.0, 1.0));
    }

    private boolean J_1907_R(r_4811_B entity) {
        if (entity == null || !entity.RealmsLongRunningMcoTaskScreen() || entity == ItemRelease.c_3005_b.Y_259_p) {
            return false;
        }
        double distance = a_1344_X.J_1907_R(entity).u_1723_Y();
        if (distance > (double)((Float)this.dalnostAimAssistaSetting.getValue()).floatValue()) {
            return false;
        }
        e_2866_D eyePos = ItemRelease.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D targetPos = entity.i_601_W().getCenter();
        e_2866_D toTarget = targetPos.G_564_y(eyePos).G_564_y();
        float realYaw = (d_2169_p.n_1700_B() ? d_2169_p.J_1907_R() : ItemRelease.c_3005_b.Y_259_p.p_178_J) * (float)Math.PI / 180.0f;
        float realPitch = (d_2169_p.n_1700_B() ? d_2169_p.R_4764_Y() : ItemRelease.c_3005_b.Y_259_p.f_4016_n) * (float)Math.PI / 180.0f;
        e_2866_D lookVec = new e_2866_D(-Math.sin(realYaw) * Math.cos(realPitch), -Math.sin(realPitch), Math.cos(realYaw) * Math.cos(realPitch)).G_564_y();
        double dot = toTarget.J_1907_R(lookVec);
        double angle = Math.toDegrees(Math.acos(u_530_F.n_1700_B(dot, -1.0, 1.0)));
        if (angle > (double)((Float)this.radiusFovSetting.getValue()).floatValue() / 2.0) {
            return false;
        }
        if (this.neStrelyatEsliTargetZaStenoyEnabled.isEnabled().booleanValue() && !ItemRelease.c_3005_b.Y_259_p.c_3005_b(entity)) {
            return false;
        }
        if (entity instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)entity;
            if (!this.celiAimAssistaOptions.isOptionEnabled("\u0414\u0440\u0443\u0437\u0435\u0439").booleanValue() && ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName())) {
                return false;
            }
            return E_4612_l.n_1700_B(entity, this.celiAimAssistaOptions, true);
        }
        return E_4612_l.R_4764_Y(entity, this.celiAimAssistaOptions) || E_4612_l.J_1907_R(entity, this.celiAimAssistaOptions) || E_4612_l.n_1700_B(entity, this.celiAimAssistaOptions);
    }

    private void R_4764_Y(r_4811_B target) {
        if (target == null || ItemRelease.c_3005_b.Y_259_p == null) {
            return;
        }
        float gravity = 0.05f;
        q_1613_l heldItem = ItemRelease.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R();
        float arrowSpeed = heldItem instanceof Z_1630_j ? 3.15f : (heldItem instanceof TridentItem ? 2.5f : 3.0f);
        e_2866_D eyePos = ItemRelease.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D targetPos = target.i_601_W().getCenter();
        if (this.prediktCeliEnabled.isEnabled().booleanValue()) {
            e_2866_D velocity = new e_2866_D(target.O_3598_v() - target.r_715_M, target.X_2960_b() - target.A_1038_p, target.l_2647_k() - target.i_1637_u);
            double distance = eyePos.u_1723_Y(targetPos);
            int flightTicks = this.n_1700_B(distance, arrowSpeed);
            e_2866_D predicted = targetPos.P_1922_E(velocity.n_1700_B((double)flightTicks));
            distance = eyePos.u_1723_Y(predicted);
            flightTicks = this.n_1700_B(distance, arrowSpeed);
            targetPos = targetPos.P_1922_E(velocity.n_1700_B((double)flightTicks));
        }
        e_2866_D diff = targetPos.G_564_y(eyePos);
        double hDist = Math.sqrt(diff.J_1907_R * diff.J_1907_R + diff.G_564_y * diff.G_564_y);
        float yaw = (float)(Math.toDegrees(Math.atan2(diff.G_564_y, diff.J_1907_R)) - 90.0);
        float pitch = this.n_1700_B(hDist, diff.R_4764_Y, arrowSpeed, gravity);
        float turnSpeed = ThreadLocalRandom.current().nextFloat(275.0f, 444.0f);
        r_4790_y.n_1700_B(new F_1446_q(yaw, pitch), turnSpeed, 1, 6);
    }

    private int n_1700_B(double distance, float arrowSpeed) {
        double speed = arrowSpeed;
        double traveled = 0.0;
        for (int tick = 1; tick <= 100; ++tick) {
            traveled += speed;
            speed *= 0.99;
            if (!(traveled >= distance)) continue;
            return tick;
        }
        return 100;
    }

    private float n_1700_B(double hDist, double vDist, float arrowSpeed, float gravity) {
        if (hDist < 0.5) {
            return (float)(-Math.toDegrees(Math.atan2(vDist, hDist)));
        }
        double adjustedVDist = vDist;
        float pitch = (float)(-Math.toDegrees(Math.atan2(adjustedVDist, hDist)));
        for (int iteration = 0; iteration < 4; ++iteration) {
            double error;
            double pitchRad = Math.toRadians(pitch);
            double hSpeed = (double)arrowSpeed * Math.cos(pitchRad);
            double vSpeed = (double)(-arrowSpeed) * Math.sin(pitchRad);
            double hTraveled = 0.0;
            double vTraveled = 0.0;
            for (int tick = 0; tick < 200; ++tick) {
                vTraveled += vSpeed;
                hSpeed *= 0.99;
                vSpeed *= 0.99;
                vSpeed -= (double)gravity;
                if ((hTraveled += hSpeed) >= hDist) break;
            }
            if (Math.abs(error = vDist - vTraveled) < 0.05) break;
            pitch = (float)(-Math.toDegrees(Math.atan2(adjustedVDist += error, hDist)));
        }
        return u_530_F.n_1700_B(pitch, -90.0f, 90.0f);
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u e) {
        if (!this.otrisovyvatFovEnabled.isEnabled().booleanValue() || !this.aimAssistEnabled.isEnabled().booleanValue() || ItemRelease.c_3005_b.Y_259_p == null) {
            return;
        }
        if (!(ItemRelease.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof BowItem || ItemRelease.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof TridentItem || ItemRelease.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof Z_1630_j)) {
            return;
        }
        float centerX = (float)c_3005_b.RealmsServerPing().Q_4569_t() / 2.0f;
        float centerY = (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f;
        double fov = ItemRelease.c_3005_b.s_956_w.n_1700_B(ItemRelease.c_3005_b.O_508_d().J_1907_R, c_3005_b.RealmsClientConfig(), true);
        float screenHeight = c_3005_b.RealmsServerPing().M_182_A();
        float halfHeight = screenHeight / 2.0f;
        float fovRadiusPixels = (float)((double)halfHeight * Math.tan(Math.toRadians((double)((Float)this.radiusFovSetting.getValue()).floatValue() / 2.0)) / Math.tan(Math.toRadians(fov / 2.0)));
        int color = -1;
        F_489_x.n_1700_B(centerX, centerY, fovRadiusPixels, color, 2.5f);
    }

    private void multiplayerClientSuggestionProvider() {
        this.C_2741_M = false;
        this.k_2293_S = -1;
        this.q_2307_F = -1;
        this.Z_875_P = 0;
        this.t_4043_B = 0;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (this.t_4043_B == 2 && ItemRelease.c_3005_b.Y_259_p != null && ItemRelease.c_3005_b.Y_259_p.Y_601_j()) {
            ItemRelease.c_3005_b.w_1457_N.onStoppedUsingItem(ItemRelease.c_3005_b.Y_259_p);
        }
        this.multiplayerClientSuggestionProvider();
    }
}



