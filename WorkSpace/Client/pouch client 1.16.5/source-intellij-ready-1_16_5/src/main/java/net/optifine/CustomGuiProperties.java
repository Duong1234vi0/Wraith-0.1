/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import lightning.product.VillagerData;
import lightning.product.K_4074_S;
import lightning.product.L_2225_p;
import lightning.product.N_4263_v;
import lightning.product.VillagerProfession;
import lightning.product.T_1316_M;
import lightning.product.U_2534_D;
import lightning.product.BlockAndTintGetter;
import lightning.product.a_433_S;
import lightning.product.Mule;
import lightning.product.c_1514_x;
import lightning.product.Donkey;
import lightning.product.e_933_M;
import lightning.product.f_395_A;
import lightning.product.g_2336_b;
import lightning.product.g_4407_j;
import lightning.product.i_2154_H;
import lightning.product.k_2603_m;
import lightning.product.k_594_Q;
import lightning.product.l_3848_Y;
import lightning.product.DropperBlockEntity;
import lightning.product.p_1429_o;
import lightning.product.s_3081_t;
import lightning.product.Horse;
import lightning.product.t_693_s;
import lightning.product.v_3445_Z;
import lightning.product.x_282_a;
import lightning.product.x_3974_Q;
import net.optifine.Config;
import net.optifine.CustomGuis;
import net.optifine.config.BiomeId;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchProfession;
import net.optifine.config.Matches;
import net.optifine.config.NbtTagValue;
import net.optifine.config.RangeListInt;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;

public class CustomGuiProperties {
    private String fileName = null;
    private String basePath = null;
    private EnumContainer container = null;
    private Map<g_2336_b, g_2336_b> textureLocations = null;
    private NbtTagValue nbtName = null;
    private BiomeId[] biomes = null;
    private RangeListInt heights = null;
    private Boolean large = null;
    private Boolean trapped = null;
    private Boolean christmas = null;
    private Boolean ender = null;
    private RangeListInt levels = null;
    private MatchProfession[] professions = null;
    private EnumVariant[] variants = null;
    private e_933_M[] colors = null;
    private static final EnumVariant[] VARIANTS_HORSE = new EnumVariant[]{EnumVariant.HORSE, EnumVariant.DONKEY, EnumVariant.MULE, EnumVariant.LLAMA};
    private static final EnumVariant[] VARIANTS_DISPENSER = new EnumVariant[]{EnumVariant.DISPENSER, EnumVariant.DROPPER};
    private static final EnumVariant[] VARIANTS_INVALID = new EnumVariant[0];
    private static final e_933_M[] COLORS_INVALID = new e_933_M[0];
    private static final g_2336_b ANVIL_GUI_TEXTURE = new g_2336_b("textures/gui/container/anvil.png");
    private static final g_2336_b BEACON_GUI_TEXTURE = new g_2336_b("textures/gui/container/beacon.png");
    private static final g_2336_b BREWING_STAND_GUI_TEXTURE = new g_2336_b("textures/gui/container/brewing_stand.png");
    private static final g_2336_b CHEST_GUI_TEXTURE = new g_2336_b("textures/gui/container/generic_54.png");
    private static final g_2336_b CRAFTING_TABLE_GUI_TEXTURE = new g_2336_b("textures/gui/container/crafting_table.png");
    private static final g_2336_b HORSE_GUI_TEXTURE = new g_2336_b("textures/gui/container/horse.png");
    private static final g_2336_b DISPENSER_GUI_TEXTURE = new g_2336_b("textures/gui/container/dispenser.png");
    private static final g_2336_b ENCHANTMENT_TABLE_GUI_TEXTURE = new g_2336_b("textures/gui/container/enchanting_table.png");
    private static final g_2336_b FURNACE_GUI_TEXTURE = new g_2336_b("textures/gui/container/furnace.png");
    private static final g_2336_b HOPPER_GUI_TEXTURE = new g_2336_b("textures/gui/container/hopper.png");
    private static final g_2336_b INVENTORY_GUI_TEXTURE = new g_2336_b("textures/gui/container/inventory.png");
    private static final g_2336_b SHULKER_BOX_GUI_TEXTURE = new g_2336_b("textures/gui/container/shulker_box.png");
    private static final g_2336_b VILLAGER_GUI_TEXTURE = new g_2336_b("textures/gui/container/villager2.png");

