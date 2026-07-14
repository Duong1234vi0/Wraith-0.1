/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.optifine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import lightning.product.B_1814_Y;
import lightning.product.B_3871_I;
import lightning.product.C_3240_x;
import lightning.product.BlockElement;
import lightning.product.F_3565_Q;
import lightning.product.BlockElementFace;
import lightning.product.L_1434_v;
import lightning.product.L_3848_p;
import lightning.product.L_4237_Q;
import lightning.product.L_972_x;
import lightning.product.R_2515_i;
import lightning.product.S_3779_r;
import lightning.product.S_3826_o;
import lightning.product.T_2910_P;
import lightning.product.V_3137_a;
import lightning.product.X_933_l;
import lightning.product.b_257_Y;
import lightning.product.MinecraftClient;
import lightning.product.c_4477_a;
import lightning.product.c_932_S;
import lightning.product.d_1062_x;
import lightning.product.e_1174_E;
import lightning.product.g_2336_b;
import lightning.product.g_2561_p;
import lightning.product.ModelManager;
import lightning.product.o_3047_I;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.SimpleBakedModel;
import net.optifine.Config;
import net.optifine.config.IParserInt;
import net.optifine.config.NbtTagValue;
import net.optifine.config.ParserEnchantmentId;
import net.optifine.config.RangeInt;
import net.optifine.config.RangeListInt;
import net.optifine.render.Blender;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.lwjgl.opengl.GL11;

public class CustomItemProperties {
    public String name = null;
    public String basePath = null;
    public int type = 1;
    public int[] items = null;
    public String texture = null;
    public Map<String, String> mapTextures = null;
    public String model = null;
    public Map<String, String> mapModels = null;
    public RangeListInt damage = null;
    public boolean damagePercent = false;
    public int damageMask = 0;
    public RangeListInt stackSize = null;
    public int[] enchantmentIds = null;
    public RangeListInt enchantmentLevels = null;
    public NbtTagValue[] nbtTagValues = null;
    public int hand = 0;
    public int blend = 1;
    public float speed = 0.0f;
    public float rotation = 0.0f;
    public int layer = 0;
    public float duration = 1.0f;
    public int weight = 0;
    public g_2336_b textureLocation = null;
    public Map mapTextureLocations = null;
    public B_3871_I sprite = null;
    public Map mapSprites = null;
    public S_3826_o bakedModelTexture = null;
    public Map<String, S_3826_o> mapBakedModelsTexture = null;
    public S_3826_o bakedModelFull = null;
    public Map<String, S_3826_o> mapBakedModelsFull = null;
    private int textureWidth = 0;
    private int textureHeight = 0;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_ENCHANTMENT = 2;
    public static final int TYPE_ARMOR = 3;
    public static final int TYPE_ELYTRA = 4;
    public static final int HAND_ANY = 0;
    public static final int HAND_MAIN = 1;
    public static final int HAND_OFF = 2;
    public static final String INVENTORY = "inventory";

