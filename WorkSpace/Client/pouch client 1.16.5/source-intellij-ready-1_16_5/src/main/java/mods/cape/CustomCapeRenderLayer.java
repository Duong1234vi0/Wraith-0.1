/*
 * Decompiled with CFR 0.152.
 */
package mods.cape;

import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.E_4346_v;
import lightning.product.RenderLayer;
import lightning.product.PlayerModel;
import lightning.product.M_1336_P;
import lightning.product.X_4340_E;
import lightning.product.Z_1993_T;
import lightning.product.Z_3224_L;
import lightning.product.c_4037_x;
import lightning.product.e_1174_E;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.ClientBootstrap;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.Items;
import lightning.product.u_530_F;
import lightning.product.Cosmetics;
import mods.cape.Cape;
import mods.cape.CapeMovement;
import mods.cape.CapeRenderer;
import mods.cape.CapeStyle;
import mods.cape.StickSimulation;
import mods.cape.VanillaCapeRenderer;
import mods.cape.Vector3;
import mods.cape.WindMode;
import net.optifine.Config;

public class CustomCapeRenderLayer
extends RenderLayer<X_4340_E, PlayerModel<X_4340_E>> {
    private static int partCount;
    private e_4189_z[] customCape = new e_4189_z[partCount];
    private static final VanillaCapeRenderer vanillaCape;
    private static final int scale = 3600000;

    public CustomCapeRenderLayer(j_4203_m<X_4340_E, PlayerModel<X_4340_E>> renderLayerParent) {
        super(renderLayerParent);
        partCount = 16;
        this.buildMesh();
    }

    private void buildMesh() {
        this.customCape = new e_4189_z[partCount];
        for (int i = 0; i < partCount; ++i) {
            e_4189_z base = new e_4189_z(64, 32, 0, i);
            this.customCape[i] = base.n_1700_B(-5.0f, i, -1.0f, 10.0f, 1.0f, 1.0f);
        }
    }

    @Override
    public void render(g_221_o poseStack, o_3091_w multiBufferSource, int i, X_4340_E abstractClientPlayer, float f, float g, float delta, float j, float k, float l) {
        Cosmetics cosmetics = (Cosmetics)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Cosmetics.class);
        if (cosmetics != null && cosmetics.n_1700_B(abstractClientPlayer)) {
            return;
        }
        CapeRenderer renderer = this.getCapeRenderer(abstractClientPlayer, multiBufferSource);
        if (renderer == null) {
            return;
        }
        Z_1993_T itemStack = abstractClientPlayer.J_1907_R(e_1174_E.P_1922_E);
        if (itemStack.J_1907_R() == Items.NyliumBlock) {
            return;
        }
        if (abstractClientPlayer.g_164_R()) {
            abstractClientPlayer.updateSimulation(abstractClientPlayer, partCount);
            if (Cape.config.capeStyle == CapeStyle.SMOOTH && renderer.vanillaUvValues()) {
                this.renderSmoothCape(poseStack, multiBufferSource, renderer, abstractClientPlayer, delta, i);
            } else {
                e_4189_z[] parts = this.customCape;
                for (int part = 0; part < partCount; ++part) {
                    e_4189_z model = parts[part];
                    this.modifyPoseStack(poseStack, abstractClientPlayer, delta, part);
                    renderer.render(abstractClientPlayer, part, model, poseStack, multiBufferSource, i, Z_3224_L.n_1700_B);
                    poseStack.J_1907_R();
                }
            }
        } else {
            this.renderVanillaCape(poseStack, multiBufferSource, i, abstractClientPlayer, f, g, delta, j, k, l);
        }
    }

    public void renderVanillaCape(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, X_4340_E player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Z_1993_T itemstack;
        if (player.T_2506_i() && !player.F_3572_x() && player.n_1700_B(E_4346_v.n_1700_B) && player.e_2887_G() != null && (itemstack = player.J_1907_R(e_1174_E.P_1922_E)).J_1907_R() != Items.NyliumBlock) {
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.0, 0.0, 0.125);
            double d0 = u_530_F.G_564_y((double)partialTicks, player.d_3244_b, player.r_2478_U) - u_530_F.G_564_y((double)partialTicks, player.r_715_M, player.O_3598_v());
            double d1 = u_530_F.G_564_y((double)partialTicks, player.v_887_r, player.h_2848_I) - u_530_F.G_564_y((double)partialTicks, player.A_1038_p, player.X_2960_b());
            double d2 = u_530_F.G_564_y((double)partialTicks, player.l_3609_d, player.A_3244_K) - u_530_F.G_564_y((double)partialTicks, player.i_1637_u, player.l_2647_k());
            float f = player.D_4361_a + (player.C_1162_e - player.D_4361_a);
            double d3 = u_530_F.n_1700_B(f * ((float)Math.PI / 180));
            double d4 = -u_530_F.J_1907_R(f * ((float)Math.PI / 180));
            float f1 = (float)d1 * 10.0f;
            f1 = u_530_F.n_1700_B(f1, -6.0f, 32.0f);
            float f2 = (float)(d0 * d3 + d2 * d4) * 100.0f;
            f2 = u_530_F.n_1700_B(f2, 0.0f, 150.0f);
            float f3 = (float)(d0 * d4 - d2 * d3) * 100.0f;
            f3 = u_530_F.n_1700_B(f3, -20.0f, 20.0f);
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            if (f2 > 165.0f) {
                f2 = 165.0f;
            }
            if (f1 < -5.0f) {
                f1 = -5.0f;
            }
            float f4 = u_530_F.v_4262_N(partialTicks, player.X_290_I, player.O_1795_e);
            f1 += u_530_F.n_1700_B(u_530_F.v_4262_N(partialTicks, player.V_1446_Y, player.PlayerInfo) * 6.0f) * 32.0f * f4;
            if (player.Z_875_P()) {
                f1 += 25.0f;
            }
            float f5 = Config.getAverageFrameTimeSec() * 20.0f;
            f5 = Config.limit(f5, 0.02f, 1.0f);
            player.N_2525_X = u_530_F.v_4262_N(f5, player.N_2525_X, 6.0f + f2 / 2.0f + f1);
            player.g_2268_R = u_530_F.v_4262_N(f5, player.g_2268_R, f3 / 2.0f);
            player.c_4037_x = u_530_F.v_4262_N(f5, player.c_4037_x, 180.0f - f3 / 2.0f);
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(player.N_2525_X));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(player.g_2268_R));
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(player.c_4037_x));
            D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.J_1907_R(player.e_2887_G()));
            ((PlayerModel)this.getEntityModel()).J_1907_R(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B);
            matrixStackIn.J_1907_R();
        }
    }

    private void renderSmoothCape(g_221_o poseStack, o_3091_w multiBufferSource, CapeRenderer capeRenderer, X_4340_E abstractClientPlayer, float delta, int light) {
        D_4792_h bufferBuilder = capeRenderer.getVertexConsumer(multiBufferSource, abstractClientPlayer);
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        D_1098_v oldPositionMatrix = null;
        for (int part = 0; part < partCount; ++part) {
            this.modifyPoseStack(poseStack, abstractClientPlayer, delta, part);
            if (oldPositionMatrix == null) {
                oldPositionMatrix = poseStack.R_4764_Y().n_1700_B();
            }
            if (part == 0) {
                CustomCapeRenderLayer.addTopVertex(bufferBuilder, poseStack.R_4764_Y().n_1700_B(), oldPositionMatrix, 0.3f, 0.0f, 0.0f, -0.3f, 0.0f, -0.06f, part, light);
            }
            if (part == partCount - 1) {
                CustomCapeRenderLayer.addBottomVertex(bufferBuilder, poseStack.R_4764_Y().n_1700_B(), poseStack.R_4764_Y().n_1700_B(), 0.3f, (float)(part + 1) * (0.96f / (float)partCount), 0.0f, -0.3f, (float)(part + 1) * (0.96f / (float)partCount), -0.06f, part, light);
            }
            CustomCapeRenderLayer.addLeftVertex(bufferBuilder, poseStack.R_4764_Y().n_1700_B(), oldPositionMatrix, -0.3f, (float)(part + 1) * (0.96f / (float)partCount), 0.0f, -0.3f, (float)part * (0.96f / (float)partCount), -0.06f, part, light);
            CustomCapeRenderLayer.addRightVertex(bufferBuilder, poseStack.R_4764_Y().n_1700_B(), oldPositionMatrix, 0.3f, (float)(part + 1) * (0.96f / (float)partCount), 0.0f, 0.3f, (float)part * (0.96f / (float)partCount), -0.06f, part, light);
            CustomCapeRenderLayer.addBackVertex(bufferBuilder, poseStack.R_4764_Y().n_1700_B(), oldPositionMatrix, 0.3f, (float)(part + 1) * (0.96f / (float)partCount), -0.06f, -0.3f, (float)part * (0.96f / (float)partCount), -0.06f, part, light);
            CustomCapeRenderLayer.addFrontVertex(bufferBuilder, oldPositionMatrix, poseStack.R_4764_Y().n_1700_B(), 0.3f, (float)(part + 1) * (0.96f / (float)partCount), 0.0f, -0.3f, (float)part * (0.96f / (float)partCount), 0.0f, part, light);
            oldPositionMatrix = poseStack.R_4764_Y().n_1700_B().u_1723_Y();
            poseStack.J_1907_R();
        }
    }

    private void modifyPoseStack(g_221_o poseStack, X_4340_E abstractClientPlayer, float h, int part) {
        if (Cape.config.capeMovement == CapeMovement.BASIC_SIMULATION) {
            this.modifyPoseStackSimulation(poseStack, abstractClientPlayer, h, part);
            return;
        }
        this.modifyPoseStackVanilla(poseStack, abstractClientPlayer, h, part);
    }

    private void modifyPoseStackSimulation(g_221_o poseStack, X_4340_E abstractClientPlayer, float delta, int part) {
        StickSimulation simulation = abstractClientPlayer.getSimulation();
        poseStack.n_1700_B();
        Z_1993_T itemStack = abstractClientPlayer.J_1907_R(e_1174_E.P_1922_E);
        double z1 = !itemStack.n_1700_B() ? 0.15 : 0.125;
        poseStack.n_1700_B(0.0, 0.0, z1);
        StickSimulation.Point capePoint = simulation.getPoints().get(0);
        float x = simulation.getPoints().get(part).getLerpX(delta) - capePoint.getLerpX(delta);
        if (x > 0.0f) {
            x = 0.0f;
        }
        float y = capePoint.getLerpY(delta) - (float)part - simulation.getPoints().get(part).getLerpY(delta);
        float z = capePoint.getLerpZ(delta) - simulation.getPoints().get(part).getLerpZ(delta);
        float sidewaysRotationOffset = 0.0f;
        float partRotation = this.getRotation(delta, part, simulation);
        float height = 0.0f;
        if (abstractClientPlayer.Z_875_P()) {
            height += 25.0f;
            poseStack.n_1700_B(0.0, (double)0.15f, 0.0);
        }
        float naturalWindSwing = this.getNatrualWindSwing(part, abstractClientPlayer.z_1737_N());
        poseStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(6.0f + height + naturalWindSwing));
        poseStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(0.0f));
        poseStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f));
        poseStack.n_1700_B((double)(-z / (float)partCount), (double)(y / (float)partCount), (double)(x / (float)partCount));
        poseStack.n_1700_B(0.0, 0.03, -0.03);
        poseStack.n_1700_B(0.0, (double)((float)part * 1.0f / (float)partCount), 0.0);
        poseStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-partRotation));
        poseStack.n_1700_B(0.0, (double)((float)(-part) * 1.0f / (float)partCount), 0.0);
        poseStack.n_1700_B(0.0, -0.03, 0.03);
    }

    private float getRotation(float delta, int part, StickSimulation simulation) {
        if (part == partCount - 1) {
            return this.getRotation(delta, part - 1, simulation);
        }
        return (float)this.getAngle(simulation.points.get(part).getLerpedPos(delta), simulation.points.get(part + 1).getLerpedPos(delta));
    }

    private double getAngle(Vector3 a, Vector3 b) {
        Vector3 angle = b.subtract(a);
        return Math.toDegrees(Math.atan2(angle.x, angle.y)) + 180.0;
    }

    private void modifyPoseStackVanilla(g_221_o poseStack, X_4340_E abstractClientPlayer, float h, int part) {
        poseStack.n_1700_B();
        poseStack.n_1700_B(0.0, 0.0, 0.125);
        double d = u_530_F.G_564_y((double)h, abstractClientPlayer.d_3244_b, abstractClientPlayer.r_2478_U) - u_530_F.G_564_y((double)h, abstractClientPlayer.r_715_M, abstractClientPlayer.O_3598_v());
        double e = u_530_F.G_564_y((double)h, abstractClientPlayer.v_887_r, abstractClientPlayer.h_2848_I) - u_530_F.G_564_y((double)h, abstractClientPlayer.A_1038_p, abstractClientPlayer.X_2960_b());
        double m = u_530_F.G_564_y((double)h, abstractClientPlayer.l_3609_d, abstractClientPlayer.A_3244_K) - u_530_F.G_564_y((double)h, abstractClientPlayer.i_1637_u, abstractClientPlayer.l_2647_k());
        float n = abstractClientPlayer.D_4361_a + abstractClientPlayer.C_1162_e - abstractClientPlayer.D_4361_a;
        double o = u_530_F.n_1700_B(n * ((float)Math.PI / 180));
        double p = -u_530_F.J_1907_R(n * ((float)Math.PI / 180));
        float height = (float)e * 10.0f;
        height = u_530_F.n_1700_B(height, -6.0f, 32.0f);
        float swing = (float)(d * o + m * p) * CustomCapeRenderLayer.easeOutSine(1.0f / (float)partCount * (float)part) * 100.0f;
        swing = u_530_F.n_1700_B(swing, 0.0f, 150.0f * CustomCapeRenderLayer.easeOutSine(1.0f / (float)partCount * (float)part));
        float sidewaysRotationOffset = (float)(d * p - m * o) * 100.0f;
        sidewaysRotationOffset = u_530_F.n_1700_B(sidewaysRotationOffset, -20.0f, 20.0f);
        float t = u_530_F.v_4262_N(h, abstractClientPlayer.X_290_I, abstractClientPlayer.O_1795_e);
        height += u_530_F.n_1700_B(u_530_F.v_4262_N(h, abstractClientPlayer.V_1446_Y, abstractClientPlayer.PlayerInfo) * 6.0f) * 32.0f * t;
        if (abstractClientPlayer.Z_875_P()) {
            height += 25.0f;
            poseStack.n_1700_B(0.0, (double)0.15f, 0.0);
        }
        float naturalWindSwing = this.getNatrualWindSwing(part, abstractClientPlayer.z_1737_N());
        poseStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(6.0f + swing / 2.0f + height + naturalWindSwing));
        poseStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(sidewaysRotationOffset / 2.0f));
        poseStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - sidewaysRotationOffset / 2.0f));
    }

    private float getNatrualWindSwing(int part, boolean underwater) {
        long highlightedPart = System.currentTimeMillis() / (long)(underwater ? 9 : 3) % 360L;
        float relativePart = (float)(part + 1) / (float)partCount;
        if (Cape.config.windMode == WindMode.WAVES) {
            return (float)(Math.sin(Math.toRadians(relativePart * 360.0f - (float)highlightedPart)) * 3.0);
        }
        return 0.0f;
    }

    private static void addBackVertex(D_4792_h bufferBuilder, D_1098_v matrix, D_1098_v oldMatrix, float x1, float y1, float z1, float x2, float y2, float z2, int part, int light) {
        float i;
        if (x1 < x2) {
            i = x1;
            x1 = x2;
            x2 = i;
        }
        if (y1 < y2) {
            i = y1;
            y1 = y2;
            y2 = i;
            D_1098_v k = matrix;
            matrix = oldMatrix;
            oldMatrix = k;
        }
        float minU = 0.015625f;
        float maxU = 0.171875f;
        float minV = 0.03125f;
        float maxV = 0.53125f;
        float deltaV = maxV - minV;
        float vPerPart = deltaV / (float)partCount;
        maxV = minV + vPerPart * (float)(part + 1);
        bufferBuilder.n_1700_B(oldMatrix, x1, y2, z1).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(maxU, minV += vPerPart * (float)part).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(oldMatrix, x2, y2, z1).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(minU, minV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(matrix, x2, y1, z2).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(minU, maxV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(matrix, x1, y1, z2).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(maxU, maxV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
    }

    private static void addFrontVertex(D_4792_h bufferBuilder, D_1098_v matrix, D_1098_v oldMatrix, float x1, float y1, float z1, float x2, float y2, float z2, int part, int light) {
        float i;
        if (x1 < x2) {
            i = x1;
            x1 = x2;
            x2 = i;
        }
        if (y1 < y2) {
            i = y1;
            y1 = y2;
            y2 = i;
            D_1098_v k = matrix;
            matrix = oldMatrix;
            oldMatrix = k;
        }
        float minU = 0.1875f;
        float maxU = 0.34375f;
        float minV = 0.03125f;
        float maxV = 0.53125f;
        float deltaV = maxV - minV;
        float vPerPart = deltaV / (float)partCount;
        maxV = minV + vPerPart * (float)(part + 1);
        bufferBuilder.n_1700_B(oldMatrix, x1, y1, z1).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(maxU, maxV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(oldMatrix, x2, y1, z1).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(minU, maxV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(matrix, x2, y2, z2).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(minU, minV += vPerPart * (float)part).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(matrix, x1, y2, z2).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(maxU, minV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
    }

    private static void addLeftVertex(D_4792_h bufferBuilder, D_1098_v matrix, D_1098_v oldMatrix, float x1, float y1, float z1, float x2, float y2, float z2, int part, int light) {
        float i;
        if (x1 < x2) {
            i = x1;
            x1 = x2;
            x2 = i;
        }
        if (y1 < y2) {
            i = y1;
            y1 = y2;
            y2 = i;
        }
        float minU = 0.0f;
        float maxU = 0.015625f;
        float minV = 0.03125f;
        float maxV = 0.53125f;
        float deltaV = maxV - minV;
        float vPerPart = deltaV / (float)partCount;
        maxV = minV + vPerPart * (float)(part + 1);
        bufferBuilder.n_1700_B(matrix, x2, y1, z1).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(maxU, maxV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(matrix, x2, y1, z2).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(minU, maxV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(oldMatrix, x2, y2, z2).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(minU, minV += vPerPart * (float)part).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(oldMatrix, x2, y2, z1).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(maxU, minV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
    }

    private static void addRightVertex(D_4792_h bufferBuilder, D_1098_v matrix, D_1098_v oldMatrix, float x1, float y1, float z1, float x2, float y2, float z2, int part, int light) {
        float i;
        if (x1 < x2) {
            i = x1;
            x1 = x2;
            x2 = i;
        }
        if (y1 < y2) {
            i = y1;
            y1 = y2;
            y2 = i;
        }
        float minU = 0.171875f;
        float maxU = 0.1875f;
        float minV = 0.03125f;
        float maxV = 0.53125f;
        float deltaV = maxV - minV;
        float vPerPart = deltaV / (float)partCount;
        maxV = minV + vPerPart * (float)(part + 1);
        bufferBuilder.n_1700_B(matrix, x2, y1, z2).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(minU, maxV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(matrix, x2, y1, z1).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(maxU, maxV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(oldMatrix, x2, y2, z1).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(maxU, minV += vPerPart * (float)part).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(oldMatrix, x2, y2, z2).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(minU, minV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
    }

    private static void addBottomVertex(D_4792_h bufferBuilder, D_1098_v matrix, D_1098_v oldMatrix, float x1, float y1, float z1, float x2, float y2, float z2, int part, int light) {
        float i;
        if (x1 < x2) {
            i = x1;
            x1 = x2;
            x2 = i;
        }
        if (y1 < y2) {
            i = y1;
            y1 = y2;
            y2 = i;
        }
        float minU = 0.171875f;
        float maxU = 0.328125f;
        float minV = 0.0f;
        float maxV = 0.03125f;
        float deltaV = maxV - minV;
        float vPerPart = deltaV / (float)partCount;
        maxV = minV + vPerPart * (float)(part + 1);
        bufferBuilder.n_1700_B(oldMatrix, x1, y2, z2).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(maxU, minV += vPerPart * (float)part).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(oldMatrix, x2, y2, z2).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(minU, minV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(matrix, x2, y1, z1).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(minU, maxV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(matrix, x1, y1, z1).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(maxU, maxV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(1.0f, 0.0f, 0.0f).endVertex();
    }

    private static void addTopVertex(D_4792_h bufferBuilder, D_1098_v matrix, D_1098_v oldMatrix, float x1, float y1, float z1, float x2, float y2, float z2, int part, int light) {
        float i;
        if (x1 < x2) {
            i = x1;
            x1 = x2;
            x2 = i;
        }
        if (y1 < y2) {
            i = y1;
            y1 = y2;
            y2 = i;
        }
        float minU = 0.015625f;
        float maxU = 0.171875f;
        float minV = 0.0f;
        float maxV = 0.03125f;
        float deltaV = maxV - minV;
        float vPerPart = deltaV / (float)partCount;
        maxV = minV + vPerPart * (float)(part + 1);
        bufferBuilder.n_1700_B(oldMatrix, x1, y2, z1).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(maxU, maxV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(0.0f, 1.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(oldMatrix, x2, y2, z1).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(minU, maxV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(0.0f, 1.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(matrix, x2, y1, z2).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(minU, minV += vPerPart * (float)part).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(0.0f, 1.0f, 0.0f).endVertex();
        bufferBuilder.n_1700_B(matrix, x1, y1, z2).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(maxU, minV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).normal(0.0f, 1.0f, 0.0f).endVertex();
    }

    private CapeRenderer getCapeRenderer(X_4340_E abstractClientPlayer, o_3091_w multiBufferSource) {
        if (!abstractClientPlayer.T_2506_i() || !abstractClientPlayer.n_1700_B(E_4346_v.n_1700_B) || abstractClientPlayer.e_2887_G() == null) {
            return null;
        }
        CustomCapeRenderLayer.vanillaCape.vertexConsumer = multiBufferSource.getBuffer(o_2576_A.R_4764_Y(abstractClientPlayer.e_2887_G()));
        return vanillaCape;
    }

    private static float getWind(double posY) {
        float x = (float)(System.currentTimeMillis() % 3600000L) / 10000.0f;
        float mod = u_530_F.n_1700_B(0.005f * (float)posY, 0.0f, 1.0f);
        return u_530_F.n_1700_B((float)(Math.sin(2.0f * x) + Math.sin(Math.PI * (double)x)) * mod, 0.0f, 2.0f);
    }

    private static float easeOutSine(float x) {
        return (float)Math.sin((double)x * Math.PI / 2.0);
    }

    static {
        vanillaCape = new VanillaCapeRenderer();
    }
}



