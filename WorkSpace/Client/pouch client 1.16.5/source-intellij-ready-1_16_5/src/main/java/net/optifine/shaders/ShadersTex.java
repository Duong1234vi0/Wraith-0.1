/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package net.optifine.shaders;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import lightning.product.B_3871_I;
import lightning.product.C_3240_x;
import lightning.product.L_3848_p;
import lightning.product.N_1972_P;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.T_1114_L;
import lightning.product.X_933_l;
import lightning.product.MinecraftClient;
import lightning.product.c_4477_a;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import net.optifine.Config;
import net.optifine.render.RenderUtils;
import net.optifine.shaders.MultiTexID;
import net.optifine.shaders.SMCLog;
import net.optifine.shaders.Shaders;
import net.optifine.util.TextureUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ShadersTex {
    public static final int initialBufferSize = 0x100000;
    public static ByteBuffer byteBuffer = BufferUtils.createByteBuffer((int)0x400000);
    public static IntBuffer intBuffer = byteBuffer.asIntBuffer();
    public static int[] intArray = new int[0x100000];
    public static final int defBaseTexColor = 0;
    public static final int defNormTexColor = -8421377;
    public static final int defSpecTexColor = 0;
    public static Map<Integer, MultiTexID> multiTexMap = new HashMap<Integer, MultiTexID>();

    public static IntBuffer getIntBuffer(int size) {
        if (intBuffer.capacity() < size) {
            int i = ShadersTex.roundUpPOT(size);
            byteBuffer = BufferUtils.createByteBuffer((int)(i * 4));
            intBuffer = byteBuffer.asIntBuffer();
        }
        return intBuffer;
    }

    public static int[] getIntArray(int size) {
        if (intArray == null) {
            intArray = new int[0x100000];
        }
        if (intArray.length < size) {
            intArray = new int[ShadersTex.roundUpPOT(size)];
        }
        return intArray;
    }

    public static int roundUpPOT(int x) {
        int i = x - 1;
        i |= i >> 1;
        i |= i >> 2;
        i |= i >> 4;
        i |= i >> 8;
        i |= i >> 16;
        return i + 1;
    }

    public static int log2(int x) {
        int i = 0;
        if ((x & 0xFFFF0000) != 0) {
            i += 16;
            x >>= 16;
        }
        if ((x & 0xFF00) != 0) {
            i += 8;
            x >>= 8;
        }
        if ((x & 0xF0) != 0) {
            i += 4;
            x >>= 4;
        }
        if ((x & 6) != 0) {
            i += 2;
            x >>= 2;
        }
        if ((x & 2) != 0) {
            ++i;
        }
        return i;
    }

    public static IntBuffer fillIntBuffer(int size, int value) {
        int[] aint = ShadersTex.getIntArray(size);
        IntBuffer intbuffer = ShadersTex.getIntBuffer(size);
        Arrays.fill(intArray, 0, size, value);
        intBuffer.put(intArray, 0, size);
        return intBuffer;
    }

    public static int[] createAIntImage(int size) {
        int[] aint = new int[size * 3];
        Arrays.fill(aint, 0, size, 0);
        Arrays.fill(aint, size, size * 2, -8421377);
        Arrays.fill(aint, size * 2, size * 3, 0);
        return aint;
    }

    public static int[] createAIntImage(int size, int color) {
        int[] aint = new int[size * 3];
        Arrays.fill(aint, 0, size, color);
        Arrays.fill(aint, size, size * 2, -8421377);
        Arrays.fill(aint, size * 2, size * 3, 0);
        return aint;
    }

    public static MultiTexID getMultiTexID(c_4477_a tex) {
        MultiTexID multitexid = tex.multiTex;
        if (multitexid == null) {
            int i = tex.getGlTextureId();
            multitexid = multiTexMap.get(i);
            if (multitexid == null) {
                multitexid = new MultiTexID(i, GL11.glGenTextures(), GL11.glGenTextures());
                multiTexMap.put(i, multitexid);
            }
            tex.multiTex = multitexid;
        }
        return multitexid;
    }

    public static void deleteTextures(c_4477_a atex, int texid) {
        MultiTexID multitexid = atex.multiTex;
        if (multitexid != null) {
            atex.multiTex = null;
            multiTexMap.remove(multitexid.base);
            X_933_l.multiplayerClientSuggestionProvider(multitexid.norm);
            X_933_l.multiplayerClientSuggestionProvider(multitexid.spec);
            if (multitexid.base != texid) {
                SMCLog.warning("Error : MultiTexID.base mismatch: " + multitexid.base + ", texid: " + texid);
                X_933_l.multiplayerClientSuggestionProvider(multitexid.base);
            }
        }
    }

    public static void bindNSTextures(int normTex, int specTex, boolean normalBlend, boolean specularBlend, boolean mipmaps) {
        if (Shaders.isRenderingWorld && X_933_l.Z_976_R() == 33984) {
            if (Shaders.configNormalMap) {
                X_933_l.t_1786_h(33985);
                X_933_l.w_1457_N(normTex);
                if (!normalBlend) {
                    int i = mipmaps ? 9984 : 9728;
                    X_933_l.J_1907_R(3553, 10241, i);
                    X_933_l.J_1907_R(3553, 10240, 9728);
                }
            }
            if (Shaders.configSpecularMap) {
                X_933_l.t_1786_h(33987);
                X_933_l.w_1457_N(specTex);
                if (!specularBlend) {
                    int j = mipmaps ? 9984 : 9728;
                    X_933_l.J_1907_R(3553, 10241, j);
                    X_933_l.J_1907_R(3553, 10240, 9728);
                }
            }
            X_933_l.t_1786_h(33984);
        }
    }

    public static void bindNSTextures(MultiTexID multiTex) {
        ShadersTex.bindNSTextures(multiTex.norm, multiTex.spec, true, true, false);
    }

    public static void bindTextures(int baseTex, int normTex, int specTex) {
        if (Shaders.isRenderingWorld && X_933_l.Z_976_R() == 33984) {
            X_933_l.t_1786_h(33985);
            X_933_l.w_1457_N(normTex);
            X_933_l.t_1786_h(33987);
            X_933_l.w_1457_N(specTex);
            X_933_l.t_1786_h(33984);
        }
        X_933_l.w_1457_N(baseTex);
    }

    public static void bindTextures(MultiTexID multiTex, boolean normalBlend, boolean specularBlend, boolean mipmaps) {
        if (Shaders.isRenderingWorld && X_933_l.Z_976_R() == 33984) {
            if (Shaders.configNormalMap) {
                X_933_l.t_1786_h(33985);
                X_933_l.w_1457_N(multiTex.norm);
                if (!normalBlend) {
                    int i = mipmaps ? 9984 : 9728;
                    X_933_l.J_1907_R(3553, 10241, i);
                    X_933_l.J_1907_R(3553, 10240, 9728);
                }
            }
            if (Shaders.configSpecularMap) {
                X_933_l.t_1786_h(33987);
                X_933_l.w_1457_N(multiTex.spec);
                if (!specularBlend) {
                    int j = mipmaps ? 9984 : 9728;
                    X_933_l.J_1907_R(3553, 10241, j);
                    X_933_l.J_1907_R(3553, 10240, 9728);
                }
            }
            X_933_l.t_1786_h(33984);
        }
        X_933_l.w_1457_N(multiTex.base);
    }

    public static void bindTexture(c_4477_a tex) {
        int i = tex.getGlTextureId();
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = false;
        if (tex instanceof L_3848_p) {
            L_3848_p atlastexture = (L_3848_p)tex;
            flag = atlastexture.h_1847_R();
            flag1 = atlastexture.Q_4569_t();
            flag2 = atlastexture.M_588_G();
        }
        ShadersTex.bindTextures(tex.getMultiTexID(), flag, flag1, flag2);
        if (X_933_l.Z_976_R() == 33984) {
            int k = Shaders.atlasSizeX;
            int j = Shaders.atlasSizeY;
            if (tex instanceof L_3848_p) {
                Shaders.atlasSizeX = ((L_3848_p)tex).R_4764_Y;
                Shaders.atlasSizeY = ((L_3848_p)tex).G_564_y;
            } else {
                Shaders.atlasSizeX = 0;
                Shaders.atlasSizeY = 0;
            }
            if (Shaders.atlasSizeX != k || Shaders.atlasSizeY != j) {
                boolean flag3 = RenderUtils.setFlushRenderBuffers(false);
                Shaders.uniform_atlasSize.setValue(Shaders.atlasSizeX, Shaders.atlasSizeY);
                RenderUtils.setFlushRenderBuffers(flag3);
            }
        }
    }

    public static void bindTextures(int baseTex) {
        MultiTexID multitexid = multiTexMap.get(baseTex);
        ShadersTex.bindTextures(multitexid, true, true, false);
    }

    public static void initDynamicTextureNS(T_1114_L tex) {
        MultiTexID multitexid = tex.getMultiTexID();
        i_2518_W nativeimage = tex.J_1907_R();
        int i = nativeimage.n_1700_B();
        int j = nativeimage.J_1907_R();
        i_2518_W nativeimage1 = ShadersTex.makeImageColor(i, j, -8421377);
        N_1972_P.n_1700_B(multitexid.norm, i, j);
        nativeimage1.n_1700_B(0, 0, 0, 0, 0, i, j, false, false, false, true);
        i_2518_W nativeimage2 = ShadersTex.makeImageColor(i, j, 0);
        N_1972_P.n_1700_B(multitexid.spec, i, j);
        nativeimage2.n_1700_B(0, 0, 0, 0, 0, i, j, false, false, false, true);
        X_933_l.w_1457_N(multitexid.base);
    }

    public static void updateDynTexSubImage1(int[] src, int width, int height, int posX, int posY, int page) {
        int i = width * height;
        IntBuffer intbuffer = ShadersTex.getIntBuffer(i);
        ((Buffer)intbuffer).clear();
        int j = page * i;
        if (src.length >= j + i) {
            ((Buffer)intbuffer.put(src, j, i)).position(0).limit(i);
            TextureUtils.resetDataUnpacking();
            GL11.glTexSubImage2D((int)3553, (int)0, (int)posX, (int)posY, (int)width, (int)height, (int)32993, (int)33639, (IntBuffer)intbuffer);
            ((Buffer)intbuffer).clear();
        }
    }

    public static c_4477_a createDefaultTexture() {
        T_1114_L dynamictexture = new T_1114_L(1, 1, true);
        dynamictexture.J_1907_R().n_1700_B(0, 0, -1);
        dynamictexture.n_1700_B();
        return dynamictexture;
    }

    public static void allocateTextureMapNS(int mipmapLevels, int width, int height, L_3848_p tex) {
        MultiTexID multitexid = ShadersTex.getMultiTexID(tex);
        if (Shaders.configNormalMap) {
            SMCLog.info("Allocate texture map normal: " + width + "x" + height + ", mipmaps: " + mipmapLevels);
            N_1972_P.n_1700_B(multitexid.norm, mipmapLevels, width, height);
        }
        if (Shaders.configSpecularMap) {
            SMCLog.info("Allocate texture map specular: " + width + "x" + height + ", mipmaps: " + mipmapLevels);
            N_1972_P.n_1700_B(multitexid.spec, mipmapLevels, width, height);
        }
        X_933_l.w_1457_N(multitexid.base);
    }

    private static i_2518_W[] generateMipmaps(i_2518_W image, int levels) {
        if (levels < 0) {
            levels = 0;
        }
        i_2518_W[] anativeimage = new i_2518_W[levels + 1];
        anativeimage[0] = image;
        if (levels > 0) {
            for (int i = 1; i <= levels; ++i) {
                i_2518_W nativeimage = anativeimage[i - 1];
                i_2518_W nativeimage1 = new i_2518_W(nativeimage.n_1700_B() >> 1, nativeimage.J_1907_R() >> 1, false);
                int j = nativeimage1.n_1700_B();
                int k = nativeimage1.J_1907_R();
                for (int l = 0; l < j; ++l) {
                    for (int i1 = 0; i1 < k; ++i1) {
                        nativeimage1.n_1700_B(l, i1, ShadersTex.blend4Simple(nativeimage.n_1700_B(l * 2 + 0, i1 * 2 + 0), nativeimage.n_1700_B(l * 2 + 1, i1 * 2 + 0), nativeimage.n_1700_B(l * 2 + 0, i1 * 2 + 1), nativeimage.n_1700_B(l * 2 + 1, i1 * 2 + 1)));
                    }
                }
                anativeimage[i] = nativeimage1;
            }
        }
        return anativeimage;
    }

    public static BufferedImage readImage(g_2336_b resLoc) {
        try {
            if (!Config.hasResource(resLoc)) {
                return null;
            }
            InputStream inputstream = Config.getResourceStream(resLoc);
            if (inputstream == null) {
                return null;
            }
            BufferedImage bufferedimage = ImageIO.read(inputstream);
            inputstream.close();
            return bufferedimage;
        }
        catch (IOException ioexception) {
            return null;
        }
    }

    public static int[][] genMipmapsSimple(int maxLevel, int width, int[][] data) {
        for (int i = 1; i <= maxLevel; ++i) {
            if (data[i] != null) continue;
            int j = width >> i;
            int k = j * 2;
            int[] aint = data[i - 1];
            data[i] = new int[j * j];
            int[] aint1 = data[i];
            for (int i1 = 0; i1 < j; ++i1) {
                for (int l = 0; l < j; ++l) {
                    int j1 = i1 * 2 * k + l * 2;
                    aint1[i1 * j + l] = ShadersTex.blend4Simple(aint[j1], aint[j1 + 1], aint[j1 + k], aint[j1 + k + 1]);
                }
            }
        }
        return data;
    }

    public static void uploadTexSub1(int[][] src, int width, int height, int posX, int posY, int page) {
        TextureUtils.resetDataUnpacking();
        int i = width * height;
        IntBuffer intbuffer = ShadersTex.getIntBuffer(i);
        int j = src.length;
        int k = 0;
        int l = width;
        int i1 = height;
        int j1 = posX;
        int k1 = posY;
        while (l > 0 && i1 > 0 && k < j) {
            int l1 = l * i1;
            int[] aint = src[k];
            ((Buffer)intbuffer).clear();
            if (aint.length >= l1 * (page + 1)) {
                ((Buffer)intbuffer.put(aint, l1 * page, l1)).position(0).limit(l1);
                GL11.glTexSubImage2D((int)3553, (int)k, (int)j1, (int)k1, (int)l, (int)i1, (int)32993, (int)33639, (IntBuffer)intbuffer);
            }
            l >>= 1;
            i1 >>= 1;
            j1 >>= 1;
            k1 >>= 1;
            ++k;
        }
        ((Buffer)intbuffer).clear();
    }

    public static int blend4Alpha(int c0, int c1, int c2, int c3) {
        int k1;
        int i = c0 >>> 24 & 0xFF;
        int j = c1 >>> 24 & 0xFF;
        int k = c2 >>> 24 & 0xFF;
        int l = c3 >>> 24 & 0xFF;
        int i1 = i + j + k + l;
        int j1 = (i1 + 2) / 4;
        if (i1 != 0) {
            k1 = i1;
        } else {
            k1 = 4;
            i = 1;
            j = 1;
            k = 1;
            l = 1;
        }
        int l1 = (k1 + 1) / 2;
        return j1 << 24 | ((c0 >>> 16 & 0xFF) * i + (c1 >>> 16 & 0xFF) * j + (c2 >>> 16 & 0xFF) * k + (c3 >>> 16 & 0xFF) * l + l1) / k1 << 16 | ((c0 >>> 8 & 0xFF) * i + (c1 >>> 8 & 0xFF) * j + (c2 >>> 8 & 0xFF) * k + (c3 >>> 8 & 0xFF) * l + l1) / k1 << 8 | ((c0 >>> 0 & 0xFF) * i + (c1 >>> 0 & 0xFF) * j + (c2 >>> 0 & 0xFF) * k + (c3 >>> 0 & 0xFF) * l + l1) / k1 << 0;
    }

    public static int blend4Simple(int c0, int c1, int c2, int c3) {
        return ((c0 >>> 24 & 0xFF) + (c1 >>> 24 & 0xFF) + (c2 >>> 24 & 0xFF) + (c3 >>> 24 & 0xFF) + 2) / 4 << 24 | ((c0 >>> 16 & 0xFF) + (c1 >>> 16 & 0xFF) + (c2 >>> 16 & 0xFF) + (c3 >>> 16 & 0xFF) + 2) / 4 << 16 | ((c0 >>> 8 & 0xFF) + (c1 >>> 8 & 0xFF) + (c2 >>> 8 & 0xFF) + (c3 >>> 8 & 0xFF) + 2) / 4 << 8 | ((c0 >>> 0 & 0xFF) + (c1 >>> 0 & 0xFF) + (c2 >>> 0 & 0xFF) + (c3 >>> 0 & 0xFF) + 2) / 4 << 0;
    }

    public static void genMipmapAlpha(int[] aint, int offset, int width, int height) {
        Math.min(width, height);
        int o2 = offset;
        int w2 = width;
        int h2 = height;
        int o1 = 0;
        int w1 = 0;
        int h1 = 0;
        int i = 0;
        while (w2 > 1 && h2 > 1) {
            o1 = o2 + w2 * h2;
            w1 = w2 / 2;
            h1 = h2 / 2;
            for (int l1 = 0; l1 < h1; ++l1) {
                int i2 = o1 + l1 * w1;
                int j2 = o2 + l1 * 2 * w2;
                for (int k2 = 0; k2 < w1; ++k2) {
                    aint[i2 + k2] = ShadersTex.blend4Alpha(aint[j2 + k2 * 2], aint[j2 + k2 * 2 + 1], aint[j2 + w2 + k2 * 2], aint[j2 + w2 + k2 * 2 + 1]);
                }
            }
            ++i;
            w2 = w1;
            h2 = h1;
            o2 = o1;
        }
        while (i > 0) {
            w2 = width >> --i;
            h2 = height >> i;
            int l2 = o2 = o1 - w2 * h2;
            for (int i3 = 0; i3 < h2; ++i3) {
                for (int j3 = 0; j3 < w2; ++j3) {
                    if (aint[l2] == 0) {
                        aint[l2] = aint[o1 + i3 / 2 * w1 + j3 / 2] & 0xFFFFFF;
                    }
                    ++l2;
                }
            }
            o1 = o2;
            w1 = w2;
        }
    }

    public static void genMipmapSimple(int[] aint, int offset, int width, int height) {
        Math.min(width, height);
        int o2 = offset;
        int w2 = width;
        int h2 = height;
        int o1 = 0;
        int w1 = 0;
        int h1 = 0;
        int i = 0;
        while (w2 > 1 && h2 > 1) {
            o1 = o2 + w2 * h2;
            w1 = w2 / 2;
            h1 = h2 / 2;
            for (int l1 = 0; l1 < h1; ++l1) {
                int i2 = o1 + l1 * w1;
                int j2 = o2 + l1 * 2 * w2;
                for (int k2 = 0; k2 < w1; ++k2) {
                    aint[i2 + k2] = ShadersTex.blend4Simple(aint[j2 + k2 * 2], aint[j2 + k2 * 2 + 1], aint[j2 + w2 + k2 * 2], aint[j2 + w2 + k2 * 2 + 1]);
                }
            }
            ++i;
            w2 = w1;
            h2 = h1;
            o2 = o1;
        }
        while (i > 0) {
            w2 = width >> --i;
            h2 = height >> i;
            int l2 = o2 = o1 - w2 * h2;
            for (int i3 = 0; i3 < h2; ++i3) {
                for (int j3 = 0; j3 < w2; ++j3) {
                    if (aint[l2] == 0) {
                        aint[l2] = aint[o1 + i3 / 2 * w1 + j3 / 2] & 0xFFFFFF;
                    }
                    ++l2;
                }
            }
            o1 = o2;
            w1 = w2;
        }
    }

    public static boolean isSemiTransparent(int[] aint, int width, int height) {
        int i = width * height;
        if (aint[0] >>> 24 == 255 && aint[i - 1] == 0) {
            return true;
        }
        for (int j = 0; j < i; ++j) {
            int k = aint[j] >>> 24;
            if (k == 0 || k == 255) continue;
            return true;
        }
        return false;
    }

    public static void updateSubTex1(int[] src, int width, int height, int posX, int posY) {
        int i = 0;
        int j = width;
        int k = height;
        int l = posX;
        int i1 = posY;
        while (j > 0 && k > 0) {
            GL11.glCopyTexSubImage2D((int)3553, (int)i, (int)l, (int)i1, (int)0, (int)0, (int)j, (int)k);
            ++i;
            j /= 2;
            k /= 2;
            l /= 2;
            i1 /= 2;
        }
    }

    public static void updateSubImage(MultiTexID multiTex, int[] src, int width, int height, int posX, int posY, boolean linear, boolean clamp) {
        int i = width * height;
        IntBuffer intbuffer = ShadersTex.getIntBuffer(i);
        TextureUtils.resetDataUnpacking();
        ((Buffer)intbuffer).clear();
        intbuffer.put(src, 0, i);
        ((Buffer)intbuffer).position(0).limit(i);
        X_933_l.w_1457_N(multiTex.base);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10242, (int)10497);
        GL11.glTexParameteri((int)3553, (int)10243, (int)10497);
        GL11.glTexSubImage2D((int)3553, (int)0, (int)posX, (int)posY, (int)width, (int)height, (int)32993, (int)33639, (IntBuffer)intbuffer);
        if (src.length == i * 3) {
            ((Buffer)intbuffer).clear();
            ((Buffer)intbuffer.put(src, i, i)).position(0);
            ((Buffer)intbuffer).position(0).limit(i);
        }
        X_933_l.w_1457_N(multiTex.norm);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10242, (int)10497);
        GL11.glTexParameteri((int)3553, (int)10243, (int)10497);
        GL11.glTexSubImage2D((int)3553, (int)0, (int)posX, (int)posY, (int)width, (int)height, (int)32993, (int)33639, (IntBuffer)intbuffer);
        if (src.length == i * 3) {
            ((Buffer)intbuffer).clear();
            intbuffer.put(src, i * 2, i);
            ((Buffer)intbuffer).position(0).limit(i);
        }
        X_933_l.w_1457_N(multiTex.spec);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10242, (int)10497);
        GL11.glTexParameteri((int)3553, (int)10243, (int)10497);
        GL11.glTexSubImage2D((int)3553, (int)0, (int)posX, (int)posY, (int)width, (int)height, (int)32993, (int)33639, (IntBuffer)intbuffer);
        X_933_l.t_1786_h(33984);
    }

    public static g_2336_b getNSMapLocation(g_2336_b location, String mapName) {
        if (location == null) {
            return null;
        }
        String s = location.J_1907_R();
        String[] astring = s.split(".png");
        String s1 = astring[0];
        return new g_2336_b(location.R_4764_Y(), s1 + "_" + mapName + ".png");
    }

    private static i_2518_W loadNSMapImage(ResourceManager manager, g_2336_b location, int width, int height, int defaultColor) {
        i_2518_W nativeimage = ShadersTex.loadNSMapFile(manager, location, width, height);
        if (nativeimage == null) {
            nativeimage = new i_2518_W(width, height, false);
            int i = TextureUtils.toAbgr(defaultColor);
            nativeimage.n_1700_B(0, 0, width, height, i);
        }
        return nativeimage;
    }

    private static i_2518_W makeImageColor(int width, int height, int defaultColor) {
        i_2518_W nativeimage = new i_2518_W(width, height, false);
        int i = TextureUtils.toAbgr(defaultColor);
        nativeimage.P_1922_E(i);
        return nativeimage;
    }

    private static i_2518_W loadNSMapFile(ResourceManager manager, g_2336_b location, int width, int height) {
        if (location == null) {
            return null;
        }
        try {
            Resource iresource = manager.n_1700_B(location);
            i_2518_W nativeimage = i_2518_W.n_1700_B(iresource.J_1907_R());
            if (nativeimage == null) {
                return null;
            }
            if (nativeimage.n_1700_B() == width && nativeimage.J_1907_R() == height) {
                return nativeimage;
            }
            nativeimage.close();
            return null;
        }
        catch (IOException ioexception) {
            return null;
        }
    }

    public static void loadSimpleTextureNS(int textureID, i_2518_W nativeImage, boolean blur, boolean clamp, ResourceManager resourceManager, g_2336_b location, MultiTexID multiTex) {
        int i = nativeImage.n_1700_B();
        int j = nativeImage.J_1907_R();
        g_2336_b resourcelocation = ShadersTex.getNSMapLocation(location, "n");
        i_2518_W nativeimage = ShadersTex.loadNSMapImage(resourceManager, resourcelocation, i, j, -8421377);
        N_1972_P.n_1700_B(multiTex.norm, 0, i, j);
        nativeimage.n_1700_B(0, 0, 0, 0, 0, i, j, blur, clamp, false, true);
        g_2336_b resourcelocation1 = ShadersTex.getNSMapLocation(location, "s");
        i_2518_W nativeimage1 = ShadersTex.loadNSMapImage(resourceManager, resourcelocation1, i, j, 0);
        N_1972_P.n_1700_B(multiTex.spec, 0, i, j);
        nativeimage1.n_1700_B(0, 0, 0, 0, 0, i, j, blur, clamp, false, true);
        X_933_l.w_1457_N(multiTex.base);
    }

    public static void mergeImage(int[] aint, int dstoff, int srcoff, int size) {
    }

    public static int blendColor(int color1, int color2, int factor1) {
        int i = 255 - factor1;
        return ((color1 >>> 24 & 0xFF) * factor1 + (color2 >>> 24 & 0xFF) * i) / 255 << 24 | ((color1 >>> 16 & 0xFF) * factor1 + (color2 >>> 16 & 0xFF) * i) / 255 << 16 | ((color1 >>> 8 & 0xFF) * factor1 + (color2 >>> 8 & 0xFF) * i) / 255 << 8 | ((color1 >>> 0 & 0xFF) * factor1 + (color2 >>> 0 & 0xFF) * i) / 255 << 0;
    }

    public static void updateTextureMinMagFilter() {
        C_3240_x texturemanager = MinecraftClient.A_4115_X().G_624_v();
        c_4477_a texture = texturemanager.J_1907_R(L_3848_p.n_1700_B);
        if (texture != null) {
            MultiTexID multitexid = texture.getMultiTexID();
            X_933_l.w_1457_N(multitexid.base);
            GL11.glTexParameteri((int)3553, (int)10241, (int)Shaders.texMinFilValue[Shaders.configTexMinFilB]);
            GL11.glTexParameteri((int)3553, (int)10240, (int)Shaders.texMagFilValue[Shaders.configTexMagFilB]);
            X_933_l.w_1457_N(multitexid.norm);
            GL11.glTexParameteri((int)3553, (int)10241, (int)Shaders.texMinFilValue[Shaders.configTexMinFilN]);
            GL11.glTexParameteri((int)3553, (int)10240, (int)Shaders.texMagFilValue[Shaders.configTexMagFilN]);
            X_933_l.w_1457_N(multitexid.spec);
            GL11.glTexParameteri((int)3553, (int)10241, (int)Shaders.texMinFilValue[Shaders.configTexMinFilS]);
            GL11.glTexParameteri((int)3553, (int)10240, (int)Shaders.texMagFilValue[Shaders.configTexMagFilS]);
            X_933_l.w_1457_N(0);
        }
    }

    public static int[][] getFrameTexData(int[][] src, int width, int height, int frameIndex) {
        int i = src.length;
        int[][] aint = new int[i][];
        for (int j = 0; j < i; ++j) {
            int[] aint1 = src[j];
            if (aint1 == null) continue;
            int k = (width >> j) * (height >> j);
            int[] aint2 = new int[k * 3];
            aint[j] = aint2;
            int l = aint1.length / 3;
            int i1 = k * frameIndex;
            int j1 = 0;
            System.arraycopy(aint1, i1, aint2, j1, k);
            System.arraycopy(aint1, i1 += l, aint2, j1 += k, k);
            System.arraycopy(aint1, i1 += l, aint2, j1 += k, k);
        }
        return aint;
    }

    public static int[][] prepareAF(B_3871_I tas, int[][] src, int width, int height) {
        boolean flag = true;
        return src;
    }

    public static void fixTransparentColor(B_3871_I tas, int[] aint) {
    }
}



