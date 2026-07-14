/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.util.List;
import lightning.product.E_4612_l;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.BlockESP;
import lightning.product.I_4477_R;
import lightning.product.NumberSetting;
import lightning.product.N_4263_v;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.h_2367_h;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.ModuleCategory;
import org.lwjgl.opengl.GL11;

public class Tracers
extends Module {
    private final MultiBooleanSetting otobrazhatOptions = new MultiBooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c", new BooleanSetting("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", true), new BooleanSetting("\u041c\u043e\u043d\u0441\u0442\u0440\u043e\u0432", false), new BooleanSetting("\u0414\u0440\u0443\u0437\u0435\u0439", true), new BooleanSetting("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445", false), new BooleanSetting("\u0416\u0438\u0442\u0435\u043b\u0435\u0439", false), new BooleanSetting("\u0413\u043e\u043b\u044b\u0445", false));
    private final h_2367_h w_1484_f = new h_2367_h("\u0426\u0432\u0435\u0442", true, -1);
    private final h_2367_h t_148_a = new h_2367_h("\u0426\u0432\u0435\u0442 \u0434\u0440\u0443\u0437\u0435\u0439", true, H_2506_c.n_1700_B(0, 255, 0), () -> this.otobrazhatOptions.isOptionEnabled("\u0414\u0440\u0443\u0437\u0435\u0439"));
    private final NumberSetting tolschinaLiniiSetting = new NumberSetting("\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u043b\u0438\u043d\u0438\u0438", 1.0f, 0.5f, 5.0f, 0.1f);
    private final BooleanSetting otrisovyvatKCelyamBlockespEnabled = new BooleanSetting("\u041e\u0442\u0440\u0438\u0441\u043e\u0432\u044b\u0432\u0430\u0442\u044c \u043a \u0446\u0435\u043b\u044f\u043c BlockESP", false);
    private final BooleanSetting otobrazhatIgrokovTolkoVNezeritovoyBroneEnabled = new BooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0438\u0433\u0440\u043e\u043a\u043e\u0432 \u0442\u043e\u043b\u044c\u043a\u043e \u0432 \u043d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u043e\u0439 \u0431\u0440\u043e\u043d\u0435", false, () -> this.otobrazhatOptions.isOptionEnabled("\u0418\u0433\u0440\u043e\u043a\u043e\u0432"));

    public Tracers() {
        super("Tracers", ModuleCategory.R_4764_Y);
        this.addSettings(this.otobrazhatIgrokovTolkoVNezeritovoyBroneEnabled, this.otobrazhatOptions, this.tolschinaLiniiSetting, this.w_1484_f, this.t_148_a, this.otrisovyvatKCelyamBlockespEnabled);
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R e) {
        List<c_1514_x> positions;
        BlockESP blockESP;
        GL11.glPushMatrix();
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)((Float)this.tolschinaLiniiSetting.getValue()).floatValue());
        e_2866_D cam = new e_2866_D(0.0, 0.0, 150.0).n_1700_B((float)(-Math.toRadians(Tracers.c_3005_b.O_508_d().J_1907_R.G_564_y()))).J_1907_R((float)(-Math.toRadians(Tracers.c_3005_b.O_508_d().J_1907_R.P_1922_E())));
        for (N_4263_v entity : Tracers.c_3005_b.Y_601_j.J_1907_R()) {
            if (!this.R_4764_Y(entity) || !(entity instanceof r_4811_B) || entity == Tracers.c_3005_b.Y_259_p) continue;
            e_2866_D pos = this.J_1907_R(entity).G_564_y(Tracers.c_3005_b.O_508_d().J_1907_R.J_1907_R());
            boolean isFriend = entity instanceof a_3913_L && ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(((a_3913_L)entity).y_4642_Y().getName());
            int selectedColor = isFriend ? (Integer)this.t_148_a.J_1907_R() : (Integer)this.w_1484_f.J_1907_R();
            float r = (float)(selectedColor >> 16 & 0xFF) / 255.0f;
            float g = (float)(selectedColor >> 8 & 0xFF) / 255.0f;
            float b = (float)(selectedColor & 0xFF) / 255.0f;
            float a = (float)(selectedColor >> 24 & 0xFF) / 255.0f;
            c_4037_x.G_564_y(r, g, b, a);
            A_4115_X.n_1700_B(1, E_688_b.w_1457_N);
            A_4115_X.pos(cam.J_1907_R, cam.R_4764_Y, cam.G_564_y).endVertex();
            A_4115_X.pos(pos.J_1907_R, pos.R_4764_Y, pos.G_564_y).endVertex();
            Y_1740_V.J_1907_R();
        }
        if (this.otrisovyvatKCelyamBlockespEnabled.isEnabled().booleanValue() && (blockESP = (BlockESP)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BlockESP.class)) != null && blockESP.w_1484_f() && !(positions = blockESP.h_1847_R()).isEmpty()) {
            e_2866_D cameraView = Tracers.c_3005_b.O_508_d().J_1907_R.J_1907_R();
            int selectedColor = (Integer)this.w_1484_f.J_1907_R();
            float r = (float)(selectedColor >> 16 & 0xFF) / 255.0f;
            float g = (float)(selectedColor >> 8 & 0xFF) / 255.0f;
            float b = (float)(selectedColor & 0xFF) / 255.0f;
            float a = (float)(selectedColor >> 24 & 0xFF) / 255.0f;
            c_4037_x.G_564_y(r, g, b, a);
            for (c_1514_x blockPos : positions) {
                e_2866_D worldPos = new e_2866_D((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5);
                e_2866_D relPos = worldPos.G_564_y(cameraView);
                A_4115_X.n_1700_B(1, E_688_b.w_1457_N);
                A_4115_X.pos(cam.J_1907_R, cam.R_4764_Y, cam.G_564_y).endVertex();
                A_4115_X.pos(relPos.J_1907_R, relPos.R_4764_Y, relPos.G_564_y).endVertex();
                Y_1740_V.J_1907_R();
            }
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glPopMatrix();
    }

    public e_2866_D n_1700_B(N_4263_v entity) {
        return new e_2866_D(entity.r_715_M, entity.A_1038_p, entity.i_1637_u);
    }

    public e_2866_D J_1907_R(N_4263_v entity) {
        e_2866_D prev = this.n_1700_B(entity);
        return prev.P_1922_E(entity.s_4990_V().G_564_y(prev).n_1700_B((double)c_3005_b.RealmsClientConfig()));
    }

    private boolean R_4764_Y(N_4263_v entity) {
        if (entity instanceof r_4811_B) {
            r_4811_B living = (r_4811_B)entity;
            if (entity != Tracers.c_3005_b.Y_259_p) {
                if (E_4612_l.n_1700_B(living, this.otobrazhatOptions, true) && (!this.otobrazhatIgrokovTolkoVNezeritovoyBroneEnabled.isEnabled().booleanValue() || this.n_1700_B((a_3913_L)living))) {
                    return true;
                }
                if (E_4612_l.n_1700_B(living, this.otobrazhatOptions) || E_4612_l.J_1907_R(living, this.otobrazhatOptions) || E_4612_l.G_564_y(living, this.otobrazhatOptions)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean n_1700_B(a_3913_L player) {
        for (Z_1993_T stack : player.l_1268_F.J_1907_R) {
            q_1613_l item;
            if (stack.n_1700_B() || (item = stack.J_1907_R()) != Items.u_488_m && item != Items.O_1043_U && item != Items.v_1900_v && item != Items.j_2129_E) continue;
            return true;
        }
        return false;
    }
}



