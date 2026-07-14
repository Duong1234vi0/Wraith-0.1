/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL20
 *  org.lwjgl.opengl.GL30
 */
package net.optifine.shaders;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.D_4792_h;
import lightning.product.E_4918_z;
import lightning.product.H_1748_a;
import lightning.product.I_4817_s;
import lightning.product.TheEndPortalBlockEntity;
import lightning.product.L_3848_p;
import lightning.product.M_660_m;
import lightning.product.N_4263_v;
import lightning.product.O_1806_w;
import lightning.product.V_772_m;
import lightning.product.X_4340_E;
import lightning.product.X_933_l;
import lightning.product.Z_2491_A;
import lightning.product.Z_875_P;
import lightning.product.b_257_Y;
import lightning.product.b_4440_Q;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.f_2689_h;
import lightning.product.g_164_R;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_3572_K;
import lightning.product.i_2154_H;
import lightning.product.l_456_f;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.r_4811_B;
import lightning.product.w_2040_b;
import lightning.product.z_3539_x;
import lightning.product.z_4547_I;
import lightning.product.z_883_p;
import net.optifine.reflect.Reflector;
import net.optifine.render.GlBlendState;
import net.optifine.render.GlCullState;
import net.optifine.render.ICamera;
import net.optifine.render.RenderTypes;
import net.optifine.shaders.ClippingHelperDummy;
import net.optifine.shaders.DrawBuffers;
import net.optifine.shaders.GlState;
import net.optifine.shaders.RenderStage;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class ShadersRender {
    private static final g_2336_b END_PORTAL_TEXTURE = new g_2336_b("textures/entity/end_portal.png");

    public static void setFrustrumPosition(ICamera frustum, double x, double y, double z) {
        frustum.setCameraPosition(x, y, z);
    }

    public static void beginTerrainSolid() {
        if (Shaders.isRenderingWorld) {
            Shaders.fogEnabled = true;
            Shaders.useProgram(Shaders.ProgramTerrain);
            Shaders.setRenderStage(RenderStage.TERRAIN_SOLID);
        }
    }

    public static void beginTerrainCutoutMipped() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramTerrain);
            Shaders.setRenderStage(RenderStage.TERRAIN_CUTOUT_MIPPED);
        }
    }

    public static void beginTerrainCutout() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramTerrain);
            Shaders.setRenderStage(RenderStage.TERRAIN_CUTOUT);
        }
    }

    public static void endTerrain() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramTexturedLit);
            Shaders.setRenderStage(RenderStage.NONE);
        }
    }

    public static void beginTranslucent() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramWater);
            Shaders.setRenderStage(RenderStage.TERRAIN_TRANSLUCENT);
        }
    }

    public static void endTranslucent() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramTexturedLit);
            Shaders.setRenderStage(RenderStage.NONE);
        }
    }

    public static void beginTripwire() {
        if (Shaders.isRenderingWorld) {
            Shaders.setRenderStage(RenderStage.TRIPWIRE);
        }
    }

    public static void endTripwire() {
        if (Shaders.isRenderingWorld) {
            Shaders.setRenderStage(RenderStage.NONE);
        }
    }

    public static void renderHand0(M_660_m er, g_221_o matrixStackIn, h_3572_K activeRenderInfo, float partialTicks) {
        if (!Shaders.isShadowPass) {
            boolean flag = Shaders.isItemToRenderMainTranslucent();
            boolean flag1 = Shaders.isItemToRenderOffTranslucent();
            if (!flag || !flag1) {
                Shaders.readCenterDepth();
                Shaders.beginHand(matrixStackIn, false);
                GL30.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                Shaders.setSkipRenderHands(flag, flag1);
                er.n_1700_B(matrixStackIn, activeRenderInfo, partialTicks, true, false, false);
                Shaders.endHand(matrixStackIn);
                Shaders.setHandsRendered(!flag, !flag1);
                Shaders.setSkipRenderHands(false, false);
            }
        }
    }

    public static void renderHand1(M_660_m er, g_221_o matrixStackIn, h_3572_K activeRenderInfo, float partialTicks) {
        if (!Shaders.isShadowPass && !Shaders.isBothHandsRendered()) {
            Shaders.readCenterDepth();
            X_933_l.Q_4569_t();
            Shaders.beginHand(matrixStackIn, true);
            GL30.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            Shaders.setSkipRenderHands(Shaders.isHandRenderedMain(), Shaders.isHandRenderedOff());
            er.n_1700_B(matrixStackIn, activeRenderInfo, partialTicks, true, false, true);
            Shaders.endHand(matrixStackIn);
            Shaders.setHandsRendered(true, true);
            Shaders.setSkipRenderHands(false, false);
        }
    }

    public static void renderItemFP(l_456_f itemRenderer, float partialTicks, g_221_o matrixStackIn, o_3091_w.n_1700_B bufferIn, X_4340_E playerEntityIn, int combinedLightIn, boolean renderTranslucent) {
        MinecraftClient.A_4115_X().u_1723_Y.R_4764_Y = playerEntityIn;
        X_933_l.n_1700_B(true);
        if (renderTranslucent) {
            X_933_l.J_1907_R(519);
            matrixStackIn.n_1700_B();
            DrawBuffers drawbuffers = GlState.getDrawBuffers();
            GlState.setDrawBuffers(Shaders.drawBuffersNone);
            Shaders.renderItemKeepDepthMask = true;
            itemRenderer.n_1700_B(partialTicks, matrixStackIn, bufferIn, playerEntityIn, combinedLightIn);
            Shaders.renderItemKeepDepthMask = false;
            GlState.setDrawBuffers(drawbuffers);
            matrixStackIn.J_1907_R();
        }
        X_933_l.J_1907_R(515);
        GL30.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        itemRenderer.n_1700_B(partialTicks, matrixStackIn, bufferIn, playerEntityIn, combinedLightIn);
        MinecraftClient.A_4115_X().u_1723_Y.R_4764_Y = null;
    }

    public static void renderItemFP(l_456_f itemRenderer, float partialTicks, g_221_o matrixStackIn, o_3091_w.n_1700_B bufferIn, Z_875_P playerEntityIn, int combinedLightIn, boolean renderTranslucent) {
        MinecraftClient.A_4115_X().u_1723_Y.R_4764_Y = playerEntityIn;
        X_933_l.n_1700_B(true);
        if (renderTranslucent) {
            X_933_l.J_1907_R(519);
            matrixStackIn.n_1700_B();
            DrawBuffers drawbuffers = GlState.getDrawBuffers();
            GlState.setDrawBuffers(Shaders.drawBuffersNone);
            Shaders.renderItemKeepDepthMask = true;
            itemRenderer.n_1700_B(partialTicks, matrixStackIn, bufferIn, playerEntityIn, combinedLightIn);
            Shaders.renderItemKeepDepthMask = false;
            GlState.setDrawBuffers(drawbuffers);
            matrixStackIn.J_1907_R();
        }
        X_933_l.J_1907_R(515);
        GL30.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        itemRenderer.n_1700_B(partialTicks, matrixStackIn, bufferIn, playerEntityIn, combinedLightIn);
        MinecraftClient.A_4115_X().u_1723_Y.R_4764_Y = null;
    }

    public static void renderFPOverlay(M_660_m er, g_221_o matrixStackIn, h_3572_K activeRenderInfo, float partialTicks) {
        if (!Shaders.isShadowPass) {
            Shaders.beginFPOverlay();
            er.n_1700_B(matrixStackIn, activeRenderInfo, partialTicks, false, true, false);
            Shaders.endFPOverlay();
        }
    }

    public static void beginBlockDamage() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramDamagedBlock);
            Shaders.setRenderStage(RenderStage.DESTROY);
            if (Shaders.ProgramDamagedBlock.getId() == Shaders.ProgramTerrain.getId()) {
                GlState.setDrawBuffers(Shaders.drawBuffersColorAtt[0]);
                X_933_l.n_1700_B(false);
            }
        }
    }

    public static void endBlockDamage() {
        if (Shaders.isRenderingWorld) {
            X_933_l.n_1700_B(true);
            Shaders.useProgram(Shaders.ProgramTexturedLit);
            Shaders.setRenderStage(RenderStage.NONE);
        }
    }

    public static void beginOutline() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramBasic);
            Shaders.setRenderStage(RenderStage.OUTLINE);
        }
    }

    public static void endOutline() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramTexturedLit);
            Shaders.setRenderStage(RenderStage.NONE);
        }
    }

    public static void beginDebug() {
        if (Shaders.isRenderingWorld) {
            Shaders.setRenderStage(RenderStage.DEBUG);
        }
    }

    public static void endDebug() {
        if (Shaders.isRenderingWorld) {
            Shaders.setRenderStage(RenderStage.NONE);
        }
    }

    public static void renderShadowMap(M_660_m entityRenderer, h_3572_K activeRenderInfo, int pass, float partialTicks, long finishTimeNano) {
        if (Shaders.hasShadowMap) {
            MinecraftClient minecraft = MinecraftClient.A_4115_X();
            minecraft.PlayerInfo().J_1907_R("shadow pass");
            z_883_p worldrenderer = minecraft.u_1723_Y;
            Shaders.isShadowPass = true;
            Shaders.updateProjectionMatrix();
            Shaders.checkGLError("pre shadow");
            GL30.glMatrixMode((int)5889);
            GL11.glPushMatrix();
            GL30.glMatrixMode((int)5888);
            GL11.glPushMatrix();
            minecraft.PlayerInfo().J_1907_R("shadow clear");
            Shaders.sfb.bindFramebuffer();
            Shaders.checkGLError("shadow bind sfb");
            minecraft.PlayerInfo().J_1907_R("shadow camera");
            ShadersRender.updateActiveRenderInfo(activeRenderInfo, minecraft, partialTicks);
            g_221_o matrixstack = new g_221_o();
            Shaders.setCameraShadow(matrixstack, activeRenderInfo, partialTicks);
            Shaders.checkGLError("shadow camera");
            Shaders.dispatchComputes(Shaders.dfb, Shaders.ProgramShadow.getComputePrograms());
            Shaders.useProgram(Shaders.ProgramShadow);
            Shaders.sfb.setDrawBuffers();
            Shaders.checkGLError("shadow drawbuffers");
            GL30.glReadBuffer((int)0);
            Shaders.checkGLError("shadow readbuffer");
            Shaders.sfb.setDepthTexture();
            Shaders.sfb.setColorTextures(true);
            Shaders.checkFramebufferStatus("shadow fb");
            GL30.glClearColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL30.glClear((int)256);
            for (int i = 0; i < Shaders.usedShadowColorBuffers; ++i) {
                if (!Shaders.shadowBuffersClear[i]) continue;
                Z_2491_A vector4f = Shaders.shadowBuffersClearColor[i];
                if (vector4f != null) {
                    GL30.glClearColor((float)vector4f.n_1700_B(), (float)vector4f.J_1907_R(), (float)vector4f.R_4764_Y(), (float)vector4f.G_564_y());
                } else {
                    GL30.glClearColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                }
                GlState.setDrawBuffers(Shaders.drawBuffersColorAtt[i]);
                GL30.glClear((int)16384);
            }
            Shaders.sfb.setDrawBuffers();
            Shaders.checkGLError("shadow clear");
            minecraft.PlayerInfo().J_1907_R("shadow frustum");
            ClippingHelperDummy clippinghelper = new ClippingHelperDummy();
            minecraft.PlayerInfo().J_1907_R("shadow culling");
            e_2866_D vector3d = activeRenderInfo.J_1907_R();
            clippinghelper.setCameraPosition(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
            X_933_l.Y_601_j(7425);
            X_933_l.P_4830_p();
            X_933_l.J_1907_R(515);
            X_933_l.n_1700_B(true);
            X_933_l.n_1700_B(true, true, true, true);
            X_933_l.n_1700_B(new GlCullState(false));
            X_933_l.n_1700_B(new GlBlendState(false));
            minecraft.PlayerInfo().J_1907_R("shadow prepareterrain");
            minecraft.G_624_v().n_1700_B(L_3848_p.n_1700_B);
            minecraft.PlayerInfo().J_1907_R("shadow setupterrain");
            int j = minecraft.u_1723_Y.Z_875_P();
            worldrenderer.n_1700_B(activeRenderInfo, clippinghelper, false, j, minecraft.Y_259_p.d_2461_k());
            minecraft.PlayerInfo().J_1907_R("shadow updatechunks");
            minecraft.PlayerInfo().J_1907_R("shadow terrain");
            double d0 = vector3d.n_1700_B();
            double d1 = vector3d.J_1907_R();
            double d2 = vector3d.R_4764_Y();
            X_933_l.C_2741_M(5888);
            X_933_l.g_221_o();
            if (Shaders.isRenderShadowTerrain()) {
                X_933_l.G_564_y();
                worldrenderer.n_1700_B(RenderTypes.SOLID, matrixstack, d0, d1, d2);
                Shaders.checkGLError("shadow terrain solid");
                X_933_l.P_1922_E();
                worldrenderer.n_1700_B(RenderTypes.CUTOUT_MIPPED, matrixstack, d0, d1, d2);
                Shaders.checkGLError("shadow terrain cutoutmipped");
                minecraft.G_624_v().J_1907_R(L_3848_p.n_1700_B).setBlurMipmapDirect(false, false);
                worldrenderer.n_1700_B(RenderTypes.CUTOUT, matrixstack, d0, d1, d2);
                minecraft.G_624_v().J_1907_R(L_3848_p.n_1700_B).restoreLastBlurMipmap();
                Shaders.checkGLError("shadow terrain cutout");
            }
            X_933_l.Y_601_j(7424);
            X_933_l.n_1700_B(516, 0.1f);
            X_933_l.C_2741_M(5888);
            X_933_l.e_2887_G();
            X_933_l.g_221_o();
            minecraft.PlayerInfo().J_1907_R("shadow entities");
            z_883_p worldrenderer1 = minecraft.u_1723_Y;
            w_2040_b entityrenderermanager = minecraft.O_508_d();
            o_3091_w.n_1700_B irendertypebuffer$impl = worldrenderer1.c_3005_b().J_1907_R();
            boolean flag = Shaders.isShadowPass && !minecraft.Y_259_p.d_2461_k();
            Iterator<z_883_p.n_1700_B> iterator = (Shaders.isRenderShadowEntities() ? worldrenderer1.H_2857_Y() : Collections.EMPTY_LIST).iterator();
            while (iterator.hasNext()) {
                z_883_p.n_1700_B worldrenderer$localrenderinformationcontainer0;
                z_883_p.n_1700_B worldrenderer$localrenderinformationcontainer = worldrenderer$localrenderinformationcontainer0 = iterator.next();
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender = worldrenderer$localrenderinformationcontainer.n_1700_B;
                H_1748_a chunk = chunkrenderdispatcher$chunkrender.P_4830_p();
                for (N_4263_v entity : chunk.getEntityLists()[chunkrenderdispatcher$chunkrender.P_1922_E().getY() / 16]) {
                    if (!entityrenderermanager.n_1700_B(entity, clippinghelper, d0, d1, d2) && !entity.Q_2552_b(minecraft.Y_259_p) || entity == activeRenderInfo.v_4262_N() && !flag && !activeRenderInfo.t_148_a() && (!(activeRenderInfo.v_4262_N() instanceof r_4811_B) || !((r_4811_B)activeRenderInfo.v_4262_N()).z_2372_L()) || entity instanceof V_772_m && activeRenderInfo.v_4262_N() != entity) continue;
                    worldrenderer1.R_4764_Y = entity;
                    Shaders.nextEntity(entity);
                    worldrenderer1.n_1700_B(entity, d0, d1, d2, partialTicks, matrixstack, irendertypebuffer$impl);
                    worldrenderer1.R_4764_Y = null;
                }
            }
            worldrenderer1.n_1700_B(matrixstack);
            irendertypebuffer$impl.n_1700_B(o_2576_A.J_1907_R(L_3848_p.n_1700_B));
            irendertypebuffer$impl.n_1700_B(o_2576_A.R_4764_Y(L_3848_p.n_1700_B));
            irendertypebuffer$impl.n_1700_B(o_2576_A.G_564_y(L_3848_p.n_1700_B));
            irendertypebuffer$impl.n_1700_B(o_2576_A.t_148_a(L_3848_p.n_1700_B));
            Shaders.endEntities();
            Shaders.beginBlockEntities();
            O_1806_w.n_1700_B();
            boolean flag1 = Reflector.IForgeTileEntity_getRenderBoundingBox.exists();
            ClippingHelperDummy clippinghelper1 = clippinghelper;
            Iterator<z_883_p.n_1700_B> iterator2 = (Shaders.isRenderShadowBlockEntities() ? worldrenderer1.A_4115_X() : Collections.EMPTY_LIST).iterator();
            while (iterator2.hasNext()) {
                z_883_p.n_1700_B worldrenderer$localrenderinformationcontainer10;
                z_883_p.n_1700_B worldrenderer$localrenderinformationcontainer1 = worldrenderer$localrenderinformationcontainer10 = iterator2.next();
                List<i_2154_H> list = worldrenderer$localrenderinformationcontainer1.n_1700_B.R_4764_Y().J_1907_R();
                if (list.isEmpty()) continue;
                for (i_2154_H tileentity : list) {
                    I_4817_s axisalignedbb;
                    if (flag1 && (axisalignedbb = (I_4817_s)Reflector.call(tileentity, Reflector.IForgeTileEntity_getRenderBoundingBox, new Object[0])) != null && !((E_4918_z)clippinghelper1).isBoundingBoxInFrustum(axisalignedbb)) continue;
                    Shaders.nextBlockEntity(tileentity);
                    c_1514_x blockpos = tileentity.x_607_J();
                    matrixstack.n_1700_B();
                    matrixstack.n_1700_B((double)blockpos.getX() - d0, (double)blockpos.getY() - d1, (double)blockpos.getZ() - d2);
                    f_2689_h.J_1907_R.n_1700_B(tileentity, partialTicks, matrixstack, irendertypebuffer$impl);
                    matrixstack.J_1907_R();
                }
            }
            worldrenderer1.n_1700_B(matrixstack);
            irendertypebuffer$impl.n_1700_B(o_2576_A.u_1723_Y());
            irendertypebuffer$impl.n_1700_B(b_4440_Q.v_4262_N());
            irendertypebuffer$impl.n_1700_B(b_4440_Q.w_1484_f());
            irendertypebuffer$impl.n_1700_B(b_4440_Q.R_4764_Y());
            irendertypebuffer$impl.n_1700_B(b_4440_Q.G_564_y());
            irendertypebuffer$impl.n_1700_B(b_4440_Q.P_1922_E());
            irendertypebuffer$impl.n_1700_B(b_4440_Q.u_1723_Y());
            irendertypebuffer$impl.J_1907_R();
            Shaders.endBlockEntities();
            Shaders.checkGLError("shadow entities");
            X_933_l.C_2741_M(5888);
            X_933_l.e_2887_G();
            X_933_l.n_1700_B(true);
            X_933_l.h_1847_R();
            X_933_l.s_2632_s();
            X_933_l.A_4115_X();
            X_933_l.J_1907_R(770, 771, 1, 0);
            X_933_l.n_1700_B(516, 0.1f);
            if (Shaders.usedShadowDepthBuffers >= 2) {
                X_933_l.t_1786_h(33989);
                Shaders.checkGLError("pre copy shadow depth");
                GL11.glCopyTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)0, (int)0, (int)Shaders.shadowMapWidth, (int)Shaders.shadowMapHeight);
                Shaders.checkGLError("copy shadow depth");
                X_933_l.t_1786_h(33984);
            }
            X_933_l.h_1847_R();
            X_933_l.n_1700_B(true);
            minecraft.G_624_v().n_1700_B(L_3848_p.n_1700_B);
            X_933_l.Y_601_j(7425);
            Shaders.checkGLError("shadow pre-translucent");
            Shaders.sfb.setDrawBuffers();
            Shaders.checkGLError("shadow drawbuffers pre-translucent");
            Shaders.checkFramebufferStatus("shadow pre-translucent");
            if (Shaders.isRenderShadowTranslucent()) {
                minecraft.PlayerInfo().J_1907_R("shadow translucent");
                worldrenderer.n_1700_B(RenderTypes.TRANSLUCENT, matrixstack, d0, d1, d2);
                Shaders.checkGLError("shadow translucent");
            }
            X_933_l.D_4792_h();
            X_933_l.Y_601_j(7424);
            X_933_l.n_1700_B(true);
            X_933_l.A_4115_X();
            X_933_l.h_1847_R();
            GL30.glFlush();
            Shaders.checkGLError("shadow flush");
            Shaders.isShadowPass = false;
            minecraft.PlayerInfo().J_1907_R("shadow postprocess");
            if (Shaders.hasGlGenMipmap) {
                Shaders.sfb.generateDepthMipmaps(Shaders.shadowMipmapEnabled);
                Shaders.sfb.generateColorMipmaps(true, Shaders.shadowColorMipmapEnabled);
            }
            Shaders.checkGLError("shadow postprocess");
            if (Shaders.hasShadowcompPrograms) {
                Shaders.renderShadowComposites();
            }
            Shaders.dfb.bindFramebuffer();
            GL11.glViewport((int)0, (int)0, (int)Shaders.renderWidth, (int)Shaders.renderHeight);
            GlState.setDrawBuffers(null);
            minecraft.G_624_v().n_1700_B(L_3848_p.n_1700_B);
            Shaders.useProgram(Shaders.ProgramTerrain);
            GL30.glMatrixMode((int)5888);
            GL11.glPopMatrix();
            GL30.glMatrixMode((int)5889);
            GL11.glPopMatrix();
            GL30.glMatrixMode((int)5888);
            Shaders.checkGLError("shadow end");
        }
    }

    public static void updateActiveRenderInfo(h_3572_K activeRenderInfo, MinecraftClient mc, float partialTicks) {
        activeRenderInfo.n_1700_B(mc.Y_601_j, mc.g_2268_R() == null ? mc.Y_259_p : mc.g_2268_R(), !mc.P_4830_p.P_4830_p().n_1700_B(), mc.P_4830_p.P_4830_p().J_1907_R(), partialTicks);
    }

    public static void preRenderChunkLayer(o_2576_A blockLayerIn) {
        if (blockLayerIn == RenderTypes.SOLID) {
            ShadersRender.beginTerrainSolid();
        }
        if (blockLayerIn == RenderTypes.CUTOUT_MIPPED) {
            ShadersRender.beginTerrainCutoutMipped();
        }
        if (blockLayerIn == RenderTypes.CUTOUT) {
            ShadersRender.beginTerrainCutout();
        }
        if (blockLayerIn == RenderTypes.TRANSLUCENT) {
            ShadersRender.beginTranslucent();
        }
        if (blockLayerIn == o_2576_A.Q_2552_b()) {
            ShadersRender.beginTripwire();
        }
        if (Shaders.isRenderBackFace(blockLayerIn)) {
            X_933_l.Y_1740_V();
        }
        if (g_164_R.w_1484_f()) {
            GL20.glEnableVertexAttribArray((int)Shaders.midBlockAttrib);
            GL20.glEnableVertexAttribArray((int)Shaders.midTexCoordAttrib);
            GL20.glEnableVertexAttribArray((int)Shaders.tangentAttrib);
            GL20.glEnableVertexAttribArray((int)Shaders.entityAttrib);
        }
    }

    public static void postRenderChunkLayer(o_2576_A blockLayerIn) {
        if (g_164_R.w_1484_f()) {
            GL20.glDisableVertexAttribArray((int)Shaders.midBlockAttrib);
            GL20.glDisableVertexAttribArray((int)Shaders.midTexCoordAttrib);
            GL20.glDisableVertexAttribArray((int)Shaders.tangentAttrib);
            GL20.glDisableVertexAttribArray((int)Shaders.entityAttrib);
        }
        if (Shaders.isRenderBackFace(blockLayerIn)) {
            X_933_l.A_4115_X();
        }
    }

    public static void preRender(o_2576_A renderType, D_3318_r buffer) {
        if (Shaders.isRenderingWorld && !Shaders.isShadowPass) {
            if (renderType.d_2427_y()) {
                ShadersRender.renderEnchantedGlintBegin();
            } else if (renderType.R_4764_Y().equals("eyes")) {
                Shaders.beginSpiderEyes();
            } else if (renderType.R_4764_Y().equals("crumbling")) {
                ShadersRender.beginBlockDamage();
            } else if (renderType == o_2576_A.H_1990_U) {
                Shaders.beginLeash();
            }
        }
    }

    public static void postRender(o_2576_A renderType, D_3318_r buffer) {
        if (Shaders.isRenderingWorld && !Shaders.isShadowPass) {
            if (renderType.d_2427_y()) {
                ShadersRender.renderEnchantedGlintEnd();
            } else if (renderType.R_4764_Y().equals("eyes")) {
                Shaders.endSpiderEyes();
            } else if (renderType.R_4764_Y().equals("crumbling")) {
                ShadersRender.endBlockDamage();
            } else if (renderType == o_2576_A.H_1990_U) {
                Shaders.endLeash();
            }
        }
    }

    public static void setupArrayPointersVbo() {
        int i = 18;
        GL20.glVertexAttribPointer((int)Shaders.midBlockAttrib, (int)3, (int)5120, (boolean)false, (int)72, (long)32L);
        GL20.glVertexAttribPointer((int)Shaders.midTexCoordAttrib, (int)2, (int)5126, (boolean)false, (int)72, (long)36L);
        GL20.glVertexAttribPointer((int)Shaders.tangentAttrib, (int)4, (int)5122, (boolean)false, (int)72, (long)44L);
        GL20.glVertexAttribPointer((int)Shaders.entityAttrib, (int)3, (int)5122, (boolean)false, (int)72, (long)52L);
    }

    public static void beaconBeamBegin() {
        Shaders.useProgram(Shaders.ProgramBeaconBeam);
    }

    public static void beaconBeamStartQuad1() {
    }

    public static void beaconBeamStartQuad2() {
    }

    public static void beaconBeamDraw1() {
    }

    public static void beaconBeamDraw2() {
        X_933_l.h_1847_R();
    }

    public static void renderEnchantedGlintBegin() {
        Shaders.useProgram(Shaders.ProgramArmorGlint);
    }

    public static void renderEnchantedGlintEnd() {
        if (Shaders.isRenderingWorld) {
            if (Shaders.isRenderingFirstPersonHand() && Shaders.isRenderBothHands()) {
                Shaders.useProgram(Shaders.ProgramHand);
            } else {
                Shaders.useProgram(Shaders.ProgramEntities);
            }
        } else {
            Shaders.useProgram(Shaders.ProgramNone);
        }
    }

    public static boolean renderEndPortal(TheEndPortalBlockEntity te, float partialTicks, float offset, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (!Shaders.isShadowPass && Shaders.activeProgram.getId() == 0) {
            return false;
        }
        X_933_l.v_4262_N();
        g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
        D_1098_v matrix4f = matrixstack$entry.n_1700_B();
        o_1290_k matrix3f = matrixstack$entry.J_1907_R();
        D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.J_1907_R(END_PORTAL_TEXTURE));
        float f = 0.5f;
        float f1 = f * 0.15f;
        float f2 = f * 0.3f;
        float f3 = f * 0.4f;
        float f4 = 0.0f;
        float f5 = 0.2f;
        float f6 = (float)(System.currentTimeMillis() % 100000L) / 100000.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        if (te.n_1700_B(b_257_Y.G_564_y)) {
            z_3539_x vector3i = b_257_Y.G_564_y.M_182_A();
            float f10 = vector3i.getX();
            float f11 = vector3i.getY();
            float f12 = vector3i.getZ();
            float f13 = matrix3f.J_1907_R(f10, f11, f12);
            float f14 = matrix3f.R_4764_Y(f10, f11, f12);
            float f15 = matrix3f.G_564_y(f10, f11, f12);
            ivertexbuilder.n_1700_B(matrix4f, f7, f8, f9 + 1.0f).n_1700_B(f1, f2, f3, 1.0f).tex(f4 + f6, f4 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f13, f14, f15).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7 + 1.0f, f8, f9 + 1.0f).n_1700_B(f1, f2, f3, 1.0f).tex(f4 + f6, f5 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f13, f14, f15).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7 + 1.0f, f8 + 1.0f, f9 + 1.0f).n_1700_B(f1, f2, f3, 1.0f).tex(f5 + f6, f5 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f13, f14, f15).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7, f8 + 1.0f, f9 + 1.0f).n_1700_B(f1, f2, f3, 1.0f).tex(f5 + f6, f4 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f13, f14, f15).endVertex();
        }
        if (te.n_1700_B(b_257_Y.R_4764_Y)) {
            z_3539_x vector3i1 = b_257_Y.R_4764_Y.M_182_A();
            float f16 = vector3i1.getX();
            float f21 = vector3i1.getY();
            float f26 = vector3i1.getZ();
            float f31 = matrix3f.J_1907_R(f16, f21, f26);
            float f36 = matrix3f.R_4764_Y(f16, f21, f26);
            float f41 = matrix3f.G_564_y(f16, f21, f26);
            ivertexbuilder.n_1700_B(matrix4f, f7, f8 + 1.0f, f9).n_1700_B(f1, f2, f3, 1.0f).tex(f5 + f6, f5 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f31, f36, f41).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7 + 1.0f, f8 + 1.0f, f9).n_1700_B(f1, f2, f3, 1.0f).tex(f5 + f6, f4 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f31, f36, f41).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7 + 1.0f, f8, f9).n_1700_B(f1, f2, f3, 1.0f).tex(f4 + f6, f4 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f31, f36, f41).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7, f8, f9).n_1700_B(f1, f2, f3, 1.0f).tex(f4 + f6, f5 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f31, f36, f41).endVertex();
        }
        if (te.n_1700_B(b_257_Y.u_1723_Y)) {
            z_3539_x vector3i2 = b_257_Y.u_1723_Y.M_182_A();
            float f17 = vector3i2.getX();
            float f22 = vector3i2.getY();
            float f27 = vector3i2.getZ();
            float f32 = matrix3f.J_1907_R(f17, f22, f27);
            float f37 = matrix3f.R_4764_Y(f17, f22, f27);
            float f42 = matrix3f.G_564_y(f17, f22, f27);
            ivertexbuilder.n_1700_B(matrix4f, f7 + 1.0f, f8 + 1.0f, f9).n_1700_B(f1, f2, f3, 1.0f).tex(f5 + f6, f5 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f32, f37, f42).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7 + 1.0f, f8 + 1.0f, f9 + 1.0f).n_1700_B(f1, f2, f3, 1.0f).tex(f5 + f6, f4 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f32, f37, f42).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7 + 1.0f, f8, f9 + 1.0f).n_1700_B(f1, f2, f3, 1.0f).tex(f4 + f6, f4 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f32, f37, f42).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7 + 1.0f, f8, f9).n_1700_B(f1, f2, f3, 1.0f).tex(f4 + f6, f5 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f32, f37, f42).endVertex();
        }
        if (te.n_1700_B(b_257_Y.P_1922_E)) {
            z_3539_x vector3i3 = b_257_Y.P_1922_E.M_182_A();
            float f18 = vector3i3.getX();
            float f23 = vector3i3.getY();
            float f28 = vector3i3.getZ();
            float f33 = matrix3f.J_1907_R(f18, f23, f28);
            float f38 = matrix3f.R_4764_Y(f18, f23, f28);
            float f43 = matrix3f.G_564_y(f18, f23, f28);
            ivertexbuilder.n_1700_B(matrix4f, f7, f8, f9).n_1700_B(f1, f2, f3, 1.0f).tex(f4 + f6, f4 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f33, f38, f43).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7, f8, f9 + 1.0f).n_1700_B(f1, f2, f3, 1.0f).tex(f4 + f6, f5 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f33, f38, f43).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7, f8 + 1.0f, f9 + 1.0f).n_1700_B(f1, f2, f3, 1.0f).tex(f5 + f6, f5 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f33, f38, f43).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7, f8 + 1.0f, f9).n_1700_B(f1, f2, f3, 1.0f).tex(f5 + f6, f4 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f33, f38, f43).endVertex();
        }
        if (te.n_1700_B(b_257_Y.n_1700_B)) {
            z_3539_x vector3i4 = b_257_Y.n_1700_B.M_182_A();
            float f19 = vector3i4.getX();
            float f24 = vector3i4.getY();
            float f29 = vector3i4.getZ();
            float f34 = matrix3f.J_1907_R(f19, f24, f29);
            float f39 = matrix3f.R_4764_Y(f19, f24, f29);
            float f44 = matrix3f.G_564_y(f19, f24, f29);
            ivertexbuilder.n_1700_B(matrix4f, f7, f8, f9).n_1700_B(f1, f2, f3, 1.0f).tex(f4 + f6, f4 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f34, f39, f44).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7 + 1.0f, f8, f9).n_1700_B(f1, f2, f3, 1.0f).tex(f4 + f6, f5 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f34, f39, f44).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7 + 1.0f, f8, f9 + 1.0f).n_1700_B(f1, f2, f3, 1.0f).tex(f5 + f6, f5 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f34, f39, f44).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7, f8, f9 + 1.0f).n_1700_B(f1, f2, f3, 1.0f).tex(f5 + f6, f4 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f34, f39, f44).endVertex();
        }
        if (te.n_1700_B(b_257_Y.J_1907_R)) {
            z_3539_x vector3i5 = b_257_Y.J_1907_R.M_182_A();
            float f20 = vector3i5.getX();
            float f25 = vector3i5.getY();
            float f30 = vector3i5.getZ();
            float f35 = matrix3f.J_1907_R(f20, f25, f30);
            float f40 = matrix3f.R_4764_Y(f20, f25, f30);
            float f45 = matrix3f.G_564_y(f20, f25, f30);
            ivertexbuilder.n_1700_B(matrix4f, f7, f8 + offset, f9 + 1.0f).n_1700_B(f1, f2, f3, 1.0f).tex(f4 + f6, f4 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f35, f40, f45).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7 + 1.0f, f8 + offset, f9 + 1.0f).n_1700_B(f1, f2, f3, 1.0f).tex(f4 + f6, f5 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f35, f40, f45).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7 + 1.0f, f8 + offset, f9).n_1700_B(f1, f2, f3, 1.0f).tex(f5 + f6, f5 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f35, f40, f45).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, f7, f8 + offset, f9).n_1700_B(f1, f2, f3, 1.0f).tex(f5 + f6, f4 + f6).R_4764_Y(combinedOverlayIn).J_1907_R(combinedLightIn).normal(f35, f40, f45).endVertex();
        }
        X_933_l.u_1723_Y();
        return true;
    }
}



