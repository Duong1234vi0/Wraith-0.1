/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.player;

import java.awt.Dimension;
import lightning.product.D_4792_h;
import lightning.product.T_1114_L;
import lightning.product.X_4340_E;
import lightning.product.MinecraftClient;
import lightning.product.c_4477_a;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.n_1658_l;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import net.optifine.player.PlayerItemRenderer;

public class PlayerItemModel {
    private Dimension textureSize = null;
    private boolean usePlayerTexture = false;
    private PlayerItemRenderer[] modelRenderers = new PlayerItemRenderer[0];
    private g_2336_b textureLocation = null;
    private i_2518_W textureImage = null;
    private T_1114_L texture = null;
    private g_2336_b locationMissing = new g_2336_b("textures/block/red_wool.png");
    public static final int ATTACH_BODY = 0;
    public static final int ATTACH_HEAD = 1;
    public static final int ATTACH_LEFT_ARM = 2;
    public static final int ATTACH_RIGHT_ARM = 3;
    public static final int ATTACH_LEFT_LEG = 4;
    public static final int ATTACH_RIGHT_LEG = 5;
    public static final int ATTACH_CAPE = 6;

    public PlayerItemModel(Dimension textureSize, boolean usePlayerTexture, PlayerItemRenderer[] modelRenderers) {
        this.textureSize = textureSize;
        this.usePlayerTexture = usePlayerTexture;
        this.modelRenderers = modelRenderers;
    }

    public void render(n_1658_l modelBiped, X_4340_E player, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, int packedOverlayIn) {
        g_2336_b resourcelocation = this.locationMissing;
        if (this.usePlayerTexture) {
            resourcelocation = player.g_221_o();
        } else if (this.textureLocation != null) {
            if (this.texture == null && this.textureImage != null) {
                this.texture = new T_1114_L(this.textureImage);
                MinecraftClient.A_4115_X().G_624_v().n_1700_B(this.textureLocation, (c_4477_a)this.texture);
            }
            resourcelocation = this.textureLocation;
        } else {
            resourcelocation = this.locationMissing;
        }
        for (int i = 0; i < this.modelRenderers.length; ++i) {
            PlayerItemRenderer playeritemrenderer = this.modelRenderers[i];
            matrixStackIn.n_1700_B();
            o_2576_A rendertype = o_2576_A.G_564_y(resourcelocation);
            D_4792_h ivertexbuilder = bufferIn.getBuffer(rendertype);
            playeritemrenderer.render(modelBiped, matrixStackIn, ivertexbuilder, packedLightIn, packedOverlayIn);
            matrixStackIn.J_1907_R();
        }
    }

    public static e_4189_z getAttachModel(n_1658_l modelBiped, int attachTo) {
        switch (attachTo) {
            case 0: {
                return modelBiped.R_4764_Y;
            }
            case 1: {
                return modelBiped.n_1700_B;
            }
            case 2: {
                return modelBiped.P_1922_E;
            }
            case 3: {
                return modelBiped.G_564_y;
            }
            case 4: {
                return modelBiped.v_4262_N;
            }
            case 5: {
                return modelBiped.u_1723_Y;
            }
        }
        return null;
    }

    public i_2518_W getTextureImage() {
        return this.textureImage;
    }

    public void setTextureImage(i_2518_W textureImage) {
        this.textureImage = textureImage;
    }

    public T_1114_L getTexture() {
        return this.texture;
    }

    public g_2336_b getTextureLocation() {
        return this.textureLocation;
    }

    public void setTextureLocation(g_2336_b textureLocation) {
        this.textureLocation = textureLocation;
    }

    public boolean isUsePlayerTexture() {
        return this.usePlayerTexture;
    }
}


