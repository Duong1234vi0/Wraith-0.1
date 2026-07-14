/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.l_3747_P;
import lightning.product.ModuleCategory;
import org.lwjgl.opengl.GL11;

public class x_555_z
extends Module {
    private final Map<K_4074_S, Integer> v_4262_N = new HashMap<K_4074_S, Integer>();

    public x_555_z() {
        super("AncientXray", "\u041f\u043e\u0434\u0441\u0432\u0435\u0447\u0438\u0432\u0430\u0435\u0442 \u043d\u0435\u0437\u0435\u0440 \u043e\u0431\u043b\u043e\u043c\u043a\u0438", ModuleCategory.R_4764_Y);
        this.n_1700_B(a_3742_W.B_368_w.multiplayerClientSuggestionProvider(), new Color(255, 255, 255).getRGB());
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (x_555_z.c_3005_b.Y_601_j == null || x_555_z.c_3005_b.Y_259_p == null) {
            return;
        }
        c_1514_x playerPos = x_555_z.c_3005_b.Y_259_p.b_2312_j();
        int range = 19;
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
        for (int x = -range; x <= range; ++x) {
            for (int y = -range; y <= range; ++y) {
                for (int z = -range; z <= range; ++z) {
                    c_1514_x pos = playerPos.add(x, y, z);
                    K_4074_S state = x_555_z.c_3005_b.Y_601_j.getBlockState(pos);
                    Integer color = this.v_4262_N.get(state);
                    if (color == null || !this.n_1700_B(pos)) continue;
                    float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
                    float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
                    float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
                    float a = (float)H_2506_c.G_564_y(color) / 255.0f;
                    I_4817_s box = new I_4817_s(pos).shrink(0.001);
                    I_4817_s renderBox = box.offset(-renderX, -renderY, -renderZ);
                    c_4037_x.G_564_y(1.5f);
                    GL11.glEnable((int)2848);
                    buffer.n_1700_B(1, E_688_b.Y_601_j);
                    this.n_1700_B(buffer, renderBox, r, g, b, a);
                    tessellator.J_1907_R();
                    GL11.glDisable((int)2848);
                }
            }
        }
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    private boolean n_1700_B(c_1514_x pos) {
        c_1514_x[] adjacentBlocks;
        int exposedCount = 0;
        for (c_1514_x adjacent : adjacentBlocks = new c_1514_x[]{pos.north(), pos.south(), pos.east(), pos.west(), pos.up(), pos.down()}) {
            K_4074_S adjacentState = x_555_z.c_3005_b.Y_601_j.getBlockState(adjacent);
            if (!adjacentState.v_4262_N() && adjacentState.J_1907_R() != a_3742_W.H_2857_Y || ++exposedCount < 2) continue;
            return true;
        }
        return false;
    }

    private void n_1700_B(D_3318_r buf, I_4817_s box, float r, float g, float b, float a) {
        float x0 = (float)box.minX;
        float x1 = (float)box.maxX;
        float y0 = (float)box.minY;
        float y1 = (float)box.maxY;
        float z0 = (float)box.minZ;
        float z1 = (float)box.maxZ;
        buf.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buf.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
    }

    private void n_1700_B(K_4074_S blockState, int color) {
        this.v_4262_N.put(blockState, color);
    }
}