    public CustomGuiProperties(Properties props, String path) {
        ConnectedParser connectedparser = new ConnectedParser("CustomGuis");
        this.fileName = connectedparser.parseName(path);
        this.basePath = connectedparser.parseBasePath(path);
        this.container = (EnumContainer)connectedparser.parseEnum(props.getProperty("container"), EnumContainer.values(), "container");
        this.textureLocations = CustomGuiProperties.parseTextureLocations(props, "texture", this.container, "textures/gui/", this.basePath);
        this.nbtName = connectedparser.parseNbtTagValue("name", props.getProperty("name"));
        this.biomes = connectedparser.parseBiomes(props.getProperty("biomes"));
        this.heights = connectedparser.parseRangeListInt(props.getProperty("heights"));
        this.large = connectedparser.parseBooleanObject(props.getProperty("large"));
        this.trapped = connectedparser.parseBooleanObject(props.getProperty("trapped"));
        this.christmas = connectedparser.parseBooleanObject(props.getProperty("christmas"));
        this.ender = connectedparser.parseBooleanObject(props.getProperty("ender"));
        this.levels = connectedparser.parseRangeListInt(props.getProperty("levels"));
        this.professions = connectedparser.parseProfessions(props.getProperty("professions"));
        Enum[] acustomguiproperties$enumvariant = CustomGuiProperties.getContainerVariants(this.container);
        this.variants = (EnumVariant[])connectedparser.parseEnums(props.getProperty("variants"), acustomguiproperties$enumvariant, "variants", VARIANTS_INVALID);
        this.colors = CustomGuiProperties.parseEnumDyeColors(props.getProperty("colors"));
    }

    private static EnumVariant[] getContainerVariants(EnumContainer cont) {
        if (cont == EnumContainer.HORSE) {
            return VARIANTS_HORSE;
        }
        return cont == EnumContainer.DISPENSER ? VARIANTS_DISPENSER : new EnumVariant[]{};
    }

    private static e_933_M[] parseEnumDyeColors(String str) {
        if (str == null) {
            return null;
        }
        str = str.toLowerCase();
        String[] astring = Config.tokenize(str, " ");
        e_933_M[] adyecolor = new e_933_M[astring.length];
        for (int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            e_933_M dyecolor = CustomGuiProperties.parseEnumDyeColor(s);
            if (dyecolor == null) {
                CustomGuiProperties.warn("Invalid color: " + s);
                return COLORS_INVALID;
            }
            adyecolor[i] = dyecolor;
        }
        return adyecolor;
    }

    private static e_933_M parseEnumDyeColor(String str) {
        if (str == null) {
            return null;
        }
        e_933_M[] adyecolor = e_933_M.values();
        for (int i = 0; i < adyecolor.length; ++i) {
            e_933_M dyecolor = adyecolor[i];
            if (dyecolor.n_1700_B().equals(str)) {
                return dyecolor;
            }
            if (!dyecolor.R_4764_Y().equals(str)) continue;
            return dyecolor;
        }
        return null;
    }

    private static g_2336_b parseTextureLocation(String str, String basePath) {
        if (str == null) {
            return null;
        }
        Object s = TextureUtils.fixResourcePath(str = str.trim(), basePath);
        if (!((String)s).endsWith(".png")) {
            s = (String)s + ".png";
        }
        return new g_2336_b(basePath + "/" + (String)s);
    }