    public CustomItemProperties(Properties props, String path) {
        this.name = CustomItemProperties.parseName(path);
        this.basePath = CustomItemProperties.parseBasePath(path);
        this.type = this.parseType(props.getProperty("type"));
        this.items = this.parseItems(props.getProperty("items"), props.getProperty("matchItems"));
        this.mapModels = CustomItemProperties.parseModels(props, this.basePath);
        this.model = CustomItemProperties.parseModel(props.getProperty("model"), path, this.basePath, this.type, this.mapModels);
        this.mapTextures = CustomItemProperties.parseTextures(props, this.basePath);
        boolean flag = this.mapModels == null && this.model == null;
        this.texture = CustomItemProperties.parseTexture(props.getProperty("texture"), props.getProperty("tile"), props.getProperty("source"), path, this.basePath, this.type, this.mapTextures, flag);
        String s = props.getProperty("damage");
        if (s != null) {
            this.damagePercent = s.contains("%");
            s = s.replace("%", "");
            this.damage = this.parseRangeListInt(s);
            this.damageMask = this.parseInt(props.getProperty("damageMask"), 0);
        }
        this.stackSize = this.parseRangeListInt(props.getProperty("stackSize"));
        this.enchantmentIds = this.parseInts(CustomItemProperties.getProperty(props, "enchantmentIDs", "enchantments"), new ParserEnchantmentId());
        this.enchantmentLevels = this.parseRangeListInt(props.getProperty("enchantmentLevels"));
        this.nbtTagValues = this.parseNbtTagValues(props);
        this.hand = this.parseHand(props.getProperty("hand"));
        this.blend = Blender.parseBlend(props.getProperty("blend"));
        this.speed = this.parseFloat(props.getProperty("speed"), 0.0f);
        this.rotation = this.parseFloat(props.getProperty("rotation"), 0.0f);
        this.layer = this.parseInt(props.getProperty("layer"), 0);
        this.weight = this.parseInt(props.getProperty("weight"), 0);
        this.duration = this.parseFloat(props.getProperty("duration"), 1.0f);
    }

    private static String getProperty(Properties props, String ... names) {
        for (int i = 0; i < names.length; ++i) {
            String s = names[i];
            String s1 = props.getProperty(s);
            if (s1 == null) continue;
            return s1;
        }
        return null;
    }

    private static String parseName(String path) {
        int j;
        String s = path;
        int i = path.lastIndexOf(47);
        if (i >= 0) {
            s = path.substring(i + 1);
        }
        if ((j = s.lastIndexOf(46)) >= 0) {
            s = s.substring(0, j);
        }
        return s;
    }

    private static String parseBasePath(String path) {
        int i = path.lastIndexOf(47);
        return i < 0 ? "" : path.substring(0, i);
    }

    private int parseType(String str) {
        if (str == null) {
            return 1;
        }
        if (str.equals("item")) {
            return 1;
        }
        if (str.equals("enchantment")) {
            return 2;
        }
        if (str.equals("armor")) {
            return 3;
        }
        if (str.equals("elytra")) {
            return 4;
        }
        Config.warn("Unknown method: " + str);
        return 0;
    }

    private int[] parseItems(String str, String str2) {
        if (str == null) {
            str = str2;
        }
        if (str == null) {
            return null;
        }
        str = str.trim();
        TreeSet<Integer> set = new TreeSet<Integer>();
        String[] astring = Config.tokenize(str, " ");
        for (int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            q_1613_l item = this.getItemByName(s);
            if (item == null) {
                Config.warn("Item not found: " + s);
                continue;
            }
            int j = q_1613_l.n_1700_B(item);
            if (j < 0) {
                Config.warn("Item ID not found: " + s);
                continue;
            }
            set.add(new Integer(j));
        }
        Integer[] ainteger = set.toArray(new Integer[set.size()]);
        int[] aint = new int[ainteger.length];
        for (int k = 0; k < aint.length; ++k) {
            aint[k] = ainteger[k];
        }
        return aint;
    }

    private q_1613_l getItemByName(String name) {
        g_2336_b resourcelocation = new g_2336_b(name);
        return !V_3137_a.e_2887_G.R_4764_Y(resourcelocation) ? null : V_3137_a.e_2887_G.n_1700_B(resourcelocation);
    }

    private static String parseTexture(String texStr, String texStr2, String texStr3, String path, String basePath, int type, Map<String, String> mapTexs, boolean textureFromPath) {
        int j;
        String s;
        if (texStr == null) {
            texStr = texStr2;
        }
        if (texStr == null) {
            texStr = texStr3;
        }
        if (texStr != null) {
            String s2 = ".png";
            if (texStr.endsWith(s2)) {
                texStr = texStr.substring(0, texStr.length() - s2.length());
            }
            return CustomItemProperties.fixTextureName(texStr, basePath);
        }
        if (type == 3) {
            return null;
        }
        if (mapTexs != null && (s = mapTexs.get("texture.bow_standby")) != null) {
            return s;
        }
        if (!textureFromPath) {
            return null;
        }
        String s1 = path;
        int i = path.lastIndexOf(47);
        if (i >= 0) {
            s1 = path.substring(i + 1);
        }
        if ((j = s1.lastIndexOf(46)) >= 0) {
            s1 = s1.substring(0, j);
        }
        return CustomItemProperties.fixTextureName(s1, basePath);
    }

