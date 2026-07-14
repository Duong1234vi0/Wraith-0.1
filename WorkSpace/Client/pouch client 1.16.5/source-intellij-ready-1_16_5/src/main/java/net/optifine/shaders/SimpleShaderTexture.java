/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.apache.commons.io.IOUtils
 */
package net.optifine.shaders;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import lightning.product.N_1972_P;
import lightning.product.ResourceManager;
import lightning.product.c_4477_a;
import lightning.product.i_2518_W;
import lightning.product.TextureMetadataSection;
import net.optifine.shaders.SMCLog;
import net.optifine.shaders.Shaders;
import org.apache.commons.io.IOUtils;

public class SimpleShaderTexture
extends c_4477_a {
    private String texturePath;

    public SimpleShaderTexture(String texturePath) {
        this.texturePath = texturePath;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void loadTexture(ResourceManager resourceManager) throws IOException {
        this.deleteGlTexture();
        InputStream inputstream = Shaders.getShaderPackResourceStream(this.texturePath);
        if (inputstream == null) {
            throw new FileNotFoundException("Shader texture not found: " + this.texturePath);
        }
        try {
            i_2518_W nativeimage = i_2518_W.n_1700_B(inputstream);
            TextureMetadataSection texturemetadatasection = SimpleShaderTexture.loadTextureMetadataSection(this.texturePath, new TextureMetadataSection(false, false));
            N_1972_P.n_1700_B(this.getGlTextureId(), nativeimage.n_1700_B(), nativeimage.J_1907_R());
            nativeimage.n_1700_B(0, 0, 0, 0, 0, nativeimage.n_1700_B(), nativeimage.J_1907_R(), texturemetadatasection.n_1700_B(), texturemetadatasection.J_1907_R(), false, true);
        }
        finally {
            IOUtils.closeQuietly((InputStream)inputstream);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static TextureMetadataSection loadTextureMetadataSection(String texturePath, TextureMetadataSection def) {
        String s = texturePath + ".mcmeta";
        String s1 = "texture";
        InputStream inputstream = Shaders.getShaderPackResourceStream(s);
        if (inputstream != null) {
            TextureMetadataSection texturemetadatasection1;
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
            try {
                JsonObject jsonobject = new JsonParser().parse((Reader)bufferedreader).getAsJsonObject();
                JsonObject jsonobject1 = jsonobject.getAsJsonObject(s1);
                if (jsonobject1 == null) {
                    TextureMetadataSection l_1226_M2 = def;
                    return l_1226_M2;
                }
                TextureMetadataSection texturemetadatasection = TextureMetadataSection.n_1700_B.n_1700_B(jsonobject1);
                if (texturemetadatasection == null) {
                    TextureMetadataSection l_1226_M3 = def;
                    return l_1226_M3;
                }
                texturemetadatasection1 = texturemetadatasection;
            }
            catch (RuntimeException runtimeexception) {
                SMCLog.warning("Error reading metadata: " + s);
                SMCLog.warning(runtimeexception.getClass().getName() + ": " + runtimeexception.getMessage());
                TextureMetadataSection l_1226_M4 = def;
                return l_1226_M4;
            }
            finally {
                IOUtils.closeQuietly((Reader)bufferedreader);
                IOUtils.closeQuietly((InputStream)inputstream);
            }
            return texturemetadatasection1;
        }
        return def;
    }
}