    private static Map<g_2336_b, g_2336_b> parseTextureLocations(Properties props, String property, EnumContainer container, String pathPrefix, String basePath) {
        HashMap<g_2336_b, g_2336_b> map = new HashMap<g_2336_b, g_2336_b>();
        String s = props.getProperty(property);
        if (s != null) {
            g_2336_b resourcelocation = CustomGuiProperties.getGuiTextureLocation(container);
            g_2336_b resourcelocation1 = CustomGuiProperties.parseTextureLocation(s, basePath);
            if (resourcelocation != null && resourcelocation1 != null) {
                map.put(resourcelocation, resourcelocation1);
            }
        }
        String s5 = property + ".";
        for (String string : props.keySet()) {
            if (!string.startsWith(s5)) continue;
            String s2 = string.substring(s5.length());
            s2 = s2.replace('\\', '/');
            s2 = StrUtils.removePrefixSuffix(s2, "/", ".png");
            String s3 = pathPrefix + s2 + ".png";
            String s4 = props.getProperty(string);
            g_2336_b resourcelocation2 = new g_2336_b(s3);
            g_2336_b resourcelocation3 = CustomGuiProperties.parseTextureLocation(s4, basePath);
            map.put(resourcelocation2, resourcelocation3);
        }
        return map;
    }

    private static g_2336_b getGuiTextureLocation(EnumContainer container) {
        if (container == null) {
            return null;
        }
        switch (container.ordinal()) {
            case 0: {
                return ANVIL_GUI_TEXTURE;
            }
            case 1: {
                return BEACON_GUI_TEXTURE;
            }
            case 2: {
                return BREWING_STAND_GUI_TEXTURE;
            }
            case 3: {
                return CHEST_GUI_TEXTURE;
            }
            case 4: {
                return CRAFTING_TABLE_GUI_TEXTURE;
            }
            case 12: {
                return null;
            }
            case 5: {
                return DISPENSER_GUI_TEXTURE;
            }
            case 6: {
                return ENCHANTMENT_TABLE_GUI_TEXTURE;
            }
            case 7: {
                return FURNACE_GUI_TEXTURE;
            }
            case 8: {
                return HOPPER_GUI_TEXTURE;
            }
            case 9: {
                return HORSE_GUI_TEXTURE;
            }
            case 13: {
                return INVENTORY_GUI_TEXTURE;
            }
            case 11: {
                return SHULKER_BOX_GUI_TEXTURE;
            }
            case 10: {
                return VILLAGER_GUI_TEXTURE;
            }
        }
        return null;
    }

    public boolean isValid(String path) {
        if (this.fileName != null && this.fileName.length() > 0) {
            if (this.basePath == null) {
                CustomGuiProperties.warn("No base path found: " + path);
                return false;
            }
            if (this.container == null) {
                CustomGuiProperties.warn("No container found: " + path);
                return false;
            }
            if (this.textureLocations.isEmpty()) {
                CustomGuiProperties.warn("No texture found: " + path);
                return false;
            }
            if (this.professions == ConnectedParser.PROFESSIONS_INVALID) {
                CustomGuiProperties.warn("Invalid professions or careers: " + path);
                return false;
            }
            if (this.variants == VARIANTS_INVALID) {
                CustomGuiProperties.warn("Invalid variants: " + path);
                return false;
            }
            if (this.colors == COLORS_INVALID) {
                CustomGuiProperties.warn("Invalid colors: " + path);
                return false;
            }
            return true;
        }
        CustomGuiProperties.warn("No name found: " + path);
        return false;
    }

    private static void warn(String str) {
        Config.warn("[CustomGuis] " + str);
    }

    private boolean matchesGeneral(EnumContainer ec, c_1514_x pos, T_1316_M blockAccess) {
        k_594_Q biome;
        if (this.container != ec) {
            return false;
        }
        if (this.biomes != null && !Matches.biome(biome = blockAccess.P_1922_E(pos), this.biomes)) {
            return false;
        }
        return this.heights == null || this.heights.isInRange(pos.getY());
    }

