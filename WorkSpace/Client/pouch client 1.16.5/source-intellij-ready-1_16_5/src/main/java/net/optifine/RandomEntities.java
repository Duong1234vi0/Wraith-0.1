/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import lightning.product.C_4114_x;
import lightning.product.N_4263_v;
import lightning.product.U_2912_j;
import lightning.product.X_4340_E;
import lightning.product.b_4507_u;
import lightning.product.f_2689_h;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.k_4690_i;
import lightning.product.ShoulderRidingEntity;
import lightning.product.r_4811_B;
import lightning.product.Horse;
import lightning.product.z_883_p;
import net.optifine.Config;
import net.optifine.IRandomEntity;
import net.optifine.RandomEntity;
import net.optifine.RandomEntityProperties;
import net.optifine.RandomTileEntity;
import net.optifine.reflect.ReflectorRaw;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;

public class RandomEntities {
    private static Map<String, RandomEntityProperties> mapProperties = new HashMap<String, RandomEntityProperties>();
    private static boolean active = false;
    private static z_883_p renderGlobal;
    private static RandomEntity randomEntity;
    private static f_2689_h tileEntityRendererDispatcher;
    private static RandomTileEntity randomTileEntity;
    private static boolean working;
    public static final String SUFFIX_PNG = ".png";
    public static final String SUFFIX_PROPERTIES = ".properties";
    public static final String PREFIX_TEXTURES_ENTITY = "textures/entity/";
    public static final String PREFIX_TEXTURES_PAINTING = "textures/painting/";
    public static final String PREFIX_TEXTURES = "textures/";
    public static final String PREFIX_OPTIFINE_RANDOM = "optifine/random/";
    public static final String PREFIX_OPTIFINE_MOB = "optifine/mob/";
    private static final String[] DEPENDANT_SUFFIXES;
    private static final String PREFIX_DYNAMIC_TEXTURE_HORSE = "horse/";
    private static final String[] HORSE_TEXTURES;
    private static final String[] HORSE_TEXTURES_ABBR;

    public static void entityLoaded(N_4263_v entity, b_4507_u world) {
        if (world != null) {
            C_4114_x entitydatamanager = entity.D_60_a();
            entitydatamanager.J_1907_R = entity.b_2312_j();
            entitydatamanager.n_1700_B = world.P_1922_E(entitydatamanager.J_1907_R);
            if (entity instanceof ShoulderRidingEntity) {
                ShoulderRidingEntity shoulderridingentity = (ShoulderRidingEntity)entity;
                RandomEntities.checkEntityShoulder(shoulderridingentity, false);
            }
        }
    }

    public static void entityUnloaded(N_4263_v entity, b_4507_u world) {
        if (entity instanceof ShoulderRidingEntity) {
            ShoulderRidingEntity shoulderridingentity = (ShoulderRidingEntity)entity;
            RandomEntities.checkEntityShoulder(shoulderridingentity, true);
        }
    }

