/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import lightning.product.o_2576_A;
import net.optifine.Config;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.render.RenderTypes;
import net.optifine.shaders.BlockAliases;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;

public class CustomBlockLayers {
    private static o_2576_A[] renderLayers = null;
    public static boolean active = false;

    public static o_2576_A getRenderLayer(BlockGetter worldReader, K_4074_S blockState, c_1514_x blockPos) {
        if (renderLayers == null) {
            return null;
        }
        if (blockState.t_148_a(worldReader, blockPos)) {
            return null;
        }
        int i = blockState.multiplayerClientSuggestionProvider();
        return i > 0 && i < renderLayers.length ? renderLayers[i] : null;
    }

    public static void update() {
        PropertiesOrdered propertiesordered;
        renderLayers = null;
        active = false;
        ArrayList<o_2576_A> list = new ArrayList<o_2576_A>();
        String s = "optifine/block.properties";
        Properties properties = ResUtils.readProperties(s, "CustomBlockLayers");
        if (properties != null) {
            CustomBlockLayers.readLayers(s, properties, list);
        }
        if (Config.isShaders() && (propertiesordered = BlockAliases.getBlockLayerPropertes()) != null) {
            String s1 = "shaders/block.properties";
            CustomBlockLayers.readLayers(s1, propertiesordered, list);
        }
        if (!list.isEmpty()) {
            renderLayers = list.toArray(new o_2576_A[list.size()]);
            active = true;
        }
    }

    private static void readLayers(String pathProps, Properties props, List<o_2576_A> list) {
        Config.dbg("CustomBlockLayers: " + pathProps);
        CustomBlockLayers.readLayer("solid", RenderTypes.SOLID, props, list);
        CustomBlockLayers.readLayer("cutout", RenderTypes.CUTOUT, props, list);
        CustomBlockLayers.readLayer("cutout_mipped", RenderTypes.CUTOUT_MIPPED, props, list);
        CustomBlockLayers.readLayer("translucent", RenderTypes.TRANSLUCENT, props, list);
    }

    private static void readLayer(String name, o_2576_A layer, Properties props, List<o_2576_A> listLayers) {
        ConnectedParser connectedparser;
        MatchBlock[] amatchblock;
        String s = "layer." + name;
        String s1 = props.getProperty(s);
        if (s1 != null && (amatchblock = (connectedparser = new ConnectedParser("CustomBlockLayers")).parseMatchBlocks(s1)) != null) {
            for (int i = 0; i < amatchblock.length; ++i) {
                MatchBlock matchblock = amatchblock[i];
                int j = matchblock.getBlockId();
                if (j <= 0) continue;
                while (listLayers.size() < j + 1) {
                    listLayers.add(null);
                }
                if (listLayers.get(j) != null) {
                    Config.warn("CustomBlockLayers: Block layer is already set, block: " + j + ", layer: " + name);
                }
                listLayers.set(j, layer);
            }
        }
    }

    public static boolean isActive() {
        return active;
    }
}


