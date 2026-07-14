/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils;

import java.awt.Color;
import lightning.product.C_3240_x;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.I_4817_s;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.l_3747_P;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.Settings;
import mods.baritone.utils.accessor.IEntityRenderManager;

public interface IRenderer {
    public static final l_3747_P tessellator = l_3747_P.n_1700_B();
    public static final D_3318_r buffer = tessellator.R_4764_Y();
    public static final IEntityRenderManager renderManager = MinecraftClient.A_4115_X().O_508_d();
    public static final C_3240_x textureManager = MinecraftClient.A_4115_X().G_624_v();
    public static final Settings settings = BaritoneAPI.getSettings();
    public static final float[] color = new float[]{1.0f, 1.0f, 1.0f, 255.0f};

    public static void glColor(Color color, float alpha) {
        float[] colorComponents = color.getColorComponents(null);
        IRenderer.color[0] = colorComponents[0];
        IRenderer.color[1] = colorComponents[1];
        IRenderer.color[2] = colorComponents[2];
        IRenderer.color[3] = alpha;
    }

    public static void startLines(Color color, float alpha, float lineWidth, boolean ignoreDepth) {
        c_4037_x.Y_601_j();
        c_4037_x.J_1907_R(770, 771, 1, 0);
        IRenderer.glColor(color, alpha);
        c_4037_x.G_564_y(lineWidth);
        c_4037_x.e_4240_b();
        c_4037_x.J_1907_R(false);
        if (ignoreDepth) {
            c_4037_x.t_1786_h();
        }
        buffer.n_1700_B(1, E_688_b.Y_601_j);
    }

    public static void startLines(Color color, float lineWidth, boolean ignoreDepth) {
        IRenderer.startLines(color, 0.4f, lineWidth, ignoreDepth);
    }

    public static void endLines(boolean ignoredDepth) {
        tessellator.J_1907_R();
        if (ignoredDepth) {
            c_4037_x.multiplayerClientSuggestionProvider();
        }
        c_4037_x.J_1907_R(true);
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
    }

    public static void emitAABB(g_221_o stack, I_4817_s aabb) {
        I_4817_s toDraw = aabb.offset(-renderManager.renderPosX(), -renderManager.renderPosY(), -renderManager.renderPosZ());
        D_1098_v matrix4f = stack.R_4764_Y().n_1700_B();
        buffer.n_1700_B(matrix4f, (float)toDraw.minX, (float)toDraw.minY, (float)toDraw.minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.maxX, (float)toDraw.minY, (float)toDraw.minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.maxX, (float)toDraw.minY, (float)toDraw.minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.maxX, (float)toDraw.minY, (float)toDraw.maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.maxX, (float)toDraw.minY, (float)toDraw.maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.minX, (float)toDraw.minY, (float)toDraw.maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.minX, (float)toDraw.minY, (float)toDraw.maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.minX, (float)toDraw.minY, (float)toDraw.minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.minX, (float)toDraw.maxY, (float)toDraw.minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.maxX, (float)toDraw.maxY, (float)toDraw.minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.maxX, (float)toDraw.maxY, (float)toDraw.minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.maxX, (float)toDraw.maxY, (float)toDraw.maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.maxX, (float)toDraw.maxY, (float)toDraw.maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.minX, (float)toDraw.maxY, (float)toDraw.maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.minX, (float)toDraw.maxY, (float)toDraw.maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.minX, (float)toDraw.maxY, (float)toDraw.minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.minX, (float)toDraw.minY, (float)toDraw.minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.minX, (float)toDraw.maxY, (float)toDraw.minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.maxX, (float)toDraw.minY, (float)toDraw.minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.maxX, (float)toDraw.maxY, (float)toDraw.minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.maxX, (float)toDraw.minY, (float)toDraw.maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.maxX, (float)toDraw.maxY, (float)toDraw.maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.minX, (float)toDraw.minY, (float)toDraw.maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)toDraw.minX, (float)toDraw.maxY, (float)toDraw.maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
    }

    public static void emitAABB(g_221_o stack, I_4817_s aabb, double expand) {
        IRenderer.emitAABB(stack, aabb.grow(expand, expand, expand));
    }

    public static void drawAABB(g_221_o stack, I_4817_s aabb) {
        buffer.n_1700_B(1, E_688_b.Y_601_j);
        IRenderer.emitAABB(stack, aabb);
        tessellator.J_1907_R();
    }
}



