/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import lightning.product.C_3240_x;
import lightning.product.b_4507_u;
import lightning.product.c_4477_a;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import net.optifine.Config;
import net.optifine.CustomSkyLayer;
import net.optifine.render.Blender;
import net.optifine.shaders.RenderStage;
import net.optifine.shaders.Shaders;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import net.optifine.util.WorldUtils;

public class CustomSky {
    private static CustomSkyLayer[][] worldSkyLayers = null;

    public static void reset() {
        worldSkyLayers = null;
    }

    public static void update() {
        CustomSky.reset();
        if (Config.isCustomSky()) {
            worldSkyLayers = CustomSky.readCustomSkies();
        }
    }

    private static CustomSkyLayer[][] readCustomSkies() {
        CustomSkyLayer[][] acustomskylayer = new CustomSkyLayer[10][0];
        String s = "optifine/sky/world";
        int i = -1;
        for (int j = 0; j < acustomskylayer.length; ++j) {
            String s1 = s + j;
            ArrayList<CustomSkyLayer> list = new ArrayList<CustomSkyLayer>();
            for (int k = 0; k < 1000; ++k) {
                String s2 = s1 + "/sky" + k + ".properties";
                int l = 0;
                try {
                    g_2336_b resourcelocation = new g_2336_b(s2);
                    InputStream inputstream = Config.getResourceStream(resourcelocation);
                    if (inputstream == null && ++l > 10) break;
                    PropertiesOrdered properties = new PropertiesOrdered();
                    properties.load(inputstream);
                    inputstream.close();
                    Config.dbg("CustomSky properties: " + s2);
                    String s3 = k + ".png";
                    CustomSkyLayer customskylayer = new CustomSkyLayer(properties, s3);
                    if (!customskylayer.isValid(s2)) continue;
                    String s4 = StrUtils.addSuffixCheck(customskylayer.source, ".png");
                    g_2336_b resourcelocation1 = new g_2336_b(s4);
                    c_4477_a texture = TextureUtils.getTexture(resourcelocation1);
                    if (texture == null) {
                        Config.log("CustomSky: Texture not found: " + String.valueOf(resourcelocation1));
                        continue;
                    }
                    customskylayer.textureId = texture.getGlTextureId();
                    list.add(customskylayer);
                    inputstream.close();
                    continue;
                }
                catch (FileNotFoundException filenotfoundexception) {
                    if (++l <= 10) continue;
                    break;
                }
                catch (IOException ioexception) {
                    ioexception.printStackTrace();
                }
            }
            if (list.size() <= 0) continue;
            CustomSkyLayer[] acustomskylayer2 = list.toArray(new CustomSkyLayer[list.size()]);
            acustomskylayer[j] = acustomskylayer2;
            i = j;
        }
        if (i < 0) {
            return null;
        }
        int i1 = i + 1;
        CustomSkyLayer[][] acustomskylayer1 = new CustomSkyLayer[i1][0];
        for (int j1 = 0; j1 < acustomskylayer1.length; ++j1) {
            acustomskylayer1[j1] = acustomskylayer[j1];
        }
        return acustomskylayer1;
    }

    public static void renderSky(b_4507_u world, C_3240_x re, g_221_o matrixStackIn, float partialTicks) {
        if (worldSkyLayers != null) {
            CustomSkyLayer[] acustomskylayer;
            int i;
            if (Config.isShaders()) {
                Shaders.setRenderStage(RenderStage.CUSTOM_SKY);
            }
            if ((i = WorldUtils.getDimensionId(world)) >= 0 && i < worldSkyLayers.length && (acustomskylayer = worldSkyLayers[i]) != null) {
                long j = world.Z_976_R();
                int k = (int)(j % 24000L);
                float f = world.G_564_y(partialTicks);
                float f1 = world.w_1484_f(partialTicks);
                float f2 = world.u_1723_Y(partialTicks);
                if (f1 > 0.0f) {
                    f2 /= f1;
                }
                for (int l = 0; l < acustomskylayer.length; ++l) {
                    CustomSkyLayer customskylayer = acustomskylayer[l];
                    if (!customskylayer.isActive(world, k)) continue;
                    customskylayer.render(world, matrixStackIn, k, f, f1, f2);
                }
                float f3 = 1.0f - f1;
                Blender.clearBlend(f3);
            }
        }
    }

    public static boolean hasSkyLayers(b_4507_u world) {
        if (worldSkyLayers == null) {
            return false;
        }
        int i = WorldUtils.getDimensionId(world);
        if (i >= 0 && i < worldSkyLayers.length) {
            CustomSkyLayer[] acustomskylayer = worldSkyLayers[i];
            if (acustomskylayer == null) {
                return false;
            }
            return acustomskylayer.length > 0;
        }
        return false;
    }
}

