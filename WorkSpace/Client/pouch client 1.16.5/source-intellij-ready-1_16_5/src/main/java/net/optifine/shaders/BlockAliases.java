/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package net.optifine.shaders;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.MinecraftClient;
import lightning.product.g_2336_b;
import lightning.product.s_3834_w;
import lightning.product.v_3760_Q;
import net.optifine.Config;
import net.optifine.ConnectedProperties;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.BlockAlias;
import net.optifine.shaders.IShaderPack;
import net.optifine.shaders.ShaderPackNone;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.MacroProcessor;
import net.optifine.util.BlockUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;

public class BlockAliases {
    private static BlockAlias[][] blockAliases = null;
    private static boolean hasAliasMetadata = false;
    private static PropertiesOrdered blockLayerPropertes = null;
    private static boolean updateOnResourcesReloaded;
    private static List<List<BlockAlias>> legacyAliases;

    public static int getAliasBlockId(K_4074_S blockState) {
        int j;
        int i = blockState.multiplayerClientSuggestionProvider();
        BlockAlias blockalias = BlockAliases.getBlockAlias(i, j = blockState.w_1457_N());
        return blockalias != null ? blockalias.getAliasBlockId() : -1;
    }

    public static boolean hasAliasMetadata() {
        return hasAliasMetadata;
    }

    public static int getAliasMetadata(K_4074_S blockState) {
        int j;
        if (!hasAliasMetadata) {
            return 0;
        }
        int i = blockState.multiplayerClientSuggestionProvider();
        BlockAlias blockalias = BlockAliases.getBlockAlias(i, j = blockState.w_1457_N());
        return blockalias != null ? blockalias.getAliasMetadata() : 0;
    }

    public static BlockAlias getBlockAlias(int blockId, int metadata) {
        if (blockAliases == null) {
            return null;
        }
        if (blockId >= 0 && blockId < blockAliases.length) {
            BlockAlias[] ablockalias = blockAliases[blockId];
            if (ablockalias == null) {
                return null;
            }
            for (int i = 0; i < ablockalias.length; ++i) {
                BlockAlias blockalias = ablockalias[i];
                if (!blockalias.matches(blockId, metadata)) continue;
                return blockalias;
            }
            return null;
        }
        return null;
    }

    public static BlockAlias[] getBlockAliases(int blockId) {
        if (blockAliases == null) {
            return null;
        }
        return blockId >= 0 && blockId < blockAliases.length ? blockAliases[blockId] : null;
    }

    public static void resourcesReloaded() {
        if (updateOnResourcesReloaded) {
            updateOnResourcesReloaded = false;
            BlockAliases.update(Shaders.getShaderPack());
        }
    }

    public static void update(IShaderPack shaderPack) {
        BlockAliases.reset();
        if (shaderPack != null && !(shaderPack instanceof ShaderPackNone)) {
            if (Reflector.Loader_getActiveModList.exists() && MinecraftClient.A_4115_X().T_2506_i() == null) {
                Config.dbg("[Shaders] Delayed loading of block mappings after resources are loaded");
                updateOnResourcesReloaded = true;
            } else {
                List<List<BlockAlias>> list = new ArrayList<List<BlockAlias>>();
                String s = "/shaders/block.properties";
                InputStream inputstream = shaderPack.getResourceAsStream(s);
                if (inputstream != null) {
                    BlockAliases.loadBlockAliases(inputstream, s, list);
                }
                BlockAliases.loadModBlockAliases(list);
                if (list.size() <= 0) {
                    list = BlockAliases.getLegacyAliases();
                    hasAliasMetadata = true;
                }
                blockAliases = BlockAliases.toBlockAliasArrays(list);
            }
        }
    }

