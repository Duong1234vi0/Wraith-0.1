/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.H_2506_c;
import lightning.product.HitResult;
import lightning.product.NumberSetting;
import lightning.product.K_1200_E;
import lightning.product.N_4263_v;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.b_3528_u;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.h_2367_h;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.n_1494_c;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;
import org.lwjgl.opengl.GL11;

public class Arrows
extends Module {
    private final MultiBooleanSetting otobrazhatOptions = new MultiBooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c", new BooleanSetting("\u0418\u0433\u0440\u043e\u043a\u0438", true), new BooleanSetting("\u0414\u0440\u0443\u0437\u044c\u044f", true), new BooleanSetting("\u0413\u043e\u043b\u044b\u0435", false), new BooleanSetting("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", false), new BooleanSetting("\u041a\u043e\u043c\u043d\u0430\u0442\u0430", true));
    private final ModeSetting cvetMode = new ModeSetting("\u0426\u0432\u0435\u0442", "\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441", "\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441", "\u0421\u0432\u043e\u0439");
    private final h_2367_h t_148_a = new h_2367_h("\u0426\u0432\u0435\u0442 \u0438\u0433\u0440\u043e\u043a\u043e\u0432", true, -1, () -> this.cvetMode.isMode("\u0421\u0432\u043e\u0439") && (this.h_1847_R() || this.M_182_A()));
    private final h_2367_h s_956_w = new h_2367_h("\u0426\u0432\u0435\u0442 \u0434\u0440\u0443\u0437\u0435\u0439", true, H_2506_c.n_1700_B(0, 255, 0), () -> this.cvetMode.isMode("\u0421\u0432\u043e\u0439") && this.Q_4569_t());
    private final h_2367_h u_2550_I = new h_2367_h("\u0426\u0432\u0435\u0442 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432", true, H_2506_c.n_1700_B(255, 0, 0), () -> this.cvetMode.isMode("\u0421\u0432\u043e\u0439") && this.t_1786_h());
    private final NumberSetting radiusSetting = new NumberSetting("\u0420\u0430\u0434\u0438\u0443\u0441", 40.0f, 1.0f, 100.0f, 1.0f);
    private final BooleanSetting pokazyvatTolkoEsliNeVidishEnabled = new BooleanSetting("\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u0435\u0441\u043b\u0438 \u043d\u0435 \u0432\u0438\u0434\u0438\u0448\u044c", false);
    private final Animation h_1847_R = new Animation(40.0f, 4.0f, Easing.A_4115_X);

    public Arrows() {
        super("Arrows", ModuleCategory.R_4764_Y);
        this.addSettings(this.otobrazhatOptions, this.cvetMode, this.t_148_a, this.s_956_w, this.u_2550_I, this.pokazyvatTolkoEsliNeVidishEnabled, this.radiusSetting);
    }

    private boolean h_1847_R() {
        return Boolean.TRUE.equals(this.otobrazhatOptions.isOptionEnabled("\u0418\u0433\u0440\u043e\u043a\u0438"));
    }

    private boolean Q_4569_t() {
        return Boolean.TRUE.equals(this.otobrazhatOptions.isOptionEnabled("\u0414\u0440\u0443\u0437\u044c\u044f"));
    }

    private boolean M_182_A() {
        return Boolean.TRUE.equals(this.otobrazhatOptions.isOptionEnabled("\u0413\u043e\u043b\u044b\u0435"));
    }

    private boolean t_1786_h() {
        return Boolean.TRUE.equals(this.otobrazhatOptions.isOptionEnabled("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b"));
    }

    private boolean multiplayerClientSuggestionProvider() {
        return Boolean.TRUE.equals(this.otobrazhatOptions.isOptionEnabled("\u041a\u043e\u043c\u043d\u0430\u0442\u0430"));
    }

    @Y_1740_V
    private void n_1700_B(b_3528_u e) {
        if (Arrows.c_3005_b.P_4830_p.r_3651_U) {
            return;
        }
        float centerX = (float)c_3005_b.RealmsServerPing().Q_4569_t() / 2.0f;
        float centerY = (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f;
        float maxRadius = Math.min(centerX, centerY);
        float percent = 18.0f + 82.0f * (((Float)this.radiusSetting.getValue()).floatValue() - 1.0f) / 99.0f;
        float targetRadius = maxRadius * (percent / 100.0f);
        this.h_1847_R.n_1700_B(targetRadius);
        float animatedRadius = this.h_1847_R.n_1700_B();
        float yawRadians = (float)Math.toRadians(Arrows.c_3005_b.s_956_w.M_588_G().P_1922_E());
        double cos = u_530_F.J_1907_R(yawRadians);
        double sin = u_530_F.n_1700_B(yawRadians);
        for (N_4263_v entity : Arrows.c_3005_b.Y_601_j.J_1907_R()) {
            if (!this.n_1700_B(entity)) continue;
            double adjustedRadius = entity instanceof n_1494_c ? (double)Math.max(animatedRadius - 20.0f, 0.0f) : (double)animatedRadius;
            e_2866_D vector3d = Arrows.c_3005_b.s_956_w.M_588_G().J_1907_R();
            double xDiff = entity.q_1982_R + (entity.O_3598_v() - entity.q_1982_R) * (double)c_3005_b.RealmsClientConfig() - vector3d.J_1907_R;
            double zDiff = entity.w_612_n + (entity.l_2647_k() - entity.w_612_n) * (double)c_3005_b.RealmsClientConfig() - vector3d.G_564_y;
            double rotationY = -(zDiff * cos - xDiff * sin);
            double rotationX = -(xDiff * cos + zDiff * sin);
            float angle = (float)Math.toDegrees(Math.atan2(rotationY, rotationX));
            double x = adjustedRadius * (double)u_530_F.J_1907_R((float)Math.toRadians(angle)) + (double)centerX;
            double y = adjustedRadius * (double)u_530_F.n_1700_B((float)Math.toRadians(angle)) + (double)centerY;
            GL11.glPushMatrix();
            GL11.glTranslated((double)x, (double)y, (double)0.0);
            GL11.glRotated((double)(angle + 90.0f), (double)0.0, (double)0.0, (double)1.0);
            int selectedColor = this.R_4764_Y(entity);
            F_489_x.n_1700_B(new g_2336_b("Pouch/icons/world_render/arrow.png"), -9.0f, 10.0f, 18.0f, 20.0f, selectedColor);
            GL11.glPopMatrix();
        }
    }

    private boolean n_1700_B(N_4263_v entity) {
        boolean isPlayerOrItem = this.G_564_y(entity);
        if (!isPlayerOrItem || !entity.RealmsLongRunningMcoTaskScreen() || entity == Arrows.c_3005_b.Y_259_p) {
            return false;
        }
        return this.pokazyvatTolkoEsliNeVidishEnabled.isEnabled() == false || !this.J_1907_R(entity);
    }

    private boolean J_1907_R(N_4263_v entity) {
        e_2866_D end;
        e_2866_D start = Arrows.c_3005_b.Y_259_p.u_2550_I(c_3005_b.RealmsClientConfig());
        ClipContext context = new ClipContext(start, end = entity.s_4990_V().J_1907_R(0.0, entity.v_165_F() / 2.0f, 0.0), ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, Arrows.c_3005_b.Y_259_p);
        BlockHitResult result = Arrows.c_3005_b.Y_601_j.n_1700_B(context);
        return ((HitResult)result).R_4764_Y() == HitResult.n_1700_B.n_1700_B;
    }

    private int R_4764_Y(N_4263_v entity) {
        boolean useInterfaceColor = this.cvetMode.isMode("\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441");
        int interfaceColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        if (entity instanceof a_3913_L) {
            boolean isNaked;
            a_3913_L player = (a_3913_L)entity;
            boolean isFriend = ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName());
            boolean bl = isNaked = player.E_3343_g() == 0;
            if (isFriend && this.Q_4569_t()) {
                return useInterfaceColor ? H_2506_c.n_1700_B(0, 255, 0) : (Integer)this.s_956_w.J_1907_R();
            }
            if (isNaked && this.M_182_A()) {
                return useInterfaceColor ? interfaceColor : (Integer)this.t_148_a.J_1907_R();
            }
            if (this.h_1847_R()) {
                return useInterfaceColor ? interfaceColor : (Integer)this.t_148_a.J_1907_R();
            }
            return useInterfaceColor ? interfaceColor : (Integer)this.t_148_a.J_1907_R();
        }
        if (entity instanceof n_1494_c && this.t_1786_h()) {
            return useInterfaceColor ? interfaceColor : (Integer)this.u_2550_I.J_1907_R();
        }
        return useInterfaceColor ? interfaceColor : (Integer)this.t_148_a.J_1907_R();
    }

    private boolean G_564_y(N_4263_v entity) {
        if (entity instanceof a_3913_L) {
            boolean isNaked;
            a_3913_L player = (a_3913_L)entity;
            boolean isFriend = ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName());
            boolean bl = isNaked = player.E_3343_g() == 0;
            if (isFriend) {
                return this.Q_4569_t();
            }
            if (isNaked) {
                return this.M_182_A();
            }
            return this.h_1847_R();
        }
        if (entity instanceof n_1494_c) {
            return this.t_1786_h();
        }
        return false;
    }
}



