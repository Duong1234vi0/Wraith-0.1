/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.tuple.ImmutablePair
 *  org.apache.commons.lang3.tuple.Pair
 */
package net.optifine;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;
import lightning.product.D_3746_J;
import lightning.product.LeavesBlock;
import lightning.product.BlockGetter;
import lightning.product.FoliageColor;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.BlockAndTintGetter;
import lightning.product.Z_1993_T;
import lightning.product.Z_4734_t;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_3457_g;
import lightning.product.c_932_S;
import lightning.product.e_2866_D;
import lightning.product.e_933_M;
import lightning.product.g_2336_b;
import lightning.product.g_3212_H;
import lightning.product.g_422_i;
import lightning.product.i_2518_W;
import lightning.product.k_4467_X;
import lightning.product.k_4690_i;
import lightning.product.k_594_Q;
import lightning.product.DoublePlantBlock;
import lightning.product.q_1613_l;
import lightning.product.s_3109_F;
import lightning.product.Material;
import lightning.product.t_5_h;
import lightning.product.MaterialColor;
import lightning.product.u_530_F;
import lightning.product.SpawnEggItem;
import net.optifine.BlockPosM;
import net.optifine.Config;
import net.optifine.CustomColorFader;
import net.optifine.CustomColormap;
import net.optifine.LightMap;
import net.optifine.LightMapPack;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.render.RenderEnv;
import net.optifine.util.BiomeUtils;
import net.optifine.util.EntityUtils;
import net.optifine.util.PotionUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import net.optifine.util.WorldUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class CustomColors {
    private static String paletteFormatDefault = "vanilla";
    private static CustomColormap waterColors = null;
    private static CustomColormap foliagePineColors = null;
    private static CustomColormap foliageBirchColors = null;
    private static CustomColormap swampFoliageColors = null;
    private static CustomColormap swampGrassColors = null;
    private static CustomColormap[] colorsBlockColormaps = null;
    private static CustomColormap[][] blockColormaps = null;
    private static CustomColormap skyColors = null;
    private static CustomColorFader skyColorFader = new CustomColorFader();
    private static CustomColormap fogColors = null;
    private static CustomColorFader fogColorFader = new CustomColorFader();
    private static CustomColormap underwaterColors = null;
    private static CustomColorFader underwaterColorFader = new CustomColorFader();
    private static CustomColormap underlavaColors = null;
    private static CustomColorFader underlavaColorFader = new CustomColorFader();
    private static LightMapPack[] lightMapPacks = null;
    private static int lightmapMinDimensionId = 0;
    private static CustomColormap redstoneColors = null;
    private static CustomColormap xpOrbColors = null;
    private static int xpOrbTime = -1;
    private static CustomColormap durabilityColors = null;
    private static CustomColormap stemColors = null;
    private static CustomColormap stemMelonColors = null;
    private static CustomColormap stemPumpkinColors = null;
    private static CustomColormap myceliumParticleColors = null;
    private static boolean useDefaultGrassFoliageColors = true;
    private static int particleWaterColor = -1;
    private static int particlePortalColor = -1;
    private static int lilyPadColor = -1;
    private static int expBarTextColor = -1;
    private static int bossTextColor = -1;
    private static int signTextColor = -1;
    private static e_2866_D fogColorNether = null;
    private static e_2866_D fogColorEnd = null;
    private static e_2866_D skyColorEnd = null;
    private static int[] spawnEggPrimaryColors = null;
    private static int[] spawnEggSecondaryColors = null;
    private static float[][] wolfCollarColors = null;
    private static float[][] sheepColors = null;
    private static int[] textColors = null;
    private static int[] mapColorsOriginal = null;
    private static float[][] dyeColorsOriginal = null;
    private static int[] potionColors = null;
    private static final K_4074_S BLOCK_STATE_DIRT = a_3742_W.s_956_w.multiplayerClientSuggestionProvider();
    private static final K_4074_S BLOCK_STATE_WATER = a_3742_W.c_3005_b.multiplayerClientSuggestionProvider();
    public static Random random = new Random();
    private static final IColorizer COLORIZER_GRASS = new IColorizer(){

        @Override
        public int getColor(K_4074_S blockState, BlockAndTintGetter blockAccess, c_1514_x blockPos) {
            k_594_Q biome = CustomColors.getColorBiome(blockAccess, blockPos);
            return swampGrassColors != null && biome == BiomeUtils.SWAMP ? swampGrassColors.getColor(biome, blockPos) : biome.n_1700_B(blockPos.getX(), blockPos.getZ());
        }

        @Override
        public boolean isColorConstant() {
            return false;
        }
    };
    private static final IColorizer COLORIZER_FOLIAGE = new IColorizer(){

        @Override
        public int getColor(K_4074_S blockState, BlockAndTintGetter blockAccess, c_1514_x blockPos) {
            k_594_Q biome = CustomColors.getColorBiome(blockAccess, blockPos);
            return swampFoliageColors != null && biome == BiomeUtils.SWAMP ? swampFoliageColors.getColor(biome, blockPos) : biome.v_4262_N();
        }

        @Override
        public boolean isColorConstant() {
            return false;
        }
    };
    private static final IColorizer COLORIZER_FOLIAGE_PINE = new IColorizer(){

        @Override
        public int getColor(K_4074_S blockState, BlockAndTintGetter blockAccess, c_1514_x blockPos) {
            return foliagePineColors != null ? foliagePineColors.getColor(blockAccess, blockPos) : FoliageColor.n_1700_B();
        }

        @Override
        public boolean isColorConstant() {
            return foliagePineColors == null;
        }
    };
    private static final IColorizer COLORIZER_FOLIAGE_BIRCH = new IColorizer(){

        @Override
        public int getColor(K_4074_S blockState, BlockAndTintGetter blockAccess, c_1514_x blockPos) {
            return foliageBirchColors != null ? foliageBirchColors.getColor(blockAccess, blockPos) : FoliageColor.J_1907_R();
        }

        @Override
        public boolean isColorConstant() {
            return foliageBirchColors == null;
        }
    };
    private static final IColorizer COLORIZER_WATER = new IColorizer(){

        @Override
        public int getColor(K_4074_S blockState, BlockAndTintGetter blockAccess, c_1514_x blockPos) {
            k_594_Q biome = CustomColors.getColorBiome(blockAccess, blockPos);
            return waterColors != null ? waterColors.getColor(biome, blockPos) : biome.P_4830_p();
        }

        @Override
        public boolean isColorConstant() {
            return false;
        }
    };

    public static void update() {
        paletteFormatDefault = "vanilla";
        waterColors = null;
        foliageBirchColors = null;
        foliagePineColors = null;
        swampGrassColors = null;
        swampFoliageColors = null;
        skyColors = null;
        fogColors = null;
        underwaterColors = null;
        underlavaColors = null;
        redstoneColors = null;
        xpOrbColors = null;
        xpOrbTime = -1;
        durabilityColors = null;
        stemColors = null;
        myceliumParticleColors = null;
        lightMapPacks = null;
        particleWaterColor = -1;
        particlePortalColor = -1;
        lilyPadColor = -1;
        expBarTextColor = -1;
        bossTextColor = -1;
        signTextColor = -1;
        fogColorNether = null;
        fogColorEnd = null;
        skyColorEnd = null;
        colorsBlockColormaps = null;
        blockColormaps = null;
        useDefaultGrassFoliageColors = true;
        spawnEggPrimaryColors = null;
        spawnEggSecondaryColors = null;
        wolfCollarColors = null;
        sheepColors = null;
        textColors = null;
        CustomColors.setMapColors(mapColorsOriginal);
        CustomColors.setDyeColors(dyeColorsOriginal);
        potionColors = null;
        paletteFormatDefault = CustomColors.getValidProperty("optifine/color.properties", "palette.format", CustomColormap.FORMAT_STRINGS, "vanilla");
        String s = "optifine/colormap/";
        String[] astring = new String[]{"water.png", "watercolorx.png"};
        waterColors = CustomColors.getCustomColors(s, astring, 256, 256);
        CustomColors.updateUseDefaultGrassFoliageColors();
        if (Config.isCustomColors()) {
            String[] astring1 = new String[]{"pine.png", "pinecolor.png"};
            foliagePineColors = CustomColors.getCustomColors(s, astring1, 256, 256);
            String[] astring2 = new String[]{"birch.png", "birchcolor.png"};
            foliageBirchColors = CustomColors.getCustomColors(s, astring2, 256, 256);
            String[] astring3 = new String[]{"swampgrass.png", "swampgrasscolor.png"};
            swampGrassColors = CustomColors.getCustomColors(s, astring3, 256, 256);
            String[] astring4 = new String[]{"swampfoliage.png", "swampfoliagecolor.png"};
            swampFoliageColors = CustomColors.getCustomColors(s, astring4, 256, 256);
            String[] astring5 = new String[]{"sky0.png", "skycolor0.png"};
            skyColors = CustomColors.getCustomColors(s, astring5, 256, 256);
            String[] astring6 = new String[]{"fog0.png", "fogcolor0.png"};
            fogColors = CustomColors.getCustomColors(s, astring6, 256, 256);
            String[] astring7 = new String[]{"underwater.png", "underwatercolor.png"};
            underwaterColors = CustomColors.getCustomColors(s, astring7, 256, 256);
            String[] astring8 = new String[]{"underlava.png", "underlavacolor.png"};
            underlavaColors = CustomColors.getCustomColors(s, astring8, 256, 256);
            String[] astring9 = new String[]{"redstone.png", "redstonecolor.png"};
            redstoneColors = CustomColors.getCustomColors(s, astring9, 16, 1);
            xpOrbColors = CustomColors.getCustomColors(s + "xporb.png", -1, -1);
            durabilityColors = CustomColors.getCustomColors(s + "durability.png", -1, -1);
            String[] astring10 = new String[]{"stem.png", "stemcolor.png"};
            stemColors = CustomColors.getCustomColors(s, astring10, 8, 1);
            stemPumpkinColors = CustomColors.getCustomColors(s + "pumpkinstem.png", 8, 1);
            stemMelonColors = CustomColors.getCustomColors(s + "melonstem.png", 8, 1);
            String[] astring11 = new String[]{"myceliumparticle.png", "myceliumparticlecolor.png"};
            myceliumParticleColors = CustomColors.getCustomColors(s, astring11, -1, -1);
            Pair<LightMapPack[], Integer> pair = CustomColors.parseLightMapPacks();
            lightMapPacks = (LightMapPack[])pair.getLeft();
            lightmapMinDimensionId = (Integer)pair.getRight();
            CustomColors.readColorProperties("optifine/color.properties");
            blockColormaps = CustomColors.readBlockColormaps(new String[]{s + "custom/", s + "blocks/"}, colorsBlockColormaps, 256, 256);
            CustomColors.updateUseDefaultGrassFoliageColors();
        }
    }

    private static String getValidProperty(String fileName, String key, String[] validValues, String valDef) {
        try {
            g_2336_b resourcelocation = new g_2336_b(fileName);
            InputStream inputstream = Config.getResourceStream(resourcelocation);
            if (inputstream == null) {
                return valDef;
            }
            PropertiesOrdered properties = new PropertiesOrdered();
            properties.load(inputstream);
            inputstream.close();
            String s = properties.getProperty(key);
            if (s == null) {
                return valDef;
            }
            List<String> list = Arrays.asList(validValues);
            if (!list.contains(s)) {
                CustomColors.warn("Invalid value: " + key + "=" + s);
                CustomColors.warn("Expected values: " + Config.arrayToString(validValues));
                return valDef;
            }
            CustomColors.dbg(key + "=" + s);
            return s;
        }
        catch (FileNotFoundException filenotfoundexception) {
            return valDef;
        }
        catch (IOException ioexception) {
            ioexception.printStackTrace();
            return valDef;
        }
    }

    private static Pair<LightMapPack[], Integer> parseLightMapPacks() {
        String s = "optifine/lightmap/world";
        String s1 = ".png";
        String[] astring = ResUtils.collectFiles(s, s1);
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        for (int i = 0; i < astring.length; ++i) {
            String s2 = astring[i];
            String s3 = StrUtils.removePrefixSuffix(s2, s, s1);
            int j = Config.parseInt(s3, Integer.MIN_VALUE);
            if (j == Integer.MIN_VALUE) {
                CustomColors.warn("Invalid dimension ID: " + s3 + ", path: " + s2);
                continue;
            }
            map.put(j, s2);
        }
        Set set = map.keySet();
        Integer[] ainteger = set.toArray(new Integer[set.size()]);
        Arrays.sort((Object[])ainteger);
        if (ainteger.length <= 0) {
            return new ImmutablePair((Object)null, (Object)0);
        }
        int j1 = ainteger[0];
        int k1 = ainteger[ainteger.length - 1];
        int k = k1 - j1 + 1;
        CustomColormap[] acustomcolormap = new CustomColormap[k];
        for (int l = 0; l < ainteger.length; ++l) {
            Integer integer = ainteger[l];
            String s4 = (String)map.get(integer);
            CustomColormap customcolormap = CustomColors.getCustomColors(s4, -1, -1);
            if (customcolormap == null) continue;
            if (customcolormap.getWidth() < 16) {
                CustomColors.warn("Invalid lightmap width: " + customcolormap.getWidth() + ", path: " + s4);
                continue;
            }
            int i1 = integer - j1;
            acustomcolormap[i1] = customcolormap;
        }
        LightMapPack[] alightmappack = new LightMapPack[acustomcolormap.length];
        for (int l1 = 0; l1 < acustomcolormap.length; ++l1) {
            LightMapPack lightmappack;
            CustomColormap customcolormap3 = acustomcolormap[l1];
            if (customcolormap3 == null) continue;
            String s5 = customcolormap3.name;
            String s6 = customcolormap3.basePath;
            CustomColormap customcolormap1 = CustomColors.getCustomColors(s6 + "/" + s5 + "_rain.png", -1, -1);
            CustomColormap customcolormap2 = CustomColors.getCustomColors(s6 + "/" + s5 + "_thunder.png", -1, -1);
            LightMap lightmap = new LightMap(customcolormap3);
            LightMap lightmap1 = customcolormap1 != null ? new LightMap(customcolormap1) : null;
            LightMap lightmap2 = customcolormap2 != null ? new LightMap(customcolormap2) : null;
            alightmappack[l1] = lightmappack = new LightMapPack(lightmap, lightmap1, lightmap2);
        }
        return new ImmutablePair((Object)alightmappack, (Object)j1);
    }

    private static int getTextureHeight(String path, int defHeight) {
        try {
            InputStream inputstream = Config.getResourceStream(new g_2336_b(path));
            if (inputstream == null) {
                return defHeight;
            }
            BufferedImage bufferedimage = ImageIO.read(inputstream);
            inputstream.close();
            return bufferedimage == null ? defHeight : bufferedimage.getHeight();
        }
        catch (IOException ioexception) {
            return defHeight;
        }
    }

    private static void readColorProperties(String fileName) {
        try {
            float[][] afloat;
            g_2336_b resourcelocation = new g_2336_b(fileName);
            InputStream inputstream = Config.getResourceStream(resourcelocation);
            if (inputstream == null) {
                return;
            }
            CustomColors.dbg("Loading " + fileName);
            PropertiesOrdered properties = new PropertiesOrdered();
            properties.load(inputstream);
            inputstream.close();
            particleWaterColor = CustomColors.readColor((Properties)properties, new String[]{"particle.water", "drop.water"});
            particlePortalColor = CustomColors.readColor((Properties)properties, "particle.portal");
            lilyPadColor = CustomColors.readColor((Properties)properties, "lilypad");
            expBarTextColor = CustomColors.readColor((Properties)properties, "text.xpbar");
            bossTextColor = CustomColors.readColor((Properties)properties, "text.boss");
            signTextColor = CustomColors.readColor((Properties)properties, "text.sign");
            fogColorNether = CustomColors.readColorVec3(properties, "fog.nether");
            fogColorEnd = CustomColors.readColorVec3(properties, "fog.end");
            skyColorEnd = CustomColors.readColorVec3(properties, "sky.end");
            colorsBlockColormaps = CustomColors.readCustomColormaps(properties, fileName);
            spawnEggPrimaryColors = CustomColors.readSpawnEggColors(properties, fileName, "egg.shell.", "Spawn egg shell");
            spawnEggSecondaryColors = CustomColors.readSpawnEggColors(properties, fileName, "egg.spots.", "Spawn egg spot");
            wolfCollarColors = CustomColors.readDyeColors(properties, fileName, "collar.", "Wolf collar");
            sheepColors = CustomColors.readDyeColors(properties, fileName, "sheep.", "Sheep");
            textColors = CustomColors.readTextColors(properties, fileName, "text.code.", "Text");
            int[] aint = CustomColors.readMapColors(properties, fileName, "map.", "Map");
            if (aint != null) {
                if (mapColorsOriginal == null) {
                    mapColorsOriginal = CustomColors.getMapColors();
                }
                CustomColors.setMapColors(aint);
            }
            if ((afloat = CustomColors.readDyeColors(properties, fileName, "dye.", "Dye")) != null) {
                if (dyeColorsOriginal == null) {
                    dyeColorsOriginal = CustomColors.getDyeColors();
                }
                CustomColors.setDyeColors(afloat);
            }
            potionColors = CustomColors.readPotionColors(properties, fileName, "potion.", "Potion");
            xpOrbTime = Config.parseInt(properties.getProperty("xporb.time"), -1);
        }
        catch (FileNotFoundException filenotfoundexception) {
            return;
        }
        catch (IOException ioexception) {
            Config.warn("Error parsing: " + fileName);
            Config.warn(ioexception.getClass().getName() + ": " + ioexception.getMessage());
        }
    }

    /*
     * WARNING - void declaration
     */
    private static CustomColormap[] readCustomColormaps(Properties props, String fileName) {
        void var6_8;
        ArrayList<CustomColormap> list = new ArrayList<CustomColormap>();
        String s = "palette.block.";
        HashMap<String, String> map = new HashMap<String, String>();
        for (String string : props.keySet()) {
            String s2 = props.getProperty(string);
            if (!string.startsWith(s)) continue;
            map.put(string, s2);
        }
        String[] astring = map.keySet().toArray(new String[map.size()]);
        boolean bl = false;
        while (var6_8 < astring.length) {
            String s6 = astring[var6_8];
            String s3 = props.getProperty(s6);
            CustomColors.dbg("Block palette: " + s6 + " = " + s3);
            String s4 = s6.substring(s.length());
            String s5 = TextureUtils.getBasePath(fileName);
            s4 = TextureUtils.fixResourcePath(s4, s5);
            CustomColormap customcolormap = CustomColors.getCustomColors(s4, 256, 256);
            if (customcolormap == null) {
                CustomColors.warn("Colormap not found: " + s4);
            } else {
                ConnectedParser connectedparser = new ConnectedParser("CustomColors");
                MatchBlock[] amatchblock = connectedparser.parseMatchBlocks(s3);
                if (amatchblock != null && amatchblock.length > 0) {
                    for (int i = 0; i < amatchblock.length; ++i) {
                        MatchBlock matchblock = amatchblock[i];
                        customcolormap.addMatchBlock(matchblock);
                    }
                    list.add(customcolormap);
                } else {
                    CustomColors.warn("Invalid match blocks: " + s3);
                }
            }
            ++var6_8;
        }
        return list.size() <= 0 ? null : list.toArray(new CustomColormap[list.size()]);
    }

    private static CustomColormap[][] readBlockColormaps(String[] basePaths, CustomColormap[] basePalettes, int width, int height) {
        String[] astring = ResUtils.collectFiles(basePaths, new String[]{".properties"});
        Arrays.sort(astring);
        ArrayList list = new ArrayList();
        for (int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            CustomColors.dbg("Block colormap: " + s);
            try {
                g_2336_b resourcelocation = new g_2336_b("minecraft", s);
                InputStream inputstream = Config.getResourceStream(resourcelocation);
                if (inputstream == null) {
                    CustomColors.warn("File not found: " + s);
                    continue;
                }
                PropertiesOrdered properties = new PropertiesOrdered();
                properties.load(inputstream);
                inputstream.close();
                CustomColormap customcolormap = new CustomColormap(properties, s, width, height, paletteFormatDefault);
                if (!customcolormap.isValid(s) || !customcolormap.isValidMatchBlocks(s)) continue;
                CustomColors.addToBlockList(customcolormap, list);
                continue;
            }
            catch (FileNotFoundException filenotfoundexception) {
                CustomColors.warn("File not found: " + s);
                continue;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (basePalettes != null) {
            for (int j = 0; j < basePalettes.length; ++j) {
                CustomColormap customcolormap1 = basePalettes[j];
                CustomColors.addToBlockList(customcolormap1, list);
            }
        }
        return list.size() <= 0 ? (CustomColormap[][])null : CustomColors.blockListToArray(list);
    }

    private static void addToBlockList(CustomColormap cm, List blockList) {
        int[] aint = cm.getMatchBlockIds();
        if (aint != null && aint.length > 0) {
            for (int i = 0; i < aint.length; ++i) {
                int j = aint[i];
                if (j < 0) {
                    CustomColors.warn("Invalid block ID: " + j);
                    continue;
                }
                CustomColors.addToList(cm, blockList, j);
            }
        } else {
            CustomColors.warn("No match blocks: " + Config.arrayToString(aint));
        }
    }

    private static void addToList(CustomColormap cm, List list, int id) {
        while (id >= list.size()) {
            list.add(null);
        }
        ArrayList<CustomColormap> sublist = (ArrayList<CustomColormap>)list.get(id);
        if (sublist == null) {
            sublist = new ArrayList<CustomColormap>();
            list.set(id, sublist);
        }
        sublist.add(cm);
    }

    private static CustomColormap[][] blockListToArray(List list) {
        CustomColormap[][] acustomcolormap = new CustomColormap[list.size()][];
        for (int i = 0; i < list.size(); ++i) {
            List lista = (List)list.get(i);
            if (lista == null) continue;
            CustomColormap[] acustomcolormap1 = lista.toArray(new CustomColormap[lista.size()]);
            acustomcolormap[i] = acustomcolormap1;
        }
        return acustomcolormap;
    }

    private static int readColor(Properties props, String[] names) {
        for (int i = 0; i < names.length; ++i) {
            String s = names[i];
            int j = CustomColors.readColor(props, s);
            if (j < 0) continue;
            return j;
        }
        return -1;
    }

    private static int readColor(Properties props, String name) {
        String s = props.getProperty(name);
        if (s == null) {
            return -1;
        }
        int i = CustomColors.parseColor(s = s.trim());
        if (i < 0) {
            CustomColors.warn("Invalid color: " + name + " = " + s);
            return i;
        }
        CustomColors.dbg(name + " = " + s);
        return i;
    }

    private static int parseColor(String str) {
        if (str == null) {
            return -1;
        }
        str = str.trim();
        try {
            return Integer.parseInt(str, 16) & 0xFFFFFF;
        }
        catch (NumberFormatException numberformatexception) {
            return -1;
        }
    }

    private static e_2866_D readColorVec3(Properties props, String name) {
        int i = CustomColors.readColor(props, name);
        if (i < 0) {
            return null;
        }
        int j = i >> 16 & 0xFF;
        int k = i >> 8 & 0xFF;
        int l = i & 0xFF;
        float f = (float)j / 255.0f;
        float f1 = (float)k / 255.0f;
        float f2 = (float)l / 255.0f;
        return new e_2866_D(f, f1, f2);
    }

    private static CustomColormap getCustomColors(String basePath, String[] paths, int width, int height) {
        for (int i = 0; i < paths.length; ++i) {
            Object s = paths[i];
            CustomColormap customcolormap = CustomColors.getCustomColors((String)(s = basePath + (String)s), width, height);
            if (customcolormap == null) continue;
            return customcolormap;
        }
        return null;
    }

    public static CustomColormap getCustomColors(String pathImage, int width, int height) {
        try {
            g_2336_b resourcelocation = new g_2336_b(pathImage);
            if (!Config.hasResource(resourcelocation)) {
                return null;
            }
            CustomColors.dbg("Colormap " + pathImage);
            PropertiesOrdered properties = new PropertiesOrdered();
            String s = StrUtils.replaceSuffix(pathImage, ".png", ".properties");
            g_2336_b resourcelocation1 = new g_2336_b(s);
            if (Config.hasResource(resourcelocation1)) {
                InputStream inputstream = Config.getResourceStream(resourcelocation1);
                properties.load(inputstream);
                inputstream.close();
                CustomColors.dbg("Colormap properties: " + s);
            } else {
                ((Properties)properties).put("format", paletteFormatDefault);
                ((Properties)properties).put("source", pathImage);
                s = pathImage;
            }
            CustomColormap customcolormap = new CustomColormap(properties, s, width, height, paletteFormatDefault);
            return !customcolormap.isValid(s) ? null : customcolormap;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static void updateUseDefaultGrassFoliageColors() {
        useDefaultGrassFoliageColors = foliageBirchColors == null && foliagePineColors == null && swampGrassColors == null && swampFoliageColors == null && Config.isSwampColors();
    }

    public static int getColorMultiplier(c_932_S quad, K_4074_S blockState, BlockAndTintGetter blockAccess, c_1514_x blockPos, RenderEnv renderEnv) {
        IColorizer customcolors$icolorizer;
        T_2915_h block = blockState.J_1907_R();
        K_4074_S blockstate = blockState;
        if (blockColormaps != null) {
            CustomColormap customcolormap;
            if (!quad.hasTintIndex()) {
                if (block == a_3742_W.t_148_a) {
                    blockstate = BLOCK_STATE_DIRT;
                }
                if (block == a_3742_W.P_5000_x) {
                    return -1;
                }
            }
            if (block instanceof DoublePlantBlock && blockState.R_4764_Y(DoublePlantBlock.P_4830_p) == g_3212_H.n_1700_B) {
                blockPos = blockPos.down();
                blockstate = blockAccess.getBlockState(blockPos);
            }
            if ((customcolormap = CustomColors.getBlockColormap(blockstate)) != null) {
                if (Config.isSmoothBiomes() && !customcolormap.isColorConstant()) {
                    return CustomColors.getSmoothColorMultiplier(blockState, blockAccess, blockPos, customcolormap, renderEnv.getColorizerBlockPosM());
                }
                return customcolormap.getColor(blockAccess, blockPos);
            }
        }
        if (!quad.hasTintIndex()) {
            return -1;
        }
        if (block == a_3742_W.S_4035_N) {
            return CustomColors.getLilypadColorMultiplier(blockAccess, blockPos);
        }
        if (block == a_3742_W.P_5000_x) {
            return CustomColors.getRedstoneColor(renderEnv.getBlockState());
        }
        if (block instanceof D_3746_J) {
            return CustomColors.getStemColorMultiplier(blockState, blockAccess, blockPos, renderEnv);
        }
        if (useDefaultGrassFoliageColors) {
            return -1;
        }
        if (block != a_3742_W.t_148_a && block != a_3742_W.Party && !(block instanceof DoublePlantBlock)) {
            if (block instanceof DoublePlantBlock) {
                customcolors$icolorizer = COLORIZER_GRASS;
                if (blockState.R_4764_Y(DoublePlantBlock.P_4830_p) == g_3212_H.n_1700_B) {
                    blockPos = blockPos.down();
                }
            } else if (block instanceof LeavesBlock) {
                customcolors$icolorizer = block == a_3742_W.A_1038_p ? COLORIZER_FOLIAGE : (block == a_3742_W.i_1637_u ? COLORIZER_FOLIAGE_PINE : (block == a_3742_W.Ping ? COLORIZER_FOLIAGE_BIRCH : COLORIZER_FOLIAGE));
            } else {
                if (block != a_3742_W.U_4087_m) {
                    return -1;
                }
                customcolors$icolorizer = COLORIZER_FOLIAGE;
            }
        } else {
            customcolors$icolorizer = COLORIZER_GRASS;
        }
        return Config.isSmoothBiomes() && !customcolors$icolorizer.isColorConstant() ? CustomColors.getSmoothColorMultiplier(blockState, blockAccess, blockPos, customcolors$icolorizer, renderEnv.getColorizerBlockPosM()) : customcolors$icolorizer.getColor(blockstate, blockAccess, blockPos);
    }

    protected static k_594_Q getColorBiome(BlockAndTintGetter blockAccess, c_1514_x blockPos) {
        k_594_Q biome = BiomeUtils.getBiome(blockAccess, blockPos);
        if (!(biome != BiomeUtils.SWAMP && biome != BiomeUtils.SWAMP_HILLS || Config.isSwampColors())) {
            biome = BiomeUtils.PLAINS;
        }
        return biome;
    }

    private static CustomColormap getBlockColormap(K_4074_S blockState) {
        if (blockColormaps == null) {
            return null;
        }
        if (!(blockState instanceof K_4074_S)) {
            return null;
        }
        K_4074_S blockstate = blockState;
        int i = blockState.multiplayerClientSuggestionProvider();
        if (i >= 0 && i < blockColormaps.length) {
            CustomColormap[] acustomcolormap = blockColormaps[i];
            if (acustomcolormap == null) {
                return null;
            }
            for (int j = 0; j < acustomcolormap.length; ++j) {
                CustomColormap customcolormap = acustomcolormap[j];
                if (!customcolormap.matchesBlock(blockstate)) continue;
                return customcolormap;
            }
            return null;
        }
        return null;
    }

    private static int getSmoothColorMultiplier(K_4074_S blockState, BlockAndTintGetter blockAccess, c_1514_x blockPos, IColorizer colorizer, BlockPosM blockPosM) {
        int i = 0;
        int j = 0;
        int k = 0;
        int l = blockPos.getX();
        int i1 = blockPos.getY();
        int j1 = blockPos.getZ();
        BlockPosM blockposm = blockPosM;
        int k1 = Config.getBiomeBlendRadius();
        int l1 = k1 * 2 + 1;
        int i2 = l1 * l1;
        for (int j2 = l - k1; j2 <= l + k1; ++j2) {
            for (int k2 = j1 - k1; k2 <= j1 + k1; ++k2) {
                blockposm.setXyz(j2, i1, k2);
                int l2 = colorizer.getColor(blockState, blockAccess, blockposm);
                i += l2 >> 16 & 0xFF;
                j += l2 >> 8 & 0xFF;
                k += l2 & 0xFF;
            }
        }
        int i3 = i / i2;
        int j3 = j / i2;
        int k3 = k / i2;
        return i3 << 16 | j3 << 8 | k3;
    }

    public static int getFluidColor(BlockAndTintGetter blockAccess, K_4074_S blockState, c_1514_x blockPos, RenderEnv renderEnv) {
        T_2915_h block = blockState.J_1907_R();
        IColorizer customcolors$icolorizer = CustomColors.getBlockColormap(blockState);
        if (customcolors$icolorizer == null && blockState.R_4764_Y() == Material.s_956_w) {
            customcolors$icolorizer = COLORIZER_WATER;
        }
        if (customcolors$icolorizer == null) {
            return CustomColors.getBlockColors().n_1700_B(blockState, blockAccess, blockPos, 0);
        }
        return Config.isSmoothBiomes() && !customcolors$icolorizer.isColorConstant() ? CustomColors.getSmoothColorMultiplier(blockState, blockAccess, blockPos, customcolors$icolorizer, renderEnv.getColorizerBlockPosM()) : customcolors$icolorizer.getColor(blockState, blockAccess, blockPos);
    }

    public static k_4467_X getBlockColors() {
        return MinecraftClient.A_4115_X().j_276_v();
    }

    public static void updatePortalFX(c_3457_g fx) {
        if (particlePortalColor >= 0) {
            int i = particlePortalColor;
            int j = i >> 16 & 0xFF;
            int k = i >> 8 & 0xFF;
            int l = i & 0xFF;
            float f = (float)j / 255.0f;
            float f1 = (float)k / 255.0f;
            float f2 = (float)l / 255.0f;
            fx.n_1700_B(f, f1, f2);
        }
    }

    public static void updateMyceliumFX(c_3457_g fx) {
        if (myceliumParticleColors != null) {
            int i = myceliumParticleColors.getColorRandom();
            int j = i >> 16 & 0xFF;
            int k = i >> 8 & 0xFF;
            int l = i & 0xFF;
            float f = (float)j / 255.0f;
            float f1 = (float)k / 255.0f;
            float f2 = (float)l / 255.0f;
            fx.n_1700_B(f, f1, f2);
        }
    }

    private static int getRedstoneColor(K_4074_S blockState) {
        if (redstoneColors == null) {
            return -1;
        }
        int i = CustomColors.getRedstoneLevel(blockState, 15);
        return redstoneColors.getColor(i);
    }

    public static void updateReddustFX(c_3457_g fx, BlockAndTintGetter blockAccess, double x, double y, double z) {
        if (redstoneColors != null) {
            K_4074_S blockstate = blockAccess.getBlockState(new c_1514_x(x, y, z));
            int i = CustomColors.getRedstoneLevel(blockstate, 15);
            int j = redstoneColors.getColor(i);
            int k = j >> 16 & 0xFF;
            int l = j >> 8 & 0xFF;
            int i1 = j & 0xFF;
            float f = (float)k / 255.0f;
            float f1 = (float)l / 255.0f;
            float f2 = (float)i1 / 255.0f;
            fx.n_1700_B(f, f1, f2);
        }
    }

    private static int getRedstoneLevel(K_4074_S state, int def) {
        T_2915_h block = state.J_1907_R();
        if (!(block instanceof Z_4734_t)) {
            return def;
        }
        Integer object = state.R_4764_Y(Z_4734_t.t_1786_h);
        if (!(object instanceof Integer)) {
            return def;
        }
        Integer integer = object;
        return integer;
    }

    public static float getXpOrbTimer(float timer) {
        if (xpOrbTime <= 0) {
            return timer;
        }
        float f = 628.0f / (float)xpOrbTime;
        return timer * f;
    }

    public static int getXpOrbColor(float timer) {
        if (xpOrbColors == null) {
            return -1;
        }
        int i = (int)Math.round((double)((u_530_F.n_1700_B(timer) + 1.0f) * (float)(xpOrbColors.getLength() - 1)) / 2.0);
        return xpOrbColors.getColor(i);
    }

    public static int getDurabilityColor(float dur, int color) {
        if (durabilityColors == null) {
            return color;
        }
        int i = (int)(dur * (float)durabilityColors.getLength());
        return durabilityColors.getColor(i);
    }

    public static void updateWaterFX(c_3457_g fx, BlockAndTintGetter blockAccess, double x, double y, double z, RenderEnv renderEnv) {
        if (waterColors != null || blockColormaps != null || particleWaterColor >= 0) {
            c_1514_x blockpos = new c_1514_x(x, y, z);
            renderEnv.reset(BLOCK_STATE_WATER, blockpos);
            int i = CustomColors.getFluidColor(blockAccess, BLOCK_STATE_WATER, blockpos, renderEnv);
            int j = i >> 16 & 0xFF;
            int k = i >> 8 & 0xFF;
            int l = i & 0xFF;
            float f = (float)j / 255.0f;
            float f1 = (float)k / 255.0f;
            float f2 = (float)l / 255.0f;
            if (particleWaterColor >= 0) {
                int i1 = particleWaterColor >> 16 & 0xFF;
                int j1 = particleWaterColor >> 8 & 0xFF;
                int k1 = particleWaterColor & 0xFF;
                f = (float)i1 / 255.0f;
                f1 = (float)j1 / 255.0f;
                f2 = (float)k1 / 255.0f;
                f *= (float)i1 / 255.0f;
                f1 *= (float)j1 / 255.0f;
                f2 *= (float)k1 / 255.0f;
            }
            fx.n_1700_B(f, f1, f2);
        }
    }

    private static int getLilypadColorMultiplier(BlockAndTintGetter blockAccess, c_1514_x blockPos) {
        return lilyPadColor < 0 ? CustomColors.getBlockColors().n_1700_B(a_3742_W.S_4035_N.multiplayerClientSuggestionProvider(), blockAccess, blockPos, 0) : lilyPadColor;
    }

    private static e_2866_D getFogColorNether(e_2866_D col) {
        return fogColorNether == null ? col : fogColorNether;
    }

    private static e_2866_D getFogColorEnd(e_2866_D col) {
        return fogColorEnd == null ? col : fogColorEnd;
    }

    private static e_2866_D getSkyColorEnd(e_2866_D col) {
        return skyColorEnd == null ? col : skyColorEnd;
    }

    public static e_2866_D getSkyColor(e_2866_D skyColor3d, BlockAndTintGetter blockAccess, double x, double y, double z) {
        if (skyColors == null) {
            return skyColor3d;
        }
        int i = skyColors.getColorSmooth(blockAccess, x, y, z, 3);
        int j = i >> 16 & 0xFF;
        int k = i >> 8 & 0xFF;
        int l = i & 0xFF;
        float f = (float)j / 255.0f;
        float f1 = (float)k / 255.0f;
        float f2 = (float)l / 255.0f;
        float f3 = (float)skyColor3d.J_1907_R / 0.5f;
        float f4 = (float)skyColor3d.R_4764_Y / 0.66275f;
        float f5 = (float)skyColor3d.G_564_y;
        return skyColorFader.getColor(f *= f3, f1 *= f4, f2 *= f5);
    }

    private static e_2866_D getFogColor(e_2866_D fogColor3d, BlockAndTintGetter blockAccess, double x, double y, double z) {
        if (fogColors == null) {
            return fogColor3d;
        }
        int i = fogColors.getColorSmooth(blockAccess, x, y, z, 3);
        int j = i >> 16 & 0xFF;
        int k = i >> 8 & 0xFF;
        int l = i & 0xFF;
        float f = (float)j / 255.0f;
        float f1 = (float)k / 255.0f;
        float f2 = (float)l / 255.0f;
        float f3 = (float)fogColor3d.J_1907_R / 0.753f;
        float f4 = (float)fogColor3d.R_4764_Y / 0.8471f;
        float f5 = (float)fogColor3d.G_564_y;
        return fogColorFader.getColor(f *= f3, f1 *= f4, f2 *= f5);
    }

    public static e_2866_D getUnderwaterColor(BlockAndTintGetter blockAccess, double x, double y, double z) {
        return CustomColors.getUnderFluidColor(blockAccess, x, y, z, underwaterColors, underwaterColorFader);
    }

    public static e_2866_D getUnderlavaColor(BlockAndTintGetter blockAccess, double x, double y, double z) {
        return CustomColors.getUnderFluidColor(blockAccess, x, y, z, underlavaColors, underlavaColorFader);
    }

    public static e_2866_D getUnderFluidColor(BlockAndTintGetter blockAccess, double x, double y, double z, CustomColormap underFluidColors, CustomColorFader underFluidColorFader) {
        if (underFluidColors == null) {
            return null;
        }
        int i = underFluidColors.getColorSmooth(blockAccess, x, y, z, 3);
        int j = i >> 16 & 0xFF;
        int k = i >> 8 & 0xFF;
        int l = i & 0xFF;
        float f = (float)j / 255.0f;
        float f1 = (float)k / 255.0f;
        float f2 = (float)l / 255.0f;
        return underFluidColorFader.getColor(f, f1, f2);
    }

    private static int getStemColorMultiplier(K_4074_S blockState, BlockGetter blockAccess, c_1514_x blockPos, RenderEnv renderEnv) {
        CustomColormap customcolormap = stemColors;
        T_2915_h block = blockState.J_1907_R();
        if (block == a_3742_W.L_1733_J && stemPumpkinColors != null) {
            customcolormap = stemPumpkinColors;
        }
        if (block == a_3742_W.n_4539_g && stemMelonColors != null) {
            customcolormap = stemMelonColors;
        }
        if (customcolormap == null) {
            return -1;
        }
        if (!(block instanceof D_3746_J)) {
            return -1;
        }
        int i = blockState.R_4764_Y(D_3746_J.P_4830_p);
        return customcolormap.getColor(i);
    }

    public static boolean updateLightmap(k_4690_i world, float torchFlickerX, i_2518_W lmColors, boolean nightvision, float partialTicks) {
        if (world == null) {
            return false;
        }
        if (lightMapPacks == null) {
            return false;
        }
        int i = WorldUtils.getDimensionId(world);
        int j = i - lightmapMinDimensionId;
        if (j >= 0 && j < lightMapPacks.length) {
            LightMapPack lightmappack = lightMapPacks[j];
            return lightmappack == null ? false : lightmappack.updateLightmap(world, torchFlickerX, lmColors, nightvision, partialTicks);
        }
        return false;
    }

    public static e_2866_D getWorldFogColor(e_2866_D fogVec, b_4507_u world, N_4263_v renderViewEntity, float partialTicks) {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        if (WorldUtils.isNether(world)) {
            return CustomColors.getFogColorNether(fogVec);
        }
        if (WorldUtils.isOverworld(world)) {
            return CustomColors.getFogColor(fogVec, minecraft.Y_601_j, renderViewEntity.O_3598_v(), renderViewEntity.X_2960_b() + 1.0, renderViewEntity.l_2647_k());
        }
        return WorldUtils.isEnd(world) ? CustomColors.getFogColorEnd(fogVec) : fogVec;
    }

    public static e_2866_D getWorldSkyColor(e_2866_D skyVec, b_4507_u world, N_4263_v renderViewEntity, float partialTicks) {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        if (WorldUtils.isOverworld(world)) {
            return CustomColors.getSkyColor(skyVec, minecraft.Y_601_j, renderViewEntity.O_3598_v(), renderViewEntity.X_2960_b() + 1.0, renderViewEntity.l_2647_k());
        }
        return WorldUtils.isEnd(world) ? CustomColors.getSkyColorEnd(skyVec) : skyVec;
    }

    /*
     * WARNING - void declaration
     */
    private static int[] readSpawnEggColors(Properties props, String fileName, String prefix, String logName) {
        void var8_10;
        ArrayList<Integer> list = new ArrayList<Integer>();
        Set<Object> set = props.keySet();
        int i = 0;
        for (String string : set) {
            String s1 = props.getProperty(string);
            if (!string.startsWith(prefix)) continue;
            String s2 = StrUtils.removePrefix(string, prefix);
            int j = EntityUtils.getEntityIdByName(s2);
            try {
                if (j < 0) {
                    j = EntityUtils.getEntityIdByLocation(new g_2336_b(s2).toString());
                }
            }
            catch (s_3109_F resourcelocationexception) {
                Config.warn("ResourceLocationException: " + resourcelocationexception.getMessage());
            }
            if (j < 0) {
                CustomColors.warn("Invalid spawn egg name: " + string);
                continue;
            }
            int k = CustomColors.parseColor(s1);
            if (k < 0) {
                CustomColors.warn("Invalid spawn egg color: " + string + " = " + s1);
                continue;
            }
            while (list.size() <= j) {
                list.add(-1);
            }
            list.set(j, k);
            ++i;
        }
        if (i <= 0) {
            return null;
        }
        CustomColors.dbg(logName + " colors: " + i);
        int[] aint = new int[list.size()];
        boolean bl = false;
        while (var8_10 < aint.length) {
            aint[var8_10] = (Integer)list.get((int)var8_10);
            ++var8_10;
        }
        return aint;
    }

    private static int getSpawnEggColor(SpawnEggItem item, Z_1993_T itemStack, int layer, int color) {
        int[] aint;
        if (spawnEggPrimaryColors == null && spawnEggSecondaryColors == null) {
            return color;
        }
        t_5_h<?> entitytype = item.n_1700_B(itemStack.Q_4569_t());
        if (entitytype == null) {
            return color;
        }
        int i = V_3137_a.g_221_o.n_1700_B(entitytype);
        if (i < 0) {
            return color;
        }
        int[] nArray = aint = layer == 0 ? spawnEggPrimaryColors : spawnEggSecondaryColors;
        if (aint == null) {
            return color;
        }
        if (i >= 0 && i < aint.length) {
            int j = aint[i];
            return j < 0 ? color : j;
        }
        return color;
    }

    public static int getColorFromItemStack(Z_1993_T itemStack, int layer, int color) {
        if (itemStack == null) {
            return color;
        }
        q_1613_l item = itemStack.J_1907_R();
        if (item == null) {
            return color;
        }
        return item instanceof SpawnEggItem ? CustomColors.getSpawnEggColor((SpawnEggItem)item, itemStack, layer, color) : color;
    }

    private static float[][] readDyeColors(Properties props, String fileName, String prefix, String logName) {
        e_933_M[] adyecolor = e_933_M.values();
        HashMap<String, e_933_M> map = new HashMap<String, e_933_M>();
        for (int i = 0; i < adyecolor.length; ++i) {
            e_933_M dyecolor = adyecolor[i];
            map.put(dyecolor.n_1700_B(), dyecolor);
        }
        map.put("lightBlue", e_933_M.G_564_y);
        map.put("silver", e_933_M.t_148_a);
        float[][] afloat1 = new float[adyecolor.length][];
        int k = 0;
        for (String string : props.keySet()) {
            String s1 = props.getProperty(string);
            if (!string.startsWith(prefix)) continue;
            String s2 = StrUtils.removePrefix(string, prefix);
            e_933_M dyecolor1 = (e_933_M)map.get(s2);
            int j = CustomColors.parseColor(s1);
            if (dyecolor1 != null && j >= 0) {
                float[] afloat = new float[]{(float)(j >> 16 & 0xFF) / 255.0f, (float)(j >> 8 & 0xFF) / 255.0f, (float)(j & 0xFF) / 255.0f};
                afloat1[dyecolor1.ordinal()] = afloat;
                ++k;
                continue;
            }
            CustomColors.warn("Invalid color: " + string + " = " + s1);
        }
        if (k <= 0) {
            return null;
        }
        CustomColors.dbg(logName + " colors: " + k);
        return afloat1;
    }

    private static float[] getDyeColors(e_933_M dye, float[][] dyeColors, float[] colors) {
        if (dyeColors == null) {
            return colors;
        }
        if (dye == null) {
            return colors;
        }
        float[] afloat = dyeColors[dye.ordinal()];
        return afloat == null ? colors : afloat;
    }

    public static float[] getWolfCollarColors(e_933_M dye, float[] colors) {
        return CustomColors.getDyeColors(dye, wolfCollarColors, colors);
    }

    public static float[] getSheepColors(e_933_M dye, float[] colors) {
        return CustomColors.getDyeColors(dye, sheepColors, colors);
    }

    private static int[] readTextColors(Properties props, String fileName, String prefix, String logName) {
        int[] aint = new int[32];
        Arrays.fill(aint, -1);
        int i = 0;
        for (String string : props.keySet()) {
            String s1 = props.getProperty(string);
            if (!string.startsWith(prefix)) continue;
            String s2 = StrUtils.removePrefix(string, prefix);
            int j = Config.parseInt(s2, -1);
            int k = CustomColors.parseColor(s1);
            if (j >= 0 && j < aint.length && k >= 0) {
                aint[j] = k;
                ++i;
                continue;
            }
            CustomColors.warn("Invalid color: " + string + " = " + s1);
        }
        if (i <= 0) {
            return null;
        }
        CustomColors.dbg(logName + " colors: " + i);
        return aint;
    }

    public static int getTextColor(int index, int color) {
        if (textColors == null) {
            return color;
        }
        if (index >= 0 && index < textColors.length) {
            int i = textColors[index];
            return i < 0 ? color : i;
        }
        return color;
    }

    private static int[] readMapColors(Properties props, String fileName, String prefix, String logName) {
        int[] aint = new int[MaterialColor.n_1700_B.length];
        Arrays.fill(aint, -1);
        int i = 0;
        for (String string : props.keySet()) {
            String s1 = props.getProperty(string);
            if (!string.startsWith(prefix)) continue;
            String s2 = StrUtils.removePrefix(string, prefix);
            int j = CustomColors.getMapColorIndex(s2);
            int k = CustomColors.parseColor(s1);
            if (j >= 0 && j < aint.length && k >= 0) {
                aint[j] = k;
                ++i;
                continue;
            }
            CustomColors.warn("Invalid color: " + string + " = " + s1);
        }
        if (i <= 0) {
            return null;
        }
        CustomColors.dbg(logName + " colors: " + i);
        return aint;
    }

    private static int[] readPotionColors(Properties props, String fileName, String prefix, String logName) {
        int[] aint = new int[CustomColors.getMaxPotionId()];
        Arrays.fill(aint, -1);
        int i = 0;
        for (String string : props.keySet()) {
            String s1 = props.getProperty(string);
            if (!string.startsWith(prefix)) continue;
            int j = CustomColors.getPotionId(string);
            int k = CustomColors.parseColor(s1);
            if (j >= 0 && j < aint.length && k >= 0) {
                aint[j] = k;
                ++i;
                continue;
            }
            CustomColors.warn("Invalid color: " + string + " = " + s1);
        }
        if (i <= 0) {
            return null;
        }
        CustomColors.dbg(logName + " colors: " + i);
        return aint;
    }

    private static int getMaxPotionId() {
        int i = 0;
        for (g_2336_b resourcelocation : V_3137_a.T_2506_i.G_564_y()) {
            g_422_i effect = PotionUtils.getPotion(resourcelocation);
            int j = g_422_i.n_1700_B(effect);
            if (j <= i) continue;
            i = j;
        }
        return i;
    }

    private static int getPotionId(String name) {
        if (name.equals("potion.water")) {
            return 0;
        }
        name = StrUtils.replacePrefix(name, "potion.", "effect.");
        String s = StrUtils.replacePrefix(name, "effect.", "effect.minecraft.");
        for (g_2336_b resourcelocation : V_3137_a.T_2506_i.G_564_y()) {
            g_422_i effect = PotionUtils.getPotion(resourcelocation);
            if (effect.R_4764_Y().equals(name)) {
                return g_422_i.n_1700_B(effect);
            }
            if (!effect.R_4764_Y().equals(s)) continue;
            return g_422_i.n_1700_B(effect);
        }
        return -1;
    }

    public static int getPotionColor(g_422_i potion, int color) {
        int i = 0;
        if (potion != null) {
            i = g_422_i.n_1700_B(potion);
        }
        return CustomColors.getPotionColor(i, color);
    }

    public static int getPotionColor(int potionId, int color) {
        if (potionColors == null) {
            return color;
        }
        if (potionId >= 0 && potionId < potionColors.length) {
            int i = potionColors[potionId];
            return i < 0 ? color : i;
        }
        return color;
    }

    private static int getMapColorIndex(String name) {
        if (name == null) {
            return -1;
        }
        if (name.equals("air")) {
            return MaterialColor.J_1907_R.Ping;
        }
        if (name.equals("grass")) {
            return MaterialColor.R_4764_Y.Ping;
        }
        if (name.equals("sand")) {
            return MaterialColor.G_564_y.Ping;
        }
        if (name.equals("cloth")) {
            return MaterialColor.P_1922_E.Ping;
        }
        if (name.equals("tnt")) {
            return MaterialColor.u_1723_Y.Ping;
        }
        if (name.equals("ice")) {
            return MaterialColor.v_4262_N.Ping;
        }
        if (name.equals("iron")) {
            return MaterialColor.w_1484_f.Ping;
        }
        if (name.equals("foliage")) {
            return MaterialColor.t_148_a.Ping;
        }
        if (name.equals("clay")) {
            return MaterialColor.u_2550_I.Ping;
        }
        if (name.equals("dirt")) {
            return MaterialColor.M_588_G.Ping;
        }
        if (name.equals("stone")) {
            return MaterialColor.P_4830_p.Ping;
        }
        if (name.equals("water")) {
            return MaterialColor.h_1847_R.Ping;
        }
        if (name.equals("wood")) {
            return MaterialColor.Q_4569_t.Ping;
        }
        if (name.equals("quartz")) {
            return MaterialColor.M_182_A.Ping;
        }
        if (name.equals("gold")) {
            return MaterialColor.x_607_J.Ping;
        }
        if (name.equals("diamond")) {
            return MaterialColor.e_4240_b.Ping;
        }
        if (name.equals("lapis")) {
            return MaterialColor.n_3318_d.Ping;
        }
        if (name.equals("emerald")) {
            return MaterialColor.d_2427_y.Ping;
        }
        if (name.equals("obsidian")) {
            return MaterialColor.z_1737_N.Ping;
        }
        if (name.equals("netherrack")) {
            return MaterialColor.v_4276_D.Ping;
        }
        if (!name.equals("snow") && !name.equals("white")) {
            if (!name.equals("adobe") && !name.equals("orange")) {
                if (name.equals("magenta")) {
                    return MaterialColor.multiplayerClientSuggestionProvider.Ping;
                }
                if (!name.equals("light_blue") && !name.equals("lightBlue")) {
                    if (name.equals("yellow")) {
                        return MaterialColor.Y_601_j.Ping;
                    }
                    if (name.equals("lime")) {
                        return MaterialColor.Y_259_p.Ping;
                    }
                    if (name.equals("pink")) {
                        return MaterialColor.Q_2552_b.Ping;
                    }
                    if (name.equals("gray")) {
                        return MaterialColor.C_2741_M.Ping;
                    }
                    if (!name.equals("silver") && !name.equals("light_gray")) {
                        if (name.equals("cyan")) {
                            return MaterialColor.q_2307_F.Ping;
                        }
                        if (name.equals("purple")) {
                            return MaterialColor.Z_875_P.Ping;
                        }
                        if (name.equals("blue")) {
                            return MaterialColor.c_3005_b.Ping;
                        }
                        if (name.equals("brown")) {
                            return MaterialColor.H_2857_Y.Ping;
                        }
                        if (name.equals("green")) {
                            return MaterialColor.A_4115_X.Ping;
                        }
                        if (name.equals("red")) {
                            return MaterialColor.Y_1740_V.Ping;
                        }
                        if (name.equals("black")) {
                            return MaterialColor.t_4043_B.Ping;
                        }
                        if (name.equals("white_terracotta")) {
                            return MaterialColor.d_2461_k.Ping;
                        }
                        if (name.equals("orange_terracotta")) {
                            return MaterialColor.G_624_v.Ping;
                        }
                        if (name.equals("magenta_terracotta")) {
                            return MaterialColor.T_2506_i.Ping;
                        }
                        if (name.equals("light_blue_terracotta")) {
                            return MaterialColor.q_4610_l.Ping;
                        }
                        if (name.equals("yellow_terracotta")) {
                            return MaterialColor.z_4693_k.Ping;
                        }
                        if (name.equals("lime_terracotta")) {
                            return MaterialColor.g_221_o.Ping;
                        }
                        if (name.equals("pink_terracotta")) {
                            return MaterialColor.e_2887_G.Ping;
                        }
                        if (name.equals("gray_terracotta")) {
                            return MaterialColor.B_1668_F.Ping;
                        }
                        if (name.equals("light_gray_terracotta")) {
                            return MaterialColor.g_164_R.Ping;
                        }
                        if (name.equals("cyan_terracotta")) {
                            return MaterialColor.X_933_l.Ping;
                        }
                        if (name.equals("purple_terracotta")) {
                            return MaterialColor.Z_976_R.Ping;
                        }
                        if (name.equals("blue_terracotta")) {
                            return MaterialColor.H_1990_U.Ping;
                        }
                        if (name.equals("brown_terracotta")) {
                            return MaterialColor.N_2525_X.Ping;
                        }
                        if (name.equals("green_terracotta")) {
                            return MaterialColor.c_4037_x.Ping;
                        }
                        if (name.equals("red_terracotta")) {
                            return MaterialColor.g_2268_R.Ping;
                        }
                        if (name.equals("black_terracotta")) {
                            return MaterialColor.T_3594_S.Ping;
                        }
                        if (name.equals("crimson_nylium")) {
                            return MaterialColor.D_4792_h.Ping;
                        }
                        if (name.equals("crimson_stem")) {
                            return MaterialColor.s_2632_s.Ping;
                        }
                        if (name.equals("crimson_hyphae")) {
                            return MaterialColor.l_1233_K.Ping;
                        }
                        if (name.equals("warped_nylium")) {
                            return MaterialColor.z_1333_t.Ping;
                        }
                        if (name.equals("warped_stem")) {
                            return MaterialColor.O_508_d.Ping;
                        }
                        if (name.equals("warped_hyphae")) {
                            return MaterialColor.r_715_M.Ping;
                        }
                        return name.equals("warped_wart_block") ? MaterialColor.A_1038_p.Ping : -1;
                    }
                    return MaterialColor.k_2293_S.Ping;
                }
                return MaterialColor.w_1457_N.Ping;
            }
            return MaterialColor.t_1786_h.Ping;
        }
        return MaterialColor.s_956_w.Ping;
    }

    private static int[] getMapColors() {
        MaterialColor[] amaterialcolor = MaterialColor.n_1700_B;
        int[] aint = new int[amaterialcolor.length];
        Arrays.fill(aint, -1);
        for (int i = 0; i < amaterialcolor.length && i < aint.length; ++i) {
            MaterialColor materialcolor = amaterialcolor[i];
            if (materialcolor == null) continue;
            aint[i] = materialcolor.i_1637_u;
        }
        return aint;
    }

    private static void setMapColors(int[] colors) {
        if (colors != null) {
            MaterialColor[] amaterialcolor = MaterialColor.n_1700_B;
            for (int i = 0; i < amaterialcolor.length && i < colors.length; ++i) {
                int j;
                MaterialColor materialcolor = amaterialcolor[i];
                if (materialcolor == null || (j = colors[i]) < 0 || materialcolor.i_1637_u == j) continue;
                materialcolor.i_1637_u = j;
            }
        }
    }

    private static float[][] getDyeColors() {
        e_933_M[] adyecolor = e_933_M.values();
        float[][] afloat = new float[adyecolor.length][];
        for (int i = 0; i < adyecolor.length && i < afloat.length; ++i) {
            e_933_M dyecolor = adyecolor[i];
            if (dyecolor == null) continue;
            afloat[i] = dyecolor.G_564_y();
        }
        return afloat;
    }

    private static void setDyeColors(float[][] colors) {
        if (colors != null) {
            e_933_M[] adyecolor = e_933_M.values();
            for (int i = 0; i < adyecolor.length && i < colors.length; ++i) {
                float[] afloat;
                e_933_M dyecolor = adyecolor[i];
                if (dyecolor == null || (afloat = colors[i]) == null || dyecolor.G_564_y().equals(afloat)) continue;
                dyecolor.n_1700_B(afloat);
            }
        }
    }

    private static void dbg(String str) {
        Config.dbg("CustomColors: " + str);
    }

    private static void warn(String str) {
        Config.warn("CustomColors: " + str);
    }

    public static int getExpBarTextColor(int color) {
        return expBarTextColor < 0 ? color : expBarTextColor;
    }

    public static int getBossTextColor(int color) {
        return bossTextColor < 0 ? color : bossTextColor;
    }

    public static int getSignTextColor(int color) {
        if (color != 0) {
            return color;
        }
        return signTextColor < 0 ? color : signTextColor;
    }

    public static interface IColorizer {
        public int getColor(K_4074_S var1, BlockAndTintGetter var2, c_1514_x var3);

        public boolean isColorConstant();
    }
}