    private static Map parseTextures(Properties props, String basePath) {
        String s = "texture.";
        Map map = CustomItemProperties.getMatchingProperties(props, s);
        if (map.size() <= 0) {
            return null;
        }
        Set set = map.keySet();
        LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();
        for (String key : set) {
            String s2 = (String)map.get(key);
            s2 = CustomItemProperties.fixTextureName(s2, basePath);
            map1.put(key, s2);
        }
        return map1;
    }

    private static String fixTextureName(String iconName, String basePath) {
        if (!(((String)(iconName = TextureUtils.fixResourcePath((String)iconName, basePath))).startsWith(basePath) || ((String)iconName).startsWith("textures/") || ((String)iconName).startsWith("optifine/"))) {
            iconName = basePath + "/" + (String)iconName;
        }
        if (((String)iconName).endsWith(".png")) {
            iconName = ((String)iconName).substring(0, ((String)iconName).length() - 4);
        }
        if (((String)iconName).startsWith("/")) {
            iconName = ((String)iconName).substring(1);
        }
        return iconName;
    }

    private static String parseModel(String modelStr, String path, String basePath, int type, Map<String, String> mapModelNames) {
        String s;
        if (modelStr != null) {
            String s1 = ".json";
            if (modelStr.endsWith(s1)) {
                modelStr = modelStr.substring(0, modelStr.length() - s1.length());
            }
            return CustomItemProperties.fixModelName(modelStr, basePath);
        }
        if (type == 3) {
            return null;
        }
        if (mapModelNames != null && (s = mapModelNames.get("model.bow_standby")) != null) {
            return s;
        }
        return modelStr;
    }

    private static Map parseModels(Properties props, String basePath) {
        String s = "model.";
        Map map = CustomItemProperties.getMatchingProperties(props, s);
        if (map.size() <= 0) {
            return null;
        }
        Set set = map.keySet();
        LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();
        for (String s1 : set) {
            String s2 = (String)map.get(s1);
            s2 = CustomItemProperties.fixModelName(s2, basePath);
            map1.put(s1, s2);
        }
        return map1;
    }

    private static String fixModelName(String modelName, String basePath) {
        String s;
        boolean flag;
        boolean bl = flag = ((String)(modelName = TextureUtils.fixResourcePath((String)modelName, basePath))).startsWith("block/") || ((String)modelName).startsWith("item/");
        if (!(((String)modelName).startsWith(basePath) || flag || ((String)modelName).startsWith("optifine/"))) {
            modelName = basePath + "/" + (String)modelName;
        }
        if (((String)modelName).endsWith(s = ".json")) {
            modelName = ((String)modelName).substring(0, ((String)modelName).length() - s.length());
        }
        if (((String)modelName).startsWith("/")) {
            modelName = ((String)modelName).substring(1);
        }
        return modelName;
    }

    private int parseInt(String str, int defVal) {
        if (str == null) {
            return defVal;
        }
        int i = Config.parseInt(str = str.trim(), Integer.MIN_VALUE);
        if (i == Integer.MIN_VALUE) {
            Config.warn("Invalid integer: " + str);
            return defVal;
        }
        return i;
    }

    private float parseFloat(String str, float defVal) {
        if (str == null) {
            return defVal;
        }
        float f = Config.parseFloat(str = str.trim(), Float.MIN_VALUE);
        if (f == Float.MIN_VALUE) {
            Config.warn("Invalid float: " + str);
            return defVal;
        }
        return f;
    }