    public boolean matchesPos(EnumContainer ec, c_1514_x pos, T_1316_M blockAccess, k_2603_m screen) {
        String s;
        if (!this.matchesGeneral(ec, pos, blockAccess)) {
            return false;
        }
        if (this.nbtName != null && !this.nbtName.matchesValue(s = CustomGuiProperties.getName(screen))) {
            return false;
        }
        switch (ec.ordinal()) {
            case 1: {
                return this.matchesBeacon(pos, blockAccess);
            }
            case 3: {
                return this.matchesChest(pos, blockAccess);
            }
            case 5: {
                return this.matchesDispenser(pos, blockAccess);
            }
            case 11: {
                return this.matchesShulker(pos, blockAccess);
            }
        }
        return true;
    }

    public static String getName(k_2603_m screen) {
        x_282_a itextcomponent = screen.getTitle();
        return itextcomponent == null ? null : itextcomponent.J_1907_R();
    }

    private boolean matchesBeacon(c_1514_x pos, BlockAndTintGetter blockAccess) {
        int i;
        i_2154_H tileentity = blockAccess.getTileEntity(pos);
        if (!(tileentity instanceof x_3974_Q)) {
            return false;
        }
        x_3974_Q beacontileentity = (x_3974_Q)tileentity;
        return this.levels == null || this.levels.isInRange(i = beacontileentity.w_1484_f());
    }

    private boolean matchesChest(c_1514_x pos, BlockAndTintGetter blockAccess) {
        i_2154_H tileentity = blockAccess.getTileEntity(pos);
        if (tileentity instanceof t_693_s) {
            t_693_s chesttileentity = (t_693_s)tileentity;
            return this.matchesChest(chesttileentity, pos, blockAccess);
        }
        if (tileentity instanceof s_3081_t) {
            s_3081_t enderchesttileentity = (s_3081_t)tileentity;
            return this.matchesEnderChest(enderchesttileentity, pos, blockAccess);
        }
        return false;
    }

    private boolean matchesChest(t_693_s tec, c_1514_x pos, BlockAndTintGetter blockAccess) {
        K_4074_S blockstate = blockAccess.getBlockState(pos);
        p_1429_o chesttype = blockstate.J_1907_R(v_3445_Z.Q_4569_t) ? blockstate.R_4764_Y(v_3445_Z.Q_4569_t) : p_1429_o.n_1700_B;
        boolean flag = chesttype != p_1429_o.n_1700_B;
        boolean flag1 = tec instanceof f_395_A;
        boolean flag2 = CustomGuis.isChristmas;
        boolean flag3 = false;
        return this.matchesChest(flag, flag1, flag2, flag3);
    }

    private boolean matchesEnderChest(s_3081_t teec, c_1514_x pos, BlockAndTintGetter blockAccess) {
        return this.matchesChest(false, false, false, true);
    }

    private boolean matchesChest(boolean isLarge, boolean isTrapped, boolean isChristmas, boolean isEnder) {
        if (this.large != null && this.large != isLarge) {
            return false;
        }
        if (this.trapped != null && this.trapped != isTrapped) {
            return false;
        }
        if (this.christmas != null && this.christmas != isChristmas) {
            return false;
        }
        return this.ender == null || this.ender == isEnder;
    }

    private boolean matchesDispenser(c_1514_x pos, BlockAndTintGetter blockAccess) {
        EnumVariant customguiproperties$enumvariant;
        i_2154_H tileentity = blockAccess.getTileEntity(pos);
        if (!(tileentity instanceof l_3848_Y)) {
            return false;
        }
        l_3848_Y dispensertileentity = (l_3848_Y)tileentity;
        return this.variants == null || Config.equalsOne((Object)(customguiproperties$enumvariant = this.getDispenserVariant(dispensertileentity)), (Object[])this.variants);
    }

