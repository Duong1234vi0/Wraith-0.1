/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.D_3925_G;
import lightning.product.G_2149_k;
import lightning.product.N_4263_v;
import lightning.product.S_922_s;
import lightning.product.T_2915_h;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_3485_j;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_3005_b;
import lightning.product.e_1174_E;
import lightning.product.g_2336_b;
import lightning.product.h_256_u;
import lightning.product.k_4690_i;
import lightning.product.n_1494_c;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.PrimedTnt;
import lightning.product.v_1669_V;
import lightning.product.z_883_p;
import net.optifine.Config;
import net.optifine.DynamicLight;
import net.optifine.DynamicLightsMap;
import net.optifine.config.ConnectedParser;
import net.optifine.config.EntityTypeNameLocator;
import net.optifine.config.IObjectLocator;
import net.optifine.config.ItemLocator;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.util.PropertiesOrdered;

public class DynamicLights {
    private static DynamicLightsMap mapDynamicLights = new DynamicLightsMap();
    private static Map<String, Integer> mapEntityLightLevels = new HashMap<String, Integer>();
    private static Map<q_1613_l, Integer> mapItemLightLevels = new HashMap<q_1613_l, Integer>();
    private static long timeUpdateMs = 0L;
    private static final double MAX_DIST = 7.5;
    private static final double MAX_DIST_SQ = 56.25;
    private static final int LIGHT_LEVEL_MAX = 15;
    private static final int LIGHT_LEVEL_FIRE = 15;
    private static final int LIGHT_LEVEL_BLAZE = 10;
    private static final int LIGHT_LEVEL_MAGMA_CUBE = 8;
    private static final int LIGHT_LEVEL_MAGMA_CUBE_CORE = 13;
    private static final int LIGHT_LEVEL_GLOWSTONE_DUST = 8;
    private static final int LIGHT_LEVEL_PRISMARINE_CRYSTALS = 8;
    private static final h_256_u<Z_1993_T> PARAMETER_ITEM_STACK = (h_256_u)Reflector.EntityItem_ITEM.getValue();
    private static boolean initialized;