    private static void checkEntityShoulder(ShoulderRidingEntity entity, boolean attach) {
        r_4811_B livingentity = entity.A_1306_N();
        if (livingentity == null) {
            livingentity = Config.getMinecraft().Y_259_p;
        }
        if (livingentity instanceof X_4340_E) {
            X_4340_E abstractclientplayerentity = (X_4340_E)livingentity;
            UUID uuid = entity.w_2705_t();
            if (attach) {
                U_2912_j compoundnbt1;
                U_2912_j compoundnbt = abstractclientplayerentity.A_1306_N();
                if (compoundnbt != null && compoundnbt.P_1922_E("UUID") && Config.equals(compoundnbt.n_1700_B("UUID"), uuid)) {
                    abstractclientplayerentity.Z_976_R = entity;
                }
                if ((compoundnbt1 = abstractclientplayerentity.D_3612_q()) != null && compoundnbt1.P_1922_E("UUID") && Config.equals(compoundnbt1.n_1700_B("UUID"), uuid)) {
                    abstractclientplayerentity.H_1990_U = entity;
                }
            } else {
                C_4114_x entitydatamanager = entity.D_60_a();
                if (abstractclientplayerentity.Z_976_R != null && Config.equals(abstractclientplayerentity.Z_976_R.w_2705_t(), uuid)) {
                    C_4114_x entitydatamanager1 = abstractclientplayerentity.Z_976_R.D_60_a();
                    entitydatamanager.J_1907_R = entitydatamanager1.J_1907_R;
                    entitydatamanager.n_1700_B = entitydatamanager1.n_1700_B;
                    abstractclientplayerentity.Z_976_R = null;
                }
                if (abstractclientplayerentity.H_1990_U != null && Config.equals(abstractclientplayerentity.H_1990_U.w_2705_t(), uuid)) {
                    C_4114_x entitydatamanager2 = abstractclientplayerentity.H_1990_U.D_60_a();
                    entitydatamanager.J_1907_R = entitydatamanager2.J_1907_R;
                    entitydatamanager.n_1700_B = entitydatamanager2.n_1700_B;
                    abstractclientplayerentity.H_1990_U = null;
                }
            }
        }
    }

