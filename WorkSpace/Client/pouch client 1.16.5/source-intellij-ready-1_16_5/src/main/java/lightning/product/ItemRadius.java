/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import lightning.product.E_688_b;
import lightning.product.F_747_P;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.H_2506_c;
import lightning.product.HitResult;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.X_933_l;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.MinecraftAccess;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;
import org.lwjgl.opengl.GL11;

public class ItemRadius
extends Module
implements MinecraftAccess {
    private final MultiBooleanSetting predmetyOptions = new MultiBooleanSetting("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", new BooleanSetting("\u0414\u0435\u0437\u043a\u0430", false), new BooleanSetting("\u042f\u0432\u043a\u0430", false), new BooleanSetting("\u041e\u0433\u043d\u0435\u043d\u043d\u044b\u0439 \u0417\u0430\u0440\u044f\u0434", false), new BooleanSetting("\u0411\u043e\u0436\u044c\u044f \u0410\u0443\u0440\u0430", false), new BooleanSetting("\u0422\u0440\u0430\u043f\u043a\u0430", false), new BooleanSetting("\u041f\u043b\u0430\u0441\u0442", false));
    private int w_1484_f = 0x55005500;
    private int t_148_a = -16755456;
    private int s_956_w = 0x55005500;
    private int u_2550_I = -16755456;
    private float M_588_G = 0.0f;
    private boolean P_4830_p = false;
    private static final float h_1847_R = 0.5f;

    public ItemRadius() {
        super("ItemRadius", ModuleCategory.R_4764_Y);
        this.addSettings(this.predmetyOptions);
    }

    @Y_1740_V
    private void n_1700_B(I_4477_R event) {
        if (ItemRadius.c_3005_b.Y_259_p == null) {
            return;
        }
        try {
            Z_1993_T mainHandItem = ItemRadius.c_3005_b.Y_259_p.A_2714_y();
            Z_1993_T offHandItem = ItemRadius.c_3005_b.Y_259_p.S_4035_N();
            e_2866_D playerPos = ItemRadius.c_3005_b.Y_259_p.s_4990_V();
            e_2866_D centerPos = playerPos.J_1907_R(0.0, -1.4, 0.0);
            if (this.predmetyOptions.isOptionEnabled("\u0414\u0435\u0437\u043a\u0430") != null && this.predmetyOptions.isOptionEnabled("\u041e\u043a\u043e") != null && this.predmetyOptions.isOptionEnabled("\u041e\u043a\u043e").booleanValue() && (this.n_1700_B(mainHandItem, Items.V_1824_v) || this.n_1700_B(offHandItem, Items.V_1824_v))) {
                boolean playersInRadius = this.n_1700_B(ItemRadius.c_3005_b.Y_259_p, centerPos, 10.0);
                this.n_1700_B(playersInRadius, 0x55005500, -16755456, 0x5500AA00, -16733696, event.J_1907_R());
                this.n_1700_B(event, 10.0f, this.w_1484_f, this.t_148_a);
                return;
            }
            if (this.predmetyOptions.isOptionEnabled("\u042f\u0432\u043a\u0430") != null && this.predmetyOptions.isOptionEnabled("\u0421\u0430\u0445\u0430\u0440") != null && this.predmetyOptions.isOptionEnabled("\u0421\u0430\u0445\u0430\u0440").booleanValue() && (this.n_1700_B(mainHandItem, Items.o_3456_E) || this.n_1700_B(offHandItem, Items.o_3456_E))) {
                boolean playersInRadius = this.n_1700_B(ItemRadius.c_3005_b.Y_259_p, centerPos, 10.0);
                this.n_1700_B(playersInRadius, 0x55999999, -6710887, 0x55FFFFFF, -1, event.J_1907_R());
                this.n_1700_B(event, 10.0f, this.w_1484_f, this.t_148_a);
                return;
            }
            if (this.predmetyOptions.isOptionEnabled("\u041e\u0433\u043d\u0435\u043d\u043d\u044b\u0439 \u0417\u0430\u0440\u044f\u0434") != null && this.predmetyOptions.isOptionEnabled("\u041e\u0433\u043d\u0435\u043d\u043d\u044b\u0439 \u0448\u0430\u0440").booleanValue() && (this.n_1700_B(mainHandItem, Items.CraftingTableBlock) || this.n_1700_B(offHandItem, Items.CraftingTableBlock))) {
                boolean playersInRadius = this.n_1700_B(ItemRadius.c_3005_b.Y_259_p, centerPos, 10.0);
                this.n_1700_B(playersInRadius, 0x55550000, -11206656, 0x55AA0000, -5636096, event.J_1907_R());
                this.n_1700_B(event, 10.0f, this.w_1484_f, this.t_148_a);
                return;
            }
            if (this.predmetyOptions.isOptionEnabled("\u0411\u043e\u0436\u044c\u044f \u0410\u0443\u0440\u0430") != null && this.predmetyOptions.isOptionEnabled("\u041c\u0435\u043c\u0431\u0440\u0430\u043d\u0430 \u0444\u0430\u043d\u0442\u043e\u043c\u0430").booleanValue() && (this.n_1700_B(mainHandItem, Items.RotatedPillarBlock) || this.n_1700_B(offHandItem, Items.RotatedPillarBlock))) {
                boolean playersInRadius = this.n_1700_B(ItemRadius.c_3005_b.Y_259_p, centerPos, 2.0);
                this.n_1700_B(playersInRadius, 0x55009999, -16737895, 0x5500FFFF, -16711681, event.J_1907_R());
                this.n_1700_B(event, 2.0f, this.w_1484_f, this.t_148_a);
                return;
            }
            if (this.predmetyOptions.isOptionEnabled("\u0422\u0440\u0430\u043f\u043a\u0430") != null && this.predmetyOptions.isOptionEnabled("\u041e\u0431\u043b\u043e\u043c\u043e\u043a \u043d\u0435\u0437\u0435\u0440\u0438\u0442\u0430").booleanValue() && (this.n_1700_B(mainHandItem, Items.m_396_H) || this.n_1700_B(offHandItem, Items.m_396_H))) {
                this.n_1700_B(event, ItemRadius.c_3005_b.Y_259_p, 1435190547, -7650029);
                return;
            }
            if (this.predmetyOptions.isOptionEnabled("\u041f\u043b\u0430\u0441\u0442") != null && this.predmetyOptions.isOptionEnabled("\u0421\u0443\u0448\u0435\u043d\u0430\u044f \u043b\u0430\u043c\u0438\u043d\u0430\u0440\u0438\u044f").booleanValue() && (this.n_1700_B(mainHandItem, Items.MinMaxBounds) || this.n_1700_B(offHandItem, Items.MinMaxBounds))) {
                this.J_1907_R(event, ItemRadius.c_3005_b.Y_259_p, 0x55333333, -13421773);
                return;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void n_1700_B(boolean playersInRadius, int baseFillColor, int baseOutlineColor, int lightFillColor, int lightOutlineColor, float partialTicks) {
        if (playersInRadius != this.P_4830_p) {
            this.M_588_G = 0.0f;
            this.P_4830_p = playersInRadius;
        }
        this.s_956_w = playersInRadius ? lightFillColor : baseFillColor;
        this.u_2550_I = playersInRadius ? lightOutlineColor : baseOutlineColor;
        this.M_588_G = Math.min(this.M_588_G + partialTicks / 0.5f, 1.0f);
        this.w_1484_f = this.n_1700_B(this.w_1484_f, this.s_956_w, this.M_588_G);
        this.t_148_a = this.n_1700_B(this.t_148_a, this.u_2550_I, this.M_588_G);
    }

    private int n_1700_B(int startColor, int endColor, float t) {
        int startA = startColor >> 24 & 0xFF;
        int startR = startColor >> 16 & 0xFF;
        int startG = startColor >> 8 & 0xFF;
        int startB = startColor & 0xFF;
        int endA = endColor >> 24 & 0xFF;
        int endR = endColor >> 16 & 0xFF;
        int endG = endColor >> 8 & 0xFF;
        int endB = endColor & 0xFF;
        int a = (int)((float)startA + (float)(endA - startA) * t);
        int r = (int)((float)startR + (float)(endR - startR) * t);
        int g = (int)((float)startG + (float)(endG - startG) * t);
        int b = (int)((float)startB + (float)(endB - startB) * t);
        return a << 24 | r << 16 | g << 8 | b;
    }

    private boolean n_1700_B(a_3913_L player, e_2866_D centerPos, double radius) {
        if (ItemRadius.c_3005_b.Y_601_j == null) {
            return false;
        }
        I_4817_s box = new I_4817_s(centerPos.J_1907_R - radius, centerPos.R_4764_Y - radius, centerPos.G_564_y - radius, centerPos.J_1907_R + radius, centerPos.R_4764_Y + radius, centerPos.G_564_y + radius);
        for (a_3913_L entity : ItemRadius.c_3005_b.Y_601_j.n_1700_B(a_3913_L.class, box)) {
            if (entity == player || !(entity.s_4990_V().u_1723_Y(centerPos) <= radius)) continue;
            return true;
        }
        return false;
    }

    private boolean n_1700_B(Z_1993_T stack, q_1613_l item) {
        return !stack.n_1700_B() && stack.J_1907_R() == item;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void n_1700_B(I_4477_R event, float radius, int fillColor, int outlineColor) {
        if (ItemRadius.c_3005_b.Y_259_p == null) {
            return;
        }
        X_933_l.g_221_o();
        c_4037_x.v_4276_D();
        try {
            float zOffset;
            c_4037_x.J_1907_R(-c_3005_b.O_508_d().renderPosX(), -c_3005_b.O_508_d().renderPosY(), -c_3005_b.O_508_d().renderPosZ());
            e_2866_D position = F_747_P.n_1700_B((N_4263_v)ItemRadius.c_3005_b.Y_259_p, event.J_1907_R());
            position = position.J_1907_R(0.0, -1.4, 0.0);
            c_4037_x.J_1907_R(position.J_1907_R, position.R_4764_Y + (double)ItemRadius.c_3005_b.Y_259_p.v_165_F(), position.G_564_y);
            double yaw = ItemRadius.c_3005_b.O_508_d().J_1907_R.P_1922_E();
            GL11.glRotatef((float)((float)(-yaw)), (float)0.0f, (float)1.0f, (float)0.0f);
            c_4037_x.J_1907_R(-position.J_1907_R, -(position.R_4764_Y + (double)ItemRadius.c_3005_b.Y_259_p.v_165_F()), -position.G_564_y);
            c_4037_x.Y_601_j();
            c_4037_x.J_1907_R(false);
            c_4037_x.e_4240_b();
            c_4037_x.q_2307_F();
            c_4037_x.J_1907_R(770, 771);
            c_4037_x.w_1484_f(7425);
            c_4037_x.G_564_y(3.0f);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            float fillR = (float)H_2506_c.n_1700_B(fillColor) / 255.0f;
            float fillG = (float)H_2506_c.J_1907_R(fillColor) / 255.0f;
            float fillB = (float)H_2506_c.R_4764_Y(fillColor) / 255.0f;
            float fillA = (float)H_2506_c.G_564_y(fillColor) / 255.0f;
            float outlineR = (float)H_2506_c.n_1700_B(outlineColor) / 255.0f;
            float outlineG = (float)H_2506_c.J_1907_R(outlineColor) / 255.0f;
            float outlineB = (float)H_2506_c.R_4764_Y(outlineColor) / 255.0f;
            float outlineA = (float)H_2506_c.G_564_y(outlineColor) / 255.0f;
            A_4115_X.n_1700_B(6, E_688_b.Y_601_j);
            A_4115_X.pos(position.J_1907_R, position.R_4764_Y + (double)ItemRadius.c_3005_b.Y_259_p.v_165_F(), position.G_564_y).n_1700_B(fillR, fillG, fillB, fillA).endVertex();
            for (int z = 0; z <= 360; z += 5) {
                float angle = (float)(position.J_1907_R + (double)(u_530_F.n_1700_B((float)Math.toRadians(z)) * radius));
                zOffset = (float)(position.G_564_y + (double)(-u_530_F.J_1907_R((float)Math.toRadians(z)) * radius));
                A_4115_X.pos(angle, position.R_4764_Y + (double)ItemRadius.c_3005_b.Y_259_p.v_165_F(), zOffset).n_1700_B(fillR, fillG, fillB, fillA).endVertex();
            }
            Y_1740_V.J_1907_R();
            A_4115_X.n_1700_B(2, E_688_b.Y_601_j);
            for (int loopIndex = 0; loopIndex <= 360; loopIndex += 5) {
                float xOffset = (float)(position.J_1907_R + (double)(u_530_F.n_1700_B((float)Math.toRadians(loopIndex)) * radius));
                zOffset = (float)(position.G_564_y + (double)(-u_530_F.J_1907_R((float)Math.toRadians(loopIndex)) * radius));
                A_4115_X.pos(xOffset, position.R_4764_Y + (double)ItemRadius.c_3005_b.Y_259_p.v_165_F(), zOffset).n_1700_B(outlineR, outlineG, outlineB, outlineA).endVertex();
            }
            Y_1740_V.J_1907_R();
        }
        finally {
            GL11.glDisable((int)2848);
            c_4037_x.x_607_J();
            c_4037_x.Y_259_p();
            c_4037_x.k_2293_S();
            c_4037_x.J_1907_R(true);
            c_4037_x.w_1484_f(7424);
            c_4037_x.d_2461_k();
            X_933_l.e_2887_G();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void n_1700_B(I_4477_R event, a_3913_L player, int fillColor, int outlineColor) {
        if (player == null) {
            return;
        }
        X_933_l.g_221_o();
        c_4037_x.v_4276_D();
        try {
            int[][] edges;
            int n;
            c_4037_x.J_1907_R(-c_3005_b.O_508_d().renderPosX(), -c_3005_b.O_508_d().renderPosY(), -c_3005_b.O_508_d().renderPosZ());
            e_2866_D position = F_747_P.n_1700_B((N_4263_v)player, event.J_1907_R());
            double cubeX = Math.floor(position.J_1907_R) + 0.5;
            double cubeY = Math.floor(position.R_4764_Y) + 0.5 + 1.625;
            double cubeZ = Math.floor(position.G_564_y) + 0.5;
            e_2866_D cubePos = new e_2866_D(cubeX, cubeY, cubeZ);
            c_4037_x.Y_601_j();
            c_4037_x.J_1907_R(false);
            c_4037_x.e_4240_b();
            c_4037_x.q_2307_F();
            c_4037_x.J_1907_R(770, 771);
            c_4037_x.w_1484_f(7425);
            c_4037_x.G_564_y(3.0f);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            c_4037_x.J_1907_R(cubePos.J_1907_R, cubePos.R_4764_Y, cubePos.G_564_y);
            float size = 4.0f;
            float halfSize = size / 2.0f;
            float[][] vertices = new float[][]{{-halfSize, -halfSize, -halfSize}, {halfSize, -halfSize, -halfSize}, {halfSize, halfSize, -halfSize}, {-halfSize, halfSize, -halfSize}, {-halfSize, -halfSize, halfSize}, {halfSize, -halfSize, halfSize}, {2.0f, 2.0f, 2.0f}, {-halfSize, halfSize, halfSize}};
            int[][] faces = new int[][]{{0, 1, 2, 3}, {5, 4, 7, 6}, {4, 5, 1, 0}, {3, 2, 6, 7}, {4, 0, 3, 7}, {1, 5, 6, 2}};
            float cubeFillR = (float)H_2506_c.n_1700_B(fillColor) / 255.0f;
            float cubeFillG = (float)H_2506_c.J_1907_R(fillColor) / 255.0f;
            float cubeFillB = (float)H_2506_c.R_4764_Y(fillColor) / 255.0f;
            float cubeFillA = (float)H_2506_c.G_564_y(fillColor) / 255.0f;
            float cubeOutlineR = (float)H_2506_c.n_1700_B(outlineColor) / 255.0f;
            float cubeOutlineG = (float)H_2506_c.J_1907_R(outlineColor) / 255.0f;
            float cubeOutlineB = (float)H_2506_c.R_4764_Y(outlineColor) / 255.0f;
            float cubeOutlineA = (float)H_2506_c.G_564_y(outlineColor) / 255.0f;
            A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
            int[][] nArrayArray = faces;
            int n2 = nArrayArray.length;
            for (n = 0; n < n2; ++n) {
                int[] face;
                for (int i : face = nArrayArray[n]) {
                    A_4115_X.pos(vertices[i][0], vertices[i][1], vertices[i][2]).n_1700_B(cubeFillR, cubeFillG, cubeFillB, cubeFillA).endVertex();
                }
            }
            Y_1740_V.J_1907_R();
            A_4115_X.n_1700_B(1, E_688_b.Y_601_j);
            int[][] nArrayArray2 = edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}, {4, 5}, {5, 6}, {6, 7}, {7, 4}, {0, 4}, {1, 5}, {2, 6}, {3, 7}};
            n = nArrayArray2.length;
            for (int i = 0; i < n; ++i) {
                int[] edge;
                for (int i2 : edge = nArrayArray2[i]) {
                    A_4115_X.pos(vertices[i2][0], vertices[i2][1], vertices[i2][2]).n_1700_B(cubeOutlineR, cubeOutlineG, cubeOutlineB, cubeOutlineA).endVertex();
                }
            }
            Y_1740_V.J_1907_R();
        }
        finally {
            GL11.glDisable((int)2848);
            c_4037_x.x_607_J();
            c_4037_x.Y_259_p();
            c_4037_x.k_2293_S();
            c_4037_x.J_1907_R(true);
            c_4037_x.w_1484_f(7424);
            c_4037_x.d_2461_k();
            X_933_l.e_2887_G();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void J_1907_R(I_4477_R event, a_3913_L player, int fillColor, int outlineColor) {
        if (player == null || ItemRadius.c_3005_b.Y_601_j == null) {
            return;
        }
        X_933_l.g_221_o();
        c_4037_x.v_4276_D();
        try {
            int n;
            int[][] edges;
            int[][] faces;
            float[][] vertices;
            e_2866_D planePos;
            c_4037_x.J_1907_R(-c_3005_b.O_508_d().renderPosX(), -c_3005_b.O_508_d().renderPosY(), -c_3005_b.O_508_d().renderPosZ());
            e_2866_D position = F_747_P.n_1700_B((N_4263_v)player, event.J_1907_R());
            float pitch = player.J_1907_R(event.J_1907_R());
            float yaw = player.R_4764_Y(event.J_1907_R());
            boolean isLookingDown = pitch > 45.0f;
            boolean isLookingUp = pitch < -45.0f;
            boolean isLookingHorizontal = !isLookingDown && !isLookingUp;
            e_2866_D start = position.J_1907_R(0.0, player.X_1313_W(), 0.0);
            e_2866_D lookVec = player.t_148_a(event.J_1907_R());
            e_2866_D end = start.J_1907_R(lookVec.J_1907_R * 4.0, lookVec.R_4764_Y * 4.0, lookVec.G_564_y * 4.0);
            ClipContext context = new ClipContext(start, end, ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, player);
            BlockHitResult rayTraceResult = ItemRadius.c_3005_b.Y_601_j.n_1700_B(context);
            float width = 4.0f;
            float height = 4.0f;
            float thickness = 1.5f;
            float halfWidth = width / 2.0f;
            float halfHeight = height / 2.0f;
            float halfThickness = thickness / 2.0f;
            if (rayTraceResult.R_4764_Y() == HitResult.n_1700_B.J_1907_R && rayTraceResult.P_1922_E().u_1723_Y(start) <= 4.0) {
                e_2866_D hitPos = rayTraceResult.P_1922_E();
                if (isLookingDown) {
                    planePos = new e_2866_D(Math.floor(hitPos.J_1907_R) + 0.5, Math.floor(hitPos.R_4764_Y + 1.0) - 1.8 + (double)halfThickness, Math.floor(hitPos.G_564_y) + 0.5);
                } else if (isLookingUp) {
                    planePos = new e_2866_D(Math.floor(hitPos.J_1907_R) + 0.5, Math.floor(hitPos.R_4764_Y) - (double)halfThickness + 1.6, Math.floor(hitPos.G_564_y) + 0.5);
                } else {
                    double offsetX = rayTraceResult.J_1907_R().t_148_a() != 0 ? (double)((float)rayTraceResult.J_1907_R().t_148_a() * halfThickness) : 0.0;
                    double offsetZ = rayTraceResult.J_1907_R().u_2550_I() != 0 ? (double)((float)rayTraceResult.J_1907_R().u_2550_I() * halfThickness) : 0.0;
                    planePos = new e_2866_D(Math.floor(hitPos.J_1907_R) + 0.5 + offsetX, Math.floor(hitPos.R_4764_Y) + 0.5 + 1.6, Math.floor(hitPos.G_564_y) + 0.5 + offsetZ);
                }
            } else {
                double distance = 4.0;
                double dx = lookVec.J_1907_R * distance;
                double dy = lookVec.R_4764_Y * distance;
                double dz = lookVec.G_564_y * distance;
                planePos = start.J_1907_R(dx, dy, dz);
                double adjustedY = Math.floor(planePos.R_4764_Y) + (isLookingDown ? -1.8 + (double)halfThickness : (isLookingUp ? (double)(-halfThickness) + 1.6 : 2.1));
                planePos = new e_2866_D(Math.floor(planePos.J_1907_R) + 0.5, adjustedY, Math.floor(planePos.G_564_y) + 0.5);
            }
            c_4037_x.Y_601_j();
            c_4037_x.J_1907_R(false);
            c_4037_x.e_4240_b();
            c_4037_x.q_2307_F();
            c_4037_x.J_1907_R(770, 771);
            c_4037_x.w_1484_f(7425);
            c_4037_x.G_564_y(3.0f);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            c_4037_x.J_1907_R(planePos.J_1907_R, planePos.R_4764_Y, planePos.G_564_y);
            if (isLookingHorizontal) {
                GL11.glRotatef((float)(-yaw), (float)0.0f, (float)1.0f, (float)0.0f);
                vertices = new float[][]{{-halfWidth, -halfHeight, -halfThickness}, {halfWidth, -halfHeight, -halfThickness}, {halfWidth, halfHeight, -halfThickness}, {-halfWidth, halfHeight, -halfThickness}, {-halfWidth, -halfHeight, halfThickness}, {halfWidth, -halfHeight, halfThickness}, {2.0f, 2.0f, 0.75f}, {-halfWidth, halfHeight, halfThickness}};
                faces = new int[][]{{0, 1, 2, 3}, {5, 4, 7, 6}, {4, 5, 1, 0}, {3, 2, 6, 7}, {4, 0, 3, 7}, {1, 5, 6, 2}};
                edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}, {4, 5}, {5, 6}, {6, 7}, {7, 4}, {0, 4}, {1, 5}, {2, 6}, {3, 7}};
            } else if (isLookingDown) {
                vertices = new float[][]{{-halfWidth, -halfThickness, -halfHeight}, {halfWidth, -halfThickness, -halfHeight}, {halfWidth, halfThickness, -halfHeight}, {-halfWidth, halfThickness, -halfHeight}, {-halfWidth, -halfThickness, halfHeight}, {halfWidth, -halfThickness, halfHeight}, {2.0f, 0.75f, 2.0f}, {-halfWidth, halfThickness, halfHeight}};
                faces = new int[][]{{0, 1, 2, 3}, {5, 4, 7, 6}, {4, 5, 1, 0}, {3, 2, 6, 7}, {4, 0, 3, 7}, {1, 5, 6, 2}};
                edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}, {4, 5}, {5, 6}, {6, 7}, {7, 4}, {0, 4}, {1, 5}, {2, 6}, {3, 7}};
            } else {
                vertices = new float[][]{{-halfWidth, -halfThickness, -halfHeight}, {halfWidth, -halfThickness, -halfHeight}, {halfWidth, halfThickness, -halfHeight}, {-halfWidth, halfThickness, -halfHeight}, {-halfWidth, -halfThickness, halfHeight}, {halfWidth, -halfThickness, halfHeight}, {2.0f, 0.75f, 2.0f}, {-halfWidth, halfThickness, halfHeight}};
                faces = new int[][]{{0, 1, 2, 3}, {5, 4, 7, 6}, {4, 5, 1, 0}, {3, 2, 6, 7}, {4, 0, 3, 7}, {1, 5, 6, 2}};
                edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}, {4, 5}, {5, 6}, {6, 7}, {7, 4}, {0, 4}, {1, 5}, {2, 6}, {3, 7}};
            }
            float planeFillR = (float)H_2506_c.n_1700_B(fillColor) / 255.0f;
            float planeFillG = (float)H_2506_c.J_1907_R(fillColor) / 255.0f;
            float planeFillB = (float)H_2506_c.R_4764_Y(fillColor) / 255.0f;
            float planeFillA = (float)H_2506_c.G_564_y(fillColor) / 255.0f;
            float planeOutlineR = (float)H_2506_c.n_1700_B(outlineColor) / 255.0f;
            float planeOutlineG = (float)H_2506_c.J_1907_R(outlineColor) / 255.0f;
            float planeOutlineB = (float)H_2506_c.R_4764_Y(outlineColor) / 255.0f;
            float planeOutlineA = (float)H_2506_c.G_564_y(outlineColor) / 255.0f;
            A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
            int[][] nArrayArray = faces;
            int n2 = nArrayArray.length;
            for (n = 0; n < n2; ++n) {
                int[] face;
                for (int i : face = nArrayArray[n]) {
                    A_4115_X.pos(vertices[i][0], vertices[i][1], vertices[i][2]).n_1700_B(planeFillR, planeFillG, planeFillB, planeFillA).endVertex();
                }
            }
            Y_1740_V.J_1907_R();
            A_4115_X.n_1700_B(1, E_688_b.Y_601_j);
            nArrayArray = edges;
            n2 = nArrayArray.length;
            for (n = 0; n < n2; ++n) {
                int[] edge;
                for (int i : edge = nArrayArray[n]) {
                    A_4115_X.pos(vertices[i][0], vertices[i][1], vertices[i][2]).n_1700_B(planeOutlineR, planeOutlineG, planeOutlineB, planeOutlineA).endVertex();
                }
            }
            Y_1740_V.J_1907_R();
        }
        finally {
            GL11.glDisable((int)2848);
            c_4037_x.x_607_J();
            c_4037_x.Y_259_p();
            c_4037_x.k_2293_S();
            c_4037_x.J_1907_R(true);
            c_4037_x.w_1484_f(7424);
            c_4037_x.d_2461_k();
            X_933_l.e_2887_G();
        }
    }
}



