/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.IOUtils
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL
 *  org.lwjgl.opengl.GL11
 */
package net.optifine.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import lightning.product.B_3871_I;
import lightning.product.F_3565_Q;
import lightning.product.ResourceManagerReloadListener;
import lightning.product.L_3848_p;
import lightning.product.N_4235_V;
import lightning.product.P_4645_d;
import lightning.product.ReloadableResourceManager;
import lightning.product.ResourceManager;
import lightning.product.ProfilerFiller;
import lightning.product.X_933_l;
import lightning.product.c_4477_a;
import lightning.product.g_2336_b;
import lightning.product.g_2561_p;
import lightning.product.i_2518_W;
import lightning.product.SimplePreparableReloadListener;
import lightning.product.u_530_F;
import net.optifine.BetterGrass;
import net.optifine.BetterSnow;
import net.optifine.Config;
import net.optifine.ConnectedTextures;
import net.optifine.CustomBlockLayers;
import net.optifine.CustomColors;
import net.optifine.CustomGuis;
import net.optifine.CustomItems;
import net.optifine.CustomLoadingScreens;
import net.optifine.CustomPanorama;
import net.optifine.CustomSky;
import net.optifine.EmissiveTextures;
import net.optifine.Lang;
import net.optifine.NaturalTextures;
import net.optifine.RandomEntities;
import net.optifine.SmartLeaves;
import net.optifine.TextureAnimations;
import net.optifine.entity.model.CustomEntityModels;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.MultiTexID;
import net.optifine.shaders.Shaders;
import net.optifine.util.StrUtils;
import net.optifine.util.TickableTexture;
import org.apache.commons.io.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class TextureUtils {
    private static final String texGrassTop = "grass_block_top";
    private static final String texGrassSide = "grass_block_side";
    private static final String texGrassSideOverlay = "grass_block_side_overlay";
    private static final String texSnow = "snow";
    private static final String texGrassSideSnowed = "grass_block_snow";
    private static final String texMyceliumSide = "mycelium_side";
    private static final String texMyceliumTop = "mycelium_top";
    private static final String texWaterStill = "water_still";
    private static final String texWaterFlow = "water_flow";
    private static final String texLavaStill = "lava_still";
    private static final String texLavaFlow = "lava_flow";
    private static final String texFireLayer0 = "fire_0";
    private static final String texFireLayer1 = "fire_1";
    private static final String texSoulFireLayer0 = "soul_fire_0";
    private static final String texSoulFireLayer1 = "soul_fire_1";
    private static final String texCampFire = "campfire_fire";
    private static final String texCampFireLogLit = "campfire_log_lit";
    private static final String texSoulCampFire = "soul_campfire_fire";
    private static final String texSoulCampFireLogLit = "soul_campfire_log_lit";
    private static final String texPortal = "nether_portal";
    private static final String texGlass = "glass";
    private static final String texGlassPaneTop = "glass_pane_top";
    public static B_3871_I iconGrassTop;
    public static B_3871_I iconGrassSide;
    public static B_3871_I iconGrassSideOverlay;
    public static B_3871_I iconSnow;
    public static B_3871_I iconGrassSideSnowed;
    public static B_3871_I iconMyceliumSide;
    public static B_3871_I iconMyceliumTop;
    public static B_3871_I iconWaterStill;
    public static B_3871_I iconWaterFlow;
    public static B_3871_I iconLavaStill;
    public static B_3871_I iconLavaFlow;
    public static B_3871_I iconFireLayer0;
    public static B_3871_I iconFireLayer1;
    public static B_3871_I iconSoulFireLayer0;
    public static B_3871_I iconSoulFireLayer1;
    public static B_3871_I iconCampFire;
    public static B_3871_I iconCampFireLogLit;
    public static B_3871_I iconSoulCampFire;
    public static B_3871_I iconSoulCampFireLogLit;
    public static B_3871_I iconPortal;
    public static B_3871_I iconGlass;
    public static B_3871_I iconGlassPaneTop;
    public static final String SPRITE_PREFIX_BLOCKS = "minecraft:block/";
    public static final String SPRITE_PREFIX_ITEMS = "minecraft:item/";
    public static final g_2336_b LOCATION_SPRITE_EMPTY;
    public static final g_2336_b LOCATION_TEXTURE_EMPTY;
    private static IntBuffer staticBuffer;
    private static int glMaximumTextureSize;
    private static Map<Integer, String> mapTextureAllocations;

    public static void update() {
        L_3848_p atlastexture = TextureUtils.getTextureMapBlocks();
        if (atlastexture != null) {
            String s = SPRITE_PREFIX_BLOCKS;
            iconGrassTop = TextureUtils.getSpriteCheck(atlastexture, s + texGrassTop);
            iconGrassSide = TextureUtils.getSpriteCheck(atlastexture, s + texGrassSide);
            iconGrassSideOverlay = TextureUtils.getSpriteCheck(atlastexture, s + texGrassSideOverlay);
            iconSnow = TextureUtils.getSpriteCheck(atlastexture, s + texSnow);
            iconGrassSideSnowed = TextureUtils.getSpriteCheck(atlastexture, s + texGrassSideSnowed);
            iconMyceliumSide = TextureUtils.getSpriteCheck(atlastexture, s + texMyceliumSide);
            iconMyceliumTop = TextureUtils.getSpriteCheck(atlastexture, s + texMyceliumTop);
            iconWaterStill = TextureUtils.getSpriteCheck(atlastexture, s + texWaterStill);
            iconWaterFlow = TextureUtils.getSpriteCheck(atlastexture, s + texWaterFlow);
            iconLavaStill = TextureUtils.getSpriteCheck(atlastexture, s + texLavaStill);
            iconLavaFlow = TextureUtils.getSpriteCheck(atlastexture, s + texLavaFlow);
            iconFireLayer0 = TextureUtils.getSpriteCheck(atlastexture, s + texFireLayer0);
            iconFireLayer1 = TextureUtils.getSpriteCheck(atlastexture, s + texFireLayer1);
            iconSoulFireLayer0 = TextureUtils.getSpriteCheck(atlastexture, s + texSoulFireLayer0);
            iconSoulFireLayer1 = TextureUtils.getSpriteCheck(atlastexture, s + texSoulFireLayer1);
            iconCampFire = TextureUtils.getSpriteCheck(atlastexture, s + texCampFire);
            iconCampFireLogLit = TextureUtils.getSpriteCheck(atlastexture, s + texCampFireLogLit);
            iconSoulCampFire = TextureUtils.getSpriteCheck(atlastexture, s + texSoulCampFire);
            iconSoulCampFireLogLit = TextureUtils.getSpriteCheck(atlastexture, s + texSoulCampFireLogLit);
            iconPortal = TextureUtils.getSpriteCheck(atlastexture, s + texPortal);
            iconGlass = TextureUtils.getSpriteCheck(atlastexture, s + texGlass);
            iconGlassPaneTop = TextureUtils.getSpriteCheck(atlastexture, s + texGlassPaneTop);
            String string = SPRITE_PREFIX_ITEMS;
        }
    }

    public static B_3871_I getSpriteCheck(L_3848_p textureMap, String name) {
        B_3871_I textureatlassprite = textureMap.J_1907_R(name);
        if (textureatlassprite == null || textureatlassprite instanceof F_3565_Q) {
            Config.warn("Sprite not found: " + name);
        }
        return textureatlassprite;
    }

    public static BufferedImage fixTextureDimensions(String name, BufferedImage bi) {
        int j;
        int i;
        if ((name.startsWith("/mob/zombie") || name.startsWith("/mob/pigzombie")) && (i = bi.getWidth()) == (j = bi.getHeight()) * 2) {
            BufferedImage bufferedimage = new BufferedImage(i, j * 2, 2);
            Graphics2D graphics2d = bufferedimage.createGraphics();
            graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2d.drawImage(bi, 0, 0, i, j, null);
            return bufferedimage;
        }
        return bi;
    }

    public static int ceilPowerOfTwo(int val) {
        int i;
        for (i = 1; i < val; i *= 2) {
        }
        return i;
    }

    public static int getPowerOfTwo(int val) {
        int i = 1;
        int j = 0;
        while (i < val) {
            i *= 2;
            ++j;
        }
        return j;
    }

    public static int twoToPower(int power) {
        int i = 1;
        for (int j = 0; j < power; ++j) {
            i *= 2;
        }
        return i;
    }

    public static c_4477_a getTexture(g_2336_b loc) {
        c_4477_a texture = Config.getTextureManager().J_1907_R(loc);
        if (texture != null) {
            return texture;
        }
        if (!Config.hasResource(loc)) {
            return null;
        }
        texture = new P_4645_d(loc);
        Config.getTextureManager().n_1700_B(loc, texture);
        return texture;
    }

    public static void resourcesReloaded(ResourceManager rm) {
        if (TextureUtils.getTextureMapBlocks() != null) {
            Config.dbg("*** Reloading custom textures ***");
            CustomSky.reset();
            TextureAnimations.reset();
            TextureUtils.update();
            NaturalTextures.update();
            BetterGrass.update();
            BetterSnow.update();
            TextureAnimations.update();
            CustomColors.update();
            CustomSky.update();
            RandomEntities.update();
            CustomItems.updateModels();
            CustomEntityModels.update();
            Shaders.resourcesReloaded();
            Lang.resourcesReloaded();
            Config.updateTexturePackClouds();
            SmartLeaves.updateLeavesModels();
            CustomPanorama.update();
            CustomGuis.update();
            N_4235_V.n_1700_B();
            CustomLoadingScreens.update();
            CustomBlockLayers.update();
            Config.getTextureManager().tick();
            Config.dbg("Disable Forge light pipeline");
            ReflectorForge.setForgeLightPipelineEnabled(false);
        }
    }

    public static L_3848_p getTextureMapBlocks() {
        return Config.getTextureMap();
    }

    public static void registerResourceListener() {
        ResourceManager iresourcemanager = Config.getResourceManager();
        if (iresourcemanager instanceof ReloadableResourceManager) {
            ReloadableResourceManager ireloadableresourcemanager = (ReloadableResourceManager)iresourcemanager;
            SimplePreparableReloadListener reloadlistener = new SimplePreparableReloadListener(){

                protected Object prepare(ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
                    return null;
                }

                protected void apply(Object objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
                }
            };
            ireloadableresourcemanager.n_1700_B(reloadlistener);
            ResourceManagerReloadListener iresourcemanagerreloadlistener = new ResourceManagerReloadListener(){

                @Override
                public void onResourceManagerReload(ResourceManager resourceManager) {
                    TextureUtils.resourcesReloaded(resourceManager);
                }
            };
            ireloadableresourcemanager.n_1700_B(iresourcemanagerreloadlistener);
        }
    }

    public static void registerTickableTextures() {
        TickableTexture tickabletexture = new TickableTexture(){

            @Override
            public void tick() {
                TextureAnimations.updateAnimations();
            }

            @Override
            public void loadTexture(ResourceManager var1) throws IOException {
            }

            @Override
            public int getGlTextureId() {
                return 0;
            }

            @Override
            public void restoreLastBlurMipmap() {
            }

            @Override
            public MultiTexID getMultiTexID() {
                return null;
            }
        };
        g_2336_b resourcelocation = new g_2336_b("optifine/tickable_textures");
        Config.getTextureManager().n_1700_B(resourcelocation, tickabletexture);
    }

    public static void registerCustomModels(g_2561_p modelBakery) {
        CustomItems.update();
        CustomItems.loadModels(modelBakery);
    }

    public static void registerCustomSprites(L_3848_p textureMap) {
        if (textureMap.R_4764_Y().equals(L_3848_p.n_1700_B)) {
            ConnectedTextures.updateIcons(textureMap);
            CustomItems.updateIcons(textureMap);
            BetterGrass.updateIcons(textureMap);
        }
    }

    public static void refreshCustomSprites(L_3848_p textureMap) {
        if (textureMap.R_4764_Y().equals(L_3848_p.n_1700_B)) {
            ConnectedTextures.refreshIcons(textureMap);
            CustomItems.refreshIcons(textureMap);
            BetterGrass.refreshIcons(textureMap);
        }
        EmissiveTextures.refreshIcons(textureMap);
    }

    public static g_2336_b fixResourceLocation(g_2336_b loc, String basePath) {
        if (!loc.R_4764_Y().equals("minecraft")) {
            return loc;
        }
        String s = loc.J_1907_R();
        String s1 = TextureUtils.fixResourcePath(s, basePath);
        if (s1 != s) {
            loc = new g_2336_b(loc.R_4764_Y(), s1);
        }
        return loc;
    }

    public static String fixResourcePath(String path, String basePath) {
        String s = "assets/minecraft/";
        if (path.startsWith(s)) {
            return path.substring(s.length());
        }
        if (path.startsWith("./")) {
            path = path.substring(2);
            if (!((String)basePath).endsWith("/")) {
                basePath = (String)basePath + "/";
            }
            return (String)basePath + path;
        }
        if (path.startsWith("/~")) {
            path = path.substring(1);
        }
        String s1 = "optifine/";
        if (path.startsWith("~/")) {
            path = path.substring(2);
            return s1 + path;
        }
        return path.startsWith("/") ? s1 + path.substring(1) : path;
    }

    public static String getBasePath(String path) {
        int i = path.lastIndexOf(47);
        return i < 0 ? "" : path.substring(0, i);
    }

    public static void applyAnisotropicLevel() {
        if (GL.getCapabilities().GL_EXT_texture_filter_anisotropic) {
            float f = GL11.glGetFloat((int)34047);
            float f1 = Config.getAnisotropicFilterLevel();
            f1 = Math.min(f1, f);
            GL11.glTexParameterf((int)3553, (int)34046, (float)f1);
        }
    }

    public static void bindTexture(int glTexId) {
        X_933_l.w_1457_N(glTexId);
    }

    public static boolean isPowerOfTwo(int x) {
        int i = u_530_F.R_4764_Y(x);
        return i == x;
    }

    public static i_2518_W scaleImage(i_2518_W ni, int w2) {
        BufferedImage bufferedimage = TextureUtils.toBufferedImage(ni);
        BufferedImage bufferedimage1 = TextureUtils.scaleImage(bufferedimage, w2);
        return TextureUtils.toNativeImage(bufferedimage1);
    }

    public static BufferedImage toBufferedImage(i_2518_W ni) {
        int i = ni.n_1700_B();
        int j = ni.J_1907_R();
        int[] aint = new int[i * j];
        ni.w_1484_f().get(aint);
        BufferedImage bufferedimage = new BufferedImage(i, j, 2);
        bufferedimage.setRGB(0, 0, i, j, aint, 0, i);
        return bufferedimage;
    }

    private static i_2518_W toNativeImage(BufferedImage bi) {
        int i = bi.getWidth();
        int j = bi.getHeight();
        int[] aint = new int[i * j];
        bi.getRGB(0, 0, i, j, aint, 0, i);
        i_2518_W nativeimage = new i_2518_W(i, j, false);
        nativeimage.w_1484_f().put(aint);
        return nativeimage;
    }

    public static BufferedImage scaleImage(BufferedImage bi, int w2) {
        int i = bi.getWidth();
        int j = bi.getHeight();
        int k = j * w2 / i;
        BufferedImage bufferedimage = new BufferedImage(w2, k, 2);
        Graphics2D graphics2d = bufferedimage.createGraphics();
        Object object = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
        if (w2 < i || w2 % i != 0) {
            object = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
        }
        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, object);
        graphics2d.drawImage(bi, 0, 0, w2, k, null);
        return bufferedimage;
    }

    public static int scaleToGrid(int size, int sizeGrid) {
        int i;
        if (size == sizeGrid) {
            return size;
        }
        for (i = size / sizeGrid * sizeGrid; i < size; i += sizeGrid) {
        }
        return i;
    }

    public static int scaleToMin(int size, int sizeMin) {
        int i;
        if (size >= sizeMin) {
            return size;
        }
        for (i = sizeMin / size * size; i < sizeMin; i += size) {
        }
        return i;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Dimension getImageSize(InputStream in, String suffix) {
        Iterator<ImageReader> iterator = ImageIO.getImageReadersBySuffix(suffix);
        while (iterator.hasNext()) {
            Dimension dimension;
            ImageReader imagereader = iterator.next();
            try {
                ImageInputStream imageinputstream = ImageIO.createImageInputStream(in);
                imagereader.setInput(imageinputstream);
                int i = imagereader.getWidth(imagereader.getMinIndex());
                int j = imagereader.getHeight(imagereader.getMinIndex());
                dimension = new Dimension(i, j);
            }
            catch (IOException ioexception) {}
            continue;
            finally {
                imagereader.dispose();
                continue;
            }
            return dimension;
        }
        return null;
    }

    public static void dbgMipmaps(B_3871_I textureatlassprite) {
        i_2518_W[] anativeimage = textureatlassprite.Q_2552_b();
        for (int i = 0; i < anativeimage.length; ++i) {
            i_2518_W nativeimage = anativeimage[i];
            if (nativeimage == null) {
                Config.dbg(i + ": " + String.valueOf(nativeimage));
                continue;
            }
            Config.dbg(i + ": " + nativeimage.n_1700_B() * nativeimage.J_1907_R());
        }
    }

    public static void saveGlTexture(String name, int textureId, int mipmapLevels, int width, int height) {
        TextureUtils.bindTexture(textureId);
        GL11.glPixelStorei((int)3333, (int)1);
        GL11.glPixelStorei((int)3317, (int)1);
        name = StrUtils.removeSuffix(name, ".png");
        File file1 = new File(name);
        File file2 = file1.getParentFile();
        if (file2 != null) {
            file2.mkdirs();
        }
        for (int i = 0; i < 16; ++i) {
            String s = name + "_" + i + ".png";
            File file3 = new File(s);
            file3.delete();
        }
        for (int l = 0; l <= mipmapLevels; ++l) {
            File file4 = new File(name + "_" + l + ".png");
            int i1 = width >> l;
            int j = height >> l;
            int k = i1 * j;
            IntBuffer intbuffer = BufferUtils.createIntBuffer((int)k);
            int[] aint = new int[k];
            GL11.glGetTexImage((int)3553, (int)l, (int)32993, (int)33639, (IntBuffer)intbuffer);
            intbuffer.get(aint);
            BufferedImage bufferedimage = new BufferedImage(i1, j, 2);
            bufferedimage.setRGB(0, 0, i1, j, aint, 0, i1);
            try {
                ImageIO.write((RenderedImage)bufferedimage, "png", file4);
                Config.dbg("Exported: " + String.valueOf(file4));
                continue;
            }
            catch (Exception exception) {
                Config.warn("Error writing: " + String.valueOf(file4));
                Config.warn(exception.getClass().getName() + ": " + exception.getMessage());
            }
        }
    }

    public static int getGLMaximumTextureSize() {
        if (glMaximumTextureSize < 0) {
            glMaximumTextureSize = TextureUtils.detectGLMaximumTextureSize();
        }
        return glMaximumTextureSize;
    }

    private static int detectGLMaximumTextureSize() {
        for (int i = 65536; i > 0; i >>= 1) {
            X_933_l.n_1700_B(32868, 0, 6408, i, i, 0, 6408, 5121, null);
            int j = GL11.glGetError();
            int k = X_933_l.R_4764_Y(32868, 0, 4096);
            if (k == 0) continue;
            return i;
        }
        return 0;
    }

    public static BufferedImage readBufferedImage(InputStream imageStream) throws IOException {
        BufferedImage bufferedimage1;
        if (imageStream == null) {
            return null;
        }
        try {
            BufferedImage bufferedimage;
            bufferedimage1 = bufferedimage = ImageIO.read(imageStream);
        }
        finally {
            IOUtils.closeQuietly((InputStream)imageStream);
        }
        return bufferedimage1;
    }

    public static int toAbgr(int argb) {
        int i = argb >> 24 & 0xFF;
        int j = argb >> 16 & 0xFF;
        int k = argb >> 8 & 0xFF;
        int l = argb >> 0 & 0xFF;
        return i << 24 | l << 16 | k << 8 | j;
    }

    public static void resetDataUnpacking() {
        X_933_l.h_1847_R(3314, 0);
        X_933_l.h_1847_R(3316, 0);
        X_933_l.h_1847_R(3315, 0);
        X_933_l.h_1847_R(3317, 4);
    }

    public static String getStackTrace(Throwable t) {
        CharArrayWriter chararraywriter = new CharArrayWriter();
        t.printStackTrace(new PrintWriter(chararraywriter));
        return chararraywriter.toString();
    }

    public static void debugTextureGenerated(int id) {
        mapTextureAllocations.put(id, TextureUtils.getStackTrace(new Throwable("StackTrace")));
        Config.dbg("Textures: " + mapTextureAllocations.size());
    }

    public static void debugTextureDeleted(int id) {
        mapTextureAllocations.remove(id);
        Config.dbg("Textures: " + mapTextureAllocations.size());
    }

    static {
        LOCATION_SPRITE_EMPTY = new g_2336_b("optifine/ctm/default/empty");
        LOCATION_TEXTURE_EMPTY = new g_2336_b("optifine/ctm/default/empty.png");
        staticBuffer = Config.createDirectIntBuffer(256);
        glMaximumTextureSize = -1;
        mapTextureAllocations = new HashMap<Integer, String>();
    }
}