    private int[] parseInts(String str, IParserInt parser) {
        if (str == null) {
            return null;
        }
        String[] astring = Config.tokenize(str, " ");
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            int j = parser.parse(s, Integer.MIN_VALUE);
            if (j == Integer.MIN_VALUE) {
                Config.warn("Invalid value: " + s);
                continue;
            }
            list.add(j);
        }
        Integer[] ainteger = list.toArray(new Integer[list.size()]);
        return Config.toPrimitive(ainteger);
    }

    private RangeListInt parseRangeListInt(String str) {
        if (str == null) {
            return null;
        }
        String[] astring = Config.tokenize(str, " ");
        RangeListInt rangelistint = new RangeListInt();
        for (int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            RangeInt rangeint = this.parseRangeInt(s);
            if (rangeint == null) {
                Config.warn("Invalid range list: " + str);
                return null;
            }
            rangelistint.addRange(rangeint);
        }
        return rangelistint;
    }

    private RangeInt parseRangeInt(String str) {
        if (str == null) {
            return null;
        }
        int i = (str = str.trim()).length() - str.replace("-", "").length();
        if (i > 1) {
            Config.warn("Invalid range: " + str);
            return null;
        }
        String[] astring = Config.tokenize(str, "- ");
        int[] aint = new int[astring.length];
        for (int j = 0; j < astring.length; ++j) {
            String s = astring[j];
            int k = Config.parseInt(s, -1);
            if (k < 0) {
                Config.warn("Invalid range: " + str);
                return null;
            }
            aint[j] = k;
        }
        if (aint.length == 1) {
            int i1 = aint[0];
            if (str.startsWith("-")) {
                return new RangeInt(0, i1);
            }
            return str.endsWith("-") ? new RangeInt(i1, 65535) : new RangeInt(i1, i1);
        }
        if (aint.length == 2) {
            int l = Math.min(aint[0], aint[1]);
            int j1 = Math.max(aint[0], aint[1]);
            return new RangeInt(l, j1);
        }
        Config.warn("Invalid range: " + str);
        return null;
    }

    private NbtTagValue[] parseNbtTagValues(Properties props) {
        String s = "nbt.";
        Map map = CustomItemProperties.getMatchingProperties(props, s);
        if (map.size() <= 0) {
            return null;
        }
        ArrayList<NbtTagValue> list = new ArrayList<NbtTagValue>();
        for (String s1 : map.keySet()) {
            String s2 = (String)map.get(s1);
            String s3 = s1.substring(s.length());
            NbtTagValue nbttagvalue = new NbtTagValue(s3, s2);
            list.add(nbttagvalue);
        }
        NbtTagValue[] anbttagvalue = list.toArray(new NbtTagValue[list.size()]);
        return anbttagvalue;
    }

    private static Map getMatchingProperties(Properties props, String keyPrefix) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        for (String string : props.keySet()) {
            String s1 = props.getProperty(string);
            if (!string.startsWith(keyPrefix)) continue;
            map.put(string, s1);
        }
        return map;
    }

    private int parseHand(String str) {
        if (str == null) {
            return 0;
        }
        if ((str = str.toLowerCase()).equals("any")) {
            return 0;
        }
        if (str.equals("main")) {
            return 1;
        }
        if (str.equals("off")) {
            return 2;
        }
        Config.warn("Invalid hand: " + str);
        return 0;
    }

    public boolean isValid(String path) {
        if (this.name != null && this.name.length() > 0) {
            if (this.basePath == null) {
                Config.warn("No base path found: " + path);
                return false;
            }
            if (this.type == 0) {
                Config.warn("No type defined: " + path);
                return false;
            }
            if (this.type == 4 && this.items == null) {
                this.items = new int[]{q_1613_l.n_1700_B(Items.NyliumBlock)};
            }
            if (this.type == 1 || this.type == 3 || this.type == 4) {
                if (this.items == null) {
                    this.items = this.detectItems();
                }
                if (this.items == null) {
                    Config.warn("No items defined: " + path);
                    return false;
                }
            }
            if (this.texture == null && this.mapTextures == null && this.model == null && this.mapModels == null) {
                Config.warn("No texture or model specified: " + path);
                return false;
            }
            if (this.type == 2 && this.enchantmentIds == null) {
                Config.warn("No enchantmentIDs specified: " + path);
                return false;
            }
            return true;
        }
        Config.warn("No name found: " + path);
        return false;
    }

    private int[] detectItems() {
        int[] nArray;
        q_1613_l item = this.getItemByName(this.name);
        if (item == null) {
            return null;
        }
        int i = q_1613_l.n_1700_B(item);
        if (i < 0) {
            nArray = null;
        } else {
            int[] nArray2 = new int[1];
            nArray = nArray2;
            nArray2[0] = i;
        }
        return nArray;
    }

    public void updateIcons(L_3848_p textureMap) {
        if (this.texture != null) {
            this.textureLocation = this.getTextureLocation(this.texture);
            if (this.type == 1) {
                g_2336_b resourcelocation = this.getSpriteLocation(this.textureLocation);
                this.sprite = textureMap.P_1922_E(resourcelocation);
            }
        }
        if (this.mapTextures != null) {
            this.mapTextureLocations = new HashMap();
            this.mapSprites = new HashMap();
            for (String s : this.mapTextures.keySet()) {
                String s1 = this.mapTextures.get(s);
                g_2336_b resourcelocation1 = this.getTextureLocation(s1);
                this.mapTextureLocations.put(s, resourcelocation1);
                if (this.type != 1) continue;
                g_2336_b resourcelocation2 = this.getSpriteLocation(resourcelocation1);
                B_3871_I textureatlassprite = textureMap.P_1922_E(resourcelocation2);
                this.mapSprites.put(s, textureatlassprite);
            }
        }
    }

    public void refreshIcons(L_3848_p textureMap) {
        if (this.sprite != null) {
            this.sprite = textureMap.J_1907_R(this.sprite.s_956_w());
        }
        if (this.mapSprites != null) {
            for (String s : this.mapSprites.keySet()) {
                B_3871_I textureatlassprite = (B_3871_I)this.mapSprites.get(s);
                if (textureatlassprite == null) continue;
                g_2336_b resourcelocation = textureatlassprite.s_956_w();
                B_3871_I textureatlassprite1 = textureMap.J_1907_R(resourcelocation);
                if (textureatlassprite1 == null || textureatlassprite1 instanceof F_3565_Q) {
                    Config.warn("Missing CIT sprite: " + String.valueOf(resourcelocation) + ", properties: " + this.basePath);
                }
                this.mapSprites.put(s, textureatlassprite1);
            }
        }
    }

    private g_2336_b getTextureLocation(String texName) {
        String s2;
        g_2336_b resourcelocation1;
        boolean flag;
        if (texName == null) {
            return null;
        }
        g_2336_b resourcelocation = new g_2336_b(texName);
        String s = resourcelocation.R_4764_Y();
        Object s1 = resourcelocation.J_1907_R();
        if (!((String)s1).contains("/")) {
            s1 = "textures/item/" + (String)s1;
        }
        if (!(flag = Config.hasResource(resourcelocation1 = new g_2336_b(s, s2 = (String)s1 + ".png")))) {
            Config.warn("File not found: " + s2);
        }
        return resourcelocation1;
    }

    private g_2336_b getSpriteLocation(g_2336_b resLoc) {
        String s = resLoc.J_1907_R();
        s = StrUtils.removePrefix(s, "textures/");
        s = StrUtils.removeSuffix(s, ".png");
        return new g_2336_b(resLoc.R_4764_Y(), s);
    }

    public void updateModelTexture(L_3848_p textureMap, B_1814_Y itemModelGenerator) {
        if (this.texture != null || this.mapTextures != null) {
            String[] astring = this.getModelTextures();
            boolean flag = this.isUseTint();
            this.bakedModelTexture = CustomItemProperties.makeBakedModel(textureMap, itemModelGenerator, astring, flag);
            if (this.type == 1 && this.mapTextures != null) {
                for (String s : this.mapTextures.keySet()) {
                    String s1 = this.mapTextures.get(s);
                    String s2 = StrUtils.removePrefix(s, "texture.");
                    if (!this.isSubTexture(s2)) continue;
                    String[] astring1 = new String[]{s1};
                    S_3826_o ibakedmodel = CustomItemProperties.makeBakedModel(textureMap, itemModelGenerator, astring1, flag);
                    if (this.mapBakedModelsTexture == null) {
                        this.mapBakedModelsTexture = new HashMap<String, S_3826_o>();
                    }
                    String s3 = "item/" + s2;
                    this.mapBakedModelsTexture.put(s3, ibakedmodel);
                }
            }
        }
    }

    private boolean isSubTexture(String path) {
        return path.startsWith("bow") || path.startsWith("crossbow") || path.startsWith("fishing_rod") || path.startsWith("shield");
    }

    private boolean isUseTint() {
        return true;
    }

    private static S_3826_o makeBakedModel(L_3848_p textureMap, B_1814_Y itemModelGenerator, String[] textures, boolean useTint) {
        String[] astring = new String[textures.length];
        for (int i = 0; i < astring.length; ++i) {
            String s = textures[i];
            astring[i] = StrUtils.removePrefix(s, "textures/");
        }
        o_3047_I blockmodel = CustomItemProperties.makeModelBlock(astring);
        o_3047_I blockmodel1 = itemModelGenerator.n_1700_B(CustomItemProperties::getSprite, blockmodel);
        return CustomItemProperties.bakeModel(textureMap, blockmodel1, useTint);
    }

    public static B_3871_I getSprite(T_2910_P material) {
        L_3848_p atlastexture = MinecraftClient.A_4115_X().h_4320_q().n_1700_B(material.n_1700_B());
        return atlastexture.J_1907_R(material.J_1907_R());
    }

    private String[] getModelTextures() {
        if (this.type == 1 && this.items.length == 1) {
            R_2515_i armoritem;
            boolean flag;
            q_1613_l item = q_1613_l.J_1907_R(this.items[0]);
            boolean bl = flag = item == Items.j_2461_G || item == Items.g_2492_v || item == Items.NetherrackBlock;
            if (flag && this.damage != null && this.damage.getCountRanges() > 0) {
                RangeInt rangeint = this.damage.getRange(0);
                int i = rangeint.getMin();
                boolean flag1 = (i & 0x4000) != 0;
                String s5 = this.getMapTexture(this.mapTextures, "texture.potion_overlay", "item/potion_overlay");
                String s6 = null;
                s6 = flag1 ? this.getMapTexture(this.mapTextures, "texture.potion_bottle_splash", "item/potion_bottle_splash") : this.getMapTexture(this.mapTextures, "texture.potion_bottle_drinkable", "item/potion_bottle_drinkable");
                return new String[]{s5, s6};
            }
            if (item instanceof R_2515_i && (armoritem = (R_2515_i)item).P_1922_E() == L_1434_v.n_1700_B) {
                String s = "leather";
                String s1 = "helmet";
                e_1174_E equipmentslottype = armoritem.R_4764_Y();
                if (equipmentslottype == e_1174_E.u_1723_Y) {
                    s1 = "helmet";
                }
                if (equipmentslottype == e_1174_E.P_1922_E) {
                    s1 = "chestplate";
                }
                if (equipmentslottype == e_1174_E.G_564_y) {
                    s1 = "leggings";
                }
                if (equipmentslottype == e_1174_E.R_4764_Y) {
                    s1 = "boots";
                }
                String s2 = s + "_" + s1;
                String s3 = this.getMapTexture(this.mapTextures, "texture." + s2, "item/" + s2);
                String s4 = this.getMapTexture(this.mapTextures, "texture." + s2 + "_overlay", "item/" + s2 + "_overlay");
                return new String[]{s3, s4};
            }
        }
        return new String[]{this.texture};
    }

    private String getMapTexture(Map<String, String> map, String key, String def) {
        if (map == null) {
            return def;
        }
        String s = map.get(key);
        return s == null ? def : s;
    }

    private static o_3047_I makeModelBlock(String[] modelTextures) {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("{\"parent\": \"builtin/generated\",\"textures\": {");
        for (int i = 0; i < modelTextures.length; ++i) {
            String s = modelTextures[i];
            if (i > 0) {
                stringbuffer.append(", ");
            }
            stringbuffer.append("\"layer" + i + "\": \"" + s + "\"");
        }
        stringbuffer.append("}}");
        String s1 = stringbuffer.toString();
        return o_3047_I.n_1700_B(s1);
    }

    private static S_3826_o bakeModel(L_3848_p textureMap, o_3047_I modelBlockIn, boolean useTint) {
        S_3779_r modelrotation = S_3779_r.n_1700_B;
        T_2910_P rendermaterial = modelBlockIn.R_4764_Y("particle");
        B_3871_I textureatlassprite = rendermaterial.R_4764_Y();
        SimpleBakedModel.n_1700_B simplebakedmodel$builder = new SimpleBakedModel.n_1700_B(modelBlockIn, L_4237_Q.n_1700_B, false).n_1700_B(textureatlassprite);
        for (BlockElement blockpart : modelBlockIn.n_1700_B()) {
            for (b_257_Y direction : blockpart.R_4764_Y.keySet()) {
                BlockElementFace blockpartface = blockpart.R_4764_Y.get(direction);
                if (!useTint) {
                    blockpartface = new BlockElementFace(blockpartface.n_1700_B, -1, blockpartface.R_4764_Y, blockpartface.G_564_y);
                }
                T_2910_P rendermaterial1 = modelBlockIn.R_4764_Y(blockpartface.R_4764_Y);
                B_3871_I textureatlassprite1 = rendermaterial1.R_4764_Y();
                c_932_S bakedquad = CustomItemProperties.makeBakedQuad(blockpart, blockpartface, textureatlassprite1, direction, modelrotation);
                if (blockpartface.n_1700_B == null) {
                    simplebakedmodel$builder.n_1700_B(bakedquad);
                    continue;
                }
                simplebakedmodel$builder.n_1700_B(b_257_Y.n_1700_B(modelrotation.n_1700_B().R_4764_Y(), blockpartface.n_1700_B), bakedquad);
            }
        }
        return simplebakedmodel$builder.n_1700_B();
    }

    private static c_932_S makeBakedQuad(BlockElement blockPart, BlockElementFace blockPartFace, B_3871_I textureAtlasSprite, b_257_Y enumFacing, S_3779_r modelRotation) {
        L_972_x facebakery = new L_972_x();
        return facebakery.n_1700_B(blockPart.n_1700_B, blockPart.J_1907_R, blockPartFace, textureAtlasSprite, enumFacing, modelRotation, blockPart.G_564_y, blockPart.P_1922_E, textureAtlasSprite.s_956_w());
    }

    public String toString() {
        return this.basePath + "/" + this.name + ", type: " + this.type + ", items: [" + Config.arrayToString(this.items) + "], textture: " + this.texture;
    }

    public float getTextureWidth(C_3240_x textureManager) {
        if (this.textureWidth <= 0) {
            if (this.textureLocation != null) {
                c_4477_a texture = textureManager.J_1907_R(this.textureLocation);
                int i = texture.getGlTextureId();
                int j = X_933_l.N_2525_X();
                X_933_l.w_1457_N(i);
                this.textureWidth = GL11.glGetTexLevelParameteri((int)3553, (int)0, (int)4096);
                X_933_l.w_1457_N(j);
            }
            if (this.textureWidth <= 0) {
                this.textureWidth = 16;
            }
        }
        return this.textureWidth;
    }

    public float getTextureHeight(C_3240_x textureManager) {
        if (this.textureHeight <= 0) {
            if (this.textureLocation != null) {
                c_4477_a texture = textureManager.J_1907_R(this.textureLocation);
                int i = texture.getGlTextureId();
                int j = X_933_l.N_2525_X();
                X_933_l.w_1457_N(i);
                this.textureHeight = GL11.glGetTexLevelParameteri((int)3553, (int)0, (int)4097);
                X_933_l.w_1457_N(j);
            }
            if (this.textureHeight <= 0) {
                this.textureHeight = 16;
            }
        }
        return this.textureHeight;
    }

    public S_3826_o getBakedModel(g_2336_b modelLocation, boolean fullModel) {
        String s;
        S_3826_o ibakedmodel1;
        Map<String, S_3826_o> map;
        S_3826_o ibakedmodel;
        if (fullModel) {
            ibakedmodel = this.bakedModelFull;
            map = this.mapBakedModelsFull;
        } else {
            ibakedmodel = this.bakedModelTexture;
            map = this.mapBakedModelsTexture;
        }
        if (modelLocation != null && map != null && (ibakedmodel1 = map.get(s = modelLocation.J_1907_R())) != null) {
            return ibakedmodel1;
        }
        return ibakedmodel;
    }

    public void loadModels(g_2561_p modelBakery) {
        if (this.model != null) {
            CustomItemProperties.loadItemModel(modelBakery, this.model);
        }
        if (this.type == 1 && this.mapModels != null) {
            for (String s : this.mapModels.keySet()) {
                String s1 = this.mapModels.get(s);
                String s2 = StrUtils.removePrefix(s, "model.");
                if (!this.isSubTexture(s2)) continue;
                CustomItemProperties.loadItemModel(modelBakery, s1);
            }
        }
    }

    public void updateModelsFull() {
        ModelManager modelmanager = Config.getModelManager();
        S_3826_o ibakedmodel = modelmanager.J_1907_R();
        if (this.model != null) {
            g_2336_b resourcelocation = CustomItemProperties.getModelLocation(this.model);
            d_1062_x modelresourcelocation = new d_1062_x(resourcelocation, INVENTORY);
            this.bakedModelFull = modelmanager.n_1700_B(modelresourcelocation);
            if (this.bakedModelFull == ibakedmodel) {
                Config.warn("Custom Items: Model not found " + modelresourcelocation.J_1907_R());
                this.bakedModelFull = null;
            }
        }
        if (this.type == 1 && this.mapModels != null) {
            for (String s : this.mapModels.keySet()) {
                String s1 = this.mapModels.get(s);
                String s2 = StrUtils.removePrefix(s, "model.");
                if (!this.isSubTexture(s2)) continue;
                g_2336_b resourcelocation1 = CustomItemProperties.getModelLocation(s1);
                d_1062_x modelresourcelocation1 = new d_1062_x(resourcelocation1, INVENTORY);
                S_3826_o ibakedmodel1 = modelmanager.n_1700_B(modelresourcelocation1);
                if (ibakedmodel1 == ibakedmodel) {
                    Config.warn("Custom Items: Model not found " + modelresourcelocation1.J_1907_R());
                    continue;
                }
                if (this.mapBakedModelsFull == null) {
                    this.mapBakedModelsFull = new HashMap<String, S_3826_o>();
                }
                String s3 = "item/" + s2;
                this.mapBakedModelsFull.put(s3, ibakedmodel1);
            }
        }
    }

    private static void loadItemModel(g_2561_p modelBakery, String model) {
        g_2336_b resourcelocation = CustomItemProperties.getModelLocation(model);
        d_1062_x modelresourcelocation = new d_1062_x(resourcelocation, INVENTORY);
        modelBakery.n_1700_B(modelresourcelocation);
    }

    private static g_2336_b getModelLocation(String modelName) {
        return new g_2336_b(modelName);
    }
}