    public static void entityAdded(N_4263_v entityIn, z_883_p renderGlobal) {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void entityRemoved(N_4263_v entityIn, z_883_p renderGlobal) {
        DynamicLightsMap dynamicLightsMap = mapDynamicLights;
        synchronized (dynamicLightsMap) {
            DynamicLight dynamiclight = mapDynamicLights.remove(entityIn.j_276_v());
            if (dynamiclight != null) {
                dynamiclight.updateLitChunks(renderGlobal);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void update(z_883_p renderGlobal) {
        long i = System.currentTimeMillis();
        if (i >= timeUpdateMs + 50L) {
            timeUpdateMs = i;
            if (!initialized) {
                DynamicLights.initialize();
            }
            DynamicLightsMap dynamicLightsMap = mapDynamicLights;
            synchronized (dynamicLightsMap) {
                DynamicLights.updateMapDynamicLights(renderGlobal);
                if (mapDynamicLights.size() > 0) {
                    List<DynamicLight> list = mapDynamicLights.valueList();
                    for (int j = 0; j < list.size(); ++j) {
                        DynamicLight dynamiclight = list.get(j);
                        dynamiclight.update(renderGlobal);
                    }
                }
            }
        }
    }

    private static void initialize() {
        initialized = true;
        mapEntityLightLevels.clear();
        mapItemLightLevels.clear();
        String[] astring = ReflectorForge.getForgeModIds();
        for (int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            try {
                g_2336_b resourcelocation = new g_2336_b(s, "optifine/dynamic_lights.properties");
                InputStream inputstream = Config.getResourceStream(resourcelocation);
                DynamicLights.loadModConfiguration(inputstream, resourcelocation.toString(), s);
                continue;
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        if (mapEntityLightLevels.size() > 0) {
            Config.dbg("DynamicLights entities: " + mapEntityLightLevels.size());
        }
        if (mapItemLightLevels.size() > 0) {
            Config.dbg("DynamicLights items: " + mapItemLightLevels.size());
        }
    }

    private static void loadModConfiguration(InputStream in, String path, String modId) {
        if (in != null) {
            try {
                PropertiesOrdered properties = new PropertiesOrdered();
                properties.load(in);
                in.close();
                Config.dbg("DynamicLights: Parsing " + path);
                ConnectedParser connectedparser = new ConnectedParser("DynamicLights");
                DynamicLights.loadModLightLevels(properties.getProperty("entities"), mapEntityLightLevels, new EntityTypeNameLocator(), connectedparser, path, modId);
                DynamicLights.loadModLightLevels(properties.getProperty("items"), mapItemLightLevels, new ItemLocator(), connectedparser, path, modId);
            }
            catch (IOException ioexception) {
                Config.warn("DynamicLights: Error reading " + path);
            }
        }
    }

    private static <T> void loadModLightLevels(String prop, Map<T, Integer> mapLightLevels, IObjectLocator<T> ol, ConnectedParser cp, String path, String modId) {
        if (prop != null) {
            String[] astring = Config.tokenize(prop, " ");
            for (int i = 0; i < astring.length; ++i) {
                String s = astring[i];
                String[] astring1 = Config.tokenize(s, ":");
                if (astring1.length != 2) {
                    cp.warn("Invalid entry: " + s + ", in:" + path);
                    continue;
                }
                String s1 = astring1[0];
                String s2 = astring1[1];
                String s3 = modId + ":" + s1;
                g_2336_b resourcelocation = new g_2336_b(s3);
                T t = ol.getObject(resourcelocation);
                if (t == null) {
                    cp.warn("Object not found: " + s3);
                    continue;
                }
                int j = cp.parseInt(s2, -1);
                if (j >= 0 && j <= 15) {
                    mapLightLevels.put(t, new Integer(j));
                    continue;
                }
                cp.warn("Invalid light level: " + s);
            }
        }
    }

    private static void updateMapDynamicLights(z_883_p renderGlobal) {
        Iterable<N_4263_v> allEntities;
        double distSq;
        double dz;
        double dy;
        double dx;
        b_4507_u clientworld = renderGlobal.Y_259_p();
        if (clientworld == null) {
            return;
        }
        k_4690_i clw = null;
        c_3005_b btw = null;
        if (clientworld instanceof k_4690_i) {
            clw = (k_4690_i)clientworld;
        } else if (clientworld instanceof c_3005_b) {
            btw = (c_3005_b)clientworld;
        }
        N_4263_v player = Config.getMinecraft().g_2268_R();
        if (player == null) {
            return;
        }
        double playerX = player.O_3598_v();
        double playerY = player.X_2960_b();
        double playerZ = player.l_2647_k();
        double CHECK_RADIUS = 64.0;
        double CHECK_RADIUS_SQ = 4096.0;
        ArrayList<Integer> entitiesToRemove = new ArrayList<Integer>();
        for (DynamicLight existingLight : mapDynamicLights.valueList()) {
            N_4263_v existingEntity = existingLight.getEntity();
            if (existingEntity == null || existingEntity.t_4219_U) {
                entitiesToRemove.add(existingEntity != null ? existingEntity.j_276_v() : -1);
                continue;
            }
            dx = existingEntity.O_3598_v() - playerX;
            distSq = dx * dx + (dy = existingEntity.X_2960_b() - playerY) * dy + (dz = existingEntity.l_2647_k() - playerZ) * dz;
            if (distSq > 4096.0) {
                entitiesToRemove.add(existingEntity.j_276_v());
                existingLight.updateLitChunks(renderGlobal);
                continue;
            }
            int lightLevel = DynamicLights.getLightLevel(existingEntity);
            if (lightLevel > 0) continue;
            entitiesToRemove.add(existingEntity.j_276_v());
            existingLight.updateLitChunks(renderGlobal);
        }
        for (Integer entityId : entitiesToRemove) {
            if (entityId < 0) continue;
            mapDynamicLights.remove(entityId);
        }
        Iterable<N_4263_v> iterable = clw != null ? clw.J_1907_R() : (allEntities = btw != null ? btw.J_1907_R() : null);
        if (allEntities == null) {
            return;
        }
        for (N_4263_v entity : allEntities) {
            int lightLevel;
            int entityId;
            if (entity == null || entity.t_4219_U || (distSq = (dx = entity.O_3598_v() - playerX) * dx + (dy = entity.X_2960_b() - playerY) * dy + (dz = entity.l_2647_k() - playerZ) * dz) > 4096.0 || mapDynamicLights.get(entityId = entity.j_276_v()) != null || (lightLevel = DynamicLights.getLightLevel(entity)) <= 0) continue;
            DynamicLight dynamiclight = new DynamicLight(entity);
            mapDynamicLights.put(entityId, dynamiclight);
        }
    }

    public static int getCombinedLight(c_1514_x pos, int combinedLight) {
        double d0 = DynamicLights.getLightLevel(pos);
        return DynamicLights.getCombinedLight(d0, combinedLight);
    }

    public static int getCombinedLight(N_4263_v entity, int combinedLight) {
        double d0 = DynamicLights.getLightLevel(entity.b_2312_j());
        if (entity == Config.getMinecraft().Y_259_p) {
            double d1 = DynamicLights.getLightLevel(entity);
            d0 = Math.max(d0, d1);
        }
        return DynamicLights.getCombinedLight(d0, combinedLight);
    }

    public static int getCombinedLight(double lightPlayer, int combinedLight) {
        int j;
        int i;
        if (lightPlayer > 0.0 && (i = (int)(lightPlayer * 16.0)) > (j = combinedLight & 0xFF)) {
            combinedLight &= 0xFFFFFF00;
            combinedLight |= i;
        }
        return combinedLight;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static double getLightLevel(c_1514_x pos) {
        double d0 = 0.0;
        DynamicLightsMap dynamicLightsMap = mapDynamicLights;
        synchronized (dynamicLightsMap) {
            List<DynamicLight> list = mapDynamicLights.valueList();
            int i = list.size();
            for (int j = 0; j < i; ++j) {
                double d8;
                double d9;
                double d10;
                double d6;
                double d5;
                DynamicLight dynamiclight = list.get(j);
                int k = dynamiclight.getLastLightLevel();
                if (k <= 0) continue;
                double d1 = dynamiclight.getLastPosX();
                double d2 = dynamiclight.getLastPosY();
                double d3 = dynamiclight.getLastPosZ();
                double d4 = (double)pos.getX() - d1;
                double d7 = d4 * d4 + (d5 = (double)pos.getY() - d2) * d5 + (d6 = (double)pos.getZ() - d3) * d6;
                if (d7 > 56.25 || !((d10 = (d9 = 1.0 - (d8 = Math.sqrt(d7)) / 7.5) * (double)k) > d0)) continue;
                d0 = d10;
            }
        }
        return Config.limit(d0, 0.0, 15.0);
    }

    public static int getLightLevel(Z_1993_T itemStack) {
        v_1669_V blockitem;
        T_2915_h block;
        if (itemStack == null) {
            return 0;
        }
        q_1613_l item = itemStack.J_1907_R();
        if (item instanceof v_1669_V && (block = (blockitem = (v_1669_V)item).v_4262_N()) != null) {
            return block.multiplayerClientSuggestionProvider().u_1723_Y();
        }
        if (item == Items.u_1934_K) {
            return a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider().u_1723_Y();
        }
        if (item != Items.A_2487_t && item != Items.C_3528_u) {
            Integer integer;
            if (item == Items.AdvancementList) {
                return 8;
            }
            if (item == Items.FrostedIceBlock) {
                return 8;
            }
            if (item == Items.S_3844_E) {
                return 8;
            }
            if (item == Items.FallingBlock) {
                return a_3742_W.k_578_l.multiplayerClientSuggestionProvider().u_1723_Y() / 2;
            }
            if (!mapItemLightLevels.isEmpty() && (integer = mapItemLightLevels.get(item)) != null) {
                return integer;
            }
            return 0;
        }
        return 10;
    }

    public static int getLightLevel(N_4263_v entity) {
        b_3485_j creeperentity;
        String s;
        Integer integer;
        a_3913_L playerentity;
        if (entity == Config.getMinecraft().g_2268_R() && !Config.isDynamicHandLight()) {
            return 0;
        }
        if (entity instanceof a_3913_L && (playerentity = (a_3913_L)entity).d_2461_k()) {
            return 0;
        }
        if (entity.RealmsPersistence()) {
            return 15;
        }
        if (!mapEntityLightLevels.isEmpty() && (integer = mapEntityLightLevels.get(s = EntityTypeNameLocator.getEntityTypeName(entity))) != null) {
            return integer;
        }
        if (entity instanceof D_3925_G) {
            return 15;
        }
        if (entity instanceof PrimedTnt) {
            return 15;
        }
        if (entity instanceof G_2149_k) {
            G_2149_k blazeentity = (G_2149_k)entity;
            return blazeentity.RealmsPersistence() ? 15 : 10;
        }
        if (entity instanceof S_922_s) {
            S_922_s magmacubeentity = (S_922_s)entity;
            return (double)magmacubeentity.J_1907_R > 0.6 ? 13 : 8;
        }
        if (entity instanceof b_3485_j && (double)(creeperentity = (b_3485_j)entity).c_3005_b(0.0f) > 0.001) {
            return 15;
        }
        if (entity instanceof r_4811_B) {
            r_4811_B livingentity = (r_4811_B)entity;
            Z_1993_T itemstack3 = livingentity.A_2714_y();
            int i = DynamicLights.getLightLevel(itemstack3);
            Z_1993_T itemstack = livingentity.S_4035_N();
            int j = DynamicLights.getLightLevel(itemstack);
            Z_1993_T itemstack1 = livingentity.J_1907_R(e_1174_E.u_1723_Y);
            int k = DynamicLights.getLightLevel(itemstack1);
            int l = Math.max(i, j);
            return Math.max(l, k);
        }
        if (entity instanceof n_1494_c) {
            n_1494_c itementity = (n_1494_c)entity;
            Z_1993_T itemstack2 = DynamicLights.getItemStack(itementity);
            return DynamicLights.getLightLevel(itemstack2);
        }
        return 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void removeLights(z_883_p renderGlobal) {
        DynamicLightsMap dynamicLightsMap = mapDynamicLights;
        synchronized (dynamicLightsMap) {
            List<DynamicLight> list = mapDynamicLights.valueList();
            for (int i = 0; i < list.size(); ++i) {
                DynamicLight dynamiclight = list.get(i);
                dynamiclight.updateLitChunks(renderGlobal);
            }
            mapDynamicLights.clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void clear() {
        DynamicLightsMap dynamicLightsMap = mapDynamicLights;
        synchronized (dynamicLightsMap) {
            mapDynamicLights.clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int getCount() {
        DynamicLightsMap dynamicLightsMap = mapDynamicLights;
        synchronized (dynamicLightsMap) {
            return mapDynamicLights.size();
        }
    }

    public static Z_1993_T getItemStack(n_1494_c entityItem) {
        return entityItem.D_60_a().n_1700_B(PARAMETER_ITEM_STACK);
    }
}


