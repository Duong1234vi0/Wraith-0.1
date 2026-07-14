/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.player;

import java.util.regex.Pattern;
import lightning.product.C_3240_x;
import lightning.product.P_4645_d;
import lightning.product.X_4340_E;
import lightning.product.MinecraftClient;
import lightning.product.c_4477_a;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.r_1020_F;
import net.optifine.Config;
import net.optifine.player.CapeImageBuffer;
import net.optifine.util.TextureUtils;

public class CapeUtils {
    private static final Pattern PATTERN_USERNAME = Pattern.compile("[a-zA-Z0-9_]+");

    public static void downloadCape(X_4340_E player) {
        String s = player.c_4037_x();
        if (s != null && !s.isEmpty() && !s.contains("\u0000") && PATTERN_USERNAME.matcher(s).matches()) {
            String s1 = "http://s.optifine.net/capes/" + s + ".png";
            g_2336_b resourcelocation = new g_2336_b("capeof/" + s);
            C_3240_x texturemanager = MinecraftClient.A_4115_X().G_624_v();
            c_4477_a texture = texturemanager.J_1907_R(resourcelocation);
            if (texture != null && texture instanceof r_1020_F) {
                r_1020_F downloadingtexture = (r_1020_F)texture;
                if (downloadingtexture.n_1700_B != null) {
                    if (downloadingtexture.n_1700_B.booleanValue()) {
                        player.n_1700_B(resourcelocation);
                        if (downloadingtexture.n_1700_B() instanceof CapeImageBuffer) {
                            CapeImageBuffer capeimagebuffer1 = (CapeImageBuffer)downloadingtexture.n_1700_B();
                            player.G_564_y(capeimagebuffer1.isElytraOfCape());
                        }
                    }
                    return;
                }
            }
            CapeImageBuffer capeimagebuffer = new CapeImageBuffer(player, resourcelocation);
            g_2336_b resourcelocation1 = TextureUtils.LOCATION_TEXTURE_EMPTY;
            r_1020_F downloadingtexture1 = new r_1020_F(null, s1, resourcelocation1, false, capeimagebuffer);
            downloadingtexture1.J_1907_R = true;
            texturemanager.n_1700_B(resourcelocation, downloadingtexture1);
        }
    }

    public static i_2518_W parseCape(i_2518_W img) {
        int j;
        int i = 64;
        int k = img.n_1700_B();
        int l = img.J_1907_R();
        for (j = 32; i < k || j < l; i *= 2, j *= 2) {
        }
        i_2518_W nativeimage = new i_2518_W(i, j, true);
        nativeimage.n_1700_B(img);
        img.close();
        return nativeimage;
    }

    public static boolean isElytraCape(i_2518_W imageRaw, i_2518_W imageFixed) {
        return imageRaw.n_1700_B() > imageFixed.J_1907_R();
    }

    public static void reloadCape(X_4340_E player) {
        String s = player.c_4037_x();
        g_2336_b resourcelocation = new g_2336_b("capeof/" + s);
        C_3240_x texturemanager = Config.getTextureManager();
        c_4477_a texture = texturemanager.J_1907_R(resourcelocation);
        if (texture instanceof P_4645_d) {
            P_4645_d simpletexture = (P_4645_d)texture;
            simpletexture.deleteGlTexture();
            texturemanager.R_4764_Y(resourcelocation);
        }
        player.n_1700_B((g_2336_b)null);
        player.G_564_y(false);
        CapeUtils.downloadCape(player);
    }
}


