/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import lightning.product.B_3871_I;
import lightning.product.L_3848_p;
import lightning.product.c_1514_x;
import lightning.product.c_932_S;
import lightning.product.g_2336_b;
import net.optifine.Config;
import net.optifine.ConnectedTextures;
import net.optifine.NaturalProperties;
import net.optifine.util.TextureUtils;

public class NaturalTextures {
    private static NaturalProperties[] propertiesByIndex = new NaturalProperties[0];

    public static void update() {
        propertiesByIndex = new NaturalProperties[0];
        if (Config.isNaturalTextures()) {
            String s = "optifine/natural.properties";
            try {
                g_2336_b resourcelocation = new g_2336_b(s);
                if (!Config.hasResource(resourcelocation)) {
                    Config.dbg("NaturalTextures: configuration \"" + s + "\" not found");
                    return;
                }
                boolean flag = Config.isFromDefaultResourcePack(resourcelocation);
                InputStream inputstream = Config.getResourceStream(resourcelocation);
                ArrayList<NaturalProperties> arraylist = new ArrayList<NaturalProperties>(256);
                String s1 = Config.readInputStream(inputstream);
                inputstream.close();
                String[] astring = Config.tokenize(s1, "\n\r");
                if (flag) {
                    Config.dbg("Natural Textures: Parsing default configuration \"" + s + "\"");
                    Config.dbg("Natural Textures: Valid only for textures from default resource pack");
                } else {
                    Config.dbg("Natural Textures: Parsing configuration \"" + s + "\"");
                }
                int i = 0;
                L_3848_p atlastexture = TextureUtils.getTextureMapBlocks();
                for (int j = 0; j < astring.length; ++j) {
                    String s2 = astring[j].trim();
                    if (s2.startsWith("#")) continue;
                    String[] astring1 = Config.tokenize(s2, "=");
                    if (astring1.length != 2) {
                        Config.warn("Natural Textures: Invalid \"" + s + "\" line: " + s2);
                        continue;
                    }
                    String s3 = astring1[0].trim();
                    String s4 = astring1[1].trim();
                    B_3871_I textureatlassprite = atlastexture.J_1907_R("minecraft:block/" + s3);
                    if (textureatlassprite == null) {
                        Config.warn("Natural Textures: Texture not found: \"" + s + "\" line: " + s2);
                        continue;
                    }
                    int k = textureatlassprite.t_1786_h();
                    if (k < 0) {
                        Config.warn("Natural Textures: Invalid \"" + s + "\" line: " + s2);
                        continue;
                    }
                    if (flag && !Config.isFromDefaultResourcePack(new g_2336_b("textures/block/" + s3 + ".png"))) {
                        return;
                    }
                    NaturalProperties naturalproperties = new NaturalProperties(s4);
                    if (!naturalproperties.isValid()) continue;
                    while (arraylist.size() <= k) {
                        arraylist.add(null);
                    }
                    arraylist.set(k, naturalproperties);
                    ++i;
                }
                propertiesByIndex = arraylist.toArray(new NaturalProperties[arraylist.size()]);
                if (i > 0) {
                    Config.dbg("NaturalTextures: " + i);
                }
            }
            catch (FileNotFoundException filenotfoundexception) {
                Config.warn("NaturalTextures: configuration \"" + s + "\" not found");
                return;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static c_932_S getNaturalTexture(c_1514_x blockPosIn, c_932_S quad) {
        B_3871_I textureatlassprite = quad.getSprite();
        if (textureatlassprite == null) {
            return quad;
        }
        NaturalProperties naturalproperties = NaturalTextures.getNaturalProperties(textureatlassprite);
        if (naturalproperties == null) {
            return quad;
        }
        int i = ConnectedTextures.getSide(quad.getFace());
        int j = Config.getRandom(blockPosIn, i);
        int k = 0;
        boolean flag = false;
        if (naturalproperties.rotation > 1) {
            k = j & 3;
        }
        if (naturalproperties.rotation == 2) {
            k = k / 2 * 2;
        }
        if (naturalproperties.flip) {
            flag = (j & 4) != 0;
        }
        return naturalproperties.getQuad(quad, k, flag);
    }

    public static NaturalProperties getNaturalProperties(B_3871_I icon) {
        if (!(icon instanceof B_3871_I)) {
            return null;
        }
        int i = icon.t_1786_h();
        return i >= 0 && i < propertiesByIndex.length ? propertiesByIndex[i] : null;
    }
}