    private EnumVariant getDispenserVariant(l_3848_Y ted) {
        return ted instanceof DropperBlockEntity ? EnumVariant.DROPPER : EnumVariant.DISPENSER;
    }

    private boolean matchesShulker(c_1514_x pos, BlockAndTintGetter blockAccess) {
        e_933_M dyecolor;
        i_2154_H tileentity = blockAccess.getTileEntity(pos);
        if (!(tileentity instanceof a_433_S)) {
            return false;
        }
        a_433_S shulkerboxtileentity = (a_433_S)tileentity;
        return this.colors == null || Config.equalsOne(dyecolor = shulkerboxtileentity.s_956_w(), this.colors);
    }

    public boolean matchesEntity(EnumContainer ec, N_4263_v entity, T_1316_M blockAccess) {
        String s;
        if (!this.matchesGeneral(ec, entity.b_2312_j(), blockAccess)) {
            return false;
        }
        if (this.nbtName != null && !this.nbtName.matchesValue(s = entity.L_3570_A())) {
            return false;
        }
        switch (ec.ordinal()) {
            case 9: {
                return this.matchesHorse(entity, blockAccess);
            }
            case 10: {
                return this.matchesVillager(entity, blockAccess);
            }
        }
        return true;
    }

    private boolean matchesVillager(N_4263_v entity, BlockAndTintGetter blockAccess) {
        int i;
        VillagerData villagerdata;
        VillagerProfession villagerprofession;
        if (!(entity instanceof L_2225_p)) {
            return false;
        }
        L_2225_p villagerentity = (L_2225_p)entity;
        return this.professions == null || MatchProfession.matchesOne(villagerprofession = (villagerdata = villagerentity.c_2086_l()).J_1907_R(), i = villagerdata.R_4764_Y(), this.professions);
    }

    private boolean matchesHorse(N_4263_v entity, BlockAndTintGetter blockAccess) {
        g_4407_j llamaentity;
        e_933_M dyecolor;
        EnumVariant customguiproperties$enumvariant;
        if (!(entity instanceof U_2534_D)) {
            return false;
        }
        U_2534_D abstracthorseentity = (U_2534_D)entity;
        if (this.variants != null && !Config.equalsOne((Object)(customguiproperties$enumvariant = this.getHorseVariant(abstracthorseentity)), (Object[])this.variants)) {
            return false;
        }
        return this.colors == null || !(abstracthorseentity instanceof g_4407_j) || Config.equalsOne(dyecolor = (llamaentity = (g_4407_j)abstracthorseentity).Q_4222_k(), this.colors);
    }

    private EnumVariant getHorseVariant(U_2534_D entity) {
        if (entity instanceof Horse) {
            return EnumVariant.HORSE;
        }
        if (entity instanceof Donkey) {
            return EnumVariant.DONKEY;
        }
        if (entity instanceof Mule) {
            return EnumVariant.MULE;
        }
        return entity instanceof g_4407_j ? EnumVariant.LLAMA : null;
    }

    public EnumContainer getContainer() {
        return this.container;
    }

    public g_2336_b getTextureLocation(g_2336_b loc) {
        g_2336_b resourcelocation = this.textureLocations.get(loc);
        return resourcelocation == null ? loc : resourcelocation;
    }

    public String toString() {
        return "name: " + this.fileName + ", container: " + String.valueOf((Object)this.container) + ", textures: " + String.valueOf(this.textureLocations);
    }

    public static enum EnumContainer {
        ANVIL,
        BEACON,
        BREWING_STAND,
        CHEST,
        CRAFTING,
        DISPENSER,
        ENCHANTMENT,
        FURNACE,
        HOPPER,
        HORSE,
        VILLAGER,
        SHULKER_BOX,
        CREATIVE,
        INVENTORY;

    }

    private static enum EnumVariant {
        HORSE,
        DONKEY,
        MULE,
        LLAMA,
        DISPENSER,
        DROPPER;

    }
}


