/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import lightning.product.B_3871_I;
import lightning.product.C_3240_x;
import lightning.product.L_3848_p;
import lightning.product.P_4645_d;
import lightning.product.MinecraftClient;
import lightning.product.c_4477_a;
import lightning.product.c_932_S;
import lightning.product.g_2336_b;
import net.optifine.Config;
import net.optifine.render.RenderUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.TextureUtils;

public class EmissiveTextures {
    private static String suffixEmissive = null;
    private static String suffixEmissivePng = null;
    private static boolean active = false;
    private static boolean render = false;
    private static boolean hasEmissive = false;
    private static boolean renderEmissive = false;
    private static final String SUFFIX_PNG = ".png";
    private static final g_2336_b LOCATION_TEXTURE_EMPTY = TextureUtils.LOCATION_TEXTURE_EMPTY;
    private static final g_2336_b LOCATION_SPRITE_EMPTY = TextureUtils.LOCATION_SPRITE_EMPTY;
    private static C_3240_x textureManager;
    private static int countRecursive;

    public static boolean isActive() {
        return active;
    }

    public static String getSuffixEmissive() {
        return suffixEmissive;
    }

    public static void beginRender() {
        if (render) {
            ++countRecursive;
        } else {
            render = true;
            hasEmissive = false;
        }
    }

    public static g_2336_b getEmissiveTexture(g_2336_b locationIn) {
        if (!render) {
            return locationIn;
        }
        c_4477_a texture = textureManager.J_1907_R(locationIn);
        if (texture instanceof L_3848_p) {
            return locationIn;
        }
        g_2336_b resourcelocation = null;
        if (texture instanceof P_4645_d) {
            resourcelocation = ((P_4645_d)texture).G_564_y;
        }
        if (!renderEmissive) {
            if (resourcelocation != null) {
                hasEmissive = true;
            }
            return locationIn;
        }
        if (resourcelocation == null) {
            resourcelocation = LOCATION_TEXTURE_EMPTY;
        }
        return resourcelocation;
    }

    public static B_3871_I getEmissiveSprite(B_3871_I sprite) {
        if (!render) {
            return sprite;
        }
        B_3871_I textureatlassprite = sprite.h_1847_R;
        if (!renderEmissive) {
            if (textureatlassprite != null) {
                hasEmissive = true;
            }
            return sprite;
        }
        if (textureatlassprite == null) {
            textureatlassprite = sprite.u_2550_I().J_1907_R(LOCATION_SPRITE_EMPTY);
        }
        return textureatlassprite;
    }

    public static c_932_S getEmissiveQuad(c_932_S quad) {
        if (!render) {
            return quad;
        }
        c_932_S bakedquad = quad.getQuadEmissive();
        if (!renderEmissive) {
            if (bakedquad != null) {
                hasEmissive = true;
            }
            return quad;
        }
        return bakedquad;
    }

    public static boolean hasEmissive() {
        return countRecursive > 0 ? false : hasEmissive;
    }

    public static void beginRenderEmissive() {
        renderEmissive = true;
    }

    public static boolean isRenderEmissive() {
        return renderEmissive;
    }

    public static void endRenderEmissive() {
        RenderUtils.flushRenderBuffers();
        renderEmissive = false;
    }

    public static void endRender() {
        if (countRecursive > 0) {
            --countRecursive;
        } else {
            render = false;
            hasEmissive = false;
        }
    }

    public static void update() {
        textureManager = MinecraftClient.A_4115_X().G_624_v();
        active = false;
        suffixEmissive = null;
        suffixEmissivePng = null;
        if (Config.isEmissiveTextures()) {
            try {
                String s = "optifine/emissive.properties";
                g_2336_b resourcelocation = new g_2336_b(s);
                InputStream inputstream = Config.getResourceStream(resourcelocation);
                if (inputstream == null) {
                    return;
                }
                EmissiveTextures.dbg("Loading " + s);
                PropertiesOrdered properties = new PropertiesOrdered();
                properties.load(inputstream);
                inputstream.close();
                suffixEmissive = properties.getProperty("suffix.emissive");
                if (suffixEmissive != null) {
                    suffixEmissivePng = suffixEmissive + SUFFIX_PNG;
                }
                active = suffixEmissive != null;
            }
            catch (FileNotFoundException filenotfoundexception) {
                return;
            }
            catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    public static void updateIcons(L_3848_p textureMap, Set<g_2336_b> locations) {
        if (active) {
            for (g_2336_b resourcelocation : locations) {
                EmissiveTextures.checkEmissive(textureMap, resourcelocation);
            }
        }
    }

    private static void checkEmissive(L_3848_p textureMap, g_2336_b locSprite) {
        g_2336_b resourcelocation;
        g_2336_b resourcelocation1;
        String s = EmissiveTextures.getSuffixEmissive();
        if (s != null && !locSprite.J_1907_R().endsWith(s) && Config.hasResource(resourcelocation1 = textureMap.n_1700_B(resourcelocation = new g_2336_b(locSprite.R_4764_Y(), locSprite.J_1907_R() + s)))) {
            B_3871_I textureatlassprite = textureMap.P_1922_E(locSprite);
            B_3871_I textureatlassprite1 = textureMap.P_1922_E(resourcelocation);
            textureatlassprite1.Q_4569_t = true;
            textureatlassprite.h_1847_R = textureatlassprite1;
            textureMap.P_1922_E(LOCATION_SPRITE_EMPTY);
        }
    }

    public static void refreshIcons(L_3848_p textureMap) {
        for (B_3871_I textureatlassprite : textureMap.w_1484_f()) {
            EmissiveTextures.refreshIcon(textureatlassprite, textureMap);
        }
    }

    private static void refreshIcon(B_3871_I sprite, L_3848_p textureMap) {
        B_3871_I textureatlassprite1;
        B_3871_I textureatlassprite;
        if (sprite.h_1847_R != null && (textureatlassprite = textureMap.G_564_y(sprite.s_956_w())) != null && (textureatlassprite1 = textureMap.G_564_y(sprite.h_1847_R.s_956_w())) != null) {
            textureatlassprite1.Q_4569_t = true;
            textureatlassprite.h_1847_R = textureatlassprite1;
        }
    }

    private static void dbg(String str) {
        Config.dbg("EmissiveTextures: " + str);
    }

    private static void warn(String str) {
        Config.warn("EmissiveTextures: " + str);
    }

    public static boolean isEmissive(g_2336_b loc) {
        return suffixEmissivePng == null ? false : loc.J_1907_R().endsWith(suffixEmissivePng);
    }

    public static void loadTexture(g_2336_b loc, P_4645_d tex) {
        if (loc != null && tex != null) {
            String s;
            tex.P_1922_E = false;
            tex.G_564_y = null;
            if (suffixEmissivePng != null && (s = loc.J_1907_R()).endsWith(SUFFIX_PNG)) {
                if (s.endsWith(suffixEmissivePng)) {
                    tex.P_1922_E = true;
                } else {
                    String s1 = s.substring(0, s.length() - SUFFIX_PNG.length()) + suffixEmissivePng;
                    g_2336_b resourcelocation = new g_2336_b(loc.R_4764_Y(), s1);
                    if (Config.hasResource(resourcelocation)) {
                        tex.G_564_y = resourcelocation;
                    }
                }
            }
        }
    }

    static {
        countRecursive = 0;
    }
}