    private static void loadModBlockAliases(List<List<BlockAlias>> listBlockAliases) {
        String[] astring = ReflectorForge.getForgeModIds();
        for (int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            try {
                g_2336_b resourcelocation = new g_2336_b(s, "shaders/block.properties");
                InputStream inputstream = Config.getResourceStream(resourcelocation);
                BlockAliases.loadBlockAliases(inputstream, resourcelocation.toString(), listBlockAliases);
                continue;
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    private static void loadBlockAliases(InputStream in, String path, List<List<BlockAlias>> listBlockAliases) {
        if (in != null) {
            try {
                in = MacroProcessor.process(in, path, true);
                PropertiesOrdered properties = new PropertiesOrdered();
                properties.load(in);
                in.close();
                Config.dbg("[Shaders] Parsing block mappings: " + path);
                ConnectedParser connectedparser = new ConnectedParser("Shaders");
                for (String string : ((Properties)properties).keySet()) {
                    String s1 = properties.getProperty(string);
                    if (string.startsWith("layer.")) {
                        if (blockLayerPropertes == null) {
                            blockLayerPropertes = new PropertiesOrdered();
                        }
                        blockLayerPropertes.put(string, s1);
                        continue;
                    }
                    String s2 = "block.";
                    if (!string.startsWith(s2)) {
                        Config.warn("[Shaders] Invalid block ID: " + string);
                        continue;
                    }
                    String s3 = StrUtils.removePrefix(string, s2);
                    int i = Config.parseInt(s3, -1);
                    if (i < 0) {
                        Config.warn("[Shaders] Invalid block ID: " + string);
                        continue;
                    }
                    MatchBlock[] amatchblock = connectedparser.parseMatchBlocks(s1);
                    if (amatchblock != null && amatchblock.length >= 1) {
                        BlockAlias blockalias = new BlockAlias(i, amatchblock);
                        BlockAliases.addToList(listBlockAliases, blockalias);
                        continue;
                    }
                    Config.warn("[Shaders] Invalid block ID mapping: " + string + "=" + s1);
                }
            }
            catch (IOException ioexception) {
                Config.warn("[Shaders] Error reading: " + path);
            }
        }
    }

    private static void addToList(List<List<BlockAlias>> blocksAliases, BlockAlias ba) {
        int[] aint = ba.getMatchBlockIds();
        for (int i = 0; i < aint.length; ++i) {
            int j = aint[i];
            while (j >= blocksAliases.size()) {
                blocksAliases.add(null);
            }
            List<BlockAlias> list = blocksAliases.get(j);
            if (list == null) {
                list = new ArrayList<BlockAlias>();
                blocksAliases.set(j, list);
            }
            BlockAlias blockalias = new BlockAlias(ba.getAliasBlockId(), ba.getMatchBlocks(j));
            list.add(blockalias);
        }
    }

    private static BlockAlias[][] toBlockAliasArrays(List<List<BlockAlias>> listBlocksAliases) {
        BlockAlias[][] ablockalias = new BlockAlias[listBlocksAliases.size()][];
        for (int i = 0; i < ablockalias.length; ++i) {
            List<BlockAlias> list = listBlocksAliases.get(i);
            if (list == null) continue;
            ablockalias[i] = list.toArray(new BlockAlias[list.size()]);
        }
        return ablockalias;
    }

    private static List<List<BlockAlias>> getLegacyAliases() {
        if (legacyAliases == null) {
            legacyAliases = BlockAliases.makeLegacyAliases();
        }
        return legacyAliases;
    }

    private static List<List<BlockAlias>> makeLegacyAliases() {
        try {
            String s = "flattening_ids.txt";
            Config.dbg("Using legacy block aliases: " + s);
            ArrayList<List<BlockAlias>> list = new ArrayList<List<BlockAlias>>();
            ArrayList<String> list1 = new ArrayList<String>();
            int i = 0;
            InputStream inputstream = Config.getOptiFineResourceStream("/" + s);
            if (inputstream == null) {
                return list;
            }
            String[] astring = Config.readLines(inputstream);
            for (int j = 0; j < astring.length; ++j) {
                int k = j + 1;
                String s1 = astring[j];
                if (s1.trim().length() <= 0) continue;
                list1.add(s1);
                if (s1.startsWith("#")) continue;
                if (s1.startsWith("alias")) {
                    String[] astring1 = Config.tokenize(s1, " ");
                    String s2 = astring1[1];
                    String s3 = astring1[2];
                    String s4 = "{Name:'" + s3 + "'";
                    List list2 = list1.stream().filter(sq -> sq.startsWith(s4)).collect(Collectors.toList());
                    if (list2.size() <= 0) {
                        Config.warn("Block not processed: " + s1);
                        continue;
                    }
                    for (String s5 : list2) {
                        String s6 = "{Name:'" + s2 + "'";
                        String s7 = s5.replace(s4, s6);
                        list1.add(s7);
                        BlockAliases.addLegacyAlias(s7, k, list);
                        ++i;
                    }
                    continue;
                }
                BlockAliases.addLegacyAlias(s1, k, list);
                ++i;
            }
            Config.dbg("Legacy block aliases: " + i);
            return list;
        }
        catch (IOException ioexception) {
            Config.warn("Error loading legacy block aliases: " + ioexception.getClass().getName() + ": " + ioexception.getMessage());
            return new ArrayList<List<BlockAlias>>();
        }
    }

    private static void addLegacyAlias(String line, int lineNum, List<List<BlockAlias>> listAliases) {
        String[] astring = Config.tokenize(line, " ");
        if (astring.length != 4) {
            Config.warn("Invalid flattening line: " + line);
        } else {
            String s = astring[0];
            String s1 = astring[1];
            int i = Config.parseInt(astring[2], Integer.MIN_VALUE);
            int j = Config.parseInt(astring[3], Integer.MIN_VALUE);
            if (i >= 0 && j >= 0) {
                try {
                    JsonParser jsonparser = new JsonParser();
                    JsonObject jsonobject = jsonparser.parse(s).getAsJsonObject();
                    String s2 = jsonobject.get("Name").getAsString();
                    g_2336_b resourcelocation = new g_2336_b(s2);
                    T_2915_h block = BlockUtils.getBlock(resourcelocation);
                    if (block == null) {
                        Config.warn("Invalid block name (" + lineNum + "): " + s2);
                        return;
                    }
                    K_4074_S blockstate = block.multiplayerClientSuggestionProvider();
                    Collection<v_3760_Q> collection = blockstate.k_2293_S();
                    LinkedHashMap<v_3760_Q, Comparable> map = new LinkedHashMap<v_3760_Q, Comparable>();
                    JsonObject jsonobject1 = (JsonObject)jsonobject.get("Properties");
                    if (jsonobject1 != null) {
                        for (Map.Entry entry : jsonobject1.entrySet()) {
                            String s3 = (String)entry.getKey();
                            String s4 = ((JsonElement)entry.getValue()).getAsString();
                            v_3760_Q property = ConnectedProperties.getProperty(s3, collection);
                            if (property == null) {
                                Config.warn("Invalid property (" + lineNum + "): " + s3);
                                continue;
                            }
                            Comparable comparable = ConnectedParser.parsePropertyValue(property, s4);
                            if (comparable == null) {
                                Config.warn("Invalid property value (" + lineNum + "): " + s4);
                                continue;
                            }
                            map.put(property, comparable);
                        }
                    }
                    int k = blockstate.multiplayerClientSuggestionProvider();
                    while (listAliases.size() <= k) {
                        listAliases.add(null);
                    }
                    List<BlockAlias> list = listAliases.get(k);
                    if (list == null) {
                        list = new ArrayList<BlockAlias>(BlockUtils.getMetadataCount(block));
                        listAliases.set(k, list);
                    }
                    MatchBlock matchblock = BlockAliases.getMatchBlock(blockstate.J_1907_R(), blockstate.multiplayerClientSuggestionProvider(), map);
                    BlockAliases.addBlockAlias(list, i, j, matchblock);
                }
                catch (Exception exception) {
                    Config.warn("Error parsing: " + line);
                }
            } else {
                Config.warn("Invalid blockID or metadata (" + lineNum + "): " + i + ":" + j);
            }
        }
    }

    private static void addBlockAlias(List<BlockAlias> listBlockAliases, int aliasBlockId, int aliasMetadata, MatchBlock matchBlock) {
        for (BlockAlias blockalias : listBlockAliases) {
            if (blockalias.getAliasBlockId() != aliasBlockId || blockalias.getAliasMetadata() != aliasMetadata) continue;
            MatchBlock[] amatchblock = blockalias.getMatchBlocks();
            for (int i = 0; i < amatchblock.length; ++i) {
                MatchBlock matchblock = amatchblock[i];
                if (matchblock.getBlockId() != matchBlock.getBlockId()) continue;
                matchblock.addMetadatas(matchBlock.getMetadatas());
                return;
            }
        }
        BlockAlias blockalias1 = new BlockAlias(aliasBlockId, aliasMetadata, new MatchBlock[]{matchBlock});
        listBlockAliases.add(blockalias1);
    }

    private static MatchBlock getMatchBlock(T_2915_h block, int blockId, Map<v_3760_Q, Comparable> mapProperties) {
        ArrayList<K_4074_S> list = new ArrayList<K_4074_S>();
        Set<v_3760_Q> collection = mapProperties.keySet();
        for (K_4074_S k_4074_S : BlockUtils.getBlockStates(block)) {
            boolean bl = true;
            for (v_3760_Q property : collection) {
                Object comparable1;
                if (!k_4074_S.J_1907_R(property)) {
                    bl = false;
                    break;
                }
                Comparable comparable = mapProperties.get(property);
                if (comparable.equals(comparable1 = k_4074_S.R_4764_Y(property))) continue;
                bl = false;
                break;
            }
            if (!bl) continue;
            list.add(k_4074_S);
        }
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
        for (K_4074_S k_4074_S : list) {
            set.add(k_4074_S.w_1457_N());
        }
        Integer[] integerArray = set.toArray(new Integer[set.size()]);
        int[] nArray = Config.toPrimitive(integerArray);
        MatchBlock matchblock = new MatchBlock(blockId, nArray);
        return matchblock;
    }

    private static void checkLegacyAliases() {
        for (g_2336_b resourcelocation : V_3137_a.q_4610_l.G_564_y()) {
            T_2915_h block = V_3137_a.q_4610_l.n_1700_B(resourcelocation);
            int i = block.multiplayerClientSuggestionProvider().multiplayerClientSuggestionProvider();
            BlockAlias[] ablockalias = BlockAliases.getBlockAliases(i);
            if (ablockalias == null) {
                Config.warn("Block has no alias: " + String.valueOf(block));
                continue;
            }
            for (K_4074_S blockstate : BlockUtils.getBlockStates(block)) {
                int j = blockstate.w_1457_N();
                BlockAlias blockalias = BlockAliases.getBlockAlias(i, j);
                if (blockalias != null) continue;
                Config.warn("State has no alias: " + String.valueOf(blockstate));
            }
        }
    }

    public static PropertiesOrdered getBlockLayerPropertes() {
        return blockLayerPropertes;
    }

    public static void reset() {
        blockAliases = null;
        hasAliasMetadata = false;
        blockLayerPropertes = null;
    }

    public static int getRenderType(K_4074_S blockState) {
        if (hasAliasMetadata) {
            T_2915_h block = blockState.J_1907_R();
            if (block instanceof s_3834_w) {
                return 1;
            }
            O_2369_F blockrendertype = blockState.w_1484_f();
            return blockrendertype != O_2369_F.J_1907_R && blockrendertype != O_2369_F.R_4764_Y ? blockrendertype.ordinal() : blockrendertype.ordinal() + 1;
        }
        return blockState.w_1484_f().ordinal();
    }
}