    public static void worldChanged(b_4507_u oldWorld, b_4507_u newWorld) {
        if (newWorld instanceof k_4690_i) {
            k_4690_i clientworld = (k_4690_i)newWorld;
            for (N_4263_v entity : clientworld.J_1907_R()) {
                RandomEntities.entityLoaded(entity, newWorld);
            }
        }
        randomEntity.setEntity(null);
        randomTileEntity.setTileEntity(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static g_2336_b getTextureLocation(g_2336_b loc) {
        g_2336_b name;
        if (!active) {
            return loc;
        }
        if (working) {
            return loc;
        }
        try {
            working = true;
            IRandomEntity irandomentity = RandomEntities.getRandomEntityRendered();
            if (irandomentity != null) {
                String s = loc.J_1907_R();
                if (s.startsWith(PREFIX_DYNAMIC_TEXTURE_HORSE)) {
                    s = RandomEntities.getHorseTexturePath(s, PREFIX_DYNAMIC_TEXTURE_HORSE.length());
                }
                if (!s.startsWith(PREFIX_TEXTURES_ENTITY) && !s.startsWith(PREFIX_TEXTURES_PAINTING)) {
                    g_2336_b g_2336_b2 = loc;
                    return g_2336_b2;
                }
                RandomEntityProperties randomentityproperties = mapProperties.get(s);
                if (randomentityproperties == null) {
                    g_2336_b g_2336_b3 = loc;
                    return g_2336_b3;
                }
                g_2336_b g_2336_b4 = randomentityproperties.getTextureLocation(loc, irandomentity);
                return g_2336_b4;
            }
            name = loc;
        }
        finally {
            working = false;
        }
        return name;
    }

    private static String getHorseTexturePath(String path, int pos) {
        if (HORSE_TEXTURES != null && HORSE_TEXTURES_ABBR != null) {
            for (int i = 0; i < HORSE_TEXTURES_ABBR.length; ++i) {
                String s = HORSE_TEXTURES_ABBR[i];
                if (!path.startsWith(s, pos)) continue;
                return HORSE_TEXTURES[i];
            }
            return path;
        }
        return path;
    }

    public static IRandomEntity getRandomEntityRendered() {
        if (RandomEntities.renderGlobal.R_4764_Y != null) {
            randomEntity.setEntity(RandomEntities.renderGlobal.R_4764_Y);
            return randomEntity;
        }
        f_2689_h tileentityrendererdispatcher = tileEntityRendererDispatcher;
        if (f_2689_h.v_4262_N != null) {
            tileentityrendererdispatcher = tileEntityRendererDispatcher;
            i_2154_H tileentity = f_2689_h.v_4262_N;
            if (tileentity.c_3005_b() != null) {
                randomTileEntity.setTileEntity(tileentity);
                return randomTileEntity;
            }
        }
        return null;
    }

    private static RandomEntityProperties makeProperties(g_2336_b loc, boolean optifine) {
        RandomEntityProperties randomentityproperties;
        String s = loc.J_1907_R();
        g_2336_b resourcelocation = RandomEntities.getLocationProperties(loc, optifine);
        if (resourcelocation != null && (randomentityproperties = RandomEntities.parseProperties(resourcelocation, loc)) != null) {
            return randomentityproperties;
        }
        g_2336_b[] aresourcelocation = RandomEntities.getLocationsVariants(loc, optifine);
        return aresourcelocation == null ? null : new RandomEntityProperties(s, aresourcelocation);
    }

    private static RandomEntityProperties parseProperties(g_2336_b propLoc, g_2336_b resLoc) {
        try {
            String s = propLoc.J_1907_R();
            RandomEntities.dbg(resLoc.J_1907_R() + ", properties: " + s);
            InputStream inputstream = Config.getResourceStream(propLoc);
            if (inputstream == null) {
                RandomEntities.warn("Properties not found: " + s);
                return null;
            }
            PropertiesOrdered properties = new PropertiesOrdered();
            properties.load(inputstream);
            inputstream.close();
            RandomEntityProperties randomentityproperties = new RandomEntityProperties(properties, s, resLoc);
            return !randomentityproperties.isValid(s) ? null : randomentityproperties;
        }
        catch (FileNotFoundException filenotfoundexception) {
            RandomEntities.warn("File not found: " + resLoc.J_1907_R());
            return null;
        }
        catch (IOException ioexception) {
            ioexception.printStackTrace();
            return null;
        }
    }

    private static g_2336_b getLocationProperties(g_2336_b loc, boolean optifine) {
        String s1;
        String s2;
        String s3;
        g_2336_b resourcelocation = RandomEntities.getLocationRandom(loc, optifine);
        if (resourcelocation == null) {
            return null;
        }
        String s = resourcelocation.R_4764_Y();
        g_2336_b resourcelocation1 = new g_2336_b(s, s3 = (s2 = StrUtils.removeSuffix(s1 = resourcelocation.J_1907_R(), SUFFIX_PNG)) + SUFFIX_PROPERTIES);
        if (Config.hasResource(resourcelocation1)) {
            return resourcelocation1;
        }
        String s4 = RandomEntities.getParentTexturePath(s2);
        if (s4 == null) {
            return null;
        }
        g_2336_b resourcelocation2 = new g_2336_b(s, s4 + SUFFIX_PROPERTIES);
        return Config.hasResource(resourcelocation2) ? resourcelocation2 : null;
    }

    protected static g_2336_b getLocationRandom(g_2336_b loc, boolean optifine) {
        String s = loc.R_4764_Y();
        String s1 = loc.J_1907_R();
        String s2 = PREFIX_TEXTURES;
        String s3 = PREFIX_OPTIFINE_RANDOM;
        if (optifine) {
            s2 = PREFIX_TEXTURES_ENTITY;
            s3 = PREFIX_OPTIFINE_MOB;
        }
        if (!s1.startsWith(s2)) {
            return null;
        }
        String s4 = StrUtils.replacePrefix(s1, s2, s3);
        return new g_2336_b(s, s4);
    }

    private static String getPathBase(String pathRandom) {
        if (pathRandom.startsWith(PREFIX_OPTIFINE_RANDOM)) {
            return StrUtils.replacePrefix(pathRandom, PREFIX_OPTIFINE_RANDOM, PREFIX_TEXTURES);
        }
        return pathRandom.startsWith(PREFIX_OPTIFINE_MOB) ? StrUtils.replacePrefix(pathRandom, PREFIX_OPTIFINE_MOB, PREFIX_TEXTURES_ENTITY) : null;
    }

    protected static g_2336_b getLocationIndexed(g_2336_b loc, int index) {
        if (loc == null) {
            return null;
        }
        String s = loc.J_1907_R();
        int i = s.lastIndexOf(46);
        if (i < 0) {
            return null;
        }
        String s1 = s.substring(0, i);
        String s2 = s.substring(i);
        String s3 = s1 + index + s2;
        return new g_2336_b(loc.R_4764_Y(), s3);
    }

    private static String getParentTexturePath(String path) {
        for (int i = 0; i < DEPENDANT_SUFFIXES.length; ++i) {
            String s = DEPENDANT_SUFFIXES[i];
            if (!path.endsWith(s)) continue;
            return StrUtils.removeSuffix(path, s);
        }
        return null;
    }

    private static g_2336_b[] getLocationsVariants(g_2336_b loc, boolean optifine) {
        ArrayList<g_2336_b> list = new ArrayList<g_2336_b>();
        list.add(loc);
        g_2336_b resourcelocation = RandomEntities.getLocationRandom(loc, optifine);
        if (resourcelocation == null) {
            return null;
        }
        for (int i = 1; i < list.size() + 10; ++i) {
            int j = i + 1;
            g_2336_b resourcelocation1 = RandomEntities.getLocationIndexed(resourcelocation, j);
            if (!Config.hasResource(resourcelocation1)) continue;
            list.add(resourcelocation1);
        }
        if (list.size() <= 1) {
            return null;
        }
        g_2336_b[] aresourcelocation = list.toArray(new g_2336_b[list.size()]);
        RandomEntities.dbg(loc.J_1907_R() + ", variants: " + aresourcelocation.length);
        return aresourcelocation;
    }

    public static void update() {
        mapProperties.clear();
        active = false;
        if (Config.isRandomEntities()) {
            RandomEntities.initialize();
        }
    }

    private static void initialize() {
        renderGlobal = Config.getRenderGlobal();
        tileEntityRendererDispatcher = f_2689_h.J_1907_R;
        String[] astring = new String[]{PREFIX_OPTIFINE_RANDOM, PREFIX_OPTIFINE_MOB};
        String[] astring1 = new String[]{SUFFIX_PNG, SUFFIX_PROPERTIES};
        String[] astring2 = ResUtils.collectFiles(astring, astring1);
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < astring2.length; ++i) {
            RandomEntityProperties randomentityproperties;
            Object s = astring2[i];
            s = StrUtils.removeSuffix((String)s, astring1);
            s = StrUtils.trimTrailing((String)s, "0123456789");
            String s1 = RandomEntities.getPathBase((String)(s = (String)s + SUFFIX_PNG));
            if (set.contains(s1)) continue;
            set.add(s1);
            g_2336_b resourcelocation = new g_2336_b(s1);
            if (!Config.hasResource(resourcelocation) || (randomentityproperties = mapProperties.get(s1)) != null) continue;
            randomentityproperties = RandomEntities.makeProperties(resourcelocation, false);
            if (randomentityproperties == null) {
                randomentityproperties = RandomEntities.makeProperties(resourcelocation, true);
            }
            if (randomentityproperties == null) continue;
            mapProperties.put(s1, randomentityproperties);
        }
        active = !mapProperties.isEmpty();
    }

    public static void dbg(String str) {
        Config.dbg("RandomEntities: " + str);
    }

    public static void warn(String str) {
        Config.warn("RandomEntities: " + str);
    }

    static {
        randomEntity = new RandomEntity();
        randomTileEntity = new RandomTileEntity();
        working = false;
        DEPENDANT_SUFFIXES = new String[]{"_armor", "_eyes", "_exploding", "_shooting", "_fur", "_eyes", "_invulnerable", "_angry", "_tame", "_collar"};
        HORSE_TEXTURES = (String[])ReflectorRaw.getFieldValue(null, Horse.class, String[].class, 0);
        HORSE_TEXTURES_ABBR = (String[])ReflectorRaw.getFieldValue(null, Horse.class, String[].class, 1);
    }
}


